package com.itjing.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijing
 * @date 2021年11月08日 16:59
 * @description 集合比较
 */
public class CompareUtil {

	/**
	 * 比较2个List，得出应该删除的 delList，和新增的addList T必须重写 hashCode和equals方法
	 * @param dbEntityList
	 * @param newDtoList
	 * @param addList
	 * @param delList
	 */
	public static <T> void filterList(List<T> dbEntityList, List<T> newDtoList, List<T> addList, List<T> delList) {
		if (dbEntityList == null) {
			dbEntityList = new ArrayList<T>();
		}

		if (newDtoList == null) {
			newDtoList = new ArrayList<T>();
		}

		// 复制一个原始数组,将作为交集
		List<T> intersecList = new ArrayList<>(newDtoList);
		// 设新集A(newDtoList)，旧集 B(dbEntityList)，求出交集C(intersecList)
		intersecList.retainAll(dbEntityList);

		// A -C ,即addList
		newDtoList.removeAll(intersecList);
		addList.addAll(newDtoList);

		// B -C,即delList
		dbEntityList.removeAll(intersecList);
		delList.addAll(dbEntityList);
	}

	/**
	 * 比较2个List，得出应该删除的 delList，和新增的addList,新旧交集remainList。 T必须重写 hashCode和equals方法
	 * @param dbEntityList
	 * @param newDtoList
	 * @param addList
	 * @param delList
	 */

	public static <T> void filterList(List<T> dbEntityList, List<T> newDtoList, List<T> addList, List<T> delList,
			List<T> remainList) {
		if (dbEntityList == null) {
			dbEntityList = new ArrayList<T>();
		}

		if (newDtoList == null) {
			newDtoList = new ArrayList<T>();
		}

		// 复制一个原始数组,将作为交集
		List<T> intersecList = new ArrayList<>(newDtoList);
		// 设新集A(newDtoList)，旧集 B(dbEntityList)，求出交集C(intersecList)
		// retainAll(Collection<?> c) 仅保留此列表中包含在指定集合中的元素。
		intersecList.retainAll(dbEntityList);

		// A -C ,即addList
		addList.addAll(newDtoList);
		addList.removeAll(intersecList);

		// B -C,即delList
		delList.addAll(dbEntityList);
		delList.removeAll(intersecList);

		remainList.addAll(intersecList);
	}

}
