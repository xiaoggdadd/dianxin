<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
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
	session.setAttribute("id", id);
	String bz = request.getParameter("bz");
	SiteModifyBean form = new SiteModifyBean();

	String byqrl = "", glys = "", dj = "", shi = "0", xian = "0", xiaoqu = "0";
	String zhdj = "", jc = "", zddj = "", phddl = "", rl = "", zb = "";
	if (bz != null && bz.equals("1") && id != null) {
		String sql = "select d.byqrl,to_char(d.glys,'fm9999999990.00') glys,to_char(d.dj,'fm9999999990.0000') dj,to_char(d.zhdj,'fm9999999990.0000') zhdj,to_char(d.jc,'fm9999999990.0000') jc,to_char(d.zddj,'fm9999999990.00') zddj,d.phddl,d.rl,to_char(d.zb,'fm9999999990.00') zb,d.shi,d.xian,d.xiaoqu from dgyydgl d where id=" + id + "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		db.connectDb();
		System.out.println("sql：" + sql);
		rs = db.queryAll(sql.toString());
		while (rs.next()) {
			byqrl = rs.getString("BYQRL") == null ? "" : rs
					.getString("BYQRL");
			glys = rs.getString("GLYS") == null ? "" : rs
					.getString("GLYS");
			dj = rs.getString("DJ") == null ? "" : rs.getString("DJ");
			zhdj = rs.getString("ZHDJ") == null ? "" : rs
					.getString("ZHDJ");
			jc = rs.getString("JC") == null ? "" : rs.getString("JC");
			zddj = rs.getString("ZDDJ") == null ? "" : rs
					.getString("ZDDJ");
			phddl = rs.getString("PHDDL") == null ? "" : rs
					.getString("PHDDL");
			rl = rs.getString("RL") == null ? "" : rs.getString("RL");
			zb = rs.getString("ZB") == null ? "" : rs.getString("ZB");
			shi = rs.getString("SHI") == null ? "" : rs
					.getString("SHI");
			xian = rs.getString("XIAN") == null ? "" : rs
					.getString("XIAN");
			xiaoqu = rs.getString("XIAOQU") == null ? "" : rs
					.getString("XIAOQU");
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
		</script>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
		</script>
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

		<%
			if (!"1".equals(bz)) {
		%>
		<title>新增</title>
		<%
			} else {
		%>
		<title>修改</title>
		<%
			}
		%>
		<script type="text/javascript">
var path = '<%=path%>';

function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = "";
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">";

	} else {
		trObject.style.display = "none";
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">";
	}
}

function ismoney(str) {
	if (/^([1-9]\d{0,15}|0)(\.\d{1,4})?$/.test(str))
		return true;
	return false;
}
function saveAccount() {
	var b = document.form1.byqrl.value;
	var g = document.form1.glys.value;
	var d = document.form1.dj.value;
	if (checkNotSelected(document.form1.shi,"城市")&&checkNotSelected(document.form1.xian,"区县")&&
	checkNotSelected(document.form1.xiaoqu,"乡镇")&&checkNotnull(document.form1.byqrl,"变压器容量")&&
	checkNotnull(document.form1.glys,"功率因素")&&checkNotnull(document.form1.dj,"一般工商及其他电价"))
	{
		
		if(!ismoney(b)){
			alert("变压器容量必须为数字，最多保留4位小数！！");
			return false;
		}
		if(!ismoney(g)){
			alert("功率因素必须为数字，最多保留4位小数！！");
			return false;
		}
		if(!ismoney(d)){
			alert("一般工商及其他电价必须为数字，最多保留4位小数！！");
			return false;
		}
		document.form1.action=path+"/servlet/dgyyd?action=add&bz="+<%=bz%>;
		document.form1.submit();
		showdiv("请稍等..............");
	}
}
	
	
       
 function fanhui(){        
    document.form1.action=path+"/web/jizhan/dianbiaolist.jsp";
    document.form1.submit();
 }
       
 function change(){
	 var b=$("#byqrl").val();
	 var c=$("#glys").val();
	 var d=$("#dj").val();
	 var e=(1.1002+0.6601+0.32)/3;
	 if(b!=""&&c!=""&&d!=""){
		 var f=d-e;
	 var g=b*0.4*40;
	 var h=g/f;
	 var i=h/30/24/c;
	 var j=i/b*100;
	 j=j.toFixed(2);
	 $("#zhdj").val(e.toFixed(4));
	 $("#jc").val(f.toFixed(4));
	 $("#zddj").val(g);
	 $("#phddl").val(parseInt(h));
	 $("#rl").val(parseInt(i));
	 $("#zb").val(j);
	 }
	 
	 
 }       
        
        
