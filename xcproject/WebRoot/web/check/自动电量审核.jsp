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
<jsp:useBean id="bean" scope="page" class="com.noki.ammeterdegree.javabean.AmmeterDegreeBean">
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
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点名称</td>
    <td class=xl25 width=165 style='border-left:none;width:124pt'>上次电表读数</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>上次抄表时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>本次电表读数</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>本次抄表时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>用电量调整</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>折算后用电量</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点类型</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点属性</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>集团报表类型</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>电流类型</td>
  <td class=xl25 width=174 style='border-left:none;width:131pt'>用电设备</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>电表ID</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>修改</td>
 </tr>
 <%   
         int curpage = Integer.parseInt(request.getParameter("curpage"));  
         String whereStr = request.getParameter("whereStr");	         
		 System.out.println("AmmeterDegreeBean---whereStr:"+whereStr);
         //AmmeterDegreeBean bean = new AmmeterDegreeBean();
         String jizhan = request.getParameter("jizhan");
         if(jizhan != null &&!jizhan.equals("")&& !jizhan.equals("0")&&!jizhan.equals("null")){
			whereStr=whereStr+" and zd.jzname like '%"+jizhan+"%'";
		}
		
		String lrrq=request.getParameter("lrrq");
		 if(lrrq != null &&!lrrq.equals("")&&!lrrq.equals("null")){
			whereStr=whereStr+" and to_char(ad.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
		}
		System.out.println(jizhan+"AmmeterDegreeBean---*whereStr:"+whereStr);
         if(whereStr==null) whereStr="";
       	 ArrayList fylist = new ArrayList();
		 fylist = bean.getPageDatash(whereStr,loginId);
       	 //int allpage=bean.getAllPage();
		 String electriccurrenttype_ammeter = "",usageofelectypeid_ammeter = "",ammeterdegreeid = "",ammeterid = "",lastdegree = "", thisdegree = "",lastdatetime = "",thisdatetime = "",floatdegree = "",actualdegree = "",zdmc="",autoauditstatus = "",autoauditdescription = "",manualauditstatus = "",stationtype2="",jzproperty2 ="",jztype2 ="";
		
		 //获取条数、折算后用电量和
		 int count = 0;
		 double blhdlcount = 0;
		 //int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();			
			for(int k = fycount;k<fylist.size()-1;k+=fycount){
			//导出条数
			count = count+1;

		     //num为序号，不同页中序号是连续的
		     
		    ammeterdegreeid = (String)fylist.get(k+fylist.indexOf("AMMETERDEGREEID"));
		    electriccurrenttype_ammeter = (String)fylist.get(k+fylist.indexOf("ELECTRICCURRENTTYPE_AMMETER"));
		    electriccurrenttype_ammeter = electriccurrenttype_ammeter != null ? electriccurrenttype_ammeter : "";
		    usageofelectypeid_ammeter = (String)fylist.get(k+fylist.indexOf("USAGEOFELECTYPEID_AMMETER"));
		    usageofelectypeid_ammeter = usageofelectypeid_ammeter != null ? usageofelectypeid_ammeter : "";
			ammeterid = (String)fylist.get(k+fylist.indexOf("AMMETERID_FK"));
			ammeterid = ammeterid != null ? ammeterid : "";
			zdmc = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    lastdegree = (String)fylist.get(k+fylist.indexOf("LASTDEGREE"));
		    lastdegree = lastdegree != null ? lastdegree : "";
		    thisdegree = (String)fylist.get(k+fylist.indexOf("THISDEGREE"));
		    thisdegree = thisdegree != null ? thisdegree : "";
		    lastdatetime = (String)fylist.get(k+fylist.indexOf("LASTDATETIME"));
		    if(lastdatetime=="0"||lastdatetime.equals("0")){
		    lastdatetime="";
		    }else{
		    lastdatetime = lastdatetime != null ? lastdatetime : "";
			}	

		    
			thisdatetime = (String)fylist.get(k+fylist.indexOf("THISDATETIME"));
			if(thisdatetime=="0"||thisdatetime.equals("0")){
		    thisdatetime="";
		    }else{
			thisdatetime = thisdatetime != null ? thisdatetime : "";
			}	
						
			floatdegree = (String)fylist.get(k+fylist.indexOf("FLOATDEGREE"));
			floatdegree = floatdegree != null ? floatdegree : "";
		    actualdegree = (String)fylist.get(k+fylist.indexOf("BLHDL"));
		    actualdegree = actualdegree != null ? actualdegree : "";
		    autoauditstatus = (String)fylist.get(k+fylist.indexOf("AUTOAUDITSTATUS"));
		    autoauditstatus = autoauditstatus != null ? autoauditstatus : "";
		    autoauditdescription = (String)fylist.get(k+fylist.indexOf("AUTOAUDITDESCRIPTION"));
		    autoauditdescription = autoauditdescription != null ? autoauditdescription : "";
		    manualauditstatus = (String)fylist.get(k+fylist.indexOf("MANUALAUDITSTATUS"));
		    manualauditstatus = manualauditstatus != null ? manualauditstatus : "";
            //System.out.println("AmmeterDegreeBean-whereStr:"+autoauditdescription);
            
            //添加站点类型、站点属性、集团报表类型
			stationtype2 = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
			stationtype2 = stationtype2 != null ? stationtype2 : "";
			jzproperty2 = (String)fylist.get(k+fylist.indexOf("PROPERTY"));
			jzproperty2 = jzproperty2 != null ? jzproperty2 : "";
			jztype2 = (String)fylist.get(k+fylist.indexOf("JZTYPE"));
			jztype2 = jztype2 != null ? jztype2 : "";             
            
            DecimalFormat la= new DecimalFormat("0.0");
            if(lastdegree==null||lastdegree.equals("")||lastdegree.equals("null")) lastdegree="0";
            lastdegree = la.format(Double.parseDouble(lastdegree));
            
            DecimalFormat th= new DecimalFormat("0.0");
            if(thisdegree==null||thisdegree.equals("")||thisdegree.equals("null")) thisdegree="0";
            thisdegree = th.format(Double.parseDouble(thisdegree));
            
            DecimalFormat f= new DecimalFormat("0.0");
            if(floatdegree==null||floatdegree.equals("")||floatdegree.equals("null")) floatdegree="0";
            floatdegree = f.format(Double.parseDouble(floatdegree));
            
            DecimalFormat a= new DecimalFormat("0.0");
            if(actualdegree==null||actualdegree.equals("")||actualdegree.equals("null")) actualdegree="0";
            actualdegree = a.format(Double.parseDouble(actualdegree));
			blhdlcount = blhdlcount+Double.parseDouble(actualdegree);	

       %>
 <tr height=19 style='height:14.25pt'>
 <%if(manualauditstatus!=null&&manualauditstatus.equals("1")){ %> 
 
          <%}else{ %>
   <%if(autoauditstatus!=null&&autoauditstatus.equals("1")){ %>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdmc%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=lastdegree%></td>

  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=lastdatetime%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=thisdegree%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=thisdatetime%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=floatdegree%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=actualdegree%></td>
  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=stationtype2%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzproperty2%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jztype2%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=electriccurrenttype_ammeter%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=usageofelectypeid_ammeter%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=ammeterid%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>审核通过</td>
  <%}else{ %>

  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdmc%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=lastdegree%></td>

  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=lastdatetime%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=thisdegree%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=thisdatetime%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=floatdegree%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=actualdegree%></td>
  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=stationtype2%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzproperty2%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jztype2%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=electriccurrenttype_ammeter%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=usageofelectypeid_ammeter%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=ammeterid%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>修改</td>
  </tr>
       		<%} %>
        <%} %>
       <%} %>
      <%} %>
  <tr> 
  	<td colspan="14">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=count%>条；</font></b>

							<font color='#000080'>&nbsp;折算后用电量和:</font>
							 <b><font color=red><%=(new DecimalFormat("0.0")).format(blhdlcount)%>度</font></b>
	</div>
  </tr>
 
 <![endif]>
</table>
</body>

</html>
