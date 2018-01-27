package com.uestc.newhelp.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.service.TeacherService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestTeacherService {
	@Autowired
	private TeacherService teacherService;
	
	@Test
	public void testAdd() {
		teacherService.add(new Teacher("test1", "test"));
	}
	
	@Test
	public void testDelete() {
		teacherService.delete("test1");
	}
}
