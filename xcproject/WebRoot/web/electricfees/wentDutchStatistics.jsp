<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.electricfees.javabean.WentDutchStatisticsBean"%>
<%@ page import="java.text.*"%>
<%
String path = request.getContextPath();
String sheng = (String)session.getAttribute("accountSheng");
Account account = (Account)session.getAttribute("account");
String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String times = request.getParameter("times")!=null?request.getParameter("times"):"";
String bztimes = request.getParameter("bztimes");
String loginId=account.getAccountId();
String color="";
int intnum=0;
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
.selected_font{
		width:100px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
.form{
	width:130px;
}
 .bttcn{ color:black;font-weight:bold;}
</style>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
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
<script type="text/javascript">
//=点击展开关闭效果=

function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
var openTip = oOpenTip || "";
var shutTip = oShutTip || "";
if(targetObj.style.display!="none"){
   if(shutAble) return;
   targetObj.style.display="none";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = shutTip; 
   }
} else {
   targetObj.style.display="block";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = openTip; 
   }
}
}
</script>
<script language="javascript">
var path = '<%=path%>';
  function queryDegree(){
                   document.form1.action=path+"/web/electricfees/wentDutchStatistics.jsp";
                   document.form1.submit();
    }
$(function(){
	$("#query").click(function(){
		queryDegree();
	});
});

var path = '<%=path%>';    
    function modifyad(shi,times,bztimes,code){
    	var b=path+"/web/electricfees/wentDutchStatisticscenter.jsp?&month="+times+"&shi="+shi+"&bztimes="+bztimes+"&code="+code;			
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			// alert(a);
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
    } 
</script>
  </head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>  
 <body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
	
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		<tr>
		<td colspan="4">
			  <div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">地市电费分摊汇总</span>	
			  </div>
	    </td>
		</tr>		
		<tr><td height="23" colspan="4">
   				<div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	        </td>
	    </tr>
	    <tr><td width="1200">
	    	<table>
	    	<tr class="form_label">
		    		<td>城市：</td><td><select name="shi" class="selected_font" onchange="changeCity()">
         		<option value="0">请选择</option>
         		<%
		         	ArrayList shilist = new ArrayList();
		         	shilist = commBean.getAgcode(sheng,account.getAccountId());
		         	if(shilist!=null){
		         		String agcode="",agname="";
		         		int scount = ((Integer)shilist.get(0)).intValue();
		         		for(int i=scount;i<shilist.size()-1;i+=scount)
	                    {
	                    	agcode=(String)shilist.get(i+shilist.indexOf("AGCODE"));
	                    	agname=(String)shilist.get(i+shilist.indexOf("AGNAME"));
	                    %>
	                    <option value="<%=agcode%>"><%=agname%></option>
	                    <%}
		         	}
		         %>
         		</select></td>
         		 <td>查询月份：</td>
		                     <td><input type="text" class="selected_font" name="times" value="<%if (null != request.getParameter("beginTime1"))out.print(request.getParameter("beginTime1"));%>"
		                         onFocus="getDatenyString(this,oCalendarChsny)" class="form" /></td>
		                     <td>报账月份：</td>
		                     <td><input type="text" class="selected_font" name="bztimes" value="<%if (null != request.getParameter("beginTime1"))out.print(request.getParameter("beginTime1"));%>"
		                         onFocus="getDatenyString(this,oCalendarChsny)" class="form" /></td>
         						
<%--         		 <td>电费支付类型：</td>--%>
<%--                 <td><select name="dfzflx"  class="selected_font" >--%>
<%--		         		<option value="0">请选择</option>--%>
<%--		         		<%--%>
<%--			         	ArrayList dfzflxa = new ArrayList();--%>
<%--			         	dfzflxa = ztcommon.getSelctOptions("DFZFLX");--%>
<%--			         	if(dfzflxa!=null){--%>
<%--			         		String code="",name="";--%>
<%--			         		int cscount = ((Integer)dfzflxa.get(0)).intValue();--%>
<%--			         		for(int i=cscount;i<dfzflxa.size()-1;i+=cscount)--%>
<%--		                    {--%>
<%--		                    	code=(String)dfzflxa.get(i+dfzflxa.indexOf("CODE"));--%>
<%--		                    	name=(String)dfzflxa.get(i+dfzflxa.indexOf("NAME"));--%>
<%--		                    %>--%>
<%--		                    <option value="<%=code%>"><%=name%></option>--%>
<%--		                    <%}--%>
<%--			         	}--%>
<%--			         %>--%>
<%--    	--%>
<%--    		         </select><font color="red">&nbsp;*</font></td>			--%>
				
