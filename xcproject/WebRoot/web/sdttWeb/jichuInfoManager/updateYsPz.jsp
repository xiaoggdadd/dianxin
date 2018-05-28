<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account,com.noki.jizhan.daoru.YspzDao,com.noki.jizhan.model.YspzClass"%>
<%
	String path = request.getContextPath();
	String sheng = (String) session.getAttribute("accountSheng");
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId(); //权限ID
	String AccountID = account.getAccountId();//用户登陆名称
	String AccountName = account.getAccountName();//用户登陆名称
	String loginName = account.getName(); //用户名称
	pageContext.setAttribute("loginName", loginName);
	pageContext.setAttribute("AccountName", AccountName);
	pageContext.setAttribute("AccountID", AccountID);
	pageContext.setAttribute("loginId", loginId);
	String shi = request.getParameter("shi") != null ? request.getParameter("shi") : "0";
	String xian = request.getParameter("xian") != null ? request.getParameter("xian") : "0";
	String xiaoqu = request.getParameter("xiaoqu") != null ? request.getParameter("xiaoqu") : "0";
	
	//根据ID获取Yspz数据变为修改默认值
	String id = request.getParameter("id");
	String month = "";
	String money = "";
	ArrayList<YspzClass> al = new ArrayList<YspzClass>();
	YspzDao dao = new YspzDao();
	al = dao.SelectYspz(id);
	for(int i=0;i<al.size();i++){
		month = al.get(i).getMonth();
		money = al.get(i).getMoney();
	}
System.out.println(shi);
System.out.println(xian);
System.out.println(xiaoqu);
System.out.println(month);
System.out.println(money);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--[if gt IE 8]><!-->
<script type="text/javascript"
	src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
<!--<![endif]-->
<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--[if gt IE 8]><!-->
<script type="text/javascript"
	src="<%=path%>/javascript/jquery-3.3.1.min.js">
	
</script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
<!--<![endif]-->
<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<jsp:useBean id="scode" scope="page" class="com.ptac.app.util.Code"></jsp:useBean>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="flowBean" scope="page"
	class="com.noki.mobi.flow.javabean.FlowBean">
