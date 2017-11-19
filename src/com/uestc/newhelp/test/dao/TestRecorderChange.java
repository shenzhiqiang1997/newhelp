package com.uestc.newhelp.test.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.RecorderChangeDao;
import com.uestc.newhelp.entity.RecorderChange;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestRecorderChange {
	@Autowired
	private RecorderChangeDao recorderChangeDao;
	@Test
	public void testAdd() {
		RecorderChange recorderChange=new RecorderChange();
		recorderChange.setStudentId(2016220305023L);
		recorderChange.setRecorderNow("´÷ÈðæÃ");
		recorderChange.setChangeReason("asdas");
		recorderChange.setChangeTime(new Date());
		recorderChangeDao.add(recorderChange);
	}
	@Test
	public void testList() {
		List<RecorderChange> recorderChanges=recorderChangeDao.list(2016220305023L);
		for (RecorderChange recorderChange : recorderChanges) {
			System.out.println(recorderChange);
		}
	}
	@Test
	public void testDelete() {
		recorderChangeDao.delete(2016220305023L);
	}
}
