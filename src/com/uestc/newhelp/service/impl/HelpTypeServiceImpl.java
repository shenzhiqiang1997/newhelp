package com.uestc.newhelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.dao.HelpTypeDao;
import com.uestc.newhelp.entity.HelpType;
import com.uestc.newhelp.service.HelpTypeService;
@Service
public class HelpTypeServiceImpl implements HelpTypeService {
	@Autowired
	private HelpTypeDao helpTypeDao;
	
	@Override
	public List<HelpType> list() {
		List<HelpType> helpTypes=helpTypeDao.list();
		return helpTypes;
	}

	@Override
	public void add(HelpType helpType) {
		helpTypeDao.add(helpType);
	}

	@Override
	public void delete(Long helpTypeId) {
		helpTypeDao.delete(helpTypeId);
	}

}
