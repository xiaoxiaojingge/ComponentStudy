package com.itjing.study.util;

import com.google.common.collect.Lists;
import com.itjing.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author lijing
 * @date 2022年06月02日 19:28
 * @description 开发效率工具类测试
 */
@Slf4j
public class DevEfficiencyUtilTest {

	/**
	 * 测试java.util.Collections Collections方法有很多，这里只测试部分
	 */
	@Test
	public void testJavaUtilCollections() {
		// 测试排序
		System.out.println("----------------测试排序----------------");
		List<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(1);
		list.add(3);
		Collections.sort(list); // 升序
		System.out.println(list);
		Collections.reverse(list); // 降序
		System.out.println(list);

		// 获取最大或最小值
		System.out.println("----------------获取最大或最小值----------------");
		Integer max = Collections.max(list);// 获取最大值
		Integer min = Collections.min(list);// 获取最小值
		System.out.println("最大值：" + max);
		System.out.println("最小值：" + min);

		// 转换线程安全集合
		System.out.println("----------------转换线程安全集合----------------");
		List<Integer> integers = Collections.synchronizedList(list);
		System.out.println(integers);

		// 返回空集合
		System.out.println("----------------返回空集合----------------");
		List<Integer> emptyList = Collections.emptyList();
		System.out.println(emptyList);

		// 二分查找
		System.out.println("----------------二分查找----------------");
		Collections.sort(list);
		// 二分查找前提：集合必须是有序的，否则结果可能不符合预期
		int i = Collections.binarySearch(list, 3);
		System.out.println(i);

		// 转换成不可修改集合
		System.out.println("----------------转换成不可修改集合----------------");
		List<Integer> unmodifiableList = Collections.unmodifiableList(list);
		unmodifiableList.add(4);
		System.out.println(unmodifiableList);
	}

	/**
	 * 测试Apache包下的utils，这里只演示了个别方法，其他方法可以参考源码
	 */
	@Test
	public void testApacheUtils() {
		// 集合判空
		System.out.println("----------------集合判空----------------");
		List<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(1);
		list.add(3);
		if (CollectionUtils.isEmpty(list)) {
			System.out.println("集合为空");
		}
		if (CollectionUtils.isNotEmpty(list)) {
			System.out.println("集合不为空");
		}

		// 对两个集合进行操作
		System.out.println("----------------对两个集合进行操作----------------");
		List<Integer> list2 = new ArrayList<>();
		list2.add(2);
		list2.add(4);
		// 获取并集
		Collection<Integer> unionList = CollectionUtils.union(list, list2);
		System.out.println(unionList);

		// 获取交集
		Collection<Integer> intersectionList = CollectionUtils.intersection(list, list2);
		System.out.println(intersectionList);

		// 获取交集的补集
		Collection<Integer> disjunctionList = CollectionUtils.disjunction(list, list2);
		System.out.println(disjunctionList);

		// 获取差集
		Collection<Integer> subtractList = CollectionUtils.subtract(list, list2);
		System.out.println(subtractList);
	}

	/**
	 * 测试guava的工具类 guava方法有很多，这里只测试部分
	 */
	@Test
	public void testGuavaUtils() {
		// 创建空集合
		System.out.println("----------------创建空集合----------------");
		List<Integer> list1 = Lists.newArrayList();
		System.out.println(list1);

		// 快速初始化集合
		System.out.println("----------------快速初始化集合----------------");
		List<Integer> list2 = Lists.newArrayList(1, 2, 3);
		System.out.println(list2);

		// 笛卡尔积
		System.out.println("----------------笛卡尔积----------------");
		List<Integer> list3 = Lists.newArrayList(1, 2, 3);
		List<Integer> list4 = Lists.newArrayList(4, 5);
		List<List<Integer>> productList = Lists.cartesianProduct(list3, list4);
		System.out.println(productList);

		// 分页
		System.out.println("----------------分页----------------");
		List<Integer> list5 = Lists.newArrayList(1, 2, 3, 4, 5);
		int total = list5.size();
		int currentPage = 3;
		int pageSize = 3;
		List<List<Integer>> partitionList = Lists.partition(list5, pageSize);
		System.out.println(partitionList);
		if (currentPage > partitionList.size()) {
			System.out.println(Lists.newArrayList());
		}
		else {
			System.out.println(partitionList.get(currentPage - 1));
		}

		// 流处理
		System.out.println("----------------流处理----------------");
		List<String> list6 = Lists.newArrayList("a", "b", "c");
		List<String> transformList = Lists.transform(list6, x -> x.toUpperCase());
		System.out.println(transformList);

		// 倒序
		System.out.println("----------------倒序----------------");
		List<Integer> list7 = Lists.newArrayList(3, 1, 2);
		List<Integer> reverseList = Lists.reverse(list7);
		System.out.println(reverseList);
	}

