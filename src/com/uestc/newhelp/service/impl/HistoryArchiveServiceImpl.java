package com.uestc.newhelp.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.constant.Constant;
import com.uestc.newhelp.constant.ReplaceMark;
import com.uestc.newhelp.dao.HistoryArchiveDao;
import com.uestc.newhelp.dao.HistoryArchiveVisibilityDao;
import com.uestc.newhelp.dao.HistoryRecordDao;
import com.uestc.newhelp.dao.HistoryRecorderChangeDao;
import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.entity.ArchiveStudent;
import com.uestc.newhelp.entity.HistoryArchive;
import com.uestc.newhelp.entity.HistoryRecord;
import com.uestc.newhelp.entity.HistoryRecorderChange;
import com.uestc.newhelp.entity.Record;
import com.uestc.newhelp.entity.RecorderChange;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.NoSuchStudentException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.path.FileName;
import com.uestc.newhelp.path.Path;
import com.uestc.newhelp.service.HistoryArchiveService;
import com.uestc.newhelp.type.RecordType;
import com.uestc.newhelp.util.POIUtil;
@Service
public class HistoryArchiveServiceImpl implements HistoryArchiveService {
	@Autowired
	private HistoryArchiveDao historyArchiveDao;
	@Autowired
	private HistoryRecordDao historyRecordDao;
	@Autowired
	private HistoryRecorderChangeDao historyRecorderChangeDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private HistoryArchiveVisibilityDao historyArchiveVisibilityDao;
	
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public List<HistoryArchive> list(String teacherId) {
		//获取老师信息
		Teacher teacher=teacherDao.getInfo(teacherId);
		teacher.setTeacherId(teacherId);
		List<HistoryArchive> historyArchives=historyArchiveDao.list(teacher);
		return historyArchives;
	}

	@Override
	public HistoryArchive get(Long historyArchiveId) {
		HistoryArchive historyArchive=historyArchiveDao.get(historyArchiveId);
		List<HistoryRecorderChange> historyRecorderChanges=historyRecorderChangeDao.list(historyArchiveId);
		historyArchive.setHistoryRecorderChanges(historyRecorderChanges);
		return historyArchive;
	}

	@Override
	public List<HistoryArchive> search(HistoryArchive historyArchive) {
		//获取老师信息
		Teacher teacher=teacherDao.getInfo(historyArchive.getTeacherId());
		teacher.setTeacherId(historyArchive.getTeacherId());
		List<HistoryArchive> historyArchives=historyArchiveDao.search(teacher,historyArchive);
		return historyArchives;
	}

	@Override
	public void deleteBatch(List<Long> historyArchiveIds) {
		if(historyArchiveIds!=null&&historyArchiveIds.size()>0) {
			historyArchiveDao.deleteBatch(historyArchiveIds);
			historyRecordDao.deleteBatch(historyArchiveIds);
			historyRecorderChangeDao.deleteBatch(historyArchiveIds);
			historyArchiveVisibilityDao.deleteBatch(historyArchiveIds);
		}
	
	}

