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
	String id=request.getParameter("id")!=null?request.getParameter("id"):"0";//第二张表id   cbzdxx
	
	String cid=request.getParameter("cid")!=null?request.getParameter("cid"):"0";
	System.out.println("22222222222222:"+id);
	String cjc = request.getParameter("changjianow")!=null?request.getParameter("changjianow"):"";

	cbzddao dao=new cbzddao();
	XianSignDao dao1 = new XianSignDao();
	cbzddao  dao2=new cbzddao();
	cbzddao  dao3=new cbzddao();
	Zdinfo bean=new Zdinfo();
	//获取站点信息
	String id1="";
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
	String sjpath = "";//省级下发附件名称
	String shyj="";//审核意见
	String zhdlwcsj="";//找回电量完成时间
	String csms="";//测试描述
	String yyfx="";//原因分析
	String csr="";//测试人
	String wcsm="";//区县完成说明
	String zhssdl="";//找回损失电量
	String sjname;//省级附件路径
	String shijshsj="";//
	String sjshsj="";
	
	String qxwcsj="";//区县完成时间
	String zgzrr="";//整改责任人
	String nhbz="";//当月省定标电量

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
	String gxgwsllast = "0";//共享固网设备数量（原始）
	String dxgxsblast="0";//电信共享设备数量(原始)
	String ydgxsblast="0";//移动共享设备数量(原始)
	String g2xqlast="0";//g2小区
	String g3sqlast="0";	//g3扇区
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
	int gxgwsl = 0;//共享固网设备数量（新）
	String dxgxsb="0";//电信共享设备数量（新）
	String ydgxsb="0";//移动共享设备数量（新）
	String g2xqnow="0";//g2小区（新）
	String g3sqnow="0";//g3扇区（新）
	
	String xfzgyq="";//整改要求
	String wcsj="";//完成时间
	String dbds="0";//结算电表读数
	String kgzl="0";//开关电源直流总负荷
	String ydzl="0";//移动共享设备直流负荷
	String dxzl="0";//电信共享设备直流负荷
	String gyzl="0";//固移共享设备直流负荷
	String ygzl="0";//直流远供负荷
	String zlzfh="";//
	String jlzfh="";
	String qsdb="";
	String yssdbdl="";//验收省定标电量
	String bdhdl="";
	
	bean=dao1.getShiXX1(id,loginId);
	
	id1=bean.getId();// id1第一张表的id
	
	int r = dao2.CheckQx(id1);
	int rr=dao3.CheckZg(id);
	
	id1=bean.getId();
	zdid=bean.getZdid();
	zdname=bean.getZdname();
	shi=bean.getShi();
	xian=bean.getXian();
	xiaoqu=bean.getXiaoqu();
	accountmonth=bean.getCbsj();
	entryperson=bean.getSjlrr();
	entrytime=bean.getSjlrsj();
	cbbili=bean.getCbbl();
	sjpath=bean.getSjpath();
	nhbz=bean.getNhbz();
	sjname=bean.getSjname();

	if(sjname==null||sjname.equals("")||sjname.equals("null")||sjname.equals(" ")){sjname="";}
	if(zdid==null||zdid.equals("")||zdid.equals("null")||zdid.equals(" ")){zdid="";}
	if(zdname==null||zdname.equals("")||zdname.equals("null")||zdname.equals(" ")){zdname="";}
	if(null==shi||shi.equals("")||shi.equals("null")||shi.equals(" ")){shi="";}
	if(null==xian||xian.equals("")||xian.equals("null")||xian.equals(" ")){xian="";}
	if(null==xiaoqu||xiaoqu.equals("")||xiaoqu.equals("null")||xiaoqu.equals(" ")){xiaoqu="";}
	if(accountmonth==null||accountmonth.equals("")||accountmonth.equals("null")||accountmonth.equals(" ")){accountmonth="";}
	if(entryperson==null||entryperson.equals("")||entryperson.equals("null")||entryperson.equals(" ")){entryperson="";}
	if(entrytime==null||entrytime.equals("")||entrytime.equals("null")||entrytime.equals(" ")){entrytime="";}
	if(cbbili==null||cbbili.equals("")||cbbili.equals("null")||cbbili.equals(" ")){cbbili="0";}
	DecimalFormat mat=new DecimalFormat("0.00");
	cbbili=mat.format(Double.parseDouble(cbbili)*100)+"%";
	if(szdq==null||szdq.equals("")||szdq.equals("null")||szdq.equals(" ")){szdq="";}
	if(sjpath==null||sjpath.equals("")||sjpath.equals("null")||sjpath.equals(" ")){sjpath="";}
	if(nhbz==null||nhbz.equals("")||nhbz.equals("null")||nhbz.equals(" ")){nhbz="";}
	szdq=shi+xian+xiaoqu;
	//原11大类
	g2xqlast=bean.getJg2xq();
	g3sqlast=bean.getJg3sq();
	g2last=bean.getG2();
	g3last=bean.getG3();
	zplast=bean.getZp();
	zslast=bean.getZs();
	changjialast=bean.getChangjia();
	jztypelast=bean.getJztype();
	shebeilast=bean.getShebei();
	bbulast=bean.getBbu();
	rrulast=bean.getRru();
	gxgwsllast=bean.getGxgwsl();
	dxgxsblast=bean.getJdxgxsb();
	ydgxsblast=bean.getJydgxsb();

	//原11大类判断空及部分转换int
	if(g2xqlast==null||g2xqlast.equals("")||g2xqlast.equals("null")||g2xqlast.equals(" ")){g2xqlast="0";}
	if(g3sqlast==null||g3sqlast.equals("")||g3sqlast.equals("null")||g3sqlast.equals(" ")){g3sqlast="0";}
	if(g2last==null||g2last.equals("")||g2last.equals("null")||g2last.equals(" ")){g2last="0";}
	if(g3last==null||g3last.equals("")||g3last.equals("null")||g3last.equals(" ")){g3last="0";}
	if(zplast==null||zplast.equals("")||zplast.equals("null")||zplast.equals(" ")){zplast="0";}
	int zpl=Integer.parseInt(zplast);
	if(zslast==null||zslast.equals("")||zslast.equals("null")||zslast.equals(" ")){zslast="0";}
	int zsl=Integer.parseInt(zslast);
	if(changjialast==null||changjialast.equals("")||changjialast.equals("null")||changjialast.equals(" ")){changjialast="";}
	if(jztypelast==null||jztypelast.equals("")||jztypelast.equals("null")||jztypelast.equals(" ")){jztypelast="";}
	if(shebeilast==null||shebeilast.equals("")||shebeilast.equals("null")||shebeilast.equals(" ")){shebeilast="";}
	if(bbulast==null||bbulast.equals("")||bbulast.equals("null")||bbulast.equals(" ")){bbulast="0";}
	int bbul=Integer.parseInt(bbulast);
	if(rrulast==null||rrulast.equals("")||rrulast.equals("null")||rrulast.equals(" ")){rrulast="0";}
	int rrul=Integer.parseInt(rrulast);
	if(gxgwsllast==null||gxgwsllast.equals("")||gxgwsllast.equals("null")||gxgwsllast.equals(" ")){gxgwsllast="0";}
	int gxgwsll=Integer.parseInt(gxgwsllast);
	if(dxgxsblast==null||dxgxsblast.equals("")||dxgxsblast.equals("null")||dxgxsblast.equals(" ")){dxgxsblast="0";}
	if(ydgxsblast==null||ydgxsblast.equals("")||ydgxsblast.equals("null")||ydgxsblast.equals(" ")){ydgxsblast="0";}
	
	//新11大类获取
	g2=bean.getXg2();
	g3=bean.getXg3();
	zp=bean.getXzp();
	zs=bean.getXzs();
	g2xqnow=bean.getG2xq();
	g3sqnow=bean.getG3sq();
	changjia=bean.getXchangjia();
	jztype=bean.getXjztype();
	shebei=bean.getXshebei();
	bbu=bean.getXbbu();
	rru=bean.getXrru();
	gxgwsl=bean.getXgxgwsl();
	dxgxsb=bean.getDxgxsb();
	ydgxsb=bean.getYdgxsb();
	zlzfh=bean.getZlzfh();
	jlzfh=bean.getJlzfh();
	qsdb=bean.getQsdb();
	yssdbdl=bean.getYssdbdl();
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
	if(yssdbdl==null||yssdbdl.equals("")||yssdbdl.equals("null")||yssdbdl.equals(" ")||qsdb==""){yssdbdl="0";}
	if(bdhdl==null||bdhdl.equals("")||bdhdl.equals("null")||bdhdl.equals(" ")||bdhdl==""){
		bdhdl="0";
	}
	//新11大类判断空
	if(g2==null||g2.equals("")||g2.equals("null")||g2.equals(" ")){g2="0";}
	if(g3==null||g3.equals("")||g3.equals("null")||g3.equals(" ")){g3="0";}
	if(changjia==null||changjia.equals("")||changjia.equals("null")||changjia.equals(" ")){changjia="";}
	if(jztype==null||jztype.equals("")||jztype.equals("null")||jztype.equals(" ")){jztype="";}
	if(shebei==null||shebei.equals("")||shebei.equals("null")||shebei.equals(" ")){shebei="";}
	if(dxgxsb==null||dxgxsb.equals("")||dxgxsb.equals("null")||dxgxsb.equals(" ")){dxgxsb="0";}
	if(ydgxsb==null||ydgxsb.equals("")||ydgxsb.equals("null")||ydgxsb.equals(" ")){ydgxsb="0";}
	if(g2xqnow==null||g2xqnow.equals("")||g2xqnow.equals("null")||g2xqnow.equals(" ")){g2xqnow="";}
	if(g3sqnow==null||g3sqnow.equals("")||g3sqnow.equals("null")||g3sqnow.equals(" ")){g3sqnow="";}
	
	wcsj=bean.getWcsj();
	xfzgyq=bean.getXfzgyq();
	zgaskinstruction=bean.getZgyq();
	csms=bean.getCsms();
	zhdlwcsj=bean.getZhdlwcsj();
	shyj=bean.getShyj();
	yyfx=bean.getYyfx();
	csr=bean.getCszrr();
	dbds=bean.getDbds();
	kgzl=bean.getKGDYZLFH();
	ydzl=bean.getYDGXSBZLFH();
	dxzl=bean.getDXGXSBZLFH();
	ygzl=bean.getZYYGSBZLFH();
	wcsm=bean.getWcsm();
	zhssdl=bean.getZhssdl();
	sjshsj=bean.getSjshsj();
	shijshsj=bean.getShijshsj();
	qxwcsj=bean.getQxwcsj();
	zgzrr=bean.getZgzrr();
	if(zgaskinstruction==null||zgaskinstruction.equals("")||zgaskinstruction.equals("null")||zgaskinstruction.equals(" ")){zgaskinstruction="";}
	if(wcsj==null||wcsj.equals("")||wcsj.equals("null")||wcsj.equals(" ")){wcsj="";}
	if(xfzgyq==null||xfzgyq.equals("")||xfzgyq.equals("null")||xfzgyq.equals(" ")){xfzgyq="";}
	if(csms==null||csms.equals("")||csms.equals("null")||csms.equals(" ")){csms="";}
	if(shyj==null||shyj.equals("")||shyj.equals("null")||shyj.equals(" ")){shyj="";}
	if(yyfx==null||yyfx.equals("")||yyfx.equals("null")||yyfx.equals(" ")){yyfx="";}
	if(csr==null||csr.equals("")||csr.equals("null")||csr.equals(" ")){csr="";}
	if(dbds==null||dbds.equals("")||dbds.equals("null")||dbds.equals(" ")){dbds="0";}
	if(kgzl==null||kgzl.equals("")||kgzl.equals("null")||kgzl.equals(" ")){kgzl="0";}
	if(ydzl==null||ydzl.equals("")||ydzl.equals("null")||ydzl.equals(" ")){ydzl="0";}
	if(dxzl==null||dxzl.equals("")||dxzl.equals("null")||dxzl.equals(" ")){dxzl="0";}
	if(ygzl==null||ygzl.equals("")||ygzl.equals("null")||ygzl.equals(" ")){ygzl="0";}
	
	if(wcsm==null||wcsm.equals("")||wcsm.equals("null")||wcsm.equals(" ")){wcsm="";}
	if(zhssdl==null||zhssdl.equals("")||zhssdl.equals("null")||zhssdl.equals(" ")){zhssdl="";}
	
	if(zhdlwcsj==null||zhdlwcsj.equals("")||zhdlwcsj.equals("null")||zhdlwcsj.equals(" ")){
		zhdlwcsj="";
		}else{
			zhdlwcsj=zhdlwcsj.substring(0,11);
		}
	
	if(qxwcsj==null||qxwcsj.equals("")||qxwcsj.equals("null")||qxwcsj.equals(" ")){
	qxwcsj="";
	}else{
		qxwcsj=qxwcsj.substring(0,11);
	}
	
	if(shijshsj==null||shijshsj.equals("")||shijshsj.equals("null")||shijshsj.equals(" ")){
		shijshsj="";
		}else{
			shijshsj=shijshsj.substring(0,11);
		}
	
	if(sjshsj==null||sjshsj.equals("")||sjshsj.equals("null")||sjshsj.equals(" ")){
		sjshsj="";
		}else{
			sjshsj=sjshsj.substring(0,11);
		}
	
	if(zgzrr==null||zgzrr.equals("")||zgzrr.equals("null")||zgzrr.equals(" ")){zgzrr="";}
	
	if("1".equals(g2last)){
		g2last="是";
	}else{
		g2last="否";
	}
	if("1".equals(g2)){
		g2="是";
	}else{
		g2="否";
	}
	if("1".equals(g3)){
		g3="是";
	}else{
		g3="否";
	}
	if("1".equals(g3last)){
		g3last="是";
	}else{
		g3last="否";
	}
		
	System.out.println("---"+g2xqnow+"ds"+g2xqlast);
