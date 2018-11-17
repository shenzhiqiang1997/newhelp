package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.dto.BaseStudentCount;
import com.uestc.newhelp.dto.Page;
import com.uestc.newhelp.entity.BaseStudent;
import com.uestc.newhelp.entity.ExposeSetting;

public interface BaseStudentDao {
	//查询指定学生基本所有信息
	public BaseStudent getAll(Long studentId);
	//查询指定学生基本个人信息
	public BaseStudent getPersonalInfo(Long studentId);
	//查询指定学生基本家庭信息
	public BaseStudent getFamilyInfo(Long studentId);
	//
	public short getGrade(Long studentId);
	//查询指定学生用于建档的信息
	public BaseStudent getArchiveInfo(Long studentId);
	//根据帮扶老师的暴露设置来查询学生基本信息列表
	public List<BaseStudent> list();
	//根据搜索条件搜索学生基本信息列表
	public List<BaseStudent> search(@Param("baseStudent")BaseStudent baseStudent,@Param("teacherGrade")Short teacherGrade,
			@Param("page")Page page,@Param("classSort")Integer classSort);
	//根据搜索条件搜索学生基本信息列表
	public List<BaseStudent> searchWithoutPage(@Param("baseStudent")BaseStudent baseStudent,@Param("teacherGrade")Short teacherGrade);
	//根据搜索条件统计学生条数
	public int searchRecordNum(@Param("baseStudent")BaseStudent baseStudent,@Param("teacherGrade")Short teacherGrade);
	//查询指定姓名的学生学号
	public List<Long> listStudentIdByName(String name);
	//批量增加学生基本信息
	public void addBatch(@Param("baseStudents")List<BaseStudent> baseStudents,@Param("exposeSetting")ExposeSetting exposeSetting);
	//更新指定学生的基本信息
	public void update(@Param("baseStudent")BaseStudent baseStudent);
	//根据学号批量查询学生基本信息
	public List<BaseStudent> listByIds(@Param("studentIds")List<Long> studentIds,@Param("exposeSetting") ExposeSetting exposeSetting,@Param("grade")Short grade);
	//统计人数
	public Integer count(@Param("baseStudent")BaseStudent baseStudent,@Param("teacherGrade")Short teacherGrade);
	//按id 姓名查找模糊
	public List<BaseStudent> searchByIdName(@Param("studentId")Long studentId,@Param("name")String name);
	//根据搜索条件统计学生条数
	public int searchRecordNumByCondition(@Param("baseStudent")BaseStudent baseStudent,@Param("teacherGrade")Short teacherGrade,
			@Param("studyCondition1")String studyCondition1,@Param("studyCondition2")String studyCondition2);
	//根据搜索条件搜索学生基本信息列表
	public List<BaseStudent> searchByCondition(@Param("baseStudent")BaseStudent baseStudent,@Param("teacherGrade")Short teacherGrade,
				@Param("page")Page page,@Param("classSort")Integer classSort,
				@Param("studyCondition1")String studyCondition1,@Param("studyCondition2")String studyCondition2);
}
