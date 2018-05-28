<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.noki.electricfees.javabean.WentDutchStatisticsBean" %>
<%@ page import="java.text.*" %>
<%
String path = request.getContextPath();
String shi=request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String accountmonth=request.getParameter("accountmonth")!=null?request.getParameter("accountmonth"):"";
String times=request.getParameter("times")!=null?request.getParameter("times"):"";
String loginId=request.getParameter("loginId")!=null?request.getParameter("loginId"):"";
String codedd=request.getParameter("codedd")!=null?request.getParameter("codedd"):"";
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
  </head>

<body class="body">
    <form action="" name="form1" method="post">
      	<table class="form_label">
					<tr>
                        <td colspan="8">
                        <div style="width:50px;display:inline;"><hr></div>&nbsp;电费信息列表(万元)&nbsp;<div style="width:300px;display:inline;"><hr></div>
                        </td>							
			       </tr>
			</table>
			<table class="form_label" width="100%" height="240px" bgcolor="#cbd5de"  cellspacing="1" cellpadding="1">
			<tr>
<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
<td width="15%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
	            <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">总费用</div></td>
	            <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">生产</div></td>
	            <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">办公</div></td>
	            <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">营业</div></td>
	  			<td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">信息化支撑</div></td> 
	  			<td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">建设投资</div></td> 
	  			<td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始月份</div></td> 
	  			<td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束月份</div></td> 
            </tr>
            <%
            String whereStr="";
            int intnum=0;
            String color="";
            double sum1=0,sum2=0,sum3=0,sum4=0,sum5=0,sum6=0,sum7=0,sum8=0,sum9=0,sum10=0,sum11=0,sum12=0;
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
            //===========
          System.out.println("=============wo====");
          System.out.println("-"+shi+"-"+accountmonth+"-"+times+"-"+loginId);
            //======
            String sumjz="";
            if(shi != null && !shi.equals("") && !shi.equals("0")){
    			whereStr=whereStr+" and z.shi='"+shi+"'";
    		}if(times != null && !times.equals("") && !times.equals("0")){
    			whereStr=whereStr+" and e.startmonth> = '"+times+"'and e.endmonth< = '"+times+"'";
    		}if(accountmonth != null && !accountmonth.equals("") && !accountmonth.equals("0")){
    			whereStr=whereStr+" and e.ACCOUNTMONTH = '"+accountmonth+"'";
    		}
    		if(codedd != null && !codedd.equals("")){
    			whereStr=whereStr+" and z.xian = '"+codedd+"'";
    		}
          	 ArrayList fylist = new ArrayList();
             WentDutchStatisticsBean bean=new WentDutchStatisticsBean();
             fylist=bean.getStatistics1(loginId,whereStr);
             System.out.println("地市电费分摊汇总-222"+fylist);
             String jzname="",startmonth="",endmonth="", shi1="",actualpay="",networkdf="",informatizationdf="",administrativedf="",marketdf="",builddf="";
             if(fylist!=null){
             int fycount = ((Integer)fylist.get(0)).intValue();
 			 for( int k = fycount;k<fylist.size()-1;k+=fycount){
	             shi1 = (String)fylist.get(k+fylist.indexOf("SHI"));
	             shi1 = shi1 != null ? shi1 : "";
	             
	             actualpay = (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));
	             actualpay = actualpay != null ? actualpay : "";
 	            
	             jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
	             jzname = jzname != null ? jzname : "";
	             
	             startmonth = (String)fylist.get(k+fylist.indexOf("STARTMONTH"));
	             startmonth = startmonth != null ? startmonth : "";
	             
	             
	             endmonth = (String)fylist.get(k+fylist.indexOf("ENDMONTH"));
	             startmonth = startmonth != null ? startmonth : "";
	             
	             
	             
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
	             
	             sum1=Double.parseDouble(actualpay);
	             sum2=sum1+sum2;
	             s2=pay.format(sum2);
	             sum3=Double.parseDouble(networkdf);
	             sum4=sum3+sum4;
	             s3=pay.format(sum4);
	             sum5=Double.parseDouble(informatizationdf);
	             sum6=sum5+sum6;
	             s4=pay.format(sum6);
	             sum7=Double.parseDouble(administrativedf);
	             sum8=sum7+sum8;
	             s5=pay.format(sum8);
	             
	             sum9=Double.parseDouble(marketdf);
	             sum10=sum10+sum9;
	             s6=pay.format(sum10);
	             
	             sum11=Double.parseDouble(builddf);
	             sum12=sum11+sum12;
	             s7=pay.format(sum12);
	            //============
	          
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
	          <td><div align="right" ><%=actualpay%></div></td> 
	          <td><div align="right" ><%=networkdf%></div></td> 
	          <td><div align="right" ><%=administrativedf%></div></td> 
	          <td><div align="right" ><%=marketdf%></div></td> 
	          <td><div align="right" ><%=informatizationdf%></div></td> 
	          <td><div align="right" ><%=builddf%></div></td> 
	          <td><div align="center" ><%=startmonth%></div></td> 
	          <td><div align="center" ><%=endmonth%></div></td> 
	       		
	       		
       		</tr>
       		 <%}
 			 }%>
 			 <tr bgcolor="#DDDDDD">
 			 <td><div align="center">合计</div></td>
 			 <td>&nbsp;</td>
 			 <td><div align="right"><%=s2%></div></td>
 			 <td><div align="right"><%=s3%></div></td>
 			  <td><div align="right"><%=s5%></div></td>
 			 <td><div align="right"><%=s6%></div></td>
 			  <td><div align="right"><%=s4%></div></td>
 			 <td><div align="right"><%=s7%></div></td>
 			 <td>&nbsp;</td>
 			 <td>&nbsp;</td>
 			
 			 
 			 </tr>
 			 </table>
    </form>
  </body>
</html>
