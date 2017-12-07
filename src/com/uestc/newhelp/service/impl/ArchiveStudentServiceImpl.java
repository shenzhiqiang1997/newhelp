package com.uestc.newhelp.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.uestc.newhelp.dao.ArchiveStudentDao;
import com.uestc.newhelp.dao.AttentionTypeDao;
import com.uestc.newhelp.dao.BaseStudentDao;
import com.uestc.newhelp.dao.HistoryArchiveDao;
import com.uestc.newhelp.dao.HistoryRecordDao;
import com.uestc.newhelp.dao.HistoryRecorderChangeDao;
import com.uestc.newhelp.dao.RecordDao;
import com.uestc.newhelp.dao.RecorderChangeDao;
import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.entity.ArchiveStudent;
import com.uestc.newhelp.entity.AttentionType;
import com.uestc.newhelp.entity.BaseStudent;
import com.uestc.newhelp.entity.HistoryArchive;
import com.uestc.newhelp.entity.HistoryRecord;
import com.uestc.newhelp.entity.HistoryRecorderChange;
import com.uestc.newhelp.entity.Record;
import com.uestc.newhelp.entity.RecorderChange;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.ArchiveStudentHadExistException;
import com.uestc.newhelp.exception.NoSuchStudentException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.exception.NotPointOutStudentIdException;
import com.uestc.newhelp.path.FileName;
import com.uestc.newhelp.path.Path;
import com.uestc.newhelp.service.ArchiveStudentService;
import com.uestc.newhelp.type.RecordType;
import com.uestc.newhelp.util.DateUtil;
import com.uestc.newhelp.util.POIUtil;
@Service
public class ArchiveStudentServiceImpl implements ArchiveStudentService {
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private BaseStudentDao baseStudentDao;
	@Autowired
	private ArchiveStudentDao archiveStudentDao;
	@Autowired
	private RecorderChangeDao recorderChangeDao;
	@Autowired
	private RecordDao recordDao;
	@Autowired
	private AttentionTypeDao attentionTypeDao;
	@Autowired
	private HistoryArchiveDao historyArchiveDao;
	@Autowired
	private HistoryRecorderChangeDao historyRecorderChangeDao;
	@Autowired
	private HistoryRecordDao historyRecordDao;
	
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public List<ArchiveStudent> list(String teacherId) {
		//��ѯָ����ʦ�İ��ѧ��
		Teacher teacher=teacherDao.getInfo(teacherId);
		teacher.setTeacherId(teacherId);
		List<ArchiveStudent> archiveStudents=archiveStudentDao.list(teacher);
		//��ѯ���й�ע�����б�
		List<AttentionType> attentionTypes=attentionTypeDao.list();
		//��Ų�ͬ��ע���������ܳ�
		Map<String,Byte> attentionTypeMap=new HashMap<>();
		//�����й�ע���͵����Ѽ���ܳ���(��ע����,��Ӧ�����ܳ�)����ʽ��ŵ�map��
		for (AttentionType attentionType : attentionTypes) {
			attentionTypeMap.put(attentionType.getAttentionTypeName(), attentionType.getRemindRecordInterval());
		}
		//������ѯ���İ��ѧ���б�
		for (ArchiveStudent archiveStudent : archiveStudents) {
			boolean remind=false;
			if(archiveStudent.getLastRecordTime()!=null) {
				//������һ�μ�¼ʱ�������ڵ�ʱ���
				long interval=DateUtil.getIntervalBetweenToday(archiveStudent.getLastRecordTime());
				//��ȡ��Ӧѧ�������Ѽ���ܳ�
				Byte weekCount=attentionTypeMap.get(archiveStudent.getAttentionType());
				//���û�ж�Ӧ�Ĺ�ע����,��ֱ�Ӳ�����
				if(weekCount==null) {
					remind=false;
					continue;
				}
				//�ж�ʱ����Ƿ񳬹����Ѽ��ʱ��
				remind=DateUtil.remindInterval(interval,weekCount);
			}
			//��������򽫸ð��ѧ���ĸ�����������Ϊtrue,����Ϊfalse
			if(remind) {
				archiveStudent.setHighlight(true);
			}else {
				archiveStudent.setHighlight(false);
			}
		}
		return archiveStudents;
	}

