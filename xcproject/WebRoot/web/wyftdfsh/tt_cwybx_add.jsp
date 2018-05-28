<%@page import="com.noki.zwhd.model.CwybxBean"%>
<%@page import="com.noki.zwhd.manage.CwManage"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String t_rq = request.getParameter("t_rq")==null||request.getParameter("t_rq").equals("")?"":request.getParameter("t_rq");
	String t_bxlx = request.getParameter("t_bxlx")==null||request.getParameter("t_bxlx").equals("")?"外部系统导入":request.getParameter("t_bxlx");
	String t_djbh = request.getParameter("t_djbh")==null||request.getParameter("t_djbh").equals("")?"":request.getParameter("t_djbh");
	String t_zy = request.getParameter("t_zy")==null||request.getParameter("t_zy").equals("")?"":request.getParameter("t_zy");
	String t_djzt = request.getParameter("t_djzt")==null||request.getParameter("t_djzt").equals("")?"完成":request.getParameter("t_djzt");
	String t_bm = request.getParameter("t_bm")==null||request.getParameter("t_bm").equals("")?"建设维护部":request.getParameter("t_bm"); 
	String t_yearmonth = request.getParameter("t_yearmonth")==null||request.getParameter("t_yearmonth").equals("")?"":request.getParameter("t_yearmonth");
	String t_sfje = request.getParameter("t_sfje")==null||request.getParameter("t_sfje").equals("")?"":request.getParameter("t_sfje");
	String t_fphsje = request.getParameter("t_fphsje")==null||request.getParameter("t_fphsje").equals("")?"":request.getParameter("t_fphsje");
	String t_fpbshje = request.getParameter("t_fpbshje")==null||request.getParameter("t_fpbshje").equals("")?"":request.getParameter("t_fpbshje");
	String t_jfpjlx = request.getParameter("t_jfpjlx")==null||request.getParameter("t_jfpjlx").equals("")?"":request.getParameter("t_jfpjlx");
	String t_jsdh = request.getParameter("t_jsdh")==null||request.getParameter("t_jsdh").equals("")?"":request.getParameter("t_jsdh");
	String t_zdmc = request.getParameter("t_zdmc")==null||request.getParameter("t_zdmc").equals("")?"":request.getParameter("t_zdmc");
	String t_zdbh = request.getParameter("t_zdbh")==null||request.getParameter("t_zdbh").equals("")?"":request.getParameter("t_zdbh");
	String t_cwyfthydcwbxje = request.getParameter("t_cwyfthydcwbxje")==null||request.getParameter("t_cwyfthydcwbxje").equals("")?"":request.getParameter("t_cwyfthydcwbxje");
	String t_cy = request.getParameter("t_cwcy")==null||request.getParameter("t_cwcy").equals("")?"":request.getParameter("t_cwcy");
	String t_wyftje = request.getParameter("t_wyftje")==null||request.getParameter("t_wyftje").equals("")?"":request.getParameter("t_wyftje");
	String t_fsyz = request.getParameter("t_fsyz")==null||request.getParameter("t_fsyz").equals("")?"":request.getParameter("t_fsyz");
	String t_bz = request.getParameter("t_bz")==null||request.getParameter("t_bz").equals("")?"":request.getParameter("t_bz");
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
	$(function() {
		$("#heduiBtn").click(function(){
			heduiBtn();
		});
		$("#saveBtn").click(function() {
			save();
		});
		$("#cancelBtn").click(function() {
			fanhui();
		});
		$("#resetBtn").click(function() {
			$.each($("form input[type='text']"), function() {
				$(this).val("");
			});
		});
	});
	
	function heduiBtn(){
		//表单前台校验
		document.form1.action = path + "/web/wyftdfsh/tt_cwybx_add.jsp";
		document.form1.submit();
	}

	function save() {
		//表单前台校验
		document.form1.action = path + "/servlet/CwybxAdd";
		document.form1.submit();
	}

	function fanhui() {
		document.form1.action = path + "/web/wyftdfsh/tt_cwybx_search.jsp";
		document.form1.submit();
	}

	function pageRefsh() {
		document.form1.action = path + "/web/wyftdfsh/tt_cwybx_add.jsp";
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
							style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">财务已报销数据添加</span>
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
								<table width="100%" border="0" align="right" cellpadding="1"
									cellspacing="1" class="form_label">
									<tr bgcolor="F9F9F9" class="selected_font">
										<td height="19" colspan="4"><img src="../../images/v.gif"
											width="15" height="15" />基本信息</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">批次：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_yearmonth" value="<%=t_yearmonth %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
											class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr bgcolor="F9F9F9" class="selected_font">
										<td height="19" colspan="4"><img src="../../images/v.gif"
											width="15" height="15" />财务数据</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">日期：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_rq" value="<%=t_rq %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy.MM.dd'})"
											class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">报销类型：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_bxlx" value="<%=t_bxlx %>"
											class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">单据编号：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_djbh" value="<%=t_djbh %>"
											class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">摘要：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_zy" value="<%=t_zy %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">单据状态：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_djzt" value="<%=t_djzt %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">部门：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_bm" value="<%=t_bm %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">实付金额：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_sfje" value="<%=t_sfje %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">发票含税金额：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_fphsje" value="<%=t_fphsje %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">发票不含税金额：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_fpbshje" value="<%=t_fpbshje %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">结算单号：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_jsdh" value="<%=t_jsdh %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">站点名称：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_zdmc" value="<%=t_zdmc %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">站点编号：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_zdbh" value="<%=t_zdbh %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">从物业分摊还原到财务报销金额：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_cwyfthydcwbxje" value="<%=t_cwyfthydcwbxje %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">差异：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_cwcy" value="<%=t_cy %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">物业分摊金额：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_wyftje" value="<%=t_wyftje %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">税负因子：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_fsyz" value="<%=t_fsyz %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">备注：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_bz" value="<%=t_bz %>" class="selected_font" />
											<span class="style1">&nbsp;*</span>
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
			</tr>
		</table>
	</form>
</body>
</html>


