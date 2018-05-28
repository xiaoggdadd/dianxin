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
  <jsp:useBean id="bean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean">
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
function addRole(){
		window.open('addRoles.jsp','','width=600,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
}
function bianji(roleid,rolename){

 	 window.open('modifyRoles.jsp?roleId='+roleid+'&fenzuid='+rolename,'','width=500,height=200,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
}
function shouquan(roleid,rolename){

	 window.open('shouquanRoles.jsp?roleId='+roleid+'&roleName='+rolename,'','width=600,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
}
function delRole(roleid1,fenzuid1){

			if(confirm("确定要删除这些角色?不可恢复!!!")){
                                document.form1.action=path+"/servlet/role?action=del&roleIdItem="+roleid1;
                                document.form1.submit();
                        }else{
                                return;
                        }
	
}
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
function qxsz(para){

 window.open(path+'/web/sys/rightManage.jsp?roleId='+para,'','width=600,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
	// document.form1.action=path+"/web/sys/rightManage.jsp?roleId="+para;
                                document.form1.submit();
}
$(function(){
		$("#add11").click(function(){
			addRole();
		  });
		  $("#shc").click(function(){
			delRole(roleid1,fenzuid1);
		  });
	});
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<body>
<ul class="tab">
  <li class="first"></li>
  <li class="cur"><a href="accountList.jsp">用户管理</a></li>
  <li><a href="roleManager.jsp">角色管理</a></li>
  <li><a href="roleManager.html">权限管理</a></li>
  <li class="end"></li>
</ul>
<form action="" name="form1" method="post">
<table width="958" border="0" cellspacing="0" cellpadding="0" height="576">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">角色管理</div>
				<div class="content01_1">
					<div class="tbtitle01"><b>角色管理查询统计一览</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
									<tr align="right">
										<td colspan="6" align="right">
										    <input name="button2" type="submit" onclick="addRole()" class="btn_c1" value="新增" />&nbsp;&nbsp;
											<input name="button2" type="submit" class="btn_c1" id="button2" value="导出Excel" />
										</td>
									</tr>
									<tr align="center">
									    <th width="10">序号</th>
										<th width="120">角色名称</th>
										<th width="100">角色说明</th>
										<th width="80">分组</th>
										<th width="80">操作</th>
										<th width="80">权限设置</th>
										<!--  <th width="80">选择</th> -->
									</tr>
						<%
		 	ArrayList list = new ArrayList();
        list = bean.getAllRole(0);
        int num = 1;
        int countColum = ((Integer)list.get(0)).intValue();
        String roleName = "",roleId = "",bj = "",memo="",fenzu="",fenzuid="";
        String color = "F3F3F3";
        if(list!=null){
        for(int i = countColum;i<list.size()-1;i+=countColum){
           roleId = (String)list.get(i+list.indexOf("ROLEID"));
           roleName = (String)list.get(i+list.indexOf("NAME"));
           memo = (String)list.get(i+list.indexOf("MEMO"));
           bj = (String)list.get(i+list.indexOf("FIXED"));
           fenzu = (String)list.get(i+list.indexOf("FZNAME"));
           fenzuid = (String)list.get(i+list.indexOf("FENZU"));
           if(memo==null)memo="";
           if(num%2==0){
							color = "#DDDDDD";
						}else{
							color = "FFFFFF";
						}
       			
       %>
      
									<!-- 数据加载  Start-->
									<tr align="center">
										<td width="10"><%=num++%></td>
										<td align="left"><%=roleName%></td>
										<td align="left"><%=memo%></td>
										<td align="center"><%=fenzu%></td>
										<td align="center">
										<a href="#" onclick="bianji('<%=roleId%>','<%=fenzuid%>')"><img src="../images/accept.png" width="16" height="16"  title="修改" /></a>
										<a href="#" onclick="delRole('<%=roleId%>','<%=fenzuid%>')"><img src="../images/delete.png" width="16" height="16"  title="删除" /></a>
										</td>
										<td align="center"><a href="#" onclick="qxsz('<%=roleId%>')">权限设置</a></td>
									<!--  	<td align="center"><input type="checkbox" name="roleIdItem" value="<%=roleId%>" /></td>-->
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

