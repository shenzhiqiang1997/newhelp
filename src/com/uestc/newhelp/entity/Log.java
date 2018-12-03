package com.uestc.newhelp.entity;

import java.util.Date;

public class Log {
	//日志id
	private Long logId;
	//操作人
	private String teacherId;
	//操作IP
	private String ip;
	//操作时间
	private Date operateTime;
	//操作内容
	private String content;
	//操作结果
	private Byte result;
	//消息
	private String message;
	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
