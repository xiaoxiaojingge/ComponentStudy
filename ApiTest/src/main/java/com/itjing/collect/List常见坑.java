package com.itjing.collect;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author: lijing
 * @Date: 2021年05月17日 10:51
 * @Description: List 可谓是我们经常使用的集合类之一，几乎所有业务代码都离不开 List。 既然天天在用，那就没准就会踩中这几个 List 常见坑。
 */
public class List常见坑 {

	public static void main(String[] args) {

		testArraysAsList2();
	}

	/**
	 * Arrays.asList 坑
	 */
	public static void testArraysAsList1() {
		List<String> list = Arrays.asList("lijing", "jingge");
		list.add("haha"); // 报 java.lang.UnsupportedOperationException 异常
		// 这是因为这个Arrays#asList 返回的 ArrayList 仅仅只是 Arrays 一个内部类，并非真正的 java.util.ArrayList。
		// 由于这个内部类继承了AbstractList，add/remove 等方法实际都来自 AbstractList。
		// 而 AbstractList 方法恰恰会抛出 UnsupportedOperationException。
	}

	/**
	 * Arrays#asList 返回的 ArrayList 除了不支持增删操作这个坑以外，还存在另外一个大坑，改动内部元素将会同步影响原数组。
	 */
	public static void testArraysAsList2() {
		String[] arrays = { "1", "2", "3" };
		List<String> list = Arrays.asList(arrays);
		list.set(0, "modify_1");
		arrays[1] = "modify_2";
		System.out.println("arrays:" + Arrays.toString(arrays)); // arrays:[modify_1,
																	// modify_2, 3]
		System.out.println("list" + list); // list[modify_1, modify_2, 3]

		/*-------------解决------------------*/
		String[] arraysnew = { "1", "2", "3" };
		// Lists 是 Guava 提供的
		List<String> arrayList = Lists.newArrayList(arraysnew);
		System.out.println("arrayList" + arrayList);
	}

}
