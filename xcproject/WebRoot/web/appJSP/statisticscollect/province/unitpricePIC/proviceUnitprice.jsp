<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean"%>
<%@ page
	import="com.ptac.app.statisticcollect.province.unitpricePIC.bean.*"%>

<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
	String color = null;
	String bargainid = "";
	int intnum = 0;
	String sheng = (String) session.getAttribute("accountSheng");
	String agcode1 = "";
	if (request.getParameter("shi") == null) {
		ArrayList<?> shilist = new ArrayList();
		CommonBean commBean = new CommonBean();
		shilist = commBean.getAgcode(sheng, account.getAccountId());
		if (shilist != null) {
			int scount = ((Integer) shilist.get(0)).intValue();
			agcode1 = (String) shilist.get(scount
					+ shilist.indexOf("AGCODE"));
		}
	}
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : agcode1;

	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String permissionRights = "";
	String roleId = (String) session.getAttribute("accountRole");
%>

<html>
	<head>
		<title></title>
		<style>
.style1 {
	color: red;
	font-weight: bold;
}

.STYLE6 {
	color: #FFFFFF;
}

.memo {
	border: 1px #C8E1FB solid
}

.style7 {
	font-weight: bold
}

.memo {
	border: 1px #888888 solid
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height: 23px
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}

.selected_font {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
}

.bttcn {
	color: BLACK;
	font-weight: bold;
}

.form {
	width: 130px
}

#id1 {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :               DXImageTransform.Microsoft.Gradient ( 
		       
		     GradientType =               0, StartColorStr =              
		#ffffff, EndColorStr =    
		    
		     #D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}
</style>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script src="<%=path%>/web/appJS/pageJs/someJs/page.js">
</script>
		<script src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script language="javascript">
var path = '<%=path%>';

function daochu() {
	document.form1.action = "<%=path%>/servlet/ProUnitpriceDaoChuServlet";
	document.form1.submit();
}



function queryDegree() {
	var qssj = document.form1.bzyf1.value;
	var jssj = document.form1.bzyf2.value;
	
	if(qssj==""||qssj==null){
		alert("请输入报账月份！");
		return;
	}
	if(jssj==""||jssj==null){
		alert("请输入报账月份！");
		return;
	}
	
	qssj = qssj+"-01";
	jssj = jssj+"-01";
	dt1 = qssj.replace(/-/g,"/");
	dt2 = jssj.replace(/-/g,"/");
	var day = (new Date(dt2)-new Date(dt1))/(1000*60*60*24)
	var zq = day*1+1*1;
	if(zq<0){
		alert("起始时间和结束时间输入有误！");
		return
	}
	
	document.form1.action = "<%=path%>/servlet/ProUnitpriceServlet";
	document.form1.submit();
	showdiv("请稍等..............");
}

