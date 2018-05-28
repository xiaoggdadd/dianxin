<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime,com.noki.elecconsume.Shenghdlquery"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>

<%
	//String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date()).substring(0,7);
	//String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date()).substring(0,7);
	String beginTime = request.getParameter("beginTime") != null ? request
			.getParameter("beginTime")
			: "";
	String endTime = request.getParameter("endTime") != null ? request
			.getParameter("endTime") : "";

	String sheng = (String) session.getAttribute("accountSheng");
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : "0";
	//String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
	//String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");

	String roleId = account.getRoleId();
	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
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
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #cecfde );
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
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #B3D997 );
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
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #CAE4B6 );
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
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #C3DAF5 );
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
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #D7E7FA );
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
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #C3DAF5 );
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
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #C3DAF5 );
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
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #FFFFFF, EndColorStr = #9DBCEA );
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
	color: white;
	font-weight: bold;
}
</style>
		<script type="text/javascript"
			src="<%=path%>/web/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/tx.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
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
	if (document.getElementById("endTime").value == ""
			|| document.getElementById("beginTime").value == "") {
		alert("日期不能为空");
	} else {
		document.form1.action = path + "/web/elecconsume/shenghdlquery.jsp";
		document.form1.submit();
	}
}

function showhb(hStr) {
	//alert(hStr);
	window
			.open("huanbi_hdl_city.jsp?dataStr=" + hStr, '',
					'width=600,height=400,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')

}
$(function() {

	$("#query").click(function() {
		chaxun();
	});
})
</script>

	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">


				<tr>
					<td width="10" height="500">
						&nbsp;
					</td>
					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="4"></td>
										</tr>

										<tr>
											<td colspan=3 width="600"
												background="<%=path%>/images/btt.bmp" height=63>
												<span style="color: black; font-weight: bold;">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;省级耗电量统计</span>
											</td>

											<td width="380">
												&nbsp;
											</td>
										</tr>
									</table>
									<br>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="4"></td>
											<td>
												<table width="100%" border="0" align="right" cellpadding="1"
													cellspacing="1">
													<tr>
														<td height="49" bgcolor="#FFFFFF">
															<table width="100%" border="0" cellspacing="1"
																cellpadding="1">
																<div id="parent" style="display: inline">
																</div>
																<div style="width: 50px; display: inline;">
																	<hr>
																</div>
																&nbsp;过滤条件&nbsp;
																<div style="width: 300px; display: inline;">
																	<hr>
																</div>
																

																<tr>
																	<td width="500">
																		起始月份&nbsp;
																		<input type="text" name="beginTime"
																			value="<%if (null != request.getParameter("beginTime"))
				                                                           out.print(request.getParameter("beginTime"));%>"
																			readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
																			class="form" />
																		结束月份
																		<input type="text" name="endTime"
																			value="<%if (null != request.getParameter("endTime"))
				                                                            out.print(request.getParameter("endTime"));%>"
																			readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
																			class="form" />
																	</td>
																	<td>
																		<div id="query"
																			style="position: relative; width: 59px; height: 23px; cursor: pointer;">
																			<img alt=""
																				src="<%=request.getContextPath()%>/images/chaxun.png"
																				width="100%" height="100%" />
																			<span
																				style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
																		</div>
																	</td>
																</tr>


															</table>
															<br>
															<div id="parent" style="display: inline">
																<div style="width: 50px; display: inline;">
																	<hr>
																</div>
																&nbsp;信息列表&nbsp;
																<div style="width: 300px; display: inline;">
																	<hr>
																</div>
															</div>

															<table width="100%" border="0" cellspacing="1"
																cellpadding="1" bgcolor="#cbd5de">
																</tr>
																<tr>
																	<td width="7%" height="23" bgcolor="#888888">
																		<div align="center" class="bttcn">
																			省份
																		</div>
																	</td>
																	<td width="7%" height="15" bgcolor="#888888">
																		<div align="center" class="bttcn">
																			月份
																		</div>
																	</td>
																	<td width="7%" height="15" bgcolor="#888888">
																		<div align="center" class="bttcn">
																			耗电量（总）
																		</div>
																	</td>

																	<td width="7%" height="15" bgcolor="#888888">
																		<div align="center" class="bttcn">
																			机房耗电量
																		</div>
																	</td>
																	<td width="7%" height="15" bgcolor="#888888">
																		<div align="center" class="bttcn">
																			基站耗电量
																		</div>
																	</td>
																	<td width="7%" height="15" bgcolor="#888888">
																		<div align="center" class="bttcn">
																			营业网点耗电量
																		</div>
																	</td>
																	<td width="7%" height="15" bgcolor="#888888">
																		<div align="center" class="bttcn">
																			其它耗电量
																		</div>
																	</td>


																</tr>
																<%
																	Shenghdlquery bean = new Shenghdlquery();
																	ArrayList fylist = new ArrayList();
																	if (beginTime != "" && endTime != "") {
																		fylist = bean.getPageData(beginTime, endTime, shi);
																	}

																	String agname = "", shengname = "", sj = "", sum_hj = "", SCYFHDL = "", TXJFHDL = "", JZHDL = "", SJZXHDL = "";

																	double sumhj1 = 0, sum_SCYFHDL1 = 0, sum_TXJFHDL1 = 0, sum_JZHDL1 = 0, sum_SJZXHDL1 = 0;

																	String sumhj = "", sum_SCYFHDL = "", sum_TXJFHDL = "", sum_JZHDL = "", sum_SJZXHDL = "";

																	int sumdl = 0, sumdf = 0;
																	int intnum = xh = pagesize * (curpage - 1) + 1;
																	if (fylist.size() != 0) {
																		int fycount = ((Integer) fylist.get(0)).intValue();
																		for (int k = fycount; k < fylist.size() - 1; k += fycount) {

																			//num为序号，不同页中序号是连续的

																			shengname = (String) fylist.get(k
																					+ fylist.indexOf("AGNAME"));
																			shengname = shengname != null ? shengname : "";
																			sj = (String) fylist.get(k + fylist.indexOf("SJ"));
																			sj = sj != null ? sj : "";
																			sum_hj = (String) fylist.get(k + fylist.indexOf("SUM_HJ"));
																			sum_hj = sum_hj != null ? sum_hj : "";
																			SCYFHDL = (String) fylist
																					.get(k + fylist.indexOf("SCYFHDL"));
																			SCYFHDL = SCYFHDL != null ? SCYFHDL : "";
																			TXJFHDL = (String) fylist
																					.get(k + fylist.indexOf("TXJFHDL"));
																			TXJFHDL = TXJFHDL != null ? TXJFHDL : "";
																			JZHDL = (String) fylist.get(k + fylist.indexOf("JZHDL"));
																			JZHDL = JZHDL != null ? JZHDL : "";
																			SJZXHDL = (String) fylist
																					.get(k + fylist.indexOf("SJZXHDL"));
																			SJZXHDL = SJZXHDL != null ? SJZXHDL : "";
																			DecimalFormat mat = new DecimalFormat("0.0");
																			sumhj1 += Double.parseDouble(sum_hj);
																			sum_SCYFHDL1 += Double.parseDouble(SCYFHDL);
																			sum_TXJFHDL1 += Double.parseDouble(TXJFHDL);
																			sum_JZHDL1 += Double.parseDouble(JZHDL);
																			sum_SJZXHDL1 += Double.parseDouble(SJZXHDL);

																			sumhj = mat.format(sumhj1);
																			sum_SCYFHDL = mat.format(sum_SCYFHDL1);
																			sum_TXJFHDL = mat.format(sum_TXJFHDL1);
																			sum_JZHDL = mat.format(sum_JZHDL1);
																			sum_SJZXHDL = mat.format(sum_SJZXHDL1);
																			String color = null;
																			sum_hj=mat.format(Double.parseDouble(sum_hj));
																			SCYFHDL=mat.format(Double.parseDouble(SCYFHDL));
																			TXJFHDL=mat.format(Double.parseDouble(TXJFHDL));
																			JZHDL=mat.format(Double.parseDouble(JZHDL));
																			
																			SJZXHDL=mat.format(Double.parseDouble(SJZXHDL));
  
																			if (intnum % 2 == 0) {
																				color = "#DDDDDD";

																			} else {
																				color = "#FFFFFF";
																			}
																			intnum++;
																%>

																<tr bgcolor="<%=color%>">
																	<td>
																		<div align="center"><%=shengname%></div>
																	</td>
																	<td>
																		<div align="center"><%=sj%></div>
																	</td>
																	<td>
																		<div align="center"><%=sum_hj%></div>
																	</td>
																	<td>
																		<div align="center"><%=SCYFHDL%></div>
																	</td>
																	<td>
																		<div align="center"><%=TXJFHDL%></div>
																	</td>
																	<td>
																		<div align="center"><%=JZHDL%></div>
																	</td>
																	<td>
																		<div align="center"><%=SJZXHDL%></div>
																	</td>

																</tr>
																<%
																	} //显示饼图数据串
																		String hStr = sum_SCYFHDL + ";" + sum_TXJFHDL + ";" + sum_JZHDL
																				+ ";" + sum_SJZXHDL + ";";
																%>
																<tr bgcolor="F2F9FF">
																	<td>
																		<div align="center"></div>
																	</td>
																	<td>
																		<div align="center">
																			<a href="huanbi_hdl_city.jsp?dataStr=<%=hStr%>"
																				target="chart">合计</a>
																		</div>
																	</td>
																	<td>
																		<div align="center"><%=sumhj%></div>
																	</td>
																	<td>
																		<div align="center"><%=sum_SCYFHDL%></div>
																	</td>
																	<td>
																		<div align="center"><%=sum_TXJFHDL%></div>
																	</td>
																	<td>
																		<div align="center"><%=sum_JZHDL%></div>
																	</td>
																	<td>
																		<div align="center"><%=sum_SJZXHDL%></div>
																	</td>

																</tr>

															</table>




															<%
																}
															%>
														</td>
													</tr>

												</table>
											</td>
										</tr>
										<tr bgcolor="#FFFFFF">

											<td align="right" colspan="10">
												<div id="parent" style="display: inline" align="right">
													<div style="width: 300px; display: inline;" align="right">
														<hr>
													</div>
												</div>
											</td>

										</tr>


										<!--  <tr bgcolor="#FFFFFF">
											
											<td colspan="10">
												

												<div align="right">
												<input type="button" name="shanchulogs1" id="id1" class="memo" value="导出" onclick="daorujz()"  style="color:#014F8A"/>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="button" name="shanchulogs1" id="id1" class="memo" value="打印" onclick="dayinpage()"  style="color:#014F8A"/>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							        </div>
											</td>
										</tr>-->



									</table>
								</td>
							</tr>
						</table>
						<div style="width: 600px; height: 400px;">
							<iframe name="chart" height="100%" width="100%" frameborder="0">

							</iframe>
						</div>
						<br />

					</td>
					<td background="../../images/img_13.gif">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<img src="../../images/img_23.gif" width="12" height="19" />
					</td>
					<td background="../../images/img_24.gif">
						&nbsp;
					</td>
					<td>
						<img src="../../images/img_25.gif" width="12" height="19" />
					</td>
				</tr>

			</table>

		</form>
		<div id="cancelBtn"
			style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 14px">
			<img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
			<span
				style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
		</div>
	</body>
</html>
<script language="javascript">
var path = '<%=path%>';

function delzd(id) {
	document.form1.action = path + "/servlet/zhandian?action=del&id=" + id
	document.form1.submit();
}

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
</script>

