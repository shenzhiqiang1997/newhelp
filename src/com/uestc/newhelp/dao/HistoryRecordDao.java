package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.HistoryRecord;

public interface HistoryRecordDao {
	//批量增加历史档案记录
	public void addBatch(@Param("historyRecords")List<HistoryRecord> historyRecords);
	//查询指定档案记录id的历史档案记录
	public HistoryRecord get(Long historyRecordId);
	//查询指定历史档案id的指定类型的历史档案记录
	public List<HistoryRecord> listOnType(@Param("historyRecord")HistoryRecord historyRecord);
	
}
