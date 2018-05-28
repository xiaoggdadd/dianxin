<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.*,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="java.sql.ResultSet"%>
<%@page
	import="com.noki.equipmentmanage.EquipmentmanageViewBean,com.noki.mobi.common.CommonBean,com.noki.mobi.common.Account"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@page import="com.noki.tongjichaxu.cbJzDao"%>
<%
	String sheng = (String) session.getAttribute("accountSheng");
	String roleId = (String) session.getAttribute("accountRole");
	Account account1 = (Account) session.getAttribute("account");
	String agcode1 = "";

	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : agcode1;
	String bzyf = request.getParameter("bzyf") != null ? request
			.getParameter("bzyf") : "";//报账月份
	String bzmonth = request.getParameter("bzmonth") != null ? request
			.getParameter("bzmonth") : "";//标准
	String jzproperty=request.getParameter("jzproperty")!=null?request
			.getParameter("jzproperty"):"0";
			
	cbJzDao bean = new cbJzDao();
	List<String> fylist = bean.getQztz(bzyf);
	String zdsxcw = "";
	String scnhft = "";
	String mycbgk = "";
	String cbzd = "";
	String mycbjscb = "";
	String lxcb = "";
	String lxcbdlzj = "";
	String pld = "";
	String cbbl = "";
	String cbjzzg = "";
	String chaxun = "";
	String bz = "0";
	if (fylist != null && !fylist.isEmpty()) {
		zdsxcw = fylist.get(1) != null ? fylist.get(1) : "0";
		scnhft = fylist.get(2) != null ? fylist.get(2) : "0";
		mycbgk = fylist.get(3) != null ? fylist.get(3) : "0";
		cbzd = fylist.get(4) != null ? fylist.get(4) : "0";
		mycbjscb = fylist.get(5) != null ? fylist.get(5) : "0";
		lxcb = fylist.get(6) != null ? fylist.get(6) : "0";
		lxcbdlzj = fylist.get(7) != null ? fylist.get(7) : "0";
		pld = fylist.get(8) != null ? fylist.get(8) : "0";
		cbbl = fylist.get(9) != null ? fylist.get(9) : "";
		cbjzzg = fylist.get(10) != null ? fylist.get(10) : "";
		bz = fylist.get(11) != null ? fylist.get(11) : "";
	}
	String datetime = new SimpleDateFormat("yyyy-MM").format(Calendar
			.getInstance().getTime());
	if (null == bzyf || "null".equals(bzyf) || "".equals(bzyf)) {
		bzyf = datetime;
	}
	if (null == bzmonth || "null".equals(bzmonth) || "".equals(bzmonth)) {
		bzmonth = datetime;
	}

	List<String> fylistsyc = bean.getQztzsyc(bzyf);
	String cbblsyc = "";

	if (fylistsyc != null && !fylistsyc.isEmpty()) {
		cbblsyc = fylistsyc.get(0) != null ? fylistsyc.get(0) : "";
	}

	String whereStr = "";
	String path = request.getContextPath();
	String loginName = (String) session.getAttribute("loginName");
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();

	String permissionRights = "";
%>

<html>
	<head>
		<title>监测点列表</title>
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

.selected_font {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
}

.text_font {
	width: 50px;
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
	color: BLACK;
	font-weight: bold;
}

.form {
	width: 130px
}

#id1 {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :                   DXImageTransform.Microsoft.Gradient (
		  
		       
		       GradientType =                   0, StartColorStr =
		               
		  #ffffff, EndColorStr =      
		    
		       #D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}
</style>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/tx.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/web/javascript/prototype.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/web/javascript/jquery-1.4.2.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
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
		<script language="javascript">
var path = '<%=path%>';
function waitThenDoIt() {
	if (window.document.readyState) {//IE
		if (window.document.readyState == 'complete') {
			//初始化页面
			getSelOpen(0);
			getDictList(0);
		} else {
			setTimeout("waitThenDoIt()", 10);
		}
	} else {//Firefox
		window.addEventListener("load", function() {
			getSelOpen(0);
			getDictList(0);
		}, false);
	}

}

