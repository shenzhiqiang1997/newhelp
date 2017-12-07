package com.uestc.newhelp.dto;

public class TeacherUpdatePasswordParam {
	private String teacherId;
	private String oldPassword;
	private String newPassword;
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "TeacherUpdatePasswordParam [teacherId=" + teacherId + ", oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + "]";
	}
	
}
