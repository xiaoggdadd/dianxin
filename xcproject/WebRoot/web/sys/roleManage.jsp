<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%
	String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
String role_id = account.getRoleId();
%>
<html>
<head>
<title>
roleManage
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
function addRole(){
		window.open('addRole.jsp','','width=600,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
}
function bianji(roleid,rolename){

 	 window.open('modifyRole.jsp?roleId='+roleid+'&roleName='+rolename,'','width=600,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
}
function delRole(){

	var i = 0;

		for(var j = 0;j < document.form1.elements.length ; j++){
			if (document.form1.elements[j].checked){
				i++;
			}
		}

		if(i>0){
			if(confirm("确定要删除这些角色么！不可恢复")){
                                document.form1.action=path+"/servlet/role?action=del";
                                document.form1.submit();
                        }else{
                                return;
                        }
		}else{
			alert("请选择要删除的角色");
			return;
		}
}
/*
function qxsz(){
	var para = "";
	var i=0;
	for(var j = 0;j < document.form1.elements.length ; j++){
			if (document.form1.elements[j].checked){
				i++;
				para = document.form1.elements[j].value;
			}
		}

		if(i>1){
			alert("一次只能选择一个角色设置权限！");
			return;
		}else if(i==0){
			alert("请先选择一个角色");
			return;
		}

	document.form1.action=path+"/web/sys/rightManage.jsp?roleId="+para;
                                document.form1.submit();
}
*/
function qxsz(para){
	document.form1.action=path+"/web/sys/rightManage.jsp?roleId="+para;
                                document.form1.submit();
}
function dxsz(){
	var para = "";
	var i=0;
	for(var j = 0;j < document.form1.elements.length ; j++){
			if (document.form1.elements[j].checked){
				i++;
				para = document.form1.elements[j].value;
			}
		}

		if(i>1){
			alert("一次只能选择一个角色设置权限！");
			return;
		}else if(i==0){
			alert("请先选择一个角色");
			return;
		}
	document.form1.action=path+"/web/sys/rightRW.jsp?roleId="+para;
                                document.form1.submit();
}
</script>
</head>
<jsp:useBean id="bean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="../../images/css.css" type="text/css" rel="stylesheet">
<form action="" name="form1" method="POST">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
 
  <tr>
    
    <td valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td colspan=1 width="600" background="<%=path%>/images/btt.bmp" height=40><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权限角色设置</span></td>
							
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
									<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" bgcolor="7C92B7">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1" >
<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
        <tr>

       <td align="right" colspan="4">
       	<%--
       		<input type="button" name="qxsz1" id="id1" value="权限设置" onclick="qxsz()" class="memo"  style="color:#014F8A"/>
					&nbsp;&nbsp;<!--
					<input type="button" name="dxsz1" id="id1" value="读写设置" onclick="dxsz()" class="memo"  style="color:#014F8A"/>
					&nbsp;&nbsp;-->
					<input type="button" name="onacc1" id="id1" value="新增" onclick="addRole()" class="memo"  style="color:#014F8A"/>
					&nbsp;&nbsp;
          <input type="button" name="del1" value="删除" id="id1" onclick="delRole()" class="memo"  style="color:#014F8A"/>
					--%>


      </td>
    </tr>
      	<div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>
        <tr>
         <td width="22%" height="23" bgcolor="#888888"><div align="center" class="bttcn">序号</div>
         </td>
         <td height="23" bgcolor="#888888"><div align="center" class="bttcn">角色名</div>
         </td>
       
         <td height="23" bgcolor="#888888"><div align="center" class="bttcn">操作</div>
         </td>
      </tr>
                                           <%
   	ArrayList list = new ArrayList();
        list = bean.getAllRole(1);
        int num = 1;
        int countColum = ((Integer)list.get(0)).intValue();
        String roleName = "",roleId = "",bj = "";
        String color = "F3F3F3";
        for(int i = countColum;i<list.size()-1;i+=countColum){
           roleId = (String)list.get(i+list.indexOf("ROLEID"));
           roleName = (String)list.get(i+list.indexOf("NAME"));
           bj = (String)list.get(i+list.indexOf("FIXED"));
           if(num%2==0){
							color = "#DDDDDD";
						}else{
							color = "FFFFFF";
						}
   %>
   	<tr>
              <td bgcolor="<%=color%>"><div align="center"><%=num++%></div>
              </td>
              <td bgcolor="<%=color%>"><div align="center"><%=roleName%></div>
              </td>
           
              <td bgcolor="<%=color%>"><div align="center"><a href="#" onclick="qxsz('<%=roleId%>')">权限设置</a></div>
              </td>
   	</tr>
   <%}%>

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>

      </table>
											</td>
										</tr>
										<%--
										<tr bgcolor="#FFFFFF">
											
											<td align="right">
												<div id="parent" style="display:inline" align="right">
                          <div style="width:300px;display:inline;" align="right"><hr></div>
                      </div>
											</td>
											
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<div align="right">
												<input type="button" name="qxsz1" id="id1" value="权限设置" onclick="qxsz()" class="memo"  style="color:#014F8A"/>
					
							        </div>
											</td>
										</tr>
										--%>
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

