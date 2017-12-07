package com.uestc.newhelp.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

//��¼�˱����¼��Ӧ��ʵ����
public class RecorderChange {
	//�����¼id
	private Long recorderChangeId;
	//���ڵĵ���ѧ��ѧ��
	private Long studentId;
	//���ʱ��
	@DateTimeFormat(iso=ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date changeTime;
	//�ּ�¼��
	private String recorderNow;
	//���ԭ��
	private String changeReason;
	
	public Long getRecorderChangeId() {
		return recorderChangeId;
	}
	public void setRecorderChangeId(Long recorderChangeId) {
		this.recorderChangeId = recorderChangeId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Date getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	public String getRecorderNow() {
		return recorderNow;
	}
	public void setRecorderNow(String recorderNow) {
		this.recorderNow = recorderNow;
	}
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	@Override
	public String toString() {
		return "RecorderChange [recorderChangeId=" + recorderChangeId + ", studentId=" + studentId + ", changeTime="
				+ changeTime + ", recorderNow=" + recorderNow + ", changeReason=" + changeReason + "]";
	}
	
}
