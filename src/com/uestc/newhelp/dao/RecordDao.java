package com.uestc.newhelp.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.Record;

public interface RecordDao {
	//���Ӽ�¼
	public void add(@Param("record")Record record);
	//�������Ӽ�¼
	public void addBatch(@Param("records")List<Record> records);
	//ɾ��ָ������ѧ���ļ�¼
	public void deleteByStudentId(Long studentId);
	//����ɾ��ָ������ѧ���ļ�¼
	public void deleteBatch(@Param("recordIds")List<Long> recordIds);
	//����ָ������ѧ���ļ�¼
	public void update(@Param("record")Record record);
	//��ѯָ����¼
	public Record get(Long recordId);
	//��ѯָ������ѧ����ָ�����ͼ�¼
	public List<Record> listOnType(@Param("record")Record record);
	//��ѯָ������ѧ�������һ�μ�¼ʱ��
	public Date getLastRecordTime(Long studentId);
	//��ѯָ��id�ļ�¼
	public List<Record> listByIds(@Param("recordIds")List<Long> recordIds);
}
