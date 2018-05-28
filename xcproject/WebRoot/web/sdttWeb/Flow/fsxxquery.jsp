<%@ page contentType="text/html; charset=GBK"%>
<%@ page
	import="com.noki.mobi.common.Account,com.noki.mobi.flow.javabean.FlowBean"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<jsp:useBean id="stepBean" scope="page"
	class="com.noki.mobi.flow.javabean.StepBean">
</jsp:useBean>
<%
	String path = request.getContextPath();
	String flowId = request.getParameter("flowId");//获取流程id 
	String flowName = FlowBean.getFlowName(flowId);
	String flowDesc = flowName.substring(flowName.indexOf("AA") + 2);
	flowName = flowName.substring(0, flowName.indexOf("AA"));
	Account account = (Account) session.getAttribute("account");
	String role_id = account.getRoleId();
%>
<html>
	<head>
		<title>流程步骤</title>
		<style>
.btn {
	BORDER-RIGHT: #7b9ebd 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7b9ebd 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :                 DXImageTransform.Microsoft.Gradient (  
		       
		      GradientType =                 0, StartColorStr =               
		 #ffffff, EndColorStr =     
		    
		      #cecfde );
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
	FILTER: progid :                 DXImageTransform.Microsoft.Gradient (  
		       
		      GradientType =                 0, StartColorStr =               
		 #ffffff, EndColorStr =     
		    
		      #B3D997 );
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
	FILTER: progid :                 DXImageTransform.Microsoft.Gradient (  
		       
		      GradientType =                 0, StartColorStr =               
		 #ffffff, EndColorStr =     
		    
		      #CAE4B6 );
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
	FILTER: progid :                 DXImageTransform.Microsoft.Gradient (  
		       
		      GradientType =                 0, StartColorStr =               
		 #ffffff, EndColorStr =     
		    
		      #C3DAF5 );
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
	FILTER: progid :                 DXImageTransform.Microsoft.Gradient (  
		       
		      GradientType =                 0, StartColorStr =               
		 #ffffff, EndColorStr =     
		    
		      #D7E7FA );
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
	FILTER: progid :                 DXImageTransform.Microsoft.Gradient (  
		       
		      GradientType =                 0, StartColorStr =               
		 #ffffff, EndColorStr =     
		    
		      #C3DAF5 );
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
	FILTER: progid :                 DXImageTransform.Microsoft.Gradient (  
		       
		      GradientType =                 0, StartColorStr =               
		 #ffffff, EndColorStr =     
		    
		      #C3DAF5 );
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
	FILTER: progid :                 DXImageTransform.Microsoft.Gradient (  
		       
		      GradientType =                 0, StartColorStr =               
		 #FFFFFF, EndColorStr =     
		    
		      #9DBCEA );
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
	FILTER: progid :                 DXImageTransform.Microsoft.Gradient (  
		       
		      GradientType =                 0, StartColorStr =               
		 #ffffff, EndColorStr =     
		    
		      #D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}
</style>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	
</script>

	</head>
	<jsp:useBean id="roleBean" scope="page"
		class="com.noki.mobi.sys.javabean.RoleBean">
	</jsp:useBean>

	<body class="body" style="overflow-x: hidden;">
		<LINK href="../../images/css.css" type="text/css" rel="stylesheet">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="10" height="500" background="../../images/img_10.gif">
						&nbsp;
					</td>
					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="4">
												&nbsp;
											</td>
										</tr>

										<tr>
											<td colspan=1 width="600"
												background="<%=path%>/images/btt12.bmp" height=63>
												<span style="color: black; font-weight: bold;">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;流程步骤</span>
											</td>

											<td width="380">
												&nbsp;
											</td>
										</tr>
									</table>
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
															<table width="80%" border="0" cellspacing="1"
																cellpadding="1" align="center">

																<tr bgcolor="F9F9F9">
																	<td width="35%" bgcolor="#DDDDDD">
																		<div align="center">
																			流程名称：
																		</div>
																	</td>
																	<td width="65%">
																		<%=flowName%>

																	</td>
																</tr>
																<tr>
																	<td height="35%" bgcolor="#DDDDDD">
																		<div align="center">
																			流程说明：
																		</div>
																	</td>
																	<td>
																		<%=flowDesc%>

																	</td>
																</tr>
															</table>
															<table width="80%" border="0" cellspacing="1"
																cellpadding="1" align="center">

																<tr>
																	<table width="80%" border="0" cellspacing="1"
																		cellpadding="1" align="center">
																		<tr align="center">
																			<th width="10">
																				步骤顺序
																			</th>
																			<th width="120">
																				步骤名称
																			</th>
																			<!--  <th width="80">选择</th> -->
																		</tr>
																		<%
																			ArrayList list = new ArrayList();
																			list = FlowBean.getStepByFlowId(flowId);
																			int countColum = ((Integer) list.get(0)).intValue();
																			String no = "", actionname = "";
																			if (list != null) {
																				for (int i = countColum; i < list.size() - 1; i += countColum) {
																					no = (String) list.get(i + list.indexOf("STEP_ORDER_NO"));
																					actionname = (String) list.get(i
																							+ list.indexOf("ACTIONNAME"));
																		%>

																		<!-- 数据加载  Start-->
																		<tr align="center">
																			<td align="center"><%=no%></td>
																			<td align="center"><%=actionname%></td>
																			<%
																				}
																				}
																			%>
																		
																	</table>
																</tr>
																<td align="right" colspan="8" height="60px">
																		<input onclick="fanhui()" type="button" class="btn_c1"
																			id="button2" value="返回" />&nbsp;
																</td>

																</tr>
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
			<%--<input type="hidden" name="step" value="<%=actionId%>" />
		--%>
		</form>
	</body>
</html>
<script type="text/javascript">
var path = '<%=path%>';
var n = 1;
var flowId = '<%=flowId%>';

function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = ""
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

	} else {
		trObject.style.display = "none"
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
	}
}


function fanhui(){
		window.close();
	}
</script>
