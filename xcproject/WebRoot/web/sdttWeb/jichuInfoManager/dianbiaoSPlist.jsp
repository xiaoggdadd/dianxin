<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.DianBiaoBean,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
		<script>
<%String path = request.getContextPath();
			Account account = (Account) session.getAttribute("account");
			String loginId = account.getAccountId();
			String bgsign = request.getParameter("bgsign") != null ? request
					.getParameter("bgsign") : "-1";//获取当前页 是否标杆 
			String txtDianbiao = request.getParameter("txtDianbiao") != null ? request
					.getParameter("txtDianbiao")
					: "";
			String baosongtime = request.getParameter("baosongtime") != null ? request
					.getParameter("baosongtime")
					: "";
			String people = request.getParameter("people") != null ? request
					.getParameter("people") : "";
			String lclx = request.getParameter("lclx") != null ? request
					.getParameter("lclx") : "";

			String txtkeyword = request.getParameter("txtkeyword") != null ? request
					.getParameter("txtkeyword")
					: "";

			String s_curpage = request.getParameter("curpage") != null ? request
					.getParameter("curpage")
					: "1";
			int count = 0, pagesize = 10, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
			String color = null;
			curpage = Integer.parseInt(s_curpage);
			String loginName = (String) session.getAttribute("loginName");
			String roleId = (String) session.getAttribute("accountRole");
			String permissionRights = "";%>
