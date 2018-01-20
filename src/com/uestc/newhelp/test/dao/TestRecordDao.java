package com.uestc.newhelp.test.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.RecordDao;
import com.uestc.newhelp.entity.Record;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestRecordDao {
	@Autowired
	private RecordDao recordDao;
	@Test
	public void testAdd() {
		Record record=new Record(null, 2016220305023L, "�ҳ���ϵ��", new Date(), "�칫��", null, "������", null, "��̸", "���񰮵���", "adsasd");
		recordDao.add(record);
	}
	@Test
	public void testAddBatch() {
		List<Record> records=new ArrayList<>();
		Record record=new Record(null, 2016220305023L, "��̸��¼��", new Date(), "�칫��", null, "������", null, "��̸", "������񰮵���", "ads������asd");
		records.add(record);
		record=new Record(null, 2016220305023L, "���ּ�¼��", new Date(), "�칫��", null, "������", null, "��̸", "������񰮰��������ص���", "ads������a�����°�����sd"); 
		records.add(record);
		recordDao.addBatch(records);
	}
	@Test
	public void testListOnType() {
		Record record=new Record();
		record.setStudentId(2016220305023L);
		List<Record> records=recordDao.listOnType(record);
		for (Record record2 : records) {
			System.out.println(record2);
		}
	}
	@Test
	public void testGet() {
		Record record=recordDao.get(1L);
		System.out.println(record);
	}
	@Test
	public void testUpdate() {
		Record record=new Record();
		record.setRecordId(1L);
		record.setRecordName("�ҳ�");
		recordDao.update(record);
	}
	@Test
	public void testDeleteBatch() {
		List<Long> recordIds=new ArrayList<>();
		recordIds.add(Long.valueOf(1));
		recordIds.add(Long.valueOf(2));
		recordIds.add(Long.valueOf(3));
		recordDao.deleteBatch(recordIds);
	}
	@Test
	public void testDeleteByStudentId() {
		recordDao.deleteByStudentId(2016220305023L);
	}
}
