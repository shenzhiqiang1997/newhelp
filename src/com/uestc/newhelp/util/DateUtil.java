package com.uestc.newhelp.util;

import java.util.Date;

public class DateUtil {
	//һ���ʱ��(����)
	private static long oneDayTime=1000*3600*24;
	//�õ�Ŀ�������뵱���ʱ���(����)
	public static long getIntervalBetweenToday(Date date) {
		Date today=new Date();
		long todayTime=today.getTime();
		todayTime=todayTime-(todayTime%(oneDayTime));
		long dateTime=date.getTime();
		long interval=todayTime-dateTime;
		return interval;
	}
	//�鿴ʱ����Ƿ����Ŀ����ʱ���(����)
	public static boolean remindInterval(long interval,byte weekCount) {
		if(interval>weekCount*oneDayTime*7) {
			return true;
		}else {
			return false;
		}
	}
}
