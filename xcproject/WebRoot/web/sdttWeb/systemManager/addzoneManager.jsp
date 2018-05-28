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
						 	
						 	
						 	String bz="";
						 	bz=request.getParameter("bz")!=null?request.getParameter("bz"):"";//修改传值过来
						 	String id=request.getParameter("id");//修改传值过来 id
						      String bianhao="",mingcheng="";
						      SiteManage bean = new SiteManage();
						      ArrayList fylist = new ArrayList();
						
						   if(bz!=null&&!"".equals(bz)&&id!=null&&!id.equals("")){
						 
						   fylist = bean.getquyuguanli(id,bz);
						    if(fylist!=null)
								{
								int fycount = ((Integer)fylist.get(0)).intValue();
								for( int k = fycount;k<fylist.size()-1;k+=fycount){
									
									//num为序号，不同页中序号是连续的
							   id = (String) fylist.get(k + fylist.indexOf("AGID"));
							   
							   bianhao = (String) fylist.get(k + fylist.indexOf("AGCODE"));
							   mingcheng = (String) fylist.get(k + fylist.indexOf("AGNAME"));
							
						 
 }	
 }

 }
 	
   	
%>
		<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<html>
		<head>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%if(!"1".equals(bz)){ %>
		<title>区域管理新增</title>
		<% }else{%>
		<title>区域系统修改</title>
		<%}%>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
		<script language="javascript">
		var path='<%=path%>';
		var bz='<%=bz%>';
		var id='<%=id%>';
		function addquyu(){
		var quyu=document.form1.quyu.value;
		var mingcheng=document.form1.mingcheng.value;
			if(quyu!="null"&&quyu!=""&&mingcheng!="null"&&mingcheng!=""){
		     if(confirm("您确认添加信息！")){
		         document.form1.action=path+"/servlet/zhandian?action=addquyu1&bz="+bz+"&id="+id;
				 document.form1.submit();
		      }
		     }else{
		     alert("数据不能为空！请填写");
		     }
		    }
		     
		  function fanhui(){
		  alert("确定返回吗");
			window.close();
		     }
		
		
// 		
		
		 </script>
		
		</head>
		
		<jsp:useBean id="commBean" scope="page"
			class="com.noki.mobi.common.CommonBean">
		</jsp:useBean>
		<jsp:useBean id="ztcommon" scope="page"
			class="com.noki.mobi.common.ZtCommon">
		</jsp:useBean>
		<body>
			<form action="" name="form1" method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr valign="top">
		
						<td width="12"><img src="../images/space.gif" width="12"
							height="17" />
						</td>
						<td>
							<div class="content01">
								<%if(!bz.equals("1")){%>
								<div class="tit01">区域管理新增</div>
								<%}else{%>
								<div class="tit01">区域管理修改</div>
								<%}%>
								<div class="content01_1">
									<table width="100px" border="0" cellpadding="2" cellspacing="0"
										class="tb_select">
									
										<tr>
											<td align="right" width="100px">区域编号:</td>
											<td width="60px"><input type="text" name="quyu"
												value="<%=bianhao%>" onkeyup="value=value.replace(/[^(\d)]/g,'')"/>
												<span style="color: #FF0000; font-weight: bold">*</span>
												<font color="red">填写为数字，其他的将会被重置</font></td>
										</tr>
										
										<tr>
											<td align="right" width="100px">区域名称:</td>
											<td width="100px"><input type="text" name="mingcheng"
												value="<%=mingcheng%>" /></td>
		
										</tr>
		
		
		
		
										<tr>
											<td align="right" colspan="8" height="60px"><input
												type="button" class="btn_c1" name="quyu1" onclick="addquyu()"
												id="quyu1" value="保存" />&nbsp;&nbsp;&nbsp; <input
												type="button" class="btn_c1" onclick="fanhui()" 
												value="返回" />&nbsp;</td>
										</tr>
		
									</table>
								</div>
							</div></td>
					</tr>
				</table>
				<input type="hidden" name="zdid" value="" /> <input type="hidden"
						name="id" value="<%=id%>" />
	</form>

</body>

</html>

