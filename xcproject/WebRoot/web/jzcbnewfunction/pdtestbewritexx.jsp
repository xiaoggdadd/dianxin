<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.*"%>
<%@ page import="com.noki.newfunction.dao.PdTestBewrite,com.noki.newfunction.javabean.Zdinfo,com.noki.newfunction.dao.CbzdQuery"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.noki.newfunction.dao.*,com.noki.newfunction.javabean.*"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
	String accountid = account.getAccountName();
	String loginId = account.getAccountId();
	String g2last="",g3last="",zplast="",zslast="",bbulast="",rrulast="",ydshebeilast="",gxgwsllast="";
	String changjialast="",jztypelast="",shebeilast="",dxgxsblast="",ydgxsblast="",g2xqlast="",g3sqlast="",nhbz="",edhdl="",zlfh="";
	
	String g2now="",g3now="",zpnow="",zsnow="",bbunow="",rrunow="",ydshebeinow="",gxgwslnow="";
	String changjianow="",jztypenow="",shebeinow="",dxgxsbnow="0",ydgxsbnow="",g2xqnow="",g3sqnow="";
	String dbds="",kgdy="",ydgx="",dxgx="",gygx="",zlyg="";
	String yyfx="",cszrr="";
	String zlzfh="",jlzfh="",qsdb="",bdhdl="";
	String idcz = request.getParameter("id") != null ? request.getParameter("id"): "";//前台传值唯一标识id
	cbzddao dao = new cbzddao();
	int r= dao.CheckQx(idcz);
		CbzdQuery zd=new CbzdQuery();
		//String tidc = request.getParameter("tidc") != null ? request.getParameter("tidc"): "1";
	//System.out.println("tidc"+tidc);
	PdTestBewrite bean = new PdTestBewrite();
	
	
	//获取站点信息
	if(idcz!=null){
        	// 站点信息
        Zdinfo zdinfo = new Zdinfo();
        zdinfo = bean.getPdTestBewritezdxx(idcz);
        if(zdinfo!=null){
        dbds=zdinfo.getDbds();
        kgdy=zdinfo.getKGDYZLFH();
        ydgx=zdinfo.getYDGXSBZLFH();
        dxgx=zdinfo.getDXGXSBZLFH();
        gygx=zdinfo.getGYGXSBZLFH();
        zlyg=zdinfo.getZYYGSBZLFH();
        if(null==dbds||"".equals(dbds)||"null".equals(dbds)){dbds="0";}
         if(null==kgdy||"".equals(kgdy)||"null".equals(kgdy)){kgdy="0";}
          if(null==ydgx||"".equals(ydgx)||"null".equals(ydgx)){ydgx="0";}
           if(null==dxgx||"".equals(dxgx)||"null".equals(dxgx)){dxgx="0";}
           if(null==gygx||"".equals(gygx)||"null".equals(gygx)){gygx="0";}
            if(null==zlyg||"".equals(zlyg)||"null".equals(zlyg)){zlyg="0";}
        
        yyfx=zdinfo.getYyfx();
        cszrr=zdinfo.getCszrr();
          if(null==yyfx||"null".equals(yyfx)){yyfx="";}
          if(null==cszrr||"null".equals(cszrr)){cszrr="";}
        
        
        
        
        
        	String zdid = zdinfo.getZdid();//站点id
        	zdid = zdid != null ? zdid : "";
            String[] hh = bean.getXgsq(zdid);
            String sqscb = hh[1];//信息修改申请生产标
            String xgsqid = hh[0];//信息申请id
        	
        	
        	String zdsx = "";
        	if(!"".equals(zdid)){
        		zdsx = bean.getZdsx(zdid);//站点属性,11大类是否必填(对于站点属性为非基站的站点，11大类不是必填项)
        	}
			String zdname = zdinfo.getZdname();//站点名称
        	zdname = zdname != null ? zdname : "";
        		
			String szdq = zdinfo.getSzdq();//所属区域
        	szdq = szdq != null ? szdq : "";
        				
			String accountmonth = zdinfo.getCbsj();//报账月份（对比月份）
        	accountmonth = accountmonth != null ? accountmonth : "";
        				
			String cbbili = zdinfo.getCbbl();//超标比例
			if(cbbili==null||"".equals(cbbili)||" ".equals(cbbili)||"null".equals(cbbili)||"o".equals(cbbili))cbbili="0.00";
			DecimalFormat cb = new DecimalFormat("0.00");
			cbbili = cb.format(Double.parseDouble(cbbili))+"%";
						
			String entryperson = zdinfo.getLrr();//录入人
        	entryperson = entryperson != null ? entryperson : "";
        				
			String entrytime = zdinfo.getLrsj();//录入时间
        	entrytime = entrytime != null ? entrytime : "";	
        			
			String zgaskinstruction = zdinfo.getZgyq();//整改要求说明
        	zgaskinstruction = zgaskinstruction != null ? zgaskinstruction : "";	
        				
			//String fjstruction = zdinfo.getSjfj();//附件说明
        	//fjstruction = fjstruction != null ? fjstruction : "0";	
        		//更改为sjname字段 
        	String fjstruction = zdinfo.getSjname();//省级附件名---超标站点上传的附件名
        		if(fjstruction==null||fjstruction.equals("")||fjstruction.equals(" ")){
        			fjstruction="";
        		}
        	System.out.println("----------"+fjstruction);
        		
			//查找站点11大类信息
			 g2last = zdinfo.getG2();//2G设备（原始）
			g2last = g2last != null ? g2last : "";	
			if("1".equals(g2last)){g2last="是";}else{g2last="否";}
			g2now=zdinfo.getXg2();
			if(null==g2now||"".equals(g2now)||"null".equals(g2now)){
			g2now="0";
			}
			
			g3last = zdinfo.getG3(); //3G设备（原始）
			g3last = g3last != null ? g3last : "";
			if("1".equals(g3last)){g3last="是";}else{g3last="否";}		
			g3now=zdinfo.getXg3();	
			if(null==g3now||"".equals(g3now)||"null".equals(g3now)){
			g3now="0";
			}

			 zplast = zdinfo.getZp();//载频（原始）
			zplast = zplast != null ? zplast : "0";
			zpnow=(zdinfo.getXzp()+"").trim();
			
			// zpnow=zdinfo.getXzp();
			
			
			 zslast = zdinfo.getZs();//载扇（原始）
			zslast = zslast != null ? zslast : "0";	
			zsnow=(zdinfo.getXzs()+"").trim();
			
					
		changjialast = zdinfo.getChangjia();//厂家（原始）
			changjialast = changjialast != null ? changjialast : "";
			changjianow=zdinfo.getXchangjia();
			if(null==changjianow||"".equals(changjianow)||"null".equals(changjianow)){
			changjianow="0";
			}
			
							
		 jztypelast = zdinfo.getJztype();//基站类型（原始）
			jztypelast = jztypelast != null ? jztypelast : "";
		jztypenow=zdinfo.getXjztype();
	if(null==jztypenow||"".equals(jztypenow)||"null".equals(jztypenow)){
	 jztypenow="0";
	}
							
			 shebeilast = zdinfo.getShebei();//设备型号（原始）
			shebeilast = shebeilast != null ? shebeilast : "";		
			shebeinow=zdinfo.getXshebei();
			if(null==shebeinow||"".equals(shebeinow)||"null".equals(shebeinow)){
			 shebeinow="0";
			}	
			
			 bbulast = zdinfo.getBbu();//bbu数量（原始）
			 if("".equals(bbulast)){bbulast="0";}
			bbulast = bbulast != null ? bbulast : "0";	
			bbunow=(zdinfo.getXbbu()+"").trim();
						
			 rrulast = zdinfo.getRru();//rru数量（原始）
			rrulast = rrulast != null ? rrulast : "0";	
			if("".equals(rrulast)){rrulast="0";}	
			rrunow=(zdinfo.getXrru()+"").trim();
					
			
		 	ydshebeilast = zdinfo.getYdshebei();//移动设备数量（原始）
			ydshebeilast = ydshebeilast != null ? ydshebeilast : "0";	
			if("".equals(ydshebeilast)){ydshebeilast="0";}	
			ydshebeinow=(zdinfo.getXydshebei()+"").trim();
						
			 gxgwsllast = zdinfo.getGxgwsl();//共享固网设备数量（原始）
			gxgwsllast = gxgwsllast != null ? gxgwsllast : "0";	
			if("".equals(gxgwsllast)){gxgwsllast="0";}
			gxgwslnow=(zdinfo.getXgxgwsl()+"").trim();
			//formbean.setXgxgwsl(Integer.parseInt(s3));
			//	formbean.setJdxgxsb(rs.getString("XDXGXSB"));
			//	formbean.setJydgxsb(rs.getString("XYDGXSB"));
			//	formbean.setJg2xq(rs.getString("XG2XQ"));
			///	formbean.setJg3sq(rs.getString("XG3SQ"));
			
			dxgxsblast=zdinfo.getDxgxsb();//电信共享设备数量
			dxgxsblast = dxgxsblast != null ? dxgxsblast : "0";	
			dxgxsbnow=zdinfo.getJdxgxsb();
			if(null==dxgxsbnow||"".equals(dxgxsbnow)||"null".equals(dxgxsbnow)){dxgxsbnow="0";}
			if("".equals(dxgxsblast)){dxgxsblast="0";}
			//dxgxsbnow=zdinfo.getDxgxsb();
			
			ydgxsblast=zdinfo.getYdgxsb();//移动共享设备数量
			ydgxsblast = ydgxsblast != null ? ydgxsblast : "0";	
			ydgxsbnow=zdinfo.getJydgxsb();
			if("".equals(ydgxsbnow)||null==ydgxsbnow||"null".equals(ydgxsbnow)){ydgxsbnow="0";}
			if("".equals(ydgxsblast)){ydgxsblast="0";}
			
			g2xqlast=zdinfo.getG2xq();//2g小区
			g2xqlast = g2xqlast != null ? g2xqlast : "0";
			g2xqnow=zdinfo.getJg2xq();
			if("".equals(g2xqnow)||null==g2xqnow||"null".equals(g2xqnow)){g2xqnow="0";}
			if("".equals(g2xqlast)){g2xqlast="0";}
			
			g3sqlast=zdinfo.getG2xq();//3g扇区
			g3sqlast = g3sqlast != null ? g3sqlast : "0";
			g3sqnow=zdinfo.getJg3sq();
			if("".equals(g3sqnow)||null==g3sqnow||"null".equals(g3sqnow)){g3sqnow="0";}
			if("".equals(g3sqlast)){g3sqlast="0";}
			
			nhbz=zdinfo.getNhbz();
			nhbz = nhbz != null ? nhbz : "0";
			
			edhdl=zdinfo.getEdhdl();
			edhdl = edhdl != null ? edhdl : "0";
			
			zlfh=zdinfo.getZlfh();
			zlfh = zlfh != null ? zlfh : "0";
			zlzfh=zdinfo.getZlzfh();
			zlzfh = zlzfh != null ? zlzfh : "0";
			jlzfh=zdinfo.getJlzfh();
			jlzfh = jlzfh != null ? jlzfh : "0";
			bdhdl=zdinfo.getBdhdl();
			bdhdl = bdhdl != null ? bdhdl : "0";
			qsdb=zdinfo.getQsdb();
			qsdb = qsdb != null ? qsdb : "0";
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
 <script src="<%=path%>/javascript/tx.js"></script>
<script language="javascript">
	var path = '<%=path%>';
	function xiazai(id,zdname,accountmonth){
		var sjfilename = '<%=fjstruction%>';
	   	document.form1.action=path+"/servlet/cbzdfjxiazai?id="+id+"&zdname="+zdname+"&accountmonth="+accountmonth+"&sjfilename="+sjfilename+"&bzw=1";
		document.form1.submit()
	}
//	function shangchuan() {
	//	$("#errorInfo").text("");
	//	var month = $("#month").val();
	//	document.form1.action = path + "/servlet/qxfj";
	//	document.form1.submit()
	// }
function mustOneone(){
	zdsx='<%=zdsx%>';
	if(zdsx =="zdsx02"){//基站
		if(checkNotnull(document.form1.g2now,"2G设备")
		    &&checkNotnull(document.form1.g3now,"3G设备")
			&&checkNotnull(document.form1.zpnow,"载频")&&checkNotnull(document.form1.zsnow,"载扇")
			&&checkNotSelected(document.form1.changjianow,"厂家")&&checkNotSelected(document.form1.jztypenow,"基站类型")
			&&checkNotSelected(document.form1.shebeinow,"设备类型")
			&&checkNotnull(document.form1.ydshebeinow,"固移共享设备数量")&&checkNotnull(document.form1.gxgwslnow,"电信共享设备数量")){
		return true;
		}
		}else{
			return true;
			}
		
}
	function shangchuan(){
		var xgsqid = document.form1.xgsqid.value;
		document.form1.fzbzw.value = 0;
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
		var jlzfh = document.form1.jlzfh.value;//交流总负荷(A)(不含空调)
		var zlzfh = document.form1.zlzfh.value;//直流总负荷(A)(不含空调)
		var bdhdl = document.form1.bdhdl.value;//额定耗电量(本地标)
		var qsdb = document.form1.qsdb.value;//省定标(不含空调)
		var tidc=document.form1.tidc.value;
		if(tidc=="1"){
			alert("附件大于6M！");
			return;
		}else{
		if(g2now=="2"){
		alert("2G设备不能为空！");
		return;
		}
		else{
		if(g3now=="2"){
		alert("3G设备不能为空！");
		return;
		}else{
			if(mustOneone()==true){
			if(
			//&&checkNotnull(document.form1.teststruction,"测试描述")
			checkNotnull(document.form1.reasonanalyse,"原因分析")
			&&checkNotnull(document.form1.testperson,"测试人")&&checkNotnull(document.form1.jsdbds,"结算电表读数")
			&&checkNotnull(document.form1.kgzfh,"开关电源直流总负荷")&&checkNotnull(document.form1.ydzlfh,"移动共享设备直流负荷")
			&&checkNotnull(document.form1.dxzlfh,"电信共享设备直流负荷")&&checkNotnull(document.form1.gyzlfh,"固移共享设备直流负荷")
			&&checkNotnull(document.form1.zlygfh,"直流远供负荷")&&checkNotnull(document.form1.zlzfh,"直流总负荷")
			&&checkNotnull(document.form1.jlzfh,"交流总负荷")&&checkNotnull(document.form1.bdhdl,"本地额定耗电量")&&checkNotnull(document.form1.qsdb,"省定标(不含空调)")){   					
	        if(parseInt(zpnow)==zpnow&&isNaN(zpnow)==false){
	        	if(parseInt(zsnow)==zsnow&&isNaN(zsnow)==false){
	        		if(parseInt(bbunow)==bbunow&&isNaN(bbunow)==false){
	        			if(parseInt(rrunow)==rrunow&&isNaN(rrunow)==false){
	        				if(parseInt(ydshebeinow)==ydshebeinow&&isNaN(ydshebeinow)==false){
	        					if(parseInt(gxgwslnow)==gxgwslnow&&isNaN(gxgwslnow)==false){
	        					   if(parseFloat(jsdbds)==jsdbds&&isNaN(jsdbds)==false){
								        if(parseFloat(kgzfh)==kgzfh&&isNaN(kgzfh)==false){
	        						   	if(parseFloat(ydzlfh)==ydzlfh&&isNaN(ydzlfh)==false){
								        if(parseFloat(dxzlfh)==dxzlfh&&isNaN(dxzlfh)==false){
								        if(parseFloat(gyzlfh)==gyzlfh&&isNaN(gyzlfh)==false){
	        						   	if(parseFloat(zlygfh)==zlygfh&&isNaN(zlygfh)==false){
	        						    if(parseInt(ydgxsb)==ydgxsb&&isNaN(ydgxsb)==false){
	        							if(parseInt(g2xqnow)==g2xqnow&&isNaN(g2xqnow)==false){ 
	        							if(parseInt(g3sqnow)==g3sqnow&&isNaN(g3sqnow)==false){
	        							if(parseFloat(zlzfh)==zlzfh&&isNaN(zlzfh)==false){
	        							if(parseFloat(jlzfh)==jlzfh&&isNaN(jlzfh)==false){
										if(parseFloat(bdhdl)==bdhdl&&isNaN(bdhdl)==false){
										if(parseFloat(qsdb)==qsdb&&isNaN(qsdb)==false){
											if(zlzfh !='0'&&jlzfh!='0'&&kgzfh!='0'&&bdhdl!='0'&&qsdb!='0'){
											if(sbblgx()){
												var fzbzw = document.form1.fzbzw.value;
									        if(confirm("您将要添加信息！确认信息正确！")){
									            document.form1.action=path+"/servlet/qxfj?g2now="+g2now+
										            "&g3now="+g3now+"&zpnow="+zpnow+"&zsnow="+zsnow+"&changjianow="+changjianow
										            +"&jztypenow="+jztypenow+"&shebeinow="+shebeinow+"&bbunow="+bbunow
										            +"&rrunow="+rrunow+"&ydshebeinow="+ydshebeinow+"&gxgwslnow="+gxgwslnow
										            +"&idcz="+idcz+"&accountid="+accountid+"&jsdbds="+jsdbds
										            +"&kgzfh="+kgzfh+"&ydzlfh="+ydzlfh+"&dxzlfh="+dxzlfh+"&gyzlfh="+gyzlfh
										            +"&zlygfh="+zlygfh+"&ydgxsb="+ydgxsb+"&g2xqnow="+g2xqnow+"&g3sqnow="+g3sqnow
										            +"&zlzfh="+zlzfh+"&jlzfh="+jlzfh+"&bdhdl="+bdhdl+"&qsdb="+qsdb+"&fzbzw="+fzbzw+"&xgsqid="+xgsqid
										            +"&bzw=1&action=sc";
												document.form1.submit();
									        }
									        /*if(confirm("您将要添加信息！确认信息正确！")){
									            document.form1.action=path+"/servlet/qxfj?action=sc&idcz="+idcz+"&accountid="+accountid;
												document.form1.submit();
									        }*/
									        }
									        }else{
									        	 alert("您输入的信息有误，开关电源直流负荷,直流总负荷,交流总负荷,额定耗电量(本地标),省定标(不含空调)都不能为0！");
									        }
									        }else{
									        alert("您输入的信息有误，省定标(不含空调)必须为数字！");
									        }
									         }else{
								        	  alert("您输入的信息有误，本地耗电量必须为数字！");
								         }
									          }else{
								        	  alert("您输入的信息有误，交流总负荷必须为数字！");
								         }
									        }else{
								        	  alert("您输入的信息有误，直流总负荷必须为数字！");
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
	}		
	    }}
		}
	}
	
	function sbblgx(){
		var sqscb = '<%=sqscb%>';	//信息修改生产标
		if(sqscb!=""){
			//(工单生产标—申请生产标)/ 申请生产标*100%。该值绝对值是否在10%以内。
			var gdscb = document.form1.qsdb.value;//工单生产标
			var blz = Math.abs((gdscb-sqscb)/sqscb);
			if(blz<0.1){
				document.form1.fzbzw.value = 1;//赋值标志位 1:赋值,0:不赋值
				return true;
			}else{
				alert("信息修改申请中有省未审核的申请单,请先处理!");
			}
		}else{
			return true;
		}	
	}
	
	
	function exportModel(idcz){
		  var path = '<%=path%>';
	      document.form1.action=path+"/servlet/download?action=downloadzdcbglxx&idcz="+idcz+"&templetname="+encodeURIComponent("超标站点测试描述模板");            
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
	$("#daochu").click(function(){
		var idcz = '<%=idcz%>';
		exportModel(idcz);
	});
	$("#xiazai").click(function() {
		var idcz = '<%=idcz%>';
		var zdname = '<%=zdname%>';
		var accountmonth = '<%=accountmonth%>';
		xiazai(idcz,zdname,accountmonth);
	});

});

 var isIE = /msie/i.test(navigator.userAgent) && !window.opera;          
  function fileChange(target) { 
	document.form1.tidc.value=0;
    var fileSize = 0;           
    if (isIE && !target.files) {       
      var filePath = target.value;       
      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");          
      var file = fileSystem.GetFile (filePath);       
      fileSize = file.Size;      
    } else {      
     fileSize = target.files[0].size;       
     }     
     var size = fileSize / 1024;
     if(size>6144){    
      	alert("附件不能大于6M");  
      	document.form1.tidc.value=1;
     }    
        
}     


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
		<form action="" name="form1" method="POST" id="form" enctype="multipart/form-data">
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
										value="<%=zdid%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										站点名称：
									</div>
								</td>
								<td>
									<input type="text" name="zdname" readonly="readonly"
										value="<%=zdname%>" class="form1" />
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
										value="<%=accountmonth%>" class="form3" />
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
										value="<%=cbbili%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										全省定标：
									</div>
								</td>
								<td>
									<input type="text" type="hidden" name="cbbili" readonly="readonly"
										value="<%=nhbz%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										额定耗电量：
									</div>
								</td>
								<td>
									<input type="hidden" name="cbbili" readonly="readonly"
										value="<%=edhdl%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										直流负荷：
									</div>
								</td>
								<td>
									<input type="text" type="hidden" name="zlfh" readonly="readonly"
										value="<%=zlfh%>" class="form2" />
								</td>
								
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										录入人：
									</div>
								</td>
								<td>
									<input type="text" name="entryperson" readonly="readonly"
										value="<%=entryperson%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										录入时间：
									</div>
								</td>
								<td>
									<input type="text" name="entrytime" readonly="readonly"
										value="<%=entrytime%>" class="form1" />
								</td>
								<td></td>							
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										整改要求说明：
									</div>
								</td>
								<td colspan='7' class="form1">
									<input type="text" name="zgaskinstruction" readonly="readonly"
										value="<%=zgaskinstruction%>" class="formcc" />
								</td>
								
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										附件说明：
									</div>
								</td>
								<%if(!fjstruction.equals("")){ %>
									<td colspan='1'>
									<div id="xiazai"
										style="width: 60px; height: 19px; cursor: pointer; float: right; position: relative; right: 70px">
										<img src="<%=path%>/images/baocun.png" width="100%"
											height="100%">
										<span
											style="font-size: 12px; position: absolute; left: 27px; top: 3px; color: white">下载</span>
									</div>
									</td>
								<%}else{ %>
									<td colspan='2'>
									    <div align="center" >无下载附件</div>
									</td>
								<%}%>
							</tr>
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									原11大类信息
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
													2G设备：
												</div>
											</td>
											<td>
												<input type="text" name="g2last" readonly="readonly"
													value="<%=g2last%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													3G设备：
												</div>
											</td>
											<td>
												<input type="text" name="g3last" readonly="readonly"
													value="<%=g3last%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载频：
												</div>
											</td>
											<td>
												<input type="text" name="zplast" readonly="readonly"
													value="<%=zplast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载扇：
												</div>
											</td>
											<td>
												<input type="text" name="zslast" readonly="readonly"
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
												<input type="text" name="chagnjialast" readonly="readonly"
													value="<%=changjialast%>" class="form3" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													基站类型：
												</div>
											</td>
											<td>
												<input type="text" name="jztypelast" readonly="readonly"
													value="<%=jztypelast%>" class="form1" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													设备类型：
												</div>
											</td>
											<td>
												<input type="text" name="shebeilast" readonly="readonly"
													value="<%=shebeilast%>" class="form1" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													拉远BBU：
												</div>
											</td>
											<td>
												<input type="text" name="bbulast" readonly="readonly"
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
												<input type="text" name="rrulast" readonly="readonly"
													value="<%=rrulast%>" class="form2" />
											</td>
											<%--<td bgcolor="#DDDDDD" class="form_label">
												<div>
													移动设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="ydshebeilast" readonly="true"
													value="<%=ydshebeilast%>" class="form2" />
											</td>
											--%><td bgcolor="#DDDDDD" class="form_label">
												<div>
													固移共享设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="gxgwsllast" readonly="readonly"
													value="<%=gxgwsllast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													电信共享设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="dxgxsblast" readonly="readonly"
													value="<%=dxgxsblast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													移动共享设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="ydgxsblast" readonly="readonly"
													value="<%=ydgxsblast%>" class="form2" />
											</td>
										</tr>
										<tr>
										    <td bgcolor="#DDDDDD" class="form_label">
											    <div>
													2G小区：
												</div>
											</td>
											<td>
												<input type="text" name="g2xqlast" readonly="readonly"
													value="<%=g2xqlast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													3G扇区：
												</div>
											</td>
											<td>
												<input type="text" name="g3sqlast" readonly="readonly"
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
												<input type="hidden" name="accountid" value="<%=accountid %>"/>
												<input type="hidden" name="idcz" value="<%=idcz %>"/>
												<div>
													2G设备：
												</div>
											</td>
											<td>
												<select name="g2now" style="width: 130" onblur="getGray(this);" onfocus="getBlack(this);" >
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
													3G设备：
												</div>
											</td>
											<td>
												<select name="g3now" style="width: 130" onblur="getGrayg3(this);" onfocus="getBlack(this);">
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
												<input type="text" name="zpnow" value="<%=zpnow%>" class="formr" onblur="getGrayzp(this);" onfocus="getBlack(this);"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载扇：
												</div>
											</td>
											<td>
												<input type="text" name="zsnow" value="<%=zsnow%>" class="formr" onblur="getGrayzs(this);" onfocus="getBlack(this);"/>
											</td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													厂家：
												</div>
											</td>
											<td>
												<select name="changjianow" onchange="sblx()" style="width: 130" onblur="getGraycj(this);" onfocus="getBlack(this);">
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
													基站类型：
												</div>
											</td>
											<td>
												<select name="jztypenow" style="width: 130" onblur="getGrayjztype(this);" onfocus="getBlack(this);">
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
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													设备类型：
												</div>
											</td>
											<td>
												<select name="shebeinow" style="width: 130" onblur="getGrayshebei(this);" onfocus="getBlack(this);">
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
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													拉远BBU：
												</div>
											</td>
											<td>
												<input type="text" name="bbunow" value="<%=bbunow%>" class="formr" onblur="getGraybbu(this);" onfocus="getBlack(this);"/>
											</td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													远供RRU：
												</div>
											</td>
											<td>
												<input type="text" name="rrunow" value="<%=rrunow%>" class="formr" onblur="getGrayrru(this);" onfocus="getBlack(this);"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													固移共享设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="ydshebeinow" value="<%=gxgwslnow%>" class="formr" onblur="getGrayyd(this);" onfocus="getBlack(this);"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													电信共享设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="gxgwslnow" value="<%=dxgxsbnow%>" class="formr" onblur="getGrayyd(this);" onfocus="getBlack(this);" />
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													移动共享设备数量：
												</div>
											</td>
											<td>
												<input type="text" name="ydgxsb" value="<%=ydgxsbnow%>" class="formr" onblur="getGraygw(this);" onfocus="getBlack(this);"/>
											</td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													2G小区：
												</div>
											</td>
											<td>
												<input type="text" name="g2xqnow" value="<%=g2xqnow%>" class="formr" onblur="getGrayG2(this);" onfocus="getBlack(this);"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													3G扇区：
												</div>
											</td>
											<td>
												<input type="text" name="g3sqnow" value="<%=g3sqnow%>" class="formr" onblur="getGrayG3(this);" onfocus="getBlack(this);"/>
											</td>
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
									<input type="text" name="jsdbds" value="<%=dbds%>" class="formr" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>开关电源直流总负荷(A)</div>
								</td>
								<td>
									<input type="text" name="kgzfh" value="<%=kgdy%>" class="formr"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>移动共享设备直流负荷(A)</div>
								</td>
								<td>    
									<input type="text" name="ydzlfh" value="<%=ydgx%>" class="formr"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>电信共享设备直流负荷(A)</div>
								</td>
								<td>
									<input type="text" name="dxzlfh" value="<%=dxgx%>" class="formr"/>
								</td>
							</tr>
							<tr>  
								<td bgcolor="#DDDDDD" class="form_label">
									<div>固移共享设备直流负荷(A)</div>
								</td>
								<td>
									<input type="text" name="gyzlfh" value="<%=gygx%>" class="formr"/>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>直流远供负荷(A)</div>
								</td>
								<td>
									<input type="text" name="zlygfh" value="<%=zlyg%>" class="formr"/>
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
									<input type="text" type="hidden" name="qsdb" value="<%=qsdb%>" class="formr" />
								</td>
								<td></td>
								<td></td>
							</tr>
							<%--<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										测试描述：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="teststruction" value="" class="formjc" />
								</td>
							</tr>
							--%><tr>
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
									<input type="text" name="testperson" value="<%=cszrr%>" class="form" />
								</td>
							</tr>
							<tr height="19px">
								<!-- <td bgcolor="#DDDDDD" class="form_label">
									<div>
										添加附件：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="addappendix" value="" class="formjc" />
								</td> -->
								<td bgcolor="#DDDDDD" class="form_label"><div>
									添加附件：
								</div></td>
								<td><div>
									<input type="file" id="fileUp" name="fileUp" height="19px" width="130px" onchange="fileChange(this);"/>
								</div></td>
							
								<td><div id="uploading"
									style="position: relative; width: 60px; height: 19px; float: left;">
									<img alt=""
										src="<%=request.getContextPath()%>/images/shangchuan.png"
										width="100%" height="100%" />
									<span
										style="font-size: 12px; position: absolute; left: 26px; top: 3px; color: white">提交</span>
								</div>
								<input type="hidden" name="tidc" value="0" />
								
								<input type="hidden" id="fzbzw" name="fzbzw" value="0"/><%--是否给信息修改申请赋值标志位--%>
								<input type="hidden" id="xgsqid" name="xgsqid" value="<%=xgsqid%>"/><%--信息修改申请id--%>
								</td>
								
								<td><div id="daochu"
									style="position: relative; width: 80px; height: 19px; float: left;left: -30px;">
									<img alt=""
										src="<%=request.getContextPath()%>/images/shangchuan.png"
										width="100%" height="100%" />
									<span
										style="font-size: 12px; position: absolute; left: 28px; top: 3px; color: white">下载模板</span>
								</div></td>
								<td>
								<div id="errorInfo"
									style="width: 200px; height: 19px; position: relative; font-size: 12px; color: red">
									<c:forEach items="${requestScope.statusInfo}" var="info">
										<br />${info}
									</c:forEach>
								</div></td>	
								<%if(r==1){ %>
								<td colspan='1'>
								
									<div align="center" ><a href="#" onclick="modifyjz2('<%=idcz%>')">
										<font  style="font-size: 15px;color: red">下载附件</font></a></div>
								</td>
								<%}else{ %>
								<td colspan='1'>
									<div align="center" ><font  style="font-size: 15px;color: red">无附件</font></div>
								</td>
								<%} %>																
							</tr>
							<!--<tr>
								<td colspan="8">
									<div id="cancelBtn"
										style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 0px">
										<img src="<%=path%>/images/quxiao.png" width="100%"
											height="100%">
										<span
											style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
									</div>
									<div id="resetBtn"
										style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 11px">
										<img src="<%=path%>/images/2chongzhi.png" width="100%"
											height="100%">
										<span
											style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
									</div>
									<div id="saveBtn"
										style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 22px">
										<img src="<%=path%>/images/baocun.png" width="100%"
											height="100%">
										<span
											style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">提交待审</span>
									</div>
								</td>
							</tr>
						--></table>
					</td>
				</tr>
			</table>
		</form>
	<%} }%>
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
 <script type="text/javascript">
var XMLHttpReq;
	//XMLHttpReq = createXMLHttpRequest();
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
//alert("222");
		createXMLHttpRequest();
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="changjia"){
			XMLHttpReq.onreadystatechange = processResponse_changjia;//指定响应函数
		
		}else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
	}
	
	function processResponse_changjia() {
	//alert("333");
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateChangjia(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
    
	}
	//厂家
function updateChangjia(res){
//alert("444");
	var shilist = document.all.shebeinow;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
 	//设备类型
	function sblx(){
	//alert("111");
	var sid = document.form1.changjianow.value;
    //var s1=document.form1.s1.value;
    
	if(sid=="0"){
		var shilist = document.all.shebeinow;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=changjia&pid="+sid,"changjia");
	}
	
}
 	function modifyjz2(id){
   // alert(path);
     		document.form1.action=path+"/servlet/cbzdfjxiazai2?id="+id+"&bzw=2";
     		 document.form1.submit();

    }
	
	document.form1.g2now.value='<%=g2now%>';
	document.form1.g3now.value='<%=g3now%>';
	document.form1.changjianow.value='<%=changjianow%>';// jztypenow
	document.form1.jztypenow.value='<%=jztypenow%>';//shebeinow
	document.form1.shebeinow.value='<%=shebeinow%>';
	
 </script>