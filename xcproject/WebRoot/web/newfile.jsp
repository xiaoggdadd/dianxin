<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%
String path = request.getContextPath();
String permissionRights="";
String roleId = (String)session.getAttribute("accountRole");
String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  <style type="text/css">
  .form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			
		}
  
  
  </style>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
  <%
	permissionRights = commBean.getPermissionRight(roleId, "0101");

	System.out.println(">>>>>>>>>>>>>>..." + permissionRights);
%>
   <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
  <script language="javascript">
  var path='<%=path%>';
  function xiazai(id){
		    	document.form1.action=path+"/servlet/FilesDownloadServlet?id="+id;
				document.form1.submit()
		    }
   $(function(){
			$("#cancelBtn").click(function(){
			window.history.back();
			});
			});
  </script>
  <body>
   <center>
    <fieldset id="regist" >
    	<legend><font style="color:black;font-weight:bold;font-size=16;">帮助资料下载</font></legend>
    	<form action="" name="form1" method="post">
			<table width="65%" border="0" cellspacing="0" cellpadding="0">
			<%
       SiteManage beans = new SiteManage();
       	 ArrayList fylist = new ArrayList();
        fylist = beans.getFiles();
        
        String id="",bt="",time="",name="";
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
				id = (String) fylist.get(k + fylist.indexOf("ID"));
       			bt = (String) fylist.get(k + fylist.indexOf("FILENAME"));
       			time = (String) fylist.get(k + fylist.indexOf("UPLOADDATE"));
       			name = (String) fylist.get(k + fylist.indexOf("NAME"));
       %>
       <tr class="form_label">
       		<td height="10">
       			<%=bt%>
       		</td>
       		<td height="10">
       			<%=name%>
       		</td>
       		<td height="10">
       			<%=time%>
       		</td>
       		<td height="10">
       			<div align="center" ><a href="#" onclick="xiazai('<%=id%>')">下载</a></div>
       		</td>
       </tr>
      <tr height="10"><td></td></tr>
       <%
       	}
       %>
        <%
       	}
       %>
			</table>
		</form> 
		</fieldset>
		</center>
  </body>
</html>
