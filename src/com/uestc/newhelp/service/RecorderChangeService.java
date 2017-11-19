package com.uestc.newhelp.service;

import com.uestc.newhelp.entity.RecorderChange;
//记录人变更有关的业务逻辑
public interface RecorderChangeService {
	//新增记录人变更记录
	public void add(RecorderChange recorderChange,String newTeacherId);
}
