<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<li><a href="/BookStore/ManagerUserServlet.do?method=showUser">用户</a></li>
			<li><a href="/BookStore/ManagerBookServlet.do?method=showBook">图书</a></li>
			<li><a href="/BookStore/ManagerOrderServlet.do?method=showOrder">订单</a></li>
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
				<dd><em><a href="/BookStore/Manager/user-add.jsp">新增</a></em><a href="/BookStore/ManagerUserServlet.do?method=showUser">用户管理</a></dd>
				<dt>图书信息</dt>
				<dd><em><a href="/BookStore/Manager/book-add.jsp">新增</a></em><a href="/BookStore/ManagerBookServlet.do?method=showBook">图书管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="/BookStore/ManagerOrderServlet.do?method=showOrder">订单管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>订单管理   &nbsp;&nbsp; <b style="color: red;">${error}</b></h2>
		<div class="manage">
			<div class="search">
				<form method="post" action="/BookStore/ManagerOrderServlet.do?method=search">
					订单号：<input type="text" class="text" style="width:250px;" name="orderId" /> 订购人：<input type="text" class="text" name="orderName" /> <label class="ui-blue"><input type="submit" name="submit" value="查询" /></label>
				</form>
			</div>
			<div class="spacer"></div>
			<table class="list">
				<tr>
					<th>订单号</th>
					<th>订购人</th>
					<th>订单总价</th>
					<th>发货地址</th>
					<th>收货号码</th>
					<th>操作</th>
				</tr>
				<c:if test="${!empty orderList}">
					<c:forEach items="${orderList}" var="order">
						<tr>
							<td class="first w4 c">${order.id}</td>
							<td class="w1 c">${order.orderName}</td>
						    <td class="w1 c">${order.totalPrice}元</td>
							<td>${order.address }</td>
							<td class="w1 c">${order.telephone}</td>
							<td class="w1 c"><a href="/BookStore/Manager/order-modify.jsp?orderId=${order.id}&pageNumber=${pageNumber}">修改</a> <a href="/BookStore/ManagerOrderServlet.do?method=delete&orderId=${order.id}&pageNumber=${pageNumber}">删除</a></td>
						</tr>
					</c:forEach>	
				</c:if>
			</table>
			<div class="pager">
				<ul class="clearfix">
					<li><b style="color: red; font-size: 14px;">共&nbsp;&nbsp;&nbsp;${pageCount}页</b></li>
					<li><a href="/BookStore/ManagerOrderServlet.do?method=showOrder&pageNumber=${pageNumber-1}" <c:if test="${pageNumber<=1}">  onclick="javascript:return false;" </c:if>  >上一页</a></li>
					<li class="current"><a href="/BookStore/ManagerOrderServlet.do?method=showOrder&pageNumber=1">1</a></li>
					<li><a href="/BookStore/ManagerOrderServlet.do?method=showOrder&pageNumber=2">2</a></li>
					<li><a href="/BookStore/ManagerOrderServlet.do?method=showOrder&pageNumber=3">3</a></li>
					<li><a href="/BookStore/ManagerOrderServlet.do?method=showOrder&pageNumber=4">4</a></li>
					<li><a href="/BookStore/ManagerOrderServlet.do?method=showOrder&pageNumber=5">5</a></li>
					<li> <a href="/BookStore/ManagerOrderServlet.do?method=showOrder&pageNumber=${pageNumber+1}"  <c:if test="${pageNumber>=pageCount }">  onclick="javascript:return false;" </c:if> >下一页</a> </li>
				</ul>
			</div>
		</div>
	</div>
</div>
</body>
</html>
