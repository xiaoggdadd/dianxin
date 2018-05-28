<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean,com.noki.newfunction.javabean.Zdinfo,com.noki.newfunction.dao.CbzdQuery"%>
<%@ page import="java.text.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<% 
		int intnum=0;
		String color="";
		String zdid=request.getParameter("zdid");
		String path = request.getContextPath();
		 Account account = (Account)session.getAttribute("account");
         String loginId = account.getAccountId();
          String loginName = account.getAccountName();
          System.out.println("loginName:"+loginName);
		
%>
<head>
<title>原因及要求
</title>
<style>
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
.form1{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;   
   height:19px;
   width:130px;
   
   
}

.form {width:130px}
.bttcn{color:black;font-weight:bold;}
 </style>
  <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
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
</head>
<body  class="body">
<form id="form1"  name="form1"  action="<%=path%>/servlet/Cbzdsjshenhe" method="post" >
  	

<table>
<tr>
<td bgcolor="#DDDDDD"   class="form_label">不通过原因：</td>
<td colspan="20">
<textarea name="yj"  rows="5">
</textarea>
</td>
</tr>
<tr>
<td bgcolor="#DDDDDD" class="form_label"><div>完成时间：</div></td>
			<td><input type="text" class="form" id="entrytimeQS" name="entrytimeQS" value="<%if(null!=request.getParameter("entrytimeQS")) out.print(request.getParameter("entrytimeQS")); %>" onFocus="getDateString(this,oCalendarChs)" /></td>

</tr>
</table>

<table>
<tr>
<td colspan="9">
		
		 <div id="uploading" style="position: relative; width: 60px; height: 20px; pointer;" >
				<img alt=""src="<%=request.getContextPath() %>/images/shangchuan.png" width="100%" height="100%" />
				<span style="font-size: 12px; position: absolute; left: 26px; top: 3px; color: white">提交</span>
		 </div>
		</td>
</tr>

</table>
<input type="hidden" id="ida" name="ida" value="<%=zdid%>"/>
<input type="hidden" id="loginName" name="loginName" value="<%=loginName%>"/>
<input type="hidden" id="action" name="action" value="shicbzdno"/>
</form>
</body>
</html>
 	<script language="javascript">
		function shangchuan(){
		//var path='<%=path%>';
		//var loginName='<%=loginName%>';
		//var yj=document.form1.yj.value;
		//var sj=document.form1.entrytimeQS.value;
		//var id=document.form1.ida.value;
		
		//var test = new RegExp("#","g");
    	//var test1= new RegExp("&","g");
    	//var test2= new RegExp("%","g");
		//var yj;
    	   //if(yj1.match(test)){
    	   // yj = yj1.toString().replace(/\#/g,"%23");
    	  // }else if(yj1.match(test1)){
    	  //  yj = yj1.toString().replace(/\&/g,"%26");
    	  // }else if(yj1.match(test2)){
    	  //  yj = yj1.toString().replace(/\%/g,"%25");
    	   //}else{
    		//  yj= yj1;
    	   //}
		// alert("22222"+path+"/servlet/Cbzdsjshenhe?action=xfshengshno&chooseIdStr1="+id+"&loginName="+loginName);
				//document.form1.action=path+"/servlet/Cbzdsjshenhe?action=xfshengshno&chooseIdStr1="+id+"&loginName="+loginName+"&sj="+sj+"&yj="+yj;
			 //document.form1.action=path+"/servlet/Cbzdsjshenhe?action=shicbzdno&chooseIdStr1="+id+"&loginName="+loginName+"&sj="+sj+"&yj="+yj;
				document.form1.submit();
			}
    $(function(){


		$("#uploading").click(function(){
			shangchuan();
		});
		
	});
		</script>

		
		
