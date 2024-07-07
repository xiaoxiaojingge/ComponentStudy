package org.itjing.lambda;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author lijing
 * @date 2021年12月09日 16:39
 * @description
 */
public class LambdaDemo16 {

	public static void main(String[] args) {
		// 使用Lambda表达式获取Person类对象
		Supplier<Person> supplier = () -> new Person();

		Person person = supplier.get();
		System.out.println("person = " + person);

		supplier = Person::new;
		person = supplier.get();
		System.out.println("person = " + person);

		// BiFunction 接收两个参数，返回一个结果
		BiFunction<String, Integer, Person> function = (name, age) -> new Person(name, age);
		person = function.apply("李四", 20);
		System.out.println("person = " + person);

		function = Person::new;
		person = function.apply("张三", 25);
		System.out.println("person = " + person);
	}

}

class Person {

	private String name;

	private Integer age;

	public Person() {
	}

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
	}

}
