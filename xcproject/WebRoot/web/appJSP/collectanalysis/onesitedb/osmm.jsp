<%@ page language="java" import="java.util.*,com.noki.mobi.common.Account" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Account account = (Account) session.getAttribute("account");//账户
String sheng = (String) session.getAttribute("accountSheng");//省
//回显字段
String shi = request.getParameter("shi") != null ? request
		.getParameter("shi") : "-1";//市
		String color;//颜色
		int intnum = request.getAttribute("colornum") != null ? (Integer) request
				.getAttribute("colornum")
				: 0;//条数 ,查出数据的条数，用于颜色设置
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>一站多表站点查询</title>

  </head>
  <style>
.style1{
color: red;
font-weight: bold;
}
.form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px

}
.selected_font{
width:130px;
font-family: 宋体;
font-size:12px;
line-height:120%;
}
body{
overflow-x:hidden;
margin-left: 0px;
margin-top: 0px;
margin-right: 0px;
margin-bottom: 0px;
}
#div1{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div1 p{float:left;font-size:12px; width:110px; cursor:pointer;}
.bttcn{color:black;font-weight:bold;}
.relativeTag{     
z-index:10;   
position:relative;     
top:expression(this.offsetParent.scrollTop);     
}
.table1{
width:100%;height:20%;border-collapse:collapse;border-spacing:0; 
}
</style>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>javascript/wait.js">
</script>
<script language="javascript">
	var path = '<%=path%>';
	$(function(){    
		$("#query").click(function(){
		queryDegree("chaxun");
		});
		$("#daochuBtn").click(function(){
		queryDegree("daochu");
		});
	});
	
	function queryDegree(command) {
		document.form1.action = path+"/servlet/OneSiteDbServlet?command="+command;
		document.form1.submit();
		if ("chaxun" == command) {
			showdiv("请稍等..............");
		}
	}
	
	</script>
		<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
  <body class="body">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
	 			<tr>
      				<td colspan="4" width="50">
						<div style="width:700px;height:50px">
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">一站多表站点查询</span>	
						</div>
					</td>
    			</tr>
				<tr>
					<td height="30" colspan="4">
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
					<td height="8%" width="1200">
						<table>
							<tr class="form_label">
								<td>
									城市：
								</td>
								<td>
									<select name="shi" class="selected_font" id="shi">
										<option value="-1">
											全省
										</option>
										<%
											ArrayList shilist = new ArrayList();
											shilist = commBean.getAgcode(sheng, account.getAccountId());
											if (shilist != null) {
												String agcode = "", agname = "";
												int scount = ((Integer) shilist.get(0)).intValue();
												int size = shilist.size() - 1;
												for (int i = scount; i < size; i += scount) {
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
									         					<td >
	         					站点启用状态:
						         </td>
						         <td>
						         <select name="state" id="state" class="selected_font">
						         <option value="-1" >全部</option>
						         	<option value="1" <c:if test="${state eq 1}">selected="selected"</c:if>>启用</option>
						         	<option value="0" <c:if test="${state eq 0}">selected="selected"</c:if>>未启用</option>
						         </select>
						         </td>
						         <td>电表启用状态：</td>
						         <td>
						         	<select name="ammerterStyleId" id="ammerterStyleId" class="selected_font">
						         	<option value="-1" >全部</option>
							         	<option value="1" <c:if test="${ammerterStyleId eq 1}">selected="selected"</c:if>>启用</option>
							         	<option value="0" <c:if test="${ammerterStyleId eq 0}">selected="selected"</c:if>>未启用</option>
							         </select>
						         </td>
								<td>

									<div id="query"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -240px; float: right; top: 0px">
										<img alt="" src="<%=path%>/images/chaxun.png" width="100%"
											height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<table>
				<tr>
					<td height="23" colspan="4">
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

			<div style="width: 100%; height: 320px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="100%" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label"
					style=" width: 100%; "
    				id="MyTable">
					<tr height="23" bgcolor="#DDDDDD" class="relativeTag ">
						<td width="1%" height="23">
							<div align="center" class="bttcn">
								地市
							</div>
						</td>
						<td width="1%" height="23">
							<div align="center" class="bttcn">
								站点数
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								一站一表站点个数
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								一站多表站点个数
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								非外借回收一站多表站点个数
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								一站零表站点个数
							</div>
						</td>
					</tr>
					<c:forEach items="${emcsList}" var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
							<td>
								<div align="center">
									${list.city}
								</div>
							</td>
							<td>
								<div align="center">
									${list.zds}
								</div>
							</td>
							<td>
								<div align="center">
									${list.yzyb}
								</div>
							</td>
							<td>
								<div align="center">
									${list.yzdb}
								</div>
							</td>
							<td>
								<div align="center">
									${list.fhsyzdb}
								</div>
							</td>
							<td>
								<div align="center">
									${list.yzlb}
								</div>
							</td>
						</tr>
					</c:forEach>

					<%
						if (intnum == 0) {
							for (int i = 0; i < 17; i++) {
								if (i % 2 == 0) {
									color = "#FFFFFF";
								} else {
									color = "#DDDDDD";
								}
					%>

					<tr bgcolor="<%=color%>">
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<%
						}
						} else if (!(intnum > 17)) {
							int j = ((intnum - 1) % 17);
							int i = 0;
							for (i = j; i < 17; i++) {
								if (i % 2 == 0)
									color = "#DDDDDD";
								else
									color = "#FFFFFF";
					%>
					<tr bgcolor="<%=color%>">
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<%
						}
						}
					%>
					<tr bgcolor="#DDDDDD" style="color:black;font-weight:bold;font-size: 12px" height="23">
							<td><div align="center" >合计</div></td>
       						<td><div align="center" >${s}</div></td>  
       						<td><div align="center" >${s1}</div></td>  
       						<td><div align="center" >${s2}</div></td>     		
							<td><div align="center" >${s3}</div></td> 
       		  				<td><div align="center" >${s4}</div></td>   
    					</tr>
				</table>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					<input type="hidden" name="bzw1" id="bzw1" value="1" />
					</td>
				</tr>
				<tr bgcolor="F9F9F9">
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
						<div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
	 						<img src="<%=path%>/images/daochu.png" width="100%" height="100%">
							<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div id="detailsDiv"
							style="position: relative; width: 99%; height: 200px; left: 0px; top: 10px; z-index: 1; float: left; overflow-y: auto;">

							<iframe name="details" frameborder="0" width="100%" height="100%"
								scrolling="no"></iframe>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
		<script type="text/javascript">
		document.form1.shi.value = '<%=shi%>';
</script>
</html>
