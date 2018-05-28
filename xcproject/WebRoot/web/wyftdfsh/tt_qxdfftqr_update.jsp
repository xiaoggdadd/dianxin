<%@page import="com.noki.zwhd.manage.WyManage"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.noki.zwhd.model.WydfftBean"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%
	String path = request.getContextPath();
	String id = request.getParameter("id");
	WyManage wyManage = new WyManage();
	WydfftBean wydfft = wyManage.searchWydfftById(id);
	Account account = (Account) session.getAttribute("account");
	String loginId=account.getAccountId();
%>
<html>
<head>
<title></title>
<style>
.btn {
	BORDER-RIGHT: #7b9ebd 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7b9ebd 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#cecfde );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#B3D997 );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#CAE4B6 );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#C3DAF5 );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#D7E7FA );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#C3DAF5 );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#C3DAF5 );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#FFFFFF, EndColorStr=#9DBCEA );
	BORDER-LEFT: #002D96 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #002D96 1px solid
}

.style1 {
	color: #FF0000;
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

.selected_font {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 120%;
}

.selected_font1 {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
}

.form_label1 {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height: 23px
}

.selected_font3 {
	width: 130px;
	text-align: right;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
}

.bttcn {
	color: BLACK;
	font-weight: bold;
}
</style>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
	
</script>
<script type="text/javascript">
var path = '<%=path%>';
$(function(){
	$("#saveBtn").click(function(){
		save();
	});
	$("#cancelBtn").click(function(){
	    fanhui();
	});
	$("#resetBtn").click(function(){
		$.each($("form input[type='text']"),function(){
		  $(this).val("");
	          });
		});
	});

function save(){
	document.form1.action=path+"/servlet/QxWydfftUpdate";
    document.form1.submit();
}

function fanhui(){        
	document.form1.action=path+"/web/wyftdfsh/tt_wydfft_search.jsp";
    document.form1.submit();
}
</script>
</head>
<body class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="form_label">
			<tr>
				<td>
					<div style="width:700px;height:50px">
						<img alt="" src="<%=path%>/images/btt.bmp" width="100%"
							height="100%" /> <span
							style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">区县电费分摊确认修改</span>
					</div></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="form_label">

			<tr>
				<td colspan="3">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="form_label">
						<tr>
							<td></td>
							<td>
								<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="form_label">
									<tr bgcolor="F9F9F9" class="selected_font">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />基本信息</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">省份公司：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_sfgs" value="<%=wydfft.getSFGS()==null?"":wydfft.getSFGS() %>" class="selected_font1" readonly />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">地市分公司：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_dsfgs" value="<%=wydfft.getDSFGS()==null?"":wydfft.getDSFGS() %>" class="selected_font1" readonly />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点名称：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_zdmc" value="<%=wydfft.getZDMC()==null?"":wydfft.getZDMC() %>" class="selected_font1" readonly />
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点编码：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_zdbm" value="<%=wydfft.getZDBM()==null?"":wydfft.getZDBM() %>" class="selected_font1" readonly />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">单号：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_dh" value="<%=wydfft.getDH()==null?"":wydfft.getDH() %>" class="selected_font1" readonly />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">账期：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_zq" value="<%=wydfft.getZQ()==null?"":wydfft.getZQ() %>" class="selected_font1" readonly />
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费金额：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_jfje" value="<%=wydfft.getJFJE()==null?"":wydfft.getJFJE() %>" class="selected_font1" readonly />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费日期：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_jfrq" value="<%=wydfft.getJFRQ()==null?"":wydfft.getJFRQ() %>" class="selected_font1" readonly />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">销账标识：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_xzbs" value="<%=wydfft.getXZBS()==null?"":wydfft.getXZBS() %>" class="selected_font1" readonly />
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费票据类型：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_jfpjlx" value="<%=wydfft.getJFPJLX()==null?"":wydfft.getJFPJLX() %>" class="selected_font1" readonly />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">供电方名称：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_gdfmc" value="<%=wydfft.getGDFMC()==null?"":wydfft.getGDFMC() %>" class="selected_font1" readonly />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">客户：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_kh" value="<%=wydfft.getKH()==null?"":wydfft.getKH() %>" class="selected_font1" readonly />
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">分摊比例(%)：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_ftbl" value="<%=wydfft.getFTBL()==null?"":wydfft.getFTBL() %>" class="selected_font1" readonly />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">税负因子(%)：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_fsyz" value="<%=wydfft.getFSYZ()==null?"":wydfft.getFSYZ() %>" class="selected_font1" readonly />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">分摊金额：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_ftje" value="<%=wydfft.getFTJE()==null?"":wydfft.getFTJE() %>" class="selected_font1" readonly />
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">开票类型：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_kplx" value="<%=wydfft.getKPLX()==null?"":wydfft.getKPLX() %>" class="selected_font1" readonly />
										</td>
									</tr>
									<tr bgcolor="F9F9F9">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />核对信息</td>
									</tr>
									<tr>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">对应财务报销单金额：</div></td>
										<td width="25%">
											<input type="text" readonly name="t_dycwbxdje" value="<%=wydfft.getDYCWBXJE()==null?"":wydfft.getDYCWBXJE() %>" class="selected_font1" />
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">对应财务核销单金额：</div></td>
										<td width="25%">
											<input type="text" readonly name="t_dycwhxdje" value="<%=wydfft.getDYCWHXJE()==null?"":wydfft.getDYCWHXJE() %>" class="selected_font1"/>
										</td>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">较高金额：</div></td>
										<td width="25%">
											<input type="text" readonly name="t_jgje" value="<%=wydfft.getJGJE()==null?"":wydfft.getJGJE() %>" class="selected_font1"/>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">差异：</div></td>
										<td width="25%"><input readonly type="text" name="t_cy" value="<%=wydfft.getCY()==null?"":wydfft.getCY() %>" class="selected_font1"/></td>
									</tr>
									<tr bgcolor="F9F9F9" class="selected_font">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />调整信息</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">调整后金额：</div>
										</td>
										<td width="25%"><input type="text" name="t_zthje" value="<%=wydfft.getTZHJE()==null?"":wydfft.getTZHJE() %>" class="selected_font" /></td>
									</tr>
									<tr>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">调整原因：</div>
										</td>
										<td width="20%" colspan="6">
											<textarea rows="5" cols="80" name="t_ztyy"><%=wydfft.getTZYY()==null?"":wydfft.getTZYY() %></textarea>
										</td>
									</tr>
									<tr>
										<td colspan="6">
											<div id="cancelBtn"
												style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
												<img src="<%=path%>/images/quxiao.png" width="100%"
													height="100%"> <span
													style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
											</div>
											<div id="resetBtn"
												style="position:relative;width:60px;height:23px;cursor: pointer;right:15px;float:right;">
												<img alt="" src="<%=path%>/images/2chongzhi.png"
													width="100%" height="100%" /> <span
													style="font-size:12px;position: absolute;left:28px;top:5px;color:white">重置</span>
											</div>
											<div id="saveBtn"
												style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:26px">
												<img src="<%=path%>/images/baocun.png" width="100%"
													height="100%"> <span
													style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>
											</div></td>
									</tr>
								</table></td>
						</tr>
					</table>
					</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td>&nbsp;</td>
				<td></td>
			</tr>
		</table>
		<input type="hidden" name="id" value="<%=id %>" />
	</form>
</body>
</html>


