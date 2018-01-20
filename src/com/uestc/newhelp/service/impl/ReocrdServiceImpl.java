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
		//��Ӽ�¼
		recordDao.add(record);
		//���¼�¼��������������¼ʱ��
		ArchiveStudent archiveStudent=new ArchiveStudent();
		archiveStudent.setStudentId(record.getStudentId());
		Date lastRecordTime=recordDao.getLastRecordTime(archiveStudent.getStudentId());
		archiveStudent.setLastRecordTime(lastRecordTime);
		archiveStudentDao.update(archiveStudent);
	}

	@Override
	public void addBatch(List<Record> records) {
		//�������Ӽ�¼
		recordDao.addBatch(records);
		//�����Ҫ��ӵ�,���¼�¼��������������¼ʱ��
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
		//�����Ҫɾ����
		if(recordIds.size()>0) {
			Long recordId=recordIds.get(0);
			Record record=recordDao.get(recordId);
			studentId=record.getStudentId();
		}
		//����ɾ����¼
		recordDao.deleteBatch(recordIds);
		if(studentId!=null) {
			//���¼�¼��������������¼ʱ��
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
		//���¼�¼��������������¼ʱ��
		
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
		//��ȡ�ļ�������
		String fileType=MultipartFileUtil.getType(multipartFile);
		//�������Ŀ���������׳��쳣
		if(!".xlsx".equals(fileType)) {
			throw new FileTypeNotMatchException("�뱣֤�ϴ����ļ���ʽΪ.xlsx");
		}
		
		List<Record> records=new ArrayList<>();
		//ʱ���ʽ�� ������ʽʱ��
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//����������
		XSSFWorkbook workbook=new XSSFWorkbook(multipartFile.getInputStream());
		//ֻ��ȡ��һ��sheet
		Sheet sheet=workbook.getSheetAt(0);
		//��ȡ�ӿͻ��˴����ļ�¼����
		String recordName=record.getRecordName();
		//��ȡ�ӿͻ��˴�����ѧ��
		Long studentId=record.getStudentId();
		//���û�л�ȡ����¼��������ѧ�����׳��쳣
		if(recordName==null) {
			workbook.close();
			throw new NotPointOutRecordNameException("��ָ��Ҫ�����������͵ļ�¼");
		}
		if(studentId==null) {
			workbook.close();
			throw new NotPointOutStudentIdException("��ָ��Ϊ�ĸ�ѧ�������¼");
		}
		//���ݱ��������Բ�ͬ�ı����ɨ��,��������Ӧ��bean,���뵽������
		try {
			if(recordName.equals("����ϵ���׼�¼��")) {
				for (Row row : sheet) {
					//������ͷ
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
			}else if(recordName.equals("�ҳ���ϵ��¼��")) {
				for (Row row : sheet) {
					//������ͷ
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
			}else if(recordName.equals("��̸��¼��")){
				for (Row row : sheet) {
					//������ͷ
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
			}else if(recordName.equals("���ּ��ܽ��¼")){
				for (Row row : sheet) {
					//������ͷ
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
			//���;�������˷��ı���ʽ,����ת���������Ȼ���׳��쳣,��ʱ�����û�����Ԫ������Ϊ�ı�����
			throw new IllegalStateException("��ȷ����¼����Ϣǰ,�����е�Ԫ��ĸ�ʽ����Ϊ�ı�����");
		}finally {
			workbook.close();
		}
		workbook.close();
		//�����������������,���׳��쳣
		if(records.size()>0) {
			recordDao.addBatch(records);
		}else {
			throw new NoDataToImportException("��ȷ�������Execl�������ݿ����ϴ�");
		}
		
	}
	
	@Override
	public byte[] exportReocrdToExcelFile(String recordName, List<Long> recordIds)
			throws NotChoseExportObjectException, IOException,RecordTypeNotMatchException {
		if(recordIds==null||recordIds.size()==0) {
			throw new NotChoseExportObjectException("��δѡ��Ҫ�����Ķ���,����ѡ����ٵ���");
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
			throw new RecordTypeNotMatchException("δƥ�䵽Ҫ�����ļ�¼����");
		}
		return body;
	}

}
