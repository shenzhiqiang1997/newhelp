package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.RecorderChange;

public interface RecorderChangeDao {
	//���ӱ����¼
	public void add(@Param("recorderChange")RecorderChange recorderChange);
	//ɾ��ָ��ѧ�������¼
	public void delete(Long studentId);
	//��ѯָ��ѧ���ı����¼
	public List<RecorderChange> list(Long studentId);
}
