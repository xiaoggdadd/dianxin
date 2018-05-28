<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.regex.Pattern"%>

<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account"); 
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
	String bztime = request.getParameter("bztime") != null ? request
			.getParameter("bztime") : "";
	String lrrq = request.getParameter("lrrq") != null ? request
			.getParameter("lrrq") : "";
	String beginTime = request.getParameter("beginTime") != null ? request
			.getParameter("beginTime")
			: CTime.formatRealDate(new Date());
	String endTime = request.getParameter("endTime") != null ? request
			.getParameter("endTime") : CTime.formatRealDate(new Date());
	//System.out.println("logManage.jsp>>"+beginTime);
	String title = request.getParameter("title") != null ? request
			.getParameter("title") : "";
	String operName = request.getParameter("operName") != null ? request
			.getParameter("operName")
			: "";
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
	String zdlx = request.getParameter("zdlx") != null ? request
			.getParameter("zdlx") : "0";
	String jzproperty = request.getParameter("jzproperty") != null ? request
			.getParameter("jzproperty")
			: "0";
	String zdname = request.getParameter("zdname") != null ? request
			.getParameter("zdname") : "0";
	String cityaudit1 = request.getParameter("cityaudit") != null ? request
			.getParameter("cityaudit")
			: "0";
	String zdqyzt=request.getParameter("zdqyzt")!=null?request.getParameter("zdqyzt"):"1";
	String dbqyzt=request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"1";
	// String citysh = request.getParameter("rgsh1");
	String loginId1 = request.getParameter("loginId");

	// System.out.println("审核"+citysh);
	//  if(citysh!=null){
	// 	cityaudit1=citysh;
	//  }
	/*String canshuStr = "";
	if ((shi != null) || (xian != null) || (xiaoqu != null)
			|| ((beginTimeQ != null) && (endTimeQ != null))
			|| (zdname != null) || (sptype != null) || (zdlx != null)
			|| (jzproperty != null) || (cityaudit1 != null)) {
		canshuStr = "shi=" + shi + "&xian=" + xian + "&xiaoqu="
				+ xiaoqu + "&beginTimeQ=" + beginTimeQ + "&endTimeQ="
				+ endTimeQ + "&zdname=" + zdname + "&sptype=" + sptype
				+ "&zdlx=" + zdlx + "&jzproperty=" + jzproperty
				+ "&cityaudit1=" + cityaudit1;
	}*/
	//String roleId = account.getRoleId();
	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String permissionRights = "";
	String color = null;
	String roleId = (String) session.getAttribute("accountRole");
	double ddf = 0.0;
	double ddfs = 0.0;
	//System.out.println("roleId:"+roleId);
%>

<html>
	<head>
		<title></title>
		<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}

.STYLE6 {
	color: #FFFFFF;
}

.memo {
	border: 1px # #888888 solid
}

.style7 {
	font-weight: bold
}

.memo {
	border: 1px #888888 solid
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

.relativeTag1 {
	z-index: 400;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}
</style>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
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

function chaxun() {
	var beginTime = document.form1.beginTime.value
	if (checkNotnull(document.form1.beginTime, "开始时间")
			&& checkNotnull(document.form1.endTime, "结束时间")) {
		document.form1.action = path + "/web/sys/logManage.jsp?beginTime="
				+ beginTime
		document.form1.submit()
	}
}

//审核通过
/*	function passCheck(){
 var m = document.getElementsByName('test[]');
 var arr = new Array
 var l = m.length; 
 var chooseIdStr1 = ""; 
 var chooseIdStr2 = ""; 
 var bz=0;   
 for(var i = 0; i < l; i++){
 if(m[i].checked == true){
 bz+=1;
 var j = m[i].value.toString().indexOf("ssss=");
 var chooseIdStr3 = m[i].value.toString().substring(0,j);
 var zflx1 = m[i].value.toString().substring(j+5,m[i].value.toString().length);
 if(zflx1=="月结"){
 chooseIdStr1 = chooseIdStr1 +"'" +chooseIdStr3 +"',";
 }else if(zflx1=="预支"){
 chooseIdStr1 = chooseIdStr1 +"'" + chooseIdStr3 +"',";
 chooseIdStr2 = chooseIdStr2 +"'" + chooseIdStr3 +"',";
 }else if(zflx1=="合同"||zflx1=="插卡"){
 chooseIdStr2 = chooseIdStr2 +"'" + chooseIdStr3 +"',";
 } 
 }             
 } 
 chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
 chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
 alert(chooseIdStr1);
 if(bz<=240){ 
 if(bz>=1){        	
 document.form1.action=path+"/servlet/check?action=checkcity&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2;
 document.form1.submit();
 }else{
 alert("请选择信息！");
 }
 }else{
 alert("您选择信息条数超过240条，信息量过大，请确定后重新执行！");
 } 
 }*/
function passCheck() {
	var m = document.getElementsByName('test[]');
	var arr = new Array
	var l = m.length;
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var bzw = 1;
	var bzw1 = "";
	var count2 = 0;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	
	//判断选择了多少条数
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
	}
	if (count != 0) {
		
		//最大只能传240条数据   所以取240的倍数
		if (count % 240 == 0) {
			n = count / 240 - 1;
		} else {
			n = (count - (count % 240)) / 240;
		}
		for ( var i = 0; i < l; i++) {
			//取出选择数据的uuid并且分配好在那个sql执行
			if (m[i].checked == true) {
				bz += 1;
				count1 += 1;//选择的条数
				var j = m[i].value.toString().indexOf("ssss=");
				var chooseIdStr3 = m[i].value.toString().substring(0, j);//截取uuid
				var zflx1 = m[i].value.toString().substring(j + 5,    //截取电费支付类型
						m[i].value.toString().length);
				if (zflx1 == "月结") {
					chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
				} else if (zflx1 == "预支") {
					count2 += 1;
					chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
					chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
				} else if (zflx1 == "合同" || zflx1 == "插卡") {
					chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
				}
			}
			bzw1 = document.form1.bzw1.value;
			if (bzw1 == "1") {
				if (count1 <= 240 * n
						|| ((bz + count2) >= 239 && (bz + count2) <= 241)) {
					if (((bz + count2) / 240 == 1)
							|| ((bz + count2) >= 239 && (bz + count2) <= 241)) {
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);//去除最后一个逗号
						chooseIdStr2 = chooseIdStr2.substring(0,
								chooseIdStr2.length - 1);
						//document.form1.action=path+"/servlet/check?action=checkcity1&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2;
						passCheck1(chooseIdStr1, chooseIdStr2);
						chooseIdStr1 = "";
						chooseIdStr2 = "";
						bz = 0;
						count2 = 0;
						//document.form1.submit();	          	
					}
				} else if (count == count1 && bzw == 1) {
					chooseIdStr1 = chooseIdStr1.substring(0,
							chooseIdStr1.length - 1);
					chooseIdStr2 = chooseIdStr2.substring(0,
							chooseIdStr2.length - 1);
					bzw = 0;
					document.form1.action = path
							+ "/servlet/check?action=checkcity&chooseIdStr1="
							+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
					document.form1.submit();
				}
			} else if (bzw1 == "0") {
				document.form1.action = path + "/web/msg.jsp";
				document.form1.submit();
				return;
			}
		}
	} else {
		alert("请选择信息！");
	}
}

