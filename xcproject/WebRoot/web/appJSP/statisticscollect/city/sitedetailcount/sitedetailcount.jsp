<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.ZhanDianForm,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = (String) session.getAttribute("loginName");
	
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sheng = (String) session.getAttribute("accountSheng");
	String agcode1 = "";
	if (request.getParameter("shi") == null) {
		ArrayList shilist = new ArrayList();
		CommonBean commBean = new CommonBean();
		shilist = commBean.getAgcode(sheng, account.getAccountId());
		if (shilist != null) {
			int scount = ((Integer) shilist.get(0)).intValue();
			agcode1 = (String) shilist.get(scount + shilist.indexOf("AGCODE"));
		}
	}
	String shi = request.getParameter("shi") != null ? request.getParameter("shi") : agcode1;
	String xian = request.getParameter("xian") != null ? request.getParameter("xian") : "0";
	String datetime = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
	String beginyue = (String)request.getAttribute("beginyue");
	String endyue = (String)request.getAttribute("endyue");
	if (null == beginyue || "null".equals(beginyue) || "".equals(beginyue)) {
		beginyue = datetime;
	}
	if (null == endyue || "null".equals(endyue) || "".equals(endyue)) {
		endyue = datetime;
	}

	String color = "";
	int intnum = request.getAttribute("intnum") != null ? (Integer) request.getAttribute("intnum"): 0;
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>站点明细统计</title>
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
</style>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>

<link type="text/css" rel="Stylesheet" href="<%=path%>/web/appCSS/pageCss/page.css" />
<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js">
</script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
  
<script type="text/javascript">
var path = '<%=path%>';

 function onloadtime(){
		var date = new Date();
    	var vYear = date.getFullYear();
   	 	var vMon = date.getMonth() + 1;
   	 	if(vMon<10){
   	 		vMon="0"+vMon;
   	 	}
    	var today=vYear+"-"+vMon;
		var beginyue = "${beginyue}";
		var endyue = "${endyue}";
	   if(beginyue == null || beginyue ==""){
		   document.getElementById("beginyue").innerText = today;
	   }
   }

function changemonth() {
	var bzmonth = document.form1.beginyue.value;
	document.form1.endyue.value = bzmonth;
}

function queryDegree(command) {

	if(checkNotnull(document.form1.shi,"市")&&checkNotnull(document.form1.beginyue,"起始年月")
		&&checkNotnull(document.form1.endyue,"结束年月")){
		
		var shi = document.form1.shi.value;
		var beginyue = document.form1.beginyue.value;
		var endyue = document.form1.endyue.value;
	
			if(shi!="" || shi!=null){
				if(beginyue!="" || beginyue!=null || endyue!="" || endyue!=null){
					document.form1.action = path + "/servlet/CitySiteDetailCountServlet?command="+command;
					document.form1.submit();
					if(command == "chaxun"){
			         showdiv("请稍等..............");
		            }
				}else{
					alert("请选择报账月份");
					}
			}else{
				alert("请选择市")
				}
	}
}

$(function() {
	$("#chaxun").click(function() {
		queryDegree("chaxun");//查询
	});
	$("#daochu").click(function() {
		queryDegree("daochu");//导出
	});
});
function changeCity() {
	var sid = document.form1.shi.value;
	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	}
}

//function xiangxi(cs){
//    	document.form1.action = path + "/servlet/CitySiteDetailCountServlet?command=xiangxi&cs=" + cs;
//		document.form1.submit();
//} 

function xiangxi(cs){  	
    	
	var shi = document.form1.shi.value;
	var xian = document.form1.xian.value;
	var beginyue = document.form1.beginyue.value;
	var endyue = document.form1.endyue.value;
	
	window.open(path+"/servlet/CitySiteDetailCountServlet?command=xiangxi&cs="+cs+"&shi="+shi+"&xian="+xian+"&beginyue="+beginyue+"&endyue="+endyue)
}

