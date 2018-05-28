<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@page import="com.noki.mobi.cx.SiteBeanchmark;"%>

<%
	String beginTime = request.getParameter("beginTime");
	String endTime1 = request.getParameter("endTime");
	String name1=request.getParameter("NAME");
	
	//System.out.println("logManage.jsp>>"+beginTime);
	String title = request.getParameter("title") != null ? request
			.getParameter("title") : "";
	String operName = request.getParameter("operName") != null ? request
			.getParameter("operName")
			: "";
	int intnum = 0;
	String color = "";
	System.out.println("operName=" + operName);
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();

	String sheng = (String) session.getAttribute("accountSheng");
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : "0";
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";
	String xiaoqu = request.getParameter("xiaoqu") != null ? request
			.getParameter("xiaoqu") : "0";
	String sptype = request.getParameter("jzproperty") != null ? request
			.getParameter("jzproperty")
			: "0";
	String jzxlx1 = request.getParameter("jzxlx") != null ? request
			.getParameter("jzxlx") : "0";
	String jflx1 = request.getParameter("jflx") != null ? request
			.getParameter("jflx") : "0";
	String jrwtype1 = request.getParameter("jrwtype") != null ? request
			.getParameter("jrwtype") : "0";
	String chanquan = request.getParameter("chanquan");
	String zdname = request.getParameter("zdname") != null ? request
			.getParameter("zdname") : "";

	String dbyt = request.getParameter("dbyt") != null ? request
			.getParameter("dbyt") : "0";
	String zdlx = request.getParameter("zdlx") != null ? request
			.getParameter("zdlx") : "0";
	String htmlsql = request.getParameter("htmlsql");
	String canshuStr = "";
	if ((shi != null) || (xian != null) || (zdlx != null)) {
		canshuStr = "&shi=" + shi + "&xian=" + xian + "&xiaoqu="
				+ xiaoqu + "&zdlx=" + zdlx;
	}
	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String permissionRights = "";
	String roleId = (String) session.getAttribute("accountRole");
%>

<html>
	<head>
		<title>logMange</title>
		<style>


.style1 {
	color: red;
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
.sel{
		width:130px;
		 
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:18px
		}
		.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}


#idss {
	font-family: 宋体;
	font-size: 12px;
}

.bttcn {
	color: BLACK;
	font-weight: bold;
}
</style>
		<style type="text/css" media=print>
.noprint {
	display: none
}
</style>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
		<script>

var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();

var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChsny.oBtnTodayTitle = "确定";
oCalendarChsny.oBtnCancelTitle = "取消";
oCalendarChsny.Init();
</script>
		<script language="javascript">
var path = '<%=path%>';
var beginTime='<%=beginTime%>'
function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = ""
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

	} else {
		trObject.style.display = "none"
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
	}
}

function queryDegree() {
	if (checkNotSelected(document.form1.shi,"城市")) {
          			if(checkNotnull(document.form1.beginTime,"起始月份")&&checkNotnull(document.form1.endTime,"结束月份")){
          			if(document.form1.endTime.value>=document.form1.beginTime.value){
		document.form1.action = path + "/web/cx/chaobiaolv.jsp";
		document.form1.submit();
}else{
  alert("结束月份不能小于起始月份");
}
	}
	}

}

  function endmonthzq(){
  document.form1.endTime.value=document.form1.beginTime.value
    	// var startmonth = document.form1.beginTime.value;
      //   var month=startmonth.substring(5,7);
      //   var year=startmonth.substring(0,4)-0;
      //   //alert(year+"-"+month);
      //   if(month>12){
      //  	 year=(year+1);
      //  	 month=month-12;
      //   }
      //   if(month<10){
      //  	 month="0"+month;
      //   }
      //   var s;
         //alert(year+"-"+month);
          
       //  s=year+"-"+month;
       //  document.form1.endTime.value=s;
      //  }



}


