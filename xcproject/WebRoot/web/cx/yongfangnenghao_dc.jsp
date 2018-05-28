<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page session="true"%>
<%@ page language="java" import="java.sql.*,java.util.*,java.io.*"%>
<%@ page language="java" import="java.util.Date"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@ page import="com.noki.mobi.cx.YongFangNengHao" %>

<%request.setCharacterEncoding("UTF-8");%>
<%
String path= request.getContextPath();
String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";
        String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";
       
	String sheng = request.getParameter("sheng")!=null?request.getParameter("sheng"):"0";
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";

%>
<html xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">

<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name=ProgId content=Excel.Sheet>
<meta name=Generator content="Microsoft Excel 11">
<link rel=File-List href="用房类型能耗统计.files/filelist.xml">
<link rel=Edit-Time-Data href="用房类型能耗统计.files/editdata.mso">
<link rel=OLE-Object-Data href="用房类型能耗统计.files/oledata.mso">
<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Created>1996-12-17T01:32:42Z</o:Created>
  <o:LastSaved>2011-11-13T08:08:04Z</o:LastSaved>
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
	padding-top:1px;
	padding-right:1px;
	padding-left:1px;
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
	font-size:16.0pt;
	font-weight:700;
	text-align:center;
	border:.5pt solid windowtext;}
.xl25
	{mso-style-parent:style0;
	font-weight:700;
	text-align:center;
	border:.5pt solid windowtext;}
.xl26
	{mso-style-parent:style0;
	mso-number-format:"0\.00_ ";
	border:.5pt solid windowtext;}
.xl27
	{mso-style-parent:style0;
	mso-number-format:"\@";
	border:.5pt solid windowtext;}
.xl28
	{mso-style-parent:style0;
	mso-number-format:"\@";
	text-align:center;
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
     <x:Print>
      <x:ValidPrinterInfo/>
      <x:PaperSizeIndex>9</x:PaperSizeIndex>
      <x:HorizontalResolution>600</x:HorizontalResolution>
      <x:VerticalResolution>600</x:VerticalResolution>
     </x:Print>
     <x:CodeName>Sheet1</x:CodeName>
     <x:Selected/>
     <x:Panes>
      <x:Pane>
       <x:Number>3</x:Number>
       <x:ActiveRow>5</x:ActiveRow>
       <x:ActiveCol>7</x:ActiveCol>
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
</xml><![endif]-->
</head>

<body link=blue vlink=purple>

<table x:str border=0 cellpadding=0 cellspacing=0 width=609 style='border-collapse:
 collapse;table-layout:fixed;width:457pt'>
 <col width=72 style='width:54pt'>
 <col width=170 style='mso-width-source:userset;mso-width-alt:5440;width:128pt'>
 <col width=184 style='mso-width-source:userset;mso-width-alt:5888;width:138pt'>
 <col width=183 style='mso-width-source:userset;mso-width-alt:5856;width:137pt'>
 <tr height=34 style='mso-height-source:userset;height:25.5pt'>
  <td colspan=4 height=34 class=xl24 width=609 style='height:25.5pt;width:457pt'>用房类型能耗统计</td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl25 style='height:14.25pt;border-top:none'>
  <form name=form1 action="" method=post>
  序号</td>
  </form>
  <td class=xl25 style='border-top:none;border-left:none'>
  <form name=form1 action="" method=post>
  用房类型</td>
  </form>
  <td class=xl25 style='border-top:none;border-left:none'>
  <form name=form1 action="" method=post>
  电量合计</td>
  </form>
  <td class=xl25 style='border-top:none;border-left:none'>
  <form name=form1 action="" method=post>
  费用合计</td>
 </tr>
 </form>
 <%
       YongFangNengHao bean = new YongFangNengHao();
       	 ArrayList fylist = new ArrayList();
       fylist = bean.getPageData(1,beginTime,endTime,shi,xian,xiaoqu);
       	
		String jzname = "",ydcount = "",countfy="";
		int intnum=1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
				jzname = (String)fylist.get(k+fylist.indexOf("NAME"));
		    ydcount = (String)fylist.get(k+fylist.indexOf("DLCOUNT"));
		    countfy = (String)fylist.get(k+fylist.indexOf("FYCOUNT"));
if(ydcount==null)ydcount="0";
				if(countfy==null)countfy="0";
			String color="#FFFFFF" ;

		
       %>
       
       <tr height=19 style='height:14.25pt'>
			  <td height=19 class=xl28 style='height:14.25pt;border-top:none' x:num><%=intnum++%></td>
			  <td class=xl27 style='border-top:none;border-left:none'><%=jzname%></td>
			  <td class=xl26 align=right style='border-top:none;border-left:none'
			  x:num="<%=ydcount%>">234324.00 </td>
			  <td class=xl26 align=right style='border-top:none;border-left:none'
			  x:num="<%=countfy%>">234324.00 </td>
			 </tr>
       <%} %>
       
       <%}%>
 
 <![if supportMisalignedColumns]>
 <tr height=0 style='display:none'>
  <td width=72 style='width:54pt'></td>
  <td width=170 style='width:128pt'></td>
  <td width=184 style='width:138pt'></td>
  <td width=183 style='width:137pt'></td>
 </tr>
 <![endif]>
</table>

</body>

</html>
