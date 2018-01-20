package com.uestc.newhelp.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
//��ʷ��¼��Ӧ��ʵ����
public class HistoryRecord {
		//��ʷ��ϵ��¼��id
		private Long historyRecordId;
		//��¼������ʷ����id
		private Long historyArchiveId;
		//��¼������
		private String recordName;
		//��¼ʱ��
		@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
		private Date recordTime;
		//�ص�
		private String location;
		//��֤��
		private String witness;
		//��¼��
		private String recorder;
		//������
		private String participant;
		//��ʽ
		private String way;
		//��Ҫ����
		private String content;
		//��ע
		private String comment;
		
		
		public HistoryRecord() {
			super();
		}
		
		public HistoryRecord(Long historyArchiveId, String recordName) {
			super();
			this.historyArchiveId = historyArchiveId;
			this.recordName = recordName;
		}

		public HistoryRecord(Long historyArchiveId, Record record) {
			super();
			this.historyArchiveId = historyArchiveId;
			this.recordName = record.getRecordName();
			this.recordTime = record.getRecordTime();
			this.location = record.getLocation();
			this.witness = record.getWitness();
			this.recorder = record.getRecorder();
			this.participant = record.getParticipant();
			this.way = record.getWay();
			this.content = record.getContent();
			this.comment = record.getComment();
		}

		public HistoryRecord(Long historyRecordId, Long historyArchiveId, String recordName, Date recordTime,
				String location, String witness, String recorder, String participant, String way, String content,
				String comment) {
			super();
			this.historyRecordId = historyRecordId;
			this.historyArchiveId = historyArchiveId;
			this.recordName = recordName;
			this.recordTime = recordTime;
			this.location = location;
			this.witness = witness;
			this.recorder = recorder;
			this.participant = participant;
			this.way = way;
			this.content = content;
			this.comment = comment;
		}
		public Long getHistoryRecordId() {
			return historyRecordId;
		}
		public void setHistoryRecordId(Long historyRecordId) {
			this.historyRecordId = historyRecordId;
		}
		public Long getHistoryArchiveId() {
			return historyArchiveId;
		}
		public void setHistoryArchiveId(Long historyArchiveId) {
			this.historyArchiveId = historyArchiveId;
		}
		public String getRecordName() {
			return recordName;
		}
		public void setRecordName(String recordName) {
			this.recordName = recordName;
		}
		public Date getRecordTime() {
			return recordTime;
		}
		public void setRecordTime(Date recordTime) {
			this.recordTime = recordTime;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getWitness() {
			return witness;
		}
		public void setWitness(String witness) {
			this.witness = witness;
		}
		public String getRecorder() {
			return recorder;
		}
		public void setRecorder(String recorder) {
			this.recorder = recorder;
		}
		public String getParticipant() {
			return participant;
		}
		public void setParticipant(String participant) {
			this.participant = participant;
		}
		public String getWay() {
			return way;
		}
		public void setWay(String way) {
			this.way = way;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}

		@Override
		public String toString() {
			return "HistoryRecord [historyRecordId=" + historyRecordId + ", historyArchiveId=" + historyArchiveId
					+ ", recordName=" + recordName + ", recordTime=" + recordTime + ", location=" + location
					+ ", witness=" + witness + ", recorder=" + recorder + ", participant=" + participant + ", way="
					+ way + ", content=" + content + ", comment=" + comment + "]";
		}
		
}
