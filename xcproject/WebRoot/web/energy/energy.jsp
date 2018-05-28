<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>同比分析</title>
     <script type="text/javascript" src="<%=path%>/javascript/prototype.js"></script>
     <script type="text/javascript" language="JavaScript" charset="utf-8">
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
             alert("请选择城市");
             return;
          }
          var energy=document.getElementsByName("energy");
          var energys="";
          for(var i =0;i<energy.length;i++){
   				if(energy[i].checked){
    				energys = energy[i].value;
   				}
 		 }
          var params = "&city="+city+"&energy="+energys;
          var Url=getPublicPath()+"/EnergyServerlet?action=EnergyServerlet";
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
 				getChar();
 	        }
	 	  })
   }
   
   
   //得到曲线图形数据
   function getChar(){
          var city=document.getElementById("city").value;
          var energy=document.getElementsByName("energy");
          var energys="";
          for(var i =0;i<energy.length;i++){
   				if(energy[i].checked){
    				energys = energy[i].value;
   				}
 		 }
          var params = "&city="+city+"&energy="+energys;
          var Url=getPublicPath()+"/EnergyCharServlet?action=EnergyCharServlet"+params;
          Url = encodeURI(Url);
	 	  document.getElementById("char").innerHTML= "<img src='"+Url+"' width=1024 height=400 border=0 />";
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
	
	//得到下拉列表数据
    function getCity(){
        var Url=getPublicPath()+"/GetCityServlet?action=GetCityServlet";
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
	<LINK href="<%=path%>/images/css.css" type=text/css rel=stylesheet>
  </head>
  <body  class="body" style="overflow-x:hidden;" onload="waitThenDoIt();">
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
							<td colspan=3 width="600" background="<%=path%>/images/btt.bmp" height=40><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;同比分析</span></td>
							
							<td width="380" align="right">
							
								</td>
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
        <td>城市：</td>
        <td>
            <select name="select" id="city">
              <option value="">--请选择--</option>
            </select>
        </td>
        <td>
            <input type="radio" name="energy"  value="con_water" checked/>水消耗量
            <input type="radio" name="energy"  value="con_power"/>耗电量
            <input type="radio" name="energy"  value="con_gaoline"/> 汽油消耗量
            <input type="radio" name="energy"  value="con_diese"/> 柴油消耗量
            <input type="radio" name="energy"  value="con_naturalgas"/> 天然气消耗量
            <input type="radio" name="energy"  value="sum"/> 总能耗
   	  </td>
      <td>
        <input type="button" value="执行查询" onClick="getDate();"/>
      </td>
    </tr>
	</table>
	<div id ="naergyContent"></div>
	<div id ="char"></div>
  </body>
</html>
