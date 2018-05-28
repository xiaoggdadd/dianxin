<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'print.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css" media=print>
.noprint {
	display: none
}
</style>
  </head>
  
  <body>
  
  	<h2 align="center"><%out.print(request.getParameter("title")); %></h2>
  	<%out.print(request.getParameter("table")); %><br>
  	
  	<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id="printWB" width=0></OBJECT>
	<div style="text-align: center" class="noprint">
		<input type="button" value="直接打印" onClick="printWB.ExecWB(6,6)">
		<input type="button" value="打印预览" onClick="printWB.ExecWB(7, 1)">
		<input type="button" value="打印设置" onClick="printWB.ExecWB(8, 1)">
		<input type="button" value="返回" onClick="history.back()">
	</div>
  </body>
</html>
