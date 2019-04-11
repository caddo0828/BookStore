window.onload = function(){
}


function focusItem(obj)
{
	obj.parentNode.parentNode.className = "current";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	msgBox.innerHTML = "";
	msgBox.className = "";
}

//登录数据不能为空
function checkByItem(obj) {
	obj.parentNode.parentNode.className = "";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	switch(obj.name) {
	case "uname":
		if(obj.value == "") {
			msgBox.innerHTML = "用户名不能为空";
			msgBox.className = "error";
			return false;
		}
		break;
	case "pwd":
		if(obj.value == "") {
			msgBox.innerHTML = "密码不能为空";
			msgBox.className = "error";
			return false;
		}
		break;
	case "veryCode":
		if(obj.value == "") {
			msgBox.innerHTML = "验证码不能为空";
			msgBox.className = "error";
			return false;
		}
		break;
	return true;
	}
}

//登录验证
function checkLogin() {
	var name = $("#unameID").val();
	var pwd = $("#passWordID").val();
	var code = $("#randomNumberID").val();
	if(name==""||pwd==""||code=="") {
		alert("用户注册信息不能为空");
		return false;
	}
	return true;
}
	

