<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.*,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.jizhan.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<html>
<%
	String path = request.getContextPath();
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
	//String currentmonth = formatter.format(date);
	String current=new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
	String m[] = current.split("-");
	System.out.println("dsfdsfdsfsfsfsf"+current+"SSS"+m[1]);
	int s=Integer.parseInt(m[1]);
	int ss=s-1;
	String mt="";
	if(ss>10){
		mt=mt+ss;
	}else{
		mt="0"+ss;
	}
	String currentmonth=m[0]+"-"+mt;
	String currentmonth1 = request.getParameter("currentmonth") != null ? request.getParameter("currentmonth"): currentmonth;
	int i =0;
	SiteManage bean = new SiteManage();
	i = bean.nhchenk(currentmonth);
	System.out.println("---------------"+i);
	
%>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="images/css.css">
<title>站点导入</title>

 <style>
            .style1 {
	color: #014F8A;
	font-weight: bold;
	line-height:22pt;
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.style4 {
	color: #ff9900;
	font-weight: bold;
}
 #id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
img{
    border:0px;
}
 </style>

</head>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<body class="body" style="overflow-x:hidden;">
<LINK href="../../images/css.css" type=text/css rel=stylesheet />
<script>
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
function daoru(){
	 var path = '<%=path%>';
	 var BeginTime=document.form1.currentmonth.value;
	 var ii=document.getElementById("i").value;
	if(ii=="1"){ 
	if(confirm("该月已保存过，是否覆盖?")){
		//alert("该月已保存过，是否覆盖");
		showdiv("请稍等..............");
		document.form1.action=path+"/servlet/zhandian?action=nhupdate";
		document.form1.submit();
	}else{
		return;
	}
	}else{
		showdiv("请稍等..............");
		document.form1.action=path+"/servlet/zhandian?action=nhupdate";
		document.form1.submit();
	}
}


 $(function(){
	$("#import").click(function(){
			daoru();
			//showdiv("请稍等..............");
		});
		
	});
</script>
<script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script src="<%=path%>/javascript/Date_ny.js"></script>

<!-- 年月日期控件 -->
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
<form name="form1" method="POST" >
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  		<tr>
		    <td width="10"></td>
		    <td>&nbsp;</td>
		    <td width="12"></td>
  		</tr>
  		<tr>
    		<td width="10" height="532">&nbsp;</td>
    		<td valign="top">
    			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
       				<tr>
       				 <td colspan="3">
       				 	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
         				 <tr>
            				<td width="11%"><img src="../../images/in_11.gif" width="123" height="58" /></td>
            				<td width="87%" background="../../images/in_12.gif"><span class="style4"></span></td>
           					<td width="2%"><img src="../../images/in_13.gif" width="25" height="58" /></td>
          				</tr>
        			   </table>
        			 </td>
        			</tr>
      	<tr>
        	<td colspan="3">
        		<table cellspacing="0" cellpadding="0" width="100%" border="0">
          			<tbody>
           				 <tr id="SearchRegion">
                              <td height="51" colspan="3" background="../../images/img_15.gif">&nbsp; </td>
                              <td width="95%" >
                              	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                                	<tr>
                                  		<td height="46" colspan="4"><span class="style1"> 基站能耗更新</span></td>
                                	</tr>
                                	<tr>
                                  		<td height="145" colspan="3" rowspan="2"><div align="center"><img src="../../images/dao.gif" width="132" height="119" /></div></td>
                                  		<td width="66%" height="64"><p> 请选择您要更新的月份</p>                                  </td>
                                	</tr>
                                	<tr>
                                  		<td height="39" valign="top">月份：<input type="text" name="currentmonth" value="<%if (null != request.getParameter("currentmonth1"))
								out.print(request.getParameter("currentmonth1"));%>"   class="form" style="width:130px;" readonly="readonly"/>          　
                                 　                                                       	  			<div id="import" style="position: relative; width: 59px; height: 23px; cursor: pointer; left: 35%; TOP: -23PX " >
							             		<img alt="" src="<%=request.getContextPath() %>/images/tijiao.png" width="100%" height="100%" />
							             		<span style="font-size:12px;position: absolute;left:28px;top:2px;color:white">确定</span>
											</div>
		                         		</td>
                                	</tr>
                                	<tr>
                                  		<td height="51" colspan="4"><div align="center"><input type="hidden" name="i" value="<%=i %>"/></div></td>
                                	</tr>
                              	</table>
                              </td>
                              <td background="../../images/img_17.gif">&nbsp;</td>
            			</tr>
            			<tr>
				              <td width="29" height="26"><img src="../../images/img_18.gif" width="29" height="26" /></td>
				              <td colspan="3" align="middle" background="../../images/img_19.gif"></td>
				              <td width="25"><img src="../../images/img_20.gif" width="25" height="26" /></td>
            			</tr>
          		</tbody>
        		</table>
        	</td>
        </tr>
    	</table>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
</table>
</form>
</body>

	<script type="text/javascript">

document.form1.currentmonth.value = '<%=currentmonth1%>';

</script>
</html>

