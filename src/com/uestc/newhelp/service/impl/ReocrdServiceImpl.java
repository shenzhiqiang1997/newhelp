package com.uestc.newhelp.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uestc.newhelp.dao.ArchiveStudentDao;
import com.uestc.newhelp.dao.RecordDao;
import com.uestc.newhelp.entity.ArchiveStudent;
import com.uestc.newhelp.entity.Record;
import com.uestc.newhelp.exception.FileTypeNotMatchException;
import com.uestc.newhelp.exception.NoDataToImportException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.exception.NotPointOutRecordNameException;
import com.uestc.newhelp.exception.NotPointOutStudentIdException;
import com.uestc.newhelp.exception.RecordTypeNotMatchException;
import com.uestc.newhelp.path.FileName;
import com.uestc.newhelp.path.Path;
import com.uestc.newhelp.service.RecordService;
import com.uestc.newhelp.type.RecordType;
import com.uestc.newhelp.util.MultipartFileUtil;
import com.uestc.newhelp.util.POIUtil;
@Service
public class ReocrdServiceImpl implements RecordService {
	
	@Autowired
	private RecordDao recordDao;
	@Autowired
	private ArchiveStudentDao archiveStudentDao;
	
	@Override
	public List<Record> list(Record record) {
		List<Record> records=recordDao.listOnType(record);
		return records;
	}

	@Override
	public void add(Record record) {
		//添加记录
		recordDao.add(record);
		//更新记录所属档案的最后记录时间
		ArchiveStudent archiveStudent=new ArchiveStudent();
		archiveStudent.setStudentId(record.getStudentId());
		Date lastRecordTime=recordDao.getLastRecordTime(archiveStudent.getStudentId());
		archiveStudent.setLastRecordTime(lastRecordTime);
		archiveStudentDao.update(archiveStudent);
	}

	@Override
	public void addBatch(List<Record> records) {
		//批量增加记录
		recordDao.addBatch(records);
		//如果有要添加的,更新记录所属档案的最后记录时间
		if(records.size()>0) {
			Record record=records.get(0);
			ArchiveStudent archiveStudent=new ArchiveStudent();
			archiveStudent.setStudentId(record.getStudentId());
			Date lastRecordTime=recordDao.getLastRecordTime(archiveStudent.getStudentId());
			archiveStudent.setLastRecordTime(lastRecordTime);
		}
	}

	@Override
	public void deleteBatch(List<Long> recordIds) {
		Long studentId=null;
		//如果有要删除的
		if(recordIds.size()>0) {
			Long recordId=recordIds.get(0);
			Record record=recordDao.get(recordId);
			studentId=record.getStudentId();
		}
		//批量删除记录
		recordDao.deleteBatch(recordIds);
		if(studentId!=null) {
			//更新记录所属档案的最后记录时间
			ArchiveStudent archiveStudent=new ArchiveStudent();
			archiveStudent.setStudentId(studentId);
			Date lastRecordTime=recordDao.getLastRecordTime(archiveStudent.getStudentId());
			if(lastRecordTime!=null) {
				archiveStudent.setLastRecordTime(lastRecordTime);
				archiveStudentDao.update(archiveStudent);
			}else {
				archiveStudentDao.updateLastRecordTimeToNull(archiveStudent);
			}
		}

	}

	@Override
	public void update(Record record) {
		recordDao.update(record);
		//更新记录所属档案的最后记录时间
		
		record=recordDao.get(record.getRecordId());
		ArchiveStudent archiveStudent=new ArchiveStudent();
		archiveStudent.setStudentId(record.getStudentId());
		Date lastRecordTime=recordDao.getLastRecordTime(archiveStudent.getStudentId());
		archiveStudent.setLastRecordTime(lastRecordTime);
		archiveStudentDao.update(archiveStudent);
	}

	@Override
	public Record get(Long recordId) {
		Record record=recordDao.get(recordId);
		return record;
	}

