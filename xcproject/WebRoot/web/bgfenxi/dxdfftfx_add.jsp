<%@ page import="com.noki.zwhd.manage.WyManage"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.noki.zwhd.model.WydfftBean"%>
<%@ page import="com.noki.zwhd.manage.SystemManage" %>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.DwBean"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page import="com.noki.database.DataBase" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.noki.zwhd.model.DianbiaoBean"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%
	String path = request.getContextPath();
	String _dw = request.getParameter("t_dw");
	String _qx = request.getParameter("t_qx");
	String _zdmc = request.getParameter("t_zdmc");
	String sheng = (String) session.getAttribute("accountSheng");
	SystemManage systemManage = new SystemManage();
	ZhandianBean zhandian = null;
	List<DianbiaoBean> dianbiaos = null;
	if(_zdmc!=null&&!_zdmc.equals("")&&!_zdmc.equals("0")&&!_zdmc.equals("null")&&!_zdmc.equals("NULL")){
		//获取站点详细信息
		zhandian = systemManage.searchZhandianByJcode(_zdmc);
		//获取站点下
		dianbiaos = systemManage.searchDianbaoList(zhandian.getID());
	}
	
	String pici = request.getParameter("pici");
	Account account = (Account) session.getAttribute("account");
	String loginId=account.getAccountId();	
	ZhandianBean page_zhandian = null;
	String sql="select * from tbl_tt_wy_dfft_tdbg where yearmonth='"+pici+"' and kh='电信'";
	System.out.println(sql);
	DataBase db = new DataBase();
	ResultSet rs = null;
	String dh="",jfqsrq="",jfzzrq="",ftje="",zdbm="",zdid="",danjia="",qm="",zm="",dl="",jfje="",jfrq="",jfpjlx="",gdfmc="",ftbl="",fsyz="";
	db.connectDb();
	rs = db.queryAll(sql.toString());
	while(rs.next()){
		 dh = rs.getString("dh");
		 jfqsrq = rs.getString("cb_jfqsrq");
		 jfzzrq = rs.getString("cb_jfzzrq");
		 qm = rs.getString("cb_qm");
		 zm  =rs.getString("cb_zm");
		 dl = rs.getString("cb_dliang");
		 jfje = rs.getString("jfje");
		 jfrq = rs.getString("jfrq");
		 jfpjlx = rs.getString("jfpjlx");
		 gdfmc=rs.getString("gdfmc");
		 ftbl=rs.getString("ftbl");
		 ftje=rs.getString("ftje");
		 fsyz=rs.getString("fsyz");
		 zdbm=rs.getString("zdbm");
		 
	}
	String _dbbm = request.getParameter("t_dbbm");
	if(_dbbm=="0"){
		SystemManage systemManage1 = new SystemManage();
		page_zhandian = systemManage.searchJzByCode(_zdmc);
 	}else{
 		SystemManage systemManage1 = new SystemManage();
 		page_zhandian = systemManage.searchJzByCode(_zdmc);
 		DataBase db2 = new DataBase();
 		ResultSet rs2 = null;
 		String sql3="select danjia from dianbiao where dbbm='"+_dbbm+"'";
 		System.out.println(sql3);
 		db2.connectDb();
 		rs2=db2.queryAll(sql3.toString());
 		while(rs2.next()){
 			danjia = rs2.getString("danjia");
 	}
 	}
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

