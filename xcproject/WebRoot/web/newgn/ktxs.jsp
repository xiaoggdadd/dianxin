<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.*,java.util.List,com.noki.mobi.common.Account"%>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.regex.Pattern,com.noki.newfunction.dao.*,com.noki.newfunction.javabean.*"%>

<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
	String sheng = (String) session.getAttribute("accountSheng");
	String agcode1 = "";
	if (request.getParameter("shi") == null) {
		ArrayList shilist = new ArrayList();
		CommonBean commBean = new CommonBean();
		shilist = commBean.getAgcode(sheng, account.getAccountId());
		if (shilist != null) {
			int scount = ((Integer) shilist.get(0)).intValue();
			agcode1 = (String) shilist.get(scount
					+ shilist.indexOf("AGCODE"));
		}
	}

	String loginId1 = request.getParameter("loginId");
	String accountmonth = request.getParameter("accountmonth") != null ? request
			.getParameter("accountmonth")
			: "";//报账月份

	int pagesize = 15, curpage = 1, xh = 1;
	String permissionRights = "";
	String color = null;
	String roleId = (String) session.getAttribute("accountRole");

	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : agcode1;
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";
	String xiaoqu = request.getParameter("xiaoqu") != null ? request
			.getParameter("xiaoqu") : "0";
	String zgmonth = request.getParameter("zgmonth") != null ? request
			.getParameter("zgmonth") : "";
	String zdname = request.getParameter("zdname") != null ? request
			.getParameter("zdname") : "";
	String rgshzt = request.getParameter("rgshzt") != null ? request
			.getParameter("rgshzt") : "0";
			
	String gxsj= request.getParameter("gxsj")!=null?request.getParameter("gxsj"):"";
			
String datetime=new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
		        Date tt1 = new Date(); //得到当前时间
		        System.out.println(tt1+"-----------");
		  		tt1 = new Date(tt1.getYear(), tt1.getMonth() + 1, 0); //得到本月最后一天
		  		String s=datetime+"-"+tt1.getDate();
		  	  	String ss=s.substring(0,7);
		  	  System.out.println(ss+"-----------");
		  		if(gxsj==null||"".equals(gxsj)){
		  			gxsj=ss;
		  		
		  		}
%>

<html>
	<head>
		<title></title>
		<style type="text/css">
		.ipt{width:60px;height:18px;}
		.ii{width:20px;height:18px;}
		.i{width:30px;height:18px;}
		</style>
		<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}

