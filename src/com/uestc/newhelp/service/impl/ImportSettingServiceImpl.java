package com.uestc.newhelp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.dao.ImportSettingDao;
import com.uestc.newhelp.dao.TeacherDao;
import com.uestc.newhelp.entity.ImportSetting;
import com.uestc.newhelp.entity.Teacher;
import com.uestc.newhelp.exception.NoAuthorityException;
import com.uestc.newhelp.service.ImportSettingService;

@Service
public class ImportSettingServiceImpl implements ImportSettingService {
	
	@Autowired
	private ImportSettingDao importSettingDao;
	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	public void update(ImportSetting importSetting) throws NoAuthorityException {
		Teacher teacher=teacherDao.getInfo(importSetting.getTeacherId());
		if(teacher==null||teacher.getGrade()!=(short)1) {
			throw new NoAuthorityException("你无权限更新导入设置,请联系具有管理员权限的用户导入");
		}
		importSettingDao.update(importSetting);
	}

	@Override
	public ImportSetting get(String teacherId) throws NoAuthorityException {
		Teacher teacher=teacherDao.getInfo(teacherId);
		if(teacher==null||teacher.getGrade()!=(short)1) {
			throw new NoAuthorityException("你无权限设置导入设置,请联系具有管理员权限的用户导入");
		}
		ImportSetting importSetting=importSettingDao.get(teacherId);
		if(importSetting==null) {
			ImportSetting setting=new ImportSetting();
			setting.setTeacherId(teacherId);
			importSettingDao.add(setting);
			importSetting=importSettingDao.get(teacherId);
		}
		return importSetting;
	}

}
