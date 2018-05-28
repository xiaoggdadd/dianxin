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
  <table width="90%" border="0" cellspacing="1" cellpadding="1" class="form_label">
   <tr class='form_label' bgcolor="#DDDDDD">
   <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
       		<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">额定耗电量</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">倍率</div></td>
             <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">是否标杆</div></td>
             
  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">是否节能</div></td>  			
  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">有无空调</div></td> 
    	</tr>     	  
       <%
       	String zdid = request.getParameter("zdid");
       	 ArrayList fylist = new ArrayList();       	 
       if(zdid != null && !zdid.equals("")){	 
       	 fylist = bean.getinformationzdmsg(zdid);
		double df=0.0;
		String zdname="",zdpro="",zdlx="",edhdl="",blv="",isbg="",isjn="",iskt="";
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
            //=================
      //    1.站点属性，站点类型，额定耗电量，倍率，是否标杆，是否节能
//，空调有无，
		  //  select  z.edhdl,z.bgsign,z.jnglmk,z.kongtiao,d.beilv,(select t.name from indexs t where t.code = z.property and TYPE = 'ZDSX') as property,
        //(select NAME from indexs where CODE = z.STATIONTYPE and type = 'stationtype') as stationtype
 //from zhandian z, dianbiao d where z.id = d.zdid and z.zdcode='bz_12986'
			//电量分摊信息
			DecimalFormat la =new DecimalFormat("0.00");
		    zdpro = (String)fylist.get(k+fylist.indexOf("PROPERTY"));
		    zdpro = zdpro != null ? zdpro : "";		    			 
		    zdlx = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
		    zdlx = zdlx != null ? zdlx : "";
		    zdname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    zdname = zdname != null ? zdname : "";
		    if("".equals(zdname)||"null".equals(zdname)){
		     zdname="";
		    }
		    edhdl = (String)fylist.get(k+fylist.indexOf("EDHDL"));
		    System.out.println(edhdl+"ddddddSSSS");
		    edhdl = edhdl != null ? edhdl : "0";
		    if(edhdl.equals("")||"null".equals(edhdl)){
		      edhdl="0";
		    }
		    edhdl=la.format(Double.parseDouble(edhdl));
		    
		    blv = (String)fylist.get(k+fylist.indexOf("BEILV"));
		    blv = blv != null ? blv : "0";
		     isbg = (String)fylist.get(k+fylist.indexOf("BGSIGN"));
		    isbg = isbg != null ? isbg : "";
		    if(isbg.equals("1")){
		     isbg="是";
		    }else{
		     isbg="否";
		    }
		     isjn = (String)fylist.get(k+fylist.indexOf("JNLMK"));
		    isjn = isjn != null ? isjn : "";
		     if(isjn.equals("1")){
		     isjn="是";
		    }else{
		     isjn="否";
		    }
		     iskt = (String)fylist.get(k+fylist.indexOf("KONGTIAO"));
		    iskt = iskt != null ? iskt : "";
		     if(iskt.equals("1")){
		     iskt="有";
		    }else{
		     iskt="无";
		    }
       %>
       <%} %>
       <%} %>
       <tr color="#DDDDDD">
       <td>
       			<div align="left" ><%=zdname%></div>
       		</td> 
 <td>
       			<div align="center" ><%=zdpro%></div>
       		</td>     
       		<td>
       			<div align="center" ><%=zdlx%></div>
       		</td> 

 <td>
       			<div align="right" ><%=edhdl%></div>
       		</td>     
       		<td>
       			<div align="right" ><%=blv%></div>
       		</td>  
       		<td>
       			<div align="center" ><%=isbg%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=isjn%></div>
       		</td>  
       		<td>
       			<div align="center" ><%=iskt%></div>
       		</td> 
 </tr>
       <%}%>
      	 
    	   	

  </table>

</body>
</html>




