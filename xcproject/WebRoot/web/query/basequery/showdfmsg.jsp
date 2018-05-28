<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.ElectricFeesQueryBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<html>
<% 
        String path = request.getContextPath();
		int intnum=0;
		String color="";
%>
<head>
<title>详细信息
</title>
<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}

.form {width:130px}
.bttcn{color:black;font-weight:bold;}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
</head>
<jsp:useBean id="bean" scope="page" class="com.noki.query.basequery.javabean.ElectricFeesQueryBean">
</jsp:useBean>
<body  class="body">
	<table>
		 <tr>
  		<td colspan="2">
			 <div style="width:470px;height=50px">
				  <img alt="" src="<%=path%>/images/btt.bmp" width="480px" height="100%" />
				  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">详细信息列表</span>	
			 </div>
			</td>
    	</tr>
		<tr><td colspan="2"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
	</table>  	
  <table width="1300px" border="0" cellspacing="1" cellpadding="1" class="form_label">
   <tr class='form_label' bgcolor="#DDDDDD">
       		<td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>
            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td>
             <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次读数</div></td>
  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次读数</div></td>  			
  			
            <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电量调整</div></td>
            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际用电量</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">单价</div></td>
             <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">费用调整</div></td>
  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">金额</div></td>  	
  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始月份</div></td> 
  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束月份</div></td>		
  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份
</div></td> 
    	</tr>     	  
       <%
       	String zdid = request.getParameter("zdid");
       	String dlid = request.getParameter("dlid");
       	String dbid = request.getParameter("dbid");
       	 ArrayList fylist = new ArrayList();       	 
       if(zdid != null && !zdid.equals("")&&dbid !=null && !dbid.equals("")&&dlid !=null&& !dlid.equals("")){	 
       	 fylist = bean.getinformationdfmsg(zdid,dlid,dbid);
		double df=0.0;
		String jzname="",dbname="",lastdate="",lastde="",thiddate="",thisde="",startm="",endmon="";
		 String floatdegree="",blhdl="",dianfei="",floatpay="",actualpay="",accountmonth="";
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
        
			//电量分摊信息
			DecimalFormat la =new DecimalFormat("0.00");
			DecimalFormat laa =new DecimalFormat("0.0000");
		    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    jzname = jzname != null ? jzname : "";		    			 
		    dbname = (String)fylist.get(k+fylist.indexOf("DBNAME"));
		    dbname = dbname != null ? dbname : "";
		    lastdate = (String)fylist.get(k+fylist.indexOf("LASTDATETIME"));
		    lastdate = lastdate != null ? lastdate : "";
		    
		    lastde = (String)fylist.get(k+fylist.indexOf("LASTDEGREE"));
		    lastde = lastde != null ? lastde : "0";
		    if("".equals(lastde)||"null".equals(lastde)){lastde="0";}
		    lastde=la.format(Double.parseDouble(lastde));
		     thiddate = (String)fylist.get(k+fylist.indexOf("THISDATETIME"));
		    thiddate = thiddate != null ? thiddate : "";
		    
		     thisde = (String)fylist.get(k+fylist.indexOf("THISDEGREE"));
		    thisde = thisde != null ? thisde : "0";
		    if("".equals(thisde)||"null".equals(thisde)){thisde="0";}
		    thisde=la.format(Double.parseDouble(thisde));
		     startm = (String)fylist.get(k+fylist.indexOf("STARTMONTH"));
		    startm = startm != null ? startm : "";
		    
		     endmon = (String)fylist.get(k+fylist.indexOf("ENDMONTH"));
		    endmon = endmon != null ? endmon : "";
		   //String floatdegree="",blhdl="",dianfei="",floatpay="",actualpay="",accountmonth="";am.Floatdegree,
		   floatdegree = (String)fylist.get(k+fylist.indexOf("FLOATDEGREE"));
		    floatdegree = floatdegree != null ? floatdegree : "0";
		    if("".equals(floatdegree)||"null".equals(floatdegree)){floatdegree="0";}
		    floatdegree=la.format(Double.parseDouble(floatdegree));
		    
		    blhdl = (String)fylist.get(k+fylist.indexOf("BLHDL"));
		    blhdl = blhdl != null ? blhdl : "0";
		    if("".equals(blhdl)||"null".equals(blhdl)){blhdl="0";}
		    blhdl=la.format(Double.parseDouble(blhdl));
		    dianfei = (String)fylist.get(k+fylist.indexOf("DIANFEI"));
		    dianfei = dianfei != null ? dianfei : "0";
		     if("".equals(dianfei)||"null".equals(dianfei)){dianfei="0";}
		    dianfei=laa.format(Double.parseDouble(dianfei));
		    
		    
		    floatpay = (String)fylist.get(k+fylist.indexOf("FLOATPAY"));
		    floatpay = floatpay != null ? floatpay : "0";
		    if("".equals(floatpay)||"null".equals(floatpay)){floatpay="0";}
		    floatpay=la.format(Double.parseDouble(floatpay));
		    
		    actualpay = (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));
		    actualpay = actualpay != null ? actualpay : "";
		     if("".equals(actualpay)||"null".equals(actualpay)){actualpay="0";}
		    actualpay=la.format(Double.parseDouble(actualpay));
		    
		    accountmonth = (String)fylist.get(k+fylist.indexOf("ACCOUNTMONTH"));
		    accountmonth = accountmonth != null ? accountmonth : "";
       %>
       <%} %>
       <%} %>
       <tr color="#FFFFFF">
 <td>
       			<div align="left" ><%=jzname%></div>
       		</td>     
       		<td>
       			<div align="center" ><%=dbname%></div>
       		</td> 

 <td>
       			<div align="center" ><%=lastdate%></div>
       		</td>  
       		<td>
       			<div align="center" ><%=thiddate%></div>
       		</td>    
       		<td>
       			<div align="right" ><%=lastde%></div>
       		</td>  
       		
       		<td>
       			<div align="right" ><%=thisde%></div>
       		</td>  
       		
       		<td>
       			<div align="right" ><%=floatdegree%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=blhdl%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=dianfei%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=floatpay%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=actualpay%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=startm%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=endmon%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=accountmonth%></div>
       		</td> 
       		
 </tr>
       <%}%>
      	 
    	   	

  </table>

</body>
</html>




