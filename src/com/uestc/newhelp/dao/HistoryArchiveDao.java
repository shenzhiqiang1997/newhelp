package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.HistoryArchive;

public interface HistoryArchiveDao {
	//增加历史档案学生信息
	public void add(@Param("historyArchive")HistoryArchive historyArchive);
	//查询指定历史档案id的档案学生信息
	public HistoryArchive get(Long historyArchiveId);
	//查询指定教师用户名的档案学生信息
	public List<HistoryArchive> list(String teacherId);
	//搜索指定教师的指定条件的档案学生信息
	public List<HistoryArchive> search(@Param("historyArchive")HistoryArchive historyArchive);
}
