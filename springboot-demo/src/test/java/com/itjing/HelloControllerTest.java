package com.itjing;

import com.alibaba.fastjson.JSONObject;
import com.itjing.entity.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author lijing
 * @date 2021年12月10日 15:41
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {

	/**
	 * 注入web环境的ApplicationContext容器
	 */
	@Autowired
	private WebApplicationContext context;

	/**
	 * 模拟mvc测试对象
	 */
	private MockMvc mockMvc;

	/**
	 * 所有测试方法执行之前执行该方法，这个注解的作用,在每个方法之前都会执行一遍
	 */
	@Before
	public void before() throws Exception {
		// 获取mockmvc对象实例
		mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	/**
	 * 传递 get 参数
	 * @throws Exception
	 */
	@Test
	public void hello() throws Exception {
		MvcResult mvcResult = mockMvc
			.perform(MockMvcRequestBuilders.get("/hello/hello").param("name", "Lijing").param("sex", "男"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
		System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
	}

	/**
	 * 测试传递 @RequestBody ，传递对象
	 * @throws Exception
	 */
	@Test
	public void add() throws Exception {
		Person person = new Person();
		person.setName("张三");
		person.setSex("male");
		String paraJson = JSONObject.toJSONString(person);
		MvcResult mvcResult = mockMvc
			.perform(
					MockMvcRequestBuilders.post("/hello/add").contentType(MediaType.APPLICATION_JSON).content(paraJson))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
		System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
	}

}
