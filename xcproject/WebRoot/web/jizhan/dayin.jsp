
<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.sql.*,java.util.*,java.io.*"%>
<%@ page language="java" import="java.util.Date"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>

<%request.setCharacterEncoding("UTF-8");%>
<%
String path= request.getContextPath();
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>打印预览</title>
	<script type="text/javascript" src="../../../js/CalendarForm.js"></script>
	<script language="JavaScript">
		function PrintNoHdr() 
		{ 
		t = new ActiveXObject("WScript.Shell"); 
		t.RegWrite("HKCU\\Software\\Microsoft\\InternetExplorer\\PageSetup\\header",""); 
		t.RegWrite("HKCU\\Software\\Microsoft\\InternetExplorer\\PageSetup\\footer",""); 
		window.print() 
		}
		
		function ss(v) 
		{ 
				window.open('dayin2.jsp','infoAccount','width=790,height=600,status=yes,scrollbars=yes,resizable=yes,left=150,top=150');
		} 

		//下一页
		function nextPage(o)
		{
			//alert(o);
			document.form1.action="../zyda/dayin.jsp?pageNum="+o;
			document.form1.submit();
		}
		//上一页
		function upPage(o)
		{
			//alert(o);
			document.form1.action="../zyda/dayin.jsp?pageNum="+o;
			document.form1.submit();
		}
		function befPage()
		{
			//var o=document.form1.elements["nowpagenum"].value;
			var o=document.getElementById("nowpagenum").value;

			//alert(o);
			document.form1.action="../zyda/dayin.jsp?pageNum="+o;
			
			document.form1.submit();
		}
		
		function inputin()
		{
			document.form1.action="../zyda/dayin.jsp?pageNum=1";
			document.form1.submit();
		}

		function showAll()
		{
			document.form1.elements["bgqx"].value="";
			document.form1.elements["jgmc"].value="";
			document.form1.elements["dabTime"].value="";
			document.form1.elements["daeTime"].value="";
			document.form1.elements["mj"].value="";
			document.form1.action="../zyda/dayin.jsp";
		    document.form1.submit();
		}
		
///////////改变表格的列宽
function MouseDownToResize(obj){ 
setTableLayoutToFixed(); 
obj.mouseDownX=event.clientX; 
obj.pareneTdW=obj.parentElement.offsetWidth; 
obj.pareneTableW=theObjTable.offsetWidth; 
obj.setCapture(); 
} 
function MouseMoveToResize(obj){ 
if(!obj.mouseDownX) return false; 
var newWidth=obj.pareneTdW*1+event.clientX*1-obj.mouseDownX; 
if(newWidth>0) 
{ 
obj.parentElement.style.width = newWidth; 
theObjTable.style.width=obj.pareneTableW*1+event.clientX*1-obj.mouseDownX; 
} 
} 
function MouseUpToResize(obj){ 
obj.releaseCapture(); 
obj.mouseDownX=0; 
} 
function setTableLayoutToFixed() 
{ 
 if(theObjTable.style.tableLayout=='fixed') return; 
var headerTr=theObjTable.rows[0]; 
for(var i=0;i<headerTr.cells.length;i++) 
{ 
headerTr.cells[i].styleOffsetWidth=headerTr.cells[i].offsetWidth; 
} 
 
for(var i=0;i<headerTr.cells.length;i++) 
{ 
headerTr.cells[i].style.width=headerTr.cells[i].styleOffsetWidth; 
} 
theObjTable.style.tableLayout='fixed'; 
} 

//控制行高
function getHeight()
{
	//获取生成的行数
	var num=document.getElementById("allTr").value;
	//判断是否存在行高较大的一项
	//声明数组
	var list_ing=new Array();
	for(var k=0;k<num;k++){
		var height=document.getElementById(k).offsetHeight;
		list_ing[k]=height;
		//alert(list_ing[k]);
     }
	list_ing.sort();
	//最大的那个行高list_ing[num-1]
	var h=list_ing[num-1];
	//alert(h);
	for(var k=0;k<num;k++){
		document.getElementById(k).height=h;
     }
	//空白处的行高固定
  	var num2=document.getElementById("Alltb").value;
	if(num2!=0)
	{
		for(var i=0;i<num2;i++)
		{
			document.getElementById("tb"+i).height=h;
		}
		
	}
	

}
</script>
<!--/*非打印的样式*/-->
<style type="text/css" media=print>
.noprint{display : none }

