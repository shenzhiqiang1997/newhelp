package com.uestc.newhelp.dto;

import java.util.List;

public class StudentIdsParam {
	private List<Long> studentIds;
	private String teacherId;
	
	public List<Long> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<Long> studentIds) {
		this.studentIds = studentIds;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	
	
}
