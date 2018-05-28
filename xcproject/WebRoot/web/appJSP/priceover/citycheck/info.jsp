<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.ptac.app.priceover.citycheck.bean.CityCheckBean" %>
<%

	String path = request.getContextPath();
    CityCheckBean bean = (CityCheckBean)request.getAttribute("bean");
	
%>
<html>
	<head>
		<title>基础数据</title>

		<style>
.style1 {
	color: red;
	font-weight: bold;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	width: 140px;
}

.form_labell {
	text-align: center;
	font-family: 宋体;
	font-size: 12px;
	width: 130px;
}

.form {
	height: 19px;
	width: 130px;
}

.formjc {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	height: 19px;
	width: 800px;
}

.formm {
	text-align: right;
	height: 19px;
	width: 80px;
}

.formr {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: right;
	height: 19px;
	width: 130px;
}

.formr5 {
	text-align: right;
	height: 19px;
	width: 130px;
}

.formr1 {
	text-align: right;
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	height: 19px;
	width: 130px;
	color: red;
}

.formcenter {
	text-align: center;
	height: 19px;
	width: 130px;
}

.form1 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	height: 19px;
	width: 130px;
}

.formcc {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	height: 19px;
	width: 800px;
}

.form2 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: right;
	height: 19px;
	width: 130px;
}

.form4 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: left;
	color: red;
	height: 19px;
	width: 130px;
}

