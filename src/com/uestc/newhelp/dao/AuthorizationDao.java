package com.uestc.newhelp.dao;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.Authorization;

public interface AuthorizationDao {
	//给指定教师建立权限
	public void add(@Param("teacherId")String teacherId);
	//查询指定教师的权限
	public Authorization get(@Param("teacherId")String teahcerId);
	//更新指定教师的权限
	public void update(@Param("authorization")Authorization authorization);
	//删除指定教师的权限
	public void delete(@Param("teacherId")String teacherId);
}
