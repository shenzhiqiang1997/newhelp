package com.uestc.newhelp.dao;

import java.util.List;

import com.uestc.newhelp.entity.Log;


public interface LogDao {
	//��ȡ��־
	public List<Log> get();
	//ɾ����־
	public void delete(Long logId);
	//�����־
	public void add(Log log);
}
