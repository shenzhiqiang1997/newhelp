package com.uestc.newhelp.service;

import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.StillHasArchiveStudentException;

//与教师账户有关的业务逻辑
public interface TeacherService {
	//添加教师账号
	public void add(Teacher teacher);
	//删除教师账号
	public void delete(String teacherId) throws StillHasArchiveStudentException;
}
