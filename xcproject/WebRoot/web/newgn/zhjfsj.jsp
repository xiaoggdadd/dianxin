<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.newfunction.dao.*"%>
<%@ page
	import="java.sql.ResultSet,com.noki.mobi.common.CommonBean,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.text.*"%>
<%
	String beginTime1 = request.getParameter("beginTime1") != null ? request
			.getParameter("beginTime1")
			: "";
	String beginTime2 = request.getParameter("beginTime2") != null ? request
			.getParameter("beginTime2")
			: "";
	String beginTime = request.getParameter("beginTime") != null ? request
			.getParameter("beginTime")
			: CTime.formatRealDate(new Date());
	String title = request.getParameter("title") != null ? request
			.getParameter("title") : "";
	String color = "";
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginId1 = request.getParameter("loginId");
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
	String zdlx = request.getParameter("zdlx") != null ? request
			.getParameter("zdlx") : "请选择";
	String zdlx1 = request.getParameter("zdlx1") != null ? request
			.getParameter("zdlx1") : "";
	String zflx = request.getParameter("dfzflx") != null ? request
			.getParameter("dfzflx") : "0";
	String ammeterid1 = request.getParameter("ammeterid");

	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String permissionRights = "";
	String roleId = (String) session.getAttribute("accountRole");
%>

<html>
	<head>
		<title>logMange</title>
		<style>
.style1 {
	color: red;
	font-weight: bold;
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
	line-height: 120%;
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
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

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

#div1 {
	width: 130px;
	height: 18px;
	border: 1px solid #0000FF;
	position: relative;
}

ul {
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	top: 30px;
	left: -1px;
	background-color: white;
	border: 1px solid #0000FF;
	width: 130px;
	display: none;
}

#div1 p {
	float: left;
	font-size: 12px;
	width: 110px;
	cursor: pointer;
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

.bttcn {
	color: black;
	font-weight: bold;
}
</style>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>

		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
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

function queryDegree() {
	if (document.getElementById("shi").value == ""
			|| document.getElementById("shi").value == "0"
			|| document.getElementById("shi").value == null) {
		alert("城市不能为空");

	} else {
		var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
		var et = document.form1.beginTime1.value;
		var et2 = document.form1.beginTime2.value;
		var sss=document.form1.dfzflx.value;
		if (reg1.exec(et) || et == "" || et == null || reg1.exec(et2)
				|| et2 == "" || et2 == null) {
			if(riqi(et,et2)){
				showdiv("请稍等..........");
				document.form1.action = path + "/web/newgn/zhjfsj.jsp?command=chaxun";
				document.form1.submit();
			} else {
			alert("您输入的报账月份有误，请确认后重新输入！");
			}
		}else{
			alert("您输入的报账月份有误，请确认后重新输入！");
		}
	}
}
//判断两个日期前后顺序是否一致
function riqi(DateOne, DateTwo) {
	var OneMonth = DateOne.substring(5);
	var OneYear = DateOne.substring(0,4);
	var TwoMonth = DateTwo.substring(5);
	var TwoYear = DateTwo.substring(0, 4);
	var y = OneYear+OneMonth;
	var e = TwoYear+TwoMonth;
	if(y==""&&e!=""){
		return false;
	}else if(e==""&&y!=""){
		return false;
	}else{
		if (y<=e) {
		return true;
		} else {
		return false;
		}
	}
	
}
	//页面载入方法
