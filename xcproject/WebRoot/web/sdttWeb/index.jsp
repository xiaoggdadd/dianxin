<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date,java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.lang.*"%>
<%@ page
	import="com.noki.mobi.common.Account,com.ptac.app.version.Version"%>
<%@ page
	import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean"%>
<%
	
	String version = new Version().getVersion();
	
	String title=(String)session.getAttribute("title");
	String url2=(String)session.getAttribute("url");
	
	session.removeAttribute("title");
	session.removeAttribute("url");
	
	Account account = (Account) session.getAttribute("account");
	String name = account.getName() != null ? account.getName() : "";
	String accountname = account.getAccountName() != null ? account
			.getAccountName() : "";
	String shiname = account.getShiname() != null ? account
			.getShiname() : "";
	String path = request.getContextPath();
	String rolename = account.getRoleName();
	String loginId = account.getAccountId();
	haodianliangBean bean = new haodianliangBean();
	String flag = "1";
	String number = account.getNumber();

	String roleId = account.getRoleId();

	Date now = new Date();

	System.out.println("now:" + now);

	SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");//可以方便地修改日期格式
	String hehe = dateFormat.format(now);
	System.out.println("hehe:" + hehe);
	Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH) + 1;
	int date = c.get(Calendar.DATE);
	int hour = c.get(Calendar.HOUR_OF_DAY);
	int minute = c.get(Calendar.MINUTE);
	int second = c.get(Calendar.SECOND);
	String ymr = year + "/" + month + "/" + date;
	System.out.println("时间:" + ymr);
	System.out.println("系统时间：" + year + "/" + month + "/" + date + " "
			+ hour + ":" + minute + ":" + second);
%>



<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>中国电信山东分公司能耗管理系统</title>

<!--[if gt IE 8]><!-->
<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"></script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
<!--<![endif]-->
<!--[if lte IE 8]>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/console.js"></script>
<![endif]-->

<link href="css/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css"/>
<script type="text/javascript" src="jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
<script language="JavaScript" type="text/javascript"> 

var shrinked=false;
var shrinked2=true;
var topH=87+28;
var fulled=false;

function fDispSysMenu(number){
	document.getElementById("MenuCLManager").style.display="none";
	document.getElementById("MenuHTManager").style.display="none";
	document.getElementById("MenuXTManager").style.display="none";

	if (number==0)
	{
		document.getElementById("MenuCLManager").style.display="block";
	}

	if (number==1)
	{
		document.getElementById("MenuHTManager").style.display="block";
	}

	if (number==2)
	{
		document.getElementById("MenuXTManager").style.display="block";
	}
}

function fullscreen(){
	if (fulled){
		topH=87+28;
		$(".top1").show();
		$(".footer").show();
		if(temp_clickshow != 1){
			shrink(1);
		}
		//shrink2(1);
		fulled=false;
		$("#maincontent").contents().find(".p_r_fullscreen").html("全屏");
	}else{
		topH=2;
		$(".top1").hide();
		$(".footer").hide();
		shrink(0);
		shrink2(0);
		fulled=true;
		$("#maincontent").contents().find(".p_r_fullscreen").text("返回");
	}
	resizediv();
}

function shrink(N){
if(N==0){shrinked=false;}else if(N==1){shrinked=true;}
	if (shrinked){
		$("#leftbox").stop().animate({'left':'0px'},250);
		document.getElementById("ss").className="ss";
		$("#rightbox").animate({'marginLeft':'213px'},250);
		shrinked=false;
	}else{
		$("#leftbox").stop().animate({'left':'-194px'},250);
		document.getElementById("ss").className="ss_s";
		$("#rightbox").animate({'marginLeft':'19px'},250);
		shrinked=true;
	}
}

