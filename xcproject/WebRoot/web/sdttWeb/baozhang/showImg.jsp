<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.Double"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="com.noki.common.OrgAndRole"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.List"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="com.noki.jizhan.SiteModifyBean"%>
<%@page import="java.math.BigDecimal"%>
<%
	String path = request.getContextPath();
	String shengId = (String) session.getAttribute("accountSheng");
	String loginName = (String) session.getAttribute("loginName");
	Account account = (Account) session.getAttribute("account");

	String accountname = account.getAccountName();
	SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd");
	String entrytime = mat.format(new Date());
	String pathImg = request.getParameter("path");
	System.out.println("pathImg?>?>?>?>?>?>" + pathImg);
	String allpath = "";
	DataBase db = new DataBase();
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	sql.append("SELECT tai.IMAGEPATH  FROM TBL_APP_IMAGEPATH tai");
	db.connectDb();
	rs = db.queryAll(sql.toString());
	while (rs.next()) {
		allpath = rs.getString("IMAGEPATH") == null ? "" : rs
				.getString("IMAGEPATH");
	}
	allpath = allpath + "/" + pathImg;

//	allpath = allpath + "/ceshi.jpg";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>抄表图片</title>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
		</script>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<link
	href="<%=path%>/javascript/jQuery-ComboSelect/css/combo.select.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
		</script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	
		</script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js">
		</script>
<script type="text/javascript"
	src="<%=path%>/javascript/PopupCalendar.js">
		</script>
<script type="text/javascript"
	src="<%=path%>/javascript/PopupCalendar_ny.js">
		</script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>

<script type="text/javascript"
	src="<%=path%>/javascript/jQuery-ComboSelect/js/jquery.combo.select.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/echarts.js"></script>
<script type="text/javascript">
//		$(function() {
//	$('#rsname').comboSelect();
//});
var path = '<%=path%>';
</script>
</head>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="diaobiaoBean" scope="page"
	class="com.noki.jizhan.DianBiaoBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page"
	class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
	<input type="hidden" name="allpath" value="<%=allpath%>"
		style="box-sizing: border-box; width: 130px" />

	<div style="padding:0px 0px;width:350px;height:540px; ">
		<img src="picCreate.jsp?ppath=<%=allpath %>" alt="抄表图片展示" />
	</div>
</body>
</html>
