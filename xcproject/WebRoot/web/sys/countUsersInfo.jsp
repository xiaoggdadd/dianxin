<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
		int intnum=0;		
		int numcolor = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;//条数 ,查出数据的条数，用于颜色设置
		String color = "";//颜色
%>
<html>
<head>
<title>用户详细信息
</title>
<style>
 .style7 {font-weight: bold}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}

.form {width:130px}
.bttcn{color:black;font-weight:bold;}
 </style>
</head>
<jsp:useBean id="bean" scope="page" class="com.noki.electricfees.javabean.ElectricFeesBean">
</jsp:useBean>
<body  class="body">
	<table  align = "center">
		<tr><td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:150px;display:inline;"><hr></div><font size="2">&nbsp;${zwshi}地区&nbsp;${zhiwu}角色&nbsp;账户信息&nbsp;</font><div style="width:150px;display:inline;"><hr></div>
       	</div></td></tr>
	</table>  	
  <table width="1000px" height=""  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label"  align = "center">
         	<tr bgcolor="#7293b0">
	            <td width="" height="23"><div align="center" class="bttcn"><font color="#FFFFFF">序号</font></div></td>
	            <td width="" height="23"><div align="center" class="bttcn"><font color="#FFFFFF">用户名</font></div></td>
	            <td width="" height="23"><div align="center" class="bttcn"><font color="#FFFFFF">角色</font></div></td>  
	          	<td width="" height="23"><div align="center" class="bttcn"><font color="#FFFFFF">oa账户</font></div></td>
	            <td width="" height="23"><div align="center" class="bttcn"><font color="#FFFFFF">电话</font></div></td>
	            <td width="" height="23"><div align="center" class="bttcn"><font color="#FFFFFF">手机</font></div></td>
	            <td width="" height="23"><div align="center" class="bttcn"><font color="#FFFFFF">邮箱</font></div></td>  
	        </tr>
		<c:forEach items="${list}"  var="list" varStatus="status">
			<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       			<td><div align="center" >${status.count}</div></td>  
       			<td><div align="left" >&nbsp;&nbsp;&nbsp;&nbsp;${list.name}</div></td> 
       			<td><div align="center" >${list.rolename}</div></td> 
       			<td><div align="center" >${list.accountname}</div></td> 
       			<td><div align="center" >${list.tel}</div></td> 
       			<td><div align="center" >${list.mobile}</div></td> 
       			<td><div align="center" >${list.email}</div></td> 
   			</tr>
		</c:forEach>
      	<% 
						int i = 0;
						int j = 0;//f用于循环
						if (numcolor==0){
							for(i=0;i<1;i++){
          						if(i%2==0){
			    					color="#FFFFFF" ;
          						}else{
			    					color="#DDDDDD";
								}
         			%>

        			<tr bgcolor="<%=color%>">  
             			<td align="center" >无资料</td> 
             			<td align="center" >无资料</td>  
            			<td align="center" >无资料</td> 
             			<td align="center" >无资料</td> 
             			<td align="center" >无资料</td> 
             			
             			<td align="center" >无资料</td> 
             			<td align="center" >无资料</td>  
            		</tr>
        			<% 
        					}
						}
					%>
  	 			</table> <br/>
	</body>
</html>




