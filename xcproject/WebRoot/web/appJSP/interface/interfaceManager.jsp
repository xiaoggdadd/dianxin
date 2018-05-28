<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();//项目路径
	String color;//颜色
	int intnum = request.getAttribute("num") != null
			? (Integer) request.getAttribute("num")
			: 0;//条数 ,查出数据的条数，用于颜色设置
	if (intnum % 2 == 0) {
		color = "#DDDDDD";
	} else {
		color = "#FFFFFF";
	}
	int i = 0;
%>

<html>
	<head>
		<title>市级电费审核</title>
		<style>
.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}
</style>

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
$(function() {
	$("#chaxun").click(function() {
		queryDegree("chaxun");//查询
		});
});
function queryDegree(command) {
		document.form1.action = path + "/getMessageAction";
		document.form1.submit();
		if ("chaxun" == command) {
			showdiv("请稍等..............");
		}
}
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
						<div style="width: 700px;">
							<img alt="" src="<%=path%>/images/btt.bmp" width="100%"
								height="100%" />
							<span
								style="font-size: 14px; font-weight: bold; position: absolute; left: 25px; top: 15px; color: black">市级电费审核</span>
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
									账期:
								</td>
								<td>
									<input class="selected_font" type="text" name="month"
										value="<%if (null != request.getParameter("month"))
				out.print(request.getParameter("month"));%>"
										readonly="readonly" class="Wdate"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />

								</td>
							</tr>

							<tr>
								<td>

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
					cellpadding="1" bgcolor="#cbd5de" class="form_label"
					style="width: 100%;" id="MyTable">
					<tr height="23" class="relativeTag">
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								账期
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								数据请求时间
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								数据接收时间
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								状态
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								数据量(条数)
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								发起人
							</div>
						</td>
					</tr>
					<c:forEach items="${list}" var="list" varStatus="status">
						<%
							i++;
						%>
						<tr>
							<c:if test="${list.INITIATOR eq '山东审计'}">
								<tr bgcolor="red">
							</c:if>

							<td>
								<div align="center">
									<%=i%>
								</div>
							</td>
							<td>
								<div align="center">
									${list.ACCOUNTSDATE}
								</div>
							</td>
							<td>
								<div align="center">
									${list.INITIDATE}
								</div>
							</td>
							<td>
								<div align="center">
									${list.RECEIVEDATE}
								</div>
							</td>
							<td>
								<div align="center">
									<c:if test="${list.STATION eq 'N' }">
								 发起
								</c:if>
									<c:if test="${list.STATION eq 'R' }">
								 接收并执行
								</c:if>
									<c:if test="${list.STATION eq 'S' }">
								 发送完成
								</c:if>
									<c:if test="${list.STATION eq 'V' }">
								校验成功并完成接收
								</c:if>
									<c:if test="${list.STATION eq 'U' }">
								 数据异常重新发送
								</c:if>
								</div>
							</td>
							<td>
								<div align="center">
									${list.TOTALNUMBER}
								</div>
							</td>
							<td>
								<div align="center">
									${list.INITIATOR}
								</div>
							</td>
						</tr>
					</c:forEach>





				</table>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
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
</html>

