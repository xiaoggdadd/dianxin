<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
<%      Account account = new Account();
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
<jsp:useBean id="bean" scope="page" class="com.noki.query.basequery.javabean.AmmeterDegreeQueryBean">
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
  <td class=xl25 width=152 style='border-left:none;width:114pt'>电表名称</td>
   <td class=xl25 width=165 style='border-left:none;width:124pt'>上次电表读数</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>上次抄表时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>本次电表读数</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>本次抄表时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>用电量调整</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>实际用电量</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>人工审核状态</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点类型</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>区县</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>小区</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>电流类型</td>
  <td class=xl25 width=174 style='border-left:none;width:131pt'>用电设备</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>电表用途</td>
  <td class=xl25 width=174 style='border-left:none;width:131pt'>额定耗电量</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>有无空调</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>直流负荷</td>
  
  <td class=xl25 width=174 style='border-left:none;width:131pt'>录入时间</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>录入人员</td>
  <td class=xl25 width=174 style='border-left:none;width:131pt'>起始年月</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>结束年月</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>站点ID</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>电表ID</td>
 </tr>
 <%   
         int curpage = Integer.parseInt(request.getParameter("curpage"));  
         String whereStr = request.getParameter("whereStr");
         String zdname = request.getParameter("zdname");
         String ammeterid1 = request.getParameter("ammeterid1");
         if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
				whereStr=whereStr+" AND ZD.JZNAME LIKE '%"+zdname+"%'";
		  }
		  if(ammeterid1 != null && ammeterid1 != "" && !ammeterid1.equals("0")){
				whereStr=whereStr+" AND AMMETERID_FK LIKE '%"+ammeterid1+"%'";
		}

         if(whereStr==null) whereStr="";
            List<ElectricFeesFormBean> fylist = bean.getPageData(curpage,whereStr,loginId);
		 String jzname="",electriccurrenttype_ammeter = "",shenhe="",usageofelectypeid_ammeter = "",ammeterdegreeid = "",ammeterid = "",lastdegree = "", thisdegree = "",lastdatetime = "",thisdatetime = "",floatdegree = "",actualdegree = "",dbname="",xian2="",xiaoqu2="",zdid="",dbyt1="";
		String zdlx="",kongt1="",edhdl1="",zlfh="";
		String entrytime="",entrypensonnel="",startmonth="",endmonth="";
	     //获取条数、实际用电量总和
		 int count = 0;
		 double actualdegreecount = 0;
		 //int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null){
			for(ElectricFeesFormBean bean1:fylist){

				//导出条数
				count = count+1;
		     //num为序号，不同页中序号是连续的
		     
		    ammeterdegreeid = bean1.getAmmeterdegreeid();
		    electriccurrenttype_ammeter = bean1.getDllx();
		    electriccurrenttype_ammeter = electriccurrenttype_ammeter != null ? electriccurrenttype_ammeter : "";
		    
		   //添加站点id
		    zdid = bean1.getZdcode();
		    zdid = zdid != null ? zdid : "";
		    //站点类型
		    zdlx = bean1.getStationtype();
		    zdlx=zdlx!=null?zdlx:"";
		    //添加电表用途
		    dbyt1 = bean1.getDbyt();
		    dbyt1 = dbyt1 != null ? dbyt1 : "";
		   	//添加直流负荷
		    zlfh = bean1.getZlfh();
		    zlfh = zlfh !=null ? zlfh : "";
		    
		   	//添加空调
		    kongt1 = bean1.getKongtiao();
		    kongt1=kongt1 !=null ? kongt1 : "";
		    //添加额定功耗
		    edhdl1 = bean1.getEdhdl();
		    edhdl1 = edhdl1 != null ? edhdl1 : "";
		    
		    usageofelectypeid_ammeter = bean1.getYdsb();
		    usageofelectypeid_ammeter = usageofelectypeid_ammeter != null ? usageofelectypeid_ammeter : "";
		    
			ammeterid = bean1.getAmmererid();
			ammeterid = ammeterid != null ? ammeterid : "";
			
			dbname = bean1.getDbname();
			dbname = dbname != null ? dbname : "";
			
			/*szdq = bean1.getSzdq();
			szdq = szdq != null ? szdq : "";*/
			
			xian2 = bean1.getXian();
			xian2 = xian2 != null?xian2:"";
			
			xiaoqu2 = bean1.getXiaoqu();
			xiaoqu2 = xiaoqu2!=null?xiaoqu2:"";
			
		    lastdegree = bean1.getLastdegree();
		    lastdegree = lastdegree != null ? lastdegree : "0.0";
		    if(lastdegree==null||lastdegree.equals("")||lastdegree.equals(" ")||lastdegree.equals("null")||lastdegree.equals("o")){
		         lastdegree="0.0";
		    }
		     DecimalFormat s=new DecimalFormat("0.0");
		    lastdegree=s.format(Double.parseDouble(lastdegree));    
		    
		    thisdegree = bean1.getThisdegree();
		    thisdegree = thisdegree != null ? thisdegree : "0.0";
		    if(thisdegree==null||thisdegree.equals("")||thisdegree.equals(" ")||thisdegree.equals("null")||thisdegree.equals("o")){
		         thisdegree="0.0";
		    }
		     DecimalFormat srr=new DecimalFormat("0.0");
		    thisdegree=srr.format(Double.parseDouble(thisdegree));		    
		    
		    lastdatetime = bean1.getLastdatetime();
		    if(lastdatetime=="0"||lastdatetime.equals("0")){
		    lastdatetime = "";
		    }else{
		    lastdatetime = lastdatetime != null ? lastdatetime : "";
		    }
		    
			thisdatetime = bean1.getThisdatetime();
		    thisdatetime = thisdatetime != null ? thisdatetime : "";
						
			floatdegree = bean1.getFloatdegree();
			floatdegree = floatdegree != null ? floatdegree : "0.0";
			if(floatdegree==null||"".equals(floatdegree)||" ".equals(floatdegree)||floatdegree.equals("null")||floatdegree.equals("o")){
		             floatdegree="0.0";
		    }
		    DecimalFormat sro=new DecimalFormat("0.0");
		    floatdegree=sro.format(Double.parseDouble(floatdegree));
			
			
		    actualdegree = bean1.getActualdegree();
		    actualdegree = actualdegree != null ? actualdegree : "0.0";
		    if(actualdegree==null||actualdegree.equals("")||actualdegree.equals(" ")||actualdegree.equals("null")||actualdegree.equals("o")){
		       actualdegree="0.0";
		    } 
		    DecimalFormat stt=new DecimalFormat("0.0");
		    actualdegree=srr.format(Double.parseDouble(actualdegree));
		    actualdegreecount = actualdegreecount+Double.parseDouble(actualdegree);
		    
		    
		    jzname = bean1.getJzname();
		    jzname = jzname != null ? jzname : "";
		    
		    
		    
		    shenhe = bean1.getManualauditstatus();
		    shenhe = shenhe != null ? shenhe : "";
            if(shenhe.equals("1")){
                 shenhe="通过";
            }else if(shenhe.equals("0")||shenhe==""||shenhe=="null"||"null".equals(shenhe)||"".equals(shenhe)||shenhe.equals(" ")||shenhe==null){
            	shenhe="不通过";
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
			/*String color=null;
			if(intnum%2==0){
			    color="F2F9FF";

			 }else{
			    color="#FFFFFF" ;
			}*/

       %>
 <tr height=19 style='height:14.25pt'> 
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dbname%></td>
    <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=lastdegree%></td>

  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=lastdatetime%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=thisdegree%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=thisdatetime%></td>

  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=floatdegree%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=actualdegree%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=shenhe%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdlx%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xian2%></td>
  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xiaoqu2%></td>
  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=electriccurrenttype_ammeter%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=usageofelectypeid_ammeter%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dbyt1%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=edhdl1%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=kongt1%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zlfh%></td>
  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=entrytime%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=entrypensonnel%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=startmonth%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=endmonth%></td>
  
   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdid%></td>
   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=ammeterid%></td>

 </tr>

 
         <%
         }}
         %>

  <tr> 
  	<td colspan="23">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=count%>条；</font></b>

							<font color='#000080'>&nbsp;实际用电量总和:</font>
							 <b><font color=red>&nbsp;<%=(new DecimalFormat("0.00")).format(actualdegreecount)%>度</font></b>
	</div>
  </tr>
 
 
 <![endif]>
</table>

</body>

</html>
