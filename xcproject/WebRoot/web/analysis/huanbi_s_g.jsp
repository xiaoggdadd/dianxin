<%@ page contentType="text/html; charset=GBK" %>
<%
	String sr1= request.getParameter("sr1");
        String sr2 = request.getParameter("sr2");
	String sr3 = request.getParameter("sr3");
        String sr4= request.getParameter("sr4");
        String sr5 = request.getParameter("sr5");
         String sr6 = request.getParameter("sr6");
	String sr7 = request.getParameter("sr7");
        String zc1= request.getParameter("zc1");
        String zc2 = request.getParameter("zc2");
	String zc3 = request.getParameter("zc3");
        String zc4= request.getParameter("zc4");
        String zc5 = request.getParameter("zc5");
	String zc6 = request.getParameter("zc6");
        String zc7= request.getParameter("zc7");
        String zc8 = request.getParameter("zc8");
%>
<html>
<head>
<title>
zc
</title>

</head>
<jsp:useBean id="huanbi" scope="page" class="com.noki.mobi.analysis.javabean.GraphHB_SR">
</jsp:useBean>
<body bgcolor="#ffffff">
 <%
			huanbi.set_hs_sr(sr1,sr2,sr3,sr4,sr5,sr6,sr7);
                        huanbi.set_hs_zc(zc1,zc2,zc3,zc4,zc5,zc6,zc7,zc8);
			String srg = huanbi.getChartViewer_hs_sr(request,response);
                        String zcg = huanbi.getChartViewer_hs_zc(request,response);
		  %>
			<div id=plane1>
			<img src="<%=srg%>" border=0 >
			</div>
                        <div id=plane2>
			<img src="<%=zcg%>" border=0 >
			</div>

</body>
</html>
