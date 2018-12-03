package com.uestc.newhelp.util;

import java.util.Date;

public class DateUtil {
	//一天的时间(毫秒)
	private static long oneDayTime=1000*3600*24;
	//得到目标日期与当天的时间差(毫秒)
	public static long getIntervalBetweenToday(Date date) {
		Date today=new Date();
		long todayTime=today.getTime();
		todayTime=todayTime-(todayTime%(oneDayTime));
		long dateTime=date.getTime();
		long interval=todayTime-dateTime;
		return interval;
	}
	//查看时间差是否大于目标周时间差(毫秒)
	public static boolean remindInterval(long interval,byte weekCount) {
		if(interval>weekCount*oneDayTime*7) {
			return true;
		}else {
			return false;
		}
	}
}
