<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.ElectricFeesQueryBean" %>
<%@ page import="java.sql.ResultSet,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.regex.Pattern"%>
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
        String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";

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
<jsp:useBean id="bean" scope="page" class="com.noki.query.basequery.javabean.ElectricFeesQueryBean">
</jsp:useBean>


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
   <td class=xl25 width=72 style='border-left:none;width:54pt'>区县</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>乡镇</td>
  <td class=xl25 width=152 style='border-left:none;width:114pt'>站点名称</td>  
    <td class=xl25 width=152 style='border-left:none;width:114pt'>电表名称</td>  
    <td class=xl25 width=72 style='border-left:none;width:54pt'>电费支付类型</td>
  
  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>报账月份</td>
    <td class=xl25 width=72 style='border-left:none;width:54pt'>财务月份</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>供电方式</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>起始电表数</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>结束电表数</td>
  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>上次抄表时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>本次抄表时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>折算后用电量</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>用电量调整</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>（电量）备注</td>
    <td class=xl25 width=72 style='border-left:none;width:54pt'>额定耗电量</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>起始月份</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>结束月份</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>本次单价</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>电费调整</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>（电费）备注</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>实际电费</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>结算周期</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>额定电费</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>与额定电费差</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>与额定电费比值</td>
   
  <td class=xl25 width=72 style='border-left:none;width:54pt'>直流负荷</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>直流负荷估算电量</td>
    <td class=xl25 width=72 style='border-left:none;width:54pt'>交流负荷</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>录入时间</td>  
<td class=xl25 width=72 style='border-left:none;width:54pt'>录入人员</td>
<td class=xl25 width=72 style='border-left:none;width:54pt'>自动审核状态</td>
<td class=xl25 width=72 style='border-left:none;width:54pt'>人工审核状态</td>
<td class=xl25 width=72 style='border-left:none;width:54pt'>人工审核时间</td>
<td class=xl25 width=72 style='border-left:none;width:54pt'>人工审核员</td>

<td class=xl25 width=72 style='border-left:none;width:54pt'>市级审核状态</td>
<td class=xl25 width=72 style='border-left:none;width:54pt'>市级审核时间</td>
<td class=xl25 width=72 style='border-left:none;width:54pt'>市级审核员</td>
<td class=xl25 width=72 style='border-left:none;width:54pt'>财务审核状态</td>
<td class=xl25 width=72 style='border-left:none;width:54pt'>财务审核时间</td>

<td class=xl25 width=72 style='border-left:none;width:54pt'>财务审核员</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>倍率</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>票据时间</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>开票时间</td>

 <td class=xl25 width=72 style='border-left:none;width:54pt'>站点类型</td> 

  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>是否标杆</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>站点别名</td>

<td class=xl25 width=72 style='border-left:none;width:54pt'>流程号</td>
<td class=xl25 width=100 style='border-left:none;width:54pt'>票据编号</td>
<td class=xl25 width=72 style='border-left:none;width:54pt'>票据类型</td>

<td class=xl25 width=72 style='border-left:none;width:54pt'>交费时间</td>
<td class=xl25 width=72 style='border-left:none;width:54pt'>缴费操作员</td>
<td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>站点ID</td>
<td class=xl25 width=174 style='border-left:none;width:131pt'>电表ID</td>

<td class=xl25 width=174 style='border-left:none;width:131pt'>原站点ID</td>

