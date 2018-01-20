package com.uestc.newhelp.entity;
//教师导入设置对应的实体类
public class ImportSetting {
	//导入设置id
	private Long importSettingId;
	//导入设置所属的教师用户名
	private String teacherId;
	//是否导入学生学号
	private Byte importStudentId;
	//是否导入学生姓名
	private Byte importName;
	//是否导入学生年级
	private Byte importGrade;
	//是否导入学生班级
	private Byte importStudentClass;
	//是否导入学生性别
	private Byte importSex;
	//是否导入学生职务
	private Byte importDuty;
	//是否导入学生宿舍
	private Byte importDormitory;
	//是否导入学生联系方式
	private Byte importContactWay;
	//是否导入学生身份证号码
	private Byte importIdCardNumber;
	//是否导入学生QQ号码
	private Byte importQqNumber;
	//是否导入学生邮箱地址
	private Byte importEmail;
	//是否导入学生生日
	private Byte importBirthday;
	//是否导入学生身高
	private Byte importHeight;
	//是否导入学生专业
	private Byte importMajor;
	//是否导入学生学业状况
	private Byte importStudyCondition;
	//是否导入学生政治面貌
	private Byte importPoliticalStatus;
	//是否导入学生民族
	private Byte importEthnicGroup;
	//是否导入学生籍贯
	private Byte importBirthOrigin;
	//是否导入学生高考成绩/满分
	private Byte importCollegeEntranceExamScore;
	//是否导入学生高考英语成绩/满分
	private Byte importCollegeEntranceExamEnglishScore;
	//是否导入学生英语入校考试成绩
	private Byte importEntranceExamEnglishScore;
	//是否导入学生家乡所在火车站
	private Byte importHometownRailwayStation;
	//是否导入学生省份
	private Byte importProvince;
	//是否导入学生所在城市
	private Byte importCity;
	//是否导入学生家庭详细地址
	private Byte importFamilyAddress;
	//是否导入学生家庭电话
	private Byte importFamilyTelNumber;
	//是否导入学生邮政编码
	private Byte importPostcode;
	//是否导入学生特长
	private Byte importSpecialty;
	//是否导入学生高中曾任职务
	private Byte importDutyInHighSchool;
	//是否导入学生高中曾获奖励
	private Byte importAwardInHighSchool;
	//是否导入学生是否有科技竞赛类获奖
	private Byte importIsHadTechnologyCompetitionAward;
	//是否导入学生父亲姓名
	private Byte importFatherName;
	//是否导入学生父亲工作单位
	private Byte importFatherWorkUnit;
	//是否导入学生父亲工作单位详细地址
	private Byte importFatherWorkUnitAddress;
	//是否导入学生父亲职务
	private Byte importFatherDuty;
	//是否导入学生父亲邮编
	private Byte importFatherPostcode;
	//是否导入学生父亲电话
	private Byte importFatherTelNumber;
	//是否导入学生母亲姓名
	private Byte importMotherName;
	//是否导入学生母亲工作单位
	private Byte importMotherWorkUnit;
	//是否导入学生母亲工作单位详细地址
	private Byte importMotherWorkUnitAddress;
	//是否导入学生母亲职务
	private Byte importMotherDuty;
	//是否导入学生母亲邮编
	private Byte importMotherPostcode;
	//是否导入学生母亲电话
	private Byte importMotherTelNumber;
	
	
	public ImportSetting() {
		super();
	}
	
