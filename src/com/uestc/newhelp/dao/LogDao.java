package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.Log;


public interface LogDao {
	//��ȡ��־
	public List<Log> list();
	//ɾ����־
	public void delete(Long logId);
	//�����־
	public void add(@Param("log")Log log);
}
