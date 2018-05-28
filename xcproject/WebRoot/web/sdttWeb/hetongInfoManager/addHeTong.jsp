<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.Double"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.*"%>
<%@ page import="com.noki.hetongguanli.Model.HetongModel"%>
<%@ page import="com.noki.hetongguanli.Dao.*"%>
<%@ page import="java.sql.ResultSet"%>
<%
	String id = request.getParameter("id");
	String path = request.getContextPath();
	String action1 = request.getParameter("action");
	String st = "";
	String et = "";
	String cn = "";
	String cd = "";
	String pa = "";
	String pb = "";
	String pm = "";
	if (action1.equals("1")) {
		Integer nall = Integer.parseInt(request.getParameter("id"));
		int id1 = nall.intValue();
		HetongDao bean = new HetongDao();
		SiteManage bean1 = new SiteManage();
		HetongModel ht = bean.Selectall(id1 + "");
		if (ht != null) {
			st = ht.getSTARTTIME();
			et = ht.getENDTIME();
			cn = ht.getCONTRACTNAME();
			cd = ht.getCONTRACTDETAIL();
			pa = ht.getPARTYA();
			pb = ht.getPARTYB();
			pm = ht.getPROJECTAMONNT();
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<%
			if ("1".equals(action1)) {
		%>
		<title>修改合同内容</title>
		<%
			} else {
		%>
		<title>新增合同内容</title>
		<%
			}
		%>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=path%>/javascript/PopupCalendar.js">
		</script>
			<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->

		</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
		</script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
		</script>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
		</script>
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body>
		<form name="form1" action="" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="top">
					<td width="12">
						<img src="../images/space.gif" width="12" height="17" />
					</td>
					<td>
						<div class="content01">

							<%
								if (action1.equals(1)) {
							%>




							<div class="tit01">
								合同内容修改
							</div>
							<%
								} else {
							%>
							<div class="tit01">
								项目合同新增
							</div>
							<%
								}
							%>
							<div class="content01_1">
								<table width="1000px" border="0" cellpadding="2" cellspacing="0"
									class="tb_select">
									<tr>
										<td align="right" width="100px">
											开始时间
										</td>
										<td width="100px">
											<input class="selected_font" type="text" name="STARTTIME"
												value="<%=st%>" readonly="readonly" class="Wdate"
												style="background-color: #FFFFFF; color: grey"
												onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
										</td>
										<td align="right" width="100px">
											结束时间
										</td>
										<td width="100px">
											<input class="selected_font" type="text" name="ENDTIME"
												value="<%=et%>" readonly="readonly" class="Wdate"
												style="background-color: #FFFFFF; color: grey"
												onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />

										</td>
										<td align="right" width="100px">
											甲方
										</td>
										<td width="100px">
											<input name="PARTYA" id="PARTYA" class="selected_font"
												value="<%=pa%>" maxlength="100">
											</input>
										</td>
										<td align="right" width="100px">
											乙方
										</td>
										<td width="180px">
											<input id="PARTYB" name="PARTYB" style="width: 80px"
												value="<%=pb%>" maxlength="100">
											</input>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											合同名称
										</td>
										<td width="180px">
											<input id="CONTRACTNAME" name="CONTRACTNAME"
												style="width: 80px" value="<%=cn%>" maxlength="100">
											</input>
										</td>
										<td align="right" width="100px">
											合同金额
										</td>
										<td width="60px">
											<input id="PROJECTAMONNT" name="PROJECTAMONNT"
												style="width: 80px" value="<%=pm%>" maxlength="100">
											</input>
										</td>
										<td align="right" width="100px">
											合同详情
										</td>
										<td width="60px">
											<input id="CONTRACTDETAIL" name="CONTRACTDETAIL"
												value="<%=cd%>" maxlength="500" />
										</td>
									</tr>
									<tr>
										<td align="right" colspan="8" height="60px">
											<!-- 								<input name="button2" type="submit" class="btn_c1" id="button2" value="临时保存" />&nbsp;  -->
											<input onclick="saveAccount()" type="button" class="btn_c1"
												id="button2" value="提交" />
											&nbsp;
											<input name="button2" type="reset" class="btn_c1"
												id="button2" value="重置" />
											&nbsp;
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<script>

var action = '<%=action1%>';
var path = '<%=path%>';
var id = '<%=id%>';

function saveAccount() {

	if (

	checkNotnull(document.form1.STARTTIME, "开始时间")
			&& checkNotnull(document.form1.ENDTIME, "结束时间")
			&& checkNotnull(document.form1.PARTYA, "甲方")
			&& checkNotnull(document.form1.PARTYB, "乙方")
			&& checkNotnull(document.form1.CONTRACTNAME, "合同名称")
			&& checkNotnull(document.form1.PROJECTAMONNT, "合同金额")
			&& checkNotnull(document.form1.CONTRACTDETAIL, "合同详情")) {

		var pmoney = document.form1.PROJECTAMONNT.value;
//alert(pmoney);
		if (!ismoneys(pmoney)) {
			alert("合同金额必须为数字！");
			return false;
		}
		adddhetong();
	};
	
}
function adddhetong() {
	if (confirm("您将要添加合同信息！确认信息正确！")) {
		document.form1.action = path + "/servlet/HetongManage?action=" + action
				+ "&id=" + id;
		document.form1.submit();
		showdiv("请稍等..............");
	}

}

function ismoneys (str) {
			if (/^(([0-9][0-9]*)|(([0]\.\d{1,4}|[1-9][0-9]*\.\d{1,4})))$/.test(str))
				return true;
			return false;
		}
</script>