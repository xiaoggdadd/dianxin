<%@page import="com.noki.zwhd.manage.WyManage"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.DianBiaoBean,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.WydfftBean"%>
<%@ page import="com.noki.mobi.common.ZtCommon"%>
<%
	String path = request.getContextPath();
	String sheng = (String) session.getAttribute("accountSheng");
	Account account = (Account) session.getAttribute("account");
	String loginId=account.getAccountId();
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
	String hdzt = request.getParameter("hdzt")!=null?request.getParameter("hdzt") : "0";
	String pc = request.getParameter("pc")!=null?request.getParameter("pc") : "";
	String zdbm = request.getParameter("zdbm")!=null?request.getParameter("zdbm") : "";
	String dh = request.getParameter("dh")!=null?request.getParameter("dh") : "";
	String cy = request.getParameter("cy")!=null?request.getParameter("cy") : "0";
	
	
	String loginName = (String) session.getAttribute("loginName");
	String roleId = (String) session.getAttribute("accountRole");
	String color = null;
	String s_curpage = request.getParameter("curpage") != null ? request.getParameter("curpage"): "1";
	int curpage = Integer.parseInt(s_curpage);
	int allpage = 1;
	int pageSize = 15;
	
	//String c_zdbm = request.getParameter("c_zdbm");
	//if(c_zdbm!=null){
	//	WyManage wymanage = new WyManage();
	//	boolean flag = wymanage.deleteWydfftByZdbm(c_zdbm);
	//}
	
	String ids = request.getParameter("ids");
	if(ids!=null){
		WyManage wymanage = new WyManage();
		boolean flag = wymanage.deleteWydfftByIds(ids);
	}
	
%>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
	String permissionRights = permissionRights = commBean
	.getPermissionRight(roleId, "0104");
%>
<html>
<head>
<title>物业数据-电费分摊</title>
<style>
.btn {
	BORDER-RIGHT: #7b9ebd 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7b9ebd 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#cecfde );
	BORDER-LEFT: #7b9ebd 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7b9ebd 1px solid
}

.btn1_mouseout {
	BORDER-RIGHT: #7EBF4F 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7EBF4F 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#B3D997 );
	BORDER-LEFT: #7EBF4F 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7EBF4F 1px solid
}

.btn1_mouseover {
	BORDER-RIGHT: #7EBF4F 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7EBF4F 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#CAE4B6 );
	BORDER-LEFT: #7EBF4F 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7EBF4F 1px solid
}

.btn2 {
	padding: 2 4 0 4;
	font-size: 12px;
	height: 23;
	background-color: #ece9d8;
	border-width: 1;
}

.btn3_mouseout {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#C3DAF5 );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

.btn3_mouseover {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

.btn3_mousedown {
	BORDER-RIGHT: #FFE400 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #FFE400 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#C3DAF5 );
	BORDER-LEFT: #FFE400 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #FFE400 1px solid
}

.btn3_mouseup {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#C3DAF5 );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

.btn_2k3 {
	BORDER-RIGHT: #002D96 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #002D96 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#FFFFFF, EndColorStr=#9DBCEA );
	BORDER-LEFT: #002D96 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #002D96 1px solid
}

.style1 {
	color: #FF9900;
	font-weight: bold;
}

.STYLE6 {
	color: #FFFFFF;
}

.memo {
	border: 1px #C8E1FB solid
}

.style7 {
	font-weight: bold
}

.memo {
	border: 1px #888888 solid
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
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
	line-height: 120%;
}

.bttcn {
	color: BLACK;
	font-weight: bold;
}

#id1 {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}
</style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
//=点击展开关闭效果=

function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
var openTip = oOpenTip || "";
var shutTip = oShutTip || "";
if(targetObj.style.display!="none"){
   if(shutAble) return;
   targetObj.style.display="none";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = shutTip; 
   }
} else {
   targetObj.style.display="block";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = openTip; 
   }
}
}
</script>
<script>

var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();


