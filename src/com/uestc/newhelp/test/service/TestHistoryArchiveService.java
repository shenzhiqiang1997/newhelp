package com.uestc.newhelp.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.entity.HistoryArchive;
import com.uestc.newhelp.service.HistoryArchiveService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestHistoryArchiveService {
	@Autowired
	private HistoryArchiveService archiveService;
	@Test
	public void testList() {
		List<HistoryArchive> historyArchives=archiveService.list("20162201");
		for (HistoryArchive historyArchive : historyArchives) {
			System.out.println(historyArchive);
		}
	}
	@Test
	public void testGet() {
		HistoryArchive archive=archiveService.get(1L);
		System.out.println(archive);
	}
}
