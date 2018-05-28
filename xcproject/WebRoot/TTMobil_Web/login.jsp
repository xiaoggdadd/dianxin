<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String flag = request.getParameter("flag");
 %>
<meta charset="utf-8">
<title>山东电信基站抄表手机客户端</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="description" content="">
<meta name="author" content="">
<link href="./css/xk-style.css" rel="stylesheet">
<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="includes/ico/apple-touch-icon-114.png">
<link rel="apple-touch-icon-precomposed"
	href="includes/ico/apple-touch-icon-57.png">
<link rel="shortcut icon" href="./ico/favicon.png">
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<%String path = request.getContextPath(); %>
<script type="text/javascript">
	var path = '<%=path%>';
	var flag = '<%=flag%>';
	function fClear() {
		document.getElementById('txtUser').value = "";
		document.getElementById('txtPass').value = "";
	}
	
	$(document).ready(function (){
		if(flag=='true'){
			alert("保存成功！");
		}else if(flag=='false'){
			alert("保存失败！");
		}else{
			
		}
	});

	function doLogin() {
		var txtUser = $("#txtUser").val();
		var txtPass = $("#txtPass").val();
		if(txtUser==undefined||txtUser==null||txtUser==""||txtUser.length==0){
			alert("用户名不能为空！");
			return;
		}
		
		if (!/^1\d{10}$/.test(txtUser)) {
			alert("手机号码格式不正确！");
			return;
		}
		
		$.ajax({ // 请求登录处理页
			url : path+"/CBUserServlet", // 登录处理页
			dataType : "json",
			// 传送请求数据
			data : {
				'method' : 'doLogin',
				'txtUser' : txtUser,
				'txtPass' : txtPass,
			},
			success : function(jsonValue) { // 登录成功后返回的数据
				var hasUser = jsonValue.hasUser;
				var flag = jsonValue.flag;
				if (!hasUser) {
					alert("未注册用户名!");
				} else if (!flag) {
					alert("密码错误!");
				} else {
					goHome();
				}
			}
		});
	}

	function goHome() {
		var txtUser = $("#txtUser").val();
		this.location.href = "./caobiao/addCaobiao.jsp";
	}
</script>
</head>

<body>
	<div class="logo" style="text-align:center;">
		<span style="font-family:微软雅黑;font-size:24px">中国电信山东分公司基站抄表手机客户端</span> 
		<span style="font-family:微软雅黑;font-size:16px;color:#888">手机版</span>
	</div>
	<br>
	<br>
	<br>
	<section class="home clearfix">
		<div
			style="text-align:center;font-family:微软雅黑;font-size:16px;color:#888">
			<table align="center">
				<tr>
					<td>用户：</td>
					<td>
						<input id="txtUser" name="txtUser" type="text" style="width:80%" />
					</td>
				</tr>
				<tr>
					<td><br>密码：</td>
					<td><br>
						<input id="txtPass" name="txtPass" type="password" style="width:80%" />
					</td>
				</tr>
			</table>
			<br>
			<br>
			<button id="loginButton" class="buttonstyle" onclick="doLogin();">登录</button>
			&nbsp;&nbsp;
			<button id="refshButton" class="buttonstyle" onclick="fClear();">重置</button>
		</div>
	</section>
	<div style="display:none;">
	</div>
	<footer style="margin-top:20px;">
		<div class="foot-link">&nbsp;</div>
		<script type="text/javascript">
			function gotohead() {
				window.scrollTo(0, 1);
			}
		</script>
		<div class="media-link">
			<a href="#" class="pc">电脑版</a>
		</div>
		<div class="copy">中国电信山东分公司&nbsp;&nbsp;Copyright &copy;
			2016-2018</div>
	</footer>
	<script>
		function closes(obj) {
			var a = document.getElementById(obj);
			a.style.display = 'none';
		}
	</script>
</body>
</html>
