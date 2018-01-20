package com.uestc.newhelp.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestUserService {
	@Autowired
	private UserService userService;
/*	@Test
	public void testLogin() {
		Teacher teacher=new Teacher();
		teacher.setTeacherId("20162201");
		teacher.setPassword("123456");
		Teacher teacher2=userService.login(teacher);
		System.out.println(teacher2);
	}*/
	@Test
	public void testList() {
		List<Teacher> teachers=userService.list("20162201");
		for (Teacher teacher : teachers) {
			System.out.println(teacher);
		}
	}
}
