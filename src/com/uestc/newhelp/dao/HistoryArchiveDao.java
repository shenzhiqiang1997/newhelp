package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.HistoryArchive;
import com.uestc.newhelp.entity.Teacher;

public interface HistoryArchiveDao {
	//增加历史档案学生信息
	public void add(@Param("historyArchive")HistoryArchive historyArchive);
	//查询指定历史档案id的档案学生信息
	public HistoryArchive get(Long historyArchiveId);
	//查询指定教师用户名的档案学生信息
	public List<HistoryArchive> list(@Param("teacher")Teacher teacher);
	//搜索指定教师的指定条件的档案学生信息
	public List<HistoryArchive> search(@Param("teacher")Teacher teacher,@Param("historyArchive")HistoryArchive historyArchive);
}
