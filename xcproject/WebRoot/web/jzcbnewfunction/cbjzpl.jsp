﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean,com.noki.newfunction.javabean.Zdinfo,com.noki.newfunction.dao.CbzdQuery"%>
<%@ page import="java.text.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<% 
		int intnum=0;
		String color="";
		String zdid=request.getParameter("idad");
		String path = request.getContextPath();
		 Account account = (Account)session.getAttribute("account");
         String loginId = account.getAccountId();
          String loginName = account.getAccountName();
		
%>
<head>
<title>整改信息
</title>
<style>
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	width: 130px;
}
.form1{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;   
   height:19px;
   width:130px;
   
   
}

.form {width:130px}
.bttcn{color:black;font-weight:bold;}
 </style>
  <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
 <script >

var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();


var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init();
</script>
</head>
<body  class="body">
<form id="form1"  name="form1"  action="" method="post" enctype="multipart/form-data">
	<table>
		<tr><td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;整改信息&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
	</table>  	

<table>
<tr>
<td bgcolor="#DDDDDD" class="form_label">
									<div>
										整改要求说明：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="zgyq" value="" class="formjc" />
								</td>
</tr>
<tr>
<td bgcolor="#DDDDDD" class="form_label"><div>完成时间：</div></td>
							<td><input type="text" class="form" id="entrytimeQS" name="entrytimeQS" value="<%if(null!=request.getParameter("entrytimeQS")) out.print(request.getParameter("entrytimeQS")); %>" onFocus="getDateString(this,oCalendarChs)" /></td>

</tr>
</table>

<table>
<tr>
<td colspan="9">
         <div style="width: 370px; height: 25px; float:left;" >
			  请选择上传文件：
		      <input type="file" id="fileUp" name="fileUp" height="25px">
		 </div>
	
		
		<div id="errorInfo" style="width:200px;height:50px;position:relative;font-size: 12px;color:red">
				<c:forEach items="${requestScope.statusInfo}" var="info">
					<br />${info}
				</c:forEach>
		</div>
		 <div id="uploading2" style="position: relative; width: 60px; height: 20px; pointer;" >
				<img alt=""src="<%=request.getContextPath() %>/images/shangchuan.png" width="100%" height="100%" />
				<span style="font-size: 12px; position: absolute; left: 26px; top: 3px; color: white">派单</span>
		 </div>
		</td>
</tr>

</table>
<input type="hidden" id="ida" name="ida" value="<%=zdid%>"/>
</form>
</body>
</html>

 	<script language="javascript">
		function shangchuan(){
		var path='<%=path%>';
		var id=document.form1.ida.value;
		//alert("id:"+id);
		var sjyq=document.form1.zgyq.value;// entrytimeQS
		var entrytimeQS=document.form1.entrytimeQS.value;
		
				$("#errorInfo").text("");
				document.form1.action=path+"/UploadB?id="+id+"&sjyq="+sjyq+"&action=ejsh"+"&bzw=4"+"&wcsj="+entrytimeQS;
				
				document.form1.submit()
			}
    $(function(){
		$("#cancelBtn").click(function(){
		 daochu(1);
		 
		});

		$("#cancela").click(function(){
			daochu(0);
		});

		$("#uploading").click(function(){
			shangchuan2();
		});
		$("#uploading2").click(function(){
			shangchuan();
		});
	});
		</script>
