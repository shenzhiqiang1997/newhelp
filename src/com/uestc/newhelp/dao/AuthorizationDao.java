package com.uestc.newhelp.dao;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.Authorization;

public interface AuthorizationDao {
	//��ָ����ʦ����Ȩ��
	public void add(@Param("teacherId")String teacherId);
	//��ѯָ����ʦ��Ȩ��
	public Authorization get(@Param("teacherId")String teahcerId);
	//����ָ����ʦ��Ȩ��
	public void update(@Param("authorization")Authorization authorization);
	//ɾ��ָ����ʦ��Ȩ��
	public void delete(@Param("teacherId")String teacherId);
}
