<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet,com.noki.newfunction.javabean.Zdinfo,com.noki.newfunction.dao.CbzdQuery"%>
<%@ page import="java.text.*"%>
<html>
<% 
		int intnum=0;
		String color="";
		
		
%>
<head>
<title>站点及电费信息
</title>
<style>
 .style7 {font-weight: bold}
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
 </style>
</head>
<jsp:useBean id="bean" scope="page" class="com.noki.electricfees.javabean.ElectricFeesBean">
</jsp:useBean>
<body  class="body">
	<table>
		<tr><td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">站点及电费信息</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
	</table>  	
  <table width="1200px" height="400px"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
         	<tr height = "23" class="relativeTag ">
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">2G设备</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">3G设备</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">载频数量</div></td>  
           <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">载扇数量</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">厂家</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">基站类型</div></td>  
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">设备编码</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">BBU数量</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">RRU数量</div></td>  
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">移动设备数量</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">共享固网设备数量</div></td>  
             <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起码</div></td>
  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">止码</div></td>
  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td> 
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">周期</div></td>  
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">费用</div></td>
  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>  
        </tr>
       <%
       	String zdid = request.getParameter("zdid");  
       	String cbyf=request.getParameter("cbyf");
        CbzdQuery beana=new CbzdQuery();
        List<Zdinfo> fylist=null;     	
        // 2G设备	3G设备	载频数量	载扇数量	厂家	基站类型	设备编码	BBU数量	RRU数量	移动设备数量	共享固网设备数量	
        // 起码	止码	上次抄表时间	本次抄表时间	周期	用电量	费用	报账月份
         
       String g2="",g3="",zp="",zs="",cj="",jztype="",shebei="",bbu="",rru="",ydsbsl="",gwgxsl="";
       String qm="",zm="",sccbsj="",bccbsj="",zq="",ydl="",fy="",bzyf=""; 
       if(zdid != null && !zdid.equals("")&&cbyf!=null&&!cbyf.equals("")){
       fylist=beana.seachShowzdinfo(zdid,cbyf);
		double df=0.0;
		 if(fylist!=null){
		
			 for(Zdinfo ls:fylist){
		    g2=ls.getG2();
		    if("1".equals(g2)){g2="是";}
		    if(null==g2||"".equals(g2)){ g2="否";}
		    g3=ls.getG3();
		     if("1".equals(g3)){g3="是";}
		    if(null==g3||"".equals(g3)){ g3="否";}
		    zp=ls.getZp();
		    if(null==zp){zp="";}
		    zs=ls.getZs();
		     if(null==zs){zs="";}
		     cj=ls.getChangjia();if(null==cj){cj="";}
		     jztype=ls.getJztype();
		     if(null==jztype){jztype="";}
		     shebei=ls.getShebei();
		     if(null==shebei){shebei="";}
		     bbu=ls.getBbu();
		     if(null==bbu||"".equals(bbu)){bbu="";}
		     rru=ls.getRru();
		     if(null==rru){rru="";}
		     ydsbsl=ls.getYdshebei();
		     if(null==ydsbsl){ydsbsl="";}
		     gwgxsl=ls.getGxgwsl();if(null==gwgxsl){gwgxsl="";}
		     qm=ls.getQm();if(null==qm||"".equals(qm)){qm="0";}
		     zm=ls.getZm();if(null==zm||"".equals(zm)){zm="0";}
		     sccbsj=ls.getSccbsj();if(null==sccbsj){sccbsj="";}
		     bccbsj=ls.getBccbsj();if(null==bccbsj){bccbsj="";}
		     zq=ls.getZq();if(null==zq){zq="";}
		     fy=ls.getFy();if(null==fy||"".equals(fy)){fy="0";}
		     ydl=ls.getYdl();if(null==ydl){ydl="";}
		     bzyf=ls.getBzyf();if(null==bzyf){bzyf="";}
		    if(intnum%2==0){
			    color="#FFFFFF" ;
			 }else{
			    color="#DDDDDD";
			}
           intnum++;
       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=g2%></div>
       		</td>
       		<td>
       			<div align="left" ><%=g3%></div>
       		</td>
       		<td>
       			<div align="left" ><%=zp%></div>
       		</td>   		
        	<td>
       			<div align="right" ><%=zs%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=cj%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=jztype%></div>
       		</td>
       		  	<td>
       			<div align="center" ><%=shebei%></div>
       		</td>   	
       		<td>
       			<div align="right" ><%=bbu%></div>
       		</td>   
       		
       		<td>
       			<div align="right" ><%=rru%></div>
       		</td> 
       			
       		<td>
       			<div align="right" ><%=ydsbsl%></div>
       		</td>   
       		<td>
       			<div align="center" ><%=gwgxsl%></div>
       		</td>
       		<td>
       			<div align="center" ><%=qm%></div>
       		</td>    
       		<td>
       			<div align="right" ><%=zm%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=sccbsj%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=bccbsj%></div>
       		</td> 
       		
       		<td>
       			<div align="right" ><%=zq%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=ydl%></div>
       		</td>    
       		<td>
       			<div align="center" ><%=fy%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=bzyf%></div>
       		</td>  
    </tr>
       
       <%}}}%>
      
        <% if (intnum==0){
    	  System.out.println("QQQQ"+intnum);
         for(int i=0;i<5;i++){
          if(i%2==0){
			    color="#FFFFFF";
          }else{
			    color="#DDDDDD";
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
        </tr>
        <% }}%>
  </table>

</body>
</html>




