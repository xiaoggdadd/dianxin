<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>导入Excel</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<%String path = request.getContextPath();%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
<!--<![endif]-->
<!--[if lte IE 8]>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="../images/css.css">
<link href="../../../images/css.css" type=text/css rel=stylesheet>
<script type="text/javascript">
var path = '<%=path%>';
var XMLHttpReq;
//XMLHttpReq = createXMLHttpRequest();
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}
</script>
 <style>
.style1 {
	color: #014F8A;
	font-weight: bold;
	line-height:22pt;
}
.style2 {
	color: red;
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
	BORDER-RIGHT: #2C59AA 1px solid; 
	PADDING-RIGHT: 2px; 
	BORDER-TOP: #2C59AA 1px solid; 
	PADDING-LEFT: 2px; 
	FONT-SIZE: 12px; 
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); 
	BORDER-LEFT: #2C59AA 1px solid; 
	CURSOR: hand; COLOR: black; 
	PADDING-TOP: 2px; 
	BORDER-BOTTOM: #2C59AA 1px solid
}
img{
    border:0px;
}
 </style>
</head>
<body class="body" style="overflow-x:hidden;">
 <script>
 function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = "";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">";
			
		}
		else
		{
			trObject.style.display = "none";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">";
		}
}
function daoru(){
    var path = '<%=path%>';
    var file = $("#file").val();
	if(""!=file){
	var filesize = document.getElementById("file").files[0].size/1024;
	if(file.indexOf("xls")<=0&&file.indexOf("xlsx")<=0){
	$("#file").val("");
	alert("此格式文件不允许上传请选择表格文件！！");
	}else if(filesize>5120){
	$("#file").val("");
	alert("此文件大于5mb！！");
	}else{
	document.form1.action=path+"/servlet/FileUpServlet?action=upload"; 
	document.form1.submit();
	showdiv("请稍等..............");
	}
	}
	
}
function exportModel(){
    var path = '<%=path%>';
    //alert(canshuStr);
    //document.form1.action=path+"/web/electricfees/电费批量导入模板.jsp?curpage="+curpage+"&whereStr="+whereStr;
    //document.form1.action=path+"/servlet/download?action=download&templetname="+encodeURIComponent("电量电费批量导入模板");
	//document.form1.action=path+"/servlet/FileUpServlet?action=upload&templetname="+encodeURIComponent("电量电费批量导入模板");     
  	// document.form1.submit();
   }
 $(function(){
			$("#daoru").click(function(){
			alert("请确认");
				daoru();
			});
		});
	
</script>
	<form name="form1" method="post" action="/IDA/servlet/uploaddegreefee" enctype="multipart/form-data">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr valign="top">
			<td width="12"><img src="../images/space.gif" width="12" height="17" />
				</td>
				<td>
					<div class="content01">
						<div class="content01_1">
							<div class="tbtitle01">
								<b>批量导入Excel</b>
							</div>
							<table width="100%" border="0" cellpadding="0" cellspacing="0" frame=void class="tb_list1">
								<tr>
									<td height="46" colspan="4"><span class="style1"> 本功能用于批量导入电表基础数据</span></td>
								</tr>
								<tr>
									<td height="145" colspan="3" rowspan="2">
										<div align="center"><img src="../../images/dao.gif" width="132" height="119" /></div>
									</td>
									<td width="66%" height="64">
										<p><a href="javascript:exportModel()"></a></p>
									</td>
								</tr>
                                <tr>
									<td height="39" valign="top"><input id="file" name="textfield" type="file" class="form" />
										<div id="daoru" style="position: relative; width: 59px; height: 23px; cursor: pointer; left: 35%; TOP: -23PX ">
											<img alt="" src="<%=request.getContextPath() %>/images/tijiao.png" width="100%" height="100%" />
											<span style="font-size:12px;position: absolute;left:28px;top:2px;color:white">确定</span>
										</div>
									</td>
                                </tr>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>


