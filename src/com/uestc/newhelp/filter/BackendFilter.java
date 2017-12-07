package com.uestc.newhelp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uestc.newhelp.constant.Constant;
import com.uestc.newhelp.entity.Teacher;

public class BackendFilter implements Filter {
	private String loginUrl;
	private String loginRequestMapping;
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
			throws IOException, ServletException {
		//将request和response转化为HttpRequest和HttpResponse
		HttpServletRequest httpServletRequest=(HttpServletRequest)req;
		HttpServletResponse httpServletResponse=(HttpServletResponse)resp;
		//如果是请求登录则放行
		if(loginRequestMapping.equals(httpServletRequest.getRequestURI())) {
			filterChain.doFilter(req, resp);
			return;
		}
		//从request中获取session
		HttpSession session=httpServletRequest.getSession();
		//从session中获取到存放的用户信息
		Teacher teacher=(Teacher) session.getAttribute("user");
		//判断是否有权限
		if(teacher!=null&&Constant.ADMIN_GRADE.equals(teacher.getGrade())) {
			//如果有权限则放行
			filterChain.doFilter(req, resp);
			return;
		}else {
			//否则退回到登录界面
			httpServletResponse.sendRedirect(loginUrl);
			return;
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		loginUrl=filterConfig.getInitParameter("loginUrl");
		loginRequestMapping=filterConfig.getInitParameter("loginRequestMapping");
	}
	
}