</style>


<style type="text/css">
<!--
.STYLE1 {
	font-size: 24px;
	font-weight: bold;
}
.STYLE5 {font-size: 14px}
.STYLE6 {
	font-size: 16px;
	font-weight: bold;
}
-->
</style>

<style type="text/css">
<!--
.STYLE1 {
	font-size: x-large;
	font-weight: bold;
}
.STYLE5 {font-size: 14px}
.STYLE6 {
	font-size: 16px;
	font-weight: bold;
}

.bg table{
font-size:12px;
color:#000000;
}
.bg td{
font-size:12px;
color:#000000;
text-align:left;
line-height:15px;
height:20px;
}
.bg td.tit{
background-color:#e2e2e2;
color:#000;
height:17px;
text-align:center;
line-height:15px;
}
.bg td.num{
background-color:#e2e2e2;
color:#000;
text-align:right;
line-height:15px;
width:30px;
height:22px;
}
.resizeDivClass{
text-align:right;
width:1px;
margin:0px 0 0px 0;
background:#fff;
border:0px;
float:right;
cursor:e-resize;
} 

-->
</style>
</head>
<%		

%>

<body onLoad="getHeight()" >
<!--startprint-->
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id="printWB"  width=0></OBJECT> 
	
<div style="text-align:center"  class="noprint">

<input type="button" value="打印测试" onClick="ss(6)"> 





<form name="form1" method="post" action="">

<table width="800" align="center" border="0" cellpadding="0" cellspacing="0">

<tr>
	  
    </table>
</form>
</div>

<table width="650" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="72%" colspan="4"  align="center"><span class="STYLE1">卷内制卡人物登记表</span></td>
  </tr>
  <tr>
    <td  align="center">&nbsp;</td>
    <td  align="center">&nbsp;</td>
    <td  align="center">&nbsp;</td>
    <td  align="right">案卷号:</td>
  </tr>
</table>

<table width="650" border="1"
cellpadding="0" cellspacing="0" align="center" bordercolor="#000000" style="border-collapse:collapse"  id="theObjTable">

  
  <tr height="30">
<td align="center" nowrap="nowrap"  class="kuangtitle"><span class="resizeDivClass noprint" onMouseDown="MouseDownToResize(this);" onMouseMove="MouseMoveToResize(this);" onMouseUp="MouseUpToResize(this);"><img src="images/box1.gif" width="3" height="18"></span><span class="STYLE6">
姓名</span></td>
<td align="center" nowrap="nowrap"  class="kuangtitle"><span class="resizeDivClass noprint" onMouseDown="MouseDownToResize(this);" onMouseMove="MouseMoveToResize(this);" onMouseUp="MouseUpToResize(this);"><img src="images/box1.gif" width="3" height="18"></span><strong>性别</strong></td>	
<td align="center" nowrap="nowrap" class="kuangtitle"><span class="resizeDivClass noprint" onMouseDown="MouseDownToResize(this);" onMouseMove="MouseMoveToResize(this);" onMouseUp="MouseUpToResize(this);"><img src="images/box1.gif" width="3" height="18"></span><strong class="STYLE6">
卷内所处页码</strong></td>	
</tr>
<%
String bgcolor = "";

for(int i=0;i<10;i++){

if(i%2==0){
bgcolor="class='kuang15'";
}
else
{
bgcolor="class='kuang5'";
}
%>
<input name="allTr" type="hidden" value="ceshi">
<tr height="26" id="<%=i%>">
<td align="center" class="STYLE5" <%=bgcolor%>>p<%=i%></td>	
<td align="center" class="STYLE5" <%=bgcolor%>></td>		
<td align="center" class="STYLE5" <%=bgcolor%>></td>		
<%
}
%>
<%
	
		for(int j=0;j<10;j++)
		{
		%>
		<input name="Alltb" type="hidden" value="j">	
  <tr height="30" id="tb<%=j%>">
		<td>&nbsp;</td><td>&nbsp;</td>
		<td>&nbsp;</td>
  </tr>
		<%
		
		}

%>
</tr>
</table><table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="236">填卡人:</td>
    <td width="99">收到卡片认:</td>
    <td width="171">&nbsp;</td>
    <td align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</td>
  </tr>
</table>

<!--endprint-->
</body>
</html>

