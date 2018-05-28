<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.Sightcing" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>

<%
String path = request.getContextPath();
Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
String loginName = account.getAccountName();  
String shengId = (String)session.getAttribute("accountSheng");
String permissionRights="";
String roleId = (String)session.getAttribute("accountRole");
int intnum=0;
String station=request.getParameter("station");
String name=request.getParameter("name");
%>

<html>
<head>
<style>
.form_label{
	font-family: 宋体;
	font-size: 12px;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}; 
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0301");
System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<body  class="body" >
	
	<form action="" name="form1" method="POST">
  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#666666">

     <tr height = "20" class= "relativeTag " >
         <td width="15%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">站点编号</div></td>     
         <td width="15%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">站点名称</div></td>                           
         <td width="15%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">所在地区</div></td>
         <td width="15%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">站点属性</div></td>
         <td width="15%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">站点类型</div></td>     
         <td width="15%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">集团报表类型</div></td>     
     </tr>
       <%
        
        Sightcing bean = new Sightcing();
       	ArrayList fylist = new ArrayList();
       	fylist = bean.gettan(loginId,station,name);
		String stationname = "",szdq="",jzcode="",property="",stationtype="",jztype="";
		int linenum=0; 
		double df=0.0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
             linenum++;
		     //num为序号，不同页中序号是连续的
		    stationname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    stationname = stationname != null ? stationname : "";
		    	
		    szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		    szdq = szdq != null ? szdq : "";
		    
		    jzcode = (String)fylist.get(k+fylist.indexOf("JZCODE"));
		    jzcode = jzcode != null ? jzcode : "";
		    
		    property= (String)fylist.get(k+fylist.indexOf("PROPERTY"));
		    property = property != null ? property : "";
		    
		    stationtype = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
		    stationtype = stationtype != null ? stationtype : "";
		    
		    jztype = (String)fylist.get(k+fylist.indexOf("JZTYPE"));
		    jztype = jztype != null ? jztype : "";
		    
			String color=null;
			
            
			if(intnum%2==0){
			    color="#FFFFFF";

			 }else{
			    color="#DDDDDD" ;
			}
          intnum++;

       %>
       <tr bgcolor="<%=color%>">
             <td>
       			<div align="center" class="form_label"><%=jzcode%></div>
       		</td>
       		<td>
       			<div align="left" class="form_label"><%=stationname%></div>
       		</td>
       		<td>
       			<div align="center" class="form_label"><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center" class="form_label"><%=property %></div> 
       		</td>
       		<td>
       			<div align="center" class="form_label"><%=stationtype%></div>
       		</td>
       		<td>
       			<div align="center" class="form_label"><%=jztype%></div>
       		</td>
       			
           
       </tr>
       <%}%>
				<% }%>
				
  	 </table> 										
							
</form>
</body>
</html>
