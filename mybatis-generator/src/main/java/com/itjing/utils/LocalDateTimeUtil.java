package com.itjing.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

/**
 * @author lijing
 * @date 2021年12月09日 11:02
 * @description LocalDateTime工具类
 */
public class LocalDateTimeUtil {

	/**
	 * 1. adjustInto 把指定的值赋值给传入的对象，使其拥有相同的时间。 2. atOffset
	 * 将LocalDateTime与偏移量(offset)组合以创建OffsetDateTime。 3. atZone
	 * 结合LocalDateTime和指定时区创建一个ZonedDateTime 4. compareTo 比较两个LocalDateTime 5. format
	 * 格式化LocalDateTime生成一个字符串 6. from 转换TemporalAccessor为LocalDateTime 7. get
	 * 得到LocalDateTime的指定字段的值 8. getDayOfMonth 得到LocalDateTime是月的第几天 9. getDayOfWeek
	 * 得到LocalDateTime是星期几 10. getDayOfYear 得到LocalDateTime是年的第几天 11. getHour
	 * 得到LocalDateTime的小时 12. getLong 得到LocalDateTime指定字段的值 13. getMinute
	 * 得到LocalDateTime的分钟 14. getMonth 得到LocalDateTime的月份 15. getMonthValue
	 * 得到LocalDateTime的月份，从1到12 16. getNano 得到LocalDateTime的纳秒数 17. getSecond
	 * 得到LocalDateTime的秒数 18. getYear 得到LocalDateTime的年份 19. isAfter
	 * 判断LocalDateTime是否在指定LocalDateTime 20. isBefore 判断LocalDateTime是否在指定LocalDateTime
	 * 21. isEqual 判断两个LocalDateTime是否相等 22. isSupported 判断LocalDateTime是否支持指定时间字段或单元 23.
	 * minus 返回LocalDateTime减去指定数量的时间得到的值 24. minusDays 返回LocalDateTime减去指定天数得到的值 25.
	 * minusHours 返回LocalDateTime减去指定小时数得到的值 26. minusMinutes 返回LocalDateTime减去指定分钟数得到的值
	 * 27. minusMonths 返回LocalDateTime减去指定月数得到的值 28. minusNanos 返回LocalDateTime减去指定纳秒数得到的值
	 * 29. minusSeconds 返回LocalDateTime减去指定秒数得到的值 30. minusWeeks
	 * 返回LocalDateTime减去指定星期数得到的值 31. minusYears 返回LocalDateTime减去指定年数得到的值 32. now
	 * 返回指定时钟的当前LocalDateTime 33. of 根据年、月、日、时、分、秒、纳秒等创建LocalDateTime 34. ofEpochSecond
	 * 根据秒数(从1970-01-0100:00:00开始)创建LocalDateTime 35. ofInstant
	 * 根据Instant和ZoneId创建LocalDateTime 36. parse 解析字符串得到LocalDateTime 37. plus
	 * 返回LocalDateTime加上指定数量的时间得到的值 38. plusDays 返回LocalDateTime加上指定天数得到的值 39. plusHours
	 * 返回LocalDateTime加上指定小时数得到的值 40. plusMinutes 返回LocalDateTime加上指定分钟数得到的值 41.
	 * plusMonths 返回LocalDateTime加上指定月数得到的值 42. plusNanos 返回LocalDateTime加上指定纳秒数得到的值 43.
	 * plusSeconds 返回LocalDateTime加上指定秒数得到的值 44. plusWeeks 返回LocalDateTime加上指定星期数得到的值 45.
	 * plusYears 返回LocalDateTime加上指定年数得到的值 46. query 查询LocalDateTime 47. range 返回指定时间字段的范围
	 * 48. toLocalDate 返回LocalDateTime的LocalDate部分 49. toLocalTime
	 * 返回LocalDateTime的LocalTime部分 50. toString 返回LocalDateTime的字符串表示 51. truncatedTo
	 * 返回LocalDateTime截取到指定时间单位的拷贝 52. until 计算LocalDateTime和另一个LocalDateTime 53. with
	 * 返回LocalDateTime指定字段更改为新值后的拷贝 54. withDayOfMonth 返回LocalDateTime月的第几天更改为新值后的拷贝 55.
	 * withDayOfYear 返回LocalDateTime年的第几天更改为新值后的拷贝 56. withHour
	 * 返回LocalDateTime的小时数更改为新值后的拷贝 57. withMinute 返回LocalDateTime的分钟数更改为新值后的拷贝 58.
	 * withMonth 返回LocalDateTime的月份更改为新值后的拷贝 59. withNano 返回LocalDateTime的纳秒数更改为新值后的拷贝 60.
	 * withSecond 返回LocalDateTime的秒数更改为新值后的拷贝 61. withYear 返回LocalDateTime年份更改为新值后的拷贝
	 */

