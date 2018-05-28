<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,java.util.Vector,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
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
String color="";
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
 <tr class=xl24 height=19 style='height:14.25pt'>  
  <td class=xl25 width=152 style='border-left:none;width:114pt'>所在地区</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点名称</td>  
  <td class=xl25 width=165 style='border-left:none;width:124pt'>站点类型</td>
  <td class=xl25 width=165 style='border-left:none;width:124pt'>上次抄表时间</td>
  <td class=xl25 width=165 style='border-left:none;width:54pt'>本次抄表时间</td>
 
  <td class=xl25 width=152 style='border-left:none;width:114pt'>上次读数</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>本次读数</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>电量调整</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>耗电量</td>
   <td class=xl25 width=165 style='border-left:none;width:54pt'>电费单价</td>
 
  <td class=xl25 width=152 style='border-left:none;width:114pt'>电费调整</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>实付费用</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>报账月份</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>支付类型</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>票据类型</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>票据金额</td>
  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>上期电费</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>上期电量</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>上期单价</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>供电方式</td>
  
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>备注</td>
 </tr>
 <%  
 
   String whereStr = request.getParameter("whereStr");	  
   String str = request.getParameter("str");	
         String zdname=request.getParameter("zdname");
				//whereStr= java.net.URLDecoder.decode(whereStr,"UTF-8");
			if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
				whereStr=whereStr+" and JZ.JZNAME like '%"+zdname+"%'";
				
			}
       	 System.out.println("StationPointQueryBean-whereStr:"+whereStr);
         //StationPointQueryBean bean = new StationPointQueryBean();
         if(whereStr==null){
         whereStr="";
         }
 ElectricFeesBean  beana = new ElectricFeesBean();
        List<ElectricFeesFormBean> fylist=null;     
        if(whereStr==null)whereStr="";
        fylist= bean.getPageDataCaiwu(whereStr,str,loginId);
       String electricfeeId = "",unitprice = "",floatpay = "", actualpay = "";
		String jzname = "",df="",szdq="",jztype="",inputdatetime="",inputoperator="",lastdatetime="",thisdatetime="",uuid="",
		accountmonth="",lastdegree="",thisdegree="",actualdegree="",floatdegree="",fffs="",memo="",pjlx="",pjje="";
		//2014-4-15新增--上期电费-上期电量-上期单价---供电方式
		String lastelecfees = "", lastelecdegree = "", lastunitprice = "",gdfs="";
		int linenum=0;
		int jnum=1;
		double actualpaycount=0;
		double actualhdl=0;
		Double dianfei=0.0;	
		//获取条数、实际电费金额总和
		 int count = 0;
		 
		//int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			//int fycount = ((Integer)fylist.get(0)).intValue();
			for(ElectricFeesFormBean bean1:fylist){
              linenum++;
		     //num为序号，不同页中序号是连续的
		    jzname = bean1.getJzname();
		    jzname = jzname != null ? jzname : "";
		    	
		    szdq = bean1.getSzdq();	
		    szdq = szdq != null ? szdq : "";
		     
		    electricfeeId = bean1.getElectricfeeId();	
		    electricfeeId = electricfeeId != null ? electricfeeId : "";
		    
		 	uuid = bean1.getDfuuid();	
		    uuid = uuid != null ? uuid : "";
		    			    
			jztype = bean1.getJztype();
			jztype = jztype != null ? jztype : "";			
		    
		    lastdatetime = bean1.getLastdatetime();
		    lastdatetime = lastdatetime != null ? lastdatetime : "";
		    
			thisdatetime = bean1.getThisdatetime();
			thisdatetime = thisdatetime != null ? thisdatetime : "";
						
			accountmonth = bean1.getAccountmonth();
			accountmonth = accountmonth != null ? accountmonth : "";
			
		    lastdegree = bean1.getLastdegree();
		    lastdegree = lastdegree != null ? lastdegree : "0";
		    
		    thisdegree = bean1.getThisdegree();
		    thisdegree = thisdegree != null ? thisdegree : "0";
		    
		    actualdegree = bean1.getBlhdl();
		    actualdegree = actualdegree != null ? actualdegree : "0";
		    
		    floatdegree = bean1.getFloatdegree();
		    floatdegree = floatdegree != null ? floatdegree : "0";
		    
		    unitprice = bean1.getUnitprice();
		    unitprice = unitprice != null ? unitprice : "0";
		    
		    floatpay = bean1.getFloatpay();
		    floatpay = floatpay != null ? floatpay : "0";
		    
		    actualpay = bean1.getActualpay();
		    actualpay = actualpay != null ? actualpay : "0";
		    
		    fffs = bean1.getDfzflx();
		    fffs = fffs != null ? fffs : "";
		    
		    memo = bean1.getMemo();
		    memo = memo != null ? memo : "";
		    
		    lastelecfees = bean1.getLastelecfees();
		    lastelecfees = lastelecfees != null ? lastelecfees : "";
		    
		    lastelecdegree = bean1.getLastelecdegree();
		    lastelecdegree = lastelecdegree != null ? lastelecdegree : "";
		    
		    lastunitprice = bean1.getLastunitprice();
		    lastunitprice = lastunitprice != null ? lastunitprice : "";
		    
		    pjlx = bean1.getNotetypeid();
		    pjlx = pjlx != null ? pjlx : "";
		    pjje = bean1.getPjjedy();
		    pjje = pjje != null ? pjje : "0";
		    gdfs = bean1.getGdfs();
		    gdfs = gdfs != null ? gdfs : "";
		    if(null==pjje||pjje.equals("")||pjje.equals(" ")||pjje.equals("null"))pjje="0";
		    if(null==actualpay||actualpay.equals("")||actualpay.equals(" ")||actualpay.equals("null"))actualpay="0";
		    if(null==actualdegree||actualdegree.equals("")||actualdegree.equals(" ")||actualdegree.equals("null"))actualdegree="0";
		    if(null==lastdegree||lastdegree.equals("")||lastdegree.equals(" ")||lastdegree.equals("null"))lastdegree="0";
		    if(null==thisdegree||thisdegree.equals("")||thisdegree.equals(" ")||thisdegree.equals("null"))thisdegree="0";
		    if(null==unitprice||unitprice.equals("")||unitprice.equals(" ")||unitprice.equals("null"))unitprice="0";
		    if(null==floatdegree||floatdegree.equals("")||floatdegree.equals(" ")||floatdegree.equals("null"))floatdegree="0";
		    if(null==floatpay||floatpay.equals("")||floatpay.equals(" ")||floatpay.equals("null"))floatpay="0";
		    
            DecimalFormat mat =new DecimalFormat("0.00");
            pjje=mat.format(Double.parseDouble(pjje));
            actualpay=mat.format(Double.parseDouble(actualpay));
            floatpay=mat.format(Double.parseDouble(floatpay));
            
            DecimalFormat dlmat=new DecimalFormat("0.0");
            actualdegree=dlmat.format(Double.parseDouble(actualdegree));
            lastdegree=dlmat.format(Double.parseDouble(lastdegree));
            thisdegree=dlmat.format(Double.parseDouble(thisdegree));
            floatdegree=dlmat.format(Double.parseDouble(floatdegree));
            actualpaycount+=Double.parseDouble(actualpay);
            DecimalFormat price=new DecimalFormat("0.0000");
            unitprice = price.format(Double.parseDouble(unitprice));
            actualhdl+=Double.parseDouble(actualdegree);
        count++;

       %>
       <tr height=19 style='height:14.25pt'>
        <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=szdq%></td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>
          <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jztype%></td>
           <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=lastdatetime%></td>
            <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=thisdatetime%></td>
             <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=lastdegree%></td>
              <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=thisdegree%></td>
       		      
       		       <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=floatdegree%></td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=actualdegree%></td>
          <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=unitprice%></td>
           <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=floatpay%></td>
            <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=actualpay%></td>
             <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=accountmonth%></td>
              <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=fffs%></td>
               <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=pjlx%></td>
               <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=pjje%></td>
               
               <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=lastelecfees%></td>
               <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=lastelecdegree%></td>
                <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=lastunitprice%></td>
                <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=gdfs%></td>
    
                <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=memo%></td>
       </tr>
       <%}}
         %>
  <tr> 
  	<td colspan="18">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=count%>条；</font></b>
                             
                             <font color='#000080'>&nbsp;耗电量总和:</font>
							 <b><font color=red><%=(new DecimalFormat("0.00")).format(actualhdl)%>度；</font></b>

							<font color='#000080'>&nbsp;实付费用总和:</font>
							 <b><font color=red>&nbsp;<%=(new DecimalFormat("0.00")).format(actualpaycount)%>元</font></b>
	</div>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
