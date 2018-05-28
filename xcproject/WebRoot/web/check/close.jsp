<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>close.jsp</title>
  </head>
  
  <body onload="window.close()">
    <input onclick="window.opener=null;window.close()" type="button" value=" 关闭 ">
  </body>
</html>
