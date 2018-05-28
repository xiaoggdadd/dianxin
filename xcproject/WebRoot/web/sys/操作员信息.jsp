<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.noki.mobi.common.Account"%>
<%@ page session="true"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
	String loginName = (String) session.getAttribute("loginName");	
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
<jsp:useBean id="bean" scope="page" class="com.noki.mobi.sys.javabean.AccountBean">
</jsp:useBean>


<body link=blue vlink=purple>
       <%
        //Vector v = bean.getHuanbi_all("0",qijian,qyear);         
       %>
<table x:str border=1 cellpadding=0 cellspacing=0 width=705 style='border-collapse:collapse;table-layout:fixed;width:530pt'>
	 <col width=142 style='mso-width-source:userset;mso-width-alt:4544;width:107pt'>
	 <col width=152 style='mso-width-source:userset;mso-width-alt:4864;width:114pt'>
	 <col width=174 style='mso-width-source:userset;mso-width-alt:5568;width:131pt'>
	 <col width=165 style='mso-width-source:userset;mso-width-alt:5280;width:124pt'>
	 <col width=72 style='width:54pt'>
	 <tr class=xl24 height=19 style='height:14.25pt'>  
	  <td class=xl25 width=60 style='border-left:none;width:114pt'>账号</td>
	  <td class=xl25 width=174 style='border-left:none;width:131pt'>姓名</td>
	  <td class=xl25 width=165 style='border-left:none;width:124pt'>城市</td>
	  <td class=xl25 width=72 style='border-left:none;width:54pt'>区县</td>
	  <td class=xl25 width=72 style='border-left:none;width:54pt'>乡镇</td>	  
	  <td class=xl25 width=72 style='border-left:none;width:54pt'>手机号码</td>
	  <td class=xl25 width=72 style='border-left:none;width:54pt'>座机</td>
	  <td class=xl25 width=72 style='border-left:none;width:54pt'>邮箱</td>
	  <td class=xl25 width=72 style='border-left:none;width:54pt'>角色</td>
 </tr>
 <%
	    String whereStr = "";
		ArrayList list = new ArrayList();
		String str = request.getParameter("whereStr");
		String usename = request.getParameter("usename");
		if(usename != null && !"".equals(usename)&& !"0".equals(usename)){
			whereStr=whereStr+" and ACCOUNTNAME like '%"+usename+"%'";				
		}
		String adminsname = request.getParameter("adminsname");
		if(adminsname != null && !"".equals(adminsname)&& !"0".equals(adminsname)){
			whereStr=whereStr+" and NAME like '%"+adminsname+"%'";				
		}
		String ssagcode =request.getParameter("ssagcode");
		list = bean.getAllAccountdc(whereStr, ssagcode,str);
		int countColum = ((Integer) list.get(0)).intValue();
		String accountId = "", accountName = "", name = "", mobile = "", roleName = "", cs = "",qx = "",xq = "", tel = "",email = "";
		int count = 0;
		for (int i = countColum; i < list.size() - 1; i += countColum) {
			//导出条数
			count=count+1;
			accountId = (String) list.get(i + list.indexOf("ACCOUNTID"));
			accountName = (String) list.get(i + list.indexOf("ACCOUNTNAME"));
			name = (String) list.get(i + list.indexOf("NAME"));
			mobile = (String) list.get(i + list.indexOf("MOBILE"));
			roleName = (String) list.get(i + list.indexOf("ROLENAME"));
			email = (String) list.get(i + list.indexOf("EMAIL"));
			cs = (String) list.get(i + list.indexOf("CS"));
			qx = (String) list.get(i + list.indexOf("QX"));
			xq = (String) list.get(i + list.indexOf("XQ"));
			if(mobile == null){
				mobile = "";
			}
		%>
 <tr height=19 style='height:14.25pt'>   
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=accountName%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=name%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=cs%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=qx%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xq%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=mobile%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=tel%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=email%></td>  
     <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=roleName%></td>
 </tr>
 <%} %>
  <tr> 
  	<td colspan="9">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=count %>条</font></b>
	</div>
	</td>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