</script>

	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body onload = "onloadtime()">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan=1 width="700" background="<%=path%>/images/btt.bmp" height=50>
						<span style="color: black; font-weight: bold;">&nbsp;&nbsp;&nbsp;地市报账单审核统计&nbsp;&nbsp;</span>
					</td>
					<td width="700" >
					</td>
				</tr>
				<tr>
					<td colspan="4"> <div style="width:50px;display:inline;"><hr></div><font size='2'>过滤条件</font><div style="width:300px;display:inline;"><hr></div></td>
				</tr>
				<tr>
					<td width="1200px">
						<table>
							<tr class="form_label">
								<td>
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
								<td>
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
								<td>
									报账月份：
								</td>
								<td>
									<input style="width:130;height:18" type="text" name="beginyue" value="${beginyue}" onpropertychange="changemonth()" readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/>
									至
									<input style="width:130;height:18" type="text" name="endyue" value="${endyue}" readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/>
									<span class="style1">&nbsp;*</span>
								</td>
								
								<td>
									<div id="chaxun"
										style="position: relative; width: 59px; height: 23px; right: -100px; cursor: pointer; TOP: 0PX">
										<img alt=""
											src="<%=request.getContextPath()%>/images/chaxun.png"
											width="100%" height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
									</div>
								</td>
							</tr>
						</table>
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
			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="100%" height="80%" border="0" cellspacing="1" cellpadding="1"
					bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								编号
							</div>
						</td>
						
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								城市
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								审核通过站点总数
							</div>
						</td>
						
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								系统结算电表数
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								交过费用的站点数
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账条数
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账率
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								自动审核条数
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								自动审核率
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								县管理员审核条数
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								县管理员审核率
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县主任审核条数
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县主任审核率
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市管理员审核条数
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市管理员审核率
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级主任审核条数
							</div>
						</td>
						
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级主任审核率
							</div>
						</td>

						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								财务审核条数
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								财务审核率
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								打印条数
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								打印率
							</div>
						</td>
					</tr>
					
					<c:forEach items="${list}" var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
							<td>
								<div align="center">
									 <input type="hidden" name="code" value="${list.code}"/><%=intnum%>
								</div>
							</td>
							<td>
								<div align="right">
									${list.city}
								</div>
							</td>
							<td>
								<div align="right">
									${list.passzdcount}
								</div>
							</td>
							<td>
								<div align="right">
									${list.xtjsdbs}
								</div>
							</td>
							<td>
								<div align="right">
									${list.diszdsl}
								</div>
							</td>
							<td>
								<div align="right">
									${list.bzts}
								</div>
							</td>
							<td>
								<div align="right">
									${list.bzl}%
								</div>
							</td>
							<td>
								<div align="right">
									<a href="javascript:xiangxi('zd')" >${list.zdshts}</a>
								</div>
							</td>
							<td>
								<div align="right">
									${list.zdshl}%
								</div>
							</td>
							<td>
								<div align="right">
									<a href="javascript:xiangxi('xgly')" >${list.xglyshts}</a>
								</div>
							</td>
							<td>
								<div align="right">
									${list.xglyshl}%
								</div>
							</td>
							<td>
								<div align="right">
									<a href="javascript:xiangxi('qxzr')" >${list.qxzrshts}</a>
								</div>
							</td>
							<td>
								<div align="right">
									${list.qxzrshl}%
								</div>
							</td>
							<td>
								<div align="right">
									<a href="javascript:xiangxi('sgly')" >${list.sglyshts}</a>
								</div>
							</td>
							<td>
								<div align="right">
									${list.sglyshl}%
								</div>
							</td>
							<td>
								<div align="right">
									<a href="javascript:xiangxi('szr')" >${list.szrshts}</a>
								</div>
							</td>
							<td>
								<div align="right">
									${list.szrshl}%
								</div>
							</td>
							<td>
								<div align="right">
									<a href="javascript:xiangxi('cw')" >${list.cwshts}</a>
								</div>
							</td>
							<td>
								<div align="right">
									${list.cwshl}%
								</div>
							</td>
							<td>
								<div align="right">
									<a href="javascript:xiangxi('dy')" >${list.dyts}</a>
								</div>
							</td>
							<td>
								<div align="right">
									${list.dyl}%
								</div>
							</td>
						</tr>
					</c:forEach>

					<%
						if (intnum == 0) {
							for (int i = 0; i < 17; i++) {
								if (i % 2 == 0) {
									color = "#FFFFFF";
								} else {
									color = "#DDDDDD";
								}
					%>

					<tr bgcolor="<%=color%>">
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<%
						}
						} else if (!(intnum > 16)) {
							int n = ((intnum - 1) % 16);
							int j = 0;
							for (j = n; j < 16; j++) {
								if (j % 2 == 0)
									color = "#DDDDDD";
								else
									color = "#FFFFFF";
					%>
					<tr bgcolor="<%=color%>">
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>

					</tr>
					<%
						}
						}
					%>

				</table>
			</div>

			<br />
			<input type="hidden" name="shi2" id="shi2" value="" />
			<input type="hidden" name="xian2" id="xian2" value="" />
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right">
						<div id="daochu"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 4px">
							<img src="<%=path%>/images/daochu.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导出</span>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<script type="text/javascript">
var path = '<%=path%>';
function changeCountry() {
	var sid = document.form1.xian.value;
	document.form1.xian2.value = document.form1.xian.value;
	if (sid == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=xian&pid=" + sid, "xian");
	}
}
</script>
<script type="text/javascript">

var XMLHttpReq;
	
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
		
		if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		
		XMLHttpReq.send(null);  
	}

    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	
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
              	updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          	updateQx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
         	 updateZd(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
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

</script>

<script type="text/javascript">
document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
</script>
