package com.uestc.newhelp.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

//记录人变更记录对应的实体类
public class RecorderChange {
	//变更记录id
	private Long recorderChangeId;
	//属于的档案学生学号
	private Long studentId;
	//变更时间
	@DateTimeFormat(iso=ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date changeTime;
	//现记录人
	private String recorderNow;
	//变更原因
	private String changeReason;
	
	public RecorderChange() {}
	
	public RecorderChange(HistoryRecorderChange historyRecorderChange) {
		this.changeTime = historyRecorderChange.getChangeTime();
		this.recorderNow = historyRecorderChange.getRecorderNow();
		this.changeReason = historyRecorderChange.getChangeReason();
	}
	
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
