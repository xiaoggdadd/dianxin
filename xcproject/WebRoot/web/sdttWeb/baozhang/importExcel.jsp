<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<link href="../css/content.css" rel="stylesheet" type="text/css" />
	<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	<link rel="stylesheet" href="<%=path %>/javascript/jquery-fileupload/css/jquery.fileupload.css" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/javascript/jquery-fileupload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="<%=path %>/javascript/jquery-fileupload/js/jquery.iframe-transport.js"></script>
	<script type="text/javascript" src="<%=path %>/javascript/jquery-fileupload/js/jquery.fileupload.js"></script>

	<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
  </head>
  <script type="text/javascript">
  var path = "<%=path%>";
    function check(){
    	 var uploadFile = document.form1.file.value;      
    	    if(uploadFile == null || uploadFile == ''){      
    	        alert("请选择要上传的Excel文件");      
    	        return;      
    	    }
    	    var reg = /^.*\.(?:xls|xlsx)$/i;
    	    if(!reg.test(uploadFile)){
    	    	alert("文件格式错误, 仅允许导入“xls”或“xlsx”格式文件");
    	    	return;
    	    }
    	    
    	   document.form1.action= path + "/servlet/DaoruElectricfeesServlet?action=import";
    	   document.form1.submit();
    	   showdiv("正在导入请稍等...");
    }
  </script>
  <body style="overflow-x: hidden;">
     <div class="content">
	     <form action=""  name="form1" method="post" enctype="multipart/form-data">
	        <input type="file" name="file" >
	        <input type="button" class="btn_c1" onclick="check()"  value="导入数据">
	     </form>
	     <div style="margin-top: 25px;">
	       <span>温馨提示： 仅允许导入“xls”或“xlsx”格式文件</span><br/><span style="margin-left: 10px;">文件大小5M</span><a href="<%=path%>/servlet/DaoruElectricfeesServlet?action=downloadTemp" >下载模板</a>
	     </div>
	     <%
	        String msg = session.getAttribute("msg")==null?"": session.getAttribute("msg").toString();
	     %>
	     <div style="margin-top: 20px; font-size: 15px;"><%=msg%></div>
	     <div>
	     	<div class="tbtitle01"><b>错误数据一览</b></div>
	     		<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
					<tr align="right">
						<td colspan="9" align="right">
						</td>
					</tr>
					
					
					<tr align="center">
					    <th width="10">序号</th>
						<th width="120">内容</th>
						<th width="50">描述</th>
					</tr>
					<%
					ArrayList<String> data = new ArrayList<String>();
					  if(session.getAttribute("errMsg") != null){
						  data = (ArrayList<String>)session.getAttribute("errMsg");
					  }
					int intnum = 1;
					if(data !=null){
							String errMsg = "";
						for(int i=0; i<data.size(); i++){
							errMsg = data.get(i);
					%>
					<!-- 数据加载  Start-->
					<tr align="center">
						<td width="10"><%=intnum++%></td>
						<td align="left"><%=errMsg%></td>
						<td align="left" width="50"></td>
					</tr>
					
					<%
						}
					}
					
					%>
					<!-- 数据加载 End -->
									
				</table>
				<div class="space_h_10"></div>
     	</div>   
     </div>  
  </body>
</html>
