package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.AttentionType;

public interface AttentionTypeDao {
	//���ӹ�ע����
	public void add(@Param("attentionType")AttentionType attentionType);
	//ɾ��ָ����ע����id�Ĺ�ע����
	public void delete(Long attentionTypeId);
	//����ָ����ע����id�Ĺ�ע���
	public void update(@Param("attentionType")AttentionType attentionType);
	//��ѯ���й�ע����
	public List<AttentionType> list();
}
