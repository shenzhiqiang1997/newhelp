package com.uestc.newhelp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Page {
	@JsonIgnore
	private Integer currentPage;
	@JsonIgnore
	private Integer pageSize;
	private Integer recordNum;
	private Integer pageNum;
	@JsonIgnore
	private Integer start;
	
	public Page(Integer currentPage, Integer pageSize, Integer recordNum) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordNum = recordNum;
		setPage();
	}
	
	public Page(Integer currentPage, Integer pageSize) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	private void setPage() {
		if(pageSize<=0||pageSize>1000) {
			pageSize=1000;
		}
		pageNum=recordNum%pageSize==0?recordNum/pageSize:(recordNum/pageSize)+1;
		if(pageNum==0) {
			pageNum=1;
		}
		if(currentPage>pageNum) {
			currentPage=pageNum;
		}
		if(currentPage<=0) {
			currentPage=1;
		}
		start=(currentPage-1)*pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(Integer recordNum) {
		this.recordNum = recordNum;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", pageSize=" + pageSize + ", recordNum=" + recordNum + ", pageNum="
				+ pageNum + ", start=" + start + "]";
	}

	
	
}
