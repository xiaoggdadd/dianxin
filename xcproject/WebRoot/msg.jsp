<%@ page contentType="text/html;charset=GBK" %>
<%@ page session="true"%>
<%
 String to =(String)session.getAttribute("url");
 String msg = (String)session.getAttribute("msg");
	System.out.println("to:"+to);
if(msg == null || msg.equals(""))msg = " ";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="../images/css.css">
<title>无标题文档</title>

 <style>
            
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #2B85C6 solid}
 .style7 {
	color: #000000;
	font-weight: bold;
}
 </style>

</head>

<body class="body" style="overflow-x:hidden;">
<LINK
href="../images/css.css" type="text/css" rel=stylesheet>
<script>
function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../images/1.gif\">"

		}
		else
		{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../images/SearchDown.gif\">"
		}
}
</script>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="10"><img src="../images/img_04.gif" width="12" height="37" /></td>
    <td background="../images/img_05.gif">&nbsp;</td>
    <td width="12"><img src="../images/img_06.gif" width="12" height="37" /></td>
  </tr>
  <tr>
    <td width="10" height="532" background="../images/img_10.gif">&nbsp;</td>
    <td valign="top" ><br />
      <br />      <table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">

      <tr>
        <td colspan="3"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>

            <td><table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" bgcolor="7C92B7">
              <tr>
                <td height="49" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="1" cellpadding="1">
                  <tr>
                    <td height="29" colspan="2"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td height="29" background="../images/bei.gif"> 　　 <img src="../images/v.gif" width="8" height="9" />　　　　　　　　　　　　　　　　　　　　　　　　　<span class="style7">提示信息！</span></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td width="42%" height="87" bgcolor="#FFFFFF"><div align="center"><img src="../images/2.gif" width="133" height="134" /></div></td>
                    <td width="58%" bgcolor="#FFFFFF"><p><%=msg%></p>
                        <p> <a href="<%=to%>" id="mes">如果你的浏览器不支持自动跳转(3秒)请点击这里。</a></p></td>
                  </tr>
                  <tr>
                    <td colspan="2" bgcolor="F2F9FF">&nbsp;</td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table>    </td>
    <td background="../images/img_13.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="../images/img_23.gif" width="12" height="19" /></td>
    <td background="../images/img_24.gif">&nbsp;</td>
    <td><img src="../images/img_25.gif" width="12" height="19" /></td>
  </tr>
</table>
</body>
</html>
<script language="javascript">
			var i = 3;  
			var intervalid;  
			intervalid = setInterval("fun()", 300);  
			function fun() {  
			    if (i == 0) {  
			        window.location.href='<%=to%>';
			        clearInterval(intervalid);  
			    }  
			    document.getElementById("mes").innerHTML ='如果你的浏览器不支持自动跳转('+ i+')秒后返回首页';  
			    i--;   
			} 
		
 
//function go(){
	//window.open('http://218.57.135.42:8080/esms/jsp/service/<%=to%>.jsp','_self')
	//window.location.href='<%=to%>';
//}//
 
//setTimeout("go()",10000)
</script>
