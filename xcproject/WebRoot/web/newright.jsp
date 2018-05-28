<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.query.basequery.javabean.ElectricFeesQueryBean"%>
<%@ page
	import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean"%>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage"%>
<%@ page
	import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean"%>
<%
	Account account = (Account) session.getAttribute("account");
	String roleid = account.getRoleId();//权限
	System.out.println("权限" + roleid);
	String accountId = account.getName();
	String path = request.getContextPath();
	String loginId = account.getAccountId();
	System.out.println("路徑" + path);
	String loginName = account.getAccountName();
	ElectricFeesQueryBean beanb = new ElectricFeesQueryBean();
	String dfzs = "0";//电费总数
	String dfbbdy = "0";//电费报表打印
	String count1 = "0";//市级待审核
	String count2 = "0";//财务
	String jsdb = "0";
	String gldb = "0";
	String zdtg = "0";
	String zdno = "0";
	String zddfno = "0";
	String rgdfno = "0";
	String rgdftg = "0";
	String zdzs = "0";
	if (roleid.equals("81") || roleid.equals("82")
			|| roleid.equals("83") || roleid.equals("84")) {
			
			
			
	} else {
		//ElectricFeesFormBean be=beanb.getCou(loginId);
		//ElectricFeesBean beansj=new ElectricFeesBean();//市级审核
		String whereStr = "", str2 = "  AND ef.CITYAUDIT='0' ", str1 = "";
		//count1=beansj.getCounttt(whereStr,loginId,str2);//市级待审核
		//count2=beansj.getCountcwdfsh(whereStr,loginId,str1);//财务
		System.out.println("市级待审核" + count1);
		//网络正常与中断
		/*	haodianliangBean bea = new haodianliangBean();
			String flag="1";
			String sum="";
			String sum2=" ";
			ArrayList<haodianliangBean> list=new ArrayList<haodianliangBean>();
			list =bea.getWarnCount(loginId);
			for(haodianliangBean bean:list){
				sum=bean.getZhengchang();
				sum2=bean.getZhongduan();
			}*/
		/* zdzs=be.getZdzs();
		   if(zdzs==null){
			  zdzs="0";
			}
		 dfbbdy=be.getBzdy();
		  if(dfbbdy==null){
			  dfbbdy="0";
			}
		 dfzs=be.getCount();
		  if(dfzs==null){
			  dfzs="0";
			}
		 jsdb=be.getJsdb();
			if(jsdb==null){
			  jsdb="0";
			}
		
		 gldb=be.getGldb() ;
			if(gldb==null){
			  gldb="0";
			}
		zdtg=be.getZd();//站点审核通过
		if(zdtg==null){
			zdtg="0";
		}
		zdno=be.getJdno();//站点待审核
			if(zdno==null){
			   zdno="0";
			}     
			
		zddfno=be.getZdno();//自动电费未审核 
			if(zddfno==null){
			   zddfno="0";
			} 
		
		rgdfno= be.getRgno();//人工电费待审核
			if(rgdfno==null){
			   rgdfno="0";
			}
		rgdftg=be.getRg();//人工电费审核通过
			if(rgdftg==null){
			   rgdftg="0";
			}*/
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<!-- 实例化一个类      对象名为bean -->
		<jsp:useBean id="bean" scope="page"
			class="com.noki.mobi.sys.javabean.NoticeBean">
		</jsp:useBean>

		<title>管理控制台</title>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
		</script>
		<script src="<%=path%>/javascript/PopupCalendar.js">
		</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
		</script>
		<script src="<%=path%>/javascript/tx.js">
		</script>
		<script language="javascript">
var path = '<%=path%>';
var loginId = '<%=loginId%>';
var autoauditfees1 = "0";
var rgsh = "1";//人工审核通过//站点审核标准  通过/采集站点
var caiji = "1";
var rgsh1 = "2";//人工审核未通过/二级审核未审核
function xiazai(id) {
	alert(id);
	document.form1.action = path + "/servlet/FilesDownloadServlet?id=" + id;
	document.form1.submit()
}
function infoNotice(noticeId) {
	window
			.open('sys/infoNotice.jsp?noticeId=' + noticeId, '',
					'width=700,height=500,status=yes,scrollbars=yes,resizable=yes,left=150,top=100')
}

function Warn() {
	self.parent.frames["main"].location = path
			+ "/web/check/checkFeesManual.jsp?rgsh1=" + rgsh1;
}

//function Warn6() {
//	self.parent.frames["main"].location = path
//			+ "/web/check/checkFeesManual.jsp?rgsh=" + rgsh;
//}
function Warn1() {
	self.parent.frames["main"].location = path
			+ "/web/appJSP/checksign/financecheck/financecheck.jsp";
}
function Warn2() {
	self.parent.frames["main"].location = path
			+ "/web/appJSP/checksign/cityelectricfeecheck/cityfeecheck.jsp?rgsh1=" + rgsh1;
}
function Warn3() {
	self.parent.frames["main"].location = path
			+ "/web/appJSP/basisquery/electricdetail/eldetail.jsp?loginId=" + loginId;
}
function Warn4() {
	self.parent.frames["main"].location = path
			+ "/web/check/checkFeesAuto.jsp?autoauditfees1=" + autoauditfees1;
}
function Warntj3() {
	self.parent.frames["main"].location = path
			+ "/web/query/sortanalysis/jizhannenghaoQuery.jsp";
}
function Warntj4() {
	self.parent.frames["main"].location = path
			+ "/web/query/caijipoint/wanquanpipeiQuery.jsp";
}
function Warn5() {
	self.parent.frames["main"].location = path
			+ "/web/electricfees/dianfeidanquery.jsp";
}
function Warn7() {
	self.parent.frames["main"].location = path
			+ "/web/jizhan/sitemanage.jsp?rgsh=" + rgsh;
}
function Warn8() {
	self.parent.frames["main"].location = path + "/web/jizhan/jzshenhe.jsp";
}
function Warn9() {
	self.parent.frames["main"].location = path
			+ "/web/jizhan/dianbiaolist.jsp?dbyt01=dbyt01";
}
function Warn10() {
	self.parent.frames["main"].location = path
			+ "/web/jizhan/dianbiaolist.jsp?dbyt03=dbyt03";
}
function Warn11() {
	self.parent.frames["main"].location = path
			+ "/web/jizhan/sitemanage.jsp?caiji=" + caiji;
}
function Warn12() {
	self.parent.frames["main"].location = path
			+ "/web/jzcbnewfunction/citySend.jsp";
}
function Warn13() {
	self.parent.frames["main"].location = path
			+ "/web/jzcbnewfunction/dsxf.jsp";
}
function Warn14() {
	self.parent.frames["main"].location = path
			+ "/web/jzcbnewfunction/qxSend.jsp";
}
function Warn15() {
	self.parent.frames["main"].location = path
			+ "/web/zdqxkz/zdzdqxcx.jsp";
}
function Warn16() {
	self.parent.frames["main"].location = path
			+ "/web/check/checkDegreeManual.jsp";
}
function War1() {

	self.parent.frames["main"].location = path
			+ "/web/query/caijipoint/collectWarn.jsp?str=1";
}
function War2() {
	self.parent.frames["main"].location = path
			+ "/web/query/caijipoint/collectWarn.jsp?str=0";
}
function daorujz() {
	self.parent.frames["main"].location = path
			+ "/web/jizhan/zhandiandaoru.jsp?servletname=zhandiandaoru&action=upzhandian";
}
function addJ() {
	self.parent.frames["main"].location = path + "/web/jizhan/addsite.jsp";
}
function dfdlr() {
	self.parent.frames["main"].location = path
			+ "/web/electricfees/adddianfeidan.jsp";
}
function dfdpl() {
	self.parent.frames["main"].location = path
			+ "/web/electricfees/inputdegreefees.jsp";
}
function htdlr() {
	self.parent.frames["main"].location = path
			+ "/web/electricfees/bargainDanInput.jsp";
}
function cklr() {
	self.parent.frames["main"].location = path
			+ "/web/electricfees/addPrepayment.jsp";
}
function yzlr() {
	self.parent.frames["main"].location = path
			+ "/web/electricfees/addPrepayment.jsp";
}
function dfinfo5(id) {
	var url = path + "/web/sys/ggaoxx.jsp?id=" + id;
	var obj = new Object();
	obj.mid = 'mid';
	var obj = showModalDialog(url, obj,
			'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');
}
$(function() {
	$("#addjz").click(function() {
		addJ();
	});
	$("#addpl").click(function() {
		daorujz();
	});
	$("#dfdlr").click(function() {
		dfdlr();
	});
	$("#dfdpl").click(function() {
		dfdpl();
	});
	$("#htdlr").click(function() {
		htdlr();
	});
	$("#cklr").click(function() {
		cklr();
	});
	$("#yzlr").click(function() {
		yzlr();
	});

});
</script>
		<style>
.btn {
	BORDER-RIGHT: #7b9ebd 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7b9ebd 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #cecfde );
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
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #B3D997 );
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
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #CAE4B6 );
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
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #C3DAF5 );
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
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #D7E7FA );
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
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #C3DAF5 );
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
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #C3DAF5 );
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
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #FFFFFF, EndColorStr =   #9DBCEA );
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

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.form_label {
	font-family: 宋体;
	font-size: 12px;
}
</style>
	<style type="text/css"> 
	a:link{color:blue;} /* 未访问的链接颜色 */
	a:visited{color:blue;} /* 已访问的链接颜色 */
	a:hover{color:red;} /* 鼠标在链接上颜色 */ 
	a:active{color:brown;} /* 点击激活链接颜色 */
	</style> 
	</head>

	<body class="body" style="overflow-x: hidden;">
		<script type="text/javascript">
function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = ""
		SelfObject.innerHTML = "<img border=\"0\" src=\"../images/1.gif\">"

	} else {
		trObject.style.display = "none"
		SelfObject.innerHTML = "<img border=\"0\" src=\"../images/SearchDown.gif\">"
	}
}
</script>
<!-- 
		<form action="" name="form1" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3">
						<div id="parent" style="display: inline">
							<hr></hr>
						</div>
					</td>
				</tr>
				<tr>
					<td width="10%">
						<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;公告信息</font>
					</td>
					<td colspan="2">
						<font size="2" color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					</td>
				</tr>
				
				<tr>
					<td width="10%">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img src="<%=path%>/web/images/gonggao.jpg" width="40%"
							height="40%" />
					</td>
					<td>
						<table width="80%" border="0" cellspacing="0" cellpadding="0">


							<%
								SiteManage beans = new SiteManage();
								ArrayList fylist = new ArrayList();
								fylist = beans.getGgao();

								String bt = "", ggtime = "", id = "", lrr = "";

								if (fylist != null) {
									int fycount = ((Integer) fylist.get(0)).intValue();
									for (int k = fycount; k < fylist.size() - 1; k += fycount) {
										id = (String) fylist.get(k + fylist.indexOf("ID"));
										bt = (String) fylist.get(k + fylist.indexOf("BT"));
										ggtime = (String) fylist.get(k + fylist.indexOf("GGTIME"));
										lrr = (String) fylist.get(k + fylist.indexOf("LRR"));
										if (bt == null)
											bt = "";
										if (ggtime == null)
											ggtime = "";
							%>
							<tr class="form_label">
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:dfinfo5('<%=id%>')"><%=bt%></a>
								</td>
								<td>
									<%=ggtime%>
								</td>
								<td class="form_label">
									<%=lrr%>
								</td>
							</tr>
							<%
								}
							%>
							<%
								}
							%>
							<tr>
								<td colspan="3" align="right">
									<a href="<%=path%>/web/sys/allggao.jsp"><font
										style="font-size: 12px;">更多>></font>
									</a>
								</td>
							</tr>

						</table>
					</td>

				</tr>
				<tr>
					<td colspan="3">
						<div id="parent" style="display: inline">
							<hr></hr>
						</div>
					</td>
				</tr>
			</table>
			<table style="width: 100%; margin-top: 8px" class="form_label" cellspacing="0" cellpadding="0">
						<tr>
						   <td  width="13%">&nbsp;&nbsp;
						     <font size="2" >对标管理：</font>
						   </td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
						    <td  width="10%">
						    <div>&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path%>/web/images/tongjichaxun.jpg" width="30%" height="30%"/></div>
					        </td >
						 	<td width="15%"><div id="jznhbzcx"  style="display:none;"><a href="#" onclick="Warntj3()">基站能耗标准查询 </a></div></td>
  	                     	<td ><div id="cbjzgl"  style="display:none;"><a href="#" onclick="Warntj4()">超标基站管理</a></div></td>
					     </tr> 						
						</table>
						<div id="parent" style="display:inline">
                              <hr></hr>
                        </div>
			<table style="width: 100%; margin-top: 8px" class="form_label" cellspacing="0" cellpadding="0">
						<tr>
						   <td  width="13%">&nbsp;&nbsp;
						     <font size="2" >电费缴纳明细：</font>
						   </td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
						    <td  width="10%">
						    <div>&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path%>/web/images/dianfei.jpg" width="30%" height="30%"/></div>
					        </td >
						 	<td ><div id="dfzs"  style="display:none;"><a href="#" onclick="Warn3()">电费缴纳明细查询 </a></div></td>
  	                     	<td ><div id="zddfwsh"  style="display:none;"><a href="#" onclick="Warn4()">自动电费审核</a></div></td>
						 	<td ><div id="rgdfwsh"  style="display:none;"><a href="#" onclick="Warn()">人工电费审核</a></div></td>
						 	<!--  <td ><div id="rgdfshtg"  style="display:none;"><a href="#" onclick="Warn6()" >人工电费审核通过</a></div></td> -->
					        <td ><div id="sjdfwsh"  style="display:none;"><a href="#" onclick="Warn2()">市级电费审核</a></div></td>
							<td ><div id="cwdfwsh"  style="display:none;"><a href="#" onclick="Warn1()">财务电费审核</a></div></td>
							<td ><div id="dfbbdy"  style="display:none;"><a href="#" onclick="Warn5()">电费报表打印</a></div></td>
							<!-- <td ><div id="dfbbdy"  style="display:none;">电费报表打印<a href="#" onclick="Warn5()"><font size="3" color="red"><%=dfbbdy%></font>条</a></div></td> -->
			
					     </tr> 
						
						</table>
						<div id="parent" style="display:none;">
                              <hr></hr>
                        </div>
                        <!--  
						<table style="width: 100%; margin-top: 8px" class="form_label" cellspacing="0" cellpadding="0">
						<tr>
							<td width="13%">&nbsp;&nbsp;&nbsp;<font size="2" >站点管理：</font></td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
						    <td  width="10%">
						    <div>&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path%>/web/images/zhandian.jpg" width="30%" height="30%"/></div>
					        </td >
							<td >
								<div id="zdzs"  style="display:none;"><a href="/energy/web/jizhan/sitemanage.jsp">站点管理查询</a></div></td>
							
							<td ><div id="zdshtg"  style="display:none;"><a href="#" onclick="Warn7()">站点审核通过</a></div></td>
							<td ><div id="zdwsh"  style="display:none;"><a href="#" onclick="Warn8()">站点待审核</a></div></td>
							<td ><div id="jsldbzs"  style="display:none;"><a href="#" onclick="Warn9()">结算类电表总数</a></div></td>
							<td ><div id="glldbzs"  style="display:none;"><a href="#"  onclick="Warn10()">管理类电表总数</a></div></td>
							<td >
							<div id="zdtj"  style="display:none;">
							<div style="position:relative;width:110px;height:20px;cursor: pointer;float:right;right:66px;top:1px">站点添加：</div>
							<div id="addjz" style="position:relative;width:60px;height:20px;cursor: pointer;float:right;right:-50px">
							  <img alt="" src="<%=request.getContextPath()%>/images/xinzeng.png" width="90%" height="90%" />
							  <span style="font-size:12px;position: absolute;left:16px;top:3px;color:white">添加</span>
							</div>
							</div>
							</td>
							<td ><div id="zdpldr"  style="display:none;">
							<div style="position:relative;width:110px;height:20px;cursor: pointer;float:right;right:100px;top:1px">站点批量导入：</div>
							 <div id="addpl" style="position:relative;width:60px;height:20px;cursor: pointer;float:right;right:-50px">
							  <img alt="" src="<%=request.getContextPath()%>/images/daoru.png" width="90%" height="90%" />
							  <span style="font-size:12px;position: absolute;left:16px;top:3px;color:white">导入</span>
							  </div>
							  </div>
					       </td>					
						</tr>
						</table>
						-->
						<div id="parent" style="display:inline">
                              <hr></hr>
                        </div>
						<!--<table class="form_label" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td width="13%">&nbsp;&nbsp;&nbsp;<font size="2" >单据管理：</font></td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td  width="10%">
						   <div> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path%>/web/images/danju.jpg" width="35%" height="35%"/></div>
					        </td >
							<td>
							<div id="dfdlrd"  style="width:100%;height:100%;display:none;">
							<div style="position:relative;width:110px;height:20px;cursor: pointer;float:right;right:76px;top:7px">电费单录入：</div>
							  <div id="dfdlr" style="position:relative;width:60px;height:20px;cursor: pointer;float:right;right:-50px;top:7px">
							  <img alt="" src="<%=request.getContextPath()%>/images/xinzeng.png" width="90%" height="90%" />
							  <span style="font-size:12px;position: absolute;left:15px;top:3px;color:white">添加</span>
							  </div>
							  </div>
							</td>
							
							<td>&nbsp;&nbsp;
							<div id="dfdpldr"  style="width:100%;height:100%;display:none;">
							<div style="position:relative;width:110px;height:20px;cursor: pointer;float:right;right:76px;top:1px">电费单批量导入：</div>
							  <div id="dfdpl" style="position:relative;width:60px;height:20px;cursor: pointer;float:right;right:-80px;top:0px">
							  <img alt="" src="<%=request.getContextPath()%>/images/daoru.png" width="90%" height="90%" />
							  <span style="font-size:12px;position: absolute;left:16px;top:3px;color:white">导入</span>
							  </div>
							  </div>
							</td>
							<td >
							&nbsp;&nbsp;<div id="htdlrd"  style="width:100%;height:100%;display:none;">
							<div style="position:relative;width:110px;height:20px;cursor: pointer;float:right;right:76px;top:1px">合同单录入：</div>
							  <div id="htdlr" style="position:relative;width:60px;height:20px;cursor: pointer;float:right;right:-55px">
							  <img alt="" src="<%=request.getContextPath()%>/images/xinzeng.png" width="90%" height="90%" />
							  <span style="font-size:12px;position: absolute;left:15px;top:3px;color:white">添加</span>
							  </div>
							  </div>
							</td>
							<td >
							&nbsp;&nbsp;<div id="cklrd"  style="width:100%;height:100%;display:none;">
							<div style="position:relative;width:110px;height:20px;cursor: pointer;float:right;right:76px;top:1px">插卡录入：</div>
							  <div id="cklr" style="position:relative;width:60px;height:20px;cursor: pointer;float:right;right:-40px">
							  <img alt="" src="<%=request.getContextPath()%>/images/xinzeng.png" width="90%" height="90%" />
							  <span style="font-size:12px;position: absolute;left:15px;top:3px;color:white">添加</span>
							  </div>
							  </div>
							</td>
							<td >
							&nbsp;&nbsp;<div id="yzlrd"  style="width:100%;height:100%;display:none;">
							<div style="position:relative;width:110px;height:20px;cursor: pointer;float:right;right:86px;top:1px">预支录入：</div>
							  <div id="yzlr" style="position:relative;width:60px;height:20px;cursor: pointer;float:right;right:-30px">
							  <img alt="" src="<%=request.getContextPath()%>/images/xinzeng.png" width="90%" height="90%" />
							  <span style="font-size:12px;position: absolute;left:15px;top:3px;color:white">添加</span>
							  </div>
							  </div>
						</td>
				
					    </tr>
					    </table>
					    <div id="parent" style="display:inline">
                              <hr></hr>
                        </div>
                        <table style="width: 100%; margin-top: 8px" class="form_label">
						<tr>
						     <td width="13%">&nbsp;&nbsp;&nbsp;<font size="2" >基础查询：</font></td>
						</tr>
						<tr>
						     <td  width="10%">&nbsp;&nbsp;&nbsp;<img src="<%=path%>/web/images/jichu.jpg" width="30%" height="30%"/></td >
						 	 <td ><div id="zdcx"  style="width:100%;height:100%;display:none;"><a href="<%=path%>/web/query/basequery/stationPointQuery.jsp" >站点查询</a></div></td>
  	                         <td ><div id="dlcx"  style="width:100%;height:100%;display:none;"><a href="<%=path%>/web/query/basequery/ammeterDegreeQuery.jsp" >电量查询	</a></div></td>
						     <td ><div id="dfjnmx"  style="width:100%;height:100%;display:none;"><a href="<%=path%>/web/query/basequery/electricFeesQuery.jsp" >电费缴纳明细查询</a></div></td>
						     <td ><div id="yffcx"  style="width:100%;height:100%;display:none;"><a href="<%=path%>/web/query/basequery/prepaymentQuery.jsp" >预付费查询</a></div></td>
					     </tr> 
						
						</table>


		--%></form>-->
		
		
	<marquee scrollamount="3" direction="right" behavior="alternate" onmouseover="this.stop()" onmouseout="this.start()"><font size=2 color="red"><div id="scmessage"></div></font></marquee>

