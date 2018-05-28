<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.*"%>
<%@ page import="com.noki.util.CTime"%>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.query.hbanalysisquery.javabean.HbQsBean;"%>

<%
	String beginTime = request.getParameter("beginTime") != null ? request
			.getParameter("beginTime")
			: CTime.formatRealDate(new Date());
	String endTime = request.getParameter("endTime") != null ? request
			.getParameter("endTime") : CTime.formatRealDate(new Date());
	//System.out.println("logManage.jsp>>"+beginTime);
	String title = request.getParameter("title") != null ? request
			.getParameter("title") : "";
	String operName = request.getParameter("operName") != null ? request
			.getParameter("operName")
			: "";
	System.out.println("operName=" + operName);
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
    CommonAccountBean bean = new CommonAccountBean();
    String list = bean.getShengChildrenArea("137",account.getAccountId());
	String roleId = account.getRoleId();
	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
%>

<html>
	<head>
<style type="text/css">
.bttcn{color:white;font-weight:bold;}
.form{width:130px}
</style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script src="<%=path%>/javascript/Date_ny.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<jsp:include page="/web/prePrint.jsp"></jsp:include>
<script > 
 
var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();
 
 
var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init();
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
<script language="javascript">
var path = '<%=path%>';
var months;

/*function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = ""
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

	} else {
		trObject.style.display = "none"
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
	}
}

function queryDegree() {
	var m = document.getElementsByName('test[]');
	var l = m.length;
	var chooseIdStr = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			chooseIdStr = chooseIdStr + m[i].value + ",";
		} else {
			chooseIdStr = chooseIdStr + "0" + ",";
		}
	}
	//var hntype = document.form1.hntype.value;
	var bTime = document.form1.BeginTime.value;
	//alert(bTime);
	beginTime = bTime;//.substr(0,4) + "." + bTime.substr(5,6); 
	//alert(beginTime);
	var eTime = document.form1.EndTime.value;
	endTime = eTime;
	var canshuStr = "?beginTime=" + beginTime + "&endTime=" + endTime + "+"
			& chooseIdStr
="+chooseIdStr; 

                   //alert(canshuStr);
                   document.form1.action=path+"/web/query/hbanalysisquery/sjZnhQsQuery.jsp"+canshuStr;
                   document.form1.submit();
       
    }*/
    function queryDegree11(){
    	var bTime = document.form1.BeginTime.value;
		var eTime = document.form1.EndTime.value;
		
		
		var monthTmp = (parseInt(eTime.substr(0,4),10)-parseInt(bTime.substr(0,4),10))*12+parseInt(eTime.substr(5),10)-parseInt(bTime.substr(5),10);
		
		if(monthTmp>=12)alert("查询信息范围必须在12个月以内！");
		else if(monthTmp<0)alert("结束月份必须大于起始月份");
		else{
			$("#tableInfo").html("");
			var tableInfo = "";
			tableInfo +="<LINK href='<%=path%>/images/css.css' type=text/css rel=stylesheet>";
			tableInfo += "<table id='tableInfo' border='1' borderColor='#DDDDDD' bgColor='#cbd5de' width='100%' cellspacing='1' cellpadding='1'>";
			
			
			tableInfo += "<tr align='center' bgcolor='#888888' >"
			tableInfo += "<td width='100' ><font color='#ffffff' ><B>地市\\统计月</B></font></td>"
			
			var monthStart = parseInt(bTime.substr(5,6),10);
			var yearStart = parseInt(bTime.substr(0,4),10);
			var yearEnd = parseInt(eTime.substr(0,4),10);
			var columNum = 0;
			months=new Array()
			while(yearStart <= yearEnd){
				while(monthStart <= 12 && columNum <= monthTmp){
				     if(monthStart<=9){
				         tableInfo += "<td><font color='#ffffff' ><B>"+yearStart+"-0"+monthStart+"</B></font></td>";
					     months.push(yearStart+"-0"+monthStart)
					 }
					else{
						tableInfo += "<td><font color='#ffffff' ><B>"+yearStart+"-"+monthStart+"</B></font></td>";
						months.push(yearStart+"-"+monthStart)
					}
					monthStart ++;
					columNum ++;
				}
				yearStart ++;
				monthStart = 1;
			}
			
			tableInfo += "</tr>";
			shi=new Array();
			shi='<%=list%>'.toString().split(";");
			for(var i = 0;i<shi.length-1;i++){
			
				tableInfo += "<tr align='center' bgcolor='#FFFFFF'>";
				tableInfo += "<td>"+shi[i].toString().substring(6,shi[i].toString().length)+"</td>";
				for(var j = 0;j<=monthTmp;j++){
				//alert(xian[i].toString().substring(6,8)+"-"+j);
					tableInfo += "<td id='"+shi[i].toString().substring(0,5)+""+j+"'>&nbsp;</td>";
				};
				tableInfo += "</tr>";
			}
			
			
			
			tableInfo += "</table>";
			$("#tableInfo").html(tableInfo);
			renderData();
			renderChart();
		}
    }
    
    function renderData(){
    	$.post(
			path+"/servlet/huanbi_hbanalysis",
			{action:"getData",city:shi.toString(),whereStr:months.toString()},
			//{action:"getShiData",cityStr:xian.toString(),timeStr:months.toString()},
			function(returnedData){	    
				for(var i = 0;i<returnedData.data.length;i++){
					for(var r = 0;r<returnedData.data[i].monthData.length;r++){
						
						
							$("#"+returnedData.data[i].city.toString()+""+r+"").text(returnedData.data[i].monthData[r]);
						}
				}
		});
    }
    function renderChart(){
    /*alert(jnStr+qdStr+zbStr+bzStr);
    	$.post(
			path+"/servlet/huanbi_hbanalysis",
			{action:"getChart",cityStr:"济南;青岛;淄博;滨州",timeStr:months.toString(),dataStr:jnStr+qdStr+zbStr+bzStr},
			function(returnedChart){
			$("#chart")=returnedChart;
		});*/
		$("#tableInfo").append("<img src='"+path+"/servlet/huanbi_hbanalysis?action=getChart&timeStr="+months.toString()+"&city="+shi.toString()+"'></img>");
		
    }
     function dayinpage(title) {
			var column = "all";//打印所有列
//			var column = [0,1,4,6];//定义表格要打印的列，下标从0开始
			var style="<style>table {border-collapse:collapse;border-style:double;border-width:3px;border-color:black;width:700px}</style>";//定义表格样式
			 
			create(title,setColumn(column,style));//创建打印样式
		};
		function setLayout(title,tableHTML){
			LODOP.SET_PRINT_STYLE("FontSize",18);
			LODOP.ADD_PRINT_TEXT(30,280,200,30,title);
			LODOP.ADD_PRINT_HTM(50,50,750,550,tableHTML); 
			LODOP.ADD_PRINT_IMAGE(200,60,700,360,"<img src='"+$("#tableInfo img").attr("src")+"' />");
			LODOP.SET_PRINT_STYLEA(3,"Stretch",1);//(可变形)扩展缩放模式
		}
		$(function(){

			$("#query").click(function(){
				queryDegree11();
			});
			$("#dayinBtn").click(function(){
				dayinpage('地市月耗电趋势');
			});
		});
