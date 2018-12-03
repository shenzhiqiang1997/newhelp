package com.uestc.newhelp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.newhelp.dao.AuthorizationDao;
import com.uestc.newhelp.entity.Authorization;
import com.uestc.newhelp.service.AuthorizationService;
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
	@Autowired
	private AuthorizationDao authorizationDao;
	@Override
	public Authorization get(String teacherId) {
		Authorization authorization=authorizationDao.get(teacherId);
		//如果账号权限不存在 则授予默认权限
		if (authorization==null) {
			authorizationDao.add(teacherId);
			authorization=authorizationDao.get(teacherId);
		}
		return authorization;
	}

}
