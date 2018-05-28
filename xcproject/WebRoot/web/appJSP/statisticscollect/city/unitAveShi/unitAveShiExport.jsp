<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns:v="urn:schemas-microsoft-com:vml"
xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">
<head>
	<title>地市单价平均与平均单价</title>
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
	<![endif]-->
	<!--[if gte mso 9]>
		<xml>
 			<o:DocumentProperties>
  			<o:Created>1996-12-17T01:32:42Z</o:Created>
			<o:LastSaved>2008-08-11T02:22:29Z</o:LastSaved>
  			<o:Version>11.9999</o:Version>
 			</o:DocumentProperties>
 			<o:OfficeDocumentSettings>
 			<o:RemovePersonalInformation/>
 			</o:OfficeDocumentSettings>
		</xml>
	<![endif]-->
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
	<!--[if gte mso 9]>
		<xml>
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
		</xml>
	<![endif]-->
	<!--[if gte mso 9]>
		<xml>
			<o:shapedefaults v:ext="edit" spidmax="1028"/>
		</xml>
	<![endif]-->
	<!--[if gte mso 9]>
	<xml>
		<o:shapelayout v:ext="edit">
		<o:idmap v:ext="edit" data="1"/>
		</o:shapelayout>
	</xml>
	<![endif]-->
</head>
<jsp:useBean id="bean" scope="page" class="com.noki.query.basequery.javabean.ElectricFeesQueryBean">
</jsp:useBean>
<body link=blue vlink=purple>
	<table x:str border=0 cellpadding=0 cellspacing=0 width=705 style='border-collapse:
 	collapse;table-layout:fixed;width:530pt'>
	<col width=142 style='mso-width-source:userset;mso-width-alt:4544;width:107pt'>
	<col width=152 style='mso-width-source:userset;mso-width-alt:4864;width:114pt'>
	<col width=174 style='mso-width-source:userset;mso-width-alt:5568;width:131pt'>
	<col width=165 style='mso-width-source:userset;mso-width-alt:5280;width:124pt'>
	<col width=72 style='width:54pt'>
		<tr class=xl24 height=19 style='height:14.25pt'>  
			<td class=xl25 width=72 style='border-left:none;width:54pt'>城市</td>
			<td class=xl25 width=72 style='border-left:none;width:54pt'>区县</td>
			<td class=xl25 width=72 style='border-left:none;width:54pt'>报账月份</td>
			<td class=xl25 width=72 style='border-left:none;width:54pt'>至报账月份</td>
			<td class=xl25 width=72 style='border-left:none;width:54pt'>单价平均</td>
			<td class=xl25 width=72 style='border-left:none;width:54pt'>平均单价</td>
			<td class=xl25 width=72 style='border-left:none;width:54pt'>单价平均趋势</td>
			<td class=xl25 width=72 style='border-left:none;width:54pt'>平均单价趋势</td>
 		</tr>
 		<c:forEach items="${list}"  var="list" varStatus="status">
 		<tr height=19 style='height:14.25pt'>
			<td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.shi}</td>
			<td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.xian}</td>
		  	<td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;${list.bzyfstart}</td>
		  	<td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;${list.bzyfend}</td>
		  	<td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.danjiapj}</td>
		  	<td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.pingjundj}</td>
		  	<td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.danjiapjqs}</td>
		  	<td class=xl26 align=right style='border-top:none;border-left:none' x:num>${list.pingjundjqs}</td>    
 		</tr>
		</c:forEach>        
	</table>
</body>
</html>