$(function(){
	$("#liulan").click(function(){
		shoulist();
	});
	$("#cancelBtn").click(function(){
	    fanhui();
	});
$("#resetBtn").click(function(){
	$.each($("form input[type='text']"),function(){
	  $(this).val("");
          });
	});
// $("#saveBtn").click(function(){
// 	saveAccount();
	
// 	});
$("#vnameBtn").click(function(){
	vName();
	});
});
			
</script>
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body>
		<form action="" name="form1" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="top">

					<td width="12">
						<img src="../images/space.gif" width="12" height="17" />
					</td>
					<td>
						<div class="content01">

							<%
								if (!"1".equals(bz)) {
							%>
							<div class="tit01">
								新增
							</div>
							<%
								} else {
							%>
							<div class="tit01">
								修改
							</div>
							<%
								}
							%>
							<div class="content01_1">
								<table width="100px" border="0" cellpadding="2" cellspacing="0"
									class="tb_select">
									<tr>
										<td align="right" width="100px">
											城市
										</td>
										<td width="100px">
											<select name="shi" class="selected_font"
												onchange="changeCity()"
												style="box-sizing: border-box; width: 130px;">
												<option value="0">
													请选择
												</option>
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
											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
											&nbsp;
										</td>
										<td align="right" width="100px">
											区县
										</td>
										<td width="100px">
											<select name="xian" id="xian"
												style="box-sizing: border-box; width: 130px;"
												class="selected_font" onchange="changeCountry()">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList xianlist = new ArrayList();
													xianlist = commBean.getAgcode(shi, form.getXian(),
															loginName); 
													System.out.println("==sql：" + xiaoqu);
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
											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											乡镇
										</td>
										<td width="100px">
											<select name="xiaoqu" id="xiaoqu"
												style="box-sizing: border-box; width: 130px;"
												onchange="changezdmc()" class="selected_font">
												<option value="0">
													请选择
												</option>
												<%
													ArrayList xiaoqulist = new ArrayList();
													xiaoqulist = commBean.getAgcode(xian, form.getXiaoqu(),
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
											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											变压器容量（KVA）
										</td>
										<td width="60px">
											<input type="text" name="byqrl" id="byqrl" onchange="change()" value="<%=byqrl%>"
												style="box-sizing: border-box; width: 130px"  placeholder="请输入数字"/>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											功率因素
										</td>
										<td width="60px">
											<input type="text" name="glys" id="glys" onchange="change()" value="<%=glys%>"
												style="box-sizing: border-box; width: 130px"  placeholder="请输入数字" />
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
										<td align="right" width="100px">
											一般工商及其他电价（元）
										</td>
										<td width="100px">

											<input type="text" name="dj" id="dj" onchange="change()"
												value="<%=dj%>" style="box-sizing: border-box; width: 130px"  placeholder="请输入数字"/>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											大工业峰谷平综合电价（元）
										</td>
										<td width="100px">
											<input type="text" name="zhdj" id="zhdj" value="<%=zhdj%>"
												style="box-sizing: border-box; width: 130px" readonly="readonly"/>
										</td>
										<td align="right" width="100px">
											价差
										</td>
										<td width="100px">
											<input type="text" name="jc" id="jc" value="<%=jc%>"
												style="box-sizing: border-box; width: 130px" readonly="readonly"/>
										</td>
										<td align="right" width="100px">
											最大需量40%基本电价（元）
										</td>
										<td width="100px">
											<input type="text" name="zddj" id="zddj" value="<%=zddj%>"
												style="box-sizing: border-box; width: 130px" readonly="readonly"/>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											平衡点电量（KWH）
										</td>
										<td width="100px">
											<input type="text" name="phddl" id="phddl" value="<%=phddl%>"
												style="box-sizing: border-box; width: 130px" readonly="readonly"/>
										</td>
										<td align="right" width="100px">
											平衡点电量折算容量
										</td>
										<td width="100px">
											<input type="text" name="rl" id="rl" value="<%=rl%>"
												style="box-sizing: border-box; width: 130px" readonly="readonly"/>
										</td>
										<td align="right" width="100px">
											占变压器比(%)
										</td>
										<td width="100px">
											<input type="text" name="zb" id="zb" value="<%=zb%>"
												style="box-sizing: border-box; width: 130px" readonly="readonly"/>
										</td>
									<tr>
										<td align="right" colspan="6" height="60px">
											<!-- 								<input name="button2" type="submit" class="btn_c1" id="button2" value="临时保存" />&nbsp;  -->
											<input onclick="saveAccount()" type="button" class="btn_c1"
												id="button2" value="保存" />
											&nbsp;
											<input type="reset" class="btn_c1" id="button2" value="重置" />
											&nbsp;
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<input type="hidden" name="zdid" value="" />
			<script type="text/javascript">
function showlist() {
	//window.open("dianbiaolist.jsp",'_blank','newwindow','height=350, width=630,top=1200,left=300,toolbar=no,menubar=no, scrollbars=yes, resizable=no,location=n o, status=no');
	window
			.open(
					'zhandianselect.jsp',
					'',
					'width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');

}

var path = '<%=path%>';
var XMLHttpReq;
//XMLHttpReq = createXMLHttpRequest();
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}

