<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.Double" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.jizhan.JiZhanBean" %>
<%@ page import="com.noki.zwhd.model.DwBean"%>
<%@ page import="com.noki.zwhd.manage.SystemManage" %>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean" %>
<%
	String path = request.getContextPath();
    Account account=(Account)session.getAttribute("account");
    String accountname = account.getAccountName();
    String roleId = account.getRoleId();

   	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="../css/content.css" rel="stylesheet" type="text/css" />
 	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	
<script language="javascript">
	function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = "";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">";

		}
		else
		{
			trObject.style.display = "none";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">";
		}
}
	function baocun(){
	
         var oldpass = document.form1.oldPass.value;
         var newpass = document.form1.newPass.value;
         var confirmpass = document.form1.confirmPass.value;
         if(oldpass==""){
          alert("旧密码不能为空！");
          document.form1.oldPass.focus();
          return
         }
         if(newpass==""){
          alert("新密码不能为空！");
          document.form1.newPass.focus();
          return
         }else{
           if(newpass!=confirmpass){
              alert("新密码两次输入不同！请核对再输入！");
          	document.form1.newPass.focus();
          	return
           }
         }
         if(newpass.length<3||newpass.length >12){
                        		alert("密码长度为3-12");
                        		return;
                        }
         if(confirm("您将要修改您的登录密码！确认？")){
         	var path = '<%=path%>';
           document.form1.action=path+"/servlet/modifyPass";
           document.form1.submit();
         }
	}
	$(function(){
	
			
			$("#resetBtn").click(function(){
			
				$.each($("form input[type='password']"),function(){
				  $(this).val("");
			          });
// 			for(i=0;i<document.all.tags("input").length;i++){
// 			if(document.all.tags("input")[i].type=="text"){
// 			document.all.tags("input")[i].value="";}}
			
			});
			$("#baocun").click(function(){
				baocun();
			});
		});

</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
	
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
			
				<div class="tit01">登录密码修改</div>
			
				
				
				<div class="content01_1">
					<table width="100px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
				
							 
						<tr>
							<td align="right" width="100px">旧密码</td>
							<td width="60px">
								<input type="password" name="oldPass"  />
							</td>
						</tr>
						
						
						<tr>								
							<td align="right" width="100px">新密码</td>
							<td width="100px">
								<input type="password" name="newPass"   />
							</td>
									
						</tr>	
						
						<tr>								
							<td align="right" width="100px">确认新密码</td>
							<td width="100px">
								<input type="password" name="confirmPass"  />
							</td>
									
						</tr>											
						
						<tr>
							<td align="right" colspan="8" height="60px">
								<input  type="button" class="btn_c1" name="baocun" id="baocun" value="保存" />&nbsp;&nbsp;&nbsp; 

								<input  type="button" class="btn_c1" id="resetBtn" value="重置" />&nbsp; 

								

							</td>
						</tr>
					
					</table>
				</div>
			</div>
		</td>
		</tr>
		</table>
		<input type="hidden" name="zdid" value=""/>
		
	</form>	
	
</body>

</html>