.style1 {
	color: red;
	font-weight: bold;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.bttcn {
	color: BLACK;
	font-weight: bold;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height: 23px
}
.td{
	height="10"
}

.selected_font {
	width: 90px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 120%;
}

.selected_font1 {
	width: 120px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 120%;
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}
</style>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript"
			src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
		<script>
var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();

var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChsny.oBtnTodayTitle = "确定";
oCalendarChsny.oBtnCancelTitle = "取消";
oCalendarChsny.Init();
</script>
		<script>

var oCalendarEn = new PopupCalendar("oCalendarEn"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();

var oCalendarChs = new PopupCalendar("oCalendarChs"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChs.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChs.oBtnTodayTitle = "今天";
oCalendarChs.oBtnCancelTitle = "取消";
oCalendarChs.Init();
</script>
		<script language="javascript">
var path = '<%=path%>';
function queryDegree() {
	document.form1.action = path
			+ "/web/jzcbnewfunction/zgrgsh.jsp?command=chaxun";
	document.form1.submit();

}
$(function(){
			$("#gengxin").click(function(){
				//$.each($("form input[type='text']"),function(){
				//	$(this).val("");
				//})
				gengxin();
			});
			$("#saveBtn").click(function(){
				save();
			});
	});
function save(){
	
	if(isNaN(document.form1.jn1.value)==false&&isNaN(document.form1.jn2.value)==false&&isNaN(document.form1.jn3.value)==false&&
		isNaN(document.form1.jn4.value)==false&&isNaN(document.form1.jn5.value)==false&&isNaN(document.form1.jn6.value)==false&&
		isNaN(document.form1.jn7.value)==false&&isNaN(document.form1.jn8.value)==false&&isNaN(document.form1.jn9.value)==false&&
		isNaN(document.form1.jn10.value)==false&&isNaN(document.form1.jn11.value)==false&&isNaN(document.form1.jn12.value)==false){
		
		if(isNaN(document.form1.qd1.value)==false&&isNaN(document.form1.qd2.value)==false&&isNaN(document.form1.qd3.value)==false&&
			isNaN(document.form1.qd4.value)==false&&isNaN(document.form1.qd5.value)==false&&isNaN(document.form1.qd6.value)==false&&
			isNaN(document.form1.qd7.value)==false&&isNaN(document.form1.qd8.value)==false&&isNaN(document.form1.qd9.value)==false&&
			isNaN(document.form1.qd10.value)==false&&isNaN(document.form1.qd11.value)==false&&isNaN(document.form1.qd12.value)==false){
			
		if(isNaN(document.form1.zb1.value)==false&&isNaN(document.form1.zb2.value)==false&&isNaN(document.form1.zb3.value)==false&&
			isNaN(document.form1.zb4.value)==false&&isNaN(document.form1.zb5.value)==false&&isNaN(document.form1.zb6.value)==false&&
			isNaN(document.form1.zb7.value)==false&&isNaN(document.form1.zb8.value)==false&&isNaN(document.form1.zb9.value)==false&&
			isNaN(document.form1.zb10.value)==false&&isNaN(document.form1.zb11.value)==false&&isNaN(document.form1.zb12.value)==false){
			
		if(isNaN(document.form1.zz1.value)==false&&isNaN(document.form1.zz2.value)==false&&isNaN(document.form1.zz3.value)==false&&
			isNaN(document.form1.zz4.value)==false&&isNaN(document.form1.zz5.value)==false&&isNaN(document.form1.zz6.value)==false&&
			isNaN(document.form1.zz7.value)==false&&isNaN(document.form1.zz8.value)==false&&isNaN(document.form1.zz9.value)==false&&
			isNaN(document.form1.zz10.value)==false&&isNaN(document.form1.zz11.value)==false&&isNaN(document.form1.zz12.value)==false){
			
		if(isNaN(document.form1.dy1.value)==false&&isNaN(document.form1.dy2.value)==false&&isNaN(document.form1.dy3.value)==false&&
			isNaN(document.form1.dy4.value)==false&&isNaN(document.form1.dy5.value)==false&&isNaN(document.form1.dy6.value)==false&&
			isNaN(document.form1.dy7.value)==false&&isNaN(document.form1.dy8.value)==false&&isNaN(document.form1.dy9.value)==false&&
			isNaN(document.form1.dy10.value)==false&&isNaN(document.form1.dy11.value)==false&&isNaN(document.form1.dy12.value)==false){
			
		if(isNaN(document.form1.yt1.value)==false&&isNaN(document.form1.yt2.value)==false&&isNaN(document.form1.yt3.value)==false&&
			isNaN(document.form1.yt4.value)==false&&isNaN(document.form1.yt5.value)==false&&isNaN(document.form1.yt6.value)==false&&
			isNaN(document.form1.yt7.value)==false&&isNaN(document.form1.yt8.value)==false&&isNaN(document.form1.yt9.value)==false&&
			isNaN(document.form1.yt10.value)==false&&isNaN(document.form1.yt11.value)==false&&isNaN(document.form1.yt12.value)==false){
			
		if(isNaN(document.form1.wf1.value)==false&&isNaN(document.form1.wf2.value)==false&&isNaN(document.form1.wf3.value)==false&&
			isNaN(document.form1.wf4.value)==false&&isNaN(document.form1.wf5.value)==false&&isNaN(document.form1.wf6.value)==false&&
			isNaN(document.form1.wf7.value)==false&&isNaN(document.form1.wf8.value)==false&&isNaN(document.form1.wf9.value)==false&&
			isNaN(document.form1.wf10.value)==false&&isNaN(document.form1.wf11.value)==false&&isNaN(document.form1.wf12.value)==false){
			
		if(isNaN(document.form1.Jn1.value)==false&&isNaN(document.form1.Jn2.value)==false&&isNaN(document.form1.Jn3.value)==false&&
			isNaN(document.form1.Jn4.value)==false&&isNaN(document.form1.Jn5.value)==false&&isNaN(document.form1.Jn6.value)==false&&
			isNaN(document.form1.Jn7.value)==false&&isNaN(document.form1.Jn8.value)==false&&isNaN(document.form1.Jn9.value)==false&&
			isNaN(document.form1.Jn10.value)==false&&isNaN(document.form1.Jn11.value)==false&&isNaN(document.form1.Jn12.value)==false){
			
		if(isNaN(document.form1.ta1.value)==false&&isNaN(document.form1.ta2.value)==false&&isNaN(document.form1.ta3.value)==false&&
			isNaN(document.form1.ta4.value)==false&&isNaN(document.form1.ta5.value)==false&&isNaN(document.form1.ta6.value)==false&&
			isNaN(document.form1.ta7.value)==false&&isNaN(document.form1.ta8.value)==false&&isNaN(document.form1.ta9.value)==false&&
			isNaN(document.form1.ta10.value)==false&&isNaN(document.form1.ta11.value)==false&&isNaN(document.form1.ta12.value)==false){
		
		if(isNaN(document.form1.wh1.value)==false&&isNaN(document.form1.wh2.value)==false&&isNaN(document.form1.wh3.value)==false&&
			isNaN(document.form1.wh4.value)==false&&isNaN(document.form1.wh5.value)==false&&isNaN(document.form1.wh6.value)==false&&
			isNaN(document.form1.wh7.value)==false&&isNaN(document.form1.wh8.value)==false&&isNaN(document.form1.wh9.value)==false&&
			isNaN(document.form1.wh10.value)==false&&isNaN(document.form1.wh11.value)==false&&isNaN(document.form1.wh12.value)==false){
		
		if(isNaN(document.form1.rz1.value)==false&&isNaN(document.form1.rz2.value)==false&&isNaN(document.form1.rz3.value)==false&&
			isNaN(document.form1.rz4.value)==false&&isNaN(document.form1.rz5.value)==false&&isNaN(document.form1.rz6.value)==false&&
			isNaN(document.form1.rz7.value)==false&&isNaN(document.form1.rz8.value)==false&&isNaN(document.form1.rz9.value)==false&&
			isNaN(document.form1.rz10.value)==false&&isNaN(document.form1.rz11.value)==false&&isNaN(document.form1.rz12.value)==false){
		
		if(isNaN(document.form1.lw1.value)==false&&isNaN(document.form1.lw2.value)==false&&isNaN(document.form1.lw3.value)==false&&
			isNaN(document.form1.lw4.value)==false&&isNaN(document.form1.lw5.value)==false&&isNaN(document.form1.lw6.value)==false&&
			isNaN(document.form1.lw7.value)==false&&isNaN(document.form1.lw8.value)==false&&isNaN(document.form1.lw9.value)==false&&
			isNaN(document.form1.lw10.value)==false&&isNaN(document.form1.lw11.value)==false&&isNaN(document.form1.lw12.value)==false){
		
		if(isNaN(document.form1.ly1.value)==false&&isNaN(document.form1.ly2.value)==false&&isNaN(document.form1.ly3.value)==false&&
			isNaN(document.form1.ly4.value)==false&&isNaN(document.form1.ly5.value)==false&&isNaN(document.form1.ly6.value)==false&&
			isNaN(document.form1.ly7.value)==false&&isNaN(document.form1.ly8.value)==false&&isNaN(document.form1.ly9.value)==false&&
			isNaN(document.form1.ly10.value)==false&&isNaN(document.form1.ly11.value)==false&&isNaN(document.form1.ly12.value)==false){
		
		if(isNaN(document.form1.dz1.value)==false&&isNaN(document.form1.dz2.value)==false&&isNaN(document.form1.dz3.value)==false&&
			isNaN(document.form1.dz4.value)==false&&isNaN(document.form1.dz5.value)==false&&isNaN(document.form1.dz6.value)==false&&
			isNaN(document.form1.dz7.value)==false&&isNaN(document.form1.dz8.value)==false&&isNaN(document.form1.dz9.value)==false&&
			isNaN(document.form1.dz10.value)==false&&isNaN(document.form1.dz11.value)==false&&isNaN(document.form1.dz12.value)==false){
		
		if(isNaN(document.form1.lc1.value)==false&&isNaN(document.form1.lc2.value)==false&&isNaN(document.form1.lc3.value)==false&&
			isNaN(document.form1.lc4.value)==false&&isNaN(document.form1.lc5.value)==false&&isNaN(document.form1.lc6.value)==false&&
			isNaN(document.form1.lc7.value)==false&&isNaN(document.form1.lc8.value)==false&&isNaN(document.form1.lc9.value)==false&&
			isNaN(document.form1.lc10.value)==false&&isNaN(document.form1.lc11.value)==false&&isNaN(document.form1.lc12.value)==false){
			
		if(isNaN(document.form1.bz1.value)==false&&isNaN(document.form1.bz2.value)==false&&isNaN(document.form1.bz3.value)==false&&
			isNaN(document.form1.bz4.value)==false&&isNaN(document.form1.bz5.value)==false&&isNaN(document.form1.bz6.value)==false&&
			isNaN(document.form1.bz7.value)==false&&isNaN(document.form1.bz8.value)==false&&isNaN(document.form1.bz9.value)==false&&
			isNaN(document.form1.bz10.value)==false&&isNaN(document.form1.bz11.value)==false&&isNaN(document.form1.bz12.value)==false){
		
		if(isNaN(document.form1.hz1.value)==false&&isNaN(document.form1.hz2.value)==false&&isNaN(document.form1.hz3.value)==false&&
			isNaN(document.form1.hz4.value)==false&&isNaN(document.form1.hz5.value)==false&&isNaN(document.form1.hz6.value)==false&&
			isNaN(document.form1.hz7.value)==false&&isNaN(document.form1.hz8.value)==false&&isNaN(document.form1.hz9.value)==false&&
			isNaN(document.form1.hz10.value)==false&&isNaN(document.form1.hz11.value)==false&&isNaN(document.form1.hz12.value)==false){
		
		if(isNaN(document.form1.jz1.value)==false&&isNaN(document.form1.jrw1.value)==false&&isNaN(document.form1.xzzj1.value)==false&&
			isNaN(document.form1.jyjf1.value)==false&&isNaN(document.form1.qt1.value)==false&&isNaN(document.form1.idcjf1.value)==false){
		
		if(isNaN(document.form1.jz2.value)==false&&isNaN(document.form1.jrw2.value)==false&&isNaN(document.form1.xzzj2.value)==false&&
			isNaN(document.form1.jyjf2.value)==false&&isNaN(document.form1.qt2.value)==false&&isNaN(document.form1.idcjf2.value)==false){
		
		if(isNaN(document.form1.jz3.value)==false&&isNaN(document.form1.jrw3.value)==false&&isNaN(document.form1.xzzj3.value)==false&&
			isNaN(document.form1.jyjf3.value)==false&&isNaN(document.form1.qt3.value)==false&&isNaN(document.form1.idcjf3.value)==false){
		
		if(isNaN(document.form1.jz4.value)==false&&isNaN(document.form1.jrw4.value)==false&&isNaN(document.form1.xzzj4.value)==false&&
			isNaN(document.form1.jyjf4.value)==false&&isNaN(document.form1.qt4.value)==false&&isNaN(document.form1.idcjf4.value)==false){
		
		if(isNaN(document.form1.jz5.value)==false&&isNaN(document.form1.jrw5.value)==false&&isNaN(document.form1.xzzj5.value)==false&&
			isNaN(document.form1.jyjf5.value)==false&&isNaN(document.form1.qt5.value)==false&&isNaN(document.form1.idcjf5.value)==false){
		
		if(isNaN(document.form1.jz6.value)==false&&isNaN(document.form1.jrw6.value)==false&&isNaN(document.form1.xzzj6.value)==false&&
			isNaN(document.form1.jyjf6.value)==false&&isNaN(document.form1.qt6.value)==false&&isNaN(document.form1.idcjf6.value)==false){
		
		if(isNaN(document.form1.xs1.value)==false&&isNaN(document.form1.jcxs1.value)==false){
		
		if(isNaN(document.form1.xs2.value)==false&&isNaN(document.form1.jcxs2.value)==false){
		
		if(isNaN(document.form1.xs3.value)==false&&isNaN(document.form1.jcxs3.value)==false){
				addKtxs();
			}else{
				alert(" 房顶(其他)  系数 ,基础系数    必须为数字");
			}
			}else{
				alert("板房(彩钢板) 系数 ,基础系数    必须为数字");
			}
			}else{
				alert(" 砖房(砖混) 系数 ,基础系数    必须为数字");
			}
			
			}else{
				alert("100A以上 基站空调系数,接入网空调值 ,乡镇支局空调系数 ,局用机房空调系数 ,其他空调系数,IDC机房空调系数   必须为数字");
			}
			}else{
				alert("61-100A 基站空调系数,接入网空调值 ,乡镇支局空调系数 ,局用机房空调系数 ,其他空调系数,IDC机房空调系数   必须为数字");
			}
			
			}else{
				alert("41-60A 基站空调系数,接入网空调值 ,乡镇支局空调系数 ,局用机房空调系数 ,其他空调系数,IDC机房空调系数   必须为数字");
			}
			
			}else{
				alert("21-40A 基站空调系数,接入网空调值 ,乡镇支局空调系数 ,局用机房空调系数 ,其他空调系数,IDC机房空调系数   必须为数字");
			}
			}else{
				alert("8-20A 基站空调系数,接入网空调值 ,乡镇支局空调系数 ,局用机房空调系数 ,其他空调系数,IDC机房空调系数   必须为数字");
			}
			}else{
				alert("0-7A 基站空调系数,接入网空调值 ,乡镇支局空调系数 ,局用机房空调系数 ,其他空调系数,IDC机房空调系数   必须为数字");
			}
			}else{
				alert("菏泽1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
			}else{
				alert("滨州1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
		
			}else{
				alert("聊城1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
			}else{
				alert("德州1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
			}else{
				alert("临沂1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
			}else{
				alert("莱芜1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
			}else{
				alert("日照 1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
			}else{
				alert("威海1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
			}else{
				alert("泰安1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
		
			}else{
				alert("济宁1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
		
			}else{
				alert("潍坊1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
		
			}else{
				alert("烟台1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
		
			}else{
				alert("东营1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
			}else{
				alert("枣庄1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
			}else{
				alert("淄博 1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
			}
		}else{
			alert("青岛 1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
		}
	}else{
		alert("济南 1,2,3,4,5,6,7,8,9,10,11,12月系数必须为数字");
	}
}
			function addKtxs(){
        		if(confirm("您将要添加站点信息！确认信息正确！")){
        		
          		document.form1.action=path+"/servlet/KtxsServlet?action=addKtxs";
							document.form1.submit();
							 showdiv("请稍等..............");
            }
        	}
			
	function gengxin(){
		if(document.form1.value==""){
			alert("请选择更新月份！");
		}else{
		document.form1.action=path+"/servlet/KtxsServlet?action=update";
		document.form1.submit();
		showdiv("请稍等..............");
		}
	}
		
</script>

	</head>

	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<%
		permissionRights = commBean.getPermissionRight(roleId, "0804");
	%>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<%--<table width="100%" border="0" cellspacing="0" cellpadding="0" height="18%">
				<tr>
			    	<td colspan="4" width="50" >
						 <div style="width:700px;height:50px">
						  
						  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">空调系数修改</span>	
						 </div>
					</td>
				</tr>
			</table>
			
			--%>
			<table border="1">
			<tr>
			<td colspan="2">
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
				<tr>
				<td><div>更新时间：<input type="text" name="gxsj" class="selected_font" value="" readonly="readonly"  style="width: 130px;"/><span class="style1">&nbsp;*</span>	 
                </div>	</td>
				</tr>
			</table>
		
		
				
					<table width="1075px" height="200">
					<tr height="16"  >
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<%--<input type="checkbox" name="test" onclick="chooseAll()" />
							--%>
							<font size="2">&nbsp;市&nbsp;</font>
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<font size="2">&nbsp;1月&nbsp;</font>
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<font size="2">&nbsp;2月&nbsp;</font>
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<font size="2">&nbsp;3月&nbsp;</font>
							</div>
						</td>

						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<font size="2">&nbsp;4月&nbsp;</font>
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<font size="2">&nbsp;5月&nbsp;</font>
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<font size="2">&nbsp;6月&nbsp;</font>
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<font size="2">&nbsp;7月&nbsp;</font>
							</div>
						</td>

						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<font size="2">&nbsp;8月&nbsp;</font>
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<font size="2">&nbsp;9月&nbsp;</font>
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<font size="2">&nbsp;10月&nbsp;</font>
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<font size="2">&nbsp;11月&nbsp;</font>
							</div>
						</td>
						<td width="7%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<font size="2">&nbsp;12月&nbsp;</font>
							</div>
						</td>

					</tr>
					<% 
					KtxsDao bean = new KtxsDao();
       				List<Ktxs> fylist = new ArrayList<Ktxs>();
       				fylist = bean.getyfxs();
       
					String id="",shi1="",ymonth="",emonth="",smonth="",simonth="",wmonth="",lmonth="",qmonth="",bmonth="",
					jmonth="",shimonth="",symonth="",semonth="",yfbz="";
					String jnymonth ="",jnemonth="",jnsmonth="",jnsimonth="",jnwmonth="",jnlmonth="",jnqmonth="",jnbmonth="",jnjmonth="",jnshimonth="",jnsymonth="",jnsemonth="";
					String qdymonth ="",qdemonth="",qdsmonth="",qdsimonth="",qdwmonth="",qdlmonth="",qdqmonth="",qdbmonth="",qdjmonth="",qdshimonth="",qdsymonth="",qdsemonth="";
					String zbymonth ="",zbemonth="",zbsmonth="",zbsimonth="",zbwmonth="",zblmonth="",zbqmonth="",zbbmonth="",zbjmonth="",zbshimonth="",zbsymonth="",zbsemonth="";
					String zzymonth ="",zzemonth="",zzsmonth="",zzsimonth="",zzwmonth="",zzlmonth="",zzqmonth="",zzbmonth="",zzjmonth="",zzshimonth="",zzsymonth="",zzsemonth="";
					String dyymonth ="",dyemonth="",dysmonth="",dysimonth="",dywmonth="",dylmonth="",dyqmonth="",dybmonth="",dyjmonth="",dyshimonth="",dysymonth="",dysemonth="";
					String ytymonth ="",ytemonth="",ytsmonth="",ytsimonth="",ytwmonth="",ytlmonth="",ytqmonth="",ytbmonth="",ytjmonth="",ytshimonth="",ytsymonth="",ytsemonth="";
					String wfymonth ="",wfemonth="",wfsmonth="",wfsimonth="",wfwmonth="",wflmonth="",wfqmonth="",wfbmonth="",wfjmonth="",wfshimonth="",wfsymonth="",wfsemonth="";
					String Jnymonth ="",Jnemonth="",Jnsmonth="",Jnsimonth="",Jnwmonth="",Jnlmonth="",Jnqmonth="",Jnbmonth="",Jnjmonth="",Jnshimonth="",Jnsymonth="",Jnsemonth="";
					String tymonth ="",temonth="",tsmonth="",tsimonth="",twmonth="",tlmonth="",tqmonth="",tbmonth="",tjmonth="",tshimonth="",tsymonth="",tsemonth="";
					String whymonth ="",whemonth="",whsmonth="",whsimonth="",whwmonth="",whlmonth="",whqmonth="",whbmonth="",whjmonth="",whshimonth="",whsymonth="",whsemonth="";
					String rzymonth ="",rzemonth="",rzsmonth="",rzsimonth="",rzwmonth="",rzlmonth="",rzqmonth="",rzbmonth="",rzjmonth="",rzshimonth="",rzsymonth="",rzsemonth="";
					String lwymonth ="",lwemonth="",lwsmonth="",lwsimonth="",lwwmonth="",lwlmonth="",lwqmonth="",lwbmonth="",lwjmonth="",lwshimonth="",lwsymonth="",lwsemonth="";
					String lyymonth ="",lyemonth="",lysmonth="",lysimonth="",lywmonth="",lylmonth="",lyqmonth="",lybmonth="",lyjmonth="",lyshimonth="",lysymonth="",lysemonth="";
					String dzymonth ="",dzemonth="",dzsmonth="",dzsimonth="",dzwmonth="",dzlmonth="",dzqmonth="",dzbmonth="",dzjmonth="",dzshimonth="",dzsymonth="",dzsemonth="";
					String lcymonth ="",lcemonth="",lcsmonth="",lcsimonth="",lcwmonth="",lclmonth="",lcqmonth="",lcbmonth="",lcjmonth="",lcshimonth="",lcsymonth="",lcsemonth="";
					String bzymonth ="",bzemonth="",bzsmonth="",bzsimonth="",bzwmonth="",bzlmonth="",bzqmonth="",bzbmonth="",bzjmonth="",bzshimonth="",bzsymonth="",bzsemonth="";
					String hzymonth ="",hzemonth="",hzsmonth="",hzsimonth="",hzwmonth="",hzlmonth="",hzqmonth="",hzbmonth="",hzjmonth="",hzshimonth="",hzsymonth="",hzsemonth="";
					int linenum=0;
					double df=0.0;
					if(fylist!=null){
						for(Ktxs beans:fylist){
							yfbz=beans.getYfbzw();
							id=beans.getYfxsid();
							shi=beans.getShiname();
							ymonth=beans.getYmonth();
							emonth=beans.getEmonth();
							smonth=beans.getSmonth();
							simonth=beans.getSimonth();
							wmonth=beans.getWmonth();
							lmonth=beans.getLmonth();
							qmonth=beans.getQmonth();
							bmonth=beans.getBmonth();
							jmonth=beans.getJmonth();
							shimonth=beans.getShimonth();
							symonth=beans.getSymonth();
							semonth=beans.getSemonth();
							DecimalFormat mat=new DecimalFormat("0.00");
							ymonth=mat.format(Double.parseDouble(ymonth)); 
							emonth=mat.format(Double.parseDouble(emonth)); 
							smonth=mat.format(Double.parseDouble(smonth)); 
							simonth=mat.format(Double.parseDouble(simonth)); 
							wmonth=mat.format(Double.parseDouble(wmonth)); 
							lmonth=mat.format(Double.parseDouble(lmonth)); 
							qmonth=mat.format(Double.parseDouble(qmonth)); 
							bmonth=mat.format(Double.parseDouble(bmonth)); 
							jmonth=mat.format(Double.parseDouble(jmonth)); 
							shimonth=mat.format(Double.parseDouble(shimonth)); 
							symonth=mat.format(Double.parseDouble(symonth)); 
							semonth=mat.format(Double.parseDouble(semonth));
							if(yfbz.equals("1")){
								jnymonth=ymonth; 
								jnemonth=emonth;
								jnsmonth=smonth;
								jnsimonth=simonth;
								jnwmonth=wmonth;
								jnlmonth=lmonth;
								jnqmonth=qmonth; 
								jnbmonth=bmonth;
								jnjmonth=jmonth;
								jnshimonth=shimonth;
								jnsymonth=symonth;
								jnsemonth=semonth;
							}else if(yfbz.equals("2")){
								qdymonth=ymonth; 
								qdemonth=emonth;
								qdsmonth=smonth;
								qdsimonth=simonth;
								qdwmonth=wmonth;
								qdlmonth=lmonth;
								qdqmonth=qmonth; 
								qdbmonth=bmonth;
								qdjmonth=jmonth;
								qdshimonth=shimonth;
								qdsymonth=symonth;
								qdsemonth=semonth;
							}else if(yfbz.equals("3")){
								zbymonth=ymonth; 
								zbemonth=emonth;
								zbsmonth=smonth;
								zbsimonth=simonth;
								zbwmonth=wmonth;
								zblmonth=lmonth;
								zbqmonth=qmonth; 
								zbbmonth=bmonth;
								zbjmonth=jmonth;
								zbshimonth=shimonth;
								zbsymonth=symonth;
								zbsemonth=semonth;
							}else if(yfbz.equals("4")){
								zzymonth=ymonth; 
								zzemonth=emonth;
								zzsmonth=smonth;
								zzsimonth=simonth;
								zzwmonth=wmonth;
								zzlmonth=lmonth;
								zzqmonth=qmonth; 
								zzbmonth=bmonth;
								zzjmonth=jmonth;
								zzshimonth=shimonth;
								zzsymonth=symonth;
								zzsemonth=semonth;
							}else if(yfbz.equals("5")){
								dyymonth=ymonth; 
								dyemonth=emonth;
								dysmonth=smonth;
								dysimonth=simonth;
								dywmonth=wmonth;
								dylmonth=lmonth;
								dyqmonth=qmonth; 
								dybmonth=bmonth;
								dyjmonth=jmonth;
								dyshimonth=shimonth;
							    dysymonth=symonth;
								dysemonth=semonth;
							}else if(yfbz.equals("6")){
								ytymonth=ymonth; 
								ytemonth=emonth;
								ytsmonth=smonth;
								ytsimonth=simonth;
								ytwmonth=wmonth;
								ytlmonth=lmonth;
								ytqmonth=qmonth; 
								ytbmonth=bmonth;
								ytjmonth=jmonth;
								ytshimonth=shimonth;
							    ytsymonth=symonth;
								ytsemonth=semonth;
							}else if(yfbz.equals("7")){
								wfymonth=ymonth; 
								wfemonth=emonth;
								wfsmonth=smonth;
								wfsimonth=simonth;
								wfwmonth=wmonth;
								wflmonth=lmonth;
								wfqmonth=qmonth; 
								wfbmonth=bmonth;
								wfjmonth=jmonth;
								wfshimonth=shimonth;
							    wfsymonth=symonth;
								wfsemonth=semonth;
								
							}else if(yfbz.equals("8")){
								Jnymonth=ymonth; 
								Jnemonth=emonth;
								Jnsmonth=smonth;
								Jnsimonth=simonth;
								Jnwmonth=wmonth;
								Jnlmonth=lmonth;
								Jnqmonth=qmonth; 
								Jnbmonth=bmonth;
								Jnjmonth=jmonth;
								Jnshimonth=shimonth;
								Jnsymonth=symonth;
								Jnsemonth=semonth;
							}else if(yfbz.equals("9")){
								tymonth=ymonth; 
								temonth=emonth;
								tsmonth=smonth;
								tsimonth=simonth;
								twmonth=wmonth;
								tlmonth=lmonth;
								tqmonth=qmonth; 
								tbmonth=bmonth;
								tjmonth=jmonth;
								tshimonth=shimonth;
								tsymonth=symonth;
								tsemonth=semonth;
							}else if(yfbz.equals("10")){
								whymonth=ymonth; 
								whemonth=emonth;
								whsmonth=smonth;
								whsimonth=simonth;
								whwmonth=wmonth;
								whlmonth=lmonth;
								whqmonth=qmonth; 
								whbmonth=bmonth;
								whjmonth=jmonth;
								whshimonth=shimonth;
								whsymonth=symonth;
								whsemonth=semonth;
							}else if(yfbz.equals("11")){
								rzymonth=ymonth; 
								rzemonth=emonth;
								rzsmonth=smonth;
								rzsimonth=simonth;
								rzwmonth=wmonth;
								rzlmonth=lmonth;
								rzqmonth=qmonth; 
								rzbmonth=bmonth;
								rzjmonth=jmonth;
								rzshimonth=shimonth;
								rzsymonth=symonth;
								rzsemonth=semonth;
							}else if(yfbz.equals("12")){
								lwymonth=ymonth; 
								lwemonth=emonth;
								lwsmonth=smonth;
								lwsimonth=simonth;
								lwwmonth=wmonth;
								lwlmonth=lmonth;
								lwqmonth=qmonth; 
								lwbmonth=bmonth;
								lwjmonth=jmonth;
								lwshimonth=shimonth;
								lwsymonth=symonth;
								lwsemonth=semonth;
							}else if(yfbz.equals("13")){
								lyymonth=ymonth; 
								lyemonth=emonth;
								lysmonth=smonth;
								lysimonth=simonth;
								lywmonth=wmonth;
								lylmonth=lmonth;
								lyqmonth=qmonth; 
								lybmonth=bmonth;
								lyjmonth=jmonth;
								lyshimonth=shimonth;
								lysymonth=symonth;
								lysemonth=semonth;
							}else if(yfbz.equals("14")){
								dzymonth=ymonth; 
								dzemonth=emonth;
								dzsmonth=smonth;
								dzsimonth=simonth;
								dzwmonth=wmonth;
								dzlmonth=lmonth;
								dzqmonth=qmonth; 
								dzbmonth=bmonth;
								dzjmonth=jmonth;
								dzshimonth=shimonth;
								dzsymonth=symonth;
								dzsemonth=semonth;
							}else if(yfbz.equals("15")){
								lcymonth=ymonth; 
								lcemonth=emonth;
								lcsmonth=smonth;
								lcsimonth=simonth;
								lcwmonth=wmonth;
								lclmonth=lmonth;
								lcqmonth=qmonth; 
								lcbmonth=bmonth;
								lcjmonth=jmonth;
								lcshimonth=shimonth;
								lcsymonth=symonth;
								lcsemonth=semonth;
							}else if(yfbz.equals("16")){
								bzymonth=ymonth; 
								bzemonth=emonth;
								bzsmonth=smonth;
								bzsimonth=simonth;
								bzwmonth=wmonth;
								bzlmonth=lmonth;
								bzqmonth=qmonth; 
								bzbmonth=bmonth;
								bzjmonth=jmonth;
								bzshimonth=shimonth;
								bzsymonth=symonth;
								bzsemonth=semonth;
							}else if(yfbz.equals("17")){
								hzymonth=ymonth; 
								hzemonth=emonth;
								hzsmonth=smonth;
								hzsimonth=simonth;
								hzwmonth=wmonth;
								hzlmonth=lmonth;
								hzqmonth=qmonth; 
								hzbmonth=bmonth;
								hzjmonth=jmonth;
								hzshimonth=shimonth;
								hzsymonth=symonth;
								hzsemonth=semonth;
							}
						}}
				%>	
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;济南&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="jn1" size="4" class="ipt" value="<%=jnymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="jn2" size="4" class="ipt" value="<%=jnemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="jn3" size="4" class="ipt" value="<%=jnsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="jn4" size="4" class="ipt" value="<%=jnsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="jn5" size="4" class="ipt" value="<%=jnwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="jn6" size="4" class="ipt" value="<%=jnlmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="jn7" size="4" class="ipt" value="<%=jnqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="jn8" size="4" class="ipt" value="<%=jnbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="jn9" size="4" class="ipt" value="<%=jnjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="jn10" size="4" class="ipt" value="<%=jnshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="jn11" size="4" class="ipt" value="<%=jnsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="jn12" size="4" class="ipt" value="<%=jnsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;青岛&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="qd1" size="4" class="ipt" value="<%=qdymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="qd2" size="4" class="ipt" value="<%=qdemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="qd3" size="4" class="ipt" value="<%=qdsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="qd4" size="4" class="ipt" value="<%=qdsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="qd5" size="4" class="ipt" value="<%=qdwmonth%>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="qd6" size="4" class="ipt" value="<%=qdlmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="qd7" size="4" class="ipt" value="<%=qdqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="qd8" size="4" class="ipt" value="<%=qdbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="qd9" size="4" class="ipt" value="<%=qdjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="qd10" size="4" class="ipt" value="<%=qdshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="qd11" size="4" class="ipt" value="<%=qdsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="qd12" size="4" class="ipt" value="<%=qdsemonth %>"/></div>
						</td>
					</tr><tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;淄博&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zb1" size="4" class="ipt" value="<%=zbymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zb2" size="4" class="ipt" value="<%=zbemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zb3" size="4" class="ipt" value="<%=zbsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zb4" size="4" class="ipt" value="<%=zbsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zb5" size="4" class="ipt" value="<%=zbwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zb6" size="4" class="ipt" value="<%=zblmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zb7" size="4" class="ipt" value="<%=zbqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zb8" size="4" class="ipt" value="<%=zbbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zb9" size="4" class="ipt" value="<%=zbjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zb10" size="4" class="ipt" value="<%=zbshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zb11" size="4" class="ipt" value="<%=zbsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zb12" size="4" class="ipt" value="<%=zbsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;枣庄&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zz1" size="4" class="ipt" value="<%=zzymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zz2" size="4" class="ipt" value="<%= zzemonth%>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zz3" size="4" class="ipt" value="<%=zzsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zz4" size="4" class="ipt" value="<%=zzsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zz5" size="4" class="ipt" value="<%=zzwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zz6" size="4" class="ipt" value="<%=zzlmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zz7" size="4" class="ipt" value="<%=zzqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zz8" size="4" class="ipt" value="<%=zzbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zz9" size="4" class="ipt" value="<%=zzjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zz10" size="4" class="ipt" value="<%=zzshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zz11" size="4" class="ipt" value="<%=zzsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="zz12" size="4" class="ipt" value="<%=zzsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;东营&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dy1" size="4" class="ipt" value="<%=dyymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dy2" size="4" class="ipt" value="<%=dyemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dy3" size="4" class="ipt" value="<%=dysmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dy4" size="4" class="ipt" value="<%=dysimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dy5" size="4" class="ipt" value="<%=dywmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dy6" size="4" class="ipt" value="<%=dylmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dy7" size="4" class="ipt" value="<%=dyqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dy8" size="4" class="ipt" value="<%=dybmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dy9" size="4" class="ipt" value="<%=dyjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dy10" size="4" class="ipt" value="<%=dyshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dy11" size="4" class="ipt" value="<%=dysymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dy12" size="4" class="ipt" value="<%=dysemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;烟台&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="yt1" size="4" class="ipt" value="<%=ytymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="yt2" size="4" class="ipt" value="<%=ytemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="yt3" size="4" class="ipt" value="<%=ytsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="yt4" size="4" class="ipt" value="<%=ytsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="yt5" size="4" class="ipt" value="<%=ytwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="yt6" size="4" class="ipt" value="<%=ytlmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="yt7" size="4" class="ipt" value="<%=ytqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="yt8" size="4" class="ipt" value="<%=ytbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="yt9" size="4" class="ipt" value="<%=ytjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="yt10" size="4" class="ipt" value="<%=ytshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="yt11" size="4" class="ipt" value="<%=ytsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="yt12" size="4" class="ipt" value="<%=ytsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;潍坊&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wf1" size="4" class="ipt" value="<%=wfymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wf2" size="4" class="ipt" value="<%=wfemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wf3" size="4" class="ipt" value="<%=wfsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wf4" size="4" class="ipt" value="<%=wfsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wf5" size="4" class="ipt" value="<%=wfwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wf6" size="4" class="ipt" value="<%=wflmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wf7" size="4" class="ipt" value="<%=wfqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wf8" size="4" class="ipt" value="<%=wfbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wf9" size="4" class="ipt" value="<%=wfjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wf10" size="4" class="ipt" value="<%=wfshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wf11" size="4" class="ipt" value="<%=wfsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wf12" size="4" class="ipt" value="<%=wfsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;济宁&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="Jn1" size="4" class="ipt" value="<%=Jnymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="Jn2" size="4" class="ipt" value="<%=Jnemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="Jn3" size="4" class="ipt" value="<%=Jnsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="Jn4" size="4" class="ipt" value="<%=Jnsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="Jn5" size="4" class="ipt" value="<%=Jnwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="Jn6" size="4" class="ipt" value="<%=Jnlmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="Jn7" size="4" class="ipt" value="<%=Jnqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="Jn8" size="4" class="ipt" value="<%=Jnbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="Jn9" size="4" class="ipt" value="<%=Jnjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="Jn10" size="4" class="ipt" value="<%=Jnshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="Jn11" size="4" class="ipt" value="<%=Jnsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="Jn12" size="4" class="ipt" value="<%=Jnsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;泰安&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ta1" size="4" class="ipt" value="<%=tymonth %>"></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ta2" size="4" class="ipt" value="<%=temonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ta3" size="4" class="ipt" value="<%=tsmonth%>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ta4" size="4" class="ipt" value="<%=tsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ta5" size="4" class="ipt" value="<%=twmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ta6" size="4" class="ipt" value="<%=tlmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ta7" size="4" class="ipt" value="<%=tqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ta8" size="4" class="ipt" value="<%=tbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ta9" size="4" class="ipt" value="<%=tjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ta10" size="4" class="ipt" value="<%=tshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ta11" size="4" class="ipt" value="<%=tsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ta12" size="4" class="ipt" value="<%=tsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;威海&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wh1" size="4" class="ipt" value="<%=whymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wh2" size="4" class="ipt" value="<%=whemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wh3" size="4" class="ipt" value="<%=whsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wh4" size="4" class="ipt" value="<%=whsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wh5" size="4" class="ipt" value="<%=whwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wh6" size="4" class="ipt" value="<%=whlmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wh7" size="4" class="ipt" value="<%=whqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wh8" size="4" class="ipt" value="<%=whbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wh9" size="4" class="ipt" value="<%=whjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wh10" size="4" class="ipt" value="<%=whshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wh11" size="4" class="ipt" value="<%=whsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="wh12" size="4" class="ipt" value="<%=whsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;日照&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="rz1" size="4" class="ipt" value="<%=rzymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="rz2" size="4" class="ipt" value="<%=rzemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="rz3" size="4" class="ipt" value="<%=rzsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="rz4" size="4" class="ipt" value="<%=rzsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="rz5" size="4" class="ipt" value="<%=rzwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="rz6" size="4" class="ipt" value="<%=rzlmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="rz7" size="4" class="ipt" value="<%=rzqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="rz8" size="4" class="ipt" value="<%=rzbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="rz9" size="4" class="ipt" value="<%=rzjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="rz10" size="4" class="ipt" value="<%=rzshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="rz11" size="4" class="ipt" value="<%=rzsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="rz12" size="4" class="ipt" value="<%=rzsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;莱芜&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lw1" size="4" class="ipt" value="<%=lwymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lw2" size="4" class="ipt" value="<%=lwemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lw3" size="4" class="ipt" value="<%=lwsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lw4" size="4" class="ipt" value="<%=lwsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lw5" size="4" class="ipt" value="<%=lwwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lw6" size="4" class="ipt" value="<%=lwlmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lw7" size="4" class="ipt" value="<%=lwqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lw8" size="4" class="ipt" value="<%=lwbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lw9" size="4" class="ipt" value="<%=lwjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lw10" size="4" class="ipt" value="<%=lwshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lw11" size="4" class="ipt" value="<%=lwsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lw12" size="4" class="ipt" value="<%=lwsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;临沂&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ly1" size="4" class="ipt" value="<%=lyymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ly2" size="4" class="ipt" value="<%=lyemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ly3" size="4" class="ipt" value="<%=lysmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ly4" size="4" class="ipt" value="<%=lysimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ly5" size="4" class="ipt" value="<%=lywmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ly6" size="4" class="ipt" value="<%=lylmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ly7" size="4" class="ipt" value="<%=lyqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ly8" size="4" class="ipt" value="<%=lybmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ly9" size="4" class="ipt" value="<%=lyjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ly10" size="4" class="ipt" value="<%=lyshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ly11" size="4" class="ipt" value="<%=lysymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="ly12" size="4" class="ipt" value="<%=lysemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;德州&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dz1" size="4" class="ipt" value="<%=dzymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dz2" size="4" class="ipt" value="<%=dzemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dz3" size="4" class="ipt" value="<%=dzsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dz4" size="4" class="ipt" value="<%=dzsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dz5" size="4" class="ipt" value="<%=dzwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dz6" size="4" class="ipt" value="<%=dzlmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dz7" size="4" class="ipt" value="<%=dzqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dz8" size="4" class="ipt" value="<%=dzbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dz9" size="4" class="ipt" value="<%=dzjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dz10" size="4" class="ipt" value="<%=dzshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dz11" size="4" class="ipt" value="<%=dzsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="dz12" size="4" class="ipt" value="<%=dzsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;聊城&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lc1" size="4" class="ipt" value="<%=lcymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lc2" size="4" class="ipt" value="<%=lcemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lc3" size="4" class="ipt" value="<%=lcsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lc4" size="4" class="ipt" value="<%=lcsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lc5" size="4" class="ipt" value="<%=lcwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lc6" size="4" class="ipt" value="<%=lclmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lc7" size="4" class="ipt" value="<%=lcqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lc8" size="4" class="ipt" value="<%=lcbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lc9" size="4" class="ipt" value="<%=lcjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lc10" size="4" class="ipt" value="<%=lcshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lc11" size="4" class="ipt" value="<%=lcsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="lc12" size="4" class="ipt" value="<%=lcsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;滨州&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="bz1" size="4" class="ipt" value="<%=bzymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="bz2" size="4" class="ipt" value="<%=bzemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="bz3" size="4" class="ipt" value="<%=bzsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="bz4" size="4" class="ipt" value="<%=bzsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="bz5" size="4" class="ipt" value="<%=bzwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="bz6" size="4" class="ipt" value="<%=bzlmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="bz7" size="4" class="ipt" value="<%=bzqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="bz8" size="4" class="ipt" value="<%=bzbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="bz9" size="4" class="ipt" value="<%=bzjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="bz10" size="4" class="ipt" value="<%=bzshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="bz11" size="4" class="ipt" value="<%=bzsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="bz12" size="4" class="ipt" value="<%=bzsemonth %>"/></div>
						</td>
					</tr>
					<tr height="16">
						<td>
							<div align="center"><font size="2">&nbsp;菏泽&nbsp;</font></div>
						</td>
						<td>
							<div align="center"><input type="text" name="hz1" size="4" class="ipt" value="<%=hzymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="hz2" size="4" class="ipt" value="<%=hzemonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="hz3" size="4" class="ipt" value="<%=hzsmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="hz4" size="4" class="ipt" value="<%=hzsimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="hz5" size="4" class="ipt" value="<%=hzwmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="hz6" size="4" class="ipt" value="<%=hzlmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="hz7" size="4" class="ipt" value="<%=hzqmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="hz8" size="4" class="ipt" value="<%=hzbmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="hz9" size="4" class="ipt" value="<%=hzjmonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="hz10" size="4" class="ipt" value="<%=hzshimonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="hz11" size="4" class="ipt" value="<%=hzsymonth %>"/></div>
						</td>
						<td>
							<div align="center"><input type="text" name="hz12" size="4" class="ipt" value="<%=hzsemonth %>"/></div>
						</td>
					</tr>
					
					</table>
			<%--</div>

			--%><table width="100%" border="0" cellspacing="0" cellpadding="0">
				
				<%--<tr>

					<td align="right">
						<div id="tongguo"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: -150px; top: 0px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 35px; top: 5px; color: white">同意</span>

						</div>
					</td>
					<td align="right">
						<div id="butongguo"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: -50px; top: 0px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">退回</span>
						</div>
					</td>

					<td align="right">
						<div id="quxiao"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: 50px; top: 0px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">撤回</span>
						</div>
					</td>

				</tr>
				<tr>
					<td>
						<div id="detailsDiv"
							style="position: relative; width: 99%; height: 20px; left: 0px; top: 10px; z-index: 1; float: left; overflow-y: auto;">

							<iframe name="details" frameborder="0" width="100%" height="50%"
								scrolling="no"></iframe>
						</div>
					</td>
				</tr>
			--%></table>
			</td>
			</tr>
			<tr>
				<td>
			<table>
				<tr>
					<td height="23" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 25px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;站点属性&nbsp;</font>
							<div style="width: 150px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center" class="bttcn">
								<font size="2">&nbsp;设备直流负荷&nbsp;</font>
							</div>
					</td>
					<td>
						<div align="center" class="bttcn">
								<font size="2">&nbsp;基站空调系数&nbsp;</font>
							</div>
					</td>
					<td>
						<div align="center" class="bttcn">
								<font size="2">&nbsp;接入网空调值&nbsp;</font>
							</div>
					</td>
					<td>
						<div align="center" class="bttcn">
								<font size="2">&nbsp;乡镇支局空调系数&nbsp;</font>	
							</div>
					</td>
					<td>
						<div align="center" class="bttcn">
								<font size="2">&nbsp;局用机房空调系数&nbsp;</font>
							</div>
					</td>
					<td>
						<div align="center" class="bttcn">
								<font size="2">&nbsp;其他空调系数&nbsp;</font>
							</div>
					</td>
					<td>
						<div align="center" class="bttcn">
								<font size="2">&nbsp;IDC机房空调系数&nbsp;</font>
							</div>
					</td>
				</tr>
				<%
				KtxsDao bean1 = new KtxsDao();
			       List<Ktxs> fylist1 = new ArrayList<Ktxs>();
			       	fylist1 = bean.getktxs();
			       String kszlfh="",jszlfh="",jz="",jrw="",xzzj="",jyjf="",qt="",idcjf="",id1="",zlfh="";
			       String kszlfh1="",jszlfh1="",jz1="",jrw1="",xzzj1="",jyjf1="",qt1="",idcjf1="",id11="",zlfh1="";
			       String kszlfh2="",jszlfh2="",jz2="",jrw2="",xzzj2="",jyjf2="",qt2="",idcjf2="",id12="",zlfh2="";
			       String kszlfh3="",jszlfh3="",jz3="",jrw3="",xzzj3="",jyjf3="",qt3="",idcjf3="",id13="",zlfh3="";
			       String kszlfh4="",jszlfh4="",jz4="",jrw4="",xzzj4="",jyjf4="",qt4="",idcjf4="",id14="",zlfh4="";
			       String kszlfh5="",jszlfh5="",jz5="",jrw5="",xzzj5="",jyjf5="",qt5="",idcjf5="",id15="",zlfh5="";
			       String kszlfh6="",jszlfh6="",jz6="",jrw6="",xzzj6="",jyjf6="",qt6="",idcjf6="",id16="",zlfh6="";
					 if(fylist1!=null){
						for(Ktxs bean2:fylist1){
						   id1=bean2.getKtxsid();
							kszlfh=bean2.getKszlfh();
							jszlfh=bean2.getJszlfh();
							jz=bean2.getJzktxs();
							jrw=bean2.getJrwktxs();
							xzzj=bean2.getXzzjktxs();
							jyjf=bean2.getJyjfktxs();
							qt=bean2.getQtktxs();
							idcjf=bean2.getIdcjfktxs();
							DecimalFormat mat=new DecimalFormat("0.00");
							jz=mat.format(Double.parseDouble(jz)); 
							jrw=mat.format(Double.parseDouble(jrw)); 
							xzzj=mat.format(Double.parseDouble(xzzj)); 
							jyjf=mat.format(Double.parseDouble(jyjf)); 
							qt=mat.format(Double.parseDouble(qt)); 
							idcjf=mat.format(Double.parseDouble(idcjf)); 
							if(id1.equals("1")){
								kszlfh1=kszlfh;
								jszlfh1=jszlfh;
								jz1=jz;
								jrw1=jrw;
								xzzj1=xzzj; 
								jyjf1=jyjf; 
								qt1=qt;
								idcjf1=idcjf;
							}else if(id1.equals("2")){
								kszlfh2=kszlfh;
								jszlfh2=jszlfh;
								jz2=jz;
								jrw2=jrw;
								xzzj2=xzzj; 
								jyjf2=jyjf; 
								qt2=qt;
								idcjf2=idcjf;
							}else if(id1.equals("3")){
								kszlfh3=kszlfh;
								jszlfh3=jszlfh;
								jz3=jz;
								jrw3=jrw;
								xzzj3=xzzj; 
								jyjf3=jyjf; 
								qt3=qt;
								idcjf3=idcjf;
							}else if(id1.equals("4")){
								kszlfh4=kszlfh;
								jszlfh4=jszlfh;
								jz4=jz;
								jrw4=jrw;
								xzzj4=xzzj; 
								jyjf4=jyjf; 
								qt4=qt;
								idcjf4=idcjf;
							}else if(id1.equals("5")){
								kszlfh5=kszlfh;
								jszlfh5=jszlfh;
								jz5=jz;
								jrw5=jrw;
								xzzj5=xzzj; 
								jyjf5=jyjf; 
								qt5=qt;
								idcjf5=idcjf;
							}else if(id1.equals("6")){
								kszlfh6=kszlfh;
								jz6=jz;
								jrw6=jrw;
								xzzj6=xzzj; 
								jyjf6=jyjf; 
								qt6=qt;
								idcjf6=idcjf;
							}
						}
					 }
				%>
				<tr height="16">
					<td>
						<div align="center"><input type="text" name="kszlfh1" size="4" class="ii" value="<%=kszlfh1 %>"/>-<input type="text" name="jszlfh1" size="4" class="ii" value="<%=jszlfh1 %>"/>A</div>
					</td>
					<td>
							<div align="center"><input type="text" name="jz1" size="4" class="ipt" value="<%=jz1 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="jrw1" size="4" class="ipt" value="<%=jrw1 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="xzzj1" size="4" class="ipt" value="<%=xzzj1 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="jyjf1" size="4" class="ipt" value="<%=jyjf1 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="qt1" size="4" class="ipt" value="<%=qt1 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="idcjf1" size="4" class="ipt" value="<%=idcjf1 %>"/></div>
					</td>
					
				</tr>
				<tr height="16">
					<td><div align="center"><input type="text" name="kszlfh2" size="4" class="ii" value="<%=kszlfh2 %>"/>-<input type="text" name="jszlfh2" size="4" class="ii" value="<%=jszlfh2 %>"/>A</div></td>
					<td>
							<div align="center"><input type="text" name="jz2" size="4" class="ipt" value="<%=jz2 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="jrw2" size="4" class="ipt" value="<%=jrw2 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="xzzj2" size="4" class="ipt" value="<%=xzzj2 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="jyjf2" size="4" class="ipt" value="<%=jyjf2 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="qt2" size="4" class="ipt" value="<%=qt2 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="idcjf2" size="4" class="ipt" value="<%=idcjf2 %>"/></div>
					</td>
				</tr>
				<tr height="16">
					<td><div align="center"><input type="text" name="kszlfh3" size="4" class="ii" value="<%=kszlfh3 %>"/>-<input type="text" name="jszlfh3" size="4" class="ii" value="<%=jszlfh3 %>"/>A</div></td>
					<td>
							<div align="center"><input type="text" name="jz3" size="4" class="ipt" value="<%=jz3 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="jrw3" size="4" class="ipt" value="<%=jrw3 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="xzzj3" size="4" class="ipt" value="<%=xzzj3 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="jyjf3" size="4" class="ipt" value="<%=jyjf3 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="qt3" size="4" class="ipt" value="<%=qt3 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="idcjf3" size="4" class="ipt" value="<%=idcjf3 %>"/></div>
					</td>
				</tr>
				<tr height="16">
					<td><div align="center"><input type="text" name="kszlfh4" size="4" class="ii" value="<%=kszlfh4 %>"/>-<input type="text" name="jszlfh4" size="4" class="ii" value="<%=jszlfh4 %>"/>A</div></td>
					<td>
							<div align="center"><input type="text" name="jz4" size="4" class="ipt" value="<%=jz4 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="jrw4" size="4" class="ipt" value="<%=jrw4 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="xzzj4" size="4" class="ipt" value="<%=xzzj4 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="jyjf4" size="4" class="ipt" value="<%=jyjf4 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="qt4" size="4" class="ipt" value="<%=qt4 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="idcjf4" size="4" class="ipt" value="<%=idcjf4 %>"/></div>
					</td>
				</tr>
				<tr height="16">
					<td><div align="center"><input type="text" name="kszlfh5" size="4" class="ii" value="<%=kszlfh5 %>"/>-<input type="text" name="jszlfh5" size="6" class="i" value="<%=jszlfh5 %>"/>A</div></td>
					<td>
							<div align="center"><input type="text" name="jz5" size="4" class="ipt" value="<%=jz5 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="jrw5" size="4" class="ipt" value="<%=jrw5 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="xzzj5" size="4" class="ipt" value="<%=xzzj5 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="jyjf5" size="4" class="ipt" value="<%=jyjf5 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="qt5" size="4" class="ipt" value="<%=qt5 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="idcjf5" size="4" class="ipt" value="<%=idcjf5 %>"/></div>
					</td>
				</tr>
				<tr height="16">
					<td><div align="center"><input type="text" name="kszlfh6" size="4" class="i" value="<%=kszlfh6 %>"/>A<font size="2">以上</font></div></td>
					<td>
							<div align="center"><input type="text" name="jz6" size="4" class="ipt" value="<%=jz6 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="jrw6" size="4" class="ipt" value="<%=jrw6 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="xzzj6" size="4" class="ipt" value="<%=xzzj6 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="jyjf6" size="4" class="ipt" value="<%=jyjf6 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="qt6" size="4" class="ipt" value="<%=qt6 %>"/></div>
					</td>
					<td>
							<div align="center"><input type="text" name="idcjf6" size="4" class="ipt" value="<%=idcjf6 %>"/></div>
					</td>
				</tr>
				<tr>
			</table>
				</td>
				<td align="center">
			<table>
				<tr>
					<td height="23" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 25px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;房屋类型&nbsp;</font>
							<div style="width: 150px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="center">
						<div align="center" class="bttcn">
								<font size="2">&nbsp;用房类型&nbsp;</font>
						</div>
					</td>
					<td align="center">
						<div align="center" class="bttcn">
								<font size="2">&nbsp;系数&nbsp;</font>
						</div>
					</td>
					<td align="center">
						<div align="center" class="bttcn">
								<font size="2">&nbsp;基础系数&nbsp;</font>
						</div>
					</td>
				</tr>
				
				<%
				   KtxsDao bean2 = new KtxsDao();
			       List<Ktxs> fylist2 = new ArrayList<Ktxs>();
			       	fylist2 = bean.getfwxs();
			       String fwid="",fwlx="",xs="",jcxs="",fwbzw="";
			       String fwid1="",fwlx1="",xs1="",jcxs1="",fwbzw1="";
			       String fwid2="",fwlx2="",xs2="",jcxs2="",fwbzw2="";
			       String fwid3="",fwlx3="",xs3="",jcxs3="",fwbzw3="";
					 if(fylist1!=null){
						for(Ktxs bean3:fylist2){
						fwid=bean3.getFwxsid();
						fwlx=bean3.getYfname();
						xs=bean3.getFxxs();
						jcxs=bean3.getJcxs();
						fwbzw=bean3.getFwsjbzw();
						DecimalFormat mat=new DecimalFormat("0.00");
						xs=mat.format(Double.parseDouble(xs)); 
						jcxs=mat.format(Double.parseDouble(jcxs));
						if(fwbzw.equals("1")){
							xs1=xs;
							jcxs1=jcxs;
						}else if(fwbzw.equals("2")){
							xs2=xs;
							jcxs2=jcxs;
						}else if(fwbzw.equals("3")){
							xs3=xs;
							jcxs3=jcxs;
						}
					}
				}
				
				%>
				<tr>
					<td align="center">
								<font size="2">&nbsp;砖房(砖混)&nbsp;</font>
					</td>
					<td align="center">
						<div align="center"><input type="text" name="xs1" size="4" class="ipt" value="<%=xs1 %>"/></div>
					</td>
					<td align="center">
						<div align="center"><input type="text" name="jcxs1" size="4" class="ipt" value="<%=jcxs1 %>"/></div>
					</td>
				</tr>
				<tr>
					<td align="center">
								<font size="2">&nbsp;板房(彩钢板)&nbsp;</font>
					</td>
					<td align="center">
						<div align="center"><input type="text" name="xs2" size="4" class="ipt" value="<%=xs2 %>"/></div>
					</td>
					<td align="center">
						<div align="center"><input type="text" name="jcxs2" size="4" class="ipt" value="<%=jcxs2 %>"/></div>
					</td>
				</tr>
				<tr>
					<td align="center">
								<font size="2">&nbsp;房顶(其他)&nbsp;</font>
					</td>
					<td align="center">
						<div align="center"><input type="text" name="xs3" size="4" class="ipt" value="<%=xs3 %>"/></div>
					</td>
					<td align="center">
						<div align="center"><input type="text" name="jcxs3" size="4" class="ipt" value="<%=jcxs3 %>"/></div>
					</td>
				</tr>					
			</table>
				</td>
			</tr>
			</table>
			<table width="800px">
				<tr>
				<td align="right">
						<div id="saveBtn"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: -310px; top: 0px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 35px; top: 5px; color: white">保存</span>

						</div>	
					</td>
				<!--  <td align="right" >
						<div id="gengxin"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: -280px; top: 0px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">更新</span>
						</div>
					</td>
					-->
				</tr>
				<tr>
					<td>
						<div id="detailsDiv"
							style="position: relative; width: 99%; height: 20px; left: 0px; top: 10px; z-index: 1; float: left; overflow-y: auto;">

							<iframe name="details" frameborder="0" width="100%" height="50%"
								scrolling="no"></iframe>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
	</html>
<script type="text/javascript">
function lookDetailssz(id) {
	var path = '<%=path%>';
	window
			.open(
					path + "/web/jzcbnewfunction/shishxx.jsp?zdid=" + id,
					'',
					'width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
}
</script>
<script type="text/javascript">
var XMLHttpReq;
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}

function sendRequest(url, para) {

	createXMLHttpRequest();

	XMLHttpReq.open("GET", url, true);

	if (para == "sheng") {
		XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
	} else if (para == "shi") {
		XMLHttpReq.onreadystatechange = processResponse_shi;
	} else if (para == "xian") {
		XMLHttpReq.onreadystatechange = processResponse_xian;
	} else {
		XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
	}
	XMLHttpReq.send(null);
}
// 处理返回信息函数
function processResponse() {

	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		var res = XMLHttpReq.responseText;
		window.alert(res);
	} else { //页面不正常
		window.alert("您所请求的页面有异常。");
	}
}

function processResponse_sheng() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态

		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");

			//var res = XMLHttpReq.responseText;

			updateShi(res);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}

function processResponse_shi() {

	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			updateQx(res);
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function processResponse_xian() {

	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			updateZd(res);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
		document.form1.gxsj.value='<%=gxsj%>';
</script>


