<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="java.text.*,com.noki.util.CTime"%>
<%@ page import="java.util.regex.Pattern"%>
<%
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
 	   <td class=xl25 width=72 style='border-left:none;width:54pt'>区县</td>
                         <td class=xl25 width=72 style='border-left:none;width:54pt'>站点名称</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>超市标比例</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>超省标比例</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>市定标电量</td>
            			<td class=xl25 width=72 style='border-left:none;width:54pt'>省定标电量</td>
                         <td class=xl25 width=72 style='border-left:none;width:54pt'>报账月份</td>
                         <td class=xl25 width=72 style='border-left:none;width:54pt'>供电方式</td>
                         <td class=xl25 width=72 style='border-left:none;width:54pt'>电表用电量</td> 
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>上次抄表时间</td> 
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>本次抄表时间</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>报账用电量</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>用电量调整</td>
                         <td class=xl25 width=72 style='border-left:none;width:54pt'>(电量调整)备注</td>
            			<td class=xl25 width=72 style='border-left:none;width:54pt'>单价</td>                        
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>电费调整</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>(电费调整)备注</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>报账电费</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>结算周期</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>超省定标电费额</td> 
                         <td class=xl25 width=72 style='border-left:none;width:54pt'>超省定标电费比值</td>
            			<td class=xl25 width=72 style='border-left:none;width:54pt'>人工审核状态</td>
            			<td class=xl25 width=72 style='border-left:none;width:54pt'>市级审核状态</td>
            			<td class=xl25 width=72 style='border-left:none;width:54pt'>财务审核状态</td>     
            			<td class=xl25 width=72 style='border-left:none;width:54pt'>电表名称</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>电费支付类型</td>  
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>录入标志</td>  
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>票据金额</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>票据类型</td> 
                         <td class=xl25 width=72 style='border-left:none;width:54pt'>站点类型</td>
            			<td class=xl25 width=72 style='border-left:none;width:54pt'>站点ID</td>
            			<td class=xl25 width=72 style='border-left:none;width:54pt'>电表ID</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>站点属性</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>上期电费</td>
            			<td class=xl25 width=72 style='border-left:none;width:54pt'>上期电量</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>上期单价</td>
                        <td class=xl25 width=72 style='border-left:none;width:54pt'>上期余额</td>
 </tr>
 <%   
         String whereStr = request.getParameter("whereStrdc");
 		 String str = request.getParameter("strdc");
         String zdname=request.getParameter("zdname");
         String lrbz1=request.getParameter("lrbz1");
         String lrbz2=request.getParameter("lrbz2");
				//whereStr= java.net.URLDecoder.decode(whereStr,"UTF-8");
			if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
				whereStr=whereStr+" and zd.jzname like '%"+zdname+"%'";
				str = str+" and zd.jzname like '%"+zdname+"%'"; 
			}
			 String lrrq=request.getParameter("lrrq");
				//whereStr= java.net.URLDecoder.decode(whereStr,"UTF-8");
			if(lrrq != null && !lrrq.equals("")&& !lrrq.equals("null")){
				whereStr=whereStr+" and to_char(ef.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
				str = str+" and to_char(ef.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
			}
       	 System.out.println("StationPointQueryBean-whereStr:"+whereStr);
         //StationPointQueryBean bean = new StationPointQueryBean();
         if(whereStr==null){
         whereStr="";
         }
	     ElectricFeesBean bean=new ElectricFeesBean();
       	 List<ElectricFeesFormBean> fylist = null;
         fylist = bean.getPageDataCheck123(whereStr,str,loginId,lrbz1,lrbz2);

         double ds=0.0,dfs=0.0;
         double dfz=0.0,dfzs=0.0;
         
         if(fylist!=null)
			{
				//int fycount = ((Integer)fylist.get(0)).intValue();
				for(ElectricFeesFormBean elecbean:fylist){
					
					String jzname = elecbean.getZdname();
					String dbname = elecbean.getDbname();
					String dfzflx = elecbean.getDfzflx();
					String accountmonth = elecbean.getAccountmonth();
					String gdfs = elecbean.getGdfs();
					String dbydl = elecbean.getDbydl();
					String lastdatetime = elecbean.getLastdatetime();
					String thisdatetime = elecbean.getThisdatetime();
					String bzydl = elecbean.getBzydl();
					String floatdegree = elecbean.getFloatdegree();
					String memo = elecbean.getMemo();
					String edhdl = elecbean.getEdhdl();
					String qsdbdl = elecbean.getQsdbdl();
					String unitprice = elecbean.getUnitprice();
					String floatpay = elecbean.getFloatpay();
					String memo1 = elecbean.getMemo1();
					String actualpay = elecbean.getActualpay();
					String jszq = elecbean.getJszq();
					String csdbdfe = elecbean.getCsdbdfe();
					String csdbdfbz = elecbean.getCsdbdfbz();
					String manualauditstatus = elecbean.getRgshzt();
					String cwsh = elecbean.getCwsh();
					String sjshzt1 = elecbean.getSjshzt();
					//String rgshzt = elecbean.getRgshzt()
					String beilv = elecbean.getBeilv();
					String pjsj = elecbean.getPjsj();
					String stationtype1 = elecbean.getStationtype();
					String pjbh = elecbean.getPjbh();
					String pjlx = elecbean.getPjlx();
					String jfsj = elecbean.getJfsj();
					String zdid = elecbean.getZdid();
					String dbid = elecbean.getDbid();
					String blhdl = elecbean.getBlhdl();
					String csbbl = elecbean.getCsbbl();
					String csbl = elecbean.getCsbl();
					String property = elecbean.getProperty();
					String quxian = elecbean.getQuxian();
					String dfbzw = elecbean.getDfbzw();
					String uuid = elecbean.getUuid();
					String biaozhi = elecbean.getBiaozhi();
					String autoauditstatus = elecbean.getAutoauditstatus();
					String dlsh = elecbean.getDlsh();
					String autoauditdescription = elecbean.getAutoauditdescription();
					String dlshzt = elecbean.getDlshzt();
					String sort = elecbean.getSort();
					double pjje = elecbean.getPjje();
					//2014-4-15新增--上期电费-上期电量-上期单价---
					String lastelecfees = elecbean.getLastelecfees();
					String lastelecdegree = elecbean.getLastelecdegree();
					String lastunitprice = elecbean.getLastunitprice();
					String lastyue = elecbean.getLastyue();
					
					if(null==blhdl||blhdl==""||blhdl=="o"||"null".equals(blhdl)||"".equals(blhdl))blhdl="0";
		             dfs=Double.parseDouble(blhdl);
		             if(actualpay==null||"".equals(actualpay)||"o".equals(actualpay)||"null".equals(actualpay))actualpay="0";
		             dfzs=Double.parseDouble(actualpay);

	         		ds=ds+dfs;
	           		dfz=dfz+dfzs;

	       %>
	       
	       
  					<tr height=19 style='height:14.25pt'>
                        <td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=quxian%></td>
                        <td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=csbbl%>%</td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=csbl%>%</td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=edhdl%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=qsdbdl%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=accountmonth%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=gdfs%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=dbydl%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=lastdatetime%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=thisdatetime%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=blhdl%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=floatdegree%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=memo%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=unitprice%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=floatpay%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=memo1%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=actualpay%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=jszq%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=csdbdfe%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=csdbdfbz%></td>
						<%if(manualauditstatus.equals("1")||manualauditstatus.equals("-1")||manualauditstatus.equals("2")){ %>
							<td class=xl25 align=right style='border-top:none;border-left:none' x:num>通过</td>
		       			<%}else if(manualauditstatus.equals("-2")){%>
		       	        	<td class=xl25 align=right style='border-top:none;border-left:none' x:num>不通过</td>   
		       			<%}else{ %>
		       	        	<td class=xl25 align=right style='border-top:none;border-left:none' x:num>未审核</td>     
		       			<%} %>	
						<%if(sjshzt1.equals("1")){ %>
							<td class=xl25 align=right style='border-top:none;border-left:none' x:num>通过</td>
		       			<%}else if(sjshzt1.equals("-2")){%>
		       	        	<td class=xl25 align=right style='border-top:none;border-left:none' x:num>不通过</td>   
		       			<%}else{ %>
		       	        	<td class=xl25 align=right style='border-top:none;border-left:none' x:num>未审核</td>     
		      		 	<%} %>		
						<%if(cwsh.equals("2")){ %>
							<td class=xl25 align=right style='border-top:none;border-left:none' x:num>通过</td>
		       			<%}else if(cwsh.equals("-1")){%>
		       	        	<td class=xl25 align=right style='border-top:none;border-left:none' x:num>不通过</td>   
		       			<%}else{ %>
		       	        	<td class=xl25 align=right style='border-top:none;border-left:none' x:num>未审核</td>     
		       			<%} %>		
		       			<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=dbname%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=dfzflx%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=sort%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=pjje%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=pjlx%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=stationtype1%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=zdid%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=dbid%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=property%></td>
 						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=lastelecfees%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=lastelecdegree%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=lastunitprice%></td>
 						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=lastyue%></td>
 				</tr>
         <%
         }}
         %>
         <%
        DecimalFormat price1=new DecimalFormat("0.00");
        String s="";
        String fy="";
        s= price1.format(ds); 
        fy=price1.format(dfz);
        %>
  <tr> 
  	<td colspan="26">
  	<div align="center">
							<font color='#000080'>电量合计：</font>
							 <b><font color=red><%=s%>度；</font></b>
							 <font color='#000080'>电费合计:</font>
							 <b><font color=red><%=fy%>元</font></b>
	</div>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
