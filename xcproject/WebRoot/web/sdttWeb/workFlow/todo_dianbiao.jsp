<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.Double"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.jizhan.JiZhanBean"%>
<%@ page import="com.noki.zwhd.model.DwBean"%>
<%@ page import="com.noki.zwhd.manage.SystemManage"%>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.*"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.ptac.app.accounting.AccountingDao"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String accountname = account.getAccountName();
	String sheng = (String) session.getAttribute("accountSheng");
	String loginId = account.getAccountId();
	String shengId = (String) session.getAttribute("accountSheng");
	String loginName = (String) session.getAttribute("loginName");
	String id = request.getParameter("id");
	session.setAttribute("dbid", id);
	DataBase db = new DataBase();
	
	//流程信息
	String nextaction = "", actionname = "", nextactionId = "", workFlowId = "", currentid = "", flowId = "", applyUserId = "", lastaction = "", lastactionname = "", bohui = "", tasktype = "", auditorid = "", bo = "";
	String steporderno="", nextsteporderno="", laststeporderno="";
	String ACCTIME = "", ACCNAME = "", taskid = "",dblc_type="",ACCTIMEStr="";
	StringBuffer sql2 = new StringBuffer();
	ResultSet rs2 = null;
	sql2
			.append("select w.* ,ai.action_name as actionname,ai2.action_name as nextactionname,ai3.action_name as lastactionname,f.flow_name as flowname,"
					+ "s.step_order_no as steporderno,s2.step_order_no as nextsteporderno,s3.step_order_no as laststeporderno,"
					+ " (select  a.name from account a where a.accountid =w.APPLYUSER_ID) as ACCNAME from s_workflow w "
					+ "left join S_STEP_INFO s on w.current_no = s.step_id "
					+ "left join S_ACTION_INFO ai on ai.action_id =s.action_id "
					+ "left join S_STEP_INFO s2 on w.NEXT_NO = s2.step_id "
					+ "left join S_ACTION_INFO ai2 on ai2.action_id =s2.action_id "
					+ "left join S_STEP_INFO s3 on w.LAST_NO = s3.step_id "
					+ "left join S_ACTION_INFO ai3 on ai3.action_id =s3.action_id "
					+ "left join S_FLOW_INFO f on f.flow_id =s.flow_id "
					+ "where w.ID =" + id);
	System.out.println(sql2);
	db.connectDb();
	rs2 = db.queryAll(sql2.toString());
	while (rs2.next()) {
		taskid = rs2.getString("TASK_ID") == null ? "" : rs2
				.getString("TASK_ID");
		actionname = rs2.getString("actionname") == null ? "" : rs2
				.getString("actionname");
		nextaction = rs2.getString("nextactionname") == null ? "流程结束"
				: rs2.getString("nextactionname");
		lastactionname = rs2.getString("lastactionname") == null ? "发起人"
				: rs2.getString("lastactionname");
		steporderno = rs2.getString("STEPORDERNO") == null ? "" : rs2
				.getString("STEPORDERNO");
		nextsteporderno = rs2.getString("NEXTSTEPORDERNO") == null ? ""
				: rs2.getString("NEXTSTEPORDERNO");
		laststeporderno = rs2.getString("LASTSTEPORDERNO") == null ? ""
				: rs2.getString("LASTSTEPORDERNO");
		nextactionId = rs2.getString("NEXT_NO") == null ? "" : rs2
				.getString("NEXT_NO");
		currentid = rs2.getString("CURRENT_NO") == null ? "" : rs2
				.getString("CURRENT_NO");
		flowId = rs2.getString("FLOW_ID") == null ? "" : rs2
				.getString("FLOW_ID");
		applyUserId = rs2.getString("APPLYUSER_ID") == null ? "" : rs2
				.getString("APPLYUSER_ID");
		lastaction = rs2.getString("LAST_NO") == null ? "" : rs2
				.getString("LAST_NO");
		bohui = rs2.getString("BOHUI") == null ? "" : rs2
				.getString("BOHUI");
		tasktype = rs2.getString("TASK_TYPE") == null ? "" : rs2
				.getString("TASK_TYPE");
		auditorid = rs2.getString("AUDITORID") == null ? "" : rs2
				.getString("AUDITORID");
		if (applyUserId.equals(auditorid)) {
			bo = "1";
		}
		ACCTIME = rs2.getString("APPLYTIME") == null ? "" : rs2
				.getString("APPLYTIME");
		ACCNAME = rs2.getString("ACCNAME") == null ? "" : rs2
				.getString("ACCNAME");
		dblc_type = rs2.getString("dblc_type") == null ? "" : rs2
				.getString("dblc_type");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		java.util.Date ACCTIMEDate = sdf.parse(ACCTIME);
		ACCTIMEStr = sdf.format(ACCTIMEDate);
	}
	System.out.println("===" + nextaction);
	String bohuijd = "";

	//String zdid = request.getParameter("zdid");
	String bz = request.getParameter("bz");
	SiteModifyBean form = new SiteModifyBean();

	String a = "", dbbm = "", shi = "0", xian = "0", xiaoqu = "0", zdmc = "0", dbmc = "", cssysj = "", beilv = "", csds = "", dfzf = "0";
	String dbId = "", zdid = "";
	String jzname = "", jztype = "", sszy = "", dbyt = "0", zdlx = "", cssytime = "", dbxh = "", memo = "", szdq = "";
	String maxds = "", dwjslx = "0", isglf = "", glbm = "", zrr = "", bzr = "", ydsx = "0", ydlx = "0", jffs = "0", jfzq = "0", jldw = "0", yhbh = "";
	String dbqyzt = "0", bgje = "", mtllhd = "", danjia = "", gysmc = "", gysbm = "", skfmc = "", yhzh = "", ssyh = "", khyh = "", zhsss = "", zhssshi = "", fplx = "0";
	String zzsl = "", dbch = "", dbscbs = "";
	String is_cont = "0", cont_type = "0", zndb = "0", isdblx = "0";
	String cbzx = "", cbzxbm = "", production_prop = "", bur_stand_propertion = "", cont_id = "", electric_supply_way = "", total_electricity = "", zhangqi = "", zhz = "";
	String gszt="",ssf="",Tssf="",FTBL="",SFYZ="";
	String sql = "select d.* from dianbiao d where id=" + taskid + "";
	System.out.println(sql.toString());

	ResultSet rs = null;
	db.connectDb();
	rs = db.queryAll(sql.toString());
	while (rs.next()) {
			dbmc = rs.getString("dbname") == null ? "" : rs
					.getString("dbname");
			cssysj = rs.getString("cssytime") == null ? "" : rs
					.getString("cssytime");
			System.out.println(cssysj.length());
			if (cssysj.length() != 0) {
				a = cssysj.substring(0, 10);
			}
			System.out.println(cssysj);
			beilv = rs.getString("beilv") == null ? "" : rs
					.getString("beilv");
			csds = rs.getString("csds") == null ? "" : rs
					.getString("csds");
			dfzf = rs.getString("dfzflx");
			dbqyzt = rs.getString("dbqyzt");
			jldw = rs.getString("jldw");
			dbyt = rs.getString("dbyt");
			dbbm = rs.getString("dbbm") == null ? "" : rs
					.getString("dbbm");
			jztype = rs.getString("jztype") == null ? "" : rs
					.getString("jztype");
			sszy = rs.getString("sszy") == null ? "" : rs
					.getString("sszy");

			cssytime = rs.getString("cssytime") == null ? "" : rs
					.getString("cssytime");
			dbxh = rs.getString("dbxh") == null ? "" : rs
					.getString("dbxh");
			memo = rs.getString("memo") == null ? "" : rs
					.getString("memo");

			maxds = rs.getString("maxds") == null ? "" : rs
					.getString("maxds");
			dwjslx = rs.getString("dwjslx");
			isglf = rs.getString("isglf") == null ? "" : rs
					.getString("isglf");
			glbm = rs.getString("glbm") == null ? "" : rs
					.getString("glbm");
			zrr = rs.getString("zrr") == null ? "" : rs
					.getString("zrr");
			bzr = rs.getString("bzr") == null ? "" : rs
					.getString("bzr");
			ydsx = rs.getString("ydsx") == null ? "" : rs
					.getString("ydsx");
			ydlx = rs.getString("ydlx");
			jffs = rs.getString("jffs") == null ? "" : rs
					.getString("jffs");
			jfzq = rs.getString("jfzq");
			jldw = rs.getString("jldw") == null ? "" : rs
					.getString("jldw");
			yhbh = rs.getString("yhbh") == null ? "" : rs
					.getString("yhbh");
			bgje = rs.getString("bgje") == null ? "" : rs
					.getString("bgje");
			mtllhd = rs.getString("mtllhd") == null ? "" : rs
					.getString("mtllhd");
			danjia = rs.getString("danjia") == null ? "" : rs
					.getString("danjia");
			gysmc = rs.getString("gysmc") == null ? "" : rs
					.getString("gysmc");
			gysbm = rs.getString("gysbm") == null ? "" : rs
					.getString("gysbm");
			skfmc = rs.getString("skfmc") == null ? "" : rs
					.getString("skfmc");
			yhzh = rs.getString("yhzh") == null ? "" : rs
					.getString("yhzh");
			ssyh = rs.getString("ssyh") == null ? "" : rs
					.getString("ssyh");
			khyh = rs.getString("khyh") == null ? "" : rs
					.getString("khyh");
			zhsss = rs.getString("zhsss") == null ? "" : rs
					.getString("zhsss");
			zhssshi = rs.getString("zhssshi") == null ? "" : rs
					.getString("zhssshi");

			fplx = rs.getString("fplx");
			zzsl = rs.getString("zzsl") == null ? "" : rs
					.getString("zzsl");
			if (zzsl != "" && zzsl.length() > 1) {
				if (zzsl.subSequence(0, 1).equals(".")) {
					zzsl = "0" + zzsl;
				}
			}

			dbch = rs.getString("dbch") == null ? "" : rs
					.getString("dbch");
			dbscbs = rs.getString("dbscbs") == null ? "" : rs
					.getString("dbscbs");

			is_cont = rs.getString("is_cont");
			cont_type = rs.getString("cont_type");
			zndb = rs.getString("zndb");
			isdblx = rs.getString("isdblx");
			cbzx = rs.getString("cbzx") == null ? "" : rs
					.getString("cbzx");
			cbzxbm = rs.getString("cbzxbm") == null ? "" : rs
					.getString("cbzxbm");
			production_prop = rs.getString("production_prop") == null ? ""
					: rs.getString("production_prop");
			bur_stand_propertion = rs.getString("bur_stand_propertion") == null ? ""
					: rs.getString("bur_stand_propertion");
			cont_id = rs.getString("cont_id") == null ? "" : rs
					.getString("cont_id");
			electric_supply_way = rs.getString("electric_supply_way") == null ? ""
					: rs.getString("electric_supply_way");
			total_electricity = rs.getString("total_electricity") == null ? ""
					: rs.getString("total_electricity");
			zhangqi = rs.getString("zq") == null ? "" : rs
					.getString("zq");
			zhz = rs.getString("zhz") == null ? "" : rs
					.getString("zhz");
			gszt = rs.getString("gszt")==null ? "" : rs.getString("gszt");
			zdid = rs.getString("zdid")==null ? "" : rs.getString("zdid");
			FTBL = rs.getString("FTBL")==null ? "" : rs.getString("FTBL");
			SFYZ = rs.getString("SFYZ")==null ? "" : rs.getString("SFYZ");
			
			ssf = rs.getString("ssf")==null ? "" : rs.getString("ssf");
			System.out.println("----------dddd----"+ssf);
			if(ssf.equals("1")){
				Tssf="非电信";
			}else if(ssf.equals("2")){
				Tssf="电信";
			}
		}
		form = form.getJizhan(zdid, "");
		shi = form.getShi();
		xian = form.getXian();
		xiaoqu = form.getXiaoqu();
		zdmc = form.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
		</script>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
		</script>
