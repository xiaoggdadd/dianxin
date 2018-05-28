<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.noki.mobi.common.Account" %>

<%	
	String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    String roleId = (String)session.getAttribute("accountRole");
    String permissionRights="";
%>

<html>
<head>
<title>

</title>
<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
.selected_font{
		width:90px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px ##888888 solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.bttcn{color:black;font-weight:bold;}
.form    {width:130px}

#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script language="javascript">
var path = '<%=path%>';
	function qianshou(){
		document.form1.action=path+"/web/jzcbnewfunction/citySend.jsp?bzw=2";
        document.form1.submit();
	}

    $(function(){
		$("#qianshou").click(function(){
			qianshou();
		});	
	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0804");

%>

<body  class="body" style="overflow-x:hidden;">

	<form action="" name="form1" method="POST">

	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
 	 	<tr>
 	 		<td align="left" height="100px"><div id="qianshou" style="position:relative;width:70px;height:23px;cursor: pointer;left:25%;float:left;top:0px;right:-240px">
				<img alt="" src="<%=path %>/images/baocun.png" width="100%" height="100%" /><span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">签收</span></div>
			</td>
		</tr>
  		<tr>
    		<td width="10"><img src="<%=path %>/images/img_04.gif" width="12" height="37" /></td>
    		<td background="<%=path %>/images/img_05.gif">&nbsp;</td>
    		<td width="12"><img src="<%=path %>/images/img_06.gif" width="12" height="37" /></td>
  		</tr>
  		<tr>
    		<td width="10" height="300px" background="<%=path %>/images/img_10.gif">&nbsp;</td>
    		<td valign="top" ><br/>
     		 <br/>    
       			<table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
     			 <tr>
        			<td colspan="3">
        				<table width="100%"  border="0" cellspacing="0" cellpadding="0">
          					<tr>
            	 				<td>
            						<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" bgcolor="7C92B7">
              							<tr>
                							<td height="49" bgcolor="#FFFFFF">
                								<table width="100%" border="0" cellspacing="1" cellpadding="1">
                 									<tr>
                    									<td height="29" colspan="2">
                    										<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    										<tr><td height="29" background="<%=path %>/images/bei.gif"><img src="<%=path %>/web/images/v.gif" width="8" height="9" />　　　　　　　　　　　　　　　　　　　　　　　
                    										　　<span class="style7">提示信息！</span></td></tr>
                        									</table>
                    									</td>
                  									</tr>
                 			 						<tr>
                   						 				<td width="42%" height="87" bgcolor="#FFFFFF"><div align="center"><img src="<%=path %>/images/2.gif" width="133" height="134" /></div></td>
                    					 				<td width="58%" bgcolor="#FFFFFF"><p>请签收需要整改的站点</p></td>
                  									</tr>
                  									<tr><td colspan="2" bgcolor="F2F9FF">&nbsp;</td></tr>
                							 	</table>
                							</td>
              							</tr>
            						</table>
            	 				</td>
          					</tr>
        				</table>
        			</td>
      			</tr>
    		</table>  
    	  </td>
    	   <td background="../images/img_13.gif">&nbsp;</td>
  	   </tr>
  	   <tr>
    	 <td><img src="../images/img_23.gif" width="12" height="19" /></td>
    	 <td background="../images/img_24.gif">&nbsp;</td>
    	 <td><img src="../images/img_25.gif" width="12" height="19" /></td>
  	   </tr>
    </table>	
	</form>

</body>

</html>