function getValue(va, sql) {
	var general = document.getElementById("general");
	var htmlsql = document.getElementById("htmlsql");
	general.value = va;
	htmlsql.value = sql;
}
//页面载入方法
function op() {
	window
			.open(
					path + '/InquiryServlet?quiryTaeble=zhandian&tab=jz&flg=into',
					'newwindow',
					'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
}
$(function() {

	$("#query").click(function() {
		queryDegree();
	});

});
</script>
<script language="javascript">

    function modifyad(shi,zdlx,beginTime,endTime1,id,cbcs){
    	var b=path+"/web/cx/chaobiaolv1.jsp?shi="+shi+"&zdlx="+ zdlx + "&beginTime=" + beginTime + "&endTime1=" + endTime1+"&id="+id+"&cbcs="+cbcs;			
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
    } 
</script>

<script language="javascript">

    function modifyad3(shi,zdlx,id,dbzs,szdq){
    	var b=path+"/web/cx/chaobiaolv1.jsp?shi="+shi+"&zdlx="+zdlx+"&id="+id+"&dbzs="+dbzs+"&szdq="+szdq;			
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
    } 
</script>

<script language="javascript">
    function modifyad4(shi,zdlx,dbzszs,xian){
    	var b=path+"/web/cx/chaobiaolv1.jsp?shi="+shi+"&zdlx="+zdlx+"&dbzszs="+dbzszs+"&xian="+xian;			
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
    } 
</script>

<script language="javascript">

    function modifyad5(shi,zdlx,beginTime,endTime1,cbzszs,xian){
    	var b=path+"/web/cx/chaobiaolv1.jsp?shi="+shi+"&zdlx="+ zdlx + "&beginTime=" + beginTime + "&endTime1=" + endTime1+"&xian="+xian+"&cbzszs="+cbzszs;			
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
    } 
</script>

<script language="javascript">

    function modifyad8(shi,zdlx,beginTime,endTime1,id,yxcs){
    	var b=path+"/web/cx/chaobiaolv1.jsp?shi="+shi+"&zdlx="+ zdlx + "&beginTime=" + beginTime + "&endTime1=" + endTime1+"&id="+id+"&yxcs="+yxcs;			
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
    } 
</script>

<script language="javascript">

    function modifyad9(shi,zdlx,beginTime,endTime1,yxcszs,xian){
    	var b=path+"/web/cx/chaobiaolv1.jsp?shi="+shi+"&zdlx="+ zdlx + "&beginTime=" + beginTime + "&endTime1=" + endTime1+"&yxcszs="+yxcszs+"&xian="+xian;			
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
    } 
</script>
<script type="text/javascript">
//=点击展开关闭效果=

function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
var openTip = oOpenTip || "";
var shutTip = oShutTip || "";
if(targetObj.style.display!="none"){
   if(shutAble) return;
   targetObj.style.display="none";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = shutTip; 
   }
}else {
   targetObj.style.display="block";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = openTip; 
   }
}
}
</script>

	</head>

	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<%
		permissionRights = commBean.getPermissionRight(roleId, "0501");

		System.out.println(">>>>>>>>>>>>>>..." + permissionRights);
	%>
	<body  class="body" >
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			     
					    	<td colspan="4" width="50" >
											 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">抄表率统计</span>	
												</div>
											</td>

	    	        </tr>	    	
			<tr>
			<td height="20" colspan="4" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td>
	    	</tr>
	    	<tr>
	    	<td width="1200px">
	    	<table>
	    	<tr class="form_label">
	    	
	    	<td>城市：</td>
	    	<td><select name="shi" class="selected_font"
																			onchange="changeCity()">
																			<option value="0">
																				请选择
																			</option>
																			<%
																				ArrayList shilist = new ArrayList();
																				shilist = commBean.getAgcode(sheng, account.getAccountId());
																				if (shilist != null) {
																					String agcode = "", agname = "";
																					int scount = ((Integer) shilist.get(0)).intValue();
																					for (int i = scount; i < shilist.size() - 1; i += scount) {
																						agcode = (String) shilist
																								.get(i + shilist.indexOf("AGCODE"));
																						agname = (String) shilist
																								.get(i + shilist.indexOf("AGNAME"));
																			%>
																			<option value="<%=agcode%>"><%=agname%></option>
																			<%
																				}
																				}
																			%>
																		</select><span class="style1">&nbsp;*</span></td>
																		<td>起始月份：</td>
																		<td>
																		<input type="text" name="beginTime" class="selected_font" 
																			value="<%if (null != request.getParameter("beginTime"))
				out.print(request.getParameter("beginTime"));%>"
																			onFocus="getDatenyString(this,oCalendarChsny)" onpropertychange="endmonthzq()"/><span class="style1">&nbsp;*</span>
																		</td>
																		<td>结束月份：</td>
																		<td><input type="text" name="endTime" class="selected_font"
																			value="<%if (null != request.getParameter("endTime"))
				out.print(request.getParameter("endTime"));%>"
																			onFocus="getDatenyString(this,oCalendarChsny)"/><span class="style1">&nbsp;*</span></td>
																			<td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							</font>
					</p>
				</td>
	         		<td >
							       <div id="query" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
							</td>
																		</tr>