</script>
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="4">
												
											</td>
										</tr>

										<tr>
											<td colspan=1 width="600"
												background="<%=path%>/images/btt.bmp" height=63>
												<span style="color: black; font-weight: bold;">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地市月耗电趋势查询</span>
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
															<table width="100%" border="0" cellspacing="1"
																cellpadding="1">
																<tr bgcolor="F9F9F9">
																<tr><td>&nbsp;</td></tr>
																	<td height="19" colspan="2">
																		<div id="parent" style="display: inline">
																			<div style="width: 50px; display: inline;">
																				<hr>
																			</div>
																			&nbsp;过滤条件&nbsp;
																			<div style="width: 300px; display: inline;">
																				<hr>
																			</div>
																		</div>
																	</td>
																</tr>
																<tr>
																	<td height="8%" width="500">
																		&nbsp;&nbsp;&nbsp; 起始月份：
																		<input type="text" name="BeginTime" value=""
																			onFocus="getDateString(this,oCalendarChsny)"
																			class="form" />
																		&nbsp;&nbsp;&nbsp;&nbsp; 结束月份：
																		<input type="text" name="EndTime" value=""
																			onFocus="getDateString(this,oCalendarChsny)"
																			class="form" />
																		
																	</td>
																	<td><div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:5%;TOP:0PX">
																			<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
																			<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
																		</div></td>
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
					</td>
				</tr>
			</table>
		</form>
		<div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
        </div>
		<div id="tableInfo"></div>
		
		</br></br>
		<div id="dayinBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:15px">
			<img src="<%=path %>/images/daochu.png" width="100%" height="100%">
			<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">打印</span>      
		</div>
	</body>