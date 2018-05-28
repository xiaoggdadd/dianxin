<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%
String path = request.getContextPath();
String zdid=request.getParameter("zdid");
String last=request.getParameter("last");
System.out.println(zdid+"eeeeeeeeeeee"+last);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String startmonth = request.getParameter("startmonth")!=null?request.getParameter("startmonth"):"";
String endmonth = request.getParameter("endmonth")!=null?request.getParameter("endmonth"):"";
System.out.println(startmonth+"时间");
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
curpage=Integer.parseInt(s_curpage);
String permissionRights="";
String roleId = (String)session.getAttribute("accountRole");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'haodianliangleft.jsp' starting page</title>   
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
bttcn{color:white;font-weight:bold;}
 .form    {width:130px}
 .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style> 
  </head> 
  <body class="body" style="overflow-x:hidden;">
   <table>
		<tr class='form_label'>
			<td colspan="11"><br/><div id="parent" style="display:inline"><div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div></div>	</td>
		</tr>
	</table>
	<div style="width:100%;height:430px;overflow-x:auto;overflow-y:auto;border:1px">
	
  <table border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
   
     <tr height = "20" class="relativeTag">
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">电表编号</div></td>
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">电表名称</div></td>
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">耗电量</div></td>        
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">时间</div></td>
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">用电设备</div></td>
         
     </tr>
      <%
     
    
      System.out.println(zdid+"id====");
      haodianliangBean bean = new haodianliangBean();
     	ArrayList fylist = new ArrayList();

     	fylist = bean.getCollectQuery1(last,zdid);
     	allpage=bean.getAllPage();
     	System.out.println("ALLLLLLL"+allpage);
		String datavalue = "",getdatetime = "",stname="",dbname="",ydsb="";
		int intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		double df=0.0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
           linenum++;
		     //num为序号，不同页中序号是连续的		    	
		    datavalue = (String)fylist.get(k+fylist.indexOf("DATAVALUE"));	
		    datavalue = datavalue != null ? datavalue : "";
		    
		    getdatetime = (String)fylist.get(k+fylist.indexOf("GETDATETIME"));	
		    getdatetime = getdatetime != null ? getdatetime : "";
		     
		    stname = (String)fylist.get(k+fylist.indexOf("STNAME"));	
		    stname = stname != null ? stname : "";
		    
		    dbname = (String)fylist.get(k+fylist.indexOf("DBNAME"));	
		    dbname = dbname != null ? dbname : "";
		    
		    ydsb = (String)fylist.get(k+fylist.indexOf("YDSB"));	
		    ydsb = ydsb != null ? ydsb : "";
		    
			String color=null;
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
        intnum++;

     %>
        <tr bgcolor="<%=color%>">
         
            <td><div align="center" style="font-size:14px"><%=stname %></div></td> 
            <td><div align="center" style="font-size:14px"><%=dbname %></div></td> 
            <td><div align="center" style="font-size:14px"><%=datavalue %></div></td>
            <td><div align="center" style="font-size:14px"><%=getdatetime %></div></td>   
            <td><div align="center" style="font-size:14px"><%=ydsb %></div></td>      
                   
        </tr>   
        <% }
        }%>     
      
   </table>
   </div>
  </body>
</html>


