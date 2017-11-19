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
		//查询指定教师的帮扶学生
		List<ArchiveStudent> archiveStudents=archiveStudentDao.list(teacherId);
		//查询所有关注类型列表
		List<AttentionType> attentionTypes=attentionTypeDao.list();
		//存放不同关注类型提醒周长
		Map<String,Byte> attentionTypeMap=new HashMap<>();
		//将所有关注类型的提醒间隔周长以(关注类型,对应提醒周长)的形式存放到map中
		for (AttentionType attentionType : attentionTypes) {
			attentionTypeMap.put(attentionType.getAttentionTypeName(), attentionType.getRemindRecordInterval());
		}
		//遍历查询出的帮扶学生列表
		for (ArchiveStudent archiveStudent : archiveStudents) {
			boolean remind=false;
			if(archiveStudent.getLastRecordTime()!=null) {
				//获得最后一次记录时间与现在的时间差
				long interval=DateUtil.getIntervalBetweenToday(archiveStudent.getLastRecordTime());
				//获取对应学生的提醒间隔周长
				Byte weekCount=attentionTypeMap.get(archiveStudent.getAttentionType());
				//如果没有对应的关注类型,则直接不高亮
				if(weekCount==null) {
					remind=false;
					continue;
				}
				//判断时间差是否超过提醒间隔时长
				remind=DateUtil.remindInterval(interval,weekCount);
			}
			//如果超过则将该帮扶学生的高亮属性设置为true,否则为false
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
				//根据搜索条件搜索指定教师的帮扶学生
				List<ArchiveStudent> archiveStudents=archiveStudentDao.search(archiveStudent);
				//查询所有关注类型列表
				List<AttentionType> attentionTypes=attentionTypeDao.list();
				//存放不同关注类型提醒周长
				Map<String,Byte> attentionTypeMap=new HashMap<>();
				//将所有关注类型的提醒间隔周长以(关注类型,对应提醒周长)的形式存放到map中
				for (AttentionType attentionType : attentionTypes) {
					attentionTypeMap.put(attentionType.getAttentionTypeName(), attentionType.getRemindRecordInterval());
				}
				//遍历查询出的帮扶学生列表
				for (ArchiveStudent archiveStudent1 : archiveStudents) {
					boolean remind=false;
					//如果该学生记录过
					if(archiveStudent1.getLastRecordTime()!=null) {
						//获得最后一次记录时间与现在的时间差
						long interval=DateUtil.getIntervalBetweenToday(archiveStudent1.getLastRecordTime());
						//获取对应学生的提醒间隔周长
						byte weekCount=attentionTypeMap.get(archiveStudent1.getAttentionType());
						//判断时间差是否超过提醒间隔时长
						remind=DateUtil.remindInterval(interval,weekCount);
					}
					//如果超过则将该帮扶学生的高亮属性设置为true,否则为false
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
		//先将删档信息录入
		archiveStudentDao.update(a);
		//查询帮扶学生档案
		ArchiveStudent archiveStudent=archiveStudentDao.get(a.getStudentId());
		//查询该档案的记录人变更记录
		List<RecorderChange> recorderChanges=recorderChangeDao.list(a.getStudentId());
		Record record=new Record();
		record.setStudentId(a.getStudentId());
		//查询该档案的记录
		List<Record> records=recordDao.listOnType(record);
		
		//创建历史档案,并把先前要删除的档案信息拷贝到其中
		HistoryArchive historyArchive=new HistoryArchive(archiveStudent);
		historyArchiveDao.add(historyArchive);
		
		//创建该历史档案的记录人变更记录
		List<HistoryRecorderChange> historyRecorderChanges=new ArrayList<>();
		//创建该历史档案的历史记录
		List<HistoryRecord> historyRecords=new ArrayList<>();
		
		if(recorderChanges!=null&&recorderChanges.size()>0) {
			//将先前要删除的档案的记录人变更记录拷贝到历史变更记录中
			for (RecorderChange recorderChange : recorderChanges) {
				HistoryRecorderChange historyRecorderChange=new HistoryRecorderChange(historyArchive.getHistoryArchiveId(), recorderChange);
				historyRecorderChanges.add(historyRecorderChange);
			}
			//把历史档案相关信息存放到数据库中
			historyRecorderChangeDao.addBatch(historyRecorderChanges);
			//将要删除的档案删除掉
			recorderChangeDao.delete(a.getStudentId());
		}
		if(records!=null&&records.size()>0) {
			//将先前要删除的档案的记录拷贝到历史记录中
			for (Record r : records) {
				HistoryRecord historyRecord=new HistoryRecord(historyArchive.getHistoryArchiveId(), r);
				historyRecords.add(historyRecord);
			}
			//把历史档案相关信息存放到数据库中
			historyRecordDao.addBatch(historyRecords);
			//将要删除的档案删除掉
			recordDao.deleteByStudentId(a.getStudentId());
		}
		
		//将要删除的档案删除掉
		archiveStudentDao.delete(a.getStudentId());
		
		

	}

	@Override
	public void update(ArchiveStudent archiveStudent) {
		archiveStudentDao.update(archiveStudent);

	}

	@Override
	public ArchiveStudent get(Long studentId) {
		//查询档案详细信息
		ArchiveStudent archiveStudent=archiveStudentDao.get(studentId);
		
		//查询对应档案的更改记录并设置到bean中
		List<RecorderChange> recorderChanges=recorderChangeDao.list(studentId);
		archiveStudent.setRecorderChanges(recorderChanges);
		return archiveStudent;
	}

	@Override
	public byte[] exportArchiveToWordFile(Long studentId) throws IOException,NotChoseExportObjectException,NoSuchStudentException {
		if(studentId==null) {
			throw new NotChoseExportObjectException("尚未选择要导出的对象,请先选择后再导出");
		}
		ArchiveStudent archiveStudent=archiveStudentDao.get(studentId);
		List<RecorderChange> recorderChanges=recorderChangeDao.list(studentId);
		
		if(archiveStudent==null) {
			throw new NoSuchStudentException("要导出的学生并未建档！");
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
