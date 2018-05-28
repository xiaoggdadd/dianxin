<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="java.text.*,com.noki.util.CTime"%>
<%@ page import="java.util.regex.Pattern"%>
<%@page import="com.noki.query.basequery.javabean.StationPointQueryBean,com.noki.mobi.common.CommonBean"%>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesFormBean,com.ptac.app.calibstat.unovershotSite.dao.UnOverShotSiteDao,com.ptac.app.calibstat.unovershotSite.dao.UnOverShotSiteDaoImpl,com.noki.mobi.common.zdbzbeanB"%>

<%

      /*  Account account = new Account();
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
        */

String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    //String roleId = account.getRoleId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");



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
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
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
	  <td class=xl25 width=72 style='border-left:none;width:54pt'>站点ID</td>
	  <td class=xl25 width=72 style='border-left:none;width:114pt'>站点名称 </td>
	  <td class=xl25 width=72 style='border-left:none;width:54pt'>本地定标</td>
	  <td class=xl25 width=72 style='border-left:none;width:54pt'>电表用电量 </td>
	  <td class=xl25 width=152 style='border-left:none;width:114pt'>合并电量 </td>
	  <td class=xl25 width=72 style='border-left:none;width:54pt'>超标比例 </td>
	  <td class=xl25 width=72 style='border-left:none;width:54pt'>直流负荷 </td>
      <td class=xl25 width=72 style='border-left:none;width:54pt'>交流负荷 </td>
      <td class=xl25 width=72 style='border-left:none;width:54pt'>站点属性</td>
      <td class=xl25 width=72 style='border-left:none;width:54pt'>城市</td>
      <td class=xl25 width=72 style='border-left:none;width:54pt'>区县 </td>
      <td class=xl25 width=72 style='border-left:none;width:54pt'>乡镇</td>
      <td class=xl25 width=72 style='border-left:none;width:54pt'>电量超标值（度/天）</td>
      <td class=xl25 width=72 style='border-left:none;width:54pt'>电费超标值（元/天）</td>
 </tr>
 <%   
 String whereStr = "";
 String str = "";
 String str1 = "";
 String str2 = "";
 String str3 = "";
 String str4 = "";
 String str5 = "";
 String bl = request.getParameter("bl");
 String bzmonth = request.getParameter("bzmonth");
 String property = request.getParameter("property");
 String shi = request.getParameter("shi");
 String xian = request.getParameter("xian");
 String dlcb = request.getParameter("dlcb");
 String dfcb = request.getParameter("dfcb");
 if(null !=bl&& !"".equals(bl)&& !" ".equals(bl)&& bl!=" " && bl!=""){
 	str = " AND DECODE(TO_NUMBER(ZD.QSDBDL),0,ZDDL.DL,(ZDDL.DL - ZD.QSDBDL) / ZD.QSDBDL)*100 <"+bl;
 	str4 = " AND ZDDL.BL*100 <"+bl;
 }
 
 if(null !=bzmonth  && !bzmonth.equals("") && !bzmonth.equals("0")){
   	str1 = " AND EF.ACCOUNTMONTH ='"+bzmonth+"'";
   	str2 = " AND CB.CBSJ = '"+bzmonth+"'";
   	str5 = " AND SYMONTH = '"+bzmonth+"'";
 } 
 
 if(null != property && !property.equals("") && !property.equals("0")){		              	
   	whereStr=whereStr+" AND ZD.PROPERTY ='"+property+"'";
   	str3 = property;
 }	
         
 if(null !=shi  && !shi.equals("") && !shi.equals("0")){
   	whereStr=whereStr+" AND ZD.SHI ='"+shi+"'";
 }
 
 if(null != xian && !xian.equals("") && !xian.equals("0")){		              	
   	whereStr=whereStr+" AND ZD.XIAN ='"+xian+"'";
 }	
 if(null !=dlcb&& !"".equals(dlcb)&& !" ".equals(dlcb)&& dlcb!=" " && dlcb!="" 
 	&& null !=dfcb&& !"".equals(dfcb)&& !" ".equals(dfcb)&& dfcb!=" " && dfcb!=""){
 	str4 = "AND (ZDDL.DLCB>=ABS('"+dlcb+"') OR (ZDDL.DLCB * 0.9)>=ABS('"+dfcb+"'))";
 }else{
 	if(null !=dlcb&& !"".equals(dlcb)&& !" ".equals(dlcb)&& dlcb!=" " && dlcb!=""){
 		str4 = "AND ZDDL.DLCB>=ABS('"+dlcb+"')";
 	}
 	if(null !=dfcb&& !"".equals(dfcb)&& !" ".equals(dfcb)&& dfcb!=" " && dfcb!=""){
 		str4 = "AND (ZDDL.DLCB * 0.9)>=ABS('"+dfcb+"')";
 	}
 
 }
 	UnOverShotSiteDao  bean = new UnOverShotSiteDaoImpl();
	List<zdbzbeanB> fylist = null;
	if ("1".equals(request.getParameter("chaxun"))
				|| "daochu".equals(request.getParameter("command"))) {
			fylist=bean.getPageDatauncbcx(whereStr,str,str1,str2,str3,str4,str5,loginId);

			String zdid = "";
			String jzname = "";
			String zdsx1 = "";
			String shiname = "";
			String xianname = "";
			String xiaoquname = "";
			String zlfh = "";
			String jlfh = "";
			String edhdl = "";
			String dbydl = "";
			String bzz = "";
			String bl1 = "";
			String bz = "",dlcb1 = "",dfcb1 = "";
			int countt=0;
			String rgshzt = "", rgshsj = "", rgshry = "";
			int countxh = 1;
			if (fylist != null) {
				for (zdbzbeanB bean1 : fylist) {
					zdid = bean1.getZdid();
					zdid = zdid != null ? zdid : "";

					jzname = bean1.getZdname();
					jzname = jzname != null ? jzname : "";
					
					zdsx1 = bean1.getProperty();
					zdsx1 = zdsx1 != null ? zdsx1 : "";
					
					shiname = bean1.getShi1();
					shiname = shiname != null ? shiname : "";
					
					xianname = bean1.getXian();
					xianname = xianname != null ? xianname : "";
					
					xiaoquname = bean1.getXiaoqu();
					xiaoquname = xiaoquname != null ? xiaoquname : "";									
					
					DecimalFormat formmat = new DecimalFormat("0.00");
					
					zlfh = bean1.getZlfh();
					if (zlfh == null || zlfh == "" || zlfh == " " || "".equals(zlfh)
					||"null".equals(zlfh)|| "o".equals(zlfh)|| " ".equals(zlfh)) {
						zlfh = "0.00";
					}
					zlfh = formmat.format(Double.parseDouble(zlfh));

					jlfh = bean1.getJlfh();
					if (jlfh == null || jlfh == "" || jlfh == " " || "".equals(jlfh)
					||"null".equals(jlfh)|| "o".equals(jlfh)|| " ".equals(jlfh)) {
						jlfh = "0.00";
					}									
					jlfh = formmat.format(Double.parseDouble(jlfh));
					
					edhdl = bean1.getEdhdl();
					if (edhdl == null || edhdl == "" || edhdl == " " || "".equals(edhdl)
					||"null".equals(edhdl)|| "o".equals(edhdl)|| " ".equals(edhdl)) {
						edhdl = "0.00";
					}
					edhdl = formmat.format(Double.parseDouble(edhdl));

					dbydl = bean1.getDbydl();
					if (dbydl == null || dbydl == "" || dbydl == " " || "".equals(dbydl)
					||"null".equals(dbydl)|| "o".equals(dbydl)|| " ".equals(dbydl)) {
						dbydl = "0.00";
					}									
					dbydl = formmat.format(Double.parseDouble(dbydl));
					
					bzz = bean1.getBzz();
					if (bzz == null || bzz == "" || bzz == " " || "".equals(bzz)
					||"null".equals(bzz)|| "o".equals(bzz)|| " ".equals(bzz)) {
						bzz = "0.00";
					}
					bzz = formmat.format(Double.parseDouble(bzz));
					
					bl1 = bean1.getBl();
					if (bl1 == null || bl1 == "" || bl1 == " " || "".equals(bl1)
					||"null".equals(bl1)|| "o".equals(bl1)|| " ".equals(bl1)) {
						bl1 = "0.00";
					}
					dlcb1 = bean1.getDlcb();
					if (dlcb1 == null || dlcb1 == "" || dlcb1 == " " || "".equals(dlcb1)
					||"null".equals(dlcb1)|| "o".equals(dlcb1)|| " ".equals(dlcb1)) {
						dlcb1 = "0.00";
					}
					dfcb1 = bean1.getDfcb();
					if (dfcb1 == null || dfcb1 == "" || dfcb1 == " " || "".equals(dfcb1)
					||"null".equals(dfcb1)|| "o".equals(dfcb1)|| " ".equals(dfcb1)) {
						dfcb1 = "0.00";
					}
					bl1 = formmat.format(Double.parseDouble(bl1)*100);
					dlcb1 = formmat.format(Double.parseDouble(dlcb1));
					dfcb1 = formmat.format(Double.parseDouble(dfcb1));
					bz = bean1.getBz();
					bz = bz != null ? bz : "0";
				
             countt++;
			 String color=null;
       %> 
    
  <tr height=19 style='height:14.25pt'>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdid%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=edhdl%></td>
   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dbydl%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=bzz%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=bl1%>%</td>
    <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zlfh%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jlfh%></td>
    <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdsx1%></td>
   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=shiname%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xianname%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xiaoquname%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dlcb1%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dfcb1%></td>

 
 </tr>
         <%
         }}
         %>
  <tr> 
  	<td colspan="12">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=countt%>条；</font></b>
	</div>
  </tr>
 <%}%>
 <![endif]>
</table>

</body>

</html>
