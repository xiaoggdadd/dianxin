<%@ page language="java" import="java.util.*,com.noki.mobi.common.Account,com.ptac.app.priceover.countysigncheck.bean.CheckBean" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Account account = (Account) session.getAttribute("account");//账户
String personnal=account.getAccountName();//审核人员
String accountid = account.getAccountId();

CheckBean bean = (CheckBean)request.getAttribute("bean");
String yzydlx = "".equals(bean.getYzydlx())?"yzydlx01":bean.getYzydlx();//业主用电类型
String ydsx = "".equals(bean.getYdsx())?"ydsx01":bean.getYdsx();//用电属性


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>单价超标核实</title>
    <style>
.form_label{
	text-align: right;
	font-family: 微软雅黑;
	font-size: 12px;
	width:13%;
}
.form2{
   border-left-width:0px; border-top-width:0px; border-right-width:0px;
   text-align: right;
   height:19px;
   width:130px;
}
.formkong{
   border-left-width:0px; border-top-width:0px; border-right-width:0px; border-bottom-width:0px;
   height:19px;
   width:130px;
}
</style>
<link type="text/css" rel="Stylesheet"
			href="<%=path%>/web/appCSS/pageCss/page.css" />
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
<script language="javascript">
var path = '<%=path%>';
var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
  function fileChange(fileid) { 
	var target = document.getElementById(fileid);
    var fileSize = 0;  
    if(""==target.value){
    	return 0;
    }else{
	    try{
		    if (isIE && !target.files) {       
		      var filePath = target.value;       
		      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");          
		      var file = fileSystem.GetFile (filePath);       
		      fileSize = file.Size;      
		    } else {      
		     fileSize = target.files[0].size;       
		     }     
		     var size = fileSize / 1024;
		     return size;
	    }catch(e){
		return -1;
	    }
    }   
}
	//直供电 比例,和为100%
	function sumfgp(){
		var gdfs = document.getElementById('gdfscode').value;//供电方式
		var jfzb = parseFloat(Number(document.getElementById('jfdlzb').value).toFixed(2));
		var bfzb = parseFloat(Number(document.getElementById('pdlzb').value).toFixed(2));
		var bpzb = parseFloat(Number(document.getElementById('gfdlzb').value).toFixed(2));
		var bgzb = parseFloat(Number(document.getElementById('gdlzb').value).toFixed(2));
		
		if(getgdfs(gdfs)){//直供电
				if(jfzb+bfzb+bpzb+bgzb==100){
					return true;
				}else{
					alert("尖峰电量占比，平电量占比，高峰电量占比，谷电量占比和不等于100%");
				}
			}else{//转供电
				return true;
			}
	}
