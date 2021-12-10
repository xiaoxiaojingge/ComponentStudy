package org.itjing.time;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

/**
 * @author lijing
 * @date 2021年12月10日 14:52
 * @description
 */
public class DateTimeDemo {
    @Test
    public void testLocalDate() {
        // LocalDate：表示日期，有年、月、日
        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);

        LocalDate date = LocalDate.of(2021, 12, 12);
        System.out.println("date = " + date);

        int year = now.getYear();
        System.out.println("year = " + year);
        int month = now.getMonth().getValue();
        System.out.println("month = " + month);
        month = now.getMonthValue();
        System.out.println("month = " + month);
        int day = now.getDayOfMonth();
        System.out.println("day = " + day);
    }

    @Test
    public void testLocalTime() {
        // LocalTime：表示时间，有时、分、秒
        LocalTime now = LocalTime.now();
        System.out.println("now = " + now);

        LocalTime time = LocalTime.of(14, 53, 44);
        System.out.println("time = " + time);

        int hour = now.getHour();
        System.out.println("hour = " + hour);
        int minute = now.getMinute();
        System.out.println("minute = " + minute);
        int second = now.getSecond();
        System.out.println("second = " + second);
        int nano = now.getNano();
        System.out.println("nano = " + nano);
    }

    @Test
    public void testLocalDateTime() {
        // LocalDateTime：表示日期时间，有年、月、日、时、分、秒
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);

        LocalDateTime dateTime = LocalDateTime.of(2021, 12, 12, 14, 54, 50);
        System.out.println("dateTime = " + dateTime);

        int year = now.getYear();
        System.out.println("year = " + year);
        int month = now.getMonthValue();
        System.out.println("month = " + month);
        month = now.getMonth().getValue();
        System.out.println("month = " + month);
        int day = now.getDayOfMonth();
        System.out.println("day = " + day);
        int hour = now.getHour();
        System.out.println("hour = " + hour);
        int minute = now.getMinute();
        System.out.println("minute = " + minute);
        int second = now.getSecond();
        System.out.println("second = " + second);
        int nano = now.getNano();
        System.out.println("nano = " + nano);
    }

    /**
     * 修改时间
     */
    @Test
    public void testLocalDateTime2() {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime localDateTime = now.withYear(2021).withMinute(5);
        System.out.println("now = " + now);
        System.out.println("localDateTime = " + localDateTime);

        LocalDateTime localDateTime1 = now.plusYears(1);
        System.out.println("localDateTime1 = " + localDateTime1);
    }


    /**
     * 比较时间
     */
    @Test
    public void testLocalDateTime3() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.now().plusYears(1);
        boolean before = now.isBefore(localDateTime);
        System.out.println("before = " + before);
    }

    @Test
    public void testInstant() {
        // Instant 内部保存了秒和纳秒，一般不是给用户使用的，而是方便我们程序做一些统计
        Instant now = Instant.now();
        System.out.println("now = " + now);

        Instant instant = now.plusSeconds(20);
        System.out.println("instant = " + instant);

        long epochSecond = now.getEpochSecond();
        System.out.println("epochSecond = " + epochSecond);
        int nano = now.getNano();
        System.out.println("nano = " + nano);
    }

    @Test
    public void testDurationAndPeriod() {
        Duration duration = Duration.between(LocalTime.now(), LocalTime.now().plusSeconds(60));

        System.out.println("相差天 = " + duration.toDays());
        System.out.println("相差小时 = " + duration.toHours());
        System.out.println("相差分钟 = " + duration.toMinutes());
        System.out.println("相差毫秒 = " + duration.toNanos());
        System.out.println("相差秒 = " + duration.getSeconds());

        Period period = Period.between(LocalDate.now(), LocalDate.now().plusDays(2).plusMonths(2));
        System.out.println("相差年 = " + period.getYears());
        System.out.println("相差月 = " + period.getMonths());
        System.out.println("相差日 = " + period.getDays());
    }

    @Test
    public void testTemporalAdjusters() {
        LocalDateTime now = LocalDateTime.now();
        // 将日期和时间调整到"下一个月的第一天"
        LocalDateTime localDateTime = now.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("localDateTime = " + localDateTime);
    }

    @Test
    public void testZoneId() {
        // 获取所有的时区信息
        ZoneId.getAvailableZoneIds().forEach(System.out::println);
        // 获取当前默认的时区
        String id = ZoneId.systemDefault().getId();
        System.out.println("默认的时区id = " + id);
        // 不带时区，获取计算机的当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("不带时区的当前时间 = " + now);
        // 操作带时区的类
        ZonedDateTime bz = ZonedDateTime.now(Clock.systemUTC());
        System.out.println("世界标准时间 = " + bz);
        ZonedDateTime time = ZonedDateTime.now(ZoneId.systemDefault());
        System.out.println("带时区的当前时间 = " + time);
        // 修改时区
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        // withZoneSameInstant既改时区，也改时间
        ZonedDateTime zonedDateTime = dateTime.withZoneSameInstant(ZoneId.systemDefault());
        // withZoneSameLocal只改时区
        ZonedDateTime zonedDateTime1 = dateTime.withZoneSameLocal(ZoneId.systemDefault());
        System.out.println("修改时区和时间 = " + zonedDateTime);
        System.out.println("只改时区 = " + zonedDateTime1);
    }
}
