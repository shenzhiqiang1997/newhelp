package com.uestc.newhelp.entity;

public class HistoryArchiveVisibility {
	// 历史帮扶文档可见id
	private Long historyArchiveVisibilityId;
	// 教师id
	private String teacherId;
	// 历史帮扶文档id
	private Long historyArchiveId;
	
	public HistoryArchiveVisibility(String teacherId, Long historyArchiveId) {
		super();
		this.teacherId = teacherId;
		this.historyArchiveId = historyArchiveId;
	}
	
	public Long getHistoryArchiveVisibilityId() {
		return historyArchiveVisibilityId;
	}
	public void setHistoryArchiveVisibilityId(Long historyArchiveVisibilityId) {
		this.historyArchiveVisibilityId = historyArchiveVisibilityId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Long getHistoryArchiveId() {
		return historyArchiveId;
	}
	public void setHistoryArchiveId(Long historyArchiveId) {
		this.historyArchiveId = historyArchiveId;
	}
	
	
}
