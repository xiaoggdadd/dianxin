<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ptac.app.util.Page, com.ptac.app.station.dao.*, com.ptac.app.station.bean.*"%>
<%@page import="java.util.*"%>
<%@page import="com.noki.mobi.common.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%String path = request.getContextPath(); %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/web/sdttWeb/css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/javascript/tx.js"></script></head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
<body>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<%
String loginName = (String)session.getAttribute("loginName");
Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
String roleId = (String)session.getAttribute("accountRole");
String sheng = (String)session.getAttribute("accountSheng");
String agcode1="0";
if(request.getParameter("shi")==null){
	ArrayList shilist = new ArrayList();
	shilist = commBean.getAgcode(sheng,account.getAccountId());
	if(shilist!=null){
  		int scount = ((Integer)shilist.get(0)).intValue();
  		//[2, AGCODE, AGNAME] update by guol2017112
		if(shilist.size()>(scount + 1)){
       		agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
		}
    }
}
String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
String stationName = request.getParameter("stationName")!=null?request.getParameter("stationName"):"";
String dianbiaoName = request.getParameter("dianbiaoName")!=null?request.getParameter("dianbiaoName"):"";
String startTime = request.getParameter("startTime")!=null?request.getParameter("startTime"):"";
String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";

%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">预提金额</div>
				<div class="content01_1">
				<form action="" name="form1" method="post">
					<table width="800px" border="0" cellpadding="1" cellspacing="0" class="tb_select" >
						<tr>
						    
							<td align="right" width="60px">局站名称：</td>
							<td align="left" width="60px">
								<input type="text" name="stationName" value="<%=stationName %>" maxlength="20" style="box-sizing: border-box; width: 130px;"/>
				         	</td>
				         	<td align="right" width="60px">电表名称：</td>
							<td align="left" width="60px">
								<input type="text" name="dianbiaoName" value="<%=dianbiaoName %>" maxlength="20" style="box-sizing: border-box; width: 130px;"/>
				         	</td>
							<td align="right" width="60px">生成时间：</td>
							<td align="left" width="60px">
								<input type="text" name="startTime" value="<%=startTime %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" style="box-sizing: border-box; width: 130px;"/>-
								<input type="text" name="endTime" value="<%=endTime %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" style="box-sizing: border-box; width: 130px;"/>
				         	</td>
							<td>
								<input onclick="query()" type="button" class="btn_c1"   value="查询" /> 
							</td>
						</tr>
					</table>
					</form>
					<div class="tbtitle01"><b>预提金额</b></div>
					<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
						<tr align="right">
							<td colspan="10" align="right">
							    <input name="button2" type="button" onclick="add()" class="btn_c1" id="button2" value="新增" />&nbsp;&nbsp;
								<%--<input name="button2" type="submit" onclick="addAll()" class="btn_c1" id="button2" value="全部新增" />
							--%></td>
						</tr>
						<tr align="center">
						    <th width="10">序号</th>
						    <th width="120">电表名称</th>
						    <th width="120">局站名称</th>
							<th width="120">生成时间</th>
							<th width="120">预提天数</th>
							<th width="120">单价</th>
							<th width="120">金额</th>
							<th width="120">预提人员</th>
							<%--<th width="10">操作</th>--%>
						</tr>
						<%
							//查询条件
							
							DecimalFormat df1 = new DecimalFormat("0.00");
							int totalRow = commBean.getAdvanceTotalRow(stationName,dianbiaoName,startTime,endTime);
							Page pager = new Page(totalRow, 10);
						    int curpage = pager.getPage();
							if(request.getParameter("page") !=null){
								curpage = Integer.parseInt(request.getParameter("page"));
							}
							pager.setPage(curpage);
							ArrayList eleclist = commBean.getAdvancePage(stationName,dianbiaoName,startTime,endTime,pager);
							int totalPage = pager.getTotalPage();
							int cnt = 0;
							cnt = (pager.getPage() - 1) * pager.getPageSize() + 1;
							
							String ID="", DBID="", ADVTIME="", ONEID="",TWOID="",MONEY="";
							String PRICE="", DAYNUM="", ACCOUNTID="", DBNAME="",JZNAME="",ACCOUNTNAME="";
						    int count = ((Integer)eleclist.get(0)).intValue();
							for(int k=count; k<eleclist.size()-1; k+=count){
								ID = (String)eleclist.get(k + eleclist.indexOf("ID"));
								DBID = (String)eleclist.get(k + eleclist.indexOf("DBID"));
								ADVTIME = (String)eleclist.get(k + eleclist.indexOf("ADVTIME"));
								ONEID = (String)eleclist.get(k + eleclist.indexOf("ONEID"));
								TWOID = (String)eleclist.get(k + eleclist.indexOf("TWOID"));
								MONEY = (String)eleclist.get(k + eleclist.indexOf("MONEY"));
								PRICE = (String)eleclist.get(k + eleclist.indexOf("PRICE"));
								DAYNUM = (String)eleclist.get(k + eleclist.indexOf("DAYNUM"));
								ACCOUNTID = (String)eleclist.get(k + eleclist.indexOf("ACCOUNTID"));
								DBNAME = (String)eleclist.get(k + eleclist.indexOf("DBNAME"));
								JZNAME = (String)eleclist.get(k + eleclist.indexOf("JZNAME"));
								ACCOUNTNAME = (String)eleclist.get(k + eleclist.indexOf("ACCOUNTNAME"));
						%>
						<!-- 数据加载  Start-->
						<tr align="center">
							<td width="10"><%=cnt++%></td>
							<td align="left"><%=DBNAME%></td>
							<td align="left"><%=JZNAME%></td>
							<td align="center"><%=ADVTIME%></td>
							<td align="center"><%=DAYNUM%></td>
							<td align="right"><%=PRICE%></td>
							<td align="right"><%=MONEY%></td>
							<td align="center"><%=ACCOUNTNAME%></td>
						
						</tr>
						<%} %>
						<!-- 数据加载 End -->
						<tr bgcolor="#ffffff"  >
							<td colspan="16" >
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
							  	        转到 <select name="page" onchange="javascript:gopagebyno(this.value)" style="width:40px;font-family: 宋体;font-size:12px;line-height:120%;" >
							  	   <%
								  	   for (int i = 1; i <= totalPage; i++) {
							     			if (curpage == i) {
							     				out.print("<option value='" + i
							     						+ "' selected='selected'>" + i + "</option>");
							     			} else {
							     				out.print("<option value='" + i + "'>" + i
							     						+ "</option>");
							     			}
							     		}
							  	 %>
					    		  </select>&nbsp;&nbsp;共 <font color='#1E90FF'><b><%=totalPage %></b>&nbsp;</font>页 &nbsp;&nbsp;
								  <font color='#1E90FF'>
								 <%
							     	if (totalPage != 0 && (curpage < totalPage)) {
							     		out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");
						     		}
							    %>
            					</font>
								&nbsp;&nbsp;							
								<font color='#1E90FF'>
								<%
							     	if (totalPage != 0 && (curpage < totalPage)) {
							     		out.print("<a href='javascript:gopagebyno(" + totalPage+ ")'><img src='../images/page-last.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
						     		}
							     %>
	            				</font>
								</div>
							</td>
						</tr>
					</table>
					<div class="space_h_10"></div>
				</div>
			</div>
		</td>
	</tr>
</table>
<script type="text/javascript">
var path = "${pageContext.request.contextPath}";

function query(){
	document.form1.action= path + "/web/sdttWeb/jichuInfoManager/advance.jsp";
	document.form1.submit();
}


function nextpage(){
	var curPage = "<%=pager.getNextPage() %>";
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/advance.jsp?page="+curPage;
	document.form1.submit();
}
function previouspage(){
	var curPage = "<%=pager.getPreviousPage() %>";
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/advance.jsp?page="+curPage;
	document.form1.submit();
}
function gopagebyno(curPage){
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/advance.jsp?page="+curPage;
	document.form1.submit();
}
function add(){
	window.open("../jichuInfoManager/addAdvance.jsp", "newwindow", "height=400, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
}
</script>
</body>
</html>