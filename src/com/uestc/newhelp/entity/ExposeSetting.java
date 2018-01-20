package com.uestc.newhelp.entity;
//教师暴露设置对应的实体类
public class ExposeSetting {
	//暴露设置id
	private Long exposeSettingId;
	//暴露设置所属的教师用户名
	private String teacherId;
	//是否暴露学生学号
	private Byte exposeStudentId;
	//是否暴露学生姓名
	private Byte exposeName;
	//是否暴露学生年级
	private Byte exposeGrade;
	//是否暴露学生班级
	private Byte exposeStudentClass;
	//是否暴露学生性别
	private Byte exposeSex;
	//是否暴露学生职务
	private Byte exposeDuty;
	//是否暴露学生宿舍
	private Byte exposeDormitory;
	//是否暴露学生联系方式
	private Byte exposeContactWay;
	//是否暴露学生身份证号码
	private Byte exposeIdCardNumber;
	//是否暴露学生QQ号码
	private Byte exposeQqNumber;
	//是否暴露学生邮箱地址
	private Byte exposeEmail;
	//是否暴露学生生日
	private Byte exposeBirthday;
	//是否暴露学生身高
	private Byte exposeHeight;
	//是否暴露学生专业
	private Byte exposeMajor;
	//是否暴露学生学业状况
	private Byte exposeStudyCondition;
	//是否暴露学生政治面貌
	private Byte exposePoliticalStatus;
	//是否暴露学生民族
	private Byte exposeEthnicGroup;
	//是否暴露学生籍贯
	private Byte exposeBirthOrigin;
	//是否暴露学生高考成绩/满分
	private Byte exposeCollegeEntranceExamScore;
	//是否暴露学生高考英语成绩/满分
	private Byte exposeCollegeEntranceExamEnglishScore;
	//是否暴露学生英语入校考试成绩
	private Byte exposeEntranceExamEnglishScore;
	//是否暴露学生家乡所在火车站
	private Byte exposeHometownRailwayStation;
	//是否暴露学生省份
	private Byte exposeProvince;
	//是否暴露学生所在城市
	private Byte exposeCity;
	//是否暴露学生家庭详细地址
	private Byte exposeFamilyAddress;
	//是否暴露学生家庭电话
	private Byte exposeFamilyTelNumber;
	//是否暴露学生邮政编码
	private Byte exposePostcode;
	//是否暴露学生特长
	private Byte exposeSpecialty;
	//是否暴露学生高中曾任职务
	private Byte exposeDutyInHighSchool;
	//是否暴露学生高中曾获奖励
	private Byte exposeAwardInHighSchool;
	//是否暴露学生是否有科技竞赛类获奖
	private Byte exposeIsHadTechnologyCompetitionAward;
	//是否暴露学生父亲姓名
	private Byte exposeFatherName;
	//是否暴露学生父亲工作单位
	private Byte exposeFatherWorkUnit;
	//是否暴露学生父亲工作单位详细地址
	private Byte exposeFatherWorkUnitAddress;
	//是否暴露学生父亲职务
	private Byte exposeFatherDuty;
	//是否暴露学生父亲邮编
	private Byte exposeFatherPostcode;
	//是否暴露学生父亲电话
	private Byte exposeFatherTelNumber;
	//是否暴露学生母亲姓名
	private Byte exposeMotherName;
	//是否暴露学生母亲工作单位
	private Byte exposeMotherWorkUnit;
	//是否暴露学生母亲工作单位详细地址
	private Byte exposeMotherWorkUnitAddress;
	//是否暴露学生母亲职务
	private Byte exposeMotherDuty;
	//是否暴露学生母亲邮编
	private Byte exposeMotherPostcode;
	//是否暴露学生母亲电话
	private Byte exposeMotherTelNumber;
	
	
	public ExposeSetting() {
		super();
	}
	
