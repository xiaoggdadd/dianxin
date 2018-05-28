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
	String id=request.getParameter("id")!=null?request.getParameter("id"):"";
	String cid=request.getParameter("cid")!=null?request.getParameter("cid"):"";
	String cjc = request.getParameter("changjianow")!=null?request.getParameter("changjianow"):"";
	System.out.println("DDDDDD"+cjc+"ssssss"+id);

	XianSignDao dao1 = new XianSignDao();
	cbzddao  dao2=new cbzddao();
	cbzddao  dao3=new cbzddao();
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
	String wcsj1="";//完成时间 //超标站点查询
	String fjstruction = "";//附件说明
	String csms="";//测试描述
	String dgpch="";//流程号
	String yppch="";//批次号
	String bt="";//标题
	String sjyqwcsj="";//省级要求完成时间
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
	String dxgxsblast="0";//电信共享设备数量
	String ydgxsblast="0";//移动共享设备数量
	String g2xq="0";//2g小区
	String g3sq="0";//3g扇区
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
	String dxgxsb="0";//电信共享设备数量
	String ydgxsb="0";//移动共享设备数量
	String xg2xq="0";//2g小区
	String xg3sq="0";//3g扇区
	
	String dbds="0";//电表读数
	String kgzl="0";//开关直流负荷
	String ydzl="0";//移动共享设备直流负荷
	String dxzl="0";//电信共享设备直流负荷
	String gyzl="0";//固移共享设备直流负荷
	String lyzl="0";//直流远供负荷
	String yyfx="";//原因分析
	String csr="";//测试人
	
	String xfzgyq="";//整改要求
	String wcsj="";//完成时间
	
	String wcsm="";//完成说明
	String wctime="";//完成时间
	String zgfzr="";//整改负责人
	
	String byy="";//整改省级审核不通过原因
	String btime="";//整改省级审核完成时间
	
	String xfyy="";//下发省级审核不通过原因
	String xftime="";//下发省级审核完成时间
	String sjname;//区县附件路径
	String qxname;//区县附件名称
	String sjpath;//省级附件名称(下发)
	String qxname1;//区县附件名称(下发)
	String zlzfh="";
	String jlzfh="";
	String qsdb="";
	String bdhdl="";
	
	
	bean=dao1.getXinXi(id,loginId);
	int r = dao2.CheckQx(id);
	int rr=dao3.CheckZg(cid);
	//站点信息获取
	zdid=bean.getZdid();
	zdname=bean.getZdname();
	shi=bean.getShi();
	xian=bean.getXian();
	xiaoqu=bean.getXiaoqu();
	accountmonth=bean.getCbsj();
	entryperson=bean.getSjlrr();
	entrytime=bean.getSjlrsj();
	cbbili=bean.getBzpld();
	zgaskinstruction=bean.getZgyq();
	wcsj1=bean.getWcsj1();
	dgpch=bean.getDgpch();
	yppch=bean.getYppch();
	bt=bean.getBt();
	sjname=bean.getSjname();
	qxname=bean.getQxname();
	sjpath=bean.getSjpath();
	qxname1=bean.getQxname1();
	
	System.out.println(dgpch+"-------------"+yppch);
	if(zdid==null||zdid.equals("")||zdid.equals("null")||zdid.equals(" ")){zdid="";}
	if(zdname==null||zdname.equals("")||zdname.equals("null")||zdname.equals(" ")){zdname="";}
	if(shi==null||shi.equals("")||shi.equals("null")||shi.equals(" ")){shi="";}
	if(xian==null||xian.equals("")||xian.equals("null")||xian.equals(" ")){xian="";}
	if(xiaoqu==null||xiaoqu.equals("")||xiaoqu.equals("null")||xiaoqu.equals(" ")){xiaoqu="";}
	if(accountmonth==null||accountmonth.equals("")||accountmonth.equals("null")||accountmonth.equals(" ")){accountmonth="";}
	if(entryperson==null||entryperson.equals("")||entryperson.equals("null")||entryperson.equals(" ")){entryperson="";}
	if(entrytime==null||entrytime.equals("")||entrytime.equals("null")||entrytime.equals(" ")){entrytime="";}
	if(cbbili==null||cbbili.equals("")||cbbili.equals("null")||cbbili.equals(" ")){cbbili="0";}
	if(zgaskinstruction==null||zgaskinstruction.equals("")||zgaskinstruction.equals("null")||zgaskinstruction.equals(" ")){zgaskinstruction="";}
	if(sjyqwcsj==null||sjyqwcsj.equals("")||sjyqwcsj.equals("null")||sjyqwcsj.equals(" ")){sjyqwcsj="";}
	if(dgpch==null||dgpch.equals("")||dgpch.equals("null")||dgpch.equals(" ")){dgpch="";}
	if(yppch==null||yppch.equals("")||yppch.equals("null")||yppch.equals(" ")){yppch="";}
	if(bt==null||bt.equals("")||bt.equals("null")||bt.equals(" ")){bt="";}
	if(wcsj1==null||wcsj1.equals("")||wcsj1.equals("null")||wcsj1.equals(" ")){wcsj1="";}
	if(sjname==null||sjname.equals("")||sjname.equals("null")||sjname.equals(" ")){sjname="";}
	if(qxname==null||qxname.equals("")||qxname.equals("null")||qxname.equals(" ")){qxname="";}
	if(sjpath==null||sjpath.equals("")||sjpath.equals("null")||sjpath.equals(" ")){sjpath="";}
	if(qxname1==null||qxname1.equals("")||qxname1.equals("null")||qxname1.equals(" ")){qxname1="";}
	szdq=shi+xian+xiaoqu;
	//原11大类获取
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
	g2xq=bean.getJg2xq();
	g3sq=bean.getJg3sq();
	if(g2xq==null||g2xq.equals("")||g2xq.equals("null")||g2xq.equals(" ")){g2xq="0";}
	if(g3sq==null||g3sq.equals("")||g3sq.equals("null")||g3sq.equals(" ")){g3sq="0";}
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
	changjia=bean.getXchangjia();
	jztype=bean.getXjztype();
	shebei=bean.getXshebei();
	bbu=bean.getXbbu();
	rru=bean.getXrru();
	gxgwsl=bean.getXgxgwsl();
	ydgxsb=bean.getYdgxsb();
	dxgxsb=bean.getDxgxsb();
	xg2xq=bean.getG2xq();
	xg3sq=bean.getG3sq();
	if(g2==null||g2.equals("")||g2.equals("null")||g2.equals(" ")){g2="0";}
	if(g3==null||g3.equals("")||g3.equals("null")||g3.equals(" ")){g3="0";}
	if(changjia==null||changjia.equals("")||changjia.equals("null")||changjia.equals(" ")){changjia="";}
	if(jztype==null||jztype.equals("")||jztype.equals("null")||jztype.equals(" ")){jztype="";}
	if(shebei==null||shebei.equals("")||shebei.equals("null")||shebei.equals(" ")){shebei="";}
	if(dxgxsb==null||dxgxsb.equals("")||dxgxsb.equals("null")||dxgxsb.equals(" ")){dxgxsb="0";}
	if(ydgxsb==null||ydgxsb.equals("")||ydgxsb.equals("null")||ydgxsb.equals(" ")){ydgxsb="0";}
	if(xg2xq==null||xg2xq.equals("")||xg2xq.equals("null")||xg2xq.equals(" ")){xg2xq="0";}
	if(xg3sq==null||xg3sq.equals("")||xg3sq.equals("null")||xg3sq.equals(" ")){xg3sq="0";}

	dbds=bean.getDbds();
	kgzl=bean.getKGDYZLFH();
	ydzl=bean.getYDGXSBZLFH();
	dxzl=bean.getDXGXSBZLFH();
	gyzl=bean.getGYGXSBZLFH();
	lyzl=bean.getZYYGSBZLFH();
	yyfx=bean.getYyfx();
	csr=bean.getCszrr();
	
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
	if(dbds==null||dbds.equals("")||dbds.equals("null")||dbds.equals(" ")){dbds="0";}
	if(kgzl==null||kgzl.equals("")||kgzl.equals("null")||kgzl.equals(" ")){kgzl="0";}
	if(ydzl==null||ydzl.equals("")||ydzl.equals("null")||ydzl.equals(" ")){ydzl="0";}
	if(dxzl==null||dxzl.equals("")||dxzl.equals("null")||dxzl.equals(" ")){dxzl="0";}
	if(gyzl==null||gyzl.equals("")||gyzl.equals("null")||gyzl.equals(" ")){gyzl="0";}
	if(lyzl==null||lyzl.equals("")||lyzl.equals("null")||lyzl.equals(" ")){lyzl="0";}
	
	xfzgyq=bean.getXfzgyq();
	wcsj=bean.getWcsj();
	wcsm=bean.getWcsm();
	wctime=bean.getQxwcsj();
	zgfzr=bean.getZgzrr();
	byy=bean.getTdyy();
	btime=bean.getFKSJ();
	xfyy=bean.getSjyqwtgyy();
	xftime=bean.getSjyqwcsj();
	
	if(yyfx==null||yyfx.equals("")||yyfx.equals("null")||yyfx.equals(" ")){yyfx="";}
	if(csr==null||csr.equals("")||csr.equals("null")||csr.equals(" ")){csr="";}
	if(xfzgyq==null||xfzgyq.equals("")||xfzgyq.equals("null")||xfzgyq.equals(" ")){xfzgyq="";}
	if(wcsj==null||wcsj.equals("")||wcsj.equals("null")||wcsj.equals(" ")){wcsj="";}
	if(wcsm==null||wcsm.equals("")||wcsm.equals("null")||wcsm.equals(" ")){wcsm="";}
	if(wctime==null||wctime.equals("")||wctime.equals("null")||wctime.equals(" ")){wctime="";}
	if(zgfzr==null||zgfzr.equals("")||zgfzr.equals("null")||zgfzr.equals(" ")){zgfzr="";}
	
	if(byy==null||byy.equals("")||byy.equals("null")||byy.equals(" ")){byy="";}
	if(btime==null||btime.equals("")||btime.equals("null")||btime.equals(" ")){btime="";}
	if(xfyy==null||xfyy.equals("")||xfyy.equals("null")||xfyy.equals(" ")){xfyy="";}
	if(xftime==null||xftime.equals("")||xftime.equals("null")||xftime.equals(" ")){xftime="";}
	
	//System.out.println("Y"+g2+"gg"+g2last+"ddd"+g3+"sss"+g3last);
	System.out.println("Y"+r+"gg"+rr);
	
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

