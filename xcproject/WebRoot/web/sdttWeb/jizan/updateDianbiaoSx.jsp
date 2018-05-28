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
	String rolename = account.getRoleName();

	String id = request.getParameter("id");
	session.setAttribute("dbid", id);
	String zdid = "",sfyz="",ssf1="",ftbl="";
	String bz = request.getParameter("bz");
	SiteModifyBean form = new SiteModifyBean();
	String a = "", dbbm = "", shi = "0", xian = "0", xiaoqu = "0", zdmc = "0", dbmc = "", cssysj = "", beilv = "", csds = "", dfzf = "0";
	String dbId = "";
	String jzname="",jztype="",sszy="",dbyt="0",zdlx="",cssytime="",dbxh="",memo="",szdq="";
	String maxds="",dwjslx="0",isglf="",glbm="",zrr="",bzr="",ydsx="0",ydlx="0",jffs="0",jfzq="0",jldw="0",yhbh="";
	String dbqyzt="0",bgje="",mtllhd="",danjia="",gysmc="",gysbm="",skfmc="",yhzh="",ssyh="",khyh="",zhsss="",zhssshi="",fplx="0";
	String zzsl="",dbch="",dbscbs="";
	String is_cont="0",cont_type="0",zndb="0",isdblx="0";
	String cbzx="",cbzxbm="",production_prop="",bur_stand_propertion="",cont_id="",electric_supply_way="",total_electricity="",zhangqi="",zhz="";
	String gszt="",jiaoliu="",zhiliu="",ssf="",Tssf="",FTBL="",SFYZ="";
	String sql = "select d.* from dianbiao d where id="+ id + "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		db.connectDb();
		rs = db.queryAll(sql.toString());
		while (rs.next()) {
			ssf1 = rs.getString("ssf") == null ? "" : rs
					.getString("ssf");
			ftbl = rs.getString("ftbl") == null ? "" : rs
					.getString("ftbl");
			sfyz = rs.getString("sfyz") == null ? "" : rs
					.getString("sfyz");
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
			dbqyzt=rs.getString("dbqyzt");
			jldw=rs.getString("jldw");
			zdid=rs.getString("zdid");
			dbyt=rs.getString("dbyt");
			dbbm = rs.getString("dbbm") == null ? "" : rs
					.getString("dbbm");
			jztype=rs.getString("jztype")==null?"":rs.getString("jztype");
	 		sszy=rs.getString("sszy")==null?"":rs.getString("sszy");
	 		
	 		cssytime=rs.getString("cssytime")==null?"":rs.getString("cssytime");
	 		dbxh=rs.getString("dbxh")==null?"":rs.getString("dbxh");
	 		memo=rs.getString("memo")==null?"":rs.getString("memo");
	 		
	 		maxds=rs.getString("maxds")==null?"":rs.getString("maxds");
	 		dwjslx=rs.getString("dwjslx");
	 		isglf=rs.getString("isglf")==null?"":rs.getString("isglf");
	 		glbm=rs.getString("glbm")==null?"":rs.getString("glbm");
	 		zrr=rs.getString("zrr")==null?"":rs.getString("zrr");
	 		bzr=rs.getString("bzr")==null?"":rs.getString("bzr");
	 		ydsx=rs.getString("ydsx");
	 		ydlx=rs.getString("ydlx");
	 		jffs=rs.getString("jffs")==null?"":rs.getString("jffs");
	 		jfzq=rs.getString("jfzq");
	 		jldw=rs.getString("jldw")==null?"":rs.getString("jldw");
	 		yhbh=rs.getString("yhbh")==null?"":rs.getString("yhbh");
	 		bgje=rs.getString("bgje")==null?"":rs.getString("bgje");
	 		mtllhd=rs.getString("mtllhd")==null?"":rs.getString("mtllhd");
	 		danjia=rs.getString("danjia")==null?"":rs.getString("danjia");
	 		gysmc=rs.getString("gysmc")==null?"":rs.getString("gysmc");
	 		gysbm=rs.getString("gysbm")==null?"":rs.getString("gysbm");
	 		skfmc=rs.getString("skfmc")==null?"":rs.getString("skfmc");
	 		yhzh=rs.getString("yhzh")==null?"":rs.getString("yhzh");
	 		ssyh=rs.getString("ssyh")==null?"":rs.getString("ssyh");
	 		khyh=rs.getString("khyh")==null?"":rs.getString("khyh");
	 		zhsss=rs.getString("zhsss")==null?"":rs.getString("zhsss");
	 		zhssshi=rs.getString("zhssshi")==null?"":rs.getString("zhssshi");
	 		
	 		fplx=rs.getString("fplx");
	 		zzsl=rs.getString("zzsl")==null?"":rs.getString("zzsl");
	 		if(zzsl!="" && zzsl.length()>1){
	 		   if(zzsl.subSequence(0, 1).equals(".")){
		 		   zzsl = "0"+zzsl;
		 		}
	 		}
	 		
	 		dbch=rs.getString("dbch")==null?"":rs.getString("dbch");
	 		dbscbs=rs.getString("dbscbs")==null?"":rs.getString("dbscbs");
	 		
	 		is_cont=rs.getString("is_cont");
	 		cont_type=rs.getString("cont_type");
	 		zndb=rs.getString("zndb");
	 		isdblx=rs.getString("isdblx");
	 		cbzx=rs.getString("cbzx")==null?"":rs.getString("cbzx");
	 		cbzxbm=rs.getString("cbzxbm")==null?"":rs.getString("cbzxbm");
	 		production_prop=rs.getString("production_prop")==null?"":rs.getString("production_prop");
	 		bur_stand_propertion=rs.getString("bur_stand_propertion")==null?"":rs.getString("bur_stand_propertion");
	 		cont_id=rs.getString("cont_id")==null?"":rs.getString("cont_id");
	 		electric_supply_way=rs.getString("electric_supply_way")==null?"":rs.getString("electric_supply_way");
	 		total_electricity=rs.getString("total_electricity")==null?"":rs.getString("total_electricity");
	 		zhangqi=rs.getString("zq")==null?"":rs.getString("zq");
	 		zhz=rs.getString("zhz")==null?"":rs.getString("zhz");
	 		gszt = rs.getString("gszt")==null ? "" : rs.getString("gszt");
	 		jiaoliu = rs.getString("jiaoliu")==null ? "" : rs.getString("jiaoliu");
			zhiliu = rs.getString("zhiliu")==null ? "" : rs.getString("zhiliu");
	 		
	 		 System.out.println("jszq="+jfzq);
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
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
		</script>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
		</script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
		</script>
		<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<jsp:useBean id="flowBean" scope="page" class="com.noki.mobi.flow.javabean.FlowBean">
    </jsp:useBean>
		<title>电表修改</title>
		<script type="text/javascript">
		var ydsx='<%=ydsx%>';
		var path = '<%=path%>';
		function ismoney (str) {
			if (/^(([0-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(str))
				return true;
			return false;
		}
		function ismoneys (str) {
			if (/^(([0-9][0-9]*)|(([0]\.\d{1,4}|[1-9][0-9]*\.\d{1,4})))$/.test(str))
				return true;
			return false;
		}
$("#sel").change(function() {
var type=$("#sel").val();
if(type=="changedb"){
	 ydsx1= $("#ydsx").val();
	}
	});
function change(){
	var danjia='<%=danjia%>';
	var dbqyzt='<%=dbqyzt%>';
	var beilv='<%=beilv%>';
	var type=$("#sel").val();
	var ssf1='<%=ssf1%>';

	if(type=="changedb"){
	 ydsx1= $("#ydsx").val();
	}
	$.ajax({
             type: "POST",//请求方式
             url: path+"/servlet/workflow?action=selflow",
             data: {type: type},
             dataType: "json",success: function(data){
    			//console.log(data)
    			if(data){
					var count = data[0];
					var flowName = "", flowId = "0",actionId="",actionName = "", roleId="";
					if(data.length > count + 1){
						flowName = data[count + data.indexOf("FLOWNAME")];
						flowId = data[count + data.indexOf("FLOWID")];
						actionId = data[count + data.indexOf("ACTIONID")];
						actionName = data[count + data.indexOf("ACTIONNAME")];
						roleId = data[count + data.indexOf("ROLEID")];
					}
					document.form1.flowName.value = flowName;
					document.form1.flowId.value = flowId;
					document.form1.actionId.value = actionId;
					$("#nextActionName").html(actionName);
					
					//执行人
					var agcode= document.form1.xian.value;
					
					$.ajax({
			             type: "POST",//请求方式
			             url: path+"/servlet/workflow?action=selAccount",
			             data: {agcode: agcode, roleId:roleId},
			             dataType: "json",success: function(result){
			             	var scountArr = result[0];
				    			var accountid="",name="";
				    			var auditors="";
				    			for (var i = scountArr; i < result.length - 1; i += scountArr) {
										accountid = result[i+ result.indexOf("ACCOUNTID")];
										name =  result[i+ result.indexOf("NAME")];
										auditors+="<label><input type='radio' name='auditorid' value='"+accountid+"' checked/>"+name+"</label>";
								}
								$("#radioDiv").html(auditors);
			             }
			        });
			    }
          }
	 });
	
	
	if(type=="changedb"){ 
		$("#strTr").attr("style", "display:none;");
		$("#strTr2").attr("style", "display:display;");
		$("#td3").attr("style", "display:display;");
		$("#td4").attr("style", "display:display;");
		
		if(ssf1!=2){
			$("#as1").hide();
			$("#as2").hide();
			$("#as3").hide();
			$("#as4").hide();
			$("#as5").hide();
			$("#as6").hide();
			$("#as7").hide();
			$("#as8").hide();
			$("#as9").hide();
			$("#as10").hide();
			$("#as11").hide();
		}else{
				$("#sa2").hide();
			}
	}
	else{
	$("#strTd").html("");
	$("#strTd2").html("");
	//document.form1.dbqyzt.value = '<%=dbqyzt%>';
	//alert('<%=dbqyzt%>') 
	$("#strTr").attr("style", "display:display;");
	$("#strTr2").attr("style", "display:none;");
	$("#td3").attr("style", "display:none;");
	$("#td4").attr("style", "display:none;");
	var html="";
	var html2="";
	if(type=="danjia"){
		html="<input name='danjia' value='"+danjia+"' disabled='disabled' style='box-sizing: border-box; width: 130px' />";
		html2="<input id='type_sel' name='type_sel'  style='box-sizing: border-box; width: 130px' />";
	}else if(type=="beilv"){
		html="<input name='danjia' value='"+beilv+"' disabled='disabled' style='box-sizing: border-box; width: 130px' />";
		html2="<input id='type_sel' name='type_sel'  style='box-sizing: border-box; width: 130px' />";
	}else if(type=="dbqyzt"){
		html="<select name='dbzt' id='dbzt' disabled='disabled' style='box-sizing:border-box;width:130px'  class='selected_font'>"
					         		<%
						         	ArrayList dfzflx = new ArrayList();
						         	dfzflx = ztcommon.getSelctOptions("dbzt");
						         	if(dfzflx!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)dfzflx.get(0)).intValue();
						         		for(int i=cscount;i<dfzflx.size()-1;i+=cscount)
					                    {
					                    	code=(String)dfzflx.get(i+dfzflx.indexOf("CODE"));
					                    	name=(String)dfzflx.get(i+dfzflx.indexOf("NAME"));
					                    %>
					                      +"<option value='<%=code%>' <%=code.equals(dbqyzt)?"selected=''selected":"" %>><%=name%></option>"
					                    <%}
						         	}
						         %>
					         	+"</select>";
		 html2="<select name='dbqyzt' id='dbqyzt' style='box-sizing:border-box;width:130px'  class='selected_font'><option value='0'>请选择</option>"
					         		<%
						         	ArrayList dfzflx2 = new ArrayList();
						         	dfzflx2 = ztcommon.getSelctOptions("dbzt");
						         	if(dfzflx2!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)dfzflx2.get(0)).intValue();
						         		for(int i=cscount;i<dfzflx2.size()-1;i+=cscount)
					                    {
					                    	code=(String)dfzflx2.get(i+dfzflx2.indexOf("CODE"));
					                    	name=(String)dfzflx2.get(i+dfzflx2.indexOf("NAME"));
					                    %>
					                    +"<option value='<%=code%>'><%=name%></option>"
					                    <%}
						         	}
						         %>
					         	+"</select>";
	}
	$("#strTd").append(html);
	$("#strTd2").append(html2);
	}
}
function tijiao(){

var ssfsf='<%=ssf1%>';
	var path = '<%=path%>';
	var id="<%=id %>";
	var ssf="<%=ssf %>";
	var type=$("#sel").val();
	var type_se="";
	var types=$("#dbqyzt").val();
	var flowId=$("#flowId").val();
	var auditorid=$('input:radio[name="auditorid"]:checked').val();
	if(checkNotSelected(document.form1.sel,"需要修改项")){
		if(type=="danjia"){
			type_se=$("#type_sel").val();
			if(!ismoneys(type_se)){
				alert("单价必须为数字，最多保留4位小数！！");
				debugger;
				return false;
			}	
		}
		if(type=="beilv"){
			type_se=$("#type_sel").val();
			if(!ismoney(type_se)){
				alert("倍率必须为数字，最多保留2位小数！！");
				return false;
			}
		}	
		if(type=="dbqyzt"){
			if(types=="0"){
				alert("电表使用状态未选择！");
				return false;
			}else{
				type_se=$("#dbqyzt").val();
			}
		}
		if(type=="changedb"){
		if(ssfsf=="1"){
		if(!(//checkNotnull(document.form1.wlbm2,"物理编码")&&
				checkNotnull(document.form1.dbname2,"电表名称")&&
				checkNotnull(document.form1.maxds,"电表最大读数")&&checkNotnull(document.form1.csds,"启用时读数")&&
				checkNotSelected(document.form1.ydsx,"用电属性")&&
				checkNotSelected(document.form1.dfzflx,"缴费方式")&&
				checkNotSelected(document.form1.jfzq,"缴费周期")&&checkNotSelected(document.form1.jffs,"计费方式")&&
			     checkNotnull(document.form1.beilv2,"倍率")&&
			    checkNotSelected(document.form1.dbqyzt2,"状态")&&
				checkNotnull(document.form1.dj2,"单价")&& 
				checkNotSelected(document.form1.fplx,"发票类型"))){
				return false;
		    }
		}else{
			if(!(//checkNotnull(document.form1.wlbm2,"物理编码")&&
				checkNotnull(document.form1.dbname2,"电表名称")&&
				checkNotnull(document.form1.maxds,"电表最大读数")&&checkNotnull(document.form1.csds,"启用时读数")&&
				checkNotSelected(document.form1.ydsx,"用电属性")&&
				checkNotSelected(document.form1.dfzflx,"缴费方式")&&
				checkNotSelected(document.form1.jfzq,"缴费周期")&&checkNotSelected(document.form1.jffs,"计费方式")&&
			     checkNotnull(document.form1.beilv2,"倍率")&&
			    checkNotSelected(document.form1.dbqyzt2,"状态")&&
				checkNotnull(document.form1.dj2,"单价")&& 
				checkNotSelected(document.form1.fplx,"发票类型")&&
				checkNotnull(document.form1.production_prop,"生产占比")&&
				checkNotnull(document.form1.bur_stand_propertion,"占局站比例")&&
				checkNotSelected(document.form1.electric_supply_way,"供电方式")&&
				checkNotSelected(document.form1.isdblx,"电表类型")&&
				checkNotSelected(document.form1.dbyt,"电表用途")&&
				checkNotSelected(document.form1.gszt,"公司主体"))){
				/* checkNotnull(document.form1.costName,"成本中心")&&
				checkNotSelected(document.form1.businessType,"业务类型")&&
				checkNotSelected(document.form1.elecProperty,"成本中心-用电属性")&&
				checkNotSelected(document.form1.projectCode,"预算项目")&&
				checkNotnull(document.form1.shareRatio,"分摊系数"))){
				*/
				
				return false;
		    }
			var zrr=document.form1.zrr.value;//责任人
			var ca = zrr.substr(zrr.length-3);

			var bzr=document.form1.bzr.value;//报账人
			var caa = bzr.substr(bzr.length-3);
			
			if(ca != null && ca != ""){
				if(ca!="@SD"){
					alert("责任人应填写以 @SD 结尾的财辅人力账号(注意英文大小写)!");
					return ;
				}
			}else{
				alert("责任人应填写以 @SD 结尾的财辅人力账号(注意英文大小写)!");
				return ;
			}
		
			if(caa != null && caa != ""){
				if(caa!="@SD"){
					alert("报账人应填写以 @SD 结尾的财辅人力账号(注意英文大小写)!");
					return ;
				}
			}else{
				alert("报账人应填写以 @SD 结尾的财辅人力账号(注意英文大小写)!");
				return ;
			}
			
			 //校验分摊系数--xk
		    var ratioSum = 0;
			var rationVal = 0;
			var trs = new Array();
			trs = $("#costTab").find("tr").length;

			var costVal = "";
			var i = 1;
			while(i < trs){
				//获取
				var val = $("#costTab tr:eq("+i+") td:eq(6)").find("input[type='text']:eq(0)").val();
				//获取成本中心
				var costSel =  $("#costTab tr:eq("+i+") td:eq(0)").find("input[type='text']:eq(0)").val();
				var costSel1 =  $("#costTab tr:eq("+i+") td:eq(1)").find("option:selected").text();;
				var costSel2 =  $("#costTab tr:eq("+i+") td:eq(2)").find("option:selected").text();
				var costSel3 =  $("#costTab tr:eq("+i+") td:eq(3)").find("input[type='text']:eq(0)").val();
				var costSel4 =  $("#costTab tr:eq("+i+") td:eq(4)").find("option:selected").text();
				var costSel5 =  $("#costTab tr:eq("+i+") td:eq(5)").find("option:selected").text();
				if(costSel == "0" || costSel == 0 || costSel == ""){
					alert("请选择成本中心");
					return;
				}
				if(costSel1 == "请选择"  || costSel1 == ""){
					alert("请选择业务类型");
					return;
				}
				if(costSel2 == "请选择"  || costSel2 == ""){
					alert("请选择用电属性");
					return;
				}
				if(costSel3 == "0" || costSel3 == 0 || costSel3 == ""){
					alert("请选择预算责任中心");
					return;
				}
				if(costSel4 == "请选择"  || costSel4 == ""){
					alert("请选择预算项目");
					return;
				}
				if(costSel5 == "请选择"  || costSel5 == ""){
					alert("请选择是否实际分表");
					return;
				}
				//分摊系数
				if(val != ""){
					ratioSum = parseFloat(ratioSum) + parseFloat(val);
				}
				i++;
			}
			
			if(ratioSum!=1){
				alert("分摊系统总和必须等于1");
			    return;
			}
		    }
		}
		
			 
		
		if(flowId==0){
			alert("请选择流程！");
		    return false;
		}
		var auditorid=$("#radioDiv_").val();
		   		//alert("JJJJJ"+auditorid);
		      	if(auditorid == "" || auditorid == null || auditorid == undefined || auditorid == 0){ // "",null,undefined
	    		   				alert("执行人不能为空!");
		   		 return false;
		   						}
		if(type=="danjia"||type=="beilv"){
			type_se=$("#type_sel").val();
		}else if(type=="dbqyzt"){
			type_se=$("#dbqyzt").val();
		}
//验证文件是否上传
	//上传人ID
	var scrid_wenjian = '<%=loginId%>';
	//电表名称 
	var dianbiaoid_wenjian = '<%=id%>';	
	//流程编码
	var liuchengId_wenjian = document.getElementById("flowName_").value;
	
		$.ajax({
   	 		//直接"post"或者"get",不需要"doPost","doGet"，该函数到后端接收缓冲区会自动匹配
        	type : "post",      
       		//servlet文件名为Calculator，需要提前在web.xml里面注册
        	url :"<%=path%>/servlet/YanZhengWenJianServlet",
        	dataType : "text",  //数据类型，可以为json，xml等等，自己百度
        	data :
        	{
            	"scrid":scrid_wenjian,				//上传人ID
            	"dianbiaoid":dianbiaoid_wenjian,	//电表ID
             	"liuchengid":liuchengId_wenjian,    //流程ID
        	},
        	success : function(Result)
        	{
        		
               //Result为后端post函数传递来的数据，这里写结果操作代码
               if(Result == "1"){
            		if(confirm("文件已上传,可以提交修改！请确认要修改的信息是否正确！")){
        			$("#dbqyzt3").removeAttr("disabled");
        			$("#zdmc").removeAttr("disabled");
        			document.form1.action=path+"/servlet/dianbiao?action=changeType&type="+type+"&id="+id+"&type_se="+type_se+"&ssf="+ssf;
        			document.form1.submit();
        			showdiv("请稍等..............");
        		}
               }else{
               		alert("未上传文件无法提交修改!");
               }
        	},
        	error : function(Result)
        	{
             	alert("未上传文件无法提交修改!");
       		}
        	
    	});

//验证文件是否上传END
//		if(confirm("您将要修改电表信息！确认信息正确！")){
//			$("#dbqyzt3").removeAttr("disabled");
//			$("#zdmc").removeAttr("disabled");
//			document.form1.action=path+"/servlet/dianbiao?action=changeType&type="+type+"&id="+id+"&type_se="+type_se+"&ssf="+ssf;
//			document.form1.submit();
//			showdiv("请稍等..............");
//		}
   }
}			
function select(){	//作废
        	$("#radioDiv").empty();
        	var flowId=$("#flowId").val();
        	
        	var agcode= document.form1.xian.value;
        	$.ajax({
	                type: "POST",//请求方式
	                url: path+"/servlet/workflow?action=getActionName",
	                data: {flowId:flowId},
	                dataType: "json",success: function(result){
				    	$("#nextActionName").html(result);
				    	$.ajax({
	                type: "POST",//请求方式
	                url: path+"/servlet/workflow?action=getAuditor",
	                data: {flowId:flowId,agcode:agcode},
	                dataType: "json",success: function(result){
				    	var  resultArr = eval(result);
				    	var html = "选择执行人：";
				    		if(resultArr!=null && resultArr.length>0){
				    			var scountArr = resultArr[0];
				    			var accountid="",name="";
				    			for (var i = scountArr; i < resultArr.length - 1; i += scountArr) {
															accountid = resultArr[i
																	+ resultArr.indexOf("ACCOUNTID")];
															name =  resultArr[i
																	+ resultArr.indexOf("NAME")];
															html+="<label><input type='radio' name='auditorid' value='"+accountid+"' checked/>"+name+"</label>";
				    		}
				    		
				    	}
				    	$("#radioDiv").append(html);
				    	$("#tongyiTr").attr("style", "display:display;");
				    }
	            });
				    }
	            });
        	}
