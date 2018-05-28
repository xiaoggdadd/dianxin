<%@ page contentType="text/html; charset=UTF-8" %>
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
     LeaseBean lb = new LeaseBean();
     lb = (LeaseBean)request.getAttribute("lb");
%>
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
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>

<script language="javascript">

var path = '<%=path%>';

function saveDegree(){
	
	if(checkNotnull(document.form1.leasenum,"租赁合同编号")&&checkNotnull(document.form1.leasename,"租赁合同名称")&&
		checkNotnull(document.form1.begintime,"合同起始时间")&&checkNotnull(document.form1.endtime,"合同结束时间")&&
		checkNotnull(document.form1.money,"合同金额")&&checkNotnull(document.form1.agelimit,"合同年限")&&
		checkNotnull(document.form1.leasername,"出租方姓名")&&checkNotnull(document.form1.leaserbank,"出租方开户行")&&
		checkNotnull(document.form1.leaseraccount,"出租方账号")&&checkNotSelected(document.form1.payway,"支付方式")&&
		checkNotSelected(document.form1.paycircle,"付款周期")&&checkNotnull(document.form1.bargainhandler,"合同经办人")){
		
		var begint = document.form1.begintime.value;
		var endt = document.form1.endtime.value;
		var money = document.form1.money.value;
		var nx = document.form1.agelimit.value;
		var czzh = document.form1.leaseraccount.value;
		var phone = document.form1.leaserphone.value;
		
		var moneykg = money.replace(/[ ]/g,"");
		var nxkg = nx.replace(/[ ]/g,"");
		var czzhkg = czzh.replace(/[ ]/g,"");
		var phonekg = phone.replace(/[ ]/g,"");
		var czfxmkg = document.form1.leasername.value.replace(/[ ]/g,"");
		
		//判断座机格式的  
		var partten = /^(\d{3,4}\-)?\d{7,8}$/i; //座机格式是 010-98909899  
		//var partten = /^0(([1-9]\d)|([3-9]\d{2}))\d{8}$/; 没有中间那段 -的 座机格式是 01098909899  
		var zuoji=partten.test(phone);  
		//判断手机格式的
		var re = /^1[35]\d{9}$/i;  
		var shouji=re.test(phone); 
		
			if(endt>=begint){
				if(moneykg!=""){
					if(isNaN(money)==false && money>0){
						if(nxkg!=""){
							if(isNaN(nx)==false && nx>0){
								if(czfxmkg!=""){
									if(czzhkg!=""){
										if(isNaN(czzh)==false){
											if(phonekg!=""){
												if(zuoji || shouji){
													if(confirm("您将要添加信息！确认信息正确！")){
															document.form1.action=path+"/servlet/LeaseBill?action=modify";
															document.form1.submit()
															showdiv("请稍等..............");
													}
												}else{
													alert("你输入的电话号码有误！")  
													phone.focus();  
							 						return false;
												}
											}else{
												alert("电话号码不能为空！") ;
											}
										}else{
											alert("出租方账号只能为数字！");
										}
									}else{
										alert("出租方账号不能为空！");
									}
								}else{
									alert("出租方姓名不能为空！");	
								}
							}else{
								alert("合同年限必须为数字且大于0！");
							}
						}else{
							alert("合同年限不能为空！");
						}
						
					}else{
						alert("合同金额必须为数字且大于0！");
					}
				}else{
					alert("合同金额不能为空！");
				}
			}else{
				alert("合同结束时间不能小于合同起始时间！");
			}																          
	}
}

	$(function(){
        $("#saveBtn").click(function(){
		   saveDegree();
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
		<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">租赁合同信息修改</span>
	</div>
  <table border="0" align="center" width="97%">
  	<tr>
  		<td width="83%">  		
			<fieldset>
				<legend><img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">基本信息</font></b></legend>
	 			<table width="97%" align="right" CellSpacing="1">
				     <tr>    
				        <td bgcolor="#DDDDDD"   class="form_label">
				        	<input type="hidden" name="accountid" value="<%=accountid %>" class="form1" />
				        	<input type="hidden" name="dbid" value="${lb.dbid_fk}" class="form1" />
				        	<input type="hidden" name="leaseid" value="${leaseid}" class="form1" />
				        	<div>地区：</div>
				        </td>
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
				 		 <td><input type="text" id="leasenum" name="leasenum" value="${lb.leasenum}" class="form1" /> 
				 		 <span style="color: #FF0000;font-weight: bold">*</span></td>
				 		 
				         <td bgcolor="#DDDDDD" class="form_label"><div>租赁合同名称：</div></td>
				         <td><input type="text" id="leasename" name="leasename" value="${lb.leasename}" class="form1"/>
				         <span style="color: #FF0000;font-weight: bold">*</span></td>
				           
				         <td bgcolor="#DDDDDD"  class="form_label"><div>合同起始时间：</div></td>
				         <td><input type="text" name="begintime" value="${lb.begintime}" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="form"/>
				         <span style="color: #FF0000;font-weight: bold">*</span></td>
				     </tr>
				     <tr>
				         <td bgcolor="#DDDDDD" class="form_label"><div>合同结束时间：</div></td>
				         <td><input type="text" name="endtime" value="${lb.endtime}" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="form1"/>
						 <span style="color: #FF0000;font-weight: bold">*</span></td>
						 
				         <td bgcolor="#DDDDDD" class="form_label"><div>合同金额（元）：</div></td>
				         <td><input type="text" name="money" value="${lb.money}" class="form1" />
						 <span style="color: #FF0000;font-weight: bold">*</span></td>
						 
				         <td height="19" bgcolor="#DDDDDD" class="form_label"><div>合同年限（年）：</div></td>
				         <td><input type="text" name="agelimit" value="${lb.agelimit}" class="form1" />
						 <span style="color: #FF0000;font-weight: bold">*</span></td>
				     </tr>
				     <tr>
				          <td bgcolor="#DDDDDD" class="form_label"><div>出租方姓名：</div></td>
				          <td><input type="text" name="leasername" value="${lb.leasername}" class="form1"/>
						  <span style="color: #FF0000;font-weight: bold">*</span></td>
						  
				          <td bgcolor="#DDDDDD"   class="form_label"><div>出租方电话：</div></td>
				          <td><input type="text" name="leaserphone" value="${lb.leaserphone}" class="form1" /></td>
				          
				          <td bgcolor="#DDDDDD" class="form_label"><div>出租方开户行：</div></td>
				          <td><input type="text" name="leaserbank" value="${lb.leaserbank}"  class="form1" />
						  <span style="color: #FF0000;font-weight: bold">*</span></td>
				     </tr>
				     <tr>
				     	  <td bgcolor="#DDDDDD" class="form_label"><div>出租方账号：</div></td>
				          <td><input type="text" name="leaseraccount" value="${lb.leaseraccount}"  class="form1" />
						  <span style="color: #FF0000;font-weight: bold">*</span></td>
				          
				          <td bgcolor="#DDDDDD" class="form_label"><div>支付方式：</div></td>
				          <td><div><select name="payway" style="width:130">
				         		<option value="0">请选择</option>
					         <%
					         	ArrayList feetypelist = new ArrayList();
					         	feetypelist = commBean.getPayway();
					         	if(feetypelist!=null){
					         		String sfid="",sfnm="";
					         		int scount = ((Integer)feetypelist.get(0)).intValue();
					         		for(int i=scount;i<feetypelist.size()-1;i+=scount)
				                    {
				                    	sfid=(String)feetypelist.get(i+feetypelist.indexOf("CODE"));
				                    	sfnm=(String)feetypelist.get(i+feetypelist.indexOf("NAME"));
				                    	if(sfid.equals(lb.getPayway())){
				                %>
				                 <option value="<%=sfid%>" selected><%=sfnm%></option>
                    			<%}else{ %>
				                     <option value="<%=sfid%>"><%=sfnm%></option>
				                    <%}
				                    }
					         	} %>
					         </select><span style="color: #FF0000;font-weight: bold"> *</span>	
					         </div>       
					      </td>
				          <td bgcolor="#DDDDDD" class="form_label"><div>付款周期（月）：</div></td>
				          <td><div><select name="paycircle" style="width:130">
				         		<option value="0">请选择</option>
					         <%
					         	ArrayList fklist = new ArrayList();
					         	fklist = commBean.getPaycircle();
					         	if(fklist!=null){
					         		String sfid="",sfnm="";
					         		int scount = ((Integer)fklist.get(0)).intValue();
					         		for(int i=scount;i<fklist.size()-1;i+=scount)
				                    {
				                    	sfid=(String)fklist.get(i+fklist.indexOf("CODE"));
				                    	sfnm=(String)fklist.get(i+fklist.indexOf("NAME"));
				                    	if(sfid.equals(lb.getPaycircle())){
				                %>
				                <option value="<%=sfid%>" selected><%=sfnm%></option>
                    			<%}else{ %>
				                     <option value="<%=sfid%>"><%=sfnm%></option>
				                    <%}
				                    }	
					         	} %>
					         </select><span style="color: #FF0000;font-weight: bold"> *</span>	
					         </div>
					     </td>
				     </tr>
				     <tr> 
				         <td bgcolor="#DDDDDD" class="form_label"><div>合同经办人：</div></td>
				         <td><input type="text" name="bargainhandler" value="${lb.bargainhandler}"  class="form1" />
						 <span style="color: #FF0000;font-weight: bold">*</span></td>
				     </tr>
				</table>
			</fieldset>
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
