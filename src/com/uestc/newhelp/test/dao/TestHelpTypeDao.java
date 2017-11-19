package com.uestc.newhelp.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.HelpTypeDao;
import com.uestc.newhelp.entity.HelpType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestHelpTypeDao {
	@Autowired
	private HelpTypeDao helpTypeDao;
	@Test
	public void testAdd() {
		HelpType helpType=new HelpType();
		helpType.setHelpTypeName("¾­¼ÃÀ§ÄÑ");
		helpTypeDao.add(helpType);
	}
	@Test
	public void testList() {
		List<HelpType> helpTypes=helpTypeDao.list();
		for (HelpType helpType : helpTypes) {
			System.out.println(helpType);
		}
	}
	@Test
	public void testDelete() {
		helpTypeDao.delete(3L);
	}
}
