
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.noki.mobi.common.Account"%>
<%@ page session="true"%>
<%
	String path = request.getContextPath();//获取路径
	Account account = (Account) session.getAttribute("account");//获取登录者信息
	String roleId = account.getRoleId();//登录者角色id
	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
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
	String permissionRights = "";//登录者 的 导出按钮 权限
%>
<html>
	<head>
		<link rel="stylesheet" type="text/css"  href="<%=path%>/web/css/accountListjsp.css" />
 
		<LINK href="<%=path%>/images/css.css" type="text/css" rel="stylesheet"/>
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
					'width=850,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
}
function delacc() {
	var i = 0;

	for ( var j = 0; j < document.form1.elements.length; j++) {
		if (document.form1.elements[j].checked) {
			i++;
		}
	}
	if (i > 0) {
		if (confirm("确定要删除这些账户么！")) {
			document.form1.action = path + "/servlet/account?action=del";
			document.form1.submit();
		} else {
			return;
		}
	} else {
		alert("请选择要删除的账户");
		return;
	}
}
function chaxun() {
	document.form1.action = path + "/web/sys/accountList.jsp";
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
		document.form1.action = path + "/web/sys/per_area.jsp?accountid="
				+ accountid;
		document.form1.submit();

	} else {
		alert("一次只能选择一个用户");
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
	document.form1.action = path + "/web/sys/addAccount.jsp";
	document.form1.submit();
}
</script>
		<jsp:useBean id="bean" scope="page"
			class="com.noki.mobi.sys.javabean.AccountBean"></jsp:useBean>
		<jsp:useBean id="ztcommon" scope="page"
			class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
		<jsp:useBean id="commBean" scope="page"
			class="com.noki.mobi.common.CommonBean"></jsp:useBean>
		<%
			String ssagcode = ztcommon.getLastAgcode(loginName);//ssagcode返回登录用户 小区或 县或 市或省的的编码
		%>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
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
		document.form1.action = path + "/web/sys/accountList.jsp?curpage="
				+ curpage;
		document.form1.submit();
	}
}
function nextpage() {
	document.form1.page.value = parseInt(document.form1.page.value) + 1;
	var curpage = '<%=(curpage + 1)%>';
	document.form1.action = path + "/web/sys/accountList.jsp?curpage="
			+ curpage;
	document.form1.submit();
}
function gopagebyno(pageno) {
	document.form1.page.value = pageno;

	document.form1.action = path + "/web/sys/accountList.jsp?curpage=" + pageno;
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

	<body class="body"  > 
		<form action="accountList.jsp" name="form1" method="POST">
			<table width="99%" border="0" align="center" cellpadding="1"
				cellspacing="1">
				<tr>
					<td colspan=2 width="700px"  id="caozuoyuanshezhi" height=63> 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作员设置 
					</td>
					<td colspan=5>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="49" bgcolor="#FFFFFF">
						<table width="100%" border="0" cellspacing="1" cellpadding="1">
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									<div class="divhr"> <hr/> </div>
									&nbsp;过滤条件&nbsp;
									<div class="divhr2"> <hr/> </div>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan=3>
												城市：
												<select name="shi" class="selectquxianshi" onchange="changeCity()">
													<option value="0">请选择</option>
													<%
														ArrayList shilist = new ArrayList();
														shilist = commBean.getAgcode(sheng, account.getAccountId());//列出 登录者 负责的 城市 
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
												区县：
												<select name="xian" class="selectquxianshi" onchange="changeCountry()">
													<option value="0"> 请选择 </option>
													<%
														ArrayList xianlist = new ArrayList();
														xianlist = commBean.getAgcode(shi, account.getAccountId());
														if (xianlist != null) {
															String agcode = "", agname = "";
															int scount = ((Integer) xianlist.get(0)).intValue();
															for (int i = scount; i < xianlist.size() - 1; i += scount) {
																agcode = (String) xianlist.get(i
																		+ xianlist.indexOf("AGCODE"));
																agname = (String) xianlist.get(i
																		+ xianlist.indexOf("AGNAME"));
													%>
													<option value="<%=agcode%>"><%=agname%></option>
													<%
														}
														}
													%>
												</select>

												乡镇：
												<select name="xiaoqu" class="selectquxianshi"
													onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
													<option value="0"> 请选择 </option>
													<%
														ArrayList xiaoqulist = new ArrayList();
														xiaoqulist = commBean.getAgcode(xian, account.getAccountId());
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
												账号：
												<input type="text" name="usename" value="<%=usename%>" class="selectquxianshi" />
												姓名：
												<input type="text" name="adminsname" value="<%=adminsname%>"
													class="selectquxianshi" />
												<div id="query" >
													<img alt="" src="<%=request.getContextPath()%>/images/chaxun.png"
														width="100%" height="100%" />
													<span id="chaxunspan" >查询</span>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>

							<tr>
								<td>
									<table width="100%" border="0" cellspacing="1" cellpadding="1"
										id="tableliebiao"  >
										<tr id="tablebiaoti">
											<td width="5%" height="23"  >
												<div >
													序号
												</div>
											</td>
											<td nowrap height="23"  >
												<div  >
													账号
												</div>
											</td>
											<td nowrap height="23"  >
												<div   >
													姓名
												</div>
											</td>
											<td nowrap height="23"  >
												<div   >
													地区
												</div>
											</td>

											<td nowrap height="23"  >
												<div   >
													手机号码
												</div>
											</td>
											<td nowrap height="23"  >
												<div   >
													座机
												</div>
											</td>
											<td nowrap height="23">
												<div align="center">
													角色
												</div>
											</td>
											<td width="100" height="23"  >
												<div   >
													编辑
												</div>
											</td>
											<td width="5%" height="23"  >
												<div >
													<input type="checkbox" name="pageCheck"
														onClick="checkPage()" />
												</div>
											</td>
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
											ArrayList list = new ArrayList();
											list = bean.getAllAccount(whereStr, usename, adminsname, curpage,
													ssagcode);//获取 用户的分页数据  ssagcode登录用户所属的区域 编码 
											allpage = bean.getAllPage();
											int intnum = 1;
											int countColum = ((Integer) list.get(0)).intValue();
											String accountId = "", accountName = "", name = "", mobile = "", roleName = "", sign = "", tel = "";
											String rsheng = "", rshi = "", rxian = "", rxiaoqu = "";
											String color = "F3F3F3";
											for (int i = countColum; i < list.size() - 1; i += countColum) {
												accountId = (String) list.get(i + list.indexOf("ACCOUNTID"));
												accountName = (String) list
														.get(i + list.indexOf("ACCOUNTNAME"));
												name = (String) list.get(i + list.indexOf("NAME"));
												mobile = (String) list.get(i + list.indexOf("MOBILE"));
												roleName = (String) list.get(i + list.indexOf("ROLENAME"));
												sign = (String) list.get(i + list.indexOf("SZDQ"));

												rsheng = (String) list.get(i + list.indexOf("SHENG"));
												rshi = (String) list.get(i + list.indexOf("SHI"));
												rxian = (String) list.get(i + list.indexOf("XIAN"));
												rxiaoqu = (String) list.get(i + list.indexOf("XIAOQU"));
												if (intnum % 2 == 0) {
													color = "#DDDDDD";
												} else {
													color = "FFFFFF";
												}
												if (mobile == null)
													mobile = "";
										%>
										<tr bgcolor="<%=color%>"  id="dongtaitr">
											<td nowrap>
												<div ><%=intnum++%></div>
											</td>
											<td>
												<div ><%=accountName%></div>
											</td>
											<td>
												<div ><%=name%></div>
											</td>
											<td>
												<div ><%=sign%></div>
											</td>
											<td>
												<div ><%=mobile%></div>
											</td>
											<td>
												<div ><%=tel%></div>
											</td>
											<td>
												<div ><%=roleName%></div>
											</td>
											<td>
												<div >
													<a
														href="javascript:editacc('<%=accountId%>','<%=rsheng%>','<%=rshi%>','<%=rxian%>','<%=rxiaoqu%>')">明细&修改</a>
												</div>
											</td>
											<td >
												<input type="checkbox" name="itemSelected"
													value="<%=accountId%>" />
											</td>
										</tr>
										<%
											}
										%>

										<tr bgcolor="#ffffff">
											<td colspan="11">
												<div align="center">
													<font color='#000080'>&nbsp;页次:</font> &nbsp;&nbsp;
													<b><font color=red><%=curpage%>/</font> </b>
													<font color='#000080'><b><%=bean.getAllPage()%></b>&nbsp;</font>
													&nbsp;&nbsp;
													<font color='#000080'> <%
 	if (curpage != 1) {
 		out.print("<a href='javascript:gopagebyno(1)'>首页</a>");
 	} else {
 		out.print("首页");
 	}
 %> </font> &nbsp;&nbsp;
													<font color='#000080'> <%
 	if (curpage != 1) {
 		out.print("<a href='javascript:previouspage()'>上页</a>");
 	} else {
 		out.print("上页");
 	}
 %> </font> &nbsp;&nbsp;
													<font color='#000080'> <%
 	if (allpage != 0 && (curpage < allpage)) {
 		out.print("<a href='javascript:nextpage()'>下页</a>");
 	} else {
 		out.print("下页");
 	}
 %> </font> &nbsp;&nbsp;
													<font color='#000080'> <%
 	if (allpage != 0 && (curpage < allpage)) {
 		out.print("<a href='javascript:gopagebyno(" + allpage
 				+ ")'>尾页</a>");
 	} else {
 		out.print("尾页");
 	}
 %> </font> &nbsp;&nbsp;
													<select name="page"
														onChange="javascript:gopagebyno(document.form1.page.value)"
														class="form">
														<%
															for (int i = 1; i <= allpage; i++) {
																if (curpage == i) {
																	out.print("<option value='" + i + "' selected='selected'>"
																			+ i + "</option>");
																} else {
																	out.print("<option value='" + i + "'>" + i + "</option>");
																}
															}
														%>
													</select>
												</div>
											</td>
										</tr>

									</table>
								</td>
							</tr>
							<tr bgcolor="#FFFFFF">
								<td colspan=15 align="right">
									<div id="parent" style="display: inline" align="right">
										<div style="width: 400px; display: inline;" align="right">
											<hr>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div id="qyu"
										style="position: relative; width: 87px; height: 23px; cursor: pointer; float: right; right: 65px">
										<img alt=""
											src="<%=request.getContextPath()%>/images/gongju.png"
											width="100%" height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">分配负责区域</span>
									</div>
									<div id="chz"
										style="position: relative; width: 83px; height: 23px; cursor: pointer; float: right; right: 72px">
										<img alt=""
											src="<%=request.getContextPath()%>/images/mmcz.png"
											width="100%" height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">密码重置</span>
									</div>
									<div id="shc"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; float: right; right: 78px">
										<img alt=""
											src="<%=request.getContextPath()%>/images/quxiao.png"
											width="100%" height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">删除</span>
									</div>
									<div id="add11"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; float: right; right: 84px">
										<img alt=""
											src="<%=request.getContextPath()%>/images/xinzeng.png"
											width="100%" height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">新增</span>
									</div>
									<%
										if (permissionRights.indexOf("PERMISSION_SEARCH") >= 0) {
									%>
									<div id="daochuBtn"
										style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: -290px;">
										<img src="<%=path%>/images/daoru.png" width="100%"
											height="100%">
										<span
											style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导出</span>
									</div>
									<%
										}
									%>
								</td>
							</tr>
							<input type="hidden" name="shi2" id="shi2" value="" />
							<input type="hidden" name="xian2" id="xian2" value="" />
							<input type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
						</table>
			</table>
		</form>
	</body>
	<script type="text/javascript">
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
	document.form1.sheng2.value=document.form1.sheng.value;
	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeCity(){
	var sid = document.form1.shi.value;
	document.form1.shi2.value=document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;s
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function changeCountry(){
	var sid = document.form1.xian.value;
	document.form1.xian2.value=document.form1.xian.value;
	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
</script>
</html>

