<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.ZhanDianForm" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean" %>
<%
String path = request.getContextPath();
    String id = request.getParameter("id");
	SiteModifyBean form = new SiteModifyBean();
	form = form.getGgao(id);
	System.out.print("内容"+form.getNr()+"信息类型"+form.getXxtype());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>公告栏</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
  </jsp:useBean>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<!--<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
--><script >

var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();


var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init();
</script>

  <body>
    <center>
    <fieldset id="regist">
    	<legend><font  style="color:black;font-weight:bold;font-size=16;">公告栏详细信息</font></legend>
    <form action="" method="post" name="form1">
    	<table border="2" class="form_label">
    	 <tr>
    		<td> 
    		信息类型 
    		</td>
    		<td>
    		<select name="xxtype" style="width:130" >
         		<option value="0">请选择</option>
         		<%
	         	ArrayList xxtype = new ArrayList();
         		xxtype = commBean.getSelctOptions("xxtype");
	         	if(xxtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)xxtype.get(0)).intValue();
	         		for(int i=cscount;i<xxtype.size()-1;i+=cscount)
                    {
                    	code=(String)xxtype.get(i+xxtype.indexOf("CODE"));
                    	name=(String)xxtype.get(i+xxtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
	         </select>
	        <font size="2">(请选择适当的信息类型)</font> 
    		</td>
    	 </tr>
    	 <tr>
    	 	<td>
    	 	公告时间
    	 	</td>
    	 	<td>
    	 	<input type="text" name="ggtime" readonly="readonly" value="<%=form.getGgtime() %>" onFocus="getDateString(this,oCalendarChs)">
    	 	</td>
    	 </tr>
    	 <tr>
    	 	<td>
    	 		标题
    	 	</td>
    	 	
    	 	<td>
    	    <input type="text" name="bt" value="<%=form.getBt() %>" style="width:260" readonly="readonly"/>
    	 	<font size="2">(标题不超过50个汉字)</font>
    	 	</td>
    	 	
    	 </tr>
    	 <tr>
    	 	<td>
    	 	详细内容
    	 	</td>
    	 	<td>
    	 	<textarea name="nr" cols="40" rows="6" readonly="readonly"></textarea>
    	 	<font size="2">(标题不超过500个汉字)</font>
    	 	</td>
    	 </tr>
    	<tr>
    	 	<td>
    	 		录入时间
    	 	</td>
    	 	
    	 	<td>
    	 	<input type="text" name="bt" value="<%=form.getDqtime() %>" style="width:260" readonly="readonly" />
    	 
    	 	</td>
    	 	
    	 </tr>
    	 <tr>
    	 	<td>
    	 		录入人
    	 	</td>
    	 	
    	 	<td>
    	 	<input type="text" name="bt" value="<%=form.getLrr() %>" style="width:260" readonly="readonly" />
    	 
    	 	</td>
    	 	
    	 </tr>
    	</table>
    	<input type="hidden" type="text" name="nr1" value="<%=form.getNr() %>"/>
    </form>
    </fieldset>
    </center>
  </body>
</html>
<script language="javascript">
document.form1.nr.value=document.form1.nr1.value;//内容
if('<%=form.getXxtype()%>'=="" || '<%=form.getXxtype()%>'=="null"){
	document.form1.xxtype.value="0";
}
document.form1.xxtype.value='<%=form.getXxtype()%>';//信息类型


</script>