package com.uestc.newhelp.entity;

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
	//��ʦȨ��
	private Byte authority;
	//��ʦ����
	private String token;
	
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
	public Byte getAuthority() {
		return authority;
	}
	public void setAuthority(Byte authority) {
		this.authority = authority;
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
				+ ", authority=" + authority + "]";
	}
	
	
}
