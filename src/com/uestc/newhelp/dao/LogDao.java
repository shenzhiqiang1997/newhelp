package com.uestc.newhelp.dao;

import java.util.List;

import com.uestc.newhelp.entity.Log;


public interface LogDao {
	//获取日志
	public List<Log> get();
	//删除日志
	public void delete(Long logId);
	//添加日志
	public void add(Log log);
}