.formcenter {
	text-align: center;
	height: 19px;
	width: 130px;
}

.form1 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: right;
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
	text-align: left;
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

function shangchuan() {
	$("#errorInfo").text("");
	var month = $("#month").val();

	document.form1.action = path + "/servlet/FilesUploadServlet?month=" + month;
	document.form1.submit()
}
function saveDegree(){
	var wcsm = document.form1.wcsm.value;
	var beginTime1 = document.form1.beginTime1.value;
	var zgfzr = document.form1.zgfzr.value;
	var cid = document.form1.cid.value;
	//window.opener.
	document.form1.action=path+"/UploadB?action=qxzgwc&wcsm="+wcsm+"&beginTime1="+beginTime1+"&zgfzr="+zgfzr+"&cid="+cid;					        
		//window.opener.
		document.form1.submit();
		//window.close();
}
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
		<form action="" name="form1" id="form"  method="post" enctype="multipart/form-data">
		
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
										value="<%=szdq%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										报账(对标)月份：
									</div>
								</td>
								<td>
									<input type="text" name="accountmonth" readonly="readonly"
										value="<%=accountmonth%>" class="form2" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										超标比例：
									</div>
								</td>
								<td>
									<input type="text" name="cbbili" readonly="readonly"
										value="<%=cbbili%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										录入人：
									</div>
								</td>
								<td>
									<input type="text" name="entryperson" readonly="readonly"
										value="<%=entryperson%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										录入时间：
									</div>
								</td>
								<td>
									<input type="text" name="entrytime" readonly="readonly" value="<%=entrytime%>" class="form2" />
								</td>
								
								<td></td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										工单号：
									</div>
								</td>
								<td>
									<input type="text" name="cbbili" readonly="readonly" value="<%=dgpch %>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										批次号：
									</div>
								</td>
								<td>
									<input type="text" name="cbbili" readonly="readonly" value="<%=yppch %>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										标题：
									</div>
								</td>
								<td>
									<input type="text" name="entrytime" readonly="readonly" value="<%=bt%>" class="form2" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										整改要求说明：
									</div>
								</td>
								<td colspan='7' class="form1">
									<input type="text" name="zgaskinstruction" readonly="readonly" value="<%=zgaskinstruction %>" class="form2" />
								</td>
							</tr>
							<tr>
							<td bgcolor="#DDDDDD" class="form_label">
									<div>
										完成时间：
									</div>
								</td>
								<td>
									<input type="text" name="entrytime" readonly="readonly" value="<%=wcsj1%>" class="form2" />
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
									<div align="center" ><a href="#" onclick="modifyjz('<%=id%>')"><font  style="font-size: 12px;">下载</font></a></div>
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
									 <input type="text" name="g2last" value="<%=g2last %>" class="form2" readonly="readonly" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										3G设备：
									</div>
								</td>
								<td>
									<input type="text" name="g3last" value="<%=g3last %>" class="form2" readonly="readonly" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										载频：
									</div>
								</td>
								<td>
									<input type="text" name="zplast" readonly="readonly" value="<%=zplast%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										载扇：
									</div>
								</td>
								<td>
									<input type="text" name="zslast" readonly="readonly" value="<%=zslast%>" class="form1" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										厂家：
									</div>
								</td>
								<td>
									<input type="text" name="changjialast" readonly="readonly" value="<%=changjialast%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										基站类型：
									</div>
								</td>
								<td>
									<input type="text" name="jztypelast" readonly="readonly" value="<%=jztypelast%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										设备类型：
									</div>
								</td>
								<td>
									 <input type="text" name="shebeilast" readonly="readonly" value="<%=shebeilast%>" class="form2"  />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										拉远bbu数量：
									</div>
								</td>
								<td>
									<input type="text" name="bbulast" readonly="readonly" value="<%=bbulast%>" class="form1" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										远供rru数量：
									</div>
								</td>
								<td>
									<input type="text" name="rrulast" readonly="readonly" value="<%=rrulast%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										固移共享设备数量：
									</div>
								</td>
								<td>
									<input type="text" name="gxgwsllast" readonly="readonly" value="<%=gxgwsllast%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										电信共享设备数量：
									</div>
								</td>
								<td>
									<input type="text" name="gxgwsllast" readonly="readonly" value="<%=dxgxsblast%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										移动共享设备数量：
									</div>
								</td>
								<td>
									<input type="text" name="gxgwsllast" readonly="readonly" value="<%=ydgxsblast%>" class="form1" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										2G小区：
									</div>
								</td>
								<td>
									<input type="text" name="gxgwslnow" value="<%=g2xq%>" class="form1" readonly="readonly"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										3G扇区：
									</div>
								</td>
								<td>
									<input type="text" name="gxgwslnow" value="<%=g3sq%>" class="form1" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									测试11大类
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
									<input type="text" name="g2now" style="color:red" value="<%=g2 %>" class="form2" readonly="readonly"/>
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
									<input type="text" name="g3now" style="color:red" value="<%=g3 %>" class="form2" readonly="readonly"/>
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										载频：
									</div>
								</td>
								<%if(zp!=zpl){ %>
								<td>
									<input type="text" name="zpnow" style="color:red" value="<%=zp %>" class="form1" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="zpnow"  value="<%=zp %>" class="form1" readonly="readonly"/>
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										载扇：
									</div>
								</td>
								<%if(zs!=zsl){ %>
								<td>
									<input type="text" name="zsnow" style="color:red" value="<%=zs %>" class="form1" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="zsnow"  value="<%=zs %>" class="form1" readonly="readonly"/>
								</td>
								<%} %>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										厂家：
									</div>
								</td>
								<%if(changjia.equals(changjialast)){ %>
								<td>
									<input type="text" name="changjia" readonly="readonly" value="<%=changjia%>" class="form2" />
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="changjia" style="color:red" readonly="readonly" value="<%=changjia%>" class="form2" /><span class="style1">*</span>	
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										基站类型：
									</div>
								</td>
								<%if(jztype.equals(jztypelast)){ %>
								<td>
									<input type="text" name="jztype" readonly="readonly" value="<%=jztype%>" class="form2" />
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="jztype" style="color:red" readonly="readonly" value="<%=jztype%>" class="form2" /><span class="style1">*</span>	
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										设备类型：
									</div>
								</td>
								<%if(shebei.equals(shebeilast)){ %>
								<td>
									<input type="text" name="shebei" readonly="readonly" value="<%=shebei%>" class="form2" />
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="shebei" style="color:red" readonly="readonly" value="<%=shebei%>" class="form2" /><span class="style1">*</span>	
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										拉远bbu数量：
									</div>
								</td>
								<%if(bbu!=bbul){ %>
								<td>
									<input type="text" name="bbunow" style="color:red" value="<%=bbu%>" class="form1" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="bbunow" value="<%=bbu%>" class="form1" readonly="readonly"/>
								</td>
								<%} %>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										远供rru数量：
									</div>
								</td>
								<%if(rru!=rrul){ %>
								<td>
									<input type="text" name="rrunow" style="color:red" value="<%=rru%>" class="form1" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="rrunow" value="<%=rru%>" class="form1" readonly="readonly"/>
								</td>
								<%} %>
								
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										固移共享设备数量：
									</div>
								</td>
								<%if(gxgwsl!=gxgwsll){ %>
								<td>
									<input type="text" name="ydshebeinow" style="color:red" value="<%=gxgwsl%>" class="form1" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="ydshebeinow"  value="<%=gxgwsl%>" class="form1" readonly="readonly"/>
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										电信共享设备数量：
									</div>
								</td>
								<%if(dxgxsb.equals(dxgxsblast)){ %>
								<td>
									<input type="text" name="gxgwslnow" value="<%=dxgxsb%>" class="form1" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="gxgwslnow" style="color:red" value="<%=dxgxsb%>" class="form1" readonly="readonly"/>
								</td>
								<%} %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										移动共享设备数量：
									</div>
								</td>
								<%if(ydgxsb.equals(ydgxsblast)){ %>
								<td>
									<input type="text" name="gxgwslnow" value="<%=ydgxsb%>" class="form1" readonly="readonly"/>
								</td>
								<%}else{ %>
								<td>
									<input type="text" name="gxgwslnow" style="color:red" value="<%=ydgxsb%>" class="form1" readonly="readonly"/>
								</td>
								<%} %>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										2G小区：
									</div>
								</td>
								<%if(xg2xq.equals(g2xq)){ %>
								<td>
									<input type="text" name="gxgwslnow" value="<%=xg2xq%>" class="form1" readonly="readonly"/>
								</td>
								<%}else{%>
								<td>
									<input type="text" name="gxgwslnow" style="color:red" value="<%=xg2xq%>" class="form1" readonly="readonly"/>
								</td>
								<%}  %>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										3G扇区：
									</div>
								</td>
								<%if(xg3sq.equals(g3sq)){ %>
								<td>
									<input type="text" name="gxgwslnow" value="<%=xg3sq%>" class="form1" readonly="readonly"/>
								</td>
								<%}else{%>
								<td>
									<input type="text" name="gxgwslnow" style="color:red" value="<%=xg3sq%>" class="form1" readonly="readonly"/>
								</td>
								<%}  %>
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
									<input type="text" name="zlygfh" value="<%=lyzl %>" class="form1" readonly="readonly"/>
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
									<input type="text" name="reasonanalyse" value="<%=yyfx %>" class="formjc" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										测试人：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="testperson" value=<%=csr %>"" class="form2" readonly="readonly"/>
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
									<div align="center" ><a href="#" onclick="modifyjz1('<%=id%>')"><font  style="font-size: 12px;">下载</font></a></div>
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
									<input type="text" name="wcsm" value="<%=xfzgyq %>" class="form2" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										完成时间：
									</div>
								</td>
								<td>
									<input type="text" name="bbulast" readonly="readonly" value="<%=wcsj %>" class="form2" readonly="readonly"/>
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
									<div align="center" ><a href="#" onclick="modifyjz2('<%=cid%>')"><font  style="font-size: 12px;">下载</font></a></div>
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
										整改完成说明：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="wcsm" value="<%=wcsm %>" class="form2" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										整改完成时间：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="beginTime1" value="<%=wctime %>"  class="form" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										整改负责人：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="zgfzr" value="<%=zgfzr %>" class="form2" readonly="readonly" />
								</td>
							</tr>
							<tr height="19px">
								<td bgcolor="#DDDDDD" class="form_label"><div>
									附件：
								</div></td>
								<%if(rr==1){ %>
								<td colspan='5'>
									<div align="center" ><a href="#" onclick="modifyjz3('<%=cid%>')"><font  style="font-size: 12px;">下载</font></a></div>
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
									整改省级审核
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										不通过原因：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="reasonanalyse" value="<%=byy %>" class="form2" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										完成时间：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="testperson" value="<%=btime %>" class="form2" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									下发省级审核
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										不通过原因：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="reasonanalyse" value="<%=xfyy%>" class="form2" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										完成时间：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="testperson" value="<%=xftime %>" class="form2" readonly="readonly" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" name="cid" value="<%=cid %>" />
		</form>
		
	</body>
</html>
<script  type="text/javascript">
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

</script>
<script>
//document.form1.jztypelast.value='<%=bean.getJztype()%>';//原始基站类型2
//if(document.form1.jztypelast.value==""||document.form1.jztypelast.value==null){
// document.form1.jztypelast.value="0";
//}
///document.form1.jztypenow.value='<%=bean.getXjztype()%>';//新基站类型2
///if(document.form1.jztypenow.value==""||document.form1.jztypenow.value==null){
 //document.form1.jztypenow.value="0";
//}


//document.form1.g2last.value='<%=bean.getG2()%>';//原始2G设备
//if(document.form1.g2last.value==""||document.form1.g2last.value==null){
// document.form1.g2last.value="0";
 ///}
//document.form1.g3last.value='<%=bean.getG3()%>';//原始3G设备
//if(document.form1.g3last.value==""||document.form1.g3last.value==null){
// document.form1.g3last.value="0";
 //}
//document.form1.g2now.value='<%=bean.getXg2()%>';//新2G设备
//if(document.form1.g2now.value==""||document.form1.g2now.value==null){
// document.form1.g2now.value="0";
//}
//document.form1.g3now.value='<%=bean.getXg3()%>';//新3G设备
///if(document.form1.g3now.value==""||document.form1.g3now.value==null){
 //document.form1.g3now.value="0";
 //}
//document.form1.changjialast.value='<%=bean.getChangjia()%>';//changjia
//if(document.form1.changjialast.value==""||document.form1.changjialast.value==null){
// document.form1.changjialast.value="0";
///}

//document.form1.changjianow.value='<%=bean.getXchangjia()%>';//changjia
//if(document.form1.changjianow.value==""||document.form1.changjianow.value==null){
// document.form1.changjianow.value="0";
//}
//document.form1.shebeilast.value='<%=bean.getShebei()%>';//设备类型
//if(document.form1.shebeilast.value==""||document.form1.shebeilast.value==null){
// document.form1.shebeilast.value="0";
//}
//document.form1.shebeinow.value='<%=bean.getXshebei()%>';//设备类型
//if(document.form1.shebeinow.value==""||document.form1.shebeinow.value==null){
// document.form1.shebeinow.value="0";
//} 


</script>
