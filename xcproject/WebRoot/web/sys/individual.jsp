<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.noki.mobi.sys.javabean.AccountFormBean" %>
<%@ page import="com.noki.mobi.common.Account" %>
<%

        Account account = new Account();
        account = (Account)session.getAttribute("account");
		AccountFormBean bean = new AccountFormBean();
		String accountId = account.getAccountId();
		bean = bean.getAccountInfo(accountId);
                String path = request.getContextPath();
                String roleId = account.getRoleId();
%>
<html>
<head>
<title>
individual
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
</head>

<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script language="javascript">
var path = '<%=path%>';
function modifyIndividual(){
           if(checkNotnull(document.form1.name,"姓名")){
            	document.form1.action=path+"/servlet/individual"
                document.form1.submit()
           }

	}
	 function vName(){
         	var accName = document.form1.accountName.value;
         	var accountid = '<%=accountId%>'
               window.open('accountName.jsp?accountId='+accountid+'&accountName='+accName,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
        	 var accountid = '<%=accountId%>'
           window.open('accountMobile.jsp?accountId='+accountid+'&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
         $(function(){
			
			$("#resetBtn").click(function(){
				$.each($("form input[type='text']"),function(){
					$(this).val("");
				})
			});
			$("#baocun").click(function(){
				modifyIndividual();
			});
		});
</script>
<body  class="body" style="overflow-x:hidden;">
<form action="" method="POST" name="form1">

          <table  width="100%"  border="0" cellspacing="0" cellpadding="0">


	<tr>
   
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						
						<tr>
							<td colspan=1 width="600" background="<%=path%>/images/btt12.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个人信息修改</span></td>
							
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
												<table width="100%" border="0" cellspacing="1" cellpadding="1">
													<tr bgcolor="F9F9F9">
                      <td height="19" colspan="4">　</td>
                    </tr>
      <tr>
                                                  <tr>
         <td height="19" bgcolor="#DDDDDD" width="14%"><div align="center">账　号：</div></td>
         <td width="36%">
         	
         	<%=bean.getAccountName()%>
         <%--
         	<input type="text" name="accountName" value="<%=bean.getAccountName()%>"  onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" class="form" /><span class="style1">&nbsp;*</span>
           <input type="button" name="validateName0" value="是否被用" onclick="vName()"  id="id1"/>
          --%>
         </td>
         <td width="13%" height="19" bgcolor="#DDDDDD"><div align="center">姓　名：</div></td>
         <td width="37%"><input type="text" name="name" value="<%=bean.getName()%>"  class="form" / style="width:130"><span class="style1">&nbsp;*</span>
         </td>
      </tr>

      <tr>
         <td height="19" bgcolor="#DDDDDD"><div align="center">性　别：</div></td>
         <td>
            <select name="sex"  style="width:130" class="form" >
               <option value="1">男</option>
               <option value="0" <% if(bean.getSex().equals("0")){ %> selected="selected"<%}%>>女</option>
            </select>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div align="center">手　机：</div></td>
         <td><input type="text" name="mobile" value="<%=bean.getMobile()%>" maxlength="11" class="form" style="width:130"/>

         </td>
      </tr>

      <tr>
         <td height="19" bgcolor="#DDDDDD"><div align="center">邮　箱：</div></td>
         <td><input type="text" name="email" value="<%=bean.getEmail()%>"  class="form" style="width:130"/>
           </td>
        <td height="19" bgcolor="#DDDDDD"><div align="center">座　机：</div>
         </td>
         <td><input type="text" name="tel" value="<%=bean.getTel()%>"  class="form" style="width:130"/>
         </td>
      </tr>
       <tr>
         <td height="19" bgcolor="#DDDDDD"><div align="center">邮　编：</div>
         </td>
         <td><input type="text" name="zip" value="<%=bean.getZip()%>"  class="form" style="width:130"/>
         </td>

      </tr>
       <tr>
         <td height="19" bgcolor="#DDDDDD"><div align="center">地　址：</div></td>
         <td colspan="2"><input size="60" type="text" name="address" value="<%=bean.getAddress()%>"  class="form" />
         </td>

      </tr>
      <tr bgcolor="F9F9F9">
                <td height="27" colspan="4" >
                                  <div id="resetBtn" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 2px">
							               <img src="<%=path%>/images/2chongzhi.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
						</div>
						<div id="baocun"
							style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right: 5px">
							<img alt=""
								src="<%=request.getContextPath()%>/images/baocun.png"
								width="100%" height="100%" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">保存</span>
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

