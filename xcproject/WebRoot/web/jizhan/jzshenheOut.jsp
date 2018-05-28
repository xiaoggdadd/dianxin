<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
 <BODY>
<div id="frameprogrssBar" align="center">
<img src="<%=path%>/img/wait.gif" />&nbsp;loading...
</div>
<iframe id="framecontent" src="<%=path%>/web/jizhan/jzshenhe.jsp" width="100%" height="100%" frameborder="0" scrolling="no" marginheight="0">
</iframe>
<script type="text/javascript">   
startload(document.getElementById("frameprogrssBar"),document.getElementById("framecontent"))
function startload(loadstr,iframestr){
var loadstr=loadstr;
var iframestr=iframestr;
loadstr.style.display ="block";
iframestr.style.display ="none";
if (window.ActiveXObject){
iframestr.onreadystatechange=function (){ 
if(this.readyState=="complete"){
   loadstr.innerHTML="load complete!";    
   loadstr.style.display="none"; 
   iframestr.style.display="block";   
}    
}
}else{
iframestr.style.display ="block";
loadstr.style.display ="none";
}
} 
</script>
</BODY>
</html>
