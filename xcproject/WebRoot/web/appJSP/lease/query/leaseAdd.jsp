<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@page
	import="com.noki.query.basequery.javabean.StationPointQueryBean,com.noki.mobi.common.CommonBean"%>
<%
	String path = request.getContextPath();
	String sheng = (String) session.getAttribute("accountSheng");
	Account account = (Account) session.getAttribute("account");
	String dfmc = (String) request.getParameter("dfmc");
	String agcode1 = "";
	if (request.getParameter("shi") == null) {
		ArrayList shilist = new ArrayList();
		CommonBean commBean = new CommonBean();
		shilist = commBean.getAgcode(sheng, account.getAccountId());
		if (shilist != null) {
			int scount = ((Integer) shilist.get(0)).intValue();
			agcode1 = (String) shilist.get(scount + shilist.indexOf("AGCODE"));
		}
	}
	if (dfmc != null && !"".equals(dfmc)) {
	} else {
		dfmc = "";
	}
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : agcode1;
	String xiaoqu = request.getParameter("xiaoqu") != null ? request
			.getParameter("xiaoqu") : "0";
	String zdlx = request.getParameter("stationtype") != null ? request
			.getParameter("stationtype") : "0";
	String stationname = request.getParameter("stationname") != null ? request
			.getParameter("stationname") : "0";		
	String loginId = account.getAccountId();
	String roleId = account.getRoleId();
	String accountid = account.getAccountName();

%>
<html>
	<head>
		<title></title>
		<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}

.form {
	height: 19px;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height: 23px
}

.selected_font {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
}

.STYLE6 {
	color: #FFFFFF;
}

.memo {
	border: 1px #C8E1FB solid
}

.style7 {
	font-weight: bold
}

#id1 {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform.Microsoft.Gradient (
		GradientType = 0, 
		StartColorStr = #ffffff,
		EndColorStr = #D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js"></script>
<script language="javascript">
var loginId = '<%=loginId%>';
var path = '<%=path%>';
function changeMC() {
	var shi = document.form1.shi.value;
	var stationname = document.form1.stationname.value;
	if (0 == shi) {
		alert("城市不能为空");
	} else if (stationname=="0"){
		alert("请选择站点名称！");
	}else {
		document.form1.action = path+ "/servlet/LeaseBill?action=zdMessage&loginId="+ loginId;
		document.form1.submit();
	}
}

$(function() {
	$("#chaxun").click(function() {		
		exportCheck();		
	});
});