	@Override
	public List<ArchiveStudent> search(ArchiveStudent archiveStudent) {
				//����������������ָ����ʦ�İ��ѧ��
				Teacher teacher=teacherDao.getInfo(archiveStudent.getTeacherId());
				teacher.setTeacherId(archiveStudent.getTeacherId());
				List<ArchiveStudent> archiveStudents=archiveStudentDao.search(teacher,archiveStudent);
				//��ѯ���й�ע�����б�
				List<AttentionType> attentionTypes=attentionTypeDao.list();
				//��Ų�ͬ��ע���������ܳ�
				Map<String,Byte> attentionTypeMap=new HashMap<>();
				//�����й�ע���͵����Ѽ���ܳ���(��ע����,��Ӧ�����ܳ�)����ʽ��ŵ�map��
				for (AttentionType attentionType : attentionTypes) {
					attentionTypeMap.put(attentionType.getAttentionTypeName(), attentionType.getRemindRecordInterval());
				}
				//������ѯ���İ��ѧ���б�
				for (ArchiveStudent archiveStudent1 : archiveStudents) {
					boolean remind=false;
					//�����ѧ����¼��
					if(archiveStudent1.getLastRecordTime()!=null) {
						//������һ�μ�¼ʱ�������ڵ�ʱ���
						long interval=DateUtil.getIntervalBetweenToday(archiveStudent1.getLastRecordTime());
						//��ȡ��Ӧѧ�������Ѽ���ܳ�
						byte weekCount=attentionTypeMap.get(archiveStudent1.getAttentionType());
						//�ж�ʱ����Ƿ񳬹����Ѽ��ʱ��
						remind=DateUtil.remindInterval(interval,weekCount);
					}
					//��������򽫸ð��ѧ���ĸ�����������Ϊtrue,����Ϊfalse
					if(remind) {
						archiveStudent1.setHighlight(true);
					}else {
						archiveStudent1.setHighlight(false);
					}
				}
				return archiveStudents;
	}

	@Override
	public void add(ArchiveStudent archiveStudent)throws NotPointOutStudentIdException,NoSuchStudentException,ArchiveStudentHadExistException{
		//���ѧ��Ϊ�����׳��쳣
		if(archiveStudent.getStudentId()==null) {
			throw new NotPointOutStudentIdException("ѧ�Ų���Ϊ��");
		}
		//��ȡ����ѧ����Ϣ
		BaseStudent baseStudent=baseStudentDao.getArchiveInfo(archiveStudent.getStudentId());
		//�����ѧ���Ļ�����Ϣ���������׳��쳣
		if(baseStudent==null) {
			throw new NoSuchStudentException("������Ϣ�������޸�ѧ����Ϣ����ֹ���");
		}
		//��ȡ��ǰѧ���Ľ�ʦid
		String teacherId=archiveStudentDao.check(archiveStudent.getStudentId());
		//�����ѧ���Ľ�ʦid���� ��˵����ѧ���Ѿ�������
		if(teacherId!=null) {
			//�����ѧ���Ľ�ʦid���ǵ�ǰҪ������ѧ��id���׳����ظ������쳣
			if(teacherId.equals(archiveStudent.getTeacherId())) {
				throw new ArchiveStudentHadExistException("���ѶԵ�ǰѧ��Ϊ"+archiveStudent.getStudentId()+"��ѧ������,�����ظ�����");
			}else {
				//����˵����ѧ����������ʦ���� ���׳��ѱ�������ʦ�������쳣
				throw new ArchiveStudentHadExistException("ѧ��Ϊ"+archiveStudent.getStudentId()+"��ѧ���Ѿ����û���Ϊ"+teacherId+"�Ľ�ʦ����,������������ϵ�ý�ʦ");
			}
		}
		//����ѧ��Ĭ�Ͻ���ʱ��Ϊ���� ��ȡ����Ӧѧ��������Ϣ ĩ�μ�¼ʱ��Ϊ����
		archiveStudent.setBulidingTime(new Date());
		archiveStudent.setLastRecordTime(new Date());
		archiveStudent.setGrade(baseStudent.getGrade());
		
		archiveStudentDao.add(archiveStudent);

	}

