package com.uestc.newhelp.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.AttentionTypeDao;
import com.uestc.newhelp.entity.AttentionType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestAttentionType {
	@Autowired
	private AttentionTypeDao attentionTypeDao;
	@Test
	public void testAdd() {
		AttentionType attentionType=new AttentionType();
		attentionType.setAttentionTypeName("ÖØµã¹Ø×¢");
		attentionType.setRemindRecordInterval((byte)2);
		attentionTypeDao.add(attentionType);
	}
	@Test
	public void testList() {
		List<AttentionType> attentionTypes=attentionTypeDao.list();
		for (AttentionType attentionType : attentionTypes) {
			System.out.println(attentionType);
		}
	}
	@Test
	public void testUpdate() {
		AttentionType attentionType=new AttentionType();
		attentionType.setAttentionTypeId(1L);
		attentionType.setRemindRecordInterval((byte)2);
		attentionTypeDao.update(attentionType);
	}
	@Test
	public void testDelete() {
		attentionTypeDao.delete(1L);
	}
}
