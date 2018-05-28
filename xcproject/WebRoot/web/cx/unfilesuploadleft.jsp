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

String color = null;

String roleId = (String)session.getAttribute("accountRole");
String accountmonth = request.getParameter("accountmonth")!=null?request.getParameter("accountmonth"):"";
 System.out.println("accountmonth"+accountmonth);  
    
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    
   
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
      curpage=Integer.parseInt(s_curpage);
      String permissionRights="";
      int intnum=1;
      
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
.bttcn{color:black;font-weight:bold;}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}  
 </style>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript">
  function ss(agcode,accountmonth){
	 var path='<%=path%>'; 
	  	var b=path+"/web/cx/weishangchuan.jsp?agcode="+agcode+"&accountmonth="+accountmonth+"&";
	  	//alert(b+"111");
			b = b.substring(0,b.length-1);
			 var a = " <a href="+b+" target='treeNodeInfo' id='tmp'></a>";
			$("#tmp").remove();
			$("body").append(a);
			$("#tmp")[0].click(); 
		var b=path+"/web/cx/shangchuan.jsp?agcode="+agcode+"&accountmonth="+accountmonth+"&";
	  //	alert(b+"111");
			b = b.substring(0,b.length-1);
			 var a = " <a href="+b+" target='treeNode' id='tmp'></a>";
			$("#tmp").remove();
			$("body").append(a);
			$("#tmp")[0].click(); 
 }
 
 </script>
    <base href="<%=basePath%>">
  </head>  
  <body>
<table border='0' width="100%" cellspacing="1" cellpadding="1" class="form_label" bgcolor="#cbd5de">			    
   <tr bgcolor="#DDDDDD" class="bttcn" align="center"><td>序号</td><td>未上传报表的公司</td><td>上传报表的个数</td><td>未上传报表的个数</td>
   </tr>

		 <%
		 if(accountmonth!=null&&accountmonth!=""){
		 FilesDownload load=new FilesDownload();
		 ArrayList fylist1 = new ArrayList();
		 fylist1 = load.getPageData1(loginId,accountmonth);
		 String agcode="",agname="",aa="";int b=0;
		 Date datee=null;
		 if(fylist1!=null)
		{
			int fycount = ((Integer)fylist1.get(0)).intValue();
			for( int k = fycount;k<fylist1.size()-1;k+=fycount){

     			//num为序号，不同页中序号是连续的
     			agname = (String) fylist1.get(k + fylist1.indexOf("AGNAME"));
     			agcode = (String) fylist1.get(k + fylist1.indexOf("AGCODE"));
     			aa = (String) fylist1.get(k + fylist1.indexOf("AA"));
     			int a=Integer.parseInt(aa);
     			    b=11-a;
     			
     			
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
     			<div align="center"  ><a href="javascript:ss('<%=agcode%>','<%=accountmonth%>')"><%=agname%></a></div>
     		</td>
     		<td >
     			<div align="center"  ><%=aa%></div>
     		</td>
     		
     		<td >
     			<div align="center"  ><%=b%></div>
     		</td>
     </tr>

     <%
     	}
     	}
     	}%>
  </table>

  </body>
</html>
