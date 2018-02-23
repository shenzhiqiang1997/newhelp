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
<link rel="stylesheet" href="<%=path%>/css/bootstrap-select.min.css"/>
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
<script type="text/javascript" src="<%=path%>/js/jqpaginator.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-select.min.js"></script>
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
			<li class="active"><a href="<%=path%>/backend/logs/20/1">操作日志</a></li>
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
<div align="center">
    <form class="bs-example bs-example-form" role="form" action="<%=path%>/backend/logs" method="POST" id="searchForm">
       	<input type="hidden" name="pageNum" value="${currentPage}" id="searchPageNum">
		<input type="hidden" name="pageSize" value="20">
        <div class="form-inline">
   			<input type="text" class="form-control" placeholder="操作日志id" value="${log.logId }" name="logId">
            <input type="text" class="form-control" placeholder="操作人" value="${log.teacherId }" name="teacherId">
            <input type="text" class="form-control" placeholder="操作ip" value="${log.ip }" name="ip">
            <input type="text" class="form-control" placeholder="操作内容" value="${log.content }" name="content">
        	<select class="selectpicker" name="result">
        		<c:choose>
        			<c:when test="${ log.result eq 1}">
        				<option value="1" selected="selected">成功</option>
						<option value="0">失败</option>   
        			</c:when>
        			<c:when test="${ log.result eq 0}">
        				<option value="1">成功</option>
						<option value="0" selected="selected">失败</option>   
        			</c:when>
        			<c:otherwise>
        				<option value="1">成功</option>
						<option value="0">失败</option>
        			</c:otherwise>
        		</c:choose>
        	</select>
        	<button class="btn btn-default" type="submit">搜索</button>
        </div>
        <br>
    </form>
</div>
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
	<div align="center">
		<ul class="pagination" id="pagination"></ul>
	</div>
</form>
<script type="text/javascript">
$('#pagination').jqPaginator({
    totalPages: ${totalPages},
    visiblePages: 20,
    currentPage: ${currentPage},
    onPageChange: function (num, type) {
        if (type=='change'){
        	if (!${isSearch}) {
        		window.location.href="<%=path%>/backend/logs/20/"+num;
			}else{
				$("#searchPageNum").val(num);
				$("#searchForm").submit();
			}
        }
        	
    }
});
</script>
</body>
</html>