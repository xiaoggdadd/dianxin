<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.*" %>
<%@ page import="java.lang.Double" %>
<%
	String path = request.getContextPath();
	String id = request.getParameter("id");
	String zdid = request.getParameter("zdid");
	DianBiaoForm form = new DianBiaoForm();
	form = form.getInfoData(id);//修改电表信息页面带出的信息
    String countbzw = form.getCountbzw();//"0"---1块电表;"1"---多块电表
	DianBiaoForm form1 = new DianBiaoForm();
	form1 = form1.getQyztCount(id,zdid);//当前站点下的非当前电表的启用电表总数和关闭电表总数
	double qydb = form1.getDbqyzs();//启用电表总数
	double gbdb = form1.getDbgbzs();//关闭电表总数
	double count = qydb + gbdb;
    Account account=(Account)session.getAttribute("account");
    String roleid = account.getRoleId();//权限
    String accountname = account.getAccountName();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改电表</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
<form action="" name="form1" method="post" id="form">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
	
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">电表修改</div>
				<div class="content01_1">
					<table width="1000px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="100px">电表名称</td>
							 <td width="25%">
					            <input type="text" name="dbname" value=""  class="selected_font"/>        
					         	<input type="hidden" name="dbid" value=""  class="form" style="width:130px"/>         				 
					         </td>
							<td align="right" width="100px">电费支付类型</td>
							<td width="25%"><input type="text" name="dfzflx" value="<%=form.getDfzflx() %>" class="selected_font" readonly="readonly"/> </td>
							
							<td align="right" width="100px">倍率</td>
							<td width="25%"><input type="text" name="beilv" value="" class="selected_font3"/></td>
						</tr>
						<tr>
					
							<td align="right" width="100px">初始使用时间</td>
							<td width="25%">
					         	<input class="selected_font" type="text" name="cssytime"
									value="<%if (null != request.getParameter("cssytime"))   out.print(request.getParameter("cssytime"));%>"
									class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
					         </td>
							
						</tr>			
						<tr>
							<td align="right" colspan="8" height="60px">
								<input name="button2" type="submit" class="btn_c1" id="button2" value="临时保存" />&nbsp; 
								<input name="button2" type="submit" class="btn_c1" id="button2" value="提交审批" />&nbsp; 
								<input name="button2" type="submit" class="btn_c1" id="button2" value="重置" />&nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td></tr></table>
		</form>
</body>
</html>
<script type="text/javascript">
document.form1.dbid.value='<%=form.getDbid()%>';
//document.form1.dllx.value='=form.getDllx()%>';
//document.form1.ydsb.value='=form.getYdsb()%>';
//document.form1.sszy.value='=form.getSszy()%>';
document.form1.dbyt.value='<%=form.getDbyt()%>';
document.form1.csds.value='<%=form.getCsds()%>';
document.form1.cssytime.value='<%=form.getCssytime()%>';
document.form1.beilv.value='<%=form.getBeilv()%>';
document.form1.dbxh.value='<%=form.getDbxh()%>';
document.form1.memo.value='<%=form.getMemo()%>';
//document.form1.netpoint_name.value='=form.getNetpoint_name()%>';
//document.form1.netpoint_id.value='=form.getNetpoint_id()%>';
//document.form1.yhdl.value='<%=form.getYhdl()%>';
document.form1.dfzflx.value='<%=form.getDfzflx()%>';
document.form1.dbname.value='<%=form.getDbname()%>';
document.form1.linelosstype.value='<%=form.getLinelosstype()%>';
document.form1.linelossvalue.value='<%=form.getLinelossvalue()%>';
document.form1.dbqyzt.value='<%=form.getDbqyzt()%>';
</script>
