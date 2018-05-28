<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.SimpleDateFormat" %>
<%@ page import="com.noki.util.CTime" %>
<%@ page import="com.noki.mobi.cx.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.sql.ResultSet,java.util.Calendar"%>

<%	   
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId=account.getAccountId();
        String roleId = account.getRoleId();
        String loginName = (String)session.getAttribute("loginName");
        
  
%>

<html>
<head>
<base target=_self> 
<title>
选择站点
</title>
<style>
.relativeTag   {   
    z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);      
}; 
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
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.bttcn{color:white;font-weight:bold;}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
<%
String startmonth = request.getParameter("startmonth")!=null?request.getParameter("startmonth"):"";
String endmonth = request.getParameter("endmonth")!=null?request.getParameter("endmonth"):"";
String zdcode=request.getParameter("jzcode");
%>
	<form action="" name="form1" id="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
    <td valign="top">														                    	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">
 <% 

 System.out.println("****"+startmonth+endmonth+zdcode);
 String whereStr = "";
 if(null != startmonth  &&!startmonth.equals("") && !startmonth.equals("0")){
		whereStr=whereStr+" and TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm')<='"+startmonth+"'";
	}
	if(null != endmonth  && !endmonth.equals("") && !endmonth.equals("0")){
		whereStr=whereStr+" and TO_CHAR(A.ENDMONTH,'yyyy-mm')<='"+endmonth+"'";
	}if(null != zdcode  && !zdcode.equals("") && !zdcode.equals("0")){
		whereStr=whereStr+" and Z.ZDCODE='"+zdcode+"'";
 %>
               <tr class="form_label ">                            
                   <td nowrap width="8%" height="23" bgcolor="#DDDDDD" ><div align="center"  style="color:black;font-weight:bold;" >电表编号</div></td>
                   <td nowrap width="8%" height="23" bgcolor="#DDDDDD" ><div align="center"  style="color:black;font-weight:bold;" >上次抄表时间</div></td>
                   <td nowrap width="8%" height="23" bgcolor="#DDDDDD" ><div align="center"  style="color:black;font-weight:bold;" >本次抄表时间</div></td>
                   <td nowrap width="5%" height="23" bgcolor="#DDDDDD" ><div align="center"  style="color:black;font-weight:bold;" >上次读数</div></td>
                   <td nowrap width="5%" height="23" bgcolor="#DDDDDD" ><div align="center"   style="color:black;font-weight:bold;">本次读数</div></td>
                   <td nowrap width="5%" height="23" bgcolor="#DDDDDD" ><div align="center"   style="color:black;font-weight:bold;">耗电量调整</div></td>
                   <td nowrap width="5%" height="23" bgcolor="#DDDDDD" ><div align="center"   style="color:black;font-weight:bold;">总耗电量</div></td>
                   <td nowrap width="8%" height="23" bgcolor="#DDDDDD" ><div align="center"   style="color:black;font-weight:bold;">开始月份</div></td>
                   <td nowrap width="8%" height="23" bgcolor="#DDDDDD" ><div align="center"   style="color:black;font-weight:bold;">结束月份</div></td>
                   <td nowrap width="5%" height="23" bgcolor="#DDDDDD" ><div align="center"   style="color:black;font-weight:bold;">费用调整</div></td> 
                   <td nowrap width="5%" height="23" bgcolor="#DDDDDD" ><div align="center"   style="color:black;font-weight:bold;">总费用</div></td>
                   <td nowrap width="8%" height="23" bgcolor="#DDDDDD" ><div align="center"   style="color:black;font-weight:bold;">报账月份</div></td>
              </tr>
       <%  
      
		
       System.out.println("whereStr:"+whereStr);
       SitePaymentStatistics bean = new SitePaymentStatistics();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getlist(whereStr);
       
		String dbid="",lasttime="",thistime="",lastdegree="",thisdegree="",floatdegree="",blhdl="",start="",end="",floatpay="",actualpay="",accountmonth="";
		int intnum=0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
				dbid = (String)fylist.get(k+fylist.indexOf("DBID"));
				dbid = dbid != null ? dbid : "";
			    
				lasttime = (String)fylist.get(k+fylist.indexOf("LASTDATETIME"));
				lasttime = lasttime != null ? lasttime : "";
			    
				thistime = (String)fylist.get(k+fylist.indexOf("THISDATETIME"));
				thistime = thistime != null ? thistime : "";
			    
				lastdegree = (String)fylist.get(k+fylist.indexOf("LASTDEGREE"));
				lastdegree = lastdegree != null ? lastdegree : "0";
			    
				thisdegree = (String)fylist.get(k+fylist.indexOf("THISDEGREE"));
				thisdegree = thisdegree != null ? thisdegree : "0";
			    
				floatdegree = (String)fylist.get(k+fylist.indexOf("FLOATDEGREE"));
				floatdegree = floatdegree != null ? floatdegree : "0";
			    
				blhdl = (String)fylist.get(k+fylist.indexOf("BLHDL"));
				blhdl = blhdl != null ? blhdl : "0";
				
				start = (String)fylist.get(k+fylist.indexOf("STARTMONTH"));
				start = start != null ? start : "";
				
				
				end = (String)fylist.get(k+fylist.indexOf("ENDMONTH"));
				end = end != null ? end : "";
				
				floatpay = (String)fylist.get(k+fylist.indexOf("FLOATPAY"));
				floatpay = floatpay != null ? floatpay : "0";
				
				actualpay = (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));
				actualpay = actualpay != null ? actualpay : "0";
				
				accountmonth = (String)fylist.get(k+fylist.indexOf("ACCOUNTMONTH"));
				accountmonth = accountmonth != null ? accountmonth : "";
				
				
				if(floatpay==null||floatpay.equals(""))floatpay="0";
				if(lastdegree==null||lastdegree.equals(""))lastdegree="0";
				if(thisdegree==null||thisdegree.equals(""))thisdegree="0";
				if(floatdegree==null||floatdegree.equals(""))floatdegree="0";
				if(blhdl==null||blhdl.equals(""))blhdl="0";
				if(actualpay==null||actualpay.equals(""))actualpay="0";
				System.out.println("=="+floatpay);
				DecimalFormat mat=new DecimalFormat("0.0");
    		      DecimalFormat mat1=new DecimalFormat("0.00");
    		      floatdegree=mat.format(Double.parseDouble(floatdegree));  
    		      lastdegree=mat.format(Double.parseDouble(lastdegree));  
    		      thisdegree=mat.format(Double.parseDouble(thisdegree));  
    		      blhdl=mat.format(Double.parseDouble(blhdl));  
    		      
    		      floatpay=mat1.format(Double.parseDouble(floatpay));
    		      actualpay=mat1.format(Double.parseDouble(actualpay));
    		      
		     //num为序号，不同页中序号是连续的
	       
			String color=null;

			if(intnum++%2==0){
			    color="#FFFFFF" ;

			 }else{
			    color="#DDDDDD";
			}

       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		
       		<td>
       			<div align="center" ><%=dbid%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lasttime%></div>
       		</td>
       		<td>
       			<div align="center" ><%=thistime%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lastdegree%></div>
       		</td>
       		<td>
       			<div align="center" ><%=thisdegree%></div>
       		</td>
       		<td>
       			<div align="center" ><%=floatdegree%></div>
       		</td>
       		<td>
       			<div align="center" ><%=blhdl%></div>
       		</td>
       		<td>
       			<div align="center" ><%=start%></div>
       		</td>
       		<td>
       			<div align="center" ><%=end%></div>
       		</td>
       		<td>
       			<div align="center" ><%=floatpay%></div>
       		</td>
       		<td>
       			<div align="center" ><%=actualpay%></div>
       		</td>
       		<td>
       			<div align="center" ><%=accountmonth%></div>
       		</td>
       		
       </tr>
       <%} %>						
       <%}}%>
  	 </table>   	 

    </td>
  </tr>
</table>
</form>
</body>
</html>