var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init();
</script>
<script language="javascript">
	var path = '<%=path%>';
	function ShowHideSearchRegion(trObject, SelfObject) {
		if (trObject.style.display == "none") {
			trObject.style.display = "";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">";
		} else {
			trObject.style.display = "none";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">";
		}
	}

	function chaxun() {
		document.form1.action = path + "/web/wyftdfsh/tt_wydfft_search.jsp";
		document.form1.submit();//提交按钮  input
	}
	
	function delLogs() {

	}
	
	function addJz() {
		document.form1.action = path + "/web/wyftdfsh/tt_wydfft_add.jsp";
		document.form1.submit();
	}
	function daorujz() {
		document.form1.action = path
				+ "/web/wyftdfsh/tt_wydfft_input.jsp";
		document.form1.submit();
	}
	
	function heduijz(){
		document.form1.action = path
				+ "/web/wyftdfsh/tt_cwybx_wydfft_hd.jsp";
		document.form1.submit();
	}
	
	function exportad() {
		document.form1.action = path
				+ "/servlet/WydfftDownload";
		document.form1.submit();
	}
	
	function pcdelete(){
		document.form1.action = path+ "/web/wyftdfsh/tt_wydfft_delete_pc.jsp";
		document.form1.submit();
	}
	
	$(function() {
		$("#pcDeleteBtn").click(function(){
			pcdelete();
		});
		
		$("#deleteBtn").click(function(){
			var i = 0;
			var _ids;
			for ( var j = 0; j < document.form1.elements.length; j++) {
				if (document.form1.elements[j].checked) {
					i++;
					_ids += document.form1.elements[j].value+",";
				}
			}
			if (i > 0) {
				var _d_dycwbxje = $("#d_dycwbxje").html();
				var _d_dycwhxdje = $("#d_dycwhxdje").html();
				var _d_jgje = $("#d_jgje").html();
				var _d_cy = $("#d_cy").html();
				document.form1.action = path + "/web/wyftdfsh/tt_wydfft_search.jsp?ids='"+_ids+"'";
				document.form1.submit();
			} else {
				alert("请选择要删除的条目");
				return;
			}
		});
		$("#daochuBtn").click(function() {
			exportad();
		});
		$("#xiugaiBtn").click(function(){
			var i = 0;
			var _id;
			for ( var j = 0; j < document.form1.elements.length; j++) {
				if (document.form1.elements[j].checked) {
					i++;
					_id = document.form1.elements[j].value;
				}
			}
			if (i > 0) {
				var _d_dycwbxje = $("#d_dycwbxje").html();
				var _d_dycwhxdje = $("#d_dycwhxdje").html();
				var _d_jgje = $("#d_jgje").html();
				var _d_cy = $("#d_cy").html();
				document.form1.action = path + "/web/wyftdfsh/tt_wydfft_update.jsp?t_wyid='"+_id+"'";
				document.form1.submit();
			} else {
				alert("请选择要修改的条目");
				return;
			}
		});
		$("#query").click(function() { 
			chaxun();
		});
		$("#xinzengBtn").click(function() {
			addJz();
			showdiv("请稍等..............");
		});
		$("#daoruBtn").click(function() {
			daorujz();
			showdiv("请稍等..............");
		});
		$("#heduiBtn").click(function() {
			heduijz();
			showdiv("请稍等..............");
		});
	});
	
function checkPage() {
	if (document.form1.pageCheck.checked) {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = true;
		}
	} else {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = false;
		}
	}
}
function gopage() {
	document.form1.submit();
}
function nextpage() {
	document.form1.page.value = parseInt(document.form1.page.value) + 1;
	var curpage = '<%=(curpage + 1)%>';
	document.form1.action = path + "/web/wyftdfsh/tt_wydfft_search.jsp?curpage="
			+ curpage;
	document.form1.submit();
}
function previouspage() {
	if ((parseInt(document.form1.page.value)) < 1)
		document.form1.page.value = 1;
	else {
		document.form1.page.value = parseInt(document.form1.page.value) - 1;
		var curpage = '<%=(curpage - 1)%>';
		document.form1.action = path + "/web/wyftdfsh/tt_wydfft_search.jsp?curpage="
				+ curpage;
		document.form1.submit();
	}
}
function gopagebyno(pageno) {
	document.form1.page.value = pageno;

	document.form1.action = path + "/web/wyftdfsh/tt_wydfft_search.jsp?curpage=" + pageno;
	document.form1.submit();
}
</script>

</head>

