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
  		<span style="position:absolute;font:微软雅黑;font-size:16px;height:30px;left:50px;top:20px;"><b>站点基本关系图</b></span>
  	</div>
  	<div class="seperator">
  		<div class="first"><hr></div><span style="font-size:12px;">过滤条件</span><div class="second"><hr></div>
  	</div>
  	<form action="<%=request.getContextPath() %>/web/jizhan/stationRelationshipMapView_iframe.jsp" name="form1">
  		<table cellpadding="1" class="form_label" border='0'>
  			<tr>
	  			<td class="form_label">城市：</td>
	  			<td>
		  			<select name="shi" onchange="changeCity()"()>
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList<?> shenglist = new ArrayList<String>();
			         	shenglist = commBean.getAgcode(shengId,"0",loginName);
			         	if(shenglist!=null){
			         		String sfid="",sfnm="";
			         		int scount = ((Integer)shenglist.get(0)).intValue();
			         		for(int i=scount;i<shenglist.size()-1;i+=scount)
		                    {
		                    	sfid=(String)shenglist.get(i+shenglist.indexOf("AGCODE"));
		                    	sfnm=(String)shenglist.get(i+shenglist.indexOf("AGNAME"));
		                    %>
		                    <option value="<%=sfid%>"><%=sfnm%></option>
		                    <%}
			         	}
			         %>
		         	</select>
	         	</td>
		        <td class="form_label">区县：</td>
	  			<td>
		  			<select name="xian">
		         		<option value="-1">请选择</option>
		         	</select>
		        </td>
		        <td class="form_label">集团报表类型：</td>
  				<td>
		         	<select name="jztype" onchange="kzinfo()">
		         		<option value="-1">请选择</option>
		         		<%
			         	ArrayList<?> zdtype = new ArrayList<String>();
			         	zdtype = ztcommon.getSelctOptions("ZDLX");
			         	if(zdtype!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)zdtype.get(0)).intValue();
			         		for(int i=cscount;i<zdtype.size()-1;i+=cscount)
		                    {
		                    	code=(String)zdtype.get(i+zdtype.indexOf("CODE"));
		                    	name=(String)zdtype.get(i+zdtype.indexOf("NAME"));
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
		         <td class="form_label">用房类型：</td>
	  			<td>
		         	<select name="yflx">
			         	<option value="-1">请选择</option>
			         		<%
				         	ArrayList<?> fwlx = new ArrayList<String>();
				         	fwlx = ztcommon.getSelctOptions("FWLX");
				         	if(fwlx!=null){
				         		String code="",name="";
				         		int cscount = ((Integer)fwlx.get(0)).intValue();
				         		for(int i=cscount;i<fwlx.size()-1;i+=cscount)
			                    {
			                    	code=(String)fwlx.get(i+fwlx.indexOf("CODE"));
			                    	name=(String)fwlx.get(i+fwlx.indexOf("NAME"));
			                    %>
			                    <option value="<%=code%>"><%=name%></option>
			                    <%}
				         	}
				         %>
		         	</select>
		        </td>
		    </tr>
		    <tr>
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
	  			<td class="form_label">是否标杆：</td>
	  			<td>
					<select name="bgsign" >
						<option value="-1">请选择</option>
				        <option value="0">否</option>
				        <option value="1">是</option>
				    </select>
				</td>
	         	<td class="form_label">是否节能：</td>
	         	<td>
					<select name="jnsign">
						<option value="-1">请选择</option>
		         		<option value="否">否</option>
		         		<option value="是">是</option>
		         	</select>
				</td>
	         	<td class="form_label">是否采集点：</td>
	         	<td>
					<select name="cjdsign" >
						<option value="-1">请选择</option>
				        <option value="0">否</option>
				        <option value="1">是</option>
				    </select>
				</td>
				<td colspan=2>
					<div id="query2" style="position:relative;width:59px;height:23px;cursor:pointer; float: left; left: 110px;">
						 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
						 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
				    </div> 
				</td>
	        </tr>
	     </table>
	<div class="seperator">
  		<div class="first"><hr></div><span  style="font-size:12px;">树状结构图</span><div class="second"><hr></div>
  	</div>
  	</form>
  	<iframe name="treeMap" width="300px" height="400px" frameborder="0" ></iframe>
  	<span style="width:20px"></span>
  	<iframe name="treeNodeInfo" width="300px" height="400px" frameborder="0" src="<%=path %>/web/jizhan/stationRelationshipMapView_iframe_station.jsp"></iframe>
  	<span style="width:20px"></span>
  	<iframe name="treeNodeInfo2" width="400px" height="400px" frameborder="0" src="<%=path %>/web/jizhan/stationRelationshipMapView_iframe_nodeInfo.jsp"></iframe>
  	
  </body>
  <script type="text/javascript">
  		document.form1.shi.value='<%=shi%>';
		var path = '<%=path%>';
		function changeCity(){
			var sid = document.form1.shi.value;
			if(sid=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选择","-1"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=shi&pid="+sid,"shi");
			}
		}
		function updateQx(res){
			var shilist = document.all.xian;
			shilist.options.length="0";
			shilist.add(new Option("请选择","-1"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		function selectTree(){

			var a = "<a href='<%=path %>/web/jizhan/stationRelationshipMapView_iframe.jsp?";
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
