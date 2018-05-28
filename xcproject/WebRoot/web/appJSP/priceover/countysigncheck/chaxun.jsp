<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean,java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();//项目路径
	Account account = (Account) session.getAttribute("account");//账户
	String personnal=account.getAccountName();//审核人员
	String accountid = account.getAccountId();
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
	String gdfstj = request.getParameter("gdfsa")!=null?request.getParameter("gdfsa"):"0";//供电方式
	String state=request.getParameter("state")!=null?request.getParameter("state"):"0";//市审核状态
	String color;//颜色
	int intnum = request.getAttribute("numcolor") != null ? (Integer) request
			.getAttribute("numcolor")
			: 0;//条数 ,查出数据的条数，用于颜色设置
	String datetime = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
	String beginyue = (String)request.getParameter("beginyue");
	if (null == beginyue) {
		beginyue = datetime;
	}
%>

<html>
	<head>
		<title>区县签收及核实</title>
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
		.selected_font{
			width:90px;
			font-family: 宋体;
			font-size:12px;
			line-height:120%;
		}
		.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
		}
		.style1 {
			color: #FF9900;
			font-weight: bold;
		}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px ##888888 solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
 }
.bttcn{color:black;font-weight:bold;}
.form {width:130px}

#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
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
function queryDegree(command) {
	if (document.getElementById("shi").value == ""
			|| document.getElementById("shi").value == "0"
			|| document.getElementById("shi").value == null) {
		alert("城市不能为空");
	} else {
		var zdid = document.getElementById("zdid").value;
		var zdidnull = zdid.replace(/[ ]/g,"");
		var bl1 = document.getElementById("bl1").value;
		var bl2 = document.getElementById("bl2").value;
		if(zdidnull==""){
			if(isNaN(bl1)|| isNaN(bl2)){
				alert("比例必须为数字!");
			}else{
				document.form1.action = path + "/servlet/CountySignCheckServlet?action="
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
				document.form1.action = path + "/servlet/CountySignCheckServlet?action="
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
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">区县签收及核实</span>	
						</div>
					</td>
    			</tr>
				<tr>
					<td height="19" colspan="4">
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
							<td>状态(区县)：</td>
							<td>
							<select name="sjzt" class="selected_font">
							<option value="1">已核实</option>
							<option value="0">未核实</option>
							</select>
							</td>
							<td>
							 报账月份：</td>
							 <td>
   		    <input style="width:130;height:18" type="text" name="beginyue" value="${beginyue}"  readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/>
								</td>
								<td>
									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: 0px; float: right; top: 0px">
										<img alt="" src="<%=path%>/images/chaxun.png" width="100%"
											height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
									</div>
								</td>
							</tr>
							<tr class="form_label">
								<td>比例(%)：</td>
								<td><input type="text" name="bl1" id ="bl1" value="" style="text-align:right" class="selected_font1" />至 <input type="text" name="bl2" id="bl2" value=""  style="text-align:right" class="selected_font1" /></td>
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
         						<td><input type="text" name="zdid" id="zdid" value="<%=zdid%>" style="width:130px" class="selected_font"/></td>
       							
       							
       							<td>供电方式：</td>
                       			<td>
                       				<select name="gdfsa"  class="selected_font" >
		         						<option value="0">请选择</option>
		         							<%
			         							ArrayList gdfsb = new ArrayList();
			         							gdfsb = ztcommon.getSelctOptions("GDFS");
			         							if(gdfsb != null){
			         								String code = "",name = "";
			         								int cscount = ((Integer)gdfsb.get(0)).intValue();
			         								int size = gdfsb.size() - 1;
			         								int i;
			         								for(i = cscount;i < size;i += cscount){
		                    							code = (String)gdfsb.get(i + gdfsb.indexOf("CODE"));
		                    							name = (String)gdfsb.get(i + gdfsb.indexOf("NAME"));
		                    				%>
		                    			<option value="<%=code%>"><%=name%></option>
		                    				<%
		                    						}
			         							}
			        	 					%>
    	
    		         				</select>
    		         			</td>
    		         			<td>市审核状态：</td>
							 	<td>
									<select name="state"  class="selected_font">
	         							<option value="-1">请选择</option>
	         							<option value="0">未审核</option>
	         							<option value="1">通过</option>
	         							<option value="2">退单</option>
         							</select>
								</td>
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
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<input type="checkbox" name="test" onclick="chooseAll()" />
								全选
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								城市
							</div>
						</td>
						<td bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								偏离度
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								操作
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								标准单价
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								实际单价
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账电费
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账电量
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账月份
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点属性
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								供电方式
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点ID
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								乡镇
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								核实状态
							</div>
						</td>
						<td height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市审核状态
							</div>
						</td>
					<c:forEach items="${list}" var="list" varStatus="status">
					<tr bgcolor="${(list.state)=='2'?'#FFFF33':'#FFFFFF'}">
						<td>
							<div align="center">
								${status.count}
							</div>
						</td>
						<td>
							<div align="center">
								<input type="checkbox" name="test[]"
									value="${list.pid}" />
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
								${list.pld}%
							</div>
						</td>
						<td>
							<div align="center"><a href="javascript:check('${list.pid}');" >
								<c:choose>
								<c:when test="${list.countycheck eq 1}">单价已核实
								</c:when>
								<c:when test="${list.countycheck eq 0}">单价未核实
								</c:when>
								</c:choose>
								</a>
							</div>
						</td>
						<td>
							<div align="right">
								${list.standardprice}
							</div>
						</td>
						<td>
							<div align="right">
								${list.actualprice}
							</div>
						</td>
						<td>
							<div align="right">
								${list.bzdf}
							</div>
						</td>
						<td>
							<div align="right">
								${list.bzdl}
							</div>
						</td>
						<td>
							<div align="right">
								${list.accountmonth}
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
						<td>
							<div align="right">
								${list.zdid}
							</div>
						</td>
						<td>
							<div align="right">
								${list.xian}
							</div>
						</td>
						<td>
							<div align="right">
								${list.xiaoqu}
							</div>
						</td>
						<td>
							<div align="center">
								<c:choose>
								<c:when test="${list.countycheck eq 1}">已核实
								</c:when>
								<c:when test="${list.countycheck eq 0}">未核实
								</c:when>
								</c:choose>
							</div>
						</td>
						<td>
			       			<div align="center" >
			       				<c:choose>
									<c:when test="${list.state eq 0}">未审核</c:when>
									<c:when test="${list.state eq 1}">通过</c:when>
									<c:when test="${list.state eq 2}">退单</c:when>
								</c:choose>
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
					</td>
				</tr>
				<tr>
				<td>
					<p>
						<font color="red" size="2">说明：市级退单显示为：黄色<br/></font>
					</p>
				</td>
			</tr>	
			</table>
			</form>
	</body>
	<script type="text/javascript">
	var path = '<%=path%>';
 function check(pid){
    	window.open(path+"/servlet/CountySignCheckServlet?action=check&pid="+pid);			
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
document.form1.beginyue.value = '<%=beginyue%>';
document.form1.gdfsa.value = '<%=gdfstj%>';
document.form1.state.value='<%=state%>';
</script>
</html>

