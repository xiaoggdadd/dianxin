<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@page import="com.noki.electricfees.javabean.*"%>
<%@page import="com.noki.tongjichaxu.javabean.fybzCountBean"%>
<%@page import="com.noki.tongjichaxu.javabean.fybzbean"%>
<%@page import="com.noki.function.*"%>
<%@page import="com.noki.tongjichaxu.javabean.cbJzBean"%>
<%@page import="com.noki.tongjichaxu.cbJzDao"%>

<%@page import="com.noki.newfunction.dao.JzxxDao"%>


<% 
    
	String bzw=request.getParameter("bzw")!=null?request.getParameter("bzw"):"";//标志位
	String bzyf = request.getParameter("bzyf")!=null?request.getParameter("bzyf"):"0";
	String bzyf02="";
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"";
	
	String property=request.getParameter("property")!=null?request.getParameter("property"):"0";
	
	String zdsxcw = request.getParameter("zdsxcw")!=null?request.getParameter("zdsxcw"):"0";
	String scnhft = request.getParameter("scnhft")!=null?request.getParameter("scnhft"):"0";
	String mycbgk = request.getParameter("mycbgk")!=null?request.getParameter("mycbgk"):"0";
	String cbzd = request.getParameter("cbzd")!=null?request.getParameter("cbzd"):"0";
	String mycbjscb = request.getParameter("mycbjscb")!=null?request.getParameter("mycbjscb"):"0";
	String lxcb = request.getParameter("lxcb")!=null?request.getParameter("lxcb"):"0";
	String lxcbdlzj = request.getParameter("lxcbdlzj")!=null?request.getParameter("lxcbdlzj"):"0";
	String pld = request.getParameter("pld")!=null?request.getParameter("pld"):"0";
	String cbbl = request.getParameter("cbbl")!=null?request.getParameter("cbbl"):"0";
	String cbjzzg = request.getParameter("cbjzzg")!=null?request.getParameter("cbjzzg"):"0";
	
	String cbblsyc = request.getParameter("cbblsyc")!=null?request.getParameter("cbblsyc"):"0";
	
	String chaxun = request.getParameter("chaxun")!=null?request.getParameter("chaxun"):"0";
	
	String qztz = "&bzyf="+bzyf+"&property="+property+"&zdsxcw="+zdsxcw+"&scnhft="+scnhft+"&mycbgk="+mycbgk+"&cbzd="+cbzd+"&mycbjscb="+mycbjscb+"&lxcb="+lxcb
				+"&lxcbdlzj="+lxcbdlzj+"&pld="+pld+"&cbbl="+cbbl+"&cbjzzg="+cbjzzg;
	
	
	Properties pro = new Properties();
	pro.put("zdsxcw",zdsxcw);
	pro.put("scnhft",scnhft);
	pro.put("mycbgk",mycbgk);
	pro.put("cbzd",cbzd);
	pro.put("mycbjscb",mycbjscb);
	pro.put("lxcb",lxcb);
	pro.put("lxcbdlzj",lxcbdlzj);
	pro.put("pld",pld);
	pro.put("cbbl",cbbl);
	pro.put("cbjzzg",cbjzzg);
	
	pro.put("cbblsyc",cbblsyc);
	
	String whereStr="";
	String whereyf ="";
	String whereyf01 ="";
	String whereyf02 = "";
	String month = "";
	String bmonth="";
    String path = request.getContextPath();
    String loginName = (String)session.getAttribute("loginName");
    System.out.println("denglu:"+loginName);
    Account account = (Account)session.getAttribute("account");
    String roleId = (String) session.getAttribute("accountRole");
    String loginId  = account.getAccountId();
    String rolename = account.getRoleName();
    System.out.println("=++_+_+_+++++++++++++++"+rolename);
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    
    int sintnum=0;
    int intnum=xh = pagesize*(curpage-1)+1;      
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
  </head>
  <style>
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:20px

		}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
