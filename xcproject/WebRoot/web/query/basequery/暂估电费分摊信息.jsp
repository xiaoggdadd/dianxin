<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.function.*" %>
<%@ page import="java.text.*,java.util.regex.Pattern"%>
<%@ page import="com.noki.electricfees.javabean.*"%>

<%

          Account account = new Account();
        account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        String loginName = account.getAccountName();
        //String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";
        String Wstr = request.getParameter("Wstr")!=null?request.getParameter("Wstr"):"";
        String lrsj = request.getParameter("lrsj")!=null?request.getParameter("lrsj"):"";
        String zgjssj = request.getParameter("zgjssj")!=null?request.getParameter("zgjssj"):"";
		String accountId = account.getAccountId();
        String path = request.getContextPath();
        String color="";
        int intnum =0;

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

<body link=blue vlink=purple>
       <%
        //Vector v = bean.getHuanbi_all("0",qijian,qyear);         
       %>
<table width="1400" height="60%" border="0" cellspacing="1" cellpadding="1" class="form_label">
 	  <tr height = "23" class="relativeTag">
 	     <td class=xl26 align=right style='border-top:none;border-left:none' x:num>序号</td>	                      	
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>成本中心</td>          
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>会计科目</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>专业明细</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>全成本</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>分摊费用</td>	       
 	  </tr>	
 	   <% 
 	   	
 	   	    String Str="";
            if(lrsj != null && !lrsj.equals("")){
			    Wstr=Wstr+" AND G.SAVETIME LIKE '"+lrsj+"%'";
				Str=Str+" AND G.SAVETIME LIKE '"+lrsj+"%'";//录入时间  
			}
			if(zgjssj != null && !zgjssj.equals("")){
				  Wstr=Wstr+" AND G.ZGMONTH='"+zgjssj+"'";//
					Str=Str+" AND G.ZGMONTH='"+zgjssj+"'";//暂估结束月份
			}
 	   
 	   
 	   
 	   
         String shi="";
    	 String xian="";
    	 String qcb="";
    	 String kjkm="";
    	 String zymx="";
    	 String sszy="";
    	 String ftfy="",heji="";
    	 double hj=0;
    	DecimalFormat mat =new DecimalFormat("0.00");
  	    ElectricFeesBean bean = new ElectricFeesBean();
        List<DianfeidandayinBean> listt=bean.getFentan1(Wstr,Str,loginId);
        int count=0;
        for(DianfeidandayinBean ftbean:listt){
        	shi=ftbean.getShi();
        	xian=ftbean.getXian();
        	ftfy=ftbean.getNETWORKDF();
            qcb=ftbean.getQcb();
            kjkm=ftbean.getKjkm();
            zymx=ftbean.getZymx();
            sszy=ftbean.getSszy();
           
            if(null==qcb||qcb.equals("")||qcb.equals(" "))qcb="";
            if(null==kjkm||kjkm.equals("")||kjkm.equals(" "))kjkm="";
            if(null==zymx||zymx.equals("")||zymx.equals(" "))zymx="";
            if(null==ftfy||ftfy.equals("")||ftfy.equals(" "))ftfy="0";
            
            ftfy=mat.format(Double.parseDouble(ftfy));
            count+=1;
            
           
    	 
 	  %>
 	    <tr>
 	        <td>
       		  <div align="center" ><%=count%></div>
       		</td>
            <td>
       		  <div align="center" ><%=shi+xian%></div>
       		</td>
       		<td>
       			<div align="center" ><%=kjkm%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zymx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=qcb%></div>
       		</td>   
       		<td>
       			<div align="center" ><%=ftfy%></div>
       		</td>          
            
       </tr>
       <%
       hj+=Double.parseDouble(ftfy);
        }
        heji=mat.format(hj);
       %>
        <tr class="relativeTag">
         <td><div align="center" >合计</div></td>
         <td colspan="4"><div >&nbsp;</div></td>
         <td ><div align="center" ><%=heji%></div></td>                      
       </tr>
       <tr> 
  	       <td colspan="6"><div align="center"><font color='#000080'>&nbsp;导出条数:</font><b><font color=red><%=count%>条</font></b></div>
       </tr>   
 </table>

</body>

</html>