	@Override
	public void importRecordFromExcelFile(Record record, MultipartFile multipartFile) throws FileTypeNotMatchException, IOException,NoDataToImportException,
	NotPointOutRecordNameException,NotPointOutStudentIdException{
		//获取文件的类型
		String fileType=MultipartFileUtil.getType(multipartFile);
		//如果不是目标类型则抛出异常
		if(!".xlsx".equals(fileType)) {
			throw new FileTypeNotMatchException("请保证上传的文件格式为.xlsx");
		}
		
		List<Record> records=new ArrayList<>();
		//时间格式器 用来格式时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//创建工作簿
		XSSFWorkbook workbook=new XSSFWorkbook(multipartFile.getInputStream());
		//只获取第一个sheet
		Sheet sheet=workbook.getSheetAt(0);
		//获取从客户端传来的记录表名
		String recordName=record.getRecordName();
		//获取从客户端传来的学号
		Long studentId=record.getStudentId();
		//如果没有获取到记录表名或者学号则抛出异常
		if(recordName==null) {
			workbook.close();
			throw new NotPointOutRecordNameException("请指明要导入哪种类型的记录");
		}
		if(studentId==null) {
			workbook.close();
			throw new NotPointOutStudentIdException("请指明为哪个学生导入记录");
		}
		//根据表名称来对不同的表进行扫描,并生成相应的bean,加入到集合中
		try {
			if(recordName.equals("周联系简易记录表")) {
				for (Row row : sheet) {
					//跳过表头
					if(row.getRowNum()==0) continue;
					Date recordTime;
					try {
						recordTime=sdf.parse(POIUtil.getStringCellValue(row, 0));
					} catch (ParseException e) {
						recordTime=null;
					}
					String location=POIUtil.getStringCellValue(row, 1);
					String way=POIUtil.getStringCellValue(row, 2);
					String content=POIUtil.getStringCellValue(row, 3);
					String comment=POIUtil.getStringCellValue(row, 4);
					Record r=new Record(studentId, recordName, recordTime, location, way, content, comment);
					records.add(r);
				}
			}else if(recordName.equals("家长联系记录表")) {
				for (Row row : sheet) {
					//跳过表头
					if(row.getRowNum()==0) continue;
					Date recordTime;
					try {
						recordTime=sdf.parse(POIUtil.getStringCellValue(row, 0));
					} catch (ParseException e) {
						recordTime=null;
					}
					String location=POIUtil.getStringCellValue(row, 1);
					String witness=POIUtil.getStringCellValue(row, 2);
					String way=POIUtil.getStringCellValue(row, 3);
					String content=POIUtil.getStringCellValue(row, 4);
					String recorder=POIUtil.getStringCellValue(row, 5);
					Record r=new Record(studentId, recordName, recordTime, location, witness, recorder, way, content);
					records.add(r);
				}	
			}else if(recordName.equals("面谈记录表")){
				for (Row row : sheet) {
					//跳过表头
					if(row.getRowNum()==0) continue;
					Date recordTime;
					try {
						recordTime=sdf.parse(POIUtil.getStringCellValue(row, 0));
					} catch (ParseException e) {
						recordTime=null;
					}
					String location=POIUtil.getStringCellValue(row, 1);
					String way=POIUtil.getStringCellValue(row, 2);
					String content=POIUtil.getStringCellValue(row, 3);
					String recorder=POIUtil.getStringCellValue(row, 4);
					Record r=new Record(studentId, recordName, recordTime, location, way, content);
					r.setRecorder(recorder);
					records.add(r);
				}
			}else if(recordName.equals("研讨及总结记录")){
				for (Row row : sheet) {
					//跳过表头
					if(row.getRowNum()==0) continue;
					Date recordTime;
					try {
						recordTime=sdf.parse(POIUtil.getStringCellValue(row, 0));
					} catch (ParseException e) {
						recordTime=null;
					}
					String location=POIUtil.getStringCellValue(row, 1);
					String participant=POIUtil.getStringCellValue(row, 2);
					String content=POIUtil.getStringCellValue(row, 3);
					String recorder=POIUtil.getStringCellValue(row, 4);
					Record r=new Record(recordName, recordTime, location, recorder, participant, content);
					r.setStudentId(studentId);
					records.add(r);
				}
			}
		} catch (IllegalStateException e) {
			//如果途中遇到了非文本格式,由于转换的问题必然会抛出异常,此时提醒用户将单元格设置为文本类型
			throw new IllegalStateException("请确保在录入信息前,将所有单元格的格式设置为文本类型");
		}finally {
			workbook.close();
		}
		workbook.close();
		//如果最后有数据无添加,则抛出异常
		if(records.size()>0) {
			recordDao.addBatch(records);
		}else {
			throw new NoDataToImportException("请确保导入的Execl中有数据可以上传");
		}
		
	}
	
