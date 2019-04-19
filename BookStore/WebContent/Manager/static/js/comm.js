window.onload=function() {
	
}


function focusItem(obj)
{
	var msgBox = obj.parentNode.parentNode.parentNode.getElementsByTagName("span")[0];
	msgBox.innerHTML = "";
	msgBox.className = "";
}

//实现用户的添加，以及修改
function checkForm(obj) {
	var array = obj.getElementsByClassName("text");
	var msgBox = obj.getElementsByTagName("span")[0];
	
	//代表的是有没有错误信息的输出
	var msg = $("span").html();
	if(msg=="") {
		for(var i=0;i<array.length;i++) {
			if(array[i].value == "") {
				msgBox.innerHTML="数据不允许为空，请重新填写!";
				msgBox.className="error";
				return false;
			}
		}
	}else {
		msgBox.innerHTML="操作失败,请确认出错信息并重新操作！";
		msgBox.className="error";
		return false;
	}

}

//后台管理校验对用户进行修改及添加的操作
function checkItem(obj) {
	var msgBox = obj.parentNode.parentNode.parentNode.getElementsByTagName("span")[0];
	switch(obj.name) {
		case "userName":
			var reg_uname=/^[a-zA-Z][a-zA-Z0-9]{3,15}$/;
			if(obj.value == "") {
				msgBox.innerHTML = "用户名不能为空";
				msgBox.className = "error";
				return false;
			}else if(!reg_uname.test(obj.value)) {
				msgBox.innerHTML = "用户名由英文字母和数字组成的4-16位字符，以字母开头";
				msgBox.className = "error";
				return false;
			}else {
				$.get("/BookStore/UserAjaxServlet?method=ajaxRequest",{userName:obj.value},function(result) {
					eval("var res="+result);
					if(res.nameExit) {
						msgBox.innerHTML = res.nameMsg;
						msgBox.className = "error";
						return false;
		 			}
		 		},"json");
			}
			break;
			
		case "passWord":
			var reg_pwd=/^[a-zA-Z0-9]{4,20}$/; 
			if(obj.value == "") {
				msgBox.innerHTML = "密码不能为空";
				msgBox.className = "error";
				return false;
			}else if(!reg_pwd.test(obj.value)) {
				msgBox.innerHTML = "密码不能含有非法字符,由英文字母和数字组成,长度在4-20之间";
				msgBox.className = "error";
				return false;
			}
			break;
			
		case "tel":
			var reg_tel =/^1(3|4|5|7|8|9)\d{9}$/;
			if(obj.value == "") {
				msgBox.innerHTML = "联系电话不能为空";
				msgBox.className = "error";
				return false;
			}else if(!reg_tel.test(obj.value)) {
				msgBox.innerHTML = "请输入11位数正确格式的手机号码！";
				msgBox.className = "error";
				return false;
			}else {
 	 			$.get("/BookStore/UserAjaxServlet?method=ajaxRequest",{tel:obj.value},function(result) {
 	 				eval("var res="+result);
 	 				if(res.telExit) {
 	 					msgBox.innerHTML = res.telMsg;
 	 					msgBox.className = "error";
 	 					return false;
 	 				}
 	 			},"json");
			}
			break;
			
		case "email":
			var reg_email = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
			if(obj.value == "") {
				msgBox.innerHTML = "注册邮箱不能为空";
				msgBox.className = "error";
				return false;
			}else if(!reg_email.test(obj.value)) {
				msgBox.innerHTML = "请输入正确的邮箱格式";
				msgBox.className = "error";
				return false;
			}else {
 	 			$.get("/BookStore/UserAjaxServlet?method=ajaxRequest",{email:obj.value},function(result){
 	 				eval("var res="+result);
 	 				if(res.emailExit) {
 	 					//用户邮箱已经存在
 	 					msgBox.innerHTML = res.emailMsg;
 	 					msgBox.className = "error";
 	 					return false;
 	 				}
 	 			}, "json");
			}
			
			break;
		case "status":
			var reg_status = /^(0|1)$/;
			if(obj.value == "") {
				msgBox.innerHTML = "用户激活状态不能为空";
				msgBox.className = "error";
				return false;
			}else if(!reg_status.test(obj.value)) {
				msgBox.innerHTML = "用户激活状态只能是1或者0";
				msgBox.className = "error";
				return false;
			}
			break;
		}
}


