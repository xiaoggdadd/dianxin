<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account,java.util.Calendar"%>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="com.noki.newfunction.javabean.*"%>
<%@ page import="com.noki.newfunction.dao.*"%>
<%@ page import="java.text.*,java.util.regex.Pattern"%>
<%@ page import="java.text.*"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
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
	String zdname = request.getParameter("zdname") != null ? request
			.getParameter("zdname") : "0";
	String shenhe = request.getParameter("shenhe") != null ? request
			.getParameter("shenhe") : "0";
	String zt = request.getParameter("zt") != null ? request
			.getParameter("zt") : "1";
	String zgmonth = request.getParameter("zgmonth") != null ? request
			.getParameter("zgmonth") : "";
	String loginId1 = request.getParameter("loginId");
	int count = 0;
	String permissionRights = "";
	String color = null;
	String roleId = (String) session.getAttribute("accountRole");
	double ddf = 0.0;
	int intnum = 0;
	String ff = "";
	if (zgmonth != null && !"".equals(zgmonth)) {
		String bijiao = zgmonth.substring(0, 7);
		zgsjdao beann = new zgsjdao();
		List<zgsj> lisst = new ArrayList<zgsj>();
		lisst = beann.getRepeat(loginId, shi, xian, bijiao);
		String qs = "";
		String q = "";
		for (zgsj beans : lisst) {
			qs = beans.getZgyf();
			System.out.println("gs" + qs);
			if (bijiao.equals(qs)) {
				ff = "you";
				break;
			}
		}

	}

	String datetime = new SimpleDateFormat("yyyy-MM").format(Calendar
			.getInstance().getTime());
	//日期默认为本月月底
	Date tt1 = new Date(); //得到当前时间
	tt1 = new Date(tt1.getYear(), tt1.getMonth() + 1, 0); //得到本月最后一天
	String s = datetime + "-" + tt1.getDate();
	if (zgmonth == null || "".equals(zgmonth)) {
		zgmonth = s;

	}
%>

<html>
	<head>
		<title></title>
		<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}

