package com.uestc.newhelp.type;


public enum RecordType {
	WEEKLY_SIMPLE_RECORD("周联系简易记录表"),
	FAMILY_RECORD("家长联系记录表"),
	FACE_TALK_RECORD("面谈记录表"),
	DISCUSS_SUMMARY_RECORD("研讨及总结记录");
	public String value;
	RecordType(String value){
		this.value=value;
	}
	
}
