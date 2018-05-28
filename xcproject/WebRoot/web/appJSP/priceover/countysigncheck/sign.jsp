<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.noki.mobi.common.Account" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
String loginName = account.getAccountName();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>区县签收</title>
    <style>
    
     #kong {
     weight:100%;
     text-align:center;
     margin-top: -12px;
   
     }
     .style7 {
     font-weight: bold;
     
     }
     body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	}
	.form    {width:130px}
    </style>
    </head>
    <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
    <script  language="javascript">
    var path = '<%=path%>';
    	function qianshou(){
		document.form1.action=path+"/servlet/CountySignCheckServlet?action=signed";
        document.form1.submit();
	}
      $(function(){
		$("#qianshou").click(function(){
			qianshou();
		});	
	});
    </script>
  <body class="body" style="overflow-x:hidden;">
  <form action="" name="form1" method="POST">
   	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
 	 	<tr>
 	 		<td colspan ='3' align="left" height="100px"><div id="qianshou" style="position:relative;width:70px;height:23px;cursor: pointer;left:10%;float:left;top:0px;right:-240px">
				<img alt="" src="<%=path %>/images/baocun.png" width="100%" height="100%" /><span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">签收</span></div>
			</td>
		</tr>
  		<tr>
    		<td width="12"><img src="<%=path %>/images/img_04.gif" width="12" height="37" /></td>
    		<td background="<%=path %>/images/img_05.gif">&nbsp;</td>
    		<td width="12"><img src="<%=path %>/images/img_06.gif" width="12" height="37" /></td>
  		</tr>
  		<tr>
    		<td height="300px" background="<%=path %>/images/img_10.gif">&nbsp;</td>
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
                    										<div id="kong"><span class="style7">提示信息！</span></div></td></tr>
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
    	   <td background="<%=path %>/images/img_13.gif">&nbsp;</td>
  	   </tr>
  	   <tr>
    	 <td><img src="<%=path %>/images/img_23.gif" width="12" height="19" /></td>
    	 <td background="<%=path %>/images/img_24.gif">&nbsp;</td>
    	 <td><img src="<%=path %>/images/img_25.gif" width="12" height="19" /></td>
  	   </tr>
  	   <tr>
  	   <td>
  	   <input name="loginid" type="hidden" value="<%=loginId %>"/>
  	   <input name="loginName" type="hidden" value="<%=loginName %>"/>
  	   </td>
  	   </tr>
    </table>
    </form>
  </body>
</html>
