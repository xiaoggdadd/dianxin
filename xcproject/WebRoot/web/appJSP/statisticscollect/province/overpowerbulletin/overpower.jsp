<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.*,java.util.List,java.util.Date,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.*"%>
<%
	String path = request.getContextPath();//项目路径
	Account account = (Account) session.getAttribute("account");//账户

	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
	Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MONTH,-1);//当前月的前一个月
    String month = ft.format(cal.getTime());
	
	String bztime = request.getParameter("bztime") != null ? request.getParameter("bztime"): month;//报账月份
	String property = request.getParameter("property") != null ? request.getParameter("property"): "0";//站点属性
	String status = request.getParameter("status") != null ? request.getParameter("status") : "2";//审核状态

	String color;//颜色
	int intnum = request.getAttribute("num") != null ? (Integer) request.getAttribute("num"): 0;//条数 ,查出数据的条数，用于颜色设置
	if (intnum % 2 == 0) {
		color = "#DDDDDD";
	} else {
		color = "#FFFFFF";
	}
%>

<html>
	<head>
		<title>超标电量通报</title>

		<link type="text/css" rel="Stylesheet"
			href="<%=path%>/web/appCSS/pageCss/page.css" />
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/someJs/page.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
		<script language="javascript">
var path = '<%=path%>';

function queryDegree(command) {
		document.form1.action = path + "/servlet/OverPowerServlet?command="+ command;
		document.form1.submit();
		if ("chaxun" == command) {
			showdiv("请稍等..............");
		}
}

$(function() {
	$("#daochuBtn").click(function() {
		queryDegree("daochu");//导出
	});
	$("#chaxun").click(function() {
		queryDegree("chaxun");//查询
	});
});
</script>

	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
	 			<tr>
      				<td colspan="4" width="50">
						<div style="width:700px;height:50px">
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">超标电量通报</span>	
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
								<td>报账月份：</td>
								<td>
									<input class="selected_font" type="text" name="bztime"
										value="<%=bztime%>"
										readonly="readonly" class="Wdate"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
								</td>
								<td>站点属性：</td>
								<td>
									<select name="property" class="selected_font" >
										<option value="0">
											请选择
										</option>
										<%
											ArrayList zdsx = new ArrayList();
											zdsx = ztcommon.getSelctOptions("zdsx");
											if (zdsx != null) {
												String code = "", name = "";
												int cscount = ((Integer) zdsx.get(0)).intValue();
												int size = zdsx.size() - 1;
												for (int i = cscount; i < size; i += cscount) {
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
								<td>审核状态：</td>
								<td>
									<select name="status" class="selected_font">
										<option value="1">
											业务审核通过
										</option>
										<option value="2">
											财务审核通过
										</option>
									</select>
								</td>
								<td>
									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -95px; float: right; top: 0px">
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
				<table width="100%" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								地市
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								总报账电量(万度)
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								超标电量(万度)
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								超标电量占比(%)
							</div>
						</td>
					</tr>

					<c:forEach items="${list}" var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
							<td><div align="center">${list.city}</div></td>
							<td><div align="center">${list.zbzdl}</div></td>
							<td><div align="center">${list.cbdl}</div></td>
							<td><div align="center">${list.cbzb}%</div></td>
						</tr>
					</c:forEach>

					<%
						if (intnum == 0) {
							for (int i = 0; i < 17; i++) {
								if (i % 2 == 0) {
									color = "#FFFFFF";
								} else {
									color = "#DDDDDD";
								}
					%>

					<tr bgcolor="<%=color%>">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<%
						}
						} else if (!(intnum > 16)) {
							int n = ((intnum - 1) % 16);
							int j = 0;
							for (j = n; j < 16; j++) {
								if (j % 2 == 0)
									color = "#DDDDDD";
								else
									color = "#FFFFFF";
					%>
					<tr bgcolor="<%=color%>">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<%
						}
						}
					%>
					<tr height="23">
						<td bgcolor="#DDDDDD">
							<div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">
							合计
							</div>
						</td>
		      			<td bgcolor="#DDDDDD"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sumbean.zbzdlsum}</div></td>
		      			<td bgcolor="#DDDDDD"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sumbean.cbdlsum}</div></td>
						<td bgcolor="#DDDDDD"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sumbean.cbzbsum}%</div></td>
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
							<img src="<%=path%>/images/daochu.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导出</span>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
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

//回显
document.form1.bztime.value='<%=bztime%>';
document.form1.property.value = '<%=property%>';
document.form1.status.value = '<%=status%>';
</script>
</html>

