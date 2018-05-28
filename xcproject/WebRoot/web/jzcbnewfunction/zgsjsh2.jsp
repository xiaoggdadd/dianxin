<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime"%>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%@ page
	import="java.util.regex.Pattern,com.noki.newfunction.dao.*,com.noki.newfunction.javabean.*"%>

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

	String loginId1 = request.getParameter("loginId");


	int pagesize = 15, curpage = 1, xh = 1;
	String permissionRights = "";
	String color = null;
	String roleId = (String) session.getAttribute("accountRole");

	String shi = request.getParameter("shi") != null ? request.getParameter("shi") : agcode1;
	String xian = request.getParameter("xian") != null ? request.getParameter("xian") : "0";
	String xiaoqu = request.getParameter("xiaoqu") != null ? request.getParameter("xiaoqu") : "0";
	String zgmonth = request.getParameter("zgmonth") != null ? request.getParameter("zgmonth") : "";
	String zdname = request.getParameter("zdname") != null ? request.getParameter("zdname") : "";
	String sjeshzt1 = request.getParameter("sjeshzt") != null ? request.getParameter("sjeshzt") : "0";
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
	width: 90px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 120%;
}

.selected_font1 {
	width: 120px;
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
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
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
function queryDegree() {
	//alert(path);
	document.form1.action = path
			+ "/web/jzcbnewfunction/zgsjsh2.jsp?command=chaxun";
			if(document.getElementById("zgyf").value==""){
	       alert("暂估月份不能为空！请填写");
	       return;
	  }else{
	document.form1.submit();
	}
}
$(function() {
	$("#chaxun").click(function() {
		queryDegree();
	});
	$("#butongguo").click(function() {
	passCheckNo();
	});
	$("#tongguo").click(function() {
		passCheck();
	});
	$("#quxiao").click(function() {
	passCheckqx()
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
	%>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
					<tr>
			    	<td colspan="4" width="50" >
						 <div style="width:700px;height:50px">
						  
						  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">市级领导审核</span>	
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
									暂估月份：
								</td>
								<td>
									<input id="zgyf" type="text" name="zgmonth"
										value="<%if (null != request.getParameter("zgmonth"))
				out.print(request.getParameter("zgmonth"));%>"
										onFocus="getDatenyString(this,oCalendarChsny)"
										class="selected_font" />
										<span class="style1">&nbsp;*</span>
								</td>
								<td>
									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; left: 50px; float: left; top: 0px">
										<img alt="" src="<%=path%>/images/chaxun.png" width="100%"
											height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
									</div>
								</td>
							</tr>

							<tr class="form_label">
								<td>
									站点名称
								</td>
								<td>
									<input type="text" class="selected_font" name="zdname"
										value="<%if (null != request.getParameter("zdname"))
				out.print(request.getParameter("zdname"));%>"
										class="form" />
								</td>
								<td>
									市级领导审核状态
								</td>
								<td>
									<select name="sjeshzt" class="selected_font"
										onchange="javascript:document.form1.cwshzt2.value=document.form1.sjeshzt.value">
										<option value="-1">
											请选择
										</option>
										<option value="0">
											未审核
										</option>
										<option value="1">
											通过
										</option>
										<option value="2">
											未通过
										</option>
									</select>
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
				zgsjdao bean = new zgsjdao();
				String whereStr = "";
				if (shi != null && !shi.equals("") && !shi.equals("0")) {
					whereStr = whereStr + " and z.shi='" + shi + "'";
				}
				if (xian != null && !xian.equals("") && !xian.equals("0")) {
					whereStr = whereStr + " and z.xian='" + xian + "'";
				}
				if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
					whereStr = whereStr + " and z.xiaoqu='" + xiaoqu + "'";
				}
				if (zgmonth != null && !zgmonth.equals("") && !zgmonth.equals("0")) {
					whereStr = whereStr + " and s.zgyf='" + zgmonth + "'";
				}
				if (zdname != null && !zdname.equals("") && !zdname.equals("0")) {
					whereStr = whereStr + " and s.zdname='" + zdname + "'";
				}
				if (sjeshzt1 != null && !sjeshzt1.equals("") && !sjeshzt1.equals("-1")) {
					whereStr = whereStr + " and s.sjeshzt='" + sjeshzt1 + "'";
				}
				if (loginId1 != null && !loginId1.equals("")) {
					loginId = loginId1;
					shi = "1";
				}
				//String count1 = "0", count2 = "0", count3 = "0", count4 = "0.00";
			%>
			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="1300px" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
						<td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()" />全选</div></td>
						<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">城市</div></td>
						<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>
						<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">乡镇</div></td>

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
								上次暂估时间
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								本次暂估时间
							</div>
						</td>

						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								省定标
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								单价
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								暂估月份
							</div>
						</td>
						<%--<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								用电量
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								用电调整
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								暂估电量
							</div>
						</td>
						--%><td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								暂估电费
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								财务审核状态
							</div>
						</td>
						<td width="9%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级领导审核状态
							</div>
						</td>


					</tr>
					<%
						List<zgsj> list = null;
						if ("daochu".equals(request.getParameter("command"))|| "chaxun".equals(request.getParameter("command"))) {
							list = bean.getzgsj2(whereStr, loginId);
						} else {
							list = null;
						}
						String id = "";
						String zdid = "";//站点编号
						String shi1 = "";//市
						String xian1 = "";//县
						String xiaoqu1 = "";//小区
						String zdname1 = "";//站点名称
						String zdlx = "";//站点类型
						String lastTime = "";//上次暂估时间
						String thisTime = "";//本次暂估时间
						String sdb = "";//省定标
						String dj = "";//单价
						String zgyf = "";//暂估月份
						String zgdf = "";//暂估电费
						String cwshzt1 = "";//财务审核状态
						String cwshzt2 = "";
						String sjeshzt = "";//市级2审核状态

						int intnum = 0;
						if (list != null) {
							for (zgsj bean2 : list) {
								id = bean2.getId();
								zdid = bean2.getZdid();
								shi1 = bean2.getShi();
								xian1 = bean2.getQuxian();
								xiaoqu1 = bean2.getXiangzhen();
								zdname1 = bean2.getZdname();
								zdlx = bean2.getZdlx();
								lastTime = bean2.getZfqssj();
								thisTime = bean2.getZfjssj();
								sdb = bean2.getSdb();
								dj = bean2.getDanjia();
								zgyf = bean2.getZgyf();
								zgdf = bean2.getMoney();
								cwshzt1 = bean2.getCwshzt();
								cwshzt2 = bean2.getCwshzt();
								//System.out.println("cwshzt1 = "+cwshzt1);
								sjeshzt = bean2.getSjeshzt();
								if (null == id || "".equals(id) || "null".equals(id)) {id = "";}
								if (zdid == null || zdid.equals("null") || zdid.equals("")|| zdid.equals(" ") || zdid == "") {zdid = "";}
								if (zdname == null || zdname.equals("null")|| zdname.equals("") || zdname.equals(" ")|| zdname == "") {zdname = "";}
								if (shi1 == null || shi1.equals("null") || shi1.equals("")|| shi1.equals(" ") || shi1 == "") {shi1 = "";}
								if (xian1 == null || xian1.equals("null")|| xian1.equals("") || xian1.equals(" ")|| xian1 == "") {xian1 = "";}
								if (xiaoqu1 == null || xiaoqu1.equals("null")|| xiaoqu1.equals("") || xiaoqu1.equals(" ")|| xiaoqu1 == "") {xiaoqu1 = "";}
								if (zgyf == null || zgyf.equals("null")|| zgyf.equals("") || zgyf.equals(" ")|| zgyf == "") {zgyf = "";}
								if (zdlx == null || zdlx.equals("null")|| zdlx.equals("") || zdlx.equals(" ")|| zdlx == "") {zdlx = "";}
								if (lastTime == null || lastTime.equals("null")|| lastTime.equals("") || lastTime.equals(" ")|| lastTime == "") {lastTime = "";}
								if (thisTime == null || thisTime.equals("null")|| thisTime.equals("") || thisTime.equals(" ")|| thisTime == "") {thisTime = "";}
								if (sdb == null || sdb.equals("null")|| sdb.equals("") || sdb.equals(" ")|| sdb == "") {sdb = "0";}
								if (dj == null || dj.equals("null")|| dj.equals("") || dj.equals(" ")|| dj == "") {dj = "0";}
								if (zgdf == null || zgdf.equals("null")|| zgdf.equals("") || zgdf.equals(" ")|| zgdf == "") {zgdf = "0";}
								if (cwshzt1 == null || cwshzt1.equals("null")|| cwshzt1.equals("") || cwshzt1.equals(" ")|| cwshzt1 == "") {cwshzt1 = "0";}
								if (sjeshzt == null || sjeshzt.equals("null")|| sjeshzt.equals("") || sjeshzt.equals(" ")|| sjeshzt == "") {sjeshzt = "0";}
								
								
								if(cwshzt1.equals("0")){
									cwshzt1="未审核";
								}else if(cwshzt1.equals("1")){
									cwshzt1="审核通过";
								}else if(cwshzt1.equals("2")){
									cwshzt1="审核未通过";
								}
								if(sjeshzt.equals("0")){
									sjeshzt="未审核";
								}else if(sjeshzt.equals("1")){
									sjeshzt="审核通过";
								}else if(sjeshzt.equals("2")){
									sjeshzt="审核未通过";
								}
								if(lastTime.length()>=7&&!lastTime.equals("")&&!lastTime.equals("null")&&!lastTime.equals(" ")){
									lastTime=lastTime.substring(0,11);
								}
								if(thisTime.length()>=7&&!thisTime.equals("")&&!thisTime.equals("null")&&!thisTime.equals(" ")){
									thisTime=thisTime.substring(0,11);
								}
								
								DecimalFormat mat = new DecimalFormat("0.00");
								DecimalFormat mat2 = new DecimalFormat("0.0000");
								zgdf=mat.format(Double.parseDouble(zgdf));
								dj=mat2.format(Double.parseDouble(dj));
								sdb=mat.format(Double.parseDouble(sdb));

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
							<div align="center">
								<input type="checkbox" checked="checked" name="test[]" value="<%=id%>" />
								<input type="hidden" type="checkbox"  name="test1[]" value="<%=cwshzt2%>" />
							</div>
						</td>
						<td>
							<div align="left"><%=shi1%></div>
						</td>
						<td>
							<div align="left"><%=xian1%></div>
						</td>
						<td>
							<div align="left"><%=xiaoqu1%></div>
						</td>
						<td>
							<div align="left"><%=zdname1%></div>
						</td>
						<td>
							<div align="left"><%=zdlx%></div>
						</td>
						<td>
							<div align="left"><%=lastTime%></div>
						</td>
						<td>
							<div align="left"><%=thisTime%></div>
						</td>
						<td>
							<div align="left"><%=sdb%></div>
						</td>
						
						<td>
							<div align="left"><%=dj%></div>
						</td>
						<td>
							<div align="left"><%=zgyf%></div>
						</td>
						<%--<td>
							<div align="left"><%=ydl%></div>
						</td>
					
						<td>
							<div align="left"><%=dltz%></div>
						</td>
						<td>
							<div align="left"><%=zgdl%></div>
						</td>
						--%><td>
							<div align="left"><%=zgdf%></div>
						</td>
						<td>
							<div align="left"><%=cwshzt1%></div>
						</td>
						<td>
							<div align="left"><%=sjeshzt%></div>
						</td>
						</tr>
					<%
						}
						}
					%>

				</table>
			</div>

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<input type="hidden" name="sheng2" id="sheng2" value="" />
						<input type="hidden" name="shi2" id="shi2" value="" />
						<input type="hidden" name="xian2" id="xian2" value="" />
						<input type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
						<input type="hidden" name="cwshzt2" id="cwshzt2" value="" />
					</td>
				</tr>
				<tr>

					<td align="right">
						<div id="tongguo"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: -150px; top: 0px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 35px; top: 5px; color: white">同意</span>

						</div>
					</td>
					<td align="right">
						<div id="butongguo"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: -50px; top: 0px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">退回</span>
						</div>
					</td>

					<td align="right">
						<div id="quxiao"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: 50px; top: 0px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">撤销</span>
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
	<script type="text/javascript">
function lookDetailssz(id) {
	var path = '<%=path%>';
	window
			.open(
					path + "/web/jzcbnewfunction/shishxx.jsp?zdid=" + id,
					'',
					'width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
}
</script>
	<script type="text/javascript">
var XMLHttpReq;
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}

function sendRequest(url, para) {

	createXMLHttpRequest();

	XMLHttpReq.open("GET", url, true);

	if (para == "sheng") {
		XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
	} else if (para == "shi") {
		XMLHttpReq.onreadystatechange = processResponse_shi;
	} else if (para == "xian") {
		XMLHttpReq.onreadystatechange = processResponse_xian;
	} else {
		XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
	}
	XMLHttpReq.send(null);
}
// 处理返回信息函数
function processResponse() {

	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		var res = XMLHttpReq.responseText;
		window.alert(res);
	} else { //页面不正常
		window.alert("您所请求的页面有异常。");
	}
}

function processResponse_sheng() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态

		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");

			//var res = XMLHttpReq.responseText;

			updateShi(res);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}

function processResponse_shi() {

	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			updateQx(res);
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function processResponse_xian() {

	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			updateZd(res);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}

function changeSheng() {
	var sid = document.form1.sheng.value;
	document.form1.sheng2.value = document.form1.sheng.value;
	if (sid == "0") {
		var shilist = document.all.shi;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=sheng&pid=" + sid, "sheng");

	}
}
function updateShi(res) {
	var shilist = document.all.shi;
	shilist.options.length = "0";
	shilist.add(new Option("请选项", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
function changeCity() {
	var sid = document.form1.shi.value;
	document.form1.shi2.value = document.form1.shi.value;
	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=shi&pid=" + sid, "shi");
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

function changeCountry() {
	var sid = document.form1.xian.value;
	document.form1.xian2.value = document.form1.xian.value;
	if (sid == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=xian&pid=" + sid, "xian");
	}
}
function changeXiaoqu() {
	var sid = document.form1.shi.value;
	document.form1.shi2.value = document.form1.shi.value;
	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=shi&pid=" + sid, "shi");
	}
}
function updateZd(res) {
	var shilist = document.all.xiaoqu;
	shilist.options.length = "0";
	shilist.add(new Option("请选项", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
//审核通过
function passCheck() {
	var loginName = '<%=loginName%>';
	var m = document.getElementsByName('test[]');
	var cwshzt2 = document.getElementsByName('test1[]');
	var l = m.length;
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var bzw = 1;
	var count2 = 0;
	var chooseIdStr1 = "";
	var con = 0;
	
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
		if(m[i].checked == true){
       			if(cwshzt2[i].value=='1'){
       			con++;
       			}
       		}
	}
	
	
	
	if(con!=0){
	 alert("财务审核已经通过不允许此操作！");
	 return;
	// alert(cwshzt1);
	}else if (count != 0) {
		if (count % 240 == 0) {
			n = count / 240 - 1;
		} else {
			n = (count - (count % 240)) / 240;
		}
		for ( var i = 0; i < l; i++) {
			if (m[i].checked == true) {
				bz += 1;
				count1 += 1;
				var chooseIdStr3 = m[i].value.toString();
				chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";

			}
		}
		
		chooseIdStr1 = chooseIdStr1.substring(0, chooseIdStr1.length - 1);
		//alert(chooseIdStr1);
		document.form1.action = path+ "/servlet/ZGsjServlet?action=sjesh&chooseIdStr1="
		+ chooseIdStr1 + "&loginName=" + loginName;
		document.form1.submit();
	} else{
		alert("请选择信息！");
	}
	

}
//审核不通过
function passCheckNo() {
	var m = document.getElementsByName('test[]');
	var cwshzt2 = document.getElementsByName('test1[]');
	var loginName = '<%=loginName%>';
	var cwshzt2 = '<%=cwshzt2%>';
	var arr = new Array
	var l = m.length;
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var bzw = 1;
	var count2 = 0;
	var con = 0;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
		if(m[i].checked == true){
       			if(cwshzt2[i].value=='1'){
       			con++;
       			}
       		}
	}
	if(con!=0){
	 alert("财务审核已经通过不允许此操作！");
	 return;
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
					var chooseIdStr3 = m[i].value.toString();
					chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
				}
			}
			chooseIdStr1 = chooseIdStr1.substring(0, chooseIdStr1.length - 1);
			alert(chooseIdStr1);
			document.form1.action = path
					+ "/servlet/ZGsjServlet?action=sjeshbu&chooseIdStr1="
					+ chooseIdStr1 + "&loginName=" + loginName;
			document.form1.submit();


		} else {
			alert("请选择信息！");
		}
}
//取消审核
function passCheckqx() {
	var loginName = '<%=loginName%>';
	var cwshzt2 = '<%=cwshzt2%>';
	var m = document.getElementsByName('test[]');
	var cwshzt2 = document.getElementsByName('test1[]');
	//var mc = document.getElementsByName('test1[]');
	//  var cuu=document.form1.currentmonth.value;
	var arr = new Array
	var l = m.length;
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var bzw = 1;
	var count2 = 0;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	var con = 0;
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
		if(m[i].checked == true){
       			if(cwshzt2[i].value=='1'){
       			con++;
       			}
       		}
		/*if (m[i].checked == true) {
			if (mc[i].value == '2') {
				con++;
			}
		}*/
	}
	//if (con != 0) {
	//	alert("省级审核已经通过不允许此操作！");
	//} else {
	if(con!=0){
	 alert("财务审核已经通过不允许此操作！");
	 return;
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

					var chooseIdStr3 = m[i].value.toString();

					chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";

				}
			}
			chooseIdStr1 = chooseIdStr1.substring(0, chooseIdStr1.length - 1);
			//alert(chooseIdStr1);
			document.form1.action = path+ "/servlet/ZGsjServlet?action=sjeshqxsh&chooseIdStr1="+ chooseIdStr1 + "&loginName=" + loginName;
			document.form1.submit();

		} else {
			alert("请选择信息！");
		}
	//}
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
document.form1.zgmonth.value = '<%=zgmonth%>';
document.form1.zdname.value = '<%=zdname%>';
document.form1.sjeshzt.value = '<%=sjeshzt1%>';
</script>
</html>