.style1 {
	color: red;
	font-weight: bold;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.bttcn {
	color: BLACK;
	font-weight: bold;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height: 23px
}

.selected_font {
	width: 100px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 120%;
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}
</style>
		<script type="text/javascript"
			src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/javascript/PopupCalendar_ny.js">
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
function queryDegree() {
	if (document.getElementById("xiaoqu").value == "0"
			|| document.getElementById("zgmonth").value == "") {
		alert("乡镇和暂估日期不能为空！请填写");
	} else {
		document.form1.action = path
				+ "/web/jzcbnewfunction/addZangudan.jsp?command=chaxun";
		document.form1.submit();
	}
}
function baocun() {
	var zt = document.getElementById("zt").value;
	if (document.getElementById("zt").value == "2") {
		if (document.getElementById("shi").value == ""
				|| document.getElementById("zgmonth").value == "") {
			alert("暂估日期不能为空！请填写");
		} else {
			document.form1.action = path
					+ "/servlet/ZGsjServlet?action=save&zt=" + zt;
			document.form1.submit();
		}
	} else {
		if (document.getElementById("ff").value == "") {
			if (document.getElementById("shi").value == ""
					|| document.getElementById("zgmonth").value == "") {
				alert("暂估日期不能为空！请填写");
			} else {
				document.form1.action = path
						+ "/servlet/ZGsjServlet?action=save&zt=" + zt;
				document.form1.submit();
			}
		} else {
			alert("本月已审核！不允许保存信息！");
		}
	}
}
function baocun1() {
	if (document.getElementById("shi").value == ""
			|| document.getElementById("zgmonth").value == "") {
		alert("暂估日期不能为空！请填写");
	} else {
		document.form1.action = path + "/servlet/ZGsjServlet?action=save&zt"
				+ zt;
		document.form1.submit();
	}
}

$(function() {
	$("#chaxun").click(function() {
		queryDegree();

	});

	$("#baocun").click(function() {
		baocun();
	});
});
</script>

	</head>

	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<%
		permissionRights = commBean.getPermissionRight(roleId, "0804");
	%>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
				<tr>
					<td colspan="4">
						<div style="width: 700px;">
							<img alt="" src="<%=path%>/images/btt.bmp" width="100%"
								height="100%" />
							<span
								style="font-size: 14px; font-weight: bold; position: absolute; left: 25px; top: 15px; color: black">区县查询</span>
						</div>
					</td>
				</tr>
				<tr>
					<td height="30" colspan="4">
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
					<td height="8%" width="1200">
						<table>
							<tr class="form_label">
								<td>
									城市：
								</td>
								<td>
									<select name="shi" class="selected_font"
										onchange="changeCity()">
										<option value="0">
											请选择
										</option>
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
									<select name="xian" class="selected_font"
										onchange="changeCountry()">
										<option value="0">
											请选择
										</option>
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
										<%
											}
											}
										%>
									</select>
									<span class="style1">&nbsp;*</span>
								</td>
								<td class="form_label">
									<div>
										暂估月份结束：
									</div>
								</td>
								<td>
									<input type="text" name="zgmonth" value=""
										onFocus="getDatenyString(this,oCalendarChs)"
										class="selected_font" />
									<span class="style1">&nbsp;*</span>
								</td>
								<td>
									站点名称：
								</td>
								<td>
									<input type="text" class="selected_font" name="zdname"
										value="<%if (null != request.getParameter("zdname"))
				out.print(request.getParameter("zdname"));%>" />
								</td>
								<td>
									财务审核
								</td>
								<td>
									<select name="zt" id="zt" style="width: 130"
										class="selected_font">
										<option value="1">
											请选择
										</option>
										<option value="2">
											审核不通过
										</option>
									</select>

								</td>

								<td>
									<%
										if (permissionRights.indexOf("PERMISSION_SEARCH") >= 0) {
									%>
									<%
										}
									%>
									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -40px; float: right; top: 0px">
										<img alt="" src="<%=path%>/images/chaxun.png" width="100%"
											height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>

									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td height="23" colspan="4">
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
			<%
				String whereStr = "";

				if (shi != null && !shi.equals("") && !shi.equals("0")) {
					whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
				}
				if (xian != null && !xian.equals("") && !xian.equals("0")) {
					whereStr = whereStr + " AND ZD.XIAN='" + xian + "'";
				}
				if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
					whereStr = whereStr + " AND ZD.XIAOQU='" + xiaoqu + "'";
				}
				if (zdname != null && !zdname.equals("") && !zdname.equals("0")) {
					whereStr = whereStr + " AND ZD.JZNAME LIKE '%" + zdname + "%'";
				}
				if (zt != null && !zt.equals("") && !zt.equals("1")) {
					whereStr = whereStr
							+ " AND ZG.QXSHZT='1' AND ZG.SJSHZT='1' AND ZG.SJESHZT='1' AND ZG.CWSHZT='2'";
				}
			%>

			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="100%" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<!-- 	<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td> -->
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								城市
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								乡镇
							</div>
						</td>
						<td width="16%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								上次暂估时间
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								本次暂估时间
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								暂估月份
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								周期
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								单价(元/天)
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								暂估电费
							</div>
						</td>
					</tr>
					<%
						ArrayList<zgsj> fylist = null;
						ArrayList<zgsj> listq = new ArrayList<zgsj>();
						zgsjdao dao = new zgsjdao();
						zgsj bean = null;
						String id = "", dbid = "", zdlx = "", zdnamea = "", shi1 = "", xian1 = "", xiaoqu1 = "";
						String zgyf = "";
						String dianfei = "";//站点表单价
						String cssytime = "";//电表初始使用时间
						String lasttime = "";//上次抄表 时间
						String thistime = "";//本次抄表时间
						String actualpay = "";//金额
						String zgqssj = "";//暂估起始时间
						String zgjssj = "";//暂估结束时间
						String money = "";//暂估金额
						String edhdl = "", sdb = "";
						String nowzangu = "";//暂估时间+1,
						String nowtime = "";//上次抄表时间+1
						String lastzg = "";//页面显示暂估起始时间
						String qxshr = "";
						String qxshsj = "";
						String sjshr = "";
						String sjshsj = "";
						String sjeshr = "";
						String sjeshsj = "";
						if ("daochu".equals(request.getParameter("command"))
								|| "chaxun".equals(request.getParameter("command"))) {
							if (zt.equals("1")) {
								fylist = dao.getZgxx(whereStr);
							} else {
								fylist = dao.getZgxx1(whereStr);
							}
							if (fylist != null) {
								for (zgsj ls : fylist) {
									bean = new zgsj();
									shi1 = ls.getShi();
									if (null == shi1 || "null".equals(shi1)
											|| "".equals(shi1)) {
										shi1 = "";
									}
									xian1 = ls.getQuxian();
									if (null == xian1 || "null".equals(xian1)
											|| "".equals(xian1)) {
										xian1 = "";
									}
									xiaoqu1 = ls.getXiangzhen();
									if (null == xiaoqu1 || "null".equals(xiaoqu1)
											|| "".equals(xiaoqu1)) {
										xiaoqu1 = "";
									}
									zdnamea = ls.getZdname();
									if (null == zdnamea || "null".equals(zdnamea)
											|| "".equals(zdnamea)) {
										zdnamea = "";
									}
									id = ls.getZdid();
									if (null == id || "null".equals(id) || "".equals(id)) {
										id = "";
									}
									dbid = ls.getDbid();
									if (null == dbid || "null".equals(dbid)
											|| "".equals(dbid)) {
										dbid = "";
									}
									zdlx = ls.getZdlx();
									if (null == zdlx || "null".equals(zdlx)
											|| "".equals(zdlx)) {
										zdlx = "";
									}
									dianfei = ls.getDianfei();
									if (null == dianfei || "null".equals(dianfei)
											|| "".equals(dianfei)) {
										dianfei = "";
									}
									cssytime = ls.getCssytime();
									if (null == cssytime || "null".equals(cssytime)
											|| "".equals(cssytime)) {
										cssytime = "";
									}
									lasttime = ls.getLasttime();
									if (null == lasttime || "null".equals(lasttime)
											|| "".equals(lasttime)) {
										lasttime = "";
									}
									thistime = ls.getThistime();
									if (null == thistime || "null".equals(thistime)
											|| "".equals(thistime)) {
										thistime = "";
									}
									actualpay = ls.getActualpay();
									if (null == actualpay || "null".equals(actualpay)
											|| "".equals(actualpay)) {
										actualpay = "";
									}
									zgqssj = ls.getZfqssj();
									if (null == zgqssj || "null".equals(zgqssj)
											|| "".equals(zgqssj)) {
										zgqssj = "";
									}
									zgjssj = ls.getZfjssj();
									if (null == zgjssj || "null".equals(zgjssj)
											|| "".equals(zgjssj)) {
										zgjssj = "";
									}
									money = ls.getMoney();
									if (null == money || "null".equals(money)
											|| "".equals(money)) {
										money = "0";
									}
									edhdl = ls.getEdhdl();
									if (null == edhdl || "null".equals(edhdl)
											|| "".equals(edhdl)) {
										edhdl = "";
									}
									sdb = ls.getSdb();
									if (null == sdb || "null".equals(sdb) || "".equals(sdb)) {
										sdb = "";
									}
									System.out.println("-lasttime--" + lasttime
											+ "-thistime-" + thistime + "-cssytime-"
											+ cssytime + "-zgjssj-" + zgjssj + "-zgqssj-"
											+ zgqssj);
									qxshr = ls.getQxshr();
									if (null == qxshr || "null".equals(qxshr)
											|| "".equals(qxshr)) {
										qxshr = "";
									}
									qxshsj = ls.getQxshsj();
									if (null == qxshsj || "null".equals(qxshsj)
											|| "".equals(qxshsj)) {
										qxshsj = "";
									} else {
										qxshsj = qxshsj.substring(0, 19);
									}
									sjshr = ls.getSjshr();
									if (null == sjshr || "null".equals(sjshr)
											|| "".equals(sjshr)) {
										sjshr = "";
									}
									sjshsj = ls.getSjshsj();
									if (null == sjshsj || "null".equals(sjshsj)
											|| "".equals(sjshsj)) {
										sjshsj = "";
									} else {
										sjshsj = sjshsj.substring(0, 19);
									}
									sjeshr = ls.getSjeshr();
									if (null == sjeshr || "null".equals(sjeshr)
											|| "".equals(sjeshr)) {
										sjeshr = "";
									}
									sjeshsj = ls.getSjeshsj();
									if (null == sjeshsj || "null".equals(sjeshsj)
											|| "".equals(sjeshsj)) {
										sjeshsj = "";
									} else {
										sjeshsj = sjeshsj.substring(0, 19);
									}
									System.out.println(qxshsj + "::" + sjshsj + "::"
											+ sjeshsj);
									double day1 = 0.0, danjia1 = 0.0, day2 = 0.0, danjia2 = 0.0, danjia3 = 0.0;
									double danjia = 0.0, yy;
									String daye = "";
									//计算单价
									danjia3 = Double.parseDouble(dianfei);
									if (!"".equals(lasttime) && !" ".equals(lasttime)
											&& lasttime != null && lasttime != ""
											&& lasttime != " " && !"".equals(thistime)
											&& !" ".equals(thistime) && thistime != null
											&& thistime != "" && thistime != " ") {
										System.out.println("抄表时间求单价" + lasttime + "--"
												+ thistime);
										Calendar ca = Calendar.getInstance();
										ca.setTime(DateFormat.getDateInstance().parse(
												lasttime));
										Calendar cal = Calendar.getInstance();
										cal.setTime(DateFormat.getDateInstance().parse(
												thistime));
										Long a = cal.getTimeInMillis()
												- ca.getTimeInMillis();
										day1 = Math.ceil(a / 1000 / 60 / 60 / 24.0);
										day1 = day1 + 1.0;
										danjia1 = Double.parseDouble(actualpay) / day1;
										System.out.println("---抄表单价---+" + danjia1);

									}
									if (!"".equals(zgjssj) && !" ".equals(zgjssj)
											&& zgjssj != null && zgjssj != ""
											&& zgjssj != " " && !"".equals(zgqssj)
											&& !" ".equals(zgqssj) && zgqssj != null
											&& zgqssj != "" && zgqssj != " ") {
										System.out.println("暂估时间求单价" + zgqssj + "--"
												+ zgjssj);
										Calendar caa = Calendar.getInstance();
										caa.setTime(DateFormat.getDateInstance().parse(
												zgqssj));
										Calendar call = Calendar.getInstance();
										call.setTime(DateFormat.getDateInstance().parse(
												zgjssj));
										Long ss = call.getTimeInMillis()
												- caa.getTimeInMillis();
										day2 = Math.ceil(ss / 1000 / 60 / 60 / 24.0);
										day2 = day2 + 1.0;
										danjia2 = Double.parseDouble(money) / day2;
										//System.out.println("暂估时间求单价11111"+day2+"   : "+money);
										System.out.println("暂估时间求单价" + danjia2);
									}
									if (!"".equals(cssytime) && !" ".equals(cssytime)
											&& cssytime != null && cssytime != ""
											&& cssytime != " ") {
										SimpleDateFormat sdf = new SimpleDateFormat(
												"yyyy-MM-dd");
										Date dt = sdf.parse(cssytime);
										Calendar rightNow = Calendar.getInstance();
										rightNow.setTime(dt);
										rightNow.add(Calendar.DAY_OF_YEAR, 1);//日期加1天
										Date dt1 = rightNow.getTime();
										lastzg = sdf.format(dt1);
										//danjia3 = Double.parseDouble(dianfei);
										danjia = 0.0;
										System.out.println("站点单价" + danjia3);
									} else {
										day1 = 0;
										daye = "0";
										danjia3 = Double.parseDouble(dianfei);
									}

									//页面显示暂估起始时间
									if (!"".equals(lasttime) && !" ".equals(lasttime)
											&& lasttime != null && lasttime != ""
											&& lasttime != " "
											&& !lasttime.equals("20101-21")) {
										if (!"".equals(zgjssj) && !" ".equals(zgjssj)
												&& zgjssj != null && zgjssj != ""
												&& zgjssj != " "
												&& !zgjssj.equals("20101-21")) {
											SimpleDateFormat sdf = new SimpleDateFormat(
													"yyyy-MM-dd");
											System.out.println("lasttime: " + lasttime
													+ "thistime: " + thistime + "zgqssj: "
													+ zgqssj + "zgjssj: " + zgjssj);
											Date dt = sdf.parse(lasttime);
											Calendar rightNow = Calendar.getInstance();
											rightNow.setTime(dt);
											rightNow.add(Calendar.DAY_OF_YEAR, 1);//日期加1天
											Date dt1 = rightNow.getTime();
											nowtime = sdf.format(dt1);
											Date dtt = sdf.parse(zgjssj);
											Calendar rightNow1 = Calendar.getInstance();
											rightNow1.setTime(dtt);
											rightNow1.add(Calendar.DAY_OF_YEAR, 1);//日期加1天
											Date dt11 = rightNow1.getTime();
											nowzangu = sdf.format(dt11);

											System.out.println("上次抄表时间+1： " + nowtime
													+ "暂估结束时间+1： " + nowzangu);
											Date d = sdf.parse(nowtime);
											boolean flag = d.before(DateFormat
													.getDateInstance().parse(nowzangu));
											if (flag) {
												lastzg = nowzangu;
												danjia = danjia2;
											} else {
												lastzg = nowtime;
												danjia = danjia1;
											}
											System.out.println("页面显示暂估开始时间： " + lastzg
													+ "抄表，暂估比较单价： " + danjia);
										} else {
											SimpleDateFormat sdf = new SimpleDateFormat(
													"yyyy-MM-dd");
											Date dt = sdf.parse(lasttime);
											Calendar rightNow = Calendar.getInstance();
											rightNow.setTime(dt);
											rightNow.add(Calendar.DAY_OF_YEAR, 1);//日期加1天
											Date dt1 = rightNow.getTime();
											lastzg = sdf.format(dt1);
											danjia = danjia1;
											System.out.println("抄表不为空，暂估为空 页面显示时间" + lastzg
													+ "抄表计算单价" + danjia);
										}
									} else {
										if (!"".equals(zgjssj) && !" ".equals(zgjssj)
												&& zgjssj != null && zgjssj != ""
												&& zgjssj != " "
												&& !zgjssj.equals("20101-21")) {
											SimpleDateFormat sdf = new SimpleDateFormat(
													"yyyy-MM-dd");
											Date dt = sdf.parse(zgjssj);
											Calendar rightNow = Calendar.getInstance();
											rightNow.setTime(dt);
											rightNow.add(Calendar.DAY_OF_YEAR, 1);//日期加1天
											Date dt1 = rightNow.getTime();
											lastzg = sdf.format(dt1);
											danjia = danjia2;
											System.out.println("暂估不为空，抄表为空 页面显示时间" + lastzg
													+ "暂估计算单价" + danjia);
										} else {
											if (!"".equals(cssytime)
													&& !" ".equals(cssytime)
													&& cssytime != null && cssytime != ""
													&& cssytime != " "
													&& !cssytime.equals("20101-21")) {
												SimpleDateFormat sdf = new SimpleDateFormat(
														"yyyy-MM-dd");
												Date dt = sdf.parse(cssytime);
												Calendar rightNow = Calendar.getInstance();
												rightNow.setTime(dt);
												rightNow.add(Calendar.DAY_OF_YEAR, 1);//日期加1天
												Date dt1 = rightNow.getTime();
												lastzg = sdf.format(dt1);
												//danjia = danjia3;
												System.out.println("只有电表初始使用时间： " + lastzg);
											} else {
												lastzg = "";
												danjia = danjia3;
												yy = 0.00;
											}
										}
									}
									//暂估月份
									String[] subString = zgmonth.split("-");
									zgyf = subString[0] + "-" + subString[1];

									//计算周期
									double days = 0.0;
									if (!"".equals(lastzg) && !" ".equals(lastzg)
											&& lastzg != null && lastzg != ""
											&& lastzg != " ") {
										System.out.println("页面显示暂估开始时间： " + lastzg
												+ " 页面显示暂估结束时间  : " + zgmonth);
										Calendar lastTime = Calendar.getInstance();//暂估起始时间
										lastTime.setTime(DateFormat.getDateInstance()
												.parse(lastzg));
										Calendar thisTime = Calendar.getInstance(); //暂估结束时间
										thisTime.setTime(DateFormat.getDateInstance()
												.parse(zgmonth));
										Long temp = thisTime.getTimeInMillis()
												- lastTime.getTimeInMillis();
										days = Math.ceil(temp / 1000 / 60 / 60 / 24.0); //暂估天数
										days = days + 1.0;
										DecimalFormat d = new DecimalFormat("0");
										daye = d.format(days);
									}
									System.out.println("wowowowowow" + daye);
									String ddd = "0";//暂估天数
									//如果暂估天数不等于0  进行判断
									int dddd = 0;
									if (daye != "0") {
										dddd = Integer.parseInt(daye) - 1;
										if (dddd < 0) { //如果小于0 暂估天数就为0
											dddd = 0;
											days = 0;
										}
										ddd = dddd + "";//暂估天数
										// System.out.println("ddd:"+ddd); 
									}

									System.out.println("-------------dddd" + dddd);
									DecimalFormat format = new DecimalFormat("0.00");
									DecimalFormat form = new DecimalFormat("0.0000");
									//求电费
									double qsdb = 0.0, eddl = 0.0, f = 0.0, fq = 0.0, fqf = 0.0;
									double dj;
									String zgdf = "", zgdj = "";
									if (!"".equals(sdb) && !" ".equals(sdb) && sdb != ""
											&& sdb != " " && sdb != null) {
										System.out.println("省定标求电费：--------");
										System.out.println("周期 ： " + dddd + "站点单价： "
												+ danjia3 + "计算后单价： " + danjia);
										qsdb = Double.parseDouble(sdb);
										//ff = qsdb*days*danjia3;
										//fqf = danjia*days;
										f = qsdb * dddd * danjia3;
										fqf = danjia * dddd;

										if (f > fqf && fqf != 0) {
											yy = fqf;
											zgdf = format.format(fqf);
											dj = danjia;
											zgdj = form.format(dj);
										} else {
											yy = f;
											zgdf = format.format(f);
											dj = danjia3;
											zgdj = form.format(dj);
										}

									} else {
										if (!"".equals(edhdl) && !" ".equals(edhdl)
												&& edhdl != "" && edhdl != " "
												&& edhdl != null) {
											System.out.println("本地定标电费： " + edhdl);
											System.out.println("周期： " + dddd + "站点单价： "
													+ danjia3 + "计算单价： " + danjia);
											eddl = Double.parseDouble(edhdl);
											fq = eddl * dddd * danjia3;
											fqf = danjia * dddd;
											System.out.println("本地定标电费: " + fq + "计算电费： "
													+ fqf);
											if (fq > fqf && fqf != 0) {
												yy = fqf;
												zgdf = format.format(fqf);
												dj = danjia;
												zgdj = form.format(dj);
												System.out.println("页面显示计算电费：" + zgdf
														+ "显示单价 :" + zgdj);
											} else {
												yy = fq;
												zgdf = format.format(fq);
												dj = danjia3;
												zgdj = form.format(dj);
												System.out.println("页面显示本地定标电费：" + zgdf
														+ "显示单价 :" + zgdj);
											}
										} else {
											System.out.println("3求电费");
											fqf = danjia * dddd;
											yy = fqf;
											zgdf = format.format(fqf);
											dj = danjia3;
											zgdj = form.format(dj);
										}
									}

									if (!"0.00".equals(zgdf) && !"".equals(zgdf)
											&& !"0".equals(ddd)) {
										//zgsj bean = new zgsj();
										System.out
												.println("来了吗!!!!!!!!!!!!!!!!!!!!!!!!!!!");
										System.out.println("shi" + shi1 + "xian1" + xian1
												+ "xiaoqu1" + xiaoqu1 + "zdnamea" + zdnamea
												+ "--id--" + id + "zddj" + zgdj + " "
												+ lastzg + " " + zgmonth + " " + zgyf + " "
												+ zgdf);
										bean.setShi(shi1);
										System.out.println("++-++++");
										bean.setQuxian(xian1);
										bean.setXiangzhen(xiaoqu1);
										bean.setZdname(zdnamea);
										bean.setZdid(id);
										bean.setDanjia(zgdj);
										bean.setZdlx(zdlx);
										bean.setZfqssj(lastzg);
										bean.setZfjssj(zgmonth);
										bean.setZgyf(zgyf);
										bean.setMoney(zgdf);
										bean.setDbid(dbid);
										bean.setBzw(zt);
										bean.setQxshr(qxshr);
										bean.setQxshsj(qxshsj);
										bean.setSjshr(sjshr);
										bean.setSjshsj(sjshsj);
										bean.setSjeshr(sjeshr);
										bean.setSjeshsj(sjeshsj);
										listq.add(bean);
										System.out.println("++++++");
										request.getSession().removeAttribute("jzname");
										request.getSession().setAttribute("jzname", listq);

										if (intnum % 2 == 0) {
											color = "#FFFFFF";
										} else {
											color = "#DDDDDD";
										}
										intnum++;
					%>

					<tr bgcolor="<%=color%>">
						<td>
							<div align="center"><%=intnum%></div>
						</td>
						<!--  <td>
              <div align="center"><input type="checkbox" name="test[]" value="<%=id%>" /></div>
            </td>-->
						<td>
							<div align="center"><%=shi1%></div>
						</td>
						<td>
							<div align="center"><%=xian1%></div>
						</td>
						<td>
							<div align="center"><%=xiaoqu1%></div>
						</td>
						<td>
							<div align="center"><%=zdnamea%></div>
						</td>
						<td>
							<div align="center"><%=zdlx%></div>
						</td>
						<td>
							<div align="center"><%=lastzg%></div>
						</td>
						<td>
							<div align="center"><%=zgmonth%></div>
						</td>
						<td>
							<div align="center"><%=zgyf%></div>
						</td>
						<td>
							<div align="center"><%=ddd%></div>
						</td>
						<td>
							<div align="center"><%=zgdj%></div>
						</td>
						<td>
							<div align="center"><%=zgdf%></div>
						</td>
					</tr>
					<%
						}
								}
							}
						}
					%>
					<%
						if (intnum == 0) {
							System.out.println("QQQQ" + intnum);
							for (int i = 0; i < 15; i++) {
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
						<%
							}
							}
						%>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="ff" value="<%=ff%>" />
						</td>
					</tr>
				</table>
			</div>

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right">
						<%--<div id="tjsh" style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right:0px;top: 0px">
				<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
				<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">提交审核</span>
			</div>
			--%>
						<div id="baocun"
							style="width: 100px; height: 23px; cursor: pointer; float: right; position: relative; right: 37px;">
							<img src="<%=path%>/images/2chongzhi.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">保存静态数据</span>
						</div>
					</td>
				</tr>
			</table>
			<input type="hidden" name="sheng2" id="sheng2" value="" />
			<input type="hidden" name="shi2" id="shi2" value="" />
			<input type="hidden" name="xian2" id="xian2" value="" />
			<input type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
			<input type="hidden" name="lrren" value="<%=loginName%>">
		</form>
	</body>
	<script language="javascript">
