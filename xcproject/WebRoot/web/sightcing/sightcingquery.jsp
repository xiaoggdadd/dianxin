<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String shengId = (String)session.getAttribute("accountSheng");
String loginName = (String)session.getAttribute("loginName");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="StyleSheet" href="<%=path%>/css/atatonic/zp-type.css" type="text/css" />	
	<script src="<%=path%>/javascript/tx.js"></script>
	<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
  </head>
  
  <body>
  <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
  <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
  	<div style="position:relative;height:30px;width:800px;top:-10px;"><img src="<%=path%>/images/btt.bmp" width="600" height="60" />
  		<span style="position:absolute;font:微软雅黑;font-size:14px;height:30px;left:30px;top:25px;"><b>标杆类型查询</b></span>
  	</div>   
  	<table border="1" width="100%">
  		<tr>
  			<td>
  				  <iframe  align="top" name="treeMap" width="100%" height="100%" frameborder="0" src="<%=path %>/web/sightcing/sightcingquery1.jsp"></iframe> 	
  			</td>
  			<td>
  				<table border="1">
  					<tr>
  						<td colspan="2">
  						  	<iframe align="top" name="treeNodeInfo" width="100%" height="100%" frameborder="0" src="<%=path %>/web/sightcing/sightcingquery2.jsp"></iframe>   
  						</td>
  					</tr>
  					<tr>
  						<td>
  							<iframe  align="right" name="treeMap1" width="100%" height="300px" frameborder="0" src="<%=path %>/web/sightcing/sightcingquery3.jsp"></iframe>     	 		  
  						</td>
  						<td>
  						  	<iframe background-position-y ="40%" align="right" name="treeNodeInfo1" width="100%" height="300px" frameborder="0" src="<%=path %>/web/sightcing/sightcingquery4.jsp"></iframe>
  						</td>
  					</tr>
  				</table>
  			</td>
  		</tr>	
  	</table>	
  
  	
  
  	
  </body>
  <script type="text/javascript">
		var path = '<%=path%>';
		function selectTree(){
			var a = "<a href='<%=path %>/web/query/caijipoint/dianbiaoqueryleft.jsp?";
			$("select").each(function(){
				a += $(this).attr("name")+"="+$(this).val()+"&";
			});
			$("input").each(function(){
				a += $(this).attr("name")+"="+$(this).val()+"&";
			});
			a = a.substring(0,a.length-1);
			a += "' target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();
			}
			$("#query2").click(function(){
			selectTree();
		});