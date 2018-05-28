<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page import="com.noki.mobi.common.Account"%>
<%@ page import="com.noki.zwhd.manage.SystemManage" %>
<%@ page import="com.noki.zwhd.model.ZhandianBean" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.noki.jichuInfo.DlFxmanage"%>
<%@ page import="com.noki.jichuInfo.DianLiangBean" %>
<%@ page import=" com.noki.biaogan.BiaoganManage"%>
<%@ page import=" com.noki.biaogan.model.BiaoganBean"%>
<%
	String path = request.getContextPath();
	String loginName = (String) session.getAttribute("loginName");
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String jztype1 = request.getParameter("jztype") != null ? request
					.getParameter("jztype") : "0";
	String jzproperty1 = request.getParameter("jzproperty") != null
					? request.getParameter("jzproperty")
					: "0";
	String bgsign = request.getParameter("bgsign") != null ? request
					.getParameter("bgsign") : "-1";
	String qyzt = request.getParameter("qyzt") != null ? request
					.getParameter("qyzt") : "-1";//站点启用状态
	String caijid = request.getParameter("caijidian") != null ? request
					.getParameter("caijidian") : "-1";
	String sitetype = request.getParameter("stationtype") != null
					? request.getParameter("stationtype")
					: "0";
	String sheng = (String) session.getAttribute("accountSheng");
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
	String shi = request.getParameter("shi") != null ? request
					.getParameter("shi") : agcode1;
	String xian = request.getParameter("xian") != null ? request
					.getParameter("xian") : "0";
	String xiaoqu = request.getParameter("xiaoqu") != null ? request
					.getParameter("xiaoqu") : "0";
	String rgsh = request.getParameter("rgsh");//站点审核通过   首页传的值
	String rgsh2 = request.getParameter("caiji");// 采集站点
	String s_curpage = request.getParameter("curpage") != null
					? request.getParameter("curpage")
					: "1";//分页
	int count = 0, pagesize = 10, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String permissionRights = "";
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	String yearmonth = sdf.format(new Date());
	
	String jzcode = request.getParameter("jzcode");
	System.out.println(jzcode);
	String year = request.getParameter("year");
	System.out.println("城市：" + shi + "区县：" + xian + "站点编号：" + jzcode + " 年份：" + year);
	String resultString = "[";
	DlFxmanage manage = new DlFxmanage();
	BiaoganManage bianganManage = new BiaoganManage();
	if (jzcode != null && !jzcode.equals("") && !jzcode.equals("null")&& !jzcode.equals("0")) {
		List<DianLiangBean> biaogans = manage.searchBiaoganChartByZdid(jzcode,year);
		String zdValue = "[";
		for (int j = 0; j < biaogans.size(); j++) {
			DianLiangBean biaogan = biaogans.get(j);
			String blhdl = biaogan.getBlhdl();
			zdValue += blhdl + ",";
		}
		zdValue = zdValue.substring(0, zdValue.length() - 1);
		zdValue += "]";
		String zd = "";
		zd += "{name:'电量值',";
		zd += "data:" + zdValue;
		zd += "}";
		resultString+=zd+"]";
	}else{
		
		SystemManage systemManage = new SystemManage();
		List<ZhandianBean> zhandianList = systemManage.searchZhandianyd(shi,xian);
		String zd = "";
		for (int i = 0; i < zhandianList.size(); i++) {
			String _zdbm = zhandianList.get(i).getJZCODE();
			String _zdmc = zhandianList.get(i).getJZNAME();
			List<DianLiangBean> biaogans = manage.searchBiaoganChartByZdid(_zdbm, year);
			String zdValue = "[";
			for (int j = 0; j < biaogans.size(); j++) {
				DianLiangBean biaogan = biaogans.get(j);
				String blhdl = biaogan.getBlhdl();
				zdValue += blhdl + ",";
			}
			zdValue = zdValue.substring(0, zdValue.length() - 1);
			zdValue += "]";
			
			zd += "{name:'" + _zdmc + "',";
			zd += "data:" + zdValue;
			zd += "},";
		}
		
		if(zd.length()>0){
			zd = zd.substring(0, zd.length() - 1);
		}
		
		resultString += zd;
		resultString += "]";
		System.out.println(resultString);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var path = "<%=path%>";
function changeChart(){
	var sid = document.form1.shi.value;
	var qxid = document.form1.xian.value;
	var zdid = document.form1.jzcode.value;
	var smc = $("#shi option:selected").text();
	if(sid=="0"){
		smc = "";
	}
	var qxmc = $("#xian option:selected").text();
	if(qxid=="0"){
		qxmc = "";
	}
	
	var zdmc = $("#jzcode option:selected").text();
	if(zdid=="0"){
		zdmc = "";
	}
	var year = document.form1.year.value;
	$("#container").highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Monthly Average Rainfall'
        },
        subtitle: {
            text: 'Source: WorldClimate.com'
        },
        xAxis: {
            categories: [
                'Jan',
                'Feb',
                'Mar',
                'Apr',
                'May',
                'Jun',
                'Jul',
                'Aug',
                'Sep',
                'Oct',
                'Nov',
                'Dec'
            ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Rainfall (mm)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Tokyo',
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
        }, {
            name: 'New York',
            data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]
        }, {
            name: 'London',
            data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]
        }, {
            name: 'Berlin',
            data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1]
        }]
    });
}