	public ExposeSetting(Long exposeSettingId, String teacherId, Byte exposeStudentId, Byte exposeName,
			Byte exposeGrade, Byte exposeStudentClass, Byte exposeSex, Byte exposeDuty, Byte exposeDormitory,
			Byte exposeContactWay, Byte exposeIdCardNumber, Byte exposeQqNumber, Byte exposeEmail, Byte exposeBirthday,
			Byte exposeHeight, Byte exposeMajor, Byte exposeStudyCondition, Byte exposePoliticalStatus,
			Byte exposeEthnicGroup, Byte exposeBirthOrigin, Byte exposeCollegeEntranceExamScore,
			Byte exposeCollegeEntranceExamEnglishScore, Byte exposeEntranceExamEnglishScore,
			Byte exposeHometownRailwayStation, Byte exposeProvince, Byte exposeCity, Byte exposeFamilyAddress,
			Byte exposeFamilyTelNumber, Byte exposePostcode, Byte exposeSpecialty, Byte exposeDutyInHighSchool,
			Byte exposeAwardInHighSchool, Byte exposeIsHadTechnologyCompetitionAward, Byte exposeFatherName,
			Byte exposeFatherWorkUnit, Byte exposeFatherWorkUnitAddress, Byte exposeFatherDuty,
			Byte exposeFatherPostcode, Byte exposeFatherTelNumber, Byte exposeMotherName, Byte exposeMotherWorkUnit,
			Byte exposeMotherWorkUnitAddress, Byte exposeMotherDuty, Byte exposeMotherPostcode,
			Byte exposeMotherTelNumber) {
		super();
		this.exposeSettingId = exposeSettingId;
		this.teacherId = teacherId;
		this.exposeStudentId = exposeStudentId;
		this.exposeName = exposeName;
		this.exposeGrade = exposeGrade;
		this.exposeStudentClass = exposeStudentClass;
		this.exposeSex = exposeSex;
		this.exposeDuty = exposeDuty;
		this.exposeDormitory = exposeDormitory;
		this.exposeContactWay = exposeContactWay;
		this.exposeIdCardNumber = exposeIdCardNumber;
		this.exposeQqNumber = exposeQqNumber;
		this.exposeEmail = exposeEmail;
		this.exposeBirthday = exposeBirthday;
		this.exposeHeight = exposeHeight;
		this.exposeMajor = exposeMajor;
		this.exposeStudyCondition = exposeStudyCondition;
		this.exposePoliticalStatus = exposePoliticalStatus;
		this.exposeEthnicGroup = exposeEthnicGroup;
		this.exposeBirthOrigin = exposeBirthOrigin;
		this.exposeCollegeEntranceExamScore = exposeCollegeEntranceExamScore;
		this.exposeCollegeEntranceExamEnglishScore = exposeCollegeEntranceExamEnglishScore;
		this.exposeEntranceExamEnglishScore = exposeEntranceExamEnglishScore;
		this.exposeHometownRailwayStation = exposeHometownRailwayStation;
		this.exposeProvince = exposeProvince;
		this.exposeCity = exposeCity;
		this.exposeFamilyAddress = exposeFamilyAddress;
		this.exposeFamilyTelNumber = exposeFamilyTelNumber;
		this.exposePostcode = exposePostcode;
		this.exposeSpecialty = exposeSpecialty;
		this.exposeDutyInHighSchool = exposeDutyInHighSchool;
		this.exposeAwardInHighSchool = exposeAwardInHighSchool;
		this.exposeIsHadTechnologyCompetitionAward = exposeIsHadTechnologyCompetitionAward;
		this.exposeFatherName = exposeFatherName;
		this.exposeFatherWorkUnit = exposeFatherWorkUnit;
		this.exposeFatherWorkUnitAddress = exposeFatherWorkUnitAddress;
		this.exposeFatherDuty = exposeFatherDuty;
		this.exposeFatherPostcode = exposeFatherPostcode;
		this.exposeFatherTelNumber = exposeFatherTelNumber;
		this.exposeMotherName = exposeMotherName;
		this.exposeMotherWorkUnit = exposeMotherWorkUnit;
		this.exposeMotherWorkUnitAddress = exposeMotherWorkUnitAddress;
		this.exposeMotherDuty = exposeMotherDuty;
		this.exposeMotherPostcode = exposeMotherPostcode;
		this.exposeMotherTelNumber = exposeMotherTelNumber;
	}

