<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ptac.app.version.Version"%>
<%
	String path = request.getContextPath();
	String version = new Version().getVersion();
	
	
	String user = request.getParameter("user");
    //String pass = request.getParameter("pass");
    String title ="";
    
    
	String url=request.getParameter("url");
	if(url.indexOf("baozhang")!=-1){
		title="财务报账审批";
	}else if(url.indexOf("SPlist")!=-1){
		title="电表数据审批";
	}else if(url.indexOf("Audit")!=-1){
		title="办公、营业厅审批";
	}
	session.setAttribute("title", title);
	session.setAttribute("url", url);
    System.out.println("user:"+user+";url="+url);
%>
<html>
<head> 
	<!--  <link rel="stylesheet" type="text/css" href="<%=path%>/css/indexjsp.css" />-->
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" /> 
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--[if gt IE 8]><!-->
<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"></script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
<!--<![endif]-->
<!--[if lte IE 8]>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/console.js"></script>
<![endif]-->
	<script type="text/javascript" src="<%=path%>/javascript/wait.js"> </script>
	<script src="<%=path%>/javascript/placeholder.js"> </script> 
	<!-- 整理本页面后的js文件javascript/index-jsp.js   下行引用一定要在jquery-1.4.2.js引入之后-->
	<script type="text/javascript" src="<%=path%>/javascript/index-jsp.js"> </script>
    <title>中国电信山东分公司能耗管理平台系统</title>
  <!-- <link href="css/main.css" rel="stylesheet" type="text/css" /> --> 
<style>
td {font-family:微软雅黑; color:#000}
</style>
<script type="text/javascript">
$(function() {
	
	var user = '<%=user%>';
	var path = '<%=path%>';
	//alert(123);
			$.post(path+"/servlet/login?action=loginOn&typeStr=1",
							{
								user : user
								//pass : pass,
							},
							function(data, status) {
								if (status == "success") {
									if (data == 1) {//成功																	
										showdiv("请稍等.........."); 
										window.location.href = path+"/servlet/NewsServlet?action=getnews";
									} else {
										alert("账户或密码不正确，请重试！");
									}
								} else {
									alert("网络错误，请检查！");
								}
							});
})

</script>
</head>
	<body>
	<div>请稍等。。。。。。</div>

	</body>
</html>
