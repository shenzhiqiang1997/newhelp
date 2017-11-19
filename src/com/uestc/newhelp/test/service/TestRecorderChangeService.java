package com.uestc.newhelp.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.entity.RecorderChange;
import com.uestc.newhelp.service.RecorderChangeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestRecorderChangeService {
	@Autowired
	private RecorderChangeService recorderChangeService;
	@Test
	public void testAdd() {
		RecorderChange recorderChange=new RecorderChange();
		recorderChange.setChangeReason("adasqe");
		recorderChange.setStudentId(2016220305014L);
		recorderChangeService.add(recorderChange, "20162202");
	}
}
