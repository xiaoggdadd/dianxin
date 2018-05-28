<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ptac.app.util.Page, com.ptac.app.station.dao.*, com.ptac.app.station.bean.*"%>
<%@page import="java.util.*"%>
<%@page import="com.noki.mobi.common.*"%>
<%@page import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/web/sdttWeb/css/content.css" rel="stylesheet" type="text/css" />
<!-- 显示时间input控件 -->
<%	String path = request.getContextPath(); %>
<script type="text/javascript"src="<%=path%>/javascript/PopupCalendar.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript"src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<!-- fxl 2018/1/11(时间控件) -->
<script type="text/javascript" src="${pageContext.request.contextPath }/javascript/tx.js"></script></head>
<body>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="bean" scope="page"
		class="com.noki.mobi.workflow.javabean.WorkFlowBean">
	</jsp:useBean>
<%
String loginName = (String)session.getAttribute("loginName");
Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
String roleId = (String)session.getAttribute("accountRole");
String sheng = (String)session.getAttribute("accountSheng");
String agcode1="";
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
String shitileixing = request.getParameter("shitileixing") != null ? request.getParameter("shitileixing"): "";		//获取页面实体类型
String jingyingdanwei = request.getParameter("jingyingdanwei") != null ? request.getParameter("jingyingdanwei"): "";//获取页面所属经营单位
String baosongren = request.getParameter("baosongren") !=null? request.getParameter("baosongren"):"";					//获取页面报送人姓名
String baosongshijian = request.getParameter("baosongshijian") !=null? request.getParameter("baosongshijian"):"";		//获取页面报送时间
String s_curpage = request.getParameter("curpage") != null ? request.getParameter("curpage"): "1";
int count = 0, pagesize = 10, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
String color = null;
curpage = Integer.parseInt(s_curpage);
String permissionRights = "";
%>
<ul class="tab">
  <li class="first"></li>
  <li class="cur"><a href="">实体审核</a></li>
  <li class="end"></li>
