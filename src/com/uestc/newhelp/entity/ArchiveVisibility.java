package com.uestc.newhelp.entity;

public class ArchiveVisibility {
	// 帮扶文档可见id
	private Long archiveVisibilityId;
	// 教师id
	private String teacherId;
	// 帮扶文档id
	private Long archiveId;
	
	
	public ArchiveVisibility(String teacherId, Long archiveId) {
		super();
		this.teacherId = teacherId;
		this.archiveId = archiveId;
	}
	public Long getArchiveVisibilityId() {
		return archiveVisibilityId;
	}
	public void setArchiveVisibilityId(Long archiveVisibilityId) {
		this.archiveVisibilityId = archiveVisibilityId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Long getArchiveId() {
		return archiveId;
	}
	public void setArchiveId(Long archiveId) {
		this.archiveId = archiveId;
	}
	
	
}
