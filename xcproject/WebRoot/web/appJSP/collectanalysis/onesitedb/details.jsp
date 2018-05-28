<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();//项目路径
%>

<html>
	<head>
		<title>站点信息详情</title>
		<style>
		.relativeTag   {     
				z-index:10;   
				position:relative;     
				top:expression(this.offsetParent.scrollTop);     
		}
		</style>

		<link type="text/css" rel="Stylesheet"
			href="<%=path%>/web/appCSS/pageCss/page.css" />


	</head>

	<body class="body">
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

			<div style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="100%" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label"
					style=" width: 100%; "
    				id="MyTable">
					<tr height="23" bgcolor="#DDDDDD" class="relativeTag ">
						<td width="1%" height="23">
							<div align="center" class="bttcn">
								城市
							</div>
						</td>
						<td width="1%" height="23">
							<div align="center" class="bttcn">
								区县
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								乡镇
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								站点属性
							</div>
						</td>
						<td width="1.8%" height="23">
							<div align="center" class="bttcn">
								站点ID
							</div>
						</td>
					</tr>
					<c:forEach items="${emcsList}" var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
							<td>
								<div align="center">
									${list.shi}
								</div>
							</td>
							<td>
								<div align="center">
									${list.xian}
								</div>
							</td>
							<td>
								<div align="center">
									${list.xiaoqu}
								</div>
							</td>
							<td>
								<div align="center">
									${list.zdname}
								</div>
							</td>
							<td>
								<div align="center">
									${list.property}
								</div>
							</td>
							<td>
								<div align="center">
									${list.zdid}
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
	</body>

</html>

