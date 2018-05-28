<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.ptac.app.noadvicescb.city.*"%>
<%@ page import="java.text.*"%>

<%
    String path = request.getContextPath();
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
	String roleId = (String)session.getAttribute("accountRole");
	String sheng = (String)session.getAttribute("accountSheng");
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
	String jzproperty1 = request.getParameter("zdsx1")!=null?request.getParameter("zdsx1"):"0";
	String bzw = request.getParameter("bzw")!=null?request.getParameter("bzw"):"";
	     
	String whereStr="";
        
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
          
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<style>
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:20px

		}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
};
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
  <head>
   
  </head>
  
  <body>
    <% if(bzw != null && !bzw.equals("") && !bzw.equals("0")){ %>
    <table width="100%" border="0" cellspacing="1" cellpadding="1">       
           <tr class="relativeTag" >
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">序号</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">城市</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">无建议生产标个数</div></td>
         </tr>
       <%
         	if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and z.shi='"+shi+"'";
			}
			if (!jzproperty1.equals("0")) {
				whereStr=whereStr+" and z.property='" + jzproperty1 + "'";
			}

		NoAdviceScbCityBean bean=new NoAdviceScbCityBean();  
		ArrayList<NoAdviceScbCityBean> fylist = null;
		if(bzw!= null && !bzw.equals("") && !bzw.equals("0")){
			fylist = bean.getCity(whereStr,loginId);
			String xian = "",num = "0",hj = "";
			double hj1 = 0;
			int intnum=xh = pagesize*(curpage-1)+1;
			if(fylist!=null){
				for(NoAdviceScbCityBean bean1:fylist){
					
					xian = bean1.getXian();
					num = bean1.getNum();
					
					hj1 = hj1 + Double.valueOf(num);
					hj = hj1+"";

					String color=null;
		            if(intnum%2==0){
					    color="#DDDDDD";
					 }else{
					    color="#FFFFFF" ;
					}

       %>
       <tr bgcolor="<%=color%>" >
       		<td>
       			<div align="center" ><%=intnum++%></div>
       		</td>
       		<td>
       			<div align="center" ><%=xian%></div>
       		</td>
       		<td>
       			<div align="center" ><%=num%></div>
       		</td>
       </tr>
       <%}}%>
			<tr bgcolor="#DDDDDD"><td colspan="2" align="center">合计</td><td align="center"><%=hj%></td></tr>	
		<%}%>
  	 </table> 
  	 <%} %>
  </body>
</html>
