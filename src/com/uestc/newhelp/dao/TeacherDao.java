package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.Teacher;

public interface TeacherDao {
	//查询指定教师用户密码
	public Teacher getPassword(String teacherId);
	//查询指定教师信息
	public Teacher getInfo(String teacherId);
	//查询指定教师权限
	public Byte  getAuthority(String teacherId);
	//查询除当前教师外所有教师的用户名和名字
	public List<Teacher> listTeacherIdAndName(String teacherId);
	//更新指定教师的密码
	public void updatePassword(@Param("teacher")Teacher teacher);
	
	//查询教师列表
	public List<Teacher> list();
	//删除指定教师
	public void delete(String teacherId);
	//更新指定教师
	public void update(@Param("teacher")Teacher teacher);
	//增加教师
	public void add(@Param("teacher")Teacher teacher);
}
