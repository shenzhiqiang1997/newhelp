package com.uestc.newhelp.dao;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.ImportSetting;

public interface ImportSettingDao {
	//����ָ����ʦ�ĵ�������
	public void add(@Param("importSetting")ImportSetting importSetting);
	//����ָ����ʦ�ĵ�������
	public void update(@Param("importSetting")ImportSetting importSetting);
	//��ѯָ����ʦ�ĵ�������
	public ImportSetting get(String teacherId);
}
