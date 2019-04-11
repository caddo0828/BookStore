<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<dl>
				<dt>图书音像</dt>
				<dd><a href="/BookStore/BookListServlet">图书</a></dd>
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
		<div class="spacer"></div>
	</div>
	<div class="main">
		<div class="product-list">
			<h2>全部商品 </h2>  
			<div class="pager">
				<ul class="clearfix">
					<li><c:if test="${pageNumber!=1}">
	       				 <a href="/BookStore/BookListServlet?pageNumber=${pageNumber-1}"><img alt="" border="0" src="static/images/previous_page.png"></a> </c:if>
	       			</li>
	       			<li class="current">
	       			   <form action="/BookStore/BookListServlet" method="post">
	       			    	<input type="text" name="pageNumber" style="width: 40px;" value="" onchange="changePage(this);"/> <input type="submit"  id="jump" value="跳转" />
	       			   </form>
	    			</li>
	    			<li><c:if test="${pageNumber!=pageCount}">
	        		     <a href="/BookStore/BookListServlet?pageNumber=${pageNumber+1}"><img alt="" border="0" src="static/images/next_page.png"></a> </c:if>&nbsp;&nbsp;&nbsp;&nbsp;
	        		</li>
	        		<li>
	        			<font  style="font-size: 14px;">当前为第<b style="color: red">${pageNumber}</b>页&nbsp;||&nbsp;共<b style="color: red">${pageCount}</b>页</font>
	        		</li>
	        		
				</ul>
			</div>
			<div class="clear"></div>
			<ul class="product clearfix">
			  <c:forEach items="${requestScope.bookList }" var="book">
			  	 <li>
					<dl>
						<dt><a href="/BookStore/BookViewServlet?id=${book.id}&pageNumber=${pageNumber}" target="_parent"><img src="${book.imgurl} " /></a></dt>
						<dd class="title"><a href="/BookStore/BookViewServlet?id=${book.id}&pageNumber=${pageNumber}" target="_parent">${book.name}</a></dd>
						<dd class="price">￥${book.price }</dd>
					</dl>
				</li>
			 </c:forEach>
			 
			</ul>
			<div class="clear"></div>
		  
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010  All Rights Reserved. 京ICP证1000001号
</div>	
		
</body>
</html>