	@Override
	public void delete(ArchiveStudent a) {
		//����ɾ��ʱ��Ϊ����
		a.setDestoryingTime(new Date());
		//�Ƚ�ɾ����Ϣ¼��
		archiveStudentDao.update(a);
		
		//��ѯ���ѧ������
		ArchiveStudent archiveStudent=archiveStudentDao.get(a.getStudentId());
		//��ѯ�õ����ļ�¼�˱����¼
		List<RecorderChange> recorderChanges=recorderChangeDao.list(a.getStudentId());
		Record record=new Record();
		record.setStudentId(a.getStudentId());
		//��ѯ�õ����ļ�¼
		List<Record> records=recordDao.listOnType(record);
		
		//������ʷ����,������ǰҪɾ���ĵ�����Ϣ����������
		HistoryArchive historyArchive=new HistoryArchive(archiveStudent);
		historyArchiveDao.add(historyArchive);
		
		//��������ʷ�����ļ�¼�˱����¼
		List<HistoryRecorderChange> historyRecorderChanges=new ArrayList<>();
		//��������ʷ��������ʷ��¼
		List<HistoryRecord> historyRecords=new ArrayList<>();
		
		if(recorderChanges!=null&&recorderChanges.size()>0) {
			//����ǰҪɾ���ĵ����ļ�¼�˱����¼��������ʷ�����¼��
			for (RecorderChange recorderChange : recorderChanges) {
				HistoryRecorderChange historyRecorderChange=new HistoryRecorderChange(historyArchive.getHistoryArchiveId(), recorderChange);
				historyRecorderChanges.add(historyRecorderChange);
			}
			//����ʷ���������Ϣ��ŵ����ݿ���
			historyRecorderChangeDao.addBatch(historyRecorderChanges);
			//��Ҫɾ���ĵ���ɾ����
			recorderChangeDao.delete(a.getStudentId());
		}
		if(records!=null&&records.size()>0) {
			//����ǰҪɾ���ĵ����ļ�¼��������ʷ��¼��
			for (Record r : records) {
				HistoryRecord historyRecord=new HistoryRecord(historyArchive.getHistoryArchiveId(), r);
				historyRecords.add(historyRecord);
			}
			//����ʷ���������Ϣ��ŵ����ݿ���
			historyRecordDao.addBatch(historyRecords);
			//��Ҫɾ���ĵ���ɾ����
			recordDao.deleteByStudentId(a.getStudentId());
		}
		
		//��Ҫɾ���ĵ���ɾ����
		archiveStudentDao.delete(a.getStudentId());
		
		

	}

	@Override
	public void update(ArchiveStudent archiveStudent) {
		archiveStudentDao.update(archiveStudent);
		
	}

	@Override
	public ArchiveStudent get(Long studentId) {
		//��ѯ������ϸ��Ϣ
		ArchiveStudent archiveStudent=archiveStudentDao.get(studentId);
		
		//��ѯ��Ӧ�����ĸ��ļ�¼�����õ�bean��
		List<RecorderChange> recorderChanges=recorderChangeDao.list(studentId);
		archiveStudent.setRecorderChanges(recorderChanges);
		return archiveStudent;
	}
	
