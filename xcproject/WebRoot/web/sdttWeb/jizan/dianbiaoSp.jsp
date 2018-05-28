<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.*"%>
<%@ page import="java.lang.Double"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.jizhan.JiZhanBean"%>
<%@ page import="com.noki.zwhd.model.DwBean"%>
<%@ page import="com.noki.zwhd.manage.SystemManage"%>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.*"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="java.sql.ResultSet"%>

<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String accountname = account.getAccountName();
	String sheng = (String) session.getAttribute("accountSheng");
	String loginId = account.getAccountId();
	String shengId = (String) session.getAttribute("accountSheng");
	String loginName = (String) session.getAttribute("loginName");
	String id = request.getParameter("id");
	session.setAttribute("dbid", id);
	String zdid = "";
	String bz = request.getParameter("bz");
	DataBase db = new DataBase();
	//流程信息
	String nextaction = "", actionname = "", nextactionId = "", workFlowId = "", currentid = "", flowId = "", applyUserId = "", lastaction = "",lastactionname="", bohui = "",tasktype="",auditorid="",bo="";
	String steporderno="", nextsteporderno="", laststeporderno="";
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
	
	
	SiteModifyBean form = new SiteModifyBean();
	
	String a = "", dbbm = "", shi = "0", xian = "0", xiaoqu = "0", zdmc = "0", dbmc = "", cssysj = "", beilv = "", csds = "", dfzf = "0";
	String dbId = "";
	String jzname="",jztype="",sszy="",dbyt="",zdlx="",cssytime="",dbxh="",memo="",szdq="";
	String maxds="",dwjslx="",isglf="",glbm="",zrr="",bzr="",ydsx="",ydlx="",jffs="",jfzq="",jldw="",yhbh="";
	String dbqyzt="",bgje="",mtllhd="",danjia="",gysmc="",gysbm="",skfmc="",yhzh="",ssyh="",khyh="",zhsss="",zhssshi="",fplx="";
	String zzsl="",dbch="",dbscbs="",danjia_mo="",beilv_mo="",dbqyzt_mo="",zt="",ssf="",Tssf="",zt_mo="";
		String sql = "select d.*,"
		+"(select t.name from indexs t where t.code=d.dbqyzt and t.type='dbzt') as dbzt,"
		+"(select t.name from indexs t where t.code=d.dbqyzt_mo and t.type='dbzt') as dbqyzt_mo2"
		+" from dianbiao d where id="
				+ taskid + "";
		System.out.println(sql.toString());
		ResultSet rs = null;
		db.connectDb();
		rs = db.queryAll(sql.toString());
		while (rs.next()) {
			dbmc = rs.getString("dbname") == null ? "" : rs
					.getString("dbname");
			cssysj = rs.getString("cssytime") == null ? "" : rs
					.getString("cssytime");
			System.out.println(cssysj.length());
			if (cssysj.length() != 0) {
				a = cssysj.substring(0, 10);
			}
			beilv = rs.getString("beilv") == null ? "" : rs
					.getString("beilv");
			csds = rs.getString("csds") == null ? "" : rs
					.getString("csds");
			dfzf = rs.getString("dfzflx");
			zdid = rs.getString("zdid");
			dbbm = rs.getString("dbbm") == null ? "" : rs
					.getString("dbbm");
			jztype=rs.getString("jztype")==null?"":rs.getString("jztype");
	 		sszy=rs.getString("sszy")==null?"":rs.getString("sszy");
	 		dbyt=rs.getString("dbyt")==null?"":rs.getString("dbyt");
	 		cssytime=rs.getString("cssytime")==null?"":rs.getString("cssytime");
	 		dbxh=rs.getString("dbxh")==null?"":rs.getString("dbxh");
	 		memo=rs.getString("memo")==null?"":rs.getString("memo");
	 		
	 		maxds=rs.getString("maxds")==null?"":rs.getString("maxds");
	 		dwjslx=rs.getString("dwjslx")==null?"":rs.getString("dwjslx");
	 		isglf=rs.getString("isglf")==null?"":rs.getString("isglf");
	 		glbm=rs.getString("glbm")==null?"":rs.getString("glbm");
	 		zrr=rs.getString("zrr")==null?"":rs.getString("zrr");
	 		bzr=rs.getString("bzr")==null?"":rs.getString("bzr");
	 		ydsx=rs.getString("ydsx")==null?"":rs.getString("ydsx");
	 		ydlx=rs.getString("ydlx")==null?"":rs.getString("ydlx");
	 		jffs=rs.getString("jffs")==null?"":rs.getString("jffs");
	 		jfzq=rs.getString("jfzq")==null?"":rs.getString("jfzq");
	 		jldw=rs.getString("jldw")==null?"":rs.getString("jldw");
	 		yhbh=rs.getString("yhbh")==null?"":rs.getString("yhbh");
	 		bgje=rs.getString("bgje")==null?"":rs.getString("bgje");
	 		mtllhd=rs.getString("mtllhd")==null?"":rs.getString("mtllhd");
	 		danjia=rs.getString("danjia")==null?"":rs.getString("danjia");
	 		gysmc=rs.getString("gysmc")==null?"":rs.getString("gysmc");
	 		gysbm=rs.getString("gysbm")==null?"":rs.getString("gysbm");
	 		skfmc=rs.getString("skfmc")==null?"":rs.getString("skfmc");
	 		yhzh=rs.getString("yhzh")==null?"":rs.getString("yhzh");
	 		ssyh=rs.getString("ssyh")==null?"":rs.getString("ssyh");
	 		khyh=rs.getString("khyh")==null?"":rs.getString("khyh");
	 		zhsss=rs.getString("zhsss")==null?"":rs.getString("zhsss");
	 		zhssshi=rs.getString("zhssshi")==null?"":rs.getString("zhssshi");
	 		dbqyzt=rs.getString("dbzt")==null?"":rs.getString("dbzt");
	 		fplx=rs.getString("fplx")==null?"":rs.getString("fplx");
	 		zzsl=rs.getString("zzsl")==null?"":rs.getString("zzsl");
	 		dbch=rs.getString("dbch")==null?"":rs.getString("dbch");
	 		dbscbs=rs.getString("dbscbs")==null?"":rs.getString("dbscbs");
	 		
	 		danjia_mo=rs.getString("danjia_mo")==null?"":rs.getString("danjia_mo");
	 		beilv_mo=rs.getString("beilv_mo")==null?"":rs.getString("beilv_mo");
	 		dbqyzt_mo=rs.getString("dbqyzt_mo2")==null?"":rs.getString("dbqyzt_mo2");
	 		
	 		zt_mo=rs.getString("dbqyzt_mo")==null?"":rs.getString("dbqyzt_mo");
	 		zt=rs.getString("dbqyzt")==null?"":rs.getString("dbqyzt");
	 		ssf = rs.getString("ssf")==null ? "" : rs.getString("ssf");
			System.out.println("----------dddd----"+ssf);
			if(ssf.equals("1")){
				Tssf="非电信";
			}else if(ssf.equals("2")){
				Tssf="电信";
			}
		form = form.getJizhan(zdid, "");
		shi = form.getShi();
		xian = form.getXian();
		xiaoqu = form.getXiaoqu();
		zdmc = form.getId();
		System.out.println(danjia_mo+"==="+beilv_mo+"==="+dbqyzt_mo);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>电表审批</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
</head>
<!--[if gt IE 8]><!-->
<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"></script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
<!--<![endif]-->
<!--[if lte IE 8]>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/console.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page"
	class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<jsp:useBean id="bean" scope="page"
	class="com.noki.mobi.workflow.javabean.WorkFlowBean">
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
						<div class="tit01">电表数据审批</div>
						<div class="content01_1">
							<table width="1000px" border="0" cellpadding="2" cellspacing="0"
								class="tb_select">
								<tr>
									<td align="right" width="100px">城市</td>
									<td width="100px"><select name="shi" disabled="disabled"
										style="background-color: #EBEBE4;box-sizing:border-box;width:130px"
										class="selected_font" onchange="changeCity()">
											<option value="0">请选择</option>
											<%
													ArrayList shenglist = new ArrayList();
													shenglist = commBean.getAgcode(shengId, "0", loginName);
													if (shenglist != null) {
														String sfid = "", sfnm = "";
														int scount = ((Integer) shenglist.get(0)).intValue();
														for (int i = scount; i < shenglist.size() - 1; i += scount) {
															sfid = (String) shenglist.get(i
																	+ shenglist.indexOf("AGCODE"));
															sfnm = (String) shenglist.get(i
																	+ shenglist.indexOf("AGNAME"));
												%>
											<option value="<%=sfid%>"><%=sfnm%></option>
											<%
													}
													}
												%>
									</select><span style="color: #FF0000; font-weight: bold">*</span> &nbsp;
									</td>
									<td align="right" width="100px">区县</td>
									<td width="100px"><select name="xian" disabled="disabled"
										style="background-color: #EBEBE4;box-sizing:border-box;width:130px"
										class="selected_font" onchange="changeCountry()">
											<option value="0">请选择</option>
											<%
													ArrayList xianlist = new ArrayList();
													xianlist = commBean.getAgcode(form.getShi(), form.getXian(),
															loginName);
													if (xianlist != null) {
														String agcode = "", agname = "";
														int scount = ((Integer) xianlist.get(0)).intValue();
														for (int i = scount; i < xianlist.size() - 1; i += scount) {
															agcode = (String) xianlist.get(i
																	+ xianlist.indexOf("AGCODE"));
															agname = (String) xianlist.get(i
																	+ xianlist.indexOf("AGNAME"));
												%>
											<option value="<%=agcode%>"><%=agname%></option>
											<%
													}
													}
												%>
									</select><span style="color: #FF0000; font-weight: bold">*</span></td>
									<td align="right" width="100px">乡镇</td>
									<td width="100px"><select disabled="disabled"
										style="background-color: #EBEBE4;box-sizing:border-box;width:130px"
										name="xiaoqu" id="xiaoqu" onchange="changezdmc()"
										class="selected_font">
											<option value="0">请选择</option>
											<%
													ArrayList xiaoqulist = new ArrayList();
													xiaoqulist = commBean.getAgcode(form.getXian(), form.getXiaoqu(),
															loginName);
													if (xiaoqulist != null) {
														String agcode = "", agname = "";
														int scount = ((Integer) xiaoqulist.get(0)).intValue();
														for (int i = scount; i < xiaoqulist.size() - 1; i += scount) {
															agcode = (String) xiaoqulist.get(i
																	+ xiaoqulist.indexOf("AGCODE"));
															agname = (String) xiaoqulist.get(i
																	+ xiaoqulist.indexOf("AGNAME"));
												%>
											<option value="<%=agcode%>"><%=agname%></option>
											<%
													}
													}
												%>
									</select><span style="color: #FF0000; font-weight: bold">*</span></td>
									<td align="right" width="100px">站点名称</td>
									<td width="180px"><select disabled="disabled"
										style="background-color: #EBEBE4;box-sizing:border-box;width:130px"
										name="zdmc" onchange="changeDbbm()" class="selected_font">
											<option value="0">请选择</option>
											<%
													ArrayList zdmclist = new ArrayList();
													zdmclist = commBean.getzdmc(form.getId());
													if (zdmclist != null) {
														String agcode = "", agname = "";
														int scount = ((Integer) zdmclist.get(0)).intValue();
														for (int i = scount; i < zdmclist.size() - 1; i += scount) {
															agcode = (String) zdmclist.get(i + zdmclist.indexOf("ID"));
															agname = (String) zdmclist.get(i
																	+ zdmclist.indexOf("JZNAME"));
												%>
											<option value="<%=agcode%>"><%=agname%></option>
											<%
													}
													}
												%>
									</select><span style="color: #FF0000; font-weight: bold">*</span></td>
								</tr>
								<tr>
										<td align="right" width="100px">
											所属方
										</td>
										<td width="180px">
											<select name="ssf" disabled="disabled" 
												style="background-color: #EBEBE4; width: 130px;"
												 class="selected_font">
									  			
													<option value="<%=ssf%>"><%=Tssf%></option>
											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									</tr>
								<tr bgcolor="F9F9F9">
									<td height="19" colspan="4"><img src="../../images/v.gif"
										width="15" height="15" /> 电表信息</td>
								</tr>
								<tr>
									<td align="right" width="100px">电表名称</td>
									<td width="25%"><input type="text" id = "dbname"; name="dbname"
										value="<%=dbmc %>" class="selected_font" disabled="disabled"
										style="box-sizing:border-box;width:130px" /></td>
									<td align="right" width="100px">电表编码</td>
									<td width="25%"><input type="text" name="dbbm"
										value="<%=dbbm %>" class="selected_font" disabled="disabled"
										style="box-sizing:border-box;width:130px" /></td>
									<%if(dblc_type.equals("4")){%>

									<td align="right" width="100px">原单价</td>
									<td width="100px"><input type="text" value="<%=danjia%>"
										style="box-sizing:border-box;width:130px" disabled="disabled" />
									</td>
									<td align="right" width="100px">修改单价</td>
									<td width="100px"><input type="text" name="danjia_mo"
										value="<%=danjia_mo%>"
										style="box-sizing:border-box;width:130px"
										<%if(!bo.equals("1")){ %> disabled="disabled" <%} %> />
									</td>

								</tr>

								<% }%>
								<%if(dblc_type.equals("2")){%>
								<tr>

									<td align="right" width="100px">原倍率</td>
									<td width="100px"><input type="text" value="<%=beilv%>"
										style="box-sizing:border-box;width:130px" disabled="disabled" />
									</td>
									<td align="right" width="100px">修改倍率：</td>
									<td width="100px"><input type="text" name="beilv_mo"
										value="<%=beilv_mo%>"
										style="box-sizing:border-box;width:130px"
										<%if(!bo.equals("1")){ %> disabled="disabled" <%} %> />
									</td>

								</tr>

								<% }%>
								<%if(dblc_type.equals("3")){%>
								<tr>

									<td align="right" width="100px">原状态：</td>
									<td width="100px"><input type="text" value="<%=dbqyzt%>"
										style="box-sizing:border-box;width:130px" disabled="disabled" />
									</td>
									<td align="right" width="100px">修改状态：</td>
									<td width="100px">
										<%if(bo.equals("1")){ %> <select name="dbqyzt"
										style="box-sizing:border-box;width:130px;"
										class="selected_font">
											<option value="0">请选择</option>
											<%
						         	ArrayList SERVICE_ID_dict = new ArrayList();
					         		SERVICE_ID_dict = ztcommon.getSelctOptions("dbzt");
						         	if(SERVICE_ID_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)SERVICE_ID_dict.get(0)).intValue();
						         		for(int i=cscount;i<SERVICE_ID_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)SERVICE_ID_dict.get(i+SERVICE_ID_dict.indexOf("CODE"));
					                    	name=(String)SERVICE_ID_dict.get(i+SERVICE_ID_dict.indexOf("NAME"));
					                    %>
											<option value="<%=code%>"><%=name%></option>
											<%}
						         	}
						         %>
									</select> <%}else{ %> <input type="text" name="zt_mo"
										value="<%=dbqyzt_mo%>"
										style="box-sizing:border-box;width:130px" disabled="disabled" />
										<%} %> <input type="hidden" name="dbqyzt_mo" value="<%=zt_mo%>" />
									</td>

								</tr>

								<% }%>
								<tr>
									<td height="19" colspan="8"><img src="../../images/v.gif"
										width="15" height="15" /> 申请/审核信息</td>
								</tr>
								<%//if(!bo.equals("1")){ %>
								<tr>
									<td align="right" width="100px">报送人</td>
									<td><input type="text" name="ACCNAME" id="ACCNAME"
										disabled="disabled" value="<%=ACCNAME%>"
										style="box-sizing: border-box; width: 130px" /></td>
									<td align="right" width="100px">报送时间</td>
									<td><input type="text" name="ACCTIME" id="ACCTIME"
										disabled="disabled" value="<%=ACCTIMEStr%>"
										style="box-sizing: border-box; width: 130px" /></td>
								</tr>

								<tr>
									<td colspan="8">
										<div>审核意见：</div>
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
											<label> <input type="radio" name="shenhe"
												onchange="tongyi()" value="0" /> 同意
											</label> <label> <input type="radio" name="shenhe"
												onchange="tongyi()" value="1" /> 不同意
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
															agcode = shi;
														} else if (nextaction.indexOf("县") != -1) {
															agcode = xian;
														}
														ArrayList acclist = new ArrayList();
														//if (nextsteporderno.equals("2")) {//step2
														if ("市公司电费管理员审核".equals(nextaction)) {//step2
															if (!ydsx.isEmpty() && ydsx.equals("ydsx11")) {//办公
																acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "办公");
															} else if (!ydsx.isEmpty() && ydsx.equals("ydsx12")) {//营业厅
																acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "营业厅");
															} else if (!ydsx.isEmpty() && ydsx.equals("ydsx15")) {//其他
																acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "生产");
															}

															 else {//生产
																acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "生产");
															}

														//} else if (nextsteporderno.equals("3")) {//step3
															} else if ("市级主任审核".equals(nextaction)) {//step3
															if (!ydsx.isEmpty() && ydsx.equals("ydsx11")) {//办公
																acclist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(),agcode, "%市综合部主任%");
															} else if (!ydsx.isEmpty() && ydsx.equals("ydsx12")) {//营业厅
																acclist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(),agcode, "%市销售部主任%");
															}  else {//生产
																acclist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(),agcode, "%市运维主任%");
															}
													} else {//角色
															acclist = bean.getAccountByActionId(nextactionId, taskid, agcode, tasktype);
														}
															if (acclist.size()!=0) {
																String accountId = "", name = "";
																int cscount = ((Integer) acclist.get(0)).intValue();
																for (int i = cscount; i < acclist.size() - 1; i += cscount) {
																	accountId = (String) acclist.get(i + acclist.indexOf("ACCOUNTID"));
																	name = (String) acclist.get(i + acclist.indexOf("NAME"));
													%>
											<label> <input type="radio" name="auditorid"
												value="<%=accountId%>" checked /><%=name%></label>
											<%
														}
															}else{
															%>
											<label> <h2>无下一节点</h2></label>
											<%
															}
														}
													%>


										</div> <%--<%} --%>
									</td>
								</tr>
								<tr id="bohuiTr" style="display: none">
									<td colspan="8">
										<%
										String action = actionname.toString();
											System.out.println("---判断是否包含---"+action.contains("市电费管理员"));
										//if(action.contains("电费管理员")||action.contains("市电费管理员")||action.contains("区县或各部分负责人审核")){
											//if (steporderno.equals("2")) {//step2
											if ("市公司电费管理员审核".equals(action)) {//step2
												%>
										<div>上一节点：发起人</div>
										<div>
											执行人：
											<%
													 bohuijd = "0";
													String accName = bean.getAuditorName(applyUserId, db);
												%><label> <input type="radio" name="bohui" value="0"
												checked /><%=accName%></label>
										</div> <%
											} else if("市分管副总审核".equals(action)) {//
												String actionNo = bean.getStepNo(currentid, db);
												int stepNo = Integer.parseInt(actionNo);
												String  acc="", accName="", lactionname="";
												if((stepNo - 2) > 0){
													String nextNo = (stepNo - 2) + "";	
													String nextStep = bean.getNextStep(flowId, nextNo);
													acc = bean.getAuditor(id, nextStep, db);
													accName = bean.getAuditorName(acc, db);
													bohuijd = nextStep;
													lactionname = bean.getActionName(nextStep, db);													
												}else{
													bohuijd = "0";
													accName = bean.getAuditorName(applyUserId, db);
													acc = "0";
													lactionname="发起人";
												}
											
											%>	
														
										<div>上一节点：<%=lactionname %></div>
										<div>
											执行人：
											<label> <input type="radio" name="bohui"
												value="<%=acc%>" checked /><%=accName%></label>
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
											<label> <input type="radio" name="bohui" value="0"
												checked /><%=accName%></label>

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
													%><label> <input type="radio" name="bohui"
												value="<%=acc%>" checked /><%=accName%></label>

											<%
														}
													%>
										</div> <%
													}
												%>
									</td>
								</tr>
								<tr>
									<td colspan="8">
										<div>审批意见：</div>
										<div>
											<table>
												<tr align="center">
													<th width="10">序号</th>
													<th>审核人</th>
													<th>审核时间</th>
													<th>审核状态</th>
													<th>审核意见</th>
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
									<input type="button" class="btn_c1" id="button2" value="附件管理" onclick="fujianguanli('<%=taskid%>')" />
									<input onclick="tijiao('<%=taskid%>')" type="button" class="btn_c1" id="button2" value="提交" />&nbsp; 
									<input onclick="fanhui()" type="button" class="btn_c1" id="button2" value="取消" />&nbsp;</td>
								</tr>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<!--  <input type="hidden" name="dbqyzt" value="<%=zt%>" />-->
		<input type="hidden" name="danjia" value="<%=danjia%>" /> <input
			type="hidden" name="beilv" value="<%=beilv%>" /> <input
			type="hidden" name="currentid" value="<%=currentid%>" /> <input
			type="hidden" name="nextactionId" value="<%=nextactionId%>" /> <input
			type="hidden" name="workFlowId" value="<%=id%>" /> <input
			type="hidden" name="flowId" value="<%=flowId%>" /> <input
			type="hidden" name="applyUserId" value="<%=applyUserId%>" /> <input
			type="hidden" name="lastaction" value="<%=lastaction%>" /> <input
			type="hidden" name="tasktype" value="<%=tasktype%>" /> <input
			type="hidden" name="bohuijd" value="<%=bohuijd%>" /> <input
			type="hidden" name="dblc_type" value="<%=dblc_type%>" />
	</form>
