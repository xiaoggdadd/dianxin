<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="java.text.*,com.noki.util.CTime"%>
<%@ page import="java.util.regex.Pattern"%>
<%@ page import="com.noki.newfunction.dao.*" %>
<%@ page import="com.noki.newfunction.javabean.*" %>
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
	String zdnamea=request.getParameter("zdname")!=null?request.getParameter("zdname"):"0";


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
	  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点名称 </td>
	  <td class=xl25 width=152 style='border-left:none;width:114pt'>所在区域 </td>
	    <td class=xl25 width=152 style='border-left:none;width:52pt'>超标月份</td>
	    <td class=xl25 width=152 style='border-left:none;width:114pt'>超标比例 </td>
	    <td class=xl25 width=152 style='border-left:none;width:114pt'>省派单 </td>
	  <td class=xl25 width=72 style='border-left:none;width:114pt'>市签收</td>
	  <td class=xl25 width=72 style='border-left:none;width:114pt'>市派单</td>
	  <td class=xl25 width=72 style='border-left:none;width:114pt'>区县签收 </td>
	  <td class=xl25 width=72 style='border-left:none;width:114pt'>区县派单 </td>
	   <td class=xl25 width=72 style='border-left:none;width:114pt'>区县测试 </td>
      <td class=xl25 width=72 style='border-left:none;width:114pt'>市审核 </td>
      <td class=xl25 width=72 style='border-left:none;width:114pt'>省审核 </td>
      <td class=xl25 width=72 style='border-left:none;width:114pt'>省下发要求</td>
      <td class=xl25 width=72 style='border-left:none;width:114pt'>地市下发</td>
      <td class=xl25 width=72 style='border-left:none;width:114pt'>整改完成状态 </td>
      <td class=xl25 width=165 style='border-left:none;width:114pt'>市审核</td>
      <td class=xl25 width=72 style='border-left:none;width:114pt'>省审核 </td>
 </tr>
 <%   
			 cbzddao bean=new cbzddao();
				
			 String whereStr = "";
			 String str="";
			String shi = request.getParameter("shi");
			String xian = request.getParameter("xian");
			String xiaoqu = request.getParameter("xiaoqu");
			String accountmonth = request.getParameter("accountmonth");
			String dgpch = request.getParameter("dgpch");
			String yppch = request.getParameter("yppch");
				if(shi != null && !shi.equals("") && !shi.equals("0")){
					whereStr=whereStr+" and z.shi='"+shi+"'";
					str=str+" and z.shi='"+shi+"'";
				}
				if(xian != null && !xian.equals("") && !xian.equals("0")){
					whereStr=whereStr+" and z.xian='"+xian+"'";
					str=str+" and z.xian='"+xian+"'";
				}
				if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
					whereStr=whereStr+" and z.xiaoqu='"+xiaoqu+"'";
					str=str+" and z.xiaoqu='"+xiaoqu+"'";
				}
			
				if(accountmonth != null && accountmonth != "" && !accountmonth.equals("0")){
					whereStr=whereStr+" AND c.CBSJ ='"+accountmonth+"'";
					str=str+" AND c.CBSJ ='"+accountmonth+"'";
				}
				
				if(dgpch != null && dgpch != "" && !dgpch.equals("")){
					whereStr=whereStr+" AND X.DGPCH like '%"+dgpch+"%'";
					str=str+" AND X.DGPCH ='"+dgpch+"'";
				}
				if(yppch != null && yppch != "" && !yppch.equals("")){
					whereStr=whereStr+" AND X.YPPCH  like '%"+yppch+"%'";
					str=str+" AND X.YPPCH ='"+yppch+"'";
				}
			 		if(zdnamea != null && !zdnamea.equals("")&& !zdnamea.equals("0")){
				whereStr=whereStr+" AND Z.JZNAME LIKE '%"+zdnamea+"%'";				
			}
 		List<Zdinfo> list=null;         
		if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
		       list	=bean.getchaxun(whereStr,loginId);
		}else{
			list=null;
		}
		String id="";
		String zdid = "";//站点编号
		String zdname = "";//站点名称
		String szdq = "";//所属地域
		String shi1="";//市
		String xian1="";//县
		String xiaoqu1="";//小区
		String cbmonth = "";//超标月份
		String cbbili = "";//超表比例
	    String shengpd="";//省派单 
	    String dsqs="";//地市签收
	    String dspd="";//地市派单
	    String qxqs="";//区县签收
	    String qxpd="";//区县派单
	    String qxcstj="";//区县测试 提交标志
	    String shish="";//市级审核
	    String shengsh="";//省级审核
	    String shengxf="";//省级下发(下发)
	    String dsxf="";//地市下发(下发)
	    String zgwczt="";//整改完成状态(下发)
	    String sjsh="";//市级审核(下发)
	    String shengjsh="";//省级审核(下发)
	    String cid="";//第二张表里的id
	    int countt=0;
	    cbzddao dao=new cbzddao();
	    cbzddao dao1=new cbzddao();
	    int r = dao.CheckQx(id);
	    int rr=dao1.CheckZg(cid);
		if(list!=null){
			for(Zdinfo bean2:list){
				id=bean2.getId();
				zdid=bean2.getZdid();
				zdname=bean2.getZdname();
				shi1=bean2.getShi();
				xian1=bean2.getXian();
				xiaoqu1=bean2.getXiaoqu();
				cbmonth=bean2.getCbsj();
				cbbili=bean2.getBzpld();
				szdq=shi1+xian1+xiaoqu1;
				shengpd=bean2.getSjpd();
				dsqs=bean2.getDsqs();
				dspd=bean2.getDspd();
				qxqs=bean2.getQxqs();
				qxpd=bean2.getQxpd();
				qxcstj=bean2.getQxtjsh();
				shish=bean2.getShijsh();
				shengsh=bean2.getShengjsh();
				shengxf=bean2.getSjxf();
				dsxf=bean2.getShijxf();
				zgwczt=bean2.getQxzgtj();
				sjsh=bean2.getSjshbz();
				shengjsh=bean2.getShengjshbz();
				cid=bean2.getCid();
				
			System.out.println("222id:"+id +" shengxf:"+shengxf+" dsxf"+dsxf);	
			if(zdid==null ||zdid.equals("null")||zdid.equals("")||zdid.equals(" ")||zdid==""){
				zdid="";
			}
				if(zdname==null ||zdname.equals("null")||zdname.equals("")||zdname.equals(" ")||zdname==""){
				zdname="";
			}
				if(shi1==null ||shi1.equals("null")||shi1.equals("")||shi1.equals(" ")||shi1==""){
				shi1="";
			}
				if(xian1==null ||xian1.equals("null")||xian1.equals("")||xian1.equals(" ")||xian1==""){
				xian1="";
			}
				if(xiaoqu1==null ||xiaoqu1.equals("null")||xiaoqu1.equals("")||xiaoqu1.equals(" ")||xiaoqu1==""){
				xiaoqu1="";
			}
				if(cbmonth==null ||cbmonth.equals("null")||cbmonth.equals("")||cbmonth.equals(" ")||cbmonth==""){
				cbmonth="";
			}
				DecimalFormat price1=new DecimalFormat("0.00");
				if(cbbili==null ||cbbili.equals("null")||cbbili.equals("")||cbbili.equals(" ")||cbbili==""){
				cbbili="";
			}else{
				double b = Double.parseDouble(cbbili);
				cbbili=price1.format(Double.parseDouble(cbbili)*100)+"%";
			}
		
				if(szdq==null ||szdq.equals("null")||szdq.equals("")||szdq.equals(" ")||szdq==""){
				szdq="";
			}
			
			
			//////////
				if(shengpd==null ||shengpd.equals("null")||shengpd.equals("")||shengpd.equals(" ")||shengpd==""){
				shengpd="0";
			}
				if(dsqs==null ||dsqs.equals("null")||dsqs.equals("")||dsqs.equals(" ")||dsqs==""){
				dsqs="0";
			}
				if(dspd==null ||dspd.equals("null")||dspd.equals("")||dspd.equals(" ")||dspd==""){
				dspd="0";
			}
				if(qxpd==null ||qxpd.equals("null")||qxpd.equals("")||qxpd.equals(" ")||qxpd==""){
				qxpd="0";
			}
				if(qxqs==null ||qxqs.equals("null")||qxqs.equals("")||qxqs.equals(" ")||qxqs==""){
				qxqs="0";
			}
				if(qxcstj==null ||qxcstj.equals("null")||qxcstj.equals("")||qxcstj.equals(" ")||qxcstj==""){
				qxcstj="0";
			}
				if(shish==null ||shish.equals("null")||shish.equals("")||shish.equals(" ")||shish==""){
				shish="0";
			}
				if(shengsh==null ||shengsh.equals("null")||shengsh.equals("")||shengsh.equals(" ")||shengsh==""){
				shengsh="0";
			}
				if(shengxf==null ||shengxf.equals("null")||shengxf.equals("")||shengxf.equals(" ")||shengxf==""){
				shengxf="0";
			}
				if(dsxf==null ||dsxf.equals("null")||dsxf.equals("")||dsxf.equals(" ")||dsxf==""){
				dsxf="0";
			}
				if(zgwczt==null ||zgwczt.equals("null")||zgwczt.equals("")||zgwczt.equals(" ")||zgwczt==""){
				zgwczt="0";
			}
				if(sjsh==null ||sjsh.equals("null")||sjsh.equals("")||sjsh.equals(" ")||sjsh==""){
				sjsh="0";
			}
				if(shengjsh==null ||shengjsh.equals("null")||shengjsh.equals("")||shengjsh.equals(" ")||shengjsh==""){
				shengjsh="0";
			}
				if(cid==null ||cid.equals("null")||cid.equals("")||cid.equals(" ")||cid==""){
				cid="";
			}
			
			if(shengpd != null&& shengpd.equals("2")){
				shengpd="地市退单";
			}else if(shengpd != null	&& shengpd.equals("1")){
				shengpd="已派单";
			} else if(shengpd != null	&& shengpd.equals("0")){
				shengpd="未派单";
			}
				if(dsqs != null&& dsqs.equals("0")){
				dsqs="未签收";
			}else if(dsqs != null	&& dsqs.equals("1")){
				dsqs="已签收";
			} 
				if(dspd != null&& dspd.equals("2")){
				dspd="区县退单";
			}else if(dspd != null	&& dspd.equals("1")){
				dspd="已派单";
			} else if(dspd != null	&& dspd.equals("0")){
				dspd="未派单";
			}
				if(qxqs != null&& qxqs.equals("0")){
				qxqs="未签收";
			}else if(qxqs != null&& qxqs.equals("1")){
				qxqs="已签收";
			}
				if(qxpd != null	&& qxpd.equals("1")){
				qxpd="已派单";
			} else if(qxpd != null	&& qxpd.equals("0")){
				qxpd="未派单";
			}
				if(qxcstj != null	&& qxcstj.equals("1")){
				qxcstj="已核实";
			} else if(qxcstj != null	&& qxcstj.equals("0")){
				qxcstj="未核实";
			}
				if(shish != null	&& shish.equals("1")){
				shish="审核不通过";
			} else if(shish != null	&& shish.equals("2")){
				shish="审核通过";
			}else if(shish != null	&& shish.equals("0")){
				shish="未审核";
			}
				if(shengsh != null	&& shengsh.equals("1")){
				shengsh="退单";
			} else if(shengsh != null	&& shengsh.equals("2")){
				shengsh="整改";
			}else if(shengsh != null	&& shengsh.equals("0")){
				shengsh="未审核";
			}else if(shengsh != null	&& shengsh.equals("3")){
				shengsh="结单";
			}
				if(shengxf != null	&& shengxf.equals("1")){
				shengxf="已下发";
			} else if(shengxf != null	&& shengxf.equals("0")){
				shengxf="未下发";
			}
			
			if(dsxf != null	&& dsxf.equals("1")){
				dsxf="已下发";
			} else if(dsxf != null	&& dsxf.equals("0")){
				dsxf="未下发";
			}
			if(zgwczt != null	&& zgwczt.equals("1")){
				zgwczt="已提交";
			} else if(zgwczt != null	&& zgwczt.equals("0")){
				zgwczt="未提交";
			}
				if(sjsh != null	&& sjsh.equals("1")){
				sjsh="审核通过";
			} else if(sjsh != null	&& sjsh.equals("0")){
				sjsh="未审核";
			}else if(sjsh != null	&& sjsh.equals("2")){
				sjsh="审核不通过";
			}
				if(shengjsh != null	&& shengjsh.equals("1")){
				shengjsh="结单";
			} else if(shengjsh != null	&& shengjsh.equals("0")){
				shengjsh="未审核";
			}else if(shengjsh != null	&& shengjsh.equals("2")){
				shengjsh="审核不通过";
			}
			
             countt++;
			 String color=null;
       %> 
    
  <tr height=19 style='height:14.25pt'>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdname%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=szdq%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=cbmonth%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=cbbili%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=shengpd%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dsqs%></td>
   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dspd%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=qxqs%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=qxpd%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=qxcstj%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=shish%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=shengsh%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=shengxf%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dsxf%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zgwczt%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=sjsh%></td>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=shengjsh%></td>
 </tr>
         <%
         }}
         %>
  <tr> 
  	<td colspan="17">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=countt%>条；</font></b>
	</div>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
