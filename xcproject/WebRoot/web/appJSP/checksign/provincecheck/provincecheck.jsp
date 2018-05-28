<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();//项目路径
	Account account = (Account) session.getAttribute("account");//账户
	String sheng = (String) session.getAttribute("accountSheng");//省
	//省市县三级联动参数
	String agcode1 = "";
	if (request.getParameter("shi") == null) {
		List shilist = new ArrayList();
		CommonBean commBean = new CommonBean();
		shilist = commBean.getAgcode(sheng, account.getAccountId());
		if (shilist != null) {
			int scount = ((Integer) shilist.get(0)).intValue();
			agcode1 = (String) shilist.get(scount
					+ shilist.indexOf("AGCODE"));
		}
	}
	//回显字段
	String shi = request.getParameter("shi") != null ? request.getParameter("shi") : agcode1;//市
	String xian = request.getParameter("xian") != null ? request.getParameter("xian") : "0";//区县
	String xiaoqu = request.getParameter("xiaoqu") != null ? request.getParameter("xiaoqu") : "0";//乡镇
	String property = request.getParameter("property") != null ? request.getParameter("property"): "0";//站点属性
	String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";//站点类型


	String color;//颜色
	int intnum = request.getAttribute("num") != null ? (Integer) request.getAttribute("num"): 0;//条数 ,查出数据的条数，用于颜色设置
	if (intnum % 2 == 0) {
		color = "#DDDDDD";
	} else {
		color = "#FFFFFF";
	}
%>

<html>
	<head>
		<title>省级新增站点审核</title>

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

function queryDegree(command) {
	if (document.getElementById("shi").value == ""
			|| document.getElementById("shi").value == "0"
			|| document.getElementById("shi").value == null) {
		alert("城市不能为空");
	} else {
		document.form1.action = path + "/servlet/ProvCheckServlet?command=" + command;
		document.form1.submit();
		if ("chaxun" == command) {
			showdiv("请稍等..............");
		}
	}
}

function check(shsign){
     
   	var i = 0;
	for(var j = 0;j < document.form1.elements.length ; j++){
		if (document.form1.elements[j].checked){
			i++;
		}
	}
	if(i>0){
		document.form1.action=path+"/servlet/ProvCheckServlet?command=shenhe&shsign="+shsign;
		document.form1.submit();
		showdiv("请稍等..............");
	}else{
		alert("请选择要审核的站点");
		return;
	}
}

