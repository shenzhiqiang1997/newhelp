package com.uestc.newhelp.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

//记录对应的实体类
public class Record{
	//记录表id
	private Long recordId;
	//记录所属档案学生学号
	private Long studentId;
	//记录表名称
	private String recordName;
	//记录时间
	@DateTimeFormat(iso=ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date recordTime;
	//地点
	private String location;
	//见证人
	private String witness;
	//记录人
	private String recorder;
	//参与人
	private String participant;
	//方式
	private String way;
	//主要内容
	private String content;
	//备注
	private String comment;
	
	
	public Record() {
		super();
	}
	
	public Record(Long studentId, String recordName) {
		super();
		this.studentId = studentId;
		this.recordName = recordName;
	}
	
	public Record(Long studentId, String recordName, Date recordTime, String location, String way, String content) {
		super();
		this.studentId = studentId;
		this.recordName = recordName;
		this.recordTime = recordTime;
		this.location = location;
		this.way = way;
		this.content = content;
	}
	

	

	public Record(Long studentId, String recordName, Date recordTime, String location, String way, String content,
			String comment) {
		super();
		this.studentId = studentId;
		this.recordName = recordName;
		this.recordTime = recordTime;
		this.location = location;
		this.way = way;
		this.content = content;
		this.comment = comment;
	}
	
	public Record(String recordName, Date recordTime, String location, String recorder,
			String participant, String content) {
		super();
		this.recordName = recordName;
		this.recordTime = recordTime;
		this.location = location;
		this.recorder = recorder;
		this.participant = participant;
		this.content = content;
	}

	public Record(Long studentId, String recordName, Date recordTime, String location, String witness, String recorder,
			String way, String content) {
		super();
		this.studentId = studentId;
		this.recordName = recordName;
		this.recordTime = recordTime;
		this.location = location;
		this.witness = witness;
		this.recorder = recorder;
		this.way = way;
		this.content = content;
	}
	
	public Record(Long recordId, Long studentId, String recordName, Date recordTime, String location, String witness,
			String recorder, String participant, String way, String content, String comment) {
		super();
		this.recordId = recordId;
		this.studentId = studentId;
		this.recordName = recordName;
		this.recordTime = recordTime;
		this.location = location;
		this.witness = witness;
		this.recorder = recorder;
		this.participant = participant;
		this.way = way;
		this.content = content;
		this.comment = comment;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getWitness() {
		return witness;
	}
	public void setWitness(String witness) {
		this.witness = witness;
	}
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	public String getParticipant() {
		return participant;
	}
	public void setParticipant(String participant) {
		this.participant = participant;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", studentId=" + studentId + ", recordName=" + recordName
				+ ", recordTime=" + recordTime + ", location=" + location + ", witness=" + witness + ", recorder="
				+ recorder + ", participant=" + participant + ", way=" + way + ", content=" + content + ", comment="
				+ comment + "]";
	}
		
}
