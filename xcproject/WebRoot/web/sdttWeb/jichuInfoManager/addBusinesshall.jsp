<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=path%>/javascript/PopupCalendar.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
	<script src="<%=path%>/javascript/tx.js"></script>
		<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	

<script>

var oCalendarEn = new PopupCalendar("oCalendarEn"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();

var oCalendarChs = new PopupCalendar("oCalendarChs"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChs.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChs.oBtnTodayTitle = "今天";
oCalendarChs.oBtnCancelTitle = "取消";
oCalendarChs.Init();
</script>
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
	
	function saveBusinesshall() {
		if(!(checkNotSelected(document.form1.entityType, "实体类型") 
				&& checkNotSelected(document.form1.entitySmallType, "子类型")
				&& checkNotnull(document.form1.entityName, "实体名称")
				&& checkNotnull(document.form1.company, "分公司")
				&& checkNotnull(document.form1.branchCompany, "区县分公司") 
				&& checkNotSelected(document.form1.practicalUse, "用途") 
				&& checkNotSelected(document.form1.ownership, "所有权")
				&& checkNotnull(document.form1.numberOfEmployees, "员工人数")
				&& checkNotnull(document.form1.builtArea, "面积"))){
			return;
		}
		if(confirm("您将要添加新实体的信息！确认信息正确！")){
            document.form1.action=path+"/servlet/businesshallServlet?action=save"
        	document.form1.submit()
        }
	}
    $(function(){
		
		$("#resetBtn").click(function(){
			$.each($("form input[type='text']"),function(){
				$(this).val("");
			})
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<jsp:useBean id="code" scope="page" class="com.ptac.app.util.Code"></jsp:useBean>
</head>
<%
    String seq = code.getNextVal();
	String entityCode = code.getEntityCode(seq);
%>
<body>
<form action="" name="form1" method="post">
<input type="hidden" name="entityCode" value="<%=entityCode %>"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">实体新增</div>
				<div class="content01_1">
					<table width="1000px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="100px">实体类型</td>
							<td width="100px">
								<select name="entityType" style="width: 130px">
									<option value="0">请选择</option>
									<option value="机楼">机楼</option>
								</select>
								<span >&nbsp;*</span>
							</td>
							<td align="right" width="100px">子类型</td>
							<td width="100px">
								<select name="entitySmallType" style="width: 130px">
									<option value="0">请选择</option>
									<option value="办公楼">办公楼(机楼)</option>
								</select>
								<span class="style1">&nbsp;*</span>
							</td>
							<td align="right" width="100px">实体编号</td>
							<td width="100px">
								<%=entityCode %>
							</td>
							<td align="right" width="100px">实体名称</td>
							<td>
							   <input type="text" name="entityName" maxlength="20" style="width: 130px;"/>
							   <span class="style1">&nbsp;*</span>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">是否附属实体</td>
							<td width="100px">
							   <input type="radio" name="isAttachedEntity" value="1"/>是
							   <input type="radio" name="isAttachedEntity" value="0" checked="checked"/>否
							</td>
							<td align="right" width="100px">分公司</td>
							<td width="100px">
								<input type="text" name="company" maxlength="20" style="width: 130px;"/>
								<span>&nbsp;*</span>
							</td>
							<td align="right" width="100px">区县分公司</td>
							<td width="100px">
								<input type="text" name="branchCompany" maxlength="20" style="width: 130px;"/>
								<span>&nbsp;*</span>
							</td>
							<td align="right" width="100px">用途</td>
							<td width="100px">
								<select name="practicalUse" style="width: 130px;">
									<option value="0">请选择</option>
									<option value="1">办公</option>
								</select>
								<span>&nbsp;*</span>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">所有权</td>
							<td width="100px">
								<select name="ownership" style="width: 130px;" >
									<option value="0">请选择</option>
									<option value="1">非自有</option>
								</select>
								<span>&nbsp;*</span>
							</td>
							<td align="right" width="100px">责任人</td>
							<td width="100px">
								<input type="text" name="personLiable" maxlength="20" style="width: 130px;" />
							</td>
							<td align="right" width="100px">使用权</td>
							<td width="100px">
								<select name="rightOfUse" style="width: 130px" >
									<option value="0">请选择</option>
									<option value="1">自用</option>
								</select>
							</td>
							<td align="right" width="100px">外围系统编码</td>
							<td width="100px">
								<input type="text" name="peripheralSystemCode" maxlength="20"  onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" value="0" style="width: 130px;" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">建筑结构</td>
							<td width="100px">
								<select name="buildingStructure" style="width: 130px" >
								    <option value="0">请选择</option>
									<option value="1">钢筋混凝土</option>
								</select>
							</td>
							<td align="right" width="100px">员工人数</td>
							<td width="100px">
								<input type="text" name="numberOfEmployees" onkeyup="value=value.replace(/[^\d]/g,'')" style="width: 130px;" />
								<span>&nbsp;*</span>
							</td>
							<td align="right" width="100px">状态</td>
							<td width="100px">
								<input type="radio" name="status" value="1" checked="checked"/>闲置
								<input type="radio" name="status" value="2" />停用
								<input type="radio" name="status" value="3" />在用
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">面积</td>
							<td width="100px">
								<input type="text" name="builtArea" onkeyup="value=value.replace(/[^\d\.]/g,'')" style="width: 130px;" />
								<span>&nbsp;*</span>
							</td>
							<td align="right" width="100px">使用单位</td>
							<td width="100px">
								<input type="text" name="organization" maxlength="20" style="width: 130px;" />
							</td>
							<td align="right" width="100px">管理部门</td>
							<td width="100px">
								<input type="text" name="manageDepartment" maxlength="20" style="width: 130px;" />
							</td>
							<td align="right" width="100px">房产证号</td>
							<td width="100px">
								<input type="text" name="certificateNumber" maxlength="20" style="width: 130px;" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">房屋价值</td>
							<td width="100px">
								<input type="text" name="houseValue" style="width: 130px;" />
							</td>
							<td align="right" width="100px">共享属性</td>
							<td width="100px">
								<input type="text" name="sharedProperty" maxlength="20" style="width: 130px;" />
							</td>
							<td align="right" width="100px">共享单位</td>
							<td width="100px">
								<input type="text" name="sharedOrganization" maxlength="20" style="width: 130px;" />
							</td>
							<td align="right" width="100px">地址</td>
							<td width="100px">
								<input type="text" name="address" style="width: 130px;" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">电流</td>
							<td width="100px">
								<input type="text" name="electricCurrent" style="width: 130px;" onkeyup="value=value.replace(/[^\d\.]/g,'')" />
							</td>
							<td align="right" width="100px">环境</td>
							<td width="100px">
								<input type="text" name="environment" maxlength="20" style="width: 130px;" />
							</td>
						
						</tr>
						<tr>
						    <td align="right" colspan="8" height="60px">
								<input name="baocun" type="button" class="btn_c1" onclick="saveBusinesshall()" id="baocun" value="保存" />&nbsp; 
								<input name="resetBtn" type="button" class="btn_c1" id="resetBtn" value="重置" />&nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td></tr></table>
		</form>
</body>
<script language="javascript">

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
            //  window.alert(res);
             
                       
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
</script>
</html>