.selected_font13 {
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
<script type="text/javascript" src="<%=path%>/javascript/PopupCalendar.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
	
</script>
<!-- 年月日期控件 -->
<script >
var oCalendarEnny=new PopupCalendarny("oCalendarEnny");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();
var oCalendarChsny=new PopupCalendarny("oCalendarChsny");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChsny.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChsny.oBtnTodayTitle="确定";
oCalendarChsny.oBtnCancelTitle="取消";
oCalendarChsny.Init();
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
	if(
	checkNotnull(document.form1.t_jfqsrq,"缴费起始日期")&&
	checkNotnull(document.form1.t_jfzzrq,"缴费终止日期")&&
	checkNotnull(document.form1.t_qm,"起码")&&
	checkNotnull(document.form1.t_zm,"止码")&&
	checkNotnull(document.form1.t_dliang,"电量")&&
	checkNotnull(document.form1.t_dj,"单价")&&
	checkNotnull(document.form1.t_dliu,"电流")
	)
	{
	var qs = document.form1.t_jfqsrq.value;
	var zz = document.form1.t_jfzzrq.value;
	var qm =  parseInt(document.form1.t_qm.value);
	var zm =  parseInt(document.form1.t_zm.value);
	var danjia = parseInt(document.form1.t_dj.value);
	if(qs>zz){
		alert("缴费起始日期大于等于缴费终止日期");
	}else if(qm>zm|qm<=0|zm<0){
		alert("起码必须小于止码且都要大于0");
	}else if(danjia<0){
		alert("单价要大于0");
	}else{
	if(confirm("您将要添加电信电费单信息！确认信息正确！")){
	document.form1.action=path+"/servlet/Dxdfftfx?action=dxadd";
    document.form1.submit();
    }
    }
    }
}
function danjiaa(){
	var dl = parseInt(document.form1.t_dliang.value);
	var jfje = parseInt(document.form1.t_jfje.value);
	var gdfs = (document.form1.t_gdfs.value);
	if(gdfs=="直供电"){
		document.form1.t_dj.value=(jfje/dl).toFixed(4);		
	}else{
		document.form1.t_dj.value='<%=danjia%>';
	}
}
function jfrqchange(){
	document.form1.t_jfrq.value=document.form1.t_jfzzrq.value;
}
function fanhui(){        
	document.form1.action=path+"/web/bgfenxi/dxdfftfx_search.jsp";
    document.form1.submit();
}
function jisdl(){
	var qima = parseInt(document.form1.t_qm.value);
	var zhima = parseInt(document.form1.t_zm.value);
	document.form1.t_dliang.value=zhima-qima;
	if(document.form1.t_dliang.value!=null){
		danjiaa();
	}
}
function pageRefsh(){
	document.form1.action=path+"/web/bgfenxi/dxdfftfx_add.jsp";
    document.form1.submit();
}
function changepici(){
	document.form1.action=path+"/web/bgfenxi/dxdfftfx_add.jsp";
    document.form1.submit();
}
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="form_label">
			<tr>
				<td>
					<div style="width:700px;height:50px">
						<img alt="" src="<%=path%>/images/btt.bmp" width="100%"
							height="100%" /> <span
							style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电信电费单添加</span>
					</div>
					</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="form_label">

			<tr>
				<td colspan="3">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="form_label">
						<tr>
							<td>
								<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="form_label">
									<tr height="23px">
										<td>&nbsp;</td>
									</tr>
									<tr bgcolor="F9F9F9" class="selected_font1">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />基本信息</td>
									</tr>
									<tr class="selected_font1">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">单位：</div>
										</td>
										<td width="25%">
											<select name="t_dw" class="selected_font" onchange="pageRefsh();">
												<option value="0" >请选择</option>
											<%
												SystemManage systemManage2 = new SystemManage();
												List<DwBean> dwList = systemManage.searchDw(sheng,loginId);
												for(int i=0;i<dwList.size();i++){
													DwBean dw = dwList.get(i);
											%>
												<option value="<%=dw.getAGCODE()%>" <%if(_dw!=null&&_dw.equals(dw.getAGCODE())){ %>selected="selected"<%} %>><%=dw.getAGNAME() %></option>
											<%
												} 
											%>
											</select>
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">区县：</div>
										</td>
										<td width="25%">
											<select name="t_qx" class="selected_font" onchange="pageRefsh();">
												<option value="0" >请选择</option>
											<%
												List<DwBean> qxList = systemManage.searchQx(_dw,loginId);
												for(int i=0;i<qxList.size();i++){
												DwBean qx = qxList.get(i);
											%>
												<option value="<%=qx.getAGCODE()%>" <%if(_qx!=null&&_qx.equals(qx.getAGCODE())){ %>selected="selected"<%} %>><%=qx.getAGNAME() %></option>
											<%
												} 
											%>
											</select>
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点名称：</div>
										</td>
										<td width="25%">
											<select name="t_zdmc" class="selected_font" onchange="pageRefsh();">
											<option value="0">请选择</option>
         									<%
												List<ZhandianBean> ZhandianList = systemManage.searchZhandian(_qx);
												for(int i=0;i<ZhandianList.size();i++){
													ZhandianBean _zhandian = ZhandianList.get(i);
											%>
												<option value="<%=_zhandian.getJZCODE()%>"<%if(_zdmc!=null&&_zdmc.equals(_zhandian.getJZCODE())){ %>selected="selected"<%} %>><%=_zhandian.getJZNAME() %></option>
											<%
												} 
											%>
											</select>
											<span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点编码：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_zdbm" value="<%=_zdmc==null||_zdmc.equals("null")?"":_zdmc %>" class="selected_font" readonly="true"  />
											<span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">供电方式：</div>
										</td>
										<td width = "25%">
										<input type="text" name="t_gdfs" value="<%=zhandian==null?"":zhandian.getGDFS() %>" class="selected_font" readonly="true" /><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">结算方式：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_jsfs" value="<%=zhandian==null?"":zhandian.getJSFS() %>"  class="selected_font" readonly="true" /><span class="style1">&nbsp;*</span>
											<span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr class="selected_font">
									<td bgcolor="#DDDDDD" width="15%"><div align="left">电表编码：</div>
										<td width="25%">
											<select name="t_dbbm" class="selected_font" onchange="pageRefsh();" >
											<option value="0" >请选择</option>
												<%
													if(dianbiaos!=null&&dianbiaos.size()!=0){
													for(int i=0;i<dianbiaos.size();i++){
														DianbiaoBean dianbiao = dianbiaos.get(i);
												%>
												<option value="<%=dianbiao.getDBBM() %>" <%if(_dbbm!=null&&_dbbm.equals(dianbiao.getDBBM())){ %>selected="selected"<% }%>><%=dianbiao.getDBBM() %></option>
												<%
													}
													}
												 %>
												
											</select>
											<span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr bgcolor="F9F9F9" class="selected_font">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15"/>铁塔缴费信息</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr class="selected_font">
									<td bgcolor="#DDDDDD" width="15%"><div align="left">批次</td>
									<td><input type="text" id="pici" name="pici" value='<%=pici==null?"":pici%>' onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" align="right" class="selected_font" onchange="changepici()"/> <span
										style="color: #FF0000; font-weight: bold">*</span></td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">单号：</div>
										</td>
										<td width="25%"><input type="text" name="t_dh" value="<%=dh %>" readonly="readonly" class="selected_font" /><span class="style1">&nbsp;*</span></td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">上次抄表时间：</div>
										</td>
										<td><input type="text" id="t_jfqsrq" name="t_jfqsrq" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"   align="right" class="selected_font"/> <span
										style="color: #FF0000; font-weight: bold">*</span></td>
										</tr>
										<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">本次抄表时间：</div>
										</td>
										<td><input type="text" id="t_jfzzrq" name="t_jfzzrq" value="" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" onChange="jfrqchange()" align="right" class="selected_font"/> <span
										style="color: #FF0000; font-weight: bold">*</span></td>					
										<td bgcolor="#DDDDDD" width="15%"><div align="left">起码：</div>
										<td width="25%">
											<input type="text" name="t_qm" value="" class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">止码：</div>
										<td width="25%">
											<input type="text" name="t_zm" value="" class="selected_font" onChange="jisdl()" /><span class="style1">&nbsp;*</span>
										</td>
										</tr>
										<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">电量：</div>
										<td width="25%">
											<input type="text" name="t_dliang" value="" class="selected_font" onChange="danjiaa()" /><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">电费单价(元/度)：</div>
										<td width="25%">
											<input type="text" name="t_dj" value="" class="selected_font" onChange="danjiaa()" /><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费金额：</div>
										<td width="25%">
											<input type="text" name="t_jfje" value="<%=jfje%>" readonly="readonly" class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>
										</tr>
										<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费日期：</div>
										<td width="25%">
											<input type="text" name="t_jfrq" value="" class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>									
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费票据类型：</div>
										<td width="25%">
											<input type="text" name="t_jfpjlx" value="<%=jfpjlx%>" readonly="readonly" class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">供电方/业主名称：</div>
										<td width="25%">
											<input type="text" name="t_gdfmc" value="<%=gdfmc%>" readonly="readonly" class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr bgcolor="F9F9F9">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />运营商分摊信息</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电流（A）：</div></td>
										<td width="25%">
											<input type="text"  name="t_dliu" value="" class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">分摊比例(%)：</div></td>
										<td width="25%">
											<input type="text"  name="t_ftbl" value="<%=ftbl %>" readonly="readonly" class="selected_font"/><span class="style1">&nbsp;*</span>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">税负因子(%)：</div></td>
										<td width="25%">
											<select name="t_fsyz">
											<option value="0">0</option>
											<option value="19.64">19.64</option>
											<span class="style1">&nbsp;*</span>
											</select>
										</td>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">分摊金额：</div></td>
										<td width="25%">
											<input type="text"  name="t_ftje" value="<%=ftje %>" readonly="readonly" class="selected_font" /><span class="style1">&nbsp;*</span>
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
		<input type="hidden" name="zdid" value="" />
	</form>
</body>
</html>


