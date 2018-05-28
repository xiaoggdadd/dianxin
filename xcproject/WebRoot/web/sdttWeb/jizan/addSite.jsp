<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.Double" %>
<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	Account account=(Account)session.getAttribute("account");
	String jzproperty=request.getParameter("jzproperty");//获取新站点的属性  
	
	String accountname=account.getAccountName();
	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd");
	String entrytime=mat.format(new Date());
	System.out.println("系统时间"+entrytime+"  shengId:"+shengId+"  loginName:"+loginName+"  accountname:"+accountname+" jzproperty:"+jzproperty);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增站点</title>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	

<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/PopupCalendar.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/PopupCalendar_ny.js"></script>

<script type="text/javascript">
var path = '<%=path%>';
	function saveAccount(){	
          	if (
          			checkNotnull(document.form1.STATION_NAME,"局站名称")&& //判断是否为空
          			checkNotSelected(document.form1.shi,"地市")&&
          			checkNotSelected(document.form1.xian,"区县")&&
          			checkNotSelected(document.form1.xiaoqu,"乡镇")&&
          			checkNotSelected(document.form1.SERVICE_ID,"业务等级")&&
          	       	checkNotSelected(document.form1.RANK_ID,"局站等级")&&
          			//checkNotSelected(document.form1.PROPERTY_ID,"产权类型")&&
          			checkNotnull(document.form1.FULL_STATION_CODE,"局站完整编码")&&
          			//checkNotSelected(document.form1.PROPERTY_ID,"产权类型")&&
          			//checkNotSelected(document.form1.SOURCESYSTEMSTATIONTYPEID,"资源系统机房类型")&&
          			//checkNotSelected(document.form1.SOURCESYSTEMSTATIONLEVELID,"资源系统机房等级")&&
          			checkNotnull(document.form1.USERSTATIONNAME,"局站名称(用户)")
           	 ){
           	 	var LONGITUDE_check = document.form1.LONGITUDE.value;
           	 	var LATITUDE_check = document.form1.LATITUDE.value;
           	 	var COVERED_AREA_check = document.form1.COVERED_AREA.value;
           	 	var OCCUPIED_AREA_check = document.form1.OCCUPIED_AREA.value;
           	 	var RENT_check = document.form1.RENT.value;
           	 	var PERIOD_check = document.form1.PERIOD.value;
           	 	
           	 	if(isNa(LONGITUDE_check)){
           	 		alert("经度必须为数字！");
           	 		return false;
           	 	}
           	 	if(isNa(LATITUDE_check)){
           	 		alert("纬度必须为数字");
           	 		return false;
           	 	}
           	 	if(isNa(COVERED_AREA_check)){
           	 		alert("建筑物使用面积必须为数字！");
           	 		return false;
           	 	}
           	 	if(isNa(OCCUPIED_AREA_check)){
           	 		alert("已使用面积必须为数字！");
           	 		return false;
           	 	}
           	 	if(isNa(RENT_check)){
           	 		alert("年租金必须为数字！");
           	 		return false;
           	 	}
           	 	if(isNa(PERIOD_check)){
           	 		alert("租期必须为数字！");
           	 		return false;
           	 	}
           	 	
           			          		  		
		          addzhandian();
			  };                                       
   }
        	function addzhandian(){
        		if(confirm("您将要添加站点信息！确认信息正确！")){
        		
          		document.form1.action=path+"/servlet/zhandian?action=addSite";
							document.form1.submit();
							 showdiv("请稍等..............");
            }
        	}
        	function isNaN2(val) { 
				return val.match(new RegExp("^[0-9]+$")); 
			} 
			function isNa(val) { 
				return val.match(new RegExp("^\d+\.\d+$")); 
			} 
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
	
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">站点新增</div>
				<div class="content01_1">
					<table width="1000px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="100px">局站名称</td>
							<td width="100px">
								<input type="text" name="STATION_NAME" value="" style="box-sizing:border-box;width:130px" title="必填项" maxlength="200"/>	<span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">地市</td>
							<td width="100px">
								<select name="shi" style="box-sizing:border-box;width:130px;" onchange="changeCity()">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList shenglist = new ArrayList();
						         	shenglist = commBean.getAgcode(shengId,"0",loginName);//è·åè¯¥ç»å½ç¨æ·è´è´£çåå¸ éå 
						         	if(shenglist!=null){
						         		String sfid="",sfnm="";
						         		int scount = ((Integer)shenglist.get(0)).intValue();
						         		for(int i=scount;i<shenglist.size()-1;i+=scount)
					                    {
					                    	sfid=(String)shenglist.get(i+shenglist.indexOf("AGCODE"));
					                    	sfnm=(String)shenglist.get(i+shenglist.indexOf("AGNAME"));
					                    %>
					                    <option value="<%=sfid%>"><%=sfnm%></option>
					                    <%}
						         	}
						         %>
					         	</select>
					         		<span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">区县</td>
							<td width="100px">
								<select name="xian" style="box-sizing:border-box;width:130px;" onchange="changeCountry()">
         							<option value="0">请选择</option>
         								</select>
         									<span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">乡镇</td>
							<td width="100px">
								<select name="xiaoqu" id="xiaoqu" style="box-sizing:border-box;width:130px;">
         							<option value="0">请选择</option>
         								</select>
         									<span style="color: #FF0000; font-weight: bold">*</span>
							</td>
						
						</tr>
						<tr>
							<td align="right" width="100px">监控中心</td>
							<td width="100px">
								<input type="text" name="LSCID" value="" style="box-sizing:border-box;width:130px" />
							</td>
							<td align="right" width="100px">局站完整编码</td>
							<td width="100px">
								<input type="text" name="FULL_STATION_CODE" value="" style="box-sizing:border-box;width:130px" maxlength="20"/>	<span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">局站编码(0-999)</td>
							<td width="100px">
								<input type="text" name="STATION_CODE" value="" style="box-sizing:border-box;width:130px" />
							</td>
							<td align="right" width="100px">业务等级</td>
							<td width="100px">
								<%--<input type="text" name="SERVICE_ID" value="" style="box-sizing:border-box;width:130px" />
								--%><select name="SERVICE_ID" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList SERVICE_ID_dict = new ArrayList();
					         		SERVICE_ID_dict = ztcommon.getSelctOptions("SERVICE_ID_DICT");
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
					         	</select>	<span style="color: #FF0000; font-weight: bold">*</span>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">局站等级</td>
							<td width="100px">
								<%--<input type="text" name="RANK_ID" value="" style="box-sizing:border-box;width:130px"/>
								
								--%><select name="RANK_ID" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList RANK_ID_dict = new ArrayList();
					         		RANK_ID_dict = ztcommon.getSelctOptions("RANK_ID_DICT");
						         	if(RANK_ID_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)RANK_ID_dict.get(0)).intValue();
						         		for(int i=cscount;i<RANK_ID_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)RANK_ID_dict.get(i+RANK_ID_dict.indexOf("CODE"));
					                    	name=(String)RANK_ID_dict.get(i+RANK_ID_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>	<span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">产权类型</td>
							<td width="100px">
								
								<select name="PROPERTY_ID" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList PROPERTY_ID_dict = new ArrayList();
					         		PROPERTY_ID_dict = ztcommon.getSelctOptions("PROPERTY_ID_DICT");
						         	if(PROPERTY_ID_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)PROPERTY_ID_dict.get(0)).intValue();
						         		for(int i=cscount;i<PROPERTY_ID_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)PROPERTY_ID_dict.get(i+PROPERTY_ID_dict.indexOf("CODE"));
					                    	name=(String)PROPERTY_ID_dict.get(i+PROPERTY_ID_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>	<span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">局站维护单位</td>
							<td width="100px">
								<select name="MAINTAIN_ID" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList MAINTAIN_ID_dict = new ArrayList();
					         		MAINTAIN_ID_dict = ztcommon.getSelctOptions("MAINTAIN_ID_DICT");
						         	if(MAINTAIN_ID_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)MAINTAIN_ID_dict.get(0)).intValue();
						         		for(int i=cscount;i<MAINTAIN_ID_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)MAINTAIN_ID_dict.get(i+MAINTAIN_ID_dict.indexOf("CODE"));
					                    	name=(String)MAINTAIN_ID_dict.get(i+MAINTAIN_ID_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">局站地址</td>
							<td width="100px">
								<input type="text" name="ADDRESS" value="" style="box-sizing:border-box;width:130px" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">局站经度</td>
							<td width="100px">
								<input type="text" name="LONGITUDE" value="" style="box-sizing:border-box;width:130px"/>
							</td>
							<td align="right" width="100px">局站纬度</td>
							<td width="100px">
								<input type="text" name="LATITUDE" value="" style="box-sizing:border-box;width:130px" />
							</td>
							<td align="right" width="100px">建筑物使用面积</td>
							<td width="100px">
								<input type="text" name="COVERED_AREA" value="" style="box-sizing:border-box;width:130px" />
							</td>
							<td align="right" width="100px">已使用面积</td>
							<td width="100px">
								<input type="text" name="OCCUPIED_AREA" value="" style="box-sizing:border-box;width:130px" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">年租金</td>
							<td width="100px">
								<input type="text" name="RENT" value="" style="box-sizing:border-box;width:130px"/>
							</td>
							<td align="right" width="100px">起租时间</td>
							<td width="100px"><%--
								<input type="text" name="HIRE_TIME" value="" style="box-sizing:border-box;width:130px" />
								--%> 
							<input type="text"  style="box-sizing:border-box;width:130px"  name="HIRE_TIME" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" value="" />
							</td>
							<td align="right" width="100px">租期</td>
							<td width="100px">
								<input type="text" name="PERIOD" value="" style="box-sizing:border-box;width:130px" />
							</td>
							<td align="right" width="100px">租金类型</td>
							<td width="100px">
								<select name="RENT_ID" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList RENT_ID_dict = new ArrayList();
					         		RENT_ID_dict = ztcommon.getSelctOptions("RENT_ID_DICT");
						         	if(RENT_ID_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)RENT_ID_dict.get(0)).intValue();
						         		for(int i=cscount;i<RENT_ID_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)RENT_ID_dict.get(i+RENT_ID_dict.indexOf("CODE"));
					                    	name=(String)RENT_ID_dict.get(i+RENT_ID_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">租赁方类型</td>
							<td width="100px">
								<select name="LEASE_ID" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList LEASE_ID_dict = new ArrayList();
					         		LEASE_ID_dict = ztcommon.getSelctOptions("LEASE_ID_DICT");
						         	if(LEASE_ID_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)LEASE_ID_dict.get(0)).intValue();
						         		for(int i=cscount;i<LEASE_ID_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)LEASE_ID_dict.get(i+LEASE_ID_dict.indexOf("CODE"));
					                    	name=(String)LEASE_ID_dict.get(i+LEASE_ID_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">最晚支付日期</td>
							<td width="100px">
								<input type="text"  style="box-sizing:border-box;width:130px"  name="LATEST_DATE" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" value="" />
							</td>
							<td align="right" width="100px">供电方式类型</td>
							<td width="100px">
								<select name="POWER_ID" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList POWER_ID_dict = new ArrayList();
					         		POWER_ID_dict = ztcommon.getSelctOptions("POWER_ID_DICT");
						         	if(POWER_ID_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)POWER_ID_dict.get(0)).intValue();
						         		for(int i=cscount;i<POWER_ID_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)POWER_ID_dict.get(i+POWER_ID_dict.indexOf("CODE"));
					                    	name=(String)POWER_ID_dict.get(i+POWER_ID_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">供电单价(元)</td>
							<td width="100px">
								<input type="text" name="PRICE" value="" style="box-sizing:border-box;width:130px" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">电费标杆系数</td>
							<td width="100px">
								<input type="text" name="ELECTRICITYRATIO" value="" style="box-sizing:border-box;width:130px"/>
							</td>
							<td align="right" width="100px">监控状态</td>
							<td width="100px">
								<select name="ISSUPERVISOR" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList ISSUPERVISOR_dict = new ArrayList();
					         		ISSUPERVISOR_dict = ztcommon.getSelctOptions("ISSUPERVISOR_DICT");
						         	if(ISSUPERVISOR_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)ISSUPERVISOR_dict.get(0)).intValue();
						         		for(int i=cscount;i<ISSUPERVISOR_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)ISSUPERVISOR_dict.get(i+ISSUPERVISOR_dict.indexOf("CODE"));
					                    	name=(String)ISSUPERVISOR_dict.get(i+ISSUPERVISOR_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">局站状态</td>
							<td width="100px">
								<select name="STATUS" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList STATUS_dict = new ArrayList();
					         		STATUS_dict = ztcommon.getSelctOptions("STATUS_DICT");
						         	if(STATUS_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)STATUS_dict.get(0)).intValue();
						         		for(int i=cscount;i<STATUS_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)STATUS_dict.get(i+STATUS_dict.indexOf("CODE"));
					                    	name=(String)STATUS_dict.get(i+STATUS_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">蓄电池使用时长(分钟)</td>
							<td width="100px">
								<input type="text" name="BATTERYUSELENGTH" value="" style="box-sizing:border-box;width:130px" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">是否农村局站</td>
							<td width="100px">
								<select name="ISCOUNTRY" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList ISCOUNTRY_dict = new ArrayList();
					         		ISCOUNTRY_dict = ztcommon.getSelctOptions("ISCOUNTRY_DICT");
						         	if(ISCOUNTRY_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)ISCOUNTRY_dict.get(0)).intValue();
						         		for(int i=cscount;i<ISCOUNTRY_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)ISCOUNTRY_dict.get(i+ISCOUNTRY_dict.indexOf("CODE"));
					                    	name=(String)ISCOUNTRY_dict.get(i+ISCOUNTRY_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">资源系统机房类型</td>
							<td width="100px">
								<select name="SOURCESYSTEMSTATIONTYPEID" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList SOURCESYSTEMSTATIONTYPEID_dict = new ArrayList();
					         		SOURCESYSTEMSTATIONTYPEID_dict = ztcommon.getSelctOptions("SOURCESYSTEMSTATIONTYPEID_DICT");
						         	if(SOURCESYSTEMSTATIONTYPEID_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)SOURCESYSTEMSTATIONTYPEID_dict.get(0)).intValue();
						         		for(int i=cscount;i<SOURCESYSTEMSTATIONTYPEID_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)SOURCESYSTEMSTATIONTYPEID_dict.get(i+SOURCESYSTEMSTATIONTYPEID_dict.indexOf("CODE"));
					                    	name=(String)SOURCESYSTEMSTATIONTYPEID_dict.get(i+SOURCESYSTEMSTATIONTYPEID_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>	<span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">资源系统机房等级</td>
							<td width="100px">
								<select name="SOURCESYSTEMSTATIONLEVELID" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList SOURCESYSTEMSTATIONLEVELID_dict = new ArrayList();
					         		SOURCESYSTEMSTATIONLEVELID_dict = ztcommon.getSelctOptions("SOURCESYSTEMSTATIONLEVELID_DICT");
						         	if(SOURCESYSTEMSTATIONLEVELID_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)SOURCESYSTEMSTATIONLEVELID_dict.get(0)).intValue();
						         		for(int i=cscount;i<SOURCESYSTEMSTATIONLEVELID_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)SOURCESYSTEMSTATIONLEVELID_dict.get(i+SOURCESYSTEMSTATIONLEVELID_dict.indexOf("CODE"));
					                    	name=(String)SOURCESYSTEMSTATIONLEVELID_dict.get(i+SOURCESYSTEMSTATIONLEVELID_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>	<span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">房屋结构</td>
							<td width="100px">
								<select name="HOUSETYPEID" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList HOUSETYPEID_dict = new ArrayList();
					         		HOUSETYPEID_dict = ztcommon.getSelctOptions("HOUSETYPEID_DICT");
						         	if(HOUSETYPEID_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)HOUSETYPEID_dict.get(0)).intValue();
						         		for(int i=cscount;i<HOUSETYPEID_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)HOUSETYPEID_dict.get(i+HOUSETYPEID_dict.indexOf("CODE"));
					                    	name=(String)HOUSETYPEID_dict.get(i+HOUSETYPEID_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">是否用户确认可用</td>
							<td width="100px">
								<input type="text" name="ISUSERCONFIRM" value="" style="box-sizing:border-box;width:130px"/>
							</td>
							<td align="right" width="100px">局站名称(用户)</td>
							<td width="100px">
								<input type="text" name="USERSTATIONNAME" value="" style="box-sizing:border-box;width:130px"/>	<span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">具体乡镇</td>
							<td width="100px">
								<input type="text" name="LEAF_AREAID" value="" style="box-sizing:border-box;width:130px" />
							</td>
							<td align="right" width="100px">编辑时间</td>
							<td width="100px">
								<input type="text"  style="box-sizing:border-box;width:130px"  name="EDITTIME" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" value="" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">使用专业</td>
							<td width="100px">
								<input type="text" name="USEDSPECIALTY" value="" style="box-sizing:border-box;width:130px"/>
							</td>
							<td align="right" width="100px">集团局站等级</td>
							<td width="100px">
								<select name="GROUPRANK_ID" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList GROUPRANK_ID_dict = new ArrayList();
					         		GROUPRANK_ID_dict = ztcommon.getSelctOptions("GROUPRANK_ID_DICT");
						         	if(GROUPRANK_ID_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)GROUPRANK_ID_dict.get(0)).intValue();
						         		for(int i=cscount;i<GROUPRANK_ID_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)GROUPRANK_ID_dict.get(i+GROUPRANK_ID_dict.indexOf("CODE"));
					                    	name=(String)GROUPRANK_ID_dict.get(i+GROUPRANK_ID_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">交换专业CC08监控</td>
							<td width="100px">
								<select name="CC08_SUPERVISORY" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList CC08_SUPERVISORY_dict = new ArrayList();
					         		CC08_SUPERVISORY_dict = ztcommon.getSelctOptions("CC08_SUPERVISORY_DICT");
						         	if(CC08_SUPERVISORY_dict!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)CC08_SUPERVISORY_dict.get(0)).intValue();
						         		for(int i=cscount;i<CC08_SUPERVISORY_dict.size()-1;i+=cscount)
					                    {
					                    	code=(String)CC08_SUPERVISORY_dict.get(i+CC08_SUPERVISORY_dict.indexOf("CODE"));
					                    	name=(String)CC08_SUPERVISORY_dict.get(i+CC08_SUPERVISORY_dict.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">局站ID生成时间</td>
							<td width="100px">
								<input type="text"  style="box-sizing:border-box;width:130px"  name="STATION_ID_INSERT_DATE" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" value="" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">动环监控开通时间</td>
							<td width="100px">
								<input type="text"  style="box-sizing:border-box;width:130px"  name="SUPERVISOR_DATE" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" value="" />
							</td>
							<td align="right" width="100px">监控传输方式</td>
							<td width="100px">
								<input type="text" name="SUPERVISOR_TYPE" value="" style="box-sizing:border-box;width:130px" />
							</td>
							<td align="right" width="100px">传输IP</td>
							<td width="100px">
								<input type="text" name="TRANS_IP" value="" style="box-sizing:border-box;width:130px" />
							</td>
							<td align="right" width="100px">监控主设备IP</td>
							<td width="100px">
								<input type="text" name="SUPERVISOR_EQU_IP" value="" style="box-sizing:border-box;width:130px" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">传输方式</td>
							<td width="100px">
								<input type="text" name="TRANS_TYPE" value="" style="box-sizing:border-box;width:130px"/>
							</td>
							<td align="right" width="100px">OLT端口地址</td>
							<td width="100px">
								<input type="text" name="OLT_PORT_ADDRE" value="" style="box-sizing:border-box;width:130px" />
							</td>
							<td align="right" width="100px"></td>
							<td width="100px">
							</td>
							<td align="right" width="100px"></td>
							<td width="100px">
							</td>
						</tr>
						<%--
						<tr>
							<td align="right" width="100px">共享信息</td>
							<td width="100px">
								<select name="gxxx" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList gxxx = new ArrayList();
					         		gxxx = ztcommon.getSelctOptions("gxxx");
						         	if(gxxx!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)gxxx.get(0)).intValue();
						         		for(int i=cscount;i<gxxx.size()-1;i+=cscount)
					                    {
					                    	code=(String)gxxx.get(i+gxxx.indexOf("CODE"));
					                    	name=(String)gxxx.get(i+gxxx.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">经度</td>
							<td width="100px">
								<input type="text" name="jingdu" style="box-sizing:border-box;width:130px"/>
							</td>
							<td align="right" width="100px">纬度</td>
							<td width="100px">
								<input type="text" name="weidu" style="box-sizing:border-box;width:130px"/>
							</td>
							<td align="right" width="100px">房屋类型</td>
							<td width="100px">
								<select name="yflx"  style="box-sizing:border-box;width:130px;" title="根据下拉值选择填写，用房类型包括：砖混、彩钢板+直流负荷 来判断基站或接入网的22个基站类型和接入网类型">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList fwlx = new ArrayList();
						         	fwlx = ztcommon.getSelctOptions("FWLX");
						         	if(fwlx!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)fwlx.get(0)).intValue();
						         		for(int i=cscount;i<fwlx.size()-1;i+=cscount)
					                    {
					                    	code=(String)fwlx.get(i+fwlx.indexOf("CODE"));
					                    	name=(String)fwlx.get(i+fwlx.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>&nbsp;
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">原产权方</td>
							<td width="100px">
								<select name="zdcq" id="zdcq" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList ycq = new ArrayList();
					         		ycq = ztcommon.getSelctOptions("ycq");
						         	if(ycq!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)ycq.get(0)).intValue();
						         		for(int i=cscount;i<ycq.size()-1;i+=cscount)
					                    {
					                    	code=(String)ycq.get(i+ycq.indexOf("CODE"));
					                    	name=(String)ycq.get(i+ycq.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					
					         	</select>
							</td>
							<td align="right" width="100px">物理站点编码</td>
							<td width="100px">
								<input type="text" name="wlzdbm" style="box-sizing:border-box;width:130px"/>
							</td>
							<td align="right" width="100px">站址类型</td>
							<td width="100px">
								<select name="zzlx" id="zzlx" style="box-sizing:border-box;width:130px;">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList zzlx = new ArrayList();
					         		zzlx = ztcommon.getSelctOptions("zzlx");
						         	if(zzlx!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)zzlx.get(0)).intValue();
						         		for(int i=cscount;i<zzlx.size()-1;i+=cscount)
					                    {
					                    	code=(String)zzlx.get(i+zzlx.indexOf("CODE"));
					                    	name=(String)zzlx.get(i+zzlx.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					
					         	</select>
							</td>
							<td align="right" width="100px">对应联通区县</td>
							<td width="100px">
								<input type="text" name="ltqx" style="box-sizing:border-box;width:130px"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">对应移动区县</td>
							<td width="100px">
								<input type="text" name="ydqx" style="box-sizing:border-box;width:130px"/>&nbsp;
							</td>
							<td align="right" width="100px">联通能耗系统ID</td>
							<td width="100px">
								<input type="text" name="xtid" style="box-sizing:border-box;width:130px"/>
							</td>
							<td align="right" width="100px">站点地址</td>
							<td width="100px">
								<input type="text" name="address"  style="box-sizing:border-box;width:130px" title="选填项，站点所在的具体物理位置 "/>
							</td>
							<td align="right" width="100px">站点面积（㎡）</td>
							<td width="100px">
								<input type="text" name="area" style="box-sizing:border-box;width:130px" title="选填项，根据站点所在地的具体面积填写 单位：平方米"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">负责人联系方式</td>
							<td width="100px">
								<input type="text" name="phone" style="box-sizing:border-box;width:130px"/>&nbsp;
							</td>
							<td align="right" width="100px">供电方名称</td>
							<td width="100px">
								<input type="text" name="gdfname" style="box-sizing:border-box;width:130px"/>
							</td>
							<td align="right" width="100px">部门</td>
							<td width="100px">
								<input type="text" name="bumen" style="box-sizing:border-box;width:130px"/>
							</td>
							<td align="right" width="100px">站点责任人</td>
							<td width="100px">
								<input type="text" name="fuzeren"  style="box-sizing:border-box;width:130px" title="选填项，站点具体负责的人员"/>&nbsp;
							</td>
						</tr>
						
						--%>
						<tr>
							<td align="right" colspan="8" height="60px">
								<input name="button2" type="button" class="btn_c1" id="button2" value="临时保存" />&nbsp; 
								<input onclick="saveAccount()" type="button" class="btn_c1" id="button2" value="提交审批" />&nbsp; 
								<input type="reset" class="btn_c1" value="重置" />&nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td>
		</tr>
		</table>
		</form>
		<script type="text/javascript">
	
	
	var path = '<%=path%>';
var XMLHttpReq;
	//XMLHttpReq = createXMLHttpRequest();
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	
	///////////////////////////////////////////////////////////
	function sendRequest(url,para) {

		createXMLHttpRequest();
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="sheng"){
			XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
		}else if(para=="jzproperty"){
			XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数
			
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
	}
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		//alert(XMLHttpReq.status);
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
            	var res = XMLHttpReq.responseText;
              window.alert(res);
             
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    
function processResponse_sheng() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            	
            	//var res = XMLHttpReq.responseText;
            
              updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateQx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZd(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
	function processResponse_zdlx() {
	//alert("333");
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZdlx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
    
	}
	//站点类型
function updateZdlx(res){
// alert("444");
	var shilist = document.all.stationtype;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
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
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

   	//站点属性
	function zdsx(){
	var sid = document.form1.jzproperty.value;
	if(sid=="0"){
		var shilist = document.all.stationtype;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=zdsx&pid="+sid,"jzproperty");
	}
	
}

   function xsl(){
  // 02xsbl线损比例  01xstz 线损调整 
   		var xsll=document.form1.linelosstype.value;
   		var bl=document.getElementById("xs");
   		var blnr="线损值（%）：";
   		var tz="线损值（度）：";
   		if(xsll=="02xsbl"){
   		 bl.innerHTML=blnr;
   		}else if(xsll=="01xstz"){
   		bl.innerHTML=tz;
   		}
   }


function kzinfo(){
	
}

		function showxuni(){

			if(document.form1.xuni.checked){
				document.getElementById("IDXUNI").style.display="block";
			}else{
				document.getElementById("IDXUNI").style.display="none";
			}
		}
		function liulan(){
		//window.open("zhandianselect.jsp");
		//return;
		var url=path+"/web/jizhan/zhandianselect.jsp";
		//window.open(url);
		//return;
			var obj = new Object();
			obj.mid='mid';
		    var obj=showModalDialog(url,obj,'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no');	
			document.form1.czzdid.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.czzd.value=obj.substring(0,obj.indexOf(","));
	}
</script>
</body>
</html>
