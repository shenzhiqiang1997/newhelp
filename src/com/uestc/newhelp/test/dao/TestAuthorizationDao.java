package com.uestc.newhelp.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.AuthorizationDao;
import com.uestc.newhelp.entity.Authorization;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestAuthorizationDao {
	@Autowired
	private AuthorizationDao authorizationDao;
	
	@Test
	public void testAdd() {
		authorizationDao.add("lizhou");
	}
	
	@Test
	public void testGet() {
		Authorization authorization=authorizationDao.get("lizhou");
		System.out.println(authorization);
	}
	
	@Test
	public void testUpdate() {
		Authorization authorization=authorizationDao.get("lizhou");
		authorization.setBackEndHandle((byte)1);
		authorization.setArchiveRecordAdd((byte)1);
		authorizationDao.update(authorization);
		authorization=authorizationDao.get("lizhou");
		System.out.println(authorization);
	}
	
	@Test
	public void testDelete() {
		authorizationDao.delete("lizhou");
	}
}
