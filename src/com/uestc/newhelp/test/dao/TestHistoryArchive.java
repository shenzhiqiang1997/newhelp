package com.uestc.newhelp.test.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.HistoryArchiveDao;
import com.uestc.newhelp.entity.HistoryArchive;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestHistoryArchive {
	@Autowired
	private HistoryArchiveDao historyArchiveDao;
	/*@Test
	public void testAdd() {
		HistoryArchive historyArchive=new HistoryArchive(null, 2016220305024L, "20162201", "男", "申志强", "软件技术", (short)2016, 2016220305, "汉族", "无", "239", "四川", "四川", "18581517585", "68874298", "无", "15058859819", "asdasqwe", "sdfghfwqe", "sdgdfvbqwe", "qweiqohiufsd", "asdiouiqwe", "qweoiuqwie", "戴瑞婷", new Date(), "asdasd", "戴瑞婷", new Date(), "戴瑞婷", "辅导员", "心理困难", "一般关注", new Date());
		historyArchiveDao.add(historyArchive);
	}*/
	@Test
	public void testGet() {
		HistoryArchive historyArchive=historyArchiveDao.get(1L);
		System.out.println(historyArchive);
	}
	/*@Test
	public void testList() {
		List<HistoryArchive> historyArchives=historyArchiveDao.list("20162201");
		for (HistoryArchive historyArchive : historyArchives) {
			System.out.println(historyArchive);
		}
	}*/
}
