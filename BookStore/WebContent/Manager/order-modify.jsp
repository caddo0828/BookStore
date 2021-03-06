<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="static/css/mana.css" />
<script type="text/javascript" src="static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="static/js/comm.js"></script>
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
		<h2>修改订单</h2>
		<div class="manage">
			<form action="/BookStore/ManagerOrderServlet.do?method=update&pageNumber=${param.pageNumber}" method="post" onsubmit="return checkOrderForm(this);">
				<table class="form">
					<tr>
						<td class="field">订单号：</td>
						<td><input type="text" class="text" name="orderId" value="${param.orderId}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="field">订购人姓名：</td>
						<td><input type="text" class="text" name="orderName" /></td>
					</tr>
					<tr>
						<td class="field">订单总价：</td>
						<td><input type="text" class="text" name="totalPrice" onfocus="focusItem(this);"  onblur="checkOrder(this);" /></td>
					</tr>
					<tr>
						<td class="field">发货地址:</td>
						<td><input type="text" class="text" name="address"  /></td>
					</tr>
					<tr>
						<td class="field">收货号码：</td>
						<td><input type="text" class="text" name="telephone" onfocus="focusItem(this);"  onblur="checkOrder(this);" /></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label><span></span></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>
