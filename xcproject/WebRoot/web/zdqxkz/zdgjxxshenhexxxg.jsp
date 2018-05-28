<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.*"%>
<%@ page import="com.noki.newfunction.dao.PdTestBewrite,com.noki.newfunction.javabean.Zdinfo,com.noki.newfunction.dao.CbzdQuery"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
	String accountid = account.getAccountName();
	String loginId = account.getAccountId();
	String zdsxnow=request.getParameter("zdsxnow");
	
	//市级审核
	String sfbglast = "";//是否标杆
	String dianjialast = "";//单价
	String edhdllast = "";//额定耗电量
	String zlfhlast = "";//交流负荷
	String jlfhlast = "";//直流负荷
	
	//省级审核
	String zdsxlast = "";//站点属性
	String zdlxlast = "";//站点类型
	String yflxlast = "";//用房类型
	String gxxxlast = "";//共享信息
	String gdfslast = "";//供电方式
	String zgdlast = "";//直供电
	String zdarealast = "";//站点面积
	String qyztlast = "";//站点启用状态
	
	//11大类
	String g2last = "";//2G
	String g3last = "";//3G
	String zplast = "";//载频
	String zslast = "";//载扇
	String changjialast = "";//厂家
	String jztypelast = "";//基站类型2
	String shebeilast = "";//设备编码
	String bbulast = "";//BBU数量
	String rrulast = "";//RRU数量
	String ydshebeilast = "";//移动设备数量
	String gxgwsllast = "";//共享固网设备数量

	String idcz = request.getParameter("id") != null ? request.getParameter("id"): "";//前台传值唯一标识id
	CbzdQuery zd=new CbzdQuery();
	PdTestBewrite bean = new PdTestBewrite();
	
	//获取站点信息
	if(idcz!=null){
        	// 站点信息
        Zdinfo zdinfo = new Zdinfo();
        zdinfo = bean.getPdTestBewritezdxx(idcz);
        if(zdinfo!=null){
        	String zdid = zdinfo.getZdid();//站点id
        	zdid = zdid != null ? zdid : "";
        		
			String zdname = zdinfo.getZdname();//站点名称
        	zdname = zdname != null ? zdname : "";
        		
			String szdq = zdinfo.getSzdq();//所属区域
        	szdq = szdq != null ? szdq : "";
        				
			String accountmonth = zdinfo.getCbsj();//报账月份（对比月份）
        	accountmonth = accountmonth != null ? accountmonth : "";
        				
			String cbbili = zdinfo.getBzpld();//超标比例
			if(cbbili==null||"".equals(cbbili)||" ".equals(cbbili)||"null".equals(cbbili)||"o".equals(cbbili))cbbili="0.00";
			DecimalFormat cb = new DecimalFormat("0.00");
			cbbili = cb.format(Double.parseDouble(cbbili));
						
			String entryperson = zdinfo.getLrr();//录入人
        	entryperson = entryperson != null ? entryperson : "";
        				
			String entrytime = zdinfo.getLrsj();//录入时间
        	entrytime = entrytime != null ? entrytime : "";	
        			
			String zgaskinstruction = zdinfo.getZgyq();//整改要求说明
        	zgaskinstruction = zgaskinstruction != null ? zgaskinstruction : "";	
        				
			String fjstruction = zdinfo.getSjfj();//附件说明
        	fjstruction = fjstruction != null ? fjstruction : "0";	
        			
			//查找站点11大类信息
			 g2last = zdinfo.getG2();//2G设备（原始）
			g2last = g2last != null ? g2last : "";	
			if("1".equals(g2last)){g2last="是";}else{g2last="否";}
			
			g3last = zdinfo.getG3(); //3G设备（原始）
			g3last = g3last != null ? g3last : "";
			if("1".equals(g3last)){g3last="是";}else{g3last="否";}			

			 zplast = zdinfo.getZp();//载频（原始）
			zplast = zplast != null ? zplast : "0";
			
			 zslast = zdinfo.getZs();//载扇（原始）
			zslast = zslast != null ? zslast : "0";	
					
			changjialast = zdinfo.getChangjia();//厂家（原始）
			changjialast = changjialast != null ? changjialast : "";
							
		 	jztypelast = zdinfo.getJztype();//基站类型（原始）
			jztypelast = jztypelast != null ? jztypelast : "";
							
			 shebeilast = zdinfo.getShebei();//设备型号（原始）
			shebeilast = shebeilast != null ? shebeilast : "";			
			
			 bbulast = zdinfo.getBbu();//bbu数量（原始）
			 if("".equals(bbulast)){bbulast="0";}
			 bbulast = bbulast != null ? bbulast : "0";	
						
			 rrulast = zdinfo.getRru();//rru数量（原始）
			rrulast = rrulast != null ? rrulast : "0";	
			if("".equals(rrulast)){rrulast="0";}			
			
		 	ydshebeilast = zdinfo.getYdshebei();//移动设备数量（原始）
			ydshebeilast = ydshebeilast != null ? ydshebeilast : "0";	
			if("".equals(ydshebeilast)){ydshebeilast="0";}	
						
			 gxgwsllast = zdinfo.getGxgwsl();//共享固网设备数量（原始）
			gxgwsllast = gxgwsllast != null ? gxgwsllast : "0";	
			if("".equals(gxgwsllast)){gxgwsllast="0";}				
%>
<html>
	<head>
		<title>站点关键信息提交修改功能详细</title>

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
	width: 50px;
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
	var path = '<%=path%>';
	function xiazai(id,zdname,accountmonth){
	   	document.form1.action=path+"/servlet/cbzdfjxiazai?id="+id+"&zdname="+zdname+"&accountmonth="+accountmonth+"&bzw=1";
		document.form1.submit()
	}
	function shangchuan(){
	$("#errorInfo").text("");
		var month = $("#month").val();
		var idcz = document.form1.idcz.value;
		var accountid = document.form1.accountid.value;
		var g2now = document.form1.g2now.value;
		var g3now = document.form1.g3now.value;
		var zpnow = document.form1.zpnow.value;
		var zsnow = document.form1.zsnow.value
		var changjianow = document.form1.changjianow.value;
		var jztypenow = document.form1.jztypenow.value;
		var shebeinow = document.form1.shebeinow.value;
		var bbunow = document.form1.bbunow.value;
		var rrunow = document.form1.rrunow.value;		
		var ydshebeinow = document.form1.ydshebeinow.value;
		var gxgwslnow = document.form1.gxgwslnow.value;	
		//var teststruction = document.form1.teststruction.value;
		var reasonanalyse = document.form1.reasonanalyse.value;
		var testperson = document.form1.testperson.value;
		var jsdbds =  document.form1.jsdbds.value;
		var kgzfh =  document.form1.kgzfh.value;
		var ydzlfh =  document.form1.ydzlfh.value;
		var dxzlfh =  document.form1.dxzlfh.value;
		var gyzlfh =  document.form1.gyzlfh.value;
		var zlygfh =  document.form1.zlygfh.value;
		var ydgxsb =  document.form1.ydgxsb.value;
		var g2xqnow = document.form1.g2xqnow.value;
		var g3sqnow = document.form1.g3sqnow.value;
		if(g2now=="2"){
		alert("2G设备不能为空！");
		return;
		}
		else{
		if(g3now=="2"){
		alert("3G设备不能为空！");
		return;
		}else{
		if(checkNotnull(document.form1.g2now,"2G设备")
		    &&checkNotnull(document.form1.g3now,"3G设备")
			&&checkNotnull(document.form1.zpnow,"载频")&&checkNotnull(document.form1.zsnow,"载扇")
			&&checkNotnull(document.form1.changjianow,"厂家")&&checkNotSelected(document.form1.changjianow,"厂家")
			&&checkNotnull(document.form1.jztypenow,"基站类型")&&checkNotSelected(document.form1.jztypenow,"基站类型")
			&&checkNotnull(document.form1.shebeinow,"设备编码")&&checkNotSelected(document.form1.shebeinow,"设备编码")
			&&checkNotnull(document.form1.ydshebeinow,"固移共享设备数量")&&checkNotnull(document.form1.gxgwslnow,"电信共享设备数量")
			//&&checkNotnull(document.form1.teststruction,"测试描述")
			&&checkNotnull(document.form1.reasonanalyse,"原因分析")
			&&checkNotnull(document.form1.testperson,"测试人")&&checkNotnull(document.form1.jsdbds,"结算电表读数")
			&&checkNotnull(document.form1.kgzfh,"开关电源直流总负荷")&&checkNotnull(document.form1.ydzlfh,"移动共享设备直流负荷")
			&&checkNotnull(document.form1.dxzlfh,"电信共享设备直流负荷")&&checkNotnull(document.form1.gyzlfh,"固移共享设备直流负荷")
			&&checkNotnull(document.form1.zlygfh,"直流远供负荷")){   					
	        if(parseInt(zpnow)==zpnow&&isNaN(zpnow)==false){
	        	if(parseInt(zsnow)==zsnow&&isNaN(zsnow)==false){
	        		if(parseInt(bbunow)==bbunow&&isNaN(bbunow)==false){
	        			if(parseInt(rrunow)==rrunow&&isNaN(rrunow)==false){
	        				if(parseInt(ydshebeinow)==ydshebeinow&&isNaN(ydshebeinow)==false){
	        					if(parseInt(gxgwslnow)==gxgwslnow&&isNaN(gxgwslnow)==false){
	        					   if(isNaN(jsdbds)==false){
								        if(isNaN(kgzfh)==false){
	        						   	if(isNaN(ydzlfh)==false){
								        if(isNaN(dxzlfh)==false){
								        if(isNaN(gyzlfh)==false){
	        						   	if(isNaN(zlygfh)==false){
	        						    if(isNaN(ydgxsb)==false){
	        							if(isNaN(g2xqnow)==false){ 
	        							if(isNaN(g3sqnow)==false){
									        if(confirm("您将要添加信息！确认信息正确！")){
									            document.form1.action=path+"/servlet/qxfj?g2now="+g2now+
										            "&g3now="+g3now+"&zpnow="+zpnow+"&zsnow="+zsnow+"&changjianow="+changjianow
										            +"&jztypenow="+jztypenow+"&shebeinow="+shebeinow+"&bbunow="+bbunow
										            +"&rrunow="+rrunow+"&ydshebeinow="+ydshebeinow+"&gxgwslnow="+gxgwslnow
										            +"&reasonanalyse="+reasonanalyse
										            +"&testperson="+testperson+"&idcz="+idcz+"&accountid="+accountid+"&jsdbds="+jsdbds
										            +"&kgzfh="+kgzfh+"&ydzlfh="+ydzlfh+"&dxzlfh="+dxzlfh+"&gyzlfh="+gyzlfh
										            +"&zlygfh="+zlygfh+"&ydgxsb="+ydgxsb+"&g2xqnow="+g2xqnow+"&g3sqnow="+g3sqnow+"&bzw=1&action=sc";
												document.form1.submit();
									        }
								        }else{
								        	  alert("您输入的信息有误，3G扇区必须为数字！");
								         }
								        }else{
								        	  alert("您输入的信息有误，2G小区必须为数字！");
								         }
								        }else{
								        	  alert("您输入的信息有误，移动共享设备数量必须为数字！");
								         }
								        }else{
								        	  alert("您输入的信息有误，直流远供负荷必须为数字！");
								         }
	        						   	}else{
								        	  alert("您输入的信息有误，固移共享设备直流负荷必须为数字！");
								         }
	        						   	}else{
								        	  alert("您输入的信息有误，电信共享设备直流负荷为数字！");
								         }
								        }else{
								        	  alert("您输入的信息有误，移动共享设备直流负荷必须为数字！");
								        }
	        						   }else{
	        							   alert("您输入的信息有误，开关电源直流总负荷必须为数字！");
	        						   }
							        }else{
							        	alert("您输入的信息有误，结算电表读数必须为数字！");
							        }
								 }else{
					   				alert("您输入的信息有误，电信共享设备数量必须为数字！");
					   		     }
							}else{
				   				alert("您输入的信息有误，固移共享设备数量必须为数字！");
				   		   }
					   }else{
			   				alert("您输入的信息有误，RRU数量必须为数字！");
			   		   }
					}else{
			   			alert("您输入的信息有误，BBU数量必须为数字！");
			   		}
				}else{
		   			alert("您输入的信息有误，载扇必须为数字！");
		   		}
		   }else{
		   		alert("您输入的信息有误，载频必须为数字！");
		   }
	    }
	    }}
	}
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
	$("#xiazai").click(function() {
		var idcz = '<%=idcz%>';
		var zdname = '<%=zdname%>';
		var accountmonth = '<%=accountmonth%>';
		xiazai(idcz,zdname,accountmonth);
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
								<td colspan="10" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									市级审核
								</td>
							</tr>
							<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													是否标杆：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="sfbglast" readonly="true"
													value="<%=sfbglast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
									         <td width="5%"><select name="sfbgnow" class="formm">
									         		<option value="0">否</option>
									         		<option value="1">是</option>									         		
									         </select></td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													单价：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="dianjialast" readonly="true"
													value="<%=dianjialast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td width="5%">
												<input type="text" name="dianjianow" value="" class="formm" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													额定耗电量：
												</div>
											</td>
											<td>
												<input type="text" name="edhdllast" readonly="true"
													value="<%=edhdllast%>" class="form2" />
											</td>
												<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为：
												</div>
											</td>
											<td>
												<input type="text" name="edhdlnow" value="" class="formm" />
											</td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													交流负荷：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="zlfhlast" readonly="true"
													value="<%=zlfhlast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="zlfhnow" value="" class="formm" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													直流负荷：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="jlfhlast" readonly="true"
													value="<%=jlfhlast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="jlfhnow" value="" class="formm" />
										</td>
									</tr>
									
							<tr>
								<td colspan="10" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									省级审核
								</td>
							</tr>		
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										用房类型：
									</div>
								</td>
								<td>
									<input type="text" name="yflxlast" readonly="true"
										value="<%=yflxlast%>" class="form2" />
								</td>
									<td bgcolor="#DDDDDD" class="form_labell">
									<div>
										改为：
									</div>
								</td>
						         <td width="5%">									         		
						         	<select name="yflxnow" class="form2">
						         		<option value="fwlx03">请选择</option>
						         		<%
							         	ArrayList fwlx = new ArrayList();
							         	fwlx = ztcommon.getSelctOptions("FWLX");
							         	if(fwlx!=null){
							         		String code="",name="";
							         		int cscount = ((Integer)fwlx.get(0)).intValue();
							         		for(int i=cscount;i<fwlx.size()-1;i+=cscount)
						                    {
						                    	code=(String)fwlx.get(i+fwlx.indexOf("CODE"));
						                    	name=(String)fwlx.get(i+fwlx.indexOf("NAME"));
						                    %>
						                    <option value="<%=code%>"><%=name%></option>
						                    <%}
							         	}
							         %>
						         	</select>									         
						         </td>
								<td bgcolor="#DDDDDD" class="form_label">
												<div>
													站点属性：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="zdsxlast" readonly="true"
													value="<%=zdsxlast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
									         <td width="5%">
									         	<select name="zdsxnow" class="form2" onchange="zdsx()">
									         		<option value="0">请选择</option>
									         		<%
										         	ArrayList zdsx = new ArrayList();
										         	zdsx = ztcommon.getSelctOptions("ZDSX");
										         	if(zdsx!=null){
										         		String code="",name="";
										         		int cscount = ((Integer)zdsx.get(0)).intValue();
										         		for(int i=cscount;i<zdsx.size()-1;i+=cscount)
									                    {
									                    	code=(String)zdsx.get(i+zdsx.indexOf("CODE"));
									                    	name=(String)zdsx.get(i+zdsx.indexOf("NAME"));
									                    %>
									                    <option value="<%=code%>"><%=name%></option>
									                    <%}
										         	}
										         %>	
										         </select>
									         </td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													站点类型：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="zdlxlast" readonly="true"
													value="<%=zdlxlast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
									         <td width="5%">
									         	<select name="zdlxnow">
									         		<option value="0">请选择</option>
									         		<%
										         	ArrayList stationtype = new ArrayList();
										         	stationtype = ztcommon.getZdlx(zdsxnow);
										         	if(stationtype!=null){
										         		String code="",name="";
										         		int cscount = ((Integer)stationtype.get(0)).intValue();
										         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
									                    {
									                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
									                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
									                    %>
									                    <option value="<%=code%>"><%=name%></option>
									                    <%}
										         	}
										         %>
									         	</select>
									         </td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													共享信息：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="gxxxlast" readonly="true"
													value="<%=gxxxlast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
								           <td width="5%">
								         	<select name="gxxxnow" class="form2">
									         		<option value="0">请选择</option>
									         		<%
										         	ArrayList gxxx = new ArrayList();
										         	gxxx = ztcommon.getSelctOptions("gxxx");
										         	if(gxxx!=null){
										         		String code1="",name1="";
										         		int cscount = ((Integer)gxxx.get(0)).intValue();
										         		for(int i=cscount;i<gxxx.size()-1;i+=cscount)
									                    {
									                    	code1=(String)gxxx.get(i+gxxx.indexOf("CODE"));
									                    	name1=(String)gxxx.get(i+gxxx.indexOf("NAME"));
									                    %>
									                    <option value="<%=code1%>"><%=name1%></option>
									                    <%}
										         	}
										         %>
										       </select>										       
								            </td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													供电方式：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="gdfslast" readonly="true"
													value="<%=gdfslast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
									        <td width="5%">
									         	<select name="gdfsnow" class="form2">
									         		<option value="0">请选择</option>
									         		<%
										         	ArrayList gdfs = new ArrayList();
										         	gdfs = ztcommon.getSelctOptions("GDFS");
										         	if(gdfs!=null){
										         		String code="",name="";
										         		int cscount = ((Integer)gdfs.get(0)).intValue();
										         		for(int i=cscount;i<gdfs.size()-1;i+=cscount)
									                    {
									                    	code=(String)gdfs.get(i+gdfs.indexOf("CODE"));
									                    	name=(String)gdfs.get(i+gdfs.indexOf("NAME"));
									                    %>
									                    <option value="<%=code%>"><%=name%></option>
									                    <%}
										         	}
										         %>									
									         	</select>									         	
									         </td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													直供电：
												</div>
											</td>
											<td>
												<input type="text" name="zgdlast" readonly="true"
													value="<%=zgdlast%>" class="form2" />
											</td>
												<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为：
												</div>
											</td>
							         		 <td width="5%">
								         		 <select name="zgdnow" class="form2">
								         		 <option value="0">否</option>
								         		 <option value="1">是</option>								         		
								         		 </select>
							                 </td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													站点面积：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="zdarealast" readonly="true"
													value="<%=zdarealast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
											<td width="5%">
												<input type="text" name="zdareanow" class="formm" value="" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													站点启用状态：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="qyztlast" readonly="true"
													value="<%=qyztlast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
								            <td width="5%">
								         	    <select name="qyztnow" class="form2">
								         		<option value="1">是</option>
								         		<option value="0">否</option>
								         	   </select>
								            </td>											
										</tr>
										<tr>
											<td colspan="10" bgcolor="F9F9F9" class="form_label">
												<img src="../../images/v.gif" width="15" height="15" />
												站点信息
											</td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													2G：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g2last" readonly="true"
													value="<%=g2last%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
											<td>
												<select name="g2now" onblur="getGray(this);" onfocus="getBlack(this);" >
													<option value="2">
														请选择
													</option>
													<option value="0">
														否
													</option>
													<option value="1">
														是
													</option>
												</select>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													3G：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="g3last" readonly="true"
													value="<%=g3last%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<select name="g3now" onblur="getGrayg3(this);" onfocus="getBlack(this);">
													<option value="2">
														请选择
													</option>
													<option value="0">
														否
													</option>
													<option value="1">
														是
													</option>
												</select>												
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
												<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="zpnow" value="0" class="formm" onblur="getGrayzp(this);" onfocus="getBlack(this);"/>
											</td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载扇：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="zslast" readonly="true"
													value="<%=zslast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
											<td width="5%">
												<input type="text" name="zsnow" value="0" class="formm" onblur="getGrayzs(this);" onfocus="getBlack(this);"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													厂家：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="changjialast" readonly="true"
													value="<%=changjialast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td width="5%">
												<select name="changjianow" onblur="getGraycj(this);" onfocus="getBlack(this);">
													<option value="0">
														请选择
													</option>
													<%
														ArrayList cj = new ArrayList();
														cj = ztcommon.getSelctOptions("cj");
														if (cj != null) {
															String code = "", name = "";
															int cscount = ((Integer) cj.get(0)).intValue();
															for (int i = cscount; i < cj.size() - 1; i += cscount) {
																code = (String) cj.get(i + cj.indexOf("CODE"));
																name = (String) cj.get(i + cj.indexOf("NAME"));
													%>
													<option value="<%=code%>"><%=name%></option>
													<%
														}
														}
													%>
												</select>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													基站类型2：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="jztypelast" readonly="true"
													value="<%=jztypelast%>" class="form2" />
											</td>
												<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为：
												</div>
											</td>
											<td width="5%">
												<select name="jztypenow" onblur="getGrayjztype(this);" onfocus="getBlack(this);">
													<option value="0">
														请选择
													</option>
													<%
														ArrayList jzlx2 = new ArrayList();
														jzlx2 = ztcommon.getSelctOptions("jzlx2");
														if (jzlx2 != null) {
															String code = "", name = "";
															int cscount = ((Integer) jzlx2.get(0)).intValue();
															for (int i = cscount; i < jzlx2.size() - 1; i += cscount) {
																code = (String) jzlx2.get(i + jzlx2.indexOf("CODE"));
																name = (String) jzlx2.get(i + jzlx2.indexOf("NAME"));
													%>
													<option value="<%=code%>"><%=name%></option>
													<%
														}
														}
													%>
												</select>
											</td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													BBU数量：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="bbulast" readonly="true"
													value="<%=bbulast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<input type="text" name="bbunow" value="0" class="formm" onblur="getGraybbu(this);" onfocus="getBlack(this);"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													RRU数量：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="rrulast" readonly="true"
													value="<%=rrulast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<input type="text" name="rrunow" value="0" class="formm" onblur="getGrayrru(this);" onfocus="getBlack(this);"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													设备编码：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="shebeilast" readonly="true"
													value="<%=shebeilast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
											<td width="5%">
												<select name="shebeinow" onblur="getGrayshebei(this);" onfocus="getBlack(this);">
													<option value="0">
														请选择
													</option>
													<%
														ArrayList sblx = new ArrayList();
														sblx = ztcommon.getSelctOptions("sblx");
														if (sblx != null) {
															String code = "", name = "";
															int cscount = ((Integer) sblx.get(0)).intValue();
															for (int i = cscount; i < sblx.size() - 1; i += cscount) {
																code = (String) sblx.get(i + sblx.indexOf("CODE"));
																name = (String) sblx.get(i + sblx.indexOf("NAME"));
													%>
													<option value="<%=code%>"><%=name%></option>
													<%
														}
														}
													%>
												</select>
											</td>																						
									</tr>
									<tr>
									<td bgcolor="#DDDDDD" class="form_label">
												<div>
													移动设备数量：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="ydshebeilast" readonly="true"
													value="<%=ydshebeilast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<input type="text" name="ydshebeinow" value="0" class="formm" onblur="getGrayyd(this);" onfocus="getBlack(this);"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													共享固网数量：
												</div>
											</td>
											<td width="5%">
												<input type="text" name="gxgwsllast" readonly="true"
													value="<%=gxgwsllast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<input type="text" name="gxgwslnow" value="0" class="formm" onblur="getGraygw(this);" onfocus="getBlack(this);" />
											</td>
										</tr>
									</table>
									<table>	
 										<tr>	
											<td colspan="7" bgcolor="F9F9F9" class="form_label">
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
									<input type="text" name="reasonanalyse" value="" class="formjc" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										修改依据：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="reasonanalyse" value="" class="formjc" />
								</td>
							</tr>
							<tr height="19px">
								<td bgcolor="#DDDDDD" class="form_label"><div>
									添加附件：
								</div></td>
								<td><div>
									<input type="file" id="fileUp" name="fileUp" height="19px" width="130px">
								</div></td>
								<td><div id="uploading"
									style="position: relative; width: 60px; height: 19px; float: left;">
									<img alt=""
										src="<%=request.getContextPath()%>/images/shangchuan.png"
										width="100%" height="100%" />
									<span
										style="font-size: 12px; position: absolute; left: 26px; top: 3px; color: white">提交</span>
								</div></td>
								<td>
								<div id="errorInfo"
									style="width: 200px; height: 19px; position: relative; font-size: 12px; color: red">
									<c:forEach items="${requestScope.statusInfo}" var="info">
										<br />${info}
									</c:forEach>
								</div></td>								
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	<%} }%>
	</body>
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
   
}
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
	function sendRequest(url,para) {
		createXMLHttpRequest();	
		XMLHttpReq.open("GET", url, true);		
		if(para=="zdsxnow"){
			XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数			
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		} 
		XMLHttpReq.send(null);  
	}
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res = XMLHttpReq.responseText;
              	window.alert(res);                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
function processResponse_zdlx() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          	updateZdlx(res);
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
 }
	//站点类型
function updateZdlx(res){
	var shilist = document.all.zdlxnow;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
//站点属性
function zdsx(){
	var sid = document.form1.zdsxnow.value;    
	if(sid=="0"){
		var shilist = document.all.zdlxnow;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=zdsx&pid="+sid,"zdsxnow");
	}
}
</script> 
</html>

 

 
 