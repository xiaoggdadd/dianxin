/*
 * Javascript Common File
 * Copyright 2011-2012 By Jinan Ryouka Science & Technology Co., Ltd
 * Author: Wang kewee
 * Date: 2011/06/15
 * Version 1.0
 */
 

/*** 登录页面    文件名:login.aspx ***/
//注册
	function register(){
		document.frmLogin.action='./register/register.html';
		document.frmLogin.submit();
	}
	
	//帮助
	function fOnhelp() {
		document.frmLogin.action='help.html';
		document.frmLogin.submit();
	}
	
	function check(){
		var name = document.getElementById('usernameinput').value;
		var psd = document.getElementById('passwordInput').value;
		
		if(name == ''){
			dispMessage(MESSAGE0001,0)
		}else if(psd == '')
		{
			dispMessage(MESSAGE0002,0)
		}else{
			frmLogin.submit();
		}
	}
	
	function fDispMessage() {
		document.getElementById('divMsg').style.display = 'block';
	
	}
	
	function fCloseMessage() {
		document.getElementById('divMsg').style.display = 'none';
	
	}
	
	function sConfirm(str){
			var msgw,msgh,bordercolor;
			msgw=300;//提示窗口的宽度
			msgh=120;//提示窗口的高度
			titleheight=25 //提示窗口标题高度
			bordercolor="#336699";//提示窗口的边框颜色
			titlecolor ="#99CCFF";//提示窗口的标题颜色
            
			var sWidth,sHeight;
			sWidth=document.body.offsetWidth;
			sHeight=screen.height;

			var bgObj=document.createElement("div");
			bgObj.setAttribute('id','bgDiv');
			bgObj.style.position="absolute";
			bgObj.style.top="0";
			bgObj.style.background="#ffffff";
			bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
			bgObj.style.opacity="0.6";
			bgObj.style.left="0";
			bgObj.style.width=sWidth + "px";
			bgObj.style.height=sHeight + "px";
			bgObj.style.zIndex = "10000";
			document.body.appendChild(bgObj);
			
			var msgObj=document.createElement("div")
			msgObj.setAttribute("id","msgDiv");
			msgObj.setAttribute("align","center");
			msgObj.style.background="#f4fafb";
			msgObj.style.border="1px solid " + bordercolor;
	    	msgObj.style.position = "absolute";
            msgObj.style.left = "50%";
            msgObj.style.top = "50%";
            msgObj.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif";
            msgObj.style.marginLeft = "-225px" ;
            msgObj.style.marginTop = -75+document.documentElement.scrollTop+"px";
            msgObj.style.width = msgw + "px";
            msgObj.style.height =msgh + "px";
            msgObj.style.textAlign = "center";
            msgObj.style.lineHeight ="25px";
            msgObj.style.zIndex = "10000";
   
		   var title=document.createElement("h4");
		   title.setAttribute("id","msgTitle");
		   title.setAttribute("align","left");
		   title.style.margin="0";
		   title.style.padding="3px";
		   title.style.background=bordercolor;
		   //title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
		   title.style.opacity="0.75";
		   title.style.border="1px solid " + bordercolor;
		   title.style.height="20px";
		   title.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif";
		   title.style.color="#ffffff";
		   title.style.cursor="pointer";
		   title.innerHTML="&nbsp;系统警告";
		   document.body.appendChild(msgObj);
		   document.getElementById("msgDiv").appendChild(title);
		   
		   var txt=document.createElement("p");
		   txt.setAttribute("align","left");
		   txt.style.background="#f4fafb";
		   txt.style.margin="1em 0"
		   txt.setAttribute("id","msgTxt");
		   txt.innerHTML="&nbsp;&nbsp;" + str;
		   
		   var close=document.createElement("span");
		   close.setAttribute("align","right");
		   close.style.background="#f4fafb";
		   close.style.margin="1em 0"
		   close.setAttribute("id","btnclose");
		   close.style.cursor="pointer";
		   //close.style.border="1px solid " + bordercolor;
		   close.innerHTML="&nbsp;[确认]&nbsp;&nbsp;";
		   close.onclick=function(){
		        document.body.removeChild(bgObj);
                document.getElementById("msgDiv").removeChild(title);
                document.body.removeChild(msgObj);
                }
                
           var btnok=document.createElement("span");
		   btnok.setAttribute("align","right");
		   btnok.style.background="#f4fafb";
		   btnok.style.margin="1em 0"
		   btnok.setAttribute("id","btnclose");
		   btnok.style.cursor="pointer";
		   //close.style.border="1px solid " + bordercolor;
		   btnok.innerHTML="&nbsp;[取消]&nbsp;&nbsp;";
		   btnok.onclick=function(){
		        document.body.removeChild(bgObj);
                document.getElementById("msgDiv").removeChild(title);
                document.body.removeChild(msgObj);
                }
                
           var hr=document.createElement("hr");
           document.getElementById("msgDiv").appendChild(txt);
           document.getElementById("msgDiv").appendChild(hr);
           document.getElementById("msgDiv").appendChild(close);
           document.getElementById("msgDiv").appendChild(btnok);
   
        }
 
 		function sAlert(str){
			var msgw,msgh,bordercolor;
			msgw=300;//提示窗口的宽度
			msgh=100;//提示窗口的高度
			titleheight=25 //提示窗口标题高度
			bordercolor="#336699";//提示窗口的边框颜色
			titlecolor ="#99CCFF";//提示窗口的标题颜色
			
			var sWidth,sHeight;
			sWidth=document.body.offsetWidth;
			sHeight=screen.height;

			var bgObj=document.createElement("div");
			bgObj.setAttribute('id','bgDiv');
			bgObj.style.position="absolute";
			bgObj.style.top="0";
			bgObj.style.background="#ffffff";
			bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
			bgObj.style.opacity="0.6";
			bgObj.style.left="0";
			bgObj.style.width=sWidth + "px";
			bgObj.style.height=sHeight + "px";
			bgObj.style.zIndex = "10000";
			document.body.appendChild(bgObj);
			
			var msgObj=document.createElement("div")
			msgObj.setAttribute("id","msgDiv");
			msgObj.setAttribute("align","center");
			msgObj.style.background="#f4fafb";
			msgObj.style.border="1px solid " + bordercolor;
	    	msgObj.style.position = "absolute";
            msgObj.style.left = "50%";
            msgObj.style.top = "50%";
            msgObj.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif";
            msgObj.style.marginLeft = "-225px" ;
            msgObj.style.marginTop = -75+document.documentElement.scrollTop+"px";
            msgObj.style.width = msgw + "px";
            msgObj.style.height =msgh + "px";
            msgObj.style.textAlign = "center";
            msgObj.style.lineHeight ="25px";
            msgObj.style.zIndex = "10001";
   
		   var title=document.createElement("h4");
		   title.setAttribute("id","msgTitle");
		   title.setAttribute("align","left");
		   title.style.margin="0";
		   title.style.padding="3px";
		   title.style.background=bordercolor;
		   //title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
		   title.style.opacity="0.75";
		   title.style.border="1px solid " + bordercolor;
		   title.style.height="20px";
		   title.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif";
		   title.style.color="#ffffff";
		   title.style.cursor="pointer";
		   title.innerHTML="&nbsp;系统提示";
		   title.onclick=function(){
		        document.body.removeChild(bgObj);
                document.getElementById("msgDiv").removeChild(title);
                document.body.removeChild(msgObj);
                }
		   document.body.appendChild(msgObj);
		   document.getElementById("msgDiv").appendChild(title);
		   
		   var txt=document.createElement("p");
		   txt.setAttribute("align","left");
		   txt.style.background="#f4fafb";
		   txt.style.margin="1em 0"
		   txt.setAttribute("id","msgTxt");
		   txt.innerHTML="&nbsp;&nbsp;" + str;
		   
		   var hr=document.createElement("hr");
		   
		   var close=document.createElement("p");
		   close.setAttribute("align","right");
		   close.style.background="#f4fafb";
		   close.style.margin="1em 0"
		   close.setAttribute("id","btnclose");
		   close.style.cursor="pointer";
		   //close.style.border="1px solid " + bordercolor;
		   close.innerHTML="&nbsp;[关闭]&nbsp;&nbsp;";
		   close.onclick=function(){
		        document.body.removeChild(bgObj);
                document.getElementById("msgDiv").removeChild(title);
                document.body.removeChild(msgObj);
                }
                
           document.getElementById("msgDiv").appendChild(txt);
           document.getElementById("msgDiv").appendChild(hr);
           document.getElementById("msgDiv").appendChild(close);

        }
 
