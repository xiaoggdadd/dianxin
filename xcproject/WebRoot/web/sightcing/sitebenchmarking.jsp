<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.mobi.cx.SiteBeanchmark" %>
<%@ page import="java.text.*" %>
<%
Account account=(Account)session.getAttribute("account");
String loginId=account.getAccountId();
String path = request.getContextPath();
String sheng = (String)session.getAttribute("accountSheng");
String shi=request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String startmonth=request.getParameter("startmonth");
String endmonth=request.getParameter("endmonth");
String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
String zdsx=request.getParameter("zdsx")!=null?request.getParameter("zdsx"):"0";
String bili=request.getParameter("overproof-percentage");
String dytype=request.getParameter("dytype")!=null?request.getParameter("dytype"):"0";
String g2=request.getParameter("g2")!=null?request.getParameter("g2"):"-1";
String g3=request.getParameter("g3")!=null?request.getParameter("g3"):"-1";
String kt1=request.getParameter("kt1")!=null?request.getParameter("kt1"):"-1";
String kt2=request.getParameter("kt2")!=null?request.getParameter("kt2"):"-1";
String kdsb=request.getParameter("kdsb")!=null?request.getParameter("kdsb"):"-1";
String yysb=request.getParameter("yysb")!=null?request.getParameter("yysb"):"-1";
String zgd=request.getParameter("zgd")!=null?request.getParameter("zgd"):"-1";
String zpslzd=request.getParameter("zpslzd");
String yysbsl=request.getParameter("yysbsl");
String zssl=request.getParameter("zssl");
String kdsbsl=request.getParameter("kdsbsl");
String zlfh=request.getParameter("zlfh");
String jlfh=request.getParameter("jlfh");
String pue=request.getParameter("pue");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sitebenchmarking.jsp' starting page</title>
    <style>
    .form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px

		}
		.selected_font{
		width:100px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
.bttcn{color:black;font-weight:bold;}
     body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.style1 {
	color: #FF0000;
	font-weight: bold;
}
    </style>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
<script>

var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();

var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChsny.oBtnTodayTitle = "确定";
oCalendarChsny.oBtnCancelTitle = "取消";
oCalendarChsny.Init();
</script>
<script language="javascript">
var path = '<%=path%>';
  function queryDegree(){
	     if(document.getElementById("zdlx").value==""||document.getElementById("zdlx").value=="0"||document.getElementById("zdlx").value==null)
		{
	                 alert("请选择站点类型");
	   
	   } else if(document.getElementById("startmonth").value==""||document.getElementById("startmonth").value=="0"||document.getElementById("startmonth").value==null)
		{
	                 alert("请选择起始月份");
	   
	   } else if(document.getElementById("endmonth").value==""||document.getElementById("endmonth").value=="0"||document.getElementById("endmonth").value==null)
		{
	                 alert("请选择结束月份");
	   
	   }else  if(document.getElementById("overproof-percentage").value==""||document.getElementById("overproof-percentage").value==null)
		{
	                 alert("请填写超标比例");
	   
	   }else{
                   document.form1.action=path+"/web/sightcing/sitebenchmarking.jsp";
                   document.form1.submit();
    }}
   $(function(){
	$("#query").click(function(){
		
		queryDegree();
	});
	
});
   function modifyad1(zdlx,averagehdl,ratio,startmonth,endmonth,pue,shi,dytype,g2,g3,kt1,kt2,kdsb,yysb,zgd,zpsl,yysbsl,zssl,kdsbsl,zlfh,jlfh,zdsx){
	    var site="0"
    	var b=path+"/web/sightcing/sitebenchmarking1.jsp?zdlx="+zdlx+"&dl="+averagehdl+"&bili="+ratio+"&startmonth="+startmonth+"&endmonth="+endmonth+"&pue="+pue+"&shi="+shi+"&dytype="+dytype+"&g2="+g2+"&g3="+g3+"&kt1="+kt1+"&kt2="+kt2+"&kdsb="+kdsb+"&yysb="+yysb+"&zgd="+zgd+"&zpsl="+zpsl+"&yysbsl="+yysbsl+"&zssl="+zssl+"&kdsbsl="+kdsbsl+"&zlfh="+zlfh+"&jlfh="+jlfh+"&zdsx="+zdsx+"&site="+site;				
    	var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			// alert(a);
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click(); 
	}
    function modifyad3(zdlx,startmonth,endmonth,pue,shi,dytype,g2,g3,kt1,kt2,kdsb,yysb,zgd,zpsl,yysbsl,zssl,kdsbsl,zlfh,jlfh,zdsx){
	    var averagehdl="";
	    var ratio="";
	    var site="1";
    	var b=path+"/web/sightcing/sitebenchmarking1.jsp?zdlx="+zdlx+"&dl="+averagehdl+"&bili="+ratio+"&startmonth="+startmonth+"&endmonth="+endmonth+"&pue="+pue+"&shi="+shi+"&dytype="+dytype+"&g2="+g2+"&g3="+g3+"&kt1="+kt1+"&kt2="+kt2+"&kdsb="+kdsb+"&yysb="+yysb+"&zgd="+zgd+"&zpsl="+zpsl+"&yysbsl="+yysbsl+"&zssl="+zssl+"&kdsbsl="+kdsbsl+"&zlfh="+zlfh+"&jlfh="+jlfh+"&zdsx="+zdsx+"&site="+site;				
    	var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			// alert(a);
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click(); 
	}
    function modifyad2(zdlx,startmonth,endmonth,pue,shi,dytype,g2,g3,kt1,kt2,kdsb,yysb,zgd,zpsl,yysbsl,zssl,kdsbsl,zlfh,jlfh,zdsx){
    	
    	var b=path+"/web/sightcing/sitebenchmarking2.jsp?zdlx="+zdlx+"&startmonth="+startmonth+"&endmonth="+endmonth+"&pue="+pue+"&shi="+shi+"&dytype="+dytype+"&g2="+g2+"&g3="+g3+"&kt1="+kt1+"&kt2="+kt2+"&kdsb="+kdsb+"&yysb="+yysb+"&zgd="+zgd+"&zpsl="+zpsl+"&yysbsl="+yysbsl+"&zssl="+zssl+"&kdsbsl="+kdsbsl+"&zlfh="+zlfh+"&jlfh="+jlfh+"&zdsx="+zdsx;				
    	var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			// alert(a);
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click(); 
	}
     function endmonthzq(){
         var startmonth = document.form1.startmonth.value;
         document.form1.endmonth.value=startmonth;
        }
   
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
String permissionRights="";
String roleId = (String)session.getAttribute("accountRole");
permissionRights=commBean.getPermissionRight(roleId,"2102");

System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
  <body  class="body" style="overflow-x:hidden;">
    <form action="" name="form1" method="post">
       <table  width="100%"  border="0" cellspacing="0" cellpadding="0" height="20%">
	 <tr>
       <td colspan="3">
				<span style="color:black;font-weight:bold;font-size=14;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;站点对标分析</span>
	   </td>
    </tr>
    <tr><td></td></tr>
    <tr>
    	<td height="19" colspan="4">
    	<div id="parent" style="display:inline">
        <div style="width:50px;display:inline;"><hr></div><font size="2">过滤条件</font><div style="width:300px;display:inline;"><hr></div>
        </div>
        </td>
    </tr>
   			 <tr >
   		 		<td height="8%" width="100%">
   		 		   <table>
   		 		   	<tr class="form_label">
                              <td align="left"> 城市：</td>    
                              <td><select name="shi" class="selected_font" >
         		                           <option value="0">请选择</option>
         		<%
		         	ArrayList shilist = new ArrayList();
		         	shilist = commBean.getAgcode(sheng,account.getAccountId());
		         	if(shilist!=null){
		         		String agcode="",agname="";
		         		int scount = ((Integer)shilist.get(0)).intValue();
		         		for(int i=scount;i<shilist.size()-1;i+=scount)
	                    {
	                    	agcode=(String)shilist.get(i+shilist.indexOf("AGCODE"));
	                    	agname=(String)shilist.get(i+shilist.indexOf("AGNAME"));
	                    %>
	                    <option value="<%=agcode%>"><%=agname%></option>
	                    <%}
		         	}
		         %>
         	                 </select></td>
                            <td>站点类型：</td>
                            <td><select name="zdlx" class="selected_font" > 
                                    <option value="0">请选择</option>
          <%
	         	List stationtype = new ArrayList();
         		stationtype = ztcommon.getSelctOptions("StationType");
	         	if(stationtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)stationtype.get(0)).intValue();
	         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
                    {
                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
                    %>
                                   <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
                            </select><span class="style1">&nbsp;*</span></td>
                            <td>起始月份：</td>
		                     <td><input type="text" class="selected_font" name="startmonth" value="<%if (null != request.getParameter("startmonth"))out.print(request.getParameter("startmonth"));%>"
		                         onFocus="getDatenyString(this,oCalendarChsny)" onpropertychange="endmonthzq()"  class="form" /><span class="style1">&nbsp;*</span></td>
                            <td>结束月份：</td>
		                    <td><input type="text" name="endmonth" class="selected_font"  value="<%if (null != request.getParameter("endmonth")) out.print(request.getParameter("endmonth"));%>" onFocus="getDatenyString(this,oCalendarChsny)" class="form" /><span class="style1">&nbsp;*</span></td>
      						<td>超标比例： </td>
                             <td><input  type="text" name="overproof-percentage" class="selected_font" value="<% if (null != request.getParameter("overproof-percentage"))out.print(request.getParameter("overproof-percentage"));%>"/>%<span class="style1">&nbsp;*</span></td>
      						<td>站点属性：</td>
                            <td><select name="zdsx" class="selected_font" > 
                                    <option value="0">请选择</option>
          <%
	         	List zdsxlist = new ArrayList();
          zdsxlist = ztcommon.getSelctOptions("ZDSX");
	         	if(zdsxlist!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdsxlist.get(0)).intValue();
	         		for(int i=cscount;i<zdsxlist.size()-1;i+=cscount)
                    {
                    	code=(String)zdsxlist.get(i+zdsxlist.indexOf("CODE"));
                    	name=(String)zdsxlist.get(i+zdsxlist.indexOf("NAME"));
                    %>
                                   <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
                            </select></td>
      						
                           </tr>
                           <tr class="form_label">
			    <td >地域属性： 
              	</td>
         		<td  >
         		<select name="dytype"  class="selected_font" >
         		<option value="0">请选择</option>
         		<%
	         	ArrayList dy = new ArrayList();
         		dy = ztcommon.getSelctXslx("dytype");
	         	if(dy!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)dy.get(0)).intValue();
	         		for(int i=cscount;i<dy.size()-1;i+=cscount)
                    {
                    	code=(String)dy.get(i+dy.indexOf("CODE"));
                    	name=(String)dy.get(i+dy.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         		}
	         	%>
         		</select>
         		</td> 
         		<td >2G设备：  </td>
        		 <td  >
         	  	 <select name="g2" class="selected_font">
         	  	 <option value="-1">请选择</option>
         		 <option value="0">无</option>
         		 <option value="1">有</option>
         		</select>
         		</td>
       		  <td  >3G设备：  </td>
         	 <td  >
         	 <select name="g3" class="selected_font">
         	    <option value="-1">请选择</option>
         		<option value="0">无</option>
         		<option value="1">有</option>
         		
         	 </select>
             </td>  
             <td  >空调1：  </td>
        		 <td  >
         	  	 <select name="kt1" class="selected_font">
         	  	 <option value="-1">请选择</option>
         		 <option value="0">无</option>
         		 <option value="1">有</option>
         		
         		 </select>
         		 </td>
       		  	 <td  >空调2：  </td>
         		 <td  >
         		 <select name="kt2" class="selected_font">
         		 <option value="-1">请选择</option>
         		 <option value="0">无</option>
         		 <option value="1">有</option>
         		
         		 </select>
                 </td>  
			</tr>
			<tr class="form_label">
				<td  >宽带设备：  </td>
        		 <td  >
         	  	 <select name="kdsb" class="selected_font">
         	  	 <option value="-1">请选择</option>
         		 <option value="0">无</option>
         		 <option value="1">有</option>
         		
         		</select>
         		</td>
       		  <td  >语音设备：  </td>
         	 <td  >
         	 <select name="yysb" class="selected_font">
         	 <option value="-1">请选择</option>
         		<option value="0">无</option>
         		<option value="1">有</option>
         		
         	 </select>
             </td> 
              <td  >直供电：  </td>
         		 <td  >
         		 <select name="zgd" class="selected_font">
         		 <option value="-1">请选择</option>
         		 <option value="0">否</option>
         		 <option value="1">是</option>
         		 </select>
                 </td>   
			<td  >载频数量： 	</td>
        	 <td  ><input type="text" name="zpslzd" value="<%if (null != request.getParameter("zpslzd"))out.print(request.getParameter("zpslzd"));%>" class="selected_font" /></td>
			<td  >语音设备数量：  </td>
        		 <td  ><input type="text" name="yysbsl" value="<%if (null != request.getParameter("yysbsl"))out.print(request.getParameter("yysbsl"));%>" class="selected_font" /></td>
			</tr>
			<tr class="form_label">
				<td  >载扇数量：  </td>
        		 <td  ><input type="text" name="zssl" value="<%if (null != request.getParameter("zssl"))out.print(request.getParameter("zssl"));%>" class="selected_font" /></td>
        		 <td  >宽带设备数量：  </td>
        		 <td  ><input type="text" name="kdsbsl" value="<%if (null != request.getParameter("kdsbsl"))out.print(request.getParameter("kdsbsl"));%>" class="selected_font" /></td>
        		 <td  >直流负荷：  </td>
        		 <td  ><input type="text" name="zlfh" value="<%if (null != request.getParameter("zlfh"))out.print(request.getParameter("zlfh"));%>" class="selected_font" /></td>
        		 <td  >交流负荷：  </td>
        		 <td  ><input type="text" name="jlfh" value="<%if (null != request.getParameter("jlfh"))out.print(request.getParameter("jlfh"));%>" class="selected_font" /></td>
			     <td  >PUE值大于：  </td>
        		 <td  ><input type="text" name="pue" value="<%if (null != request.getParameter("pue"))out.print(request.getParameter("pue"));%>" class="selected_font" /></td>
			<td>
                                   <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
                                 <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:85%;margin:0px 0px 0px 0px">
			                        <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
			                        <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                        </div>      <%}%>
                             </td>
                             
			</tr>
                        <tr bgcolor="F9F9F9">
                        <td height="19" colspan="8"><div id="parent" style="display:inline">
                        <div style="width:50px;display:inline;"><hr></div><font size="2">信息列表</font><div style="width:300px;display:inline;"><hr></div>
                         </div>
                       </td>
                       </tr>
               </table>
        </td>
    </tr>
          </table>
         <table width="100%" height="30%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
           <%
              String wherestr="",str1="",str2="";
              int time=0;
              long quot=0;
              Calendar cal=Calendar.getInstance();   
  			String yf="";
  			int y=cal.get(Calendar.YEAR);    
  			String m=cal.get(Calendar.MONTH)+1+"";   
  			if(m.length()==1){
  				yf=y+"-0"+m;
  			}else{
  				yf=y+"-"+m;
  			}

              
           if(shi != null && !shi.equals("") && !shi.equals("0")){
        	   wherestr=wherestr+" and z.shi='"+shi+"'";
   		     }if(zdsx != null && !zdsx.equals("") && !zdsx.equals("0")){
        	   wherestr=wherestr+" and z.property='"+zdsx+"'";
   		     }if(dytype != null && !dytype.equals("") && !dytype.equals("0")){
        	   wherestr=wherestr+" and z.dytype='"+dytype+"'";
   		     }if(g2 != null && !g2.equals("") && !g2.equals("-1")){
        	   wherestr=wherestr+" and z.g2='"+g2+"'";
   		     }if(g3 != null && !g3.equals("") && !g3.equals("-1")){
        	   wherestr=wherestr+" and z.g3='"+g3+"'";
   		     }if(kt1 != null && !kt1.equals("") && !kt1.equals("-1")){
        	   wherestr=wherestr+" and z.kt1='"+kt1+"'";
   		     }if(kt2 != null && !kt2.equals("") && !kt2.equals("-1")){
        	   wherestr=wherestr+" and z.kt2='"+kt2+"'";
   		     }if(kdsb != null && !kdsb.equals("") && !kdsb.equals("-1")){
        	   wherestr=wherestr+" and z.kdsb='"+kdsb+"'";
   		     }if(yysb != null && !yysb.equals("") && !yysb.equals("-1")){
        	   wherestr=wherestr+" and z.yysb='"+yysb+"'";
   		     }if(zgd != null && !zgd.equals("") && !zgd.equals("-1")){
        	   wherestr=wherestr+" and z.zgd='"+zgd+"'";
   		     }if(zpslzd != null && !zpslzd.equals("") && !zpslzd.equals("0")){
        	   wherestr=wherestr+" and z.zpsl='"+zpslzd+"'";
   		     }if(yysbsl != null && !yysbsl.equals("") && !yysbsl.equals("0")){
        	   wherestr=wherestr+" and z.yysbsl='"+yysbsl+"'";
   		     }if(zssl != null && !zssl.equals("") && !zssl.equals("0")){
        	   wherestr=wherestr+" and z.zssl='"+zssl+"'";
   		     }if(kdsbsl != null && !kdsbsl.equals("") && !kdsbsl.equals("0")){
        	   wherestr=wherestr+" and z.kdsbsl='"+kdsbsl+"'";
   		     }if(zlfh != null && !zlfh.equals("") && !zlfh.equals("0")){
        	   wherestr=wherestr+" and z.zlfh='"+zlfh+"'";
   		     }if(jlfh != null && !jlfh.equals("") && !jlfh.equals("0")){
        	   wherestr=wherestr+" and z.jlfh='"+jlfh+"'";
   		     }if(startmonth != null && !startmonth.equals("") && !startmonth.equals("0")){
        	   str1=str1+" and to_char(a.startmonth,'yyyy-mm')>='"+startmonth+"'";
        	   //wherestr=wherestr+" and a.startmonth>='"+startmonth+"'";
        	   str2=str2+" and to_char(a.lastdatetime,'yyyy-mm-dd')>='"+startmonth+"-01'";
   		     }if(endmonth != null && !endmonth.equals("") && !endmonth.equals("0")){
   		    	str1=str1+" and to_char(a.endmonth,'yyyy-mm')<='"+endmonth+"'";
   		    	//wherestr=wherestr+" and a.startmonth<='"+endmonth+"'";
   		    	str2=str2+" and to_char(a.thisdatetime,'yyyy-mm-dd')<='"+endmonth+"-31'";
        	      int startn=Integer.parseInt(startmonth.substring(0,4));
        	      int starty=Integer.parseInt(startmonth.substring(5,7));
        	      int endn=Integer.parseInt(endmonth.substring(0,4));
        	      int endy=Integer.parseInt(endmonth.substring(5,7));
        	       time=(endn-startn)*12+endy-starty+1;
        	       SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM");
        	   		 SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        	   		 Date date = sdf0.parse(endmonth);
        	         Calendar calendar = new GregorianCalendar();
        	         calendar.setTime(date);
        	         int tian=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        	         String time1=startmonth+"-01";
        	         String time2=endmonth+"-"+tian;
        	         Date date1 = ft.parse( time1 );
        	         Date date2 = ft.parse( time2 );
        	         quot = date2.getTime() - date1.getTime();
        	         quot = quot/1000/60/60/24+1;
        	         System.out.println(tian);
   		     }if(pue != null && !pue.equals("") && !pue.equals("0")){
   		    	 str1=str1+" AND A.BLHDL / (Z.ZLFH * 54 + Z.JLFH * 220)>'"+pue+"' ";
   		     }
   		 
   		

              System.out.println(quot);

              SiteBeanchmark bean=new SiteBeanchmark();
              Integer number=0;
              DecimalFormat mat=new DecimalFormat("0.0");
              if(zdlx != null && !zdlx.equals("") && !zdlx.equals("0")){
              List<SiteBeanchmark> list = bean.getsite1(wherestr,loginId,zdlx,str1);
              for(SiteBeanchmark sitebean : list){
            	  String zd="",hd="",zdlx1="";
            	  zd=sitebean.getZdcode();
            	  hd=sitebean.getHdl();
            	  zdlx1=sitebean.getZdlx();
    			  if(hd==null)hd="0";
    			  System.out.println(hd+"---"+zd+"---"+time);
    			  if(hd.equals("0")){
    				  hd="0";
    			  }else{
    				  hd=mat.format(Double.parseDouble(hd)/Double.parseDouble(zd)/time);
    			  }
              
              
              %>
              <tr height = "23" class="relativeTag">
            	<td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
              <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" ><%=zdlx1 %></div></td>
              <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">地市标杆月耗电量</div></td> 
              <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" ><%=hd %></div></td>
              <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">统计站点数</div></td>
              <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" ><a href="javascript:modifyad3('<%=zdlx%>','<%=startmonth %>','<%=endmonth %>','<%=pue %>','<%=shi %>','<%=dytype %>','<%=g2 %>','<%=g3 %>','<%=kt1 %>','<%=kt2 %>','<%=kdsb %>','<%=yysb %>','<%=zgd %>','<%=zpslzd %>','<%=yysbsl %>','<%=zssl %>','<%=kdsbsl %>','<%=zlfh %>','<%=jlfh %>','<%=zdsx %>')" ><%=zd %></a></div></td>                         
        	</tr>
              <% }
              List<SiteBeanchmark> list2 = bean.getsite2(wherestr,loginId,zdlx,str2);
              for(SiteBeanchmark list2bean:list2){
            	  String zdlx2="",zdcode2="",hdl2="";
            	  String zdlx1="",zdcode1="",hdl1="";
            	  number++;
            	  if(null!=list2bean.getHdl()&&!list2bean.getHdl().equals("")){
            		  zdlx2=list2bean.getZdlx();
            		  hdl2=list2bean.getHdl();
            		  zdcode2=list2bean.getZdcode();
            		  Double averagehdl=0.0;
            		  String zdlxname=list2bean.getZdsx();
            		  if(hdl2==null)hdl2="0";
            		  averagehdl=Double.parseDouble(hdl2)/Double.parseDouble(zdcode2)*quot/time;
            		  Double ratio=1+Double.parseDouble(bili)/100;
            		  String dl=mat.format(averagehdl);
            		  %>
        			<tr height = "23" class="relativeTag">
                      	<td nowrap height="23" bgcolor="#FFFFFF"><div align="center" class="bttcn">站点类型</div></td>
                        <td nowrap height="23" bgcolor="#FFFFFF"><div align="center" ><%=zdlxname%></div></td>
                        <td nowrap height="23" bgcolor="#FFFFFF"><div align="center" class="bttcn">采集标杆月耗电量</div></td> 
                        <td nowrap height="23" bgcolor="#FFFFFF"><div align="center" ><%=dl %></div></td>
                        <td nowrap height="23" bgcolor="#FFFFFF"><div align="center" class="bttcn">统计站点数</div></td>
                        <td nowrap height="23" bgcolor="#FFFFFF"><div align="center" ><a href="javascript:modifyad2('<%=zdlx%>','<%=startmonth %>','<%=endmonth %>','<%=pue %>','<%=shi %>','<%=dytype %>','<%=g2 %>','<%=g3 %>','<%=kt1 %>','<%=kt2 %>','<%=kdsb %>','<%=yysb %>','<%=zgd %>','<%=zpslzd %>','<%=yysbsl %>','<%=zssl %>','<%=kdsbsl %>','<%=zlfh %>','<%=jlfh %>','<%=zdsx %>')" ><%=zdcode2 %></a></div></td>                         
                  	</tr>
        			  <% 
            		  List<SiteBeanchmark> list1 = bean.getsite1(wherestr,loginId,ratio,zdlx2,averagehdl,str1);
            		  for(SiteBeanchmark list1bean:list1){
            			  zdcode1=list1bean.getZdcode();
            			  hdl1=list1bean.getHdl();
            			  if(hdl1==null)hdl1="0";
            			  System.out.println(hdl1+zdcode1+time);
            			  if(hdl1.equals("0")){
            				  hdl1="0";
            			  }else{
            				  hdl1=mat.format(Double.parseDouble(hdl1)/Double.parseDouble(zdcode1)/time);
            			  }
            			 
            			  %>
            			 <tr height = "23" class="relativeTag">
                      	<td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" ><%=zdlxname%></div></td>
                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结算月耗电量</div></td> 
                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" ><%=hdl1 %></div></td>
                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超标站点数</div></td>
                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" ><a href="javascript:modifyad1('<%=zdlx%>','<%=averagehdl%>','<%=ratio %>','<%=startmonth %>','<%=endmonth %>','<%=pue %>','<%=shi %>','<%=dytype %>','<%=g2 %>','<%=g3 %>','<%=kt1 %>','<%=kt2 %>','<%=kdsb %>','<%=yysb %>','<%=zgd %>','<%=zpslzd %>','<%=yysbsl %>','<%=zssl %>','<%=kdsbsl %>','<%=zlfh %>','<%=jlfh %>','<%=zdsx %>')" ><%=zdcode1 %></a></div></td>
                                             
                     	</tr>
            			  <% 
            		  }
            	  }
              }}
           %> 
         </table>
         <br>
        <iframe  name="treeMap" width="100%" height="50%" frameborder="0"></iframe> 	
    
    </form>
  </body>
</html>
<script type="text/javascript">
document.form1.shi.value='<%=shi%>';
document.form1.zdlx.value='<%=zdlx%>';
document.form1.dytype.value='<%=dytype%>';
document.form1.g2.value='<%=g2%>';
document.form1.g3.value='<%=g3%>';
document.form1.kt1.value='<%=kt1%>';
document.form1.kt2.value='<%=kt2%>';
document.form1.kdsb.value='<%=kdsb%>';
document.form1.yysb.value='<%=yysb%>';
document.form1.zgd.value='<%=zgd%>';
document.form1.zdsx.value='<%=zdsx%>';
var start='<%=startmonth%>';
var s="null";
if(start==s){
document.form1.startmonth.value='<%=yf%>';
}else if(start!=s){
document.form1.startmonth.value='<%=startmonth%>';
}
if(start==s){
document.form1.endmonth.value='<%=yf%>';
}else if(start!=s){
document.form1.endmonth.value='<%=endmonth%>';
}

</script>
