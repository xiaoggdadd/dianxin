<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account,java.util.List"%>
<%@ page import="java.text.*"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.noki.newfunction.dao.PdTestBewrite,com.noki.newfunction.javabean.Zdinfo,com.noki.newfunction.dao.CbzdQuery,com.noki.zdqxkz.dao.ShiQuery,com.noki.zdqxkz.javabean.Zdqxkz"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
	String accountid = account.getAccountName();
	String loginId = account.getAccountId();
	String g2last="",g3last="",zplast="",zslast="",bbulast="",rrulast="",ydshebeilast="",gxgwsllast="";
	String changjialast="",jztypelast="",shebeilast="",dxgxsblast="",ydgxsblast="",g2xqlast="",g3sqlast="";
	String idcz = request.getParameter("zdid") != null ? request.getParameter("zdid"): "";//前台传值唯一标识id
	
	String iddb=(String)session.getAttribute("idshisj");
	
	if(null==idcz||"".equals(idcz)){
	idcz=iddb;
	}
	
	
	//System.out.println("idcz:"+idcz);
		CbzdQuery zd=new CbzdQuery();
	PdTestBewrite bean = new PdTestBewrite();
 String bgnew="";//标杆是否(新)
 String bgold="";//标杆时候(旧)
 String djnew="";//单价(新)
 String djold="";//单价(旧)
 String edhdlnew="";//额定耗电量(新)
 String edhdlold="";//额定耗电量(旧)
 String jlfhnew="";//交流负荷(新)
 String jlfhold="";//交流负荷(旧)
 String zlfhnew="";//直流负荷(新)
 String zlfhold="";//直流负荷(旧)
 String zdsxnew="";//站点属性(新)
 String zdsxold="";//站点属性(旧)
 String zdlxnew="";//站点类型(新)
 String zdlxold="";//站点类型(旧)
 String yflxnew="";//用房类型(新)
 String yflxold="";//用房类型(旧)
 String gxxxnew="";//共享信息(新)
 String gxxxold="";//共享信息(旧)
 String gdfsnew="";//供电方式(新)
 String gdfsold="";//供电方式(旧)
 String zgdnew="";//直供电(新)
 String zgdold="";//直供电(旧)
 String zdmjnew="";//占地面积(新)
 String zdmjold="";//占地面积(旧)
 String zdqyztnew="";//站点启用状态(新)
 String zdqyztold="";//站点启用状态(旧)
 String g2new="";//g2是否(新)
 String g2old="";//g2是否(旧)
  String g3new="";//g3是否(新)
 String g3old="";//g3是否(旧)
 String zpnew="";//载频(新)
 String zpold="";//载频(旧)
 String zsnew="";//载扇(新)
 String zsold="";//载扇(旧)
 String jzlx2new="";//基站类型2(新)
 String jzlx2old="";//基站类型2(旧)
 String  cjnew="";//厂家(新)
