package com.uestc.newhelp.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.entity.Record;

public interface RecordDao {
	//增加记录
	public void add(@Param("record")Record record);
	//批量增加记录
	public void addBatch(@Param("records")List<Record> records);
	//删除指定建档学生的记录
	public void deleteByStudentId(Long studentId);
	//批量删除指定建档学生的记录
	public void deleteBatch(@Param("recordIds")List<Long> recordIds);
	//更新指定建档学生的记录
	public void update(@Param("record")Record record);
	//查询指定记录
	public Record get(Long recordId);
	//查询指定建档学生的指定类型记录
	public List<Record> listOnType(@Param("record")Record record);
	//查询指定建档学生的最后一次记录时间
	public Date getLastRecordTime(Long studentId);
	//查询指定id的记录
	public List<Record> listByIds(@Param("recordIds")List<Long> recordIds);
}
