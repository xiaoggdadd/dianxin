<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
<%@ page import =" java.text.SimpleDateFormat"%>

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
<jsp:useBean id="bean" scope="page" class="com.noki.query.basequery.javabean.AmmeterQueryBean">
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
  <td class=xl25 width=152 style='border-left:none;width:114pt'>电表名称</td>  
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点名称</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点类型</td>
  <td class=xl25 width=174 style='border-left:none;width:131pt'>区县</td>
  <td class=xl25 width=174 style='border-left:none;width:131pt'>小区</td>
  
  <td class=xl25 width=165 style='border-left:none;width:124pt'>初始读数</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>初始使用时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>倍率</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>单价</td>
  <td class=xl25 width=174 style='border-left:none;width:131pt'>电表型号</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>电费支付类型</td>
 
  <td class=xl25 width=165 style='border-left:none;width:124pt'>所属专业</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>电表用途</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>电流类型</td>
  <td class=xl25 width=174 style='border-left:none;width:131pt'>用电设备</td>
  
  <td class=xl25 width=152 style='border-left:none;width:114pt'>线损类型</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>线损值</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>录入时间</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>录入人员</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>电表ID</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点ID</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点启用状态</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>电表启用状态</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>自报电用户号</td>
 </tr>
 
  <%   
         int curpage = Integer.parseInt(request.getParameter("curpage"));  
          String whereStr=request.getParameter("whereStr");
          String zdname= request.getParameter("zdname");
          String dbname = request.getParameter("dbname");
          if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
			 whereStr=whereStr+" AND JZ.JZNAME LIKE '%"+zdname+"%'";
			} 
		 if(dbname != null && dbname != "" && !dbname.equals("0")){
				whereStr=whereStr+" AND DBNAME LIKE '%"+dbname+"%'";
			}      
         //判断是否为空
         if(whereStr==null){
         whereStr ="";
         }

       	 List<ElectricFeesFormBean> fylist = bean.getPageDatapp(whereStr,loginId);
       	 
       	 //allpage=bean.getAllPage();
		String id = "";
		//添加站点ID
		String zdid ="";
		String shengl = "";
		String shil = "";
		String xianl = "";
		//添加乡镇
		String xiaoqu1 = "";
		String jzname = "";
		String professionaltypeid = "";
		String ammeteruse = "";
		String electriccurrenttype_ammeter = "";
		String usageofelectypeid_ammeter = "";
		String initialdegree = "";
		String initialdate = "";
		String multiplyingpower = "";
		String danjia = "";
		String ammetertype = "";
		String dbname1 = "";
		//String area = "";
		String xian2="";
		String xiaoqu2="";
		//电费支付类型
		String paylx = "";
		//线损类型
		String xslx = "";
		//线损值
		String xsz = "";
		//录入时间
		String lrtime = "";
		//录入人员
		String lrperson = "";
		//站点类型，站点启用状态
		String zdlx="",zdqyzt1="";
		String dbqyzt1 = "",zbdyh="";
		
	   //获取条数、折算后用电量和
		 int count = 0;
		//int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			for(ElectricFeesFormBean bean1:fylist){

			//导出条数
			count = count+1;
		     //num为序号，不同页中序号是连续的
		     
		    id = bean1.getDbid();
			
		    zdid = bean1.getZdcode();
		    zdid = zdid != null ? zdid : "";
			
		    jzname = bean1.getJzname();
		    jzname = jzname != null ? jzname : "";
			
		   /* area = bean1.getSzdq();
			area = area != null ? area : "";*/
			
			xian2 = bean1.getXian();
			xian2 = xian2 != null ? xian2 : "";
			
			xiaoqu2 = bean1.getXiaoqu();
			xiaoqu2 = xiaoqu2 != null ? xiaoqu2 : "";
		    
		    professionaltypeid = bean1.getProfessionaltypeid();
		    professionaltypeid = professionaltypeid != null ? professionaltypeid : "";
		    
		    ammeteruse = bean1.getDbyt();
		    ammeteruse = ammeteruse != null ? ammeteruse : "";
		    
			electriccurrenttype_ammeter = bean1.getDllx();
			electriccurrenttype_ammeter = electriccurrenttype_ammeter != null ? electriccurrenttype_ammeter : "";
			if(electriccurrenttype_ammeter.equals("null")){
				electriccurrenttype_ammeter="";
			}
						
			usageofelectypeid_ammeter = bean1.getYdsb();
			usageofelectypeid_ammeter = usageofelectypeid_ammeter != null ? usageofelectypeid_ammeter : "";
			
			
			
			initialdegree = bean1.getCsds();
			if(initialdegree==null||initialdegree.equals("")||initialdegree.equals("null")||initialdegree.equals("o")){
			initialdegree="0.0";
			}
			DecimalFormat ste=new DecimalFormat("0.0");
			initialdegree=ste.format(Double.parseDouble(initialdegree));
			
			
			
			initialdate = bean1.getCssytime();
			initialdate = initialdate != null ? initialdate : "";
			if(initialdate=="0"||initialdate.equals("0")||initialdate.equals("null")){
		      initialdate="";
		    }		

			multiplyingpower = bean1.getBeilv();
			multiplyingpower = multiplyingpower != null ? multiplyingpower : "0";
			if(multiplyingpower==null||multiplyingpower.equals("")||multiplyingpower.equals("null")||multiplyingpower.equals("o")){
			multiplyingpower="0";
			}
			DecimalFormat s=new DecimalFormat("0.00");
			multiplyingpower=s.format(Double.parseDouble(multiplyingpower));
			
			danjia = bean1.getUnitprice();
			danjia = danjia != null ? danjia : "0.0000";
			if(danjia==null||danjia.equals("")||danjia.equals("null")){
				danjia="0.0000";
			}
			DecimalFormat dj = new DecimalFormat("0.0000");
			danjia = dj.format(Double.parseDouble(danjia));
			
			ammetertype = bean1.getDbxh();
			ammetertype = ammetertype != null ? ammetertype : "";
			
			dbname1 = bean1.getDbname();
			dbname1 = dbname1 != null ? dbname1 : "";
			
			zdlx = bean1.getStationtype();
			zdlx=zdlx!=null?zdlx:"";
			
			//电费支付类型	
			paylx = bean1.getDfzflx();
			paylx = paylx != null ? paylx : "";
			if(paylx.equals("null")){
			paylx="";
			}
			
		    //线损类型
		    xslx = bean1.getLinelosstype();
			xslx = xslx != null ? xslx : "";
			if(xslx.equals("null")){
			xslx="";
			}
		    //线损值
		    xsz = bean1.getLinelossvalue();
			xsz = xsz != null ? xsz : "";
			if(xsz.equals("null")){
			xsz="";
			}
		    //录入时间
		    lrtime = bean1.getEntrytime();
			lrtime = lrtime != null ? lrtime : "";
			if(lrtime=="0"||lrtime.equals("0")||lrtime.equals("null")){
		      lrtime="";
		    }
		    //录入人员
		    lrperson = bean1.getEntrypensonnel();
			lrperson = lrperson != null ? lrperson : "";
			if(lrperson.equals("null")){
			lrperson="";
			}
			zdqyzt1=bean1.getZdqyzt();
			if(zdqyzt1.equals("1")){
				zdqyzt1="启用";
			}else if(zdqyzt1.equals("0")){
				zdqyzt1="不启用";
			}else {
				zdqyzt1="";
			}
			
			dbqyzt1 = bean1.getDbqyzt();
			if(dbqyzt1.equals("1")){
				dbqyzt1="启用";
			}else if(dbqyzt1.equals("0")){
				dbqyzt1="不启用";
			}else {
				dbqyzt1="";
			}
			
			zbdyh = bean1.getDbzbdyhh();
			zbdyh = zbdyh != null ? zbdyh : "";
			
		   // area = shengl + " " + shil + " " + xianl;
			String color=null;
            String ss="aaa";
       %>
  <tr height=19 style='height:14.25pt'>  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dbname1%></td>  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdlx%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xian2%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xiaoqu2%></td>
  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=initialdegree%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=initialdate%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=multiplyingpower%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none'><%=danjia%></td> 
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=ammetertype%></td>  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=paylx%></td>
  
  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=professionaltypeid%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=ammeteruse%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=electriccurrenttype_ammeter%></td>
  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=usageofelectypeid_ammeter%></td> 
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xslx%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xsz%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=lrtime%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=lrperson%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=id%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdid%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdqyzt1%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' ><%=dbqyzt1%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none'><%=zbdyh%></td>
 </tr>
         <%
         }}
         %>
  <tr> 
  	<td colspan="19">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							<b><font color=red><%=count%>条</font></b>
	</div>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
