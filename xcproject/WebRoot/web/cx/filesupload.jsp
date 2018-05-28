<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime"%>
<%@ page import="java.sql.ResultSet"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String path = request.getContextPath();
%>

<html>
	<head>
		<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
		<script src="<%=path%>/javascript/PopupCalendar.js"></script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
		<script type="text/javascript">
			var oCalendarEnny=new PopupCalendarny("oCalendarEnny");	//初始化控件时,请给出实例名称如:oCalendarEn
			oCalendarEnny.Init();
			 
			var oCalendarChsny=new PopupCalendarny("oCalendarChsny");	//初始化控件时,请给出实例名称:oCalendarChs
			oCalendarChsny.weekDaySting=new Array("日","一","二","三","四","五","六");
			oCalendarChsny.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
			oCalendarChsny.oBtnTodayTitle="确定";
			oCalendarChsny.oBtnCancelTitle="取消";
			oCalendarChsny.Init();
		</script>
		<script language="javascript">
			var path = '<%=path%>';
			
			
			function shangchuan(){
				$("#errorInfo").text("");
				var month = $("#month").val();
				
				document.form1.action=path+"/servlet/FilesUploadServlet?month="+month;
				document.form1.submit()
			}

			$(function(){
		
		$("#uploading").click(function(){
			shangchuan();
		});
	});

		</script>
<style type="text/css">
		.style1 {
	color: #FF0000;
	
}
</style>
	</head>
	<body class="body" style="overflow-x: hidden;">
		<div style="position: relative;">
			<img src="<%=path%>/images/btt.bmp">
			<span style="position:absolute;left:40px;top:22px; font-weight: bold;">文件上传</span>
		</div>
		<form id="form1" name="form1" action="" method="post" enctype="multipart/form-data" >
           <br />
			<div id="parent" style="display: inline">
				<div style="width: 50px; display: inline;">
					<hr>
				</div>
				&nbsp;文件上传&nbsp;
				<div style="width: 300px; display: inline;">
					<hr>
				</div>
				<br/><br>
				<input type="hidden" id="month"  onFocus="getDateString(this,oCalendarChsny)"/>
				<br />
			</div>
			<br /> 
			<div style="width: 370px; height: 25px; float:left;" >
			请选择上传文件：
			<input type="file" id="fileUp" name="fileUp" height="25px">
			</div>
		    <div id="uploading" style="position: relative; width: 60px; height: 20px; float:left; pointer;" >
				<img alt=""src="<%=request.getContextPath() %>/images/shangchuan.png" width="100%" height="100%" />
				<span style="font-size: 12px; position: absolute; left: 26px; top: 3px; color: white">上传</span>
			</div>
			<div id="errorInfo" style="width:200px;height:50px;position:relative;font-size: 12px;color:red">
				<c:forEach items="${requestScope.statusInfo}" var="info">
					<br />${info}
				</c:forEach>
			</div>
			
		</form>
	</body>
</html>