.form3 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: left;
	height: 19px;
	width: 130px;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>

	</head>
	<jsp:useBean id="roleBean" scope="page"
		class="com.noki.mobi.sys.javabean.RoleBean"></jsp:useBean>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body class="body" style="overflow-x: hidden;">
		<br>
		<LINK href="../../images/css.css" type="text/css" rel=stylesheet />
		<form action="" name="form1" method="POST" id="form">
			<table border="0" width="100%">
				<tr>
					<td width="83%" scope="2">
						<table width="100%" border="0" cellspacing="1" cellpadding="1">
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="<%=path %>/images/v.gif" width="15" height="15" />
									基础数据
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										站点名称：
									</div>
								</td>
								<td>
									<input type="text" name="zdname" readonly="true"
										value="${bean.zdname}" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										站点ID：
									</div>
								</td>
								<td>
									<input type="text" name="zdid" readonly="true"
										value="${bean.zdid}" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										站点属性：
									</div>
								</td>
								<td>
									<input type="text" name="szdq" readonly="true"
										value="${bean.zdsx}" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										站点类型：
									</div>
								</td>
								<td>
									<input type="text" name="accountmonth" readonly="true"
										value="${bean.zdlx}" class="form1" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										城市：
									</div>
								</td>
								<td>
									<input type="text" name="cbbili" readonly="true"
										value="${bean.city}" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										区县：
									</div>
								</td>
								<td>
									<input type="text" name="entryperson" readonly="true"
										value="${bean.xian}" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										乡镇：
									</div>
								</td>
								<td>
									<input type="text" name="entrytime" readonly="true"
										value="${bean.xiaoqu}" class="form1" />
								</td>
								<td></td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										报账月份：
									</div>
								</td>
								<td>
									<input type="text" name="zdname" readonly="true"
										value="${bean.accountmonth}" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										报账电费：
									</div>
								</td>
								<td>
									<input type="text" name="zdid" readonly="true"
										value="${bean.bzdf}" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										报账电量：
									</div>
								</td>
								<td>
									<input type="text" name="szdq" readonly="true"
										value="${bean.bzdl}" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										实际单价：
									</div>
								</td>
								<td>
									<input type="text" name="accountmonth" readonly="true"
										value="${bean.sjdj}" class="form1" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										标准单价：
									</div>
								</td>
								<td>
									<input type="text" name="cbbili" readonly="true"
										value="${bean.bzdj}" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										偏离度：
									</div>
								</td>
								<td>
									<input type="text" name="entryperson" readonly="true"
										value="${bean.pld}" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										供电方式：
									</div>
								</td>
								<td>
									<input type="text" name="entrytime" readonly="true"
										value="${bean.gdfs}" class="form1" />
								</td>
								<td></td>
							</tr>
							
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="<%=path %>/images/v.gif" width="15" height="15" />
									比例信息
								</td>
							</tr>
							<tr>

								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										业主用电类型：
									</div>
								</td>
								<td>
									<input type="text" name="g2last" id="g2last"
										value="${bean.yzydlx}" readonly="readonly" class="form3">
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										业主获电基础单价：
									</div>
								</td>
								<td>
									<input type="text" name="g3last" id="g3last"
										value="${bean.yzhdjcdj}" readonly="readonly" class="form3">
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										核准单价：
									</div>
								</td>
								<td>
									<input type="text" name="zplast" readonly="true"
										value="${bean.hzdj}" class="form3" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										省标准单价：
									</div>
								</td>
								<td>
									<input type="text" name="g2last" id="changjialast"
										value="${bean.bzdj}" readonly="readonly" class="form3">
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										尖峰电量(占比)：
									</div>
								</td>
								<td>
									<input type="text" name="jztypelast" id="jztypelast"
										value="${bean.jfdlzb}" readonly="readonly" class="form3">
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										平电量(占比)：
									</div>
								</td>
								<td>
									<input type="text" name="shebeilast" id="shebeilast"
										value="${bean.pdlzb}" readonly="readonly" class="form3">
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										直供电基础单价：
									</div>
								</td>
								<td>
									<input type="text" name="g2last" id="changjialast"
										value="${bean.zgdjcdj}" readonly="readonly" class="form3">
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										高峰电量(占比)：
									</div>
								</td>
								<td>
									<input type="text" name="jztypelast" id="jztypelast"
										value="${bean.gfdlzb}" readonly="readonly" class="form3">
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										谷电量(占比)：
									</div>
								</td>
								<td>
									<input type="text" name="shebeilast" id="shebeilast"
										value="${bean.gdlzb}" readonly="readonly" class="form3">
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										变压器容量：
									</div>
								</td>
								<td>
									<input type="text" name="rrulast" readonly="true"
										value="${bean.byqrl}" class="form3" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										倍率：
									</div>
								</td>
								<td>
									<input type="text" name="gxgwsllast" readonly="true"
										value="${bean.beilv}" class="form3" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										用电属性：
									</div>
								</td>
								<td>
									<input type="text" name="dxgxsblast" value="${bean.ydsx}"
										class="form3" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										省定标：
									</div>
								</td>
								<td>
									<input type="text" name="ydgxsblast" value="${bean.sdb}"
										class="form3" readonly="readonly" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										线变损率(%)：
									</div>
								</td>
								<td>
									<input type="text" name="g2xqlast" readonly="true"
										value="${bean.xbsl}" class="form3" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										线变损电量：
									</div>
								</td>
								<td>
									<input type="text" name="g3sqlast" readonly="true"
										value="${bean.xbsdl}" class="form3" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										管理费上浮系数(%)：
									</div>
								</td>
								<td>
									<input type="text" name="ydgxsblast" value="${bean.glfsfxs}"
										class="form3" readonly="readonly" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										忙时上浮系数(%)：
									</div>
								</td>
								<td>
									<input type="text" name="g2xqlast" readonly="true"
										value="${bean.msfxs}" class="form3" />
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td colspan="8" bgcolor="F9F9F9" class="form_label">
									<img src="<%=path %>/images/v.gif" width="15" height="15" />
									附件说明：
								</td>
							</tr>
							<tr height="19px">
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										用电合同附件：
									</div>
								</td>
								<td width='400px'>
									<div><input type="file" id="fileUp1" name="fileUp1" height="19px" width="130px" onchange="fileChange(this);"/>
									<c:choose>
									<c:when test="${f1 eq true}">
									<a href="#" onclick="modifyjz2('${bean.id}','1');return false;">
												<font  style="font-size: 15px;color: red">下载附件</font></a>
									</c:when>
									<c:when test="${f1 eq false}">
									<font  style="font-size: 15px;color: red">无附件</font>
									</c:when>
									</c:choose>
									</div>
								</td>

							</tr>
							<tr height="19px">
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										忙时增益依据(照片)：
									</div>
								</td>
								<td>
									<div>
										<input type="file" id="fileUp2" name="fileUp2" height="19px" width="130px" onchange="fileChange(this);"/>
											<c:choose>
											<c:when test="${f2 eq true}">
											<a href="#" onclick="modifyjz2('${bean.id}','2');return false;">
														<font  style="font-size: 15px;color: red">下载附件</font></a>
											</c:when>
											<c:when test="${f2 eq false}">
											<font  style="font-size: 15px;color: red">无附件</font>
											</c:when>
											</c:choose>
									</div>
								</td>
							</tr>
							<tr height="19px">
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										业主尖峰电量明细(照片)：
									</div>
								</td>
								<td>
									<div>
										<input type="file" id="fileUp3" name="fileUp3" height="19px" width="130px" onchange="fileChange(this);"/>
											<c:choose>
											<c:when test="${f3 eq true}">
											<a href="#" onclick="modifyjz2('${bean.id}','3');return false;">
														<font  style="font-size: 15px;color: red">下载附件</font></a>
											</c:when>
											<c:when test="${f3 eq false}">
											<font  style="font-size: 15px;color: red">无附件</font>
											</c:when>
											</c:choose>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" name="tidc" value="0"/>
		</form>
	</body>
<script type="text/javascript">
var path = '<%=path%>';
var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
  function fileChange(target) { 
	document.form1.tidc.value=0;
    var fileSize = 0;           
    if (isIE && !target.files) {       
      var filePath = target.value;       
      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");          
      var file = fileSystem.GetFile (filePath);       
      fileSize = file.Size;      
    } else {      
     fileSize = target.files[0].size;       
     }     
     var size = fileSize / 1024;
     if(size>6144){    
      	alert("附件不能大于6M");  
      	document.form1.tidc.value=1;
     }    
}

function modifyjz2(id,bzw){
     document.form1.action=path+"/servlet/DownLoadAction?wyid="+id+"&bzw="+bzw;
     document.form1.submit();
}
</script>
</html>
