 <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.dfdfcx.servise.*" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*" %>
<%@page import="com.noki.dfdfcx.bean.dfdfBean"%>

<%
	String na = request.getParameter("startmonth");
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    String bz = request.getParameter("bz");
    
    String dltype = request.getParameter("dltype");
    String ydsbb = request.getParameter("ydsb");
    String dbmc = request.getParameter("dbname")!=null?request.getParameter("dbname"):"";
	String startmonth = request.getParameter("startmonth")!=null?request.getParameter("startmonth"):"";
	
	System.out.println("--------"+startmonth);
	
	String endmonth = request.getParameter("endmonth")!=null?request.getParameter("endmonth"):"";
	//String stationname = request.getParameter("stationname")!=null?request.getParameter("stationname"):"";
	String shi = request.getParameter("shiid");
	String xian = request.getParameter("xianid");
	String jztype = request.getParameter("jztype");
	System.out.println(shi+" 1"+xian+"2 "+startmonth+" 4"+endmonth+"77777777777777");
     String canshuStr="";
     
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    //System.out.println("roleId:"+roleId);
%>

<html>
<head>
<title>
</title>
<style>

            .style1 {
	color: #FF9900;
	font-weight: bold;
}
 .STYLE6 {color: #DDDDDD; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
bttcn{color:white;font-weight:bold;font-size:14px}
 .form    {width:130px}
 .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#DDDDDD, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop); 
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px    
}; 
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>

<script >

var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();


var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init();
</script>
<script language="javascript">
var path = '<%=path%>';    
    function modifyad(xianid,shiid,startmonth,endmonth){
    	var b=path+"/web/dfdf/dfdfchaxun02.jsp?xianid="+xianid+"&shiid="+shiid+"&startmonth="+startmonth+"&endmonth="+endmonth+"&";			
			b = b.substring(0,b.length-1);
			 var a = " <a href="+b+" target='treeNodeInfo' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
		 
    } 
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0301");

System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<body  class="body" >
	
	<form action="" name="form1" method="POST">
  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">

     <tr class="form_label">   
         <td width="30%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">净使用电量</div></td>
         <td width="80%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">净起止电量差值</div></td>
     </tr>
       <tr  class="form_label">
       	     <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">折算后电量</div></td> 
       	     <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">线损调整：（差值 +线损+调整）* 倍率
线损比例：（差值 *线损+调整）* 倍率</div></td>               		
       </tr>
       <tr  class="form_label">
       	    <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">调整电量</div></td>
       	    <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">电量调整</div></td>
       </tr>
       <tr  class="form_label">
       	    <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">线损电量</div></td>
       	    <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">线损调整：（差值 +线损）
线损比例：（差值 *线损）</div></td>
       </tr>
		<tr  class="form_label">
       	    <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">平均倍率</div></td>
       	   <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">倍率平均值</div></td>
       	    
       </tr>	
       <tr  class="form_label">
       	    <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">平均单价</div></td>
       	    <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">单价平均值</div></td>
       	    
       </tr>
       <tr  class="form_label">
       	    <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">结算电费</div></td>
       	    <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">实际结算电费</div></td>
       </tr>
       <tr  class="form_label">
       	    <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">净使用电费</div></td>
       	    <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">净使用电量*单价</div></td>
       </tr>
       <tr  class="form_label">
       	   <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">多付电费</div></td>
       	   <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">实际结算电费-净使用电费 </div></td>
       </tr>	
  	 </table> 										
							
</form>
</body>
</html>
<script type="text/javascript">

function changeCity(){
	var sid = document.form1.shi.value;
	document.form1.shi2.value=document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;s
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function changeCountry(){
	var sid = document.form1.xian.value;
	document.form1.xian2.value=document.form1.xian.value;
	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}


</script>
