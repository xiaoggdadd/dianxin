<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.query.basequery.javabean.PrepaymentQueryBean,com.noki.electricfees.javabean.ElectricFeesFormBean"%>

<%@ page import="java.text.*"%>
<%
String xlxa=request.getParameter("xlxa");
String dbid=request.getParameter("dbid");
String dlid=request.getParameter("dlid");
String color = null;
int intnum=0;
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sheng = (String)session.getAttribute("accountSheng");

String loginName = (String)session.getAttribute("loginName");

Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
      curpage=Integer.parseInt(s_curpage);
      String permissionRights="";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
 
<style type="text/css">
   .form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
</style>

  </head>
 <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean> 
  <body>
  <form action="" name="form1" method="POST">
   <table>
    <tr>
  		<td colspan="2">
			 <div style="width:470px;height=50px">
				  <img alt="" src="<%=path%>/images/btt.bmp" width="480px" height="100%" />
				  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:30px;color:black">详细信息列表</span>	
			 </div>
		</td>
    </tr>
	<tr class='form_label'>
		<td colspan="2"><br/><div id="parent" style="display:inline"><div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div></div>	</td>
    </tr>
     	</td>		
   	</tr>
   </table>   	
  	 	<%	  	 	
   	    PrepaymentQueryBean bean=new PrepaymentQueryBean();
        String networkdf="",informatizationdf="",administrativedf="",marketdf="",builddf="",dddfdf="";
       	ElectricFeesFormBean bean1 = bean.getPageDatae(dbid,loginId,dlid);
       	//int intnum=xh = pagesize*(curpage-1)+1;
         if(bean1 != null){
        			//num为序号，不同页中序号是连续的
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
 			 %> 
 <table border="0" cellspacing="1" cellpadding="1" class="form_label">
     <tr class='form_label' bgcolor="#DDDDDD" >
   	    <td width="300px">网络运营线电费(生产)(元)：</td><td width="150px"><div align="right" ><%=networkdf%>&nbsp;</div></td> 
   	 </tr>	  
   	 <tr class='form_label'>
   	    <td>行政综合线电费（办公）(元)：</td><td><div align="right" ><%=administrativedf%>&nbsp;</div></td> 
   	 </tr>
   	 <tr class='form_label' bgcolor="#DDDDDD">
   	 	<td>市场营销线电费(营业)(元)：</td><td><div align="right" ><%=marketdf%>&nbsp;</div></td>
   	 </tr>	
   	 <tr class='form_label'>
   		 <td>信息化支撑线电费(元)：</td> <td><div align="right" ><%=informatizationdf%>&nbsp;</div></td>
   	 </tr>	
   	 <tr class='form_label' bgcolor="#DDDDDD">	
   	 	<td>建设投资线电费(元)：</td><td><div align="right" ><%=builddf%>&nbsp;</div></td>
   	</tr>
   	 <tr class='form_label'>
   		 <td>代垫电费(元)：</td> <td><div align="right" ><%=dddfdf%>&nbsp;</div></td>
   	 </tr>	
  
   	 <%}%>
 		
       
  </table>
	  
   </form>   
  </body>
</html>
    