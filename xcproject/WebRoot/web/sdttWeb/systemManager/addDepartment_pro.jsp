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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js">
</script>

<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		
<script src="<%=path%>/javascript/tx.js">
</script>
		
 <script src="<%=path%>/javascript/jquery-3.3.1.min.js"></script>
 <script src="<%=path%>/javascript/jquery-migrate-1.2.1.min.js"></script>
</script>
		

   <%
		    Account account=(Account)session.getAttribute("account");
		    String accountname = account.getAccountName();
		    String shengId = (String) session.getAttribute("accountSheng");
		    String loginName = (String) session.getAttribute("loginName");
		 	String sheng = request.getParameter("sheng");
			String bianma = request.getParameter("bianma")!=null?request.getParameter("bianma"):"";
		 	
		 	System.out.println("父级编码："+bianma);
		 	
		 	
		 	String bz="";
		 	bz=request.getParameter("bz")!=null?request.getParameter("bz"):"";//修改传值过来
		 	String id=request.getParameter("id")!=null?request.getParameter("id"):"";;//修改传值过来 id
		    String deptcode="",deptname="",fdeptcode="", shi="0", fdeptname="";
		    SiteManage bean = new SiteManage();
		    ArrayList fylist = new ArrayList();
						 
		    fylist = bean.getbumenguanli(id,bz);
		    if(fylist!=null){
				int fycount = ((Integer)fylist.get(0)).intValue();
				for( int k = fycount;k<fylist.size()-1;k+=fycount){
				   id = (String) fylist.get(k + fylist.indexOf("ID"));
				   deptcode = (String) fylist.get(k + fylist.indexOf("DEPTCODE"));
				   deptname = (String) fylist.get(k + fylist.indexOf("DEPTNAME"));
				   fdeptcode=(String) fylist.get(k + fylist.indexOf("FDEPTCODE"));
				   fdeptname=(String) fylist.get(k + fylist.indexOf("FDEPTNAME"));
				   shi=(String) fylist.get(k + fylist.indexOf("SHI"));
						 
               }	
          }
 	
   	
%>
<%if("".equals(id)){ %>
<title>部门管理新增</title>
<% }else{%>
<title>部门系统修改</title>
<%}%>
<link href="../css/content.css" rel="stylesheet" type="text/css" />

<script language="javascript">
var path='<%=path%>';
var id='<%=id%>';
function addbumen(){
	if(checkNotSelected(document.form1.shi,"城市")&&
			checkNotnull(document.form1.deptname,"部门名称")){
			 
		if(confirm("确认保存？")){
		 document.form1.action=path+"/servlet/zhandian?action=saveDept";
		 document.form1.submit();
		 }
	}
}   
     
 function fanhui(){
	     alert("确定返回吗");
		 window.close();
		     }
		     
function changeCity(){
    var fdeptcode = "<%=sheng %>";
	var shiObj = document.form1.shi;
	if(shiObj.value=="0"){
		document.form1.fdeptname.value="*分公司";
	}else{
		//var text = shiObj.options[shiObj.selectedIndex].text;
		//document.form1.fdeptname.value = text+"分公司";
		
		$.ajax({
		   type: 'post',
		   url: path + '/servlet/commonBeanServlet?action=dept',
		   cache: false,
		   data: {
		     shi: shiObj.value,
		     fdeptcode: fdeptcode
		   },
		   dataType: 'json',
		   success: function(data){
		      if(data && data.length>0){
		         var dept = data[0].split("\|")
		         document.form1.fdeptcode.value = dept[0];
		         document.form1.fdeptname.value = dept[1];
		      }
		   },
		   error: function(){
		     return;
		   }
		   
		});
		
		
	}
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
	     <input type="hidden" name="id" value="<%=id %>"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr valign="top">
				<td width="12"><img src="../images/space.gif" width="12" height="17" />
						</td>
				<td>
					<div class="content01">
						<%if(id.equals("")){%>
							<div class="tit01">部门新增</div>
							<%}else{%>
							<div class="tit01">部门修改</div>
							<%}%>
							<div class="content01_1">
								<table width="100px" border="0" cellpadding="2" cellspacing="0" class="tb_select">
									<tr>
										<td align="right" width="100px">
											城市
										</td>
										<td width="100px">
											<select name="shi" class="selected_font"
												onchange="changeCity()"
												style="box-sizing: border-box; width: 130px;">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList shenglist = new ArrayList();
													shenglist = commBean.getAgcode(shengId, "0", loginName);
													if (shenglist != null) {
														String sfid = "", sfnm = "";
														int scount = ((Integer) shenglist.get(0)).intValue();
														for (int i = scount; i < shenglist.size() - 1; i += scount) {
															sfid = (String) shenglist.get(i
																	+ shenglist.indexOf("AGCODE"));
															sfnm = (String) shenglist.get(i
																	+ shenglist.indexOf("AGNAME"));
												%>
												<option value="<%=sfid%>"><%=sfnm%></option>
												<%
													}
													}
												%>
											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
											&nbsp;
										</td>
									</tr>
									<tr>		
										<td align="right" width="100px">市公司名称</td>
										<td width="60px"><input type="text" name="fdeptname" id="fdeptname" value="<%=fdeptname %>" readonly="readonly" style="box-sizing: border-box; width: 130px;"/>
										<input type="hidden" name="fdeptcode" id="fdeptcode" value="<%=fdeptcode %>" />
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">部门名称</td>
										<td width="100px"><input type="text" id="deptname" name="deptname" value="<%=deptname %>" maxlength="20" style="box-sizing: border-box; width: 130px;"/>
										<input type="hidden" id="deptcode" name="deptcode" value="<%=deptcode %>" />
										<span style="color: #FF0000; font-weight: bold">*</span>
										</td>

									</tr>
									<tr>
										<td align="right" colspan="8" height="60px">
											<input type="button" class="btn_c1" id="baocun"  value="保存" />&nbsp;&nbsp;&nbsp; 
											<input type="button" class="btn_c1" onclick="fanhui()" value="返回" />&nbsp;</td>
									</tr>
		
									</table>
								</div>
							</div></td>
					</tr>
				</table>
	</form>
	<script>
	  document.form1.shi.value="<%=shi%>";
	</script>
</body>
</html>

