package com.uestc.newhelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.dao.HistoryArchiveDao;
import com.uestc.newhelp.dao.HistoryRecorderChangeDao;
import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.entity.HistoryArchive;
import com.uestc.newhelp.entity.HistoryRecorderChange;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.service.HistoryArchiveService;
@Service
public class HistoryArchiveServiceImpl implements HistoryArchiveService {
	@Autowired
	private HistoryArchiveDao historyArchiveDao;
	@Autowired
	private HistoryRecorderChangeDao historyRecorderChangeDao;
	@Autowired
	private TeacherDao teacherDao;
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

}
