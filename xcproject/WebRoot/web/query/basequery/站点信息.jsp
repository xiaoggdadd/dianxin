<%@page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ page import="java.util.List,java.util.ArrayList,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
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
<jsp:useBean id="bean" scope="page" class="com.noki.query.basequery.javabean.StationPointQueryBean">
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
 <td class=xl25 width=152 style='border-left:none;width:114pt'>站点名称</td>
 <td class=xl25 width=152 style='border-left:none;width:114pt'>站点别名</td>
 <td class=xl25 width=174 style='border-left:none;width:131pt'>区县</td>
 <td class=xl25 width=174 style='border-left:none;width:131pt'>乡镇</td>
 <td class=xl25 width=165 style='border-left:none;width:124pt'>站点属性</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>站点类型</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>用房类型</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>供电方式</td>
 <td class=xl25 width=72 style='border-left:none;width:54pt'>占地面积</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>负责人</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>录入人员</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>录入时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>市级审核状态</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>市级审核时间</td>
  <td class=xl25 width=72 style='border-left:none;width:54pt'>市级审核人员</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>站点ID</td>
  <td class=xl25 width=142 height=19  style='height:14.25pt;width:107pt'>站点启用状态</td>
 </tr>
 <%   
        
         String whereStr = request.getParameter("whereStr");	  
         String zdname=request.getParameter("zdname");
		if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
			whereStr=whereStr+" AND JZ.JZNAME LIKE '%"+zdname+"%'";			
		}
         if(whereStr==null){
         whereStr="";
         }
       	 List<ElectricFeesFormBean> fylist = bean.getPageDatap(whereStr,loginId);
       	 //allpage=bean.getAllPage();
		String id = "";
		String zlfh="",jlfh="",sdbdl="";
		String shengl = "";
		String shil = "",bieming="";
		String xianl = "";
		String jzname = "";//站点名称
		String jzcode = "";//站点代码
		String sydate = "";
		String erpcode = "";
		String fzr = "";//负责人
		String area = "";//所在地区
		String dianfei = "";
		String mianji = "";//占地面积
		String	property="";//站点属性
		String jztype="";//集团报表类型
		String zdl="";//站点类型
		String jnglmk="";//节能设备
		String yflx="";//用房类型
		String gdfs="";//供电方式
		String xiangzhen="";
		
		/**
	        12-08-13修改
		站点类型(STATIONTYPE)，归属方(GSF)，
	          是否标杆(BGSIGN)，直流负荷(ZLFH)，
		额定耗电量(EDHDL)，有无空调(KONGTIAO)，
	           录入人员(ENTRYPENSONNEL)，录入时间(ENTRYTIME)*/
	           
		String gsf1="",isbg="",zl="",edhd="",kt="",lrry="",lrsj="",scb="";
		
		String rgshzt="",rgshsj="",rgshry="";
	
		String ysjts="",wjts="",yybgkt="",jfsckt="",rru="",ydgxsbsl="",dxgxsbsl="",kts="",ktzgl="",kt1="",kt2="",zdqyzt1="";
		
		//获取条数
		 int count = 0;
		 
		//int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			for(ElectricFeesFormBean bean1:fylist){

			//导出条数
			count = count+1;
		     //num为序号，不同页中序号是连续的		     
		    id = bean1.getStationid();
		    id = id != null ? id : "";
		    
		    area = bean1.getXian();
		    xiangzhen = bean1.getXiaoqu();
		    
		    DecimalFormat lab =new DecimalFormat("0.00");
		    
		 	jlfh=bean1.getJlfh();
		 	jlfh=jlfh!=null?jlfh:"";
			if(jlfh.equals("null")){
				jlfh="";
			}
		    zlfh=bean1.getZlfh();
		    
		    scb = bean1.getScb();
		    scb=scb!=null?scb:"";
			if(scb.equals("null")){
				scb="0";
			}
		    
		    sdbdl=bean1.getSdbdl();
		    if(null==sdbdl||"".equals(sdbdl)||"null".equals(sdbdl)){
		    	/*
		    if(null!=zlfh&&!"".equals(zlfh)&&!"null".equals(zlfh)){
		    System.out.println("直流负荷："+zlfh);
		   sdbdl=lab.format((Double.parseDouble(zlfh)*54*24*1.2)/1000);
		   System.out.println("定标电量："+sdbdl);
		    }else if(null!=jlfh&&!"".equals(jlfh)&&!"null".equals(jlfh)&&(null==zlfh||"".equals(zlfh)||"null".equals(zlfh))){
		    sdbdl=lab.format(((Double.parseDouble(jlfh)*220*24)/1000));
		    System.out.println("交流负荷 ："+jlfh);
		    }*/
		    sdbdl="";
		    }
		
		
		
		
			jztype = bean1.getJztype();
			jztype = jztype != null ? jztype : "";
			
			yflx = bean1.getYflx();
			yflx = yflx != null ? yflx : "";
			if("null".equals(yflx)){
				yflx="";
			}
			bieming = bean1.getBieming();
			bieming = bieming != null ? bieming : "";
			if("null".equals(bieming)){
			bieming="";
			}
			
			gdfs = bean1.getGdfs();
		    gdfs = gdfs != null ? gdfs : "";
			
			rgshzt  = bean1.getFeebz();
		    rgshzt = rgshzt != null ? rgshzt : "";
		    if(rgshzt.equals("null")){
		    	rgshzt="";
		    }
		    if(rgshzt.equals("0")){
		    rgshzt="未通过";
		    }else if(rgshzt.equals("1")){
		    rgshzt="通过";
		    }
		    
		    rgshsj = bean1.getManualauditdatetime();
		    rgshsj = rgshsj != null ? rgshsj : "";
		    if(rgshsj.equals("null")){
		    	rgshsj="";
		    }
		    
		    
		    rgshry = bean1.getManualauditname();
		    rgshry = rgshry != null ? rgshry : "";		    
		    if(rgshry.equals("null")){
		    	rgshry="";
		    }
		    ysjts = bean1.getYsjts();
			ysjts=ysjts!=null?ysjts:"0";
			if(ysjts.equals("null")){
			ysjts="0";
			}
			
			wjts = bean1.getWjts();
			wjts = wjts!=null?wjts:"0";
			if(wjts.equals("null")){
			wjts="0";
			}
			
			yybgkt = bean1.getYybgkt();
			yybgkt = yybgkt!=null?yybgkt:"0";
			if(yybgkt.equals("null")){
			yybgkt="0";
			}
			
			
			jfsckt = bean1.getJfsckt();
			jfsckt = jfsckt!=null?jfsckt:"0";
			if(jfsckt.equals("null")){
			jfsckt="0";
			}
			kts = bean1.getKts();
			kts = kts!=null?kts:"";
			if(kts.equals("null")){
			kts="";
			}
			kt1 = bean1.getKtyps();
			kt1 = kt1!=null?kt1:"0";
			if(kt1.equals("null")){
			kt1="0";
			}	
			kt2 = bean1.getKteps();
			kt2 = kt2!=null?kt2:"0";
			if(kt2.equals("null")){
			kt2="0";
			}
			ktzgl = bean1.getKtzgl();
			ktzgl = ktzgl!=null?ktzgl:"";
			if(ktzgl.equals("null")){
			ktzgl="";
			}
			
			
			rru = bean1.getRru();
			rru = rru!=null?rru:"0";
			if(rru.equals("null")){
			rru="0";
			}
			
			ydgxsbsl = bean1.getYdgxsbsl();
			ydgxsbsl = ydgxsbsl!=null?ydgxsbsl:"0";
			if(ydgxsbsl.equals("null")){
			ydgxsbsl="0";
			}
			
			dxgxsbsl = bean1.getDxgxsbsl();
			dxgxsbsl = dxgxsbsl!=null?dxgxsbsl:"0";
			if(dxgxsbsl.equals("null")){
			dxgxsbsl="0";
			}
			
			property = bean1.getProperty();
			property = property != null ? property : "";
			
		    jzname = bean1.getJzname();
		    jzname = jzname != null ? jzname : "";
		    
		    jzcode = bean1.getZdcode();
		    jzcode = jzcode != null ? jzcode : "";
		    
		    sydate = bean1.getSydate();
		    sydate = sydate != null ? sydate : "";
		    
			erpcode = bean1.getErpcode();
			erpcode = erpcode != null ? erpcode : "";
					
			fzr = bean1.getFzr();
			fzr = fzr != null ? fzr : "";
			if(fzr.equals("null")){
			 fzr="";
			}
			mianji = bean1.getArea();
			mianji = mianji != null ? mianji : "";
			
			//dianfei = (String)fylist.get(k+fylist.indexOf("YFLX"));
			dianfei = bean1.getYflx();
			dianfei = dianfei != null ? dianfei : "";
			
			
			zdl = bean1.getStationtype();
			zdl=zdl!=null?zdl:"";
			
			gsf1 = bean1.getGsf();
			gsf1=gsf1!=null?gsf1:"";
			
			isbg = bean1.getBzdy();
			isbg=isbg!=null?isbg:"";
			if(isbg.equals("0")){
			 isbg="否";
			}else if(isbg.equals("1")){
			isbg="是";
			}
			
			zl = bean1.getZlfh();
			zl=zl!=null?zl:"";
			
			jnglmk = bean1.getJnglmk();
			jnglmk=jnglmk!=null?jnglmk:"";
			if(jnglmk.equals("0")){
			  jnglmk="否";
			}else if(jnglmk.equals("1")){
			jnglmk="是";
			}
			
			DecimalFormat la =new DecimalFormat("0.00");
			edhd = bean1.getEdhdl();
			edhd=edhd!=null?edhd:"";
			if(edhd==null||edhd==""||"".equals(edhd)||"o".equals(edhd)){
				edhd="0.00";
			}
            edhd=la.format(Double.parseDouble(edhd));
			
			kt = bean1.getKongtiao();
			kt=kt!=null?kt:"";
			if(kt.equals("0")){
				kt="无";
			}else if(kt.equals("1")){
				kt="有";
			}
			zdqyzt1 = bean1.getZdqyzt();
			
			if(zdqyzt1.equals("0")){
				zdqyzt1="不启用";
			}else if(zdqyzt1.equals("1")){
				zdqyzt1="启用";
			}
			
			lrry = bean1.getEntrypensonnel();
			lrry=lrry!=null?lrry:"";
			
			lrsj = bean1.getEntrytime();
			lrsj=lrsj!=null?lrsj:"";
		  

			String color=null;

       %>
 <tr height=19 style='height:14.25pt'>   
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=jzname%></td>
	   <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=bieming%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=area%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=xiangzhen%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=property%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdl%></td>
     <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=yflx%></td>
	 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=gdfs%></td>
	 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=mianji%></td>
	 <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=fzr%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=lrry%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=lrsj%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=rgshzt%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=rgshsj%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num>&nbsp;<%=rgshry%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=id%></td>
	  <td class=xl26 align=right style='border-top:none;border-left:none' x:num><%=zdqyzt1%></td>
 
 </tr>
         <%
         }}
         %>
  <tr> 
  	<td colspan="17">
  	<div align="center">
							<font color='#000080'>&nbsp;导出条数:</font>
							 <b><font color=red><%=count%>条</font></b>
	</div>
  </tr>
 
 <![endif]>
</table>

</body>

</html>
