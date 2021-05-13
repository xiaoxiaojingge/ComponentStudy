package com.itjing.redisson.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: lijing
 * @Date: 2021/04/28 9:23
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("redisson")
public class RedissonController {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 最佳实战： 使用 lock.lock(xxx, TimeUnit.SECONDS);
     * 因为省掉了整个续期操作，我们只要保证自动解锁时间大于业务执行时间即可。
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        // 1、获取一把锁，只要锁的名字一样，就是同一把锁
        RLock lock = redissonClient.getLock("my-lock");

        // 2、加锁，无需调用 unlock 方法手动解锁，
        // 阻塞式等待，默认加的锁都是30s时间
        lock.lock();
        // （1）锁自动续期，如果业务超长，运行期间自动给锁续上新的30s，不用担心业务时间过长，锁自动过期被删掉
        // （2）加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，锁默认在30s以后自动删除

        // 问题： lock.lock(10, TimeUnit.SECONDS); 在锁时间到了以后，不会自动续期。
        // 1、如果我们传递了锁的超时时间，就发送给redis执行脚本，进行占锁，默认超时时间就是我们指定的时间
        // 2、如果我们未指定锁的超时时间，就使用 lockWatchdogTimeout = 30 * 1000 ，看门狗的默认时间，可以根据源码溯源
        //  只要占锁成功，就会启动一个定时任务【重新给锁设置过期时间，新的过期时间就是看门狗的默认时间】，
        //  1/3 个看门狗时间，续一次期，每隔10s都会自动再次续期，续成 30s

        try {
            log.info("加锁成功，执行业务..." + Thread.currentThread().getId());
            TimeUnit.SECONDS.sleep(20);
        } catch (Exception e) {

        } finally {
            // 3、解锁
            log.info("释放锁..." + Thread.currentThread().getId());
            lock.unlock();
        }
        return "hello";
    }

    /*---------------------读写锁测试-----------------------*/
    /**
     * 写锁，保证一定能读到最新数据，修改期间，写锁是一个排它锁（互斥锁、独享锁）。读锁是一个共享锁。
     * 写锁没有释放，读就必须等待。
     * 读 + 读：相当于无锁，并发读，只会在 redis 中记录好所有当前的读锁。他们都会同时加锁成功。
     * 写 + 读：等待写锁释放
     * 写 + 写：阻塞方式
     * 读 + 写：有读锁，写也需要等待。
     * 只要有写的存在，都必须等待
     * @return
     */
    @GetMapping("/write")
    public String writeValue() {
        RReadWriteLock lock = redissonClient.getReadWriteLock("rw-lock");
        String s = "";
        RLock rLock = lock.writeLock();
        // 改数据加写锁
        rLock.lock();
        try {
            s = UUID.randomUUID().toString();
            TimeUnit.SECONDS.sleep(30);
            redisTemplate.opsForValue().set("writeValue", s);
        } catch (InterruptedException e) {
            log.error("writeValue()------------>" + e.getMessage());
        } finally {
            rLock.unlock();
        }
        return s;
    }

    /**
     * 读
     *
     * @return
     */
    @GetMapping("/read")
    public String readValue() {
        RReadWriteLock lock = redissonClient.getReadWriteLock("rw-lock");
        String s = "";
        RLock rLock = lock.readLock();
        // 读数据加读锁
        rLock.lock();
        try {
            s = redisTemplate.opsForValue().get("writeValue");
        } catch (Exception e) {
            log.error("readValue()------------>" + e.getMessage());
        } finally {
            rLock.unlock();
        }
        return s;
    }

    /*---------------------闭锁测试-----------------------*/
    /**
     *
     * 放假，锁门
     * 5个班都走完了，我们可以锁大门
     */
    @GetMapping("/lockDoor")
    @ResponseBody
    public String lockDoor() throws InterruptedException {
        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        door.trySetCount(5);
        door.await(); // 等待闭锁都完成
        return "放假了...";
    }

    @GetMapping("/gogogo/{id}")
    @ResponseBody
    public String gogogo(@PathVariable("id") Long id) {
        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        door.countDown(); // 计数减 1
        return id + "班的人走了...";
    }

    /*---------------------信号量测试-----------------------*/
    /**
     * 车库停车
     * 3车位
     * 信号量也可以用作分布式限流
     */
    @GetMapping("/park")
    public String park() throws InterruptedException {
        RSemaphore park = redissonClient.getSemaphore("park");
        // park.acquire(); // 获取一个信号，获取一个值，占一个车位，阻塞式获取，获取不到就一直等待，一定要获取到
        // 尝试获取，不行就算了，可以做限流操作
        boolean b = park.tryAcquire();
        if (b) {
            // 执行业务
        } else {
            return "error";
        }
        return "ok=>" + b;
    }

    @GetMapping("/go")
    public String go() {
        RSemaphore park = redissonClient.getSemaphore("park");
        park.release(); // 释放一个车位
        return "ok";
    }
}
