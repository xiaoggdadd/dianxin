<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@page import="com.noki.electricfees.javabean.*"%>
<%@page import="com.noki.tongjichaxu.javabean.fybzCountBean"%>
<%@page import="com.noki.tongjichaxu.javabean.fybzbean"%>
<%@page import="com.noki.function.*"%>
<%
	String bz = request.getParameter("bz") != null ? request
			.getParameter("bz") : "0";
	String sheng = (String) session.getAttribute("accountSheng");
	String roleId = (String) session.getAttribute("accountRole");
	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : "0";
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";
	String fysx = request.getParameter("fysx") != null ? request
			.getParameter("fysx") : "0";
	String zdsx = request.getParameter("zdsx") != null ? request
			.getParameter("zdsx") : "0";//站点属性
	String sbzyf = request.getParameter("sbzyf") != null ? request
			.getParameter("sbzyf") : ""; //报账月份
	String ebzyf = request.getParameter("ebzyf") != null ? request
			.getParameter("ebzyf") : ""; //报账月份
	String xxft = request.getParameter("xxft") != null ? request
			.getParameter("xxft") : "0";//详细分摊
	String fzyx = request.getParameter("fzyx") != null ? request
			.getParameter("fzyx") : "0";//分专业线
	String bzw = request.getParameter("bzw") != null ? request
			.getParameter("bzw") : "";//标志位

	String whereStr = "";
	String whereyc = "";
	String wherezc = "";
	String fysxyjStr = "";
	String fysxyjStr1 = "";
	String fysxyzStr = "";
	String fysxyzStr1 = "";
	String wherebz = "", ww = "", dd = "";
	String path = request.getContextPath();
	String loginName = (String) session.getAttribute("loginName");
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();

	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String permissionRights = "";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

	</head>
	<style>
.style1 {
	color: red;
	font-weight: bold;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height: 20px
}

.selected_font {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 120%;
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}

;
.memo {
	border: 1px #C8E1FB solid
}

