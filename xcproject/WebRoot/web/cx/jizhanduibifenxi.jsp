<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.mobi.cx.JiZhanDuiBiFenXi" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*" %>

<%
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date()).substring(0,7);
    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date()).substring(0,7);
       
	String jza = request.getParameter("jzacode")!=null?request.getParameter("jzacode"):"0";
	String jzb = request.getParameter("jzbcode")!=null?request.getParameter("jzbcode"):"0";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
	String roleId = account.getRoleId();
	String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String jzmc="0;0",dlhj="0;0",fyhj="0;0";
%>

<html>
<head>
<title>
logMange
</title>
<style type="text/css">
.bttcn{color:white;font-weight:bold;}
.form{width:130px}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			
}
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
			alert("请选择要对比的站点");
			return;
		}
		if(jza==jzb){
			alert("两个站点不能相同");
			return;
		}
		document.form1.action=path+"/web/cx/jizhanduibifenxi.jsp";
		document.form1.submit();
	}
	
	function daorujz(){
		var tt = 'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no';
		var jza = '<%=jza%>';
		var jzb = '<%=jzb%>';
		
		var beginTime = '<%=beginTime%>';
		var endTime = '<%=endTime%>';

		var url = path+"/web/cx/jizhanduibifenxi_dc.jsp?jza="+jza+"&jzb="+jzb+"&beginTime="+beginTime+"&endTime="+endTime
		window.open(url,"nenghaodayin",tt);
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
		dayinpage('站点对比分析');
	});
	
	$("#daochuBtn").click(function(){
		daorujz();
	});
	$("#a").click(function(){
		getjz('a');
	});
	$("#b").click(function(){
		getjz('b');
	});
	
	
});
</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		<tr><td colspan="4">
			 <div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点对比分析</span>	
			  </div>
		</td></tr>
		<tr><td height="19" colspan="4">
		<div id="parent" style="display:inline">
                <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
        </div>
	</td></tr>
	<tr class="form_label"><td height="8%" width="1200">
		<table>
		<tr class="form_label">
          <td width="250px">
         	起始月份：<input type="text" readonly name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form"/>
          </td>
        	<td nowrap width="550px">
        		<div id="inputdiv"  style="width:200px;height:23px;float:left;position:relative;" >
        		站&nbsp;&nbsp;点A：<input type="text" readonly name="jzaname" id="jzaname" value="<%if(null!=request.getParameter("jzaname")) out.print(request.getParameter("jzaname")); %>"  class="form" />       		
        			<input type="hidden" name="jzacode" id="jzacode" value="<%if(null!=request.getParameter("jzacode")) out.print(request.getParameter("jzacode")); %>" />
        		</div>
        		<div id="a" style="position:relative;width:59px;height:23px;cursor: pointer;left:1%;TOP:2PX;">
					<img alt="" src="<%=request.getContextPath() %>/images/xuanze.png" width="100%" height="100%" />
					<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">选择</span>
				</div>
			</td>
		</tr>
        <tr  class="form_label">
		  <td width="250px">
                                结束月份：<input type="text" readonly name="endTime" value="<%if(null!=request.getParameter("endTime")) out.print(request.getParameter("endTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form"/>
         </td>
         <td nowrap width="270px">
       		<div id="inputdiv" style="width:200px;height:23px;float:left;position:relative;">
       		站&nbsp;&nbsp;点B：<input type="text" readonly name="jzbname" id="jzbname" value="<%if(null!=request.getParameter("jzbname")) out.print(request.getParameter("jzbname")); %>"  class="form"/>       		
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
	  </table>
   </td></tr>
  </table>
  	<div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div><font size='2'>&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                      </div>
   <div id="tableInfo">                
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                  <tr >
                      <td width="5%" height="23" bgcolor="#888888"><div align="center" class="bttcn">序号</div></td>
                      <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点编号</div></td>
                      <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点名称</div></td>
            		  <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">所在地区</div></td>
                      <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">合计用电量</div></td>
            		  <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">抄表次数</div></td>
				      <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">合计交费数额</div></td>
            		 <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">交费次数</div></td>
                  </tr>
       <%
       JiZhanDuiBiFenXi bean = new JiZhanDuiBiFenXi();
       	 ArrayList fylist = new ArrayList();
       fylist = bean.getPageData(jza,jzb,beginTime,endTime);
		String jzcode="",jzname = "",ydcount = "",cbcount = "", jfcount1= "",jfcs="",szdq="",df="";
		double jfcount=0.00,dl=0.0;
		int intnum=xh = pagesize*(curpage-1)+1;
		
		 if(fylist!=null)
		{
		//
			int fycount = ((Integer)fylist.get(0)).intValue();
			
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
			if(k==fycount){
			jzmc="";dlhj="";fyhj="";
			}
		     //num为序号，不同页中序号是连续的
		     jzcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
				jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
				szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		    cbcount = (String)fylist.get(k+fylist.indexOf("NUMDL"));
		    ydcount = (String)fylist.get(k+fylist.indexOf("DEGREE"));
		    jfcount1 = (String)fylist.get(k+fylist.indexOf("PAY"));
		    jfcs = (String)fylist.get(k+fylist.indexOf("NUMDF"));
		        if(jfcount1==null||jfcount1=="")jfcount1="0";
		        if(cbcount==null||cbcount=="")cbcount="0";
		        if(jfcs==null||jfcs=="")jfcs="0";
				if(jfcount1!=null)
				{   
				    jfcount=Double.parseDouble(jfcount1);
				    
				}
				if(ydcount==null||ydcount=="")ydcount="0";
				DecimalFormat mat=new DecimalFormat("0.00");
				DecimalFormat matdl=new DecimalFormat("0.0");
				dl=Double.parseDouble(ydcount);
				ydcount=matdl.format(dl);
				df=mat.format(jfcount);
				jzmc+=jzname+";";
				dlhj+=ydcount+";";
				fyhj+=jfcount+";";
			String color=null;

			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		<td>
       			<div align="center" ><%=intnum++%></div>
       		</td>
       <td>
       			<div align="center" ><%=jzcode%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center" ><%=ydcount%></div>
       		</td>
       		<td>
       			<div align="center" ><%=cbcount%></div>
       		</td>
       		<td>
       			<div align="center" ><%=df%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jfcs%></div>
       		</td>
       		

       </tr>
       <%} %>
       
       <%}%>


  	 </table> 
  	 </div>
  	 									<%
									if(fylist!=null)
									System.out.println("sjfa;jfdjaf");
		{%>
									  	 									      <br />
<table width="100%"  border="0" cellspacing="0" cellpadding="0">

  	 									
 <jsp:include flush="true" page="jizhanduibifenxi_chart.jsp">
 
  	 								
  <jsp:param name="jzmc" value="<%=jzmc%>"/>
  <jsp:param name="dlhj" value="<%=dlhj%>"/>
  	<jsp:param name="fyhj" value="<%=fyhj%>"/>
</jsp:include>

</table>  
<%}%>
						
												<div id="parent" style="display:inline" align="right">
							                          <div style="width:300px;display:inline;float:right"><hr></div>
							                    </div>
											<br>
												<div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
	 												<img src="<%=path %>/images/daochu.png" width="100%" height="100%">
													<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
												</div>
												<div id="dayinBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:15px">
														<img src="<%=path %>/images/dayin.png" width="100%" height="100%">
														<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">打印</span>      
												</div>
											

</form>
</body>
<jsp:include page="/web/prePrint.jsp"></jsp:include>
<script language="javascript">
	var path = '<%=path%>';
		function getjz(par){
			var divWidth = 1000;
			var bodyWidth = $("body").innerWidth();
			var leftSize = 0;//div距离左侧的位置
			if(bodyWidth>divWidth)leftSize = (bodyWidth-divWidth)/2;
			var divStr = "<div id='areaDiv' name='"+par+"' style='position:absolute;width:"+divWidth+"px;height:500px;border:5px #cad5db groove;left:"+leftSize+"px;top:100px;background-color:green'>";
			divStr += "<iframe src='<%=path%>/web/cx/jizhanduibifenxi_zhandian.jsp' name='' id='' width='"+(divWidth-5)+"px' height=100%></iframe>";
			divStr += "</div>";
			$("body").append(divStr);
		}
</script>
</html>
