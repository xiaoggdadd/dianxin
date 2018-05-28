<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%
	String path = request.getContextPath();
	String sheng = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	Account account=(Account)session.getAttribute("account");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=path%>/javascript/PopupCalendar.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
	<script src="<%=path%>/javascript/tx.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	

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
	
	function select(){
        	$("#radioDiv").empty();
        	var flowId=$("#flowId").val();
        	
        	var agcode= document.form1.xian.value;
        	$.ajax({
	                type: "POST",//请求方式
	                url: path+"/servlet/workflow?action=getActionName",
	                data: {flowId:flowId},
	                dataType: "json",success: function(result){
				    	$("#nextActionName").html(result);
				    	
				    	$.ajax({
	                type: "POST",//请求方式
	                url: path+"/servlet/workflow?action=getAuditor",
	                data: {flowId:flowId,agcode:agcode},
	                dataType: "json",success: function(result){
				    	var  resultArr = eval(result);
				    	var html = "选择执行人：";
				    		if(resultArr!=null && resultArr.length>0){
				    			var scountArr = resultArr[0];
				    			var accountid="",name="";
				    			for (var i = scountArr; i < resultArr.length - 1; i += scountArr) {
															accountid = resultArr[i
																	+ resultArr.indexOf("ACCOUNTID")];
															name =  resultArr[i
																	+ resultArr.indexOf("NAME")];
															html+="<label><input type='radio' name='auditorid' value='"+accountid+"' checked/>"+name+"</label>";
				    		}
				    		
				    	}
				    	$("#radioDiv").append(html);
				    	$("#tongyiTr").attr("style", "display:display;");
				    }
	            });
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
	
	function saveStation() {
		var flowId=$("#flowId").val();
		var auditorid=$('input:radio[name="auditorid"]:checked').val();
		if(!(checkNotSelected(document.form1.shi, "地市") 
				&& checkNotSelected(document.form1.xian, "区县")
				&& checkNotSelected(document.form1.xiaoqu, "乡镇")
				&& checkNotnull(document.form1.jzName, "实体名称")
				&& checkNotSelected(document.form1.stationType, "实体类型")
				&& checkNotnull(document.form1.ascriptionUnit, "所归属经营单位")
				&& checkNotSelected(document.form1.power, "供电方式")
				//&& checkNotSelected(document.form1.flowId, "流程")
				)){

			return;
		}
		if(confirm("您将要添加新实体的信息！确认信息正确！")){
            document.form1.action=path+"/servlet/stationServlet?action=save&flowId="+flowId+"&auditorid="+auditorid;
        	document.form1.submit()
        }
	}
	function changeSheng(){
		var sid = document.form1.sheng.value;

		if(sid=="0"){
			var shilist = document.all.shi;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			return;
		}else{
		 //alert("11111");
			sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

		}
	}
	function updateShi(res){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		for(var i = 0;i<res.length;i+=2){
			shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
		}
	}
	function changeCity(){
		var sid = document.form1.shi.value;
		
		if(sid=="0"){
			var shilist = document.all.xian;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			return;
		}else{
			sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
		}
	}
	function updateQx(res){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		
		for(var i = 0;i<res.length;i+=2){
			shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
		}
	}

	function changeCountry(){
		var sid = document.form1.xian.value;
		if(sid=="0"){
			var shilist = document.all.xiaoqu;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			return;
		}else{
			sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
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
<jsp:useBean id="scode" scope="page" class="com.ptac.app.util.Code"></jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="flowBean" scope="page" class="com.noki.mobi.flow.javabean.FlowBean">
    </jsp:useBean>
</head>
<body>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">实体新增</div>
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
								<select name="shi" style="width: 130px" onchange="changeCity()">
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
								 <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">区县</td>
							<td width="100px">
								<select name="xian" style="width: 130px" onchange="changeCountry()">
									<option value="0">请选择</option>
								</select>
								 <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">乡镇</td>
							<td width="100px">
								<select name="xiaoqu" style="width: 130px">
									<option value="0">请选择</option>
								</select>
								 <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">实体名称</td>
							<td>
							   <input type="text" name="jzName" maxlength="20" style="width: 130px;"/>
							    <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
						</tr>
						<tr>
						   <td align="right" width="100px">实体类型</td>
							<td width="100px">
								<select name="stationType" style="width: 130px;">
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
								 <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
						    <td align="right" width="100px">所归属经营单元</td>
							<td width="100px">
								<input type="text" name="ascriptionUnit" maxlength="20" style="width: 130px;"/>
							 <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">是否共享外租</td>
							<td width="100px">
							   <input type="radio" name="isSharingRent" value="0" checked="checked"/>否
							   <input type="radio" name="isSharingRent" value="1"/>是
							</td>
							<td align="right" width="100px">供电方式</td>
							<td width="100px">
								<select name="power" style="width: 130px;">
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
								 <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">是否有空调</td>
							<td width="100px">
							  <input type="radio" name="kongtiao" value="0"/>否 
							  <input type="radio" name="kongtiao" value="1" checked="checked"/>是
							</td>
						</tr>
						<%-- 
						<tr bgcolor="F9F9F9">
										<td height="19" colspan="4">
											<img src="../../images/v.gif" width="15" height="15" />
											流程信息
										</td>
						</tr>
						<tr>
										<td align="right" width="100px">
											选择流程
										</td>
										<td>
											<select name="flowId" id="flowId" onchange="select()">
												<option value="0">
													请选择
												</option>
												<%
												ArrayList flowlist = new ArrayList();
												flowlist = flowBean.getAllFlow();
											    String flowName = "", flowId = "0";
												if (flowlist != null) {
													int countColum = ((Integer) flowlist.get(0)).intValue();
													for (int i = countColum; i < flowlist.size() - 1; i += countColum) {
														flowId = (String) flowlist.get(i + flowlist.indexOf("FLOWID"));
														flowName = (String) flowlist.get(i
																	+ flowlist.indexOf("FLOWNAME"));
												%>
												<option value="<%=flowId%>"><%=flowName%></option>

												<%
													}
													}
												%>

											</select><span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									</tr>
									<tr id="tongyiTr" style="display: none">
										<td colspan="8">
											<div>
												下一节点：<span id="nextActionName"></span></div>
											<div id="radioDiv">
											</div>
										</td>
									</tr>--%>
						<tr>
						    <td align="right" colspan="8" height="60px">
								<input name="baocun" type="button" class="btn_c1" onclick="saveStation()" id="baocun" value="保存" />&nbsp; 
								<input name="resetBtn" type="button" class="btn_c1" id="resetBtn" value="重置" />&nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td></tr></table>
		</form>
</body>

</html>
