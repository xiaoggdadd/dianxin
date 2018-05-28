<%@ page contentType="text/html; charset=UTF-8" %>
<%
	    String yflx= request.getParameter("yflx");
        String dlhj = request.getParameter("dlhj");
        String fyhj = request.getParameter("fyhj");
%>
<html>
<head>
<title>
zc
</title>

</head>
<jsp:useBean id="huanbi" scope="page" class="com.noki.mobi.cx.chart.YongFangLX">
</jsp:useBean>
<body bgcolor="#ffffff">
 <%
			huanbi.setDlhj(dlhj);
			huanbi.setFyhj(fyhj);
			huanbi.setYflx(yflx);
			String srg = huanbi.getBarChartViewer_zc(request,response);
		  %>

			<img src="<%=srg%>" border=0 >

                     
</body>
</html>
