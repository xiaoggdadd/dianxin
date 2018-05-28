<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.noki.mobi.common.Account,com.noki.mobi.sys.javabean.RoleBean" %>
<%@ page import="java.util.ArrayList" %>
<%
	String path = request.getContextPath();
	String roleId = request.getParameter("roleId");//获取角色id 
	String fenzuid = request.getParameter("fenzuid");
        String roleName = RoleBean.getRoleName(roleId);
        String memo =roleName.substring(roleName.indexOf("AA")+2);
        roleName=roleName.substring(0,roleName.indexOf("AA"));
        Account account = (Account)session.getAttribute("account");
String role_id = account.getRoleId();
%>
<html>
<head>
<title>
修改角色
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
	$(function(){
			$("#typeVal").click(function(){
				baocun();
			});
		
			$("#cancelBtn").click(function(){
				window.close();
			});
			$("#resetBtn").click(function(){
				$.each($("form input[type='text']"),function(){
					$(this).val("");
				})
			});
		});
</script>
</head>
<jsp:useBean id="bean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
<form action="" name="form1" method="POST">
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">


	<tr>
    
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan=1  background="<%=path%>/images/btt.bmp" height=40><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编辑角色</span></td>

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
                      <td width="35%" bgcolor="#DDDDDD" align="center">分组：</td>
                      <td width="65%">
                       <%
								      	ArrayList list = new ArrayList();
								        list = bean.getFenzu();
								        int countColum = ((Integer)list.get(0)).intValue();
								        String roleId2= "",roleName2 = "";
								      %> 
								       <select name="fenzu"  style="width:130"  class="form" >

						                                                   <%
						               		for(int i = countColum;i<list.size()-1;i+=countColum){
						                                  roleId2 = (String)list.get(i+list.indexOf("ID"));
						                                  roleName2 = (String)list.get(i+list.indexOf("NAME"));
						               %>
						               			<option value="<%=roleId2%>"><%=roleName2%></option>
						               <%
						               		}
						               %>
						
						            </select>
                       </td>
                    </tr>
                    <script>
											document.form1.fenzu.value='<%=fenzuid%>';
										</script>

       <tr>
      <td width="35%" bgcolor="#DDDDDD"><div align="center">原角色名称：
      </div></td>
      <td width="65%" >
        <%=roleName%>

      </td>
    </tr>
      <tr>
      <td height="22" bgcolor="#DDDDDD"><div align="center">新名称：
      </div></td>
      <td>
        <input type="text" name="roleName" value=""  class="form" width="65%"  style="width:130"/><span class="style1">&nbsp;*</span>

      </td>
    </tr>
     <tr>
      <td height="22" bgcolor="#DDDDDD"><div align="center">角色说明：
      </div></td>
      <td>
        <input type="text" name="memo" value="<%=memo%>"  class="form" width="65%"style="width:130"/>

      </td>
    </tr>
      <tr>
      <td></td>
      <td>
      
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
     
  <input type="hidden" name = "roleId" value="<%=roleId%>"/>
<div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:1px">
	           <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
          </div>
	  
        <div id="resetBtn"style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:3px">
	   <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
       <span style="font-size:12px;position:absolute;left:27px;top:5px;color:white">重置</span>
       </div>
         <div id="typeVal" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:6px">
	      <img src="<%=path %>/images/tijiao.png" width="100%" height="100%">
	       <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">提交</span>
        </div>
</form>
</body>
</html>


