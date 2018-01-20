package com.uestc.newhelp.service;

import java.util.List;

import com.uestc.newhelp.entity.HistoryArchive;
//与历史帮扶学生有关的业务逻辑
public interface HistoryArchiveService {
	//查询历史帮扶学生列表
	public List<HistoryArchive> list(String teacherId);
	//搜索历史帮扶学生列表
	public List<HistoryArchive> search(HistoryArchive historyArchive);
	//查询指定历史帮扶学生详细信息
	public HistoryArchive get(Long historyArchiveId);
	
}
