  <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.Account,com.noki.mobi.common.AccountJzqa,com.noki.mobi.common.zdbzbeanB" %>
<%@ page import="java.text.*"%>
<%
	String xlxa=request.getParameter("xlxa");
String color = null;
int intnum=0;
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sheng = (String)session.getAttribute("accountSheng");
String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String loginName = (String)session.getAttribute("loginName");
String yuefen=request.getParameter("yuefen");
Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
// dbid="+dbid+"&bl="+bl+"&yf="+yf+"&bzmonth="+bzmonth+"&sjyf="+sjyf+"&shi="+shia+"&xiana="+xiana+"&xiaoqu="+xiaoqua+"&bili="+bili;
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
String dbid=request.getParameter("dbid");
String bl=request.getParameter("bl");
String bzmonth=request.getParameter("bzmonth");
String sjyf=request.getParameter("sjyf");
String bili=request.getParameter("bili");
String shia=request.getParameter("shi");
String xiana=request.getParameter("xiana");
String xiaoqua=request.getParameter("xiaoqu");
String yf=request.getParameter("yf");
String dbyt=request.getParameter("dbyt");
String id=request.getParameter("id");
int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
      curpage=Integer.parseInt(s_curpage);
      String permissionRights="";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stationfees.jsp' starting page</title>
<style type="text/css">
   .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
    }
</style>

  </head>
 <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean> 
  <body>
  <form action="" name="form1" method="POST">
   <table width="100%" border="0" cellspacing="1" cellpadding="1" class="form_label">
	<tr class='form_label'>
		<td colspan="11"><br/><div id="parent" style="display:inline"><div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div></div>	</td>
    </tr>
   	<tr bgcolor="#DDDDDD" style="color:black;font-weight:bold;" align="center">
   	 	<th>站点名称</th> 
   	 	<th>电表名称</th>
   	 	<th>本次抄表时间</th>
   	 	<th>上次抄表时间</th>
   	 	<th>本次读数</th>			
   	 	<th>上次读数</th>
   	 	<th>实际用电量</th>
 	</tr>		
  	 	<%
  	 	if(null !=shia  && !shia.equals("") && !shia.equals("0")){
  	 	String whereStr="";
  	 		if(null !=shia  && !shia.equals("") && !shia.equals("0")){
              	whereStr=whereStr+" and zd.shi='"+shia+"'";
              		}
              		if(null != xiana && !xiana.equals("") && !xiana.equals("0")){
              	
              	whereStr=whereStr+" and zd.xian='"+xiana+"'";
              		}		
              		if(null != dbid && !dbid.equals("") && !dbid.equals("0")){
              	
              	whereStr=whereStr+" and DBB.dbid='"+dbid+"'";
              		}
  	 	   	if(null!=yf&&!yf.equals("")){
                    if("3".equals(yf)){
                  if(null!=sjyf&&!"".equals(sjyf)){
                 	whereStr=whereStr+" and e.accountmonth='"+sjyf+"'";
                 }
              }
              if("2".equals(yf)){
              if(null!=sjyf&&!"".equals(sjyf)){
                 	whereStr=whereStr+" and startmonth='"+sjyf+"'";
                 }
              }
           }
  	 	            
  	 	
  	 	
		  	 		AccountJzqa   bean = new AccountJzqa();
		  	 	       	 List<zdbzbeanB> fylist = null;
		  	 	       	 fylist=bean.getPageDatapadl(whereStr,yf,dbyt);
		  	 	        String jzname="",zdcode="",szdq="",stationtype="",property="",danjia="",bgsign="",yflx="",gdfs="";
		  	 	       	 String jznamea="",dbname="",bcds="",scds="",scsj="",bcsj="",ydl="",acmonth;
		  	 	       	//int intnum=xh = pagesize*(curpage-1)+1;
		  	 	       	 DecimalFormat ww =new DecimalFormat("0.00");
		  	 	       	if(fylist!=null)
		  	 	 		{
		  	 	        		for(zdbzbeanB be:fylist){	
		  	 	        			jznamea=be.getZdname();
		  	 	        			if(null==jznamea||"null".equals(jznamea)){
		  	 	        			 jznamea="";
		  	 	        			}
		  	 	        			dbname=be.getDbname();
		  	 	        			if(null==dbname||"null".equals(dbname)){
		  	 	        			 dbname="";
		  	 	        			}
		  	 	        			bcds=be.getBccb();
		  	 	        			if(null==bcds||"".equals(bcds)){
		  	 	        			 bcds="0";
		  	 	        			}
		  	 	        			bcds=ww.format(Double.parseDouble(bcds));
		  	 	        			scds=be.getSccb();
		  	 	        			if(null==scds||"".equals(scds)){
		  	 	        			 scds="0";
		  	 	        			}
		  	 	        				scds=ww.format(Double.parseDouble(scds));
		  	 	        			scsj=be.getSccbsj();
		  	 	        			bcsj=be.getBccbsj();
		  	 	        			ydl=be.getSjydl();
		  	 	        			if(null==ydl||"".equals(ydl)){
		  	 	        			 ydl="0";
		  	 	        			}
		  	 	        				ydl=ww.format(Double.parseDouble(ydl));
		  	 	        		 acmonth=be.getAccountmonth();
		  	 	        		 if(null==acmonth||"null".equals(acmonth)){
		  	 	        		  acmonth="";
		  	 	        		 }
		  	 	        			if (intnum % 2 == 0) {
		  	 	        				color = "#FFFFFF";

		  	 	        			} else {
		  	 	        				color = "#DDDDDD";
		  	 	        			}
		  	 	        			  intnum++;
		  	 	%>  		
    <tr bgcolor="<%=color%>">  
	   	<td><div align="left" ><%=jznamea%></div></td>
	   	<td><div align="center" ><%=dbname%></div></td>
   	    <td><div align="center" ><%=bcsj%></div></td>
   	    <td><div align="center" ><%=scsj%></div></td>
       	<td><div align="right" ><%=bcds%></div></td>
   	    <td><div align="right" ><%=scds%></div></td>
   	    <td><div align="right" ><%=ydl%></div></td>
   	</tr>
  
   	 <%
 	 
 		}
  	 	}
 		}
           %>
         
  </table>
   </form>   
  </body>
</html>
    