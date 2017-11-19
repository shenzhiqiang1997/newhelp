package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.AttentionType;

public interface AttentionTypeDao {
	//增加关注类型
	public void add(@Param("attentionType")AttentionType attentionType);
	//删除指定关注类型id的关注类型
	public void delete(Long attentionTypeId);
	//更新指定关注类型id的关注间隔
	public void update(@Param("attentionType")AttentionType attentionType);
	//查询所有关注类型
	public List<AttentionType> list();
}
