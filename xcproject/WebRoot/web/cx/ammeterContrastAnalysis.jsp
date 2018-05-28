<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.mobi.cx.AmmeterContrastAnalysis" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*" %>

<%
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date()).substring(0,7);
    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date()).substring(0,7);
       
	String jza = request.getParameter("jzacode")!=null?request.getParameter("jzacode"):"";
	String jzb = request.getParameter("jzbcode")!=null?request.getParameter("jzbcode"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
	String roleId = account.getRoleId();
	String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String dbid="0;0",dlhj="0;0",fyhj="0;0",time="0;0";
    String dbid2="0;0",dlhj2="0;0",fyhj2="0;0",time2="0;0";
%>

<html>
<head>
<title>
logMange
</title>
<style type="text/css">
.bttcn{color:white;font-weight:bold;}
.form{width:130px}
</style>

<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script >

var oCalendarEnny=new PopupCalendarny("oCalendarEnny");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();


var oCalendarChsny=new PopupCalendarny("oCalendarChsny");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChsny.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChsny.oBtnTodayTitle="确定";
oCalendarChsny.oBtnCancelTitle="取消";
oCalendarChsny.Init();
</script>
<script language="javascript">
var path = '<%=path%>';
function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

		}
		else
		{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
}

  function chaxun(){
  		var beginTime = document.form1.beginTime.value;
		var endTime = document.form1.endTime.value;
		var jza = document.form1.jzacode.value;
		var jzb = document.form1.jzbcode.value;
		
		if(beginTime==""||endTime==""){
			alert("时间不能为空");
			return;
		}
		if(jza==""||jzb==""){
			alert("请选择要对比的电表");
			return;
		}
		if(jza==jzb){
			alert("两个电表不能相同");
			return;
		}
		document.form1.action=path+"/web/cx/ammeterContrastAnalysis.jsp";
		document.form1.submit();
	}
		function dayinpage(title) {
			var column = "all";//打印所有列
//			var column = [0,1,4,6];//定义表格要打印的列，下标从0开始
			var style="<style>table {border-collapse:collapse;border-style:double;border-width:3px;border-color:black;width:700px}</style>";//定义表格样式
			 
			create(title,setColumn(column,style));//创建打印样式
		};
		function setLayout(title,tableHTML){
			LODOP.SET_PRINT_STYLE("FontSize",18);
			LODOP.ADD_PRINT_TEXT(50,320,200,30,title);
			LODOP.ADD_PRINT_HTM(80,130,750,150,tableHTML); 
			LODOP.ADD_PRINT_IMAGE(230,160,550,350,"<img src='http://localhost:8080/energy/servlet/chart_jzdb' />");
			LODOP.SET_PRINT_STYLEA(3,"Stretch",1);//(可变形)扩展缩放模式
		}
$(function(){

	$("#query").click(function(){
		chaxun();
	});
	$("#dayinBtn").click(function(){
		dayinpage('电表对比分析');
	});
	$("#a").click(function(){
		getjz('a');
	});
	$("#b").click(function(){
		getjz('b');
	});
	
	
});
		function getjz(par){
			var divWidth = 800;
			var bodyWidth = $("body").innerWidth();
			var leftSize = 0;//div距离左侧的位置
			if(bodyWidth>divWidth)leftSize = (bodyWidth-divWidth)/2;
			var divStr = "<div id='areaDiv' name='"+par+"' style='position:absolute;width:"+divWidth+"px;height:400px;border:5px #cad5db groove;left:"+leftSize+"px;top:100px;background-color:green'>";
			divStr += "<iframe src='<%=path%>/web/cx/ammeterContrastAnalysis_ammeter.jsp' name='' id='' width='"+(divWidth-5)+"px' height=100%></iframe>";
			divStr += "</div>";
			$("body").append(divStr);
		}
