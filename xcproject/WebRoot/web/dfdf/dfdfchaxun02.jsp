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
    String whereStr = "";
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
 .STYLE6 {color: #FFFFFF; }
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
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
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

     <tr height = "20" class="relativeTag">
         <td width="20%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">地市</div></td>     
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">净使用电量</div></td>
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">折算后电量</div></td>                     
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">调整电量</div></td>
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">线损电量</div></td>
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">平均倍率</div></td>
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">平均单价</div></td>
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">结算电费</div></td>
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">静使用电费</div></td>
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">多付电费</div></td>
     
     </tr>
       <%
       System.out.println("------+"+bz);
        if(bz!=null){

       String str="";
		if(null !=shi  && !shi.equals("") && !shi.equals("-1")){
			whereStr=whereStr+" and zd.shi='"+shi+"'";
		}
		if(null != xian && !xian.equals("") && !xian.equals("-1")&& !xian.equals("0")){
			
			whereStr=whereStr+" and zd.xian='"+xian+"'";
		}			
		if(null != startmonth  &&!startmonth.equals("") && !startmonth.equals("0")){
			whereStr=whereStr+" and ddv.startmonth>='"+startmonth+"'";
		}
		if(null != endmonth  && !endmonth.equals("") && !endmonth.equals("0")){
			whereStr=whereStr+" and ddv.endmonth <='"+endmonth+"'";
		}
		

		
			
		
		System.out.println("whereStr123"+whereStr);
        dfdfcxServise ser = new dfdfcxServise();
       	ArrayList<dfdfBean> fylist = new ArrayList();
        
        fylist = ser.getDfdfChaxunzd(whereStr);
       	//System.out.println("ALLLLLLL"+allpage);
		String shiname = "",jsydl="",zshdl="",tzdl="",xsdl="",pjbl="",pjdj="",jsdf="",chundf="",cha="";
		int intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		  DecimalFormat price=new DecimalFormat("0.00");
        //  bzpldu = price.format(Double.parseDouble(bzpldu));
		double df=0.0;
		 if(fylist!=null)
		{
			
			 for (dfdfBean beans:fylist) {   
	             shiname = beans.getJzname();
	             jsydl = beans.getJsydl() != null ? beans.getJsydl() : "0";
	             jsydl = price.format(Double.parseDouble(jsydl));
	             zshdl = beans.getZshdl() != null ? beans.getZshdl() : "0";
	             zshdl = price.format(Double.parseDouble(zshdl));
	             tzdl = beans.getTzdl() != null ? beans.getTzdl() : "0";
	             tzdl = price.format(Double.parseDouble(tzdl));
	             xsdl = beans.getXsdl() != null ? beans.getXsdl() : "0";
	             xsdl = price.format(Double.parseDouble(xsdl));
	             pjbl = beans.getPjbl() != null ? beans.getPjbl() : "0";
	             pjbl = price.format(Double.parseDouble(pjbl));
	             pjdj = beans.getPjdj() != null ? beans.getPjdj() : "0";
	             pjdj = price.format(Double.parseDouble(pjdj));
	             jsdf = beans.getJsdf() != null ? beans.getJsdf() : "0";
	             jsdf = price.format(Double.parseDouble(jsdf));
	             chundf = beans.getChundf() != null ? beans.getChundf() : "0";
	             chundf = price.format(Double.parseDouble(chundf));
	             cha = beans.getCha() != null ? beans.getCha() : "0";
	             cha = price.format(Double.parseDouble(cha));
	            
		  
		    
		    String color=null;
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
          intnum++;

       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		<td>
       			<div align="left"><%=shiname%></div>
       		</td>
       		<td>
       			<div align="center"><%=jsydl%></div>
       		</td>
       		<td>
       			<div align="right"><%=zshdl%></div>
       		</td>
       		<td>
       			<div align="right"><%=tzdl%></div>
       		</td>
       		<td>
       			<div align="right"><%=xsdl%></div>
       		</td>
       		<td>
       			<div align="right"><%=pjbl%></div>
       		</td>
       		<td>
       			<div align="right"><%=pjdj%></div>
       		</td>
       		<td>
       			<div align="right"><%=jsdf%></div>
       		</td>
       		<td>
       			<div align="right"><%=chundf%></div>
       		</td>
       		<td>
       			<div align="right"><%=cha%></div>
       		</td>  
       		<td>
       		   <input type="hidden" id="whereStr" name="whereStr" value="<%=whereStr%>"> 
       		</td>                      		
           
       </tr>
       <%}%>
				<% }}%>
				
  	 </table> 	
  	 <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px; " >
	 								 <img src="<%=path %>/images/daoru.png" width="100%" height="100%">
									 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
	   </div>										
							
</form>
</body>
</html>
<script type="text/javascript">
$("#daochuBtn").click(function(){ 
	
exportad();
		
	});
function exportad(){
	//alert("d");
	        var path = '<%=path%>';
            var whereStr = document.getElementById("whereStr").value;
           // var shi = document.getElementById("shit").value;
            
           // alert(path+"/web/query/basequery/dfdfExt01.jsp?whereStr="+whereStr+"&command=daochu");
        	document.form1.action=path+"/web/query/basequery/dfdfExt01.jsp?whereStr="+whereStr+"&command=daochu";
            document.form1.submit();
   }
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