	@Override
	public byte[] exportArchiveToWordFile(Long studentId) throws IOException,NotChoseExportObjectException,NoSuchStudentException,Exception {
		if(studentId==null) {
			throw new NotChoseExportObjectException("��δѡ��Ҫ�����Ķ���,����ѡ����ٵ���");
		}
		ArchiveStudent archiveStudent=archiveStudentDao.get(studentId);
		List<RecorderChange> recorderChanges=recorderChangeDao.list(studentId);
		if(archiveStudent==null) {
			throw new NoSuchStudentException("Ҫ������ѧ����δ������");
		}
		//�Ѽ�¼�˸ı���б����õ�������
		archiveStudent.setRecorderChanges(recorderChanges);
		
		//��ѯ����ϵ���׼�¼��
		Record record=new Record(studentId, RecordType.WEEKLY_SIMPLE_RECORD.value);
		List<Record> weeklyReocrds=recordDao.listOnType(record);
		//��ѯ�ҳ���ϵ��¼��
		record.setRecordName(RecordType.FAMILY_RECORD.value);
		List<Record> familyRecords=recordDao.listOnType(record);
		//��ѯ��̸��¼��
		record.setRecordName(RecordType.FACE_TALK_RECORD.value);
		List<Record> faceRecords=recordDao.listOnType(record);
		//��ѯ���ּ��ܽ��¼
		record.setRecordName(RecordType.DISCUSS_SUMMARY_RECORD.value);
		List<Record> discussRecords=recordDao.listOnType(record);
		
		//������docx
		XWPFDocument originDocument=getArchiveCoverDocument(archiveStudent);
		//���ѧ��������Ϣdocx
		XWPFDocument baseStudentInfoDocument=getBaseStudentInfoDocument(archiveStudent);
		//��佨����Ϣdocx
		XWPFDocument bulidingArchiveDocument=getBulidingArchiveDocument(archiveStudent);
		
		//ƴ�ӷ����ѧ��������Ϣ
		POIUtil.mergeDocx(originDocument, baseStudentInfoDocument);
		//����ƴ�ӽ�����Ϣ
		POIUtil.mergeDocx(originDocument, bulidingArchiveDocument);
		
		//������ܼ�����ϵ���׼�¼
		if(weeklyReocrds.size()!=0) {
			//�����ܼ�����ϵ���׼�¼ҳ��
			int weeklyRecordNum=weeklyReocrds.size()%7==0?weeklyReocrds.size()/7:weeklyReocrds.size()/7+1;
			//����ҳ��ȡÿ����ϵ��¼
			for (int i = 0; i < weeklyRecordNum; i++) {
				List<Record> weeklyRecords;
				if((i+1)*Constant.WEEKLY_RECORD_PAGE_SIZE<=weeklyReocrds.size()) {
					weeklyRecords=weeklyReocrds.subList(i*Constant.WEEKLY_RECORD_PAGE_SIZE, (i+1)*Constant.WEEKLY_RECORD_PAGE_SIZE);
				}else {
					weeklyRecords=weeklyReocrds.subList(i*Constant.WEEKLY_RECORD_PAGE_SIZE, weeklyReocrds.size());
				}
				//����ÿҳ���ܼ��׼�¼����ܼ��׼�¼docx
				XWPFDocument weeklyRecordDocument =getWeeklyRecordDocument(weeklyRecords);
				//�����õ�docx��䵽��docx��
				POIUtil.mergeDocx(originDocument, weeklyRecordDocument);
			}
		}
		
		//������ͥ��ϵ��¼
		for (Record familyRecord : familyRecords) {
			//������䵽��Ӧdocx��
			XWPFDocument familyRecordDocument=getFamilyRecordDocument(familyRecord);
			//�����õ�docx��䵽��docx��
			POIUtil.mergeDocx(originDocument, familyRecordDocument);
		}
		
		//������̸��¼
		for (Record faceRecord : faceRecords) {
			//������䵽��Ӧdocx��
			XWPFDocument faceRecordDocument=getFaceRecordDocument(faceRecord);
			//�����õ�docx��䵽��docx��
			POIUtil.mergeDocx(originDocument, faceRecordDocument);
		}
		
		//�������ּ�¼
		for (Record discussRecord : discussRecords) {
			//������䵽��Ӧdocx��
			XWPFDocument discussRecordDocument=getDiscussRecordDocument(discussRecord);
			//�����õ�docx��䵽��docx��
			POIUtil.mergeDocx(originDocument, discussRecordDocument);
		}
		
			
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		originDocument.write(byteArrayOutputStream);
		byteArrayOutputStream.close();
		originDocument.close();
		return byteArrayOutputStream.toByteArray();
	}
	
	private XWPFDocument getArchiveCoverDocument(ArchiveStudent archiveStudent) throws Exception{
		//�Ӵ��̶�ȡģ���ļ�
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.ARCHIVE_COVER_TEMPLATE_NAME);
		
