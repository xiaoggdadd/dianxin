<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.Account" %>
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
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
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
   	    <th>站点编号</th>
   	 	<th>站点名称</th> 
   	 	<th>所在地区</th>
   	 	<th>站点类型</th>
   	 	<th>站点属性</th>
   	 	<th>单价</th>			
   	 	<th>是否标杆</th>
   	 	<th>用房类型</th>
   	 	<th>供电方式</th>
   	 
   	 		   
 	</tr>		
  	 	<%	
  	 	if(xlxa!=null){
  	 	String whereStr="";
  	 	if(shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr=whereStr+" and zd.shi='"+shi+"'";
			
		}
  	 	if(yuefen != null && !yuefen.equals("") && !yuefen.equals(" ")){
			whereStr=whereStr+"  and to_char(am.endmonth,'yyyy-mm')='"+yuefen+"'";
		}
   	     JiZhanBean bean = new JiZhanBean();
       	 ArrayList fylist = new ArrayList();
        String jzname="",zdcode="",szdq="",stationtype="",property="",danjia="",bgsign="",yflx="",gdfs="";
       	 fylist = bean.getPageData8(whereStr,loginId,loginName,xlxa);
       	//int intnum=xh = pagesize*(curpage-1)+1;
       	if(fylist!=null)
 		{
 			int fycount = ((Integer)fylist.get(0)).intValue();
 			for( int k = fycount;k<fylist.size()-1;k+=fycount){

        			//num为序号，不同页中序号是连续的
        			zdcode = (String) fylist.get(k + fylist.indexOf("ZDCODE"));
        			jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
        			szdq = (String) fylist.get(k + fylist.indexOf("SZDQ"));
        			stationtype = (String) fylist.get(k+ fylist.indexOf("STATIONTYPE"));
        			property = (String) fylist.get(k + fylist.indexOf("PROPERTY"));
        			danjia = (String) fylist.get(k+ fylist.indexOf("DIANFEI"));
        			bgsign = (String) fylist.get(k+ fylist.indexOf("BGSIGN"));
        			yflx = (String) fylist.get(k + fylist.indexOf("YFLX"));
        			gdfs = (String) fylist.get(k+ fylist.indexOf("GDFS"));
        			
        			
        		
        			if (intnum % 2 == 0) {
        				color = "#FFFFFF";

        			} else {
        				color = "#DDDDDD";
        			}
        			  intnum++;
       %>  		
    <tr bgcolor="<%=color%>">  
        <td><div align="center" ><%=zdcode%></div></td> 	    
	   	<td><div align="left" ><%=jzname%></div></td>
	   	<td><div align="center" ><%=szdq%></div></td>
   	    <td><div align="center" ><%=stationtype%></div></td>
   	    <td><div align="center" ><%=property%></div></td>
       	<td><div align="right" ><%=danjia%></div></td>
   	    <td><div align="center" ><%=bgsign%></div></td>
   	    <td><div align="center" ><%=yflx%></div></td>
   	    <td><div align="center" ><%=gdfs%></div></td>

   	</tr>
  
   	 <%
 	 }
 		}
  	 	}
 		
        if (intnum==0){
      	  System.out.println("QQQQ"+intnum);
           for(int i=0;i<15;i++){
            if(i%2==0){
  			    color="#FFFFFF";
            }else{
  			    color="#DDDDDD" ;
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
                               
          </tr>   
           
 		
        <% }}else if((intnum-1)%15!=0){
      	  for(int i=((intnum-1)%15);i<15;i++){
              if(i%2==0){
  			    color="#DDDDDD";
              }else{
  			    color="#FFFFFF" ;
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
             
            </tr>
          <% }}%>
  </table>
	   	</td>		
   	</tr>
   </table>
   </form>   
  </body>
</html>
    