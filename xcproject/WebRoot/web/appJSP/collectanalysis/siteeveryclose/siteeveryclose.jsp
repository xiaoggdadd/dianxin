<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean,java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();//项目路径
	Account account = (Account) session.getAttribute("account");//账户
	String sheng = (String) session.getAttribute("accountSheng");//省
	//回显字段
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : "0";//市
	String jzproperty = request.getParameter("jzproperty") != null ? request
			.getParameter("jzproperty")
			: "";//站点属性
	String zdid = request.getParameter("zdid") != null ? request
			.getParameter("zdid"):"";//站点id
	String stationtypea = request.getParameter("stationtype") != null ? request
					.getParameter("stationtype"):"";//站点类型
String datetime = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
String beginyue = (String)request.getAttribute("beginyue");
String endyue = (String)request.getAttribute("endyue");
if (null == beginyue || "null".equals(beginyue) || "".equals(beginyue)) {
	beginyue = datetime;
}
if (null == endyue || "null".equals(endyue) || "".equals(endyue)) {
	endyue = datetime;
}
	String color;//颜色
	int intnum = request.getAttribute("colornum") != null ? (Integer) request
			.getAttribute("colornum")
			: 0;//条数 ,查出数据的条数，用于颜色设置
%>

<html>
	<head>
		<title>站点每月关闭情况统计</title>
		<style>
		.relativeTag   {     
				z-index:10;   
				position:relative;     
				top:expression(this.offsetParent.scrollTop);     
		}
		</style>

		<link type="text/css" rel="Stylesheet"
			href="<%=path%>/web/appCSS/pageCss/page.css" />
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/someJs/page.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>

<script language="javascript">
var path = '<%=path%>';
	var beginyue = "${beginyue}";
	var endyue = "${endyue}";
   function lookDetails1(shi,zdsx,zdlx){
	window.open(path+"/servlet/SiteEveryClose?command=xiangqing1&shi="+shi+"&beginyue="+beginyue+"&endyue="+endyue+"&zdsx="+zdsx+"&zdlx="+zdlx);
   }
   function lookDetails2(shi,zdsx,zdlx){
	window.open(path+"/servlet/SiteEveryClose?command=xiangqing2&shi="+shi+"&beginyue="+beginyue+"&endyue="+endyue+"&zdsx="+zdsx+"&zdlx="+zdlx);
   }
   function lookDetails3(shi,zdsx,zdlx){
	window.open(path+"/servlet/SiteEveryClose?command=xiangqing3&shi="+shi+"&beginyue="+beginyue+"&endyue="+endyue+"&zdsx="+zdsx+"&zdlx="+zdlx);
   }




