package com.itjing;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itjing.mapper.UserMapper;
import com.itjing.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {
    // 继承了BaseMapper，所有的方法都来自己父类    
    // 我们也可以编写自己的扩展方法！
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        // 参数是一个 Wrapper ，条件构造器，这里我们先不用    使用null        
        // 查询全部用户
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    //测试插入
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("lihui");
        user.setAge(25);
        user.setEmail("1404538859@qq.com");
        int result = userMapper.insert(user);// 帮我们自动生成id
        System.out.println(result);// 受影响的行数
        System.out.println(user);// 发现id会自动回填
    }

    //测试更新
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1336870289706672130L);
        user.setName("itjing");
        user.setEmail("2427259171@qq.com");
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    // 测试乐观锁成功！
    @Test
    public void testOptimisticlocker() {
        // 1、查询用户信息
        User user = userMapper.selectById(1L);
        // 2、修改用户信息    
        user.setName("ajing");
        user.setEmail("1045455624@qq.com");
        // 3、执行更新操作    
        userMapper.updateById(user);
    }

    // 测试乐观锁失败！在多线程下！
    @Test
    public void testOptimisticlocker2() {
        //线程 1
        User user = userMapper.selectById(1L);
        user.setName("jinggege");
        user.setEmail("2427259171@qq.com");

        // 模拟另外一个线程执行了插队操作，这里并没有写多线程，只是模拟插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("lijinggege");
        user2.setEmail("2427259171@qq.com");
        userMapper.updateById(user2);

        userMapper.updateById(user); // 如果没有乐观锁就会覆盖插队线程的值！
    }

    //测试查询
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    //测试批量查询
    @Test
    public void testSelectByBatchIds() {
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        userList.forEach(System.out::println);
    }

    //按条件查询之使用map操作
    @Test
    public void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
        /*自定义查询*/
        map.put("name", "jinggege");
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    public void testPage() {
        //  参数一：当前页    
        //  参数二：页面大小    
        //  使用了分页插件之后，所有的分页操作也变得简单的！
        IPage<User> page = new Page<>(2, 5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }

    // 测试删除
    @Test
    public void testDeleteById() {
        userMapper.deleteById(1L);
    }

    //通过id批量删除
    @Test
    public void testDeleteBatchIds() {
        userMapper.deleteBatchIds(Arrays.asList(1336870289706672131L, 1336870289706672132L));
    }

    //通过map删除
    @Test
    public void testDeleteByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name", "itjing");
        userMapper.deleteByMap(map);
    }


}
