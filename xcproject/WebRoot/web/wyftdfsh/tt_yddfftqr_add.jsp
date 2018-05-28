<%@ page import="com.noki.zwhd.manage.WyManage"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.noki.zwhd.model.WydfftBean"%>
<%@ page import="com.noki.zwhd.manage.SystemManage" %>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.DwBean"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page import="com.noki.zwhd.manage.WyManage"%>
<%@ page import="com.noki.zwhd.model.WydfftBean"%>
<%@ page import="com.noki.zwhd.manage.SystemManage" %>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.DwBean"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.zwhd.model.DianbiaoBean"%>
<%
	String path = request.getContextPath();

String sheng = (String) session.getAttribute("accountSheng");
Account account = (Account) session.getAttribute("account");
String loginId=account.getAccountId();


	String _dw = request.getParameter("t_dw");
	String _qx = request.getParameter("t_qx");
	String _zdmc = request.getParameter("t_zdmc");
	ZhandianBean page_zhandian = null;
	if(_zdmc!=null){
		SystemManage systemManage = new SystemManage();
		page_zhandian = systemManage.searchJzByCode(_zdmc);
	}
	String _zdbm = request.getParameter("t_zdmc");
	
	SystemManage systemManage = new SystemManage();
	ZhandianBean zhandian = null;
	List<DianbiaoBean> dianbiaos = null;
	if(_zdbm!=null&&!_zdbm.equals("")&&!_zdbm.equals("0")&&!_zdbm.equals("null")&&!_zdbm.equals("NULL")){
		//获取站点详细信息
		zhandian = systemManage.searchZhandianByJcode(_zdbm);
		//获取站点下
		dianbiaos = systemManage.searchDianbaoList(zhandian.getID());
	}
	 
	String _dbbm = request.getParameter("t_dbbm");
	String _yearmonth = request.getParameter("t_yearmonth");
	WyManage wyManage = new WyManage();
	WydfftBean wydfftBean = null;
	if(_yearmonth!=null&&_zdbm!=null&&zhandian!=null){
		wydfftBean = wyManage.searchWydfftyd(_yearmonth,_zdbm,"移动","未修改");
	}
		String dbdja="";
	if(_dbbm!=null&&!_dbbm.equals("")){
		
		dbdja=wyManage.searchYddj(_dbbm);
	}
	WydfftBean dfbeana= new WydfftBean();
	String scsj="",scds="";
	if(_yearmonth!=null&&!_yearmonth.equals("")&&_zdbm!=null&&!_zdbm.equals("")&&!_zdbm.equals("0")&&!_zdbm.equals("null")&&!_zdbm.equals("NULL")&&_dbbm!=null&&!_dbbm.equals("")&&_yearmonth!=null&&!_yearmonth.equals("0")){
		
		//获取最近读数和最近抄表时间    如果是第一次录入则为空即可 
		dfbeana=wyManage.searchWydfftydzjds(_yearmonth,_zdbm,_dbbm,"已修改","移动");
		if(dfbeana!=null){
		scsj=dfbeana.getBccbsj()!=null?dfbeana.getBccbsj():"";
		scds=dfbeana.getZM()!=null?dfbeana.getZM():"";
		}else{
			scsj="";
			scds="";
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
//数字判断  日期判断   空 

var a=document.form1.t_sccbsj.value;//上次抄表时间
var b=document.form1.t_bccbsj.value;//本次抄表时间
var c=document.form1.t_jszq.value;//账期
var d=document.form1.t_sccbzm.value;//上次抄表止码
var e=document.form1.t_bccbzm.value;//本次抄表止码
var f=document.form1.t_sunhao.value;//耗损
var g=document.form1.t_dliang.value;//电量
var h=document.form1.t_dj.value;//单价
var i=document.form1.t_dw.value;
var j=document.form1.t_qx.value;
var k=document.form1.t_zdmc.value;
var l=document.form1.t_dbbm.value;
var p=document.form1.t_yearmonth.value;
var gdfs=document.form1.t_gdfs.value;
var bb=Number(e)-Number(d);
if( 
		checkNotSelected(document.form1.t_dw,"单位")&&
		checkNotSelected(document.form1.t_qx,"区县")&&
		checkNotSelected(document.form1.t_zdmc,"站点名称")&&
		checkNotSelected(document.form1.t_dbbm,"电表编码")&&
		
		checkNotnull(document.form1.t_yearmonth,"批次")&&
		checkNotnull(document.form1.t_sccbsj,"上次抄表时间")&&
		checkNotnull(document.form1.t_bccbsj,"本次抄表时间")&&
		checkNotnull(document.form1.t_jszq,"账期")&&
		checkNotnull(document.form1.t_sccbzm,"上次抄表止码")&&
		checkNotnull(document.form1.t_bccbzm,"本次抄表止码")&&
		checkNotnull(document.form1.t_sunhao,"耗损")&&
		checkNotnull(document.form1.t_dliang,"电量")&&
		checkNotnull(document.form1.t_dj,"电费单价")
){
	if(b>=a){
		if(Number(bb)>0&&Number(e)>=0&&Number(d)>0){
  if(isNaN(c)==false&&isNaN(d)==false&&isNaN(e)==false&&isNaN(f)==false&&isNaN(g)==false&&isNaN(h)==false){
	  if("直供电"==gdfs&&Number(h)<1){
	  if (confirm("您将要添加信息！确认信息正确！")) {
	document.form1.action=path+"/servlet/YddfftqrAdd";
    document.form1.submit();
    }
	  }else{
		  alert("供电方式为直供电的电费单价不能大于1元！！！");
	  }
    }else {
    	alert("账期、上次抄表止码、本次抄表止码、耗损、电量、电费单价 必须为数字，请核对！！！");
    }
    }else{
    	alert("本次抄表止码必须大于上次抄表止码且不能为负数，请核对！！！ ")
    }
    }else {
    	alert("本次抄表时间必须大于上次抄表时间，请核对！！！");
    }
}
    
    
}

function fanhui(){        
	document.form1.action=path+"/web/wyftdfsh/tt_yddfftqr_search.jsp";
    document.form1.submit();
}

function pageRefsh(){
	document.form1.action=path+"/web/wyftdfsh/tt_yddfftqr_add.jsp";
    document.form1.submit();
}
function DateUpdate(){
	var sc=document.form1.t_sccbsj.value;
	var bc=document.form1.t_bccbsj.value;
	
	var ts="";
	if(sc!=null&&bc!=null&&sc!=""&&bc!=""){
		ts=DateDiff(bc,sc);
		document.form1.t_jfrq.value=bc;
	}
	       document.form1.t_jszq.value=ts;
	
}

function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
       var  aDate,  oDate1,  oDate2,  iDays  
       aDate  =  sDate1.split("-")  
       oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式  
       aDate  =  sDate2.split("-")  
       oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
       iDays  =  parseInt((oDate1  -  oDate2)  /  1000  /  60  /  60  /24+1)    //把相差的毫秒数转换为天数  
       return  iDays
   } 
