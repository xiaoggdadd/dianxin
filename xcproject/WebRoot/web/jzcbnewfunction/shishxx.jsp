o<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account,com.noki.newfunction.dao.*,com.noki.newfunction.javabean.*"%>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean"%>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String sss=request.getParameter("changjianow");

System.out.println("sss:"+sss);
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
	String accountid = account.getAccountName();
	String loginId = account.getAccountId();
	String id=request.getParameter("zdid")!=null?request.getParameter("zdid"):"";
                          String iddb=(String)session.getAttribute("ida");
                          if(null==id||"".equals(id)){
                            id=iddb;
                          }
                          String msg=(String)session.getAttribute("msg");
                          if(null==msg){msg="";}
                          session.removeAttribute(msg);
	cbzddao  dao=new cbzddao();
	cbzddao  dao1=new cbzddao();
	Zdinfo bean=new Zdinfo();
	//获取站点信息
	String zdid = "";//站点id
	String zdname = "";//站点名称
	String shi="";
	String xian="";
	String xiaoqu="";
	String szdq = "";//所属区域
	String accountmonth = "";//报账月份（对比月份）
	String cbbili = "";//超标比例
	String entryperson = "";//省级录入人
	String entrytime = "";//省级录入时间
	String zgaskinstruction = "";//整改要求说明
	String fjstruction = "";//附件说明
	String csms="";//测试描述
	String yyfx="";//原因分析
	String csr="";//测试人
	
	//查找站点原11大类信息
	String g2last = "";//2G设备（原始）
	String g3last = ""; //3G设备（原始）
	String zplast = "0";//载频（原始）
	String zslast = "0";//载扇（原始）
	String changjialast = "";//厂家（原始）
	String jztypelast = "";//基站类型（原始）
	String shebeilast = "";//设备型号（原始）
	String bbulast = "0";//bbu数量（原始）
	String rrulast = "0";//rru数量（原始）
	String ydshebeilast = "0";//移动设备数量（原始）
	String gxgwsllast = "0";//共享固网设备数量（原始）
	String ydgxsblast="0";//移动共享设备数量(原始)
	String dxgxsblast="";//电信共享设备数量(原始)
	String g2xqlast="";//2g小区(原始)
	String g3sqlast="";//3g小区(原始)
	
	//查找站点新11大类信息
	String g2 = "";//2G设备（新）
	String g3 = ""; //3G设备（新）
	int zp = 0;//载频（新）
	int zs = 0;//载扇（新）
	String changjia = "";//厂家（新）
	String jztype = "";//基站类型（新）
	String shebei = "";//设备型号（新）
	int bbu = 0;//bbu数量（新）
	int rru = 0;//rru数量（新）
	int ydshebei = 0;//移动设备数量（新）
	int gxgwsl = 0;//共享固网设备数量（新）
	String g2xqnow="";//2g小区（新）
	String g3sqnow="";//3g扇区（新）
	String ydgxsbnow="";//移动共享设备数量（新）
	String dxgxsbnow="";//电信共享设备数量（新）
	int gxgwslnow = 0;//固网共享设备
	String jsdbds="";//电表读数
	String kgzfh="";//开关直流负荷
	String ydzlfh="";//移动直流负荷
	String dxzlfh="";//电信直流负荷
	String gyzlfh="";//固移直流负荷
	String zlygfh="";//拉远直流负荷
	String zlzfh="";//直流总负荷
	String jlzfh="";//交流总负荷
	String qsdb="";//全省定标
	String bdhdl="";//本地额定耗电量
	String sjname;//省级附件名称
	String qxname;//区县附件名称
	bean=dao.getShiXx(id,loginId);
	int r=dao1.CheckQx(id);
	zdid=bean.getZdid();
	zdname=bean.getZdname();
	shi=bean.getShi();
	xian=bean.getXian();
	xiaoqu=bean.getXiaoqu();
	ydgxsbnow=bean.getYdgxsb();
	accountmonth=bean.getCbsj();
	entryperson=bean.getSjlrr();
	entrytime=bean.getSjlrsj();
	sjname=bean.getSjname();
	qxname=bean.getQxname();
	DecimalFormat mat=new DecimalFormat("0.00");
	cbbili=bean.getCbbl();
	if(null!=cbbili&&!"".equals(cbbili)){
		cbbili=mat.format(Double.parseDouble(cbbili)*100)+"%";
	}else{
		cbbili="";
	}
	zgaskinstruction=bean.getZgyq();
	csms=bean.getCsms();
	yyfx=bean.getYyfx();
	csr=bean.getCszrr();
	g2last=bean.getG2();
	
	if("1".equals(g2last)){g2last="是";}else{g2last="否";}
	g3last=bean.getG3();
	if("1".equals(g3last)){g3last="是";}else{g3last="否";}
	zplast=bean.getZp();
	if(null==zplast||"".equals(zplast)){zplast="0";}

	zslast=bean.getZs();
	if(null==zslast||"".equals(zslast)){zslast="0";}
	changjialast=bean.getChangjia();
	if(null==changjialast){changjialast="";}
	jztypelast=bean.getJztype();
	if(null==jztypelast){jztypelast="";}
	shebeilast=bean.getShebei();
	if(null==shebeilast){shebeilast="";}
	bbulast=bean.getBbu();
	if(null==bbulast||"".equals(bbulast)){bbulast="0";}
	rrulast=bean.getRru();
	if(null==rrulast||"".equals(rrulast)){rrulast="0";}
	ydshebeilast=bean.getYdshebei();
	if(null==ydshebeilast||"".equals(ydshebeilast)){ydshebeilast="0";}
	gxgwsllast=bean.getGxgwsl();
	if(null==gxgwsllast||"".equals(gxgwsllast)){gxgwsllast="0";}
	ydgxsbnow=bean.getYdgxsb();
	if(null==ydgxsbnow||"".equals(ydgxsbnow)){ydgxsbnow="0";}
	//ydgxsb=bean.getYdgxsb();
	//if(null==ydgxsb||"".equals(ydgxsb)){ydgxsb="0";}
	dxgxsbnow=bean.getDxgxsb();
	if(null==dxgxsbnow||"".equals(dxgxsbnow)){dxgxsbnow="0";}
	jsdbds=bean.getDbds();
	if(null==jsdbds||"".equals(jsdbds)){jsdbds="0";}
	kgzfh=bean.getKGDYZLFH();
	if(null==kgzfh||"".equals(kgzfh)){kgzfh="0";}
	
	ydzlfh=bean.getYDGXSBZLFH();
	if(null==ydzlfh||"".equals(ydzlfh)){ydzlfh="0";}
	dxzlfh=bean.getDXGXSBZLFH();
	if(null==dxzlfh||"".equals(dxzlfh)){dxzlfh="0";}
	gyzlfh=bean.getGYGXSBZLFH();
	if(null==gyzlfh||"".equals(gyzlfh)){gyzlfh="0";}
	zlygfh=bean.getZYYGSBZLFH();
	if(null==zlygfh||"".equals(zlygfh)){zlygfh="0";}
	g2xqlast=bean.getJg2xq();
	if(null==g2xqlast||"".equals(g2xqlast)){g2xqlast="0";}
	g3sqlast=bean.getJg3sq();
	if(null==g3sqlast||"".equals(g3sqlast)){g3sqlast="0";}
	g2xqnow=bean.getG2xq();
	if(null==g2xqnow||"".equals(g2xqnow)){g2xqnow="0";}
	g3sqnow=bean.getG3sq();
	if(null==g2xqnow||"".equals(g2xqnow)){g2xqnow="0";}
	ydgxsblast=bean.getJydgxsb();
	if(null==ydgxsblast||"".equals(ydgxsblast)){ydgxsblast="0";}
	dxgxsblast=bean.getJdxgxsb();
	if(null==dxgxsblast||"".equals(dxgxsblast)){dxgxsblast="0";}
	gxgwslnow=bean.getXgxgwsl();

	g2=bean.getXg2();
		if("1".equals(g2)){
		g2="是";
		}else{
		g2="否";
		}
	g3=bean.getXg3();
	if("1".equals(g3)){
		g3="是";
	}else{
		g3="否";
	}
	zp=bean.getXzp();
	
	zs=bean.getXzs();
	changjia=bean.getXchangjia();
	if(null==changjia||"".equals(changjia)){changjia="";}
	jztype=bean.getXjztype();
	if(null==jztype||"".equals(jztype)){jztype="";}
	shebei=bean.getXshebei();
	if(null==shebei||"".equals(shebei)){shebei="";}
	bbu=bean.getXbbu();
	rru=bean.getXrru();
	ydshebei=bean.getXydshebei();
	//gxgwsl=bean.getXgxgwsl();
	szdq=shi+xian+xiaoqu;
