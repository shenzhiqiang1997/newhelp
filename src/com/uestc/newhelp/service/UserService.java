package com.uestc.newhelp.service;

import java.util.List;

import com.uestc.newhelp.dto.TeacherUpdatePasswordParam;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.NoAuthorityException;
import com.uestc.newhelp.exception.NoSuchUserException;
import com.uestc.newhelp.exception.PasswordErrorException;
//与用户有关的业务逻辑
public interface UserService {
	//登录验证
	public Teacher login (Teacher teacher)throws NoSuchUserException,PasswordErrorException;
	//登出
	public void logout(String teacherId);
	//更改教师密码
	public void updatePassword(TeacherUpdatePasswordParam teacherUpdatePasswordParam) throws NoSuchUserException,PasswordErrorException;
	//获得除当前用户外所有用户的姓名和用户名
	public List<Teacher> list(String teacherId);
	//后台登录验证
	public Teacher backendLogin(Teacher teacher) throws NoSuchUserException, PasswordErrorException, NoAuthorityException;
}
