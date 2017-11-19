package com.uestc.newhelp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.uestc.newhelp.entity.Record;
import com.uestc.newhelp.exception.FileTypeNotMatchException;
import com.uestc.newhelp.exception.NoDataToImportException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.exception.NotPointOutRecordNameException;
import com.uestc.newhelp.exception.NotPointOutStudentIdException;
import com.uestc.newhelp.exception.RecordTypeNotMatchException;
//���¼�йص�ҵ���߼�
public interface RecordService {
	//��ѯ��¼�б�
	public List<Record> list(Record record);
	//���Ӽ�¼
	public void add(Record record);
	//�������Ӽ�¼
	public void addBatch(List<Record> records);
	//����ɾ����¼
	public void deleteBatch(List<Long> recordIds);
	//���¼�¼
	public void update(Record record);
	//��ѯָ����¼��ϸ��Ϣ
	public Record get(Long recordId);
	//��Excel�ļ��е����¼
	public void importRecordFromExcelFile(Record record,MultipartFile multipartFile) throws FileTypeNotMatchException, IOException,
	NoDataToImportException,NotPointOutRecordNameException,NotPointOutStudentIdException;
	//��ָ���ļ�¼�б�����Excel�ļ���
	public byte[] exportReocrdToExcelFile(String recordName,List<Long> recordIds) throws NotChoseExportObjectException,IOException,RecordTypeNotMatchException;
}
