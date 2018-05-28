<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
<%
        Account account = new Account();
        account = (Account)session.getAttribute("account");
        String qyear = request.getParameter("qyear");
		CTime time = new CTime();
		String accountId = account.getAccountId();
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
<jsp:useBean id="bean" scope="page" class="com.noki.query.basequery.javabean.PrepaymentQueryBean">
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
                            <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">所属区县</div></td>
                            <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">所属乡镇</div></td>
                           	<td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">站点名称</div></td>                           	
                           	<td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">电表名称</div></td>
                           	<td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">电费支付类型</div></td>
                           	
                           	<td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">金额</div></td>
                           		<td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">供电方式</div></td>
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">起始电表数</div></td>
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">终止电表数</div></td>
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">起始时间</div></td>
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">预计结束时间</div></td>
	                            <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">报账月份</div></td>
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">财务月份</div></td>
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">市级审核</div></td>
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">财务审核</div></td>
                           	<td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">站点类型</div></td>
                           	<td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">费用类型</div></td>

                            	
                            <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">流程号</div></td>	                         
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">录入时间</div></td>
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">录入人员</div></td>    
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">起始年月</div></td>
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">结束年月</div></td>
	                        
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">站点ID</div></td>
	                        <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">电表ID</div></td>                      
                             <td width="10%" class=xl25 height="23" bgcolor="C8E1FB"><div align="center">备注</div></td>                      
                     
                      </tr>
 <%   
         int curpage = Integer.parseInt(request.getParameter("curpage"));  
         String whereStr = request.getParameter("whereStr");
         String zdname=request.getParameter("zdname");
         if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
				whereStr=whereStr+" AND ZD.JZNAME LIKE '%"+zdname+"%'";
		 }
		 if(whereStr==null) whereStr="";
        List<ElectricFeesFormBean> fylist = bean.getPageData(curpage,whereStr,loginId);
       	 //allpage=bean.getAllPage();
		String Id = "",
		feetypeid = "",
		ammeterdegreeidFk = "", 
		money = "",
		money2="",
		startdegree = "",
		stopdegree = "",
		startdate = "",cwyf="",
		enddate = "",gdfs="",dfzflx2="",
		prepaymentammeterid = "",zdid="",jzname="",sjsh="",cwsh="",dbname="",szXian="",szxq="",zdlx="",accountmonth="";
		//int intnum=xh = pagesize*(curpage-1)+1;
		
		String entrytime="",entrypensonnel="",startmonth="",endmonth="",liuchenghao="",memo="";
		//获取条数、金额总
		 int count = 0;
		 double money2count  = 0;
		 
		double money1=0;
		 if(fylist!=null){
			for(ElectricFeesFormBean bean1:fylist){

			//导出条数
			count = count+1;
			
		     //num为序号，不同页中序号是连续的
		    Id = bean1.getStationid();	
		    prepaymentammeterid = bean1.getPrepayment_ammeterid();
		    prepaymentammeterid = prepaymentammeterid != null ? prepaymentammeterid : "";
		    
		    liuchenghao = bean1.getLiuchenghao();	
		    liuchenghao = liuchenghao != null ? liuchenghao : "";
		    
		    feetypeid = bean1.getFeebz();	
		    feetypeid = feetypeid != null ? feetypeid : "";
		    	
		    dbname = bean1.getDbname();
		    dbname = dbname != null ? dbname : "";
		    
		    accountmonth = bean1.getAccountmonth();
		    accountmonth = accountmonth != null ? accountmonth : "";
		    if("null".equals(accountmonth)){accountmonth="";}
		    
		    cwyf = bean1.getKjyf();
		    cwyf = cwyf != null ? cwyf : "";
		    if("null".equals(cwyf)){cwyf="";}
		    
		    		
		     zdid = bean1.getZdcode();
		    zdid = zdid != null ? zdid : "";		
		    
		    zdlx = bean1.getStationtype();
			zdlx=zdlx!=null?zdlx:"";		
		    		
		     jzname = bean1.getJzname();
		    jzname=jzname!=null?jzname:"";
		    
		    memo=bean1.getMemo();
		    memo=memo!=null?memo:"";
		    
		    dfzflx2 = bean1.getDfzflx();
		    if(dfzflx2=="0"||dfzflx2.equals("0")||dfzflx2.equals("null")||dfzflx2==null){
		      dfzflx2="";
		    }
		    
		     //添加录入时间、录入人员、起始时间、结束时间
            entrytime = bean1.getEntrytime();			
			if(entrytime=="0"||entrytime.equals("0")||entrytime.equals("null")){
		      entrytime="";
		    }else{
		   		entrytime = entrytime != null ? entrytime : "";
		    }
		    
		    entrypensonnel = bean1.getEntrypensonnel();			
			if(entrypensonnel=="0"||entrypensonnel.equals("0")||entrypensonnel.equals("null")){
		      entrypensonnel="";
		    }else{
		    	entrypensonnel = entrypensonnel != null ? entrypensonnel : "";
		    }

			startmonth = bean1.getStartmonth();			
			if(startmonth=="0"||startmonth.equals("0")||startmonth.equals("null")){
		      startmonth="";
		    }else{
		    	startmonth = startmonth != null ? startmonth : "";
		    }
		    
		    endmonth = bean1.getEndmonth();			
			if(endmonth=="0"||endmonth.equals("0")||endmonth.equals("null")){
		      endmonth="";
		    }else{
		    	endmonth = endmonth != null ? endmonth : "";
		    }			    
		    			    
		    sjsh = bean1.getCityaudit();
		    sjsh=sjsh!=null?sjsh:"";
		    if(sjsh.equals("0")){
		         sjsh="未通过";
		    }else if(sjsh.equals("1")){
		          sjsh="通过";
		    }else if(sjsh.equals("-2")){
		          sjsh="不通过";
		    }
		   
		   cwsh = bean1.getCw();
		   cwsh=cwsh!=null?cwsh:"";
					
			if(cwsh.equals("0")){
		         cwsh="未通过";
		    }else if(cwsh.equals("1")){
		          cwsh="未通过";
		    }else if(cwsh.equals("2")){
		    cwsh="通过";
 		    }else if(cwsh.equals("-1")){
		    cwsh="不通过";
 		    }						    
		    			    
		    			    
			ammeterdegreeidFk = bean1.getAmmeterdegreeidFk();
			ammeterdegreeidFk = ammeterdegreeidFk != null ? ammeterdegreeidFk : "";
	
		    
		    szXian=bean1.getXian();
		    szXian = szXian != null ? szXian : "";
		    
		    szxq=bean1.getXiaoqu();
		    szxq = szxq != null ? szxq : "";
		    
		    startdegree = bean1.getStartdegree();
		    startdegree = startdegree != null ? startdegree : "";
		    
		    stopdegree = bean1.getStopdegree();
		    stopdegree = stopdegree != null ? stopdegree : "";
		    
		    startdate = bean1.getStartdate();
		    startdate = startdate != null ? startdate : "";
		    
			enddate = bean1.getStopdate();	
			enddate = enddate != null ? enddate : "";	
			
			gdfs = bean1.getGdfs();	
			gdfs = gdfs != null ? gdfs : "";		

			DecimalFormat mat=new DecimalFormat("0.00");
		    money = bean1.getMoney();
		    if(money==null||money==""||money.equals("")||money.equals("null")||money.equals("o")){
		     money="0.00";
		    }
		    money = money != null ? money : "";
		    money1=Double.parseDouble(money);
		    money2=mat.format(money1);
		    money2count = money2count+Double.parseDouble(money2);

			if(startdegree==null||startdegree==""||startdegree.equals("")||startdegree.equals(" ")||startdegree.equals("null")||startdegree.equals("o")){
				startdegree="0.0";
			}
			 DecimalFormat s=new DecimalFormat("0.0");
			 startdegree=s.format(Double.parseDouble(startdegree));
			
			if(stopdegree==null||stopdegree==""||stopdegree.equals("")||stopdegree.equals(" ")||stopdegree.equals("null")||stopdegree.equals("o")){
			 	 stopdegree="0";
			 }
			 stopdegree=s.format(Double.parseDouble(stopdegree));
             
			String color=null;
		
       %>
 <tr height=19 style='height:14.25pt'>  
        <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=szXian%></td>  
        <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=szxq%></td> 	
       	<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>		
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dbname%></td>
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dfzflx2%></td>
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=money2%></td>
        <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=gdfs%></td>
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=startdegree%></td>
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=stopdegree%></td>
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=startdate%></td>
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=enddate%></td>  
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=accountmonth%></td>  
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=cwyf%></td>  
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=sjsh%></td>  
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=cwsh%></td> 		
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdlx%></td>
       		
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=feetypeid%></td>
		
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=liuchenghao%></td>
		
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=entrytime%></td>
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=entrypensonnel%></td>  
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=startmonth%></td>  
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=endmonth%></td>
		
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdid%></td>
		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=prepaymentammeterid%></td>  
			<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=memo%></td> 
 </tr>

 
         <%
         }}
         %>

  <tr> 
  	<td colspan="21">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=count%>条；</font></b>

							<font color='#000080'>&nbsp;金额总和:</font>
							 <b><font color=red>&nbsp;<%=(new DecimalFormat("0.00")).format(money2count)%>元</font></b>
	</div>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
