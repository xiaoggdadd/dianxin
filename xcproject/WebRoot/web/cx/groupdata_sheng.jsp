<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime,com.noki.mobi.cx.JiZhanNengHao"%>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.database.DataBase"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String path = request.getContextPath();
%>

<html>
	<head>
		<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
		<script src="<%=path%>/javascript/PopupCalendar.js"></script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
		<script type="text/javascript">
			var oCalendarEnny=new PopupCalendarny("oCalendarEnny");	//初始化控件时,请给出实例名称如:oCalendarEn
			oCalendarEnny.Init();
			 
			 
			var oCalendarChsny=new PopupCalendarny("oCalendarChsny");	//初始化控件时,请给出实例名称:oCalendarChs
			oCalendarChsny.weekDaySting=new Array("日","一","二","三","四","五","六");
			oCalendarChsny.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
			oCalendarChsny.oBtnTodayTitle="确定";
			oCalendarChsny.oBtnCancelTitle="取消";
			oCalendarChsny.Init();
		</script>
		<script type="text/javascript">
			var path = '<%=path%>';
			function daochu() {
				var isChecked = false;
				var checkBoxes = false;//复选框是否选中的标志
				var radios = document.getElementsByName("daochu1");
				for ( var k = 0; k < radios.length; k++) {
					if (radios[k].checked) {
						isChecked = true;
						var boxes = $("input[name='city']");
						boxes.each(function(){
							if(this.checked == true) 
								checkBoxes = true;
						});
						if(!checkBoxes){
							alert("请选择地区！");
							return;
						}
						
						var month = $("#month").val();
						if(month == ""){
							alert("请选择月份！");
							return ;
						}
						
						if(radios[k].value=="z"){
							var req = path + "/servlet/jtreport?action=" + radios[k].value
										+ "&reportType=0z&monthUp="+month+"&isModel=1";
						$("#form1").attr("action",req);
						$("#form1").submit();
						}else{
						var type = "0"+$(radios[k]).val();
						$("#form1").attr(
								"action",
								path + "/servlet/JtReportShengServlet?action=" + radios[k].value
										+ "&reportType=" + type+"&month="+month);
						$("#form1").submit();
						}
						return;
					}
				}
				if (!isChecked) {
					alert("请选择要导出的报表类型！");
				}
			}
			function selAll(){
				var boxes = $("input[name='city']");
				var flag = false;
				if(boxes[boxes.length-1].checked == true) flag = true;
				boxes.each(function(){
					this.checked = flag;
				});
			}
	$(function(){
		
		$("#cancelBtn").click(function(){
			daochu();
		});
	});
			
		</script>
	</head>
	<body class="body" style="overflow-x: hidden;">
		<div style="position: relative;">
			<img src="<%=path%>/images/btt.bmp">
			<span style="position:absolute;left:40px;top:22px; font-weight: bold;">省级节能减排报表</span>
		</div>
		<form id="form1" action="" method="post" >
			<div id="parent" style="display: inline">
				<div style="width: 50px; display: inline;">
					<hr>
				</div>
				&nbsp;数据导出&nbsp;
				<div style="width: 300px; display: inline;">
					<hr>
				</div>
			</div>
			
			<br />
			请选择月份：
			<input type="text" id="month" readonly="readonly" onFocus="getDateString(this,oCalendarChsny)"/>
			<br><br>
			请选择城市：
			<div style="width: 615px;height: 60px;">
				<%
					DataBase db = new DataBase();
					String sql = "select d.agcode,d.agname from d_area_grade d where length(d.agcode)=5";
					ResultSet rs = db.queryAll(sql);
					int i = 0;
					while(rs.next()){
						i++;
				%>
						<input type="checkbox" name="city" value="<%=rs.getString(1) %>"><%=rs.getString(2) %>
				<%
						if(i%8 == 0){%><br><%}
					}
					db.close();
				 %>
				 <input type="checkbox" name="city" value="-1" onclick="selAll()">全部
			</div>
			<br>
			请选择要导出的报表类型：
			<div style="width: 615px;height: 60px;">
				<input type="radio" name="daochu1" value="1" />
				附件1:地市基站用电量信息汇总表
				<br />
				<input type="radio" name="daochu1" value="2" />
				附件2:基站用电量汇总分析表
				<br />
					<input type="radio" name="daochu1" value="8" />
				附件3:基站节能减排技术应用情况统计表
				<br />
				<input type="radio" name="daochu1" value="f" />
				附件4:地市接入网机房用电量信息汇总
				<br />
				<input type="radio" name="daochu1" value="c" />
				附件5:接入网机房节能减排技术应用情况
				<br />
				<input type="radio" name="daochu1" value="4" />
				附件6:IDC用电量汇总表
				<br />
				<input type="radio" name="daochu1" value="5" />
				附件7：通信局房用电量汇总表
				<br />
				<input type="radio" name="daochu1" value="b" />
				附件8：通信机房节能技术应用信息统计
				<br />
				<input type="radio" name="daochu1" value="d" />
				附件9：节能减排工作成效报表
				<br />
				<input type="radio" name="daochu1" value="v" />
				附件10：节能设备后评价报表
				<br />
				<input type="radio" name="daochu1" value="9" />
				节能减排季报表
				<br />
				<!--<input type="radio" name="daochu1" value="3" />
				固网专业用电量汇总表
				<br />
				
				
				<input type="radio" name="daochu1" value="6" />
				通信机房环境温度管理节能情况汇总表
				<br />
				<input type="radio" name="daochu1" value="7" />
				IDC机房节能技术应用情况汇总表
				<br />
			
				
<%--				<input type="radio" name="daochu1" value="a" />--%>
<%--				接入网机房用电量台账--%>
<%--				<br />--%>
				
				
				
				<input type="radio" name="daochu1" value="e" />
				节能技术分类汇总表
				<br />
			
				<input type="radio" name="daochu1" value="g" />
				通信机房环境温度管理情况汇总
				</div>
				<input type="radio" name="daochu1" value="z" />
				采集基站用电量信息汇总表
				</div>
			-->
			<br />
			
			<div style="width:400px;height:40px;float:left;font-size: 12px;color:red">
				<c:forEach items="${requestScope.errors}" var="error">
					${error}
				</c:forEach>
			</div>
					<td>
		
          <div id="cancelBtn" style="width:90px;height:23px;cursor:pointer;float:center;position:relative;left:220px">
	           <img src="<%=path %>/images/daochu-1.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:3px;color:white">导出Excel</span>
          </div>
        </td>
		</form>
		
	</body>
</html>
