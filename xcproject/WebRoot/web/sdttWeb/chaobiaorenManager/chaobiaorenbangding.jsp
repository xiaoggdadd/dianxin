<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.noki.chaobiaorenyuan.bean.ChaoBiaoRen"%>
<%@ page import="com.noki.chaobiaorenyuan.bean.GuanLiQuYu"%>
<%@ page import="com.noki.chaobiaorenyuan.bean.ZhanDian"%>
<%@ page import="com.noki.chaobiaorenyuan.bean.DianBiao_new"%>
<%@ page import="com.noki.chaobiaorenyuan.Dao.ChaoBiaoRenDao"%>
<%@ page import="com.noki.chaobiaorenyuan.Dao.ChaoBiaoRenDao_new"%>
<%@ page import="com.noki.chaobiaorenyuan.Dao.GuanLiQuYuDao"%>
<%@ page import="com.noki.biaogan.model.ZhandianBean"%>
<%@ page import="com.noki.chaobiaorenyuan.bean.QuYu"%>
<%@ page import="com.noki.chaobiaorenyuan.Dao.BangDingDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	//获取抄表人所在地区并赋给下拉列表（被点击的抄表员）
	String uuid = request.getParameter("uuid");//这是抄表人管理页面传来的ID（这是个重要的参数）
	System.out.println("uuid"+uuid);
	String uuname = request.getParameter("uuname");//这是抄表人管理页面传来的ID（这是个重要的参数）
	System.out.println("uuid"+uuname);
	//#===#
	//当前登录帐号
	String loginName = (String) session.getAttribute("loginName");
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String roleId = (String) session.getAttribute("accountRole");
	String jztype1 = request.getParameter("jztype") != null ? request.getParameter("jztype") : "0";
	String jzproperty1 = request.getParameter("jzproperty") != null ? request.getParameter("jzproperty"): "0";
	String bgsign = request.getParameter("bgsign") != null ? request.getParameter("bgsign") : "-1";
	String qyzt = request.getParameter("qyzt") != null ? request.getParameter("qyzt") : "-1";//站点启用状态
	String caijid = request.getParameter("caijidian") != null ? request.getParameter("caijidian") : "-1";
	String sitetype = request.getParameter("stationtype") != null ? request.getParameter("stationtype"): "0";
	String color = null;
	String sheng = (String) session.getAttribute("accountSheng");
	//String sheng = "137";
	String agcode1="";
   	if(request.getParameter("shi")==null){
   		ArrayList shilist = new ArrayList();
   		CommonBean commBean = new CommonBean();
   		shilist = commBean.getAgcode(sheng,uuid);
		if(shilist!=null){
       		int scount = ((Integer)shilist.get(0)).intValue();
            agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
        }
   	}  
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
    String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
	String zhandian = request.getParameter("zhandian") != null ?request.getParameter("zhandian") : "0";
	String sname = request.getParameter("sname") != null ? request.getParameter("sname") : "";
	String szdcode = request.getParameter("szdcode") != null ? request.getParameter("szdcode") : "";
	String keyword = request.getParameter("txtKeyword") != null ? request.getParameter("txtKeyword"): "";
	String rgsh = request.getParameter("rgsh");//站点审核通过   首页传的值
	String rgsh2 = request.getParameter("caiji");// 采集站点
	String s_curpage = request.getParameter("curpage") != null ? request.getParameter("curpage"): "1";//分页
	int count = 0, pagesize = 10, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String permissionRights = "";
	ArrayList<DianBiao_new> al = new ArrayList<DianBiao_new>(); 		//创建获取Dao层返回的集合
	GuanLiQuYuDao dianbiaoManage = new GuanLiQuYuDao(); 				//获取Dao层 
	if(s_curpage != null && !s_curpage.equals("") && !s_curpage.equals("0")){  //不为空则重新赋值
		curpage = Integer.valueOf(s_curpage).intValue();//强转int
		}
	allpage = dianbiaoManage.COUNT(shi, xian, xiaoqu, zhandian, keyword, roleId, uuid);
	al = dianbiaoManage.SelectZD(curpage,shi, xian, xiaoqu,zhandian,keyword,roleId,uuid);//获取Dao层的返回集合	
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>绑定电表管理</title>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	<script type="text/javascript">
			//复选框onclick事件：点击全选，再次点击全不选
			function check() {
				//添加外部判断：奇数点击时value为1，全不选，否则全选，如此可以在反选与全选间衍生出极端选择的方案
				var checkbox = document.getElementById('checkbox');
				//value初始化为1，此处的三目执行后value一定不为1，而页面初始化时checkbox都为未选中状态，所以value为1时全不选
				checkbox.value == 1 ? checkbox.value = 2 : checkbox.value = 1;
				var checkboxs = document.getElementsByName('box');
				for(var i = 0; i < checkboxs.length; i++) {
					if(checkbox.value == 1) {
						checkboxs[i].checked = false; //全不选
					} else {
						checkboxs[i].checked = true; //全选
					}
					//checkboxs[i].checked?checkboxs[i].checked=false:checkboxs[i].checked=true;//反选
				}
			}
			
			//复选框onclick事件：点击全选，再次点击全不选
			function checkNO() {
				//添加外部判断：奇数点击时value为1，全不选，否则全选，如此可以在反选与全选间衍生出极端选择的方案
				var checkbox = document.getElementById('checkbox');
				//value初始化为1，此处的三目执行后value一定不为1，而页面初始化时checkbox都为未选中状态，所以value为1时全不选
				checkbox.value == 1 ? checkbox.value = 2 : checkbox.value = 1;
				var checkboxs = document.getElementsByName('boxNO');
				for(var i = 0; i < checkboxs.length; i++) {
					if(checkbox.value == 1) {
						checkboxs[i].checked = false; //全不选
					} else {
						checkboxs[i].checked = true; //全选
					}
					//checkboxs[i].checked?checkboxs[i].checked=false:checkboxs[i].checked=true;//反选
				}
			}
			//不可选定
			$(function() {
				$(".repeatall").click(function() {
					if($(".repeatall:checked").length > 0) {
						$(".repeat_child").attr("disabled", true);
					} else {
						$(".repeat_child").attr("disabled", false);
					}
				});
				$(".repeat_child").click(function() {
					if($(".repeat_child:checked").length > 0) {
						$(".repeatall").attr("disabled", true);
					} else {
						$(".repeatall").attr("disabled", false);
					}
				});
			})
	</script>
	${alert}
	<body>
		<form action="" name="form1" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="top">
					<td width="12">
						<img src="../images/space.gif" width="12" height="17" />
					</td>
					<td>
						<div class="content01">
							<div class="tit01">
								绑定电表管理
							</div>
							<div class="content01_1">
					<table width="800px" border="0" cellpadding="1" cellspacing="0"class="tb_select">
						<tr>
							<td align="right" width="60px">城市：</td>
							<td align="left" width="60px">
							<select name="shi" id="shi" style="width:130px;" onchange="changeCity()" disabled="disabled">
				         		<option value="0">请选择</option>
				         		<%
				         			ArrayList shilist = new ArrayList();
				         			shilist = commBean.getAgcode(sheng, shi, uuname);
				         			if (shilist != null) {
				         				String agcode = "", agname = "";
				         				int scount = ((Integer) shilist.get(0)).intValue();
				         				for (int i = scount; i < shilist.size() - 1; i += scount) {
				         			 	agcode = (String) shilist
				         							.get(i + shilist.indexOf("AGCODE"));
				         					agname = (String) shilist
				         							.get(i + shilist.indexOf("AGNAME"));
				         		%>
				                    <option value="<%=agcode%>"><%=agname%></option>
				                    <%
				                    	}
				                    }
				                    %>
				         		</select>
				         		</td>
				         		<td align="right" width="60px">区县：</td>
								<td align="left" width="60px">
								<select name="xian" id="xian" style="width:130px;" onchange="changeCountry()" class="selected_font">
				         		<option value="0">请选择</option>
				         		<%
				         		ArrayList xianlist = new ArrayList();
				         		xianlist = commBean.getAgcode(shi, xian, uuname);
				         			if (xianlist != null) {
				         				String agcode = "", agname = "";
				         				int scount = ((Integer) xianlist.get(0)).intValue();
				         				for (int i = scount; i < xianlist.size() - 1; i += scount) {
				         					agcode = (String) xianlist
				         							.get(i + xianlist.indexOf("AGCODE"));
				         					agname = (String) xianlist
				         							.get(i + xianlist.indexOf("AGNAME"));
				         		%>
				                    <option value="<%=agcode%>"><%=agname%></option>
				                    <%
				                    	}
				                    	}
				                    %>
				         		</select>
				         	</td>
				         	<td align="right" width="60px">乡镇：</td>
							<td align="left" width="60px">
							<select name="xiaoqu" id="xiaoqu" style="width:130px;" class="selected_font">       
			         		<option value="0">请选择</option>
			         		<%
			         			ArrayList xiaoqulist = new ArrayList();   //下拉列表
			         			xiaoqulist = commBean.getAgcode(xian, xian, uuname);
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
							<td align="right" width="60px">
											站点名称：
							</td>
										<td align="left" width="130px">
											<input type="text" name="zhandian" id="zhandian" value=""
												style="width: 130px" />
										</td>

										<td align="right" width="60px">
											电表名称：
										</td>
										<td align="left" width="130px">
											<input type="text" name="txtKeyword" id="txtKeyword" value="" style="width: 130px" />
										</td>
									</tr>
									<tr>
										<td align="right" colspan="8">
											<input type="submit" class="btn_c1" onclick="query('<%=uuid%>','<%=uuname%>>')" value="查询" />
										</td>
									</tr>
								</table>

								<div class="tbtitle01">
									<b>可以绑定的电表一览</b>
								</div>
								<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb_list1">
									<tr align="right">
										<td colspan="6" align="right">
										勾选一键绑定<input type="checkbox" id="checkbox" value="1" onclick="check()"  class="repeat_child"/>
										<input type="submit" class="btn_c1" onclick="duoxuan()"
												value="绑定" />
										勾选一键解绑<input type="checkbox" id="checkbox" value="1" onclick="checkNO()" class="repeatall" />
										<input type="submit" class="btn_c1" onclick="duoxuanNO()"
												value="解绑" />
										</td>
									</tr>
									<tr align="center">
										<th width="200">
											站点名称
										</th>
										<th width="200">
											电表名称
										</th>
										<th width="120">
											绑定人
										</th>
										<th width="120">
											绑定日期
										</th>
										<th width="120">
											状态
										</th>
										<th>
											操作
										</th>
									</tr>
									<%
										String id = "", userid = "", name = "", jzname = "", accountname = "";
										int intnum = pagesize * (curpage - 1) + 1;		
										String zt = null;
										if (al != null) {
											for (int i = 0; i < al.size(); i++) {
												int zdid = al.get(i).getZdid();
												String zdname = al.get(i).getZdname();
												int dbid = al.get(i).getDbid();
												String dbaname = al.get(i).getDbname();
												String useruuid = al.get(i).getUserid();
												String username = al.get(i).getAccountname();
												String bdtime = al.get(i).getBdtime();
											if(dbaname != null && !dbaname.equals("") && !dbaname.equals("0")){ //存在的电表
												if (useruuid != null && !useruuid.equals("") && !useruuid.equals("0")) {
													zt = "已绑定".trim();
												} else {
													zt = "未绑定".trim();
													username = "暂无绑定人";
													bdtime = "暂无绑定";
												}
									%>
									<!-- 数据加载  Start-->
									<tr align="center">
										<td width="80"><%=zdname%></td>
										<td width="80"><%=dbaname%></td>
										<td width="80"><%=username%></td>
										<td width="80"><%=bdtime%></td>
										<td width="80"><%=zt%></td>
										<%
											if ("未绑定".equals(zt)) {
										%>
										<td width="80">
											<a href="#" onclick="update('<%=dbid%>','<%=uuid%>')"><img
													src="../images/accept.png" width="16" height="16" title="绑定站点" />
											</a>
											<!--<input type="checkbox" name="vehicle" value="Car"/>  -->
										<input type="checkbox" value="<%=dbid%>" name="box"  class="repeat_child"/>
										</td>
										<%
											} else {
										%>
										<td width="80">
											<a href="#" onclick="upalert('<%=dbid%>','<%=uuid%>')"><img
													src="../images/delete.png" width="16" height="16" title="解除绑定" />
											</a>
											<!-- <input type="checkbox" name="vehicle" value="Car"/>  -->
											<input type="checkbox" value="<%=dbid%>" name="boxNO"  class="repeatall"/>
										</td>
										<%
											}
										%>
									</tr>
									<%
											}
										}
									
									%>

									<!-- 数据加载 End -->
									<tr bgcolor="#ffffff">
										<td colspan="10" align="left">
											<div align="left">

												&nbsp;&nbsp;
												<font color='#1E90FF'> <%
 								if (curpage != 1) {
 										out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
 								} else {
 										out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
 								}
 											%> </font> &nbsp;&nbsp;
												<font color='#1E90FF'> <%
 								if (curpage != 1) {
 										out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
 								} else {
 										out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
 								}
 								%> </font> &nbsp;&nbsp; 转到
									<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)"style="width: 40px; font-family: 宋体; font-size: 12px; line-height: 120%;">
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
									   <font color='#1E90FF'> <%
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
						</div>
					</td>
				</tr>
				<%
					}
				%>
			</table>
			<input type="hidden" name="uuid" value="<%=uuid%>"/>
			<input type="hidden" name="uuname"value="<%=uuname%>"/>
		</form>

