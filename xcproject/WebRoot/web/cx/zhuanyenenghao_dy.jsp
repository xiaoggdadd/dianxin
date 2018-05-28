<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.sql.*,java.util.*,java.io.*"%>
<%@ page language="java" import="java.util.Date"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@ page import="com.noki.mobi.cx.ZhuanYeNengHao"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String path = request.getContextPath();
	String beginTime = request.getParameter("beginTime") != null ? request
			.getParameter("beginTime")
			: "";
	String endTime = request.getParameter("endTime") != null ? request
			.getParameter("endTime") : "";

	String sheng = request.getParameter("sheng") != null ? request
			.getParameter("sheng") : "0";
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : "0";
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";
	String xiaoqu = request.getParameter("xiaoqu") != null ? request
			.getParameter("xiaoqu") : "0";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>专业能耗类型统计</title>
		<script language="JavaScript">

function ss(v) {
	try {
		printWB.ExecWB(v, 1);
	} catch (e) {
	}
}
function MouseDownToResize(obj) {
	setTableLayoutToFixed();
	obj.mouseDownX = event.clientX;
	obj.pareneTdW = obj.parentElement.offsetWidth;
	obj.pareneTableW = theObjTable.offsetWidth;
	obj.setCapture();
}
function MouseMoveToResize(obj) {
	if (!obj.mouseDownX)
		return false;
	var newWidth = obj.pareneTdW * 1 + event.clientX * 1 - obj.mouseDownX;
	if (newWidth > 0) {
		obj.parentElement.style.width = newWidth;
		theObjTable.style.width = obj.pareneTableW * 1 + event.clientX * 1
				- obj.mouseDownX;
	}
}
function MouseUpToResize(obj) {
	obj.releaseCapture();
	obj.mouseDownX = 0;
}
function setTableLayoutToFixed() {
	if (theObjTable.style.tableLayout == 'fixed')
		return;
	var headerTr = theObjTable.rows[0];
	for ( var i = 0; i < headerTr.cells.length; i++) {
		headerTr.cells[i].styleOffsetWidth = headerTr.cells[i].offsetWidth;
	}

	for ( var i = 0; i < headerTr.cells.length; i++) {
		headerTr.cells[i].style.width = headerTr.cells[i].styleOffsetWidth;
	}
	theObjTable.style.tableLayout = 'fixed';
}
</script>
		<style type="text/css" media=print>
.noprint {
	display: none
}
</style>


		<style type="text/css">
<!--
.STYLE1 {
	font-size: 24px;
	font-weight: bold;
}

.STYLE5 {
	font-size: 14px
}

.STYLE6 {
	font-size: 16px;
	font-weight: bold;
}
-->
</style>

		<style type="text/css">
<!--
.STYLE1 {
	font-size: x-large;
	font-weight: bold;
}

.STYLE5 {
	font-size: 14px
}

.STYLE6 {
	font-size: 16px;
	font-weight: bold;
}

.bg table {
	font-size: 12px;
	color: #000000;
}

.bg td {
	font-size: 12px;
	color: #000000;
	text-align: left;
	line-height: 15px;
	height: 20px;
}

.bg td.tit {
	background-color: #e2e2e2;
	color: #000;
	height: 17px;
	text-align: center;
	line-height: 15px;
}

.bg td.num {
	background-color: #e2e2e2;
	color: #000;
	text-align: right;
	line-height: 15px;
	width: 30px;
	height: 22px;
}

