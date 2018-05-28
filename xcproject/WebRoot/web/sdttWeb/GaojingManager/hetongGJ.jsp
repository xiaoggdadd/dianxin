<%@ page contentType="text/html; charset=UTF-8"%>  
<%@ page
	import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.query.basequery.javabean.ElectricFeesQueryBean"%>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page
	import="java.text.*,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.util.regex.Pattern"%>
<%@ page import="com.noki.hetongguanli.Dao.ShenPiDao"%>
<%@ page import="com.noki.hetongguanli.javabean.ShenPiClass"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<%
	String path = request.getContextPath();//获取路径
	Account account = (Account) session.getAttribute("account");//获取登录者信息
	String roleId = account.getRoleId();//登录者角色id
	String s_curpage = request.getParameter("curpage") != null ? request
	.getParameter("curpage")
	: "1";
	String rgsh = request.getParameter("rgsh");		//站点审核通过   首页传的值
	String rgsh2 = request.getParameter("caiji");	// 采集站点
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
	
	String txtKeyword=request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"";
%>
<%
	//获取DAO中TBL_HETONG_SHENPI表的值
	ShenPiDao dao = new ShenPiDao();
	ShenPiClass sp = new ShenPiClass();
	ArrayList<ShenPiClass> al =  null;
	//获取文本框的值
	String starttime = request.getParameter("starttime");	
	String endtime = request.getParameter("endtime");
	String time = request.getParameter("time");	
	String keyword = request.getParameter("keyword");  
	String partya = request.getParameter("partya");	
	String partyb = request.getParameter("partyb");
	String contractname = request.getParameter("contractname");	 
	String zhuangtai = "1";   	//审核状态固定为通过审核
	String yeshu = request.getParameter("curpage"); //获取分页查询页数 
	System.out.println(allpage);
	if(yeshu != null && !yeshu.equals("") && !yeshu.equals("0")){//不为空则重新赋值

		curpage = Integer.valueOf(yeshu).intValue();//强转int

		}
	//判断下拉列表的值并进行查询方法的选择 
	if(starttime != null && !starttime.equals("") && !starttime.equals("0")){
		//开始时间为starttime查询方法
		al = dao.SelectStarttime(starttime);
	}else if(endtime != null && !endtime.equals("") && !endtime.equals("0")){
		//结束时间为endtime查询方法
		al = dao.SelectEndtime(endtime);
	}else if(time != null && !time.equals("") && !time.equals("0")){
		//距离结束时间还有time查询方法
		if(time.equals("yi")){
			//当时间固定为一周内时,计算共多少页
			allpage  = dao.SelectCountSelectTime1(partya, partyb, contractname, zhuangtai, keyword);
			//当时间固定为一周内时,遍历集合内容
			al = dao.SelectTime1(curpage,partya, partyb, contractname,zhuangtai,keyword);
		}else if(time.equals("er")){
			allpage  = dao.SelectCountSelectTime2(partya, partyb, contractname, zhuangtai, keyword);
			al = dao.SelectTime2(curpage,partya, partyb, contractname,zhuangtai,keyword);
		}else if(time.equals("san")){
			allpage  = dao.SelectCountSelectTime3(partya, partyb, contractname, zhuangtai, keyword);
			al = dao.SelectTime3(curpage,partya, partyb, contractname,zhuangtai,keyword);
		}
	}else{
		allpage  = dao.SelectCountZhuangTai(partya, partyb, contractname, zhuangtai, keyword);

		al = dao.SelectHeTongGaoJing(curpage,partya, partyb, contractname,zhuangtai,keyword);
	}
 %>

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
</head>
<script language="javascript">
		 
var path = '<%=path%>';
function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = "";
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">";
	} else {
		trObject.style.display = "none";
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">";
	}
}

