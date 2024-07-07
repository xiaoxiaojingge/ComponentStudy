package com.itjing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itjing.mapper.UserMapper;
import com.itjing.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		// 查询name不为空，并且邮箱不为空的用户，年龄大于等于12
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.isNotNull("name").isNotNull("email").ge("age", 12);
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(System.out::println);
	}

	@Test
	public void test2() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("name", "jinggege");
		User user = userMapper.selectOne(wrapper);
		System.out.println(user);
	}

	@Test
	public void test3() {
		// 查询年龄在20~30之间的用户
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.between("age", 20, 30);// 区间
		Integer count = userMapper.selectCount(wrapper);// 查询结果数
		System.out.println(count);
	}

	// 模糊查询
	@Test
	public void test4() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		// 左和右
		// 左 %t 百分号在左边
		// 右 t% 百分号在右边
		wrapper.notLike("name", "e")// name中不包含e
			.likeRight("email", "t");// email以 t 开头的
		List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
		maps.forEach(System.out::println);
	}

	@Test
	public void test5() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		// id在 子查询中查出来
		wrapper.inSql("id", "select id from user where id<3");
		List<Object> objects = userMapper.selectObjs(wrapper);// 查询对象
		objects.forEach(System.out::println);
	}

	@Test
	public void test6() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		// 通过id进行排序
		wrapper.orderByDesc("id");

		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(System.out::println);
	}

}
