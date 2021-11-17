package com.itjing.utils;

import com.itjing.annotation.SensitiveInfo;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * @author lijing
 * @date 2021年11月17日 10:58
 * @description 数据脱敏工具类
 */
public class SensitiveUtils {

    /**
     * 脱敏枚举类
     */
    public enum sensitiveType {
        name, idCardNum, mobilePhone, bankCard, cnapsCode, fixedPhone, defaultType;
    }


    /**
     * 用户名：只显示第一个，其他隐藏为星号<例子：李**>
     *
     * @param ss
     * @return
     */
    public static String name(String ss) {
        if (StringUtils.isBlank(ss)) {
            return "";
        }
        int length = StringUtils.length(ss);
        String newStr = StringUtils.left(ss, 1);
        return StringUtils.rightPad(newStr, length, "*");
    }


    /**
     * 默认全部字符串转换为* <例子：1234 ----> **** >
     *
     * @param strDefault
     * @return
     */
    public static String defaultType(String strDefault) {
        return strDefault.replaceAll("(.)", "*");
    }

    /**
     * 身份证号码：显示最后四位，其他隐藏。共计18位或者15位。<例子：*************5762>
     *
     * @param id
     * @return
     */
    public static String idCardNum(String id) {
        if (StringUtils.isBlank(id)) {
            return "";
        }
        String num = StringUtils.right(id, 4);
        return StringUtils.leftPad(num, StringUtils.length(id), "*");
    }


    /**
     * [手机号码] 前三位，后四位，其他隐藏 <例子:138******1234>
     *
     * @param num
     * @return
     */
    public static String mobilePhone(String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.left(num, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"), "***"));
    }

    /**
     * [固定电话] 后四位，其他隐藏<例子：****1234>
     *
     * @param num
     * @return
     */
    public static String fixedPhone(String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        int length = StringUtils.length(num);
        //从右边截取制定长度的字符串
        String right = StringUtils.right(num, 4);
        //如果参数1长度小于参数2，那么全部用*号替换
        return StringUtils.leftPad(right, length, "*");
    }

    /**
     * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:6222600**********1234>
     *
     * @param cardNum
     * @return
     */
    public static String bankCard(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        return StringUtils.left(cardNum, 6).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
    }

    /**
     * [公司开户银行联号] 公司开户银行联行号,显示前两位，其他用星号隐藏，每位1个星号<例子:12********>
     *
     * @param code
     * @return
     */
    public static String cnapsCode(String code) {
        if (StringUtils.isBlank(code)) {
            return "";
        }
        return StringUtils.rightPad(StringUtils.left(code, 2), StringUtils.length(code), "*");
    }


    /**
     * 获取脱敏对象
     *
     * @param object
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    public static String getJavaBean(Object object) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

        Class<? extends Object> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String str = "";
        for (Field field : fields) {
            field.setAccessible(true);
            //获取字段上的注解
            SensitiveInfo annotation = field.getAnnotation(SensitiveInfo.class);
            //获取字段的属性名称
            String fieldName = field.getName();
            //获取字段的值并转换为String类型
            String value = fieldType(object, field, fieldName);
            //判断是否存在注解
            if (null != annotation) {
                //获取注解的具体类型
                sensitiveType Type = annotation.Type();
                //匹配注解类型
                switch (Type) {
                    case name: {
                        //替换为*号，重新输出
                        String valueStr = SensitiveUtils.name(value);
                        str += fieldName + ":" + valueStr + ",";
                        break;
                    }
                    case idCardNum: {
                        //替换为*号，重新输出
                        String valueStr = SensitiveUtils.idCardNum(value);
                        str += fieldName + ":" + valueStr + ",";
                        break;
                    }
                    case mobilePhone: {
                        //替换为*号，重新输出
                        String valueStr = SensitiveUtils.mobilePhone(value);
                        str += fieldName + ":" + valueStr + ",";
                        break;
                    }
                    case fixedPhone: {
                        //替换为*号，重新输出
                        String valueStr = SensitiveUtils.fixedPhone(value);
                        str += fieldName + ":" + valueStr + ",";
                        break;
                    }
                    default: {
                        String valueStr = SensitiveUtils.defaultType(value);
                        str += fieldName + ":" + valueStr + ",";
                        break;
                    }
                }
            }
            ;
            if (null == annotation) {
                str += fieldName + ":" + value + ",";
            }
        }
        return str;
    }

    private static String fieldType(Object object, Field field, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        //获取字段的类型
        Class<?> fieldClazz = field.getType();
        String value = "";
        //通过属性名获取私有属性字段的属性值
        Field f1 = object.getClass().getDeclaredField(fieldName);
        f1.setAccessible(true);
        //判断字段具体类型，最终将字段值全部转换为String类型。
        if (fieldClazz.equals(String.class)) {
            value = (String) f1.get(object);
        }
        if (fieldClazz.equals(Integer.class)) {
            Integer valueInteger = (Integer) f1.get(object);
            value = valueInteger.toString();
        }
        if (fieldClazz.equals(Long.class)) {
            Long valueLong = (Long) f1.get(object);
            value = valueLong.toString();
        }
        if (fieldClazz.equals(int.class)) {
            int valueLong = (int) f1.get(object);
            value = String.valueOf(valueLong);
        }
        return value;
    }
}