<%--         			<td>--%>
<%--						<p><font size="2">--%>
<%--								 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">--%>
<%--									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />--%>
<%--									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>--%>
<%--		                          </div>--%>
<%--						--%>
<%--						</font>--%>
<%--					</p></td>--%>
					 <td>
						       <div width="60%" id="query" style="position:relative;width:60px;height:23px;cursor: pointer;right:-220px;float:right;top:0;">
								<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
								<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
					</td>
			    			
			
			</tr>
	</table>
	
	</td></tr>	
 </table>
			<table class="form_label">
					<tr>
                        <td colspan="8">
                        <div style="width:50px;display:inline;"><hr></div>&nbsp;城市信息列表(万元)&nbsp;<div style="width:300px;display:inline;"><hr></div>
                        </td>							
			       </tr>
			</table>
			<table class="form_label" width="100%" height="240px" bgcolor="#cbd5de"  cellspacing="1" cellpadding="1">
			<tr>
				<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">编号</div></td>
	            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">城市</div></td>
	            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">总费用</div></td>
	            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">生产</div></td>
	            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">生产占比</div></td>
	            
	            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">办公</div></td>
	            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">办公占比</div></td>
	            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">营业</div></td>
	  			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">营业占比</div></td>
	  			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">信息化支撑</div></td> 
	  			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">信息化支撑占比</div></td>
	  			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">建设投资</div></td> 
	  			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">建设投资占比</div></td>
	  			
            </tr>
            <%
            String whereStr="";
            String where="";
            String str="";
             //===========
             String s="0";
            String ss="0";
            String s1="0";
            String s2="0";
            String s3="0";
            String s4="0";
            String s5="0";
            String s6="0";
            String s7="0";
            String s8="0";
            String s9="0";
            String s10="0";
            String s11="0";
            String s12="0";
            String s13="0";
            String s14="0";
            String s15="0";
             String s16="0";
             //===========
            double d=0;
            double d1=0;
           
            double d2=0;
            double d3=0;
            
            double d4=0;
            double d5=0;
            
            double d6=0;
            double d7=0;
            
            double d8=0;
            double d9=0;
            
            double d10=0;
            double d11=0;
            
            double d12=0;
            double d13=0;
            
             double d14=0;
            double d15=0;
            
            double d16=0;
            double d17=0;
            
            double d18=0;
            double d19=0;
            
            double d20=0;
            double d21=0;
            //======
            if(bztimes != null && !bztimes.equals("") && !bztimes.equals("0")){
    			whereStr=whereStr+" and e.ACCOUNTMONTH = '"+bztimes+"'";
    		
            if(shi != null && !shi.equals("") && !shi.equals("0")){
    			whereStr=whereStr+" and z.shi='"+shi+"'";
    			str=str+" and z.shi='"+shi+"'";
    			
    		}if(times != null && !times.equals("") && !times.equals("0")){
    			whereStr=whereStr+" and e.startmonth> = '"+times+"'and e.endmonth< = '"+times+"'";
    		}
    		
          	 ArrayList fylist = new ArrayList();
             WentDutchStatisticsBean bean=new WentDutchStatisticsBean();
             fylist=bean.getStatistics(loginId,whereStr);
             String shi1="",shi2="",actualpay="",networkdf="",informatizationdf="",administrativedf="",marketdf="",builddf="";
             if(fylist!=null){
             int fycount = ((Integer)fylist.get(0)).intValue();
 			 for( int k = fycount;k<fylist.size()-1;k+=fycount){
	             shi1 = (String)fylist.get(k+fylist.indexOf("SHI"));
	             shi1 = shi1 != null ? shi1 : "";
	             
	             shi2 = (String)fylist.get(k+fylist.indexOf("SSS"));
	             shi2 = shi2 != null ? shi2 : "";
	             
	             actualpay = (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));
	             actualpay = actualpay != null ? actualpay : "";
	             
	             networkdf = (String)fylist.get(k+fylist.indexOf("NETWORKDF"));
	             networkdf = networkdf != "" ? networkdf : "0";
	             
	             informatizationdf = (String)fylist.get(k+fylist.indexOf("INFORMATIZATIONDF"));
	             informatizationdf = informatizationdf != "" ? informatizationdf : "0";
	             
	             administrativedf = (String)fylist.get(k+fylist.indexOf("ADMINISTRATIVEDF"));
	             administrativedf = administrativedf != "" ? administrativedf : "0";
	             
	             marketdf = (String)fylist.get(k+fylist.indexOf("MARKETDF"));
	             marketdf = marketdf != "" ? marketdf : "0";
	             
	             builddf = (String)fylist.get(k+fylist.indexOf("BUILDDF"));
	             builddf = builddf != "" ? builddf : "0";
	             DecimalFormat pay=new DecimalFormat("0.0000");
	             DecimalFormat pay1=new DecimalFormat("0.00");
	             if(actualpay==null||actualpay.equals(""))actualpay="0";
	             if(networkdf==null||networkdf.equals(""))networkdf="0";
	             if(informatizationdf==null||informatizationdf.equals(""))informatizationdf="0";
	             if(administrativedf==null||administrativedf.equals(""))administrativedf="0";
	             if(marketdf==null||marketdf.equals(""))marketdf="0";
	             if(builddf==null||builddf.equals(""))builddf="0";
	             actualpay=pay.format(Double.parseDouble(actualpay)/10000);
	             networkdf=pay.format(Double.parseDouble(networkdf)/10000);
	             informatizationdf=pay.format(Double.parseDouble(informatizationdf)/10000);
	             administrativedf=pay.format(Double.parseDouble(administrativedf)/10000);
	             marketdf=pay.format(Double.parseDouble(marketdf)/10000);
	             builddf=pay.format(Double.parseDouble(builddf)/10000);
	             //============
	            s1=pay1.format(Double.parseDouble(networkdf)/Double.parseDouble(actualpay)*100);
	           d4= Double.parseDouble(pay1.format(Double.parseDouble(networkdf)/Double.parseDouble(actualpay)));
	            d5=d4+d5;
	           // s2=pay1.format(d5);
	            d6=Double.parseDouble(informatizationdf);
	            d7=d6+d7;
	            s16=pay.format(d7);
	          d8= Double.parseDouble(pay1.format(Double.parseDouble(informatizationdf)/Double.parseDouble(actualpay)));
	          s3= pay1.format(Double.parseDouble(informatizationdf)/Double.parseDouble(actualpay)*100);
	          d9=d8+d9;
	         // s9=pay1.format(d9);
	            d10=Double.parseDouble(administrativedf);
	            d11=d10+d11;
	            s10=pay.format(d11);
	            
	            d12=Double.parseDouble(pay1.format(Double.parseDouble(administrativedf)/Double.parseDouble(actualpay)));
	            s4=pay1.format(Double.parseDouble(administrativedf)/Double.parseDouble(actualpay)*100);
	             d13=d12+d13;
	            // s11=pay1.format(d13);
	             
	             d14=Double.parseDouble(marketdf);
	             d15=d14+d15;
	             s12=pay.format(d15);
	             
	             d16=Double.parseDouble(pay1.format(Double.parseDouble(marketdf)/Double.parseDouble(actualpay)));
	             s5=pay1.format(Double.parseDouble(marketdf)/Double.parseDouble(actualpay)*100);
	             d17=d16+d17;
	            // s13=pay1.format(d17);
	             
	             d18=Double.parseDouble(builddf);
	             d19=d18+d19;
	             s14=pay.format(d19);
	             
	             
	             d20=Double.parseDouble(pay1.format(Double.parseDouble(builddf)/Double.parseDouble(actualpay)));
	              s6=pay1.format(Double.parseDouble(builddf)/Double.parseDouble(actualpay)*100);
	             d21=d20+d21;
	            // s15=pay1.format(d21);
	              //====================
	             d=Double.parseDouble(actualpay);
	             d2=Double.parseDouble(networkdf);
	            
	             d1=Double.parseDouble(pay.format(d1));
	             
	              d1=d+d1;
	              d3=d2+d3;
	              s7=pay.format(d1);
	              s8=pay.format(d3);
	              s2=pay1.format(d3/d1*100);
	              s11=pay1.format(d11/d1*100);
	              s13=pay1.format(d15/d1*100);
	              s9=pay1.format(d7/d1*100);
	              s15=pay1.format(d19/d1*100);
	              //===============
	            
             if(intnum%2==0){
 			    color="#FFFFFF" ;
 			 }else{
 			    color="#DDDDDD";
 			 }
               intnum++;
            %>
            <tr bgcolor="<%=color%>">
	            <td><div align="center" ><%=intnum%></div></td>
	       		<td><div align="center" ><a href="javascript:modifyad('<%=shi2 %>','<%=times %>','<%=bztimes %>','<%=loginId %>')" ><%=shi1%></a></div></td> 
	       		<td><div align="right" ><%=actualpay%></div></td> 
	       		<td><div align="right" ><%=networkdf%></div></td> 
	       		<td><div align="right" ><%=s1%>%</div></td> 
	       		
	       		<td><div align="right" ><%=administrativedf%></div></td>
	       		<td><div align="right" ><%=s4%>%</div></td> 
	       		<td><div align="right" ><%=marketdf%></div></td>
	       		<td><div align="right" ><%=s5%>%</div></td> 
	       		
	       		<td><div align="right" ><%=informatizationdf%></div></td>
	       		<td><div align="right" ><%=s3%>%</div></td> 
	       		<td><div align="right" ><%=builddf%></div></td>
	       		<td><div align="right" ><%=s6%>%</div></td>
       		</tr>
       		 <%}}
 			 }%>
            <tr bgcolor="#FFFFFF">
 			 <td><div align="center">合计</div></td>
 			 <td>&nbsp;</td>
 			 <td><div align="right"><%=s7%></div></td>
 			 
 			 
 			 <td><div align="right"><%=s8%></div></td>
 			 <td><div align="right"><%=s2%>%</div></td>
 			 
 			  <td><div align="right"><%=s10%></div></td>
 			 <td><div align="right"><%=s11%>%</div></td>
 			 
 			  <td><div align="right"><%=s12%></div></td>
 			  <td><div align="right"><%=s13%>%</div></td>
 			 
 			  <td><div align="right"><%=s16%></div></td>
 			 <td><div align="right"><%=s9%>%</div></td>
 			  
 			 <td><div align="right"><%=s14%></div></td>
 			 <td><div align="right"><%=s15%>%</div></td>
 			 
 			 </tr>
			</table>	
			<iframe name="treeMap" width="100%" height="270px" frameborder="0"></iframe> 	
			</form>
			<%
			Calendar cal=Calendar.getInstance();   
			String yf="";
			int y=cal.get(Calendar.YEAR);    
			String m=cal.get(Calendar.MONTH)+1+"";   
			if(m.length()==1){
				yf=y+"-0"+m;
			}else{
				yf=y+"-"+m;
			}
			

			%>
  </body>
</html>
<script type="text/javascript">
	document.form1.shi.value='<%=shi%>';
	document.form1.times.value='<%=times%>';
	
var start='<%=bztimes%>';
var s="null";
if(start==s){
document.form1.bztimes.value='<%=yf%>';
}else if(start!=s){
document.form1.bztimes.value='<%=bztimes%>';
}
	

</script>
