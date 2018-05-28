<%@ page import="com.noki.zwhd.manage.WyManage"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.noki.zwhd.model.WydfftBean"%>
<%@ page import="com.noki.zwhd.manage.SystemManage" %>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.DwBean"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.zwhd.model.DianbiaoBean"%>
<%@ page import="java.text.DecimalFormat" %>
<%
	String path = request.getContextPath();
	String id = request.getParameter("id");
	WyManage wyManage = new WyManage();
	WydfftBean wydfftBean = wyManage.searchWydfftById(id);
	
	SystemManage systemManage = new SystemManage();
	ZhandianBean zhandian = null;
	List<DianbiaoBean> dianbiaos = null;
	
	if(wydfftBean!=null){
		wydfftBean.getDH();
		String _zdbm = wydfftBean.getZDBM();
		wydfftBean.getYEARMONTH();		
		zhandian = systemManage.searchZhandianByJcode(_zdbm);
		if(zhandian!=null){
			dianbiaos = systemManage.searchDianbaoList(zhandian.getID());
		}
	}
	
	String sheng = (String) session.getAttribute("accountSheng");
	Account account = (Account) session.getAttribute("account");
	String loginId=account.getAccountId();
	
	
	String _dliang = request.getParameter("t_dliang")==null||request.getParameter("t_dliang").equals("")?"":request.getParameter("t_dliang");
	String _sccbzm = request.getParameter("t_sccbzm")==null||request.getParameter("t_sccbzm").equals("")?"":request.getParameter("t_sccbzm");
	String _bccbzm = request.getParameter("t_bccbzm")==null||request.getParameter("t_bccbzm").equals("")?"":request.getParameter("t_bccbzm");
	String _sh = request.getParameter("t_sh")==null||request.getParameter("t_sh").equals("")?"":request.getParameter("t_sh");
	
	if(_sccbzm!=null&&!_sccbzm.equals("")&&_bccbzm!=null&&!_bccbzm.equals("")&&_sh!=null&&!_sh.equals("")){
		double sccbzm = Double.parseDouble(_sccbzm);
		double bccbzm = Double.parseDouble(_bccbzm);
		double sh = Double.parseDouble(_sh);
		DecimalFormat df = new DecimalFormat("#0.00");
		_dliang = df.format(bccbzm-sccbzm+sh);
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
	document.form1.action=path+"/servlet/LtdfftqrUpdate";
    document.form1.submit();
}

function fanhui(){        
	document.form1.action=path+"/web/wyftdfsh/tt_ltdfftqr_search.jsp";
    document.form1.submit();
}