	/**
	 * 测试jdk下的Objects Objects方法有很多，这里只测试部分
	 */
	@Test
	public void testObjects() {
		// 对象判空
		System.out.println("----------------对象判空----------------");
		Integer integer1 = new Integer(1);
		if (Objects.isNull(integer1)) {
			System.out.println("对象为空");
		}

		if (Objects.nonNull(integer1)) {
			System.out.println("对象不为空");
		}

		// 对象为空抛异常
		System.out.println("----------------对象为空抛异常----------------");
		Integer integer2 = new Integer(128);
		// Integer integer2 = null;
		Objects.requireNonNull(integer2);
		Objects.requireNonNull(integer2, "参数不能为空");
		Objects.requireNonNull(integer2, () -> "参数不能为空");

		// 判断两个对象是否相等
		System.out.println("----------------判断两个对象是否相等----------------");
		Integer integer3 = new Integer(1);
		Integer integer4 = new Integer(1);

		System.out.println(Objects.equals(integer3, integer4));

		// 获取对象的hashCode
		System.out.println("----------------获取对象的hashCode----------------");
		String str = new String("abc");
		System.out.println(Objects.hashCode(str));
	}

	/**
	 * 测试Apache下的BooleanUtils BooleanUtils类的方法有很多，这里只测试部分
	 */
	@Test
	public void testApacheBooleanUtils() {
		// 判断true或false
		System.out.println("----------------判断true或false----------------");
		Boolean aBoolean = new Boolean(true);
		System.out.println(BooleanUtils.isTrue(aBoolean));
		System.out.println(BooleanUtils.isFalse(aBoolean));

		// 判断不为true或不为false
		System.out.println("----------------判断不为true或不为false----------------");
		Boolean aBoolean1 = null;
		System.out.println(BooleanUtils.isNotTrue(aBoolean));
		System.out.println(BooleanUtils.isNotTrue(aBoolean1));
		System.out.println(BooleanUtils.isNotFalse(aBoolean));
		System.out.println(BooleanUtils.isNotFalse(aBoolean1));

		// 转换成数字
		System.out.println("----------------转换成数字----------------");
		// 如果想将true转换成数字1，false转换成数字0，可以使用toInteger方法
		Boolean bBoolean = new Boolean(true);
		Boolean bBoolean1 = new Boolean(false);
		System.out.println(BooleanUtils.toInteger(bBoolean));
		System.out.println(BooleanUtils.toInteger(bBoolean1));

		// Boolean转换成布尔值
		System.out.println("----------------Boolean转换成布尔值----------------");
		Boolean cBoolean = new Boolean(true);
		Boolean cBoolean1 = null;
		System.out.println(BooleanUtils.toBoolean(cBoolean));
		System.out.println(BooleanUtils.toBoolean(cBoolean1));
		System.out.println(BooleanUtils.toBooleanDefaultIfNull(cBoolean1, false));
	}

