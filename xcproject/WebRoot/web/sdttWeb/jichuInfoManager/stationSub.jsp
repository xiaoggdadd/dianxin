<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@page import="com.ptac.app.station.bean.*,com.ptac.app.station.dao.*"%>
<%
	String path = request.getContextPath();
	String sheng = (String)session.getAttribute("accountSheng");
	String xian = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	Account account=(Account)session.getAttribute("account");
	String rolename = account.getRoleName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=path%>/javascript/PopupCalendar.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
	<script src="<%=path%>/javascript/tx.js"></script>


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
		function selectFlow(){
		    var flowid = $("#flowName").val();
		     var ydsx1 = $("#stationType").val();
		  //alert("sss"+ydsx1);
		    console.log(flowid);
		    $.ajax({
	                type: "POST",//请求方式
	                url: path+"/servlet/workflow?action=selflow2",
	                data: {flowid:flowid},
	                dataType: "json",
	                success: function(data){
	                        var acount = data[0];
	                        //[5,"ACTIONID","ACTIONNAME","FLOWID","FLOWNAME","ROLEID",
						var flowName = "", flowId = "0", actionId = "", actionName = "", roleId = "";
						if(data.length > acount + 1){
							actionId = data[acount + data.indexOf("ACTIONID")];
							actionName = data[acount + data.indexOf("ACTIONNAME")];
							flowId = data[acount + data.indexOf("FLOWID")];
							flowName = data[acount + data.indexOf("FLOWNAME")];
							roleId = data[acount + data.indexOf("ROLEID")];
						}
						$("#flowId").val(flowId);
						$("#actionName").html(actionName);
						$("#actionId").val(actionId);
						var xian = $("#XIANCODE").val();
						getAuditors(xian,roleId,ydsx1);
	                }
	           });
		}
		 function getAuditors(xian, roleId,ydsx1){
           	var agcode= document.form1.xian.value;

			$
					.ajax({
						type : "POST",//请求方式
						url : path + "/servlet/workflow?action=bangongSela",
						data : {
							agcode : agcode,
							ydsx1 : ydsx1,
							roleId : roleId
						},
						dataType : "json",
						success : function(result) {
							var scountArr = result[0];
							var accountid = "", name = "";
							var auditors = "";
							for ( var i = scountArr; i < result.length - 1; i += scountArr) {
								accountid = result[i
										+ result.indexOf("ACCOUNTID")];
								name = result[i + result.indexOf("NAME")];
								auditors += "<label><input type='radio' name='auditorid' value='"+accountid+"' checked/>"
										+ name + "</label>";
							}
							$("#radioDiv").html(auditors);
						}
					});
		}
	
	function ShowHideSearchRegion(trObject, SelfObject) {
		if (trObject.style.display == "none") {
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"
	
		} else {
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
	}
	
    $(function(){
		
		$("#resetBtn").click(function(){
			$.each($("form input[type='text']"),function(){
				$(this).val("");
			})
		});
		$("#cancelBtn").click(function(){
				window.close();
			});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link href="../css/content.css" rel="stylesheet" type="text/css" />
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="station" scope="page" class="com.ptac.app.station.bean.Station"></jsp:useBean>
<jsp:useBean id="flowBean" scope="page" class="com.noki.mobi.flow.javabean.FlowBean">
    </jsp:useBean>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	
</head>
<%
String id = request.getParameter("id");
if(id !=null && !id.isEmpty()){
    StationDao dao = new StationDaoImpl();
    station = dao.getOne(Integer.parseInt(id));
}
String approvestatus = request.getParameter("approvestatus");
%>
<body>
<form action="" name="form1" method="post">
<input type="hidden" name="id" value="<%=station.getId()%>"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">实体提交</div>
				<div class="content01_1">
					<table width="1000px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr bgcolor="F9F9F9">
										<td height="19" colspan="4">
											<img src="../../images/v.gif" width="15" height="15" />
										          实体信息
										</td>
						</tr>
						<tr>
							<td align="right" width="100px">地市</td>
							<td width="100px">
								<select name="shi" style="width: 130px" onchange="changeCity()" disabled="disabled">
									<option value="0">请选择</option>
									<%
				         			ArrayList shilist = new ArrayList();
				         			shilist = commBean.getAgcode(sheng, "0", loginName);
				         			if (shilist != null) {
				         				String agcode = "", agname = "";
				         				int scount = ((Integer) shilist.get(0)).intValue();
				         				for (int i = scount; i < shilist.size() - 1; i += scount) {
					         			 	agcode = (String) shilist.get(i + shilist.indexOf("AGCODE"));
					         				agname = (String) shilist.get(i + shilist.indexOf("AGNAME"));
				         			%>
				                    	<option value="<%=agcode%>"><%=agname%></option>
				                    <%
				                    	}
				                    }
				                    %>
								</select>
							</td>
							<td align="right" width="100px">区县</td>
							<td width="100px">
								<select name="xian" style="width: 130px" onchange="changeCountry()" disabled="disabled">
									<option value="0">请选择</option>
									<%
					         			ArrayList xianlist = new ArrayList();
										xianlist = commBean.getAgcode(station.getShi(), station.getXian(), loginName);
					         			if (xianlist != null) {
					         				String agcode = "", agname = "";
					         				int scount = ((Integer) xianlist.get(0)).intValue();
					         				for (int i = scount; i < xianlist.size() - 1; i += scount) {
						         			 	agcode = (String) xianlist.get(i + xianlist.indexOf("AGCODE"));
						         				agname = (String) xianlist.get(i + xianlist.indexOf("AGNAME"));
				         			%>
				                    	<option value="<%=agcode%>"><%=agname%></option>
				                    <%
				                    	}
				                    }
				                    %>
								</select>
							</td>
							<td align="right" width="100px">乡镇</td>
							<td width="100px">
								<select name="xiaoqu" style="width: 130px" disabled="disabled">
									<option value="0">请选择</option>
									<%
					         			ArrayList xiaoqulist = new ArrayList();
										xiaoqulist = commBean.getAgcode(station.getXian(), station.getXiaoqu(), loginName);
					         			if (xiaoqulist != null) {
					         				String agcode = "", agname = "";
					         				int scount = ((Integer) xiaoqulist.get(0)).intValue();
					         				for (int i = scount; i < xiaoqulist.size() - 1; i += scount) {
						         			 	agcode = (String) xiaoqulist.get(i + xiaoqulist.indexOf("AGCODE"));
						         				agname = (String) xiaoqulist.get(i + xiaoqulist.indexOf("AGNAME"));
				         			%>
				                    	<option value="<%=agcode%>"><%=agname%></option>
				                    <%
				                    	}
				                    }
				                    %>
								</select>
							</td>
							<td align="right" width="100px">实体名称</td>
							<td>
							   <input type="text" name="jzName" value="<%=station.getJzName() %>" maxlength="20" style="width: 130px;" disabled="disabled"/>
							</td>
						</tr>
						<tr>
						<td align="right" width="100px">实体类型</td>
							<td width="100px">
								<select name="stationType" id="stationType" style="width: 130px;" disabled="disabled">
									<option value="0">请选择</option>
									<%
									ArrayList etlist = commBean.getSelctOptions("substationtype");
									if(etlist !=null){
										String code = "", name="";
										int gcount = ((Integer)etlist.get(0)).intValue();
										for(int i=gcount; i<etlist.size()-1; i+=gcount){
											code = (String)etlist.get(i + etlist.indexOf("CODE"));
											name = (String)etlist.get(i + etlist.indexOf("NAME"));
									%>
									  <option value="<%=code %>"><%=name %></option>
									<% 
										}
									}
									%>
								</select>
							</td>
						    <td align="right" width="100px">所归属经营单元</td>
							<td width="100px">
								<input type="text" name="ascriptionUnit" value="<%=station.getAscriptionUnit() %>" maxlength="20" style="width: 130px;" disabled="disabled"/>
							</td>
							<td align="right" width="100px">是否共享外租</td>
							<td width="100px">
							   <input type="radio" name="isSharingRent" value="0" checked="checked" disabled="disabled"/>否
							   <input type="radio" name="isSharingRent" value="1" disabled="disabled"/>是
							</td>
							<td align="right" width="100px">供电方式</td>
							<td width="100px">
								<select name="power" style="width: 130px;" disabled="disabled">
									<option value="0">请选择</option>
									<%
									ArrayList gdfslist = commBean.getSelctOptions("GDFS");
									if(gdfslist !=null){
										String code = "", name="";
										int gcount = ((Integer)gdfslist.get(0)).intValue();
										for(int i=gcount; i<gdfslist.size()-1; i+=gcount){
											code = (String)gdfslist.get(i + gdfslist.indexOf("CODE"));
											name = (String)gdfslist.get(i + gdfslist.indexOf("NAME"));
									%>
									  <option value="<%=code %>"><%=name %></option>
									<% 
										}
									}
									%>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">是否有空调</td>
							<td width="100px">
							  <input type="radio" name="kongtiao" value="0" disabled="disabled"/>否 
							  <input type="radio" name="kongtiao" value="1" checked="checked" disabled="disabled"/>是
							</td>
						</tr>
						<tr bgcolor="F9F9F9">
										<td height="19" colspan="4">
											<img src="../../images/v.gif" width="15" height="15" />
											流程信息
										</td>
						</tr>
						<tr>
									<td align="right" width="100px">流程名称</td>
									<td width="100px" colspan="8">
										<%--   <input list="itemlist" type="text" name="flowName" value="<%=flowName%>" style="box-sizing: border-box; width: 130px"/>&nbsp; --%>
										<select name="flowName" id="flowName" onchange="selectFlow()">
											<option value="0">请选择</option>
											<%
											ArrayList flowlist = new ArrayList();
											System.out.println("根据fff："+rolename);
												if(rolename.contains("市")) {//step2
													flowlist = flowBean.getAllFlows("2","%市%");
													
													} else if(rolename.contains("县")){//其他
														flowlist = flowBean.getAllFlows2("2","%市%");
														
													} else{//其他
														flowlist = flowBean.getAllFlowsfujian("2");
														
													}
											
												String flowName = "", flowId = "0";
												if (flowlist.size()!=0) {
													int countColum = ((Integer) flowlist.get(0)).intValue();
													for (int i = countColum; i < flowlist.size() - 1; i += countColum) {
														flowId = (String) flowlist.get(i
																+ flowlist.indexOf("FLOWID"));
														flowName = (String) flowlist.get(i
																+ flowlist.indexOf("FLOWNAME"));
											%>
											<option value="<%=flowId%>"><%=flowName%></option>

											<%
												}
												}
											%>

									</select> <span style="color: #FF0000; font-weight: bold">*</span> <input
										type="hidden" name="flowId" id="flowId" /></td>
								</tr>
								<tr>
									<td align="right" width="100px">下一节点</td>
									<td width="100px" colspan="8"><span id="actionName"></span>
										<input type="hidden" name="actionId" id="actionId" />
									</td>
								</tr>
								<tr>
									<td align="right" width="100px">执行人</td>
									<td width="100px" colspan="8">
										<div id="radioDiv"></div></td>
								</tr>
								<tr>
						    <td align="right" colspan="8" height="60px">
								<input name="baocun" type="button" class="btn_c1" onclick="saveStation()" id="baocun" value="提交" />&nbsp; 
								<input onclick="fanhui()" type="button" class="btn_c1" id="button2" value="返回" />&nbsp; &nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td></tr></table>
		</form>
</body>
<script language="javascript">
function saveStation() {
		var flowId=$("#flowId").val();
		var auditorid=$('input:radio[name="auditorid"]:checked').val();
		if(!(checkNotSelected(document.form1.shi, "地市") 
				&& checkNotSelected(document.form1.xian, "区县")
				&& checkNotSelected(document.form1.xiaoqu, "乡镇")
				&& checkNotnull(document.form1.jzName, "实体名称")
				&& checkNotSelected(document.form1.stationType, "实体类型")
				&& checkNotnull(document.form1.ascriptionUnit, "所归属经营单位")
				&& checkNotSelected(document.form1.power, "供电方式")//end 添加流程名称不为空判断
				&& checkNotSelected(document.form1.flowName, "流程名称"))){
			return;
		}
				
		   		//alert("JJJJJ"+auditorid);
		      	if(auditorid == "" || auditorid == null || auditorid == undefined || auditorid == 0){ // "",null,undefined
	    		   				alert("执行人不能为空!");
		   		 return false;
		   						}
		if(confirm("您将要提交新实体的信息！确认信息正确！")){
            document.form1.action=path+"/servlet/stationServlet?action=sub&flowId="+flowId+"&auditorid="+auditorid+"&taskId="+'<%=id%>';
        	document.form1.submit()
        }
	}
	function fanhui(){
		window.close();
	}
	/**临时****下拉框选中*/
    document.form1.shi.value='<%=station.getShi()%>';
    document.form1.xian.value='<%=station.getXian()%>';
    document.form1.xiaoqu.value='<%=station.getXiaoqu()%>';
    document.form1.power.value='<%=station.getPower()%>';
    document.form1.stationType.value='<%=station.getStationType()%>';
    document.form1.isSharingRent.value='<%=station.getIsSharingRent()%>';
    document.form1.kongtiao.value='<%=station.getKongtiao()%>';
</script>
</html>