function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = ""
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

	} else {
		trObject.style.display = "none"
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
	}
}
function getPublicPath() {
	var path = document.location.pathname;
	var arraypath = path.split("/");
	var mapath = "/";
	if (arraypath[1] == undefined) {
		arraypath[1] = "";
		mapath = "";
	}
	path = mapath + arraypath[1];
	return path;
}
function chaxun() {
	var bzyf = document.form1.bzyf.value;//报账月份
	var bzmonth = document.form1.bzmonth.value;//报账月份
	var property = document.form1.jzproperty.value;//站点属性
	var shi = document.form1.shi.value;

	var zdsxcw = document.form1.zdsxcw.value;
	var scnhft = document.form1.scnhft.value;
	var mycbgk = document.form1.mycbgk.value;
	var cbzd = document.form1.cbzd.value;
	var mycbjscb = document.form1.mycbjscb.value;
	var lxcb = document.form1.lxcb.value;
	var lxcbdlzj = document.form1.lxcbdlzj.value;

	var pld = document.form1.pld.value;
	var cbbl = document.form1.cbbl.value;
	var cbjzzg = document.form1.cbjzzg.value;

	var cbblsyc = '<%=cbblsyc%>';
	if (cbblsyc == null || cbblsyc == "") {
		cbblsyc = cbbl;
	}

	var he = parseFloat(
			parseFloat(zdsxcw) + parseFloat(scnhft) + parseFloat(mycbgk)
					+ parseFloat(cbzd) + parseFloat(mycbjscb)
					+ parseFloat(lxcb) + parseFloat(lxcbdlzj)).toFixed(2);
	if (cbjzzg <= 40 && cbjzzg >= 0) {
		if (he == 100 || he == 100.00) {
			if (isNaN(document.form1.zdsxcw.value) == false
					&& isNaN(document.form1.scnhft.value) == false
					&& isNaN(document.form1.mycbgk.value) == false
					&& isNaN(document.form1.cbzd.value) == false
					&& isNaN(document.form1.mycbjscb.value) == false
					&& isNaN(document.form1.lxcb.value) == false
					&& isNaN(document.form1.lxcbdlzj.value) == false
					&& isNaN(document.form1.pld.value) == false
					&& isNaN(document.form1.cbbl.value) == false
					&& isNaN(document.form1.cbjzzg.value) == false) {

				var href = "cbjzGlIfram.jsp?bzyf=" + bzyf + "&bzmonth="
						+ bzmonth + "&property=" + property + "&bzw=1&shi=" + shi + "&zdsxcw=" + zdsxcw
						+ "&scnhft=" + scnhft + "&mycbgk=" + mycbgk + "&cbzd="
						+ cbzd + "&mycbjscb=" + mycbjscb + "&lxcb=" + lxcb
						+ "&lxcbdlzj=" + lxcbdlzj + "&pld=" + pld + "&cbjzzg="
						+ cbjzzg + "&cbblsyc=" + cbblsyc + "&cbbl=" + cbbl
						+ "&chaxun=1";
				var str = "<a id='aa' href='" + href + "' target='test'></a>";
				$("#aa").remove();
				$("body").append(str);
				$("#aa")[0].click();

			} else {
				alert("真实性权重调整或者偏离超标性调整必须为数字，请修改为数字");
			}
		} else {
			alert("真实性权重总和不等于100，或大于小于100，请修改");
		}
	} else {
		alert("超标基站整改不大于40，或小于0，请修改");
	}

}
$(function() {

	$("#query").click(function() {
		chaxun();
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
function changetime() {
	var bzmonth = document.form1.bzmonth.value;
	document.form1.action = path + "/web/tongjichaxun/cbjzGl.jsp?bzmonth="
			+ bzmonth;
	document.form1.submit();
}
function changemonth() {
	var bzmonth = document.form1.bzmonth.value;
	document.form1.bzyf.value = bzmonth;
	//alert(bzmonth);
}
</script>

	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>

	<jsp:useBean id="commBean1" scope="page"
		class="com.noki.mobi.common.CommonAccountBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<%
		permissionRights = commBean.getPermissionRight(roleId, "0101");
		System.out.println(">>>>>>>>>>>>>>..." + permissionRights);
	%>
	<body class="body" style="overflow-x: hidden;" onload="waitThenDoIt()">
		<LINK href="../../images/css.css" type=text/css rel=stylesheet>
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
				<tr>
					<td width="50px">
						<div style="width: 700px;">
							<img alt="" src="<%=path%>/images/btt.bmp" width="100%"
								height="100%" />
							<span
								style="font-size: 14px; font-weight: bold; position: absolute; left: 25px; top: 15px; color: black">基础数据真实性管理</span>
						</div>
					</td>
				</tr>

				<tr>
					<td height="20">
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
					<td colspan="11">
						<table>
							<tr class="form_label">

								<td>
									<input type="hidden" name="shi" id="ccz" value="0" />
								</td>

								<td class="form_label">
									标准月份：
								</td>
								<td class="form_label">
									<input type="text" name="bzmonth" value="<%=bzmonth%>"
										onFocus="getDatenyString(this,oCalendarChsny)"
										onpropertychange="changemonth()" style="width: 130px;" />
								</td>
								<td class="form_label">
									报账月份：
								</td>
								<td class="form_label">
									<input type="text" name="bzyf" value="<%=bzyf%>"
										onpropertychange="changetime()"
										onFocus="getDatenyString(this,oCalendarChsny)"
										style="width: 130px;" />
								</td>
								<td class="form_label">站点属性：</td>
         						<td><select name="jzproperty" class="selected_font">
				         		<option value="0">请选择</option>
				         		<%
					         	ArrayList zdsx = new ArrayList();
					         	zdsx = ztcommon.getSelctOptions("zdsx");
					         	if(zdsx!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)zdsx.get(0)).intValue();
					         		for(int i=cscount;i<zdsx.size()-1;i+=cscount)
				                    {
				                    	code=(String)zdsx.get(i+zdsx.indexOf("CODE"));
				                    	name=(String)zdsx.get(i+zdsx.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         		%>
				         		</select></td>
								<td class="form_label">
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
										style="position: relative; width: 59px; height: 23px; right: -250px; cursor: pointer; TOP: 0PX">
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
				<%
					if (bz == "1" || "1".equals(bz)) {
				%>
				<tr>
					<td colspan="11">
						<div style="width: 99%;">
							<p id="box3" style="display: none">
								<table>
									<tr class="form_label">
										<td class="form_label">
											真实性权重调整：
										</td>
										<td class="form_label">
											属性错误
										</td>
										<td>
											<input type="text" name="zdsxcw" class="text_font"
												readonly="readonly"
												value="<%if (null != request.getParameter("zdsxcw"))
					out.print(request.getParameter("zdsxcw"));
				else
					out.print(1);%>" />
											%
										</td>
										<td class="form_label">
											分摊
										</td>
										<td>
											<input type="text" name="scnhft" class="text_font"
												readonly="readonly"
												value="<%if (null != request.getParameter("scnhft"))
					out.print(request.getParameter("scnhft"));
				else
					out.print(1);%>" />
											%
										</td>
										<td class="form_label">
											抄表管控
										</td>
										<td>
											<input type="text" name="mycbgk" class="text_font"
												readonly="readonly"
												value="<%if (null != request.getParameter("mycbgk"))
					out.print(request.getParameter("mycbgk"));
				else
					out.print(1);%>" />
											%
										</td>
										<td class="form_label">
											超标站点
										</td>
										<td>
											<input type="text" name="cbzd" class="text_font"
												readonly="readonly"
												value="<%if (null != request.getParameter("cbzd"))
					out.print(request.getParameter("cbzd"));
				else
					out.print(1);%>" />
											%
										</td>
										<td class="form_label">
											结算电量超标
										</td>
										<td>
											<input type="text" name="mycbjscb" class="text_font"
												readonly="readonly"
												value="<%if (null != request.getParameter("mycbjscb"))
					out.print(request.getParameter("mycbjscb"));
				else
					out.print(1);%>" />
											%
										</td>
										<td class="form_label">
											连续超标
										</td>
										<td>
											<input type="text" name="lxcb" class="text_font"
												readonly="readonly"
												value="<%if (null != request.getParameter("lxcb"))
					out.print(request.getParameter("lxcb"));
				else
					out.print(1);%>" />
											%
										</td>
										<td class="form_label">
											连续超标且电量增加
										</td>
										<td>
											<input type="text" name="lxcbdlzj" class="text_font"
												readonly="readonly"
												value="<%if (null != request.getParameter("lxcbdlzj"))
					out.print(request.getParameter("lxcbdlzj"));
				else
					out.print(1);%>" />
											%
										</td>
									</tr>

									<tr class="form_label">
										<td class="form_label">
											偏离超标性调整：
										</td>
										<td class="form_label">
											偏离度：
										</td>
										<td>
											<input type="text" name="pld" class="text_font"
												readonly="readonly"
												value="<%if (null != request.getParameter("pld"))
					out.print(request.getParameter("pld"));
				else
					out.print("10");%>" />
											%
										</td>

										<td class="form_label">
											超标比例：
										</td>
										<td>
											<input type="text" name="cbbl" class="text_font"
												readonly="readonly"
												value="<%if (null != request.getParameter("cbbl"))
					out.print(request.getParameter("cbbl"));
				else
					out.print("10");%>" />
											%
										</td>
										<td class="form_label">
											超标基站整改：
										</td>
										<td>
											<input type="text" name="cbjzzg" class="text_font"
												readonly="readonly"
												value="<%if (null != request.getParameter("cbjzzg"))
					out.print(request.getParameter("cbjzzg"));
				else
					out.print(1);%>" />
										</td>
									</tr>

								</table>
							</p>
						</div>
					</td>

				</tr>
				<%
					} else {
				%>
				<tr>
					<td colspan="11">
						<div style="width: 99%;">
							<p id="box3" style="display: none">
								<table>
									<tr class="form_label">
										<td class="form_label">
											真实性权重调整：
										</td>
										<td class="form_label">
											属性错误
										</td>
										<td>
											<input type="text" name="zdsxcw" class="text_font"
												value="<%if (null != request.getParameter("zdsxcw"))
					out.print(request.getParameter("zdsxcw"));
				else
					out.print(1);%>" />
											%
										</td>
										<td class="form_label">
											分摊
										</td>
										<td>
											<input type="text" name="scnhft" class="text_font"
												value="<%if (null != request.getParameter("scnhft"))
					out.print(request.getParameter("scnhft"));
				else
					out.print(1);%>" />
											%
										</td>
										<td class="form_label">
											抄表管控
										</td>
										<td>
											<input type="text" name="mycbgk" class="text_font"
												value="<%if (null != request.getParameter("mycbgk"))
					out.print(request.getParameter("mycbgk"));
				else
					out.print(1);%>" />
											%
										</td>
										<td class="form_label">
											超标站点
										</td>
										<td>
											<input type="text" name="cbzd" class="text_font"
												value="<%if (null != request.getParameter("cbzd"))
					out.print(request.getParameter("cbzd"));
				else
					out.print(1);%>" />
											%
										</td>
										<td class="form_label">
											结算电量超标
										</td>
										<td>
											<input type="text" name="mycbjscb" class="text_font"
												value="<%if (null != request.getParameter("mycbjscb"))
					out.print(request.getParameter("mycbjscb"));
				else
					out.print(1);%>" />
											%
										</td>
										<td class="form_label">
											连续超标
										</td>
										<td>
											<input type="text" name="lxcb" class="text_font"
												value="<%if (null != request.getParameter("lxcb"))
					out.print(request.getParameter("lxcb"));
				else
					out.print(1);%>" />
											%
										</td>
										<td class="form_label">
											连续超标且电量增加
										</td>
										<td>
											<input type="text" name="lxcbdlzj" class="text_font"
												value="<%if (null != request.getParameter("lxcbdlzj"))
					out.print(request.getParameter("lxcbdlzj"));
				else
					out.print(1);%>" />
											%
										</td>
									</tr>

									<tr class="form_label">
										<td class="form_label">
											偏离超标性调整：
										</td>
										<td class="form_label">
											偏离度：
										</td>
										<td>
											<input type="text" name="pld" class="text_font"
												value="<%if (null != request.getParameter("pld"))
					out.print(request.getParameter("pld"));
				else
					out.print("10");%>" />
											%
										</td>

										<td class="form_label">
											超标比例：
										</td>
										<td>
											<input type="text" name="cbbl" class="text_font"
												value="<%if (null != request.getParameter("cbbl"))
					out.print(request.getParameter("cbbl"));
				else
					out.print("10");%>" />
											%
										</td>
										<td class="form_label">
											超标基站整改：
										</td>
										<td>
											<input type="text" name="cbjzzg" class="text_font"
												value="<%if (null != request.getParameter("cbjzzg"))
					out.print(request.getParameter("cbjzzg"));
				else
					out.print(1);%>" />
										</td>
									</tr>

								</table>
							</p>
						</div>
					</td>

				</tr>
				<%
					}
				%>
			</table>

			<table>
				<tr>
					<td height="5" colspan="4" class="form_label">
						<div id="parent" style="display: inline">
						</div>
						<div style="width: 50px; display: inline;">
							<hr>
						</div>
						&nbsp;信息列表&nbsp;
						<div style="width: 300px; display: inline;">
							<hr>
						</div>

					</td>
				</tr>
			</table>
		</form>


		<iframe name="test" name="treeMap" width="100%" height="100%"
			frameborder="0" src="<%=path%>/web/tongjichaxun/cbjzGlIfram.jsp"></iframe>
		<br />


	</body>
</html>

<script language="javascript">

var path = '<%=path%>';
function changeShi() {
	var shi = document.form1.shi.value;
	if (shi == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/area?action=shi&pid=" + shi, "shi");
	}
}

function updateQx(res) {
	var shilist = document.all.xian;
	shilist.options.length = "0";
	shilist.add(new Option("请选项", "0"));

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

document.form1.shi.value = '<%=shi%>';//sptype

var fylist = '<%=fylist%>';
if (fylist != null && fylist.length > 2) {
	document.form1.zdsxcw.value = '<%=zdsxcw%>';
	document.form1.scnhft.value = '<%=scnhft%>';
	document.form1.mycbgk.value = '<%=mycbgk%>';
	document.form1.cbzd.value = '<%=cbzd%>';
	document.form1.mycbjscb.value = '<%=mycbjscb%>';
	document.form1.lxcb.value = '<%=lxcb%>';
	document.form1.lxcbdlzj.value = '<%=lxcbdlzj%>';
	document.form1.pld.value = '<%=pld%>'
	document.form1.cbbl.value = '<%=cbbl%>'
	document.form1.cbjzzg.value = '<%=cbjzzg%>'
}
</script>
