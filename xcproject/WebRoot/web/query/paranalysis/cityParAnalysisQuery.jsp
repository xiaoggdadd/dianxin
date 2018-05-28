<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.*"%>
<%@ page import="com.noki.util.CTime"%>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.query.hbanalysisquery.javabean.HbQsBean;"%>

<%
	String beginTime = request.getParameter("beginTime") != null ? request
			.getParameter("beginTime")
			:"";
	String title = request.getParameter("title") != null ? request
			.getParameter("title") : "";
	String operName = request.getParameter("operName") != null ? request
			.getParameter("operName")
			: "";
	System.out.println("operName=" + operName);
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
    String shi=request.getParameter("shi") != null ? request.getParameter("shi"):"137";
	String shiName=request.getParameter("shiName") != null ? request.getParameter("shiName"):"市";
	//System.out.println("shi_-------"+shi+account.getAccountId());
    CommonAccountBean bean = new CommonAccountBean();
    String list = bean.getShiChildrenArea(shi,account.getAccountId());
	String roleId = account.getRoleId();
	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
%>

<html>
	<head>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script src="<%=path%>/javascript/Date_ny.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>


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
var xian;
     function changeCity(){
     var shi=document.form1.shi.value;
     var shiName=document.form1.shi.options[document.form1.shi.selectedIndex].text;
    // alert(shiName);
     document.form1.action=path+"/web/query/paranalysis/cityParAnalysisQuery.jsp?beginTime="+document.form1.BeginTime.value+"&shi="+shi+"&shiName="+shiName;
     document.form1.submit();
  
    }
    function queryDegree(){
    	var bTime = document.form1.BeginTime.value;
		//var eTime = document.form1.EndTime.value;
		
			$("#tableTmp").html("");
			var tableInfo = "";
			tableInfo +="<LINK href='<%=path%>/images/css.css' type=text/css rel=stylesheet>";
			tableInfo += "<table id='tableInfo' border='1' borderColor='#DDDDDD' bgColor='#cbd5de' width='100%' cellspacing='1' cellpadding='1'>";
			xian=new Array();
			xian='<%=list%>'.toString().split(";");
			
			tableInfo += "<tr align='center' bgcolor='#888888'>";
			tableInfo += "<td width='100'><font color='#ffffff'><B>月份\\区县</B></font></td>";
			
		
			for(var i=0;i<xian.length;i++){
			
			     tableInfo += "<td><font color='#ffffff'><B>"+xian[i].toString().substring(8,xian[i].toString().length)+"(度/月)</B></font></td>";
			
			};
            tableInfo += "<td><font color='#ffffff'><B>合计(度/月)</B></font></td>";
			tableInfo += "</tr>";
			
	    	tableInfo += "<tr align='center' bgcolor='#FFFFFF'>"
			tableInfo += "<td>"+bTime+"</td>";
			for(var i = 0;i<xian.length;i++){
				tableInfo += "<td id='month"+i+"'>&nbsp;</td>";
			}
			tableInfo += "<td id='total'>&nbsp;</td>";
			tableInfo += "</tr>";
			
			
			tableInfo += "</table>";
			$("#tableTmp").html(tableInfo);
			renderData();
			renderChart();
		//}
    }
    
    function renderData(){
    	$.post(
			path+"/servlet/huanbi_hbpar",
			{action:"getShiData",cityStr:xian.toString(),timeStr:document.form1.BeginTime.value+""},
			function(returnedData){	   
			 data=new Array();
			 data =returnedData.toString().split(",");
			 var total=0;
				for(var i = 0;i<data.length;i++){
					$("#month"+i).text(data[i]);
					total=total+parseInt(data[i]);
				}
				$("#total").text(total);
		});
    }
    function renderChart(){
  // alert(xian.toString());
   
		$("#tableTmp").append("<img src='"+path+"/servlet/huanbi_hbpar?action=getShiChart&cityStr="+xian.toString()+"&timeStr="+document.form1.BeginTime.value+"'></img>");
		
    }
    $(function(){
		$("#query").click(function(){
			queryDegree();
		});
	});
</script>
	</head>
	<jsp:useBean id="commAccountBean" scope="page"
		class="com.noki.mobi.common.CommonAccountBean">
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
											<td colspan=3 width="600"
												background="<%=path%>/images/btt.bmp" height=63>
												<span style="color: black; font-weight: bold;">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 区县月耗电对比分析</span>
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
													<br>
													<tr>
														<td height="49" bgcolor="#FFFFFF">
															<table width="100%" border="0" cellspacing="1"
																cellpadding="1">
																<tr bgcolor="F9F9F9">
																	<td height="19" colspan="4">
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
																	<td height="8%" width="1000">
																		&nbsp;&nbsp;&nbsp; 月份：
																		<input type="text" name="BeginTime" value="<%=beginTime%>"
																			onFocus="getDateString(this,oCalendarChsny)"
																			class="form" />
																		&nbsp;&nbsp;&nbsp;
																	&nbsp;&nbsp;&nbsp;&nbsp;   地市：              
																         <select name="shi" style="width:130" onchange="changeCity()">
																   
																         		<option value="0">请选择</option>
																         		<%
																	         	ArrayList shenglist = new ArrayList();
																	         	shenglist = commAccountBean.getShi(account.getAccountId());
																	         	if(shenglist!=null){
																	         		//String sfid="",sfnm="";
																	         		int scount = ((Integer)shenglist.get(0)).intValue();
																	         		for(int i=scount;i<shenglist.size()-1;i+=scount)
																                    {
																                    	shi=(String)shenglist.get(i+shenglist.indexOf("AGCODE"));
																                    	shiName=(String)shenglist.get(i+shenglist.indexOf("AGNAME"));
																                    %>
																                    <option value="<%=shi%>"<%if(null != request.getParameter("shi"))if(request.getParameter("shi").equals(shi)) {%> selected<%} %>><%=shiName%></option>
																                    <%
																                    }
																	         	}
																	         %>
																         	</select>
																        <td>
																			<div id="query" style="position:relative;width:59px;height:23px;cursor: pointer; float: left; left: -500px">
						                                                        <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
						                                                        <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
					                                                        </div>
					                                                        </td>
																	
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
					</td>
				</tr>
			</table>
		</form>
		<div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
        </div>
		<div id="tableTmp"></div>
		
		</br></br>
		
	</body>