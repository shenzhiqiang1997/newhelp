package com.uestc.newhelp.constant;

public interface Regex {
	String STUDENT_ID="^\\d{13}$";
	String NAME="^[\\s\\S]{0,15}$";
	String GRADE="^\\d{4}$";
	String STUDENT_CLASS="^\\d{10}$";
	String SEX="^[\\s\\S]{0,2}$";
	String DUTY="^[\\s\\S]{0,20}$";
	String DORMITORY="^[\\s\\S]{0,40}$";
	String CONTACT_WAY="^\\d{11}|[\\s\\S]{0,30}$";
	String ID_CARD_NUMBER="^((\\d{18})|([0-9x]{18})|([0-9X]{18}))|[\\s\\S]{0,18}$";
	String QQ_NUMBER="^[1-9][0-9]{4,19}|[\\s\\S]{0,19}$";
	String EMAIL="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*|[\\s\\S]{0,32}$";
	String BIRTHDAY="^(19|20)\\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])|(19|20)\\d{2}/(1[0-2]|0?[1-9])/(0?[1-9]|[1-2][0-9]|3[0-1])|(19|20)\\d{2}.(1[0-2]|0?[1-9]).(0?[1-9]|[1-2][0-9]|3[0-1])|[\\s\\S]{0,}$";
	String HEIGHT="^[1-2]\\d{2}\\.{0,1}\\d{0,1}|[\\s\\S]{0,5}$";
	String MAJOR="^[\\u4e00-\\u9fa5£¨£©\\s()]{0,20}|[\\s\\S]{0,20}$";
	String STUDY_CONDITION="^[\\u4e00-\\u9fa5]{0,6}$";
	String POLITICAL_STATUS="^[\\u4e00-\\u9fa5]{0,4}$";
	String ETHNIC_GROUP="^[\\s\\S]{0,10}$";
	String BIRTH_ORIGIN="^[\\s\\S]{0,20}$";
	String COLLEGE_ENTRANCE_EXAM_SCORE="^\\d{0,3}\\.{0,1}\\d{0,1}(/\\d{0,3}){0,1}|[\\s\\S]{0,9}$";
	String COLLEGE_ENTRANCE_EXAM_ENGLISH_SCORE="^\\d{0,3}\\.{0,1}\\d{0,1}(/\\d{0,3})|[\\s\\S]{0,9}$";
	String ENTRANCE_EXAM_ENGLISH_SCORE="^\\d{0,3}\\.{0,1}\\d{0,1}|[\\s\\S]{0,5}$";
	String HOMETOWN_RAILWAY_STATION="^[\\s\\S]{0,12}$";
	String PROVINCE="^[\\s\\S]{0,8}$";
	String CITY="^[\\s\\S]{0,15}$";
	String FAMILY_ADDRESS="^[\\s\\S]{0,40}$";
	String FAMILY_TEL_NUMBER="^\\d{3}-\\d{8}|\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d{4}-\\d{9}|[\\s\\S]{0,30}$";
	String POSTCODE="^[0-9]\\d{5}(?!\\d)|[\\s\\S]{0,6}$";
	String SPECIALTY="^[\\s\\S]{0,30}$";
	String DUTY_IN_HIGH_SCHOOL="^[\\s\\S]{0,40}$";
	String AWARD_IN_HIGH_SCHOOL="^[\\s\\S]{0,200}$";
	String IS_HAD_TECHNOLOGY_COMPETITION_AWARD="^[\\s\\S]{0,200}$";
	String WORK_UNIT="^[\\s\\S]{0,40}$";
	String WORK_UNIT_DETAIL="^[\\S\\s]{0,60}$";
}
