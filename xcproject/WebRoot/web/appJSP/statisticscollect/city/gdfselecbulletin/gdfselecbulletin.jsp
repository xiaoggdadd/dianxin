<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.*,java.util.List,java.util.Date,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.*"%>
<%
	Account account = (Account)session.getAttribute("account");
	String loginId = account.getAccountId();
	String sheng = (String)session.getAttribute("accountSheng");//省
	
	String agcode1="";
	if(request.getParameter("shi")==null){
		List shilist = new ArrayList();
		CommonBean commBean = new CommonBean();
		shilist = commBean.getAgcode(sheng,account.getAccountId());
		if(shilist!=null){
			int scount = ((Integer)shilist.get(0)).intValue();
	    	agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
	 	}
	}    
	//回显字段
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;//市
	String path = request.getContextPath();//项目路径
	Calendar c = Calendar.getInstance();
	c.add(Calendar.MONTH,-1);//上个月
	String begin = new SimpleDateFormat("yyyy-MM").format(c.getTime());
	
	String beginbztime = request.getParameter("beginbztime") != null ? request.getParameter("beginbztime"): begin;//报账月份
	String property = request.getParameter("property") != null ? request.getParameter("property"): "0";//站点属性
	String status = request.getParameter("status") != null ? request.getParameter("status") : "2";//审核状态

	String color;//颜色
	int intnum = request.getAttribute("num") != null ? (Integer) request.getAttribute("num"): 0;//条数 ,查出数据的条数，用于颜色设置
	if (intnum % 2 == 0) {
		color = "#DDDDDD";
	} else {
		color = "#FFFFFF";
	}
	Date d = Calendar.getInstance().getTime();
	String beginn = new SimpleDateFormat("yyyy-MM").format(d);
	String now = new SimpleDateFormat("yyyy-MM-dd").format(d);
	String now03 = beginn+"-03";
	Calendar cc = Calendar.getInstance();
	cc.add(Calendar.MONTH,-1);
	String nowm1 = new SimpleDateFormat("yyyy-MM").format(cc.getTime());
%>

<html>
	<head>
		<title>供电方式电量通报</title>

		<link type="text/css" rel="Stylesheet"
			href="<%=path%>/web/appCSS/pageCss/page.css" />
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/someJs/page.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
		<script language="javascript">
var path = '<%=path%>';

function queryDegree(command) {
	if(yue()){
		var accountmonth = document.form1.beginbztime.value;
			document.form1.action = path + "/servlet/GdfsElecBulletinServlet2?command="+ command;
			document.form1.submit();
			if ("chaxun" == command) {
				showdiv("请稍等..............");
			}
		}
}
function yue(){
	var shi = document.form1.shi.value;
	var begin = '<%=beginn%>';
	var last = '<%=nowm1%>';
	var now = '<%=now%>';
	var now03 = '<%=now03%>';
	var beginbztime = document.form1.beginbztime.value;
		if("0"==shi || ""==shi){
		alert("城市不能为空!");
	}else if(""==beginbztime){
		alert("报账月份不能为空!");
	}else{
		return true;
//		if(beginbztime>=begin){
//			alert("暂无数据!当月3日之后方可查询上个月的数据!");
//		}else{
//			if(beginbztime<last){
//				return true;
//			}else{
//				if(now>now03){
//					return true;
//				}else{
//					alert("当月3日之后方可查询上个月的数据!");
//				}
//			}
//		}
		
	}
}

