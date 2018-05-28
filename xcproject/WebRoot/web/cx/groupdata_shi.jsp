<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime,com.noki.mobi.cx.JiZhanNengHao"%>
<%@ page import="java.sql.ResultSet"%>
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
		<script language="javascript">
			var path = '<%=path%>';
			function daochu(state) {
				var isChecked = false;
				var radios = document.getElementsByName("daochu1");
				for ( var k = 0; k < radios.length; k++) {
					if (radios[k].checked) {
						isChecked = true;
						var action = radios[k].value;
						var reportType = "0"+action;
						var month = $("#monthDownLoad").val();
						var accountmonth = $("#accountmonth").val();
						if(month == ""&&accountmonth==""){
							alert("请选择月份！");
							return;
						}

		              if(action==8 || action=="c")
						{
						//alert("eeeeeeeee");
							var req = path + "/servlet/jtreport?action=" + action
										+ "&reportType=" + reportType+"&monthUp="+month+"&accountmonth="+accountmonth+"&isModel="+1;
						}else{
						var req = path + "/servlet/jtreport?action=" + action
										+ "&reportType=" + reportType+"&monthUp="+month+"&accountmonth="+accountmonth+"&isModel="+state;
					
						}
						$("#form1").attr("action",req);
						$("#form1").submit();
						return;
					}
				}
				if (!isChecked) {
					alert("请选择要导出的数据");
				}
			}
			
			function shangchuan(){
				$("#errorInfo").text("");
				var month = $("#month").val();
				if(month == ""){
					alert("请输入月份！");
					return;
				}
				var reportType = $("#reportType").val();

				$.post(
					path + "/UploadJtReportServlet",
					{reportType:reportType,month:month,confirm:1},
					function(data){
						if(data == 0){
							if(window.confirm("该文件已经上传过，是否覆盖？")){
								$("#form1").attr("action",path + "/UploadJtReportServlet?month="+month+"&reportType="+reportType);
								$("#form1").submit();
							}
						}
						else if(data == 1){
							$("#form1").attr("action",path + "/UploadJtReportServlet?month="+month+"&reportType="+reportType);
							$("#form1").submit();
						}
					}
				);
			}
	$(function(){
		
		$("#cancelBtn").click(function(){
			daochu(1);
		});
		$("#cancela").click(function(){
			daochu(0);
		});
			$(function(){
		
		$("#uploading").click(function(){
			shangchuan();
		});
	});
	});
		</script>
