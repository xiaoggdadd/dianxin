<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.*"%>
<%@ page
	import="com.noki.mobi.common.Account,com.ptac.app.version.Version"%>
<%@ page
	import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean"%>
<%@ page import="com.noki.mobi.common.Right"%>
<%
	String path = request.getContextPath();
	String version = new Version().getVersion();
	Account account = (Account) session.getAttribute("account");
	String accountId = account.getName();
	String rolename = account.getRoleName();
	String loginId = account.getAccountId();
	haodianliangBean bean = new haodianliangBean();
	String flag = "1";
	String number = account.getNumber();
	ArrayList<Right> fatherRights = new Right().selectWebRightByAccountId_father(account
			.getAccountId());
	String fatherRightIds = "";
	for(int i=0;i<fatherRights.size();i++){
		fatherRightIds += fatherRights.get(i).getRightId()+",";
	}
	fatherRightIds = fatherRightIds.substring(0, fatherRightIds.length()-1);
	
	ArrayList<Right> userSonRights  = new Right().selectWebRightByAccountId_byUser_son(account.getAccountId());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>站点账户管理平台 v<%=version%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<script type="text/javascript" src="javascript/jquery-1.4.2.js"></script>
<script type="text/javascript">
	var path = '<%=path%>';
	$(function(){
		var body = document.body;
		var ifram_hight = window.screen.height;
		$("#index_content").css("height",ifram_hight+"px");
	});
	
	var isShowMenuTool = false;
	function menuToolClick(){
		if(isShowMenuTool){
			$("#menuTool").show();
		}else{
			$("#menuTool").hide();
		}
		
		isShowMenuTool = !isShowMenuTool;
		
	}
	
	var isShowUserMenuTool = false;
	
	function changeUserMenuTool(){
		if(isShowUserMenuTool){
			$("#userMenuTool").show();
		}else{
			$("#userMenuTool").hide();
		}
		isShowUserMenuTool = !isShowUserMenuTool;
	}

	function chengMenuContentWindow(fatherMenuId){
		<%
			if(!fatherRightIds.equals("")){
		%>
		var fids = "<%=fatherRightIds%>";
		var fidList= fids.split(",");
		for(var index_fid=0;index_fid<fidList.length;index_fid++){
			var fid = fidList[index_fid];
			$("#c_"+fid).hide();
		}
		$("#c_"+fatherMenuId).show();
		<%
			}
		%>
	}
	
	var left = 0;
	var maxLeft = <%=fatherRights.size()%>*90-90;
	function moveLeftToolMenuWindow(){
		if(left!=0){
			left+=90;
			$("#menuTool_content").css("left",left+"px");
		}
		
	}
	function moveRightToolMenuWindow(){
		if(left!=-maxLeft){
			left-=90;
			$("#menuTool_content").css("left",left+"px");
		}
	}
	
	function employeeMap_bt_click(id){
		$("td[name=t_menuid] table").css("background","");
		$("td[name=t_menuid] label").css("color","#ffffff");
		$("#t_"+id+" table").css("background","url('../images/image 46.png')");
		$("#t_"+id+" label").css("color","#1a64af");
	}
	
	function menuClick(name,icon,cpath){
		var mypaht = '<%=path%>/'+cpath;
		$("#index_content").attr("src",mypaht);
		
		$("#daohang_menu img").attr("src","../images/menubuttons/"+icon+".png");
		
		$("#daohang_menu label").html(name);
	}
	
	function tuichuxitong(){
		if(confirm("确定要退出系统么？")){
			parent.location=path+"/tuichu.jsp";
		}
		else{
	    	return false;
		}
	}
</script>
</head>

