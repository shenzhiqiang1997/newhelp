package com.uestc.newhelp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.dao.ExposeSettingDao;
import com.uestc.newhelp.entity.ExposeSetting;
import com.uestc.newhelp.service.ExposeSettingService;
@Service
public class ExposeSettingServiceImpl implements ExposeSettingService {
	
	@Autowired
	private ExposeSettingDao exposeSettingDao;
	
	@Override
	public void update(ExposeSetting exposeSetting) {
		exposeSettingDao.update(exposeSetting);
	}

	@Override
	public ExposeSetting get(String teacherId) {
		ExposeSetting exposeSetting=exposeSettingDao.get(teacherId);
		if(exposeSetting==null) {
			ExposeSetting setting=new ExposeSetting();
			setting.setTeacherId(teacherId);
			exposeSettingDao.add(setting);
			exposeSetting=exposeSettingDao.get(teacherId);
		}
		return exposeSetting;
	}

}
