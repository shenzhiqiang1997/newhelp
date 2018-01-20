package com.uestc.newhelp.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
//历史记录对应的实体类
public class HistoryRecord {
		//历史联系记录表id
		private Long historyRecordId;
		//记录所属历史档案id
		private Long historyArchiveId;
		//记录表名称
		private String recordName;
		//记录时间
		@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
		private Date recordTime;
		//地点
		private String location;
		//见证人
		private String witness;
		//记录人
		private String recorder;
		//参与人
		private String participant;
		//方式
		private String way;
		//主要内容
		private String content;
		//备注
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
