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
<script language="javascript">
var path = '<%=path%>';    
    function modifyad(code){
    	
    	var b=path+"/web/sightcing/sightcingquery2.jsp?code="+code;			
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
         <td width="25%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">站点类型</div></td>     
         <td width="30%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">日均耗电</div></td>                           
         <td width="20%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">采集点数</div></td>
         <td width="25%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">总站点数</div></td>     
     </tr>
       <%
        
        Sightcing bean = new Sightcing();
       	ArrayList fylist = new ArrayList();
       	fylist = bean.getleft(loginId);
		String code="",stationtype = "",haodian="",caiji="",fou="";
		int linenum=0; 
		double df=0.0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
             linenum++;
		     //num为序号，不同页中序号是连续的
		     
		    code = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
		    code = code != null ? code : "";
		    
		    stationtype = (String)fylist.get(k+fylist.indexOf("NAME"));
		    stationtype = stationtype != null ? stationtype : "";
		    		    
		    caiji = (String)fylist.get(k+fylist.indexOf("CAIJI"));	
		    caiji = caiji != null ? caiji : "";
		    
		    fou = (String)fylist.get(k+fylist.indexOf("FOU"));	
		    fou = fou != null ? fou : "";
		    
		  
		    
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
       			<div align="left" class="form_label"><a href="javascript:modifyad('<%=code%>')" ><%=stationtype%></div>
       		</td>
       		<td>
       			<div align="right" class="form_label"><%=haodian%></div>
       		</td>
       		<td>
       			<div align="right" class="form_label"><%=caiji%></div>
       		</td>
       		<td>
       			<div align="right" class="form_label"><%=fou%></div>
       		</td>
       			
           
       </tr>
       <%}%>
	   <%}%>
				
  	 </table> 										
							
</form>
</body>
</html>
