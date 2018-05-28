<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>

<html>
<head>
<%
	String path = request.getContextPath();
%>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
	<LINK href="<%=path%>/images/css.css" type=text/css rel=stylesheet> 
</head>
<body style="margin: 0 0 0 0;">
<table width="100%" id="tb"  border="1" cellspacing="0" cellpadding="0">
   <%
   	      String ammeterid = request.getParameter("dbid")!=null?request.getParameter("dbid"):"";
          String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";
        	String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";
        	AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
        	List list = bean.getCheckDayAmmeterDegree(ammeterid,beginTime,endTime);
        	//session.setAttribute("dataList",list);
        	
      %>
	 <tr bgcolor="#888888" align="center"> 
       <td width="10%" height="23"><div class="bttcn">电表ID</div></td>
       <td width="10%" height="23"><div class="bttcn">电量采集日期</div></td>
       <td width="10%" height="23"><div class="bttcn">实际电度数(度)</div></td>
	</tr>
  <% for(int i=0;i<list.size();i++){
      if(i%2==0){
     %>
     <tr bgcolor="#DDDDDD" align="center"> 
       <td width="10%" height="23"><%=ammeterid%></td>
       <td width="10%" height="23"><%=list.get(i).toString().split(",")[0]%></td>
       <td width="10%" height="23"><%=list.get(i).toString().split(",")[1]%></td>
	</tr>
  <% } else if(i%2==1) {%>
   <tr bgcolor="#FFFFFF" align="center"> 
       <td width="10%" height="23"><%=ammeterid%></td>
       <td width="10%" height="23"><%=list.get(i).toString().split(",")[0]%></td>
       <td width="10%" height="23"><%=list.get(i).toString().split(",")[1]%></td>
	</tr>
	<%} } %>
</table>
</body>
</html>
