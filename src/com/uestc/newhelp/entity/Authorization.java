package com.uestc.newhelp.entity;

public class Authorization {
	// Ȩ��id
	private Long authorizationId;
	// ������ʦ�û���
	private String teacherId;
	// ��̨����
	private Byte backEndHandle;
	// ����ѧ����Ϣ�鿴
	private Byte baseStudentSee;
	// ����ѧ����Ϣ�޸�
	private Byte baseStudentEdit;
	// ����ѧ����Ϣ����Excel�б�
	private Byte baseStudentImport;
	// ����ѧ����Ϣ����Excel�б�
	private Byte baseStudentExport;
	// ����ѧ�������鿴
	private Byte archiveStudentSee;
	// ����ѧ�������޸�
	private Byte archiveStudentEdit;
	// ����ѧ���������
	private Byte archiveStudentChange;
	// ����ѧ����������
	private Byte archiveStudentBuild;
	// ����ѧ����������
	private Byte archiveStudentDestory;
	// ����ѧ��������������
	private Byte archiveStudentExport;
	// ����ѧ����¼�鿴
	private Byte archiveRecordSee;
	// ����ѧ����¼�޸�
	private Byte archiveRecordEdit;
	// ����ѧ����¼����
	private Byte archiveRecordAdd;
	// ����ѧ����¼����
	private Byte archiveRecordExport;
	// ����ѧ����ʷ�����鿴
	private Byte historyArchiveSee;
	// ����ѧ����ʷ��¼�鿴
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
