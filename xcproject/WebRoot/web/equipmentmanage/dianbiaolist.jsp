<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.DianBiaoBean,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%
	String loginName = (String) session.getAttribute("loginName");
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String roleId = account.getRoleId();

	String sheng = (String) session.getAttribute("accountSheng");
	String agcode1 = "";
	if (request.getParameter("shi") == null) {
		ArrayList shilist = new ArrayList();
		CommonBean commBean = new CommonBean();
		shilist = commBean.getAgcode(sheng, account.getAccountId());
		if (shilist != null) {
			int scount = ((Integer) shilist.get(0)).intValue();
			agcode1 = (String) shilist.get(scount
					+ shilist.indexOf("AGCODE"));
		}
	}
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : agcode1;
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";
	String xiaoqu = request.getParameter("xiaoqu") != null ? request
			.getParameter("xiaoqu") : "0";
	//System.out.println("logManage.jsp>>"+beginTime);
	String sdbid = request.getParameter("sdbid") != null ? request
			.getParameter("sdbid") : "";
	String zdmc = request.getParameter("zdmc") != null ? request
			.getParameter("zdmc") : "";

	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1, intnum = 0;
	;
	curpage = Integer.parseInt(s_curpage);

	String color = null;
%>

<html>
	<head>
		<title>监测点电表选择</title>
		<style>
.btn {
	BORDER-RIGHT: #7b9ebd 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7b9ebd 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #cecfde );
	BORDER-LEFT: #7b9ebd 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7b9ebd 1px solid
}

.btn1_mouseout {
	BORDER-RIGHT: #7EBF4F 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7EBF4F 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #B3D997 );
	BORDER-LEFT: #7EBF4F 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7EBF4F 1px solid
}

.btn1_mouseover {
	BORDER-RIGHT: #7EBF4F 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7EBF4F 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #CAE4B6 );
	BORDER-LEFT: #7EBF4F 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7EBF4F 1px solid
}

.btn2 {
	padding: 2 4 0 4;
	font-size: 12px;
	height: 23;
	background-color: #ece9d8;
	border-width: 1;
}

