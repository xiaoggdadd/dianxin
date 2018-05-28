<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ptac.app.version.Version"%>
<%
	String path = request.getContextPath();
	String version = new Version().getVersion();
	
	System.out.println("path:"+path);
%>
<html>
<script language="JavaScript" type="text/javascript"> 
</script>
<head> 
	
</head>

	<body>
	<!-- <a href="<%=path%>/web/sdttWeb/index.jsp"> -->
	 <a href="javascript:self.close()" > 
	<img alt="点击关闭" title="点击关闭"  src="<%=path%>/web/images/wqx.jpg">
	
	</a>
	
                        
                
		
	</body>
</html>