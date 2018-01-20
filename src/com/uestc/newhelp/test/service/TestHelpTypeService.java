package com.uestc.newhelp.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.entity.HelpType;
import com.uestc.newhelp.service.HelpTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestHelpTypeService {
	@Autowired
	private HelpTypeService helpTypeService;
	@Test
	public void testAdd() {
		HelpType helpType=new HelpType();
		
		helpType.setHelpTypeName("¼ÒÍ¥À§ÄÑ");
		helpTypeService.add(helpType);
		
	}
	@Test
	public void testList() {
		List<HelpType> helpTypes=helpTypeService.list();
		for (HelpType helpType : helpTypes) {
			System.out.println(helpType);
		}
	}
	@Test
	public void testDelete() {
		helpTypeService.delete(1L);
	}
}
