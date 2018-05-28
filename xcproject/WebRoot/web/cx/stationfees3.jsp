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
      String permissionRights="",zdfa="",zdla="";
      Double zdl=0.0,zdf=0.0;
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
   	 	<th>电表编号</th>
   	 	<th>电表名称</th>
   	 	<th>单价</th>			
   	 	<th>实际电量</th>
   	 	<th>电费实际金额</th>
   	 	<th>上次抄表数</th>
   	 	<th>本次抄表数</th>
   	 	<th>上次抄表时间</th>
   	 	<th>本次抄表时间</th>
   	 	<th>起始月份</th>
   	 	<th>结束月份</th>
   	 		   
 	</tr>		
  	 	<%	
  	 	if(xlxa!=null){
  	 	String whereStr="";
  	 	if(shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr=whereStr+" and zd.shi='"+shi+"'";
			
		}
  		if(yuefen != null && !yuefen.equals("") && !yuefen.equals(" ")){
			whereStr=whereStr+" and  to_char(am.endmonth,'yyyy-mm')='"+yuefen+"'";
		}
   	     JiZhanBean bean = new JiZhanBean();
       	 ArrayList fylist = new ArrayList();
         String jzsl1="",zcbs1="",money1="",jzname="",dianfei="",dbname="",dbid="",zdcode="",jzcode="",startmonth="",endmonth="",thisdegree="",lastdegree="",thisdatetime="",lastdatetime="";
       	 fylist = bean.getPageData23(whereStr,loginId,loginName,xlxa);
       	//int intnum=xh = pagesize*(curpage-1)+1;
       	if(fylist!=null)
 		{
 			int fycount = ((Integer)fylist.get(0)).intValue();
 			for( int k = fycount;k<fylist.size()-1;k+=fycount){

        			//num为序号，不同页中序号是连续的
        			jzcode = (String) fylist.get(k + fylist.indexOf("ZDCODE"));
        			jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
        			zcbs1 = (String) fylist.get(k + fylist.indexOf("BLHDL"));
        			money1 = (String) fylist.get(k+ fylist.indexOf("ACTUALPAY"));
        			dbname = (String) fylist.get(k + fylist.indexOf("DBNAME"));
        			dbid = (String) fylist.get(k+ fylist.indexOf("DBID"));
        			thisdegree = (String) fylist.get(k+ fylist.indexOf("THISDEGREE"));
        			lastdegree = (String) fylist.get(k + fylist.indexOf("LASTDEGREE"));
        			dianfei = (String) fylist.get(k+ fylist.indexOf("DIANFEI"));
        			thisdatetime = (String) fylist.get(k+ fylist.indexOf("THISDATETIME"));
        			lastdatetime = (String) fylist.get(k+ fylist.indexOf("LASTDATETIME"));
        			startmonth = (String) fylist.get(k+ fylist.indexOf("STARTMONTH"));
        			endmonth = (String) fylist.get(k+ fylist.indexOf("ENDMONTH"));
        			
        			if(zcbs1==null||zcbs1.equals("")||zcbs1.equals("null"))zcbs1="0";
                    DecimalFormat pay1=new DecimalFormat("0.00");
                    Double zz=Double.parseDouble(zcbs1);
                    zdl=zdl+zz;
                    zcbs1 = pay1.format(zz);
                    zdla = pay1.format(zdl);
                    
                    if(money1==null||money1.equals("")||money1.equals("null"))money1="0";
                    DecimalFormat pay3=new DecimalFormat("0.00");
                    Double ss=Double.parseDouble(money1);
                    zdf=zdf+ss;
                    money1 = pay3.format(ss);  
                    zdfa = pay3.format(zdf);     
        		
        			if (intnum % 2 == 0) {
        				color = "#FFFFFF";

        			} else {
        				color = "#DDDDDD";
        			}
        			  intnum++;
       %>  		
    <tr bgcolor="<%=color%>">  
        <td><div align="left" ><%=jzcode%></div></td> 	    
	   	<td><div align="left" ><%=jzname%></div></td>
	   	<td><div align="center" ><%=dbid%></div></td>
   	    <td><div align="center" ><%=dbname%></div></td>
   	    <td><div align="center" ><%=dianfei%></div></td>
       	<td><div align="right" ><%=zcbs1%></div></td>
   	    <td><div align="right" ><%=money1%></div></td>
   	    <td><div align="right" ><%=lastdegree%></div></td>
   	    <td><div align="right" ><%=thisdegree%></div></td>
   	    <td><div align="center" ><%=lastdatetime%></div></td>
   	    <td><div align="center" ><%=thisdatetime%></div></td>
   	    <td><div align="center" ><%=startmonth%></div></td>
   	    <td><div align="center" ><%=endmonth%></div></td>
   	   
   	</tr>
  
   	 <%
 	 }
 		}
  	 	}
 		
        if (intnum==0){
      	  System.out.println("QQQQ"+intnum);
           for(int i=0;i<15;i++){
            if(i%2==0){
  			    color="#FFFFFF" ;   
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
                     
          </tr>   
          <% }}     
 		
       %>
       <tr>
          	<td>合计</td>
          	<td>&nbsp;</td>
          	<td>&nbsp;</td>
          	<td>&nbsp;</td>
          	<td>&nbsp;</td>
          	<td align="right"><%=zdla %></td>
          	<td align="right"><%=zdfa %></td>
          </tr>
  </table>
	   	</td>		
   	</tr>
   </table>
   </form>   
  </body>
</html>
    