	// 数据库格式的日期
	public static final String SQL_MONTH = "yyyy-MM";

	public static final String SQL_DATE = "yyyy-MM-dd";

	public static final String SQL_TIME = "yyyy-MM-dd HH:mm:ss";

	public static final String SQL_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SS";

	// 斜杠格式的日期
	public static final String DATE = "yyyy/MM/dd";

	public static final String TIMESTAMP = "yyyy/MM/dd HH:mm:ss.SS";

	public static final String TIMESTAMP_SHORT = "yyyy/MM/dd HH:mm";

	public static final String TIME = "HH:mm:ss";

	public static final String TIME_SHORT = "HH:mm";

	// 不常用日期格式
	public static final String CHINESE_DATE = "yyyy年MM月dd日";

	public static final String DATE_TIME = "yyyyMMddHHmmss";

	public static final String DATE_TIME_DETAIL = "yyyyMMddHHmmssSS";

	public static final String DATE_DAY = "yyyyMMdd";

	public static final String DATE_HOUR = "yyyyMMddHH";

	/**
	 * 防止被实例化
	 */
	private LocalDateTimeUtil() {
	}

	/**
	 * Date转LocalDateTime 使用系统时区
	 * @param date
	 * @return
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
		Instant instant = date.toInstant();
		return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * LocalDateTime转Date 使用系统时区
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
		return Date.from(zdt.toInstant());
	}

	/**
	 * 日期转字符串
	 * @param localDateTime
	 * @param pattern
	 * @return
	 */
	public static String dateTimeToStr(LocalDateTime localDateTime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDateTime.format(formatter);
	}

	/**
	 * 将字符串日期解析为java.time.LocalDateTime
	 * @param dateTimeStr
	 * @param pattern
	 * @return
	 */
	public static LocalDateTime strToLocalDateTime(String dateTimeStr, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.parse(dateTimeStr, formatter);
	}

	/**
	 * 开始日期，补齐" 00:00:00"
	 * @param localDateTime
	 * @return
	 */
	public static LocalDateTime getStartDateTimeWithHMS(LocalDateTime localDateTime) {
		return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
	}

	/**
	 * 结束日期，补齐" 23:59:59"
	 * @param localDateTime
	 * @return
	 */
	public static LocalDateTime getEndDateWithHMS(LocalDateTime localDateTime) {
		return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
	}

	/**
	 * 获取几年后的LocalDateTime
	 * @param localDateTime
	 * @param count
	 * @return
	 */
	public static LocalDateTime getAfterYears(LocalDateTime localDateTime, int count) {
		return localDateTime.plusYears(count);
	}

	/**
	 * 获取几月后的LocalDateTime
	 * @param localDateTime
	 * @param count
	 * @return
	 */
	public static LocalDateTime getAfterMonths(LocalDateTime localDateTime, int count) {
		return localDateTime.plusMonths(count);
	}

	/**
	 * 获取几天后的LocalDateTime
	 * @param localDateTime
	 * @param count
	 * @return
	 */
	public static LocalDateTime getAfterDays(LocalDateTime localDateTime, int count) {
		return localDateTime.plusDays(count);
	}

	/**
	 * 获取几小时后的LocalDateTime
	 * @param localDateTime
	 * @param count
	 * @return
	 */
	public static LocalDateTime getAfterHours(LocalDateTime localDateTime, int count) {
		return localDateTime.plusHours(count);
	}

	/**
	 * 获取几分钟后的LocalDateTime
	 * @param localDateTime
	 * @param count
	 * @return
	 */
	public static LocalDateTime getAfterMinutes(LocalDateTime localDateTime, int count) {
		return localDateTime.plusMinutes(count);
	}