	@Override
	public byte[] exportHistoryArchiveToWordFile(Long historyArchiveId) throws IOException,NotChoseExportObjectException,NoSuchStudentException,Exception {
		if(historyArchiveId==null) {
			throw new NotChoseExportObjectException("尚未选择要导出的对象,请先选择后再导出");
		}
		
		HistoryArchive historyArchive=historyArchiveDao.get(historyArchiveId);
		
		if(historyArchive==null) {
			throw new NoSuchStudentException("要导出的历史档案不存在！");
		}
		
		List<HistoryRecorderChange> historyRecorderChanges=historyRecorderChangeDao.list(historyArchiveId); 
		historyArchive.setHistoryRecorderChanges(historyRecorderChanges);
		
		//查询历史周联系简易记录表
		HistoryRecord historyRecord=new HistoryRecord(historyArchiveId, RecordType.WEEKLY_SIMPLE_RECORD.value);
		List<HistoryRecord> historyWeeklyReocrds=historyRecordDao.listOnType(historyRecord);
		//查询历史家长联系记录表
		historyRecord.setRecordName(RecordType.FAMILY_RECORD.value);
		List<HistoryRecord> historyFamilyRecords=historyRecordDao.listOnType(historyRecord);
		//查询历史面谈记录表
		historyRecord.setRecordName(RecordType.FAMILY_RECORD.value);
		List<HistoryRecord> historyFaceRecords=historyRecordDao.listOnType(historyRecord);
		//查询历史研讨及总结记录
		historyRecord.setRecordName(RecordType.DISCUSS_SUMMARY_RECORD.value);
		List<HistoryRecord> historyDiscussRecords=historyRecordDao.listOnType(historyRecord);
		
		//由历史档案构建学生档案
		ArchiveStudent archiveStudent=new ArchiveStudent(historyArchive);
		
		//由历史周联系简易记录表构建周联系简易记录表
		List<Record> weeklyReocrds=new ArrayList<>();
		for(HistoryRecord h:historyWeeklyReocrds) {
			weeklyReocrds.add(new Record(h));
		}
		//由历史家长联系记录表构建家长联系记录表
		List<Record> familyRecords=new ArrayList<>();
		for(HistoryRecord h:historyFamilyRecords) {
			familyRecords.add(new Record(h));
		}
		//由历史面谈记录表构建面谈记录表
		List<Record> faceRecords=new ArrayList<>();
		for(HistoryRecord h:historyFaceRecords) {
			faceRecords.add(new Record(h));
		}
		//由历史研讨及总结记录构建研讨及总结记录
		List<Record> discussRecords=new ArrayList<>();
		for(HistoryRecord h:historyDiscussRecords) {
			discussRecords.add(new Record(h));
		}
		
		//填充封面docx
		XWPFDocument originDocument=getArchiveCoverDocument(archiveStudent);
		//填充学生基本信息docx
		XWPFDocument baseStudentInfoDocument=getBaseStudentInfoDocument(archiveStudent);
		//填充建档信息docx
		XWPFDocument bulidingArchiveDocument=getBulidingArchiveDocument(archiveStudent);
		
		//拼接封面和学生基本信息
		POIUtil.mergeDocx(originDocument, baseStudentInfoDocument);
		//继续拼接建档信息
		POIUtil.mergeDocx(originDocument, bulidingArchiveDocument);
		
		//如果有周简易联系简易记录
		if(weeklyReocrds.size()!=0) {
			//计算周简易联系简易记录页数
			int weeklyRecordNum=weeklyReocrds.size()%7==0?weeklyReocrds.size()/7:weeklyReocrds.size()/7+1;
			//根据页数取每周联系记录
			for (int i = 0; i < weeklyRecordNum; i++) {
				List<Record> weeklyRecords;
				if((i+1)*Constant.WEEKLY_RECORD_PAGE_SIZE<=weeklyReocrds.size()) {
					weeklyRecords=weeklyReocrds.subList(i*Constant.WEEKLY_RECORD_PAGE_SIZE, (i+1)*Constant.WEEKLY_RECORD_PAGE_SIZE);
				}else {
					weeklyRecords=weeklyReocrds.subList(i*Constant.WEEKLY_RECORD_PAGE_SIZE, weeklyReocrds.size());
				}
				//根据每页的周简易记录填充周简易记录docx
				XWPFDocument weeklyRecordDocument =getWeeklyRecordDocument(weeklyRecords);
				//将填充好的docx填充到根docx上
				POIUtil.mergeDocx(originDocument, weeklyRecordDocument);
			}
		}
		
		//遍历家庭联系记录
		for (Record familyRecord : familyRecords) {
			//将其填充到相应docx中
			XWPFDocument familyRecordDocument=getFamilyRecordDocument(familyRecord);
			//将填充好的docx填充到根docx上
			POIUtil.mergeDocx(originDocument, familyRecordDocument);
		}
		
		//遍历面谈记录
		for (Record faceRecord : faceRecords) {
			//将其填充到相应docx中
			XWPFDocument faceRecordDocument=getFaceRecordDocument(faceRecord);
			//将填充好的docx填充到根docx上
			POIUtil.mergeDocx(originDocument, faceRecordDocument);
		}
		
		//遍历研讨记录
		for (Record discussRecord : discussRecords) {
			//将其填充到相应docx中
			XWPFDocument discussRecordDocument=getDiscussRecordDocument(discussRecord);
			//将填充好的docx填充到根docx上
			POIUtil.mergeDocx(originDocument, discussRecordDocument);
		}
		
			
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		originDocument.write(byteArrayOutputStream);
		byteArrayOutputStream.close();
		originDocument.close();
		return byteArrayOutputStream.toByteArray();
	}
	
