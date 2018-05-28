<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.ptac.app.basisquery.liucheng.dao.LiuChengDaoImpl" %>
<%@ page import="java.sql.ResultSet,com.ptac.app.basisquery.liucheng.bean.LiuCheng"%>
<%@ page import="java.text.*"%>
<html>
<% 
        String path = request.getContextPath();
		LiuCheng bean = (LiuCheng)request.getAttribute("bean");	
		String zdzt = bean.getZdzt();
		String rgzt = bean.getRgzt();
		String qxzt = bean.getQxzt();
		String sjzt = bean.getSjzt();
		String szrzt = bean.getSzrzt();
		String cwzt = bean.getCwzt();
%>
<head>
<title>详细信息
</title>
</head>
<body  style="font-size: 12px;">
	<center>
		<div style="background-image:url(/energy/images/test.png); background-repeat: no-repeat;width:500px;height:600px;">
			<div style="padding-left:20px; padding-top:30px;" title="录入时间:${bean.lrsj}&#10;录入人员:${bean.lrry}">电费单</div>
			<div style="padding-left:20px; padding-top:27px;" title="审核状态:${bean.zdzt}">自动审核</div>
			<%if(rgzt.equals("未审核") && zdzt.equals("通过")){ %>
				<div style="padding-left:20px; padding-top:35px;" title="审核状态:${bean.rgzt}&#10;审核时间:${bean.rgtime}&#10;审核人:${bean.rgpeople}"><font color="red">人工审核</font></div>
			<%}else if(rgzt.equals("通过") && qxzt.equals("不通过")){%>
				<div style="padding-left:20px; padding-top:35px;" title="审核状态:${bean.rgzt}&#10;审核时间:${bean.rgtime}&#10;审核人:${bean.rgpeople}"><font color="red">人工审核</font></div>
			<%}else{%>
				<div style="padding-left:20px; padding-top:35px;" title="审核状态:${bean.rgzt}&#10;审核时间:${bean.rgtime}&#10;审核人:${bean.rgpeople}">人工审核</div>
			<%}%>
			
			<%if(qxzt.equals("未审核") && rgzt.equals("通过")){ %>
				<div style="padding-left:20px; padding-top:60px;" title="审核状态:${bean.qxzt}&#10;审核时间:${bean.qxtime}&#10;审核人:${bean.qxpeople}"><font color="red">区县主任</font></div>
			<%}else if(qxzt.equals("通过") && sjzt.equals("不通过")){%>
				<div style="padding-left:20px; padding-top:60px;" title="审核状态:${bean.qxzt}&#10;审核时间:${bean.qxtime}&#10;审核人:${bean.qxpeople}"><font color="red">区县主任</font></div>
			<%}else{%>
				<div style="padding-left:20px; padding-top:60px;" title="审核状态:${bean.qxzt}&#10;审核时间:${bean.qxtime}&#10;审核人:${bean.qxpeople}">区县主任</div>
			<%}%>
			
			<%if(sjzt.equals("未审核") && qxzt.equals("通过")){ %>
				<div style="padding-right:200px; padding-top:83px;" title="审核状态:${bean.sjzt}&#10;审核时间:${bean.sjtime}&#10;审核人:${bean.sjpeople}"><font color="red">市级审核</font></div>
			<%}else if(sjzt.equals("通过") && szrzt.equals("不通过")){%>
				<div style="padding-right:200px; padding-top:83px;" title="审核状态:${bean.sjzt}&#10;审核时间:${bean.sjtime}&#10;审核人:${bean.sjpeople}"><font color="red">市级审核</font></div>
			<%}else{%>
				<div style="padding-right:200px; padding-top:83px;" title="审核状态:${bean.sjzt}&#10;审核时间:${bean.sjtime}&#10;审核人:${bean.sjpeople}">市级审核</div>
			<%}%>
			
			<%if(szrzt.equals("未审核") && sjzt.equals("通过")){ %>
				<div style="padding-right:200px; padding-top:60px;" title="审核状态:${bean.szrzt}&#10;审核时间:${bean.szrtime}&#10;审核人:${bean.szrpeople}"><font color="red">市级主任</font></div>
			<%}else if(szrzt.equals("通过") && cwzt.equals("不通过")){%>
				<div style="padding-right:200px; padding-top:60px;" title="审核状态:${bean.szrzt}&#10;审核时间:${bean.szrtime}&#10;审核人:${bean.szrpeople}"><font color="red">市级主任</font></div>
			<%}else{%>
				<div style="padding-right:200px; padding-top:60px;" title="审核状态:${bean.szrzt}&#10;审核时间:${bean.szrtime}&#10;审核人:${bean.szrpeople}">市级主任</div>
			<%}%>
			
			<div style="padding-left:20px; padding-top:50px;" title="打印时间:${bean.dysj}&#10;打印人员:${bean.dyr}">报账打印</div>
			
			<%if(cwzt.equals("未审核") && szrzt.equals("通过")){ %> 
				<div style="padding-left:20px; padding-top:40px;" title="审核状态:${bean.cwzt}&#10;审核时间:${bean.cwtime}&#10;审核人:${bean.cwpeople}"><font color="red">财务确认</font></div>
			<%}else if(cwzt.equals("不通过")){%>
				<div style="padding-left:20px; padding-top:40px;" title="审核状态:${bean.cwzt}&#10;审核时间:${bean.cwtime}&#10;审核人:${bean.cwpeople}"><font color="red">财务确认</font></div>
			<%}else{%>
				<div style="padding-left:20px; padding-top:40px;" title="审核状态:${bean.cwzt}&#10;审核时间:${bean.cwtime}&#10;审核人:${bean.cwpeople}">财务确认</div>
			<%}%>
			
			<div style="padding-left:20px; padding-top:38px;" title="">查询分析</div>
		</div>
		<%if(zdzt.equals("通过") && rgzt.equals("未审核") && qxzt.equals("通过") && szrzt.equals("通过")){%>
			<h4>注:如果是自动审核通过,但人工未审核的单子,则区县主任和市级主任都是自动审核通过</h4>
		<%}%>
	</center>
</body>
</html>



