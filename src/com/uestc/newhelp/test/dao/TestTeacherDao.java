package com.uestc.newhelp.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.entity.Teacher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestTeacherDao {
	@Autowired
	private TeacherDao teacherDao;
	@Test
	public void test() {
		Teacher teacher=new Teacher();
		teacher.setTeacherId("20162202");
		teacher.setPassword("abcdef");
		teacherDao.updatePassword(teacher);
	}
}
