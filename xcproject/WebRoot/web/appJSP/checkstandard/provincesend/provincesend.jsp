<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();//项目路径
	Account account = (Account) session.getAttribute("account");//账户
	String personnal=account.getAccountName();//审核人员
	String accountid = account.getAccountId();
	String sheng = (String) session.getAttribute("accountSheng");//省
	//省市县三级联动参数
	//回显字段
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : "0";//市
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";//区县
	String jzproperty = request.getParameter("jzproperty") != null ? request
			.getParameter("jzproperty")
			: "0";//站点属性
	String sjzt = request.getParameter("sjzt") != null ? request
			.getParameter("sjzt")
			: "0";//省级状态
	String bl1 = request.getParameter("bl1") != null ? request
			.getParameter("bl1")
			: "";//比例1
	String bl2 = request.getParameter("bl2") != null ? request
			.getParameter("bl2")
			: "";//比例2
	String zdid = request.getParameter("zdid") != null ? request
			.getParameter("zdid"):"";//站点id
	String color;//颜色
	int intnum = request.getAttribute("numcolor") != null ? (Integer) request
			.getAttribute("numcolor")
			: 0;//条数 ,查出数据的条数，用于颜色设置
%>

<html>
	<head>
		<title>省手动更新及派单</title>
		<style>
		.relativeTag   {     
				z-index:10;   
				position:relative;     
				top:expression(this.offsetParent.scrollTop);     
		}
		.selected_font1 {
				width: 56px;
				font-family: 宋体;
				font-size: 12px;
				line-height: 120%;
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
	var l = m.length;//总复选框个数
	var count = 0;//选中复选框个数
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
	}
	//if (count > 1000) {
		//alert("单次派单条数不得超过1000条！");
	//} else 
		if(count == 0) {
		alert("请选择信息！");
	} else {
		document.form1.action = path
		+ "/servlet/ProvinceSendServlet?command=paidan";
		document.form1.submit();
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

					}
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
	
		var zdid = document.getElementById("zdid").value;
		var zdidnull = zdid.replace(/[ ]/g,"");
		var bl1 = document.getElementById("bl1").value;
		var bl2 = document.getElementById("bl2").value;
		if(zdidnull==""){
			if(isNaN(bl1)|| isNaN(bl2)){
				alert("比例必须为数字!");
			}else{
				document.form1.action = path + "/servlet/ProvinceSendServlet?command="
					+ command;
						document.form1.submit();
				if ("chaxun" == command) {
					showdiv("请稍等..............");
				}
			}

		}else if(parseInt(zdid)==zdid && parseInt(zdid) > 0){
			if(isNaN(bl1)|| isNaN(bl2)){
				alert("比例必须为数字!");
			}else{
				document.form1.action = path + "/servlet/ProvinceSendServlet?command="
					+ command;
						document.form1.submit();
				if ("chaxun" == command) {
					showdiv("请稍等..............");
				}
			}

		}else{
			alert("站点ID输入有误!");
		}
}
$(function() {

	$("#tongguo").click(function() {
		passCheck();//通过
		});
	$("#butongguo").click(function() {
		passCheck();//不通过
		});
	$("#chaxun").click(function() {
		queryDegree("chaxun");//查询
		});
	$("#daochuBtn").click(function() {
		queryDegree("daochu");//查询
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
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">省手动更新及派单</span>	
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
									城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市：
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
								</td>
								<td>
									&nbsp;区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;县：
								</td>
								<td>
									<select name="xian" class="selected_font">
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
								站点属性：
							</td>
							<td>
								<select name="jzproperty" class="selected_font">
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
							<td>省级状态：</td>
							<td>
							<select name="sjzt" class="selected_font">
							<option value="0">未操作</option>
							<option value="1">已更新</option>
							<option value="2">已派单</option>
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
							<tr class="form_label">
								<td>比例(%)：</td>
								<td><input type="text" name="bl1" value="" style="text-align:right" class="selected_font1" />至 <input type="text" name="bl2" value=""  style="text-align:right" class="selected_font1" /></td>
								<td>
									&nbsp;站点名称：
								</td>
								<td>
								<input type="text" name="zdname"
									value="<%if (null != request.getParameter("zdname"))
									out.print(request.getParameter("zdname"));%>"
									class="selected_font" />
								</td>
								<td>站&nbsp;点&nbsp;ID&nbsp;：</td>
         						<td><input type="text" name="zdid" value="<%=zdid%>" style="width:130px" class="selected_font"/></td>
       						</tr>
						</table>
					</td>
				</tr>
			</table>

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
					<td width="5%" height="23" bgcolor="#DDDDDD">
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
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								城市
							</div>
						</td>
						<td width="12%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								相差比例
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								生产标(度)
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								建议生产标(度)
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								直流负荷(A)
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								交流负荷(A)
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								本地标(度)
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点属性
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点ID
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								乡镇
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
									value="${list.bgid}" />
							</div>
						</td>
						<td>
							<div align="center">
								${list.city}
							</div>
						</td>
						<td>
							<div align="left">
								<a href="javascript:modifyad('${list.zdid}')" >${list.zdname}</a>
							</div>
						</td>
						<td>
							<div align="right">
								${list.xcbl}%
							</div>
						</td>
						<td>
							<div align="right">
								${list.scb}
							</div>
						</td>
						<td>
							<div align="right">
								${list.jyscb}
							</div>
						</td>
						<td>
							<div align="right">
								${list.zlfh}
							</div>
						</td>
						<td>
							<div align="right">
								${list.jlfh}
							</div>
						</td>
						<td>
							<div align="right">
								${list.bdb}
							</div>
						</td>
						<td>
							<div align="right">
								${list.property}
							</div>
						</td>
						<td>
							<div align="right">
								${list.zdid}
							</div>
						</td>
						<td>
							<div align="right">
								${list.quxian}
							</div>
						</td>
						<td>
							<div align="right">
								${list.xiaoqu}
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
					<input type="hidden" name="personnal" value="<%=personnal%>" />
					<input type="hidden" name="accountid" value="<%=accountid%>" />
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
						<div id="daochuBtn"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 4px">
							<img src="<%=path%>/images/daochu.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导出</span>
						</div>
						<div id="butongguo"
							style="position: relative; width: 105px; height: 23px; cursor: pointer; float: right; right: 10px">
							<img alt="" src="<%=request.getContextPath()%>/images/mmcz.png"
								width="100%" height="100%" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">核实标杆派单</span>
						</div>
						<div id="tongguo"
							style="width: 100px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
							<img src="<%=path%>/images/2chongzhi.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 35px; top: 5px; color: white">更新生产标</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div id="detailsDiv"
							style="position: relative; width: 99%; height: 200px; left: 0px; top: 10px; z-index: 1; float: left; ">
							<iframe name="treeMap" frameborder="0" width="100%" height="100%"
								></iframe>
						</div>
					</td>
				</tr>
			</table>
			</form>
	</body>
	<script type="text/javascript">
	var path = '<%=path%>';
 function modifyad(zdid){
    	var b=path+"/servlet/ProvinceSendServlet?command=detail&zdid="+zdid;			
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();
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
document.form1.jzproperty.value = '<%=jzproperty%>';
document.form1.sjzt.value = '<%=sjzt%>';
document.form1.bl1.value = '<%=bl1%>';
document.form1.bl2.value = '<%=bl2%>';
</script>
</html>

