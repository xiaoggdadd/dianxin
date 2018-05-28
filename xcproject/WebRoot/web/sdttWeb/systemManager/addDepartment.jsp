<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.Double"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.jizhan.JiZhanBean"%>
<%@ page import="com.noki.zwhd.model.DwBean"%>
<%@ page import="com.noki.zwhd.manage.SystemManage"%>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%
							String path = request.getContextPath();
						    Account account=(Account)session.getAttribute("account");
						    String accountname = account.getAccountName();
						 	//String bianma=request.getParameter("bianma")!=null?request.getParameter("boanma"):"";//修改传值过来
						 	
							String bianma = request.getParameter("bianma")!=null?request.getParameter("bianma"):"";
						 	
						 	System.out.println("父级编码："+bianma);
						 	
						 	
						 	String bz="";
						 	bz=request.getParameter("bz")!=null?request.getParameter("bz"):"";//修改传值过来
						 	String id=request.getParameter("id");//修改传值过来 id
						    String bianhao="",mingcheng="";
						    SiteManage bean = new SiteManage();
						    ArrayList fylist = new ArrayList();
						      
						      
						
						    if(bz!=null&&!"".equals(bz)&&id!=null&&!id.equals("")&&bz.equals("1")){
						 
						    fylist = bean.getbumenguanli(id,bz);
						    if(fylist!=null)
								{
								int fycount = ((Integer)fylist.get(0)).intValue();
								for( int k = fycount;k<fylist.size()-1;k+=fycount){
							   id = (String) fylist.get(k + fylist.indexOf("ID"));
							   bianhao = (String) fylist.get(k + fylist.indexOf("DEPTCODE"));
							   mingcheng = (String) fylist.get(k + fylist.indexOf("DEPTNAME"));
							   bianma=(String) fylist.get(k + fylist.indexOf("FDEPTCODE"));
						 
 }	
 }
 }
 	
   	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js">
</script>

<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		
<script src="<%=path%>/javascript/tx.js">
</script>
		
<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%if(!"1".equals(bz)){ %>
<title>部门管理新增</title>
<% }else{%>
<title>部门系统修改</title>
<%}%>
<link href="../css/content.css" rel="stylesheet" type="text/css" />

<script language="javascript">
var path='<%=path%>';
var bz='<%=bz%>';
var id='<%=id%>';
function addbumen(){
var bumen=document.form1.bumen.value;
var mingcheng=document.form1.mingcheng.value;
var bianma=document.form1.bianma.value;
var fglength=bianma.length;
if(fglength==2){
	fglength=1;
	}else if(fglength==4){
	fglength=2;
	}else if(fglength==6){
	fglength=3;
	}else if(fglength==8){
	fglength=4;
	}
		
	if(bumen!=null&&bumen!=""&&mingcheng!=null&&mingcheng!=""&&bianma!=null&&bianma!=""){
			 
		if(confirm("确认保存？")){
		 document.form1.action=path+"/servlet/zhandian?action=addbumen1&bianma="+bianma+"&bz="+bz+"&id="+id+"&fglength="+fglength;
		 document.form1.submit();
		 }
		  
		}else{
		  alert("数据不允许为空！请填写！");
		  }
		     }
		   
     
 function fanhui(){
	     alert("确定返回吗");
		 window.close();
		     }
		     
		     
		     
$(function(){
$("#baocun").click(function(){
		addbumen();
			});
		});
		
		
		
		
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
				<td width="12"><img src="../images/space.gif" width="12" height="17" />
						</td>
				<td>
					<div class="content01">
						<%if(!bz.equals("1")){%>
							<div class="tit01">部门新增</div>
							<%}else{%>
							<div class="tit01">部门修改</div>
							<%}%>
							<div class="content01_1">
								<table width="100px" border="0" cellpadding="2" cellspacing="0" class="tb_select">
										<tr>
											<td align="right" width="100px">上级编号:</td>
											<td width="60px"><input type="text" name="bianma"  id="bianma" value="<%=bianma %>" /></td>
										</tr>

										
										<tr>		
											<td align="right" width="100px">部门编号:</td>
											<td width="60px"><input type="text" name="bumen" id="bumen"
												value="<%=bianhao%>" onkeyup="value=value.replace(/[^(\d)]/g,'')"/> *<font color="red">填写为数字，其他的将会被重置</font></td>
										</tr>
									
									
										<tr>
											<td align="right" width="100px">部门名称:</td>
											<td width="100px"><input type="text" id="mingcheng" name="mingcheng"
												value="<%=mingcheng%>" /></td>

										</tr>
		
		
		
		
										<tr>
											<td align="right" colspan="8" height="60px">
												<input type="button" class="btn_c1" name="quyu1" id="baocun"  value="保存" />&nbsp;&nbsp;&nbsp; 
												<input type="button" class="btn_c1" onclick="fanhui()" value="返回" />&nbsp;</td>
										</tr>
		
									</table>
								</div>
							</div></td>
					</tr>
				</table>
	</form>
	
</body>
</html>

