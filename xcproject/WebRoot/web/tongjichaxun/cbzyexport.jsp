<%@ page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account,java.util.Vector,java.sql.ResultSet" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.ZhanDianForm,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" %>
<%@ page import="com.noki.tongjichaxu.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%
String path = request.getContextPath();
String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";
String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";
String jzsx = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
String bzw = request.getParameter("bzw")!=null?request.getParameter("bzw"):"";
String fy = request.getParameter("feiyongtype")!=null?request.getParameter("feiyongtype"):"0";
String zflx= request.getParameter("zflx")!=null?request.getParameter("zflx"):"0";

Account account = (Account) session.getAttribute("account");
String roleId = account.getRoleId();
String s_curpage = request.getParameter("curpage") != null ? request.getParameter("curpage"): "1";
String shenhe= request.getParameter("shenhe")!=null?request.getParameter("shenhe"):"2";
int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
String b1=request.getParameter("");
curpage = Integer.parseInt(s_curpage);   
int intnum=0;
String color="";
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
  <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>   



<body link=blue vlink=purple>
       <%
        //Vector v = bean.getHuanbi_all("0",qijian,qyear);         
       %>
<table x:str border=0 cellpadding=0 cellspacing=0 width=705 style='border-collapse:
 collapse;table-layout:fixed;width:530pt'>
 <col width=142 style='mso-width-source:userset;mso-width-alt:4544;width:107pt'>
 <col width=152 style='mso-width-source:userset;mso-width-alt:4864;width:114pt'>
 <col width=174 style='mso-width-source:userset;mso-width-alt:5568;width:131pt'>
 <col width=165 style='mso-width-source:userset;mso-width-alt:5280;width:124pt'>
 <col width=72 style='width:54pt'>
 <tr class=xl24 height=19 style='height:14.25pt'>  
 	   	<td class=xl25 width=72 style='border-left:none;width:54pt'>地市</td>
 	   	 <td class=xl25 width=72 style='border-left:none;width:54pt'>站点总数</td>
 	   	  <td class=xl25 width=72 style='border-left:none;width:54pt'>结算周期</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>费用条数</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>业务审核金额</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>财务确认金额</td>
         <td class=xl25 width=72 style='border-left:none;width:54pt'>增值税金额</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>比例</td>
		<td class=xl25 width=72 style='border-left:none;width:54pt'>网络运营</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>市场经营</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>行政综合</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>信息化支撑</td> 
        <td class=xl25 width=72 style='border-left:none;width:54pt'>建设投资</td> 
        <td class=xl25 width=72 style='border-left:none;width:54pt'>代垫电费</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>比例</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>移动专业-2G</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>移动专业-3G</td>
		<td class=xl25 width=72 style='border-left:none;width:54pt'>固网专业</td>                        
        <td class=xl25 width=72 style='border-left:none;width:54pt'>固移共同专业</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>移动共同专业</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>不可分摊专业</td>
        <td class=xl25 width=72 style='border-left:none;width:54pt'>比例</td>