<style type="text/css">
		.style1 {
	color: #FF0000;
	
}
</style>
	</head>
	<body class="body" style="overflow-x: hidden;">
		<div style="position: relative;">
			<img src="<%=path%>/images/btt.bmp">
			<span style="position:absolute;left:40px;top:22px; font-weight: bold;">市级节能减排报表</span>
		</div>
		<form id="form1" action="" method="post" enctype="multipart/form-data" >
			<div id="parent" style="display: inline">
				<div style="width: 50px; display: inline;">
					<hr>
				</div>
				&nbsp;数据导出&nbsp;
				<div style="width: 300px; display: inline;">
					<hr>
				</div><br>
			</div>
			<br/>
			请选择数据月份：
			<input type="text" id="monthDownLoad"  onFocus="getDateString(this,oCalendarChsny)"/>
			请选择报账月份：
			<input type="text" id="accountmonth"  onFocus="getDateString(this,oCalendarChsny)"/>
			<br />
			<input type="radio" name="daochu1" value="1" />附件1：地市基站用电量信息汇总表 
			<br />
			<input type="radio" name="daochu1" value="2" />附件2：基站用电量汇总分析表 
			<br />
			<input type="radio" name="daochu1" value="8" />附件3：基站节能减排技术应用情况统计表<span class="style1">&nbsp;*</span>
			<br />
			<input type="radio" name="daochu1" value="f" />附件4：地市接入网机房用电量信息汇总表 
			<br />
			<input type="radio" name="daochu1" value="c" />附件5：接入网机房节能减排技术应用情况统计表<span class="style1">&nbsp;*</span>
			<br />
			<input type="radio" name="daochu1" value="4" />附件6：IDC用电量汇总表（月报） 
			<br />
			<input type="radio" name="daochu1" value="5" />附件7：通信局房用电量汇总新表（月报增加生产用电电费） 
			<br />
			<input type="radio" name="daochu1" value="b" />附件8：通信机房节能技术应用信息统计 
			<br />
			<input type="radio" name="daochu1" value="d" />附件9：节能减排工作成效报表 
			<br />
			<input type="radio" name="daochu1" value="v" />附件10：节能设备后评价报表 
			<br />
			<input type="radio" name="daochu1" value="9" />节能减排季报表（集团原始表） 
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
			
			
			<input type="radio" name="daochu1" value="a" />
			接入网机房用电量台账
			<br />
			
		
			
			<input type="radio" name="daochu1" value="e" />
			节能技术分类汇总表
			<br />
			
			<input type="radio" name="daochu1" value="g" />
			通信机房环境温度管理情况汇总
			<br />
			<input type="radio" name="daochu1" value="h" />
			基站用电量台账
			<br />
			-->
			<br />
			&nbsp;&nbsp;注：带<span class="style1">*</span>号的导出Excel点击带数据按钮
			<br />
			
			<br /><br />
			
			<div id="cancelBtn" style="width:166px;height:23px;cursor:pointer;float:left;position:relative;left:15px">
	           <img src="<%=path %>/images/baobiao-1.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:39px;top:3px;color:white">导出Excel（带数据）</span>
            </div>
            <div id="cancela" style="width:166px;height:23px;cursor:pointer;float:center;position:relative;left:15px">
	           <img src="<%=path %>/images/baobiao-1.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:40px;top:3px;color:white">导出Excel(无数据)</span>
            </div>
           <br />
			<div id="parent" style="display: inline">
				<div style="width: 50px; display: inline;">
					<hr>
				</div>
				&nbsp;数据上传&nbsp;
				<div style="width: 300px; display: inline;">
					<hr>
				</div>
				<br/><br>
				请选择上传月份：
				<input type="text" id="month" readonly="readonly" onFocus="getDateString(this,oCalendarChsny)"/>
				<br />
				请选择报表类型：
				<select name="reportType" id="reportType">
					<option value="00">请选择</option>
					<option value="01">附件1：地市基站用电量信息汇总表</option>
					<option value="02">附件2：基站用电量汇总分析表</option>
					<option value="08">附件3：基站节能减排技术应用情况统计表</option>
					<option value="0f">附件4：地市接入网机房用电量信息汇总表</option>
					<option value="0c">附件5：接入网机房节能减排技术应用情况统计表</option>
					<option value="04">附件6：IDC用电量汇总表（月报）</option>
					<option value="05">附件7：通信局房用电量汇总表（月报增加生产用电电费）</option>
					<option value="0b">附件8：通信机房节能技术应用信息统计</option>
					<option value="0d">附件9：节能减排工作成效报表</option>
					<option value="0v">附件10：节能设备后评价报表</option>
					<option value="09">节能减排季报表（集团原始表）</option>
					<!--<option value="03">固网专业用电量汇总表</option>
					<option value="05">通信局房用电量汇总表</option>
					<option value="06">通信机房环境温度管理节能情况汇总表</option>
					<option value="07">IDC机房节能技术应用情况汇总表</option>
					
					<option value="0a">接入网机房用电量台账</option>
					<option value="0b">通信机房节能技术应用信息统计</option>
					
					<option value="0e">节能技术分类汇总表</option>
					<option value="0g">通信机房环境温度管理情况汇总</option>
					-->
				</select>
			</div>
			
			<br /> 

			<div style="width: 370px; height: 25px; float:left;" >
			请选择报表文件：
			<input type="file" id="fileUp" name="fileUp" height="25px">
			</div>
		    <div id="uploading" style="position: relative; width: 60px; height: 20px; float:left; pointer;" >
				<img alt=""src="<%=request.getContextPath() %>/images/shangchuan.png" width="100%" height="100%" />
				<span style="font-size: 12px; position: absolute; left: 26px; top: 3px; color: white">上传</span>
			</div>
			
			<div id="errorInfo" style="width:200px;height:50px;position:relative;font-size: 12px;color:red">
				<c:forEach items="${requestScope.statusInfo}" var="info">
					<br />${info}
				</c:forEach>
			</div>
			
		</form>
	</body>
</html>
