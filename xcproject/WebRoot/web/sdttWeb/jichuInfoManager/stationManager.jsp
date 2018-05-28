<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ptac.app.util.Page, com.ptac.app.station.dao.*, com.ptac.app.station.bean.*"%>
<%@page import="java.util.*"%>
<%@page import="com.noki.mobi.common.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%String path = request.getContextPath(); %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/web/sdttWeb/css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/javascript/tx.js"></script>
</head>
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
String accountname = request.getParameter("accountname")!=null?request.getParameter("accountname"):"";//审核人

String stationType = request.getParameter("stationType")!=null?request.getParameter("stationType"):"";	//实体类型
String ascriptionUnit = request.getParameter("ascriptionUnit")!=null?request.getParameter("ascriptionUnit"):"";	//所归属经营单元
String power = request.getParameter("power")!=null?request.getParameter("power"):"";	//供电方式
String approveStatus = request.getParameter("approveStatus")!=null?request.getParameter("approveStatus"):"";	//审核状态
%>
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
						    <td align="right" width="60px">地市：</td>
							<td align="left" width="60px">
							<select name="shi" id="shi" style="box-sizing: border-box; width: 130px;" onchange="changeCity()">
	         					<option value="0">请选择</option>
				         		<%
				         			ArrayList shilist = new ArrayList();
				         			shilist = commBean.getAgcode(sheng, shi, loginName);
				         			if (shilist != null) {
				         				String agcode = "", agname = "";
				         				int scount = ((Integer) shilist.get(0)).intValue();
				         				for (int i = scount; i < shilist.size() - 1; i += scount) {
					         			 	agcode = (String) shilist.get(i + shilist.indexOf("AGCODE"));
					         				agname = (String) shilist.get(i + shilist.indexOf("AGNAME"));
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
					         					agcode = (String) xianlist.get(i + xianlist.indexOf("AGCODE"));
					         					agname = (String) xianlist.get(i + xianlist.indexOf("AGNAME"));
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
							<td align="right" width="60px">实体名称：</td>
							<td align="left" width="60px">
								<input type="text" name="stationName" style="box-sizing: border-box; width: 130px;"/>
				         	</td>
						</tr>
							<tr>
							<td align="right" width="100px">实体类型：</td>
							<td width="100px">
								<select name="stationType" style="box-sizing: border-box; width: 130px;">
									<option value="">请选择</option>
									<%
									ArrayList etlist = commBean.getSelctOptions("substationtype");
									if(etlist !=null){
										String code = "", name="";
										int gcount = ((Integer)etlist.get(0)).intValue();
										for(int i=gcount; i<etlist.size()-1; i+=gcount){
											code = (String)etlist.get(i + etlist.indexOf("CODE"));
											name = (String)etlist.get(i + etlist.indexOf("NAME"));
									%>
									  <option value="<%=code %>"><%=name %></option>
									<% 
										}
									}
									%>
								</select>
							</td>
							<td align="right" width="100px">所归属经营单元：</td>
							<td width="100px">
								<input type="text" name="ascriptionUnit" maxlength="20" style="box-sizing: border-box; width: 130px;"/>
							</td>
							<td align="right" width="100px">供电方式：</td>
							<td width="100px">
								<select name="power" style="box-sizing: border-box; width: 130px;">
									<option value="">请选择</option>
									<%
									ArrayList gdfslist = commBean.getSelctOptions("GDFS");
									if(gdfslist !=null){
										String code = "", name="";
										int gcount = ((Integer)gdfslist.get(0)).intValue();
										for(int i=gcount; i<gdfslist.size()-1; i+=gcount){
											code = (String)gdfslist.get(i + gdfslist.indexOf("CODE"));
											name = (String)gdfslist.get(i + gdfslist.indexOf("NAME"));
									%>
									  <option value="<%=name %>"><%=name %></option>
									<% 
										}
									}
									%>
								</select>
							</td>
							<td align="right" width="100px">审核状态：</td>
							<td>
							<select name="approveStatus" style="box-sizing: border-box; width: 130px;">
							<option value="">请选择</option>
							<option value="0">审核中</option>
							<option value="2">未上报</option>
							<option value="1">已通过</option>
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
								<input name="button2" type="submit" onclick="daochu()" class="btn_c1" id="button2" value="导出Excel" />
							</td>
						</tr>
						<tr align="center">
						    <th width="10">序号</th>
							<th width="120">实体名称</th>
							<th width="100">地市</th>
							<th width="80">区县</th>
							<th width="80">实体类型</th>
							<th width="80">所归属经营单元</th>
							<th width="80">实体编码</th>
							<th width="80">供电方式</th>
							<th width="80">审核状态</th>
							<th>操作</th>
						</tr>
						<%
							//查询条件
							StationQuery query = new StationQuery();
						query.setAccountId(account.getAccountId());
						query.setShi(shi);
						query.setXian(xian);
						query.setXiaoqu(xiaoqu);
						query.setStationname(stationName);
						query.setStationType(stationType);		//实体类型
						query.setAscriptionUnit(ascriptionUnit);//所属经营单元
						query.setPower(power);					//供电方式
						query.setApproveStatus(approveStatus);	//审核状态
						
						query.setCollectMode("1");
							StationDao dao = new StationDaoImpl();
							int totalRow = dao.findTotalRow(query);
							Page pager = new Page(totalRow, 10);
						    int curpage = pager.getPage();
							if(request.getParameter("page") !=null){
								curpage = Integer.parseInt(request.getParameter("page"));
							}
							pager.setPage(curpage);
							List<Station> list = dao.findPage(query, pager);
							int totalPage = pager.getTotalPage();
							int cnt = 0;
							cnt = (pager.getPage() - 1) * pager.getPageSize() + 1;
							for(int i=0; i<list.size(); i++){
								Station station = list.get(i);
								String approveStatusDes="";
								if(station.getApproveStatus().equals("0")){
									//approveStatusDes="审核中";
								//approveStatusDes=(String)list.get(i+list.indexOf("accountname"));
									approveStatusDes=station.getAccountname();

								}else if(station.getApproveStatus().equals("2")){
									approveStatusDes="未上报";
								}else if(station.getApproveStatus().equals("1")){
									approveStatusDes="已通过";
								}
						%>
						<!-- 数据加载  Start-->
						<tr align="center">
							<td width="10"><%=cnt++%></td>
							<td align="left"><%=station.getJzName()%></td>
							<td align="left"><%=station.getShi()%></td>
							<td align="center"><%=station.getXian()%></td>
							<td align="center"><%=station.getStationType()%></td>
							<td align="right"><%=station.getAscriptionUnit()%></td><%--
							<td align="center"><%=station.getStationFullCode()%></td>
							--%>
							<td align="center"><%=station.getZdcode()%></td>
							<td align="center"><%=station.getPower()%></td>
							<td align="center"><%=approveStatusDes%></td>
							<td width="80">
								<a href="#" onclick="ch('<%=station.getId() %>','<%=station.getApproveStatus() %>')"><img src="../images/ch.png" width="16" height="16"  title="撤回" /></a>																	
								<%--<a href="#" onclick="show('<%=businesshall.getId() %>')"><img src="../images/lefticon3.png" width="16" height="16"  title="详细" /></a> --%>
								<a href="#" onclick="edit('<%=station.getId() %>','<%=station.getApproveStatus() %>')"><img src="../images/accept.png" width="16" height="16"  title="修改" /></a>
								<a href="#" onclick="tj('<%=station.getId() %>','<%=station.getApproveStatus() %>')"><img src="../images/lefticon_01.png" width="16" height="16"  title="提交审批" /></a>
								<a href="#" onclick="del('<%=station.getId() %>','<%=station.getApproveStatus() %>')"><img src="../images/delete.png" width="16" height="16"  title="删除" /></a>
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
	window.open("addStation.jsp", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");
}
function query(){
	document.form1.action= path + "/web/sdttWeb/jichuInfoManager/stationManager.jsp";
	document.form1.submit();
}
function edit(id,s){
	if(s==0&&s!="2"){
		alert("数据审核中，无法进行此操作");
		return;
		
	}
	 
	window.open("modifyStation.jsp?id="+id+"&approvestatus="+s, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");
}
//新增撤回功能
     function ch(id,s){	
    	 //alert("chegui："+id);
    	 if(s!=0){   		   
    			alert("对不起！您只能撤回审核中的数据！");
        			return;   			
    		}
     
		if(confirm("您确认要撤回本条信息？")){
		     		document.form1.action=path+"/servlet/dianbiao?action=chbgyy&id="+id;
		      		document.form1.submit();
		      		showdiv("请稍等..............");
		      	}
     }  
function del(id,s){
	if(s!="2"){
		alert("数据审核中或已通过，无法进行此操作");
		return;
		
	}
	if (confirm("确定要删除此实体么！")) {
		window.location.href=path + "/servlet/stationServlet?action=del&id="+id;
	} else {
		return;
	}
}
function tj(id,s){
	if(s!="2"){
		alert("数据审核中或已通过，无法进行此操作");
		return;
		
	}
	window.open("stationSub.jsp?id="+id+"&approvestatus="+s, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");
}
function show(id){
	
}
//城市级联--市
function changeCity(){
	var sid=document.form1.shi.value;
	if(sid=="0"){
		var shilist=document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return; 
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
//城市级联--县
function changeCountry(){
	var sid=document.form1.xian.value;
	if(sid=="0"){
		var shilist=document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return; 
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
//导出数据
function daochu(){
	document.form1.action=path+"/servlet/CbDownload?bz=yingyeting";
	document.form1.submit();
}
function nextpage(){
	var curPage = "<%=pager.getNextPage() %>";
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/stationManager.jsp?page="+curPage;
	document.form1.submit();
}
function previouspage(){
	var curPage = "<%=pager.getPreviousPage() %>";
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/stationManager.jsp?page="+curPage;
	document.form1.submit();
}
function gopagebyno(curPage){
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/stationManager.jsp?page="+curPage;
	document.form1.submit();
}
document.form1.shi.value='<%=shi%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
document.form1.xian.value='<%=xian%>';
document.form1.stationName.value='<%=stationName%>';
document.form1.stationType.value='<%=stationType%>';
document.form1.ascriptionUnit.value='<%=ascriptionUnit%>';
document.form1.power.value='<%=power%>';
document.form1.approveStatus.value='<%=approveStatus%>';
</script>
</body>
</html>