<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.Double"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="com.noki.hetongguanli.Dao.*"%>

<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean"%>
<%@ page import="java.sql.ResultSet"%>

<%@ page import="com.noki.hetongguanli.Model.HetongModel"%>
<%
	String id = request.getParameter("id");
int a=0;
	String st = "";
	String et = "";
	String cn = "";
	String cd = "";
	String pa = "";
	String pb = "";
	String pm = "";
if(a==0){
	Integer nall = Integer.parseInt(request.getParameter("id"));
	int id1 = nall.intValue();
	HetongDao bean = new HetongDao();
	SiteManage bean1 = new SiteManage();
	HetongModel ht = bean.Selectall(id1+"");
	if (ht != null) {
		st = ht.getSTARTTIME();
		et = ht.getENDTIME();
		cn = ht.getCONTRACTNAME();
		cd = ht.getCONTRACTDETAIL();
		pa = ht.getPARTYA();
		pb = ht.getPARTYB();
		pm = ht.getPROJECTAMONNT();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>详细查询</title>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr valign="top">
				<td width="12">
					<img src="../images/space.gif" width="12" height="17" />
				</td>
				<td>
					<div class="content01">
						<div class="tit01">
							详细数据
						</div>
						<div class="content01_1">
							<table width="1000px" border="0" cellpadding="2" cellspacing="0"
								class="tb_select">
								<tr>
									<td align="right" width="100px">
										开始时间
									</td>
									<td width="100px">
										<input value='<%=st%>' readonly="readonly" />
									</td>
									<td align="right" width="100px">
										结束时间
									</td>
									<td width="100px">
										<input readonly="readonly" value='<%=et%>' />
									</td>
									<td align="right" width="100px">
										甲方
									</td>
									<td width="100px">
										<input readonly="readonly" value='<%=pa%>' />
									</td>
								</tr>
								<tr>
									<td align="right" width="100px">
										乙方
									</td>
									<td width="100px">
										<input value='<%=pb%>' readonly="readonly" />
									</td>

									<td align="right" width="100px">
										合同名称
									</td>
									<td width="100px">
										<input value='<%=cn%>' readonly="readonly" />
									</td>
									<td align="right" width="100px">
										项目金额
									</td>
									<td width="100px">
										<input value='<%=pm%>' readonly="readonly" />
									</td>
								</tr>

								<tr>
									<td align="right" width="100px">
										项目金额
									</td>
									<td width="100px">
										<input value='<%=cd%>' readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td align="right" colspan="8" height="60px">
										<input onclick="fanhui()" type="submit" class="btn_c1"
											id="button2" value="返回" />
									</td>
								</tr>
								<%} %>

							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<script>
function fanhui() {
	window.close();
}
</script>
	</body>
</html>

