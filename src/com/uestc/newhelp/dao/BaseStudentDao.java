package com.uestc.newhelp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uestc.newhelp.dto.BaseStudentCount;
import com.uestc.newhelp.dto.Page;
import com.uestc.newhelp.entity.BaseStudent;
import com.uestc.newhelp.entity.ExposeSetting;

public interface BaseStudentDao {
	//��ѯָ��ѧ������������Ϣ
	public BaseStudent getAll(Long studentId);
	//��ѯָ��ѧ������������Ϣ
	public BaseStudent getPersonalInfo(Long studentId);
	//��ѯָ��ѧ��������ͥ��Ϣ
	public BaseStudent getFamilyInfo(Long studentId);
	//
	public short getGrade(Long studentId);
	//��ѯָ��ѧ�����ڽ�������Ϣ
	public BaseStudent getArchiveInfo(Long studentId);
	//���ݰ����ʦ�ı�¶��������ѯѧ��������Ϣ�б�
	public List<BaseStudent> list();
	//����������������ѧ��������Ϣ�б�
	public List<BaseStudent> search(@Param("baseStudent")BaseStudent baseStudent,@Param("teacherGrade")Short teacherGrade,
			@Param("page")Page page,@Param("classSort")Integer classSort);
	//����������������ѧ��������Ϣ�б�
	public List<BaseStudent> searchWithoutPage(@Param("baseStudent")BaseStudent baseStudent,@Param("teacherGrade")Short teacherGrade);
	//������������ͳ��ѧ������
	public int searchRecordNum(@Param("baseStudent")BaseStudent baseStudent,@Param("teacherGrade")Short teacherGrade);
	//��ѯָ��������ѧ��ѧ��
	public List<Long> listStudentIdByName(String name);
	//��������ѧ��������Ϣ
	public void addBatch(@Param("baseStudents")List<BaseStudent> baseStudents,@Param("exposeSetting")ExposeSetting exposeSetting);
	//����ָ��ѧ���Ļ�����Ϣ
	public void update(@Param("baseStudent")BaseStudent baseStudent);
	//����ѧ��������ѯѧ��������Ϣ
	public List<BaseStudent> listByIds(@Param("studentIds")List<Long> studentIds,@Param("exposeSetting") ExposeSetting exposeSetting,@Param("grade")Short grade);
	//ͳ������
	public Integer count(@Param("baseStudent")BaseStudent baseStudent,@Param("teacherGrade")Short teacherGrade);
	//��id ��������ģ��
	public List<BaseStudent> searchByIdName(@Param("studentId")Long studentId,@Param("name")String name);
	//������������ͳ��ѧ������
	public int searchRecordNumByCondition(@Param("baseStudent")BaseStudent baseStudent,@Param("teacherGrade")Short teacherGrade,
			@Param("studyCondition1")String studyCondition1,@Param("studyCondition2")String studyCondition2);
	//����������������ѧ��������Ϣ�б�
	public List<BaseStudent> searchByCondition(@Param("baseStudent")BaseStudent baseStudent,@Param("teacherGrade")Short teacherGrade,
				@Param("page")Page page,@Param("classSort")Integer classSort,
				@Param("studyCondition1")String studyCondition1,@Param("studyCondition2")String studyCondition2);
}
