package com.uestc.newhelp.service;

import java.util.List;

import com.uestc.newhelp.entity.HistoryArchive;
//����ʷ���ѧ���йص�ҵ���߼�
public interface HistoryArchiveService {
	//��ѯ��ʷ���ѧ���б�
	public List<HistoryArchive> list(String teacherId);
	//������ʷ���ѧ���б�
	public List<HistoryArchive> search(HistoryArchive historyArchive);
	//��ѯָ����ʷ���ѧ����ϸ��Ϣ
	public HistoryArchive get(Long historyArchiveId);
	
}
