<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.noki.mobi.common.Account"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script>
	<%
	String path = request.getContextPath();//获取路径
	Account account = (Account) session.getAttribute("account");//获取登录者信息
	String roleId = account.getRoleId();//登录者角色id
	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 10, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String loginName = (String) session.getAttribute("loginName");
	String sheng = (String) session.getAttribute("accountSheng");
	// String roleId = (String)session.getAttribute("accountRole");

	//获取本页查询的信息 
	String usename = request.getParameter("usename") != null ? request
			.getParameter("usename") : "";
	String adminsname = request.getParameter("adminsname") != null ? request
			.getParameter("adminsname")
			: "";
	// String sheng = (String)session.getAttribute("accountSheng");
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : "0";
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";
	String xiaoqu = request.getParameter("xiaoqu") != null ? request
			.getParameter("xiaoqu") : "0";
	String role=request.getParameter("role")!=null?request.getParameter("role"):"";		
	String bumen = request.getParameter("bumen") != null ? request
			.getParameter("bumen") : "0";
			String txtKeyword=request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"";
	String permissionRights = "";//登录者 的 导出按钮 权限
	int signs=1;//标志位  查询角色类型
    if(roleId.equals("1")){
	   signs=0;//如果roleid是1管理员权限  查询角色的标志位为0 表示查询全部角色
    }
%>
</script>
		<script language="javascript">
		 
var path = '<%=path%>';
function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = ""
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"
	} else {
		trObject.style.display = "none"
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
	}
}

