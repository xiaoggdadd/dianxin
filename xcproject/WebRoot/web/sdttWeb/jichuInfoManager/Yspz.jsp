<%@ page contentType="text/html; charset=utf-8"%>
<%@ page
	import="java.util.List,java.util.ArrayList,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%@ page import="com.noki.jizhan.model.YspzClass"%>
<%@ page import="com.noki.chaobiaorenyuan.Dao.uploadDao"%>
<%@ page import="com.noki.baobiaohuizong.BaoBiaoHuiZongDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String path = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"></script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	<title>无标题文档</title>
	<link href="${pageContext.request.contextPath }/web/sdttWeb/css/content.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript"src="${pageContext.request.contextPath }/javascript/tx.js"></script>
</head>
<body>
	<jsp:useBean id="commBean" scope="page"class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<%
		//主要数据 
		Account account = (Account) session.getAttribute("account"); 
		String color = null;
		String loginId = account.getAccountId();
		String loginName = (String) session.getAttribute("loginName");
		String roleId = (String) session.getAttribute("accountRole");
		//级联查询传输数据 
		String sheng = (String) session.getAttribute("accountSheng");
		String shi = request.getParameter("shi") != null ? request.getParameter("shi"):"0";
		String xian = request.getParameter("xian") != null ? request.getParameter("xian"):"0";
		String xiaoqu = request.getParameter("xiaoqu") != null ? request.getParameter("xiaoqu"):"0";
		//分页需要的数据 
		String rgsh = request.getParameter("rgsh");	
		String rgsh2 = request.getParameter("caiji");
		int count = 0,curpage = 1,pagesize = 10,nextpage = 1, prepage = 1, allpage = 1, xh = 1;
		//数据遍历获取
		YspzClass stl = new YspzClass();//实体类
		BaoBiaoHuiZongDao dao = new BaoBiaoHuiZongDao();//DAO方法
		List<YspzClass> al = new ArrayList<YspzClass>();//泛型集合
		//获取共有几页
		allpage = dao.pagesYspzClass(shi, xian, xiaoqu); //共有几页
		String yeshu = request.getParameter("curpage"); //获取分页查询页数 
		if (yeshu != null && !yeshu.equals("") && !yeshu.equals("0")) { //不为空则重新赋值
			curpage = Integer.valueOf(yeshu).intValue();//强转int
		}
		al = dao.queryYspzClass(curpage, shi, xian, xiaoqu);//根据条件获取要显示的内容 
		int intnum = pagesize * (curpage - 1) + 1;//计算显示序号
		
		//调用文件DAO需要使用其中的根据用户ID查询与户名的方法
		uploadDao wjDAO = new uploadDao();
	%>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr valign="top">
			<td width="12"><img src="../images/space.gif" width="12"
				height="17" />
			</td>
			<td>
				<div class="content01">
					<div class="tit01">预算配置信息</div>
					<div class="content01_1">
						<form action="" name="form1" method="post">
							<table width="800px" border="0" cellpadding="1" cellspacing="0"
								class="tb_select">
								<tr>
									<td align="right" width="60px">城市：</td>
									<td align="left" width="124px"><select name="shi" id="shi"
										style="width:124px;" onchange="changeCity()">
											<option value="0">请选择</option>
											<%
				         			ArrayList shilist = new ArrayList();
				         			shilist = commBean.getAgcode(sheng, shi, loginName);
				         			if (shilist != null) {
				         				String agcode = "", agname = "";
				         				int scount = ((Integer) shilist.get(0)).intValue();
				         				for (int i = scount; i < shilist.size() - 1; i += scount) {
				         			 	agcode = (String) shilist
				         							.get(i + shilist.indexOf("AGCODE"));
				         					agname = (String) shilist
				         							.get(i + shilist.indexOf("AGNAME"));
				         		%>
											<option value="<%=agcode%>">
												<%=agname%>
											</option>
											<%
				                    	}
				                    }
				                    %>
									</select></td>
									<td align="right" width="60px">区县：</td>
									<td align="left" width="124px"><select name="xian"
										id="xian" style="width:124px;" onchange="changeCountry()"
										class="selected_font">
											<option value="0">请选择</option>
											<%
				         		ArrayList xianlist = new ArrayList();
				         		xianlist = commBean.getAgcode(shi, xian, loginName);
				         			if (xianlist != null) {
				         				String agcode = "", agname = "";
				         				int scount = ((Integer) xianlist.get(0)).intValue();
				         				for (int i = scount; i < xianlist.size() - 1; i += scount) {
				         					agcode = (String) xianlist
				         							.get(i + xianlist.indexOf("AGCODE"));
				         					agname = (String) xianlist
				         							.get(i + xianlist.indexOf("AGNAME"));
				         		%>
											<option value="<%=agcode%>">
												<%=agname%>
											</option>
											<%
				                    	}
				                    }
				                    %>
									</select></td>
									<td align="right" width="60px">乡镇：</td>
									<td align="left" width="124px"><select name="xiaoqu"
										id="xiaoqu" style="width:124px;" class="selected_font">
											<option value="0">请选择</option>
											<%
			         			ArrayList xiaoqulist = new ArrayList();   //下拉列表
			         			xiaoqulist = commBean.getAgcode(xian, xian, loginName);
			         			if (xiaoqulist != null) {
			         				String agcode = "", agname = "";
			         				int scount = ((Integer) xiaoqulist.get(0)).intValue();
			         				for (int i = scount; i < xiaoqulist.size() - 1; i += scount) {
			         					agcode = (String) xiaoqulist.get(i
			         							+ xiaoqulist.indexOf("AGCODE"));
			         					agname = (String) xiaoqulist.get(i
			         							+ xiaoqulist.indexOf("AGNAME"));
			         			%>
											<option value="<%=agcode%>">
												<%=agname%>
											</option>
											<%
			                    	}
			                    }
			                    %>
									</select></td>
								</tr>
								<tr>
									<td align="right" colspan="8"><input type="button"
										class="btn_c1" onclick="query()" value="查询" /></td>
								</tr>
							</table>
						</form>
						<div class="tbtitle01">
							<b>预算配置</b>
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="tb_list1">
							<tr align="right">
								<td colspan="10" align="right"><input name="button2"
									type="button" onclick="addYspz()" class="btn_c1" id="button2"
									value="新增" /></td>
							</tr>
							<tr align="center">
								<th width="10">序号</th>
								<th width="100">地市</th>
								<th width="100">区县</th>
								<th width="100">乡镇</th>
								<th width="200">月份</th>
								<th width="200">金额（万元）</th>
								<th width="200">录入人</th>
								<th>操作</th>
							</tr>
							<%
									for(int i=0;i<al.size();i++){
										//为null的话默认为空
										String YID = al.get(i).getYid()!= null ? al.get(i).getYid():"";//ID
										String shi_code = al.get(i).getShi()!= null ? al.get(i).getShi():"";//地市
										String xian_code = al.get(i).getXian()!= null ? al.get(i).getXian():"";//区县
										String xiaoqu_code = al.get(i).getXiaoqu()!= null ? al.get(i).getXiaoqu():"";//乡镇
										String Month = al.get(i).getMonth()!= null ? al.get(i).getMonth():"";//月份
										String Money = al.get(i).getMoney() != null ? al.get(i).getMoney():"";//金额（万元）
 										String Entryperson = al.get(i).getEntryperson() != null ? al.get(i).getEntryperson():"";//综合电价（总电费/总电量）
										//根据shi,xina,xiaoqu的adcode转为中文显示
										String shi_name = dao.DQname(shi_code);
										String xian_name = dao.DQname(xian_code);
										String xiaoqu_name = dao.DQname(xiaoqu_code);
										//根据录入人ID查询显示名称
										Entryperson = wjDAO.account_name(Entryperson);
										//单价精确到小数点后两位
										double d = Double.parseDouble(Money);// 这个是转为double类型  
										DecimalFormat df = new DecimalFormat("0.00");   
										Money = df.format(d);  //这个是字符串，但已经是我要的两位小数了  
									 %>
							<tr align="center">
								<td width="10"><%=intnum++%></td>
								<td align="left"><%=shi_name%></td>
								<td align="left"><%=xian_name%></td>
								<td align="left"><%=xiaoqu_name%></td>
								<td align="left"><%=Month%></td>
								<td align="left"><%=Money%></td>
								<td align="left"><%=Entryperson%></td>
								<td>
								<a href="#" onclick="update('<%=YID%>','<%=shi_code%>','<%=xian_code%>','<%=xiaoqu_code%>')"><img src="../images/accept.png" width="16" height="16"  title="修改" /></a>
								<a href="#" onclick="delbz('<%=YID%>')"><img src="../images/delete.png" width="16" height="16"  title="删除" /></a>
								</td>
							</tr>
							<%
									}
							%>
							<tr bgcolor="#ffffff">
										<td colspan="15" align="left">
											<div align="left">

												&nbsp;&nbsp;
												<font color='#1E90FF'>
													<%
 								if (curpage != 1) {
 										out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
 								} else {
 										out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
 								}
 											%> </font> &nbsp;&nbsp;
												<font color='#1E90FF'>
													<%
 								if (curpage != 1) {
 										out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
 								} else {
 										out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
 								}
 								%> </font> &nbsp;&nbsp; 转到
												<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" style="width: 40px; font-family: 宋体; font-size: 12px; line-height: 120%;">
													<%
														for (int i = 1; i <= allpage; i++) {
																if (curpage == i) {
																	out.print("<option value='" + i
																			+ "' selected='selected'>" + i + "</option>");
																} else {
																	out.print("<option value='" + i + "'>" + i
																			+ "</option>");
																}
															}
													%>
												</select>&nbsp;&nbsp;共
												<font color='#1E90FF'><b><%=allpage%></b>&nbsp;</font>页&nbsp;&nbsp;
												<font color='#1E90FF'>
													<%
 								if (allpage != 0 && (curpage < allpage)) {
 									out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");
 								} else {
 									out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");
 								}
 									%> </font> &nbsp;&nbsp;
												<font color='#1E90FF'>
													<%
 								if (allpage != 0 && (curpage < allpage)) {
 									out.print("<a href='javascript:gopagebyno(" + allpage + ")'><img src='../images/page-last.gif'></a>");
 								} else {
 									out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
 								}
									 %> </font> &nbsp;&nbsp;
											</div>
										</td>
							</tr>
						</table>
						<div class="space_h_10"></div>
					</div>
				</div></td>
		</tr>
	</table>
	<script type="text/javascript">
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
			//分页js
			function gopage() {
				document.form1.submit();
			}

			function previouspage() {
				var rgsh2 = '<%=rgsh2%>';
				var rgsh = '<%=rgsh%>';
				//document.form1.page.value = parseInt(document.form1.page.value) - 1;
				var curpage = '<%=(curpage - 1)%>';
				document.form1.action = path +
					"/web/sdttWeb/jichuInfoManager/Yspz.jsp?rgsh=" + rgsh +
					"&caiji=" + rgsh2 + "&curpage=" + curpage;
				document.form1.submit();
			}

			function nextpage() {
				var rgsh2 = '<%=rgsh2%>';
				var rgsh = '<%=rgsh%>';
				//document.form1.page.value = parseInt(document.form1.page.value) + 1;
				var curpage = '<%=(curpage + 1)%>';
				document.form1.action = path +
					"/web/sdttWeb/jichuInfoManager/Yspz.jsp?rgsh=" + rgsh +
					"&caiji=" + rgsh2 + "&curpage=" + curpage;
				document.form1.submit();
			}

			function gopagebyno(pageno) {
				var rgsh2 = '<%=rgsh2%>';
				var rgsh = '<%=rgsh%>';
				//document.form1.page.value = pageno;
				document.form1.action = path +
					"/web/sdttWeb/jichuInfoManager/Yspz.jsp?rgsh=" + rgsh +
					"&caiji=" + rgsh2 + "&curpage=" + pageno;
				document.form1.submit();
			}
			function query(){
				document.form1.action=path+"/web/sdttWeb/jichuInfoManager/Yspz.jsp";
				document.form1.submit();
			}
function addYspz(){
	window.open("../jichuInfoManager/AddYsPz.jsp", "newwindow", "height=400, width=1400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
}
function update(id,shi,xian,xiaoqu){
	window.open("../jichuInfoManager/updateYsPz.jsp?id="+id+"&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu, "newwindow", "height=400, width=1400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
}
function delbz(id) {
		if (confirm("您确定要删除此条预算信息?")) {
			document.form1.action = path + "/bz_solo_servlet?action=delectyspz&id="+id;
			document.form1.submit();
			showdiv("请稍等..............");
		}
}
		</script>
	
</body>
</html>