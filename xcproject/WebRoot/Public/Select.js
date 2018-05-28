function getDivCount() {
	var arr=document.all; 
	re=0;
	for (i=0;i<arr.length;i++) {
		str=arr[i].id;
		if (str.indexOf("ZfDiv_")==0) {   
			re++;  
		}
	} 
	return re;
}

//取得objId的最后一位数字
function getI(ObjId) {
	for (i=0;i<ObjId.length;i++) {
		if (ObjId.charAt(i)=="_") 
			return(ObjId.substr(i+1,ObjId.length-1));
	}
	return 0;
}

function select_edit(TextObj){//鼠标经过高亮度 
	TextObj.focus();
	TextObj.select();
}

function checkValue(ID){
	var sl=document.all["ZfText_"+ID]; 
	var sv=document.all["ZfDiv_"+ID];
	var da=document.all["ZfData_"+ID];   
	sv.style.display='';

	for(i=0;i<da.rows.length;i++)
		da.rows[i].style.display='';

	for(i=0;i<da.rows.length;i++)
	{
		if(da.rows[i].cells[0].innerText.indexOf(sl.value)!=0)
			da.rows[i].style.display='none';
		getPosition(ID);
	} 
}

function getL(e)
{
	var l=e.offsetLeft;
	while(e=e.offsetParent){
		l += e.offsetLeft;
	}
 	return(l);
}

function getT(e)
{
	var t=e.offsetTop;
	while(e=e.offsetParent)
	{
		t += e.offsetTop;
	}
	return(t);
}


function getPosition(ID){ 
	var sv=document.all["ZfDiv_"+ID];
	var sl=document.all["ZfText_"+ID];
	var spn=document.all["ZfSpan_"+ID];
	var da=document.getElementById("ZfData_"+ID);
	sv.style.pixelWidth=spn.offsetWidth;
	da.style.pixelWidth=sv.offsetWidth;
	sv.style.pixelLeft=getL(spn);
	sv.style.pixelTop=getT(spn)+sl.offsetHeight+3;
	if(da.offsetHeight>200){
		sv.style.pixelHeight=200;
		sv.style.overflowY='scroll';
	} else {
		sv.style.pixelHeight=da.offsetHeight;
		sv.style.overflowY='hidden';
	}
}

//下拉摸拟层
function dropDown(ID){ 
	var sv=document.all["ZfDiv_"+ID]	
	var tb=document.all["ZfData_"+ID]
 
	if(sv.style.display=='none')
	{
		sv.style.display='';  
		for(i=0;i<tb.rows.length;i++)
			tb.rows[i].style.display='';
		getPosition(ID);  
 	} else {  
		sv.style.display='none';
	}
}


//隐藏模拟层
function hiddenDiv(){
	var o=window.event.srcElement.id;
	var tb;
	var sv;
	if(o == "") 
	{
		for (j=0;j<getDivCount();j++) 
		{
			tb=document.getElementById('ZfData_'+j);
			sv=document.getElementById('ZfDiv_'+j);   
   			for(i=0;i<tb.rows.length;i++) 
   				tb.rows[i].style.display='';
   			sv.style.display='none';   
   		}
   	}

}


//给文本框赋值
function setValue(obj){
	var i=getI(obj.parentElement.parentElement.parentElement.id);
	//alert(obj.parentElement.parentElement.parentElement.id);

	var sl=document.all["ZfText_"+i];
	var sv=document.all['ZfDiv_'+i];
	sl.value=obj.innerText;
	sv.style.display='none';
	//sldIndex=obj.parentElement.rowIndex;
}


//鼠标经过变色
function over(obj){
	obj.className="td_over";
	obj.title=obj.innerText;
	obj.focus();
}


//鼠标离开还原
function out(obj){
	obj.className="td_out";
}


//自定义去空格函数Trim()
function Trim()
{
	return(this.replace(/(^\s*)|(\s*$)/g,''));
}


//增加list的接口,ID表示该组控件是页面中的第几个
function add(v,ID){
	var sv = document.all['ZfDiv_'+ID];
	if(!v.Trim())
		{return;}
	var tb = document.all['ZfData_'+ID];
	var c = tb.insertRow(tb.rows.length).insertCell();
	c.innerHTML = '<nobr>'+v.Trim()+'</nobr>';
	c.onmouseover = new Function("over(this)");
	c.onmouseout = new Function("out(this)");
	c.onclick = new Function("setValue(this)");
	c.className = "td_out";
	v = '';
}

//增加inpnubox的接口,在页面中产生一个inputbox控件,下拉列表为空
function addText(name, DefValue) {
	var i = getDivCount();
	var szInput = '';
	document.write('<span id="ZfSpan_'+i+'" heigth="16" style="border:1 solid #9CA0CB">');

	szInput =  '<input type="text" value="'+DefValue+'" name="'+name+'" id="ZfText_';
	szInput += i+'" ondblclick="ZfDrop_'+i+'.click()" class="slv" onmouseover="select_edit(this)" ';
	szInput += 'onkeyup="checkValue('+i+')">';

	szInput += '<input type=button id="ZfDrop_'+i+'" value=">" class="down" ';
	szInput += 'onclick="this.hideFocus=true;dropDown('+i+');" ';
	szInput += 'onmouseover="this.style.backgroundColor=\'#EEF3FD\'" ';
	szInput += 'onmouseout="this.style.backgroundColor=\'\'" ';
	szInput += 'onmousedown="this.style.backgroundColor=\'#ABC4F5\'" ';
	szInput += 'onmouseup="this.style.backgroundColor=\'\'"';
	szInput += '></span>';
	document.write(szInput);

	document.write('<div heigth="16" id="ZfDiv_'+i+'" class="seldiv" style="display:none;"><table id="ZfData_'+i+'" onselectstart="return false" border="0"  cellspacing="0" cellpadding="0" class="table1"></table></div>');
}
