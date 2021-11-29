package com.itjing;

import com.itjing.base.listener.ContextRefreshedListener;
import com.itjing.mapstruct.RoomConverter;
import com.itjing.mapstruct.StudentConverter;
import com.itjing.pojo.Room;
import com.itjing.pojo.Student;
import com.itjing.vo.RoomVo;
import com.itjing.vo.StudentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class MybatisGeneratorApplication implements ApplicationRunner, CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisGeneratorApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MybatisGeneratorApplication.class);
        /* 添加监听，这里写的监听类主要是spring初始化完毕后，将对应的mapper注入给通用Service */
        springApplication.addListeners(new ContextRefreshedListener());
        springApplication.run(args);


        // mapstruct
        Student student = new Student();
        student.setId(1).setName("张三").setGender(1).setBirthday(new Date()).setHome("bj");
        StudentVo vo = StudentConverter.INSTANCE.student2Vo(student);
        System.out.println(vo);
        System.out.println("===========================");
        Room room = new Room();
        room.setId(100).setStudent(student).setTime(new Date());
        RoomVo roomVo = RoomConverter.INSTANCE.building2Vo(room);
        System.out.println(roomVo);

    }


    // 实现 ApplicationRunner, CommandLineRunner，这两个接口可以在 springboot 启动后，做一些事情，执行 run 方法
    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("ApplicationRunner....run");
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("CommandLineRunner....run");
    }
}