<body
	style="overflow:-Scroll;overflow-y:hidden;position:absolute; width:100%; left: 0px; top: 0px; padding:0px;background-color: #d6e3f2;border-bottom-color: #99bbe8;border-bottom-style: solid;border-bottom-width: 0px;border-left-color: #99bbe8;border-left-style: solid;border-left-width: 0px;border-top-color: #99bbe8;border-top-style: solid;border-top-width: 0px;border-right-color: #99bbe8;border-right-style: solid;border-right-width: 0px;margin: 0px;">
	<table
		style="width: 100%;height: 100%;left: 0px;top: 0px;padding: 0px;margin: 0px;padding: 0px"
		cellpadding="0" cellspacing="0" align="left">
		<tr>
			<td
				style="width: 230px;height: 60px;background: url('../images/image 81.png');">
				<table style="width: 230px;height: 50px;" cellpadding="0"
					cellspacing="0">
					<tr>
						<td style="width: 230px;height: 50px;"><img alt=""
							src="../images/sys_logo.png" width="230px" height="50px" /></td>
					</tr>
				</table></td>
			<td
				style="width:100%;height: 80px;background: url('../images/image 126.png');">
				<table style="width: 100%;height: 80px;" cellpadding="0"
					cellspacing="0">
					<tr>
						<td style="width: 100%;height: 27px;">
							<table style="width: 100%;height: 27px;" cellpadding="0"
								cellspacing="0">
								<tr>
									<%
										for(int i=0;i<userSonRights.size();i++){
											Right userSonRight = userSonRights.get(i);
											String sid = userSonRight.getRightId();
											String sname = userSonRight.getName();
											String sicon = userSonRight.getIcon();
											String spath = userSonRight.getUrl();
									%>
										<td style="width: 84px;height: 27px;padding-right: 10px;" id="t_<%=sid%>" name="t_menuid" onclick="menuClick('<%=sname%>','<%=sicon%>','<%=spath%>');" onmousemove="employeeMap_bt_click('<%=sid%>');">
											<table
												style="width: 84px;height: 27px;<%=i==0?"background:url('../images/image 46.png')":"" %>"
												cellpadding="0" cellspacing="0" id="employeeMap_bt">
												<tr>
													<td align="center">
														<img
														style="width: 24px ;height: 21px" alt=""
														src="../images/menubuttons/<%=sicon %>.png" title="<%=sname %>">
													</td>
													<td align="center"><label
														style="<%=i==0?"color:#1a64af":"color:#ffffff" %>;font-size: 12px;font-family: 微软雅黑">
															<%=sname %></label>
													</td>
												</tr>
											</table>
										</td>
									<%	
										}
									%>
									<td align="center" style="padding-right: 10px;">
										<img alt="" src="../images/set1.png" onmousedown="changeUserMenuTool();"/>
									</td>
									<td style="width: 100%"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td style="width: 100%;height: 21px">
							<div style="float: left;width: 100%;height: 21px;">
								<div style="padding-right:10px;width: 97px;height: 21px;float: left;padding: 0px;margin: 0px;">
									<img
										style="width: 97px;height: 21px" alt=""
										src="../images/image 110.png" onmousedown="menuToolClick();"/>
								</div>
								<% 
									if(userSonRights.size()!=0){
								%>
								<div style="padding-right:10px;width: 24px;height: 21px;float: left;padding: 0px;margin: 0px;">
									<img id="gndh_img"
													style="width: 24px;height: 21px" alt=""
													src="../images/menubuttons/<%=userSonRights.get(0).getIcon()%>.png">
								</div>
								<div style="padding-right:10px;width: 100px;height: 21px;float: left;padding: 0px;margin: 0px;">
									<label id="gndh_label" style="width:100px;height:21px;color: #1a64af;font-size: 12px;font-family: 微软雅黑">
														<%=userSonRights.get(0).getName() %>
													</label>
								</div>
								<%
									} 
								%>
								<div style="padding-right:10px;float: left;width: 100px;height: 21px;position: absolute;right: 0px;padding: 0px;margin: 0px;">
									<img src="../images/renyuan.png" alt="" style="width: 15px;height: 17px;"/>
									<label id="l_username" style="width:100px;height:21px;color: #1a64af;font-size: 12px;font-family: 微软雅黑">
														<%=accountId%>
									</label>
									<img src="../images/tuichu.png" alt="" style="width: 16px;height: 17px;" onclick="tuichuxitong();"/>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr style="width: 100%">
			<td colspan="2" style="width: 100%;background-color: #FFFFFF;" align="left">
			<iframe
					frameborder="0"
					style="width: 100%;height: 400px;left: 0px;top: 0px;"
					scrolling="no" src="<%=userSonRights.size()==0?"":path+"/"+userSonRights.get(0).getUrl() %>" id="index_content" name="content"></iframe>
			</td>
		</tr>
	</table>
	
	<div id="menuTool"
		style="display:none;background-color:#3f88d7;float:left;position: absolute; width: 200px; height:599px;top:60px; left:1px; bottom:10px; padding:1px;overflow: hidden;border: 1px solid #3f88d7;">
		<table style="width: 100%;height: 100%;" cellpadding="0"
					cellspacing="0">
			<tr>
				<td style="width: 100%;height: 26px;" align="center">
					<label style="color:#ffffff;font-size: 12px;font-family: 微软雅黑">功能导航</label>
				</td>
			</tr>
			<tr>
				<td style="width: 100%;height: 100%;background-color: #FFFFFF;" align="center">
					<iframe src="menuTool.jsp" style="width: 100%;height: 100%" name="left" frameborder="0" noresize scrolling="auto"  marginwidth=0 ></iframe>
				</td>
			</tr>
		</table>
	</div>
	
	<div id="userMenuTool"
		style="display:none;background-color:#3f88d7;position: absolute; width: 400px; height:599px;top:60px; left:400px; bottom:10px; padding:1px;overflow: hidden;border: 1px solid #3f88d7;">
		<iframe src="userMenuTool.jsp" style="width: 100%;height: 100%" name="left" frameborder="0" noresize scrolling="no"  marginwidth=0 ></iframe>
	</div>
</body>
</html>