function shangchuan(){
	var peid = document.form1.peid.value;
	var yzydlx = document.form1.yzydlx.value;//业主用电类型
	var yzhdjcdj = document.form1.yzhdjcdj.value;//业主获电基础单价
	var hzprice = document.form1.hzprice.value;//核准单价
	var jfdlzb = document.form1.jfdlzb.value;//尖峰电量占比(%)
	var pdlzb = document.form1.pdlzb.value;//平电量占比(%)
	var zgdjcdj = document.form1.zgdjcdj.value;//直供电基础单价
	var gfdlzb = document.form1.gfdlzb.value;//高峰电量占比
	var gdlzb = document.form1.gdlzb.value;//谷电量占比
	var byqrl = document.form1.byqrl.value;//变压器容量
	var beilv = document.form1.beilv.value;//倍率
	var ydsx = document.form1.ydsx.value;//用电属性
	var xbsl = document.form1.xbsl.value;//线变损率
	var xbsdl = document.form1.xbsdl.value;//线变损电量
	var glfsfxs = document.form1.glfsfxs.value;//管理费上浮系数(%)
	var mssfxs = document.form1.mssfxs.value;//忙时上浮系数(%)
	
	var glfsfxs1 = document.form1.glfsfxs.value.replace(/[ ]/g,"");
	var mssfxs1 = document.form1.mssfxs.value.replace(/[ ]/g,"");
//	var file1 = fileChange("fileUp1");
//	var file2 = fileChange("fileUp2");
//	var file3 = fileChange("fileUp3");
//	var filesize = file1+file2+file3;
//	if(filesize>6144){
	//	alert("附件大小之和不能大于6M"); 
	//}else if(filesize<0){
	//		alert( "跳出此消息框，是由于你的activex控件没有设置好,\n" +
	//"你可以在浏览器菜单栏上依次选择\n" +
	//"工具->internet选项->\"安全\"选项卡->自定义级别,\n" +
//	"打开\"安全设置\"对话框，把\"对没有标记为安全的\n" +
//	"ActiveX控件进行初始化和脚本运行\"，改为\"启动\"即可");
	//}else
	if(""==glfsfxs1){
	alert("管理费上浮系数不能为空!");
	}else if(""==mssfxs1){
		alert("忙时上浮系数不能为空!");
	}else if(parseFloat(glfsfxs)!=glfsfxs){
		alert("管理费上浮系数必须为数字!");
	}else if(parseFloat(glfsfxs)>15 || parseFloat(glfsfxs)<0){
		alert("管理费上浮系数必须大于等于0且小于等于15");
	}else if(parseFloat(mssfxs)!=mssfxs){
		alert("忙时上浮系数必须为数字!");
	}else if(parseFloat(mssfxs)>15 || parseFloat(mssfxs)<0){
		alert("忙时上浮系数必须大于等于0且小于等于15!");
	}else if(parseFloat(hzprice)!=hzprice){
		alert("核准单价必须为数字!");
	}else if(isNaN(yzhdjcdj)){
		alert("业主获电基础单价必须为数字!");
	}else if(isNaN(jfdlzb)){
		alert("尖峰电量占比必须为数字!");
	}else if(isNaN(pdlzb)){
		alert("平电量占比必须为数字!");
	}else if(isNaN(zgdjcdj)){
		alert("直供电基础单价必须为数字!");
	}else if(isNaN(gfdlzb)){
		alert("高峰电量占比必须为数字!");
	}else if(isNaN(beilv)){
		alert("倍率必须为数字!");
	}else if(parseFloat(xbsl)!=xbsl){
		alert("线变损率必须为数字!");
	}else if(parseFloat(xbsl)<0 || parseFloat(xbsl)>100){
		alert("线变损率必须为0-100数字!");
	}else if(isNaN(xbsdl)){
		alert("线变损电量必须为数字!");
	}else if(sumfgp()){
		if(confirm("您将要添加信息！确认信息正确！")){
			document.form1.action=path+"/servlet/CountySignCheckServlet?action=commit&peid="+peid;
			document.form1.submit();
			showdiv("请稍等..............");
		}	
	}
}
//比例是否只读
function zgdzgd(){
	var gdfs = document.getElementById('gdfscode').value;
	if(getgdfs(gdfs)){//直供电	
	}else{//转供电
		var obj1 = document.getElementById("jfdlzb");
		var obj2 = document.getElementById("pdlzb");
		var obj3 = document.getElementById("gfdlzb");
		var obj4 = document.getElementById("gdlzb");
		obj1.setAttribute("readOnly",true); 
		obj2.setAttribute("readOnly",true);
		obj3.setAttribute("readOnly",true);
		obj4.setAttribute("readOnly",true);	
	}
}
//判断直供电转供电 直供电返回true,转供电返回false
function getgdfs(code){
		if("gdfs06"==code || "gdfs07"==code){//直供电
		return true;
	}else{//转供电
		return false;
	}
}
//计算核准单价
function gethzdj(){
	var gdfs = document.getElementById('gdfscode').value;
	var bzdj = document.form1.sbzprice.value;//省标准单价
	var xs;
	if(getgdfs(gdfs)){
		xs = Number(document.form1.mssfxs.value).toFixed(2);
	}else{
		xs = Number(document.form1.glfsfxs.value).toFixed(2);
	}
	var hzdj = Number(bzdj*(1+xs/100)).toFixed(4);
	document.form1.hzprice.value = hzdj;
}
//计算线变损电量
function getxbsdl(){
	var sdb = document.form1.sdb.value;//省定标
	var xbsl = document.form1.xbsl.value;//线变损率
	var xbsdl = parseFloat(sdb)*(parseFloat(xbsl)/100);
	document.form1.xbsdl.value = xbsdl.toFixed(2);
	
}


 	function modifyjz2(id,bzw){
     		document.form1.action=path+"/servlet/DownLoadAction?wyid="+id+"&bzw="+bzw;
     		document.form1.submit();

    }
 	
 			function exportModel(idcz){
	      document.form1.action=path+"/servlet/download?action=downloadpriceover&idcz="+idcz+"&templetname="+encodeURIComponent("单价超标核实模板");            
	      document.form1.submit();
	}

