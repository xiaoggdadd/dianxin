<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>省级能源同比分析</title>
     <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
     <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
      <script type="text/javascript" src="<%=path%>/web/javascript/popup_layer.js"></script>
     <script type="text/javascript" language="JavaScript" charset="utf-8">
     //弹出层
     $(document).ready(function() {
			new PopupLayer({trigger:"#ele1",popupBlk:"#blk1",closeBtn:"#close1",useOverlay:true,useFx:true});
			new PopupLayer({trigger:"#ele2",popupBlk:"#blk2",closeBtn:"#close2",useOverlay:true,useFx:true});
			new PopupLayer({trigger:"#ele3",popupBlk:"#blk3",closeBtn:"#close3",useOverlay:true,useFx:true});
		});
     
     //得到根路径
     function getPublicPath(){
		   var path = document.location.pathname;
			var arraypath=path.split("/");
			var mapath="/";
		 	if(arraypath[1]==undefined){
			 arraypath[1]="";
			 mapath="";
		 	}
		  path=mapath+arraypath[1];
		  return path;
	} 
	
	//得到表格数据   
     function getDate(){
          var city=document.getElementById("city").value;
          if(city==""){
             alert("请选择省份");
             return;
          }
          var year=document.getElementById("year").value;
          if(year==""){
             alert("请选择年");
             return;
          }
            var month=document.getElementById("month").value;
          if(month==""){
             alert("请选择月份");
             return;
          }

          var energy=document.getElementsByName("energy");
          var month=document.getElementsByName("month");
          var year=document.getElementsByName("year");
          var energys="";
          var months="";
          var years="";
          var len = energy.length;
          for(var i =0;i<len;i++){
  			if(energy[i].checked){
   				energys = energys +energy[i].value+",";
   			}
 		 }
 		 
 		 for(var i =0;i<month.length;i++){
   				if(month[i].checked){
    				months = months+month[i].value+",";
   				}
 		 }
 		 for(var i =0;i<year.length;i++){
   				if(year[i].checked){
    				years = years+year[i].value+",";
   				}
 		 }
 		 
          var params = "&city="+city+"&energy="+energys+"&month="+months+"&year="+years;
        
          var Url=getPublicPath()+"/ShenEnergyServerlet?action=ShenEnergyServerlet";
	      new Ajax.Request(Url,{
      			method : 'post',
			asynchronous: false,
			parameters : params,
			onComplete : function (resp){
				var city=document.getElementById("city").value;
        	  	var energy=document.getElementsByName("energy");
        	  	var energys="";
	         	for(var i =0;i<energy.length;i++){
	   				if(energy[i].checked){
	    				energys = energy[i].value;
	   				}
		 		}
 				var result=resp.responseText;
 				document.getElementById("naergyContent").innerHTML=result;
 				var oTable =  document.getElementById("energy");//得到表格
 				var oRow = oTable.rows;//得到行
 				var oCell = oTable.cells;
 				var oCellLen = oCell.length;
 				var cellTotal = 0;
 				var trNode = oTable.insertRow(oRow.length);//插入一行
 				trNode.setAttribute("bgcolor","F2F9FF");
 				var tdNode=trNode.insertCell(0);//插入一列
 				var dirNode = document.createElement("div");//创建一个层
 			    dirNode.setAttribute("align","center");//层居中
 				var divText=document.createTextNode(city);//创建文本节点
 			    dirNode.appendChild(divText);//层中加入文本
 			    tdNode.appendChild(dirNode);//将层节点加入列
 			    
 				var tdNode1=trNode.insertCell(1);
 				var dirNode1 = document.createElement("div");//创建一个层
 				dirNode1.setAttribute("align","center");
 				var divText1=document.createTextNode("总计");//创建文本节点
 				dirNode1.appendChild(divText1);//层中加入文本
 				tdNode1.appendChild(dirNode1);//将层节点加入列
 			    getChar();
 			    
 			    var eney = document.getElementById("eney")//得到层
 			    eney.style.display="";
 			     en = document.getElementById("eneys")//得到select
 			     en.innerHTML="";
 			    var che = document.getElementsByName("energy");
 			   	for(var k =0;k<che.length;k++){
	   				if(che[k].checked){
	   				    var op =document.createElement("option");
	   				    op.innerHTML = che[k].nextSibling.nodeValue;
	   				    op.value = che[k].value;
	    				en.appendChild(op);
	   				}
	   				
		 		}
		 		
		 		 
		 		
 			    
 			    //遍历列
				  for(var j=2;j<oCellLen;j++){
				  //遍历行
				    for(var i=1;i<oRow.length-1;i++){
				       cellTotal += parseInt(oRow[i].cells[j].innerText);
				    }
				     var tdNode=trNode.insertCell(j); 
				     var dirNode = document.createElement("div");//创建一个层
				     dirNode.setAttribute("align","center");
				     var divText=document.createTextNode(cellTotal);//创建文本节点
				     dirNode.appendChild(divText);//层中加入文本
				     tdNode.appendChild(dirNode);//将层节点加入列
				     cellTotal=0;
				};
 				
 	        }
	 	  })
   }
   
   
   //得到曲线图形数据
   function getChar(energy){
          var city=document.getElementById("city").value;
          if(city==""){
             alert("请选择城市");
             return;
          }
         // var energy=document.getElementsByName("energy");
          var month=document.getElementsByName("month");
          var year=document.getElementsByName("year");
          var energys="";
          var months="";
          var years="";
        /*  var len = energy.length;
          for(var i =0;i<len;i++){
  			if(energy[i].checked){
   				energys = energys +energy[i].value+",";
   			}
 		 }*/
 		 
 		 if(!energy){
 		     var energy=document.getElementsByName("energy");
 		     var len = energy.length;
         	 for(var i =0;i<len;i++){
  			 if(energy[i].checked){
   				energys = energy[i].value;
   			//	return;
   			}
 		 }
 		 }else {
 		     energys = energy;
 		 }
 	
 		 
 		 for(var i =0;i<month.length;i++){
   				if(month[i].checked){
    				months = months+month[i].value+",";
   				}
 		 }
 		 for(var i =0;i<year.length;i++){
   				if(year[i].checked){
    				years = years+year[i].value+",";
   				}
 		 }
 		 
         var params = "&city="+city+"&energy="+energys+"&month="+months+"&year="+years;
         
          var Url=getPublicPath()+"/ShenEnergyCharServlet?action=ShenEnergyCharServlet"+params;
          Url = encodeURI(Url);
		  document.getElementById("char").innerHTML= "<img src='"+Url+"' width=1024 height=400 border=0 />";
   }
   
   function enChange(){
      var en = document.getElementById("eneys").value;
      getChar(en);
   }

   //页面载入完成初始化下拉列表
   function waitThenDoIt(){
	    if (window.document.readyState){//IE
		      if (window.document.readyState=='complete'){ 
		      //初始化下拉列表
		        getCity();
		      }else{
		        setTimeout("waitThenDoIt()",10);
		      }
	   	 }else {//Firefox
	        window.addEventListener("load",function(){getCity();},false);
	    }  
	}
	
   function open1(){
      	window.showModalDialog("pop.html", 'newwindow', 'height=400, width=400, top=0, left=0, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=no');
   }	
	
	//得到下拉列表数据
    function getCity(){
        var Url=getPublicPath()+"/ShenGetCityServlet?action=ShenGetCityServlet";
	    new Ajax.Request(Url,{
	      		method : 'post',
				asynchronous: false,
				onComplete : function (resp){
	 			 var result=resp.responseText;
	 			 var city = document.getElementById("city");
	 			 var opt = result.split(",");
	 			 totalPage=opt.length;
	 			 for (i=0;i<opt.length;i++ ){
	 			 	 var op =document.createElement("option");
	 			 	 op.innerHTML = opt[i];
	 			 	 op.value = opt[i];  
	 			 	 city.appendChild(op); 
	 			 } 
	 			   getYear();
	 	        }
		 })
    }
    
    function getYear(){
       var Url=getPublicPath()+"/ShenGetYearServlet?action=ShenGetYearServlet";
	    new Ajax.Request(Url,{
	      		method : 'post',
				asynchronous: false,
				onComplete : function (resp){
				   var result=resp.responseText;
				   var opt = result.split(",");
				   var ener = document.getElementById("ener");
				    for (i=0;i<opt.length;i++ ){
				       var op =document.createElement("li");
				       op.innerHTML ="<input type ='checkbox' name='year' value='"+ opt[i]+"' />"+opt[i]+"年";
				       ener.appendChild(op);
				    }
				}
		})
    }
    //页面载入方法
 
   </script>
     
     <style>
            .btn {
             BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7b9ebd 1px solid
            }
            .btn1_mouseout {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#B3D997); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn1_mouseover {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#CAE4B6); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn2 {padding: 2 4 0
            4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;}
            .btn3_mouseout {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mouseover {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mousedown
            {
             BORDER-RIGHT: #FFE400 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #FFE400 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #FFE400
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #FFE400 1px solid
            }
            .btn3_mouseup {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn_2k3 {
             BORDER-RIGHT: #002D96 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #002D96 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); BORDER-LEFT: #002D96
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #002D96 1px solid
            }
            .style1 {
	color: #FF9900;
	font-weight: bold;
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}

 </style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<LINK href="web/images/css.css" type=text/css rel=stylesheet>
	<link href="web/images/core.css" type="text/css" rel="stylesheet"/>
  </head>
  <body  class="body" style="overflow-x:hidden;" onload="waitThenDoIt();">
  <div id="emample1" class="example">
  <div id="blk1" class="blk" style="display:none;">
            <div class="main">
                <h2>请选择年</h2>
                <a href="javascript:void(0)" id="close1" class="closeBtn">保存</a>
                <ul id="ener">
                </ul>
            </div>
        </div>
        <div id="blk2" class="blk" style="display:none;">
            <div class="head"><div class="head-right"></div></div>
            <div class="main">
                <h2>请选择月</h2>
                <a href="javascript:void(0)" id="close2" class="closeBtn">保存</a>
                <ul>
                    <li><input type="checkbox" name="month"  value="1" />一月</li>
                    <li><input type="checkbox" name="month"  value="2" />二月</li>
                    <li><input type="checkbox" name="month"  value="3" />三月</li>
                    <li><input type="checkbox" name="month"  value="4" />四月</li>
                    <li><input type="checkbox" name="month"  value="5" />五月</li>
                    <li><input type="checkbox" name="month"  value="6" />六月</li>
                    <li><input type="checkbox" name="month"  value="7" />七月</li>
                    <li><input type="checkbox" name="month"  value="8" />八月</li>
                    <li><input type="checkbox" name="month"  value="9" />九月</li>
                    <li><input type="checkbox" name="month"  value="10" />十月</li>
                    <li><input type="checkbox" name="month"  value="11" />十一月</li>
                    <li><input type="checkbox" name="month"  value="12" />十二月</li>
                </ul>
            </div>
        </div>
        
           <div id="blk3" class="blk" style="display:none;">
           <div class="head"><div class="head-right"></div></div>
           <div class="main">
               <a href="javascript:void(0)" id="close3" class="closeBtn">保存</a>
               <ul>
                   <li><input type="checkbox" name="energy"  value="con_water" />水消耗量</li>
                   <li><input type="checkbox" name="energy"  value="con_power"/>耗电量</li>
                   <li><input type="checkbox" name="energy"  value="con_gaoline"/> 汽油消耗量</li>
                   <li><input type="checkbox" name="energy"  value="con_diese"/> 柴油消耗量</li>
                   <li><input type="checkbox" name="energy"  value="con_naturalgas"/> 天然气消耗量</li>
                   <li><input type="checkbox" name="energy"  value="sum" checked/> 总能耗</li>
               </ul>
           </div>
       </div>
        
        
        </div>
    <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
    <td width="10" height="500" background="images/img_10.gif">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td width="6" background="images/a_22.gif"><img src="images/a_21.gif" width="44" height="32" /></td>
							<td width="169" background="images/a_22.gif"><span class="style1">同比分析</span></td>
							<td width="185"><img src="images/a_23.gif" width="185" height="32" /></td>
							<td width="380">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="4"></td>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" bgcolor="7C92B7">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">
<tr bgcolor="F9F9F9">
                      <td height="19" colspan="4">　<img src="images/v.gif" width="8" height="9" /></td>
                    </tr>

  		</table>
  <table>
  	<tr>
        <td>省份：</td>
        <td>
            <select name="select" id="city">
              <option value="">--请选择--</option>
            </select>
        </td>
        <td>年：</td>
        <td>
            <input align="center" type ="text" value="" id="ele1" name="year" readOnly />
        </td>
        <td>月：</td>
        <td>
            <input align="center" type ="text" value="" id="ele2" name="month" readOnly />
        </td>
        <td>耗能：</td>
        <td>
            <input align="center" type ="text" value="" id="ele3" name="month" readOnly />
        </td>
      <td>
        <input type="button" value="执行查询" onClick="getDate();"/>
      </td>
    </tr>
	</table>
	<div id ="naergyContent"></div>
	<div style="display:none;" id ="eney">
	<table>
		<tr>
		<td>请选择能源：</td>
		<td>
		<select name="select" id="eneys" onchange="enChange();">
           <option value="">--请选择--</option>
        </select>
        </td>
        </tr>
     </table>
    </div>
	<div id ="char"></div>
      <table width="100%" border="1" cellspacing="0" cellpadding="0">
               <tr bgcolor="F9F9F9">
                <td height="28" colspan="2" ></td>
              </tr>        
              <tr bgcolor="F9F9F9">
                <td height="28" colspan="2" background="images/bg.jpg"><label> 　　<img src="images/ling.jpg" width="17" height="16" />　温馨提示</label></td>
              </tr>
              <tr>
                <td height="51" colspan="2"><div align="center"></div>
                    <div align="center"> </div>
                    <div align="center"> </div>
                    <div align="center" class="style7"></div>
                    <div align="center" class="style7"> </div>
                    <div align="center"></div>
                    <div align="center">
                      <table width="85%"  border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td></td>
                        </tr>
                      </table>
                  </div>
                 </td>
              </tr>
      </table>
  </body>
</html>