</script>
	</head>
	
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
								电表属性修改
							</div>
							<div class="content01_1">
								<table width="100px" border="0" cellpadding="2" cellspacing="0"
									class="tb_select">
									<tr>
										<td align="right" width="100px">
											城市
										</td>
										<td width="100px">
											<select name="shi"  disabled="disabled" style="background-color: #EBEBE4;box-sizing:border-box;width:130px" class="selected_font"
												onchange="changeCity()">
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
											</select><span style="color: #FF0000; font-weight: bold">*</span>
											&nbsp;
										</td>
										<td align="right" width="100px">
											区县
										</td>
										<td width="100px">
											<select name="xian" disabled="disabled" style="background-color: #EBEBE4;box-sizing:border-box;width:130px" class="selected_font"
												onchange="changeCountry()">
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
											</select><span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											乡镇
										</td>
										<td width="100px">
											<select disabled="disabled" style="background-color: #EBEBE4;box-sizing:border-box;width:130px" name="xiaoqu" id="xiaoqu" onchange="changezdmc()"
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
											</select><span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											站点名称
										</td>
										<td width="180px">
											<select disabled="disabled" style="background-color: #EBEBE4;box-sizing:border-box;width:130px" name="zdmc" id="zdmc" onchange="changeDbbm()"
												class="selected_font">
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
											</select><span style="color: #FF0000; font-weight: bold">*</span>
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
											<input disabled="disabled"  type="text" name="wlbm" value="<%=dbbm %>" style="box-sizing:border-box;width:130px"/><span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											电表名称
										</td>
										<td width="60px">
											<input disabled="disabled"  type="text" id="dbname" name="dbname" value="<%=dbmc %>" style="box-sizing:border-box;width:130px"/><span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									<td align="right" width="100px">
											需要修改项
										</td>
										<td width="25%">
											<select id="sel" onchange="change()"  name="sel"
												style="box-sizing: border-box; width: 130px">
												<option value="0">
													请选择
												</option>
												<option value="danjia">
													单价
												</option>
												<option value="beilv">
													倍率
												</option>
												<option value="dbqyzt">
													电表使用状态
												</option>
												<option value="changedb">
													更换电表
												</option>
											</select><span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" id="td4" style="display: none" width="100px">
											状态
										</td>
										<td width="100px" id="td3" style="display: none">
											<select name="dbqyzt3" id="dbqyzt3" disabled="disabled" style="box-sizing:border-box;width:130px;" class="selected_font" >
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
					                    <option value="<%=code%>"  <%=name.equals("停用")?"selected=''selected":"" %>><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
											
										</td>
									</tr>
									<tr id="strTr" style="display: none">
										<td align="right" width="100px" >
											原值
										</td>
										<td id="strTd"></td>
										<td align="right" width="100px" >
											修改为
										</td>
										<td id="strTd2"></td>									
									</tr>
									<tr id="strTr2" style="display: none">
										<td colspan="8">
											<table>
												<tr bgcolor="F9F9F9">
													<td height="19" colspan="4">
														<img src="../../images/v.gif" width="15" height="15" />
														新增电表信息
													</td>
												</tr>
												<tr>
										<td align="right" width="100px">
											用户编号
										</td>
										<td width="100px">
											<input type="text" name="yhbh" value="<%=yhbh%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											电表名称
										</td>
										<td width="60px">
											<input type="text" name="dbname2" 
												style="box-sizing: border-box; width: 130px" />
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											电表最大读数
										</td>
										<td width="100px">

											<input type="text" name="maxds" value="<%=maxds %>"  style="box-sizing:border-box;width:130px"/><span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<!--<td align="right" width="100px">倍率</td>
							<td width="25%"><input type="text" name="beilv" value='<%=beilv%>' class="selected_font3"/></td>
							-->
										<td align="right" width="100px">
											启用时读数
										</td>
										<td width="100px">
											<input type="text" name="csds" value="<%=csds%>"
												class="selected_font3"
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
												style="box-sizing: border-box; width: 130px" />
												<span style="color: #FF0000; font-weight: bold">*</span>
									
										</td>
									
										<td align="right" width="100px">
											报账人
										</td>
										<td width="100px">
											<input type="text" name="bzr" value="<%=bzr%>"
												style="box-sizing: border-box; width: 130px" />
																				<span style="color: #FF0000; font-weight: bold">*</span>
									
										</td>
										<td align="right" width="100px">
											用电属性
										</td>
										<td width="100px">
											<select name="ydsx" id="ydsx"
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
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											缴费方式
										</td>
										<td width="100px">
											<select name="dfzflx"
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList fkfs = new ArrayList();
													fkfs = ztcommon.getSelctOptions("FKFS");
													if (fkfs != null) {
														String code = "", name = "";
														int cscount = ((Integer) fkfs.get(0)).intValue();
														for (int i = cscount; i < fkfs.size() - 1; i += cscount) {
															code = (String) fkfs.get(i + fkfs.indexOf("CODE"));
															name = (String) fkfs.get(i + fkfs.indexOf("NAME"));
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
											<tr>
										<td align="right" width="100px">
											缴费周期
										</td>
										<td width="100px">
											<select name="jfzq"
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
											<select name="jffs"
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
											<input type="text" name="beilv2" value='<%=beilv%>'
												style="box-sizing: border-box; width: 130px"/>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											状态
										</td>
										<td width="100px">
											<select name="dbqyzt2" style="box-sizing:border-box;width:130px;" class="selected_font" >
					         		<option value="0">请选择</option>
					         		<%
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
											<input type="text" name="dj2" value="<%=danjia%>"
												style="box-sizing: border-box; width: 130px"/>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
											<td align="right" width="100px">
											发票类型
										</td>
										<td width="100px">
											<select name="fplx" onchange="checkfplx()"
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
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											生产占比(%)
										</td>
										<td>
											<input type="text" name="production_prop"
												value="<%=production_prop%>"
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
												<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									</tr>
									<tr>
									
									<td align="right" width="100px">
											占局站比例(%)
										</td>
										<td>
											<input type="text" name="bur_stand_propertion"
												id="bur_stand_propertion" value="<%=bur_stand_propertion%>"
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									<td align="right" width="100px">
											电表类型
										</td>
										<td>
											<select name="isdblx"
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

											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											供电方式
										</td>
										<td>
											<select name="electric_supply_way"
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

											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											电表用途
										</td>
										<td width="100px">
											<select name="dbyt"
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
									</tr>
									<tr id = "sa2">
										<td align="right" width="100px">
											分摊比例（%）
										</td>
										<td width="100px">
											<input type="text" id="ftbl" name="ftbl" value="<%=ftbl %>"
												style="box-sizing: border-box; width: 130px"
												
												class="selected_font3" />
												<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											税负因子（%）
										</td>
										<td width="100px">
											<input type="text" id="sfyz" name="sfyz" value="<%=sfyz %>"
												style="box-sizing: border-box; width: 130px"
												
												class="selected_font3" />
												<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										
										</tr>
									<tr id="as1">
										<td align="right" width="100px">
											是否有管理费
										</td>
										<td width="100px">
											<select name="isglf"
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
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											用电类型
										</td>
										<td width="100px">
											<select name="ydlx"
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
										<td align="right" width="100px">
											对外结算类型
										</td>
										<td width="100px">
											<select name="dwjslx"
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
									</tr>
								
									<tr id="as2">
										<td align="right" width="100px">
											计量单位
										</td>
										<td width="100px">
											<select name="jldw"
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
											物理编码
										</td>
										<td width="60px">
											<input type="text" name="wlbm2" 
												style="box-sizing: border-box; width: 130px" /><%--
											<span style="color: #FF0000; font-weight: bold">*</span>
										--%></td>
										<td align="right" width="100px">
											包干金额
										</td>
										<td width="100px">
											<input type="text" name="bgje" value="<%=bgje%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											每天理论耗电量
										</td>
										<td width="100px">
											<input type="text" name="mtllhd" value="<%=mtllhd%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
									</tr>
									<tr id="as3">
										
										<td align="right" width="100px">
											供应商名称
										</td>
										<td width="100px">
										     <%-- <select name="gysmc" onchange="changeSupplier()" style="box-sizing: border-box; width: 130px">
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
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
									</tr>
									<tr id="as4">
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
									<tr id="as5">
										
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
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											是否有合同
										</td>
										<td>
											<select name="is_cont"
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
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
									</tr>
									<tr id="as6">
										
										<td align="right" width="100px">
											合同类型
										</td>
										<td>
											<select name="cont_type"
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
										<td align="right" width="100px">
											账期
										</td>
										<td>
											<input type="text" name="zhangqi" value="<%=zhangqi%>"
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
										
										<td align="right" width="100px">
											直流功率
										</td>
										<td>
											<input type="text" name="zhiliu" value="<%=zhiliu%>"
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											是否智能电表
										</td>
										<td>
											<select name="zndb"
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
									</tr>
									<tr id="as7">
										
										<td align="right" width="100px">
											交流功率
										</td>
										<td>
											<input type="text" name="jiaoliu" value="<%=jiaoliu%>"
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
										<td align="right" width="100px">
											区县分公司
										</td>
										<td>
											<input type="text" name="cbzx" value="<%=cbzx%>"
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
												<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											成本中心编码
										</td>
										<td>
											<input type="text" name="cbzxbm" value="<%=cbzxbm%>"
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
												<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											核定电量
										</td>
										<td>
											<input type="text" name="total_electricity"
												value="<%=total_electricity%>"
												style="box-sizing: border-box; width: 130px"
												class="selected_font3" />
										</td>
									</tr>
									<tr id="as8">
										
										
										</td>
										
									</tr>
									<tr id="as9" bgcolor="F9F9F9">
										<td height="19" colspan="4">
											<img src="../../images/v.gif" width="15" height="15" />
											成本中心信息&nbsp;&nbsp;&nbsp;&nbsp;（如需填写分摊系数时，分摊系数总和必须等于1）
										</td>
									</tr>
									<tr id="as10">
										<td align="right" width="100px">
											公司主体
										</td>
										<td width="100px" colspan="8">
										   <select name="gszt" style="width: 250px;">
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
									<tr id="as11">
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
									            if(id != null && !id.isEmpty()){
									             	AccountingDao dao = new AccountingDao();
										            ArrayList list = dao.getAccounting(id);
										            if(list !=null && list.size()>0){
										            	int count = ((Integer)list.get(0)).intValue();
										            	String costCode="",businessType="",elecProperty="",dutyCode="",projectCode="",isRealSubAmmeter="",shareRatio="";
											            String costName="", dutyName="";
										            	for(int k=count; k<list.size()-1; k+=count){
											            	//COST_CODE,BUSINESS_TYPE,ELEC_PROPERTY,DUTY_CODE,PROJECT_CODE,IS_REAL_sub_AMMETER,SHARE_RATIO
											            	costCode = (String)list.get(k + list.indexOf("COST_CODE"));
											            	costName = (String)list.get(k + list.indexOf("COST_NAME"));
											            	businessType = (String)list.get(k + list.indexOf("BUSINESS_TYPE"));
											            	elecProperty = (String)list.get(k + list.indexOf("ELEC_PROPERTY"));
											            	dutyCode = (String)list.get(k + list.indexOf("DUTY_CODE"));
											            	dutyName = (String)list.get(k + list.indexOf("DUTY_NAME"));
											            	projectCode = (String)list.get(k + list.indexOf("PROJECT_CODE"));
											            	isRealSubAmmeter = (String)list.get(k + list.indexOf("IS_REAL_SUB_AMMETER"));
											            	shareRatio = (String)list.get(k + list.indexOf("SHARE_RATIO"));
											  %>
											 <tr bgcolor="#FFF" id="editCost">
									            <td>
									           		<%--<select name="costCode" style="box-sizing: border-box; width: 130px" onchange="changeCostCenter()">
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
									           		</select>--%>
									           		<input type="text" name="costName" value="<%=costName %>" style="box-sizing: border-box; width: 130px" onclick="openCostCenter(this)" readonly="readonly"/>
									           		<span style="color: #FF0000; font-weight: bold">*</span>
									           		<br/>
									           		<input type="text" name="costCode" value="<%=costCode %>" style="box-sizing: border-box; width: 130px" readonly="readonly"/>
									          <span style="color: #FF0000; font-weight: bold">*</span>
									            </td>
									            <td>
									           		<select name="businessType" style="box-sizing: border-box; width: 100px">
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
											   		<span style="color: #FF0000; font-weight: bold">*</span>
									            </td>
									            <td>
									           		<select name="elecProperty" style="box-sizing: border-box; width: 100px">
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
											   		<span style="color: #FF0000; font-weight: bold">*</span>
									            </td>
									            <td>
									           		<%--<select name="dutyCode" style="box-sizing: border-box; width: 130px">
														<option value="0">请选择</option>
														<%
															ArrayList dutylist = new ArrayList();
														    dutylist = commBean.getBudgetDutyCenter();
															if (dutylist != null) {
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
											   		</select>--%>
											   		<input type="text" name="dutyName" value="<%=dutyName %>" style="box-sizing: border-box; width: 130px" readonly="readonly"/>
											   		<br/>
											   		<input type="text" name="dutyCode" value="<%=dutyCode %>" style="box-sizing: border-box; width: 130px" readonly="readonly"/>
									           <span style="color: #FF0000; font-weight: bold">*</span>
									            </td>
									            <td>
									           		<select name="projectCode" style="box-sizing: border-box; width: 130px">
														<option value="0">请选择</option>
														<%
															ArrayList projectlist = new ArrayList();
															projectlist = commBean.getBudgetProject();
															if (projectlist != null) {
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
											   		<span style="color: #FF0000; font-weight: bold">*</span>
									            </td>
									            <td>
									           		<select name="isRealSubAmmeter" style="box-sizing: border-box; width: 100px">
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
											   		<span style="color: #FF0000; font-weight: bold">*</span>
									            </td>
									            <td>
									           	  <input type="text" name="shareRatio" value="<%=shareRatio %>" onkeyup="this.value=this.value.replace(/[^\d\.]/g,'')" style="box-sizing: border-box; width: 100px"/>
									        <span style="color: #FF0000; font-weight: bold">*</span>
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
									           		<%--<select name="costCode" style="box-sizing: border-box; width: 173px" onchange="changeCostCenter(this)">
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
									           		</select>--%>
									           		<input type="text" name="costName" style="box-sizing: border-box; width: 130px" onclick="openCostCenter(this)" readonly="readonly"/>
									           		<span style="color: #FF0000; font-weight: bold">*</span>
									           		<br/>
									           		<input type="text" name="costCode" style="box-sizing: border-box; width: 130px" readonly="readonly"/>
									           <span style="color: #FF0000; font-weight: bold">*</span>
									            </td>
									            <td>
									           		<select name="businessType" style="box-sizing: border-box; width: 75px">
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
											   		<span style="color: #FF0000; font-weight: bold">*</span>
									            </td>
									            <td>
									           		<select name="elecProperty" style="box-sizing: border-box; width: 75px">
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
											   		<span style="color: #FF0000; font-weight: bold">*</span>
									            </td>
									            <td>
									           		<%--<select name="dutyCode" style="box-sizing: border-box; width: 173px">
														<option value="0">请选择</option>
														<%
															ArrayList dutylist = new ArrayList();
														    dutylist = commBean.getBudgetDutyCenter();
															if (dutylist != null) {
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
											   		</select>--%>
											   		<input type="text" name="dutyName" style="box-sizing: border-box; width: 130px" readonly="readonly"/>
											   		<br/>
											   		<input type="text" name="dutyCode" style="box-sizing: border-box; width: 130px" readonly="readonly"/>
									          <span style="color: #FF0000; font-weight: bold">*</span>  </td>
									            <td>
									           		<select name="projectCode" style="box-sizing: border-box; width: 75px">
														<option value="0">请选择</option>
														<%
															ArrayList projectlist = new ArrayList();
															projectlist = commBean.getBudgetProject();
															if (projectlist != null) {
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
											   		<span style="color: #FF0000; font-weight: bold">*</span>
									            </td>
									            <td>
									           		<select name="isRealSubAmmeter" style="box-sizing: border-box; width: 75px">
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
											   		<span style="color: #FF0000; font-weight: bold">*</span>
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
											
											
											
											
											</table>
										</td>
									</tr>
									<tr bgcolor="F9F9F9">
										<td height="19" colspan="4">
											<img src="../../images/v.gif" width="15" height="15" />
											流程信息
										</td>
									</tr>
									<%--
									   ArrayList actionlist = new ArrayList();
									    actionlist = flowBean.getAction("flowtype01", "1"); //电表审核 step1
									    int acount = ((Integer) actionlist.get(0)).intValue();
									     String flowName = "", flowId = "0",actionId="",actionName = "", roleId="";
									    if(actionlist.size() > (acount+1)){
									        flowId = (String) actionlist.get(acount + actionlist.indexOf("FLOWID"));
									        flowName = (String) actionlist.get(acount + actionlist.indexOf("FLOWNAME"));
									        actionId = (String) actionlist.get(acount + actionlist.indexOf("ACTIONID"));
											actionName = (String) actionlist.get(acount + actionlist.indexOf("ACTIONNAME"));
											roleId = (String) actionlist.get(acount + actionlist.indexOf("ROLEID"));
									    }
									
									 --%>
								<tr id="Clean">
									<td align="right" width="100px">流程名称</td>
									<td width="100px" colspan="8">
										<%--   <input list="itemlist" type="text" name="flowName" value="<%=flowName%>" style="box-sizing: border-box; width: 130px"/>&nbsp; --%>
										<select name="flowName_" id="flowName_" onchange="selectFlow()">
											<option value="0">请选择</option>
											<%ArrayList flowlist = new ArrayList();
											System.out.println("根据fff："+rolename);
											if(rolename.contains("市")) {//step2
												flowlist = flowBean.getAllFlows("2","%市%");
												
												}  else if(rolename.contains("县")){//其他
													flowlist = flowBean.getAllFlows2("2","%市%");
													
												} else{//其他
													flowlist = flowBean.getAllFlowsfujian("2");
													
												}
												String flowName = "", flowId = "0";
												if (flowlist.size()!=0) {
													int countColum = ((Integer) flowlist.get(0)).intValue();
													for (int i = countColum; i < flowlist.size() - 1; i += countColum) {
														flowId = (String) flowlist.get(i
																+ flowlist.indexOf("FLOWID"));
														flowName = (String) flowlist.get(i
																+ flowlist.indexOf("FLOWNAME"));
											%>
											<option value="<%=flowId%>"><%=flowName%></option>

											<%
												}
												}
											%>

									</select> <span style="color: #FF0000; font-weight: bold">*</span> <input
										type="hidden" name="flowId" id="flowId" /></td>
								</tr>
								<tr>
									<td align="right" width="100px">下一节点</td>
									<td width="100px" colspan="8"><span id="actionName"></span>
										<input type="hidden" name="actionId" id="actionId" />
									</td>
								</tr>
								<tr>
									<td align="right" width="100px">执行人</td>
									<td width="100px" colspan="8">
										<div id="radioDiv_"></div></td>
								</tr>
									<%--
									<tr>
										<td align="right" width="100px">
											选择流程
										</td>
										<td>
											<select name="flowId" id="flowId" onchange="select()">
												<option value="0">
													请选择
												</option>
												<%
												ArrayList flowlist = new ArrayList();
												flowlist = flowBean.getAllFlow();
											    String flowName = "", flowId = "0";
												if (flowlist != null) {
													int countColum = ((Integer) flowlist.get(0)).intValue();
													for (int i = countColum; i < flowlist.size() - 1; i += countColum) {
														flowId = (String) flowlist.get(i + flowlist.indexOf("FLOWID"));
														flowName = (String) flowlist.get(i
																	+ flowlist.indexOf("FLOWNAME"));
												%>
												<option value="<%=flowId%>"><%=flowName%></option>

												<%
													}
													}
												%>

											</select><span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									</tr>
									<tr id="tongyiTr" style="display: none">
										<td colspan="8">
											<div>
												下一节点：<span id="nextActionName"></span></div>
											<div id="radioDiv">
											</div>
										</td>
									</tr>--%>
									<tr>
										<td align="right" colspan="8" height="60px">
										<input type="button" class="btn_c1" id="button2" value="附件管理" onclick="fujianguanli('<%=id%>')" />
										<input name="button2" type="button" class="btn_c1" id="button2" onclick="tijiao()" value="提交审批" />&nbsp; 
										<input onclick="fanhui()" type="button" class="btn_c1" id="button2" value="返回" />&nbsp; 
									</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<input type="hidden" name="zdid" value="" />
			<input type="hidden" id = "wenjianyanzheng" name="wenjianyanzheng" value="" />
			<script type="text/javascript">
			function fanhui(){
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
		//$('#sl').attr("readonly","readonly");
		$('#sl').val("0.17");
	} else {
		$('#sl').removeAttr("readonly");
		$('#sl').val("0");
	}
}
function addTr(){
    var tr = $("#costTab tr").eq(1).clone();
    tr.attr('id', new Date().getTime());
    var aHTML = '<a href="#" onclick="delTr(this)">删除</a>';
    tr.find("td:last").append(aHTML);
    tr.appendTo("#costTab");
}  
function delTr(obj){
	$(obj).closest('tr').remove();
}  
function openSupplier(){
	window.open("Supplier.jsp", "newwindowSupp", "height=550, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
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
		function selectFlow(){
		    var flowid = $("#flowName_").val();
		     var ydsx1 = $("#ydsx").val();
		    console.log(flowid);
		    $.ajax({
	                type: "POST",//请求方式
	                url: path+"/servlet/workflow?action=selflow2",
	                data: {flowid:flowid},
	                dataType: "json",success: function(data){
	                        var acount = data[0];
	                        //[5,"ACTIONID","ACTIONNAME","FLOWID","FLOWNAME","ROLEID",
						var flowName = "", flowId = "0", actionId = "", actionName = "", roleId = "";
						if(data.length > acount + 1){
							actionId = data[acount + data.indexOf("ACTIONID")];
							actionName = data[acount + data.indexOf("ACTIONNAME")];
							flowId = data[acount + data.indexOf("FLOWID")];
							flowName = data[acount + data.indexOf("FLOWNAME")];
							roleId = data[acount + data.indexOf("ROLEID")];
						}
						$("#flowId").val(flowId);
						$("#actionName").html(actionName);
						$("#actionId").val(actionId);
						var xian = <%=xian%>;
						getAuditors(xian,roleId,ydsx1);
	                }
	           });
		}
		 function getAuditors(xian,roleId,ydsx1){
    var agcode= xian;
			$
					.ajax({
						type : "POST",//请求方式
						url : path + "/servlet/workflow?action=selAccount",
						data : {
							agcode : agcode,
							ydsx1 : ydsx1,
							roleId : roleId
						},
						dataType : "json",
						success : function(result) {
							var scountArr = result[0];
							var accountid = "", name = "";
							var auditors = "";
							for ( var i = scountArr; i < result.length - 1; i += scountArr) {
								accountid = result[i
										+ result.indexOf("ACCOUNTID")];
								name = result[i + result.indexOf("NAME")];
								auditors += "<label><input type='radio' name='auditorid' value='"+accountid+"' checked/>"
										+ name + "</label>";
							}
							$("#radioDiv_").html(auditors);
							$("#radioDiv_").val(accountid);
						}
					});
		}
function fujianguanli(id){
	var liuchengxuanze = "2";
	var dianbiaoName = document.getElementById("dbname").value;	//电表名称 
	var liuchengId = document.getElementById("flowName_").value;	//流程编码
	
	if(dianbiaoName == "" || dianbiaoName == null || dianbiaoName == undefined){ // "",null,undefined
	     alert("电表名称不能为空");
    }else{
    	if(liuchengId == "" || liuchengId == null || liuchengId == undefined || liuchengId == 0){ // "",null,undefined
	     alert("请选择流程");
    	}else{
    	
         window.open("../jizan/fujianguanli.jsp?id="+id+"&name="+dianbiaoName+"&liuchengId="+liuchengId+"&liuchengxuanze="+liuchengxuanze, "newwindowCost", "height=550, width=1000, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
   		}
	}
}
function WhetherFile(){	//验证是否上传文件(作废)
	//上传人ID
	var scrid_wenjian = '<%=loginId%>';
	//电表名称 
	var dianbiaoid_wenjian = '<%=id%>';	
	//流程编码
	var liuchengId_wenjian = document.getElementById("flowName_").value;
	
		$.ajax({
   	 		//直接"post"或者"get",不需要"doPost","doGet"，该函数到后端接收缓冲区会自动匹配
        	type : "post",      
       		//servlet文件名为Calculator，需要提前在web.xml里面注册
        	url :"<%=path%>/servlet/YanZhengWenJianServlet",
        	dataType : "text",  //数据类型，可以为json，xml等等，自己百度
        	data :
        	{
            	"scrid":scrid_wenjian,				//上传人ID
            	"dianbiaoid":dianbiaoid_wenjian,	//电表ID
             	"liuchengid":liuchengId_wenjian,    //流程ID
        	},
        	success : function(Result)
        	{
               //Result为后端post函数传递来的数据，这里写结果操作代码
               if(Result == "1"){
            	   	alert("文件已上传,可以提交修改!");
               }else{
               		alert("未上传文件无法提交修改!");
               }
        	},
        	error : function(Result)
        	{
             	alert("未上传文件无法提交修改!");
       		}
        	
    	});
}
document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
document.form1.zdmc.value = '<%=zdmc%>';
document.form1.dfzflx.value = '<%=dfzf%>';
//document.form1.dbqyzt.value = '<%=dbqyzt%>';
document.form1.dbyt.value = '<%=dbyt%>';
//document.form1.jffs.value = '<%=jffs%>';
document.form1.jldw.value = '<%=jldw%>';
//document.form1.jfzq.value = '<%=jfzq%>';
document.form1.ydlx.value = '<%=ydlx%>';
document.form1.fplx.value = '<%=fplx%>';
document.form1.dwjslx.value = '<%=dwjslx%>';
document.form1.ydsx.value = '<%=ydsx%>';
</script>
		</form>

	</body>

</html>

