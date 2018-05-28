
<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.sql.*,java.util.*,java.io.*"%>
<%@ page language="java" import="java.util.Date"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@ page import="com.noki.util.CTime,com.noki.query.hnlcomparison.javabean.HnlComparisonBean" %>

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
			document.form1.action="../tjhzquery/cityNhtjZhnlquery_print.jsp?pageNum="+o;
			document.form1.submit();
		}
		//上一页
		function upPage(o)
		{
			//alert(o);
			document.form1.action="../tjhzquery/cityNhtjZhnlquery_print.jsp?pageNum="+o;
			document.form1.submit();
		}
		function befPage()
		{
			//var o=document.form1.elements["nowpagenum"].value;
			var o=document.getElementById("nowpagenum").value;

			//alert(o);
			document.form1.action="../tjhzquery/cityNhtjZhnlquery_print.jsp?pageNum="+o;
			
			document.form1.submit();
		}
		
		function inputin()
		{
			document.form1.action="../tjhzquery/cityNhtjZhnlquery_print.jsp?pageNum=1";
			document.form1.submit();
		}

		function showAll()
		{
			document.form1.elements["bgqx"].value="";
			document.form1.elements["jgmc"].value="";
			document.form1.elements["dabTime"].value="";
			document.form1.elements["daeTime"].value="";
			document.form1.elements["mj"].value="";
			document.form1.action="../basequery/cityNhtjZhnlquery_print.jsp";
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
             <td width="10%" height="23" bgcolor="#888888"><div align="center">地市</div>
                                </td>
                                <td width="10%" height="23" bgcolor="#888888"><div align="center">时间段</div>
            			        </td>
                                <td width="8%" height="23" bgcolor="#888888">
                                <div align="center">实际耗电量</div>
                                <hr>
                                <div align="center">度</div>                              
            			        </td>
                                  <td width="8%" height="23" bgcolor="#888888">
                                <div align="center">预算耗电量</div>
                                <hr>
                                <div align="center">度</div>                              
            			        </td>
                                <td width="8%" height="23" bgcolor="#888888">
                                 <div align="center">实际水消耗量</div>
                                <hr>
                                <div align="center">吨</div>
            			        </td>
            			         <td width="8%" height="23" bgcolor="#888888">
                                 <div align="center">预算水消耗量</div>
                                <hr>
                                <div align="center">吨</div>
            			        </td>
				                <td width="8%" height="23" bgcolor="#888888">
				                <div align="center">实际汽油消耗量</div>
				                 <hr>
                                <div align="center">升</div>
            			        </td>
            			        <td width="8%" height="23" bgcolor="#888888">
				                <div align="center">预算汽油消耗量</div>
				                 <hr>
                                <div align="center">升</div>
            			        </td>
				                <td width="8%" height="23" bgcolor="#888888">
				                <div align="center">实际柴油消耗量</div>
				                <hr>
                                <div align="center">升</div>
            			        </td>
            			         <td width="8%" height="23" bgcolor="#888888">
				                <div align="center">预算柴油消耗量</div>
				                <hr>
                                <div align="center">升</div>
            			        </td>
            			        <td width="8%" height="23" bgcolor="#888888">
            			        <div align="center">实际天然气消耗量</div>
            			         <hr>
                                <div align="center">立方米</div>
                                <td width="8%" height="23" bgcolor="#888888">
            			        <div align="center">预算天然气消耗量</div>
            			         <hr>
                                <div align="center">立方米</div>
                                                                                        
            </tr>
       <%
         String whereStr = request.getParameter("whereStr");	         
		System.out.println("AmmeterDegreeBean-whereStr:"+whereStr);
			      
         HnlComparisonBean bean = new HnlComparisonBean();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageDataZhnSjysFXCity(curpage,whereStr);
       	 allpage=bean.getAllPage();
		String id="",
		zdname = "",
		shi = "",
		agname = "",
		sj = "",
		hdl = "", 
		sxhl= "",
		qyxhl = "",
		cyxhl="",
		trqxhl="",
		hdlys = "", 
		sxhlys= "",
		qyxhlys = "",
		cyxhlys="",
		trqxhlys="";
         			
         String cityStr = "";
         String dataStr = "";
         String dataStrhdl = "";
         String dataStrsxhl = "";
         String dataStrqyxhl = "";
         String dataStrcyxhl = "";
         String dataStrtrqxhl = "";
         
         String dataStrhdlys = "";
         String dataStrsxhlys = "";
         String dataStrqyxhlys = "";
         String dataStrcyxhlys = "";
         String dataStrtrqxhlys = "";
         
         String timeStr = "";			
         
         
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		    id = (String)fylist.get(k+fylist.indexOf("ID"));
		    shi = (String)fylist.get(k+fylist.indexOf("SHI"));
		    shi = shi != null ? shi : "";
		    sj = (String)fylist.get(k+fylist.indexOf("SJ"));
		    sj = sj != null ? sj : "";
		    hdl = (String)fylist.get(k+fylist.indexOf("HDL_SJ"));
		    hdl = hdl != null ? hdl : "";
		    sxhl = (String)fylist.get(k+fylist.indexOf("SXHL_SJ"));
		    sxhl = sxhl != null ? sxhl : "";
			qyxhl = (String)fylist.get(k+fylist.indexOf("QYXHL_SJ"));
			qyxhl = qyxhl != null ? qyxhl : "";
			cyxhl = (String)fylist.get(k+fylist.indexOf("CYXHL_SJ"));
			cyxhl = cyxhl != null ? cyxhl : "";
			trqxhl = (String)fylist.get(k+fylist.indexOf("TRQXHL_SJ"));
			trqxhl = trqxhl != null ? trqxhl : "";
			 hdlys = (String)fylist.get(k+fylist.indexOf("HDL_YS"));
			 hdlys = hdlys != null ? hdlys : "";
		    sxhlys = (String)fylist.get(k+fylist.indexOf("SXHL_YS"));
		    sxhlys = sxhlys != null ? sxhlys : "";
			qyxhlys = (String)fylist.get(k+fylist.indexOf("QYXHL_YS"));
			qyxhlys = qyxhlys != null ? qyxhlys : "";
			cyxhlys = (String)fylist.get(k+fylist.indexOf("CYXHL_YS"));
			cyxhlys = cyxhlys != null ? cyxhlys : "";
			trqxhlys = (String)fylist.get(k+fylist.indexOf("TRQXHL_YS"));	
			trqxhlys = trqxhlys != null ? trqxhlys : "";
            
            //折线图数据
            dataStrhdl = dataStrhdl + hdl+";";         
            dataStrsxhl = dataStrsxhl + sxhl+";";
            dataStrqyxhl = dataStrqyxhl + qyxhl+";";
            dataStrcyxhl = dataStrcyxhl + cyxhl+";";
            dataStrtrqxhl = dataStrtrqxhl + trqxhl+";";
            
            dataStrhdlys = dataStrhdlys + hdlys+";";         
            dataStrsxhlys = dataStrsxhlys + sxhlys+";";
            dataStrqyxhlys = dataStrqyxhlys + qyxhlys+";";
            dataStrcyxhlys = dataStrcyxhlys + cyxhlys+";";
            dataStrtrqxhlys = dataStrtrqxhlys + trqxhlys+";";
            
            timeStr = timeStr + shi+":"+sj+";";
            
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
       			<div align="center" ><%=sj%></div>
       		</td>
       		<td>
       			<div align="center" ><%=hdl%></div>
       		</td>
       		<td>
       			<div align="center" ><%=hdlys%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sxhl%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sxhlys%></div>
       		</td>
       		<td>
       			<div align="center" ><%=qyxhl%></div>
       		</td>
       		<td>
       			<div align="center" ><%=qyxhlys%></div>
       		</td>
       		<td>
       			<div align="center" ><%=cyxhl%></div>
       		</td>  
       		<td>
       			<div align="center" ><%=cyxhlys%></div>
       		</td>  
       		<td>
       			<div align="center" ><%=trqxhl%></div>
       		</td>  
       		<td>
       			<div align="center" ><%=trqxhlys%></div>
       		</td>        		
       </tr>
       <%} 
       }
        dataStr = dataStrhdl + "/"+dataStrhdlys + "/"+dataStrsxhl + "/"+dataStrsxhlys + "/"+dataStrqyxhl + "/"+dataStrqyxhlys + "/"+dataStrcyxhl + "/"+dataStrcyxhlys + "/"+dataStrtrqxhl+ "/"+dataStrtrqxhlys;
       %>
       
</table>
<!--endprint-->
</body>
</html>