function shrink2(N2){
	if(N2==0){shrinked2=false;}else if(N2==1){shrinked2=true;}
	if (shrinked2){
		$("#qm").stop().animate({'width':'170px'},250);
		document.getElementById("ss2").className="ss2";
		$("#rightbox").animate({'marginRight':'180px'},250);
		shrinked2=false;
	}else{
		$("#qm").stop().animate({'width':'10px'},250);
		document.getElementById("ss2").className="ss2_s";
		$("#rightbox").animate({'marginRight':'19px'},250);
		shrinked2=true;
	}
}

function resizediv(){
	document.getElementById("leftbox").style.height="auto";
	document.getElementById("maincontent").style.height="auto";
	//document.getElementById("qm").style.height="auto";
	mainH=document.body.clientHeight;
	document.getElementById("leftbox").style.height=mainH-topH-2+"px";
	document.getElementById("maincontent").style.height=mainH-topH-2-32+"px";
	//document.getElementById("qm").style.height=mainH-topH-2+"px";
}
window.onload=resizediv;
window.onresize=resizediv;

var temp_clickshow=0;
function clickhide(n){
	var thisObj = $("#leftmenu_"+temp_clickshow);
	var tempS;
	thisObj.find("li").each(function(i){
		var tA=$(this);
		setTimeout(function(){
			tA.animate({left:"-194px"},300,function(){//setTimeout(function(){tA.animate({left:"0"},300)},200)
			});
		},60*i);
	});
	temp_clickshow=n;
	setTimeout(function(){thisObj.hide();if(temp_clickshow==1){shrink(0)}else{shrink(1);}clickshow(temp_clickshow);},thisObj.find("li").size()*60);
}
function clickshow(n){
var thisObj = $("#leftmenu_"+n);
var tempS;
	thisObj.show();
	thisObj.find("li").each(function(i){
		var tA=$(this);
		setTimeout(function(){
			tA.animate({left:"0"},300)
		},60*i);
	});
temp_clickshow=n;
}

