<%@page import="com.noki.zwhd.manage.CwManage"%>
<%@page import="com.noki.zwhd.model.CwybxBean"%>
<%@page import="com.noki.zwhd.manage.WyManage"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.noki.zwhd.model.WydfftBean"%>
<%@ page import="com.noki.zwhd.manage.SystemManage"%>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.DwBean"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%
	String path = request.getContextPath();
	String sheng = (String) session.getAttribute("accountSheng");
	Account account = (Account) session.getAttribute("account");
	String loginId=account.getAccountId();
	String id = request.getParameter("t_wyid");
	WyManage wyManage = new WyManage();
	CwManage cwManage = new CwManage();
	WydfftBean wydfft = wyManage.searchWydfftById(id);
	//CwybxBean cwybx = cwManage.searchCwybxByWyid(id);
	String zq = wydfft.getZQ();
	String zq1 = "";
	String zq2 = "";
	if(zq==null||zq.equals("")||zq.equals("null")){
	
	}else{
		String[] zqs = zq.split(";");
		zq1 = zqs[0];
		zq2 = zqs[1];
	}
	
	
	
	String _sfgs = request.getParameter("t_sfgs")==null||request.getParameter("t_sfgs").equals("")?wydfft.getSFGS():request.getParameter("t_sfgs");
	String _dsfgs = request.getParameter("t_dsfgs")==null||request.getParameter("t_dsfgs").equals("")?wydfft.getDSFGS():request.getParameter("t_dsfgs");
	String _zdbm = request.getParameter("t_zdmc")==null||request.getParameter("t_zdmc").equals("")?wydfft.getZDBM():request.getParameter("t_zdmc");
	String _ftbl = request.getParameter("t_ftbl")==null||request.getParameter("t_ftbl").equals("")?wydfft.getFTBL():request.getParameter("t_ftbl");
	String _jfje = request.getParameter("t_jfje")==null||request.getParameter("t_jfje").equals("")?wydfft.getJFJE():request.getParameter("t_jfje");
	String _ftje = request.getParameter("t_ftje")==null||request.getParameter("t_ftje").equals("")?wydfft.getFTJE():request.getParameter("t_ftje");
	String _dycwbxdje = request.getParameter("t_dycwbxdje")==null||request.getParameter("t_dycwbxdje").equals("")?wydfft.getDYCWBXJE():request.getParameter("t_dycwbxdje");
	String _dycwhxdje = request.getParameter("t_dycwhxdje")==null||request.getParameter("t_dycwhxdje").equals("")?wydfft.getDYCWHXJE():request.getParameter("t_dycwhxdje");
	String _jgje = request.getParameter("t_jgje")==null||request.getParameter("t_jgje").equals("")?wydfft.getJGJE():request.getParameter("t_jgje");
	String _cy = request.getParameter("t_cy")==null||request.getParameter("t_cy").equals("")?wydfft.getCY():request.getParameter("t_cy");
	String _fsyz = request.getParameter("t_fsyz")==null||request.getParameter("t_fsyz").equals("")?wydfft.getFSYZ():request.getParameter("t_fsyz");
	DecimalFormat  df = new DecimalFormat("#.00");
	if(_fsyz!=null&&_ftbl!=null&&_jfje!=null&&!_fsyz.equals("")&&!_ftbl.equals("")&&!_jfje.equals("")&&!_fsyz.equals("null")&&!_ftbl.equals("null")&&!_jfje.equals("null")){
		_ftje = df.format(Double.parseDouble(_jfje)*(1+Double.parseDouble(_fsyz)/100)*(Double.parseDouble(_ftbl)/100));
	}
	
	String gs = "0";
	
	if(_fsyz!=null&&_ftbl!=null&&_ftje!=null&&!_fsyz.equals("")&&!_ftbl.equals("")&&!_ftje.equals("")&&!_fsyz.equals("null")&&!_ftbl.equals("null")&&!_ftje.equals("null")){
		gs = df.format(Double.parseDouble(_ftje)*(1+Double.parseDouble(_fsyz)/100)*(Double.parseDouble(_ftbl)/100));
	}
	
	//String gs2 = "0";
	
	//if(_fsyz!=null&&_ftbl!=null&&_fphsje!=null&&!_fsyz.equals("")&&!_ftbl.equals("")&&!_fphsje.equals("")&&!_fsyz.equals("null")&&!_ftbl.equals("null")&&!_fphsje.equals("null")){
	///	gs2 = df.format(Double.parseDouble(_fphsje)*(1+Double.parseDouble(_fsyz)/100)*(Double.parseDouble(_ftbl)/100));
	//}
	
	//String gs3 = "0";
	
	//if(_fsyz!=null&&_ftbl!=null&&_fpbhsje!=null&&!_fsyz.equals("")&&!_ftbl.equals("")&&!_fpbhsje.equals("")&&!_fsyz.equals("null")&&!_ftbl.equals("null")&&!_fpbhsje.equals("null")){
	//	gs3 = df.format(Double.parseDouble(_fpbhsje)*(1+Double.parseDouble(_fsyz)/100)*(Double.parseDouble(_ftbl)/100));
	//}
	
	//String gs4 = "0";
	
	//if(_fsyz!=null&&_ftbl!=null&&_fpbhsje!=null&&_fphsje!=null&&!_fsyz.equals("")&&!_ftbl.equals("")&&!_fpbhsje.equals("")&&!_fphsje.equals("")&&!_fsyz.equals("null")&&!_ftbl.equals("null")&&!_fpbhsje.equals("null")&&!_fphsje.equals("null")){
	//	gs4 = Double.parseDouble(gs3)>Double.parseDouble(gs4)?gs3:gs4;
	//}
	
	//String _cy1 = "0";
	
	//if(gs4!=null&&!gs4.equals("0")&&_ftje!=null&&!_ftje.equals("")){
	//	_cy1 = df.format((Double.parseDouble(_ftje) - Double.parseDouble(gs4)));
	//}
	
	//String _cy2 = "0";
	
	//if(_fphsje!=null&&!_fphsje.equals("")&&gs!=null&&!gs.equals("0")){
	//	_cy2 = df.format((Double.parseDouble(_fphsje) - Double.parseDouble(gs)));
	//}
	
	String t_dh = request.getParameter("t_dh")==null||request.getParameter("t_dh").equals("null")?wydfft.getDH():request.getParameter("t_dh");
	String t_zq1 = request.getParameter("t_zq1")==null||request.getParameter("t_zq1").equals("null")?zq1:request.getParameter("t_zq1");
	String t_zq2 = request.getParameter("t_zq2")==null||request.getParameter("t_zq2").equals("null")?zq2:request.getParameter("t_zq2");
	String t_jfje = request.getParameter("t_jfje")==null||request.getParameter("t_jfje").equals("null")?wydfft.getJFJE():request.getParameter("t_jfje");
	String t_jfrq = request.getParameter("t_jfrq")==null||request.getParameter("t_jfrq").equals("null")?wydfft.getJFRQ():request.getParameter("t_jfrq");
	String t_xzbs = request.getParameter("t_xzbs")==null||request.getParameter("t_xzbs").equals("null")?wydfft.getXZBS():request.getParameter("t_xzbs");
	String t_jfpjlx = request.getParameter("t_jfpjlx")==null||request.getParameter("t_jfpjlx").equals("null")?wydfft.getJFPJLX():request.getParameter("t_jfpjlx");
	String t_gdfmc = request.getParameter("t_gdfmc")==null||request.getParameter("t_gdfmc").equals("null")?wydfft.getGDFMC():request.getParameter("t_gdfmc");
	String t_kh = request.getParameter("t_kh")==null||request.getParameter("t_kh").equals("null")?wydfft.getKH():request.getParameter("t_kh");
	String t_bz = request.getParameter("t_bz")==null||request.getParameter("t_bz").equals("null")?wydfft.getBZ()==null||wydfft.getBZ().equals("")?"":wydfft.getBZ():request.getParameter("t_bz");
	String t_kplx = request.getParameter("t_kplx")==null||request.getParameter("t_kplx").equals("null")?wydfft.getKPLX():request.getParameter("t_kplx");
	
	//String _fphsje = request.getParameter("t_fphsje")==null||request.getParameter("t_fphsje").equals("")?"0":request.getParameter("t_fphsje");
	//String _fpbhsje = request.getParameter("t_fpbhsje")==null||request.getParameter("t_fpbhsje").equals("")?"0":request.getParameter("t_fpbhsje");
	//String t_rq = request.getParameter("t_rq")==null||request.getParameter("t_rq").equals("null")?cwybx==null||cwybx.getRQ()==null?"":cwybx.getRQ():request.getParameter("t_rq");
	//String t_bxlx = request.getParameter("t_bxlx")==null||request.getParameter("t_bxlx").equals("null")?cwybx==null||cwybx.getBXLX()==null?"外部系统导入":cwybx.getBXLX():request.getParameter("t_bxlx");
	//String t_djbh = request.getParameter("t_djbh")==null||request.getParameter("t_djbh").equals("null")?cwybx==null||cwybx.getDJPH()==null?"":cwybx.getDJPH():request.getParameter("t_djbh");
	//String t_zy = request.getParameter("t_zy")==null||request.getParameter("t_zy").equals("null")?cwybx==null||cwybx.getZY()==null?"":cwybx.getZY():request.getParameter("t_zy");
	//String t_djzt = request.getParameter("t_djzt")==null||request.getParameter("t_djzt").equals("null")?cwybx==null||cwybx.getDJZT()==null?"完成":cwybx.getDJZT():request.getParameter("t_djzt");
	//String t_bm = request.getParameter("t_bm")==null||request.getParameter("t_bm").equals("null")?cwybx==null||cwybx.getBM()==null?"建设维护部":cwybx.getBM():request.getParameter("t_bm");
	//String t_sfje = request.getParameter("t_sfje")==null||request.getParameter("t_sfje").equals("null")?cwybx==null||cwybx.getSFJE()==null?"0":cwybx.getSFJE():request.getParameter("t_sfje");
	//String t_fphsje = request.getParameter("t_fphsje")==null||request.getParameter("t_fphsje").equals("null")?cwybx==null||cwybx.getFPHSJE()==null?"0":cwybx.getFPHSJE():request.getParameter("t_fphsje");
	//String t_fpbhsje = request.getParameter("t_fbphsje")==null||request.getParameter("t_fbphsje").equals("null")?cwybx==null||cwybx.getFPBHSJE()==null?"0":cwybx.getFPBHSJE():request.getParameter("t_fbphsje");
	
	String t_yearmonth = request.getParameter("t_yearmonth")==null||request.getParameter("t_yearmonth").equals("null")?wydfft.getYEARMONTH():request.getParameter("t_yearmonth");
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
		document.form1.action = path + "/web/wyftdfsh/tt_wydfft_add.jsp";
		document.form1.submit();
	}

	function save() {
		var _zq1 = $("t_zq1").val();
		var _zq2 = $("t_zq2").val();
		if(_zq1==null||_zq2==null){
			alert("账期不能为空");
		}
		//表单前台校验
		document.form1.action = path + "/servlet/WydfftUpdate";
		document.form1.submit();
	}
	
	function changeFtje(){
		//表单前台校验
		document.form1.action = path + "/web/wyftdfsh/tt_wydfft_update.jsp";
		document.form1.submit();
	}

	function fanhui() {
		document.form1.action = path + "/web/wyftdfsh/tt_wydfft_search.jsp";
		document.form1.submit();
	}

	function pageRefsh() {
		document.form1.action = path + "/web/wyftdfsh/tt_wydfft_add.jsp";
		document.form1.submit();
	}
