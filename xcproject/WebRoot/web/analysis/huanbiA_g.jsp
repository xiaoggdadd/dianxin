<%@ page contentType="text/html; charset=UTF-8" %>
<%
	String sr1= request.getParameter("sr1");
        String sr2 = request.getParameter("sr2");

        String zc1= request.getParameter("zc1");
        String zc2 = request.getParameter("zc2");

%>
<html>
<head>
<title>
zc
</title>

</head>
<jsp:useBean id="huanbi" scope="page" class="com.noki.mobi.analysis.javabean.Hb_tx">
</jsp:useBean>
<body bgcolor="#ffffff">
 <%
			huanbi.set_sr(sr1,sr2);
                        huanbi.set_zc(zc1,zc2);
			String srg = huanbi.getChartViewer(request,response);
                        String zcg = huanbi.getChartViewer_zc(request,response);
		  %>
			<div id=plane1>
			<img src="<%=srg%>" border=0 >
			</div>
                        <div id=plane2>
			<img src="<%=zcg%>" border=0 >
			</div>

</body>
</html>
