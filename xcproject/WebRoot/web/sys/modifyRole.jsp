<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.noki.mobi.common.Account" %>
<%
	String path = request.getContextPath();
	String roleId = request.getParameter("roleId");
        String roleName = request.getParameter("roleName");
        byte[] tmp = null;

	try{tmp = roleName.getBytes("iso-8859-1");}catch(Exception e){}
	roleName = new String(tmp,"gb2312");
        Account account = (Account)session.getAttribute("account");
String role_id = account.getRoleId();
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
<script language="javascript">
var path = '<%=path%>';
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
         var roleName = document.form1.roleName.value;
        if(roleName==""){
         	alert("角色新名称不能为空！");
                 document.form1.roleName.focus();
                 return
        }
        if(roleName.length > 10){
        	alert("角色名称长度不大于10个汉字的长度！");
                 document.form1.roleName.focus();
                 return
        }
         if(confirm("你将要修改角色名称？确定？")){
           document.form1.action=path+"/servlet/role?action=modify";
           document.form1.submit();
         }
	}
</script>
</head>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="../../images/css.css" type="text/css" rel=stylesheet>
<form action="" name="form1" method="POST">
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">


	<tr>
    <td width="10" height="500" background="../../images/img_10.gif">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td width="6" background="../../images/a_22.gif"><img src="../../images/a_21.gif" width="44" height="32" /></td>
							<td width="169" background="../../images/a_22.gif"><span class="style1"> 编辑角色</span></td>
							<td width="185"><img src="../../images/a_23.gif" width="185" height="32" /></td>
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
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" bgcolor="7C92B7">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="80%" border="0" cellspacing="1" cellpadding="1" align="center">

       <tr>
      <td width="21%" bgcolor="F2F9FF"><div align="center">原角色名称：
      </div></td>
      <td width="79%">
        <%=roleName%>

      </td>
    </tr>
      <tr>
      <td height="22" bgcolor="F2F9FF"><div align="center">新名称：
      </div></td>
      <td>
        <input type="text" name="roleName" value=""  class="form" /><span class="style1">&nbsp;*</span>

      </td>
    </tr>
      <tr>
        <td height="31" colspan="2"><div align="center"><input type="button" name="bcun1" id="id1" value="保存" onclick="baocun()" id="id1"/>
                &nbsp;&nbsp;
                <input type="reset" name="reset0" id="id1" value="重置" id="id1"/></div></td>
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
      <br />
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="4"></td>
          <td><table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="7C92B7">
              <tr>
                <td height="49" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr bgcolor="F9F9F9">
                      <td height="28" colspan="2" background="../../images/bg.jpg"><label> 　　<img src="../../images/ling.jpg" width="17" height="16" />　温馨提示</label></td>
                    </tr>
                    <tr>
                      <td height="51" colspan="2"><div align="center"></div>
                          <div align="center"> </div>
                          <div align="center"> </div>
                          <div align="center" class="style7"></div>
                          <div align="center" class="style7"> </div>
                          <div align="center"></div>
                          <div align="center">
                            <table width="85%"  border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td><div align="left">1、带有*号的是必填项<br />

                              </tr>
                            </table>
                        </div></td>
                    </tr>
                </table></td>
              </tr>
          </table></td>
        </tr>
      </table></td>
    <td background="../../images/img_13.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="../../images/img_23.gif" width="12" height="19" /></td>
    <td background="../../images/img_24.gif">&nbsp;</td>
    <td><img src="../../images/img_25.gif" width="12" height="19" /></td>
  </tr>
  <input type="hidden" name = "roleId" value="<%=roleId%>"/>
</table>
</form>
</body>
</html>
<jsp:include flush="true" page="../common/rwdSub.jsp">
  <jsp:param name="pageUrl" value="roleManage"/>
  <jsp:param name="roleId" value="<%=role_id%>"/>
</jsp:include>
