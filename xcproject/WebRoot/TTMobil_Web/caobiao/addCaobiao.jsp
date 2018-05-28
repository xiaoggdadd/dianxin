<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name=”apple-mobile-web-app-capable” content=”yes” />
<meta name="description" content="">
<meta name="author" content="">
<link href="../css/xk-style.css" rel="stylesheet">
<link href="../css/xk-info.css" rel="stylesheet">
<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../../includes/ico/apple-touch-icon-114.png">
<link rel="apple-touch-icon-precomposed"
	href="../ico/apple-touch-icon-57.png">
<link rel="shortcut icon" href="../ico/favicon.png">
<script type="text/javascript" src="../js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<%String path = request.getContextPath(); %>
<script type="text/javascript">
var path = '<%=path%>';
var classObj=
     {
       ToUnicode:function(str) 
       {
        return escape(str).replace(/%/g,"\\").toLowerCase();
       },
       UnUnicode:function(str)
       {
        return unescape(str.replace(/\\/g, "%"));
       },
      copyingTxt:function(str)
      {
       document.getElementById(str).select(); 
       document.execCommand("Copy"); 
      }
    };
</script>
<script type="text/javascript">
	$(document).ready(function() {
		searchCbuser();
		searchZd();
		//searchErrorMessage();
	});
	
	

	function searchZd() {
		$.ajax({ // 请求登录处理页
			url : path+"/CBUserServlet", // 登录处理页
			dataType : "json",
			// 传送请求数据
			data : {
				'method' : 'searchZd'
			},
			success : function(jsonValue) { // 登录成功后返回的数据
				var zdhtml = "";
				for ( var i = 0; i < jsonValue.length; i++) {
					var json = jsonValue[i];
					zdhtml += "<option value=\""+json.ID+"\">" + json.JZNAME
							+ "</option>";
				}
				$("#zdList").html(zdhtml);
				searchDb(jsonValue[0].ID);
			}
		});
	}

	function doSearchDb() {
		var dbid = $("#zdList").val();
		searchDb(dbid);
	}

	function searchDb(dbid) {
		$.ajax({ // 请求登录处理页
			url : path+"/CBUserServlet", // 登录处理页
			dataType : "json",
			// 传送请求数据
			data : {
				'method' : 'searchDb',
				'dbid' : dbid
			},
			success : function(jsonValue) { // 登录成功后返回的数据
				var dbhtml = "";
				for ( var i = 0; i < jsonValue.length; i++) {
					var json = jsonValue[i];
					dbhtml += "<option value=\""+json.DBID+"\">" + json.DBBM
							+ "</option>";
				}
				$("#dbList").html(dbhtml);
				searchLastDL();
			}
		});
	}
	
	function searchErrorMessage(){
		$.ajax({ // 请求登录处理页
			url : path+"/CBUserServlet", // 登录处理页
			dataType : "html",
			// 传送请求数据
			data : {
				'method' : 'searchErroMessage'
			},
			success : function(jsonValue) { // 登录成功后返回的数据
				if(jsonValue=="0"){
					goBack();
				}else{
					alert(jsonValue);
				}
				
			}
		});
	}
	
	function searchDj(){
		var dbid = $("#dbList").val();
		var cbTime = $("#datetime").val();
		$.ajax({
			url : path+"/CBUserServlet", // 登录处理页
			dataType : "json",
			// 传送请求数据
			data : {
				'method' : 'searchDj',
				'dbid':dbid,
				'cbTime':cbTime
			},
			success : function(jsonValue) { // 登录成功后返回的数据
				var value = jsonValue.DANJIA;
				if(value==null){
					canSave = false;
					alert("该电表尚未录入本月单价！");
				}
			}
		});
	}
	
	var canSave = true;
	
	function searchZdl(){
		var dbid = $("#dbList").val();
		var cbTime = $("#datetime").val();
		$.ajax({
			url : path+"/CBUserServlet", // 登录处理页
			dataType : "json",
			// 传送请求数据
			data : {
				'method' : 'searchZdl',
				'dbid':dbid,
				'cbTime':cbTime
			},
			success : function(jsonValue) { // 登录成功后返回的数据
				var value = jsonValue.ZDL;
				if(value==null){
					canSave = false;
					alert("该电表尚未录入本月直流负荷！");
				}
				searchDj();
			}
		});
	}
	
	function searchLastDL(){
		var dbid = $("#dbList").val();
		var cbTime = $("#datetime").val();
		$.ajax({
			url : path+"/CBUserServlet", // 登录处理页
			dataType : "json",
			// 传送请求数据
			data : {
				'method' : 'searchLastDbdl',
				'dbid':dbid,
				'cbTime':cbTime
			},
			success : function(jsonValue) { // 登录成功后返回的数据
				var value = jsonValue.THISDEGREE;
				var time = jsonValue.THISDATETIME;
				$("#lastDlValue").val(value);
				$("#lastCBTime").val(time);
				searchZdl();
			}
		});
	}
	
	function searchCbuser() {
		$.ajax({ // 请求登录处理页
			url : path+"/CBUserServlet", // 登录处理页
			dataType : "json",
			contentType : "application/json",
			// 传送请求数据
			data : {
				'method' : 'searchCbuser'
			},
			success : function(jsonValue) { // 登录成功后返回的数据
				var userid = jsonValue.ACCOUNTID;
				$("#userid").val(userid);
			
				var username = jsonValue.NAME;
				$("#username").val(classObj.UnUnicode(username));

				var datetime = jsonValue.DATETIME;
				$("#datetime").val(datetime);

				var ssdwname = jsonValue.SSDWNAME;
				$("#ssdwname").val(classObj.UnUnicode(ssdwname));
			}
		});
	}
	
	function ajaxFileUpload() {
		var ssdwname = $("#ssdwname").val();
		var zdid = $("#zdList").val();
		var dbid = $("#dbList").val();
		var isLd = $("#isLd").val();
		var hasZzsfp = $("#hasZzsfp").val();
		var dlValue = $("#dlValue").val();
		
		var lastDlValue = $("#lastDlValue").val();
		var lastCBTime = $("#lastCBTime").val();
		var cbyName = $("#username").val();
		var cbyDatetime = $("#datetime").val();
		
    	$.ajaxFileUpload({
        	url: path+"/CBUserSendMessageServlet", //用于文件上传的服务器端请求地址
            secureuri: false, //一般设置为false
            data:{'ssdwname':ssdwname,'zdid':zdid,'dbid':dbid,'isLd':isLd,'hasZzsfp':hasZzsfp,'dlValue':dlValue,'lastDlValue':lastDlValue,'lastCBTime':lastCBTime,'cbyName':cbyName,'cbyDatetime':cbyDatetime},
            fileElementId: ['dbImage','ldImage1','ldImage2'], //文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType: 'json', //返回值类型 一般设置为json
            success: function (data)  //服务器成功响应处理函数
            {
            	alert(data.message);
            	if(data.flag){
            		setTimeout("goBack()",1000);
            	}
            },
            error: function (e)//服务器响应失败处理函数
            {
                alert(e);       
            }
		});
     }
	
	function daoru(){
		var dlValue = $("#dlValue").val();
		var lastDlValue = $("#lastDlValue").val();
		
		if(Number(dlValue)<=Number(lastDlValue)){
			alert("本次抄表读数应大于上次抄表读数！");
			return;
		}else if(canSave){
			document.form1.action = path+"/CBUserSendMessageServlet";
			document.form1.submit();
		}else{
			alert("请先完善电价和直流负荷！");
		}
	}
	
	function goBack(){
		this.location.href = "../login.jsp";
	}
