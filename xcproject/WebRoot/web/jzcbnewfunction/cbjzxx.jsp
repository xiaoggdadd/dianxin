<%@ page contentType="text/html; charset=UTF-8"%>
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
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
	String accountid = account.getAccountName();
	String loginId = account.getAccountId();
	String id=request.getParameter("wjid")!=null?request.getParameter("wjid"):"";
   String jydgxsb="",jdxgxsb="",jg2xq="",jg3sq="",g2xq="",g3sq="",dbds="",kgzl="",ydzl="",dxzl="",gyzl="",lyzl="";
	cbzddao dao=new cbzddao();
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
	String sjname="";//
	String qxname="";
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
	String zlzfh="";//
	String jlzfh="";
	String qsdb="";
	String bdhdl="";
	
	
	
	
	
	
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
	int ydgx=0;
	int dxgx=0;
	DecimalFormat mat=new DecimalFormat("0.00");
	bean=dao.getShiXx(id,loginId);
	//  dbds="",kgzl="",ydzl="",dxzl="",gyzl="",lyzl="";
	 //formbean.setDbds(rs.getString(44)!=null?rs.getString(44):"0");
	 
	 dbds=bean.getDbds();
	 if(null==dbds||"".equals(dbds)||"null".equals(dbds)){dbds="0";}
	 ydzl=bean.getYDGXSBZLFH();
	 if(null==ydzl||"".equals(ydzl)||"null".equals(ydzl)){ydzl="0";}
	 kgzl=bean.getKGDYZLFH();
	 if(null==kgzl||"".equals(kgzl)||"null".equals(kgzl)){kgzl="0";}
	 dxzl=bean.getDXGXSBZLFH();
	 if(null==dxzl||"".equals(dxzl)||"null".equals(dxzl)){dxzl="0";}
	 gyzl=bean.getGYGXSBZLFH();
	 if(null==gyzl||"".equals(gyzl)||"null".equals(gyzl)){gyzl="0";}
	 lyzl=bean.getZYYGSBZLFH();
	 if(null==lyzl||"".equals(lyzl)||"null".equals(lyzl)){lyzl="0";}
					//	    formbean.setKGDYZLFH(rs.getString(45)!=null?rs.getString(45):"0");
						 //   formbean.setYDGXSBZLFH(rs.getString(46)!=null?rs.getString(46):"0");
						 //   formbean.setDXGXSBZLFH(rs.getString(47)!=null?rs.getString(47):"0");
						//    formbean.setGYGXSBZLFH(rs.getString(48)!=null?rs.getString(48):"0");
						 ///   formbean.setZYYGSBZLFH(rs.getString(49)!=null?rs.getString(49):"0");
	zdid=bean.getZdid();
	if(null==zdid){zdid="";}
	zdname=bean.getZdname();
	if(null==zdname){zdname="";}
	shi=bean.getShi();
	if(null==shi){shi="";}
	xian=bean.getXian();
	if(null==xian){xian="";}
	xiaoqu=bean.getXiaoqu();
	if(null==xiaoqu){xiaoqu="";}
	accountmonth=bean.getCbsj();
	if(null==accountmonth){accountmonth="";}
	entryperson=bean.getSjlrr();
	if(null==entryperson){entryperson="";}
	entrytime=bean.getSjlrsj();
	if(null==entrytime){entrytime="";}
	cbbili=bean.getCbbl();
	if(null==cbbili||"".equals(cbbili)){
	cbbili="0";
	}
	cbbili=mat.format(Double.parseDouble(cbbili)*100)+"%";
	zgaskinstruction=bean.getZgyq();
	if(null==zgaskinstruction){zgaskinstruction="";}
	csms=bean.getCsms();
	if(null==csms){csms="";}
	yyfx=bean.getYyfx();
	if(null==yyfx){yyfx="";}
	csr=bean.getCszrr();
	if(null==csr){csr="";}
	g2last=bean.getG2();
	if("1".equals(g2last)){
	g2last="是";
	}else{
	g2last="否";
	}
	g3last=bean.getG3();
	if("1".equals(g3last)){
	g3last="是";
	}else{
	g3last="否";
	}
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
	sjname=bean.getSjname();
	qxname=bean.getQxname();
	if(sjname==null||sjname.equals("")||sjname.equals("null")||sjname.equals(" ")){sjname="";}
	if(qxname==null||qxname.equals("")||qxname.equals("null")||qxname.equals(" ")){qxname="";}
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
	// if(null==zgaskinstruction){zgaskinstruction="";}
	zs=bean.getXzs();
	// if(null==zs){zs="";}
	changjia=bean.getXchangjia();
	if(null==changjia){changjia="";}
	jztype=bean.getXjztype();
	if(null==jztype){jztype="";}
	shebei=bean.getXshebei();
	if(null==shebei){shebei="";}
	bbu=bean.getXbbu();
	
	rru=bean.getXrru();
	
	ydshebei=bean.getXydshebei();

	gxgwsl=bean.getXgxgwsl();
    
    String s=bean.getYdgxsb();
    if("".equals(s)||null==s){s="0";}
    ydgx=Integer.parseInt(s);
    String ss=bean.getDxgxsb();
     if("".equals(ss)||null==ss){ss="0";}
    dxgx=Integer.parseInt(ss);
    // jydgxsb="",jdxgxsb="",jg2xq="",jg3sq="",g2xq="",g3sq="";
    jydgxsb=bean.getJydgxsb();
       if("".equals(jydgxsb)||null==jydgxsb||jydgxsb.equals("null")){jydgxsb="0";}
    jdxgxsb=bean.getJdxgxsb();
       if("".equals(jdxgxsb)||null==jdxgxsb||jdxgxsb.equals("null")){jdxgxsb="0";}
    jg2xq=bean.getJg2xq();
       if("".equals(jg2xq)||null==jg2xq||jg2xq.equals("null")){jg2xq="0";}
    jg3sq=bean.getJg3sq();
       if("".equals(jg3sq)||null==jg3sq||jg3sq.equals("null")){jg3sq="0";}
    g2xq=bean.getG2xq();
       if("".equals(g2xq)||null==g2xq||g2xq.equals("null")){g2xq="0";}
    g3sq=bean.getG3sq();
       if("".equals(g3sq)||null==g3sq||g3sq.equals("null")){g3sq="0";}
  System.out.print("33333"+jdxgxsb+"");  
	szdq=shi+xian+xiaoqu;
	if(null==szdq){szdq="";}
	zlzfh=bean.getZlzfh();
	jlzfh=bean.getJlzfh();
	qsdb=bean.getQsdb();
	bdhdl=bean.getBdhdl();
	
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
		<title>整改信息</title>

		<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}
