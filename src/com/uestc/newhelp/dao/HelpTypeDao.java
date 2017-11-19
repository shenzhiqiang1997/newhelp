package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.HelpType;

public interface HelpTypeDao {
	//增加帮扶类型
	public void add(@Param("helpType")HelpType helpType);
	//删除指定帮扶id的帮扶类型
	public void delete(Long helpTypeId);
	//查询所有帮扶类型
	public List<HelpType> list();
}
