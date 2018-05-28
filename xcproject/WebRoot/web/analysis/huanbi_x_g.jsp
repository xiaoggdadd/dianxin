<%@ page contentType="text/html; charset=GBK" %>
<%
	String sr1= request.getParameter("sr1");
        String sr2 = request.getParameter("sr2");
	String sr3 = request.getParameter("sr3");
        String zc1= request.getParameter("zc1");
        String zc2 = request.getParameter("zc2");
	String zc3 = request.getParameter("zc3");
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
			huanbi.set_hx_sr(sr1,sr2,sr3);
                        huanbi.set_hx_zc(zc1,zc2,zc3);
			String srg = huanbi.getChartViewer_hx_sr(request,response);
                        String zcg = huanbi.getChartViewer_hx_zc(request,response);
		  %>
			<div id=plane1>
			<img src="<%=srg%>" border=0 >
			</div>
                        <div id=plane2>
			<img src="<%=zcg%>" border=0 >
			</div>

</body>
</html>
