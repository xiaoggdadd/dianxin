/*
 * Javascript Common File
 * Copyright 2011-2012 By Jinan Ryouka Science & Technology Co., Ltd
 * Author: Wang kewee
 * Date: 2011/06/15
 * Version 1.0
 */
 
/*** ��ͨ��MESSAGE���� ***/
/*
 * MODE: 0  
 */
 
function dispMessage( strMsg, MsgMode) {
	if (MsgMode==0) {
		alert(strMsg);
		return true;
	
	} else {
	   if (confirm(strMsg)==true){
				return true;
			}else{
				return false;
			}
	}
}

/*** ��ͨ��MESSAGEһ�� ����MESSAGE ***/
var MESSAGE0001_OK           = '�û�����Ϊ�գ����������룡';
var MESSAGE0002_OK           = '���벻��Ϊ�գ����������룡';

/*** ��ͨ��MESSAGEһ�� ȷ��MESSAGE ***/
var MESSAGE0002_OKCANCEL     = 'ȷʵҪɾ�����¼��';