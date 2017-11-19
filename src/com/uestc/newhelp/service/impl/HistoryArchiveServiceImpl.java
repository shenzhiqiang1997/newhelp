package com.uestc.newhelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.dao.HistoryArchiveDao;
import com.uestc.newhelp.dao.HistoryRecorderChangeDao;
import com.uestc.newhelp.entity.HistoryArchive;
import com.uestc.newhelp.entity.HistoryRecorderChange;
import com.uestc.newhelp.service.HistoryArchiveService;
@Service
public class HistoryArchiveServiceImpl implements HistoryArchiveService {
	@Autowired
	private HistoryArchiveDao historyArchiveDao;
	@Autowired
	private HistoryRecorderChangeDao historyRecorderChangeDao;
	@Override
	public List<HistoryArchive> list(String teacherId) {
		List<HistoryArchive> historyArchives=historyArchiveDao.list(teacherId);
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
		List<HistoryArchive> historyArchives=historyArchiveDao.search(historyArchive);
		return historyArchives;
	}

}