</ul>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
<%
  String whereStr="";
  String str="";
  String xuni="0";

		if (!shitileixing.equals("")) {
			
			whereStr=whereStr+" and z.stationType = '" + shitileixing + "'";
		}
		if (!jingyingdanwei.equals("")) {
			whereStr=whereStr+" and z.ascription_unit like '%" + jingyingdanwei + "%'";
		}
		if (!baosongren.equals("")) {
			whereStr=whereStr+" and a.NAME like '%" + baosongren + "%'";
		}
		if (!baosongshijian.equals("")) {
			whereStr=whereStr+" and to_char (w.applytime,'yyyy-MM-dd') like '%" + baosongshijian + "%'";
		}

 
	%>
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">实体审核统计一览表</div>
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
								<select name="shitileixing" style="box-sizing: border-box; width: 130px;">
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
								<input type="text" name="jingyingdanwei" maxlength="20" style="box-sizing: border-box; width: 130px;"/>
							</td>
							<td align="right" width="100px">报送人：</td>
							<td width="100px">
								<input type="text" name="baosongren" maxlength="20" style="box-sizing: border-box; width: 130px;"/>
							</td>
							<td align="right" width="100px">报送时间：</td>
							<td width="100px">
								<!-- 显示时间的input -->
								<input class="selected_font" type="text" name="baosongshijian" value="" readonly="readonly" class="Wdate" style="background-color: #FFFFFF; color: grey"onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
							</td>
						</tr>
						<tr>
							<td align="right" colspan="8">
								<input onclick="query()" type="button" class="btn_c1"   value="查询" /> 
							</td>
						</tr>
					</table>
					</form>
					<div class="tbtitle01"><b>实体审核统计一览</b></div>
					<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
						<tr align="center">
							<th>操作</th>
						    <th width="10">序号</th>
							<th width="120">实体名称</th>
							<th width="100">地市</th>
							<th width="80">区县</th>
							<th width="80">实体类型</th>
							<th width="80">所归属经营单元</th>
							<th width="80">实体编码</th>
							<th width="80">报送人</th>
							<th width="80">报送时间</th>
							<th width="80">流程类型</th>
							<th width="80">当前节点</th>
							
						</tr>
						<%
							//查询条件
							ArrayList fylist = new ArrayList();
							fylist = bean.getZDData(curpage, "ZHANDIAN", loginId,  shi,  xian, xiaoqu,  stationName ,whereStr);//添加实体类型，所属经营单位，报送人，报送时间 的模糊查询
							allpage = bean.getAllPage();
						    String id = "", taskId = "", fullname = "", unit = "", fullcode = "",fgs="", qx = "", stationtype = "", applyuser = "", dblc_type = "", currentaction = "", applytime = "",lctype="";
							int intnum = xh = pagesize * (curpage - 1) + 1;
							Double bl = 0.00;
							if (fylist != null) {
								int fycount = ((Integer) fylist.get(0)).intValue();
								for (int k = fycount; k < fylist.size() - 1; k += fycount) {

												//num为序号，不同页中序号是连续的
												id = (String) fylist.get(k + fylist.indexOf("ID"));
												taskId = (String) fylist.get(k + fylist.indexOf("TASK_ID"));
												fullname = (String) fylist.get(k + fylist.indexOf("STATION_FULL_NAME"));
												unit = (String) fylist.get(k + fylist.indexOf("ASCRIPTION_UNIT"));
												fullcode = (String) fylist.get(k + fylist.indexOf("FULL_STATION_CODE"));
												fgs = (String) fylist.get(k + fylist.indexOf("SHI"));
												qx = (String) fylist.get(k + fylist.indexOf("XIAN"));
												stationtype = (String) fylist.get(k + fylist.indexOf("STATIONTYPE"));
												applyuser = (String) fylist.get(k + fylist.indexOf("NAME"));
												dblc_type = (String) fylist.get(k+ fylist.indexOf("DBLC_TYPE"));
												currentaction = (String) fylist.get(k+ fylist.indexOf("CURRENTACTION"));
												applytime = (String) fylist.get(k+ fylist.indexOf("APPLYTIME"));

												SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
												java.util.Date applytimeDate=sdf.parse(applytime);
												String applytimeStr = sdf.format(applytimeDate); //上报时间
												if (dblc_type.equals("0")) {
													lctype = "实体修改";
												} else if (dblc_type.equals("1")) {
													lctype = "实体新增";
												}
												if (intnum % 2 == 0) {
													color = "#DDDDDD";

												} else {
													color = "#FFFFFF";
												}
												System.out.println("报账审核：" + currentaction);
												if(currentaction==null||currentaction.equals("")){
													currentaction="调整任务";
												}
						%>
						<!-- 数据加载  Start-->
						<tr align="center">
							<td width="80">
								<button type="button" class="btn_c1" onclick="sp('<%=id%>','<%=dblc_type %>')">
									审批
								</button>
							</td>
							<td width="10"><%=intnum++%></td>
							<td align="center"><%=fullname%></td>
							<td align="center"><%=fgs%></td>
							<td align="center"><%=qx%></td>
							<td align="center"><%=stationtype%></td>
							<td align="center"><%=unit%></td>
							<td align="center"><%=fullcode%></td>
							<td align="center"><%=applyuser%></td>
							<td align="center"><%=applytimeStr%></td>
							<td align="center"><%=lctype%></td>
							<td align="center"><%=currentaction%></td>
							
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
					    		  </select>&nbsp;&nbsp;共 <font color='#1E90FF'><b><%=allpage %></b>&nbsp;</font>页 &nbsp;&nbsp;
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
								</div>
							</td>
						</tr>
						<%
							}
						%>
						
					</table>
					<div class="space_h_10"></div>
				</div>
			</div>
		</td>
	</tr>
</table>
<script type="text/javascript">
var path = "${pageContext.request.contextPath}";
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
function sp(a,n) {
	window.open("stationAudit.jsp?id=" + a,
					"newwindow",
					"height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");
	document.form1.submit();
}
function query(){
	document.form1.action= path + "/web/sdttWeb/jichuInfoManager/stationManagerAudit.jsp";
	document.form1.submit();
}
function nextpage(){
	document.form1.page.value = parseInt(document.form1.page.value) + 1;
	var curpage = '<%=(curpage + 1)%>';
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/stationManagerAudit.jsp?page="+curPage;
	document.form1.submit();
}
function previouspage(){
	if ((parseInt(document.form1.page.value)) < 1)
		document.form1.page.value = 1;
	else {
	document.form1.page.value = parseInt(document.form1.page.value) + 1;
	var curpage = '<%=(curpage + 1)%>';
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/stationManagerAudit.jsp?page="+curPage;
	document.form1.submit();
	}
}
function gopagebyno(curPage){
	document.form1.action = path + "/web/sdttWeb/jichuInfoManager/stationManagerAudit.jsp?page="+curPage;
	document.form1.submit();
}
document.form1.shi.value='<%=shi%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
document.form1.xian.value='<%=xian%>';
document.form1.stationName.value='<%=stationName%>';
document.form1.shitileixing.value='<%=shitileixing%>';
document.form1.jingyingdanwei.value='<%=jingyingdanwei%>';
document.form1.baosongren.value='<%=baosongren%>';
document.form1.baosongshijian.value='<%=baosongshijian%>';
   
</script>
</body>
</html>