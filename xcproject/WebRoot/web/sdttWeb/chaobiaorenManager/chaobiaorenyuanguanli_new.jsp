<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.noki.chaobiaorenyuan.Dao.ChaoBiaoRenDao"%>
<%@ page import="com.noki.chaobiaorenyuan.bean.ChaoBiaoRen"%>
<%@ page import="com.noki.chaobiaorenyuan.Dao.ChaoBiaoRenDao_new" %>
<%@ page import="com.noki.chaobiaorenyuan.bean.ChaoBiaoRen_new"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>抄表人员管理</title>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
		<script>
<%    		String path = request.getContextPath();
			String loginName = (String) session.getAttribute("loginName");
			Account account = (Account) session.getAttribute("account");
			String loginId = account.getAccountId();
			String roleId = (String) session.getAttribute("accountRole");
			String color = null;
			String sheng = (String) session.getAttribute("accountSheng");
			String shi = request.getParameter("shi") != null ? request.getParameter("shi"):"0";
			String xian = request.getParameter("xian") != null ? request.getParameter("xian"):"0";
			String xiaoqu = request.getParameter("xiaoqu") != null ? request.getParameter("xiaoqu"):"0";
			String rgsh = request.getParameter("rgsh");		//站点审核通过   首页传的值
			String rgsh2 = request.getParameter("caiji");	// 采集站点
			String s_curpage = request.getParameter("curpage") != null ? request.getParameter("curpage"): "1";//分页
			int count = 0,curpage = 1,pagesize = 10,nextpage = 1, prepage = 1, allpage = 1, xh = 1;
			String permissionRights = "";
			String whereStr = "";
			String str = "";
			String xuni = "0";
			//遍历抄表人
		    ChaoBiaoRen_new cbr = new ChaoBiaoRen_new();
			ChaoBiaoRenDao_new cbrDao = new ChaoBiaoRenDao_new();
			ArrayList<ChaoBiaoRen_new> al = new ArrayList<ChaoBiaoRen_new>();
			String yeshu = request.getParameter("curpage"); //获取分页查询页数 
			 
			String aid = request.getParameter("accountid")!=null?request.getParameter("accountid"):""; 
			String aname = request.getParameter("accountname")!=null?request.getParameter("accountname"):""; 
			String name = request.getParameter("name")!=null?request.getParameter("name"):""; 
			
			if(yeshu != null && !yeshu.equals("") && !yeshu.equals("0")){  //不为空则重新赋值
				curpage = Integer.valueOf(yeshu).intValue();//强转int
				}
				allpage = cbrDao.COUNT(aid,aname,name,sheng,shi,xian,xiaoqu,roleId,loginId);	//共多少页
				al = cbrDao.FenYe(curpage,aid,aname,name,sheng,shi,xian,xiaoqu,roleId,loginId);
			%>
