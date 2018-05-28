<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%
	String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
String role_id = account.getRoleId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	
  <jsp:useBean id="bean" scope="page" class="com.noki.mobi.flow.javabean.StepBean">
</jsp:useBean>
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
function addStep(){
		window.open('addStep.jsp','','width=600,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
}
function bianji(actionid1){

 	 window.open('modifyStep.jsp?actionId='+actionid1,'','width=500,height=200,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
}

function delStep(actionid1){

			if(confirm("确定要删除这些角色?不可恢复!!!")){
                                document.form1.action=path+"/servlet/step?action=del&actionIdItem="+actionid1;
                                document.form1.submit();
                        }else{
                                return;
                        }
	
}
function bzsz(){
	var para = "";
	var i=0;
	for(var j = 0;j < document.form1.elements.length ; j++){
			if (document.form1.elements[j].checked){
				i++;
				para = document.form1.elements[j].value;
			}
		}

		if(i>1){
			alert("一次只能选择一个步骤设置角色！");
			return;
		}else if(i==0){
			alert("请先选择一个步骤");
			return;
		}

	document.form1.action=path+"/web/sdttWeb/Flow/roleManage.jsp?actionId="+para;
                                document.form1.submit();
}

function bzsz(para){

 window.open(path+'/web/sdttWeb/Flow/roleManage.jsp?actionId='+para,'','width=600,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
	// document.form1.action=path+"/web/sys/rightManage.jsp?roleId="+para;
                                document.form1.submit();
}
/* 
function daochu(){
 document.form1.action =  path + "/servlet/SysManger?bz=" +'jsgl';
 document.form1.submit();
 
 }
*/
$(function(){
		$("#add11").click(function(){
			addStep();
		  });
		  $("#shc").click(function(){
			delStep(roleid1,fenzuid1);
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
<table width="958" border="0" cellspacing="0" cellpadding="0" height="576">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">步骤管理</div>
				<div class="content01_1">
					<div class="tbtitle01"><b>步骤管理查询统计一览</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
									<tr align="right">
										<td colspan="6" align="right">
										    <input name="button2" type="submit" onclick="addStep()" class="btn_c1" value="新增" />&nbsp;&nbsp;
											<%--<input name="button2" type="button" class="btn_c1" onclick="daochu()" id="button2" value="导出Excel" />
										--%></td>
									</tr>
									<tr align="center">
									    <th width="10">序号</th>
										<th width="120">步骤名称</th>
										<th width="100">步骤说明</th>
										<th width="80">操作</th>
										<th width="80">步骤角色设置</th>
										<!--  <th width="80">选择</th> -->
									</tr>
						<%
		ArrayList list = new ArrayList();
        list = bean.getAllAction();
        int num = 1;
        int countColum = ((Integer)list.get(0)).intValue();
        String actionName = "",actionId = "",actionDesc = "";
        String color = "F3F3F3";
        if(list!=null){
        for(int i = countColum;i<list.size()-1;i+=countColum){
        	actionId = (String)list.get(i+list.indexOf("ACTIONID"));
        	actionName = (String)list.get(i+list.indexOf("ACTIONNAME"));
        	actionDesc = (String)list.get(i+list.indexOf("ACTIONDESC"));
           if(actionDesc==null)actionDesc="";
           if(num%2==0){
							color = "#DDDDDD";
						}else{
							color = "FFFFFF";
						}
       			
       %>
      
									<!-- 数据加载  Start-->
									<tr align="center">
										<td width="10"><%=num++%></td>
										<td align="left"><%=actionName%></td>
										<td align="left"><%=actionDesc%></td>
										<td align="center">
										<a href="#" onclick="bianji('<%=actionId%>')"><img src="../images/accept.png" width="16" height="16"  title="修改" /></a>
										<a href="#" onclick="delStep('<%=actionId%>')"><img src="../images/delete.png" width="16" height="16"  title="删除" /></a>
										</td>
										<td align="center"><a href="#" onclick="bzsz('<%=actionId%>')">步骤角色设置</a></td>
									</tr><% } %>
 
									<!-- 数据加载 End -->
								</table>
						<div class="space_h_10"></div>
				</div>
			</div>
		</td></tr>
		<% } %><!-- else {%>-->
		<!--  <tr align="center" >
			<td align="left" colspan="9">
			当前无数据。
			</td>
		</tr>-->
	
		</table>
		</form>
</body>
</html>

