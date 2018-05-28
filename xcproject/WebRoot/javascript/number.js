/************************************
Method:		checkNumber()
purpose:	 为数字
parameters :	控件名称，提示信息
return value :	true/false.
************************************/
function checkNumber(controller,message)
{
	var value=controller.value;
        var count=0;
	if (value.length!=0)
        {
	  for (var i=0;i<value.length;i++)
          {
            if (value.charAt(i)>"9" || value.charAt(i)<"0")
            {
              alert(message + "为0-9的数字");
              controller.focus();
              return(false);
            }
	  }
          if ( value.length > 10 )
          {
               alert("数字太大，请检查!");
               controller.focus();
               return false;
          }
          else if ( value.length == 10)
          {
            if (value > "2147483647")
            {
              alert("数字太大，请检查!");
              controller.focus();
              return false;
            }
          }
        }
	return true;
}
/************************************
Method:		checkMayMinusNumber()
purpose:	 可以为负数的数字
parameters :	控件名称，提示信息
return value :	true/false.
************************************/
function checkMayMinusNumber(controller,message)
{
	var value=controller.value;
        var count=0;
	if (value.length!=0)
        {
	  for (var i=0;i<value.length;i++)
          {
            if(i == 0 && value.charAt(0) == "-")//第一位为负号，通过
				continue;
			if ((value.charAt(i)>"9" || value.charAt(i)<"0"))
            {
              alert(message + "为0-9的数字");
              controller.focus();
              return(false);
            }
	  }
        if(value.charAt(0) != "-")
		{
		  if ( value.length > 10 )
          {
               alert("数字太大，请检查!");
               controller.focus();
               return false;
          }
          else if ( value.length == 10)
          {
            if (value > "2147483647")
            {
              alert("数字太大，请检查!");
              controller.focus();
              return false;
            }
          }
		}
		else
		{
           if ( value.length > 11 )
          {
               alert("数字太大，请检查!");
               controller.focus();
               return false;
          }
          else if ( value.length == 11)
          {
            if (value > "-2147483647")
            {
              alert("数字太大，请检查!");
              controller.focus();
              return false;
            }
          }
		}
		  
        }
	return true;
}
/************************************
Method:		checkNotZeroNumber()
purpose:	 为数字
parameters :	控件名称，提示信息
return value :	true/false.
************************************/
function checkNotZeroNumber(controller,message)
{
	var value=controller.value;
        var count=0;
	if (value.length!=0)
      {
	  for (var i=0;i<value.length;i++)
          {
            if (value.charAt(i)>"9" || value.charAt(i)<"0")
            {
              alert(message + "为0-9的数字");
              controller.focus();
              return(false);
            }
	      }
	  if ( value.length > 10 )
	  {
		   alert("数字太大，请检查!");
		   controller.focus();
		   return false;
	  }
	  else if ( value.length == 10)
	  {
		if (value > "2147483647")
		{
		  alert("数字太大，请检查!");
		  controller.focus();
		  return false;
		}
	  }
	  if (parseInt(value,10)==0)
	  {
		  alert(message+"不能等于零");
		  controller.focus();
		  return(false);
	  }
    }
	return true;
}

/*******
 * 监测是否是数字（不管数字长短）
 */
function checkIsNum(controller,message)
{
  var value=controller.value;
  var count=0;
  if (value.length!=0)
  {
    for (var i=0;i<value.length;i++)
    {
      if (value.charAt(i)>"9" || value.charAt(i)<"0")
      {
        alert(message + "为0-9的数字");
        controller.focus();
        return(false);
      }
    }
  }
  return true;
}