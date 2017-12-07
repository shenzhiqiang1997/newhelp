package com.uestc.newhelp.dto;

import java.util.List;

import com.uestc.newhelp.entity.BaseStudent;

public class BaseStudentsWithPage {
	private List<BaseStudent> baseStudents;
	private Page page;
	public BaseStudentsWithPage(List<BaseStudent> baseStudents, Page page) {
		super();
		this.baseStudents = baseStudents;
		this.page = page;
	}
	public List<BaseStudent> getBaseStudents() {
		return baseStudents;
	}
	public void setBaseStudents(List<BaseStudent> baseStudents) {
		this.baseStudents = baseStudents;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "BaseStudentsWithPage [baseStudents=" + baseStudents + ", page=" + page + "]";
	}
	
}
