<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
  <script type="text/javascript"> 
    function init() { 
    var options="status=yes,resizable=yes,left=0,top=0,width="+(window.screen.availWidth)+",height="+(window.screen.availHeight); 
    window.open('index.jsp',"_blank",options); 
    } 
    function closewin(){
      window.opener=null;    
      //window.opener=top;    
      window.open("","_self");    
      window.close();     
     }
    function clock(){
       i=i-1
     if(i>0)
       setTimeout("clock();",1000);
     else
      closewin();
     }
 var i=5
 clock(); 
  </script> 
  <body onload='init()'> 
  </body> 
  </html> 