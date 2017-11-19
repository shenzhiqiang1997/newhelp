package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.HistoryRecorderChange;

public interface HistoryRecorderChangeDao {
	//批量增加历史记录变更记录
	public void addBatch(@Param("historyRecorderChanges")List<HistoryRecorderChange> historyRecorderChanges);
	//查询指定历史档案的记录变更记录
	public List<HistoryRecorderChange> list(Long historyArchiveId);
}
