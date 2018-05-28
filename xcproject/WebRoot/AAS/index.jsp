  <!DOCTYPE html> 
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ptac.app.version.Version" %>
<%
String version = new Version().getVersion();
%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <style>
.whiteFont{font-size: 12px;color:#fff;}
form{ margin:0px; padding:0px}
.btnLogin{width:40px;height:15px; background:url(images/btn_login.jpg) 0 0 no-repeat; border:0; color:#fff;}
    	
		#loginpanel{
			top:15%;
			position: relative;
			margin-left: aotu;
			margin-right: auto;
		}
		.sysInfo{
			width: 250px;
			position: relative;
			top:50px;
			right:130px;
		}
		.loginForm{
			left:130px;
			position: relative;
			width: 300px;
			top:-35px;
		}
		.loginForm table tr td{
			font: normal 12px tahoma, arial, verdana, sans-serif;
		}
		html,body{
		height:100%;
		margin:0px;
		} 
		body{
		background:#FFF;
		font-size:12px;
		font-family:宋体;
		
		}
		img{
		border:0px;
		}
		 
		#overlay{
		position: absolute;
		top: 0;
		left: 0;
		z-index: 99;
		width: 1600px;
		height: 800px;
		background-color: #000;
		filter:alpha(opacity=40);
		-moz-opacity: 0.6;
		opacity: 0.6;
		}
		 
		.floatDiv{
			padding:0 0 0 0; 
			position:absolute; 
			width:300px; 
			margin:0 0 0 0; 
			z-index:100;
			border:1px solid #DADADA; 
			background:#FFF; 
			padding:1px;
			float:left;
			right:500px;
			top:250px;
		}
		 
		.floatDiv .divTitle{
		background:#E0E0E0 url('images/dragForm/title_bg.gif');
		background-position:top left;
		background-repeat:repeat-x;
		height:25px;
		line-height:25px;
		padding:0px 4px;
		cursor:default;
		}
		 
		.floatDiv .divContent{
		padding:3px; 
		min-height:100px;
		height:auto; cursor:default;
		}
		 
		.floatDiv .divFoot{
		background:#F0F0F0; 
		text-align:right; 
		padding:4px;
		background:#E0E0E0 url('images/dragForm/bottom_bg.gif');
		background-position:top left;
		background-repeat:repeat-x;
		}
		 
		.floatDiv input.divButton{
		background:#E0E0E0 url('images/dragForm/button_bg.gif');
		background-position:top left;
		background-repeat:repeat-x;
		border:1px solid #D1D3D2;
		height:21px;
		font-size:12px;
		padding:2px 5px;
		color:#626264;
		}
		
    </style>
    <script type="text/javascript" src="../javascript/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="../javascript/wait.js"></script>
    <script src="../javascript/placeholder.js"></script> 
    <script type="text/javascript">
                      //2-4
                  $(function(){               
                   var support = (function(input) { 
                        return function(attr) { return attr in input } 
                   })(document.createElement('input'))          
                   if ( !(support('placeholder') && $.browser.webkit) ) {               
                        $('input[placeholder],textarea[placeholder]').placeholder({ 
                             useNative: false,  
                             hideOnFocus: false,  
                             style: {  
                                  textShadow: 'none'  
                             }  
                        }); 
                   } 
                   
                   if ( !support('autofocus') ) { 
                        $('input[autofocus]').focus() 
                   } 
              });

    	$(function(){
    		$("#btnLogin").click(send);    		
    		$("#btnLogin").mouseup(function(){
    			$("#btnLogin img").attr("src","images/denglu_.gif");
    			$("#btnLogin span").css({"color":"white","left":"27","top":"1"});
    		});
    		$("#btnLogin").mousedown(function(){
    			$("#btnLogin img").attr("src","images/denglu_.gif");
    			$("#btnLogin span").css({"color":"rgb(214,213,213)","left":"28","top":"2"});
    		});
    		$("#btnReset").mouseup(function(){
    			$("#btnReset img").attr("src","images/chongzhi_.gif");
    			$("#btnReset span").css({"color":"white","left":"27","top":"1"});
    		});
    		$("#btnReset").mousedown(function(){
    			$("#btnReset img").attr("src","images/chongzhi_.gif");
    			$("#btnReset span").css({"color":"rgb(214,213,213)","left":"28","top":"2"});
    		});
    		$("#btnReset").click(function(){
    			$("input[type != 'button']").val("");
    		});
    		$("#user").keypress(function(e){
    			if(e.keyCode == 13){
    				$("#pass").focus();
    			}
    		});
    		$("#pass").keypress(function(e){
    			if(e.keyCode == 13){
    				send();
    			}
    		});
    	})
    	function checkForm(user,pass){
    		if(user == "")return false;
    		if(pass == "")return false;
    		return true;
    	}
    	function send(){
    	// var regu =/(^\([1-9]{3}\)[1-9]{3}(-\d{4})?$)|(^\([1-9]{3}\)\s[1-9]{3}(-\d{4})?$)|(^([1-9]{3}\s\/\s[1-9]{3}(-\d{4}))?$)
                 //  |(^([1-9]{3}-[1-9]{3}(-\d{4}))?$)|(^([1-9]{3}\s[1-9]{3}(\s\d{4}))?$)|(^\d{10}$)/;
    	var user = $("#user").val();
    	var pass = $("#pass").val();
    	  if($("#phone").attr("checked")==true){
          if(!/^1\d{10}$/.test(user)){
    	    alert("电话号码格式不正确！");
    	   }else{
    	if(checkForm(user,pass)){
    				$.post(
    					"../servlet/login?action=loginOn2",
    					{user:user,pass:pass},
    					function(data,status){
							if(status == "success"){
								if(data == 1){//成功																	
									showdiv("请稍等..........");
									//showDiv('floatDiv',event);
									window.location.href="servlet/NewsServlet?action=getnews";
								}
								else{
									alert("账户或密码不正确，请重试！");
								}
							}
							else{
								alert("网络错误，请检查！");
							}
						}
    				);
    			}
    			else{
    				alert("请输入登录信息！");
    			}
    			}
         // if(!(/^1[3|5][0-9]\d{4,8}$/.test(user))){ 
           //   alert("电话号码格式不正确！"); 
        //  } 
    	  }else {
    	  alert();
    	         if(checkForm(user,pass)){
    				$.post(
    					"../servlet/login?action=loginOn",
    					{user:user,pass:pass},
    					function(data,status){
							if(status == "success"){
								if(data == 1){//成功																	
									showdiv("请稍等..........");
									//showDiv('floatDiv',event);
									window.location.href="servlet/NewsServlet?action=getnews";
								}
								else{
									alert("账户或密码不正确，请重试！");
								}
							}
							else{
								alert("网络错误，请检查！");
							}
						}
    				);
    			}
    			else{
    				alert("请输入登录信息！");
    			}}
    	}
    	function showDiv(_sID,event){
			var arrySize = getPageSize();
			var oObj = $(_sID);
			var oDiv =document.createElement("div");
			oDiv.id = "overlay";
			document.body.appendChild(oDiv);
			var overlay = $("overlay");
			overlay.height = arrySize[1];
			overlay.width = arrySize[0];
			if(event == null){
			   if(oObj.style.left == ""){
			    oObj.left = arrySize[0] / 2 - 150 ;
			   }
			   if(oObj.top == ""){
			    oObj.top = arrySize[0] / 2;
			   }  
			}else{  
			   var iEvent= window.event ? window.event : event;  
			   oObj.left = arrySize[0] / 2 - 150 ; // iEvent.clientX;
			   oObj.top = arrySize[1] / 2 - 150 ;// iEvent.clientY;
			}
			
			var floatDiv= document.getElementById("floatDiv");
			floatDiv.style.display = "block";
			overlay.display = "block";
			overlay.zindex = oObj.zindex - 1;
		}
			
		function getPageSize(){			
				var xScroll, yScroll;			
				if (window.innerHeight && window.scrollMaxY) { 
				   xScroll = document.body.scrollWidth;
				   yScroll = window.innerHeight + window.scrollMaxY;
				} else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
				   xScroll = document.body.scrollWidth;
				   yScroll = document.body.scrollHeight;
				} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
				   xScroll = document.body.offsetWidth;
				   yScroll = document.body.offsetHeight;
				}
				
				var windowWidth, windowHeight;
				if (self.innerHeight) { // all except Explorer
				   windowWidth = self.innerWidth;
				   windowHeight = self.innerHeight;
				} else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
				   windowWidth = document.documentElement.clientWidth;
				   windowHeight = document.documentElement.clientHeight;
				} else if (document.body) { // other Explorers
				   windowWidth = document.body.clientWidth;
				   windowHeight = document.body.clientHeight;
				} 
				
				// for small pages with total height less then height of the viewport
				if(yScroll < windowHeight){
				   pageHeight = windowHeight;
				} else { 
				   pageHeight = yScroll;
				}
				
				// for small pages with total width less then width of the viewport
				if(xScroll < windowWidth){ 
				   pageWidth = windowWidth;
				} else {
				   pageWidth = xScroll;
				}	
				arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight) 
				return arrayPageSize;
			}
			function closeDiv(_sID){
				var oObj = $(_sID);
				var overlay = $("overlay"); 
				if(overlay != null){				
				   overlay.outerHTML = "";
				   oObj.display = "none";
				   window.location.href="web/index.jsp";
				}else{
					oObj.display = "none"; 
				}
			}
		</script>
		
		
