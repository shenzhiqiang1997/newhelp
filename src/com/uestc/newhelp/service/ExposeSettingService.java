package com.uestc.newhelp.service;

import com.uestc.newhelp.entity.ExposeSetting;

//与教师设置学生暴露条目有关的业务逻辑
public interface ExposeSettingService {
	//更新指定教师的关于学生暴露条目设置
	public void update(ExposeSetting exposeSetting);
	//查询指定教师关于学生暴露条目设置
	public ExposeSetting get(String teacherId);
}