function searchBiaoganChart(){
	document.form1.action = path+"/web/sdttWeb/DianliangManager/dianliangFengxiChart.jsp";
	showMsg("正在查询请稍后");
	document.form1.submit();
}
    
$(document).ready(function(){
	changeChart();
});
</script>
<style>
#H-dialog {
	display: none;
	position: absolute;
	z-index: 9999999;
	width: 400px;
	height: auto;
	background-color: #fff;
}

#H-dialog .close {
	float: right;
	font-size: 30px;
	margin-right: 10px;
	margin-top: 5px;
	cursor: pointer;
}

#H-dialog .title {
	height: 40px;
	padding-left: 10px;
	font-size: 20px;
	line-height: 40px;
}

#H-dialog #msgCont {
	height: 36px;
	margin: 30px 0 50px;
	padding-left: 65px;
	font-size: 25px;
	line-height: 36px;
	vertical-align: middle;
	background: url(../Images/ui_alert.png) no-repeat 20px 50%;
}
</style>
<div id="H-dialog">
	<a class="close" onclick="popupClose(this)">×</a>
	<div class="title">提示</div>
	<div id="msgCont">内容</div>
</div>

<script type="text/javascript">
    //锁定背景屏幕
    function lockScreen() {
        var clientH = document.body.offsetHeight; //body高度
        var clientW = document.body.offsetWidth; //body宽度
        var docH = document.body.scrollHeight; //浏览器高度
        var docW = document.body.scrollWidth; //浏览器宽度
        var bgW = clientW > docW ? clientW : docW; //取有效宽
        var bgH = clientH > docH ? clientH : docH; //取有效高
        var blackBg = document.createElement("div");
        blackBg.id = "blackBg";
        blackBg.style.position = "absolute";
        blackBg.style.zIndex = "99999";
        blackBg.style.top = "0";
        blackBg.style.left = "0";
        blackBg.style.width = bgW+"px";
        blackBg.style.height = bgH+"px";
        blackBg.style.opacity = "0.4";
        blackBg.style.backgroundColor = "#333";
        document.body.appendChild(blackBg);
    }
    //关闭按钮事件
    function popupClose(el) {
        var blackBg = document.getElementById("blackBg");
        blackBg && document.body.removeChild(blackBg);
        el.parentNode.style.display = "none";
    }
    //自动关闭
    function autoClose(id) {
        id = id || "H-dialog";
        var blackBg = document.getElementById("blackBg");
        var objDiv = document.getElementById(id);
        setTimeout(function(){
            blackBg && document.body.removeChild(blackBg);
            objDiv.style.display = "none";
        },1000*5);
    }
    /**
    *功能 : 弹窗信息
    *参数1 : 提示信息内容
    *参数2 : 提示信息状态默认0 为提示信息,1为成功信息
    *参数3 : 弹窗div的id,默认"H-dialog"
    *参数4 : 弹窗内容的id,默认"msgCont"
    **/
    function showMsg(msg) {
        msg = msg || "请重新操作";
        var status = arguments[1] || 0,
        popupId = arguments[2] || "H-dialog",
        contentId = arguments[3] || "msgCont";       
        lockScreen();
        //屏幕实际高宽
        var pageWidth = window.innerWidth;
        var pageHeight = window.innerHeight;
        if (typeof pageWidth != "number") {
            if (document.compatMode == "CSS1Compat") {
                pageWidth = document.documentElement.clientWidth;
                pageHeight = document.documentElement.clientHeight;
            } else {
                pageWidth = document.body.clientWidth;
                pageHeight = document.body.clientHeight;
            }
        }
        //滚动条高宽
        var scrollLeft = window.document.documentElement.scrollLeft;
        var scrollTop = 0;
        if (typeof window.pageYOffset != 'undefined') {
            scrollTop = window.pageYOffset;
        } else if (typeof window.document.compatMode != 'undefined' &&
            window.document.compatMode != 'BackCompat') {
            scrollTop = window.document.documentElement.scrollTop;
        } else if (typeof window.document.body != 'undefined') {
            scrollTop = window.document.body.scrollTop;
        }

        var div_X = (pageWidth - 400) / 2 + scrollLeft;
        var div_Y = (pageHeight - 200) / 2 + scrollTop;
        var objDiv = document.getElementById(popupId);
        if (status) {
            document.getElementById(contentId).style.background = "url($Root/Assets/Images/ui_success.png) no-repeat 20px 50%";
        }
        document.getElementById(contentId).innerHTML = msg;
        objDiv.style.display = "block";
        objDiv.style.left = div_X + "px";
        objDiv.style.top = div_Y + "px";
        autoClose(popupId);
    }
</script>
</head>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page"
	class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