<title>站点账户管理平台v<%=version %></title>
</head>
<body background="../pic/2015xc.jpg" style="width: 100%">

     									<table  style="margin-top:200px; margin-left:980px;">
											<tr>
  												
  												<td height="35" ><font size="3"><span class="blue">用户名:&nbsp;&nbsp;</span></font></td>
  												<td colspan="2"><input id="user" type="text"  placeholder="请输入用户名.." style="width:115px;height:19px;background-image:url(images/input_bg.jpg);border:0px solid #9E0E0E;font:15px Verdana, Arial; color:#1B1B1B;"/></td>
												
											<td></td>
											</tr>
											<tr height="20">
												<td></td>
											</tr>
											<tr>
 												<td><font size="3"><span class="blue">密&nbsp;&nbsp;码:&nbsp;&nbsp;</span></font></td>
  												<td colspan="2"><input id="pass" type="password" placeholder="请输入密码.." style="width:115px;height:19px;background-image:url(images/input_bg.jpg);border:0px solid #9E0E0E; font:15px Verdana, Arial; color:#1B1B1B;"/></td>
											</tr>
											<tr height="27">
												<td></td>
											</tr>
											<tr>
												<td valign="bottom" align="center">
       												<div id="btnLogin" style="width:50px;height:30px;cursor: pointer;float:left;position: relative; left: 10px">
														<img alt="" src="../images/denglu_.gif" width="100%" height="100%">
													</div>
         										</td>
         										<td></td>
         										<td valign="bottom">
        											<div id="btnReset" style="width:50px;height:30px;cursor: pointer;float:left;position: relative; left: 30px">
														<img alt="" src="../images/chongzhi_.gif" width="100%" height="100%">
														</div>
    											</td>
											</tr>
     									</table>
     						
<form action="" name="form1">
</form>
</body>
</html>