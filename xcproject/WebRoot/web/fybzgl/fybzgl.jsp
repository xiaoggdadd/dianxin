<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.ZhanDianForm,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean"%>
<%@ page import="com.noki.tongjichaxu.*"%>
<%@ page import="com.noki.fybzgl.bean.*"%>
<%@ page import="java.text.*"%>

<%
	String sheng = (String) session.getAttribute("accountSheng");
	String roleId = (String) session.getAttribute("accountRole");
	Account account1 = (Account) session.getAttribute("account");
	String agcode1 = "";
	String path = request.getContextPath();
	String beginTime = request.getParameter("beginTime") != null ? request
			.getParameter("beginTime")
			: "";
	String endTime = request.getParameter("endTime") != null ? request
			.getParameter("endTime") : "";
	String jzsx = request.getParameter("jzproperty") != null ? request
			.getParameter("jzproperty") : "";
	String bzw = request.getParameter("bzw") != null ? request
			.getParameter("bzw") : "";
	String fy = request.getParameter("feiyongtype") != null ? request
			.getParameter("feiyongtype") : "";

	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : "0";

	Account account = (Account) session.getAttribute("account");
	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	String b1 = request.getParameter("");
	curpage = Integer.parseInt(s_curpage);
	int intnum = 0;
	String color = "";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'cbzy.jsp' starting page</title>
		<style type="text/css">
.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}

.style1 {
	color: red;
	font-weight: bold;
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}

.bttcn {
	color: BLACK;
	font-weight: bold;
}

