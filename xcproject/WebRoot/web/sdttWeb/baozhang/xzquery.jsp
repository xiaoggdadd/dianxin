<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.Double" %>
<%@ page import="com.noki.database.DataBase" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.noki.jizhan.SiteManage" %>
<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	Account account=(Account)session.getAttribute("account");
	String jzproperty=request.getParameter("jzproperty");//获取新站点的属性  
	String rolename = account.getRoleName();
	String accountname=account.getAccountName();
	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd");
	String entrytime=mat.format(new Date());
	System.out.println("系统时间"+entrytime+"  shengId:"+shengId+"  loginName:"+loginName+"  accountname:"+accountname+" jzproperty:"+jzproperty);
	
	String ID = request.getParameter("id");
	//报账没数据时过去电表的读数,时间和单价
	String CSDS = "",CSSYTIME = "",huizhuan="",maxds="";
	//修改报账添加（手机抄表开始时间，手机抄表结束时间，手机抄表上期读数，手机抄表本期读数，手机抄表电量，手机抄表用电天数，手机抄表日均用电量，电量偏离数，日期偏离数）
	String  ZZSL="",EAO_C="",STARTTIME_C="",ENDTIME_C="", SQDS_C="",BQDS_C="",DIANLIANG_C="",DAYNUM_C="",RJYDL_C="", DLPLS = "",RQPLS="";
	String DIANBIAOID="",DBNAME="",CBZX="",STARTTIME="",ENDTIME="",SQDS="",BQDS="",BEILV="",DIANLIANG="";
	String ALLMONEY="",DIANSUN="",MONEY="",TAX="",SQDJ="",PRICE="",FTXS="",STARTTIMEStr="",ENDTIMEStr="",DBIMAGEPATH1="",BGDL="",bgpll="";
	String  SHI="",ZDNAME="",XIAN="",TIANSHU="",RJYDL="",SQRJYDL="",FANGSHI="",FANGSHINAME="",STARTTIMEStr_C="",ENDTIMEStr_C="",PUEZHI="",AVGPUEZHI="";
	String XIANCODE="";
	String ydsx="";
	DataBase db = new DataBase();
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	sql.append("SELECT d.CSDS,d.YDSX,d.CSSYTIME,to_char(d.ZZSL,'fm9999999990.00') ZZSL,B.STARTTIME_C,B.EAO_C,B.ENDTIME_C, B.SQDS_C,B.BQDS_C, B.DIANLIANG_C,B.DAYNUM_C,B.RJYDL_C, B.DLPLS, B.RQPLS, B.ELECTRICFEE_ID ID,B.DIANBIAOID DIANBIAOID," +
			"(select d.dbname  from DIANBIAO d  where d.id = B.DIANBIAOID) as DBNAME," +
			"B.CBZX CBZX," +
			"B.STARTTIME STARTTIME,B.ENDTIME ENDTIME,to_char(B.SQDS,'fm9999999990.00') SQDS," +
			"to_char(B.BQDS,'fm9999999990.00') BQDS,B.BEILV BEILV,to_char(B.DIANLIANG,'fm9999999990.00') DIANLIANG," +
			"to_char(B.ALLMONEY,'fm9999999990.00') ALLMONEY,to_char(B.DIANSUN,'fm9999999990.00') DIANSUN," +
			"to_char(B.MONEY,'fm9999999990.00') MONEY,to_char(B.TAX,'fm9999999990.00') TAX,to_char(B.SQDJ,'fm9999999990.00') SQDJ," +
			"to_char(B.PRICE,'fm9999999990.00') PRICE,B.FTXS FTXS,z.JZNAME JZNAME," + 
			"(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.SHI ) as SHI," + 
			"(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.XIAN) as XIAN,z.XIAN as xiancode,b.DAYNUM DAYNUM,b.RJYDL RJYDL,b.SQRJYDL SQRJYDL," +
			"d.ELECTRIC_SUPPLY_WAY FANGSHI, (select t.name from indexs t where t.code=d.ELECTRIC_SUPPLY_WAY and t.type='GDFS') as FANGSHINAME,b.PUEZHI PUEZHI,b.DBIMAGEPATH1 DBIMAGEPATH1,B.BGDL BGDL ,d.hzstate hzstate,d.MAXDS MAXDS  " +
			" FROM ELECTRICFEES B left join DIANBIAO d on b.DIANBIAOID = d.ID  "+
			"left join ZHANDIAN z on z.ID=d.ZDID "+
			"left join ACCOUNT a  on a.accountid=b.ENTRYPENSONNEL "+
			"where 1=1 and B.ELECTRICFEE_ID = "+ID+
			"  ORDER BY B.DIANBIAOID");
	
	System.out.println("查看报账sql："+sql);
 	db.connectDb();
 	DecimalFormat df1 = new DecimalFormat("0.00");
 	rs=db.queryAll(sql.toString());
 	while(rs.next()){
 	
 		CSDS=rs.getString("CSDS")==null?"":rs.getString("CSDS");//初始读数
 		CSSYTIME=rs.getString("CSSYTIME")==null?"":rs.getString("CSSYTIME");//初始时间
 		
 		maxds=rs.getString("maxds")==null?"":rs.getString("maxds");//最大读数
 		huizhuan=rs.getString("hzstate")==null?"":rs.getString("hzstate");//回转状态
 		
	  	ydsx = rs.getString("YDSX") == null ? "" : rs.getString("YDSX");
 		TAX=rs.getString("TAX")==null?"":rs.getString("TAX");//税额
 		ZZSL=rs.getString("ZZSL")==null?"":rs.getString("ZZSL");//税率
 		EAO_C=rs.getString("EAO_C")==null?"":rs.getString("EAO_C");//手机标识符
 		STARTTIME_C=rs.getString("STARTTIME_C")==null?"":rs.getString("STARTTIME_C");	//手机抄表开始日期
 		ENDTIME_C=rs.getString("ENDTIME_C")==null?"":rs.getString("ENDTIME_C");			//手机抄表结束日期
 		SQDS_C=rs.getString("SQDS_C")==null?"":rs.getString("SQDS_C");					//手机抄表上期读数
 		BQDS_C=rs.getString("BQDS_C")==null?"":rs.getString("BQDS_C");					//手机抄表本期读数
 		DIANLIANG_C=rs.getString("DIANLIANG_C")==null?"":rs.getString("DIANLIANG_C");	//手机抄表电量
 		DAYNUM_C=rs.getString("DAYNUM_C")==null?"":rs.getString("DAYNUM_C");			//手机抄表用电天数
 		RJYDL_C=rs.getString("RJYDL_C")==null?"":rs.getString("RJYDL_C");				//手机抄表日均用电量
 		DLPLS=rs.getString("DLPLS")==null?"":rs.getString("DLPLS");						//电量偏离数
 		RQPLS=rs.getString("RQPLS")==null?"":rs.getString("RQPLS");						//日期偏离数
 		
 		DIANBIAOID=rs.getString("DIANBIAOID")==null?"":rs.getString("DIANBIAOID");
 		DBNAME=rs.getString("DBNAME")==null?"":rs.getString("DBNAME");
 		CBZX=rs.getString("CBZX")==null?"":rs.getString("CBZX");
 		STARTTIME=rs.getString("STARTTIME")==null?"":rs.getString("STARTTIME");
 		ENDTIME=rs.getString("ENDTIME")==null?"":rs.getString("ENDTIME");
 		SQDS=rs.getString("SQDS")==null?"":rs.getString("SQDS");
 		BQDS=rs.getString("BQDS")==null?"":rs.getString("BQDS");
 		BEILV=rs.getString("BEILV")==null?"":rs.getString("BEILV");
 		DIANLIANG=rs.getString("DIANLIANG")==null?"":rs.getString("DIANLIANG");
 		ALLMONEY=rs.getString("ALLMONEY")==null?"":rs.getString("ALLMONEY");
 		DIANSUN=rs.getString("DIANSUN")==null?"":rs.getString("DIANSUN");
 		MONEY=rs.getString("MONEY")==null?"":rs.getString("MONEY");
 		SQDJ=rs.getString("SQDJ")==null?"":rs.getString("SQDJ");
 		PRICE=rs.getString("PRICE")==null?"":rs.getString("PRICE");
 		FTXS=rs.getString("FTXS")==null?"":rs.getString("FTXS");
 		SHI=rs.getString("SHI")==null?"":rs.getString("SHI");
 		ZDNAME=rs.getString("JZNAME")==null?"":rs.getString("JZNAME");
 		XIAN=rs.getString("XIAN")==null?"":rs.getString("XIAN");
 		XIANCODE=rs.getString("XIANCODE")==null?"":rs.getString("XIANCODE");
 		TIANSHU=rs.getString("DAYNUM")==null?"":rs.getString("DAYNUM");
 		RJYDL=rs.getString("RJYDL")==null?"":rs.getString("RJYDL");
 		SQRJYDL=rs.getString("SQRJYDL")==null?"":rs.getString("SQRJYDL");
 		FANGSHI=rs.getString("FANGSHI")==null?"":rs.getString("FANGSHI");
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
		java.util.Date CSSYTIMEDate=null;
		
		if(STARTTIME!=null && !STARTTIME.equals("")){
			STARTTIMEDate=sdf.parse(STARTTIME);
		}
		if(ENDTIME!=null && !ENDTIME.equals("")){
			ENDTIMEDate=sdf.parse(ENDTIME);
		}
		//初始日期的转换
		if(CSSYTIME!=null && !CSSYTIME.equals("")){
			CSSYTIMEDate=sdf.parse(CSSYTIME);
		}
		
		java.util.Date STARTTIMEDate_C=null;
		if(STARTTIME_C!=null && !STARTTIME_C.equals("")){//手机
			STARTTIMEDate_C=sdf.parse(STARTTIME_C);
		}
		java.util.Date ENDTIMEDate_C=null;
		if(ENDTIME_C!=null && !ENDTIME_C.equals("")){//手机
			ENDTIMEDate_C=sdf.parse(ENDTIME_C);
		}

		
		if(STARTTIMEDate!=null){
			STARTTIMEStr = sdf.format(STARTTIMEDate); 
		}else{
			//为空
			if(CSSYTIMEDate!=null ){
			STARTTIMEStr = sdf.format(CSSYTIMEDate);
			}
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
 	SiteManage bean = new SiteManage();
 	  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
      String addtime =sdf2.format(new Date());
      
 	AVGPUEZHI = bean.getAnalysisByShi(SHI,addtime);
 	if(AVGPUEZHI==null || AVGPUEZHI.equals("") || AVGPUEZHI.equals("null")){
 		AVGPUEZHI="";
 	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>录入报账信息</title>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->

<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/PopupCalendar.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/PopupCalendar_ny.js"></script>

<script type="text/javascript">
var path = '<%=path%>';

</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="diaobiaoBean" scope="page" class="com.noki.jizhan.DianBiaoBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<jsp:useBean id="flowBean" scope="page" class="com.noki.mobi.flow.javabean.FlowBean">
</jsp:useBean>
<body>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
	
		<td width="12" ><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">录入报账信息</div>
				<div class="content01_1">
					<table style="width: 100%;"  border="0" id="tableStr"  cellpadding="0" cellspacing="0" class="tb_select" >
						<tr bgcolor="F9F9F9">
						<td height="19" colspan="8">
							<img src="../../images/v.gif" width="15" height="15" />
							电表信息</td>
						</tr>
					 <tr>
					 	<td align="right" width="100px">主电表</td>
					 	<td width="100px">
					         	<input type="text" name="DBNAME" id="DBNAME" disabled="disabled" value="<%=DBNAME %>"
									style="box-sizing: border-box; width: 130px" />
					       </td>
					       <td align="right" width="100px">所属局站</td>
						   <td width="100px"><input type="text" name="ZDNAME" id="ZDNAME" disabled="disabled" value="<%=ZDNAME %>"
									style="box-sizing: border-box; width: 130px" />
						   </td>
						   <td align="right" width="100px">所属地市</td>
						   <td width="100px"><input type="text" name="SHI" id="SHI" disabled="disabled" value="<%=SHI %>"
									style="box-sizing: border-box; width: 130px" />
						   </td>
						   <td align="right" width="100px">所属县区</td>
						   <td width="100px"><input type="text" name="XIAN" id="XIAN" disabled="disabled" value="<%=XIAN %>"
									style="box-sizing: border-box; width: 130px" />
						   </td>
					    </tr>
					     <tr>
					 	
					       <td align="right" width="100px">供电方式</td>
						   <td width="100px"><input type="text" name="FANGSHINAME" id="FANGSHINAME" disabled="disabled" value="<%=FANGSHINAME %>"
									style="box-sizing: border-box; width: 130px" />
						   </td>
						   <td width="100px">
									<h id="butue" onclick="yincang()" style="color: #FF0000; font-weight: bold" type="button"value = "显示其他信息"></h>
						   <br /></td>
					    </tr>
					    
					    <!-- 新添手机端报账信息填写 -->
						 	 	
						<tr bgcolor="F9F9F9">
						<td height="19" colspan="8">
							<img src="../../images/v.gif" width="15" height="15" />
							手机抄表信息</td>
						</tr>
					    <tr>
						<td align="right" width="100px"><span style="color: #FF0000; font-weight: bold">*</span>手机抄表开始日期</td>
						<td><input type="text" onchange="changeSJ()" style="box-sizing:border-box;width:130px" class="Wdate"  name="STARTTIME_C" id="STARTTIME_C" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
						<%
				 			if(STARTTIMEStr_C!=""){
				 		 %>
				 		 disabled="disabled" 
				 		 <%
				 			}
				 		 %>
						value="<%=STARTTIMEStr_C%>" />
						</td>
					 	<td align="right" width="100px"><span style="color: #FF0000; font-weight: bold">*</span>手机抄表结束日期</td>
					 	<td><input type="text" onchange="changeSJ()" style="box-sizing:border-box;width:130px" class="Wdate" name="ENDTIME_C" id="ENDTIME_C" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
						<%
				 			if(ENDTIMEStr_C!=""){
				 		 %>
				 		 disabled="disabled" 
				 		 <%
				 			}
				 		 %>
					 	value="<%=ENDTIMEStr_C%>" />
						</td>  
						<td align="right" width="100px"><span style="color: #FF0000; font-weight: bold">*</span>手机抄表上期读数</td>
					 	<td><input type="text" name="SQDS_C" id="SQDS_C" onchange="changeSJ()" style="box-sizing:border-box;width:130px" title="必填项" 
					 	<%
				 			if(SQDS_C!=""){
				 		 %>
				 		 disabled="disabled" 
				 		 <%
				 			}
				 		 %>
					 	value="<%=SQDS_C%>"/></td>
						<td align="right" width="100px"><span style="color: #FF0000; font-weight: bold">*</span>手机抄表本期读数</td>
					 	<td onclick="endtime_c()"><input type="text" name="BQDS_C" id="BQDS_C" onchange="changeSJ()" style="box-sizing:border-box;width:130px" title="必填项"
					 	<%
				 			if(SQDS_C!=""){
				 		 %>
				 		 disabled="disabled" 
				 		 <%
				 			}
				 		 %>
					 	 value="<%=BQDS_C%>"/></td>  
						</tr>
						<tr>
						<td align="right" width="100px">手机抄表用电量</td>
					 	<td><input type="text" name="DIANLIANG_C"  id="DIANLIANG_C" onchange="changeSJ()" disabled="disabled" value="<%=DIANLIANG_C%>" style="box-sizing:border-box;width:130px" /></td>
						<td align="right" width="100px">手机抄表用电天数</td>
						<td><input type="text" name="TIANSHU_C" id="TIANSHU_C" onchange="changeSJ()" disabled="disabled"  value="<%=DAYNUM_C%>" style="box-sizing:border-box;width:130px"/></td>
						<td align="right" width="100px">手机抄表日均用电量</td>
					 	<td><input type="text" name="RJYDL_C" id="RJYDL_C" disabled="disabled"  value="<%=RJYDL_C %>" style="box-sizing:border-box;width:130px" title="必填项" maxlength="30"/></td>
						<td align="right" width="100px">电表是否回转</td>
						<td>是 <input type="radio"  name="huizhuan_c" value="1" onchange="changeSJ()"  />
							否 <input type="radio"  name="huizhuan_c" value="0" onchange="changeSJ()"  checked="checked" />
						</td>
						
						</tr>  
						
						<!--手机端报账信息填写 end -->	
					    
					    <tr bgcolor="F9F9F9">
						<td height="19" colspan="8">
							<img src="../../images/v.gif" width="15" height="15" />
							电费信息</td>
						</tr>
					    <tr>
						<td align="right" width="100px"><span style="color: #FF0000; font-weight: bold">*</span>开始日期</td>
						<td><input type="text" id="STARTTIME" onchange="changeSJ()" style="box-sizing:border-box;width:130px" class="Wdate"  name="STARTTIME" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
						<%if(STARTTIMEStr != "" && STARTTIMEStr == null && STARTTIMEStr != " "){ %>	
				 		disabled="disabled" 
						<%} %>				 		 
						value="<%=STARTTIMEStr %>" />
						</td>
					 	<td align="right" width="100px"><span style="color: #FF0000; font-weight: bold">*</span>结束日期</td>
					 	<td onclick="endtime()"><input type="text" onchange="changeSJ()" style="box-sizing:border-box;width:130px" class="Wdate" name="ENDTIME" id="ENDTIME" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" value="<%=ENDTIMEStr %>" />
						</td>
						<td id="sa" align="right" width="100px">用电天数</td>
						<td><input type="text" name="TIANSHU" onchange="changeSJ()" disabled="disabled" id="TIANSHU" value="<%=TIANSHU %>" style="box-sizing:border-box;width:130px"/>	
						</td>
							<td align="right" width="100px">倍率</td>
						<td><input type="text" name="BEILV"  disabled="disabled" id="BEILV" value="<%=BEILV %>" style="box-sizing:border-box;width:130px"/>	
						</td>
						
					    </tr>
					    <tr>
					    <td align="right" width="100px"><span style="color: #FF0000; font-weight: bold">*</span>上期读数</td>
					 	<td><input type="text" name="SQDS" id="SQDS" onchange="changeSJ()" 
					 	<%if("".equals(SQDS) || null == SQDS || "" == SQDS){
					 		if("0".equals(CSDS)){
					 	%>
						 		value="<%=CSDS%>"
						 	<%}else{ %>
						 		value="<%=CSDS%>" disabled="disabled"
					 	<%}}%> value="<%=SQDS%>"
					 	 style="box-sizing:border-box;width:130px" title="必填项"/>	
						</td>
						<td align="right" width="100px"><span style="color: #FF0000; font-weight: bold">*</span>本期读数</td>
					 	<td><input type="text" name="BQDS" id="BQDS" value="<%=BQDS %>" onchange="changeSJ()" style="box-sizing:border-box;width:130px" title="必填项"/>	
						</td>
					    <td align="right" width="100px">用电量</td>
					 	<td>
					 		<input type="text" name="DIANLIANG"  id="DIANLIANG" onchange="changeSJ()" disabled="disabled" value="<%=DIANLIANG %>" style="box-sizing:border-box;width:130px" />	
						</td>
						<td align="right" width="100px">电表是否回转</td>
							<input type="hidden" name="hz" id="hz" value="<%=huizhuan %>"/>
							<input type="hidden" name="maxds" id="maxds" value="<%=maxds%>"/>
							<td>是 <input type="radio"  name="huizhuan" value="1" onchange="changeSJ()"  onclick="jia()"/>
								否 <input type="radio"  name="huizhuan" value="0" onchange="changeSJ()"  onclick="jia()" checked="checked" />
						</td>
						
					    </tr>
					    <tr>
					    	 <td align="right" width="100px">上期单价</td>
					 	<td>
					 		<input type="text" name="SQDJ" id="SQDJ" 
					 		<%
					 			if(SQDJ!=""){
					 		 %>
					 		 disabled="disabled" 
					 		 <%
					 			}
					 		 %>
					 		 value="<%=SQDJ %>" style="box-sizing:border-box;width:130px" title="必填项"/>	
						</td>
						<td align="right" width="100px">单价</td>
					 	<td>
					 		<input type="text" name="PRICE" disabled="disabled" id="PRICE" onchange="changeSJ()" value="<%=PRICE %>" style="box-sizing:border-box;width:130px" title="必填项" maxlength="30"/>
						</td>
						<td align="right" width="100px"><span style="color: #FF0000; font-weight: bold">*</span>电费金额</td>
					 	<td>
					 	<input type="text" name="ALLMONEY" id="ALLMONEY" onchange="changeDianbiao()" oninput="shuie()" value="<%=ALLMONEY %>" style="box-sizing:border-box;width:130px" title="必填项"/>
						</td>
							<td align="right" width="100px" id="diansunTd">
							<%if(FANGSHI.equals("gdfs05")){ %>
							<span style='color: #FF0000; font-weight: bold'>*</span>电损（度）
							<%}else{ %>
								电损金额（元）
							<%} %>
							</td>
					 	<td>
					 		<input type="text" name="DIANSUN" id="DIANSUN"
					 		<%if(FANGSHI.equals("gdfs05")){ %>
							
							<%}else{ %>
								disabled="disabled"
							<%} %>
					 		 onchange="changeSJ()"  value="<%=DIANSUN %>" style="box-sizing:border-box;width:130px" title="必填项" maxlength="30"/>
						</td>
					    </tr>
					    
					    <tr>
					    <td align="right" width="100px">日均用电量</td>
					 	<td>
					 		<input type="text" name="RJYDL" id="RJYDL" disabled="disabled"  value="<%=RJYDL %>" style="box-sizing:border-box;width:130px" title="必填项" maxlength="30"/>
						</td>
						<td align="right" width="100px">上期日均用电量</td>
					 	<td>
					 		<input type="text" name="SQRJYDL" disabled="disabled" id="SQRJYDL"  value="<%=SQRJYDL %>" style="box-sizing:border-box;width:130px" title="必填项" maxlength="30"/>
						</td>
						<!-- 2018-03-09 gcl-添加页面税额  -->	
						<td align="right" width="100px">税额</td>
							<td><input type="text" name="TAX" 
								id="TAX"  value="<%=TAX %>"
								style="box-sizing:border-box;width:130px" title="必填项"
								maxlength="30" />
						<input type="hidden" name="zzsl" id="zzsl" value="<%=ZZSL %>" />
						<input type="hidden" name="TAXchange" id="TAXchange" value="" />
						<input type="hidden" name="TAXSTATE" id="TAXSTATE" value="" /></td>
					    </tr>
					    
					    <!-- 报账信息添加 2018/1/18 -->
					      <tr bgcolor="F9F9F9">
						<td height="19" colspan="8">
							<img src="../../images/v.gif" width="15" height="15" />
							数据分析信息</td>
						</tr>
						<tr>
						<td align="right" width="100px">电量偏离数</td>
					 	<td>
					 	<input type="hidden" name="LiLunDianLiang" id="LiLunDianLiang" disabled="disabled"  onchange="changeSJ()" value="" style="box-sizing:border-box;width:130px" title="必填项" maxlength="30"/>
					 	<input type="text" name="DianLiangPianLiShu" id="DianLiangPianLiShu" disabled="disabled"  onchange="changeSJ()" value="<%=DLPLS%>" style="box-sizing:border-box;width:130px" title="必填项" maxlength="30"/>
						</td>
						<td align="right" width="100px">日期偏离数</td>
					 	<td>
					 	<input type="text" name="RiQiPianLiShu" id="RiQiPianLiShu" disabled="disabled"  onchange="changeSJ()" value="<%=RQPLS%>" style="box-sizing:border-box;width:130px" title="必填项" maxlength="30"/>
						</td>
							<td align="right" width="100px">PUE值</td>
					 		<td>
					 		<input type="text" name="PUEZHI" id="PUEZHI" disabled="disabled"   value="<%=PUEZHI%>" style="box-sizing:border-box;width:130px" title="必填项" maxlength="30"/>
							</td>
							<td align="right" width="100px">本月全市平均PUE值</td>
					 		<td>
					 		<input type="text" name="AVGPUEZHI" id="AVGPUEZHI" disabled="disabled"   value="<%=AVGPUEZHI%>" style="box-sizing:border-box;width:130px" title="必填项" maxlength="30"/>
							</td>
						</tr>
						<!-- END -->
					    <tr>
					   	 <td align="right" width="100px">电费标杆</td>
					 		<td>
					 		<input type="text" name="BGDL" id="BGDL" disabled="disabled"   value="<%=BGDL %>" style="box-sizing:border-box;width:130px" title="必填项" maxlength="30"/>
							</td>
							<td align="right" width="100px">标杆偏离率</td>
					 		<td>
					 		<input type="text" name="BGPLL" id="BGPLL" disabled="disabled"   value="<%=bgpll %>" style="box-sizing:border-box;width:130px" title="必填项" maxlength="30"/>
							</td>
							<td align="right" width="100px"><a id="showPUE" href="#" onclick="showPUE()">查看最近PUE曲线</>
							<td align="right" width="100px"><a id="showImg" href="#" onclick="showImg()">查看手机抄表信息</>
						
						</tr>
						<tr bgcolor="F9F9F9">
							<td height="19" colspan="4">
								<img src="../../images/v.gif" width="15" height="15" />
								流程信息
							</td>
						</tr>

						<%--
						    ArrayList actionlist = new ArrayList();
						    actionlist = flowBean.getAction("flowtype02", "1"); //报账审核 step1
						    int acount = ((Integer) actionlist.get(0)).intValue();
						     String flowName = "", flowId = "0",actionId="",actionName = "", roleId="";
						    if(actionlist.size() > (acount+1)){
						        flowId = (String) actionlist.get(acount + actionlist.indexOf("FLOWID"));
						        flowName = (String) actionlist.get(acount + actionlist.indexOf("FLOWNAME"));
						        actionId = (String) actionlist.get(acount + actionlist.indexOf("ACTIONID"));
								actionName = (String) actionlist.get(acount + actionlist.indexOf("ACTIONNAME"));
								roleId = (String) actionlist.get(acount + actionlist.indexOf("ROLEID"));
						    }
						
						 --%>
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
					 	
							<td align="left" colspan="6" height="60px">
							 	<font style="color: #4ad2ff;">
							 	※普通表(非直供电):总金额-((截止度数-起始度数)*倍率*单价)=电损金额 </br>
							 	※电业表(直供电表):总金额/((截止度数-起始度数)*倍率+其中电损)=单价
							 	</font>
						 	</td>
						<td align="right" colspan="2" height="60px">
						<input type="button" class="btn_c1" id="button2" value="附件管理" onclick="fujianguanli()" />
							<%--<input onclick="saveBz()" type="button" class="btn_c1" id="button2" value="临时保存" />
						--%>
							<input onclick="saveBz('1')" type="button" class="btn_c1" id="button2" value="临时保存" />
							<%
							String action = rolename.toString();
											System.out.println("---判断是否包含---"+action.contains("抄表员"));
										if(action.contains("抄表员")){%>
								
								<%}else{%>
									<input onclick="saveBz('0')" type="button" class="btn_c1" id="button2" value="提交" />
								 <%}
							 %>
							<input type="hidden" value="" id="saveState" name="saveState" />
						</td>
						</tr>
					</table>
				</div>
			</div>
		</td>
		</tr>
			
	</table>
	<input type="hidden" value="<%=FANGSHI %>" id="FANGSHI" name="FANGSHI" />
	<input type="hidden" value="<%=ID %>" id="ID" name="ID" />
	<input type="hidden" value="<%=ydsx%>" id="ydsx11" name="ydsx11" /> 
	<input type="hidden" value="<%=DIANBIAOID %>" id="DIANBIAOID" name="DIANBIAOID"/>
	<input type="hidden" value="<%=DBIMAGEPATH1 %>" id="DBIMAGEPATH1" name="DBIMAGEPATH1"/>
		<input type="hidden" value="" id="FANGSHI" />
		<input type="hidden" value="" id="XIANCODE" />
	</form>
<script type="text/javascript">
	var path = '<%=path%>';
window.onload = function(){
	var ea='<%=EAO_C %>';
	if(ea.indexOf("ea")>-1){
	$("#butue").text('此条信息为手机抄表信息');
	}
	changeSJ();
	}

	function saveBz(st){
		$("#saveState").val(st);
			if (
          			checkNotSelected(document.form1.DIANBIAOID,"主电表")&&
          			checkNotnull(document.form1.STARTTIME,"开始日期")&&
          			checkNotnull(document.form1.ENDTIME,"结束日期")&&
          			checkNotnull(document.form1.SQDS,"上期读数")&&
          			checkNotnull(document.form1.BQDS,"本期读数")&&
          			checkNotnull(document.form1.SQDJ,"上期单价")&&
          			checkNotnull(document.form1.PRICE,"单价")&&
          			checkNotnull(document.form1.ALLMONEY,"电费金额")&&
          			checkNotnull(document.form1.DIANSUN,"其中电损或电损金额")&&
          			
          			//新添手机验证
          			checkNotnull(document.form1.STARTTIME_C,"手机抄表开始日期")&&
          			checkNotnull(document.form1.ENDTIME_C,"手机抄表结束日期")&&
          			checkNotnull(document.form1.SQDS_C,"手机抄表上期读数")&&
          			checkNotnull(document.form1.BQDS_C,"手机抄表本期读数")&&
          			
          			checkNotnull(document.form1.DianLiangPianLiShu,"电量偏离数")&&
          			checkNotnull(document.form1.RiQiPianLiShu,"日期偏离数")
          			//end
           	 ) {
          	 	//新添手机验证
           	 	var beginTime_C = $("#STARTTIME_C").val();	//手机抄表开始实时间
    			var endTime_C  = $("#ENDTIME_C").val();		//手机抄表结束时间
           	 	var SQDS_check_C = $("#SQDS_C").val();		//手机抄表上期读数	
           	 	var BQDS_check_C = $("#BQDS_C").val();		//手机抄表本期读数
           	 	
           	 	var DianLiangPianLiShu_check = $("#DianLiangPianLiShu").val();	//电量偏离数
           	 	var RiQiPianLiShu_check = $("#RiQiPianLiShu").val();			//日期偏离数
           	 	//end
           	 	var SQDS_check = $("#SQDS").val();
			
           	 	var BQDS_check = $("#BQDS").val();
           	 	var DIANLIANG_check = $("#DIANLIANG").val();
           	 	var ALLMONEY_check = $("#ALLMONEY").val();
           	 	var DIANSUN_check = $("#DIANSUN").val();
           	 	var SQDJ_check = $("#SQDJ").val();
           	 	var PRICE_check = $("#PRICE").val();
           	 	
           	 	var beginTime = $("#STARTTIME").val();
    			var endTime   = $("#ENDTIME").val();
		     	var arys1= new Array();      
		   		var arys2= new Array();      
 				if(beginTime != null && endTime != null) {      
	     			arys1=beginTime.split('-');      
	     		 	var sdate=new Date(arys1[0],parseInt(arys1[1]-1),arys1[2]);      
	    			arys2=endTime.split('-');      
	   				var edate=new Date(arys2[0],parseInt(arys2[1]-1),arys2[2]);    
	      			if(sdate > edate){
	      				alert("结束时间不能小于开始时间！");
	           	 		return false;
	      			}
   				 }

           	 	if(SQDS_check!=null && SQDS_check!="" && !isdushumoney(SQDS_check)){
           	 		alert("上期读数必须为数字,最多两位小数！");
           	 		return false;
           	 	}
           	 	if(BQDS_check!=null && BQDS_check!="" && !ismoney(BQDS_check)){
           	 		alert("本期读数必须为数字,最多两位小数！");
           	 		return false;
           	 	}
           	 	/* if(BQDS_check*1<=SQDS_check*1){
           	 		alert("本期读数不能小于上期读数！");
           	 		return false;
           	 	} */
           	 	if(DIANLIANG_check!=null && DIANLIANG_check!="" && !ismoney(DIANLIANG_check)){
           	 		alert("电量必须为数字,最多两位小数！");
           	 		return false;
           	 	}
           	 	if(ALLMONEY_check!=null && ALLMONEY_check!="" && !ismoney(ALLMONEY_check)){
           	 		alert("总金额必须为金额！");
           	 		return false;
           	 	}
           	 if(DIANSUN_check!=null && DIANSUN_check!="" && !isDIANSUNmoney(DIANSUN_check)){
     	 		alert("其中电损或者电损金额必须为数字,最多两位小数！");
     	 		return false;
     	 	}
           	 	if(SQDJ_check!=null && SQDJ_check!="" && !ismoneys(SQDJ_check)){
           	 		alert("上期单价必须为金额！");
           	 		return false;
           	 	}
           	 	if(PRICE_check!=null && PRICE_check!="" && !ismoneys(PRICE_check)){
           	 		alert("单价必须为金额！");
           	 		return false;
           	 	}
           	 if(PRICE_check == "0" || PRICE_check == 0){
        	 		alert("单价不能为零！");
    	 			return false;
        	 	}
               	//新添手机验证
           	 	if(beginTime_C != null && endTime_C != null) {      
	     			arys1=beginTime_C.split('-');      
	     		 	var sdate=new Date(arys1[0],parseInt(arys1[1]-1),arys1[2]);      
	    			arys2=endTime_C.split('-');      
	   				var edate=new Date(arys2[0],parseInt(arys2[1]-1),arys2[2]);    
	      			if(sdate > edate){
	      				alert("手机抄表结束时间不能小于手机抄表开始时间！");
	           	 		return false;
	      			}
   				 }
           	 	if(SQDS_check_C!=null && SQDS_check_C!="" && !isdushumoney(SQDS_check_C)){
           	 		alert("手机抄表上期读数必须为数字,最多两位小数！");
           	 		return false;
           	 	}
           	 	if(BQDS_check_C!=null && BQDS_check_C!="" && !ismoney(BQDS_check_C)){
           	 		alert("手机抄表本期读数必须为数字,最多两位小数！");
           	 		return false;
           	 	}
           	 /* 	if(BQDS_check_C*1<=SQDS_check_C*1){
           	 		alert("手机抄表本期读数不能小于手机抄表上期读数！");
           	 		return false;
           	 	} */
           	 	if(DianLiangPianLiShu_check == ""){
           	 		alert("电量偏离数不能为空！");
           	 		return false;
           	 	}
           	 	if(RiQiPianLiShu_check == ""){
           	 		alert("日期偏离数不能为空！");
           	 		return false;
           	 	}
           	 	 	///流程判断
           	 	if(st==0){
		   			//流程
		   			//alert("st+3333"+st);
		   			var flowName=document.form1.flowName.value;
		   			if(flowName == "" || flowName == null || flowName == undefined || flowName == 0){ // "",null,undefined
	    		   				alert("流程名称不能为空!");
		   			return false;
		   			}
		   			var auditorid=$("#radioDiv").val();
		   		//alert("JJJJJ"+auditorid);
		      	if(auditorid == "" || auditorid == null || auditorid == undefined || auditorid == 0){ // "",null,undefined
	    		   				alert("执行人不能为空!");
		   		 return false;
		   						}
		   		}
           	 /* 
           	 税额上下浮动判断
           	 	var TAX = $("#TAX").val();
        	 	var zzsl_ch = $("#zzsl").val();
         		//2018年4月3日 -gcl 计算税额的更改额度 更改的税额-计算的税额 = ±0.05以内
      		if(zzsl_ch != "" && zzsl_ch != null){
      			var TA = (parseFloat(ALLMONEY_check) / (parseFloat(1) + parseFloat(zzsl_ch)) * parseFloat(zzsl_ch)).toFixed(2);
      			var i = (parseFloat(TAX) - parseFloat(TA)).toFixed(2);
      			if(TA != TAX){
      				$("#TAXSTATE").val("1");
      			}else{
      				$("#TAXSTATE").val("0");
      			}
      			if(i > 0.05 || i< -0.05){
      				alert("修改后的税额不应大于或小于原税额 0.05元");
      				$("#TAX").val(TA);
      				return false;
      			}
      			$("#TAXchange").val(TA);
      		} */
           	 	//end
		          addbaozhang();
			  };
           	 
  	 }
        	function addbaozhang(){
        		if(confirm("您将要添加报账！确认信息正确！")){
        			
                //新添手机验证
                $('#STARTTIME_C').attr("disabled",false);	//手机抄表开始日期
                $('#SQDS_C').attr("disabled",false);		//手机抄表上期读数
                $('#DIANLIANG_C').attr("disabled",false);	//手机抄表用电量
                $('#TIANSHU_C').attr("disabled",false);		//手机抄表用电天数
                $('#RJYDL_C').attr("disabled",false);		//手机抄表日均用电量

                $('#DianLiangPianLiShu').attr("disabled",false);//电量偏离数
                $('#RiQiPianLiShu').attr("disabled",false);		//日期偏离数
                //end
        			
				$('#STARTTIME').attr("disabled",false);
				$('#SQDS').attr("disabled",false);
				$('#SQDJ').attr("disabled",false);
				$('#PRICE').attr("disabled",false);
				$('#DIANLIANG').attr("disabled",false);
				$('#ALLMONEY').attr("disabled",false);
				$('#TIANSHU').attr("disabled",false);
				$('#RJYDL').attr("disabled",false);
				$('#SQRJYDL').attr("disabled",false);
				$('#BEILV').attr("disabled",false);
				$('#DIANSUN').attr("disabled",false);
				$('#TAX').attr("disabled",false);
				$('#BGDL').attr("disabled",false);
				$('#PUEZHI').attr("disabled",false);
          		document.form1.action=path+"/servlet/zhandian?action=updateBaozhang";
							document.form1.submit();
							 showdiv("请稍等..............");
            }
        	}
        		function isNaN2(val) { 
				return val.match(new RegExp("^[0-9]+$")); 
			} 
        function ismoney (str) {
			if (/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(str))
				return true;
			return false;
		}
        function isdushumoney (str) {
			if (/(([0-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(str))
				return true;
			return false;
		}
        function isDIANSUNmoney (str) {
			if (/^[+-]?\d*\.?\d{1,3}$/.test(str))
				return true;
			return false;
		}
        function ismoneys (str) {
			if (/^(([0-9][0-9]*)|(([0]\.\d{1,4}|[1-9][0-9]*\.\d{1,4})))$/.test(str))
				return true;
			return false;
		}
			function isNa(val) { 
				return val.match(new RegExp("^\d+\.\d+$")); 
			}
function changeDianbiao(){
	var dianbiaoId = document.form1.DIANBIAOID.value;
changeSJ();
	if(dianbiaoId=="0"){
		//手机抄表获取数据添加
		$("#STARTTIME_C").val("");					//手机抄表开始时间
		$('#STARTTIME_C').attr("disabled",false);	//手机抄表开始时间
		$("#SQDS_C").val("");						//手机抄表上期读数
		$('#SQDS_C').attr("disabled",false);		//手机抄表上期读数
		//end
		$("#STARTTIME").val("");
		$('#STARTTIME').attr("disabled",false);
		$("#SQDS").val("");
		$('#SQDS').attr("disabled",false);
		$("#SQDJ").val("");
		$('#SQDJ').attr("disabled",false);
		$("#PRICE").val("");
		$('#PRICE').attr("disabled",false);
		$("#BEILV").val("");
		$('#ZDNAME').val("");
		$('#SHI').val("");
		$('#XIAN').val("");
		$("#SQRJYDL").val("");
		return;
	}else{
		
		 $.ajax({
                type: "POST",//请求方式
                url: path+"/servlet/zhandian?action=getDianbiaoById",
                data: "dianbiaoId="+dianbiaoId,
                dataType: "json",
                scriptCharset: 'utf-8',
				success: function(result){
			 if(result!=null && result.length>0){
				var dianbiaoRe = result.split("*_*")[0];
			 	var baozhangRe = result.split("*_*")[1];
			 	var pueRe = result.split("*_*")[2];
			 	if(dianbiaoRe!=null && dianbiaoRe.length>0){
					var  dianjia_dianbiao = dianbiaoRe.split("@_@")[0];
					var  fangshi_dianbiao = dianbiaoRe.split("@_@")[1];
					var  juzhan_dianbiao = dianbiaoRe.split("@_@")[2];
					var  shi_dianbiao = dianbiaoRe.split("@_@")[3];
					var  xian_dianbiao = dianbiaoRe.split("@_@")[4];
					var  beilv_dianbiao = dianbiaoRe.split("@_@")[5];
					if(fangshi_dianbiao=="gdfs05"){
						$('#diansunTd').html("<span style='color: #FF0000; font-weight: bold'>*</span>电损（度）");
						$('#DIANSUN').attr("disabled",false);
						$("#PRICE").val("");
						$("#FANGSHI").val("gdfs05");
						
					}else{
						$('#diansunTd').html("电损金额（元）");
						$('#DIANSUN').attr("disabled",true);
						$("#PRICE").val(dianjia_dianbiao);
						$("#FANGSHI").val("");
					}
			 	}
			 }
                }
            });
	}
	
}
 function getAuditors(xian,roleId,ydsx1){
    var agcode= xian;
			$
					.ajax({
						type : "POST",//请求方式
						url : path + "/servlet/workflow?action=selAccount",
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
							$("#radioDiv").val(accountid);
						}
					});
		}
    function getNextDay(d){
        d = new Date(d);
        d = +d + 1000*60*60*24;
        d = new Date(d);
        //return d;
        //格式化
        return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
         
    }
function changeSJ(){
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
	
	var FANGSHI_ch = $("#FANGSHI").val();
	var BEILV_ch = $("#BEILV").val();
	if(BEILV_ch==""){
		BEILV_ch=1;
	}
	
	var huizhuan_c = $("input[name='huizhuan_c']:checked").val(); 
	var huizhuan = $("input[name='huizhuan']:checked").val(); 
	
	//电量赋值
	if(BQDS_ch!="" && SQDS_ch!=""){
		if(huizhuan == 1){
			var maxds = $("#maxds").val();
			//因为电表的回转,所以电量应该用电表的(最大读数-上期读数+本期读数)*倍率
			$("#DIANLIANG").val(((parseFloat(maxds) - parseFloat(SQDS_ch) + parseFloat(BQDS_ch)) * parseFloat(BEILV_ch)).toFixed(2));
		}else{	
			$("#DIANLIANG").val(((BQDS_ch * 1 - SQDS_ch * 1) * BEILV_ch).toFixed(2));
		}

	}else{
		$("#DIANLIANG").val("");
	}
	//用电天数赋值
	if(STARTTIME_ch!="" && ENDTIME_ch!=""){
		$("#TIANSHU").val(DateDiff(ENDTIME_ch,STARTTIME_ch));
		var dianliang = $("#DIANLIANG").val();
		if(BQDS_ch!="" && SQDS_ch!=""){
			$("#RJYDL").val((dianliang/(DateDiff(ENDTIME_ch,STARTTIME_ch))).toFixed(2));
		}else{
			$("#RJYDL").val("");
		}
	}else{
		$("#TIANSHU").val("");
		$("#RJYDL").val("");
	}
	
	//直供电添加单价，其他添加电损
	if(FANGSHI_ch=="gdfs05"){
		if(BQDS_ch!="" && SQDS_ch!="" && ALLMONEY_ch!="" && DIANSUN_ch!=""){
			$("#PRICE").val(((ALLMONEY_ch*1) / ((BQDS_ch*1 - SQDS_ch*1)*(BEILV_ch*1) + DIANSUN_ch*1)).toFixed(4));
		}else{
			$("#PRICE").val("");
		}
	}else{
		if(BQDS_ch!="" && SQDS_ch!="" && ALLMONEY_ch!="" && PRICE_ch!=""){
			var allmoney = ALLMONEY_ch*1;
			var dushu = BQDS_ch*1 - SQDS_ch*1;
			var beidan = (BEILV_ch*1)*(PRICE_ch*1);
			var all = (dushu*beidan*1).toFixed(2);
			var diansun = (allmoney - all).toFixed(2);
			$("#DIANSUN").val(diansun);
		}else{
			$("#DIANSUN").val("");
		}
	}
	
	//手机抄表计算方法添加
	//手机电量赋值
 	if(BQDS_ch_C!="" && SQDS_ch_C!=""){
 		if(huizhuan_c == 1){
			var maxds = $("#maxds").val();
			//因为电表的回转,所以电量应该用电表的(最大读数-上期读数+本期读数)*倍率
			$("#DIANLIANG_C").val(((parseFloat(maxds) - parseFloat(SQDS_ch_C) + parseFloat(BQDS_ch_C)) * parseFloat(BEILV_ch)).toFixed(2));
		}else{	
			$("#DIANLIANG_C").val(((BQDS_ch_C * 1 - SQDS_ch_C * 1) * BEILV_ch).toFixed(2));
		}
	}else{
		$("#DIANLIANG_C").val("");
	}
	//手机用电天数赋值
	if(STARTTIME_ch_C!="" && ENDTIME_ch_C!=""){
		$("#TIANSHU_C").val(DateDiff(ENDTIME_ch_C,STARTTIME_ch_C));
	if(BQDS_ch_C!="" && SQDS_ch_C!=""){
		var dianliang_C = 	$("#DIANLIANG_C").val();
		$("#RJYDL_C").val((dianliang_C/(DateDiff(ENDTIME_ch_C,STARTTIME_ch_C))).toFixed(2));
		}else{
		$("#RJYDL_C").val("");
	}
	}else{
		$("#TIANSHU_C").val("");
		$("#RJYDL_C").val("");
	}
	//end
	//报账信息添加 2018/1/18
	//计算抄表理论用电量
	if($("#DIANLIANG").val() != "" && $("#TIANSHU").val() != "" && $("#TIANSHU_C").val() != "" && $("#RJYDL").val()!= ""){
		var DIANLIANG_V = $("#DIANLIANG").val();		//用电量
		var TIANSHU_V = $("#TIANSHU").val();			//天数
		var TIANSHU_C_V = $("#TIANSHU_C").val();		//手机天数
		var RJYDL_V = $("#RJYDL").val();				//日均用电量
		$("#LiLunDianLiang").val(DIANLIANG_V*1+(TIANSHU_C_V*1-TIANSHU_V*1) * RJYDL_V*1);
	}else{
		$("#LiLunDianLiang").val("");
	}
	//计算电量偏离数
	if($("#LiLunDianLiang").val() != "" && $("#DIANLIANG_C").val() != ""){
		var LiLunDianLiang_V = $("#LiLunDianLiang").val();	//抄表理论用电量
		var TIANSHU_V = $("#DIANLIANG_C").val();			//抄表实际用电量
		$("#DianLiangPianLiShu").val((TIANSHU_V/LiLunDianLiang_V).toFixed(2));
			$(document).ready(function() {
				var i=$("#DianLiangPianLiShu").val()*1;
				if (i<0.95) {
					$('#DianLiangPianLiShu').addClass('red');
				//	alert("电量偏离度过大");
				}else if(i>1.05){
					$('#DianLiangPianLiShu').addClass('red');
				//	alert("电量偏离度过大");
				}else{
					$('#DianLiangPianLiShu').addClass('black');
				//	alert("电量偏离度正常");
				}
			});
	}else{
		$("#DianLiangPianLiShu").val("");
	}
	//计算日期偏离数
	if($("#ENDTIME").val() != "" && $("#ENDTIME_C").val() != ""){
		$("#RiQiPianLiShu").val((DateDiff($("#ENDTIME").val(),$("#ENDTIME_C").val())) - 1);
		$(document).ready(function() {
			var i=$("#RiQiPianLiShu").val()*1;
			if (i< -15) {
				$('#RiQiPianLiShu').addClass('red');
			//	alert("日期偏离度过大");
			}else if(i>15){
				$('#RiQiPianLiShu').addClass('red');
				//alert("日期偏离度过大");
			}else{
				$('#RiQiPianLiShu').addClass('black');
			//	alert("日期偏离度正常");
			}
		});
	}else{
		$("#RiQiPianLiShu").val();
	}
	//END $("#TIANSHU").val(DateDiff(ENDTIME_ch,STARTTIME_ch));
	if(dianbiaoId!="0" && BQDS_ch!="" && SQDS_ch!="" && STARTTIME_ch!="" && ENDTIME_ch!=""){
		var dl=((BQDS_ch*1 - SQDS_ch*1)*BEILV_ch).toFixed(2);
		var ts=DateDiff(ENDTIME_ch,STARTTIME_ch);
		 $.ajax({
             type: "POST",//请求方式
          //   async: false,
             url: path+"/servlet/zhandian?action=getDbxxById",
             data: {dianbiaoId:dianbiaoId,stime:STARTTIME_ch,etime:ENDTIME_ch,dl:dl,ts:ts},
             dataType: "json",
             scriptCharset: 'utf-8',
				success: function(result){
			 if(result!=null && result.length>0){
				//alert(result);
				 $("#PUEZHI").val(result);
				}
			 }
		});
	}else{
		 $("#PUEZHI").val("");
	}
	if(dianbiaoId!="0" && STARTTIME_ch!="" && ENDTIME_ch!=""){
		var ts=DateDiff(ENDTIME_ch,STARTTIME_ch);
		var SQRJYDL_str =  $("#SQRJYDL").val();
		var BEILV = $("#BEILV").val();
		 $.ajax({
             type: "POST",//请求方式
         //    async: false,
             url: path+"/servlet/dianbiao?action=getDbxxById2",
             data: {dianbiaoId:dianbiaoId,stime:STARTTIME_ch,etime:ENDTIME_ch,ts:ts,SQRJYDL_str:SQRJYDL_str,BEILV:BEILV},
             dataType: "json",
             scriptCharset: 'utf-8',
				success: function(result){
			 if(result!=null && result.length>0){
				 $("#BGDL").val(result);
				 if(result!="" && BQDS_ch!="" && SQDS_ch!=""){
					 var BGPLL = (((BQDS_ch * 1 - SQDS_ch * 1) * BEILV_ch) - result * 1)
												/ ((result * 1));
											BGPLL=(BGPLL*100).toFixed(2);
										$("#BGPLL").val(BGPLL);
					 
				 }
				 
				}
			 }
		});
	}else{
		 $("#BGDL").val("");
		 $("#BGPLL").val("");
	}
	
	//选择开始日期后,放开结束日期 2018年3月17日-gcl
	var STARTTIME = $("#STARTTIME").val();
	if(STARTTIME != "" || STARTTIME != null ){
		$("#ENDTIME").attr("disabled",false);
	}
	if(STARTTIME == "" || STARTTIME == null ){
		$("#ENDTIME").val("");
		$("#ENDTIME").attr("disabled",true);
	}
	
	var STARTTIME_C = $("#STARTTIME_C").val();
	if(STARTTIME_C != "" || STARTTIME_C != null ){
		$("#ENDTIME_C").attr("disabled",false);
	}
	if(STARTTIME_C == "" || STARTTIME_C == null ){
		$("#ENDTIME_C").val("");
		$("#ENDTIME_C").attr("disabled",true);
	}
}
 function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
       var  aDate,  oDate1,  oDate2,  iDays;
       aDate  =  sDate1.split("-");
       oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);  //转换为12-18-2006格式  
       aDate  =  sDate2.split("-");
       oDate2  =  new  Date(aDate[1]  +  '-'	  +  aDate[2]  +  '-'  +  aDate[0]);  
       iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24);    //把相差的毫秒数转换为天数  
       return  iDays*1+1;
   }    

 function showPUE(){
		var s = document.form1.DIANBIAOID.value;
		if(s=="0"){
			alert("请先选择电表！");
			return;
		}else{
			window.open("showPUE.jsp?dbid="+s, "newwindowPue", "height=330, width=1000, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
		}
	}
 function showImg(){
	 var s = document.form1.DBIMAGEPATH1.value;
	 if(s==""){
		 alert("无图片!");
	 }else{
			window.open("showImg.jsp?path="+s, "newwindowImg", "height=570, width=380, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=no");  

	 }
	 
}
 
		function selectFlow(){
		    var flowid = $("#flowName").val();
		     var ydsx1 = $("#ydsx11").val();
		    console.log(flowid);
		    $.ajax({
	                type: "POST",//请求方式
	                url: path+"/servlet/workflow?action=selflow2",
	                data: {flowid:flowid},
	                dataType: "json",success: function(data){
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
						var xian = <%=XIANCODE%>;
						getAuditors(xian,roleId,ydsx1);
	                }
	           });
		}
		//2018年3月9日 -gcl 根据金额判断是否更改
		function shuie(){
			//税额赋值
			//税额计算公式 : 总金额/(1+税率)*税率 = 税额
			var ALLMONEY_ch = $("#ALLMONEY").val();
			var zzsl_ch = $("#zzsl").val();
			if(zzsl_ch != "" && zzsl_ch != null){
			    $("#TAX").val((parseFloat(ALLMONEY_ch) / (parseFloat(1) + parseFloat(zzsl_ch)) * parseFloat( zzsl_ch)).toFixed(2));
			}else{
				$("#TAX").val("0");
			}
		}
		var zzsl_ch = $("#zzsl").val();
		if(zzsl_ch == "" || zzsl_ch == null || zzsl_ch == 0 || zzsl_ch == 0.00){
			$("#TAX").attr("disabled",true);
		}
		
		var BQDS = $("#BQDS").val();
		var SQDS = $("#SQDS").val();
		if(BQDS<SQDS){
			var hz = $("#hz").val();
			hz = hz*1 - 1*1;
			$("#hz").val(hz);
			//alert(hz);
		}
		
		//进入页面时判断,初始日期是否为空,为空时不可选择结束日期
		var STARTTIME = $("#STARTTIME").val();
		if(STARTTIME == "" || STARTTIME == null ){
				$("#ENDTIME").attr("disabled",true);
		}//报账-end

		var STARTTIME_C = $("#STARTTIME_C").val();
		if(STARTTIME_C == "" || STARTTIME_C == null ){
				$("#ENDTIME_C").attr("disabled",true);
		}//抄表-end
		
		function endtime(){
			var STARTTIME = $("#STARTTIME").val();
			if(STARTTIME == "" || STARTTIME == null ){
				alert("请先选择开始日期!");
			}
		}//报账
		
	
		
		function endtime_c(){
			var STARTTIME_C = $("#STARTTIME_C").val();
			if(STARTTIME_C == "" || STARTTIME_C == null ){
				alert("请先选择手机开始日期!");
			}
		} //抄表
function fujianguanli(){
	var id = document.form1.DIANBIAOID.value;	//电表ID
	var dianbiaoName = document.form1.DBNAME.value;//电表名称 
	var liuchengxuanze = "1";
	var liuchengId = document.getElementById("flowName").value;	//流程编码
	if(dianbiaoName == "" || dianbiaoName == null || dianbiaoName == undefined){ // "",null,undefined
	     alert("电表名称不能为空");
    }else{
    	if(liuchengId == "" || liuchengId == null || liuchengId == undefined || liuchengId == 0){ // "",null,undefined
	     alert("请选择流程");
    	}else{
         window.open("../jizan/fujianguanli.jsp?id="+id+"&name="+dianbiaoName+"&liuchengId="+liuchengId+"&liuchengxuanze="+liuchengxuanze, "newwindowCost", "height=550, width=1000, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
   		}
	}
}
		
function jia(){
	var huizhuan = $("input[name='huizhuan']:checked").val(); 
	if(huizhuan != 0){
		var tyy = $("#hz").val();
		tyy = tyy*1 + 1*1;
		$("#hz").val(tyy);
	}else{
		var tyy = $("#hz").val();
		tyy = tyy*1 - 1*1;
		$("#hz").val(tyy);
	}
}
</script>
</body>
</html>
