package com.uestc.newhelp.test.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.HistoryRecorderChangeDao;
import com.uestc.newhelp.entity.HistoryRecorderChange;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestHistoryRecorderChangeDao {
	@Autowired
	private HistoryRecorderChangeDao historyRecorderChangeDao;
	@Test
	public void testAddBatch() {
		List<HistoryRecorderChange> historyRecorderChanges=new ArrayList<>(2);
		HistoryRecorderChange historyRecorderChange=new HistoryRecorderChange(null, 1L, new Date(), "王猛", "whatever");
		historyRecorderChanges.add(historyRecorderChange);
		historyRecorderChange=new HistoryRecorderChange(null, 1L, new Date(), "李周", "whatever");
		historyRecorderChanges.add(historyRecorderChange);
		historyRecorderChangeDao.addBatch(historyRecorderChanges);
	}
	@Test
	public void testList() {
		List<HistoryRecorderChange> historyRecorderChanges=historyRecorderChangeDao.list(1L);
		for (HistoryRecorderChange historyRecorderChange : historyRecorderChanges) {
			System.out.println(historyRecorderChange);
		}
	}
}
