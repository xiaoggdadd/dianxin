/************************************************
function:	checkDateSeq
purpose:	检验输入的时间段,日期格式yyyy-MM-dd
parameters :	起始日期控件名称，终止日期控件名称，formatter输入日期的格式
                输入值为年，formatter对应"y"；
                输入值为月，formatter对应"ym"；
                输入值为年月日，formatter对应"ymd"；
return value :	TRUE / FALSE
*************************************************/
function checkDateSeq(qsrqController,zzrqController,formatter,qsdateTagYear,zzdateTagYear){
  var qsrq=qsrqController.value;
  var zzrq=zzrqController.value;
  if (qsrq.length==0 && zzrq.length!=0){
    alert("请输入起始日期！");
    qsdateTagYear.focus();
    return false;
  }
  if (zzrq.length==0 && qsrq.length!=0){
    alert("请输入终止日期！");
    zzdateTagYear.focus();
    return false;
  }
if (zzrq.length!=0 && qsrq.length!=0){
  if (!checkDate(qsrqController,formatter,qsdateTagYear)){
    return false;
  }
  if (!checkDate(zzrqController,formatter,zzdateTagYear)){
    return false;
  }

  if (checkDate(qsrqController,formatter,qsdateTagYear) && checkDate(zzrqController,formatter,zzdateTagYear)){

    if (format(qsrq)>format(zzrq)){
      alert("起始日期不能大于终止日期！");
      qsdateTagYear.focus();
      return false;
    }
  }
}

  return true;

}

//格式为yyyymmdd
function format(str)
{
  var yy="",mm="",dd="";
  m=0;
  j=0;
  for (i=0;i<=str.length;i++){
    if(str.charAt(i)=="-" || i==str.length){
      m++;
      if(m==1)
         yy=str.substring(j,i);
      if(m==2)
         mm=str.substring(j,i);
      if(m==3)
         dd=str.substring(j,i);

      j=i+1;
    }
  }
  if (mm!="")
    mm=add0(mm);
  if (dd!="")
    dd=add0(dd);

  date = yy + mm +dd;
  return (date);

}
//数字检验
function isInt(str){
 if(str.length!=0){
   for (i=0;i<str.length;i++)
     if ( str.charAt(i)<'0' || str.charAt(i)>'9')
       return false;
   }
  return true;
}
//月日去0
function trim0(str)
{
  var i,j,result="";
  j = str.length;
  for (i=0;i<j;i++)
	if ( str.charAt(i)!='0' ) break;
  result = str.substring(i,j);
  return result;
}
//月日加0
function add0(str)
{
  var i,j,result="";
  j = str.length;
  if (j==1)
    result = "0" + str;
  else
    result = str;
  return result;
}
/************************************************
function:	checkDate
purpose:	检验输入的日期yyyy-MM-dd
parameters :	日期控件名称，formatter输入日期的格式
                输入值为年，formatter对应"y"；
                输入值为月，formatter对应"ym"；
                输入值为年月日，formatter对应"ymd"；
return value :	TRUE / FALSE
*************************************************/
function checkDate(controller,formatter,dateTagYear){
var str=trim(controller.value);
controller.value = str;
m=0;
j=0;
k=0;

if (str.length==0)
	return true;

for(i=0;i<str.length;i++){
   if(str.charAt(i)=="-")
     k++;
}

if (formatter=="y"){
	if (k!=0){
		alert("只输入年份!!");
                dateTagYear.focus();
		return false;
	}
}
if (formatter=="ym"){
	if (k!=1){
		alert("输入年、月!!");
                dateTagYear.focus();
		return false;
	}
}
if (formatter=="ymd"){
	if (k!=2){
		alert("输入年、月、日!!");
                dateTagYear.focus();
		return false;
	}
}




var yy="",mm="",dd="",temp;
  for (i=0;i<=str.length;i++){
    if(str.charAt(i)=="-" || i==str.length){
      m++;
      if(m==1)
         yy=str.substring(j,i);
      if(m==2)
         mm=str.substring(j,i);
      if(m==3){
         dd=str.substring(j,i);
         if(dd==""){
           alert("日期不能为零号!!")
           dateTagYear.focus();
           return false;
        }
      }
      j=i+1;
    }
  }
if (yy.length==0)
{
	alert("年份不能为空!");
	dateTagYear.focus();
	return false;
}
if(yy.length!=4){
	   alert("年份必须为四位数字!");
	   dateTagYear.focus();
	   return false;
}
if (formatter=="ym" || formatter=="ymd")
{
	if(mm.length>2){
	   alert("月份必须为一位或两位数字!");
	   dateTagYear.focus();
	   return false;
	}
}
if (formatter=="ymd")
{
	if(dd.length>2){
	   alert("日期必须为一位或两位数字!");
	   dateTagYear.focus();
	   return false;
	}
}

yy=trim0(yy);
mm=trim0(mm);
dd=trim0(dd);


if(!isInt(yy)){
   alert("年份必须为数字!");
   dateTagYear.focus();
   return false;
}
if(parseInt(yy)<1900 || parseInt(yy)>2079 ||(yy=="")){
  alert("年份必须在1900-2079之间!!");
  dateTagYear.focus();
  return false;
}

if (formatter=="ym" || formatter=="ymd")
{
if(!isInt(mm)){
   alert("月份必须为数字!!");
   dateTagYear.focus();
   return false;
}
if(parseInt(mm)>12 || parseInt(mm)<1||(mm=="")){
    alert("月份必须在1-12之间!!!");
    dateTagYear.focus();
    return false;
}
}



if (formatter=="ymd")
{
if(!isInt(dd)){
   alert("日期必须为数字!!");
   dateTagYear.focus();
   return false;
}
if (dd=="")
{
	alert("日期不能为0!");
    dateTagYear.focus();
    return false;
}
if((mm=="1" || mm=="3" || mm=="5" || mm=="7" || mm=="8" || mm=="10" || mm=="12") && parseInt(dd)>31){
  alert("该月最大为31天!!!");
  dateTagYear.focus();
  return false;
}

if(( mm=="4" || mm=="6" || mm=="9" || mm=="11") && parseInt(dd)>30){
  alert("该月最大为30天");
  dateTagYear.focus();
  return false;
}
if(mm=="2"){
  // alert((parseInt(yy)%4));
   if((parseInt(yy)%4)==0){
          if(parseInt(dd)>29){
               alert("该月日期不能大于29");
               dateTagYear.focus();
               return false;
           }
	}
   else{
           if(parseInt(dd)>28){
               alert("该月日期不能大于28");
               dateTagYear.focus();
               return false;
           }
   }
}
}

return true;

}

