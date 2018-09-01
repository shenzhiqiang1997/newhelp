package com.uestc.newhelp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.dao.ArchiveStudentDao;
import com.uestc.newhelp.dao.ArchiveVisibilityDao;
import com.uestc.newhelp.dao.HistoryArchiveDao;
import com.uestc.newhelp.dao.HistoryRecordDao;
import com.uestc.newhelp.dao.HistoryRecorderChangeDao;
import com.uestc.newhelp.dao.RecordDao;
import com.uestc.newhelp.dao.RecorderChangeDao;
import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.entity.ArchiveStudent;
import com.uestc.newhelp.entity.ArchiveVisibility;
import com.uestc.newhelp.entity.HistoryArchive;
import com.uestc.newhelp.entity.HistoryRecord;
import com.uestc.newhelp.entity.HistoryRecorderChange;
import com.uestc.newhelp.entity.Record;
import com.uestc.newhelp.entity.RecorderChange;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.service.RecorderChangeService;
@Service
public class RecordChangeServiceImpl implements RecorderChangeService {
	
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private ArchiveStudentDao archiveStudentDao;
	@Autowired
	private RecorderChangeDao recorderChangeDao;
	@Autowired
	private ArchiveVisibilityDao archiveVisibilityDao;
	/*@Autowired
	private RecordDao recordDao;
	@Autowired
	private HistoryArchiveDao historyArchiveDao;
	@Autowired
	private HistoryRecorderChangeDao historyRecorderChangeDao;
	@Autowired
	private HistoryRecordDao historyRecordDao;*/
	
	@Override
	public void add(RecorderChange recorderChange,String newTeacherId) {
		//��ȡ�����Ŀ���ʦ����Ϣ
		Teacher newTeacher=teacherDao.getInfo(newTeacherId);
		//���ñ��ʱ��,Ĭ��Ϊ��ǰʱ��
		recorderChange.setChangeTime(new Date());
		//�����ּ�¼��Ϊ���Ŀ���ʦ
		recorderChange.setRecorderNow(newTeacher.getName());
		//�������¼���뵽���ݿ���
		recorderChangeDao.add(recorderChange);
		
		//��õ�������ѧ��ѧ��
		Long studentId=recorderChange.getStudentId();
		//����ѧ�Ż�ȡ���䵵��
		ArchiveStudent archiveStudent=archiveStudentDao.get(studentId);
		//ʹ�øñ���ĵ����Ա��ǰ�Ľ�ʦ�ɼ�
		ArchiveVisibility archiveVisibility = 
				new ArchiveVisibility(archiveStudent.getTeacherId(), archiveStudent.getArchiveId());
		archiveVisibilityDao.add(archiveVisibility);
		
		/*//��ѯ�õ����ļ�¼�˱����¼
		List<RecorderChange> recorderChanges=recorderChangeDao.list(studentId);
		Record record=new Record();
		record.setStudentId(studentId);
		//��ѯ�õ����ļ�¼
		List<Record> records=recordDao.listOnType(record);
				
		//������ʷ����,������ǰҪ����ĵ�����Ϣ����������
		HistoryArchive historyArchive=new HistoryArchive(archiveStudent);
		historyArchiveDao.add(historyArchive);
		
		//��������ʷ�����ļ�¼�˱����¼
		List<HistoryRecorderChange> historyRecorderChanges=new ArrayList<>();
		//��������ʷ��������ʷ��¼
		List<HistoryRecord> historyRecords=new ArrayList<>();
				
		if(recorderChanges!=null&&recorderChanges.size()>0) {
			//����ǰҪ����ĵ����ļ�¼�˱����¼��������ʷ�����¼��
			for (RecorderChange r : recorderChanges) {
				HistoryRecorderChange historyRecorderChange=new HistoryRecorderChange(historyArchive.getHistoryArchiveId(), r);
				historyRecorderChanges.add(historyRecorderChange);
				//����ʷ���������Ϣ��ŵ����ݿ���
				historyRecorderChangeDao.addBatch(historyRecorderChanges);
			}
		}
		if(records!=null&&records.size()>0) {
			//����ǰҪ����ĵ����ļ�¼��������ʷ��¼��
			for (Record r : records) {
				HistoryRecord historyRecord=new HistoryRecord(historyArchive.getHistoryArchiveId(), r);
				historyRecords.add(historyRecord);
			}
			//����ʷ���������Ϣ��ŵ����ݿ���
			historyRecordDao.addBatch(historyRecords);
		}*/
		
		//�ѵ�ǰ�����İ����ʦ�ĳ��µ���ʦ
		archiveStudent.setTeacherId(newTeacherId);
		archiveStudentDao.update(archiveStudent);
	}

}
