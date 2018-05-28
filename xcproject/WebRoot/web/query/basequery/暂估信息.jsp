<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.function.*"%>
<%@ page import="java.text.*,java.util.regex.Pattern"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%

        Account account = new Account();
        account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        String loginName = account.getAccountName();
        String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";
        String Wstr = request.getParameter("Wstr")!=null?request.getParameter("Wstr"):"";
        String Str = request.getParameter("Str")!=null?request.getParameter("Str"):"";
        String Wdfshi = request.getParameter("Wdfshi")!=null?request.getParameter("Wdfshi"):"";
        String Wyfshi= request.getParameter("Wyfshi")!=null?request.getParameter("Wyfshi"):"";
        String zgqssj= request.getParameter("zgqssj")!=null?request.getParameter("zgqssj"):"";
        String shi= request.getParameter("shi")!=null?request.getParameter("shi"):"";
		String accountId = account.getAccountId();
        String path = request.getContextPath();
        String color="";
        int intnum =0;

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
<jsp:useBean id="bean" scope="page" class="com.noki.query.basequery.javabean.StationPointQueryBean">
</jsp:useBean>


<body link=blue vlink=purple>
       <%
        //Vector v = bean.getHuanbi_all("0",qijian,qyear);         
       %>
<table width="1400" height="60%" border="0" cellspacing="1" cellpadding="1" class="form_label">
 	  <tr height = "23" class="relativeTag">
 	     <td class=xl26 align=right style='border-top:none;border-left:none' x:num>序号</td>	                      	
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>站点名称</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>站点id</td>  
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>电表id</td> 
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>电表名称</td>         
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>所在地区</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>站点属性</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>站点类型</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>电费支付类型</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>暂估使用时间标志</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>最近一次报账抄表时间</td>
	     <td class=xl26 align=right style='border-top:none;border-left:none' x:num>暂估起始时间</td>	                      	
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>暂估结束时间</td>          
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>暂估天数</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>单价（元/天）</td>
         <td class=xl26 align=right style='border-top:none;border-left:none' x:num>暂估金额</td>   
              
 	  </tr>	
 	  <%   
 	  int countxh=1;
	  zanguservlet beanw = new zanguservlet();
	  ArrayList<CityQueryBean> fylist =null;
	  String jzname="",address="",lastdatetime="",thisdatetime="",tianshu="",danjia="",daye="",stationtype="",dfzflxx="",
 	  dianfeizangu ="",actualpay="",thisdatetime1 = "",cssytime="",dbid="",edhdl="",dbname="",bzw1="",zdid="",property="",qsdbdl="";
 	 double danjia1=0.0;
	// if(shi != null && !shi.equals("") && !shi.equals("0")){
		if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
			
		//System.out.println("whereStr"+whereStr);
    	 fylist = beanw.getPageData(whereStr,loginId,Str,Wstr,Wdfshi,Wyfshi,shi);
    	 thisdatetime1=request.getParameter("zgsj");
    	 int count=0;
    	 if(fylist!=null){
    			
 			for(CityQueryBean list:fylist){ 	
 			String bzw="0";//标志着用那个日期进行的暂估   1---最近一次抄表时间  2----电表初始使用时间   3----暂估起始月份	   
 		    jzname =list.getJzname();
 		    stationtype=list.getStationtype();
 		    address =list.getAddress();
 		    dfzflxx=list.getDfzflx();
 		    lastdatetime=list.getStarttime();
 		    thisdatetime=list.getEndtime();
 		    cssytime=list.getCssytime();
 		    danjia=list.getDianfei();
 		    actualpay=list.getActualpay();
 		    //lastdatetime1=list.getTianshu();
 		    edhdl=list.getEdhdl();
 		    dbid=list.getDbid();
 		    dbname=list.getDbname();
 		    zdid=list.getZdid();
 		   property = list.getProperty();//站点属性
		    qsdbdl = list.getQsdbdl();
		   if(qsdbdl==null||qsdbdl.equals("")||qsdbdl.equals("null")) qsdbdl="0";
 		    if(dbname==null||dbname.equals("null")||dbname.equals(" ")){
 		    	dbname="";
 		    }
 		   //System.out.println("thisdatetime8888888888888888"+thisdatetime1+"--*"+thisdatetime+"--*");
 		if(null!=thisdatetime&&!"".equals(thisdatetime)&&!" ".equals(thisdatetime)&&!"0".equals(thisdatetime)){
 	  if(thisdatetime.length()<=7&&thisdatetime.length()>0){	
 		  //if(dfzflxx.equals("合同")){
 		 //System.out.println("thisdatetim88888"+thisdatetime.length());
 		    int y,m,tian; 
 		   //  String day=JOptionPane.showInputDialog("请输入一个日期（年-月）："); 
 		    y=Integer.parseInt(thisdatetime.substring(0,thisdatetime.indexOf("-"))); 
 		    m=Integer.parseInt(thisdatetime.substring(thisdatetime.indexOf("-")+1,thisdatetime.length())); 
 		    //System.out.println("m"+m);
			
 		    tian=y+(y-1)/4-(y-1)/100+(y-1)/400;
 		    if(m==4||m==6||m==9||m==11){
 		    	tian=30; 
 		    }else if(m==2){ 
 		    	if((y%4==0&&y%100!=0)||(y%400==0)){
					tian=29; 
				}else{
					tian= 28 ;
				}			
			}else{
				tian=31; 		 	
			} 
 		   String mm="";
 		   if(m<=9){
				mm="0"+m;
			}else{
				mm=m+"";
			}
			  
			  thisdatetime=y+"-"+mm+"-"+tian;
			  
				
 		}}
 		    //System.out.println("thisdatetime199111111:"+thisdatetime);
 		  // System.out.println("lastdatetime1199999999911111:"+lastdatetime);
 		   
 		    if(actualpay==null||actualpay.equals("")||actualpay.equals("null")) actualpay="0";
		    if(danjia==null||danjia.equals("")||danjia.equals("null")) danjia="0";
		    if(edhdl==null||edhdl.equals("")||edhdl.equals("null")) edhdl="0";
		    
		    DecimalFormat mat=new DecimalFormat("0.00");
		   
		   
		    double day1=0.0,days=0.0;
		    
		    
		    if(!"0".equals(qsdbdl)&&!"".equals(qsdbdl)&&null!=qsdbdl){
			        danjia1 = Double.parseDouble(qsdbdl)*Double.parseDouble(danjia);//单价（元/天）：全省定标电量*站点单价
		    
		  }else{
			  
			  danjia1 =Double.parseDouble(danjia)*Double.parseDouble(edhdl) ;//单价（元/天）：额定耗电量*站点单价
			  day1=0;
			  daye="0";
		  }
		   if("".equals(danjia1)||" ".equals(danjia1)){
			   danjia1=0.0;
		   }
		  //计算的时间
		   if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&!"0".equals(thisdatetime)&&!" ".equals(thisdatetime)){
			    
			     if(!"0".equals(zgqssj)&&!"".equals(zgqssj)&&!" ".equals(zgqssj)&&null!=zgqssj){
				  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime&&!thisdatetime.equals("20101-21")){
				  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				  		Date d = sdf.parse(thisdatetime);
				  		boolean flag = d.before(DateFormat.getDateInstance().parse(zgqssj));//比较大小
				  		if(flag){
				  			//System.out.println("thisdatetime"+thisdatetime+" zgqssj"+zgqssj);
				  		thisdatetime=zgqssj;
				  		 bzw="4";//标志是用的那个时间进行的暂估
				  		}
				  		}
			      }
			    
			    
			    //暂估开始时间
		   	Calendar lastTime = Calendar.getInstance();
		    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
			    //暂估结束时间
			   Calendar thisTime = Calendar.getInstance();
			  // System.out.println("22222222:"+thisdatetime);
			   thisTime.setTime(DateFormat.getDateInstance().parse(thisdatetime1));
			    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
			    //暂估天数
			    days = Math.ceil(temp/1000/60/60/24.0);
			    days=days+1.0;
			    DecimalFormat day2=new DecimalFormat("0");
			    daye=day2.format(days);
			   bzw="1";
		   }else{
				  thisdatetime=cssytime;
				  bzw="2";
				  	  //如果暂估起始时间不为空，并且大于电表初始使用时间，就从暂估起始时间进行暂估数据
				  if(!"0".equals(zgqssj)&&!"".equals(zgqssj)&&!" ".equals(zgqssj)&&null!=zgqssj){
				    if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime){
				  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				  		Date d = sdf.parse(thisdatetime);
				  		boolean flag = d.before(DateFormat.getDateInstance().parse(zgqssj));//比较大小
				  		if(flag){
				  			//System.out.println("thisdatetime"+thisdatetime+" zgqssj"+zgqssj);
				  		thisdatetime=zgqssj;
				  		 bzw="3";//标志是用的那个时间进行的暂估
				  		}
				  	}
				  }
				  //System.out.println("lastdatetime"+lastdatetime+"*--");
				  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime){
					 	Calendar lastTime = Calendar.getInstance();
					    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
						    //暂估结束时间
						   Calendar thisTime = Calendar.getInstance();
						  // System.out.println("22222222:"+thisdatetime);
						   thisTime.setTime(DateFormat.getDateInstance().parse(thisdatetime1));
						    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
						    //暂估天数
						    days = Math.ceil(temp/1000/60/60/24.0);
						    days=days+1.0;
						    DecimalFormat day2=new DecimalFormat("0");
						    daye=day2.format(days);
				  }else{
					  daye="0";
					  days=0;
					  
				  }
			  
		   }
		  String ddd="0";
		  if(daye!="0"){
			  int dddd=Integer.parseInt(daye)-1;
			   if(dddd<0){ //如果小于0 暂估天数就为0
				dddd=0;
				days=0;
			  }
			   ddd=dddd+"";
		  }
		    DecimalFormat dj=new DecimalFormat("0.0000");
		   
		    danjia=dj.format(danjia1);	   		    
			dianfeizangu=mat.format(Double.parseDouble(danjia)*Double.parseDouble(ddd));//暂估金额
			dianfeizangu = dianfeizangu != null ? dianfeizangu : ""; 
			String lastadtimew="";
	     if(!thisdatetime.equals("")){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date dt=sdf.parse(thisdatetime);
			Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加1天
            Date dt1=rightNow.getTime();
            lastadtimew = sdf.format(dt1);
		 } 
		 //暂估起始时间  标志位
		  if(bzw.equals("1")){
	   		bzw1="最近一次报账抄表时间";
	  		 }else if(bzw.equals("2")){
	   			bzw1="电表初始使用时间";
	   		}else if(bzw.equals("3")){
	   			bzw1="暂估起始时间";
	 	  }else if(bzw.equals("4")){
	   			bzw1="暂估起始时间（填写）";
	 	  }
		 //判断 暂估金额不等于0不为空 并且暂估天数不等于0 的信息显示
		 if((!"0.00".equals(dianfeizangu)&&!"".equals(dianfeizangu))&&!"0".equals(ddd)){
	     count = count+1; 	
 	  %>
 	   <tr>
       		<td>
       			<div align="center" ><%=countxh++%></div>
       		</td>      
            <td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=zdid%></div>
       		</td>
       		 <td>
       			<div align="left" ><%=dbid%></div>
       		</td>
       		 <td>
       			<div align="left" ><%=dbname%></div>
       		</td>
       		<td>
       			<div align="left" ><%=address%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=property%></div>
       		</td>
       		<td>
       			<div align="center" ><%=stationtype%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dfzflxx%></div>
       		</td>
       		<td>
       			<div align="center" >&nbsp;<%=bzw1%></div>
       		</td>
       		<td>
       			<div align="center" >&nbsp;<%=thisdatetime%></div>
       		</td>
			<td>
       			<div align="center" >&nbsp;<%=lastadtimew%></div>
       		</td>
       		<td>
       			<div align="center" >&nbsp;<%=thisdatetime1%></div>
       		</td>          
            <td>
       			<div align="right" ><%=ddd %></div>
       		</td>
       		 <td>
       			<div align="right" ><%=danjia%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=dianfeizangu%></div>
       		</td>
       		
       		
       </tr>  
       <%}%>
       <%}
 		}%>
 		    
        <tr> 
  	       <td colspan="22"><div align="center"><font color='#000080'>&nbsp;导出条数:</font><b><font color=red><%=count%>条</font></b>
	</div>
  </tr> 
  <%}%>	   
   
 </table>

</body>

</html>
