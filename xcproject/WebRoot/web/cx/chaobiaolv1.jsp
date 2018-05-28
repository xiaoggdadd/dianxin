<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@page import="com.noki.mobi.cx.SiteBeanchmark;"%>

<%
	String beginTime = request.getParameter("beginTime");
	String endTime = request.getParameter("endTime1");
	String szdq=request.getParameter("szdq");
    String cbcs=request.getParameter("cbcs");
    String yxcs=request.getParameter("yxcs");
String id=request.getParameter("id");
String dbzs=request.getParameter("dbzs");
 String dbzszs=request.getParameter("dbzszs");
	String cbzszs=request.getParameter("cbzszs");
	String yxcszs=request.getParameter("yxcszs");
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
			.getParameter("xian") : "";
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
	String s_curpage = request.getParameter("curpage") != null ? request.getParameter("curpage") : "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String permissionRights = "";
	String roleId = (String) session.getAttribute("accountRole");
	System.out.println("=====所在地区====="+szdq);
		System.out.println("=====所在地区====="+endTime);
		System.out.println("=====市====="+shi);
		System.out.println("=====县====="+xian);
		System.out.println("=========xx========所在的市区");
%>

<html>
	<head>
		<title>logMange</title>
		<style>
.btn {
	BORDER-RIGHT: #7b9ebd 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7b9ebd 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :       DXImageTransform.Microsoft.Gradient (     
		 GradientType =       0, StartColorStr =       #ffffff, EndColorStr =
		    
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
	FILTER: progid :       DXImageTransform.Microsoft.Gradient (     
		 GradientType =       0, StartColorStr =       #ffffff, EndColorStr =
		    
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
	FILTER: progid :       DXImageTransform.Microsoft.Gradient (     
		 GradientType =       0, StartColorStr =       #ffffff, EndColorStr =
		    
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
	FILTER: progid :       DXImageTransform.Microsoft.Gradient (     
		 GradientType =       0, StartColorStr =       #ffffff, EndColorStr =
		    
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
	FILTER: progid :       DXImageTransform.Microsoft.Gradient (     
		 GradientType =       0, StartColorStr =       #ffffff, EndColorStr =
		    
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
	FILTER: progid :       DXImageTransform.Microsoft.Gradient (     
		 GradientType =       0, StartColorStr =       #ffffff, EndColorStr =
		    
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
	FILTER: progid :       DXImageTransform.Microsoft.Gradient (     
		 GradientType =       0, StartColorStr =       #ffffff, EndColorStr =
		    
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
	FILTER: progid :       DXImageTransform.Microsoft.Gradient (     
		 GradientType =       0, StartColorStr =       #FFFFFF, EndColorStr =
		    
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

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}

.trHead {
z-index: 10;
position: relative;
top: expression(this.offsetParent.scrollTop);

}


.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}

#id1 {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :       DXImageTransform.Microsoft.Gradient (     
		 GradientType =       0, StartColorStr =       #ffffff, EndColorStr =
		    
		 #D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

#idss {
	font-family: 宋体;
	font-size: 12px;
}

.bttcn {
	color: white;
	font-weight: bold;
}
.form_label{
          
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px

		}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.bttcn{color:BLACK;font-weight:bold;}
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

	if (document.getElementById("shi").value == ""
			|| document.getElementById("shi").value == "0"
			|| document.getElementById("shi").value == null) {
		alert("城市不能为空");

	} else {
		document.form1.action = path + "/web/cx/chaobiaolv1.jsp";
		document.form1.submit();
	}

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
</script>
		<script language="javascript">
var path = '<%=path%>';
function modifyad(shi, xian, zdlx, beginTime, endTime) {
	var b = path + "/web/cx/chaobiaolv1.jsp?shi=" + shi + "&xian=" + xian
			+ "&zdlx=" + zdlx + "&beginTime=" + beginTime + "&endTime="
			+ endTime;
	var a = " <a href=" + b + " target='treeMap1' id='tmpTree'></a>";
	// alert(a);
	$("#tmpTree").remove();
	$("body").append(a);
	$("#tmpTree")[0].click();
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
	
		
			<% 
			if(cbcs!=null&&!cbcs.equals("")){ 
			%>
		 <div>
			<table width="99%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
				<tr height="20"  class="trHead">
				<td id="idss" width="8%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							序号
						</div>
					</td>
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							站点名称
						</div>
					</td>
					
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							上次电表读数
						</div>
					</td>
					<td id="idss" width="8%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							本次读表数
						</div>
					</td>
					<td id="idss" width="8%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							实际用电量
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							上次读表时间
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							本次抄表时间
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							起始月份
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						        结束月份
						</div>
					</td>
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							电表ID
						</div>
					</td>
				</tr>
				<%	
																	SiteBeanchmark bean = new SiteBeanchmark();
																	String whereStr = "";
																	System.out.println("dianliangListjsp--------------:" + sptype
																			+ chanquan);
																			String kkt="";
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		kkt = kkt + " AND Z.SHI='" + shi + "'";
																	}
																	
																	String zdd = "";
																	if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
																		zdd = zdd + " AND Z.STATIONTYPE='" + zdlx + "'";
																	}
																	String bett = "";
																	if (beginTime != null && !beginTime.equals("")
																			&& !beginTime.equals("0")) {
																		bett = bett + " AND TO_CHAR(A.STARTMONTH,'yyyy-mm') >='" + beginTime + "'";
																	}
																	if (endTime != null && !endTime.equals("") && !endTime.equals("0")) {
																		bett = bett + " AND TO_CHAR(A.ENDMONTH,'yyyy-mm')<='" + endTime + "'";
																	}
																	if(szdq!=null && !szdq.equals("")){
																	   szdq = szdq + " AND ZD.XIAN='" + szdq + "'";
																	}
																	String idd="";
																	if(id!=null&&!id.equals("")){
																	   idd=idd+" AND Z.XIAN='"+id+"'";
																	}
                                                                       System.out.println("==============="+shi+"1"+xian+"2"+zdlx+"3"+beginTime+"4"+endTime+"5");
																	ArrayList fylist = new ArrayList();
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		fylist = bean.getlistchaobiao1(kkt, zdd, bett, loginId,idd);
															
																		String shengl = "";
																		String shil = "";
																		String xianl = "";

																		String jztype = "", property = "", yflx = "", jnglmk = "", gdfs = "", df = "", dl = "", dldb = "", dfdb = "";
																		//==所在地区szdq==在用电表总数dbzs===抄表次数cbcs==有效次数yxcs==电表抄表率dblv==
																		String sszdq="";
																	
																	
																	
																		//=============
																		String zdmc="";//站点名称
																		String dbid="";//电表ID
																		String scdbs="";//上次读表数
																		String bcdbs="";//本次读表数
																		String scdbsj="";//上次读表时间
																		String bcdbsj="";//本次读表时间
																		String zsdl="";//折算后电量
																		String ac="";
																		String startmonth="",endmonth="";
																		 
																		 double d=0;
																		//=============
																		if (fylist != null) {
																			int fycount = ((Integer) fylist.get(0)).intValue();
																			for (int k = fycount; k < fylist.size() - 1; k += fycount) {
                                           
                                                                                 	startmonth = (String) fylist.get(k + fylist.indexOf("STARTMONTH"));
																				startmonth = startmonth != null ? startmonth : "";
																				if("null".equals(startmonth)){
																				 startmonth="";
																				}
																				
																				endmonth = (String) fylist.get(k + fylist.indexOf("ENDMONTH"));
																				endmonth = endmonth != null ? endmonth : "";
																				if("null".equals(endmonth)){
																				 endmonth="";
																				}
                                                                                   
                                           
                                           
																				zdmc = (String) fylist.get(k + fylist.indexOf("JZNAME"));
																				zdmc = zdmc != null ? zdmc : "";
																				
																				ac = (String) fylist.get(k + fylist.indexOf("BLHDL"));
																				ac = ac != null ? ac : "0";
																				if(ac.equals("null")){
																				ac="0";
																				}
																				

																				dbid = (String) fylist.get(k + fylist.indexOf("DBID"));
																				dbid = dbid != null ? dbid : "0";
																				
																				scdbs = (String) fylist.get(k + fylist.indexOf("LASTDEGREE"));
																				scdbs = scdbs != null ? scdbs : "0";
																				if("null".equals(scdbs)||"".equals(scdbs)){
																				scdbs="0";
																				}
																				 DecimalFormat countdl=new DecimalFormat("0.0");
		                                                                                 scdbs=countdl.format(Double.parseDouble(scdbs));
																			             ac=countdl.format(Double.parseDouble(ac));

																				bcdbs = (String) fylist.get(k + fylist.indexOf("THISDEGREE"));
																				bcdbs = bcdbs != null ? bcdbs : "0";
																				if("null".equals(bcdbs)||"".equals(bcdbs)){
																				bcdbs="0";
																				}
																				 DecimalFormat countd2=new DecimalFormat("0.0");
		                                                                                 bcdbs=countd2.format(Double.parseDouble(bcdbs));
																			 
																			 bcdbsj=(String)fylist.get(k + fylist.indexOf("THISDATETIME"));
																			 bcdbsj = bcdbsj != null ? bcdbsj : "";


                                                                             scdbsj=(String)fylist.get(k + fylist.indexOf("LASTDATETIME"));
																			 scdbsj = scdbsj != null ? scdbsj : "";
																			 
																			 
																			 zsdl=(String)fylist.get(k + fylist.indexOf("ACTUALDEGREE"));
																			 zsdl = zsdl != null ? zsdl : "";
																			 
																				String color1 = null;

																				if (intnum % 2 == 0) {
																					color1 = "#FFFFFF";

																				} else {
																					color1 = "#DDDDDD";
																				}
																				intnum++;
																				
																%>
				<tr bgcolor=<%=color1%>>
				    
				    <td id="idss">
						<div align="center"><%=intnum%></div>
					</td>
					<td id="idss">
						<div align="left"><%=zdmc%></div>
					</td>
					
					<td id="idss">
						<div align="right"><%=scdbs%></div>
					</td>

					<td id="idss">
						<div align="right"><%=bcdbs%></div>
					</td>
					<td id="idss">
						<div align="right"><%=ac%></div>
					</td>
					<td id="idss">
						<div align="center"><%=scdbsj%>
						</div>
					</td>
					<td id="idss">
						<div align="center"><%=bcdbsj%>
						</div>
					</td>
				<td id="idss">
						<div align="center"><%=startmonth%>
						</div>
					</td>
					<td id="idss">
						<div align="center"><%=endmonth%>
						</div>
					</td>
					<td id="idss">
						<div align="left"><%=dbid%></div>
					</td>
				</tr>

				<%
																	}
																%>
				<%
																	}
																%>
				<%
																	}
																%>

			</table>
			</div>
			<% 
			}
			%>
		
			<%if(dbzs!=null&&!dbzs.equals("")){%>

  
			<table width="99%"  border="0" cellspacing="1" bgcolor="#cbd5de"  class="form_label">
				<tr height="20"  class="trHead">
				<td id="idss" width="3%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							序号
						</div> 
						
					</td>
					
					
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							电表名称
						</div>
					</td>
					
				
					
					
					<td id="idss" width="17%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							站点名称
						</div>
					</td>
					<td id="idss" width="7%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							地区
						</div>
					</td><td id="idss" width="7%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							站点类型
						</div>
					</td><td id="idss" width="4%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							属性
						</div>
					</td>
					
					<td id="idss" width="7%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							集团报表类型
						</div>
				</td>
					<td id="idss" width="3%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							倍率
						</div>
					</td>
					<td id="idss" width="4%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							初始读数
						</div>
					</td>
					<td id="idss" width="7%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							初始使用时间
						</div>
					</td>
					<td id="idss" width="7%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						电表ID
						</div>
					</td>
				</tr>
				


				<%
																	
																	String whereStr = "";
																	SiteBeanchmark bean = new SiteBeanchmark();
																	
																			String kkt="";
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		kkt = kkt + " AND Z.SHI='" + shi + "'";
																	}
																	
																	String zdd = "";
																	if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
																		zdd = zdd + " AND Z.STATIONTYPE='" + zdlx + "'";
																	}
																	String bett = "";
																	if (beginTime != null && !beginTime.equals("")
																			&& !beginTime.equals("0")) {
																		bett = bett + " AND TO_CHAR(A.STARTMONTH,'yyyy-mm') >='" + beginTime + "'";
																	}
																	if (endTime != null && !endTime.equals("") && !endTime.equals("0")) {
																		bett = bett + " AND TO_CHAR(A.ENDMONTH,'yyyy-mm')<='" + endTime + "'";
																	}
																	String idd="";
																	if(id!=null&&!id.equals("")){
																	   idd=idd+" AND Z.XIAN='"+id+"'";
																	}
                                                                       System.out.println("==============="+shi+"1"+xian+"2"+zdlx+"3"+beginTime+"4"+endTime+"5");
																	ArrayList fylist = new ArrayList();
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		fylist = bean.getlistchaobiao2(kkt,loginId,idd,zdd);
															
																		String shengl = "";
																		String shil = "";
																		String xianl = "";

																		String jztype = "", property = "", yflx = "", jnglmk = "", gdfs = "", df = "", dl = "", dldb = "", dfdb = "";
																		//==所在地区szdq==在用电表总数dbzs===抄表次数cbcs==有效次数yxcs==电表抄表率dblv==
																		String sszdq="";
																		
																	
																	
																		//=============
																		String zdmc="";//站点名称
																		String dbid="";//电表ID
																		String dbmc="";//电表名称
																		String csdss="";//初始读数
																		String cssysj="";//初始使用时间
																		String stationtype="";
																		String property3="";
																		String jztype4="";
																		String bl="";
																	   String szdqq="";
																		double d=0;
																		//=============
																		if (fylist != null) {
																			int fycount = ((Integer) fylist.get(0)).intValue();
																			for (int k = fycount; k < fylist.size() - 1; k += fycount) {

																				zdmc = (String) fylist.get(k + fylist.indexOf("JZNAME"));
																				zdmc = zdmc != null ? zdmc : "";
																				
																				 if("null".equals(zdmc)){
																			      zdmc="";
																			     }
																			     
																			     szdqq = (String) fylist.get(k + fylist.indexOf("SZDQQ"));
																				szdqq = szdqq != null ? szdqq : "";
																				
																				 if("null".equals(szdqq)){
																			      szdqq="";
																			     }
																			     
																			     
																			     
																			     
																			     jztype4 = (String) fylist.get(k + fylist.indexOf("JZTYPE"));
																				jztype4 = jztype4 != null ? jztype4 : "";
																				
																				 if("null".equals(jztype4)){
																			      jztype4="";
																			     }
																			     
																			       bl = (String) fylist.get(k + fylist.indexOf("BEILV"));
																				bl = bl != null ? bl : "0";
																				
																				 if("null".equals(bl)||"".equals(bl)){
																			      bl="0";
																			     }
																			     
																			      DecimalFormat bll=new DecimalFormat("0.00");
		                                                                                 bl=bll.format(Double.parseDouble(bl));
																			     
																			     
																			     
																			     
																			     property3 = (String) fylist.get(k + fylist.indexOf("PROPERTY"));
																				property3 = property3 != null ? property3 : "";
																				
																				 if("null".equals(property3)){
																			      property3="";
																			     }
																			     
																			     
																			     
                                                                               stationtype=(String)fylist.get(k + fylist.indexOf("STATIONTYPE"));
                                                                               stationtype=stationtype!=null?stationtype : "";
                                                                               if(stationtype.equals("null")){
                                                                                 stationtype="";
                                                                               }
                          
																				dbid = (String) fylist.get(k + fylist.indexOf("DBID"));
																				dbid = dbid != null ? dbid : "";
																				
																				dbmc=(String)fylist.get(k+fylist.indexOf("DBNAME"));
																				dbmc=dbmc !=null ? dbmc : "";
																			     if("null".equals(dbmc)){
																			      dbmc="";
																			     }
																			   
																			   
																			    csdss=(String)fylist.get(k+fylist.indexOf("CSDS"));
																			    csdss=csdss !=null ? csdss : "0";
																			   if("null".equals(csdss)||"".equals(csdss)){
																			      csdss="0";
																			   }
                                                                                   DecimalFormat countdl=new DecimalFormat("0.0");
		                                                                                 csdss=countdl.format(Double.parseDouble(csdss));
                                                                                 cssysj=(String)fylist.get(k+fylist.indexOf("CSSYTIME"));
                                                                                 cssysj=cssysj !=null ? cssysj : "";
														                      if("null".equals(cssysj)){
														                        cssysj="";
														                      }
																			 
																				String color1 = null;

																				if (intnum % 2 == 0) {
																					color1 = "#FFFFFF";

																				} else {
																					color1 = "#DDDDDD";
																				}
																				intnum++;
																				
																%>
				<tr bgcolor=<%=color1%>>
				<td id="idss">
						<div align="center"><%=intnum%></div>
					</td>
					
					<td id="idss">
						<div align="left"><%=dbmc%></div>
					</td>
					
					
					
					<td id="idss">
						<div align="left"><%=zdmc%>
						</div>
					</td>
					<td id="idss">
						<div align="left"><%=szdqq%></div>
					</td>
					<td id="idss">
						<div align="center"><%=stationtype%></div>
					</td>
					<td id="idss">
						<div align="center"><%=property3%></div>
					</td>
					<td id="idss">
						<div align="center"><%=jztype4%></div>
					</td>
					<td id="idss">
						<div align="right"><%=bl%></div>
					</td>
					<td id="idss">
						<div align="right"><%=csdss%>
						</div>
					</td>
				<td id="idss">
						<div align="center"><%=cssysj%>
						</div>
					</td>
					<td id="idss">
						<div align="left"><%=dbid%></div>
					</td>
				</tr>

				<%
																	}
																%>
				<%
																	}
																%>
				<%
																	}
																%>
			</table>
		
		 
			<% 
			}
			%>
			
			
			<%if(dbzszs!=null && !dbzszs.equals("")){ %>
			<table width="99%" border="0" cellspacing="1" bgcolor="#cbd5de" cellpadding="1" class="form_label">
				<tr height="20"  class="trHead">
				<td id="idss" width="3%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							序号
						</div> 
						
					</td>
					
					
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							电表名称
						</div>
					</td>
					
					<td id="idss" width="17%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							站点名称
						</div>
					</td>
					<td id="idss" width="7%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							地区
						</div>
					</td><td id="idss" width="7%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							站点类型
						</div>
					</td><td id="idss" width="4%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							属性
						</div>
					</td>
					
					<td id="idss" width="7%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							集团报表类型
						</div>
				</td>
					<td id="idss" width="3%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn" >
							倍率
						</div>
					</td>
					<td id="idss" width="4%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							初始读数
						</div>
					</td>
					<td id="idss" width="7%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							初始使用时间
						</div>
					</td>
					<td id="idss" width="7%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						电表ID
						</div>
					</td>
				</tr>
			
																	
																	

																	
															


				<%
																	
																	SiteBeanchmark bean = new SiteBeanchmark();
																	String whereStr = "";
																	System.out.println("dianliangListjsp--------------:" + sptype
																			+ chanquan);
																			String kkt="";
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		kkt = kkt + " AND Z.SHI='" + shi + "'";
																	}
																	
																	String zdd = "";
																	if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
																		zdd = zdd + " AND Z.STATIONTYPE='" + zdlx + "'";
																	}
																	String bett = "";
																	if (beginTime != null && !beginTime.equals("")
																			&& !beginTime.equals("0")) {
																		bett = bett + " AND TO_CHAR(A.STARTMONTH,'yyyy-mm') >='" + beginTime + "'";
																	}
																	if (endTime != null && !endTime.equals("") && !endTime.equals("0")) {
																		bett = bett + " AND TO_CHAR(A.ENDMONTH,'yyyy-mm')<='" + endTime + "'";
																	}
																	String ist="";
																	if(xian!=null&&!xian.equals("")&&!xian.equals("0")){
																	   ist=ist+" AND Z.XIAN='"+xian+"'";
																	}
																	
																	
																	  
																	
																	
																	
																	
                                                                       System.out.println("==============="+shi+"1"+xian+"2"+zdlx+"3"+beginTime+"4"+endTime+"5");
																	ArrayList fylist = new ArrayList();
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		fylist = bean.getlistchaobiao4(kkt,zdd,loginId,ist);
															
																		String shengl = "";
																		String shil = "";
																		String xianl = "";

																		String jztype = "", property = "", yflx = "", jnglmk = "", gdfs = "", df = "", dl = "", dldb = "", dfdb = "";
																		//==所在地区szdq==在用电表总数dbzs===抄表次数cbcs==有效次数yxcs==电表抄表率dblv==
																		String sszdq="";
																		
																	
																	
																		//=============
																		String zdmc="";//站点名称
																		String dbid="";//电表ID
																		String dbmc="";//电表名称
																		String csdss="";//初始读数
																		String cssysj="";//初始使用时间
																		String bl="";
																		String property3="",stationtype="",szdqq="",jztype4="";
																		//=============
																		if (fylist != null) {
																			int fycount = ((Integer) fylist.get(0)).intValue();
																			for (int k = fycount; k < fylist.size() - 1; k += fycount) {

																				zdmc = (String) fylist.get(k + fylist.indexOf("JZNAME"));
																				zdmc = zdmc != null ? zdmc : "";
																				
																				 if("null".equals(zdmc)){
																			      zdmc="";
																			     }
                                                                                  bl = (String) fylist.get(k + fylist.indexOf("BEILV"));
																				bl = bl != null ? bl : "0";
																				
																				 if("null".equals(bl)||"".equals(bl)){
																			      bl="0";
																			     }
																			     
																			      DecimalFormat bll=new DecimalFormat("0.00");
		                                                                                 bl=bll.format(Double.parseDouble(bl));
																	
                                                                                    szdqq = (String) fylist.get(k + fylist.indexOf("SZDQQ"));
																				szdqq = szdqq != null ? szdqq : "";
																				
																				 if("null".equals(szdqq)){
																			      szdqq="";
																			     }
                                                                                 
                          
																				dbid = (String) fylist.get(k + fylist.indexOf("DBID"));
																				dbid = dbid != null ? dbid : "";
																				
																				dbmc=(String)fylist.get(k+fylist.indexOf("DBNAME"));
																				dbmc=dbmc !=null ? dbmc : "";
																			     if("null".equals(dbmc)){
																			      dbmc="";
																			     }
																			       
																			     property3 = (String) fylist.get(k + fylist.indexOf("PROPERTY"));
																				property3 = property3 != null ? property3 : "";
																				
																				 if("null".equals(property3)){
																			      property3="";
																			     }
																			     
																			     jztype4 = (String) fylist.get(k + fylist.indexOf("JZTYPE"));
																				jztype4 = jztype4 != null ? jztype4 : "";
																				
																				 if("null".equals(jztype4)){
																			      jztype4="";
																			     }
																			     
                                                                               stationtype=(String)fylist.get(k + fylist.indexOf("STATIONTYPE"));
                                                                               stationtype=stationtype!=null?stationtype : "";
                                                                               if(stationtype.equals("null")){
                                                                                 stationtype="";
                                                                               }
																			   
																			    csdss=(String)fylist.get(k+fylist.indexOf("CSDS"));
																			    csdss=csdss !=null ? csdss : "0";
																			   if("null".equals(csdss)||"".equals(csdss)){
																			      csdss="0";
																			   }
                                                                                   DecimalFormat countdl=new DecimalFormat("0.0");
		                                                                                 csdss=countdl.format(Double.parseDouble(csdss));
                                                                                 cssysj=(String)fylist.get(k+fylist.indexOf("CSSYTIME"));
                                                                                 cssysj=cssysj !=null ? cssysj : "";
														                      if("null".equals(cssysj)){
														                        cssysj="";
														                      }
																			 
																				String color1 = null;

																				if (intnum % 2 == 0) {
																					color1 = "#FFFFFF";

																				} else {
																					color1 = "#DDDDDD";
																				}
																				intnum++;
					
					
				%>
					
					
					
				<tr bgcolor=<%=color1%>>
				<td id="idss">
						<div align="center"><%=intnum%></div>
					</td>
					
					<td id="idss">
						<div align="left"><%=dbmc%></div>
					</td>
					
					<td id="idss">
						<div align="left"><%=zdmc%>
						</div>
					</td>
					<td id="idss">
						<div align="left"><%=szdqq%></div>
					</td>
					<td id="idss">
						<div align="center"><%=stationtype%></div>
					</td>
					<td id="idss">
						<div align="center"><%=property3%></div>
					</td>
					<td id="idss">
						<div align="center"><%=jztype4%></div>
					</td>
					<td id="idss">
						<div align="right"><%=bl%></div>
					</td>
					<td id="idss">
						<div align="right"><%=csdss%>
						</div>
					</td>
				<td id="idss">
						<div align="center"><%=cssysj%>
						</div>
					</td><td id="idss">
						<div align="left"><%=dbid%></div>
					</td>
				</tr>

				<%
																	}
																%>
				<%
																	}
																%>
				

				<%
																	}
																%>

			</table>
			
			<% 
			}
			%>
			
			<%if(cbzszs!=null&&!cbzszs.equals("")){ %>
			<table width="99%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
				<tr height="20"  class="trHead">
				<td id="idss" width="8%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							序号
						</div>
					</td>
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							站点名称
						</div>
					</td>
					
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
					上次电表读数
						</div>
					</td>
					<td id="idss" width="8%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						本次读表数
						</div>
					</td>
					<td id="idss" width="8%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						实际用电量
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						上次读表时间
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							本次抄表时间
						</div>
					</td>
						<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						起始月份
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						结束月份
						</div>
					</td>
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						电表ID
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
																			String kkt="";
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		kkt = kkt + " AND Z.SHI='" + shi + "'";
																	}
																	String ist="";
																	if(xian !=null && !xian.equals("")&&!xian.equals("0")){
																	  ist=ist+" AND Z.XIAN='"+xian+"'";
																	}
																	String zdd = "";
																	if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
																		zdd = zdd + " AND Z.STATIONTYPE='" + zdlx + "'";
																	}
																	String bett = "";
																	if (beginTime != null && !beginTime.equals("")
																			&& !beginTime.equals("0")) {
																		bett = bett + " AND TO_CHAR(A.STARTMONTH,'yyyy-mm') >='" + beginTime + "'";
																	}
																	if (endTime != null && !endTime.equals("") && !endTime.equals("0")) {
																		bett = bett + " AND TO_CHAR(A.ENDMONTH,'yyyy-mm')<='" + endTime + "'";
																	}
																	
																	
                                                                       System.out.println("==============="+shi+"1"+xian+"2"+zdlx+"3"+beginTime+"4"+endTime+"5");
																	ArrayList fylist = new ArrayList();
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		fylist = bean.getlistchaobiao1(kkt, zdd, bett, loginId,ist);
															
																		String shengl = "";
																		String shil = "";
																		String xianl = "";

																		String jztype = "", property = "", yflx = "", jnglmk = "", gdfs = "", df = "", dl = "", dldb = "", dfdb = "";
																		//==所在地区szdq==在用电表总数dbzs===抄表次数cbcs==有效次数yxcs==电表抄表率dblv==
																		String sszdq="";
																	
																	
																	
																		//=============
																		String zdmc="";//站点名称
																		String dbid="";//电表ID
																		String scdbs="";//上次读表数
																		String bcdbs="";//本次读表数
																		String scdbsj="";//上次读表时间
																		String bcdbsj="";//本次读表时间
																		String zsdl="";//折算后电量
																		 String ac="";
																		 String startmonth="",endmonth="";
																		//=============
																		if (fylist != null) {
																			int fycount = ((Integer) fylist.get(0)).intValue();
																			for (int k = fycount; k < fylist.size() - 1; k += fycount) {

																				zdmc = (String) fylist.get(k + fylist.indexOf("JZNAME"));
																				zdmc = zdmc != null ? zdmc : "";
                                                                                   	ac = (String) fylist.get(k + fylist.indexOf("BLHDL"));
																				ac = ac != null ? ac : "0";
																				if(ac.equals("null")){
																				ac="0";
																				}
                                                                                   	startmonth = (String) fylist.get(k + fylist.indexOf("STARTMONTH"));
																				startmonth = startmonth != null ? startmonth : "";
																				if("null".equals(startmonth)){
																				 startmonth="";
																				}
																				
																				endmonth = (String) fylist.get(k + fylist.indexOf("ENDMONTH"));
																				endmonth = endmonth != null ? endmonth : "";
																				if("null".equals(endmonth)){
																				 endmonth="";
																				}
                                                                                   
                                                                                   
                                                                                   
                                                                                   
                                                                                   
																				dbid = (String) fylist.get(k + fylist.indexOf("DBID"));
																				dbid = dbid != null ? dbid : "0";
																				
																				scdbs = (String) fylist.get(k + fylist.indexOf("LASTDEGREE"));
																				scdbs = scdbs != null ? scdbs : "0";
																				if("null".equals(scdbs)||"".equals(scdbs)){
																				scdbs="0";
																				}
																				
																				DecimalFormat countdl=new DecimalFormat("0.0");
		                                                                                 scdbs=countdl.format(Double.parseDouble(scdbs));
																			             ac=countdl.format(Double.parseDouble(ac));

																				bcdbs = (String) fylist.get(k + fylist.indexOf("THISDEGREE"));
																				bcdbs = bcdbs != null ? bcdbs : "0";
																				if("null".equals(bcdbs)||"".equals(bcdbs)){
																				bcdbs="0";
																				}
																				
																				DecimalFormat countd2=new DecimalFormat("0.0");
		                                                                                 bcdbs=countd2.format(Double.parseDouble(bcdbs));
																			 
																			 bcdbsj=(String)fylist.get(k + fylist.indexOf("THISDATETIME"));
																			 bcdbsj = bcdbsj != null ? bcdbsj : "";


                                                                             scdbsj=(String)fylist.get(k + fylist.indexOf("LASTDATETIME"));
																			 scdbsj = scdbsj != null ? scdbsj : "";
																			if("null".equals(scdbsj)){
																			scdbsj="";
																			}
																			 
																			 
																			 zsdl=(String)fylist.get(k + fylist.indexOf("ACTUALDEGREE"));
																			 zsdl = zsdl != null ? zsdl : "";
																			 
																				String color1 = null;

																				if (intnum % 2 == 0) {
																					color1 = "#FFFFFF";

																				} else {
																					color1 = "#DDDDDD";
																				}
																				intnum++;
																				
																%>
				<tr bgcolor=<%=color1%>>
				<td>
						<div align="center"><%=intnum%></div>
					</td>
				<td>
						<div align="left"><%=zdmc%></div>
					</td>
					
					<td id="idss">
						<div align="right"><%=scdbs%></div>
					</td>

					<td id="idss">
						<div align="right"><%=bcdbs%></div>
					</td>
					<td id="idss">
						<div align="right"><%=ac%></div>
					</td>
					<td id="idss">
						<div align="center"><%=scdbsj%>
						</div>
					</td>
					<td id="idss">
						<div align="center"><%=bcdbsj%>
						</div>
					</td>
					<td id="idss">
						<div align="center"><%=startmonth%>
						</div>
					</td>
					<td id="idss">
						<div align="center"><%=endmonth%>
						</div>
					</td>
				<td id="idss">
						<div align="left"><%=dbid%></div>
					</td>
				</tr>

				<%
																	}
																%>
				<%
																	}
																%>
			

				<%
																	}
																%>

			</table>
			<% 
			}
			%>
			
			<%if(yxcs!=null&&!yxcs.equals("")){%>
			
			<table width="99%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
				<tr height="20"  class="trHead">
				    <td id="idss" width="8%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							序号
						</div>
					</td>
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							站点名称
						</div>
					</td>
					
					
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							上次电表读数
						</div>
					</td>
					<td id="idss" width="8%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						本次读表数
						</div>
					</td>
					<td id="idss" width="8%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						实际用电量
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						上次读表时间
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
				本次抄表时间
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
				起始月份
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
				结束月份
						</div>
					</td>
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							电表ID
						</div>
					</td>
				</tr>
				<%
											
																	SiteBeanchmark bean = new SiteBeanchmark();
																	String whereStr = "";
																	System.out.println("dianliangListjsp--------------:" + sptype
																			+ chanquan);
																			String kkt="";
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		kkt = kkt + " AND Z.SHI='" + shi + "'";
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
																	if (endTime != null && !endTime.equals("") && !endTime.equals("0")) {
																		bett = bett + " AND TO_CHAR(AA.ENDMONTH,'yyyy-mm')<='" + endTime + "'";
																	}
																	
																	String idd="";
																	if(id!=null&&!id.equals("")){
																	   idd=idd+" AND Z.XIAN='"+id+"'";
																	}
                                                                       System.out.println("==============="+shi+"1"+xian+"2"+zdlx+"3"+beginTime+"4"+endTime+"5");
																	ArrayList fylist = new ArrayList();
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		fylist = bean.getlistchaobiao7(kkt, zdd, bett, loginId,idd);
															
																		String shengl = "";
																		String shil = "";
																		String xianl = "";

																		String jztype = "", property = "", yflx = "", jnglmk = "", gdfs = "", df = "", dl = "", dldb = "", dfdb = "";
																		//==所在地区szdq==在用电表总数dbzs===抄表次数cbcs==有效次数yxcs==电表抄表率dblv==
																		String sszdq="";
																	
																	
																	
																		//=============
																		String zdmc="";//站点名称
																		String dbid="";//电表ID
																		String scdbs="";//上次读表数
																		String bcdbs="";//本次读表数
																		String scdbsj="";//上次读表时间
																		String bcdbsj="";//本次读表时间
																		String zsdl="";//折算后电量
																		 String ac="";
																		String  startmonth="";
																		String endmonth="";
																		//=============
																		if (fylist != null) {
																			int fycount = ((Integer) fylist.get(0)).intValue();
																			for (int k = fycount; k < fylist.size() - 1; k += fycount) {

																				zdmc = (String) fylist.get(k + fylist.indexOf("JZNAME"));
																				zdmc = zdmc != null ? zdmc : "";
																				
																				startmonth = (String) fylist.get(k + fylist.indexOf("STARTMONTH"));
																				startmonth = startmonth != null ? startmonth : "";
																				if("null".equals(startmonth)){
																				 startmonth="";
																				}
																				
																				endmonth = (String) fylist.get(k + fylist.indexOf("ENDMONTH"));
																				endmonth = endmonth != null ? endmonth : "";
																				if("null".equals(endmonth)){
																				 endmonth="";
																				}
																				
																				

																				dbid = (String) fylist.get(k + fylist.indexOf("DBID"));
																				dbid = dbid != null ? dbid : "0";
																				
																				  ac = (String) fylist.get(k + fylist.indexOf("BLHDL"));
																				ac = ac != null ? ac : "0";
																				if(ac.equals("null")){
																				ac="0";
																				}
																				
																				
																				
																				
																				
																				scdbs = (String) fylist.get(k + fylist.indexOf("LASTDEGREE"));
																				scdbs = scdbs != null ? scdbs : "0";
																				if("null".equals(scdbs)||"".equals(scdbs)){
																				scdbs="0";
																				}
																				DecimalFormat countd1=new DecimalFormat("0.0");
		                                                                                 scdbs=countd1.format(Double.parseDouble(scdbs));
																			 ac=countd1.format(Double.parseDouble(ac));

																				bcdbs = (String) fylist.get(k + fylist.indexOf("THISDEGREE"));
																				bcdbs = bcdbs != null ? bcdbs : "0";
																				if("null".equals(bcdbs)||"".equals(bcdbs)){
																				bcdbs="0";
																				}
																				DecimalFormat countd2=new DecimalFormat("0.0");
		                                                                                 bcdbs=countd2.format(Double.parseDouble(bcdbs));
																			 
																			 bcdbsj=(String)fylist.get(k + fylist.indexOf("THISDATETIME"));
																			 bcdbsj = bcdbsj != null ? bcdbsj : "";


                                                                             scdbsj=(String)fylist.get(k + fylist.indexOf("LASTDATETIME"));
																			 scdbsj = scdbsj != null ? scdbsj : "";
																			 
																			 
																			 zsdl=(String)fylist.get(k + fylist.indexOf("ACTUALDEGREE"));
																			 zsdl = zsdl != null ? zsdl : "";
																			 
																				String color1 = null;

																				if (intnum % 2 == 0) {
																					color1 = "#FFFFFF";

																				} else {
																					color1 = "#DDDDDD";
																				}
																				intnum++;
																				
																%>
				<tr bgcolor=<%=color1%>>
				<td id="idss">
						<div align="center"><%=intnum%></div>
					</td>
					<td id="idss">
						<div align="left"><%=zdmc%></div>
					</td>
					
					<td id="idss">
						<div align="center"><%=scdbs%></div>
					</td>

					<td id="idss">
						<div align="right"><%=bcdbs%></div>
					</td>
					<td id="idss">
						<div align="right"><%=ac%></div>
					</td>
					<td id="idss">
						<div align="center"><%=scdbsj%>
						</div>
					</td>
					<td id="idss">
						<div align="center"><%=bcdbsj%>
						</div>
					</td>
					<td id="idss">
						<div align="center"><%=startmonth%>
						</div>
					</td>
					<td id="idss">
						<div align="center"><%=endmonth%>
						</div>
					</td>
				<td id="idss">
						<div align="left"><%=dbid%></div>
					</td>
				</tr>

				<%
																	}
																%>
				<%
																	}
																%>
			

				<%
																	}
																%>

			</table>
			<% 
			}
			%>
			
			<%if(yxcszs!=null &&!yxcszs.equals("")){%>
 <table width="99%" border="0" cellspacing="1"  bgcolor="#cbd5de" cellpadding="1" class="form_label">
				<tr height="20"  class="trHead">
				    <td id="idss" width="8%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							序号
						</div>
					</td>
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							站点名称
						</div>
					</td>
					
					
					<td id="idss" width="9%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							上次电表读数
						</div>
					</td>
					<td id="idss" width="8%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						本次读表数
						</div>
					</td>
					<td id="idss" width="8%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						实际用电量
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
						上次读表时间
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
				本次抄表时间
						</div>
					</td>
					<td id="idss" width="12%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
				起始月份
						</div>
					</td>
					<td id="idss" width="14%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
				结束月份
						</div>
					</td>
					<td id="idss" width="11%" height="23" bgcolor="#DDDDDD">
						<div align="center" class="bttcn">
							电表ID
						</div>
					</td>
				</tr>


				<%
																	
																		SiteBeanchmark bean = new SiteBeanchmark();
																	String whereStr = "";
																	System.out.println("dianliangListjsp--------------:" + sptype
																			+ chanquan);
																			String kkt="";
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		kkt = kkt + " AND Z.SHI='" + shi + "'";
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
																	if (endTime != null && !endTime.equals("") && !endTime.equals("0")) {
																		bett = bett + " AND TO_CHAR(AA.ENDMONTH,'yyyy-mm')<='" + endTime + "'";
																	}
																	
																	String ist="";
																	if(xian!=null&&!xian.equals("")&&!xian.equals("0")){
																	   ist=ist+" AND Z.XIAN='"+xian+"'";
																	}
                                                                       System.out.println("==============="+shi+"1"+xian+"2"+zdlx+"3"+beginTime+"4"+endTime+"5");
																	ArrayList fylist = new ArrayList();
																	if (shi != null && !shi.equals("") && !shi.equals("0")) {
																		fylist = bean.getlistchaobiao7(kkt, zdd, bett, loginId,ist);
															
																		String shengl = "";
																		String shil = "";
																		String xianl = "";

																		String jztype = "", property = "", yflx = "", jnglmk = "", gdfs = "", df = "", dl = "", dldb = "", dfdb = "";
																		//==所在地区szdq==在用电表总数dbzs===抄表次数cbcs==有效次数yxcs==电表抄表率dblv==
																		String sszdq="";
																	
																	
																	
																		//=============
																		String zdmc="";//站点名称
																		String dbid="";//电表ID
																		String scdbs="";//上次读表数
																		String bcdbs="";//本次读表数
																		String scdbsj="";//上次读表时间
																		String bcdbsj="";//本次读表时间
																		String zsdl="";//折算后电量
																		 String ac="";
																		 String startmonth="",endmonth="";
																		//=============
																		if (fylist != null) {
																			int fycount = ((Integer) fylist.get(0)).intValue();
																			for (int k = fycount; k < fylist.size() - 1; k += fycount) {

																				dbid = (String) fylist.get(k + fylist.indexOf("DBID"));
																				dbid = dbid != null ? dbid : "";
                                                                                 
                                                                                 startmonth = (String) fylist.get(k + fylist.indexOf("STARTMONTH"));
																				startmonth = startmonth != null ? startmonth : "";
																				if("null".equals(startmonth)){
																				 startmonth="";
																				}
																				
																				endmonth = (String) fylist.get(k + fylist.indexOf("ENDMONTH"));
																				endmonth = endmonth != null ? endmonth : "";
																				if("null".equals(endmonth)){
																				 endmonth="";
																				}

                                                                                ac = (String) fylist.get(k + fylist.indexOf("BLHDL"));
																				ac = ac != null ? ac : "0";
																				if(ac.equals("null")){
																				ac="0";
																				}

                                                                                    
																				zdmc = (String) fylist.get(k + fylist.indexOf("JZNAME"));
																				zdmc = zdmc != null ? zdmc : "0";
																				
																				scdbs = (String) fylist.get(k + fylist.indexOf("LASTDEGREE"));
																				scdbs = scdbs != null ? scdbs : "0";
																				if("null".equals(scdbs)||"".equals(scdbs)){
																				scdbs="0";
																				}
																				DecimalFormat countd1=new DecimalFormat("0.0");
		                                                                                 scdbs=countd1.format(Double.parseDouble(scdbs));
																			            ac=countd1.format(Double.parseDouble(ac));

																				bcdbs = (String) fylist.get(k + fylist.indexOf("THISDEGREE"));
																				bcdbs = bcdbs != null ? bcdbs : "0";
																				if("null".equals(bcdbs)||"".equals(bcdbs)){
																				bcdbs="0";
																				}
																			 DecimalFormat countd2=new DecimalFormat("0.0");
		                                                                                 bcdbs=countd2.format(Double.parseDouble(bcdbs));
																			 bcdbsj=(String)fylist.get(k + fylist.indexOf("THISDATETIME"));
																			 bcdbsj = bcdbsj != null ? bcdbsj : "";


                                                                             scdbsj=(String)fylist.get(k + fylist.indexOf("LASTDATETIME"));
																			 scdbsj = scdbsj != null ? scdbsj : "";
																			 
																			 
																			 zsdl=(String)fylist.get(k + fylist.indexOf("ACTUALDEGREE"));
																			 zsdl = zsdl != null ? zsdl : "";
																			 
																				String color1 = null;

																				if (intnum % 2 == 0) {
																					color1 = "#FFFFFF";

																				} else {
																					color1 = "#DDDDDD";
																				}
																				intnum++;
																				
																%>
				<tr bgcolor=<%=color1%>>
				<td id="idss">
						<div align="center"><%=intnum%></div>
					</td>
					<td id="idss">
						<div align="left"><%=zdmc%></div>
					</td>
					
					<td id="idss">
						<div align="right"><%=scdbs%></div>
					</td>

					<td id="idss">
						<div align="right"><%=bcdbs%></div>
					</td>
					<td id="idss">
						<div align="right"><%=ac%></div>
					</td>
					
					<td id="idss">
						<div align="center"><%=scdbsj%>
						</div>
					</td>
					<td id="idss">
						<div align="center"><%=bcdbsj%>
						</div>
					</td>
					<td id="idss">
						<div align="center"><%=startmonth%>
						</div>
					</td>
					<td id="idss">
						<div align="center"><%=endmonth%>
						</div>
					</td>
				<td id="idss">
						<div align="left"><%=dbid%></div>
					</td>
				</tr>

				<%
																	}
																%>
				<%
																	}
																%>
				

				<%
																	}
																%>

			</table>			
			
			<% 
			}
			%>
			
	
	<script type="text/javascript">
var path = '<%=path%>';
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

</script>
