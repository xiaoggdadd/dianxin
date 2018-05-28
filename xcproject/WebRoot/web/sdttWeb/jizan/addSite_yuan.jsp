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
          	if (checkNotSelected(document.form1.shi,"城市")&&
          			checkNotSelected(document.form1.xian,"区县")&&
          			checkNotSelected(document.form1.xiaoqu,"乡镇")&&
          			checkNotSelected(document.form1.jzproperty,"站点属性")&&
          	       	checkNotSelected(document.form1.stationtype,"站点类型")&&
          	        checkNotnull(document.form1.jzname,"站点名称")&& //判断是否为空
          			checkNotSelected(document.form1.gdfs,"供电方式")&&
          			checkNotnull(document.form1.zjcode,"站点编码")&&
          		  	checkNotSelected(document.form1.gxxx,"共享信息")&&
          		    checkNotnull(document.form1.jingdu,"经度")&&
          			checkNotnull(document.form1.weidu,"纬度")&&
          			checkNotSelected(document.form1.yflx,"房屋类型")&&
          			checkNotSelected(document.form1.zdcq,"原产权方")&&
          			checkNotnull(document.form1.wlzdbm,"物理站点编码")&&
          			checkNotSelected(document.form1.zzlx,"站址类型")&&
          			checkNotnull(document.form1.ltqx,"对应联通区县")&&
          			checkNotnull(document.form1.ydqx,"对应移动区县")&&
          			checkNotnull(document.form1.xtid,"能耗系统id")&&
          			checkNotnull(document.form1.address,"地址")&&
          			checkNotnull(document.form1.area,"面积")&&
          			checkNotnull(document.form1.phone,"负责人联系方式")&&
          			checkNotnull(document.form1.gdfname,"供电方名称")&&
          			checkNotnull(document.form1.bumen,"部门")&&
          			checkNotnull(document.form1.fuzeren,"负责人")
           	 )
           	 	var zdbm = document.form1.zjcode.value;
           	 	var jd = document.form1.jingdu.value;
           	 	var wd = document.form1.weidu.value;
           	 	var a = document.form1.xtid.value;
           	 	var b = document.form1.phone.value;
           	 	var mj = document.form1.area.value;
           	 	if(!isNaN(zdbm)){
           	 		alert("站点编码必须为数字！");
           	 		return false;
           	 	}
           	 	if(isNa(jd)){
           	 		alert("经度必须为数字！");
           	 		return false;
           	 	}
           	 	if(isNa(wd)){
           	 		alert("纬度必须为数字");
           	 		return false;
           	 	}
           	 	if(!isNaN(a)){
           	 		alert("能耗系统id必须为数字！");
           	 		return false;
           	 	}
           	 	if(!isNaN(b)){
           	 		alert("负责人联系方式必须为数字！");
           	 		return false;
           	 	}
           	 	if(isNa(mj)){
           	 		alert("面积必须为数字！");
           	 		return false;
           	 	}
           	 {		          		  		
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
        	function isNaN(val) { 
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
							<td align="right" width="100px">城市</td>
							<td width="100px">
								<select name="shi" style="width:130;" onchange="changeCity()">
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
					         	</select>&nbsp;
							</td>
							<td align="right" width="100px">区县</td>
							<td width="100px">
								<select name="xian" style="width:130" onchange="changeCountry()">
         							<option value="0">请选择</option>
         								</select>
							</td>
							<td align="right" width="100px">乡镇</td>
							<td width="100px">
								<select name="xiaoqu" id="xiaoqu" style="width:130">
         							<option value="0">请选择</option>
         								</select>
							</td>
							<td align="right" width="100px">站点属性</td>
							<td width="100px">
								<select name="jzproperty" style="width:130" onchange="zdsx()" title="必填项，根据下拉值选择填写">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList zdsx = new ArrayList();
						         	zdsx = ztcommon.getSelctOptions("ZDSX");
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
					         	</select>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">站点类型</td>
							<td width="100px">
								<select name="stationtype" style="width:130" title="必填项，根据下拉值选择填写">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList stationtype = new ArrayList();
						         	stationtype = ztcommon.getZdlx(jzproperty);
					         		 stationtype = ztcommon.getSelctOptions("StationType");
						         	if(stationtype!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)stationtype.get(0)).intValue();
						         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
					                    {
					                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
					                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">站点名称</td>
							<td width="100px">
								<input type="text" name="jzname" value="" style="width:130px" title="必填项,根据站点所在建筑物名称填写 例如：软件科技园D座楼"/>
							</td>
							<td align="right" width="100px">供电方式</td>
							<td width="100px">
								<select name="gdfs"  style="width:130" title="必填项，根据下拉值选择填写">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList gdfs = new ArrayList();
						         	gdfs = ztcommon.getSelctOptions("GDFS");
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
							<td align="right" width="100px">站点编码</td>
							<td width="100px">
								<input type="text" name="zjcode" style="width:130px"/>
							</td>
						</tr>
						
						<tr>
							<td align="right" width="100px">共享信息</td>
							<td width="100px">
								<select name="gxxx" style="width:130">
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
								<input type="text" name="jingdu" style="width:130px"/>
							</td>
							<td align="right" width="100px">纬度</td>
							<td width="100px">
								<input type="text" name="weidu" style="width:130px"/>
							</td>
							<td align="right" width="100px">房屋类型</td>
							<td width="100px">
								<select name="yflx"  style="width:130" title="根据下拉值选择填写，用房类型包括：砖混、彩钢板+直流负荷 来判断基站或接入网的22个基站类型和接入网类型">
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
								<select name="zdcq" id="zdcq" style="width:130">
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
								<input type="text" name="wlzdbm" style="width:130px"/>
							</td>
							<td align="right" width="100px">站址类型</td>
							<td width="100px">
								<select name="zzlx" id="zzlx" style="width:130">
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
								<input type="text" name="ltqx" style="width:130px"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">对应移动区县</td>
							<td width="100px">
								<input type="text" name="ydqx" style="width:130px"/>&nbsp;
							</td>
							<td align="right" width="100px">联通能耗系统ID</td>
							<td width="100px">
								<input type="text" name="xtid" style="width:130px"/>
							</td>
							<td align="right" width="100px">站点地址</td>
							<td width="100px">
								<input type="text" name="address"  style="width:130px" title="选填项，站点所在的具体物理位置 "/>
							</td>
							<td align="right" width="100px">站点面积（㎡）</td>
							<td width="100px">
								<input type="text" name="area" style="width:130px" title="选填项，根据站点所在地的具体面积填写 单位：平方米"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">负责人联系方式</td>
							<td width="100px">
								<input type="text" name="phone" style="width:130px"/>&nbsp;
							</td>
							<td align="right" width="100px">供电方名称</td>
							<td width="100px">
								<input type="text" name="gdfname" style="width:130px"/>
							</td>
							<td align="right" width="100px">部门</td>
							<td width="100px">
								<input type="text" name="bumen" style="width:130px"/>
							</td>
							<td align="right" width="100px">站点责任人</td>
							<td width="100px">
								<input type="text" name="fuzeren"  style="width:130px" title="选填项，站点具体负责的人员"/>&nbsp;
							</td>
						</tr>
						
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
function isNaN(val) { 
return val.match(new RegExp("^[0-9]+$")); 
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
