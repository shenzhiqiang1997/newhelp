package com.uestc.newhelp.service;

import com.uestc.newhelp.entity.Authorization;

public interface AuthorizationService {
	//查询指定教师的权限
	public Authorization get(String teacherId);
}
