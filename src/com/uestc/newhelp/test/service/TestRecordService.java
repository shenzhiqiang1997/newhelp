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
		Record record=new Record(null, 2016220305014L, "�ҳ���ϵ��", new Date(), "�칫��", null, "����", null, "��̸", "���񰮵���", "adsasd");
		recordService.add(record);
	}
	@Test
	public void testAddBatch() {
		List<Record> records=new ArrayList<>(2);
		Record record=new Record(null, 2016220305014L, "��̸��ϵ��", new Date(), "��etr����", null, "����", null, "te", "����erter����", "asdwerer");
		records.add(record);
		record.setRecordName("���ּ�¼��");
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
		record.setRecordName("�绰��ϵ��");
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
