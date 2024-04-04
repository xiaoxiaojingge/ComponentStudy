package com.itjing.springboot.test;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itjing.springboot.entity.PersonInfo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 生成MyBatis批量插入时，无数据插入，有数据更新的on duplicate key update语句
 *
 * @author lijing
 * @date 2024-04-04
 */
public class GenerateCode {

    public static void main(String[] args) {

        Class<?> entityClass = PersonInfo.class;
        // 获取表名
        TableName tableName = entityClass.getAnnotation(TableName.class);
        // 获取属性
        Field[] declaredFields = entityClass.getDeclaredFields();
        List<String> dbFieldNameList = new CopyOnWriteArrayList<>();
        // 按属性设置sql字段
        Arrays.stream(declaredFields).forEach(field -> {
            TableField tableField = field.getAnnotation(TableField.class);
            if (Objects.nonNull(tableField)) {
                dbFieldNameList.add(tableField.value());
            }
        });
        StringBuilder updateSql = new StringBuilder();
        updateSql.append("on duplicate key update").append("\n");
        // String formatStr = "`{}` = if(isnull(values(`{}`)), `{}`, values(`{}`)),";
        String formatStr = "`{}` = values(`{}`),";
        dbFieldNameList.stream().forEachOrdered(fieldName -> {
            updateSql.append(StrUtil.format(formatStr, fieldName, fieldName, fieldName, fieldName)).append("\n");
        });
        // 去除最后一个逗号
        updateSql.delete(updateSql.length() - 2, updateSql.length());
        System.out.println(updateSql);
    }
}
