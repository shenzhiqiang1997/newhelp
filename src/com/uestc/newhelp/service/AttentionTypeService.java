package com.uestc.newhelp.service;


import java.util.List;

import com.uestc.newhelp.entity.AttentionType;
//���ע�����йص�ҵ���߼�
public interface AttentionTypeService {
	//��ѯ��ע�����б�
	public List<AttentionType> list();
	//���ӹ�ע����
	public void add(AttentionType attentionType);
	//ɾ����ע����
	public void delete(Long attentionTypeId);
	//���¹�ע����
	public void update(AttentionType attentionType);
}
