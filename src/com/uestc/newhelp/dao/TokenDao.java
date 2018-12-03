package com.uestc.newhelp.dao;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.Token;
//有token有关的数据操作
public interface TokenDao {
	//查询指定教师的token
	public String get(String teacherId);
	//添加token
	public void add(@Param("token")Token token);
	//删除指定教师的token
	public void delete(String teacherId);
}