</table>
</td>
</tr>																		
	 <tr>
			     <td colspan="5">
					<div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table><tr class="form_label">
																	<td >区县：</td>
																	<td>	<select name="xian" class="selected_font"
																			onchange="changeCountry()">
																			<option value="0">
																				请选择
																			</option>
																			<%
																				ArrayList xianlist = new ArrayList();
																				xianlist = commBean.getAgcode(shi, account.getAccountId());
																				if (xianlist != null) {
																					String agcode = "", agname = "";
																					int scount = ((Integer) xianlist.get(0)).intValue();
																					for (int i = scount; i < xianlist.size() - 1; i += scount) {
																						agcode = (String) xianlist.get(i
																								+ xianlist.indexOf("AGCODE"));
																						agname = (String) xianlist.get(i
																								+ xianlist.indexOf("AGNAME"));
																			%>
																			<option value="<%=agcode%>"><%=agname%></option>
																			<%
																				}
																				}
																			%>
																		</select></td>
																		
																		
																		
																		
																		
																		<td>站点类型：</td>
																		<td>
																		<select name="zdlx" class="selected_font" >
																			<option value="0">
																				请选择
																			</option>
																			<%
																				ArrayList stationtype = new ArrayList();
																				stationtype = ztcommon.getSelctOptions("StationType");
																				if (stationtype != null) {
																					String code = "", name = "";
																					int cscount = ((Integer) stationtype.get(0)).intValue();
																					for (int i = cscount; i < stationtype.size() - 1; i += cscount) {
																						code = (String) stationtype.get(i
																								+ stationtype.indexOf("CODE"));
																						name = (String) stationtype.get(i
																								+ stationtype.indexOf("NAME"));
																			%>
																			<option value="<%=code%>"><%=name%></option>
																			<%
																				}
																				}
																			%>
																		</select>
																	</td>
																</tr>
																</table>
																</p>
																</div>
																</td>
																</tr>
																</table>
																
			        
			       
