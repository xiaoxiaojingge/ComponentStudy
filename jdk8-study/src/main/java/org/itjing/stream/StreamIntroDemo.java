package org.itjing.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lijing
 * @date 2021年12月09日 16:55
 * @description
 */
public class StreamIntroDemo {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰");

		// 拿到所有姓张的
		List<String> zhangList = new ArrayList<>();
		for (String s : list) {
			if (s.startsWith("张")) {
				zhangList.add(s);
			}
		}

		// 拿到名字长度=3的
		List<String> threeList = new ArrayList<>();
		for (String s : zhangList) {
			if (s.length() == 3) {
				threeList.add(s);
			}
		}

		// 对结果进行打印
		for (String name : threeList) {
			System.out.println(name);
		}
	}

}
