<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.ptac.app.businesshall.bean.*,com.ptac.app.businesshall.dao.*"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=path%>/javascript/PopupCalendar.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
	<script src="<%=path%>/javascript/tx.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	

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
	function ShowHideSearchRegion(trObject, SelfObject) {
		if (trObject.style.display == "none") {
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"
	
		} else {
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
	}
	
	function saveBusinesshall() {
		if(!(checkNotSelected(document.form1.entityType, "实体类型") 
				&& checkNotSelected(document.form1.entitySmallType, "子类型")
				&& checkNotnull(document.form1.entityName, "实体名称")
				&& checkNotnull(document.form1.company, "分公司")
				&& checkNotnull(document.form1.branchCompany, "区县分公司") 
				&& checkNotSelected(document.form1.practicalUse, "用途") 
				&& checkNotSelected(document.form1.ownership, "所有权")
				&& checkNotnull(document.form1.numberOfEmployees, "员工人数")
				&& checkNotnull(document.form1.builtArea, "面积"))){
			return;
		}
		if(confirm("您将要添加新实体的信息！确认信息正确！")){
            document.form1.action=path+"/servlet/businesshallServlet?action=save"
        	document.form1.submit()
        }
	}
   	$(function(){
	    $("#resetBtn").click(function(){
			$.each($("form input[type='text']"),function(){
				$(this).val("");
			})
		});
   });
		
		
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<jsp:useBean id="businesshall" scope="page" class="com.ptac.app.businesshall.bean.Businesshall"></jsp:useBean>
</head>
<%
     String id = request.getParameter("id");
     if(id !=null && !id.isEmpty()){
	     BusinesshallDao dao = new BusinesshallDaoImpl();
	     businesshall = dao.getOne(Integer.parseInt(id));
     }

%>
<body>

<form action="" name="form1" method="post">
<input type="hidden" name="id" value="<%=businesshall.getId() %>"/>
<input type="hidden" name="entityCode" value="<%=businesshall.getEntityCode() %>"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">实体修改</div>
				<div class="content01_1">
					<table width="1000px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="100px">实体类型</td>
							<td width="100px">
								<select name="entityType" style="width: 130px">
									<option value="0">请选择</option>
									<option value="机楼">机楼</option>
								</select>
								<span >&nbsp;*</span>
							</td>
							<td align="right" width="100px">子类型</td>
							<td width="100px">
								<select name="entitySmallType" style="width: 130px">
									<option value="0">请选择</option>
									<option value="办公楼">办公楼(机楼)</option>
								</select>
								<span class="style1">&nbsp;*</span>
							</td>
							<td align="right" width="100px">实体编号</td>
							<td width="100px">
								<%=businesshall.getEntityCode() %>
							</td>
							<td align="right" width="100px">实体名称</td>
							<td>
							   <input type="text" name="entityName" maxlength="20" value="<%=businesshall.getEntityName() %>" style="width: 130px;"/>
							   <span class="style1">&nbsp;*</span>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">是否附属实体</td>
							<td width="100px">
							   <input type="radio" name="isAttachedEntity" value="1" <%if(businesshall.isAttachedEntity()==1){out.print("checked");} %>/>是
							   <input type="radio" name="isAttachedEntity" value="0" <%if(businesshall.isAttachedEntity()==0){out.print("checked");} %>/>否
							</td>
							<td align="right" width="100px">分公司</td>
							<td width="100px">
								<input type="text" name="company" maxlength="20" value="<%=businesshall.getCompany() %>" style="width: 130px;"/>
								<span>&nbsp;*</span>
							</td>
							<td align="right" width="100px">区县分公司</td>
							<td width="100px">
								<input type="text" name="branchCompany" maxlength="20" value="<%=businesshall.getBranchCompany() %>" style="width: 130px;"/>
								<span>&nbsp;*</span>
							</td>
							<td align="right" width="100px">用途</td>
							<td width="100px">
								<select name="practicalUse" style="width: 130px;">
								    <option value="0">请选择</option>
									<option value="1">办公</option>
								</select>
								<span>&nbsp;*</span>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">所有权</td>
							<td width="100px">
								<select name="ownership" style="width: 130px;" >
								    <option value="0">请选择</option>
									<option value="1">非自有</option>
								</select>
							</td>
							<td align="right" width="100px">责任人</td>
							<td width="100px">
								<input type="text" name="personLiable" value="<%=businesshall.getPersonLiable() %>" style="width: 130px;" />
							</td>
							<td align="right" width="100px">使用权</td>
							<td width="100px">
								<select name="rightOfUse" style="width: 130px" >
								    <option value="0">请选择</option>
									<option value="1">自用</option>
								</select>
							</td>
							<td align="right" width="100px">外围系统编码</td>
							<td width="100px">
								<input type="text" name="peripheralSystemCode" value="<%=businesshall.getPeripheralSystemCode() %>" maxlength="20"  onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" style="width: 130px;" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">建筑结构</td>
							<td width="100px">
								<select name="buildingStructure" style="width: 130px" >
								    <option value="0">请选择</option>
									<option value="1" selected="selected">钢筋混凝土</option>
								</select>
							</td>
							<td align="right" width="100px">员工人数</td>
							<td width="100px">
								<input type="text" name="numberOfEmployees" value="<%=businesshall.getNumberOfEmployees() %>" onkeyup="value=value.replace(/[^\d]/g,'')" style="width: 130px;" />
								<span>&nbsp;*</span>
							</td>
							<td align="right" width="100px">状态</td>
							<td width="100px">
								<input type="radio" name="status" value="1" <%if(businesshall.getStatus()==1){out.print("checked");} %>/>闲置
								<input type="radio" name="status" value="2" <%if(businesshall.getStatus()==2){out.print("checked");} %>/>停用
								<input type="radio" name="status" value="3" <%if(businesshall.getStatus()==3){out.print("checked");} %>/>在用
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">面积</td>
							<td width="100px">
								<input type="text" name="builtArea" value="<%=businesshall.getBuiltArea() %>" onkeyup="value=value.replace(/[^\d\.]/g,'')" style="width: 130px;" />
								<span>&nbsp;*</span>
							</td>
							<td align="right" width="100px">使用单位</td>
							<td width="100px">
								<input type="text" name="organization" maxlength="20" value="<%=businesshall.getOrganization() %>" style="width: 130px;" />
							</td>
							<td align="right" width="100px">管理部门</td>
							<td width="100px">
								<input type="text" name="manageDepartment" maxlength="20" value="<%=businesshall.getManageDepartment() %>" style="width: 130px;" />
							</td>
							<td align="right" width="100px">房产证号</td>
							<td width="100px">
								<input type="text" name="certificateNumber" maxlength="20" value="<%=businesshall.getCertificateNumber() %>" style="width: 130px;" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">房屋价值</td>
							<td width="100px">
								<input type="text" name="houseValue" value="<%=businesshall.getHouseValue() %>" style="width: 130px;" />
							</td>
							<td align="right" width="100px">共享属性</td>
							<td width="100px">
								<input type="text" name="sharedProperty" value="<%=businesshall.getSharedProperty() %>" style="width: 130px;" />
							</td>
							<td align="right" width="100px">共享单位</td>
							<td width="100px">
								<input type="text" name="sharedOrganization" value="<%=businesshall.getSharedOrganization() %>" style="width: 130px;" />
							</td>
							<td align="right" width="100px">地址</td>
							<td width="100px">
								<input type="text" name="address" value="<%=businesshall.getAddress() %>" style="width: 130px;" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">电流</td>
							<td width="100px">
								<input type="text" name="electricCurrent" value="<%=businesshall.getElectricCurrent() %>" style="width: 130px;" />
							</td>
							<td align="right" width="100px">环境</td>
							<td width="100px">
								<input type="text" name="environment" maxlength="20" value="<%=businesshall.getEnvironment() %>" style="width: 130px;" />
							</td>
						
						</tr>
						<tr>
						    <td align="right" colspan="8" height="60px">
								<input name="baocun" type="button" class="btn_c1" onclick="saveBusinesshall()" id="baocun" value="保存" />&nbsp; 
								<input name="resetBtn" type="button" class="btn_c1" id="resetBtn" value="重置" />&nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td></tr></table>
		</form>
</body>
<script language="javascript">
    /**临时****下拉框选中*/
	document.form1.entityType.value="<%=businesshall.getEntityType() %>";
	document.form1.entitySmallType.value="<%=businesshall.getEntitySmallType() %>";
	document.form1.practicalUse.value="<%=businesshall.getPracticalUse() %>";
	document.form1.ownership.value="<%=businesshall.getOwnership() %>";
	document.form1.rightOfUse.value="<%=businesshall.getRightOfUse() %>";
	document.form1.buildingStructure.value="<%=businesshall.getBuildingStructure() %>";
</script>
</html>
