package com.uestc.newhelp.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.entity.HistoryRecord;
import com.uestc.newhelp.service.HistoryRecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestHistoryRecordService {
	@Autowired
	private HistoryRecordService historyRecordService;
	@Test
	public void testList() {
		HistoryRecord historyRecord=new HistoryRecord();
		historyRecord.setHistoryArchiveId(1L);
		List<HistoryRecord> historyRecords=historyRecordService.list(historyRecord);
		for (HistoryRecord historyRecord2 : historyRecords) {
			System.out.println(historyRecord2);
		}
	}
	@Test
	public void testGet() {
		HistoryRecord historyRecord=historyRecordService.get(1L);
		System.out.println(historyRecord);
	}
}
