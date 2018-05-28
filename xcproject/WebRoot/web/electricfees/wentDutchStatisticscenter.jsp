<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.noki.electricfees.javabean.WentDutchStatisticsBean" %>
<%@ page import="java.text.*" %>
<%
String path = request.getContextPath();
String shi=request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String accountmonth=request.getParameter("bztimes")!=null?request.getParameter("bztimes"):"";
String times=request.getParameter("month")!=null?request.getParameter("month"):"";
String loginId=request.getParameter("code")!=null?request.getParameter("code"):"";
System.out.println("-"+shi+"-"+accountmonth+"-"+times+"-"+loginId);
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
<script type="text/javascript">
var path = '<%=path%>';
 function modifyad(shi,times,accountmonth,loginId,codedd){

    var url=path+"/web/electricfees/wentDutchStatisticscenter1.jsp?shi="+shi+"&times="+times+"&accountmonth="+accountmonth+"&loginId="+loginId+"&codedd="+codedd;		
	var obj = new Object();
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
   
}
</script>
  </head>
  
  <body class="body" style="overflow-x:hidden;">
    <form action="" name="form1" method="post">
      	<table class="form_label">
					<tr>
                        <td colspan="8">
                        <div style="width:50px;display:inline;"><hr></div>&nbsp;区县信息列表(万元)&nbsp;<div style="width:300px;display:inline;"><hr></div>
                        </td>							
			       </tr>
			</table>
			<table class="form_label" width="100%" height="240px" bgcolor="#cbd5de"  cellspacing="1" cellpadding="1">
			<tr>
				<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">编号</div></td>
	            <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>
	            <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">总费用</div></td>
	            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">分摊站点数</div></td>
	            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">生产</div></td>
	            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">生产占比</div></td>
	            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">办公</div></td>
	            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">办公占比</div></td>
	            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">营业</div></td>
	  			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">营业占比</div></td>
	            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">信息化支撑</div></td> 
	  			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">信息化支撑占比</div></td>
	            
	  			
	  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">建设投资</div></td> 
	  			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">建设投资占比</div></td>
            </tr>
            <%
            String whereStr="";
            int intnum=0;
            String color="";
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
            double d=0;
            double d1=0;
           
            double d2=0;
            double d3=0;
            
            double d4=0;
            double d5=0;
            
            double d6=0;
            double d7=0;
            
         
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
            int dd=0;
            int df=0;
            //======
            String sumjz="";
            if(shi != null && !shi.equals("") && !shi.equals("0")){
    			whereStr=whereStr+" and z.shi='"+shi+"'";
    		}if(times != null && !times.equals("") && !times.equals("0")){
    			whereStr=whereStr+" and e.startmonth> = '"+times+"'and e.endmonth< = '"+times+"'";
    		}if(accountmonth != null && !accountmonth.equals("") && !accountmonth.equals("0")){
    			whereStr=whereStr+" and e.ACCOUNTMONTH = '"+accountmonth+"'";
    		}
          	 ArrayList fylist = new ArrayList();
          	 
             WentDutchStatisticsBean bean=new WentDutchStatisticsBean();
             fylist=bean.getStatistics3(loginId,whereStr);
             String shi1="",actualpay="",networkdf="",informatizationdf="",administrativedf="",marketdf="",builddf="";
             String xian="",codedd="";
             if(fylist!=null){
             int fycount = ((Integer)fylist.get(0)).intValue();
 			 for( int k = fycount;k<fylist.size()-1;k+=fycount){
	             xian = (String)fylist.get(k+fylist.indexOf("XIAN"));
	             xian = xian != null ? xian : "";
	                double d8=0;
	             actualpay = (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));
	             actualpay = actualpay != null ? actualpay : "";
	             
	            codedd = (String)fylist.get(k+fylist.indexOf("CODEDD"));
	             codedd = codedd != null ? codedd : "";
	             
	             
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
	             
	             sumjz=(String)fylist.get(k+fylist.indexOf("SUMJZ"));
	               sumjz = sumjz != "" ? sumjz : "0";
	                if(sumjz==null||sumjz.equals(""))sumjz="0";
	               dd=Integer.parseInt(sumjz);
	               df=df+dd;
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
	            s11=pay1.format(Double.parseDouble(networkdf)/Double.parseDouble(actualpay)*100);
	            d4=Double.parseDouble(pay1.format(Double.parseDouble(networkdf)/Double.parseDouble(actualpay)));
	            d5=d4+d5;
	           // s1=pay1.format(d5);
	            d6=Double.parseDouble(informatizationdf);
	            d7=d6+d7;
	            s2=pay.format(d7);
	          s10= pay1.format(Double.parseDouble(informatizationdf)/Double.parseDouble(actualpay)*100);
	          d8=Double.parseDouble(pay1.format(Double.parseDouble(informatizationdf)/Double.parseDouble(actualpay)));
	          d9=d8+d9;
	          System.out.println(d8+"d8");
	          //d9==s3
	          //s3=pay1.format(d9);
	            d10=Double.parseDouble(administrativedf);
	            d11=d10+d11;
	            s4=pay.format(d11);
	            
	            s12=pay1.format(Double.parseDouble(administrativedf)/Double.parseDouble(actualpay)*100);
	            d12=Double.parseDouble(pay1.format(Double.parseDouble(administrativedf)/Double.parseDouble(actualpay)));
	             d13=d12+d13;
	           //  s5=pay1.format(d13);
	             
	             d14=Double.parseDouble(marketdf);
	             d15=d14+d15;
	             s9=pay.format(d15);
	             
	             s13=pay1.format(Double.parseDouble(marketdf)/Double.parseDouble(actualpay)*100);
	             d16=Double.parseDouble(pay1.format(Double.parseDouble(marketdf)/Double.parseDouble(actualpay)));
	             d17=d16+d17;
	             //s6=pay1.format(d17);
	             
	             d18=Double.parseDouble(builddf);
	             d19=d18+d19;
	             s7=pay.format(d19);
	             
	             
	             s14=pay1.format(Double.parseDouble(builddf)/Double.parseDouble(actualpay)*100);
	             d20=Double.parseDouble(pay1.format(Double.parseDouble(builddf)/Double.parseDouble(actualpay)));
	             d21=d20+d21;
	            // s8=pay1.format(d21);
	              //====================
	             d=Double.parseDouble(actualpay);
	             d2=Double.parseDouble(networkdf);
	             d1=d+d1;
	             s=pay.format(d1);//总费用
	              d3=d2+d3;
	              ss=pay.format(d3);//生产费用总和
	             s5=pay1.format(d11/d1*100);
	             s1=pay1.format(d3/d1*100);
	             s6=pay1.format(d15/d1*100);
	             s3=pay1.format(d7/d1*100);
	             s8=pay1.format(d19/d1*100);
	              //===============
             if(intnum%2==0){
 			    color="#FFFFFF" ;
 			 }else{
 			    color="#DDDDDD";
 			 }
               intnum++;
            %>
            <tr bgcolor="<%=color%>">
	            <td><div align="center" ><input type="hidden" name="code" value="<%=shi %>"/><%=intnum%></div></td>
	       		<td><div align="left" ><%=xian%></div></td> 
	       		<td><div align="right" ><%=actualpay%></div></td> 
	       		<td><div align="right" ><a href="javascript:modifyad('<%=shi%>','<%=times %>','<%=accountmonth %>','<%=loginId %>','<%=codedd%>')" ><%=sumjz%></a></div></td> 
	       		<td><div align="right" ><%=networkdf%></div></td> 
	       		<td><div align="right" ><%=s11%>%</div></td> 
	       		<td><div align="right" ><%=administrativedf%></div></td>
	       		<td><div align="right" ><%=s12%>%</div></td> 
	       		<td><div align="right" ><%=marketdf%></div></td>
	       		<td><div align="right" ><%=s13%>%</div></td> 
	       		<td><div align="right" ><%=informatizationdf%></div></td>
	       		<td><div align="right" ><%=s10%>%</div></td> 
	       		
	       		<td><div align="right" ><%=builddf%></div></td>
	       		<td><div align="right" ><%=s14%>%</div></td>
	       		
	       		
       		</tr>
       		<% }}%>
       		<tr bgcolor="#DDDDDD">
 			 <td><div align="center">合计</div></td>
 			 <td>&nbsp;</td>
 			 <td><div align="right"><%=s%></div></td>
 			 <td><div align="right"><%=df%></div></td>
 			 
 			 
 			 
 			  <td><div align="right"><%=ss%></div></td>
 			 <td><div align="right"><%=s1%>%</div></td>
 			 
 			 <td><div align="right"><%=s4%></div></td>
 			 <td><div align="right"><%=s5%>%</div></td>
 			 
 			 <td><div align="right"><%=s9%></div></td>
 			  <td><div align="right"><%=s6%>%</div></td>
 			 
 			 
 			 
 			  <td><div align="right"><%=s2%></div></td>
 			 <td><div align="right"><%=s3%>%</div></td>
 			 
 			 
 			 
 			  
 			  
 			 <td><div align="right"><%=s7%></div></td>
 			 <td><div align="right"><%=s8%>%</div></td>
 			 
 			 </tr>
       		
 			 
 			 </table>
    </form>
  </body>
</html>