$(function() {
	$("#daochuBtn").click(function() {
		queryDegree("daochu");//导出
	});
	$("#chaxun").click(function() {
		queryDegree("chaxun");//查询
	});
});
</script>

	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
	 			<tr>
      				<td colspan="4" width="50">
						<div style="width:700px;height:50px">
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">供电方式电量分析通报</span>	
						</div>
					</td>
    			</tr>
				<tr>
					<td height="30" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;过滤条件&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="8%" width="1200">
						<table>
							<tr class="form_label">
							    <td>城市：</td>
		    					<td>
		    						<select name="shi" class="selected_font" >
	         							<option value="0">请选择</option>
	         							<%
			         						ArrayList shilist = new ArrayList();
			         						shilist = commBean.getAgcode(sheng,account.getAccountId());
			         						if(shilist != null){
			         							String agcode = "",agname = "";
			         							int scount = ((Integer)shilist.get(0)).intValue();
			         							int size = shilist.size()-1;
			         							int i;
			         							for(i = scount;i < size;i += scount){
		                    							agcode = (String)shilist.get(i + shilist.indexOf("AGCODE"));
		                    							agname = (String)shilist.get(i + shilist.indexOf("AGNAME"));
		                    			%>
		                    			<option value="<%=agcode%>"><%=agname%></option>
		                    			<%		
		                    					}
			         						}
			   							%>
	         						</select>
	         						<font color="red">&nbsp;*</font>
	         					</td>
								<td>报账月份：</td>
								<td>
									<input class="selected_font" type="text" name="beginbztime"
										value=""
										readonly="readonly" class="Wdate"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
										<span class="style1">*&nbsp;</span>
								</td>
								<td>审核状态：</td>
								<td>
									<select name="status" class="selected_font">
										<option value="2">
											财务审核通过
										</option>
										<option value="1">
											业务审核通过
										</option>
									</select>
								</td>
								<td>站点属性：</td>
								<td>
									<select name="property" class="selected_font" >
										<option value="0">
											请选择
										</option>
										<%
											ArrayList zdsx = new ArrayList();
											zdsx = ztcommon.getSelctOptions("zdsx");
											if (zdsx != null) {
												String code = "", name = "";
												int cscount = ((Integer) zdsx.get(0)).intValue();
												int size = zdsx.size() - 1;
												for (int i = cscount; i < size; i += cscount) {
													code = (String) zdsx.get(i + zdsx.indexOf("CODE"));
													name = (String) zdsx.get(i + zdsx.indexOf("NAME"));
										%>
										<option value="<%=code%>"><%=name%></option>
										<%
											}
											}
										%>
									</select>
								</td>
								<td>
									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -95px; float: right; top: 0px">
										<img alt="" src="<%=path%>/images/chaxun.png" width="100%"
											height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td height="23" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;信息列表&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
					<td>
					<input type="hidden" name="loginid" value="<%=loginId%>"></input>
					</td>
				</tr>
			</table>
			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="100%" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="10%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								城市
							</div>
						</td>
						<td width="10%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县
							</div>
						</td>
						<td width="16%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
							<c:choose>
							<c:when test="${nian1 eq null}">_</c:when>
							<c:when test="${nian1 != null}">${nian1}</c:when>
							</c:choose>
							年
							<c:choose>
							<c:when test="${yue1 eq null}">_</c:when>
							<c:when test="${yue1 != null}">${yue1}</c:when>
							</c:choose>月份电量(万度)
							</div>
						</td>
						<td width="16%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
							其中:直供电电量(万度)
							</div>
						</td>
						<td width="16%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
							其中:直供电电量占比%
							</div>
						</td>
						<td width="16%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
							其中:转供电电量(万度)
							</div>
						</td>
						<td width="16%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
							其中:转供电电量占比%
							</div>
						</td>
					</tr>

					<c:forEach items="${list}" var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
							<td><div align="right">${list.shi}</div></td>
							<td><div align="right">${list.city}</div></td>
							<td><div align="right">${list.zdl}</div></td>
							<td><div align="right">${list.zhigd}</div></td>
							<td><div align="right">${list.zhigdzb}%</div></td>
							<td><div align="right">${list.zhuangd}</div></td>
							<td><div align="right">${list.zhuangdzb}%</div></td>
						</tr>
					</c:forEach>

					<%
						if (intnum == 0) {
							for (int i = 0; i < 17; i++) {
								if (i % 2 == 0) {
									color = "#FFFFFF";
								} else {
									color = "#DDDDDD";
								}
					%>

					<tr bgcolor="<%=color%>">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<%
						}
						} else if (!(intnum > 16)) {
							int n = ((intnum - 1) % 16);
							int j = 0;
							for (j = n; j < 16; j++) {
								if (j % 2 == 0)
									color = "#DDDDDD";
								else
									color = "#FFFFFF";
					%>
					<tr bgcolor="<%=color%>">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<%
						}
						}
					%>
				<tr height="23">
				<td bgcolor="#DDDDDD" colspan="2">
					<div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">
					合计
					</div>
				</td>
        		<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${total1}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${total2}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${total3}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${total4}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${total5}</div></td>
        	</tr>

				</table>
			</div>

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr bgcolor="F9F9F9">
					<td align="right" height="19" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<div id="daochuBtn"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 4px">
							<img src="<%=path%>/images/daochu.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导出</span>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
<script type="text/javascript">
//回显
document.form1.shi.value='<%=shi%>';
document.form1.beginbztime.value='<%=beginbztime%>';
document.form1.property.value = '<%=property%>';
document.form1.status.value = '<%=status%>';
</script>
</html>

