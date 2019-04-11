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
<link rel="stylesheet" type="text/css" href="static/css/style.css"></link>
<script type="text/javascript" src="static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="static/js/function.js"></script>
<title>易买网 - 首页</title>
</head>
<body>	
	<div id="header" class="wrap">
	<div id="logo"><img src="static/images/logo.gif" /></div>
	<div class="help"><a href="/BookStore/ShowCarServlet" class="shopping">购物车</a><a href="/BookStore/HistoryServlet">订单历史记录</a><a href="/BookStore/register.html">注册</a><a href="/BookStore/LoginOutServlet">安全退出</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="/BookStore/ProductServlet">首页</a></li>
			<li><a href="/BookStore/BookListServlet?pageNumber=${pageNumber}">图书</a></li>
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
	<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<dl>
				<dt>图书音像</dt>
				<dd><a href="/BookStore/BookListServlet?pageNumber=${pageNumber}">图书</a></dd>
				<dt>百货</dt>
				<dd><a href="#">运动健康</a></dd>
				<dd><a href="#">服装</a></dd>
				<dd><a href="#">家居</a></dd>
				<dd><a href="#">美妆</a></dd>
				<dd><a href="#">母婴</a></dd>
				<dd><a href="#">食品</a></dd>
				<dd><a href="#">手机数码</a></dd>
				<dd><a href="#">家具首饰</a></dd>
				<dd><a href="#">手表饰品</a></dd>
				<dd><a href="#">鞋包</a></dd>
				<dd><a href="#">家电</a></dd>
				<dd><a href="#">电脑办公</a></dd>
				<dd><a href="#">玩具文具</a></dd>
				<dd><a href="#">汽车用品</a></dd>
			</dl>
		</div>
	</div>	
	<div id="product" class="main">
		<h1>${bookView.name }</h1>
		<div class="infos">
			<div class="thumb"><img src="${bookView.imgurl}" id="img" /></div>
			<div class="buy">
				<p>商城价：<span class="price">￥${bookView.price }</span></p>
				<p>作　者：${bookView.author }</p>
				<p>书籍类型：${bookView.category }</p>
				<p>库　存：<b style="color: #c00; font-size: 15px">${bookView.nums}本</b></p>
				<p>购买数量<input type="text" name="buyNums" id="buyNums" value="1" style="width:80px;height: 20px;"  onchange="checkItemNums(this);"/></p>
				<p class="msg"><span></span></p>
				<div class="button">
					<input type="button" name="button" id="buyButton" value="immiBuy" onclick="return checkNums(this,${bookView.nums},'${bookView.id}');"/>
					<input type="button" id="carButton" value="shoppingCar"  onclick="return checkNums(this,${bookView.nums},'${bookView.id}');" />
			  </div>
			</div>                                            
			<div class="clear"></div>
		</div>
		<div class="introduce">
			<h2><strong>商品详情</strong></h2>
			<div class="text">
				<b style="color: red;">书籍描述：</b>&nbsp;&nbsp;${bookView.description }<br />
				<b style="color: red;">优惠信息：</b>凡是本周内购买此商品，可获5元优惠卷一张<br/>
				<b style="color: red;">温馨提示：</b>本店所售出的商品均为正品，经过检验认证，谨防盗图行为，必将追究法律责任
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010  All Rights Reserved. 京ICP证1000001号
</div>	
</body>
</html>