<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page session="true"%>
<%@ page language="java" import="java.sql.*,java.util.*,java.io.*"%>
<%@ page language="java" import="java.util.Date"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@ page import="com.noki.mobi.cx.IDC" %>

<%request.setCharacterEncoding("UTF-8");%>
<%
String path= request.getContextPath();
String month = request.getParameter("month")!=null?request.getParameter("month"):"";

	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
	response.setHeader("Content-disposition","inline; filename="+new String("IDC用电量汇总表.xls".getBytes("gb2312"),"iso-8859-1")); 

%>
<html xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">

<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name=ProgId content=Excel.Sheet>
<meta name=Generator content="Microsoft Excel 11">
<link rel=File-List href="IDC用电量汇总表.files/filelist.xml">
<link rel=Edit-Time-Data href="IDC用电量汇总表.files/editdata.mso">
<link rel=OLE-Object-Data href="IDC用电量汇总表.files/oledata.mso">
<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Author>王路</o:Author>
  <o:LastAuthor>雨林木风</o:LastAuthor>
  <o:Created>2010-07-02T06:51:22Z</o:Created>
  <o:LastSaved>2012-01-11T11:45:49Z</o:LastSaved>
  <o:Company>CNC</o:Company>
  <o:Version>11.9999</o:Version>
 </o:DocumentProperties>
</xml><![endif]-->
<style>
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
@page
	{margin:1.0in .75in 1.0in .75in;
	mso-header-margin:.5in;
	mso-footer-margin:.5in;}
.font8
	{color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
tr
	{mso-height-source:auto;
	mso-ruby-visibility:none;}
col
	{mso-width-source:auto;
	mso-ruby-visibility:none;}
br
	{mso-data-placement:same-cell;}
.style16
	{mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	white-space:nowrap;
	mso-rotate:0;
	mso-background-source:auto;
	mso-pattern:auto;
	color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Helv, sans-serif;
	mso-font-charset:0;
	border:none;
	mso-protection:locked visible;
	mso-style-name:"";}
.style24
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
	mso-protection:locked visible;
	mso-style-name:3232;}
.style0
	{mso-number-format:General;
	text-align:general;
	vertical-align:middle;
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
.style44
	{mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	white-space:nowrap;
	mso-rotate:0;
	mso-background-source:auto;
	mso-pattern:auto;
	color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Helv, sans-serif;
	mso-font-charset:0;
	border:none;
	mso-protection:locked visible;
	mso-style-name:常规_3网络公司运维成本专业台账模版20100701;}
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
	vertical-align:middle;
	border:none;
	mso-background-source:auto;
	mso-pattern:auto;
	mso-protection:locked visible;
	white-space:nowrap;
	mso-rotate:0;}
.xl70
	{mso-style-parent:style44;
	font-size:10.0pt;
	white-space:normal;}
.xl71
	{mso-style-parent:style44;
	font-size:10.0pt;
	font-weight:700;
	text-align:center;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:none;
	border-left:.5pt solid windowtext;
	white-space:normal;}
.xl72
	{mso-style-parent:style44;
	font-size:10.0pt;
	text-align:center;
	white-space:normal;}
.xl73
	{mso-style-parent:style44;
	font-size:10.0pt;
	border:.5pt solid windowtext;
	white-space:normal;}
.xl74
	{mso-style-parent:style44;
	font-size:10.0pt;
	text-align:center;
	border:.5pt solid windowtext;
	white-space:normal;}
.xl75
	{mso-style-parent:style44;
	font-size:10.0pt;
	text-align:center;
	border:.5pt solid windowtext;
	white-space:normal;}
.xl76
	{mso-style-parent:style44;
	font-size:10.0pt;
	font-weight:700;
	white-space:normal;}
.xl77
	{mso-style-parent:style44;
	font-size:10.0pt;}
.xl78
	{mso-style-parent:style16;
	font-size:10.0pt;
	font-weight:700;
	text-align:center;
	border:.5pt solid windowtext;
	white-space:normal;}
.xl79
	{mso-style-parent:style44;
	font-size:10.0pt;
	mso-number-format:"Short Date";
	text-align:center;
	border:.5pt solid windowtext;
	white-space:normal;}
.xl80
	{mso-style-parent:style44;
	font-size:10.0pt;
	mso-number-format:"Short Date";
	text-align:center;
	border:.5pt solid windowtext;
	white-space:normal;}