</tr>
 		<%
		if(bzw.equals("1")){
			String whereStr = "",str="";
			//String whereStr1="",str1="";
			if(beginTime != null && !beginTime.equals("") && !beginTime.equals("0")){
				whereStr=whereStr+" AND to_char(e.accountmonth,'yyyy-mm')>='"+beginTime+"'";
				str=str+" AND to_char(ee.accountmonth,'yyyy-mm')>='"+beginTime+"'";
				//whereStr1=whereStr1+" AND e.accountmonth>='"+beginTime+"'";
				//str1=str1+" AND ee.accountmonth>='"+beginTime+"'";
			}
			if(endTime != null && !endTime.equals("") && !endTime.equals("0")){
				whereStr=whereStr+" AND to_char(e.accountmonth,'yyyy-mm')<='"+endTime+"'";
				str=str+" AND to_char(ee.accountmonth,'yyyy-mm')<='"+endTime+"'";
				//whereStr1=whereStr1+" AND e.accountmonth<='"+endTime+"'";
				//str1=str1+" AND ee.accountmonth<='"+endTime+"'";
			}
			if(jzsx != null && !jzsx.equals("") && !jzsx.equals("0")){
				whereStr=whereStr+" AND z.property='"+jzsx+"'";
				str=str+" AND zz.property='"+jzsx+"'";
				//whereStr1=whereStr1+" AND z.property='"+jzsx+"'";
				//str1=str1+" AND zz.property='"+jzsx+"'";
			}
			if(fy != null && !fy.equals("") && !fy.equals("0")){
				if(fy.equals("1")){
					whereStr = whereStr+"AND e.actualpay >= '0'";
					str = str+"AND ee.actualpay >= '0'";
					//whereStr1 = whereStr1+"AND e.money >= '0'";
					//str1 = str1+"AND ee.money >= '0'";
				}
			if(fy.equals("-1")){
					whereStr = whereStr+"AND e.actualpay < '0'";
					str = str+"AND ee.actualpay < '0'";
					//whereStr1 = whereStr1+"AND e.money < '0'";
					//str1 = str1+"AND ee.money < '0'";
				}
			}
			
			if (zflx != null && !zflx.equals("") && !zflx.equals("0")) {
				whereStr = whereStr + " AND d.DFZFLX='" + zflx + "'";
				str=str+" AND dd.DFZFLX='"+zflx+"'";
			}
			if(shenhe != null && !shenhe.equals("") && !shenhe.equals("0")){
				if(shenhe.equals("2")){
					whereStr = whereStr+"AND e.MANUALAUDITSTATUS = '2'";
					str = str+"AND ee.MANUALAUDITSTATUS = '2'";
					//whereStr1 = whereStr1+"AND e.money >= '0'";
					//str1 = str1+"AND ee.money >= '0'";
				}
			if(shenhe.equals("1")){
					whereStr = whereStr+"AND e.CITYZRAUDITSTATUS = '1'";
					str = str+"AND ee.CITYZRAUDITSTATUS = '1'";
					//whereStr1 = whereStr1+"AND e.money < '0'";
					//str1 = str1+"AND ee.money < '0'";
				}
			}
			
       	FeiyongbaozhangDao dao = new FeiyongbaozhangDao();
       	HashMap<String,YuejieBean> hs=null;
       	HashMap<String,YuefufeiBean> hm=null;
		HashMap<String,ZHBean> hh = new HashMap<String,ZHBean>();
		ArrayList<String> li = new ArrayList<String>();
		li=dao.getShi();
		DecimalFormat mat=new DecimalFormat("0.00");
		int counts=0,jszqhj=0,diszdidhj=0;
		double ywz=0.0,cwz=0.0,qz=0.0,bl1=0.0,bl2=0.0,cqz=0.0,wlz=0.0,
		scz=0.0,xzz=0.0,xxz=0.0,jsz=0.0,dddfz=0.0,qtz=0.0,g2z=0.0,
		g3z=0.0,gwz=0.0,gyz=0.0,ydz=0.0,bkz=0.0,bl3=0.0,qt1=0.0,zy1=0.0,qt2=0.0,zy2=0.0;
		//遍历hs的key
       //	if(zflx.equals("-1")){
    		 hs = dao.getYJ(whereStr,str);
    		 System.out.println("月结---------------");
    		 if(!hs.isEmpty()){//判断月结Hashmap中是否为空,
    			Set<String> valu = hs.keySet();
    			for(String sss:valu){
    				ZHBean zb = new ZHBean();
    				qt1 = hs.get(sss).getWlyy()+hs.get(sss).getScjy()+hs.get(sss).getXzzh()+hs.get(sss).getXxhzc()+hs.get(sss).getTzjs()+hs.get(sss).getDddf();
    				zb.setCityname(sss);
    				zb.setCount(hs.get(sss).getCount());
    				zb.setYwmoney(hs.get(sss).getYwmoney());
    				zb.setCwmoney(hs.get(sss).getCwmoney());
    				zb.setBili((hs.get(sss).getCwmoney())/(hs.get(sss).getYwmoney()));
    				zb.setWlyy(hs.get(sss).getWlyy());
    				zb.setScjy(hs.get(sss).getScjy());
    				zb.setXzzh(hs.get(sss).getXzzh());
    				zb.setXxhzc(hs.get(sss).getXxhzc());
    				zb.setTzjs(hs.get(sss).getTzjs());
    				zb.setDddf(hs.get(sss).getDddf());
    				zb.setQt(qt1);
    				zb.setYwhdbl(qt1/hs.get(sss).getYwmoney());
    				zb.setYdzy1(hs.get(sss).getYdzy1());
    				zb.setYdzy2(hs.get(sss).getYdzy2());
    				zb.setGwzy(hs.get(sss).getGwzy());
    				zb.setGygtzy(hs.get(sss).getGygtzy());
    				zb.setYdgtzy(hs.get(sss).getYdgtzy());
    				zb.setBkftzy(hs.get(sss).getBkftzy());
    				zy1 = hs.get(sss).getYdzy1()+hs.get(sss).getYdzy2()+hs.get(sss).getGwzy()+hs.get(sss).getGygtzy()+hs.get(sss).getYdgtzy()+hs.get(sss).getBkftzy();
    				zb.setYwbl(zy1/hs.get(sss).getYwmoney());
    				zb.setJszq(hs.get(sss).getJszq());
    				zb.setDiszdid(hs.get(sss).getDiszdid());
    				zb.setZzsje(hs.get(sss).getZzsje());
    				hh.put(sss,zb);
    				}	
    			}
//      		}else if(zflx.equals("1")){
 //   		 	hm = dao.getYFF(whereStr1,str1);
 //   		 	 System.out.println("预支---------------");
 //   		 	if(!hm.isEmpty()){//判断预付费是否为空
// 					Set<String> val = hm.keySet();
// 					for(String ss:val){//遍历城市名
// 					ZHBean zb = new ZHBean();
// 						qt2 = hm.get(ss).getWlyy()+hm.get(ss).getScjy()+hm.get(ss).getXzzh()+hm.get(ss).getXxhzc()+hm.get(ss).getTzjs()+hm.get(ss).getDddf();
// 						zb.setCityname(ss);
// 						zb.setCount(hm.get(ss).getCount());
// 						zb.setYwmoney(hm.get(ss).getYwmoney());
// 						zb.setCwmoney(hm.get(ss).getCwmoney());
// 						zb.setBili((hm.get(ss).getCwmoney())/(hm.get(ss).getYwmoney()));
// 						zb.setWlyy(hm.get(ss).getWlyy());
// 						zb.setScjy(hm.get(ss).getScjy());
// 						zb.setXzzh(hm.get(ss).getXzzh());
// 						zb.setXxhzc(hm.get(ss).getXxhzc());
// 						zb.setTzjs(hm.get(ss).getTzjs());
// 						zb.setDddf(hm.get(ss).getDddf());
// 						zb.setQt(qt2);
// 						zb.setYwhdbl(qt2/hm.get(ss).getYwmoney());
// 						zb.setYdzy1(hm.get(ss).getYdzy1());
// 						zb.setYdzy2(hm.get(ss).getYdzy2());
// 						zb.setGwzy(hm.get(ss).getGwzy());
// 						zb.setGygtzy(hm.get(ss).getGygtzy());
// 						zb.setYdgtzy(hm.get(ss).getYdgtzy());
// 						zb.setBkftzy(hm.get(ss).getBkftzy());
// 						zy2 = hm.get(ss).getYdzy1()+hm.get(ss).getYdzy2()+hm.get(ss).getGwzy()+hm.get(ss).getGygtzy()+hm.get(ss).getYdgtzy()+hm.get(ss).getBkftzy();
// 						zb.setYwbl(zy2/hm.get(ss).getYwmoney());
// 						zb.setJszq(hm.get(ss).getJszq());
// 	    				zb.setDiszdid(hm.get(ss).getDiszdid());
// 						hh.put(ss,zb);
// 					}
// 				}
 //      		}else{
//       		 	hs = dao.getYJ(whereStr,str);
 //      			hm = dao.getYFF(whereStr1,str1);
//       		 	System.out.println("月结--------预支-------");
//       			Set<String> values = hs.keySet();
//    			Iterator<String> it = values.iterator();
//    			while(it.hasNext()){
//    				String name=(String)it.next();//得到key 城市名
//    				if(hm.containsKey(name)){//判断hm的key是否在hs中存在(一个城市是有2种都有),如果存在相应属性加起来
//    					ZHBean zb = new ZHBean(); //创建总和的Bean
//    					counts=hs.get(name).getCount()+hm.get(name).getCount();//总条数
//    					ywz = hs.get(name).getYwmoney()+hm.get(name).getYwmoney();//总业务审核金额
//    					cwz = hs.get(name).getCwmoney()+hm.get(name).getCwmoney();//总业务审核金额
//    					bl1 = cwz/ywz;//比例
//    					wlz = hs.get(name).getWlyy()+hm.get(name).getWlyy();//网络运营总金额
//    					scz = hs.get(name).getScjy()+hm.get(name).getScjy();//市场经营总金额
//    					xzz = hs.get(name).getXzzh()+hm.get(name).getXzzh();//行政综合总金额
//    					xxz = hs.get(name).getXxhzc()+hm.get(name).getXxhzc();//信息化支撑总金额
//    					jsz = hs.get(name).getTzjs()+hm.get(name).getTzjs();//投资建设总金额
//    					dddfz = hs.get(name).getDddf()+hm.get(name).getDddf();//代垫电费总金额
//    					qtz = wlz+scz+xzz+xxz+jsz+dddfz;//其他
//    					bl2 = qtz/ywz;//业务活动比例
//    					g2z = hs.get(name).getYdzy1()+hm.get(name).getYdzy1();//移动专业2G总金额
//    					g3z = hs.get(name).getYdzy2()+hm.get(name).getYdzy2();//移动专业3G总金额
//    					gwz = hs.get(name).getGwzy()+hm.get(name).getGwzy();//固网专业总金额
//    					gyz = hs.get(name).getGygtzy()+hm.get(name).getGygtzy();//固移共同专业总金额
//    					ydz = hs.get(name).getYdgtzy()+hm.get(name).getYdgtzy();//移动共同专业总金额
//    					bkz = hs.get(name).getBkftzy()+hm.get(name).getBkftzy();//不可分摊总金额
//    					bl3 = (g2z+g3z+gwz+gyz+ydz+bkz)/ywz;//专业比例
 //   					jszqhj=hs.get(name).getJszq()+hm.get(name).getJszq();//总条数
//    					diszdidhj=hs.get(name).getDiszdid()+hm.get(name).getDiszdid();//总条数
//    					zb.setCityname(name); 
//    					zb.setCount(counts);
//    					zb.setYwmoney(ywz);
//    					zb.setCwmoney(cwz);
//    					zb.setBili(bl1);
//    					zb.setWlyy(wlz);
 //   					zb.setScjy(scz);
//    					zb.setXzzh(xzz);
//    					zb.setXxhzc(xxz);
//    					zb.setTzjs(jsz);
//    					zb.setDddf(dddfz);
//    					zb.setQt(qtz);
//    					zb.setYwhdbl(bl2);
//    					zb.setYdzy1(g2z);
//    					zb.setYdzy2(g3z);
//    					zb.setGwzy(gwz);
//    					zb.setGygtzy(gyz);
//    					zb.setYdgtzy(ydz);
///    					zb.setBkftzy(bkz);
 //   					zb.setYwbl(bl3);
//    					zb.setJszq(jszqhj);
///    					zb.setDiszdid(diszdidhj);
//    					hh.put(name,zb);//put进第3个HashMap中
//    					it.remove();
//    					hm.remove(name);
//    				}
//    			}
 //   			if(!hs.isEmpty()){//判断月结Hashmap中是否为空,
  //  				Set<String> valu = hs.keySet();
//    				for(String sss:valu){
//	    				ZHBean zb = new ZHBean();
//	    				qt1 = hs.get(sss).getWlyy()+hs.get(sss).getScjy()+hs.get(sss).getXzzh()+hs.get(sss).getXxhzc()+hs.get(sss).getTzjs()+hs.get(sss).getDddf();
//	    				zb.setCityname(sss);
//	    				zb.setCount(hs.get(sss).getCount());
//	    				zb.setYwmoney(hs.get(sss).getYwmoney());
//	    				zb.setCwmoney(hs.get(sss).getCwmoney());
//	    				zb.setBili((hs.get(sss).getCwmoney())/(hs.get(sss).getYwmoney()));
//	    				zb.setWlyy(hs.get(sss).getWlyy());
//	    				zb.setScjy(hs.get(sss).getScjy());
//	    				zb.setXzzh(hs.get(sss).getXzzh());
//	    				zb.setXxhzc(hs.get(sss).getXxhzc());
//	    				zb.setTzjs(hs.get(sss).getTzjs());
//	    				zb.setDddf(hs.get(sss).getDddf());
//	    				zb.setQt(qt1);
//	    				zb.setYwhdbl(qt1/hs.get(sss).getYwmoney());
//	    				zb.setYdzy1(hs.get(sss).getYdzy1());
//	    				zb.setYdzy2(hs.get(sss).getYdzy2());
//	    				zb.setGwzy(hs.get(sss).getGwzy());
//	    				zb.setGygtzy(hs.get(sss).getGygtzy());
//	    				zb.setYdgtzy(hs.get(sss).getYdgtzy());
//	    				zb.setBkftzy(hs.get(sss).getBkftzy());
//	    				zb.setJszq(hs.get(sss).getJszq());
 //   					zb.setDiszdid(hs.get(sss).getDiszdid());
//	    				zy1 = hs.get(sss).getYdzy1()+hs.get(sss).getYdzy2()+hs.get(sss).getGwzy()+hs.get(sss).getGygtzy()+hs.get(sss).getYdgtzy()+hs.get(sss).getBkftzy();
//	    				zb.setYwbl(zy1/hs.get(sss).getYwmoney());
//	    				hh.put(sss,zb);
 //   				}	
 //   			}
  //  			if(!hm.isEmpty()){//判断预付费是否为空
   // 				Set<String> val = hm.keySet();
//    				for(String ss:val){//遍历城市名
//    					ZHBean zb = new ZHBean();
//    					qt2 = hm.get(ss).getWlyy()+hm.get(ss).getScjy()+hm.get(ss).getXzzh()+hm.get(ss).getXxhzc()+hm.get(ss).getTzjs()+hm.get(ss).getDddf();
//    					zb.setCityname(ss);
//    					zb.setCount(hm.get(ss).getCount());
//    					zb.setYwmoney(hm.get(ss).getYwmoney());
//    					zb.setCwmoney(hm.get(ss).getCwmoney());
 //   					zb.setBili((hm.get(ss).getCwmoney())/(hm.get(ss).getYwmoney()));
//    					zb.setWlyy(hm.get(ss).getWlyy());
//    					zb.setScjy(hm.get(ss).getScjy());
//    					zb.setXzzh(hm.get(ss).getXzzh());
 //   					zb.setXxhzc(hm.get(ss).getXxhzc());
//    					zb.setTzjs(hm.get(ss).getTzjs());
//    					zb.setDddf(hm.get(ss).getDddf());
//    					zb.setQt(qt2);
//    					zb.setYwhdbl(qt2/hm.get(ss).getYwmoney());
///    					zb.setYdzy1(hm.get(ss).getYdzy1());
//    					zb.setYdzy2(hm.get(ss).getYdzy2());
//    					zb.setGwzy(hm.get(ss).getGwzy());
//    					zb.setGygtzy(hm.get(ss).getGygtzy());
//    					zb.setYdgtzy(hm.get(ss).getYdgtzy());
//    					zb.setBkftzy(hm.get(ss).getBkftzy());
//    					zb.setJszq(hm.get(ss).getJszq());
//    					zb.setDiszdid(hm.get(ss).getDiszdid());
//    					zy2 = hm.get(ss).getYdzy1()+hm.get(ss).getYdzy2()+hm.get(ss).getGwzy()+hm.get(ss).getGygtzy()+hm.get(ss).getYdgtzy()+hm.get(ss).getBkftzy();
//    					zb.setYwbl(zy2/hm.get(ss).getYwmoney());
//    					hh.put(ss,zb);
//    				}
//    			}
//      	 	}
       	Set<String> keys = hh.keySet();
		for(String i:li){
			for(String ii:keys){
       			if(i.equals(ii)){
				ZHBean zz = hh.get(i);	
       %>
	       
	       
  					<tr height=19 style='height:14.25pt'>
                        <td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=zz.getCityname()%></td>
                         <td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=zz.getDiszdid()%></td>
                          <td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=zz.getJszq()%></td>
                        
                        <td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=zz.getCount()%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getYwmoney())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getCwmoney())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getZzsje())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=mat.format(zz.getBili()*100)%>%</td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getWlyy())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getScjy())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getXzzh())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getXxhzc())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getTzjs())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getDddf())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=mat.format(zz.getYwhdbl()*100)%>%</td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getYdzy1())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getYdzy2())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getGwzy())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getGygtzy())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getYdgtzy())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num><%=mat.format(zz.getBkftzy())%></td>
						<td class=xl25 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=mat.format(zz.getYwbl()*100)%>%</td>
 				</tr>
         <%
         }}}}
         %>


</table>
 <tr height=38 style='height:28.5pt;mso-xlrowspan:2'>
  <td height=38 colspan=5 style='height:28.5pt;mso-ignore:colspan'></td>
 </tr>
  
 <![endif]>
</table>

</body>

</html>
