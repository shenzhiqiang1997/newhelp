package com.uestc.newhelp.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.newhelp.dao.ImportSettingDao;
import com.uestc.newhelp.entity.ImportSetting;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class TestImportSettingDao {
	@Autowired
	private ImportSettingDao importSettingDao;
	@Test
	public void testAdd() {
		ImportSetting importSetting=new ImportSetting();
		importSetting.setTeacherId("20162201");
		importSettingDao.add(importSetting);
	}
	@Test
	public void testGet() {
		ImportSetting importSetting=importSettingDao.get("20162201");
		System.out.println(importSetting);
		System.out.println(importSetting.getImportStudyCondition());
	}
	@Test
	public void testUpdate() {
		ImportSetting importSetting=new ImportSetting();
		importSetting.setImportSettingId(1L);
		importSetting.setImportStudyCondition((byte)0);
		importSettingDao.update(importSetting);
	}
}