function op() {
	window.open(
					path + '/InquiryServlet?quiryTaeble=zhandian,ammeters,ammeterdegrees&tab=zd,am,ad,ef&flg=into',
					'newwindow',
					'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
}
$(function() {

	$("#query").click(function() {
		queryDegree();
	});
	$("#daochuBtn").click(function() {
		exportad();
	});
	$("#queding").click(function() {
		queding();
	});
	$("#quxiao").click(function() {
		quxiao();
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
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>

	<%
		permissionRights = commBean.getPermissionRight(roleId, "0503");
	%>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
				<tr >
	      <td colspan="4" width="50"  >
	       <div style="width:700px;height:50px">
											  
												 <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">未交费站点查询</span>	
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
									<span class="style1">&nbsp;*</span>
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
										style="position: relative; width: 59px; height: 23px; right: -205px; cursor: pointer; TOP: 0PX">
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
					<td colspan="10">
						<div style="width: 100%;">
							<p id="box3" style="display: none">
								<table>
									<tr class="form_label">


										<td>
											站点名称：
										</td>
										<td>
											<input type="text" name="zdname"
												value="<%if (null != request.getParameter("zdname"))
				out.print(request.getParameter("zdname"));%>"
												class="selected_font" />
										</td>
										<td>
											&nbsp;&nbsp;站点类型：
										</td>
										<td>
											<div id="div1">
												<p>
													<input type="text" class="selected_font" readonly="true"
														name="zdlx" value="请选择" />
												</p>
												<ul>
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
													<li>
														<input type="checkbox" name="CheckboxGroup1"
															value="<%=code%>,<%=name%>" id="CheckboxGroup1" /><%=name%></li>
													<%
														}
														}
													%>
													<li>
														<input type="button" name="queding" id="queding"
															value="确定" />
														<input type="button" name="quxiao" id="quxiao" value="取消" />
													</li>
												</ul>

											</div>
										</td>

										<td>
											&nbsp;&nbsp;起始月份：
										</td>
										<td>
											<input type="text" class="selected_font" name="beginTime1"
												value="<%if (null != request.getParameter("beginTime1"))
				out.print(request.getParameter("beginTime1"));%>"
												onFocus="getDatenyString(this,oCalendarChsny)" />
										</td>
										<td>
											&nbsp;&nbsp;结束月份：
										</td>
										<td>
											<input type="text" class="selected_font" name="beginTime2"
												value="<%if (null != request.getParameter("beginTime2"))
				out.print(request.getParameter("beginTime2"));%>"
												onFocus="getDatenyString(this,oCalendarChsny)" />
										</td>
										</tr>
									<tr class="form_label">
										<td>
											电费支付类型：
										</td>
										<td>
											<select name="dfzflx" class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList dfzflx = new ArrayList();
													dfzflx = ztcommon.getSelctOptions("DFZFLX");
													if (dfzflx != null) {
														String code = "", name = "";
														int cscount = ((Integer) dfzflx.get(0)).intValue();
														for (int i = cscount; i < dfzflx.size() - 1; i += cscount) {
															code = (String) dfzflx.get(i + dfzflx.indexOf("CODE"));
															name = (String) dfzflx.get(i + dfzflx.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
													}
													}
												%>

											</select>
										</td>
									
									</tr>

								</table>
							</p>
						</div>
					</td>
				</tr>
			</table>
			<table height=23>
				<tr>
					<td height="20" colspan="4">
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
				String zcxstr1 = "";
				String zcxstr2 = "";
				String flag_zf="";
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
					whereStr = whereStr + " AND ZD.JZNAME = '" + zdname + "'";
				}
				
				if (zflx != null && !zflx.equals("") && !zflx.equals("0")) {
					whereStr=whereStr+" AND db.dfzflx='"+zflx+"' ";
					flag_zf=zflx;
				}
				if (beginTime1 != null && beginTime1 != ""
						&& !beginTime1.equals("0")) {
					zcxstr1 = " and df.ACCOUNTMONTH between '"+beginTime1+"' and '";
					zcxstr2 = " and yff.accountmonth between '"+beginTime1+"' and '";
				}
				if (beginTime2 != null && beginTime2 != ""
					&& !beginTime2.equals("0")) {
				zcxstr1 =zcxstr1+beginTime2+"'";
				zcxstr2 =zcxstr2+beginTime2+"'";
			}
				if (zdlx1 != null && !zdlx1.equals("") && !zdlx1.equals("0")) {
					whereStr = whereStr + " AND ZD.STATIONTYPE IN(" + zdlx1 + ")";
				}
				if (loginId != null && !"".equals(loginId)) {
					whereStr = whereStr
							+ "AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
							+ loginId + "')))";
				}
				
			%>


			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="1100px" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								城市
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								乡镇
							</div>
						</td>

						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								支付类型
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								最后一次抄表时间
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账月份
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点ID
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表ID
							</div>
						</td>
					</tr>
					<%
						List<ElectricFeesFormBean> list = null;
						if ("chaxun".equals(request.getParameter("command"))) {
							SerchZhandian bean = new SerchZhandian();
							list = bean.getLastPay(whereStr,zcxstr1,zcxstr2,flag_zf);
						} else {
							list = null;
						}
						int intnum = 0;
						if (list != null) {
							String shi1 = "", xian1 = "", xz = "", jzname1 = "", jztype1 = "", zhandianid = "", dianbiaoid = "",dfzf="",dbid="",jfsj="",bzyf="";
							for (ElectricFeesFormBean ef : list) {
								shi1 = ef.getShi();
								xian1 = ef.getXian();
								xz = ef.getXiaoqu();
								jzname1 = ef.getJzname();
								jztype1 = ef.getJztype();
								zhandianid = ef.getId();
								bzyf=ef.getAccountmonth();
								jfsj=ef.getPaydatetime();
								dfzf=ef.getDfzflx();
								dbid=ef.getDbid();
								if (shi1 == null || "null".equals(shi1)) {
									shi1 = "";
								}
								if (xian1 == null || "null".equals(xian1)) {
									xian1 = "";
								}
								if (xz == null || "null".equals(xz)) {
									xz = "";
								}
								if (jzname1 == null || "null".equals(jzname1)) {
									jzname1 = "";
								}
								if (jztype1 == null || "null".equals(jztype1)) {
									jztype1 = "";
								}
								if (zhandianid == null || "null".equals(zhandianid)) {
									zhandianid = "";
								}
								if (dfzf == null || "null".equals(dfzf)||"0".equals(dfzf)) {
									dfzf = "";
								}
								if (dbid == null || "null".equals(dbid)) {
									dbid = "";
								}
								if (jfsj == null || "null".equals(jfsj)) {
									jfsj = "";
								}
								if (bzyf == null || "null".equals(bzyf)) {
									bzyf = "";
								}
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
						<td>
							<div align="left"><%=shi1%></div>
						</td>
						<td>
							<div align="left"><%=xian1%></div>
						</td>
						<td>
							<div align="left"><%=xz%></div>
						</td>
						<td>
							<div align="left"><%=jzname1%></div>
						</td>
						<td>
							<div align="left"><%=jztype1%></div>
						</td>
						<td>
							<div align="left">
								<%=dfzf %>
							</div>
						</td>
						<td>
							<div align="left"><%=jfsj%></div>
						</td>
						<td>
							<div align="left"><%=bzyf%></div>
						</td>
						<td>
							<div align="left"><%=zhandianid%></div>
						</td>
						<td>
							<div align="left"><%=dbid%></div>
						</td>
					</tr>
					<%
						}
						}
					%>

				</table>
			</div>
			<table width="100%" height="8%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td align="right" height="19" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<div id="daochuBtn"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 4px">
							<img src="<%=path%>/images/daoru.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导出</span>
						</div>
					</td>

				</tr>
				<tr>
					<td>
						<input type="hidden" name="sheng2" id="sheng2" value="" />
						<input type="hidden" name="shi2" id="shi2" value="" />
						<input type="hidden" name="xian2" id="xian2" value="" />
						<input type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
						<input type="hidden" name="dbyt2" id="dbyt2" value="" />
						<input type="hidden" name="zdlx1" id="zdlx1" value="" />
						<input type="hidden" name="ccz" id="ccz" value="<%=zdlx1%>" />
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script language="javascript">
var path = '<%=path%>';
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
window.onload = function() {
	var oDiv = document.getElementById('div1');
	var oUl = oDiv.getElementsByTagName('ul')[0];
	var oP = oDiv.getElementsByTagName('p')[0];
	var bSwitch = false;

	oP.onclick = function() {
		if (bSwitch) {
			oUl.style.display = 'none';
			bSwitch = false;
		} else {
			var obj = document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
			var zdlx2 = document.form1.zdlx1.value;
			if (zdlx2 != "" && zdlx2 != null) {
				var zdlx3 = zdlx2.split(",");
				for ( var i = 0; i < obj.length; i++) {
					var m = obj[i].value.toString().indexOf(",");
					var bm = obj[i].value.toString().substring(0, m);
					for ( var j = 0; j < zdlx3.length; j++) {
						var zdlx4 = zdlx3[j].toString().substring(1,
								zdlx3[j].length - 1);
						if (bm == zdlx4) {
							obj[i].checked = true;
						}
					}

				}
			}
			oUl.style.display = 'block';
			bSwitch = true;
		}
	};

};
function queding() {
	var oDiv = document.getElementById('div1');
	var oUl = oDiv.getElementsByTagName('ul')[0];
	var obj = document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
	var str1 = "";
	var str2 = "";
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].checked) {
			var m = obj[i].value.toString().indexOf(",");
			var bm = obj[i].value.toString().substring(0, m);
			var mc = obj[i].value.toString().substring(m + 1,
					obj[i].value.toString().length);
			str1 = str1 + "'" + bm + "',";
			str2 = str2 + mc + ",";
		}
	}
	str1 = str1.substring(0, str1.length - 1);
	str2 = str2.substring(0, str2.length - 1);
	document.form1.zdlx1.value = str1;
	document.form1.zdlx.value = str2;
	oUl.style.display = 'none';
}
//取消选中   
function quxiao() {
	var oDiv = document.getElementById('div1');
	var oUl = oDiv.getElementsByTagName('ul')[0];
	var obj = document.getElementsByName("CheckboxGroup1");
	if (obj.length) {
		for ( var i = 0; i < obj.length; i++) {
			obj[i].checked = false;
		}
		oUl.style.display = 'none';
	} else {
		obj.checked = false;
		oUl.style.display = 'none';
	}
}
</script>
	<script type="text/javascript">
function exportad() {
	var Str = "<%=whereStr%>";
	var zcx1 = "<%=zcxstr1%>";
	var zcx2 = "<%=zcxstr2%>";
	var flag_zf="<%=flag_zf%>";
	
	document.form1.action = path + "/web/newgn/站点最后缴费时间.jsp?zcxStr1=" + zcx1
			+ "&zcxStr2=" + zcx2 + "&whereStr=" + Str+"&flag_zflx="+flag_zf;
	document.form1.submit();
}

document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
document.form1.zdlx.value = '<%=zdlx%>';
document.form1.dfzflx.value = '<%=zflx%>';
document.form1.zdlx1.value = document.form1.ccz.value;
</script>
</html>
