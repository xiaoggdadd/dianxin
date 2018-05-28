/**************************************
 注释的函数都是现用到的，其他的不明确 
 ***************************************/
/**************************************
文档初始执行的函数
 ***************************************/
$(function() {
	 
	var support = (function(input) {
		return function(attr) {
			return attr in input
		}
	})(document.createElement('input'))
	if (!(support('placeholder') && $.browser.webkit)) {
		$('input[placeholder],textarea[placeholder]').placeholder( {
			useNative : false,
			hideOnFocus : false,
			style : {
				textShadow : 'none'
			}
		});
	}

	if (!support('autofocus')) {
		$('input[autofocus]').focus()
	}
});
/**************************************
文档初始执行的函数，登录的点击事件
 ***************************************/
$(function() {
	$("#btnLogin").click(send);
	$("#btnLogin").mouseup(function() {
		$("#btnLogin img").attr("src", "images/denglu_.gif");
		$("#btnLogin span").css( {
			"color" : "white",
			"left" : "27",
			"top" : "1"
		});
	});
	$("#btnLogin").mousedown(function() {
		$("#btnLogin img").attr("src", "images/denglu_.gif");
		$("#btnLogin span").css( {
			"color" : "rgb(214,213,213)",
			"left" : "28",
			"top" : "2"
		});
	});
	$("#btnReset").mouseup(function() {
		$("#btnReset img").attr("src", "images/chongzhi_.gif");
		$("#btnReset span").css( {
			"color" : "white",
			"left" : "27",
			"top" : "1"
		});
	});
	$("#btnReset").mousedown(function() {
		$("#btnReset img").attr("src", "images/chongzhi_.gif");
		$("#btnReset span").css( {
			"color" : "rgb(214,213,213)",
			"left" : "28",
			"top" : "2"
		});
	});
	$("#btnReset").click(function() {
		$("input[type != 'button']").val("");
	});
	$("#user").keypress(function(e) {
		if (e.keyCode == 13) {
			$("#pass").focus();
		}
	});
	$("#pass").keypress(function(e) {
		if (e.keyCode == 13) {
			send();
		}
	});
})
function checkForm(user, pass) {
	if (user == "")
		return false;
	if (pass == "")
		return false;
	return true;
}
/******************************
点击事件  登录事件调用的函数send()
*******************************/
function send() {
	var user = $("#user").val();
	var pass = $("#pass").val();
	var rCode = $("#rCode").val();
	
	if ($("#phone").attr("checked") == true) {//选择电话登录的方式（本系统暂时不使用该功能）
		if (!/^1\d{10}$/.test(user)) {
			alert("电话号码格式不正确！");
		} else {
			if (checkForm(user, pass)) {
				$.post(//执行AJAX 判断登录用户是否存在
								"servlet/login?action=loginOn2",
								{
									user : user,
									pass : pass
								},
								function(data, status) {
									if (status == "success") {
										if (data == 1) {//成功																	
											showdiv("请稍等.........."); 
											window.location.href = "servlet/NewsServlet?action=getnews";
										} else {
											alert("账户或密码不正确，请重试！");
										}
									} else {
										alert("网络错误，请检查！");
									}
								});
			} else {
				alert("请输入登录信息！");
			}
		}  
	} else {
		if (checkForm(user, pass)) {//普通 用户名 和密码登录方式
			if(rCode==""){
				alert("请输入验证码！");
				return;
			}
			$ .post(
			"servlet/login?action=loginOn&typeStr=0",
			{
				user : user,
				pass : pass,
				rCode: rCode
			},
			function(data, status) {
			//	data = 1;
				if (status == "success") {
					if (data == 1) {//成功
						if(pass=="12345"){
							if(confirm("请更改初始密码，立即更改请点确定！")){
								window.open("updatePWD.jsp", "newwindow", "height=350, width=500,screenX=350px,screenY=350PX  toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");
								return;
							}else{
								alert("未修改初始密码不允许登陆!");
								return;
							}
						}
						showdiv("请稍等.........."); 
						window.location.href = "servlet/NewsServlet?action=getnews";
					} else if(data==2) {
						alert("验证码错误！");
						getVerificationCode();
					} else {
						alert("账户或密码不正确，请重试！");
						getVerificationCode();
					}
				} else {
					alert("网络错误，请检查！");
				}
			});
		} else {
			alert("请输入登录信息！");
		}
	}
}
function showDiv(_sID, event) {
	var arrySize = getPageSize();
	var oObj = $(_sID);
	var oDiv = document.createElement("div");
	oDiv.id = "overlay";
	document.body.appendChild(oDiv);
	var overlay = $("overlay");
	overlay.height = arrySize[1];
	overlay.width = arrySize[0];
	if (event == null) {
		if (oObj.style.left == "") {
			oObj.left = arrySize[0] / 2 - 150;
		}
		if (oObj.top == "") {
			oObj.top = arrySize[0] / 2;
		}
	} else {
		var iEvent = window.event ? window.event : event;
		oObj.left = arrySize[0] / 2 - 150; // iEvent.clientX;
		oObj.top = arrySize[1] / 2 - 150;// iEvent.clientY;
	}

	var floatDiv = document.getElementById("floatDiv");
	floatDiv.style.display = "block";
	overlay.display = "block";
	overlay.zindex = oObj.zindex - 1;
}

function getPageSize() {
	var xScroll, yScroll;
	if (window.innerHeight && window.scrollMaxY) {
		xScroll = document.body.scrollWidth;
		yScroll = window.innerHeight + window.scrollMaxY;
	} else if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac
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
	} else if (document.documentElement
			&& document.documentElement.clientHeight) { // Explorer 6 Strict Mode
		windowWidth = document.documentElement.clientWidth;
		windowHeight = document.documentElement.clientHeight;
	} else if (document.body) { // other Explorers
		windowWidth = document.body.clientWidth;
		windowHeight = document.body.clientHeight;
	}

	// for small pages with total height less then height of the viewport
	if (yScroll < windowHeight) {
		pageHeight = windowHeight;
	} else {
		pageHeight = yScroll;
	}

	// for small pages with total width less then width of the viewport
	if (xScroll < windowWidth) {
		pageWidth = windowWidth;
	} else {
		pageWidth = xScroll;
	}
	arrayPageSize = new Array(pageWidth, pageHeight, windowWidth, windowHeight)
	return arrayPageSize;
}
function closeDiv(_sID) {
	var oObj = $(_sID);
	var overlay = $("overlay");
	if (overlay != null) {
		overlay.outerHTML = "";
		oObj.display = "none";
		window.location.href = "web/sdttWeb/index.html";
	} else {
		oObj.display = "none";
	}
}