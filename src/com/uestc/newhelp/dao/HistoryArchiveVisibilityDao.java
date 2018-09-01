package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.HistoryArchiveVisibility;

public interface HistoryArchiveVisibilityDao {
	// ���ӽ�ʦ�Ŀɼ���ʷ����ĵ�
	public void add(@Param("historyArchiveVisibility")HistoryArchiveVisibility historyArchiveVisibility);
	// ����ɾ��ָ����ʷ�������id�Ŀɼ���
	public void deleteBatch(@Param("historyArchiveIds")List<Long> historyArchiveIds);
	// ��ѯָ����ʦ�Ŀɼ�����ĵ�
	public List<Long> list(@Param("teacherId")String teacherId);
}
