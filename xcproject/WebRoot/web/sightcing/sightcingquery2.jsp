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
String stationtype=request.getParameter("code");
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
    function modifyad(station,name){
    	//alert(station+name);
    	var b=path+"/web/sightcing/sightcingquery3.jsp?station="+station+"&name="+name;			
			 var a = " <a href="+b+" target='treeMap1' id='tmpTree'></a>";
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

     <tr height ="20" class= "relativeTag " >
     	 <td width="10%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">站点类型</div></td>      	
         <td width="10%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">标杆名称</div></td>     
         <td width="10%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">日均耗电</div></td>                           
         <td width="10%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">采集点数</div></td>
         <td width="10%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">站点总数</div></td>
         <td width="10%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">G2</div></td>     
         <td width="10%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">G3</div></td>     
         <td width="10%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">地域类型</div></td>     
         <td width="10%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">OLTG</div></td>                 
         <td width="10%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" style="color:black;font-weight:bold">空调数量</div></td>     
     </tr>
       <%
        
        Sightcing bean = new Sightcing();
       	ArrayList fylist = new ArrayList();
       	fylist = bean.getright(loginId,stationtype);
		String station="",name ="",haodian="",caiji="",fou="",g2="",g3="",region="",olt="",air_condition="",zdlx="";
		
		int linenum=0; 
		double df=0.0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
             linenum++;
		     //num为序号，不同页中序号是连续的
		    station = (String)fylist.get(k+fylist.indexOf("STATIONTYPEID"));
		    station = station != null ? station : "";
		    
		    zdlx = (String)fylist.get(k+fylist.indexOf("ZDLX"));
		    zdlx = zdlx != null ? zdlx : "";
		     
		    name = (String)fylist.get(k+fylist.indexOf("NAME"));
		    name = name != null ? name : "";
		    	
		    g2 = (String)fylist.get(k+fylist.indexOf("G2"));
		    g2 = g2 != null ? g2 : "";
		    
		    g3 = (String)fylist.get(k+fylist.indexOf("G3"));
		    g3 = g3 != null ? g3 : "";
		    
		    caiji = (String)fylist.get(k+fylist.indexOf("CAIJI"));	
		    caiji = caiji != null ? caiji : "";
		    
		    fou = (String)fylist.get(k+fylist.indexOf("FOU"));	
		    fou = fou != null ? fou : "";
		    	    
		    region = (String)fylist.get(k+fylist.indexOf("REGION"));	
		    region = region != null ? region : "";
		    
		    
		    air_condition = (String)fylist.get(k+fylist.indexOf("AIR_CONDITION"));	
		    air_condition = air_condition != null ? air_condition : "";
		    
		    olt = (String)fylist.get(k+fylist.indexOf("OLT"));	
		    olt = olt != null ? olt : "";
		    
		    air_condition = (String)fylist.get(k+fylist.indexOf("AIR_CONDITION"));	
		    air_condition = air_condition != null ? air_condition : "";
		 
		  
		    
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
       			<div align="center" class="form_label"><%=zdlx%></div>
       		</td>
       		<td>
       			<div align="center" class="form_label"><a href="javascript:modifyad('<%=station%>','<%=name %>')" ><%=name%></a></div>
       		</td>
       		<td>
       			<div align="right" class="form_label"><%=haodian%></div>
       		</td>
       		<td>
       			<div align="right" class="form_label"><a href="javascript:dfinfo1('<%=name%>','<%=station %>')" ><%=caiji%></a></div>
       		</td>
       		<td>
       			<div align="right" class="form_label"><%=fou%></div>
       		</td>
       		<td>
       			<div align="center" class="form_label"><%=g2%></div>
       		</td>
       		<td>
       			<div align="center" class="form_label"><%=g3%></div>
       		</td>
       		<td>
       			<div align="center" class="form_label"><%=region%></div>
       		</td>
       		<td>
       			<div align="center" class="form_label"><%=olt%></div>
       		</td>
       		<td>
       			<div align="right" class="form_label"><%=air_condition%></div>
       		</td>
       			
           
       </tr>
       <%}%>
				<% }%>
				
  	 </table> 										
							
</form>
</body>
</html>
<script>
function dfinfo1(name,station){
    var url=path+"/web/sightcing/sightcingquery5.jsp?name="+name+"&station="+station;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1000px;dialogHeight:500px;status:auto;scroll:auto');	
}
</script>
