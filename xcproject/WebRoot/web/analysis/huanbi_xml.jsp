﻿<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
<%

        Account account = new Account();
        account = (Account)session.getAttribute("account");
        String qyear = request.getParameter("qyear");
		CTime time = new CTime();
		String accountId = account.getAccountId();
                String path = request.getContextPath();
	String dwxz = request.getParameter("dwxz")!=null?request.getParameter("dwxz"):"2";
		String month = String.valueOf(Integer.parseInt(time.formatShortDate().substring(4, 6)));
        String qijian = request.getParameter("qijian")!=null?request.getParameter("qijian"):month;
        String dw = request.getParameter("dw")!=null?request.getParameter("dw"):"0";

%>
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
<jsp:useBean id="bean" scope="page" class="com.noki.mobi.analysis.javabean.HB">
</jsp:useBean>


<body link=blue vlink=purple>
<%
            	Vector v = bean.getHuanbi_all("0",qijian,qyear);
%>
<table x:str border=0 cellpadding=0 cellspacing=0 width=705 style='border-collapse:
 collapse;table-layout:fixed;width:530pt'>
 <col width=142 style='mso-width-source:userset;mso-width-alt:4544;width:107pt'>
 <col width=152 style='mso-width-source:userset;mso-width-alt:4864;width:114pt'>
 <col width=174 style='mso-width-source:userset;mso-width-alt:5568;width:131pt'>
 <col width=165 style='mso-width-source:userset;mso-width-alt:5280;width:124pt'>
 <col width=72 style='width:54pt'>
 <tr class=xl24 height=19 style='height:14.25pt'>
  <td height=19 class=xl25 width=142 style='height:14.25pt;width:107pt'>项目</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>本月</td>
  <td class=xl25 width=174 style='border-left:none;width:131pt'>上月</td>
  <td class=xl25 width=165 style='border-left:none;width:124pt'>增减额</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>增减%</td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl26 style='height:14.25pt;border-top:none'>一、收入</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(0).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(1).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(2).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=v.get(3)%></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl26 style='height:14.25pt;border-top:none'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp; </span>行政</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(4).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(5).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(6).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=v.get(7)%></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl26 style='height:14.25pt;border-top:none'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp; </span>事业</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(8).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(9).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(10).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=v.get(11)%></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl26 style='height:14.25pt;border-top:none'>二、支出</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(12).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(13).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(14).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=v.get(15)%></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl26 style='height:14.25pt;border-top:none'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp; </span>行政</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(16).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(17).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(18).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=v.get(19)%></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl26 style='height:14.25pt;border-top:none'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp; </span>事业</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(20).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(21).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(22).toString()))%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=v.get(23)%></td>
 </tr>
 <tr height=38 style='height:28.5pt;mso-xlrowspan:2'>
  <td height=38 colspan=5 style='height:28.5pt;mso-ignore:colspan'></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 style='height:14.25pt' align=left valign=top><!--[if gte vml 1]><v:shapetype
   id="_x0000_t201" coordsize="21600,21600" o:spt="201" path="m,l,21600r21600,l21600,xe">
   <v:stroke joinstyle="miter"/>
   <v:path shadowok="f" o:extrusionok="f" strokeok="f" fillok="f"
    o:connecttype="rect"/>
   <o:lock v:ext="edit" shapetype="t"/>
  </v:shapetype><v:shape id="_x0000_s1026" type="#_x0000_t201" style='position:absolute;
   margin-left:36.75pt;margin-top:2.25pt;width:365.25pt;height:224.25pt;
   z-index:1' fillcolor="window [78]" strokecolor="windowText [77]"
   o:insetmode="auto">
   <v:fill color2="windowText [77]"/>
   <o:lock v:ext="edit" rotation="t" text="t"/>
   <x:ClientData ObjectType="Chart">
    <x:WebChart>
     <x:Chart>
      <x:Name>图表 2</x:Name>
      <x:Title>
       <x:Caption>
        <x:DataSource>-1</x:DataSource>
        <x:Data>&quot;<x:Font>收入对比</x:Font>&quot;</x:Data>
       </x:Caption>
       <x:Font>
        <x:FontName>宋体</x:FontName>
        <x:Size>12</x:Size>
        <x:AutoScale/>
       </x:Font>
       <x:Border>
        <x:ColorIndex>None</x:ColorIndex>
       </x:Border>
       <x:Interior>
        <x:ColorIndex>None</x:ColorIndex>
       </x:Interior>
      </x:Title>
      <x:Options>
       <x:SizeWithWindow/>
      </x:Options>
      <x:PageSetup>
       <x:ChartSize>FullPage</x:ChartSize>
      </x:PageSetup>
      <x:Font>
       <x:FontName>宋体</x:FontName>
       <x:Size>12</x:Size>
       <x:AutoScale/>
      </x:Font>
      <x:Left>0</x:Left>
      <x:Top>0</x:Top>
      <x:Width>7319.99267578125</x:Width>
      <x:Height>4500</x:Height>
      <x:ChartGrowth>
       <x:HorzGrowth>1</x:HorzGrowth>
       <x:VertGrowth>1</x:VertGrowth>
      </x:ChartGrowth>
      <x:PlotArea>
       <x:Border>
        <x:ColorIndex>None</x:ColorIndex>
       </x:Border>
       <x:Interior>
        <x:ColorIndex>None</x:ColorIndex>
       </x:Interior>
       <x:Font>
        <x:FontName>宋体</x:FontName>
        <x:Size>12</x:Size>
        <x:AutoScale/>
       </x:Font>
       <x:Graph>
        <x:Type>Pie</x:Type>
        <x:SubType>Standard</x:SubType>
        <x:SubType>3D</x:SubType>
        <x:VaryColors/>
        <x:GapWidth>150</x:GapWidth>
        <x:FirstSliceAngle>0</x:FirstSliceAngle>
        <x:Series>
         <x:Index>0</x:Index>
         <x:Name>系列 1</x:Name>
         <x:Category>
          <x:DataSource>0</x:DataSource>
          <x:Data>Sheet1!$A$3:$A$4</x:Data>
         </x:Category>
         <x:Value>
          <x:DataSource>0</x:DataSource>
          <x:Data>Sheet1!$B$3:$B$4</x:Data>
         </x:Value>
        </x:Series>
        <x:DataLabels>
         <x:Border>
          <x:ColorIndex>None</x:ColorIndex>
         </x:Border>
         <x:Font>
          <x:FontName>宋体</x:FontName>
          <x:Size>12</x:Size>
          <x:AutoScale/>
         </x:Font>
         <x:Interior>
          <x:ColorIndex>None</x:ColorIndex>
         </x:Interior>
         <x:Number>
          <x:FormatString>0%</x:FormatString>
         </x:Number>
         <x:ShowPercent/>
         <x:ShowLeaderLines/>
        </x:DataLabels>
        <x:LabelContentEx>
         <x:ShowValue/>
         <x:ShowCategoryName/>
         <x:ShowPercent/>
        </x:LabelContentEx>
        <x:PlotVisible/>
       </x:Graph>
      </x:PlotArea>
      <x:Legend>
       <x:Placement>Bottom</x:Placement>
       <x:Font>
        <x:FontName>宋体</x:FontName>
        <x:Size>12</x:Size>
        <x:AutoScale/>
       </x:Font>
      </x:Legend>
     </x:Chart>
    </x:WebChart>
   </x:ClientData>
  </v:shape><![endif]--><![if !vml]><span style='mso-ignore:vglayout;
  position:absolute;z-index:1;margin-left:49px;margin-top:3px;width:488px;
  height:300px'><![endif]><![if !excel]><![endif]><![if !vml]></span><![endif]><span
  style='mso-ignore:vglayout2'>
  <table cellpadding=0 cellspacing=0>
   <tr>
    <td height=19 width=142 style='height:14.25pt;width:107pt'></td>
   </tr>
  </table>
  </span></td>
  <td colspan=4 style='mso-ignore:colspan'></td>
 </tr>
 <tr height=304 style='height:228.0pt;mso-xlrowspan:16'>
  <td height=304 colspan=5 style='height:228.0pt;mso-ignore:colspan'></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 style='height:14.25pt' align=left valign=top><!--[if gte vml 1]><v:shape
   id="_x0000_s1027" type="#_x0000_t201" style='position:absolute;
   margin-left:36.75pt;margin-top:11.25pt;width:365.25pt;height:224.25pt;
   z-index:2' fillcolor="window [78]" strokecolor="windowText [77]"
   o:insetmode="auto">
   <v:fill color2="windowText [77]"/>
   <o:lock v:ext="edit" rotation="t" text="t"/>
   <x:ClientData ObjectType="Chart">
    <x:WebChart>
     <x:Chart>
      <x:Name>图表 3</x:Name>
      <x:Title>
       <x:Caption>
        <x:DataSource>-1</x:DataSource>
        <x:Data>&quot;<x:Font>支出对比</x:Font>&quot;</x:Data>
       </x:Caption>
       <x:Font>
        <x:FontName>宋体</x:FontName>
        <x:Size>12</x:Size>
        <x:AutoScale/>
       </x:Font>
       <x:Border>
        <x:ColorIndex>None</x:ColorIndex>
       </x:Border>
       <x:Interior>
        <x:ColorIndex>None</x:ColorIndex>
       </x:Interior>
      </x:Title>
      <x:Options>
       <x:SizeWithWindow/>
      </x:Options>
      <x:PageSetup>
       <x:ChartSize>FullPage</x:ChartSize>
      </x:PageSetup>
      <x:Font>
       <x:FontName>宋体</x:FontName>
       <x:Size>12</x:Size>
       <x:AutoScale/>
      </x:Font>
      <x:Left>0</x:Left>
      <x:Top>0</x:Top>
      <x:Width>7319.99267578125</x:Width>
      <x:Height>4500</x:Height>
      <x:ChartGrowth>
       <x:HorzGrowth>1</x:HorzGrowth>
       <x:VertGrowth>1</x:VertGrowth>
      </x:ChartGrowth>
      <x:PlotArea>
       <x:Border>
        <x:ColorIndex>None</x:ColorIndex>
       </x:Border>
       <x:Interior>
        <x:ColorIndex>None</x:ColorIndex>
       </x:Interior>
       <x:Font>
        <x:FontName>宋体</x:FontName>
        <x:Size>12</x:Size>
        <x:AutoScale/>
       </x:Font>
       <x:Graph>
        <x:Type>Pie</x:Type>
        <x:SubType>Standard</x:SubType>
        <x:SubType>3D</x:SubType>
        <x:VaryColors/>
        <x:GapWidth>150</x:GapWidth>
        <x:FirstSliceAngle>0</x:FirstSliceAngle>
        <x:Series>
         <x:Index>0</x:Index>
         <x:Name>系列 1</x:Name>
         <x:Category>
          <x:DataSource>0</x:DataSource>
          <x:Data>Sheet1!$A$6:$A$7</x:Data>
         </x:Category>
         <x:Value>
          <x:DataSource>0</x:DataSource>
          <x:Data>Sheet1!$B$6:$B$7</x:Data>
         </x:Value>
        </x:Series>
        <x:DataLabels>
         <x:Border>
          <x:ColorIndex>None</x:ColorIndex>
         </x:Border>
         <x:Font>
          <x:FontName>宋体</x:FontName>
          <x:Size>12</x:Size>
          <x:AutoScale/>
         </x:Font>
         <x:Interior>
          <x:ColorIndex>None</x:ColorIndex>
         </x:Interior>
         <x:Number>
          <x:FormatString>0%</x:FormatString>
         </x:Number>
         <x:ShowPercent/>
         <x:ShowLeaderLines/>
        </x:DataLabels>
        <x:LabelContentEx>
         <x:ShowValue/>
         <x:ShowCategoryName/>
         <x:ShowPercent/>
        </x:LabelContentEx>
        <x:PlotVisible/>
       </x:Graph>
      </x:PlotArea>
      <x:Legend>
       <x:Placement>Bottom</x:Placement>
       <x:Font>
        <x:FontName>宋体</x:FontName>
        <x:Size>12</x:Size>
        <x:AutoScale/>
       </x:Font>
      </x:Legend>
     </x:Chart>
    </x:WebChart>
   </x:ClientData>
  </v:shape><![endif]--><![if !vml]><span style='mso-ignore:vglayout;
  position:absolute;z-index:2;margin-left:49px;margin-top:15px;width:488px;
  height:300px'><![endif]><![if !excel]><![endif]><![if !vml]></span><![endif]><span
  style='mso-ignore:vglayout2'>
  <table cellpadding=0 cellspacing=0>
   <tr>
    <td height=19 width=142 style='height:14.25pt;width:107pt'></td>
   </tr>
  </table>
  </span></td>
  <td colspan=4 style='mso-ignore:colspan'></td>
 </tr>
 <tr height=304 style='height:228.0pt;mso-xlrowspan:16'>
  <td height=304 colspan=5 style='height:228.0pt;mso-ignore:colspan'></td>
 </tr>
 <![if supportMisalignedColumns]>
 <tr height=0 style='display:none'>
  <td width=142 style='width:107pt'></td>
  <td width=152 style='width:114pt'></td>
  <td width=174 style='width:131pt'></td>
  <td width=165 style='width:124pt'></td>
  <td width=72 style='width:54pt'></td>
 </tr>
 <![endif]>
</table>

</body>

</html>
