<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean"%>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%
	String bzyf = request.getParameter("bzyf") != null ? request
			.getParameter("bzyf") : "";//报账月份
	String beginTime = request.getParameter("beginTime") != null ? request
			.getParameter("beginTime")
			: CTime.formatRealDate(new Date());
	String endTime = request.getParameter("endTime") != null ? request
			.getParameter("endTime") : CTime.formatRealDate(new Date());
	String title = request.getParameter("title") != null ? request
			.getParameter("title") : "";
	String operName = request.getParameter("operName") != null ? request
			.getParameter("operName")
			: "";
	//添加站点类型
	String stationtype1 = request.getParameter("stationtype") != null ? request
			.getParameter("stationtype")
			: "0";
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
	String color = null;
	String bargainid = "";
	int intnum = 0;
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
			.getParameter("zdname") : "";
	String beginTimeQ = request.getParameter("startmonth") != null ? request
			.getParameter("startmonth")
			: "";
	String endTimeQ = request.getParameter("endmonth") != null ? request
			.getParameter("endmonth")
			: "";
	String qyzt = request.getParameter("qyzt")!=null?request.getParameter("qyzt"):"1";//站点启用状态

	String canshuStr = "";
	if ((shi != null) || (xian != null) || (xiaoqu != null)
			|| ((beginTime != null) && (endTime != null))
			|| (stationtype1 != null) || (zdname != null)) {
		canshuStr = "&shi=" + shi + "&xian=" + xian + "&xiaoqu="
				+ xiaoqu + "&startmonth=" + beginTimeQ + "&endmonth="
				+ endTimeQ + "&stationtype1=" + stationtype1
				+ "&zdname=" + zdname;
	}
	//String roleId = account.getRoleId();
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
		<title></title>
		<style>
.style1 {
	color: red;
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

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height: 23px
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}

