<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page session="true"%>
<%@ page language="java" import="java.sql.*,java.util.*,java.io.*"%>
<%@ page language="java" import="java.util.Date"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@ page import="com.noki.mobi.cx.JiZhanNengHao" %>

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
<link rel=File-List href="基站能耗查询.files/filelist.xml">
<link rel=Edit-Time-Data href="基站能耗查询.files/editdata.mso">
<link rel=OLE-Object-Data href="基站能耗查询.files/oledata.mso">
<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Created>1996-12-17T01:32:42Z</o:Created>
  <o:LastSaved>2011-11-13T07:53:21Z</o:LastSaved>
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
.xl29
	{mso-style-parent:style0;
	mso-number-format:"0_ ";
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
       <x:ActiveRow>2</x:ActiveRow>
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

<table x:str border=0 cellpadding=0 cellspacing=0 width=1030 style='border-collapse:
 collapse;table-layout:fixed;width:775pt'>
 <col width=66 style='mso-width-source:userset;mso-width-alt:2112;width:50pt'>
 <col width=132 style='mso-width-source:userset;mso-width-alt:4224;width:99pt'>
 <col width=177 style='mso-width-source:userset;mso-width-alt:5664;width:133pt'>
 <col width=242 style='mso-width-source:userset;mso-width-alt:7744;width:182pt'>
 <col width=126 style='mso-width-source:userset;mso-width-alt:4032;width:95pt'>
 <col width=70 style='mso-width-source:userset;mso-width-alt:2240;width:53pt'>
 <col width=145 style='mso-width-source:userset;mso-width-alt:4640;width:109pt'>
 <col width=72 style='width:54pt'>
 <tr height=33 style='mso-height-source:userset;height:24.75pt'>
  <td colspan=8 height=33 class=xl24 width=1030 style='height:24.75pt;
  width:775pt'>基站能耗查询</td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl25 style='height:14.25pt;border-top:none'>
  <form name=form1 action="" method=post>
  序号</td>
  </form>
  <td class=xl25 style='border-top:none;border-left:none'>
  <form name=form1 action="" method=post>
  基站编号</td>
  </form>
  <td class=xl25 style='border-top:none;border-left:none'>
  <form name=form1 action="" method=post>
  基站名称</td>
  </form>
  <td class=xl25 style='border-top:none;border-left:none'>
  <form name=form1 action="" method=post>
  所在地区</td>
  </form>
  <td class=xl25 style='border-top:none;border-left:none'>
  <form name=form1 action="" method=post>
  合计用电量</td>
  </form>
  <td class=xl25 style='border-top:none;border-left:none'>
  <form name=form1 action="" method=post>
  抄表次数</td>
  </form>
  <td class=xl25 style='border-top:none;border-left:none'>
  <form name=form1 action="" method=post>
  合计交费数额</td>
  </form>
  <td class=xl25 style='border-top:none;border-left:none'>
  <form name=form1 action="" method=post>
  交费次数</td>
 </tr>
 </form>
 <%
       JiZhanNengHao bean = new JiZhanNengHao();
       	 ArrayList fylist = new ArrayList();
       fylist = bean.getPageData(beginTime,endTime,shi,xian,xiaoqu);
       	System.out.println(fylist.toString());
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
		    cbcount = (String)fylist.get(k+fylist.indexOf("NUMDL"));//
		    ydcount = (String)fylist.get(k+fylist.indexOf("DEGREE"));//
		    jfcount = (String)fylist.get(k+fylist.indexOf("NUMDF"));//交费
		    jfcs = (String)fylist.get(k+fylist.indexOf("PAY"));//交费金额
				if(jfcount==null)jfcount="0";
				if(ydcount==null)ydcount="0";
			String color="#FFFFFF" ;

       %>
       
       <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl26 style='height:14.25pt;border-top:none' x:num><%=intnum++%></td>
  <td class=xl27 style='border-top:none;border-left:none'><%=jzcode%></td>
  <td class=xl27 style='border-top:none;border-left:none'><%=jzname%></td>
  <td class=xl27 style='border-top:none;border-left:none'><%=szdq%></td>
  <td class=xl28 align=right style='border-top:none;border-left:none'
  x:num="<%=ydcount%>"></td>
  <td class=xl29 align=right style='border-top:none;border-left:none' x:num="<%=cbcount%>">
  </td>
  <td class=xl28 align=right style='border-top:none;border-left:none'
  x:num="<%=jfcs%>"></td>
  <td class=xl29 align=right style='border-top:none;border-left:none'
  x:num="<%=jfcount%>"></td>
 </tr>
       <%} %>
       
       <%}%>
 
 <![if supportMisalignedColumns]>
 <tr height=0 style='display:none'>
  <td width=66 style='width:50pt'></td>
  <td width=132 style='width:99pt'></td>
  <td width=177 style='width:133pt'></td>
  <td width=242 style='width:182pt'></td>
  <td width=126 style='width:95pt'></td>
  <td width=70 style='width:53pt'></td>
  <td width=145 style='width:109pt'></td>
  <td width=72 style='width:54pt'></td>
 </tr>
 <![endif]>
</table>

</body>

</html>