String sf=	bean.getJztype();
zlzfh=bean.getZlzfh();
jlzfh=bean.getJlzfh();
qsdb=bean.getQsdb();
bdhdl=bean.getBdhdl();
if(null==sf||"".equals(sf)){sf="";}
//System.out.println("------"+sjname+"-------"+qxname);
if(sjname==null||sjname.equals("")||sjname.equals("null")||sjname.equals(" ")||sjname==""){
	 sjname="";
}
if(qxname==null||qxname.equals("")||qxname.equals("null")||qxname.equals(" ")||qxname==""){
	qxname="";
}
if(zlzfh==null||zlzfh.equals("")||zlzfh.equals("null")||zlzfh.equals(" ")||zlzfh==""){
	zlzfh="0";
}
if(jlzfh==null||jlzfh.equals("")||jlzfh.equals("null")||jlzfh.equals(" ")||jlzfh==""){
	jlzfh="0";
}
if(qsdb==null||qsdb.equals("")||qsdb.equals("null")||qsdb.equals(" ")||qsdb==""){
	qsdb="0";
}
if(bdhdl==null||bdhdl.equals("")||bdhdl.equals("null")||bdhdl.equals(" ")||bdhdl==""){
	bdhdl="0";
}


%>
<html>
	<head>
		<title>测试描述修改</title>

		<style>
