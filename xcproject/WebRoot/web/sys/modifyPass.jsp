<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.noki.mobi.common.Account" %>
<%
	String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
String roleId = account.getRoleId();
%>
<html>
<head>
<title>
modifyPass
</title>
<style>
            .btn {
             BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7b9ebd 1px solid
            }
            .btn1_mouseout {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#B3D997); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn1_mouseover {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#CAE4B6); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn2 {padding: 2 4 0
            4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;}
            .btn3_mouseout {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mouseover {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mousedown
            {
             BORDER-RIGHT: #FFE400 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #FFE400 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #FFE400
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #FFE400 1px solid
            }
            .btn3_mouseup {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn_2k3 {
             BORDER-RIGHT: #002D96 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #002D96 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); BORDER-LEFT: #002D96
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #002D96 1px solid
            }
            .style1 {
	color: #FF9900;
	font-weight: bold;
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
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
 </style>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script language="javascript">
		function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

		}
		else
		{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
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
         if(newpass.length<3||newpass.length>12){
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
				$.each($("form input[type='text']"),function(){
				  $(this).val("");
			          })
			});
			$("#baocun").click(function(){
				baocun();
			});
		});
</script>
</head>
<body  class="body" style="overflow-x:hidden;">
<form action="" name="form1" method="POST">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  
  <tr>
    
    <td valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">

						
						<tr>
							<td colspan=1 width="600" background="<%=path%>/images/btt12.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改登录密码</span></td>
							
							<td width="380">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="4"></td>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" >
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
												<tr bgcolor="F9F9F9">
                      <td height="19" colspan="4">　</td>
                    </tr>
    <tr>
      <td width="22%" bgcolor="#DDDDDD"><div>旧密码：
      </div></td>
      <td width="78%">
        <input type="password" name="oldPass" value=""  class="form" /><span class="style1">&nbsp;*</span>
      </td>
    </tr>
    <tr>
      <td  bgcolor="#DDDDDD"><div>新密码：
      </div></td>
      <td>
        <input type="password" name="newPass" value="" maxlength="12" class="form" /><span class="style1">&nbsp;*</span>
      </td>
    </tr>
    <tr>
      <td height="21" bgcolor="#DDDDDD"><div>确认新密码：
      </div></td>
      <td>
        <input type="password" name="confirmPass" value="" maxlength="12" class="form" /><span class="style1">&nbsp;*</span>
      </td>
    </tr>
    <tr>
      <td height="29" colspan="2"><div align="center">
      		<div id="baocun" style="position: relative; width: 63px; height: 23px; cursor: pointer; float: left; left: 23%">
			<img alt=""src="<%=request.getContextPath()%>/images/baocun.png" width="100%" height="100%" />
			<span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">保存</span>
			</div>
             <div id="resetBtn" style="width: 60px; height: 23px; cursor: pointer; float: left; position: relative; left: 24%">
			 <img src="<%=path%>/images/2chongzhi.png" width="100%" height="100%">
			<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
			</div>

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
			</tr>
		</table>
    
</form>
</body>
</html>

