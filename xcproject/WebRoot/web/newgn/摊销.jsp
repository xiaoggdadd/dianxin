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
	    String str1=request.getParameter("str1")!=null?request.getParameter("str1"):"";
	    String str2=request.getParameter("str2")!=null?request.getParameter("str2"):"";
	    String str3=request.getParameter("str3")!=null?request.getParameter("str3"):"";
	    String str11=request.getParameter("str11")!=null?request.getParameter("str11"):"";
	    String str22=request.getParameter("str22")!=null?request.getParameter("str22"):"";
	    String str33=request.getParameter("str33")!=null?request.getParameter("str33"):"";
	    String tr=request.getParameter("tr")!=null?request.getParameter("tr"):"";
	    String beginTime=request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";
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
  <td class=xl25 width=72 style='border-left:none;width:54pt'>站点名称</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>所在地区</td>
  <td class=xl25 width=72 style='border-left:none;width:114pt'>电费支付类型</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>合同编号</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>摊销金额</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>摊销余额</td>
 </tr>
  <%
	     DanZaiPin be=new DanZaiPin();	
       	 List<CityQueryBean> fylist = null;
       	
         fylist = be.getTanXiao(whereStr,str1,loginId,tr,str2,str3,str11,str22,str33);
		 String jzname = "",szdq="",zdlx1="",dfzflx1="",txje="0",htbh1="",bzyf="",qsyf="",jsyf="",zje="0",je="0",txye="0";
		 int intnum=xh = pagesize*(curpage-1)+1;
		 java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM"); //日期格式类型初始化
		 int count1 = 0;
		 if(fylist!=null){
             for (CityQueryBean beans:fylist) {  
              
		    
		     //num为序号，不同页中序号是连续的
			jzname = beans.getJzname();
			szdq =  beans.getAddress();
			dfzflx1 =  beans.getDfzflx();
			txje =  beans.getTxje();
			htbh1=beans.getHtbh();
			bzyf=beans.getBzyf();//报账月份
			qsyf=beans.getQsyf();//本次合同起始月份
			jsyf=beans.getJsyf();//本次合同结束月份
			zje=beans.getSfjine();//本次合同总金额
			//转换为日期格式
			Date bzyf1=formatter.parse(bzyf);//报账月份
			Date beginTime1=formatter.parse(beginTime);//本次摊销月份
			Date qsyf1=formatter.parse(qsyf);//本次合同起始月份
			Date jsyf1=formatter.parse(jsyf);//本次合同结束月份
			int month = bzyf1.getYear()*12+bzyf1.getMonth()-qsyf1.getYear()*12-qsyf1.getMonth()+1;//相差几个月
			int db=beginTime1.getYear()*12+beginTime1.getMonth()-bzyf1.getYear()*12-bzyf1.getMonth();//摊销金额的判断条件
			int tt=beginTime1.getYear()*12+beginTime1.getMonth()-qsyf1.getYear()*12-qsyf1.getMonth();//摊销余额乘的数
			//System.out.println("起始月份："+qsyf+"结束月份："+jsyf+"报账月份："+bzyf+"总金额"+zje+"查询月份"+beginTime+"比较数"+db+"相差月份："+month);
					String dd="0";//判断数据显示的标志位
      //第一种情况  报账月份在 起始月份和结束月份之间
	        if(qsyf1.getTime()<=bzyf1.getTime()&&bzyf1.getTime()<=jsyf1.getTime()){
	        	if(beginTime1.getTime()<bzyf1.getTime()){  //查询月份小于报账月份  //不显示 待处理？？？？
	        	  dd="1";//判断显示不显示标志位
	        	 }else if (beginTime1.getTime()==bzyf1.getTime()){//查询月份等于报账月份
	        	 			if(beginTime1.getTime()==jsyf1.getTime()){
	        	 	  			je=zje;//摊销金额
	        	 	  			txye="0";//摊销余额
	        	 			}else{
	        	 				double tx=Double.valueOf(txje)*month;//摊销金额  为 每月平均摊销金额乘以报账月份之前有几个月
	        	 				double yee=Double.parseDouble(zje)-tx;
			           			txye=yee+"";//摊销余额
			           			je=tx+"";//本次摊销金额
	        	 	        }
	        	 
	        	 }else if(beginTime1.getTime()>bzyf1.getTime()){//查询月份大于报账月份
	        	 		if(jsyf1.getTime()==beginTime1.getTime()){
	        	 			je=txje;//本次摊销金额
	        	 			txye="0";
	        	 		}else{
	        	      		je=txje;//本次摊销金额
				     		 double ye=Double.parseDouble(zje)-Double.valueOf(txje)-Double.parseDouble(txje)*tt;//总金额-本次摊销-前几个月的摊销
				      		txye=ye+"";//摊销余额
	        	 		}
	        	 }else{
	        	 	dd="5";
	        	 }
			
			}else if(bzyf1.getTime()>jsyf1.getTime()){  //第二种情况  报账月份大于 起始月份和结束月份   (有可能查到不需要的数据？？？)
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
						}else if (qsyf1.getTime()<=beginTime1.getTime()&&beginTime1.getTime()<=jsyf1.getTime()){
									if(jsyf1.getTime()==beginTime1.getTime()){
										je=txje;//摊销金额
										txye="0";//摊销余额
									}else{
										je=txje;//摊销金额
							 			double ye=Double.parseDouble(zje)-Double.valueOf(txje)-Double.parseDouble(txje)*tt;//总金额-本次摊销-前几个月的摊销
	        	 	  					txye=ye+"";//摊销余额
	        	 	  				}
						}else{
							dd="7";
						}
			
			}
			//摊销余额如果为负数则不显示
		/*	if(Double.valueOf(txye)<0){ 
			   dd="8";
			}
			*/
			
			if("0".equals(dd)){
			
			count1 = count1+1;
			DecimalFormat mat=new DecimalFormat("0.00");
			je=mat.format(Double.parseDouble(je));
			txye=mat.format(Double.parseDouble(txye));
			//count1=count1+Double.valueOf(je);
			//heji=mat.format(count1);//合计金额
			
		

       %>
       <tr height=19 style='height:14.25pt'>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=intnum++%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=szdq%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dfzflx1%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=htbh1%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=je%></td>
       		<td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=txye%></td>
       </tr>
       <%} %>
        <%} %>

       <%}%>
  <tr> 
  	<td colspan="6">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=count1%>条；</font></b>
							
	</div>
  </tr>

</table>

</body>

</html>
