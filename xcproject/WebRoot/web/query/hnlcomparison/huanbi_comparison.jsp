<%@ page contentType="text/html; charset=UTF-8" %>
<%
	    String titleStr= "实际耗电量;预算耗电量;实际水消耗量;预算水消耗量;实际汽油消耗量;预算汽油消耗量;实际柴油消耗量;预算柴油消耗量;实际天然气消耗量;预算天然气消耗量";
        String dataStr = request.getParameter("dataStr");
        String timeStr = request.getParameter("timeStr");
        String bz = request.getParameter("bz");
        String bzStr = "";
        if(bz.equals("sheng")){
          bzStr = "省级-分项耗能";
        }else if(bz.equals("shi")){
          bzStr = "地市-分项耗能";
        }
        System.out.println(dataStr+"*******"+timeStr);
%>
<html>
<head>
<title>
zc
</title>

</head>
<jsp:useBean id="huanbi" scope="page" class="com.noki.query.hnlcomparison.javabean.Hb_comparison">
</jsp:useBean>
<body bgcolor="#ffffff">
 <%
			huanbi.setCityStr(titleStr);
			huanbi.setDataStr(dataStr);
			huanbi.setTimeStr(timeStr);
			huanbi.setChartTitle(bzStr);
			String srg = huanbi.getChartViewer(request,response);
 %>
			<div id=plane1>
			<img src="<%=srg%>" border=0 >
			</div>
                     
</body>
</html>