<!--[if gt IE 8]><!-->
<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"></script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
<!--<![endif]-->
<!--[if lte IE 8]>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/console.js"></script>
<![endif]-->
	
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
		</script>

		<title>电表审批</title>
		<script type="text/javascript">
var path = '<%=path%>';

function tongyi() {
	var shenhe = $('input:radio[name="shenhe"]:checked').val();
	if (shenhe == 0) {

		$("#tongyiTr").attr("style", "display:display;");
		$("#bohuiTr").attr("style", "display:none;");
	} else {
		$("#tongyiTr").attr("style", "display:none;");
		$("#bohuiTr").attr("style", "display:display;");
	}
}
function tijiao(taskId) {
	var bh = '<%=bo%>';

	if (bh == 1) {
		$("input[name='beilv']").removeAttr("disabled");
				    			$("input[name='dj']").removeAttr("disabled");
				    			$("input[name='dbqyzt']").removeAttr("disabled");
		document.form1.action = path
				+ "/servlet/workflow?action=adjust&taskId=" + taskId;
		document.form1.submit();
	} else {
		$("input[name='beilv']").removeAttr("disabled");
				    			$("input[name='dj']").removeAttr("disabled");
				    			$("input[name='dbqyzt']").removeAttr("disabled");
		var shenhe = $('input:radio[name="shenhe"]:checked').val();
		var opinion = document.form1.opinion.value;
		var nextactionId = $('input[name="nextactionId"]').val();

		if (typeof (shenhe) == "undefined") {
			alert("请选择审核状态！");
			return false;
		} else if (shenhe == 0) {
			var auditorid = $('input:radio[name="auditorid"]:checked').val();
			if (typeof (auditorid) == "undefined"
					&& !(nextactionId == null || nextactionId == "")) {
				alert("请选择执行人！");
				return false;
			}
		} else {
			var bohui = $('input:radio[name="bohui"]:checked').val();
			if (typeof (bohui) == "undefined") {
				alert("请选择驳回节点！");
				return false;
			}
			if (opinion == "") {
				alert("请输入审核意见！");
				return false;
			}

		}						$("input[name='beilv']").removeAttr("disabled");
				    			$("input[name='dj']").removeAttr("disabled");
				    			$("input[name='dbqyzt']").removeAttr("disabled");
		document.form1.action = path
				+ "/servlet/workflow?action=complete&type=" + shenhe
				+ "&taskId=" + taskId;
		document.form1.submit();
	}

}

