package com.uestc.newhelp.dao;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.Token;
//��token�йص����ݲ���
public interface TokenDao {
	//��ѯָ����ʦ��token
	public String get(String teacherId);
	//���token
	public void add(@Param("token")Token token);
	//ɾ��ָ����ʦ��token
	public void delete(String teacherId);
}
