<%@ page contentType="text/vnd.wap.wml; charset=gb2312"%>
<%response.setContentType("text/vnd.wap.wml; charset=UTF-8");%>
<%request.setCharacterEncoding("UTF-8");%>

<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.noki.mobi.common.Account" %>
<?xml version="1.0"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<wml>
<%
    //取手机号
String mobile = "" ;
String MO="";
String temvit = "";
String version = "";

Enumeration headerNames = request.getHeaderNames();
while(headerNames.hasMoreElements()) {
String headerName = (String)headerNames.nextElement();
if (headerName.equals("x-up-calling-line-id")) {
temvit=request.getHeader(headerName);
if (temvit.substring(0,3).trim().equals("861")) {
mobile=temvit.substring(2,13);
}
if (temvit.substring(0,2).trim().equals("13")) {
mobile=temvit;
}
}

if (headerName.equals("user-agent")) {
MO=request.getHeader(headerName);
}

/*if (headerName.equals("x-up-calling-line-id")) {
temvit=request.getHeader(headerName);
if (temvit.substring(0,2).trim().equals("13")) {
mobile=temvit;
}
}*/
}


    System.out.println("mobile:"+mobile);
    Account account = (Account)session.getAttribute("wapaccount");
    if(account != null){
    	session.removeAttribute("wapaccount");
    	System.out.println("wapexit");
    }
%>
<head>
<meta http-equiv="Cache-Control" content="max-age=0" forua="true"/>
</head>
<card id="login" title="华威数据上报" >
<onevent type="onenterforward">
	<refresh>
<setvar name="pwd" value=""/>
	</refresh>
</onevent>
<p align="left">
	帐户<br/><input name="code" type="text" size="11" maxlength="30"  value=''/><br/>
	密码<br/><input name="pwd" type="password" size="11" maxlength="30" value=""/><br/>

<anchor title="登陆">
登陆
	<go href="<%=response.encodeURL("/mobilebuss/waplogin")%>" method="get">
	<postfield name="code" value="$(code)" />
	<postfield name="pwd" value="$(pwd)" />
	</go>
</anchor>
</p>
</card>

</wml>
