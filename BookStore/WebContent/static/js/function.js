window.onload = function(){
}


function FocusItem(obj)
{
	obj.parentNode.parentNode.className = "current";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	msgBox.innerHTML = "";
	msgBox.className = "";
}


//分页
function changePage(obj) {
	//获取到用户进行页面跳转输入的页数
	var number = obj.value;
	var reg_number =/^[1-9]\d*$/;
	if(!reg_number.test(number)) {
		//让跳转页面的按钮失效，并且清空填入的查询页数
		obj.value= "";
		return false;
	}else {
		$("#jump").attr("disabled",false);
	}
	return true;
}

//购物车修改数量
function updateNumber(obj) {
	//获取购物数量
	var number = obj.value;
	var reg_number = /^[1-9]\d*$/;
	if(!reg_number.test(number)) {
		//将原购买数量赋值回去
		var updateNums = document.getElementsByClassName("updateNums");
		var shoppingNums = document.getElementsByClassName("shoppingNums");
		for(var i=0;i<updateNums.length;i++) {
			updateNums[i].value = shoppingNums[i].value;
		}
		return false;
	}else {
		$(".jump").attr("disabled",false);
	}

}

//全选及全选取消操作
function allCheck(obj) {
	var flag = obj.checked;
	var array = document.getElementsByClassName("checkBox");
	if(flag) {
		for(var i=0;i<array.length;i++) {
			array[i].checked = true;
		}
	}else {
		for(var i=0;i<array.length;i++) {
			array[i].checked = false;
		}
	}
}


//判断修改框是否为不可用，根据此信息修改action中的路径
function changeAction(obj) {
	//先判断购物车数据是否为空
	var hidden = document.getElementById("noProuduct");
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	
	if(hidden!=null) {
		msgBox.innerHTML = "当前购物车为空，请先进行商品加购！";
		msgBox.className = "error";
		return false; 
	}else {
		//获取修改按钮的属性
		var flag = $(".jump").attr("disabled");
		if(flag) {
			//表明完成了数据修改操作
			//获取所有多选框状态
			var ck = false;
			var array = document.getElementsByClassName("checkBox");
			for(var i=0;i<array.length;i++) {
				if(array[i].checked) {
					ck = true;
				}
			}
			
			if(ck) {
				 $("#form1").attr("action","OrderServlet?method=shoppingCar");
				 $("#form1")[0].submit();
				 return true;
			}else {
				msgBox.innerHTML = "您还没有选中商品，请先进行商品的选择！";
				msgBox.className = "error";
				return false;
			}
		}else {
			msgBox.innerHTML = "尚未完成数量修改，请先完成此操作!";
			msgBox.className = "error";
			return false; 
		}
		
	}
	
}


//对book_view.jsp中输入框中，书籍购买量的格式进行校验判断
function checkItemNums(obj) {
	var number = obj.value;
	var reg_number =/^[1-9]\d*$/;
	if(!reg_number.test(number)) {
		obj.value= "1";
		return false;
	}
	return true;
}


//book_view判断库存是否不足，否则无法进行数据添加
//注意踩坑点，参数一一对应，直接传参的字符串类型加  单引号, ; 对象， 库存，书籍id
function checkNums(obj,nums,bookID) {
	var msgBox = obj.parentNode.parentNode.getElementsByTagName("span")[1];
	//获取当前要进行购买的书籍数量
	var buyNums = document.getElementById("buyNums").value;
	
	if(nums>=1&&buyNums<=nums) {
		//允许购买,在判断是直接购买类型，还是加入购物车类型
		if(obj.value=="immiBuy") {
			location.href="OrderServlet?method=immeBuy&id="+bookID+"&buyNums="+buyNums; 
		}else if(obj.value=="shoppingCar") {
			location.href="BookServlet?method=add&id="+bookID+"&buyNums="+buyNums;
		}	
	}else {
		msgBox.innerHTML = "当前书籍库存量不足，无法购买";
		msgBox.className = "error";
		return false;
	}
}



//判断订单数据是否为空，为空，提交按钮则失效
function checkData() {
	var data = $("#data").val();
	if(data=="null") {
		$("#dataSub").attr("disabled",true);
		return false;
	}
	return true;
}



//判断收货信息是否符合规范
function focusPhone(obj) {
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	msgBox.innerHTML="";
	msgBox.className="";
}



//填写收货联系电话时校验
function checkPhone(obj) {
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	var tel = obj.value;
	var reg_tel =/^[1][3,4,5,7,8,9]\d{9}$/;
	if(!reg_tel.test(tel)) {
		msgBox.innerHTML="请输入正确格式的手机号码！";
		msgBox.className="error";
		return false;
	}
	
}

//对总的订购消息验证，不允许为空
function checkMsg() {
	var msgBox = document.getElementById("errorMsg");
	var array =  document.getElementsByClassName("text");
	var phoneTip = $("#phoneTip").html();
	
	if(phoneTip=="") {
		for(var i=0;i<array.length;i++) {
			if(array[i].value=="") {
				msgBox.innerHTML="订购消息不能为空，请重新填写！";
				msgBox.className="error";
				return false;
			}
		}
	}else {
		msgBox.innerHTML="订购消息出错，请确认出错信息重新填写！";
		msgBox.className="error";
		return false;
	}

}