	public Long getExposeSettingId() {
		return exposeSettingId;
	}
	public void setExposeSettingId(Long exposeSettingId) {
		this.exposeSettingId = exposeSettingId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Byte getExposeStudentId() {
		return exposeStudentId;
	}
	public void setExposeStudentId(Byte exposeStudentId) {
		this.exposeStudentId = exposeStudentId;
	}
	public Byte getExposeName() {
		return exposeName;
	}
	public void setExposeName(Byte exposeName) {
		this.exposeName = exposeName;
	}
	public Byte getExposeGrade() {
		return exposeGrade;
	}
	public void setExposeGrade(Byte exposeGrade) {
		this.exposeGrade = exposeGrade;
	}
	public Byte getExposeStudentClass() {
		return exposeStudentClass;
	}
	public void setExposeStudentClass(Byte exposeStudentClass) {
		this.exposeStudentClass = exposeStudentClass;
	}
	public Byte getExposeSex() {
		return exposeSex;
	}
	public void setExposeSex(Byte exposeSex) {
		this.exposeSex = exposeSex;
	}
	public Byte getExposeDuty() {
		return exposeDuty;
	}
	public void setExposeDuty(Byte exposeDuty) {
		this.exposeDuty = exposeDuty;
	}
	public Byte getExposeDormitory() {
		return exposeDormitory;
	}
	public void setExposeDormitory(Byte exposeDormitory) {
		this.exposeDormitory = exposeDormitory;
	}
	public Byte getExposeContactWay() {
		return exposeContactWay;
	}
	public void setExposeContactWay(Byte exposeContactWay) {
		this.exposeContactWay = exposeContactWay;
	}
	public Byte getExposeIdCardNumber() {
		return exposeIdCardNumber;
	}
	public void setExposeIdCardNumber(Byte exposeIdCardNumber) {
		this.exposeIdCardNumber = exposeIdCardNumber;
	}
	public Byte getExposeQqNumber() {
		return exposeQqNumber;
	}
	public void setExposeQqNumber(Byte exposeQqNumber) {
		this.exposeQqNumber = exposeQqNumber;
	}
	public Byte getExposeEmail() {
		return exposeEmail;
	}
	public void setExposeEmail(Byte exposeEmail) {
		this.exposeEmail = exposeEmail;
	}
	public Byte getExposeBirthday() {
		return exposeBirthday;
	}
	public void setExposeBirthday(Byte exposeBirthday) {
		this.exposeBirthday = exposeBirthday;
	}
	public Byte getExposeHeight() {
		return exposeHeight;
	}
	public void setExposeHeight(Byte exposeHeight) {
		this.exposeHeight = exposeHeight;
	}
	public Byte getExposeMajor() {
		return exposeMajor;
	}
	public void setExposeMajor(Byte exposeMajor) {
		this.exposeMajor = exposeMajor;
	}
	public Byte getExposeStudyCondition() {
		return exposeStudyCondition;
	}
	public void setExposeStudyCondition(Byte exposeStudyCondition) {
		this.exposeStudyCondition = exposeStudyCondition;
	}
	public Byte getExposePoliticalStatus() {
		return exposePoliticalStatus;
	}
	public void setExposePoliticalStatus(Byte exposePoliticalStatus) {
		this.exposePoliticalStatus = exposePoliticalStatus;
	}
	
	public Byte getExposeEthnicGroup() {
		return exposeEthnicGroup;
	}
	public void setExposeEthnicGroup(Byte exposeEthnicGroup) {
		this.exposeEthnicGroup = exposeEthnicGroup;
	}
	public Byte getExposeBirthOrigin() {
		return exposeBirthOrigin;
	}
	public void setExposeBirthOrigin(Byte exposeBirthOrigin) {
		this.exposeBirthOrigin = exposeBirthOrigin;
	}
	public Byte getExposeCollegeEntranceExamScore() {
		return exposeCollegeEntranceExamScore;
	}
	public void setExposeCollegeEntranceExamScore(Byte exposeCollegeEntranceExamScore) {
		this.exposeCollegeEntranceExamScore = exposeCollegeEntranceExamScore;
	}
	public Byte getExposeCollegeEntranceExamEnglishScore() {
		return exposeCollegeEntranceExamEnglishScore;
	}
	public void setExposeCollegeEntranceExamEnglishScore(Byte exposeCollegeEntranceExamEnglishScore) {
		this.exposeCollegeEntranceExamEnglishScore = exposeCollegeEntranceExamEnglishScore;
	}
	public Byte getExposeEntranceExamEnglishScore() {
		return exposeEntranceExamEnglishScore;
	}
	public void setExposeEntranceExamEnglishScore(Byte exposeEntranceExamEnglishScore) {
		this.exposeEntranceExamEnglishScore = exposeEntranceExamEnglishScore;
	}
	public Byte getExposeHometownRailwayStation() {
		return exposeHometownRailwayStation;
	}
	public void setExposeHometownRailwayStation(Byte exposeHometownRailwayStation) {
		this.exposeHometownRailwayStation = exposeHometownRailwayStation;
	}
	public Byte getExposeProvince() {
		return exposeProvince;
	}
	public void setExposeProvince(Byte exposeProvince) {
		this.exposeProvince = exposeProvince;
	}
	public Byte getExposeCity() {
		return exposeCity;
	}
	public void setExposeCity(Byte exposeCity) {
		this.exposeCity = exposeCity;
	}
	public Byte getExposeFamilyAddress() {
		return exposeFamilyAddress;
	}
	public void setExposeFamilyAddress(Byte exposeFamilyAddress) {
		this.exposeFamilyAddress = exposeFamilyAddress;
	}
	public Byte getExposeFamilyTelNumber() {
		return exposeFamilyTelNumber;
	}
	public void setExposeFamilyTelNumber(Byte exposeFamilyTelNumber) {
		this.exposeFamilyTelNumber = exposeFamilyTelNumber;
	}
	public Byte getExposePostcode() {
		return exposePostcode;
	}
	public void setExposePostcode(Byte exposePostcode) {
		this.exposePostcode = exposePostcode;
	}
	public Byte getExposeSpecialty() {
		return exposeSpecialty;
	}
	public void setExposeSpecialty(Byte exposeSpecialty) {
		this.exposeSpecialty = exposeSpecialty;
	}
	public Byte getExposeDutyInHighSchool() {
		return exposeDutyInHighSchool;
	}
	
	public Byte getExposeAwardInHighSchool() {
		return exposeAwardInHighSchool;
	}

	public void setExposeAwardInHighSchool(Byte exposeAwardInHighSchool) {
		this.exposeAwardInHighSchool = exposeAwardInHighSchool;
	}



	public void setExposeDutyInHighSchool(Byte exposeDutyInHighSchool) {
		this.exposeDutyInHighSchool = exposeDutyInHighSchool;
	}
	public Byte getExposeIsHadTechnologyCompetitionAward() {
		return exposeIsHadTechnologyCompetitionAward;
	}
	public void setExposeIsHadTechnologyCompetitionAward(Byte exposeIsHadTechnologyCompetitionAward) {
		this.exposeIsHadTechnologyCompetitionAward = exposeIsHadTechnologyCompetitionAward;
	}
	public Byte getExposeFatherName() {
		return exposeFatherName;
	}
	public void setExposeFatherName(Byte exposeFatherName) {
		this.exposeFatherName = exposeFatherName;
	}
	public Byte getExposeFatherWorkUnit() {
		return exposeFatherWorkUnit;
	}
	public void setExposeFatherWorkUnit(Byte exposeFatherWorkUnit) {
		this.exposeFatherWorkUnit = exposeFatherWorkUnit;
	}
	public Byte getExposeFatherWorkUnitAddress() {
		return exposeFatherWorkUnitAddress;
	}
	public void setExposeFatherWorkUnitAddress(Byte exposeFatherWorkUnitAddress) {
		this.exposeFatherWorkUnitAddress = exposeFatherWorkUnitAddress;
	}
	public Byte getExposeFatherDuty() {
		return exposeFatherDuty;
	}
	public void setExposeFatherDuty(Byte exposeFatherDuty) {
		this.exposeFatherDuty = exposeFatherDuty;
	}
	public Byte getExposeFatherPostcode() {
		return exposeFatherPostcode;
	}
	public void setExposeFatherPostcode(Byte exposeFatherPostcode) {
		this.exposeFatherPostcode = exposeFatherPostcode;
	}
	public Byte getExposeFatherTelNumber() {
		return exposeFatherTelNumber;
	}
	public void setExposeFatherTelNumber(Byte exposeFatherTelNumber) {
		this.exposeFatherTelNumber = exposeFatherTelNumber;
	}
	public Byte getExposeMotherName() {
		return exposeMotherName;
	}
	public void setExposeMotherName(Byte exposeMotherName) {
		this.exposeMotherName = exposeMotherName;
	}
	public Byte getExposeMotherWorkUnit() {
		return exposeMotherWorkUnit;
	}
	public void setExposeMotherWorkUnit(Byte exposeMotherWorkUnit) {
		this.exposeMotherWorkUnit = exposeMotherWorkUnit;
	}
	public Byte getExposeMotherWorkUnitAddress() {
		return exposeMotherWorkUnitAddress;
	}
	public void setExposeMotherWorkUnitAddress(Byte exposeMotherWorkUnitAddress) {
		this.exposeMotherWorkUnitAddress = exposeMotherWorkUnitAddress;
	}
	public Byte getExposeMotherDuty() {
		return exposeMotherDuty;
	}
	public void setExposeMotherDuty(Byte exposeMotherDuty) {
		this.exposeMotherDuty = exposeMotherDuty;
	}
	public Byte getExposeMotherPostcode() {
		return exposeMotherPostcode;
	}
	public void setExposeMotherPostcode(Byte exposeMotherPostcode) {
		this.exposeMotherPostcode = exposeMotherPostcode;
	}
	public Byte getExposeMotherTelNumber() {
		return exposeMotherTelNumber;
	}
	public void setExposeMotherTelNumber(Byte exposeMotherTelNumber) {
		this.exposeMotherTelNumber = exposeMotherTelNumber;
	}

	@Override
	public String toString() {
		return "ExposeSetting [exposeSettingId=" + exposeSettingId + ", teacherId=" + teacherId + ", exposeStudentId="
				+ exposeStudentId + ", exposeName=" + exposeName + ", exposeGrade=" + exposeGrade
				+ ", exposeStudentClass=" + exposeStudentClass + ", exposeSex=" + exposeSex + ", exposeDuty="
				+ exposeDuty + ", exposeDormitory=" + exposeDormitory + ", exposeContactWay=" + exposeContactWay
				+ ", exposeIdCardNumber=" + exposeIdCardNumber + ", exposeQqNumber=" + exposeQqNumber + ", exposeEmail="
				+ exposeEmail + ", exposeBirthday=" + exposeBirthday + ", exposeHeight=" + exposeHeight
				+ ", exposeMajor=" + exposeMajor + ", exposePoliticalStatus=" + exposePoliticalStatus
				+ ", exposeEthnicGroup=" + exposeEthnicGroup + ", exposeBirthOrigin=" + exposeBirthOrigin
				+ ", exposeCollegeEntranceExamScore=" + exposeCollegeEntranceExamScore
				+ ", exposeCollegeEntranceExamEnglishScore=" + exposeCollegeEntranceExamEnglishScore
				+ ", exposeEntranceExamEnglishScore=" + exposeEntranceExamEnglishScore
				+ ", exposeHometownRailwayStation=" + exposeHometownRailwayStation + ", exposeProvince="
				+ exposeProvince + ", exposeCity=" + exposeCity + ", exposeFamilyAddress=" + exposeFamilyAddress
				+ ", exposeFamilyTelNumber=" + exposeFamilyTelNumber + ", exposePostcode=" + exposePostcode
				+ ", exposeSpecialty=" + exposeSpecialty + ", exposeDutyInHighSchool=" + exposeDutyInHighSchool
				+ ", exposeIsHadTechnologyCompetitionAward=" + exposeIsHadTechnologyCompetitionAward
				+ ", exposeFatherName=" + exposeFatherName + ", exposeFatherWorkUnit=" + exposeFatherWorkUnit
				+ ", exposeFatherWorkUnitAddress=" + exposeFatherWorkUnitAddress + ", exposeFatherDuty="
				+ exposeFatherDuty + ", exposeFatherPostcode=" + exposeFatherPostcode + ", exposeFatherTelNumber="
				+ exposeFatherTelNumber + ", exposeMotherName=" + exposeMotherName + ", exposeMotherWorkUnit="
				+ exposeMotherWorkUnit + ", exposeMotherWorkUnitAddress=" + exposeMotherWorkUnitAddress
				+ ", exposeMotherDuty=" + exposeMotherDuty + ", exposeMotherPostcode=" + exposeMotherPostcode
				+ ", exposeMotherTelNumber=" + exposeMotherTelNumber + "]";
	}

	
}
