<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
String path = request.getContextPath();
String servletName = request.getParameter("servletname");
System.out.println("servletName:"+servletName);
String mfile = "";

%>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="images/css.css">
<title>无标题文档</title>

 <style>
            .style1 {
	color: #014F8A;
	font-weight: bold;
	line-height:22pt;
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.style4 {
	color: #ff9900;
	font-weight: bold;
}
 #id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
img{
    border:0px;
}
 </style>

</head>

<body class="body" style="overflow-x:hidden;">
<LINK href="../../images/css.css" type=text/css rel=stylesheet>
<script>
function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"
			
		}
		else
		{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
}
function daoru(){
    var path = '<%=path%>';
	document.form1.action=path+"/servlet/uploadfee";
	document.form1.submit();
}
 function exportModel(){
         var path = '<%=path%>';
            //alert(canshuStr);
        	//document.form1.action=path+"/web/electricfees/电费批量导入模板.jsp?curpage="+curpage+"&whereStr="+whereStr;
            document.form1.action=path+"/servlet/downloadup?action=download&templetname="+encodeURIComponent("电费批量导入模板");
            document.form1.submit();
   }
 $(function(){
			$("#daoru").click(function(){
				daoru();
			});
		});

</script>
<form name="form1" method="post" action="/IDA/servlet/uploadfee"  enctype="multipart/form-data" >
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="10"><img src="../../images/img_04.gif" width="12" height="37" /></td>
    <td background="../../images/img_05.gif">&nbsp;</td>
    <td width="12"><img src="images/img_06.gif" width="12" height="37" /></td>
  </tr>
  <tr>
    <td width="10" height="532" background="../../images/img_10.gif">&nbsp;</td>
    <td valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="3"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="11%"><img src="../../images/in_11.gif" width="106" height="58" /></td>
            <td width="87%" background="../../images/in_12.gif"><span class="style4">数据导入</span></td>
            <td width="2%"><img src="../../images/in_13.gif" width="25" height="58" /></td>
          </tr>
        </table></td>
        </tr>
      <tr>
        <td colspan="3">
        <table cellspacing="0" cellpadding="0" width="100%" border="0">
          <tbody>
            <tr id="SearchRegion">
                              <td height="51" colspan="3" background="../../images/img_15.gif">&nbsp; </td>
             
                              <td width="95%" ><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <td height="46" colspan="4">　　　　　　　　　　　<span class="style1"> 本功能用于批量导入数据。

</span></td>
                                </tr>
                                <tr>
                                  <td height="145" colspan="3" rowspan="2"><div align="center"><img src="../../images/dao.gif" width="132" height="119" /></div></td>
                                  <td width="66%" height="64"><p> 请选择您要导入的数据文件，此文件包含您要导入的数据.请点<a href="javascript:exportModel()"><此处></a>生成导入模版.
 </p>                                  </td>
                                </tr>
                                <tr>
                                  <td height="39" valign="top"><input  name="textfield" type="file" class="form"  />　
                                 　                                                         <div id="daoru" style="position:relative;width:60px;height:23px;cursor: pointer;right:400px;float:right;top:-5px">
		                            <img alt="" src="<%=path %>/images/tijiao.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">确定</span>
								</div>
                                </tr>
                                <tr>
                                  <td height="51" colspan="4"><div align="center">
                                   
                                   </div></td>
                                </tr>
                              </table></td>
                              <td background="../../images/img_17.gif">&nbsp;</td>
            </tr>
            <tr>
              <td width="29" height="26"><img src="../../images/img_18.gif" width="29" height="26" /></td>
              <td 
colspan="3" align="middle" background="../../images/img_19.gif">
                </td>
              <td width="25"><img src="../../images/img_20.gif" width="25" height="26" /></td>
            </tr>
          </tbody>
        </table></td>
        </tr>
    </table>
    </td>
    <td background="../../images/img_13.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="../../images/img_23.gif" width="12" height="19" /></td>
    <td background="../../images/img_24.gif">&nbsp;</td>
    <td><img src="../../images/img_25.gif" width="12" height="19" /></td>
  </tr>
</table>
<input type="hidden" name="servletname" value="<%=servletName%>"/>
</form>
</body>
</html>
