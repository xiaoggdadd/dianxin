<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%
String path = request.getContextPath();
Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
String loginName = account.getName();
pageContext.setAttribute("loginName", loginName);
pageContext.setAttribute("loginId", loginId);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>密码修改</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"></script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/console.js"></script>
	<![endif]-->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="assets/bootstrap-v3/css/bootstrap.css">
	
	<script type="text/javascript" src="<%=path%>/javascript/wait.js"> </script>
	<script src="<%=path%>/javascript/placeholder.js"> </script> 
	<!-- 整理本页面后的js文件javascript/index-jsp.js   下行引用一定要在jquery-1.4.2.js引入之后-->
	<script type="text/javascript" src="<%=path%>/javascript/index-jsp.js"> </script>
  </head>
  <body>
  	<form class="form-signin" action="" name="form1" method="post">
  	<br>
     <h1 class="h3 mb-3 font-weight-normal"  align="center" >用户:${loginName}</h1>
      <label for="inputEmail" class="sr-only"  align="center" >初始密码:123456</label><input type="hidden" value="${loginId }" id = "userid" name="userid" >
      <input type="password" name="pass" id="pass" class="form-control" data-toggle="password" placeholder="密码大于6个字符,字母开头" required >
      <input type="password" name="inputPwd" id="inputPwd" class="form-control" placeholder="再次输入确认密码" required>
		<br>     
		<p style="color: #999999">密码提示：密码大于6个字符,字母开头</p>     
		<br>     
      <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="tijiao()" >更改密码去登陆</button>
      <p class="mt-5 mb-3 text-muted " align="center" ></p>
    </form>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <script type="text/javascript">
  var path = '<%=path%>';
  	function tijiao(){
  		var pass = $("#pass").val();
  		var inputPwd = $("#inputPwd").val();
  		var regex = /^[\w_-]{6,30}$/;
		var patrn=/^[0-9]{1,20}$/;
  		if(pass == null || pass == ""){
  			alert("请输入密码!");
  			return;
  		}
  		if(inputPwd == null || inputPwd == ""){
  			alert("请确认密码!");
  			return;
  		}
  		if(pass!=inputPwd){
  			alert("两次密码不一致,请重新输入!");
  			return;
  		}
  		if(!regex.test(pass)){
  			alert("密码中必须包含字母、数字，至少6个字符，最多30个字符。");
  			return;
  		}
		if (patrn.exec(pass)) {
			alert("密码中必须包含字母、数字，至少6个字符，最多30个字符。");
		return ;
		}	

  		login();
  	}
  	function login(){
  		if (confirm("确认要修改的密码？")) {
  				document.form1.action=path+"/bz_solo_servlet?action=uppass";
  	      		document.form1.submit();	
  			} else {
  				
  				return;
  			}
  	}
  </script>
  </body>
</html>
