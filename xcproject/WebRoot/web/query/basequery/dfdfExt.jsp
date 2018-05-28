<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.List,java.util.ArrayList,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="com.noki.dfdfcx.bean.dfdfBean" %>
<%@ page import="com.noki.dfdfcx.servise.dfdfcxServise" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
<%

        Account account = new Account();
        account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        String loginName = account.getAccountName();
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
<jsp:useBean id="bean" scope="page" class="com.noki.query.basequery.javabean.StationPointQueryBean">
</jsp:useBean>


<body link=blue vlink=purple>
       <%
        //Vector v = bean.getHuanbi_all("0",qijian,qyear);         
       %>
<table x:str border=1 cellpadding=0 cellspacing=0 width=705 style='border-collapse:
 collapse;table-layout:fixed;width:530pt'>
 <col width=142 style='mso-width-source:userset;mso-width-alt:4544;width:107pt'>
 <col width=152 style='mso-width-source:userset;mso-width-alt:4864;width:114pt'>
 <col width=174 style='mso-width-source:userset;mso-width-alt:5568;width:131pt'>
 <col width=165 style='mso-width-source:userset;mso-width-alt:5280;width:124pt'>
 <col width=72 style='width:54pt'>
 <tr class=xl24 height=19 style='height:14.25pt'>  
 <td class=xl25 width=152 style='border-left:none;width:114pt'>地市</td>
 <td class=xl25 width=152 style='border-left:none;width:114pt'>净使用电量</td>
 <td class=xl25 width=174 style='border-left:none;width:131pt'>折算后电量</td>
 <td class=xl25 width=165 style='border-left:none;width:124pt'>调整电量</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>线损电量</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>平均倍率</td>  
  
 <td class=xl25 width=72 style='border-left:none;width:54pt'>平均单价</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>结算电费</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>静使用电费</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>多付电费</td>
 
 </tr>
 

 
 
 <%   
        
         String whereStr = request.getParameter("whereStr");	
         String shi  = request.getParameter("shi");
         System.out.println("-----------"+whereStr);
         System.out.println("-----------"+shi);
         //String zdname=request.getParameter("zdname");
		//if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
		//	whereStr=whereStr+" AND JZ.JZNAME LIKE '%"+zdname+"%'";			
		//}
         if(whereStr==null){
         whereStr="";
         }
         dfdfcxServise ser = new dfdfcxServise();
         ArrayList<dfdfBean> fylist = new ArrayList();
         fylist = ser.getDfdfChaxunAll(whereStr,shi);
 		String shiname = "",jsydl="",zshdl="",actualpay="",tzdl="",xsdl="",pjbl="",pjdj="",jsdf="",chundf="",cha="",xianid="",shiid="";
 		 DecimalFormat price=new DecimalFormat("0.00");
         //  bzpldu = price.format(Double.parseDouble(bzpldu));
 		double df=0.0;

		 if(fylist!=null)
		{
			for(dfdfBean beans:fylist){
				shiname = beans.getShi();
	             jsydl = beans.getJsydl() != null ? beans.getJsydl() : "0";
	             jsydl = price.format(Double.parseDouble(jsydl));
	             zshdl = beans.getZshdl() != null ? beans.getZshdl() : "0";
	             zshdl = price.format(Double.parseDouble(zshdl));
	             tzdl = beans.getTzdl() != null ? beans.getTzdl() : "0";
	             tzdl = price.format(Double.parseDouble(tzdl));
	             xsdl = beans.getXsdl() != null ? beans.getXsdl() : "0";
	             xsdl = price.format(Double.parseDouble(xsdl));
	             pjbl = beans.getPjbl() != null ? beans.getPjbl() : "0";
	             pjbl = price.format(Double.parseDouble(pjbl));
	             pjdj = beans.getPjdj() != null ? beans.getPjdj() : "0";
	             pjdj = price.format(Double.parseDouble(pjdj));
	             jsdf = beans.getJsdf() != null ? beans.getJsdf() : "0";
	             jsdf = price.format(Double.parseDouble(jsdf));
	             chundf = beans.getChundf() != null ? beans.getChundf() : "0";
	             chundf = price.format(Double.parseDouble(chundf));
	             cha = beans.getCha() != null ? beans.getCha() : "0";
	             cha = price.format(Double.parseDouble(cha));
	             xianid =  beans.getXianid() != null ? beans.getXianid() : "0";
		         shiid = beans.getShiid() != null ? beans.getShiid() : "0";
		  
		
		  

			String color=null;

       %>
 <tr height=19 style='height:14.25pt'>   
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=shiname%></td>
	   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jsydl%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zshdl%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=tzdl%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xsdl%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=pjbl%></td>
      <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=pjdj%></td>
     <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jsdf%></td>
     <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=chundf%></td>
	 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=cha%></td>
	 
 </tr>
         <%
         }}
         %>
  <tr> 
  	<td colspan="22">

  </tr>
 
 <![endif]>
</table>

</body>

</html>
