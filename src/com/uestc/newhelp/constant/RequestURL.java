package com.uestc.newhelp.constant;

public interface RequestURL {
	// 基本学生信息查看
	public String BASE_STUDENT_SEE_LIST_URL="/newhelp/api/baseStudents";
	// 基本学生信息查看
	public String BASE_STUDENT_SEE_ONE_URL="/newhelp/api/baseStudent";
	// 基本学生信息修改
	public String BASE_STUDENT_EDIT_URL="/newhelp/api/baseStudent";
	// 基本学生信息导入EXCEL模板下载
	public String BASE_STUDENT_IMPORT_TEMPLATE_URL="/newhelp/api/download/baseStudentTemplate";
	// 基本学生信息导入EXCEL列表
	public String BASE_STUDENT_IMPORT_URL="/newhelp/api/import/baseStudent";
	// 基本学生信息导出EXCEL列表
	public String BASE_STUDENT_EXPORT_URL="/newhelp/api/export/baseStudent";
	// 困难学生档案查看
	public String ARCHIVE_STUDENT_SEE_LIST_URL="/newhelp/api/archiveStudents";
	// 困难学生档案查看
	public String ARCHIVE_STUDENT_SEE_ONE_URL="/newhelp/api/archiveStudent";
	// 困难学生档案修改
	public String ARCHIVE_STUDENT_URL="/newhelp/api/archiveStudent";
	// 困难学生档案变更
	public String ARCHIVE_STUDENT_CHANGE_URL="/newhelp/api/change";
	// 困难学生档案导出整本
	public String ARCHIVE_STUDENT_EXPORT_URL="/newhelp/api/export/archive";
	// 困难学生记录查看
	public String ARCHIVE_RECORD_SEE_LIST_URL="/newhelp/api/records";
	// 困难学生记录查看
	public String ARCHIVE_RECORD_SEE_ONE_URL="/newhelp/api/record";
	// 困难学生记录修改
	public String ARCHIVE_RECORD_URL="/newhelp/api/record";
	// 困难学生记录删除
	public String ARCHIVE_RECORD_DELETE_URL="/newhelp/api/records";
	// 困难学生记录导出
	public String ARCHIVE_RECORD_EXPORT_URL="/newhelp/api/export/record";
	// 困难学生历史档案查看
	public String HISTORY_ARCHIVE_SEE_LIST_URL="/newhelp/api/historyArchives";
	// 困难学生历史档案查看
	public String HISTORY_ARCHIVE_SEE_ONE_URL="/newhelp/api/historyArchive";
	// 困难学生历史档案删除
	public String HISTORY_ARCHIVE_DELETE_URL="/newhelp/api/historyArchives";
	// 困难学生历史记录查看
	public String HISTORY_RECORD_SEE_LIST_URL="/newhelp/api/historyRecords";
	// 困难学生历史记录查看
	public String HISTORY_RECORD_SEE_ONE_URL="/newhelp/api/historyRecord";
	
}
