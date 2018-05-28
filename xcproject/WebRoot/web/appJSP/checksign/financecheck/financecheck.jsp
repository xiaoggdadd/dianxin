<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page
	import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean"%>
<%@ page
	import="com.noki.electricfees.javabean.ElectricFeesFormBean,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";

	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
	String currentmonth = formatter.format(date);
	String currentmonth1 = request.getParameter("kjyf") != null ? request.getParameter("kjyf"): currentmonth;
	
	int hourse = date.getHours();//获取当前时间   小时
	int dateString = date.getDate();//获取当前时间   日期
	int monthString = date.getMonth()+1;//获取当前月份
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
%>

<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'financecheck.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link type="text/css" rel="Stylesheet"
			href="<%=path%>/web/appCSS/pageCss/page.css" />
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/someJs/page.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/someJs/m.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>

		<script type="text/javascript">
var path = '<%=path%>';
</script>

	</head>

	<body>
		<jsp:useBean id="commBean" scope="page"
			class="com.noki.mobi.common.CommonAccountBean"></jsp:useBean>
		<jsp:useBean id="ztcommon" scope="page"
			class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
		<%
			String roleId = (String) session.getAttribute("accountRole");
			String permissionRights = commBean.getPermissionRight(roleId,
					"0806");
		%>
		<form name="form1" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
	 			<tr>
      				<td colspan="4" width="50">
						<div style="width:700px;height:50px">
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">财务电费审核</span>	
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

					</td>
				</tr>
			</table>
			<input type="hidden" name="loginId" id="loginId"
				value="<%=loginId%>">
			<table>
				<tr class="form_label">
					<td>
						城市：
					</td>
					<td>
						<select name="shi" class="selected_font" onchange="changeCity()">
							<option value="0">
								请选择
							</option>

							<%
								ArrayList shenglist = new ArrayList();
								shenglist = commBean.getShi(loginId);
								if (shenglist != null) {
									String sfid = "", sfnm = "";
									int scount = ((Integer) shenglist.get(0)).intValue();
									for (int i = scount; i < shenglist.size() - 1; i += scount) {
										sfid = (String) shenglist.get(i
												+ shenglist.indexOf("AGCODE"));
										request.setAttribute("sfid", sfid);
										sfnm = (String) shenglist.get(i
												+ shenglist.indexOf("AGNAME"));
							%>
							<option value="<%=sfid%>"
								<c:choose>
							<c:when test="${user.shi ==null}">
								<%if (sfid != null) {
						if (sfid.equals(shi)) {%>
										selected
										<%}
					}%>
							</c:when>
							<c:otherwise >
								<c:if test="${user.shi eq sfid}">selected</c:if>
							</c:otherwise>
							</c:choose>>
								<%=sfnm%></option>

							<%
								}
								}
							%>
						</select>


					</td>


					<td>
						区县:
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
										request.setAttribute("agcode", agcode);
										agname = (String) xianlist.get(i
												+ xianlist.indexOf("AGNAME"));
							%>
							<option value="<%=agcode%>"
								<c:if test="${user.xian eq agcode}">selected</c:if>><%=agname%></option>
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
						<select name="xiaoqu" class="selected_font" onchange="">
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
										request.setAttribute("xzcode", agcode);
										agname = (String) xiaoqulist.get(i
												+ xiaoqulist.indexOf("AGNAME"));
							%>
							<option value="<%=agcode%>"
								<c:if test="${user.xiaoqu eq xzcode}">selected</c:if>><%=agname%></option>
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
						<div id="chaxun"
							style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -250px; float: right; top: 0px">
							<img alt="" src="<%=path%>/images/chaxun.png" width="100%"
								height="100%" onclick="chaxun()" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white"
								onclick="chaxun()">查询</span>
						</div>
						<%
							}
						%>
					</td>
				</tr>
			</table>
			<p id="box3" style="display: none">
				<table>
					<tr class="form_label">
						<td>
							站点名称：
						</td>
						<td>
							<input type="text" name="zdmc" value="${user.zdmc}"
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
											request.setAttribute("cd", code);
											name = (String) stationtype.get(i
													+ stationtype.indexOf("NAME"));
								%>
								<option value="<%=code%>"
									<c:if test="${user.zdlx eq cd}">selected</c:if>><%=name%></option>
								<%
									}
									}
								%>
							</select>
						</td>
						<td>
							站点属性:
						</td>
						<td>
							<select name="zdsx" class="selected_font" onchange="kzinfo()">
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
											request.setAttribute("zdco", code);
											name = (String) zdsx.get(i + zdsx.indexOf("NAME"));
								%>
								<option value="<%=code%>"
									<c:if test="${user.zdsx eq zdco}">selected</c:if>><%=name%></option>
								<%
									}
									}
								%>
							</select>
						</td>


						<td>
							流程号：
						</td>
						<td>
							<input type="text" name="lch" value="${user.lch}"
								class="selected_font" />
						</td>

					</tr>
					<tr class="form_label">

						<td>
							报账月份：
						</td>
						<td>
							<input type="text" class="selected_font" name="bzyf"
								value="${user.bzyf}"
								onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
								class="form" />
						</td>
						<td>
							录入日期：
						</td>
						<td>
							<input type="text" class="selected_font" name="lrrq"
								value="${user.lrrq}"
								onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
								class="form" />
						</td>

						<td>
							站点启用状态
						</td>
						<td>
							<select name="zdqyzt" class="selected_font">
								<option value=""
									<c:if test="${user.zdqyzt eq ''}">selected</c:if>>
									请选择
								</option>
								<option value="1"
									<c:if test="${user.zdqyzt eq '1'}">selected</c:if>>
									启用
								</option>
								<option value="0"
									<c:if test="${user.zdqyzt eq '0'}">selected</c:if>>
									未启用
								</option>
							</select>
						</td>
						<td>
							电表启用状态
						</td>
						<td>
							<select name="dbqyzt" class="selected_font">
								<option value=""
									<c:if test="${user.dbqyzt eq ''}">selected</c:if>>
									请选择
								</option>
								<option value="1"
									<c:if test="${user.dbqyzt eq '1'}">selected</c:if>>
									启用
								</option>
								<option value="0"
									<c:if test="${user.dbqyzt eq '0'}">selected</c:if>>
									未启用
								</option>
							</select>
						</td>

					</tr>
					<tr class="form_label">

						<td>
							财务审核状态：
						</td>
						<td>
							<select name="cwshzt" class="selected_font">
								<option value="1"
									<c:if test="${user.cwshzt eq '1'}">selected</c:if>>
									审核未通过
								</option>
								<option value=""
									<c:if test="${user.cwshzt eq ''}">selected</c:if>>
									请选择
								</option>
								<option value="2"
									<c:if test="${user.cwshzt eq '2'}">selected</c:if>>
									审核通过
								</option>

								<option value="-1"
									<c:if test="${user.cwshzt eq '-1'}">selected</c:if>>
									审核不通过
								</option>
							</select>
						</td>

						<td>
							票据类型
						</td>
						<td>
							<select name="pjlx" class="selected_font">
								<option value="">
									请选择
								</option>
								<%
									ArrayList listpjlx = new ArrayList();
									listpjlx = ztcommon.getSelctOptions("pjlx");

									if (listpjlx != null) {
										String code = "", name = "";
										int cscount = ((Integer) listpjlx.get(0)).intValue();
										for (int i = cscount; i < listpjlx.size() - 1; i += cscount) {
											code = (String) listpjlx.get(i + listpjlx.indexOf("CODE"));
											request.setAttribute("pjcode", code);
											name = (String) listpjlx.get(i + listpjlx.indexOf("NAME"));
								%>
								<option value="<%=code%>"
									<c:if test="${user.pjlx eq pjcode}">selected</c:if>><%=name%></option>
								<%
									}
									}
								%>
							</select>
						</td>
					</tr>
				</table>
			</p>
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
			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="2300px" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="2	%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<input type="checkbox" name="test" onclick="chooseAll()" />
								全选
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								所在地区
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								上次抄表时间
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								本次抄表时间
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								上次读数
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								本次读数
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								起始月份
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								结束月份
							</div>
						</td>
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								倍率
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电量调整
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								耗电量
							</div>
						</td>
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电费单价
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电费调整
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								实际电费金额
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账月份
							</div>
						</td>
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电费支付类型
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								票据类型
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								票据金额
							</div>
						</td>

						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								流程号
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点属性
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电流类型
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								用电设备
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								票据编号
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								自动审核
							</div>
						</td>
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								人工审核
							</div>
						</td>
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								财务审核
							</div>
						</td>
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表ID
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								人工审核通过原因
							</div>
						</td>
					</tr>
					<c:forEach items="${Mess}" var="m" varStatus="sum">
						<tr <c:if  test="${sum.count%2==0}">bgcolor="#DDDDDD"</c:if>
							<c:if  test="${sum.count%2==1}">bgcolor="#FFFFFF"</c:if>>
							<td align="center">
								${sum.count}
							</td>
							<td align="center">
								<input type="checkbox" name="test[]"
									value="${m.dfuuid}+zflx:+${m.bzw}" />
								<input type="hidden" type="checkbox" name="test1[]" value="${m.accountmonth}" />
							</td>
							<td align="center">
								${m.szdq}
							</td>
							<td align="center">
								${m.jzname}
							</td>
							<td align="center">
								${m.stationtype}
							</td>
							<td align="center">
								${m.lastdatetime}
							</td>
							<td align="center">
								${m.thisdatetime}
							</td>
							<td align="center">
								${m.lastdegree}
							</td>
							<td align="center">
								${m.thisdegree}
							</td>
							<td align="center">
								${m.startmonth}
							</td>
							<td align="center">
								${m.endmonth}
							</td>
							<td align="center">
								${m.beilv}
							</td>
							<td align="center">
								${m.floatdegree}
							</td>
							<td align="center">
								${m.blhdl}
							</td>
							<td align="center">
								<fmt:formatNumber value="${m.unitprice}" type="number"
									maxFractionDigits="4" minFractionDigits="4" />
							</td>
							<td align="center">
								${m.floatpay}
							</td>
							<td align="center">
								${m.actualpay}
							</td>
							<td align="center">
								${m.accountmonth}
							</td>
							<td align="center">
								${m.dfzflx}
							</td>
							<td align="center">
								${m.notetypeid}
							</td>
							<td align="center">
								${m.pjjedf}
							</td>
							<td align="center">
								${m.liuchenghao}
							</td>
							<td align="center">
								${m.property}
							</td>
							<td align="center">
								${m.dllx}
							</td>
							<td align="center">
								${m.ydsb}
							</td>
							<td align="center">
								${m.noteno}
							</td>
							<td align="center">
								${m.autoauditstatus}
							</td>
							<td align="center">
								<c:if test="${m.manualauditstatus==1}">通过</c:if>
								<c:if test="${m.manualauditstatus==2}">通过</c:if>
								<c:if test="${m.manualauditstatus==-1}">通过</c:if>
								<c:if test="${m.manualauditstatus==-2}">未通过</c:if>
								<c:if test="${m.manualauditstatus==0}">未审核</c:if>
							</td>
							<td align="center">
								<c:if test="${m.manualauditstatus==1}">未通过</c:if>
								<c:if test="${m.manualauditstatus==2}">通过</c:if>
								<c:if test="${m.manualauditstatus!=1&&m.manualauditstatus!=2}">不通过</c:if>
							</td>
							<td align="center">
								${m.dbid}
							</td>
							<td align="center">
								${m.rgshtgyy}
							</td>

						</tr>
					</c:forEach>
					<c:if test="${Mess==null }">
						<c:forEach begin="0" end="6" step="1">
							<tr bgcolor="#FFFFFF">
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

							<tr bgcolor="#DDDDDD">
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
						</c:forEach>
					</c:if>

					<tr height="23">
						<td bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								合计:
							</div>
						</td>
						<td bgcolor="#DDDDDD">
							<div align="left" class="bttcn">
								总电量：
							</div>
						</td>
						<td bgcolor="#DDDDDD">
							<div align="left" class="bttcn">
								${sumdl }度
							</div>
						</td>
						<td bgcolor="#DDDDDD">
							<div align="left" class="bttcn">
								实际电费总和：
							</div>
						</td>
						<td colspan="29" bgcolor="#DDDDDD">
							<div align="left" class="bttcn">
								${sumdf }元
							</div>
						</td>
					</tr>
				</table>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
							<img src="<%=path%>/images/daochu.png" width="100%" height="100%"
								onclick="daochu()">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white"
								onclick="daochu()">导出</span>
						</div>
						<div id="butongguo"
							style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right: 10px">
							<img alt="" src="<%=request.getContextPath()%>/images/baocun.png"
								width="100%" height="100%" onclick="butongguo()" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white"
								onclick="butongguo()">不通过</span>
						</div>
						<div id="tongguo"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
							<img src="<%=path%>/images/2chongzhi.png" width="100%"
								height="100%" onclick="tongguo()">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white"
								onclick="tongguo()">通过</span>
						</div>


						<div
							style="width: 130px; height: 23px; cursor: pointer; float: right; position: relative; right: 50px">
							<input type="text" class="selected_font1" name="kjyf" id="kjyf"
								value="<%=currentmonth1%>"
								onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
								class="form" />
							<span class="style2">&nbsp;*</span>
						</div>
						<div
							style="width: 100px; height: 23px; cursor: pointer; float: right; position: relative; right: 50px">
							<span
								style="font-size: 12px; position: absolute; left: 40px; top: 5px; color: black">会计月份：</span>
						</div>

					</td>
				</tr>
			</table>
			<input type="hidden" value="${str}" name="str" id="str" />
			<input type="hidden" value="${str1}" name="str1" id="str1" />
			<input type="hidden" value="${str2}" name="str2" id="str2" />
			<input type="hidden" value="${user.lrrq}" name="lr" id="lr" />
			<input type="hidden" value="${user.zdmc}" name="mc" id="mc" />
			<input type="hidden" name="bzw1" id="bzw1" value="1" />
		</form>
	</body>
	<script language="javascript">
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
function exportad() {
	var whereStr = document.form1.str.value;
	var str1 = document.form1.str1.value;
	var str2 = document.form1.str2.value;
	var lrrq = document.form1.lr.value;
	var zdmc = document.form1.mc.value;
	document.form1.action = "servlet/FinanceDaochuServlet?whereStr=" + whereStr
			+ "&str1=" + str1 + "&str2=" + str2 + "&lrrq=" + lrrq+ "&zdmc=" + zdmc
			+ "&command=daochu";
	document.form1.submit();
}
</script>

	<script type="text/javascript">