function tmenushow(NN){
for(i=1;i<8;i++){
	document.getElementById("tml_"+i).className="";
}
	document.getElementById("tml_"+NN).className="cur";
	clickhide(NN);
}
</script>
<script language="javascript">
var path = '<%=path%>';
	function logout() {
		if (confirm("确定要退出系统么？")) {

			parent.location = path + "/tuichu.jsp";
		} else {
			return false;
		}
	}

	$(document).ready(function() {
		$("#menu li a").prepend('<span class="out"></span>');
		$("#menu li a").prepend('<span class="over"></span>');
		$("#menu li a").append('<span class="bg"></span>');
		$("#menu li a").append('<span class="bg2"></span>');

		$("#menu li a").hover(function() {
			// 鼠标经过时候被触发的函数
			$(".out", this).stop().animate({
				'top' : '76px'
			}, 150); // 向下滑动 - 隐藏
			$(".over", this).stop().animate({
				'top' : '0px'
			}, 250); // 向下滑动 - 显示
			$(".bg", this).stop().animate({
				'top' : '76px'
			}, 250); // 向下滑动 - 隐藏
			$(".bg2", this).stop().animate({
				'top' : '0px'
			}, 350); // 向下滑动 - 显示

		}, function() {
			// 鼠标移出时候被触发的函数
			$(".out", this).stop().animate({
				'top' : '0px'
			}, 250); // 向上滑动 - 显示
			$(".over", this).stop().animate({
				'top' : '-76px'
			}, 150); // 向上滑动 - 隐藏
			$(".bg", this).stop().animate({
				'top' : '0px'
			}, 350); // 向上滑动 - 显示
			$(".bg2", this).stop().animate({
				'top' : '-76px'
			}, 250); // 向下滑动 - 隐藏
		});

		//左侧菜单伸缩
		var tabs_i = 0;
		$('.bm').click(function() {
			var _self = $(this);
			var j = $('.bm').index(_self);
			if (tabs_i == j)
				return false;
			tabs_i = j;
			$('.bm').each(function(e) {
				if (e == tabs_i) {
					$(_self).addClass('cur');
				} else {
					$(this).removeClass('cur');
				}
			});
			$('.leftmenu li div').slideUp(300).eq(tabs_i).slideDown(400);
		});

		//左侧二级菜单更改样式
		$('.leftmenu div a').not('.leftmenu div a.submenu').click(function() {
			//"div:not([delay='false'])"     $("li").find("ul").prev().click
			$(this).addClass('cur');
			$('.leftmenu div a').not($(this)).removeClass('cur');
			$(this).parent().prev().addClass('cur');
		});
		//左侧三级菜单伸缩
		$('.leftmenu a.submenu').click(function() {
			$(this).next().toggle(400);
		});

		//左侧菜单伸缩222
		$(".leftmenu").hide();
		$(".leftmenu li").css("left", "-194px");
		clickshow(1);
		//window.location.href="GaojingManager/workAlert.jsp";
	});

	function modifypass() {
		if (confirm("确定要修改密码么？")) {

			window
					.open(
							"sys/modifyPass1.jsp",
							"newwindow",
							"height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
		} else {
			return false;
		}

	}
</script>
</head>
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean">
</jsp:useBean>
<jsp:useBean id="rightBean" scope="page" class="com.noki.mobi.sys.javabean.RightBean">
</jsp:useBean>
<body>
	<div class="top1">
		<div class="top2">
			<div class="top3">
				<div class="welcome">
					<div class="welcome3">
						<a class="ti3" onclick="modifypass()"><i></i>修改密码</a><a
							class="ti1" onclick="logout()"><i></i>注销用户</a><a
							onclick="logout()" class="ti5"><i></i>退出系统</a>
					</div>
				</div>
				<div></div>
			</div>
		</div>
	</div>
	<div>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" style="background:#dbeefd;height:25px;">
			<tr>
				<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%=shiname%><%=name%>[<%=rolename%>]
					，欢迎您使用本系统。</td>
				<td align="right">当前时间:<%=ymr%>&nbsp;<%=hehe%>&nbsp;&nbsp;</td>
			</tr>
		</table>
	</div>
	<div class="main">
		<div class="leftbox" id="leftbox">
			<!--  系统首页 -->
			<ul class="leftmenu" id="leftmenu_1">
				<li><a href="GaojingManager/workAlert.jsp" class="bm bm0 cur"
					target="maincontent" onclick="$('#tabtext').text($(this).text())"><i></i>我的主页</a>
					<div></div></li>
				<div id="menuCL" style="text-align:center;width:100%">
					<input name="button2" type="button" class="btn_c1" id="button2"
						style="height:30px; background:#05a2b0;width:180px;color:#ffffff;border:0;font-size:15px;"
						value="系统导航" />
				</div>
				<div id="MenuAutoManager" style="display:block;">
					<%
           				ArrayList rightList = new ArrayList();
                		rightList = rightBean.getAllRights(roleId);
                		int k = 1;
                		int count = ((Integer)rightList.get(0)).intValue();
                		String rName="",rightId="",checked="",block="",waporweb="",url="",icon="";
                		int end = rightList.size()-1;
                		for(int i = count;i<rightList.size()-1;i+=count){
		                   rName = (String)rightList.get(i+rightList.indexOf("NAME"));
		                   rightId = (String)rightList.get(i+rightList.indexOf("RIGHTID"));
		                   checked = (String)rightList.get(i+rightList.indexOf("CHECKED"));
		                   block = (String)rightList.get(i+rightList.indexOf("BLOCK"));
		                   waporweb = (String)rightList.get(i+rightList.indexOf("WAPORWEB"));
		                   url = (String)rightList.get(i+rightList.indexOf("URL")); 
		                   icon = (String)rightList.get(i+rightList.indexOf("ICON"));
                	%>
	                	<%if(block.equals("1")&& checked.equals("1")){
	                	%>
		                	<%if(i==0){%>
		                		<li><a href="#here" class="bm <%=icon%>"><i></i><%=rName%></a>
		                			<div style="display:none">
	                		<%}else{%>
								</li><li><a href="#here" class="bm <%=icon%>"><i></i><%=rName%></a>
		                			<div style="display:none">
	                		<%}%>
	                	<%}else if(block.equals("0")&& checked.equals("1")){%>
	                		<%if(i==end){%>
	                			<a href="<%=url%>" target="maincontent"
								onclick="$('#tabtext').text($(this).text())"><i></i><%=rName%></a></li> 
	                		<%}else{%>
	                			<a href="#here" 
								onclick="addTab('<%=rName %>','<%=url %>')"><i></i><%=rName%></a> 	                		
	                		<%}%>
	                	<%}%>
                	<%}%>
				</div>
				

		</ul>
		
	</div>

					<!-- <img id="jiantou" class="btn2" src="../images/jiantou.png"></img>
					
					<img id="jiantou" class="btn1" src="../images/jiantou.png"></img>
	 -->
	<div id="rightbox" class="easyui-tabs rightbox" >
	
		<div title="我的首页">
			<iframe id="maincontent" name="maincontent"
			src="GaojingManager/workAlert.jsp" width="100%" scrolling="yes"
			frameborder="0"></iframe>
		</div>
	</div>
	<!--<div class="rightbox" id="rightbox">
		<div class="ss" id="ss" onclick="shrink()"></div>
		<div class="ss2_s" id="ss2" onclick="shrink2()"></div>
		<ul class="tab">
			<li class="left"><a href="#">&nbsp;</a>
			</li>
			<li id="tab1" class="cur"><a href="#here" id="tabtext">我的主页</a><a
				href="#here" class="close">&nbsp;</a>
			</li>
			<li class="right"><a href="#">&nbsp;</a>
			</li>
			<div class="clear"></div>
		</ul>
		<iframe id="maincontent" name="maincontent"
			src="GaojingManager/workAlert.jsp" width="100%" scrolling="yes"
			frameborder="0"></iframe>
	</div>-->
	</div>
	<script>
		
		
		/* $(document).ready(function(){
		  $(".btn1").click(function(){
		  $(".leftbox").toggle();
		  });
		}); */
		$(".btn1").click(function(){
			$(".rightbox").css("margin","0 0px 0 0px");
			$(".rightbox").css("height","700px");
			$(".tabs-header").css("width","100%");
			//width: 1299.56px;width: 1300px;canvas;tabs
			/* $("html, body").css("min-width","1800px"); */
			$(".tabs-panels").css("width","100%");
			$(".panel-body").css("width","100%");
			$(".tabs").css("border-style","none");
			
			
			$(".btn1").css("visibility","hidden");
			$(".btn2").css("visibility","visible");
			
		});
		$(".btn2").click(function(){
			$(".rightbox").css("margin","0 0px 0 213px");
			
			$(".tabs-panels").css("width","1299.56px");
			$(".panel-body").css("width","width: 1300px");
			$(".tabs").css("border-style","solid");
			
			$(".btn2").css("visibility","hidden");
			$(".btn1").css("visibility","visible");
			
		});
		function addTab(title, url){
			if ($('#rightbox').tabs('exists', title)){
				$('#rightbox').tabs('select', title);
			} else {
				var content = '<iframe scrolling="auto" frameborder="0" src="'+url+'" onload="setIframeHeight(this)" style="width:100%;min-height:500px;"></iframe>';
				$('#rightbox').tabs('add',{
					title:title,
					content:content,
					closable:true
				});
			}
		}
		function setIframeHeight(iframe) {
			if (iframe) {
				var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
				if (iframeWin.document.body) {
					iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
				}
			}
		};
		
		
		$(function(){
			var title='<%=title%>';
			var url='<%=url2%>';
			if(title!="null"){
				var content = '<iframe scrolling="auto" frameborder="0" src="'+url+'" onload="setIframeHeight(this)" style="width:100%;min-height:500px;"></iframe>';
				$('#rightbox').tabs('add',{
					title:title,
					content:content,
					closable:true
				});
			}
			
		})
	</script>
	<div class="footer">
		<div class="version">版本： V1.0</div>
		Copyright 2017 
		版权所有
	</div>
</body>
</html>
