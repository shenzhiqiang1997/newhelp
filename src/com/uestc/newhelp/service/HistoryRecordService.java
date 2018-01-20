package com.uestc.newhelp.service;

import java.util.List;

import com.uestc.newhelp.entity.HistoryRecord;
//与历史档案有关的业务逻辑
public interface HistoryRecordService {
	//查询指定学生历史记录列表
	public List<HistoryRecord> list(HistoryRecord historyRecord);
	//查询指定历史记录详细
	public HistoryRecord get(Long historyRecordId);
	
}
