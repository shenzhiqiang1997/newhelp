package com.uestc.newhelp.service;

import com.uestc.newhelp.entity.ExposeSetting;

//���ʦ����ѧ����¶��Ŀ�йص�ҵ���߼�
public interface ExposeSettingService {
	//����ָ����ʦ�Ĺ���ѧ����¶��Ŀ����
	public void update(ExposeSetting exposeSetting);
	//��ѯָ����ʦ����ѧ����¶��Ŀ����
	public ExposeSetting get(String teacherId);
}
