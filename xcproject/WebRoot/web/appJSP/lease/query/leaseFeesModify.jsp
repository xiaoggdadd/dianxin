<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.*" %>
<%	String path = request.getContextPath(); %>

<html>
<head>
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
  <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
  <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
  <script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
 <script type="text/javascript" language="javascript">
 var path = "<%=path%>";
function saveLeaseFees(){
	 var paybegintime = document.form1.paybegintime.value;//缴费开始时间
	 var payendtime = document.form1.payendtime.value;//缴费结束时间
	 var paymoney = document.form1.paymoney.value;//缴费金额
	 var payhandler = document.form1.payhandler.value;//付款经办人
	 var payer = payhandler.replace(/[ ]/g,"");//付款经办人去空格
	 var endtime = document.form1.endtime.value;//合同结束时间
	 
	 if(checkNotnull(document.form1.paybegintime,"缴费开始时间")&&checkNotnull(document.form1.payendtime,"缴费结束时间")
			 &&checkNotnull(document.form1.paymoney,"金额")&&checkNotnull(document.form1.payhandler,"付款经办人")
			 &&checkNotnull(document.form1.accountmonth,"报账月份")){
		 if(paybegintime<=payendtime){
			  if(endtime>=payendtime){
				 if(!isNaN(paymoney)){
					 if(parseFloat(paymoney)>0){
						 if(payer != ""){
							 	 if(confirm("您将要添加信息！确认信息正确！")){
					        		document.form1.action=path+"/servlet/LeaseBargainFeesServlet?action=modify";
									document.form1.submit();
									showdiv("请稍等..............");
					       		 }
						 	 }else{
						 		alert("付款经办人不能为空！"); 
						 	 }
				 	 	}else{
				 	 		alert("金额必须大于0！");
				 	 	}
				 	 }else{
				 		 alert("金额必须为数字!");
				 	 }
				 }else{
					alert("缴费结束时间不能大于合同结束时间！"); 
				 }
		}else{
			alert("缴费结束时间不能小于缴费开始时间！");
		}
	 }
 }
 
 $(function(){
        $("#saveBtn").click(function(){
		   saveLeaseFees();
		});
        $("#resetBtn").click(function() {	
        	$("#form")[0].reset();
        });
		$("#cancelBtn").click(function(){
			window.history.back();
		});
        
	});
</script>
</head>
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean"></jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST" id="form">

	<div style="width:700px;height:50px">
		<img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
		<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">添加租赁费用</span>
	</div>
  <table border="0" align="center" width="97%">
  	<tr>
  		<td width="83%">  		
			<fieldset>
				<legend><img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">基本信息</font></b></legend>
	 			<table width="97%" align="right" CellSpacing="1">
				     <tr>    
				        <td bgcolor="#DDDDDD"   class="form_label"><div>地区：</div></td>
				        <td width="21%"><input type="text" name="diqu" readonly="readonly" value="${lbf.shi}${lbf.xian}${lbf.xiaoqu}"  class="form1" /></td>
						<td bgcolor="#DDDDDD"   class="form_label"><div>站点类型：</div></td>
				        <td width="21%"><input type="text" name="stationtype"  readonly="readonly" value="${lbf.stationtype}"  class="form1" /></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>站点名称：</div></td>
				        <td><input type="text" name="gsf"  readonly="readonly" value="${lbf.zdname}"  class="form1" /></td>
				     </tr>
				     <tr>
				     	<td bgcolor="#DDDDDD"   class="form_label"><div>面积(m2)：</div></td>
				        <td><input type="text" name="area"  readonly="readonly" value="${lbf.area}"  class="form1" /></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>详细地址：</div></td>
				        <td><input type="text" name="gsf"  readonly="readonly" value="${lbf.address}"  class="form1" /></td>
				     </tr>
				</table>
			</fieldset>
			<fieldset>
				<legend><img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">合同信息</font></b></legend>
	 			<table width="97%" align="right" CellSpacing="1">
				     <tr>    
				        <td bgcolor="#DDDDDD"   class="form_label"><div>租赁合同编号：</div></td>
				        <td width="21%"><input type="text" name="leasenum" readonly="readonly" value="${lbf.leasenum}"  class="form1" /></td>
						<td bgcolor="#DDDDDD"   class="form_label"><div>租赁合同名称：</div></td>
				        <td width="21%"><input type="text" name="leasename"  readonly="readonly" value="${lbf.leasename}"  class="form1" /></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>合同起始时间：</div></td>
				        <td><input type="text" name="begintime"  readonly="readonly" value="${lbf.begintime}"  class="form1" /></td>
				     </tr>
				     <tr>
				     <td bgcolor="#DDDDDD"   class="form_label"><div>合同结束时间：</div></td>
				        <td><input type="text" name="endtime"  readonly="readonly" value="${lbf.endtime}"  class="form1" /></td>
				     	<td bgcolor="#DDDDDD"   class="form_label"><div>合同金额(元)：</div></td>
				        <td><input type="text" name="money"  readonly="readonly" value="${lbf.money}"  class="form1" /></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>合同年限(年)：</div></td>
				        <td><input type="text" name="agelimit"  readonly="readonly" value="${lbf.agelimit}"  class="form1" /></td>
				     </tr>
				</table>
			</fieldset>
						<fieldset>
				<legend><img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">费用信息</font></b></legend>
	 			<table width="97%" align="right" CellSpacing="1">
				     <tr>    
				        <td bgcolor="#DDDDDD"   class="form_label"><div>缴费开始时间：</div></td>
				        <td width="21%"><input type="text" name="paybegintime" readonly="readonly" value="${lbf.paybegintime}" class="form1" /><span style="color: #FF0000;font-weight: bold"> *</span></td>
						<td bgcolor="#DDDDDD"   class="form_label"><div>缴费结束时间：</div></td>
				        <td width="21%"><input type="text" name="payendtime"   value="${lbf.payendtime}" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="form1" /><span style="color: #FF0000;font-weight: bold"> *</span></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>金额（元）：</div></td>
				        <td><input type="text" name="paymoney"   value="${lbf.paymoney}"  class="form1" /><span style="color: #FF0000;font-weight: bold"> *</span></td>
				     </tr>
				     <tr>
				     <td bgcolor="#DDDDDD"   class="form_label"><div>付款经办人：</div></td>
				        <td><input type="text" name="payhandler"   value="${lbf.payhandler}"  class="form1" /><span style="color: #FF0000;font-weight: bold"> *</span></td>
				     	<td bgcolor="#DDDDDD"   class="form_label"><div>报账月份：</div></td>
				        <td><input type="text" name="accountmonth" readonly="readonly" value = "${lbf.accountmonth}"   class="form1" /><span style="color: #FF0000;font-weight: bold"> *</span></td>
				     </tr>
				</table>
			</fieldset>
			<input type="hidden" name="leasefeeid" value="${lbf.leasefeeid}"/>
  		</td>
 		
	</tr>
	<tr>
        <td>
            <input type="hidden" name="lastgree01" id="lastgree01" value=""/>
	        <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:0px">
				<img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
				<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
			</div>
	         <div id="resetBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:11px">
				 <img src="<%=path%>/images/2chongzhi.png" width="100%" height="100%">
				 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">重置</span>      
			</div>
			
	         <div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:22px">
				 <img src="<%=path%>/images/baocun.png" width="100%" height="100%">
				 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>      
			</div>
			
	     </td>
     </tr>
     
</table>
</form>
  </body>
</html>
