<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	Account account=(Account)session.getAttribute("account");
	String accountname=account.getAccountName();
	String accountId = account.getAccountId();
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'addggao.jsp' starting page</title>
    
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
  <style type="text/css">
   table {
        border-collapse:collapse;
        border-spacing:0;
    }
  .form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			
		}
  
  
  </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<!--<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
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
<script language="javascript">
var path='<%=path%>';
function addggao(){
	/*var value = document.form1.nr.value;//获取TEXTAREA中得文本值
	alert(value);
	var result = value.replace(",","$nbsp;");//替换value中得空格
    result = value.replace(":","<br>");//替换value中得回车换行*WIN系统*
    alert(result);*/
   if(checkNotSelected(document.form1.xxtype,"信息类型")&&
      checkNotnull(document.form1.ggtime,"公告时间")&&
      checkNotnull(document.form1.bt," 标题")&&
   	  checkNotnull(document.form1.nr,"内容")&&
      confirm("您将要添加公告信息！确认信息正确！")){
         document.form1.action=path+"/servlet/zhandian?action=addggao";
		 document.form1.submit();
      }
     }
     //限定字符数
    /* function maxlength(node, maxcount) {
     alert(node);
    if (node.value.length >= maxcount) {
    	//alert("你输入的汉字超过限定数，请减少部分汉字");
        return false;
    }
    return true;
}*/
  

  $(function(){
            $("#fanhui").click(function(){
				window.history.back();
			});
			$("#tijiao").click(function(){
				addggao();
			});
	});




</script>
  <body>
  <center>
    <fieldset id="regist">
    	<legend><font  style="color:black;font-weight:bold;font-size=16;">添加公告栏</font></legend>
    <form action="" method="post" name="form1">
    	<table border="1" class="form_label"  >
    	 <tr>
    		<td> 
    		信息类型 
    		</td>
    		<td>
    		<select name="xxtype" style="width:130">
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
    	 	<input type="text" name="ggtime" value="" onFocus="getDateString(this,oCalendarChs)">
    	 	</td>
    	 </tr>
    	 <tr>
    	 	<td>
    	 		标题
    	 	</td>
    	 	
    	 	<td>
    	 	<input type="text" name="bt" value="" style="width:260" maxlength="50"/>
    	 	<font size="2">(标题不超过50个汉字,超过部分不会显示)</font>
    	 	</td>
    	 	
    	 </tr>
    	 <tr>
    	 	<td>
    	 	详细内容
    	 	</td>
    	 	<td>
    	 	<textarea name="nr" cols="40" rows="6"></textarea>
    	 	<font size="2">(内容不超过500个汉字，超过将无法保存)</font>
    	 	</td>
    	 </tr>
    	<tr>
    		<td colspan="2">
    			<center>
    	
    			
    			</center>
    		</td>
    	</tr>
    	</table>
    			
    			<div id="fanhui" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:14px">
	           <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
               </div>
	           <div id="tijiao" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:40px">
	           <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>
               </div>
          
    	<input type="hidden" name="accountname" value="<%=accountname %>"/>
    	<input type="hidden" name="accountId" value="<%=accountId %>"/>
    </form>
    </fieldset>
    </center>
  </body>
</html>
