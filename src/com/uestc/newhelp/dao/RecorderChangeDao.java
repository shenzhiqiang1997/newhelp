package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.RecorderChange;

public interface RecorderChangeDao {
	//增加变更记录
	public void add(@Param("recorderChange")RecorderChange recorderChange);
	//删除指定学生变更记录
	public void delete(Long studentId);
	//查询指定学生的变更记录
	public List<RecorderChange> list(Long studentId);
}
