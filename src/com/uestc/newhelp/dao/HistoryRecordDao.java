package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.HistoryRecord;

public interface HistoryRecordDao {
	//����������ʷ������¼
	public void addBatch(@Param("historyRecords")List<HistoryRecord> historyRecords);
	//��ѯָ��������¼id����ʷ������¼
	public HistoryRecord get(Long historyRecordId);
	//��ѯָ����ʷ����id��ָ�����͵���ʷ������¼
	public List<HistoryRecord> listOnType(@Param("historyRecord")HistoryRecord historyRecord);
	
}
