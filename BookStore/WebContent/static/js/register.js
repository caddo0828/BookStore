window.onload = function(){
}


function FocusItem(obj)
{
	obj.parentNode.parentNode.className = "current";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	msgBox.innerHTML = "";
	msgBox.className = "";
}

function CheckItem(obj)
{
	obj.parentNode.parentNode.className = "";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
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
				//进行与服务器的交互,判断注册名是否已经存在
				$.get("UserAjaxServlet?method=ajaxRequest",{userName:obj.value},function(result) {
		 			//判断回调函数中的值
					eval("var res="+result);
					if(res.nameExit) {
		 				//用户名已经被占用
						msgBox.innerHTML = res.nameMsg;
						msgBox.className = "error";
						return false;
		 			}
		 		},"json");
			}
			break;
		case "pwd":
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
		case "rePwd":
			if(obj.value == "") {
				msgBox.innerHTML = "确认密码不能为空";
				msgBox.className = "error";
				return false;
			} else if(obj.value != $("#pwdID").val()) {
				msgBox.innerHTML = "两次输入的密码不相同";
				msgBox.className = "error";
				return false;
			}
			break;
		case "email":
			//先判断用户输入的邮箱格式正确，正确则进行服务器端交互校验
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
				//校验注册邮箱是否已经存在
 	 			$.get("UserAjaxServlet?method=ajaxRequest",{email:obj.value},function(result){
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
		case "tel":
			var reg_tel =/^1(3|4|5|7|8|9)\d{9}$/;
			if(obj.value == "") {
				msgBox.innerHTML = "联系电话不能为空";
				msgBox.className = "error";
				return false;
			}else if(!reg_tel.test(obj.value)) {
				msgBox.innerHTML = "请输入正确格式的手机号码！";
				msgBox.className = "error";
				return false;
			}else {
				//校验注册的联系电话是否已经存在
 	 			$.get("UserAjaxServlet?method=ajaxRequest",{tel:obj.value},function(result) {
 	 				eval("var res="+result);
 	 				if(res.telExit) {
 	 					msgBox.innerHTML = res.telMsg;
 	 					msgBox.className = "error";
 	 					return false;
 	 				}
 	 			},"json");
			}
			break;
	}
	
}

//注册按钮失效
function BoxChange(obj) {
	var flag = obj.checked;
 	if(flag) {
 		$("#reg").attr("disabled",false);
 	}else {
 		$("#reg").attr("disabled",true);
 	}
}

//注册表单验证
function checkForm(frm)
{
	var  unameTip = $("#unameTip").html();
	var  pwdTip = $("#pwdTip").html();
	var  rePwdTip = $("#rePwdTip").html();
	var  emailTip = $("#emailTip").html();
	var  telTip = $("#telTip").html();
	
	if(unameTip==""&&pwdTip==""&&rePwdTip==""&&emailTip==""&&telTip=="") {
		var els = frm.getElementsByTagName("input");
		for(var i=0; i<els.length; i++) {
			if(els[i].value=="") {
				alert("用户注册信息不能为空，请重新填写！");
				return false;
			}
		}
	}else {
		alert("注册信息输入有误，请确认出错信息，重新填写！");
		return false;
	}
	
}