package com.uestc.newhelp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

//��ʦ��Ӧʵ����
public class Teacher {
	//��ʦ�û���
	private String teacherId;
	//��ʦ����
	private String password;
	//��ʦ����
	private String name;
	//��ʦְ��
	private String duty;
	//��ʦ�꼶
	@JsonIgnore
	private Short grade;
	//��ʦ����
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
