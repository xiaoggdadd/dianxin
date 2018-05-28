<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean"%>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.noki.ammeterdegree.javabean.BargainBean"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.ptac.app.electricmanage.bargainbill.bean.*"%>
<%
	String path = request.getContextPath();
	String id = request.getParameter("id");
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
	String accountname = account.getAccountName();
%>
<html>
	<head>
		<title></title>
		<style>
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

#id1 {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
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
	line-height: 100%;
}

.selected_font1 {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
}

.selected_font3 {
	width: 130px;
	text-align: right;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
}
</style>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript" src="">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
<script src="<%=path%>/web/appJS/pageJs/someJs/timeCount.js">
</script>
		<script src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>


		<script language="javascript">
var path = '<%=path%>';
function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = ""
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

	} else {
		trObject.style.display = "none"
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
	}
}
function saveDegree() {
			var qsdbdl = document.form1.qsdbdl.value;
		var edhdl =  document.form1.edhdl.value;
		var zlfh = document.form1.zlfh.value;
		var jlfh = document.form1.jlfh.value;
		var scb = document.form1.scb.value;
		var property = document.form1.property.value;
		if(0== Number(edhdl)){
			alert("系统中的额定耗电量是空值,请先填写额定耗电量！！！");	
		}else{
				if(0==Number(qsdbdl)){
					alert("系统中的全省定标电量是空值，请先填写全省定标电量！！！");	
				}else{
					if(0==Number(zlfh)){
						alert("系统中的直流负荷是空值，请先填写直流负荷！！！");	
					}else{
						if(0==Number(jlfh)){
							alert("系统中的交流负荷是空值，请先填写交流负荷！！！");	
						}else{
							if(0==Number(scb)){
								alert("系统中的生产标是空值，请先填写生产标！！！");	
							}else{
									if(property==""||property=="null"){
										alert("系统中的站点属性为空值，请先填写站点属性！！！");	
									}else{
	if (checkNotnull(document.form1.money, "金额")
			&& checkNotnull(document.form1.startmonth, "起始年月")
			&& checkNotnull(document.form1.endmonth, "结束年月")
			&& checkNotnull(document.form1.htbianhao, "合同编号")
			&& checkNotSelected(document.form1.notetypeid, "票据类型")
			&& checkNotnull(document.form1.pjje, "票据金额")
			&& checkNotnull(document.form1.accountmonth, "报账月份")) {
		var moneyqk = document.form1.money.value.replace(/[ ]/g, "");
		var moneyqk1 = document.form1.pjje.value.replace(/[ ]/g, "");
		if ((isNaN(document.form1.money.value) == false) && moneyqk != "") {
			if ((isNaN(document.form1.pjje.value) == false) && moneyqk1 != "") {
				var moneyn = parseFloat(document.form1.money.value).toFixed(2);
				var dbili1n = parseFloat(document.form1.networkdf.value);
				var dbili2n = parseFloat(document.form1.marketdf.value);
				var dbili3n = parseFloat(document.form1.administrativedf.value);
				var dbili4n = parseFloat(document.form1.informatizationdf.value);
				var dbili5n = parseFloat(document.form1.builddf.value);
				var dbili6n = parseFloat(document.form1.dddf.value);//第六条专业线 代垫电费
				if (Math.abs(moneyn
						- parseFloat(
								dbili1n + dbili2n + dbili3n + dbili4n + dbili5n
										+ dbili6n).toFixed(2)) <= 0.02) {
					var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
					var ntt = document.form1.notetime.value;
					var kpt = document.form1.kptime.value;

					var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
					var sm = document.form1.startmonth.value;
					var em = document.form1.endmonth.value;

					var am = document.form1.accountmonth.value;
					if (reg1.exec(sm)) {
						if (reg1.exec(em)) {
							if (isDate(kpt) == true || kpt == "" || kpt == null) {
								if (isDate(ntt) == true || ntt == ""
										|| ntt == null) {
									if (reg1.exec(am)) {
										if (em >= sm) {

											if (confirm("您将要修改合同单信息！确认信息正确！")) {
												document.form1.action = path
														+ "/servlet/UpdateBargainBillServlet";
												document.form1.submit();
												showdiv("请稍等..............");
											}
										} else {
											alert("您输入的信息有误，结束年月必须大于等于起始年月！");
										}
									} else {
										alert("您输入的报账月份有误，请确认后重新输入！");
									}
								} else {
									alert("您输入的票据时间有误，请确认后重新输入！");
								}
							} else {
								alert("您输入的开票时间有误，请确认后重新输入！");
							}
						} else {
							alert("您输入的结束年月有误，请确认后重新输入！");
						}
					} else {
						alert("您输入的起始年月有误，请确认后重新输入！");
					}
				} else {
					alert("总金额与分摊金额与不符，请确认！");
				}
			} else {
				alert("您输入的信息有误，票据金额必须为数字！");
			}
		} else {
			alert("您输入的信息有误，金额必须为数字！");
		}
	}
	}
	}
	}
	}
	}
	}
}
function isDate(str) {
	var reg = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
	if (reg.test(str)) {
		return true;
	} else {

		return false;

	}
}function showIdList(){
        
               window.open('showBargainList.jsp','','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        function vCode(){
          var accCode = document.form1.workSn.value;
          if(accCode==""){
           	alert("不能为空！")
                   return
          }
               window.open('accountCode.jsp?accountId=0&accountCode='+accCode,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }

        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！")
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
         function getHaodianliang(){
        
          // alert("-===");
        	// var bl=document.form1.mpower.value;
        	// if(bl==null||bl=="")bl="1"
        	var dbili1=document.form1.dbili1.value;
        	var dbili2=document.form1.dbili2.value;
        	var dbili3=document.form1.dbili3.value;
        	var dbili4=document.form1.dbili4.value;
        	var dbili5=document.form1.dbili5.value;
        	var dbili6=document.form1.dbili6.value;
        	var money= document.form1.money.value;
        	if(isNaN(document.form1.money.value)==false){}
        	 //生产
        	 if((dbili1 !="")&&isNaN(document.form1.dbili1.value)==false){
        		document.form1.networkdf.value=((parseFloat(dbili1)/100)*parseFloat(money)).toFixed(2);
 			}else{
 				document.form1.networkdf.value="0.00";
 			}
        	  //销售
        	if((dbili2 !="")&&isNaN(document.form1.dbili2.value)==false){
        	 	document.form1.marketdf.value=((parseFloat(dbili2)/100)*parseFloat(money)).toFixed(2);	
        	}else{
        	 	document.form1.marketdf.value="0.00";
 			}
        	 // ADMINISTRATIVEDF
        	 if((dbili3 !="")&&isNaN(document.form1.dbili3.value)==false){
        	 	document.form1.administrativedf.value=((parseFloat(dbili3)/100)*parseFloat(money)).toFixed(2);
        	 }else{
        	 	document.form1.administrativedf.value="0.00";
 			}
        	  
        	 // INFORMATIZATIONDF
        	 if((dbili4 !="")&&isNaN(document.form1.dbili4.value)==false){	
        	 	document.form1.informatizationdf.value=((parseFloat(dbili4)/100)*parseFloat(money)).toFixed(2);
        	 }else{
        	 	document.form1.informatizationdf.value="0.00";
 			 }
        	 // BUILDDF
        	 if((dbili5 !="")&&isNaN(document.form1.dbili5.value)==false){	
        	 	document.form1.builddf.value=((parseFloat(dbili5)/100)*parseFloat(money)).toFixed(2); 
        	 }else{
        	 	document.form1.builddf.value="0.00";
 			 }  
 			 //代垫电费 第六条   
 			 if((dbili6 !="")&&isNaN(document.form1.dbili6.value)==false){	
        	 	document.form1.dddf.value=((parseFloat(dbili6)/100)*parseFloat(money)).toFixed(2); 
        	 }else{
        	 	document.form1.dddf.value="0.00";
 			 }  
 			   	        	 
         } 
        
	$(function(){
        $("#saveBtn").click(function(){
		   saveDegree();
		});
        $("#resetBtn").click(function() {	
        	$("#form")[0].reset();
        });
         $("#liulan").click(function(){
		   showIdList();
		});
         
		$("#cancelBtn").click(function(){
			window.history.back();
		});
        
	});
</script>
	</head>
	<jsp:useBean id="roleBean" scope="page"
		class="com.noki.mobi.sys.javabean.RoleBean">
	</jsp:useBean>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST" id="form">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="form_label">
				<tr>
					<td width="10"></td>
					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="2"></td>
							</tr>

						</table>
					</td>
					<td width="12"></td>
				</tr>

				<tr>
					<td width="10" height="500">
						&nbsp;
					</td>
					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="form_label">
							<tr>
								<td colspan="3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="form_label">
										<tr>
											<td colspan="4"></td>
										</tr>
										<tr>
											<td colspan="4" width="50">
												<div style="width: 700px;">
													<img alt="" src="<%=path%>/images/btt.bmp" width="100%"
														height="100%" />
													<span
														style="font-size: 14px; font-weight: bold; position: absolute; left: 25px; top: 15px; color: black">修改合同单</span>
													<input type="hidden" name="accountname"
														value="<%=accountname%>">
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="form_label">
										<tr>
											<td width="4"></td>
											<td>
												<table width="100%" border="0" align="right" cellpadding="1"
													cellspacing="1" class="form_label">
													<tr>
														<td height="49" bgcolor="#FFFFFF">
															<table width="100%" border="0" cellspacing="1"
																cellpadding="1" class="form_label">

																<%
																	String money1 = "", networkdf1 = "", administrativedf1 = "", marketdf1 = "", informatizationdf1 = "", builddf1 = "", dddf1 = "";
																	BargainBean bean = new BargainBean();

																	bean = bean.getBargainDan(id);
																	String beilv = bean.getBeilv();
																	   if("".equals(beilv)||"null".equals(beilv)||beilv==null||"0".equals(beilv)){
																		   beilv="1";
																		}
																	    String shicode = bean.getShicode();
																	    String property = bean.getProperty();
																	    String scb = bean.getScb();
																	    String zlfh = bean.getZlfh();
																	    String jlfh = bean.getJlfh();
																	    String dfzflxcode = bean.getDfzflx();
																	    String stationtypecode = bean.getStationtypecode();
																	    String gdfscode = bean.getGdfscode();
																	    String qsdbdl = bean.getQsdbdl();
																	    String edhdl = bean.getEdhdl();
																	    if(null==shicode){
																	    	shicode = "";
																	    }
																	    if(null==property){
																	    	property = "";
																	    }
																	    if(null==scb){
																	    	scb = "";
																	    }
																	    if(null==zlfh){
																	    	zlfh = "";
																	    }
																	    if(null==jlfh){
																	    	jlfh = "";
																	    }
																	    if(null==dfzflxcode){
																	    	dfzflxcode = "";
																	    }
																	    if(null==stationtypecode){
																	    	stationtypecode = "";
																	    }
																	    if(null==gdfscode){
																	    	gdfscode = "";
																	    }
																	    if(null==qsdbdl){
																	    	qsdbdl = "";
																	    }
																	    if(null==edhdl){
																	    	edhdl = "";
																	    }
																	
																		String str_csdb = bean.getCsdb();
																		String str_cCity = bean.getDedhdl();
																	
															        request.setAttribute("csdb",str_csdb);
															        request.setAttribute("cCity",str_cCity);
																	
																	String kpsj = bean.getKptime();

																	double ddd = bean.getPjje();
																	if (kpsj == null || "".equals(kpsj) || "null".equals(kpsj)) {
																		kpsj = "";
																	}
																	//格式化单价
																	String dianfei = bean.getDanjia();
																	if (dianfei == null || dianfei.equals("") || dianfei.equals("null")
																			|| dianfei.equals("o")) {
																		dianfei = "0.0000";
																	}
																	DecimalFormat mat1 = new DecimalFormat("0.0000");
																	dianfei = mat1.format(Double.parseDouble(dianfei));
																	//格式化金额、生产、办公、营业、信息化支撑、建设投资、代垫电费
																	DecimalFormat mat = new DecimalFormat("0.00");
																	money1 = bean.getMoney();
																	networkdf1 = bean.getNetworkdf();
																	administrativedf1 = bean.getAdministrativedf();
																	marketdf1 = bean.getMarketdf();
																	informatizationdf1 = bean.getInformatizationdf();
																	builddf1 = bean.getBuilddf();
																	dddf1 = bean.getDddf();
																	String htbh1 = bean.getHtbianhao();
																	if (money1 == null || "".equals(money1) || "null".equals(money1)
																			|| "o".equals(money1)) {
																		money1 = "0.00";

																	}
																	if (ddd == 0 || ddd == 0.0) {
																		ddd = Double.parseDouble(money1);
																	}
																	money1 = mat.format(Double.parseDouble(money1));
																	if (networkdf1 == null || "".equals(networkdf1)
																			|| "null".equals(networkdf1) || "o".equals(networkdf1)) {
																		networkdf1 = "0.00";
																	}

																	networkdf1 = mat.format(Double.parseDouble(networkdf1));
																	if (administrativedf1 == null || "".equals(administrativedf1)
																			|| "null".equals(administrativedf1)
																			|| "o".equals(administrativedf1)) {
																		administrativedf1 = "0.00";
																	}
																	administrativedf1 = mat.format(Double
																			.parseDouble(administrativedf1));
																	if (marketdf1 == null || "".equals(marketdf1)
																			|| "null".equals(marketdf1) || "o".equals(marketdf1)) {
																		marketdf1 = "0.00";
																	}
																	marketdf1 = mat.format(Double.parseDouble(marketdf1));
																	if (informatizationdf1 == null || "".equals(informatizationdf1)
																			|| "null".equals(informatizationdf1)
																			|| "o".equals(informatizationdf1)) {
																		informatizationdf1 = "0.00";
																	}
																	informatizationdf1 = mat.format(Double
																			.parseDouble(informatizationdf1));
																	if (builddf1 == null || "".equals(builddf1)
																			|| "null".equals(builddf1) || "o".equals(builddf1)) {
																		builddf1 = "0.00";
																	}
																	builddf1 = mat.format(Double.parseDouble(builddf1));

																	//新增第六条线 代垫电费
																	if (dddf1 == null || "".equals(dddf1) || "null".equals(dddf1)
																			|| "o".equals(dddf1) || " ".equals(dddf1)) {
																		dddf1 = "0.00";
																	}
																	dddf1 = mat.format(Double.parseDouble(dddf1));

																	String dbid = bean.getPrepayment_ammeterid();
																	AmmeterDegreeFormBean bean1 = new AmmeterDegreeFormBean();

																	//获取额定耗电量、全省定标电量、单价
																	//BargainBillCountBean bbc = new BargainBillCountBean();
																	//bbc = bean1.getCount(bean.getPrepayment_ammeterid());
																	//request.setAttribute("couBean", bbc);
																	
																	//计算电量
																	String danjia = bean.getDanjia();
																	double dj = Double.parseDouble(danjia);
																	double f1_yjs =0.0;
																	if(dj!=0){
																		double je = Double.parseDouble(money1);
																		double dianl = je/dj;
																		BigDecimal bg_yjs = new BigDecimal(dianl);
																        f1_yjs = bg_yjs.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
																        
																	}
																	request.setAttribute("dl",f1_yjs);

																	ArrayList listt = new ArrayList();
																	listt = bean1.getStationMhchexkt(dbid);
																	String shuoshuzhuanye = "";
																	String dbili = "", dbili1 = "", dbili2 = "", dbili3 = "", dbili4 = "", dbili5 = "", dbili6 = "";
																	if (listt != null) {
																		int fycount = ((Integer) listt.get(0)).intValue();
																		for (int k = fycount; k < listt.size() - 1; k += fycount) {
																			shuoshuzhuanye = (String) listt.get(k
																					+ listt.indexOf("SHUOSHUZHUANYE"));
																			shuoshuzhuanye = shuoshuzhuanye != null ? shuoshuzhuanye
																					: "";
																			dbili = (String) listt.get(k + listt.indexOf("DBILI"));
																			dbili = dbili != null ? dbili : "0";
																			if (shuoshuzhuanye.equals("zylx01")) {
																				dbili1 = dbili;
																			}
																			if (shuoshuzhuanye.equals("zylx02")) {
																				dbili2 = dbili;
																			}
																			if (shuoshuzhuanye.equals("zylx03")) {

																				dbili3 = dbili;
																			}
																			if (shuoshuzhuanye.equals("zylx04")) {
																				dbili4 = dbili;

																			}
																			if (shuoshuzhuanye.equals("zylx05")) {
																				dbili5 = dbili;

																			}
																			if (shuoshuzhuanye.equals("zylx06")) {
																				dbili6 = dbili;
																			}
																		}
																	}
																%>
																<tr>
																	<td>
																		&nbsp;
																	</td>
																</tr>
																<tr bgcolor="F9F9F9">
																	<td height="19" colspan="4">
																		<img src="../../images/v.gif" width="15" height="15" />
																		基本信息
																	</td>
																</tr>
																<tr>
																	<td>
																		&nbsp;
																	</td>
																</tr>
																<tr>
																	<td height="19" bgcolor="#DDDDDD" width="15%">
																		<div>
																			站点名称：
																		</div>
																	</td>
																	<td width="35%">
																		<input type="text" name="stationname"
																			value="<%=bean.getJzname()%>" class="selected_font1"
																			readonly=true />
																	</td>
																	<td height="19" bgcolor="#DDDDDD" width="15%">
																		<div>
																			站点编号：
																		</div>
																	</td>
																	<td width="35%">
																		<input type="text" name=zdbh
																			value="<%=bean.getZdcode()%>" class="selected_font1"
																			readonly=true />
																	</td>
																	<input type="hidden" name="zdid"
																		value="<%=bean.getStationid()%>">
																</tr>
																<tr>
																	<td height="19" bgcolor="#DDDDDD" width="15%">
																		<div>
																			电表ID：
																		</div>
																	</td>
																	<td width="35%">
																		<input type="text" name="ammeteridFk"
																			value="<%=bean.getPrepayment_ammeterid()%>"
																			class="selected_font1" readonly=true />
																			<span style="color: #FF0000; font-weight: bold">&nbsp;*</span>

																	</td>
																	<td height="8%" bgcolor="#DDDDDD" width="15%">
																		<div>
																			电表名称：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="initialdate"
																			value="<%=bean.getDbname()%>" class="selected_font"
																			maxlength="11" />
																	</td>
																</tr>
																<tr>
																	<td height="19" bgcolor="#DDDDDD" width="15%">
																		<div>
																			金额：
																		</div>
																	</td>
																	<td width="35%">
																		<input type="text" name="money"
																		onblur="getdl(this.value,'danjia','dl');getShengDB('dl','csdb','csb','startmonth','endmonth','qsdbdl','edhdl')"
																			onChange="getHaodianliang()" class="selected_font3"
																			value="<%=money1%>" />
																		<span style="color: #FF0000; font-weight: bold">&nbsp;*</span>
																	</td>
																	<input type="hidden" name="id"
																		value="<%=bean.getId()%>" class="form">
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			备注：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="memo"
																			value="<%=bean.getMemo()%>" class="selected_font" />
																	</td>
																</tr>
																<tr>
																	<td height="19" bgcolor="#DDDDDD" width="15%">
																		<div>
																			全省定标电量：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="qsdbdl" id="qsdbdl"
																			readonly="readonly" value="<%=qsdbdl %>"
																			class="selected_font1" />
																	</td>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			额定耗电量：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="edhdl" id="edhdl"
																			readonly="readonly" value="<%=edhdl %>"
																			class="selected_font1" />
																		  <input type="hidden" name="beilv"  value="<%=beilv%>">
																	      <input type="hidden" name="property"  value="<%=property%>">
																	      <input type="hidden" name="shicode"  value="<%=shicode%>">
																	      <input type="hidden" name="zlfh"  value="<%=zlfh%>">
																	      <input type="hidden" name="jlfh"  value="<%=jlfh%>">
																	      <input type="hidden" name="scb"  value="<%=scb%>">
																	      <input type="hidden" name="stationtypecode"  value="<%=stationtypecode%>">
																	      <input type="hidden" name="gdfscode"  value="<%=gdfscode%>">
																	      <input type="hidden" name="dfzflxcode"  value="<%=dfzflxcode%>">
																	</td>
																</tr>
																<tr>
																	<td height="8%" bgcolor="#DDDDDD" width="15%">
																		<div>
																			电量：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="dl" id="dl" onpropertychange="getCsdb()"
																			readonly="readonly" value="${dl}" class="selected_font1"
																			maxlength="11" />
																	</td>
																	<td height="19" bgcolor="#DDDDDD" width="15%">
																		合同编号
																	</td>
																	<td>
																		<input type="text" id="htbianhao" name="htbianhao"
																			value="<%=htbh1%>" class="selected_font">
																		<span style="color: #FF0000; font-weight: bold">&nbsp;*</span>
																	</td>
																</tr>
																<tr>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			起始年月：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="startmonth" id= "startmonth"
																			value="<%=bean.getStartmonth()%>"
																			onchange="getShengDB('dl','csdb','csb','startmonth','endmonth','qsdbdl','edhdl')"
																			onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
																			onpropertychange="getCsdb()"
																			class="selected_font" />
																		<span style="color: #FF0000; font-weight: bold">&nbsp;*</span>
																	</td>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			结束年月：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="endmonth" id = "endmonth"
																			value="<%=bean.getEndmonth()%>"
																			onchange="getShengDB('dl','csdb','csb','startmonth','endmonth','qsdbdl','edhdl')"
																			onpropertychange="getCsdb()"
																			onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
																			class="selected_font" />
																		<span style="color: #FF0000; font-weight: bold">&nbsp;*</span>
																	</td>
																</tr>
																<tr>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			票据时间：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="notetime"
																			value="<%=bean.getNotetime()%>"
																			onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
																			class="selected_font" maxlength="11" />
																	</td>
																	<td height="19" bgcolor="#DDDDDD" width="15%">
																		<div>
																			开票时间：
																		</div>
																	</td>

																	<td>
																		<input type="text" name="kptime" value="<%=kpsj%>"
																			onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
																			class="selected_font" />
																	</td>
																</tr>
																<tr>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			电费支付类型：
																		</div>
																	</td>
																	<td>
																		<select name="bargain" class="selected_font">
																			<option value="0">
																				请选择
																			</option>
																			<%
																				ArrayList bartypelist = new ArrayList();
																				bartypelist = commBean.getSelctOptions();
																				if (bartypelist != null) {
																					String sfid = "", sfnm = "";
																					int scount = ((Integer) bartypelist.get(0)).intValue();
																					for (int i = scount; i < bartypelist.size() - 1; i += scount) {
																						sfid = (String) bartypelist.get(i
																								+ bartypelist.indexOf("CODE"));
																						sfnm = (String) bartypelist.get(i
																								+ bartypelist.indexOf("NAME"));
																						if ((bean.getDfzflx()).equals(sfid)) {
																			%>
																			<option selected="selecte" value="<%=sfid%>"><%=sfnm%></option>
																			<%
																				} else {
																			%>
																			<option value="<%=sfid%>"><%=sfnm%></option>
																			<%
																				}
																					}
																				}
																			%>
																		</select>

																	</td>

																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			报账月份：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="accountmonth"
																			value="<%=bean.getAccountmonth()%>"
																			onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
																			class="selected_font" />
																		<span style="color: #FF0000; font-weight: bold">&nbsp;*</span>
																	</td>
																</tr>

																<tr>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			票据类型：
																		</div>
																	</td>
																	<td>
																		<select name="notetypeid" class="selected_font">
																			<option value="0">
																				请选择
																			</option>
																			<%
																				ArrayList feetypelist = new ArrayList();
																				feetypelist = commBean.getNoteType();
																				if (feetypelist != null) {
																					String sfid = "", sfnm = "";
																					int scount = ((Integer) feetypelist.get(0)).intValue();
																					for (int i = scount; i < feetypelist.size() - 1; i += scount) {
																						sfid = (String) feetypelist.get(i
																								+ feetypelist.indexOf("CODE"));
																						sfnm = (String) feetypelist.get(i
																								+ feetypelist.indexOf("NAME"));
																						if ((bean.getNotetypeid()).equals(sfid)) {
																			%>
																			<option selected="selected" value="<%=sfid%>"><%=sfnm%></option>
																			<%
																				} else {
																			%>
																			<option value="<%=sfid%>"><%=sfnm%></option>
																			<%
																				}
																					}
																				}
																			%>
																		</select>
																		<span style="color: #FF0000; font-weight: bold">&nbsp;*</span>
																		<td height="19" bgcolor="#DDDDDD" width="15%">
																			<div>
																				票据金额：
																			</div>
																		</td>
																		<td>
																			<input type="text" id="pjje" name="pjje"
																				value="<%=ddd%>" class="selected_font3">
																			<span style="color: #FF0000; font-weight: bold">&nbsp;&nbsp;*</span>
																		</td>
																	</td>
																</tr>
																<tr>
																	<td height="8%" bgcolor="#DDDDDD" width="15%">
																		<div>
																			单价：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="danjia" id="danjia"
																			readonly="readonly" value="<%=dianfei %>"
																			class="selected_font3" maxlength="11" />
																	</td>
																</tr>
																<tr>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			超省定标：
																		</div>
																	</td>
																	<td>
																	<input type="text"  id="csdbdiv"  value="超省标${csdb}%"
																			readonly="readonly" class="selected_font" />
																		<input type="hidden" name="csdb" id="csdb"  value="超省标${csdb}%"
																			readonly="readonly" class="selected_font" />
																	</td>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			超市标：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="csb" id="csb" value="超市标${cCity}%"
																			readonly="readonly" class="selected_font" />
																			<input type="hidden" id="bzw" name="bzw" value="1" >
																	</td>
																</tr>
																<tr>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			周期：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="jszq" value="<%=bean.getJszq() %>" id = "jszq"
																			readonly="readonly"
																			class="selected_font" />
																	</td>
																</tr>
																
																
																
																<tr bgcolor="F9F9F9">
																	<td height="19" colspan="4">
																		<img src="../../images/v.gif" width="15" height="15" />
																		分摊信息
																	</td>
																</tr>
																<tr>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			生产：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="networkdf"
																			value="<%=networkdf1%>" class="selected_font3" />
																	</td>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			办公：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="administrativedf"
																			value="<%=administrativedf1%>" class="selected_font3" />
																	</td>
																</tr>
																<tr>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			营业：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="marketdf"
																			value="<%=marketdf1%>" class="selected_font3" />
																	</td>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			信息化支撑：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="informatizationdf"
																			value="<%=informatizationdf1%>"
																			class="selected_font3" />
																	</td>
																</tr>
																<tr>
																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			建设投资：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="builddf"
																			value="<%=builddf1%>" class="selected_font3" />
																	</td>

																	<td height="19" bgcolor="#DDDDDD">
																		<div>
																			代垫电费：
																		</div>
																	</td>
																	<td>
																		<input type="text" name="dddf" value="<%=dddf1%>"
																			class="selected_font3" />
																	</td>

																	<td>
																		<input type="hidden" name="dbili1" value="<%=dbili1%>">
																	</td>
																	<td>
																		<input type="hidden" name="dbili2" value="<%=dbili2%>">
																	</td>
																	<td>
																		<input type="hidden" name="dbili3" value="<%=dbili3%>">
																	</td>
																	<td>
																		<input type="hidden" name="dbili4" value="<%=dbili4%>">
																	</td>
																	<td>
																		<input type="hidden" name="dbili5" value="<%=dbili5%>">
																	</td>
																	<td>
																		<input type="hidden" name="dbili6" value="<%=dbili6%>">
																	</td>
																	<td>
																		<input type="hidden" name="danjia"
																			value="<%=dianfei%>">
																	</td>
																</tr>
																<tr>
																	<td colspan=4">
																		<div id="cancelBtn"
																			style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 250px; top: 30px;">
																			<img src="<%=path%>/images/quxiao.png" width="100%"
																				height="100%">
																			<span
																				style="font-size: 12px; position: absolute; left: 27px; top: 3px; color: white">返回</span>
																		</div>
																		<div id="resetBtn"
																			style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 260px; top: 30px;">
																			<img src="<%=path%>/images/2chongzhi.png"
																				width="100%" height="100%">
																			<span
																				style="font-size: 12px; position: absolute; left: 27px; top: 3px; color: white">重置</span>
																		</div>
																		<div id="saveBtn"
																			style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 270px; top: 30px;">
																			<img src="<%=path%>/images/baocun.png" width="100%"
																				height="100%">
																			<span
																				style="font-size: 12px; position: absolute; left: 27px; top: 3px; color: white">保存</span>
																		</div>


																	</td>
																</tr>


															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<br />
					</td>
				</tr>
			</table>
		</form>

	</body>
	<script type="text/javascript">
	var XMLHttpReq;
function createXMLHttpRequest1() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}
function sendRequest(url) {
	createXMLHttpRequest1();
	XMLHttpReq.open("POST", url, true);
	XMLHttpReq.onreadystatechange = processResponse_csdb;//指定响应函数
	XMLHttpReq.send(null);
}

	function processResponse_csdb() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseText;
			document.getElementById("csdbdiv").value="超省标"+res;
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function getCsdb() {
	var qsdbdl = document.form1.qsdbdl.value;
	var stationtype = document.form1.stationtypecode.value;
	var zlfh = document.form1.zlfh.value;
	var jlfh = document.form1.jlfh.value;
	var property = document.form1.property.value;
	var edhdl = document.form1.edhdl.value;
	var scb = document.form1.scb.value;
	var dbid = document.form1.ammeteridFk.value;
	var shicode = document.form1.shicode.value;
	var startmonth = document.form1.startmonth.value;
	var endmonth = document.form1.endmonth.value;
	var dl = document.form1.dl.value;
	var kong1 = document.form1.startmonth.value.replace(/[ ]/g,"");
	var kong2 = document.form1.endmonth.value.replace(/[ ]/g,"");
	if(parseFloat(dl)==dl
			&&isNaN(dl)==false
			&& kong1!="" && kong2!="" 
			&& endmonth>=startmonth
			&& Number(zlfh)!=0
			&& Number(jlfh)!=0
			&& Number(edhdl)!=0
			&& Number(qsdbdl)!=0
			&& Number(scb)!=0
			&& property!="" && property!="null"){
	sendRequest(path
+"/servlet/AddBargainServlet?action=getCsdb&qsdbdl="+qsdbdl+"&stationtype="+stationtype+"&zlfh="
+zlfh+"&jlfh="+jlfh+"&property="+property+"&edhdl="+edhdl
+"&scb="+scb+"&ammeteridFk="+dbid+"&shicode="+shicode+"&startmonth="+startmonth
+"&endmonth="+endmonth+"&dl="+dl);
}else{
	document.getElementById("csdbdiv").value="";;
}
}
	</script>
</html>


