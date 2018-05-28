<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.noki.jizhan.StationRelationshipTree"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="StyleSheet" href="<%=path%>/css/dtree.css" type="text/css" />
	<script type="text/javascript" src="<%=path%>/javascript/dtree.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery.ztree.exedit-3.5.js"></script>
	<style type="text/css">
  		body {
			    scrollbar-face-color:#A7C4EA;
			    scrollbar-base-color:#F5F5F5;
			    scrollbar-arrow-color:black;
			    scrollbar-track-color:#F5F5F5;
			    scrollbar-shadow-color:#EBF5FF; 
			    scrollbar-highlight-color:#F5F5F5;
			    scrollbar-3dlight-color:#8EAFE1;
			    scrollbar-darkshadow-Color:#9D9D9D;
			}
  	</style>
  </head>
  <body>
	<jsp:useBean id="treeListBean" class="com.noki.jizhan.StationRelationshipTreeBean" />
	<jsp:setProperty name="treeListBean" property="*" />
	<jsp:setProperty name="treeListBean" property="context" value="<%=path %>" />
    <div class="dtree">
		<p><a href="javascript: d.openAll();">打开全部</a> | <a href="javascript: d.closeAll();">关闭全部</a></p>		
		<script type="text/javascript">
			d = new dTree('d');
			d.add(0,-1,'汇总关系图');		
			<%
			  	List<StationRelationshipTree> list = treeListBean.getTreefuzeren();
			  	for(StationRelationshipTree tree:list){
			  		if(tree.getUrl() == null){
			%>
						d.add(<%=tree.getId()%>,<%=tree.getPid()%>,"<%=tree.getContent()%>",null,"<%=tree.getContent()%>","treeNodeInfo");
			<%
					}
					else{
			%>
						d.add(<%=tree.getId()%>,<%=tree.getPid()%>,"<%=tree.getContent()%>","<%=tree.getUrl()%>","<%=tree.getContent()%>","treeNodeInfo");
			<%
					}
				}
			%>
			document.write(d);
		</script>
	</div>
  </body>
</html>
