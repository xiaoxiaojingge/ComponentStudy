package com.itjing.annotation.jdk.db.provider.parser;

import com.itjing.annotation.jdk.db.provider.Column;
import com.itjing.annotation.jdk.db.provider.Table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: lijing
 * @Date: 2021年06月02日 17:01
 * 第四步：在com.itjing.annotation.jdk.db.provider.parser包中创建一个AnnotationParser类，
 * AnnotationParser 类是整个框架的核心，它负责解析标注在实体类上的注解，并且将对应的实体类及其字段信息映射到对应的数据表和字段上
 */
public class AnnotationParser {

	/**
	 * 通过注解来组装查询条件，生成查询语句
	 * @param obj
	 * @return
	 */
	public static String assembleSqlFromObj(Object obj) {
		Table table = obj.getClass().getAnnotation(Table.class);
		StringBuffer sbSql = new StringBuffer();
		String tableName = table.value();
		sbSql.append("select * from " + tableName + " where 1=1 ");
		Field[] fileds = obj.getClass().getDeclaredFields();
		for (Field f : fileds) {
			String fieldName = f.getName();
			String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				Column column = f.getAnnotation(Column.class);
				if (column != null) {
					Method method = obj.getClass().getMethod(methodName);
					Object v = method.invoke(obj);
					if (v != null) {
						if (v instanceof String) {
							String value = v.toString().trim();
							// 判断参数是不是 in 类型参数 1,2,3
							if (value.contains(",")) {
								// 去掉value中的,
								String sqlParams = value.replace(",", "").trim();
								// value中都是纯数字
								if (isNum(sqlParams)) {
									sbSql.append(" and " + column.value() + " in (" + value + ") ");
								}
								else {
									String[] split = value.split(",");
									// 将value重置为空
									value = "";
									for (int i = 0; i < split.length - 1; i++) {
										value += "'" + split[i] + "',";
									}
									value += "'" + split[split.length - 1] + "'";
									sbSql.append(" and " + column.value() + " in (" + value + ") ");
								}
							}
							else {
								if (value != null && value.length() > 0) {
									sbSql.append(" and " + column.value() + " like '%" + value + "%' ");
								}
							}
						}
						else {
							sbSql.append(" and " + column.value() + "=" + v.toString() + " ");
						}
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sbSql.toString();
	}

	/**
	 * 检查给定的值是不是 id 类型 1.检查字段名称 2.检查字段值
	 * @param target
	 * @return
	 */
	public static boolean isNum(String target) {
		boolean isNum = false;
		if (target.toLowerCase().contains("id")) {
			isNum = true;
		}
		if (target.matches("\\d+")) {
			isNum = true;
		}
		return isNum;
	}

}
