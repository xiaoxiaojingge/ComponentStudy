package com.itjing.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lijing
 * @date 2021年11月14日 10:43
 * @description 时间、日期工具类
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN1 = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String PATTERN2 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static final String PATTERN3 = "yyyyMMdd";

    public static final String PATTERN4 = "yyyy-MM-dd";

    public static final String PATTERN5 = "yyyyMMddHHmmss";

    public static final String PATTERN6 = "yyyy-MM-dd HH:mm";

    public static final String YYYY_MM_DD_HHMMSS_S = "yyyy-MM-dd HH:mm:ss.S";

    public static final String ZONE1 = "Asia/Shanghai";

    public static final int DATE_LENGTH = 17;

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * @param nowTimeStr
     * @param startTimeStr
     * @param endTimeStr
     * @return
     * @throws ParseException
     */
    public static boolean isEffectiveDate(String nowTimeStr, String startTimeStr, String endTimeStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN6);
        Date nowTime = simpleDateFormat.parse(nowTimeStr);
        Date startTime = simpleDateFormat.parse(startTimeStr);
        Date endTime = simpleDateFormat.parse(endTimeStr);

        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算两个时间段之间的日期列表，比如20211114至20211231之间的时间
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getBetweenTime(String startTime, String endTime) {
        List<String> betweenTime = new ArrayList<String>();
        try
        {
            Date sdate= new SimpleDateFormat(PATTERN4).parse(startTime);
            Date edate= new SimpleDateFormat(PATTERN4).parse(endTime);

            SimpleDateFormat outformat = new SimpleDateFormat(PATTERN4);

            Calendar sCalendar = Calendar.getInstance();
            sCalendar.setTime(sdate);
            int year = sCalendar.get(Calendar.YEAR);
            int month = sCalendar.get(Calendar.MONTH);
            int day = sCalendar.get(Calendar.DATE);
            sCalendar.set(year, month, day, 0, 0, 0);

            Calendar eCalendar = Calendar.getInstance();
            eCalendar.setTime(edate);
            year = eCalendar.get(Calendar.YEAR);
            month = eCalendar.get(Calendar.MONTH);
            day = eCalendar.get(Calendar.DATE);
            eCalendar.set(year, month, day, 0, 0, 0);

            while (sCalendar.before(eCalendar))
            {
                betweenTime.add(outformat.format(sCalendar.getTime()));
                sCalendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            betweenTime.add(outformat.format(eCalendar.getTime()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return betweenTime;
    }

    /**
     * 将时间戳 1613705920407转换成 1613705880000
     * @param seconds
     * @return
     * @throws ParseException
     */
    public static Long timeStampToMinutes(Long seconds) throws ParseException {
        String format = "yyyy-MM-dd HH:mm:00";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date = sdf.format(new Date(Long.parseLong(String.valueOf(seconds))));
        Long s = sdf.parse(date).getTime();
        return s;
    }

    /**
     * 存储SimpleDateFormat对应格式发的String类型
     */
    private static Map<String, SimpleDateFormat> map = new HashMap<String, SimpleDateFormat>();

    /**
     * 间隔天数
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return 相差天数
     */
    public static int betweenDays(Date beginDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN4);

        try {
            beginDate = sdf.parse(sdf.format(beginDate));
            endDate = sdf.parse(sdf.format(endDate));
        } catch (ParseException e) {
            logger.error("", e);
            return 0;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        long minTime = cal.getTimeInMillis();
        cal.setTime(endDate);
        long maxTime = cal.getTimeInMillis();
        long betweenDays = (maxTime - minTime) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     * 日期加N天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        Date newDate = cal.getTime();
        return newDate;
    }

    /**
     * 日期加N月
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        Date newDate = cal.getTime();
        return newDate;
    }

    /**
     * 日期加N分钟
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        Date newDate = cal.getTime();
        return newDate;
    }

    /**
     * 获取n分钟后的时间
     *
     * @param n 可以是负数，负数的话表示n分钟前
     * @return
     * @author liangyi
     */
    public static Timestamp getMinutes(int n) {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.MINUTE, n);
        Timestamp nMinutesLater = new Timestamp(calender.getTimeInMillis());

        return nMinutesLater;
    }

    /**
     * 将指定格式的日期字符串转成Timestamp
     *
     * @return
     * @author mayt
     */
    public static Timestamp getTimestamp(String time, String pattern) throws Exception {
        DateFormat format = new SimpleDateFormat(pattern);
        format.setLenient(false);
        Timestamp ts = new Timestamp(format.parse(time).getTime());
        return ts;

    }

    /**
     * 将指定格式的日期字符串转成Date
     *
     * @param dateStr
     * @param pattern
     * @return
     * @throws Exception
     * @author liangyi
     */
    public static Date getDate(String dateStr, String pattern) throws Exception {
        DateFormat format = new SimpleDateFormat(pattern);
        format.setLenient(false);
        Date date = new Date(format.parse(dateStr).getTime());
        return date;
    }

    /**
     * 获取系统当前时间
     *
     * @return
     * @author liangyi
     */
    public static Timestamp getSysDate() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        return time;
    }

    /**
     * 根据指定的Timestamp获取指定格式时间字符串
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String getDateString(Timestamp time, String pattern) {
        DateFormat dfmt = new SimpleDateFormat(pattern);
        Date date = time;
        return dfmt.format(date);
    }

    /**
     * 根据指定的Date获取指定格式时间字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getDateString(Date date, String pattern) {
        DateFormat dfmt = new SimpleDateFormat(pattern);
        return dfmt.format(date);
    }

    /**
     * 获取系统时间
     *
     * @param format 14位:yyyyMMddHHmmss,19位：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getSysdate(String format) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(now);
    }

    /**
     * 通过时间格式化字符串获取SimpleDateFormat
     *
     * @param parsePatterns 时间格式化
     * @return {@link SimpleDateFormat}
     */
    public static SimpleDateFormat getSimpleDateFormat(final String parsePatterns) {
        SimpleDateFormat dateFormat = null;
        if (map.containsKey(parsePatterns)) {
            dateFormat = map.get(parsePatterns);
        } else {
            dateFormat = new SimpleDateFormat(parsePatterns);
            map.put(parsePatterns, dateFormat);
        }
        return dateFormat;
    }

    /**
     * 格式化时间，返回格式化后的时间字符串
     * <p>
     * <pre>
     * 例子,如有一个Date = 2012-08-09:
     * DateUtils.format(date,"yyyy-MM-dd") = "2012-08-09"
     * DateUtils.format(date,"yyyy年MM月dd日") = "2012年08月09日"
     * DateUtils.format(date,"") = null
     * DateUtils.format(date,null) = null
     * </pre>
     *
     * @param date          时间
     * @param parsePatterns 格式化字符串
     * @return String
     */
    public static String format(final Date date, final String parsePatterns) {
        if (StringUtils.isEmpty(parsePatterns) || date == null) {
            return null;
        }
        return getSimpleDateFormat(parsePatterns).format(date);
    }

    /**
     * 简单转换日期类型到默认字符串格式 "yyyy-MM-dd HH:mm:ss"
     *
     * @param date 日期
     * @return String 格式化好的日期字符串 "yyyy-MM-dd HH:mm:ss"
     */
    public static String format(final Date date) {
        return format(date, PATTERN);
    }

    /**
     * 根据周期获取两个时间段内的天数
     *
     * @param date
     * @param py
     * @param cycle
     * @return
     */
    public static int getDaysByRule(Date date, int py, int cycle) {

        Date newDate = new Date();
        //周
        if (cycle == 2) {

            newDate = addDays(date, 7 * py);
        }

        //月
        else if (cycle == 3) {

            newDate = addMonth(date, py);
        } else {
            return py;
        }

        return betweenDays(date, newDate);
    }


    /**
     * 判断某个时间time1是否在另一个时间time2之前
     *
     * @param time1 第一个时间
     * @param time2 第二个时间
     * @return 时间time1是否在另一个时间time2之前
     */
    public static boolean beforeTime(final Date time1, final Date time2) {
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time1);

        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(time2);

        return calendar1.before(calendar2);
    }

    /**
     * 返回下个月最后一秒的时间
     *
     * @return
     * @throws Exception
     */
    public static Date getNextMonthLastTime(int end) throws Exception {

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, end + 1);
        calendar.set(Calendar.DATE, 0);

        Date date1 = calendar.getTime();

        String yyyymmdd = format(date1, PATTERN4);
        yyyymmdd += " 23:59:59";

        date1 = getDate(yyyymmdd, PATTERN);

        return date1;

    }


    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 获取季度日期起始时间
     * @param date
     * @return
     */
    public static String[] getSeasonDate(Date date) {
        String[] season = new String[2];
        int nSeason = getSeason(date);
        if (nSeason == 1) {// 第一季度

            season[0] = getSysdate("yyyy") + "01";
            season[1] = getSysdate("yyyy") + "03";

        } else if (nSeason == 2) {// 第二季度

            season[0] = getSysdate("yyyy") + "04";
            season[1] = getSysdate("yyyy") + "06";

        } else if (nSeason == 3) {// 第三季度

            season[0] = getSysdate("yyyy") + "07";
            season[1] = getSysdate("yyyy") + "09";

        } else if (nSeason == 4) {// 第四季度

            season[0] = getSysdate("yyyy") + "10";
            season[1] = getSysdate("yyyy") + "12";

        }
        return season;
    }

    /**
     * 获取当前天是这个月的第几天
     * @return
     */
    public static int getDayOfMonth() {
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取上一个月月份
     *
     * @return
     */
    public static String getBeforeMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        String time = format.format(calendar.getTime());
        return time;
    }

    /**
     * 获得当月月底最后一秒钟的时间（格式：yyyy-MM-dd HH:mm:ss）
     *
     * @return
     * @throws Exception
     */
    public static Date getLastSecondOfCurrentMonth()
            throws Exception {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
        //设置每天的最大小时
        now.set(Calendar.HOUR_OF_DAY, now.getActualMaximum(Calendar.HOUR_OF_DAY));
        //设置每小时最大分钟
        now.set(Calendar.MINUTE, now.getActualMaximum(Calendar.MINUTE));
        //设置每分钟最大秒
        now.set(Calendar.SECOND, now.getActualMaximum(Calendar.SECOND));
        return now.getTime();
    }

    /**
     * 根据身份证获取年龄
     * @param idCard
     * @return
     */
    public static int getAge(String idCard) {
        int idxSexStart = 16;
        int birthYearSpan = 4;
        //如果是15位的证件号码
        if (idCard.length() == 15) {
            idxSexStart = 14;
            birthYearSpan = 2;
        }

        //出生日期
        String year = (birthYearSpan == 2 ? "19" : "") + idCard.substring(6, 6 + birthYearSpan);
        String month = idCard.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);
        String day = idCard.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);
