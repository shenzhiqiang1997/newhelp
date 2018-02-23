package com.uestc.newhelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.uestc.newhelp.dao.LogDao;
import com.uestc.newhelp.entity.Log;
import com.uestc.newhelp.service.LogService;
@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogDao logDao;
	@Override
	public PageInfo<Log> list(int pageNum, int pageSize) {
		List<Log> logs=logDao.list(pageNum, pageSize);
		return new PageInfo<>(logs);
	}
	
	@Override
	public PageInfo<Log> search(Log log, int pageNum, int pageSize) {
		List<Log> logs=logDao.search(log, pageNum, pageSize);
		return new PageInfo<>(logs);
	}
	
}
