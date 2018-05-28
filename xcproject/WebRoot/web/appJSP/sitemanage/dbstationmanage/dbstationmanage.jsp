<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean"%>
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
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : agcode1;//市
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";//区县
	String xiaoqu = request.getParameter("xiaoqu") != null ? request
			.getParameter("xiaoqu") : "0";//乡镇
	String jzproperty = request.getParameter("jzproperty") != null ? request
			.getParameter("jzproperty")
			: "0";//站点属性
	String zdid = request.getParameter("zdid") != null ? request
			.getParameter("zdid"):"";//站点id
	String stationtypea = request.getParameter("stationtype") != null ? request
					.getParameter("stationtype"):"0";//站点类型
	String color;//颜色
	int intnum = request.getAttribute("num") != null ? (Integer) request
			.getAttribute("num")
			: 0;//条数 ,查出数据的条数，用于颜色设置
%>

<html>
	<head>
		<title>多表站点维护</title>
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
    //审核通过
function passCheck() {//通过
	var m = document.getElementsByName('test[]');
	var arr = new Array;
	var l = m.length;//总复选框个数
	var bz = 0;
	var n = 0;
	var count = 0;//选中复选框个数
	var count1 = 0;
	var bzw = 1;
	var bzw1 = "";
	var chooseIdStr1 = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
	}
	if (count != 0) {
		if (count % 240 == 0) {
			n = count / 240 - 1;
		} else {
			n = (count - (count % 240)) / 240;
		}
		var r = 240 * n;
		for ( var i = 0; i < l; i++) {
			if (m[i].checked == true) {
				bz += 1;
				count1 += 1;
				var chooseIdStr3 = m[i].value.toString();
				chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
	
			}
			var t = bz;
			bzw1 = document.form1.bzw1.value;
			if (bzw1 == "1") {
				if (count1 <= r || (t >= 239 && t <= 241) {//满240条
					if ((t / 240 == 1) || (t >= 239 && t <= 241)) {
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						passCheck1(chooseIdStr1,'checkpass1');
						chooseIdStr1 = "";
						bz = 0;

					}
				} else if (count == count1 && bzw == 1) {//不足240条，且ajax部分已通过
					chooseIdStr1 = chooseIdStr1.substring(0,
							chooseIdStr1.length - 1);
					bzw = 0;
					document.form1.action = path
							+ "/servlet/DbStationManageServlet?command=checkpass&chooseIdStr1="
							+ chooseIdStr1 ;
					document.form1.submit();
				}
			} else if (bzw1 == "0") {
				document.form1.action = path + "/web/msg.jsp";
				document.form1.submit();
				return;
			}
		}
	} else {
		alert("请选择信息！");
	}
}
    
//取消审核
function passCheckNoPass() {
	var m = document.getElementsByName('test[]');
	var arr = new Array;
	var l = m.length;//总复选框个数
	var bz = 0;
	var n = 0;
	var count = 0;//选中复选框个数
	var count1 = 0;
	var bzw = 1;
	var bzw1 = "";
	var chooseIdStr1 = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
	}
	if (count != 0) {
		if (count % 240 == 0) {
			n = count / 240 - 1;
		} else {
			n = (count - (count % 240)) / 240;
		}
		var r = 240 * n;
		for ( var i = 0; i < l; i++) {
			if (m[i].checked == true) {
				bz += 1;
				count1 += 1;
				var chooseIdStr3 = m[i].value.toString();
				chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
	
			}
			var t = bz;
			bzw1 = document.form1.bzw1.value;
			if (bzw1 == "1") {
				if (count1 <= r || (t >= 239 && t <= 241) {//满240条
					if ((t / 240 == 1) || (t >= 239 && t <= 241)) {
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						passCheckNo2(chooseIdStr1,'checkquxiao1');
						chooseIdStr1 = "";
						bz = 0;

					};
				} else if (count == count1 && bzw == 1) {//不足240条，且ajax部分已通过
					chooseIdStr1 = chooseIdStr1.substring(0,
							chooseIdStr1.length - 1);
					bzw = 0;
					document.form1.action = path
							+ "/servlet/DbStationManageServlet?command=checkquxiao&chooseIdStr1="
							+ chooseIdStr1 ;
					document.form1.submit();
				}
			} else if (bzw1 == "0") {
				document.form1.action = path + "/web/msg.jsp";
				document.form1.submit();
				return;
			}
		}
	} else {
		alert("请选择信息！");
	}
}

