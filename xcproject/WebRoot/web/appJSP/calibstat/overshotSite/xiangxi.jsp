<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<style>   
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: right;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}

.bttcn{color:BLACK;font-weight:bold;}

.form1{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;
   height:19px;
   width:180px;
}

 </style>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" >	
	<form action="" name="form1" method="POST">
  	<table height="23">     
     <tr><td height="5"  colspan="4">
      <div id="parent" style="display:inline">
        <div style="width:50px;display:inline;"><hr></div>
        <font size="2">&nbsp;超标站点查询详细信息&nbsp;</font>
        <div style="width:300px;display:inline;"><hr></div>
      </div></td>
     </tr>
  </table>
  <table border="0" align="center" width="97%">
  	<tr>
  		<td width="83%">  		
			<fieldset>
				<legend><b><font size="2">站点信息</font></b></legend>
	 			<table width="100%" align="left" CellSpacing="0" class="form_label">
				     <tr>    
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>站点名称：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${bean.zdname}" class="form1" /></td>
						<td bgcolor="#DDDDDD" class="bttcn"><div align=center>站点ID：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${bean.zdid}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>城市：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${bean.city}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>区县：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${bean.xian}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>乡镇：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${bean.xiaoqu}" class="form1" /></td>
				     </tr>
				     <tr>
				     	<td bgcolor="#DDDDDD" class="bttcn"><div align=center>站点属性：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${bean.property}" class="form1" /></td>
						<td bgcolor="#DDDDDD" class="bttcn"><div align=center>直流：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${bean.zlfh}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>交流：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${bean.jlfh}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>额定耗电量：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${bean.edhdl}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>生产标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${bean.scb}" class="form1" /></td>
				     </tr>
				      <tr>
				     	<td bgcolor="#DDDDDD" class="bttcn"><div align=center>站点类型：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${bean.zdlx}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>房屋类型：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${bean.fwlx}" class="form1" /></td>
				     </tr>
				</table>
			</fieldset>
			<br/>
			<fieldset>
				<legend><b><font size="2">省定标信息</font></b></legend>
	 			<table width="100%" align="center" CellSpacing="0" class="form_label">
				     <tr>    
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>1月省定标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${sdb.sdb1}" class="form1" /></td>
						<td bgcolor="#DDDDDD" class="bttcn"><div align=center>2月省定标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${sdb.sdb2}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>3月省定标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${sdb.sdb3}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>4月省定标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${sdb.sdb4}" class="form1" /></td>
				     </tr>
				     <tr>
				     	<td bgcolor="#DDDDDD" class="bttcn"><div align=center>5月省定标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${sdb.sdb5}" class="form1" /></td>
						<td bgcolor="#DDDDDD" class="bttcn"><div align=center>6月省定标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${sdb.sdb6}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>7月省定标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${sdb.sdb7}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>8月省定标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${sdb.sdb8}" class="form1" /></td>
				     </tr>
				     <tr>
				     	<td bgcolor="#DDDDDD" class="bttcn"><div align=center>9月省定标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${sdb.sdb9}" class="form1" /></td>
						<td bgcolor="#DDDDDD" class="bttcn"><div align=center>10月省定标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${sdb.sdb10}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>11月省定标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${sdb.sdb11}" class="form1" /></td>
				        <td bgcolor="#DDDDDD" class="bttcn"><div align=center>12月省定标：</div></td>
				        <td width="10%"><input type="text" readonly="readonly" value="${sdb.sdb12}" class="form1" /></td>
				     </tr>
				</table>
			</fieldset>
			<br/>
			<fieldset>
				<legend><b><font size="2">其他信息</font></b></legend>
	 			<table width="100%" align="center" CellSpacing="0" class="form_label">
	 			 <tr height = "23" >
                      <td bgcolor="#DDDDDD" class="bttcn" ><div align="center">上次抄表时间</div></td>
                      <td bgcolor="#DDDDDD" class="bttcn" ><div align="center">本次抄表时间</div></td>
                      <td bgcolor="#DDDDDD" class="bttcn" ><div align="center">上次电表读数</div></td>
                      <td bgcolor="#DDDDDD" class="bttcn" ><div align="center">本次电表读数</div></td>
                      <td bgcolor="#DDDDDD" class="bttcn" ><div align="center">报账电量</div></td>
                      <td bgcolor="#DDDDDD" class="bttcn" ><div align="center">倍率</div></td>
                      <td bgcolor="#DDDDDD" class="bttcn"><div align="center">报账月份</div></td>
                  </tr>
                  <c:forEach items="${other}"  var="ot" varStatus="status">
					<tr height = "23" class="relativeTag">
      					<td><div align="center" >${ot.lasttime}</div></td> 
			           	<td><div align="center" >${ot.thistime}</div></td>
			       		<td><div align="center" >${ot.lastdegree}</div></td>
			       		<td><div align="center" >${ot.thisdegree}</div></td>
			       		<td><div align="center" >${ot.bzdl}</div></td>          
			       		<td><div align="center" >${ot.beilv}</div></td> 
			           	<td><div align="center" >${ot.accountmonth}</div></td>
   					</tr>
   					</c:forEach>
	 			</table>
	 		</fieldset>
  		</td>
	</tr>
</table>
</form>
</body>
</html>

