package com.uestc.newhelp.dao;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.ImportSetting;

public interface ImportSettingDao {
	//新增指定教师的导入设置
	public void add(@Param("importSetting")ImportSetting importSetting);
	//更新指定教师的导入设置
	public void update(@Param("importSetting")ImportSetting importSetting);
	//查询指定教师的导入设置
	public ImportSetting get(String teacherId);
}