	public ImportSetting(Long importSettingId, String teacherId, Byte importStudentId, Byte importName,
			Byte importGrade, Byte importStudentClass, Byte importSex, Byte importDuty, Byte importDormitory,
			Byte importContactWay, Byte importIdCardNumber, Byte importQqNumber, Byte importEmail, Byte importBirthday,
			Byte importHeight, Byte importMajor, Byte importStudyCondition, Byte importPoliticalStatus,
			Byte importEthnicGroup, Byte importBirthOrigin, Byte importCollegeEntranceExamScore,
			Byte importCollegeEntranceExamEnglishScore, Byte importEntranceExamEnglishScore,
			Byte importHometownRailwayStation, Byte importProvince, Byte importCity, Byte importFamilyAddress,
			Byte importFamilyTelNumber, Byte importPostcode, Byte importSpecialty, Byte importDutyInHighSchool,
			Byte importAwardInHighSchool, Byte importIsHadTechnologyCompetitionAward, Byte importFatherName,
			Byte importFatherWorkUnit, Byte importFatherWorkUnitAddress, Byte importFatherDuty,
			Byte importFatherPostcode, Byte importFatherTelNumber, Byte importMotherName, Byte importMotherWorkUnit,
			Byte importMotherWorkUnitAddress, Byte importMotherDuty, Byte importMotherPostcode,
			Byte importMotherTelNumber) {
		super();
		this.importSettingId = importSettingId;
		this.teacherId = teacherId;
		this.importStudentId = importStudentId;
		this.importName = importName;
		this.importGrade = importGrade;
		this.importStudentClass = importStudentClass;
		this.importSex = importSex;
		this.importDuty = importDuty;
		this.importDormitory = importDormitory;
		this.importContactWay = importContactWay;
		this.importIdCardNumber = importIdCardNumber;
		this.importQqNumber = importQqNumber;
		this.importEmail = importEmail;
		this.importBirthday = importBirthday;
		this.importHeight = importHeight;
		this.importMajor = importMajor;
		this.importStudyCondition = importStudyCondition;
		this.importPoliticalStatus = importPoliticalStatus;
		this.importEthnicGroup = importEthnicGroup;
		this.importBirthOrigin = importBirthOrigin;
		this.importCollegeEntranceExamScore = importCollegeEntranceExamScore;
		this.importCollegeEntranceExamEnglishScore = importCollegeEntranceExamEnglishScore;
		this.importEntranceExamEnglishScore = importEntranceExamEnglishScore;
		this.importHometownRailwayStation = importHometownRailwayStation;
		this.importProvince = importProvince;
		this.importCity = importCity;
		this.importFamilyAddress = importFamilyAddress;
		this.importFamilyTelNumber = importFamilyTelNumber;
		this.importPostcode = importPostcode;
		this.importSpecialty = importSpecialty;
		this.importDutyInHighSchool = importDutyInHighSchool;
		this.importAwardInHighSchool = importAwardInHighSchool;
		this.importIsHadTechnologyCompetitionAward = importIsHadTechnologyCompetitionAward;
		this.importFatherName = importFatherName;
		this.importFatherWorkUnit = importFatherWorkUnit;
		this.importFatherWorkUnitAddress = importFatherWorkUnitAddress;
		this.importFatherDuty = importFatherDuty;
		this.importFatherPostcode = importFatherPostcode;
		this.importFatherTelNumber = importFatherTelNumber;
		this.importMotherName = importMotherName;
		this.importMotherWorkUnit = importMotherWorkUnit;
		this.importMotherWorkUnitAddress = importMotherWorkUnitAddress;
		this.importMotherDuty = importMotherDuty;
		this.importMotherPostcode = importMotherPostcode;
		this.importMotherTelNumber = importMotherTelNumber;
	}

