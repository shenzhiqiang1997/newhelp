package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.HistoryArchiveVisibility;

public interface HistoryArchiveVisibilityDao {
	// 增加教师的可见历史帮扶文档
	public void add(@Param("historyArchiveVisibility")HistoryArchiveVisibility historyArchiveVisibility);
	// 批量删除指定历史帮扶档案id的可见性
	public void deleteBatch(@Param("historyArchiveIds")List<Long> historyArchiveIds);
	// 查询指定教师的可见帮扶文档
	public List<Long> list(@Param("teacherId")String teacherId);
}
