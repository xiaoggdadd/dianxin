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
System.out.println(station+"ddddd"+name);
%>

<html>
<head>
<style>
.form_label{
	text-align: left;
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
<script language="javascript">
var path = '<%=path%>';    
    function modifyad(stationtype){
    	var b=path+"/web/sightcing/sightcingquery3.jsp?stationtype="+stationtype;			
			 var a = " <a href="+b+" target='treeNodeInfo' id='tmpTree'></a>";
			// alert(a);
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
    } 
</script>
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
         <td width="25%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">年月</div></td>     
         <td width="30%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">日均耗电</div></td>                           
     </tr>
       <%
        
        Sightcing bean = new Sightcing();
       	ArrayList fylist = new ArrayList();
       	fylist = bean.getright1(loginId,station,name);
		String tian = "",du="",lastdatetime="";
		int linenum=0; 
		String ripjs="";
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
             linenum++;
		     //num为序号，不同页中序号是连续的
		    tian = (String)fylist.get(k+fylist.indexOf("TIAN"));
		    tian = tian != null ? tian : "";
		    
		    
		    du = (String)fylist.get(k+fylist.indexOf("DU"));	
		    du = du != null ? du : "";
		    
		    lastdatetime = (String)fylist.get(k+fylist.indexOf("STARTMONTH"));	
		    lastdatetime = lastdatetime != null ? lastdatetime : "";
		    
		    Double tianshu=Double.parseDouble(tian);
		    Double dushu=Double.parseDouble(du);
		    Double ripj=dushu/tianshu;
		    DecimalFormat pay=new DecimalFormat("0.00");
            ripjs = pay.format(ripj);
            
			String color=null;
			
            
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
          intnum++;

       %>
       <tr bgcolor="<%=color%>">
       		
       		<td>
       			<div align="right" class="form_label"><%=lastdatetime%></div>
       		</td>
       		<td>
       			<div align="right" class="form_label"><%=ripjs%></div>
       		</td>
       		
       			
           
       </tr>
       <%}%>
				<% }%>
				
  	 </table> 										
							
</form>
</body>
</html>
