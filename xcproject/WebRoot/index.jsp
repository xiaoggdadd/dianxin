<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ptac.app.version.Version"%>
<%
	String path = request.getContextPath();
	String version = new Version().getVersion();
	
	System.out.println("path:"+path);
%>
<html>
<head> 
	<!--  <link rel="stylesheet" type="text/css" href="<%=path%>/css/indexjsp.css" />-->
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" /> 
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
	
	<script type="text/javascript" src="<%=path%>/javascript/wait.js"> </script>
	<script src="<%=path%>/javascript/placeholder.js"> </script> 
	<!-- 整理本页面后的js文件javascript/index-jsp.js   下行引用一定要在jquery-1.4.2.js引入之后-->
	<script type="text/javascript" src="<%=path%>/javascript/index-jsp.js"> </script>
    <title>中国电信山东分公司能耗管理平台系统</title>
<style>
td {font-family:微软雅黑; color:#000}
</style>
</head>
<script type="text/javascript">
  function getVerificationCode(){
      document.getElementById("vImage").src="verificationCode.jsp?_t=" + new Date().getTime();
  }
</script>
	<body>
	
		<form action="" name="form1"> </form>
		<table border="0" align="center" cellpadding="0" cellspacing="0" style="height:585px; width:1063px;  background:url(<%=path%>/pic/loginbg.jpg) no-repeat 0 0;font-size:12px; border: #CCCCCC 1px solid; text-align:center;display:block">
<tr>
	<td width="530px">&nbsp;</td>
	<td height="585px"><br><br><br>
		<table border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td align="left" style="font-size:16px; "><br>&nbsp;<img src="<%=path%>/pic/lefticon_00.png" width="24" height="24"  style=" border:0; vertical-align:middle; " />&nbsp;系统登录<BR>&nbsp;</td>
			</tr>
			<tr align="center">
				<td align="right">用户名：</td>
				<td align="left"><input name="user" type="text"  id="user"  style="width:120px" /></td>
			</tr>
			<tr align="center" height="40px">
				<td align="right">密码：</td>
				<td align="left"><input name="pass" type="password"  id="pass"  style="width:120px"  /></td>
			</tr>
			<tr align="center">
				<td align="right"></td>
				<td align="left"><input name="rCode" type="text"  id="rCode" maxlength="4" onkeyup="this.value=this.value.replace(/\D/g,'')" style="width:60px"  />
				<img alt="" style="cursor: pointer;vertical-align: middle;margin-top: -3px;" id="vImage" src="verificationCode.jsp" id="verificationCode" onclick="getVerificationCode(this)">
				</td>
			</tr>
			<tr align="center" height="40px">
				<td align="right" colspan="2" style="font-size:13px; ">
				<input name="btnLogin" type="button"  class="btn_c1"  id="btnLogin"  style="height:30px; background:#033393;width:120px;color:#ffffff;border:0;font-size:10px;"  value="登 录" />
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
	</body>
</html>