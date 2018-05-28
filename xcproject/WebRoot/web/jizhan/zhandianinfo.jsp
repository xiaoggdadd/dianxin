<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.ZhanDianForm" %>
<%@ page import="com.noki.mobi.common.Account" %>
<%
	String path = request.getContextPath();
	String id = request.getParameter("id");
	System.out.println(id+"11111111111");
	ZhanDianForm form = new ZhanDianForm();
	form = form.getJizhan(id);
	System.out.println("gczt=="+form.getGczt()+"<<");
	String typename="";
	typename=form.getTypename();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
%>
<html>
<head>
<title>
addAccount
</title>
<style>
.form{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px

		}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
<script >

var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();


var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init();
</script>
<script language="javascript">
var path = '<%=path%>';
	function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

		}
		else
		{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
}
	function saveAccount(){

/*
          	if(checkNotnull(document.form1.accountName,"账号")&&
                  checkNotnull(document.form1.password,"密码")&&
                   checkNotnull(document.form1.confirmPassword,"确认密码")&&
                   checkNotnull(document.form1.name,"姓名")){
                      
  */                    
                                            	

                        if(confirm("您将要修改站点信息！确认信息正确！")){
													document.form1.action=path+"/servlet/zhandian?action=modifyZhanDian"
													document.form1.submit()
                         }
                    //  }
        	}

        function vName(){
         	var accName = document.form1.accountName.value;
                 if(accName==""){
           	alert("不能为空！")
                   return
          }
               window.open('accountName.jsp?accountId=0&accountName='+accName,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        function vCode(){
          var accCode = document.form1.workSn.value;
          if(accCode==""){
           	alert("不能为空！")
                   return
          }
               window.open('accountCode.jsp?accountId=0&accountCode='+accCode,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }

        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！")
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
$(function(){

	$("#cancelBtn").click(function(){
	window.history.back();
	});
	$("#liulan").click(function(){
		liulan();
	});
});
</script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">

<form action="" name="form1" method="POST">
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>   
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4"></td>
						</tr>
				
							<tr>
							<td colspan="4">
								  <div style="width:700px;height:50px">
								       
								       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点信息</span>	
								  </div>
						    </td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0" >
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1" >

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
		  		   
                   <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4">&nbsp;&nbsp;<img src="../../images/v.gif" width="15" height="15" /><font size="2">&nbsp;基本信息</font></td>
                    </tr>
                  
                    
      <tr  class="selected_font">
         
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">市：</div>
         </td>
         <td width="25%">
         <select name="shi" class="selected_font" onchange="changeCity()"()>
         		<option value="0">请选择</option>
         		<%
	         	ArrayList shenglist = new ArrayList();
	         	shenglist = commBean.getAgcode(shengId,"0",loginName);
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
         </td>
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">区、县：</div>
         </td>
         <td width="26%">
         <select name="xian" class="selected_font" onchange="changeXian()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList xianlist = new ArrayList();
	         	xianlist = commBean.getAgcode(form.getShi(),form.getXian(),loginName);
	         	if(xianlist!=null){
	         		String agcode="",agname="";
	         		int scount = ((Integer)xianlist.get(0)).intValue();
	         		for(int i=scount;i<xianlist.size()-1;i+=scount)
                    {
                    	agcode=(String)xianlist.get(i+xianlist.indexOf("AGCODE"));
                    	agname=(String)xianlist.get(i+xianlist.indexOf("AGNAME"));
                    %>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%}
	         	}
	         %>
         	</select>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">乡镇：</div>
         </td>
         <td width="26%">
         <select name="xiaoqu" id="xiaoqu" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList xiaoqulist = new ArrayList();
	         	xiaoqulist = commBean.getAgcode(form.getXian(),form.getXiaoqu(),loginName);
	         	if(xiaoqulist!=null){
	         		String agcode="",agname="";
	         		int scount = ((Integer)xiaoqulist.get(0)).intValue();
	         		for(int i=scount;i<xiaoqulist.size()-1;i+=scount)
                    {
                    	agcode=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGCODE"));
                    	agname=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGNAME"));
                    %>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%}
	         	}
	         %>
         	</select>
         </td>
      </tr>
      
      <tr  class="selected_font">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">集团报表类型：</div>
         </td>
         <td width="25%">
         	<select name="jztype" class="selected_font"  onchange="kzinfo()">
         		<%
	         	ArrayList zdtype = new ArrayList();
	         	zdtype = ztcommon.getSelctOptions("zdlx");
	         	if(zdtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdtype.get(0)).intValue();
	         		for(int i=cscount;i<zdtype.size()-1;i+=cscount)
                    {
                    	code=(String)zdtype.get(i+zdtype.indexOf("CODE"));
                    	name=(String)zdtype.get(i+zdtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点属性：</div>
         </td>
         <td width="25%">
         	<select name="jzproperty" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = ztcommon.getSelctOptions("zdsx");
	         	if(zdsx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdsx.get(0)).intValue();
	         		for(int i=cscount;i<zdsx.size()-1;i+=cscount)
                    {
                    	code=(String)zdsx.get(i+zdsx.indexOf("CODE"));
                    	name=(String)zdsx.get(i+zdsx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         		<option value="3">营业网点</option>
         		<option value="4">其他</option>
         	</select>
         	</td>
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">用房类型：</div>
         </td>
         <td width="26%">
         		<select name="yflx" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList fwlx = new ArrayList();
	         	fwlx = ztcommon.getSelctOptions("fwlx");
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
         	</select>
         </td>
      </tr>
      
      <tr  class="selected_font">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点名称：</div>
         </td>
         <td width="25%"><input type="text" name="jzname" value=""  class="selected_font" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">别名：</div>
         </td>
         <td width="25%"><input type="text" name="bieming" value=""  class="selected_font" style="width:130px"/></td>
				<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点ID：</div>
         </td>
         <td width="25%"><input type="text" name="zdcode" value=""  class="selected_font" style="width:130px"/></td>
      </tr>
     
      <tr  class="selected_font">
      	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">是否标杆：</div>
         </td>
         <td width="25%"><input type="checkbox" name="bgsign"  />
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">节能管理模块：</div>
         </td>
         <td width="25%">
         	<select name="jnglmk" class="selected_font">
         		<option value="有">有</option>
         		<option value="无">无</option>
         	</select>
         </td>
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">地址：</div>
         </td>
         <td width="25%"><input type="text" name="address" value=""  class="selected_font" style="width:130px"/></td>
      </tr>
    
      <tr  class="selected_font">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">供电方式：</div>
         </td>
         <td width="25%">
         	<select name="gdfs" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList gdfs = new ArrayList();
	         	gdfs = ztcommon.getSelctOptions("gdfs");
	         	if(gdfs!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)gdfs.get(0)).intValue();
	         		for(int i=cscount;i<gdfs.size()-1;i+=cscount)
                    {
                    	code=(String)gdfs.get(i+gdfs.indexOf("CODE"));
                    	name=(String)gdfs.get(i+gdfs.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点面积：</div>
         </td>
         <td width="25%">
         	<input type="area" name="area"  class="selected_font"/>
         </td>
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">备注：</div>
         </td>
         <td width="26%"><input type="text" name="memo" value="" class="selected_font"/>
         </td>
      </tr>
    
   
    
      <tr  class="selected_font">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">基站责任人：</div>
         </td>
         <td width="25%">
         	<select name="fzr" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList account = new ArrayList();
	         	account = commBean.getAccount("0",1);
	         	if(account!=null){
	         		String acc="",accname="";
	         		int acccount = ((Integer)account.get(0)).intValue();
	         		for(int i=acccount;i<account.size()-1;i+=acccount)
                    {
                    	acc=(String)account.get(i+account.indexOf("ACCOUNTNAME"));
                    	accname=(String)account.get(i+account.indexOf("NAME"));
                    %>
                    <option value="<%=acc%>"><%=accname%></option>
                    <%}
	         	}
	         %>
         	</select>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">单价：</div>
         </td>
         <td width="25%">
         	<input type="text" name="dianfei" value=""  class="selected_font"/>
         </td>
                 
      </tr>
      <tr class="selected_font">
      	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">虚拟站点：</div>
         </td>
         <td width="25%"><input type="checkbox" name="xuni"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">是否采集点：</div>
         </td>
         <td width="25%">
         	<select name="caiji" class="selected_font">
				         		<option value="0">否</option>
				         		<option value="1">是</option>
				         	</select>
         </td>
         
      </tr>
     
      				<tr class="selected_font">
				         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">PUE值：</div>
				         </td>
				         <td width="25%"><input type="text" name="PUE" value=""  class="selected_font"/>
				         	
				         </td>
				         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">组织机构编码：</div>
				         </td>
				         <td width="25%"><input type="text" name="zzjgbm" value=""  class="selected_font"/></td>
						<!-- 		<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">参照站点：</div>
				         </td>
				         <td width="25%"><input type="text" name="czzd" value=""  class="selected_font"/><img id="liulan" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png" style="cursor: pointer"/>
				         	</td> -->
				      </tr>
				      <tr class="selected_font">
				         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">工程状态：</div>
				         </td>
				         <td width="25%">
				         		<select name="gczt" class="selected_font">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList gcztlist = new ArrayList();
						         	gcztlist = ztcommon.getSelctOptions("GCZT");
						         	if(gcztlist!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)gcztlist.get(0)).intValue();
						         		for(int i=cscount;i<gcztlist.size()-1;i+=cscount)
					                    {
					                    	
					                    	code=(String)gcztlist.get(i+gcztlist.indexOf("CODE"));
					                    	name=(String)gcztlist.get(i+gcztlist.indexOf("NAME"));
					                    	System.out.println("code=="+code+"<<");
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						       </select>
				         </td>
				         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">工程项目：</div>
				         </td>
				         <td width="25%">
				         	<select name="gcxm" class="selected_font">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList gcxmlist = new ArrayList();
						         	gcxmlist = ztcommon.getSelctOptions("GCXM");
						         	if(gcxmlist!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)gcxmlist.get(0)).intValue();
						         		for(int i=cscount;i<gcxmlist.size()-1;i+=cscount)
					                    {
					                    	code=(String)gcxmlist.get(i+gcxmlist.indexOf("CODE"));
					                    	name=(String)gcxmlist.get(i+gcxmlist.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						       </select>
				         	</td>
								<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点产权：</div>
				         </td>
				         <td width="25%">
				         	<select name="zdcq" class="selected_font">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList zdcqlist = new ArrayList();
						         	zdcqlist = ztcommon.getSelctOptions("ZDCQ");
						         	if(zdcqlist!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)zdcqlist.get(0)).intValue();
						         		for(int i=cscount;i<zdcqlist.size()-1;i+=cscount)
					                    {
					                    	code=(String)zdcqlist.get(i+zdcqlist.indexOf("CODE"));
					                    	name=(String)zdcqlist.get(i+zdcqlist.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						       </select>
				         	</td>
				      </tr>
				      <tr class="selected_font">
				         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">自动抄表：</div>
				         </td>
				         <td width="25%">
				         	<select name="zdcb"  class="selected_font">
				         		<option value="0">否</option>
				         		<option value="1">是</option>
				         	</select>
				         </td>
				         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">直流负荷：</div>
				         </td>
				         <td width="25%"><input type="text" name="zlfh" value=""  class="selected_font"/></td>
								<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">节能技术描述：</div>
				         </td>
				         <td width="25%"><input type="text" name="jnjsms" value=""  class="selected_font"/></td>
				      </tr>
      		<tr class="selected_font">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">能耗监测单元：</div>
         </td>
         <td width="25%">
         	<select name="nhjcdy"  class="selected_font">
				         		<option value="0">否</option>
				         		<option value="1">是</option>
				         	</select>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">ERP编号：</div>
         </td>
         <td width="25%"><input type="text" name="ERPbh" value="<%=form.getERPbh()%>"  class="selected_font"/></td>
				<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">动环ID：</div>
         </td>
         <td width="25%"><input type="text" name="dhID" value="<%=form.getDhID()%>"  class="selected_font"/></td>
      </tr>
      <tr class="selected_font">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">综合网管ID：</div>
         </td>
         <td width="25%"><input type="text" name="zhwgID" value="<%=form.getZhwgID()%>"  class="selected_font"/>
         	
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电子运维ID：</div>
         </td>
         <td width="25%"><input type="text" name="dzywID" value="<%=form.getDzywID()%>"  class="selected_font"/></td>
				
      </tr>
      <tr class="selected_font">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">经度：</div>
         </td>
         <td width="25%"><input type="text" name="longitude" value="<%=form.getLongitude()%>"  class="selected_font"/>
         	
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">纬度：</div>
         </td>
         <td width="25%"><input type="text" name="latitude" value="<%=form.getLatitude()%>"  class="selected_font"/></td>
				
      </tr>
      <tr class="selected_font">
      	<td colspan=6>
      		<div align="center" id="IDCJFKZ" style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;IDC机房扩展信息&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr  class="selected_font">
      					<td>出口宽带：<input type="text" name="ckkd" class="selected_font" value="<%=form.getCkkd()%>"></td>
      					<td>已使用面积：<input type="text" name="ysymj" class="selected_font" value="<%=form.getYsymj()%>"/></td>
      					<td>机柜个数：<input type="text" name="jggs" class="selected_font" value="<%=form.getJggs()%>"/></td>
      					<td>已使用个数：<input type="text" name="ysygs" class="selected_font" value="<%=form.getYsygs()%>"/></td>		
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr  class="selected_font">
      	<td colspan=6>
      		<div align="center" id="ZHJFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;综合机房扩展信息&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr  class="selected_font">
      					<td>机房高度：<input type="text" name="jfgd" class="selected_font" value="<%=form.getJfgd()%>"></td>
      					<td>设定温度：<input type="text" name="sdwd" class="selected_font" value="<%=form.getSdwd()%>"/></td>
      					<td>送风方式：
      						<select name="sffs" class="selected_font">
      						<option value="0">请选择</option>
				         		<%
					         	ArrayList sffs = new ArrayList();
					         	sffs = ztcommon.getSelctOptions("SFFS");
					         	if(sffs!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)sffs.get(0)).intValue();
					         		for(int i=cscount;i<sffs.size()-1;i+=cscount)
				                    {
				                    	code=(String)sffs.get(i+sffs.indexOf("CODE"));
				                    	name=(String)sffs.get(i+sffs.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         %>
					         </select>
      					</td>
      					<td>冷源方式：
      						<select name="lyfs" class="selected_font">
      						<option value="0">请选择</option>
					         		<%
						         	ArrayList lyfs = new ArrayList();
						         	lyfs = ztcommon.getSelctOptions("LYFS");
						         	if(lyfs!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)lyfs.get(0)).intValue();
						         		for(int i=cscount;i<lyfs.size()-1;i+=cscount)
					                    {
					                    	code=(String)lyfs.get(i+lyfs.indexOf("CODE"));
					                    	name=(String)lyfs.get(i+lyfs.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						         </select>
      					</td>		
      				</tr>
      				<script>
      					document.form1.sffs.value='<%=form.getSffs()%>';
      					document.form1.lyfs.value='<%=form.getLyfs()%>';
      					</script>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr>
      	<td colspan=6>
      		<div align="center" id="JZKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;基站扩展信息&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr  class="selected_font">
      					<td>共站情况：<input type="text" name="gzqk" class="selected_font" value="<%=form.getGzqk()%>"></td>
      					<td>能耗占比：<input type="text" name="nhzb" class="selected_font" value="<%=form.getNhzb()%>"/></td>
      					<td>载频数量：<input type="text" name="zpsl" class="selected_font" value="<%=form.getZpsl()%>"/></td>
      				
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr>
      	<td colspan=6>
      		<div align="center" id="GLYFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;管理用房扩展信息&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr>
      					<td>在岗人员：<input type="text" name="zgry" value="<%=form.getZgry()%>"></td>
      					<td>空调数量：<input type="text" name="ktsl" value="<%=form.getKtsl()%>"/></td>
      					<td>PC数量：<input type="text" name="pcsl" value="<%=form.getPcsl()%>"/></td>
      				
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr>
      	<td colspan=6>
      		<div align="center" id="QDYFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;渠道用房扩展信息&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr>
      					<td>在岗人员：<input type="text" name="zgry" class="selected_font" value="<%=form.getZgry()%>"></td>
      					<td>空调数量：<input type="text" name="ktsl" class="selected_font" value="<%=form.getKtsl()%>"/></td>
      					<td>PC数量：<input type="text" name="pcsl" class="selected_font" value="<%=form.getPcsl()%>"/></td>
      					<td>人流量（天/人）：<input type="text" name="rll" class="selected_font" value="<%=form.getRll()%>"/></td>
      				
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr>
      	<td colspan=6>
      		<div align="center" id="FSCYFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;非生产用房扩展信息&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr>
      					<td>在岗人员：<input type="text" name="zgry" class="selected_font" value="<%=form.getZgry()%>"></td>
      					<td>空调数量：<input type="text" name="ktsl" class="selected_font" value="<%=form.getKtsl()%>"/></td>
      					<td>PC数量：<input type="text" name="pcsl" class="selected_font" value="<%=form.getPcsl()%>"/></td>
      				
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
       
    <tr>
        <td colspan="6">
        	<div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:10px">
			<img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
			<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>      
		</div>
        </td>
      </tr>



      <%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>

      </table>
											</td>
										</tr>
									</table>
								</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
     
<input type="hidden" name="id" value="<%=id%>"/>
<input type="hidden" name="kzid" value="<%=form.getKzid()%>"/>
<input type="hidden" name="czzdid" value="<%=form.getCzzdid()%>"/>
</form>
</body>
</html>
<script type="text/javascript">
<!--


function changeCity(){
	var sid = document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/area?action=shi&pid="+sid,"shi");
	}
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeXian(){
			var shi = document.form1.xian.value;
			if(shi=="0"){
				var shilist = document.all.xiaoqu;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=xian&pid="+shi,"xian");
			}
		}
		
		function updateXQ(res){
			var shilist = document.all.xiaoqu;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		
		
		function liulan(){
		//window.open("zhandianselect.jsp");
		//return;
		var url=path+"/web/jizhan/zhandianselect.jsp"
		//window.open(url);
		//return;
			var obj = new Object();
			obj.mid='mid';
		    var obj=showModalDialog(url,obj,'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no');	
			document.form1.czzdid.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.czzd.value=obj.substring(0,obj.indexOf(","));
			/*
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_area.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_jzlx.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_bgsign.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_bieming.value=obj.substring(obj);
			*/
	}
document.form1.shi.value='<%=form.getShi()%>';
document.form1.xian.value='<%=form.getXian()%>';
document.form1.xiaoqu.value='<%=form.getXiaoqu()%>';
document.form1.jztype.value='<%=form.getJztype()%>';
document.form1.jzproperty.value='<%=form.getJzproperty()%>';
document.form1.yflx.value='<%=form.getYflx()%>';
document.form1.jzname.value='<%=form.getJzname()%>';
document.form1.bieming.value='<%=form.getBieming()%>';
document.form1.zdcode.value='<%=id%>';
if('<%=form.getBgsign()%>'=="1"){
document.form1.bgsign.checked=true;
}
document.form1.jnglmk.value='<%=form.getJnglmk()%>';
document.form1.address.value='<%=form.getAddress()%>';
document.form1.gdfs.value='<%=form.getGdfs()%>';
document.form1.area.value='<%=form.getArea()%>';
document.form1.memo.value='<%=form.getMemo()%>';
document.form1.fzr.value='<%=form.getFzr()%>';
document.form1.dianfei.value='<%=form.getDianfei()%>';
//pue
if('<%=form.getXuni()%>'=='1'){
		document.form1.xuni.checked=true;
		document.getElementById("IDXUNI").style.display="block";
}
document.form1.PUE.value='<%=form.getPUE()%>';
document.form1.zzjgbm.value='<%=form.getZzjgbm()%>';
document.form1.czzd.value='<%=form.getCzzd()%>';
document.form1.czzdid.value='<%=form.getCzzdid()%>';
document.form1.gczt.value='<%=form.getGczt()%>';
document.form1.gcxm.value='<%=form.getGcxm()%>';
document.form1.zdcq.value='<%=form.getZdcq()%>';
document.form1.zdcb.value='<%=form.getZdcb()%>';
document.form1.zlfh.value='<%=form.getZlfh()%>';
document.form1.jnjsms.value='<%=form.getJnjsms()%>';
document.form1.nhjcdy.value='<%=form.getNhjcdy()%>';
document.form1.caiji.value='<%=form.getCaiji()%>';

//-->
function kzinfo(){
	var jztype = document.form1.jztype.value;
	if(jztype=="zdlx01"){//IDC机房
		document.getElementById("IDCJFKZ").style.display="block";
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx03"){//综合机房
		document.getElementById("ZHJFKZ").style.display="block";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx08"){//基站
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="block";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx10"){//管理用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="block";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx11"){//渠道用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="block";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx12"){//非生产用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="block";
	}else{
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}
}
var typecode='<%=form.getJztype()%>';
if(typecode=="zdlx01"){//IDC机房
		document.getElementById("IDCJFKZ").style.display="block";
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx03"){//综合机房
		document.getElementById("ZHJFKZ").style.display="block";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx08"){//基站
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="block";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx10"){//管理用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="block";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx11"){//渠道用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="block";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx12"){//非生产用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="block";
	}else{
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}
	function showxuni(){

			if(document.form1.xuni.checked){
				document.getElementById("IDXUNI").style.display="block";
			}else{
				document.getElementById("IDXUNI").style.display="none";
			}
		}
</script>

