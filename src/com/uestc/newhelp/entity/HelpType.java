package com.uestc.newhelp.entity;
//������Ͷ�Ӧ��ʵ����
public class HelpType {
	//�������id
	private Long helpTypeId;
	//�����������
	private String helpTypeName;
	
	public HelpType() {
		super();
	}
	public HelpType(String helpTypeName) {
		super();
		this.helpTypeName = helpTypeName;
	}
	public Long getHelpTypeId() {
		return helpTypeId;
	}
	public void setHelpTypeId(Long helpTypeId) {
		this.helpTypeId = helpTypeId;
	}
	public String getHelpTypeName() {
		return helpTypeName;
	}
	public void setHelpTypeName(String helpTypeName) {
		this.helpTypeName = helpTypeName;
	}
	@Override
	public String toString() {
		return "HelpType [helpTypeId=" + helpTypeId + ", helpTypeName=" + helpTypeName + "]";
	}
	
}
