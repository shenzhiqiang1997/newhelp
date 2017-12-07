package com.uestc.newhelp.dto;

import com.uestc.newhelp.entity.BaseStudent;

public class BaseStudentWithPageParam {
	private BaseStudent baseStudent;
	private Page page;
	
	public BaseStudentWithPageParam(BaseStudent baseStudent, Page page) {
		super();
		this.baseStudent = baseStudent;
		this.page = page;
	}
	public BaseStudent getBaseStudent() {
		return baseStudent;
	}
	public void setBaseStudent(BaseStudent baseStudent) {
		this.baseStudent = baseStudent;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
}
