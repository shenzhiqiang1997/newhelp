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
//与记录有关的业务逻辑
public interface RecordService {
	//查询记录列表
	public List<Record> list(Record record);
	//增加记录
	public void add(Record record);
	//批量增加记录
	public void addBatch(List<Record> records);
	//批量删除记录
	public void deleteBatch(List<Long> recordIds);
	//更新记录
	public void update(Record record);
	//查询指定记录详细信息
	public Record get(Long recordId);
	//从Excel文件中导入记录
	public void importRecordFromExcelFile(Record record,MultipartFile multipartFile) throws FileTypeNotMatchException, IOException,
	NoDataToImportException,NotPointOutRecordNameException,NotPointOutStudentIdException;
	//将指定的记录列表导出到Excel文件中
	public byte[] exportReocrdToExcelFile(String recordName,List<Long> recordIds) throws NotChoseExportObjectException,IOException,RecordTypeNotMatchException;
}
