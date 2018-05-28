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
<%@ page import="java.util.ArrayList,com.noki.jizhan.*"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="java.sql.ResultSet"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String accountname = account.getAccountName();
	String sheng = (String) session.getAttribute("accountSheng");
	String loginId = account.getAccountId();
	String shengId = (String) session.getAttribute("accountSheng");
	String loginName = (String) session.getAttribute("loginName");
	String id = request.getParameter("id");
	SiteModifyBean form = new SiteModifyBean();
	String byqrl = "", glys = "", dj = "", shi = "0", xian = "0", xiaoqu = "0";
	String zhdj = "", jc = "", zddj = "", phddl = "", rl = "", zb = "";
	String sql = "select d.id,d.byqrl,to_char(d.glys,'fm9999999990.00') glys,to_char(d.dj,'fm9999999990.0000') dj,to_char(d.zhdj,'fm9999999990.0000') zhdj,to_char(d.jc,'fm9999999990.0000') jc,to_char(d.zddj,'fm9999999990.00') zddj,d.phddl,d.rl,to_char(d.zb,'fm9999999990.00') zb"
			+ ",(select  ag.agname from d_area_grade ag where ag.agcode =d.xian) as xian"
			+ ",(select  ag.agname from d_area_grade ag where ag.agcode =d.shi) as shi"
			+ ",(select  ag.agname from d_area_grade ag where ag.agcode =d.xiaoqu) as xiaoqu "
			+ " from dgyydgl d where id=" + id + "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		db.connectDb();
		System.out.println("sql：" + sql);
		rs = db.queryAll(sql.toString());
		while (rs.next()) {
			byqrl = rs.getString("BYQRL") == null ? "" : rs
					.getString("BYQRL");
			glys = rs.getString("GLYS") == null ? "" : rs
					.getString("GLYS");
			dj = rs.getString("DJ") == null ? "" : rs.getString("DJ");
			zhdj = rs.getString("ZHDJ") == null ? "" : rs
					.getString("ZHDJ");
			jc = rs.getString("JC") == null ? "" : rs.getString("JC");
			zddj = rs.getString("ZDDJ") == null ? "" : rs
					.getString("ZDDJ");
			phddl = rs.getString("PHDDL") == null ? "" : rs
					.getString("PHDDL");
			rl = rs.getString("RL") == null ? "" : rs.getString("RL");
			zb = rs.getString("ZB") == null ? "" : rs.getString("ZB");
			shi = rs.getString("SHI") == null ? "" : rs
					.getString("SHI");
			xian = rs.getString("XIAN") == null ? "" : rs
					.getString("XIAN");
			xiaoqu = rs.getString("XIAOQU") == null ? "" : rs
					.getString("XIAOQU");
		}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
		</script>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
		</script>
			<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
		</script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
		</script>

		
		<title>详细信息</title>
		
		<script type="text/javascript">
var path = '<%=path%>';
function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = "";
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">";

	} else {
		trObject.style.display = "none";
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">";
	}
}     
 function fanhui(){        
    window.close();
 }    
$(function(){
	$("#liulan").click(function(){
		shoulist();
	});
	$("#cancelBtn").click(function(){
	    fanhui();
	});
$("#resetBtn").click(function(){
	$.each($("form input[type='text']"),function(){
	  $(this).val("");
          });
	});
// $("#saveBtn").click(function(){
// 	saveAccount();
	
// 	});
$("#vnameBtn").click(function(){
	vName();
	});
});
			
</script>
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<jsp:useBean id="flowBean" scope="page"
		class="com.noki.mobi.flow.javabean.FlowBean">
	</jsp:useBean>
	<body>
		<form action="" name="form1" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="top">

					<td width="12">
						<img src="../images/space.gif" width="12" height="17" />
					</td>
					<td>
						<div class="content01">

							
							<div class="tit01">
								详细信息
							</div>
							
							<div class="content01_1">
								<table width="100px" border="0" cellpadding="2" cellspacing="0"
									class="tb_select">
									<tr>
										<td align="right" width="100px">
											城市
										</td>
										<td width="100px">
											<input type="text" name="shi" id="shi" readonly="readonly" value="<%=shi%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											区县
										</td>
										<td width="100px">
											<input type="text" name="xian" id="xian" readonly="readonly" value="<%=xian%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											乡镇
										</td>
										<td width="100px">
											<input type="text" name="xiaoqu" id="xiaoqu" readonly="readonly" value="<%=xiaoqu%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											变压器容量（KVA）
										</td>
										<td width="60px">
											<input type="text" name="byqrl" id="byqrl" readonly="readonly" value="<%=byqrl%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											功率因素
										</td>
										<td width="60px">
											<input type="text" name="glys" id="glys" readonly="readonly" value="<%=glys%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											一般工商及其他电价（元）
										</td>
										<td width="100px">

											<input type="text" name="dj" id="dj" onchange="change()" readonly="readonly"
												value="<%=dj%>" style="box-sizing: border-box; width: 130px" />
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											大工业峰谷平综合电价（元）
										</td>
										<td width="100px">
											<input type="text" name="zhdj" id="zhdj" readonly="readonly" value="<%=zhdj%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											价差
										</td>
										<td width="100px">
											<input type="text" name="jc" id="jc" readonly="readonly" value="<%=jc%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											最大需量40%基本电价（元）
										</td>
										<td width="100px">
											<input type="text" name="zddj" id="zddj" readonly="readonly" value="<%=zddj%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											平衡点电量（KWH）
										</td>
										<td width="100px">
											<input type="text" name="phddl" id="phddl" readonly="readonly" value="<%=phddl%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											平衡点电量折算容量
										</td>
										<td width="100px">
											<input type="text" name="rl" id="rl" readonly="readonly" value="<%=rl%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											占变压器比(%)
										</td>
										<td width="100px">
											<input type="text" name="zb" id="zb" readonly="readonly" value="<%=zb%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
									<tr>
										<td align="right" colspan="6" height="60px">
											<input onclick="fanhui()" type="button" class="btn_c1"
													id="button2" value="返回" />&nbsp;
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<script type="text/javascript">

</script>
		</form>

	</body>

</html>

