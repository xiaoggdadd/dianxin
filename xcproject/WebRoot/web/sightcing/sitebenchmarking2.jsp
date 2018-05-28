<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.mobi.cx.SiteBeanchmark" %>
<%@ page import="java.text.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Account account=(Account)session.getAttribute("account");
String loginId=account.getAccountId();
String shi=request.getParameter("shi");
String zdlx=request.getParameter("zdlx");
String dl=request.getParameter("dl");
String bili=request.getParameter("bili");
String startmonth=request.getParameter("startmonth");
String endmonth=request.getParameter("endmonth");
String pue=request.getParameter("pue");
String dytype=request.getParameter("dytype");
String g2=request.getParameter("g2");
String g3=request.getParameter("g3");
String kt1=request.getParameter("kt1");
String kt2=request.getParameter("kt2");
String kdsb=request.getParameter("kdsb");
String yysb=request.getParameter("yysb");
String zgd=request.getParameter("zgd");
String zpslzd=request.getParameter("zpslzd");
String yysbsl=request.getParameter("yysbsl");
String zssl=request.getParameter("zssl");
String kdsbsl=request.getParameter("kdsbsl");
String zlfh=request.getParameter("zlfh");
String jlfh=request.getParameter("jlfh");
String zdsx=request.getParameter("zdsx");

