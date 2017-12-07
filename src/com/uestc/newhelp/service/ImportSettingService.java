package com.uestc.newhelp.service;

import com.uestc.newhelp.entity.ImportSetting;
import com.uestc.newhelp.exception.NoAuthorityException;

//与教师设置学生导入条目有关的业务逻辑
public interface ImportSettingService {
	//更新指定教师的关于学生导入条目设置
	public void update(ImportSetting importSetting) throws NoAuthorityException;
	//查询指定教师关于学生导入条目设置
	public ImportSetting get(String teacherId) throws NoAuthorityException;
}