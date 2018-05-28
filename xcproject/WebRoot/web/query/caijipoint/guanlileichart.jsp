<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.query.caijipoint.javabean.GuanLiDB_Hdl" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Calendar" %>
<html>
<head>
<title>
</title>

</head>
<body style="margin: 0 0 0 0;">
 <%
   String ammeterid = request.getParameter("dbid")!=null?request.getParameter("dbid"):"";
   String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";
   String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";
        
    AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
    ArrayList dataList = bean.getCheckDayAmmeterDegree(ammeterid,beginTime,endTime);
	GuanLiDB_Hdl hdl = new GuanLiDB_Hdl();
  	hdl.setList(dataList);
	String srg = hdl.getChartViewer(request,response);
 %>
 
<div id=plane1>
   <img src="<%=srg%>" border=0 >
</div>
</body>
</html>