.memo {
	border: 1px #888888 solid
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

#id1 {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

.bttcn {
	color: black;
	font-weight: bold;
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}

.form_la {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}
</style>
	<script type="text/javascript"
		src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
	<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
	<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
	<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
	<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
	<script type="text/javascript">




function zd(zdid) {
	var path = '<%=path%>';
	window
			.open(
					path
							+ "/web/query/basequery/sitepaymentstatisticscentreyf.jsp?zdid="
							+ zdid, '',
					'width=500,height=400,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
}
function modifyjz(id) {
	var path = '<%=path%>';
	window
			.open(path + "/web/jizhan/zhandianinfo.jsp?id=" + id, '',
					'width=500,height=400,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
	//document.form1.action=path+"/web/jizhan/zhandianinfo.jsp?id="+id
	//document.form1.submit();
}
function information(dbid, dlid) {

	var path = '<%=path%>';
	window
			.open(path + "/web/query/basequery/showdfxx.jsp?dbid=" + dbid
					+ "&dlid=" + dlid, '',
					'width=500,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
}
function dfinfo5(dbid, dlid) {
	var path = '<%=path%>';
	window
			.open(path + "/web/query/basequery/yfftan.jsp?dbid=" + dbid
					+ "&dlid=" + dlid, '',
					'width=500,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')

}
</script>
	<body>
		<form action="" name="form1" method="POST">

			<%
				if (bzw != null && !bzw.equals("") && !bzw.equals("0")) {
			%>
			<%
				}
			%>
			<table width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr class="relativeTag">
					<td width="2%" bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							ID
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							所在地区
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							站点名称
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							电表名称
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							电费支付类型
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							一级分摊比例
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							详细分摊比例
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							报账月份
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							折算后用电量
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							实际电费
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							结算周期
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							录入人员
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							录入时间
						</div>
					</td>
					<td bgcolor="#DDDDDD" class="form_label">
						<div align="center" class="bttcn">
							站点详细分摊
						</div>
					</td>
				</tr>
				<%
					if (shi != null && !shi.equals("") && !shi.equals("0")) {
						whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
						wherezc = wherezc + "AND Z.SHI='" + shi + "'";
					}
					if (xian != null && !xian.equals("") && !xian.equals("0")) {
						whereStr = whereStr + " AND ZD.XIAN='" + xian + "'";
						wherezc = wherezc + "AND Z.XIAN='" + xian + "'";
					}
					System.out.println(fysx);
					if (fysx != null && !fysx.equals("") && !fysx.equals("0")) {
						System.out.println(fysx + "----------------");
						if (fysx.equals("1")) {
							fysxyjStr = fysxyjStr + " AND DFV.ACTUALPAY >=0";
							fysxyjStr1 = fysxyjStr1 + " AND E.actualpay>=0";

							fysxyzStr = fysxyzStr + " AND DFV.money >=0 ";
							fysxyzStr1 = fysxyzStr1 + " AND E.money >=0 ";

						} else {
							if (fysx.equals("2")) {
								fysxyjStr = fysxyjStr + " AND DFV.ACTUALPAY <0";
								fysxyjStr1 = fysxyjStr1 + " AND E.actualpay <0";
								fysxyzStr = fysxyzStr + " AND DFV.money <0 ";
								fysxyzStr1 = fysxyzStr1 + " AND E.money <0 ";
							}
						}

					}
					if (zdsx != null && !zdsx.equals("") && !zdsx.equals("0")) {
						whereStr = whereStr + " AND ZD.PROPERTY  ='" + zdsx + "'";//站点属性
						wherezc = wherezc + "AND Z.PROPERTY='" + zdsx + "'";
					}
					if (ebzyf != null && !ebzyf.equals("") && !ebzyf.equals("0")) {
						whereStr = whereStr + " AND to_char(DFV.ACCOUNTMONTH,'yyyy-mm')  <='" + ebzyf
								+ "'";//报账月份
						wherezc = wherezc + "AND to_char(E.ACCOUNTMONTH,'yyyy-mm') <='" + ebzyf + "'";
					}
					if (sbzyf != null && !sbzyf.equals("") && !sbzyf.equals("0")) {
						whereStr = whereStr + " AND to_char(DFV.ACCOUNTMONTH,'yyyy-mm')  >='" + sbzyf
								+ "'";//报账月份
						wherezc = wherezc + "AND to_char(E.ACCOUNTMONTH,'yyyy-mm') >='" + sbzyf + "'";
					}

					if (xxft != null && !xxft.equals("") && !xxft.equals("0")) {
						//whereStr=whereStr+" AND   ='"+xxft+"'";//
						if ("1".equals(xxft)) {
							whereyc = whereyc + "AND wc1='1'";
						} else if ("2".equals(xxft)) {
							whereyc = whereyc + "AND wf1='1'";
						} else if ("3".equals(xxft)) {
							whereyc = whereyc + "AND yc1='1'";
						}
					}
					if (fzyx != null && !fzyx.equals("") && !fzyx.equals("0")) {
						//whereStr=whereStr+" AND   ='"+fzyx+"'";//
						if ("1".equals(fzyx)) {
							whereyc = whereyc + "AND wc='1'";
						} else if ("2".equals(fzyx)) {
							whereyc = whereyc + "AND wf='1'";
						} else if ("3".equals(fzyx)) {
							whereyc = whereyc + "AND yc='1'";
						}
					}

					fybzCountBean bean = new fybzCountBean();
					// EquipmentmanageBean bean1=new EquipmentmanageBean();

					List<fybzbean> fylist = null;
					System.out.println("1111222");
					if (bzw != null && !bzw.equals("") && !bzw.equals("0")) {
						fylist = bean.getFt(whereStr, loginId, whereyc, wherezc,
								fysxyjStr1, fysxyjStr, fysxyzStr, fysxyzStr1);
						System.out.println("fylist:"+fylist.size());
						allpage = bean.getAllPage();
						String str = "";
						String zdid = "", area = "", dlid = "", zdname = "", dianbiaoid = "", dbname = "", dfzflx = "", yijibili = "", erjibili = "", bzyf = "", zshdl = "", actrypay = "", countmonth = "", enterper = "", entertime = "";
						String zdsx01 = "";
						int intnum = xh = pagesize * (curpage - 1) + 1;
						if (fylist != null) {
							for (fybzbean beans : fylist) {
								zdid = beans.getZdid();
								//num为序号，不同页中序号是连续的
								area = beans.getArea();//所在地区
								zdname = beans.getZdname();//所在地区
								dbname = beans.getDbname();
								dianbiaoid = beans.getDianbiaoid();
								dlid = beans.getDlid();
								dfzflx = beans.getDfzflx();//站点属性
								yijibili = beans.getYijibili();//站点类型
								erjibili = beans.getErjibili();//负责人
								bzyf = beans.getBzyf();//分专业线
								zshdl = beans.getZshdl();
								actrypay = beans.getActrypay();
								countmonth = beans.getCountmonth();
								enterper = beans.getEnterper();
								entertime = beans.getEntertime();

								str = "dianBiaoId=" + dianbiaoid;
								String color = null;
								str = "zdid=" + zdid;
								if (intnum % 2 == 0) {
									color = "#DDDDDD";

								} else {
									color = "#FFFFFF";
								}
				%>
				<tr bgcolor="<%=color%>" class="form_label">
					<td>
						<%=intnum++%>
					</td>

					<td>
						<%=area%>
					</td>
					<td>

						<div align="left">
							<a href="#" onclick="modifyjz('<%=zdid%>')"><%=zdname%></a>
						</div>
					</td>

					<td>
						<%=dbname%>
					</td>
					<td>
						<%=dfzflx%>
					</td>
					<td>
						<%=yijibili%>
					</td>
					<td>
						<%=erjibili%>
					</td>
					<td>
						<%=bzyf%>
					</td>
					<td>
						<%=zshdl%>
					</td>
					<td>
						<%
							if ("月结".equals(dfzflx)) {
						%>
						<div align="left">
							<a href="#" onclick="information('<%=dianbiaoid%>','<%=dlid%>')"><%=actrypay%></a>
						</div>
						<%
							} else {
						%>
						<div align="left">
							<a href="#" onclick="dfinfo5('<%=dianbiaoid%>','<%=dlid%>')"><%=actrypay%></a>
						</div>
						<%
							}
						%>

					</td>
					<td>
						<%=countmonth%>
					</td>
					<td>
						<%=enterper%>
					</td>
					<td>
						<%=entertime%>
					</td>
					<td>
						<a target="treeNodeInfo" name="dianbiao"
							href=<%=path
										+ "/web/tongjichaxun/fybzcountIfram2.jsp?dianbiaoid="
										+ dianbiaoid%>>分摊详细</a>
					</td>


				</tr>
				<%
					}
						}

					}
				%>

				<script type="text/javascript">
//查询详细信息

function lookDetails(zdcode) {

	window
			.open(
					path + "/web/jizhan/shenhemodifsite.jsp?id=" + zdcode,
					'',
					'width=1100,height=600px,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
	// document.form1.action=path+"/web/jizhan/shenhemodifsite.jsp?shi1="+shi+"&xian1="+xian+"&xiaoqu1="+xiaoqu+"&sname1="+sname+"&szdcode1="+szdcode+"&stationtype1="+stationtype+"&jzproperty1="+jzproperty+"&jztype1="+jztype+"&lrrq1="+lrrq+"&id="+zdcode;
	document.form1.submit();
}
function modify(str) {

	var bz = "2";//标志位 所属专业分摊数据从数据库还是从当前页面 获取值
	var de = "1";//标志位  删除时从bean里遍历数据  
	document.form1.action = path + "/web/equipmentmanage/add.jsp?" + str
			+ "&bz=" + bz + "&de=" + de + "";
	document.form1.submit();

}
</script>
			</table>
		</form>
	</body>
<script type="text/javascript">
	window.parent.CloseDiv();
</script>


</html>
