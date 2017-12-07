package com.uestc.newhelp.test.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.uestc.newhelp.entity.BaseStudent;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.service.BaseStudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestBaseStudentService {
	@Autowired
	private BaseStudentService baseStudentService;
	
	@Test
	public void testAddBatch() throws ParseException {
		List<BaseStudent> baseStudents=new ArrayList<>();
		
		BaseStudent baseStudent=new BaseStudent();
		baseStudent.setStudentId(2016220304014L);
		baseStudent.setName("陈靖2");
		baseStudent.setGrade((short) 2016);
		baseStudent.setStudentClass(2016220305);
		baseStudent.setSex("男");
		baseStudent.setDuty("无");
		baseStudent.setDormitory("239");
		baseStudent.setContactWay("18581517585");
		baseStudent.setIdCardNumber("511025199703213096");
		baseStudent.setQqNumber(1422537078L);
		baseStudent.setEmail("1422537078@qq.com");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse("1997-03-21");
		baseStudent.setBirthday(date);
		baseStudent.setHeight(180f);
		baseStudent.setMajor("软件技术");
		baseStudent.setPoliticalStatus("团员");
		baseStudent.setEthnicGroup("汉族");
		baseStudent.setBirthOrigin("四川");
		baseStudent.setCollegeEntranceExamScore("621/750");
		baseStudent.setCollegeEntranceExamEnglishScore("141/150");
		baseStudent.setEntranceExamEnglishScore((byte)70);
		baseStudent.setHometownRailwayStation("资中");
		baseStudent.setProvince("四川");
		baseStudent.setCity("内江");
		baseStudent.setFamilyAddress("四川省资中县陈家镇");
		baseStudent.setFamilyTelNumber("68874298");
		baseStudent.setPostcode("641200");
		baseStudent.setSpecialty("独轮车");
		baseStudent.setDutyInHighSchool("体育委员");
		baseStudent.setIsHadTechnologyCompetitionAward("否");
		baseStudent.setFatherName("申金堂");
		baseStudent.setFatherWorkUnit("无");
		baseStudent.setFatherWorkUnitAddress("无");
		baseStudent.setFatherDuty("厂长");
		baseStudent.setFatherPostcode("641200");
		baseStudent.setFatherTelNumber("无");
		baseStudent.setMotherName("桂学英");
		baseStudent.setMotherWorkUnit("无");
		baseStudent.setMotherWorkUnitAddress("无");
		baseStudent.setMotherDuty("员工");
		baseStudent.setMotherPostcode("641200");
		baseStudent.setMotherTelNumber("15058859819");
		
		BaseStudent baseStudent1=new BaseStudent();
		baseStudent1.setStudentId(2016220305023L);
		baseStudent1.setName("申志强1");
		baseStudent1.setGrade((short) 2016);
		baseStudent1.setStudentClass(2016220305);
		baseStudent1.setSex("男");
		baseStudent1.setDuty("无");
		baseStudent1.setDormitory("239");
		baseStudent1.setContactWay("18581517585");
		baseStudent1.setIdCardNumber("511025199703213096");
		baseStudent1.setQqNumber(1422537078L);
		baseStudent1.setEmail("1422537078@qq.com");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		Date date1=sdf1.parse("1997-03-21");
		baseStudent1.setBirthday(date1);
		baseStudent1.setHeight(180f);
		baseStudent1.setMajor("软件技术");
		baseStudent1.setPoliticalStatus("团员");
		baseStudent1.setEthnicGroup("汉族");
		baseStudent1.setBirthOrigin("四川");
		baseStudent1.setCollegeEntranceExamScore("621/750");
		baseStudent1.setCollegeEntranceExamEnglishScore("141/150");
		baseStudent1.setEntranceExamEnglishScore((byte)70);
		baseStudent1.setHometownRailwayStation("资中");
		baseStudent1.setProvince("四川");
		baseStudent1.setCity("内江");
		baseStudent1.setFamilyAddress("四川省资中县陈家镇");
		baseStudent1.setFamilyTelNumber("68874298");
		baseStudent1.setPostcode("641200");
		baseStudent1.setSpecialty("独轮车");
		baseStudent1.setDutyInHighSchool("体育委员");
		baseStudent1.setIsHadTechnologyCompetitionAward("否");
		baseStudent1.setFatherName("申金堂");
		baseStudent1.setFatherWorkUnit("无");
		baseStudent1.setFatherWorkUnitAddress("无");
		baseStudent1.setFatherDuty("厂长");
		baseStudent1.setFatherPostcode("641200");
		baseStudent1.setFatherTelNumber("无");
		baseStudent1.setMotherName("桂学英");
		baseStudent1.setMotherWorkUnit("无");
		baseStudent1.setMotherWorkUnitAddress("无");
		baseStudent1.setMotherDuty("员工");
		baseStudent1.setMotherPostcode("641200");
		baseStudent1.setMotherTelNumber("15058859819");
		
		baseStudents.add(baseStudent);
		baseStudents.add(baseStudent1);
	}
	@Test
	public void testList() {
		List<BaseStudent> baseStudents=baseStudentService.list();
		for (BaseStudent baseStudent : baseStudents) {
			System.out.println(baseStudent);
		}
	}
	/*@Test
	public void testSearch() throws ParseException {
		BaseStudent baseStudent=new BaseStudent();
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		baseStudent.setHeight(180F);
		BaseStudentWithPageParam baseStudentWithPageParam=new BaseStudentWithPageParam();
		Page page=new Page(1, -1, 0);
		baseStudentWithPageParam.setPage(page);
		baseStudentWithPageParam.setBaseStudent(baseStudent);
		BaseStudentsWithPage baseStudentsWithPage=baseStudentService.search(baseStudentWithPageParam);
		System.out.println(baseStudentsWithPage);
		System.out.println(page);
		
		List<BaseStudent> baseStudents=baseStudentService.search( baseStudent);
		for (BaseStudent b : baseStudents) {
			System.out.println(b);
		}
	}*/
	/*@Test
	public void testListIdByName() {
		List<Long> longs=baseStudentService.listIdByName("申志强1");
		for (Long long1 : longs) {
			System.out.println(long1);
		}
	}*/
	@Test
	public void testGetAllInfo() {
		BaseStudent baseStudent=baseStudentService.getAllInfo(2016220305023L);
		System.out.println(baseStudent);
	}
	@Test
	public void testGetFamilyInfo() {
		BaseStudent baseStudent=baseStudentService.getFamilyInfo(2016220305023L);
		System.out.println(baseStudent);
	}
	@Test
	public void testGetPersonalInfo() {
		BaseStudent baseStudent=baseStudentService.getPersonalInfo(2016220305023L);
		System.out.println(baseStudent);
	}
/*	@Test
	public void testGetArchiveInfo() {
		BaseStudent baseStudent=baseStudentService.getArchiveInfo(2016220305023L);
		System.out.println(baseStudent);
		
	}*/
	@SuppressWarnings("unused")
	@Test
	public void testUpdate() throws FileNotFoundException, IOException {
		Teacher teacher=new Teacher();
		teacher.setTeacherId("20162201");
		teacher.setPassword("123456");
		BaseStudent baseStudent=new BaseStudent();
		baseStudent.setStudentId(2016220305023L);
		baseStudent.setName("申");
		File file=new File("饺子3.jpg");
		MultipartFile multipartFile=new MockMultipartFile("file", file.getName(), "application/x-jpg", new FileInputStream(file));
		/*try {
			baseStudentService.update(baseStudent, teacher, multipartFile);
		} catch (FileTypeNotMatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PasswordNotMatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
