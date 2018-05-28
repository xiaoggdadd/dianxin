<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%
	String actionId = request.getParameter("actionId")!=null?request.getParameter("actionId"):"0";
        String path = request.getContextPath();
        System.out.println("actionId:"+actionId);

Account account = (Account)session.getAttribute("account");
String role_id = account.getRoleId();
System.out.println("account:"+account+" role_id:"+role_id);
%>
<html>
<head>
<title>
步骤角色设置
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
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	
 <script type="text/javascript">
 	var path = '<%=path%>';
 </script>
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

        function saveAccount(){
          	var action = document.form1.step.value;
                if(action=="0"){
                 	alert("请选中角色!");
                        return;
                }
          	document.form1.action=path+"/servlet/step?action=stepRole";
            document.form1.submit();
        }

	function clickNote(id,checked){
        var sub = id.substring(2,4);
        var headsub = id.substring(0,2);
          	for(var j = 0;j < document.form1.elements.length ; j++){
          		if (document.form1.elements[j].value==id){
          			if(checked){
          				for(var jj = 0;jj < document.form1.elements.length ; jj++){
          					if (document.form1.elements[jj].value==headsub+"00"||document.form1.elements[jj].value==id.substring(0,4)){
          						document.form1.elements[jj].checked=true;
										}
									}
								}else{// 没有被选中
									var para = 0;
									var pid = headsub+"00";
									if(id.length==4){
	                  for(var jj = 0;jj < document.form1.elements.length ; jj++){
	                  	if (document.form1.elements[jj].value.substring(0,2)==headsub&&document.form1.elements[jj].value!=headsub+"00"){
	                  		if(document.form1.elements[jj].checked){
	                  			for(var jjj = 0;jjj < document.form1.elements.length ; jjj++){
	                  				if (document.form1.elements[jjj].value==headsub+"00"){
	                  					document.form1.elements[jjj].checked=true;
	                  					para++;
														}
													}
	                      }
											}
										}
										if(para==0){
		                  	for(var jj = 0;jj < document.form1.elements.length ; jj++){
		                  		if (document.form1.elements[jj].value==headsub+"00"||document.form1.elements[jj].value==id.substring(0,4)){
		                  			document.form1.elements[jj].checked=false;
													}
												}
		                 }
									}else{//选择的是三级节点
										headsub = id.substring(0,4);
										for(var jj = 0;jj < document.form1.elements.length ; jj++){
	                  	if (document.form1.elements[jj].value.substring(0,4)==headsub&&document.form1.elements[jj].value!=headsub){
	                  		if(document.form1.elements[jj].checked){
	                  			
	                  					para++;
													
	                      }
											}
										}
										if(para==0){
		                  	document.getElementById(headsub).checked=false;
		                 }
		                var headsub = id.substring(0,2);
		                var fid = headsub+"00";
		                var cp = 0;
		                for(var jj = 0;jj < document.form1.elements.length ; jj++){
	                  	if (document.form1.elements[jj].value.substring(0,2)==headsub&&document.form1.elements[jj].value!=fid){
	                  		if(document.form1.elements[jj].checked){
	                  			
	                  					cp++;
													
	                      }
											}
											
										}
										if(cp==0){
												document.getElementById(fid).checked=false;
											}
									}
                  
							}
						}
         }//end for
	}//end function
	$(function(){
			
			$("#resetBtn").click(function(){
				$("input:checkbox").removeAttr("checked");
			});
			$("#baocun").click(function(){
				saveAccount();
			});
		});
</script>
</head>
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean">
</jsp:useBean>
<jsp:useBean id="stepBean" scope="page" class="com.noki.mobi.flow.javabean.StepBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="../../images/css.css" type="text/css" rel="stylesheet">
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
							<td colspan=1 width="600" background="<%=path%>/images/btt12.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分配角色</span></td>
							
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
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="80%" border="0" cellspacing="1" cellpadding="1" align="center">

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
			 <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4">　</td>
                    </tr>
        <tr>
      </tr>
    </table>
	<table width="80%" border="0" cellspacing="1" cellpadding="1" align="center">
       <%
           	ArrayList roleList = new ArrayList();
                roleList = roleBean.getAllRoles(actionId);
                int k = 1;
                int count = ((Integer)roleList.get(0)).intValue();
                String name="",roleId="",checked="";
                String color = "F3F3F3";
                for(int i = count;i<roleList.size()-1;i+=count){
                   name = (String)roleList.get(i+roleList.indexOf("NAME"));
                   roleId = (String)roleList.get(i+roleList.indexOf("ROLEID"));
                   checked = (String)roleList.get(i+roleList.indexOf("CHECKED"));
                   if(k%2==0){
											color = "#DDDDDD";
										}else{
											color = "FFFFFF";
										}
								           %>
           	<tr>
                      <td bgcolor="<%=color%>">
                      	<div align="left">
                      		&nbsp;&nbsp;                   		
                      		<input type="checkbox" id="<%=roleId%>" <%if(checked.equals("1")){%> checked="checked"<%}%> value="<%=roleId%>" onclick="clickNote('<%=roleId%>',checked)"  name="roleItem"/><%=name%>
                      	</div>
                      </td>

                </tr>
           <%k++;}%>

      <tr>
      <td>
       <div id="resetBtn"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 16px">
							<img src="<%=path%>/images/2chongzhi.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
						</div>
						<div id="baocun"
							style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right: 20px">
							<img alt=""
								src="<%=request.getContextPath()%>/images/baocun.png"
								width="100%" height="100%" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">保存</span>
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
      </td>
    <td background="../../images/img_13.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="../../images/img_23.gif" width="12" height="19" /></td>
    <td background="../../images/img_24.gif">&nbsp;</td>
    <td><img src="../../images/img_25.gif" width="12" height="19" /></td>
  </tr>
</table>
<input type="hidden" name="step" value="<%=actionId%>"/>
</form>
</body>
</html>

