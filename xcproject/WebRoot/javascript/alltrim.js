/************************************
Method:		allTrim()
purpose:	去掉界面上所有的空格
created by xu_ywei
************************************/
function allTrim(){
	divColl = document.all.tags("input");   
	    for (i=0; i<divColl.length; i++) {   
	         whichEl = divColl(i);      
	         whichEl.value = trim(whichEl.value);        
	    }    
}
function trim(str) 
{
 str = this != window? this : str;
 return str.replace(/^\s+/g, '').replace(/\s+$/g, '');
}