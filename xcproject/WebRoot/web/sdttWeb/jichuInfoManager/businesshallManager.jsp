<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ptac.app.util.Page, com.ptac.app.businesshall.dao.*, com.ptac.app.businesshall.bean.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/web/sdttWeb/css/content.css" rel="stylesheet" type="text/css" />
</head>

<body>
<ul class="tab">
  <li class="first"></li>
  <li class="cur"><a href="">实体管理</a></li>
  <li class="end"></li>
</ul>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">实体信息</div>
				<div class="content01_1">
				<form action="" name="form1" method="post">
					<table width="800px" border="0" cellpadding="1" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="60px">实体名称：</td>
							<td align="left" width="60px">
								<input type="text" name="entityName" style="width:100px"/>
				         	</td>
			         		<td align="right" width="60px">实体编号：</td>
							<td align="left" width="60px">
								<input type="text" name="entityCode" style="width:100px"/>
				         	</td>
				         	<td align="right" width="60px">分公司名称：</td>
							<td align="left" width="60px">
								<input type="text" name="company" style="width:100px"/>
							</td>
							<td align="right" width="60px">责任人：</td>
							<td align="left" width="60px">
								<input type="text" name="personLiable" style="width:100px"/>
							</td>
						</tr>
						<tr>
							<td align="right" width="60px">实体类型：</td>
							<td align="left" width="60px">
								<select name="entityType" style="width:100px">
									<option value="">请选择</option>
									<option value="机楼">机楼</option>
								</select>
				         	</td>
			         		<td align="right" width="60px">外围系统编码：</td>
							<td align="left" width="60px">
								<input name="peripheralSystemCode" style="width:100px"/>
				         	</td>
				         	<td align="right" width="60px">状态：</td>
							<td align="left" width="60px">
								<select name="status" style="width:100px">
									<option value="">请选择</option>
									<option value="1">闲置</option>
									<option value="2">停用</option>
									<option value="3">在用</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td align="right" colspan="8">
								<input onclick="query()" type="button" class="btn_c1"   value="查询" /> 
							</td>
						</tr>
					</table>
					</form>
					<div class="tbtitle01"><b>实体查询统计一览</b></div>
					<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
						<tr align="right">
							<td colspan="10" align="right">
							    <input name="button2" type="button" onclick="add()" class="btn_c1" id="button2" value="新增" />&nbsp;&nbsp;
								<input name="button2" type="submit" onclick="exportExcel()" class="btn_c1" id="button2" value="导出Excel" />
							</td>
						</tr>
						<tr align="center">
						    <th width="10">序号</th>
							<th width="120">分公司</th>
							<th width="100">实体编号</th>
							<th width="80">实体名称</th>
							<th width="80">实体类型</th>
							<th width="80">坐落地点</th>
							<th width="80">外围系统编码</th>
							<th width="80">责任人</th>
							<th width="80">状态</th>
							<th>操作</th>
						</tr>
						<%
							//查询条件
							Map<String, String> map = new HashMap<String, String>();
								String entityName = request.getParameter("entityName");
							if(request.getParameter("entityName")!=null){
								map.put("entityName", entityName);
							}
							if(request.getParameter("entityCode")!=null){
								String entityCode = request.getParameter("entityCode");
								map.put("entityCode", entityCode);
							}
							if(request.getParameter("company")!=null){
								String company = request.getParameter("company");
								map.put("company", company);
							}
							if(request.getParameter("personLiable")!=null){
								String personLiable = request.getParameter("personLiable");
								map.put("personLiable", personLiable);
							}
							if(request.getParameter("entityType")!=null){
								String entityType = request.getParameter("entityType");
								map.put("entityType", entityType);
							}
							if(request.getParameter("peripheralSystemCode")!=null){
								String peripheralSystemCode = request.getParameter("peripheralSystemCode");
								map.put("peripheralSystemCode", peripheralSystemCode);
							}
							if(request.getParameter("status")!=null){
								String status = request.getParameter("status");
								map.put("status", status);
							}
							BusinesshallDao dao = new BusinesshallDaoImpl();
							int totalCount = dao.getTotalCount(map);
							Page pager = new Page(totalCount, 10);
						    int curpage = pager.getPage();
							if(request.getParameter("page") !=null){
								curpage = Integer.parseInt(request.getParameter("page"));
							}
							List<Businesshall> list = dao.getPageData(curpage, 10, map);
							int totalPage = pager.getTotalPage();
							int cnt = 1;
							for(int i=0; i<list.size(); i++){
								Businesshall businesshall = list.get(i);
						%>
						<!-- 数据加载  Start-->
						<tr align="center">
							<td width="10"><%=cnt++ %></td>
							<td align="left"><%=businesshall.getCompany()%></td>
							<td align="left"><%=businesshall.getEntityCode()%></td>
							<td align="center"><%=businesshall.getEntityName()%></td>
							<td align="center"><%=businesshall.getEntityType()%></td>
							<td align="right"><%=businesshall.getAddress()%></td>
							<td align="center"><%=businesshall.getPeripheralSystemCode()%></td>
							<td align="center"><%=businesshall.getPersonLiable()%></td>
							<td align="center">
							<%
								if(businesshall.getStatus()==1){
									out.print("闲置");
								}else if(businesshall.getStatus()==2){
									out.print("停用");
								}else if(businesshall.getStatus()==3){
									out.print("在用");
								}else{
									out.print("");
								}
							%>
							</td>
							<td width="80">
								<%--<a href="#" onclick="show('<%=businesshall.getId() %>')"><img src="../images/lefticon3.png" width="16" height="16"  title="详细" /></a> --%>
								<a href="#" onclick="edit('<%=businesshall.getId() %>')"><img src="../images/accept.png" width="16" height="16"  title="修改" /></a>
								<a href="#" onclick="remove('<%=businesshall.getId() %>')"><img src="../images/delete.png" width="16" height="16"  title="删除" /></a>
							</td>
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
function add(){
	window.open("addBusinesshall.jsp", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
}
function query(){
	document.form1.action= path + "/web/sdttWeb/jichuInfoManager/businesshallManager.jsp";
	document.form1.submit();
}
function edit(id){
	window.open("modifyBusinesshall.jsp?id="+id, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
}
function remove(id){
	if (confirm("确定要删除此实体么！")) {
		window.location.href=path + "/servlet/businesshallServlet?action=del&id="+id;
	} else {
		return;
	}
}
function show(id){
	
}
function nextpage(){
	var curPage = "<%=pager.getNextPage() %>";
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/businesshallManager.jsp?page="+curPage;
	document.form1.submit();
}
function previouspage(){
	var curPage = "<%=pager.getPreviousPage() %>";
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/businesshallManager.jsp?page="+curPage;
	document.form1.submit();
}
function gopagebyno(curPage){
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/businesshallManager.jsp?page="+curPage;
	document.form1.submit();
}
</script>
</body>
</html>