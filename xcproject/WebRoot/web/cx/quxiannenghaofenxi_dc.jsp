<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page session="true"%>
<%@ page language="java" import="java.sql.*,java.util.*,java.io.*,java.text.*"%>
<%@ page language="java" import="java.util.Date"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@ page import="com.noki.mobi.cx.QuXianNengHaoFenXi" %>

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
<link rel=File-List href="区县能耗比例分析.files/filelist.xml">
<link rel=Edit-Time-Data href="区县能耗比例分析.files/editdata.mso">
<link rel=OLE-Object-Data href="区县能耗比例分析.files/oledata.mso">
<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Created>1996-12-17T01:32:42Z</o:Created>
  <o:LastSaved>2011-11-13T08:02:12Z</o:LastSaved>
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
	mso-number-format:"\@";
	text-align:center;
	border:.5pt solid windowtext;}
.xl27
	{mso-style-parent:style0;
	mso-number-format:"\@";
	border:.5pt solid windowtext;}
.xl28
	{mso-style-parent:style0;
	mso-number-format:"0\.00_ ";
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
       <x:ActiveRow>10</x:ActiveRow>
       <x:ActiveCol>2</x:ActiveCol>
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

<table x:str border=0 cellpadding=0 cellspacing=0 width=733 style='border-collapse:
 collapse;table-layout:fixed;width:550pt'>
 <col width=72 style='width:54pt'>
 <col width=265 style='mso-width-source:userset;mso-width-alt:8480;width:199pt'>
 <col width=211 style='mso-width-source:userset;mso-width-alt:6752;width:158pt'>
 <col width=185 style='mso-width-source:userset;mso-width-alt:5920;width:139pt'>
 <tr height=38 style='mso-height-source:userset;height:28.5pt'>
  <td colspan=4 height=38 class=xl24 width=733 style='height:28.5pt;width:550pt'>区县能耗比例分析</td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl25 style='height:14.25pt;border-top:none'>
  <form name=form1 action="" method=post>
  序号</td>
  </form>
  <td class=xl25 style='border-top:none;border-left:none'>
  <form name=form1 action="" method=post>
  所在地区</td>
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
       QuXianNengHaoFenXi bean = new QuXianNengHaoFenXi();
       	 ArrayList fylist = new ArrayList();
       fylist = bean.getPageData(1,beginTime,endTime,shi);

		String agname="",dlcount = "",countfy = "",sumdl="",sumdf="",dianfei="",hejidf="";
		double df=0,dl=0;
		int intnum=1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     agname = (String)fylist.get(k+fylist.indexOf("XIAN"));
		     dlcount = (String)fylist.get(k+fylist.indexOf("DL"));
			 countfy = (String)fylist.get(k+fylist.indexOf("DF"));
			DecimalFormat mat=new DecimalFormat("0.0");
			DecimalFormat mat1=new DecimalFormat("0.00");
		    if(dlcount==null||dlcount=="")dlcount="0";
			if(countfy==null||countfy=="")countfy="0";
			
			dlcount=mat.format(Double.parseDouble(dlcount));
			countfy=mat1.format(Double.parseDouble(countfy));
			dl+=Double.parseDouble(dlcount);
			df+=Double.parseDouble(countfy);
			sumdl=mat.format(dl);
			hejidf=mat1.format(df);
				
		String color="#FFFFFF" ;

       %>
     <tr height=19 style='height:14.25pt'>
		  <td height=19 class=xl26 style='height:14.25pt;border-top:none' x:num><%=intnum++%></td>
		  <td class=xl27 style='border-top:none;border-left:none'><%=agname%></td>
		  <td class=xl28 align=right style='border-top:none;border-left:none'
		  x:num="<%=dlcount%>">234324.00 </td>
		  <td class=xl28 align=right style='border-top:none;border-left:none'
		  x:num="<%=countfy%>">23432432432.00 </td>
		 </tr>
       <%} %>
       
       <%}%>

 <![if supportMisalignedColumns]>
 <tr height=0 style='display:none'>
  <td width=72 style='width:54pt'></td>
  <td width=265 style='width:199pt'></td>
  <td width=211 style='width:158pt'></td>
  <td width=185 style='width:139pt'></td>
 </tr>
 <![endif]>
</table>

</body>

</html>