function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = "";
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">";

	} else {
		trObject.style.display = "none";
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">";
	}
}
function ismoney(str) {
	if (/^(([0-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(str))
		return true;
	return false;
}
function iszhengshu(str) {
	if (/^[0-9]\d*$/.test(str))
		return true;
	return false;
}
function checkfplx() {
	var strfplx = document.form1.fplx.value;
	if (strfplx == 'pjlx02') {
		$('#sl').attr("readonly", "readonly");
		$('#sl').val("0.17")
	} else {
		$('#sl').removeAttr("readonly");
		$('#sl').val("0")
	}
}
function isNO(f) {
	return f.match(new RegExp("^[0-9]+$"));
}
function Number(a) {
	return a.match(new RegExp("^\d+\.\d+$"));
}
function saveAccount() {
	var bl = document.form1.beilv.value;
	var ds = document.form1.csds.value;
	var a = document.form1.wlbm.value;
	var m = document.form1.maxds.value;
	var mt = document.form1.mtllhd.value;
	var bg = document.form1.bgje.value;
	var dan = document.form1.dj.value;
	var zz = document.form1.sl.value;
	var yh = document.form1.yhzh.value;
	var bur_stand_propertion = document.form1.bur_stand_propertion.value;

	if (checkNotSelected(document.form1.shi,"城市")&&checkNotSelected(document.form1.xian,"区县")&&
	checkNotSelected(document.form1.xiaoqu,"乡镇")&&checkNotSelected(document.form1.zdmc,"站点名称")&&
	checkNotnull(document.form1.dbname,"电表名称")&&
	checkNotnull(document.form1.dj,"单价")&&
	checkNotnull(document.form1.beilv,"倍率")&&checkNotnull(document.form1.csds,"启用时读数")&&
	checkNotnull(document.form1.wlbm,"物理编码")&&checkNotnull(document.form1.maxds,"最大读数")
	/*&&checkNotSelected(document.form1.dfzflx,"电费支付类型")*/
	){
		if(bl!=null&&bl!=""){
			if(!iszhengshu(bl)){
				alert("倍率必须为整数！");
				return false;
			}
		}
		if(ds!=null&&ds!=""){
			if(!ismoney(ds)){
				alert("启用时读数必须为数字！");
				return false;
			}
		}
		if(m!=null&&m!=""){
			if(!ismoney(m)){
				alert("最大读数必须为数字！");
				return false;
			}
		}
	    if(mt!=null&&mt!=""){
	    	if(!ismoney(mt)){
				alert("每天理论耗电量必须为数字！");
				return false;
			}
	    }
	    if(bg!=null&&bg!=""){
	    	if(!ismoney(bg)){
				alert("包干金额必须为数字！");
				return false;
			}
	    }
	    if(dan!=null&&dan!=""){
	    	if(!ismoney(dan)){
				alert("单价必须为数字！");
				return false;
			}
	    }
	    if(zz!=null&&zz!=""){
	    	if(!ismoney(zz)){
				alert("税率必须为数字！");
				return false;
			}
	    }	
	    if(yh!=null&&yh!=""){
	    	if(!isNO(yh)){
				alert("银行账号必须为数字！");
				return false;
			}
	    }
	    if(!ismoney(bur_stand_propertion)){
				alert("占局站比例必须为正数！");
				return false;
			}
	    if(parseInt(bur_stand_propertion)>100 || parseInt(bur_stand_propertion)<0){
	    	alert("占局站比例范围0~100！");
				return false;
	    }
	    
	    var bz='<%=bz%>';
	    if(bz!="1"){
		    if(a!=null&&a!=""){
		    	/*if(!isNO(a)){
					alert("电表编码必须为数字！");
					return false;
				}*/
				var idStr ='0';
				 $.ajax({
	                type: "POST",//请求方式
	                url: path+"/servlet/dianbiao?action=getDianbiaoByDbbm",
	                data: {dbbm:a,id:idStr},
	                dataType: "json",
	 success: function(result){
				    	if(result!=""){
				 			alert("电表编码重复！");
				 			return false;
				    	}else{
								if(confirm("您将要添加电表信息！确认信息正确！")){
									document.form1.action=path+"/servlet/dianbiao?action=addDB&bz="+'<%=bz%>';
									document.form1.submit();
									showdiv("请稍等..............");
								}
						}
				    }
	            });
		    }
	    }
		if("1"==bz){
			  if(a!=null&&a!=""){
		    	/*if(!isNO(a)){
					alert("电表编码必须为数字！");
					return false;
				}*/
				var idStr ='<%=id%>';
				 $.ajax({
	                type: "POST",//请求方式
	                url: path+"/servlet/dianbiao?action=getDianbiaoByDbbm",
	                data: {dbbm:a,id:idStr},
	                dataType: "json",
				success: function(result){
				    	if(result!=""){
				 			alert("电表编码重复！");
				 			return false;
				    	}else{
								$("input[name='beilv']").removeAttr("disabled");
				    			$("input[name='dj']").removeAttr("disabled");
				    			$("input[name='dbqyzt']").removeAttr("disabled");
								if(confirm("您将要修改电表信息！确认信息正确！")){
									document.form1.action=path+"/servlet/dianbiao?action=addDB&bz="+'<%=bz%>';
									document.form1.submit();
									showdiv("请稍等..............");
								}
						}
				    }
	            });
		    }
						
						}
		
}
	}
	
        function vName(){
         	var accName = document.form1.dbid.value;
                 if(accName==""){
           	alert("电表ID不能为空！");
                   return
          }
               window.open('validateDbid.jsp?dbid='+accName+'&id=0','','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
        }
        function vCode(){
          var accCode = document.form1.workSn.value;
          if(accCode==""){
           	alert("不能为空！");
                   return
          }
               window.open('accountCode.jsp?accountId=0&accountCode='+accCode,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
        }

        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！");
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
        }
        function fanhui(){        
              document.form1.action=path+"/web/jizhan/dianbiaolist.jsp";
              document.form1.submit();
        }
        
        
        
        
$(function(){
	$("#liulan").click(function(){
		shoulist();
	});
	$("#cancelBtn").click(function(){
	    fanhui();
	});
$("#resetBtn").click(function(){
	$.each($("form input[type='text']"),function(){
	  $(this).val("");
          });
	});
// $("#saveBtn").click(function(){
// 	saveAccount();
	
// 	});
$("#vnameBtn").click(function(){
	vName();
	});
});
			
</script>
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<jsp:useBean id="bean" scope="page"
		class="com.noki.mobi.workflow.javabean.WorkFlowBean">
	</jsp:useBean>
	<jsp:useBean id="flowBean" scope="page"
		class="com.noki.mobi.flow.javabean.FlowBean">
	</jsp:useBean>
	<body>
		<form action="" name="form1" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="top">

					<td width="12">
						<img src="../images/space.gif" width="12" height="17" />
					</td>
					<td>
						<div class="content01">
							<div class="tit01">
								电表审批
							</div>
							<div class="content01_1">
								<table width="100px" border="0" cellpadding="2" cellspacing="0"
									class="tb_select">
									<tr>
										<td align="right" width="100px">
											城市
										</td>
										<td width="100px">
											<select name="shi" disabled="disabled"
												style="background-color: #EBEBE4; box-sizing: border-box; width: 130px"
												class="selected_font" onchange="changeCity()">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList shenglist = new ArrayList();
													shenglist = commBean.getAgcode(shengId, "0", loginName);
													if (shenglist != null) {
														String sfid = "", sfnm = "";
														int scount = ((Integer) shenglist.get(0)).intValue();
														for (int i = scount; i < shenglist.size() - 1; i += scount) {
															sfid = (String) shenglist.get(i
																	+ shenglist.indexOf("AGCODE"));
															sfnm = (String) shenglist.get(i
																	+ shenglist.indexOf("AGNAME"));
												%>
												<option value="<%=sfid%>"><%=sfnm%></option>
												<%
													}
													}
												%>
											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
											&nbsp;
										</td>
										<td align="right" width="100px">
											区县
										</td>
										<td width="100px">
											<select name="xian" disabled="disabled"
												style="background-color: #EBEBE4; box-sizing: border-box; width: 130px"
												class="selected_font" onchange="changeCountry()">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList xianlist = new ArrayList();
													xianlist = commBean.getAgcode(form.getShi(), form.getXian(),
															loginName);
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
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											乡镇
										</td>
										<td width="100px">
											<select disabled="disabled"
												style="background-color: #EBEBE4; box-sizing: border-box; width: 130px"
												name="xiaoqu" id="xiaoqu" onchange="changezdmc()"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList xiaoqulist = new ArrayList();
													xiaoqulist = commBean.getAgcode(form.getXian(), form.getXiaoqu(),
															loginName);
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
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											站点名称
										</td>
										<td width="180px">
											<select disabled="disabled"
												style="background-color: #EBEBE4; box-sizing: border-box; width: 130px"
												name="zdmc" onchange="changeDbbm()" class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList zdmclist = new ArrayList();
													zdmclist = commBean.getzdmc(form.getId());
													if (zdmclist != null) {
														String agcode = "", agname = "";
														int scount = ((Integer) zdmclist.get(0)).intValue();
														for (int i = scount; i < zdmclist.size() - 1; i += scount) {
															agcode = (String) zdmclist.get(i + zdmclist.indexOf("ID"));
															agname = (String) zdmclist.get(i
																	+ zdmclist.indexOf("JZNAME"));
												%>
												<option value="<%=agcode%>"><%=agname%></option>
												<%
													}
													}
												%>
											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											所属方
										</td>
										<td width="180px">
											<select name="ssf" disabled="disabled" 
												style="background-color: #EBEBE4; width: 130px;"
												 class="selected_font">
									  			
													<option value="<%=ssf%>"><%=Tssf%></option>
											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									</tr>
									<tr bgcolor="F9F9F9">
										<td height="19" colspan="4">
											<img src="../../images/v.gif" width="15" height="15" />
											电表信息
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											电表编码
										</td>
										<td width="60px">
											<input type="text" name="wlbm" value="<%=dbbm%>"
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px" />
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											电表名称
										</td>
										<td width="60px">
											<input type="text" id = "dbname" name="dbname" value="<%=dbmc%>"
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px" />
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											电表最大读数
										</td>
										<td width="100px">

											<input type="text" name="maxds" value="<%=maxds %>"  
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
											style="box-sizing:border-box;width:130px"/><span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										
										<td align="right" width="100px">
											启用时读数
										</td>
										<td width="100px">
											<input type="text" name="csds" value="<%=csds%>"
												class="selected_font3"
												<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px" />
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									</tr>
									<tr>
									<td align="right" width="100px">
											责任人
										</td>
										<td width="100px">
											<input type="text" name="zrr" value="<%=zrr%>"
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px" />
												<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											报账人
										</td>
										<td width="100px">
											<input type="text" name="bzr" value="<%=bzr%>"
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px" /><span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											用电属性
										</td>
										<td width="100px">
											<select name="ydsx" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList sx = new ArrayList();
													sx = ztcommon.getSelctOptions("ydsx");
													if (sx != null) {
														String code = "", name = "";
														int cscount = ((Integer) sx.get(0)).intValue();
														for (int i = cscount; i < sx.size() - 1; i += cscount) {
															code = (String) sx.get(i + sx.indexOf("CODE"));
															name = (String) sx.get(i + sx.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
													}
													}
												%>

											</select>
										</td>
										
										<td align="right" width="100px">
											缴费方式
										</td>
										<td width="100px">
											<select name="dfzflx" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList dfzflx = new ArrayList();
													dfzflx = ztcommon.getSelctOptions("FKFS");
													if (dfzflx != null) {
														String code = "", name = "";
														int cscount = ((Integer) dfzflx.get(0)).intValue();
														for (int i = cscount; i < dfzflx.size() - 1; i += cscount) {
															code = (String) dfzflx.get(i + dfzflx.indexOf("CODE"));
															name = (String) dfzflx.get(i + dfzflx.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
													}
													}
												%>

											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											缴费周期
										</td>
										<td width="100px">
											<select name="jfzq" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList zq = new ArrayList();
													zq = ztcommon.getSelctOptions("FKZQ");
													if (zq != null) {
														String code = "", name = "";
														int cscount = ((Integer) zq.get(0)).intValue();
														for (int i = cscount; i < zq.size() - 1; i += cscount) {
															code = (String) zq.get(i + zq.indexOf("CODE"));
															name = (String) zq.get(i + zq.indexOf("NAME"));
												%>
												<option value="<%=code%>" <%=code.equals(jfzq)?"selected=''selected":"" %>><%=name%></option>
												<%
													}
													}
												%>

											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										
										<td align="right" width="100px">
											计费方式
										</td>
										<td width="100px">
											<select name="jffs" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList jf = new ArrayList();
													jf = ztcommon.getSelctOptions("JFFS");
													if (jf != null) {
														String code = "", name = "";
														int cscount = ((Integer) jf.get(0)).intValue();
														for (int i = cscount; i < jf.size() - 1; i += cscount) {
															code = (String) jf.get(i + jf.indexOf("CODE"));
															name = (String) jf.get(i + jf.indexOf("NAME"));
												%>
												<option value="<%=code%>" <%=code.equals(jffs)?"selected=''selected":"" %>><%=name%></option>
												<%
													}
													}
												%>

											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											倍率
										</td>
										<td width="100px">
											<input type="text" name="beilv" value='<%=beilv%>'
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"/>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											状态
										</td>
										<td width="100px">
											<select name="dbqyzt" style="box-sizing:border-box;width:130px;"
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %> class="selected_font" >
					         		<option value="0">请选择</option>
					         		<%
					         		ArrayList SERVICE_ID_dict = new ArrayList();
					         		SERVICE_ID_dict = ztcommon.getSelctOptions("dbzt");
						         	if(SERVICE_ID_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)SERVICE_ID_dict.get(0)).intValue();
						         		for(int i=cscount;i<SERVICE_ID_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)SERVICE_ID_dict.get(i+SERVICE_ID_dict.indexOf("CODE"));
					                    	name=(String)SERVICE_ID_dict.get(i+SERVICE_ID_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"  <%=code.equals(dbqyzt)?"selected=''selected":"" %>><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
											
										
									</tr>
									<tr>
									<td align="right" width="100px">
											单价
										</td>
										<td width="100px">
											<input type="text" name="dj" value="<%=danjia%>"
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"/>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											发票类型
										</td>
										<td width="100px">
											<select name="fplx" onchange="checkfplx()"
											 <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList fp = new ArrayList();
													fp = ztcommon.getSelctOptions("PJLX");
													if (fp != null) {
														String code = "", name = "";
														int cscount = ((Integer) fp.get(0)).intValue();
														for (int i = cscount; i < fp.size() - 1; i += cscount) {
															code = (String) fp.get(i + fp.indexOf("CODE"));
															name = (String) fp.get(i + fp.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
													}
													}
												%>

											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											税率
										</td>
										<td width="100px">
											<input type="text" name="sl" id="sl" value="<%=zzsl%>"
											 <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" /><span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											生产占比(%)
										</td>
										<td>
											<input type="text" name="production_prop"
												value="<%=production_prop%>"
												 <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td><span style="color: #FF0000; font-weight: bold">*</span>
										</tr><tr>
										<td align="right" width="100px">
											占局站比例(%)
										</td>
										<td>
											<input type="text" name="bur_stand_propertion"
												id="bur_stand_propertion" value="<%=bur_stand_propertion%>"
												style="box-sizing: border-box; width: 130px"
												 <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												class="selected_font3" />
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											电表类型
										</td>
										<td>
											<select name="isdblx"  <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList isdblxDict = new ArrayList();
													isdblxDict = ztcommon.getSelctOptions("ISDBLX_DICT");
													if (isdblxDict != null) {
														String code = "", name = "";
														int cscount = ((Integer) isdblxDict.get(0)).intValue();
														for (int i = cscount; i < isdblxDict.size() - 1; i += cscount) {
															code = (String) isdblxDict.get(i
																	+ isdblxDict.indexOf("CODE"));
															name = (String) isdblxDict.get(i
																	+ isdblxDict.indexOf("NAME"));
												%>
												<option value="<%=code%>"
													<%=code.equals(isdblx) ? "selected=''selected" : ""%>><%=name%></option>
												<%
													}
													}
												%>

											</select><span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											供电方式
										</td>
										<td>
											<select name="electric_supply_way"
											 <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList electric_supply_wayDict = new ArrayList();
													electric_supply_wayDict = ztcommon.getSelctOptions("GDFS");
													if (electric_supply_wayDict != null) {
														String code = "", name = "";
														int cscount = ((Integer) electric_supply_wayDict.get(0))
																.intValue();
														for (int i = cscount; i < electric_supply_wayDict.size() - 1; i += cscount) {
															code = (String) electric_supply_wayDict.get(i
																	+ electric_supply_wayDict.indexOf("CODE"));
															name = (String) electric_supply_wayDict.get(i
																	+ electric_supply_wayDict.indexOf("NAME"));
												%>
												<option value="<%=code%>"
													<%=code.equals(electric_supply_way) ? "selected=''selected"
									: ""%>><%=name%></option>
												<%
													}
													}
												%>

											</select><span style="color: #FF0000; font-weight: bold">*</span>
										</td><td align="right" width="100px">
											电表用途
										</td>
										<td width="100px">
											<select name="dbyt" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList DBYT = new ArrayList();
													DBYT = ztcommon.getSelctOptions("DBYT");
													if (DBYT != null) {
														String code = "", name = "";
														int cscount = ((Integer) DBYT.get(0)).intValue();
														for (int i = cscount; i < DBYT.size() - 1; i += cscount) {
															code = (String) DBYT.get(i + DBYT.indexOf("CODE"));
															name = (String) DBYT.get(i + DBYT.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
													}
													}
												%>

											</select>
<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										</tr>
										<tr id="yc1">
										
										<td align="right" width="100px">
											分摊比例（%）
										</td>
										<td width="100px">
											<input type="text" name="FTBL" id="FTBL" value="<%=FTBL%>"
												style="box-sizing: border-box; width: 130px"
												disabled="disabled"
												class="selected_font3" />
												<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											税负因子（%）
										</td>
										<td width="100px">
											<input type="text" name="SFYZ" id="SFYZ" value="<%=SFYZ%>"
												style="box-sizing: border-box; width: 130px"
												disabled="disabled"
												class="selected_font3" />
												<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										</tr>
										<tr id="yc2">
										<td align="right" width="100px">
											对外结算类型
										</td>
										<td width="100px">
											<select name="dwjslx" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList jslx = new ArrayList();
													jslx = ztcommon.getSelctOptions("dwjslx");
													if (jslx != null) {
														String code = "", name = "";
														int cscount = ((Integer) jslx.get(0)).intValue();
														for (int i = cscount; i < jslx.size() - 1; i += cscount) {
															code = (String) jslx.get(i + jslx.indexOf("CODE"));
															name = (String) jslx.get(i + jslx.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
													}
													}
												%>

											</select>
										</td>
										<td align="right" width="100px">
											是否有管理费
										</td>
										<td width="100px">
											<select name="isglf" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px">
												<option value="" <%="".equals(isglf) ? "selected" : ""%>>
													请选择
												</option>
												<option value="0" <%="0".equals(isglf) ? "selected" : ""%>>
													否
												</option>
												<option value="1" <%="1".equals(isglf) ? "selected" : ""%>>
													是
												</option>
											</select>
										</td>
										<td align="right" width="100px">
											管理部门
										</td>
										<td width="100px">
											<input type="text" name="glbm" value="<%=glbm%>"
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											用电类型
										</td>
										<td width="100px">
											<select name="ydlx" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList lx = new ArrayList();
													lx = ztcommon.getSelctOptions("YDLX");
													if (lx != null) {
														String code = "", name = "";
														int cscount = ((Integer) lx.get(0)).intValue();
														for (int i = cscount; i < lx.size() - 1; i += cscount) {
															code = (String) lx.get(i + lx.indexOf("CODE"));
															name = (String) lx.get(i + lx.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
													}
													}
												%>

											</select>
										</td>
									</tr>
									
									
									<tr id="yc3">										
									<td align="right" width="100px">
											计量单位
										</td>
										<td width="100px">
											<select name="jldw" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList dw = new ArrayList();
													dw = ztcommon.getSelctOptions("MU");
													if (dw != null) {
														String code = "", name = "";
														System.out.println("计量单位");
														int cscount = ((Integer) dw.get(0)).intValue();
														for (int i = cscount; i < dw.size() - 1; i += cscount) {
															code = (String) dw.get(i + dw.indexOf("CODE"));
															name = (String) dw.get(i + dw.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
													}
													}
												%>

											</select>
										</td>
										<td align="right" width="100px">
											用户编号
										</td>
										<td width="100px">
											<input type="text" name="yhbh" value="<%=yhbh%>"
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											包干金额
										</td>
										<td width="100px">
											<input type="text" name="bgje" value="<%=bgje%>"
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											每天理论耗电量
										</td>
										<td width="100px">
											<input type="text" name="mtllhd" value="<%=mtllhd%>"
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px" />
										</td>
									</tr>
									<tr id="yc4">
										
										<td align="right" width="100px">
											供应商名称
										</td>
										<td width="100px">
										    <%-- <select name="gysmc" onchange="changeSupplier()" 
										     <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
										     style="box-sizing: border-box; width: 130px">
										     <option value="0">请选择</option>
										       <%
										       ArrayList supplist = new ArrayList();
										       supplist = commBean.getSuppliers();
										       if(supplist != null){
										    	   String code="",name="";
										    	   int suppcount = ((Integer)supplist.get(0)).intValue();
										    	   for(int i=suppcount; i<supplist.size()-1; i+=suppcount){
											    	   code = (String)supplist.get(i + supplist.indexOf("CODE"));
											    	   name = (String)supplist.get(i + supplist.indexOf("NAME"));
											   %>
											       <option value="<%=code %>"><%=name %></option>
											   <%
										    	   }
										       }
										       %>
										       
										       
										     </select>--%>
											<input type="text" name="gysmc" value="<%=gysmc%>" onclick="openSupplier()"
												readonly="readonly" 
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											供应商编码
										</td>
										<td width="100px">
											<input type="text" name="gysbm" value="<%=gysbm%>"
												style="box-sizing: border-box; width: 130px"
												readonly="readonly"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											收款方名称
										</td>
										<td width="100px">
											<input type="text" name="skfmc" value="<%=skfmc%>"
												style="box-sizing: border-box; width: 130px"
												readonly="readonly"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											供应商账户组
										</td>
										<td>
											<input type="text" name="zhz" value="<%=zhz%>"
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
									</tr>
									<tr id="yc5">
										<td align="right" width="100px">
											银行帐号
										</td>
										<td width="100px">
											<input type="text" name="yhzh" value="<%=yhzh%>"
												style="box-sizing: border-box; width: 130px"
												readonly="readonly"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											所属银行
										</td>
										<td width="100px">
											<input type="text" name="ssyh" value="<%=ssyh%>"
												style="box-sizing: border-box; width: 130px"
												readonly="readonly"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											开户银行
										</td>
										<td width="100px">
											<input type="text" name="khyh" value="<%=khyh%>"
												style="box-sizing: border-box; width: 130px"
												readonly="readonly"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											银行帐户所属省
										</td>
										<td width="100px">
											<input type="text" name="zhsss" value="<%=zhsss%>"
												style="box-sizing: border-box; width: 130px"
												readonly="readonly"
												class="selected_font3" />
										</td>
									</tr>
									<tr id="yc6">
										<td align="right" width="100px">
											银行帐户所属市
										</td>
										<td width="100px">
											<input type="text" name="zhssshi" value="<%=zhssshi%>"
												style="box-sizing: border-box; width: 130px"
												readonly="readonly"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											备注
										</td>
										<td width="100px">
											<input type="text" name="memo" value="<%=memo%>"
											 <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											是否智能电表
										</td>
										<td>
											<select name="zndb"  <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList zndbDict = new ArrayList();
													zndbDict = ztcommon.getSelctOptions("shifou");
													if (zndbDict != null) {
														String code = "", name = "";
														int cscount = ((Integer) zndbDict.get(0)).intValue();
														for (int i = cscount; i < zndbDict.size() - 1; i += cscount) {
															code = (String) zndbDict.get(i + zndbDict.indexOf("CODE"));
															name = (String) zndbDict.get(i + zndbDict.indexOf("NAME"));
												%>
												<option value="<%=code%>"
													<%=code.equals(zndb) ? "selected=''selected" : ""%>><%=name%></option>
												<%
													}
													}
												%>

											</select>
										</td>
										
										<td align="right" width="100px">
											账期
										</td>
										<td>
											<input type="text" name="zhangqi" value="<%=zhangqi%>"
											<%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
									</tr>
									<%--	<tr>
										<td align="right" width="100px">
											电表用途
										</td>
										<td width="100px">
											<select name="dbyt" style="box-sizing:border-box;width:130px"  class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList DBYT = new ArrayList();
         		DBYT = ztcommon.getSelctOptions("DBYT");
	         	if(DBYT!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)DBYT.get(0)).intValue();
	         		for(int i=cscount;i<DBYT.size()-1;i+=cscount)
                    {
                    	code=(String)DBYT.get(i+DBYT.indexOf("CODE"));
                    	name=(String)DBYT.get(i+DBYT.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	
         	</select>
						
										</td>
										<td align="right" width="100px">
											电表串号(实拍号)
										</td>
										<td width="100px">
											<input type="text" name="dbch" value="<%=dbch %>" style="box-sizing:border-box;width:130px"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											生产电表标识
										</td>
										<td width="100px">
											<select name="dbscbs" style="box-sizing:border-box;width:130px">
												<option value="" <%="".equals(dbscbs)?"selected":"" %>>请选择</option>
												<option value="0" <%="0".equals(dbscbs)?"selected":"" %>>否</option>
      											<option value="1" <%="1".equals(dbscbs)?"selected":"" %>>是</option>
											</select>
										</td>

									</tr> --%>
									<tr id="yc7">
										<td align="right" width="100px">
											区县分公司
										</td>
										<td>
											<input type="text" name="cbzx" value="<%=cbzx%>"
											 <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											成本中心编码
										</td>
										<td>
											<input type="text" name="cbzxbm" value="<%=cbzxbm%>"
											 <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											核定电量
										</td>
										<td>
											<input type="text" name="total_electricity"
												value="<%=total_electricity%>"
												 <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											合同类型
										</td>
										<td>
											<select name="cont_type"  <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList cont_typeDict = new ArrayList();
													cont_typeDict = ztcommon.getSelctOptions("CONT_TYPE_DICT");
													if (cont_typeDict != null) {
														String code = "", name = "";
														int cscount = ((Integer) cont_typeDict.get(0)).intValue();
														for (int i = cscount; i < cont_typeDict.size() - 1; i += cscount) {
															code = (String) cont_typeDict.get(i
																	+ cont_typeDict.indexOf("CODE"));
															name = (String) cont_typeDict.get(i
																	+ cont_typeDict.indexOf("NAME"));
												%>
												<option value="<%=code%>"
													<%=code.equals(cont_type) ? "selected=''selected"
							: ""%>><%=name%></option>
												<%
													}
													}
												%>

											</select>
										</td>
									</tr>
									<tr id="yc8">
										<td align="right" width="100px">
											是否有合同
										</td>
										<td>
											<select name="is_cont"  <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList is_contDict = new ArrayList();
													is_contDict = ztcommon.getSelctOptions("shifou");
													if (is_contDict != null) {
														String code = "", name = "";
														int cscount = ((Integer) is_contDict.get(0)).intValue();
														for (int i = cscount; i < is_contDict.size() - 1; i += cscount) {
															code = (String) is_contDict.get(i
																	+ is_contDict.indexOf("CODE"));
															name = (String) is_contDict.get(i
																	+ is_contDict.indexOf("NAME"));
												%>
												<option value="<%=code%>"
													<%=code.equals(is_cont) ? "selected=''selected"
									: ""%>><%=name%></option>
												<%
													}
													}
												%>

											</select>
										</td>
										<td align="right" width="100px">
											合同号
										</td>
										<td>
											<input type="text" name="cont_id" value="<%=cont_id%>"
												 <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
										
										
									</tr>
									<tr>
										
										
									</tr>
									
									<tr bgcolor="F9F9F9" id="yc9">
										<td height="19" colspan="4">
											<img src="../../images/v.gif" width="15" height="15" />
											成本中心信息&nbsp;&nbsp;&nbsp;&nbsp;（如需填写分摊系数时，分摊系数总和必须等于1）
										</td>
									</tr>
									<tr id="yc10">
										<td align="right" width="100px">
											公司主体
										</td>
										<td width="100px" colspan="8">
										   <select name="gszt" style="width: 250px;"
										   <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>
										   >
										      <option value="0">请选择</option>
										      <%
													ArrayList gsztlist = new ArrayList();
										     	 gsztlist = ztcommon.getSelctOptions("GSZT");
													if (gsztlist != null) {
														String code = "", name = "";
														int cscount = ((Integer) gsztlist.get(0)).intValue();
														for (int i = cscount; i < gsztlist.size() - 1; i += cscount) {
															code = (String) gsztlist.get(i + gsztlist.indexOf("CODE"));
															name = (String) gsztlist.get(i + gsztlist.indexOf("NAME"));
												%>
														<option value="<%=code%>" <%=code.equals(gszt)?"selected='selected'":"" %>><%=name%></option>
												<%
													}
												 }
												%>
										   </select>
										   <span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									</tr>
									<tr id="yc11">
									   <td align="center" colspan="8">
									      <table id="costTab" width="96%" border="0" cellspacing="1" cellpadding="0" bgcolor="#C0C0C0">
									         <tr bgcolor="#FFF">
									         	<th align="center" width="100px">成本中心</th>
												<th align="center" width="100px">业务类型</th>
												<th align="center" width="100px">用电属性</th>
												<th align="center" width="100px">预算责任中心</th>
		 										<th align="center" width="100px">预算项目</th>
												<th align="center" width="50px">是否实际分表</th>
												<th align="center" width="50px">分摊系数</th>
												<th align="left" width="100px"><a href="javascript:void(0)" onclick="addTr()">添加</a></th>
									         </tr>
									         
									         <%
									            if(taskid != null && !taskid.isEmpty()){
									             	AccountingDao dao = new AccountingDao();
										            ArrayList list = dao.getAccounting(taskid);
										            if(list !=null && list.size()>0){
										            	int count = ((Integer)list.get(0)).intValue();
										            	String costCode="",businessType="",elecProperty="",dutyCode="",projectCode="",isRealSubAmmeter="",shareRatio="";
											            for(int k=count; k<list.size()-1; k+=count){
											            	//COST_CODE,BUSINESS_TYPE,ELEC_PROPERTY,DUTY_CODE,PROJECT_CODE,IS_REAL_sub_AMMETER,SHARE_RATIO
											            	costCode = (String)list.get(k + list.indexOf("COST_CODE"));
											            	businessType = (String)list.get(k + list.indexOf("BUSINESS_TYPE"));
											            	elecProperty = (String)list.get(k + list.indexOf("ELEC_PROPERTY"));
											            	dutyCode = (String)list.get(k + list.indexOf("DUTY_CODE"));
											            	projectCode = (String)list.get(k + list.indexOf("PROJECT_CODE"));
											            	isRealSubAmmeter = (String)list.get(k + list.indexOf("IS_REAL_SUB_AMMETER"));
											            	shareRatio = (String)list.get(k + list.indexOf("SHARE_RATIO"));
											  %>
											 <tr bgcolor="#FFF" id="yc12">
									            <td>
									           		<select name="costCode" style="box-sizing: border-box; width: 130px" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %> onchange="changeCostCenter()">
									           		   <option value="0">请选择</option>
									           		   <%
															ArrayList costlist = new ArrayList();
									           				costlist = commBean.getCostCenter();
															if (costlist != null) {
																int costcount = ((Integer) costlist.get(0)).intValue();
															    String CODE = "", NAME = "";
																for (int i = costcount; i < costlist.size() - 1; i += costcount) {
																	CODE = (String) costlist.get(i + costlist.indexOf("CODE"));
																	NAME = (String) costlist.get(i + costlist.indexOf("NAME"));
															%>
																	<option value="<%=CODE%>" <%=CODE.equals(costCode)? "selected='selected'" : "" %>><%=NAME%></option>
			
															<%
																}
															 }
															%>
									           		</select>
									           		<span style="color: #FF0000; font-weight: bold">*</span>
									            </td>
									            <td>
									           		<select name="businessType" style="box-sizing: border-box; width: 100px" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>>
														<option value="0">请选择</option>
														<%
														ArrayList busilist = new ArrayList();
														busilist = ztcommon.getSelctOptions("dianbiao_ywlx");
														if (busilist != null) {
															String code = "", name = "";
															int busicount = ((Integer) busilist.get(0)).intValue();
															for (int i = busicount; i < busilist.size() - 1; i += busicount) {
																code = (String) busilist.get(i + busilist.indexOf("CODE"));
																name = (String) busilist.get(i + busilist.indexOf("NAME"));
														%>
															<option value="<%=code%>" <%=code.equals(businessType)? "selected='selected'" : "" %>><%=name%></option>
														<%
															}
														}
														%>
											   		</select>
									            </td>
									            <td>
									           		<select name="elecProperty" style="box-sizing: border-box; width: 100px" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>>
														<option value="0">请选择</option>
														<%
														ArrayList eleclist = new ArrayList();
														eleclist = ztcommon.getSelctOptions("ydsx");
														if (eleclist != null) {
															String code = "", name = "";
															int eleccount = ((Integer) eleclist.get(0)).intValue();
															for (int i = eleccount; i < eleclist.size() - 1; i += eleccount) {
																code = (String) eleclist.get(i + eleclist.indexOf("CODE"));
																name = (String) eleclist.get(i + eleclist.indexOf("NAME"));
														%>
															<option value="<%=code%>" <%=code.equals(elecProperty)? "selected='selected'" : "" %>><%=name%></option>
														<%
															}
														}
														%>
											   		</select>
									            </td>
									            <td>
									           		<select name="dutyCode" style="box-sizing: border-box; width: 130px" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>>
														<option value="0">请选择</option>
														<%
															ArrayList dutylist = new ArrayList();
														    dutylist = commBean.getBudgetDutyCenter();
															if (costlist != null) {
																int dutycount = ((Integer) dutylist.get(0)).intValue();
														    	String CODE = "", NAME = "";
																for (int i = dutycount; i < dutylist.size() - 1; i += dutycount) {
																	CODE = (String) dutylist.get(i + dutylist.indexOf("CODE"));
																	NAME = (String) dutylist.get(i + dutylist.indexOf("NAME"));
														%>
																<option value="<%=CODE%>" <%=CODE.equals(dutyCode)? "selected='selected'" : "" %>><%=NAME%></option>
			
														<%
															}
														 }
														%>
											   		</select>
									            </td>
									            <td>
									           		<select name="projectCode" style="box-sizing: border-box; width: 130px" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>>
														<option value="0">请选择</option>
														<%
															ArrayList projectlist = new ArrayList();
															projectlist = commBean.getBudgetProject();
															if (costlist != null) {
																int projectcount = ((Integer) projectlist.get(0)).intValue();
														    	String CODE = "", NAME = "";
																for (int i = projectcount; i < projectlist.size() - 1; i += projectcount) {
																	CODE = (String) projectlist.get(i + projectlist.indexOf("CODE"));
																	NAME = (String) projectlist.get(i + projectlist.indexOf("NAME"));
														%>
																	<option value="<%=CODE%>" <%=CODE.equals(projectCode)? "selected='selected'" : "" %>><%=NAME%></option>
			
														<%
															}
														 }
														%>
											   		</select>
									            </td>
									            <td>
									           		<select name="isRealSubAmmeter" style="box-sizing: border-box; width: 100px" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>>
									           		    <%
									           		 		ArrayList sublist = new ArrayList();
									           		       sublist = commBean.getSelctOptions("IS_REAL_SUB_AMMETER");
									           		       if(sublist != null){
									           		    	  String CODE = "", NAME = "";
									           		    	  int subcount = ((Integer) sublist.get(0)).intValue();
															  for (int i = subcount; i < sublist.size() - 1; i += subcount) {
																 CODE = (String) sublist.get(i + sublist.indexOf("CODE"));
																 NAME = (String) sublist.get(i + sublist.indexOf("NAME"));
														%>
															<option value="<%=CODE %>" <%=CODE.equals(isRealSubAmmeter)? "selected='selected'" : "" %>><%=NAME %></option>
														<%
									           		          }
									           		       }
									           		    %>
											   		</select>
									            </td>
									            <td>
									           	  <input type="text" name="shareRatio" value="<%=shareRatio %>" onkeyup="this.value=this.value.replace(/[^\d\.]/g,'')" style="box-sizing: border-box; width: 100px"/>
									            </td>
									            <td>&nbsp;</td>
									         </tr>
											 
											  <%
											            }
										            }
										            
									            } else {
									         
									         %>
									         <tr bgcolor="#FFF" id="addCost">
									            <td>
									           		<select name="costCode" style="box-sizing: border-box; width: 173px" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %> onchange="changeCostCenter(this)">
									           		   <option value="0">请选择</option>
									           		   <%
															ArrayList costlist = new ArrayList();
									           				costlist = commBean.getCostCenter();
															if (costlist != null) {
																int costcount = ((Integer) costlist.get(0)).intValue();
															    String CODE = "", NAME = "";
																for (int i = costcount; i < costlist.size() - 1; i += costcount) {
																	CODE = (String) costlist.get(i + costlist.indexOf("CODE"));
																	NAME = (String) costlist.get(i + costlist.indexOf("NAME"));
															%>
																	<option value="<%=CODE%>"><%=NAME%></option>
			
															<%
																}
															 }
															%>
									           		</select>
									           		<span style="color: #FF0000; font-weight: bold">*</span>
									            </td>
									            <td>
									           		<select name="businessType" style="box-sizing: border-box; width: 75px" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>>
														<option value="0">请选择</option>
														<%
														ArrayList busilist = new ArrayList();
														busilist = ztcommon.getSelctOptions("dianbiao_ywlx");
														if (busilist != null) {
															String code = "", name = "";
															int busicount = ((Integer) busilist.get(0)).intValue();
															for (int i = busicount; i < busilist.size() - 1; i += busicount) {
																code = (String) busilist.get(i + busilist.indexOf("CODE"));
																name = (String) busilist.get(i + busilist.indexOf("NAME"));
														%>
															<option value="<%=code%>"><%=name%></option>
														<%
															}
														}
														%>
											   		</select>
									            </td>
									            <td>
									           		<select name="elecProperty" style="box-sizing: border-box; width: 75px" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>>
														<option value="0">请选择</option>
														<%
														ArrayList eleclist = new ArrayList();
														eleclist = ztcommon.getSelctOptions("ydsx");
														if (eleclist != null) {
															String code = "", name = "";
															int eleccount = ((Integer) eleclist.get(0)).intValue();
															for (int i = eleccount; i < sx.size() - 1; i += eleccount) {
																code = (String) eleclist.get(i + eleclist.indexOf("CODE"));
																name = (String) eleclist.get(i + eleclist.indexOf("NAME"));
														%>
															<option value="<%=code%>"><%=name%></option>
														<%
															}
														}
														%>
											   		</select>
									            </td>
									            <td>
									           		<select name="dutyCode" style="box-sizing: border-box; width: 173px" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>>
														<option value="0">请选择</option>
														<%
															ArrayList dutylist = new ArrayList();
														    dutylist = commBean.getBudgetDutyCenter();
															if (costlist != null) {
																int dutycount = ((Integer) dutylist.get(0)).intValue();
														    	String CODE = "", NAME = "";
																for (int i = dutycount; i < dutylist.size() - 1; i += dutycount) {
																	CODE = (String) dutylist.get(i + dutylist.indexOf("CODE"));
																	NAME = (String) dutylist.get(i + dutylist.indexOf("NAME"));
														%>
																<option value="<%=CODE%>"><%=NAME%></option>
			
														<%
															}
														 }
														%>
											   		</select>
									            </td>
									            <td>
									           		<select name="projectCode" style="box-sizing: border-box; width: 75px" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>>
														<option value="0">请选择</option>
														<%
															ArrayList projectlist = new ArrayList();
															projectlist = commBean.getBudgetProject();
															if (costlist != null) {
																int projectcount = ((Integer) projectlist.get(0)).intValue();
														    	String CODE = "", NAME = "";
																for (int i = projectcount; i < projectlist.size() - 1; i += projectcount) {
																	CODE = (String) projectlist.get(i + projectlist.indexOf("CODE"));
																	NAME = (String) projectlist.get(i + projectlist.indexOf("NAME"));
															%>
																	<option value="<%=CODE%>"><%=NAME%></option>
			
															<%
																}
															 }
															%>
											   		</select>
									            </td>
									            <td>
									           		<select name="isRealSubAmmeter" style="box-sizing: border-box; width: 75px" <%if(!bo.equals("1")){ %>disabled="disabled" <%} %>>
														<%
									           		 		ArrayList sublist = new ArrayList();
									           		        sublist = commBean.getSelctOptions("IS_REAL_SUB_AMMETER");
									           		        if(sublist != null){
									           		    	   String code = "", name = "";
									           		    	   int subcount = ((Integer) sublist.get(0)).intValue();
															   for (int i = subcount; i < sublist.size() - 1; i += subcount) {
																  code = (String) sublist.get(i + sublist.indexOf("CODE"));
																  name = (String) sublist.get(i + sublist.indexOf("NAME"));
														%>
															<option value="<%=code %>"><%=name %></option>
														<%
									           		          }
									           		       }
									           		    %>
											   		</select>
									            </td>
									            <td>
									           	  <input type="text" name="shareRatio" onkeyup="this.value=this.value.replace(/[^\d\.]/g,'')" style="box-sizing: border-box; width: 70px"/>
									            </td>
									            <td>&nbsp;</td>
									         </tr>
									         <%
									          }
									         %>
									      </table>
									   </td>
									</tr>
											
									<tr>
										<td height="19" colspan="8">
											<img src="../../images/v.gif" width="15" height="15" />
											申请/审核信息
										</td>
									</tr>
									<%
									//if(!bo.equals("1")){
										%>
									<tr>
										<td align="right" width="100px">
											报送人
										</td>
										<td>
											<input type="text" name="ACCNAME" id="ACCNAME"
												disabled="disabled" value="<%=ACCNAME%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											报送时间
										</td>
										<td>
											<input type="text" name="ACCTIME" id="ACCTIME"
												disabled="disabled" value="<%=ACCTIMEStr%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										</tr>
										<tr>
											<td colspan="8">
												<div>
													审核意见：
												</div>
												<div>
													<textarea rows="5" cols="50" name="opinion"></textarea>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="8">
												<div>
													当前节点：<%=actionname%></div>
											</td>
										</tr>


										<tr>
											<td colspan="8">
												<div>
													<label>
														<input type="radio" name="shenhe" onchange="tongyi()"
															value="0" />
														同意
													</label>
													<label>
														<input type="radio" name="shenhe" onchange="tongyi()"
															value="1" />
														不同意
													</label>
												</div>
											</td>
										</tr>
										<%
										//} 
										%>
										<tr id="tongyiTr" style="display: none">
											<td colspan="8">
												<div>
													下一节点：<%=nextaction%></div>
												<div>
													执行人：
													<%
													if (nextactionId != null && !nextactionId.equals("")) {
														String agcode = "";
														if (nextaction.indexOf("市") != -1) {
															agcode = shi;
														} else if (nextaction.indexOf("县") != -1) {
															agcode = xian;
														}
														ArrayList acclist = new ArrayList();
														//区县管理员上报到是电费管理员 【电表-用电属性】
														//if (nextaction.equals("市公司电费管理员审核")) {
														//if (nextsteporderno.equals("2")) {//step2
															if ("市公司电费管理员审核".equals(nextaction)) {//step2
															if (!ydsx.isEmpty() && ydsx.equals("ydsx11")) {//办公
																acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "办公");
															} else if (!ydsx.isEmpty() && ydsx.equals("ydsx12")) {//营业厅
																acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "营业厅");
															} else if (!ydsx.isEmpty() && ydsx.equals("ydsx15")) {//其他
																acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "生产");

															} else {//生产
																acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "生产");
															}

															} else if ("市级主任审核".equals(nextaction)) {//step3
																if (!ydsx.isEmpty() && ydsx.equals("ydsx11")) {//办公
																	acclist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(),agcode, "%市综合部主任%");
																} else if (!ydsx.isEmpty() && ydsx.equals("ydsx12")) {//营业厅
																	acclist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(),agcode, "%市销售部主任%");
																} 

																 else {//生产
																	acclist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(),agcode, "%市运维主任%");
																}
														}else {
															acclist = bean.getAccountByActionId(nextactionId, taskid, agcode, tasktype);
														}
																if (acclist.size()!=0) {
																	String accountId = "", name = "";
																	int cscount = ((Integer) acclist.get(0)).intValue();
																	for (int i = cscount; i < acclist.size() - 1; i += cscount) {
																		accountId = (String) acclist.get(i + acclist.indexOf("ACCOUNTID"));
																		name = (String) acclist.get(i + acclist.indexOf("NAME"));
														%>
												
													<label>
														<input type="radio" name="auditorid"
															value="<%=accountId%>" checked /><%=name%></label>
													<%
															}
														}
													}
													%>


												</div>
											</td>
										</tr>
										<tr id="bohuiTr" style="display: none">
											<td colspan="8">
												<%
													String action = actionname.toString();
													//if (actionname.equals("市公司电费管理员审核（电表）")) {
													//if (steporderno.equals("2")) {//step2
													if ("市公司电费管理员审核".equals(action)) {//step2
												%>
												<div>
													上一节点：发起人
												</div>
												<div>
													执行人：
													<%
													bohuijd = "0";
													String accName = bean.getAuditorName(applyUserId, db);
												%><label>
														<input type="radio" name="bohui" value="0" checked /><%=accName%></label>
												</div>
												<%
													}else if("市分管副总审核".equals(action)) {//
														String actionNo = bean.getStepNo(currentid, db);
														int stepNo = Integer.parseInt(actionNo);
														String  acc="", accName="", lactionname="";
														if((stepNo - 2) > 0){
															String nextNo = (stepNo - 2) + "";	
															String nextStep = bean.getNextStep(flowId, nextNo);
															acc = bean.getAuditor(id, nextStep, db);
															accName = bean.getAuditorName(acc, db);
															bohuijd = nextStep;
															lactionname = bean.getActionName(nextStep, db);													
														}else{
															bohuijd = "0";
															accName = bean.getAuditorName(applyUserId, db);
															acc = "0";
															lactionname="发起人";
														}
													
													%>	
																
												<div>上一节点：<%=lactionname %></div>
												<div>
													执行人：
													<label> <input type="radio" name="bohui"
														value="<%=acc%>" checked /><%=accName%></label>
												</div>
														<%	
															} else {
														%>
												<div>
													上一节点：<%=lastactionname%>
												</div>
												<div>
													执行人：
													<%
															if (lastactionname.equals("发起人")) {
																	bohuijd = "0";
																	String accName = bean.getAuditorName(applyUserId, db);
														%>
													<label> <input type="radio" name="bohui" value="0"
														checked /><%=accName%></label>

													<%
																} else {
															%>
													<%
																String actionNo = bean.getStepNo(currentid, db);
																		int stepNo = Integer.parseInt(actionNo);
																		String nextNo = (stepNo - 1) + "";
																		String nextStep = bean.getNextStep(flowId, nextNo);
																		String acc = bean.getAuditor(id, nextStep, db);
																		String accName = bean.getAuditorName(acc, db);
																		bohuijd = nextStep;
															%><label> <input type="radio" name="bohui"
														value="<%=acc%>" checked /><%=accName%></label>

													<%
																}
															%>
												</div> <%
															}
														%>
											</td>
										</tr>
										<tr><%--
										 <% if(bo.equals("1")){ 
	                                          //当前流程在发起人时
                                              //FlowBean flowBean = new FlowBean();
										      ArrayList rolelist = flowBean.getRole(nextactionId, account.getDeptNo());//当前步骤对应角色
										      int rolecount = ((Integer)rolelist.get(0)).intValue();
										      String actionRole="", actionId="", actionName="";
										      if(rolelist.size() > (rolecount+1)){
										         actionRole = (String)rolelist.get(rolecount + rolelist.indexOf("ROLEID"));
										         actionId = (String)rolelist.get(rolecount + rolelist.indexOf("ACTIONID"));
										         actionName = (String)rolelist.get(rolecount + rolelist.indexOf("ACTIONNAME"));
										      }
											%>
										 <tr>
										   <td align="right" width="100px">下一节点</td>
										   <td width="100px" colspan="8">
										       <%=actionName %>
										       <input type="hidden" name="actionId" value="<%=actionId %>"/>
										   </td>
										</tr>
										<tr>
										   <td align="right" width="100px">执行人</td>
										   <td width="100px" colspan="8">
	                                         <%
												 ArrayList acclist = flowBean.getAccountByDeptNoAndRoleId(account.getDeptNo(), xian, actionRole);
												 int acnt = ((Integer)acclist.get(0)).intValue();
												 String auditorId="", auditorName="";
												 for(int i=acnt; i<acclist.size()-1; i+=acnt){
													  auditorId = (String) acclist.get(acnt + acclist.indexOf("ACCOUNTID"));
										        	  auditorName = (String) acclist.get(acnt + acclist.indexOf("NAME"));
											    %>
											    	<input type="radio" name="auditorid" value="<%=auditorId %>" checked="checked"/>&nbsp;<%=auditorName %>&nbsp;&nbsp;
												<%	
												  } 
												%>
                                            </td>
									    </tr>	
									    <%} %>									
											--%><tr>
												<td colspan="8">
													<div>
														审批意见：
													</div>
													<div>
														<table>
															<tr align="center">
																<th width="10">
																	序号
																</th>
																<th>
																	审核人
																</th>
																<th>
																	审核时间
																</th>
																<th>
																	审核状态
																</th>
																<th>
																	审核意见
																</th>
															</tr>
															<%
																ArrayList li = new ArrayList();
																li = bean.getApproverByWorkFlowId(id);
																if (li != null) {
																	String accountname2 = "", checktime = "", state = "", opinion = "";
																	int num = 1;
																	int cscount = ((Integer) li.get(0)).intValue();
																	for (int i = cscount; i < li.size() - 1; i += cscount) {
																		accountname2 = (String) li.get(i
																				+ li.indexOf("ACCOUNTNAME"));
																		checktime = (String) li.get(i + li.indexOf("CHECKTIME"));
																		state = (String) li.get(i + li.indexOf("STATE"));
																		opinion = (String) li.get(i + li.indexOf("OPINION"));
																		if (opinion == null) {
																			opinion = "";
																		}
																		if (state.equals("0")) {
																			state = "同意";
																		} else if (state.equals("1")) {
																			state = "不同意";
																		}
															%>
															<tr align="center">
																<td width="10"><%=num++%></td>
																<td align="center"><%=accountname2%></td>
																<td align="center"><%=checktime%></td>
																<td align="center"><%=state%></td>
																<td align="left"><%=opinion%></td>
															</tr>
															<%
																}
																}
															%>
														</table>
													</div>
												</td>
											</tr>
											<tr>
												<td align="right" colspan="8" height="60px">
												<input type="button" class="btn_c1" id="button2" value="附件管理" onclick="fujianguanli('<%=taskid%>')" />
													<input onclick="tijiao('<%=taskid%>')" type="button"
														class="btn_c1" id="button2" value="提交" />
													&nbsp;
													<input onclick="fanhui()" type="button" class="btn_c1"
														id="button2" value="取消" />
													&nbsp;
												</td>
											</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
			</div>
			</div>
			</td>
			</tr>
			</table>
			<input type="hidden" name="currentid" value="<%=currentid%>" />
			<input type="hidden" name="nextactionId" value="<%=nextactionId%>" />
			<input type="hidden" name="workFlowId" value="<%=id%>" />
			<input type="hidden" name="flowId" value="<%=flowId%>" />
			<input type="hidden" name="applyUserId" value="<%=applyUserId%>" />
			<input type="hidden" name="lastaction" value="<%=lastaction%>" />
			<input type="hidden" name="tasktype" value="<%=tasktype%>" />
			<input type="hidden" name="bohuijd" value="<%=bohuijd%>" />
			<input type="hidden" name="dblc_type" value="<%=dblc_type%>" />
		</form>
		<script type="text/javascript">
		window.onload = function(){
			var ssf1 ='<%=ssf%>';
			if(ssf1 !=2){
			$("#yc2").hide();
			$("#yc3").hide();
			$("#yc4").hide();
			$("#yc5").hide();
			$("#yc6").hide();
			$("#yc7").hide();
			$("#yc8").hide();
			$("#yc9").hide();
			$("#yc10").hide();
			$("#yc11").hide();
			$("#yc12").hide();
			}
else{
				$("#yc1").hide();
			}
			};
function fanhui() {
	window.close();
}
function showlist() {
	//window.open("dianbiaolist.jsp",'_blank','newwindow','height=350, width=630,top=1200,left=300,toolbar=no,menubar=no, scrollbars=yes, resizable=no,location=n o, status=no');
	window
			.open(
					'zhandianselect.jsp',
					'',
					'width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');

}
function pageRefsh() {
	document.form1.action = path + "/web/sdttWeb/jizan/adddianbiao.jsp";
	document.form1.submit();
}
var path = '<%=path%>';
var XMLHttpReq;
//XMLHttpReq = createXMLHttpRequest();
function createXMLHttpRequest() {
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

///////////////////////////////////////////////////////////
function sendRequest(url, para) {

	createXMLHttpRequest();

	XMLHttpReq.open("GET", url, true);

	if (para == "sheng") {
		XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
	} else if (para == "shi") {
		XMLHttpReq.onreadystatechange = processResponse_shi;
	} else if (para == "xian") {
		XMLHttpReq.onreadystatechange = processResponse_xian;
	} else if (para == "dbbm") {
		XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数			
	} else if (para == "zdmc") {
		XMLHttpReq.onreadystatechange = processResponse_zdmc;
	} else {
		XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
	}
	//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
	XMLHttpReq.send(null);
}
/////////////////////////////////////////////////////////////
// 处理返回信息函数
function processResponse() {

	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
			var res = XMLHttpReq.responseText;
			window.alert(res);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}

function processResponse_sheng() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态

		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");

			//var res = XMLHttpReq.responseText;

			updateShi(res);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}

function processResponse_shi() {

	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
			//var res = XMLHttpReq.responseText;
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			updateQx(res);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function processResponse_xian() {

	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
			//var res = XMLHttpReq.responseText;
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			//var ress=XMLHttpReq.responseXML.getElementsByTagName("res");
			updateZd(res);
			// updateUser(ress);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function processResponse_zdmc() {

	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
			//var res = XMLHttpReq.responseText;
			var res = XMLHttpReq.responseXML.getElementsByTagName("ress");
			//var ress=XMLHttpReq.responseXML.getElementsByTagName("res");
			updatezdmc(res);
			// updateUser(ress);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function processResponse_zdlx() {
	//alert("333");
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
			//var res = XMLHttpReq.responseText;
			var res = XMLHttpReq.responseXML.getElementsByTagName("ress");
			//   updateZdlx(res);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}

}
//站点类型
// function updateZdlx(res){

// 	var shilist = document.all.dbbm;
// 	shilist.options.length="0";
// 	shilist.add(new Option("请选择","0"));

// 	for(var i = 0;i<res.length;i+=2){
// 		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
// 	}
// }

function changeSheng() {
	var sid = document.form1.sheng.value;

	if (sid == "0") {
		var shilist = document.all.shi;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		//alert("11111");
		sendRequest(path + "/servlet/garea?action=sheng&pid=" + sid, "sheng");
	}
}
function changezdmc() {
	var sid = document.form1.xiaoqu.value;
	if (sid == "0") {
		var shilist = document.all.zdmc;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=quxian&pid=" + sid, "zdmc");
	}
}
function updateShi(res) {
	var shilist = document.all.shi;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
function changeCity() {
	var sid = document.form1.shi.value;

	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=shi&pid=" + sid, "shi");
	}
}
function updateQx(res) {
	var shilist = document.all.xian;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));

	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}

function changeCountry() {
	var sid = document.form1.xian.value;
	if (sid == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=xian&pid=" + sid, "xian");
	}
}
function changeDbbm() {
	var sid = document.form1.zdmc.value;
	if (sid == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=dbbm&pid=" + sid, "dbbm");
	}
}
function updateZd(res) {
	var shilist = document.all.xiaoqu;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
function updatezdmc(res) {
	var shilist = document.all.zdmc;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
function updateUser(ress) {
	var shilist = document.all.username;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));

	for ( var i = 0; i < ress.length; i += 2) {
		shilist.add(new Option(ress[i + 1].firstChild.data,
				ress[i].firstChild.data));
	}
}
/**供应商信息*/
function changeSupplier(){
	
	var codeReq = document.form1.gysmc.value;
	$.ajax({
		type: 'post',
		url: path + '/servlet/commonBeanServlet?action=gys',
		cache:false,
		data: {
			code: codeReq
		},
		dataType: 'json',
		success: function(data){
			if(data){
				var count = data[0];
				var code="",payeeName="",bankAccount="",subjectBank="",openingBank="",province="",city="";
				if(data.length > count + 1){
					code = data[count + data.indexOf("CODE")];
					payeeName = data[count + data.indexOf("PAYEE_NAME")];
					bankAccount = data[count + data.indexOf("BANK_ACCOUNT")];
					subjectBank = data[count + data.indexOf("SUBJECT_BANK")];
					openingBank = data[count + data.indexOf("OPENING_BANK")];
					province = data[count + data.indexOf("PROVINCE")];
					city = data[count + data.indexOf("CITY")];
				}
				document.form1.gysbm.value = code;
				document.form1.skfmc.value = payeeName
				document.form1.yhzh.value = bankAccount
				document.form1.ssyh.value = subjectBank
				document.form1.khyh.value = openingBank
				document.form1.zhsss.value = province
				document.form1.zhssshi.value = city
			}
			
		},
		error: function(){
			return;
		}
	});
}
function checkfplx(){
	var strfplx=document.form1.fplx.value;
	if(strfplx=='pjlx01'){
		$('#sl').attr("readonly","readonly");
		$('#sl').val("0.17")
	} else {
		$('#sl').removeAttr("readonly");
		$('#sl').val("0")
	}
}
function addTr(){
    var tr = $("#costTab tr").eq(1).clone();
    tr.id = new Date().getTime();
    var aHTML = '<a href="#" onclick="delTr(this)">删除</a>';
    tr.find("td:last").append(aHTML);
    tr.appendTo("#costTab");
}  
function delTr(obj){
	$(obj).closest('tr').remove();
}  
function openSupplier(){
	window.open("../jizan/Supplier.jsp", "newwindowSupp", "height=550, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}
function openCostCenter(obj){
	var gszt = document.form1.gszt.value;
	if(gszt=="0"){
		alert("请选择公司主体");
		return;
	}
	var tr = $(obj).parent().parent();
	var trId = tr.attr('id');
	var costWin = window.open("costCenter.jsp?gszt="+gszt+"&trId="+trId, "newwindowCost", "height=550, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no"); 
}
function fujianguanli(id){
	var dianbiaoName = document.getElementById("dbname").value;
	if(dianbiaoName == "" || dianbiaoName == null || dianbiaoName == undefined){ // "",null,undefined
	     alert("电表名称不能为空");
    }else{
         window.open("../jizan/fujianguanli.jsp?id="+id+"&name="+dianbiaoName, "newwindowCost", "height=550, width=1000, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
	}
}
document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
document.form1.zdmc.value = '<%=zdmc%>';
document.form1.dfzflx.value = '<%=dfzf%>';
document.form1.dbqyzt.value = '<%=dbqyzt%>';
document.form1.dbyt.value = '<%=dbyt%>';
document.form1.jffs.value = '<%=jffs%>';
document.form1.jldw.value = '<%=jldw%>';
document.form1.jfzq.value = '<%=jfzq%>';
document.form1.ydlx.value = '<%=ydlx%>';
document.form1.fplx.value = '<%=fplx%>';
document.form1.dwjslx.value = '<%=dwjslx%>';
document.form1.ydsx.value = '<%=ydsx%>';
</script>

	</body>

</html>

