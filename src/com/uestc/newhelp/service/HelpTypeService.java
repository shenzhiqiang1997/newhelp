package com.uestc.newhelp.service;


import java.util.List;

import com.uestc.newhelp.entity.HelpType;
//与帮扶类型有关的业务逻辑
public interface HelpTypeService {
	//查询帮扶类型列表
	public List<HelpType> list();
	//增加帮扶类型
	public void add(HelpType helpType);
	//删除帮扶类型
	public void delete(Long helpTypeId);
}
