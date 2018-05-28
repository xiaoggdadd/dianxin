<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.noki.zwhd.manage.SystemManage"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String path = request.getContextPath();
	String servletName = request.getParameter("servletname");
	System.out.println("servletName:" + servletName);
	String mfile = "";
%>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
	
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="images/css.css">
	<title>无标题文档</title>

	<style>
.style1 {
	color: #014F8A;
	font-weight: bold;
	line-height: 22pt;
}

.style2 {
	color: red;
	font-weight: bold;
	line-height: 22pt;
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

.style4 {
	color: #ff9900;
	font-weight: bold;
}

#id1 {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

img {
	border: 0px;
}
</style>
</head>

<body class="body" style="overflow-x:hidden;">
	<LINK href="../../images/css.css" type=text/css rel=stylesheet>
		<script>
function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = "";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">";
			
		}
		else
		{
			trObject.style.display = "none";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">";
		}
}
function daoru(){
	var _pc = $("#pc").val();
	if(confirm("确定删除"+_pc+"全部数据么")){
	    var path = '<%=path%>';
		document.form1.action=path+"/servlet/WydfftDelete";
		document.form1.submit();
		showdiv("请稍等..............");
	}
}
function exportModel(){
	var path = '<%=path%>';
				document.form1.action = path
						+ "/servlet/ExcelModelDownload?templetname=wydfft";
				document.form1.submit();
			}
			$(function() {
				$("#daoru").click(function() {
					daoru();
				});
			});
		</script>
		<form name="form1" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="10"></td>
					<td>&nbsp;</td>
					<td width="12"></td>
				</tr>
				<tr>
					<td width="10" height="532">&nbsp;</td>
					<td valign="top"><table width="100%" border="0"
							cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="3"><table width="100%" border="0"
										cellspacing="0" cellpadding="0">
										<tr>
											<td width="11%"><img src="../../images/in_11.gif"
												width="122" height="58" />
											</td>
											<td width="87%" background="../../images/in_12.gif"><span
												class="style4">物业电费分摊数据按批次批量删除</span>
											</td>
											<td width="2%"><img src="../../images/in_13.gif"
												width="25" height="58" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<table cellspacing="0" cellpadding="0" width="100%" border="0">
										<tbody>
											<tr id="SearchRegion">
												<td height="51" colspan="3"
													background="../../images/img_15.gif">&nbsp;</td>

												<td width="95%"><table width="100%" border="0"
														cellspacing="0" cellpadding="0">
														<tr>
															<td height="46" colspan="4"><span class="style1"> 
																	</span>
															<br /></td>
														</tr>
														<tr>
															<td height="145" colspan="3" rowspan="2"><div
																	align="center">
																	<img src="../../images/dao.gif" width="132"
																		height="119" />
																</div>
															</td>
															<td width="66%" height="64">
																<p>
																		请选择您要删除的批次。
																</p>
															</td>
														</tr>
														<tr>
															<td align="left">
																批次:<input type="text" name="pc" value="" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font" />
																<div id="daoru"
																	style="position: relative; width: 59px; height: 23px; cursor: pointer; left: 60%; TOP: -23PX ">
																	<img alt=""
																		src="<%=request.getContextPath()%>/images/tijiao.png"
																		width="100%" height="100%" /> <span
																		style="font-size:12px;position: absolute;left:28px;top:2px;color:white">确定</span>
																</div>
														<tr>
															<td height="39" valign="top" colspan="3"></td>
														</tr>
														<tr>
															<td height="51" colspan="4"><div align="center">

																</div>
															</td>
														</tr>
													</table>
												</td>
												<td background="../../images/img_17.gif">&nbsp;</td>
											</tr>
											<tr>
												<td width="29" height="26"><img
													src="../../images/img_18.gif" width="29" height="26" />
												</td>
												<td colspan="3" align="middle"
													background="../../images/img_19.gif"></td>
												<td width="25"><img src="../../images/img_20.gif"
													width="25" height="26" />
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</table></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td>&nbsp;</td>
					<td></td>
				</tr>
			</table>
			<input type="hidden" name="servletname" value="<%=servletName%>" />
		</form>
</body>
</html>
