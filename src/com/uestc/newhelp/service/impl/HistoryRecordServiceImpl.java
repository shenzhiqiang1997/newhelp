package com.uestc.newhelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.dao.HistoryRecordDao;
import com.uestc.newhelp.entity.HistoryRecord;
import com.uestc.newhelp.service.HistoryRecordService;

@Service
public class HistoryRecordServiceImpl implements HistoryRecordService {
	@Autowired
	private HistoryRecordDao historyRecordDao;
	@Override
	public List<HistoryRecord> list(HistoryRecord historyRecord) {
		List<HistoryRecord> historyRecords=historyRecordDao.listOnType(historyRecord);
		return historyRecords;
	}

	@Override
	public HistoryRecord get(Long historyRecordId) {
		HistoryRecord historyRecord=historyRecordDao.get(historyRecordId);
		return historyRecord;
	}
	

}
