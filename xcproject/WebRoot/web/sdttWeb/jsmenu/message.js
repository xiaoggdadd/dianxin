/*
 * Javascript Common File
 * Copyright 2011-2012 By Jinan Ryouka Science & Technology Co., Ltd
 * Author: Wang kewee
 * Date: 2011/06/15
 * Version 1.0
 */
 
/*** 共通用MESSAGE函数 ***/
function dispMessage(strMsg,MsgMode) {
	if (MsgMode==0) {   //警告MESSAG
		alert(strMsg);
		return false;
	
	} else {            //确认MESSAG
	   if (confirm(strMsg)==true){
				return true;
		}else{
				return false;
		}
	}
}

/*** 共通用MESSAGE一览 警告MESSAGE --- OK ***/
var MESSAGE0001           = '用户名不能为空，请重新输入！';
var MESSAGE0002           = '密码不能为空，请重新输入！';


/*** 共通用MESSAGE一览 确认MESSAGE --- OK + CANCEL ***/
var MESSAGE1001           = '确实要删除本项记录吗？';