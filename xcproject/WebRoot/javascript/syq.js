    var strFrame;  
    //日历用iframe实现          
    document.writeln('<iframe id=MMDateLayer  frameborder=no scrolling=no Author=MM style="position: absolute; width: 145; height: 46; z-index: 9998;display=none"></iframe>')  
    //ifram中用div定位  
    strFrame+='<div style="z-index:9999;position: absolute; left:0; top:0;" onselectstart="return false">'  
    strFrame+='<span id=tmpMMSelectYearLayer  style="z-index: 9999;position: absolute;top: 2; left: 18;display: none"></span>';  
    strFrame+='<span id=tmpMMSelectMonthLayer style="z-index: 9999;position: absolute;top: 2; left: 75;display: none"></span>';  
    strFrame+='<table border=0 cellspacing=1 cellpadding=0 width=142 height=23 bgcolor=#808080 onselectstart="return false" Author=MM>';  
    strFrame+='<tr>';  
    strFrame+='<td width=142 height=23 bgcolor=#FFFFFF>';  
    strFrame+='<table border=0 cellspacing=1 cellpadding=0 width=140 height=23 Author=MM>';  
    strFrame+='<tr align=center>'  
    strFrame+='<td width=20 Author=MM align=center bgcolor=#808080 style="font-size:12px;cursor: hand;color: #FFD700" ';  
    strFrame+=' onclick="parent.MMPrevM()" title="向前翻月" ><b Author=MM>&lt;&lt;</b>';  
    strFrame+=' </td>'  
    strFrame+='<td width=60 align=right style="font-size:12px;cursor:default" title="点击这里选择年份">';  
    strFrame+=' <span width=60 Author=MM id=MMYearHead onclick="parent.MMSelectYearInnerHTML(this.innerText)"></span>&nbsp;年&nbsp;';  
    strFrame+='</span>';  
    strFrame+='</td>';  
    strFrame+='<td width=48 align=left style="font-size:12px;cursor:default" title="点击这里选择月份">';  
    strFrame+='<span  id=MMMonthHead Author=MM onclick="parent.MMSelectMonthInnerHTML(this.innerText)"></span>&nbsp;月';  
    strFrame+='</td>';  
    strFrame+=' <td width=20 bgcolor=#808080 align=center style="font-size:12px;cursor: hand;color: #FFD700" ';  
    strFrame+=' onclick="parent.MMNextM()" title="往后翻月" Author=MM><b Author=MM>&gt;&gt;</b></td></tr>';  
    strFrame+=' </table></td></tr>';  
      
    strFrame+=' <tr><td width=142 height=18 bgcolor=#808080>';  
    strFrame+='<table border=0 cellspacing=0 cellpadding=0 width=140 height=1 style="cursor:default">';  
    strFrame+='<tr align=center><td style="font-size:12px;color:#FFFFFF" Author=MM></td>';  
    strFrame+='<td style="font-size:12px;color:#FFFFFF" Author=MM></td><td style="font-size:12px;color:#FFFFFF" Author=MM></td>';  
    strFrame+='<td style="font-size:12px;color:#FFFFFF" Author=MM  onclick=parent.MMDayClick(this.innerText)>确定</td>';  
    strFrame+='<td style="font-size:12px;color:#FFFFFF" Author=MM  onclick=parent.MMDayClick(this.innerText)>清空</td></tr>';  
    strFrame+='</table></td></tr>';  
    strFrame+='  </table></div>';  
    window.frames.MMDateLayer.document.writeln(strFrame);   
    window.frames.MMDateLayer.document.close();        //解决ie进度条不结束的问题   
      
      
    var MMoutObject;  
    var MModatelayer=window.frames.MMDateLayer.document.all;        //存放日历对象   
      
    function setMonth(tt,obj) //主调函数   
    {   
          
        if (arguments.length > 2){alert("对不起！传入本控件的参数太多！");return;}   
        if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}   
        var dads = document.all.MMDateLayer.style;   
        var th = tt;   
        var ttop = tt.offsetTop; //TT控件的定位点高   
        var thei = tt.clientHeight; //TT控件本身的高   
        var tleft = tt.offsetLeft; //TT控件的定位点宽   
        var ttyp = tt.type; //TT控件的类型   
        while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}   
        dads.top = (ttyp=="image")? ttop+thei : ttop+thei+6;   
        dads.left = tleft;   
        MMoutObject = (arguments.length == 1) ? th : obj;   
        outButton = (arguments.length == 1) ? null : th;    //设定外部点击的按钮   
        dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;  
        dads.left = tleft;  
        MMoutObject = (arguments.length == 1) ? th : obj;  
        dads.display = '';  
      event.returnValue=false;  
    }   
    ////////////////////////  
      
    var MMMonHead = new Array(12);           
        MMMonHead[0] = 31; MMMonHead[1] = 28; MMMonHead[2] = 31; MMMonHead[3] = 30; MMMonHead[4]  = 31; MMMonHead[5]  = 30;  
        MMMonHead[6] = 31; MMMonHead[7] = 31; MMMonHead[8] = 30; MMMonHead[9] = 31; MMMonHead[10] = 30; MMMonHead[11] = 31;  
    var MMTheYear=new Date().getFullYear();   
    var MMTheMonth=new Date().getMonth()+1;   
    var MMWDay=new Array(37);                 
    // function document.onkeydown() ////////提示错误 ！！ 暂改如下格式！！！！！！！！！！！！！！ ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
   function onkeydown1(){  
      { if (getAttribute("Author")==null && tagName != "INPUT")  
       document.all.MMDateLayer.style.display="none";  
      }  
    }  
    function MMWriteHead(yy,mm)    
      {   
        window.frames.MMDateLayer.document.all.MMYearHead.innerText  = yy ;  
        window.frames.MMDateLayer.document.all.MMMonthHead.innerText = mm;  
      }  
        
    function MMSelectYearInnerHTML(strYear) //年份的下拉框   
    {   
      
      if (strYear.match(/D/)!=null){alert("年份输入参数不是数字！");return;}   
      var m = (strYear) ? strYear : new Date().getFullYear();   
      if (m < 1000 || m > 9999) {alert("年份值不在 1000 到 9999 之间！");return;}   
      var n = m - 10;   
      if (n < 1000) n = 1000;   
      if (n + 26 > 9999) n = 9974;   
      var s = "<select Author=MM name=tmpSelectYear style='font-size: 12px' "   
      s += "onblur='document.all.tmpMMSelectYearLayer.style.display=\"none\"' "   
      s += "onchange='document.all.tmpMMSelectYearLayer.style.display=\"none\";"   
      s += "parent.MMTheYear = this.value; parent.MMSetDay(parent.MMTheYear,parent.MMTheMonth)'> ";   
      var selectInnerHTML = s;   
      for (var i = n; i < n + 26; i++)   
      {   
        if (i == m)   
        {selectInnerHTML += "<option Author=MM value='" + i + "' selected>" + i + "年" + "</option> ";}   
        else {selectInnerHTML += "<option Author=MM value='" + i + "'>" + i + "年" + "</option> ";}   
      }   
      selectInnerHTML += "</select>";   
      MModatelayer.tmpMMSelectYearLayer.style.display="";   
      MModatelayer.tmpMMSelectYearLayer.innerHTML = selectInnerHTML;   
      MModatelayer.tmpSelectYear.focus();   
    }   
    //月份下拉框  
    function MMSelectMonthInnerHTML(strMonth)  
    {  
      if (strMonth.match(/D/)!=null){alert("月份输入参数不是数字！");return;}  
      var m = (strMonth) ? strMonth : new Date().getMonth() + 1;  
      var s = "<select Author=MM name=tmpMMSelectMonth style='font-size: 12px' "  
         s += "onblur='document.all.tmpMMSelectMonthLayer.style.display=\"none\"' "  
         s += "onchange='document.all.tmpMMSelectMonthLayer.style.display=\"none\";"  
         s += "parent.MMTheMonth = this.value; parent.MMSetDay(parent.MMTheYear,parent.MMTheMonth)'> ";  
      var selectInnerHTML = s;  
      for (var i = 1; i < 13; i++)  
      {  
        if (i == m)  
           {selectInnerHTML += "<option value='"+i+"' selected>"+i+"月"+"</option> ";}  
        else {selectInnerHTML += "<option value='"+i+"'>"+i+"月"+"</option> ";}  
      }  
      selectInnerHTML += "</select>";  
      MModatelayer.tmpMMSelectMonthLayer.style.display="";  
      MModatelayer.tmpMMSelectMonthLayer.innerHTML = selectInnerHTML;  
      MModatelayer.tmpMMSelectMonth.focus();  
    }  
      
    function MMcloseLayer()             
      {  
        document.all.MMDateLayer.style.display="none";   
      }  
   // function document.onkeydown() ////////提示错误 ！！ 暂改如下格式！！！！！！！！！！！！！！ ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
      document.onkeydown1()
      {  
    	
    	//ESC键
        if (window.event.keyCode==27)document.all.MMDateLayer.style.display="none";  
      }  
    function MMIspinyear(year)           
      {  
        if (0==year%4&&((year%100!=0)||(year%400==0))) return true;else return false;  
      }  
    function MMGetMonthCount(year,month)  
      {  
        var c=MMMonHead[month-1];if((month==2)&&MMIspinyear(year)) c++;return c;  
      }  
    function MMGetDOW(day,month,year)      
      {  
        var dt=new Date(year,month-1,day).getDay()/7; return dt;  
      }  
    function MMPrevY()   
      {  
        if(MMTheYear > 999 && MMTheYear <10000){MMTheYear--;}  
        else{alert("年份超出范围（1000-9999）！");}  
        MMSetDay(MMTheYear,MMTheMonth);  
      }  
    function MMNextY()   
      {  
        if(MMTheYear > 999 && MMTheYear <10000){MMTheYear++;}  
        else{alert("年份超出范围（1000-9999）！");}  
        MMSetDay(MMTheYear,MMTheMonth);  
      }  
    function MMToday()   
      {  
        MMTheYear = new Date().getFullYear();  
        MMTheMonth = new Date().getMonth()+1;  
        MMSetDay(MMTheYear,MMTheMonth);  
      }  
    function MMPrevM()   
      {  
        if(MMTheMonth>1){MMTheMonth--}else{MMTheYear--;MMTheMonth=12;}  
        MMSetDay(MMTheYear,MMTheMonth);  
      }  
    function MMNextM()   
      {  
        if(MMTheMonth==12){MMTheYear++;MMTheMonth=1}else{MMTheMonth++}  
        MMSetDay(MMTheYear,MMTheMonth);  
      }  
    function MMSetDay(yy,mm)  
    {  
      MMWriteHead(yy,mm);  
      for (var i = 0; i < 37; i++){MMWDay[i]=""};  
      var day1 = 1,firstday = new Date(yy,mm-1,1).getDay();   
      for (var i = firstday; day1 < MMGetMonthCount(yy,mm)+1; i++){MMWDay[i]=day1;day1++;}  
    }  
    function MMDayClick(n)   
    {  
      var yy = MMTheYear;  
      var mm = MMTheMonth;  
      if (mm < 10){mm = "0" + mm;}  
      if (MMoutObject)  
      {  
        if (n =='清空') {MMoutObject.value=""; MMcloseLayer();return;}  
        MMoutObject.value= yy + "-" + mm ;  
        MMcloseLayer();   
      }  
      else {MMcloseLayer(); alert("您所要输出的控件对象并不存在！");}  
    }  
    MMSetDay(MMTheYear,MMTheMonth);  