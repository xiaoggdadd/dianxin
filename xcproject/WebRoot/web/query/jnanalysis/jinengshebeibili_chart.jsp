<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.jfree.chart.JFreeChart,
                org.jfree.chart.plot.PlotOrientation,
                org.jfree.chart.servlet.ServletUtilities,
                org.jfree.data.category.DefaultCategoryDataset,
                org.jfree.chart.ChartFactory" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   String zdnum_bl=request.getParameter("zdnum_bl");
   
  
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jinengshebeibili_chart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <jsp:useBean id="bili" scope="page" class="com.noki.query.jcanalysis.javabean.Jienengsheibeibili">
</jsp:useBean>
  <body>
    <%
        
       /*  bili.setJieneng(zdnum_bl);
         JFreeChart chart =bili.createPieChart3D(request,response);
         String filename=ServletUtilities.saveChartAsJPEG(chart,500,500,null,session);
         String graphURL=request.getContextPath()+"/DisplayChart?filename="+filename;*/
         
         
     %>
     <img src="" border=0 >
     
  </body>
</html>