.xl81
	{mso-style-parent:style44;
	font-size:10.0pt;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:top;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:none;
	border-left:.5pt solid windowtext;
	white-space:normal;}
.xl82
	{mso-style-parent:style44;
	font-size:10.0pt;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:top;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	white-space:normal;}
.xl83
	{mso-style-parent:style44;
	font-size:10.0pt;
	text-align:center;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:none;
	border-left:.5pt solid windowtext;
	white-space:normal;}
.xl84
	{mso-style-parent:style44;
	font-size:10.0pt;
	text-align:center;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	white-space:normal;}
.xl85
	{mso-style-parent:style44;
	color:black;
	font-size:11.0pt;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	white-space:normal;}
.xl86
	{mso-style-parent:style44;
	font-size:11.0pt;
	text-align:center;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	white-space:normal;}
.xl87
	{mso-style-parent:style44;
	color:black;
	font-size:11.0pt;
	text-align:center;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	white-space:normal;}
.xl88
	{mso-style-parent:style44;
	font-size:10.0pt;
	font-weight:700;
	text-align:center;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:.5pt solid windowtext;
	white-space:normal;}
.xl89
	{mso-style-parent:style44;
	font-size:10.0pt;
	font-weight:700;
	text-align:center;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:none;
	white-space:normal;}
.xl90
	{mso-style-parent:style44;
	font-size:10.0pt;
	font-weight:700;
	text-align:center;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:none;
	border-left:none;
	white-space:normal;}
.xl91
	{mso-style-parent:style24;
	font-weight:700;
	text-align:left;
	border:.5pt solid windowtext;
	white-space:normal;}
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
    <x:Name>电费-IDC汇总表</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>240</x:DefaultRowHeight>
     <x:Print>
      <x:ValidPrinterInfo/>
      <x:PaperSizeIndex>9</x:PaperSizeIndex>
      <x:HorizontalResolution>200</x:HorizontalResolution>
      <x:VerticalResolution>200</x:VerticalResolution>
     </x:Print>
     <x:TabColorIndex>42</x:TabColorIndex>
     <x:Selected/>
     <x:Panes>
      <x:Pane>
       <x:Number>3</x:Number>
       <x:RangeSelection>$A$1:$AE$5</x:RangeSelection>
      </x:Pane>
     </x:Panes>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
  </x:ExcelWorksheets>
  <x:WindowHeight>9120</x:WindowHeight>
  <x:WindowWidth>15480</x:WindowWidth>
  <x:WindowTopX>0</x:WindowTopX>
  <x:WindowTopY>570</x:WindowTopY>
  <x:ProtectStructure>False</x:ProtectStructure>
  <x:ProtectWindows>False</x:ProtectWindows>
 </x:ExcelWorkbook>
 <x:SupBook>
  <x:Path>I:\节能减排\移动网节能工作安排\附件5：基站电费台账模板.xls</x:Path>
  <x:SheetName>基础数据定义</x:SheetName>
  <x:SheetName>基站电费台账（基础管理用）</x:SheetName>
  <x:Xct>
   <x:Count>1</x:Count>
   <x:SheetIndex>0</x:SheetIndex>
   <x:Crn>
    <x:Row>7</x:Row>
    <x:ColFirst>0</x:ColFirst>
    <x:ColLast>4</x:ColLast>
    <x:Text>月交</x:Text>
    <x:Text>两月交</x:Text>
    <x:Text>季度交</x:Text>
    <x:Text>半年交</x:Text>
    <x:Text>年交</x:Text>
   </x:Crn>
  </x:Xct>
 </x:SupBook>
 <x:ExcelName>
  <x:Name>缴费周期</x:Name>
  <x:Formula>='I:\节能减排\移动网节能工作安排\[附件5：基站电费台账模板.xls]基础数据定义'!$A$8:$E$8</x:Formula>
 </x:ExcelName>
</xml><![endif]-->
</head>

<body link=blue vlink=purple class=xl70>

