package com.uestc.newhelp.service;

import com.github.pagehelper.PageInfo;
import com.uestc.newhelp.entity.Log;

//��־�й�ҵ���߼�
public interface LogService {
	//��ҳ�鿴��־�б�
	public PageInfo<Log> list(int pageNum,int pageSize);
	//��ҳ������־�б�
	public PageInfo<Log> search(Log log,int pageNum,int pageSize);
}
