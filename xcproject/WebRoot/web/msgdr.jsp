<%@ page contentType="text/html;charset=utf-8" %>
<%@ page session="true"%>
<%
 String to =(String)session.getAttribute("url");
 String msg = (String)session.getAttribute("msg");
 String wfile = (String)session.getAttribute("wfile");
 String path = request.getContextPath();
 String downwrongdata=path+"/servlet/download?action=downwrongdata";
	//System.out.println("to:"+to);
if(msg == null || msg.equals(""))msg = " ";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="../images/css.css">
<title>无标题文档</title>

 <style>
            .btn {
             BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7b9ebd 1px solid
            }
            .btn1_mouseout {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#B3D997); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn1_mouseover {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#CAE4B6); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn2 {padding: 2 4 0
            4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;}
            .btn3_mouseout {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mouseover {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mousedown
            {
             BORDER-RIGHT: #FFE400 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #FFE400 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #FFE400
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #FFE400 1px solid
            }
            .btn3_mouseup {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn_2k3 {
             BORDER-RIGHT: #002D96 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #002D96 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); BORDER-LEFT: #002D96
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #002D96 1px solid
            }
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

function downwrongdata(){
     document.form1.action='<%=downwrongdata%>';
	document.form1.submit();
}
</script>
<form name="form1" method="post" enctype="multipart/form-data">
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
                        <p> <a href="<%=to%>">返回列表请点击这里。</a></p>
                        <p><a href="javascript:downwrongdata()">未成功导入数据。</a></p></td>
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
</form>
</body>
</html>
<script language="javascript">
function go(){
	//window.open('http://218.57.135.42:8080/esms/jsp/service/<%=to%>.jsp','_self')
	window.location.href='<%=to%>';
}
//setTimeout("go()",10000)
</script>
