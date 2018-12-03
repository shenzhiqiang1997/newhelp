package com.uestc.newhelp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

//档案学生对应的实体类
public class ArchiveStudent {
	//档案编号
	private Long archiveId;
	//档案学生学号
	private Long studentId;
	//档案教师用户名
	private String teacherId;
	//档案学生性别
	private String sex;
	//档案学生姓名
	private String name;
	//建档学生专业
	private String major;
	//档案学生年级
	private Short grade;
	//档案学生班级
	private Integer studentClass;
	//档案学生政治面貌
	private String politicalStatus;
	//档案学生民族
	private String ethnicGroup;
	//档案学生职务
	private String duty;
	//档案学生宿舍
	private String dormitory;
	//档案学生生源地
	private String birthOrigin;
	//档案学生家庭住址
	private String familyAddress;
	//档案学生联系方式
	private String contactWay;
	//档案学生家庭电话
	private String familyTelNumber;
	//档案学生父亲电话
	private String fatherTelNumber;
	//档案学生母亲电话
	private String motherTelNumber;
	//档案学生家庭情况
	private String familyCondition;
	//档案学生学习情况
	private String studyCondition;
	//档案学生身心情况
	private String healthCondition;
	//档案学生生活情况
	private String lifeCondition;
	//档案其他情况
	private String otherCondition;
	//建档依据
	private String bulidingBasis;
	//建档记录人
	private String bulidingRecorder;
	//建档时间
	@DateTimeFormat(iso=ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date bulidingTime;
	//除档依据
	private String destoryingBasis;
	//除档记录人
	private String destoryingRecorder;
	//除档时间
	@DateTimeFormat(iso=ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date destoryingTime;
	//建档人
	private String bulidingPerson;
	//建档人职务
	private String bulidingPersonDuty;
	//帮扶类型
	private String helpType;
	//关注状态
	private String attentionType;
	//该学生末次记录时间
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date lastRecordTime;
	//该学生是否高亮
	private Boolean highlight;
	private List<RecorderChange> recorderChanges;
	
	public ArchiveStudent() {
		super();
	}
	
	public ArchiveStudent(Long studentId, String teacherId, String sex, String name, String major, Short grade,
			Integer studentClass, String politicalStatus, String ethnicGroup, String duty, String dormitory,
			String birthOrigin, String familyAddress, String contactWay, String familyTelNumber, String fatherTelNumber,
			String motherTelNumber, String familyCondition, String studyCondition, String healthCondition,
			String lifeCondition, String otherCondition, String bulidingBasis, String bulidingRecorder,
			Date bulidingTime, String destoryingBasis, String destoryingRecorder, Date destoryingTime,
			String bulidingPerson, String bulidingPersonDuty, String helpType, String attentionType,
			Date lastRecordTime, Boolean highlight, List<RecorderChange> recorderChanges) {
		super();
		this.studentId = studentId;
		this.teacherId = teacherId;
		this.sex = sex;
		this.name = name;
		this.major = major;
		this.grade = grade;
		this.studentClass = studentClass;
		this.politicalStatus = politicalStatus;
		this.ethnicGroup = ethnicGroup;
		this.duty = duty;
		this.dormitory = dormitory;
		this.birthOrigin = birthOrigin;
		this.familyAddress = familyAddress;
		this.contactWay = contactWay;
		this.familyTelNumber = familyTelNumber;
		this.fatherTelNumber = fatherTelNumber;
		this.motherTelNumber = motherTelNumber;
		this.familyCondition = familyCondition;
		this.studyCondition = studyCondition;
		this.healthCondition = healthCondition;
		this.lifeCondition = lifeCondition;
		this.otherCondition = otherCondition;
		this.bulidingBasis = bulidingBasis;
		this.bulidingRecorder = bulidingRecorder;
		this.bulidingTime = bulidingTime;
		this.destoryingBasis = destoryingBasis;
		this.destoryingRecorder = destoryingRecorder;
		this.destoryingTime = destoryingTime;
		this.bulidingPerson = bulidingPerson;
		this.bulidingPersonDuty = bulidingPersonDuty;
		this.helpType = helpType;
		this.attentionType = attentionType;
		this.lastRecordTime = lastRecordTime;
		this.highlight = highlight;
		this.recorderChanges = recorderChanges;
	}

	public ArchiveStudent(Long studentId, String teacherId, String sex, String name, String major, Short grade,
			Integer studentClass, String ethnicGroup, String duty, String dormitory, String birthOrigin,
			String familyAddress, String contactWay, String familyTelNumber, String fatherTelNumber,
			String motherTelNumber, String familyCondition, String studyCondition, String healthCondition,
			String lifeCondition, String otherCondition, String bulidingBasis, String bulidingRecorder,
			Date bulidingTime, String destoryingBasis, String destoryingRecorder, Date destoryingTime,
			String bulidingPerson, String bulidingPersonDuty, String helpType, String attentionType,
			Date lastRecordTime, Boolean highlight) {
		super();
		this.studentId = studentId;
		this.teacherId = teacherId;
		this.sex = sex;
		this.name = name;
		this.major = major;
		this.grade = grade;
		this.studentClass = studentClass;
		this.ethnicGroup = ethnicGroup;
		this.duty = duty;
		this.dormitory = dormitory;
		this.birthOrigin = birthOrigin;
		this.familyAddress = familyAddress;
		this.contactWay = contactWay;
		this.familyTelNumber = familyTelNumber;
		this.fatherTelNumber = fatherTelNumber;
		this.motherTelNumber = motherTelNumber;
		this.familyCondition = familyCondition;
		this.studyCondition = studyCondition;
		this.healthCondition = healthCondition;
		this.lifeCondition = lifeCondition;
		this.otherCondition = otherCondition;
		this.bulidingBasis = bulidingBasis;
		this.bulidingRecorder = bulidingRecorder;
		this.bulidingTime = bulidingTime;
		this.destoryingBasis = destoryingBasis;
		this.destoryingRecorder = destoryingRecorder;
		this.destoryingTime = destoryingTime;
		this.bulidingPerson = bulidingPerson;
		this.bulidingPersonDuty = bulidingPersonDuty;
		this.helpType = helpType;
		this.attentionType = attentionType;
		this.lastRecordTime = lastRecordTime;
		this.highlight = highlight;
	}
	
	public ArchiveStudent(HistoryArchive historyArchive) {
		this.studentId = historyArchive.getStudentId();
		this.teacherId = historyArchive.getTeacherId();
		this.sex = historyArchive.getSex();
		this.name = historyArchive.getName();
		this.major = historyArchive.getMajor();
		this.grade = historyArchive.getGrade();
		this.studentClass = historyArchive.getStudentClass();
		this.politicalStatus = historyArchive.getPoliticalStatus();
		this.ethnicGroup = historyArchive.getEthnicGroup();
		this.duty = historyArchive.getDuty();
		this.dormitory = historyArchive.getDormitory();
		this.birthOrigin = historyArchive.getBirthOrigin();
		this.familyAddress = historyArchive.getFamilyAddress();
		this.contactWay = historyArchive.getContactWay();
		this.familyTelNumber = historyArchive.getFamilyTelNumber();
		this.fatherTelNumber = historyArchive.getFatherTelNumber();
		this.motherTelNumber = historyArchive.getMotherTelNumber();
		this.familyCondition = historyArchive.getFamilyCondition();
		this.studyCondition = historyArchive.getStudyCondition();
		this.healthCondition = historyArchive.getHealthCondition();
		this.lifeCondition = historyArchive.getLifeCondition();
		this.otherCondition = historyArchive.getOtherCondition();
		this.bulidingBasis = historyArchive.getBulidingBasis();
		this.bulidingRecorder = historyArchive.getBulidingRecorder();
		this.bulidingTime = historyArchive.getBulidingTime();
		this.destoryingBasis = historyArchive.getDestoryingBasis();
		this.destoryingRecorder = historyArchive.getDestoryingRecorder();
		this.destoryingTime = historyArchive.getDestoryingTime();
		this.bulidingPerson = historyArchive.getBulidingPerson();
		this.bulidingPersonDuty = historyArchive.getBulidingPersonDuty();
		this.helpType = historyArchive.getHelpType();
		this.attentionType = historyArchive.getAttentionType();
		this.lastRecordTime = historyArchive.getLastRecordTime();
		this.recorderChanges = new ArrayList<>();
		for (HistoryRecorderChange historyRecorderChange : historyArchive.getHistoryRecorderChanges()) {
			this.recorderChanges.add(new RecorderChange(historyRecorderChange));
		}

	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
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
	public String getEthnicGroup() {
		return ethnicGroup;
	}
	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
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
	public String getBirthOrigin() {
		return birthOrigin;
	}
	public void setBirthOrigin(String birthOrigin) {
		this.birthOrigin = birthOrigin;
	}
	public String getFamilyAddress() {
		return familyAddress;
	}
	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getFamilyTelNumber() {
		return familyTelNumber;
	}
	public void setFamilyTelNumber(String familyTelNumber) {
		this.familyTelNumber = familyTelNumber;
	}
	public String getFatherTelNumber() {
		return fatherTelNumber;
	}
	public void setFatherTelNumber(String fatherTelNumber) {
		this.fatherTelNumber = fatherTelNumber;
	}
	public String getMotherTelNumber() {
		return motherTelNumber;
	}
	public void setMotherTelNumber(String motherTelNumber) {
		this.motherTelNumber = motherTelNumber;
	}
	public String getFamilyCondition() {
		return familyCondition;
	}
	public void setFamilyCondition(String familyCondition) {
		this.familyCondition = familyCondition;
	}
	public String getStudyCondition() {
		return studyCondition;
	}
	public void setStudyCondition(String studyCondition) {
		this.studyCondition = studyCondition;
	}
	public String getHealthCondition() {
		return healthCondition;
	}
	public void setHealthCondition(String healthCondition) {
		this.healthCondition = healthCondition;
	}
	public String getLifeCondition() {
		return lifeCondition;
	}
	public void setLifeCondition(String lifeCondition) {
		this.lifeCondition = lifeCondition;
	}
	public String getOtherCondition() {
		return otherCondition;
	}
	public void setOtherCondition(String otherCondition) {
		this.otherCondition = otherCondition;
	}
	public String getBulidingBasis() {
		return bulidingBasis;
	}
	public void setBulidingBasis(String bulidingBasis) {
		this.bulidingBasis = bulidingBasis;
	}
	public String getBulidingRecorder() {
		return bulidingRecorder;
	}
	public void setBulidingRecorder(String bulidingRecorder) {
		this.bulidingRecorder = bulidingRecorder;
	}
	public Date getBulidingTime() {
		return bulidingTime;
	}
	public void setBulidingTime(Date bulidingTime) {
		this.bulidingTime = bulidingTime;
	}
	public String getDestoryingBasis() {
		return destoryingBasis;
	}
	public void setDestoryingBasis(String destoryingBasis) {
		this.destoryingBasis = destoryingBasis;
	}
	public String getDestoryingRecorder() {
		return destoryingRecorder;
	}
	public void setDestoryingRecorder(String destoryingRecorder) {
		this.destoryingRecorder = destoryingRecorder;
	}
	public Date getDestoryingTime() {
		return destoryingTime;
	}
	public void setDestoryingTime(Date destoryingTime) {
		this.destoryingTime = destoryingTime;
	}
	public String getBulidingPerson() {
		return bulidingPerson;
	}
	public void setBulidingPerson(String bulidingPerson) {
		this.bulidingPerson = bulidingPerson;
	}
	public String getBulidingPersonDuty() {
		return bulidingPersonDuty;
	}
	public void setBulidingPersonDuty(String bulidingPersonDuty) {
		this.bulidingPersonDuty = bulidingPersonDuty;
	}
	public String getHelpType() {
		return helpType;
	}
	public void setHelpType(String helpType) {
		this.helpType = helpType;
	}
	public String getAttentionType() {
		return attentionType;
	}
	public void setAttentionType(String attentionType) {
		this.attentionType = attentionType;
	}
	public Date getLastRecordTime() {
		return lastRecordTime;
	}
	public void setLastRecordTime(Date lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}
	public Boolean getHighlight() {
		return highlight;
	}
	public void setHighlight(Boolean highlight) {
		this.highlight = highlight;
	}
	
	public List<RecorderChange> getRecorderChanges() {
		return recorderChanges;
	}
	public void setRecorderChanges(List<RecorderChange> recorderChanges) {
		this.recorderChanges = recorderChanges;
	}
	
	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	
	public Long getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(Long archiveId) {
		this.archiveId = archiveId;
	}

	@Override
	public String toString() {
		return "ArchiveStudent [studentId=" + studentId + ", teacherId=" + teacherId + ", sex=" + sex + ", name=" + name
				+ ", major=" + major + ", grade=" + grade + ", studentClass=" + studentClass + ", ethnicGroup="
				+ ethnicGroup + ", duty=" + duty + ", dormitory=" + dormitory + ", birthOrigin=" + birthOrigin
				+ ", familyAddress=" + familyAddress + ", contactWay=" + contactWay + ", familyTelNumber="
				+ familyTelNumber + ", fatherTelNumber=" + fatherTelNumber + ", motherTelNumber=" + motherTelNumber
				+ ", familyCondition=" + familyCondition + ", studyCondition=" + studyCondition + ", healthCondition="
				+ healthCondition + ", lifeCondition=" + lifeCondition + ", otherCondition=" + otherCondition
				+ ", bulidingBasis=" + bulidingBasis + ", bulidingRecorder=" + bulidingRecorder + ", bulidingTime="
				+ bulidingTime + ", destoryingBasis=" + destoryingBasis + ", destoryingRecorder=" + destoryingRecorder
				+ ", destoryingTime=" + destoryingTime + ", bulidingPerson=" + bulidingPerson + ", bulidingPersonDuty="
				+ bulidingPersonDuty + ", helpType=" + helpType + ", attentionType=" + attentionType
				+ ", lastRecordTime=" + lastRecordTime + ", highlight=" + highlight + ", recorderChanges="
				+ recorderChanges + "]";
	}
	
	
}
