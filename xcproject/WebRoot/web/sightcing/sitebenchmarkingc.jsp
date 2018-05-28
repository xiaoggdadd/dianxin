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
System.out.println(shi+"startmonth"+startmonth+"endmonth"+endmonth);
String color="";
int intnum=0;
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
		width:130px;
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
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
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
                   document.form1.action=path+"/web/sightcing/sitebenchmarkingc.jsp";
                   document.form1.submit();
    }}
   $(function(){
	$("#query").click(function(){
		
		queryDegree();
	});
	
});
   function modifyad1(zdlx,averagehdl,ratio,startmonth,endmonth,shi,zdsx,code){
	    var site="0"
    	var b=path+"/web/sightcing/sitebenchmarkingc1.jsp?zdlx="+zdlx+"&dl="+averagehdl+"&bili="+ratio+"&startmonth="+startmonth+"&endmonth="+endmonth+"&shi="+shi+"&zdsx="+zdsx+"&site="+site+"&code="+code;				
    	var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
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
    function modifyad2(zdlx,startmonth,endmonth,shi,zdsx,code){
    	
    	var b=path+"/web/sightcing/sitebenchmarkingc2.jsp?zdlx="+zdlx+"&startmonth="+startmonth+"&endmonth="+endmonth+"&shi="+shi+"&zdsx="+zdsx+"&code="+code;				
    	var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			// alert(a);
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click(); 
	}
	 function modifyad4(zdlx,averagehdl,ratio,startmonth,endmonth,shi,zdsx,code){
		 	var site="0"
	    	var b=path+"/web/sightcing/sitebenchmarkingc1.jsp?zdlx="+zdlx+"&dl="+averagehdl+"&bili="+ratio+"&startmonth="+startmonth+"&endmonth="+endmonth+"&shi="+shi+"&zdsx="+zdsx+"&site="+site+"&code="+code;				
	    	var a = " <a href="+b+" target='treeMap1' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click(); 
    	
	    	var b=path+"/web/sightcing/sitebenchmarkingc2.jsp?zdlx="+zdlx+"&startmonth="+startmonth+"&endmonth="+endmonth+"&shi="+shi+"&zdsx="+zdsx+"&code="+code;				
	    	var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
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
	    <tr><td colspan="4" width="50"><div style="width:700px;height:50px">
			
			<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点对标分析</span>	
		</div></td></tr>
    	<tr><td height="19" colspan="4">
        <div id="parent" style="width:50px;display:inline;"><hr></div><font size="2">过滤条件</font><div style="width:300px;display:inline;"><hr></div>
       </td></tr>
   		<tr><td height="8%" width="100%">
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
		                           <option value="StationType02">基站</option>
		                           <option value="StationType03">接入网</option>
	                       </select><span class="style1">&nbsp;*</span></td>
	                       <td>起始月份：</td>
	                  <td><input type="text" class="selected_font" name="startmonth" value="<%if (null != request.getParameter("startmonth"))out.print(request.getParameter("startmonth"));%>"
	                      onFocus="getDatenyString(this,oCalendarChsny)" onpropertychange="endmonthzq()"  class="form" /><span class="style1">&nbsp;*</span></td>
	                       <td>结束月份：</td>
	                 <td><input type="text" name="endmonth" class="selected_font"  value="<%if (null != request.getParameter("endmonth")) out.print(request.getParameter("endmonth"));%>" onFocus="getDatenyString(this,oCalendarChsny)" class="form" /><span class="style1">&nbsp;*</span></td>
					</tr>
          
			<tr class="form_label">
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
				<td>
                    <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
                    <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;right:-400px;margin:0px 0px 0px 0px">
                   	<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
                   	<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
                  	</div> <%}%>
               </td>                             
			</tr>
             <tr bgcolor="F9F9F9">
                 <td height="19" colspan="8">
                   <div id="parent" style="width:50px;display:inline;"><hr></div><font size="2">信息列表</font><div style="width:300px;display:inline;"><hr></div>
             </td></tr>
               </table>
        </td>
    	</tr>
   	</table>
   	<div style="height:100%;width:100%;margin-left:0px;">
    <table class="form_label"><tr><td valign="top"><table class="form_label" width="470px">
      <tr height = "23">
   		<td width="200px" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
  		<td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">采集站点数</div></td>          
        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">采集站点月耗电量</div></td>
   		<td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结算站点数</div></td>
  		<td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结算站点月耗电量</div></td>    	
  	  </tr>
  	  <%
  	  	String wherestr="",str1="",str2="",averagehdl="";
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
   		   }
   		   if(zdsx != null && !zdsx.equals("") && !zdsx.equals("0")){
        	   wherestr=wherestr+" and z.property='"+zdsx+"'";
   		   }
   		   if(startmonth != null && !startmonth.equals("") && !startmonth.equals("0")){
        	   str1=str1+" and to_char(a.startmonth,'yyyy-mm')>='"+startmonth+"'";
        	   str2=str2+" and to_char(a.lastdatetime,'yyyy-mm-dd')>='"+startmonth+"-01'";
   		   }
   		   if(null !=endmonth  && !"".equals(endmonth) && !"null".equals(endmonth)){
   		    	str1=str1+" and to_char(a.endmonth,'yyyy-mm')<='"+endmonth+"'";
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
   		     }
			if(bili==null||bili.equals("0")||bili==""||"".equals(bili)||"null".equals(bili))bili="0";
   		       Double ratio=1+Double.parseDouble(bili)/100;
   		      SiteBeanchmark bean=new SiteBeanchmark();
              Integer number=0;              
              DecimalFormat mat=new DecimalFormat("0.0");
             if(zdlx.equals("StationType02")){
              List<SiteBeanchmark> list = bean.getsitecx(wherestr,loginId,str1,str2,time,quot,ratio);
               for(SiteBeanchmark sitebean : list){
            	  String zd="",numcj="",sumcj="",numjs="",sumjs="",code="";
            	  code=sitebean.getCode();
            	  zd=sitebean.getZdlx();
            	  numcj=sitebean.getNumcj();
            	  sumcj=sitebean.getSumcj1();
            	  numjs=sitebean.getNumjs();
            	  sumjs=sitebean.getSumjs1();
            	  if(numcj==null||numcj.equals("0")||numcj==""||"".equals(numcj)||"null".equals(numcj))numcj="0";
            	  if(sumcj==null)sumcj="0";
    			  if(numjs==null||numjs.equals("0")||numjs==""||"".equals(numjs)||"null".equals(numjs))numjs="0";
    			  if(sumjs==null)sumjs="0";
    			 if(sumcj.equals("0")||sumcj==""||"".equals(sumcj)||"null".equals(sumcj)){
    				  sumcj="0.0";
    			  }else{
    				  sumcj=mat.format(Double.parseDouble(sumcj));    				  
    				  //Double.parseDouble(numcj)/time);
    			  }
    			  averagehdl=sumcj;
    			  if(sumjs.equals("0")||sumjs==""||"".equals(sumjs)||"null".equals(sumjs)){
    				  sumjs="0.0";
    			  }else{
    				  //sumjs=mat.format(Double.parseDouble(sumjs)/Double.parseDouble(numjs)/time);
    				  sumjs=mat.format(Double.parseDouble(sumjs)/time);
    			  } 
  	  		if(intnum%2==0){
			    color="#FFFFFF" ;
			}else{
			    color="#DDDDDD";
			}
           intnum++;
  	   %>
  	  <!-- <tr bgcolor="<%=color%>">
              <td><div align="left"><a href="javascript:modifyad4('<%=zdlx%>','<%=averagehdl %>','<%=ratio %>','<%=startmonth %>','<%=endmonth %>','<%=shi %>','<%=zdsx %>','<%=code %>')" ><%=zd%></a></div></td>
              <td><div align="right" ><a href="javascript:modifyad2('<%=zdlx%>','<%=startmonth %>','<%=endmonth %>','<%=shi %>','<%=zdsx %>','<%=code %>')" ><%=numcj%></a></div></td>
              <td><div align="right" ><%=sumcj%></div></td> 
              <td><div align="right" ><a href="javascript:modifyad1('<%=zdlx%>','<%=averagehdl %>','<%=ratio %>','<%=startmonth %>','<%=endmonth %>','<%=shi %>','<%=zdsx %>','<%=code %>')" ><%=numjs%></a></div></td>
              <td><div align="right" ><%=sumjs%></div></td>                         
       </tr> -->
	       <tr bgcolor="<%=color%>">
	              <td><div align="left"><a href="javascript:modifyad4('<%=zdlx%>','<%=averagehdl %>','<%=ratio %>','<%=startmonth %>','<%=endmonth %>','<%=shi %>','<%=zdsx %>','<%=code %>')" ><%=zd%></a></div></td>
	              <td><div align="right" ><%=numcj%></div></td>
	              <td><div align="right" ><%=sumcj%></div></td> 
	              <td><div align="right" ><%=numjs%></div></td>
	              <td><div align="right" ><%=sumjs%></div></td>                         
	       </tr> 
            <% }}else if(zdlx.equals("StationType03")){
              	List<SiteBeanchmark> list = bean.getsitecx1(wherestr,loginId,str1,str2,time,quot,ratio);
              for(SiteBeanchmark sitebean : list){
            	  String zd="",numcj="",sumcj="",numjs="",sumjs="",code="";
            	  code=sitebean.getCode();
            	  zd=sitebean.getZdlx();
            	  numcj=sitebean.getNumcj();
            	  sumcj=sitebean.getSumcj1();
            	  numjs=sitebean.getNumjs();
            	  sumjs=sitebean.getSumjs1();
            	  if(numcj==null||numcj.equals("0")||numcj==""||"".equals(numcj)||"null".equals(numcj))numcj="0";
            	  if(sumcj==null)sumcj="0";
    			  if(numjs==null||numjs.equals("0")||numjs==""||"".equals(numjs)||"null".equals(numjs))numjs="0";
    			  if(sumjs==null)sumjs="0";
    			 if(sumcj.equals("0")||sumcj==""||"".equals(sumcj)||"null".equals(sumcj)){
    				  sumcj="0.0";
    			  }else{
    				  sumcj=mat.format(Double.parseDouble(sumcj));    				      				  
    				  //Double.parseDouble(numcj)/time);
    			  }
					averagehdl=sumcj; 
    			  if(sumjs.equals("0")||sumjs==""||"".equals(sumjs)||"null".equals(sumjs)){
    				  sumjs="0.0";
    			  }else{
    				// sumjs=mat.format(Double.parseDouble(sumjs)/Double.parseDouble(numjs)/time);
    				sumjs=mat.format(Double.parseDouble(sumjs)/time);
    			  } 
  	  		if(intnum%2==0){
			    color="#FFFFFF" ;
			}else{
			    color="#DDDDDD";
			}
           intnum++;
  	   %>
  	   <!-- <tr bgcolor="<%=color%>">
              <td><div align="left"><a href="javascript:modifyad4('<%=zdlx%>','<%=averagehdl %>','<%=ratio %>','<%=startmonth %>','<%=endmonth %>','<%=shi %>','<%=zdsx %>','<%=code %>')" ><%=zd%></a></div></td>
              <td><div align="right" ><a href="javascript:modifyad2('<%=zdlx%>','<%=startmonth %>','<%=endmonth %>','<%=shi %>','<%=zdsx %>','<%=code %>')" ><%=numcj%></a></div></td>
              <td><div align="right" ><%=sumcj%></div></td> 
              <td><div align="right" ><a href="javascript:modifyad1('<%=zdlx%>','<%=averagehdl %>','<%=ratio %>','<%=startmonth %>','<%=endmonth %>','<%=shi %>','<%=zdsx %>','<%=code %>')" ><%=numjs%></a></div></td>
              <td><div align="right" ><%=sumjs%></div></td>                         
       </tr> -->
	       <tr bgcolor="<%=color%>">
	              <td><div align="left"><a href="javascript:modifyad4('<%=zdlx%>','<%=averagehdl %>','<%=ratio %>','<%=startmonth %>','<%=endmonth %>','<%=shi %>','<%=zdsx %>','<%=code %>')" ><%=zd%></a></div></td>
	              <td><div align="right" ><%=numcj%></div></td>
	              <td><div align="right" ><%=sumcj%></div></td> 
	              <td><div align="right" ><%=numjs%></div></td>
	              <td><div align="right" ><%=sumjs%></div></td>                         
	       </tr>
       <%}} if (intnum==0){
         for(int i=0;i<22;i++){
          if(i%2==0){
			    color="#FFFFFF";
          }else{
			    color="#DDDDDD";
			}
         %>

        <tr bgcolor="<%=color%>">
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
             <td>&nbsp;</td>
        </tr>
      <% }}else if(intnum < 22){
    	  for(int i=((intnum-1)%12);i<11;i++){
            if(i%2==0)
			    color="#FFFFFF" ;
            else
			    color="#DDDDDD";
        %>
        <tr bgcolor="<%=color%>">
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>                 
        </tr>
        <% }}%>
       </table></td>
       <td width="100%" height="100%">
       <table width="100%" height="100%">
       <tr><td><div style="margin-left:5px;"><iframe  name="treeMap" frameborder="0" scrolling="yes" width="100%" height="100%"></iframe></div></td></tr>
       <tr><td><div style="margin-left:5px;"><iframe  name="treeMap1" frameborder="0" scrolling="yes" width="100%" height="100%"></iframe></div></td></tr>
       </table>
       </td>
       </tr>
  	</table></div>   
    </form>
  </body>
<script type="text/javascript">
document.form1.shi.value='<%=shi%>';
document.form1.zdlx.value='<%=zdlx%>';
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
</html>