var path = '<%=path%>';
function lookDetailss(zdid) {
	window
			.open(
					path + "/web/zdqxkz/shishenhe.jsp?zdid=" + zdid,
					'',
					'width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
}
function deletezdxx(zdid) {
	if (confirm("您确定删除此电费信息？")) {
		document.form1.action = path
				+ "/servlet/zdqxcxxg?action=deleteqx&zdid=" + id;
		document.form1.submit();
		showdiv("请稍等..........");
	}
}
function modifyzdxx(id) {
	document.form1.action = path + "/web/zdqxkz/modifyzdgjxxxgxx.jsp?id=" + id;
	document.form1.submit();
	showdiv("请稍等..........");
}
</script>
	<script type="text/javascript">
<!--
var XMLHttpReq;
	//XMLHttpReq = createXMLHttpRequest();
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	
	///////////////////////////////////////////////////////////
	function sendRequest(url,para) {

		createXMLHttpRequest();
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="sheng"){
			XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
	}
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		//alert(XMLHttpReq.status);
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
            	var res = XMLHttpReq.responseText;
              window.alert(res);
             
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    
function processResponse_sheng() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            	
            	//var res = XMLHttpReq.responseText;
            
              updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateQx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZd(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}

function changeSheng(){
	var sid = document.form1.sheng.value;
	document.form1.sheng2.value=document.form1.sheng.value;
	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeCity(){
	var sid = document.form1.shi.value;
	document.form1.shi2.value=document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function changeCountry(){
	var sid = document.form1.xian.value;
	document.form1.xian2.value=document.form1.xian.value;
	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

//-->
</script>
	<script type="text/javascript">
var XMLHttpReq1;
function createXMLHttpRequest1() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq1 = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq1 = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq1 = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}
function sendRequest1(url, para) {

	createXMLHttpRequest1();
	XMLHttpReq1.open("POST", url, true);
	if (para == "checkcity1") {
		XMLHttpReq1.onreadystatechange = processResponse_checkcity1;
	} else if (para == "checkcityno1") {
		XMLHttpReq1.onreadystatechange = processResponse_checkcityno1;
	} else {
		XMLHttpReq1.onreadystatechange = processResponse;//指定响应函数
	}
	XMLHttpReq1.send(null);
}
// 处理返回信息函数
function processResponse() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq1.responseText;
			window.alert(res);
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function passCheck1(chooseIdStr1, chooseIdStr2) {
	sendRequest1(path + "/servlet/check?action=checkcity1&chooseIdStr1="
			+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2, "checkcity1");
}
function processResponse_checkcity1() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			document.form1.bzw1.value = XMLHttpReq1.responseText;
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function passCheckNo1(chooseIdStr1, chooseIdStr2) {
	alert(chooseIdStr1 + " " + chooseIdStr2);
	window
			.open(
					path + "/web/jzcbnewfunction/showzgpl.jsp?zdid="
							+ chooseIdStr1 + "&cbyf=" + chooseIdStr2,
					'',
					'width=1230,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
	//  sendRequest1(path+"/servlet/check?action=checkcityno1&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2,"checkcityno1");
}

function processResponse_checkcityno1() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			document.form1.bzw1.value = XMLHttpReq1.responseText;
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
</script>
	<script type="text/javascript">
function tjsh() { //区县暂估提交审核
	var m = document.getElementsByName('test[]');
	//var mc = document.getElementsByName('zdid2[]');
	var arr = new Array
	var l = m.length;
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var bzw = 1;
	var count2 = 0;
	var chooseIdStr = "";
	//	var comt=0;
	var chooseIdStr1 = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}

	}

	if (count != 0) {
		if (count % 240 == 0) {
			n = count / 240 - 1;
		} else {
			n = (count - (count % 240)) / 240;
		}
		for ( var i = 0; i < l; i++) {
			if (m[i].checked == true) {
				bz += 1;
				count1 += 1;
				chooseIdStr = chooseIdStr + "'" + m[i].value + "',";
			}

			if (count1 <= 240 * n
					|| ((bz + count2) >= 239 && (bz + count2) <= 241)) {
				if (((bz + count2) / 240 == 1)
						|| ((bz + count2) >= 239 && (bz + count2) <= 241)) {
					chooseIdStr = chooseIdStr.substring(0,
							chooseIdStr.length - 1);
					// alert(chooseIdStr+"222");
					document.form1.action = path
							+ "/servlet/TuiDanServlet?action=tuidan&chooseIdStr="
							+ chooseIdStr;
					chooseIdStr = "";
					bz = 0;
					count2 = 0;
					document.form1.submit();
				}
			} else if (count == count1 && bzw == 1) {
				chooseIdStr = chooseIdStr.substring(0, chooseIdStr.length - 1);
				//alert(chooseIdStr+"111");
				bzw = 0;
				document.form1.action = path
						+ "/servlet/TuiDanServlet?action=tuidan&chooseIdStr="
						+ chooseIdStr;
				document.form1.submit();
			}
		}
	} else {
		alert("请选择信息！");
	}

}
</script>
	<!--多选框选择 -->
	<script type="text/javascript">
function chooseAll() {
	var qm = document.getElementsByName('test');
	var m = document.getElementsByName('test[]');
	var l = m.length;
	if (qm[0].checked == true) {
		for ( var i = 0; i < l; i++) {
			m[i].checked = true;
		}
	} else {
		for ( var i = 0; i < l; i++) {
			m[i].checked = false;
		}
	}
}

document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
document.form1.zgmonth.value = '<%=zgmonth%>';
document.form1.zt.value = '<%=zt%>';
</script>
</html>

