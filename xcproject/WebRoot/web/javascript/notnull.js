/************************************
Method:		checkNotnull()
purpose:	 非空
parameters :	控件名称，提示信息
return value :	true/false.
************************************/
function checkNotnull(controller,message){
	var value=(controller.value);
	if (value.length==0){
		alert(message+"不能为空！")
		return false;
	}
	return true;

}
/**
 * 检查两次输入字符串是否相等
 */
function checkEqual(controller1,controller2,message){
 	var value1 = controller1.value
        var value2 = controller2.value
        if(value1!=value2){
         	alert("两次输入 "+message+" 不相等！")
                return false
        }else{
         	return true
        }
}
/**
 * 检查输入邮箱格式是否正确
 */
 /*
 function checkEmail(controller){
  	var value = controller.value
        if(value!="")){
        	if(value.indexOf("@")==-1){
           		alert("输入的邮箱格式不正确！")
               		 return false
        	}else{
         		return true
        	}
        }else{
         	return true
        }
 }
*/
