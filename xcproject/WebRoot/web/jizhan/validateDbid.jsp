<%@ page contentType="text/html; charset=UTF-8" %>
<%
	String dbid = request.getParameter("dbid");
        String id = request.getParameter("id");
%>

<html>
<head>
<jsp:useBean id="bean" scope="page" class="com.noki.jizhan.DianBiaoBean">
</jsp:useBean>
<title>电表ID是否重复</title>
<style type="text/css">
<!--
.style7 {	color: #000000;
	font-weight: bold;
}
-->
</style>
</head>

<body>
<table width="470" height="300"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" bgcolor="7C92B7">
              <tr>
                <td height="49" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="1" cellpadding="1">
                    <tr>
                      <td height="29"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td height="29" background="../../images/bei.gif">&nbsp; </td>
                          </tr>
                      </table></td>
                    </tr>
                    <tr>
                      <td height="292" valign="top" background="../../images/ti.jpg" bgcolor="#FFFFFF"><table width="100%" height="182"  border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="49%">&nbsp;</td>
                          <td width="51%"><%=bean.validateName(dbid,id)%></td>
                        </tr>
                      </table>                        <div align="center"></div></td>
                    </tr>
                    <tr>
                      <td bgcolor="F2F9FF">&nbsp;</td>
                    </tr>
                </table></td>
              </tr>
          </table></td>
        </tr>
    </table></td>
  </tr>
</table>
</body>
</html>

