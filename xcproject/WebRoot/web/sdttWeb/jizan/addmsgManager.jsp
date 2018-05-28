<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.Double" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.jizhan.JiZhanBean" %>
<%@ page import="com.noki.zwhd.model.DwBean"%>
<%@ page import="com.noki.zwhd.manage.SystemManage" %>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean" %>
<%
	String path = request.getContextPath();
    Account account=(Account)session.getAttribute("account");
    String accountname = account.getAccountName();
 	String xxlx=request.getParameter("xxlx")!=null?request.getParameter("xxlx"):"0";
 	
 	String bz="";
 	bz=request.getParameter("bz")!=null?request.getParameter("bz"):"";//修改传值过来
 	String id=request.getParameter("id");//修改传值过来 id
 String dbname="", nr="",bt="";
  SiteManage bean = new SiteManage();
ArrayList fylist = new ArrayList();

 if(bz!=null&&!"".equals(bz)&&id!=null&&!id.equals("")){
 
 fylist = bean.getxxxg(id,bz);
  if(fylist!=null)
		{
		int fycount = ((Integer)fylist.get(0)).intValue();
		for( int k = fycount;k<fylist.size()-1;k+=fycount){
	//xxlx=(String) fylist.get(k + fylist.indexOf("ID"));			
						       			//num为序号，不同页中序号是连续的
	id = (String) fylist.get(k + fylist.indexOf("ID"));
	dbname = (String) fylist.get(k + fylist.indexOf("NAME"));
	nr = (String) fylist.get(k + fylist.indexOf("NR"));
	bt = (String) fylist.get(k + fylist.indexOf("BT"));
	System.out.println("dbname:"+dbname);
 
 }	
 }
 }
 	
   	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%if(!"1".equals(bz)){ %>
<title>系统消息新增</title>
<% }else{%>
<title>新增系统修改</title>
<%}%>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script language="javascript">
var path='<%=path%>';
var bz='<%=bz%>';
var id='<%=id%>'
function addggao(){
  // if(checkNotSelected(document.form1.xxtype,"信息类型")&&
     // checkNotnull(document.form1.bt," 标题")&&
   	 // checkNotnull(document.form1.nr,"内容")&&
     if(confirm("您将要添加或修改公告信息！确认信息正确！")){
         document.form1.action=path+"/servlet/zhandian?action=addggao&bz="+bz+"&id="+id;
		 document.form1.submit();
      }
     }
     
     
  function fanhui(){
  alert("确定返回吗");
	window.history.back();
     }


//   $(function(){
//             $("#fanhui").click(function(){
// 				window.history.back();
// 			});
// 			$("#baocun").click(function(){
// 				addggao();
// 			});
// 	});

</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
	
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
			<%if(!bz.equals("1")){%>
				<div class="tit01">系统消息新增</div>
			<%}else{%>
				<div class="tit01">系统消息修改</div>
				<%}%>
				<div class="content01_1">
					<table width="100px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
					<tr>
				         <td align="right" width="100px">消息类型：</td>
				         <%if(!bz.equals("1")){%>
							<td width="100px">
								
					        <select name="xxlx" id="xxlx" style="width:80px;">
					        

							<option value="xxtype01">通知公告</option>
							<option value="xxtype02">工作提醒</option>
							<option value="xxtype03"> 提示告警</option>
						
							</select>
							</td>
							<% }else{%>
							<td width="100px">
								
					       <input name="xxlx" id="xxlx" readonly="readonly" value="<%=dbname%>" />
							</td>
							<% }%>
					</tr>	
							 
						<tr>
							<td align="right" width="100px">标题</td>
							<td width="60px">
								<input type="text" name="dbname" value="<%=bt%>" />
							</td>
						</tr>
						
						
						<tr>								
							<td align="right" width="100px">内容</td>
							<td width="100px">
								<textarea  name="nr" cols="40" rows="6" ><%=nr%></textarea>
							</td>
									
						</tr>												
						
						<tr>
							<td align="right" colspan="8" height="60px">
								<input  type="submit" class="btn_c1" name="baocun" onclick="addggao()" id="baocun" value="保存" />&nbsp;&nbsp;&nbsp; 
								<input  type="submit" class="btn_c1" onclick="fanhui()" id="fanhui" value="返回" />&nbsp; 
							</td>
						</tr>
					
					</table>
				</div>
			</div>
		</td>
		</tr>
		</table>
		<input type="hidden" name="zdid" value=""/>
		<input type="hidden" name="id" value="<%=id%>"/>
	</form>	
	
</body>

</html>

