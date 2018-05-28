<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%
	String roleId = request.getParameter("roleId")!=null?request.getParameter("roleId"):"0";
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
String role_id = account.getRoleId();
%>
<html>
<head>
<title>
addAccount
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
	function roleRightShow(){
         	var role = document.form1.role.value;
                 document.form1.action=path+"/web/sys/rightRW.jsp?roleId="+role
                 document.form1.submit()
	}

        function saveAccount(){
          var role = document.form1.role.value;
                if(role=="0"){
                 	alert("请选中角色!");
                        return;
                }
          	document.form1.action=path+"/servlet/right?action=rwd";
                 document.form1.submit()
        }

        function changeR(){

			var dd = document.form1.itemR.length
			if(!document.form1.itemRr.checked){
				for(var k = 0;k<dd;k++){
					document.form1.itemR[k].checked=false

				}
			}else{
				for(var k = 0;k<dd;k++){
					document.form1.itemR[k].checked=true

				}
			}
	}
	function changeRW(){

			var dd = document.form1.itemRW.length

			if(!document.form1.itemRW1.checked){
				for(var k = 0;k<dd;k++){
					document.form1.itemRW[k].checked=false

				}
			}else{
				for(var k = 0;k<dd;k++){
					document.form1.itemRW[k].checked=true

				}
			}
	}
	function changeRWD(){

			var dd = document.form1.itemRWD.length

			if(!document.form1.itemRWD2.checked){
				for(var k = 0;k<dd;k++){
					document.form1.itemRWD[k].checked=false

				}
			}else{
				for(var k = 0;k<dd;k++){
					document.form1.itemRWD[k].checked=true

				}
			}
	}
	function changeWS(){
		var dd = document.form1.itemWS.length

			if(!document.form1.itemRWD3.checked){
				for(var k = 0;k<dd;k++){
					document.form1.itemWS[k].checked=false

				}
			}else{
				for(var k = 0;k<dd;k++){
					document.form1.itemWS[k].checked=true

				}
			}
	}

</script>
</head>
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean">
</jsp:useBean>
<jsp:useBean id="rightBean" scope="page" class="com.noki.mobi.sys.javabean.RightBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="../../images/css.css" type="text/css" rel="stylesheet">
<form action="" name="form1" method="POST">
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
  	<tr>
    <td width="10"><img src="../../images/img_04.gif" width="12" height="37" /></td>
    <td valign="top" background="../../images/img_05.gif"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="2"></td>
      </tr>
      <tr>
        <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="58%">&nbsp;</td>
            <td width="6%" bgcolor="#FFFFFF"><img src="../../images/b_13.gif" width="85" height="26" /></td>
            <td width="31%" background="../../images/b_14.gif"><span class="STYLE6"><a href="<%=path%>/web/sys/roleManage.jsp">｜返回｜</a></span></td>
            <td width="5%"><img src="../../images/b_15.gif" width="40" height="26" /></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
    <td width="12"><img src="../../images/img_06.gif" width="12" height="37" /></td>
  </tr>

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
							<td width="169" background="../../images/a_22.gif"><span class="style1"> 权限读写设置</span></td>
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
												<table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
			 <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4">　</td>
                    </tr>
      
    </table>
	<table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
           <tr>
              <td width="10%" height="23" bgcolor="C8E1FB"><div align="center">序号</div>
              </td>
              <td width="40%" height="23" bgcolor="C8E1FB"><div align="center">权限</div>
              </td>
              <td width="15%" height="23" bgcolor="C8E1FB"><div align="center">只读<input type="checkbox" name="itemRr" onClick="changeR()" /></div>
              </td>
              <td width="10%" height="23" bgcolor="C8E1FB"><div align="center">编辑<input type="checkbox" name="itemRW1" onClick="changeRW()" /></div>
              </td>
              <td width="10%" height="23" bgcolor="C8E1FB"><div align="center">审核<input type="checkbox" name="itemRWD2" onClick="changeRWD()" /></div>
              </td>
              <td width="15%" height="23" bgcolor="C8E1FB"><div align="center">编辑与审核<input type="checkbox" name="itemRWD3" onClick="changeWS()" /></div>
              </td>
           </tr>
       <%
           	ArrayList rightList = new ArrayList();
                rightList = rightBean.getRightByRoleId(roleId);
                int k = 1,j=1;
                int count = ((Integer)rightList.get(0)).intValue();
                String rw="",rightId="",rightname="";
                String color = "F3F3F3";
                for(int i = count;i<rightList.size()-1;i+=count){
                   rightname = (String)rightList.get(i+rightList.indexOf("RIGHTNAME"));
                   rightId = (String)rightList.get(i+rightList.indexOf("RIGHTID"));
                   rw = (String)rightList.get(i+rightList.indexOf("OPERATIONRIGHT"));

                   if(j%2==0){
											color = "#F2F9FF";
										}else{
											color = "FFFFFF";
										}
								           %>
           		<tr >
                      <td bgcolor="<%=color%>"><div align="center"><%=k++%></div>
                      </td>
                      <td bgcolor="<%=color%>"><div align="center"><%=rightname%></div>
                      </td>

                      <td bgcolor="<%=color%>">
                         <div align="center">
                            <input type="checkbox" value="<%=rightId%>" name="itemR" <%if(rw.equals("0")){%> checked="checked"<%}%>/>
                         </div>
                      </td>
                      <td bgcolor="<%=color%>">
                         <div align="center">
                            <input type="checkbox" value="<%=rightId%>" name="itemRW" <%if(rw.equals("1")){%> checked="checked"<%}%>/>
                         </div>
                      </td>
                      <td bgcolor="<%=color%>">
                         <div align="center">
                            <input type="checkbox" value="<%=rightId%>" name="itemRWD" <%if(rw.equals("2")){%> checked="checked"<%}%>/>
                         </div>
                      </td>
                      <td bgcolor="<%=color%>">
                         <div align="center">
                            <input type="checkbox" value="<%=rightId%>" name="itemWS" <%if(rw.equals("3")){%> checked="checked"<%}%>/>
                         </div>
                      </td>
                </tr>
           <%j++;}%>

      <tr>
        <td colspan="5">
          <div align="center">

          <input type="button" name="baocun" value="保存" id="id1" onclick="saveAccount()"  class="memo"  style="color:#014F8A"/>
          &nbsp;　&nbsp;
          <input type="reset" name="chongzhi" value="重置" id="id1" class="memo"  style="color:#014F8A"/>
          </div>
        </td>
      </tr>

      <%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
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
                      <td height="28" colspan="2" background="../../images/bg.jpg"><label>   <img src="../../images/ling.jpg" width="17" height="16" /> &#28201;&#39336;&#25552;&#31034; </label></td>
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
                                <td><div align="left"><p>1、读写权限分为四级：0-只读；1-可编辑；2-审核；3-编辑与审核<br />
                     <br/>
                      3、如果对一个权限同时选中了多个级别，按最右边的级别的保存</p></div></td>
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
</table>
<input type="hidden" name="role" value="<%=roleId%>"/>
</form>
</body>
</html>
<!--
<jsp:include flush="true" page="../common/rwdSub.jsp">
  <jsp:param name="pageUrl" value="rightManage"/>
  <jsp:param name="roleId" value="<%=role_id%>"/>
</jsp:include>
-->