function exportCheck() {
	var loginId = '<%=loginId%>';
	var path = '<%=path%>';
	var shi = document.form1.shi.value;
	if (0 == shi) {
		alert("城市不能为空");
	} else {
		document.form1.action = path + "/servlet/LeaseBill?loginId="+loginId+"&action=zdName";
		document.form1.submit();		
	}
}
</script>

	</head>
	<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean"></jsp:useBean>
	<jsp:useBean id="Bean" scope="page" class="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean"></jsp:useBean>
	<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
	<LINK href="../../../../images/css.css" type="text/css" rel=stylesheet>

	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST" id="form">
			<div style="width:700px;height:50px">
				<img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
				<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">添加租赁合同单</span>
			</div>
			<table border="0" align="center" width="97%">
				<tr>
					<td>
						<fieldset>
							<legend><img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">基本信息</font></b></legend>
	 						<table width="97%" align="right" CellSpacing="1">
				     			<tr>
									<td class="form_label">
										<table width="100%" border="0" cellspacing="1"cellpadding="1">
											<tr class="form_label">
												<td class="form_label">城市：</td>
												<td>
													<select name="shi" class="selected_font"
														onchange="changeCity()">
														<option value="0">请选择</option>
														<%
															ArrayList shilist = new ArrayList();
															shilist = commBean.getAgcode(sheng, account.getAccountId());
															if (shilist != null) {
																String agcode = "", agname = "";
																int scount = ((Integer) shilist.get(0)).intValue();
																for (int i = scount; i < shilist.size() - 1; i += scount) {
																	agcode = (String) shilist
																			.get(i + shilist.indexOf("AGCODE"));
																	agname = (String) shilist
																			.get(i + shilist.indexOf("AGNAME"));
														%>
														<option value="<%=agcode%>"><%=agname%></option>
														<%}}%>
													</select>
													<span class="style1">&nbsp;*</span>
												</td>
												<td class="form_label">区县：</td>
												<td>
													<select name="xian" class="selected_font"
														onchange="changeCountry()">
														<option value="0">请选择</option>
														<%
															ArrayList xianlist = new ArrayList();
															xianlist = commBean.getAgcode(shi, account.getAccountId());
															if (xianlist != null) {
																String agcode = "", agname = "";
																int scount = ((Integer) xianlist.get(0)).intValue();
																for (int i = scount; i < xianlist.size() - 1; i += scount) {
																	agcode = (String) xianlist.get(i
																			+ xianlist.indexOf("AGCODE"));
																	agname = (String) xianlist.get(i
																			+ xianlist.indexOf("AGNAME"));
														%>
														<option value="<%=agcode%>"><%=agname%></option>
														<%}}%>
													</select>
												</td>
												<td class="form_label">乡镇：</td>
												<td>
													<select name="xiaoqu" class="selected_font"
														onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
														<option value="0">
															请选择
														</option>
														<%
															ArrayList xiaoqulist = new ArrayList();
															xiaoqulist = commBean.getAgcode(xian, account.getAccountId());
															if (xiaoqulist != null) {
																String agcode = "", agname = "";
																int scount = ((Integer) xiaoqulist.get(0)).intValue();
																for (int i = scount; i < xiaoqulist.size() - 1; i += scount) {
																	agcode = (String) xiaoqulist.get(i
																			+ xiaoqulist.indexOf("AGCODE"));
																	agname = (String) xiaoqulist.get(i
																			+ xiaoqulist.indexOf("AGNAME"));
														%>
														<option value="<%=agcode%>"><%=agname%></option>
														<%}}%>
													</select>
												</td>
												<td class="form_label">站点类型：</td>
												<td>
													<select name="stationtype" class="selected_font" />
														<option class="form_label" value="0">
															请选择
														</option>
														<%
															ArrayList stationtype = new ArrayList();
															stationtype = ztcommon.getSelctOptions("StationType");
															if (stationtype != null) {
																String code = "", name = "";
																int cscount = ((Integer) stationtype.get(0)).intValue();
																for (int i = cscount; i < stationtype.size() - 1; i += cscount) {
																	code = (String) stationtype.get(i
																			+ stationtype.indexOf("CODE"));
																	name = (String) stationtype.get(i
																			+ stationtype.indexOf("NAME"));
														%>
														<option value="<%=code%>"><%=name%></option>
														<%}}%>
													</select>
												</td>
											</tr>
											<tr>
												<td colspan="8">
													<table width="100%" border="0">
														<tr class="selected_font">
															<td height="5%" bgcolor="#DDDDDD" width="15%"
																colspan="8">
																<div align="left">
																	<font size="2">站点名称：</font>
																</div>
															</td>
															<td>
																<div style="position: relative;">
																	<span id="sp01"
																		style="margin-left: 230px; width: 18px; overflow: hidden;">
																		<select name="stationname"
																			style="width: 248px; margin-left: -230px;"
																			style="width:300" onchange="changeMC()">
																			<option value="0">
																				请选择
																			</option>
																			<%
																				ArrayList stationnamea = new ArrayList();
																				stationnamea = (ArrayList) request.getAttribute("zdname");

																				String scode = "";
																				if (stationnamea != null) {
																					String name = "";
																					String dbid = "";
																					String zdcode = "";
																					if (stationnamea.size() == 0) {
																			%>
																			<option value="0">输入有误</option>
																			<%
																				} else {
																						int cscount = ((Integer) stationnamea.get(0)).intValue();
																						for (int i = cscount; i < stationnamea.size() - 1; i += cscount) {
																							scode = (String) stationnamea.get(i
																									+ stationnamea.indexOf("DBID"));
																							name = (String) stationnamea.get(i
																									+ stationnamea.indexOf("JZNAME"));
																							dbid = (String) stationnamea.get(i
																									+ stationnamea.indexOf("DBNAME"));
																							zdcode = (String) stationnamea.get(i
																									+ stationnamea.indexOf("ZDCODE"));
																							name = name + "--" + dbid;
																			%>
																			<option value="<%=scode%>,<%=zdcode%>"><%=name%></option>
																			<%}}}%>
																		</select> </span>
																	<%if (dfmc != null && !"".equals(dfmc) && stationnamea.size() != 0
																				&& stationnamea.size() < 3) {
																	%>
																	<input type="text" name="dfmc"
																		style="width: 230px; position: absolute; left: 0px;"
																		value="<%=(String) stationnamea.get(2 + stationnamea.indexOf("JZNAME"))%>" />
																	<%} else {%>
																	<input type="text" name="dfmc"
																		style="width: 230px; position: absolute; left: 0px;"
																		value="" />
																	<%}%>
																</div>
															</td>
															<td>
																<div id="chaxun" style="position: relative; width: 60px; height: 23px">
																	<img alt="" src="/energy/images/chaxun.png"
																		width="100%" height="100%" />
																	<span
																		style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
																</div>
															</td>
															<td width="220px"></td>
															<td width="300px"></td>
															<td width="120px"></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
										</tr>
									</table>
									<br />
								</td>
							</tr>
						</table>
			<input type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
		</form>
	</body>
</html>

<script>

function changeCity() {
	var sid = document.form1.shi.value;
	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=shi&pid=" + sid, "shi");
	}
}

function changeCountry() {
	var sid = document.form1.xian.value;
	if (sid == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=xian&pid=" + sid, "xian");
	}
}

document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
document.form1.stationtype.value = '<%=zdlx%>';
document.form1.stationname.value = '<%=stationname%>';

</script>