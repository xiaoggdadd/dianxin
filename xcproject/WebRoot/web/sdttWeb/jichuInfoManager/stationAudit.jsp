<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@page import="com.ptac.app.station.bean.*,com.ptac.app.station.dao.*"%>
<%@page import="com.noki.database.DataBase"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	String path = request.getContextPath();
	String sheng = (String)session.getAttribute("accountSheng");
	String xian = (String)session.getAttribute("accountSheng");
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
		if(!(checkNotSelected(document.form1.shi, "地市") 
				&& checkNotSelected(document.form1.xian, "区县")
				&& checkNotSelected(document.form1.xiaoqu, "乡镇")
				&& checkNotnull(document.form1.jzName, "实体名称")
				&& checkNotSelected(document.form1.stationType, "实体类型")
				&& checkNotnull(document.form1.ascriptionUnit, "所归属经营单位")
				&& checkNotSelected(document.form1.power, "供电方式"))){
			return;
		}
		if(confirm("您将要添加新实体的信息！确认信息正确！")){
            document.form1.action=path+"/servlet/stationServlet?action=save"
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
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="station" scope="page" class="com.ptac.app.station.bean.Station"></jsp:useBean>
<jsp:useBean id="bean" scope="page" class="com.noki.mobi.workflow.javabean.WorkFlowBean"></jsp:useBean>
<jsp:useBean id="flowBean" scope="page"
		class="com.noki.mobi.flow.javabean.FlowBean">
	</jsp:useBean>
</head>
<%
String id = request.getParameter("id");
//流程信息
	DataBase db = new DataBase();
	String nextaction = "", actionname = "", nextactionId = "", workFlowId = "", currentid = "", flowId = "", applyUserId = "", lastaction = "",lastactionname="", bohui = "",tasktype="",auditorid="",bo="";
	String steporderno="", nextsteporderno="", laststeporderno="";//配置角色根据ID
	String ACCTIME="",ACCNAME="",taskid="",dblc_type = "",ACCTIMEStr="";
	StringBuffer sql2 = new StringBuffer();
	ResultSet rs2 = null;
	sql2.append("select w.* ,ai.action_name as actionname,ai2.action_name as nextactionname,ai3.action_name as lastactionname,f.flow_name as flowname,"
					+"s.step_order_no as steporderno,s2.step_order_no as nextsteporderno,s3.step_order_no as laststeporderno,"
	 				+" (select  a.name from account a where a.accountid =w.APPLYUSER_ID) as ACCNAME from s_workflow w "
					+ "left join S_STEP_INFO s on w.current_no = s.step_id "
					+ "left join S_ACTION_INFO ai on ai.action_id =s.action_id "
					+ "left join S_STEP_INFO s2 on w.NEXT_NO = s2.step_id "
					+ "left join S_ACTION_INFO ai2 on ai2.action_id =s2.action_id "
					+ "left join S_STEP_INFO s3 on w.LAST_NO = s3.step_id "
					+ "left join S_ACTION_INFO ai3 on ai3.action_id =s3.action_id "
					+ "left join S_FLOW_INFO f on f.flow_id =s.flow_id "
					+ "where w.ID =" + id);
	System.out.println(sql2);
	db.connectDb();
	rs2 = db.queryAll(sql2.toString());
	while (rs2.next()) {
		taskid = rs2.getString("TASK_ID") == null ? "" : rs2.getString("TASK_ID");
		actionname = rs2.getString("actionname") == null ? "" : rs2
				.getString("actionname");
		nextaction = rs2.getString("nextactionname") == null ? "流程结束"
				: rs2.getString("nextactionname");
		lastactionname = rs2.getString("lastactionname") == null ? "发起人"
				: rs2.getString("lastactionname");
		steporderno = rs2.getString("STEPORDERNO") == null ? "" : rs2
				.getString("STEPORDERNO");
		nextsteporderno = rs2.getString("NEXTSTEPORDERNO") == null ? ""
				: rs2.getString("NEXTSTEPORDERNO");
		laststeporderno = rs2.getString("LASTSTEPORDERNO") == null ? ""
				: rs2.getString("LASTSTEPORDERNO");
		nextactionId = rs2.getString("NEXT_NO") == null ? "" : rs2
				.getString("NEXT_NO");
		currentid = rs2.getString("CURRENT_NO") == null ? "" : rs2
				.getString("CURRENT_NO");
		flowId = rs2.getString("FLOW_ID") == null ? "" : rs2
				.getString("FLOW_ID");
		applyUserId = rs2.getString("APPLYUSER_ID") == null ? "" : rs2
				.getString("APPLYUSER_ID");
		lastaction = rs2.getString("LAST_NO") == null ? "" : rs2
				.getString("LAST_NO");
		bohui = rs2.getString("BOHUI") == null ? "" : rs2
				.getString("BOHUI");
		tasktype = rs2.getString("TASK_TYPE") == null ? "" : rs2
				.getString("TASK_TYPE");
		auditorid = rs2.getString("AUDITORID") == null ? "" : rs2
				.getString("AUDITORID");
		dblc_type = rs2.getString("dblc_type") == null ? "" : rs2
				.getString("dblc_type");
		if(applyUserId.equals(auditorid)){
			bo="1";
		}
		ACCTIME=rs2.getString("APPLYTIME")==null?"":rs2.getString("APPLYTIME");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		java.util.Date ACCTIMEDate = sdf.parse(ACCTIME);
		ACCTIMEStr = sdf.format(ACCTIMEDate);
 		ACCNAME=rs2.getString("ACCNAME")==null?"":rs2.getString("ACCNAME");

	}
	String bohuijd="";



    StationDao dao = new StationDaoImpl();
    station = dao.getOne(Integer.parseInt(taskid));

%>
<body>
<form action="" name="form1" method="post">
<input type="hidden" name="id" value="<%=station.getId()%>"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">实体审批</div>
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
								<select name="shi" style="box-sizing: border-box; width: 130px;" onchange="changeCity()"
								<%if(!bo.equals("1")){ %>
											disabled="disabled"
											<%} %>
								>
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
								<span >&nbsp;*</span>
							</td>
							<td align="right" width="100px">区县</td>
							<td width="100px">
								<select name="xian" style="box-sizing: border-box; width: 130px;" onchange="changeCountry()"
								<%if(!bo.equals("1")){ %>
											disabled="disabled"
											<%} %>
								>
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
								<span class="style1">&nbsp;*</span>
							</td>
							<td align="right" width="100px">乡镇<%=station.getXiaoqu() %></td>
							<td width="100px">
								<select name="xiaoqu" style="box-sizing: border-box; width: 130px;"
								<%if(!bo.equals("1")){ %>
											disabled="disabled"
											<%} %>
								>
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
								<span class="style1">&nbsp;*</span>
							</td>
							<td align="right" width="100px">实体名称</td>
							<td>
							   <input type="text" name="jzName" value="<%=station.getJzName() %>" maxlength="20" style="box-sizing: border-box; width: 130px;"
							   <%if(!bo.equals("1")){ %>
											disabled="disabled"
											<%} %>
							   />
							   <span class="style1">&nbsp;*</span>
							</td>
						</tr>
						<tr>
						<td align="right" width="100px">实体类型</td>
							<td width="100px">
								<select name="stationType" style="box-sizing: border-box; width: 130px;"
								<%if(!bo.equals("1")){ %>
											disabled="disabled"
											<%} %>
								>
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
								<span>&nbsp;*</span>
							</td>
						    <td align="right" width="100px">所归属经营单元</td>
							<td width="100px">
								<input type="text" name="ascriptionUnit" value="<%=station.getAscriptionUnit() %>" maxlength="20" style="box-sizing: border-box; width: 130px;"
								<%if(!bo.equals("1")){ %>
											disabled="disabled"
											<%} %>
								/>
								<span>&nbsp;*</span>
							</td>
							<td align="right" width="100px">是否共享外租</td>
							<td width="100px">
							   <input type="radio" name="isSharingRent" value="0" checked="checked"
							   <%if(!bo.equals("1")){ %>
											disabled="disabled"
											<%} %>
							   />否
							   <input type="radio" name="isSharingRent" value="1"
							   <%if(!bo.equals("1")){ %>
											disabled="disabled"
											<%} %>
							   />是
							</td>
							<td align="right" width="100px">供电方式</td>
							<td width="100px">
								<select name="power" style="box-sizing: border-box; width: 130px;"
								<%if(!bo.equals("1")){ %>
											disabled="disabled"
											<%} %>
								>
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
								<span>&nbsp;*</span>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">是否有空调</td>
							<td width="100px">
							  <input type="radio" name="kongtiao" value="0"
							  <%if(!bo.equals("1")){ %>
											disabled="disabled"
											<%} %>
							  />否 
							  <input type="radio" name="kongtiao" value="1" checked="checked"
							  <%if(!bo.equals("1")){ %>
											disabled="disabled"
											<%} %>
							  />是
							</td>
						</tr>
						<tr>
										<td height="19" colspan="8">
											<img src="../../images/v.gif" width="15" height="15" />
											申请/审核信息
										</td>
									</tr>
									<%//if(!bo.equals("1")){ %>
									<tr>
										<td align="right" width="100px">
											报送人
										</td>
										<td>
											<input type="text" name="ACCNAME" id="ACCNAME"
												disabled="disabled" value="<%=ACCNAME%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										<td align="right" width="100px">
											报送时间
										</td>
										<td>
											<input type="text" name="ACCTIME" id="ACCTIME"
												disabled="disabled" value="<%=ACCTIMEStr%>"
												style="box-sizing: border-box; width: 130px" />
										</td>
										</tr>
										
										<tr>
											<td colspan="8">
												<div>
													审核意见：
												</div>
												<div>
													<textarea rows="5" cols="50" name="opinion"></textarea>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="8">
												<div>
													当前节点：<%=actionname%></div>
											</td>
										</tr>


										<tr>
											<td colspan="8">
												<div>
													<label>
														<input type="radio" name="shenhe" onchange="tongyi()"
															value="0" />
														同意
													</label>
													<label>
														<input type="radio" name="shenhe" onchange="tongyi()"
															value="1" />
														不同意
													</label>
												</div>
											</td>
										</tr>
										<%//} %>
										<tr id="tongyiTr" style="display: none">
											<td colspan="8">
												<%--<%if(nextaction.equals("流程结束")&&dblc_type.equals("4")){
													
												}else{ --%>
												<div>
													下一节点：<%=nextaction%></div>
												<div>
													执行人：
													<%
													if (nextactionId != null && !nextactionId.equals("")) {
														String agcode = "";
														if (nextaction.indexOf("市") != -1) {
															agcode = station.getShi();
														} else if (nextaction.indexOf("县") != -1) {
															agcode = station.getXian();
														}
													ArrayList acclist = new ArrayList();
													//if (nextsteporderno.equals("2")) {//step2
													if ("市公司电费管理员审核".equals(nextaction)) {//step2
													if (!station.getStationType().isEmpty() && station.getStationType().equals("StationType18")) {//办公
														acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "办公");
													} else if (!station.getStationType().isEmpty() && station.getStationType().equals("StationType19")) {//营业厅
														acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "营业厅");
													}  else {//生产
														acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "生产");
													}

												} //else if (nextsteporderno.equals("3")) {//step3
												 else if ("市级主任审核".equals(nextaction)) {//step3													 
												 	if (!station.getStationType().isEmpty() && station.getStationType().equals("StationType18")) {//办公
															acclist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(),agcode, "%市综合部主任%");
														} else if (!station.getStationType().isEmpty() && station.getStationType().equals("StationType19")) {//营业厅
															acclist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(),agcode, "%市销售部主任%");
														} 

														 else {//生产
															acclist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(),agcode, "%市运维主任%");
														}

					
													}else {
														acclist = bean.getAccountByActionId(nextactionId, taskid, agcode, tasktype);
													}
								
												if (acclist.size()!=0) {
													String accountId = "", name = "";
													int cscount = ((Integer) acclist.get(0)).intValue();
													for (int i = cscount; i < acclist.size() - 1; i += cscount) {
														accountId = (String) acclist.get(i + acclist.indexOf("ACCOUNTID"));
														name = (String) acclist.get(i + acclist.indexOf("NAME"));
										%>
												
													<label>
														<input type="radio" name="auditorid"
															value="<%=accountId%>" checked /><%=name%></label>
													<%
														}
															}
														}
													%>


												</div>
												<%--<%} --%>
											</td>
										</tr>
										<tr id="bohuiTr" style="display: none">
											<td colspan="8">
												<%
												  String action = actionname.toString();
												   System.out.println("---判断是否包含---"+action.contains("市电费管理员"));
													//if (steporderno.equals("2")) {//step2
														if ("市公司电费管理员审核".equals(nextaction)) {//step2
												%>
												<div>
													上一节点：发起人
												</div>
												<div>
													执行人：
													<%
													 bohuijd = "0";
													String accName = bean.getAuditorName(applyUserId, db);
												%>
												<label>
														<input type="radio" name="bohui" value="0" checked /><%=accName%></label>
												</div>
												<%
													} else {
												%>
												<div>
													上一节点：<%=lastactionname%>
												</div>
												<div>
													执行人：
													<%
													if (lastactionname.equals("发起人")) {
															bohuijd = "0";
															String accName = bean.getAuditorName(applyUserId, db);
												%>
													<label>
														<input type="radio" name="bohui" value="0" checked /><%=accName%></label>

													<%
														} else {
													%>
													<%
														String actionNo = bean.getStepNo(currentid, db);
																int stepNo = Integer.parseInt(actionNo);
																String nextNo = (stepNo - 1) + "";
																String nextStep = bean.getNextStep(flowId, nextNo);
																String acc = bean.getAuditor(id, nextStep, db);
																String accName = bean.getAuditorName(acc, db);
																bohuijd = nextStep;
													%><label>
														<input type="radio" name="bohui" value="<%=acc%>" checked /><%=accName%></label>

													<%
														}
													%>
												</div>
												<%
													}
												%>
											</td>
										</tr>
										<tr>
												<td colspan="8">
													<div>
														审批意见：
													</div>
													<div>
														<table>
															<tr align="center">
																<th width="10">
																	序号
																</th>
																<th>
																	审核人
																</th>
																<th>
																	审核时间
																</th>
																<th>
																	审核状态
																</th>
																<th>
																	审核意见
																</th>
															</tr>
															<%
																ArrayList li = new ArrayList();
																li = bean.getApproverByWorkFlowId(id);
																if (li != null) {
																	String accountname2 = "", checktime = "", state = "", opinion = "";
																	int num = 1;
																	int cscount = ((Integer) li.get(0)).intValue();
																	for (int i = cscount; i < li.size() - 1; i += cscount) {
																		accountname2 = (String) li.get(i
																				+ li.indexOf("ACCOUNTNAME"));
																		checktime = (String) li.get(i + li.indexOf("CHECKTIME"));
																		state = (String) li.get(i + li.indexOf("STATE"));
																		opinion = (String) li.get(i + li.indexOf("OPINION"));
																		if (opinion == null) {
																			opinion = "";
																		}
																		if (state.equals("0")) {
																			state = "同意";
																		} else if (state.equals("1")) {
																			state = "不同意";
																		}
															%>
															<tr align="center">
																<td width="10"><%=num++%></td>
																<td align="center"><%=accountname2%></td>
																<td align="center"><%=checktime%></td>
																<td align="center"><%=state%></td>
																<td align="left"><%=opinion%></td>
															</tr>
															<%
																}
																}
															%>
														</table>
													</div>
												</td>
											</tr>
											<tr>
												<td align="right" colspan="8" height="60px">
													<input onclick="tijiao('<%=taskid%>')" type="button"
														class="btn_c1" id="button2" value="提交" />
													&nbsp;
													<input onclick="javascript:window.close()" type="button" class="btn_c1"
														id="button2" value="取消" />
													&nbsp;
												</td>
											</tr>
					</table>
				</div>
			</div>
		</td></tr></table>
		<input type="hidden" name="currentid" value="<%=currentid%>" />
			<input type="hidden" name="nextactionId" value="<%=nextactionId%>" />
			<input type="hidden" name="workFlowId" value="<%=id%>" />
			<input type="hidden" name="flowId" value="<%=flowId%>" />
			<input type="hidden" name="applyUserId" value="<%=applyUserId%>" />
			<input type="hidden" name="lastaction" value="<%=lastaction%>" />
			<input type="hidden" name="tasktype" value="<%=tasktype%>" />
			<input type="hidden" name="bohuijd" value="<%=bohuijd%>" />
			<input type="hidden" name="dblc_type" value="<%=dblc_type%>" />
		
		</form>
