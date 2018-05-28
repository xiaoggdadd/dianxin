<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.ptac.app.noadvicescb.province.*"%>
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
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">原因说明</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点总数</div></td>
         </tr>
       <%
       		
         	if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" AND Z.SHI='"+shi+"'";
			}
			if (!jzproperty1.equals("0")) {
				whereStr=whereStr+" AND Z.PROPERTY='" + jzproperty1 + "'";
			}

		NoAdvScbProReason bean1 = new NoAdvScbProReason();
		NoAdvScbProReason bean2 = new NoAdvScbProReason();
		NoAdvScbProReason bean3 = new NoAdvScbProReason();
		
		if(bzw!= null && !bzw.equals("") && !bzw.equals("0")){
			
			bean1 =(NoAdvScbProReason) bean1.getProReason1(whereStr,loginId);
			bean2 =(NoAdvScbProReason) bean2.getProReason2(whereStr,loginId);
			bean3 =(NoAdvScbProReason) bean3.getProReason3(whereStr,loginId);
       
			Double hj = 0.00;
			hj = Double.valueOf(bean1.getNum())+Double.valueOf(bean2.getNum())+Double.valueOf(bean3.getNum());
       %>
       <tr bgcolor="#FFFFFF" class="form_label">
       		<td align="center">1</td>
       		<td align="center">
       			<%=bean1.getReason()%>
       		</td>
       		<td align="center">
       			<div align="center" >
       				<a target="_Blank" href="<%=path+"/web/appJSP/noadvicescb/province/noAdvScbProReasonXq.jsp?shi="+shi+"&zdsx="+jzproperty1+"&bzwx=1"%>" ><%=bean1.getNum()%></a>
       			</div>
       		</td>
       </tr>
       <tr bgcolor="#DDDDDD" class="form_label">
       		<td align="center">2</td>
       		<td align="center">
       			<%=bean2.getReason()%>
       		</td>
       		<td align="center">
       			<div align="center" >
       				<a target="_Blank" href="<%=path+"/web/appJSP/noadvicescb/province/noAdvScbProReasonXq.jsp?shi="+shi+"&zdsx="+jzproperty1+"&bzwx=2"%>" ><%=bean2.getNum()%></a>
       			</div>
       		</td>
       </tr>
       <tr bgcolor="#FFFFFF" class="form_label">
       		<td align="center">3</td>
       		<td align="center">
       			<%=bean3.getReason()%>
       		</td>
       		<td align="center">
       			<div align="center" >
       				<a target="_Blank" href="<%=path+"/web/appJSP/noadvicescb/province/noAdvScbProReasonXq.jsp?shi="+shi+"&zdsx="+jzproperty1+"&bzwx=3"%>" ><%=bean3.getNum()%></a>
       			</div>
       		</td>
       </tr>
       <tr bgcolor="#DDDDDD"><td align="center">合计</td><td colspan="2" align="center"><%=hj%></td></tr>	
	  <%}%>
  	 </table> 
  	 <%} %>
  </body>
</html>
