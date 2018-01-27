package com.uestc.newhelp.entity;

public class Authorization {
	// 权限id
	private Long authorizationId;
	// 所属教师用户名
	private String teacherId;
	// 后台管理
	private Byte backEndHandle;
	// 基本学生信息查看
	private Byte baseStudentSee;
	// 基本学生信息修改
	private Byte baseStudentEdit;
	// 基本学生信息导入Excel列表
	private Byte baseStudentImport;
	// 基本学生信息导出Excel列表
	private Byte baseStudentExport;
	// 困难学生档案查看
	private Byte archiveStudentSee;
	// 困难学生档案修改
	private Byte archiveStudentEdit;
	// 困难学生档案变更
	private Byte archiveStudentChange;
	// 困难学生档案建档
	private Byte archiveStudentBuild;
	// 困难学生档案除档
	private Byte archiveStudentDestory;
	// 困难学生档案导出整本
	private Byte archiveStudentExport;
	// 困难学生记录查看
	private Byte archiveRecordSee;
	// 困难学生记录修改
	private Byte archiveRecordEdit;
	// 困难学生记录新增
	private Byte archiveRecordAdd;
	// 困难学生记录导出
	private Byte archiveRecordExport;
	// 困难学生历史档案查看
	private Byte historyArchiveSee;
	// 困难学生历史记录查看
	private Byte historyRecordSee;

	public Long getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(Long authorizationId) {
		this.authorizationId = authorizationId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Byte getBackEndHandle() {
		return backEndHandle;
	}

	public void setBackEndHandle(Byte backEndHandle) {
		this.backEndHandle = backEndHandle;
	}

	public Byte getBaseStudentSee() {
		return baseStudentSee;
	}

	public void setBaseStudentSee(Byte baseStudentSee) {
		this.baseStudentSee = baseStudentSee;
	}

	public Byte getBaseStudentEdit() {
		return baseStudentEdit;
	}

	public void setBaseStudentEdit(Byte baseStudentEdit) {
		this.baseStudentEdit = baseStudentEdit;
	}

	public Byte getBaseStudentImport() {
		return baseStudentImport;
	}

	public void setBaseStudentImport(Byte baseStudentImport) {
		this.baseStudentImport = baseStudentImport;
	}

	public Byte getBaseStudentExport() {
		return baseStudentExport;
	}

	public void setBaseStudentExport(Byte baseStudentExport) {
		this.baseStudentExport = baseStudentExport;
	}

	public Byte getArchiveStudentSee() {
		return archiveStudentSee;
	}

	public void setArchiveStudentSee(Byte archiveStudentSee) {
		this.archiveStudentSee = archiveStudentSee;
	}

	public Byte getArchiveStudentEdit() {
		return archiveStudentEdit;
	}

	public void setArchiveStudentEdit(Byte archiveStudentEdit) {
		this.archiveStudentEdit = archiveStudentEdit;
	}

	public Byte getArchiveStudentChange() {
		return archiveStudentChange;
	}

	public void setArchiveStudentChange(Byte archiveStudentChange) {
		this.archiveStudentChange = archiveStudentChange;
	}

	public Byte getArchiveStudentBuild() {
		return archiveStudentBuild;
	}

	public void setArchiveStudentBuild(Byte archiveStudentBuild) {
		this.archiveStudentBuild = archiveStudentBuild;
	}

	public Byte getArchiveStudentDestory() {
		return archiveStudentDestory;
	}

	public void setArchiveStudentDestory(Byte archiveStudentDestory) {
		this.archiveStudentDestory = archiveStudentDestory;
	}

	public Byte getArchiveStudentExport() {
		return archiveStudentExport;
	}

	public void setArchiveStudentExport(Byte archiveStudentExport) {
		this.archiveStudentExport = archiveStudentExport;
	}

	public Byte getArchiveRecordSee() {
		return archiveRecordSee;
	}

	public void setArchiveRecordSee(Byte archiveRecordSee) {
		this.archiveRecordSee = archiveRecordSee;
	}

	public Byte getArchiveRecordEdit() {
		return archiveRecordEdit;
	}

	public void setArchiveRecordEdit(Byte archiveRecordEdit) {
		this.archiveRecordEdit = archiveRecordEdit;
	}

	public Byte getArchiveRecordAdd() {
		return archiveRecordAdd;
	}

	public void setArchiveRecordAdd(Byte archiveRecordAdd) {
		this.archiveRecordAdd = archiveRecordAdd;
	}

	public Byte getArchiveRecordExport() {
		return archiveRecordExport;
	}

	public void setArchiveRecordExport(Byte archiveRecordExport) {
		this.archiveRecordExport = archiveRecordExport;
	}

	public Byte getHistoryArchiveSee() {
		return historyArchiveSee;
	}

	public void setHistoryArchiveSee(Byte historyArchiveSee) {
		this.historyArchiveSee = historyArchiveSee;
	}

	public Byte getHistoryRecordSee() {
		return historyRecordSee;
	}

	public void setHistoryRecordSee(Byte historyRecordSee) {
		this.historyRecordSee = historyRecordSee;
	}

	@Override
	public String toString() {
		return "Authorization [authorizationId=" + authorizationId + ", teacherId=" + teacherId + ", backEndHandle="
				+ backEndHandle + ", baseStudentSee=" + baseStudentSee + ", baseStudentEdit=" + baseStudentEdit
				+ ", baseStudentImport=" + baseStudentImport + ", baseStudentExport=" + baseStudentExport
				+ ", archiveStudentSee=" + archiveStudentSee + ", archiveStudentEdit=" + archiveStudentEdit
				+ ", archiveStudentChange=" + archiveStudentChange + ", archiveStudentBuild=" + archiveStudentBuild
				+ ", archiveStudentDestory=" + archiveStudentDestory + ", archiveStudentExport=" + archiveStudentExport
				+ ", archiveRecordSee=" + archiveRecordSee + ", archiveRecordEdit=" + archiveRecordEdit
				+ ", archiveRecordAdd=" + archiveRecordAdd + ", archiveRecordExport=" + archiveRecordExport
				+ ", historyArchiveSee=" + historyArchiveSee + ", historyRecordSee=" + historyRecordSee + "]";
	}

}
