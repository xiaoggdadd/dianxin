<%@ page contentType="text/html; charset=UTF-8" %>
<%
	    String jzmc= request.getParameter("jzmc");
        String dlhj = request.getParameter("dlhj");
        String fyhj = request.getParameter("fyhj");
        System.out.println("dlhj"+dlhj);
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
			huanbi.setJzmc(jzmc);
			String srg = huanbi.getBarChartViewer_jzdb(request,response);
			System.out.println(srg+"qqqqqqqqqqqqqqqqqq");
		  %>

			<img src="<%=srg%>" border=0 >

                     
</body>
</html>
