<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@page import="com.noki.electricfees.javabean.*"%>
<%@page import="com.noki.tongjichaxu.javabean.fybzCountBean"%>
<%@page import="com.noki.tongjichaxu.javabean.fybzbean"%>
<%@page import="com.noki.function.*"%>
<%
	String bz = request.getParameter("bz") != null ? request
			.getParameter("bz") : "0";
	String bzw = request.getParameter("bzw") != null ? request
			.getParameter("bzw") : "";//标志位
	
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
			<table width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr class="relativeTag">
					<td width="2%" bgcolor="" class="form_label">
						
					</td>
					<td bgcolor="" class="form_label">
						
					</td>
					
				</tr>
				<%
					
					
					
				

					fybzCountBean bean = new fybzCountBean();
					List<fybzbean> fylist = null;
					String color=null;
					if (bzw != null && !bzw.equals("") && !bzw.equals("0")) {
						
								int intnum = xh = pagesize * (curpage - 1) + 1;
								if (intnum % 2 == 0) {
									color = "#DDDDDD";

								} else {
									color = "#FFFFFF";
								}
				%>
				<tr bgcolor="<%=color%>" class="form_label">
					<td>
						<%=intnum++%>
					</td>

					


				</tr>
				<%
					}
						

					
				%>


			</table>
		</form>
	</body>
<script type="text/javascript">
	window.parent.CloseDiv();
</script>


</html>
