<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
<%
        Account account = new Account();
        account = (Account)session.getAttribute("account");
        String qyear = request.getParameter("qyear");
		CTime time = new CTime();
		//String accountId = account.getAccountId();
		String loginId = account.getAccountId();
        String loginName = account.getAccountName();
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
<jsp:useBean id="bean" scope="page" class="com.noki.electricfees.javabean.PrepaymentBean">
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
 <tr  height = "20">
                           
                        <td class=xl25 width=152 style='border-left:none;width:114pt'><div align="center">站点名称</div>	</td>
                        <td class=xl25 width=152 style='border-left:none;width:114pt'><div align="center">站点类型</div>	</td>
                        <td class=xl25 width=152 style='border-left:none;width:114pt'><div align="center">费用类型</div></td>
                        <td class=xl25 width=152 style='border-left:none;width:114pt'><div align="center">电费支付类型</div></td>
                        <td class=xl25 width=152 style='border-left:none;width:114pt'><div align="center">金额</div></td>           			
                        <td class=xl25 width=152 style='border-left:none;width:114pt'><div align="center">起始电表数</div></td>
                        <td class=xl25 width=152 style='border-left:none;width:114pt'><div align="center">终止电表数</div></td>
                        <td class=xl25 width=152 style='border-left:none;width:114pt'><div align="center">实际用电量</div></td>
                        <td class=xl25 width=152 style='border-left:none;width:114pt'><div align="center">起始时间</div></td>
                        <td class=xl25 width=152 style='border-left:none;width:114pt'><div align="center">预计结束时间</div></td>    
                        <td class=xl25 width=152 style='border-left:none;width:114pt'><div align="center">电表ID</div></td>                     
                      </tr>
 <%   
         int curpage = Integer.parseInt(request.getParameter("curpage"));  
         String whereStr = request.getParameter("whereStr");	
         String zdname = request.getParameter("zdname");
                   
         String ammeterid1 = request.getParameter("ammeterid1");
		 System.out.println("PrepaymentBean-whereStr:"+whereStr);
		 if(whereStr==null) whereStr="";
		 
		 
		 if(zdname != null && !zdname.equals("") && !zdname.equals("0")&&!"null".equals(zdname)){
				whereStr=whereStr+" and zd.jzname like '%"+zdname+"%'";
				
			}
		 if(ammeterid1 != null && ammeterid1 != "" && !ammeterid1.equals("0")&&!"null".equals(zdname)){
				whereStr=whereStr+" and pr.prepayment_ammeterid like '%"+ammeterid1+"%'";
			}
		
         ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageData(whereStr,loginId);
       	 //allpage=bean.getAllPage();
		String Id = "",
		feetypeid = "",
		ammeterdegreeidFk = "", 
		money = "",
		money2="",
		startdegree = "",
		startdate = "",
		enddate = "",
		prepaymentammeterid = "";
		String zdlx="";
		String stopdegree="",actualdegree="";
		String dfzflx="";
		String jznamem="";
		 double money1=0;
		 
		 //获取条数、金额总和
		 int count = 0;
		 double moneycount = 0;
		 
		//int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

			//导出条数
			count = count+1;
			
		     //num为序号，不同页中序号是连续的
		    Id = (String)fylist.get(k+fylist.indexOf("ID"));	
		    prepaymentammeterid = (String)fylist.get(k+fylist.indexOf("PREPAYMENT_AMMETERID"));	
		    prepaymentammeterid = prepaymentammeterid != null ? prepaymentammeterid : "";
		    
		    feetypeid = (String)fylist.get(k+fylist.indexOf("NAME"));	
		    feetypeid = feetypeid != null ? feetypeid : "";
		    
		    jznamem = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    jznamem = jznamem != null ? jznamem : "";
		    if(jznamem.equals("null")){
		    jznamem="";
		    }
		    			    
			ammeterdegreeidFk = (String)fylist.get(k+fylist.indexOf("AMMETERDEGREEID_FK"));
			ammeterdegreeidFk = ammeterdegreeidFk != null ? ammeterdegreeidFk : "";
			
		    DecimalFormat mat=new DecimalFormat("0.00");
		    money = (String)fylist.get(k+fylist.indexOf("MONEY"));
		    money = money != null ? money : "";
		    if(money.equals("null")){
			 money="0.0";
			 }
		    money1=Double.parseDouble(money);
		    money2=mat.format(money1);
		    moneycount = moneycount+Double.parseDouble(money2);
		    
		    stopdegree = (String)fylist.get(k+fylist.indexOf("STOPDEGREE"));
		    stopdegree = stopdegree != null ? stopdegree : "";
		    
		    startdegree = (String)fylist.get(k+fylist.indexOf("STARTDEGREE"));
		    startdegree = startdegree != null ? startdegree : "";
		    
		    dfzflx=(String)fylist.get(k+fylist.indexOf("DFZFLX"));
			dfzflx = dfzflx != null ? dfzflx : "";	
		    
		    startdate = (String)fylist.get(k+fylist.indexOf("STARTDATE"));
		    startdate = startdate != null ? startdate : "";
		    
			enddate = (String)fylist.get(k+fylist.indexOf("ENDDATE"));
			enddate = enddate != null ? enddate : "";			
            zdlx = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
			zdlx = zdlx != null ? zdlx : "";

            if(stopdegree==null||stopdegree.equals("")||stopdegree.equals("null")) stopdegree="0";
		     DecimalFormat s=new DecimalFormat("0.0");
			 stopdegree=s.format(Double.parseDouble(stopdegree));
			 
			 if(startdegree==null||startdegree.equals("")||startdegree.equals("null")) startdegree="0";
		    // DecimalFormat s=new DecimalFormat("0.0");
			 startdegree=s.format(Double.parseDouble(startdegree));
			 
			 actualdegree=(String)fylist.get(k+fylist.indexOf("ACTUALDEGREE"));
			 actualdegree = actualdegree != null ? actualdegree : "";
		     if(actualdegree.equals("null")||actualdegree==null||actualdegree.equals("")){
			  actualdegree="0.0";
			 }
			 
			 actualdegree=s.format(Double.parseDouble(actualdegree));
			String color=null;
       %>
 <tr height=19 style='height:14.25pt'>
 
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jznamem%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdlx%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=feetypeid%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=dfzflx%></td>  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=money2%></td>  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=startdegree%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=stopdegree%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=actualdegree%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=startdate%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=enddate%></td>  
   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=prepaymentammeterid%></td>
 </tr>
         <%
         }}
         %>
   <tr> 
  	<td colspan="11">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=count%>条；</font></b>

							<font color='#000080'>&nbsp;金额总和:</font>
							 <b><font color=red>&nbsp;<%=(new DecimalFormat("0.00")).format(moneycount)%>元</font></b>
	</div>
  </tr>
 <![endif]>
</table>
</body>
</html>