	private XWPFDocument getArchiveCoverDocument(ArchiveStudent archiveStudent) throws Exception{
		//从磁盘读取模板文件
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.ARCHIVE_COVER_TEMPLATE_NAME);
		
		//打开学生帮扶记录薄封面docx 获取读写权限
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//通过opcPackage来创建XWPFDocument工作文档
		XWPFDocument document=new XWPFDocument(opcPackage);
		//获取所有段落
		List<XWPFParagraph> paragraphs=document.getParagraphs();
		
		//遍历所有段落
		for (XWPFParagraph paragraph : paragraphs) {
			List<XWPFRun> runs=paragraph.getRuns();
			
			//遍历所有子句
			for (int i = 0; i < runs.size(); i++) {
				XWPFRun run=runs.get(i);
				if(ReplaceMark.GRADE.equals(run.text())) {
					paragraph.removeRun(i);
					XWPFRun newRun=paragraph.insertNewRun(i);
					newRun.setText(String.valueOf(archiveStudent.getGrade()==null?"":archiveStudent.getGrade()));
					newRun.setFontFamily(Constant.COVER_FONT_FAMILY);
					newRun.setBold(true);
					continue;
				}else if(ReplaceMark.HELP_TYPE.equals(run.text())) {
					paragraph.removeRun(i);
					XWPFRun newRun=paragraph.insertNewRun(i);
					newRun.setText(String.valueOf(archiveStudent.getHelpType()==null?"":archiveStudent.getHelpType()));
					newRun.setFontFamily(Constant.COVER_FONT_FAMILY);
					newRun.setBold(true);
					continue;
				}else if(ReplaceMark.BULIDING_TIME.equals(run.text())) {
					paragraph.removeRun(i);
					String bulidingTime;
					try {
						bulidingTime=sdf.format(archiveStudent.getBulidingTime());
					} catch (Exception e) {
						bulidingTime="";
					}
					XWPFRun newRun=paragraph.insertNewRun(i);
					newRun.setText(bulidingTime);
					newRun.setFontFamily(Constant.COVER_FONT_FAMILY);
					newRun.setBold(true);
					continue;
				}else if(ReplaceMark.ARCHIVE_ID.equals(run.text())) {
					paragraph.removeRun(i);
					XWPFRun newRun=paragraph.insertNewRun(i);
					newRun.setText(String.valueOf(archiveStudent.getArchiveId()));
					newRun.setFontFamily(Constant.COVER_FONT_FAMILY);
					newRun.setBold(true);
					continue;
				}
			}
		}
		
