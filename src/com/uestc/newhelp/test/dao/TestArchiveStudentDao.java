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
		ArchiveStudent archiveStudent=new ArchiveStudent(2016220305023L, "20162203", "男", "申志强as", "软件技术as", (short)2016, 2016220305, "汉族是", "无", "239", "四川是", "四川资中是", "18581517585是", "68874298是", "15058859819是", "15058859819是", "15058859819", "15058859819", "15058859819", "15058859819", "15058859819", "15058859819", "戴瑞婷是", new Date(), null, null, null, "戴瑞是婷", "辅导员", "心理委员是", "重点关注", new Date(), false); 
		archiveStudent.setStudentId(2016220305023L);
		archiveStudent.setTeacherId("20162201");
		archiveStudent.setBulidingRecorder("戴瑞婷");
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
