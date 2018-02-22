CREATE DATABASE newhelp;

USE newhelp;

CREATE TABLE teacher_(
	teacher_id VARCHAR(13) COMMENT '教师用户名',
	password VARCHAR(32) COMMENT '教师密码',
	name VARCHAR(15) COMMENT '教师姓名',
	duty VARCHAR(10) COMMENT '教师职务',
	grade SMALLINT UNSIGNED COMMENT '教师年级',
	PRIMARY KEY(teacher_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '教师表';

CREATE TABLE base_student_(
	student_id BIGINT UNSIGNED COMMENT '学生学号',
	name VARCHAR(15) COMMENT '学生姓名',
	grade SMALLINT UNSIGNED COMMENT '学生年级',
	student_class INT UNSIGNED COMMENT '学生班级',
	sex VARCHAR(2) COMMENT '学生性别',
	photo_url VARCHAR(80) COMMENT '学生照片url',
	duty VARCHAR(20) COMMENT '学生职务',
	dormitory VARCHAR(40) COMMENT '学生宿舍',
	contact_way VARCHAR(14) COMMENT '学生联系方式',
	id_card_number VARCHAR(18) COMMENT '学生身份证号码',
	qq_number BIGINT UNSIGNED COMMENT '学生QQ号码',
	email VARCHAR(32) COMMENT '学生邮箱地址',
	birthday DATE COMMENT '学生生日',
	height FLOAT(4,1) COMMENT '学生身高',
	major VARCHAR(20) COMMENT '学生专业',
	study_condition VARCHAR(6) DEFAULT '在读' COMMENT '学生学业状态',
	political_status VARCHAR(12) COMMENT '学生政治面貌',
	ethnic_group VARCHAR(10) COMMENT '学生民族',
	birth_origin VARCHAR(20) COMMENT '学生籍贯',
	college_entrance_exam_score VARCHAR(9) COMMENT '学生高考成绩/满分',
	college_entrance_exam_english_score VARCHAR(9) COMMENT '学生高考英语成绩/满分',
	entrance_exam_english_score TINYINT UNSIGNED COMMENT '学生英语入校考试成绩',
	hometown_railway_station VARCHAR(12) COMMENT '学生家乡所在火车站',
	province VARCHAR(8) COMMENT '学生省份',
	city VARCHAR(24) COMMENT '学生所在城市',
	family_address VARCHAR(40) COMMENT '学生家庭详细地址',
	family_tel_number VARCHAR(30) COMMENT '学生家庭电话',
	postcode VARCHAR(6) COMMENT '学生邮政编码',
	specialty VARCHAR(30) COMMENT '学生特长',
	duty_in_high_school VARCHAR(40) COMMENT '学生高中曾任职务',
	award_in_high_school VARCHAR(200) COMMENT '学生高中曾获奖励',
	is_had_technology_competition_award VARCHAR(200) COMMENT '学生是否有科技竞赛类获奖',
	father_name VARCHAR(15) COMMENT '学生父亲姓名',
	father_work_unit VARCHAR(40) COMMENT '学生父亲工作单位',
	father_work_unit_address VARCHAR(60) COMMENT '学生父亲工作单位详细地址',
	father_duty VARCHAR(20) COMMENT '学生父亲职务',
	father_postcode VARCHAR(6) COMMENT '学生父亲邮编',
	father_tel_number VARCHAR(30) COMMENT '学生父亲电话',
	mother_name VARCHAR(15) COMMENT '学生母亲姓名',
	mother_work_unit VARCHAR(40) COMMENT '学生母亲工作单位',
	mother_work_unit_address VARCHAR(60) COMMENT '学生母亲工作单位详细地址',
	mother_duty VARCHAR(20) COMMENT '学生母亲职务',
	mother_postcode VARCHAR(6) COMMENT '学生母亲邮编',
	mother_tel_number VARCHAR(30) COMMENT '学生母亲电话',
	PRIMARY KEY(student_id),
	INDEX idx_name(name),
	INDEX idx_grade(grade),
	INDEX idx_student_class(student_class),
	INDEX idx_sex(sex),
	INDEX idx_duty(duty),
	INDEX idx_major(major),
	INDEX idx_political_status(political_status),
	INDEX idx_ethnic_group(ethnic_group),
	INDEX idx_birth_origin(birth_origin),
	INDEX idx_province(province),
	INDEX idx_city(city),
	INDEX idx_father_name(father_name),
	INDEX idx_mother_name(mother_name)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '学生基本信息表';

CREATE TABLE expose_setting_(
	expose_setting_id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '暴露设置id',
	teacher_id VARCHAR(13) COMMENT '暴露设置所属的教师用户名',
	expose_student_id TINYINT UNSIGNED DEFAULT 1 COMMENT '是否暴露学生学号',
	expose_name TINYINT UNSIGNED DEFAULT 1 COMMENT '是否暴露学生姓名',
	expose_grade TINYINT UNSIGNED UNSIGNED DEFAULT 1 COMMENT '是否暴露学生年级',
	expose_student_class TINYINT UNSIGNED DEFAULT 1 COMMENT '是否暴露学生班级',
	expose_sex TINYINT UNSIGNED DEFAULT 1 COMMENT '是否暴露学生性别',
	expose_duty TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生职务',
	expose_dormitory TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生宿舍',
	expose_contact_way TINYINT UNSIGNED DEFAULT 1 COMMENT '是否暴露学生联系方式',
	expose_id_card_number TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生身份证号码',
	expose_qq_number TINYINT UNSIGNED DEFAULT 1 COMMENT '是否暴露学生QQ号码',
	expose_email TINYINT UNSIGNED DEFAULT 1 COMMENT '是否暴露学生邮箱地址',
	expose_birthday TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生生日',
	expose_height TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生身高',
	expose_major TINYINT UNSIGNED DEFAULT 1 COMMENT '是否暴露学生专业',
	expose_study_condition TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生学业状态',
	expose_political_status TINYINT UNSIGNED DEFAULT 1 COMMENT '是否暴露学生政治面貌',
	expose_ethnic_group TINYINT UNSIGNED DEFAULT 1 COMMENT '是否暴露学生民族',
	expose_birth_origin TINYINT UNSIGNED DEFAULT 1 COMMENT '是否暴露学生籍贯',
	expose_college_entrance_exam_score TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生高考成绩/满分',
	expose_college_entrance_exam_english_score TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生高考英语成绩/满分',
	expose_entrance_exam_english_score TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生英语入校考试成绩',
	expose_hometown_railway_station TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生家乡所在火车站',
	expose_province TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生省份',
	expose_city TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生所在城市',
	expose_family_address TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生家庭详细地址',
	expose_family_tel_number TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生家庭电话',
	expose_postcode TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生邮政编码',
	expose_specialty TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生特长',
	expose_duty_in_high_school TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生高中曾任职务',
	expose_award_in_high_school TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生高中曾获奖励',
	expose_is_had_technology_competition_award TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生是否有科技竞赛类获奖',
	expose_father_name TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生父亲姓名',
	expose_father_work_unit TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生父亲工作单位',
	expose_father_work_unit_address TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生父亲工作单位详细地址',
	expose_father_duty TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生父亲职务',
	expose_father_postcode TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生父亲邮编',
	expose_father_tel_number TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生父亲电话',
	expose_mother_name TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生母亲姓名',
	expose_mother_work_unit TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生母亲工作单位',
	expose_mother_work_unit_address TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生母亲工作单位详细地址',
	expose_mother_duty TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生母亲职务',
	expose_mother_postcode TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生母亲邮编',
	expose_mother_tel_number TINYINT UNSIGNED DEFAULT 0 COMMENT '是否暴露学生母亲电话',
	PRIMARY KEY(expose_setting_id),
	UNIQUE KEY(teacher_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '教师暴露设置表';

CREATE TABLE import_setting_(
	import_setting_id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '导入设置id',
	teacher_id VARCHAR(13) COMMENT '导入设置所属的教师用户名',
	import_student_id TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生学号',
	import_name TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生姓名',
	import_grade TINYINT UNSIGNED UNSIGNED DEFAULT 1 COMMENT '是否导入学生年级',
	import_student_class TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生班级',
	import_sex TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生性别',
	import_duty TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生职务',
	import_dormitory TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生宿舍',
	import_contact_way TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生联系方式',
	import_id_card_number TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生身份证号码',
	import_qq_number TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生QQ号码',
	import_email TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生邮箱地址',
	import_birthday TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生生日',
	import_height TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生身高',
	import_major TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生专业',
	import_study_condition TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生学业状态',
	import_political_status TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生政治面貌',
	import_ethnic_group TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生民族',
	import_birth_origin TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生籍贯',
	import_college_entrance_exam_score TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生高考成绩/满分',
	import_college_entrance_exam_english_score TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生高考英语成绩/满分',
	import_entrance_exam_english_score TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生英语入校考试成绩',
	import_hometown_railway_station TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生家乡所在火车站',
	import_province TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生省份',
	import_city TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生所在城市',
	import_family_address TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生家庭详细地址',
	import_family_tel_number TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生家庭电话',
	import_postcode TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生邮政编码',
	import_specialty TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生特长',
	import_duty_in_high_school TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生高中曾任职务',
	import_award_in_high_school TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生高中曾获奖励',
	import_is_had_technology_competition_award TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生是否有科技竞赛类获奖',
	import_father_name TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生父亲姓名',
	import_father_work_unit TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生父亲工作单位',
	import_father_work_unit_address TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生父亲工作单位详细地址',
	import_father_duty TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生父亲职务',
	import_father_postcode TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生父亲邮编',
	import_father_tel_number TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生父亲电话',
	import_mother_name TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生母亲姓名',
	import_mother_work_unit TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生母亲工作单位',
	import_mother_work_unit_address TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生母亲工作单位详细地址',
	import_mother_duty TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生母亲职务',
	import_mother_postcode TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生母亲邮编',
	import_mother_tel_number TINYINT UNSIGNED DEFAULT 1 COMMENT '是否导入学生母亲电话',
	PRIMARY KEY(import_setting_id),
	UNIQUE KEY(teacher_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '教师导入设置表';

CREATE TABLE archive_student_(
	archive_id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '档案编号',
	student_id BIGINT UNSIGNED COMMENT '档案学生学号',
	teacher_id VARCHAR(13) NOT NULL COMMENT '档案教师用户名',
	sex VARCHAR(2) COMMENT '档案学生性别',
	name VARCHAR(15) COMMENT '档案学生姓名',
	major VARCHAR(10) COMMENT '建档学生专业',
	grade SMALLINT UNSIGNED COMMENT '档案学生年级',
	student_class INT UNSIGNED COMMENT '档案学生班级',
	political_status VARCHAR(6) COMMENT '档案学生政治面貌',
	ethnic_group VARCHAR(5) COMMENT '档案学生民族',
	duty VARCHAR(20) COMMENT '档案学生职务',
	dormitory VARCHAR(15) COMMENT '档案学生宿舍',
	birth_origin VARCHAR(10) COMMENT '档案学生生源地',
	family_address VARCHAR(30) COMMENT '档案学生家庭住址',
	contact_way VARCHAR(14) COMMENT '档案学生联系方式',
	family_tel_number VARCHAR(14) COMMENT '档案学生家庭电话',
	father_tel_number VARCHAR(14) COMMENT '档案学生父亲电话',
	mother_tel_number VARCHAR(14) COMMENT '档案学生母亲电话',
	family_condition VARCHAR(300) COMMENT '档案学生家庭情况',
	study_condition VARCHAR(300) COMMENT '档案学生学习情况',
	health_condition VARCHAR(300) COMMENT '档案学生身心情况',
	life_condition VARCHAR(300) COMMENT '档案学生生活情况',
	other_condition VARCHAR(300) COMMENT '档案其他情况',
	buliding_basis VARCHAR(1000) COMMENT '建档依据',
	buliding_recorder VARCHAR(15) COMMENT '建档记录人',
	buliding_time DATE COMMENT '建档时间',
	destorying_basis VARCHAR(1000) COMMENT '除档依据',
	destorying_recorder VARCHAR(15) COMMENT '除档记录人',
	destorying_time DATE COMMENT '除档时间',
	buliding_person VARCHAR(15) COMMENT '建档人',
	buliding_person_duty VARCHAR(15) COMMENT '建档人职务',
	help_type VARCHAR(15) COMMENT '帮扶类型',
	attention_type VARCHAR(4) COMMENT '关注状态',
	last_record_time DATE COMMENT '该学生末次记录时间',
	PRIMARY KEY(archive_id),
	UNIQUE INDEX idx_student_id(student_id),
	INDEX idx_name(name),
	INDEX idx_grade(grade),
	INDEX idx_student_class(student_class),
	INDEX idx_sex(sex),
	INDEX idx_major(major),
	INDEX idx_ethnic_group(ethnic_group),
	INDEX idx_help_type(help_type),
	INDEX idx_attention_type(attention_type)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '帮扶建档学生信息表';

CREATE TABLE recorder_change_(
	recorder_change_id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '记录人变更记录id',
	student_id BIGINT UNSIGNED NOT NULL COMMENT '属于的档案学生学号',
	change_time DATE COMMENT '变更时间',
	recorder_now VARCHAR(15) COMMENT '现记录人',
	change_reason VARCHAR(50) COMMENT '变更原因',
	PRIMARY KEY(recorder_change_id),
	INDEX idx_student_id(student_id),
	INDEX idx_change_time(change_time)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '记录人变更记录';

CREATE TABLE record_(
		record_id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '记录表id',
		student_id BIGINT UNSIGNED NOT NULL COMMENT '记录所属档案学生学号',
		record_name VARCHAR(10) COMMENT '记录表名称',
		record_time DATE COMMENT '记录时间',
		location VARCHAR(20) COMMENT '地点',
		witness VARCHAR(20) COMMENT '见证人',
		recorder VARCHAR(20) COMMENT '记录人',
		participant VARCHAR(20) COMMENT '参与人',
		way VARCHAR(16) COMMENT '方式',
		content TEXT COMMENT '主要内容',
		comment VARCHAR(36) COMMENT '备注',
		PRIMARY KEY(record_id),
		INDEX idx_record_time(record_time),
		INDEX idx_student_id(student_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '联系记录表';

CREATE TABLE help_type_(
	help_type_id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '帮扶类型id',
	help_type_name VARCHAR(10) COMMENT '帮扶类型名称',
	PRIMARY KEY (help_type_id),
	UNIQUE INDEX idx_help_type_name(help_type_name)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '帮扶类型种类表';

CREATE TABLE attention_type_(
	attention_type_id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '关注类型id',
	attention_type_name VARCHAR(10) COMMENT '关注类型名称',
	remind_record_interval TINYINT UNSIGNED COMMENT '该种关注类型提醒记录间隔',
	PRIMARY KEY (attention_type_id),
	UNIQUE INDEX idx_attention_type_name(attention_type_name)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '关注类型种类表';

CREATE TABLE history_archive_(
	history_archive_id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '历史建档id',
	student_id BIGINT UNSIGNED NOT NULL COMMENT '档案学生学号',
	teacher_id VARCHAR(13) NOT NULL COMMENT '档案教师用户名',
	sex VARCHAR(2) COMMENT '档案学生性别',
	name VARCHAR(15) COMMENT '档案学生姓名',
	major VARCHAR(10) COMMENT '档案学生专业',
	grade SMALLINT UNSIGNED COMMENT '档案学生年级',
	student_class INT UNSIGNED COMMENT '档案学生班级',
	political_status VARCHAR(6) COMMENT '档案学生政治面貌',
	ethnic_group VARCHAR(5) COMMENT '档案学生民族',
	duty VARCHAR(20) COMMENT '档案学生职务',
	dormitory VARCHAR(15) COMMENT '档案学生宿舍',
	birth_origin VARCHAR(10) COMMENT '档案学生生源地',
	family_address VARCHAR(30) COMMENT '档案学生家庭住址',
	contact_way VARCHAR(14) COMMENT '档案学生联系方式',
	family_tel_number VARCHAR(14) COMMENT '档案学生家庭电话',
	father_tel_number VARCHAR(14) COMMENT '档案学生父亲电话',
	mother_tel_number VARCHAR(14) COMMENT '档案学生母亲电话',
	family_condition VARCHAR(300) COMMENT '档案学生家庭情况',
	study_condition VARCHAR(300) COMMENT '档案学生学习情况',
	health_condition VARCHAR(300) COMMENT '档案学生身心情况',
	life_condition VARCHAR(300) COMMENT '档案学生生活情况',
	other_condition VARCHAR(300) COMMENT '档案其他情况',
	buliding_basis VARCHAR(1000) COMMENT '建档依据',
	buliding_recorder VARCHAR(15) COMMENT '建档记录人',
	buliding_time DATE COMMENT '建档时间',
	destorying_basis VARCHAR(1000) COMMENT '除档依据',
	destorying_recorder VARCHAR(15) COMMENT '除档记录人',
	destorying_time DATE COMMENT '除档时间',
	buliding_person VARCHAR(15) COMMENT '建档人',
	buliding_person_duty VARCHAR(15) COMMENT '建档人职务',
	help_type VARCHAR(15) COMMENT '帮扶类型',
	attention_type VARCHAR(4) COMMENT '关注状态',
	last_record_time DATE COMMENT '该学生末次记录时间',
	PRIMARY KEY(history_archive_id),
	INDEX idx_name(name),
	INDEX idx_grade(grade),
	INDEX student_class(student_class),
	INDEX idx_sex(sex),
	INDEX idx_major(major),
	INDEX idx_ethnic_group(ethnic_group),
	INDEX idx_help_type(help_type),
	INDEX idx_attention_type(attention_type)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '历史建档信息表';

CREATE TABLE history_recorder_change_(
	history_recorder_change_id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '历史记录人变更记录id',
	history_archive_id BIGINT UNSIGNED NOT NULL COMMENT '属于的历史档案的id',
	change_time DATE COMMENT '变更时间',
	recorder_now VARCHAR(15) COMMENT '现记录人',
	change_reason VARCHAR(50) COMMENT '变更原因',
	PRIMARY KEY(history_recorder_change_id),
	INDEX idx_student_id(history_archive_id),
	INDEX idx_change_time(change_time)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '历史记录人变更记录';

CREATE TABLE history_record_(
		history_record_id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '历史联系记录表id',
		history_archive_id BIGINT UNSIGNED NOT NULL COMMENT '记录所属历史档案id',
		record_name VARCHAR(10) COMMENT '记录表名称',
		record_time DATE COMMENT '记录时间',
		location VARCHAR(20) COMMENT '地点',
		witness VARCHAR(20) COMMENT '见证人',
		recorder VARCHAR(20) COMMENT '记录人',
		participant VARCHAR(20) COMMENT '参与人',
		way VARCHAR(16) COMMENT '方式',
		content TEXT COMMENT '主要内容',
		comment VARCHAR(36) COMMENT '备注',
		PRIMARY KEY(history_record_id),
		INDEX idx_record_time(record_time),
		INDEX idx_student_id(history_archive_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '历史联系记录表';

CREATE TABLE token_(
	teacher_id VARCHAR(13) COMMENT 'token所属教师用户名',
	token_value VARCHAR(100) COMMENT 'token值',
	PRIMARY KEY pk_teacher_id(teacher_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT 'token存储表';

CREATE TABLE authorization_(
	authorization_id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '权限id',
	teacher_id VARCHAR(13) COMMENT '所属教师用户名',
	back_end_handle TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '后台管理',
	base_student_see TINYINT(1) UNSIGNED DEFAULT 1 COMMENT '基本学生信息查看',
	base_student_edit TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '基本学生信息修改',
	base_student_import TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '基本学生信息导入Excel列表',
	base_student_export TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '基本学生信息导出Excel列表',
	archive_student_see TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生档案查看',
	archive_student_edit TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生档案修改',
	archive_student_change TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生档案变更',
	archive_student_build TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生档案建档',
	archive_student_destory TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生档案除档',
	archive_student_export TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生档案导出整本',
	archive_record_see TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生记录查看',
	archive_record_edit TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生记录修改',
	archive_record_add TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生记录新增',
	archive_record_delete TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生记录删除',
	archive_record_export TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生记录导出',
	history_archive_see TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生历史档案查看',
	history_record_see TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '困难学生历史记录查看',
	PRIMARY KEY(authorization_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '权限表';

CREATE TABLE log_(
	log_id BIGINT UNSIGNED AUTO_INCREMENT COMMENT '日志id',
	teacher_id VARCHAR(13) COMMENT '操作人',
	ip VARCHAR(15) COMMNET '操作ip',
	operate_time DATETIME COMMENT '操作时间',
	content VARCHAR(40) COMMENT '操作内容',
	result TINYINT(1) UNSIGNED DEFAULT 1 COMMENT '操作结果',
	message VARCHAR(30) DEFAULT '无' COMMENT '操作消息',
	PRIMARY KEY(log_id),
	INDEX operate_time_idx(operate_time)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '日志表';