$(function() {
	$("#tongguo").click(function() {
		check('1');//通过
	});
	$("#butongguo").click(function() {
		check('2');//不通过
	});
	$("#chaxun").click(function() {
		queryDegree("chaxun");//查询
	});
});
</script>

	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
	 			<tr>
      				<td colspan="4" width="50">
						<div style="width:700px;height:50px">
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">省级新增站点审核</span>	
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
									<select name="shi" id="shi" class="selected_font"
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
												int size = xianlist.size() - 1;
												for (int i = scount; i < size; i += scount) {
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
									乡镇：
								</td>
								<td>
									<select name="xiaoqu" class="selected_font" onchange="">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList xiaoqulist = new ArrayList();
											xiaoqulist = commBean.getAgcode(xian, account.getAccountId());
											if (xiaoqulist != null) {
												String agcode = "", agname = "";
												int scount = ((Integer) xiaoqulist.get(0)).intValue();
												int size = xiaoqulist.size() - 1;
												for (int i = scount; i < size; i += scount) {
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
								<td>
									站点名称：
								</td>
								<td>
									<input type="text" name="zdname"
										value="<%if (null != request.getParameter("zdname"))
													out.print(request.getParameter("zdname"));%>"
										class="selected_font" />
								</td>
								<td>
									<p>
										<font size="2">
											<div title="您可以进行详细的条件筛选" id="query1"
												onclick="openShutManager(this,'box3',false)"
												style="position: relative; width: 17px; height: 17px; cursor: pointer; top: 10PX">
												<img alt=""
													src="<%=request.getContextPath()%>/images/gaojichaxun.gif"
													width="100%" height="100%" />
												<span
													style="font-size: 12px; position: absolute; left: 2px; top: 0px; color: white">&nbsp;&nbsp;&nbsp;</span>
											</div> </font>
									</p>
								</td>
								<td>

									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -95px; float: right; top: 0px">
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
				<p id="box3" style="display: none">
					<table>
						<tr class="form_label">
							<td>站点ID：</td>
                        	<td><input type="text" name="zdid" class="selected_font" value="<%if(null!=request.getParameter("zdid")) out.print(request.getParameter("zdid")); %>"/>
                        	</td>
							<td>
								站点属性：
							</td>
							<td>
								<select name="property" class="selected_font"
										onchange="zdsxCheck(this.value)">
									<option value="0">
											请选择
									</option>
									<%
										ArrayList zdsx = new ArrayList();
										zdsx = ztcommon.getSelctOptions("ZDSX");
										if (zdsx != null) {
											String code = "", name = "";
											int cscount = ((Integer) zdsx.get(0)).intValue();
											for (int i = cscount; i < zdsx.size() - 1; i += cscount) {
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
                            <td>
								站点类型：
							</td>
							<td>
								<select name="zdlx" class="selected_font">
									<option value="0">
											请选择
									</option>
									<%
										ArrayList stationtype = new ArrayList();
										stationtype = ztcommon.getZdlx(property);
										//stationtype = ztcommon.getSelctOptions("StationType");
										if (stationtype != null) {
											String code = "", name = "";
											int cscount = ((Integer) stationtype.get(0)).intValue();
											int size = stationtype.size() - 1;
											int i;
											for (i = cscount; i < size; i += cscount) {
												code = (String) stationtype.get(i
													+ stationtype.indexOf("CODE"));
												name = (String) stationtype.get(i
													+ stationtype.indexOf("NAME"));
												if(zdlx!=null&&!"".equals(zdlx)){
									%>
									<option value="<%=code%>"><%=name%></option>
									<%
											}
										}
									}
									%>
								</select>
							</td>
						</tr>
					</table>
				</p>
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
			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="100%" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<input type="checkbox" name="test" onclick="chooseAll()" />
								全选
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								城市
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点属性
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								直流负荷
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								交流负荷
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								额定耗电量
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								供电方式
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								共享信息
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								乡镇
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点ID
							</div>
						</td>
					</tr>

					<c:forEach items="${list}" var="list" varStatus="status">
						<tr bgcolor="${(list.provauditstatus)=='0'?'#FFFF33':'#FF3333'}">
							<td>
								<div align="center">
									${status.count}
								</div>
							</td>
							<td>
								<div align="center">
									<input type="checkbox" name="itemSelected"
										value="${list.zdid}" />
								</div>
							</td>
							<td>
								<div align="center">
									${list.city}
								</div>
							</td>
							<td>
								<div align="center">
									${list.zdname}
								</div>
							</td>
							<td>
								<div align="center">
									${list.property}
								</div>
							</td>
							<td>
								<div align="center">
									${list.stationtype}
								</div>
							</td>
							<td>
								<div align="center">
									${list.zlfh}
								</div>
							</td><td>
								<div align="center">
									${list.jlfh}
								</div>
							</td>
							<td>
								<div align="center">
									${list.edhdl}
								</div>
							</td>
							<td>
								<div align="center">
									${list.gdfs}
								</div>
							</td>
							<td>
								<div align="center">
									${list.gxmsg}
								</div>
							</td>
							<td>
								<div align="center">
									${list.xian}
								</div>
							</td><td>
								<div align="center">
									${list.town}
								</div>
							</td>
							<td>
								<div align="center">
									${list.zdid}
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
					</tr>
					<%
						}
						}
					%>
				</table>
			</div>

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
						<div id="butongguo"
							style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right: 10px">
							<img alt="" src="<%=request.getContextPath()%>/images/baocun.png"
								width="100%" height="100%" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">不通过</span>
						</div>
						<div id="tongguo"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
							<img src="<%=path%>/images/2chongzhi.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">通过</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<p >
							<font color="red" size="2">说明：省级未审核的站点显示为：黄色<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;省级不通过的站点显示为：红色</font>
						</p>
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
</script>

<script type="text/javascript">
//改变城市
function changeCity() {
	var sid = document.form1.shi.value;
	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=shi&pid=" + sid, "shi");
	}
}
//改变乡镇
function changeCountry() {
	var sid = document.form1.xian.value;
	if (sid == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=xian&pid=" + sid, "xian");
	}
}
//多选框全选
function chooseAll() {
	if(document.form1.test.checked){
     	for(var j = 0;j < document.form1.elements.length ; j++){
			document.form1.elements[j].checked=true
		}
   	}else{
   		for(var j = 0;j < document.form1.elements.length ; j++){
			document.form1.elements[j].checked=false
		}
   	}
}
//回显
document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
document.form1.property.value = '<%=property%>';
document.form1.property.value = '<%=zdlx%>';
</script>

<script type="text/javascript">
//站点属性
function zdsxCheck(obj) {
	var sid = obj;
	if (sid == "0") {
		var shilist = document.all.zdlx;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest2(path + "/servlet/garea?action=zdsx&pid=" + sid,
				"jzproperty");
	}
}

function sendRequest2(url, para) {

	createXMLHttpRequest();
	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数
	XMLHttpReq.send(null);
}

function processResponse_zdlx() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			updateZdlx(res);
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}

}

function updateZdlx(res) {

	var shilist = document.all.zdlx;
	shilist.options.length = "0";
	shilist.add(new Option("请选项", "0"));

	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
</script>
</html>

