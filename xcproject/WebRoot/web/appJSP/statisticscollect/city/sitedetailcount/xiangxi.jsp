<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.ZhanDianForm,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String color = "";
	int intnum = request.getAttribute("intnum") != null ? (Integer) request.getAttribute("intnum"): 0;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>报账单审核统计明细</title>
		<style type="text/css">
.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}

.style1 {
	color: red;
	font-weight: bold;
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}

.bttcn {
	color: BLACK;
	font-weight: bold;
}

.selected_font {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
}
</style>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>

<link type="text/css" rel="Stylesheet" href="<%=path%>/web/appCSS/pageCss/page.css" />
<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js">
</script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
  
</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body onload = "onloadtime()">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan=1 width="500" background="<%=path%>/images/btt.bmp" height=50>
						<span style="color: black; font-weight: bold;">&nbsp;&nbsp;&nbsp;报账单审核统计详细查询&nbsp;&nbsp;</span>
					</td>
					<td width='380'>
					</td>
				</tr>
			</table>
			<table height="23">
				<tr>
					<td colspan="4"> <div style="width:50px;display:inline;"><hr></div><font size='2'>信息列表</font><div style="width:300px;display:inline;"><hr></div></td>
				</tr>
			</table>
			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="100%" height="80%" border="0" cellspacing="1" cellpadding="1"
					bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账单号
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账电量
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账电费
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								审核人
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								审核时间
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								通过理由
							</div>
						</td>
					</tr>
					
					<c:forEach items="${list}" var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
							<td>
								<div align="center">
									${status.count}
								</div>
							</td>
							<td>
								<div align="center">
									${list.id}
								</div>
							</td>
							<td>
								<div align="center">
									${list.zdname}
								</div>
							</td>
							<td>
								<div align="center">
									${list.blhdl}
								</div>
							</td>
							<td>
								<div align="center">
									${list.actualpay}
								</div>
							</td>
							<td>
								<div align="center">
									${list.shr}
								</div>
							</td>
							<td>
								<div align="center">
									${list.shtime}
								</div>
							</td>
							<td>
								<div align="center">
									${list.passly}
								</div>
							</td>
						</tr>
					</c:forEach>

					<%
						if (intnum == 0) {
							for (int i = 0; i < 17; i++) {
								if (i % 2 == 0) {
									color = "#FFFFFF";
								} else {
									color = "#DDDDDD";
								}
					%>

					<tr bgcolor="<%=color%>">
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<%
						}
						} else if (!(intnum > 16)) {
							int n = ((intnum - 1) % 16);
							int j = 0;
							for (j = n; j < 16; j++) {
								if (j % 2 == 0)
									color = "#DDDDDD";
								else
									color = "#FFFFFF";
					%>
					<tr bgcolor="<%=color%>">
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<%
						}
						}
					%>

				</table>
			</div>
		</form>
	</body>
</html>


