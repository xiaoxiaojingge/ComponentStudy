package com.itjing.collect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.itjing.entity.fastjson.Student;
import com.itjing.entity.fastjson.Teacher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lijing
 * @Date: 2021年05月19日 13:53
 * @Description: 从0开始fastjson漏洞分析，参考网址：https://www.cnblogs.com/piaomiaohongchen/p/14777856.html
 */
public class fastjson漏洞分析 {
    /**
     * fastjson是一个Java语言编写的高性能功能完善的JSON库。
     * 它采用一种“假定有序快速匹配”的算法，把JSON Parse的性能提升到极致，
     * 是目前Java语言中最快的JSON库。Fastjson接口简单易用，
     * 已经被广泛使用在缓存序列化、协议交互、Web输出、Android客户端等多种应用场景。
     * 简单点说就是帮我们处理json数据的。
     */
    @Test
    public void fastjson_test1() {
        Student student = new Student(1, "jack", 24);
        System.out.println(JSON.toJSON(student));
    }

    @Test
    public void fastjson_test2() {
        List<Student> studentList = new ArrayList<Student>();
        for (int i = 0; i < 4; i++) {
            Student student = new Student(i, "jack" + i, 23 + i);
            studentList.add(student);
        }
        List<Teacher> teacherList = new ArrayList<Teacher>();
        Teacher teacher = new Teacher();
        teacher.setStudentList(studentList);

        System.out.println(JSON.toJSON(teacher));
    }

    /**
     * 除了使用toJSON方法转换外,还可以使用toJSONString方法
     */
    @Test
    public void fastjson_test3() {
        Student student = new Student(1, "jack", 24);
        System.out.println(JSON.toJSONString(student)); // {"age":24,"id":1,"name":"jack"}
    }

    /**
     * 只需要Student对象的id和age字段,不要name字段,怎么做?
     */
    @Test
    public void fastjson_test4() {
        Student student = new Student(1, "jack", 24);
        // 过滤只要id和age字段
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Student.class, "id", "age");
        String value = JSON.toJSONString(student, filter);
        System.out.println(value); // {"age":24,"id":1}
    }

    /**
     * 反序列化,str类型数据转换成class类型对象
     */
    @Test
    public void fastjson_test5() {
        Student student = new Student(1, "jack", 24);
        String value = JSON.toJSONString(student);
        System.out.println("转换成json数据：" + value); // 转换成json数据：{"age":24,"id":1,"name":"jack"}
        System.out.println("str类型json数据转换成class类型对象：" + JSON.parseObject(value, Student.class)); // str类型json数据转换成class类型对象：Student(id=1, name=jack, age=24)
    }

    /**
     * 通过上面代码,我们可以发现一个重点:
     * fastjson会处理字符串类型的json数据,上面的value变量是字符串类型,这对我们后续漏洞分析很有帮助
     */

    /**
     * 查看打印结果后发现多了个@type字段,说明了我们Student对象转换成json数据的数据类型,
     * 告诉我们是com.itjing.entity.fastjson.Student类型的数据被转换成json数据了.
     */
    @Test
    public void fastjson_test6() {
        Student student = new Student(1, "jack", 24);
        String value = JSON.toJSONString(student, SerializerFeature.WriteClassName);
        System.out.println(value); // {"@type":"com.itjing.entity.fastjson.Student","age":24,"id":1,"name":"jack"}
        Student student1 = JSON.parseObject(value, Student.class);
        System.out.println(student1); // Student(id=1, name=jack, age=24)
    }

    /**
     * 前面说了fastjson会处理我们的字符串json,直接写一段字符串json数据
     */
    @Test
    public void fastjson_test7() {
        String jsonStr = "{\"age\":24,\"id\":1,\"name\":\"jack\"}";
        System.out.println(jsonStr); // {"age":24,"id":1,"name":"jack"}
        System.out.println(JSON.parseObject(jsonStr)); // {"name":"jack","id":1,"age":24}
        /**
         * 我们像前面这样写,会发现最后字符串json没有转换成对象
         * 因为fastjson找不到我们要转换的json数据在哪个类,这里我们要声明类型
         */
        String jsonStr2 = "{\"@type\":\"com.itjing.entity.fastjson.Student\",\"age\":24,\"id\":1,\"name\":\"jack\"}";
        System.out.println(JSON.parseObject(jsonStr2)); // {"name":"jack","id":1,"age":24}
        System.out.println(JSON.parse(jsonStr2)); // Student(id=1, name=jack, age=24)
    }
}
