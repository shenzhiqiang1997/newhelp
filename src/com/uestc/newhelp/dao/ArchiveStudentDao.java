package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.ArchiveStudent;
import com.uestc.newhelp.entity.Teacher;

public interface ArchiveStudentDao {
	//增加档案学生信息
	public void add(@Param("archiveStudent")ArchiveStudent archiveStudent);
	//删除指定档案学生信息
	public void delete(Long studentId);
	//更新指定档案学生信息
	public void update(@Param("archiveStudent")ArchiveStudent archiveStudent);
	//更新指定档案学生最后记录时间为null
	public void updateLastRecordTimeToNull(@Param("archiveStudent")ArchiveStudent archiveStudent);
	//查询指定档案学生信息
	public ArchiveStudent get(Long studentId);
	//查询指定教师的档案学生信息
	public List<ArchiveStudent> list(@Param("teacher")Teacher teacher);
	//搜索指定教师的档案学生信息
	public List<ArchiveStudent> search(@Param("teacher")Teacher teacher,@Param("archiveStudent")ArchiveStudent archiveStudent);
	//查看该学生是否已经被建档
	public String check(Long studentId);
}
