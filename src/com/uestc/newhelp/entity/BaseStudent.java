package com.uestc.newhelp.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

//基础学生对应的实体类
public class BaseStudent {
	//学生学号
	private Long  studentId;
	//学生姓名
	private String name;
	//学生年级
	private Short grade;
	//学生班级
	private Integer studentClass;
	//学生性别
	private String sex;
	//学生照片
	private String photoUrl;
	//学生职务
	private String duty;
	//学生宿舍
	private String dormitory;
	//学生联系方式
	private String contactWay;
	//学生身份证号码
	private String idCardNumber;
	//学生QQ号码
	private Long qqNumber;
	//学生邮箱地址
	private String email;
	//学生生日
	@DateTimeFormat(iso=ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	//学生身高
	private Float height;
	//学生专业
	private String major;
	//学生政治面貌
	private String politicalStatus;
	//学生民族
	private String ethnicGroup;
	//学生籍贯
	private String birthOrigin;
	//学生高考成绩/满分
	private String collegeEntranceExamScore;
	//学生高考英语成绩/满分
	private String collegeEntranceExamEnglishScore;
	//学生英语入校考试成绩
	private Byte entranceExamEnglishScore;
	//学生家乡所在火车站
	private String hometownRailwayStation;
	//学生省份
	private String province;
	//学生所在城市
	private String city;
	//学生家庭详细地址
	private String familyAddress;
	//学生家庭电话
	private String familyTelNumber;
	//学生邮政编码
	private String postcode;
	//学生特长
	private String specialty;
	//学生高中曾任职务
	private String dutyInHighSchool;
	//学生高中曾获奖励
	private String awardInHighSchool;
	//学生是否有科技竞赛类获奖
	private String isHadTechnologyCompetitionAward;
	//学生父亲姓名
	private String fatherName;
	//学生父亲工作单位
	private String fatherWorkUnit;
	//学生父亲工作单位详细地址
	private String fatherWorkUnitAddress;
	//学生父亲职务
	private String fatherDuty;
	//学生父亲邮编
	private String fatherPostcode;
	//学生父亲电话
	private String fatherTelNumber;
	//学生母亲姓名
	private String motherName;
	//学生母亲工作单位
	private String motherWorkUnit;
	//学生母亲工作单位详细地址
	private String motherWorkUnitAddress;
	//学生母亲职务
	private String motherDuty;
	//学生母亲邮编
	private String motherPostcode;
	//学生母亲电话
	private String motherTelNumber;
	
	public BaseStudent() {
		super();
	}
	
	

	



	public BaseStudent(Long studentId, String name, Short grade, Integer studentClass, String sex, String duty,
			String dormitory, String contactWay, String idCardNumber, Long qqNumber, String email, Date birthday,
			Float height, String major, String politicalStatus, String ethnicGroup, String birthOrigin,
			String collegeEntranceExamScore, String collegeEntranceExamEnglishScore, Byte entranceExamEnglishScore,
			String hometownRailwayStation, String province, String city, String familyAddress, String familyTelNumber,
			String postcode, String specialty, String dutyInHighSchool, String awardInHighSchool,
			String isHadTechnologyCompetitionAward, String fatherName, String fatherWorkUnit,
			String fatherWorkUnitAddress, String fatherDuty, String fatherPostcode, String fatherTelNumber,
			String motherName, String motherWorkUnit, String motherWorkUnitAddress, String motherDuty,
			String motherPostcode, String motherTelNumber) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.grade = grade;
		this.studentClass = studentClass;
		this.sex = sex;
		this.duty = duty;
		this.dormitory = dormitory;
		this.contactWay = contactWay;
		this.idCardNumber = idCardNumber;
		this.qqNumber = qqNumber;
		this.email = email;
		this.birthday = birthday;
		this.height = height;
		this.major = major;
		this.politicalStatus = politicalStatus;
		this.ethnicGroup = ethnicGroup;
		this.birthOrigin = birthOrigin;
		this.collegeEntranceExamScore = collegeEntranceExamScore;
		this.collegeEntranceExamEnglishScore = collegeEntranceExamEnglishScore;
		this.entranceExamEnglishScore = entranceExamEnglishScore;
		this.hometownRailwayStation = hometownRailwayStation;
		this.province = province;
		this.city = city;
		this.familyAddress = familyAddress;
		this.familyTelNumber = familyTelNumber;
		this.postcode = postcode;
		this.specialty = specialty;
		this.dutyInHighSchool = dutyInHighSchool;
		this.awardInHighSchool = awardInHighSchool;
		this.isHadTechnologyCompetitionAward = isHadTechnologyCompetitionAward;
		this.fatherName = fatherName;
		this.fatherWorkUnit = fatherWorkUnit;
		this.fatherWorkUnitAddress = fatherWorkUnitAddress;
		this.fatherDuty = fatherDuty;
		this.fatherPostcode = fatherPostcode;
		this.fatherTelNumber = fatherTelNumber;
		this.motherName = motherName;
		this.motherWorkUnit = motherWorkUnit;
		this.motherWorkUnitAddress = motherWorkUnitAddress;
		this.motherDuty = motherDuty;
		this.motherPostcode = motherPostcode;
		this.motherTelNumber = motherTelNumber;
	}







	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Short getGrade() {
		return grade;
	}
	public void setGrade(Short grade) {
		this.grade = grade;
	}
	public Integer getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(Integer studentClass) {
		this.studentClass = studentClass;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getDormitory() {
		return dormitory;
	}
	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getIdCardNumber() {
		return idCardNumber;
	}
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}
	public Long getQqNumber() {
		return qqNumber;
	}
	public void setQqNumber(Long qqNumber) {
		this.qqNumber = qqNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public String getEthnicGroup() {
		return ethnicGroup;
	}
	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}
	public String getBirthOrigin() {
		return birthOrigin;
	}
	public void setBirthOrigin(String birthOrigin) {
		this.birthOrigin = birthOrigin;
	}
	public String getCollegeEntranceExamScore() {
		return collegeEntranceExamScore;
	}
	public void setCollegeEntranceExamScore(String collegeEntranceExamScore) {
		this.collegeEntranceExamScore = collegeEntranceExamScore;
	}
	public String getCollegeEntranceExamEnglishScore() {
		return collegeEntranceExamEnglishScore;
	}
	public void setCollegeEntranceExamEnglishScore(String collegeEntranceExamEnglishScore) {
		this.collegeEntranceExamEnglishScore = collegeEntranceExamEnglishScore;
	}
	public Byte getEntranceExamEnglishScore() {
		return entranceExamEnglishScore;
	}
	public void setEntranceExamEnglishScore(Byte entranceExamEnglishScore) {
		this.entranceExamEnglishScore = entranceExamEnglishScore;
	}
	public String getHometownRailwayStation() {
		return hometownRailwayStation;
	}
	public void setHometownRailwayStation(String hometownRailwayStation) {
		this.hometownRailwayStation = hometownRailwayStation;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFamilyAddress() {
		return familyAddress;
	}
	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	public String getFamilyTelNumber() {
		return familyTelNumber;
	}
	public void setFamilyTelNumber(String familyTelNumber) {
		this.familyTelNumber = familyTelNumber;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getDutyInHighSchool() {
		return dutyInHighSchool;
	}
	public void setDutyInHighSchool(String dutyInHighSchool) {
		this.dutyInHighSchool = dutyInHighSchool;
	}
	
	public String getAwardInHighSchool() {
		return awardInHighSchool;
	}



	public void setAwardInHighSchool(String awardInHighSchool) {
		this.awardInHighSchool = awardInHighSchool;
	}



	public String getIsHadTechnologyCompetitionAward() {
		return isHadTechnologyCompetitionAward;
	}
	public void setIsHadTechnologyCompetitionAward(String isHadTechnologyCompetitionAward) {
		this.isHadTechnologyCompetitionAward = isHadTechnologyCompetitionAward;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getFatherWorkUnit() {
		return fatherWorkUnit;
	}
	public void setFatherWorkUnit(String fatherWorkUnit) {
		this.fatherWorkUnit = fatherWorkUnit;
	}
	public String getFatherWorkUnitAddress() {
		return fatherWorkUnitAddress;
	}
	public void setFatherWorkUnitAddress(String fatherWorkUnitAddress) {
		this.fatherWorkUnitAddress = fatherWorkUnitAddress;
	}
	public String getFatherDuty() {
		return fatherDuty;
	}
	public void setFatherDuty(String fatherDuty) {
		this.fatherDuty = fatherDuty;
	}
	public String getFatherPostcode() {
		return fatherPostcode;
	}
	public void setFatherPostcode(String fatherPostcode) {
		this.fatherPostcode = fatherPostcode;
	}
	public String getFatherTelNumber() {
		return fatherTelNumber;
	}
	public void setFatherTelNumber(String fatherTelNumber) {
		this.fatherTelNumber = fatherTelNumber;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMotherWorkUnit() {
		return motherWorkUnit;
	}
	public void setMotherWorkUnit(String motherWorkUnit) {
		this.motherWorkUnit = motherWorkUnit;
	}
	public String getMotherWorkUnitAddress() {
		return motherWorkUnitAddress;
	}
	public void setMotherWorkUnitAddress(String motherWorkUnitAddress) {
		this.motherWorkUnitAddress = motherWorkUnitAddress;
	}
	public String getMotherDuty() {
		return motherDuty;
	}
	public void setMotherDuty(String motherDuty) {
		this.motherDuty = motherDuty;
	}
	public String getMotherPostcode() {
		return motherPostcode;
	}
	public void setMotherPostcode(String motherPostcode) {
		this.motherPostcode = motherPostcode;
	}
	public String getMotherTelNumber() {
		return motherTelNumber;
	}
	public void setMotherTelNumber(String motherTelNumber) {
		this.motherTelNumber = motherTelNumber;
	}

	@Override
	public String toString() {
		return "BaseStudent [studentId=" + studentId + ", name=" + name + ", grade=" + grade + ", studentClass="
				+ studentClass + ", sex=" + sex + ", photoUrl=" + photoUrl + ", duty=" + duty + ", dormitory="
				+ dormitory + ", contactWay=" + contactWay + ", idCardNumber=" + idCardNumber + ", qqNumber=" + qqNumber
				+ ", email=" + email + ", birthday=" + birthday + ", height=" + height + ", major=" + major
				+ ", politicalStatus=" + politicalStatus + ", ethnicGroup=" + ethnicGroup + ", birthOrigin="
				+ birthOrigin + ", collegeEntranceExamScore=" + collegeEntranceExamScore
				+ ", collegeEntranceExamEnglishScore=" + collegeEntranceExamEnglishScore + ", entranceExamEnglishScore="
				+ entranceExamEnglishScore + ", hometownRailwayStation=" + hometownRailwayStation + ", province="
				+ province + ", city=" + city + ", familyAddress=" + familyAddress + ", familyTelNumber="
				+ familyTelNumber + ", postcode=" + postcode + ", specialty=" + specialty + ", dutyInHighSchool="
				+ dutyInHighSchool + ", isHadTechnologyCompetitionAward=" + isHadTechnologyCompetitionAward
				+ ", fatherName=" + fatherName + ", fatherWorkUnit=" + fatherWorkUnit + ", fatherWorkUnitAddress="
				+ fatherWorkUnitAddress + ", fatherDuty=" + fatherDuty + ", fatherPostcode=" + fatherPostcode
				+ ", fatherTelNumber=" + fatherTelNumber + ", motherName=" + motherName + ", motherWorkUnit="
				+ motherWorkUnit + ", motherWorkUnitAddress=" + motherWorkUnitAddress + ", motherDuty=" + motherDuty
				+ ", motherPostcode=" + motherPostcode + ", motherTelNumber=" + motherTelNumber + "]";
	}
	
	
	
}
