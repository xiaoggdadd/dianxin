<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page import="java.lang.Double"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%@ page import="java.sql.ResultSet"%>
<%
	String path = request.getContextPath();
	String shengId = (String) session.getAttribute("accountSheng");
	String loginName = (String) session.getAttribute("loginName");
	Account account = (Account) session.getAttribute("account");
	String rolename = account.getRoleName();
	String jzproperty = request.getParameter("jzproperty");//获取新站点的属性  
	String accountname = account.getAccountName();
	DataBase db = new DataBase();
	SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd");
	String entrytime = mat.format(new Date());
	System.out.println("系统时间" + entrytime + "  shengId:" + shengId
	+ "  loginName:" + loginName + "  accountname:"
	+ accountname + " jzproperty:" + jzproperty);
	
	String workFlowId = request.getParameter("workFlowId");
	
	String nextaction = "", actionname = "", nextactionId = "", ID = "", currentid = "", flowId = "", applyUserId = "", lastaction = "",lastactionname="", bohui = "",tasktype="",auditorid="",bo="";
	String steporderno="", nextsteporderno="", laststeporderno="";
	String ACCTIME = "", ACCNAME = "";
	StringBuffer sql2 = new StringBuffer();
	ResultSet rs2 = null;
	sql2.append("select w.* ,ai.action_name as actionname,ai2.action_name as nextactionname,ai3.action_name as lastactionname,f.flow_name as flowname,"
	+ "s.step_order_no as steporderno,s2.step_order_no as nextsteporderno,s3.step_order_no as laststeporderno,"
	+ " (select  a.name from account a where a.accountid =w.APPLYUSER_ID) as ACCNAME from s_workflow w "
	+ "left join S_STEP_INFO s on w.current_no = s.step_id "
	+ "left join S_ACTION_INFO ai on ai.action_id =s.action_id "
	+ "left join S_STEP_INFO s2 on w.NEXT_NO = s2.step_id "
	+ "left join S_ACTION_INFO ai2 on ai2.action_id =s2.action_id "
	+ "left join S_STEP_INFO s3 on w.LAST_NO = s3.step_id "
	+ "left join S_ACTION_INFO ai3 on ai3.action_id =s3.action_id "
	+ "left join S_FLOW_INFO f on f.flow_id =s.flow_id "
	+ "where w.ID ='" + workFlowId +"' ");
	System.out.println(sql2);
	db.connectDb();
	rs2 = db.queryAll(sql2.toString());
	while (rs2.next()) {
		ID = rs2.getString("TASK_ID") == null ? "" : rs2.getString("TASK_ID");
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
		if(applyUserId.equals(auditorid)){
	bo="1";
		}
		ACCTIME = rs2.getString("APPLYTIME") == null ? "" : rs2
		.getString("APPLYTIME");
		ACCNAME = rs2.getString("ACCNAME") == null ? "" : rs2
		.getString("ACCNAME");
		}
	String bohuijd="",zzsl="", TAX = "";
	String STARTTIME_C="",ENDTIME_C="", SQDS_C="",BQDS_C="",DIANLIANG_C="",DAYNUM_C="",RJYDL_C="", DLPLS = "",RQPLS="";
	String DIANBIAOID = "", DBNAME = "", CBZX = "", STARTTIME = "", ENDTIME = "", SQDS = "", BQDS = "", BEILV = "", DIANLIANG = "";
	String ALLMONEY = "", DIANSUN = "", MONEY = "", SQDJ = "", PRICE = "", FTXS = "", STARTTIMEStr = "", ENDTIMEStr = "";
	String SHI = "", ZDNAME = "", XIAN = "", TIANSHU = "", RJYDL = "", SQRJYDL = "",STARTTIMEStr_C="",ENDTIMEStr_C="", FANGSHI = "",SHICODE="",XIANCODE="",ydsx="";//用电属性
	String FANGSHINAME="",BGDL="",bgpll="",PUEZHI="",AVGPUEZHI="",DBIMAGEPATH1="",FTBL="",SFYZ="",BZQJ="",FTJE="";
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	sql
	.append("SELECT d.SFYZ,d.FTBL,b.CREATEDATE,b.FTJE,d.CSDS,d.CSSYTIME,to_char(d.ZZSL,'fm9999999990.00') ZZSL,B.STARTTIME_C,B.ENDTIME_C, B.SQDS_C,B.BQDS_C, B.DIANLIANG_C,B.DAYNUM_C,B.RJYDL_C,B.ELECTRICFEE_ID ID,B.DIANBIAOID DIANBIAOID,z.shi SHICODE,z.xian XIANCODE,"
	+ "d.dbname,d.YDSX,B.CBZX CBZX,B.BGDL BGDL,B.DLPLS, B.RQPLS,B.PUEZHI PUEZHI,b.DBIMAGEPATH1 DBIMAGEPATH1,"
	+ "B.STARTTIME STARTTIME,B.ENDTIME ENDTIME,to_char(B.SQDS,'fm9999999990.00') SQDS,"
	+ "to_char(B.BQDS,'fm9999999990.00') BQDS,B.BEILV BEILV,to_char(B.DIANLIANG,'fm9999999990.00') DIANLIANG,"
	+ "to_char(B.ALLMONEY,'fm9999999990.00') ALLMONEY,to_char(B.DIANSUN,'fm9999999990.00') DIANSUN,"
	+ "to_char(B.MONEY,'fm9999999990.00') MONEY,to_char(B.TAX,'fm9999999990.00') TAX,to_char(B.SQDJ,'fm9999999990.00') SQDJ,"
	+ "to_char(B.PRICE,'fm9999999990.00') PRICE,B.FTXS FTXS,z.JZNAME JZNAME,"
	+ "(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.SHI ) as SHI,"
	+ "(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.XIAN) as XIAN,b.DAYNUM DAYNUM,b.RJYDL RJYDL,b.SQRJYDL SQRJYDL,b.ENTRYTIMEOLD ENTRYTIMEOLD,"
	+"d.ELECTRIC_SUPPLY_WAY FANGSHI, (select t.name from indexs t where t.code=d.ELECTRIC_SUPPLY_WAY and t.type='GDFS') as FANGSHINAME" 
	+ " FROM ELECTRICFEES B left join DIANBIAO d on b.DIANBIAOID = d.ID  "+
	"left join ZHANDIAN z on z.ID=d.ZDID "+
	"left join ACCOUNT a  on a.accountid=b.ENTRYPENSONNEL "	+
	"where 1=1 and B.ELECTRICFEE_ID = "+ID+
	"  ORDER BY B.DIANBIAOID");

	System.out.println("审批时查寻报账sql：" + sql);
	db.connectDb();
 	DecimalFormat df1 = new DecimalFormat("0.00");
	rs = db.queryAll(sql.toString());
	while (rs.next()) {
	//18-3-22liushuo
	SFYZ=rs.getString("SFYZ")==null?"":rs.getString("SFYZ");//税赋因子
 		FTJE=rs.getString("FTJE")==null?"":rs.getString("FTJE");//分摊金额
 		BZQJ=rs.getString("CREATEDATE")==null?"":rs.getString("CREATEDATE");//报账区间
 		FTBL=rs.getString("FTBL")==null?"":rs.getString("FTBL");//分摊比例
		TAX=rs.getString("TAX")==null?"":rs.getString("TAX");//增值税额
		zzsl=rs.getString("ZZSL")==null?"":rs.getString("ZZSL");//增值税率
		STARTTIME_C=rs.getString("STARTTIME_C")==null?"":rs.getString("STARTTIME_C");	//手机抄表开始日期
 		ENDTIME_C=rs.getString("ENDTIME_C")==null?"":rs.getString("ENDTIME_C");			//手机抄表结束日期
 		SQDS_C=rs.getString("SQDS_C")==null?"":rs.getString("SQDS_C");					//手机抄表上期读数
 		BQDS_C=rs.getString("BQDS_C")==null?"":rs.getString("BQDS_C");					//手机抄表本期读数
 		DIANLIANG_C=rs.getString("DIANLIANG_C")==null?"":rs.getString("DIANLIANG_C");	//手机抄表电量
 		DAYNUM_C=rs.getString("DAYNUM_C")==null?"":rs.getString("DAYNUM_C");			//手机抄表用电天数
 		RJYDL_C=rs.getString("RJYDL_C")==null?"":rs.getString("RJYDL_C");				//手机抄表日均用电量
 		DLPLS=rs.getString("DLPLS")==null?"":rs.getString("DLPLS");						//电量偏离数
 		RQPLS=rs.getString("RQPLS")==null?"":rs.getString("RQPLS");						//日期偏离数
		SHICODE = rs.getString("SHICODE") == null ? "" : rs.getString("SHICODE");
		XIANCODE = rs.getString("XIANCODE") == null ? "" : rs.getString("XIANCODE");
		DLPLS=rs.getString("DLPLS")==null?"":rs.getString("DLPLS");						//电量偏离数
 		RQPLS=rs.getString("RQPLS")==null?"":rs.getString("RQPLS");						//日期偏离数
 		
		DIANBIAOID = rs.getString("DIANBIAOID") == null ? "" : rs.getString("DIANBIAOID");
	  	ydsx = rs.getString("YDSX") == null ? "" : rs.getString("YDSX");
		DBNAME = rs.getString("DBNAME") == null ? "" : rs.getString("DBNAME");
		CBZX = rs.getString("CBZX") == null ? "" : rs.getString("CBZX");
		STARTTIME = rs.getString("STARTTIME") == null ? "" : rs
		.getString("STARTTIME");
		ENDTIME = rs.getString("ENDTIME") == null ? "" : rs
		.getString("ENDTIME");
		SQDS = rs.getString("SQDS") == null ? "" : rs.getString("SQDS");
		BQDS = rs.getString("BQDS") == null ? "" : rs.getString("BQDS");
		BEILV = rs.getString("BEILV") == null ? "" : rs
		.getString("BEILV");
		DIANLIANG = rs.getString("DIANLIANG") == null ? "" : rs
		.getString("DIANLIANG");
		ALLMONEY = rs.getString("ALLMONEY") == null ? "" : rs
		.getString("ALLMONEY");
		DIANSUN = rs.getString("DIANSUN") == null ? "" : rs
		.getString("DIANSUN");
		MONEY = rs.getString("MONEY") == null ? "" : rs
		.getString("MONEY");
		SQDJ = rs.getString("SQDJ") == null ? "" : rs.getString("SQDJ");
		PRICE = rs.getString("PRICE") == null ? "" : rs
		.getString("PRICE");
		Double cny = Double.parseDouble(PRICE);
		DecimalFormat df = new DecimalFormat("0.0000");   
		String CNY = df.format(cny);
		FTXS = rs.getString("FTXS") == null ? "" : rs.getString("FTXS");

		SHI = rs.getString("SHI") == null ? "" : rs.getString("SHI");
		ZDNAME = rs.getString("JZNAME") == null ? "" : rs
		.getString("JZNAME");
		XIAN = rs.getString("XIAN") == null ? "" : rs.getString("XIAN");
		TIANSHU = rs.getString("DAYNUM") == null ? "" : rs
		.getString("DAYNUM");
		RJYDL = rs.getString("RJYDL") == null ? "" : rs
		.getString("RJYDL");
		SQRJYDL = rs.getString("SQRJYDL") == null ? "" : rs
		.getString("SQRJYDL");
		FANGSHI = rs.getString("FANGSHI") == null ? "" : rs
		.getString("FANGSHI");
		FANGSHINAME=rs.getString("FANGSHINAME")==null?"":rs.getString("FANGSHINAME");
		PUEZHI=rs.getString("PUEZHI")==null?"":rs.getString("PUEZHI");
		DBIMAGEPATH1=rs.getString("DBIMAGEPATH1")==null?"":rs.getString("DBIMAGEPATH1");
 		BGDL=rs.getString("BGDL")==null?"":rs.getString("BGDL");
 		if(!BGDL.equals("") && !DIANLIANG.equals("")){
 			bgpll = df1.format((Double.valueOf(DIANLIANG).doubleValue() - Double.valueOf(BGDL).doubleValue() )/Double.valueOf(BGDL).doubleValue());
 		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		java.util.Date STARTTIMEDate=null;
		java.util.Date ENDTIMEDate=null;
		if(STARTTIME!=null && !STARTTIME.equals("")){
	STARTTIMEDate=sdf.parse(STARTTIME);
		}
		if(ENDTIME!=null && !ENDTIME.equals("")){
	ENDTIMEDate=sdf.parse(ENDTIME);
		}
		java.util.Date STARTTIMEDate_C=null;
		if(STARTTIME_C!=null && !STARTTIME_C.equals("")){
	STARTTIMEDate_C=sdf.parse(STARTTIME_C);
		}
		java.util.Date ENDTIMEDate_C=null;
		if(ENDTIME_C!=null && !ENDTIME_C.equals("")){
	ENDTIMEDate_C=sdf.parse(ENDTIME_C);
		}
		if(STARTTIMEDate!=null){
	STARTTIMEStr = sdf.format(STARTTIMEDate); 
		}
		if(ENDTIMEDate!=null){
	ENDTIMEStr = sdf.format(ENDTIMEDate); 
		}
		if(STARTTIMEDate_C!=null){
	STARTTIMEStr_C = sdf.format(STARTTIMEDate_C); 			//手机抄表		
		}
		if(ENDTIMEDate_C!=null){
	ENDTIMEStr_C = sdf.format(ENDTIMEDate_C); 				//手机抄表	
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报账审批</title>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
		</script>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
		</script>
<!--[if gt IE 8]><!-->
<script type="text/javascript"
	src="<%=path%>/javascript/jquery-3.3.1.min.js"></script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
<!--<![endif]-->
<!--[if lte IE 8]>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/console.js"></script>
<![endif]-->
</script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js">
		</script>
<script type="text/javascript"
	src="<%=path%>/javascript/PopupCalendar.js">
		</script>
<script type="text/javascript"
	src="<%=path%>/javascript/PopupCalendar_ny.js">
		</script>

<script type="text/javascript">
var path = '<%=path%>';
</script>
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
						<div class="tit01">
							报账审批：<%=rolename%></div>
						<div class="content01_1">
							<table style="width: 100%;" border="0" id="tableStr"
								cellpadding="0" cellspacing="0" class="tb_select">
								<tr bgcolor="F9F9F9">
									<td height="19" colspan="8"><img src="../../images/v.gif"
										width="15" height="15" /> 电表信息</td>
								</tr>
								<tr>
									<td align="right" width="100px">主电表</td>
									<td width="100px"><input type="text" name="DBNAME"
										id="DBNAME" disabled="disabled" value="<%=DBNAME%>"
										style="box-sizing: border-box; width: 130px" /></td>
									<td align="right" width="100px">所属局站</td>
									<td width="100px"><input type="text" name="ZDNAME"
										id="ZDNAME" disabled="disabled" value="<%=ZDNAME%>"
										style="box-sizing: border-box; width: 130px" /></td>
									<td align="right" width="100px">所属地市</td>
									<td width="100px"><input type="text" name="SHI" id="SHI"
										disabled="disabled" value="<%=SHI%>"
										style="box-sizing: border-box; width: 130px" /></td>
									<td align="right" width="100px">所属县区</td>
									<td width="100px"><input type="text" name="XIAN" id="XIAN"
										disabled="disabled" value="<%=XIAN%>"
										style="box-sizing: border-box; width: 130px" /></td>
								</tr>
								<tr>

									<td align="right" width="100px">供电方式</td>
									<td width="100px"><input type="text" name="FANGSHINAME"
										id="FANGSHINAME" disabled="disabled" value="<%=FANGSHINAME%>"
										style="box-sizing: border-box; width: 130px" /></td>

								</tr>
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
										<%if(STARTTIMEStr_C!=""){%> disabled="disabled" <%}%>
										value="<%=STARTTIMEStr_C%>" /></td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>手机抄表结束日期</td>
									<td><input type="text" onchange="changeSJ()"
										style="box-sizing:border-box;width:130px" class="Wdate"
										name="ENDTIME_C" id="ENDTIME_C"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
										<%if(ENDTIMEStr_C!=""){%> disabled="disabled" <%}%>
										value="<%=ENDTIMEStr_C%>" /></td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>手机抄表上期读数</td>
									<td><input type="text" name="SQDS_C" id="SQDS_C"
										onchange="changeSJ()"
										style="box-sizing:border-box;width:130px" title="必填项"
										<%if(SQDS_C!=""){%> disabled="disabled" <%}%>
										value="<%=SQDS_C%>" />
									</td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>手机抄表本期读数</td>
									<td><input type="text" name="BQDS_C" id="BQDS_C"
										onchange="changeSJ()"
										style="box-sizing:border-box;width:130px" title="必填项"
										<%if(SQDS_C!=""){%> disabled="disabled" <%}%>
										value="<%=BQDS_C%>" />
									</td>
								</tr>
								<tr>
									<td align="right" width="100px">手机抄表用电量</td>
									<td><input type="text" name="DIANLIANG_C" id="DIANLIANG_C"
										onchange="changeSJ()" disabled="disabled"
										value="<%=DIANLIANG_C%>"
										style="box-sizing:border-box;width:130px" />
									</td>
									<td align="right" width="100px">手机抄表用电天数</td>
									<td><input type="text" name="TIANSHU_C" id="TIANSHU_C"
										onchange="changeSJ()" disabled="disabled"
										value="<%=DAYNUM_C%>"
										style="box-sizing:border-box;width:130px" />
									</td>
									<td align="right" width="100px">手机抄表日均用电量</td>
									<td><input type="text" name="RJYDL_C" id="RJYDL_C"
										disabled="disabled" value="<%=RJYDL_C%>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" />
									</td>
								</tr>

								<tr id="sa" bgcolor="F9F9F9">
									<td height="19" colspan="8"><img src="../../images/v.gif"
										width="15" height="15" /> 电费信息</td>
								</tr>
								<tr id="sa1">
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>开始日期</td>
									<td><input type="text" id="STARTTIME"
										onchange="changeSJ()"
										style="box-sizing:border-box;width:130px" class="Wdate"
										name="STARTTIME" disabled="disabled"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
										value="<%=STARTTIMEStr%>" /></td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>结束日期</td>
									<td><input type="text" onchange="changeSJ()"
										style="box-sizing:border-box;width:130px" class="Wdate"
										name="ENDTIME" id="ENDTIME"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
										value="<%=ENDTIMEStr%>" <%if(!bo.equals("1")){%>
										disabled="disabled" <%}%> /></td>
									<td align="right" width="100px">用电天数</td>
									<td><input type="text" name="TIANSHU"
										onchange="changeSJ()" disabled="disabled" id="TIANSHU"
										value="<%=TIANSHU%>" style="box-sizing:border-box;width:130px" />
									</td>
									<td align="right" width="100px">倍率</td>
									<td><input type="text" name="BEILV" disabled="disabled"
										id="BEILV" value="<%=BEILV%>"
										style="box-sizing:border-box;width:130px" /></td>
								</tr>
								<tr id="sa2">
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>上期读数</td>
									<td><input type="text" name="SQDS" id="SQDS"
										onchange="changeSJ()" value="<%=SQDS%>" disabled="disabled"
										style="box-sizing:border-box;width:130px" title="必填项" /></td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>本期读数</td>
									<td><input type="text" name="BQDS" id="BQDS"
										value="<%=BQDS%>" onchange="changeSJ()"
										style="box-sizing:border-box;width:130px" title="必填项"
										<%if(!bo.equals("1")){%> disabled="disabled" <%}%> />
									</td>
									<td align="right" width="100px">用电量</td>
									<td><input type="text" name="DIANLIANG" id="DIANLIANG"
										onchange="changeSJ()" disabled="disabled"
										value="<%=DIANLIANG%>"
										style="box-sizing:border-box;width:130px" /></td>
									<td align="right" width="100px" id="diansunTd">其中电损</td>
									<td><input type="text" name="DIANSUN" id="DIANSUN"
										onchange="changeSJ()" value="<%=DIANSUN%>" disabled="disabled"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>

								</tr>
								<tr id="sa3">
									<td align="right" width="100px">上期单价</td>
									<td><input type="text" name="SQDJ" id="SQDJ"
										value="<%=SQDJ%>" disabled="disabled"
										style="box-sizing:border-box;width:130px" title="必填项" /></td>
									<td align="right" width="100px">单价</td>
									<td><input type="text" name="PRICE" disabled="disabled"
										id="PRICE" onchange="changeSJ()" value="<%=PRICE+"00"%>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>缴费金额</td>
									<td><input type="text" name="ALLMONEY" id="ALLMONEY"
										value="<%=ALLMONEY%>" oninput="shuie()"
										style="box-sizing:border-box;width:130px" title="必填项"
										<%if(!bo.equals("1")){%> disabled="disabled" <%}%> />
									</td>
									<!-- 税额 2018年3月9日 -gcl -->
									<td align="right" width="100px"><span
										style="color: #FF0000; font-weight: bold">*</span>税额</td>
									<td><input type="text" name="TAX" id="TAX"
										value="<%=TAX%>" style="box-sizing:border-box;width:130px"
										title="必填项" disabled="disabled" /> <input type="hidden"
										id="zzsl" name="zzsl" value="<%=zzsl%>>" />
									</td>

									<input type="hidden" value="" id="saveState" name="saveState" />

									<td align=center valign=middlle><input id="butue1"
										onclick="yincang1()" style="color: #FF0000; font-weight: bold"
										type="button" value="显示其他信息" />
									</td>
								</tr>
								<tr id="as4">
									<td align="right" width="100px">报账区间(月)</td>
									<td><input type="text" name="BZQJ" id="BZQJ"
										disabled="disabled" value="<%=BZQJ%>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
									<td align="right" width="100px">分摊金额</td>
									<td><input type="text" name="FTJE" id="FTJE"
										disabled="disabled" value="<%=FTJE%>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
									<td align="right" width="100px"><span
										style="color: rgb(255, 0, 0); font-weight: bold;">*</span>税负因子</td>
									<td><input type="text" name="SFYZ" id="SFYZ"
										disabled="disabled" value="<%=SFYZ%>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
									<td align="right" width="100px"><span
										style="color: rgb(255, 0, 0); font-weight: bold;">*</span>分摊电量</td>
									<td><input type="text" name="FTDL" id="FTDL"
										disabled="disabled" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" />
									</td>
								</tr>
								<tr>
									<td align="right" width="100px"><span
										style="color: rgb(255, 0, 0); font-weight: bold;">*</span>分摊比例</td>
									<td><input type="text" name="FTBL" id="FTBL"
										disabled="disabled" value="<%=FTBL %>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
										<td align="right" width="100px"><span
										style="color: rgb(255, 0, 0); font-weight: bold;">*</span>不含税金额</td>
									<td><input type="text" name="BHSJE" id="BHSJE"
										disabled="disabled" value=""
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>

								</tr>

								<!-- 报账信息添加 2018/1/18 -->
								<tr id="qw" bgcolor="F9F9F9">
									<td height="19" colspan="8"><img src="../../images/v.gif"
										width="15" height="15" /> 数据分析信息</td>
								</tr>
								<tr id="qw1">
									<td align="right" width="100px">电量偏离数</td>
									<td><input type="hidden" name="LiLunDianLiang"
										id="LiLunDianLiang" disabled="disabled" onchange="changeSJ()"
										value="" style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /> <input type="text"
										name="DianLiangPianLiShu" id="DianLiangPianLiShu"
										disabled="disabled" onchange="changeSJ()" value="<%=DLPLS%>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
									<td align="right" width="100px">日期偏离数</td>
									<td><input type="text" name="RiQiPianLiShu"
										id="RiQiPianLiShu" disabled="disabled" onchange="changeSJ()"
										value="<%=RQPLS%>" style="box-sizing:border-box;width:130px"
										title="必填项" maxlength="30" /></td>
									<td align="right" width="100px">PUE值</td>
									<td><input type="text" name="PUEZHI" id="PUEZHI"
										disabled="disabled" value="<%=PUEZHI%>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
									<td align="right" width="100px">本月全市平均PUE值</td>
									<td><input type="text" name="AVGPUEZHI" id="AVGPUEZHI"
										disabled="disabled" value="<%=AVGPUEZHI%>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
								</tr>
								<!-- END -->
								<tr id="qw2">
									<td align="right" width="100px">电费标杆</td>
									<td><input type="text" name="BGDL" id="BGDL"
										disabled="disabled" value="<%=BGDL%>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
									<td align="right" width="100px">标杆偏离率</td>
									<td><input type="text" name="BGPLL" id="BGPLL"
										disabled="disabled" value="<%=bgpll%>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
									<td align="right" width="100px"><a id="showPUE" href="#"
										onclick="showPUE()">查看最近PUE曲线</>
											<td align="right" width="100px"><a id="showImg" href="#"
												onclick="showImg()">查看手机抄表信息</> 
								</tr>
								<tr id="qw3">
									<td align="right" width="100px">日均用电量</td>
									<td><input type="text" name="RJYDL" id="RJYDL"
										disabled="disabled" value="<%=RJYDL%>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
									<td align="right" width="100px">上期日均用电量</td>
									<td><input type="text" name="SQRJYDL" disabled="disabled"
										id="SQRJYDL" value="<%=SQRJYDL%>"
										style="box-sizing:border-box;width:130px" title="必填项"
										maxlength="30" /></td>
									<td align=center valign=middlle><input id="butue"
										onclick="yincang()" style="color: #FF0000; font-weight: bold"
										type="button" value="显示其他信息" />
									</td>
								</tr>
								<tr bgcolor="F9F9F9">
									<td height="19" colspan="8"><img src="../../images/v.gif"
										width="15" height="15" /> 申请/审核信息</td>
								</tr>

								<tr>
									<td align="right" width="100px">报送人</td>
									<td><input type="text" name="ACCNAME" id="ACCNAME"
										disabled="disabled" value="<%=ACCNAME%>"
										style="box-sizing:border-box;width:130px" /></td>
									<td align="right" width="100px">报送时间</td>
									<td><input type="text" name="ACCTIME" id="ACCTIME"
										disabled="disabled" value="<%=ACCTIME%>"
										style="box-sizing:border-box;width:130px" /></td>
								</tr>
								<%
									//if(!bo.equals("1")){
								%>
								<tr>
									<td colspan="8">
										<div>审核意见：</div>
										<div style="float:left;">
											<textarea id="txt" rows="5" cols="50" name="opinion"></textarea>
										</div>
										<div style="float:left; margin-left: 10px;">
											<%
												CommonBean cobean = new CommonBean();
																																	List<ZhandianBean> YJlist=cobean.getyijianshenhe();
																																	if(YJlist != null){
																																	for(int i=0;i<YJlist.size();i++){
											%>
											<input type="checkbox" name="checkbox" onclick="checkedAll()"
												data-animal-type="<%=YJlist.get(i).getLATITUDE()%>"
												class="grp1" id="cf" /><%=YJlist.get(i).getLATITUDE()%><br />
											<%
												}
																												//	}
											%>

										</div></td>
								</tr>
								<tr>
									<td colspan="8">
										<div>
											当前节点：<%=actionname%></div></td>
								</tr>


								<tr>
									<td colspan="8">
										<div>
											<label> <input type="radio" name="shenhe"
												onchange="tongyi()" value="0" /> 同意 </label> <label> <input
												type="radio" name="shenhe" onchange="tongyi()" value="1" />
												不同意 </label>
										</div></td>
								</tr>
								<%
									}
								%>
								<tr id="tongyiTr" style="display: none">
									<td colspan="8">
										<div>
											下一节点：<%=nextaction%></div>
										<div>
											执行人：
											<%
											if(nextactionId!=null&&!nextactionId.equals("")){
												String agcode = "";
												if (nextaction.indexOf("市") != -1) {
													agcode = SHICODE;
												} else if (nextaction.indexOf("县") != -1) {
													agcode = XIANCODE;
												}
												ArrayList acclist = new ArrayList();
												//if (nextsteporderno.equals("2")) {//step2
													if ("市公司电费管理员审核".equals(nextaction)) {//step2
													if (!ydsx.isEmpty() && ydsx.equals("ydsx11")) {//办公
														acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "办公");
													} else if (!ydsx.isEmpty() && ydsx.equals("ydsx12")) {//营业厅
														acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "营业厅");
													} else if (!ydsx.isEmpty() && ydsx.equals("ydsx15")) {//其他

													} else {//生产
														acclist = flowBean.getAccountLikeRolename(nextactionId, agcode, "生产");
													}

												} //else if (nextsteporderno.equals("3")) {//step3
												else if ("市级主任审核".equals(nextaction)) {//step3
													if (!ydsx.isEmpty() && ydsx.equals("ydsx11")) {//办公
														acclist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(),agcode, "%市综合部主任%");
													} else if (!ydsx.isEmpty() && ydsx.equals("ydsx12")) {//营业厅
														acclist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(),agcode, "%市销售部主任%");
													} 

													 else {//生产
														acclist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(),agcode, "%市运维主任%");
													}

				
												}else {
													acclist = bean.getAccountByActionId(nextactionId, ID, agcode, tasktype);
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
													}
															}
											%>


										</div></td>
								</tr>
								<tr id="bohuiTr" style="display: none">
									<td colspan="8">
										<%
										   String action = actionname.toString();
										System.out.println("---判断是否包含---"+action.contains("市电费管理员"));
											//if (steporderno.equals("2")) {//step2
												if ("市公司电费管理员审核".equals(action)) {//step2
												%>
										%>
										<div>上一节点：发起人</div>
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
												}else{ 
											
											%>	
												
										
											
										<div>
											上一节点：<%=lastactionname%>
										</div>
										<div>
											执行人：
											<%
											if(lastactionname.equals("发起人")){ 
												bohuijd="0";
												String accName=bean.getAuditorName(applyUserId,db);
										%>
											<label>
												<input type="radio" name="bohui" value="0" checked /><%=accName%></label>

											<%
												}else{
											%>
											<%
												String actionNo=bean.getStepNo(currentid,db);
													int stepNo = Integer.parseInt(actionNo);
													String nextNo = (stepNo -1) + "";
													String nextStep = bean.getNextStep(flowId, nextNo);
													String acc=bean.getAuditor(workFlowId,nextStep,db);
													String accName=bean.getAuditorName(acc,db);
													bohuijd=nextStep;
											%>
											<label>
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
													li = bean.getApproverByWorkFlowId(workFlowId);
													if (li != null) {
														String accountname2 = "", checktime = "", state = "", opinion = "";
														int num = 1;
														int cscount = ((Integer) li.get(0)).intValue();
														for (int i = cscount; i < li.size() - 1; i += cscount) {
															accountname2 = (String) li
																	.get(i + li.indexOf("ACCOUNTNAME"));
															checktime = (String) li.get(i + li.indexOf("CHECKTIME"));
															state = (String) li.get(i + li.indexOf("STATE"));
															opinion = (String) li.get(i + li.indexOf("OPINION"));
															if(opinion==null){
																opinion="";
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
											</div></td>
									</tr>
									<tr>
										<td align="left" colspan="6" height="60px"><font
											style="color: #4ad2ff;">
												※普通表(非直供电):总金额-((截止度数-起始度数)*倍率*单价)=电损金额 </br>
												※电业表(直供电表):总金额/((截止度数-起始度数)*倍率+其中电损)=单价 </font></td>
										<td align="right" colspan="2" height="60px"><input
											onclick="tijiao('<%=ID%>')" type="button" class="btn_c1"
											id="button2" value="提交" /> &nbsp; <input onclick="fanhui()"
											type="button" class="btn_c1" id="button2" value="取消" />
											&nbsp;</td>
									</tr>
							</table>
						</div>
					</div></td>
			</tr>

		</table>
		<input type="hidden" value="<%=FANGSHI%>" id="FANGSHI" name="FANGSHI" />
		<input type="hidden" value="<%=ID%>" id="ID" name="ID" /> <input
			type="hidden" value="<%=DIANBIAOID%>" id="DIANBIAOID"
			name="DIANBIAOID" /> <input type="hidden" value="<%=DBIMAGEPATH1%>"
			id="DBIMAGEPATH1" name="DBIMAGEPATH1" /> <input type="hidden"
			name="currentid" value="<%=currentid%>" /> <input type="hidden"
			name="nextactionId" value="<%=nextactionId%>" /> <input
			type="hidden" name=workFlowId value="<%=workFlowId%>" /> <input
			type="hidden" name="flowId" value="<%=flowId%>" /> <input
			type="hidden" name="applyUserId" value="<%=applyUserId%>" /> <input
			type="hidden" name="lastaction" value="<%=lastaction%>" /> <input
			type="hidden" name="tasktype" value="<%=tasktype%>" /> <input
			type="hidden" name="bohuijd" value="<%=bohuijd%>" />
	</form>
	<script type="text/javascript">

window.onload = function(){
shuie();
changeSJ();
rolen='<%=rolename%>';
var btn1=document.getElementById("butue1");
var btn=document.getElementById("butue");
var box1=document.getElementById("sa");
var box2=document.getElementById("sa1");
var box3=document.getElementById("sa2");
var box4=document.getElementById("sa3");
var bax1=document.getElementById("qw");
var bax2=document.getElementById("qw1");
var bax3=document.getElementById("qw2");
var bax4=document.getElementById("qw3");
if(rolen.indexOf("市")>-1){
box1.style.display='none';btn.value="显示其他信息";
box2.style.display='none';box3.style.display='none';
box4.style.display='none';btn1.style.display='none';
}else{
bax1.style.display='none';btn1.value="显示其他信息";
 bax2.style.display='none'; bax3.style.display='none';
  bax4.style.display='none';btn.style.display='none';
}
}
function yincang(){
var btn1=document.getElementById("butue");
var box1=document.getElementById("sa");
var box2=document.getElementById("sa1");
var box3=document.getElementById("sa2");
var box4=document.getElementById("sa3");
if(btn1.value=="隐藏其他信息"){
box1.style.display='none';btn1.value="显示其他信息";box2.style.display='none';box3.style.display='none';box4.style.display='none';
 }else{
 box1.style.display='';btn1.value="隐藏其他信息"; box2.style.display=''; box3.style.display=''; box4.style.display='';
}
}
function yincang1(){
var btn1=document.getElementById("butue1");
var box1=document.getElementById("qw");
var box2=document.getElementById("qw1");
var box3=document.getElementById("qw2");
var box4=document.getElementById("qw3");
if(btn1.value=="隐藏其他信息"){
box1.style.display='none';btn1.value="显示其他信息";box2.style.display='none';box3.style.display='none';box4.style.display='none';
 }else{
 box1.style.display='';btn1.value="隐藏其他信息"; box2.style.display=''; box3.style.display=''; box4.style.display='';
}
}
 var path = '<%=path%>';
/*$('input.grp1').click(function(){
	if(this.checked){
	 var obj = document.getElementById("txt").value;
	var setrem1 =this.getAttribute("data-animal-type");
	        obj.append(setrem1);
	}else{
	var obj = document.getElementById("txt").value; 
	var setrem1 =this.getAttribute("data-animal-type");
	obj=obj.replace(setrem1,'');
	document.getElementById("txt").value=obj;
	}
});*/

function checkedAll(){
	var checkbox = document.getElementsByName("checkbox");
	var checkedVals = new Array();
	for(var i=0; i<checkbox.length; i++){
	     if(checkbox[i].checked){
	     var val = checkbox[i].getAttribute("data-animal-type");
	        checkedVals.push(val);
	     }
	}
	document.getElementById("txt").value=checkedVals;
}


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
				var bh ='<%=bo%>';
				if (bh == 1) {
				if (checkNotSelected(document.form1.DIANBIAOID, "主电表")
						&& checkNotnull(document.form1.STARTTIME, "开始日期")
						&& checkNotnull(document.form1.ENDTIME, "结束日期")
						&& checkNotnull(document.form1.SQDS, "上期读数")
						&& checkNotnull(document.form1.BQDS, "本期读数")
						&& checkNotnull(document.form1.SQDJ, "上期单价")
						&& checkNotnull(document.form1.PRICE, "单价")
						&& checkNotnull(document.form1.TAX, "税额")
						&& checkNotnull(document.form1.DIANSUN, "其中电损或电损金额")) {
					var SQDS_check = $("#SQDS").val();

					var BQDS_check = $("#BQDS").val();
					var DIANLIANG_check = $("#DIANLIANG").val();
					var ALLMONEY_check = $("#ALLMONEY").val();
					var DIANSUN_check = $("#DIANSUN").val();
					var SQDJ_check = $("#SQDJ").val();
					var PRICE_check = $("#PRICE").val();

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
							alert("结束时间不能小于开始时间！");
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
					//gcl
					document.form1.action = path
							+ "/servlet/workflow?action=adjust&taskId="
							+ taskId + "&TAX=" + TAX;
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
					var auditorid = $('input:radio[name="auditorid"]:checked')
							.val();
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

				document.form1.action = path
						+ "/servlet/workflow?action=complete&type=" + shenhe
						+ "&taskId=" + taskId;
				document.form1.submit();
			}

		}
		function fanhui() {
			window.close();
		}

		function saveBz() {
			if (checkNotSelected(document.form1.DIANBIAOID, "主电表")
					&& checkNotnull(document.form1.STARTTIME, "开始日期")
					&& checkNotnull(document.form1.ENDTIME, "结束日期")
					&& checkNotnull(document.form1.SQDS, "上期读数")
					&& checkNotnull(document.form1.BQDS, "本期读数")
					&& checkNotnull(document.form1.SQDJ, "上期单价")
					&& checkNotnull(document.form1.PRICE, "单价")
					&& checkNotnull(document.form1.ALLMONEY, "电费金额")
					&& checkNotnull(document.form1.DIANSUN, "其中电损或电损金额")) {
				var SQDS_check = $("#SQDS").val();

				var BQDS_check = $("#BQDS").val();
				var DIANLIANG_check = $("#DIANLIANG").val();
				var ALLMONEY_check = $("#ALLMONEY").val();
				var DIANSUN_check = $("#DIANSUN").val();
				var SQDJ_check = $("#SQDJ").val();
				var PRICE_check = $("#PRICE").val();

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
						alert("结束时间不能小于开始时间！");
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
				if (BQDS_check * 1 < SQDS_check * 1) {
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
						&& !ismoney(SQDJ_check)) {
					alert("上期单价必须为金额！");
					return false;
				}
				if (PRICE_check != null && PRICE_check != ""
						&& !ismoneys(PRICE_check)) {
					alert("单价必须为金额！");
					return false;
				}
				addbaozhang();
			}
			;
		}
		function addbaozhang() {
			if (confirm("您将要添加报账！确认信息正确！")) {
				$('#STARTTIME').attr("disabled", false);
				$('#SQDS').attr("disabled", false);
				$('#SQDJ').attr("disabled", false);
				$('#PRICE').attr("disabled", false);
				$('#DIANLIANG').attr("disabled", false);
				$('#ALLMONEY').attr("disabled", false);
				$('#TIANSHU').attr("disabled", false);
				$('#RJYDL').attr("disabled", false);
				$('#SQRJYDL').attr("disabled", false);
				document.form1.action = path
						+ "/servlet/zhandian?action=updateBaozhang";
				document.form1.submit();
				showdiv("请稍等..............");
			}
		}
		function isNaN2(val) {
			return val.match(new RegExp("^[0-9]+$"));
		}
		function ismoney(str) {
			if (/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/
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
		function showImg() {
			var s = document.form1.DBIMAGEPATH1.value;
			if (s == "") {
				alert("无图片!");
			} else {
				window
						.open(
								"showImg.jsp?path=" + s,
								"newwindowImg",
								"height=570, width=380, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=no");
			}
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
			var DIANLIANG =$("#DIANLIANG").val();
			var FANGSHI_ch = $("#FANGSHI").val();
			var BEILV_ch = $("#BEILV").val();
			var FTBL_ch = $("#FTBL").val();
			var BHSJE_ch = $("#BHSJE").val();
			var FTJE_ch = $("#FTJE").val();
			var SFYZ_ch = $("#SFYZ").val();
			var BZQJ_ch = $("#BZQJ").val();
		if(FTBL_ch!="" && ALLMONEY_ch!=""){
			$("#BHSJE").val(((Number(FTBL_ch)/100)*Number(ALLMONEY_ch)).toFixed(2));
			}
		if(FTBL_ch!=""&&DIANLIANG!=""){
			$("#FTDL").val(((Number(FTBL_ch)/100)*Number(DIANLIANG)).toFixed(2));
			}
		if(ALLMONEY_ch!=""&&FTBL_ch!=""){
			$("#FTJE").val(((Number(FTBL_ch)/100)*Number(ALLMONEY_ch)*(1+Number(SFYZ_ch))).toFixed(2));
		}
		if(BEILV_ch==""){
			BEILV_ch=1;
			}
		if(ALLMONEY_ch!="" && FTBL_ch!="" && SFYZ_ch!=""){
			$("#FTJE").val(
						(((Number(ALLMONEY_ch) * (Number(FTBL_ch)/100)).toFixed(2))*((Number(SFYZ_ch)/100)+1)).toFixed(2));
			}
			//电量赋值
			if (BQDS_ch != "" && SQDS_ch != "") {
				$("#DIANLIANG").val(
						((BQDS_ch * 1 - SQDS_ch * 1) * BEILV_ch).toFixed(2));
				//alert(((BQDS_ch*1 - SQDS_ch*1)*BEILV_ch).toFixed(2));
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
			//END $("#TIANSHU").val(DateDiff(ENDTIME_ch,STARTTIME_ch));
			if (dianbiaoId != "0" && BQDS_ch != "" && SQDS_ch != ""
					&& STARTTIME_ch != "" && ENDTIME_ch != "") {
			 	var dl = ((BQDS_ch * 1 - SQDS_ch * 1) * BEILV_ch).toFixed(2);
				var ts = DateDiff(ENDTIME_ch, STARTTIME_ch);
				$.ajax({
					type : "POST",//请求方式
					//   async: false,
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
							$("#PUEZHI").val(result);
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
							//    async: false,
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
										//var BGPLL = (((BQDS_ch * 1 - SQDS_ch * 1) * BEILV_ch) - result * 1)
										//		/ (ts * 1).toFixed(2);
										 var BGPLL = (((BQDS_ch * 1 - SQDS_ch * 1) * BEILV_ch) - result * 1)
												/ ((ts * 1)*(result * 1));
											BGPLL=(BGPLL*100).toFixed(2);
										$("#BGPLL").val(BGPLL);

									}

								}
							}
						});
			} else {
				$("#BGDL").val("");
				$("#BGPLL").val("");
			}
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
		//2018年3月9日 -gcl 根据金额判断是否更改
		function shuie() {
			//税额赋值-gcl
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
	</script>
</body>
</html>
