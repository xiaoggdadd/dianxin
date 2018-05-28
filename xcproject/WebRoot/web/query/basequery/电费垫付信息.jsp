<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.List,com.noki.util.CTime,java.util.ArrayList,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date"%>
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
int a =0;
String c="";
SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd");
String entrytime=mat.format(new Date());
String yue=entrytime.substring(5,7);
String sj=request.getParameter("entrytime");
if(sj==null||"".equals(sj)){
	   	a=Integer.parseInt(yue);
	}else if(sj!=null){
		c=sj.substring(5,7);
		a=Integer.parseInt(c);
	}
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
<jsp:useBean id="bean" scope="page" class="com.noki.jizhan.SiteManage">
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
 <td class=xl25 width=152 style='border-left:none;width:114pt'>地市</td>
 <td class=xl25 width=152 style='border-left:none;width:114pt'><%=a-1%>月底前垫付未收回资金</td>
 <td class=xl25 width=174 style='border-left:none;width:131pt'><%=a-1%>月底前垫付未收回资金:<%=a%>月已回款</td>
 <td class=xl25 width=174 style='border-left:none;width:131pt'>待回收资金</td>
 <td class=xl25 width=165 style='border-left:none;width:124pt'>待回收资金:已和运营商完成明细核对</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>待回收资金:已开票</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>已核对占比</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>已开票占比</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>运营商</td>
 
 </tr>
 <%   
        
         String whereStr = request.getParameter("whereStr");	  
         String zdname=request.getParameter("zdname");
		
         if(whereStr==null){
         whereStr="";
         }
          ArrayList fylist = new ArrayList();
           fylist =bean.getPageDatadfdc(loginName,loginId,whereStr);
       	 //List<ElectricFeesFormBean> fylist = bean.getPageDatap(whereStr,loginId);
       	 //allpage=bean.getAllPage();
		String id = "";
		String zlfh="",jlfh="",sdbdl="";
		String shengl = "";
		String shil = "",bieming="";
		String xianl = "";
		String jzname = "";//站点名称
		String jzcode = "";//站点代码
		String sydate = "";
		String erpcode = "";
		String fzr = "";//负责人
		String area = "";//所在地区
		String dianfei = "";
		String mianji = "";//占地面积
		String	property="";//站点属性
		String jztype="";//集团报表类型
		String zdl="";//站点类型
		String jnglmk="";//节能设备
		String yflx="";//用房类型
		String gdfs="";//供电方式
		String xiangzhen="";
		
		/**
	        12-08-13修改
		站点类型(STATIONTYPE)，归属方(GSF)，
	          是否标杆(BGSIGN)，直流负荷(ZLFH)，
		额定耗电量(EDHDL)，有无空调(KONGTIAO)，
	           录入人员(ENTRYPENSONNEL)，录入时间(ENTRYTIME)*/
	           
		String gsf1="",isbg="",zl="",edhd="",kt="",lrry="",lrsj="",scb="";
		
		String rgshzt="",rgshsj="",rgshry="";
	
		String ysjts="",wjts="",yybgkt="",jfsckt="",rru="",ydgxsbsl="",dxgxsbsl="",kts="",ktzgl="",kt1="",kt2="",zdqyzt1="";
			 DecimalFormat df1 = new DecimalFormat("0.00");
		//获取条数
		 int count = 0;
		 String dfwhszj,dfwhszj_byhs,dhszj,dhszj_yyshd,dhszj_ykp,dfhs_yysa,dfhs_hdzb,dfhs_kpzb
,entrytimea,updatetime,shia,entryperson,updateperson;
		//int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
		int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

			//导出条数
			count = count+1;
		     //num为序号，不同页中序号是连续的		     
		id = (String) fylist.get(k + fylist.indexOf("ID"));
				dfwhszj = (String) fylist.get(k + fylist.indexOf("DFWHSZJ"));
				dfwhszj=df1.format(Double.parseDouble(dfwhszj));
				dfwhszj_byhs = (String) fylist.get(k + fylist.indexOf("DFWHSZJ_BYHS"));
				dfwhszj_byhs=df1.format(Double.parseDouble(dfwhszj_byhs));
				dhszj = (String) fylist.get(k + fylist.indexOf("DHSZJ"));
				dhszj=df1.format(Double.parseDouble(dhszj));
				dhszj_yyshd = (String) fylist.get(k + fylist.indexOf("DHSZJ_YYSHD"));
				dhszj_yyshd=df1.format(Double.parseDouble(dhszj_yyshd));
				dhszj_ykp = (String) fylist.get(k + fylist.indexOf("DHSZJ_YKP"));
				dhszj_ykp=df1.format(Double.parseDouble(dhszj_ykp));
				dfhs_yysa = (String) fylist.get(k + fylist.indexOf("DFHS_YYS"));
       			
				dfhs_hdzb=(String) fylist.get(k+fylist.indexOf("DFHS_HDZB"));
				dfhs_hdzb=df1.format(Double.parseDouble(dfhs_hdzb));
				dfhs_kpzb = (String) fylist.get(k + fylist.indexOf("DFHS_KPZB"));
				dfhs_kpzb=df1.format(Double.parseDouble(dfhs_kpzb));
				shia = (String) fylist.get(k+ fylist.indexOf("SHI"));
		  

			String color=null;

       %>
 <tr height=19 style='height:14.25pt'>   
	  <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=shia%></td>
	   <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=dfwhszj%></td>
	  <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=dfwhszj_byhs%></td>
	  <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=dhszj%></td>
	  <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=dhszj_yyshd%></td>
	  <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=dhszj_ykp%></td>
     <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=dfhs_hdzb%></td>
	 <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=dfhs_kpzb%></td>
	 <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=dfhs_yysa%></td>
 
 </tr>
         <%
         }}
         %>
  <tr> 
  	<td colspan="9">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=count%>条</font></b>
	</div>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