	public Long getImportSettingId() {
		return importSettingId;
	}
	public void setImportSettingId(Long importSettingId) {
		this.importSettingId = importSettingId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Byte getImportStudentId() {
		return importStudentId;
	}
	public void setImportStudentId(Byte importStudentId) {
		this.importStudentId = importStudentId;
	}
	public Byte getImportName() {
		return importName;
	}
	public void setImportName(Byte importName) {
		this.importName = importName;
	}
	public Byte getImportGrade() {
		return importGrade;
	}
	public void setImportGrade(Byte importGrade) {
		this.importGrade = importGrade;
	}
	public Byte getImportStudentClass() {
		return importStudentClass;
	}
	public void setImportStudentClass(Byte importStudentClass) {
		this.importStudentClass = importStudentClass;
	}
	public Byte getImportSex() {
		return importSex;
	}
	public void setImportSex(Byte importSex) {
		this.importSex = importSex;
	}
	public Byte getImportDuty() {
		return importDuty;
	}
	public void setImportDuty(Byte importDuty) {
		this.importDuty = importDuty;
	}
	public Byte getImportDormitory() {
		return importDormitory;
	}
	public void setImportDormitory(Byte importDormitory) {
		this.importDormitory = importDormitory;
	}
	public Byte getImportContactWay() {
		return importContactWay;
	}
	public void setImportContactWay(Byte importContactWay) {
		this.importContactWay = importContactWay;
	}
	public Byte getImportIdCardNumber() {
		return importIdCardNumber;
	}
	public void setImportIdCardNumber(Byte importIdCardNumber) {
		this.importIdCardNumber = importIdCardNumber;
	}
	public Byte getImportQqNumber() {
		return importQqNumber;
	}
	public void setImportQqNumber(Byte importQqNumber) {
		this.importQqNumber = importQqNumber;
	}
	public Byte getImportEmail() {
		return importEmail;
	}
	public void setImportEmail(Byte importEmail) {
		this.importEmail = importEmail;
	}
	public Byte getImportBirthday() {
		return importBirthday;
	}
	public void setImportBirthday(Byte importBirthday) {
		this.importBirthday = importBirthday;
	}
	public Byte getImportHeight() {
		return importHeight;
	}
	public void setImportHeight(Byte importHeight) {
		this.importHeight = importHeight;
	}
	public Byte getImportMajor() {
		return importMajor;
	}
	public void setImportMajor(Byte importMajor) {
		this.importMajor = importMajor;
	}
	public Byte getImportStudyCondition() {
		return importStudyCondition;
	}
	public void setImportStudyCondition(Byte importStudyCondition) {
		this.importStudyCondition = importStudyCondition;
	}
	public Byte getImportPoliticalStatus() {
		return importPoliticalStatus;
	}
	public void setImportPoliticalStatus(Byte importPoliticalStatus) {
		this.importPoliticalStatus = importPoliticalStatus;
	}
	
	public Byte getImportEthnicGroup() {
		return importEthnicGroup;
	}
	public void setImportEthnicGroup(Byte importEthnicGroup) {
		this.importEthnicGroup = importEthnicGroup;
	}
	public Byte getImportBirthOrigin() {
		return importBirthOrigin;
	}
	public void setImportBirthOrigin(Byte importBirthOrigin) {
		this.importBirthOrigin = importBirthOrigin;
	}
	public Byte getImportCollegeEntranceExamScore() {
		return importCollegeEntranceExamScore;
	}
	public void setImportCollegeEntranceExamScore(Byte importCollegeEntranceExamScore) {
		this.importCollegeEntranceExamScore = importCollegeEntranceExamScore;
	}
	public Byte getImportCollegeEntranceExamEnglishScore() {
		return importCollegeEntranceExamEnglishScore;
	}
	public void setImportCollegeEntranceExamEnglishScore(Byte importCollegeEntranceExamEnglishScore) {
		this.importCollegeEntranceExamEnglishScore = importCollegeEntranceExamEnglishScore;
	}
	public Byte getImportEntranceExamEnglishScore() {
		return importEntranceExamEnglishScore;
	}
	public void setImportEntranceExamEnglishScore(Byte importEntranceExamEnglishScore) {
		this.importEntranceExamEnglishScore = importEntranceExamEnglishScore;
	}
	public Byte getImportHometownRailwayStation() {
		return importHometownRailwayStation;
	}
	public void setImportHometownRailwayStation(Byte importHometownRailwayStation) {
		this.importHometownRailwayStation = importHometownRailwayStation;
	}
	public Byte getImportProvince() {
		return importProvince;
	}
	public void setImportProvince(Byte importProvince) {
		this.importProvince = importProvince;
	}
	public Byte getImportCity() {
		return importCity;
	}
	public void setImportCity(Byte importCity) {
		this.importCity = importCity;
	}
	public Byte getImportFamilyAddress() {
		return importFamilyAddress;
	}
	public void setImportFamilyAddress(Byte importFamilyAddress) {
		this.importFamilyAddress = importFamilyAddress;
	}
	public Byte getImportFamilyTelNumber() {
		return importFamilyTelNumber;
	}
	public void setImportFamilyTelNumber(Byte importFamilyTelNumber) {
		this.importFamilyTelNumber = importFamilyTelNumber;
	}
	public Byte getImportPostcode() {
		return importPostcode;
	}
	public void setImportPostcode(Byte importPostcode) {
		this.importPostcode = importPostcode;
	}
	public Byte getImportSpecialty() {
		return importSpecialty;
	}
	public void setImportSpecialty(Byte importSpecialty) {
		this.importSpecialty = importSpecialty;
	}
	public Byte getImportDutyInHighSchool() {
		return importDutyInHighSchool;
	}
	
	public Byte getImportAwardInHighSchool() {
		return importAwardInHighSchool;
	}

	public void setImportAwardInHighSchool(Byte importAwardInHighSchool) {
		this.importAwardInHighSchool = importAwardInHighSchool;
	}



	public void setImportDutyInHighSchool(Byte importDutyInHighSchool) {
		this.importDutyInHighSchool = importDutyInHighSchool;
	}
	public Byte getImportIsHadTechnologyCompetitionAward() {
		return importIsHadTechnologyCompetitionAward;
	}
	public void setImportIsHadTechnologyCompetitionAward(Byte importIsHadTechnologyCompetitionAward) {
		this.importIsHadTechnologyCompetitionAward = importIsHadTechnologyCompetitionAward;
	}
	public Byte getImportFatherName() {
		return importFatherName;
	}
	public void setImportFatherName(Byte importFatherName) {
		this.importFatherName = importFatherName;
	}
	public Byte getImportFatherWorkUnit() {
		return importFatherWorkUnit;
	}
	public void setImportFatherWorkUnit(Byte importFatherWorkUnit) {
		this.importFatherWorkUnit = importFatherWorkUnit;
	}
	public Byte getImportFatherWorkUnitAddress() {
		return importFatherWorkUnitAddress;
	}
	public void setImportFatherWorkUnitAddress(Byte importFatherWorkUnitAddress) {
		this.importFatherWorkUnitAddress = importFatherWorkUnitAddress;
	}
	public Byte getImportFatherDuty() {
		return importFatherDuty;
	}
	public void setImportFatherDuty(Byte importFatherDuty) {
		this.importFatherDuty = importFatherDuty;
	}
	public Byte getImportFatherPostcode() {
		return importFatherPostcode;
	}
	public void setImportFatherPostcode(Byte importFatherPostcode) {
		this.importFatherPostcode = importFatherPostcode;
	}
	public Byte getImportFatherTelNumber() {
		return importFatherTelNumber;
	}
	public void setImportFatherTelNumber(Byte importFatherTelNumber) {
		this.importFatherTelNumber = importFatherTelNumber;
	}
	public Byte getImportMotherName() {
		return importMotherName;
	}
	public void setImportMotherName(Byte importMotherName) {
		this.importMotherName = importMotherName;
	}
	public Byte getImportMotherWorkUnit() {
		return importMotherWorkUnit;
	}
	public void setImportMotherWorkUnit(Byte importMotherWorkUnit) {
		this.importMotherWorkUnit = importMotherWorkUnit;
	}
	public Byte getImportMotherWorkUnitAddress() {
		return importMotherWorkUnitAddress;
	}
	public void setImportMotherWorkUnitAddress(Byte importMotherWorkUnitAddress) {
		this.importMotherWorkUnitAddress = importMotherWorkUnitAddress;
	}
	public Byte getImportMotherDuty() {
		return importMotherDuty;
	}
	public void setImportMotherDuty(Byte importMotherDuty) {
		this.importMotherDuty = importMotherDuty;
	}
	public Byte getImportMotherPostcode() {
		return importMotherPostcode;
	}
	public void setImportMotherPostcode(Byte importMotherPostcode) {
		this.importMotherPostcode = importMotherPostcode;
	}
	public Byte getImportMotherTelNumber() {
		return importMotherTelNumber;
	}
	public void setImportMotherTelNumber(Byte importMotherTelNumber) {
		this.importMotherTelNumber = importMotherTelNumber;
	}

	@Override
	public String toString() {
		return "ImportSetting [importSettingId=" + importSettingId + ", teacherId=" + teacherId + ", importStudentId="
				+ importStudentId + ", importName=" + importName + ", importGrade=" + importGrade
				+ ", importStudentClass=" + importStudentClass + ", importSex=" + importSex + ", importDuty="
				+ importDuty + ", importDormitory=" + importDormitory + ", importContactWay=" + importContactWay
				+ ", importIdCardNumber=" + importIdCardNumber + ", importQqNumber=" + importQqNumber + ", importEmail="
				+ importEmail + ", importBirthday=" + importBirthday + ", importHeight=" + importHeight
				+ ", importMajor=" + importMajor + ", importPoliticalStatus=" + importPoliticalStatus
				+ ", importEthnicGroup=" + importEthnicGroup + ", importBirthOrigin=" + importBirthOrigin
				+ ", importCollegeEntranceExamScore=" + importCollegeEntranceExamScore
				+ ", importCollegeEntranceExamEnglishScore=" + importCollegeEntranceExamEnglishScore
				+ ", importEntranceExamEnglishScore=" + importEntranceExamEnglishScore
				+ ", importHometownRailwayStation=" + importHometownRailwayStation + ", importProvince="
				+ importProvince + ", importCity=" + importCity + ", importFamilyAddress=" + importFamilyAddress
				+ ", importFamilyTelNumber=" + importFamilyTelNumber + ", importPostcode=" + importPostcode
				+ ", importSpecialty=" + importSpecialty + ", importDutyInHighSchool=" + importDutyInHighSchool
				+ ", importIsHadTechnologyCompetitionAward=" + importIsHadTechnologyCompetitionAward
				+ ", importFatherName=" + importFatherName + ", importFatherWorkUnit=" + importFatherWorkUnit
				+ ", importFatherWorkUnitAddress=" + importFatherWorkUnitAddress + ", importFatherDuty="
				+ importFatherDuty + ", importFatherPostcode=" + importFatherPostcode + ", importFatherTelNumber="
				+ importFatherTelNumber + ", importMotherName=" + importMotherName + ", importMotherWorkUnit="
				+ importMotherWorkUnit + ", importMotherWorkUnitAddress=" + importMotherWorkUnitAddress
				+ ", importMotherDuty=" + importMotherDuty + ", importMotherPostcode=" + importMotherPostcode
				+ ", importMotherTelNumber=" + importMotherTelNumber + "]";
	}

	
}