$(function() {

	$("#daochu").click(function() {
		daochu();

	});
	$("#chaxun").click(function() {
		queryDegree();
	});
});
</script>

	</head>

	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<%
		permissionRights = commBean.getPermissionRight(roleId, "0302");
	%>
	<body class="body">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
				<tr>
					<td colspan="4" width="50">
						<div style="width: 700px;">
							<img alt="" src="<%=path%>/images/btt.bmp" width="100%"
								height="100%" />
							<span
								style="font-size: 14px; font-weight: bold; position: absolute; left: 25px; top: 15px; color: black">地市每月平均单价</span>
						</div>
					</td>
				</tr>
				<tr>
					<td height="20" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;过滤条件&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="1200px">
						<table>
							<tr class="form_label">
								<td>
									城市：
									<input type="hidden" name="loginID" value="<%=loginId %>" />
								</td>
								<td>
									<select name="shi" class="selected_font" id="shi"
										onchange="city(this.value,'document.all.xian')">
										<option value="">
											全部
										</option>
										<%
											ArrayList shilist = new ArrayList();
											shilist = commBean.getAgcode(sheng, account.getAccountId());
											if (shilist != null) {
												String agcode = "", agname = "";
												int scount = ((Integer) shilist.get(0)).intValue();
												for (int i = scount; i < shilist.size() - 1; i += scount) {
													agcode = (String) shilist
															.get(i + shilist.indexOf("AGCODE"));
													request.setAttribute("shicd", agcode);
													agname = (String) shilist
															.get(i + shilist.indexOf("AGNAME"));
										%>
										<option value="<%=agcode%>"
											<c:choose>
							<c:when test="${bean.shi ==null}">
							</c:when>
							<c:otherwise >
								<c:if test="${bean.shi eq shicd}">selected</c:if>
							</c:otherwise>
							</c:choose>><%=agname%></option>
										<%
											}
											}
										%>
									</select>&nbsp;
								</td>
								<td>
									报账月份
								</td>
								<td>
									<input type="text" name="bzyf1" value="${bean.bzyf1}"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
										class="selected_font" />
									至
									<input type="text" name="bzyf2" value="${bean.bzyf2}"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
										class="selected_font" />
									&nbsp;
								</td>
								<td>
										电费审核状态
									</td>
									<td>
										<select name="dfshzt" class="selected_font">
											<option value="1" <c:if test="${bean.dfshzt eq '1'}">selected </c:if>>
											
												市级审核通过
											</option>
											<option value="2" <c:if test="${bean.dfshzt eq '2'}">selected </c:if>>
												人工审核通过
											</option>
											<option value="3" <c:if test="${bean.dfshzt eq '3'}">selected </c:if>>
												财务审核通过
											</option>
										</select>
									</td>
									<td>
										<%
											if (permissionRights.indexOf("PERMISSION_SEARCH") >= 0) {
										%>
										<div id="chaxun"
											style="position: relative; width: 59px; height: 23px; right: -180px; cursor: pointer; TOP: 0PX">
											<img alt="" src="<%=path%>/images/chaxun.png" width="100%"
												height="100%" />
											<span
												style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
										</div>
										<%
											}
										%>
									</td>
							</tr>
							<tr class="form_label">
								<td>
									站点启用状态
								</td>
								<td>
									<select class="selected_font" name="zdqyzt">
										<option value="" <c:if test="${bean.zdqyzt eq ''}">selected </c:if>>
											全部
										</option>
										<option value="1" <c:if test="${bean.zdqyzt eq '1'}">selected </c:if>>
											启用
										</option>
										<option value="0" <c:if test="${bean.zdqyzt eq '0'}">selected </c:if>>
											关闭
										</option>
									</select>
									&nbsp;
								</td>
								<td>
									电表启用状态
								</td>
								<td>
									<select class="selected_font" name="dbqyzt">
										<option value="" <c:if test="${bean.dbqyzt eq ''}">selected </c:if>>
											全部
										</option>
										<option value="1" <c:if test="${bean.dbqyzt eq '1'}">selected </c:if>>
											启用
										</option>
										<option value="0" <c:if test="${bean.dbqyzt eq '0'}">selected </c:if>>
											关闭
										</option>
									</select>
								</td>
							</tr>
						</table>

						<table height="23">
							<tr>
								<td height="5" colspan="4">
									<div id="parent" style="display: inline">
										<div style="width: 50px; display: inline;">
											<hr>
										</div>
										<font size="2">&nbsp;信息列表&nbsp;</font>
										<div style="width: 300px; display: inline;">
											<hr>
										</div>
									</div>
								</td>
							</tr>
						</table>
						<div
							style=" overflow-x: auto; overflow-y: auto; border: 1px">
							
							<table width="100%" height="60%" border="0" cellspacing="1"
								cellpadding="1" bgcolor="#cbd5de" class="form_label">
								<% List<String> monls = (List<String>)request.getAttribute("month");
							List<ProUnitpriceBean> ls =(List<ProUnitpriceBean> )request.getAttribute("ls");
							if(monls!=null&&ls!=null){
								request.getSession().setAttribute("list",ls);
								request.getSession().setAttribute("mon",monls);
								
							%>
								<tr height="10" class="relativeTag">
									<td height="23" bgcolor="#DDDDDD">
										<div align="center" class="bttcn">
											市
										</div>
									</td>
									<%
									
									for(int i = 0;i<monls.size();i++){
										%>
									<td  height="23" bgcolor="#DDDDDD">
										<div align="center" class="bttcn">
											<%=monls.get(i) %>
										</div>
									</td>	
										<%
									} 
								%>
								</tr>
								
								<% for(int i = 0;i < ls.size();i++){
									%>
								<tr>
									<td height="23" bgcolor="#FFFFFF">
									<div align="center" >
											<%=ls.get(i).getShi() %>
											</div>
									</td>
									
									<%for(int s = 0;s<monls.size();s++){
										int boo = 1;//标志位：用来判断是否有符合条件的数据
										for(int j = 0;j<ls.get(i).getLs().size();j++){
											if(monls.get(s).equals(ls.get(i).getLs().get(j).getBzyf())){//这两个双重for循环只是为了将数据集里面的报账月份和
												%>
												<td  height="23" bgcolor="#FFFFFF">
												<div align="center" >
														<%=ls.get(i).getLs().get(j).getUnitprice() %>
														</div>
												</td>	
												<%
												boo = 2;
												break;
											}
										}
										if(boo==1){
											%>
											<td  height="23" bgcolor="#FFFFFF">
											<div align="center" ></div>
											</td>	
											<%
										}									} %>
									
								</tr>
									<%
									} 
								}else{%>
								<tr height="10" class="relativeTag">
									<td height="23" bgcolor="#DDDDDD">
										<div align="center" class="bttcn">
											市
										</div>
									</td>
								<td height="23" bgcolor="#DDDDDD">
										<div align="center" class="bttcn">
											月份
										</div>
									</td>
									<%
									
									for(int j=0;j<16;j++){
		            					if(j%2==0){
		            						color="#FFFFFF"	;
		            					}else{
		            						color="#DDDDDD";
		            					}
									
									%>
									
									<tr bgcolor="<%=color%>">
        				
            			<td>&nbsp;</td>  
            			<td>&nbsp;</td> 

        			</tr>
									
									<%
									
								}%>
								
								<%} %>
								
							</table>

						</div>

						<div style="width: 100%; height: 8%; border: 1px">
							<table width="100%" height="20%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td align="right" height="19" colspan="4">
										<div id="parent" style="display: inline">
											<div style="width: 50px; display: inline;">
												<hr>
											</div>
											<div style="width: 300px; display: inline;">
												<hr>
											</div>
										</div>
									</td>
								</tr>

								<tr>
									<td align="right">
										<%
											if (permissionRights.indexOf("PERMISSION_ADD") >= 0) {
										%>

										<div id="daochu"
											style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 4px;">
											<img alt="" src="<%=path%>/images/daoru.png" width="100%"
												height="100%" />
											<span
												style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导出</span>
										</div>
										<%
											}
										%>

									</td>
								</tr>
								<tr>
								</tr>
							</table>
						</div>
			</table>
		<!-- 	<br />
				<%
					if(monls!=null&&ls!=null){
						%>
						<img src="<%=path%>/servlet/ProPicServlet" >
						<%
					}
				request.removeAttribute("month");
				request.removeAttribute("ls");
				%>
			
			<br />    -->
		</form>
	</body>
</html>
