<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.noki.mobi.common.Account,com.ptac.app.version.Version" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean" %>
<%

String version = new Version().getVersion();


Account account = (Account)session.getAttribute("account");
        String accountId = account.getName();
        String path = request.getContextPath();
String rolename = account.getRoleName();
String loginId=account.getAccountId();
haodianliangBean bean = new haodianliangBean();
String flag="1";
String number=account.getNumber();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>页头</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.style1 {
	color: #FFFFFF;
	font-size: 9pt;
}
a:active {color:#0000ff; text-decoration:none;font-size:12px;}
a:hover {color:#0000ff; text-decoration: none;font-size:12px;}
a:link {color: #1E90FF; text-decoration: none;font-size:12px;}
a:visited {color: #0000ff; text-decoration: none;font-size:12px;}
-->
</style>
<script type="text/javascript" src="javascript/jquery-1.4.2.js"></script>
    <script language="javascript" type="text/javascript">
    	$(function(){
		$("#bangzhu").click(function(){
			Warn2();
		});
		$("#caozuo").click(function(){
			Warn();
		});
		$("#guanli").click(function(){
			Warn1();
		});
	});
		var Window_PopOnlineHelpWindow = null
		function Pop_HelpWindow(Whichfun,HelpURL)
		{

			Whichfun=document.all("funid").value;
			HelpURL=document.all("helpurl").value;
			//alert(HelpURL)
			var Pop_HelpWindow_feature = "directories=yes,height=500,width=760,left=200,top=20,status=yes,toolbar=yes,menubar=yes,scrollbars=yes,resizable=yes"
			var Pop_HelpWindow_URL = "onlinehelp/" + HelpURL
			if(HelpURL != "")
			{
			   Window_PopOnlineHelpWindow =  window.open(Pop_HelpWindow_URL,"PopOnlineHelpWindow", Pop_HelpWindow_feature);
			    Window_PopOnlineHelpWindow.focus()
			}
		}

    function doQuit(){
	    parent.location = "<%=path%>/index.jsp";
    }

</script>
<script language="javascript">
	var path = '<%=path%>';
function logout()
  {
	  if(confirm("确定要退出系统么？")){
			
		 	parent.location=path+"/tuichu.jsp";
	  }
	  else{
	    return false;
	  }
  }
    function Warn2(){
	self.parent.frames["main"].location=path+"/web/newfile.jsp";
}
function Warn(){
	
	self.parent.frames["main"].location=path+"/web/newright.jsp";
}
function Warn1(){
	self.parent.frames["main"].location=path+"/web/newsh.jsp";
}
    function modifypass(){
    	self.parent.frames["main"].location=path+"/web/sys/modifyPass.jsp";
    	
    }
</script>
</head>
<body>
<table  width="100%" height="100%" cellspacing="0" cellpadding="0"  border="0" style="background-color:#8B0000;">
  	<tr>
		<td align="left" height="88px" >
			<img valign="bottom" src="./tree/img/cd.gif" width="60px" /><span style="font-famliy:微软雅黑; color:#ffffff;font-size:24pt"><b>&nbsp;&nbsp;中国铁塔山东分公司（枣庄）能耗管理平台系统</b></span>		
		</td>
		<td width="590px"  align="right" valign="bottom" >
			<span style="font-famliy:微软雅黑; color:#ffffff;font-size:15px;"><img valign="bottom" src="./tree/img/menuemp.gif "/><%=accountId%>&nbsp;<strong>|</strong>&nbsp;</span>
			<span style="font-famliy: 微软雅黑;color:#ffffff;font-size:15px;">角色：<%=rolename%>&nbsp;<strong>|</strong>&nbsp;</span>
			<a href="#" onclick="modifypass()" style="font-size:15px;color:#ffffff;font-famliy:微软雅黑;">修改密码&nbsp;<strong>|</strong>&nbsp;</a>
			<a href="#" onclick="logout()" style="font-size:15px;color:#ffffff;font-famliy:微软雅黑;">退出系统&nbsp;&nbsp;&nbsp;</a>
		</td>
	</tr>
</table>
</body>
</html>