package com.uestc.newhelp.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

//����ѧ����Ӧ��ʵ����
public class BaseStudent {
	//ѧ��ѧ��
	private Long  studentId;
	//ѧ������
	private String name;
	//ѧ���꼶
	private Short grade;
	//ѧ���༶
	private Integer studentClass;
	//ѧ���Ա�
	private String sex;
	//ѧ����Ƭ
	private String photoUrl;
	//ѧ��ְ��
	private String duty;
	//ѧ������
	private String dormitory;
	//ѧ����ϵ��ʽ
	private String contactWay;
	//ѧ�����֤����
	private String idCardNumber;
	//ѧ��QQ����
	private Long qqNumber;
	//ѧ�������ַ
	private String email;
	//ѧ������
	@DateTimeFormat(iso=ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	//ѧ�����
	private Float height;
	//ѧ��רҵ
	private String major;
	//ѧ��������ò
	private String politicalStatus;
	//ѧ������
	private String ethnicGroup;
	//ѧ������
	private String birthOrigin;
	//ѧ���߿��ɼ�/����
	private String collegeEntranceExamScore;
	//ѧ���߿�Ӣ��ɼ�/����
	private String collegeEntranceExamEnglishScore;
	//ѧ��Ӣ����У���Գɼ�
	private Byte entranceExamEnglishScore;
	//ѧ���������ڻ�վ
	private String hometownRailwayStation;
	//ѧ��ʡ��
	private String province;
	//ѧ�����ڳ���
	private String city;
	//ѧ����ͥ��ϸ��ַ
	private String familyAddress;
	//ѧ����ͥ�绰
	private String familyTelNumber;
	//ѧ����������
	private String postcode;
	//ѧ���س�
	private String specialty;
	//ѧ����������ְ��
	private String dutyInHighSchool;
	//ѧ������������
	private String awardInHighSchool;
	//ѧ���Ƿ��пƼ��������
	private String isHadTechnologyCompetitionAward;
	//ѧ����������
	private String fatherName;
	//ѧ�����׹�����λ
	private String fatherWorkUnit;
	//ѧ�����׹�����λ��ϸ��ַ
	private String fatherWorkUnitAddress;
	//ѧ������ְ��
	private String fatherDuty;
	//ѧ�������ʱ�
	private String fatherPostcode;
	//ѧ�����׵绰
	private String fatherTelNumber;
	//ѧ��ĸ������
	private String motherName;
	//ѧ��ĸ�׹�����λ
	private String motherWorkUnit;
	//ѧ��ĸ�׹�����λ��ϸ��ַ
	private String motherWorkUnitAddress;
	//ѧ��ĸ��ְ��
	private String motherDuty;
	//ѧ��ĸ���ʱ�
	private String motherPostcode;
	//ѧ��ĸ�׵绰
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
