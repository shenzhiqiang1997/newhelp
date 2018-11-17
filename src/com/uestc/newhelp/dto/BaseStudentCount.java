package com.uestc.newhelp.dto;

public class BaseStudentCount {
	private Integer currentStuNum;
	private Integer suspendedStuNum;
	private Integer dropoutStuNum;
	private Integer male;
	private Integer female;
	
	
	public Integer getMale() {
		return male;
	}
	public Integer getFemale() {
		return female;
	}
	public void setMale(Integer male) {
		this.male = male;
	}
	public void setFemale(Integer female) {
		this.female = female;
	}
	public Integer getCurrentStuNum() {
		return currentStuNum;
	}
	public void setCurrentStuNum(Integer currentStuNum) {
		this.currentStuNum = currentStuNum;
	}
	public Integer getSuspendedStuNum() {
		return suspendedStuNum;
	}
	public void setSuspendedStuNum(Integer suspendedStuNum) {
		this.suspendedStuNum = suspendedStuNum;
	}
	public Integer getDropoutStuNum() {
		return dropoutStuNum;
	}
	public void setDropoutStuNum(Integer dropoutStuNum) {
		this.dropoutStuNum = dropoutStuNum;
	}
	
	
}