.btn3_mouseout {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #C3DAF5 );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

.btn3_mouseover {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

.btn3_mousedown {
	BORDER-RIGHT: #FFE400 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #FFE400 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #C3DAF5 );
	BORDER-LEFT: #FFE400 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #FFE400 1px solid
}

.btn3_mouseup {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #C3DAF5 );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

.btn_2k3 {
	BORDER-RIGHT: #002D96 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #002D96 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #FFFFFF, EndColorStr =   #9DBCEA );
	BORDER-LEFT: #002D96 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #002D96 1px solid
}

.style1 {
	color: #FF9900;
	font-weight: bold;
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

.memo {
	border: 1px #888888 solid
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

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}

.bttcn {
	color: black;
	font-weight: bold;
}

#id1 {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}
</style>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/tx.js">
</script>
		<script>

var oCalendarEn = new PopupCalendar("oCalendarEn"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();

var oCalendarChs = new PopupCalendar("oCalendarChs"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChs.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChs.oBtnTodayTitle = "今天";
oCalendarChs.oBtnCancelTitle = "取消";
oCalendarChs.Init();
</script>
		<script language="javascript">
var path = '<%=path%>';
function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = ""
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

	} else {
		trObject.style.display = "none"
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
	}
}

function chaxun() {
	document.form1.action = path + "/web/equipmentmanage/dianbiaolist.jsp";
	document.form1.submit();
}
function queren() {
	var m = document.getElementsByName('itemSelected');//选择的电表数
	var n = document.getElementsByName('zdname');
	var ss = document.getElementsByName('area');

	var l = m.length;
	var dianBiaoId = "";
	var zhandianName = "";
	var area = "";
	var num = 0;
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			num = num + 1;
			dianBiaoId = m[i].value;
			zhandianName = n[i].value;
			area = ss[i].value;
		}
	}
	if (num != 1) {
		alert("请选择一个电表");
		return;
	}
	// alert(dianBiaoId);
	var bz = "2";
	var de = "1";
	window.opener.location.href = path
			+ "/web/equipmentmanage/add.jsp?dianBiaoId=" + dianBiaoId + "&bz="
			+ bz + "&de=" + de + "&zhandianName=" + zhandianName + "&area="
			+ area;
	// window.opener.document.form1.submit();
	window.close();
}
$(function() {
	$("#query").click(function() {
		chaxun();
	});
	$("#querenBtn").click(function() {
		queren();
	});
});
</script>
		<script type="text/javascript">
//=点击展开关闭效果=

function openShutManager(oSourceObj, oTargetObj, shutAble, oOpenTip, oShutTip) {
	var sourceObj = typeof oSourceObj == "string" ? document
			.getElementById(oSourceObj) : oSourceObj;
	var targetObj = typeof oTargetObj == "string" ? document
			.getElementById(oTargetObj) : oTargetObj;
	var openTip = oOpenTip || "";
	var shutTip = oShutTip || "";
	if (targetObj.style.display != "none") {
		if (shutAble)
			return;
		targetObj.style.display = "none";
		if (openTip && shutTip) {
			sourceObj.innerHTML = shutTip;
		}
	} else {
		targetObj.style.display = "block";
		if (openTip && shutTip) {
			sourceObj.innerHTML = openTip;
		}
	}
}
</script>
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<body class="body">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
				<tr>

					<td colspan="4" width="50">
						<div style="width: 700px; height: 50px">

							<span
								style="font-size: 14px; font-weight: bold; position: absolute; left: 25px; top: 25px; color: black">电表列表</span>
						</div>
					</td>
				</tr>
				<tr>
					<td height="20" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;过滤条件&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="1200px">
						<table>
							<tr class="form_label">
								<td>
									城市：
								</td>
								<td>
									<select name="shi" id="shi" class="selected_font" style="box-sizing: border-box; width: 130px;"
										onchange="changeShi()">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList shilist = new ArrayList();
											shilist = commBean.getAgcode(sheng, shi, loginName);
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
										<%
											}
											}
										%>
									</select>
								</td>
								<td>
									区县：
								</td>
								<td>
									<select name="xian" id="xian" class="selected_font" style="box-sizing: border-box; width: 130px;"
										onchange="changeXian()">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList xianlist = new ArrayList();
											shilist = commBean.getAgcode(shi, xian, loginName);
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
										<%
											}
											}
										%>
									</select>
								</td>
								<td>
									乡镇：
								</td>
								<td>
									<select name="xiaoqu" id="xiaoqu" class="selected_font">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList xiaoqulist = new ArrayList();
											xiaoqulist = commBean.getAgcode(xian, xian, loginName);
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
										<%
											}
											}
										%>
									</select>

								</td>
								<td>
									<p>
										<font size="2">
											<div title="您可以进行详细的条件筛选" id="query1"
												onclick="openShutManager(this,'box3',false)"
												style="position: relative; width: 17px; height: 17px; cursor: pointer; top: 10PX">
												<img alt=""
													src="<%=request.getContextPath()%>/images/gaojichaxun.gif"
													width="100%" height="100%" />
												<span
													style="font-size: 12px; position: absolute; left: 2px; top: 0px; color: white">&nbsp;&nbsp;&nbsp;</span>
											</div> </font>
									</p>
								</td>
								<td>
									<div id="query"
										style="position: relative; width: 59px; height: 23px; right: -180px; cursor: pointer; TOP: 0PX">
										<img alt=""
											src="<%=request.getContextPath()%>/images/chaxun.png"
											width="100%" height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
									</div>
								</td>



							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div style="width: 90%;">
							<p id="box3" style="display: none">
								<table>
									<tr class="form_label">
										<td>
											站点名称：
										</td>
										<td>
											<input type="text" name="zdmc" value="<%=zdmc%>"
												class="selected_font" />
										</td>
										<td>
											电表ID：
										</td>
										<td>
											<input type="text" name="sdbid" value="<%=sdbid%>"
												class="selected_font" />
										</td>
									</tr>
								</table>
							</p>
						</div>
					</td>
				</tr>
			</table>
			<table height="23">
				<tr>
					<td height="5" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;信息列表&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<div style="width: 100%; border: 1px">
				<table width="100%" border="0" cellspacing="1" cellpadding="1"
					bgcolor="#cbd5de" class="form_label">
					<tr>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								选择
							</div>
						</td>
						<td nowrap height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表ID
							</div>
						</td>
						<td nowrap height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td nowrap height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								所在地区
							</div>
						</td>

						<td nowrap height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								所属专业
							</div>
						</td>
						<td nowrap height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表用途
							</div>
						</td>
						<td nowrap height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								初始读数
							</div>
						</td>
						<td nowrap height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								初始读数时间
							</div>
						</td>
						<td nowrap height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								倍率
							</div>
						</td>
						<td nowrap height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表型号
							</div>
						</td>
						<td nowrap height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								备注
							</div>
						</td>

					</tr>
					<%
						DianBiaoBean bean = new DianBiaoBean();
						ArrayList fylist = new ArrayList();
						fylist = bean.getPageData(curpage, sheng, shi, xian, xiaoqu, sdbid,
								loginName, zdmc, loginId);
						allpage = bean.getAllPage();
						String dbid = "", zdname = "", area = "", sszy = "", dbyt = "", csds = "", cssytime = "", beilv = "", id = "", dbxh = "", memo = "";

						intnum = xh = pagesize * (curpage - 1) + 1;
						double df = 0;
						if (fylist != null) {
							int fycount = ((Integer) fylist.get(0)).intValue();
							for (int k = fycount; k < fylist.size() - 1; k += fycount) {

								//intnum为序号，不同页中序号是连续的
								id = (String) fylist.get(k + fylist.indexOf("ID"));
								dbid = (String) fylist.get(k + fylist.indexOf("DBID"));
								zdname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
								area = (String) fylist.get(k + fylist.indexOf("SZDQ"));
								sszy = (String) fylist.get(k + fylist.indexOf("SSZY"));
								dbyt = (String) fylist.get(k + fylist.indexOf("DBYT"));
								csds = (String) fylist.get(k + fylist.indexOf("CSDS"));
								cssytime = (String) fylist.get(k
										+ fylist.indexOf("CSSYTIME"));
								beilv = (String) fylist.get(k + fylist.indexOf("BEILV"));
								dbxh = (String) fylist.get(k + fylist.indexOf("DBXH"));
								memo = (String) fylist.get(k + fylist.indexOf("MEMO"));
								if (beilv == null || beilv == "" || beilv == "o")
									beilv = "0";
								DecimalFormat ma = new DecimalFormat("0.00");
								df = Double.parseDouble(beilv);
								beilv = ma.format(df);

								if (csds == null || csds.equals("null") || csds.equals(""))
									csds = "";
								if (cssytime == null || cssytime.equals("null")
										|| cssytime.equals(""))
									cssytime = "";
								if (dbxh == null || dbxh.equals("null") || dbxh.equals(""))
									dbxh = "";
								if (memo == null || memo.equals("null") || memo.equals(""))
									memo = "";

								if (intnum % 2 == 0) {
									color = "#DDDDDD";

								} else {
									color = "#FFFFFF";
								}
								intnum++;
					%>
					<tr bgcolor="<%=color%>">
						<td>
							<div align="center"><%=intnum - 1%></div>
						</td>
						<td>
							<div align="center">
								<input type="checkbox" id="itemSelected" name="itemSelected"
									value="<%=dbid%>" />
							</div>
						</td>
						<td>
							<div align="center"><%=dbid%></div>
						</td>
						<td>
							<div align="left">
								<input type="hidden" id="zdname" value="<%=zdname%>" readonly
									class="form"><%=zdname%></div>
						</td>
						<td>
							<div align="left">
								<input type="hidden" id="area" value="<%=area%>" readonly
									class="form"><%=area%></div>
						</td>
						<td>
							<div align="center"><%=sszy%></div>
						</td>
						<td>
							<div align="center"><%=dbyt%></div>
						</td>
						<td>
							<div align="center"><%=csds%></div>
						</td>
						<td>
							<div align="center"><%=cssytime%></div>
						</td>
						<td>
							<div align="right"><%=beilv%></div>
						</td>
						<td>
							<div align="center"><%=dbxh%></div>
						</td>
						<td>
							<div align="center"><%=memo%></div>
						</td>

					</tr>
					<%
						}
					%>
					<%
						if (intnum == 0) {
								System.out.println("QQQQ" + intnum);
								for (int i = 0; i < 15; i++) {
									if (i % 2 == 0) {
										color = "#DDDDDD";
									} else {
										color = "#FFFFFF";
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
							} else if (!(intnum > 15)) {
								for (int i = ((intnum - 1) % 15); i < 15; i++) {
									if (i % 2 == 0)
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

					<tr bgcolor="#ffffff">
						<td colspan="13">
							<div align="center">
								<font color='#000080'>&nbsp;页次:</font> &nbsp;&nbsp;
								<b><font color=red><%=curpage%></font> </b>

								<font color='#000080'>/<b><%=allpage%></b>&nbsp;</font>
								&nbsp;&nbsp;
								<font color='#000080'> <%
 	if (curpage != 1) {
 			out.print("<a href='javascript:gopagebyno(1)'>首页</a>");
 		} else {
 			out.print("首页");
 		}
 %> </font> &nbsp;&nbsp;
								<font color='#000080'> <%
 	if (curpage != 1) {
 			out.print("<a href='javascript:previouspage()'>上页</a>");
 		} else {
 			out.print("上页");
 		}
 %> </font> &nbsp;&nbsp;
								<font color='#000080'> <%
 	if (allpage != 0 && (curpage < allpage)) {
 			out.print("<a href='javascript:nextpage()'>下页</a>");
 		} else {
 			out.print("下页");
 		}
 %> </font> &nbsp;&nbsp;
								<font color='#000080'> <%
 	if (allpage != 0 && (curpage < allpage)) {
 			out.print("<a href='javascript:gopagebyno(" + allpage
 					+ ")'>尾页</a>");
 		} else {
 			out.print("尾页");
 		}
 %> </font> &nbsp;&nbsp;
								<select name="page"
									onChange="javascript:gopagebyno(document.form1.page.value)"
									class="form">
									<%
										for (int i = 1; i <= allpage; i++) {
												if (curpage == i) {
													out.print("<option value='" + i
															+ "' selected='selected'>" + i + "</option>");
												} else {
													out.print("<option value='" + i + "'>" + i
															+ "</option>");
												}
											}
									%>
								</select>


							</div>

						</td>
					</tr>

					<%
						}
					%>


				</table>
			</div>
			<table align="right">
				<tr>
					<td align="right">
						<div id="parent" style="display: inline" align="right">
							<div style="width: 300px; display: inline;" align="right">
								<hr>
							</div>
						</div>
					</td>

				</tr>
				<tr bgcolor="#FFFFFF">
					<td>
						<div align="right">
						</div>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF">
						<div id="querenBtn"
							style="position: relative; width: 59px; height: 23px; cursor: pointer; left: 80%; TOP: -0PX">
							<img alt="" src="<%=request.getContextPath()%>/images/tijiao.png"
								width="100%" height="100%" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">确认</span>
						</div>
					</td>
				</tr>



			</table>

		</form>
	</body>
</html>
<script language="javascript">
var path = '<%=path%>';
function gopage() {
	document.form1.submit();
}
function previouspage() {
	if ((parseInt(document.form1.page.value)) < 1)
		document.form1.page.value = 1;
	else {
		document.form1.page.value = parseInt(document.form1.page.value) - 1;
		var curpage = '<%=(curpage - 1)%>';
		document.form1.action = path
				+ "/web/equipmentmanage/dianbiaolist.jsp?curpage=" + curpage;
		document.form1.submit();
	}
}
function nextpage() {
	document.form1.page.value = parseInt(document.form1.page.value) + 1;
	var curpage = '<%=(curpage + 1)%>';
	document.form1.action = path
			+ "/web/equipmentmanage/dianbiaolist.jsp?curpage=" + curpage;
	document.form1.submit();
}
function gopagebyno(pageno) {
	document.form1.page.value = pageno;

	document.form1.action = path
			+ "/web/equipmentmanage/dianbiaolist.jsp?curpage=" + pageno;
	document.form1.submit();
}
function delzd(id) {
	document.form1.action = path + "/servlet/dianbiao?action=del&id=" + id
	document.form1.submit();
}
function bianji(id) {
	document.form1.action = path + "/web/jizhan/modifydianbiao.jsp?id=" + id
	document.form1.submit();
}
function changeSheng() {
	var sheng = document.form1.sheng.value;
	var xianlist = document.all.xian;
	xianlist.options.length = "0";
	xianlist.add(new Option("请选择", "0"));
	if (sheng == "0") {
		var shilist = document.all.shi;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));

		return;
	} else {
		sendRequest(path + "/servlet/area?action=sheng&pid=" + sheng, "sheng");
	}
}
function updateShi(res) {
	var shilist = document.all.shi;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));

	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
function changeShi() {
	var shi = document.form1.shi.value;
	if (shi == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/area?action=shi&pid=" + shi, "shi");
	}
}
function updateQx(res) {
	var shilist = document.all.xian;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));

	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
function changeXian() {
	var shi = document.form1.xian.value;
	if (shi == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/area?action=xian&pid=" + shi, "xian");
	}
}

function updateXQ(res) {
	var shilist = document.all.xiaoqu;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));

	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
</script>