	/**
	 * 测试Apache下的StringUtils StringUtils类的方法有很多，这里只测试部分
	 */
	@Test
	public void testApacheStringUtils() {
		// 字符串判空
		System.out.println("----------------字符串判空----------------");
		String str1 = null;
		String str2 = "";
		String str3 = " ";
		String str4 = "abc";
		System.out.println(StringUtils.isEmpty(str1));
		System.out.println(StringUtils.isEmpty(str2));
		System.out.println(StringUtils.isEmpty(str3));
		System.out.println(StringUtils.isEmpty(str4));
		System.out.println("=====");
		System.out.println(StringUtils.isNotEmpty(str1));
		System.out.println(StringUtils.isNotEmpty(str2));
		System.out.println(StringUtils.isNotEmpty(str3));
		System.out.println(StringUtils.isNotEmpty(str4));
		System.out.println("=====");
		System.out.println(StringUtils.isBlank(str1));
		System.out.println(StringUtils.isBlank(str2));
		System.out.println(StringUtils.isBlank(str3));
		System.out.println(StringUtils.isBlank(str4));
		System.out.println("=====");
		System.out.println(StringUtils.isNotBlank(str1));
		System.out.println(StringUtils.isNotBlank(str2));
		System.out.println(StringUtils.isNotBlank(str3));
		System.out.println(StringUtils.isNotBlank(str4));
		// 优先推荐使用isBlank和isNotBlank方法，因为它会把" "也考虑进去。

		// 分隔字符串
		System.out.println("----------------分隔字符串----------------");
		String str5 = null;
		System.out.println(StringUtils.split(str5, ","));
		// 使用StringUtils的split方法会返回null，而使用String的split方法会报指针异常。
		// System.out.println(str5.split(","));

		// 判断是否纯数字
		System.out.println("----------------判断是否纯数字----------------");
		String str6 = "123";
		String str7 = "123q";
		String str8 = "0.33";
		System.out.println(StringUtils.isNumeric(str6));
		System.out.println(StringUtils.isNumeric(str7));
		System.out.println(StringUtils.isNumeric(str8));

		// 将集合拼接成字符串
		System.out.println("----------------将集合拼接成字符串----------------");
		List<String> list1 = Lists.newArrayList("a", "b", "c");
		List<Integer> list2 = Lists.newArrayList(1, 2, 3);
		System.out.println(StringUtils.join(list1, ","));
		System.out.println(StringUtils.join(list2, " "));
	}

	/**
	 * Spring提供了Assert类，它表示断言。 Assert类的方法有很多，这里只测试部分
	 */
	@Test
	public void testAssert() {
		// 断言参数是否为空
		{
			System.out.println("----------------断言参数是否为空----------------");
			String str = null;
			Assert.isNull(str, "str必须为空");
			Assert.isNull(str, () -> "str必须为空");
			// Assert.notNull(str, "str不能为空");
		}

		// 断言集合是否为空
		{
			System.out.println("----------------断言集合是否为空----------------");
			List<String> list = null;
			Map<String, String> map = null;
			// Assert.notEmpty(list, "list不能为空");
			// Assert.notEmpty(list, () -> "list不能为空");
			// Assert.notEmpty(map, "map不能为空");
		}

		{
			// 断言条件是否为空
			List<String> list = null;
			// Assert.isTrue(CollectionUtils.isNotEmpty(list), "list不能为空");
			// Assert.isTrue(CollectionUtils.isNotEmpty(list), () -> "list不能为空");
		}
	}

	/**
	 * 测试Apache下的IOUtils IOUtils类的方法有很多，这里只测试部分
	 */
	@Test
	public void testApacheIOUtils() throws IOException {
		{
			// 读取文件
			System.out.println("----------------读取文件----------------");
			String str = IOUtils.toString(new FileInputStream(
					"E:\\workspace_idea\\programmer-evolution-house-nav\\programmer-evolution-house-nav-admin\\src\\main\\resources\\banner.txt"),
					StandardCharsets.UTF_8);
			System.out.println(str);
		}

		{
			// 写入文件
			System.out.println("----------------写入文件----------------");
			String str = "abcde";
			IOUtils.write(str,
					new FileOutputStream(
							"E:\\workspace_idea\\ComponentStudy\\springboot-demo\\src\\main\\resources\\a.txt"),
					StandardCharsets.UTF_8);
		}

		{
			// 文件拷贝
			System.out.println("----------------文件拷贝----------------");
			IOUtils.copy(new FileInputStream(
					"E:\\workspace_idea\\programmer-evolution-house-nav\\programmer-evolution-house-nav-admin\\src\\main\\resources\\banner.txt"),
					new FileOutputStream(
							"E:\\workspace_idea\\ComponentStudy\\springboot-demo\\src\\main\\resources\\a.txt"));
		}

		{
			// 读取文件内容到字节数组
			System.out.println("----------------读取文件内容到字节数组----------------");
			byte[] bytes = IOUtils.toByteArray(new FileInputStream(
					"E:\\workspace_idea\\programmer-evolution-house-nav\\programmer-evolution-house-nav-admin\\src\\main\\resources\\banner.txt"));
		}
	}