//对书籍进行添加修改
function checkBook(obj) {
	var msgBox = obj.parentNode.parentNode.parentNode.getElementsByTagName("span")[0];
	switch(obj.name) {
		case "bookName":
			if(obj.value=="") {
				msgBox.innerHTML = "图书名称不能为空!";
				msgBox.className = "error";
				return false;
			}
			break;
		case "price":
			var reg_price = /^(0|([1-9]\d{0,9}(\.\d{1,2})?))$/;
			if(obj.value=="") {
				msgBox.innerHTML = "商品价格不能为空!";
				msgBox.className = "error";
				return false;
			}else if(!reg_price.test(obj.value)) {
				msgBox.innerHTML = "请输入正确格式的商品价格";
				msgBox.className = "error";
				return false;
			}
			break;
		case "nums":
			var reg_nums = /^(0|([1-9]\d*))$/;
			if(obj.value=="") {
				msgBox.innerHTML = "商品库存不能为空!";
				msgBox.className = "error";
				return false;
			}else if(!reg_nums.test(obj.value)) {
				msgBox.innerHTML = "请输入正确格式的商品库存数量";
				msgBox.className = "error";
				return false;
			}
			break;
		case "author":
			if(obj.value=="") {
				msgBox.innerHTML = "书籍作者不能为空!";
				msgBox.className = "error";
				return false;
			}
			break;
		case "imgurl":
			if(obj.value=="") {
				msgBox.innerHTML = "请上传商品图片";
				msgBox.className = "error";
				return false;
			}
			break;
	}	
}

//对书籍表单的整体校验
function checkBookForm(obj) {
	var array = obj.getElementsByTagName("input");
	var msgBox = obj.getElementsByTagName("span")[0];
	//代表的是有没有错误信息的输出
	var msg = $("span").html();
	if(msg=="") {
		for(var i=0;i<array.length;i++) {
			if(array[i].value == "") {
				msgBox.innerHTML="商品编号,商品名,库存量,单价,书籍作者，封面图不能为空,请重新填写！";
				msgBox.className="error";
				return false;
			}
		}
	}else {
		msgBox.innerHTML="操作失败，请确认数据格式以及数据状态，重新操作！";
		msgBox.className="error";
		return false;
	}
}



//对修改订单数据的订单总价以及联系电话进行格式校验
function checkOrder(obj) {
	var msgBox = obj.parentNode.parentNode.parentNode.getElementsByTagName("span")[0];
	switch(obj.name) {
		case "totalPrice":
			var reg_price = /^(0|([1-9]\d{0,9}(\.\d{1,2})?))$/;
			if(obj.value=="") {
				msgBox.innerHTML = "订单总价不能为空!";
				msgBox.className = "error";
				return false;
			}else if(!reg_price.test(obj.value)) {
				msgBox.innerHTML = "请输入正确格式的订单总价格";
				msgBox.className = "error";
				return false;
			}
			break;
		case "telephone":
			var reg_tele =/^1(3|4|5|7|8|9)\d{9}$/;
			if(obj.value=="") {
				msgBox.innerHTML = "收货的联系方式不能为空，请填写!";
				msgBox.className = "error";
				return false;
			}else if(!reg_tele.test(obj.value)) {
				msgBox.innerHTML = "收货的联系方式格式为1开头，第二位为3或4或5或7或8或9,总11位!";
				msgBox.className = "error";
				return false;
			}
			break;
	}

}

//对订单修改信息表单的校验
function checkOrderForm(obj) {
	var array = obj.getElementsByTagName("input");
	var msgBox = obj.getElementsByTagName("span")[0];
	
	//代表的是有没有错误信息的输出
	var msg = $("span").html();
	if(msg=="") {
		for(var i=0;i<array.length;i++) {
			if(array[i].value == "") {
				msgBox.innerHTML="修改的订单数据不允许出现空，请重新填写";
				msgBox.className="error";
				return false;
			}
		}
	}else {
		msgBox.innerHTML="操作失败，请确认数据格式以及数据状态，重新操作！";
		msgBox.className="error";
		return false;
	}
	
}
