<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime"%>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.regex.Pattern,com.noki.newfunction.dao.*,com.noki.newfunction.javabean.*"%>

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
	String accountmonth = request.getParameter("accountmonth") != null ? request
			.getParameter("accountmonth")
			: "";
	String zdname = request.getParameter("zdname") != null ? request
			.getParameter("zdname") : "";
	String cwshenhe = request.getParameter("cwshenhe") != null ? request
			.getParameter("cwshenhe")
			: "0";

	String loginId1 = request.getParameter("loginId");
	//String accountmonth = request.getParameter("accountmonth") != null ? request.getParameter("accountmonth") : "";//报账月份

	int pagesize = 15, curpage = 1, xh = 1;
	String permissionRights = "";
	String color = null;
	String roleId = (String) session.getAttribute("accountRole");

	String dgpch = request.getParameter("dgpch") != null ? request
			.getParameter("dgpch") : "";
	String yppch = request.getParameter("yppch") != null ? request
			.getParameter("yppch") : "";
	// String shenhe = request.getParameter("shenhe")!=null?request.getParameter("shenhe"):"5";
	// System.out.println("------"+dgpch+"----"+yppch+"-----"+shenhe);
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
		.selected_font1{
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
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
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
		var path='<%=path%>';
		function queryDegree() {
		         //alert(111);
				document.form1.action = path+ "/web/jzcbnewfunction/cxzgmx.jsp?command=chaxun";
				
				 if(document.getElementById("zgyf").value==""){
	       alert("暂估月份不能为空！请填写");
	       return;
	  }else
				document.form1.submit();
		
		}
		$(function() {
			$("#chaxun").click(function() {
			//alert("111");
				queryDegree();
			});
			$("#butongguo").click(function() {
			  // alert("111");
				passCheckNo();
				
			});
			$("#tongguo").click(function(){
			//alert("111");
				passCheck();
			});
				$("#quxiao").click(function(){
			//alert("111");
				passCheckqx();
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
			<table width="100%" border="0" cellspacing="0" cellpadding="0" height="18%">
				<tr>
			    	<td colspan="4" width="50" >
						 <div style="width:700px;height:50px">
						  
						  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">市级审核</span>	
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
								<td>乡镇：</td><td><select name="xiaoqu" class="selected_font">
         		<option value="0">请选择</option>
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
         		</select></td>
         						<td>暂估月份：</td>
								<td>
									<input id="zgyf" type="text" name="accountmonth"
										value="<%if (null != request.getParameter("accountmonth"))
				out.print(request.getParameter("accountmonth"));%>"
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
								<td>站点名称：</td>
							              <td>
							              <input type="text" class="selected_font" name="zdname" value="<%if (null != request.getParameter("zdname"))
				out.print(request.getParameter("zdname"));%>" class="form" 
                                               />
							              </td>
								
								<td>财务审核状态：</td>
         						<td><select name="cwshenhe" class="selected_font">
         							<option value="5">请选择</option>
         							<option value="0">未审核</option>
         							<option value="2">审核不通过</option>
         							<option value="1">审核通过</option>
         							</select></td>

								
							
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
				String str = "";

				if (shi != null && !shi.equals("") && !shi.equals("0")) {
					whereStr = whereStr + " and z.shi='" + shi + "'";
					str = str + " and z.shi='" + shi + "'";
				}
				if (xian != null && !xian.equals("") && !xian.equals("0")) {
					whereStr = whereStr + " and z.xian='" + xian + "'";
					str = str + " and z.xian='" + xian + "'";
				}
				if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
					whereStr = whereStr + " and z.xiaoqu='" + xiaoqu + "'";
					str = str + " and z.xiaoqu='" + xiaoqu + "'";
				}

				if (accountmonth != null && accountmonth != ""
						&& !accountmonth.equals("0")) {
					whereStr = whereStr + " AND s.zgyf ='" + accountmonth + "'";
					str = str + " AND s.zgyf ='" + accountmonth + "'";
				}
				if (zdname != null && zdname != "" && !zdname.equals("0")) {
					whereStr = whereStr + " AND z.jzname ='" + zdname + "'";
					str = str + " AND z.jzname ='" + zdname + "'";
				}

				/*if(dgpch!=null && dgpch!="" && !dgpch.equals("0")){
					whereStr=whereStr+" AND x.DGPCH like'%"+dgpch+"%'";
				}
				if(yppch!=null && yppch!="" && !yppch.equals("0")){
					whereStr=whereStr+" AND x.YPPCH like'%"+yppch+"%'";
				}*/
				if (cwshenhe != null && cwshenhe != "" && !cwshenhe.equals("5")) {
					whereStr = whereStr + " AND s.cwshzt ='" + cwshenhe + "'";
				}

				if (loginId1 != null && !loginId1.equals("")) {
					loginId = loginId1;
					shi = "1";
				}
				String count1 = "0", count2 = "0", count3 = "0", count4 = "0.00";
			%>
			<div style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="2100px" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								城市
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								乡镇
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								上次暂估时间
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								本次暂估时间
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								省定标
							</div>
						</td>
					
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								单价
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								暂估月份
							</div>
						</td>
						<%--<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								用电量
							</div>
						</td>
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电量调整
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								暂估电量
							</div>
						</td>
						--%><td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								暂估电费
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								人工审核状态
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								人工审核员
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								人工审核时间
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
							市级审核状态
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级审核员
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级审核时间
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级领导审核状态
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级领导审核人
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级领导审核时间
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								财务审核状态
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								财务审核员
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								财务审核时间
							</div>
						</td>
					</tr>
	<%
		List<zgsj> list = null;
		if ("daochu".equals(request.getParameter("command"))
				|| "chaxun".equals(request.getParameter("command"))) {
			list = bean.getZgmx(whereStr, loginId);
		} else {
			list = null;
		}
		String id = "";
		String shi2 = "";//市
		String xian2 = "";
		String xiaoqu2 = "";
		String jzname = "";//站点名称
		String jztype = "";//站点类型
		String lastzgtime = "";//上次暂估时间
		String thiszgtime = "";//本次暂估时间
		String sdb = "";//省定标
		String danjia = "";//单价
		String zgmonth = "";//暂估月份
		String zgdianfei = "";//暂估电费
		String rgshenhezt = "";//人工审核状态
		String rgshenheyuan = "";//人工审核员
		String rgshenhesj = ""; //人工审核时间
		String sjshzt = "";//市级审核状态
		String sjshenheyuan = "";// 市级审核员
		String sjshenhesj = "";//市级审核时间
		String cwshenhezt = "";//财务审核状态
		String cwshenheyuan = "";//财务审核员
		String cwshenhesj = "";//财务审核时间
		String sjeshzt = "";//市级2审核状态
		String sjeshr = "";//市级2审核人
		String sjeshsj = "";//市级2审核时间
		
		int intnum = 0;
		if (list != null) {
			for (zgsj bean2 : list) {
				id = bean2.getId();
				shi2 = bean2.getShi();
				xian2 = bean2.getQuxian();
				xiaoqu2 = bean2.getXiangzhen();
				jzname = bean2.getZdname();
				jztype = bean2.getZdlx();
				lastzgtime = bean2.getZfqssj();
				thiszgtime = bean2.getZfjssj();
				sdb = bean2.getSdb();
				danjia = bean2.getDanjia();
				zgmonth = bean2.getZgyf();
				zgdianfei = bean2.getMoney();
				rgshenhezt = bean2.getQxshzt();
				rgshenheyuan = bean2.getQxshr();
				rgshenhesj = bean2.getQxshsj();
				sjshzt = bean2.getSjshzt();
				sjshenheyuan = bean2.getSjshr();
				sjshenhesj = bean2.getSjshsj();
				cwshenhezt = bean2.getCwshzt();
				cwshenheyuan = bean2.getCwshr();
				cwshenhesj = bean2.getCwshsj();
				sjeshzt = bean2.getSjeshzt();
				
				sjeshr = bean2.getSjeshr();
				sjeshsj = bean2.getSjeshsj();
				

				if (shi2 == null || shi2.equals("null") || shi2.equals("")
						|| shi2.equals(" ") || shi2 == "") {
					shi2 = "";
				}
				if (xian2 == null || xian2.equals("null")
						|| xian2.equals("") || xian2.equals(" ")
						|| xian2 == "") {
					xian2 = "";
				}
				if (xiaoqu2 == null || xiaoqu2.equals("null")
						|| xiaoqu2.equals("") || xiaoqu2.equals(" ")
						|| xiaoqu2 == "") {
					xiaoqu2 = "";
				}
				if (jzname == null || jzname.equals("null")
						|| jzname.equals("") || jzname.equals(" ")
						|| jzname == "") {
					jzname = "";
				}
				if (jztype == null || jztype.equals("null")
						|| jztype.equals("") || jztype.equals(" ")
						|| jztype == "") {
					jztype = "";
				}
				if (lastzgtime == null || lastzgtime.equals("null")
						|| lastzgtime.equals("") || lastzgtime.equals(" ")
						|| lastzgtime == "") {
					lastzgtime = "";
				} else {
					lastzgtime = lastzgtime.substring(0, 10);
				}
				if (thiszgtime == null || thiszgtime.equals("null")
						|| thiszgtime.equals("") || thiszgtime.equals(" ")
						|| thiszgtime == "") {
					thiszgtime = "";
				} else {
					thiszgtime = thiszgtime.substring(0, 10);
				}
				if (sdb == null || sdb.equals("null") || sdb.equals("")
						|| sdb.equals(" ") || sdb == "") {
					sdb = "0";
				}
				if (danjia == null || danjia.equals("null")
						|| danjia.equals("") || danjia.equals(" ")
						|| danjia == "") {
					danjia = "0";
				}
				if (zgmonth == null || zgmonth.equals("null")
						|| zgmonth.equals("") || zgmonth.equals(" ")
						|| zgmonth == "") {
					zgmonth = "";
				}/*else{
					zgmonth = zgmonth.substring(0,10);
					}*/
				
				if (zgdianfei == null || zgdianfei.equals("null")
						|| zgdianfei.equals("") || zgdianfei.equals(" ")
						|| zgdianfei == "") {
					zgdianfei = "0";
				}
				if (rgshenhezt == null || rgshenhezt.equals("null")
						|| rgshenhezt.equals("") || rgshenhezt.equals(" ")
						|| rgshenhezt == "") {
					rgshenhezt = "";
				}
				if (rgshenheyuan == null || rgshenheyuan.equals("null")
						|| rgshenheyuan.equals("")
						|| rgshenheyuan.equals(" ") || rgshenheyuan == "") {
					rgshenheyuan = "";
				}
				if (rgshenhesj == null || rgshenhesj.equals("null")
						|| rgshenhesj.equals("") || rgshenhesj.equals(" ")
						|| rgshenhesj == "") {
					rgshenhesj = "";
				} else {
					rgshenhesj = rgshenhesj.substring(0, 10);
				}
				if (sjshzt == null || sjshzt.equals("null")
						|| sjshzt.equals("") || sjshzt.equals(" ")
						|| sjshzt == "") {
					sjshzt = "";
				}
				if (sjshenheyuan == null || sjshenheyuan.equals("null")
						|| sjshenheyuan.equals("")
						|| sjshenheyuan.equals(" ") || sjshenheyuan == "") {
					sjshenheyuan = "";
				}
				if (sjshenhesj == null || sjshenhesj.equals("null")
						|| sjshenhesj.equals("") || sjshenhesj.equals(" ")
						|| sjshenhesj == "") {
					sjshenhesj = "";
				} else {
					sjshenhesj = sjshenhesj.substring(0, 10);
				}
				if (cwshenhezt == null || cwshenhezt.equals("null")
						|| cwshenhezt.equals("") || cwshenhezt.equals(" ")
						|| cwshenhezt == "") {
					cwshenhezt = "";
				}
				System.out.println("------"+cwshenheyuan);
				if (cwshenheyuan == null || cwshenheyuan.equals("null")
						|| cwshenheyuan.equals("")
						|| cwshenheyuan.equals(" ") || cwshenheyuan == "") {
					cwshenheyuan = "";
				}
				if (cwshenhesj == null || cwshenhesj.equals("null")
						|| cwshenhesj.equals("") || cwshenhesj.equals(" ")
						|| cwshenhesj == "") {
					cwshenhesj = "";
				} else {
					cwshenhesj = cwshenhesj.substring(0, 10);
				}
				if (sjeshzt == null || sjeshzt.equals("null")
						|| sjeshzt.equals("") || sjeshzt.equals(" ")
						|| sjeshzt == "") {
					sjeshzt = "";
				}
				if (sjeshr == null || sjeshr.equals("null")
						|| sjeshr.equals("") || sjeshr.equals(" ")
						|| sjeshr == "") {
					sjeshr = "";
				}
				if (sjeshsj == null || sjeshsj.equals("null")
						|| sjeshsj.equals("") || sjeshsj.equals(" ")
						|| sjeshsj == "") {
					sjeshsj = "";
				}else {
					sjeshsj = sjeshsj.substring(0, 10);
				}
				if (rgshenhezt != null && rgshenhezt.equals("2")) {
					rgshenhezt = "审核不通过";
				} else if (rgshenhezt != null && rgshenhezt.equals("1")) {
					rgshenhezt = "审核通过";
				} else if (rgshenhezt != null && rgshenhezt.equals("0")) {
					rgshenhezt = "未审核";
				}
				if (sjshzt != null && sjshzt.equals("2")) {
					sjshzt = "审核不通过";
				} else if (sjshzt != null && sjshzt.equals("1")) {
					sjshzt = "审核通过";
				} else if (sjshzt != null && sjshzt.equals("0")) {
					sjshzt = "未审核";
				}
				if (cwshenhezt != null && cwshenhezt.equals("2")) {
					cwshenhezt = "审核不通过";
				} else if (cwshenhezt != null && cwshenhezt.equals("1")) {
					cwshenhezt = "审核通过";
				} else if (cwshenhezt != null && cwshenhezt.equals("0")) {
					cwshenhezt = "未审核";
				}
				if (sjeshzt != null && sjeshzt.equals("2")) {
					sjeshzt = "审核不通过";
				} else if (sjeshzt != null && sjeshzt.equals("1")) {
					sjeshzt = "审核通过";
				} else if (sjeshzt != null && sjeshzt.equals("0")) {
					sjeshzt = "未审核";
				}
				DecimalFormat mat = new DecimalFormat("0.00");
				DecimalFormat mat2 = new DecimalFormat("0.0000");
				sdb = mat.format(Double.parseDouble(sdb));
				danjia = mat2.format(Double.parseDouble(danjia));
			
				zgdianfei = mat.format(Double.parseDouble(zgdianfei));

				if (intnum % 2 == 0) {
					color = "#FFFFFF";

				} else {
					color = "#DDDDDD";
				}

				intnum++;
				//System.out.println("222:"+bean2.getBzpld());
				//int intnum=xh = pagesize*(curpage-1)+1;
	%>

					<tr bgcolor="<%=color%>">
						<td>
							<div align="center"><%=intnum%></div>
						</td>
						<!--<td>
							<div align="center">
								<input type="checkbox" name="test[]" value="<%=id%>" />
								<input type="hidden" type="checkbox"  name="test1[]" value="<%=cwshenhezt%>" />
							</div>
						</td>
						--><td>
							<div align="center"><%=shi2%></div>
						</td>
						<td>
							<div align="center"><%=xian2%></div>
						</td>
						<td>
							<div align="center"><%=xiaoqu2%></div>
						</td>
						<td>
							<div align="center"><%=jzname%></div>
						</td>
						<td>
							<div align="center"><%=jztype%></div>
						</td>
						<td>
							<div align="center"><%=lastzgtime%></div>
						</td>
						<td>
							<div align="center"><%=thiszgtime%></div>
						</td>
						<td>
							<div align="center"><%=sdb%></div>
						</td>
					
						<td>
							<div align="center"><%=danjia%></div>
						</td>
						<td>
							<div align="center"><%=zgmonth%></div>
						</td>
						<%--<td>
							<div align="center"><%=yongdianliang%></div>
						</td>
						<td>
							<div align="center"><%=dianliangtz%></div>
						</td>
						<td>
							<div align="left"><%=zgdianliang%></div>
						</td>
						--%><td>
							<div align="center"><%=zgdianfei%></div>
						</td>
						<td>
							<div align="center"><%=rgshenhezt%></div>
						</td>
						<td>
							<div align="center"><%=rgshenheyuan%></div>
						</td>
						<td>
							<div align="center"><%=rgshenhesj%></div>
						</td>
						<td>
							<div align="center"><%=sjshzt%></div>
						</td>
						<td>
							<div align="center"><%=sjshenheyuan%></div>
						</td>
						<td>
							<div align="center"><%=sjshenhesj%></div>
						</td>
						<td>
							<div align="center"><%=sjeshzt%></div>
						</td>
						<td>
							<div align="center"><%=sjeshr%></div>
						</td>
						<td>
							<div align="center"><%=sjeshsj%></div>
						</td>
						<td>
							<div align="center"><%=cwshenhezt%></div>
						</td>
						<td>
							<div align="center"><%=cwshenheyuan%></div>
						</td>
						<td>
							<div align="center"><%=cwshenhesj%></div>
						</td>
					
					
							<!--<td>
							<div align="center">
								<a
									href="javascript:lookDetailssz('<%=id%>')">查看详细</a>
							</div>
						</td>
					--></tr>
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
					</td>
				</tr>
				
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
				.open(path + "/web/jzcbnewfunction/shishxx.jsp?zdid=" + id, '',
						'width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
	}
</script>
<script type="text/javascript">
	var XMLHttpReq;
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
	    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
	          	updateQx(res);                   
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
	function processResponse_xian() {
		
		if (XMLHttpReq.readyState == 4) { // 判断对象状态
	    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
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
	
	//审核通过
	  function passCheck(){
	  var loginName='<%=loginName%>';
        var m = document.getElementsByName('test[]');
        var mc = document.getElementsByName('test1[]');
      //  var cuu=document.form1.currentmonth.value;
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr1 = ""; 
       	var chooseIdStr2 = ""; 
       	var con=0;
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       		if(m[i].checked == true){
       			if(mc[i].value=='2'){
       			con++;
       			}
       		}
       	}
       	if(con!=0){
       	alert("省级审核已经通过不允许此操作！");
       	return;
       	}else{
       	if(count!=0){
       		//if(cuu!=""&&cuu!=null){
		       	if(count%240==0){
		       		n=count/240-1;
		       	}else{
		       		n=(count-(count%240))/240;
		       	}
		        for(var i = 0; i < l; i++){
		         if(m[i].checked == true){
		             bz+=1;
		             count1+=1;
		             //var j = m[i].value.toString().indexOf("ssss=");
		            // var chooseIdStr3 = m[i].value.toString().substring(0,j);
		           //  var zflx1 = m[i].value.toString().substring(j+5,m[i].value.toString().length);
		           var chooseIdStr3 = m[i].value.toString();
		            // if(zflx1=="月结"){
			             chooseIdStr1 = chooseIdStr1 +"'" +chooseIdStr3 +"',";
		            // }//else if(zflx1=="预支"){
		             	//count2+=1;
		             	//chooseIdStr1 = chooseIdStr1 +"'"+ chooseIdStr3 +"',";
		             	//chooseIdStr2 = chooseIdStr2 +"'"+ chooseIdStr3 +"',";
		             }//else if(zflx1=="合同"||zflx1=="插卡"){
		             	// chooseIdStr2 = chooseIdStr2 +"'" + chooseIdStr3 +"',";
		            // } 
		            //alert(chooseIdStr1);
		        }
		          chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
		          document.form1.action=path+"/servlet/Cbzdsjshenhe?action=cbzdssh&chooseIdStr1="+chooseIdStr1+"&loginName="+loginName;
					document.form1.submit();
		    /*      if(count1<=240*n||((bz+count2)>=239&&(bz+count2)<=241)){
		         	  if(((bz+count2)/240==1)||((bz+count2)>=239&&(bz+count2)<=241)){
				        chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
				        chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
				        document.form1.action=path+"/servlet/check?action=checkff1&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2;
			            chooseIdStr1 = ""; 
		       			chooseIdStr2 = "";
		       			bz=0;
		       			count2=0;
			            	          	
			          }
			       }else if(count==count1&&bzw==1){
			          chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
				      chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
				      bzw=0;
			          document.form1.action=path+"/servlet/check?action=checkff&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2;
			          document.form1.submit(); 
			       }     */       
		      //  } 
		  // }else{
		       //alert("会计月份不能为空！");
		   //}
       }else{
        alert("请选择信息！");
       }
       }
   }
	//审核不通过
	 function passCheckNo(){
        var m = document.getElementsByName('test[]');
         var mc = document.getElementsByName('test1[]');
        var loginName='<%=loginName%>';
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
        var con=0;
       	var chooseIdStr1 = ""; 
       	var chooseIdStr2 = ""; 
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       		if(m[i].checked == true){
                if(mc[i].value=='2'){
                 con++;
                }
       		}
       	}
       	if(con!=0){
       	alert("省级审核已经通过不允许此操作！");
       	return;
       	}else{
       	if(count!=0){
	       	if(count%240==0){
	       		n=count/240-1;
	       	}else{
	       		n=(count-(count%240))/240;
	       	}
	        for(var i = 0; i < l; i++){
	         if(m[i].checked == true){
	             bz+=1;
	             count1+=1;
	              var chooseIdStr3 = m[i].value.toString();
			      chooseIdStr1 = chooseIdStr1 +"'" +chooseIdStr3 +"',";
			      }
			      }
	              chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
		          document.form1.action=path+"/servlet/Cbzdsjshenhe?action=cbzdsshno&chooseIdStr1="+chooseIdStr1+"&loginName="+loginName;
				  document.form1.submit();
	      /*       var j = m[i].value.toString().indexOf("ssss=");
	             var chooseIdStr3 = m[i].value.toString().substring(0,j);
	             var zflx1 = m[i].value.toString().substring(j+5,m[i].value.toString().length);
	             if(zflx1=="月结"){
		             chooseIdStr1 = chooseIdStr1 +"'" +chooseIdStr3 +"',";
	             }else if(zflx1=="预支"){
		             	count2+=1;
		             	chooseIdStr1 = chooseIdStr1 +"'"+ chooseIdStr3 +"',";
		             	chooseIdStr2 = chooseIdStr2 +"'"+ chooseIdStr3 +"',";
		         }else if(zflx1=="合同"||zflx1=="插卡"){
	             	 chooseIdStr2 = chooseIdStr2 +"'" + chooseIdStr3 +"',";
	             } 
	        }*/
	         /* if(count1<=240*n||((bz+count2)>=239&&(bz+count2)<=241)){
	         	  if(((bz+count2)/240==1)||((bz+count2)>=239&&(bz+count2)<=241)){
			        chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
			        chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
			        document.form1.action = path+"/servlet/check?action=checkffno1&chooseIdStr1="+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
		            chooseIdStr1 = ""; 
	       			chooseIdStr2 = "";
	       			bz=0;
	       			count2=0;
		            document.form1.submit();	          	
		          }
		       }else if(count==count1&&bzw==1){
		          chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
			      chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
			      bzw=0;
		          document.form1.action = path+"/servlet/check?action=checkffno&chooseIdStr1="+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
		          document.form1.submit(); 
		       } */           
	        
        }else{
          alert("请选择信息！");
        }
        }
   }
   	//取消审核
	  function passCheckqx(){
	  var loginName='<%=loginName%>';
        var m = document.getElementsByName('test[]');
        var mc = document.getElementsByName('test1[]');
      //  var cuu=document.form1.currentmonth.value;
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr1 = ""; 
       	var chooseIdStr2 = ""; 
       	var con=0;
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       		if(m[i].checked == true){
       		if(mc[i].value=='2'){
       		con++;
       		}
       		}
       	}
       	if(con!=0){
       	alert("省级审核已经通过不允许此操作！");
       	}else{
       	if(count!=0){
       		
		       	if(count%240==0){
		       		n=count/240-1;
		       	}else{
		       		n=(count-(count%240))/240;
		       	}
		        for(var i = 0; i < l; i++){
		         if(m[i].checked == true){
		             bz+=1;
		             count1+=1;
		          
		           var chooseIdStr3 = m[i].value.toString();
		            
			             chooseIdStr1 = chooseIdStr1 +"'" +chooseIdStr3 +"',";
		      
		        }}
		          chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
		          document.form1.action=path+"/servlet/Cbzdsjshenhe?action=cbzdsshqx&chooseIdStr1="+chooseIdStr1+"&loginName="+loginName;
					document.form1.submit();

       }else{
        alert("请选择信息！");
       }
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
		document.form1.accountmonth.value = '<%=accountmonth%>';
		document.form1.zdname.value = '<%=zdname%>';
		document.form1.cwshenhe.value = '<%=cwshenhe%>';
	</script>
</html>