	/**
	 * Spring的org.springframework.util包下的ClassUtils类
	 */
	@Test
	public void testClassUtils() {
		{
			// 获取对象的所有接口
			System.out.println("----------------获取对象的所有接口----------------");
			Class<?>[] allInterfaces = ClassUtils.getAllInterfaces(Object.class);
		}

		{
			// 获取某个类的包名
			System.out.println("----------------获取某个类的包名----------------");
			String packageName = ClassUtils.getPackageName(Object.class);
			System.out.println(packageName);
		}

		{
			// 判断某个类是否内部类
			System.out.println("----------------判断某个类是否内部类----------------");
			boolean isInnerClass = ClassUtils.isInnerClass(Object.class);
			System.out.println(isInnerClass);
		}

		{
			// 判断对象是否代理对象
			System.out.println("----------------判断对象是否代理对象----------------");
			boolean isCglibProxy = ClassUtils.isCglibProxy(new Object());
			System.out.println(isCglibProxy);
		}
	}

	/**
	 * Spring的BeanUtils类
	 */
	@Test
	public void testBeanUtils() {
		{
			// 拷贝对象的属性
			System.out.println("----------------拷贝对象的属性----------------");
			User user1 = new User();
			user1.setId(1);
			user1.setUserName("lijing");
			user1.setTelphone("17798832262");

			User user2 = new User();
			BeanUtils.copyProperties(user1, user2);
			System.out.println(user2);
		}

		{
			// 实例化某个类
			System.out.println("----------------实例化某个类----------------");
			User user = BeanUtils.instantiateClass(User.class);
		}

		{
			// 获取指定类的指定方法
			System.out.println("----------------获取指定类的指定方法----------------");
			Method method = BeanUtils.findMethod(User.class, "setUserName", String.class);
			System.out.println(method.getName());
		}

		{
			// 获取指定方法的参数
			Method declaredMethod = BeanUtils.findDeclaredMethod(User.class, "getId");
			PropertyDescriptor propertyForMethod = BeanUtils.findPropertyForMethod(declaredMethod);
			System.out.println(propertyForMethod.getPropertyType());
			System.out.println(propertyForMethod.getName());
		}
	}

	/**
	 * Spring的ReflectionUtils类
	 */
	@Test
	public void testReflectionUtils() {
		{
			// 获取方法
			System.out.println("----------------获取方法----------------");
			Method method = ReflectionUtils.findMethod(User.class, "setUserName", String.class);
		}

		{
			// 获取字段
			System.out.println("----------------获取字段----------------");
			Field field = ReflectionUtils.findField(User.class, "userName");
		}

		{
			// 执行方法
			System.out.println("----------------执行方法----------------");
			User user = new User();
			Method method = ReflectionUtils.findMethod(User.class, "setUserName", String.class);
			ReflectionUtils.invokeMethod(method, user, "lijing");
			System.out.println(user);
		}

		{
			// 判断字段是否常量
			System.out.println("----------------判断字段是否常量----------------");
			Field field = ReflectionUtils.findField(User.class, "userName");
			System.out.println(ReflectionUtils.isPublicStaticFinal(field));
		}

		{
			// 判断是否equals方法
			System.out.println("----------------判断是否equals方法----------------");
			Method method = ReflectionUtils.findMethod(User.class, "getId");
			System.out.println(ReflectionUtils.isEqualsMethod(method));
		}
	}

}
