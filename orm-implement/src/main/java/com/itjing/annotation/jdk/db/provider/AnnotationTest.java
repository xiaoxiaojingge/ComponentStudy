package com.itjing.annotation.jdk.db.provider;

import com.itjing.annotation.jdk.db.provider.parser.AnnotationParser;
import org.junit.jupiter.api.Test;

/**
 * @author: lijing
 * @Date: 2021年06月02日 17:07
 * 第五步：创建AnnotationTest 类，用以测试我们实现的极简ORM框架的效果
 */
public class AnnotationTest {
    @Test
    public void testDBAnnotation() {
        User testDto = new User("123", "34");
        User testDto1 = new User("123", "test1");
        User testDto2 = new User("", "test1,test2,test3,test4");
        String sql = AnnotationParser.assembleSqlFromObj(testDto);
        String sql1 = AnnotationParser.assembleSqlFromObj(testDto1);
        String sql2 = AnnotationParser.assembleSqlFromObj(testDto2);
        System.out.println(sql);
        System.out.println(sql1);
        System.out.println(sql2);
    }
}
