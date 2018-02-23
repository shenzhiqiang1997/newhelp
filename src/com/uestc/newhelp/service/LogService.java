package com.uestc.newhelp.service;

import com.github.pagehelper.PageInfo;
import com.uestc.newhelp.entity.Log;

//日志有关业务逻辑
public interface LogService {
	//分页查看日志列表
	public PageInfo<Log> list(int pageNum,int pageSize);
	//分页搜索日志列表
	public PageInfo<Log> search(Log log,int pageNum,int pageSize);
}
