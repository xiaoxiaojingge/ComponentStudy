package org.itjing.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lijing
 * @date 2021年12月10日 14:56
 * @description
 */
public class DateTimeFormatterDemo {
    @Test
    public void test() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        // 格式化 调用LocalDateTime
        String format = now.format(df);
        System.out.println("format = " + format);

        // 解析 调用LocalDateTime
        LocalDateTime parse = LocalDateTime.parse(format, df);
        System.out.println("parse = " + parse);

        // 格式化
        format = df.format(now);
        System.out.println("format = " + format);

        // 解析
        parse = df.parse(format, LocalDateTime::from);
        System.out.println("parse = " + parse);
    }
}