function queryDegree() {
	if (document.getElementById("shi").value == ""
			|| document.getElementById("shi").value == "0"
			|| document.getElementById("shi").value == null) {
		alert("城市不能为空");

	} else {

		document.form1.action = path
				+ "/web/check/checkFeesCity.jsp?command=chaxun";
		document.form1.submit();

	}
}
$(function() {
	$("#daochuBtn").click(function() {
		exportad();

	});

	$("#tongguo").click(function() {
		passCheck();
		showdiv("请稍等..............");
	});

	$("#butongguo").click(function() {
		passCheckNoPass();
		showdiv("请稍等..............");
	});
	$("#chaxun").click(function() {
		queryDegree();
		showdiv("请稍等..............");
	});
	$("#quxiao").click(function() {
		passCheckNo();
		showdiv("请稍等..............");
	});
});
</script>

	</head>

	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<%
		permissionRights = commBean.getPermissionRight(roleId, "0804");

		//System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
	%>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
				<tr>
					<td colspan="4">
							<div style="width:700px;height:50px">
							  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">市级电费审核</span>	
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
									<%
										if (permissionRights.indexOf("PERMISSION_SEARCH") >= 0) {
									%>
									<%
										}
									%>
									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -240px; float: right; top: 0px">
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

			<div style="width: 88%;">
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
								站点类型：
							</td>
							<td>
								<select name="zdlx" class="selected_font">
									<option value="0">
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
									<%
										}
										}
									%>
								</select>
							</td>
							<td>
								站点属性：
							</td>
							<td>
								<select name="jzproperty" class="selected_font"
									onchange="kzinfo()">
									<option value="0">
										请选择
									</option>
									<%
										ArrayList zdsx = new ArrayList();
										zdsx = ztcommon.getSelctOptions("zdsx");
										if (zdsx != null) {
											String code = "", name = "";
											int cscount = ((Integer) zdsx.get(0)).intValue();
											for (int i = cscount; i < zdsx.size() - 1; i += cscount) {
												code = (String) zdsx.get(i + zdsx.indexOf("CODE"));
												name = (String) zdsx.get(i + zdsx.indexOf("NAME"));
									%>
									<option value="<%=code%>"><%=name%></option>
									<%
										}
										}
									%>
								</select>
							</td>
							<td>
								电表启用状态：
							</td>
							<td>
								<select name="dbqyzt" class="selected_font">

									<option value="-1">
										请选择
									</option>
									<option value="1">
										启用
									</option>
									<option value="0">
										未启用
									</option>
								</select>
							</td>
						</tr>
						
						<tr class="form_label">
							<td>
								市级审核状态：
							</td>
							<td>
								<select name="cityaudit" class="selected_font"
									onchange="javascript:document.form1.cityaudit2.value=document.form1.cityaudit.value">

									<option value="-1">
										请选择
									</option>
									<option value="1">
										审核通过
									</option>
									<option value="0">
										审核未通过
									</option>
									<option value="-2">
										审核不通过
									</option>
								</select>
							</td>
							<td>
								报账月份：
							</td>
							<td>
								<input type="text" class="selected_font" name="bztime"
									value="<%if (null != request.getParameter("bztime"))
				out.print(request.getParameter("bztime"));%>"
									onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
							</td>
							<td>
								录入日期：
							</td>
							<td>
								<input type="text" name="lrrq"
									value="<%if (null != request.getParameter("lrrq"))
				out.print(request.getParameter("lrrq"));%>"
									onFocus="getDateString(this,oCalendarChs)"
									class="selected_font" />
							</td>
							<td>
								站点启用状态：
							</td>
							<td>
								<select name="zdqyzt" class="selected_font">

									<option value="-1">
										请选择
									</option>
									<option value="1">
										启用
									</option>
									<option value="0">
										未启用
									</option>
								</select>
							</td>
						</tr>
						
					</table>
				</p>
			</div>

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
				ElectricFeesBean bean = new ElectricFeesBean();

				double dfdfd = 0;
				String whereStr = "";
				String str = "";
				//System.out.println("dianliangListjsp:"+zdname+beginTimeQ+endTimeQ);

				if (shi != null && !shi.equals("") && !shi.equals("0")) {
					whereStr = whereStr + " and zd.shi='" + shi + "'";
					str = str + " and zd.shi='" + shi + "'";
				}
				if (xian != null && !xian.equals("") && !xian.equals("0")) {
					whereStr = whereStr + " and zd.xian='" + xian + "'";
					str = str + " and zd.xian='" + xian + "'";
				}
				if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
					whereStr = whereStr + " and zd.xiaoqu='" + xiaoqu + "'";
					str = str + " and zd.xiaoqu='" + xiaoqu + "'";
				}
				if (zdname != null && !zdname.equals("") && !zdname.equals("0")) {

					whereStr = whereStr + " and zd.jzname  like '%" + zdname + "%'";
				}
				if (lrrq != null && !lrrq.equals("") && !lrrq.equals("null")) {

					whereStr = whereStr + " and to_char(ef.entrytime,'yyyy-mm-dd')  like '%" + lrrq
							+ "%'";
				}
				if (bztime != null && bztime != "" && !bztime.equals("0")) {
					whereStr = whereStr + " and to_char(ef.ACCOUNTMONTH,'yyyy-mm') ='" + bztime + "'";
					str = str + " and to_char(ef.ACCOUNTMONTH,'yyyy-mm') ='" + bztime + "'";
				}

				String cityaudit3 = cityaudit1;
				if (cityaudit3 != null && !cityaudit3.equals("")
						&& !cityaudit3.equals("-1")) {
					whereStr = whereStr + " and ef.cityaudit='" + cityaudit3 + "'";
					str = str + " and ef.cityaudit='" + cityaudit3 + "'";
				}
				if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
					whereStr = whereStr + " and zd.STATIONTYPE='" + zdlx + "'";
					str = str + " and zd.STATIONTYPE='" + zdlx + "'";
				}
				if (jzproperty != null && !jzproperty.equals("")
						&& !jzproperty.equals("0")) {
					whereStr = whereStr + " and zd.PROPERTY='" + jzproperty + "'";
					str = str + " and zd.PROPERTY='" + jzproperty + "'";
				}
				if(zdqyzt != null && !zdqyzt.equals("") && !zdqyzt.equals("-1")){
					whereStr = whereStr + " and zd.qyzt='" + zdqyzt + "'";
					str = str + " and zd.qyzt='" + zdqyzt + "'";
				}
				if(dbqyzt != null && !dbqyzt.equals("") && !dbqyzt.equals("-1")){
					whereStr = whereStr + " and d.dbqyzt='" + dbqyzt + "'";
					str = str + " and d.dbqyzt='" + dbqyzt + "'";
				}

				//String str1="";
				//String str2="  AND ef.CITYAUDIT='0' ";
				//String str3=" AND ef.CITYAUDIT='1'  ";
				if (loginId1 != null && !loginId1.equals("")) {
					loginId = loginId1;
					shi = "1";
				}
				String count1 = "0", count2 = "0", count3 = "0", count4 = "0.00";

				//  	if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
				//  	 ElectricFeesBean beana=new ElectricFeesBean();
				//	 List<ElectricFeesFormBean> list=beana.getCounttt(whereStr,loginId);
				//	    for(ElectricFeesFormBean ls:list){
				//		   		count1=ls.getCountts();
				//		     	count2=ls.getTg();
				//      	count3=ls.getWtg();
				//		       	count4=ls.getSummoney();
				//    }
				// DecimalFormat matt=new DecimalFormat("0.00");
				//    dfdfd=Double.parseDouble(count4);
				//    count4= matt.format(dfdfd);
			%>
			<!--  	<table  height="5%">
          <tr>
  	        <td><font size="2">总共</font></td><td><font size="2" color="red"><%=count1%></font><font size="2">条  |</font></td>
  	         <td><font size="2">审核通过</font></td><td><font size="2" color="red"><%=count2%></font><font size="2">条  |</font></td>
  	          <td><font size="2">审核未通过</font></td><td><font size="2" color="red"><%=count3%></font><font size="2">条  |</font></td>
  	           <td><font size="2">实际电费金额总和</font></td><td><font size="2" color="red"><%=count4%></font><font size="2">元  |</font></td>
  	      </tr>
		</table>
		-->

			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="2000px" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<input type="checkbox" name="test" onclick="chooseAll()" />
								全选
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								所在地区
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								实际电费金额
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								额定电费
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								额定耗电量(度/天)
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								实际用电量
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电量超标比例
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								全省定标电量超标比例
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								结算周期
							</div>
						</td>

						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账月份
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点属性
							</div>
						</td>

						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电费支付类型
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								线损类型
							</div>
						</td>
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								线损值
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								直流负荷
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								交流负荷
							</div>
						</td>



						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								倍率
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								票据类型
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								票据编号
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								票据金额
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								自动审核状态
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								人工审核状态
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								二级审核状态
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表ID
							</div>
						</td>
					</tr>
					<%
						//System.out.println("ElectricFeesBean-whereStr:"+whereStr);

						List<ElectricFeesFormBean> list = null;

						if ("daochu".equals(request.getParameter("command"))
								|| "chaxun".equals(request.getParameter("command"))) {
							list = bean.getPageDataCheckCity(whereStr, loginId);
						} else {
							list = null;
						}
						String electricfeeId = "", szdq = "", cbbl = "", floatpay = "", actualpay = "", notetypeid = "",pjje="", noteno = "", dfzflx = "", bzyf = "";
						String jzname = "", dbid = "", thisdatatime = "", lastdatatime = "", autoauditstatus = "", edhdl = "", manualauditstatus = "", cityaudit = "", dbyt = "";
						Double df = 0.00;
						//=================新增==========
						String zdsxx = "";
						String zdlxx = "";
						String unitprice = "", linelosstype = "", blhdl = "", dfbzw = "", linelossvalue = "", beilv = "", uuid = "", zdcode = "", dedhdl = "", zlfh = "", jlfh = "", zq = "", dfzflx2 = "";
						String qsdbdl = "";//全省定标电量
						double dbdl = 0.00;
						//===============xin===========
						int intnum = xh = pagesize * (curpage - 1) + 1;
						double dfdf = 0;

						if (list != null) {
							for (ElectricFeesFormBean listt : list) {
								jzname = listt.getJzname();
								zdcode = listt.getZdcode();
								szdq = listt.getSzdq();
								bzyf = listt.getAccountmonth();
								cbbl = listt.getCbbl();
								zdsxx = listt.getProperty();//站点属性
								zdlxx = listt.getStationtype();
								dbid = listt.getDbid();
								edhdl = listt.getEdhdl();
								autoauditstatus = listt.getAutoauditstatus();
								manualauditstatus = listt.getManualauditstatus();
								dfzflx2 = listt.getDfzflx();
								dfbzw = listt.getDfbzw();
								blhdl = listt.getBlhdl();
								if (blhdl == null || blhdl == "" || blhdl == "o"
										|| "null".equals(blhdl))
									blhdl = "0";
								electricfeeId = listt.getElectricfeeId();// electricfeeId = (String)fylist.get(k+fylist.indexOf("ELECTRICFEE_ID"));
								uuid = listt.getDfuuid();
								linelosstype = listt.getLinelosstype();
								linelossvalue = listt.getLinelossvalue();
								beilv = listt.getBeilv();
								floatpay = listt.getFloatpay();
								actualpay = listt.getActualpay();
								notetypeid = listt.getNotetypeid();//
								noteno = listt.getNoteno();
								pjje=listt.getPjjedf();
								cityaudit = listt.getCityaudit();
								unitprice = listt.getUnitprice();
								dedhdl = listt.getDedhdl();
								zlfh = listt.getZlfh();
								jlfh = listt.getJlfh();
								zq = listt.getJszq();
								qsdbdl = listt.getQsdbdl();
								if (" ".equals(qsdbdl) || "null".equals(qsdbdl)
										|| qsdbdl == null) {
									qsdbdl = "";
								}
								if ("".equals(zlfh) || "null".equals(zlfh) || zlfh == null) {
									zlfh = "";
								}
								if ("".equals(jlfh) || "null".equals(jlfh) || jlfh == null) {
									jlfh = "";
								}
								if ("".equals(zq) || "null".equals(zq) || zq == null) {
									zq = "";
								}
								if ("".equals(dedhdl) || "null".equals(dedhdl)
										|| dedhdl == null) {
									dedhdl = "";
								}
								if (unitprice == null || unitprice == ""
										|| unitprice == "o" || unitprice == "null"
										|| "null".equals(unitprice)) {
									unitprice = "0";
								}
								if ("".equals(zdsxx) || "null".equals(zdsxx)
										|| zdsxx == null) {
									zdsxx = "";
								}
								if ("".equals(zdlxx) || "null".equals(zdlxx)
										|| zdlxx == null) {
									zdlxx = "";
								}
								if ("".equals(dfzflx2) || "null".equals(dfzflx2)
										|| dfzflx2 == null) {
									dfzflx2 = "";
								}
								if ("".equals(cbbl) || "null".equals(cbbl) || cbbl == null) {
									cbbl = "0";
								}
								if ("".equals(edhdl) || "null".equals(edhdl)
										|| edhdl == null) {
									edhdl = "0";
								}
								/*  if("月结".equals(dfzflx)){
								 	 dfzflx2="dfzflx01";
								  }else if("合同".equals(dfzflx)){
								 	 dfzflx2="dfzflx02";
								  }else if("预支".equals(dfzflx)){
								 	 dfzflx2="dfzflx03";
								  }else if("插卡".equals(dfzflx)){
								 	 dfzflx2="dfzflx04";
								  }else{
								   	 dfzflx2="";
								  }*/

								//添加 单价  线损类型   线损值   倍率
								//unitprice = (String)fylist.get(k+fylist.indexOf("UNITPRICE"));
								//unitprice = unitprice != null ? unitprice : "";

								if ("null".equals(linelosstype) || linelosstype == null) {
									linelosstype = "";
								}

								if ("null".equals(linelossvalue) || linelossvalue == null) {
									linelossvalue = "";
								}
								if (beilv == null || beilv == "" || beilv == "o")
									beilv = "0";
								DecimalFormat ma = new DecimalFormat("0.00");
								df = Double.parseDouble(beilv);

								beilv = ma.format(df);
								double cb = 0;
								cb = Double.parseDouble(cbbl);
								cb = cb * 100;
								cbbl = ma.format(cb);
								floatpay = floatpay != null ? floatpay : "";
								if ("null".equals(floatpay) || "".equals(floatpay)
										|| floatpay == null) {
									floatpay = "0";
								}
								actualpay = actualpay != null ? actualpay : "";
								if ("null".equals(actualpay) || "".equals(actualpay)
										|| actualpay == null) {
									actualpay = "0";
								}
								notetypeid = notetypeid != null ? notetypeid : "";
								noteno = noteno != null ? noteno : "";
								if ("null".equals(noteno)) {
									noteno = "";
								}
								if ("null".equals(notetypeid)) {
									notetypeid = "";
								}
								pjje=pjje!=null?pjje:"";
								if ("null".equals(pjje)) {
									notetypeid = "";
								}
								cityaudit = cityaudit != null ? cityaudit : "";
								DecimalFormat mat = new DecimalFormat("0.00");
								if (actualpay == null || actualpay == "")
									actualpay = "0";
								df = Double.parseDouble(actualpay);
								actualpay = mat.format(df);
								thisdatatime = listt.getThisdatetime();
								lastdatatime = listt.getLastdatetime();
								SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
								String dfsl = "0";
								double shu;
								double shu1;
								double sjydl;
								double dd, ff;
								double dbdl1;
								String dlbl = "0";
								long quot;
								if ((lastdatatime != null && !"0".equals(lastdatatime)
										&& !"null".equals(lastdatatime)
										&& !lastdatatime.equals("") && lastdatatime != "")
										&& (thisdatatime != null
												&& !"0".equals(thisdatatime)
												&& !"null".equals(thisdatatime)
												&& !thisdatatime.equals("") && thisdatatime != "")) {
									String startn = lastdatatime.substring(0, 4);
									String endn = thisdatatime.substring(0, 4);
									String sgang = lastdatatime.substring(4, 5);
									String egang = thisdatatime.substring(4, 5);

									Pattern pattern = Pattern.compile("[0-9]*");//判断前四位是否是数字
									if (pattern.matcher(startn).matches() == true
											&& pattern.matcher(endn).matches() == true
											&& sgang.equals("-") && egang.equals("-")) {
										if (lastdatatime.length() >= 8
												&& thisdatatime.length() >= 8) {
											Date date1 = ft.parse(lastdatatime);
											Date date2 = ft.parse(thisdatatime);
											quot = date2.getTime() - date1.getTime();

											quot = quot / 1000 / 60 / 60 / 24 + 1;//计算天数
											shu = Double.parseDouble(edhdl)
													* Double.parseDouble(unitprice) * quot;//计算额定电费
											DecimalFormat eddf = new DecimalFormat("0.00");
											dfsl = eddf.format(shu);//格式化额定电费
											shu1 = Double.parseDouble(edhdl) * quot;// 额定电量
											sjydl = Double.parseDouble(blhdl);// 实际用电量
											//if(shu1!=0){
											//dd=((sjydl-shu1)/shu1)*100;
											//	}else{
											//	dd=0;
											//	}
											//System.out.println("blhdl:"+blhdl+" qsdbdl:"+qsdbdl+" quot:"+quot);
											DecimalFormat we = new DecimalFormat("0.00");
											if (!"".equals(qsdbdl)) {

												dbdl1 = Double.parseDouble(qsdbdl) * quot;//全省定标电量
												//  System.out.println("sjydl"+sjydl+"qsdbdl"+qsdbdl+"quot"+quot);
												dbdl = ((sjydl - dbdl1) / dbdl1) * 100;//（实际用电量-（全省定标电量*周期））/（全省定标电量*周期）      预付费的不进行公式计算有就显示省定标没有就不显示
												qsdbdl = dbdl + "";
												qsdbdl = we.format(Double
														.parseDouble(qsdbdl));

											}

											//  dlbl=we.format(dd);
										}
									}
								} else {
									dfsl = "0.00";
								}
								DecimalFormat ww = new DecimalFormat("0.00");
								edhdl = ww.format(Double.parseDouble(edhdl));

								if (intnum % 2 == 0) {
									color = "#DDDDDD";

								} else {
									color = "#FFFFFF";
								}
								intnum++;
								ddf = ddf + Double.parseDouble(blhdl);
								ddfs = ddfs + Double.parseDouble(actualpay);
								if (blhdl.equals("0")) {
									blhdl = "";
								}

								if (notetypeid.equals("pjlx05")) {
									notetypeid = "收据";
								} else if (notetypeid.equals("pjlx03")) {
									notetypeid = "发票";
								} else {
									notetypeid = "";
								}
								if (zdsxx.equals("zdsx01")) {
									zdsxx = "局用机房";
								} else if (zdsxx.equals("zdsx02")) {
									zdsxx = "基站";
								} else if (zdsxx.equals("zdsx03")) {
									zdsxx = "营业网点";
								} else if (zdsxx.equals("zdsx04")) {
									zdsxx = "其他";
								} else if (zdsxx.equals("zdsx05")) {
									zdsxx = "接入网";
								} else if (zdsxx.equals("zdsx06")) {
									zdsxx = "乡镇支局";
								} else {
									zdsxx = "";
								}
								if (linelosstype.equals("02xsbl")) {
									linelosstype = "线损比例";
								} else if (linelosstype.equals("01xstz")) {
									linelosstype = "线损调整";
								} else {
									linelosstype = "";
								}
								if (dfzflx2.equals("dfzflx01")) {
									dfzflx = "月结";
								} else if (dfzflx2.equals("dfzflx02")) {
									dfzflx = "合同";
								} else if (dfzflx2.equals("dfzflx03")) {
									dfzflx = "预支";
								} else if (dfzflx2.equals("dfzflx04")) {
									dfzflx = "插卡";
								} else {
									dfzflx = "";
								}
								//System.out.println("---"+dfzflx+"---"+dfzflx2);
					%>

					<tr bgcolor="<%=color%>">
						<td>
							<div align="center"><%=intnum - 1%></div>
						</td>
						<td>
							<div align="center">
								<input type="checkbox" name="test[]"
									value="<%=uuid%>ssss=<%=dfzflx%>" />
							</div>
						</td>
						<td>
							<div align="left">
								<a
									href="javascript:lookDetailss('<%=dbid%>','<%=bzyf%>','<%=dfzflx2%>','<%=dfbzw%>')"><%=jzname%></a>
							</div>
						</td>
						<td>
							<div align="left"><%=szdq%></div>
						</td>
						<td>
							<div align="right"><%=actualpay%></div>
						</td>
						<td>
							<div align="right"><%=dfsl%></div>
						</td>
						<td>
							<div align="right"><%=edhdl%></div>
						</td>
						<td>
							<div align="right"><%=blhdl%></div>
						</td>
						<td>
							<div align="right"><%=dedhdl%>%
							</div>
						</td>
						<td>
							<div align="right"><%=qsdbdl%>%
							</div>
						</td>
						<td>
							<div align="right"><%=zq%></div>
						</td>
						<td>
							<div align="right"><%=bzyf%></div>
						</td>
						<td>
							<div align="center"><%=zdsxx%></div>
						</td>
						<td>
							<div align="center"><%=zdlxx%></div>
						</td>
						<td>
							<div align="center"><%=dfzflx%></div>
						</td>
						<td>
							<div align="center"><%=linelosstype%></div>
						</td>
						<td>
							<div align="center"><%=linelossvalue%></div>
						</td>
						<td>
							<div align="right"><%=zlfh%></div>
						</td>
						<td>
							<div align="right"><%=jlfh%></div>
						</td>
						<td>
							<div align="right"><%=beilv%></div>
						</td>
						<td>
							<div align="center"><%=notetypeid%></div>
						</td>
						<td>
							<div align="center"><%=noteno%></div>
						</td>
						<td>
							<div align="center"><%=pjje%></div>
						</td>
						
						
						<%
							if (autoauditstatus != null && autoauditstatus.equals("1")) {
						%>
						<td>
							<div align="center">
								通过
							</div>
						</td>
						<%
							} else {
						%>
						<td>
							<div align="center">
								未通过
							</div>
						</td>
						<%
							}
						%>
						<%
							if (manualauditstatus != null
											&& manualauditstatus.equals("1")) {
						%>
						<td>
							<div align="center">
								通过
							</div>
						</td>
						<%
							}else if(manualauditstatus != null
									&& manualauditstatus.equals("-1")){%>
						<td>
							<div align="center">
								通过
							</div>
						</td>
						<%} else if (manualauditstatus != null
											&& manualauditstatus.equals("-2")) {
						%>
						<td>
							<div align="center">
								不通过
							</div>
						</td>
						<%
							} else {
						%>
						<td>
							<div align="center">
								未通过
							</div>
						</td>
						<%
							}
						%>
						<%
							if (cityaudit != null && cityaudit.equals("1")) {
						%>
						<td>
							<div align="center">
								通过
							</div>
						</td>
						<%
							} else if (cityaudit != null && cityaudit.equals("-2")) {
						%>
						<td>
							<div align="center">
								不通过
							</div>
						</td>
						<%
							} else {
						%>
						<td>
							<div align="center">
								未通过
							</div>
						</td>
						<%
							}
						%>
						<td>
							<div align="left"><%=dbid%></div>
						</td>

					</tr>

					<%
						}
						}
					%>
					<%
						if (intnum == 0) {
							// System.out.println("QQQQ"+intnum);
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
						} else if (!(intnum > 17)) {
							for (int i = ((intnum - 1) % 17); i < 17; i++) {
								if (i % 2 == 0)
									color = "#FFFFFF";
								else
									color = "#DDDDDD";
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
					<%
						DecimalFormat we = new DecimalFormat("0.00");
						String s = "";
						String ss = "";
						s = we.format(ddf);
						ss = we.format(ddfs);
					%>
					<tr height="23">
						<td bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								合计
							</div>
						</td>
						<td bgcolor="#DDDDDD">
							<div align="left" class="bttcn">
								总电量：
							</div>
						</td>
						<td colspan="2" bgcolor="#DDDDDD">
							<div align="left" class="bttcn"><%=s%>度
							</div>
						</td>
						<td bgcolor="#DDDDDD">
							<div align="left" class="bttcn">
								总电费：
							</div>
						</td>
						<td colspan="23" bgcolor="#DDDDDD">
							<div align="left" class="bttcn"><%=ss%>元
							</div>
						</td>
					</tr>
				</table>
			</div>

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<input type="hidden" name="sheng2" id="sheng2" value="" />
						<input type="hidden" name="shi2" id="shi2" value="" />
						<input type="hidden" name="xian2" id="xian2" value="" />
						<input type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
						<input type="hidden" name="sptype2" id="sptype2" value="" />
						<input type="hidden" name="cityaudit2" id="cityaudit2" value="" />
						<input type="hidden" name="bzw1" id="bzw1" value="1" />
					</td>
				</tr>
				<tr bgcolor="F9F9F9">
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
							<img src="<%=path%>/images/daochu.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导出</span>
						</div>

						<div id="butongguo"
							style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right: 10px">
							<img alt="" src="<%=request.getContextPath()%>/images/baocun.png"
								width="100%" height="100%" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">不通过</span>
						</div>
						<div id="tongguo"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
							<img src="<%=path%>/images/2chongzhi.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">通过</span>
						</div>
						<div id="quxiao"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: 30px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">取消通过</span>
						</div>

					</td>
				</tr>



				<tr>
					<td>
						<div id="detailsDiv"
							style="position: relative; width: 99%; height: 200px; left: 0px; top: 10px; z-index: 1; float: left; overflow-y: auto;">

							<iframe name="details" frameborder="0" width="100%" height="100%"
								scrolling="no"></iframe>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script language="javascript">
function exportad() {
	var path = '<%=path%>';
	var zdname = "<%=zdname%>";
	var whereStr = "<%=str%>";
	var lrrq = '<%=lrrq%>';
	document.form1.action = path + "/web/check/人工电费二级审核.jsp?zdname=" + zdname
			+ "&whereStr=" + whereStr + "&lrrq=" + lrrq + "&command=daochu";

	document.form1.submit();
}
</script>
	<script language="javascript">
var path = '<%=path%>';
function lookDetails(dfid, dfzflx) {
	var dfid1 = dfid;
	var dfzflx1 = dfzflx;

	var href = "FeesDetails.jsp?dfid=" + dfid1 + "&dfzflx=" + dfzflx1;
	var str = "<a id='aa' href='" + href + "' target='details'></a>";
	$("body").append(str);
	$("#aa")[0].click();
	$("#aa").remove();
}
</script>
	<script type="text/javascript">
function lookDetailss(dbid, bzyf, dfzflx2, dfbzw) {
	var path = '<%=path%>';
	window
			.open(path + "/web/check/showdfxx.jsp?dbid=" + dbid + "&bzyf="+ bzyf + "&dfzflx=" + dfzflx2 + "&dfbzw=" + dfbzw,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
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
	sendRequest1(path + "/servlet/check?action=checkcityno1&chooseIdStr1="
			+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2, "checkcityno1");
}
function passCheckNo2(chooseIdStr1, chooseIdStr2) {
	sendRequest1(path + "/servlet/check?action=checkcityno11&chooseIdStr1="
			+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2, "checkcityno1");
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
/*function passCheckNo(){
 var m = document.getElementsByName('test[]');      
 var l = m.length; 
 var chooseIdStr1 = ""; 
 var chooseIdStr2 = ""; 
 var bz=0;
 for (var i = 0; i < l; i++) {  
 if(m[i].checked == true){
 bz+=1;
 var j = m[i].value.toString().indexOf("ssss=");
 var chooseIdStr3 = m[i].value.toString().substring(0,j);
 var zflx1 = m[i].value.toString().substring(j+5,m[i].value.toString().length);
 if(zflx1=="月结"){
 chooseIdStr1 = chooseIdStr1 +"'"+ chooseIdStr3 +"',";
 }else if(zflx1=="预支"){
 chooseIdStr1 = chooseIdStr1 +"'"+ chooseIdStr3 +"',";
 chooseIdStr2 = chooseIdStr2 +"'"+ chooseIdStr3 +"',";
 }else if(zflx1=="合同"||zflx1=="插卡"){
 chooseIdStr2 = chooseIdStr2 +"'"+ chooseIdStr3 +"',";
 } 
 }               
 } 
 chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
 chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
 if(bz<=240){ 
 if(bz>=1){
 document.form1.action=path+"/servlet/check?action=checkcityno&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2;
 document.form1.submit();
 }else{
 alert("请选择信息！");
 }
 }else{
 alert("您选择信息条数超过240条，信息量过大，请确定后重新执行！");
 }  
 }*/
function passCheckNo() {
	var m = document.getElementsByName('test[]');
	var l = m.length;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var count2 = 0;
	var bzw = 1;
	var bzw1 = "";
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
				var j = m[i].value.toString().indexOf("ssss=");
				var chooseIdStr3 = m[i].value.toString().substring(0, j);
				var zflx1 = m[i].value.toString().substring(j + 5,
						m[i].value.toString().length);
				if (zflx1 == "月结") {
					chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
				} else if (zflx1 == "预支") {
					count2 += 1;
					chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
					chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
				} else if (zflx1 == "合同" || zflx1 == "插卡") {
					chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
				}
			}
			bzw1 = document.form1.bzw1.value;
			if (bzw1 == "1") {
				
				if (count1 <= 240 * n
						|| ((bz + count2) >= 239 && (bz + count2) <= 241)) {
					if (((bz + count2) / 240 == 1)
							|| ((bz + count2) >= 239 && (bz + count2) <= 241)) {
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						chooseIdStr2 = chooseIdStr2.substring(0,
								chooseIdStr2.length - 1);
						//document.form1.action=path+"/servlet/check?action=checkcityno1&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2;
						passCheckNo1(chooseIdStr1, chooseIdStr2);
						chooseIdStr1 = "";
						chooseIdStr2 = "";
						bz = 0;
						count2 = 0;
						//document.form1.submit();	          	
					}
				} else if (count == count1 && bzw == 1) {
					chooseIdStr1 = chooseIdStr1.substring(0,
							chooseIdStr1.length - 1);
					chooseIdStr2 = chooseIdStr2.substring(0,
							chooseIdStr2.length - 1);
					bzw = 0;
					document.form1.action = path
							+ "/servlet/check?action=checkcityno&chooseIdStr1="
							+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
					document.form1.submit();
					showdiv("请稍等..............");
				}
			} else if (bzw1 == "0") {
				document.form1.action = path + "/web/msg.jsp";
				document.form1.submit();
				return;
			}
		}
	} else {
		alert("请选择信息！");
	}
}
function passCheckNoPass() {
	var m = document.getElementsByName('test[]');
	var l = m.length;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var count2 = 0;
	var bzw = 1;
	var bzw1 = "";
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
				var j = m[i].value.toString().indexOf("ssss=");
				var chooseIdStr3 = m[i].value.toString().substring(0, j);
				var zflx1 = m[i].value.toString().substring(j + 5,
						m[i].value.toString().length);
				if (zflx1 == "月结") {
					chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
				} else if (zflx1 == "预支") {
					count2 += 1;
					chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
					chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
				} else if (zflx1 == "合同" || zflx1 == "插卡") {
					chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
				}
			}
			bzw1 = document.form1.bzw1.value;
			if (bzw1 == "1") {
				if (count1 <= 240 * n
						|| ((bz + count2) >= 239 && (bz + count2) <= 241)) {
					if (((bz + count2) / 240 == 1)
							|| ((bz + count2) >= 239 && (bz + count2) <= 241)) {
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						chooseIdStr2 = chooseIdStr2.substring(0,
								chooseIdStr2.length - 1);
						//document.form1.action=path+"/servlet/check?action=checkcityno1&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2;
						passCheckNo2(chooseIdStr1, chooseIdStr2);
						chooseIdStr1 = "";
						chooseIdStr2 = "";
						bz = 0;
						count2 = 0;
						//document.form1.submit();	          	
					}
				} else if (count == count1 && bzw == 1) {
					chooseIdStr1 = chooseIdStr1.substring(0,
							chooseIdStr1.length - 1);
					chooseIdStr2 = chooseIdStr2.substring(0,
							chooseIdStr2.length - 1);
					bzw = 0;
					document.form1.action = path
							+ "/servlet/check?action=checkcityno2&chooseIdStr1="
							+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
					document.form1.submit();
				}
			} else if (bzw1 == "0") {
				document.form1.action = path + "/web/msg.jsp";
				document.form1.submit();
				return;
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
	//alert(qm[0].checked);  
	var m = document.getElementsByName('test[]');
	var l = m.length;
	if (qm[0].checked == true) {
		for ( var i = 0; i < l; i++) {
			m[i].checked = true;
		}
	} else {
		for ( var i = 0; i < l; i++) {
			//m[i].checked == true ? m[i].checked = false: m[i].checked = true; 
			m[i].checked = false;
		}
	}
}

document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
document.form1.zdlx.value = '<%=zdlx%>';
document.form1.jzproperty.value = '<%=jzproperty%>';
document.form1.cityaudit.value = '<%=cityaudit1%>';
document.form1.zdqyzt.value = '<%=zdqyzt%>';
document.form1.dbqyzt.value = '<%=dbqyzt%>';
</script>
</html>

