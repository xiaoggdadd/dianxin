<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.Double"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="java.sql.ResultSet"%>
<%
	String path = request.getContextPath();
	String shengId = (String) session.getAttribute("accountSheng");
	String loginName = (String) session.getAttribute("loginName");
	Account account = (Account) session.getAttribute("account");
	String jzproperty = request.getParameter("jzproperty");//获取新站点的属性  
	String rolename = account.getRoleName();
	String accountname = account.getAccountName();
	SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd");
	String entrytime = mat.format(new Date());
	System.out.println("系统时间" + entrytime + "  shengId:" + shengId
			+ "  loginName:" + loginName + "  accountname:"
			+ accountname + " jzproperty:" + jzproperty);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增铁塔报账信息</title>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<link
	href="<%=path%>/javascript/jQuery-ComboSelect/css/combo.select.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>

<script type="text/javascript"
	src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>

<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript"
	src="<%=path%>/javascript/PopupCalendar.js"></script>
<script type="text/javascript"
	src="<%=path%>/javascript/PopupCalendar_ny.9js"></script>

<script type="text/javascript"
	src="<%=path%>/javascript/jQuery-ComboSelect/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/javascript/jQuery-ComboSelect/js/jquery.combo.select.js"></script>
<script type="text/javascript">
$(function() {
	$('#DIANBIAOID').comboSelect();
});
var path = '<%=path%>';
	
         
</script>
<!-- 设置不和规参数颜色 -->
<style type="text/css">
.red {
	color: red;
}

.black {
	color: black;
}
</style>
<!-- end -->
</head>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="diaobiaoBean" scope="page"
	class="com.noki.jizhan.DianBiaoBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page"
	class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<jsp:useBean id="flowBean" scope="page"
	class="com.noki.mobi.flow.javabean.FlowBean">
