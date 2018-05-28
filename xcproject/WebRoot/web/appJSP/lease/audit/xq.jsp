<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.ptac.app.lease.query.bean.LeaseBean" %>
<%
	 String path = request.getContextPath();
	 Account account = (Account)session.getAttribute("account");
     String roleId = account.getRoleId();
     String accountid=account.getAccountName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<style>
.form_label{
	text-align: right;
	font-family: 微软雅黑;
	font-size: 12px;
	width:13%;
}
.form_labell{
	text-align: right;
	font-family: 微软雅黑;
	font-size: 12px;
	width:120px;
}
.form{
   height:19px;
   width:130px;
}

.formm{
   text-align: right;
   height:19px;
   width:80px;
}
.formr{
   text-align: right;
   height:19px;
   width:130px;
}
.form1{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;
   height:19px;
   width:130px;
}
.form2{
   border-left-width:0px; border-top-width:0px; border-right-width:0px;
   text-align: right;
   height:19px;
   width:130px;
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>

</head>
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean"></jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="post" id="form">
	<div style="width:700px;height:50px">
		<img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
		<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">租赁合同审核详细信息</span>
	</div>
  <table border="0" align="center" width="97%">
  	<tr>
  		<td width="83%">  		
			<fieldset>
				<legend><img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">基本信息</font></b></legend>
	 			<table width="97%" align="right" CellSpacing="1">
				     <tr>    
				        <td bgcolor="#DDDDDD"   class="form_label"><div>地区：</div></td>
				        <td width="21%"><input type="text" name="area" readonly="readonly" value="${lb.area}"  class="form1" /></td>
						<td bgcolor="#DDDDDD"   class="form_label"><div>站点类型：</div></td>
				        <td width="21%"><input type="text" name="stationtype"  readonly="readonly" value="${lb.stationtype}"  class="form1" /></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>站点名称：</div></td>
				        <td><input type="text" name="jzname"  readonly="readonly" value="${lb.jzname}"  class="form1" /></td>
				     </tr>
				     <tr>
				     	<td bgcolor="#DDDDDD"   class="form_label"><div>面积：</div></td>
				        <td><input type="text" name="mj" readonly="readonly" value="${lb.mj}"  class="form1" /></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>详细地址：</div></td>
				        <td><input type="text" name="address" readonly="readonly" value="${lb.address}"  class="form1" /></td>
				     </tr>
				</table>
			</fieldset>
			<fieldset>
				<legend><img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">租赁合同信息</font></b></legend>
	 			<table width="97%" align="right" CellSpacing="1">
				     <tr>
				         <td bgcolor="#DDDDDD" class="form_label"><div>租赁合同编号：</div></td> 
				 		 <td><input type="text" id="leasenum" name="leasenum" value="${lb.leasenum}" readonly="readonly" class="form1" /></td>
				 		 
				         <td bgcolor="#DDDDDD" class="form_label"><div>租赁合同名称：</div></td>
				         <td><input type="text" id="leasename" name="leasename" value="${lb.leasename}" readonly="readonly" class="form1"/></td>
				           
				         <td bgcolor="#DDDDDD"  class="form_label"><div>合同起始时间：</div></td>
				         <td><input type="text" name="begintime" value="${lb.begintime}" readonly="readonly" class="form"/></td>
				     </tr>
				     <tr>
				         <td bgcolor="#DDDDDD" class="form_label"><div>合同结束时间：</div></td>
				         <td><input type="text" name="endtime" value="${lb.endtime}" readonly="readonly" class="form1"/></td>
						 
				         <td bgcolor="#DDDDDD" class="form_label"><div>合同金额（元）：</div></td>
				         <td><input type="text" name="money" value="${lb.money}" readonly="readonly" class="form1" /></td>
						 
				         <td height="19" bgcolor="#DDDDDD" class="form_label"><div>合同年限（年）：</div></td>
				         <td><input type="text" name="agelimit" value="${lb.agelimit}" readonly="readonly" class="form1" /></td>
				     </tr>
				     <tr>
				          <td bgcolor="#DDDDDD" class="form_label"><div>出租方姓名：</div></td>
				          <td><input type="text" name="leasername" value="${lb.leasername}" readonly="readonly" class="form1"/></td>
						  
				          <td bgcolor="#DDDDDD"   class="form_label"><div>出租方电话：</div></td>
				          <td><input type="text" name="leaserphone" value="${lb.leaserphone}" readonly="readonly" class="form1" /></td>
				          
				          <td bgcolor="#DDDDDD" class="form_label"><div>出租方开户行：</div></td>
				          <td><input type="text" name="leaserbank" value="${lb.leaserbank}" readonly="readonly" class="form1" /></td>
				     </tr>
				     <tr>
				     	  <td bgcolor="#DDDDDD" class="form_label"><div>出租方账号：</div></td>
				          <td><input type="text" name="leaseraccount" value="${lb.leaseraccount}" readonly="readonly" class="form1" /></td>
				          
				          <td bgcolor="#DDDDDD" class="form_label"><div>支付方式：</div></td>
				          <td><input type="text" name="payway" value="${lb.payway}" readonly="readonly" class="form1" /></td>
			
				          <td bgcolor="#DDDDDD" class="form_label"><div>付款周期（月）：</div></td>
				          <td><input type="text" name="paycircle" value="${lb.paycircle}" readonly="readonly" class="form1" /></td>
				     </tr>
				     <tr> 
				         <td bgcolor="#DDDDDD" class="form_label"><div>合同经办人：</div></td>
				         <td><input type="text" name="bargainhandler" value="${lb.bargainhandler}" readonly="readonly" class="form1" />
				     </tr>
				</table>
			</fieldset>
  		</td>
	</tr>
</table>
</form> 
</body>
</html>