<table x:str border=0 cellpadding=0 cellspacing=0 width=1865 style='border-collapse:
 collapse;table-layout:fixed;width:1405pt'>
 <col class=xl70 width=87 style='mso-width-source:userset;mso-width-alt:2784;
 width:65pt'>
 <col class=xl70 width=80 style='mso-width-source:userset;mso-width-alt:2560;
 width:60pt'>
 <col class=xl70 width=81 style='mso-width-source:userset;mso-width-alt:2592;
 width:61pt'>
 <col class=xl70 width=98 style='mso-width-source:userset;mso-width-alt:3136;
 width:74pt'>
 <col class=xl70 width=84 style='mso-width-source:userset;mso-width-alt:2688;
 width:63pt'>
 <col class=xl70 width=62 span=5 style='mso-width-source:userset;mso-width-alt:
 1984;width:47pt'>
 <col class=xl70 width=55 style='mso-width-source:userset;mso-width-alt:1760;
 width:41pt'>
 <col class=xl70 width=56 style='mso-width-source:userset;mso-width-alt:1792;
 width:42pt'>
 <col class=xl70 width=50 style='mso-width-source:userset;mso-width-alt:1600;
 width:38pt'>
 <col class=xl70 width=52 style='mso-width-source:userset;mso-width-alt:1664;
 width:39pt'>
 <col class=xl70 width=62 style='mso-width-source:userset;mso-width-alt:1984;
 width:47pt'>
 <col class=xl70 width=52 span=6 style='mso-width-source:userset;mso-width-alt:
 1664;width:39pt'>
 <col class=xl70 width=42 style='mso-width-source:userset;mso-width-alt:1344;
 width:32pt'>
 <col class=xl70 width=54 style='mso-width-source:userset;mso-width-alt:1728;
 width:41pt'>
 <col class=xl70 width=57 style='mso-width-source:userset;mso-width-alt:1824;
 width:43pt'>
 <col class=xl70 width=55 style='mso-width-source:userset;mso-width-alt:1760;
 width:41pt'>
 <col class=xl70 width=59 style='mso-width-source:userset;mso-width-alt:1888;
 width:44pt'>
 <col class=xl70 width=50 style='mso-width-source:userset;mso-width-alt:1600;
 width:38pt'>
 <col class=xl70 width=54 style='mso-width-source:userset;mso-width-alt:1728;
 width:41pt'>
 <col class=xl70 width=70 style='mso-width-source:userset;mso-width-alt:2240;
 width:53pt'>
 <col class=xl70 width=45 style='mso-width-source:userset;mso-width-alt:1440;
 width:34pt'>
 <col class=xl70 width=52 style='mso-width-source:userset;mso-width-alt:1664;
 width:39pt'>
 <col class=xl70 width=44 span=225 style='mso-width-source:userset;mso-width-alt:
 1408;width:33pt'>
 <tr height=19 style='mso-height-source:userset;height:14.25pt'>
  <td colspan=31 height=19 class=xl91 width=1865 style='height:14.25pt;
  width:1405pt'>IDC机房用电量汇总表</td>
 </tr>
 <tr class=xl72 height=18 style='mso-height-source:userset;height:13.5pt'>
  <td rowspan=2 height=83 class=xl71 width=87 style='border-bottom:.5pt solid black;
  height:62.25pt;border-top:none;width:65pt'>总部、省公司名称</td>
  <td rowspan=2 class=xl71 width=80 style='border-bottom:.5pt solid black;
  border-top:none;width:60pt'>地市</td>
  <td rowspan=2 class=xl71 width=81 style='border-bottom:.5pt solid black;
  border-top:none;width:61pt'>IDC机房名称</td>
  <td rowspan=2 class=xl71 width=98 style='border-bottom:.5pt solid black;
  border-top:none;width:74pt'>机房类型<font class="font8">（五星/四星/三星/二星/一星/未评定）</font></td>
  <td rowspan=2 class=xl71 width=84 style='border-bottom:.5pt solid black;
  border-top:none;width:63pt'>用电类型<font class="font8">（商业用电/非普工业用电/居民用电）</font></td>
  <td colspan=11 class=xl88 width=637 style='border-right:.5pt solid black;
  border-left:none;width:481pt'>采用节能技术的机房</td>
  <td colspan=11 class=xl88 width=577 style='border-right:.5pt solid black;
  border-left:none;width:434pt'>未采用节能技术的机房</td>
  <td rowspan=2 class=xl71 width=54 style='border-bottom:.5pt solid black;
  border-top:none;width:41pt'>电费发生月份</td>
  <td rowspan=2 class=xl71 width=70 style='border-bottom:.5pt solid black;
  border-top:none;width:53pt'>录入日期</td>
  <td rowspan=2 class=xl71 width=45 style='border-bottom:.5pt solid black;
  border-top:none;width:34pt'>录入单位</td>
  <td rowspan=2 class=xl71 width=52 style='border-bottom:.5pt solid black;
  border-top:none;width:39pt'>录入人员</td>
 </tr>
 <tr class=xl72 height=65 style='mso-height-source:userset;height:48.75pt'>
  <td height=65 class=xl78 width=62 style='height:48.75pt;border-left:none;
  width:47pt'>机房面积(㎡)</td>
  <td class=xl78 width=62 style='border-left:none;width:47pt'>已使用面积(㎡)</td>
  <td class=xl78 width=62 style='border-left:none;width:47pt'>机柜<br>
    总数（个）</td>
  <td class=xl78 width=62 style='border-left:none;width:47pt'>已使用机柜数（个）</td>
  <td class=xl78 width=62 style='border-left:none;width:47pt'>U总数（个）</td>
  <td class=xl78 width=55 style='border-left:none;width:41pt'>已使用U数（个）</td>
  <td class=xl71 width=56 style='border-left:none;width:42pt'>实际抄表耗电总量（度）</td>
  <td class=xl71 width=50 style='border-left:none;width:38pt'>其中：设备耗电量（度）</td>
  <td class=xl71 width=52 style='border-left:none;width:39pt'>其中：空调耗电量（度）</td>
  <td class=xl71 width=62 style='border-left:none;width:47pt'>其中：照明及其他耗电量（度）</td>
  <td class=xl71 width=52 style='border-left:none;width:39pt'>实际电费金额（元）</td>
  <td class=xl78 width=52 style='border-left:none;width:39pt'>机房面积(㎡)</td>
  <td class=xl78 width=52 style='border-left:none;width:39pt'>已使用面积(㎡)</td>
  <td class=xl78 width=52 style='border-left:none;width:39pt'>机柜<br>
    总数（个）</td>
  <td class=xl78 width=52 style='border-left:none;width:39pt'>已使用机柜数（个）</td>
  <td class=xl78 width=52 style='border-left:none;width:39pt'>U总数（个）</td>
  <td class=xl78 width=42 style='border-left:none;width:32pt'>已使用U数（个）</td>
  <td class=xl71 width=54 style='border-left:none;width:41pt'>实际抄表耗电总量（度）</td>
  <td class=xl71 width=57 style='border-left:none;width:43pt'>其中：设备耗电量（度）</td>
  <td class=xl71 width=55 style='border-left:none;width:41pt'>其中：空调耗电量（度）</td>
  <td class=xl71 width=59 style='border-left:none;width:44pt'>其中：照明及其他耗电量（度）</td>
  <td class=xl71 width=50 style='border-left:none;width:38pt'>实际电费金额（元）</td>
 </tr>
 <%
       IDC bean = new IDC();
       	 ArrayList fylist = new ArrayList();
       fylist = bean.getPageData_IDC(shi,month);
       	
		String szdq="",jzname = "",jnglmk="",area="",gdfs = "",ysymj = "", jggs= "",ysygs="",ydcount="",jfcount="";
		int intnum=1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
				jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
				jnglmk = (String)fylist.get(k+fylist.indexOf("JNGLMK"));
		    area = (String)fylist.get(k+fylist.indexOf("AREA"));
		    gdfs = (String)fylist.get(k+fylist.indexOf("GDFS"));
		    ysymj = (String)fylist.get(k+fylist.indexOf("YSYMJ"));
		    jggs = (String)fylist.get(k+fylist.indexOf("JGGS"));
		    ysygs = (String)fylist.get(k+fylist.indexOf("YSYGS"));
		    ydcount = (String)fylist.get(k+fylist.indexOf("YDCOUNT"));
		    jfcount = (String)fylist.get(k+fylist.indexOf("JFCOUNT"));
				if(jfcount==null)jfcount="0";
				if(ydcount==null)ydcount="0";
				if(area==null)area="";
				if(ysymj==null)ysymj="";
				if(jggs==null)jggs="";
				if(ysygs==null)ysygs="";
			String color="#FFFFFF" ;

       %>
 <tr height=16 style='height:12.0pt'>
  <td height=32 class=xl83 width=87 style='border-bottom:.5pt solid black;
  height:24.0pt;border-top:none;width:65pt'>山东省</td>
  <td class=xl83 width=80 style='border-bottom:.5pt solid black;
  border-top:none;width:60pt'><%=szdq%></td>
  <td class=xl73 width=81 style='border-top:none;border-left:none;width:61pt'><%=jzname%></td>
  <td class=xl73 width=98 style='border-top:none;border-left:none;width:74pt'>　</td>
  <td class=xl73 width=84 style='border-top:none;border-left:none;width:63pt'><%=gdfs%></td>
  <%if(jnglmk.equals("有")){%>
  <td class=xl73 align=right width=62 style='border-top:none;border-left:none;
  width:47pt' x:num><%=area%></td>
  <td class=xl73 align=right width=62 style='border-top:none;border-left:none;
  width:47pt' x:num><%=ysymj%></td>
  <td class=xl73 align=right width=62 style='border-top:none;border-left:none;
  width:47pt' x:num><%=jggs%></td>
  <td class=xl73 align=right width=62 style='border-top:none;border-left:none;
  width:47pt' x:num><%=ysygs%></td>
  <td class=xl73 align=right width=62 style='border-top:none;border-left:none;
  width:47pt' x:num></td>
  <td class=xl73 align=right width=55 style='border-top:none;border-left:none;
  width:41pt' x:num></td>
  <td class=xl73 align=right width=56 style='border-left:none;width:42pt' x:num><%=ydcount%></td>
  <td class=xl73 align=right width=50 style='border-left:none;width:38pt' x:num></td>
  <td class=xl73 align=right width=52 style='border-left:none;width:39pt' x:num></td>
  <td class=xl73 align=right width=62 style='border-left:none;width:47pt' x:num></td>
  <td class=xl73 align=right width=52 style='border-left:none;width:39pt' x:num><%=jfcount%></td>
  <td class=xl73 align=right width=52 style='border-top:none;border-left:none;
  width:39pt' x:num></td>
  <td class=xl73 align=right width=52 style='border-top:none;border-left:none;
  width:39pt' x:num></td>
  <td class=xl73 align=right width=52 style='border-top:none;border-left:none;
  width:39pt' x:num></td>
  <td class=xl73 align=right width=52 style='border-top:none;border-left:none;
  width:39pt' x:num></td>
  <td class=xl73 align=right width=52 style='border-top:none;border-left:none;
  width:39pt' x:num></td>
  <td class=xl74 width=42 style='border-top:none;border-left:none;width:32pt'
  x:num></td>
  <td class=xl73 align=right width=54 style='border-left:none;width:41pt' x:num></td>
  <td class=xl73 align=right width=57 style='border-left:none;width:43pt' x:num></td>
  <td class=xl73 align=right width=55 style='border-left:none;width:41pt' x:num></td>
  <td class=xl73 align=right width=59 style='border-left:none;width:44pt' x:num></td>
  <td class=xl73 align=right width=50 style='border-left:none;width:38pt' x:num></td>
  <%}else{%>
  <td class=xl73 align=right width=62 style='border-top:none;border-left:none;
  width:47pt' x:num></td>
  <td class=xl73 align=right width=62 style='border-top:none;border-left:none;
  width:47pt' x:num></td>
  <td class=xl73 align=right width=62 style='border-top:none;border-left:none;
  width:47pt' x:num></td>
  <td class=xl73 align=right width=62 style='border-top:none;border-left:none;
  width:47pt' x:num></td>
  <td class=xl73 align=right width=62 style='border-top:none;border-left:none;
  width:47pt' x:num></td>
  <td class=xl73 align=right width=55 style='border-top:none;border-left:none;
  width:41pt' x:num></td>
  <td class=xl73 align=right width=56 style='border-left:none;width:42pt' x:num></td>
  <td class=xl73 align=right width=50 style='border-left:none;width:38pt' x:num></td>
  <td class=xl73 align=right width=52 style='border-left:none;width:39pt' x:num></td>
  <td class=xl73 align=right width=62 style='border-left:none;width:47pt' x:num></td>
  <td class=xl73 align=right width=52 style='border-left:none;width:39pt' x:num></td>
  <td class=xl73 align=right width=52 style='border-top:none;border-left:none;
  width:39pt' x:num><%=area%></td>
  <td class=xl73 align=right width=52 style='border-top:none;border-left:none;
  width:39pt' x:num><%=ysymj%></td>
  <td class=xl73 align=right width=52 style='border-top:none;border-left:none;
  width:39pt' x:num><%=jggs%></td>
  <td class=xl73 align=right width=52 style='border-top:none;border-left:none;
  width:39pt' x:num><%=ysygs%></td>
  <td class=xl73 align=right width=52 style='border-top:none;border-left:none;
  width:39pt' x:num></td>
  <td class=xl74 width=42 style='border-top:none;border-left:none;width:32pt'
  x:num></td>
  <td class=xl73 align=right width=54 style='border-left:none;width:41pt' x:num><%=ydcount%></td>
  <td class=xl73 align=right width=57 style='border-left:none;width:43pt' x:num></td>
  <td class=xl73 align=right width=55 style='border-left:none;width:41pt' x:num></td>
  <td class=xl73 align=right width=59 style='border-left:none;width:44pt' x:num></td>
  <td class=xl73 align=right width=50 style='border-left:none;width:38pt' x:num><%=jfcount%></td>
	<%}%>
  <td  class=xl81 width=54 style='border-bottom:.5pt solid black;
  border-top:none;width:41pt'><%=month%></td>
  <td  class=xl81 width=70 style='border-bottom:.5pt solid black;
  border-top:none;width:53pt'></td>
  <td  class=xl79 width=45 style='border-top:none;width:34pt'>　</td>
  <td  class=xl79 width=52 style='border-top:none;width:39pt'>　</td>
  
 </tr>
        <%} %>
       
       <%}%>
 
 <tr height=16 style='height:12.0pt'>
  <td height=16 colspan=31 class=xl70 style='height:12.0pt;mso-ignore:colspan'></td>
 </tr>
 <tr height=16 style='height:12.0pt'>
  <td height=16 class=xl76 style='height:12.0pt'></td>
  <td colspan=30 class=xl70 style='mso-ignore:colspan'></td>
 </tr>
 <tr height=16 style='height:12.0pt'>
  <td height=16 class=xl77 style='height:12.0pt'></td>
  <td colspan=30 class=xl70 style='mso-ignore:colspan'></td>
 </tr>
 <tr height=16 style='height:12.0pt'>
  <td height=16 class=xl77 style='height:12.0pt'></td>
  <td colspan=30 class=xl70 style='mso-ignore:colspan'></td>
 </tr>
 <tr height=16 style='height:12.0pt'>
  <td height=16 colspan=2 class=xl77 style='height:12.0pt;mso-ignore:colspan'></td>
  <td colspan=29 class=xl70 style='mso-ignore:colspan'></td>
 </tr>
 <tr height=16 style='height:12.0pt'>
  <td height=16 class=xl77 style='height:12.0pt'></td>
  <td colspan=30 class=xl70 style='mso-ignore:colspan'></td>
 </tr>
 <tr height=16 style='height:12.0pt'>
  <td height=16 class=xl77 style='height:12.0pt'></td>
  <td colspan=30 class=xl70 style='mso-ignore:colspan'></td>
 </tr>
 <![if supportMisalignedColumns]>
 <tr height=0 style='display:none'>
  <td width=87 style='width:65pt'></td>
  <td width=80 style='width:60pt'></td>
  <td width=81 style='width:61pt'></td>
  <td width=98 style='width:74pt'></td>
  <td width=84 style='width:63pt'></td>
  <td width=62 style='width:47pt'></td>
  <td width=62 style='width:47pt'></td>
  <td width=62 style='width:47pt'></td>
  <td width=62 style='width:47pt'></td>
  <td width=62 style='width:47pt'></td>
  <td width=55 style='width:41pt'></td>
  <td width=56 style='width:42pt'></td>
  <td width=50 style='width:38pt'></td>
  <td width=52 style='width:39pt'></td>
  <td width=62 style='width:47pt'></td>
  <td width=52 style='width:39pt'></td>
  <td width=52 style='width:39pt'></td>
  <td width=52 style='width:39pt'></td>
  <td width=52 style='width:39pt'></td>
  <td width=52 style='width:39pt'></td>
  <td width=52 style='width:39pt'></td>
  <td width=42 style='width:32pt'></td>
  <td width=54 style='width:41pt'></td>
  <td width=57 style='width:43pt'></td>
  <td width=55 style='width:41pt'></td>
  <td width=59 style='width:44pt'></td>
  <td width=50 style='width:38pt'></td>
  <td width=54 style='width:41pt'></td>
  <td width=70 style='width:53pt'></td>
  <td width=45 style='width:34pt'></td>
  <td width=52 style='width:39pt'></td>
 </tr>
 <![endif]>
</table>

</body>

</html>
