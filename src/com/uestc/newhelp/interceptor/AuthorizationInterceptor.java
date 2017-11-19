package com.uestc.newhelp.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.uestc.newhelp.dao.TokenDao;
import com.uestc.newhelp.util.TokenUtil;

import io.jsonwebtoken.Claims;

public class AuthorizationInterceptor implements HandlerInterceptor{
	@Autowired
	private TokenDao tokenDao;
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		//����Ԥ����
		if("OPTIONS".equals(request.getMethod())) return true;
		//��ȡ��token
		String token=request.getHeader("Authorization");
		//�����ȡ����token,��������
		if(token==null) {
			response.setStatus(401);
			return false;
		}
		//����
		Claims claims=TokenUtil.parseToken(token);
		//��ȡǩ��ʱ��
		Date issuedDate=claims.getIssuedAt();
		//�����ǩ��ʱ��,��������
		if(issuedDate==null) {
			response.setStatus(401);
			return false;
		}
		long issuedTime=issuedDate.getTime();
		long nowTime=new Date().getTime();
		long dur=nowTime-issuedTime;
		//���ǩ����ʱ,��������
		if(dur>=1000*1800) {
			response.setStatus(401);
			return false;
		}
		String teacherId=claims.getSubject();
		//�����ǩ������,��������
		if(teacherId==null) {
			response.setStatus(401);
			return false;
		}
		String storeToken=tokenDao.get(teacherId);
		//���û��ȡ��ָ��token,��������
		if(storeToken==null) {
			response.setStatus(401);
			return false;
		}
		//���token�뷢�ŵ�token��ƥ��,��������
		if(!storeToken.equals(token)) {
			response.setStatus(401);
			return false;
		}
		//ͨ������,����
		return true;
	}


}
