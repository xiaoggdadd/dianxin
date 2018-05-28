﻿<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.query.basequery.javabean.CityHouseBean" %>
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
<jsp:useBean id="bean" scope="page" class="com.noki.query.tjhzquery.javabean.SjZnhtjBean">
</jsp:useBean>


<body link=blue vlink=purple>
       <%
        //Vector v = bean.getHuanbi_all("0",qijian,qyear);         
       %>
<table x:str border=0 cellpadding=0 cellspacing=0 width=705 style='border-collapse:
 collapse;table-layout:fixed;width:530pt'>
 <col width=142 style='mso-width-source:userset;mso-width-alt:4544;width:107pt'>
 <col width=152 style='mso-width-source:userset;mso-width-alt:4864;width:114pt'>
 <col width=174 style='mso-width-source:userset;mso-width-alt:5568;width:131pt'>
 <col width=165 style='mso-width-source:userset;mso-width-alt:5280;width:124pt'>
 <col width=72 style='width:54pt'>
 <tr class=xl24 height=19 style='height:14.25pt'>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>地市</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点</td>
  <td class=xl25 width=174 style='border-left:none;width:131pt'>时间段</td>
  <td class=xl25 width=165 style='border-left:none;width:124pt'>耗电量<hr>度</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>水消耗量<hr>吨</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>汽油消耗量<hr>升</td>
  
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>柴油消耗量<hr>升</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>天然气消耗量<hr>立方米</td>
 </tr>
 <%   
         int curpage = Integer.parseInt(request.getParameter("curpage"));  
         String whereStr = request.getParameter("whereStr");	         
       	 System.out.println("StationPointQueryBean-whereStr:"+whereStr);

        String zhandian = request.getParameter("zhandian");
         String beginTimeQ = request.getParameter("beginTime");
         System.out.println("ammeterDegreeQueryList:"+beginTimeQ);
          

			if(beginTimeQ != null && !beginTimeQ.equals("")){
				whereStr=whereStr+" and t.sj='"+beginTimeQ+"'";
			}
			if(zhandian != null && !zhandian.equals("")){
				whereStr=whereStr+" and zd.jzname like '%"+zhandian+"%'";
			}
			      
         
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageDataNhlCity(curpage,whereStr);
       	
		String id="",
		zdname = "",
		shi = "",
		agname = "",
		sj = "",
		hdl = "", 
		sxhl= "",
		qyxhl = "",
		cyxhl="",
		trqxhl="";
         			//合计值
			String sum_hdl = "",
			sum_sxhl = "",
			sum_qyxhl = "",
			sum_cyxhl = "",
			sum_trqxhl = "";
         
         
         
		//int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		    id = (String)fylist.get(k+fylist.indexOf("ID"));
		    shi = (String)fylist.get(k+fylist.indexOf("SHI"));
		    shi = shi != null ? shi : "";
		    zdname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    zdname = zdname != null ? zdname : "";
		    sj = (String)fylist.get(k+fylist.indexOf("SJ"));
		    sj = sj != null ? sj : "";
		    hdl = (String)fylist.get(k+fylist.indexOf("HDL"));
		    hdl = hdl != null ? hdl : "";
		    sxhl = (String)fylist.get(k+fylist.indexOf("SXHL"));
		    sxhl = sxhl != null ? sxhl : "";
			qyxhl = (String)fylist.get(k+fylist.indexOf("QYXHL"));
			qyxhl = qyxhl != null ? qyxhl : "";
			cyxhl = (String)fylist.get(k+fylist.indexOf("CYXHL"));
			cyxhl = cyxhl != null ? cyxhl : "";
			trqxhl = (String)fylist.get(k+fylist.indexOf("TRQXHL"));
			trqxhl = trqxhl != null ? trqxhl : "";

			sum_hdl = (String)fylist.get(k+fylist.indexOf("SUM_HDL"));
			sum_hdl = sum_hdl != null ? sum_hdl : "";
			sum_sxhl = (String)fylist.get(k+fylist.indexOf("SUM_SXHL"));
			sum_sxhl = sum_sxhl != null ? sum_sxhl : "";
			sum_qyxhl = (String)fylist.get(k+fylist.indexOf("SUM_QYXHL"));
			sum_qyxhl = sum_qyxhl != null ? sum_qyxhl : "";
			sum_cyxhl = (String)fylist.get(k+fylist.indexOf("SUM_CYXHL"));
			sum_cyxhl = sum_cyxhl != null ? sum_cyxhl : "";
			sum_trqxhl = (String)fylist.get(k+fylist.indexOf("SUM_TRQXHL"));
			sum_trqxhl = sum_trqxhl != null ? sum_trqxhl : "";

       %>
 <tr height=19 style='height:14.25pt'>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=shi%></td> 
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdname%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=sj%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=hdl%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=sxhl%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=qyxhl%></td>
  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=cyxhl%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=trqxhl%></td>
 </tr>
         <%}%>
 <tr height=19 style='height:14.25pt'>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num></td> 
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>合计</td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=sum_hdl%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=sum_sxhl%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=sum_qyxhl%></td>
  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=sum_cyxhl%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=sum_trqxhl%></td>
 </tr>     
         
   <% } %>
 <tr height=38 style='height:28.5pt;mso-xlrowspan:2'>
  <td height=38 colspan=5 style='height:28.5pt;mso-ignore:colspan'></td>
 </tr>
 
 <![endif]>
</table>

</body>

</html>
