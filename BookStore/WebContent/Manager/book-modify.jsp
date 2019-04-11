<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="/BookStore/Manager/static/css/mana.css" />
<script type="text/javascript" src="/BookStore/Manager/static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="/BookStore/Manager/static/js/comm.js"></script>
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
		<h2>修改商品 &nbsp;  <b style="color: red;">${error}</b> </h2>
		<div class="manage">
			<form action="/BookStore/ManaBookUpload.do?type=update" method="post" enctype="multipart/form-data" onsubmit="return checkBookForm(this);">
				<table class="form">
					<tr>
						<td class="field">商品ID：</td>
						<td><input type="text" class="text" name="bid" value="${param.bid}" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="field">商品名称：</td>
						<td><input type="text" class="text" name="bookName" value="${param.bname}" onfocus="focusItem(this);"  onchange="checkBook(this);"/></td>
					</tr>
					<tr>
						<td class="field">所属分类：</td>
						<td>
							<select name="category">
								<option value="计算机">计算机</option>
								<option value="文学">文学</option>
								<option value="原版">原版</option>
								<option value="科技">科技</option>
								<option value="小说">小说</option>
								<option value="励志">励志</option>
								<option value="生活">生活</option>
								<option value="少儿">少儿</option>
								<option value="生活百科">生活百科</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="field">图书价格：</td>
						<td><input type="text" class="text tiny" name="price" onfocus="focusItem(this);"  onblur="checkBook(this);"/> 元</td>
					</tr>
					<tr>
						<td class="field">图书库存：</td>
						<td><input type="text" class="text" name="nums" onfocus="focusItem(this);"  onblur="checkBook(this);" /></td>
					</tr>
					<tr>
						<td class="field">图书作者：</td>
						<td><input type="text" class="text" name="author" onfocus="focusItem(this);"  onblur="checkBook(this);"/></td>
					</tr>
					<tr>
						<td class="field">商品描述：</td>
						<td><textarea name="description"></textarea></td>
					</tr>
					<tr>
						<td class="field">商品图片：</td>
						<td><input type="file" class="text" name="imgurl" value="${param.imgurl}"/></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="修改" /></label><span></span></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>
	
	