<table  height="23">
<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>

															<table width="100%" border="0" cellspacing="1"
																cellpadding="1" bgcolor="#cbd5de">
																<tr height="20">
																	<td id="idss" width="20%" height="23" bgcolor="#DDDDDD">
																		<div align="center" class="bttcn">
																		市区县
																		</div>
																	</td>
																	<td id="idss" width="20%" height="23" bgcolor="#DDDDDD">
																		<div align="center" class="bttcn">
																			系统在用电表数
																		</div>
																	</td>
																	<td id="idss" width="20%" height="23" bgcolor="#DDDDDD">
																		<div align="center" class="bttcn">
																			抄表次数
																		</div>
																	</td>
																	<td id="idss" width="20%" height="23" bgcolor="#DDDDDD">
																		<div align="center" class="bttcn">
																			有效次数
																		</div>
																	</td>
																	<td id="idss" width="20%" height="23" bgcolor="#DDDDDD">
																		<div align="center" class="bttcn">
																			电表抄表率
																		</div>
																	</td>
																</tr>
																<%
																	DecimalFormat mat = new DecimalFormat("0.0");
																	DecimalFormat mat1 = new DecimalFormat("0.00");
																	String str = "";
																	String ydl = "";
																	SiteBeanchmark bean = new SiteBeanchmark();
                                                                        
																	if (!str.equals("")) {

																		System.out.println("str" + str);
																		bean = bean.getInfor(str);
																		String countsite = bean.getcount(str);
																		ydl = bean.getHdl();

																		if (ydl.equals(""))
																			ydl = "0";
																		System.out.println("ydl" + ydl + "countsite" + countsite);
																		ydl = mat.format(Double.parseDouble(ydl)
																				/ Double.parseDouble(countsite));
																		String zdsx = "";
																%>


																<%
																	}
																	String whereStr = "";
																	System.out.println("dianliangListjsp--------------:" + sptype
																			+ chanquan);
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		whereStr = whereStr + " AND Z.SHI='" + shi + "'";
																	}
																	if (xian != null && !xian.equals("") && !xian.equals("0")) {
																		whereStr = whereStr + " AND Z.XIAN='" + xian + "'";
																	}
																	String zdd = "";
																	if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
																		zdd = zdd + " AND Z.STATIONTYPE='" + zdlx + "'";
																	}
																	String bett = "";
																	if (beginTime != null && !beginTime.equals("")
																			&& !beginTime.equals("0")) {
																		bett = bett + " AND TO_CHAR(AA.STARTMONTH,'yyyy-mm') >='" + beginTime + "'";
																	}
																	if (endTime1 != null && !endTime1.equals("") && !endTime1.equals("0")) {
																		bett = bett + " AND TO_CHAR(AA.ENDMONTH,'yyyy-mm')<='" + endTime1 + "'";
																	}

																	ArrayList fylist = new ArrayList();
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																	System.out.println("zdd:"+zdd);
																		fylist = bean.getlistchaobiao(whereStr,zdd,bett,loginId);
																		String id = "";
																		String shengl = "";
																		String shil = "";
																		String xianl = "";

																		String jztype = "", property = "", yflx = "", jnglmk = "", gdfs = "", df = "", dl = "", dldb = "", dfdb = "";
																		//==所在地区szdq==在用电表总数dbzs===抄表次数cbcs==有效次数yxcs==电表抄表率dblv==
																		String szdq = "";
																		String dbzs = "";
																		String cbcs = "";
																		String yxcs = "";
																		String dblv = "";
																		String xx="";
																		int cbcszs = 0;
																		int dbzszs = 0;
																		int yxcszs = 0;
																		int n = 0;
																		int n1 = 0;
																		int n2 = 0;
																		double dd = 0.00;
																		double ddzs = 0.00;
																		if (fylist != null) {
																			int fycount = ((Integer) fylist.get(0)).intValue();
																			for (int k = fycount; k < fylist.size() - 1; k += fycount) {

																				szdq = (String) fylist.get(k + fylist.indexOf("NAME"));
																				szdq = szdq != null ? szdq : "";
                                                                                
                                                                                
                                                                                id=(String)fylist.get(k+fylist.indexOf("ID"));
                                                                                id=id!=null?id:"";
                                                                                
                                                                                
                                                                                
																				dbzs = (String) fylist.get(k + fylist.indexOf("DBID"));
																				dbzs = dbzs != null ? dbzs : "0";
																				if ("".equals(dbzs) || "null".equals(dbzs)) {
																					dbzs = "0";
																				}

																				cbcs = (String) fylist.get(k + fylist.indexOf("DLID"));
																				cbcs = cbcs != null ? cbcs : "";
																				if ("".equals(cbcs) || cbcs == null
																						|| "null".equals(cbcs)) {
																					cbcs = "0";
																				}
																				n = Integer.parseInt(cbcs);

																				yxcs = (String) fylist.get(k + fylist.indexOf("YXCS"));
																				yxcs = yxcs != null ? yxcs : "0";
																				if ("".equals(yxcs)) {
																					yxcs = "0";
																				}

																				n1 = Integer.parseInt(dbzs);
																				dbzszs = n1 + dbzszs;

																				n2 = Integer.parseInt(yxcs);
																				yxcszs = n2 + yxcszs;
																				if (n1 != 0) {
																					dd = (double) n2 / n1 * 100;

																					DecimalFormat dfi = new DecimalFormat("0.00");
																					dd = Double.parseDouble(dfi.format(dd));
																					ddzs = dd + ddzs;
																				}
																				if (n1 == 0) {
																					dd = 0.00 * 100;
																				}

																				String color1 = null;

																				if (intnum % 2 == 0) {
																					color1 = "#FFFFFF";

																				} else {
																					color1 = "#DDDDDD";
																				}
																				intnum++;
																				cbcszs = cbcszs + n;
																				//dl=mat.format(Double.parseDouble(dl));
																				// dldb=mat1.format((Double.parseDouble(dl)-Double.parseDouble(ydl))/Double.parseDouble(ydl)*100);
																%>
																<tr bgcolor=<%=color1%>>
																	<td id="idss">
																	
																		<div align="center">
																	<%=szdq%></div>
																		<input type="hidden" class="szdq" value="<%=szdq %>"/>
																	</td>
																	<td id="idss">
																		<div align="right">
																		<a href="javascript:modifyad3('<%=shi%>','<%=zdlx%>','<%=id%>','<%=dbzs%>','<%=szdq%>')"><%=dbzs%></a></div>
																	</td>
																	<td id="idss">
																		<div align="right">
																			<a
																				href="javascript:modifyad('<%=shi%>','<%=zdlx%>','<%=beginTime%>','<%=endTime1%>','<%=id%>','<%=cbcs%>')"><%=cbcs%></a>
																		</div>
																		
																	</td>

																	<td id="idss">
																		<div align="right">
																		<a href="javascript:modifyad8('<%=shi%>','<%=zdlx%>','<%=beginTime%>','<%=endTime1%>','<%=id%>','<%=yxcs%>')"><%=yxcs%></a></div>
																	</td>
																	<td id="idss">
																		<div align="right">
																		<%=dd%>%
																		 </div>
																	</td>
                                                                   
																</tr>

																<%
																	}
																%>
																<%
																	}
																%>
																<tr bgcolor="#DDDDDD">
																	<td id="idss" align="center">
																		合计
																	</td>
																	<td id="idss" align="right"><a href="javascript:modifyad4('<%=shi%>','<%=zdlx%>','<%=dbzszs%>','<%=xian%>')"><%=dbzszs%></a></td>
																	<td id="idss" align="right"><a href="javascript:modifyad5('<%=shi%>','<%=zdlx%>','<%=beginTime%>','<%=endTime1%>','<%=cbcszs%>','<%=xian%>')"><%=cbcszs%></a></td>
																	<td id="idss" align="right"><a href="javascript:modifyad9('<%=shi%>','<%=zdlx%>','<%=beginTime%>','<%=endTime1%>','<%=yxcszs%>','<%=xian%>')"><%=yxcszs%></a></td>
																	<td id="idss" align="right"></td>
																</tr>

																<%
																	}
																%>

															</table><br/>
															<input type="hidden" name="sheng2" id="sheng2" value="" />
									<input type="hidden" name="shi2" id="shi2" value="" />
									<input type="hidden" name="xian2" id="xian2" value="" />
									<input type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
									<input type="hidden" name="sptype2" id="sptype2" value="" />
									<input type="hidden" name="chanquan2" id="chanquan2" value="" />
									<input type="hidden" name="fwlx2" id="fwlx2" value="" />

									<input type="hidden" value="" id="htmlsql" name="htmlsql" />

<table width="100%">
<iframe  name="treeMap" width="100%"s
			 frameborder="0"
				src="<%=path%>/web/cx/chaobiaolv1.jsp"></iframe>

	</table>
		</form>
		
			
	
	</body>

	<script type="text/javascript">
var path = '<%=path%>';

document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.zdlx.value = '<%=zdlx%>';
</script>

</html>
<script type="text/javascript">

<!--
var XMLHttpReq;
	//XMLHttpReq = createXMLHttpRequest();
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	function sendRequest(url,para) {

		createXMLHttpRequest();
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="sheng"){
			XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
	}
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		//alert(XMLHttpReq.status);
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
            	var res = XMLHttpReq.responseText;
              window.alert(res);
             
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    
function processResponse_sheng() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            	
            	//var res = XMLHttpReq.responseText;
            
              updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateQx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZd(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}

function changeSheng(){
	var sid = document.form1.sheng.value;
	document.form1.sheng2.value=document.form1.sheng.value;
	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeCity(){
	var sid = document.form1.shi.value;
	document.form1.shi2.value=document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function changeCountry(){
	var sid = document.form1.xian.value;
	document.form1.xian2.value=document.form1.xian.value;
	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
	
//-->
</script>