var path = '<%=path%>';
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
	XMLHttpReq1.open("POST", url, false);
	if (para == "checkpass1") {
		XMLHttpReq1.onreadystatechange = processResponse_checkcity1;
	} else if (para == "checknopass1") {
		XMLHttpReq1.onreadystatechange = processResponse_checkcityno1;
	} else {
		XMLHttpReq1.onreadystatechange = processResponse;//指定响应函数
	}
	XMLHttpReq1.send(null);
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
function processResponse_checkcityno1() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			document.form1.bzw1.value = XMLHttpReq1.responseText;
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
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
function passCheck1(chooseIdStr1, chooseIdStr2,kjyf) {//通过240
	sendRequest1(path
			+ "/servlet/FinanceCheckServlet?action=checkpass1&chooseIdStr1="
			+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2+"&kjyfa=" + kjyf, "checkpass1");
}
function passnoCheck1(chooseIdStr1, chooseIdStr2,kjyf) {//通过240
	sendRequest1(path
			+ "/servlet/FinanceCheckServlet?action=checknopass1&chooseIdStr1="
			+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2+"&kjyfa=" + kjyf, "checknopass1");
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
function delLogs() {
	var beginTime = document.form1.delBeginTime.value
	var endTime = document.form1.delEndTime.value

	if (checkNotnull(document.form1.delBeginTime, "开始时间")
			&& checkNotnull(document.form1.delEndTime, "结束时间")) {
		if (beginTime > endTime) {
			alert("开始时间不能大于结束时间！");
			return;
               		 }
         if(confirm("确定要删除，不可恢复！")){
    	  document.form1.action=path+"/servlet/log?delBeginTime="+beginTime+"&delEndTime="+endTime
              document.form1.submit()
  		 }
                }

	}
	
  function passCheck(){
   }
    function delad(electricfeeId){
       if(confirm("您确定删除此电费信息？")){
              document.form1.action=path+"/servlet/electricfees?action=del&degreeid="+electricfeeId;
       	      document.form1.submit();
       }
    }
    function modifyad(electricfeeId){
               document.form1.action=path+"/web/electricfees/modifyElectricFees.jsp?degreeid="+electricfeeId;
               document.form1.submit();
  
    }
    
</script>

	<script type="text/javascript">
//改变城市
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
//改变乡镇
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
function tongguo() {
	var m = document.getElementsByName('test[]');
	var cuu = document.form1.kjyf.value;
	var arr = new Array;
	var l = m.length;
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var bzw = 1;
	var count2 = 0;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
	}
	if (count != 0) {
		if (cuu != "" && cuu != null) {
			if (count % 240 == 0) {
				n = count / 240 - 1;
			} else {
				n = (count - (count % 240)) / 240;
			}
			for ( var i = 0; i < l; i++) {
				if (m[i].checked == true) {
					bz += 1;
					count1 += 1;
					var j = m[i].value.toString().indexOf("+zflx:+");
					var chooseIdStr3 = m[i].value.toString().substring(0, j);
					var zflx1 = m[i].value.toString().substring(j + 7,
							m[i].value.toString().length);
					if (zflx1 == "1") {
						chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
					} else if (zflx1 == "2") {
						chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
					}
				}
			var bzw1 = document.form1.bzw1.value;
			if (bzw1 == "1") {
				if (count1 <= 240 * n
						|| (bz >= 239 && bz<= 241) {
					if ((bz/ 240 == 1)
							|| (bz >= 239 && bz <= 241)) {
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						chooseIdStr2 = chooseIdStr2.substring(0,
								chooseIdStr2.length - 1);
						passCheck1(chooseIdStr1,chooseIdStr2,cuu);
						//document.form1.action = "servlet/FinanceCheckServlet?action=buwanquan&chooseIdStr1="
						//		+ chooseIdStr1
						//		+ "&chooseIdStr2="
						//		+ chooseIdStr2 + "&shbz=" + shbz + "&kjyfa="+cuu;
						chooseIdStr1 = "";
						chooseIdStr2 = "";
						bz = 0;
						count2 = 0;
						document.form1.submit();
					}
				} else if (count == count1 && bzw == 1) {
					chooseIdStr1 = chooseIdStr1.substring(0,
							chooseIdStr1.length - 1);
					chooseIdStr2 = chooseIdStr2.substring(0,
							chooseIdStr2.length - 1);
					bzw = 0;
				//	alert(chooseIdStr1+":"+chooseIdStr2)
					document.form1.action = "servlet/FinanceCheckServlet?action=checkpass&chooseIdStr1="
							+ chooseIdStr1
							+ "&chooseIdStr2="
							+ chooseIdStr2
							+ "&kjyfa="+cuu;
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
			alert("会计月份不能为空！");
		}
	} else {
		alert("请选择信息！");
	}
}
function butongguo() {
	var m = document.getElementsByName('test[]');
	var cuu = document.form1.kjyf.value;
	var l = m.length;
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var bzw = 1;
	var count2 = 0;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	
	var m2 = document.getElementsByName('test1[]');
	var count3 = 0;
	
	var dateString = <%=dateString%>;//当前日期
	var monthString = <%=monthString%>;//当前月份
	var hoursString = <%=hourse%>;//当前 小时
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
			if(dateString >= 3&& hoursString>=12){//每月的3号12点 以后不能退上个月的电费单
				var bzmonth = m2[i].value.toString().substr(5,2);//获取报账月份后两位
				var bzmonth1 = parseInt(bzmonth, 10); 
				if(monthString > bzmonth1){
					count3++;
				}
			}
		}
	}
	
	if (count != 0) {
		if(count3 <= 0){
			if (cuu != "" && cuu != null) {
			if (count % 240 == 0) {
				n = count / 240 - 1;
			} else {
				n = (count - (count % 240)) / 240;
			}
			for ( var i = 0; i < l; i++) {
				if (m[i].checked == true) {
					bz += 1;
					count1 += 1;
					var j = m[i].value.toString().indexOf("+zflx:+");
	
					var chooseIdStr3 = m[i].value.toString().substring(0, j);
					var zflx1 = m[i].value.toString().substring(j + 7,
							m[i].value.toString().length);
					if (zflx1 == "1") {
						chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
					} else if (zflx1 == "2") {
						chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
					}
				}
			var bzw1 = document.form1.bzw1.value;
			if (bzw1 == "1") {
				if (count1 <= 240 * n
						|| (bz >= 239 && bz <= 241) {
					if ((bz / 240 == 1)
							|| (bz>= 239 &&bz<= 241)) {
	
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						chooseIdStr2 = chooseIdStr2.substring(0,
								chooseIdStr2.length - 1);
						passnoCheck1(chooseIdStr1,chooseIdStr2,cuu);
						chooseIdStr1 = "";
						chooseIdStr2 = "";
						bz = 0;
						count2 = 0;
						document.form1.submit();
					}
				} else if (count == count1 && bzw == 1) {
	
					chooseIdStr1 = chooseIdStr1.substring(0,
							chooseIdStr1.length - 1);
					chooseIdStr2 = chooseIdStr2.substring(0,
							chooseIdStr2.length - 1);
					bzw = 0;
					document.form1.action = "servlet/FinanceCheckServlet?action=checknopass&chooseIdStr1="
							+ chooseIdStr1
							+ "&chooseIdStr2="
							+ chooseIdStr2
							+ "&kjyfa="+cuu;
					document.form1.submit();
					showdiv("请稍等..............");
				}
			} else if (bzw1 == "0") {
				document.form1.action = path + "/web/msg.jsp";
				document.form1.submit();
				return;
			}
			}
		}else {
			alert("会计月份不能为空！");
		}
		}else{
			alert("每月的3号12点 以后不能退上个月的电费单！");
		}
	} else {
		alert("请选择信息！");
	}
	
}
function chaxun() {
	document.form1.action = "servlet/FinanceSelectServlet";
	document.form1.submit();
	showdiv("请稍等..............");
}
function daochu() {
	exportad();
}
</script>

</html>