.selected_font {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
}
.selected_font1 {
	width: 80px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
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
</style>
		<script type="text/javascript"
			src="<%=path%>/web/javascript/jquery-1.4.2.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
		<!-- 年月日期控件 -->
		<script type='text/javascript'
			src='/energy/dwr/interface/fybzglServise.js'>
</script>
		<script type='text/javascript' src='/energy/dwr/engine.js'>
</script>
		<script type='text/javascript' src='/energy/dwr/util.js'>
</script>

		<script language="javascript">

var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();

var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChsny.oBtnTodayTitle = "确定";
oCalendarChsny.oBtnCancelTitle = "取消";
oCalendarChsny.Init();

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

function showhb(hStr) {
	//alert(hStr);
	window
			.open("huanbi_hdl_city.jsp?dataStr=" + hStr, '',
					'width=600,height=400,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')

}

function chaxun() {
	var bz1 = document.form1.beginTime.value;
	var bz2 = document.form1.endTime.value;
	var shi = document.form1.shi.value;
	
	var feiyongtype = document.form1.feiyongtype.value;
	var zflx = document.form1.zflx.value;
	
	var whereStr = " ";
	var str = " ";
	if (document.getElementById("endTime").value == ""
			|| document.getElementById("beginTime").value == "") {
		alert("日期不能为空");
	} else {

		if (null != bz1 && bz1 != "" && bz1 != "0") {
			whereStr = whereStr + " and  to_char(e.accountmonth,'yyyy-mm')>='" + bz1 + "'";
			str = str + " and  to_char(ee.accountmonth,'yyyy-mm')>='" + bz1 + "'";
		}
		if (null != bz2 && bz2 != "" && bz2 != "0") {
			whereStr = whereStr + " and  to_char(e.accountmonth,'yyyy-mm')<='" + bz2 + "'";
			str = str + " and  to_char(ee.accountmonth,'yyyy-mm')<='" + bz2 + "'";
		}
		if (null != shi && shi != "" && shi != "0") {
			whereStr = whereStr + " and  z.shi='" + shi + "'";
			str = str + " and  zz.shi='" + shi + "'";
		}
		//zdsx();
		fybzglServise.getAall(whereStr, str,feiyongtype,zflx, callBack);

	}
}


function zdsx(){
	var whereStr = " ";
	var zflx = document.form1.zflx.value;
	gdfs();
	//alert(zflx);
	var bz1 = document.form1.beginTime.value;
	var bz2 = document.form1.endTime.value;
	var shi = document.form1.shi.value;
	var feiyongtype = document.form1.feiyongtype.value;
	
	if (null != bz1 && bz1 != "" && bz1 != "0") {
			whereStr = whereStr + " and  to_char(e.accountmonth,'yyyy-mm')>='" + bz1 + "'";
		}
		if (null != bz2 && bz2 != "" && bz2 != "0") {
			whereStr = whereStr + " and  to_char(e.accountmonth,'yyyy-mm')<='" + bz2 + "'";
		}
		if (null != shi && shi != "" && shi != "0") {
			whereStr = whereStr + " and  z.shi='" + shi + "'";
		}
		
		fybzglServise.getZdsxMonery(whereStr,feiyongtype,zflx,callzdsx);
		
}
function callzdsx(data){
	for(var i=0;i<6;i++){
		document.getElementById("zd"+i).innerHTML="<td bgcolor='#DDDDDD' class='form_label'><div align='center' class='bttcn'></div></td>"; 
		document.getElementById("zdm"+i).innerHTML="<td bgcolor='#DDDDDD' class='form_label'><div align='center' class='bttcn'></div></td>";
	}
	
	
	for(var i=0;i<data.length;i++){   
		var str = data[i];
		//alert(str.property);
		//alert("zd"+i);
		//alert("'+str.zdsx+'");
		var t =0;
		if("局用机房"==str.zdsx){
			t=0;
		}else{
			if("基站"==str.zdsx){
				t=1;
			}else{
				if("营业网点"==str.zdsx){
					t=2;
					}else{
						if("接入网"==str.zdsx){
							t=3;
						}else{
							if("其他"==str.zdsx){
								t=4;
							}else{
								if("乡镇支局"==str.zdsx){
									t=5;
								}
							}
						}
					}
				}
			}
		
		//var t = i;
		//alert(str.zdsx+"    "+t);
		document.getElementById("zd"+i).innerHTML="<td bgcolor='#DDDDDD' class='form_label'><div align='center' class='bttcn'>"+str.zdsx+"</div></td>"; 
		document.getElementById("zdm"+i).innerHTML="<td bgcolor='#DDDDDD' class='form_label'><div align='center' class='bttcn'><a href='#' onclick='tt("+t+")'>"+Math.round(str.zdsxmonery*100)/100+"</a></div></td>";
		}
	var url = "<%=path%>/web/fybzgl/fybzgliframeLeft.jsp?bz=1";
	var target='treeMap1';
	window.open(url, target); 
			
	
}
function gdfszd(){
	var feiyongtype = document.form1.feiyongtype.value;
	var whereStr = " ";
	var bz1 = document.form1.beginTime.value;
	var bz2 = document.form1.endTime.value;
	var shi = document.form1.shi.value;
	
	if (null != bz1 && bz1 != "" && bz1 != "0") {
			whereStr = whereStr + " and  to_char(e.accountmonth,'yyyy-mm')>='" + bz1 + "'";
		}
		if (null != bz2 && bz2 != "" && bz2 != "0") {
			whereStr = whereStr + " and  to_char(e.accountmonth,'yyyy-mm')<='" + bz2 + "'";
		}
		if (null != shi && shi != "" && shi != "0") {
			whereStr = whereStr + " and  z.shi='" + shi + "'";
		}
		
		
		fybzglServise.getGdfsMonery(whereStr,feiyongtype,callgdfs);
		
}
function tt(tt){
	var whereStr = " ";
	var feiyongtype = document.form1.feiyongtype.value;
	var bz1 = document.form1.beginTime.value;
	var bz2 = document.form1.endTime.value;
	var shi = document.form1.shi.value;
	var zflx = document.form1.zflx.value;
	if (null != tt && tt != "") {
		   if("0"==tt){
			   whereStr = whereStr + " and  z.property='zdsx01'";
		   }else{
			 
			  if("1"==tt){
			   whereStr = whereStr + " and  z.property='zdsx02'";
		      }else{
		    	  if("2"==tt){
			   whereStr = whereStr + " and  z.property='zdsx03'";
		        }else{
		        	if("3"==tt){
			   whereStr = whereStr + " and  z.property='zdsx04'";
		           }else{
		        	   if("4"==tt){
			   whereStr = whereStr + " and  z.property='zdsx05'";
		              } else{
		            	  if("5"==tt){
			   whereStr = whereStr + " and  z.property='zdsx06'";
		                 }else{
		                	 whereStr = whereStr + " and  z.property='zdsx01'";
		                 }
		              }
		           }
		        }
		      }
		   }
			
		}
	
	//alert(zflx+"   "+tt);
	if (null != bz1 && bz1 != "" && bz1 != "0") {
			whereStr = whereStr + " and  to_char(e.accountmonth,'yyyy-mm')>='" + bz1 + "'";
		}
		if (null != bz2 && bz2 != "" && bz2 != "0") {
			whereStr = whereStr + " and  to_char(e.accountmonth,'yyyy-mm')<='" + bz2 + "'";
		}
		if (null != shi && shi != "" && shi != "0") {
			whereStr = whereStr + " and  z.shi='" + shi + "'";
		}
		
		
		fybzglServise.getGdfsMonery(whereStr,feiyongtype,zflx,callgdfs);
		
}
function gdfs(){
	var feiyongtype = document.form1.feiyongtype.value;
	var whereStr = " ";
	var zflx = document.form1.zflx.value;
	//alert("gdfs"+zflx);
	var bz1 = document.form1.beginTime.value;
	var bz2 = document.form1.endTime.value;
	var shi = document.form1.shi.value;
	
	if (null != bz1 && bz1 != "" && bz1 != "0") {
			whereStr = whereStr + " and  to_char(e.accountmonth,'yyyy-mm')>='" + bz1 + "'";
		}
		if (null != bz2 && bz2 != "" && bz2 != "0") {
			whereStr = whereStr + " and  to_char(e.accountmonth,'yyyy-mm')<='" + bz2 + "'";
		}
		if (null != shi && shi != "" && shi != "0") {
			whereStr = whereStr + " and  z.shi='" + shi + "'";
		}
		
		
		fybzglServise.getGdfsMonery(whereStr,feiyongtype,zflx,callgdfs);
		
}
function callgdfs(data){
	for(var j =0;j<3;j++){
		document.getElementById("gd"+j).innerHTML="<td bgcolor='#DDDDDD' class='form_label'><div align='center' class='bttcn'></div></td>";
		document.getElementById("gdm"+j).innerHTML="<td bgcolor='#DDDDDD' class='form_label'><div align='center' class='bttcn'></div></td>";
		
	}
	for(var i=0;i<data.length;i++){   
		var str = data[i];
		//alert("zd"+i);
		document.getElementById("gd"+i).innerHTML="<td bgcolor='#DDDDDD' class='form_label'><div align='center' class='bttcn'>"+str.gdfs+"</div></td>";
		document.getElementById("gdm"+i).innerHTML="<td bgcolor='#DDDDDD' class='form_label'><div align='center' class='bttcn'>"+Math.round(str.gdfsmonery*100)/100+"</div></td>";
		}
	
	var url = "<%=path%>/web/fybzgl/fybzgliframeRight01.jsp?bz=1";
	var target='treeMap2';
	window.open(url, target); 
	
}
function callBack(data) {
	//var url = path + "/web/fybzgl/fybzgliframeLeft.jsp?bzw=1&list=" + data;
    
	//var target = "treeMap";
	//window.open(url, target);
	var bz1 = document.form1.beginTime.value;
	var bz2 = document.form1.endTime.value;
	var shi = document.form1.shi.value;
	
	document.getElementById("dshi").innerHTML = data.shi;
    document.getElementById("dcount").innerHTML = data.count;
    document.getElementById("dmonery").innerHTML = "<a href='#' onclick='zdsx()'>"+data.monerycount+"</a>";
    document.getElementById("dcwmonery").innerHTML = data.cwmonerycount;
    document.getElementById("dyiji").innerHTML = data.yjbili;
    document.getElementById("derji").innerHTML = data.erjbili;
    document.getElementById("dyj01").innerHTML = data.yj01;
    document.getElementById("dyj02").innerHTML = data.yj02;
    document.getElementById("dyj03").innerHTML = data.yj03;
    document.getElementById("dyj04").innerHTML = data.yj04;
    document.getElementById("dyj05").innerHTML = data.yj05;
    document.getElementById("dyj06").innerHTML = data.yj06;
    document.getElementById("derj01").innerHTML = data.erj01;
    document.getElementById("derj02").innerHTML = data.erj02;
    document.getElementById("derj03").innerHTML = data.erj03;
    document.getElementById("derj04").innerHTML = data.erj04;
    document.getElementById("derj05").innerHTML = data.erj05;
    document.getElementById("derj06").innerHTML = data.erj06;
    
    var url = "<%=path%>/web/fybzgl/fybzgliframeLeft.jsp?bz=1";
	var target='treeMap1';
	window.open(url, target); 
	
	
	var url11 = "<%=path%>/web/fybzgl/fybzgliframeRight01.jsp?bz=1";
	var target11='treeMap2';
	window.open(url11, target11); 
    
}
//for(var i=0;i<data.length;i++)  
//*对于java方法的返回值为List(Set)的情况，DWR将其转     化为Object数组，传递个javascript*/
//{

//alert(data[i]);
//}

$(function() {
	$("#query").click(function() {

		chaxun();
	});
})
</script>
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
	<body class="body" style="overflow-x: hidden;">
	<div>
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
				<tr>
                        
					<td colspan="4" width="50">
						<div style="width: 700px;">
							<img alt="" src="<%=path%>/images/btt.bmp" width="100%"
								height="100%" />
							<span
								style="font-size: 14px; font-weight: bold; position: absolute; left: 25px; top: 15px; color: black">站点查询</span>
						</div>
					</td>

				</tr>
				<tr>
					<td height="20" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;过滤条件&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="1200px">
						<table>
							<tr class="form_label">
								<td>
									报账月份：
								</td>
								<td>
									<input type="text" name="beginTime"
										value="<%if (null != request.getParameter("beginTime"))
				out.print(request.getParameter("beginTime"));%>"
										onFocus="getDatenyString(this,oCalendarChsny)"
										class="selected_font" />
								</td>
								<td>
									至
								</td>
								<td>
									<input type="text" name="endTime"
										value="<%if (null != request.getParameter("endTime"))
				out.print(request.getParameter("endTime"));%>"
										onFocus="getDatenyString(this,oCalendarChsny)"
										class="selected_font" />
									<span class="style1">&nbsp;*</span>
								</td>

								<td>
									城市：
								</td>
								<td>
									<select name="shi" class="selected_font1"
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
								</td>
								<td>
									费用属性：
								</td>
								<td>
									<select name="feiyongtype" 
										class="selected_font1">
										<option value="0">
											请选择
										</option>
										<option value="-1">
											收入
										</option>
										<option value="1">
											支出
										</option>
									</select>
								</td>
								<td>
									支付类型：
								</td>
								<td>
									<select name="zflx" 
										class="selected_font1">
										<option value="0">
											请选择
										</option>
										<option value="1">
											月结
										</option>
										<option value="-1">
											预支
										</option>
									</select>
								</td>
								<td>
									<div id="query"
										style="position: relative; width: 59px; height: 23px; right: -60px; cursor: pointer; TOP: 0PX">
										<img alt=""
											src="<%=request.getContextPath()%>/images/chaxun.png"
											width="100%" height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
									</div>
								</td>

							</tr>
						</table>
					</td>
				</tr>

			</table>
			<table height="23">
				<tr>
					<td height="5" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;信息列表&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<div style="width:100%;height:180px;overflow-x:auto;overflow-y:auto;border:1px" >
            <table >
			  <tr>
			   <td rowspan="6">
			     <table width="100%" border="0" cellspacing="1" cellpadding="1">
				   <tr class="relativeTag">
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							地区
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							总条数
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							总费用
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							财务总费用
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							一级分摊比例
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							二级分摊比例
						</div>
					</td>
				</tr>
			        <tr class="relativeTag">
			        <td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="dshi">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="dcount">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="dmonery">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="dcwmonery">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="dyiji">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="derji">
							
						</div>
					</td>
			        </tr>
			        <tr class="relativeTag">
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
					      网络运营线电费
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							信息化支撑
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							行政综合
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							市场营销
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							建设投资
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							代垫电费
						</div>
					</td>
				</tr>
			        <tr class="relativeTag">
			        <td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="dyj01">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="dyj02">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="dyj03">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="dyj04">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="dyj05">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="dyj06">
							
						</div>
					</td>
			        </tr>
			        <tr class="relativeTag">
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
					      移动专业-2G
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							移动专业-3G
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							固网专业
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							固移共同专业
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							移动共同专业
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							不可分摊专业
						</div>
					</td>
				</tr>
			        <tr class="relativeTag">
			        <td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="derj01">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="derj02">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="derj03">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="derj04">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="derj05">
							
						</div>
					</td>
					<td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn" id ="derj06">
							
						</div>
					</td>
			        </tr>
			      </table>
			   </td>
			   <td>|</td>
			   <td>
			   <table>
			   <tr class="relativeTag">
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							站点属性
						</div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="zd0"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="zd1"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="zd2"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="zd3"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="zd4"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="zd5"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="zd6"></div>
					</td>
					</tr>
					<tr>
					 <td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn">
							 
						</div>
					</td>
					</tr>
					<tr class="relativeTag">
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							费用
						</div>
					</td>
					<td class="form_label" bgcolor="#DDDDDD">
					<div id="zdm0"></div>
					</td>
					<td class="form_label" bgcolor="#DDDDDD">
					<div id="zdm1"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="zdm2"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="zdm3"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="zdm4"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="zdm5"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="zdm6"></div>
					</td>
					</tr>
				</table>
			   </td>
			  </tr>
			   <tr>
			   <td>|</td>
			   <td>
                  <table>
			   <tr class="relativeTag">
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							供电方式
						</div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="gd0"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="gd1"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="gd2"></div>
					</td>
					
					</tr>
					<tr>
					 <td bgcolor="#FFFFFF" class="form_label">
						<div align="center" class="bttcn">
							 
						</div>
					</td>
					</tr>
					<tr class="relativeTag">
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							费用
						</div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="gdm0"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="gdm1"></div>
					</td>
					<td class="form_label"bgcolor="#DDDDDD">
					<div id="gdm2"></div>
					</td>
					</tr>
				</table>
			   </td>
			  </tr>
			  
			</table>
</div>

			<!-- 根据条件得到 月结,预支的bean-->


		</form>
		<iframe name="treeMap1" width="400px" height="200" frameborder="0"
			src="<%=path%>/web/fybzgl/fybzgliframeLeft.jsp"></iframe>
		<span style="width: 20px"></span>
		<iframe name="treeMap2" width="400px" height="200px" frameborder="0"
			src="<%=path%>/web/fybzgl/fybzgliframeRight01.jsp"></iframe>
		<span style="width: 20px"></span>
		<iframe name="2" width="400px" height="200px" frameborder="0"
			src="<%=path%>/web/fybzgl/Copy of fybzgliframeLeft.jsp">
	    </iframe>
		
			</div>
	</body>
</html>
