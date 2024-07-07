package com.itjing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itjing.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author lijing
 * @date 2021年12月08日 17:10
 * @description
 */
@SpringBootTest
class TestActiveRecord {

	@Test
	public void testSelectById() {
		User user = new User().setId(1L).selectById();
		System.out.println("user = " + user);
	}

	@Test
	public void testInsert() {
		boolean flag = new User().setUsername("lijing")
			.setEmail("lijing@qq.com")
			.setPassword("123456")
			.setName("晶哥哥")
			.setAge(18)
			.insert();
		System.out.println("flag = " + flag);
	}

	@Test
	public void testUpdate() {
		User user = new User();
		user.setId(1L);
		user.setAge(18);
		boolean flag = user.updateById();
		System.out.println("flag = " + flag);
	}

	@Test
	public void testDelete() {
		User user = new User();
		user.setId(1L);
		boolean flag = user.deleteById();
		System.out.println("flag = " + flag);
	}

	@Test
	public void testSelect() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.le("age", "20");
		User user = new User();
		List<User> userList = user.selectList(wrapper);
		System.out.println("userList = " + userList);
	}

}
