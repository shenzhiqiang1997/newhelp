package com.uestc.newhelp.entity;
//帮扶类型对应的实体类
public class HelpType {
	//帮扶类型id
	private Long helpTypeId;
	//帮扶类型名称
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