</jsp:useBean>
<body>
	<form action="" name="form1" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr valign="top">

				<td width="12"><img src="../images/space.gif" width="12"
					height="17" /></td>
				<td>
					<div class="content01">
						<div class="tit01">新增铁塔报账信息</div>
						<div class="content01_1">
							<table border="0" id="tableStr" cellpadding="0" cellspacing="0"
								class="tb_select" width="1218" height="507">
								<tr bgcolor="F9F9F9">
									<td height="19" colspan="8"><img src="../../images/v.gif"
										width="15" height="15" /> 电表信息</td>
								</tr>
								<tr>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>主电表</td>
									<td width="100px">
										<%--<select name="DIANBIAOID" id="DIANBIAOID" style="box-sizing:border-box;width:130px;" onchange="changeDianbiao()">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList dianbiaolist = new ArrayList();
					         		dianbiaolist = diaobiaoBean.getDianbiao(); 
						         	if(dianbiaolist!=null){
						         		String dbid_se="",dbname_se="";
						         		int scount_se = ((Integer)dianbiaolist.get(0)).intValue();
						         		for(int j=scount_se;j<dianbiaolist.size()-1;j+=scount_se)
					                    {
						         			dbid_se=(String)dianbiaolist.get(j+dianbiaolist.indexOf("ID"));
						         			
						         			dbname_se=(String)dianbiaolist.get(j+dianbiaolist.indexOf("DBNAME"));
					                    %>
					                    <option value="<%=dbid_se%>"><%=dbname_se%></option>
					                    <%}
						         	}
						         %>
					         	</select> --%> <input type="text" name="DIANBIAONAME"
										onclick="openMeter()"
										style="box-sizing:border-box;width:130px;" readonly="readonly" />
										<input type="hidden" name="DIANBIAOID" />
									</td>
									<td align="right" width="100px">所属局站</td>
									<td width=" 100px"><input type="text" name="ZDNAME"
										id="ZDNAME" disabled="disabled" value=""
										style="box-sizing: border-box; width: 130px" />
									</td>
									<td align="right" width="100px">所属地市</td>
									<td width="100px"><input type="text" name="SHI" id="SHI"
										disabled="disabled" value=""
										style="box-sizing: border-box; width: 130px" />
									</td>
									<td align="right" width="100px">所属县区</td>
									<td width="100px"><input type="text" name="XIAN" id="XIAN"
										disabled="disabled" value=""
										style="box-sizing: border-box; width: 130px" />
									</td>
								</tr>
								<tr>
									<td align="right" width="100px">供电方式</td>
									<td width="100px"><input type="text" name="FANGSHINAME"
										id="FANGSHINAME" disabled="disabled" value=""
										style="box-sizing: border-box; width: 130px" />
									</td>

								</tr>

								<!-- 新添手机端报账信息填写 -->

								<tr bgcolor="F9F9F9">
									<td height="19" colspan="8"><img src="../../images/v.gif"
										width="15" height="15" /> 手机抄表信息</td>
								</tr>
								<tr>

									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>手机抄表开始日期</td>
									<td><input type="text" onchange="changeSJ()"
										style="box-sizing:border-box;width:130px" class="Wdate"
										name="STARTTIME_C" id="STARTTIME_C"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
										value="" />
									</td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>手机抄表结束日期</td>
									<td onclick="endtime_c()"><input type="text"
										onchange="changeSJ()"
										style="box-sizing:border-box;width:130px" class="Wdate"
										name="ENDTIME_C" id="ENDTIME_C"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
										value="" />
									</td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>手机抄表上期读数</td>
									<td><input type="text" name="SQDS_C" id="SQDS_C"
										onchange="changeSJ()" value=""
										style="box-sizing:border-box;width:130px" title="必填项" /></td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>手机抄表本期读数</td>
									<td><input type="text" name="BQDS_C" id="BQDS_C" value=""
										onchange="changeSJ()"
										style="box-sizing:border-box;width:130px" title="必填项" /></td>
								</tr>
								<tr>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>手机抄表用电量</td>
									<td><input type="text" name="DIANLIANG_C" id="DIANLIANG_C"
										onchange="changeSJ()" disabled="disabled" value=""
										style="box-sizing:border-box;width:130px" /></td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>手机抄表用电天数</td>
									<td><input type="text" name="TIANSHU_C" id="TIANSHU_C"
										onchange="changeSJ()" disabled="disabled" value=""
										style="box-sizing:border-box;width:130px" /></td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>手机抄表日均用电量</td>
									<td><input type="text" name="RJYDL_C" id="RJYDL_C"
										disabled="disabled" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
								</tr>

								<!--手机端报账信息填写 end -->

								<tr bgcolor="F9F9F9">
									<td height="19" colspan="8"><img src="../../images/v.gif"
										width="15" height="15" /> 电费信息</td>
								</tr>
								<tr>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>开始日期</td>
									<td><input type="text" id="STARTTIME"
										onchange="changeSJ()"
										style="box-sizing:border-box;width:130px" class="Wdate"
										name="STARTTIME"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
										value="" />
									</td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold" o>*</span>结束日期</td>
									<!-- gcl 2018年3月17日  -->
									<td onclick="endtime('')"><input type="text"
										onchange="changeSJ()"
										style="box-sizing:border-box;width:130px" class="Wdate"
										name="ENDTIME" id="ENDTIME"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
										value="" />
									</td>
									<td align="right" width="100px">用电天数</td>
									<td><input type="text" name="TIANSHU"
										onchange="changeSJ()" disabled="disabled" id="TIANSHU"
										value="" style="box-sizing:border-box;width:130px" />
									</td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>税负因子(%)</td>
									<td><input type="text" name="SFYZ" onchange="changeSJ()"
										disabled="disabled" id="SFYZ" value=""
										style="box-sizing:border-box;width:130px" />
									</td>

								</tr>
								<tr>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>上期读数</td>
									<td><input type="text" name="SQDS" id="SQDS"
										onchange="changeSJ()" value=""
										style="box-sizing:border-box;width:130px" title="必填项" />
									</td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>本期读数</td>
									<td><input type="text" name="BQDS" id="BQDS" value=""
										onchange="changeSJ()"
										style="box-sizing:border-box;width:130px" title="必填项" />
									</td>
									<td align="right" width="100px">用电量</td>
									<td><input type="text" name="DIANLIANG" id="DIANLIANG"
										onchange="changeSJ()" disabled="disabled" value=""
										style="box-sizing:border-box;width:130px" />
									</td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>分摊比例(%)</td>
									<td><input type="text" name="FTBL" id="FTBL"
										onchange="changeSJ()" disabled="disabled" value=""
										style="box-sizing:border-box;width:130px" />
									</td>



								</tr>
								<tr>
									<td align="right" width="100px">上期单价</td>
									<td><input type="text" name="SQDJ" id="SQDJ" value=""
										style="box-sizing:border-box;width:130px" title="必填项" />
									</td>
									<td align="right" width="100px">单价</td>
									<td><input type="text" name="PRICE" disabled="disabled"
										id="PRICE" onchange="changeSJ()" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" />
									</td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>缴费金额</td>
									<td><input type="text" name="ALLMONEY" id="ALLMONEY"
										onchange="changeSJ()" oninput="shuie()" value=""
										style="box-sizing:border-box;width:130px" title="必填项" />
									</td>

									<td align="right" width="100px" id="diansunTd">其中电损</td>
									<td><input type="text" name="DIANSUN" id="DIANSUN"
										onchange="changeSJ()" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" />
									</td>
								</tr>
								<tr>

									<td align="right" width="100px">日均用电量</td>
									<td><input type="text" name="RJYDL" id="RJYDL"
										disabled="disabled" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" />
									</td>
									<td align="right" width="100px">上期日均用电量</td>
									<td><input type="text" name="SQRJYDL" disabled="disabled"
										id="SQRJYDL" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" />
									</td>

									<!-- 2018-03-09 gcl-添加页面税额  -->
									<td align="right" width="100px">税额</td>
									<td><input type="text" name="TAX" disabled="disabled"
										id="TAX" onchange="changeSJ()" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /> <input type="hidden" name="zzsl" id="zzsl"
										value="" />
									</td>
									<td align="right" width="100px">倍率</td>
									<td><input type="text" name="BEILV" disabled="disabled"
										id="BEILV" value="" style="box-sizing:border-box;width:130px" />
									</td>
								</tr>
								<tr>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>报账区间(月)</td>
									<td><input type="text" name="BZQJ"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
										id="BZQJ" value="" style="box-sizing:border-box;width:130px" />
									</td>
									<td align="right" width="100px">分摊金额</td>
									<td><input type="text" name="FTJE" disabled="disabled"
										id="FTJE" value="" style="box-sizing:border-box;width:130px" />
									</td>
									<td align="right" width="100px">分摊电量</td>
									<td><input type="text" name="FTDL" disabled="disabled"
										id="FTDL" value="" style="box-sizing:border-box;width:130px" />
									</td>
									<td align="right" width="100px">不含税金额</td>
									<td><input type="text" name="BHSJE" disabled="disabled"
										id="BHSJE" value="" style="box-sizing:border-box;width:130px" />
									</td>
								</tr>
								<tr bgcolor="F9F9F9">
									<td height="19" colspan="8"><img src="../../images/v.gif"
										width="15" height="15" /> 数据分析信息</td>
								</tr>
								<tr>

									<!-- 报账信息添加 2018/1/18 -->
									<td align="right" width="100px">电量偏离数</td>
									<td><input type="hidden" name="LiLunDianLiang"
										id="LiLunDianLiang" disabled="disabled" onchange="changeSJ()"
										value="" style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /> <input type="text"
										name="DianLiangPianLiShu" id="DianLiangPianLiShu"
										disabled="disabled" onchange="changeSJ()" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" />
									</td>
									<td align="right" width="100px">日期偏离数</td>
									<td><input type="text" name="RiQiPianLiShu"
										id="RiQiPianLiShu" disabled="disabled" onchange="changeSJ()"
										value="" style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" />
									</td>
									<!-- END -->
									<td align="right" width="100px">PUE值</td>
									<td><input type="text" name="PUEZHI" id="PUEZHI"
										disabled="disabled" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" />
									</td>
									<td align="right" width="100px">本月全市平均PUE值</td>
									<td><input type="text" name="AVGPUEZHI" id="AVGPUEZHI"
										disabled="disabled" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" />
									</td>
									<!-- END -->

								</tr>
								<tr>
									<td align="right" width="100px">电费标杆(度)</td>
									<td><input type="text" name="BGDL" id="BGDL"
										disabled="disabled" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" />
									</td>
									<td align="right" width="100px">标杆偏离率(%)</td>
									<td><input type="text" name="BGPLL" id="BGPLL"
										disabled="disabled" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" />
									</td>
									<td align="right" width="100px"><a id="showPUE" href="#"
										onclick="showPUE()">查看最近PUE曲线 </a>
									</td>
								</tr>
								<tr bgcolor="F9F9F9">
									<td height="19" colspan="4"><img src="../../images/v.gif"
										width="15" height="15" /> 流程信息</td>
								</tr>

								<%--
									ArrayList actionlist = new ArrayList();
									actionlist = flowBean.getAction("flowtype02", "1"); //报账审核 step1
									int acount = ((Integer) actionlist.get(0)).intValue();
									String flowName = "", flowId = "0", actionId = "", actionName = "", roleId = "";
									if (actionlist.size() > (acount + 1)) {
										flowId = (String) actionlist.get(acount
												+ actionlist.indexOf("FLOWID"));
										flowName = (String) actionlist.get(acount
												+ actionlist.indexOf("FLOWNAME"));
										actionId = (String) actionlist.get(acount
												+ actionlist.indexOf("ACTIONID"));
										actionName = (String) actionlist.get(acount
												+ actionlist.indexOf("ACTIONNAME"));
										roleId = (String) actionlist.get(acount
												+ actionlist.indexOf("ROLEID"));
									}
								--%>
								<tr>
									<td align="right" width="100px">流程名称</td>
									<td width="100px" colspan="8">
										<%--   <input list="itemlist" type="text" name="flowName" value="<%=flowName%>" style="box-sizing: border-box; width: 130px"/>&nbsp; --%>
										<select name="flowName" id="flowName" onchange="selectFlow()"
										title="提交为必选项">
											<option value="0">请选择</option>
											<%
												ArrayList flowlist = new ArrayList();
											System.out.println("根据fff："+rolename);
												if(rolename.contains("市")) {//step2
													flowlist = flowBean.getAllFlows("1","%市%");
													
													}  else if(rolename.contains("县")){//其他
														flowlist = flowBean.getAllFlows2("1","%市%");
														
													} else{//其他
														flowlist = flowBean.getAllFlowsfujian("1");
														
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
										type="hidden" name="flowId" id="flowId" />
									</td>
								</tr>
								<tr>
									<td align="right" width="100px">下一节点</td>
									<td width="100px" colspan="8"><span id="actionName"></span>
										<input type="hidden" name="actionId" id="actionId" /></td>
								</tr>
								<tr>
									<td align="right" width="100px">执行人</td>
									<td width="100px" colspan="8">
										<div id="radioDiv"></div>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="6" height="60px"><font
										style="color:#0099FF;">
											※普通表(非直供电):总金额-((截止度数-起始度数)*倍率*单价)=电损金额 </br>
											※电业表(直供电表):总金额/((截止度数-起始度数)*倍率+其中电损)=单价 </font>
									</td>
									<td align="right" colspan="2" height="60px"><input
										onclick="saveBz('1')" type="button" class="btn_c1"
										id="button2" value="临时保存" /> <%
 	//如果角色名包含抄表员说明是抄表员，隐藏提交按钮。
 	String action = rolename.toString();
 	System.out.println("---判断是否包含---" + action.contains("抄表员"));
 	if (action.contains("抄表员")) {
 %> <%
 	} else {
 %> <input onclick="saveBz('0')" type="button" class="btn_c1"
										id="button2" value="提交" /> <%
 	}
 %> <input type="hidden" value="" id="saveState" name="saveState" />
									</td>
								</tr>
							</table>
						</div>
					</div>
				</td>
			</tr>

		</table>
		<input type="hidden" value="" id="FANGSHI" /> <input type="hidden"
			value="" id="XIANCODE" />
			<input id="ydsxbm" name="ydsxbm" type="hidden"
											value="" />
	</form>
	<script type="text/javascript">
	var path = '<%=path%>';

		function saveBz(st) {
			$("#saveState").val(st);
			//点击按钮执行验证（st）代表状态  1是临时保存   0是提交
			if (
			//判断是否为空
			checkNotSelected(document.form1.DIANBIAOID, "主电表")
					&& checkNotnull(document.form1.STARTTIME, "开始日期")
					&& checkNotnull(document.form1.ENDTIME, "结束日期")
					&& checkNotnull(document.form1.SQDS, "上期读数")
					&& checkNotnull(document.form1.BQDS, "本期读数")
					&& checkNotnull(document.form1.SQDJ, "上期单价")
					&& checkNotnull(document.form1.PRICE, "单价")
					&& checkNotnull(document.form1.ALLMONEY, "电费金额")
					&& checkNotnull(document.form1.DIANSUN, "其中电损或电损金额") &&
					//新添手机验证
					checkNotnull(document.form1.STARTTIME_C, "手机抄表开始日期")
					&& checkNotnull(document.form1.ENDTIME_C, "手机抄表结束日期")
					&& checkNotnull(document.form1.SQDS_C, "手机抄表上期读数")
					&& checkNotnull(document.form1.BQDS_C, "手机抄表本期读数") &&

					checkNotnull(document.form1.TAX, "税额")
					&& checkNotnull(document.form1.DianLiangPianLiShu, "电量偏离数")
					&& checkNotnull(document.form1.RiQiPianLiShu, "日期偏离数")
			//&& checkNotSelected(document.form1.flowName, "流程名称")
			//end 添加流程名称不为空判断
			) {
				//新添手机验证  判断其他
				var beginTime_C = $("#STARTTIME_C").val(); //手机抄表开始实时间
				var endTime_C = $("#ENDTIME_C").val(); //手机抄表结束时间
				var SQDS_check_C = $("#SQDS_C").val(); //手机抄表上期读数	
				var BQDS_check_C = $("#BQDS_C").val(); //手机抄表本期读数

				var DianLiangPianLiShu_check = $("#DianLiangPianLiShu").val(); //电量偏离数
				var RiQiPianLiShu_check = $("#RiQiPianLiShu").val(); //日期偏离数
				//end
				var SQDS_check = $("#SQDS").val();

				var BQDS_check = $("#BQDS").val();
				var DIANLIANG_check = $("#DIANLIANG").val();
				var ALLMONEY_check = $("#ALLMONEY").val();
				var DIANSUN_check = $("#DIANSUN").val();
				var SQDJ_check = $("#SQDJ").val();
				var PRICE_check = $("#PRICE").val();

				//var S_time = $("#STARTTIME").val();
				var beginTime = $("#STARTTIME").val();
				var endTime = $("#ENDTIME").val();
				var arys1 = new Array();
				var arys2 = new Array();
				if (beginTime != null && endTime != null) {
					arys1 = beginTime.split('-');
					var sdate = new Date(arys1[0], parseInt(arys1[1] - 1),
							arys1[2]);
					arys2 = endTime.split('-');
					var edate = new Date(arys2[0], parseInt(arys2[1] - 1),
							arys2[2]);
					if (sdate > edate) {
						alert("结束日期不能小于开始日期！");
						return false;
					}
				}

				if (SQDS_check != null && SQDS_check != ""
						&& !ismoney(SQDS_check)) {
					alert("上期读数必须为数字,最多两位小数！");
					return false;
				}
				if (BQDS_check != null && BQDS_check != ""
						&& !ismoney(BQDS_check)) {
					alert("本期读数必须为数字,最多两位小数！");
					return false;
				}
				if (BQDS_check * 1 <= SQDS_check * 1) {
					alert("本期读数不能小于上期读数！");
					return false;
				}
				if (DIANLIANG_check != null && DIANLIANG_check != ""
						&& !ismoney(DIANLIANG_check)) {
					alert("电量必须为数字,最多两位小数！");
					return false;
				}
				if (ALLMONEY_check != null && ALLMONEY_check != ""
						&& !ismoney(ALLMONEY_check)) {
					alert("总金额必须为金额！");
					return false;
				}
				if (DIANSUN_check != null && DIANSUN_check != ""
						&& !ismoney(DIANSUN_check)) {
					alert("其中电损或者电损金额必须为数字,最多两位小数！");
					return false;
				}
				if (SQDJ_check != null && SQDJ_check != ""
						&& !ismoneys(SQDJ_check)) {
					alert("上期单价必须为金额！");
					return false;
				}
				if (PRICE_check != null && PRICE_check != ""
						&& !ismoneys(PRICE_check)) {
					alert("单价必须为金额！");
					return false;
				}
				//新添手机验证
				if (beginTime_C != null && endTime_C != null) {
					arys1 = beginTime_C.split('-');
					var sdate = new Date(arys1[0], parseInt(arys1[1] - 1),
							arys1[2]);
					arys2 = endTime_C.split('-');
					var edate = new Date(arys2[0], parseInt(arys2[1] - 1),
							arys2[2]);
					if (sdate > edate) {
						alert("手机抄表结束时间不能小于手机抄表开始时间！");
						return false;
					}
				}
				if (SQDS_check_C != null && SQDS_check_C != ""
						&& !ismoney(SQDS_check_C)) {
					alert("手机抄表上期读数必须为数字,最多两位小数！");
					return false;
				}
				if (BQDS_check_C != null && BQDS_check_C != ""
						&& !ismoney(BQDS_check_C)) {
					alert("手机抄表本期读数必须为数字,最多两位小数！");
					return false;
				}
				if (BQDS_check_C * 1 <= SQDS_check_C * 1) {
					alert("手机抄表本期读数不能小于手机抄表上期读数！");
					return false;
				}
				if (DianLiangPianLiShu_check == "") {
					alert("电量偏离数不能为空！");
					return false;
				}
				if (RiQiPianLiShu_check == "") {
					alert("日期偏离数不能为空！");
					return false;
				}
				///流程判断
           	 	if(st==0){
		   			//流程
		   		
		   			var flowName=document.form1.flowName.value;
		   			if(flowName == "" || flowName == null || flowName == undefined || flowName == 0){ // "",null,undefined
	    		   				alert("流程名称不能为空!");
		   			return false;
		   			}
		   		}
				//end  完成后提交
				addbaozhang();
			}
			;

		}
		function addbaozhang() {
			if (confirm("您将要添加报账！确认信息正确！")) {

				//新添手机验证
				$('#STARTTIME_C').attr("disabled", false); //手机抄表开始日期
				$('#SQDS_C').attr("disabled", false); //手机抄表上期读数
				$('#DIANLIANG_C').attr("disabled", false); //手机抄表用电量
				$('#TIANSHU_C').attr("disabled", false); //手机抄表用电天数
				$('#RJYDL_C').attr("disabled", false); //手机抄表日均用电量

				$('#DianLiangPianLiShu').attr("disabled", false);//电量偏离数
				$('#RiQiPianLiShu').attr("disabled", false); //日期偏离数
				//end

				$('#STARTTIME').attr("disabled", false);
				$('#SQDS').attr("disabled", false);
				$('#SQDJ').attr("disabled", false);
				$('#PRICE').attr("disabled", false);
				$('#DIANLIANG').attr("disabled", false);
				$('#ALLMONEY').attr("disabled", false);
				$('#TIANSHU').attr("disabled", false);
				$('#RJYDL').attr("disabled", false);
				$('#SQRJYDL').attr("disabled", false);
				$('#BEILV').attr("disabled", false);
				$('#DIANSUN').attr("disabled", false);
				$('#FTJE').attr("disabled", false);
				$('#FTDL').attr("disabled", false);
				$('#SFYZ').attr("disabled", false);
				$('#PUEZHI').attr("disabled", false);
				$('#FTBL').attr("disabled", false);
				$('#BZQJ').attr("disabled", false);
				$('#BGDL').attr("disabled", false);
				$('#BGPLL').attr("disabled", false);
				
				document.form1.action = path
						+ "/servlet/zhandian?action=addBaozhangtieta";
				document.form1.submit();
				showdiv("请稍等..............");
			}
		}

		function isNaN2(val) {
			return val.match(new RegExp("^[0-9]+$"));
		}
		function ismoney(str) {
			if (/^(([0-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/
					.test(str))
				return true;
			return false;
		}
		function ismoneys(str) {
			if (/^(([0-9][0-9]*)|(([0]\.\d{1,4}|[1-9][0-9]*\.\d{1,4})))$/
					.test(str))
				return true;
			return false;
		}
		function isNa(val) {
			return val.match(new RegExp("^\d+\.\d+$"));
		}
		function openMeter() {
			window
					.open(
							"../baozhang/ChooseDB.jsp",
							"newwindowSupp",
							"height=550, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
		}
		function changeDianbiao() {
			var dianbiaoId = document.form1.DIANBIAOID.value;

			if (dianbiaoId == "0") {
				//手机抄表获取数据添加
				$("#STARTTIME_C").val(""); //手机抄表开始时间
				$('#STARTTIME_C').attr("disabled", false); //手机抄表开始时间
				$("#SQDS_C").val(""); //手机抄表上期读数
				$('#SQDS_C').attr("disabled", false); //手机抄表上期读数
				//end
				$("#STARTTIME").val("");
				$('#STARTTIME').attr("disabled", false);
				$("#SQDS").val("");
				$('#SQDS').attr("disabled", false);
				$("#SQDJ").val("");
				$('#SQDJ').attr("disabled", false);
				$("#PRICE").val("");
				$('#PRICE').attr("disabled", false);
				$("#BEILV").val("");
				$('#ZDNAME').val("");
				$('#SHI').val("");
				$('#XIAN').val("");
				$("#SQRJYDL").val("");
				$('#FANGSHINAME').val("");
				$("#AVGPUEZHI").val("");

				return;
			} else {

				$
						.ajax({
							type : "POST",//请求方式
							url : path
									+ "/servlet/zhandian?action=getDianbiaoById",
							data : "dianbiaoId=" + dianbiaoId,
							dataType : "json",
							scriptCharset : 'utf-8',
							success : function(result) {
								if (result != null && result.length > 0) {
									var dianbiaoRe = result.split("*_*")[0];
									var baozhangRe = result.split("*_*")[1];
									var pueRe = result.split("*_*")[2];

									if (dianbiaoRe != null
											&& dianbiaoRe.length > 0) {
										var dianjia_dianbiao = dianbiaoRe
												.split("@_@")[0];
										var fangshi_dianbiao = dianbiaoRe
												.split("@_@")[1];
										var juzhan_dianbiao = dianbiaoRe
												.split("@_@")[2];
										var shi_dianbiao = dianbiaoRe
												.split("@_@")[3];
										var xian_dianbiao = dianbiaoRe
												.split("@_@")[4];
										var beilv_dianbiao = dianbiaoRe
												.split("@_@")[5];
										//2018-03-09 gcl 增值税率
										var zzsl_dianbiao = dianbiaoRe
												.split("@_@")[6];
										var fangshiname_dianbiao = dianbiaoRe
												.split("@_@")[7];
										var xian_code_dianbiao = dianbiaoRe
												.split("@_@")[8];
										//初始度数
										var CSDS_dianbiao = dianbiaoRe
												.split("@_@")[9];
										//初始日期
										var CSSYTIME_dianbiao = dianbiaoRe
												.split("@_@")[10];
										var dianbiao_ftbl = dianbiaoRe
												.split("@_@")[16];
										var dianbiao_sfyz = dianbiaoRe
												.split("@_@")[17];
 										  var ydsxbm = dianbiaoRe.split("@_@")[19];
				   						   $('#ydsxbm').val(ydsxbm);
										if (zzsl_dianbiao != ""
												&& zzsl_dianbiao != null
												&& zzsl_dianbiao != " ") {
											//alert(zzsl_dianbiao);
											$("#zzsl").val(zzsl_dianbiao);
										}
										if (fangshi_dianbiao == "gdfs05") {
											$('#diansunTd')
													.html(
															"<span style='color: #FF0000; font-weight: bold'>*</span>电损（度）");
											$('#DIANSUN').attr("disabled",
													false);
											$("#PRICE").val("");
											$("#FANGSHI").val("gdfs05");

										} else {
											$('#diansunTd').html("电损金额（元）");
											$('#DIANSUN')
													.attr("disabled", true);
											$("#PRICE").val(dianjia_dianbiao);
											$("#FANGSHI").val("");
										}

										if (dianjia_dianbiao != null
												&& dianjia_dianbiao != "") {
											$('#PRICE').attr("disabled", true);
										} else {
											$('#PRICE').attr("disabled", false);
										}
										if (fangshiname_dianbiao != null
												&& fangshiname_dianbiao != "") {
											$('#FANGSHINAME').val(
													fangshiname_dianbiao);
										} else {
											$('#FANGSHINAME').val("");
										}

										if (juzhan_dianbiao != null
												&& juzhan_dianbiao != "") {
											$('#ZDNAME').val(juzhan_dianbiao);
										} else {
											$('#ZDNAME').val("");
										}
										if (shi_dianbiao != null
												&& shi_dianbiao != "") {
											$('#SHI').val(shi_dianbiao);
										} else {
											$('#SHI').val("");
										}
										if (xian_dianbiao != null
												&& xian_dianbiao != "") {
											$('#XIAN').val(xian_dianbiao);
											$('#XIANCODE').val(
													xian_code_dianbiao);
											// getAuditors(xian_code_dianbiao);//根据区县查找流程执行人.
										} else {
											$('#XIAN').val("");
										}
										if (dianbiao_ftbl != null
												&& dianbiao_ftbl != "") {
											$('#FTBL').val(dianbiao_ftbl);
										} else {
											$('#FTBL').val("");
										}
										if (dianbiao_sfyz != null
												&& dianbiao_sfyz != "") {
											$('#SFYZ').val(dianbiao_sfyz);
										} else {
											$('#SFYZ').val("");
										}
										if (beilv_dianbiao != null
												&& beilv_dianbiao != "") {
											$('#BEILV').val(beilv_dianbiao);
										} else {
											$('#BEILV').val("1");
										}
									} else {
										$("#PRICE").val("");
										$('#PRICE').attr("disabled", false);
										$('#ZDNAME').val("");
										$('#SHI').val("");
										$('#XIAN').val("");
										$('#BEILV').val("");
										$('#FANGSHINAME').val("");

									}

									if (baozhangRe != null
											&& baozhangRe.length > 0) {
										//开始时间
										if (baozhangRe.split("@_@")[0] != null
												&& baozhangRe.split("@_@")[0] != "") {
											alert(CSSYTIME_s);
											$("#STARTTIME").val(
													getNextDay(baozhangRe
															.split("@_@")[0]
															.substr(0, 10)));
											$('#STARTTIME').attr("disabled",
													true);
										} else {
											$('#STARTTIME').attr("disabled",
													false);
										}
										//上期读数
										$("#SQDS").val(
												baozhangRe.split("@_@")[1]);
										if (baozhangRe.split("@_@")[1] != null
												&& baozhangRe.split("@_@")[1] != "") {
											$('#SQDS').attr("disabled", true);
										} else {
											$('#SQDS').attr("disabled", false);
										}

										$("#SQDJ").val(
												baozhangRe.split("@_@")[2]);
										if (baozhangRe.split("@_@")[2] != null
												&& baozhangRe.split("@_@")[2] != "") {
											$('#SQDJ').attr("disabled", true);
										} else {
											$('#SQDJ').attr("disabled", false);
										}

										if (baozhangRe.split("@_@")[3] != null
												&& baozhangRe.split("@_@")[3] != "") {
											$("#SQRJYDL").val(
													baozhangRe.split("@_@")[3]);
										} else {
											$("#SQRJYDL").val("0");
										}

										//手机抄表内容添加
										if (baozhangRe.split("@_@")[4] != null
												&& baozhangRe.split("@_@")[4] != "") {

											$("#STARTTIME_C").val(
													getNextDay(baozhangRe
															.split("@_@")[4]
															.substr(0, 10)));
											$('#STARTTIME_C').attr("disabled",
													true);
										} else {
											$('#STARTTIME_C').attr("disabled",
													false);
										}

										$("#SQDS_C").val(
												baozhangRe.split("@_@")[5]);
										if (baozhangRe.split("@_@")[5] != null
												&& baozhangRe.split("@_@")[5] != "") {
											$('#SQDS_C').attr("disabled", true);
										} else {
											$('#SQDS_C')
													.attr("disabled", false);
										}

										//end
									} else {
										//手机抄表内容添加
										$("#STARTTIME_C").val("");
										$('#STARTTIME_C').attr("disabled",
												false);
										$("#SQDS_C").val("");
										$('#SQDS_C').attr("disabled", false);
										//end

										//第一次报账时获取电表的的初始读数和日期和单价   2018年3月14日 - gcl
										if (CSSYTIME_dianbiao != null
												&& CSSYTIME_dianbiao != "") {
											$('#STARTTIME').val(
													CSSYTIME_dianbiao.substr(0,
															10));
											$('#STARTTIME').attr("disabled",
													true);
										} else {
											$('#STARTTIME').val("");
											$('#STARTTIME').attr("disabled",
													false);
										}

										//初始读数
										if (CSDS_dianbiao != null
												&& CSDS_dianbiao != "") {
											//如果初始读数为零,则放开,因为数据库该字段默认为零
											if (CSDS_dianbiao == 0) {
												$("#SQDS").val(CSDS_dianbiao);
												$('#SQDS').attr("disabled",
														false);
											} else {
												$("#SQDS").val(CSDS_dianbiao);
												$('#SQDS').attr("disabled",
														true);
											}
										} else {
											$('#SQDS').val("");
											$('#SQDS').attr("disabled", false);
										}

										//单价
										if (dianjia_dianbiao != null
												&& dianjia_dianbiao != "") {
											$("#SQDJ").val(dianjia_dianbiao);
											$('#SQDJ').attr("disabled", true);
										} else {
											$('#SQDJ').val("");
											$('#SQDJ').attr("disabled", false);
										}

										$("#SQRJYDL").val("0");
									}

									if (pueRe != null && pueRe.length > 0
											&& pueRe != "null") {
										$("#AVGPUEZHI").val(pueRe);
									} else {
										$("#AVGPUEZHI").val("");
									}

								}
								changeSJ();
							}
						});
			}

		}
		function getAuditors(xian,roleId,ydsx1){
			var agcode = xian;
			$
					.ajax({
						type : "POST",//请求方式
						url : path + "/servlet/workflow?action=selAccount",
						data : {
							agcode : agcode,
							ydsx1:ydsx1,
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
		function getNextDay(d) {
			d = new Date(d);
			d = +d + 1000 * 60 * 60 * 24;
			d = new Date(d);
			//return d;
			//格式化
			return d.getFullYear() + "-" + (d.getMonth() + 1) + "-"
					+ d.getDate();

		}
		function changeSJ() {
			var dianbiaoId = document.form1.DIANBIAOID.value;
			//手机抄表内容添加
			var STARTTIME_ch_C = $("#STARTTIME_C").val();
			var ENDTIME_ch_C = $("#ENDTIME_C").val();
			var SQDS_ch_C = $("#SQDS_C").val();
			var BQDS_ch_C = $("#BQDS_C").val();
			//end

			var STARTTIME_ch = $("#STARTTIME").val();
			var ENDTIME_ch = $("#ENDTIME").val();

			var BQDS_ch = $("#BQDS").val();
			var SQDS_ch = $("#SQDS").val();
			var PRICE_ch = $("#PRICE").val();
			var ALLMONEY_ch = $("#ALLMONEY").val();
			var DIANSUN_ch = $("#DIANSUN").val();
			var FTBL_ch = $("#FTBL").val();
			var SFYZ_ch = $("#SFYZ").val();
			var FANGSHI_ch = $("#FANGSHI").val();
			var BHSJE_ch = $("#BHSJE").val();
			var BEILV_ch = $("#BEILV").val();
			var DIANLIANG_ch = $("#DIANLIANG").val();
			if(FTBL_ch!="" && ALLMONEY_ch!=""){
				$("#BHSJE").val(((Number(FTBL_ch)/100)*Number(ALLMONEY_ch)).toFixed(2));
				}
			if (DIANLIANG_ch != "" && FTBL_ch != "") {//分摊电量
				$("#FTDL").val(((Number(FTBL_ch)/100) * (Number(DIANLIANG_ch))).toFixed(2));
			} else {
				$("#FTDL").val("");
			}
			if (BEILV_ch == "") {
				BEILV_ch = 1;
			}
			//分摊金额计算  缴费金额*分摊比例*（1+税负因子）
			if (ALLMONEY_ch != "" && FTBL_ch != "" && SFYZ_ch != "") {
				$("#FTJE").val(
						(((Number(ALLMONEY_ch) * (Number(FTBL_ch)/100)).toFixed(2))*((Number(SFYZ_ch)/100)+1)).toFixed(2));
			
			}
			//电量赋值
			if (BQDS_ch != "" && SQDS_ch != "") {
				$("#DIANLIANG").val(
						((BQDS_ch * 1 - SQDS_ch * 1) * BEILV_ch).toFixed(2));
			} else {
				$("#DIANLIANG").val("");
			}

			//用电天数赋值
			if (STARTTIME_ch != "" && ENDTIME_ch != "") {
				$("#TIANSHU").val(DateDiff(ENDTIME_ch, STARTTIME_ch));
				if (BQDS_ch != "" && SQDS_ch != "") {
					$("#RJYDL")
							.val(
									(((BQDS_ch * 1 - SQDS_ch * 1).toFixed(2)) / (DateDiff(
											ENDTIME_ch, STARTTIME_ch)))
											.toFixed(2));
				} else {
					$("#RJYDL").val("");
				}
			} else {
				$("#TIANSHU").val("");
				$("#RJYDL").val("");
			}

			//直供电添加单价，其他添加电损
			if (FANGSHI_ch == "gdfs05") {
				if (BQDS_ch != "" && SQDS_ch != "" && ALLMONEY_ch != ""
						&& DIANSUN_ch != "") {
					$("#PRICE").val(
							((ALLMONEY_ch * 1) / ((BQDS_ch * 1 - SQDS_ch * 1)
									* (BEILV_ch * 1) + DIANSUN_ch * 1))
									.toFixed(4));
				} else {
					$("#PRICE").val("");
				}
			} else {
				if (BQDS_ch != "" && SQDS_ch != "" && ALLMONEY_ch != ""
						&& PRICE_ch != "") {
					$("#DIANSUN")
							.val(
									((ALLMONEY_ch * 1) - (((BQDS_ch * 1 - SQDS_ch * 1) * (BEILV_ch * 1)) * (PRICE_ch * 1)))
											.toFixed(2));
				} else {
					$("#DIANSUN").val("");
				}
			}

			//手机抄表计算方法添加
			//手机电量赋值
			if (BQDS_ch_C != "" && SQDS_ch_C != "") {
				$("#DIANLIANG_C")
						.val(
								((BQDS_ch_C * 1 - SQDS_ch_C * 1) * BEILV_ch)
										.toFixed(2));
			} else {
				$("#DIANLIANG_C").val("");
			}
			//手机用电天数赋值
			if (STARTTIME_ch_C != "" && ENDTIME_ch_C != "") {
				$("#TIANSHU_C").val(DateDiff(ENDTIME_ch_C, STARTTIME_ch_C));
				if (BQDS_ch_C != "" && SQDS_ch_C != "") {
					$("#RJYDL_C")
							.val(
									(((BQDS_ch_C * 1 - SQDS_ch_C * 1)
											.toFixed(2)) / (DateDiff(
											ENDTIME_ch_C, STARTTIME_ch_C)))
											.toFixed(2));
				} else {
					$("#RJYDL_C").val("");
				}
			} else {
				$("#TIANSHU_C").val("");
				$("#RJYDL_C").val("");
			}
			//end
			//报账信息添加 2018/1/18
			//计算抄表理论用电量
			if ($("#DIANLIANG").val() != "" && $("#TIANSHU").val() != ""
					&& $("#TIANSHU_C").val() != "" && $("#RJYDL").val() != "") {
				var DIANLIANG_V = $("#DIANLIANG").val(); //用电量
				var TIANSHU_V = $("#TIANSHU").val(); //天数
				var TIANSHU_C_V = $("#TIANSHU_C").val(); //手机天数
				var RJYDL_V = $("#RJYDL").val(); //日均用电量
				$("#LiLunDianLiang").val(
						DIANLIANG_V * 1 + (TIANSHU_C_V * 1 - TIANSHU_V * 1)
								* RJYDL_V * 1);
			} else {
				$("#LiLunDianLiang").val("");
			}
			//计算电量偏离数
			if ($("#LiLunDianLiang").val() != ""
					&& $("#DIANLIANG_C").val() != "") {
				var LiLunDianLiang_V = $("#LiLunDianLiang").val(); //抄表理论用电量
				var TIANSHU_V = $("#DIANLIANG_C").val(); //抄表实际用电量
				$("#DianLiangPianLiShu").val(
						(TIANSHU_V / LiLunDianLiang_V).toFixed(2));
				$(document).ready(function() {
					var i = $("#DianLiangPianLiShu").val() * 1;
					if (i < 0.95) {
						$('#DianLiangPianLiShu').addClass('red');
						//	alert("电量偏离度过大");
					} else if (i > 1.05) {
						$('#DianLiangPianLiShu').addClass('red');
						//	alert("电量偏离度过大");
					} else {
						$('#DianLiangPianLiShu').addClass('black');
						//	alert("电量偏离度正常");
					}
				});
			} else {
				$("#DianLiangPianLiShu").val("");
			}
			//计算日期偏离数
			if ($("#ENDTIME").val() != "" && $("#ENDTIME_C").val() != "") {
				$("#RiQiPianLiShu")
						.val(
								(DateDiff($("#ENDTIME").val(), $("#ENDTIME_C")
										.val())) - 1);
				$(document).ready(function() {
					var i = $("#RiQiPianLiShu").val() * 1;
					if (i < -15) {
						$('#RiQiPianLiShu').addClass('red');
						//	alert("日期偏离度过大");
					} else if (i > 15) {
						$('#RiQiPianLiShu').addClass('red');
						//alert("日期偏离度过大");
					} else {
						$('#RiQiPianLiShu').addClass('black');
						//	alert("日期偏离度正常");
					}
				});
			} else {
				$("#RiQiPianLiShu").val();
			}
			if (dianbiaoId != "0" && BQDS_ch != "" && SQDS_ch != ""
					&& STARTTIME_ch != "" && ENDTIME_ch != "") {
				var dl = ((BQDS_ch * 1 - SQDS_ch * 1) * BEILV_ch).toFixed(2);
				var ts = DateDiff(ENDTIME_ch, STARTTIME_ch);
				$.ajax({
					type : "POST",//请求方式
					//    async: false,
					url : path + "/servlet/zhandian?action=getDbxxById",
					data : {
						dianbiaoId : dianbiaoId,
						stime : STARTTIME_ch,
						etime : ENDTIME_ch,
						dl : dl,
						ts : ts
					},
					dataType : "json",
					scriptCharset : 'utf-8',
					success : function(result) {
						if (result != null && result.length > 0) {
							if (result.indexOf("*") != -1) {
								$("#PUEZHI").val(result.replace("*", ""));
								alert("直流主设备电流,未能正常检索,按照标准14A");
							} else {
								$("#PUEZHI").val(result);
							}
						}
					}
				});
			} else {
				$("#PUEZHI").val("");
			}
			if (dianbiaoId != "0" && STARTTIME_ch != "" && ENDTIME_ch != "") {
				var ts = DateDiff(ENDTIME_ch, STARTTIME_ch);
				var SQRJYDL_str = $("#SQRJYDL").val();
				$
						.ajax({
							type : "POST",//请求方式
							//  async: false,
							url : path
									+ "/servlet/dianbiao?action=getDbxxById2",
							data : {
								dianbiaoId : dianbiaoId,
								stime : STARTTIME_ch,
								etime : ENDTIME_ch,
								ts : ts,
								SQRJYDL_str : SQRJYDL_str
							},
							dataType : "json",
							scriptCharset : 'utf-8',
							success : function(result) {
								if (result != null && result.length > 0) {
									$("#BGDL").val(result);
									if (result != "" && BQDS_ch != ""
											&& SQDS_ch != "") {
										var BGPLL = (((BQDS_ch * 1 - SQDS_ch * 1) * BEILV_ch) - result * 1)
												/ ((ts * 1) * (result * 1));
										BGPLL = (BGPLL * 100).toFixed(2);
										$("#BGPLL").val(BGPLL);

									}

								}
							}
						});
			} else {
				$("#BGDL").val("");
				$("#BGPLL").val("");
			}
			//END $("#TIANSHU").val(DateDiff(ENDTIME_ch,STARTTIME_ch));

			//选择开始日期后,放开结束日期 2018年3月17日-gcl
			var STARTTIME = $("#STARTTIME").val();
			if (STARTTIME != "" || STARTTIME != null) {
				$("#ENDTIME").attr("disabled", false);
			}
			if (STARTTIME == "" || STARTTIME == null) {
				$("#ENDTIME").val("");
				$("#ENDTIME").attr("disabled", true);
			}

			var STARTTIME_C = $("#STARTTIME_C").val();
			if (STARTTIME_C != "" || STARTTIME_C != null
					|| !"".equals(STARTTIME_C)) {
				$("#ENDTIME_C").attr("disabled", false);
			}
			if (STARTTIME_C == "" || STARTTIME_C == null
					|| "".equals(STARTTIME_C)) {
				$("#ENDTIME_C").val("");
				$("#ENDTIME_C").attr("disabled", true);
			}
		}
		function DateDiff(sDate1, sDate2) { //sDate1和sDate2是2006-12-18格式  
			var aDate, oDate1, oDate2, iDays;
			aDate = sDate1.split("-");
			oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]); //转换为12-18-2006格式  
			aDate = sDate2.split("-");
			oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
			iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数  
			return iDays * 1 + 1;
		}
		function showPUE() {
			var s = document.form1.DIANBIAOID.value;
			if (s == "0") {
				alert("请先选择电表！");
				return;
			} else {
				window
						.open(
								"showPUE.jsp?dbid=" + s,
								"newwindowPue",
								"height=330, width=1000, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
			}
		}
		function selectFlow() {
			var flowid = $("#flowName").val();
			var ydsx1 = $("#ydsxbm").val();
			console.log(flowid);
			$
					.ajax({
						type : "POST",//请求方式
						url : path + "/servlet/workflow?action=selflow2",
						data : {
							flowid : flowid
						},
						dataType : "json",
						success : function(data) {
							var acount = data[0];
							//[5,"ACTIONID","ACTIONNAME","FLOWID","FLOWNAME","ROLEID",
							var flowName = "", flowId = "0", actionId = "", actionName = "", roleId = "";
							if (data.length > acount + 1) {
								actionId = data[acount
										+ data.indexOf("ACTIONID")];
								actionName = data[acount
										+ data.indexOf("ACTIONNAME")];
								flowId = data[acount + data.indexOf("FLOWID")];
								flowName = data[acount
										+ data.indexOf("FLOWNAME")];
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
		//2018年3月9日 -gcl 根据金额判断是否更改
		function shuie() {
			//税额赋值
			//税额计算公式 : 总金额/(1+税额)*税额 = 税额
			var ALLMONEY_ch = $("#ALLMONEY").val();
			var zzsl_ch = $("#zzsl").val();
			if (zzsl_ch != "" && zzsl_ch != null) {
				$("#TAX")
						.val(
								(parseFloat(ALLMONEY_ch)
										/ (parseFloat(1) + parseFloat(zzsl_ch)) * parseFloat(zzsl_ch))
										.toFixed(2));
			} else {
				$("#TAX").val("0");
			}
		}

		//进入页面时判断,初始日期是否为空,为空时不可选择结束日期
		var STARTTIME = $("#STARTTIME").val();
		if (STARTTIME == "" || STARTTIME == null || "".equals(STARTTIME)) {
			$("#ENDTIME").attr("disabled", true);
		}//报账-end

		var STARTTIME_C = $("#STARTTIME_C").val();
		if (STARTTIME_C == "" || STARTTIME_C == null || "".equals(STARTTIME_C)) {
			$("#ENDTIME_C").attr("disabled", true);
		}//抄表-end

		function endtime() {
			var STARTTIME = $("#STARTTIME").val();
			if (STARTTIME == "" || STARTTIME == null || "".equals(STARTTIME)) {
				alert("请先选择开始日期!");
			}
		}//报账

		function endtime_c() {
			var STARTTIME_C = $("#STARTTIME_C").val();
			if (STARTTIME_C == "" || STARTTIME_C == null
					|| "".equals(STARTTIME_C)) {
				alert("请先选择手机开始日期!");
			}
		} //抄表
	</script>
</body>
</html>