function DbdsUpdate(){
	var scds=document.form1.t_sccbzm.value;
	var bcds=document.form1.t_bccbzm.value;
	var sh=document.form1.t_sunhao.value;
	var a="";
	var b="",c="",d="";
	if(scds!=null&&scds!=""){
		b=(Math.round(scds*100)/100).toFixed(2);
		document.form1.t_sccbzm.value=b;
	}
	if(bcds!=null&&bcds!=""){
		c=(Math.round(bcds*100)/100).toFixed(2);
		document.form1.t_bccbzm.value=c;
	}
	if(sh!=null&&sh!=""){
		d=(Math.round(sh*100)/100).toFixed(2);
		document.form1.t_sunhao.value=d;
	}
	if(scds!=null&&scds!=""&&bcds!=null&&bcds!=""){
	  a=(parseFloat(c)-parseFloat(b));
	  a=(Math.round(a*100)/100).toFixed(2);
	  document.form1.t_dliang.value=a;
	}
	if(a!=""){
		DjupDate();
	}
}
function DjupDate(){
	var dj='<%=dbdja%>';
	var gdfsa=document.form1.t_gdfs.value;
    var dl   =document.form1.t_dliang.value;
    var jfje =document.form1.t_jfje.value;
    if(gdfsa!=null&&gdfsa!=""&&jfje!=null&&jfje!=""&&dl!=null&&dl!=""){
    	if("转供电"==gdfsa){
    		dj=(Math.round(dj*100)/100).toFixed(4);
    		document.form1.t_dj.value=dj;
    	}else if("直供电"==gdfsa){
    		dj=jfje/dl;
    		 dj=(Math.round(dj*100)/100).toFixed(4);
    			document.form1.t_dj.value=dj;
    	}
    }
    
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
						<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">移动电费单添加</span>
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
												<option value="0" >请选择</option>
											<%
												List<ZhandianBean> ZhandianList = systemManage.searchZhandianyd(_shi,_qx);
												for(int i=0;i<ZhandianList.size();i++){
													ZhandianBean _zhandian = ZhandianList.get(i);
											%>
												<option value="<%=_zhandian.getJZCODE()%>" <%if(_zdbm!=null&&_zdbm.equals(_zhandian.getJZCODE())){ %>selected="selected"<%} %>><%=_zhandian.getJZNAME() %></option>
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
											<input type="text" name="t_zdbm" value="<%=_zdbm==null||_zdbm.equals("null")?"":_zdbm %>" class="selected_font"  />
										</td>
											<td bgcolor="#DDDDDD" width="15%"><div align="left">站点地址：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_address" value="<%=zhandian==null?"":zhandian.getADDRESS() %>" class="selected_font"  />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点经度：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_longitude" value="<%=zhandian==null?"":zhandian.getLONGITUDE() %>" class="selected_font"  />
										</td>
										</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点纬度：</div>
										</td>
										
											<td width="25%">
											<input type="text" name="t_latitude" value="<%=zhandian==null?"":zhandian.getLATITUDE() %>" class="selected_font"  />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">资产交接站址编码：</div>
										</td>
										<td width="25%"><input type="text" name="t_zcjjzzbm" value="" class="selected_font" /></td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">供电方式：</div>
										</td>
										<td width="25%"><input type="text" name="t_gdfs" value="<%=zhandian==null?"":zhandian.getGDFS() %>" class="selected_font" />
										</td>
										</tr>
										<tr class="selected_font">
										
										<td bgcolor="#DDDDDD" width="15%"><div align="left">结算方式：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_jsfs" value="<%= page_zhandian==null||page_zhandian.getJSFS()==null?"":page_zhandian.getJSFS() %>" class="selected_font"  />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">电表编码：</div></td>
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
													}}
												 %>
												
											</select>
											<span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr bgcolor="F9F9F9" class="selected_font">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />铁塔缴费信息</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr class="selected_font">
									<td bgcolor="#DDDDDD" width="15%"><div align="left">批次：</div></td>
										<td width="25%">
											<input type="text" name="t_yearmonth" value="<%=_yearmonth==null?"":_yearmonth %>" class="selected_font" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" onchange="pageRefsh();" /><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">单号：</div></td>
										<td width="25%">
											<input type="text" name="t_dh" readonly="readonly" value="<%=wydfftBean==null?"":wydfftBean.getDH() %>" class="selected_font" />
										</td>
										</tr>
										<tr class="selected_font">
										
											<td bgcolor="#DDDDDD" width="15%"><div align="left">上次抄表时间：</div></td>
										<td width="25%">
											<input type="text" name="t_sccbsj"  class="selected_font" value="<%=scsj%>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" onchange="DateUpdate()"/><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">本次抄表时间：</div></td>
										<td width="25%">
											<input type="text" name="t_bccbsj"  class="selected_font" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"/ onchange="DateUpdate()"><span class="style1">&nbsp;*</span>
										</td>
									<td bgcolor="#DDDDDD" width="15%"><div align="left">账期：</div>
										</td>
										<td width="25%"><input type="text" name="t_jszq"   class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>
									</tr>
									<tr>
									<td bgcolor="#DDDDDD" width="15%"><div align="left">上次抄表止码：</div></td>
										<td width="25%">
											<input type="text" name="t_sccbzm" class="selected_font" value="<%=scds%>"  onchange="DbdsUpdate()"/><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">本次抄表止码：</div></td>
										<td width="25%">
											<input type="text" name="t_bccbzm"  class="selected_font" onchange="DbdsUpdate()" /><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">耗损：</div></td>
										<td width="25%">
											<input type="text" name="t_sunhao" class="selected_font"  /><span class="style1">&nbsp;*</span>
										</td>
									
									</tr>
									<tr>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">电量：</div></td>
										<td width="25%">
											<input type="text" name="t_dliang"  class="selected_font" onchange="DjupDate()"/><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">电费单价(元/度)：</div></td>
										<td width="25%">
											<input type="text" name="t_dj"  class="selected_font" onchange="DjupDate()" /><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费金额：</div></td>
										<td width="25%">
											<input type="text"  name="t_jfje" readonly="readonly" value="<%=wydfftBean==null?"":wydfftBean.getJFJE() %>"  class="selected_font"/>
										</td>
										
									</tr>
									<tr>
									<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费日期：</div></td>
										<td width="25%">
											<input type="text" name="t_jfrq" value="" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="selected_font" /><span class="style1">&nbsp;*</span>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费票据类型：</div></td>
										<td width="25%">
											<input type="text"  name="t_jfpjlx" value="<%=wydfftBean==null?"":wydfftBean.getJFPJLX() %>" class="selected_font"/>
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">供电方/业主名称：</div></td>
										<td width="25%">
											<input type="text"  name="t_gdfmc" value="<%=wydfftBean==null?"":wydfftBean.getGDFMC() %>" class="selected_font"/>
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
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">分摊比例(%)：</div></td>
										<td width="25%">
											<input type="text"  name="t_ftbl" value="<%=wydfftBean==null?"":wydfftBean.getFTBL() %>" class="selected_font"/>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">税负因子(%)：</div></td>
										<td width="25%">
											<input type="text"  name="t_fsyz" value="<%=wydfftBean==null?"":wydfftBean.getFSYZ() %>" class="selected_font"/>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">分摊金额：</div></td>
										<td width="25%">
											<input type="text"  name="t_ftje" value="<%=wydfftBean==null?"":wydfftBean.getFTJE() %>" class="selected_font" onchange="pageRefsh();"/>
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


