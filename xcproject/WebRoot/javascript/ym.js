var gdCurDate = new Date();
var giYear = gdCurDate.getFullYear();
var giMonth = gdCurDate.getMonth()+1;
var gdCtrl = new Object();	
var closebutton = '<a href="javascript:void(0);" style="position:absolute; right:6px; top:5px;cursor:pointer;" id="closed" onclick="fHideCalendar();">关闭</a>';
var YearOpt = '<select name="tbSelYear" id="tbSelYear">';

for(i=1900;i<=2200;i++){
  YearOpt = YearOpt + '<option value="'+ i +'">'+ i +'</option>';
 // YearOpt = YearOpt + "<option value='"+ i +"'>"+ i +"</option>";
}
YearOpt = YearOpt + '</select>';
var MonthOpt = '<select name="tbSelMonth" id="tbSelMonth">';
for(i=1;i<=12;i++){
	if(i<10){
		MonthOpt = MonthOpt + '<option value="0'+ i +'">'+'0'+ i +'</option>';
	}else{
		MonthOpt = MonthOpt + '<option value="'+ i +'">'+ i +'</option>';
	}
 
}
MonthOpt = MonthOpt + '</select>';
var setokbutton = '<input type="button" value="确定" onclick="fSetDate();" />';
var calendarDiv='<div id="calendarbox" style="visibility :hidden;">'+ closebutton +"<div style=\'color:#ffffff; position:absolute; top:10px;\'>"+ YearOpt + MonthOpt + setokbutton +"</div></div>";
document.write(calendarDiv);
fSetYearMon(giYear,giMonth);
function fPopCalendar(evt,popCtrl,dateCtrl){
 evt.cancelBubble=true;
 gdCtrl = dateCtrl;
 var point = fGetXY(popCtrl);
 with(document.getElementById("calendarbox").style){
  width = "200px";
  height = "40px";
  position = "absolute";
  left = point.x+"px";
  top = (point.y+popCtrl.offsetHeight+1)+"px";
  visibility  = "visible";
  zindex = "100";
  backgroundColor = "#FFFFFF";
  //filter = "alpha(opacity = 50)";
  border = "1px solid #090";
  fontSize = "12px";
  lineHeight = "25px";
  padding = "5px";
 }
}
function Point(iX,iY){this.x = iX;this.y = iY;}
function fGetXY(aTag){
 var oTmp = aTag;
 var pt = new Point(0,0);
 do{
  pt.x += oTmp.offsetLeft;pt.y += oTmp.offsetTop;oTmp = oTmp.offsetParent;
 }while(oTmp.tagName.toLowerCase()!="body");
 return pt;
}
function fSetDate(){
 var iYear = document.getElementById("tbSelYear").value;
 var iMonth = document.getElementById("tbSelMonth").value;
 gdCtrl.value = iYear +'-'+ iMonth;
 fHideCalendar();
}
function fHideCalendar(){
 document.getElementById("calendarbox").style.visibility = "hidden";
}
function fSetYearMon(iYear,iMon){
 document.getElementById("tbSelMonth").options[iMon-1].selected = true;
 for(var i=0;i<document.getElementById("tbSelYear").length;i++){
  if(document.getElementById("tbSelYear").options[i].value==iYear){
   document.getElementById("tbSelYear").options[i].selected = true;
  }
 }
}
