<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Account account = (Account) session.getAttribute("account");//账户
	String personnal=account.getAccountName();//审核人员
	String accountid = account.getAccountId();
%>
<html>
<head>
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
.form1{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;
   height:19px;
   width:180px;
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
 <link type="text/css" rel="Stylesheet"
			href="<%=path%>/web/appCSS/pageCss/page.css" />
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
<script language="javascript">
var path = '<%=path%>';

function baocun(){
	//var pid = document.form1.pid.value;
	var zlfhold = document.form1.zlfhold.value;//直流负荷
	var jlfhold = document.form1.jlfhold.value;//交流负荷
	var edhdlold = document.form1.edhdlold.value;//本地标
	var scbold = document.form1.scbold.value;//生产标
	var zlfhnew = document.form1.zlfhnew.value;//核实后直流
	var jlfhnew = document.form1.jlfhnew.value;//核实后交流
	var edhdlnew = document.form1.edhdlnew.value;//核实后本地标
	var scbnew = document.form1.scbnew.value;//核实后生产标

	if(isNaN(zlfhold)){
		alert("直流负荷必须为数字!");
	}else if(isNaN(jlfhold)){
		alert("交流负荷必须为数字!");
	}else if(isNaN(edhdlold)){
		alert("本地标必须为数字!");
	}else if(isNaN(scbold)){
		alert("生产标必须为数字!");
	}else if(isNaN(zlfhnew)){
		alert("核实后直流必须为数字!");
	}else if(isNaN(jlfhnew)){
		alert("核实后交流必须为数字!");
	}else if(isNaN(edhdlnew)){
		alert("核实后本地标必须为数字!");
	}else if(isNaN(scbnew)){
		alert("核实后生产标必须为数字!");
	}else{
		if(confirm("您将要添加信息！确认信息正确！")){
			document.form1.action=path+"/servlet/CountyVerifyServlet?command=commit&peid="+peid;
			document.form1.submit();
		}	
	}
}

$(function() {
	$("#baocun").click(function() {
		baocun();
	});
});
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" >	
	<form action="" name="form1" method="POST">
  	<table>
		<tr><td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;基础数据&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
	</table> 
 	<table width="100%" height="400px"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
   		<tr height="23" class="relativeTag ">
	 		<td bgcolor="#DDDDDD" class="bttcn"><div align="center">站点名称</div></td>
            <td bgcolor="#DDDDDD" class="bttcn" ><div align="center">上次抄表时间</div></td>
          	<td bgcolor="#DDDDDD" class="bttcn" ><div align="center">本次抄表时间</div></td>
          	<td bgcolor="#DDDDDD" class="bttcn" ><div align="center">上次电表读数</div></td>
         	<td bgcolor="#DDDDDD" class="bttcn" ><div align="center">本次电表读数</div></td>
        	<td bgcolor="#DDDDDD" class="bttcn" ><div align="center">倍率</div></td>
           	<td bgcolor="#DDDDDD" class="bttcn" ><div align="center">电表用电量</div></td>
           	<td bgcolor="#DDDDDD" class="bttcn" ><div align="center">报账金额</div></td>
         	<td bgcolor="#DDDDDD" class="bttcn" ><div align="center">报账电量</div></td>
           	<td bgcolor="#DDDDDD" class="bttcn"><div align="center">报账月份</div></td>
           	<td bgcolor="#DDDDDD" class="bttcn" ><div align="center">市级主任审核状态</div></td>
      	</tr>
       	<c:forEach items="${list}"  var="ot" varStatus="status">
		<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
			<td><div align="center" >${ot.zdname}</div></td> 
      		<td><div align="center" >${ot.lasttime}</div></td> 
			<td><div align="center" >${ot.thistime}</div></td>
			<td><div align="center" >${ot.lastdegree}</div></td>
			<td><div align="center" >${ot.thisdegree}</div></td>
			<td><div align="center" >${ot.beilv}</div></td> 
			<td><div align="center" >${ot.dbydl}</div></td> 
			<td><div align="center" >${ot.bzdf}</div></td> 
			<td><div align="center" >${ot.bzdl}</div></td>          
			<td><div align="center" >${ot.accountmonth}</div></td>
			<td><div align="center" >${ot.citystate}</div></td>
   		</tr>
   		</c:forEach>
 	</table>
 	<table>
		<tr><td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;核实信息&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
	</table> 
 	<table width="100%" align="left" CellSpacing="0" class="form_label">
		<tr>    
			<td bgcolor="#DDDDDD" class="bttcn"><div align=center>直流负荷：</div></td>
			<td width="10%"><input type="text" name="zlfhold" value="" class="form1" /></td>
			<td bgcolor="#DDDDDD" class="bttcn"><div align=center>交流负荷：</div></td>
			<td width="10%"><input type="text" name="jlfhold" value="" class="form1" /></td>
			<td bgcolor="#DDDDDD" class="bttcn"><div align=center>本地标：</div></td>
			<td width="10%"><input type="text" name="edhdlold" value="" class="form1" /></td>
			<td bgcolor="#DDDDDD" class="bttcn"><div align=center>生产标：</div></td>
			<td width="10%"><input type="text" name="scbold" value="" class="form1" /></td>
		</tr>
		<tr></tr>
		<tr>
			<td bgcolor="#DDDDDD" class="bttcn"><div align=center>核实后直流负荷：</div></td>
			<td width="10%"><input type="text" name="zlfhnew" value="" class="form1" /></td>
			<td bgcolor="#DDDDDD" class="bttcn"><div align=center>核实后交流负荷：</div></td>
			<td width="10%"><input type="text" name="jlfhnew" value="" class="form1" /></td>
			<td bgcolor="#DDDDDD" class="bttcn"><div align=center>核实后本地标：</div></td>
			<td width="10%"><input type="text" name="edhdlnew" value="" class="form1" /></td>
			<td bgcolor="#DDDDDD" class="bttcn"><div align=center>核实后生产标：</div></td>
			<td width="10%"><input type="text" name="scbnew" value="" class="form1" /></td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><div id="baocun" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 400px">
					<img alt="" src="<%=request.getContextPath()%>/images/shangchuan.png" width="100%" height="100%" />
					<span style="font-size: 12px; position: absolute; left: 26px; top: 3px; color: white">提交</span>
				</div>
			</td>
		</tr>
	</table>
</form>
</body>
</html>

