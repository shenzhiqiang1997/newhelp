package com.uestc.newhelp.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.uestc.newhelp.dao.ArchiveStudentDao;
import com.uestc.newhelp.dao.AttentionTypeDao;
import com.uestc.newhelp.dao.HistoryArchiveDao;
import com.uestc.newhelp.dao.HistoryRecordDao;
import com.uestc.newhelp.dao.HistoryRecorderChangeDao;
import com.uestc.newhelp.dao.RecordDao;
import com.uestc.newhelp.dao.RecorderChangeDao;
import com.uestc.newhelp.entity.ArchiveStudent;
import com.uestc.newhelp.entity.AttentionType;
import com.uestc.newhelp.entity.HistoryArchive;
import com.uestc.newhelp.entity.HistoryRecord;
import com.uestc.newhelp.entity.HistoryRecorderChange;
import com.uestc.newhelp.entity.Record;
import com.uestc.newhelp.entity.RecorderChange;
import com.uestc.newhelp.exception.NoSuchStudentException;
import com.uestc.newhelp.exception.NotChoseExportObjectException;
import com.uestc.newhelp.path.FileName;
import com.uestc.newhelp.path.Path;
import com.uestc.newhelp.service.ArchiveStudentService;
import com.uestc.newhelp.type.RecordType;
import com.uestc.newhelp.util.DateUtil;
@Service
public class ArchiveStudentServiceImpl implements ArchiveStudentService {
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
	
