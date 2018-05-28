<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@page import="com.noki.electricfees.javabean.*"%>
<%@page import="com.noki.fybzgl.bean.*"%>
<%@page import="com.noki.function.*"%>
<%@ page import="java.sql.ResultSet,java.util.List"%>
<%
	String bz = request.getParameter("bz") != null ? request
			.getParameter("bz") : "0";
	String bzw = request.getParameter("bz") != null ? request
			.getParameter("bz") : "0";
	String path = request.getContextPath();
	String loginName = (String) session.getAttribute("loginName");
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();

	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String permissionRights = "";
	
	String list1 = request.getParameter("list");

	
   


	
	
	
    
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

	</head>
	<style>
.style1 {
	color: red;
	font-weight: bold;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height: 20px
}

.selected_font {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 120%;
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}

;
.memo {
	border: 1px #C8E1FB solid
}

.memo {
	border: 1px #888888 solid
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

#id1 {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

.bttcn {
	color: black;
	font-weight: bold;
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}

.form_la {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}
</style>
	<script type="text/javascript"
		src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
	<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
	<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
	<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
	<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
	<body>
		<form action="" name="form1" method="POST">

			<%
				if (bzw != null && !bzw.equals("") && !bzw.equals("0")) {
					
			%>
			<%
				}
			%>
			
			<img alt="" src="<%=path%>/web/Map/zdsx.png" width="90%">
		</form>
		
	</body>
	
<script type="text/javascript">
	window.parent.CloseDiv();
</script>



</html>