String cjold="";//厂家(旧) 
String shebeinew="";//设备编码(新)
String shebeiold="";//设备编码(旧)
String bbunew="";//bbu数量(新)
String bbuold="";//bbu数量(旧)
String rrunew="";//rru数量(新)
String rruold="";//rru数量(旧)
String ydshebeinew="";//移动设备数量(新)
String ydshebeiold="";//移动设备数量(旧)
String gxgwslnew="";//共享固网数量(新)
String  gxgwslold="";//共享固网数量(旧)
String xgyj="";//修改依据
String xgsm="";//修改说明
String fjmc="";//附件名称
String oldqsdbdl="";//全省定标电量（旧）
String newqsdbdl="";//全省定标电量（新）
String newqsdb="";// 2014-9-11 WangYiXiao 不含空调（省定标）
String oldqsdb="";//2014-9-11 WangYiXiao 旧不含空调（省定标）
String dbds="";//电表读数
String newdfzflx = "";//电费支付类型(新)
String olddfzflx = "";//电费支付类型(旧)
String id="";//id
	//获取站点信息
	if(idcz!=null){
        	// 站点信息Z
        	List<Zdqxkz> ls=null;
        	Zdqxkz zq=new Zdqxkz();
        	ShiQuery sh=new ShiQuery();
        	ls=sh.seachDatassh(idcz);
        	if(ls!=null){
        	for(Zdqxkz lst:ls ){
        	bgnew=lst.getNewbgsign();
        	bgold=lst.getOldbgsign();
        	djnew=lst.getNewdianfei();
        	djold=lst.getOlddianfei();
        	edhdlnew=lst.getNewedhdl();
        	edhdlold=lst.getOldedhdl();
        	jlfhnew=lst.getNewjlfh();
        	jlfhold=lst.getOldjlfh();
        	zlfhnew=lst.getNewzlfh();
        	zlfhold=lst.getOldzlfh();
        	zdsxnew=lst.getNewproperty();
        	zdsxold=lst.getOldproperty();
        	zdlxnew=lst.getNewstationtype();
        	zdlxold=lst.getOldstationtype();
        	yflxnew=lst.getNewyflx();
        	yflxold=lst.getOldyflx();
        	gxxxnew=lst.getNewgxxx();
        	gxxxold=lst.getOldgxxx();
        	gdfsnew=lst.getNewgdfs();
        	gdfsold=lst.getOldgdfs();
        	zgdnew=lst.getNewzgd();
        	zgdold=lst.getOldzgd();
        	zdqyztnew=lst.getNewqyzt();
        	zdqyztold=lst.getOldqyzt();
        	zdmjnew=lst.getNewarea();
        	zdmjold=lst.getOldarea();
        	g2new=lst.getNewg2();
        	g2old=lst.getOldg2();
        	g3new=lst.getNewg3();
        	g3old=lst.getOldg3();
        	zpnew=lst.getNewzpsl();
        	zpold=lst.getOldzpsl();
        	zsnew=lst.getNewzssl();
        	zsold=lst.getOldzssl();
        	cjnew=lst.getNewchangjia();
        	cjold=lst.getOldchangjia();
        	jzlx2new=lst.getNewjzlx();
        	jzlx2old=lst.getOldjzlx();
        	shebeinew=lst.getNewshebei();
        	shebeiold=lst.getOldshebei();
        	bbunew=lst.getNewbbu();
        	bbuold=lst.getOldbbu();
        	rrunew=lst.getNewrru();
        	rruold=lst.getOldrru();
        	ydshebeinew=lst.getNewydshebei();
        	ydshebeiold=lst.getOldydshebei();
        	gxgwslnew=lst.getNewgxgwsl();
        	gxgwslold=lst.getOldgxgwsl();
        	xgyj=lst.getXgyj();
        	xgsm=lst.getXgsm();
        	fjmc=lst.getFjmc();
        	oldqsdbdl=lst.getOldqsdbdl();
        	newqsdbdl=lst.getNewqsdbdl();
        	
        	newqsdb=lst.getNewqsdbdl();
            oldqsdb = lst.getOldqsdbdl();
            
            newdfzflx = lst.getNewdfzflx();
            olddfzflx = lst.getOlddfzflx();
            if(null==newdfzflx||"null".equals(newdfzflx)){newdfzflx="";}
            if(null==olddfzflx||"null".equals(olddfzflx)){olddfzflx="";}
        	
        	id=lst.getId();
        	if(null==xgyj||"".equals(xgyj)||"null".equals(xgyj)){xgyj="";}
        	if(null==xgsm||"".equals(xgsm)||"null".equals(xgsm)){xgsm="";}
        	if(null==newqsdb||"".equals(newqsdb)||"null".equals(newqsdb)){newqsdb="0";}
        	if(null==oldqsdb||"".equals(oldqsdb)||"null".equals(oldqsdb)){oldqsdb="0";}
        	if(null==ydshebeinew||"".equals(ydshebeinew)||"null".equals(ydshebeinew)){ydshebeinew="0";}
        	if(null==ydshebeiold||"".equals(ydshebeiold)||"null".equals(ydshebeiold)){ydshebeiold="0";}
        	if(null==gxgwslnew||"".equals(gxgwslnew)||"null".equals(gxgwslnew)){gxgwslnew="0";}
        	if(null==gxgwslold||"".equals(gxgwslold)||"null".equals(gxgwslold)){gxgwslold="0";}
        	
        	
        	
        	if(null==bbunew||"".equals(bbunew)||"null".equals(bbunew)){bbunew="0";}
        	if(null==bbuold||"".equals(bbuold)||"null".equals(bbuold)){bbuold="0";}
        	if(null==rrunew||"".equals(rrunew)||"null".equals(rrunew)){rrunew="0";}
        	if(null==rruold||"".equals(rruold)||"null".equals(rruold)){rruold="0";}
        	
        	
        	if(null==shebeinew||"".equals(shebeinew)||"null".equals(shebeinew)){shebeinew="";}
        	if(null==shebeiold||"".equals(shebeiold)||"null".equals(shebeiold)){shebeiold="";}
        	
        	if(null==cjnew||"".equals(cjnew)||"null".equals(cjnew)){cjnew="";}
        	if(null==cjold||"".equals(cjold)||"null".equals(cjold)){cjold="";}
        	if(null==jzlx2new||"".equals(jzlx2new)||"null".equals(jzlx2new)){jzlx2new="";}
        	if(null==jzlx2old||"".equals(jzlx2old)||"null".equals(jzlx2old)){jzlx2old="";}
        	
        	
        	if(null==zpnew||"".equals(zpnew)||"null".equals(zpnew)){zpnew="0";}
        	if(null==zpold||"".equals(zpold)||"null".equals(zpold)){zpold="0";}
        	if(null==zsnew||"".equals(zsnew)||"null".equals(zsnew)){zsnew="0";}
        	if(null==zsold||"".equals(zsold)||"null".equals(zsold)){zsold="0";}
        	
        	if("1".equals(g2new)){g2new="是";}else{g2new="否";}
        	if("1".equals(g2old)){g2old="是";}else{g2old="否";}
        	if("1".equals(g3new)){g3new="是";}else{g3new="否";}
        	if("1".equals(g3old)){g3old="是";}else{g3old="否";}
        	
        	DecimalFormat cbd= new DecimalFormat("0.00");
        	if(null==zdmjnew||"".equals(zdmjnew)||"null".equals(zdmjnew)){zdmjnew="0";}
        	if(null==zdmjold||"".equals(zdmjold)||"null".equals(zdmjold)){zdmjold="0";}
        	zdmjnew=cbd.format(Double.parseDouble(zdmjnew));
        	zdmjold=cbd.format(Double.parseDouble(zdmjold));
        	
        	if("1".equals(zdqyztnew)){zdqyztnew="是";}else{zdqyztnew="否";}
        	if("1".equals(zdqyztold)){zdqyztold="是";}else{zdqyztold="否";}
        	if("1".equals(zgdnew)){zgdnew="是";}else{zgdnew="否";}
        	if("1".equals(zgdold)){zgdold="是";}else{zgdold="否";}
        	if(null==gdfsnew||"".equals(gdfsnew)||"null".equals(gdfsnew)){gdfsnew="";}
        	if(null==gdfsold||"".equals(gdfsold)||"null".equals(gdfsold)){gdfsold="";}
        	
        	if(null==gxxxnew||"".equals(gxxxnew)||"null".equals(gxxxnew)){gxxxnew="";}
        	if(null==gxxxold||"".equals(gxxxold)||"null".equals(gxxxold)){gxxxold="";}
        	
        	if(null==yflxnew||"".equals(yflxnew)||"null".equals(yflxnew)){yflxnew="";}
        	if(null==yflxold||"".equals(yflxold)||"null".equals(yflxold)){yflxold="";}
        	if(null==zdlxnew||"".equals(zdlxnew)||"null".equals(zdlxnew)){zdlxnew="";}
        	if(null==zdlxold||"".equals(zdlxold)||"null".equals(zdlxold)){zdlxold="";}
        	if(null==zdsxnew||"".equals(zdsxnew)||"null".equals(zdsxnew)){zdsxnew="";}
        	if(null==zdsxold||"".equals(zdsxold)||"null".equals(zdsxold)){zdsxold="";}
        	DecimalFormat cb = new DecimalFormat("0.00");
        	DecimalFormat cbb = new DecimalFormat("0.0");
        	if(null==jlfhnew||"".equals(jlfhnew)||"null".equals(jlfhnew)){jlfhnew="0";}
        	jlfhnew=cbb.format(Double.parseDouble(jlfhnew));
        	if(null==jlfhold||"".equals(jlfhold)||"null".equals(jlfhold)){jlfhold="0";}
        	jlfhold=cbb.format(Double.parseDouble(jlfhold));
        	if(null==zlfhnew||"".equals(zlfhnew)||"null".equals(zlfhnew)){zlfhnew="0";}
        		zlfhnew=cbb.format(Double.parseDouble(zlfhnew));
        	if(null==zlfhold||"".equals(zlfhold)||"null".equals(zlfhold)){zlfhold="0";}
        	zlfhold=cbb.format(Double.parseDouble(zlfhold));
        	if(null==edhdlnew||"".equals(edhdlnew)||"null".equals(edhdlnew)){edhdlnew="0";}
        	edhdlnew=cb.format(Double.parseDouble(edhdlnew));
        	if(null==edhdlold||"".equals(edhdlold)||"null".equals(edhdlold)){edhdlold="0";}
        		edhdlold=cb.format(Double.parseDouble(edhdlold));
        	if(null==djnew||"".equals(djnew)||"null".equals(djnew)){djnew="";}
        	if(null==djold||"".equals(djold)||"null".equals(djold)){djold="";}
        	if("1".equals(bgnew)){bgnew="是";}else{bgnew="否";}
        	if("1".equals(bgold)){bgold="是";}else{bgold="否";}
        	
        	dbds=lst.getDbds();
        	if(null==dbds||"".equals(dbds)||"null".equals(dbds)){dbds="";}	
        	if(!StringUtils.isEmpty(dbds)){dbds=cb.format(Double.parseDouble(dbds));}
				
%>
<html>
	<head>
		<title>测试描述修改</title>

		<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	width: 120px;
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
	width: 80px;
}

.form3 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: right;
	height: 19px;
	width: 80px;
}
.form31 {
	
	text-align: right;
	height: 19px;
	width: 80px;
}
.form4 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: left;
	height: 19px;
	width: 80px;
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
 <script src="<%=path%>/javascript/tx.js"></script>
<script language="javascript">
	/*var path = '<%=path%>';
	function xiazai(id,zdname,accountmonth){
	   	document.form1.action=path+"/servlet/cbzdfjxiazai?id="+id+"&zdname="+zdname+"&accountmonth="+accountmonth+"&bzw=1";
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
		<LINK href="../../images/css.css" type="text/css" rel=stylesheet />
		<form action="" name="form1" method="POST" id="form" enctype=multipart/form-data>
			<table border="0" width="100%">
				<tr>
					<td width="100%" scope="2">
						<table width="100%" border="0" cellspacing="1" cellpadding="1">
							
							<tr>
								<td colspan="12" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									市级审核
								</td>
							</tr>
							<tr>
								<!--<td colspan="8">
									<table>
										<tr>
											 <td rowspan='3' bgcolor="#DDDDDD" class="form_labell">
												原11大类
											</td> -->
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													是否标杆：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=bgold%>" class="form4" />
											</td><td bgcolor="#DDDDDD" class="form_label"><div>改为</div></td>
											<%if(bgnew.equals(bgold)){ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=bgnew%>" class="form4" />
											</td>
											<%}else{ %>
												<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=bgnew%>" class="form4" style="color: red" />
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													单价：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g3last" readonly="true"
													value="<%=djold%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为
												</div>
											</td>
											<%if(djnew.equals(djold)){ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=djnew%>" class="form2" />
											</td>
											<%}else{ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=djnew%>" class="form2" style="color: red" />
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													额定耗电量：
												</div>
											</td>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=edhdlold%>" class="form2" />
											</td>
												<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为：
												</div>
											</td>
											<%if(edhdlnew.equals(edhdlold)){%>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=edhdlnew%>" class="form2" />
											</td>
											<%}else{ %>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=edhdlnew%>" class="form2" style="color: red" />
											</td>
											<% }%>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													交流负荷：
												</div>
											</td>
											<td>
												<input type="text" name="chagnjialast" readonly="true"
													value="<%=jlfhold%>" class="form3" />
											</td>
											
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为：
												</div>
											</td>
											<%if(jlfhnew.equals(jlfhold)){ %>
											<td>
												<input type="text" name="jztypelast" readonly="true"
													value="<%=jlfhnew%>" class="form3" />
											</td>
											<%}else{ %>
											<td>
												<input type="text" name="jztypelast" readonly="true"
													value="<%=jlfhnew%>" class="form3" style="color: red" />
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													直流负荷：
												</div>
											</td>
											<td>
												<input type="text" name="shebeilast" readonly="true"
													value="<%=zlfhold%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为：
												</div>
											</td>
											<%if(zlfhnew.equals(zlfhold)){ %>
											<td>
												<input type="text" name="bbulast" readonly="true"
													value="<%=zlfhnew%>" class="form3" />
											</td>
											<%}else{%>
											<td>
												<input type="text" name="bbulast" readonly="true"
													value="<%=zlfhnew%>" class="form3" style="color: red"/>
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													电费支付类型:
												</div>
											</td>
											<td width="5%">
												<input type="text" name="dfzflxlast" readonly="true"
													value="<%=olddfzflx%>" class="form4" />
											</td><td bgcolor="#DDDDDD" class="form_label"><div>改为</div></td>
											<%if(newdfzflx.equals(olddfzflx)){ %>
											<td width="5%">
												<input type="text" name="dfzflxnow" readonly="true"
													value="<%=newdfzflx%>" class="form4" />
											</td>
											<%}else{ %>
											<td width="5%">
												<input type="text" name="dfzflxnow" readonly="true"
													value="<%=newdfzflx%>" class="form3" style="color: red"/>
											</td>
											<%} %>
										</tr>
										<tr>
										<td bgcolor="#DDDDDD" class="form_label">电表读数:</td>
											<td>
											<input type="text"  readonly="true" 
													value="<%=dbds%>" class="form2" />
											</td>
										</tr>
							<tr>
								<td colspan="12" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									省级审核
								</td>
							</tr>		
								<tr                                                                              >
							<td bgcolor="#DDDDDD" class="form_label">
												<div>
													站点属性：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=zdsxold%>" class="form4" />
											</td><td bgcolor="#DDDDDD" class="form_label"><div>改为</div></td>
											<%if(zdsxnew.equals(zdsxold)){ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=zdsxnew%>" class="form4" />
											</td>
											<%}else{ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=zdsxnew%>" class="form3" style="color: red"/>
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													站点类型：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g3last" readonly="true"
													value="<%=zdlxold%>" class="form4" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为
												</div>
											</td>
											<%if(zdlxnew.equals(zdlxold)){ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=zdlxnew%>" class="form4" />
											</td>
											<%}else{ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=zdlxnew%>" class="form4" style="color: red"/>
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													用房类型：
												</div>
											</td>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=yflxold%>" class="form4" />
											</td>
												<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为：
												</div>
											</td>
											
											<%if(yflxnew.equals(yflxold)){ %>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=yflxnew%>" class="form4" />
											</td>
											<%}else{ %>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=yflxnew%>" class="form4" style="color: red"/>
											</td>
											<%} %>
										</tr>
												<tr                                                                                 >
							<td bgcolor="#DDDDDD" class="form_label">
												<div>
													共享信息：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=gxxxold%>" class="form4" />
											</td><td bgcolor="#DDDDDD" class="form_label"><div>改为</div></td>
											<%if(gxxxnew.equals(gxxxold)){ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=gxxxnew%>" class="form4" />
											</td>
											<%}else{ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=gxxxnew%>" class="form4" style="color: red"/>
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													供电方式：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g3last" readonly="true"
													value="<%=gdfsold%>" class="form4" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为
												</div>
											</td>
											<%if(gdfsnew.equals(gdfsold)){ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=gdfsnew%>" class="form4" />
											</td>
											<% }else{%>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=gdfsnew%>" class="form4" style="color: red"/>
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													直供电：
												</div>
											</td>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=zgdold%>" class="form4" />
											</td>
												<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为：
												</div>
											</td>
											<%if(zgdnew.equals(zgdold)){ %>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=zgdnew%>" class="form4" />
											</td>
											<%}else{ %>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=zgdnew%>" class="form4" style="color: red"/>
											</td>
											<%} %>
										</tr>
												<tr                                                                                 >
							<td bgcolor="#DDDDDD" class="form_label">
												<div>
													站点面积：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=zdmjold%>" class="form3" />
											</td><td bgcolor="#DDDDDD" class="form_label"><div>改为</div></td>
											<%if(zdmjnew.equals(zdmjold)){ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=zdmjnew%>" class="form3" />
											</td>
											<%}else{ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=zdmjnew%>" class="form3" style="color: red"/>
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													站点启用状态：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g3last" readonly="true"
													value="<%=zdqyztold%>" class="form4" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为
												</div>
											</td>
											<%if(zdqyztnew.equals(zdqyztold)){ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=zdqyztnew%>" class="form4" />
											</td>
											<%}else{ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=zdqyztnew%>" class="form4" style="color: red" />
											</td>
											<%} %>
											
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													省定标(不含空调)：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=oldqsdb%>" class="form3" style="color: red"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为
												</div>
											<td width="5%">
												<input type="text" name="newqsdb" 
													value="<%=newqsdb%>" class="form31" style="color: red"/>
											</td>
										<!--  	<td bgcolor="#DDDDDD" class="form_label">
												<div>
													全省定标电量：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="qsdbdl" readonly="true"
													value="<%=oldqsdbdl%>" class="form3" />
											</td><td bgcolor="#DDDDDD" class="form_label"><div>改为</div></td>
											<%if(newqsdbdl.equals(oldqsdbdl)){ %>
											<td width="5%">
												<input type="text" name="qsdbdl" readonly="true"
													value="<%=newqsdbdl%>" class="form3" />
											</td>
											<%}else{ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=newqsdbdl%>" class="form3" style="color: red"/>
											</td>
											<%} %>
											-->
										</tr>
						<tr>
								<td colspan="12" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									站点信息
								</td>
							</tr>
							<tr                                                                              >
							<td bgcolor="#DDDDDD" class="form_label">
												<div>
													2G：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=g2old%>" class="form4" />
											</td><td bgcolor="#DDDDDD" class="form_label"><div>改为</div></td>
										<%if(g2new.equals(g2old)){ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=g2new%>" class="form4" />
											</td>
											<%}else{ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=g2new%>" class="form4" style="color: red"/>
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													3G：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g3last" readonly="true"
													value="<%=g3old%>" class="form4" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为
												</div>
											</td>
											<%if(g3new.equals(g3old)){ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=g3new%>" class="form4" />
											</td>
											<%}else{ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=g3new%>" class="form4" style="color: red"/>
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载频：
												</div>
											</td>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=zpold%>" class="form3" />
											</td>
												<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为：
												</div>
											</td>
											<%if(zpnew.equals(zpold)){ %>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=zpnew%>" class="form3" />
											</td>
											<%}else{ %>
												<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=zpnew%>" class="form3" style="color: red"/>
											</td>
											<%} %>
										</tr>
												<tr>
							<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载扇：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=zsold%>" class="form3" />
											</td><td bgcolor="#DDDDDD" class="form_label"><div>改为</div></td>
											<%if(zsnew.equals(zsold)){ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=zsnew%>" class="form3" />
											</td>
											<%}else{ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=zsnew%>" class="form3" style="color: red" />
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													厂家：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g3last" readonly="true"
													value="<%=cjold%>" class="form4" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为
												</div>
											</td>
											<%if(cjnew.equals(cjold)){%>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=cjnew%>" class="form4" />
											</td>
											<%}else{ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=cjnew%>" class="form4" style="color: red" />
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													基站类型2：
												</div>
											</td>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=jzlx2old%>" class="form2" />
											</td>
												<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为：
												</div>
											</td>
											<%if(jzlx2new.equals(jzlx2old)){ %>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=jzlx2new%>" class="form2" />
											</td>
											<%}else{%>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=jzlx2new%>" class="form2" style="color: red"/>
											</td>
											<%} %>
										</tr>
												<tr>
													<td bgcolor="#DDDDDD" class="form_label">
												<div>
													设备编码：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=shebeiold%>" class="form4" />
											</td><td bgcolor="#DDDDDD" class="form_label"><div>改为</div></td>
											<%if(shebeinew.equals(shebeiold)){ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=shebeinew%>" class="form4" />
											</td>
											<%}else{ %>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=shebeinew%>" class="form4" style="color: red"/>
											</td>
											<%} %>
											
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													BBU数量：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g3last" readonly="true"
													value="<%=bbuold%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为
												</div>
											</td>
											<%if(bbunew.equals(bbuold)){ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=bbunew%>" class="form3" />
											</td>
											<%}else{ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=bbunew%>" class="form3" style="color: red"/>
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													RRU数量：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g3last" readonly="true"
													value="<%=rruold%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为
												</div>
											</td>
											<%if(rrunew.equals(rruold)){ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=rrunew%>" class="form3" />
											</td>
											<%}else{ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=rrunew%>" class="form3" style="color: red"/>
											</td>
											<%} %>
										</tr>
									<tr>
									<td bgcolor="#DDDDDD" class="form_label">
												<div>
													他网共享设备数量：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g3last" readonly="true"
													value="<%=ydshebeiold%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为
												</div>
											</td>
											<%if(ydshebeinew.equals(ydshebeiold)){ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=ydshebeinew%>" class="form3" />
											</td>
											<%}else{ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=ydshebeinew%>" class="form3" style="color: red"/>
											</td>
											<%} %>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													共享固网设备数量：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g3last" readonly="true"
													value="<%=gxgwslold%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													改为
												</div>
											</td>
											<%if(gxgwslnew.equals(gxgwslold)){ %>
											<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=gxgwslnew%>" class="form3" />
											</td>
											<%}else{ %>
												<td>
												<input type="text" name="zplast" readonly="true"
													value="<%=gxgwslnew%>" class="form3" style="color: red"/>
											</td>
											<%} %>
									</tr>
										</table>
										<table>	
										<tr>	
										<td colspan="12" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									修改原因
								</td>
								</tr>
								
								<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										修改原因：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="reasonanalyse" value="<%=xgsm%>" class="formjc"  readonly="readonly"/>
								</td>
							</tr>
							<tr>
						
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										修改依据：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="reasonanalyse" value="<%=xgyj%>" class="formjc" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<%if(fjmc!=null&&!"".equals(fjmc)&&!"null".equals(fjmc)){%>
							<td colspan='2'>
									<div id="xiazai"
										style="width: 60px; height: 19px; cursor: pointer; float: right; position: relative; right: 70px">
										<img src="<%=path%>/images/baocun.png" width="100%"
											height="100%">
										<span
											style="font-size: 12px; position: absolute; left: 27px; top: 3px; color: white">下载</span>
									</div>
									</td>
									<%}else{%>
									<td colspan='2'>
									    <div align="center" >无下载附件</div>
									</td>
									<%}%>
							</tr>
										<tr>
					<td style="display:none" align="right">
						<div id="baocun" style="width:85px;height:23px;cursor:pointer;float:right;position: relative; right:-150px; top: 0px">
						<img  src="<%=path %>/images/mmcz.png" width="100%" height="100%">
						 <span style="font-size: 12px; position: absolute; left: 35px; top: 5px; color: white">保存</span>
						
						</div>
					</td>		
					</tr>
							<tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	<%}}}%>
	</body>
</html>
<SCRIPT LANGUAGE="JavaScript">

function getGray(obj){ 
var g2ls='<%=g2last%>';
var g2now=document.form1.g2now.value;
if("1"==g2now){g2now="是";}else{g2now="否";}
if(g2ls==g2now){
obj.style.color = 'BLACK';
}else{
obj.style.color = 'RED';
}
   
}
function getGrayg3(obj){ 
var g3ls='<%=g3last%>';
var g3now=document.form1.g3now.value;
if("1"==g3now){g3now="是";}else{g3now="否";}
if(g3ls==g3now){
obj.style.color = 'BLACK';
}else{
obj.style.color = 'RED';
}
   
}
// getGrayG2
function getGrayG2(obj){ 
var g2xqlast='<%=g2xqlast%>';
var g2xqnow=document.form1.g2xqnow.value;
if(null==g2xqnow||""==g2xqnow){g2xqnow="0";}
if(g2xqnow==g2xqlast){
obj.style.color = 'BLACK';
}else{
obj.style.color = 'RED';
}
   
}
//=================================
function getGrayG3(obj){ 
var g3sqlast='<%=g3sqlast%>';
var g3sqnow=document.form1.g3sqnow.value;
if(null==g3sqnow||""==g3sqnow){g3sqnow="0";}
if(g3sqnow==g3sqlast){
obj.style.color = 'BLACK';
}else{
obj.style.color = 'RED';
}
   
}
//====================================
function getGrayzp(obj){ 
var zpls='<%=zplast%>';
var zpnow=document.form1.zpnow.value;

if(null==zpnow||""==zpnow){zpnow="0";}
if(zpls==zpnow){
obj.style.color = 'BLACK';
}else{
obj.style.color = 'RED';
}
   
}
function getGrayzs(obj){ 
var zsls='<%=zslast%>';
var zsnow=document.form1.zsnow.value;

if(null==zsnow||""==zsnow){zsnow="0";}
if(zsls==zsnow){
obj.style.color = 'BLACK';
}else{
obj.style.color = 'RED';
}
   
}//bbulast="",rrulast="",ydshebeilast="",gxgwsllast=""
function getGraybbu(obj){ 
var bbu='<%=bbulast%>';
var bbunow=document.form1.bbunow.value;

if(null==bbunow||""==bbunow){bbunow="0";}
if(bbu==bbunow){
obj.style.color = 'BLACK';
}else{
obj.style.color = 'RED';
}
}
function getGrayrru(obj){ 
var rru='<%=rrulast%>';
var rrunow=document.form1.rrunow.value;

if(null==rrunow||""==rrunow){rrunow="0";}
if(rru==rrunow){
obj.style.color = 'BLACK';
}else{
obj.style.color = 'RED';
}
}
function getGrayyd(obj){ 
var yd='<%=ydshebeilast%>';
var ydunow=document.form1.ydshebeinow.value;

if(null==ydunow||""==ydunow){ydunow="0";}
if(yd==ydunow){
obj.style.color = 'BLACK';
}else{
obj.style.color = 'RED';
}
}
function getGraygw(obj){ 
var gw='<%=gxgwsllast%>';
var gwnow=document.form1.gxgwslnow.value;

if(null==gwnow||""==gwnow){gwnow="0";}
if(gw==gwnow){
obj.style.color = 'BLACK';
}else{
obj.style.color = 'RED';
}
}
function getBlack(obj){
   obj.style.color = 'BLACK';
}
// getGraycj(this);
 
 </SCRIPT>
 <SCRIPT LANGUAGE="JavaScript">
 /*function getGraycj(obj){ 
var cjlast='<%=changjialast%>';// changjianow
var cjnow=document.form1.changjianow.value;
obj.style.color = 'BLACK';
obj.style.color = 'RED';
}
*/
 </script>
 <script type="text/javascript">
 
/*通过异步传输XMLHTTP发送参数到ajaxServlet，返回符合条件的XML文档*/
 
function getGraycj(obj){  
 
var path='<%=path%>';
var cjlast='<%=changjialast%>';// changjianow
var cjnow=document.form1.changjianow.value;

var url = path+"/servlet/qxfj?action=send&cjnow="+cjnow;
 
 if (window.XMLHttpRequest) { 
 
    req = new XMLHttpRequest();   

 }else if (window.ActiveXObject){    
 
 req = new ActiveXObject("Microsoft.XMLHTTP");  
  }  
 
 if(req){     
 
    req.open("POST",url, true);     
 
    req.onreadystatechange = complete;   
 
    req.send(null); 
 
  
 }
 /*分析返回的XML文档*/
function complete(){ 
var cjlast='<%=changjialast%>'; 
      if (req.readyState == 4){    
 
             if (req.status == 200) {     
                 var  b= req.responseText;
                 var  c=rtrim(b);
             if(c==cjlast){
                 obj.style.color = 'BLACK';
                }else{
                  obj.style.color = 'RED';
             }
        } 
 }
}
}
function getGrayjztype(obj){  
 
var path='<%=path%>';
var jztypelast='<%=jztypelast%>';// changjianow
var jztypenow=document.form1.jztypenow.value;

var url = path+"/servlet/qxfj?action=send&jztypenow="+jztypenow+"&bb=1";
 
 if (window.XMLHttpRequest) { 
 
    req = new XMLHttpRequest();   

 }else if (window.ActiveXObject){    
 
 req = new ActiveXObject("Microsoft.XMLHTTP");  
  }  
 
 if(req){     
 
    req.open("POST",url, true);     
 
    req.onreadystatechange = complete;   
 
    req.send(null); 
 
  
 }
 /*分析返回的XML文档*/
function complete(){ 
var jztypelast='<%=jztypelast%>'; 
      if (req.readyState == 4){    
 
             if (req.status == 200) {     
                 var  b= req.responseText;
                 var  c=rtrim(b);
             if(c==jztypelast){
                 obj.style.color = 'BLACK';
                }else{
                  obj.style.color = 'RED';
             }
        } 
 }
}
}
function getGrayshebei(obj){  
var path='<%=path%>';
var shebeilast='<%=shebeilast%>';// changjianow
var shebeinow=document.form1.shebeinow.value;
var url = path+"/servlet/qxfj?action=send&shebeinow="+shebeinow+"&bb=2";
 if (window.XMLHttpRequest) { 
 
    req = new XMLHttpRequest();   

 }else if (window.ActiveXObject){    
 
 req = new ActiveXObject("Microsoft.XMLHTTP");  
  }  
 
 if(req){     
 
    req.open("POST",url, true);     
 
    req.onreadystatechange = complete;   
 
    req.send(null); 
 
  
 }
 /*分析返回的XML文档*/
function complete(){ 
var shebeilast='<%=shebeilast%>'; 
      if (req.readyState == 4){    
 
             if (req.status == 200) {     
                 var  b= req.responseText;
                 var  c=rtrim(b);
             if(c==shebeilast){
                 obj.style.color = 'BLACK';
                }else{
                  obj.style.color = 'RED';
             }
        } 
 }
}
}
function getBlack(obj){
   obj.style.color = 'BLACK';
}

function rtrim(s){
return s.replace( /\s*$/, "");
}
 
</script> 
<script language="javascript">
	var path = '<%=path%>';

		  function modifyjz2(id){
     		document.form1.action=path+"/servlet/cbzdfjxiazai2?id="+id+"&bzw=5";
     		 document.form1.submit();

    }
    function baocun(){
	var idd='<%=idcz%>';
	var qsdb=document.form1.newqsdb.value;
	   	document.form1.action=path+"/servlet/zdqxcxxg?action=zdqxsjsh&id="+idd+"&qsdb="+qsdb;
		document.form1.submit();
	}
    
    	$(function() {
			
			$("#baocun").click(function(){
			baocun();
				
			});
		});
		$("#xiazai").click(function() {
		var idcz = '<%=id%>';
		modifyjz2(idcz);
	});
</script>
 
 