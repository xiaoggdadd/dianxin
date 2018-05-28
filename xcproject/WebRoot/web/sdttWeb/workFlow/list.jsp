<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%
	    String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
		String accountId =account.getAccountId() ;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	
  <jsp:useBean id="bean" scope="page" class="com.noki.mobi.workflow.javabean.WorkFlowBean">
</jsp:useBean>
 <script language="javascript">
 
var path = '<%=path%>';
function banli(taskId,taskType){
	if(taskType=='DIANBIAO'){
		window.open("todo_dianbiao.jsp?id="+taskId, "newwindow", "height=650, width=1300, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
	}
	else if(taskType=='ELECTRICFEES'){
		window.open("todo_baozhang.jsp?id="+taskId, "newwindow", "height=650, width=1300, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
	}
}
function checkPage() {
	if (document.form1.pageCheck.checked) {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = true;
		}
	} else {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = false;
		}
	}
}
function plbl(n) {
	var msg="";
	if(n==0){
		msg="通过";
	}else{
		msg="驳回";
	}
	var i = 0;

	for ( var j = 0; j < document.form1.elements.length; j++) {
		if (document.form1.elements[j].checked) {
			i++;
		}
	}
	if (i > 0) {
		if (confirm("确定要"+msg+"这些任务么？")) {
			document.form1.action = path + "/servlet/workflow?action=plbl&n="+n;
			document.form1.submit();
		} else {
			return;
		}
	} else {
		alert("请选择要办理的任务");
		return;
	}
}

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
			<div class="content01" >
				<div class="tit01">待办任务</div>
				<div class="content01_1">
					<div class="tbtitle01"><b>待办任务查询统计一览</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
									<tr align="right">
										<td colspan="9" align="right">
										    <input name="button2" type="submit" onclick="plbl(0)" class="btn_c1" value="批量通过" />&nbsp;&nbsp;
											<input name="button2" type="submit" onclick="plbl(1)" class="btn_c1" value="批量驳回" />
										</td>
									</tr>
									<tr align="center">
									    <th width="10">序号</th>
										<th width="120">流程名称</th>
										<th width="100">当前节点</th>
										<th width="80">操作</th>
										<th width="10"><div>勾选<input type="checkbox" name="pageCheck" onclick="checkPage()" /></div></th>
									</tr>
						<%
		 	ArrayList list = new ArrayList();
        list = bean.getAllFlow(accountId);
        int num = 1;
        int countColum = ((Integer)list.get(0)).intValue();
        String flowName = "",flowId = "",flowDesc = "",actionname="",taskId="",taskType="",bohui="",id="";
        if(list!=null){
        for(int i = countColum;i<list.size()-1;i+=countColum){
        	id = (String)list.get(i+list.indexOf("ID"));
        	flowId = (String)list.get(i+list.indexOf("FLOWID"));
        	flowName = (String)list.get(i+list.indexOf("FLOWNAME"));
        	actionname = (String)list.get(i+list.indexOf("ACTIONNAME"));
        	taskId = (String)list.get(i+list.indexOf("TASK_ID"));
        	taskType = (String)list.get(i+list.indexOf("TASK_TYPE"));
        	bohui = (String)list.get(i+list.indexOf("BOHUI"));
        	if(actionname==null||bohui.equals("1")){
        		actionname="调整任务";
        	}
       			
       %>
      
									<!-- 数据加载  Start-->
									<tr align="center">
										<td width="10"><%=num++%></td>
										<td align="center"><%=flowName%></td>
										<td align="center"><%=actionname%></td>
										<td align="center">
										<a href="#" onclick="banli('<%=taskId%>','<%=taskType%>')"><img src="../images/accept.png" width="16" height="16"  title="办理" /></a>
										</td>
										<td>
											<input type="checkbox" name="itemSelected"
													value="<%=id%>" />
										</td>
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

