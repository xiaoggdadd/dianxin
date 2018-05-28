<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean"%>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean"%>
<%@ page import="java.util.Calendar"%>
<%@page
	import="com.noki.query.basequery.javabean.StationPointQueryBean,com.noki.mobi.common.CommonBean"%>
<%
	String path = request.getContextPath();
	String sheng = (String) session.getAttribute("accountSheng");

	Account account = (Account) session.getAttribute("account");
	String dfmc = (String) request.getParameter("dfmc");//跳转到本页面 获取的  站点的输入 要查询的名字 
	System.out.println("dfmc:::::::"+dfmc);
	String agcode1 = "";
	if (request.getParameter("shi") == null) {
		ArrayList shilist = new ArrayList();
		CommonBean commBean = new CommonBean();
		shilist = commBean.getAgcode(sheng, account.getAccountId());
		if (shilist != null) {
			int scount = ((Integer) shilist.get(0)).intValue();
			agcode1 = (String) shilist.get(scount
					+ shilist.indexOf("AGCODE"));
		}
	}

	if (dfmc != null && !"".equals(dfmc)) {
	} else {
		dfmc = "";
	}
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : agcode1;
	String xiaoqu = request.getParameter("xiaoqu") != null ? request
			.getParameter("xiaoqu") : "0";
	String zdlx = request.getParameter("zdlx") != null ? request
			.getParameter("zdlx") : "0";
	String str = "";
	if (shi != null && !shi.equals("") && !shi.equals("0")) {

		str = str + " and Z.shi='" + shi + "'";
	}
	if (xian != null && !xian.equals("") && !xian.equals("0")) {

		str = str + " and Z.xian='" + xian + "'";
	}
	if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {

		str = str + " and Z.xiaoqu='" + xiaoqu + "'";
	}
	if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {

		str = str + " and Z.STATIONTYPE='" + zdlx + "'";
	}
	System.out.println("str:"+str);
	String loginId = account.getAccountId();
	String roleId = account.getRoleId();
	String accountid = account.getAccountName();
	String canshuStr = "";
	if ((shi != null) || (xian != null)) {
		canshuStr = "&shi=" + shi + "&xian=" + xian;
	}
%>
<html>
	<head>
		<title></title>
		<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}

