<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.net.URLEncoder" %>
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
    <% 
    	if(bzw != null && !bzw.equals("") && !bzw.equals("0")){       		
         	if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and z.shi='"+shi+"'";
			}
			if (!jzproperty1.equals("0")) {
				whereStr=whereStr+" and z.property='" + jzproperty1 + "'";
			}

		NoAdvScbCityReason bean1=new NoAdvScbCityReason();
		NoAdvScbCityReason bean2=new NoAdvScbCityReason();
		NoAdvScbCityReason bean3=new NoAdvScbCityReason();
		
		
		bean1 = bean1.getReason1(whereStr,loginId);
		bean2 = bean2.getReason2(whereStr,loginId);
		bean3 = bean3.getReason3(whereStr,loginId);
		
		String reason1 = "",reason2 = "",reason3 = "";
		String num1 = "",num2 = "",num3 = "";
		
		reason1 = bean1.getReason();
		reason2 = bean2.getReason();
		reason3 = bean3.getReason();
		num1 = bean1.getNum();
		num2 = bean2.getNum();
		num3 = bean3.getNum();
		
		String hStr = "",str = "";
		hStr = num1 + ";" + num2 + ";" + num3 + ";";
        str = reason1 + ";" + reason2 + ";" + reason3 + ";";
	%>
    	<div>
			<img src="<%=path%>/servlet/PieChartServlet?cityStr=<%=str%>&dataStr=<%=hStr%>">
		</div>
      <%}%>
  </body>
</html>
