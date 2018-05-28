<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="com.noki.newfunction.dao.*" %>
<%@ page import="com.noki.newfunction.javabean.*" %>
<%@ page import="java.text.*,com.noki.util.CTime"%>
<%@ page import="java.util.regex.Pattern"%>
<%

      /*  Account account = new Account();
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
        */

String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    //String roleId = account.getRoleId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"0";
System.out.println("站点名称:"+zdname);


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
	  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点编号</td>
	  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点名称</td>
	    <td class=xl25 width=152 style='border-left:none;width:114pt'>所属区域</td>
	    <td class=xl25 width=152 style='border-left:none;width:114pt'>超标月份</td>
	  <td class=xl25 width=152 style='border-left:none;width:114pt'>超标比例</td>
	  <td class=xl25 width=152 style='border-left:none;width:114pt'>分析原因</td>
	    <td class=xl25 width=152 style='border-left:none;width:114pt'>测试人</td>
	    <td class=xl25 width=152 style='border-left:none;width:114pt'>整改意见</td>
	    <td class=xl25 width=152 style='border-left:none;width:114pt'>完成时间</td>
	  <td class=xl25 width=72 style='border-left:none;width:54pt'>状态</td>
 </tr>
 <%   
 String shi=request.getParameter("shi");
	String xian=request.getParameter("xian");
	String beginTime1=request.getParameter("beginTime1");
	String whereStr = "";
 Zdinfo zd = new Zdinfo();
		if(shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr=whereStr+" AND zd.shi='"+shi+"'";
		}
		if(xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr=whereStr+" AND zd.xian='"+xian+"'";
		}	
		if(beginTime1 != null && beginTime1 != "" && !beginTime1.equals("0")){
			whereStr=whereStr+" AND cc.cbsj='"+beginTime1+"'";
		}	
		if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
				whereStr=whereStr+" AND ZD.JZNAME LIKE '%"+zdname+"%'";				
			}

List<Zdinfo> fylist=null;
System.out.println("iiiiiiiiiiiiiiiiiii"+shi+"---"+xian+"222"+beginTime1+"---"+request.getParameter("command"));
if("daochu".equals(request.getParameter("command"))){
ZGShenHeDao dao2 = new ZGShenHeDao();
System.out.println("11234511");
fylist = dao2.getZdinfo(whereStr);
	System.out.println(fylist);

} else{
fylist=null;
}
int  countt=0;

int countxh=1;
String jzid="",al="";//站点id
String jzname="";//站点名称
String manualauditstatus="";
String quxian="";//区县
String xiaoq="";//小区
String cbyf="";//超标月份
String bili="";//超标比例
//String csms="";//测试描述
String fxyy="";//分析原因
String zgyj="";//整改意见
String wcsj="";//完成时间
String tjzt="";//提交状态
String cszrr="";//测试人
String id="";//cbzd表主键id
String cid="";//cbzdxx 主键id
String sjshbz="";//市级审核标志
if(fylist!=null){
for(Zdinfo z:fylist){
  jzid=z.getZdid();
  jzname=z.getZdname();
  quxian=z.getXian();
  xiaoq=z.getXiaoqu();
  cbyf=z.getCbsj();
  al=z.getBzpld();
  //csms=z.getCsms();
  fxyy=z.getYyfx();
  zgyj=z.getXfzgyq();
  wcsj=z.getWcsj();
  tjzt=z.getQxtjsh();
  sjshbz=z.getSjshbz();
 // System.out.println("sssss"+tjzt);
  if("1".equals(tjzt)){
 	 
 	 tjzt="已提交";
  }else{
 	 tjzt="未提交";
  }
  cszrr=z.getCszrr();
  id=z.getId();
  cid=z.getCid();
  System.out.println("sss"+id+"ddd"+cid);
  
  if(null==al||"".equals(al)){
 	 al="0";
  }
  
	 DecimalFormat mat=new DecimalFormat("0.00");
	al=mat.format(Double.parseDouble(al)*100)+"%";
	
	 if("null".equals(fxyy)||fxyy==null){
		 fxyy="";
	 }
	 if("null".equals(zgyj)||zgyj==null){
		 zgyj="";
	 }
	 if("null".equals(cszrr)||cszrr==null){
		 cszrr="";
	 }
	 if("null".equals(tjzt)||tjzt==null){
		 tjzt="0";
	 }
	 if("null".equals(wcsj)||wcsj==null){
		 wcsj="";
	 }
	 if(sjshbz==null||sjshbz.equals("")||sjshbz.equals("null")||sjshbz.equals(" ")||sjshbz==""){
		 sjshbz="0";
	 } 
             countt++;
			 String color=null;
       %> 
    
  <tr height=19 style='height:14.25pt'>
<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzid%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=quxian%>,<%=xiaoq %></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=cbyf%></td> 

  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=al%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=fxyy%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=cszrr%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zgyj%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=wcsj%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=tjzt%></td>
 
 </tr>
         <%
         }}
         %>
  <tr> 
  	<td colspan="10">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=countt%>条；</font></b>
						
	</div>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
