<%@ page contentType="text/html; charset=UTF-8" %>
<%
	    String dbid= request.getParameter("dbid");
        String dlhj = request.getParameter("dlhj");
        String fyhj = request.getParameter("fyhj");
        String time=request.getParameter("time");
        String dbid2= request.getParameter("dbid2");
        String dlhj2 = request.getParameter("dlhj2");
        String fyhj2 = request.getParameter("fyhj2");
        String time2=request.getParameter("time2");
        System.out.println("dlhj"+dlhj);
%>
<html>
<head>
<title>
zc
</title>

</head>
<jsp:useBean id="huanbi" scope="page" class="com.noki.mobi.cx.chart.ammeterContrastAnalysisChart">
</jsp:useBean>
<body bgcolor="#ffffff">
 <%
			huanbi.setDlhj(dlhj);
			huanbi.setFyhj(fyhj);
			huanbi.setDbid(dbid);
			huanbi.setTime(time);
			huanbi.setDlhj2(dlhj2);
			huanbi.setFyhj2(fyhj2);
			huanbi.setDbid2(dbid2);
			huanbi.setTime2(time2);
			//String srg = huanbi.getBarChartViewer_jzdb(request,response);
			String srg =huanbi.getChart (request,response);
			
			System.out.println(srg+"qqqqqqqqqqqqqqqqqq");
		  %>

			<img src="<%=srg%>" border=0 >
			

                     
</body>
</html>
