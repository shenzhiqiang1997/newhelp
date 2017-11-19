package com.uestc.newhelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.dao.AttentionTypeDao;
import com.uestc.newhelp.entity.AttentionType;
import com.uestc.newhelp.service.AttentionTypeService;
@Service
public class AttentionTypeServiceImpl implements AttentionTypeService {
	@Autowired
	private AttentionTypeDao attentionTypeDao;
	
	@Override
	public List<AttentionType> list() {
		List<AttentionType> attentionTypes=attentionTypeDao.list();
		return attentionTypes;
	}

	@Override
	public void add(AttentionType attentionType) {
		attentionTypeDao.add(attentionType);

	}

	@Override
	public void delete(Long attentionTypeId) {
		attentionTypeDao.delete(attentionTypeId);

	}

	@Override
	public void update(AttentionType attentionType) {
		attentionTypeDao.update(attentionType);

	}

}
