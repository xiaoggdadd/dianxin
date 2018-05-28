<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="java.text.*,com.noki.util.CTime"%>
<%@ page import="java.util.regex.Pattern"%>
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
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>站点名称</td>  
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>所在地区</td>  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>实际电费金额</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>额定电费</td>
    <td class=xl25 width=72 style='border-left:none;width:54pt'>额定耗电量(度/天)</td>
    <td class=xl25 width=72 style='border-left:none;width:54pt'>实际用电量</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>电量超标比例</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>全省定标电量超标比例</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>结算周期</td>
  <td class=xl25 width=165 style='border-left:none;width:124pt'>报账月份</td>
  <td class=xl25 width=165 style='border-left:none;width:124pt'>站点属性</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>站点类型</td>
  
   <td class=xl25 width=72 style='border-left:none;width:54pt'>电费支付类型</td>   
   <td class=xl25 width=72 style='border-left:none;width:54pt'>线损类型</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>线损值</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>直流负荷</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>交流负荷</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>倍率</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>票据类型</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>票据编号</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>票据金额</td>
        
   <td class=xl25 width=72 style='border-left:none;width:54pt'>自动审核状态</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>人工审核状态</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>二级审核状态</td>
   <td class=xl25 width=152 style='border-left:none;width:114pt'>电表ID</td>
 </tr>
 <%   
         String whereStr = request.getParameter("whereStr");	  
         String zdname=request.getParameter("zdname");
				//whereStr= java.net.URLDecoder.decode(whereStr,"UTF-8");
			if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
				whereStr=whereStr+" and zd.JZNAME like '%"+zdname+"%'";
				
			}
			 String lrrq=request.getParameter("lrrq");
				//whereStr= java.net.URLDecoder.decode(whereStr,"UTF-8");
			if(lrrq != null && !lrrq.equals("")&& !lrrq.equals("null")){
				whereStr=whereStr+" and to_char(ef.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
				
			}
       	 System.out.println("StationPointQueryBean-whereStr:"+whereStr);
         //StationPointQueryBean bean = new StationPointQueryBean();
         if(whereStr==null){
         whereStr="";
         }
         
        
         ElectricFeesBean bean=new ElectricFeesBean();
          List<ElectricFeesFormBean> list=bean.getPageDataCheckCity(whereStr,loginId);
          //bean.getPageDataCheckCitydaochu(whereStr,loginId);
       	 ArrayList fylist = new ArrayList();
     	String electricfeeId = "",
		floatpay = "", actualpay = "",bzyf="",blhdl="",notetypeid = "",szdq="",noteno = "",pjje="",cbbl="",dfzflx = "";
		String jzname = "",thisdatatime="",edhdl,lastdatatime="",dbid="",autoauditstatus = "",manualauditstatus = "",cityaudit="";
		Double df=0.00;
		 double sjdf=0.00;
		
		//=================新增==========
		String zdsxx="";
		String zdlxx="";
		String unitprice = "",linelosstype="",linelossvalue="",beilv="",dedhdl="",zq="",zlfh="",jlfh="",dfzflx2="";
		String qsdbdl="";//全省定标电量
		double dbdl=0.00;
		
		//===============xin===========
		 int countt=0;
		//int intnum=xh = pagesize*(curpage-1)+1;
		for(ElectricFeesFormBean listt:list){
            
		     //num为序号，不同页中序号是连续的
		     jzname=listt.getJzname();
		     jzname = jzname != null ? jzname : "";	
	         bzyf=listt.getAccountmonth();	     
		     bzyf = bzyf != null ? bzyf : "";	
		   zdsxx=listt.getProperty();  
		    zdsxx = zdsxx != null ? zdsxx : "";
		   
		     szdq = listt.getSzdq();
		     szdq = szdq != null ? szdq : "";	
		     
		     dedhdl = listt.getDedhdl();
		     dedhdl = dedhdl != null ? dedhdl : "";	
		     
		     zlfh = listt.getZlfh();
		     zlfh = zlfh != null ? zlfh : "";	
		     
		     jlfh = listt.getJlfh();
		     jlfh = jlfh != null ? jlfh : "";	
		     
		      cbbl = listt.getCbbl();
		     cbbl = cbbl != null ? cbbl : "0";	
		     
		     
		      zdlxx = listt.getStationtype();
		     zdlxx = zdlxx != null ? zdlxx : "";
		     
		     
		     	
		      
		      dbid =listt.getDbid(); 
		     dbid = dbid != null ? dbid : "";	
		     qsdbdl=listt.getQsdbdl();
		     if(qsdbdl==null||qsdbdl==" "||qsdbdl=="null"||"null".equals(qsdbdl)){
		     	qsdbdl="";
		     }
		     
		     
		     autoauditstatus =listt.getAutoauditstatus();
		     autoauditstatus = autoauditstatus != null ? autoauditstatus : "";
		     
             manualauditstatus = listt.getManualauditstatus();
		     manualauditstatus = manualauditstatus != null ? manualauditstatus : "";
		     
		     dfzflx2 = listt.getDfzflx(); 
		     dfzflx2 = dfzflx2 != null ? dfzflx2 : "";
		     
		     electricfeeId = listt.getAmmererid();	
		     electricfeeId = electricfeeId != null ? electricfeeId : "";
			 
			 			  //添加 单价  线损类型   线损值   倍率
			 //unitprice = (String)fylist.get(k+fylist.indexOf("UNITPRICE"));
			 //unitprice = unitprice != null ? unitprice : "";
			 
			 unitprice=listt.getUnitprice();
			 if(unitprice==null||unitprice==""||unitprice=="o"||unitprice=="null"||"null".equals(unitprice)){
		             	unitprice="0";
		             }
			 
			 linelosstype = listt.getLinelosstype();
             linelosstype = linelosstype != null ? linelosstype : "";
			 if(linelosstype.equals("null")){
				linelosstype="";
			 }
			 
			 linelossvalue =listt.getLinelossvalue();
			 linelossvalue = linelossvalue != null ? linelossvalue : "";
			 if(linelossvalue.equals("null")){
				linelossvalue="";
			 }
			 
			 beilv = listt.getBeilv();
			 beilv = beilv != null ? beilv : "";
			 if(beilv==null||beilv==""||beilv=="o")beilv="0";
			 DecimalFormat ma=new DecimalFormat("0.00");
             df=Double.parseDouble(beilv);
             beilv=ma.format(df);
			 
		     floatpay = listt.getFloatpay();
		     floatpay = floatpay != null ? floatpay : "";
		     
		     zq=listt.getJszq();
		     zq=zq != null ? zq : "";
		     
		     actualpay = listt.getActualpay();
		     actualpay = actualpay != null ? actualpay : "";
		     
		     notetypeid = listt.getNotetypeid();
		     notetypeid = notetypeid != null ? notetypeid : "";
		     
			 noteno = listt.getNoteno();
			 noteno = noteno != null ? noteno : "";	
			 if(noteno.equals("null")){
				noteno="";
			 }	
			 pjje=listt.getPjjedf();
			 pjje = pjje!=null?pjje:"";
			 if(pjje.equals("null")){
					pjje="";
				}
			  blhdl=listt.getBlhdl();
		   if(blhdl==null||blhdl==""||blhdl=="o"||"null".equals(blhdl))blhdl="0";
			 
		     
		     cityaudit = listt.getCityaudit();
		     cityaudit = cityaudit != null ? cityaudit : "";
		     
             DecimalFormat mat=new DecimalFormat("0.00");
             if(actualpay==null||actualpay=="")actualpay="0";
             df=Double.parseDouble(actualpay);
             actualpay=mat.format(df);
             sjdf=df+sjdf;
             countt++;
             
             if("".equals(cbbl)||"null".equals(cbbl)){
		       cbbl="0";
		     }
		      double cb=0;
             cb=Double.parseDouble(cbbl);
             cb=cb*100;
             cbbl=ma.format(cb);
             
             
             if(autoauditstatus!=null&&autoauditstatus.equals("1")){
       		     autoauditstatus="通过";
       	  }else{
                 autoauditstatus="未通过";	
       			}
            	 if(manualauditstatus!=null&&manualauditstatus.equals("1")){manualauditstatus="通过";}else{manualauditstatus="不通过";}    
             if(cityaudit!=null&&cityaudit.equals("1")){cityaudit="通过";}else{cityaudit="不通过";}
             edhdl=listt.getEdhdl();
               if("".equals(edhdl)||"null".equals(edhdl)||edhdl==null){
		       edhdl="0";
		     }
               thisdatatime=listt.getThisdatetime();
		   lastdatatime=listt.getLastdatetime();
              SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    			     String dfsl="0";
    			       double shu1;
    			     double sjydl;
    			     double dd;
    			     String dlbl="0";
    			     double dbdl1;
    			     double shu;
    			     long quot;
			     if((lastdatatime!=null&&!"0".equals(lastdatatime)&&!"null".equals(lastdatatime)&&!lastdatatime.equals("")&&lastdatatime!="")&&(thisdatatime!=null&&!"0".equals(thisdatatime)&&!"null".equals(thisdatatime)&&!thisdatatime.equals("")&&thisdatatime!="")){
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
        	         	shu=Double.parseDouble(edhdl)*Double.parseDouble(unitprice)*quot;//计算额定电费
        	        	DecimalFormat eddf =new DecimalFormat("0.00");
                     	dfsl=eddf.format(shu);//格式化额定电费
                     		shu1=Double.parseDouble(edhdl)*quot;// 额定电量
        	         	sjydl=Double.parseDouble(blhdl);// 实际用电量
        	         //	if(shu1!=0){
        	         	//dd=((sjydl-shu1)/shu1)*100;
        	         	///}else{
        	         //	dd=0;
        	         //	}
        	         	 DecimalFormat we =new DecimalFormat("0.00");
        	         	 	 if(!"".equals(qsdbdl)){
        	         	    dbdl1=Double.parseDouble(qsdbdl)*quot;//全省定标电量
        	         		dbdl=((sjydl-dbdl1)/dbdl1)*100;
        	         		qsdbdl=dbdl+"";
        	         		qsdbdl=we.format(Double.parseDouble(qsdbdl));
        	         		
        	         	}
			
			//  dlbl=we.format(dd);
                     	
                     	
		           		}
		      		}
		      		}else{
		      		 dfsl="0.00";
		      		}	
		      		DecimalFormat ww =new DecimalFormat("0.0");
		      		 edhdl=ww.format(Double.parseDouble(edhdl));
			 String color=null;
				if(notetypeid.equals("pjlx05")){
					notetypeid="收据";	
				}else if(notetypeid.equals("pjlx03")){
					notetypeid="发票";
				}else{
					notetypeid="";
				}
				if(zdsxx.equals("zdsx01")){
					zdsxx="局用机房";
				   }else if(zdsxx.equals("zdsx02")){
					   zdsxx="基站";
				   }else if(zdsxx.equals("zdsx03")){
					   zdsxx="营业网点";
				   }else if(zdsxx.equals("zdsx04")){
					   zdsxx="其他";
				   }else if(zdsxx.equals("zdsx05")){
					   zdsxx="接入网";
				   }else if(zdsxx.equals("zdsx06")){
					   zdsxx="乡镇支局";
				   }else{
					   zdsxx="";
				   }           
	           		if(linelosstype.equals("02xsbl")){
	           			linelosstype="线损比例";
	           		}else if(linelosstype.equals("01xstz")){
	           			linelosstype="线损调整";
	           		}else{
	           			linelosstype="";
	           		}
	           	if(dfzflx2.equals("dfzflx01")){
	   		    	 dfzflx="月结";
	   		     }else if(dfzflx2.equals("dfzflx02")){
	   		    	 dfzflx="合同";
	   		     }else if(dfzflx2.equals("dfzflx03")){
	   		    	 dfzflx="预支";
	   		     }else if(dfzflx2.equals("dfzflx04")){
	   		    	 dfzflx="插卡";
	   		     }else{
	   		      	 dfzflx="";
	   		     }
	   		     if(blhdl.equals("0")){
	   		     	blhdl="";
	   		     }
		
       %> 
    
  <tr height=19 style='height:14.25pt'>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>	
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=szdq%></td>  
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=actualpay%></td>
	  
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dfsl%></td>
	 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=edhdl%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=blhdl%></td>
	 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=dedhdl%>%</td>
	 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=qsdbdl%>%</td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=zq%></td>
	 <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=bzyf%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdsxx%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdlxx%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dfzflx%></td>  
	  
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=linelosstype%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=linelossvalue%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zlfh%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jlfh%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=beilv%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=notetypeid%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=noteno%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=pjje%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=autoauditstatus%></td>
	 
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=manualauditstatus%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=cityaudit%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=dbid%></td>
 
 </tr>
         <%
         }
         %>
        
  <tr> 
  	<td colspan="17">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=countt%>条；</font></b>
							 <font color='#000080'>&nbsp;实际电费金额总计:</font>
							 <b><font color=red>&nbsp;<%=(new DecimalFormat("0.00")).format(sjdf)%>元</font></b>
	</div>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