<script type="text/javascript">

var role = '<%=roleid%>';

if (role == "62" || role == "65" 
		|| role == "1") {//区县录入,,,
	document.getElementById("dfzs").style.display = "block";//电费总数
	document.getElementById("zdzs").style.display = "block";//站点总数
	document.getElementById("jsldbzs").style.display = "block";//结算类电表总数
	document.getElementById("glldbzs").style.display = "block";//管理类电表总数

}
if (role == "101" || role == "102" || role == "65" || role == "1") {
	//document.getElementById("dfdlrd").style.display = "block";//电费单录入
	//document.getElementById("dfdpldr").style.display = "block";//电费单批量导入
	//document.getElementById("htdlrd").style.display = "block";//合同单录入
	//document.getElementById("cklrd").style.display = "block";//插卡录入
	//document.getElementById("yzlrd").style.display = "block";//预支录入
}
if (role == "62" || role == "65" || role == "1") {
	//document.getElementById("zdpldr").style.display = "block";//站点批量导入
	//document.getElementById("zdtj").style.display = "block";//站点添加
	document.getElementById("zdwsh").style.display = "block";//站点未审核
	document.getElementById("zdshtg").style.display = "block";//站点审核通过
	document.getElementById("zddfwsh").style.display = "block";//自动电费未审核
	document.getElementById("rgdfwsh").style.display = "block";//人工电费未审核
	//document.getElementById("rgdfshtg").style.display = "block";//人工电费审核通过
	document.getElementById("dfbbdy").style.display = "block";//电费报表打印
}
if (role == "102" || role == "101" ){
	document.getElementById("dfzs").style.display = "block";//电费总数
	document.getElementById("zdzs").style.display = "block";//站点总数
	document.getElementById("jsldbzs").style.display = "block";//结算类电表总数
	document.getElementById("glldbzs").style.display = "block";//管理类电表总数
	document.getElementById("zdshtg").style.display = "block";//站点审核通过
	document.getElementById("zddfwsh").style.display = "block";//自动电费未审核
	document.getElementById("rgdfwsh").style.display = "block";//人工电费未审核
	//document.getElementById("rgdfshtg").style.display = "block";//人工电费审核通过
	document.getElementById("dfbbdy").style.display = "block";//电费报表打印
}
if (role == "65" || role == "1") {
	document.getElementById("sjdfwsh").style.display = "block";//市级电费审核未通过
	document.getElementById("cwdfwsh").style.display = "block";//财务电费未审核

}
if (role == "64" || role == "63" || role == "65" || role == "102"
		|| role == "1" || role == "104" || role == "81" || role == "82") {
	//document.getElementById("zdcx").style.display = "block";//站点查询
	//document.getElementById
	//("dlcx").style.display = "block";//电量查询
	//document.getElementById("dfcx").style.display="block";//电费查询
	//document.getElementById("dfjnmx").style.display = "block";//电费缴纳明细
	//document.getElementById("yffcx").style.display = "block";//预付费查询
}
if (role == "64" || role == "63" || role == "65" || role == "1" || role == "81" || role == "82"|| role == "83"|| role == "84"|| role == "85") {
	document.getElementById("jznhbzcx").style.display = "block";//基站能耗标准查询
	document.getElementById("cbjzgl").style.display = "block";//超标基站管理
}
if(role=="62"||role=="63"||role=="64"){
	var url = "<%=request.getContextPath()%>/servlet/scmessage?action=dishi";
	var para="dishi";
	sendRequest(url,para);
}
if(role == "65"){//市系统
	var url = "<%=request.getContextPath()%>/servlet/scmessage?action=dishi2";
	var para="dishi2";
	sendRequest(url,para);
}
if(role == "104"){
	var url = "<%=request.getContextPath()%>/servlet/scmessage?action=quxian";
	var para="quxian";
	sendRequest(url,para);
}
if(role == "102"){//区县管理
	var url = "<%=request.getContextPath()%>/servlet/scmessage?action=quxian2";
	var para="quxian2";
	sendRequest(url,para);
}



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

	///////////////////////////////////////////////////////////
	function sendRequest(url,para) {

		createXMLHttpRequest();
	
		XMLHttpReq.open("POST", url, true);
		
		if(para=="dishi"){
			XMLHttpReq.onreadystatechange = processResponse_dishi;//指定响应函数
		}else if(para=="quxian"){
			XMLHttpReq.onreadystatechange = processResponse_quxian;//指定响应函数
		}else if(para=="dishi2"){
			XMLHttpReq.onreadystatechange = processResponse_dishi2;//指定响应函数
		}else if(para=="quxian2"){
			XMLHttpReq.onreadystatechange = processResponse_quxian2;//指定响应函数
		}
		
		XMLHttpReq.send(null);  
	}
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数
    function processResponse_dishi() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	
            	var res = XMLHttpReq.responseText;
        		var v=res.split("@");
        	    var v1=v[0];
        	    var v2=v[1];
            	var message= document.getElementById("scmessage");
				message.innerHTML="<a href='#' onclick='Warn12()'>您有<font color='red'>"+v1+"</font>条未签收工单请签收</a></br><a href='#' onclick='Warn13()'>您有<font color='red'>"+v2+"</font>条整改工单未派发</a>";
				message.style.display = "block";
            } 
        }
    }
		// 处理返回信息函数
    function processResponse_dishi2() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	
            	var res = XMLHttpReq.responseText;
        		var v=res.split("@");
        	    var v1=v[0];
        	    var v2=v[1];
        	    var v3=v[2];
            	var message= document.getElementById("scmessage");
				message.innerHTML="<a href='#' onclick='Warn12()'>您有<font color='red'>"
				+v1+"</font>条未签收工单请签收</a></br><a href='#' onclick='Warn13()'>您有<font color='red'>"+
				v2+"</font>条整改工单未派发</a></br><a href='#' onclick='Warn15()'>您有<font color='red'>"+
				v3+"</font>条修改申请未审核,请审核</a>";
				message.style.display = "block";
            } 
        }
    }
	 function processResponse_quxian() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	
            	var res = XMLHttpReq.responseText;
        		
            	var message= document.getElementById("scmessage");
				message.innerHTML="<a href='#' onclick='Warn14()'>您有"+res+"条未签收工单请签收</a>";
				message.style.display = "block";
            } 
        }
    }
	 	 function processResponse_quxian2() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	
            	var res = XMLHttpReq.responseText;
        		var v=res.split("@");
        	    var v1=v[0];
        	    var v2=v[1];
            	var message= document.getElementById("scmessage");
				message.innerHTML="<a href='#' onclick='Warn14()'>您有"+v1+"条未签收工单请签收</a></br><a href='#' onclick='Warn16()'>您有<font color='red'>"+
				v2+"</font>条管理电量未审核，请审核</a>";
				message.style.display = "block";
            } 
        }
    }
</script>
	</body>
</html>
