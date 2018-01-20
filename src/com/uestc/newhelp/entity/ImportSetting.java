package com.uestc.newhelp.entity;
//��ʦ�������ö�Ӧ��ʵ����
public class ImportSetting {
	//��������id
	private Long importSettingId;
	//�������������Ľ�ʦ�û���
	private String teacherId;
	//�Ƿ���ѧ��ѧ��
	private Byte importStudentId;
	//�Ƿ���ѧ������
	private Byte importName;
	//�Ƿ���ѧ���꼶
	private Byte importGrade;
	//�Ƿ���ѧ���༶
	private Byte importStudentClass;
	//�Ƿ���ѧ���Ա�
	private Byte importSex;
	//�Ƿ���ѧ��ְ��
	private Byte importDuty;
	//�Ƿ���ѧ������
	private Byte importDormitory;
	//�Ƿ���ѧ����ϵ��ʽ
	private Byte importContactWay;
	//�Ƿ���ѧ�����֤����
	private Byte importIdCardNumber;
	//�Ƿ���ѧ��QQ����
	private Byte importQqNumber;
	//�Ƿ���ѧ�������ַ
	private Byte importEmail;
	//�Ƿ���ѧ������
	private Byte importBirthday;
	//�Ƿ���ѧ�����
	private Byte importHeight;
	//�Ƿ���ѧ��רҵ
	private Byte importMajor;
	//�Ƿ���ѧ��ѧҵ״��
	private Byte importStudyCondition;
	//�Ƿ���ѧ��������ò
	private Byte importPoliticalStatus;
	//�Ƿ���ѧ������
	private Byte importEthnicGroup;
	//�Ƿ���ѧ������
	private Byte importBirthOrigin;
	//�Ƿ���ѧ���߿��ɼ�/����
	private Byte importCollegeEntranceExamScore;
	//�Ƿ���ѧ���߿�Ӣ��ɼ�/����
	private Byte importCollegeEntranceExamEnglishScore;
	//�Ƿ���ѧ��Ӣ����У���Գɼ�
	private Byte importEntranceExamEnglishScore;
	//�Ƿ���ѧ���������ڻ�վ
	private Byte importHometownRailwayStation;
	//�Ƿ���ѧ��ʡ��
	private Byte importProvince;
	//�Ƿ���ѧ�����ڳ���
	private Byte importCity;
	//�Ƿ���ѧ����ͥ��ϸ��ַ
	private Byte importFamilyAddress;
	//�Ƿ���ѧ����ͥ�绰
	private Byte importFamilyTelNumber;
	//�Ƿ���ѧ����������
	private Byte importPostcode;
	//�Ƿ���ѧ���س�
	private Byte importSpecialty;
	//�Ƿ���ѧ����������ְ��
	private Byte importDutyInHighSchool;
	//�Ƿ���ѧ������������
	private Byte importAwardInHighSchool;
	//�Ƿ���ѧ���Ƿ��пƼ��������
	private Byte importIsHadTechnologyCompetitionAward;
	//�Ƿ���ѧ����������
	private Byte importFatherName;
	//�Ƿ���ѧ�����׹�����λ
	private Byte importFatherWorkUnit;
	//�Ƿ���ѧ�����׹�����λ��ϸ��ַ
	private Byte importFatherWorkUnitAddress;
	//�Ƿ���ѧ������ְ��
	private Byte importFatherDuty;
	//�Ƿ���ѧ�������ʱ�
	private Byte importFatherPostcode;
	//�Ƿ���ѧ�����׵绰
	private Byte importFatherTelNumber;
	//�Ƿ���ѧ��ĸ������
	private Byte importMotherName;
	//�Ƿ���ѧ��ĸ�׹�����λ
	private Byte importMotherWorkUnit;
	//�Ƿ���ѧ��ĸ�׹�����λ��ϸ��ַ
	private Byte importMotherWorkUnitAddress;
	//�Ƿ���ѧ��ĸ��ְ��
	private Byte importMotherDuty;
	//�Ƿ���ѧ��ĸ���ʱ�
	private Byte importMotherPostcode;
	//�Ƿ���ѧ��ĸ�׵绰
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
