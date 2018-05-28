
<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.sql.*,java.util.*,java.io.*"%>
<%@ page language="java" import="java.util.Date"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@ page import="com.noki.util.CTime,com.noki.query.tjhzquery.javabean.SjZnhtjBean" %>

<%request.setCharacterEncoding("UTF-8");%>
<%
String path= request.getContextPath();
%>
<%
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
%>

<html>
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
		try{ 
		printWB.ExecWB(v,1); 
		} 
		catch(e){} 
		} 

		//下一页
		function nextPage(o)
		{
			//alert(o);
			document.form1.action="../tjhzquery/cityStationPointHdlQuer_print.jsp?pageNum="+o;
			document.form1.submit();
		}
		//上一页
		function upPage(o)
		{
			//alert(o);
			document.form1.action="../tjhzquery/cityStationPointHdlQuer_print.jsp?pageNum="+o;
			document.form1.submit();
		}
		function befPage()
		{
			//var o=document.form1.elements["nowpagenum"].value;
			var o=document.getElementById("nowpagenum").value;

			//alert(o);
			document.form1.action="../tjhzquery/cityStationPointHdlQuer_print.jsp?pageNum="+o;
			
			document.form1.submit();
		}
		
		function inputin()
		{
			document.form1.action="../tjhzquery/cityStationPointHdlQuer_print.jsp?pageNum=1";
			document.form1.submit();
		}

		function showAll()
		{
			document.form1.elements["bgqx"].value="";
			document.form1.elements["jgmc"].value="";
			document.form1.elements["dabTime"].value="";
			document.form1.elements["daeTime"].value="";
			document.form1.elements["mj"].value="";
			document.form1.action="../basequery/cityStationPointHdlQuer_print.jsp";
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

<body onLoad="getHeight()" >
<!--startprint-->
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id="printWB"  width=0></OBJECT> 
	
<div style="text-align:center"  class="noprint">
<input onClick="document.all.printWB.ExecWB(6,6)" type="button" value="直接打印">
<input type="button" value="打印该页" onClick="ss(6)"> 
<input type="button" value="打印预览" onClick="ss(7)"> 
<input type="button" value="打印设置" onClick="ss(8)"> 
<input type="button" value="关闭窗口" onClick="javascript:window.close()">

</div>

<table width="100%" border="1"
cellpadding="0" cellspacing="0" align="center" bordercolor="#000000" style="border-collapse:collapse"  id="theObjTable">
                      <tr>
             <td width="7%" height="23" bgcolor="#888888"><div align="center">地市</div>
                                </td>
                                <td width="7%" height="23" bgcolor="#888888">                              
                                <div align="center">站点</div>
            			        </td>
                                <td width="7%" height="15" bgcolor="#888888"><div align="center">时间段</div>
            			        </td>
                                <td width="7%" height="15" bgcolor="#888888">
                                <div align="center">耗电量（总）</div>                                                    
            			        </td>
                                <td width="7%" height="15" bgcolor="#888888">
                                 <div align="center">机房耗电量</div>                                
            			        </td>
				                <td width="7%" height="15" bgcolor="#888888">
				                <div align="center">基站耗电量</div>				                
            			        </td>
				                <td width="7%" height="15" bgcolor="#888888">
				                <div align="center">营业网点耗电量</div>				               
            			        </td>
            			        <td width="7%" height="15" bgcolor="#888888">
            			        <div align="center">其它耗电量</div>
            			         </td>
                                 
                                                                                        
            </tr>
       <%

         String whereStr = request.getParameter("whereStr");	         
		 System.out.println("SjZnhtjBean-whereStr:"+whereStr);
         SjZnhtjBean bean = new SjZnhtjBean();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageDatahdlCity(curpage,whereStr);
       	 allpage=bean.getAllPage();
		String id="",
		zdname = "",
		shi = "",
		agname = "",
		sj = "",
		sum_hj="",
		SCYFHDL = "", 
		TXJFHDL= "",
		JZHDL = "",
		SJZXHDL="",
		JRJSHDL ="",
		
	    SWJUHDL = "", 
		FSCYFHDL= "",
		GLYFHDL = "",
		QDYFHDL="",
		QTFSCYFHDL ="";
         			//合计值
			String sum_zhj="",
			sum_SCYFHDL = "",
			sum_TXJFHDL = "",
			sum_JZHDL = "",
			sum_SJZXHDL = "",
			sum_JRJSHDL = "",
			
			sum_SWJUHDL = "",
			sum_FSCYFHDL = "",
			sum_GLYFHDL = "",
			sum_QDYFHDL = "",
			sum_QTFSCYFHDL = "";
         
         
         
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		    id = (String)fylist.get(k+fylist.indexOf("ID"));
			zdname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
			zdname = zdname != null ? zdname : "";
		    shi = (String)fylist.get(k+fylist.indexOf("SHI"));
		    shi = shi != null ? shi : "";
		    sj = (String)fylist.get(k+fylist.indexOf("SJ"));
		    sj = sj != null ? sj : "";
		    sum_hj = (String)fylist.get(k+fylist.indexOf("SUM_HJ"));
		    sum_hj = sum_hj != null ? sum_hj : "";
		    SCYFHDL = (String)fylist.get(k+fylist.indexOf("SCYFHDL"));
		    SCYFHDL = SCYFHDL != null ? SCYFHDL : "";
		    TXJFHDL = (String)fylist.get(k+fylist.indexOf("TXJFHDL"));
		    TXJFHDL = TXJFHDL != null ? TXJFHDL : "";
			JZHDL = (String)fylist.get(k+fylist.indexOf("JZHDL"));
			JZHDL = JZHDL != null ? JZHDL : "";
			SJZXHDL = (String)fylist.get(k+fylist.indexOf("SJZXHDL"));
			SJZXHDL = SJZXHDL != null ? SJZXHDL : "";
			
			sum_zhj = (String)fylist.get(k+fylist.indexOf("SUM_ZHJ"));
			sum_zhj = sum_zhj != null ? sum_zhj : "";
			sum_SCYFHDL = (String)fylist.get(k+fylist.indexOf("SUM_SCYFHDL"));
			sum_SCYFHDL = sum_SCYFHDL != null ? sum_SCYFHDL : "";
			sum_TXJFHDL = (String)fylist.get(k+fylist.indexOf("SUM_TXJFHDL"));
			sum_TXJFHDL = sum_TXJFHDL != null ? sum_TXJFHDL : "";
			sum_JZHDL = (String)fylist.get(k+fylist.indexOf("SUM_JZHDL"));
			sum_JZHDL = sum_JZHDL != null ? sum_JZHDL : "";
			sum_SJZXHDL = (String)fylist.get(k+fylist.indexOf("SUM_SJZXHDL"));
			sum_SJZXHDL = sum_SJZXHDL != null ? sum_SJZXHDL : "";
			
			
			//显示饼图数据串
			String hStr = SCYFHDL+";"+TXJFHDL+";"+JZHDL+";"+ SJZXHDL +";";

			String color=null;

				if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
           intnum++;

       %>
       <tr bgcolor="<%=color%>">      		
       		<td>
       			<div align="center" ><%=shi%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sj%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sum_hj%></div>
       		</td>
       		<td>
       			<div align="center" ><%=SCYFHDL%></div>
       		</td>
       		<td>
       			<div align="center" ><%=TXJFHDL%></div>
       		</td>
       		<td>
       			<div align="center" ><%=JZHDL%></div>
       		</td>
       		<td>
       			<div align="center" ><%=SJZXHDL%></div>
       		</td>  
       		    		
       </tr>
       <%} %>
        <tr bgcolor="F2F9FF">      		
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" >合计</div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ><%=sum_zhj%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sum_SCYFHDL%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sum_TXJFHDL%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sum_JZHDL%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sum_SJZXHDL%></div>
       		</td>  
       		 		
       </tr>
       <%} 
       %>
       
</table>
<!--endprint-->
</body>
</html>