.style2 {
	color: red;
	font-weight: bold;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	width: 130px;
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
	height: 19px;
	width: 800px;
}

.formm {
	text-align: right;
	height: 19px;
	width: 80px;
}

.formr {
	text-align: right;
	height: 19px;
	width: 130px;
}
.formr5{
	text-align: right;
	height: 19px;
	width: 130px;
	color: red;
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
.form5 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	color:red;
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
.form25{
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: right;
	color:red;
	height: 19px;
	width: 130px;
}

.form3 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: center;
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
<script >

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
/*
var path = '<%=path%>';
function shangchuan() {
	$("#errorInfo").text("");
	var month = $("#month").val();

	document.form1.action = path + "/servlet/FilesUploadServlet?month=" + month;
	document.form1.submit()
}
*/
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
		<form action="" name="form1" method="POST" id="form" enctype=multipart/form-data>
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
										报账(对标)月份：
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
								<%if(!"".equals(sjname)){ %>
								<td colspan='7'>
									<div align="center" ><a href="#" onclick="modifyjz('<%=id%>')"><font  style="font-size: 12px;">下载</font></a></div>
								</td>
								<%}else{ %>
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
											<input type="text" name="g2last" value="<%=g2last%>" class="form1" readonly="readonly">
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													3G设备：
												</div>
											</td>
											<td>
											<input type="text" name="g3last" value="<%=g3last%>" class="form1" readonly="readonly">
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载频：
												</div>
											</td>
											<td>
												<input type="text" name="zplast" readonly="true" value="<%=zplast%>" class="form2" />
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
											<input type="text" name="changjialast" readonly="true" value="<%=changjialast%>" class="form1" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													基站类型：
												</div>
											</td>
											<td>
											<input type="text" name="jztypelast" readonly="true" value="<%=jztypelast%>" class="form1" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													设备类型：
												</div>
											</td>
											<td>
										<input type="text" name="shebeilast" readonly="true" value="<%=shebeilast%>" class="form1" />
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
											
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													固移共享设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="gxgwsllast" readonly="readonly" value="<%=gxgwsllast%>" class="form2" />
											</td>
												<td bgcolor="#DDDDDD" class="form_label">
												<div>
													移动共享设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="gxgwsllast" readonly="readonly" value="<%=jydgxsb%>" class="form2" />
											</td>
												<td bgcolor="#DDDDDD" class="form_label">
												<div>
													电信共享设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="gxgwsllast" readonly="readonly" value="<%=jdxgxsb%>" class="form2" />
											</td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													2G小区：
												</div>
											</td>
											<td>
												<input type="text" name="gxgwsllast" readonly="readonly" value="<%=jg2xq%>" class="form2" />
											</td>
												<td bgcolor="#DDDDDD" class="form_label">
												<div>
													3G扇区：
												</div>
											</td>
											<td>
												<input type="text" name="gxgwsllast" readonly="readonly" value="<%=jg3sq%>" class="form2" />
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
											<% if(g2.equals(g2last)){%>
											<td>
											<input type="text" name="g2now" readonly="readonly" value="<%=g2%>" class="form1" />
											</td>
											<%}else{%>
											<td>
											<input type="text" name="g2now" readonly="readonly" value="<%=g2%>" class="form5" />
											<span class="style2">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													3G设备：
												</div>
											</td>
											<%if(g3.equals(g3last)){%>
											<td>
											<input type="text" name="g3now" readonly="readonly" value="<%=g3%>" class="form1" />
											</td>
											<%}else{%>
											<td>
											<input type="text" name="g3now" readonly="readonly" value="<%=g3%>" class="form5" />
											<span class="style2">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载频：
												</div>
											</td>
											<%if(zp==Integer.parseInt(zplast)){%>
											<td>
												<input type="text" name="zpnow" value="<%=zp%>" class="form2" readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
												<input type="text" name="zpnow" value="<%=zp%>" class="form25" readonly="readonly"/>
												<span class="style2">*</span>
											</td>
											<% }%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载扇：
												</div>
											</td>
											<%if(zs==Integer.parseInt(zslast)){%>
											<td>
												<input type="text" name="zsnow" value="<%=zs %>" class="form2" readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
												<input type="text" name="zsnow" value="<%=zs %>" class="form25" readonly="readonly"/>
												<span class="style2">*</span>
											</td>
											<% }%>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													厂家：
												</div>
											</td>
											<%if(changjia.equals(changjialast)){%>
											<td>
											<input type="text" name="zsnow" value="<%=changjia%>" style="width:130;border-left-width: 0;border-right-width: 0;border-top-width: 0" readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
											<input type="text" name="zsnow" value="<%=changjia%>" style="width:130;color: red;border-left-width: 0;border-right-width: 0;border-top-width: 0" readonly="readonly"/>
											<span class="style2">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													基站类型：
												</div>
											</td>
											<%if(jztype.equals(jztypelast)){%>
											<td>
											<input type="text" name="jztypenow" value="<%=jztype%>" style="width:130;border-left-width: 0;border-right-width: 0;border-top-width: 0" readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
											<input type="text" name="jztypenow" value="<%=jztype%>" style="width:130;color:red;border-right-width: 0;border-top-width: 0" readonly="readonly"/>
											<span class="style2">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													设备类型：
												</div>
											</td>
											
											<%if(shebei.equals(shebeilast)){%>
												<td>
											<input type="text" name="shebeinow" value="<%=shebei%>" style="width:130;border-left-width: 0;border-right-width: 0;border-top-width: 0" readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
											<input type="text" name="shebeinow" value="<%=shebei%>" style="width:130;color: red;border-left-width: 0;border-right-width: 0;border-top-width: 0" readonly="readonly"/>
											<span class="style2">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													拉远BBU：
												</div>
											</td>
											<%if(bbu==Integer.parseInt(bbulast)){%>
											<td>
												<input type="text" name="bbunow" value="<%=bbu%>" class="form2" readonly="readonly"/>
											</td>
											<% }else{%>
											<td>
												<input type="text" name="bbunow" value="<%=bbu%>" class="form25" readonly="readonly"/>
											<span class="style2">*</span>
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
												<input type="text" name="rrunow" value="<%=rru%>" class="form2" readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
												<input type="text" name="rrunow" value="<%=rru%>" class="form25" readonly="readonly"/>
												<span class="style2">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													固移共享设备数量：
												</div>
											</td>
											<%if(gxgwsl==Integer.parseInt(gxgwsllast)){%>
											<td>
												<input type="text" name="ydshebeinow" value="<%=gxgwsl%>" class="form2" readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
												<input type="text" name="ydshebeinow" value="<%=gxgwsl%>" class="form25" readonly="readonly"/>
												<span class="style2">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													移动共享设备数量：
												</div>
											</td>
											<%if(ydgx==Integer.parseInt(jydgxsb)){%>
											<td>
												<input type="text" name="gxgwslnow" value="<%=ydgx%>" class="form2" readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
												<input type="text" name="gxgwslnow" value="<%=ydgx%>" class="form25" readonly="readonly"/>
												<span class="style2">*</span>
											</td>
											<%}%>
												<td bgcolor="#DDDDDD" class="form_label">
												<div>
													电信共享设备数量：
												</div>
											</td>
											<%if(dxgx==Integer.parseInt(jdxgxsb)){%>
											<td>
												<input type="text" name="gxgwslnow" value="<%=dxgx%>" class="form2" readonly="readonly"/>
											</td>
											<%}else{%>
												<td>
												<input type="text" name="gxgwslnow" value="<%=dxgx%>" class="form25" readonly="readonly"/>
											<span class="style2">*</span>
											</td>
											<%}%>
											<td></td>
											<td></td>
										</tr>
										<tr>
										<td bgcolor="#DDDDDD" class="form_label">
												<div>
													2G小区：
												</div>
											</td>
											<%if(jg2xq.equals(g2xq)){%>
											<td>
												<input type="text" name="gxgwslnow" value="<%=g2xq%>" class="form2" readonly="readonly"/>
											</td>
											<%}else{%>
											<td>
												<input type="text" name="gxgwslnow" value="<%=g2xq%>" class="form25" readonly="readonly"/>
											<span class="style2">*</span>
											</td>
											<%}%>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													3G扇区：
												</div>
											</td>
											<%if(g3sq.equals(jg3sq)){%>
											<td>
												<input type="text" name="gxgwslnow" value="<%=g3sq%>" class="form2" readonly="readonly"/>
											</td>
											<%}else{%>
												<td>
												<input type="text" name="gxgwslnow" value="<%=g3sq%>" class="form25" readonly="readonly"/>
												<span class="style2">*</span>
											</td>
											<%}%>
										
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
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>结算电表读数：</div>
								</td>
								<td>
									<input type="text" name="jsdbds" value="<%=dbds %>" class="formr" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>开关电源直流总负荷(A)</div>
								</td>
								<td>
									<input type="text" name="kgzfh" value="<%=kgzl %>" class="formr" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>移动共享设备直流负荷(A)</div>
								</td>
								<td>    
									<input type="text" name="ydzlfh" value="<%=ydzl %>" class="formr" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>电信共享设备直流负荷(A)</div>
								</td>
								<td>
									<input type="text" name="dxzlfh" value="<%=dxzl %>" class="formr" readonly="readonly"/>
								</td>
							</tr>
							<tr>  
								<td bgcolor="#DDDDDD" class="form_label">
									<div>固移共享设备直流负荷(A)</div>
								</td>
								<td>
									<input type="text" name="gyzlfh" value="<%=gyzl %>" class="formr" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>直流远供负荷(A)</div>
								</td>
								<td>
									<input type="text" name="zlygfh" value="<%=lyzl %>" class="formr" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>交流总负荷(A)(不含空调)</div>
								</td>
								<td>
									<input type="text" type="hidden" name="jlzfh" value="<%=jlzfh %>" onblur="getJl()" class="formr"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>直流总负荷(A)(不含空调)</div>
								</td>
								<td>
									<input type="text" type="hidden" name="zlzfh" value="<%=zlzfh %>" onblur="getZl()" class="formr"/>
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>额定耗电量(本地标)</div>
								</td>
								<td>
									<input type="text" type="hidden" name="bdhdl" value="<%=bdhdl %>" class="formr"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>省定标(不含空调)</div>
								</td>
								<td>
									<input type="text" type="hidden" name="qsdb" value="<%=qsdb %>" class="formr" readonly="readonly"/>
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
									<input type="text" name="reasonanalyse" value="<%=yyfx%>" class="formjc" readonly="readonly"/>
								</td>
							</tr>
						
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										测试人：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="testperson" value="<%=csr%>" class="form" readonly="readonly"/>
								</td>
							</tr>
							<tr height="19px">
									<td bgcolor="#DDDDDD" class="form_label">
									<div>
										附件说明：
									</div>
								</td>
								<%if(!"".equals(qxname)){ %>
								<td colspan='7'>
									<div align="center" ><a href="#" onclick="modifyjz2('<%=id%>')"><font  style="font-size: 12px;">下载</font></a></div>
								</td>
								<%}else{ %>
								<td colspan='7'>
									<div align="center" ><font  style="font-size: 12px;">无</font></div>
								</td>
								<%} %>			
							</tr>
						<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									整改
								</td>
							</tr>
							<tr>
<td bgcolor="#DDDDDD" class="form_label">
									<div>
										整改要求说明：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="zgyq" value="" class="formjc" />
								</td>
</tr>
<tr>
<td bgcolor="#DDDDDD" class="form_label"><div>完成时间：</div></td>
							<td><input type="text" class="form" id="entrytimeQS" name="entrytimeQS" value="<%if(null!=request.getParameter("entrytimeQS")) out.print(request.getParameter("entrytimeQS")); %>" onFocus="getDateString(this,oCalendarChs)" /></td>

</tr>
							<tr>
							<td colspan="9">
         <div style="width: 370px; height: 25px; float:left;" >
			  请选择上传文件：
		      <input type="file" id="fileUp" name="fileUp" height="25px">
		 </div>
	      <div id="uploading2" style="position: relative; width: 60px; height: 20px; float:left; pointer;" >
				<img alt=""src="<%=request.getContextPath() %>/images/shangchuan.png" width="100%" height="100%" />
				<span style="font-size: 12px; position: absolute; left: 26px; top: 3px; color: white">派单</span>
		 </div>
		
		<div id="errorInfo" style="width:200px;height:50px;position:relative;font-size: 12px;color:red">
				<c:forEach items="${requestScope.statusInfo}" var="info">
					<br />${info}
				</c:forEach>
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
<script>

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

</script>
<script>
/*
// document.form1.jztypelast.value='<%=bean.getJztype()%>';//原始基站类型2
//if(document.form1.jztypelast.value==""||document.form1.jztypelast.value==null){
// document.form1.jztypelast.value="0";
// }
/*document.form1.jztypenow.value='<%=bean.getXjztype()%>';//新基站类型2
if(document.form1.jztypenow.value==""||document.form1.jztypenow.value==null){
 document.form1.jztypenow.value="0";
}
*/

//document.form1.g2last.value='<%=bean.getG2()%>';//原始2G设备
//if(document.form1.g2last.value==""||document.form1.g2last.value==null){
 //document.form1.g2last.value="0";
 //}
//document.form1.g3last.value='<%=bean.getG3()%>';//原始3G设备
//if(document.form1.g3last.value==""||document.form1.g3last.value==null){
 //document.form1.g3last.value="0";
 //}
//document.form1.g2now.value='<%=bean.getXg2()%>';//新2G设备
//if(document.form1.g2now.value==""||document.form1.g2now.value==null){
// document.form1.g2now.value="0";
 //}
document.form1.g3now.value='<%=bean.getXg3()%>';//新3G设备
if(document.form1.g3now.value==""||document.form1.g3now.value==null){
 document.form1.g3now.value="0";
 }
document.form1.changjialast.value='<%=bean.getChangjia()%>';//changjia
if(document.form1.changjialast.value==""||document.form1.changjialast.value==null){
 document.form1.changjialast.value="0";
}

document.form1.changjianow.value='<%=bean.getXchangjia()%>';//changjia
if(document.form1.changjianow.value==""||document.form1.changjianow.value==null){
 document.form1.changjianow.value="0";
}
document.form1.shebeilast.value='<%=bean.getShebei()%>';//设备类型
if(document.form1.shebeilast.value==""||document.form1.shebeilast.value==null){
 document.form1.shebeilast.value="0";
}
document.form1.shebeinow.value='<%=bean.getXshebei()%>';//设备类型
if(document.form1.shebeinow.value==""||document.form1.shebeinow.value==null){
 document.form1.shebeinow.value="0";
}

</script>
	<script language="javascript">
		function shangchuan(){
		var path='<%=path%>';
		var id='<%=id%>';
		var sjyq=document.form1.zgyq.value;// entrytimeQS
		var entrytimeQS=document.form1.entrytimeQS.value;
		
				$("#errorInfo").text("");
				document.form1.action=path+"/UploadB?id="+id+"&sjyq="+sjyq+"&action=ejsh"+"&bzw=3"+"&wcsj="+entrytimeQS;
				
				document.form1.submit()
			}
    $(function(){
		$("#cancelBtn").click(function(){
		 daochu(1);
		 
		});

		$("#cancela").click(function(){
			daochu(0);
		});

		$("#uploading").click(function(){
			shangchuan2();
		});
		$("#uploading2").click(function(){
			shangchuan();
		});
	});
		</script>