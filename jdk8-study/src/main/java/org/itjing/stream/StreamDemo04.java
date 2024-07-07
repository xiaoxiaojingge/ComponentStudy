package org.itjing.stream;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lijing
 * @date 2021年12月10日 11:29
 * @description
 */
public class StreamDemo04 {

	public static void main(String[] args) {
		Stream<Person> stream = Stream.of(new Person("鞠婧祎", 18, 100), new Person("赵丽颖", 18, 95),
				new Person("杨颖", 19, 86), new Person("迪丽热巴", 19, 99), new Person("柳岩", 20, 77));

		// 根据年龄分组
		/*
		 * Map<Integer, List<Person>> map =
		 * stream.collect(Collectors.groupingBy(Person::getAge)); map.forEach((k, v) -> {
		 * System.out.println("年龄 = " + k); List<Person> personList = v;
		 * System.out.println("person = " + personList); });
		 */

		/*
		 * Stream<Person> stream = Stream.of( new Person("鞠婧祎", 18, 100, "女"), new
		 * Person("赵丽颖", 18, 95, "女"), new Person("杨颖", 19, 86, "女"), new Person("迪丽热巴",
		 * 19, 99, "女"), new Person("柳岩", 20, 77, "女"), new Person("胡歌", 20, 100, "男"));
		 *
		 * // 先根据性别分组，性别相同再按照年龄分组 Map<String, Map<Integer, List<Person>>> map =
		 * stream.collect(Collectors.groupingBy(Person::getSex,
		 * Collectors.groupingBy(Person::getAge))); map.forEach((sex, v1) -> {
		 * System.out.println("sex = " + sex); Map<Integer, List<Person>> m = v1;
		 * m.forEach((age, v2) -> { System.out.print(age + "---->");
		 * System.out.println("personList = " + v2); }); });
		 */

		/*
		 * Map<Boolean, List<Person>> map = stream.collect(Collectors.partitioningBy(p ->
		 * p.getScore() > 90)); map.forEach((k,v)->{ System.out.println("k = " + k);
		 * System.out.println("v = " + v); });
		 */

		String str = stream.map(Person::getName).collect(Collectors.joining("><", "(#^.^#)", "^_^"));
		System.out.println("str = " + str);

	}

}
