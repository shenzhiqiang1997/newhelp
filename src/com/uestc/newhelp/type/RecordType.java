package com.uestc.newhelp.type;


public enum RecordType {
	WEEKLY_SIMPLE_RECORD("����ϵ���׼�¼��"),
	FAMILY_RECORD("�ҳ���ϵ��¼��"),
	FACE_TALK_RECORD("��̸��¼��"),
	DISCUSS_SUMMARY_RECORD("���ּ��ܽ��¼");
	public String value;
	RecordType(String value){
		this.value=value;
	}
	
}
