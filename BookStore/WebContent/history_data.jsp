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
	<div class="help"><a href="/BookStore/ShowCarServlet" class="shopping">购物车</a><a href="/BookStore/register.html">注册</a><a href="/BookStore/LoginOutServlet">安全退出</a></div>
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
	<div id="history">
			<table>
				<c:choose>
				    <c:when test="${!empty historyList}">
				    <tr>
						<th>订单编号</th>
						<th>订购商品&nbsp;&nbsp;&nbsp;&nbsp;订购数量&nbsp;&nbsp;&nbsp;&nbsp;订购单价</th>
						<th>订购人&nbsp;&nbsp;&nbsp;&nbsp;联系电话&nbsp;&nbsp;&nbsp;&nbsp;收货地址</th>
						<th>商品总价</th>
						<th>订单成交时间</th>
					</tr>
				    	<c:forEach items="${historyList}" var="history">
			    		   <tr id="product_id_1">
								<td class="orderID">${history.order.id}</td>
								<td class="bookMsg">
									<dl>
										<dd>${history.orderItem.bookName}&nbsp;&nbsp;${history.orderItem.bookNum}本 &nbsp;&nbsp;${history.orderItem.bookPrice}元 </dd>
									</dl>
								</td>
								<td class="orderMsg">
									<dl>	
									  <dd>${history.order.orderName} &nbsp; ${history.order.telephone}&nbsp;&nbsp; ${history.order.address} </dd>	
									</dl>
								</td>
								<td class="price" id="price_id_1">
									<span>￥${history.orderItem.bookNum * history.orderItem.bookPrice}</span>
								</td>
								<td class="orderTime">
									<dl>
										<dt>${history.order.orderDate}</dt>
									</dl>
								</td>
							</tr>
						</c:forEach>
				    </c:when>
				    <c:otherwise>
				    	<tr><td colspan="4"><h2 align="center" style="color: #cc3300">当前无任何订单记录  , 请前往购物大厅进行选购！！！</h2></td></tr>
				    </c:otherwise>
				</c:choose>	
			</table>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2010  All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
