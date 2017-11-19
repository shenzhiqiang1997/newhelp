package com.uestc.newhelp.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.entity.AttentionType;
import com.uestc.newhelp.service.AttentionTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestAttentionTypeService {
	@Autowired
	private AttentionTypeService attentionTypeService;
	@Test
	public void testAdd() {
		AttentionType attentionType=new AttentionType();
		attentionType.setAttentionTypeName("一般关注");
		attentionType.setRemindRecordInterval((byte)1);
		attentionTypeService.add(attentionType);
		attentionType.setAttentionTypeName("特别关注");
		attentionType.setRemindRecordInterval((byte)2);
		attentionTypeService.add(attentionType);
	}
	@Test
	public void testList() {
		List<AttentionType> attentionTypes=attentionTypeService.list();
		for (AttentionType attentionType : attentionTypes) {
			System.out.println(attentionType);
		}
	}
	@Test
	public void testDelete() {
		attentionTypeService.delete(4L);
	}
}