</script>
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<jsp:useBean id="bean" scope="page"
		class="com.noki.mobi.workflow.javabean.WorkFlowBean">
	</jsp:useBean>
	<!-- 显示时间input控件 -->
	<%
		//String path = request.getContextPath();
	%>
	<script type="text/javascript"
		src="<%=path%>/javascript/PopupCalendar.js">
	</script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	

	<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
	</script>
	<script type="text/javascript" src="<%=path%>/javascript/wait.js">
	</script>
	<script type="text/javascript"
		src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
	</script>
	<!-- fxl 2018/1/11(时间控件) -->
	<body>
		<%
			String whereStr = "";
			String str = "";
			String xuni = "0";
			if (txtDianbiao != "" && txtDianbiao != null && txtDianbiao != "0") {
				whereStr = whereStr + " and d.DBNAME like '%" + txtDianbiao
						+ "%'";
			
			}
			if (!txtkeyword.equals("0")) {
				whereStr = whereStr + " and z.jzname like '%" + txtkeyword
						+ "%'";

			}
			if (!people.equals("0")) {
				whereStr = whereStr + " and a.name like '%" + people + "%'";
			}
			if (!baosongtime.equals("0")) {

				whereStr = whereStr
						+ " and  to_char (w.applytime,'yyyy-MM-dd')like '%"
						+ baosongtime + "%'";
			}
			if (lclx != "") {
				whereStr = whereStr + " and w.dblc_type  = '" + lclx + "'";
			}
		%>
		<form action="" name="form1" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="top">
					<td width="12">
						<img src="../images/space.gif" width="12" height="17" />
					</td>
					<td>
						<div class="content01">
							<div class="tit01">
								电表数据审批
							</div>
							<div class="content01_1">
								<table width="800px" border="0" cellpadding="1" cellspacing="0"
									class="tb_select">
									<tr>
										<td align="right" width="60px">
											电表名称：
										</td>
										<td align="left" width="60px">
											<input type="text" name="txtDianbiao" id="txtDianbiao"
												style="box-sizing: border-box; width: 130px;" />
										</td>
										<td align="right" width="60px">
											实体名称：
										</td>
										<td align="left" width="60px">
											<input type="text" name="txtkeyword" id="txtkeyword" 
												style="box-sizing: border-box; width: 130px;" />
										</td>
										<td align="right" width="60px">
											报送人：
										</td>
										<td align="left" width="60px">
											<input type="text" name="people" id="people" value=""
												style="box-sizing: border-box; width: 130px;" />
										</td>
										
									</tr>
									<tr>
										<td align="right" width="100px">
											流程类型
										</td>
										<td width="100px">
											<select name="lclx"
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="">
													请选择
												</option>
												<option value="0">
													新增电表
												</option>
												<option value="1">
													更换电表
												</option>
												<option value="2">
													修改倍率
												</option>
												<option value="3">
													修改状态
												</option>
												<option value="4">
													修改单价
												</option>


											</select>
										</td>
										<td align="right" width="100px">
											报送时间：
										</td>
										<td width="100px">
											<!-- 显示时间的input -->
											<input class="selected_font" type="text" name="baosongtime"
												value="" readonly="readonly" class="Wdate"
												style="background-color: #FFFFFF; color: grey;box-sizing: border-box; width: 100px"
												onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
										</td>
										<td align="right" colspan="8">
											<input type="submit" class="btn_c1" onclick="query()"
												value="查询" />
										</td>
									</tr>
								</table>

								<div class="tbtitle01">
									<b>电表审批统计一览</b>
								</div>
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="tb_list1">
									<%--<tr align="right">
										<td colspan="9" align="right">
										    <input name="button2" type="button" onclick="addjz()" class="btn_c1" id="button2" value="新增" />&nbsp;&nbsp;
											<input name="button2" type="submit" onclick="daochu()" class="btn_c1" id="button2" value="导出Excel" />
										</td>
									</tr>
									
									
									--%>
									<tr align="center">
										<th>
											操作
										</th>
										<th width="10">
											序号
										</th>
										<th width="120">
											电表编码
										</th>
										<th width="100">
											电表名称
										</th>
										<th width="80">
											分公司
										</th>
										<th width="80">
											区县分公司
										</th>
										<th width="80">
											实体名称
										</th>
										<th width="80">
											报送人
										</th>
										<th width="80">
											报送时间
										</th>
										<th width="80">
											流程类型
										</th>
										<th width="80">
											当前节点
										</th>
										
									</tr>
									<%
										ArrayList fylist = new ArrayList();
										fylist = bean.getDBData(curpage, "DIANBIAO", loginId, whereStr);
										allpage = bean.getAllPage();
										String id = "", taskId = "", dbbm = "", dbname = "", fgs = "", qx = "", zdname = "", applyuser = "", dblc_type = "", currentaction = "", applytime = "", lctype = "";
										int intnum = xh = pagesize * (curpage - 1) + 1;
										Double bl = 0.00;
										if (fylist != null) {
											int fycount = ((Integer) fylist.get(0)).intValue();
											for (int k = fycount; k < fylist.size() - 1; k += fycount) {

												//num为序号，不同页中序号是连续的
												id = (String) fylist.get(k + fylist.indexOf("ID"));
												taskId = (String) fylist.get(k + fylist.indexOf("TASK_ID"));
												dbbm = (String) fylist.get(k + fylist.indexOf("DBBM"));
												dbname = (String) fylist.get(k + fylist.indexOf("DBNAME"));
												fgs = (String) fylist.get(k + fylist.indexOf("SHI"));
												qx = (String) fylist.get(k + fylist.indexOf("XIAN"));
												zdname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
												applyuser = (String) fylist.get(k + fylist.indexOf("NAME"));
												dblc_type = (String) fylist.get(k
														+ fylist.indexOf("DBLC_TYPE"));
												currentaction = (String) fylist.get(k
														+ fylist.indexOf("CURRENTACTION"));
												applytime = (String) fylist.get(k
														+ fylist.indexOf("APPLYTIME"));

												SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
												java.util.Date applytimeDate = sdf.parse(applytime);
												String applytimeStr = sdf.format(applytimeDate); //上报时间
												if (dblc_type.equals("0")) {
													lctype = "新增电表";
												} else if (dblc_type.equals("1")) {
													lctype = "更换电表";
												} else if (dblc_type.equals("2")) {
													lctype = "修改倍率";
												} else if (dblc_type.equals("3")) {
													lctype = "修改状态";
												} else if (dblc_type.equals("4")) {
													lctype = "修改单价";
												}
												if (intnum % 2 == 0) {
													color = "#DDDDDD";

												} else {
													color = "#FFFFFF";
												}
												System.out.println("报账审核：" + currentaction);
												if (currentaction == null || currentaction.equals("")) {
													currentaction = "调整任务";
												}
									%>
									<!-- 数据加载  Start-->
									<tr align="center">
									<td width="80" align="center">
											<button type="button" class="btn_c1"
												onclick="sp('<%=id%>','<%=dblc_type%>')">
												审批
											</button>
										</td>
										<td width="10"><%=intnum++%></td>
										<td align="center"><%=dbbm%></td>
										<td align="center"><%=dbname%></td>
										<td align="center"><%=fgs%></td>
										<td align="center"><%=qx%></td>
										<td align="center"><%=zdname%></td>
										<td align="center"><%=applyuser%></td>
										<td align="center"><%=applytimeStr%></td>
										<td align="center"><%=lctype%></td>
										<td align="center"><%=currentaction%></td>
										
									</tr>
									<%
										}
									%>
									<!-- 数据加载 End -->

									<tr bgcolor="#ffffff">
										<td colspan="16">
											<div align="left">
												&nbsp;&nbsp;
												<font color='#1E90FF'> <%
 	if (curpage != 1) {
 			out
 					.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
 		} else {
 			out
 					.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
 		}
 %> </font> &nbsp;&nbsp;
												<font color='#1E90FF'> <%
 	if (curpage != 1) {
 			out
 					.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
 		} else {
 			out
 					.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
 		}
 %> </font> &nbsp;&nbsp; 转到
												<select name="page"
													onChange="javascript:gopagebyno(document.form1.page.value)"
													style="width: 40px; font-family: 宋体; font-size: 12px; line-height: 120%;">
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
												&nbsp;&nbsp;共
												<font color='#1E90FF'><b><%=allpage%></b>&nbsp;</font>页
												&nbsp;&nbsp;

												<font color='#1E90FF'> <%
 	if (allpage != 0 && (curpage < allpage)) {
 			out
 					.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");
 		} else {
 			out
 					.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");
 		}
 %> </font> &nbsp;&nbsp;
												<font color='#1E90FF'> <%
 	if (allpage != 0 && (curpage < allpage)) {
 			out.print("<a href='javascript:gopagebyno(" + allpage
 					+ ")'><img src='../images/page-last.gif'></a>");
 		} else {
 			out
 					.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
 		}
 %> </font> &nbsp;&nbsp;
											</div>
										</td>
									</tr>
									<%
										}
									%>
								</table>
								<div class="space_h_10"></div>
							</div>
						</div>
					</td>
				</tr>
				<!-- else {%>-->
				<!--  <tr align="center" >
			<td align="left" colspan="9">
			当前无数据。
			</td>
		</tr>-->

			</table>
		</form>
	</body>
</html>
<script>
var path = '<%=path%>';

function sp(a, n) {
	if (n == 0) {
	//新增电表
		window.open(
						"../workFlow/todo_dianbiao.jsp?id=" + a,
						"newwindow",
						"height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");
	} else if (n == 1) {
	//更换电表
		window.open(
						"../jizan/dianbiaoGHSp.jsp?id=" + a + "&type=" + n,
						"newwindow",
						"height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");
	} else {
	//倍率，状态，单价
		window.open(
						"../jizan/dianbiaoSp.jsp?id=" + a + "&type=" + n,
						"newwindow",
						"height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");
	}

	document.form1.submit();
}

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
				+ "/web/sdttWeb/jichuInfoManager/dianbiaoSPlist.jsp?curpage="
				+ curpage;
		document.form1.submit();
	}
}
function nextpage() {
	document.form1.page.value = parseInt(document.form1.page.value) + 1;
	var curpage = '<%=(curpage + 1)%>';
	document.form1.action = path
			+ "/web/sdttWeb/jichuInfoManager/dianbiaoSPlist.jsp?curpage="
			+ curpage;
	document.form1.submit();
}
function gopagebyno(pageno) {
	document.form1.page.value = pageno;

	document.form1.action = path
			+ "/web/sdttWeb/jichuInfoManager/dianbiaoSPlist.jsp?curpage="
			+ pageno;
	document.form1.submit();
}

var XMLHttpReq;
//XMLHttpReq = createXMLHttpRequest();
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
///////////////////////////////////////////////////////////
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
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");

			//var res = XMLHttpReq.responseText;

			updateShi(res);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
		;
	}
	;
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

function query() {
	document.form1.action = path
			+ "/web/sdttWeb/jichuInfoManager/dianbiaoSPlist.jsp";
}

document.form1.baosongtime.value = '<%=baosongtime%>';
document.form1.people.value = '<%=people%>';
document.form1.lclx.value = '<%=lclx%>';
document.form1.txtDianbiao.value = '<%=txtDianbiao%>';
document.form1.txtkeyword.value = '<%=txtkeyword%>';
</script>