</body>
<script language="javascript">
	/**临时****下拉框选中*/
    document.form1.shi.value='<%=station.getShi()%>';
    document.form1.xian.value='<%=station.getXian()%>';
    document.form1.xiaoqu.value='<%=station.getXiaoqu()%>';
    document.form1.power.value='<%=station.getPower()%>';
    document.form1.stationType.value='<%=station.getStationType()%>';
    document.form1.isSharingRent.value='<%=station.getIsSharingRent()%>';
    document.form1.kongtiao.value='<%=station.getKongtiao()%>';
  
    
    
    function tongyi() {
	var shenhe = $('input:radio[name="shenhe"]:checked').val();
	if (shenhe == 0) {

		$("#tongyiTr").attr("style", "display:display;");
		$("#bohuiTr").attr("style", "display:none;");
	} else {
		$("#tongyiTr").attr("style", "display:none;");
		$("#bohuiTr").attr("style", "display:display;");
	}
}
function tijiao(taskId) {
	var bh = '<%=bo%>';

	if (bh == 1) {
		if(!(checkNotSelected(document.form1.shi, "地市") 
				&& checkNotSelected(document.form1.xian, "区县")
				&& checkNotSelected(document.form1.xiaoqu, "乡镇")
				&& checkNotnull(document.form1.jzName, "实体名称")
				&& checkNotSelected(document.form1.stationType, "实体类型")
				&& checkNotnull(document.form1.ascriptionUnit, "所归属经营单位")
				&& checkNotSelected(document.form1.power, "供电方式"))){
			return;
		}
		if(confirm("您将要添加新实体的信息！确认信息正确！")){
           document.form1.action = path+ "/servlet/workflow?action=adjust&taskId=" + taskId;
		   document.form1.submit();
        }
		
	} else {
		var shenhe = $('input:radio[name="shenhe"]:checked').val();
		var opinion = document.form1.opinion.value;
		var nextactionId = $('input[name="nextactionId"]').val();

		if (typeof (shenhe) == "undefined") {
			alert("请选择审核状态！");
			return false;
		} else if (shenhe == 0) {
			var auditorid = $('input:radio[name="auditorid"]:checked').val();
			if (typeof (auditorid) == "undefined"
					&& !(nextactionId == null || nextactionId == "")) {
				alert("请选择执行人！");
				return false;
			}
		} else {
			var bohui = $('input:radio[name="bohui"]:checked').val();
			if (typeof (bohui) == "undefined") {
				alert("请选择驳回节点！");
				return false;
			}
			if (opinion == "") {
				alert("请输入审核意见！");
				return false;
			}

		}
			$("input[name='beilv_mo']").removeAttr("disabled");
			$("input[name='danjia_mo']").removeAttr("disabled");
			$("input[name='dbqyzt_mo']").removeAttr("disabled");
		document.form1.action = path
				+ "/servlet/workflow?action=complete&type=" + shenhe
				+ "&taskId=" + taskId;
		document.form1.submit();
	}

}

</script>
</html>
