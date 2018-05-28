<%@page contentType="application/vnd.ms-excel;charset=GBK" language="java"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime,java.math.BigDecimal"%>
<%
	CTime time = new CTime();
        Account account = new Account();
        account = (Account)session.getAttribute("account");
		String accountId = account.getAccountId();
                String path = request.getContextPath();
	String dwxz = request.getParameter("dwxz")!=null?request.getParameter("dwxz"):"0";
        String srzc = request.getParameter("srzc")!=null?request.getParameter("srzc"):"1";
		String month = String.valueOf(Integer.parseInt(time.formatShortDate().substring(4, 6)));
        String qijian = request.getParameter("qijian")!=null?request.getParameter("qijian"):month;

		BigDecimal d = new BigDecimal("00000000000000.00");
		BigDecimal dc = new BigDecimal("00000000000000.00");
%>
<html xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">

<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name=ProgId content=Excel.Sheet>
<meta name=Generator content="Microsoft Excel 11">
<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Created>1996-12-17T01:32:42Z</o:Created>
  <o:LastSaved>2008-08-11T05:49:59Z</o:LastSaved>
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
	text-align:center;}
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
       <x:ActiveRow>3</x:ActiveRow>
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
<jsp:useBean id="bean" scope="page" class="com.noki.mobi.analysis.javabean.TopAnalysisBean">
</jsp:useBean>
<body link=blue vlink=purple>

<table x:str border=0 cellpadding=0 cellspacing=0 width=656 style='border-collapse:
 collapse;table-layout:fixed;width:492pt'>
 <col class=xl24 width=75 style='mso-width-source:userset;mso-width-alt:2400;
 width:56pt'>
 <col width=248 style='mso-width-source:userset;mso-width-alt:7936;width:186pt'>
 <col width=164 style='mso-width-source:userset;mso-width-alt:5248;width:123pt'>
 <col width=169 style='mso-width-source:userset;mso-width-alt:5408;width:127pt'>
 <tr class=xl24 height=19 style='height:14.25pt'>
  <td height=19 class=xl24 width=75 style='height:14.25pt;width:56pt'>序号</td>
  <td class=xl24 width=248 style='width:186pt'>预算单位</td>
  <td class=xl24 width=164 style='width:123pt'>收入</td>
  <td class=xl24 width=169 style='width:127pt'>支出</td>
 </tr>
  <%
      	ArrayList list = new ArrayList();
        list = bean.getTopAnalysis(dwxz,srzc,qijian);
        int k = 1;
        String color = "F3F3F3";
        int countColum = ((Integer)list.get(0)).intValue();
      %>

       <%	String sr = "",zc = "";
              for(int i = countColum;i<list.size()-1;i+=countColum){
                sr = (String)list.get(i+list.indexOf("SR"));
                zc = (String)list.get(i+list.indexOf("ZC"));

                sr = sr!=null?sr:"0";
                zc = zc!=null?zc:"0";
				d = d.add(new BigDecimal(Double.parseDouble(sr)));
				dc = dc.add(new BigDecimal(Double.parseDouble(zc)));
              if(k%2==0){
                          color = "#F2F9FF";
                  }else{
                          color = "FFFFFF";
                  }
       %>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl24 style='height:14.25pt' x:num><%=k++%></td>
  <td><%=(String)list.get(i+list.indexOf("DWMC"))%></td>
  <td align=right x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(sr))%></td>
  <td align=right x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(zc))%></td>
 </tr>
 <%}%>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl24 style='height:14.25pt'></td>
  <td class=xl24>总计</td>
  <td align=right x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(d.toString()))%></td>
  <td align=right x:num><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(dc.toString()))%></td>
 </tr>
 <![if supportMisalignedColumns]>
 <tr height=0 style='display:none'>
  <td width=75 style='width:56pt'></td>
  <td width=248 style='width:186pt'></td>
  <td width=164 style='width:123pt'></td>
  <td width=169 style='width:127pt'></td>
 </tr>
 <![endif]>
</table>

</body>

</html>