</script>
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
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<body>
		<form action="" name="form1" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="top">
					<td width="12">
						<img src="../images/space.gif" width="12" height="17" />
					</td>
					<td>
						<div class="content01">
							<div class="tit01">
								抄表人员查询
							</div>
							<div class="content01_1">
								<table width="800px" border="0" cellpadding="1" cellspacing="0"
									class="tb_select">
									<tr>
							<td align="right" width="60px">城市：</td>
							<td align="left" width="124px">
							<select name="shi" id="shi" style="width:124px;" onchange="changeCity()" >
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
								<td align="left" width="124px">
								<select name="xian" id="xian" style="width:124px;" onchange="changeCountry()" class="selected_font">
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
							<td align="left" width="124px">
								<select name="xiaoqu" id="xiaoqu" style="width:124px;" class="selected_font">
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
							<%-- 
							<td align="right" width="60px">抄表人员帐号：</td>
							<td align="left" width="120px">
								<input type="text" name="accountid" id="accountid" value="" style="width:120px" />
							</td>
							--%>
							<td align="right" width="60px">抄表人员登录名：</td>
								<td align="left" width="120px">
								<input type="text" name="accountname" id="accountname" value="" style="width:120px" />
							</td>
							<td align="right" width="60px">抄表人员姓名：</td>
								<td align="left" width="120px">
								<input type="text" name="name" id="name" value="" style="width:120px" />
							</td>
							</tr>
							<tr>
								<td colspan="2">注：绑定操作需要抄表员先分配负责区域</td>
								<td align="right" colspan="6">
									<input type="submit" class="btn_c1" onclick="query()" value="查询" />
								</td>
							</tr>
						</table>

								<div class="tbtitle01">
									<b>抄表员信息查询统计一览</b>
								</div>
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="tb_list1">
									</tr>
									<tr align="center">
									
										<th width="40">
											序号
										</th>
										<th width="150">
											登录名
										</th>
										<th width="150">
											姓名
										</th>
										<th width="150">
											角色名称
										</th>
										<th>
											操作
										</th>
									</tr>
									<%
										int intnum = pagesize * (curpage - 1) + 1;
										if (al != null) {
											int num = 1;
											for (int i = 0; i < al.size(); i++) {
												int accountid = al.get(i).getAccountid();
												String accountname = al.get(i).getAccountname();
												String xmname = al.get(i).getName();
												String password = al.get(i).getPassword();
												String roleid = al.get(i).getRoleid();
												String rolename = al.get(i).getRolename();
												int delsign = al.get(i).getDelsign();

									%>
									<tr align="center">
										<td width="40"><%=intnum++%></td>
										<td width="150"><%=accountname%></td>
										<td width="150"><%=xmname%></td>
										<td width="150"><%=rolename%></td>
										<td width="80">
											<a href="#" onclick="bangding('<%=accountid%>','<%=accountname%>')"><img
													src="../images/icon03.gif" width="16" height="16"
													title="绑定电表管理" /> </a>
										</td>
									</tr>
									<%
										}
									%>

									<!-- 数据加载 End -->
									<tr bgcolor="#ffffff">
										<td colspan="10" align="left">
											<div align="left">

												&nbsp;&nbsp;
												<font color='#1E90FF'> <%
 								if (curpage != 1) {
 										out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
 								} else {
 										out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
 								}
 											%> </font> &nbsp;&nbsp;
												<font color='#1E90FF'> <%
 								if (curpage != 1) {
 										out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
 								} else {
 										out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
 								}
 								%> </font> &nbsp;&nbsp; 转到
									<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)"style="width: 40px; font-family: 宋体; font-size: 12px; line-height: 120%;">
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
									</select>&nbsp;&nbsp;共
									<font color='#1E90FF'><b><%=allpage%></b>&nbsp;</font>页&nbsp;&nbsp;
									<font color='#1E90FF'> 
									<%
 								if (allpage != 0 && (curpage < allpage)) {
 									out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");
 								} else {
 									out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");
 								}
 									%> </font> &nbsp;&nbsp;
									   <font color='#1E90FF'> <%
 								if (allpage != 0 && (curpage < allpage)) {
 									out.print("<a href='javascript:gopagebyno(" + allpage + ")'><img src='../images/page-last.gif'></a>");
 								} else {
 									out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
 								}
									 %> </font> &nbsp;&nbsp;
											</div>
										</td>
									</tr>
								</table>
								<div class="space_h_10"></div>
							</div>
						</div>
					</td>
				</tr>
				<%
					}
				%><!-- else {%>-->
				<!--  <tr align="center" >
			<td align="left" colspan="9">
			当前无数据。
			</td>
		</tr>-->

			</table>
		</form>
		<script>
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
			+ "/web/sdttWeb/chaobiaorenManager/chaobiaorenyuanguanli_new.jsp?rgsh=" + rgsh
			+ "&caiji=" + rgsh2 + "&curpage=" + curpage;
	document.form1.submit();
}
function nextpage() {
	var rgsh2 = '<%=rgsh2%>';
	var rgsh = '<%=rgsh%>';
	document.form1.page.value = parseInt(document.form1.page.value) + 1;
	var curpage = '<%=(curpage + 1)%>';
	document.form1.action = path
			+ "/web/sdttWeb/chaobiaorenManager/chaobiaorenyuanguanli_new.jsp?rgsh=" + rgsh
			+ "&caiji=" + rgsh2 + "&curpage=" + curpage;
	document.form1.submit();
}
function gopagebyno(pageno) {
	var rgsh2 = '<%=rgsh2%>';
	var rgsh = '<%=rgsh%>';
	document.form1.page.value = pageno;
	document.form1.action = path
			+ "/web/sdttWeb/chaobiaorenManager/chaobiaorenyuanguanli_new.jsp?rgsh=" + rgsh
			+ "&caiji=" + rgsh2 + "&curpage=" + pageno;
	document.form1.submit();
}
var XMLHttpReq;
	//XMLHttpReq = createXMLHttpRequest();
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
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
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
function query(){
	document.form1.action=path+"/web/sdttWeb/chaobiaorenManager/chaobiaorenyuanguanli_new.jsp";
}
//绑定方法
function bangding(id,name){
	window.open("../chaobiaorenManager/chaobiaorenbangding.jsp?uuid="+id+"&uuname="+name+"", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
}
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
//document.form1.accountid.value='<=aid%>';
document.form1.accountname.value='<%=aname%>';
document.form1.name.value='<%=name%>';
</script>
</body>
</html>



