package com.uestc.newhelp.dao;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.ExposeSetting;

public interface ExposeSettingDao {
	//����ָ����ʦ�ı�¶����
	public void add(@Param("exposeSetting")ExposeSetting exposeSetting);
	//����ָ����ʦ�ı�¶����
	public void update(@Param("exposeSetting")ExposeSetting exposeSetting);
	//��ѯָ����ʦ�ı�¶����
	public ExposeSetting get(String teacherId);
	
	
}
