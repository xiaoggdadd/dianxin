<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.noki.electricfees.javabean.*" %>
<%@ page import="java.sql.*"  %>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dbid=request.getParameter("dianbiaoid");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'zhandianfentan_iframe2.jsp' starting page</title>
    
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
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:20px

		}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
};
 .memo {border: 1px #C8E1FB solid}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}

.bttcn{color:black;font-weight:bold;}

.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.form_la{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}
</style>  
  <body>
  <%if(dbid != null && !dbid.equals("") && !dbid.equals("0")){ %>
   <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de"> 
   <hr/> 
    <tr class="relativeTag" >
             <td  bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn">监测点ID</div></td>
             <td  bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn">名称</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">规格</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">所属专业</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">所属网元</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">会计科目</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">全成本科目</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">专业明细</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">电表占比</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">标称耗电</div></td>
             
             
         </tr>
    <% 
     
     Zhandianfentanmx bean=new Zhandianfentanmx();
     EquipmentmanageBean jiancebean=new EquipmentmanageBean();  
     ArrayList list=new ArrayList();
     list=bean.getData(dbid);
     String id="",name="",guige="",sszy="",sswy="",kjkm="",qcbkm="",zymx="",dbzb="",bchd="";
      if(list!=null)
		{
			int fycount = ((Integer)list.get(0)).intValue();
			for( int k = fycount;k<list.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
			id = (String)list.get(k+list.indexOf("SHEIEBANID"));
			name=(String)list.get(k+list.indexOf("MINGCHENG"));
			guige=(String)list.get(k+list.indexOf("GUIGE"));
			sszy=(String)list.get(k+list.indexOf("SHUOSHUZHUANYE"));
			sswy=(String)list.get(k+list.indexOf("SHUOSHUWANGYUAN"));
			kjkm=(String)list.get(k+list.indexOf("KJKMCODE"));
			qcbkm=(String)list.get(k+list.indexOf("QCBCODE"));
			zymx=(String)list.get(k+list.indexOf("ZYMXCODE"));
			dbzb=(String)list.get(k+list.indexOf("DBILI"));
			bchd=(String)list.get(k+list.indexOf("BCCD"));
			kjkm=jiancebean.getKJKM1(kjkm);
            qcbkm=jiancebean.getQCB1(qcbkm);
            zymx=jiancebean.SelPermissionCconfiguration("ZYMX",zymx);
            sszy=jiancebean.SelPermissionCconfiguration("SSZY",sszy);
			
     
  %>
     <tr bgcolor="#FFFFFF" class="form_label">
				<td><%=id %></td>
				<td><%=name%></td>
				<td><%=guige%></td>
				<td><%=sszy%></td>
				<td><%=sswy%></td>
				<td><%=kjkm%></td>
				<td><%=qcbkm%></td>
				<td><%=zymx%></td>
				<td><%=dbzb%>%</td>
				<td><%=bchd%></td>
				

			</tr>
  
			<%}} %>
	</table>
	<%} %>
  </body>
</html>
