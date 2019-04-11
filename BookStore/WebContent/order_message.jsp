<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link rel="stylesheet" type="text/css" href="static/css/style.css"></link>
<script type="text/javascript" src="static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="static/js/function.js"></script>
<title>易买网 - 首页</title>
</head>
	<body>	
	<div id="header" class="wrap">
	<div id="logo"><img src="static/images/logo.gif" /></div>
	<div class="help"><a href="/BookStore/ShowCarServlet" class="shopping">购物车</a><a href="/BookStore/login.jsp">登录</a><a href="/BookStore/register.html">注册</a><a href="#">帮助中心</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="/BookStore/ProductServlet">首页</a></li>
			<li><a href="/BookStore/BookListServlet">图书</a></li>
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
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>网站首页</dt>
				<dd><a href="/BookStore/product_hall.jsp">回到首页</a></dd>
				<dt>购物车信息</dt>
				<dd><a href="/BookStore/ShowCarServlet">返回购物车</a></dd>
				<dt>订单管理</dt>
				<dd><a href="#">取消订单</a></dd>
			</dl>
		</div>
	</div>
	
	<div class="main">
		<h2>填写订购信息</h2>
		<div class="message">
			<form action="/BookStore/OrderItemServlet" method="post" >
				<table class="form">
					<tr>
						<td class="field">收货人姓名</td>
						<td><input type="text" class="text" name="userName" value="" /></td>
					</tr>
					<tr>
						<td class="field">联系电话</td>
						<td><input type="text" class="text" name="telephone" value="" onfocus="focusPhone(this)" onblur="checkPhone(this);"/><span></span></td>
					</tr>
					<tr>
						<td class="field">送货地址：</td>
						<td><input type="text" class="text" name="address" value=""/><span></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" onclick="return checkMsg();"/></label></td>
					</tr>
				</table>
				<span id="errorMsg"></span>
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
