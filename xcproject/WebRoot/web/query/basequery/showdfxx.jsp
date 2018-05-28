<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.ElectricFeesQueryBean" %>
<%@ page import="java.sql.ResultSet,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
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
  <table width="480px" border="0" cellspacing="1" cellpadding="1" class="form_label">
       <%
       System.out.print("dddddd");
       	String dbid1 = request.getParameter("dbid");
       	String dlid1 = request.getParameter("dlid");
       	//电费分摊
       	String networkdf="",informatizationdf="",administrativedf="",marketdf="",builddf="",dddfdf="";
       	//电量分摊
       	String networkhdl="",informatizationhdl="",administrativehdl="",markethdl="",buildhdl="",dddfdl="";
       	    	
       	ElectricFeesFormBean bean1 = null;      	 
       if(dbid1 != null && !dbid1.equals("")&&dlid1 != null && !dlid1.equals("")){	 
       	 bean1 = bean.getinformationdbxx(dbid1,dlid1);
		double df=0.0;
		 if(bean1!=null){
            //电费分摊信息
            DecimalFormat b2 =new DecimalFormat("0.00");
		    networkdf = bean1.getScdff();
		    networkdf = networkdf != null ? networkdf : "";		    				    
		    if(networkdf==null||networkdf==""||networkdf=="o")networkdf="0.00";
            networkdf=b2.format(Double.parseDouble(networkdf));
		    
		    informatizationdf = bean1.getXxhdf();
		    informatizationdf = informatizationdf != null ? informatizationdf : "";
		    if(informatizationdf==null||informatizationdf==""||informatizationdf=="o")informatizationdf="0.00";
            informatizationdf=b2.format(Double.parseDouble(informatizationdf));
		    
		    administrativedf = bean1.getBgdf();
		    administrativedf = administrativedf != null ? administrativedf : "";
		    if(administrativedf==null||administrativedf==""||administrativedf=="o")administrativedf="0.00";
            administrativedf=b2.format(Double.parseDouble(administrativedf));
		    
		    marketdf = bean1.getYydf();
		    marketdf = marketdf !=null ? marketdf:"";
		    if(marketdf==null||marketdf==""||marketdf=="o")marketdf="0.00";
            marketdf=b2.format(Double.parseDouble(marketdf));

		    builddf = bean1.getJstzdf();
		    builddf = builddf !=null ? builddf:"";
		    if(builddf==null||builddf==""||builddf=="o")builddf="0.00";
            builddf=b2.format(Double.parseDouble(builddf));
            
            dddfdf = bean1.getDddfdf();//代垫电费
		    dddfdf = dddfdf !=null ? dddfdf:"";
		    if(dddfdf==null||dddfdf==""||dddfdf=="o")dddfdf="0.00";
            dddfdf=b2.format(Double.parseDouble(dddfdf));
		    
			//电量分摊信息
			DecimalFormat la =new DecimalFormat("0.0");
		    networkhdl = bean1.getScdl();
		    networkhdl = networkhdl != null ? networkhdl : "";		    			 
			if(networkhdl==null||networkhdl.equals("")||networkhdl.equals("null")||networkhdl.equals("o"))networkhdl="0.0";
            networkhdl=la.format(Double.parseDouble(networkhdl));
		    
		    informatizationhdl = bean1.getXxhdl();
		    informatizationhdl = informatizationhdl != null ? informatizationhdl : "";
			if(informatizationhdl==null||informatizationhdl.equals("")||informatizationhdl.equals("null")||informatizationhdl.equals("o"))informatizationhdl="0.0";
            informatizationhdl=la.format(Double.parseDouble(informatizationhdl));		     
		    
		    administrativehdl = bean1.getBgdl();
		    administrativehdl = administrativehdl != null ? administrativehdl : "";
		    if(administrativehdl==null||administrativehdl.equals("")||administrativehdl.equals("null")||administrativehdl.equals("o"))administrativehdl="0.0";
            administrativehdl=la.format(Double.parseDouble(administrativehdl)); 
		    
		    markethdl = bean1.getYydl();
		    markethdl = markethdl != null ? markethdl : ""; 
		    if(markethdl==null||markethdl.equals("")||markethdl.equals("null")||markethdl.equals("o"))markethdl="0.0";
            markethdl=la.format(Double.parseDouble(markethdl)); 		    		    
		    
		    buildhdl = bean1.getJstzdl();
		    buildhdl = buildhdl != null ? buildhdl : ""; 		    
		    if(buildhdl==null||buildhdl.equals("")||buildhdl.equals("null")||buildhdl.equals("o"))buildhdl="0.0";
            buildhdl=la.format(Double.parseDouble(buildhdl));
            
            dddfdl = bean1.getDddfdl();//代垫电量
		    dddfdl = dddfdl != null ? dddfdl : ""; 		    
		    if(dddfdl==null||dddfdl.equals("")||dddfdl.equals("null")||dddfdl.equals("o"))dddfdl="0.0";
            dddfdl=la.format(Double.parseDouble(dddfdl));
       %>
       <%} %>
       <%}%>
       <tr class='form_label' bgcolor="#DDDDDD">
       		<td width="300px">
       			<div align="left">网络运营线电费(生产)（元）：</div>
       		</td>
       		<td width="150px">
       			<div align="right"><%=networkdf%></div>
       		</td>     		   
    	</tr>
    	<tr class='form_label'>
       		<td width="300px">
       			<div align="left">信息化支撑线电费（元）：</div>
       		</td>
       		<td width="150px">
       			<div align="right"><%=informatizationdf%></div>
       		</td>     		   
    	</tr>
    	<tr class='form_label' bgcolor="#DDDDDD">
       		<td width="300px">
       			<div align="left">行政综合线电费（办公）（元）：</div>
       		</td>
       		<td width="150px">
       			<div align="right"><%=administrativedf%></div>
       		</td>     		   
    	</tr>
    	<tr class='form_label'>
       		<td width="300px">
       			<div align="left">市场营销线电费(营业)（元）：</div>
       		</td>
       		<td width="150px">
       			<div align="right"><%=marketdf%></div>
       		</td>     		   
    	</tr>
    	<tr class='form_label' bgcolor="#DDDDDD">
       		<td width="300px">
       			<div align="left">建设投资线电费（元）：</div>
       		</td>
       		<td width="150px">
       			<div align="right"><%=builddf%></div>
       		</td>     		   
    	</tr> 
    	<tr class='form_label'>
       		<td width="300px">
       			<div align="left">代垫电费（元）：</div>
       		</td>
       		<td width="150px">
       			<div align="right"><%=dddfdf%></div>
       		</td>     		   
    	</tr> 
    	
    	
       <tr class='form_label' bgcolor="#DDDDDD">
       		<td width="300px">
       			<div align="left">网络运营线耗电量（生产）（度）：</div>
       		</td>
       		<td width="150px">
       			<div align="right"><%=networkhdl%></div>
       		</td>     		   
    	</tr>
    	<tr class='form_label'>
       		<td width="300px">
       			<div align="left">信息化支撑线电费（度）：</div>
       		</td>
       		<td width="150px">
       			<div align="right" ><%=informatizationhdl%></div>
       		</td>     		   
    	</tr>
    	<tr class='form_label' bgcolor="#DDDDDD">
       		<td width="300px">
       			<div align="left">行政综合线电费（办公）（度）：</div>
       		</td>
       		<td width="150px">
       			<div align="right"><%=administrativehdl%></div>
       		</td>     		   
    	</tr>
    	<tr class='form_label'>
       		<td width="300px">
       			<div align="left">市场营销线电费(营业)（度）：</div>
       		</td>
       		<td width="150px">
       			<div align="right"><%=markethdl%></div>
       		</td>     		   
    	</tr>
    	<tr class='form_label' bgcolor="#DDDDDD">
       		<td width="300px">
       			<div align="left">建设投资线电费（度）：</div>
       		</td>
       		<td width="150px">
       			<div align="right"><%=buildhdl%></div>
       		</td>     		   
    	</tr> 
    	<tr class='form_label'>
       		<td width="300px">
       			<div align="left">代垫电费电量（度）：</div>
       		</td>
       		<td width="150px">
       			<div align="right"><%=dddfdl%></div>
       		</td>     		   
    	</tr>     	  	    	

 
  </table>

</body>
</html>