		//��ѧ�������¼������docx ��ȡ��дȨ��
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//ͨ��opcPackage������XWPFDocument�����ĵ�
		XWPFDocument document=new XWPFDocument(opcPackage);
		//��ȡ���ж���
		List<XWPFParagraph> paragraphs=document.getParagraphs();
		
		//�������ж���
		for (XWPFParagraph paragraph : paragraphs) {
			List<XWPFRun> runs=paragraph.getRuns();
			
			//���������Ӿ�
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
		//�Ӵ��̶�ȡģ���ļ�
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.BASE_STUDENT_DOCX_TEMPLATE_NAME);
		
		//��ѧ�������¼������docx ��ȡ��дȨ��
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//ͨ��opcPackage������XWPFDocument�����ĵ�
		XWPFDocument document=new XWPFDocument(opcPackage);
		
		//��ȡ���б��
		List<XWPFTable> tables=document.getTables();
		
		//�������б��
		for (XWPFTable table : tables) {
			//��ȡ����ÿһ��
			List<XWPFTableRow> rows=table.getRows();
			
			//����������
			for (XWPFTableRow row : rows) {
				//��ȡÿ�е�ÿ������
				List<XWPFTableCell> cells=row.getTableCells();
				//����ÿ������
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
		//�Ӵ��̶�ȡģ���ļ�
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.ARCHIVE_INFO_TEMPLATE_NAME);
		
		//��ѧ�������¼������docx ��ȡ��дȨ��
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//ͨ��opcPackage������XWPFDocument�����ĵ�
		XWPFDocument document=new XWPFDocument(opcPackage);
		
		//��ȡ���б��
		List<XWPFTable> tables=document.getTables();
		
		//�������б��
		for (XWPFTable table : tables) {
			//��ȡ����ÿһ��
			List<XWPFTableRow> rows=table.getRows();
			
			//����������
			for (XWPFTableRow row : rows) {
				//��ȡÿ�е�ÿ������
				List<XWPFTableCell> cells=row.getTableCells();
				//����ÿ������ ������ƥ�䴮���滻ģ�� ���������ƥ��ĸ���
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
					
					//��������¼�������ڵ���һ ���滻��һ�б����¼
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
						
						//��������¼�������ڵ��ڶ� ��һ���滻�ڶ��б����¼
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
							//��������¼�������ڵ���3 ��һ���滻��3�б����¼
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
								//��������¼����С��3 ���ʣ��Ĳ����滻�ɿհ�
								replaceCellString(cell, ReplaceMark.CHANGE_TIME_TWO, "");
								replaceCellString(cell, ReplaceMark.RECORDER_NOW_TWO, "");
								replaceCellString(cell, ReplaceMark.CHANGE_REASON_TWO, "");
							}
						}else {
							//��������¼����С��2 ���ʣ��Ĳ����滻�ɿհ�
							replaceCellString(cell, ReplaceMark.CHANGE_TIME_ONE, "");
							replaceCellString(cell, ReplaceMark.RECORDER_NOW_ONE, "");
							replaceCellString(cell, ReplaceMark.CHANGE_REASON_ONE, "");
								
							replaceCellString(cell, ReplaceMark.CHANGE_TIME_TWO, "");
							replaceCellString(cell, ReplaceMark.RECORDER_NOW_TWO, "");
							replaceCellString(cell, ReplaceMark.CHANGE_REASON_TWO, "");
						}
							
					}else {
						//��������¼����С��1 ������еĲ����滻�ɿհ�
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
		//�Ӵ��̶�ȡģ���ļ�
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.WEEKLY_SIMPLE_RECORD_DOCX_TEMPLATE_NAME);
		
		//��ѧ�������¼������docx ��ȡ��дȨ��
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//ͨ��opcPackage������XWPFDocument�����ĵ�
		XWPFDocument document=new XWPFDocument(opcPackage);
		
		//��ȡ���б��
		List<XWPFTable> tables=document.getTables();
		
