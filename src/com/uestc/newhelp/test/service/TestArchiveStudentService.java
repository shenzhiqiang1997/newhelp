package com.uestc.newhelp.test.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.entity.ArchiveStudent;
import com.uestc.newhelp.exception.NoSuchStudentException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.service.ArchiveStudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestArchiveStudentService {
	@Autowired
	private ArchiveStudentService archiveStudentService;
	/*@Test
	public void testAdd() {
		ArchiveStudent archiveStudent=new ArchiveStudent(); 
		archiveStudent.setStudentId(2016220305014l);
		archiveStudent.setTeacherId("20162201");
		archiveStudentService.add(archiveStudent);
	}*/
	@Test
	public void testList() {
		List<ArchiveStudent> archiveStudents=archiveStudentService.list("20162202");
		for (ArchiveStudent archiveStudent : archiveStudents) {
			System.out.println(archiveStudent);
		}
				
	}
	@Test
	public void testSearch() {
		ArchiveStudent a=new ArchiveStudent();
		a.setTeacherId("20162201");
		a.setStudentId(2016220305014l);
		List<ArchiveStudent> archiveStudents=archiveStudentService.search(a);
		for (ArchiveStudent archiveStudent : archiveStudents) {
			System.out.println(archiveStudent);
		}
	}
	@Test
	public void update() throws ParseException {
		ArchiveStudent archiveStudent=new ArchiveStudent(); 
		archiveStudent.setStudentId(2016220305014l);
		archiveStudent.setTeacherId("20162201");
		archiveStudent.setAttentionType("Ò»°ã¹Ø×¢");
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-HH-mm");
		Date date=dateFormat.parse("2016-12-01");
		archiveStudent.setLastRecordTime(date);
		archiveStudentService.update(archiveStudent);
	}
	@Test
	public void testGet() {
		ArchiveStudent archiveStudent=archiveStudentService.get(2016220305014L);
		System.out.println(archiveStudent);
	}
	@Test
	public void testDelete() {
		/*archiveStudentService.delete(2016220305024L);*/
	}
	@Test
	public void testExport() {
		FileOutputStream outputStream = null;
		try {
			byte[] bytes=archiveStudentService.exportArchiveToWordFile(2016220305023L);
			outputStream=new FileOutputStream("result.docx");
			outputStream.write(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotChoseExportObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchStudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(outputStream!=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