<ul class="tab">
	  <li class="first"></li>
	  <li ><a href="dianliangFengxiChart.jsp">全年各站点电量走势分析</a></li>
	  <li class="cur"><a href="dianliangFengxiChart2.jsp">某月全站点电量比较</a></li>
	  <li ><a href="dianliangFengxiChart3.jsp">超标杆比例分析</a></li>
	  <li class="end"></li>
</ul>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">某月全站点电量比较数据分析柱状图</div>
				<div class="content01_1">
					<table width="100%" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr >
							<td>
								城市&nbsp; <select name="shi" id="shi"
									style="width:80px;" onchange="changeCity()">
										<option value="0">请选择</option>
										<%
											ArrayList shilist = new ArrayList();
											shilist = commBean.getAgcode(sheng, shi, loginName);
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
							</select>&nbsp;&nbsp;&nbsp; 区县&nbsp; 
							     <select name="xian" id="xian"
									onchange="changeZd()" style="width:80px;">
										<option value="0">请选择</option>
										<%
											ArrayList xianlist = new ArrayList();
											xianlist = commBean.getAgcode(shi, xian, loginName);
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
								</select>&nbsp;&nbsp;&nbsp;
								基站名称：
								<select name="jzcode" id="jzcode" style="width:160px;">
									<option value="0">请选择</option>
									<%
										SystemManage systemManage = new SystemManage();
										List<ZhandianBean> jzList = systemManage.searchZhandianyd(shi,xian);
										for (int i=0;i<jzList.size();i++) {
											ZhandianBean zhandian = jzList.get(i);	
									%>
										<option value="<%=zhandian.getID()%>"><%=zhandian.getJZNAME()%></option>
									<%
										}
									%>
								</select>
								&nbsp;&nbsp;
								年份:&nbsp;&nbsp;<input type="text" id="year" name="year" value="<%=yearmonth %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy'})" style="width:80px;"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<input id="biaoganChartSubmit" name="biaoganChartSubmit" type="button" class="btn_c1"  value="输出图表" onclick="searchBiaoganChart()"/>&nbsp; 
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="5" cellpadding="0">
						  <tr align="center">
						    <td width="50%" class="border01">
						    <script src="../highcharts/js/highcharts.js"></script>
								<script src="../highcharts/js/modules/exporting.js"></script>
								<div id="container" style="min-width: 480px; height: 340px; margin: 0 auto"></div>
								</div>
								<br>
								<div id="container1" style="min-width: 480px; height: 30px; margin: 0 auto;text-align:left">
								</div>
						    </td>
						  </tr>
						</table>
						<div class="space_h_10"></div>
				</div>
			</div>
		</td></tr></table>
		</form>
</body>
</html>
<script>
	var path = '<%=path%>';
	function gopage()
     {
      document.form1.submit();
     }
     function previouspage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage - 1)%>';
      		document.form1.action=path+"/web/sdttWeb/BiaoganManager/biaoganHistory.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      		document.form1.action=path+"/web/sdttWeb/BiaoganManager/biaoganHistory.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = pageno;   
      document.form1.action=path+"/web/sdttWeb/BiaoganManager/biaoganHistory.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+pageno;
      document.form1.submit();
     }
     function delzd(id){
    // alert(id);
     if(confirm("您确定删除此站点信息？")){
     	//<if (permissionRights.indexOf("PERMISSION_DELETE") >= 0) {%> //权限
     		document.form1.action=path+"/servlet/zhandian?action=delsite&id="+id;
      		document.form1.submit();
      //<} else {%>
      //alert("您没有删除站点的权限");
    //<}%>
    }
    }
    function xiangxi(id){
    	window.open("../jizan/xxquery.jsp?id="+id, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
    }
    function dfinfo(id){
    
    	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/dfinfo.jsp?id="+id;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }
    function zlinfo(id){
    	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/zlinfo.jsp?id="+id;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }

    function modifyjz(id,intnum){
    //alert(intnum);
  	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/modifysite.jsp?id="+id+"&nums="+intnum;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
       
    }
    function getValue(va,sql){
       var general =document.getElementById("general");
       var htmlsql =document.getElementById("htmlsql");
       general.value=va;
       htmlsql.value = sql;    
    }
    //页面载入方法
    function op(){
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian,ammeters,ammeterdegrees,electricfees&tab=zd,am,ad,ef&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
    }

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
	
	///////////////////////////////////////////////////////////
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
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZd(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}

function changeSheng(){
	var sid = document.form1.sheng.value;

	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
	 //alert("11111");
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

	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}

function changeZd(){
	document.form1.action=path+"/web/sdttWeb/DianliangManager/dianliangFenxiChart.jsp";
	showMsg("正在查询请稍后");
	document.form1.submit();
}

function updateZd(res){
	var jzlist = document.all.jzcode;
	jzlist.options.length="0";
	jzlist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		jzlist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function addjz(){
	window.open("../jizan/addSite.jsp", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}
function update(id){
	window.open("../jizan/UpdateSite.jsp?id="+id, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}
function query(){
	document.form1.action=path+"/web/sdttWeb/BiaoganManager/biaoganHistory.jsp";
	showMsg("正在查询请稍后");
}

document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.jzcode.value='<%=jzcode%>';
</script>
