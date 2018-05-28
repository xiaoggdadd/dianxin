<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%
String path = request.getContextPath();
String permissionRights="";
String roleId = (String)session.getAttribute("accountRole");
String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
          String roleid = account.getRoleId();//权限
          System.out.println("权限"+roleid);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  <style type="text/css">
  .form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			
		}
  
  
  </style>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
  <%
	permissionRights = commBean.getPermissionRight(roleId, "0101");

	System.out.println(">>>>>>>>>>>>>>..." + permissionRights);
%>
   <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
  <script language="javascript">
  var path='<%=path%>';
  function dfinfo5(id){
    		var url=path+"/web/sys/ggaoxx.jsp?id="+id;		
			var obj = new Object();
			obj.mid='mid';
   			 var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
			}
  function delzd(id){
     if(confirm("您确定删除此公告信息？")){
    
     		document.form1.action=path+"/servlet/zhandian?action=delggao&id="+id;
            document.form1.submit();
    }
    }
     function modifyjz(id){
    
     		document.form1.action=path+"/web/sys/modifyggao.jsp?id="+id;
            document.form1.submit();
           
    }
   $(function(){
			$("#cancelBtn").click(function(){
			window.history.back();
			});
			});
  </script>
  <body>
   <center>
    <fieldset id="regist" >
    	<legend><font style="color:black;font-weight:bold;font-size=16;">公告栏详细信息</font></legend>
    	<form action="" name="form1" method="post">
   <table width="50%"  border="0" cellspacing="1" cellpadding="1" class="form_label">
            <!--
			<tr><td colspan="5">
			  <div id="parent" style="display:inline">
               <hr></hr>
               </div></td></tr>
			-->
			<%
			
       SiteManage beans = new SiteManage();
       	 ArrayList fylist = new ArrayList();
        fylist = beans.getAGgao();
       	 
       	 
		String bt = "",ggtime = "",id = "", lrr="";
		
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
				id = (String) fylist.get(k + fylist.indexOf("ID"));
       			bt = (String) fylist.get(k + fylist.indexOf("BT"));
       			ggtime = (String) fylist.get(k + fylist.indexOf("GGTIME"));
       			lrr = (String) fylist.get(k + fylist.indexOf("LRR"));
       			
       			if (bt == null)
       				bt = "";
       			if (ggtime == null)
       				ggtime = "";
       		

       		
       %>
       <tr class="form_label">
       		
       		<td >
       			<a href="javascript:dfinfo5('<%=id%>')"><%=bt%></a>
       		</td>
       		<td >
       			<%=ggtime%>
       		</td >
      
       		<td class="form_label">
       			<%=lrr%> 
       		</td>
       		
			<td class="form_label">
       			<div id="sc" align="center"><!-- style="display:none;" -->
       			<a href="#" onclick="delzd('<%=id%>')">
       			<font  style="font-size: 12px;">删除</font>
       			</a></div>
       		</td>
       		<td class="form_label">
       			<div id="xg" align="center" >
       			<!-- style="display:none;" -->
       			<a href="#" onclick="modifyjz('<%=id%>')"><font  style="font-size: 12px;">修改</font></a>
       			</div>
       		</td>
       </tr>
       <%
       	}
       %>
        <%
       	}
       %>
       
       <tr><td colspan="3" align="right">
       		</td></tr>
		<tr><td colspan="5">
			  <div id="parent" style="display:inline">
               <hr></hr>
               </div></td></tr>
			<tr>
		</table>
		<div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
			<img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
			<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>      
			</div>
		</form> 
		</fieldset>
		</center>
		<script language="javascript">
var roid='<%=roleid%>';

if(roid=="1"){
document.getElementById("sc").style.display="block";//删除
document.getElementById("xg").style.display="block";//修改
}



</script>
  </body>
</html>
