<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.noki.jtreport.servlet.FilesDownload"%>
<%@ page import="java.text.SimpleDateFormat,java.util.Date"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String accountmonth = request.getParameter("accountmonth")!=null?request.getParameter("accountmonth"):"";
String agcode = request.getParameter("agcode")!=null?request.getParameter("agcode"):"";

String color = null;
String sheng = (String)session.getAttribute("accountSheng");
String roleId = (String)session.getAttribute("accountRole");
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
   
    
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    
    String rgsh=request.getParameter("rgsh");//站点审核通过   首页传的值
    String rgsh2=request.getParameter("caiji");/// 采集站点
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
      curpage=Integer.parseInt(s_curpage);
      String permissionRights="";
      
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style type="text/css">
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	
}
.btt{ bgcolor:#888888;font-weight:bold;}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px

		}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}  
 </style>
    <base href="<%=basePath%>">
  </head>
  
  <body>
    <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">

             <tr>
		          <td width="2%"  bgcolor="#DDDDDD"><div align="center" class="btt" >序号</div></td>
                  <td width="19%" bgcolor="#DDDDDD" ><div align="center" class="btt">上传的报表</div></td>
                 
            </tr>
              
       <%
       if(accountmonth!=null&&accountmonth!=""){
		 FilesDownload load=new FilesDownload();
       	 ArrayList fylist = new ArrayList();
       	 fylist= load.getPageData(agcode,accountmonth);
       	 allpage=load.getAllPage();
		 String agname="";
		 Date datee=null;
		 int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

       			//num为序号，不同页中序号是连续的
       			agname = (String) fylist.get(k + fylist.indexOf("FILENAME"));
       			
       			if (intnum % 2 == 0) {
       				color = "#DDDDDD";

       			} else {
       				color = "#FFFFFF";
       			}
       %>
     <tr bgcolor="<%=color%>">
       		<td >
       			<div align="center" ><%=intnum++%></div>
       		</td>
       		
       		<td >
       			<div align="left"  ><%=agname%></div>
       		</td>
       		
       </tr>
      <%}	}}%>
  	 </table> 
  </body>
</html>
