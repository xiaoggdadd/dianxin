<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
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
 <td class=xl25 width=142 height=19  style='height:14.25pt;width:166pt'>区县</td>  
 <td class=xl25 width=142 height=19  style='height:14.25pt;width:166pt'>乡镇</td>  
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>站点名称</td>  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>实际电费金额</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>报账月份</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>流程号</td>
  <td class=xl25 width=165 style='border-left:none;width:124pt'>站点属性</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>站点类型</td>
  
   <td class=xl25 width=72 style='border-left:none;width:54pt'>电流类型</td>
    <td class=xl25 width=72 style='border-left:none;width:54pt'>用电设备</td>
     <td class=xl25 width=72 style='border-left:none;width:54pt'>电量支付类型</td>      
       <td class=xl25 width=72 style='border-left:none;width:54pt'>票据类型</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>票据编号</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>票据金额</td>
         <td class=xl25 width=72 style='border-left:none;width:54pt'>自动审核状态</td>
          <td class=xl25 width=72 style='border-left:none;width:54pt'>人工审核状态</td>
           <td class=xl25 width=72 style='border-left:none;width:54pt'>财务审核状态</td>
           <td class=xl25 width=152 style='border-left:none;width:114pt'>电表ID</td>
 </tr>
 <%   
         String whereStr = request.getParameter("whereStr");	
         String str2 = request.getParameter("str2");
         String str1 = request.getParameter("str1");  
         String jizhan=request.getParameter("jizhan");
          String lrrq=request.getParameter("lrrq");
				//whereStr= java.net.URLDecoder.decode(whereStr,"UTF-8");
			if(jizhan != null && !jizhan.equals("")&& !jizhan.equals("0")){
				whereStr=whereStr+" and zd.jzname like '%"+jizhan+"%'";
				
			}
			if(lrrq != null && !lrrq.equals("")&& !lrrq.equals("0")){
				whereStr=whereStr+" and to_char(ef.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
				
			}
       	 System.out.println("StationPointQueryBean-whereStr:"+whereStr);
         //StationPointQueryBean bean = new StationPointQueryBean();
         if(whereStr==null){
         whereStr="";
         }
         ElectricFeesBean bean=new ElectricFeesBean();
         
       	 List<ElectricFeesFormBean> fylist = null;
       	 fylist = bean.getPageDataCheckdaochu(whereStr,loginId,str1,str2);
       	 
		String electricfeeId = "",unitprice = "",
		floatpay = "", actualpay = "",notetypeid = "",
		noteno = "",dfzflx = "",liuchenghao="",bzyf="",pjje="";
		String jzname = "",dbid="",autoauditstatus = "",manualauditstatus = "",dllx = "",ydsb = "",xian= "",xiaoqu="";
		Double df=0.00;
		double sjdf=0.00;
		int countt=0;
		//=================新增==========
		String zdsxx="";
		String zdlxx="";
		//===============xin===========
		
		 if(fylist.size()>0)
		{
		    
			//int fycount = ((Integer)fylist.get(0)).intValue();
			for(ElectricFeesFormBean bean1:fylist){

		     //num为序号，不同页中序号是连续的
			jzname = bean1.getJzname();
		    jzname = jzname != null ? jzname : "";
		    
		    xian=bean1.getXian();
		    xian=xian!=null?xian:"";
		    
		    xiaoqu=bean1.getXiaoqu();
		    xiaoqu=xiaoqu!=null?xiaoqu:"";
		    
			dbid = bean1.getDbid();
		    dbid = dbid != null ? dbid : "";	
		     
			zdsxx = bean1.getProperty();
		    zdsxx = zdsxx != null ? zdsxx : "";
		     
			liuchenghao = bean1.getLiuchenghao();
		    liuchenghao = liuchenghao != null ? liuchenghao : "";
		     
			bzyf = bean1.getAccountmonth();
		    bzyf = bzyf != null ? bzyf : "";
		     
			zdlxx = bean1.getStationtype();
		    zdlxx = zdlxx != null ? zdlxx : "";		     
		     
		     
			dllx = bean1.getDllx();
		    dllx = dllx != null ? dllx : "";
		    if("null".equals(dllx)){
		     dllx="";
		    }
		    
			ydsb = bean1.getYdsb();
		    ydsb = ydsb != null ? ydsb : "";
		     
			autoauditstatus = bean1.getAutoauditstatus();
		    autoauditstatus = autoauditstatus != null ? autoauditstatus : "";
		     
			manualauditstatus = bean1.getManualauditstatus();
		    manualauditstatus = manualauditstatus != null ? manualauditstatus : "";
		    
			dfzflx = bean1.getDfzflx();
		    dfzflx = dfzflx != null ? dfzflx : "";
		    
			electricfeeId = bean1.getElectricfeeId();
		    electricfeeId = electricfeeId != null ? electricfeeId : "";	
		    		    
			unitprice = bean1.getUnitprice();
			unitprice = unitprice != null ? unitprice : "";
			
			floatpay = bean1.getFloatpay();
		    floatpay = floatpay != null ? floatpay : "";
		    
			actualpay = bean1.getActualpay();
		    actualpay = actualpay != null ? actualpay : "";
		    if(actualpay.equals("null")){
		     actualpay="0";
		    }
		     
			notetypeid = bean1.getNotetypeid();
		    notetypeid = notetypeid != null ? notetypeid : "";
		    
			noteno = bean1.getNoteno();
			noteno = noteno != null ? noteno : "";
			if(noteno.equals("null")){
			 	noteno="";
			}
			pjje=bean1.getPjjedf();
			pjje= pjje!=null?pjje:"";
		    
            DecimalFormat mat=new DecimalFormat("0.00");
            if(actualpay==null||actualpay=="")actualpay="0";
            df=Double.parseDouble(actualpay);
            actualpay=mat.format(df);
			sjdf=df+sjdf;
             countt++;
			if(autoauditstatus!=null&&autoauditstatus.equals("1")){ 
       		autoauditstatus="通过";
       	   }else{ 
       	   autoauditstatus="未通过";
       	    }
       	    
       	    
			String color=null;

%>
<tr height=19 style='height:14.25pt'>
<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xian%></td>  
<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xiaoqu%></td>  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=actualpay%></td>
   <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=bzyf%></td>
    <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=liuchenghao%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdsxx%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdlxx%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dllx%></td>
  
    <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=ydsb%></td>
     <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=dfzflx%></td> 
 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=notetypeid%></td>
 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=noteno%></td>
 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=pjje%></td>
   <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=autoauditstatus%></td>
   
 
<%if(manualauditstatus.equals("1")){ %>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num>通过</td>
       	   <%}else{ %>
       	    <td class=xl26 align=right style='border-top:none;border-left:none' x:num>未通过</td>      	     
       	   <%} %>
 <%if(manualauditstatus!=null&&manualauditstatus.equals("2")){ %>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num>通过</td>
       	   <%}else{ %>
       	    <td class=xl26 align=right style='border-top:none;border-left:none' x:num>未通过</td>     	     
       	   <%} %>
   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dbid%></td>
 </tr>
         <%
         }}
         %>
        
  <tr> 
  	<td colspan="18">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=countt%>条；</font></b>
							 <font color='#000080'>&nbsp;实际电费金额总计:</font>
							 <b><font color=red>&nbsp;<%=(new DecimalFormat("0.00")).format(sjdf)%>元</font></b>
	</div>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
