package com.uestc.newhelp.entity;

public class Token {
	private String teacherId;
	private String tokenValue;
	
	public Token() {
		super();
	}

	public Token(String teacherId, String tokenValue) {
		super();
		this.teacherId = teacherId;
		this.tokenValue = tokenValue;
	}

	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	
}
