package com.uestc.newhelp.test.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.LogDao;
import com.uestc.newhelp.entity.Log;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestLogDao {
	@Autowired
	private LogDao logDao;
	
	@Test
	public void TestAdd() {
		Log log=new Log();
		log.setContent("test");
		log.setOperateTime(new Date());
		log.setResult((byte)1);
		logDao.add(log);
	}
	
	@Test
	public void testList() {
		List<Log> logs=logDao.list();
		System.out.println(logs);
	}
	
	@Test
	public void testDelete() {
		logDao.delete((long)1);
	}
}
