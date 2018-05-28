<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns:v="urn:schemas-microsoft-com:vml"
xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">

<head>
<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
<meta name=ProgId content=Excel.Sheet>
<meta name=Generator content="Microsoft Excel 11">
<!--[if !mso]>
<style>
v\:* {behavior:url(#default#VML);}
o\:* {behavior:url(#default#VML);}
x\:* {behavior:url(#default#VML);}
.shape {behavior:url(#default#VML);}
</style>
<![endif]--><!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Created>1996-12-17T01:32:42Z</o:Created>
  <o:LastSaved>2008-08-11T02:22:29Z</o:LastSaved>
  <o:Version>11.9999</o:Version>
 </o:DocumentProperties>
 <o:OfficeDocumentSettings>
  <o:RemovePersonalInformation/>
 </o:OfficeDocumentSettings>
</xml><![endif]-->
<style>
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
@page
	{margin:1.0in .75in 1.0in .75in;
	mso-header-margin:.5in;
	mso-footer-margin:.5in;}
tr
	{mso-height-source:auto;
	mso-ruby-visibility:none;}
col
	{mso-width-source:auto;
	mso-ruby-visibility:none;}
br
	{mso-data-placement:same-cell;}
.style0
	{mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	white-space:nowrap;
	mso-rotate:0;
	mso-background-source:auto;
	mso-pattern:auto;
	color:windowtext;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	border:none;
	mso-protection:locked visible;
	mso-style-name:常规;
	mso-style-id:0;}
td
	{mso-style-parent:style0;
	padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border:none;
	mso-background-source:auto;
	mso-pattern:auto;
	mso-protection:locked visible;
	white-space:nowrap;
	mso-rotate:0;}
.xl24
	{mso-style-parent:style0;
	text-align:center;}
.xl25
	{mso-style-parent:style0;
	text-align:center;
	border:.5pt solid windowtext;}
.xl26
	{mso-style-parent:style0;
	border:.5pt solid windowtext;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-char-type:none;
	display:none;}
-->
</style>
<!--[if gte mso 9]><xml>
 <x:ExcelWorkbook>
  <x:ExcelWorksheets>
   <x:ExcelWorksheet>
    <x:Name>Sheet1</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:CodeName>Sheet1</x:CodeName>
     <x:Selected/>
     <x:Panes>
      <x:Pane>
       <x:Number>3</x:Number>
       <x:ActiveRow>6</x:ActiveRow>
       <x:ActiveCol>6</x:ActiveCol>
      </x:Pane>
     </x:Panes>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
   <x:ExcelWorksheet>
    <x:Name>Sheet2</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:CodeName>Sheet2</x:CodeName>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
   <x:ExcelWorksheet>
    <x:Name>Sheet3</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:CodeName>Sheet3</x:CodeName>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
  </x:ExcelWorksheets>
  <x:WindowHeight>4530</x:WindowHeight>
  <x:WindowWidth>8505</x:WindowWidth>
  <x:WindowTopX>480</x:WindowTopX>
  <x:WindowTopY>120</x:WindowTopY>
  <x:AcceptLabelsInFormulas/>
  <x:ProtectStructure>False</x:ProtectStructure>
  <x:ProtectWindows>False</x:ProtectWindows>
 </x:ExcelWorkbook>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <o:shapedefaults v:ext="edit" spidmax="1028"/>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <o:shapelayout v:ext="edit">
  <o:idmap v:ext="edit" data="1"/>
 </o:shapelayout></xml><![endif]-->
</head>
<jsp:useBean id="bean" scope="page" class="com.noki.electricfees.javabean.ElectricFeesBean">
</jsp:useBean>


<body link=blue vlink=purple>
       <%
        //Vector v = bean.getHuanbi_all("0",qijian,qyear);         
       %>
<table x:str border=1 cellpadding=0 cellspacing=0 width=705 style='border-collapse:
 collapse;table-layout:fixed;width:530pt'>
 <tr class=xl24 height=19 style='height:14.25pt'>  
 <td class=xl25 width=152 style='border-left:none;width:114pt'>序号</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>城市</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>区县</td>  
  <td class=xl25 width=152 style='border-left:none;width:124pt'>乡镇</td>
  <td class=xl25 width=152 style='border-left:none;width:124pt'>站点名称</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>报账月份</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>上次电表读数</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>本次电表读数</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>上次抄表时间</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>本次抄表时间</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>报账用电量</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>用电量调整</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>电量调整备注</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>本次单价</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>报账电费</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>电费调整</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>电费调整备注</td>
 <td class=xl25 width=152 style='border-left:none;width:54pt'>结算周期</td>
 <td class=xl25 width=152 style='border-left:none;width:54pt'>倍率</td>
 <td class=xl25 width=152 style='border-left:none;width:54pt'>站点属性</td>
 <td class=xl25 width=152 style='border-left:none;width:54pt'>站点类型</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>流程号</td>
  <td class=xl25 width=152 style='border-left:none;width:54pt'>站点ID</td>
  <td class=xl25 width=152 height=19  style='height:14.25pt;width:107pt'>电表ID</td>
 </tr>
<c:forEach items="${beanlist}"  var="list" varStatus="status">
 <tr height=19 style='height:14.25pt'>
 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>${status.count}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.shi}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.xian}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.xiaoqu}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.jzname}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.accountmonth}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.lastdegree}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.thisdegree}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.lastdatetime}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.thisdatetime}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.blhdl}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.floatdegree}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.amemo}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.unitprice}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.actualpay}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.floatpay}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.ememo}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.jszq}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.beilv}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.property}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.stationtype}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.liuchenghao}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.zdid}</td>
  <td class=xl26 align=right style='border-top:none;border-left:none'>${list.dbid}</td>
 </tr>
</c:forEach>         
  <tr> 
  	<td colspan="10">
  	<div align="center">
		<font color='#000080'>&nbsp;导出条数:</font>
		 <b><font color=red>${numcolor}条；</font></b>
	</div>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
