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
String addTime = request.getParameter("addTime")!=null?request.getParameter("addTime"):"";
String startTime = request.getParameter("startTime")!=null?request.getParameter("startTime"):"";
String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";

%>
<%--<ul class="tab">
  <li class="first"></li>
  <li class="cur"><a href="">电量管理</a></li>
  <li class="end"></li>
</ul>
--%><table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">PUE分析</div>
				<div class="content01_1">
				<form action="" name="form1" method="post">
					<table width="800px" border="0" cellpadding="1" cellspacing="0" class="tb_select" >
						<tr>
						    
							<td align="right" width="60px">局站名称：</td>
							<td align="left" width="60px">
								<input type="text" name="stationName" value="<%=stationName %>" maxlength="20" style="box-sizing: border-box; width: 130px;"/>
				         	</td>
							<td align="right" width="60px">时间：</td>
							<td align="left" width="60px">
								<input type="text" name="addTime" value="<%=addTime %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" style="box-sizing: border-box; width: 130px;"/><%---
								<input type="text" name="endTime" value="<%=endTime %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" style="width:100px"/>
				         	--%></td>
							<td>
								<input onclick="query()" type="button" class="btn_c1"   value="查询" /> 
							</td>
						</tr>
					</table>
					</form>
					<div class="tbtitle01"><b>PUE分析一览</b></div>
					<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
						<tr align="right">
							<%--<td colspan="10" align="right">
							    <input name="button2" type="button" onclick="add()" class="btn_c1" id="button2" value="新增" />&nbsp;&nbsp;
								<input name="button2" type="submit" onclick="daochu()" class="btn_c1" id="button2" value="导出Excel" />
							</td>--%>
						</tr>
						<tr align="center">
						    <th width="10">序号</th>
						    <th width="120">局站名称</th>
							<th width="120">生成时间</th>
							<th width="120">实际电量</th>
							<th width="120">设备电量</th>
							<th width="120">PUE值</th>
							<%--<th width="10">操作</th>--%>
						</tr>
						<%
							//查询条件
							
							DecimalFormat df1 = new DecimalFormat("0.00");
						DecimalFormat df2 = new DecimalFormat("0.0000");
							int totalRow = commBean.getAnalysisTotalRow(stationName, addTime);
							Page pager = new Page(totalRow, 10);
						    int curpage = pager.getPage();
							if(request.getParameter("page") !=null){
								curpage = Integer.parseInt(request.getParameter("page"));
							}
							pager.setPage(curpage);
							ArrayList eleclist = commBean.getAnalysisPage(stationName, addTime, pager);
							int totalPage = pager.getTotalPage();
							int cnt = 0;
							cnt = (pager.getPage() - 1) * pager.getPageSize() + 1;
							
							String id="", addTime_s="", jzname="", shibaodl="",shebeidl="",puezhi="";
						    int count = ((Integer)eleclist.get(0)).intValue();
							for(int k=count; k<eleclist.size()-1; k+=count){
								id = (String)eleclist.get(k + eleclist.indexOf("ID"));
								jzname = (String)eleclist.get(k + eleclist.indexOf("JZNAME"));
								addTime_s = (String)eleclist.get(k + eleclist.indexOf("ADDTIME"));
								shibaodl = (String)eleclist.get(k + eleclist.indexOf("SHIBAODL"));
								shebeidl = (String)eleclist.get(k + eleclist.indexOf("SHEBEIDL"));
								puezhi = (String)eleclist.get(k + eleclist.indexOf("PUE"));
								if(shibaodl!=null && !shibaodl.equals("")){
									shibaodl = ""+df1.format(Double.valueOf(shibaodl).doubleValue());
								}
								if(shebeidl!=null && !shebeidl.equals("")){
									shebeidl = ""+df1.format(Double.valueOf(shebeidl).doubleValue());
								}
								if(puezhi!=null && !puezhi.equals("")){
									puezhi = ""+df2.format(Double.valueOf(puezhi).doubleValue());
								}
							//	if(shibaodl!=null && !shibaodl.equals("") && shebeidl!=null && !shebeidl.equals("")){
							//		if(shibaodl.equals("0") || shebeidl.equals("0")){
							//			puezhi = "0";
							//		}else{
							//			  try {
							//					double shibaodl_dou = Double.valueOf(shibaodl).doubleValue();
							//					double shebeidl_dou = Double.valueOf(shebeidl).doubleValue();
							//					puezhi = ""+df1.format(shibaodl_dou/shebeidl_dou);
							//				} catch (Exception e) {
							//					puezhi = "";
							//				}
							//		
							//		}
									
							//	}
							
								
						%>
						<!-- 数据加载  Start-->
						<tr align="center">
							<td width="10"><%=cnt++%></td>
							<td align="left"><%=jzname%></td>
							<td align="center"><%=addTime_s%></td>
							<td align="right"><%=shibaodl%></td>
							<td align="right"><%=shebeidl%></td>
							<td align="right"><%=puezhi%></td>
						
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
	document.form1.action= path + "/web/sdttWeb/jichuInfoManager/PUEAnalysis.jsp";
	document.form1.submit();
}


function nextpage(){
	var curPage = "<%=pager.getNextPage() %>";
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/PUEAnalysis.jsp?page="+curPage;
	document.form1.submit();
}
function previouspage(){
	var curPage = "<%=pager.getPreviousPage() %>";
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/PUEAnalysis.jsp?page="+curPage;
	document.form1.submit();
}
function gopagebyno(curPage){
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/PUEAnalysis.jsp?page="+curPage;
	document.form1.submit();
}

</script>
</body>
</html>