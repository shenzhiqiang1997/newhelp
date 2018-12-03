package com.uestc.newhelp.service;

import java.io.IOException;
import java.util.List;


import com.uestc.newhelp.entity.ArchiveStudent;
import com.uestc.newhelp.exception.ArchiveStudentHadExistException;
import com.uestc.newhelp.exception.NoSuchStudentException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.exception.NotPointOutStudentIdException;

public interface ArchiveStudentService {
	//查询指定教师的帮扶学生列表
	public List<ArchiveStudent> list(String teacherId);
	//搜索指定教师帮扶学生列表
	public List<ArchiveStudent> search(ArchiveStudent archiveStudent);
	//增加帮扶学生
	public void add(ArchiveStudent archiveStudent)throws NotPointOutStudentIdException,NoSuchStudentException,ArchiveStudentHadExistException;
	//删除帮扶学生
	public void delete(ArchiveStudent archiveStudent);
	//更新帮扶学生
	public void update(ArchiveStudent archiveStudent);
	//查询帮扶学生详细信息
	public ArchiveStudent get(Long studentId);
	//导出指定学生整个档案到Word文件中
	public byte[] exportArchiveToWordFile(Long studentId)
			throws IOException, NotChoseExportObjectException, NoSuchStudentException, Exception;
}