///////////////////////////////////////////////////////////
function sendRequest(url, para) {
	createXMLHttpRequest();
	
	XMLHttpReq.open("GET", url, true);

	if (para == "sheng") {
		XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
	} else if (para == "shi") {
		XMLHttpReq.onreadystatechange = processResponse_shi;
	} else if (para == "xian") {
		XMLHttpReq.onreadystatechange = processResponse_xian;
	} else if (para == "dbbm") {
		XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数			
	} else if (para == "zdmc") {
		XMLHttpReq.onreadystatechange = processResponse_zdmc;
	} else {
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
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");

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
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
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
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			//var ress=XMLHttpReq.responseXML.getElementsByTagName("res");
			updateZd(res);
			// updateUser(ress);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function processResponse_zdmc() {

	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
			//var res = XMLHttpReq.responseText;
			var res = XMLHttpReq.responseXML.getElementsByTagName("ress");
			//var ress=XMLHttpReq.responseXML.getElementsByTagName("res");
			updatezdmc(res);
			// updateUser(ress);

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
			var res = XMLHttpReq.responseXML.getElementsByTagName("ress");
			//   updateZdlx(res);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}

}
//站点类型
// function updateZdlx(res){

// 	var shilist = document.all.dbbm;
// 	shilist.options.length="0";
// 	shilist.add(new Option("请选择","0"));

// 	for(var i = 0;i<res.length;i+=2){
// 		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
// 	}
// }

function changeSheng() {
	var sid = document.form1.sheng.value;

	if (sid == "0") {
		var shilist = document.all.shi;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		//alert("11111");
		sendRequest(path + "/servlet/garea?action=sheng&pid=" + sid, "sheng");
	}
}
function changezdmc() {
	var sid = document.form1.xiaoqu.value;
	if (sid == "0") {
		var shilist = document.all.zdmc;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=quxian&pid=" + sid, "zdmc");
	}
}
function updateShi(res) {
	var shilist = document.all.shi;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
function changeCity() {
	var sid = document.form1.shi.value;
	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=shi&pid=" + sid, "shi");
	}
}
function updateQx(res) {
	var shilist = document.all.xian;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));

	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}

function changeCountry() {
	var sid = document.form1.xian.value;
	if (sid == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=xian&pid=" + sid, "xian");
	}
}
function changeDbbm() {
	var sid = document.form1.zdmc.value;
	if (sid == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=dbbm&pid=" + sid, "dbbm");
	}
}
function updateZd(res) {
	var shilist = document.all.xiaoqu;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
function updatezdmc(res) {
	var shilist = document.all.zdmc;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
function updateUser(ress) {
	var shilist = document.all.username;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));

	for ( var i = 0; i < ress.length; i += 2) {
		shilist.add(new Option(ress[i + 1].firstChild.data,
				ress[i].firstChild.data));
	}
}
document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
//document.getElementById("xian").value ='<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
</script>
		</form>

	</body>

</html>

