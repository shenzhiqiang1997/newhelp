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
		//����Ԥ����
		if("OPTIONS".equals(request.getMethod())) return true;
		//��ȡ��token
		String token=request.getHeader("Authorization");
		//�����ȡ����token,��������
		if(token==null) {
			token=request.getParameter("token");
		}
		if(token==null) {
			//����ѧ����Ϣ����ģ������ֱ�ӽ��з���
			if(request.getRequestURI().startsWith(RequestURL.BASE_STUDENT_IMPORT_TEMPLATE_URL)
					&&Constant.GET.equals(request.getMethod())) {
				return true;
			}else {
				response.setStatus(401);
				return false;
			}
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
		
		//��ʼ�û���֤Ȩ��
		Authorization authorization=authorizationDao.get(teacherId);
		//����˺Ŵ��� ���ݿ���û��Ȩ�� ������Ĭ��Ȩ��
		if(authorization==null) {
			authorizationDao.add(teacherId);
			authorization=authorizationDao.get(teacherId);
		}
		
		String url=request.getRequestURI();
		String method=request.getMethod();
		
		//����Ƿ��л���ѧ����Ϣ�鿴Ȩ��
		if(url.startsWith(RequestURL.BASE_STUDENT_SEE_ONE_URL)&&method.equals(Constant.GET)
				||(url.startsWith(RequestURL.BASE_STUDENT_SEE_LIST_URL)&&(method.equals(Constant.GET)||method.equals(Constant.POST)))) {
			if(authorization.getBaseStudentSee()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//����Ƿ��л���ѧ����Ϣ�޸�Ȩ��
		if(url.equals(RequestURL.BASE_STUDENT_EDIT_URL)&&method.equals(Constant.POST)) {
			if(authorization.getBaseStudentEdit()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//����Ƿ��л���ѧ����Ϣ����Ȩ��
		if(url.equals(RequestURL.BASE_STUDENT_IMPORT_URL)&&method.equals(Constant.POST)) {
			if(authorization.getBaseStudentImport()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//����Ƿ��л���ѧ����Ϣ����Ȩ��
		if(url.equals(RequestURL.BASE_STUDENT_EXPORT_URL)&&method.equals(Constant.GET)) {
			if(authorization.getBaseStudentExport()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//����Ƿ�������ѧ�������鿴Ȩ��
		if(url.startsWith(RequestURL.ARCHIVE_STUDENT_SEE_ONE_URL)&&method.equals(Constant.GET)
				||url.startsWith(RequestURL.ARCHIVE_STUDENT_SEE_LIST_URL)&&(method.equals(Constant.GET)||method.equals(Constant.POST))) {
			if(authorization.getArchiveStudentSee()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		if(url.equals(RequestURL.ARCHIVE_STUDENT_URL)) {
			//����Ƿ�������ѧ�������޸�Ȩ��
			if(method.equals(Constant.PUT)&&authorization.getArchiveStudentEdit()!=1){
				response.setStatus(401);
				return false;
			}			
			//����Ƿ�������ѧ������Ȩ��
			if(method.equals(Constant.POST)&&authorization.getArchiveStudentBuild()!=1){
				response.setStatus(401);
				return false;
			}			
			//����Ƿ�������ѧ������Ȩ��
			if(method.equals(Constant.DELETE)&&authorization.getArchiveStudentDestory()!=1){
				response.setStatus(401);
				return false;
			}
		}
		
		//����Ƿ�������ѧ���������Ȩ��
		if(url.startsWith(RequestURL.ARCHIVE_STUDENT_CHANGE_URL)&&method.equals(Constant.POST)) {
			if(authorization.getArchiveStudentChange()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//����Ƿ�������ѧ����������Ȩ��
		if(url.startsWith(RequestURL.ARCHIVE_STUDENT_EXPORT_URL)&&method.equals(Constant.GET)) {
			if(authorization.getArchiveStudentExport()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//����Ƿ�������ѧ����¼�鿴Ȩ��
		if((url.startsWith(RequestURL.ARCHIVE_RECORD_SEE_LIST_URL)||url.startsWith(RequestURL.ARCHIVE_RECORD_SEE_ONE_URL))
				&&method.equals(Constant.GET)) {
			if(authorization.getArchiveRecordSee()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		if(url.equals(RequestURL.ARCHIVE_RECORD_URL)) {
			//����Ƿ�������ѧ����¼�޸�Ȩ��
			if(method.equals(Constant.PUT)&&authorization.getArchiveRecordEdit()!=1) {
				response.setStatus(401);
				return false;
			}
			//����Ƿ�������ѧ����¼���Ȩ��
			if(method.equals(Constant.POST)&&authorization.getArchiveRecordAdd()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//����Ƿ�������ѧ����¼ɾ��Ȩ��
		if(url.equals(RequestURL.ARCHIVE_RECORD_DELETE_URL)&&method.equals(Constant.DELETE)) {
			if(authorization.getArchiveRecordDelete()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//����Ƿ�������ѧ����ʷ�����鿴Ȩ��
		if((url.startsWith(RequestURL.HISTORY_ARCHIVE_SEE_ONE_URL))&&method.equals(Constant.GET)
				||url.startsWith(RequestURL.HISTORY_ARCHIVE_SEE_LIST_URL)&&(method.equals(Constant.GET)||method.equals(Constant.POST))) {
			if(authorization.getHistoryArchiveSee()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//����Ƿ�������ѧ����ʷ��¼�鿴Ȩ��
		if((url.startsWith(RequestURL.HISTORY_RECORD_SEE_ONE_URL)||url.startsWith(RequestURL.HISTORY_RECORD_SEE_LIST_URL))
				&&method.equals(Constant.GET)) {
			if(authorization.getHistoryRecordSee()!=1) {
				response.setStatus(401);
				return false;
			}
		}
		
		//��teacherId����request�� �Ա��ڼ�¼��־ʱ���Ի�ȡ��
		request.setAttribute("teacherId", teacherId);
		
		//ͨ������,����
		return true;
	}


}
