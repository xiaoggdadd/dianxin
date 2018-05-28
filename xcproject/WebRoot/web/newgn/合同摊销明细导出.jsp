<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>

<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*" %>
<%@ page import="com.noki.electricfees.javabean.*" %>
<%@ page import="com.noki.function.*" %>
<%@ page import="java.util.ArrayList,java.util.List" %>
<%

        Account account = new Account();
        account = (Account)session.getAttribute("account");
        String qyear = request.getParameter("qyear");
		CTime time = new CTime();
		//String accountId = account.getAccountId();
		String loginId = account.getAccountId();
        String loginName = account.getAccountName();
        String path = request.getContextPath();
	    String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";
	    String where = request.getParameter("where")!=null?request.getParameter("where"):"";
	    String str = request.getParameter("str")!=null?request.getParameter("str"):"";
	    String wh = request.getParameter("wh")!=null?request.getParameter("wh"):"";
	    String pd = request.getParameter("pd")!=null?request.getParameter("pd"):"";
	     String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";
	    System.out.println("22222"+whereStr);
	    
	    
	    
        String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);

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

  <td class=xl25 width=152 style='border-left:none;width:114pt'>ID</td>  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>所在地区</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>站点名称</td>
  <td class=xl25 width=72 style='border-left:none;width:114pt'>电费支付类型</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>起始月份</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>结束月份</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>合同总金额</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>本次摊销金额</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>摊销余额</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>累计付款</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>累计摊销金额</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>累计收据金额</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>累计发票金额</td>
 </tr>   
 <%
   
	     DanZaiPin be=new DanZaiPin();	
       	 List<CityQueryBean> fylist = null;
         fylist = be.getHtTanXiao(whereStr,str,where,wh,loginId,pd);
		 String jzname = "",szdq="",dfzflx1="",txje="0",qsyf="",jsyf="",htzje="0",txye="0",
		 ljff="0",ljtxje="0",ljsj="0",ljfp="0",fp="0",bfp="0",ksyf1="",jsyf1="",bzyf="",je="0",zje="0";
		 int intnum=xh = pagesize*(curpage-1)+1;
		 java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM"); //日期格式类型初始化
		 int count1=0;
		 if(fylist!=null){
             for (CityQueryBean beans:fylist) {   
             je="0";
             txye="0";
             ljtxje="0";//初始化
             count1=count1+1;
		     //num为序号，不同页中序号是连续的
			
			jzname = beans.getJzname();
			szdq =  beans.getAddress();
			dfzflx1 =  beans.getDfzflx();
			txje =  beans.getTxje();
			qsyf=beans.getQsyf();//最小起始月份
			jsyf=beans.getJsyf();//最大结束月份
			htzje=beans.getHtzje();
			ljff=beans.getLjff();
			//ljtxje=beans.getLjtxje();
			ljsj=beans.getLjsj();
			fp=beans.getLjfp();
			bfp=beans.getBfp();
			ksyf1=beans.getKsyf1();//本次摊销的起始月份  做判断
			jsyf1=beans.getJsyf1();//本次摊销的结束月份  做判断
			bzyf=beans.getBzyf();//本次摊销的报账月份        做判断
			zje=beans.getBchtzje();//本次合同总金额
			
			DecimalFormat mat=new DecimalFormat("0.00");
			ljff=mat.format(Double.parseDouble(ljff));//累计付费
			fp=mat.format(Double.parseDouble(fp));//发票金额
			bfp=mat.format(Double.parseDouble(bfp));//补发票金额
			
		     if("".equals(txje)||txje==null)txje="0";
		     if("".equals(htzje)||htzje==null)htzje="0";
		     if("".equals(ljff)||ljff==null)ljff="0";
		     if("".equals(ljsj)||htzje==null)ljsj="0";
		     if("".equals(fp)||fp==null)fp="0";
		     if("".equals(bfp)||bfp==null)bfp="0";
		     if("".equals(zje)||zje==null)zje="0";
		
			double fp1=Double.parseDouble(fp)+Double.parseDouble(bfp);
			ljfp=fp1+"";
			htzje=mat.format(Double.parseDouble(htzje));//合同总金额
			txje=mat.format(Double.parseDouble(txje));//本次摊销金额
			ljfp=mat.format(Double.parseDouble(ljfp));//累计发票金额
			ljsj=mat.format(Double.parseDouble(ljsj));//累计收据金额
			
			
			//判断
			//转换为日期格式
			if(!"".equals(bzyf)&&!"".equals(ksyf1)&&!"".equals(jsyf1)){//月份不为空就执行下面的功能
			Date bzyf1=formatter.parse(bzyf);//报账月份
			Date beginTime1=formatter.parse(beginTime);//本次摊销月份
			Date qsyf1=formatter.parse(ksyf1);//本次合同起始月份
			Date jsyf2=formatter.parse(jsyf1);//本次合同结束月份
			int month = bzyf1.getYear()*12+bzyf1.getMonth()-qsyf1.getYear()*12-qsyf1.getMonth()+1;//相差几个月
			int db=beginTime1.getYear()*12+beginTime1.getMonth()-bzyf1.getYear()*12-bzyf1.getMonth();//摊销金额的判断条件(查询月份减报账月份)
			int tt=beginTime1.getYear()*12+beginTime1.getMonth()-qsyf1.getYear()*12-qsyf1.getMonth();//摊销余额乘的数     查询月份-起始月份
			  String dd="0";//判断数据显示的标志位
	        //第一种情况  报账月份在 起始月份和结束月份之间
	        if(qsyf1.getTime()<=bzyf1.getTime()&&bzyf1.getTime()<=jsyf2.getTime()){
	        	if(beginTime1.getTime()<bzyf1.getTime()){  //查询月份小于报账月份  //不显示 待处理？？？？
	        	  	dd="1";//判断显示不显示标志位
	        	 }else if (beginTime1.getTime()==bzyf1.getTime()){//查询月份等于报账月份
	        	 			if(beginTime1.getTime()==jsyf2.getTime()){
	        	 	  			je=zje;//摊销金额
	        	 	  			txye="0";//摊销余额
	        	 			}else{
	        	 				double tx=Double.valueOf(txje)*month;//摊销金额  为 每月平均摊销金额乘以报账月份之前有几个月
	        	 				double yee=Double.parseDouble(zje)-tx;
			           			txye=yee+"";//摊销余额
			           			je=tx+"";//本次摊销金额
	        	 	        }
	        	 
	        	 }else if(beginTime1.getTime()>bzyf1.getTime()){//查询月份大于报账月份
	        	      je=txje;//本次摊销金额
	        	      System.out.println("33331");
				      double ye=Double.parseDouble(zje)-Double.valueOf(txje)-Double.parseDouble(txje)*tt;//总金额-本次摊销-前几个月的摊销
				      System.out.println(Double.parseDouble(zje)+"qqq"+Double.valueOf(txje)+"eee"+Double.parseDouble(txje)*tt);
				      txye=ye+"";//摊销余额
	        	 
	        	 }else{
	        	 	dd="5";
	        	 }
			
			}else if(bzyf1.getTime()>jsyf2.getTime()){  //第二种情况  报账月份大于 起始月份和结束月份   (有可能查到不需要的数据？？？)
						if(beginTime1.getTime()<bzyf1.getTime()){//不显示 带处理
							dd="2";
						}else if (beginTime1.getTime()==bzyf1.getTime()){
							    je=zje;//摊销金额
	        	 	  			txye="0";//摊销余额
						}else if(beginTime1.getTime()>bzyf1.getTime()){//不显示 带处理
							dd="3";	
						}else{
							dd="6";
						}
			
			}else if(bzyf1.getTime()<qsyf1.getTime()){//第三种情况  报账月份小于起始结束月份
						if(beginTime1.getTime()<bzyf1.getTime()){//查不到 待处理
							dd="4";	
						}else if(bzyf1.getTime()<=beginTime1.getTime()&&beginTime1.getTime()<qsyf1.getTime()){
						        je="0";//摊销金额
	        	 	  			txye=zje;//摊销余额
						}else if (qsyf1.getTime()<=beginTime1.getTime()&&beginTime1.getTime()<=jsyf2.getTime()){
								je=txje;//摊销金额
							 	double ye=Double.parseDouble(zje)-Double.valueOf(txje)-Double.parseDouble(txje)*tt;//总金额-本次摊销-前几个月的摊销
	        	 	  			txye=zje;//摊销余额
						}else{
							dd="7";
						}
			
			}
			if("0".equals(dd)){
	        double qq=Double.parseDouble(ljff)-Double.parseDouble(txye);
	           ljtxje=qq+"";//累计摊销金额	
	        }
		}	
		je=mat.format(Double.parseDouble(je));//本次摊销金额
		txye=mat.format(Double.parseDouble(txye));//摊销余额
		ljtxje=mat.format(Double.parseDouble(ljtxje));//累计摊销金额	
			
			String color=null;

			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr height=19 style='height:14.25pt'>
			<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=intnum++%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=szdq%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dfzflx1%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num> &nbsp;<%=qsyf%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num> &nbsp;<%=jsyf%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=htzje%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=je%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=txye%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=ljff%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=ljtxje%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=ljsj%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=ljfp%></td>
       </tr>
       <%} %>

       <%}%>
  <tr> 
  	<td colspan="13">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=count1%>条；</font></b>
							
	</div>
  </tr>

</table>

</body>

</html>
