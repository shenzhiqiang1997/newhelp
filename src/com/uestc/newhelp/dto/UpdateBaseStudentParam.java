package com.uestc.newhelp.dto;

import com.uestc.newhelp.entity.BaseStudent;
import com.uestc.newhelp.entity.Teacher;

public class UpdateBaseStudentParam {
	private BaseStudent baseStudent;
	private Teacher teacher;
	public BaseStudent getBaseStudent() {
		return baseStudent;
	}
	public void setBaseStudent(BaseStudent baseStudent) {
		this.baseStudent = baseStudent;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
