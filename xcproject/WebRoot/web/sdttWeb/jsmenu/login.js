/*
 * Javascript Common File
 * Copyright 2011-2012 By Jinan Ryouka Science & Technology Co., Ltd
 * Author: Wang kewee
 * Date: 2011/06/15
 * Version 1.0
 */
 

/*** µÇÂ¼Ò³Ãæ    ÎÄ¼þÃû:login.aspx ***/
//×¢²á
	function register(){
		document.frmLogin.action='./register/register.html';
		document.frmLogin.submit();
	}
	
	//°ïÖú
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
		document.getElementById('cmnMessage').style.display = 'block';
	
	}
 
