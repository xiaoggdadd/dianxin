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
String site=request.getParameter("site");
if(bili==null||bili.equals(""))bili="0";
Double bl=Double.parseDouble(bili);
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
    var url=path+"/web/sightcing/sitebenchmarking3.jsp?zdcode="+zdcode+"&startmonth="+startmonth+"&endmonth="+endmonth;		
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
      	     <%
      	     if(site.equals("0")){
      	    	 %>
      	    	 <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超标比例</div></td>
      	    	 <% 
      	     }
      	     %>
            <td width="20%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
            <td width="20%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">单价</div></td>
            <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td>
             <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">缴费周期</div></td>
             <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">PUE值</div></td>
             <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">倍率</div></td>
             <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">线损类型</div></td>
             <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">线损值</div></td>
       
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
           if(pue != null && !pue.equals("") && !pue.equals("0")){
 		    	 str1=str1+" AND A.BLHDL / (Z.ZLFH * 54 + Z.JLFH * 220)>'"+pue+"' ";
 		     }
 		 System.out.println("where"+wherestr);
           List<SiteBeanchmark> list=bean.getsite3(loginId,zdlx,dl,bl,startmonth,endmonth,str1,wherestr,site);
           DecimalFormat mat=new DecimalFormat("0.00");
           DecimalFormat mat1=new DecimalFormat("0.0000");
           int intnum=0;
           for(SiteBeanchmark bean1:list){
        	   String color="";
        	   String zdname="",zdcode="",ratio="",szdq="",df="",jtlx="",fkzq="",beilv="",xslx="",xsz="";
        	   zdname=bean1.getZdname();
        	   zdcode=bean1.getZdcode();
        	   ratio=bean1.getRatio();
        	   if(site.equals("0")){
        		   ratio=mat.format((Double.parseDouble(ratio)-1)*100)+"%";
        	   }
        	   szdq=bean1.getSzdq();
        	   df=bean1.getDf();
        	   jtlx=bean1.getZdlx();
        	   fkzq=bean1.getFkzq();
        	   pue=bean1.getPue();
        	   beilv=bean1.getBeilv();
        	   xslx=bean1.getXslx();
        	   xsz=bean1.getXsz();
        	   if(df==null)df="";
        	   if(pue==null)pue="";
        	   if(fkzq==null)fkzq="";
        	   if(jtlx==null)jtlx="";
        	   if(beilv==null)beilv="";
        	   if(xslx==null)xslx="";
        	   if(xsz==null)xsz="";
        	   if(!pue.equals("")){
        		   pue=mat.format(Double.parseDouble(pue));
        	   }
        	   if(!"".equals(df)&&null!=df){
        		   df=mat1.format(Double.parseDouble(df));
        	   }
        	  
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
       		<%
       		  if(site.equals("0")){
       			  %>
       			  <td>
       			<div align="center" ><a href="javascript:dfinfo1('<%=zdcode %>','<%=startmonth %>','<%=endmonth %>')"><%=ratio%></a></div>
       		</td>
       		<td>
       			<div align="left" ><a href="javascript:dfinfo('<%=zdcode %>')"><%=zdname%></a></div>
       		</td> 
       			  <% 
       		  }else{
       			  %>
       			  <td>
       			<div align="left" ><a href="javascript:dfinfo1('<%=zdcode %>','<%=startmonth %>','<%=endmonth %>')"><%=zdname%></a></div>
       		</td> 
       			  <% 
       		  }
       		%>
       		
       		<td>
       			<div align="center" ><%=szdq%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=df%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=jtlx%></div>
       		</td>    
        	<td>
       			<div align="center" ><%=fkzq%></div>
       		</td>
       		<td>
       			<div align="right" ><%=pue%></div>
       		</td>
       		<td>
       			<div align="right" ><%=beilv%></div>
       		</td>    
        	<td>
       			<div align="center" ><%=xslx%></div>
       		</td>
       		<td>
       			<div align="right" ><%=xsz%></div>
       		</td>
       		</tr>
        	  <% 
        	   
           }
           
           %>
           
           
       </table>
    </form>
  </body>
</html>

