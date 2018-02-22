package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.Log;


public interface LogDao {
	//获取日志
	public List<Log> list();
	//搜索日志 用分页插件完成
	public List<Log> search(@Param("log")Log log,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize);
	//删除日志
	public void delete(Long logId);
	//添加日志
	public void add(@Param("log")Log log);
}
