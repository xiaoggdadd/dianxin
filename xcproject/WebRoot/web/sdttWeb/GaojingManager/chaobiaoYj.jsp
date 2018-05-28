<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.query.basequery.javabean.ElectricFeesQueryBean"%>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page
	import="java.text.*,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.util.regex.Pattern"%>
<%@ page import="com.noki.bgyj.BGYJDao"%>
<%@ page import="com.noki.bgyj.BGYJBean"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
		<%
		int c=0;
		String aaa="";
			String path = request.getContextPath();//获取路径
			Account account = (Account) session.getAttribute("account");//获取登录者信息
			String roleId = account.getRoleId();//登录者角色id
		  	String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
		    int count=0,pagesize=10,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
		    curpage=Integer.parseInt(s_curpage);
			String loginName = (String) session.getAttribute("loginName");
			String sheng = (String) session.getAttribute("accountSheng");
			// String roleId = (String)session.getAttribute("accountRole");

			//获取本页查询的信息 
			String usename = request.getParameter("usename") != null ? request
					.getParameter("usename") : "";
			String adminsname = request.getParameter("adminsname") != null ? request
					.getParameter("adminsname")
					: "";
			// String sheng = (String)session.getAttribute("accountSheng");
			String shi = request.getParameter("shi") != null ? request
					.getParameter("shi") : "0";
			String xian = request.getParameter("xian") != null ? request
					.getParameter("xian") : "0";
			String xiaoqu = request.getParameter("xiaoqu") != null ? request
					.getParameter("xiaoqu") : "0";
			String permissionRights = "";//登录者 的 导出按钮 权限

			String txtKeyword = request.getParameter("txtKeyword") != null ? request
					.getParameter("txtKeyword")
					: "";
		%>


		<jsp:useBean id="bean" scope="page"
			class="com.noki.mobi.sys.javabean.AccountBean"></jsp:useBean>
		<jsp:useBean id="ztcommon" scope="page"
			class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
		<jsp:useBean id="commBean" scope="page"
			class="com.noki.mobi.common.CommonBean"></jsp:useBean>
		<jsp:useBean id="dianbiaoBean" scope="page" class="com.noki.jizhan.DianBiaoBean">   
		</jsp:useBean>
		<%
			String ssagcode = ztcommon.getLastAgcode(loginName);//ssagcode返回登录用户 小区或 县或 市或省的的编码
		%>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->

	
		</script>
	</head>
	<script language="javascript">

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


function sh(id) {
	window
			.open(
					"../hetongshenpi/hetongshenpikuozhan.jsp?id=" + id,
					"newwindow",
					"height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
}
</script>
	<body onLoad="goPage(1,10);">
	<%-- <body>--%>
		<form action="" name="form1" method="post">
			<div class="content01_1">


				<div class="tbtitle01">
					<b>抄表预警</b>
				</div>
				<table width="100%" border="1" cellpadding="0" cellspacing="0" id="idData"
					class="tb_list1">
					<thead>
						<tr align="right">
						<td colspan="4" align="right" valign="middle">
							<%--<input name="button2" type="submit" onclick="daochu()"
								class="btn_c1" id="button2" value="Excel导出" />
							&nbsp;
						--%></td>
					</tr>
					<tr align="center">
						<th width="10">
							序号
						</th>
						<th>
							电表编码
						</th>
						<th>
							电表名称
						</th>
						<th>
							上次抄表时间
						</th>
						
						
					</tr>
					</thead>
					<tbody></tbody>
				</table>
				<div class="space_h_10"></div>
			</div>
			</form>
		<script type="text/javascript">
function exportad() {
	var usename = "<%=usename%>";
	var adminsname = "<%=adminsname%>";
	var ssagcode = "<%=ssagcode%>";
	document.form1.action = path + "/web/sys/操作员信息.jsp?usename=" + usename
			+ "&whereStr=" + whereStr + "&adminsname=" + adminsname
			+ "&ssagcode=" + ssagcode;
	document.form1.submit();
}
 function previouspage()
     {
    
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage - 1)%>';
      		document.form1.action=path+"/web/sdttWeb/GaojingManager/jzbgYj.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
function nextpage(){
     document.form1.page.value = parseInt(document.form1.page.value) + 1;
     var curpage='<%=(curpage + 1)%>';
     document.form1.action=path+"/web/sdttWeb/GaojingManager/jzbgYj.jsp?curpage="+curpage;
     document.form1.submit();
}

 function gopagebyno(pageno){
    document.form1.page.value = pageno;   
    document.form1.action=path+"/web/sdttWeb/BiaoganManager/biaoganHistory.jsp?curpage="+pageno;
    document.form1.submit();
}

function delet(yjid) {
	if (confirm("确定要删除么！")) {
		document.form1.action = path
				+ "/servlet/deleteSERVLET?action=del&yjid=" + yjid;
		document.form1.submit();
	}

}
var XMLHttpReq;
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
			;
		}
		;
	}
	;
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
	} else {
		XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
	}
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
		;
	}
	;
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
		;
	}
	;
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
		;
	}
	;
}
function processResponse_xian() {

	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
			//var res = XMLHttpReq.responseText;
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			updateZd(res);

		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
		;
	}
	;
}

