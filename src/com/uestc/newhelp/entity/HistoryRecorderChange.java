package com.uestc.newhelp.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
//��ʷ��¼�˱����¼��Ӧ��ʵ����
public class HistoryRecorderChange {
	//��ʷ��¼�˱����¼id
	private Long historyRecorderChangeId;
	//���ڵ���ʷ������id
	private Long historyArchiveId;
	//���ʱ��
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date changeTime;
	//�ּ�¼��
	private String recorderNow;
	//���ԭ��
	private String changeReason;
	
	public HistoryRecorderChange() {
		super();
	}
	
	

	public HistoryRecorderChange(Long historyArchiveId, RecorderChange recorderChange) {
		super();
		this.historyArchiveId = historyArchiveId;
		this.changeTime = recorderChange.getChangeTime();
		this.recorderNow = recorderChange.getRecorderNow();
		this.changeReason = recorderChange.getChangeReason();
	}



	public HistoryRecorderChange(Long historyRecorderChangeId, Long historyArchiveId, Date changeTime,
			String recorderNow, String changeReason) {
		super();
		this.historyRecorderChangeId = historyRecorderChangeId;
		this.historyArchiveId = historyArchiveId;
		this.changeTime = changeTime;
		this.recorderNow = recorderNow;
		this.changeReason = changeReason;
	}
	public Long getHistoryRecorderChangeId() {
		return historyRecorderChangeId;
	}
	public void setHistoryRecorderChangeId(Long historyRecorderChangeId) {
		this.historyRecorderChangeId = historyRecorderChangeId;
	}
	public Long getHistoryArchiveId() {
		return historyArchiveId;
	}
	public void setHistoryArchiveId(Long historyArchiveId) {
		this.historyArchiveId = historyArchiveId;
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
		return "HistoryRecorderChange [historyRecorderChangeId=" + historyRecorderChangeId + ", historyArchiveId="
				+ historyArchiveId + ", changeTime=" + changeTime + ", recorderNow=" + recorderNow + ", changeReason="
				+ changeReason + "]";
	}
	
	
}
