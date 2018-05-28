<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();//项目路径
	Account account = (Account) session.getAttribute("account");//账户

	String color;//颜色
	int intnum = request.getAttribute("num") != null ? (Integer) request.getAttribute("num") : 0;//条数 ,查出数据的条数，用于颜色设置
	if (intnum % 2 == 0) {
		color = "#DDDDDD";
	} else {
		color = "#FFFFFF";
	}
%>

<html>
	<head>
		<title>暂估静态数据电费报账对照查询</title>
<link type="text/css" rel="Stylesheet"
			href="<%=path%>/web/appCSS/pageCss/page.css" />

		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
		<script language="javascript">
var path = '<%=path%>';
function queryDegree(command) {
	var zangumonth = document.form1.zangumonth.value;
	if(document.getElementById("zangumonth").value == ""){
		alert("暂估结束月份不能为空！");
	}else{
		document.form1.action = path + "/servlet/EstimateContrastServlet?command="
				+ command;
		;
		document.form1.submit();
		if ("chaxun" == command) {
			showdiv("请稍等..............");
		}
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
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">暂估静态数据电费报账对照查询</span>	
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
								<td>
									暂估结束月份：
								</td>
								<td>
									<input class="selected_font" type="text" name="zangumonth" id = "zangumonth"
									value="${requestScope.zangumonth}"
									readonly="readonly" class="Wdate"
									onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
								</td>

								<td>

									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -240px; float: right; top: 0px">
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

			<div style="width: 88%;">
				<p id="box3" style="display: none">
				
				</p>
			</div>

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
				</tr>
			</table>


<div style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
   <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
   	<tr  class="relativeTag" >
   	    <td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  rowspan="2"><div  align="center" class="bttcn">地市</div></td>  
   	   	<td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  colspan="4"><div align="center" class="bttcn">核对信息</div></td>           
	    <td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  colspan="6"><div align="center" class="bttcn">业务活动</div></td> 
		<td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  colspan="6"><div align="center" class="bttcn">专业</div></td> 	
    </tr> 
	<tr  class="relativeTag"  >
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">站点总数</div></td>
  		<td bgcolor='#DDDDDD' class="relativeTag"  width="5%"><div align="center" class="bttcn">总条数</div></td>
  		<%--<td bgcolor='#DDDDDD' class="relativeTag"  width="5%"><div align="center" class="bttcn">总金额(元)</div></td>--%>
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">暂估总金额(元)</div></td>
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">暂估总周期</div></td>
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">网络运营(元)</div></td>
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">市场经营(元)</div></td>
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">行政综合(元)</div></td>
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">信息化支撑(元)</div></td>
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">建设投资(元)</div></td>
  		<td bgcolor='#DDDDDD' class="relativeTag"  width="5%"><div align="center" class="bttcn">代垫电费(元)</div></td>
  		
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">移动专业-2G(元)</div></td>
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">移动专业-3G(元)</div></td>
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">固网专业(元)</div></td>
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">固移共同专业(元)</div></td>
  		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">移动共同专业(元)</div></td>
  		<td bgcolor='#DDDDDD' class="relativeTag"  width="5%"><div align="center" class="bttcn">不可分摊专业(元)</div></td>
  	</tr>	
				   		
  	 	 		
  <c:forEach items="${list}" var="list" varStatus="status">
		<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
		<td>
			<div align="center">
				${list.city}
			</div>
		</td>
		<td>
			<div align="right">
				${list.zdcount}
			</div>
		</td>
		<td>
			<div align="right">
				${list.zcount}
			</div>
		</td>
		<%--<td>
			<div align="right">
				${list.money}
			</div>
		</td>
		--%>
		<td>
			<div align="right">
				${list.zgje}
			</div>
		</td>
		<td>
			<div align="right">
				${list.zgzq}
			</div>
		</td>
		
		
		
		
		<td>
			<div align="right">
				${list.wlyy}
			</div>
		</td>
		<td>
			<div align="right">
				${list.scjy}
			</div>
		</td>
		<td>
			<div align="right">
				${list.xzzh}
			</div>
		</td>
		<td>
			<div align="right">
				${list.xxhzc}
			</div>
		</td>
		<td>
			<div align="right">
				${list.jstz}
			</div>
		</td>
		<td>
			<div align="right">
				${list.dddf}
			</div>
		</td>
		<td>
			<div align="right">
				${list.ydzy2}
			</div>
		</td>
		<td>
			<div align="right">
				${list.ydzy3}
			</div>
		</td>
		<td>
			<div align="right">
				${list.gwzy}
			</div>
		</td>
		<td>
			<div align="right">
				${list.gygtzy}
			</div>
		</td>
		<td>
			<div align="right">
				${list.ydgtzy}
			</div>
		</td>
		<td>
			<div align="right">
				${list.bkftzy}
			</div>
		</td>
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
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td><td>
				&nbsp;
			</td>
			
			<%--<td>
				&nbsp;
			</td>
		--%></tr>
		<%
			}
			} else if (!(intnum > 17)) {
				int j = ((intnum - 1) % 17);
				int i = 0;
				for (i = j; i < 17; i++) {
					if (i % 2 == 0)
						color = "#FFFFFF";
					else
						color = "#DDDDDD";
		%>
		<tr bgcolor="<%=color%>">
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<%--<td>
				&nbsp;
			</td>
		--%></tr>
		<%
			}
			}
		%>
  </table>
  </div>
  

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						
					</td>
				</tr>
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
				<tr>
					<td>
						<div id="detailsDiv"
							style="position: relative; width: 99%; height: 200px; left: 0px; top: 10px; z-index: 1; float: left; overflow-y: auto;">
							<iframe name="details" frameborder="0" width="100%" height="100%"
								scrolling="no"></iframe>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
	

	<script type="text/javascript">
//回显

</script>
</html>

