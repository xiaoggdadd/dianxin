﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@  page import="java.text.*" %>

<%	
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String acccountid = account.getAccountId();
	String jztype1 = request.getParameter("jztype") != null ? request
			.getParameter("jztype") : "0";
	String jzproperty1 = request.getParameter("jzproperty") != null ? request
			.getParameter("jzproperty")
			: "0";
	String bgsign = request.getParameter("bgsign") != null ? request
			.getParameter("bgsign") : "0";

	String sheng = (String) session.getAttribute("accountSheng");
	String loginName = (String) session.getAttribute("loginName");
	String agcode1="";
   	if(request.getParameter("shi")==null){
   		ArrayList shilist = new ArrayList();
   		CommonBean commBean = new CommonBean();
   		shilist = commBean.getAgcode(sheng,account.getAccountId());
		if(shilist!=null){
       		int scount = ((Integer)shilist.get(0)).intValue();
            agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
         }
    }  
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : agcode1;
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";
	String xiaoqu = request.getParameter("xiaoqu") != null ? request
			.getParameter("xiaoqu") : "0";
	//System.out.println("logManage.jsp>>"+beginTime);
	String sname = request.getParameter("sname") != null ? request
			.getParameter("sname") : "";
	String szdcode = request.getParameter("szdcode") != null ? request
			.getParameter("szdcode") : "";
	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String roleId = (String) session.getAttribute("accountRole");
	String permissionRights = "";
%>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page"
	class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
	permissionRights = commBean.getPermissionRight(roleId, "0102");
%>
<html>
	<head>
		<title>logMange</title>
		<style>
