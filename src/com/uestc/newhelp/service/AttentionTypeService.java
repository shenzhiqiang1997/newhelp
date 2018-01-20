package com.uestc.newhelp.service;


import java.util.List;

import com.uestc.newhelp.entity.AttentionType;
//与关注类型有关的业务逻辑
public interface AttentionTypeService {
	//查询关注类型列表
	public List<AttentionType> list();
	//增加关注类型
	public void add(AttentionType attentionType);
	//删除关注类型
	public void delete(Long attentionTypeId);
	//更新关注类型
	public void update(AttentionType attentionType);
}
