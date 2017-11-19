package com.uestc.newhelp.dao;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.ExposeSetting;

public interface ExposeSettingDao {
	//新增指定教师的暴露设置
	public void add(@Param("exposeSetting")ExposeSetting exposeSetting);
	//更新指定教师的暴露设置
	public void update(@Param("exposeSetting")ExposeSetting exposeSetting);
	//查询指定教师的暴露设置
	public ExposeSetting get(String teacherId);
	
	
}
