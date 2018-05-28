<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="java.sql.ResultSet"%>
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
 <col width=72 style='width:54pt'>
 <tr class=xl24 height=19 style='height:14.25pt'>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点名称</td>  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>上次抄表时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>本次抄表时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>实际缴费金额</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>单价（元/天）</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>电费暂估金额</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>所在地区</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点类型</td>
  <td class=xl25 width=165 style='border-left:none;width:124pt'>电表名称</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>站点编号</td>
  <td class=xl25 width=165 style='border-left:none;width:124pt'>电表ID</td>
 </tr>
 <%   
         int curpage = Integer.parseInt(request.getParameter("curpage"));  
         String whereStr = request.getParameter("whereStr");
         String zdbh = request.getParameter("zdbh");
         String dbid = request.getParameter("dbid");
         String	lastdatetime1=request.getParameter("lastdatetime1");
		 String thisdatetime1=request.getParameter("thisdatetime1");
         	if(zdbh != null && zdbh != "" && !zdbh.equals("0")){
				whereStr=whereStr+" and jzname like '%"+zdbh+"%'";
			}
			if(dbid != null && dbid != "" && !dbid.equals("0")){
				whereStr=whereStr+" and ad.ammeterid_fk like '%"+dbid+"%'";
			}
         
         if(whereStr==null)whereStr="";         
		 System.out.println("AmmeterDegreeBean-whereStr:"+whereStr);
         ArrayList fylist = new ArrayList();
        if(whereStr==null)whereStr="";
       	 fylist = bean.getPageDatazangu(whereStr,loginId);
       	 //allpage=bean.getAllPage();
			String ammeterid_fk = "",lastdatetime = "", thisdatetime = "",actualpay = "",danjia = "",dianfeizangu = "",stationtype2="";
			String jzname = "",jzcode = "",heji="",dbname="",szdq="";
			
		//获取条数、实际缴费金额总和、电费暂估金额总和
		 int count = 0;
		 double actualpaycount = 0;
		 
		//int intnum=xh = pagesize*(curpage-1)+1;
		double zanguheji = 0;
		 if(fylist.size()!=0)
		{
				int fycount = ((Integer)fylist.get(0)).intValue();
				for( int k = fycount;k<fylist.size()-1;k+=fycount){
				
				count = count+1;
				
			     //num为序号，不同页中序号是连续的
			    szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
			    szdq = szdq != null ? szdq : "";
			     
			    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
			    jzname = jzname != null ? jzname : "";
			    
			    //添加站点类型
			    stationtype2 = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
			    stationtype2 = stationtype2 != null ? stationtype2 : "";
			    	
			    jzcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));	
			    jzcode = jzcode != null ? jzcode : "";
			     
			    ammeterid_fk = (String)fylist.get(k+fylist.indexOf("AMMETERID_FK"));	
			    ammeterid_fk = ammeterid_fk != null ? ammeterid_fk : "";
			    
			    dbname = (String)fylist.get(k+fylist.indexOf("DBNAME"));
			    dbname = dbname != null ? dbname : "";
			    
				lastdatetime = (String)fylist.get(k+fylist.indexOf("LASTDATETIMEA"));
				lastdatetime = lastdatetime != null ? lastdatetime : "";
				
			    thisdatetime = (String)fylist.get(k+fylist.indexOf("THISDATETIMEA"));
			    thisdatetime = thisdatetime != null ? thisdatetime : "";
			    			    			    		    
			    actualpay = (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));
			    actualpay = actualpay != null ? actualpay : "";
			    if(actualpay==null||actualpay.equals("")||actualpay.equals("null")){
					actualpay="0.0";
				}
			    DecimalFormat s2=new DecimalFormat("0.00");
			    actualpay=s2.format(Double.parseDouble(actualpay));
			    actualpaycount = actualpaycount+Double.parseDouble(actualpay);
			    
			   if(dianfeizangu==null||dianfeizangu.equals("")||dianfeizangu.equals("null")) dianfeizangu="0";
			    if(actualpay==null||actualpay.equals("")||actualpay.equals("null")) actualpay="0";
			    if(danjia==null||danjia.equals("")||danjia.equals("null")) danjia="0";
			    DecimalFormat mat=new DecimalFormat("0.00");
			    
  			   if(lastdatetime!=null&&!lastdatetime.equals("0")&&!lastdatetime.equals("")&&thisdatetime!=null&&!thisdatetime.equals("0")&&!thisdatetime.equals("")){
			   Calendar lastTime1 = Calendar.getInstance();//获取Calendar对象
			    lastTime1.setTime(DateFormat.getDateInstance().parse(lastdatetime1));//  parse（）从给定字符串的开始解析文本，以生成一个日期
			    Calendar thisTime1 = Calendar.getInstance();
			    thisTime1.setTime(DateFormat.getDateInstance().parse(thisdatetime1));
			    Long temp1 = thisTime1.getTimeInMillis()-lastTime1.getTimeInMillis();
			    Double day1 = Math.ceil(temp1/1000/60/60/24.0);
			    day1=day1+1.0;			    
			    Calendar lastTime = Calendar.getInstance();
			    lastTime.setTime(DateFormat.getDateInstance().parse(lastdatetime));
			    Calendar thisTime = Calendar.getInstance();
			    thisTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
			    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
			    Double days = Math.ceil(temp/1000/60/60/24.0);
			    days=days+1.0;
			   // if(heji==null||heji.equals("")) heji="0";
			    Double danjia1 = Double.parseDouble(actualpay)/days;
			   
			    DecimalFormat dj=new DecimalFormat("0.0000");
			    
			    danjia=dj.format(danjia1);
			    
			    actualpay=mat.format(Double.parseDouble(actualpay));
				dianfeizangu=mat.format(Double.parseDouble(danjia)*day1);
				dianfeizangu = dianfeizangu != null ? dianfeizangu : ""; 
			}
			if(lastdatetime.equals("0")){
				lastdatetime="";
			}
			if(thisdatetime.equals("0")){
				thisdatetime="";
			}
				zanguheji += Double.parseDouble(dianfeizangu);
				
				heji=mat.format(zanguheji);	  

       %>
 <tr height=19 style='height:14.25pt'>
 
  
  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>  
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=lastdatetime%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=thisdatetime%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=actualpay%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=danjia%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dianfeizangu%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=szdq%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=stationtype2%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=dbname%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzcode%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=ammeterid_fk%></td>
 </tr>

 
         <%
         }}
         %>
  <tr> 
  	<td colspan="11">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=count%>条；</font></b>

							<font color='#000080'>&nbsp;实际缴费金额总和:</font>
							 <b><font color=red>&nbsp;<%=(new DecimalFormat("0.00")).format(actualpaycount)%>元；</font></b>
							 <font color='#000080'>&nbsp;电费暂估金额总和:</font>
							 <b><font color=red>&nbsp;<%=heji%>元</font></b>
	</div>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
