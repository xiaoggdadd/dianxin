<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" %>
<%
String path = request.getContextPath();
String shengId = (String)session.getAttribute("accountSheng");
Account account = (Account)session.getAttribute("account");
String loginName = (String)session.getAttribute("loginName");
String agcode1="";
if(request.getParameter("shi")==null){
 	ArrayList shilist = new ArrayList();
 	CommonBean commBean = new CommonBean();
 	shilist = commBean.getAgcode(shengId,account.getAccountId());
	if(shilist!=null){
     	int scount = ((Integer)shilist.get(0)).intValue();
       	agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
	}
}
String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="StyleSheet" href="<%=path%>/css/atatonic/zp-type.css" type="text/css" />
	
	<script src="<%=path%>/javascript/tx.js"></script>
	<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
	
	<style type="text/css">
		select{
			width:90px;
			font-family: 宋体;
			font-size: 12px;
		}
		.form_label{
			text-align: right;
			font-family: 宋体;
			font-size: 12px;
		}
		.seperator{
			width:100%;
			margin-bottom: -20px;
		}
		.seperator .first{
			width:5%;
			position: relative;
			float: left;
		}
		.seperator span{
			position: relative;
			float:left;
			font-family: 宋体;
			font-size: 14px;
		}
		.seperator .second{
			width:30%;
			position: relative;
		}
	</style>  
  </head>
  
  <body>
  <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
  <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
  	<div style="position:relative;height:30px;width:800px;top:0px;">
  		<img alt="" src="<%=path%>/images/btt.bmp">
  		<span style="position:absolute;font:微软雅黑;font-size:16px;height:30px;left:50px;top:20px;"><b>站点负责人查询关系图</b></span>
  	</div> 
  	<div class="seperator">
  		<div class="first"><hr></div><span style="font-size:12px;">过滤条件</span><div class="second"><hr></div>
  	</div>
  	<form action="" name="form1">
  		<table cellpadding="1" class="form_label" border='0'>
  			<tr>
		        <td class="form_label">站点属性：</td>
  				<td>
		         	<select name="property">
		         		<option value="-1">请选择</option>
		         		<%
			         	ArrayList<?> property = new ArrayList<String>();
		         		property = ztcommon.getSelctOptions("ZDSX");
			         	if(property!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)property.get(0)).intValue();
			         		for(int i=cscount;i<property.size()-1;i+=cscount)
		                    {
		                    	code=(String)property.get(i+property.indexOf("CODE"));
		                    	name=(String)property.get(i+property.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         	%>
		         	</select>
		        </td>
		         <td class="form_label">站点归属方：</td>
  				<td>
		         	<select name="gsf">
		         		<option value="-1">请选择</option>
		         		<%
			         	ArrayList<?> gsf = new ArrayList<String>();
			         	gsf = ztcommon.getSelctOptions("gsf");
			         	if(gsf!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)gsf.get(0)).intValue();
			         		for(int i=cscount;i<gsf.size()-1;i+=cscount)
		                    {
		                    	code=(String)gsf.get(i+gsf.indexOf("CODE"));
		                    	name=(String)gsf.get(i+gsf.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         	%>
		         	</select>
		        </td>
		        <td class="form_label">是否标杆：</td>
	  			<td>
					<select name="bgsign" >
						<option value="-1">请选择</option>
				        <option value="0">否</option>
				        <option value="1">是</option>
				    </select>
				</td>
				 <td class="form_label">电表用途：</td>
	  			<td>
		         	<select name="dbyt">
		         		<option value="-1">请选择</option>
		         		<%
			         	ArrayList<?> dbyt = new ArrayList<String>();
		         		dbyt = ztcommon.getSelctOptions("DBYT");
			         	if(dbyt!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)dbyt.get(0)).intValue();
			         		for(int i=cscount;i<dbyt.size()-1;i+=cscount)
		                    {
		                    	code=(String)dbyt.get(i+dbyt.indexOf("CODE"));
		                    	name=(String)dbyt.get(i+dbyt.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
		         	</select>
		        </td>
			    <td class="form_label">供电方式：</td>
	  			<td>
		         	<select name="gdfs">
		         		<option value="-1">请选择</option>
		         		<%
			         	ArrayList<?> gdfs = new ArrayList<String>();
		         		gdfs = ztcommon.getSelctOptions("GDFS");
			         	if(gdfs!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)gdfs.get(0)).intValue();
			         		for(int i=cscount;i<gdfs.size()-1;i+=cscount)
		                    {
		                    	code=(String)gdfs.get(i+gdfs.indexOf("CODE"));
		                    	name=(String)gdfs.get(i+gdfs.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
		         	</select>
		        </td>
		        
		   
			    <td class="form_label">站点产权：</td>
	  			<td>
		         	<select name="zdcq">
			         	<option value="-1">请选择</option>
			         		<%
				         	ArrayList<?> zdcq = new ArrayList<String>();
				         	zdcq = ztcommon.getSelctOptions("ZDCQ");
				         	if(zdcq!=null){
				         		String code="",name="";
				         		int cscount = ((Integer)zdcq.get(0)).intValue();
				         		for(int i=cscount;i<zdcq.size()-1;i+=cscount)
			                    {
			                    	code=(String)zdcq.get(i+zdcq.indexOf("CODE"));
			                    	name=(String)zdcq.get(i+zdcq.indexOf("NAME"));
			                    %>
			                    <option value="<%=code%>"><%=name%></option>
			                    <%}
				         	}
				         %>
		         	</select>
		        </td>
	  		</tr>
		    <tr>
	         	<td class="form_label">2G设备：</td>
	         	<td>
					<select name="g2">
						<option value="-1">请选择</option>
		         		<option value="否">否</option>
		         		<option value="是">是</option>
		         	</select>
				</td>
	         	<td class="form_label">3G设备：</td>
	         	<td>
					<select name="g3" >
						<option value="-1">请选择</option>
				        <option value="0">否</option>
				        <option value="1">是</option>
				    </select>
				</td>
				<td class="form_label">空调1：</td>
	         	<td>
					<select name="kt1" >
						<option value="-1">请选择</option>
				        <option value="0">否</option>
				        <option value="1">是</option>
				    </select>
				</td>
				<td class="form_label">空调2：</td>
	         	<td>
					<select name="kt2" >
						<option value="-1">请选择</option>
				        <option value="0">否</option>
				        <option value="1">是</option>
				    </select>
				</td><td class="form_label">宽带设备：</td>
	         	<td>
					<select name="kdsb" >
						<option value="-1">请选择</option>
				        <option value="0">否</option>
				        <option value="1">是</option>
				    </select>
				</td><td class="form_label">语音设备：</td>
	         	<td>
					<select name="yysb" >
						<option value="-1">请选择</option>
				        <option value="0">否</option>
				        <option value="1">是</option>
				    </select>
				</td>
				<td colspan=2>
					<div id="query2" style="position:relative;width:59px;height:23px;">
						 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
						 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
				    </div> 
				</td>
	     </table>
	<div class="seperator">
  		<div class="first"><hr></div><span  style="font-size:12px;">树状结构图</span><div class="second"><hr></div>
  	</div>
  	</form>	
  	<iframe name="treeMap" width="250px" height="400px" frameborder="0"></iframe>
  	<span style="width:20px"></span>
    <iframe name="treeNodeInfo" width="150px" height="400px" frameborder="0" src="<%=path %>/web/jizhan/fuzeren_iframe_fuzeren.jsp"></iframe>
    <span style="width:20px"></span>
    <iframe name="treeNodeInfo2" width="650px" height="400px" frameborder="0" src="<%=path %>/web/jizhan/fuzeren_iframe_nodeInfo.jsp"></iframe>
  </body>
  <script type="text/javascript">
		var path = '<%=path%>';
		function selectTree(){

			var a = "<a href='<%=path %>/web/jizhan/fuzeren_iframe.jsp?";
			$("select").each(function(){
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
	</script>
</html>
