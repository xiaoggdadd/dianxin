<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.ElectricFeesQueryBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.util.regex.Pattern"%>
<%@ page import="com.noki.database.DataBase" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新建基站审批</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />

	<%
	String path = request.getContextPath();//获取路径
	Account account = (Account) session.getAttribute("account");//获取登录者信息
	String roleId = account.getRoleId();//登录者角色id
	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
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
	
	String txtKeyword=request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"";
	String zdmc="",property="",yflx="",gdfs="",szdq="",area="",type="",spzt="";
	String id=request.getParameter("id")!=null?request.getParameter("id"):"";
	String sql ="select  z.jzname,(select t.name from indexs t where t.code = z.property  and t.type='ZDSX') as property,(select t.name from indexs t where t.code = z.yflx   and t.type='FWLX') as yflx,"+
	"case z.spzy when '00' then '区域管理员待批' when '01' then '区域主任待批' when '02' then '建维部门待批' when '03' then '运发部待批' else '审批通过' end spzy,"+
	"(select t.name from indexs t where t.code = z.gdfs and t.type='ZDSX') as gdfs,(case when z.xian is not null then(select distinct agname from d_area_grade where agcode = z.xian) else '' end) || (case"+
	" when z.xiaoqu is not null then(select distinct agname from d_area_grade where agcode = z.xiaoqu)else ''end) as szdq, z.area,(select t.name from indexs t where t.code = z.stationtype  and t.type='stationtype') as xlx"+
	" from zhandian z where id='"+id+"'";
	DataBase db = new DataBase();
	ResultSet rs = null;
	db.connectDb();
	rs=db.queryAll(sql.toString());
	while(rs.next()){
		zdmc=rs.getString(1);
		property=rs.getString(2);
		yflx=rs.getString(3);
		spzt=rs.getString(4);
		gdfs=rs.getString(5);
		szdq=rs.getString(6);
		area=rs.getString(7);
		type=rs.getString(8);
		}
	
%>

</head>
<jsp:useBean id="bean" scope="page"
			class="com.noki.mobi.sys.javabean.AccountBean"></jsp:useBean>
		<jsp:useBean id="ztcommon" scope="page"
			class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
		<jsp:useBean id="commBean" scope="page"
			class="com.noki.mobi.common.CommonBean"></jsp:useBean>
				<%
			String ssagcode = ztcommon.getLastAgcode(loginName);//ssagcode返回登录用户 小区或 县或 市或省的的编码
		%>
			<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	
			<script language="javascript">
		 
var path = '<%=path%>';
function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = ""
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"
	} else {
		trObject.style.display = "none"
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
	}
}

