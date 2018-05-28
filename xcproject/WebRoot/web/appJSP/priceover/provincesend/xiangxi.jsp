<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<% 
		int intnum = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;//条数 ,查出数据的条数，用于颜色设置
		String color="";
%>
<head>
<title>省派单详细信息
</title>
<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
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
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
</head>
<jsp:useBean id="bean" scope="page" class="com.noki.electricfees.javabean.ElectricFeesBean">
</jsp:useBean>
<body  class="body">
	<table>
		<tr><td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
	</table>  	
  <table width="100%" height="400px"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
         	<tr height = "23" class="relativeTag ">
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
  			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td> 
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次电表读数</div></td>
  			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次电表读数</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">倍率</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表用电量</div></td>  
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账金额</div></td>           
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账电量</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td> 
        </tr>
     <c:forEach items="${list}"  var="list" varStatus="status">
		<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       		<td>
				<div align="left">${list.zdname}</div>
			</td>
       		<td>
       			<div align="left" >${list.lasttime}</div>
       		</td>   		
        	<td>
       			<div align="right" >${list.thistime}</div>
       		</td>
       		<td>
				<div align="right">${list.lastdegree}</div>
			</td>
			<td>
				<div align="right">${list.thisdegree}</div>
			</td>
			<td>
       			<div align="center" >${list.beilv}</div>
       		</td>
       		  	<td>
       			<div align="center" >${list.dbydl}</div>
       		</td>  
       		<td>
       			<div align="center" >${list.bzdf}</div>
       		</td>
       		  	<td>
       			<div align="center" >${list.bzdl}</div>
       		</td>   	
       		<td>
       			<div align="right" >${list.accountmonth}</div>
       		</td>   
    	</tr>
    </c:forEach>
       
        <% if (intnum==0){
	         for(int i=0;i<5;i++){
	          if(i%2==0){
				    color="#FFFFFF";
	          }else{
				    color="#DDDDDD";
				}
	         %>
	
	        <tr bgcolor="<%=color%>">
	             <td>&nbsp;</td>  
	             <td>&nbsp;</td> 
	             <td>&nbsp;</td>  
	             <td>&nbsp;</td> 
	             <td>&nbsp;</td> 
	             <td>&nbsp;</td> 
	             <td>&nbsp;</td>  
	             <td>&nbsp;</td> 
	             <td>&nbsp;</td>                   
	             <td>&nbsp;</td>
	        </tr>
	    <% }}%>
  </table>
</body>
</html>




