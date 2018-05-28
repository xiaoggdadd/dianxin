<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.jizhan.JiZhanBean"%>
<%@ page import="com.noki.jizhan.ZhanDianForm"%>
<%@ page import="java.text.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sheng = (String)session.getAttribute("accountSheng");
Account account = (Account)session.getAttribute("account");
String shicode = request.getParameter("code");
String citystatus = request.getParameter("citystatus");
String manauditstatus = request.getParameter("manauditstatus");
String dfzflx = request.getParameter("dfzflx");
String times = request.getParameter("month");
String bztimes = request.getParameter("bztimes")!=null?request.getParameter("bztimes"):"";
String loginId=account.getAccountId();
System.out.println(shicode+"|"+citystatus+"|"+manauditstatus+"|"+dfzflx+"|"+times+bztimes);
String color="";
int intnum=0, zds=0,dbs=0,rgs=0,jfs=0,cws=0,shis=0,wjfs=0,liucheng=0;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px

}
.form{
	width:130px;
}
 .bttcn{ color:black;font-weight:bold;}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
</style>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
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

  </head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>  
 <body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
		   <table>
				<tr class='form_label'>
					<td colspan="11"><br/><div id="parent" style="display:inline"><div style="width:50px;display:inline;"><hr></div>&nbsp;区县信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div></div>	</td>
				</tr>
			</table>
			<div style="width:100%;height:270px;overflow-x:auto;overflow-y:auto;border:0px">
			<table class="form_label" bgcolor="#cbd5de"  cellspacing="1" cellpadding="1">
			<tr class="relativeTag">
				<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">编号</div></td>
	            <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>
	            <td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">审核通过站点总数</div></td>
	            <td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">系统结算电表数</div></td>
	            <td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表交费次数</div></td>
	            <td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点未交费数</div></td>
	            <td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">缴费率</div></td>
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核条数</div></td>
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核率</div></td> 
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市级审核条数</div></td>
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市级审核率</div></td> 
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务审核条数</div></td>
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务审核率</div></td> 
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">打印条数</div></td>
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">打印率</div></td> 
	  			
            </tr>
            <% 
            String whereStr="",where="",str="";
            if(shicode != null && !shicode.equals("") && !shicode.equals("0")){
    			whereStr=whereStr+" and zd.shi='"+shicode+"'";
    			where=where+" and zd.shi='"+shicode+"'";
    			str=str+" and zd.shi='"+shicode+"'";
    			
    		}
    		if(citystatus != null && !citystatus.equals("") && !citystatus.equals("2")){
    			where=" and yf.cityaudit='"+citystatus+"'";
    			whereStr=whereStr+" and df.cityaudit='"+citystatus+"'";
    			
    		}
    		if(manauditstatus != null && !manauditstatus.equals("") && !manauditstatus.equals("3")){
    			if(manauditstatus.equals("-1")){
    				manauditstatus="2";
    				whereStr=whereStr+" and df.manualauditstatus<>'"+manauditstatus+"'";
    				
    			}else{
    				whereStr=whereStr+" and df.manualauditstatus='"+manauditstatus+"'";
    			
    			}
    			
    			
    		}
    		if(times != null && !times.equals("") && !times.equals("0")){
    			whereStr=whereStr+" and to_char(dd.startmonth,'yyyy-mm')> = '"+times+"'and to_char(dd.startmonth,'yyyy-mm')< = '"+times+"'";
    			where=where+" and to_char(yf.startmonth,'yyyy-mm')> = '"+times+"'and to_char(yf.startmonth,'yyyy-mm')< = '"+times+"'";
    		}
    		if(bztimes != null && !bztimes.equals("") && !bztimes.equals("0")){
    			whereStr=whereStr+" and to_char(df.ACCOUNTMONTH,'yyyy-mm') = '"+bztimes+"'";
    			where=where+" and to_char(yf.ACCOUNTMONTH,'yyyy-mm') = '"+bztimes+"'";
    		}
    		if(dfzflx != null && !dfzflx.equals("") && !dfzflx.equals("0")){
    			where=where+" and d.dfzflx = '"+dfzflx+"'";
    			whereStr=whereStr+" and d.dfzflx = '"+dfzflx+"'";
    		}
    		System.out.println("whereStr"+whereStr);
            
   		     //co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
            
          	 ArrayList<ZhanDianForm>  fylist = null;
             JiZhanBean bean=new JiZhanBean();
             fylist=bean.getbean2(whereStr,str,dfzflx,where,loginId);
             String code="",id="",weishu="",zdshu="",dbshu="",jfshu="",jfl="",cwshu="",shishu="",cwlv="",shilv="",liuchshu="",liuchlv="";
             String rgshu="",rglv="",zishu="",zilv="",xian="";
             if(fylist!=null){
            		for(ZhanDianForm list:fylist){
	             xian = list.getXian();
	             xian = xian != null ? xian : "";
	             
	             code = list.getCode();
	             code = code != null ? code : "";
	             
	             zdshu =list.getZhan();
	             zdshu = zdshu != "" ? zdshu : "0";
	             
	             dbshu = list.getDianbiao();
	             dbshu = dbshu != "" ? dbshu : "0";
	             
	             jfshu = list.getJiaofei();
	             jfshu = jfshu != "" ? jfshu : "0";
	             
	             cwshu =list.getCaiwu();
	             cwshu = cwshu != "" ? cwshu : "0";
	             
	             shishu = list.getShiji();
	             shishu = shishu != "" ? shishu : "0";
	             
	             liuchshu = list.getLiuchenghao();
	             liuchshu = liuchshu != "" ? liuchshu : "0";
	             
	             rgshu = list.getRengong();
	             rgshu = rgshu != "" ? rgshu : "0";
	             
	             zishu = list.getZidong();
	             zishu = zishu != "" ? zishu : "0";
	             
	             weishu = list.getCounn3();
	             weishu = weishu != "" ? weishu : "0";
	             
	             DecimalFormat pay2=new DecimalFormat("0.00");
	             Double db=0.0,jf=0.0,shia=0.0,cw=0.0,liucha=0.0,zi=0.0,rg=0.0,shii=0.0;
	             if(jfshu!=""){
	            	 jf=Double.parseDouble(jfshu); 
	             }
	             if(dbshu!=""){
		             db=Double.parseDouble(dbshu);
	             } 
	             if(zishu!=""&&zishu!=" "&&zishu!=null&&zishu!="null"){
	            	  zi=Double.parseDouble(zishu);
	             }
	             if(rgshu!=""&&rgshu!=" "&&rgshu!=null&&rgshu!="null"){
	            	  rg=Double.parseDouble(rgshu);
	             }
	             if(cwshu!=""){
	            	 cw=Double.parseDouble(cwshu);
	             }
	             if(shishu!=""){
	            	 shia=Double.parseDouble(shishu);
	             }
	             if(liuchshu!=""){
	            	 liucha=Double.parseDouble(liuchshu);
	             }
	             if(zdshu!=""){
	            	 int zd=Integer.parseInt(zdshu);
	            	 zds=zds+zd;
	             }
	             if(dbshu!=""){
	            	 db=Double.parseDouble(dbshu);
	            	 
	            	 int dba=Integer.parseInt(dbshu);
	            	 dbs=dbs+dba;
	             }
	             if(jfshu!=""){
	            	jf=Double.parseDouble(jfshu);
	            	
	             	int jfa=Integer.parseInt(jfshu);
	             	jfs=jfs+jfa;
	             }
	             
	             
	             if(cwshu!=""){
	            	 cw=Double.parseDouble(cwshu);
	            	 int cwa=Integer.parseInt(cwshu);
	            	 cws=cws+cwa;
	             }
	             if(shishu!=""){
	            	 shii=Double.parseDouble(shishu);
	            	 int shi2=Integer.parseInt(shishu);
	            	 shis=shis+shi2;
	             }
	             if(weishu!=""){
		             	int wjfa=Integer.parseInt(weishu);
		             	wjfs=wjfs+wjfa;
		         }
	             if(rgshu!=""){
	                 int rga=Integer.parseInt(rgshu);
	            	 rgs=rgs+rga;
	             }
	             if(liuchshu!=""){
	                 int liuch2=Integer.parseInt(liuchshu);
	            	 liucheng=liucheng+liuch2;
	             }
	               
	           
	           
     			Double avg=jf/db*100;
                jfl = pay2.format(avg);
                
                Double avg1=zi/jf*100;
	            zilv=pay2.format(avg1);
                
                Double avg11=rg/jf*100;
	            rglv=pay2.format(avg11);
	            
                Double avg4=shia/jf*100;
                shilv=pay2.format(avg4);
             
                Double avg3=cw/jf*100;
                cwlv=pay2.format(avg3);
                
                Double avg44=liucha/jf*100;
                liuchlv=pay2.format(avg44);
                if(jf==0){
             	   jfl="0.00";
             	   zilv="0.00";
             	   rglv="0.00";
             	   shilv="0.00";
             	   cwlv="0.00";
             	   liuchlv="0.00";
                }
	           	
	             
             
             if(intnum%2==0){
 			    color="#FFFFFF" ;
 			 }else{
 			    color="#DDDDDD";
 			 }
               intnum++;
            %>
            <tr bgcolor="<%=color%>">
	            <td><div align="center" ><%=intnum%><input type="hidden" name="code" value="<%=code %>"/></div></td>
	       		<td><div align="center" ><a href="javascript:modifyad('<%=citystatus%>','<%=code %>','<%=dfzflx %>','<%=manauditstatus %>','<%=times %>','<%=bztimes %>')" ><%=xian%></a></div></td> 
	       		<td><div align="right" ><%=zdshu%></div></td> 
       		    <td><div align="right" ><%=dbshu%></div></td>
	       		<td><div align="right" ><a href="javascript:modifyad2('<%=citystatus%>','<%=code %>','<%=dfzflx %>','<%=manauditstatus %>','<%=times %>','<%=bztimes %>')" ><%=jfshu%></a></div></td> 
	       	    <td><div align="right" ><a href="javascript:modifyad3('<%=citystatus%>','<%=code %>','<%=dfzflx %>','<%=manauditstatus %>','<%=times %>','<%=bztimes %>')" ><%=weishu%></a></div></td> 
	       		<td><div align="right" ><%=jfl%>%</div></td> 
	       		<td><div align="right" ><%=rgshu%></div></td>
	       		<td><div align="right" ><%=rglv%>%</div></td> 
	       		<td><div align="right" ><%=shishu%></div></td> 
	       		<td><div align="right" ><%=shilv%>%</div></td> 
	       		<td><div align="right" ><%=cwshu%></div></td> 
	       		<td><div align="right" ><%=cwlv%>%</div></td>
	       		<td><div align="right" ><%=liuchshu%></div></td> 
	       		<td><div align="right" ><%=liuchlv%>%</div></td>
	       		
	       		
       		</tr>
       		 <%}
 			 }%>
           <tr bgcolor="#DDDDDD"><td align="center">合计</td>
	           <td align="right">&nbsp;</td>
	           <td align="right"><%=zds %></td>
	           <td align="right"><%=dbs %></td>
	           <td align="right"><%=jfs %></td>
	           <td align="right"><%=wjfs %></td>
	           <td align="right">&nbsp;</td>
	           <td align="right"><%=rgs %></td>
	           <td align="right">&nbsp;</td>
	           <td align="right"><%=shis %></td>
	           <td align="right">&nbsp;</td>
	           <td align="right"><%=cws %></td>
	           <td align="right">&nbsp;</td>
	            <td align="right"><%=liucheng %></td>
	           <td align="right">&nbsp;</td>
           </tr>
			</table>
			</div> 	
			
			</form>
  </body>
</html>
<script type="text/javascript">
var path = '<%=path%>'; 
function modifyad(citystatus,code,dfzflx,manauditstatus,times,bztimes){
    var url=path+"/web/cx/stationmxntan.jsp?citystatus="+citystatus+"&code="+code+"&dfzflx="+dfzflx+"&manauditstatus="+manauditstatus+"&month="+times+"&bztimes="+bztimes;			
    window.open(url,'','width=1200,height=500px,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
}	
function modifyad2(citystatus,code,dfzflx,manauditstatus,times,bztimes){
    var url=path+"/web/cx/stationmxntan2.jsp?citystatus="+citystatus+"&code="+code+"&dfzflx="+dfzflx+"&manauditstatus="+manauditstatus+"&month="+times+"&bztimes="+bztimes;			
    window.open(url,'','width=1200,height=500px,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
}	
function modifyad3(citystatus,code,dfzflx,manauditstatus,times,bztimes){
    var url=path+"/web/cx/stationmxntan3.jsp?citystatus="+citystatus+"&code="+code+"&dfzflx="+dfzflx+"&manauditstatus="+manauditstatus+"&month="+times+"&bztimes="+bztimes;			
    window.open(url,'','width=1200,height=500px,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
}	
</script>
