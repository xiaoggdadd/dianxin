<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.function.*"%>
<%@ page import="java.text.*,java.util.regex.Pattern"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%

        Account account = new Account();
        account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        String loginName = account.getAccountName();
        String Wstr = request.getParameter("Wstr")!=null?request.getParameter("Wstr"):"";
        String lrsj = request.getParameter("lrsj")!=null?request.getParameter("lrsj"):"";
        String endzgsj = request.getParameter("endzgsj")!=null?request.getParameter("endzgsj"):"";
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
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>站点名称</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>所在地区</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>站点类型</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>电费支付类型</td>
	     <td class=xl26 align=right style='border-top:none;border-left:none' x:num>暂估起始时间</td>	                      	
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>暂估结束时间</td>          
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>暂估天数</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>日电费（元/天）</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>暂估金额</td>   
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>录入人</td>      
 	  </tr>	
 	  <% 
 	  	String Str="";
      	if(lrsj != null && !lrsj.equals("")){
		    Wstr=Wstr+" AND G.SAVETIME LIKE '"+lrsj+"%'";
			Str=Str+" AND G.SAVETIME LIKE '"+lrsj+"%'";//录入时间  
		}
		if(endzgsj != null && !endzgsj.equals("")){
			  Wstr=Wstr+" AND G.ZGMONTH='"+endzgsj+"'";//
				Str=Str+" AND G.ZGMONTH='"+endzgsj+"'";//暂估结束月份
		}
		
		zanguservlet bean1 = new zanguservlet();
		ArrayList<CityQueryBean> fylist = null;
		String jzname="",address="",zgqssj="",zgjssj="",stationtype="",dfzflx1="",tianshu="",danjia="",zgje="",zdid="",lrren="",heji="0";
		double heji1=0;
	 	double danjia1=0.00;
	 	int count = 0;
			
	 	fylist = bean1.getPageDataZgjt(Wstr,loginId,Str);    	
	 	
    	 if(fylist!=null){
 			for(CityQueryBean beans:fylist){ 	
 				jzname=beans.getJzname();
 				address=beans.getAddress();
 				zgqssj=beans.getZangustartmonth();
 				
 				if(zgqssj.equals("")||zgqssj==null){
 				zgqssj="";
 				}else{
 				  zgqssj=zgqssj.substring(0,7);
 				}
 				
 				zgjssj=beans.getZanguendmonth();
 				if(zgjssj.equals("")||zgjssj==null){
 				zgjssj="";
 				}else{
 				  zgjssj=zgjssj.substring(0,7);
 				}
 				
 				stationtype=beans.getStationtype();
 				dfzflx1=beans.getDfzflx();
 				tianshu=beans.getTianshu();
 				danjia=beans.getDianfei();
 				zgje=beans.getZangumoney();
 				if(zgje==null||"".equals(zgje))zgje="0";
 				DecimalFormat mat1=new DecimalFormat("0.00");
 		 		zgje=mat1.format(Double.parseDouble(zgje));
 				zdid=beans.getZdid();
 				lrren=beans.getLrren();
 				
 				heji1=heji1+Double.valueOf(zgje);
 		        heji=heji1+"";
 		        
 		        count += 1;
 	  %>
 	   <tr>
       		<td>
       			<div align="center" ><%=count%></div>
       		</td>      
            <td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="left" ><%=address%></div>
       		</td>
       		<td>
       			<div align="center" ><%=stationtype%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dfzflx1%></div>
       		</td>
			<td>
       			<div align="center" >&nbsp;<%=zgqssj%></div>
       		</td>
       		<td>
       			<div align="center" >&nbsp;<%=zgjssj%></div>
       		</td>          
            <td>
       			<div align="right" ><%=tianshu%></div>
       		</td>
       		<td>
       			<div align="right" ><%=danjia%></div>
       		</td>
       		<td>
       			<div align="right" ><%=zgje%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=lrren%></div>
       		</td>
       </tr>  
    
       <%
	       	DecimalFormat mat=new DecimalFormat("0.00");
			heji=mat.format(Double.parseDouble(heji));
 		}
    }%>
 	   <tr class="relativeTag">
         <td colspan="2"><div align="center" >合计</div></td>
         <td ><div align="center" ><%=heji%></div></td>                      
        </tr>    
        <tr> 
  	       <td colspan="11"><div align="center"><font color='#000080'>&nbsp;导出条数:</font><b><font color=red><%=count%>条</font></b></div>
  		</tr> 
 
   
 </table>

</body>

</html>
