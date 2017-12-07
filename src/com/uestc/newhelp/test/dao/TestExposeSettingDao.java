package com.uestc.newhelp.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.ExposeSettingDao;
import com.uestc.newhelp.entity.ExposeSetting;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestExposeSettingDao {
	@Autowired
	private ExposeSettingDao exposeSettingDao;
	@Test
	public void testAdd() {
		ExposeSetting exposeSetting=new ExposeSetting();
		exposeSetting.setTeacherId("20162201");
		exposeSettingDao.add(exposeSetting);
	}
	@Test
	public void testGet() {
		ExposeSetting exposeSetting=exposeSettingDao.get("20162201");
		System.out.println(exposeSetting);
		System.out.println(exposeSetting.getExposeStudyCondition());
	}
	@Test
	public void testUpdate() {
		ExposeSetting exposeSetting=new ExposeSetting();
		exposeSetting.setExposeSettingId(1L);
		exposeSetting.setExposeStudyCondition((byte)1);
		exposeSettingDao.update(exposeSetting);
	}
}
