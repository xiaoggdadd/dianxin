<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.lang.Double" %>
<%@ page import="com.noki.jizhan.model.BasicDataBean"%>
<%@ page import="com.noki.jizhan.SiteManage"%>
<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	Account account=(Account)session.getAttribute("account");
	String jzproperty=request.getParameter("jzproperty");//获取新站点的属性  
	String accountname=account.getAccountName();
	
	//获取字典表ID
	String id =request.getParameter("id");
	//创建Dao方法
	SiteManage sm = new SiteManage();
	//创建接受集合
	ArrayList<BasicDataBean> al = new ArrayList<BasicDataBean>();
	//获取根据ID查询的内容
	al = sm.getBasicData(id);
	String ne = "";
	String ce = "";
	String de = "";
	//遍历获取DAO方法集合内的值
    ne = al.get(0).getName();
    ce = al.get(0).getCode();
  	de = al.get(0).getDescribe();
	//----------------------------
	//下面是修改方法，首先判断是否响应修改
	String xg = request.getParameter("xg");
	if(xg != null && !xg.equals("") && !xg.equals("0")){	//若xg有值则进行修改操作
		xg = ""; //还原修改判断
		//获取修改为的值
		 ne = request.getParameter("ne");
		 de = request.getParameter("de");
		 System.out.println("跳转--");
		int rs = sm.updeatBasicData(id,ne,de);
		System.out.println("跳转");
		if(rs != 0){
			System.out.println("修改成功");
			String url = path+"/web/sdttWeb/sys/basicData.jsp",msg="修改成功";
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}else{
			System.out.println("修改失败");
			String url = path+"/web/sdttWeb/sys/basicData.jsp",msg="修改失败，请于管理员联系";
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>基础数据修改</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
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
		<div class="tit01">基础数据修改</div>
				<div class="content01_1">
					<table width="800px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="80px">名称：</td>
							<td width="80px">
								<input type="hidden" name=id id="id" value="<%=id%>" style="width:130px" maxlength="45" readonly="readonly"/>&nbsp;
								<input type="text" name="ne" id="ne" value="<%=ne%>" style="width:130px;background-color:#FFEC8B " maxlength="80" readonly="readonly"/>
							</td>
							<td align="right" width="80px">值：</td>
							<td width="80px">
								<input type="text" name="ce" id="ce" value="<%=ce%>" style="width:130px" maxlength="60"/>
							</td>
							<td align="right" width="80px">备注：</td>
							<td width="80px">
								<input type="text" name="de" id="de" value="<%=de%>"  style="width:130px;background-color:#FFEC8B" maxlength="60" readonly="readonly"/>&nbsp;
							</td>
						</tr>
						
						<tr>
							<td align="right" colspan="8" height="60px">
								<%String xiugai = "123456789"; %>
								<input name="baocun" onclick="saveAccount('<%=xiugai%>')" type="button" class="btn_c1" id="baocun" value="保存" />&nbsp; 
								<input name="chz" type="reset" class="btn_c1" id="chz" value="重置" />&nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td></tr></table>
		
<script type="text/javascript">
var path='<%=path%>';
function exportad() {
	document.form1.action = path + "/web/sys/操作员信息.jsp?usename=" + usename
			+ "&whereStr=" + whereStr + "&adminsname=" + adminsname
			+ "&ssagcode=" + ssagcode;
	document.form1.submit();
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
}
function saveAccount(xg){
	var id = document.getElementById("id").value;  
	var ne = document.getElementById("ne").value;  
	var de = document.getElementById("de").value;  
	window.open("../sys/addBasicData.jsp?id="+id+"&ne="+ne+"&de="+de+"&xg="+xg+"", "newwindow", "height=400, width=1100, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}

</script>
</form>
</body>

</html>