function queryDegree(command) {
	if (document.getElementById("shi").value == ""
			|| document.getElementById("shi").value == "0"
			|| document.getElementById("shi").value == null) {
		alert("城市不能为空");
	} else {
		var zdid = document.getElementById("zdid").value;
		var zdidnull = zdid.replace(/[ ]/g,"");
		if(zdidnull==""){
		document.form1.action = path + "/servlet/DbStationManageServlet?command="
				+ command;
		document.form1.submit();
		if ("chaxun" == command) {
			showdiv("请稍等..............");
		}
		}else if(parseInt(zdid)==zdid){
		document.form1.action = path + "/servlet/DbStationManageServlet?command="
				+ command;
		document.form1.submit();
		if ("chaxun" == command) {
			showdiv("请稍等..............");
		}
		}else{
			alert("站点ID必须是数字!");
		}

	}
}
$(function() {

	$("#tongguo").click(function() {
		passCheck();//通过
		});
	$("#butongguo").click(function() {
		passCheckNoPass();//不通过
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
	<body class="body">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
	 			<tr>
      				<td colspan="4" width="50">
						<div style="width:700px;height:50px">
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">多表站点维护</span>	
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
									<select name="shi" class="selected_font" id="shi"
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
								站点属性：
							</td>
							<td>
								<select name="jzproperty" class="selected_font"
									 onchange="zdsx()">
									<option value="0">
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
				<p id="box3" style="display: none">
					<table>
						<tr class="form_label">
							<td>
								站点名称：
							</td>
							<td>
								<input type="text" name="zdname"
									value="<%if (null != request.getParameter("zdname"))
				out.print(request.getParameter("zdname"));%>"
									class="selected_font" />
							</td>
							<td>站点ID：</td>
         <td><input type="text" name="zdid" value="<%=zdid%>" style="width:130px" class="selected_font"/></td>
         <td>站点类型：</td>
            <td>
         	<select name="stationtype"  class="selected_font">
         		<option value="0">请选择</option>
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

			<div style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="100%" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label"
					style=" width: 100%; "
    				id="MyTable">
					<tr height="23" class="relativeTag">
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<input type="checkbox" name="test" onclick="chooseAll()" />
								全选
							</div>
						</td>
						<td width="10%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								城市
							</div>
						</td>
						<td width="19%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="13%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点ID
							</div>
						</td>
						<td width="15%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点属性
							</div>
						</td>
						<td width="15%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						<td width="15%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								供电方式
							</div>
						</td>
					<c:forEach items="${list}" var="list" varStatus="status">
					<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
						<td>
							<div align="center">
								${status.count}
							</div>
						</td>
						<td>
							<div align="center">
								<input type="checkbox" name="test[]"
									value="${list.zdid}" />
							</div>
						</td>
						<td>
							<div align="center">
								${list.city}
							</div>
						</td>
						<td>
							<div align="left">
								${list.zdname}
							</div>
						</td>
						<td>
							<div align="right">
								${list.zdid}
							</div>
						</td>
						<td>
							<div align="right">
								${list.property}
							</div>
						</td>
						<td>
							<div align="right">
								${list.stationtype}
							</div>
						</td>
						<td>
							<div align="right">
								${list.gdfs}
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
						<div id="butongguo"
							style="position: relative; width: 105px; height: 23px; cursor: pointer; float: right; right: 10px">
							<img alt="" src="<%=request.getContextPath()%>/images/mmcz.png"
								width="100%" height="100%" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">取消多表状态</span>
						</div>
						<div id="tongguo"
							style="width: 115px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
							<img src="<%=path%>/images/2chongzhi.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 35px; top: 5px; color: white">允许多表状态</span>
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
	if (para == "checkpass1") {
		XMLHttpReq1.onreadystatechange = processResponse_checkcity1;
	} else if (para == "checkquxiao1") {
		XMLHttpReq1.onreadystatechange = processResponse_checkcityno1;
	}else if(para=="jzproperty"){
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
	shilist.add(new Option("请选项","0"));
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
function passCheck1(chooseIdStr1,para) {//通过240
	sendRequest1(path
			+ "/servlet/DbStationManageServlet?command=checkpass1&chooseIdStr1="
			+ chooseIdStr1,para);
}
function passCheckNo2(chooseIdStr1,para) {//取消审核1
	sendRequest1(path
			+ "/servlet/DbStationManageServlet?command=checkquxiao1&chooseIdStr1="
			+ chooseIdStr1,para);
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
	var qm = document.getElementsByName('test');
	var m = document.getElementsByName('test[]');
	var l = m.length;
	if (qm[0].checked == true) {
		for ( var i = 0; i < l; i++) {
			m[i].checked = true;
		}
	} else {
		for ( var i = 0; i < l; i++) {
			m[i].checked = false;
		}
	}
}
//回显
document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
document.form1.jzproperty.value = '<%=jzproperty%>';
document.form1.stationtype.value = '<%=stationtypea%>';
</script>
</html>

