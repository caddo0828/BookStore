<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="/BookStore/Manager/static/css/mana.css" />
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="static/images/logo.gif" /></div>
	<div class="help"><a href="/BookStore/ProductServlet">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="/BookStore/Manager/index.html">首页</a></li>
			<li><a href="/BookStore/ManafindUserServlet.do">用户</a></li>
			<li><a href="/BookStore/ManaFindBookServlet.do">图书</a></li>
			<li><a href="/BookStore/ManaFindOrderServlet.do">订单</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员您好，欢迎回到易买网管理后台。
	</div>
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><em><a href="/BookStore/Manager/user-add.jsp">新增</a></em><a href="/BookStore/ManafindUserServlet.do">用户管理</a></dd>
				<dt>图书信息</dt>
				<dd><em><a href="/BookStore/Manager/book-add.jsp">新增</a></em><a href="/BookStore/ManaFindBookServlet.do">图书管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="/BookStore/ManaFindOrderServlet.do">订单管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>用户管理    <b style="color: red;">${error}</b></h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>密码</th>
					<th>Email</th>
					<th>手机</th>
					<th>激活状态</th>
					<th>操作</th>
				</tr>
				<c:if test="${!empty userList}">
					<c:forEach items="${userList}" var="user">	
					<tr>
						<td class="first">${user.id}</td>
						<td class="w1 c">${user.name}</td>
						<td>${user.pwd}</td>
						<td>${user.email}</td>
						<td class="w4 c">${user.tel}</td>
						<td class="w1 c">${user.status}</td>
						<td class="w1 c">
							<a href="/BookStore/Manager/user-modify.jsp?id=${user.id}&userName=${user.name}&tel=${user.tel}&email=${user.email}">修改</a>
							<a href="/BookStore/ManaUserServlet.do?type=delete&uid=${user.id}">删除</a> </td>
					</tr>
				</c:forEach>
				</c:if>

			</table>
		</div>
	</div>
	<div class="clear"></div>
</div>
</body>
</html>
