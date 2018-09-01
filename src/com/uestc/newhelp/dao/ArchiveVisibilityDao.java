package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.ArchiveVisibility;

public interface ArchiveVisibilityDao {
	// 增加教师的可见帮扶文档
	public void add(@Param("archiveVisibility")ArchiveVisibility archiveVisibility);
	// 删除教师对指定帮扶档案的可见性
	public void delete(@Param("archiveVisibility")ArchiveVisibility archiveVisibility);
	// 查询指定教师的可见帮扶文档
	public List<Long> list(@Param("teacherId")String teacherId);
}