		//�������б��
		for (XWPFTable table : tables) {
			//��ȡ����ÿһ��
			List<XWPFTableRow> rows=table.getRows();
			
			//����������
			for (XWPFTableRow row : rows) {
				//��ȡÿ�е�ÿ������
				List<XWPFTableCell> cells=row.getTableCells();
				//����ÿ������ ������ƥ�䴮���滻ģ�� ���������ƥ��ĸ���
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
					//��ʣ�µ�ģ��Ǻ��û�Ϊ�մ�
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
		//�Ӵ��̶�ȡģ���ļ�
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.FAMILY_RECORD_DOCX_TEMPLATE_NAME);
		
		//��ѧ�������¼������docx ��ȡ��дȨ��
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//ͨ��opcPackage������XWPFDocument�����ĵ�
		XWPFDocument document=new XWPFDocument(opcPackage);
		
		//��ȡ���б��
		List<XWPFTable> tables=document.getTables();
		
		//�������б��
		for (XWPFTable table : tables) {
			//��ȡ����ÿһ��
			List<XWPFTableRow> rows=table.getRows();
			
			//����������
			for (XWPFTableRow row : rows) {
				//��ȡÿ�е�ÿ������
				List<XWPFTableCell> cells=row.getTableCells();
				//����ÿ������ ������ƥ�䴮���滻ģ�� ���������ƥ��ĸ���
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
		//�Ӵ��̶�ȡģ���ļ�
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.FACE_TALK_RECORD_DOCX_TEMPLATE_NAME);
		
		//��ѧ�������¼������docx ��ȡ��дȨ��
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//ͨ��opcPackage������XWPFDocument�����ĵ�
		XWPFDocument document=new XWPFDocument(opcPackage);
		
		//��ȡ���б��
		List<XWPFTable> tables=document.getTables();
		
		//�������б��
		for (XWPFTable table : tables) {
			//��ȡ����ÿһ��
			List<XWPFTableRow> rows=table.getRows();
			
			//����������
			for (XWPFTableRow row : rows) {
				//��ȡÿ�е�ÿ������
				List<XWPFTableCell> cells=row.getTableCells();
				//����ÿ������
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
		//�Ӵ��̶�ȡģ���ļ�
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.DISCUSS_SUMMARY_RECORD_DOCX_TEMPLATE_NAME);
		
		//��ѧ�������¼������docx ��ȡ��дȨ��
		OPCPackage opcPackage = OPCPackage.open(inputStream);
		
		//ͨ��opcPackage������XWPFDocument�����ĵ�
		XWPFDocument document=new XWPFDocument(opcPackage);
		
		//��ȡ���б��
		List<XWPFTable> tables=document.getTables();
		
		//�������б��
		for (XWPFTable table : tables) {
			//��ȡ����ÿһ��
			List<XWPFTableRow> rows=table.getRows();
			
			//����������
			for (XWPFTableRow row : rows) {
				//��ȡÿ�е�ÿ������
				List<XWPFTableCell> cells=row.getTableCells();
				//����ÿ������
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
		//��ȡ���Ԫ����ַ���
		String cellString=cell.getText();
		if(replaceMark.equals(cellString)) {
			//ɾ��������Ķ���ɾ��
			cell.removeParagraph(0);
			//�����µĶ���
			XWPFParagraph paragraph=new XWPFParagraph(cell.getCTTc().addNewP(), cell);
			//�����µ��Ӿ�
			XWPFRun run=paragraph.createRun();
			//�����Ӿ�����滻������
			run.setText(newCellString);
			//��������
			run.setFontFamily(Constant.FONT_FAMILY);
			//���µĶ���ŵ�������
			cell.addParagraph(paragraph);
		}
	}
	private void replaceCellStringForCondition(XWPFTableCell cell,String replaceMark,String newCellString) {
		//��ȡ���Ԫ����ַ���
		String cellString=cell.getText();
		if(cellString!=null&&cellString.contains(replaceMark)) {
			//ɾ��������Ķ���ɾ��
			cell.removeParagraph(0);
			//�����µĶ���
			XWPFParagraph paragraph=new XWPFParagraph(cell.getCTTc().addNewP(), cell);
			//�����µ��Ӿ�
			XWPFRun run=paragraph.createRun();
			//�����Ӿ�����滻������
			run.setText(cellString.replaceFirst(replaceMark, newCellString));
			//��������
			run.setFontFamily(Constant.FONT_FAMILY);
			//���µĶ���ŵ�������
			cell.addParagraph(paragraph);
		}
	}
	
}
