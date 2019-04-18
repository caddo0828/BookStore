<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>易买网- 首页</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" />
<script type="text/javascript" src="static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="static/js/login.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="static/images/logo.gif" /></div>
	<div class="help"><a href="/BookStore/register.html">注册</a><a href="#">帮助中心</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="#">首页</a></li>
			<li><a href="#">图书</a></li>
			<li><a href="#">百货</a></li>
			<li><a href="#">品牌</a></li>
			<li><a href="#">促销</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<li class="first"><a href="#">音乐</a></li>
			<li><a href="#">影视</a></li>
			<li><a href="#">少儿</a></li>
			<li><a href="#">动漫</a></li>
			<li><a href="#">小说</a></li>
			<li><a href="#">外语</a></li>
			<li><a href="#">科幻</a></li>
			<li><a href="#">情感</a></li>
			<li><a href="#">教育</a></li>
			<li><a href="#">传记</a></li>
			<li><a href="#">古典哲学</a></li>
			<li><a href="#">名著经典</a></li>
			<li><a href="#">杂志</a></li>
			<li><a href="#">电子书籍</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h2>欢迎用户登录</h2>
			<form id="loginForm" method="post" action="${pageContext.request.contextPath}/LoginServlet" onsubmit="return checkLogin()">
				<table>
					<tr>
						<td class="field">* 用户名：</td>
						<td><input class="text" type="text" id="unameID" name="uname" value="${loginUser.name}" onfocus="focusItem(this)" onblur="checkByItem(this);"/><span></span></td>
					</tr>
					<tr>
						<td class="field">* 登录密码：</td>
						<td><input class="text" type="password" id="passWordID" name="pwd" value="${loginUser.pwd}" onfocus="focusItem(this)" onblur="checkByItem(this);"/><span></span></td>
					</tr>
					<tr> 
            			<td class="field">自动登录：</td> 
             			<td class="text">
	                                                                        一天<input type="radio" name="keepTime" value="${60*60*24 }"/>&nbsp;
	                                                                        一星期<input type="radio" name="keepTime" value="${60*60*24*7 }"/> &nbsp;
	                                                                         一个月<input type="radio" name="keepTime" value="${60*60*24*31 }"/> </td> 
       			    </tr>
					<tr>
						<td class="field">验证码：</td>
						<td><input class="text verycode" type="text" name="veryCode" id="randomNumberID" onfocus="focusItem(this)" onblur="checkByItem(this);" />
							<img id="veryCode" src="/BookStore/ImageServlet" />
							<a href="/BookStore/login.jsp">看不清，换一张</a>
							<span></span>
						</td>
					</tr>
					<tr>
						<td><label class="ui-left"><input type="submit" name="submit"/></label></td>
						<td><label class="ui-right"><input type="button" name="register" onclick="window.location='/BookStore/register.html';"/></label></td>
					</tr>
					
				</table>
				 <b style="color: red;text-align: center;" id="errID">${err }</b>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>



<div id="footer">
	Copyright &copy; 2010  All Rights Reserved. 京ICP证1000001号
</div>

  
</body>
</html>