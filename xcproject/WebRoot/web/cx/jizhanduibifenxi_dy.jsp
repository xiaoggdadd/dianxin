<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.sql.*,java.util.*,java.io.*"%>
<%@ page language="java" import="java.util.Date"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@ page import="com.noki.mobi.cx.JiZhanDuiBiFenXi" %>

<%request.setCharacterEncoding("UTF-8");%>
<%
String path= request.getContextPath();
String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";
        String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";
       
	String sheng = request.getParameter("sheng")!=null?request.getParameter("sheng"):"0";
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
String jza = request.getParameter("jza")!=null?request.getParameter("jza"):"0";
String jzb = request.getParameter("jzb")!=null?request.getParameter("jzb"):"0";
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>基站对比分析</title>
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
		try{ 
		printWB.ExecWB(v,1); 
		} 
		catch(e){} 
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
<input onClick="document.all.printWB.ExecWB(6,6)" type="button" value="直接打印">
<%--<input type="button" value="打印该页" onClick="ss(6)"> --%>
<input type="button" value="打印预览" onClick="ss(7)"> 
<input type="button" value="打印设置" onClick="ss(8)"> 
<input type="button" value="关闭窗口" onClick="javascript:window.close()">




<form name="form1" method="post" action="">

<table width="800" align="center" border="0" cellpadding="0" cellspacing="0">

<tr>
	  
    </table>
</form>
</div>

<table width="650" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="72%" colspan="4"  align="center"><span class="STYLE1">基站对比分析</span></td>
  </tr>

</table>

<table width="650" border="1"
cellpadding="0" cellspacing="0" align="center" bordercolor="#000000" style="border-collapse:collapse"  id="theObjTable">

<tr>
             <td align="center" nowrap="nowrap" class="kuangtitle"><span class="resizeDivClass noprint" onMouseDown="MouseDownToResize(this);" onMouseMove="MouseMoveToResize(this);" onMouseUp="MouseUpToResize(this);"><img src="images/box1.gif" width="3" height="18"></span><strong class="STYLE6">序号</strong>
                                </td>
                               
                                <td align="center" nowrap="nowrap" class="kuangtitle"><span class="resizeDivClass noprint" onMouseDown="MouseDownToResize(this);" onMouseMove="MouseMoveToResize(this);" onMouseUp="MouseUpToResize(this);"><img src="images/box1.gif" width="3" height="18"></span><strong class="STYLE6">基站编号</strong>
            			</td>
                                <td align="center" nowrap="nowrap" class="kuangtitle"><span class="resizeDivClass noprint" onMouseDown="MouseDownToResize(this);" onMouseMove="MouseMoveToResize(this);" onMouseUp="MouseUpToResize(this);"><img src="images/box1.gif" width="3" height="18"></span><strong class="STYLE6">基站名称</strong>
            			</td>
            			<td align="center" nowrap="nowrap" class="kuangtitle"><span class="resizeDivClass noprint" onMouseDown="MouseDownToResize(this);" onMouseMove="MouseMoveToResize(this);" onMouseUp="MouseUpToResize(this);"><img src="images/box1.gif" width="3" height="18"></span><strong class="STYLE6">所在地区</strong>
            			</td>

                                    <td align="center" nowrap="nowrap" class="kuangtitle"><span class="resizeDivClass noprint" onMouseDown="MouseDownToResize(this);" onMouseMove="MouseMoveToResize(this);" onMouseUp="MouseUpToResize(this);"><img src="images/box1.gif" width="3" height="18"></span><strong class="STYLE6">合计用电量</strong>
            			</td>
            			<td align="center" nowrap="nowrap" class="kuangtitle"><span class="resizeDivClass noprint" onMouseDown="MouseDownToResize(this);" onMouseMove="MouseMoveToResize(this);" onMouseUp="MouseUpToResize(this);"><img src="images/box1.gif" width="3" height="18"></span><strong class="STYLE6">抄表次数</strong>
            			</td>
				<td align="center" nowrap="nowrap" class="kuangtitle"><span class="resizeDivClass noprint" onMouseDown="MouseDownToResize(this);" onMouseMove="MouseMoveToResize(this);" onMouseUp="MouseUpToResize(this);"><img src="images/box1.gif" width="3" height="18"></span><strong class="STYLE6">合计交费数额</strong>
            			</td>
            			<td align="center" nowrap="nowrap" class="kuangtitle"><span class="resizeDivClass noprint" onMouseDown="MouseDownToResize(this);" onMouseMove="MouseMoveToResize(this);" onMouseUp="MouseUpToResize(this);"><img src="images/box1.gif" width="3" height="18"></span><strong class="STYLE6">交费次数</strong>
            			</td>
				
            </tr>
   <%
       JiZhanDuiBiFenXi bean = new JiZhanDuiBiFenXi();
       	 ArrayList fylist = new ArrayList();
       fylist = bean.getPageData(jza,jzb,beginTime,endTime);
		String jzcode="",jzname = "",ydcount = "",cbcount = "", jfcount= "",jfcs="",szdq="";
		int intnum=1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     jzcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
				jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
				szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		    cbcount = (String)fylist.get(k+fylist.indexOf("CBCOUNT"));
		    ydcount = (String)fylist.get(k+fylist.indexOf("YDCOUNT"));
		    jfcount = (String)fylist.get(k+fylist.indexOf("JFCOUNT"));
		    jfcs = (String)fylist.get(k+fylist.indexOf("JFCS"));
				if(jfcount==null)jfcount="0";
				if(ydcount==null)ydcount="0";
			String color="#FFFFFF" ;

       %>
       <input name="allTr" type="hidden" value="ceshi">
       <tr bgcolor="<%=color%>" id="<%=intnum%>">
       		<td>
       			<div align="center" ><%=intnum++%></div>
       		</td>
       <td>
       			<div align="center" ><%=jzcode%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center" ><%=ydcount%></div>
       		</td>
       		<td>
       			<div align="center" ><%=cbcount%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jfcount%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jfcs%></div>
       		</td>
       		

       </tr>
       <%} %>
       
       <%}%>
</tr>


<!--endprint-->
</body>
</html>

