<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String role_id = account.getRoleId();
%>
<html>
	<head>
		<LINK href="../../images/css.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/web/css/quanxianliebiaojsp.css" />
		<title>roleManage</title>

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
function addRole() {
	window
			.open('addbiaoshi.jsp', '',
					'width=600,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
}
function bianji(roleid, rolename) {
	alert("完善中！");
	return;
	window
			.open('modifyfenzu.jsp?roleId=' + roleid + '&roleName=' + rolename,
					'',
					'width=600,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
}
function delRole() {

	var i = 0;

	for ( var j = 0; j < document.form1.elements.length; j++) {
		if (document.form1.elements[j].checked) {
			i++;
		}
	}

	if (i > 0) {
		if (confirm("确定要删除这些权限标示么！不可恢复")) {
			document.form1.action = path + "/servlet/fenzu?action=del_bs";
			document.form1.submit();
		} else {
			return;
		}
	} else {
		alert("请选择要删除的权限标示");
		return;
	}
}
</script>
	</head>
	<jsp:useBean id="bean" scope="page"
		class="com.noki.mobi.sys.javabean.FenzuBean">
	</jsp:useBean>
	<body class="body" style="overflow-x: hidden;"> 
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
				<tr> 
					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr><%--table2 --%>
								<td colspan="3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
										<tr>
											<td colspan=1 width="600" background="<%=path%>/images/btt12.bmp" height=63>
												<span style="color: black; font-weight: bold;">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权限管理信息列表</span>
											</td>

											<td width="380">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr><%--table2 --%>
							<tr><%--table2 --%>
								<td colspan="3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="4"></td>
											<td>
												<table width="100%" border="0" align="right" cellpadding="0"
													cellspacing="0" bgcolor="7C92B7">
													<tr>
														<td height="49" bgcolor="#FFFFFF">
															<table width="100%" border="0" cellspacing="1"
																cellpadding="1" bgcolor="#cbd5de">
																 
																<tr bgcolor="F9F9F9">

																	<td align="right" colspan="7"> 
																	</td>
																</tr>
																 

																<tr>
																	<td width="22%" height="23" bgcolor="#888888">
																		<div align="center" class="bttcn">
																			序号
																		</div>
																	</td>
																	<td height="23" bgcolor="#888888">
																		<div align="center" class="bttcn">
																			名称
																		</div>
																	</td>
																	<td height="23" bgcolor="#888888">
																		<div align="center" class="bttcn">
																			标识
																		</div>
																	</td>
																	<td height="23" bgcolor="#888888">
																		<div align="center" class="bttcn">
																			说明
																		</div>
																	</td>
																 
																</tr>
																<%
																	ArrayList list = new ArrayList();
																	list = bean.getAllBiaoshi();
																	int num = 1;
																	int countColum = ((Integer) list.get(0)).intValue();
																	String roleName = "", roleId = "", orderid = "", memo = "", biaoshi = "";
																	String color = "F3F3F3";
																	for (int i = countColum; i < list.size() - 1; i += countColum) {
																		roleId = (String) list.get(i + list.indexOf("ID"));
																		roleName = (String) list.get(i + list.indexOf("NAME"));
																		memo = (String) list.get(i + list.indexOf("MEMO"));
																		orderid = (String) list.get(i + list.indexOf("ORDERID"));
																		biaoshi = (String) list.get(i + list.indexOf("BIAOSHI"));
																		if (num % 2 == 0) {
																			color = "#DDDDDD";
																		} else {
																			color = "FFFFFF";
																		}
																%>
																<tr>
																	<td bgcolor="<%=color%>">
																		<div align="center"><%=num++%></div>
																	</td>
																	<td bgcolor="<%=color%>">
																		<div align="center"><%=roleName%></div>
																	</td>
																	<td bgcolor="<%=color%>">
																		<div align="left"><%=biaoshi%></div>
																	</td>
																	<td bgcolor="<%=color%>">
																		<div align="left"><%=memo%></div>
																	</td>
																 
																</tr>
																<%
																	}
																%>

																 

															</table>
														</td>
													</tr>
													 
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

						</form>
	</body>
</html>

