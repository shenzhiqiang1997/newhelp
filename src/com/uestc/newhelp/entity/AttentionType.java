package com.uestc.newhelp.entity;
//��ע���Ͷ�Ӧ��ʵ����
public class AttentionType {
	//��ע����id
	private Long attentionTypeId;
	//��ע��������
	private String attentionTypeName;
	//���ֹ�ע�������Ѽ�¼���
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
