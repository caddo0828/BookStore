<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" />
<script type="text/javascript" src="static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="static/js/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="static/images/logo.gif" /></div>
	<div class="help"><a href="/BookStore/ShowCarServlet" class="shopping">购物车</a><a href="#">我的订单</a><a href="/BookStore/register.html">注册</a><a href="/BookStore/LoginOutServlet">安全退出</a></div>
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
			<li><a href="#">数码相机</a></li>
			<li><a href="#">笔记本</a></li>
			<li><a href="#">羽绒服</a></li>
			<li><a href="#">秋冬靴</a></li>
			<li><a href="#">运动鞋</a></li>
			<li><a href="#">美容护肤</a></li>
			<li><a href="#">家纺用品</a></li>
			<li><a href="#">婴幼奶粉</a></li>
			<li><a href="#">饰品</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div class="wrap">
	<div id="shopping">
		<form action="/BookStore/order_message.jsp" onsubmit="return checkData();">
			<table>
				<tr>
					<th>商品封面图</th>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
				</tr>
				<c:choose>
				    <c:when test="${!empty bookList}">
				    	<c:forEach items="${bookList}" var="book">
				    		<tr id="product_id_1">
								<td class="thumb"><img src="${book.imgurl}" /></td>
								<td class="bookName" style="text-align: center;">${book.name}</td>
								<td class="price" id="price_id_1">
									<span>￥${book.price}</span>
								</td>
								<td class="number">
									<dl>
										<dt>${book.shopNums}本</dt>
									</dl>
								</td>
								<td></td>
							</tr>
						</c:forEach>
				    </c:when>
				    <c:otherwise>
				    	<tr>
				    	  <td colspan="4"><h2 align="center" style="color: #cc3300">当前无任何订单  , 请前往购物大厅进行选购！！！</h2></td>
				    	</tr>
				    	<input type="hidden" name="data" id="data" value="null">
				    </c:otherwise>
				</c:choose>
				
			</table>
			<b style="color: #cc3300;float:left ;margin-top:5px;font-size:14px;">订单总价为:${totalPrice}￥</b>
			<div class="button"><input type="submit" id="dataSub"/></div>
		</form>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2010  All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
