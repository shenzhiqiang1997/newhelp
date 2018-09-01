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
		//获取到变更目标教师的信息
		Teacher newTeacher=teacherDao.getInfo(newTeacherId);
		//设置变更时间,默认为当前时间
		recorderChange.setChangeTime(new Date());
		//设置现记录人为变更目标教师
		recorderChange.setRecorderNow(newTeacher.getName());
		//将变更记录加入到数据库中
		recorderChangeDao.add(recorderChange);
		
		//获得档案所属学生学号
		Long studentId=recorderChange.getStudentId();
		//根据学号获取到其档案
		ArchiveStudent archiveStudent=archiveStudentDao.get(studentId);
		//使得该变更的档案对变更前的教师可见
		ArchiveVisibility archiveVisibility = 
				new ArchiveVisibility(archiveStudent.getTeacherId(), archiveStudent.getArchiveId());
		archiveVisibilityDao.add(archiveVisibility);
		
		/*//查询该档案的记录人变更记录
		List<RecorderChange> recorderChanges=recorderChangeDao.list(studentId);
		Record record=new Record();
		record.setStudentId(studentId);
		//查询该档案的记录
		List<Record> records=recordDao.listOnType(record);
				
		//创建历史档案,并把先前要变更的档案信息拷贝到其中
		HistoryArchive historyArchive=new HistoryArchive(archiveStudent);
		historyArchiveDao.add(historyArchive);
		
		//创建该历史档案的记录人变更记录
		List<HistoryRecorderChange> historyRecorderChanges=new ArrayList<>();
		//创建该历史档案的历史记录
		List<HistoryRecord> historyRecords=new ArrayList<>();
				
		if(recorderChanges!=null&&recorderChanges.size()>0) {
			//将先前要变更的档案的记录人变更记录拷贝到历史变更记录中
			for (RecorderChange r : recorderChanges) {
				HistoryRecorderChange historyRecorderChange=new HistoryRecorderChange(historyArchive.getHistoryArchiveId(), r);
				historyRecorderChanges.add(historyRecorderChange);
				//把历史档案相关信息存放到数据库中
				historyRecorderChangeDao.addBatch(historyRecorderChanges);
			}
		}
		if(records!=null&&records.size()>0) {
			//将先前要变更的档案的记录拷贝到历史记录中
			for (Record r : records) {
				HistoryRecord historyRecord=new HistoryRecord(historyArchive.getHistoryArchiveId(), r);
				historyRecords.add(historyRecord);
			}
			//把历史档案相关信息存放到数据库中
			historyRecordDao.addBatch(historyRecords);
		}*/
		
		//把当前档案的帮扶老师改成新的老师
		archiveStudent.setTeacherId(newTeacherId);
		archiveStudentDao.update(archiveStudent);
	}

}
