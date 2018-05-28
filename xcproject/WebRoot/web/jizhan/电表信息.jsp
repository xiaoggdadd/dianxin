<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.DianBiaoBean" %>
<%@ page import="java.sql.ResultSet"%>

<%
				String sheng = request.getParameter("sheng")!=null?request.getParameter("sheng"):"0";
        String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
        String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
        String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String dbid = request.getParameter("dbid")!=null?request.getParameter("dbid"):"";
%>
<html xmlns:v="urn:schemas-microsoft-com:vml"
xmlns:o="urn:schemas-microsoft-com:office:office" 
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">

<head>
<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
<meta name=ProgId content=Excel.Sheet>
<meta name=Generator content="Microsoft Excel 11">
<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Created>1996-12-17T01:32:42Z</o:Created>
  <o:LastSaved>2011-10-02T11:31:30Z</o:LastSaved>
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
	text-align:center;
	border:.5pt solid windowtext;}
.xl26
	{mso-style-parent:style0;
	font-weight:700;
	text-align:center;
	border:.5pt solid windowtext;}
.xl27
	{mso-style-parent:style0;
	border:.5pt solid windowtext;}
.xl28
	{mso-style-parent:style0;
	mso-number-format:"\@";
	text-align:right;
	border:.5pt solid windowtext;}
.xl29
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
     <x:CodeName>Sheet1</x:CodeName>
     <x:Selected/>
     <x:Panes>
      <x:Pane>
       <x:Number>3</x:Number>
       <x:ActiveRow>8</x:ActiveRow>
       <x:ActiveCol>10</x:ActiveCol>
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

<table x:str border=0 cellpadding=0 cellspacing=0 width=1260 style='border-collapse:
 collapse;table-layout:fixed;width:946pt'>
 <col width=42 style='mso-width-source:userset;mso-width-alt:1344;width:32pt'>
 <col width=90 style='mso-width-source:userset;mso-width-alt:2880;width:68pt'>
 <col width=165 style='mso-width-source:userset;mso-width-alt:5280;width:124pt'>
 <col width=239 style='mso-width-source:userset;mso-width-alt:7648;width:179pt'>
 <col width=91 style='mso-width-source:userset;mso-width-alt:2912;width:68pt'>
 <col width=79 style='mso-width-source:userset;mso-width-alt:2528;width:59pt'>
 <col width=95 style='mso-width-source:userset;mso-width-alt:3040;width:71pt'>
 <col width=107 style='mso-width-source:userset;mso-width-alt:3424;width:80pt'>
 <col width=53 style='mso-width-source:userset;mso-width-alt:1696;width:40pt'>
 <col width=93 style='mso-width-source:userset;mso-width-alt:2976;width:70pt'>
 <col width=206 style='mso-width-source:userset;mso-width-alt:6592;width:155pt'>
 <tr height=37 style='mso-height-source:userset;height:27.75pt'>
  <td colspan=11 height=37 class=xl24 width=1260 style='height:27.75pt;
  width:946pt'>电表信息列表</td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl26 style='height:14.25pt;border-top:none'>序号</td>
  <td class=xl26 style='border-top:none;border-left:none'>电表ID</td>
  <td class=xl26 style='border-top:none;border-left:none'>基站名称</td>
  <td class=xl26 style='border-top:none;border-left:none'>所在地区</td>
  <td class=xl26 style='border-top:none;border-left:none'>所属专业</td>
  <td class=xl26 style='border-top:none;border-left:none'>电表用途</td>
  <td class=xl26 style='border-top:none;border-left:none'>初始读数</td>
  <td class=xl26 style='border-top:none;border-left:none'>初始读数时间</td>
  <td class=xl26 style='border-top:none;border-left:none'>倍率</td>
  <td class=xl26 style='border-top:none;border-left:none'>电表型号</td>
  <td class=xl26 style='border-top:none;border-left:none'>备注</td>
 </tr>
  <%
       DianBiaoBean bean = new DianBiaoBean();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getOutPutData(sheng,shi,xian,xiaoqu,dbid);

		String cdbid = "",zdname = "",area = "", sszy= "",dbyt = "",csds="",cssytime="",beilv="",id="",dbxh="",memo="";
		int intnum=1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     id = (String)fylist.get(k+fylist.indexOf("ID"));
				cdbid = (String)fylist.get(k+fylist.indexOf("DBID"));
		    zdname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    area = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		    sszy = (String)fylist.get(k+fylist.indexOf("SSZY"));
				dbyt = (String)fylist.get(k+fylist.indexOf("DBYT"));
				csds = (String)fylist.get(k+fylist.indexOf("CSDS"));
				cssytime = (String)fylist.get(k+fylist.indexOf("CSSYTIME"));
				beilv = (String)fylist.get(k+fylist.indexOf("BEILV"));
				dbxh = (String)fylist.get(k+fylist.indexOf("DBXH"));
				memo = (String)fylist.get(k+fylist.indexOf("MEMO"));
			String color=null;
			if(csds==null)csds="";
			if(cssytime==null)cssytime="";
			if(beilv==null)beilv="";
			if(dbxh==null)dbxh="";
			if(memo==null)memo="";

			

       %>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl25 style='height:14.25pt;border-top:none' x:num><%=intnum++%></td>
  <td class=xl27 style='border-top:none;border-left:none'><%=cdbid%></td>
  <td class=xl27 style='border-top:none;border-left:none'><%=zdname%></td>
  <td class=xl27 style='border-top:none;border-left:none'><%=area%></td>
  <td class=xl25 style='border-top:none;border-left:none'><%=sszy%></td>
  <td class=xl25 style='border-top:none;border-left:none'><%=dbyt%></td>
  <td class=xl28 style='border-top:none;border-left:none' x:num><%=csds%></td>
  <td class=xl29 style='border-top:none;border-left:none'><%=cssytime%></td>
  <td class=xl25 style='border-top:none;border-left:none' x:num><%=beilv%></td>
  <td class=xl27 style='border-top:none;border-left:none'><%=dbxh%></td>
  <td class=xl27 style='border-top:none;border-left:none'><%=memo%></td>
 </tr>
 <%}  }%>
 <![if supportMisalignedColumns]>
 <tr height=0 style='display:none'>
  <td width=42 style='width:32pt'></td>
  <td width=90 style='width:68pt'></td>
  <td width=165 style='width:124pt'></td>
  <td width=239 style='width:179pt'></td>
  <td width=91 style='width:68pt'></td>
  <td width=79 style='width:59pt'></td>
  <td width=95 style='width:71pt'></td>
  <td width=107 style='width:80pt'></td>
  <td width=53 style='width:40pt'></td>
  <td width=93 style='width:70pt'></td>
  <td width=206 style='width:155pt'></td>
 </tr>
 <![endif]>
</table>

</body>

</html>
