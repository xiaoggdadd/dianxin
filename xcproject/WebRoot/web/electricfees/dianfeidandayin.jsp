<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
	String chooseIdStr = request.getParameter("chooseIdStr") != null ? request
			.getParameter("chooseIdStr")
			: "";
	boolean isload = false;
%>
<html>
	<head>
		<title>dianfeidandayin</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script src="<%=path%>/javascript/tx.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<jsp:include page="/web/prePrint.jsp"></jsp:include>


	</head>
	<body>
		<div id="hiddenDiv" style="visibility: hidden">
			<form id="form1">


				<%
					ElectricFeesBean bean = new ElectricFeesBean();

					ArrayList list = bean.getPageData(chooseIdStr);
					String zdname = "", liushuihao = "", dbid = "", lastdegree = "", thisdegree = "", floatdegree = "",
					 actualdegree = "", multiplyingpower = "", floatpay = "", unitprice = "", actualpay = "", 
					 autoauditstatus = "", payoperator = "", paydatetime = "", autoauditdescription = "", manualauditstatus = "";
					 zdname=list.get(1).toString();
					 
				%>
				<table id="dayindan" border='1' width='100%' cellspacing='1'
					cellpadding='1'>
					<tr>
						<td>站点信息：</td>
						<td></td>
						<td colspan="4">站点名称：<%=list.get(0)%></td>
						<td>流水号：</td>
						<td></td>
					</tr>
					<tr>
						<td>电表ID：</td>
						<td><%=list.get(1)%></td>
						<td>上次电表读数：</td>
						<td><%=list.get(2)%></td>
						<td>本次电表读数：</td>
						<td>
							<%=list.get(3)%>
						</td>
						<td>
							用电量调整：
						</td>
						<td>
							<%=list.get(4)%>
						</td>

					</tr>
					<tr>
						<td>
							实际电表读数：
						</td>
						<td>
							<%=list.get(5)%>
						</td>
						<td>
							电表倍率：
						</td>
						<td>
							<%=list.get(6)%>
						</td>
						<td>
							应缴电费：
						</td>
						<td>
							
						</td>
						<td>
							实际电费：
						</td>
						<td>
							<%=list.get(11)%>
						</td>

					</tr>
					<tr>
						<td>
							日均标杆值：
						</td>
						<td>

						</td>
						<td>
							上次抄表日期：
						</td>
						<td>
                         <%=list.get(7)%>
						</td>
						<td>
							本次抄表日期：
						</td>
						<td>
                          <%=list.get(8)%>
						</td>
						<td>
							用电度数标杆值：
						</td>
						<td>

						</td>

					</tr>
					<tr>
						<td>
							电费单价：
						</td>
						<td>
                           <%=list.get(9)%>
						</td>
						<td>
							电表最大读数：
						</td>
						<td>
                           
						</td>
						<td>
							旧电表本次读数：
						</td>
						<td>
                           
						</td>
						<td>
							新电表上次读数：
						</td>
						<td>
							

						</td>
					</tr>

					<tr>
						<td colspan="8" style="text-align: center">
							已处理情况
						</td>
					</tr>
					<tr>
						<td>
							电费自动审核：
						</td>
						<td colspan="7">
							<%=list.get(15) %>
						</td>


					</tr>
					<tr>
						<td>
							电费人工审核：
						</td>
						<td colspan="7">
							<%if(Integer.parseInt(list.get(16).toString())>=1)out.print("同意");else{out.print("不同意");} %>
						</td>
					</tr>
					<tr>
						<td>
							电费市级审核：
						</td>
						<td colspan="7">
							
						</td>
					</tr>

				</table>
				<%
					isload = true;
				%>
			
	</body>
	</form>
	</div>
</html>
<script language="javascript">
var path = '<%=path%>';
var isload = '<%=isload%>';

var tablehtml = "<body>" + document.getElementById("form1").innerHTML
		+ "</body>";

if (isload) {
	var column = "all";//打印所有列
	//var column = [1,2,3,4,5,6,7,8,9,10];//定义表格要打印的列，下标从0开始
	var style = "<style>table {border-collapse:collapse;border-style:double;border-width:3px;border-color:black;width:720px;font-size:14}</style>";//定义表格样式

	create("电费单", style + tablehtml);//创建打印样式
	//LODOP.PRINT_DESIGN();
	LODOP.PREVIEW();
	window.location.href = path + "/web/electricfees/dianfeidanquery.jsp";

}
function create(title, postSetColumn) {

	LODOP.PRINT_INIT(title);

	LODOP.SET_PRINT_STYLE("FontSize", 18);
	LODOP.ADD_PRINT_TEXT(50, 250, 200, 30, title);
	LODOP.ADD_PRINT_HTM(80, 20, 1000, 500, postSetColumn);
};

</script>
