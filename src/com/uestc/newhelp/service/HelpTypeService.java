package com.uestc.newhelp.service;


import java.util.List;

import com.uestc.newhelp.entity.HelpType;
//���������йص�ҵ���߼�
public interface HelpTypeService {
	//��ѯ��������б�
	public List<HelpType> list();
	//���Ӱ������
	public void add(HelpType helpType);
	//ɾ���������
	public void delete(Long helpTypeId);
}
