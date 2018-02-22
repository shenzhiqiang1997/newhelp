<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path=request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作日志列表</title>
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css"/>
<style type="text/css">
td  
{  
    text-align:center;  
}  
th  
{  
    text-align:center;  
}  
</style>
<script type="text/javascript" src="<%=path%>/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function deleteLog(logId){
		var deleteFlag=confirm("确定删除");
		if(deleteFlag){
			var deleteForm=$("#deleteForm");
			var action=deleteForm.attr("action");
			deleteForm.attr("action",action+logId);
			deleteForm.submit();
			return false;
		}
		
	}
</script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand">帮扶系统后台</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<li><a href="<%=path%>/backend/teachers">教师账号</a></li>
			<li><a href="<%=path%>/backend/helpTypes">帮扶类型</a></li>
			<li><a href="<%=path%>/backend/attentionTypes">关注类型</a></li>
			<li class="active"><a href="<%=path%>/backend/logs">操作日志</a></li>
		</ul>
		<form class="navbar-form navbar-right"  action="<%=path%>/backend/logout" method="post">
			<input type="hidden" name="_method" value="delete"/>
			<span>${user.name }</span>
            <button type="submit" class="btn btn-default">退出登录</button>
        </form>
	</div>
	</div>
</nav>
<br/>
<h1 class="text-center">操作日志列表</h1>
<br>
<form action="<%=path%>/backend/log/" method="POST" id="deleteForm">
	<input type="hidden" name="_method" value="DELETE"/>
	<table class="table table-bordered">
	  <thead>
	    <tr>
	      <th>操作日志id</th>
	      <th>操作人</th>
	      <th>操作ip</th>
	      <th>操作内容</th>
	      <th>操作时间</th>
	      <th>操作结果</th>
	      <th>操作信息</th>
	      <th>删除</th>
	    </tr>
	  </thead>
	  <tbody>
		<c:forEach items="${logs}" var="log">
    		<tr>
    		  <td>${log.logId}</td>
    		  <td>${log.teacherId}</td>
    		  <td>${log.ip}</td>
		      <td>${log.content}</td>
		      <td><fmt:formatDate value="${log.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		      <td>${log.result eq 1?"成功":"失败"}</td>
		      <td>${log.message}</td>
		      <td><button class="btn btn-default" type="button" onclick="deleteLog(${log.logId})">删除</button></td>
	    	</tr>
    	</c:forEach>
	  </tbody>
	</table>
</form>
</body>
</html>