.btn {
	BORDER-RIGHT: #7b9ebd 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7b9ebd 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :     DXImageTransform.Microsoft.Gradient (    
		GradientType =     0, StartColorStr =     #ffffff, EndColorStr =    
		#cecfde );
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
	FILTER: progid :     DXImageTransform.Microsoft.Gradient (    
		GradientType =     0, StartColorStr =     #ffffff, EndColorStr =    
		#B3D997 );
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
	FILTER: progid :     DXImageTransform.Microsoft.Gradient (    
		GradientType =     0, StartColorStr =     #ffffff, EndColorStr =    
		#CAE4B6 );
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
	FILTER: progid :     DXImageTransform.Microsoft.Gradient (    
		GradientType =     0, StartColorStr =     #ffffff, EndColorStr =    
		#C3DAF5 );
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
	FILTER: progid :     DXImageTransform.Microsoft.Gradient (    
		GradientType =     0, StartColorStr =     #ffffff, EndColorStr =    
		#D7E7FA );
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
	FILTER: progid :     DXImageTransform.Microsoft.Gradient (    
		GradientType =     0, StartColorStr =     #ffffff, EndColorStr =    
		#C3DAF5 );
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
	FILTER: progid :     DXImageTransform.Microsoft.Gradient (    
		GradientType =     0, StartColorStr =     #ffffff, EndColorStr =    
		#C3DAF5 );
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
	FILTER: progid :     DXImageTransform.Microsoft.Gradient (    
		GradientType =     0, StartColorStr =     #FFFFFF, EndColorStr =    
		#9DBCEA );
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

.btt {
	bgcolor: #888888;
}

.bttcn {
	color: black;
	font-weight: bold;
}

.selected_font {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 120%;
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
	FILTER: progid :     DXImageTransform.Microsoft.Gradient (    
		GradientType =     0, StartColorStr =     #ffffff, EndColorStr =    
		#D7E7FA );
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
	document.form1.action = path + "/web/jizhan/danjiatiaozheng.jsp";
	document.form1.submit();
}
function daorudj(){
		document.form1.action=path+"/web/jizhan/danjiadaoru.jsp?servletname=danjiadaoru&action=updanjia";
		document.form1.submit();
	}
function delLogs() {

}
function addJz() {
	document.form1.action = path + "/web/jizhan/addjz.jsp";
	document.form1.submit();
}
function daorujz() {
	alert("请期待！");
}
$(function() {

	$("#query").click(function() {
		chaxun();
	});
	
	$("#import").click(function(){
			daorudj();
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

	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" height="18%">
				<tr>
					<td colspan="4" width="50">
						<div style="width:700px;">
							<img alt="" src="<%=path%>/images/btt.bmp" height=63/>
							<span
								style="font-size: 14px; font-weight: bold; position: absolute; left: 25px; top: 15px; color: black">站点单价调整</span>
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
					<td>
						<table>
							<tr class="form_label">
								<td>
									城市：
								</td>
								<td>
									<select name="shi" id="shi" style="width: 130;"
										onchange="changeShi()" class="selected_font">
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
									<select name="xian" id="xian" style="width: 130" class="selected_font"
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
									<select name="xiaoqu" id="xiaoqu" style="width: 130" class="selected_font">
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
											站点属性：
										</td>
										<td>
											<select name="jzproperty" style="width: 130"
												class="selected_font" onchange="kzinfo()">
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
											集团报表类型：
										</td>
										<td>
											<select name="jztype" style="width: 130"
												class="selected_font" onchange="kzinfo()">
												<%
													ArrayList zdtype = new ArrayList();
													zdtype = ztcommon.getSelctOptions("zdlx");
													if (zdtype != null) {
														String code = "", name = "";
														int cscount = ((Integer) zdtype.get(0)).intValue();
														for (int i = cscount; i < zdtype.size() - 1; i += cscount) {
															code = (String) zdtype.get(i + zdtype.indexOf("CODE"));
															name = (String) zdtype.get(i + zdtype.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
													}
													}
												%>
											</select>
										</td>
										<td>
											是否标杆：
										</td>
										<td>
											<select name="bgsign" style="width: 130"
												class="selected_font">
												<option value="0">
													否
												</option>
												<option value="1">
													是
												</option>

											</select>
										</td>
										<td>
											站点名称：
										</td>
										<td>
											<input type="text" name="sname" value="<%=sname%>"
												style="width: 130px" class="selected_font" />
										</td>
									</tr>
									<tr class="form_label">
										<td>
											站点代码：
										</td>
										<td>
											<input type="text" name="szdcode" value="<%=szdcode%>"
												style="width: 130px" class="selected_font" />
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

<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >  
			 <table width="100%" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
				<tr>
					<td width="5%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">序号</div>
					</td>
					<td nowrap height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">站点ID</div>
					</td>
					<td nowrap height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">站点名称</div>
					</td>
					<td nowrap height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">所在地区</div>
					</td>

					<td nowrap height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">投入使用日期</div>
					</td>
					<td nowrap height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">负责人</div>
					</td>

					<td width=80 height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">电价</div>
					</td>
					<td width=80 height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">调整</div>
					</td>
					<td nowrap height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">详细信息</div>
					</td>
				</tr>
				<%
					JiZhanBean bean = new JiZhanBean();
					ArrayList fylist = new ArrayList();
					fylist = bean.getPageData_danjiatiaozheng(curpage, sheng, shi,
							xian, xiaoqu, sname, szdcode, acccountid, jztype1,
							jzproperty1, bgsign);
					allpage = bean.getAllPage();
					String jzname = "", szdq = "", sydate = "", fzr = "", dianfei = "", zdcode = "", id = "";
					int intnum = xh = pagesize * (curpage - 1) + 1;
					if (fylist != null) {
						int fycount = ((Integer) fylist.get(0)).intValue();
						for (int k = fycount; k < fylist.size() - 1; k += fycount) {

							//num为序号，不同页中序号是连续的
							id = (String) fylist.get(k + fylist.indexOf("ID"));
							jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
							szdq = (String) fylist.get(k + fylist.indexOf("SZDQ"));
							sydate = (String) fylist.get(k + fylist.indexOf("SYDATE"));
							fzr = (String) fylist.get(k + fylist.indexOf("FZR"));
							dianfei = (String) fylist
									.get(k + fylist.indexOf("DIANFEI"));
							zdcode = (String) fylist.get(k + fylist.indexOf("ZDCODE"));
							if (dianfei == null || dianfei.equals(""))
								dianfei = "0";
							DecimalFormat price = new DecimalFormat("0.0000");
							dianfei = price.format(Double.parseDouble(dianfei));

							String color = null;

							if (fzr == null || fzr.equals("null"))
								fzr = "";
							if (sydate == null)
								sydate = "";
							if (intnum % 2 == 0) {
								color = "#DDDDDD";
							} else {
								color = "#FFFFFF";
							}
				%>
				<tr bgcolor="<%=color%>">
					<td>
						<div align="center"><%=intnum++%></div>
					</td>
					<td>
						<div align="center"><%=id%></div>
					</td>
					<td>
						<div align="left"><%=jzname%></div>
					</td>
					<td>
						<div align="center"><%=szdq%></div>
					</td>
					<td>
						<div align="center"><%=sydate%></div>
					</td>
					<td>
						<div align="center"><%=fzr%></div>
					</td>


					<td>
						<div align="right" id="div<%=id%>"><%=dianfei%></div>
					</td>
					<td>
						<div align="center" id="butt<%=id%>">
							<a href="#" onclick="delzd('<%=id%>','<%=dianfei%>')">调整</a>
						</div>
					</td>
					<td>
						<div align="center">
							<a href="#" onclick="modifyjz('<%=id%>')">详细信息</a>
						</div>
					</td>

				</tr>
				<%
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
			<td >							
												<div id="parent" style="display:inline" align="right">
                          <div style="width:300px;display:inline;" align="right"><hr></div>
                      </div>
											
												<div align="right">
									</td>
									</tr>
										<tr>
										<td>

												<div align="right">
													<%
														if (permissionRights.indexOf("PERMISSION_ADD") >= 0) {
													%>
													<div id="import" style="position:relative;width:60px;height:23px;cursor: pointer;right:3px;float:right;">
							                            <img alt="" src="<%=request.getContextPath() %>/images/daoru.png" width="100%" height="100%" />
							                            <span style="font-size:12px;position: absolute;left:25px;top:1px;color:white">导入</span>
													</div>
							        	<%
							        		}
							        	%>
							        </div>
										</td>
										</tr>
			</table>


			<input type="hidden" id="hid" name="hid" value="" />
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
				+ "/web/jizhan/danjiatiaozheng.jsp?curpage=" + curpage;
		document.form1.submit();
	}
}
function nextpage() {
	document.form1.page.value = parseInt(document.form1.page.value) + 1;
	var curpage = '<%=(curpage + 1)%>';
	document.form1.action = path + "/web/jizhan/danjiatiaozheng.jsp?curpage="
			+ curpage;
	document.form1.submit();
}
function gopagebyno(pageno) {
	document.form1.page.value = pageno;

	document.form1.action = path + "/web/jizhan/danjiatiaozheng.jsp?curpage="
			+ pageno;
	document.form1.submit();
}
function delzd(id, dianfei) {
<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
      var divid="div"+id;
      var buttid = "butt"+id;
      var inputname="name"+id;
      document.getElementById(divid).innerHTML='<input type="text" id="'+inputname+'" name="'+inputname+'" value=\''+dianfei+'\' size=5/>';
      var bhtml='<input type="button" name="updatedf" value="更新" onclick="updatedfei(\''+id+'\')">';
      bhtml+='<input type="button" name="canceldf" value="取消" onclick="canceldfei(\''+id+'\',\''+dianfei+'\')">';
      //alert(bhtml);
      document.getElementById(buttid).innerHTML=bhtml;
      <%} else {%>
      	alert("您没有编辑站点信息的权限");
     <%}%>
      
      
    }
     function canceldfei(id,dianfei){
     	
     	var divid="div"+id;
      var buttid = "butt"+id;
			//alert(dianfei);
      document.getElementById(divid).innerHTML=dianfei;
     var bhtml='<a href="#" onclick="delzd(\''+id+'\',\''+dianfei+'\')">调整</a>';
     
      //alert(bhtml);
      document.getElementById(buttid).innerHTML=bhtml;
      
     	
     }
     function updatedfei(id){
     	
    	 var inputname="name"+id;
    	 var inputvalue=document.getElementById(inputname).value
    	 if(isNaN(inputvalue)==false){
	    	 var url = path+"/servlet/zhandian?action=modifydanjia&id="+id+"&ndianfei="+inputvalue;
	    	 document.getElementById("hid").value=id;
	    	// alert(url);
			sendRequest(url,"modifydj");
		}else{
			alert("您输入的信息有误，电价必须为数字！");
		}			
     }
     function yemianhuifu(res){
     //alert(res);
    	 
    	 var dianfei = res.substring(res.indexOf("修改为")+4);
    	//alert(dianfei);
    	var id = document.getElementById("hid").value;
    	 //var id = document.getElementById("hid").value;//res.substring(res.indexOf(":")+1,res.indexOf("<"));
    	// alert(id);
    	 //alert(res+"/d=="+dianfei+"/id=="+id+"<");
    	 var inputname="name"+id;
    	 var inputvalue=document.getElementById(inputname).value
    	 canceldfei(id,inputvalue);
    	 //alert(res+"/d=="+dianfei+"/id=="+id);
    	// var divid="div"+id;
       //  var buttid = "butt"+id;
   			//alert(dianfei);
   			//alert(divid);
        // document.getElementById(divid).innerHTML=dianfei;
        //var bhtml='<a href="#" onclick="delzd(\''+id+'\',\''+dianfei+'\')">调整</a>';
        
         //alert(bhtml);
        // document.getElementById(buttid).innerHTML=bhtml;
         
     }
    function modifyjz(id){
     		document.form1.action=path+"/web/jizhan/zhandianinfo.jsp?id="+id
      document.form1.submit();
    }
    function changeSheng(){
			var sheng = document.form1.sheng.value;
			var xianlist = document.all.xian;
				xianlist.options.length="0";
				xianlist.add(new Option("请选择","0"));
			if(sheng=="0"){
				var shilist = document.all.shi;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				
				return;
			}else{
				sendRequest(path+"/servlet/area?action=sheng&pid="+sheng,"sheng");
			}
		}
		function updateShi(res){
			var shilist = document.all.shi;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		function changeShi(){
			var shi = document.form1.shi.value;
			var xiaoqu = document.all.xiaoqu;
				xiaoqu.options.length="0";
				xiaoqu.add(new Option("请选项","0"));
			if(shi=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=shi&pid="+shi,"shi");
			}
		}
		function updateQx(res){
			var shilist = document.all.xian;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
			function changeXian(){
			var shi = document.form1.xian.value;
			if(shi=="0"){
				var shilist = document.all.xiaoqu;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=xian&pid="+shi,"xian");
			}
		}
		
		function updateXQ(res){
			var shilist = document.all.xiaoqu;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
document.form1.jztype.value='<%=jztype1%>';
document.form1.jzproperty.value='<%=jzproperty1%>';
document.form1.bgsign.value='<%=bgsign%>';
     </script>

