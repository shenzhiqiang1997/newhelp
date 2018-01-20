package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.HistoryArchive;
import com.uestc.newhelp.entity.Teacher;

public interface HistoryArchiveDao {
	//������ʷ����ѧ����Ϣ
	public void add(@Param("historyArchive")HistoryArchive historyArchive);
	//��ѯָ����ʷ����id�ĵ���ѧ����Ϣ
	public HistoryArchive get(Long historyArchiveId);
	//��ѯָ����ʦ�û����ĵ���ѧ����Ϣ
	public List<HistoryArchive> list(@Param("teacher")Teacher teacher);
	//����ָ����ʦ��ָ�������ĵ���ѧ����Ϣ
	public List<HistoryArchive> search(@Param("teacher")Teacher teacher,@Param("historyArchive")HistoryArchive historyArchive);
}
