<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.noki.chaobiaorenyuan.Dao.ChaoBiaoRenDao"%>
<%@ page import="com.noki.chaobiaorenyuan.bean.ChaoBiaoRen"%>
<%@ page import="com.noki.chaobiaorenyuan.Dao.ChaoBiaoRenDao_new" %>
<%@ page import="com.noki.chaobiaorenyuan.bean.ChaoBiaoRen_new"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>抄表人员管理</title>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
		<script>
<%    		String path = request.getContextPath();
			String loginName = (String) session.getAttribute("loginName");
			Account account = (Account) session.getAttribute("account");
			String loginId = account.getAccountId();
			String roleId = (String) session.getAttribute("accountRole");
			System.out.println("logindi:"+loginId);
			
			String jztype1 = request.getParameter("jztype") != null ? request
					.getParameter("jztype") : "0";
			String jzproperty1 = request.getParameter("jzproperty") != null ? request
					.getParameter("jzproperty")
					: "0";
			String bgsign = request.getParameter("bgsign") != null ? request
					.getParameter("bgsign") : "-1";
			String qyzt = request.getParameter("qyzt") != null ? request
					.getParameter("qyzt") : "-1";//站点启用状态
			String caijid = request.getParameter("caijidian") != null ? request
					.getParameter("caijidian") : "-1";
			String sitetype = request.getParameter("stationtype") != null ? request
					.getParameter("stationtype")
					: "0";
			String color = null;
			String sheng = (String) session.getAttribute("accountSheng");
			
			ArrayList<ChaoBiaoRen_new> all = new ArrayList<ChaoBiaoRen_new>();
			   ChaoBiaoRen_new cbrr = new ChaoBiaoRen_new();
				ChaoBiaoRenDao_new cbrDaoo = new ChaoBiaoRenDao_new();
				all=cbrDaoo.SHX(loginId);
				String shi="",xian="",xiaoqu="";
				if (all != null){
					int numm = 1;
					for (int ii = 0; ii< all.size(); ii++) {
				
						
					shi = all.get(ii).getShi();
					xian=all.get(ii).getXian();
					xiaoqu=all.get(ii).getXiaoqu();
						
				}

				}
			
			String agcode1 = "0";
			if (request.getParameter("shi") == null&&request.getParameter("shi")==""&&request.getParameter("shi")=="0") {
				ArrayList shilist = new ArrayList();
				CommonBean commBean = new CommonBean();
				shilist = commBean.getAgcode(sheng, account.getAccountId());
				if (shilist != null) {
					int scount = ((Integer) shilist.get(0)).intValue();
					agcode1 = (String) shilist.get(scount
							+ shilist.indexOf("AGCODE"));
				}
			}
		//	String shi = request.getParameter("shi") != null ? request
			//		.getParameter("shi") : agcode1;
		//	String xian = request.getParameter("xian") != null ? request
			//		.getParameter("xian") : "0";
		//	String xiaoqu = request.getParameter("xiaoqu") != null ? request
			//		.getParameter("xiaoqu") : "0";
			String sname = request.getParameter("sname") != null ? request
					.getParameter("sname") : "";
			String szdcode = request.getParameter("szdcode") != null ? request
					.getParameter("szdcode") : "";
			String keyword = request.getParameter("txtKeyword") != null ? request
					.getParameter("txtKeyword")
					: "";

			String rgsh = request.getParameter("rgsh");		//站点审核通过   首页传的值
			String rgsh2 = request.getParameter("caiji");	// 采集站点
			
			String s_curpage = request.getParameter("curpage") != null ? request.getParameter("curpage"): "1";//分页
			int count = 0,curpage = 1,pagesize = 10,nextpage = 1, prepage = 1, allpage = 1, xh = 1;
			String permissionRights = "";
			
			//遍历抄表人
		    ChaoBiaoRen_new cbr = new ChaoBiaoRen_new();
			ChaoBiaoRenDao_new cbrDao = new ChaoBiaoRenDao_new();
			ArrayList<ChaoBiaoRen_new> al = new ArrayList<ChaoBiaoRen_new>();
			 
			String name = request.getParameter("name")!=null?request.getParameter("name"):""; //获取页面模糊查询字符
			String yeshu = request.getParameter("curpage"); //获取分页查询页数 
			
			if(yeshu != null && !yeshu.equals("") && !yeshu.equals("0")){  //不为空则重新赋值
				
				curpage = Integer.valueOf(yeshu).intValue();//强转int
				
				}
				al = cbrDao.FenYe(curpage,"",sheng,shi,xian,xiaoqu,roleId);//当模糊查询字符为空时	
			//allpage = cbrDao.COUNT("");	//共多少页
			if (name != null && !name.equals("") && !name.equals("0")) {
				
				al = cbrDao.FenYe(curpage,name,sheng,shi,xian,xiaoqu,roleId);
				allpage = cbrDao.COUNTNAME(name,sheng,shi,xian,xiaoqu,roleId);	//共多少页
				
			}else{
				
				allpage = cbrDao.COUNT(sheng,shi,xian,xiaoqu,roleId);	//共多少页
				
			}
			System.out.println("allpage:"+allpage); //判断多少页
			%>
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
				<%
					String whereStr = "";
					String str = "";
					String xuni = "0";
					if (xuni.equals("3")) {
						whereStr = whereStr + " and shsign!='1'";
					}
					if (!xiaoqu.equals("0")) {
						whereStr = whereStr + " and z.xiaoqu='" + xiaoqu + "'";
						str = str + " and z.xiaoqu='" + xiaoqu + "'";
					} else if (!xian.equals("0")) {
						whereStr = whereStr + " and z.xian='" + xian + "'";
						str = str + " and z.xian='" + xian + "'";
					} else if (!shi.equals("0")) {
						whereStr = whereStr + " and z.shi='" + shi + "'";
						str = str + " and z.shi='" + shi + "'";
					} else if (!sheng.equals("0")) {
						whereStr = whereStr + " and z.sheng='" + sheng + "'";
						str = str + " and z.sheng='" + sheng + "'";
					}
					if (keyword.length() > 0 && keyword != null) {
						whereStr = whereStr + " and z.jzname like '%" + keyword + "%'";
						str = str + " and z.jzname like '%" + keyword + "%'";
					}
					if (szdcode.length() > 0 && szdcode != null) {
						whereStr = whereStr + " and z.id='" + szdcode + "'";
						str = str + " and z.id='" + szdcode + "'";
					}

					if (!jztype1.equals("0")) {
						whereStr = whereStr + " and z.jztype='" + jztype1 + "'";
						str = str + " and z.jztype='" + jztype1 + "'";
					}
					if (!jzproperty1.equals("0")) {
						whereStr = whereStr + " and z.property='" + jzproperty1 + "'";
						str = str + " and z.property='" + jzproperty1 + "'";
					}
					if (!bgsign.equals("-1")) {
						whereStr = whereStr + " and z.bgsign='" + bgsign + "'";
						str = str + " and z.bgsign='" + bgsign + "'";
					}
					if (!qyzt.equals("-1")) {//站点启用状态
						whereStr = whereStr + " and z.qyzt='" + qyzt + "'";
						str = str + " and z.qyzt='" + qyzt + "'";
					}

					if (rgsh != null && !rgsh.equals("null")) {//首页传的值 审核通过的
						whereStr = whereStr + " and z.SHSIGN='" + rgsh + "'";
						str = str + " and z.SHSIGN='" + rgsh + "'";
					}
					if (rgsh2 != null && !rgsh2.equals("null")) {//首页传的值 采集站点
						whereStr = whereStr + " and z.caiji='" + rgsh2 + "'";
						str = str + " and z.caiji='" + rgsh2 + "'";
					}

					if (caijid != null && !caijid.equals("") && !caijid.equals("-1")) {
						whereStr = whereStr + " and CAIJI='" + caijid + "'";
						str = str + " and CAIJI='" + caijid + "'";
					}
					if (sitetype != null && !sitetype.equals("")
							&& !sitetype.equals("0")) {
						whereStr = whereStr + " and STATIONTYPE='" + sitetype + "'";
						str = str + " and STATIONTYPE='" + sitetype + "'";
					}
				%>
				<tr valign="top">
					<td width="12">
						<img src="../images/space.gif" width="12" height="17" />
					</td>
					<td>
						<div class="content01">
							<div class="tit01">
								抄表人员查询
							</div>
							<div class="content01_1">
								<table width="800px" border="0" cellpadding="1" cellspacing="0"
									class="tb_select">
									<tr>
										<td align="left" width="60px">
											请输入抄表人员名称进行查询：
										</td>
										<td align="left" width="60px">
											<input type="text" name="name" value="" id="name" maxlength="80"
												style="box-sizing: border-box; width: 130px;" >
										</td>
									</tr>
									<tr>
										<td align="right" colspan="8">
											<input type="submit" class="btn_c1" onclick="query()" value="查询" />
										</td>
									</tr>
								</table>

								<div class="tbtitle01">
									<b>抄表员信息查询统计一览</b>
								</div>
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="tb_list1">
									<!--  <tr align="right">
										<td colspan="5" align="right">
											<input name="button2" type="submit" onclick="daochu()" class="btn_c1" id="button2" value="导出Excel" />
										</td>
									</tr> -->
									<tr align="center">
										<th width="40">
											序号
										</th>
										<th width="200">
											登录名
										</th>
										<th width="200">
											姓名
										</th>
										<th width="80">
											角色名称
										</th>
										<th>
											操作
										</th>
									</tr>
									<%
										int intnum = pagesize * (curpage - 1) + 1;
										if (al != null) {
											int num = 1;
											for (int i = 0; i < al.size(); i++) {
												int accountid = al.get(i).getAccountid();
												String accountname = al.get(i).getAccountname();
												String xmname = al.get(i).getName();
												String password = al.get(i).getPassword();
												String roleid = al.get(i).getRoleid();
												String rolename = al.get(i).getRolename();
												int delsign = al.get(i).getDelsign();

									%>
									<!-- 数据加载  Start-->
									<tr align="center">
										<td width="40"><%=intnum++%></td>
										<td width="200"><%=accountname%></td>
										<td width="200"><%=xmname%></td>
										<td width="200"><%=rolename%></td>
										<td width="80">
											<a href="#" onclick="bangding('<%=accountid%>','<%=accountname%>')"><img
													src="../images/icon03.gif" width="16" height="16"
													title="绑定电表管理" /> </a>
										</td>
									</tr>
									<%
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
				%><!-- else {%>-->
				<!--  <tr align="center" >
			<td align="left" colspan="9">
			当前无数据。
			</td>
		</tr>-->

			</table>
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
			+ "/web/sdttWeb/chaobiaorenManager/chaobiaorenyuanguanli_new.jsp?rgsh=" + rgsh
			+ "&caiji=" + rgsh2 + "&curpage=" + curpage;
	document.form1.submit();
}
function nextpage() {
	var rgsh2 = '<%=rgsh2%>';
	var rgsh = '<%=rgsh%>';
	document.form1.page.value = parseInt(document.form1.page.value) + 1;
	var curpage = '<%=(curpage + 1)%>';
	document.form1.action = path
			+ "/web/sdttWeb/chaobiaorenManager/chaobiaorenyuanguanli_new.jsp?rgsh=" + rgsh
			+ "&caiji=" + rgsh2 + "&curpage=" + curpage;
	document.form1.submit();
}
function gopagebyno(pageno) {
	var rgsh2 = '<%=rgsh2%>';
	var rgsh = '<%=rgsh%>';
	document.form1.page.value = pageno;
	document.form1.action = path
			+ "/web/sdttWeb/chaobiaorenManager/chaobiaorenyuanguanli_new.jsp?rgsh=" + rgsh
			+ "&caiji=" + rgsh2 + "&curpage=" + pageno;
	document.form1.submit();
}
function delzd(id) {
	if (confirm("您确定删除此条信息？")) {
		document.form1.action = path
				+ "/web/sdttWeb/chaobiaorenManager/chaobiaorenyuanguanli.jsp?id="
				+ id;
		document.form1.submit();
	}
}
function xiangxi(id) {
	window
			.open(
					"../jizan/xxquery.jsp?id=" + id,
					"newwindow",
					"height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
}
function dfinfo(id) {
<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/dfinfo.jsp?id="+id;
      		document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>    		
    }
    function zlinfo(id){
    	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/zlinfo.jsp?id="+id;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }

    function modifyjz(id,intnum){
    //alert(intnum);
  	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/modifysite.jsp?id="+id+"&nums="+intnum;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
       
    }
    function getValue(va,sql){
       var general =document.getElementById("general");
       var htmlsql =document.getElementById("htmlsql");
       general.value=va;
       htmlsql.value = sql;    
    }
    //页面载入方法
    function op(){
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian,ammeters,ammeterdegrees,electricfees&tab=zd,am,ad,ef&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
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
function addjz(){
	window.open("../chaobiaorenManager/addcaobiaoren.jsp", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}
function daochu(){
	document.form1.action=path+"/servlet/CbDownload?bz=cbyuan";
	document.form1.submit();
}
function update(id){
	window.open("../chaobiaorenManager/addcaobiaoren.jsp?id="+id+"+&bz=1", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}
function query(){
	document.form1.action=path+"/web/sdttWeb/chaobiaorenManager/chaobiaorenyuanguanli_new.jsp";
}
//绑定方法
function bangding(id,name){
	window.open("../chaobiaorenManager/chaobiaorenbangding.jsp?uuid="+id+"&uuname="+name+"", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}

document.form1.name.value='<%=name%>';

</script>
	</body>
</html>



