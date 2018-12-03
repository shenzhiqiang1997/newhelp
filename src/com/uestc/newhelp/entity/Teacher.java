package com.uestc.newhelp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

//教师对应实体类
public class Teacher {
	//教师用户名
	private String teacherId;
	//教师密码
	private String password;
	//教师姓名
	private String name;
	//教师职务
	private String duty;
	//教师年级
	@JsonIgnore
	private Short grade;
	//教师令牌
	private String token;
	
	public Teacher() {
		super();
	}
	
	public Teacher(String teacherId, String password) {
		super();
		this.teacherId = teacherId;
		this.password = password;
	}

	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}	
	public Short getGrade() {
		return grade;
	}
	public void setGrade(Short grade) {
		this.grade = grade;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", password=" + password + ", name=" + name + ", duty=" + duty
				+ ", grade=" + grade + ", token=" + token + "]";
	}
	
	
}
