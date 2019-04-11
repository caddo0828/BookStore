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
<div class="wrap">
	<div id="shopping">
		<form action="/BookStore/BookCLServlet?type=update" method="post" id="form1">
			<table>
				<tr>
					<th><font style="float: left;">全选</font><input type="checkbox" id="allCheckBox" style="float: left; margin-top: 5px;" onclick="allCheck(this);" />商品图</th>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr> 
				<c:choose>
					<c:when test="${!empty bookList }">
						<c:forEach items="${bookList }" var="book">
							<tr id="product_id_1">
								<td class="thumb"><input type="checkbox" name="books" id="checkBox" class="checkBox" value="${book.id}=${book.shopNums}" /><img src="${book.imgurl}" /><a href="/Shop/BookViewServlet?id=${book.id }"></a></td>
								<td class="bookName" style="text-align: center;">${book.name}</td>
								<td class="price" id="price_id_1">
									<span>￥${book.price}</span>
									<input type="hidden" name="updateID" value="${book.id}"/>
									<input type="hidden" class="bookPrice" value="${book.price }">
								</td>
								<td class="number">
									<dl> 
										<dt>
											<input class="updateNums" type="text" name="updateNums" value="${book.shopNums}" onchange="updateNumber(this);"/>
											<input type="hidden" class="shoppingNums" name="shoppingNums" value="${book.shopNums}"/>
										</dt>
										<dd><input type="submit"  disabled="disabled" class="jump" value="修改" /></dd>
									</dl>
								</td>
								<td class="delete"><a href="/BookStore/BookCLServlet?type=delete&id=${book.id}&booknums=${book.shopNums}">删除</a></td>
							</tr>
					   </c:forEach>
				   </c:when>
				   <c:otherwise>
				   		<tr>
				   			<td colspan="4">
				   				<h2 align="center" style="color: #cc3300">购物车空空如也....请前往进行选购！！！</h2>
				   				<input type="hidden" id="noProuduct" value="" />
				   			</td>
				   		</tr>
				   </c:otherwise>
				</c:choose>
			</table>
			
			<div class="button">
				<b style="color: #cc3300;float:left ;margin-top:5px;font-size:14px;">${errorMsg}</b>
				<span></span>
				<a href="${pageContext.request.contextPath}/BookCLServlet?type=clear">清空购物车</a>
				<input type="button" onclick="return changeAction(this);">
			</div>
		</form>
	</div>
	
</div>
<div id="footer">
	Copyright &copy; 2010  All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>