<script>
var path = '<%=path%>';
function gopage() {
	document.form1.submit();
}
function previouspage() {
	var rgsh2 = '<%=rgsh2%>';
	var rgsh = '<%=rgsh%>';
	document.form1.page.value = parseInt(document.form1.page.value) - 1;
	var curpage = '<%=(curpage - 1)%>';
	document.form1.action = path
			+ "/web/sdttWeb/chaobiaorenManager/chaobiaorenbangding.jsp?rgsh=" + rgsh
			+ "&caiji=" + rgsh2 + "&curpage=" + curpage;
	document.form1.submit();
}
function nextpage() {
	var rgsh2 = '<%=rgsh2%>';
	var rgsh = '<%=rgsh%>';
	document.form1.page.value = parseInt(document.form1.page.value) + 1;
	var curpage = '<%=(curpage + 1)%>';
	document.form1.action = path
			+ "/web/sdttWeb/chaobiaorenManager/chaobiaorenbangding.jsp?rgsh=" + rgsh
			+ "&caiji=" + rgsh2 + "&curpage=" + curpage;
	document.form1.submit();
}
function gopagebyno(pageno) {
	var rgsh2 = '<%=rgsh2%>';
	var rgsh = '<%=rgsh%>';
	document.form1.page.value = pageno;
	document.form1.action = path
			+ "/web/sdttWeb/chaobiaorenManager/chaobiaorenbangding.jsp?rgsh=" + rgsh
			+ "&caiji=" + rgsh2 + "&curpage=" + pageno;
	document.form1.submit();
}
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
function daochu(){
	document.form1.action=path+"/servlet/CbDownload?bz=cbyuan";
	document.form1.submit();
}
function query(){
	document.form1.action=path+"/web/sdttWeb/chaobiaorenManager/chaobiaorenbangding.jsp";
	document.form1.submit();
}

