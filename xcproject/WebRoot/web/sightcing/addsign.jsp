<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addsign.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
   <style>
   .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}
  
  
  </style>
  <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
<script type="text/javascript">
var path='<%=path%>';

function saveAccount(){
        		if(confirm("您将要添加站点信息！确认信息正确！")){
          		document.form1.action=path+"/servlet/zhandian?action=addsign";
							document.form1.submit();
            }
        	}
$(function(){
			$("#cancelBtn").click(function(){
				window.history.back();
			});
			$("#resetBtn").click(function(){
				$.each($("form input[type='text']"),function(){
					$(this).val("");
				})
			});
			$("#saveBtn").click(function(){
				saveAccount();
			});
		
		});


</script>
  <body>
   <form action="" name="form1" method="post">
   	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4"></td>
						 </tr>
						 <tr>
							<td colspan=1 width="700"
							 background="<%=path%>/images/btt.bmp" height=63>
							<span style="color: black; font-weight: bold;font-size:14px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加标杆类型</span>
							</td>

							<td width="380">&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
					
					<tr>
					<td>
					<div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>
                      </td>
                   </tr>
		 <tr>
			<td>&nbsp;</td>
		</tr>
		</table>	
   <table class="form_label">
   		<tr>
   			<td>&nbsp;</td>
   		</tr>
   		<tr>
			 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">标杆类型名称：</div></td>
             <td width="25%"><input type="text" name="signname" value="" style="width:130px" /></td> 
             <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点类型：</div>
         </td>
         <td width="25%">
         	<select name="stationtype" style="width:130" onchange="kzinfo()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList stationtype = new ArrayList();
         		stationtype = ztcommon.getSelctOptions("StationType");
	         	if(stationtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)stationtype.get(0)).intValue();
	         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
                    {
                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	
          </td>   
         
          <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">地域类型：</div>
         </td>
         <td width="25%">
         	<select name="dytype" style="width:130">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList dy = new ArrayList();
         		dy = ztcommon.getSelctXslx("dytype");
	         	if(dy!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)dy.get(0)).intValue();
	         		for(int i=cscount;i<dy.size()-1;i+=cscount)
                    {
                    	code=(String)dy.get(i+dy.indexOf("CODE"));
                    	name=(String)dy.get(i+dy.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         	</td>     
   		</tr>
   <tr>
   	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">2G：</div>
         </td>
         <td width="25%">
         	<select name="g2" style="width:130">
         		<option value="0">否</option>
         		<option value="1">是</option>
         		
         	</select>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">3G：</div>
         </td>
         <td width="25%">
         	<select name="g3" style="width:130">
         		<option value="0">否</option>
         		<option value="1">是</option>
         		
         	</select>
         </td>
   		<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">OLTG：</div>
         </td>
         <td width="25%">
         	<select name="oltg" style="width:130">
         		<option value="0">否</option>
         		<option value="1">是</option>
         		
         	</select>
         </td>
   </tr>
   <tr>
   	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">空调数量：</div></td>
             <td width="25%"><input type="text" name="ktnum" value="" style="width:130px" /></td> 
  
   </tr>
   
   <tr class="form_label">
        <td colspan="6">
          <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:14px">
	           <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
          </div>
            
          <div id="resetBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:16px">
	           <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">重置</span>
          </div>
          
          <div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:18px">
	           <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>
          </div>
        </td>
      </tr>
   
   </table>
   
   
   </form>
  </body>
</html>