.form {
	height: 19px;
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
	line-height: 100%;
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
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #D7E7FA );
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
</style>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>

		<script>

var oCalendarEn = new PopupCalendar("oCalendarEn"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();

var oCalendarChs = new PopupCalendar("oCalendarChs"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChs.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChs.oBtnTodayTitle = "今天";
oCalendarChs.oBtnCancelTitle = "取消";
oCalendarChs.Init();
</script>
		<!-- 年月日期控件 -->
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

function changeMC() {

	var stationname = document.form1.stationname.value;
	 
	//var station = document.form1.stationname.value;
	var Text01 = document.form1.stationname.options[document.form1.stationname.selectedIndex].text;
	 
	var text = document.getElementById('dfmc');
	
	if (stationname == "") {
		alert("不能为空！");
		return
        	}else{ 
        	text.value=Text01;
        	alert(stationname);
        	var b=path+"/web/electricfees/adddianfeidannei.jsp?stationname="+stationname;
        	//alert(b);
			var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";		
			$("#tmpTree").remove();
			$("body").append(a);
			
			$("#tmpTree")[0].click();
			}
         }
        
        function vCode(){
          var accCode = document.form1.workSn.value;
          if(accCode==""){
           	alert("不能为空！")
                   return
          }
               window.open('accountCode.jsp?accountId=0&accountCode='+accCode,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }

        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！")
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        //计算结束年月
        function endmonthzq(payzq){
         var startmonth = document.form1.startmonth.value;
         if(payzq==""||payzq==null||payzq==0)payzq=1;
         var month=startmonth.substring(5,7)-1+payzq;
         var year=startmonth.substring(0,4)-0;
        // alert(year+"-"+month);
         if(month>12){
        	 year=(year+1);
        	 month=month-12;
         }
         if(month<10){
        	 month="0"+month;
         }
         var s;
         s=year+"-"+month;
         document.form1.endmonth.value=s;
        }
        
       $(function(){
        $("#chaxun").click(function(){
	    exportCheck();
         });
       });
       function exportCheck(){
          
          document.form1.action=path+"/web/electricfees/adddianfeidan.jsp?";      
                   document.form1.submit();
       }

		
</script>
		<%--
<script type="text/javascript">   
startload(document.getElementById("frameprogrssBar"),document.getElementById("framecontent"))

function startload(loadstr,iframestr){
var loadstr=loadstr;
var iframestr=iframestr;

//loadstr.style.display ="block";//显示进度条
//iframestr.style.display ="none";//隐藏 数据


if (window.ActiveXObject){
iframestr.onreadystatechange=function (){ 
if(this.readyState=="complete"){// 解释：一个iframe加载完毕的状态是complete,就象xmlhttp里的那个==4一样,这些都是规定的
   loadstr.innerHTML="load complete!";    
   loadstr.style.display="none"; 
   iframestr.style.display="block";   
}    
}
}else{
iframestr.style.display ="block";//隐藏 
loadstr.style.display ="none";//显示
}

} 


</script>--%>

	</head>
	<jsp:useBean id="roleBean" scope="page"
		class="com.noki.mobi.sys.javabean.RoleBean"></jsp:useBean>
	<jsp:useBean id="Bean" scope="page"
		class="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean"></jsp:useBean>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body class="body" style="overflow-x: hidden;">
		<LINK href="../../images/css.css" type="text/css" rel=stylesheet>
		<form action="" name="form1" method="POST" id="form">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table width="100%" height="100%" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td colspan="8">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="8"></td>
										</tr>

										<tr>
											<td colspan="8">
												<div style="width: 700px;">
													<img src="<%=path%>/images/btt.bmp" width="100%"
														height="100%" />
													<span
														style="font-size: 14px; font-weight: bold; position: absolute; left: 25px; top: 15px; color: black">添加电费单</span>
												</div>
											</td>

										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="8">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="49" bgcolor="#FFFFFF" colspan="8">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="1">



													<tr class="form_label">
														<td class="form_label">
															城市：
														</td>

														<td>
															<select name="shi" class="selected_font"
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
															</select>
															<span class="style1">&nbsp;*</span>
														</td>
														<td class="form_label">
															区县：
														</td>
														<td>
															<select name="xian" class="selected_font"
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
															</select>
														</td>


														<td class="form_label">
															乡镇：
														</td>
														<td>
															<select name="xiaoqu" class="selected_font"
																onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
																<option value="0">
																	请选择
																</option>
																<%
																	ArrayList xiaoqulist = new ArrayList();
																	xiaoqulist = commBean.getAgcode(xian, account.getAccountId());
																	if (xiaoqulist != null) {
																		String agcode = "", agname = "";
																		int scount = ((Integer) xiaoqulist.get(0)).intValue();
																		for (int i = scount; i < xiaoqulist.size() - 1; i += scount) {
																			agcode = (String) xiaoqulist.get(i
																					+ xiaoqulist.indexOf("AGCODE"));
																			agname = (String) xiaoqulist.get(i
																					+ xiaoqulist.indexOf("AGNAME"));
																%>
																<option value="<%=agcode%>"><%=agname%></option>
																<%
																	}
																	}
																%>
															</select>
														</td>
														<td class="form_label">
															站点类型：
														</td>
														<td>
															<select name="zdlx" class="selected_font" />
																<option class="form_label" value="0">
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










													<tr>
														<td colspan="8">
															<table width="100%" border="0">
																<tr class="selected_font">
																	<td height="5%" bgcolor="#DDDDDD" width="15%"
																		colspan="8">
																		<div align="left">
																			<font size="2">站点名称：</font>
																		</div>
																	</td>
																	<td>

																		<div style="position: relative;">
																			<span id="sp01"
																				style="margin-left: 230px; width: 18px; overflow: hidden;">
																				<select name="stationname"
																					style="width: 248px; margin-left: -230px;"
																					style="width:300" onchange="changeMC()">
																					<option value="0">
																						请选择
																					</option>
																					<%
																						ArrayList stationnamea = new ArrayList();
																						stationnamea = Bean.getStationMhchexk(loginId, dfmc, str);
																						String scode = "";
																						if (stationnamea != null) {
																							String name = "";
																							String dbid = "";
																							String zdcode = "";
																							if (stationnamea.size() == 0) {
																					%>

																					<option value="0">
																						输入有误
																					</option>

																					<%
																						} else {
																								int cscount = ((Integer) stationnamea.get(0)).intValue();
																								for (int i = cscount; i < stationnamea.size() - 1; i += cscount) {
																									scode = (String) stationnamea.get(i
																											+ stationnamea.indexOf("DBID"));
																									name = (String) stationnamea.get(i
																											+ stationnamea.indexOf("JZNAME"));
																									dbid = (String) stationnamea.get(i
																											+ stationnamea.indexOf("DBNAME"));
																									zdcode = (String) stationnamea.get(i
																											+ stationnamea.indexOf("ZDCODE"));
																									name = name + "--" + dbid;
																									//  System.out.println("dbid:"+scode+"  zdcode"+zdcode);
																					%>
																					<option value="<%=scode%>,<%=zdcode%>"><%=name%></option>
																					<%
																						}
																							}

																						}
																					%>
																				</select> </span>
																			<%
																				if (dfmc != null && !"".equals(dfmc) && stationnamea.size() != 0
																						&& stationnamea.size() < 3) {
																			%>
																			<input type="text" name="dfmc"
																				style="width: 230px; position: absolute; left: 0px;"
																				value="<%=(String) stationnamea.get(2 + stationnamea
						.indexOf("JZNAME"))%>" />
																			<%
																				} else {
																			%>
																			<input type="text" name="dfmc"
																				style="width: 230px; position: absolute; left: 0px;"
																				value="" />
																			<%
																				}
																			%>

																		</div>

																	</td>
																	<td>

																		<div id="chaxun"
																			style="position: relative; width: 60px; height: 23px">
																			<img alt="" src="<%=path%>/images/chaxun.png"
																				width="100%" height="100%" />
																			<span
																				style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
																		</div>
																	</td>
																	<td width="220px"></td>
																	<td width="300px"></td>
																	<td width="120px"></td>

																</tr>
															</table>
														</td>
													</tr>




													<tr>
														<td colspan="8">
															<iframe name="treeMap" width="100%" height="600px"
																scrolling="no" frameborder="0"
																src="<%=path%>/web/electricfees/adddianfeidannei.jsp"></iframe>

														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<br />
								</td>
							</tr>
						</table>
						<input type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
						</form>
	</body>
</html>

<script>
var XMLHttpReq;
//XMLHttpReq = createXMLHttpRequest();
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}
///////////////////////////////////////////////////////////
function sendRequest(url, para) {

	createXMLHttpRequest();

	XMLHttpReq.open("GET", url, true);

	if (para == "sheng") {
		XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
	} else if (para == "shi") {
		XMLHttpReq.onreadystatechange = processResponse_shi;
	} else if (para == "xian") {
		XMLHttpReq.onreadystatechange = processResponse_xian;
	} else {
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
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");

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
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
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
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			updateZd(res);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}

function changeSheng() {
	var sid = document.form1.sheng.value;
	//document.form1.sheng2.value=document.form1.sheng.value;
	if (sid == "0") {
		var shilist = document.all.shi;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=sheng&pid=" + sid, "sheng");

	}
}
function updateShi(res) {
	var shilist = document.all.shi;
	shilist.options.length = "0";
	shilist.add(new Option("请选项", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
function changeCity() {
	var sid = document.form1.shi.value;
	//document.form1.shi2.value=document.form1.shi.value;
	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=shi&pid=" + sid, "shi");
	}
}
function updateQx(res) {
	var shilist = document.all.xian;
	shilist.options.length = "0";
	shilist.add(new Option("请选项", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}

function changeCountry() {
	var sid = document.form1.xian.value;
	//document.form1.xian2.value=document.form1.xian.value;
	if (sid == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=xian&pid=" + sid, "xian");
	}
}
function updateZd(res) {
	var shilist = document.all.xiaoqu;
	shilist.options.length = "0";
	shilist.add(new Option("请选项", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
document.form1.zdlx.value = '<%=zdlx%>';
</script>