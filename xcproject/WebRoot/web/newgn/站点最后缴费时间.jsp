<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>

<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime" %>
<%@ page import="com.noki.newfunction.dao.*" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*" %>
<%@ page import="com.noki.electricfees.javabean.*" %>
<%@ page import="com.noki.function.*" %>
<%@ page import="java.util.ArrayList,java.util.List" %>
<%@page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean"%>
<%@page import="com.noki.query.basequery.javabean.AmmeterDegreeQueryBean"%>
<%

        Account account = new Account();
        account = (Account)session.getAttribute("account");
        String qyear = request.getParameter("qyear");
		CTime time = new CTime();
		//String accountId = account.getAccountId();
		String loginId = account.getAccountId();
        String loginName = account.getAccountName();
        String path = request.getContextPath();
	    String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";
	    String zcxstr1=request.getParameter("zcxStr1")!=null?request.getParameter("zcxStr1"):"";
	    String zcxstr2=request.getParameter("zcxStr2")!=null?request.getParameter("zcxStr2"):"";
	    String flag_zf=request.getParameter("flag_zflx")!=null?request.getParameter("flag_zflx"):"";
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
<jsp:useBean id="bean" scope="page" class="com.noki.electricfees.javabean.ElectricFeesBean">
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
  <td class=xl25 width=152 style='border-left:none;width:114pt'>城市</td>  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>区县</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>乡镇</td>
  <td class=xl25 width=72 style='border-left:none;width:114pt'>站点名称</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>站点类型</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>支付类型</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>最后一次抄表时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>保障月份</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>站点ID</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>电表ID</td>
  
 </tr>
<% 			
         SerchZhandian bean1 = new SerchZhandian();
         String shi1="";
    	 String xian="";
    	 String xiaoqu="";
    	 String jzname="";
    	 String jztype="";
    	 String id="";
    	 String dfzflx="";
    	 String dbid="";
    	 String jfsj="";
    	 String bzyf="";
    	 int count1=0;
    	 
        List<ElectricFeesFormBean> ls=bean1.getLastPay(whereStr,zcxstr1,zcxstr2,flag_zf);
        	for(ElectricFeesFormBean ef:ls){
        	count1 = count1+1;
            shi1=ef.getShi();
            xian=ef.getXian();
            xiaoqu=ef.getXiaoqu();
            jzname=ef.getJzname();
            jztype=ef.getJztype();
            id=ef.getId();
            jfsj=ef.getPaydatetime();
            bzyf=ef.getAccountmonth();
            dfzflx=ef.getDfzflx();
            dbid=ef.getDbid();
          
            if(null==shi1||shi1.equals("")||shi1.equals(" "))shi1="";
            if(null==xian||xian.equals("")||xian.equals(" "))xian="";
            if(null==xiaoqu||xiaoqu.equals("")||xiaoqu.equals(" "))xiaoqu="";
            if(null==jzname||jzname.equals("")||jzname.equals(" "))jzname="0";
            if(null==jztype||"".equals(jztype)||" ".equals(jztype))jztype="";
            if(null==id||"".equals(id)||" ".equals(id))id="";
            if(null==dfzflx||"".equals(dfzflx)||" ".equals(dfzflx))id="0";
            if(null==dbid||"".equals(dbid)||" ".equals(dbid))dbid="";
            
            
            

       %>
       <tr height=19 style='height:14.25pt'>
   
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=shi1%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xian%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xiaoqu%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jztype%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dfzflx%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jfsj%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=bzyf%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=id%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dbid%></td>
       </tr>
         <%
        }
       %>

  <tr> 
  	<td colspan="6">
  	<div align="center">
							<font color='#000080'>&nbsp;&nbsp;导出条数:</font>
							 <b><font color=red><%=count1%>条；</font></b>
							
	</div>
  </tr>

</table>

</body>

</html>