function saccount() {
	var sname = document.form1.sname.value
	var srole = document.form1.srole.value
	var sorg = document.form1.sorg.value
	document.form1.action = path + "/web/sys/accountList.jsp?doQuery=1&san=1"
	document.form1.submit();
}
function editacc(id, sheng, shi, xian, xiaoqu) {
	window
			.open('modifyAccount.jsp?accountId=' + id + "&sheng=" + sheng
					+ "&shi=" + shi + "&xian=" + xian + "&xiaoqu=" + xiaoqu,
					'infoAccount',
					'height=500, width=1200,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
}
function delacc(accountId) {
	
		if (confirm("确定要删除此账户么！")) {
			document.form1.action = path + "/servlet/account?action=del&accountId="+accountId;
			document.form1.submit();
		} else {
			return;
		}
	
}
function chaxun() {
	document.form1.action = path + "/web/sdttWeb/sys/accountList.jsp";
	document.form1.submit();
}
function accountOn() {
	var i = 0;

	for ( var j = 0; j < document.form1.elements.length; j++) {
		if (document.form1.elements[j].checked) {
			i++;
		}
	}
	if (i > 0) {
		if (confirm("确定要启用这些账户么！")) {
			document.form1.action = path
					+ "/servlet/account?action=onOrOff&sign=1";
			document.form1.submit();
		} else {
			return;
		}
	} else {
		alert("请选择要启用的账户");
		return;
	}
}
function accountOff() {
	var i = 0;

	for ( var j = 0; j < document.form1.elements.length; j++) {
		if (document.form1.elements[j].checked) {
			i++;
		}
	}
	if (i > 0) {
		if (confirm("确定要停用这些账户么！所属关系也将被删除")) {
			document.form1.action = path
					+ "/servlet/account?action=onOrOff&sign=0";
			document.form1.submit();
		} else {
			return;
		}
	} else {
		alert("请选择要停用的账户");
		return;
	}
}
function resetPass() {
	var i = 0;

	for ( var j = 0; j < document.form1.elements.length; j++) {
		if (document.form1.elements[j].checked) {
			i++;
		}
	}
	if (i > 0) {
		if (confirm("确定要重置这些账户的密码么？")) {
			document.form1.action = path + "/servlet/account?action=resetPass";
			document.form1.submit();
		} else {
			return;
		}
	} else {
		alert("请选择要重置密码的账户");
		return;
	}
}
function fzqy() {
	var i = 0;
	var accountid = "";
	for ( var j = 0; j < document.form1.elements.length; j++) {
		if (document.form1.elements[j].checked) {
			i++;
			accountid = document.form1.elements[j].value;
		}
	}

	if (i == 1) {
	window.open('../sys/per_area.jsp?accountid='+ accountid,'newwindow','width=700,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=100');
	
		//document.form1.action = path + "/web/sdttWeb/sys/per_area.jsp?accountid="+ accountid;
		//document.form1.submit();

	} else {
		alert("每次只能选择一个用户");
		return;
	}
}
function checkPage() {
	if (document.form1.pageCheck.checked) {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = true
		}
	} else {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = false
		}
	}
}
function addacc() {

window.open("../sys/addAccount.jsp", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");
	//document.form1.action = path + "/web/sdttWeb/sys/addAccount.jsp";
	//document.form1.submit();
}
</script>
</head>
<jsp:useBean id="roleBean" scope="page"
		class="com.noki.mobi.sys.javabean.RoleBean">
	</jsp:useBean>
<jsp:useBean id="bean" scope="page"
			class="com.noki.mobi.sys.javabean.AccountBean"></jsp:useBean>
		<jsp:useBean id="ztcommon" scope="page"
			class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
		<jsp:useBean id="commBean" scope="page"
			class="com.noki.mobi.common.CommonBean"></jsp:useBean>
			<%
			String ssagcode = ztcommon.getLastAgcode(loginName);//ssagcode返回登录用户 小区或 县或 市或省的的编码
		%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	
<script language="javascript">
var path = '<%=path%>';
function gopage() {
	document.form1.submit();
}
function previouspage() {
	if ((parseInt(document.form1.page.value)) < 1)
		document.form1.page.value = 1;
	else {
		document.form1.page.value = parseInt(document.form1.page.value) - 1;
		var curpage = '<%=(curpage - 1)%>';
		document.form1.action = path + "/web/sdttWeb/sys/accountList.jsp?curpage="
				+ curpage;
		document.form1.submit();
	}
}
function nextpage() {
	document.form1.page.value = parseInt(document.form1.page.value) + 1;
	var curpage = '<%=(curpage + 1)%>';
	document.form1.action = path + "/web/sdttWeb/sys/accountList.jsp?curpage="+ curpage;
	document.form1.submit();
}
function gopagebyno(pageno) {
	document.form1.page.value = pageno;

	document.form1.action = path + "/web/sdttWeb/sys/accountList.jsp?curpage=" + pageno;
	document.form1.submit();
}

 function daochu(){
 document.form1.action =  path + "/servlet/SysManger?bz=" +'yhgl';
 document.form1.submit();
 
 }
$(function() {
	$("#add11").click(function() {
		addacc();
	});
	$("#shc").click(function() {
		delacc();
	});
	$("#chz").click(function() {
		resetPass();
	});
	$("#qyu").click(function() {
		fzqy();
	});
	$("#query").click(function() {
		chaxun();
	});
	$("#daochuBtn").click(function() {
		exportad();
	});
});
</script>
 
	 </head>
	<%
		permissionRights = commBean.getPermissionRight(roleId, "0501");//获取该角色 有哪些权限  返回权限的 字段值+， 的字符串  0501 站点查询权限 
	 
	%>
<body>
<ul class="tab">
  <li class="first"></li>
  <li class="cur"><a href="accountList.jsp">用户管理</a></li>
  <li ><a href="roleManager.jsp">角色管理</a></li>
 
  <li class="end"></li>
</ul>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">用户信息</div>
				<div class="content01_1">
					<table width="800px" border="0" cellpadding="1" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="60px">城市：</td>
							<td align="left" width="60px">
							<select name="shi" id="shi" style="box-sizing: border-box; width: 130px;" onchange="changeCity()" >
	         					<option value="0">请选择</option>
				         		<%
				         			ArrayList shilist = new ArrayList();
				         			shilist = commBean.getAgcode(sheng, shi, loginName);
				         			if (shilist != null) {
				         				String agcode = "", agname = "";
				         				int scount = ((Integer) shilist.get(0)).intValue();
				         				for (int i = scount; i < shilist.size() - 1; i += scount) {
				         			 	agcode = (String) shilist
				         							.get(i + shilist.indexOf("AGCODE"));
				         					agname = (String) shilist
				         							.get(i + shilist.indexOf("AGNAME"));
				         		%>
				                    <option value="<%=agcode%>"><%=agname%></option>
				                    <%
				                    	}
				                    }
				                    %>
				         		</select>
				         		</td>
				         		<td align="right" width="60px">区县：</td>
								<td align="left" width="60px">
								<select name="xian" id="xian" style="box-sizing: border-box; width: 130px;" onchange="changeCountry()" class="selected_font">
	         					<option value="0">请选择</option>
				         		<%
				         			ArrayList xianlist = new ArrayList();
				         		xianlist = commBean.getAgcode(shi, xian, loginName);
				         			if (xianlist != null) {
				         				String agcode = "", agname = "";
				         				int scount = ((Integer) xianlist.get(0)).intValue();
				         				for (int i = scount; i < xianlist.size() - 1; i += scount) {
				         					agcode = (String) xianlist
				         							.get(i + xianlist.indexOf("AGCODE"));
				         					agname = (String) xianlist
				         							.get(i + xianlist.indexOf("AGNAME"));
				         		%>
				                    <option value="<%=agcode%>"><%=agname%></option>
				                    <%
				                    	}
				                    	}
				                    %>
				         		</select>
				         	</td>
				         	<td align="right" width="60px">乡镇：</td>
							<td align="left" width="60px">
								<select name="xiaoqu" id="xiaoqu" style="box-sizing: border-box; width: 130px;" class="selected_font">
			         		<option value="0">请选择</option>             
			         		<%
			         			ArrayList xiaoqulist = new ArrayList();   //下拉列表
			         			xiaoqulist = commBean.getAgcode(xian, xian, loginName);
			         			if (xiaoqulist != null) {
			         				String agcode = "", agname = "";
			         				int scount = ((Integer) xiaoqulist.get(0)).intValue();
			         				for (int i = scount; i < xiaoqulist.size() - 1; i += scount) {
			         					agcode = (String) xiaoqulist.get(i
			         							+ xiaoqulist.indexOf("AGCODE"));
			         					agname = (String) xiaoqulist.get(i
			         							+ xiaoqulist.indexOf("AGNAME"));
			         		%>
			                    <option value="<%=agcode%>"><%=agname%></option>
			                    <%
			                    	}
			                    	}
			                    %>
			         			</select>
							</td>
						</tr>
						<tr>
							<td align="right" width="60px">角色：</td>
							<td width="100px">
								<select name="role" style="box-sizing: border-box; width: 130px;" class="selected_font">
								<option value="">请选择</option>  
								<%
								  ArrayList listjs = new ArrayList();
									listjs = roleBean.getAllRole(signs);
									int countColums = ((Integer) listjs.get(0)).intValue();
									String roleId2 = "", roleName2 = "";
									for (int i = countColums; i < listjs.size() - 1; i += countColums) {
										roleId2 = (String) listjs.get(i + listjs.indexOf("ROLEID"));
										roleName2 = (String) listjs.get(i + listjs.indexOf("NAME"));
								%>
								<option value="<%=roleId2%>"><%=roleName2%></option>
								<%
									}
								%>
			
							</select>
							</td>
							<td align="right" width="100px">部门:</td>
							<td width="100px">
							    <select name="bumen" style="width: 130px;">
							        <option value="">请选择</option>
							    </select>
							</td>
							<td align="right" width="60px">关键字：</td>
							<td align="left" width="60px">
								<input type="text" name="txtKeyword" id="txtKeyword"  style="box-sizing: border-box; width: 130px;" />
							</td>
						</tr>
						<tr>
							<td align="left" colspan="8">
								※关键字:包括姓名/账号/人力账号
							</td>
					    </tr>
						<tr>
							<td align="right" colspan="8">
								<input type="submit" class="btn_c1" onclick="chaxun()" value="查询" /> 
							</td>
						</tr>
					</table>
					
					<div class="tbtitle01"><b>用户查询统计一览</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
									<tr align="right">
										<td colspan="9" align="right">
										    <input name="button2" type="submit" onclick="addacc()" class="btn_c1" value="新增" />&nbsp;&nbsp;
											<input name="button2" type="submit" onclick="fzqy()" class="btn_c1" id="button2" value="分配负责区域" />
											<input name="chz" type="submit"  class="btn_c1" id="chz" value="密码重置" />
										    <input name="button2" type="button" class="btn_c1" onclick="daochu()" id="button2" value="导出Excel" />
										</td>
									</tr>
									<tr align="center">
									    <th width="10">序号</th>
										<th width="120">账号</th>
										<th width="100">姓名</th>
										<th width="80">地区</th>
										<th width="80">手机号码</th>
										<th width="80">座机</th>
										<th width="80">角色</th>
										<th width="80">操作</th>
										<th width="10"><div>勾选<input type="checkbox" name="pageCheck" onclick="checkPage()" /></div></th>
									</tr>
						<%
											String whereStr = "";
											if (shi != null && !shi.equals("") && !shi.equals("0")) {
												whereStr = whereStr + " and a.shi='" + shi + "'";
											}
											if (xian != null && !xian.equals("") && !xian.equals("0")) {
												whereStr = whereStr + " and a.xian='" + xian + "'";
											}
											if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
												whereStr = whereStr + " and a.xiaoqu='" + xiaoqu + "'";
											}
											if (txtKeyword != null && !txtKeyword.equals("") && !txtKeyword.equals("0")) {
												whereStr = whereStr + " and (a.accountname like '%" + txtKeyword + "%' or a.name like '%" + txtKeyword + "%'or a.cthrnumber like '%" + txtKeyword + "%')";
											}
											if (role != null && !role.equals("") && !role.equals("0")) {
												whereStr = whereStr + " and a.roleid like '%" + role + "%'";
											}
											if (bumen != null && !bumen.equals("") && !bumen.equals("0")) {
												whereStr = whereStr + " and a.bumen = '" + bumen + "'";
											}
											
		                    ArrayList list = new ArrayList();
		                   list = bean.getAllAccountxyh(whereStr, usename, adminsname, curpage,ssagcode);//获取 用户的分页数据  ssagcode登录用户所属的区域 编码 
		                    allpage = bean.getAllPage();
											int intnum = pagesize*(curpage-1)+1;
											System.out.println(intnum);										
											int countColum = ((Integer) list.get(0)).intValue();
											String accountId = "", accountName = "", name = "", mobile = "", roleName = "", sign = "", tel = "";
											String rsheng = "", rshi = "", rxian = "", rxiaoqu = "";
										String color = "F3F3F3";
		 //System.out.println(intnum);
			if(list!=null){
											for (int i = countColum; i < list.size() - 1; i += countColum) {
												accountId = (String) list.get(i + list.indexOf("ACCOUNTID"));
												accountName = (String) list
														.get(i + list.indexOf("ACCOUNTNAME"));
												name = (String) list.get(i + list.indexOf("NAME"));
												mobile = (String) list.get(i + list.indexOf("MOBILE"));
												roleName = (String) list.get(i + list.indexOf("ROLENAME"));
												sign = (String) list.get(i + list.indexOf("SZDQ"));
                                                tel=(String)list.get(i+list.indexOf("TEL"));
												rsheng = (String) list.get(i + list.indexOf("SHENG"));
												rshi = (String) list.get(i + list.indexOf("SHI"));
												rxian = (String) list.get(i + list.indexOf("XIAN"));
												rxiaoqu = (String) list.get(i + list.indexOf("XIAOQU"));
												if (mobile == null)
													mobile = "";

       			if (intnum % 2 == 0) {
       				color = "#DDDDDD";

       			} else {
       				color = "#FFFFFF";
       			}
       			
       			
       %>
      
									<!-- 数据加载  Start-->
									<tr align="center">
										<td width="10"><%=intnum++%></td>
										<td align="left"><%=accountName%></td>
										<td align="left"><%=name%></td>
										<td align="center"><%=sign%></td>
										<td align="center"><%=mobile%></td>
										<td align="center"><%=tel%></td>
										<td align="center"><%=roleName%></td>
										<td width="80" align="center"><div >
											<a href="#" onclick="editacc('<%=accountId%>','<%=rsheng%>','<%=rshi%>','<%=rxian%>','<%=rxiaoqu%>')"><img src="../images/accept.png" width="16" height="16"  title="修改" /></a>
											<a href="#" onclick="delacc('<%=accountId%>')"><img src="../images/delete.png" width="16" height="16"  title="删除" /></a>
												
												
												</div></td>
										<td>
											<input type="checkbox" name="itemSelected"
													value="<%=accountId%>" />
										</td>
									</tr><% } %>
 
									<!-- 数据加载 End -->
									
									<tr bgcolor="#ffffff">
					<td colspan="10" align="left" >
						<div align="left">

							 &nbsp;&nbsp;
							 <font color='#1E90FF'>
						     <%
						     	if (curpage != 1) {
						     			out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
						     		}
						     %>
					     </font>
					     &nbsp;&nbsp;
							 <font color='#1E90FF'>
						     <%
						     	if (curpage != 1) {
						     			out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
						     		}
						     %>
						   </font>
						   &nbsp;&nbsp;
						   转到 <select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" style="width:40px;font-family: 宋体;font-size:12px;line-height:120%;" >
					     <%
					     	for (int i = 1; i <= allpage; i++) {
					     			if (curpage == i) {
					     				out.print("<option value='" + i
					     						+ "' selected='selected'>" + i + "</option>");
					     			} else {
					     				out.print("<option value='" + i + "'>" + i
					     						+ "</option>");
					     			}
					     		}
					     %>
				    </select>&nbsp;&nbsp;共 <font color='#1E90FF'><b><%=allpage%></b>&nbsp;</font>页 &nbsp;&nbsp;
							<font color='#1E90FF'>
						     <%
						     	if (allpage != 0 && (curpage < allpage)) {
						     			out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");
						     		}
						     %>
             				</font>
							&nbsp;&nbsp;							
							<font color='#1E90FF'>
					     <%
					     	if (allpage != 0 && (curpage < allpage)) {
					     			out.print("<a href='javascript:gopagebyno(" + allpage+ ")'><img src='../images/page-last.gif'></a>");
					     		} else {
					     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
					     		}
					     %>
            </font>
            &nbsp;&nbsp;
						</div>
					</td>
				</tr>
								
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
		<input type="hidden" name="shi2" id="shi2" value="" />
		<input type="hidden" name="xian2" id="xian2" value="" />
		<input type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
		</form>

<script type="text/javascript">
$(document).ready(function(){    
  	var sid = document.form1.shi.value;
	updateDept(sid);
}); 
function exportad() {
	var usename = "<%=usename%>";
	var adminsname = "<%=adminsname%>";
	var ssagcode = "<%=ssagcode%>";
	var whereStr = "<%=whereStr%>";
	document.form1.action = path + "/web/sys/操作员信息.jsp?usename=" + usename
			+ "&whereStr=" + whereStr + "&adminsname=" + adminsname
			+ "&ssagcode=" + ssagcode;
	document.form1.submit();
}
var XMLHttpReq;
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	
	///////////////////////////////////////////////////////////
	function sendRequest(url,para) {

		createXMLHttpRequest();
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="sheng"){
			XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		XMLHttpReq.send(null);  
	}
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		//alert(XMLHttpReq.status);
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
            	var res = XMLHttpReq.responseText;
              window.alert(res);
             
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    
function processResponse_sheng() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            	
            	//var res = XMLHttpReq.responseText;
            
              updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateQx(res);
        //  var sid = document.form1.shi.value;
		//  updateDept(sid);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZd(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}

function changeSheng(){
	var sid = document.form1.sheng.value;

	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
	 //alert("11111");
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeCity(){
	var sid = document.form1.shi.value;
	updateDept(sid);
	
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function changeCountry(){
	var sid = document.form1.xian.value;

	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
//地市级联查询部门
function updateDept(sid){
	
   var deptObj = document.all.bumen;
   deptObj.options.length="0";
   deptObj.add(new Option("请选择","0"));
   //console.log(sid)
   if(sid && sid !="0"){
      $.ajax({
         type:'post',
         url: path + '/servlet/commonBeanServlet?action=dept',
         cache: false,
         data: {
         	shi: sid,
         	fdeptcode: sid
         },
         dataType: 'json',
         success: function(data){
         	//console.log(data)
         	if(data){
	         	for ( var i = 0; i < data.length; i++) {
					var optn = data[i].split("\|");
					deptObj.add(new Option(optn[1],optn[0]));
				}
         	}
         	document.form1.bumen.value='<%=bumen%>';
         },
         error: function(){
            return;
         }
      });
   }
} 

function openDept(){
	var shiObj = document.form1.shi;
	if(shiObj.value == "0"){
	}else{
	   window.open("deptWin.jsp", "newwindowDept?shi="+shiObj.value, "height=550, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
	}
}
        document.form1.role.value='<%=role%>';
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
	
		document.form1.txtKeyword.value='<%=txtKeyword%>';
</script>
</body>
</html>