.style1 {
	color: red;
	font-weight: bold;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	width: 140px;
}

.form_labell {
	text-align: center;
	font-family: 宋体;
	font-size: 12px;
	width: 130px;
}

.form {
	height: 19px;
	width: 130px;
}

.formjc {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	height: 19px;
	width: 800px;
}

.formm {
	text-align: right;
	height: 19px;
	width: 80px;
}

.formr {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: right;
	height: 19px;
	width: 130px;
}
.formr5 {
	text-align: right;
	height: 19px;
	width: 130px;
}
.formr1 {
	text-align: right;
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	height: 19px;
	width: 130px;
	color:red;
}

.formcenter {
	text-align: center;
	height: 19px;
	width: 130px;
}

.form1 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	height: 19px;
	width: 130px;
}

.formcc {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	height: 19px;
	width: 800px;
}

.form2 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: right;
	height: 19px;
	width: 130px;
}
.form4 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: left;
	color:red;
	height: 19px;
	width: 130px;
}
.form3 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: left;
	height: 19px;
	width: 130px;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script language="javascript">
var path = '<%=path%>';

function shangchuan() {
	$("#errorInfo").text("");
	var month = $("#month").val();

	document.form1.action = path + "/servlet/FilesUploadServlet?month=" + month;
	document.form1.submit()
}
	function baocun(){
	var idd='<%=id%>';
	var jlfh=document.form1.jlzfh.value;
	var zlfh=document.form1.zlzfh.value;   
	var edhdl=document.form1.bdhdl.value;
	var qsdb=document.form1.qsdb.value;
	   	document.form1.action=path+"/servlet/Cbzdsjshenhe?action=sjshxxbc&j="+jlfh+"&z="+zlfh+"&e="+edhdl+"&q="+qsdb+"&id="+idd;
		document.form1.submit();
	}

			$(function() {
			
			$("#baocun").click(function(){
			baocun();
				
			});
		});
</script>

<script language="javascript">