	/**
	 * 获取几秒钟后的LocalDateTime
	 * @param localDateTime
	 * @param count
	 * @return
	 */
	public static LocalDateTime getAfterSeconds(LocalDateTime localDateTime, int count) {
		return localDateTime.plusSeconds(count);
	}

	/**
	 * 获得当前年的第一天
	 * @param
	 * @return
	 */
	public static LocalDateTime getYearFirstDay(LocalDateTime localDateTime) {
		return localDateTime.with(TemporalAdjusters.firstDayOfYear());
	}

	/**
	 * 获得当前年的最后一天
	 * @param
	 * @return
	 */
	public static LocalDateTime getYearLastDay(LocalDateTime localDateTime) {
		return localDateTime.with(TemporalAdjusters.lastDayOfYear());
	}

	/**
	 * 获得当前月的第一天
	 * @param
	 * @return
	 */
	public static LocalDateTime getMonthFirstDay(LocalDateTime localDateTime) {
		return localDateTime.with(TemporalAdjusters.firstDayOfMonth());
	}

	/**
	 * 获得当前月的最后一天
	 * @param
	 * @return
	 */
	public static LocalDateTime getMonthLastDay(LocalDateTime localDateTime) {
		return localDateTime.with(TemporalAdjusters.lastDayOfMonth());
	}

	/**
	 * 获得当前星期的第一天
	 * @param localDateTime
	 * @param locale 默认Locale.CHINA 周日为一周的第一天
	 * @return
	 */
	public static LocalDateTime getWeekFirstDay(LocalDateTime localDateTime, Locale locale) {
		return localDateTime.with(WeekFields.of(locale == null ? Locale.CHINA : locale).dayOfWeek(), 1);
	}

	/**
	 * 获得当前星期的最后一天
	 * @param localDateTime
	 * @param locale 默认默认Locale.CHINA 周日为一周的第一天
	 * @return
	 */
	public static LocalDateTime getWeekLastDay(LocalDateTime localDateTime, Locale locale) {
		return localDateTime.with(WeekFields.of(locale == null ? Locale.CHINA : locale).dayOfWeek(), 7);
	}

	/**
	 * 计算两个日期之间相差年数
	 * @param smallDateTime 较小的时间
	 * @param bigDateTime 较大的时间
	 * @return 相差年数
	 */
	public static int getYearDiff(LocalDateTime smallDateTime, LocalDateTime bigDateTime) {
		return (int) smallDateTime.until(bigDateTime, ChronoUnit.YEARS);
	}

	/**
	 * 计算两个日期之间相差月数
	 * @param smallDateTime 较小的时间
	 * @param bigDateTime 较大的时间
	 * @return 相差月数
	 */
	public static int getMonthDiff(LocalDateTime smallDateTime, LocalDateTime bigDateTime) {
		return (int) smallDateTime.until(bigDateTime, ChronoUnit.MONTHS);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * @param smallDateTime 较小的时间
	 * @param bigDateTime 较大的时间
	 * @return 相差天数
	 */
	public static int getDayDiff(LocalDateTime smallDateTime, LocalDateTime bigDateTime) {
		return (int) smallDateTime.until(bigDateTime, ChronoUnit.DAYS);
	}

	/**
	 * 计算两个日期之间相差小时数
	 * @param smallDateTime 较小的时间
	 * @param bigDateTime 较大的时间
	 * @return 相差小时数
	 */
	public static int getHourDiff(LocalDateTime smallDateTime, LocalDateTime bigDateTime) {
		return (int) smallDateTime.until(bigDateTime, ChronoUnit.HOURS);
	}

	/**
	 * 计算两个日期之间相差分钟数
	 * @param smallDateTime
	 * @param bigDateTime
	 * @return 相差分钟数
	 */
	public static int getMinutesDiff(LocalDateTime smallDateTime, LocalDateTime bigDateTime) {
		return (int) smallDateTime.until(bigDateTime, ChronoUnit.MINUTES);
	}

	/**
	 * 计算两个日期之间相差秒数
	 * @param smallDateTime
	 * @param bigDateTime
	 * @return 相差秒数
	 */
	public static int getSecondsDiff(LocalDateTime smallDateTime, LocalDateTime bigDateTime) {
		return (int) smallDateTime.until(bigDateTime, ChronoUnit.SECONDS);
	}

}
