package com.uestc.newhelp.service;

import java.util.List;

import com.uestc.newhelp.entity.HistoryRecord;
//����ʷ�����йص�ҵ���߼�
public interface HistoryRecordService {
	//��ѯָ��ѧ����ʷ��¼�б�
	public List<HistoryRecord> list(HistoryRecord historyRecord);
	//��ѯָ����ʷ��¼��ϸ
	public HistoryRecord get(Long historyRecordId);
	
}
