<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.ElectricFeesQueryBean,java.util.Calendar" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
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
        String shi            = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
		String xian           = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
		String xiaoqu         = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";

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
  <td class=xl25 width=152 style='border-left:none;width:114pt'>地区</td>  
  <td class=xl25 width=165 style='border-left:none;width:124pt'>站点名称</td>
  <td class=xl25 width=165 style='border-left:none;width:124pt'>站点属性</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>实际用电量</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>金额</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>起始月份</td>
  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>结束月份</td>
   <td class=xl25 width=72 style='border-left:none;width:54pt'>交费周期(天数)</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>单载频能耗</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>载频数量</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>有无2G设备</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>有无3G设备</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>直流负荷</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>交流负荷</td>
  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>站点类型</td>
  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>载扇数量</td>
  
  <td class=xl25 width=72 style='border-left:none;width:54pt'>报账月份</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>电表ID</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>站点ID</td>
 </tr>
 <%   
    String lastdatatime="",thisdatatime="";long quot=0;int mon=0;double baa=0;
 double d=0,d1=0;String s="";
 
         int curpage = Integer.parseInt(request.getParameter("curpage"));  
         String whereStr = request.getParameter("whereStr");	 
         String zdnamee=request.getParameter("zdname");
         String blhdl1=request.getParameter("blhdl1");
         String endTime1       = request.getParameter("endTime1") != null ? request.getParameter("endTime1") : "";
          String zdlx           = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"";
          String dbyt=request.getParameter("dbyt")!=null?request.getParameter("dbyt"):"";
         String ssd="",dds="",sdr="",dfdf="";
         if( shi != null && !shi.equals("") && !shi.equals("0")){
			dds=dds+" and zz.shi='"+shi+"'";
		}
		if(xian != null && !xian.equals("") && !xian.equals("0")){
			dds=dds+" and zz.xian='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
			dds=dds+" and zz.xiaoqu='"+xiaoqu+"'";
		}
         if(zdnamee != null && !zdnamee.equals("") && !zdnamee.equals("0")){
				whereStr=whereStr+" and zz.jzname like '%"+zdnamee+"%'";
			}
		 System.out.println("AmmeterDegreeBean-whereStr:"+whereStr);
		 if( endTime1 != null && endTime1 != "" && !endTime1.equals("0")){
				ssd = ssd+" and TO_CHAR(aa.ENDMONTH,'yyyy-mm')>='"+endTime1+"'";
				ssd = ssd+" and TO_CHAR(aa.ENDMONTH,'yyyy-mm')<='"+endTime1+"'";
				
			}
			if(zdlx!=null&&!zdlx.equals("")&& !zdlx.equals("0")){
		   dds=dds+" and zz.STATIONTYPE='"+zdlx+"'";
		}
		if(dbyt!=null&&!dbyt.equals("")&& !dbyt.equals("0")){
			  whereStr=whereStr+" and dd.dbyt='"+dbyt+"'";
		}
         ArrayList fylist = new ArrayList();
         if(whereStr==null) whereStr="";
          if(blhdl1.equals("")||null==blhdl1){
  blhdl1="";
  dfdf="";
  }else{
 dfdf="";
       }
         
         
       	  fylist = bean.getPageDatadc(whereStr,loginId,dfdf);
       int countxh=1;
       int count=0;
       int c=0;
		double act=0;
		double mm=0;
		 String xiaoquq="",ts="",accountm="",property="",dzpnh="",shiq="", zdnameee="",actualdegree="",money="",stmon="",enmon="",twoG="",thrG="",zlfh="",jlfh="",jzlx="",zpsl="",zssl="",dlid="",dbid="",zdid="";
		 if(fylist!=null)
		{		
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
            //=================
          xiaoquq =(String)fylist.get(k+fylist.indexOf("XIAOQU"));
          xiaoquq=xiaoquq!=null?xiaoquq:"";
          if("null".equals(xiaoquq)){
          xiaoquq="";
          }
          
          shiq =(String)fylist.get(k+fylist.indexOf("SHI"));
          shiq=shiq!=null?shiq:"";
          if("null".equals(shiq)){
          shiq="";
          }
            zdnameee=(String)fylist.get(k+fylist.indexOf("JZNAME"));
          zdnameee=zdnameee!=null?zdnameee:"";
          if("null".equals(zdnameee)){
          zdnameee="";
          }
          
          property=(String)fylist.get(k+fylist.indexOf("PROPERTY"));
          property=property!=null?property:"";
          if("null".equals(property)){
        	  property="";
          }
          
          //==耗电量
          actualdegree=(String)fylist.get(k+fylist.indexOf("BLHDL"));
          actualdegree=actualdegree!=null?actualdegree:"0";
          if("null".equals(actualdegree)||"".equals(actualdegree)||null==actualdegree){
          actualdegree="0";
          }
          
		    money = (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));
		    money = money != null ? money : "0";
		    if("".equals(money)||"null".equals(money)){
		    money="0";
		    }
		  
		 //   ts = (String)fylist.get(k+fylist.indexOf("TS"));
		    ts = ts != null ? ts : "0";
		    if("".equals(ts)||"null".equals(ts)||null==ts){
		    ts="0";
		    }
		  
		  
		    stmon=(String)fylist.get(k+fylist.indexOf("STARTMONTH"));
			stmon=stmon!=null?stmon:"";
		    enmon = (String)fylist.get(k+fylist.indexOf("ENDMONTH"));
		    enmon = enmon != null ? enmon : "";
		    twoG=(String)fylist.get(k+fylist.indexOf("G2"));
		    if(twoG.equals("1")){
		     twoG="有";
		    }else{
		      twoG="无";
		    }
		   
		    thrG=(String)fylist.get(k+fylist.indexOf("G3"));
		   if(thrG.equals("1")){
		     thrG="有";
		    }else{
		      thrG="无";
		    }
		    //新加
		     zlfh = (String)fylist.get(k+fylist.indexOf("ZLFH"));
		    zlfh = zlfh != null ? zlfh : "0"; 
		    jlfh = (String)fylist.get(k+fylist.indexOf("JLFH"));
		    jlfh = jlfh != null ? jlfh : "0";
		    if("".equals(jlfh)||"null".equals(jlfh)){
		     jlfh="0";
		    }
		  
		   jzlx = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
		    jzlx = jzlx != null ? jzlx : "";
		  
		    zpsl = (String)fylist.get(k+fylist.indexOf("ZPSL"));
		    zpsl = zpsl != null ? zpsl : "0";
		    if("".equals(zpsl)||"null".equals(zpsl)){
		     zpsl="0";
		    }
		     zssl = (String)fylist.get(k+fylist.indexOf("ZSSL"));
		    zssl = zssl != null ? zssl : "0";
		    if("".equals(zssl)||"null".equals(zssl)){
		     zssl="0";
		    }
		    
		    dbid = (String)fylist.get(k+fylist.indexOf("DBID"));
		    dbid = dbid != null ? dbid : "";
		   	dlid = (String)fylist.get(k+fylist.indexOf("ELECTRICFEE_ID"));
		    dlid = dlid != null ? dlid : "";
		    
		    zdid = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
		    zdid = zdid != null ? zdid : "";
		    
		  //   dzpnh = (String)fylist.get(k+fylist.indexOf("DZPNH"));
		    dzpnh = dzpnh != null ? dzpnh : "0";
		    if("".equals(dzpnh)||"null".equals(dzpnh)){
		       dzpnh="0";
		    }
		   accountm=(String)fylist.get(k+fylist.indexOf("ACCOUNTMONTH"));
          accountm=accountm!=null?accountm:"";
          if("null".equals(accountm)){
          accountm="";
           }
		    DecimalFormat mat1=new DecimalFormat("0.0");
		  actualdegree=mat1.format(Double.parseDouble(actualdegree));
		  DecimalFormat mat2=new DecimalFormat("0.00");
		
		  //  act=act+Double.parseDouble(actualdegree);
		  //  dzpnh=mat2.format(Double.parseDouble(dzpnh));
		    if("".equals(zlfh)||null==zlfh){
		   zlfh="0";
		  }
		  if("".equals(jlfh)||null==jlfh){
		  jlfh="0";
		  }
		  if("".equals(zssl)||null==zssl){
		   zssl="0";
		  }
		   zlfh=mat2.format(Double.parseDouble(zlfh));
		  jlfh=mat2.format(Double.parseDouble(jlfh));
		   count++;
		  lastdatatime=(String)fylist.get(k+fylist.indexOf("LASTDATETIME"));
		   thisdatatime=(String)fylist.get(k+fylist.indexOf("THISDATETIME"));
		  	 SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    			     String dfsl="0";
    			     double shu;
    			     double shu1;
    			     double sjydl;
    			     double dd,ff;
    			     double dbdl1;
    			     String dlbl="0";
    			     
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
        	         	d1=Double.parseDouble(quot+"");
        	         	if(Integer.parseInt(zpsl)>0){
        	         	d= Double.parseDouble(actualdegree);//电量
        	         	s = (d/d1/Integer.parseInt(zpsl)+"").trim();
        	         	s=mat1.format(Double.parseDouble(s));
        	         	
        	         	}
        	     
        	         	
                    
		           		}
		      		}
		      		}
		      		SimpleDateFormat ftm = new SimpleDateFormat("yyyy-MM");
		      		  if((stmon!=null&&!"0".equals(stmon)&&!"null".equals(stmon)&&!stmon.equals("")&&stmon!="")&&(enmon!=null&&!"0".equals(enmon)&&!"null".equals(enmon)&&!enmon.equals("")&&enmon!="")){
	                       String sty=stmon.substring(0,4);//截取年
	                       String ety=enmon.substring(0,4);//截取年 结束月份
	                        String styh=stmon.substring(4,5);//截取-
	                        String etyh=enmon.substring(4,5);//截取-
	                         Pattern pattern = Pattern.compile("[0-9]*");//判断前四位是否是数字
	                     if( pattern.matcher(sty).matches()==true&&pattern.matcher(ety).matches()==true&&styh.equals("-")&&etyh.equals("-")){     
	                        if(stmon.length()>=6&&enmon.length()>=6){
	                         	
	                               Date dateone = ftm.parse(stmon); //起始月份
	                               Date datetwo = ftm.parse(enmon); //结束月份
	                                   Calendar objCalendarDate1 = Calendar.getInstance();   	
	                                   Calendar objCalendarDate2 = Calendar.getInstance();  
	                                   objCalendarDate1.setTime(dateone);
	                                   objCalendarDate2.setTime(datetwo);  
	                                 if (objCalendarDate2.equals(objCalendarDate1)){
	                                    	mon=1;
	                                   }		
	                 if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR)){ 
	                          mon = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))*12 + objCalendarDate2.get(Calendar.MONTH)- objCalendarDate1.get(Calendar.MONTH))+1; 
                        }else{
	                          mon = (objCalendarDate2.get(Calendar.MONTH)- objCalendarDate1.get(Calendar.MONTH))+1;                    	
		                }  
                  }     	
                  }	
		      		}
		   
		   if(mon>0){
           baa=(Double.parseDouble(actualdegree))/mon;
           }
		   
		   
		   
		   
		   
		   
			String color=null;
		
       %>
       <% if(baa>Double.parseDouble(blhdl1)){
       c++;
       	 
       %>
 <tr height=19 style='height:14.25pt'>
  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=shiq+xiaoquq%></td>  
		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdnameee%></td>
		   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=property%></td>
		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=actualdegree%></td>
		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=money%></td>
		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=stmon%></td>
          
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=enmon%></td> 
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=quot%></td> 
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=s%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zpsl%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=twoG%></td>
   		  
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=thrG%></td>
          <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=zlfh%></td>
          <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=jlfh%></td>
          <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=jzlx%></td>
           		 
   		  
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zssl%></td>
   		   
   		   <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=accountm%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=dbid%></td>
   		  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=zdid%></td>
   		  
   		  
 </tr>

 
         <%
           act=act+Double.parseDouble(actualdegree);
       	  money=mat2.format(Double.parseDouble(money));
		  mm=mm+Double.parseDouble(money);
         }}}
         %>
         <table border=1>
 <tr> 
  	<td colspan="19">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=c%>条</font></b>

							<font color='#000080'>&nbsp;折算后用电量总和:</font>
							 <b><font color=red>&nbsp;<%=(new DecimalFormat("0.00")).format(act)%>度</font></b>
							 
							 <font color='#000080'>&nbsp;实际电费总和:</font>
							 <b><font color=red>&nbsp;<%=(new DecimalFormat("0.00")).format(mm)%>元 </font> </b> 
</div>
</td>
  </tr>
</table>
 <tr height=38 style='height:28.5pt;mso-xlrowspan:2'>
  <td height=38 colspan=5 style='height:28.5pt;mso-ignore:colspan'></td>
 </tr>

     <![endif]>
</table>

</body>

</html>
