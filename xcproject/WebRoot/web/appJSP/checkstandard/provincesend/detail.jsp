<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String color;//颜色
int intnum = request.getAttribute("numcolor") != null ? (Integer) request
		.getAttribute("numcolor")
		: 0;//条数 ,查出数据的条数，用于颜色设置
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>详情</title>
    		<link type="text/css" rel="Stylesheet"
			href="<%=path%>/web/appCSS/pageCss/page.css" />
				<style>
		.relativeTag   {     
				z-index:10;   
				position:relative;     
				top:expression(this.offsetParent.scrollTop);     
		}
		</style>
  </head>
  
  <body>
    <div style="width: 100%; height: 200px; border: 1px">
				<table width="100%" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label"
					style=" width: 100%; "
    				id="MyTable">
					<tr height="23" class="relativeTag">
						<td width="16%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="10%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								上次抄表时间
							</div>
						</td>
						<td width="10%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								本次抄表时间
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								上次电表读数
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								本次电表读数
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								倍率
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表用电量
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账金额
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账电量
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								报账月份
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级主任审核状态
							</div>
						</td>
					<c:forEach items="${list}" var="list" varStatus="status">
					<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
						<td>
							<div align="left">
								${list.zdname}
							</div>
						</td>
						<td>
							<div align="center">
								${list.lasttime}
							</div>
						</td>
						<td>
							<div align="center">
								${list.thistime}
							</div>
						</td>
						<td>
							<div align="right">
								${list.lastdegree}
							</div>
						</td>
						<td>
							<div align="right">
								${list.thisdegree}
							</div>
						</td>
						<td>
							<div align="right">
								${list.beilv}
							</div>
						</td>
						<td>
							<div align="right">
								${list.dbydl}
							</div>
						</td>
						<td>
							<div align="right">
								${list.bzdf}
							</div>
						</td>
						<td>
							<div align="right">
								${list.bzdl}
							</div>
						</td>
						<td>
							<div align="center">
								${list.accountmonth}
							</div>
						</td>
						<td>
							<div align="center">
							<c:choose>
							<c:when test="${list.cityzrauditstatus eq 0}"> 未审核</c:when>
							<c:when test="${list.cityzrauditstatus eq 1}"> 通过</c:when>
							<c:when test="${list.cityzrauditstatus eq 2}"> 不通过</c:when>
							</c:choose>
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

				</table>
			</div>
  </body>
</html>