</script>
<script>

var oCalendarEn = new PopupCalendar("oCalendarEn"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();

var oCalendarChs = new PopupCalendar("oCalendarChs"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChs.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月","八月", "九月", "十月", "十一月", "十二月");
oCalendarChs.oBtnTodayTitle = "今天";
oCalendarChs.oBtnCancelTitle = "取消";
oCalendarChs.Init();
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
							style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">物业数据电费分摊修改</span>
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
									<tr height="23px">
										<td>&nbsp;</td>
									</tr>
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
											width="15" height="15" />物业数据</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">省份公司：</div>
										</td>
										<td width="25%"><select name="t_sfgs"
											class="selected_font" onchange="pageRefsh();">
												<option value="0">请选择</option>
												<%
													SystemManage systemManage = new SystemManage();
													List<DwBean> sfgsList = systemManage.searchSfgs();
													for (int i = 0; i < sfgsList.size(); i++) {
														DwBean sfgs = sfgsList.get(i);
												%>
												<option value="<%=sfgs.getAGCODE()%>"
													<%if (_sfgs != null && _sfgs.equals(sfgs.getAGCODE())) {%>
													selected="selected" <%}%>><%=sfgs.getAGNAME()%></option>
												<%
													}
												%>
										</select> <span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">地市分公司：</div>
										</td>
										<td width="25%"><select name="t_dsfgs"
											class="selected_font" onchange="pageRefsh();">
												<option value="0">请选择</option>
												<%
													List<DwBean> dsfgsList = systemManage.searchdsfgs(_sfgs,loginId);
													for (int i = 0; i < dsfgsList.size(); i++) {
														DwBean dsfgs = dsfgsList.get(i);
												%>
												<option value="<%=dsfgs.getAGCODE()%>"
													<%if (_dsfgs != null && _dsfgs.equals(dsfgs.getAGCODE())) {%>
													selected="selected" <%}%>><%=dsfgs.getAGNAME()%></option>
												<%
													}
												%>
										</select> <span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">站点名称：</div>
										</td>
										<td width="25%"><select name="t_zdmc"
											class="selected_font" onchange="pageRefsh();">
												<%
													List<ZhandianBean> ZhandianList = systemManage.searchZhandian(_dsfgs,null);
													for (int i = 0; i < ZhandianList.size(); i++) {
														ZhandianBean zhandian = ZhandianList.get(i);
												%>
												<option value="<%=zhandian.getJZCODE()%>"
													<%if (_zdbm != null && _zdbm.equals(zhandian.getJZCODE())) {%>
													selected="selected" <%}%>><%=zhandian.getJZNAME()%></option>
												<%
													}
												%>
										</select> <span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">站点编码：</div>
										</td>
										<td width="25%"><input type="text" name="t_zdbm"
											value="<%=_zdbm == null||_zdbm.equals("null") ? wydfft.getZDBM() : _zdbm%>"
											class="selected_font" /> <span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">单号：</div>
										</td>
										<td width="25%"><input type="text" name="t_dh" value="<%=t_dh %>"
											class="selected_font" /> <span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">账期起始日期：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_zq1" id="t_zq1" value="<%=t_zq1 %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="selected_font" /> 
											<span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">账期终止日期：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_zq2" id="t_zq2" value="<%=t_zq2 %>" id="t_zq2" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
											class="selected_font" /> 
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">缴费金额：</div>
										</td>
										<td width="25%"><input type="text" name="t_jfje" value="<%=t_jfje %>"
											class="selected_font" /> <span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">缴费日期：</div>
										</td>
										<td width="25%">
											<input type="text" id="t_jfrq" value="<%=t_jfrq==null||t_jfrq.equals("null")||t_jfrq.equals("")?"":t_jfrq %>"
											name="t_jfrq" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
											class="selected_font"/>
											<span class="style1">&nbsp;*</span></td>
										
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">销账标识：</div>
										</td>
										<td width="25%"><input type="text" name="t_xzbs"
											value="<%=t_xzbs==null||t_xzbs.equals("")?"未传送":t_xzbs %>" class="selected_font" /> <span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">缴费票据类型：</div>
										</td>
										<td width="25%"><select name="t_jfpjlx"
											class="selected_font" onchange="pageRefsh();">
												<option value="收据/白条" <%=t_jfpjlx!=null&&!t_jfpjlx.equals("")&&t_jfpjlx.equals("收据/白条")?"selected=\"selected\"":"" %>>收据/白条</option>
												<option value="17%增值税专用发票" <%=t_jfpjlx!=null&&!t_jfpjlx.equals("")&&t_jfpjlx.equals("17%增值税专用发票")?"selected=\"selected\"":"" %>>17%增值税专用发票</option>
										</select> <span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">供电方名称：</div>
										</td>
										<td width="25%"><input type="text" name="t_gdfmc"
											value="<%=t_gdfmc==null||t_gdfmc.equals("null")||t_gdfmc.equals("")?"":t_gdfmc %>" class="selected_font" /> <span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">客户：</div>
										</td>
										<td width="25%"><select name="t_kh" class="selected_font"
											onchange="pageRefsh();">
												<option value="移动" <%=t_kh!=null&&!t_kh.equals("")&&t_kh.equals("移动")?"selected=\"selected\"":"" %>>移动</option>
												<option value="联通" <%=t_kh!=null&&!t_kh.equals("")&&t_kh.equals("联通")?"selected=\"selected\"":"" %>>联通</option>
												<option value="电信" <%=t_kh!=null&&!t_kh.equals("")&&t_kh.equals("电信")?"selected=\"selected\"":"" %>>电信</option>
										</select> <span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">分摊比例(%)：</div>
										</td>
										<td width="25%"><input type="text" name="t_ftbl" value="<%=_ftbl %>"
											class="selected_font" /> <span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">税负因子(%)：</div>
										</td>
										<td width="25%"><input type="text" name="t_fsyz" value="<%=_fsyz %>"
											class="selected_font" onmouseout="changeFtje();"/> <span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="13%"><div align="left">分摊金额：</div>
										</td>
										<td width="25%"><input type="text" name="t_ftje" value="<%=_ftje %>"
											class="selected_font" /> <span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">开票类型：</div>
										</td>
										<td width="25%"><input type="text" name="t_kplx" value="<%=t_kplx==null||t_kplx.equals("")?"增值税专用发票":t_kplx %>"
											class="selected_font" /> <span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="13%"><div align="left">备注：</div>
										<td width="25%"><input type="text" name="t_bz" value="<%=t_bz %>"
											class="selected_font" />
										</td>
									</tr>
									<!-- 
									<tr bgcolor="F9F9F9">
										<td height="19" colspan="4"><img src="../../images/v.gif"
											width="15" height="15" />财务数据</td>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">日期</div></td>
										<td width="25%"><input type="text" name="t_rq" value="" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
											class="selected_font" />
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">报销类型</div></td>
										<td width="25%"><input type="text" name="t_bxlx"
											value="" class="selected_font" />
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">单据编号</div></td>
										<td width="25%"><input type="text" name="t_djbh" value=""
											class="selected_font" />
										</td>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">摘要</div></td>
										<td width="25%"><input type="text" name="t_zy" value=""
											class="selected_font" />
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">单据状态</div></td>
										<td width="25%"><input type="text" name="t_djzt" value=""
											class="selected_font" />
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">部门</div></td>
										<td width="25%"><input type="text" name="t_bm" value=""
											class="selected_font" />
										</td>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">实付金额</div></td>
										<td width="25%"><input type="text" name="t_sfje" value=""
											class="selected_font" />
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">发票含税金额</div></td>
										<td width="25%"><input type="text" name="t_fphsje"
											value="" class="selected_font" />
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">发票不含税金额</div></td>
										<td width="25%"><input type="text" name="t_fpbhsje"
											value="" class="selected_font" />
										</td>
									</tr>
									-->
									<tr bgcolor="F9F9F9">
										<td height="19" colspan="4"><img src="../../images/v.gif"
											width="15" height="15" />核对信息</td>
									</tr>
									<tr>
										<td colspan="7">
											<label style="color: red;">核对公式1：对应财务报销单金额=发票含税金额*(1+税负因子/100)*(分摊比例/100)</label>
										</td>
									</tr>
									<tr>
										<td colspan="7">
											<label style="color: red;">核对公式2：对应财务核销单金额=发票不含税金额*(1+税负因子/100)*(分摊比例/100)</label>
										</td>
									</tr>
									<tr>
										<td colspan="7">
											<label style="color: red;">核对公式3：较高金额为对应财务报销单金额与对应财务核销单金额的较高值</label>
										</td>
									</tr>
									<tr>
										<td colspan="7">
											<label style="color: red;">核对公式4：从物业分摊还原到财务报销金额：分摊金额=发票不含税金额*(1+税负因子/100)*(分摊比例/100)</label>
										</td>
									</tr>
									<tr>
										<td colspan="7">
											<label style="color: red;">核对公式5：财务差异：发票含税金额与从物业分摊还原到财务报销金额的差</label>
										</td>
									</tr>
									<tr>
										<td colspan="7">
											<label style="color: red;">核对公式5：物业差异：分摊金额与较高金额的差</label>
										</td>
									</tr>
									<tr>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">对应财务报销单金额：</div></td>
										<td width="25%"><input type="text" name="t_dycwbxdje"
											value="<%=_dycwbxdje%>" class="selected_font" />
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">对应财务核销单金额：</div></td>
										<td width="25%"><input type="text" name="t_dycwhxdje"
											value="<%=_dycwhxdje%>" class="selected_font" />
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">较高金额：</div></td>
										<td width="25%"><input type="text" name="t_jgje" value="<%=_jgje%>"
											class="selected_font" />
										</td>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">差异：</div></td>
										<td width="25%"><input type="text" name="t_cy"
											value="<%=_cy%>" class="selected_font" />
										</td>
									</tr>
									<!-- 
									
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">物业财务报销还原：</div></td>
										<td width="25%"><input type="text" name="t_cwyfthydcwbx" value=""
											class="selected_font" /></td>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">物业差异：</div></td>
										<td width="25%"><input type="text" name="t_wycy" value=""
											class="selected_font" /></td>
										<td height="8%" bgcolor="#DDDDDD" width="13%"><div
												align="left">财务差异：</div></td>
										<td width="25%"><input type="text" name="t_cwcy" value=""
											class="selected_font" /></td>
									</tr>
									-->
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
											</div>
											<!-- 
											<div id="heduiBtn"
												style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:36px">
												<img src="<%=path%>/images/baocun.png" width="100%"
													height="100%"> <span
													style="font-size:12px;position: absolute;left:27px;top:5px;color:white">核对</span>
											</div>
											 -->
											
										</td>
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
		<input type="hidden" name="t_wyid" value="<%=id %>" />
	</form>
</body>
</html>


