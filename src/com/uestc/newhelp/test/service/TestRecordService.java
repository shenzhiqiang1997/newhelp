package com.uestc.newhelp.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.entity.Record;
import com.uestc.newhelp.service.RecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestRecordService {
	@Autowired
	private RecordService recordService;
	@Test
	public void testAdd() {
		Record record=new Record(null, 2016220305014L, "家长联系表", new Date(), "办公室", null, "李周", null, "面谈", "大神爱迪生", "adsasd");
		recordService.add(record);
	}
	@Test
	public void testAddBatch() {
		List<Record> records=new ArrayList<>(2);
		Record record=new Record(null, 2016220305014L, "面谈联系表", new Date(), "办etr公室", null, "李周", null, "te", "大神erter迪生", "asdwerer");
		records.add(record);
		record.setRecordName("研讨记录表");
		records.add(record);
		recordService.addBatch(records);
	}
	@Test
	public void testList() {
		Record record=new Record();
		record.setStudentId(2016220305014L);
		List<Record> records=recordService.list(record);
		for (Record record2 : records) {
			System.out.println(record2);
		}
	}
	@Test
	public void testGet() {
		Record record=recordService.get(3L);
		System.out.println(record);
	}
	@Test
	public void testUpdate() {
		Record record=new Record();
		record.setStudentId(2016220305014L);
		record.setRecordId(3L);
		record.setRecordName("电话联系表");
		recordService.update(record);
	}
	@Test
	public void testDeleteBatch() {
		List<Long> recordIds=new ArrayList<>(2);
		recordIds.add(2L);
		recordIds.add(3L);
		recordService.deleteBatch(recordIds);
	}
}
