package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.HistoryRecorderChange;

public interface HistoryRecorderChangeDao {
	//����������ʷ��¼�����¼
	public void addBatch(@Param("historyRecorderChanges")List<HistoryRecorderChange> historyRecorderChanges);
	//��ѯָ����ʷ�����ļ�¼�����¼
	public List<HistoryRecorderChange> list(Long historyArchiveId);
	//����ɾ��ָ����ʷ����id�ļ�¼�˱仯��¼
	public void deleteBatch(@Param("historyArchiveIds")List<Long> historyArchiveIds);
}