$(function() {
	$("#uploading").click(function() {
		shangchuan();
	});
		$("#daochu").click(function(){
		var idcz = '${bean.peid}';
		exportModel(idcz);
	});
});

</script>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
  </head>
  <body onload="zgdzgd();gethzdj();getxbsdl();">
  <fieldset>
  	 			<table width="97%" align="right" CellSpacing="1">
	 				<tr>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>站点名称：</div></td>
				        <td width="12%"><input type="text" id="zdname" name="zdname"  readonly="readonly" value="${bean.zdname}" class="form2" /></td>
				        <td bgcolor="#DDDDDD"  class="form_label"><div>站点ID：</div></td>
				        <td width="12%"><input type="text" id="zdid" name="zdid"  readonly="readonly" value="${bean.zdid}" class="form2" /></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>站点属性：</div></td>
				        <td width="12%"><input type="text" id="proeprty" name="proeprty"  readonly="readonly" value="${bean.property}" class="form2"/></td>
				         <td bgcolor="#DDDDDD"   class="form_label"><div>站点类型：</div></td>
				        <td width="12%"><input type="text"  id="stationtype" name="stationtype"  readonly="readonly" value="${bean.stationtype}" class="form2"/></td>
				     </tr>
				     <tr>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>城市：</div></td>
					      <td><input type="text" id="city" name="city"  readonly="readonly" value="${bean.city}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>区县：</div></td>
					      <td><input type="text" id="xian" name="xian"  readonly="readonly" value="${bean.xian}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>乡镇：</div></td>
					      <td><input type="text" id="xiaoqu" name="xiaoqu"  readonly="readonly" value="${bean.xiaoqu}"   class="form2" /></td>
					      <td></td>
					      <td></td>
				     </tr>
				     <tr>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>报账月份：</div></td>
					      <td><input type="text" id="accountmonth" name="accountmonth"  readonly="readonly" value="${bean.accountmonth}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>报账电费：</div></td>
					      <td><input type="text" id="bzdf" name="bzdf"  readonly="readonly" value="${bean.bzdf}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>报账电量：</div></td>
					      <td><input type="text" id="bzdl" name="bzdl"  readonly="readonly" value="${bean.bzdl}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>实际单价：</div></td>
					      <td><input type="text" id="sjprice" name="sjprice"  readonly="readonly" value="${bean.sjprice}"   class="form2" /></td>
				     </tr>
				     <tr>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>标准单价：</div></td>
					      <td><input type="text" id="bzprice" name="bzprice"  readonly="readonly" value="${bean.bzprice}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>偏离度：</div></td>
					      <td><input type="text" id="pld" name="pld"  readonly="readonly" value="${bean.pld}%"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>供电方式：</div></td>
					      <td><input type="text" id="gdfs" name="gdfs"  readonly="readonly" value="${bean.gdfs}"   class="form2" /></td>
					      <td></td>
					      <td></td>
				     </tr>
				</table>
	</fieldset>
	  <form action="" name="form1" method="POST" id="form" enctype=multipart/form-data>
	  <fieldset>
  	 			<table width="97%" align="right" CellSpacing="1">
	 				<tr>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>业主用电类型：</div></td>
				       <td width="12%">
				         	<select name="yzydlx" id ="yzydlx" class="form2">
					         		<%
						         	ArrayList yzydlist = new ArrayList();
					         		yzydlist = ztcommon.getSelctOptions("YZYDLX");
						         	if(yzydlist!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)yzydlist.get(0)).intValue();
						         		for(int i=cscount;i<yzydlist.size()-1;i+=cscount)
					                    {
					                    	code=(String)yzydlist.get(i+yzydlist.indexOf("CODE"));
					                    	name=(String)yzydlist.get(i+yzydlist.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						       </select>
				             </td> 
				        
				        
				        
				        <td bgcolor="#DDDDDD"  class="form_label"><div>业主获电基础单价：</div></td>
				        <td width="12%"><input type="text" id="yzhdjcdj" name="yzhdjcdj"  value="${bean.yzhdjcdj}" class="form2" /></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>核准单价：</div></td>
				        <td width="12%"><input type="text" id="hzprice"  name="hzprice"  readonly="readonly" value="" class="form2"/></td>
	      				<td class="form_label"></td>
					    <td width="12%"><input type="text" class="formkong" />
					    <input type="hidden" name="tidc" value="0"/>
					    <input type="hidden" name="peid" value="${bean.peid}"/>
					    <input type="hidden" name="gdfscode" id = "gdfscode" value="${bean.gdfscode}"/>
					    <input type="hidden" name="personnal" value="<%=personnal%>" />
					    <input type="hidden" name="accountid" value="<%=accountid%>" />
					    </td>
				     </tr>
				     <tr>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>省标准单价：</div></td>
					      <td><input type="text" id="sbzprice" name="sbzprice"  readonly="readonly" value="${bean.bzprice}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>尖峰电量占比(%)：</div></td>
					      <td><input type="text"  id="jfdlzb" name="jfdlzb"  value="${bean.jfdlzb}"   class="form2" /></td>
					       <td bgcolor="#DDDDDD"   class="form_label"><div>平电量占比(%)：</div></td>
					      <td><input type="text" id="pdlzb" name="pdlzb"  value="${bean.pdlzb}"   class="form2" /></td>
					      <td></td>
					      <td></td>
				     </tr>
				     <tr>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>直供电基础单价：</div></td>
					      <td><input type="text" id="zgdjcdj" name="zgdjcdj"  value="${bean.zgdjcdj}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>高峰电量占比(%)：</div></td>
					      <td><input type="text" id="gfdlzb"  name="gfdlzb"   value="${bean.gfdlzb}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>谷电量占比(%)：</div></td>
					      <td><input type="text"  id="gdlzb" name="gdlzb"   value="${bean.gdlzb}"   class="form2" /></td>
					      <td></td>
					      <td></td>
				     </tr>
				      <tr>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>变压器容量：</div></td>
					      <td><input type="text" id="byqrl" name="byqrl"  value="${bean.byqrl}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>倍率：</div></td>
					      <td><input type="text" id="beilv" name="beilv"  value="${bean.beilv}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>用电属性：</div></td>
					      <td>
				         	<select name="ydsx" id ="ydsx" class="form2">
					         		<%
						         	ArrayList ydsxlist = new ArrayList();
					         		ydsxlist = ztcommon.getSelctOptions("YDSX");
						         	if(ydsxlist!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)ydsxlist.get(0)).intValue();
						         		for(int i=cscount;i<ydsxlist.size()-1;i+=cscount)
					                    {
					                    	code=(String)ydsxlist.get(i+ydsxlist.indexOf("CODE"));
					                    	name=(String)ydsxlist.get(i+ydsxlist.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						       </select>
				             </td>
					      <td></td>
					      <td></td>
				     </tr>
				     <tr>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>省定标：</div></td>
					      <td><input type="text" id="sdb"  name="sdb"  readonly="readonly" value="${bean.sdb}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>线变损率(%)：</div></td>
					      <td><input type="text" id="xbsl"  name="xbsl" value="${bean.xbsl}" onchange ="getxbsdl();"  class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>线变损电量：</div></td>
					      <td><input type="text" id="xbsdl" name="xbsdl" value="" readonly="readonly"  class="form2" /></td>
					      <td></td>
					      <td></td>
				     </tr>
				     <tr>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>管理费上浮系数(%)：<font style="color: #FF0000;font-weight: bold">*</font></div></td>
					      <td><input type="text" id="glfsfxs" name="glfsfxs" value="${bean.glfsfxs}" onchange="gethzdj()"  class="form2" /> </td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>忙时上浮系数(%)：<span style="color: #FF0000;font-weight: bold">*</span></div></td>
					      <td><input type="text" id="mssfxs" name="mssfxs" value="${bean.mssfxs}"  onchange="gethzdj()" class="form2" /> </td>
					      <td></td>
					      <td></td>
					      <td></td>
					      <td></td>
				     </tr>
				</table>
	</fieldset>
	<br/>
	  <fieldset>
  	 			<table width="97%" align="right" CellSpacing="1">
	 				<tr>
				        <td bgcolor="#DDDDDD" class="form_label">
				        	<div>用电合同附件：</div>
						</td>
						<td width='400px'>
							<div><input type="file" id="fileUp1" name="fileUp1" height="19px" width="130px"/>
							<c:choose>
							<c:when test="${f1 eq true}">
							<a href="#" onclick="modifyjz2('${bean.peid}','1');return false;">
										<font  style="font-size: 15px;color: red">下载附件</font></a>
							</c:when>
							<c:when test="${f1 eq false}">
							<font  style="font-size: 15px;color: red">无附件</font>
							</c:when>
							</c:choose>
							</div>
							</td>
						<td colspan='2'><div style=" height: 19px; position: relative; left: 10px; font-size: 14px; color: red">单个附件不能超过3M</div></td>
				     </tr>
				     <tr>
					     <td bgcolor="#DDDDDD" class="form_label"><div>
									忙时增益依据(照片)：
								</div></td>
								<td><div>
									<input type="file" id="fileUp2" name="fileUp2" height="19px" width="130px"/>
							<c:choose>
							<c:when test="${f2 eq true}">
							<a href="#" onclick="modifyjz2('${bean.peid}','2');return false;">
										<font  style="font-size: 15px;color: red">下载附件</font></a>
							</c:when>
							<c:when test="${f2 eq false}">
							<font  style="font-size: 15px;color: red">无附件</font>
							</c:when>
							</c:choose>
								</div></td>
								<td colspan='2'><div style=" height: 19px; position: relative;  left: 10px;font-size: 14px; color: red">附件总大小不能超过6M</div></td>
				     </tr>
				     <tr>
					     <td bgcolor="#DDDDDD" class="form_label"><div>
									业主尖峰电量明细(照片)：
								</div></td>
								<td><div>
									<input type="file" id="fileUp3" name="fileUp3" height="19px" width="130px"/>
							<c:choose>
							<c:when test="${f3 eq true}">
							<a href="#" onclick="modifyjz2('${bean.peid}','3');return false;">
										<font  style="font-size: 15px;color: red">下载附件</font></a>
							</c:when>
							<c:when test="${f3 eq false}">
							<font  style="font-size: 15px;color: red">无附件</font>
							</c:when>
							</c:choose>
								</div></td>
								<td width="90px"><div id="daochu"
									style="position: relative;cursor: pointer; width: 80px; height: 19px; float: left;left: 10px;">
									<img alt=""
										src="<%=request.getContextPath()%>/images/shangchuan.png"
										width="100%" height="100%" />
									<span
										style="font-size: 12px; position: absolute; left: 28px; top: 3px; color: white">下载模板</span>
								</div></td>
								<td><div style=" height: 19px; position: relative; font-size: 14px; color: red">注:三个附件模板通用</div></td>
								
				     </tr>
				     <tr><td colspan='4'><div id="errorInfo"
									style=" height: 19px; position: relative; font-size: 14px; color: red">
									<c:forEach items="${requestScope.statusInfo}" var="info">
										<br />${info}
									</c:forEach>
								</div></td>
					</tr>
				     </table>
	</fieldset>
	
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
				 <tr>
				    	<td><div id="uploading"
									style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 400px">
									<img alt=""
										src="<%=request.getContextPath()%>/images/shangchuan.png"
										width="100%" height="100%" />
									<span
										style="font-size: 12px; position: absolute; left: 26px; top: 3px; color: white">提交</span>
								</div>
					   </td>
				    </tr>
			</table>
				
				</form>
  </body>
  <script type="text/javascript">
  document.form1.yzydlx.value = '<%=yzydlx%>';
  document.form1.ydsx.value = '<%=ydsx%>';
  </script>
</html>
