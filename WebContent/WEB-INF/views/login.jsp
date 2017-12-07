<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path=request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/buttons.css">
<style>
  body{
    margin: 0;
    padding: 0;
    height:100%;
    font:12px/1.5 Tahoma,Arial;
    background: white ;
  }
  h1{
    text-align: center;
  }
  input[type=checkbox]{
    -ms-transform: scale(1.5); /* IE */
    -moz-transform: scale(1.5); /* FireFox */
    -webkit-transform: scale(1.5); /* Safari and Chrome */
    -o-transform: scale(1.5); /* Opera */
  }
  .mainBox{
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  background-image: linear-gradient(60deg, #abecd6 0%, #fbed96 100%);

  /*  border: solid 1px rgba(192,192,192,1);*/
  }
  .img{
    float: left;
  }
  #changeId{
    margin-top: 45px;
    margin-left: 60px;
    width:272px;
    height: 40px;
    margin-bottom: 10px;

    padding-left: 5px;
  }
  #changeReasons{

    width: 272px;
    margin-left: 60px;
    height: 175px;
    border-radius: 5px;
    resize:none;
    overflow-y: scroll;
  }
  #confirm{
    float:right;
    margin-right: 70px;
    font-size: 15px;
    background:
  }
  #place{
    width: 400px;
    height: 380px;
    background:rgba(	169,169,169,0.8);
    border-top: 70px solid rgba(128,128,128,.8);
    -webkit-box-shadow:0 0 10px rgba(0, 204, 204, .5);
    -moz-box-shadow:0 0 10px rgba(0, 204, 204, .5);
    box-shadow:0 0 10px rgba(255,250,205,.9);
    margin: auto;
    margin-top: 100px;
    border-radius: 10px;
  }
  #place p{
    margin-top: -50px;
    font-size: 20px;
    font-weight: bold;
    color: white;
    margin-left: 10px;
  }
  #place div img{
    width: 40px;
    height: 40px;
    float: right;
    margin-top: -80px;
    margin-right: -8px;
  }

  .infoHolder{
    width: 380px;
    height:350px;
    border-top: solid 85px rgba(0,0,0,.6);
    margin:auto;
    background: rgba(192,192,192,0.6);
    border-radius: 18px;
    -webkit-box-shadow:0 0 10px rgba(0, 204, 204, .5);
    -moz-box-shadow:0 0 10px rgba(0, 204, 204, .5);
    box-shadow:0 0 10px rgba(255,250,205,.9);
  }
  #froatpage{
    width: 200px;
    height:200px;
    margin: auto;
    display: block;
    margin: auto;
  }
  .helpLogin{
    margin-top: -60px;
    font-size: 25px;
    font-weight: bold;
    color: white;
    margin-left: 10px;
    font-style:normal;
  }
  #userName{

  width: 270px;
  height: 45px;
  border: solid 1px rgba(192,192,192,1);
  border-radius: 5px;
  margin-left: 32px;
  margin-top: 50px;
  margin-bottom: 5px;
  padding-left: 40px;
  background: url(img/username1.png) center left 3.5px no-repeat;
  font-size: 18px;
  background-color: white;
  background-color: rgba(255,235,205,.5);
  border-bottom: 2px;
  }

  #cover{
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    background: rgba(	169,169,169,0.6);

  }

  .warningMsg{

    margin-left: 40px;
    color: red;
  }
  #savePass{
    -ms-transform: scale(1.5); /* IE */
    -moz-transform: scale(1.5); /* FireFox */
    -webkit-transform: scale(1.5); /* Safari and Chrome */
    -o-transform: scale(1.5); /* Opera */
  }
  #passWords{
    width: 270px;
    height: 45px;
    margin-bottom: 5px;
    border: solid 1px rgba(192,192,192,1);
    margin-left: 32px;
    margin-top: 15px;
    border-radius: 5px;
    padding-left: 40px;
    background: url(img/passwords1.png) center left  no-repeat;
    font-size: 18px;
    background-color: rgba(255,235,205,.5);
    border-bottom: 2px;
  }
  .icons{
    width: 1px;
    height: 1px;
  }
  #login{
    font-size: 16px;
    font-weight: bold;
    width: 310px;
    height:60px;
    margin-left: 32px;
    margin-top: 10px;
    border-radius: 4px;
  }

</style>
<title>学生帮扶系统后台登录</title>
</head>
<body>
<div class="mainBox">

  <img src="<%=path %>/img/ourlogo2.png"  id="froatpage"/>

<div class="infoHolder">
	<p class="helpLogin">帮扶系统后台登录</p>
	<form action="<%=path%>/backend/login" method="post">
	    <input id="userName" name="teacherId" placeholder="用户名" value="${teacher.teacherId }"/>
	    <span id="enterInfo" class="warningMsg"></span><br/>
	    <input id="passWords" name="password" type="password" placeholder="密码" value="${teacher.password }"/>
	    <span id="enterInfo2" class="warningMsg"></span><br/>
	    <button  id="login" class="button button-primary button-small" type="submit">登录</button>
    </form>
</div>
</div>
<c:if test="${result.success eq false }">
	<script type="text/javascript">
		alert("${result.message}");
	</script>
</c:if>
</body>
</html>