<td class=xl25 width=174 style='border-left:none;width:131pt'>自报电用户号</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>生产费用</td>
    <td class=xl25 width=72 style='border-left:none;width:54pt'>办公费用</td>
    <td class=xl25 width=72 style='border-left:none;width:54pt'>营业费用</td>
    <td class=xl25 width=72 style='border-left:none;width:54pt'>建设费用</td>
    <td class=xl25 width=72 style='border-left:none;width:54pt'>信息化费用</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>代垫电费</td>

 </tr>
 <%   
         int curpage = Integer.parseInt(request.getParameter("curpage"));  
         String whereStr = request.getParameter("whereStr");	 
         String zdname=request.getParameter("zdname");
         if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
				whereStr=whereStr+" AND ZD.JZNAME LIKE '%"+zdname+"%'";
		}
         String dbname=request.getParameter("dbname");
         if(dbname != null && !dbname.equals("") && !dbname.equals("0")){
				whereStr=whereStr+" AND D.DBNAME LIKE '%"+dbname+"%'";
		}
         if(whereStr==null) whereStr="";  
         String bzw="";
   		 if(!xiaoqu.equals("0")||!"".equals(xiaoqu)){
   		 bzw="1";
   	 }
       	 List<ElectricFeesFormBean> fylist = bean.getPageData(curpage,whereStr,loginId,bzw);
       	 //allpage=bean.getAllPage();
		String lastdegree = "",
		 thisdegree = "",
		 paydatetime = "";
		 String dbid="";//电表id
		String szdq="";//所属地区
		String dfzflx="";//电费支付类型
		String jtbblx="";//集团报表类型
		String bgsign="";//是否标杆
		String bieming="";//站点别名
		String dllx="";//电流类型
		
		
		String dbyt="";//电表用途
		String csds="";//初始读数
		String cssytime="";//初始使用时间
		String bl="";//倍率
		String lastdatatime="";//上次抄表时间
		String thisdatatime="";//本次抄表时间
		String ydltz="";//用电量调整
		String cbczy="";//抄表操作员
		String zshydl="";
		String dqsj="";
		String dlbz="";
		String pjsj="";
		String bzyf="";
		String kpsj="";
		String dfbz="";
		String startmonth="";
	   String endmonth="";
	   String danjia="";
	   String dftiaozheng="";
	   String dianfei="";
		String pjbianhao="",yid="",gdfs="";
		String zdlx="";
		    String lrry="";
		    String lrsj="";
		    //市级审核时间、市级审核人员、财务审核状态 ef.ADMINISTRATIVEDF,ef.NETWORKDF,ef.MARKETDF,ef.BUILDDF,ef.INFORMATIZATIONDF
		String jszq="";//结算周期
		 String  administrativedf="",networkdf="",marketdf="",builddf="",information="",dddfdf="";
		String sjtime="";
		String sjshy="";
		String cwzt="";
		String kjyf="";
		//直流负荷
		String jlfh="";
		String zlfh="";
		String zlfhgs="";//直流负荷估算电量
		  //共计条数 count,实际用电量sjydl  actualdegree,实际费用sjfy dianfei
		  int count=0;
		  double sjydl=0.0;
		  double sjfy=0.00;
		String xianname = "";//区县  
		String xiaoquname = "";//乡镇
		 //=================
		String jzname = "",jzcode = "",pjleixing="",caozuoyuan="",autoauditstatus="",manualauditstatus="",manualauditdatetime="",manualauditname="",financialoperator="",financialdatetime="",cityaudit="",edhdl = "";
		//int intnum=xh = pagesize*(curpage-1)+1;
		String liuchenghao,dbzbdyhh="";
		
		String dbmc = "";//电表名称
		
		double df=0.0;
		 if(fylist!=null){
			for(ElectricFeesFormBean bean1:fylist){
              
          sjshy = bean1.getCityauditpensonnel();
          sjshy = sjshy!=null?sjshy:"";
          if(sjshy.equals("null")){
          	sjshy="";
          }       
           
          sjtime = bean1.getCityaudittime();
          sjtime=sjtime!=null?sjtime:"";
	      if(sjtime=="0"||sjtime.equals("0")||sjtime.equals("null")){
	      	sjtime="";
	      }

		     //num为序号，不同页中序号是连续的			     
		    jzcode = bean1.getZdcode();
		    jzcode = jzcode != null ? jzcode : "";
		    
		    dbzbdyhh = bean1.getDbzbdyhh();
		    dbzbdyhh = dbzbdyhh != null ? dbzbdyhh : "";
		    
		    lrry = bean1.getEntrypensonnel();
		    lrry=lrry!=null?lrry:"";
		    if("null".equals(lrry)){
		    lrry="";
		    }
		     
		     lrsj = bean1.getEntrytime();
		     lrsj=lrsj!=null?lrsj:"";
		    if(lrsj=="0"||lrsj.equals("0")||lrsj.equals("null")){
		      lrsj="";
		    }
		    
		    //新加
		    edhdl = bean1.getEdhdl();//额定耗电量
		    edhdl = edhdl != null ? edhdl : "0";
		    if(edhdl==null||edhdl==""||edhdl.equals("")||"o".equals(edhdl))edhdl="0";
		    
		    jzname = bean1.getJzname();
		    jzname = jzname != null ? jzname : "";
		    
		    dbmc = bean1.getDbname();
		    dbmc = dbmc != null ? dbmc : "";
		    			
		    dbid = bean1.getDbid();
		    dbid = dbid != null ? dbid : "";
		    
		    yid = bean1.getYid();
		    yid = yid != null ? yid : "";
		    	
		    szdq = bean1.getSzdq();
		    szdq = szdq != null ? szdq : "";

		    xianname = bean1.getXian();
		    xianname = xianname != null ? xianname : "";
		    
		    xiaoquname = bean1.getXiaoqu();
		    xiaoquname = xiaoquname != null ? xiaoquname : "";
		    			    	
		    dfzflx = bean1.getDfzflx();
		    dfzflx = dfzflx != null ? dfzflx : ""; 			
		    
		    zdlx = bean1.getStationtype();
		    zdlx = zdlx != null ? zdlx : ""; 	
		    jlfh=bean1.getJlfh();//交流负荷
			if("null".equals(jlfh)||"".equals(jlfh)||jlfh==null)jlfh="0";   	
		    			    	
		    jtbblx = bean1.getJztype();
		    jtbblx = jtbblx != null ? jtbblx : "";
		   
		    bgsign = bean1.getBgsign();
		    bgsign = bgsign != null ? bgsign : ""; 
		    if("0".equals(bgsign)){
		    bgsign="否";
		    }else if("1".equals(bgsign)){
		    bgsign="是";
		    }
		    
		    bieming = bean1.getBieming();
		    bieming = bieming != null ? bieming : ""; 
		    			    	
		    dllx = bean1.getDllx();
		    dllx = dllx != null ? dllx : ""; 
			if("null".equals(dllx)){
				dllx="";
			}			    	
		    //=============================			    	
		    dbyt = bean1.getDbyt();
		    dbyt = dbyt != null ? dbyt : ""; 
		    
		    csds = bean1.getCsds();
		    csds = csds != null ? csds : ""; 
		    
		    cssytime = bean1.getCssytime();
		    cssytime = cssytime != null ? cssytime : ""; 
		    if(cssytime=="0"||cssytime.equals("0")||cssytime.equals("null")){
		      cssytime="";
		    }
		    
		    bl = bean1.getBeilv();
		    bl = bl != null ? bl : ""; 
		    if(bl.equals("")||bl.equals("null")||bl.equals("o")){
			  bl="0.00";
			}
		     DecimalFormat aa=new DecimalFormat("0.00");
            bl = aa.format(Double.parseDouble(bl));
		    
		    lastdatatime = bean1.getLastdatetime();
		    lastdatatime = lastdatatime != null ? lastdatatime : ""; 
		    if(lastdatatime=="0"||lastdatatime.equals("0")||lastdatatime.equals("null")){
		      lastdatatime="";
		    }
		    
		//  String  administrativedf="",networkdf="",marketdf="",builddf="",information="";
		// //市级审核时间、市级审核人员、财务审核状态 ef.ADMINISTRATIVEDF,ef.NETWORKDF,ef.MARKETDF,ef.BUILDDF,ef.INFORMATIZATIONDF
		   
		//行政综合线耗电量（办公）
		administrativedf = bean1.getBgdf();
		    administrativedf = administrativedf != null ? administrativedf : "0"; 
		    if(administrativedf==null||administrativedf.equals("")||administrativedf.equals("null")){
		      administrativedf="0";
		    }
		    administrativedf=aa.format(Double.parseDouble(administrativedf));
		//    网络运营线耗电量（生产）
		   networkdf = bean1.getScdff();
		    networkdf = networkdf != null ? networkdf : "0"; 
		    if(networkdf==null||networkdf.equals("")||networkdf.equals("null")){
		      networkdf="0";
		    }
		    networkdf=aa.format(Double.parseDouble(networkdf));
		    //市场营销线耗电量（营业）
		      marketdf = bean1.getYydf();
		    marketdf = marketdf != null ? marketdf : "0"; 
		    if(marketdf==null||marketdf.equals("")||marketdf.equals("null")){
		      marketdf="0";
		    }
		    marketdf=aa.format(Double.parseDouble(marketdf));
		    //建设投资线耗电量
		      builddf = bean1.getJstzdf();
		    builddf = builddf != null ? builddf : "0"; 
		    if(builddf==null||builddf.equals("")||builddf.equals("null")){
		      builddf="0";
		    }
		    builddf=aa.format(Double.parseDouble(builddf));
		   // 信息化支撑线耗电量
		      information = bean1.getXxhdf();
		    information = information != null ? information : "0"; 
		    if(information==null||information.equals("")||information.equals("null")){
		      information="0";
		    }   
		    information=aa.format(Double.parseDouble(information));
		   // 代垫电费 
		    dddfdf = bean1.getDddfdf();
		    dddfdf = dddfdf != null ? dddfdf : "0"; 
		    if(dddfdf==null||dddfdf.equals("")||dddfdf.equals("null")){
		      dddfdf="0";
		    }   
		    dddfdf=aa.format(Double.parseDouble(dddfdf));
		 //========================================================   
		    
		    
		    thisdatatime = bean1.getThisdatetime();
		    thisdatatime = thisdatatime != null ? thisdatatime : ""; 
		    if(thisdatatime=="0"||thisdatatime.equals("0")||thisdatatime.equals("null")){
		      thisdatatime="";
		    }
		    
		    ydltz = bean1.getFloatdegree();
		    ydltz = ydltz != null ? ydltz : ""; 
		    
		    cbczy = bean1.getInputoperator();
		    cbczy = cbczy != null ? cbczy : "";  
		    if("null".equals(cbczy)){
		      cbczy="";
		    }
		    
		   
		 //=============================
		  //=====================pm===========
		    zshydl = bean1.getBlhdl();
		    zshydl = zshydl != null ? zshydl : ""; 
		    
		    dqsj = bean1.getInputdatetime();
		    dqsj = dqsj != null ? dqsj : "";
		    if(dqsj=="0"||dqsj.equals("0")||dqsj.equals("null")){
		      dqsj="";
		    }   
		    
		    dlbz = bean1.getMemo();
		    dlbz = dlbz != null ? dlbz : "";  
		    
		    pjsj = bean1.getNotetime();
		    pjsj = pjsj != null ? pjsj : ""; 
		    if(pjsj=="0"||pjsj.equals("0")||pjsj.equals("null")){
		      pjsj="";
		    }   
		    
		    bzyf = bean1.getAccountmonth();
		    bzyf = bzyf != null ? bzyf : "";
		    if(bzyf=="0"||bzyf.equals("0")||bzyf.equals("null")){
		      bzyf="";
		    }    	    	
		    			    	
		     kpsj = bean1.getKptime();
		    kpsj = kpsj != null ? kpsj : ""; 
		    if(kpsj=="0"||kpsj.equals("0")||kpsj.equals("null")){
		      kpsj="";
		    }   
		    
		    dfbz = bean1.getMemoam();
		    dfbz = dfbz != null ? dfbz : ""; 			    	
		    			    	
		    startmonth = bean1.getStartmonth();
		    startmonth = startmonth != null ? startmonth : "";
		    if(startmonth=="0"||startmonth.equals("0")||startmonth.equals("null")){
		      startmonth="";
		    }
		    
		    endmonth = bean1.getEndmonth();
		    endmonth = endmonth != null ? endmonth : "";
		    if(endmonth=="0"||endmonth.equals("0")||endmonth.equals("null")){
		      endmonth="";
		    }			    	
		    
		  	//直流负荷
		    zlfh = bean1.getZlfh();
		    zlfh = zlfh != null ? zlfh : "0";
		    kjyf=bean1.getKjyf();
			if("null".equals(kjyf)||"".equals(kjyf)||kjyf==null)kjyf="";			    	
		    danjia = bean1.getUnitprice();
		    danjia = danjia != null ? danjia : "0";
		    if("".equals(danjia)||"null".equals(danjia)||"o".equals(danjia)){
		     danjia="0";
		    }
		    			    	
		    dftiaozheng = bean1.getFloatpay();
		    dftiaozheng = dftiaozheng != null ? dftiaozheng : "";
		    
			dianfei = bean1.getActualpay();
			dianfei = dianfei != null ? dianfei : "";
		    
		    
		    liuchenghao = bean1.getLiuchenghao();
		    liuchenghao =liuchenghao!=null ? liuchenghao : "";
		    
		    gdfs = bean1.getGdfs();
		    gdfs =gdfs!=null ? gdfs : "";
		    
		     //计算额定电费
    			     SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    			     String dfsl="0";
    			     double shu;
    			     double zlfhdl;
    			     long quot;
    			 if((lastdatatime!=null&&!lastdatatime.equals("")&&lastdatatime!="")&&(thisdatatime!=null&&!thisdatatime.equals("")&&thisdatatime!="")){
    			      String startn=lastdatatime.substring(0,4);
    			      String endn=thisdatatime.substring(0,4);
    			      String sgang=lastdatatime.substring(4,5);
    			      String egang=thisdatatime.substring(4,5);
    			      
    			      Pattern pattern = Pattern.compile("[0-9]*");//判断前四位是否是数字
    			      if( pattern.matcher(startn).matches()==true&&pattern.matcher(endn).matches()==true&&sgang.equals("-")&&egang.equals("-")){
    			     	
    			     	  if(lastdatatime.length()>=8&&thisdatatime.length()>=8){
    			     	    Date date1 = ft.parse( lastdatatime );
        	         		Date date2 = ft.parse( thisdatatime );
        	         		quot = date2.getTime() - date1.getTime();
        	         		quot = quot/1000/60/60/24+1;//计算天数
        	         		shu=Double.parseDouble(edhdl)*Double.parseDouble(danjia)*quot;//计算额定电费
        	        		DecimalFormat eddf =new DecimalFormat("0.00");
                     		dfsl=eddf.format(shu);//格式化额定电费
                     		if(zlfh!=null&&!zlfh.equals("")&&zlfh!=""){
                     		zlfhdl=Double.parseDouble(zlfh)*54*quot;//直流负荷估算电量
                     		zlfhgs=eddf.format(zlfhdl);//格式化
                     		}else{
                     		zlfhgs="";
                     		}                   	
		           		}
		      		}
		      		}
			
			pjbianhao = bean1.getNoteno();
		    pjbianhao = pjbianhao != null ? pjbianhao : "";
			
			 pjleixing = bean1.getNotetypeid();
		    pjleixing = pjleixing != null ? pjleixing : "";
			
			caozuoyuan = bean1.getPayoperator();
		    caozuoyuan = caozuoyuan != null ? caozuoyuan : "";
		    		    		    
		   	//直流负荷
		   // zlfh = bean1.getZlfh();
		  //  zlfh = zlfh != null ? zlfh : "";
		    
		    //结算周期
		   	jszq = bean1.getJszq();
		    jszq = jszq != null ? jszq : "";		    
		    
		    autoauditstatus = bean1.getAutoauditstatus();
		    autoauditstatus = autoauditstatus != null ? autoauditstatus : "";
		    
		     manualauditstatus = bean1.getManualauditstatus();
		    manualauditstatus = manualauditstatus != null ? manualauditstatus : "";
		    if("0".equals(manualauditstatus)){
		        manualauditstatus="未通过";
		        cwzt="未通过";
		    }
		    if("1".equals(manualauditstatus)){
		    manualauditstatus="通过";
		    cwzt="未通过";
		    }
		    if("2".equals(manualauditstatus)){
		    cwzt=manualauditstatus;
		      manualauditstatus="通过";
		    }
		    if("-1".equals(manualauditstatus)){
		    cwzt=manualauditstatus;
		    manualauditstatus="未通过";
		    }
		  
		    
		    manualauditdatetime = bean1.getManualauditdatetime();
		    manualauditdatetime = manualauditdatetime != null ? manualauditdatetime : "";
		    if(manualauditdatetime=="0"||manualauditdatetime.equals("0")||manualauditdatetime.equals("null")){
		      manualauditdatetime="";
		    }
		    
		    manualauditname = bean1.getManualauditname();
		    manualauditname = manualauditname != null ? manualauditname : "";
		    
		    
		    financialoperator = bean1.getFinancialoperator();
		    financialoperator = financialoperator != null ? financialoperator : "";
		    
		    financialdatetime = bean1.getFinancialdatetime();
		    if(financialdatetime=="0"||financialdatetime.equals("0")||financialdatetime.equals("null")){
				financialdatetime="";
			}else{
		    	financialdatetime = financialdatetime != null ? financialdatetime : "";
			}
		    cityaudit = bean1.getCityaudit();
		    cityaudit = cityaudit != null ? cityaudit : "";
		    			    
			lastdegree = bean1.getLastdegree();
			lastdegree = lastdegree != null ? lastdegree : "";
			
		    thisdegree = bean1.getThisdegree();
		    thisdegree = thisdegree != null ? thisdegree : "";
		    
		     paydatetime = bean1.getPaydatetime();
		    if(paydatetime=="0"||paydatetime.equals("0")||paydatetime.equals("null")){
				paydatetime="";
			}else{
		    paydatetime = paydatetime != null ? paydatetime : "";
			}	    
		    
		    if(lastdegree.equals("")||lastdegree.equals("null")||lastdegree.equals("o")){
		       lastdegree="0";
		     }
		    DecimalFormat la =new DecimalFormat("0.0");
            lastdegree=la.format(Double.parseDouble(lastdegree));
            
			if(thisdegree.equals("")||thisdegree.equals("null")||thisdegree.equals("o")){
		       thisdegree="0";
		     }
			DecimalFormat th =new DecimalFormat("0.0");
            thisdegree=th.format(Double.parseDouble(thisdegree));
			 
			
			
			
			if(ydltz.equals("")||ydltz.equals("null")||ydltz.equals("o")){
			  ydltz="0.0";
			}
		     DecimalFormat fl=new DecimalFormat("0.0");
            ydltz = fl.format(Double.parseDouble(ydltz));
		    
		    if(zshydl.equals("")||zshydl.equals("null")||zshydl.equals("o")){
		     zshydl="0.0";
		    }
		    DecimalFormat ac=new DecimalFormat("0.0");
            zshydl = ac.format(Double.parseDouble(zshydl));
            sjydl =Double.parseDouble(zshydl)+sjydl;//实际读数
            
            if(dianfei.equals("")||dianfei.equals("null")||dianfei.equals("o")){
             dianfei="0.00";
            }
             DecimalFormat dfi =new DecimalFormat("0.00");
            dianfei=dfi.format(Double.parseDouble(dianfei));
           
           
            if(danjia.equals("")||danjia.equals("null")||danjia.equals("o")){
            danjia="0.0000";
            }
            DecimalFormat price=new DecimalFormat("0.0000");
            danjia = price.format(Double.parseDouble(danjia));
            
            if(dftiaozheng.equals("")||dftiaozheng.equals("null")||dftiaozheng.equals("o")){
            dftiaozheng="0.00";
            }
            DecimalFormat pay=new DecimalFormat("0.00");
            dftiaozheng = pay.format(Double.parseDouble(dftiaozheng));
		    
		    DecimalFormat mat =new DecimalFormat("0.00");
		    if(dianfei.equals("")||dianfei.equals("null")||null==dianfei||dianfei.equals("o")){
		    dianfei="0.0";
		    }
		    df=Double.parseDouble(dianfei);
		    dianfei=mat.format(df);
		    
		    
		    if(csds.equals("null")||csds.equals("o")){
			 csds="0.0";
			}
		    if(csds==null||csds.equals(""))csds="0";
		    DecimalFormat t = new DecimalFormat("0.0");
			csds = t.format(Double.parseDouble(csds));
			
			double cha=Double.parseDouble(dfsl)-Double.parseDouble(dianfei);//额定电费与实际电费的差
			DecimalFormat c =new DecimalFormat("0.00");
			edhdl=t.format(Double.parseDouble(edhdl));
			String ch = c.format(cha);
		
			double chaa=0;
			if(dfsl.equals("0.00")){
			chaa=0.00;
			}else{
			 chaa=(Double.parseDouble(dianfei)-Double.parseDouble(dfsl))/Double.parseDouble(dfsl)*100;
			}
			String chh=c.format(chaa);
	        //==============================
	        count++;//共计条数
	       
	          sjfy=sjfy+df;//电费总计
	              
		  //======================pm===========
		   if("2".equals(cwzt)){
		   cwzt="通过";
		   }
		   if(pjleixing.equals("pjlx05")){
				pjleixing="收据";	
			}else if(pjleixing.equals("pjlx03")){
				pjleixing="发票";
			}else{
				pjleixing="";
			}
			 if(jtbblx.equals("zdlx12")){
				 jtbblx="通信机房";
				}else if(jtbblx.equals("zdlx01")){
					jtbblx="IDC机房";
				}else if(jtbblx.equals("zdlx07")){
					jtbblx="接入网";
				}else if(jtbblx.equals("zdlx08")){
					jtbblx="基站";
				}else if(jtbblx.equals("zdlx19")){
					jtbblx="其他";
				}else{
					jtbblx="";
				}
			if(gdfs.equals("gdfs01")){
				gdfs="供电局";
			}else if(gdfs.equals("gdfs02")){
				gdfs="业主";
			}else if(gdfs.equals("gdfs03")){
				gdfs="其他";
			}else if(gdfs.equals("gdfs04")){
				gdfs="直供电";
			}else if(gdfs.equals("gdfs05")){
				gdfs="转供电";
			}else{
				gdfs="";
			}
			if(dbyt.equals("dbyt01")){
				dbyt="结算";
			}else if(dbyt.equals("dbyt02")){
				dbyt="采集";
			}else if(dbyt.equals("dbyt03")){
				dbyt="管理";
			}else{
				dbyt="";
			}
			if(dfzflx.equals("dfzflx01")){
 		    	 dfzflx="月结";
 		     }else if(dfzflx.equals("dfzflx02")){
 		    	 dfzflx="合同";
 		     }else if(dfzflx.equals("dfzflx03")){
 		    	 dfzflx="预支";
 		     }else if(dfzflx.equals("dfzflx04")){
 		    	 dfzflx="插卡";
 		     }else{
 		      	 dfzflx="";
 		     }
		   
			String color=null;
		
       %>
 <tr height=19 style='height:14.25pt'>
   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xianname%></td>
		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xiaoquname%></td>
		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>  
		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dbmc%></td>  
		   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dfzflx%></td>
		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=bzyf%></td> 
		   <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=kjyf%></td> 
		   <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=gdfs%></td> 
		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=lastdegree%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=thisdegree%></td>
   		  
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=lastdatatime%></td>
          <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=thisdatatime%></td>
          <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zshydl%></td>
          <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=ydltz%></td>
           <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dlbz%></td>
           <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=edhdl%></td>
          <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=startmonth%></td>
          
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=endmonth%></td>  		 
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=danjia%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dftiaozheng%></td>
   		   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dfbz%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dianfei%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jszq%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dfsl%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=ch%></td>
   		    <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=chh%>%</td>

   		
   		    
   		    
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zlfh%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zlfhgs%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jlfh%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=lrsj%></td>   		  
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=lrry%></td>
	  	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=autoauditstatus%></td>
	   	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=manualauditstatus%></td>
	      <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=manualauditdatetime%></td>
	      <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=manualauditname%></td>
	      
	       <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=cityaudit%></td>
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=sjtime%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=sjshy%></td>
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=cwzt%></td>
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=financialdatetime%></td>
   		 
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=financialoperator%></td>
          <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=bl%></td>		  
		 
		  
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=pjsj%></td>

   		   		
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=kpsj%></td>
   		
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdlx%></td>
		  
		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=bgsign%></td>
		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=bieming%></td>
   		 
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=liuchenghao%></td>
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=pjbianhao%></td>
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=pjleixing%></td>
   		 
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=paydatetime%></td>   		          
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=caozuoyuan%></td>   		 
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzcode%></td>
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dbid%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=yid%></td>
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dbzbdyhh%></td>
   		 
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=networkdf%></td>
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=administrativedf%></td>
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=marketdf%></td>
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=builddf%></td>
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=information%></td>
   		 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dddfdf%></td>
   		    
 </tr>

 
         <%
         }}
         %>
         <table border=1>
         
         <%
           DecimalFormat matt =new DecimalFormat("0.00");
           sjfy=Double.parseDouble(matt.format(sjfy));
         
         %>
 <tr> 
  	<td colspan="62">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=count%>条</font></b>

							<font color='#000080'>&nbsp;折算后用电量总和:</font>
							 <b><font color=red>&nbsp;<%=(new DecimalFormat("0.00")).format(sjydl)%>度</font></b>
							 
							 <font color='#000080'>&nbsp;实际电费总和:</font>
							 <b><font color=red>&nbsp;<%=(new DecimalFormat("0.00")).format(sjfy)%>元</font></b>
	</div>
  </tr>
</table>
 <tr height=38 style='height:28.5pt;mso-xlrowspan:2'>
  <td height=38 colspan=5 style='height:28.5pt;mso-ignore:colspan'></td>
 </tr>
  
 
 
 
 <![endif]>
</table>

</body>

</html>