%>
<html>
	<head>
		<title>完成整改提交审核</title>

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
	height: 19px;
	width: 800px;
}

.formm {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: left;
	height: 19px;
	width: 130px;
}

.formr {
	text-align: right;
	height: 19px;
	width: 130px;
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
		<script language="javascript">
var path = '<%=path%>';

function saveDegree(){
	
	var jlzfh = document.form1.jlzfh.value;
	var zlzfh = document.form1.zlzfh.value;
	var qsdb = document.form1.qsdb.value;
	var zhssdl = document.form1.zhssdl.value;
	var zhdlwcsj = document.form1.zhdlwcsj.value;
	var yssdbdl = document.form1.yssdbdl.value;
	var cid = document.form1.cid.value;
	var zdid = document.form1.zdid.value;
	
	 window.alert("执行到!");
	document.form1.action=path+"/servlet/ShengjzgSh?action=cssave";					        
	document.form1.submit();
}

</script>
<script>
		var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
		oCalendarEnny.Init();
		
		var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
		oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
		oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
				"八月", "九月", "十月", "十一月", "十二月");
		oCalendarChsny.oBtnTodayTitle = "确定";
		oCalendarChsny.oBtnCancelTitle = "取消";
		oCalendarChsny.Init();
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

var path = '<%=path%>';
$(function() {
	$("#saveBtn").click(function() {
		saveDegree();
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
		<form action="" name="form1"  method="POST" >
		
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
									<input type="text" name="zdid" readonly="readonly"
										value="<%=zdid%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										站点名称：
									</div>
								</td>
								<td>
									<input type="text" name="zdname" readonly="readonly"
										value="<%=zdname%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										所属区域：
									</div>
								</td>
								<td>
									<input type="text" name="szdq" readonly="readonly"
										value="<%=szdq%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										报账月份：
									</div>
								</td>
								<td>
									<input type="text" name="accountmonth" readonly="readonly" value="<%=accountmonth%>" class="form2" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										省定标电量：
									</div>
								</td>
								<td>
									<input type="text" name="nhbz" readonly="readonly" value="<%=nhbz%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										超标比例：
									</div>
								</td>
								<td>
									<input type="text" name="cbbili" readonly="readonly" value="<%=cbbili%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										录入人：
									</div>
								</td>
								<td>
									<input type="text" name="entryperson" readonly="readonly" value="<%=entryperson%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										录入时间：
									</div>
								</td>
								<td>
									<input type="text" name="entrytime" readonly="readonly" value="<%=entrytime%>" class="form2" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										要求说明：
									</div>
								</td>
								<td colspan='7' class="form2">
									<input type="text" name="zgaskinstruction" readonly="readonly" value="<%=zgaskinstruction%>" class="formcc" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										附件说明：
									</div>
								</td>
								<%if(!"".equals(sjname)){ %>
								<td colspan='5'>
									<div align="center" ><a href="#" onclick="modifyjz('<%=id1%>')"><font  style="font-size: 12px;">下载</font></a></div>
								</td>
								<%}else{ %>
								<td colspan='5'>
									<div align="center" ><font  style="font-size: 12px;">无</font></div>
								</td>
								<%} %>
							</tr>
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									测试后11大类
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										2G设备：
									</div>
								</td>
								<%if(g2.equals(g2last)){ %>
								<td>
									<input type="text" name="g2now" value="<%=g2 %>" class="form2" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="g2now" value="<%=g2 %>" style="color:red" class="form2" readonly="readonly"/><span class="style1">*</span>
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										3G设备：
									</div>
								</td>
								<%if(g3.equals(g3last)){ %>
								<td>
									<input type="text" name="g3now" value="<%=g3 %>" class="form2" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="g3now" value="<%=g3 %>" style="color:red" class="form2" readonly="readonly"/><span class="style1">*</span>
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										载频数量：
									</div>
								</td>
								<%if(zp!=zpl){ %>
								<td>
									<input type="text" name="zpnow" style="color:red" value="<%=zp %>" class="form2" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="zpnow" value="<%=zp %>" class="form2" readonly="readonly"/>
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										载扇数量：
									</div>
								</td>
								<%if(zs!=zsl){ %>
								<td>
									<input type="text" name="zsnow" style="color:red" value="<%=zs %>" class="form2" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="zsnow" value="<%=zs %>" class="form2" readonly="readonly"/>
								</td>
								<%} %>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										厂家：
									</div>
								</td>
								<% if(changjia.equals(changjialast)){%>
								<td>
									<input type="text" name="changjia" readonly="readonly" value="<%=changjia%>" class="form2" />
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="changjia" readonly="readonly" style="color:red" value="<%=changjia%>" class="form2" /><span class="style1">*</span>
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										基站类型：
									</div>
								</td>
								<%if(jztype.equals(jztypelast)){ %>
								<td>
									<input type="text" name="jztype" readonly="readonly" value="<%=jztype%>" class="form2" /><span class="style1">*</span>	
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="jztype" readonly="readonly" style="color:red" value="<%=jztype%>" class="form2" /><span class="style1">*</span>	
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										设备编码：
									</div>
								</td>
								<%if(shebei.equals(shebeilast)){ %>
								<td>
									<input type="text" name="shebei" readonly="readonly" value="<%=shebei%>" class="form2" />
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="shebei" readonly="readonly" style="color:red" value="<%=shebei%>" class="form2" /><span class="style1">*</span>
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										BBU数量：
									</div>
								</td>
								<%if(bbu!=bbul){ %>
								<td>
									<input type="text" name="bbunow" style="color:red" value="<%=bbu%>" class="form2" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="bbunow" value="<%=bbu%>" class="form2" readonly="readonly"/>
								</td>
								<%} %>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										RRU数量：
									</div>
								</td>
								<%if(rru!=rrul){ %>
								<td>
									<input type="text" name="rrunow" style="color:red"  value="<%=rru%>" class="form2" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="rrunow" value="<%=rru%>" class="form2" readonly="readonly"/>
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										移动设备数量：
									</div>
								</td>
								<%if(ydgxsb.equals(ydgxsblast)){%>
								<td>
									<input type="text" name="gxgwslnow" value="<%=ydgxsb%>" class="form2" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="gxgwslnow" style="color:red" value="<%=ydgxsb%>" class="form2" readonly="readonly"/>
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										共享固网设备数量：
									</div>
								</td>
								<%if(gxgwsl!=gxgwsll){ %>
								<td>
									<input type="text" name="ydshebeinow" style="color:red" value="<%=gxgwsl%>" class="form2" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="ydshebeinow" value="<%=gxgwsl%>" class="form2" readonly="readonly"/>
								</td>
								<%} %>
							</tr>
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
									<input type="text" name="jsdbds" value="<%=dbds %>" class="form1" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>开关电源直流总负荷(A)</div>
								</td>
								<td>
									<input type="text" name="kgzfh" value="<%=kgzl %>" class="form1" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>移动共享设备直流负荷(A)</div>
								</td>
								<td>    
									<input type="text" name="ydzlfh" value="<%=ydzl %>" class="form1" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>电信共享设备直流负荷(A)</div>
								</td>
								<td>
									<input type="text" name="dxzlfh" value="<%=dxzl %>" class="form1" readonly="readonly"/>
								</td>
							</tr>
							<tr>  
								<td bgcolor="#DDDDDD" class="form_label">
									<div>固移共享设备直流负荷(A)</div>
								</td>
								<td>
									<input type="text" name="gyzlfh" value="<%=gyzl %>" class="form1" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>直流远供负荷(A)</div>
								</td>
								<td>
									<input type="text" name="zlygfh" value="<%=ygzl %>" class="form1" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>交流总负荷(A)(不含空调)</div>
								</td>
								<td>
									<input type="text" name="jlzfh" value="<%=jlzfh %>" onblur="getJl()" class="form1" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>直流总负荷(A)(不含空调)</div>
								</td>
								<td>
									<input type="text" name="zlzfh" value="<%=zlzfh %>" onblur="getZl()" class="form1" readonly="readonly"/>
								</td>
							</tr>
							<tr>
							<td bgcolor="#DDDDDD" class="form_label">
									<div>额定耗电量(本地标)</div>
								</td>
								<td>
									<input type="text" name="bdhdl" value="<%=bdhdl %>" class="form1" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>省定标(不含空调)</div>
								</td>
								<td>
									<input type="text" name="qsdb" value="<%=qsdb %>" class="form1" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										找回损失电量：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="zhssdl" value="<%=zhssdl %>" class="form1" readonly="readonly"/>
								</td>
							</tr>
							
							<tr>
							<td bgcolor="#DDDDDD" class="form_label">
									<div>找回电量完成时间</div>
								</td>
								<td>
									<input type="text" name="zhdlwcsj" value="<%if (null != request.getParameter("zhdlwcsj")) out.print(request.getParameter("zhdlwcsj"));%>" onFocus="getDatenyString(this,oCalendarChsny)"
										class="form1" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label" style="display:none;">
									<div style="display:none;">验收省定标电量(不含空调)</div>
								</td>
								<td>
									<input type="hidden" name="yssdbdl" value="<%=yssdbdl %>" class="form1" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										审核意见：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="shyj" value="<%=shyj%>" class="form2" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										测试描述：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="csms" value="<%=csms%>" class="form2" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										原因分析：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="yyfx" value="<%=yyfx%>" class="form2" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										测试人：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="csr" value="<%=csr%>" class="form2" readonly="readonly"/>
								</td>
							</tr>
							<tr height="19px">
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										附件：
									</div>
								</td>
								<%if(r==1){ %>
								<td colspan='5'>
									<div align="center" ><a href="#" onclick="modifyjz1('<%=id1%>')"><font  style="font-size: 12px;">下载</font></a></div>
								</td>
								<%}else{ %>
								<td colspan='5'>
									<div align="center" ><font  style="font-size: 12px;">无</font></div>
								</td>
								<%} %>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										市审核时间：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="shijshsj" value="<%=shijshsj %>" class="form2" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									省级下发要求
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										整改要求：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="xfzgyq" value="<%=xfzgyq %>" class="form2" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										完成时间：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="wcsj" value="<%=wcsj %>" class="form2" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										整改附件：
									</div>
								</td>
								<%if(!"".equals(sjpath)){ %>
								<td colspan='5'>
									<div align="center" ><a href="#" onclick="modifyjz2('<%=id%>')"><font  style="font-size: 12px;">下载</font></a></div>
								</td>
								<%}else{ %>
								<td colspan='5'>
									<div align="center" ><font  style="font-size: 12px;">无</font></div>
								</td>
								<%} %>
							</tr>
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									整改完成情况
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										完成说明：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="wcsm" value="<%=wcsm %>" class="form2" readonly="readonly"/>
								</td>
							</tr>
							
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										完成时间：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="qxwcsj" value="<%=qxwcsj %>" class="form2" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										整改负责人：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="zgfzr" value="<%=zgzrr %>" class="form2" readonly="readonly"/>
								</td>
							</tr>
							<tr height="19px">
								<td bgcolor="#DDDDDD" class="form_label"><div>
									附件：
								</div></td>
								<%if(rr==1){ %>
								<td colspan='5'>
									<div align="center" ><a href="#" onclick="modifyjz3('<%=id%>')"><font  style="font-size: 12px;">下载</font></a></div>
								</td>
								<%}else{ %>
								<td colspan='5'>
									<div align="center" ><font  style="font-size: 12px;">无</font></div>
								</td>
								<%} %>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										市审核时间：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="sjshsj" value="<%=sjshsj %>" class="form2" readonly="readonly"/>
								</td>
								<td>
									<div id="errorInfo" style="width:200px;height:50px;position:relative;font-size: 12px;color:red">
										<c:forEach items="${requestScope.statusInfo}" var="info">
										<br />${info}
										</c:forEach>
										</div>
								</td>
							</tr>
							<tr>
							
								<td colspan="8">
									<div id="saveBtn" style="display:none;"
										style="width: 80px; height: 23px; cursor: pointer; float: right; position: relative; right: 300px">
										<img src="<%=path%>/images/baocun.png" width="100%"
											height="100%">
										<span
											style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">保存</span>
									</div>
								</td>
								
							</tr>
							
						</table>
					</td>
				</tr>
				
			</table>
			<input type="hidden" name="cid" value="<%=id %>" />
		</form>
		
	</body>
</html>
<script>
function modifyjz(id){
	
   // alert(path);
     		document.form1.action=path+"/servlet/cbzdfjxiazai?id="+id+"&bzw=1";
     		 document.form1.submit();
    }
function modifyjz1(id){
   // alert(path);
     		document.form1.action=path+"/servlet/cbzdfjxiazai2?id="+id+"&bzw=2";
     		 document.form1.submit();
    }
function modifyjz2(id){
   // alert(path);
     		document.form1.action=path+"/servlet/cbzdfjxiazai?id="+id+"&bzw=3";
     		 document.form1.submit();
    }
function modifyjz3(id){
   // alert(path);
     		document.form1.action=path+"/servlet/cbzdfjxiazai2?id="+id+"&bzw=4";
     		 document.form1.submit();
    }
</script>