	@Override
	public byte[] exportReocrdToExcelFile(String recordName, List<Long> recordIds)
			throws NotChoseExportObjectException, IOException,RecordTypeNotMatchException {
		if(recordIds==null||recordIds.size()==0) {
			throw new NotChoseExportObjectException("尚未选择要导出的对象,请先选择后再导出");
		}
		List<Record> records=recordDao.listByIds(recordIds);
		Map<Integer, Object[]> row=new HashMap<>();
		byte[] body = null;
		if(recordName.equals(RecordType.WEEKLY_SIMPLE_RECORD.value)) {
			for (int i = 0; i < records.size(); i++) {
				Record record=records.get(i);
				Object[] rowValues=new Object[] {record.getRecordTime(),record.getLocation(),
						record.getWay(),record.getContent(),record.getComment()};
				row.put(i+1, rowValues);
			}
			body=POIUtil.getExcelBytes(row, Path.TEMPLATE_BASE_PATH+FileName.WEEKLY_SIMPLE_RECORD_EXCEL_TEMPLATE_NAME);
		}else if(recordName.equals(RecordType.FAMILY_RECORD.value)) {
			for (int i = 0; i < records.size(); i++) {
				Record record=records.get(i);
				Object[] rowValues=new Object[] {record.getRecordTime(),record.getLocation(),
						record.getWitness(),record.getWay(),record.getContent(),record.getRecorder()};
				row.put(i+1, rowValues);
			}
			body=POIUtil.getExcelBytes(row, Path.TEMPLATE_BASE_PATH+FileName.FAMILY_RECORD_EXCEL_TEMPLATE_NAME);
		}else if(recordName.equals(RecordType.FACE_TALK_RECORD.value)) {
			for (int i = 0; i < records.size(); i++) {
				Record record=records.get(i);
				Object[] rowValues=new Object[] {record.getRecordTime(),record.getLocation(),
						record.getWay(),record.getContent(),record.getRecorder()};
				row.put(i+1, rowValues);
			}
			body=POIUtil.getExcelBytes(row, Path.TEMPLATE_BASE_PATH+FileName.FACE_TALK_RECORD_EXCEL_TEMPLATE_NAME);
		}else if(recordName.equals(RecordType.DISCUSS_SUMMARY_RECORD.value)) {
			for (int i = 0; i < records.size(); i++) {
				Record record=records.get(i);
				Object[] rowValues=new Object[] {record.getRecordTime(),record.getLocation(),
						record.getParticipant(),record.getContent(),record.getRecorder()};
				row.put(i+1, rowValues);
			}
			body=POIUtil.getExcelBytes(row, Path.TEMPLATE_BASE_PATH+FileName.DISCUSS_SUMMARY_RECORD_EXCEL_TEMPLATE_NAME);
		}
		if(body==null) {
			throw new RecordTypeNotMatchException("未匹配到要导出的记录类型");
		}
		return body;
	}

}
