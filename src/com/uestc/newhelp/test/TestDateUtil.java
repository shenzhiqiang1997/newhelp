package com.uestc.newhelp.test;

import java.util.Date;

import org.junit.Test;

import com.uestc.newhelp.util.DateUtil;

public class TestDateUtil {
	@Test
	public void test() {
		@SuppressWarnings("deprecation")
		long time=DateUtil.getIntervalBetweenToday(new Date(2017, 12, 4));
		boolean flag=DateUtil.remindInterval(time, (byte)1);
		System.out.println(flag);
	}
}