function update(dbid,uuid){
	     if(confirm("您确定绑定该站点？")){
     		document.form1.action=path+"/servlet/AppUserServlet?dbid="+dbid+"&uuid="+uuid+"&caozuo=bangding";
      		document.form1.submit();
   		 }
}
function upalert(dbid,uuid){
	     if(confirm("您确定解除该绑定？")){
     		document.form1.action=path+"/servlet/AppUserServlet?dbid="+dbid+"&uuid="+uuid+"&caozuo=jiebang";
      		document.form1.submit();
   		 }
}
function duoxuan(){
	    var r=document.getElementsByName("box"); 
	    var dbid="";
	    for(var i=0;i<r.length;i++){
	         if(r[i].checked){
	         //alert(r[i].value);
	         if(dbid==""){
	       	     dbid= r[i].value;
	         }else{
	       	  dbid = dbid+"," + r[i].value;
	         }
	       }
	    }      
	  //  alert(dbid);
	  var uuid = '<%=uuid%>';
	  if(dbid==""){
		   alert("请选择需要绑定的电表");
		   return;
	  }
	if(confirm("您确定绑定这些电表？")){
		     document.form1.action=path+"/servlet/AppUserServlet?dbid="+dbid+"&uuid="+uuid+"&caozuo=bangdingALL";
		     document.form1.submit();
	 }
}
function duoxuanNO(){
    var r=document.getElementsByName("boxNO"); 
    var dbid="";
    for(var i=0;i<r.length;i++){
         if(r[i].checked){
         //alert(r[i].value);
         if(dbid==""){
       	     dbid= r[i].value;
         }else{
       	  dbid = dbid+"," + r[i].value;
         }
       }
    }      
  //  alert(dbid);
  var uuid = '<%=uuid%>';
  if(dbid==""){
	   alert("请选择需要解绑的电表");
	   return;
  }
if(confirm("您确定解绑这些电表？")){
	     document.form1.action=path+"/servlet/AppUserServlet?dbid="+dbid+"&uuid="+uuid+"&caozuo=bangdingALLNO";
	     document.form1.submit();
 }
}
document.form1.uuid.value='<%=uuid%>';
document.form1.uuname.value='<%=uuname%>';
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
document.form1.txtKeyword.value='<%=keyword%>';
</script>
	</body>
</html>



