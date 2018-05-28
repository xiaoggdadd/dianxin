//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
//★                                                              ★
//★ 日期控件 Version 1.0                                         ★
//★                                                              ★
//★ Code by Chris.J(黄嘉隆)                                      ★
//★                                                              ★
//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

function PopupCalendarny(InstanceName)
{
	///Global Tag
	this.instanceName=InstanceName;
	///Properties
	this.separator="-"
	this.oBtnTodayTitle="Today"
	this.oBtnCancelTitle="Cancel"
	this.weekDaySting=new Array("S","M","T","W","T","F","S");
	this.monthSting=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
	this.Width=200;
	this.currDate=new Date();
	this.today=new Date();
	this.startYear=2000;
	this.endYear=this.currDate.getYear();
	///Css
	this.normalfontColor="#666666";
	this.selectedfontColor="red";
	this.divBorderCss="1px solid #BCD0DE";
	this.titleTableBgColor="#98B8CD";
	this.tableBorderColor="#CCCCCC"
	///Method
	this.Init=CalendarInitny;
	this.Fill=CalendarFillny;
	this.Refresh=CalendarRefreshny;
	this.Restore=CalendarRestoreny;
	///HTMLObject
	this.oTaget=null;
	this.oPreviousCell=null;
	this.sDIVID=InstanceName+"_Div";
	this.sTABLEID=InstanceName+"_Table";
	this.sMONTHID=InstanceName+"_Month";
	this.sYEARID=InstanceName+"_Year";
	this.sTODAYBTNID=InstanceName+"_TODAYBTN";
	
}
function CalendarInitny()				///Create panel
{
	var sMonth,sYear
	sMonth=this.currDate.getMonth();
	sYear=this.currDate.getYear();
	htmlAll="<div id='"+this.sDIVID+"' style='display:none;position:absolute;width:"+this.Width+";border:"+this.divBorderCss+";padding:2px;background-color:#FFFFFF'>";
	htmlAll+="<div align='center'>";
	/// Month
	htmloMonth="<select id='"+this.sMONTHID+"' onchange=CalendarMonthChange("+this.instanceName+") style='width:50%'>";
	for(i=0;i<12;i++)
	{			
		htmloMonth+="<option value='"+i+"'>"+this.monthSting[i]+"</option>";
	}
	htmloMonth+="</select>";
	/// Year
	htmloYear="<select id='"+this.sYEARID+"' onchange=CalendarYearChange("+this.instanceName+") style='width:50%'>";
	for(i=this.startYear;i<=this.endYear;i++)
	{   
	    if(sYear==i){
	      htmloYear+="<option selected value='"+i+"'>"+i+"</option>";
	    }else{
		  htmloYear+="<option value='"+i+"'>"+i+"</option>";
		}
	}
	htmloYear+="</select></div>";
	/// Today Button
	htmloButton="<div align='center' style='padding:3px'>"
	htmloButton+="<button id='"+this.sTODAYBTNID+"' style='width:40%;border:1px solid #BCD0DE;background-color:#eeeeee;cursor:hand'"
	htmloButton+=" onclick=CalendarTodaynyClick("+this.instanceName+")>"+this.oBtnTodayTitle+"</button>&nbsp;"
	htmloButton+="<button style='width:40%;border:1px solid #BCD0DE;background-color:#eeeeee;cursor:hand'"
	htmloButton+=" onclick=CalendarCancel("+this.instanceName+")>"+this.oBtnCancelTitle+"</button> "
	htmloButton+="</div>"
	/// All
	htmlAll=htmlAll+htmloMonth+htmloYear+htmloButton+"</div>";
	document.write(htmlAll);
	this.Fill();	
}
function CalendarFillny()			///
{
	var sMonth,sYear,sWeekDay,sToday,oTable,currRow,MaxDay,iDaySn,sIndex,rowIndex,cellIndex,oSelectMonth,oSelectYear
	sMonth=this.currDate.getMonth();
	sYear=this.currDate.getYear();
	sWeekDay=(new Date(sYear,sMonth,1)).getDay();
	sToday=this.currDate.getDate();
	iDaySn=1
	oTable=document.all[this.sTABLEID];
	//currRow=oTable.rows[1];
	MaxDay=CalendarGetMaxDay(sYear,sMonth);
	
	oSelectMonth=document.all[this.sMONTHID]
	oSelectMonth.selectedIndex=sMonth;
	oSelectYear=document.all[this.sYEARID]
	for(i=0;i<oSelectYear.length;i++)
	{
		if(parseInt(oSelectYear.options[i].value)==sYear)oSelectYear.selectedIndex=i;
	}
	
}
function CalendarRestoreny()					/// Clear Data
{	
	var i,j,oTable
	oTable=document.all[this.sTABLEID]
	for(i=1;i<oTable.rows.length;i++)
	{
		for(j=0;j<oTable.rows[i].cells.length;j++)
		{
			CalendarCellSetCss(0,oTable.rows[i].cells[j]);
			oTable.rows[i].cells[j].innerHTML="&nbsp;";
		}
	}	
}
function CalendarRefreshny(newDate)					///
{
	this.currDate=newDate;
	this.Restore();	
	this.Fill();	
}
function CalendarCellsMsOver(oInstance)				/// Cell MouseOver
{
	var myCell = event.srcElement;
	CalendarCellSetCss(0,oInstance.oPreviousCell);
	if(myCell)
	{
		CalendarCellSetCss(1,myCell);
		oInstance.oPreviousCell=myCell;
	}
}
function CalendarCellsMsOut(oInstance)				////// Cell MouseOut
{
	var myCell = event.srcElement;
	CalendarCellSetCss(0,myCell);	
}
function CalendarYearChange(oInstance)				/// Year Change
{
	var sDay,sMonth,sYear,newDate
	sDay=oInstance.currDate.getDate();
	sMonth=oInstance.currDate.getMonth();
	sYear=document.all[oInstance.sYEARID].value
	newDate=new Date(sYear,sMonth,sDay);
	oInstance.Refresh(newDate);
}
function CalendarMonthChange(oInstance)				/// Month Change
{
	var sDay,sMonth,sYear,newDate
	sDay=oInstance.currDate.getDate();
	sMonth=document.all[oInstance.sMONTHID].value
	sYear=oInstance.currDate.getYear();
	newDate=new Date(sYear,sMonth,sDay);
	oInstance.Refresh(newDate);	
}
function CalendarCellsClick(oCell,oInstance)
{
	var sDay,sMonth,sYear,newDate
	sYear=oInstance.currDate.getFullYear();
	sMonth=oInstance.currDate.getMonth();
	sDay=oInstance.currDate.getDate();
	if(oCell.innerText!=" ")
	{
		sDay=parseInt(oCell.innerText);
		if(sDay!=oInstance.currDate.getDate())
		{
			newDate=new Date(sYear,sMonth,sDay);
			oInstance.Refresh(newDate);
		}
	}
	sDateString=sYear+oInstance.separator+CalendarDblNum(sMonth+1)+oInstance.separator+CalendarDblNum(sDay);		///return sDateString
	if(oInstance.oTaget.tagName.toLowerCase()=="input")oInstance.oTaget.value = sDateString;
	CalendarCancel(oInstance);
	return sDateString;
}
function CalendarTodaynyClick(oInstance)				/// "Today" button Change
{	
   var sDay,sMonth,sYear,newDate
	sYear=oInstance.currDate.getFullYear();
	sMonth=oInstance.currDate.getMonth();
	sDay=oInstance.currDate.getDate();	
	sDateString=sYear+oInstance.separator+CalendarDblNum(sMonth+1);		///return sDateString
	//alert(sDateString);
	if(oInstance.oTaget.tagName.toLowerCase()=="input")oInstance.oTaget.value = sDateString;
	CalendarCancel(oInstance);
	return sDateString;	
}
function getDatenyString(oInputSrc,oInstance)
{    
	if(oInputSrc&&oInstance) 
	{
		var CalendarDiv=document.all[oInstance.sDIVID];
		oInstance.oTaget=oInputSrc;
		CalendarDiv.style.pixelLeft=CalendargetPos(oInputSrc,"Left");
		CalendarDiv.style.pixelTop=CalendargetPos(oInputSrc,"Top") + oInputSrc.offsetHeight;
		CalendarDiv.style.display=(CalendarDiv.style.display=="none")?"":"none";	
		
	}	
}
function CalendarCellSetCss(sMode,oCell)			/// Set Cell Css
{
	// sMode
	// 0: OnMouserOut 1: OnMouseOver 
	if(sMode)
	{
		oCell.style.border="1px solid #5589AA";
		oCell.style.backgroundColor="#BCD0DE";
	}
	else
	{
		oCell.style.border="1px solid #FFFFFF";
		oCell.style.backgroundColor="#FFFFFF";
	}	
}
function CalendarGetMaxDay(nowYear,nowMonth)			/// Get MaxDay of current month
{
	var nextMonth,nextYear,currDate,nextDate,theMaxDay
	nextMonth=nowMonth+1;
	if(nextMonth>11)
	{
		nextYear=nowYear+1;
		nextMonth=0;
	}
	else	
	{
		nextYear=nowYear;	
	}
	currDate=new Date(nowYear,nowMonth,1);
	nextDate=new Date(nextYear,nextMonth,1);
	theMaxDay=(nextDate-currDate)/(24*60*60*1000);
	return theMaxDay;
}
function CalendargetPos(el,ePro)				/// Get Absolute Position
{
	var ePos=0;
	while(el!=null)
	{		
		ePos+=el["offset"+ePro];
		el=el.offsetParent;
	}
	return ePos;
}
function CalendarDblNum(num)
{
	if(num < 10) 
		return "0"+num;
	else
		return num;
}
function CalendarCancel(oInstance)			///Cancel
{
	var CalendarDiv=document.all[oInstance.sDIVID];
	CalendarDiv.style.display="none";		
}