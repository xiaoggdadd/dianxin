/*
 * Javascript Common File
 * Copyright 2011-2012 By Jinan Ryouka Science & Technology Co., Ltd
 * Author: Wang kewee
 * Date: 2011/06/15
 * Version 1.0
 */
 
/*** ��ͨ��MESSAGE���� ***/
function dispMessage(strMsg,MsgMode) {
	if (MsgMode==0) {   //����MESSAG
		alert(strMsg);
		return false;
	
	} else {            //ȷ��MESSAG
	   if (confirm(strMsg)==true){
				return true;
		}else{
				return false;
		}
	}
}

/*** ��ͨ��MESSAGEһ�� ����MESSAGE --- OK ***/
var MESSAGE0001           = '�û�������Ϊ�գ����������룡';
var MESSAGE0002           = '���벻��Ϊ�գ����������룡';


/*** ��ͨ��MESSAGEһ�� ȷ��MESSAGE --- OK + CANCEL ***/
var MESSAGE1001           = 'ȷʵҪɾ�������¼��';