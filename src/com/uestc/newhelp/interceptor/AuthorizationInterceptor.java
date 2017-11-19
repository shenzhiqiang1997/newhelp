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
		//放行预请求
		if("OPTIONS".equals(request.getMethod())) return true;
		//获取到token
		String token=request.getHeader("Authorization");
		//如果获取不到token,拦截请求
		if(token==null) {
			response.setStatus(401);
			return false;
		}
		//解密
		Claims claims=TokenUtil.parseToken(token);
		//获取签发时间
		Date issuedDate=claims.getIssuedAt();
		//如果无签发时间,拦截请求
		if(issuedDate==null) {
			response.setStatus(401);
			return false;
		}
		long issuedTime=issuedDate.getTime();
		long nowTime=new Date().getTime();
		long dur=nowTime-issuedTime;
		//如果签发超时,拦截请求
		if(dur>=1000*1800) {
			response.setStatus(401);
			return false;
		}
		String teacherId=claims.getSubject();
		//如果无签发对象,拦截请求
		if(teacherId==null) {
			response.setStatus(401);
			return false;
		}
		String storeToken=tokenDao.get(teacherId);
		//如果没获取到指定token,拦截请求
		if(storeToken==null) {
			response.setStatus(401);
			return false;
		}
		//如果token与发放的token不匹配,拦截请求
		if(!storeToken.equals(token)) {
			response.setStatus(401);
			return false;
		}
		//通过以上,放行
		return true;
	}


}
