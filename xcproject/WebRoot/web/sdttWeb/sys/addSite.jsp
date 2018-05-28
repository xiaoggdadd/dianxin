<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean" %>
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
	String bz="0"; 
	bz=request.getParameter("bz");
	String idzd="";
	idzd=request.getParameter("id");
	SiteManage st=new SiteManage();
	 ArrayList fylist = new ArrayList();
         fylist=null; 
         String xmbm="",xmname="",xmlx="",xmlxsm="",id="";
	if(bz!=null&&bz.equals("1")&&idzd!=null){
	 	fylist= st.getPageDataZDXG(bz,idzd);
	 	 if(fylist!=null)
			{
			int fycount = ((Integer)fylist.get(0)).intValue();
				for( int k = fycount;k<fylist.size()-1;k+=fycount){
	 	        id = (String) fylist.get(k + fylist.indexOf("ID"));
       		
       			xmname = (String) fylist.get(k + fylist.indexOf("NAME"));
       		     
       		    xmbm= (String) fylist.get(k + fylist.indexOf("CODE"));
       		     
       			xmlxsm= (String) fylist.get(k + fylist.indexOf("INDEXS1"));
       		     
       		    xmlx= (String) fylist.get(k + fylist.indexOf("TYPE"));
       		    
       		    }
       		    }
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%if(!"1".equals(bz)){ %>
		<title>字典数据新增</title>
		<% }else{%>
		<title>字典数据修改</title>
		<%}%>
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
				
				<%if(!"1".equals(bz)){ %>
		<div class="tit01">字典数据新增</div>
		<% }else{%>
		<div class="tit01">字典数据修改</div>
		<%}%>
				<div class="content01_1">
					<table width="1000px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr>
							
						</tr>
					
				
					
						<tr>
							<td align="right" width="100px">项目编码</td>
							<td width="100px">
								<input type="text" name="xmbm" id="xmbm" value="<%=xmbm%>" style="width:130px" maxlength="45"/>&nbsp;
							</td>
							<td align="right" width="100px">项目名称</td>
							<td width="100px">
								<input type="text" name="xmname" id="xmname" value="<%=xmname%>" style="width:130px" maxlength="80"/>
							</td>
							<td align="right" width="100px">项目类型</td>
							<td width="100px">
								<input type="text" name="xmlx" id="xmlx" value="<%=xmlx%>" style="width:130px" maxlength="60"/>
							</td>
							<td align="right" width="100px">项目类型说明</td>
							<td width="100px">
								<input type="text" name="xmlxsm" id="xmlxsm" value="<%=xmlxsm%>"  style="width:130px" maxlength="40"/>&nbsp;
							</td>
						</tr>
						
						<tr>
							<td align="right" colspan="8" height="60px">
								<input name="baocun" onclick="saveAccount()" type="button" class="btn_c1" id="baocun" value="保存" />&nbsp; 
								<input name="chz" type="submit" class="btn_c1" id="chz" value="重置" />&nbsp; 
							</td>
						</tr>
					</table>
					<input type="hidden" name="idzd" value="<%=idzd%>" />
				</div>
			</div>
		</td></tr></table>
		
<script type="text/javascript">
var path='<%=path%>';
function saveAccount(){
			var a=document.form1.xmbm.value;//站点类型 
		    var b=document.form1.xmname.value;
		    var c = document.form1.xmlx.value;
		    var d = document.form1.xmlxsm.value;
          			
		            if( 
		            checkNotnull(document.form1.xmbm,"项目编码")&&
		            checkNotnull(document.form1.xmname,"项目名称")&&
		            checkNotnull(document.form1.xmlx,"项目类型")&&
		            checkNotnull(document.form1.xmlxsm,"项目类型说明")
		            )
	     	 {	     	
			      	if(confirm("确认添加或修改字典信息")){       		
          				document.form1.action=path+"/servlet/zhandian?action=addSitezdxx&bz="+'<%=bz%>';
						document.form1.submit();
					    showdiv("请稍等..............");
            }
			   
		}                                     
        
   }


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
		return;s
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
	function fanhui(){
	window.close();
	}
	
		}
</script>
</form>
</body>

</html>