function pageRefsh(){
	document.form1.action=path+"/web/wyftdfsh/tt_ltdfftqr_add.jsp";
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
							style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">联通电费单修改</span>
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
									<tr bgcolor="F9F9F9" class="selected_font1">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />站点信息</td>
									</tr>
									<tr class="selected_font1">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">单位：</div>
										</td>
										<td width="25%">
											<select name="t_dw" class="selected_font" disabled="disabled">
												<option value="0" >请选择</option>
											<%
												List<DwBean> dwList = systemManage.searchDw(sheng,loginId);
												for(int i=0;i<dwList.size();i++){
													DwBean dw = dwList.get(i);
											%>
												<option value="<%=dw.getAGCODE()%>" <%if(zhandian!=null&&zhandian.getDw().equals(dw.getAGCODE())){ %>selected="selected"<%} %>><%=dw.getAGNAME() %></option>
											<%
												} 
											%>
											</select>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">区县：</div>
										</td>
										<td width="25%">
											<select name="t_qx" class="selected_font" disabled="disabled">
												<option value="0" >请选择</option>
											<%
												List<DwBean> qxList = systemManage.searchQx(zhandian.getDw(),loginId);
												for(int i=0;i<qxList.size();i++){
													DwBean qx = qxList.get(i);
											%>
												<option value="<%=qx.getAGCODE()%>" <%if(zhandian!=null&&zhandian.getQx().equals(qx.getAGCODE())){ %>selected="selected"<%} %>><%=qx.getAGNAME() %></option>
											<%
												} 
											%>
											</select>
											
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点名称：</div>
										</td>
										<td width="25%">
											<input name="t_zdmc" class="selected_font" disabled="disabled" value="<%=zhandian==null?"":zhandian.getJZNAME()%>">
											
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点编码：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_zdbm" value="<%=zhandian==null?"":zhandian.getJZCODE() %>" class="selected_font"  disabled="disabled"/>
											
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点地址：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_address" value="<%=zhandian==null?"":zhandian.getADDRESS() %>" class="selected_font"  disabled="disabled"/>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点经度：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_longitude" value="<%=zhandian==null?"":zhandian.getLONGITUDE() %>" class="selected_font"  disabled="disabled"/>
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点纬度：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_latitude" value="<%=zhandian==null?"":zhandian.getLATITUDE() %>" class="selected_font"  disabled="disabled"/>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">物理站址编码：</div>
										</td>
										<td width="25%"><input type="text" name="t_wlzzbm" value="<%=zhandian==null?"":zhandian.getWLZDBM()==null?"":zhandian.getWLZDBM() %>" class="selected_font" disabled="disabled"/></td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">供电方式：</div>
										</td>
										<td width="25%"><input type="text" name="t_gdfs" value="<%=zhandian==null?"":zhandian.getGDFS() %>" class="selected_font" disabled="disabled"/>
										</td>
									</tr>
									<tr>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">结算方式：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_jsfs" value="<%=zhandian==null?"":zhandian.getJSFS() %>"  class="selected_font" disabled="disabled"/>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站址类型：</div>
										</td>
										<td width="25%">
											<select name="t_zzlx" class="selected_font" disabled="disabled">
												<option value="宏站" <%if(wydfftBean!=null&&wydfftBean.getCB_ZZLX().equals("宏站")){ %>selected="selected"<%} %>>宏站</option>
												<option value="一体化电源柜" <%if(wydfftBean!=null&&wydfftBean.getCB_ZZLX().equals("一体化电源柜")){ %>selected="selected"<%} %>>一体化电源柜</option>
											</select>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">原产权单位：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_ycqdw" value="<%=zhandian==null?"":zhandian.getYCQ()%>"  class="selected_font" disabled="disabled"/>
										</td>
									</tr>
									<tr>
										<tr bgcolor="F9F9F9" class="selected_font1">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />电表信息</td>
									</tr>
									<tr>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">电表编码：</div>
										<td width="25%">
										
											<select name="t_dbbm" class="selected_font" disabled="disabled">
											<option value="0" >请选择</option>
												<%
													if(dianbiaos!=null&&dianbiaos.size()!=0){
													for(int i=0;i<dianbiaos.size();i++){
														DianbiaoBean dianbiao = dianbiaos.get(i);
												%>
												<option value="<%=dianbiao.getDBBM() %>" selected="selected"><%=dianbiao.getDBBM() %></option>
												<%
													}}
												 %>
												
											</select>
										</td>
									</tr>
									<tr>
										<tr bgcolor="F9F9F9" class="selected_font1">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />缴费信息</td>
									</tr>
									<tr>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">批次：</div>
										<td width="25%">
											<input type="text" name="t_yearmonth" value="<%=wydfftBean==null?"":wydfftBean.getYEARMONTH() %>" class="selected_font" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" disabled="disabled"/>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">单号：</div>
										<td width="25%">
											<input type="text" name="t_dh" value="<%=wydfftBean==null?"":wydfftBean.getDH() %>" class="selected_font" disabled="disabled"/>
										</td>
									</tr>
									<tr>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">账期起始日期：</div>
										<td width="25%">
											<input type="text" name="t_zq_s" value="<%=wydfftBean==null?"":wydfftBean.getZQ().split(";")[0] %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="selected_font" disabled="disabled"/><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">上次抄表时间：</div>
										<td width="25%">
											<input type="text" name="t_sccbsj" value="<%=wydfftBean==null?"":wydfftBean.getSccbsj()%>" class="selected_font" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"/><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">上次抄表止码：</div>
										<td width="25%">
											<input type="text" name="t_sccbzm" value="<%=wydfftBean==null?"":wydfftBean.getQM()%>" class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">账期结束日期：</div>
										<td width="25%">
											<input type="text" name="t_zq_e" value="<%=wydfftBean==null?"":wydfftBean.getZQ().split(";")[1] %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="selected_font" disabled="disabled"/><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">本次抄表时间：</div>
										<td width="25%">
											<input type="text" name="t_bccbsj" value="<%=wydfftBean==null?"":wydfftBean.getBccbsj()%>" class="selected_font" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"/><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">本次抄表止码：</div>
										<td width="25%">
											<input type="text" name="t_bccbzm" value="<%=wydfftBean==null?"":wydfftBean.getZM()%>" class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费起始日期：</div>
										<td width="25%">
											<input type="text" name="t_jfqsrq" value="<%=wydfftBean==null?"":wydfftBean.getJfqsrq() %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费终止日期：</div>
										<td width="25%">
											<input type="text" name="t_jfzzrq" value="<%=wydfftBean==null?"":wydfftBean.getJfzzrq() %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="selected_font"/><span class="style1">&nbsp;*</span>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">损耗：</div></td>
										<td width="25%">
											<input type="text"  name="t_sh" value="<%=wydfftBean==null?"":wydfftBean.getSh()%>" class="selected_font" onchange="pageRefsh();"/><span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">电量(度)：</div>
										<td width="25%">
											<input type="text" name="t_dliang" value="<%=wydfftBean==null?"":wydfftBean.getDLIANG()%>" class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">电费单价(元/度)：</div>
										<td width="25%">
											<input type="text" name="t_dj" value="<%=dianbiaos==null&&dianbiaos.size()>0?"0":dianbiaos.get(0).getDANJIA()%>" class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">缴费金额：</div></td>
										<td width="25%">
											<input type="text"  name="t_jfje" value="<%=wydfftBean==null?"":wydfftBean.getJFJE() %>" class="selected_font" disabled="disabled"/>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">缴费日期：</div></td>
										<td width="25%">
											<input type="text"  name="t_jfrq" value="<%=wydfftBean==null?"":wydfftBean.getJFRQ()%>" class="selected_font" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" disabled="disabled"/>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">缴费票据类型：</div></td>
										<td width="25%">
											<input type="text"  name="t_jfpjlx" value="<%=wydfftBean==null?"":wydfftBean.getJFPJLX() %>" class="selected_font" disabled="disabled"/>
										</td>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">供电方/业主名称：</div></td>
										<td width="25%">
											<input type="text"  name="t_gdfmc" value="<%=wydfftBean==null?"":wydfftBean.getGDFMC() %>" class="selected_font" disabled="disabled"/><span class="style1">&nbsp;*</span>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">分摊比例：</div></td>
										<td width="25%">
											<input type="text"  name="t_ftbl" value="<%=wydfftBean==null?"":wydfftBean.getFTBL() %>" class="selected_font" disabled="disabled"/>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">分摊金额：</div></td>
										<td width="25%">
											<input type="text"  name="t_ftje" value="<%=wydfftBean==null?"":wydfftBean.getFTJE() %>" class="selected_font" disabled="disabled"/>
										</td>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">能源管理系统ID号：</div></td>
										<td width="25%">
											<input type="text"  name="t_nyglxtid" value="<%=wydfftBean==null?"":wydfftBean.getCB_NYGLXTID()%>" class="selected_font"/><span class="style1">&nbsp;*</span>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">增值税(17%税率)：</div></td>
										<td width="25%">
											<input type="text"  name="t_zzs" value="<%=wydfftBean==null?"":wydfftBean.getCB_ZZS()%>" class="selected_font"/><span class="style1">&nbsp;*</span>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">税中税(2.64税率)：</div></td>
										<td width="25%">
											<input type="text"  name="t_szs" value="<%=wydfftBean==null?"":wydfftBean.getCB_SZS()%>" class="selected_font"/><span class="style1">&nbsp;*</span>
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
		<input type="hidden" name="t_wyid" value="<%=wydfftBean==null?"":wydfftBean.getID() %>" />
	</form>
</body>
</html>