</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
    <td width="10" height="500">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4"></td>
						</tr>
						
						<tr>
							<td colspan=3 width="600" background="<%=path%>/images/btt.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电表对比分析</span></td>
							
							<td width="380">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="4"></td>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">
						<tr><td>&nbsp;</td></tr>
					    <tr>					  
					    <td colspan="2"><div id="parent" style="display:inline"></div>
					  		  <div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                              </div>
					   </td>
					  </tr>
  		 <tr>
  
          <td width="250px">
         	起始月份：<input type="text" readonly name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form"/>
          </td>
        	<td nowrap width="550px">
        		<div id="inputdiv"  style="width:200px;height:23px;float:left;position:relative;" >
        		电&nbsp;&nbsp;表A：<input type="text" readonly name="jzaname" id="jzaname" value="<%if(null!=request.getParameter("jzacode")) out.print(request.getParameter("jzacode")); %>"  class="form" />       		
        			<input type="hidden" name="jzacode" id="jzacode" value="<%if(null!=request.getParameter("jzacode")) out.print(request.getParameter("jzacode")); %>" />
        		</div>
        		<div id="a" style="position:relative;width:59px;height:23px;cursor: pointer;left:1%;TOP:2PX;">
					<img alt="" src="<%=request.getContextPath() %>/images/xuanze.png" width="100%" height="100%" />
					<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">选择</span>
				</div>
			</td>
		</tr>
        <tr>
		  <td width="250px">
                                结束月份：<input type="text" readonly name="endTime" value="<%if(null!=request.getParameter("endTime")) out.print(request.getParameter("endTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form"/>
         </td>
         <td nowrap width="270px">
       		<div id="inputdiv" style="width:200px;height:23px;float:left;position:relative;">
       		电&nbsp;&nbsp;表B：<input type="text" readonly name="jzbname" id="jzbname" value="<%if(null!=request.getParameter("jzbcode")) out.print(request.getParameter("jzbcode")); %>"  class="form"/>       		
       			<input type="hidden" name="jzbcode" id="jzbcode" value="<%if(null!=request.getParameter("jzbcode")) out.print(request.getParameter("jzbcode")); %>" />
       		</div>
       		<div id="b" style="position:relative;width:59px;height:23px;cursor: pointer;left:1%;TOP:2PX;">
				<img alt="" src="<%=request.getContextPath() %>/images/xuanze.png" width="100%" height="100%" />
				<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">选择</span>
			</div>
			 </td>
			<td >
			<div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;right:70%;TOP:2PX">
				<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
				<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		  </div> 
       
         </td></tr>
         <tr><td>&nbsp;</td></tr>
          
  </table>

  	<div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>
                         
  
   <table width="100%" border="0" >
   <tr bgcolor="#666666" align="center">
	   <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点id<div></td>
	   <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点名称<div></td>	 
	   <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">电表id<div></td> 
	   <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">电表名称<div></td>
	   <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">所在地区<div></td>
	   <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">电表用途<div></td>
	   <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">所属专业<div></td>
	   <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">电流类型<div></td>
	   <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">用电设备<div></td>
	   <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">线损类型<div></td>
   </tr>
   <%
     AmmeterContrastAnalysis bean0 = new AmmeterContrastAnalysis();  	 
     ArrayList fylist = new ArrayList();
   if(jza!=null && !jza.equals("")){
	   
   fylist = bean0.getAmmeterContrastAnalysis11(jza,beginTime,endTime);
 
   String szdq="",zdid="",zdname="",dbidd="",dbname="",dbyt="",sszy="",dllx="",ydsb="",xslx="";
  
   if(fylist!=null)
	{
		int fycount = ((Integer)fylist.get(0)).intValue();
		
		for( int k = fycount;k<fylist.size()-1;k+=fycount){
			   szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
			   zdid = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
			   zdname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
			   dbidd = (String)fylist.get(k+fylist.indexOf("DBID"));
			   dbname = (String)fylist.get(k+fylist.indexOf("DBNAME"));
			   dbyt = (String)fylist.get(k+fylist.indexOf("DBYT"));
			   sszy = (String)fylist.get(k+fylist.indexOf("SSZY"));
			   dllx = (String)fylist.get(k+fylist.indexOf("DLLX"));
			   ydsb = (String)fylist.get(k+fylist.indexOf("YDSB"));
			   xslx = (String)fylist.get(k+fylist.indexOf("LINELOSSTYPE"));			  
   }
     %>
   <tr align="center">	  
	   <td><%=zdid %></td>
	   <td><%=zdname %></td>
	   <td><%=dbidd %></td>
	   <td><%=dbname %></td>
	   <td><%=szdq %></td>
	   <td><%=dbyt %></td>
	   <td><%=sszy %></td>
	   <td><%=dllx %></td>
	   <td><%=ydsb %></td>
	   <td><%=xslx %></td>
   </tr> 
   <%}} %>
   <%
   AmmeterContrastAnalysis beana = new AmmeterContrastAnalysis();  
   ArrayList fylista = new ArrayList();
   fylista = beana.getAmmeterContrastAnalysis11(jzb,beginTime,endTime);
   if(fylista!=null)
	{
	   String szdqa="",zdida="",zdnamea="",dbidda="",dbnamea="",dbyta="",sszya="",dllxa="",ydsba="",xslxa="";
		int fycount = ((Integer)fylista.get(0)).intValue();		
		for( int k = fycount;k<fylista.size()-1;k+=fycount){
			 szdqa = (String)fylista.get(k+fylista.indexOf("SZDQ"));
			   zdida = (String)fylista.get(k+fylista.indexOf("ZDCODE"));
			   zdnamea = (String)fylista.get(k+fylista.indexOf("JZNAME"));
			   dbidda = (String)fylista.get(k+fylista.indexOf("DBID"));
			   dbnamea = (String)fylista.get(k+fylista.indexOf("DBNAME"));
			   dbyta = (String)fylista.get(k+fylista.indexOf("DBYT"));
			   sszya = (String)fylista.get(k+fylista.indexOf("SSZY"));
			   dllxa = (String)fylista.get(k+fylista.indexOf("DLLX"));
			   ydsba = (String)fylista.get(k+fylista.indexOf("YDSB"));
			   xslxa = (String)fylista.get(k+fylista.indexOf("LINELOSSTYPE"));
		}	
   %>
   <tr bgcolor="#cbd5de" align="center">	  
	   <td><%=zdida %></td>
	   <td><%=zdnamea %></td>
	   <td><%=dbidda %></td>
	   <td><%=dbnamea %></td>
	   <td><%=szdqa %></td>
	   <td><%=dbyta %></td>
	   <td><%=sszya %></td>
	   <td><%=dllxa %></td>
	   <td><%=ydsba %></td>
	   <td><%=xslxa %></td> 
	</tr>
	<%} %>
	</table>
	<br>
	<table width="100%" border="0">
    <tr><td colspan="10">
  <table  height="100%" border="0"  >
     <tr>
     <%
     AmmeterContrastAnalysis bean = new AmmeterContrastAnalysis();
   	 ArrayList fylist1 = new ArrayList();
   	ArrayList fylist2 = new ArrayList();
   if(jza!=null && !jza.equals("")){
   fylist1 = bean.getAmmeterContrastAnalysis1(jza,beginTime,endTime);
   fylist2 = bean.getAmmeterContrastAnalysis2(jzb,beginTime,endTime);
	
	String ammeter1="",month1="",actualdegree1="",actualpay1="";
	String ammeter2="",month2="",actualdegree2="",actualpay2="";
     %>
     <td>
     <table border="0" cellspacing="1" cellpadding="1" bgcolor="#f7c0d3">
             <tr>
              
                <td><div align="center" >月份</div></td>
                <td><div align="center" >耗电量</div></td>
                <td><div align="center" >电费</div></td>
              
             </tr>  
       <%
      
		double jfcount=0.00,dl=0.0;
		int intnum=xh = pagesize*(curpage-1)+1;
		
		 if(fylist1!=null)
		{
		//
		dlhj="";fyhj="";time="";dbid="";
			int fycount = ((Integer)fylist1.get(0)).intValue();
			
			for( int k = fycount;k<fylist1.size()-1;k+=fycount){
			
		     //num为序号，不同页中序号是连续的
		     ammeter1 = (String)fylist1.get(k+fylist1.indexOf("DBID"));
		     month1 = (String)fylist1.get(k+fylist1.indexOf("STARTMONTH"));
		     actualdegree1 = (String)fylist1.get(k+fylist1.indexOf("SUM(A.ACTUALDEGREE)"));
		     actualpay1 = (String)fylist1.get(k+fylist1.indexOf("SUM(E.ACTUALPAY)"));
		       
				DecimalFormat mat=new DecimalFormat("0.00");
				DecimalFormat matdl=new DecimalFormat("0.0");
				actualdegree1=matdl.format(Double.parseDouble(actualdegree1));
				actualpay1=mat.format(Double.parseDouble(actualpay1));
				dbid=ammeter1;
				dlhj+=actualdegree1+";";
				 System.out.println("--"+dlhj+"**"+actualdegree1);			
				fyhj+=actualpay1+";";
				time+=month1+";";
				
			String color=null;

			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr bgcolor="<%=color%>">
            
       		<td>
       			<div align="center" ><%=month1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=actualdegree1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=actualpay1%></div>
       		</td>
       </tr>
       <%}}%>
       </table>
       </td>
       <td>
       
       <table border="0"  height="100%" cellspacing="1" cellpadding="1" bgcolor="#c3f6f3">
         <tr>
               
                <td><div align="center" >月份</div></td>
                <td><div align="center" >耗电量</div></td>
                <td><div align="center" >电费</div></td>
         </tr>
         <%
         if(fylist2!=null)
 		{
 		//
 		dlhj2="";fyhj2="";time2="";dbid2="";
 			int fycount = ((Integer)fylist2.get(0)).intValue();
 			
 			for( int k = fycount;k<fylist2.size()-1;k+=fycount){
 		
 		     //num为序号，不同页中序号是连续的
 		     ammeter2 = (String)fylist2.get(k+fylist2.indexOf("DBID"));
 		     month2 = (String)fylist2.get(k+fylist2.indexOf("STARTMONTH"));
 		     actualdegree2 = (String)fylist2.get(k+fylist2.indexOf("SUM(A.ACTUALDEGREE)"));
 		     actualpay2 = (String)fylist2.get(k+fylist2.indexOf("SUM(E.ACTUALPAY)"));
 		       
 				DecimalFormat mat=new DecimalFormat("0.00");
 				DecimalFormat matdl=new DecimalFormat("0.0");
 				actualdegree2=matdl.format(Double.parseDouble(actualdegree2));
				actualpay2=mat.format(Double.parseDouble(actualpay2));
 				dbid2=ammeter2;
				dlhj2+=actualdegree2+";";
				fyhj2+=actualpay2+";";
				time2+=month2+";";
 			String color=null;

 			if(intnum%2==0){
 			    color="#DDDDDD";

 			 }else{
 			    color="#FFFFFF" ;
 			}

         %>
         <tr bgcolor="<%=color%>">
              
       		<td>
       			<div align="center" ><%=month2%></div>
       		</td>
       		<td>
       			<div align="center" ><%=actualdegree2%></div>
       		</td>
       		<td>
       			<div align="center" ><%=actualpay2%></div>
       		</td>
         </tr>
         <%}} %>
       </table>
       </td>
       <%} %>
       </tr>
  	 </table> 
  	 </td>
  	 <td>
  	 									<%
									if(fylist1.size()!=0){
		%>
									  	 									      <br />
<table width="100%"  border="0" cellspacing="0" cellpadding="0">

  	 									
 <jsp:include flush="true" page="ammeterContrastAnalysis_chart.jsp">
 
  					
  <jsp:param name="dbid" value="<%=dbid%>"/>
  <jsp:param name="dlhj" value="<%=dlhj%>"/>
  	<jsp:param name="fyhj" value="<%=fyhj%>"/>
  	 <jsp:param name="dbid2" value="<%=dbid2%>"/>
  <jsp:param name="dlhj2" value="<%=dlhj2%>"/>
  	<jsp:param name="fyhj2" value="<%=fyhj2%>"/>
  	 <jsp:param name="time" value="<%=time%>"/>
  <jsp:param name="time2" value="<%=time2%>"/>
  
</jsp:include>
   </td>
      </tr>
   </table>  
</table>  
<%}%>
											</td>
										</tr>
									</table>

								</td>
						</tr>
						<tr bgcolor="#FFFFFF">
											
											<td align="right" colspan="10">
												<div id="parent" style="display:inline" align="right">
                          <div style="width:300px;display:inline;" align="right"><hr></div>
                      </div>
											</td>
										</tr>
					</table>
				</td>
			</tr>
		</table>
      <br />
     
    </td>
  
  </tr>
  
</table>
</form>
</body>
</html>
