package com.uestc.newhelp.entity;

import java.util.Date;

public class Log {
	//��־id
	private Long logId;
	//����ʱ��
	private Date operateTime;
	//��������
	private String content;
	//�������
	private Byte result;
	//��Ϣ
	private String message;
	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Byte getResult() {
		return result;
	}
	public void setResult(Byte result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Log [logId=" + logId + ", operateTime=" + operateTime + ", content=" + content + ", result=" + result
				+ ", message=" + message + "]";
	}
	
	
}
