<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.jizhan.JiZhanBean,com.noki.jizhan.ZhanDianForm"%>
<%@ page import="java.text.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sheng = (String)session.getAttribute("accountSheng");
Account account = (Account)session.getAttribute("account");
String loginId=account.getAccountId();
String shicode = request.getParameter("code");
String citystatus = request.getParameter("citystatus");
String manauditstatus = request.getParameter("manauditstatus");
String dfzflx = request.getParameter("dfzflx");
String times = request.getParameter("month");
String bztimes = request.getParameter("bztimes")!=null?request.getParameter("bztimes"):"";

System.out.println(dfzflx+"111"+citystatus+"222"+manauditstatus+"333"+times);
String color="";
int intnum=0;
int jishu=0;
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
<script language="javascript">
var path = '<%=path%>';
  function queryDegree(){
                
      
                   document.form1.action=path+"/web/cx/stationmx.jsp";
                       
                   document.form1.submit();
       
    }


</script>
  </head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>  
 <body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
		<table>
			<tr class='form_label'>
				<td colspan="11"><br/><div id="parent" style="display:inline"><div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div></div>	</td>
			</tr>
		</table>
	 	<div style="width:100%;height:430px;overflow-x:auto;overflow-y:auto;border:1px">
		<table class="form_label" width="100%" bgcolor="#cbd5de"  cellspacing="1" cellpadding="1">
			<tr class="relativeTag">
				<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">编号</div></td>
	            <td width="16%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
	            <td width="16%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
	            <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
	            <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>
	  			<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">缴费周期</div></td>
	  			<td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">最后缴费时间</div></td> 
            	<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">缴费次数</div></td> 
            	<td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点代码</div></td>
            
            </tr>
            <%   
            String whereStr="",where="",str="";
            if(shicode != null && !shicode.equals("") && !shicode.equals("0")){
    			whereStr=whereStr+" and zd.xian='"+shicode+"'";
    			str=str+" and zd.xian='"+shicode+"'";
    			
    		}
    		/*if(citystatus != null && !citystatus.equals("") && !citystatus.equals("2")){
    			where=" and pr.cityaudit='"+citystatus+"'";
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
    			whereStr=whereStr+" and dl.startmonth> = '"+times+"'and dl.startmonth< = '"+times+"'";
    			where=where+" and pr.startmonth> = '"+times+"'and pr.startmonth< = '"+times+"'";
    			
    		}
    		if(bztimes != null && !bztimes.equals("") && !bztimes.equals("0")){
    			whereStr=whereStr+" and df.ACCOUNTMONTH = '"+bztimes+"'";
    			where=where+" and pr.ACCOUNTMONTH = '"+bztimes+"'";
    		}*/
            
   		     //co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
            
          	ArrayList<ZhanDianForm>  fylist = null;
             JiZhanBean bean=new JiZhanBean();
             fylist=bean.getbean3(whereStr,str,dfzflx,where,loginId);
             String zdcode="",jzname="",stationtype="",dfzflxa="",fkzq="",dfpay="",dfel="",szdq="";
             if(fylist!=null){
         		for(ZhanDianForm list:fylist){
	             zdcode = list.getZdcode();
	             zdcode = zdcode != null ? zdcode : "";
	             
	             jzname = list.getJzname();
	             jzname = jzname != null ? jzname : "";
	             
	             szdq = list.getAddress();
	             szdq = szdq != null ? szdq : "";
	             
	             stationtype = list.getStationtype();
	             stationtype = stationtype != null ? stationtype : "";
	             
	             dfzflxa = list.getDfzflx();
	             dfzflxa = dfzflxa != null ? dfzflxa : "";
	             
	             fkzq = list.getFkzq();
	             fkzq = fkzq != null ? fkzq : "";
	             
	             dfpay = list.getDfpay();
	             dfpay = dfpay != null ? dfpay : "";
	             
	             dfel = list.getDfel();
	             dfel = dfel != null ? dfel : "";
	             
	             int a=Integer.parseInt(dfel);
	             jishu=jishu+a;
	             
	         
             
             if(intnum%2==0){
 			    color="#FFFFFF" ;
 			 }else{
 			    color="#DDDDDD";
 			 }
               intnum++;
            %>
            <tr bgcolor="<%=color%>">
	            <td><div align="center" ><%=intnum%></div></td>
	       		<td><div align="left" ><%=jzname%></div></td> 
	       		<td><div align="center" ><%=szdq%></div></td> 
       		    <td><div align="center" ><%=stationtype%></div></td>
	       		<td><div align="center" ><%=dfzflxa%></div></td> 
	       		<td><div align="right" ><%=fkzq%></div></td> 
	       		<td><div align="center" ><%=dfpay%></div></td>
	       		<td><div align="right" ><%=dfel%></div></td> 
	       		<td><div align="center" ><%=zdcode%></div></td> 
	       	
       		</tr>
       		 <%}
 			 }%>
 			 <tr><td>合计</td><td>站点数：<%=intnum %></td><td colspan="6" align="right">缴费次数：<%=jishu %></td></tr>
			</table>
			</div>
			</form>
  </body>
</html>

