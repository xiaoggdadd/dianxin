<%@ page import="com.noki.mobi.common.Account" %>
<%
	String path  = request.getContextPath();
	session.invalidate();
	response.sendRedirect(path+"/index.jsp");
%>