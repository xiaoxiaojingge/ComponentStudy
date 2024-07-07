package com.itjing;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itjing.entity.User;
import com.itjing.enu.SexEnum;
import com.itjing.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlus2021ApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSelect() {
		List<User> userList = userMapper.selectList(null);
		userList.forEach(System.out::println);
	}

	@Test
	public void testInsert() {
		User user = new User();
		user.setUsername("lijing");
		user.setPassword("123456");
		user.setName("李晶");
		user.setAge(18);
		user.setEmail("123456@qq.com");

		int count = userMapper.insert(user);
		// 数据库受影响的行数
		System.out.println("count = " + count);
		// 自增后的id会回填到对象中
		System.out.println("user.getId() = " + user.getId());
	}

	@Test
	public void testInsertEnum() {
		User user = new User();
		user.setUsername("sunwukong");
		user.setPassword("123456");
		user.setName("孙悟空");
		user.setAge(18);
		user.setEmail("123456@qq.com");
		user.setSex(SexEnum.MAN); // 设置性别

		int count = userMapper.insert(user);
		// 数据库受影响的行数
		System.out.println("count = " + count);
		// 自增后的id会回填到对象中
		System.out.println("user.getId() = " + user.getId());
	}

	@Test
	public void testUpdateById() {

		User user = new User();
		user.setId(1L);
		user.setUsername("zs");

		// 根据id更新，更新不为null的字段，即有效字段更新，返回更新的条数
		int result = userMapper.updateById(user);
		System.out.println("result = " + result);
	}

	@Test
	public void testUpdate() {
		// 封装更新字段的对象
		User user = new User();
		user.setAge(18);

		// 封装更新条件的对象
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name", "lijing");

		int result = userMapper.update(user, queryWrapper);
		System.out.println("result = " + result);
	}

	@Test
	public void testUpdate2() {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.set("age", 18).set("password", "123456").eq("user_name", "zhangsan");

		int result = userMapper.update(null, updateWrapper);
		System.out.println("result = " + result);
	}

	@Test
	public void testDeleteById() {
		// 执行删除操作
		int result = userMapper.deleteById(1468483313494695941L);
		System.out.println("result = " + result);
	}

	@Test
	public void testDeleteByMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", "5");
		map.put("age", "24");

		// 将map中的元素设置为删除的条件，多个条件之间是and的关系
		int result = userMapper.deleteByMap(map);
		System.out.println("result = " + result);
	}

	@Test
	public void testDelete() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.gt("age", 20);
		userMapper.delete(queryWrapper);
	}

	@Test
	public void testDeleteBatchIds() {
		// 根据id集合批量删除
		int result = userMapper.deleteBatchIds(Arrays.asList(1, 2, 3));
		System.out.println("result = " + result);
	}

	@Test
	public void testSelectById() {
		// 根据id查询数据
		User user = userMapper.selectById(1);
		System.out.println("user = " + user);
	}

	@Test
	public void testSelectBatchIds() {
		// 根据id集合批量查询
		List<User> userList = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
		System.out.println("userList = " + userList);
	}

	@Test
	public void testSelectOne() {
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(User::getUsername, "zhangsan");

		// 根据条件查询一条数据，如果结果超过一条则报错
		User user = userMapper.selectOne(lambdaQueryWrapper);
		System.out.println("user = " + user);
	}

	@Test
	public void testSelectList() {
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.ge(User::getId, 3);
		// 根据条件查询数据
		List<User> userList = userMapper.selectList(lambdaQueryWrapper);
		System.out.println("userList = " + userList);
	}

	@Test
	public void testSelectCount() {
		// 根据条件查询数据条数
		Integer count = userMapper.selectCount(new LambdaQueryWrapper<User>().ge(User::getId, 2));
		System.out.println("count = " + count);
	}

	@Test
	public void testSelectPage() {
		// 分页
		Page<User> page = new Page<>(1, 3);
		// 查询分页数据，后面的 QueryWrapper，可以自己设置查询条件
		Page<User> selectPage = userMapper.selectPage(page, new QueryWrapper<>());
		long total = selectPage.getTotal();
		long pages = selectPage.getPages();
		System.out.println("数据总条数 = " + total);
		System.out.println("总页数 = " + pages);
		List<User> userList = selectPage.getRecords();
		System.out.println("userList = " + userList);
	}

	@Test
	public void testAllEq() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();

		// 设置条件
		Map<String, Object> params = new HashMap<>();
		params.put("name", "zhangsan");
		params.put("age", null);

		// SELECT id,user_name,password,name,age,email FROM tb_user WHERE (name = ? AND
		// age IS NULL)
		wrapper.allEq(params);

		List<User> userList = userMapper.selectList(wrapper);
		System.out.println("userList = " + userList);
	}

	@Test
	public void testAllEqNull2IsNullFalse() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();

		// 设置条件
		Map<String, Object> params = new HashMap<>();
		params.put("name", "zhangsan");
		params.put("age", null);

		// SELECT id,user_name,password,name,age,email FROM tb_user WHERE (name = ?)
		wrapper.allEq(params, false);

		List<User> userList = userMapper.selectList(wrapper);
		System.out.println("userList = " + userList);
	}

	@Test
	public void testSelectWrapper() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();

		// SELECT user_name,age,password FROM tb_user WHERE (name = ? OR age = ?)
		wrapper.eq("name", "张三").or().eq("age", 18).select("user_name", "age", "password");

		List<User> userList = this.userMapper.selectList(wrapper);
		System.out.println("userList = " + userList);
	}

	@Test
	public void testOptimisticLocker() {
		// 先查询再修改，会触发乐观锁配置，即实体对象中要有version字段值
		User user = userMapper.selectById(1);
		user.setUsername("lijing");
		int result = userMapper.updateById(user);
		System.out.println("result = " + result);
	}

}