//        String birthday = year + '-' + month + '-' + day;

        //年龄
        Calendar certificateCal = Calendar.getInstance();
        Calendar currentTimeCal = Calendar.getInstance();
        certificateCal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
        int yearAge = (currentTimeCal.get(Calendar.YEAR)) - (certificateCal.get(Calendar.YEAR));
        certificateCal.set(currentTimeCal.get(Calendar.YEAR), Integer.parseInt(month) - 1, Integer.parseInt(day));
        int monthFloor = (currentTimeCal.before(certificateCal) ? 1 : 0);

        int age = yearAge - monthFloor;
        return age;
    }

    /**
     * 根据身份证获取性别
     * @param idCard
     * @return
     */
    public static String getSex(String idCard) {
        int idxSexStart = 16;
        int birthYearSpan = 4;
        //如果是15位的证件号码
        if (idCard.length() == 15) {
            idxSexStart = 14;
            birthYearSpan = 2;
        }

        //性别
        String idxSexStr = idCard.substring(idxSexStart, idxSexStart + 1);
        int idxSex = Integer.parseInt(idxSexStr) % 2;
        String sex = (idxSex == 1) ? "0" : "1";

        return sex;
    }

    /**
     * 根据身份证获取生日
     * @param idCard
     * @return
     */
    public static String getBirthDay(String idCard) {
        int idxSexStart = 16;
        int birthYearSpan = 4;
        //如果是15位的证件号码
        if (idCard.length() == 15) {
            idxSexStart = 14;
            birthYearSpan = 2;
        }

        //出生日期
        String year = (birthYearSpan == 2 ? "19" : "") + idCard.substring(6, 6 + birthYearSpan);
        String month = idCard.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);
        String day = idCard.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);
        String birthday = year + '-' + month + '-' + day;

        return birthday;
    }

    /**
     * 今天是这周第几天
     * @return
     * @throws Exception
     */
    public static int dayForWeek() throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 获取时间（加上23:59:59）
     *
     * @param date
     * @return
     */
    public static Date getDateOfEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        Date result = new Date(calendar.getTimeInMillis());
        return result;
    }

    /**
     * 两个日期差了多少月
     * @param start
     * @param end
     * @return
     */
    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }

    /**
     * 下个月开始时间
     * @param end
     * @return
     * @throws Exception
     */
    public static Date getNextMonthStartTime(int end) throws Exception {

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, end);
        calendar.set(Calendar.DATE, 1);

        Date date1 = calendar.getTime();

        String yyyymmdd = format(date1, PATTERN4);
        yyyymmdd += " 00:00:01";

        date1 = getDate(yyyymmdd, PATTERN);

        return date1;

    }


    /**
     * 获取下个月开始时间
     * @return
     * @throws Exception
     */
    public static Date getNextMonthBegin() throws Exception {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);

        Date date1 = calendar.getTime();

        String yyyymmdd = format(date1, PATTERN4);
        yyyymmdd += " 00:00:00";

        date1 = getDate(yyyymmdd, PATTERN);
        return date1;
    }



    /**
     * 获取两个日期的时间戳差
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getTimeBetween(Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HHMMSS_S);

        try {
            startDate = sdf.parse(sdf.format(startDate));
            endDate = sdf.parse(sdf.format(endDate));
        } catch (ParseException e) {
            logger.error("", e);
            return 0L;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long minTime = cal.getTimeInMillis();
        cal.setTime(endDate);
        long maxTime = cal.getTimeInMillis();
        long betweenTimes = maxTime - minTime;

        return betweenTimes;
    }

    /**
     * 根据毫秒数转换为时间字符串： xxx时xxx分xxx秒
     * 毫秒数不是时间，比如： 1000 ，转换成 1秒
     *
     * @param millisecond
     * @return
     */
    public static String getTimeLength(Long millisecond) {
        int iHour = (int) Math.floor(millisecond / (1000 * 60 * 60));
        int iMiniute = (int) Math.floor((millisecond - iHour * 1000 * 60 * 60) / (1000 * 60));
        int iSecond = (int) Math.floor((millisecond / 1000) % 60);

        StringBuffer buffer = new StringBuffer();

        if (0 < iHour) {
            buffer.append(iHour).append("时");
        }

        if (0 < iMiniute) {
            buffer.append(iMiniute).append("分");
        }

        if (0 < iSecond) {
            buffer.append(iSecond).append("秒");
        }

        return buffer.toString();
    }


    /**
     * 时区转换
     *
     * @param date
     * @param pattern
     * @param timeZone
     * @return
     */
    public static String simpleConvert(Long date, String pattern, String timeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return simpleDateFormat.format(date);
    }

    /**
     * 处理时间戳数据
     *
     * @param time 时间
     * @return 时间戳
     */
    public static Long dealTimeStampByString(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(PATTERN1);
            return sdf.parse(time).getTime();
        } catch (Exception e) {
            logger.error("时间戳转换失败");
        }
        return null;
    }
    /**
     * 将时间戳转化为ms的日期
     * @param time
     * @return java.lang.String
     */
    public static String getTimeByStamp(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN1);
        return sdf.format(new Date(time));
    }

    /**
     * 将时间戳转化为ms的日期
     * @param time
     * @return java.lang.String
     */
    public static Long getStampByString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN1);
        Date ret = null;
        try {
            ret =  sdf.parse(time);
        } catch (ParseException e) {
            logger.error("日期转换错误"+e.getMessage());
        }
        return ret.getTime();
    }

    /**
     * 时间长度不足的精度补足
     *
     * @param time 时间
     * @return 数据
     */
    public static String dealTimeStamp(String time) {
        char[] evtTimeArray = time.toCharArray();
        if (evtTimeArray.length < DATE_LENGTH) {
            char[] tempArr = new char[DATE_LENGTH];
            for (int i = 0; i < tempArr.length; i++) {
                if (i <= evtTimeArray.length - 1) {
                    tempArr[i] = evtTimeArray[i];
                } else {
                    tempArr[i] = 0;
                }
            }
            return String.copyValueOf(tempArr);
        }
        return time;
    }

    /**
     * 时间格式转换
     *
     * @param date
     * @return
     */
    public static String dateTransformer(String date) {
        if (date == null) {
            return "";
        } else {
            StringBuffer buffer = new StringBuffer();
            char[] charArray = date.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                if (i == 4 || i == 6) {
                    buffer.append("-");
                } else if (i == 8) {
                    buffer.append(" ");
                } else if (i == 10 || i == 12) {
                    buffer.append(":");
                } else if (i == 14) {
                    buffer.append(".");
                }
                buffer.append(charArray[i]);
            }
            return buffer.toString();
        }
    }

    /**
     * 格式化日期
     * @param seconds
     * @return
     */
    public static String timeStampGangDate(String seconds) {
        String format = PATTERN4;
        if(seconds == null || seconds.isEmpty() || "null".equals(seconds)){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    /**
     * date转String
     *
     * @param date    date
     * @param pattern 转换格式
     * @return string类型日期
     */
    public static String dealDateToString(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * timestamp转date
     *
     * @param timeStamp timeStamp
     * @param pattern   转换格式
     * @return string类型日期
     */
    public static String dealTimeStampToString(Long timeStamp, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(timeStamp);
    }

    /**
     * timestamp转date
     *
     * @return string类型日期
     */
    public static String currentDate(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date());
    }

    /**
     * 2021-03-23 10:18:10转2021-03-23
     *
     * @return string类型日期
     */
    public static String getDateBySdf(String oldPattern, String newPattern, String date) {
        try {
            SimpleDateFormat oldSdf = new SimpleDateFormat(oldPattern);
            Date lostDate = oldSdf.parse(date);
            SimpleDateFormat newSdf = new SimpleDateFormat(newPattern);
            return newSdf.format(lostDate);
        } catch (ParseException e) {
            logger.error("时间转换失败" + e.getMessage());
        }
        return null;
    }

    /**
     * 两个时间差
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int compareTwoTime(Date time1, Date time2) {
        int flagValue = 0;
        try {
            long millisecond = time1.getTime() - time2.getTime();
            if (millisecond > 0) {
                flagValue = 1;
            } else if (millisecond < 0) {
                flagValue = -1;
            } else if (millisecond == 0) {
                flagValue = 0;
            }
        } catch (Exception e) {
            logger.error("时间比较失败" + e.getMessage());
        }
        return (flagValue);
    }

    /**
     * 两个时间差
     *
     * @param dateString1
     * @param dateString2
     * @param pattern
     * @return
     */
    public static long getTimeDiff(String dateString1, String dateString2, String pattern) {
        long millisecond = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date1 = sdf.parse(dateString1);
            Date date2 = sdf.parse(dateString2);
            return getTimeDiff(date1, date2);
        } catch (Exception e) {
            logger.error("时间比较失败" + e.getMessage());
        }
        return millisecond;
    }

    /**
     * 计算两个日期时间差
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getTimeDiff(Date date1, Date date2) {
        return date1.getTime() - date2.getTime();
    }


    /**
     * 当月第一天
     *
     * @return
     */
    public static Date getCurrentMonthBegin() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        gregorianCalendar.setTime(new Date(System.currentTimeMillis()));
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        gregorianCalendar.set(Calendar.MINUTE, 0);
        //将秒至0
        gregorianCalendar.set(Calendar.SECOND, 0);
        //将毫秒至0
        gregorianCalendar.set(Calendar.MILLISECOND, 0);

        return gregorianCalendar.getTime();
    }

    /**
     * 获取当天开始时间（去除时分秒）
     *
     * @return
     */
    public static Date getDateOfBegin() {
        return getDateOfBegin(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取当天凌晨时间
     *
     * @param date
     * @return
     */
    public static Date getDateOfBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);

        Date result = new Date(calendar.getTimeInMillis());
        return result;
    }

    /**
     * 根据毫秒数转换为时间字符串： xxx时xxx分xxx秒
     * 毫秒数不是时间，比如： 1000 ，转换成 1秒
     *
     * @param millisecond
     * @return
     */
    public static String timeStampToString(Long millisecond) {
        long iHour = (int) Math.floor(millisecond / (1000 * 60 * 60));
        long iMiniute = (int) Math.floor((millisecond - iHour * 1000 * 60 * 60) / (1000 * 60));
        long iSecond = (int) Math.floor((millisecond / 1000) % 60);

        StringBuffer buffer = new StringBuffer();
        if (0 < iHour) {
            buffer.append(iHour).append("时");
        }

        if (0 < iMiniute) {
            buffer.append(iMiniute).append("分");
        }

        if (0 < iSecond) {
            buffer.append(iSecond).append("秒");
        }

        return buffer.toString();
    }


    /**
     * 获取昨天0点时间
     *
     * @return string
     */
    public static String getYesterdayTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)-1,
                0,0,0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = dateFormat.format(calendar.getTime());
        return createTime;
    }

    /**
     * 获取当天0点时间
     *
     * @return string
     */
    public static String getZeroTime() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = dateFormat.format(zero);
        return createTime;
    }

    /**
     * 获取前一整小时
     *
     * @return
     */
    public static String getPreHourTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY) - 1,0,0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime=calendar.getTime();
        String createTime = dateFormat.format(dateTime);
        return createTime;
    }

    /**
     * 获取当前整小时
     *
     * @return
     */
    public static String getHourTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = dateFormat.format(zero);
        return createTime;
    }

    /**
     * 获取一个月前的时间
     * @return
     */
    public static StringBuffer getOneMonthTime() {
        StringBuffer dateTime = new StringBuffer();
        //Java获取当前日期的前一个月,前一天时间
        Calendar calendar = Calendar.getInstance();
        //得到前一天
        calendar.add(Calendar.DATE, -1);
        //得到前一个月
        calendar.add(Calendar.MONTH, -1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        return dateTime.append(year).append("-").append(month).append("-").append(date);
    }

    /**
     * 时间戳-->yyyyMMdd
     * @param seconds
     * @return
     */
    public static String timeStampNoGangDate(String seconds) {
        String format = PATTERN3;
        if (seconds == null || seconds.isEmpty() || "null".equals(seconds)) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyyMMdd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    /**
     * 毫秒转成分秒 格式：3分01秒
     *
     * @param time
     * @return
     */
    public static String formatTime(long time) {
        long minute = time / (1000 * 60);
        long second = (time - minute * (1000 * 60)) / (1000);
        return second < 10 ? String.format("%s分0%s秒", minute, second) : String.format("%s分%s秒", minute, second);
    }

    /**
     * java时间字符串去空格、冒号和横杠
     * 将 1609139732636 转换成  20201228151532636
     * @param seconds
     * @return
     */
    public static String timeStampReplace(String seconds) {
        String format = PATTERN1;
        if(seconds == null || seconds.isEmpty() || "null".equals(seconds)){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date = sdf.format(new Date(Long.parseLong(seconds)));
        String result = date.replaceAll("[[\\s-:.punct:]]", "");
        return result;
    }
}
