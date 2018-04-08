package com.uestc.newhelp.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.uestc.newhelp.constant.Constant;
import com.uestc.newhelp.constant.RequestURL;
import com.uestc.newhelp.dao.AuthorizationDao;
import com.uestc.newhelp.dao.TokenDao;
import com.uestc.newhelp.entity.Authorization;
import com.uestc.newhelp.util.TokenUtil;

import io.jsonwebtoken.Claims;

public class AuthorizationInterceptor implements HandlerInterceptor{
	@Autowired
	private TokenDao tokenDao;
	@Autowired
	private AuthorizationDao authorizationDao;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		//放行预请求
		if("OPTIONS".equals(request.getMethod())) return true;
		//获取到token
		String token=request.getHeader("Authorization");
		//如果获取不到token,拦截请求
		if(token==null) {
			token=request.getParameter("token");
		}
		if(token==null) {
			//基本学生信息导入模板下载直接进行放行
			if(request.getRequestURI().startsWith(RequestURL.BASE_STUDENT_IMPORT_TEMPLATE_URL)
					&&Constant.GET.equals(request.getMethod())) {
				return true;
			}else {
				response.setStatus(401);
				return false;
			}
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
		
		//开始用户验证权限
		Authorization authorization=authorizationDao.get(teacherId);
		//如果账号存在 数据库中没有权限 则授予默认权限
		if(authorization==null) {
			authorizationDao.add(teacherId);
			authorization=authorizationDao.get(teacherId);
		}
		
		String url=request.getRequestURI();
		String method=request.getMethod();
		
		//检查是否有基本学生信息查看权限
		if(url.startsWith(RequestURL.BASE_STUDENT_SEE_ONE_URL)&&method.equals(Constant.GET)
				||(url.startsWith(RequestURL.BASE_STUDENT_SEE_LIST_URL)&&(method.equals(Constant.GET)||method.equals(Constant.POST)))) {
			if(authorization.getBaseStudentSee()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//检查是否有基本学生信息修改权限
		if(url.equals(RequestURL.BASE_STUDENT_EDIT_URL)&&method.equals(Constant.POST)) {
			if(authorization.getBaseStudentEdit()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//检查是否有基本学生信息导入权限
		if(url.equals(RequestURL.BASE_STUDENT_IMPORT_URL)&&method.equals(Constant.POST)) {
			if(authorization.getBaseStudentImport()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//检查是否有基本学生信息导出权限
		if(url.equals(RequestURL.BASE_STUDENT_EXPORT_URL)&&method.equals(Constant.GET)) {
			if(authorization.getBaseStudentExport()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//检查是否有困难学生档案查看权限
		if(url.startsWith(RequestURL.ARCHIVE_STUDENT_SEE_ONE_URL)&&method.equals(Constant.GET)
				||url.startsWith(RequestURL.ARCHIVE_STUDENT_SEE_LIST_URL)&&(method.equals(Constant.GET)||method.equals(Constant.POST))) {
			if(authorization.getArchiveStudentSee()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		if(url.equals(RequestURL.ARCHIVE_STUDENT_URL)) {
			//检查是否有困难学生档案修改权限
			if(method.equals(Constant.PUT)&&authorization.getArchiveStudentEdit()!=1){
				response.setStatus(401);
				return false;
			}			
			//检查是否有困难学生建档权限
			if(method.equals(Constant.POST)&&authorization.getArchiveStudentBuild()!=1){
				response.setStatus(401);
				return false;
			}			
			//检查是否有困难学生除档权限
			if(method.equals(Constant.DELETE)&&authorization.getArchiveStudentDestory()!=1){
				response.setStatus(401);
				return false;
			}
		}
		
		//检查是否有困难学生档案变更权限
		if(url.startsWith(RequestURL.ARCHIVE_STUDENT_CHANGE_URL)&&method.equals(Constant.POST)) {
			if(authorization.getArchiveStudentChange()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//检查是否有困难学生档案导出权限
		if(url.startsWith(RequestURL.ARCHIVE_STUDENT_EXPORT_URL)&&method.equals(Constant.GET)) {
			if(authorization.getArchiveStudentExport()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//检查是否有困难学生记录查看权限
		if((url.startsWith(RequestURL.ARCHIVE_RECORD_SEE_LIST_URL)||url.startsWith(RequestURL.ARCHIVE_RECORD_SEE_ONE_URL))
				&&method.equals(Constant.GET)) {
			if(authorization.getArchiveRecordSee()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		if(url.equals(RequestURL.ARCHIVE_RECORD_URL)) {
			//检查是否有困难学生记录修改权限
			if(method.equals(Constant.PUT)&&authorization.getArchiveRecordEdit()!=1) {
				response.setStatus(401);
				return false;
			}
			//检查是否有困难学生记录添加权限
			if(method.equals(Constant.POST)&&authorization.getArchiveRecordAdd()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//检查是否有困难学生记录删除权限
		if(url.equals(RequestURL.ARCHIVE_RECORD_DELETE_URL)&&method.equals(Constant.DELETE)) {
			if(authorization.getArchiveRecordDelete()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//检查是否有困难学生历史档案查看权限
		if((url.startsWith(RequestURL.HISTORY_ARCHIVE_SEE_ONE_URL))&&method.equals(Constant.GET)
				||url.startsWith(RequestURL.HISTORY_ARCHIVE_SEE_LIST_URL)&&(method.equals(Constant.GET)||method.equals(Constant.POST))) {
			if(authorization.getHistoryArchiveSee()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//检查是否有困难学生历史记录查看权限
		if((url.startsWith(RequestURL.HISTORY_RECORD_SEE_ONE_URL)||url.startsWith(RequestURL.HISTORY_RECORD_SEE_LIST_URL))
				&&method.equals(Constant.GET)) {
			if(authorization.getHistoryRecordSee()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//将teacherId放入request中 以便在记录日志时可以获取到
		request.setAttribute("teacherId", teacherId);
		
		//通过以上,放行
		return true;
	}


}