function checkTwoDate(qsrqController,zzrqController,formatter){
  var qsrq=qsrqController.value;
  var zzrq=zzrqController.value;
  if (qsrq.length==0 && zzrq.length!=0){
    alert("请输入起始日期！");
    //qsrqController.focus();
    return false;
  }
  if (zzrq.length==0 && qsrq.length!=0){
    alert("请输入终止日期！");
    //zzrqController.focus();
    return false;
  }
if (zzrq.length!=0 && qsrq.length!=0){
  if (!checkDate(qsrqController,formatter))
          return false;
  if (!checkDate(zzrqController,formatter))
          return false;

  if (checkDate(qsrqController,formatter) &&
      checkDate(zzrqController,formatter)){

    if (format(qsrq)>format(zzrq)){
      //alert("起始日期不能大于终止日期！");
      //qsrqController.focus();
      return false;
    }
  }
}

  return true;

}
//删除字符串两侧的空格
function trim(str) 
{
 str = this != window? this : str;
 return str.replace(/^\s+/g, '').replace(/\s+$/g, '');
}


//用下拉框显示月、日，日下拉框根据年月确定
function DaysInMonth(WhichMonth, WhichYear)
{
  var DaysInMonth = 31;
  if (WhichMonth == "04" || WhichMonth == "06" || WhichMonth == "09" || WhichMonth == "11") DaysInMonth = 30;
  if (WhichMonth == "02" && (WhichYear/4) != Math.floor(WhichYear/4))	DaysInMonth = 28;
  if (WhichMonth == "02" && (WhichYear/4) == Math.floor(WhichYear/4))	DaysInMonth = 29;
  return DaysInMonth;
}

//function to change the available days in a months
//用于根据年月显示日期下拉框长度
function ChangeOptionDays(YearObject,MonthObject,DaysObject)
{

  Month = MonthObject[MonthObject.selectedIndex].text;
  Year = trim(YearObject.value);
  if (Month!="")
  {
  
	  if (Year=="")
	  {
		  alert("请先输入年份！");
		  YearObject.focus();
		  return;
	  }
	if (Year.length==0)
	{
		alert("年份不能为空!");
		YearObject.focus();
		return;
	}
	if(Year.length!=4){
		   alert("年份必须为四位数字!");
		   YearObject.focus();
		   return;
	}
	if(!isInt(Year)){
	   alert("年份必须为数字!");
	   YearObject.focus();
	   return;
	}
	if(parseInt(Year)<1900 || parseInt(Year)>9999 ||(Year=="")){
	  alert("年份必须在1900-9999之间!!");
	  YearObject.focus();
	  return;
	}
}
/*
if (Year!="")
{
  if (Month=="")
  {
	  alert("请选择月份！");
	  MonthObject.focus();
	  return;
  }
}
*/
  DaysForThisSelection = DaysInMonth(Month, Year);
  CurrentDaysInSelection = DaysObject.length;
  if (CurrentDaysInSelection-1 > DaysForThisSelection)
  {
    for (i=0; i<(CurrentDaysInSelection-1-DaysForThisSelection); i++)
    {
      DaysObject.options[DaysObject.options.length - 1] = null
    }
  }
  if (DaysForThisSelection > CurrentDaysInSelection-1)
  {
    for (i=0; i<(DaysForThisSelection-CurrentDaysInSelection+1); i++)
    {
      NewOption = new Option(DaysObject.options.length,DaysObject.options.length);
      DaysObject.add(NewOption);
    }
  }
    if (DaysObject.selectedIndex < 0) DaysObject.selectedIndex == 0;
}