.resizeDivClass {
	text-align: right;
	width: 1px;
	margin: 0px 0 0px 0;
	background: #fff;
	border: 0px;
	float: right;
	cursor: e-resize;
}
-->
</style>
	</head>

	<body>
		<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0
			id="printWB" width=0></OBJECT>

		<div style="text-align: center" class="noprint">
			<input onClick="document.all.printWB.ExecWB(6,6)" type="button"
				value="直接打印">
			<input type="button" value="打印预览" onClick="ss(7)">
			<input type="button" value="打印设置" onClick="ss(8)">
			<input type="button" value="关闭窗口" onClick="javascript:window.close()">
		</div>

		<table width="650" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="72%" colspan="4" align="center">
					<span class="STYLE1">专业能耗类型统计</span>
				</td>
			</tr>
		</table>

		<table width="650" border="1" cellpadding="0" cellspacing="0"
			align="center" bordercolor="#000000"
			style="border-collapse: collapse" id="theObjTable">
			<tr>
				<td align="center" nowrap="nowrap" class="kuangtitle">
					<span class="resizeDivClass noprint"
						onMouseDown="MouseDownToResize(this);"
						onMouseMove="MouseMoveToResize(this);"
						onMouseUp="MouseUpToResize(this);"><img
							src="images/box1.gif" width="3" height="18">
					</span><strong class="STYLE6">序号</strong>
				</td>

				<td align="center" nowrap="nowrap" class="kuangtitle">
					<span class="resizeDivClass noprint"
						onMouseDown="MouseDownToResize(this);"
						onMouseMove="MouseMoveToResize(this);"
						onMouseUp="MouseUpToResize(this);"><img
							src="images/box1.gif" width="3" height="18">
					</span><strong class="STYLE6">集团报表类型名称</strong>
				</td>
				<td align="center" nowrap="nowrap" class="kuangtitle">
					<span class="resizeDivClass noprint"
						onMouseDown="MouseDownToResize(this);"
						onMouseMove="MouseMoveToResize(this);"
						onMouseUp="MouseUpToResize(this);"><img
							src="images/box1.gif" width="3" height="18">
					</span><strong class="STYLE6">站点数目</strong>
				</td>

				<td align="center" nowrap="nowrap" class="kuangtitle">
					<span class="resizeDivClass noprint"
						onMouseDown="MouseDownToResize(this);"
						onMouseMove="MouseMoveToResize(this);"
						onMouseUp="MouseUpToResize(this);"><img
							src="images/box1.gif" width="3" height="18">
					</span><strong class="STYLE6">合计用电量</strong>
				</td>
				<td align="center" nowrap="nowrap" class="kuangtitle">
					<span class="resizeDivClass noprint"
						onMouseDown="MouseDownToResize(this);"
						onMouseMove="MouseMoveToResize(this);"
						onMouseUp="MouseUpToResize(this);"><img
							src="images/box1.gif" width="3" height="18">
					</span><strong class="STYLE6">合计交费数额</strong>
				</td>

			</tr>
			<%
				ZhuanYeNengHao bean = new ZhuanYeNengHao();
				ArrayList fylist = new ArrayList();
				fylist = bean.getPageData(beginTime, endTime, shi, xian, xiaoqu);

				String jzname = "", zdcount = "", ydcount = "", jfcount = "";
				int intnum = 1;
				if (fylist != null) {
					int fycount = ((Integer) fylist.get(0)).intValue();
					for (int k = fycount; k < fylist.size() - 1; k += fycount) {

						//num为序号，不同页中序号是连续的
						jzname = (String) fylist.get(k + fylist.indexOf("NAME"));
						zdcount = (String) fylist
								.get(k + fylist.indexOf("ZDCOUNT"));
						ydcount = (String) fylist
								.get(k + fylist.indexOf("YDCOUNT"));
						jfcount = (String) fylist
								.get(k + fylist.indexOf("JFCOUNT"));
						if (jfcount == null)
							jfcount = "0";
						if (ydcount == null)
							ydcount = "0";
						String color = "#FFFFFF";
			%>
			<tr bgcolor="<%=color%>" id="<%=intnum%>">
				<td>
					<div align="center"><%=intnum++%></div>
				</td>

				<td>
					<div align="center"><%=jzname%></div>
				</td>
				<td>
					<div align="center"><%=zdcount%></div>
				</td>
				<td>
					<div align="center"><%=ydcount%></div>
				</td>
				<td>
					<div align="center"><%=jfcount%></div>
				</td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</body>
</html>

