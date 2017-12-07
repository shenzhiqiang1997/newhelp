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
		//��request��responseת��ΪHttpRequest��HttpResponse
		HttpServletRequest httpServletRequest=(HttpServletRequest)req;
		HttpServletResponse httpServletResponse=(HttpServletResponse)resp;
		//����������¼�����
		if(loginRequestMapping.equals(httpServletRequest.getRequestURI())) {
			filterChain.doFilter(req, resp);
			return;
		}
		//��request�л�ȡsession
		HttpSession session=httpServletRequest.getSession();
		//��session�л�ȡ����ŵ��û���Ϣ
		Teacher teacher=(Teacher) session.getAttribute("user");
		//�ж��Ƿ���Ȩ��
		if(teacher!=null&&Constant.ADMIN_GRADE.equals(teacher.getGrade())) {
			//�����Ȩ�������
			filterChain.doFilter(req, resp);
			return;
		}else {
			//�����˻ص���¼����
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