</body>
</html>
<script type="text/javascript">

var path = '<%=path%>';
function tongyi() {
	$("input[name='beilv']").removeAttr("disabled");
		$("input[name='dj']").removeAttr("disabled");
		$("input[name='dbqyzt']").removeAttr("disabled");
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
		$("input[name='beilv_mo']").removeAttr("disabled");
			$("input[name='danjia_mo']").removeAttr("disabled");
			$("input[name='dbqyzt_mo']").removeAttr("disabled");
		document.form1.action = path+ "/servlet/workflow?action=adjust&taskId=" + taskId;
		document.form1.submit();
	} else {
		$("input[name='beilv']").removeAttr("disabled");
		$("input[name='dj']").removeAttr("disabled");
		$("input[name='dbqyzt']").removeAttr("disabled");
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

function fanhui() {
			window.close();
		}
function tg(id,n){
	var nr=document.form1.shishly.value;
	if(nr!=null&&nr!=""){
		if (confirm("确定要审核！")) {
		   	//$("input[name='beilv']").removeAttr("disabled");
			//$("input[name='danjia']").removeAttr("disabled");
			//$("input[name='dbqyzt']").removeAttr("disabled");
			$("input[name='beilv_mo']").removeAttr("disabled");
			$("input[name='danjia_mo']").removeAttr("disabled");
			$("input[name='dbqyzt_mo']").removeAttr("disabled");
			document.form1.action = path+"/servlet/dianbiao?action=shenhe&shsign="+n+"&id="+id;
			document.form1.submit();
		} else {
			return;
		}
	}else{
	alert("首先填写备注 ！");
	}
}
function fujianguanli(id){
	var liuchengxuanze = "2";
	var dianbiaoName = document.getElementById("dbname").value;
	if(dianbiaoName == "" || dianbiaoName == null || dianbiaoName == undefined){ // "",null,undefined
	     alert("电表名称不能为空");
    }else{
         window.open("../jizan/fujianguanli.jsp?id="+id+"&name="+dianbiaoName+"&liuchengxuanze="+liuchengxuanze, "newwindowCost", "height=550, width=1000, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
	}
}
document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
document.form1.zdmc.value = '<%=zdmc%>';
document.form1.dbqyzt.value = '<%=zt_mo%>';
</script>
