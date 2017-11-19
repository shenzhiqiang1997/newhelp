package com.uestc.newhelp.entity;

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
	//教师权限
	private Byte authority;
	//教师令牌
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