function queryDegree(command) {
	if(checkNotnull(document.form1.entrytimeQS,"开始月份") 
			&& checkNotnull(document.form1.entrytimeJS,"结束月份")){
		if(document.form1.entrytimeQS.value<=document.form1.entrytimeJS.value){
			document.form1.action =  path + "/servlet/SiteEveryClose?command="
				+ command;
			document.form1.submit();
			if ("chaxun" == command) {
				showdiv("请稍等..............");
			}
		}else{
			alert("开始月份不能大于结束月份!");
		}
	}

}
$(function() {
	$("#chaxun").click(function() {
		queryDegree("chaxun");//查询
		});
		$("#daochuBtn").click(function() {
		queryDegree("daochu");//导出
		});
});
</script>

	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body class="body">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
	 			<tr>
      				<td colspan="4" width="50">
						<div style="width:700px;height:50px">
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点每月关闭情况统计</span>	
						</div>
					</td>
    			</tr>
				<tr>
					<td height="30" colspan="4">
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
					<td height="8%" width="1200">
						<table>
							<tr class="form_label">
								<td>
									城市：
								</td>
								<td>
									<select name="shi" class="selected_font" id="shi">
										<option value="0">
											全部
										</option>
										<%
											ArrayList shilist = new ArrayList();
											shilist = commBean.getAgcode(sheng, account.getAccountId());
											if (shilist != null) {
												String agcode = "", agname = "";
												int scount = ((Integer) shilist.get(0)).intValue();
												int size = shilist.size() - 1;
												for (int i = scount; i < size; i += scount) {
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
								站点属性：
							</td>
							<td>
								<select name="jzproperty" class="selected_font"
									 onchange="zdsx()">
									<option value="">
										请选择
									</option>
									<%
										ArrayList zdsx = new ArrayList();
										zdsx = ztcommon.getSelctOptions("zdsx");
										if (zdsx != null) {
											String code = "", name = "";
											int cscount = ((Integer) zdsx.get(0)).intValue();
											int size = zdsx.size() - 1;
											for (int i = cscount; i < size; i += cscount) {
												code = (String) zdsx.get(i + zdsx.indexOf("CODE"));
												name = (String) zdsx.get(i + zdsx.indexOf("NAME"));
									%>
									<option value="<%=code%>"><%=name%></option>
									<%
										}
										}
									%>
								</select>
							</td>
							<td>站点类型：</td>
            <td>
         	<select name="stationtype"  class="selected_font">
         		<option value="">请选择</option>
         		<%
	         	ArrayList stationtype = new ArrayList();
         		stationtype = "0".equals(jzproperty)?ztcommon.getSelctOptions("StationType"):ztcommon.getZdlx(jzproperty);
	         	if(stationtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)stationtype.get(0)).intValue();
	         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
                    {
                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         </td>
								<td>

									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -240px; float: right; top: 0px">
										<img alt="" src="<%=path%>/images/chaxun.png" width="100%"
											height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<div style="width: 88%;">
			
					<table>
						<tr class="form_label">
         								<td>
									开始月份：
								</td>
								<td>
									<input type="text" class="selected_font" name="entrytimeQS" id = "entrytimeQS"
										value=""
										readonly="readonly" class="Wdate"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
										<font color="red">&nbsp;*</font>
								</td>
								<td>
									结束月份：
								</td>
								<td>
									<input type="text" class="selected_font" name="entrytimeJS" id="entrytimeJS"
										value=""
										readonly="readonly" class="Wdate"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
										<font color="red">&nbsp;*</font>
								</td>
       					</tr>
						
					</table>
			
		</div>

			<table>
				<tr>
					<td height="23" colspan="4">
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

			<div style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="100%" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label"
					style=" width: 100%; "
    				id="MyTable">
					<tr height="23" bgcolor="#DDDDDD" class="relativeTag ">
						<td width="1%" height="23">
							<div align="center" class="bttcn">
								城市
							</div>
						</td>
						<td width="1%" height="23">
							<div align="center" class="bttcn">
								站点属性
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								开始月份
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								结束月份
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								申请关闭总数
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								关闭总数
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								报账电费
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								报账电量
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								报账周期
							</div>
						</td>
					</tr>
					<c:forEach items="${emcsList}" var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
							<td>
								<div align="center">
									${list.cityname}
								</div>
							</td>
							<td>
								<div align="center">
									${list.siteproperty}
								</div>
							</td>
							<td>
								<div align="center">
									${list.sitetype}
								</div>
							</td>
							<td>
								<div align="center">
									${list.starttime}
								</div>
							</td>
							<td>
								<div align="center">
									${list.endtime}
								</div>
							</td>
							<td>
								<div align="center">
									<a href="javascript:lookDetails1('${list.cityid}','${list.sitepropertycode}','${list.sitetypecode}')">${list.applyclosecount}</a>
								</div>
							</td>
							<td>
								<div align="center">
									<a href="javascript:lookDetails2('${list.cityid}','${list.sitepropertycode}','${list.sitetypecode}')">${list.closecount}</a>
								</div>
							</td>
							<td>
								<div align="center">
									<a href="javascript:lookDetails3('${list.cityid}','${list.sitepropertycode}','${list.sitetypecode}')">${list.powerrate}</a>
								</div>
							</td>
							<td>
								<div align="center">
									${list.electricityquantity}
								</div>
							</td>
							<td>
								<div align="center">
									${list.cycle}
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
					</tr>
					<%
						}
						} else if (!(intnum > 17)) {
							int j = ((intnum - 1) % 17);
							int i = 0;
							for (i = j; i < 17; i++) {
								if (i % 2 == 0)
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
					</tr>
					<%
						}
						}
					%>

				</table>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					<input type="hidden" name="bzw1" id="bzw1" value="1" />
					</td>
				</tr>
				<tr bgcolor="F9F9F9">
					<td align="right" height="19" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
	 						<img src="<%=path%>/images/daochu.png" width="100%" height="100%">
							<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div id="detailsDiv"
							style="position: relative; width: 99%; height: 200px; left: 0px; top: 10px; z-index: 1; float: left; overflow-y: auto;">

							<iframe name="details" frameborder="0" width="100%" height="100%"
								scrolling="no"></iframe>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script type="text/javascript">
var path = '<%=path%>';
var XMLHttpReq1;
function createXMLHttpRequest1() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq1 = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq1 = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq1 = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}
function sendRequest1(url, para) {
	createXMLHttpRequest1();
	XMLHttpReq1.open("POST", url, true);
	if(para=="jzproperty"){
			XMLHttpReq1.onreadystatechange = processResponse_zdlx;//指定响应函数
	}else {
		XMLHttpReq1.onreadystatechange = processResponse;//指定响应函数
	}
	XMLHttpReq1.send(null);
}

	function processResponse_zdlx() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
    	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
        	var res=XMLHttpReq1.responseXML.getElementsByTagName("res");
          updateZdlx(res);
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
	}
	//站点类型
function updateZdlx(res){
	var shilist = document.all.stationtype;
	shilist.options.length="0";
	shilist.add(new Option("请选择",""));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
	   	//站点属性
	function zdsx(){
	var sid = document.form1.jzproperty.value;
	if(sid=="0"){
		sendRequest1(path+"/servlet/garea?action=zdsxnull&pid=StationType","jzproperty");
	}else{
		sendRequest1(path+"/servlet/garea?action=zdsx&pid="+sid,"jzproperty");
	}
}
// 处理返回信息函数
function processResponse() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq1.responseText;
			window.alert("nimei"+res);
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}

function processResponse_checkcity1() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			document.form1.bzw1.value = XMLHttpReq1.responseText;
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}

//响应函数
function processResponse_checkcityno1() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			document.form1.bzw1.value = XMLHttpReq1.responseText;
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}

</script>
	<script type="text/javascript">

//回显
document.form1.shi.value = '<%=shi%>';
document.form1.jzproperty.value = '<%=jzproperty%>';
document.form1.stationtype.value = '<%=stationtypea%>';
document.form1.entrytimeQS.value = '<%=beginyue%>';
document.form1.entrytimeJS.value = '<%=endyue%>';
</script>
</html>

