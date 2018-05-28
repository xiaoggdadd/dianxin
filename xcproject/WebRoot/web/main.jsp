<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.noki.mobi.common.Account,com.ptac.app.version.Version" %>
<%
String version = new Version().getVersion();
		Account account = (Account)session.getAttribute("account");
        String roleid = account.getRoleId();//权限
        System.out.println("权限"+roleid);
        String path = request.getContextPath();
        String loginId = account.getAccountId();
        System.out.println("路徑"+path);
        String loginName = account.getAccountName();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>站点账户管理平台</title>

</head>
	<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
		<frame src="top.jsp" class="noprint" name="topFrame" frameborder=0 scrolling="no" noresize marginwidth=0 />
		<frameset cols="180,9,1024" name="main1" frameborder="no" border="0" framespacing="0">
			<frame src="left.jsp" class="noprint" name="left" frameborder="0" noresize scrolling="auto"  marginwidth=0 />
			<frame src="control.htm" class="noprint" name="control" frameborder="0" scrolling="no" noresize  marginwidth=0 />
			<frame src="./jizhan/sitemanage.jsp" name="main" frameborder="0" noresize scrolling="auto"  marginwidth=0 />
		</frameset>
	</frameset>
</html>