.text_font {
	width: 50px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
}
.dext_font {
	width: 50px;
	font-family: 宋体;
	font-size: 12px;
	text-align:right;
	line-height: 100%;
}
.stext_font {
	width: 30px;
	font-family: 宋体;
	font-size: 12px;
	text-align:right;
	line-height: 100%;
}

.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
};
 .memo {border: 1px #C8E1FB solid}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}

.bttcn{color:black;font-weight:bold;}

.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.form_la{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}
</style>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
 <script type="text/javascript">
 function zd(zdid){ 
	 var path='<%=path%>';   
	 window.open(path+"/web/query/basequery/sitepaymentstatisticscentreyf.jsp?zdid="+zdid,'','width=500,height=400,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
    }
 function modifyjz(id){
 var path='<%=path%>'; 
    window.open(path+"/web/jizhan/zhandianinfo.jsp?id="+id,'','width=500,height=400,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
     //document.form1.action=path+"/web/jizhan/zhandianinfo.jsp?id="+id
      //document.form1.submit();
    }
 function information(dbid,dlid){	 
	 var path='<%=path%>'; 
    	window.open(path+"/web/query/basequery/showdfxx.jsp?dbid="+dbid+"&dlid="+dlid,'','width=500,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
 }
 function dfinfo5(dbid,dlid){
	 var path='<%=path%>';
	 window.open(path+"/web/query/basequery/yfftan.jsp?dbid="+dbid+"&dlid="+dlid,'','width=500,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
  
}
 </script> 
 <script language="javascript">
	function passCheck(){
		
		var chaxun = '<%=chaxun%>';
		var qztz = '<%=qztz%>';
		var path='<%=path%>';
		var num = parseInt(document.form1.intnum.value);
		var arr = new Array();
		
		for(i=2;i<=num;i++){
			
		  var shiname = "shicode"+i;
		  //var zsx = "17"+num; 
		  //var pld = "18"+num; 
		  //var cbjz = "19"+num; 
		  var cbdl = "z"+i; 
		  var xz  = "p"+i; 
		  var zf = "c"+i;
		  var shi = document.getElementById(shiname).value;
	      //var a = document.getElementById(zsx).value;
	     // var b = document.getElementById(pld).value;
	      //var c = document.getElementById(cbjz).value;
	      var d = document.getElementById(cbdl).value;
	      var e = document.getElementById(xz).value;
	      var f = document.getElementById(zf).value;
	      //var element = "shi="+shi+";zsx="+a+";pld="+b+";cbjz="+c+";cbdl="+d+";xz="+e+";zf="+f;
	      if(isNaN(e)==false&&isNaN(f)==false){
		      var element = shi+";"+d+";"+e+";"+f;
		      arr.push(element);
	      }else{
	      	alert("超标电量整治或协助省公司电量定标，必须填写数字！");
	      	return;
	      }	  	
		}	     

	     
		if('1'==chaxun){
			document.form1.action = path+"/UploadB?action=jcsjzsx&qztz="+qztz+"&array="+arr;
			document.form1.submit();
			
		}else{
			alert("请先查询后，再进行保存！");
		}
    }
	
	$(function() {
		$("#save").click(function(){
			passCheck();
		});
	});
</script>
  <body>
   <form action="" name="form1" method="POST">
    <% if(bzw != null && !bzw.equals("") && !bzw.equals("0")){
    %><%} %>
    <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 
    <table width="2000px" border="0" cellspacing="1" cellpadding="1">
           <tr class="relativeTag" >
             <td width="2%" bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn">ID</div></td>
              <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">所在地区</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">属性错误</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">分摊</div></td>
              <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">抄表管控</div></td>
              <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">超标站点</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">结算电量超标</div></td>
                <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">连续超标</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">连续超标且电量增加</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">真实性量</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">超标站核查未通过(待开发)</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">调查数据验证错误(待开发)</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">实测电量有误(待开发)</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">标准电量和</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">电表用电量和</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">标准偏离度</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">未按期整改量</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">真实性</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">偏离度</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">超标基站整改</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">超标电量整治</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">协助省公司电量定标</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">总分</div></td>
         </tr>
       <%
         
         if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" AND zd.shi='"+shi+"'";
		 }
      
		 if(bzyf != null && !bzyf.equals("") && !bzyf.equals("0")){
			 DateFormat df = new SimpleDateFormat("yyyy-MM"); 
			// String bzyf02="";
			        try {  
			             Date d1 = df.parse(bzyf); 
			             Calendar  g = Calendar.getInstance();  
			             g.setTime(d1);  
			             g.add(Calendar.MONTH,-1);   
			             Date d2 = g.getTime(); 
			             bzyf02 = df.format(d2);  
			              
			         } catch (ParseException e) {              
			             e.printStackTrace();  
			         }  
			 whereyf01=whereyf01+" AND symonth ='"+bzyf+"'";
			
			  
			 //后期更改的 标准月份
			 month = bzyf;
			 
			 whereyf02=whereyf02+" AND symonth ='"+bzyf02+"'";
			
			 whereyf=whereyf+" AND  ef.accountmonth ='"+bzyf+"'";
			 
		 }
		cbJzDao bean=new cbJzDao();  
		
		List<cbJzBean> fylist = null;
		boolean b = false;
		if(bzw != null && !bzw.equals("") && !bzw.equals("0")){
        String str="";
        String shicode = "";
        String shiname="",jzsxcount="0",scnhcount="0",cbgkcount="0",cbzdcount="0",lxcbcount="0",lxzzcbcount="0",jsdbcount="0",bzdlcount="0",szdlcount="0",bzpldu="0",waqzgcount="0",
               zsx="0",pldcount="0",cbjzzgcount="0",zf="0",zsxcount="0"; 
        String cbdlzz = "0";
        String xzsgsdldb = "0";
        String bzw_count = "2";//传到后台用来判断超标比例
        //报账月份
        b = bean.getJcsjzsxHistory(bzyf,property);
       if(b){
       		fylist = bean.getJcsjzsxHistoryData(bzyf,loginId,property);
       }else{

           // JzxxDao dao = new JzxxDao();
       		// dao.getJxx(bzyf);
            //JzxxDao dao = new JzxxDao();
       		//dao.getJxx(bzyf);

       		fylist = bean.getData(whereStr,property,whereyf,whereyf01,whereyf02,month,pro,bzyf,loginId,loginName);
       }       
		
		  if(fylist!=null){
	     	request.getSession().removeAttribute("fylist");
			request.getSession().setAttribute("fylist",fylist);
             for (cbJzBean beans:fylist) {   
             shiname = beans.getShiname();
             shicode = beans.getShicode();
             jzsxcount = beans.getJzsxcount() != null ? beans.getJzsxcount() : "0";
             scnhcount = beans.getScnhcount() != null ? beans.getScnhcount() : "0";
             cbgkcount = beans.getCbgkcount() != null ? beans.getCbgkcount() : "0";
             cbzdcount = beans.getCbzdcount() != null ? beans.getCbzdcount() : "0";
             lxcbcount = beans.getLxcbcount() != null ? beans.getLxcbcount() : "0";
             lxzzcbcount = beans.getLxzzcbcount() != null ? beans.getLxzzcbcount() : "0";
             jsdbcount = beans.getJsdbcount() != null ? beans.getJsdbcount() : "0";
             zsxcount  = beans.getZsxcount() != null ? beans.getZsxcount() : "0";
             bzdlcount = beans.getBzdlcount() != null ? beans.getBzdlcount() : "0";
             szdlcount = beans.getSzdlcount() != null ? beans.getSzdlcount() : "0";
             bzpldu = beans.getBzpldu() != null ? beans.getBzpldu() : "0";
             waqzgcount = beans.getWaqzgcount()!=null ? beans.getWaqzgcount() : "0";
             zsx = beans.getZsx()!=null ? beans.getZsx() : "0";
             DecimalFormat p=new DecimalFormat("0");
             zsx = p.format(Double.parseDouble(zsx));
             zsxcount = p.format(Double.parseDouble(zsxcount));
             pldcount = beans.getPld()!=null ? beans.getPld() : "0";
             pldcount = p.format(Double.parseDouble(pldcount));
            DecimalFormat price=new DecimalFormat("0.00");
            if("".equals(bzdlcount)){bzdlcount="0";}
            bzdlcount=price.format(Double.parseDouble(bzdlcount));
            bzpldu = price.format(Double.parseDouble(bzpldu));
            szdlcount = price.format(Double.parseDouble(szdlcount));
            cbjzzgcount = beans.getCbjzzg() != null ? beans.getCbjzzg() : "0";
            cbjzzgcount = p.format(Double.parseDouble(cbjzzgcount));
            if(!b){
               zf = String.valueOf( Double.parseDouble(zsx)+Double.parseDouble(pldcount)+Double.parseDouble(cbjzzgcount));
               zf = p.format(Double.parseDouble(zf));
            }else{
            	
               zsxcount = p.format(Math.ceil(Double.valueOf(jzsxcount)*(Double.valueOf(zdsxcw)/100)+
               			Double.valueOf(scnhcount)*(Double.valueOf(scnhft)/100)+Double.valueOf(cbgkcount)*(Double.valueOf(mycbgk)/100)+
               			Double.valueOf(cbzdcount)*(Double.valueOf(cbzd)/100)+Double.valueOf(jsdbcount)*(Double.valueOf(mycbjscb)/100)+
               			Double.valueOf(lxcbcount)*(Double.valueOf(lxcb)/100)+Double.valueOf(lxzzcbcount)*(Double.valueOf(lxcbdlzj)/100)));
               			
               if(Double.parseDouble(zsxcount)>10){
               		zsx = "0";
               }else if(Double.parseDouble(zsxcount)<0){
               		zsx = p.format(10);
               }else{
               		zsx = p.format(10-Double.parseDouble(zsxcount));
               }			
               
               double pldcb = Double.valueOf(bzpldu)*100-Double.valueOf(pld);
               if(pldcb<0){
               		pldcount = "10";
               }else if(pldcb>10){
               		pldcount = "0";
               }else{
               		pldcount = p.format(10-(Double.valueOf(bzpldu)*100-Double.valueOf(pld)));
               }
               
               double weizgzdcb = Double.valueOf(waqzgcount)-Double.valueOf(cbjzzg);
               if(weizgzdcb>40){
               		cbjzzgcount = "0";
               }else if(weizgzdcb<0){
               		cbjzzgcount = p.format(40);
               }else{
               		cbjzzgcount = p.format(40-weizgzdcb);
               }
               
               
               cbdlzz = beans.getCbdlzz()!= null ? beans.getCbdlzz(): "0";
               if(cbdlzz==""||"".equals(cbdlzz)||cbdlzz==null||" ".equals(cbdlzz)){
               		cbdlzz = "0";
               }
               cbdlzz = price.format(Double.parseDouble(cbdlzz));
        	   xzsgsdldb = beans.getXzsgsdldb()!= null ? beans.getXzsgsdldb(): "0";
        	   if(xzsgsdldb==""||"".equals(xzsgsdldb)||xzsgsdldb==null||" ".equals(xzsgsdldb)){
               		xzsgsdldb = "0";
               }
        	   xzsgsdldb = price.format(Double.parseDouble(xzsgsdldb));
        	   //zf = beans.getZf()!= null ? beans.getZf(): "0";
        	   
        	   zf = price.format(Double.parseDouble(zsx)+Double.parseDouble(pldcount)+Double.parseDouble(cbjzzgcount)
        	   		+Double.parseDouble(cbdlzz)+Double.parseDouble(xzsgsdldb));
            }

			 String color=null;
			 
            if(intnum%2==0){
			    color="#DDDDDD";
			 }else{
			    color="#FFFFFF";
			}
            	
       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		<td>
       			<%=intnum++%>
       		</td>
       		
       		<td>
       		<div id="2<%=intnum%>"><input id="shicode<%=intnum%>" type="hidden" value="<%=shicode%>"/><%=shiname%></div>
       		</td>
       		<td>
       		<div align="center" id="3<%=intnum%>"><a href="javascript:tz('<%=shicode%>','<%=loginId%>')"><%=jzsxcount%></a></div>
       		</td>
       		<td>
       		<div align="center" id="4<%=intnum%>"><a href="javascript:sc('<%=shicode%>','<%=loginId%>','<%=bzyf%>')"><%=scnhcount%></a></div>
       		</td>
       		<td>
       		<div align="center" id="5<%=intnum%>"><a href="javascript:cbgk('<%=shicode%>','<%=bzyf%>','<%=property%>','<%=loginId%>')"><%=cbgkcount%></a></div>
       		</td>
       		<td>
       		<div align="center" id="6<%=intnum%>"><a href="javascript:cbzd('<%=shicode%>','<%=bzyf%>','<%=property%>','<%=cbbl%>','<%=loginId%>')"><%=cbzdcount%></a></div>
       		</td>
       		<td>
       		<div align="center" id="7<%=intnum%>"><a href="javascript:jsdb('<%=shicode%>','<%=bzyf%>','<%=property%>','<%=bzyf02%>','<%=cbbl%>','<%=cbblsyc%>','<%=loginId%>')"><%=jsdbcount%></a></div>
       		</td>
       		<td>
       		<div align="center" id="8<%=intnum%>"><a href="javascript:lxcb('<%=shicode%>','<%=bzyf%>','<%=property%>','<%=bzyf02%>','<%=cbbl%>','<%=cbblsyc%>','<%=loginId%>')"><%=lxcbcount%></a></div>
       		</td>
       		<td>
       		<div  align="center"id="9<%=intnum%>"><a href="javascript:lxcbzj('<%=shicode%>','<%=bzyf%>','<%=property%>','<%=bzyf02%>','<%=cbbl%>','<%=cbblsyc%>','<%=loginId%>')"><%=lxzzcbcount%></a></div>
       		</td>
       		<td>
       		<div  align="center" id="<%=intnum%>"><%=zsxcount%></div>
       		</td>
       		<td>
       			<div align="center">xxx</div>
       		</td>
       		<td>
       			<div align="center">xxx</div>
       		</td>
       		<td>
       			<div align="center">xxx</div>
       		</td>
       		<td>
       		<div align="center" id="13<%=intnum%>"><%=bzdlcount%></div>
       		</td>
       		<td>
       		<div  align="center" id="14<%=intnum%>"><%=szdlcount%></div>
       		</td>
       		<td>
       		<div align="center" id="15<%=intnum%>"><%=bzpldu%></div>
       		</td>
       		<td>
       		<div align="center" id="16<%=intnum%>"><a href="javascript:waqzg('<%=shicode%>','<%=bzyf%>','<%=property%>','<%=cbbl%>','<%=loginId%>')"><%=waqzgcount%></a></div>
       		</td>
       		<td>
       		
       		<input id="17<%=intnum%>" readonly="readonly" type="text" name="" class="stext_font" value="<%=zsx%>"/>
       		</td>
       		<td>
       		<input id="18<%=intnum%>" readonly="readonly" type="text" name="" class="stext_font" value="<%=pldcount%>" />
       		</td>
       		<td>
       		<input id="19<%=intnum%>" readonly="readonly" type="text" name="" class="stext_font" value="<%=cbjzzgcount%>"/>
       		</td>
       		<%if(!b){%>
       		<td>
       			<input id="z<%=intnum%>" type="text" name="" class="dext_font" value="" onChange="countzf01(<%=intnum%>)"/>
       		</td>
       		<td>
       			<input id="p<%=intnum%>" type="text" name="" class="dext_font" value="" onChange="countzf01(<%=intnum%>)"/>
       		</td>
       		<%}else{%>
       		<td>
       			<input id="z<%=intnum%>" type="text" name="" class="dext_font" value="<%=cbdlzz%>" onChange="countzf01(<%=intnum%>)"/>
       		</td>
       		<td>
       			<input id="p<%=intnum%>" type="text" name="" class="dext_font" value="<%=xzsgsdldb%>" onChange="countzf01(<%=intnum%>)"/>
       		</td>
       		<%} %>
       		<td>
       		 <input id="c<%=intnum%>" readonly="readonly" type="text"  name="" class="dext_font" value="<%=zf%>" />       			
       		</td>
       </tr>
       <%} } }%>
      <%
        //int intnum = 0;
        String color =null;
        if (intnum==1){
         for(int i=1;i<17;i++){
          if(i%2==0){
			    color="#DDDDDD";
          }else{
			    color="#FFFFFF";
		  }
         %>

        <tr bgcolor="<%=color%>">
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>                   
            <td>&nbsp;</td>
              
            <td>&nbsp;</td> 
            <td>&nbsp;</td>   
            <td>&nbsp;</td>   
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			
			<td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>                   
            <td>&nbsp;</td>
              
            <td>&nbsp;</td>
            <td>&nbsp;</td> 
            </tr>
      <% }}else if(!(intnum > 17)){
    	  for(int i=((intnum-1)%17);i<17;i++){
            if(i%2==0)
			    color="#FFFFFF" ;
            else
			    color="#DDDDDD";
        %>
        <tr bgcolor="<%=color%>">
            <td>&nbsp;</td>  
             <td>&nbsp;</td> 
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
            <td>&nbsp;</td>
             
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>                   
            <td>&nbsp;</td>
            <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            <td>&nbsp;</td>   
            <td>&nbsp;</td>  
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>                   
            <td>&nbsp;</td>
              
            <td>&nbsp;</td>
            <td>&nbsp;</td>                    
        </tr>
        <% }}%>
     </table> 
  	 </div>
	 <table align="right">
	   	 <tr>
               <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                    <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
               </div></td>
	    </tr>
		<tr>
	    <%if(("1".equals(roleId)||roleId=="1")&&(("263".equals(loginId)||loginId=="263")||("10380".equals(loginId)||loginId=="10380"))&&!b){ %>
		<td align="right"> 
          <div id="save"
				style="width: 60px; height: 23px; cursor: pointer; float: left; position: relative; left: 280px">
				<img src="<%=path%>/images/2chongzhi.png" width="100%"
					height="100%">
				<span
					style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">保存</span>
			</div>
     	</td>
     	<!-- <td align="right" title="删除查询月份保存数据"> 
          <div id="deletedata"
				style="width: 60px; height: 23px; cursor: pointer; float: left; position: relative; left: 100px">
				<img src="<%=path%>/images/2chongzhi.png" width="100%"
					height="100%">
				<span
					style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">删除</span>
			</div>
     	</td> -->
     	<%} %>
     	</tr>     	
	 </table>
	 <input type="hidden" name="intnum" id="intnum" value="<%=intnum%>"/>
  	 </form>
  </body>
	 <script type="text/javascript">
	      //查询详细信息	
	    function countzf01(num){
	     
	      var zsx = "17"+num; var pld = "18"+num; var cbjz = "19"+num; var cbdl = "z"+num; var xz  = "p"+num; var zf = "c"+num;
	      var a = document.getElementById(zsx);
	      var b = document.getElementById(pld);
	      var c = document.getElementById(cbjz);
	      var d = document.getElementById(cbdl);
	      var e = document.getElementById(xz);
	      var f = document.getElementById(zf);
	      f.value = Number(a.value)+Number(b.value)+Number(c.value)+Number(d.value)+Number(e.value);
	      
	      
	    }
	    function lookDetails(zdcode){ 
	       	
	    	window.open(path+"/web/jizhan/shenhemodifsite.jsp?id="+zdcode,'','width=1100,height=600px,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
	      // document.form1.action=path+"/web/jizhan/shenhemodifsite.jsp?shi1="+shi+"&xian1="+xian+"&xiaoqu1="+xiaoqu+"&sname1="+sname+"&szdcode1="+szdcode+"&stationtype1="+stationtype+"&jzproperty1="+jzproperty+"&jztype1="+jztype+"&lrrq1="+lrrq+"&id="+zdcode;
	      document.form1.submit();
	    }
	    function modify(str){
	    	
				var bz="2";//标志位 所属专业分摊数据从数据库还是从当前页面 获取值
				var de="1";//标志位  删除时从bean里遍历数据  
	     		document.form1.action=path+"/web/equipmentmanage/add.jsp?"+str+"&bz="+bz+"&de="+de+"";
	            document.form1.submit();
	    }
	  </script>
	<script type="text/javascript">
		function tz(shicode,loginId) {
			var path = '<%=path%>';
			window.open(path +  "/web/tongjichaxun/sxcwQuery.jsp?shiname="+shicode+"&loginId="+loginId,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
		}
		function sc(shicode,loginId,bzyf) {
			var path = '<%=path%>';
			window.open(path +  "/web/tongjichaxun/scfentan.jsp?shiname="+shicode+"&loginId="+loginId+"&bzyf="+bzyf,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
		}
		function cbgk(shicode,bzyf,property,loginId) {
			var path = '<%=path%>';
			window.open(path +  "/web/tongjichaxun/Cbgk.jsp?shiname="+shicode+"&bzyf="+bzyf+"&property="+property+"&loginId="+loginId,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
		}
		function cbzd(shicode,bzyf,property,cbbl,loginId) {
			var path = '<%=path%>';
			window.open(path +  "/web/tongjichaxun/CbzdNewBiLi.jsp?shiname="+shicode+"&bzyf="+bzyf+"&property="+property+"&cbbl="+cbbl+"&loginId="+loginId,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
		}
		function jsdb(shicode,bzyf,property,bzyf02,cbbl,cbblsyc,loginId) {	
			var path = '<%=path%>';
			window.open(path +  "/web/tongjichaxun/jsdbNewBiLi.jsp?shiname="+shicode+"&bzyf="+bzyf+"&property="+property+"&bzyf02="+bzyf02+"&cbbl="+cbbl+"&cbblsyc="+cbblsyc+"&loginId="+loginId,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
		}
		function lxcb(shicode,bzyf,property,bzyf02,cbbl,cbblsyc,loginId) {	
			var path = '<%=path%>';
			window.open(path +  "/web/tongjichaxun/LxcbNewBiLi.jsp?shiname="+shicode+"&bzyf="+bzyf+"&property="+property+"&bzyf02="+bzyf02+"&cbbl="+cbbl+"&cbblsyc="+cbblsyc+"&loginId="+loginId,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
		}
		function lxcbzj(shicode,bzyf,property,bzyf02,cbbl,cbblsyc,loginId) {	
			var path = '<%=path%>';
			window.open(path +  "/web/tongjichaxun/LxcbzjNewBiLi.jsp?shiname="+shicode+"&bzyf="+bzyf+"&property="+property+"&bzyf02="+bzyf02+"&cbbl="+cbbl+"&cbblsyc="+cbblsyc+"&loginId="+loginId,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
		}
		function waqzg(shicode,bzyf,property,cbbl,loginId) {
			var path = '<%=path%>';
			window.open(path +  "/web/tongjichaxun/Waqzg.jsp?shiname="+shicode+"&bzyf="+bzyf+"&property="+property+"&cbbl="+cbbl+"&loginId="+loginId,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
		}
	</script>
</html>

