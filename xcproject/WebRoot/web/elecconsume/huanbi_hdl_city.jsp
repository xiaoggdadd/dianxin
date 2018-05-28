<%@ page contentType="text/html; charset=UTF-8" %>
<%
	    String cityStr= "机房耗电量;基站耗电量;营业网点耗电量;其他耗电量";
        String dataStr = request.getParameter("dataStr");
        String path = request.getContextPath();
        System.out.println(dataStr);
%>
<html>
<head>
<title>
zc
</title>

</head>
<body bgcolor="#ffffff">
			<div id=plane1>
			<img src="<%=path%>/servlet/huanbi_sr?cityStr=<%=cityStr%>&dataStr=<%=dataStr%>" border=0 >
			</div>
                     
</body>
</html>
