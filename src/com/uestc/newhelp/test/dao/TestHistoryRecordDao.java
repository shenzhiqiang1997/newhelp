package com.uestc.newhelp.test.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.HistoryRecordDao;
import com.uestc.newhelp.entity.HistoryRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestHistoryRecordDao {
	@Autowired
	private HistoryRecordDao historyRecordDao;
	@Test
	public void testAddBaatch() {
		List<HistoryRecord> historyRecords=new ArrayList<>(2);
		HistoryRecord historyRecord=new HistoryRecord(null, 1L, "家庭联系表", new Date(), "办公室", "无", "戴瑞婷", "戴瑞婷", "面聊", "dasdqweqtrete1111", "asdoiquweyg");
		historyRecords.add(historyRecord);
		historyRecord=new HistoryRecord(null, 1L, "研讨记录表", new Date(), "办公室", "无", "戴瑞婷", "戴瑞婷", "面聊", "dasdqweqtrete1111", "asdoiquweyg");
		historyRecords.add(historyRecord);
		historyRecordDao.addBatch(historyRecords);
	}
	@Test
	public void testGet() {
		HistoryRecord historyRecord=historyRecordDao.get(1L);
		System.out.println(historyRecord);
	}
	@Test
	public void testList() {
		HistoryRecord historyRecord=new HistoryRecord();
		historyRecord.setHistoryArchiveId(1L);
		List<HistoryRecord> historyRecords=historyRecordDao.listOnType(historyRecord);
		for (HistoryRecord historyRecord2 : historyRecords) {
			System.out.println(historyRecord2);
		}
	}
}