function saccount() {
	var sname = document.form1.sname.value;
	var srole = document.form1.srole.value;
	var sorg = document.form1.sorg.value;
	document.form1.action = path + "/web/sys/accountList.jsp?doQuery=1&san=1";
	document.form1.submit();
}
function editacc(id, sheng, shi, xian, xiaoqu) {
	window
			.open('modifyAccount.jsp?accountId=' + id + "&sheng=" + sheng
					+ "&shi=" + shi + "&xian=" + xian + "&xiaoqu=" + xiaoqu,
					'infoAccount',
					'width=850,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
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
	document.form1.action = path + "/web/sdttWeb/GaojingManager/hetongGJ.jsp";
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
	
	window.open('../sys/per_area.jsp?accountid='+ accountid,'','width=600,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
	
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
			document.form1.elements[j].checked = true;
		}
	} else {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = false;
		}
	}
}
function addacc() {
window.open("../sys/addAccount.jsp", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");
	//document.form1.action = path + "/web/sdttWeb/sys/addAccount.jsp";
	//document.form1.submit();
}
function sh(id){
window.open("../hetongshenpi/hetongshenpikuozhan.jsp?id="+id, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");
}
</script>
<body>
	<form action="" name="form1" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr valign="top">
				<td width="12"><img src="../images/space.gif" width="12"
					height="17" />
				</td>
				<td>
					<div class="content01">
						<div class="tit01">合同到期提醒</div>
						<div class="content01_1">
							<table width="800px" border="0" cellpadding="1" cellspacing="0"
								class="tb_select">
								<tr>
									<td align="right" width="60px">合同结束还有：</td>
									<td align="left" width="60px"><select name="time"
										id="time" style="box-sizing: border-box; width: 130px;" class="selected_font">
											<option value="0">请选择</option>
											<option value="yi">一周</option>
											<option value="er">二周</option>
											<option value="san">三周</option>
									</select></td>
									<td align="right" width="60px">甲方：</td>
									<td align="left" ><input type="text"
										name="partya" id="partya" value="" style="box-sizing: border-box; width: 130px;"/></td>
									<td align="right" width="60px">乙方：</td>
									<td align="left" width="60px"><input type="text"
										name="partyb" id="partyb" value="" style="box-sizing: border-box; width: 130px;" /></td>
									<td align="right" width="60px">合同名称：</td>
									<td align="left" width="80px"><input type="text"
										name="contractname" id="contractname" value=""style="box-sizing: border-box; width: 130px;" /></td>
								<!--<td align="right" width="60px">合同开始时间：</td>
									<td align="left" width="60px"><input type="text"
										name="starttime" id="starttime" value="" style="width:80px" /></td>
									<td align="right" width="60px">合同结束时间：</td>
									<td align="left" width="60px"><input type="text"
										name="endtime" id="endtime" value="" style="width:80px" /></td>   -->	
								</tr>
								<tr>
								<!-- 	<td align="right" width="60px">关键字：</td>
							   			<td align="left" width="60px"><input type="text"
										name="keyword" id="keyword" value="" style="width:80px" />
									</td>
									<td align="left">※关键字：甲方/乙方/合同名称</td>  -->
								</tr>
								<tr>
									<td align="right" colspan="8"><input type="submit"
										class="btn_c1" onclick="chaxun()" value="查询" /></td>
								</tr>
							</table>

							<div class="tbtitle01">
								<b>合同时间一览</b>
							</div>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="tb_list1">
								<tr align="right">
									<td colspan="9" align="right" valign="middle">
										<!--<input name="button2" type="submit" class="btn_c1" id="button2" value="批量审批" />&nbsp;  -->
										<input name="button2" type="submit" onclick="daochu()"
										class="btn_c1" id="button2" value="Excel导出" />&nbsp;</td>
								</tr>
								<tr align="center">
									<th width="10">序号</th>
									<th>合同开始时间</th>
									<th>合同结束时间</th>
									<th>合同甲方</th>
									<th>合同乙方</th>
									<th>合同名称</th>
									<th>合同项目金额</th>
									<th>审批状态</th>
								</tr>

								<%
								String whereStr = "";
											if (shi != null && !shi.equals("") && !shi.equals("0")) {
												whereStr = whereStr + " and zd.shi='" + shi + "'";
											}
											if (xian != null && !xian.equals("") && !xian.equals("0")) {
												whereStr = whereStr + " and zd.xian='" + xian + "'";
											}
											if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
												whereStr = whereStr + " and zd.xiaoqu='" + xiaoqu + "'";
											}
											if(txtKeyword!=null&&!txtKeyword.equals("")){
											    whereStr = whereStr + " and zd.jzname like '%" + txtKeyword + "%'";
											}

													if(al!=null){	
														for(int i=0;i<al.size(); i++){
														 	int xuhao  = i+1;
															String bz = al.get(i).getBeizhu();
															String cn = al.get(i).getContractname();
															String et = al.get(i).getEndtime();
															int id = al.get(i).getId();
															String pa = al.get(i).getPartya();
															String pb = al.get(i).getPartyb();
															String pt = al.get(i).getProjectamonnt();
															String st = al.get(i).getStarttime();
															String zt = al.get(i).getZhuangtai();
															if(zt.equals("0")){
																//未审核
																zt = "未审核";
															}else if(zt.equals("1")){
																//审核通过
																zt = "审核通过";
															}else if(zt.equals("-1")){
																//审核不通过
																zt = "审核不通过";
															}
								%>
								<!-- 数据加载  Start-->
								<tr align="center">
									<td width="10"><%=xuhao++%></td>
									<td align="left"><%=st%></td>
									<td align="left"><%=et%></td>
									<td align="right"><%=pa%></td>
									<td align="right"><%=pb%></td>
									<td align="right"><%=cn%></td>
									<td align="right"><%=pt%></td>
									<td align="center"><%=zt%></td>
								</tr>
								<%	
									}
								%>

								<!-- 数据加载 End -->

								<tr bgcolor="#ffffff">
									<td colspan="9" align="left">
										<div align="left">

											&nbsp;&nbsp; <font color='#1E90FF'> <%
 	if (curpage != 1) {
 				     			out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
 				     		} else {
 				     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
 				     		}
 %> </font> &nbsp;&nbsp; <font color='#1E90FF'> <%
 	if (curpage != 1) {
 				     			out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
 				     		} else {
 				     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
 				     		}
 %> </font> &nbsp;&nbsp; 转到 <select name="page"
												onChange="javascript:gopagebyno(document.form1.page.value)"
												style="width:40px;font-family: 宋体;font-size:12px;line-height:120%;">
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
											</select>&nbsp;&nbsp;共 <font color='#1E90FF'><b><%=allpage%></b>&nbsp;</font>页
											&nbsp;&nbsp; <font color='#1E90FF'> <%
 	if (allpage != 0 && (curpage < allpage)) {
 				     			out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");
 				     		} else {
 				     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");
 				     		}
 %> </font> &nbsp;&nbsp; <font color='#1E90FF'> <%
 	if (allpage != 0 && (curpage < allpage)) {
 			     			out.print("<a href='javascript:gopagebyno(" + allpage+ ")'><img src='../images/page-last.gif'></a>");
 			     		} else {
 			     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
 			     		}
 %> </font> &nbsp;&nbsp;
										</div></td>
								</tr>

							</table>
							<div class="space_h_10"></div>
						</div>
					</div></td>
			</tr>
			<%
				}
			%>

		</table>
		<input type="hidden" name="shi2" id="shi2" value="" /> <input
			type="hidden" name="xian2" id="xian2" value="" /> <input
			type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
	</form>
	<script type="text/javascript">
	var path = '<%=path%>';
	function gopage() {
		document.form1.submit();
	}
	function previouspage() {
		var rgsh2 = '<%=rgsh2%>';
		var rgsh = '<%=rgsh%>';
		document.form1.page.value = parseInt(document.form1.page.value) - 1;
		var curpage = '<%=(curpage - 1)%>';
		document.form1.action = path
				+ "/web/sdttWeb/GaojingManager/hetongGJ.jsp?rgsh=" + rgsh
				+ "&caiji=" + rgsh2 + "&curpage=" + curpage;
		document.form1.submit();
	}
	function nextpage() {
		var rgsh2 = '<%=rgsh2%>';
		var rgsh = '<%=rgsh%>';
		document.form1.page.value = parseInt(document.form1.page.value) + 1;
		var curpage = '<%=(curpage + 1)%>';
		document.form1.action = path
				+ "/web/sdttWeb/GaojingManager/hetongGJ.jsp?rgsh=" + rgsh
				+ "&caiji=" + rgsh2 + "&curpage=" + curpage;
		document.form1.submit();
	}
	function gopagebyno(pageno) {
		var rgsh2 = '<%=rgsh2%>';
		var rgsh = '<%=rgsh%>';
		document.form1.page.value = pageno;
		document.form1.action = path
				+ "/web/sdttWeb/GaojingManager/hetongGJ.jsp?rgsh=" + rgsh
				+ "&caiji=" + rgsh2 + "&curpage=" + pageno;
		document.form1.submit();
	}
	
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
				} catch (e) {};
			};
		};
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
            };
        };
    }
    
function processResponse_sheng() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            	
            	//var res = XMLHttpReq.responseText;
            
              updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            };
        };
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
        };
    };
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
        };
    };
}

function changeSheng(){

	var sid = document.form1.sheng.value;
	document.form1.sheng2.value=document.form1.sheng.value;
	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	};
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	};
}
function changeCity(){
	var sid = document.form1.shi.value;
	document.form1.shi2.value=document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	};
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	};
}

function changeCountry(){
	var sid = document.form1.xian.value;
	document.form1.xian2.value=document.form1.xian.value;
	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	};
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	};
}
function daochu(){
	document.form1.action=path+"/servlet/PlDownload?bz=JZBGSP";
	document.form1.submit();
}
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.txtKeyword.value='<%=txtKeyword%>';
</script>
</body>
</html>
