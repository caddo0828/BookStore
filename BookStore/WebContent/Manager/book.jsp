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
		<h2>商品管理&nbsp;&nbsp;&nbsp;  <b style="color: red;">${error}</b></h2>
		<div class="manage">
			<div class="search">
				<form method="post" action="/BookStore/ManaFindBookServlet.do?type=search">
					图书编号：<input type="text" class="text" style="width:250px;" name="bookId" /> 图书名称：<input type="text" class="text" name="bookName" /> <label class="ui-blue"><input type="submit" name="submit" value="查询" /></label>
				</form>
			</div>
			<table class="list">
				<tr>
					<th>ID</th>
					<th>商品名称</th>
					<th>操作</th>
				</tr>
				<c:if test="${!empty bookList}">
				<c:forEach items="${bookList}" var="book">
				  <tr>
					<td class="first">${book.id}</td>  
					<td class="thumb"><img src="/BookStore/${book.imgurl}" style="width: 54px;height: 54px;" /><a href="/BookStore/BookViewServlet?id=${book.id}" target="_blank">${book.name}</a></td>
					<td class="w1 c">
							<a href="/BookStore/Manager/book-modify.jsp?bid=${book.id}&bname=${book.name}&imgurl=${book.imgurl}">修改</a> 
							<a href="/BookStore/ManaBookDelServlet.do?bid=${book.id}">删除</a>
					</td>
				  </tr>
				</c:forEach>
				</c:if>
			</table>
			<div class="pager">
				<ul class="clearfix">
					<li><b style="color: red; font-size: 14px;">共&nbsp;&nbsp;&nbsp;${pageCount}页</b></li>
					<li><a href="/BookStore/ManaFindBookServlet.do?pageNumber=${pageNumber-1}">上一页</a></li>
					<li class="current"><a href="/BookStore/ManaFindBookServlet.do?pageNumber=1">1</a></li>
					<li><a href="/BookStore/ManaFindBookServlet.do?pageNumber=2">2</a></li>
					<li><a href="/BookStore/ManaFindBookServlet.do?pageNumber=3">3</a></li>
					<li><a href="/BookStore/ManaFindBookServlet.do?pageNumber=4">4</a></li>
					<li><a href="/BookStore/ManaFindBookServlet.do?pageNumber=5">5</a></li>
					<li> <a href="/BookStore/ManaFindBookServlet.do?pageNumber=${pageNumber+1}">下一页</a> </li>
				</ul>
			</div>
		</div>
	</div>
</div>
</body>
</html>
