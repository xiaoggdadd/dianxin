<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.List,java.util.ArrayList,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean,com.noki.mobi.common.AccountJzqa,com.noki.mobi.common.zdbzbeanB" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
<%
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
	
	String shi = request.getParameter("shi");
	String xian = request.getParameter("xian");
    String bla=request.getParameter("bl");
    String bzmonth=request.getParameter("bzmonth");//标准月份
    String yf=request.getParameter("yf");//月份    2 开始月份     3 报账月份
     String sjyf=request.getParameter("sjyf");
     String dbyt=request.getParameter("dbyt");//3 结算电表    2管理电表
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");

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
 <td class=xl25 width=72 style='border-left:none;width:54pt'>2G</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>3G</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>厂家</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>载频</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>载扇</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>标准电量</td>  
  
 <td class=xl25 width=72 style='border-left:none;width:80pt'>地市</td>
 <td class=xl25 width=72 style='border-left:none;width:80pt'>区县</td>
 <td class=xl25 width=72 style='border-left:none;width:80pt'>乡镇</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>站点ID</td>
 <td class=xl25 width=72 style='border-left:none;width:80pt'>站点名称</td>
 <td class=xl25 width=72 style='border-left:none;width:80pt'>上次抄表时间</td>       
        
  <td class=xl25 width=72 style='border-left:none;width:54pt'>上次抄表读数</td>
  <td class=xl25 width=72 style='border-left:none;width:80pt'>本次抄表时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>本次抄表读数</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>倍率耗电量</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>周期</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>日均耗电量</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>超标比例</td>
 </tr>
 <%           
		if(null !=bla&& !bla.equals("")){
       		String whereStr = "";
                     String str="",str1="",str2="",month="";                     
              		if(null !=shi  && !shi.equals("") && !shi.equals("0")){
              			whereStr=whereStr+" and zd1.shi='"+shi+"'";
              		}
              		if(null != xian && !xian.equals("") && !xian.equals("0")){              	
              			whereStr=whereStr+" and zd1.xian='"+xian+"'";
              		}		
             if(null!=yf&&!yf.equals("")){
                 if("3".equals(yf)){
	                  if(null!=sjyf&&!"".equals(sjyf)){
	                 	whereStr=whereStr+" and to_char(b.accountmonth,'yyyy-mm')='"+sjyf+"'";
	                  }
              	  }
	              if("2".equals(yf)){
	              		if(null!=sjyf&&!"".equals(sjyf)){
	                 		whereStr=whereStr+" and to_char(startmonth,'yyyy-mm')='"+sjyf+"'";
		                }
		           }
	           }
              		
                   AccountJzqa  bean = new AccountJzqa();
                   List<zdbzbeanB> fylist = null;
                    fylist=bean.getPageDatapdc(whereStr,bla,yf,bzmonth,dbyt);
              		String dbid="",dl="",time="",zdid="",dbida="";
              		int intnum=xh = pagesize*(curpage-1)+1;
              		int linenum=0;
              		int zs,zp;
              		String g2="",g3="";
              		String cj="",rjdl="";
              		String shi1="",xian1="",xiaoqu="",zdname="",blhdl="",zq="",bl="";
              		String sccbsj="",sccb="",bccbsj="",bccb="";
              		double df=0.0,bd=0.0;
              		if(fylist!=null){
              		for(zdbzbeanB bean1:fylist){
              			count++;
              		   DecimalFormat mat =new DecimalFormat("0.0");
              			g2=bean1.getG2();
	              		if("1".equals(g2)){
	              		 g2="是";
	              		}else{
	              		 g2="否";
	              		}
	              		g3=bean1.getG3();
	              		if("1".equals(g3)){
	              		 g3="是";
	              		}else{
	              		 g3="否";
	              		}
              		zp=bean1.getZP();
              		zs=bean1.getZS();
              		cj=bean1.getCHANGJIA();
              		cj = cj !=null ? cj : "";
              		
              		bd=bean1.getEDGL();
              		
              		shi1=bean1.getSHI();
              		shi1 = shi1 !=null ? shi1 : "";
              		
              		xian1=bean1.getXian();
              		xian1 = xian1 !=null ? xian1 : "";
              		
              		xiaoqu=bean1.getXiaoqu();
              		xiaoqu = xiaoqu !=null ? xiaoqu : "";
              		
              		zdname=bean1.getZdname();
              		zdname = zdname !=null ? zdname : "";
              		
              		zdid=bean1.getId();
              		zdid = zdid !=null ? zdid : "";
              		
              		sccbsj = bean1.getSccbsj();
              		sccbsj = sccbsj !=null ? sccbsj : ""; 
              		
              		sccb = bean1.getSccb();
              		sccb = sccb !=null ? sccb : "0.0";       	
        			if("".equals(sccb)||"null".equals(sccb)){
        			 	sccb="0.0";
      			  	}
      			  	
      			  	bccbsj = bean1.getBccbsj();
      			  	bccbsj = bccbsj !=null ? bccbsj : ""; 
      			  	bccb =bean1.getBccb();
      			  	bccb = bccb !=null ? bccb : "0.0";       	
        			if("".equals(bccb)||"null".equals(bccb)){
        			 	bccb="0.0";
      			  	}
      			  	
              		blhdl = bean1.getBlhdl();
              		blhdl = blhdl !=null ? blhdl : "0.0";       	
        			if("".equals(blhdl)||"null".equals(blhdl)){
        			 	blhdl="0.0";
      			  	}
      			  	blhdl = mat.format(Double.parseDouble(blhdl));
      			  	
      			  	zq = bean1.getJs();
      			  	zq = zq !=null ? zq : "";
      			  	
              		rjdl = bean1.getRjdl();
              		rjdl = rjdl !=null ? rjdl : "0.0";       	
        			if("".equals(rjdl)||"null".equals(rjdl)){
        			 	rjdl="0.0";
      			  	}
      			  	rjdl = mat.format(Double.parseDouble(rjdl));
      			  	
      			  	bl = bean1.getBl();
      			  	bl = bl !=null ? bl : "0.0";       	
        			if("".equals(bl)||"null".equals(bl)){
        			 	rjdl="0.0";
      			  	}
      			  	bl = mat.format(Double.parseDouble(bl));		  

			String color=null;

       %>
 <tr height=19 style='height:14.25pt'>   
	  <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=g2%></td>
	   <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=g3%></td>
	  <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=cj%></td>
	  <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=zp%></td>
	  <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=zs%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=bd%></td>
	  <td class=xl26 align=left style='border-top:none;border-left:none' x:num><%=shi1%></td>
  
     <td class=xl26 align=left style='border-top:none;border-left:none' x:num><%=xian1%></td>
     <td class=xl26 align=left style='border-top:none;border-left:none' x:num><%=xiaoqu%></td>
	 <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=zdid%></td>
	 <td class=xl26 align=center style='border-top:none;border-left:none' x:num><%=zdname%></td>
	 <td class=xl26 align=center style='border-top:none;border-left:none' x:num>&nbsp;<%=sccbsj%></td>
	 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=sccb%></td>
 
	  <td class=xl26 align=center style='border-top:none;border-left:none' x:num>&nbsp;<%=bccbsj%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=bccb%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=blhdl%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zq%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=rjdl%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=bl%>%</td> 
 </tr>
         <%
         }}}
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
