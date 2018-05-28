<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.jizhan.JiZhanBean"%>
<%@ page import="java.text.*"%>
<%@ page import="com.noki.ammeterdegree.javabean.DaoFormBean"%>
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
 .bttcn{ color:black;font-weight:bold;}
.form{
	width:130px;
}
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
				<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">编号</div></td>
	            <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
	            <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>
	            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>
	            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">单价</div></td> 
	            <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次电表读数</div></td>
	            <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td>
	  			<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次电表读数</div></td>
	  			<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td> 
	  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量调整</div></td> 
	  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际用电量</div></td> 
	  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费调整</div></td> 
	  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际电费</div></td> 
            	<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始月份</div></td> 
                <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束月份</div></td> 
                <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">最后缴费时间</div></td> 
                <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电量审核人</div></td> 
                <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费审核人</div></td> 
            </tr>
            <%   
            String whereStr="",where="",str="";
            if(shicode != null && !shicode.equals("") && !shicode.equals("0")){
    			whereStr=whereStr+" and zd.xian='"+shicode+"'";
    			str=str+" and zd.xian='"+shicode+"'";
    			
    		}
    		if(citystatus != null && !citystatus.equals("") && !citystatus.equals("2")){
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
    			whereStr=whereStr+" and to_char(dl.startmonth,'yyyy-mm')> = '"+times+"'and to_char(dl.startmonth,'yyyy-mm')< = '"+times+"'";
    			where=where+" and to_char(pr.startmonth,'yyyy-mm')> = '"+times+"'and to_char(pr.startmonth,'yyyy-mm')< = '"+times+"'";
    		}
    		if(bztimes != null && !bztimes.equals("") && !bztimes.equals("0")){
    			whereStr=whereStr+" and TO_CHAR(df.ACCOUNTMONTH,'yyyy-mm') = '"+bztimes+"'";
    			where=where+" and TO_CHAR(pr.ACCOUNTMONTH,'yyyy-mm') = '"+bztimes+"'";
    		}
    		if(dfzflx != null && !dfzflx.equals("") && !dfzflx.equals("0")){
    			whereStr=whereStr+" and db.dfzflx = '"+dfzflx+"'";
    			where=where+" and db.dfzflx = '"+dfzflx+"'";
    		}
            System.out.println("whereStr:"+whereStr);
   		     //co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
            
          	 ArrayList<DaoFormBean> fylist = new ArrayList();
             JiZhanBean bean=new JiZhanBean();
             fylist=bean.getbean4(whereStr,str,dfzflx,where,loginId);
             // 电表名称、站点名称、电费支付类型、上次电表读数、本次电表读数、上次抄表时间、本次抄表时间、起始月份、结束月份、最后缴费时间
             String lastdegree="",jzname="",lastdate="",dfzflxa="",thisdegree="",thisdate="",danjia="",floatdegree="",floatpay="";
             String startmonth="",endmonth="",lasttime="",dbname="",actualdegree="",actualpay="",eman="",aman="";
             if(fylist!=null){
             	for(DaoFormBean list:fylist){

	             dbname = list.getDbname();
	             dbname = dbname != null ? dbname : "";
	             
	             jzname = list.getJzname();
	             jzname = jzname != null ? jzname : "";
	             
	             dfzflxa = list.getDfzflx();
	             dfzflxa = dfzflxa != null ? dfzflxa : "";
	             
	             startmonth = list.getStartmonth();
	             startmonth = startmonth != null ? startmonth : "";
	             
	             endmonth = list.getEndmonth();
	             endmonth = endmonth != null ? endmonth : "";
	             
	             lastdegree = list.getLastdegree();
	             lastdegree = lastdegree != null ? lastdegree : "";
	             
	             thisdegree = list.getThisdegree();
	             thisdegree = thisdegree != null ? thisdegree : "";
	             
	             lastdate = list.getLastdatetime();
	             lastdate = lastdate != null ? lastdate : "";
	             
	             thisdate = list.getThisdatetime();
	             thisdate = thisdate != null ? thisdate : "";	 
	             
	             danjia = list.getDianfei();
	             danjia = danjia != null ? danjia : "";	
	             
	             actualdegree = list.getActualdegree();
	             actualdegree = actualdegree != null ? actualdegree : "";	
	             
	             floatdegree = list.getFloatdegree();
	             floatdegree = floatdegree != null ? floatdegree : "";	
	             
	             floatpay = list.getFloatpay();
	             floatpay = floatpay != null ? floatpay : "";	
	             
	             actualpay = list.getActualpay();
	             actualpay = actualpay != null ? actualpay : "";	
	             
	             aman = list.getAman();
	             aman = aman != null ? aman : "";	
	             
	             eman = list.getEman();
	             eman = eman != null ? eman : "";	
	             
	             lasttime = list.getLasttime();
	             lasttime = lasttime != null ? lasttime : "";
	             
	             DecimalFormat pay3=new DecimalFormat("0.00");
		             if(lastdegree != null && !lastdegree.equals("")&& !lastdegree.equals(" ") && !lastdegree.equals("0")){
			             lastdegree = pay3.format(Double.parseDouble(lastdegree));
		             }
		             if(thisdegree != null && !thisdegree.equals("")&& !thisdegree.equals(" ") && !thisdegree.equals("0")){
		             thisdegree = pay3.format(Double.parseDouble(thisdegree));
		             }
		             if(actualdegree != null && !actualdegree.equals("")&& !actualdegree.equals(" ") && !actualdegree.equals("0")){
		            	 actualdegree = pay3.format(Double.parseDouble(actualdegree));
			         }
		             if(actualpay != null && !actualpay.equals("") && !actualpay.equals(" ") && !actualpay.equals("0")){
		            	 actualpay = pay3.format(Double.parseDouble(actualpay));
			         }
		             if(floatdegree != null && !floatdegree.equals("") && !floatdegree.equals(" ") && !floatdegree.equals("0")){
		            	 floatdegree = pay3.format(Double.parseDouble(floatdegree));
			         }
		             if(floatpay != null && !floatpay.equals("") && !floatpay.equals(" ") && !floatpay.equals("0")){
		            	 floatpay = pay3.format(Double.parseDouble(floatpay));
			         }
	             
             
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
	       		<td><div align="center" ><%=dbname%></div></td> 
	       		<td><div align="center" ><%=dfzflxa%></div></td> 
	       		<td><div align="right" ><%=danjia%></div></td> 
       		    <td><div align="right" ><%=lastdegree%></div></td>
	       		<td><div align="center" ><%=lastdate%></div></td> 
	       		<td><div align="right" ><%=thisdegree%></div></td> 
	       		<td><div align="center" ><%=thisdate%></div></td>
	       		<td><div align="right" ><%=floatdegree%></div></td>
	       		<td><div align="right" ><%=actualdegree%></div></td>
	       		<td><div align="right" ><%=floatpay%></div></td>
	       		<td><div align="right" ><%=actualpay%></div></td>
	       		<td><div align="center" ><%=startmonth%></div></td> 
	       		<td><div align="center" ><%=endmonth%></div></td> 
	       		<td><div align="center" ><%=lasttime%></div></td> 
	       		<td><div align="center" ><%=aman%></div></td> 
	       		<td><div align="center" ><%=eman%></div></td> 
       		</tr>
       		 <%}
 			 }%>           
			</table>
			</div>
			</form>
  </body>
</html>