<body class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="20%">
			<tr>
				<td>
				</td>
			</tr>
			<tr>
				<td colspan="4" >
					<div style="width:700px;height:50px">
						<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">物业数据电费分摊</span>
					</div>
				</td>
			</tr>
			<tr>
				<td height="30" colspan="4">
					<div id="parent" style="display:inline">
						<div style="width:50px;display:inline;">
							<hr>
						</div>
						<font size="2">&nbsp;过滤条件&nbsp;</font>
						<div style="width:300px;display:inline;">
							<hr>
						</div>
					</div></td>
			</tr>
			<tr>
				<td width="1200">
					<table border='0'>
						<tr class="form_label">
							<td>城市：</td>
							<td><select name="shi" class="selected_font"
								onchange="changeCity()">
									<option value="0">请选择</option>
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

							<td>区县:</td>
							<td><select name="xian" class="selected_font"
								onchange="changeCountry()">
									<option value="0">请选择</option>
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
							<td>乡镇：</td>
							<td><select name="xiaoqu" class="selected_font">
									<option value="0">请选择</option>
									<%
										ArrayList xiaoqulist = new ArrayList();
										xiaoqulist = commBean.getAgcode(xian, account.getAccountId());
										if (xiaoqulist != null) {
											String agcode = "", agname = "";
											int scount = ((Integer) xiaoqulist.get(0)).intValue();
											for (int i = scount; i < xiaoqulist.size() - 1; i += scount) {
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
								<p>
									<font size="2">
										<div title="您可以进行详细的条件筛选" id="query1"
											onclick="openShutManager(this,'box3',false)"
											style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
											<img alt=""
												src="<%=request.getContextPath()%>/images/gaojichaxun.gif"
												width="100%" height="100%" /> <span
												style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
										</div> </font>
								</p>
							</td>
							<td>
								<%
									if (permissionRights.indexOf("PERMISSION_SEARCH") >= 0) {
								%>
								<div id="query"
									style="position:relative;width:59px;height:23px;cursor: pointer;right:-225px;float:right;top:0;">
									<img alt=""
										src="<%=request.getContextPath()%>/images/chaxun.png"
										width="100%" height="100%" /> <span
										style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
								</div> <%
 	}
 %>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>

		<div style="width:88%;" > 
			<p id="box3" style="display:none">
			<table border='0'>
				<tr class="form_label">
		         	<td>核对状态：</td>
		            <td>
		            	<select name="hdzt">
		            		<option value="0">请选择</option>
		            		<option value="已核对">已核对</option>
		            		<option value="未核对">未核对</option>
		            	</select>
		            </td>
		            <td>批次：</td>
		            <td>
		            	<input type="text" name="pc" value="<%=pc %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font" />
		            </td>
		            <td>站点编码：</td>
		            <td>
		            	<input type="text" name="zdbm" value="<%=zdbm %>" class="selected_font" />
		            </td>
		            <td>单号：</td>
		            <td>
		            	<input type="text" name="dh" value="<%=dh %>" class="selected_font" />
		            </td>
		            <td>差异：</td>
		            <td>
		            	<input name="cy" type="radio" value="0" <%=cy.equals("0")? "checked=\"checked\"":""%> />全部
		            	<input name="cy" type="radio" value="1" <%=cy.equals("1")? "checked=\"checked\"":""%>/>无
		            	<input name="cy" type="radio" value="2" <%=cy.equals("2")? "checked=\"checked\"":""%>/>有
		            </td>
	            </tr>
            </table>
	</div>	
		
		<div id="parent" style="display:inline">
			<div style="width:50px;display:inline;">
				<hr>
			</div>
			<font size="2">&nbsp;信息列表&nbsp;</font>
			<div style="width:300px;display:inline;">
				<hr>
			</div>
		</div>
		<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >  
		<table width="2000px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
			<tr bgcolor="#DDDDDD">
				<td width="30" height="23"><div align="center" class="bttcn"><input type="checkbox" name="pageCheck" onClick="checkPage()" /></div></td>
				<td width="50" height="23"><div align="center" class="bttcn">序号</div></td>
				<td width="80" height="23"><div align="center" class="bttcn">批次</div></td>
				<td width="80" height="23"><div align="center" class="bttcn">核对状态</div></td>
				<td width="100" height="23"><div align="center" class="bttcn">省分公司</div>
				</td>
				<td width="100" height="23"><div align="center" class="bttcn">地市分公司</div>
				</td>
				<td width="300" height="23"><div align="center" class="bttcn">站点名称</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">站点编码</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">单号</div>
				</td>
				<td width="250" height="23"><div align="center" class="bttcn">账期</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">缴费金额</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">缴费日期</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">销账标识</div>
				</td>
				<td width="250" height="23"><div align="center" class="bttcn">缴费票据类型</div>
				</td>
				<td width="400" height="23"><div align="center" class="bttcn">供电方名称</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">客户</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">分摊比例(%)</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">税负因子(%)</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">分摊金额</div>
				</td>
				<td width="250" height="23"><div align="center" class="bttcn">开票类型</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">调整后金额</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">调整原因</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">调整人</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">备注</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">对应财务报销单金额</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">对应财务核销单金额</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">较高金额</div>
				</td>
				<td nowrap height="23"><div align="center" class="bttcn">差异</div>
				</td>
			</tr>
			<%
				WyManage wyManage = new WyManage();
				
				List<WydfftBean> wydfftList = wyManage.searchWydfft_fy(loginName,loginId,pageSize,curpage,sheng,shi,xian,xiaoqu,hdzt,"0",pc,zdbm,dh,cy);
				allpage = wyManage.searchWydfft_fy(loginName,loginId,9999999,1,sheng,shi,xian,xiaoqu,hdzt,"0",pc,zdbm,dh,cy).size()/pageSize+1;
				for (int i=0;i<wydfftList.size();i++) {
					WydfftBean wydfft = wydfftList.get(i);
					if(i%2==0){
		 				color="#FFFFFF" ;
					}else{
					   color="#DDDDDD";
					}
			%>
			<tr bgcolor="<%=color%>">
				<td>
					<div align="center"><input type="checkbox" name="itemSelected"
													value="<%=wydfft.getID()%>" /></div></td>
				<td>
					<div align="center"><%=wydfft.getRownum()%></div></td>
				<td>
					<div align="center"><%=wydfft.getYEARMONTH()%></div></td>
				<td>
					<div align="center"><%=wydfft.getHDZT()%></div></td>
				<td>
					<div align="left"><%=wydfft.getSFGS()%></div></td>
				<td>
					<div align="left"><%=wydfft.getDSFGS()%></div></td>
				<td>
					<div align="left"><%=wydfft.getZDMC()%></div></td>
				<td>
					<div align="center"><%=wydfft.getZDBM()%></div></td>
				<td>
					<div align="center"><%=wydfft.getDH()%></div></td>
				<td>
					<div align="center"><%=wydfft.getZQ()%></div></td>
				<td>
					<div align="center"><%=wydfft.getJFJE()%></div></td>
				<td>
					<div align="right"><%=wydfft.getJFRQ()%></div></td>
				<td>
					<div align="center"><%=wydfft.getXZBS()%></div></td>
				<td>
					<div align="right"><%=wydfft.getJFPJLX()%></div></td>
				<td>
					<div align="center"><%=wydfft.getGDFMC()%></div></td>
				<td>
					<div align="center"><%=wydfft.getKH()%></div></td>
				<td>
					<div align="center"><%=wydfft.getFTBL()%></div></td>
				<td>
					<div align="center"><%=wydfft.getFSYZ()%></div></td>
				<td>
					<div align="center"><%=wydfft.getFTJE()%></div></td>
				<td>
					<div align="center"><%=wydfft.getKPLX()%></div></td>
				<td>
					<div align="center"><%=wydfft.getTZHJE()%></div></td>
				<td>
					<div align="center"><%=wydfft.getTZYY()%></div></td>
				<td>
					<div align="center"><%=wydfft.getTZR()%></div></td>
					<td>
					<div align="center"><%=wydfft.getBZ()%></div></td>
				<td>
					<div align="center"><%=wydfft.getDYCWBXJE()%></div></td>
				<td>
					<div align="center"><%=wydfft.getDYCWHXJE()%></div></td>
				<td>
					<div align="center"><%=wydfft.getJGJE()%></div></td>
				<td>
					<div align="center"><%=wydfft.getCY()%></div></td>
			</tr>
			<%
				}
			%>
		</table>
		</div>
		<table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
			<tr bgcolor="#ffffff"  >
				<td colspan="16" >
					<div align="center">
							<font color='#000080'>&nbsp;页次:</font>
							&nbsp;&nbsp;
							 <b><font color=red><%=curpage%></font></b>

							 <font color='#000080'>/<b><%=allpage%></b>&nbsp;</font>
							 &nbsp;&nbsp;
							 <font color='#000080'>
						     <% if (curpage!=1)
						       {out.print("<a href='javascript:gopagebyno(1)'>首页</a>");}
						      else
						      {out.print("首页");}
						      %>
						     </font>
						       &nbsp;&nbsp;
							 <font color='#000080'>
						     <%if(curpage!=1)
						          {out.print("<a href='javascript:previouspage()'>上页</a>");}
						         else
						       {out.print("上页");}
						      %>
							 </font>
							  &nbsp;&nbsp;
							<font color='#000080'>
						     <% if(allpage!=0&&(curpage<allpage))
						         {out.print("<a href='javascript:nextpage()'>下页</a>");}
						         else
						        {out.print("下页");}
						     %>
             				</font>
             				&nbsp;&nbsp;
							<font color='#000080'>
						     <% if(allpage!=0&&(curpage<allpage))
						         {out.print("<a href='javascript:gopagebyno("+allpage+")'>尾页</a>");}
						        else
						        {out.print("尾页");}
						     %>
	            			</font>
	            			&nbsp;&nbsp;
							<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" style="width:70px;font-family: 宋体;font-size:12px;line-height:120%;">
						     <%for(int i=1;i<=allpage;i++)
						         {if(curpage==i){out.print("<option value='"+i+"' selected='selected'>"+i+"</option>");}
						      else{out.print("<option value='"+i+"'>"+i+"</option>");}
						         }
						     %>
					    	</select>
					</div>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			align="right" height="5%">
			<tr align="right">
				<td align="right" height="19" colspan="4"><div id="parent"
						style="display:inline">
						<div style="width:50px;display:inline;">
							<hr>
						</div>
						<div style="width:300px;display:inline;">
							<hr>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<%
						if (!roleId.equals("101")) {
							if (permissionRights.indexOf("PERMISSION_ADD") >= 0) {
					%>

					<div id="daoruBtn"
						style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
						<img src="<%=path%>/images/daoru.png" width="100%" height="100%">
						<span
							style="font-size:12px;position: absolute;left:27px;top:3px;color:white">导入</span>
					</div>
					<div id="xinzengBtn"
						style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:10px">
						<img src="<%=path%>/images/xinzeng.png" width="100%" height="100%">
						<span
							style="font-size:12px;position: absolute;left:27px;top:3px;color:white">新增</span>
					</div> 
					<div id="heduiBtn"
						style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:16px">
						<img src="<%=path%>/images/xinzeng.png" width="100%" height="100%">
						<span
							style="font-size:12px;position: absolute;left:27px;top:3px;color:white">核对</span>
					</div> 
					<%
					 		}
					 	}
					%>
 					<%
						if (!roleId.equals("101")) {
							if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {
					%>
					<div id="xiugaiBtn"
						style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:22px">
						<img src="<%=path%>/images/daoru.png" width="100%" height="100%">
						<span
							style="font-size:12px;position: absolute;left:27px;top:3px;color:white">修改</span>
					</div>
					<%
					 		}
					 	}
					 %>
					 
					<%
						if (!roleId.equals("101")) {
							if (permissionRights.indexOf("PERMISSION_DELETE") >= 0) {
					%>
					<div id="pcDeleteBtn"
						style="width:100px;height:23px;cursor:pointer;float:right;position:relative;right:28px">
						<img src="<%=path%>/images/daochu-1.png" width="100%" height="100%">
						<span
							style="font-size:12px;position: absolute;left:27px;top:3px;color:white">按批次删除</span>
					</div>
					<div id="deleteBtn"
						style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:34px">
						<img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
						<span
							style="font-size:12px;position: absolute;left:27px;top:3px;color:white">删除</span>
					</div>
					<div id="daochuBtn"
						style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:40px">
						<img src="<%=path%>/images/daoru.png" width="100%" height="100%">
						<span
							style="font-size:12px;position: absolute;left:27px;top:3px;color:white">导出</span>
					</div>
					<%
					 		}
					 	}
					%>
					
				</td>
			</tr>
		</table>
	</form>
</body>

</html>
<script type="text/javascript">
	document.form1.shi.value='<%=shi%>';
	document.form1.xian.value='<%=xian%>';
	document.form1.xiaoqu.value='<%=xiaoqu%>';
	document.form1.hdzt.value='<%=hdzt%>';
</script>

