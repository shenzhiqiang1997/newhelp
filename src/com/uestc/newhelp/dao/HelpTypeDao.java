package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.HelpType;

public interface HelpTypeDao {
	//���Ӱ������
	public void add(@Param("helpType")HelpType helpType);
	//ɾ��ָ�����id�İ������
	public void delete(Long helpTypeId);
	//��ѯ���а������
	public List<HelpType> list();
}
