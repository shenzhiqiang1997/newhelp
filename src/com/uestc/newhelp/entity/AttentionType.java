package com.uestc.newhelp.entity;
//关注类型对应的实体类
public class AttentionType {
	//关注类型id
	private Long attentionTypeId;
	//关注类型名称
	private String attentionTypeName;
	//该种关注类型提醒记录间隔
	private Byte remindRecordInterval;
	public Long getAttentionTypeId() {
		return attentionTypeId;
	}
	public void setAttentionTypeId(Long attentionTypeId) {
		this.attentionTypeId = attentionTypeId;
	}
	public String getAttentionTypeName() {
		return attentionTypeName;
	}
	public void setAttentionTypeName(String attentionTypeName) {
		this.attentionTypeName = attentionTypeName;
	}
	public Byte getRemindRecordInterval() {
		return remindRecordInterval;
	}
	public void setRemindRecordInterval(Byte remindRecordInterval) {
		this.remindRecordInterval = remindRecordInterval;
	}
	@Override
	public String toString() {
		return "AttentionType [attentionTypeId=" + attentionTypeId + ", attentionTypeName=" + attentionTypeName
				+ ", remindRecordInterval=" + remindRecordInterval + "]";
	}
	
}