	@Override
	public List<ArchiveStudent> list(String teacherId) {
		//��ѯָ����ʦ�İ��ѧ��
		List<ArchiveStudent> archiveStudents=archiveStudentDao.list(teacherId);
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
				List<ArchiveStudent> archiveStudents=archiveStudentDao.search(archiveStudent);
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
	public void add(ArchiveStudent archiveStudent) {
		archiveStudentDao.add(archiveStudent);

	}

	@Override
	public void delete(ArchiveStudent a) {
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
	public byte[] exportArchiveToWordFile(Long studentId) throws IOException,NotChoseExportObjectException,NoSuchStudentException {
		if(studentId==null) {
			throw new NotChoseExportObjectException("��δѡ��Ҫ�����Ķ���,����ѡ����ٵ���");
		}
		ArchiveStudent archiveStudent=archiveStudentDao.get(studentId);
		List<RecorderChange> recorderChanges=recorderChangeDao.list(studentId);
		
		if(archiveStudent==null) {
			throw new NoSuchStudentException("Ҫ������ѧ����δ������");
		}
		Record record=new Record(studentId, RecordType.WEEKLY_SIMPLE_RECORD.value);
		List<Record> weeklyReocrds=recordDao.listOnType(record);
		record.setRecordName(RecordType.FAMILY_RECORD.value);
		List<Record> familyRecords=recordDao.listOnType(record);
		record.setRecordName(RecordType.FACE_TALK_RECORD.value);
		List<Record> faceRecords=recordDao.listOnType(record);
		record.setRecordName(RecordType.DISCUSS_SUMMARY_RECORD.value);
		List<Record> discussRecords=recordDao.listOnType(record);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		FileInputStream inputStream=new FileInputStream(Path.TEMPLATE_BASE_PATH+FileName.ARCHIVE_NAME);
		HWPFDocument document=new HWPFDocument(inputStream);
		Range range=document.getRange();
		
		range.replaceText("${archiveId}", String.valueOf(archiveStudent.getArchiveId()));
		range.replaceText("${name}", archiveStudent.getName()==null?"":archiveStudent.getName());
		range.replaceText("${grade}", String.valueOf(archiveStudent.getGrade()==null?"":archiveStudent.getGrade()));
		range.replaceText("${sex}", archiveStudent.getSex()==null?"":archiveStudent.getSex());
		range.replaceText("${studentId}", String.valueOf(archiveStudent.getStudentId()));
		range.replaceText("${studentClass}", String.valueOf(archiveStudent.getStudentClass()==null?"":archiveStudent.getStudentClass()));
		range.replaceText("${politicalStatus}", archiveStudent.getPoliticalStatus()==null?"":archiveStudent.getPoliticalStatus());
		range.replaceText("${enthicGroup}", archiveStudent.getEthnicGroup()==null?"":archiveStudent.getEthnicGroup());
		range.replaceText("${duty}", archiveStudent.getDuty()==null?"":archiveStudent.getDuty());
		range.replaceText("${dormitory}", archiveStudent.getDormitory()==null?"":archiveStudent.getDormitory());
		range.replaceText("${birthOrgin}", archiveStudent.getBirthOrigin()==null?"":archiveStudent.getBirthOrigin());
		range.replaceText("${familyAddress}", archiveStudent.getFamilyAddress()==null?"":archiveStudent.getFamilyAddress());
		range.replaceText("${contactWay}", archiveStudent.getContactWay()==null?"":archiveStudent.getContactWay());
		range.replaceText("${fatherTelNumber}", archiveStudent.getFamilyTelNumber()==null?"":archiveStudent.getFamilyTelNumber());
		range.replaceText("${motherTelNumber}", archiveStudent.getMotherTelNumber()==null?"":archiveStudent.getMotherTelNumber());
		range.replaceText("${familyCondition}", archiveStudent.getFamilyCondition()==null?"":archiveStudent.getFamilyCondition());
		range.replaceText("${studyCondition}", archiveStudent.getStudyCondition()==null?"":archiveStudent.getStudyCondition());
		range.replaceText("${healthCondition}", archiveStudent.getHealthCondition()==null?"":archiveStudent.getHealthCondition());
		range.replaceText("${lifeCondition}", archiveStudent.getLifeCondition()==null?"":archiveStudent.getLifeCondition());
		range.replaceText("${otherCondition}", archiveStudent.getOtherCondition()==null?"":archiveStudent.getOtherCondition());
		
		range.replaceText("${bulidingBasis}", archiveStudent.getBulidingBasis()==null?"":archiveStudent.getBulidingBasis());
		range.replaceText("${bulidingRecorder}", archiveStudent.getBulidingRecorder()==null?"":archiveStudent.getBulidingRecorder());
		try {
			range.replaceText("${bulidingTime}", sdf.format(archiveStudent.getBulidingTime()));
		} catch (Exception e) {
			range.replaceText("${bulidingTime}", "");
		}
		range.replaceText("${destoryingBasis}", archiveStudent.getDestoryingBasis()==null?"":archiveStudent.getDestoryingBasis());
		range.replaceText("${destoryingRecorder}", archiveStudent.getDestoryingRecorder()==null?"":archiveStudent.getDestoryingRecorder());
		try {
			range.replaceText("${destoryingTime}", sdf.format(archiveStudent.getDestoryingTime()));
		} catch (Exception e) {
			range.replaceText("${destoryingTime}", "");
		}
		range.replaceText("${bulidingPerson}", archiveStudent.getBulidingPerson()==null?"":archiveStudent.getBulidingPerson());
		range.replaceText("${bulidingPersonDuty}", archiveStudent.getBulidingPersonDuty()==null?"":archiveStudent.getBulidingPersonDuty());
		range.replaceText("${helpType}", archiveStudent.getHelpType()==null?"":archiveStudent.getHelpType());
		
		int recorderChangesSize=recorderChanges.size();
		for (int i = 0; i < recorderChangesSize; i++) {
			RecorderChange recorderChange=recorderChanges.get(i);
			try {
				range.replaceText("${changeTime"+i+"}",sdf.format(recorderChange.getChangeTime()));
			} catch (Exception e) {
				range.replaceText("${changeTime"+i+"}","");
			}
			range.replaceText("${recorderNow"+i+"}", recorderChange.getRecorderNow()==null?"":recorderChange.getRecorderNow());
			range.replaceText("${changeReason"+i+"}", recorderChange.getChangeReason()==null?"":recorderChange.getChangeReason());
		}
		
		for (int i =recorderChangesSize; i < 3; i++) {
			range.replaceText("${changeTime"+i+"}","");
			range.replaceText("${recorderNow"+i+"}","");
			range.replaceText("${changeReason"+i+"}","");
		}
		
		int weeklyReocrdsSize=weeklyReocrds.size();
		for (int i = 0; i < weeklyReocrdsSize; i++) {
			Record r=weeklyReocrds.get(i);
			try {
				range.replaceText("${wt"+i+"}", sdf.format(r.getRecordTime()));
			} catch (Exception e) {
				range.replaceText("${wt"+i+"}", "");
			}
			range.replaceText("${wl"+i+"}", r.getLocation()==null?"":r.getLocation());
			range.replaceText("${ww"+i+"}", r.getWay()==null?"":r.getWay());
			range.replaceText("${wcontent"+i+"}", r.getContent()==null?"":r.getContent());
			range.replaceText("${wcomm"+i+"}", r.getComment()==null?"":r.getComment());
		}
		
		for (int i = weeklyReocrdsSize; i <48 ; i++) {
			range.replaceText("${wt"+i+"}", "");
			range.replaceText("${wl"+i+"}", "");
			range.replaceText("${ww"+i+"}", "");
			range.replaceText("${wcontent"+i+"}", "");
			range.replaceText("${wcomm"+i+"}", "");
		}
		
		int familyRecordsSize=familyRecords.size();
		for (int i = 0; i < familyRecordsSize; i++) {
			Record r=familyRecords.get(i);
			try {
				range.replaceText("${ft"+i+"}", sdf.format(r.getRecordTime()));
			} catch (Exception e) {
				range.replaceText("${ft"+i+"}", "");
			}
			range.replaceText("${fl"+i+"}", r.getLocation()==null?"":r.getLocation());
			range.replaceText("${fwit"+i+"}", r.getWitness()==null?"":r.getWitness());
			range.replaceText("${fw"+i+"}", r.getWay()==null?"":r.getWay());
			range.replaceText("${fc"+i+"}", r.getContent()==null?"":r.getContent());
			range.replaceText("${fr"+i+"}", r.getRecorder()==null?"":r.getRecorder());
		}
		
		for (int i = familyRecordsSize; i < 6; i++) {
			range.replaceText("${ft"+i+"}", "");
			range.replaceText("${fl"+i+"}", "");
			range.replaceText("${fwit"+i+"}", "");
			range.replaceText("${fw"+i+"}", "");
			range.replaceText("${fc"+i+"}", "");
			range.replaceText("${fr"+i+"}", "");
		}
		
		int faceRecordsSize=faceRecords.size();
		for (int i = 0; i < faceRecordsSize; i++) {
			Record r=familyRecords.get(i);
			try {
				range.replaceText("${fat"+i+"}", sdf.format(r.getRecordTime()));
			} catch (Exception e) {
				range.replaceText("${fat"+i+"}", "");
			}
			range.replaceText("${fal"+i+"}", r.getLocation()==null?"":r.getLocation());
			range.replaceText("${faw"+i+"}", r.getWay()==null?"":r.getWay());
			range.replaceText("${fac"+i+"}", r.getContent()==null?"":r.getContent());
			range.replaceText("${far"+i+"}", r.getRecorder()==null?"":r.getRecorder());
		}
		
		for (int i = faceRecordsSize; i <10 ; i++) {
			range.replaceText("${fat"+i+"}", "");
			range.replaceText("${fal"+i+"}", "");
			range.replaceText("${faw"+i+"}", "");
			range.replaceText("${fac"+i+"}", "");
			range.replaceText("${far"+i+"}", "");
		}
		
		int discussRecordsSize=discussRecords.size();
		for (int i = 0; i < discussRecordsSize; i++) {
			Record r=familyRecords.get(i);
			try {
				range.replaceText("${dt"+i+"}", sdf.format(r.getRecordTime()));
			} catch (Exception e) {
				range.replaceText("${dt"+i+"}", "");
			}
			range.replaceText("${dl"+i+"}", r.getLocation()==null?"":r.getLocation());
			range.replaceText("${dp"+i+"}", r.getParticipant()==null?"":r.getParticipant());
			range.replaceText("${dc"+i+"}", r.getContent()==null?"":r.getContent());
			range.replaceText("${dr"+i+"}", r.getRecorder()==null?"":r.getRecorder());
		}
		
		for (int i = discussRecordsSize; i <6 ; i++) {
			range.replaceText("${dt"+i+"}", "");
			range.replaceText("${dl"+i+"}", "");
			range.replaceText("${dp"+i+"}", "");
			range.replaceText("${dc"+i+"}", "");
			range.replaceText("${dr"+i+"}", "");
		}

		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		document.write(byteArrayOutputStream);
		byteArrayOutputStream.close();
		document.close();
		return byteArrayOutputStream.toByteArray();
	}

}