.selected_font {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
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
		<script src="<%=path%>/web/appJS/pageJs/someJs/page.js">
</script>
		<script src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script language="javascript">
var path = '<%=path%>';

function adddegree() {
	document.form1.action = path
			+ "/web/appJSP/electricmanage/bargainbill/addBargainBill.jsp";
	document.form1.submit();
	showdiv("请稍等..............");
}

function delad(id) {
	if (confirm("您确定删除此合同单信息？")) {
		document.form1.action =path+"/servlet/DropBargainBillServlet?id=" + id;
		document.form1.submit();
		showdiv("请稍等..............");
	}
}
function modifyad(id) {
	document.form1.action = path
			+ "/web/appJSP/electricmanage/bargainbill/UpdateBargainBill.jsp?id="
			+ id;
	document.form1.submit();
	showdiv("请稍等..............");

}

function queryDegree() {
	document.form1.action = path + "/servlet/BargainBillSelectServlet";
	document.form1.submit();
	showdiv("请稍等..............");
}
//批量导入
function exportFData() {
	document.form1.action = path
			+ "/web/appJSP/electricmanage/bargainbill/bargainBillInput.jsp";
	document.form1.submit();
}
$(function() {
	$("#daoru").click(function() {
		exportFData();
	});
	$("#zengjia").click(function() {
		adddegree();

	});
	$("#chaxun").click(function() {
		queryDegree();
	});
});
</script>

	</head>

	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<%
		permissionRights = commBean.getPermissionRight(roleId, "0302");
	%>
	<body class="body">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
				<tr>
					<td colspan="4" width="50">
						<div style="width:700px;height:50px">
							<img alt="" src="<%=path%>/images/btt.bmp" width="100%"
								height="100%" />
							<span
								style="font-size: 14px; font-weight: bold; position: absolute; left: 25px; top: 15px; color: black">合同单管理</span>
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
									<select name="shi" class="selected_font" id="shi"
										onchange="city(this.value,'document.all.xian')">
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
													request.setAttribute("shicd", agcode);
													agname = (String) shilist
															.get(i + shilist.indexOf("AGNAME"));
										%>
										<option value="<%=agcode%>"
											<c:choose>
							<c:when test="${bean.shi ==null}">
								<%if (agcode != null) {
						if (agcode.equals(shi)) {%>
										selected
										<%}
					}%>
							</c:when>
							<c:otherwise >
								<c:if test="${bean.shi eq shicd}">selected</c:if>
							</c:otherwise>
							</c:choose>><%=agname%></option>
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
									<select name="xian" class="selected_font" id="xian"
										onchange="county(this.value,'document.form1.xiaoqu')">
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
													request.setAttribute("xiancd", agcode);
													agname = (String) xianlist.get(i
															+ xianlist.indexOf("AGNAME"));
										%>
										<option value="<%=agcode%>"
										<c:if test="${bean.xian eq xiancd}">selected</c:if>><%=agname%></option>
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
									<select name="xiaoqu" class="selected_font" id="xiaoqu">
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
													request.setAttribute("xiaoqucd", agcode);
													agname = (String) xiaoqulist.get(i
															+ xiaoqulist.indexOf("AGNAME"));
										%>
										<option value="<%=agcode%>"
										<c:if test="${bean.xiaoqu eq xiaoqucd}">selected</c:if>><%=agname%></option>
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
										style="position: relative; width: 59px; height: 23px; right: -180px; cursor: pointer; TOP: 0PX">
										<img alt="" src="<%=path%>/images/chaxun.png" width="100%"
											height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
									</div>
									<%
										}
									%>
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
											<input type="text" name="zdname" class="selected_font"
												value="${bean.zdmc}" />
										</td>
										<td>
											起始时间：
										</td>
										<td>
											<input type="text" class="selected_font" name="startmonth"
												value="${bean.qssj}"
												onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
										</td>
										<td>
											结束时间：
										</td>
										<td>
											<input type="text" class="selected_font" name="endmonth"
												value="${bean.jssj}"
												onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
										</td>
										<td>
											站点类型：
										</td>
										<td>
											<select name="stationtype" class="selected_font" onchange="">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList stationtype = new ArrayList();
													stationtype = commBean.getSelctOptions("StationType");
													if (stationtype != null) {
														String code = "", name = "";
														int cscount = ((Integer) stationtype.get(0)).intValue();
														for (int i = cscount; i < stationtype.size() - 1; i += cscount) {
															code = (String) stationtype.get(i
																	+ stationtype.indexOf("CODE"));
															request.setAttribute("zdcd", code);
															name = (String) stationtype.get(i
																	+ stationtype.indexOf("NAME"));
												%>
												<option value="<%=code%>"
												<c:if test="${bean.zdlx eq zdcd}">selected="selected" </c:if>><%=name%></option>
												<%
													}
													}
												%>
											</select>
										</td>
									</tr>
									<tr class="form_label">
										<td>
											报账月份:
										</td>
										<td>
											<input type="text" name="bzyf" value="${bean.bzyf}"
												onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
												class="selected_font" />
										</td>
										<td>站点启用状态：</td>
						                <td>
						                   <select name="qyzt" id="zd" class="form" >
						         		   		<option value="1">启用</option>
						         		   		<option value="-1">请选择</option>
						         		        <option value="0">未启用</option>
						         	       </select>
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
			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="100%" height="60%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="10" class="relativeTag">
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="20%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="12%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表名称
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								金额
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								起始时间
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								结束时间
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账月份
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点编号
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表编号
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								合同编号
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD" colspan="2">
							<div align="center" class="bttcn">
								操作
							</div>
						</td>
					</tr>

					<c:forEach items="${Info}" var="m" varStatus="sum">
						<tr <c:if  test="${sum.count%2==0}">bgcolor="#DDDDDD"</c:if>
							<c:if  test="${sum.count%2==1}">bgcolor="#FFFFFF"</c:if>>
							<td>
								<div align="center">
									${sum.count}
								</div>
							</td>
							<td>
								<div align="left">
									${m.jzname}
								</div>
							</td>
							<td>
								<div align="center">
									${m.stationtype}
								</div>
							</td>

							<td>
								<div align="left">
									${m.dbname}
								</div>
							</td>
							<td>
								<div align="right">
									${m.money}
								</div>
							</td>
							<td>
								<div align="center">
									${m.startmonth}
								</div>
							</td>
							<td>
								<div align="center">
									${m.endmonth}
								</div>
							</td>
							<td>
								<div align="center">
									${m.accountmonth}
								</div>
							</td>
							<td>
								<div align="left">
									${m.zdcode}
								</div>
							</td>
							<td>
								<div align="left">
									${m.prepayment_ammeterid}
								</div>
							</td>
							<td>
								<div align="left">
									${m.htbh}
								</div>
							</td>
							<td>
								<div align="center">
									<a href="javascript:modifyad('${m.id}')">修改</a>
								</div>
							</td>
							<td>
								<%
									if (permissionRights.indexOf("PERMISSION_DELETE") >= 0) {
								%>
								<div align="center">
									<a href="javascript:delad('${m.id}')">删除</a>
								</div>
								<%
									}
								%>
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
							</tr>
						</c:forEach>
					</c:if>
				</table>

			</div>

			<div style="width: 100%; height: 8%; border: 1px">
				<table width="100%" height="20%" border="0" cellspacing="0"
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
							<%
								if (permissionRights.indexOf("PERMISSION_ADD") >= 0) {
							%>

							<div id="daoru"
								style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 4px;">
								<img alt="" src="<%=path%>/images/daoru.png" width="100%"
									height="100%" />
								<span
									style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导入</span>
							</div>
							<%
								}
							%>
							<%
								if (permissionRights.indexOf("PERMISSION_ADD") >= 0) {
							%>
							<div id="zengjia"
								style="position: relative; width: 60px; height: 23px; cursor: pointer; right: 6px; float: right;">
								<img alt="" src="<%=path%>/images/xinzeng.png" width="100%"
									height="100%" />
								<span
									style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">增加</span>
							</div>

							<%
								}
							%>

						</td>
					</tr>
					<tr>
						<!--   <td>
                <input type="hidden" name="sheng2" id="sheng2" value=""/>
                <input type="hidden" name="shi2" id="shi2" value=""/>
                <input type="hidden" name="xian2" id="xian2" value=""/>
                <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
                
              </td> -->
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