		return document;
	}
	
	private XWPFDocument getBaseStudentInfoDocument(ArchiveStudent archiveStudent) throws Exception{
		//从磁盘读取模板文件
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.BASE_STUDENT_DOCX_TEMPLATE_NAME);
		
		//打开学生帮扶记录薄封面docx 获取读写权限
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//通过opcPackage来创建XWPFDocument工作文档
		XWPFDocument document=new XWPFDocument(opcPackage);
		
		//获取所有表格
		List<XWPFTable> tables=document.getTables();
		
		//遍历所有表格
		for (XWPFTable table : tables) {
			//获取表格的每一行
			List<XWPFTableRow> rows=table.getRows();
			
			//遍历所有行
			for (XWPFTableRow row : rows) {
				//获取每行的每个格子
				List<XWPFTableCell> cells=row.getTableCells();
				//遍历每个格子
				for (int i = 0; i < cells.size(); i++) {
					XWPFTableCell cell=cells.get(i);
					replaceCellString(cell, ReplaceMark.STUDENT_NAME, archiveStudent.getName()==null?"":archiveStudent.getName());
					replaceCellString(cell, ReplaceMark.SEX, archiveStudent.getSex()==null?"":archiveStudent.getSex());
					replaceCellString(cell, ReplaceMark.STUDENT_ID, String.valueOf(archiveStudent.getStudentId()));
					replaceCellString(cell, ReplaceMark.GRADE_CLASS, String.valueOf(archiveStudent.getStudentClass()==null?"":archiveStudent.getStudentClass()));
					replaceCellString(cell, ReplaceMark.POLITICAL_STATUS, archiveStudent.getPoliticalStatus()==null?"":archiveStudent.getPoliticalStatus());
					replaceCellString(cell, ReplaceMark.ETHNIC_GROUP, archiveStudent.getEthnicGroup()==null?"":archiveStudent.getEthnicGroup());
					replaceCellString(cell, ReplaceMark.DUTY, archiveStudent.getDuty()==null?"":archiveStudent.getDuty());
					replaceCellString(cell, ReplaceMark.DORMITORY, archiveStudent.getDormitory()==null?"":archiveStudent.getDormitory());
					replaceCellString(cell, ReplaceMark.STUDENT_ORGIN, archiveStudent.getBirthOrigin()==null?"":archiveStudent.getBirthOrigin());
					replaceCellString(cell, ReplaceMark.FAMILY_ADDRESS, archiveStudent.getFamilyAddress()==null?"":archiveStudent.getFamilyAddress());
					replaceCellString(cell, ReplaceMark.CONTACT_WAY_ZERO, archiveStudent.getContactWay()==null?"":archiveStudent.getContactWay());
					replaceCellString(cell, ReplaceMark.CONTACT_WAY_ONE, archiveStudent.getFatherTelNumber()==null?"":archiveStudent.getFatherTelNumber());
					replaceCellString(cell, ReplaceMark.CONTACT_WAY_TWO, archiveStudent.getMotherTelNumber()==null?"":archiveStudent.getMotherTelNumber());
					replaceCellStringForCondition(cell, ReplaceMark.FAMILY_CONDITION, archiveStudent.getFamilyCondition()==null?"":archiveStudent.getFamilyCondition());
					replaceCellStringForCondition(cell, ReplaceMark.STUDY_CONDITION, archiveStudent.getStudyCondition()==null?"":archiveStudent.getStudyCondition());
					replaceCellStringForCondition(cell, ReplaceMark.HEALTH_CONDITION, archiveStudent.getHealthCondition()==null?"":archiveStudent.getHealthCondition());
					replaceCellStringForCondition(cell, ReplaceMark.LIFE_CONDITION, archiveStudent.getLifeCondition()==null?"":archiveStudent.getLifeCondition());
					replaceCellStringForCondition(cell, ReplaceMark.OTHER_CONDITION, archiveStudent.getOtherCondition()==null?"":archiveStudent.getOtherCondition());
				}
			}
		}
		return document;
	}
	
	private XWPFDocument getBulidingArchiveDocument(ArchiveStudent archiveStudent) throws Exception{
		//从磁盘读取模板文件
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.ARCHIVE_INFO_TEMPLATE_NAME);
		
		//打开学生帮扶记录薄封面docx 获取读写权限
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//通过opcPackage来创建XWPFDocument工作文档
		XWPFDocument document=new XWPFDocument(opcPackage);
		
		//获取所有表格
		List<XWPFTable> tables=document.getTables();
		
		//遍历所有表格
		for (XWPFTable table : tables) {
			//获取表格的每一行
			List<XWPFTableRow> rows=table.getRows();
			
			//遍历所有行
			for (XWPFTableRow row : rows) {
				//获取每行的每个格子
				List<XWPFTableCell> cells=row.getTableCells();
				//遍历每个格子 并根据匹配串来替换模板 重新填充相匹配的格子
				for (int i = 0; i < cells.size(); i++) {
					XWPFTableCell cell=cells.get(i);
					replaceCellString(cell, ReplaceMark.BULIDING_BASIS, archiveStudent.getBulidingBasis()==null?"":archiveStudent.getBulidingBasis());
					replaceCellString(cell, ReplaceMark.BULIDING_RECORDER, archiveStudent.getBulidingRecorder()==null?"":archiveStudent.getBulidingRecorder());
					
					String bulidingTime;
					try {
						bulidingTime=sdf.format(archiveStudent.getBulidingTime());
					} catch (Exception e) {
						bulidingTime="";
					}
					replaceCellString(cell, ReplaceMark.BULIDING_TIME, bulidingTime);
					
					replaceCellString(cell, ReplaceMark.DESTORYING_BASIS, archiveStudent.getDestoryingBasis()==null?"":archiveStudent.getDestoryingBasis());
					replaceCellString(cell, ReplaceMark.DESTORYING_RECORDER, archiveStudent.getDestoryingRecorder()==null?"":archiveStudent.getDestoryingRecorder());
					
					String destoryTime;
					try {
						destoryTime=sdf.format(archiveStudent.getDestoryingTime());
					} catch (Exception e) {
						destoryTime="";
					}
					replaceCellString(cell, ReplaceMark.DESTORYING_TIME, destoryTime);
					
					replaceCellString(cell, ReplaceMark.BULIDING_PERSON, archiveStudent.getBulidingPerson()==null?"":archiveStudent.getBulidingPerson());
					replaceCellString(cell, ReplaceMark.BULIDING_PERSON_DUTY, archiveStudent.getBulidingPersonDuty()==null?"":archiveStudent.getBulidingPersonDuty());
					replaceCellString(cell, ReplaceMark.HELP_TYPE, archiveStudent.getHelpType()==null?"":archiveStudent.getHelpType());
					
					//如果变更记录数量大于等于一 则替换第一行变更记录
					if(archiveStudent.getRecorderChanges().size()>=1) {
						RecorderChange recorderChange=archiveStudent.getRecorderChanges().get(0);
						String changeTime;
						try {
							changeTime=sdf.format(recorderChange.getChangeTime());
						} catch (Exception e) {
							changeTime="";
						}
						replaceCellString(cell, ReplaceMark.CHANGE_TIME_ZERO, changeTime);
						replaceCellString(cell, ReplaceMark.RECORDER_NOW_ZERO, recorderChange.getRecorderNow()==null?"":recorderChange.getRecorderNow());
						replaceCellString(cell, ReplaceMark.CHANGE_REASON_ZERO, recorderChange.getChangeReason()==null?"":recorderChange.getChangeReason());
						
						//如果变更记录数量大于等于二 进一步替换第二行变更记录
						if(archiveStudent.getRecorderChanges().size()>=2) {
							RecorderChange recorderChange1=archiveStudent.getRecorderChanges().get(1);
							String changeTime1;
							try {
								changeTime1=sdf.format(recorderChange.getChangeTime());
							} catch (Exception e) {
								changeTime1="";
							}
							replaceCellString(cell, ReplaceMark.CHANGE_TIME_ONE, changeTime1);
							replaceCellString(cell, ReplaceMark.RECORDER_NOW_ONE, recorderChange1.getRecorderNow()==null?"":recorderChange1.getRecorderNow());
							replaceCellString(cell, ReplaceMark.CHANGE_REASON_ONE, recorderChange1.getChangeReason()==null?"":recorderChange1.getChangeReason());
							//如果变更记录数量大于等于3 进一步替换第3行变更记录
							if(archiveStudent.getRecorderChanges().size()>=3) {
								RecorderChange recorderChange2=archiveStudent.getRecorderChanges().get(2);
								String changeTime2;
								try {
									changeTime2=sdf.format(recorderChange2.getChangeTime());
								} catch (Exception e) {
									changeTime2="";
								}
								replaceCellString(cell, ReplaceMark.CHANGE_TIME_TWO, changeTime2);
								replaceCellString(cell, ReplaceMark.RECORDER_NOW_TWO, recorderChange2.getRecorderNow()==null?"":recorderChange2.getRecorderNow());
								replaceCellString(cell, ReplaceMark.CHANGE_REASON_TWO, recorderChange2.getChangeReason()==null?"":recorderChange2.getChangeReason());
							}else {
								//如果变更记录数量小于3 则把剩余的部分替换成空白
								replaceCellString(cell, ReplaceMark.CHANGE_TIME_TWO, "");
								replaceCellString(cell, ReplaceMark.RECORDER_NOW_TWO, "");
								replaceCellString(cell, ReplaceMark.CHANGE_REASON_TWO, "");
							}
						}else {
							//如果变更记录数量小于2 则把剩余的部分替换成空白
							replaceCellString(cell, ReplaceMark.CHANGE_TIME_ONE, "");
							replaceCellString(cell, ReplaceMark.RECORDER_NOW_ONE, "");
							replaceCellString(cell, ReplaceMark.CHANGE_REASON_ONE, "");
								
							replaceCellString(cell, ReplaceMark.CHANGE_TIME_TWO, "");
							replaceCellString(cell, ReplaceMark.RECORDER_NOW_TWO, "");
							replaceCellString(cell, ReplaceMark.CHANGE_REASON_TWO, "");
						}
							
					}else {
						//如果变更记录数量小于1 则把所有的部分替换成空白
						replaceCellString(cell, ReplaceMark.CHANGE_TIME_ZERO, "");
						replaceCellString(cell, ReplaceMark.RECORDER_NOW_ZERO, "");
						replaceCellString(cell, ReplaceMark.CHANGE_REASON_ZERO, "");
							
						replaceCellString(cell, ReplaceMark.CHANGE_TIME_ONE, "");
						replaceCellString(cell, ReplaceMark.RECORDER_NOW_ONE, "");
						replaceCellString(cell, ReplaceMark.CHANGE_REASON_ONE, "");
							
						replaceCellString(cell, ReplaceMark.CHANGE_TIME_TWO, "");
						replaceCellString(cell, ReplaceMark.RECORDER_NOW_TWO, "");
						replaceCellString(cell, ReplaceMark.CHANGE_REASON_TWO, "");
					}
				}
			}
		}
		return document;
	}
	
	private XWPFDocument getWeeklyRecordDocument(List<Record> records) throws Exception{
		//从磁盘读取模板文件
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.WEEKLY_SIMPLE_RECORD_DOCX_TEMPLATE_NAME);
		
		//打开学生帮扶记录薄封面docx 获取读写权限
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//通过opcPackage来创建XWPFDocument工作文档
		XWPFDocument document=new XWPFDocument(opcPackage);
		
		//获取所有表格
		List<XWPFTable> tables=document.getTables();
		
		//遍历所有表格
		for (XWPFTable table : tables) {
			//获取表格的每一行
			List<XWPFTableRow> rows=table.getRows();
			
			//遍历所有行
			for (XWPFTableRow row : rows) {
				//获取每行的每个格子
				List<XWPFTableCell> cells=row.getTableCells();
				//遍历每个格子 并根据匹配串来替换模板 重新填充相匹配的格子
				for (int i = 0; i < cells.size(); i++) {
					XWPFTableCell cell=cells.get(i);
					int j;
					for (j = 0; j < records.size(); j++) {
						Record record=records.get(j);
							
						String weeklyRecordTime;
						try {
							weeklyRecordTime=sdf.format(record.getRecordTime());
						} catch (Exception e) {
							weeklyRecordTime="";
						}
						replaceCellString(cell, ReplaceMark.WEEKLY_RECORD_TIME+j, weeklyRecordTime);
						replaceCellString(cell, ReplaceMark.WEEKLY_RECORD_LOCATION+j, record.getLocation()==null?"":record.getLocation());
						replaceCellString(cell, ReplaceMark.WEEKLY_RECORD_WAY+j, record.getWay()==null?"":record.getWay());
						replaceCellString(cell, ReplaceMark.WEEKLY_REOCRD_CONTENT+j, record.getContent()==null?"":record.getContent());
						replaceCellString(cell, ReplaceMark.WEEKLY_RECORD_COMMENT+j, record.getComment()==null?"":record.getComment());
					}
					//将剩下的模板记号置换为空串
					for (int k = j; k <=Constant.WEEKLY_RECORD_PAGE_SIZE; k++) {
						replaceCellString(cell, ReplaceMark.WEEKLY_RECORD_TIME+k, "");
						replaceCellString(cell, ReplaceMark.WEEKLY_RECORD_LOCATION+k, "");
						replaceCellString(cell, ReplaceMark.WEEKLY_RECORD_WAY+k, "");
						replaceCellString(cell, ReplaceMark.WEEKLY_REOCRD_CONTENT+k, "");
						replaceCellString(cell, ReplaceMark.WEEKLY_RECORD_COMMENT+k, "");
					}
				}
			}
		}
		return document;
	}
	
	private XWPFDocument getFamilyRecordDocument(Record r) throws Exception{
		//从磁盘读取模板文件
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.FAMILY_RECORD_DOCX_TEMPLATE_NAME);
		
		//打开学生帮扶记录薄封面docx 获取读写权限
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//通过opcPackage来创建XWPFDocument工作文档
		XWPFDocument document=new XWPFDocument(opcPackage);
		
		//获取所有表格
		List<XWPFTable> tables=document.getTables();
		
		//遍历所有表格
		for (XWPFTable table : tables) {
			//获取表格的每一行
			List<XWPFTableRow> rows=table.getRows();
			
			//遍历所有行
			for (XWPFTableRow row : rows) {
				//获取每行的每个格子
				List<XWPFTableCell> cells=row.getTableCells();
				//遍历每个格子 并根据匹配串来替换模板 重新填充相匹配的格子
				for (int i = 0; i < cells.size(); i++) {
					XWPFTableCell cell=cells.get(i);
					String familyRecordTime;
					try {
						familyRecordTime=sdf.format(r.getRecordTime());
					} catch (Exception e) {
						familyRecordTime="";
					}
					replaceCellString(cell, ReplaceMark.FAMILY_RECORD_TIME, familyRecordTime);
					replaceCellString(cell, ReplaceMark.FAMILY_RECORD_LOCATION, r.getLocation()==null?"":r.getLocation());
					replaceCellString(cell, ReplaceMark.FAMILY_RECORD_WITNESS, r.getWitness()==null?"":r.getWitness());
					replaceCellString(cell, ReplaceMark.FAMILY_RECORD_WAY, r.getWay()==null?"":r.getWay());
					replaceCellString(cell, ReplaceMark.FAMILY_RECORD_CONTENT, r.getContent()==null?"":r.getContent());
					replaceCellString(cell, ReplaceMark.FAMILY_RECORD_RECORDER, r.getRecorder()==null?"":r.getRecorder());
						
				}
			}
		}
		return document;
	}
	
	private XWPFDocument getFaceRecordDocument(Record r) throws Exception{
		//从磁盘读取模板文件
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.FACE_TALK_RECORD_DOCX_TEMPLATE_NAME);
		
		//打开学生帮扶记录薄封面docx 获取读写权限
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//通过opcPackage来创建XWPFDocument工作文档
		XWPFDocument document=new XWPFDocument(opcPackage);
		
		//获取所有表格
		List<XWPFTable> tables=document.getTables();
		
		//遍历所有表格
		for (XWPFTable table : tables) {
			//获取表格的每一行
			List<XWPFTableRow> rows=table.getRows();
			
			//遍历所有行
			for (XWPFTableRow row : rows) {
				//获取每行的每个格子
				List<XWPFTableCell> cells=row.getTableCells();
				//遍历每个格子
				for (int i = 0; i < cells.size(); i++) {
					XWPFTableCell cell=cells.get(i);
					String faceRecordTime;
					try {
						faceRecordTime=sdf.format(r.getRecordTime());
					} catch (Exception e) {
						faceRecordTime="";
					}
					replaceCellString(cell, ReplaceMark.FACE_RECORD_TIME, faceRecordTime);
					replaceCellString(cell, ReplaceMark.FACE_RECORD_LOCATION, r.getLocation()==null?"":r.getLocation());
					replaceCellString(cell, ReplaceMark.FACE_RECORD_WAY, r.getWay()==null?"":r.getWay());
					replaceCellString(cell, ReplaceMark.FACE_RECORD_CONTENT, r.getContent()==null?"":r.getContent());
					replaceCellString(cell, ReplaceMark.FACE_RECORD_RECORDER, r.getRecorder()==null?"":r.getRecorder());
						
				}
			}
		}
		return document;
	}
	
	private XWPFDocument getDiscussRecordDocument(Record r) throws Exception{
		//从磁盘读取模板文件
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.DISCUSS_SUMMARY_RECORD_DOCX_TEMPLATE_NAME);
		
		//打开学生帮扶记录薄封面docx 获取读写权限
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//通过opcPackage来创建XWPFDocument工作文档
		XWPFDocument document=new XWPFDocument(opcPackage);
		
		//获取所有表格
		List<XWPFTable> tables=document.getTables();
		
		//遍历所有表格
		for (XWPFTable table : tables) {
			//获取表格的每一行
			List<XWPFTableRow> rows=table.getRows();
			
			//遍历所有行
			for (XWPFTableRow row : rows) {
				//获取每行的每个格子
				List<XWPFTableCell> cells=row.getTableCells();
				//遍历每个格子
				for (int i = 0; i < cells.size(); i++) {
					XWPFTableCell cell=cells.get(i);
					String discussRecordTime;
					try {
						discussRecordTime=sdf.format(r.getRecordTime());
					} catch (Exception e) {
						discussRecordTime="";
					}
					replaceCellString(cell, ReplaceMark.DISCUESS_RECORD_TIME, discussRecordTime);
					replaceCellString(cell, ReplaceMark.DISCUESS_RECORD_LOCATION, r.getLocation()==null?"":r.getLocation());
					replaceCellString(cell, ReplaceMark.DISCUESS_RECORD_PARTICIPANT, r.getParticipant()==null?"":r.getParticipant());
					replaceCellString(cell, ReplaceMark.DISCUESS_RECORD_CONTENT, r.getContent()==null?"":r.getContent());
					replaceCellString(cell, ReplaceMark.DISCUESS_RECORD_RECORDER, r.getRecorder()==null?"":r.getRecorder());
				}
			}
		}
		return document;
	}
	
	private void replaceCellString(XWPFTableCell cell,String replaceMark,String newCellString) {
		//获取表格单元里的字符串
		String cellString=cell.getText();
		if(replaceMark.equals(cellString)) {
			//删除格子里的段落删除
			cell.removeParagraph(0);
			//创建新的段落
			XWPFParagraph paragraph=new XWPFParagraph(cell.getCTTc().addNewP(), cell);
			//创建新的子句
			XWPFRun run=paragraph.createRun();
			//在新子句里放替换的内容
			run.setText(newCellString);
			//设置字体
			run.setFontFamily(Constant.FONT_FAMILY);
			//将新的段落放到格子里
			cell.addParagraph(paragraph);
		}
	}
	private void replaceCellStringForCondition(XWPFTableCell cell,String replaceMark,String newCellString) {
		//获取表格单元里的字符串
		String cellString=cell.getText();
		if(cellString!=null&&cellString.contains(replaceMark)) {
			//删除格子里的段落删除
			cell.removeParagraph(0);
			//创建新的段落
			XWPFParagraph paragraph=new XWPFParagraph(cell.getCTTc().addNewP(), cell);
			//创建新的子句
			XWPFRun run=paragraph.createRun();
			//在新子句里放替换的内容
			run.setText(cellString.replaceFirst(replaceMark, newCellString));
			//设置字体
			run.setFontFamily(Constant.FONT_FAMILY);
			//将新的段落放到格子里
			cell.addParagraph(paragraph);
		}
	}

}