</script>
</head>
<body>
	<header class="bar_fixed">
		<div id="toolbar_1" class="toolbar">
			<div class="ui-toolbar-left">
				<a href="javascript:goBack();" class="ui-toolbar-backbtn"></a>
			</div>
			<h2 class="ui-toolbar-title">山东铁塔基站抄表手机客户端</h2>
			<div class="ui-toolbar-right">
				<span id="menu" class="ui-toolbar-home" onclick="show()"></span>
			</div>
		</div>
		<div id="dropmenu" style="display:none;">
			<nav>
				<ul class="head-nav clearfix">
					<li><a href="msgList.html" title="">►通知消息</a>
					</li>
					<li><a href="msgAdd.html" title="">►发送消息</a>
					</li>
				</ul>
			</nav>
		</div>
	</header>
	<div class="module_nav">基站抄表</div>
	<article style="padding:0px 0 0px;">
		<div class="pad8">
			<table width="100%" border="0" cellpadding="3" cellspacing="0">
				<tbody>
					<tr>
						<th width="150px" height="35" class="fgray" align="right">基站名称：</th>
						<td>
							<input id="ssdwname" name="ssdwname" type="text" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th width="120px" height="35" class="fgray" align="right">&nbsp;</th>
						<td><select size="1" id="zdList" name="zdid"
							onchange="doSearchDb();" style="font-family:微软雅黑;width:270px">
						</select>&nbsp;</td>
					</tr>
					<tr>
						<th width="120px" height="35" class="fgray" align="right">&nbsp;电表编号：</th>
						<td><select size="1" id="dbList" name="dbid"
							style="font-family:微软雅黑;width:180px" onchange="searchLastDL();">

						</select>&nbsp;</td>
					</tr>
					<tr>
						<th width="120px" height="35" class="fgray" align="right">电表图片：</th>
						<td><input id="dbImage" name="dbImage" class="buttonstyle" type="file" style="width:180px"
							value="图片文件路径" />
						</td>
					</tr>
					<tr>
						<th width="120px" height="35" class="fgray" align="right">有无偷漏电：</th>
						<td>无<input id="isLd" name="isLd" type="radio" value="0"
							checked />&nbsp;&nbsp;有<input name="isLd" type="radio"
							value="1" /></td>
					</tr>
					<tr>
						<th width="120px" height="35" class="fgray" align="right">有无增值税发票：</th>
						<td>无<input id="hasZzsfp" name="hasZzsfp" type="radio" value="0"
							checked />&nbsp;&nbsp;有<input name="isLd" type="radio"
							value="1" /></td>
					</tr>
					<tr>
						<th width="81px" height="35" class="fgray" align="right"></th>
						<td><input id="ldImage1" name="ldImage1" class="buttonstyle" type="file" style="width:180px"
							value="图片文件路径" />
						</td>
					</tr>
					<tr>
						<th width="81px" height="35" class="fgray" align="right">电流数据：</th>
						<td><input id="dlImage2" name="dlImage2" class="buttonstyle" type="file" style="width:180px"
							value="图片文件路径" />
						</td>
					</tr>
					<tr>
						<th width="81px" height="35" class="fgray" align="right">本次抄表读数：</th>
						<td><input id="dlValue" name="dlValue" type="text" style="width:180px" onkeyup="this.value=this.value.replace(/[^0-9]+\./g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"
							value="0" />
						</td>
					</tr>
					<tr>
						<th width="81px" height="35" class="fgray" align="right">上次抄表读数：</th>
						<td><input id="lastDlValue" name="lastDlValue" type="text" style="width:180px"
							value="" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th width="81px" height="35" class="fgray" align="right">上次抄表时间：</th>
						<td><input id="lastCBTime" name="lastCBTime" type="text" style="width:180px"
							value="" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th width="81px" height="35" class="fgray" align="right">抄表员：</th>
						<td>
							<input type="text" id="username" name="cbyName" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th width="81px" height="35" class="fgray" align="right">抄表时间：</th>
						<td>
						<input type="text" id="datetime" name="cbyDatetime" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th width="81px" height="40" class="fgray" align="right">&nbsp;</th>
						<td>
							<button id="sumbitButton" class="buttonstyle" onclick="ajaxFileUpload();">提交</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="refshButton" class="buttonstyle" onclick="fClear();" >重置</button>
						</td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" id="userid" name="userid" value=""/>
		</div>
	</article>
	<footer>
		<div style="margin-top:0px;"></div>
		<div class="foot-link">
			<a href="javascript:gotohead();" class="fgray">通知消息</a>&nbsp;&nbsp;<a
				href="../index.html" class="fgray">抄表流程</a>
		</div>
		<script type="text/javascript">
			function gotohead() {
				window.scrollTo(0, 1);
			}
		</script>
		<div class="media-link">
			<a href="#" class="pc">&nbsp;</a>
		</div>
		<div class="copy">中国铁塔山东省公司&nbsp;&nbsp;Copyright &copy;
			2016-2018</div>
	</footer>
</body>
</html>