</jsp:useBean>
</head>
<body>
	<form action="" name="form1" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr valign="top">
				<td width="12"><img src="../images/space.gif" width="12"
					height="17" />
				</td>
				<td>
					<div class="content01">
						<div class="tit01">修改预算</div>
						<div class="content01_1">
							<table width="1000px" border="0" cellpadding="2" cellspacing="0"
								class="tb_select">
								<tr>
									<td align="right" width="100px">地市</td>
									<td width="100px"><select id="shi" name="shi"
										style="width: 130px" onchange="changeCity()">
											<option value="0">请选择</option>
											<%
												ArrayList shilist = new ArrayList();
												shilist = commBean.getAgcode(sheng, shi, AccountName);
												if (shilist != null) {
													String sfid = "", sfnm = "";
													int scount = ((Integer) shilist.get(0)).intValue();
													for (int i = scount; i < shilist.size() - 1; i += scount) {
														sfid = (String) shilist
																.get(i + shilist.indexOf("AGCODE"));
														sfnm = (String) shilist
																.get(i + shilist.indexOf("AGNAME"));
											%>
											<option value="<%=sfid%>" ><%=sfnm%></option>
											<%
												}
												}
											%>
									</select> <span style="color: #FF0000; font-weight: bold">*</span></td>
									<td align="right" width="100px">区县</td>
									<td width="100px"><select name="xian"
										style="box-sizing: border-box; width: 130px;"
										class="selected_font" onchange="changeCountry()">
											<option value="0">请选择</option>
											<%
												ArrayList xianlist = new ArrayList();
												xianlist = commBean.getAgcode(shi, xian, AccountName);
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
									</td>
									<td align="right" width="100px">乡镇</td>
									<td width="100px"><select name="xiaoqu" id="xiaoqu"
										style="box-sizing: border-box; width: 130px;"
										onchange="changezdmc()" class="selected_font">
											<option value="0">请选择</option>
											<%
												ArrayList xiaoqulist = new ArrayList();
												xiaoqulist = commBean.getAgcode(xian, xiaoqu, AccountName);
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
									</td>
									<td align="right" width="100px">月份</td>
									<td>
										<input type="text" style="box-sizing:border-box;width:130px"
										class="Wdate" name="month" id="month"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
										value="<%=month%>" /> <span style="color: #FF0000; font-weight: bold">*</span>
									</td>
									<td align="right" width="100px">预算金额(万元)</td>
									<td><input name="money" id="money" type="text"  value="<%=money%>"/> 
									<span style="color: #FF0000; font-weight: bold">*</span> 
										<input name="loginName" id="loginName" type="hidden" value=${loginName } /> 
										<input name="AccountName" id="AccountName" type="hidden" value=${AccountName } /> 
										<input name="loginId" id="loginId" type="hidden" value=${loginId } />
										<input name="AccountID" id="AccountID" type="hidden" value=${AccountID } /></td>
										<input name="Yid" id="Yid" type="hidden" value="<%=id%>"/></td>
								</tr>
								<tr>
									<td align="right" colspan="8" height="60px"><input
										name="baocun" type="button" class="btn_c1"
										onclick="saveStation()" id="baocun" value="保存" />&nbsp; <input
										name="resetBtn" type="button" class="btn_c1" id="resetBtn"
										value="重置" />&nbsp;</td>
								</tr>
							</table>
						</div>
				</div>
	<script language="javascript">
			var path = '<%=path%>';
			var XMLHttpReq;
			//XMLHttpReq = createXMLHttpRequest();
			function createXMLHttpRequest() {
				if(window.XMLHttpRequest) { //Mozilla 浏览器
					XMLHttpReq = new XMLHttpRequest();
				} else if(window.ActiveXObject) { // IE浏览器
					try {
						XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
					} catch(e) {
						try {
							XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
						} catch(e) {}
					}
				}
			}
			///////////////////////////////////////////////////////////
			//级联查询js
			function sendRequest(url, para) {

				createXMLHttpRequest();

				XMLHttpReq.open("GET", url, true);

				if(para == "sheng") {
					XMLHttpReq.onreadystatechange = processResponse_sheng; //指定响应函数
				} else if(para == "shi") {
					XMLHttpReq.onreadystatechange = processResponse_shi;
				} else if(para == "xian") {
					XMLHttpReq.onreadystatechange = processResponse_xian;
				} else {
					XMLHttpReq.onreadystatechange = processResponse; //指定响应函数
				}
				//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
				XMLHttpReq.send(null);
			}
			/////////////////////////////////////////////////////////////
			// 处理返回信息函数
			function processResponse() {

				if(XMLHttpReq.readyState == 4) { // 判断对象状态
					//alert(XMLHttpReq.status);
					if(XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
						//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
						var res = XMLHttpReq.responseText;
						window.alert(res);

					} else { //页面不正常
						window.alert("您所请求的页面有异常。");
					}
				}
			}

			function processResponse_sheng() {
				if(XMLHttpReq.readyState == 4) { // 判断对象状态

					if(XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
						var res = XMLHttpReq.responseXML.getElementsByTagName("res");

						//var res = XMLHttpReq.responseText;

						updateShi(res);

					} else { //页面不正常
						window.alert("您所请求的页面有异常。");
					}
				}
			}

			function processResponse_shi() {

				if(XMLHttpReq.readyState == 4) { // 判断对象状态
					//alert(XMLHttpReq.status);
					if(XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
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

				if(XMLHttpReq.readyState == 4) { // 判断对象状态
					//alert(XMLHttpReq.status);
					if(XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
						//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
						//var res = XMLHttpReq.responseText;
						var res = XMLHttpReq.responseXML.getElementsByTagName("res");
						updateZd(res);

					} else { //页面不正常
						window.alert("您所请求的页面有异常。");
					}
				}
			}

			function changeSheng() {
				var sid = document.form1.sheng.value;

				if(sid == "0") {
					var shilist = document.all.shi;
					shilist.options.length = "0";
					shilist.add(new Option("请选择", "0"));
					return;
				} else {
					//alert("11111");
					sendRequest(path + "/servlet/garea?action=sheng&pid=" + sid, "sheng");

				}
			}

			function updateShi(res) {
				var shilist = document.all.shi;
				shilist.options.length = "0";
				shilist.add(new Option("请选择", "0"));
				for(var i = 0; i < res.length; i += 2) {
					shilist.add(new Option(res[i + 1].firstChild.data, res[i].firstChild.data));
				}
			}

			function changeCity() {
				var sid = document.form1.shi.value;

				if(sid == "0") {
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

				for(var i = 0; i < res.length; i += 2) {
					shilist.add(new Option(res[i + 1].firstChild.data, res[i].firstChild.data));
				}
			}

			function changeCountry() {
				var sid = document.form1.xian.value;

				if(sid == "0") {
					var shilist = document.all.xiaoqu;
					shilist.options.length = "0";
					shilist.add(new Option("请选择", "0"));
					return;
				} else {
					sendRequest(path + "/servlet/garea?action=xian&pid=" + sid, "xian");
				}
			}

			function updateZd(res) {
				var shilist = document.all.xiaoqu;
				shilist.options.length = "0";
				shilist.add(new Option("请选择", "0"));

				for(var i = 0; i < res.length; i += 2) {
					shilist.add(new Option(res[i + 1].firstChild.data, res[i].firstChild.data));
				}
			}
	$(function() {

		$("#resetBtn").click(function() {
			$.each($("form input[type='text']"), function() {
				$(this).val("");
			})
		});
	});
	function saveStation() {
		var shi = $("#shi").val();
		var month = $("#month").val();
		var money = $("#money").val();
		var xian = $("#xian").val();
		var xiaoqu = $("#xiaoqu").val();
		if (shi == "" || shi == null) {
			alert("地市不能为空");
			return;
		}
		if (month == "" || month == null) {
			alert("月份不能为空");
			return;
		}
		if (money == "" || money == null) {
			alert("金额不能为空");
			return;
		}
		if (money != null && money != "" && !ismoney(money)) {
			alert("预算金额必须为数字,最多两位小数！");
			return;
		}
		addyspz(shi, month, money);
	}
	function addyspz(shi, month, money) {
		if (confirm("您确定要修改此条预算信息?")) {
			document.form1.action = path + "/bz_solo_servlet?action=updateyspz";
			document.form1.submit();
			showdiv("请稍等..............");
		}
	}
	//EL校验
	function isNaN2(val) {
		return val.match(new RegExp("^[0-9]+$"));
	}
	function ismoney(str) {
		if (/^(([0-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(str))
			return true;
		return false;
	}
	function ismoneys(str) {
		if (/^(([0-9][0-9]*)|(([0]\.\d{1,4}|[1-9][0-9]*\.\d{1,4})))$/.test(str))
			return true;
		return false;
	}
	function isNa(val) {
		return val.match(new RegExp("^\d+\.\d+$"));
	}
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
</script>
		</form>
	</body>
</html>
