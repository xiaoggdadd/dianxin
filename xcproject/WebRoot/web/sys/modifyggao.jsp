<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.ZhanDianForm" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%

	
    String path = request.getContextPath();
    String id = request.getParameter("id");
	SiteModifyBean form = new SiteModifyBean();
	form = form.getGgao(id);
	System.out.print("内容"+form.getNr()+"信息类型"+form.getXxtype()+id);
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	Account account=(Account)session.getAttribute("account");
	String accountname=account.getAccountName();
	String accountId=account.getAccountId();


/*String  str=form.getNr();  
while(str.indexOf("\n")!=-1){   //把空格和换行符 用别的符号替换
str = str.substring(0,str.indexOf("\n"))+","+str.substring(str.indexOf("\n")+1);
} 
while(str.indexOf(" ")!=-1){ 
str = str.substring(0,str.indexOf(" "))+","+str.substring(str.indexOf(" ")+1); 
} */


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
  </jsp:useBean>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
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

var path='<%=path%>';
function modifyggao(){
     
   if(confirm("您将要修改公告信息！确认信息正确！")){
         document.form1.action=path+"/servlet/zhandian?action=modifyggao";
		 document.form1.submit();
      }
     }
     
    
     function push_txt(str) {  
           var obj = document.getElementById("txt");   // textarea对象       
           obj.append(str);   // 在对象末尾处加入内容
     }
  $(function(){
            $("#fanhui").click(function(){
				window.history.back();
			});
			$("#tijiao").click(function(){
				modifyggao();
			});
	});






</script>
<style>
   table {
        border-collapse:collapse;
        border-spacing:0;
    }

</style>
  <body>
    <center>
    <fieldset id="regist">
    	<legend><font  style="color:black;font-weight:bold;font-size=16;">公告栏详细信息</font></legend>
    <form action="" method="post" name="form1">
    	<table border="1" class="form_label">
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
    	 	<input type="text" name="ggtime" value="<%=form.getGgtime() %>" onFocus="getDateString(this,oCalendarChs)">
    	 	</td>
    	 </tr>
    	 <tr>
    	 	<td>
    	 		标题
    	 	</td>
    	 	
    	 	<td>
    	    <input type="text" name="bt" value="<%=form.getBt() %>" style="width:260" />
    	 	<font size="2">(标题不超过50个汉字)</font>
    	 	</td>
    	 	
    	 </tr>
    	 <tr>
    	 	<td>
    	 	详细内容
    	 	</td>
    	 	<td>
             <textarea name="nr" cols="45" rows="8"></textarea>
    	 	
    	 	<!--<input type="text" name="bt" value="" style="width:260;height: 260" />
    	 	--><font size="2">(标题不超过500个汉字)</font>
    	 	</td>
    	 </tr><!--
    	<tr>
    	 	<td>
    	 		录入时间
    	 	</td>
    	 	
    	 	<td>
    	 	<input type="text" name="bt" value="<%=form.getDqtime() %>" style="width:260" />
    	 
    	 	</td>
    	 	
    	 </tr>
    	 <tr>
    	 	<td>
    	 		录入人
    	 	</td>
    	 	
    	 	<td>
    	 	<input type="text" name="bt" value="<%=form.getLrr() %>" style="width:260" />
    	 
    	 	</td>
    	 	
    	 </tr>
    	 --><tr>
    		<td colspan="2">
    			<center>
    			<input type="button" name="tij" id="tijiao" value="提交"/>
    			<input type="button" name="fh" id="fanhui"value="返回"/>
    			</center>
    		</td>
    	</tr>
    	</table>
    	<input type="hidden" name="nr1" value="<%=form.getNr()%>"/>
    	<input type="hidden" name="id" value="<%=id %>"/>
    	<input type="hidden" name="accountname" value="<%=accountname %>"/>
    	<input type="hidden" name="accountId" value="<%=accountId %>"/>
    </form>
    </fieldset>
    </center>
  </body>
</html>
<script language="javascript">

if('<%=form.getXxtype()%>'=="" || '<%=form.getXxtype()%>'=="null"){
	document.form1.xxtype.value="0";
}
document.form1.xxtype.value='<%=form.getXxtype()%>';//信息类型//内容
document.form1.nr.value=document.form1.nr1.value;

</script>