var path = '<%=path%>';
$(function() {
	$("#saveBtn").click(function() {
		saveDegree();
	});
	$("#resetBtn").click(function() {
		$("#form")[0].reset();
	});
	$("#cancelBtn").click(function() {
		window.history.back();
	});
	$("#uploading").click(function() {
		shangchuan();
	});

});
</script>
	</head>
	<jsp:useBean id="roleBean" scope="page"
		class="com.noki.mobi.sys.javabean.RoleBean"></jsp:useBean>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body class="body" style="overflow-x: hidden;">
		<br>
		<LINK href="../../images/css.css" type="text/css" rel=stylesheet />
		<form action="" name="form1" method="POST" id="form">
			<table border="0" width="100%">
				<tr>
					<td width="83%" scope="2">
						<table width="100%" border="0" cellspacing="1" cellpadding="1">
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									站点信息
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										站点编号：
									</div>
								</td>
								<td>
									<input type="text" name="zdid" readonly="true"
										value="<%=zdid%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										站点名称：
									</div>
								</td>
								<td>
									<input type="text" name="zdname" readonly="true"
										value="<%=zdname%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										所属区域：
									</div>
								</td>
								<td>
									<input type="text" name="szdq" readonly="true"
										value="<%=szdq%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										对标月份：
									</div>
								</td>
								<td>
									<input type="text" name="accountmonth" readonly="true"
										value="<%=accountmonth%>" class="form1" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										超标比例：
									</div>
								</td>
								<td>
									<input type="text" name="cbbili" readonly="true"
										value="<%=cbbili%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										录入人：
									</div>
								</td>
								<td>
									<input type="text" name="entryperson" readonly="true"
										value="<%=entryperson%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										录入时间：
									</div>
								</td>
								<td>
									<input type="text" name="entrytime" readonly="true"
										value="<%=entrytime%>" class="form1" />
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										整改要求说明：
									</div>
								</td>
								<td colspan='7' class="form1">
									<input type="text" name="zgaskinstruction" readonly="true"
										value="<%=zgaskinstruction%>" class="formcc" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										附件说明：
									</div>
								</td>
								<%if(!sjname.equals("")){ %>
								<td colspan='7'>
									<div align="center" ><a href="#" onclick="modifyjz('<%=id%>')"><font  style="font-size: 12px;">下载</font></a></div>
								</td>
								<%}else{%>
								<td colspan='7'>
									<div align="center" ><font  style="font-size: 12px;">无</font></div>
								</td>
								<%} %>
							</tr>
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									原11大类信息
								</td>
							</tr>
							<tr>
								
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													2G设备：
												</div>
											</td>
											<td>
											<input type="text" name="g2last" id="g2last" value="<%=g2last%>" readonly="readonly" class="form3">
											</td>
											<td bgcolor="#DDDDDD" class="form_label" >
												<div>
													3G设备：
												</div>
											</td>
											<td>
											<input type="text" name="g3last" id="g3last" value="<%=g3last%>" readonly="readonly" class="form3">
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载频：
												</div>
											</td>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=zplast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载扇：
												</div>
											</td>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=zslast%>" class="form2" />
											</td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													厂家：
												</div>
											</td>
											<td>
								<input type="text" name="g2last" id="changjialast" value="<%=changjialast%>" readonly="readonly" class="form3">
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													基站类型：
												</div>
											</td>
											<td>
							<input type="text" name="jztypelast" id="jztypelast" value="<%=jztypelast%>" readonly="readonly" class="form3">
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													设备类型：
												</div>
											</td>
											<td>
									<input type="text" name="shebeilast" id="shebeilast" value="<%=shebeilast%>" readonly="readonly" class="form3">
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													拉远BBU：
												</div>
											</td>
											<td>
												<input type="text" name="bbulast" readonly="true"
													value="<%=bbulast%>" class="form2" />
											</td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													远供RRU：
												</div>
											</td>
											<td>
												<input type="text" name="rrulast" readonly="true"
													value="<%=rrulast%>" class="form2" />
											</td>
											<!-- <td bgcolor="#DDDDDD" class="form_label">
												<div>
													移动设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="ydshebeilast" readonly="true"
													value="<%=ydshebeilast%>" class="form2" />
											</td> -->
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													固移共享设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="gxgwsllast" readonly="true"
													value="<%=gxgwsllast%>" class="form2" />
											</td>
												<td bgcolor="#DDDDDD" class="form_label">
												<div>
													电信共享设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="dxgxsblast" value="<%=dxgxsblast%>" class="formr"  readonly="readonly"/>
											</td>
											
											
											<td></td>
											<td></td>
										</tr>
										<tr>
										<td bgcolor="#DDDDDD" class="form_label">
												<div>
													移动共享设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="ydgxsblast" value="<%=ydgxsblast%>" class="formr"  readonly="readonly"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													2G小区：
												</div>
											</td>
											<td>
												<input type="text" name="g2xqlast" readonly="true"
													value="<%=g2xqlast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													3G扇区：
												</div>
											</td>
											<td>
												<input type="text" name="g3sqlast" readonly="true"
													value="<%=g3sqlast%>" class="form2" />
											</td>
										</tr>
									<!-- </table>
								</td>
							</tr> -->
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									测试11大类
								</td>
							</tr>
							<tr>
								<!-- <td colspan="8">
									<table>
										<tr>
											<td rowspan='3' bgcolor="#DDDDDD" class="form_labell">
												测试11大类
											</td> -->
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													2G设备：
												</div>
											</td>
											<%if(g2.equals(g2last)){%>
											<td>
						                       <input type="text" name="g2now" id="g2now" value="<%=g2%>" readonly="readonly" class="form3">
											</td>
											<%}else{%>
											<td>
											<input type="text" name="g2now" id="g2now" value="<%=g2%>" readonly="readonly" class="form4">
										<span class="style1">*</span>
											</td>
											<% }%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													3G设备：
												</div>
											</td>
											<%if(g3.equals(g3last)){%>
											<td>
				                            <input type="text" name="g3now" id="g3now" value="<%=g3%>" readonly="readonly" class="form3">
											</td>
											<%}else{%>
											<td>
				                            <input type="text" name="g3now" id="g3now" value="<%=g3%>" readonly="readonly" class="form4">
											<span class="style1">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载频：
												</div>
											</td>
											<%if(zp==Integer.parseInt(zplast)){%>
											<td>
												<input type="text" name="zpnow" value="<%=zp %>" class="formr" readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
												<input type="text" name="zpnow" value="<%=zp %>" class="formr1" readonly="readonly"/>
											<span class="style1">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载扇：
												</div>
											</td>
											<%if(zs==Integer.parseInt(zslast)){%>
											<td>
												<input type="text" name="zsnow" value="<%=zs %>" class="formr" readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
												<input type="text" name="zsnow" value="<%=zs %>" class="formr1"  readonly="readonly"/>
											<span class="style1">*</span>
											</td>
											<%}%>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													厂家：
												</div>
											</td>
											<%if(changjia.equals(changjialast)){%>
											<td>
											<input type="text" name="changjianow" id="changjianow" value="<%=changjia%>" readonly="readonly" class="form3" readonly="readonly">
											</td>
											<%}else{%>
											<td>
											<input type="text" name="changjianow" id="changjianow" value="<%=changjia%>" readonly="readonly" class="form4" readonly="readonly">
											<span class="style1">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													基站类型：
												</div>
											</td>
											<%if(jztype.equals(jztypelast)){%>
											<td>
											<input type="text" name="jztypenow" id="jztypenow" value="<%=jztype%>" readonly="readonly" class="form3" readonly="readonly">
											</td>
											<%}else{%>
											<td>
											<input type="text" name="jztypenow" id="jztypenow" value="<%=jztype%>" readonly="readonly" class="form4" readonly="readonly">
											<span class="style1">*</span>
											</td>
											
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label" >
												<div>
													设备类型：
												</div>
											</td>
											<%if(shebei.equals(shebeilast)){%>
											<td>
											<input type="text" name="shebeinow" id="shebeinow" value="<%=shebei%>" readonly="readonly" class="form3" readonly="readonly">
											</td>
											<%}else{%>
												<td>
												<input type="text" name="shebeinow" id="shebeinow" value="<%=shebei%>" readonly="readonly" class="form4" readonly="readonly">
											<span class="style1">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													拉远BBU：
												</div>
											</td>
											<%if(bbu==Integer.parseInt(bbulast)){%>
											<td>
												<input type="text" name="bbunow" value="<%=bbu%>" class="formr"  readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
												<input type="text" name="bbunow" value="<%=bbu%>" class="formr1"  readonly="readonly"/>
												<span class="style1">*</span>
											</td>
											<%}%>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													远供RRU：
												</div>
											</td>
											<%if(rru==Integer.parseInt(rrulast)){%>
											<td>
												<input type="text" name="rrunow" value="<%=rru%>" class="formr"  readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
												<input type="text" name="rrunow" value="<%=rru%>" class="formr1" readonly="readonly"/>
												<span class="style1">*</span>
											</td>
											<%}%>
											<!-- <td bgcolor="#DDDDDD" class="form_label">
												<div>
													移动设备数量：
												</div>
											</td>
											<%if(ydshebei==Integer.parseInt(ydshebeilast)){%>
											<td>
												<input type="text" name="ydshebei" value="<%=ydshebei%>" class="formr"  readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
												<input type="text" name="ydshebei" value="<%=ydshebei%>" class="formr1"  readonly="readonly"/>
											<span class="style1">*</span>
											</td>
											<%}%> -->
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													固移共享设备数量：
												</div>
											</td>
											<%if(gxgwslnow==Integer.parseInt(gxgwsllast)){%>
											<td>
												<input type="text" name="gxgwslnow" value="<%=gxgwslnow%>" class="formr"  readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
												<input type="text" name="gxgwslnow" value="<%=gxgwslnow%>" class="formr1"  readonly="readonly"/>
											<span class="style1">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													电信共享设备数量：
												</div>
											</td>
											<%if(dxgxsbnow.equals(dxgxsblast)){%>
											<td>
						                       <input type="text" name="dxgxsbnow" id="dxgxsbnow" value="<%=dxgxsbnow%>" readonly="readonly" class="formr">
											</td>
											<%}else{%>
											<td>
											<input type="text" name="dxgxsbnow" id="dxgxsbnow" value="<%=dxgxsbnow%>" readonly="readonly" class="formr1">
											<span class="style1">*</span>
											</td>
											<% }%>
											<td></td>
											<td></td>
										</tr>
										<tr>
										<td bgcolor="#DDDDDD" class="form_label">
												<div>
													移动共享设备数量：
												</div>
											</td>
											<%if(ydgxsbnow.equals(ydgxsblast)){%>
											<td>
						                       <input type="text" name="ydgxsbnow" id="ydgxsbnow" value="<%=ydgxsbnow%>" readonly="readonly" class="formr">
											</td>
											<%}else{%>
											<td>
											<input type="text" name="ydgxsbnow" id="ydgxsbnow" value="<%=ydgxsbnow%>" readonly="readonly" class="formr1">
											<span class="style1">*</span>
											</td>
											<% }%>
											<td bgcolor="#DDDDDD" class="form_label">
											
												<div>
													2G小区：
												</div>
											</td>
											
											<%if(g2xqnow.equals(g2xqlast)){%>
											<td>
						                       <input type="text" name="g2xqnow" id="g2xqnow" value="<%=g2xqnow%>" readonly="readonly" class="formr">
											</td>
											<%}else{%>
											<td>
											<input type="text" name="g2xqnow" id="g2xqnow" value="<%=g2xqnow%>" readonly="readonly" class="formr1">
											<span class="style1">*</span>
											</td>
											<% }%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													3G扇区：
												</div>
											</td>
											<%if(g3sqnow.equals(g3sqlast)){%>
											<td>
						                       <input type="text" name="g3sqnow" id="g3sqnow" value="<%=g3sqnow%>" readonly="readonly" class="formr">
											</td>
											<%}else{%>
											<td>
											<input type="text" name="g3sqnow" id="g3sqnow" value="<%=g3sqnow%>" readonly="readonly" class="formr1">
											<span class="style1">*</span>
											</td>
											<% }%>
											
										</tr>
									<!-- </table>
								</td>
							</tr> -->
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									测试描述及原因分析
								</td>
							</tr>
							<!--<tr>
								  <td bgcolor="#DDDDDD" class="form_label">
									<div>
										测试描述：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="teststruction" value="<%=csms%>" class="formjc" />
								</td>
							</tr>-->
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>结算电表读数：</div>
								</td>
								<td>
									<input type="text" name="jsdbds" value="<%=jsdbds%>" class="formr" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>开关电源直流总负荷(A)</div>
								</td>
								<td>
									<input type="text" name="kgzfh" value="<%=kgzfh%>" class="formr"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>移动共享设备直流负荷(A)</div>
								</td>
								<td>    
									<input type="text" name="ydzlfh" value="<%=ydzlfh%>" class="formr"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>电信共享设备直流负荷(A)</div>
								</td>
								<td>
									<input type="text" name="dxzlfh" value="<%=dxzlfh%>" class="formr"/>
								</td>
							</tr>
							<tr>  
								<td bgcolor="#DDDDDD" class="form_label">
									<div>固移共享设备直流负荷(A)</div>
								</td>
								<td>
									<input type="text" name="gyzlfh" value="<%=gyzlfh%>" class="formr"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>直流远供负荷(A)</div>
								</td>
								<td>
									<input type="text" name="zlygfh" value="<%=zlygfh%>" class="formr"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>交流总负荷(A)(不含空调)</div>
								</td>
								<td>
									<input type="text"  name="jlzfh" value="<%=jlzfh %>" onblur="getJl()" class="formr5"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>直流总负荷(A)(不含空调)</div>
								</td>
								<td>
									<input type="text"  name="zlzfh" value="<%=zlzfh %>" onblur="getZl()" class="formr5"/>
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>额定耗电量(本地标)</div>
								</td>
								<td>
									<input type="text"  name="bdhdl" value="<%=bdhdl %>" class="formr5"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>省定标(不含空调)</div>
								</td>
								<td>
									<input type="text"  name="qsdb" value="<%=qsdb %>" class="formr5" />
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										原因分析：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="reasonanalyse" value="<%=yyfx%>" class="formjc" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										测试人：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="testperson" value="<%=csr%>" class="formr" />
								</td>
							</tr>
							<tr height="19px">
									<td bgcolor="#DDDDDD" class="form_label">
									<div>
										附件说明：
									</div>
								</td>
								<%if(r==1){ %>
								<td colspan='7'>
									<div align="center" ><a href="#" onclick="modifyjz2('<%=id%>')"><font  style="font-size: 12px;">下载</font></a></div>
								</td>
								<%}else{ %>
								<td colspan='7'>
									<div align="center" ><font  style="font-size: 12px;">无</font></div>
								</td>
								<%} %>		
									<!--  <td>
										<font color="red" size="9px"><%=msg%></font>
								
								</td>-->	
						</tr>
							<tr>
					<td align="right">
						<div id="baocun" style="width:85px;height:23px;cursor:pointer;float:right;position: relative; right:-150px; top: 0px">
						<img  src="<%=path %>/images/mmcz.png" width="100%" height="100%">
						 <span style="font-size: 12px; position: absolute; left: 35px; top: 5px; color: white">保存</span>
						
						</div>
					</td>		
					</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<script type="text/javascript">
	  function modifyjz(id){
   // alert(path);
     		document.form1.action=path+"/servlet/cbzdfjxiazai?id="+id+"&bzw=1";
     		 document.form1.submit();

    }
	  function modifyjz2(id){
   // alert(path);
     		document.form1.action=path+"/servlet/cbzdfjxiazai2?id="+id+"&bzw=2";
     		 document.form1.submit();

    }

function getJl(){
		var zl=document.form1.zlzfh.value;		
		var jl=document.form1.jlzfh.value;
		
		var zlfh=Number(zl*54*24/1000/0.85).toFixed(2);
		var jlfh=Number(jl*220*24/1000).toFixed(2);

		if(jl!='0'&&zl=='0'){
			document.form1.qsdb.value=jlfh;
		}else if(jl=='0'&&zl!='0'){
			document.form1.qsdb.value=zlfh;
		}else if(jl=='0'&&zl=='0'){
			document.form1.qsdb.value='0';
		}else{
			if(Number(zlfh)>Number(jlfh)){
				document.form1.qsdb.value=jlfh;
			}else{
				document.form1.qsdb.value=zlfh;
			}	
		}
	}
	function getZl(){
		var zl=document.form1.zlzfh.value;
		var jl=document.form1.jlzfh.value;
		
		var zlfh=Number(zl*54*24/1000/0.85).toFixed(2);
		var jlfh=Number(jl*220*24/1000).toFixed(2);

		if(jl=='0'&&zl!='0'){
			document.form1.qsdb.value=zlfh;
		}else if(jl!='0'&&zl=='0'){
			document.form1.qsdb.value=jlfh;
		}else if(jl=='0'&&zl=='0'){
			document.form1.qsdb.value='0';
		}else{	
				if(Number(zlfh)>Number(jlfh)){
					document.form1.qsdb.value=jlfh;
				}else{
					document.form1.qsdb.value=zlfh;
				}	
		}
	}

</script>
