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
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : agcode1;//市
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";//区县
	String xiaoqu = request.getParameter("xiaoqu") != null ? request
			.getParameter("xiaoqu") : "0";//乡镇
	
	String property = request.getParameter("property") != null ? request
			.getParameter("property")
			: "0";//站点属性
	String cityzrauditstatus = request
			.getParameter("cityzrauditstatus") != null ? request
			.getParameter("cityzrauditstatus") : "0";//市级主任审核状态
	String getlrbz=request.getParameter("getlrbz") != null ? request.getParameter("getlrbz") : "0";//录入标志位

	String color;//颜色
	int intnum = request.getAttribute("num") != null ? (Integer) request
			.getAttribute("num")
			: 0;//条数 ,查出数据的条数，用于颜色设置
	if (intnum % 2 == 0) {
		color = "#DDDDDD";
	} else {
		color = "#FFFFFF";
	}
%>

<html>
	<head>
		<title>市级主任审核</title>

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

function passCheck() {//通过
	var m = document.getElementsByName('test[]');
	var arr = new Array;
	var l = m.length; //l表示选择的条数
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var bzw = 1;
	var bzw1 = "";
	var count2 = 0;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";

	//判断选择了多少条数
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
	}
	if (count != 0) {

		//最大只能传240条数据   所以取240的倍数
		if (count % 240 == 0) {
			n = count / 240 - 1;
		} else {
			n = (count - (count % 240)) / 240;
		}
		var r = 240 * n;
		for ( var i = 0; i < l; i++) {
			//取出选择数据的uuid并且分配好在那个sql执行
			if (m[i].checked == true) {
				bz += 1;
				count1 += 1;//选择的条数
				var j = m[i].value.toString().indexOf("ssss=");
				var chooseIdStr3 = m[i].value.toString().substring(0, j);//截取uuid
				//截取电费支付类型
				var zflx1 = m[i].value.toString().substring(j + 5,
						m[i].value.toString().length);
				if (zflx1 == "1") {
					chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
				}else if (zflx1 == "2") {
					chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
				}
			}
			var t = bz;
			bzw1 = document.form1.bzw1.value;
			if (bzw1 == "1") {
				if (count1 <= r || (t >= 239 && t <= 241) {
					if ((t / 240 == 1) || (t >= 239 && t <= 241)) {
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);//去除最后一个逗号
						chooseIdStr2 = chooseIdStr2.substring(0,
								chooseIdStr2.length - 1);
						passCheck1(chooseIdStr1, chooseIdStr2);
						chooseIdStr1 = "";
						chooseIdStr2 = "";
						bz = 0;
						count2 = 0;
					}
				} else if (count == count1 && bzw == 1) {
					chooseIdStr1 = chooseIdStr1.substring(0,
							chooseIdStr1.length - 1);
					chooseIdStr2 = chooseIdStr2.substring(0,
							chooseIdStr2.length - 1);
					bzw = 0;
					document.form1.action = path
							+ "/servlet/CityMngCheckServlet?command=checkcity&chooseIdStr1="
							+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
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
		document.form1.action = path + "/servlet/CityMngCheckServlet?command="
				+ command;
		;
		document.form1.submit();
		if ("chaxun" == command) {
			showdiv("请稍等..............");
		}
	}
}
$(function() {
	$("#daochuBtn").click(function() {
		queryDegree("daochu");//导出
		});

	$("#tongguo").click(function() {
		passCheck();//通过
		});

	$("#butongguo").click(function() {
		passCheckNoPass();//不通过
		});
	$("#chaxun").click(function() {
		queryDegree("chaxun");//查询
		});
	$("#quxiao").click(function() {
		passCheckNo();//取消通过
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
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">市级主任审核</span>	
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
							<td>
								站点属性：
							</td>
							<td>
								<select name="property" class="selected_font"
									onchange="kzinfo()">
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
								报账月份：
							</td>
							<td>
								<input class="selected_font" type="text" name="bztime"
									value="<%if (null != request.getParameter("bztime"))
				out.print(request.getParameter("bztime"));%>"
									readonly="readonly" class="Wdate"
									onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />

							</td>
							<td>
								市级主任审核状态：
							</td>
							<td>
								<select name="cityzrauditstatus" class="selected_font"
									onchange="javascript:document.form1.cityzrauditstatus2.value=document.form1.cityzrauditstatus.value">

									<option value="-1">
										请选择
									</option>
									<option value="1">
										审核通过
									</option>
									<option value="0">
										未审核
									</option>
									<option value="2">
										审核不通过
									</option>
								</select>
							</td>
							<td>录入标志位</td>
							<td>
							<select name="getlrbz" class="selected_font">
												<option value="0" selected="selected">请选择</option>
												<option value="1">电费单</option>
												<option value="2">预付费</option>
												
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
				<table width="4000px" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">工单提醒</div></td>
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
								区县
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								超省标比例
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								超市标比例
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								本地标（度/天）
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								省定标电量
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								供电方式
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表用电量
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								上次抄表时间
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								本次抄表时间
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账用电量
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								用电量调整
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								(电量调整)备注
							</div>
						</td>
						
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								单价
							</div>
						</td>

						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电费调整
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								(电费调整)备注
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期电费</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期电量</div></td>
                       	<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期单价</div></td>
                       	<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期余额</div></td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账电费
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								结算周期
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								超省定标电费额
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								超省定标电费比值
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								人工审核状态
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级审核状态
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								财务审核状态
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表名称
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电费支付类型
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								录入标志
							</div>
						</td>
						
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								票据类型
							</div>
						</td>
						
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点ID
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表ID
							</div>
						</td>

						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点属性
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级主任审核状态
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								人工审核通过原因
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电量调整*倍率
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								上期电表用电量
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								上期电量调整*倍率
							</div>
						</td>
						<td width="" height="23" bgcolor="#DDDDDD">
                        <div align="center" class="bttcn">
                        	线变损电量
                        </div>
                        </td>
                        <td width="" height="23" bgcolor="#DDDDDD">
                        <div align="center" class="bttcn">
                        	报账日均电量
                        </div>
                        </td>
                        <td width="" height="23" bgcolor="#DDDDDD">
                        <div align="center" class="bttcn">
                        	倍率
                        </div>
                        </td>
					</tr>

					<c:forEach items="${list}" var="list" varStatus="status">
						
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
							<td><div align="center" >${list.gdtx}</div></td> 
							<td>
								<div align="center">
									${status.count}
								</div>
							</td>
							<td>
								<div align="center">
									<input type="checkbox" name="test[]"
										value="${list.uuid}ssss=${list.dfbzw}" />
								</div>
							</td>
							<td>
								<div align="right">
									${list.quxian}
								</div>
							</td>
							<td>
								<div align="left">
									<a href="javascript:lookDetailss('${status.count}')">${list.zdname}</a>
								</div>
								<input id="dbid" name="dbid${status.count}" type="hidden"
									value="${list.dbid}" />
								<input id="dfzflx" name="dfzflx${status.count}" type="hidden"
									value="${list.dfzflx}" />
								<input id="dfbzw" name="dfbzw${status.count}" type="hidden"
									value="${list.dfbzw}" />
								<input id="acciuntmonth" name="accountmonth${status.count}"
									type="hidden" value="${list.accountmonth}" />
							</td>
							
							<td>
								<div align="right">
									${list.csbl}%
								</div>
							</td>
							<td>
								<div align="right">
									${list.csbbl}%
								</div>
							</td>
							<td>
								<div align="right">
									${list.edhdl}
								</div>
							</td>
							<td>
								<div align="right">
									${list.qsdbdl}
								</div>
							</td>
							<td>
								<div align="right">
									${list.gdfs}
								</div>
							</td>
							<td>
								<div align="right">
									${list.dbydl}
								</div>
							</td>
							<td>
								<div align="right">
									${list.lastdatetime}
								</div>
							</td>
							<td>
								<div align="right">
									${list.thisdatetime}
								</div>
							</td>
							<td>
								<div align="right">
									${list.blhdl}
								</div>
							</td>
							<td>
								<div align="right">
									${list.floatdegree}
								</div>
							</td>
							<td>
								<div align="right">
									${list.memo}
								</div>
							</td>
							
							<td>
								<div align="right">
									${list.unitprice}
								</div>
							</td>
							<td>
								<div align="right">
									${list.floatpay}
								</div>
							</td>
							<td>
								<div align="right">
									${list.memo1}
								</div>
							</td>
							<td><div align="center" >${list.lastelecfees}</div></td>
							<td><div align="center" >${list.lastelecdegree}</div></td>
							<td><div align="center" >${list.lastunitprice}</div></td>
							<td><div align="center" >${list.lastyue}</div></td>
							<td>
								<div align="right">
									${list.actualpay}
								</div>
							</td>
							<td>
								<div align="right">
									${list.jszq}
								</div>
							</td>
							<td>
								<div align="right">
									${list.csdbdfe}
								</div>
							</td>
							<td>
								<div align="right">
									${list.csdbdfbz}
								</div>
							</td>

							<td>
								<c:if
									test="${list.rgshzt eq 1 || list.rgshzt eq -1 || list.rgshzt eq 2}">

									<div align="center">
										通过
									</div>

								</c:if>
								<c:if test="${list.rgshzt eq -2}">
									<div align="center">
										不通过
									</div>
								</c:if>
								<c:if
									test="${list.rgshzt == 0 || (list.rgshzt !=1 && list.rgshzt !=-1 && list.rgshzt !=2 && list.rgshzt !=-2)}">
									<div align="center">
										未通过
									</div>
								</c:if>
							</td>
							<td>
								<c:if test="${list.sjshzt eq 1}">
									<div align="center">
										通过
									</div>
								</c:if>
								<c:if test="${list.sjshzt eq -2}">
									<div align="center">
										不通过
									</div>
								</c:if>
								<c:if test="${list.sjshzt !=1 && list.sjshzt != -2}">

									<div align="center">
										未通过
									</div>

								</c:if>
							</td>

							<td>
								<c:if test="${list.rgshzt eq 2}">
									<div align="center">
										通过
									</div>
								</c:if>
								<c:if test="${list.rgshzt eq -1}">
									<div align="center">
										不通过
									</div>
								</c:if>
								<c:if test="${list.rgshzt !=-1 && list.rgshzt != 2}">
									<div align="center">
										未通过
									</div>
								</c:if>
							</td>
							<td>
								<div align="right">
									${list.dbname}
								</div>
							</td>
							<td>
								<div align="right">
									${list.dfzflx}
								</div>
							</td>
							<td>
								<div align="right">
									${list.lrzt}
								</div>
							</td>
							
							<td>
								<div align="right">
									${list.stationtype}
								</div>
							</td>
							
							<td>
								<div align="right">
									${list.pjlx}
								</div>
							</td>
							
							<td>
								<div align="right">
									${list.zdid}
								</div>
							</td>
							<td>
								<div align="right">
									${list.dbid}
								</div>
							</td>


							<td>
								<div align="center">
									${list.property}
								</div>
							</td>
							<c:if test="${list.cityzrauditstatus eq 1}">
								<td>
									<div align="center">
										通过
									</div>
								</td>
							</c:if>
							<c:if test="${list.cityzrauditstatus eq 2}">
								<td>
									<div align="center">
										不通过
									</div>
								</td>
							</c:if>
							<c:if
								test="${list.cityzrauditstatus !=1 && list.cityzrauditstatus != 2}">
								<td>
									<div align="center">
										未审核
									</div>
								</td>
							</c:if>
							
							<td>
								<div align="right">
									${list.manpassmemo}
								</div>
							</td>
						<td><div align="center" >${list.floatdegreeandbl}</div></td>
						<td><div align="center" >${list.lastactualdegree}</div></td>
						<td><div align="center" >${list.lastfloatdegreeandbl}</div></td>
						<td><div align="right">${list.lineandchangeandbl}</div></td>
						<td><div align="right">${list.bzrj}</div></td>
						<td><div align="right">${list.beilv}</div></td>

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
						<input type="hidden" name="sheng2" id="sheng2" value="" />
						<input type="hidden" name="shi2" id="shi2" value="" />
						<input type="hidden" name="xian2" id="xian2" value="" />
						<input type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
						<input type="hidden" name="sptype2" id="sptype2" value="" />
						<input type="hidden" name="cityzrauditstatus2"
							id="cityzrauditstatus2" value="" />
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
						<div id="daochuBtn"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 4px">
							<img src="<%=path%>/images/daochu.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导出</span>
						</div>

						<div id="butongguo"
							style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right: 10px">
							<img alt="" src="<%=request.getContextPath()%>/images/baocun.png"
								width="100%" height="100%" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">退单</span>
						</div>
						<div id="tongguo"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
							<img src="<%=path%>/images/2chongzhi.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">通过</span>
						</div>
						<div id="quxiao"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: 30px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">撤回</span>
						</div>

					</td>
				</tr>
	
			</table>
		</form>
	</body>
	<script type="text/javascript">
var path = '<%=path%>';
function lookDetailss(statusi) {//查看详情
	document.form1.attributes["target"].value = "_blank";
	document.form1.attributes["action"].value = path
			+ "/servlet/CityMngCheckServlet?command=xiangqing&statusi="
			+ statusi;
	document.form1.submit();
}
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
	if (para == "checkcity1") {
		XMLHttpReq1.onreadystatechange = processResponse_checkcity1;
	} else if (para == "checkcityno1") {
		XMLHttpReq1.onreadystatechange = processResponse_checkcityno1;
	} else {
		XMLHttpReq1.onreadystatechange = processResponse;//指定响应函数
	}
	XMLHttpReq1.send(null);
}
// 处理返回信息函数
function processResponse() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq1.responseText;
			window.alert(res);
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function passCheck1(chooseIdStr1, chooseIdStr2) {//通过240
	sendRequest1(path
			+ "/servlet/CityMngCheckServlet?command=checkcity1&chooseIdStr1="
			+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2, "checkcity1");
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
function passCheckNo1(chooseIdStr1, chooseIdStr2) {//取消通过1
	sendRequest1(path
			+ "/servlet/CityMngCheckServlet?command=checkcityno1&chooseIdStr1="
			+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2, "checkcityno1");
}
function passCheckNo2(chooseIdStr1, chooseIdStr2) {//审核不通过1
	sendRequest1(
			path
					+ "/servlet/CityMngCheckServlet?command=checkcityno11&chooseIdStr1="
					+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2,
			"checkcityno1");
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
var path = '<%=path%>';
//取消通过
function passCheckNo() {
	var m = document.getElementsByName('test[]');
	var l = m.length;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var count2 = 0;
	var bzw = 1;
	var bzw1 = "";
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
				var j = m[i].value.toString().indexOf("ssss=");
				var chooseIdStr3 = m[i].value.toString().substring(0, j);
				var zflx1 = m[i].value.toString().substring(j + 5,
						m[i].value.toString().length);
				if (zflx1 == "1") {
					chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
				} else if (zflx1 == "2") {
					chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
				}
			}
			bzw1 = document.form1.bzw1.value;
			var t = bz;
			if (bzw1 == "1") {
				if (count1 <= r || (t >= 239 && t <= 241) {
					if (((bz + count2) / 240 == 1) || (t >= 239 && t <= 241)) {
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						chooseIdStr2 = chooseIdStr2.substring(0,
								chooseIdStr2.length - 1);
						passCheckNo1(chooseIdStr1, chooseIdStr2);
						chooseIdStr1 = "";
						chooseIdStr2 = "";
						bz = 0;
						count2 = 0;

					}
				} else if (count == count1 && bzw == 1) {
					chooseIdStr1 = chooseIdStr1.substring(0,
							chooseIdStr1.length - 1);
					chooseIdStr2 = chooseIdStr2.substring(0,
							chooseIdStr2.length - 1);
					bzw = 0;
					document.form1.action = path
							+ "/servlet/CityMngCheckServlet?command=checkcityno&chooseIdStr1="
							+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
					document.form1.submit();
					showdiv("请稍等..............");
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
//审核不通过
function passCheckNoPass() {
	var m = document.getElementsByName('test[]');
	var l = m.length;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var count2 = 0;
	var bzw = 1;
	var bzw1 = "";
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
				var j = m[i].value.toString().indexOf("ssss=");
				var chooseIdStr3 = m[i].value.toString().substring(0, j);
				var zflx1 = m[i].value.toString().substring(j + 5,
						m[i].value.toString().length);
				if (zflx1 == "1") {
					chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
				} else if (zflx1 == "1") {
					chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
				}
			}
			bzw1 = document.form1.bzw1.value;
			var t = bz;
			if (bzw1 == "1") {
				if (count1 <= r || (t >= 239 && t <= 241) {
					if ((t / 240 == 1) || (t >= 239 && t <= 241)) {
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						chooseIdStr2 = chooseIdStr2.substring(0,
								chooseIdStr2.length - 1);
						passCheckNo2(chooseIdStr1, chooseIdStr2);
						chooseIdStr1 = "";
						chooseIdStr2 = "";
						bz = 0;
						count2 = 0;
					}
				} else if (count == count1 && bzw == 1) {
					chooseIdStr1 = chooseIdStr1.substring(0,
							chooseIdStr1.length - 1);
					chooseIdStr2 = chooseIdStr2.substring(0,
							chooseIdStr2.length - 1);
					bzw = 0;
					document.form1.action = path
							+ "/servlet/CityMngCheckServlet?command=checkcityno2&chooseIdStr1="
							+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
					document.form1.submit();
					showdiv("请稍等..............");
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
document.form1.property.value = '<%=property%>';
document.form1.cityzrauditstatus.value = '<%=cityzrauditstatus%>';
document.form1.getlrbz.value='<%=getlrbz%>';
</script>
</html>

