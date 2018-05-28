<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account,com.noki.newfunction.dao.*,com.noki.newfunction.javabean.*"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.*"%>
<%@ page import="com.noki.zdqxkz.dao.Zdqxcxxg,com.noki.zdqxkz.javabean.Zdqxkz,com.noki.zdqxkz.dao.Zdqxcxxg,com.noki.zdqxkz.javabean.jcnh"%>
<%@ page import="com.noki.zdqxkz.dao.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
	String accountid = account.getAccountName();
	String loginId = account.getAccountId();
	
	String zdid = "";//zdid
	
		//市级审核
	String sfbglast = "";//是否标杆(旧)
	String dianjialast = "";//单价(旧)
	String edhdllast = "";//额定耗电量(旧)
	String zlfhlast = "";//交流负荷(旧)
	String jlfhlast = "";//直流负荷(旧)
	String dfzflxlast= "";//电费支付类型(旧)
	
	//省级审核
	String zdsxlast = "";//站点属性(旧)
	String zdlxlast = "";//站点类型(旧)
	String yflxlast = "";//用房类型(旧)
	String gxxxlast = "";//共享信息(旧)
	String gdfslast = "";//供电方式(旧)
	String zgdlast = "";//直供电(旧)
	String zdarealast = "";//站点面积(旧)
	String qyztlast = "";//站点启用状态(旧)
	
	//11大类
	String g2last = "";//2G(旧)
	String g3last = "";//3G(旧)
	String zplast = "";//载频(旧)
	String zslast = "";//载扇(旧)
	String changjialast = "";//厂家(旧)
	String jztypelast = "";//基站类型2(旧)
	String shebeilast = "";//设备编码(旧)
	String bbulast = "";//BBU数量(旧)
	String rrulast = "";//RRU数量(旧)
	String ydshebeilast = "";//移动设备数量(旧)
	String gxgwsllast = "";//共享固网设备数量(旧)
	
			//市级审核
	String sfbgnow = "";//是否标杆(新)
	String dianjianow = "";//单价(新)
	String edhdlnow = "";//额定耗电量(新)
	String zlfhnow = "";//交流负荷(新)
	String jlfhnow = "";//直流负荷(新)
	String dfzflxnow = "";//电费支付类型(新)
	
	//省级审核
	String zdsxnow = "";//站点属性(新)
	String zdlxnow = "";//站点类型(新)
	String yflxnow = "";//用房类型(新)
	String gxxxnow = "";//共享信息(新)
	String gdfsnow = "";//供电方式(新)
	String zgdnow = "";//直供电(新)
	String zdareanow = "";//站点面积(新)
	String qyztnow = "";//站点启用状态(新)
	
	//11大类
	String g2now = "";//2G(新)
	String g3now = "";//3G(新)
	String zpnow = "";//载频(新)
	String zsnow = "";//载扇(新)
	String changjianow = "";//厂家(新)
	String jztypenow = "";//基站类型2(新)
	String shebeinow = "";//设备编码(新)
	String bbunow = "";//BBU数量(新)
	String rrunow = "";//RRU数量(新)
	String ydshebeinow = "";//移动设备数量(新)
	String gxgwslnow = "";//共享固网设备数量(新)
	
	String reasonanalyse = "";//修改原因
	String reasonanalyseyj = "";//修改依据
	
	String biaoti = "";//标题
	String zdname = "";//站点名称
	String diquzdid = "";//所在地区的站点
	String zt = "";//状态
	String zt1 = "";//状态编码
	
	String fjmc = "";//附件名称
	String filepath = "";//附件路径
	String newqsdbdl = "";//全省定标电量
	String oldqsdbdl = "";//全省定标电量
	String dbid="",dbds="";//电表ID,电表读数
	double d,dd;
	double qsdb;
	String oldqsdb="";
	String qd="",zdbzw="";
	String zdidcz = request.getParameter("id") != null ? request.getParameter("id"): "";//前台传值唯一标识id
	Zdqxcxxg bean = new Zdqxcxxg();
	ShiQuery dao=new ShiQuery();
	int r=dao.CheckQs(zdidcz);
	 jcnh jc=new jcnh();
		String nhjc="0",sjz="0",jyscb="0";//能耗基础值  实际值  建议生产标

	//获取站点信息
	if(zdidcz!=null){
        	// 站点信息
        Zdqxkz zdinfo = new Zdqxkz();
        Zdqxkz zdinfoa = new Zdqxkz();
        zdinfo = bean.modifyZdqxcxxgxx(zdidcz);
        //zdinfoa=bean.getZdqxcxxgxxgx(zdidcz);
        if(zdinfo!=null){  
        	zdbzw=zdinfo.getZdbzw();
        	zdid = zdinfo.getZdid();//zdid
			zdid = zdid != null ? zdid : "";
		     jc=bean.getZdqxkzbl(zdid);//能耗基础值  实际值  建议生产标
		     if(jc!=null){
		     	nhjc=jc.getNhjcz();
		 		sjz=jc.getSjz();
		 		jyscb=jc.getJyscb();
		 			if(null==nhjc||nhjc.equals("")){
		 				nhjc="0";
		 			}
		 			if(null==sjz||sjz.equals("")){
		 				sjz="0";
		 			}
		 			if(null==jyscb||jyscb.equals("")){
		 				jyscb="0";
		 			}
		     }
		     
		     String[] hh = bean.getZggd(zdid);//获取整改工单最小生产标及工单id
		     String gdscb = hh[1];//工单生产标
		     String zggdid = hh[0];//整改工单id
        	
             dbid=zdinfo.getDbid();
             dbid = dbid != null ? dbid : "";
             //旧站点信息
			//市级审核			
			sfbglast = zdinfo.getOldbgsign();//是否标杆
			sfbglast = sfbglast != null ? sfbglast : "0";
			
			dianjialast = zdinfo.getOlddianfei();//单价
			if(dianjialast==null||"".equals(dianjialast)||" ".equals(dianjialast)||"null".equals(dianjialast)||"o".equals(dianjialast))dianjialast="0.0000";
			DecimalFormat cb = new DecimalFormat("0.0000");
			dianjialast = cb.format(Double.parseDouble(dianjialast));	
			
			edhdllast = zdinfo.getOldedhdl();//额定耗电量
			if(edhdllast==null||"".equals(edhdllast)||" ".equals(edhdllast)||"null".equals(edhdllast)||"o".equals(edhdllast)) edhdllast="0.00";
			DecimalFormat dj = new DecimalFormat("0.00");
			edhdllast = dj.format(Double.parseDouble(edhdllast));
			
			zlfhlast = zdinfo.getOldzlfh();//直流负荷
			zlfhlast = zlfhlast != null ? zlfhlast : "";
			
			jlfhlast = zdinfo.getOldjlfh();//交流负荷
			jlfhlast = jlfhlast != null ? jlfhlast : "";
			
			dfzflxlast = zdinfo.getOlddfzflx();
			if("null".equals(dfzflxlast) || null == dfzflxlast){
				dfzflxlast = "";
			}
						
			//省级审核
			zdsxlast = zdinfo.getOldproperty();//站点属性
			zdsxlast = zdsxlast != null ? zdsxlast : "";
			
			zdlxlast = zdinfo.getOldstationtype();//站点类型
			zdlxlast = zdlxlast != null ? zdlxlast : "";
						
			yflxlast = zdinfo.getOldyflx();//用房类型
			yflxlast = yflxlast != null ? yflxlast : "";
						
			gxxxlast = zdinfo.getOldgxxx();//共享信息
			gxxxlast = gxxxlast != null ? gxxxlast : "";
			
			gdfslast = zdinfo.getOldgdfs();//供电方式
			gdfslast = gdfslast != null ? gdfslast : "";
			
			zgdlast = zdinfo.getOldzgd();//直供电
			zgdlast = zgdlast != null ? zgdlast : "0";
			
			zdarealast = zdinfo.getOldarea();//站点面积
			zdarealast = zdarealast != null ? zdarealast : "0";
						
			qyztlast = zdinfo.getOldqyzt();//站点启用状态 
			qyztlast = qyztlast != null ? qyztlast : "0";

		    //11大类
			g2last = zdinfo.getOldg2();//2G
			g2last = g2last != null ? g2last : "0";
				
			g3last = zdinfo.getOldg3();//3G
			g3last = g3last != null ? g3last : "0";		
				
			zplast = zdinfo.getOldzpsl();//载频
			zplast = zplast != null ? zplast : "0";	
			
			zslast = zdinfo.getOldzssl();//载扇
			zslast = zslast != null ? zslast : "0";	
			
			changjialast = zdinfo.getOldchangjia();//厂家
			changjialast = changjialast != null ? changjialast : "";
				
			jztypelast = zdinfo.getOldjzlx();//基站类型2
			jztypelast = jztypelast != null ? jztypelast : "";	
					
			shebeilast = zdinfo.getOldshebei();//设备编码
			shebeilast = shebeilast != null ? shebeilast : "";	
						
			bbulast = zdinfo.getOldbbu();//BBU数量
			bbulast = bbulast != null ? bbulast : "0";
							
			rrulast = zdinfo.getOldrru();//RRU数量
			rrulast = rrulast != null ? rrulast : "0";
			
			ydshebeilast = zdinfo.getOldydshebei();//移动设备数量
			ydshebeilast = ydshebeilast != null ? ydshebeilast : "0";
			
			gxgwsllast = zdinfo.getOldgxgwsl();//共享固网设备数量	
			gxgwsllast = gxgwsllast != null ? gxgwsllast : "0";	
			
			oldqsdbdl=zdinfo.getOldqsdbdl()!=null?zdinfo.getOldqsdbdl():"";//全省定标电量

       //新站点信息
			//市级审核			
			sfbgnow = zdinfo.getNewbgsign();//是否标杆
			sfbgnow = sfbgnow != null ? sfbgnow : "0";
			
			dianjianow = zdinfo.getNewdianfei();//单价
			if(dianjianow==null||"".equals(dianjianow)||" ".equals(dianjianow)||"null".equals(dianjianow)||"o".equals(dianjianow))dianjianow="0.0000";
			dianjianow = cb.format(Double.parseDouble(dianjianow));	
			
			edhdlnow = zdinfo.getNewedhdl();//额定耗电量
			if(edhdlnow==null||"".equals(edhdlnow)||" ".equals(edhdlnow)||"null".equals(edhdlnow)||"o".equals(edhdlnow)) edhdlnow="0.00";
			edhdlnow = dj.format(Double.parseDouble(edhdlnow));
			
			zlfhnow = zdinfo.getNewzlfh();//直流负荷
			zlfhnow = zlfhnow != null ? zlfhnow : "0";
			
			jlfhnow = zdinfo.getNewjlfh();//交流负荷
			jlfhnow = jlfhnow != null ? jlfhnow : "0";
			String qsdbd=zdinfo.getNewqsdbdl();
			oldqsdb=zdinfo.getOldqsdbdl();
			if("".equals(oldqsdb)||null==oldqsdb){
			oldqsdb="0";
			}
			if("".equals(qsdbd)||null==qsdbd){
			qsdbd="0";
			}
             DecimalFormat th = new DecimalFormat("0.00");
            qd=th.format(Double.parseDouble(qsdbd));					
			oldqsdb=th.format(Double.parseDouble(oldqsdb));			
			
			dfzflxnow = zdinfo.getNewdfzflx();
			if("null".equals(dfzflxnow) || null == dfzflxnow){
				dfzflxnow = "";
			}
						
						
			//省级审核
			zdsxnow = zdinfo.getNewproperty();//站点属性
			zdsxnow = zdsxnow != null ? zdsxnow : "";
			
			zdlxnow = zdinfo.getNewstationtype();//站点类型
			zdlxnow = zdlxnow != null ? zdlxnow : "";
						
			yflxnow = zdinfo.getNewyflx();//用房类型
			yflxnow = yflxnow != null ? yflxnow : "";
						
			gxxxnow = zdinfo.getNewgxxx();//共享信息
			gxxxnow = gxxxnow != null ? gxxxnow : "";
			
			gdfsnow = zdinfo.getNewgdfs();//供电方式
			gdfsnow = gdfsnow != null ? gdfsnow : "";
			
			zgdnow = zdinfo.getNewzgd();//直供电
			zgdnow = zgdnow != null ? zgdnow : "0";
			
			zdareanow = zdinfo.getNewarea();//站点面积
			zdareanow = zdareanow != null ? zdareanow : "0";
						
			qyztnow = zdinfo.getNewqyzt();//站点启用状态 
			qyztnow = qyztnow != null ? qyztnow : "0";

		    //11大类
			g2now = zdinfo.getNewg2();//2G
			g2now = g2now != null ? g2now : "0";
				
			g3now = zdinfo.getNewg3();//3G
			g3now = g3now != null ? g3now : "0";		
				
			zpnow = zdinfo.getNewzpsl();//载频
			zpnow = zpnow != null ? zpnow : "0";	
			
			zsnow = zdinfo.getNewzssl();//载扇
			zsnow = zsnow != null ? zsnow : "0";	
			
			changjianow = zdinfo.getNewchangjia();//厂家
			changjianow = changjianow != null ? changjianow : "";
				
			jztypenow = zdinfo.getNewjzlx();//基站类型2
			jztypenow = jztypenow != null ? jztypenow : "";	
					
			shebeinow = zdinfo.getNewshebei();//设备编码
			shebeinow = shebeinow != null ? shebeinow : "";	
						
			bbunow = zdinfo.getNewbbu();//BBU数量
			bbunow = bbunow != null ? bbunow : "0";
							
			rrunow = zdinfo.getNewrru();//RRU数量
			rrunow = rrunow != null ? rrunow : "0";
			
			ydshebeinow = zdinfo.getNewydshebei();//移动设备数量
			ydshebeinow = ydshebeinow != null ? ydshebeinow : "0";
			
			gxgwslnow = zdinfo.getNewgxgwsl();//共享固网设备数量	
			gxgwslnow = gxgwslnow != null ? gxgwslnow : "0";	
			
			zdname = zdinfo.getZdname();//站点名称
			zdname = zdname != null ? zdname : "";
			
			biaoti = zdinfo.getBiaoti();//所在地区的站点
			biaoti = biaoti != null ? biaoti : "";	
			
			reasonanalyse = zdinfo.getXgsm();//修改原因
			reasonanalyse = reasonanalyse != null ? reasonanalyse : "";	
			
			reasonanalyseyj = zdinfo.getXgyj();//修改依据
			reasonanalyseyj = reasonanalyseyj != null ? reasonanalyseyj : "";	
			
			newqsdbdl = zdinfo.getNewqsdbdl()!=null?zdinfo.getNewqsdbdl():"";//全省定标电量
			
			
			int j = biaoti.indexOf("--");
            diquzdid = biaoti.substring(0,j+2);
            
            zt = biaoti.substring(j+2,biaoti.length());
            if(zt=="增加"||"增加".equals(zt)){
            	zt1 = "1";
            }else if(zt=="修改"||"修改".equals(zt)){
            	zt1 = "2";
            }else if(zt=="减少"||"减少".equals(zt)){
            	zt1 = "3";
            }
            
            fjmc = zdinfo.getFjmc();//附件名称
			fjmc = fjmc != null ? fjmc : "";
			
            filepath = zdinfo.getFilepath();//附件路径
			filepath = filepath != null ? filepath : "";
            
		 	dbds = zdinfo.getDbds();//附件名称
		 	dbds = dbds != null ? dbds : "";
			
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
	width: 100px;
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
.formm1 {
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
	width: 300px;
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
	function shangchuan(){
		var zggdid = document.form1.zggdid.value;
		document.form1.fzbzw.value = 0;
		var id = '<%=zdidcz%>';
		var zdid = '<%=zdid%>';
		var fjmc = '<%=fjmc%>';
		var filepath = '<%=filepath%>';
		var bzw1 = "0";
		var bzw2 = "0";
	    var bzw3 = "0";
		var bzw4 = "0";
		
		var diquzdid = document.form1.diquzdid.value;
		var zt = document.form1.zt.value;
		
		var sfbglast = document.form1.sfbglast.value;
		var sfbgnow = document.form1.sfbgnow.value;
		
		var dianjialast = document.form1.dianjialast.value;
		var dianjianow = document.form1.dianjianow.value;
		
		var edhdllast = document.form1.edhdllast.value;
		var edhdlnow = document.form1.edhdlnow.value;
		
		var zlfhlast = document.form1.zlfhlast.value;
		var zlfhnow = document.form1.zlfhnow.value;
		
		var jlfhlast = document.form1.jlfhlast.value;
		var jlfhnow = document.form1.jlfhnow.value;
		
		var dfzflxlast = document.form1.dfzflxlast.value;
		var dfzflxnow = document.form1.dfzflxnow.value;
		
		var zdsxlast = document.form1.zdsxlast.value;
		var zdsxnow = document.form1.zdsxnow.value;
		
		var zdlxlast = document.form1.zdlxlast.value;
		var zdlxnow = document.form1.zdlxnow.value;
				
		var yflxlast = document.form1.yflxlast.value;
		var yflxnow = document.form1.yflxnow.value;
				
		var gxxxlast = document.form1.gxxxlast.value;
		var gxxxnow = document.form1.gxxxnow.value;
		
		var gdfslast = document.form1.gdfslast.value;
		var gdfsnow = document.form1.gdfsnow.value;
				
		var zgdlast = document.form1.zgdlast.value;
		var zgdnow = document.form1.zgdnow.value;		
		
		var zdarealast = document.form1.zdarealast.value;
		var zdareanow = document.form1.zdareanow.value;
		
		var qyztlast = document.form1.qyztlast.value;
		var qyztnow = document.form1.qyztnow.value;
		
		var g2last = document.form1.g2last.value;
		var g2now = document.form1.g2now.value;	
			
		var g3last = document.form1.g3last.value;
		var g3now = document.form1.g3now.value;	
			
		var zplast = document.form1.zplast.value;
		var zpnow = document.form1.zpnow.value;
		
		var zslast = document.form1.zslast.value;
		var zsnow = document.form1.zsnow.value;			
		
		var changjialast = document.form1.changjialast.value;
		var changjianow = document.form1.changjianow.value;	
			
		var jztypelast = document.form1.jztypelast.value;
		var jztypenow = document.form1.jztypenow.value;
		
		var shebeilast = document.form1.shebeilast.value;
		var shebeinow = document.form1.shebeinow.value;
		
		var bbulast = document.form1.bbulast.value;
		var bbunow = document.form1.bbunow.value;
		
		var rrulast = document.form1.rrulast.value;
		var rrunow = document.form1.rrunow.value;
		
		var ydshebeilast = document.form1.ydshebeilast.value;
		var ydshebeinow = document.form1.ydshebeinow.value;
					
		var gxgwsllast = document.form1.gxgwsllast.value;	
		var gxgwslnow = document.form1.gxgwslnow.value;		
				
		var reasonanalyse = document.form1.reasonanalyse.value;
		var reasonanalyseyj = document.form1.reasonanalyseyj.value;
		
		var ss = document.all("errorInfo").innerText; 
		
		//var newqsdbdl=document.form1.newqsdbdl.value;
		// var oldqsdbdl=document.form1.oldqsdbdl.value;
		
		var newqsdbdl=document.form1.qsdb.value;
		var oldqsdbdl=document.form1.oldqsdb.value;
		var dbds=document.form1.dbds.value;
		var tidc=document.form1.tidc.value;
		var zdbzw=document.form1.zdhbbzw.value;//站点核标标志位
		var nhjc=document.form1.nhjc.value;//站点核标标志位
			var sjz=document.form1.sjz.value;//站点核标标志位
				var jyscb=document.form1.jyscb.value;//站点核标标志位
				
				var nh=((Number(nhjc)-Number(newqsdbdl))/Number(newqsdbdl))*100;
				var nhbl=nh.toFixed(2);
				var sj=((Number(sjz)-Number(newqsdbdl))/Number(newqsdbdl))*100;
				var sjbl=sj.toFixed(2);
				var jy=((Number(jyscb)-Number(newqsdbdl))/Number(newqsdbdl))*100;
				var jybl=jy.toFixed(2);
				if(Number(nhjc)==0){nhbl=0;}
				if(Number(sjz)==0){sjbl=0;}
				if(Number(jyscb)==0){jybl=0;}
				var zl=(zlfhnow*1.52).toFixed(2);var jl=(jlfhnow*5.28).toFixed(2);
				var zldl=((Number(zl)-Number(newqsdbdl))/Number(newqsdbdl))*100;
				var jldl=((Number(jl)-Number(newqsdbdl))/Number(newqsdbdl))*100;
				var zlbl=zldl.toFixed(2);
				var jlbl=jldl.toFixed(2);
		 var lg='<%=accountid%>';
		if(tidc=="1"){
			alert("附件大于6M！");
			return;
		}else{
		if(ss.indexOf('增加权限控制站点信息成功！')==-1){
		$("#errorInfo").text("");
		if(checkNotnull(document.form1.zt,"标题状态")&&checkNotSelected(document.form1.zt,"标题状态")
			&&checkNotnull(document.form1.sfbgnow,"是否标杆")&&checkNotnull(document.form1.zgdnow,"直供电")	
			&&checkNotnull(document.form1.dianjianow,"单价")&&checkNotnull(document.form1.edhdlnow,"额定耗电量")
			&&checkNotnull(document.form1.zlfhnow,"直流负荷")&&checkNotnull(document.form1.jlfhnow,"交流负荷")
			&&checkNotnull(document.form1.zdsxnow,"站点属性")&&checkNotSelected(document.form1.zdsxnow,"站点属性")
			&&checkNotSelected(document.form1.dfzflxnow,"电费支付类型")
			&&checkNotnull(document.form1.zdlxnow,"站点类型")&&checkNotSelected(document.form1.zdlxnow,"站点类型")
			&&checkNotnull(document.form1.yflxnow,"用房类型")&&checkNotSelected(document.form1.yflxnow,"用房类型")
			&&checkNotnull(document.form1.gdfsnow,"供电方式")&&checkNotSelected(document.form1.gdfsnow,"供电方式")
			&&checkNotnull(document.form1.gxxxnow,"共享信息")&&checkNotSelected(document.form1.gxxxnow,"共享信息")
			&&checkNotnull(document.form1.zdareanow,"站点面积")&&checkNotnull(document.form1.qsdb,"省定标(不含空调)")&&checkNotnull(document.form1.qyztnow,"站点启用状态")
			&&checkNotnull(document.form1.g2now,"2G设备")&&checkNotnull(document.form1.zgdnow,"直供电")
			&&checkNotnull(document.form1.g3now,"3G设备")&&checkNotnull(document.form1.zpnow,"载频")
			&&checkNotnull(document.form1.zsnow,"载扇")
			//&&checkNotnull(document.form1.changjianow,"厂家")&&checkNotnull(document.form1.jztypenow,"基站类型")&&checkNotnull(document.form1.shebeinow,"设备编码")
			&&checkNotnull(document.form1.bbunow,"BBU数量")
			&&checkNotnull(document.form1.rrunow,"RRU数量")&&checkNotnull(document.form1.ydshebeinow,"移动设备数量")
			&&checkNotnull(document.form1.gxgwslnow,"共享固网数量")&&checkNotnull(document.form1.reasonanalyse,"修改原因")
			&&checkNotnull(document.form1.reasonanalyseyj,"修改依据")		
			){   					
	        if(parseInt(zpnow)==zpnow&&isNaN(zpnow)==false){
	        	if(quztAndScb()){
	        	if(parseInt(zsnow)==zsnow&&isNaN(zsnow)==false){
	        		if(parseInt(bbunow)==bbunow&&isNaN(bbunow)==false){
	        			if(parseInt(rrunow)==rrunow&&isNaN(rrunow)==false){
	        				if(parseInt(ydshebeinow)==ydshebeinow&&isNaN(ydshebeinow)==false){
	        					if(parseInt(gxgwslnow)==gxgwslnow&&isNaN(gxgwslnow)==false){
	        					   if(isNaN(dianjianow)==false){
	        					   		if(qyztAndEdhdl()){
	        					   			if(isNaN(zlfhnow)==false){
	        					   				if(isNaN(jlfhnow)==false){
	        					   					if(isNaN(zdareanow)==false){
	        					   						if(isNaN(dbds)==false){
	        					   						var	bzw = "0";        					   							
														if((sfbglast!= sfbgnow)||(Number(dianjialast)!= Number(dianjianow))||(Number(edhdllast)!= Number(edhdlnow))||(Number(zlfhlast)!= Number(zlfhnow))||(Number(jlfhlast)!= Number(jlfhnow))||(dfzflxlast !=dfzflxnow)||dbds!=""){
															bzw1 = "1";
														}
														if((zdsxlast!= zdsxnow)||(zdlxlast!= zdlxnow)||(yflxlast!= yflxnow)||(gxxxlast!= gxxxnow)||(gdfslast!= gdfsnow)||(zgdlast!= zgdnow)
														||(Number(zdarealast)!= Number(zdareanow))||(qyztlast!= qyztnow)||(g2last!= g2now)||(g3last!= g3now)||(zplast!= zpnow)||(Number(oldqsdbdl)!= Number(newqsdbdl))||(zslast!= zsnow)
														||(changjialast!= changjianow)||(jztypelast!= jztypenow)||(shebeilast!= shebeinow)||(bbulast!= bbunow)||(rrulast!= rrunow)
														||(ydshebeilast!= ydshebeinow)||(gxgwsllast!= gxgwslnow)){
															bzw2 = "1";
														}
														if(bzw1=="1"&&bzw2=="0"){
															bzw = "1";
														}else if((bzw1=="0"||bzw1=="1")&&bzw2=="1"){
															bzw = "2";
														}
														
															//降低标杆  单纯的降低标杆 oldqsdb>qsdb 
														if(
														(sfbglast== sfbgnow)&&(Number(dianjialast)== Number(dianjianow))&&(Number(zlfhlast)== Number(zlfhnow))&&(Number(jlfhlast)== Number(jlfhnow))&&dbds==""&&
														(zdsxlast== zdsxnow)&&(zdlxlast== zdlxnow)&&(yflxlast== yflxnow)&&(gxxxlast== gxxxnow)&&(gdfslast== gdfsnow)&&(zgdlast== zgdnow)&&
														(Number(zdarealast)== Number(zdareanow))&&(qyztlast== qyztnow)&&(g2last== g2now)&&(g3last== g3now)&&(zplast== zpnow)&&(zslast== zsnow)&&
														(changjialast== changjianow)&&(jztypelast== jztypenow)&&(shebeilast== shebeinow)&&(bbulast== bbunow)&&(rrulast== rrunow)&&
														(ydshebeilast== ydshebeinow)&&(gxgwsllast== gxgwslnow)&&(dfzflxlast== dfzflxnow)&&(Number(oldqsdbdl)>Number(newqsdbdl))
														  
														){
															 bzw3='1';
														}
														//降低标杆 同时交流负荷或者直流负荷改变
														if(
														(sfbglast== sfbgnow)&&(Number(dianjialast)== Number(dianjianow))&&dbds==""&&
														(zdsxlast== zdsxnow)&&(zdlxlast== zdlxnow)&&(yflxlast== yflxnow)&&(gxxxlast== gxxxnow)&&(gdfslast== gdfsnow)&&(zgdlast== zgdnow)&&
														(Number(zdarealast)== Number(zdareanow))&&(qyztlast== qyztnow)&&(g2last== g2now)&&(g3last== g3now)&&(zplast== zpnow)&&(zslast== zsnow)&&
														(changjialast== changjianow)&&(jztypelast== jztypenow)&&(shebeilast== shebeinow)&&(bbulast== bbunow)&&(rrulast== rrunow)&&
														(ydshebeilast== ydshebeinow)&&(gxgwsllast== gxgwslnow)&&(dfzflxlast== dfzflxnow)&&(Number(oldqsdbdl)>Number(newqsdbdl))
														  
														){
															 bzw3='2';
														}
														//单纯升高标杆 升高标杆
															if(
														(sfbglast== sfbgnow)&&(Number(dianjialast)== Number(dianjianow))&&dbds==""&&
														(zdsxlast== zdsxnow)&&(zdlxlast== zdlxnow)&&(yflxlast== yflxnow)&&(gxxxlast== gxxxnow)&&(gdfslast== gdfsnow)&&(zgdlast== zgdnow)&&
														(Number(zdarealast)== Number(zdareanow))&&(qyztlast== qyztnow)&&(g2last== g2now)&&(g3last== g3now)&&(zplast== zpnow)&&(zslast== zsnow)&&
														(changjialast== changjianow)&&(jztypelast== jztypenow)&&(shebeilast== shebeinow)&&(bbulast== bbunow)&&(rrulast== rrunow)&&
														(ydshebeilast== ydshebeinow)&&(gxgwsllast== gxgwslnow)&&(dfzflxlast== dfzflxnow)&&(Number(oldqsdbdl)<Number(newqsdbdl))
														  
														){
															 bzw4='1';
														}
														if(bzw3=="1"||bzw3=="2"){
															bzw="3";
														}
														if(bzw4=="1"){
															bzw="4";
														}
														alert("能耗基础值占比:"+Math.abs(nhbl)+"%"+"\n"+"实际值占比:"+Math.abs(sjbl)+"%"+"\n"+"建议生产标占比:"+Math.abs(jybl)+"%"+"\n"+"直流电量占比:"+Math.abs(zlbl)+"%"+"\n"+"交流电量占比:"+Math.abs(jlbl)+"%"+"\n"+"能耗基础值:"+Number(nhjc).toFixed(2)+" 实际值:"+Number(sjz).toFixed(2)+" 建议生产标:"+Number(jyscb).toFixed(2)+"交流电量:"+Number(jl).toFixed(2)+"直流电量"+Number(zl).toFixed(2));
														if(bzw != "0"){
															if(sbblgx()){
																var fzbzw = document.form1.fzbzw.value;
														   if(confirm("您将要添加信息！确认信息正确！")){
														         document.form1.action=path+"/servlet/zdqxcxxg?action=modifyzd&id="+id+"&zdid="+zdid+"&sfbglast="+sfbglast+"&sfbgnow="+sfbgnow
														         +"&dianjialast="+dianjialast+"&dianjianow="+dianjianow+"&edhdllast="+edhdllast+"&edhdlnow="+edhdlnow
														         +"&zlfhlast="+zlfhlast+"&zlfhnow="+zlfhnow+"&jlfhlast="+jlfhlast+"&jlfhnow="+jlfhnow
														         +"&zdsxlast="+zdsxlast+"&zdsxnow="+zdsxnow+"&zdlxlast="+zdlxlast+"&zdlxnow="+zdlxnow		         
														         +"&yflxlast="+yflxlast+"&yflxnow="+yflxnow+"&gxxxlast="+gxxxlast+"&gxxxnow="+gxxxnow
														         +"&gdfslast="+gdfslast+"&gdfsnow="+gdfsnow+"&zgdlast="+zgdlast+"&zgdnow="+zgdnow
														         +"&zdarealast="+zdarealast+"&zdareanow="+zdareanow+"&qyztlast="+qyztlast+"&qyztnow="+qyztnow
														         +"&g2last="+g2last+"&g2now="+g2now+"&g3last="+g3last+"&g3now="+g3now
																 +"&zplast="+zplast+"&zpnow="+zpnow+"&zslast="+zslast+"&qsdb="+newqsdbdl+"&oldqsdb="+oldqsdbdl+"&zsnow="+zsnow
														         +"&changjialast="+changjialast+"&changjianow="+changjianow+"&jztypelast="+jztypelast+"&jztypenow="+jztypenow
														         +"&shebeilast="+shebeilast+"&shebeinow="+shebeinow+"&bbulast="+bbulast+"&bbunow="+bbunow
																 +"&rrulast="+rrulast+"&rrunow="+rrunow+"&ydshebeilast="+ydshebeilast+"&ydshebeinow="+ydshebeinow		         
														         +"&gxgwsllast="+gxgwsllast+"&gxgwslnow="+gxgwslnow+"&bzw="+bzw+"&diquzdid="+diquzdid+"&zt="+zt+"&dbds="+dbds+"&zdbzw="+zdbzw+"&fjmc="+fjmc
														         +"&lg="+lg+"&fzbzw="+fzbzw+"&zggdid="+zggdid
														         +"&filepath="+filepath+"&reasonanalyse="+reasonanalyse+"&reasonanalyseyj="+reasonanalyseyj+"&dfzflxlast="+dfzflxlast+"&dfzflxnow="+dfzflxnow;
								
																 document.form1.submit();
														     }
														   }
														 }else{
														 	alert("您未更改任何信息！");
														 }
														}else{
								        				alert("您输入的信息有误，电表读数必须为数字！");
								        		 	  }
													  }else{
								        				alert("您输入的信息有误，站点面积必须为数字！");
								        		 	  }
												  }else{
							        				alert("您输入的信息有误，交流负荷必须为数字！");
							        		 	  }
											  }else{
							        			alert("您输入的信息有误，直流负荷必须为数字！");
							        		  }
										}
							        }else{
							        	alert("您输入的信息有误，单价必须为数字！");
							        }
								 }else{
					   				alert("您输入的信息有误，共享固网数量必须为数字！");
					   		     }
							}else{
				   				alert("您输入的信息有误，移动设备数量必须为数字！");
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
	        }	
		   }else{
		   		alert("您输入的信息有误，载频必须为数字！");
		   }
	    }
	    }else{
	    	alert("您修改的站点信息已经成功提交，不允许重复提交！");
	    }
	    }									     
	}
	
		//站点其中状态为否时,额定耗电量可以为0
	function qyztAndEdhdl(){
		var qyztnow = document.form1.qyztnow.value;//站点启用状态
		var edhdlnow = document.form1.edhdlnow.value;//额定耗电量
		
		if("0"==qyztnow){//站点启用状态为否
			if(parseFloat(edhdlnow)==edhdlnow&&isNaN(edhdlnow)==false&&parseFloat(edhdlnow)>=0){
				return true;
			}else{
				alert("您输入的信息有误，额定耗电量必须为数字且大于等于0！");
				return false;
			}
		}else{//站点启用状态为是
			if(parseFloat(edhdlnow)==edhdlnow&&isNaN(edhdlnow)==false&&parseFloat(edhdlnow)>0){
				return true;
			}else{
				alert("您输入的信息有误，额定耗电量必须为数字且大于0！");
				return false;
			}
		}
	}
	//站点其中状态为否时,生产标可以为0
	function quztAndScb(){
		var qyztnow = document.form1.qyztnow.value;//站点启用状态
		var qsdb= document.form1.qsdb.value;//生产标
		parseFloat(qsdb)==qsdb&&isNaN(qsdb)==false&&parseFloat(qsdb)>0
		if("0"==qyztnow){//站点启用状态为否
			if(parseFloat(qsdb)==qsdb&&isNaN(qsdb)==false&&parseFloat(qsdb)>=0){
				return true;
			}else{
				alert("您输入的信息有误，省定标(不含空调 )必须为数字且大于等于0！");
				return false;
			}
		}else{//站点启用状态为是
			if(parseFloat(qsdb)==qsdb&&isNaN(qsdb)==false&&parseFloat(qsdb)>0){
				return true;
			}else{
				alert("您输入的信息有误，省定标(不含空调 )必须为数字且大于0！");
				return false;
			}
		}
	}
	
	function sbblgx(){
	var gdscb = '<%=gdscb%>';//工单生产标
		if(gdscb!=""){
			//申请生产标—工单生产标）/ 工单生产标*100%。该值绝对值是否大于10%。
			var sqscb = document.form1.qsdb.value;//申请生产标
			var blz = Math.abs((sqscb-gdscb)/gdscb);
			if(blz<0.1){
				document.form1.fzbzw.value = 1;//赋值标志位 1:赋值,0:不赋值
				return true;
			}else{
				alert("整改工单中有省未审核的工单,请先处理!");
			}
		}else{
			return true;
		}	
	}
	
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

<script language="javascript">
var path = '<%=path%>';
$(function() {
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
						<table width="100%" border="0s" cellspacing="1" cellpadding="1">
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									标题：
								</td>
								<td colspan="5"><input type="text" name="diquzdid" readonly="true" value="<%=diquzdid%>" class="form1" />
								<select name="zt" class="formm">
												<option value="0">请选择</option>
								         		<option value="1">增加</option>
								         		<option value="2">修改</option>
								         		<option value="3">减少</option>										         		
								</select></td>
							</tr>
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
											<td>
												 <span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 
													<select name="sfbglast" class="formm">
									         			<option value="0">否</option>
									         			<option value="1">是</option>									         		
									         		</select>
									         	 </span>
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
									         <td><select name="sfbgnow" class="formm" onblur="getGraygys('sfbgnow');" onfocus="getBlack(this);">
									         		<option value="0">否</option>
									         		<option value="1">是</option>									         		
									         </select></td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													单价：
												</div>
											</td>
											<td>
												<input type="text" name="dianjialast" readonly="true"
													value="<%=dianjialast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<input type="text" name="dianjianow" onblur="getGraygys('dianjianow');" onfocus="getBlack(this);" value="<%=dianjianow%>" class="formm" />
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
												<input type="text" name="edhdlnow" onblur="getGraygys('edhdlnow');" onfocus="getBlack(this);" value="<%=edhdlnow%>" class="formm" />
											</td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													交流负荷：
												</div>
											</td>
											<td>
												<input type="text" name="jlfhlast" readonly="true"
													value="<%=jlfhlast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为：
												</div>
											</td>
											<td>
												<input type="text" name="jlfhnow" onblur="getGraygys('jlfhnow');" onfocus="getBlack(this);" value="<%=jlfhnow%>" class="formm" onchange="getJl()"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													直流负荷：
												</div>
											</td>
											<td>
												<input type="text" name="zlfhlast" readonly="true"
													value="<%=zlfhlast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为：
												</div>
											</td>
											<td>
												<input type="text" name="zlfhnow" onblur="getGraygys('zlfhnow');" onfocus="getBlack(this);" value="<%=zlfhnow%>" class="formm" onchange="getZl()"/>
										</td>
										<td bgcolor="#DDDDDD" class="form_label">电费支付类型:
								         </td>
								         <td>
								         <span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 
								         	<select name="dfzflxlast" class="form2">
								         		<option value="0">请选择</option>
								         		<%
									         	ArrayList dfzflxa = new ArrayList();
									         	dfzflxa = ztcommon.getSelctOptions("dfzflx");
									         	if(dfzflxa!=null){
									         		String code="",name="";
									         		int cscount = ((Integer)dfzflxa.get(0)).intValue();
									         		for(int i=cscount;i<dfzflxa.size()-1;i+=cscount)
								                    {
								                    	code=(String)dfzflxa.get(i+dfzflxa.indexOf("CODE"));
								                    	name=(String)dfzflxa.get(i+dfzflxa.indexOf("NAME"));
								                    %>
								                    <option value="<%=code%>"><%=name%></option>
								                    <%}
									         	}
									         %>
								         	
								         	</select>
								         	</span>
								         	</td>
								         	<td bgcolor="#DDDDDD" class="form_labell"><div>改为:</div>
								         </td>
								         <td>
								         	<select name="dfzflxnow" onblur="getGraygys('dfzflxnow');" onfocus="getBlack(this);" class="form2">
								         		<option value="0">请选择</option>
								         		<%
									         	ArrayList dfzflxaa = new ArrayList();
									         	dfzflxaa = ztcommon.getSelctOptions("dfzflx");
									         	if(dfzflxaa!=null){
									         		String code="",name="";
									         		int cscount = ((Integer)dfzflxaa.get(0)).intValue();
									         		for(int i=cscount;i<dfzflxaa.size()-1;i+=cscount)
								                    {
								                    	code=(String)dfzflxaa.get(i+dfzflxaa.indexOf("CODE"));
								                    	name=(String)dfzflxaa.get(i+dfzflxaa.indexOf("NAME"));
								                    %>
								                    <option value="<%=code%>"><%=name%></option>
								                    <%}
									         	}
									         %>
								         	
								         	</select>
								         	</td>
										
									</tr>
									<tr>
									<td bgcolor="#DDDDDD" class="form_label">站点ID:</td>
										<td>
										<input type="text"  readonly="true" 
													value="<%=zdid%>" class="form2" />
													</td>
										<td bgcolor="#DDDDDD" class="form_label">电表ID:</td>
										<td>
										<input type="text"  readonly="true" 
													value="<%=dbid%>" class="form2" />
									</td>
										<td bgcolor="#DDDDDD" class="form_label">
										电表读数 修改为
										</td>
										<td>
										<input type="text" name="dbds" value="<%=dbds%>" class="form2" />
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
									<span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 
							         	<select name="yflxlast" class="form2">
							         		<option value="0">请选择</option>
							         		<%
								         	ArrayList fwlx1 = new ArrayList();
								         	fwlx1 = ztcommon.getSelctOptions("FWLX");
								         	if(fwlx1!=null){
								         		String code="",name="";
								         		int cscount = ((Integer)fwlx1.get(0)).intValue();
								         		for(int i=cscount;i<fwlx1.size()-1;i+=cscount)
							                    {
							                    	code=(String)fwlx1.get(i+fwlx1.indexOf("CODE"));
							                    	name=(String)fwlx1.get(i+fwlx1.indexOf("NAME"));
							                    %>
							                    <option value="<%=code%>"><%=name%></option>
							                    <%}
								         	}
								         %>
							         	</select>	
									 </span>
								</td>
									<td bgcolor="#DDDDDD" class="form_labell">
									<div>
										改为：
									</div>
								</td>
						         <td>									         		
						         	<select name="yflxnow" class="form2" onblur="getGraygys('yflxnow');" onfocus="getBlack(this);">
						         		<option value="0">请选择</option>
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
											<td>
												<span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 				
													<select name="zdsxlast" class="form2">
										         		<option value="0">请选择</option>
										         		<%
											         	ArrayList zdsx1 = new ArrayList();
											         	zdsx1 = ztcommon.getSelctOptions("ZDSX");
											         	if(zdsx1!=null){
											         		String code="",name="";
											         		int cscount = ((Integer)zdsx1.get(0)).intValue();
											         		for(int i=cscount;i<zdsx1.size()-1;i+=cscount)
										                    {
										                    	code=(String)zdsx1.get(i+zdsx1.indexOf("CODE"));
										                    	name=(String)zdsx1.get(i+zdsx1.indexOf("NAME"));
										                    %>
										                    <option value="<%=code%>"><%=name%></option>
										                    <%}
											         		}
											        	 %>	
										        	 </select>
										        </span>
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
									         <td>
									         	<select name="zdsxnow" class="form2" onchange="zdsx()" onblur="getGraygys('zdsxnow');" onfocus="getBlack(this);">
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
											<td>
												<span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 				
										         	<select name="zdlxlast" class="form2">
										         		<option value="0">请选择</option>
										         		<%
											         	ArrayList stationtype1 = new ArrayList();
											         	stationtype1 = ztcommon.getZdlx(zdsxlast);
											         	if(stationtype1!=null){
											         		String code="",name="";
											         		int cscount = ((Integer)stationtype1.get(0)).intValue();
											         		for(int i=cscount;i<stationtype1.size()-1;i+=cscount)
										                    {
										                    	code=(String)stationtype1.get(i+stationtype1.indexOf("CODE"));
										                    	name=(String)stationtype1.get(i+stationtype1.indexOf("NAME"));
										                    %>
										                    <option value="<%=code%>"><%=name%></option>
										                    <%}
											         	}
											         %>
										         	</select>
										        </span>
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
									         <td>
									         	<select name="zdlxnow" class="formr"  onblur="getGraygys('zdlxnow');" onfocus="getBlack(this);">
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
											<td>
												<span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 				
										         	<select name="gxxxlast" class="form2">
											         		<option value="0">请选择</option>
											         		<%
												         	ArrayList gxxx1 = new ArrayList();
												         	gxxx1 = ztcommon.getSelctOptions("gxxx");
												         	if(gxxx1!=null){
												         		String code1="",name1="";
												         		int cscount = ((Integer)gxxx1.get(0)).intValue();
												         		for(int i=cscount;i<gxxx1.size()-1;i+=cscount)
											                    {
											                    	code1=(String)gxxx1.get(i+gxxx1.indexOf("CODE"));
											                    	name1=(String)gxxx1.get(i+gxxx1.indexOf("NAME"));
											                    %>
											                    <option value="<%=code1%>"><%=name1%></option>
											                    <%}
												         	}
												         %>
												    </select>	
										        </span>
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
								           <td>
								         	<select name="gxxxnow" class="form2" onblur="getGraygys('gxxxnow');" onfocus="getBlack(this);">
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
											<td>
												<span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 				
										         	<select name="gdfslast" class="form2">
										         		<option value="0">请选择</option>
										         		<%
											         	ArrayList gdfs1 = new ArrayList();
											         	gdfs1 = ztcommon.getSelctOptions("GDFS");
											         	if(gdfs1!=null){
											         		String code="",name="";
											         		int cscount = ((Integer)gdfs1.get(0)).intValue();
											         		for(int i=cscount;i<gdfs1.size()-1;i+=cscount)
										                    {
										                    	code=(String)gdfs1.get(i+gdfs1.indexOf("CODE"));
										                    	name=(String)gdfs1.get(i+gdfs1.indexOf("NAME"));
										                    %>
										                    <option value="<%=code%>"><%=name%></option>
										                    <%}
											         	}
											         %>									
										         	</select>	
										        </span>
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
									        <td>
									         	<select name="gdfsnow" onblur="getGraygys('gdfsnow');" onfocus="getBlack(this);" class="form2">
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
												<span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 				
								         		 <select name="zgdlast" class="form2">
									         		 <option value="0">否</option>
									         		 <option value="1">是</option>								         		
								         		 </select>	
										        </span>
											</td>
												<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为：
												</div>
											</td>
							         		 <td>
								         		 <select name="zgdnow" class="form2" onblur="getGraygys('zgdnow');" onfocus="getBlack(this);">
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
											<td>
												<input type="text" name="zdarealast" readonly="true"
													value="<%=zdarealast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
											<td>
												<input type="text" name="zdareanow" onblur="getGraygys('zdareanow');" onfocus="getBlack(this);" class="formm" value="<%=zdareanow%>"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													站点启用状态：
												</div>
											</td>
											<td>
												<span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 				
									         		 <select name="qyztlast" class="form2">
										         		 <option value="0">否</option>
										         		 <option value="1">是</option>								         		
									         		 </select>
									         	</span>	
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
								            <td>
								         	    <select name="qyztnow" onblur="getGraygys('qyztnow');" onfocus="getBlack(this);" class="form2">
								         		<option value="1">是</option>
								         		<option value="0">否</option>
								         	   </select>
								            </td>
								             <td bgcolor="#DDDDDD" class="form_label">
												<div>
													省定标(不含空调)：
												</div>
											</td>
											<TD> <input type="TEXT" name="oldqsdb"  value='<%=oldqsdb%>' readonly="true" class="form2"/>
											</TD>
												<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<input type="text" name="qsdb" value="<%=qd%>" class="formm" onblur="getGrayqsdb(this);" onfocus="getBlack(this);"/>
											</td>
								        
								       <!--       <td bgcolor="#DDDDDD" class="form_label">
												<div>
													全省定标电量：
												</div>
											</td>
											<td>
												<input type="text" name="oldqsdbdl" readonly="true"
													value="<%=oldqsdbdl%>" class="form2" />
											</td>
												<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为：
												</div>
											</td>
											<td>
												<input type="text" name="newqsdbdl" value="<%=newqsdbdl%>" class="formm" onblur="getGrayqsdb(this);" onfocus="getBlack(this);"/>
											</td>	
											-->										
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
											<td>
												<span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 				
													<select name="g2last" class="form3">
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
									         	</span>														
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
											<td>
												<select name="g2now" class="form3" onblur="getGray(this);" onfocus="getBlack(this);">
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
											<td>
												<span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 				
													<select name="g3last" class="form3">
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
									         	</span>	
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<select name="g3now" class="form3" onblur="getGrayg3(this);" onfocus="getBlack(this);">
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
											<td>
												<input type="text" name="zpnow" value="<%=zpnow%>" class="formm" onblur="getGrayzp(this);" onfocus="getBlack(this);"/>
											</td>
										</tr>
										<tr>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													载扇：
												</div>
											</td>
											<td>
												<input type="text" name="zslast" readonly="true"
													value="<%=zslast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
											<td>
												<input type="text" name="zsnow" value="<%=zsnow%>" class="formm" onblur="getGrayzs(this);" onfocus="getBlack(this);"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													厂家：
												</div>
											</td>
											<td>
												<span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 				
													<select name="changjialast" class="form3">
														<option value="0">
															请选择
														</option>
														<%
															ArrayList cj1 = new ArrayList();
															cj1 = ztcommon.getSelctOptions("cj");
															if (cj1 != null) {
																String code = "", name = "";
																int cscount = ((Integer) cj1.get(0)).intValue();
																for (int i = cscount; i < cj1.size() - 1; i += cscount) {
																	code = (String) cj1.get(i + cj1.indexOf("CODE"));
																	name = (String) cj1.get(i + cj1.indexOf("NAME"));
														%>
														<option value="<%=code%>"><%=name%></option>
														<%
															}
															}
														%>
													</select>	
									         	</span>														
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<select name="changjianow" class="form3" onblur="getGraygys('changjianow');" onfocus="getBlack(this);">
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
											<td>
												<span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 				
													<select class="form3" name="jztypelast">
														<option value="0">
															请选择
														</option>
														<%
															ArrayList jzlx21 = new ArrayList();
															jzlx21 = ztcommon.getSelctOptions("jzlx2");
															if (jzlx21 != null) {
																String code = "", name = "";
																int cscount = ((Integer) jzlx21.get(0)).intValue();
																for (int i = cscount; i < jzlx21.size() - 1; i += cscount) {
																	code = (String) jzlx21.get(i + jzlx21.indexOf("CODE"));
																	name = (String) jzlx21.get(i + jzlx21.indexOf("NAME"));
														%>
														<option value="<%=code%>"><%=name%></option>
														<%
															}
															}
														%>
													</select>	
									         	</span>
											</td>
												<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为：
												</div>
											</td>
											<td>
												<select name="jztypenow" class="formr" onblur="getGraygys('jztypenow');" onfocus="getBlack(this);">
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
											<td>
												<input type="text" name="bbulast" readonly="true"
													value="<%=bbulast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<input type="text" name="bbunow" value="<%=bbunow%>" class="formm" onblur="getGraybbu(this);" onfocus="getBlack(this);"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													RRU数量：
												</div>
											</td>
											<td>
												<input type="text" name="rrulast" readonly="true"
													value="<%=rrulast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<input type="text" name="rrunow" value="<%=rrunow%>" class="formm" onblur="getGrayrru(this);" onfocus="getBlack(this);"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													设备编码：
												</div>
											</td>
											<td>
												<span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();"> 				
													<select name="shebeilast" class="form3">
														<option value="0">
															请选择
														</option>
														<%
															ArrayList sblx1 = new ArrayList();
															sblx1 = ztcommon.getSelctOptions("sblx");
															if (sblx1 != null) {
																String code = "", name = "";
																int cscount = ((Integer) sblx1.get(0)).intValue();
																for (int i = cscount; i < sblx1.size() - 1; i += cscount) {
																	code = (String) sblx1.get(i + sblx1.indexOf("CODE"));
																	name = (String) sblx1.get(i + sblx1.indexOf("NAME"));
														%>
														<option value="<%=code%>"><%=name%></option>
														<%
															}
															}
														%>
													</select>	
									         	</span>
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>改为:</div>
											</td>
											<td>
												<select name="shebeinow" onblur="getGraygys('shebeinow');" onfocus="getBlack(this);">
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
													他网共享数量：
												</div>
											</td>
											<td>
												<input type="text" name="ydshebeilast" readonly="true"
													value="<%=ydshebeilast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<input type="text" name="ydshebeinow" value="<%=ydshebeinow%>" class="formm" onblur="getGrayyd(this);" onfocus="getBlack(this);"/>
											</td>
											<td bgcolor="#DDDDDD" class="form_label">
												<div>
													共享固网数量：
												</div>
											</td>
											<td>
												<input type="text" name="gxgwsllast" readonly="true"
													value="<%=gxgwsllast%>" class="form2" />
											</td>
											<td bgcolor="#DDDDDD" class="form_labell">
												<div>
													改为:
												</div>
											</td>
											<td>
												<input type="text" name="gxgwslnow" value="<%=gxgwslnow%>" class="formm" onblur="getGraygw(this);" onfocus="getBlack(this);" />
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
									<input type="text" name="reasonanalyse" value="<%=reasonanalyse%>" class="formjc" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										修改依据：
									</div>
								</td>
								<td colspan='7'>
									<input type="text" name="reasonanalyseyj" value="<%=reasonanalyseyj%>" class="formjc" />
								</td>
							</tr>
							<tr height="19px">
								<td bgcolor="#DDDDDD" class="form_label"><div>
									添加附件：
								</div></td>
								<td><div>
									<input type="file" id="fileUp" name="fileUp" height="19px" width="130px" onchange="fileChange(this);" />
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
								<input type="hidden" value="<%=zdbzw%>" name="zdhbbzw"/>
								</td>
								<td>
								<div id="errorInfo"
									style="width: 200px; height: 19px; position: relative; font-size: 12px; color: red">
									<c:forEach items="${requestScope.statusInfo}" var="info">
										<br />${info}
									</c:forEach>
								</div></td>	
								
								<%if(r==1){ %>
								<td colspan='1'>
									<div align="center" ><a href="#" onclick="modifyjz2('<%=zdidcz%>')"><font  style="font-size: 15px;color: red">下载附件	</font></a></div>
								</td>
								<%}else{ %>
								<td colspan='1'>
									<div align="center" ><font  style="font-size: 15px;color: red">无附件</font></div>
								</td>
								<%} %>							
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<font size="2px" color="red"> 以下都为绝对值占比:能耗基础值占比：(能耗基础值-省定标(不含空调))/省定标(不含空调)</font><br>
		<font size="2px" color="red">实际值占比：(实际值-省定标(不含空调))/省定标(不含空调)</font><br>
			<font size="2px" color="red">建议生产标占比:(建议生产标-省定标(不含空调))/省定标(不含空调) </font><br>
			<font size="2px" color="red">交流电量占比：(交流负荷(交流电量)*5.28-省定标(不含空调))/省定标(不含空调)</font>
			<font size="2px" color="red">直流电量占比：(直流负荷(直流电量)*1.52-省定标(不含空调))/省定标(不含空调)</font><br>
				<input type="hidden" value="<%=nhjc%>" name="nhjc"/>
			<input type="hidden" value="<%=sjz%>" name="sjz"/>
			<input type="hidden" value="<%=jyscb%>" name="jyscb"/>
			<input type="hidden" id="fzbzw" name="fzbzw" value="0"/>
			<input type="hidden" id="zggdid" name="zggdid" value="<%=zggdid%>"/><%--整改工单ID--%></form>
		
	<%} }%>
	</body>
<SCRIPT LANGUAGE="JavaScript">

function getGraygys(obj){
	if(obj=='sfbgnow'){ 
		var lastval = '<%=sfbglast%>';
		var nowval = document.form1.sfbgnow.value;
		var obj1 = document.form1.sfbgnow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='dianjianow'){ 
		var lastval = '<%=dianjialast%>';
		var nowval = document.form1.dianjianow.value;
		var obj1 = document.form1.dianjianow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='edhdlnow'){ 
		var lastval = '<%=edhdllast%>';
		var nowval = document.form1.edhdlnow.value;
		var obj1 = document.form1.edhdlnow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='jlfhnow'){ 
		var lastval = '<%=jlfhlast%>';
		var nowval = document.form1.jlfhnow.value;
		var obj1 = document.form1.jlfhnow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='zlfhnow'){ 
		var lastval = '<%=zlfhlast%>';
		var nowval = document.form1.zlfhnow.value;
		var obj1 = document.form1.zlfhnow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='zdsxnow'){ 
		var lastval = '<%=zdsxlast%>';
		var nowval = document.form1.zdsxnow.value;
		var obj1 = document.form1.zdsxnow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='zdlxnow'){ 
		var lastval = '<%=zdlxlast%>';
		var nowval = document.form1.zdlxnow.value;
		var obj1 = document.form1.zdlxnow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='yflxnow'){ 
		var lastval = '<%=yflxlast%>';
		var nowval = document.form1.yflxnow.value;
		var obj1 = document.form1.yflxnow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='gxxxnow'){ 
		var lastval = '<%=gxxxlast%>';
		var nowval = document.form1.gxxxnow.value;
		var obj1 = document.form1.gxxxnow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='gdfsnow'){ 
		var lastval = '<%=gdfslast%>';
		var nowval = document.form1.gdfsnow.value;
		var obj1 = document.form1.gdfsnow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='zgdnow'){ 
		var lastval = '<%=zgdlast%>';
		var nowval = document.form1.zgdnow.value;
		var obj1 = document.form1.zgdnow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='zdareanow'){ 
		var lastval = '<%=zdarealast%>';
		var nowval = document.form1.zdareanow.value;
		var obj1 = document.form1.zdareanow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='qyztnow'){ 
		var lastval = '<%=qyztlast%>';
		var nowval = document.form1.qyztnow.value;
		var obj1 = document.form1.qyztnow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='changjianow'){ 
		var lastval = '<%=changjialast%>';
		var nowval = document.form1.changjianow.value;
		var obj1 = document.form1.changjianow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='jztypenow'){ 
		var lastval = '<%=jztypelast%>';
		var nowval = document.form1.jztypenow.value;
		var obj1 = document.form1.jztypenow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='shebeinow'){ 
		var lastval = '<%=shebeilast%>';
		var nowval = document.form1.shebeinow.value;
		var obj1 = document.form1.shebeinow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}else if(obj=='dfzflxnow'){ 
		var lastval = '<%=dfzflxlast%>';
		var nowval = document.form1.dfzflxnow.value;
		var obj1 = document.form1.dfzflxnow;
		if(lastval==nowval){
			obj1.style.color = 'BLACK';
		}else{
			obj1.style.color = 'RED';
		}
	}
		
}

function getGray(obj){ 
var g2ls='<%=g2last%>';
var g2now=document.form1.g2now.value;
if(g2ls==g2now){
	obj.style.color = 'BLACK';
}else{
	obj.style.color = 'RED';
}
   
}
function getGrayg3(obj){ 
var g3ls='<%=g3last%>';
var g3now=document.form1.g3now.value;
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
function getGrayqsdb(obj){
	var zpls='<%=oldqsdb%>';
	var zpnow=document.form1.qsdb.value;
	
	if(null==zpnow||""==zpnow){zpnow="0";}
	if(zpls==zpnow){
	obj.style.color = 'BLACK';
	}else{
	obj.style.color = 'RED';
}
}
 
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
	        document.form1.sfbglast.value='<%=sfbglast%>';
	        document.form1.sfbgnow.value='<%=sfbgnow%>';
			document.form1.zdsxlast.value='<%=zdsxlast%>';
			document.form1.zdsxnow.value='<%=zdsxnow%>';
			document.form1.zdlxlast.value='<%=zdlxlast%>';
			document.form1.zdlxnow.value='<%=zdlxnow%>';
			document.form1.yflxlast.value='<%=yflxlast%>';
			document.form1.yflxnow.value='<%=yflxnow%>';
			document.form1.gxxxlast.value='<%=gxxxlast%>';
			document.form1.gxxxnow.value='<%=gxxxnow%>';
			document.form1.gdfslast.value='<%=gdfslast%>';
			document.form1.gdfsnow.value='<%=gdfsnow%>';
			document.form1.zgdlast.value='<%=zgdlast%>';
			document.form1.zgdnow.value='<%=zgdnow%>';
			document.form1.qyztlast.value='<%=qyztlast%>';
			document.form1.qyztnow.value='<%=qyztnow%>';
			document.form1.g2last.value='<%=g2last%>';
			document.form1.g2now.value='<%=g2now%>';
			document.form1.g3last.value='<%=g3last%>';
			document.form1.g3now.value='<%=g3now%>';
			document.form1.changjialast.value='<%=changjialast%>';
			document.form1.changjianow.value='<%=changjianow%>';
			document.form1.jztypelast.value='<%=jztypelast%>';
			document.form1.jztypenow.value='<%=jztypenow%>';
			document.form1.shebeilast.value='<%=shebeilast%>';
			document.form1.shebeinow.value='<%=shebeinow%>';
			document.form1.dfzflxlast.value='<%=dfzflxlast%>';
			document.form1.dfzflxnow.value='<%=dfzflxnow%>';
			document.form1.zt.value='<%=zt1%>';
</script>
<script type="text/javascript">

function getJl(){
		var zl=document.form1.zlfhnow.value;		
		var jl=document.form1.jlfhnow.value;
		
		var zlfh=Number(zl*54*24/1000/0.85).toFixed(2);
		var jlfh=Number(jl*220*24/1000).toFixed(2);

		if(jl!='0'&&zl=='0'){
			document.form1.qsdb.value=jlfh;
		}else if(jl=='0'&&zl!='0'){
			document.form1.qsdb.value=zlfh;
		}else if(jl=='0'&&zl=='0'){
			document.form1.qsdb.value='0';
		}else{
			if(Number(zl)>Number(jl)){
				document.form1.qsdb.value=jlfh;
			}else{
				document.form1.qsdb.value=zlfh;
			}	
		}
	}
	function getZl(){
		var zl=document.form1.zlfhnow.value;		
		var jl=document.form1.jlfhnow.value;
		var zlfh=Number(zl*54*24/1000/0.85).toFixed(2);
		var jlfh=Number(jl*220*24/1000).toFixed(2);

		if(jl=='0'&&zl!='0'){
			document.form1.qsdb.value=zlfh;
		}else if(jl!='0'&&zl=='0'){
			document.form1.qsdb.value=jlfh;
		}else if(jl=='0'&&zl=='0'){
			document.form1.qsdb.value='0';
		}else{	
				if(Number(zl)>Number(jl)){
					document.form1.qsdb.value=jlfh;
				}else{
					document.form1.qsdb.value=zlfh;
				}	
		}
	}
	function modifyjz2(id){
   // alert(path);
     		document.form1.action=path+"/servlet/cbzdfjxiazai2?id="+id+"&bzw=6";
     		 document.form1.submit();

    }

</script>
</html>

 

 
 