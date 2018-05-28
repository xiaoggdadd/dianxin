<%@page import="com.noki.zwhd.model.DianbiaoBean"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean"%>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="com.noki.database.DbException"%>
<%@ page import="com.noki.util.Query"%>
<%@ page import="com.noki.zwhd.manage.WyManage"%>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page import="com.noki.zwhd.model.WydfftBean"%>
<%
	//如果电量管理的合理，在添加电费单的的时候  电量，本次抄表时间会自动带出
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
	String accountid = account.getAccountName();
	String id = request.getParameter("id");
	WyManage wyManage = new WyManage();
	ZhandianBean zhandian = wyManage.searchZdByWyid(id);
	DianbiaoBean dianbao = wyManage.searchDbByWyid(id);

	
%>
<html>
<head>
<title></title>

<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	width: 130px;
}

.form_labell {
	font-family: 宋体;
	font-size: 12px;
	width: 70px;
}

.form {
	height: 19px;
	width: 130px;
}

.formm {
	text-align: right;
	height: 19px;
	width: 80px;
}

.formr {
	text-align: right;
	height: 19px;
	width: 130px;
}

.form1 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	height: 19px;
	width: 130px;
}

.form2 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: right;
	height: 19px;
	width: 130px;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>

</head>
<jsp:useBean id="roleBean" scope="page"
	class="com.noki.mobi.sys.javabean.RoleBean"></jsp:useBean>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<body class="body" style="overflow-x: hidden;">
	<br>
	<LINK href="../../images/css.css" type="text/css" rel=stylesheet>
	<form action="" name="form1" method="POST" id="form">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="form_label">
			<tr>
				<td>
					<div style="width:700px;height:50px">
						<img alt="" src="<%=path%>/images/btt.bmp" width="100%"
							height="100%" /> <span
							style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">区县抄表</span>
					</div></td>
			</tr>
		</table>
		<table border="0" width="100%">
			<tr>
				<td width="83%" scope="2">
					<table width="100%" border="0" cellspacing="1" cellpadding="1">
						<tr>
							<td colspan="6" class="form_label"><img
								src="../../images/v.gif" width="15" height="15" /> 站点信息</td>
						</tr>
						<tr>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>地区：</div></td>
							<td><input type="text" name="shi" readonly
								value="<%=zhandian.getXIAN()%>" class="form1" /></td>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>站点类型：</div></td>
							<td><input type="text" name="stationtype" readonly
								value="<%=zhandian.getJZTYPE()%>" class="form1" /></td>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>原产权单位：</div></td>
							<td><input type="text" name="ycq" readonly
								value="<%=zhandian.getGSF()%>" class="form1" /></td>
						</tr>
						<tr>

							<td bgcolor="#DDDDDD" class="form_label">
								<div>站点名称：</div></td>
							<td><input type="text" name="jzname" readonly
								value="<%=zhandian.getJZNAME()%>" class="form1" /></td>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>站点编码：</div></td>
							<td><input type="text" name="jzcode" readonly
								value="<%=zhandian.getJZCODE()%>" class="form1" /></td>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>物理站点编码：</div></td>
							<td><input type="text" name="wlzdbm" readonly
								value="<%=zhandian.getWLZDBM()%>" class="form1" /></td>

						</tr>
						<tr>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>供电方式：</div></td>
							<td><input type="text" name="zfdh" readonly
								value="<%=zhandian.getGDFS()%>" class="form2" /></td>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>供电方名称：</div></td>
							<td><input type="text" name="gdfname" readonly
								value="<%=zhandian.getGDFNAME()%>" class="form1" /></td>
						</tr>
						<tr>
							<td colspan="6" class="form_label"><img
								src="../../images/v.gif" width="15" height="15" /> 电表信息</td>
						</tr>
						<tr>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>电表户号：</div></td>
							<td><input type="text" name="dbbm" readonly
								value="<%=dianbao==null?"":dianbao.getDBBM()%>" class="form2" /></td>
						</tr>
						<tr>
							<td colspan="6" class="form_label"><img
								src="../../images/v.gif" width="15" height="15" /> 抄表信息</td>
						</tr>
						<tr>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>缴费起始日期：</div></td>


							<td><input type="text" id="jfqsrq" name="jfqsrq"
								onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"  align="right" class="form"/> <span
								style="color: #FF0000; font-weight: bold">*</span></td>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>缴费终止日期：</div></td>
							<td><input type="text" id="jfzzrq" name="jfzzrq"
								onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" align="right" class="form"/> <span
								style="color: #FF0000; font-weight: bold">*</span></td>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>电流：</div></td>
							<td><input type="text" name="dianliu" class="form" /> <span
								style="color: #FF0000; font-weight: bold">*</span></td>
						</tr>
						<tr>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>起码：</div></td>
							<td><input type="text" id="qima" name="qima" value=""
								class="form" /> <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>止码：</div></td>
							<td><input type="text" id="zhima" name="zhima" class="form" />
								<span style="color: #FF0000; font-weight: bold">*</span></td>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>电量：</div></td>
							<td><input type="text" id="dianliang" name="dianliang" onmouseout="document.getElementById('dianliang').value=document.getElementById('zhima').value-document.getElementById('qima').value"
								value="" class="form" /> <span
								style="color: #FF0000; font-weight: bold">*</span>
							</td>
						</tr>
						<tr>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>损耗：</div></td>
							<td><input type="text" id="sunhao" name="sunhao"
								class="form" /> <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>上次抄表时间：</div></td>
							<td><input type="text" id="lastcbsj" name="lastcbsj"
								onFocus="getDateString(this,oCalendarChs)" class="form"
								onpropertychange="endmonthzq()" /> <span
								style="color: #FF0000; font-weight: bold">*</span></td>
							<td bgcolor="#DDDDDD" class="form_label">
								<div>本次抄表时间：</div></td>
							<td><input type="text" id="thiscbsj" name="thiscbsj"
								onFocus="getDateString(this,oCalendarChs)" class="form"
								onpropertychange="endmonthzq()" /> <span
								style="color: #FF0000; font-weight: bold">*</span></td>
						</tr>
						<tr>
							<td><input type="hidden" name="lastgree01" id="lastgree01"
								value="" /></td>
							<td colspan="6">
								<div id="cancelBtn"
									style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 0px">
									<img src="<%=path%>/images/quxiao.png" width="100%"
										height="100%"> <span
										style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
								</div>
								<div id="resetBtn"
									style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 11px">
									<img src="<%=path%>/images/2chongzhi.png" width="100%"
										height="100%"> <span
										style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
								</div> 
								<div id="saveBtn"
									style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 22px">
									<img src="<%=path%>/images/baocun.png" width="100%"
										height="100%"> <span
										style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">保存</span>
								</div> 
							</td>
						</tr>
					</table></td>
			</form>

		
</body>
</html>