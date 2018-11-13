package com.uestc.newhelp.dto;

import com.uestc.newhelp.entity.Teacher;

public class DropParam {
	private Long studentId;
	private Teacher teacher;
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