function saccount() {
	var sname = document.form1.sname.value
	var srole = document.form1.srole.value
	var sorg = document.form1.sorg.value
	document.form1.action = path + "/web/sys/accountList.jsp?doQuery=1&san=1"
	document.form1.submit();
}
function editacc(id, sheng, shi, xian, xiaoqu) {
	window
			.open('modifyAccount.jsp?accountId=' + id + "&sheng=" + sheng
					+ "&shi=" + shi + "&xian=" + xian + "&xiaoqu=" + xiaoqu,
					'infoAccount',
					'width=850,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
}
function delacc(accountId) {
	
		if (confirm("确定要删除此账户么！")) {
			document.form1.action = path + "/servlet/account?action=del&accountId="+accountId;
			document.form1.submit();
		} else {
			return;
		}
	
}
function chaxun() {
	document.form1.action = path + "/web/sdttWeb/ShenpiManager/DifenShujuShenpi.jsp";
	document.form1.submit();
}
function accountOn() {
	var i = 0;

	for ( var j = 0; j < document.form1.elements.length; j++) {
		if (document.form1.elements[j].checked) {
			i++;
		}
	}
	if (i > 0) {
		if (confirm("确定要启用这些账户么！")) {
			document.form1.action = path
					+ "/servlet/account?action=onOrOff&sign=1";
			document.form1.submit();
		} else {
			return;
		}
	} else {
		alert("请选择要启用的账户");
		return;
	}
}
function accountOff() {
	var i = 0;

	for ( var j = 0; j < document.form1.elements.length; j++) {
		if (document.form1.elements[j].checked) {
			i++;
		}
	}
	if (i > 0) {
		if (confirm("确定要停用这些账户么！所属关系也将被删除")) {
			document.form1.action = path
					+ "/servlet/account?action=onOrOff&sign=0";
			document.form1.submit();
		} else {
			return;
		}
	} else {
		alert("请选择要停用的账户");
		return;
	}
}
function resetPass() {
	var i = 0;

	for ( var j = 0; j < document.form1.elements.length; j++) {
		if (document.form1.elements[j].checked) {
			i++;
		}
	}
	if (i > 0) {
		if (confirm("确定要重置这些账户的密码么？")) {
			document.form1.action = path + "/servlet/account?action=resetPass";
			document.form1.submit();
		} else {
			return;
		}
	} else {
		alert("请选择要重置密码的账户");
		return;
	}
}
function fzqy() {
	var i = 0;
	var accountid = "";
	for ( var j = 0; j < document.form1.elements.length; j++) {
		if (document.form1.elements[j].checked) {
			i++;
			accountid = document.form1.elements[j].value;
		}
	}

	if (i == 1) {
	
	window.open('../sys/per_area.jsp?accountid='+ accountid,'','width=600,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
	
		//document.form1.action = path + "/web/sdttWeb/sys/per_area.jsp?accountid="+ accountid;
		//document.form1.submit();

	} else {
		alert("每次只能选择一个用户");
		return;
	}
}
function checkPage() {
	if (document.form1.pageCheck.checked) {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = true
		}
	} else {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = false
		}
	}
}
function addacc() {
window.open("../sys/addAccount.jsp", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	//document.form1.action = path + "/web/sdttWeb/sys/addAccount.jsp";
	//document.form1.submit();
}
function tg(a,b) {
var nr=document.form1.memo.value;
	if(nr!=null&&nr!=""){
		if (confirm("确定要审核！")) {
		   
			document.form1.action = path+"/servlet/zhandian?action=shenhe&shsign="+a+"&id="+b;
			document.form1.submit();
		} else {
			return;
		}
	}else{
	alert("首先填写备注 ！");
	}
}
</script>
<body>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">基站审批</div>
				<div class="content01_1">
					
					<div class="tbtitle01"><b>基站审批一览</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
									<tr align="center">
										<th>基站名称</th>
										<th>基站属性</th>
										<th>用房类型</th>
										<th>供电方式</th>
										<th>所在地区</th>
										<th>面积</th>
										<th>站点类型</th>
										<th>审批状态</th>
									</tr>
									<tr align="center">
										<td width="10"><%=zdmc%></td>
										<td align="left"><%=property%></td>
										<td align="left"><%=yflx%></td>
										<td align="right"><%=gdfs%></td>
										<td align="right"><%=szdq%></td>
										<td align="right"><%=area%></td>
										<td align="right"><%=type%></td>
										<td align="center"><%=spzt%></td>
									</tr>
									
									<tr>
									
											<td>备注：</td>
									<td width="80" align="left" colspan="9" >
											<input name="memo"  type="text"  id="memo"  />
										</td>	
										</tr>
										<tr>
												<td width="80" align="right" colspan="10">
									<div >
											&nbsp;<input name="button2" type="submit" class="btn_c1" onclick="tg('<%=spzt%>','<%=id %>')" id="button2" value="通过" />&nbsp; 
											&nbsp;<input name="button2" type="submit" class="btn_c1" onclick="tg('<%=id%>','-1')" id="button2" value="不通过" />&nbsp; 
												</div></td>
									</tr>
									
 
									<!-- 数据加载 End -->
								
								</table>
						<div class="space_h_10"></div>
				</div>
			</div>
		</td></tr>
		
		</table>
		<input type="hidden" name="shi2" id="shi2" value="" />
		<input type="hidden" name="xian2" id="xian2" value="" />
		<input type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
		</form>
		<script type="text/javascript">

var XMLHttpReq;
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
	document.form1.sheng2.value=document.form1.sheng.value;
	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeCity(){
	var sid = document.form1.shi.value;
	document.form1.shi2.value=document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
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

function changeCountry(){
	var sid = document.form1.xian.value;
	document.form1.xian2.value=document.form1.xian.value;
	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
</script>
</body>

</html>
