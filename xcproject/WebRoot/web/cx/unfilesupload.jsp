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
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
   
    
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
  String accountmonth=request.getParameter("accountmonth")!=null?request.getParameter("accountmonth"):""; 
   
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
      curpage=Integer.parseInt(s_curpage);
      String permissionRights="";
      int intnum=0;
      
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



 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>

 <script>

var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();

var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChsny.oBtnTodayTitle = "确定";
oCalendarChsny.oBtnCancelTitle = "取消";
oCalendarChsny.Init();
</script>
 <script type="text/javascript">
$(function(){
	$("#query").click(function(){
		queryDegree();
	});
});	
function queryDegree(){
	var path='<%=path%>';
	var accountmonth=document.form1.accountmonth.value;
	//alert(accountmonth);
	var b=path+"/web/cx/unfilesuploadleft.jsp?accountmonth="+accountmonth;			
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			//alert(a+"aaaa");
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
			//alert(a+"aaaa");
	
}
 </script>

    <base href="<%=basePath%>">
  </head>
  
  <body>
  	<form action="" name="form1" method="POST">
    <table style="width: 100%;" class="form_label" border='0'>
        <tr>
			<td colspan="4" width="50">
				<div style="width:700px;height:50px">
			    <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">报表查询</span>	</div>
		    </td>
		 </tr>
		 <tr>
		   <td height="20" colspan="4"><div id="parent" style="display:inline">
               <div style="width:50px;display:inline;"><hr></div><font size="2">过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
           </div></td>
		</tr>
		<tr><td>
			<table class="form_label">
				<tr>
					<td>报账时间：</td><td><input type="text" name="accountmonth" id='accountmonth' value="<%if(null!=request.getParameter("accountmonth"))out.print(request.getParameter("accountmonth"));%>" onFocus="getDatenyString(this,oCalendarChsny)"  class="selected_font"/></td>	
					<td >
					       <div id="query" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
							<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
							<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
                          </div>
					</td>
				</tr>
			</table>
		</td></tr>
		 <tr>
		    <td height="23" colspan="4">
				<div id="parent" style="display: inline"><div style="width: 50px; display: inline;"><hr></div>
				<font size="2">&nbsp;信息列表&nbsp;</font>
				<div style="width: 300px; display: inline;"><hr></div></div>
			</td>
		</tr>
		<tr><td height="300px">
		    <iframe  background-position-x ="1%" name="treeMap" height='320px' width="40%" frameborder="0" src="<%=path %>/web/cx/unfilesuploadleft.jsp"></iframe> 	
  	        <iframe name="treeNodeInfo" width="28%" height='320px'  frameborder="0" src="<%=path %>/web/cx/weishangchuan.jsp"></iframe>    	
		  	<iframe name="treeNode" width="28%" height='320px'  frameborder="0" src="<%=path %>/web/cx/shangchuan.jsp"></iframe>    	
		
		</td></tr>	
	</table>
</form>		
  </body>
</html>

