package com.uestc.newhelp.test.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.ArchiveStudentDao;
import com.uestc.newhelp.entity.ArchiveStudent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestArchiveStudentDao {
	@Autowired
	private ArchiveStudentDao archiveStudentDao;
	@Test
	public void testAdd() {
		ArchiveStudent archiveStudent=new ArchiveStudent(); 
		archiveStudent.setStudentId(2016220305023l);
		archiveStudent.setTeacherId("20162201");
		archiveStudentDao.add(archiveStudent);
	}
	
	@Test
	public void testUpdate() {
		ArchiveStudent archiveStudent=new ArchiveStudent(2016220305023L, "20162203", "��", "��־ǿas", "�������as", (short)2016, 2016220305, "������", "��", "239", "�Ĵ���", "�Ĵ�������", "18581517585��", "68874298��", "15058859819��", "15058859819��", "15058859819", "15058859819", "15058859819", "15058859819", "15058859819", "15058859819", "��������", new Date(), null, null, null, "��������", "����Ա", "����ίԱ��", "�ص��ע", new Date(), false); 
		archiveStudent.setStudentId(2016220305023L);
		archiveStudent.setTeacherId("20162201");
		archiveStudent.setBulidingRecorder("������");
		archiveStudentDao.update(archiveStudent);
	}
	
	@Test
	public void testGet() {
		ArchiveStudent archiveStudent=archiveStudentDao.get(2016220305023L);
		System.out.println(archiveStudent);
	}
	
	@Test
	public void testList() {
		List<ArchiveStudent> archiveStudents=new ArrayList<>();
		archiveStudents=archiveStudentDao.list("20162201");
		for (ArchiveStudent archiveStudent : archiveStudents) {
			System.out.println(archiveStudent);
		}
	}
	
	@Test
	public void testSearch() {
		ArchiveStudent archiveStudent=new ArchiveStudent();
		archiveStudent.setTeacherId("20162201");
		archiveStudent.setGrade((short)2016);
		List<ArchiveStudent> archiveStudents=archiveStudentDao.search(archiveStudent);
		for (ArchiveStudent archiveStudent1 : archiveStudents) {
			System.out.println(archiveStudent1);
		}
	}
	
	@Test
	public void testCheck() {
		String teacherId=archiveStudentDao.check(2016220305023L);
		System.out.println(teacherId);
	}
	
	@Test
	public void testDelete() {
		archiveStudentDao.delete(2016220305023L);
	}
}
