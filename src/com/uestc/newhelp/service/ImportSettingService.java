package com.uestc.newhelp.service;

import com.uestc.newhelp.entity.ImportSetting;
import com.uestc.newhelp.exception.NoAuthorityException;

//���ʦ����ѧ��������Ŀ�йص�ҵ���߼�
public interface ImportSettingService {
	//����ָ����ʦ�Ĺ���ѧ��������Ŀ����
	public void update(ImportSetting importSetting) throws NoAuthorityException;
	//��ѯָ����ʦ����ѧ��������Ŀ����
	public ImportSetting get(String teacherId) throws NoAuthorityException;
}