System.out.println(zdlx+"}}}}}}}}}}}}}"+dl);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sitebenchmarking1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px

		}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
 .memo {border: 1px #C8E1FB solid}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}

.bttcn{color:black;font-weight:bold;}

.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.form_la{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}
</style>

  </head>
  <script type="text/javascript">
  var path = '<%=path%>';
  
  function dfinfo1(zdcode,startmonth,endmonth){
    var url=path+"/web/sightcing/sitebenchmarking5.jsp?zdcode="+zdcode+"&startmonth="+startmonth+"&endmonth="+endmonth;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1000px;dialogHeight:1000px;status:auto;scroll:auto');	
}
   function dfinfo(zdcode){
    var url=path+"/web/sightcing/sitebenchmarking4.jsp?zdcode="+zdcode;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1000px;dialogHeight:1000px;status:auto;scroll:auto');	
}
  </script>
  <body class="body" style="overflow-x:hidden;">
    <form action="" name="form1" method="post">
       <table width="100%" height="30%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
      	<tr height = "23" class="relativeTag ">
      	    <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">编号</div></td>
            <td width="20%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
            <td width="20%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
            <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">条数</div></td>
             <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">耗电量</div></td>
             <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">平均日耗电量</div></td>
             <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">PUE值</div></td>
            <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始时间</div></td>
            <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束时间</div></td>
   
       
       </tr>
           <%
           
           SiteBeanchmark bean=new SiteBeanchmark();
           String str1="";
           String wherestr="";
           if(shi != null && !shi.equals("") && !shi.equals("0")){
        	   wherestr=wherestr+" and z.shi='"+shi+"'";
   		     }if(zdsx != null && !zdsx.equals("") && !zdsx.equals("0")){
        	   wherestr=wherestr+" and z.property='"+zdsx+"'";
   		     }if(dytype != null && !dytype.equals("") && !dytype.equals("0")){
        	   wherestr=wherestr+" and z.dytype='"+dytype+"'";
   		     }if(g2 != null && !g2.equals("") && !g2.equals("-1")){
        	   wherestr=wherestr+" and z.g2='"+g2+"'";
   		     }if(g3 != null && !g3.equals("") && !g3.equals("-1")){
        	   wherestr=wherestr+" and z.g3='"+g3+"'";
   		     }if(kt1 != null && !kt1.equals("") && !kt1.equals("-1")){
        	   wherestr=wherestr+" and z.kt1='"+kt1+"'";
   		     }if(kt2 != null && !kt2.equals("") && !kt2.equals("-1")){
        	   wherestr=wherestr+" and z.kt2='"+kt2+"'";
   		     }if(kdsb != null && !kdsb.equals("") && !kdsb.equals("-1")){
        	   wherestr=wherestr+" and z.kdsb='"+kdsb+"'";
   		     }if(yysb != null && !yysb.equals("") && !yysb.equals("-1")){
        	   wherestr=wherestr+" and z.yysb='"+yysb+"'";
   		     }if(zgd != null && !zgd.equals("") && !zgd.equals("-1")){
        	   wherestr=wherestr+" and z.zgd='"+zgd+"'";
   		     }if(zpslzd != null && !zpslzd.equals("") && !zpslzd.equals("0")){
        	   wherestr=wherestr+" and z.zpsl='"+zpslzd+"'";
   		     }if(yysbsl != null && !yysbsl.equals("") && !yysbsl.equals("0")){
        	   wherestr=wherestr+" and z.yysbsl='"+yysbsl+"'";
   		     }if(zssl != null && !zssl.equals("") && !zssl.equals("0")){
        	   wherestr=wherestr+" and z.zssl='"+zssl+"'";
   		     }if(kdsbsl != null && !kdsbsl.equals("") && !kdsbsl.equals("0")){
        	   wherestr=wherestr+" and z.kdsbsl='"+kdsbsl+"'";
   		     }if(zlfh != null && !zlfh.equals("") && !zlfh.equals("0")){
        	   wherestr=wherestr+" and z.zlfh='"+zlfh+"'";
   		     }if(jlfh != null && !jlfh.equals("") && !jlfh.equals("0")){
        	   wherestr=wherestr+" and z.jlfh='"+jlfh+"'";
   		     }
          
 		 System.out.println("where"+wherestr);
           List<SiteBeanchmark> list=bean.getsite2(loginId,zdlx,startmonth,endmonth,wherestr);
           DecimalFormat mat=new DecimalFormat("0.0");
           DecimalFormat mat1=new DecimalFormat("0.0000");
           int intnum=0;
           String zdname="",hdl="",count="",szdq="",lasttime="",thistime="",zdcode="", avegage="",puez="";
           for(SiteBeanchmark bean1:list){
        	   String color="";
        	  zdname=bean1.getZdname();
        	  hdl=bean1.getHdl();
        	  count=bean1.getAccountmonth();
        	  szdq=bean1.getSzdq();
        	  lasttime=bean1.getLasttime();
        	  thistime=bean1.getThistime();
        	  zdcode=bean1.getZdcode();
        	  puez=bean1.getPue();
        	  if(hdl==null)hdl="0";
        	  if(count==null)count="0";
        	  if(puez==null)puez="";
        	  
        	  hdl=mat.format(Double.parseDouble(hdl));
        	   avegage=mat.format(Double.parseDouble(hdl)/Double.parseDouble(count));
        	   if(intnum%2==0){
   			    color="#FFFFFF" ;
   			 }else{
   			    color="#DDDDDD";
   			}
              intnum++; 
        	  %>
        	    <tr bgcolor="<%=color%>">
        	    <td>
       			<div align="center" ><%=intnum%></div>
       		   </td>
       		    <td>
       			<div align="left" ><a href="javascript:dfinfo1('<%=zdcode %>','<%=startmonth %>','<%=endmonth %>')"><%=zdname%></a></div>
       		   </td>
       		    <td>
       			<div align="center" ><%=szdq%></div>
       		   </td>
       		    <td>
       			<div align="right" ><%=count%></div>
       		   </td>
       		    <td>
       			<div align="right" ><%=hdl%></div>
       		   </td>
       		    <td>
       			<div align="right" ><%=avegage%></div>
       		   </td> <td>
       			<div align="right" ><%=puez%></div>
       		   </td>
       		    <td>
       			<div align="center" ><%=lasttime%></div>
       		   </td>
       		    <td>
       			<div align="center" ><%=thistime%></div>
       		   </td>
       		
       		</tr>
        	  <% 
        	   
           }
           
           %>
           
           
       </table>
    </form>
  </body>
</html>