function changeSheng() {

	var sid = document.form1.sheng.value;
	document.form1.sheng2.value = document.form1.sheng.value;
	if (sid == "0") {
		var shilist = document.all.shi;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=sheng&pid=" + sid, "sheng");

	}
	;
}
function updateShi(res) {
	var shilist = document.all.shi;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
	;
}
function changeCity() {
	var sid = document.form1.shi.value;
	document.form1.shi2.value = document.form1.shi.value;
	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=shi&pid=" + sid, "shi");
	}
	;
}
function updateQx(res) {
	var shilist = document.all.xian;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
	;
}

function changeCountry() {
	var sid = document.form1.xian.value;
	document.form1.xian2.value = document.form1.xian.value;
	if (sid == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=xian&pid=" + sid, "xian");
	}
	;
}
function updateZd(res) {
	var shilist = document.all.xiaoqu;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));
	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
	;
}
function daochu() {
	document.form1.action = path + "/servlet/CbDownload?bz=Chaobiaoyj";
	document.form1.submit();
}



/**
 * 
 * pno--页数
 * psize--每页显示记录数
 *
 * 
 **/
function goPage(pno,psize){
	$("#idData tbody").empty();
	$.ajax({
	                type: "POST",//请求方式
	                url: path+"/servlet/dianbiao?action=getYJData",
	                data: {},
	                dataType: "json",success: function(result){
	                	//alert(result)
	                	var  strlist = eval(result);
						var length=strlist.length;
						var count=0;
						var totalPage = 0;//总页数
   					 	var pageSize = psize;//每页显示行数
   
     
    					var currentPage = pno;//当前页数
    
   	 					var startRow = (currentPage - 1) * pageSize*3;//开始显示的行  31 
    					var endRow = currentPage * pageSize*3;//结束显示的行   40
    					var html="";
    					var no=(pno-1)*psize+1;
    					var dbname,dbbm,lasttime;
						if(length>0){
							count=length/3;
		 					//总共分几页 
							if(count/pageSize > parseInt(count/pageSize)){   
            					totalPage=parseInt(count/pageSize)+1;   
       						}else{   
           						totalPage=parseInt(count/pageSize);   
       						} 
							for(var i=startRow;i<endRow-1;i+=3){
								dbname = strlist[i];
	        					dbbm = strlist[i+1];
		    					lasttime = strlist[i+2];
		    					
								if(lasttime==undefined){
									break;
								}
								html+="<tr align='center'><td>"+no
								  +"</td><td>"+dbbm+"</td><td>"+dbname+"</td><td>"+lasttime
            					  +"</td></tr>";
								no++;
							}
							
						}else{
							html+="";
						}
						$("#idData tbody").append(html);
						
						var htm="<tr bgcolor='#ffffff'><td colspan='9' align='lef' id='barcon'>";
						htm+="<div align='left'>&nbsp;&nbsp;<font color='#1E90FF'>";
 	                    if (currentPage != 1) {
 							htm+="<a onClick=\'goPage(1,10)\'><img src='../images/page-first.gif'></a>";
 		                } else {
 							htm+="<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>";
 					    }
                        htm+="</font> &nbsp;&nbsp;<font color='#1E90FF'>"
 	                    if (currentPage != 1) {
 							htm+="<a onClick=\'goPage("+(currentPage-1)+",10)\'><img src='../images/page-prev.gif'></a>";
 						} else {
 							htm+="<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>";
 						}
 						htm+="</font> 转到<select name='page'onchange='goPage(document.form1.page.value,10)'style='width: 40px; font-family: 宋体; font-size: 12px; line-height: 120%;'>"
						for (var i = 1; i <= totalPage; i++) {
							if (currentPage == i) {
								htm+="<option value='" + i+ "' selected='selected'>" + i + "</option>";
							} else {
								htm+="<option value='" + i + "'>" + i+ "</option>";
							}
						}
						htm+="</select>&nbsp;&nbsp;共<font color='#1E90FF'><b>"+totalPage+"</b>&nbsp;</font>页&nbsp;&nbsp;<font color='#1E90FF'> ";
 	                    if (totalPage != 0 && (currentPage < totalPage)) {
 			            	htm+="<a onClick=\'goPage("+(currentPage+1)+",10)\'><img src='../images/page-next.gif'></a>";
 						} else {
 							htm+="<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>";
 						}
	 						htm+="</font> &nbsp;&nbsp;<font color='#1E90FF'>";
 						if (totalPage != 0 && (currentPage < totalPage)) {
	 						htm+="<a onClick=\'goPage("+totalPage+",10)\'><img src='../images/page-last.gif'></a>";
 						} else {
 							htm+="<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>";
 						}
 						htm+="</font> &nbsp;&nbsp;</div></td></tr>";
	                	$("#idData tbody").append(htm);
	                }
	        })
}


</script>
	</body>
</html>
