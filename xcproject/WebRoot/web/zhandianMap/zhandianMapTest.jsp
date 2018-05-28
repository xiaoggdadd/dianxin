﻿﻿﻿<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    String path = request.getContextPath();
%>
<script>
var publicPath = getPublicPath();
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
</script>
<script type="text/javascript" src="../javascript/prototype.js"></script>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="http://code.google.com/apis/maps/documentation/javascript/examples/default.css" rel="stylesheet" type="text/css" />  
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>  
<script type="text/javascript" src="../zhandianMap/jquery-1.6.2.min.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<SCRIPT type="text/javascript" src="../zhandianMap/demo.js"></SCRIPT>
<link type="text/css" href="../zhandianMap/default.css" rel="stylesheet" />

<HTML>
<HEAD>
<TITLE>地图</TITLE>
<style type="text/css">
			HTML,BODY {
				font-size: 9pt;
				font-family: 宋体;
				margin: 0px;
				height: 100%;
				width: 100%;
				overflow: hidden;
			}
			
			#div_left {
				position: absolute;
				height: 100%;
				background-color :#ffffff;
				z-index:1000;
				width: 100px;
				top: 50px;
				left: 0px;
				z-index: 1;
			}
			
			#div_main {
				position: relative;
				height: 100%;
				width: 0  px;
				z-index:1000;
				margin-left: 200px;
				margin-right: 200px;
				background: #ccc;
				overflow: auto;
				cursor: pointer;
			}
			
			#div_right {
			    background-color :#555555;
			   
				position: absolute;
				width:400px;
				height: 100%;
				width: 200px;
				right: 0px;
				top: 80px;
			}
			#map_canvas1{ 
				position:relative; z-index:10000;width:1170px;padding-right:0px; top:0px; right:0px;
			}
            
            .style1 {
	color: #FF9900;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
.selected_font{
		width:90px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
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
.bttcn{ color:white;font-weight:bold;}
		</style>


<script language="javascript">
	var lx;
	var ly;
	function selectAreaData(type){
	var Url = getPublicPath()+"/servlet/map_zhanmap?action=ajaxareaData";
	var params ="&key=value";
	if (type==1){
	  params ="&zhanDian=137";
	}else if (type==2){
	  params ="&zhanDian="+ document.getElementById("shi").value;
	}else if (type==3){
	  params ="&zhanDian="+ document.getElementById("xian").value;
	}
	var obj = "" ;
	if(params!="&zhanDian="){
      new Ajax.Request(Url,{
      			method : 'post',
			asynchronous: false,
			parameters : params,
			onComplete : function (resp){
	 			var result=resp.responseText;
	 			//alert(result);
	            var retObject =result.split(",");
				if (retObject!=null){
					for(var i=0;i<retObject.length;i++){
					       
					 	 var items =retObject[i].split("|");
					 	 if (type==1){
				      	   obj=document.getElementById('shi');
				      	   if(i==0){
				      	    	RemoveItems(obj);
				      	   }
				      	 }else if (type==2){
				      	   obj=document.getElementById('xian');
				      	   if(i==0){
				      	   	RemoveItems(obj);
				      	   }
				      	 }else if (type==3){
				      	    obj=document.getElementById('xiaoqu');
				      	   if(i==0){
				      	   	RemoveItems(obj);
				      	   }
				      	 }
					     obj.options.add(new Option(items[1],items[0])); //这个兼容IE与firefox  
		
				    }
				}
			 }
			}
      );
	}else{  
		if (type==1){
		     obj=document.getElementById('shi');
		     RemoveItems(obj);
		 }else if (type==2){
		     obj=document.getElementById('xian');
		     RemoveItems(obj);
		  } else if (type==3){
		     obj=document.getElementById('xiaoqu');
		     RemoveItems(obj);
		  }
	}
	}
	
	function RemoveItems(obj){
		for(var i=obj.options.length;i>=1;i--){
		   obj.options.remove(i);
	    }
	}
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

                var Map={
				init:function(){
					var width=document.body.clientWidth+181;
					var mainWidth=document.getElementById("div_main").clientWidth;
					var div_left=document.getElementById("div_left");
					var div_main=document.getElementById("div_main");
					var div_right=document.getElementById("div_right");
					var contentWidth=(width-mainWidth)/2;
					div_main.style.marginLeft=contentWidth*0.60;
					div_left.style.width=contentWidth*0.33;
					div_right.style.width=width;
					Map.initEnd(contentWidth);
					selectAreaData(1);
				},
				initEnd:function(width){
					//alert("width==>"+width);
				},
				exchangeEnd:function(width){
					//alert("width  update to:"+width);
				}, //arg[0]为地图宽度
				exchange:function(){
					var width=document.body.clientWidth;
					var mainWidth=document.getElementById("div_main").clientWidth;
					var div_left=document.getElementById("div_left");
					var div_main=document.getElementById("div_main");
					var div_right=document.getElementById("div_right");
					var contentWidth=(width-mainWidth)/2;
					var marginLeft=div_main.style.marginLeft;
					//alert("mainWidth==>"+mainWidth)
					
					if(marginLeft+""!="0px"){
						div_left.style.display="none";
						div_main.style.marginLeft=0;
						var rightWidth=width-mainWidth;
						div_right.style.width=rightWidth;
						div_main.innerHTML="&gt;";
						if(Map.exchangeEnd!=null){
						    //map_canvas.style=map_canvas1;
							Map.exchangeEnd(rightWidth);
							//Map.init();
							
						}
					}else{
						var contentWidth1=width-mainWidth;
						div_main.style.marginLeft=contentWidth;
						div_left.style.width=contentWidth;
						div_right.style.width=contentWidth;
						div_left.style.display="";
						div_main.innerHTML="&lt;";
						if(Map.exchangeEnd!=null){
						
							Map.exchangeEnd(contentWidth1);
						}
					}
				}
			}
			
function liulan(){
	
   var shi = document.getElementById("shi").value;
   var xian = document.getElementById("xian").value;
   var xiaoqu = document.getElementById("xiaoqu").value;
   var zdmc = document.getElementById("zdmc").value;
   //var zhandiantype = document.getElementById("zhandiantype").value;
   //var biaogan = document.getElementById("biaogan").value;
  // var fangwuleixing = document.getElementById("fangwuleixing").value;
   var Url = getPublicPath()+"/servlet/map_zhanmap?action=sel";
   var params ="&key=value";
   params ="&shi="+ shi;
   params =params+"&xian="+ xian;
   params =params+ "&xiaoqu="+ xiaoqu;
   params =params+ "&zdmc="+ zdmc;
   //params =params+ "&biaogan="+ biaogan;
   //params =params+ "&fangwuleixing="+ fangwuleixing;

   			    new Ajax.Request(Url,{
			      		method : 'post',
						asynchronous: false,
						parameters : params,
						onComplete : function (resp){//回调
			 			  var result=resp.responseText;//返回数据
			 			 
			 			  var retObject = result.split(",");
			 			  
		                  var oTable = document.getElementById("dict");//得到表格
		                  var len = oTable.rows.length;
						  for(var k=1;k<len;k++){
						    oTable.deleteRow(1);
						  }
						  var lx;
						  var ly;
						  var zoom;
                         if(shi==""){
                           zoom=7;
                         }else if (shi!="" && xian ==""){
                           zoom=10;
                         } else if (xian!="" && xiaoqu ==""){
                           zoom=13;
                         }else if (xiaoqu !=""){
                           zoom=15;
                         }
						  var Url1 = getPublicPath()+"/servlet/map_zhanmap?action=sellocality";
						  new Ajax.Request(Url1,{
				      		method : 'post',
							asynchronous: false,
							parameters : params,
							onComplete : function (resp){//回调
							   var result1=resp.responseText;//返回数据
							   //alert(result1);
							   var items1 = result1.split("|");
							    
							    lx = items1[0];
						        ly = items1[1];
						         
							}
							})
						 var x;
						  var y;
						  for(var i=0;i<retObject.length;i++){
						  	 if(i==0){
								  buildMap(lx,ly,zoom);
								}
                              var items =retObject[i].split("|");
                              
								var trNode = oTable.insertRow();//插入一行
								if(i%2==0){
									trNode.style.backgroundColor="#DDDDDD";
								}else{
								    trNode.style.backgroundColor="#FFFFFF";
								}
			 					var tdNode=trNode.insertCell();//插入一列
								var dirNode = document.createElement("div");//创建一个层
					    		dirNode.setAttribute("align","center");//层居中
								var divText=document.createTextNode();//创建文本节点
								dirNode.innerHTML=i+1;
		    		            tdNode.appendChild(dirNode);//将层节点加入列
		    		            								
							     var tdNode=trNode.insertCell();//插入一列
				 			     var dirNode = document.createElement("div");//创建一个层
				 			     dirNode.setAttribute("align","center");//层居中 
				 			     // 
				 			     var url="'"+i+"'";
                                 var str ="<a href=\"javascript:openData1(" + url + ")\">"+items[3]+"</a>";  
				 			     dirNode.innerHTML=str;
				 			     tdNode.appendChild(dirNode);//将层节点加入列
				 			     trNode.appendChild(tdNode);
				 			     
								   x = items[0];
		                           y = items[1];
								var text = items[3];
					    		var tdNode=trNode.insertCell();//插入一列
								var dirNode = document.createElement("div");//创建一个层
					    		dirNode.setAttribute("align","center");//层居中
								var divText=document.createTextNode();//创建文本节点
								dirNode.innerHTML=items[4];
					    		tdNode.appendChild(dirNode);//将层节点加入列
					    		tdNode.appendChild(dirNode);//将层节点加入列
					    		var tdNode=trNode.insertCell();//插入一列
								var dirNode = document.createElement("div");//创建一个层
					    		dirNode.setAttribute("align","center");//层居中
								var divText=document.createTextNode();//创建文本节点
								dirNode.innerHTML=items[5];
					    		tdNode.appendChild(dirNode);//将层节点加入列


							     var tdNode=trNode.insertCell();//插入一列
				 			     var dirNode = document.createElement("div");//创建一个层
				 			     dirNode.setAttribute("align","center");//层居中 
				 			      var intnum=0;
				 			     var url="'"+"web/zhandianMap/Mapmodifysite.jsp?id="+items[6]+"&nums=1'";
                                 var str ="<a href=\"javascript:openData(" + url + ")\">"+"详细"+"</a>";  
				 			     dirNode.innerHTML=str;
				 			     tdNode.appendChild(dirNode);//将层节点加入列
				 			     trNode.appendChild(tdNode);
						         var location = new google.maps.LatLng(x,y);  
						         
								 var marker = new google.maps.Marker({        
										position: location,         
										map: map,
										zIndex:1,
										icon:'../zhandianMap/red.png',
										title: text
								});
								
								
								
								var province = $("#shi").html();
								var infowindow = new google.maps.InfoWindow(      
							    {
							        content: marker.getTitle() + '的信息：<br>' + '站点编号：'+items[4]
							        +'<br>' + '地址：'+items[3]+'<br>'+ '经纬度：'+items[0]+ ','+items[1],                
							        size: new google.maps.Size(50,50)     
							    });
							    
							   markers[i] = marker;
							   infowindows[i] = infowindow;
							   
			 	          }
			 	          //trNode.onClick = displayPoint(marker,i);
					      //清除list内容
					       $("#list").html("");
					       $("#list").css({'display':''});
					       //构建list
						   $(markers).each(function(i,marker){
						   
						   $("<li />")
								.html(marker.getTitle())
								.click(function(){
								
									displayPoint(marker,i);
								})
								.bind('mouseenter mouseleave', function() {
					                $(this).toggleClass('lihover');
					            })
								.appendTo("#list");
								google.maps.event.addListener(marker, "click", function(){
								
								displayPoint(marker,i);	
							});

							});  
			 	        }
			 	})
}	

   function openData(url){
       window.location.href=getPublicPath()+"/"+url; 
   }	
   function openData1(url){
    for(var i=0;i<markers.length;i++){
     if (i==url){
        displayPoint(markers[i],i)
     }
    }
       
   }
$(function(){

	$("#query").click(function(){
		
		liulan();
	});
});	
</script>
</HEAD>
<BODY onload="Map.init()">
<form action="areaData" name="form1" method="POST">
<div >
 <table width="100%"  border="0" cellspacing="0" cellpadding="0" style="margin:-30px 0px 0px 0px">
			<tr>
				<td colspan="4" width="50" >
					<div style="width:700px;height:50px">
				 	 
						<span style="font-size:14px;font-weight:bold;position: absolute;left:18px;top:25px;color:black">站点地图</span>	
				    </div>
				</td>
			</tr>
			  
 </table><br>
</div>
<div id="div_left">
		<div>
		   <table cellpadding="0" cellspacing="10" >
 		      <tr><td>&nbsp;</td></tr>
                <tr><td height="20" colspan="6" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:100px;display:inline;"><hr></div>
                </div>
	    	   </td>
	    	</tr> 
            <tr class="form_label">
               <td>城市</td>
               <td><select id="shi" name="shi" onchange="selectAreaData(2)" class="selected_font">
                 <option value="">城市</option>
                  </select>
               </td>
               <td>区县</td>
               <td><select id="xian" name="xian" onchange="selectAreaData(3)" class="selected_font">
                  <option value="">区县</option>
                  </select>
               </td>
      
             <tr class="form_label">
                <td>乡镇</td> 
                <td> <select id="xiaoqu" name="xiaoqu" onchange="selectAreaData(4)" class="selected_font">
                  <option value="">乡镇</option>
                     </select>
                </td>
                 <td>名称</td><td><input type="text" id="zdmc" name="zdmc"  class="selected_font"/>
                 </td>
              </tr>

              <tr>
                 <td colspan="3" align="right" >
                    <div id="query" style="position:relative;width:60px;height:23px;cursor: pointer;right:-120px;float:right;top:0;">
		             <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
		                  <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
	                </div>
                </td>
              </tr>
              <tr><td height="5"  colspan="6">
                 <div id="parent" style="display:inline">
                 <div style="width:50px;display:inline;"><hr>
                 </div><font size="2">&nbsp;信息列表&nbsp;</font>
                 <div style="width:100px;display:inline;"><hr></div>
                 </div></td>
              </tr>
          </table>

   	<div id ="naergyContent" style="width:130%;overflow-x:auto;overflow-y:auto;border:1px;height:300px">
	   	 <table width="100%" height="300px" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label" id ="dict" >
            <tr >
             <td width="1%" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div> </td>
             <td width="1%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
             <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属区域</div></td>
             <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td>
             <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">详细</div></td>
	  </table>
	</div>
	
</div>
</div>

<div id="div_main"  >&lt;
</div>
<div id="div_right" style="width:100%;overflow-x:auto;border:0px solid #FFOOFF"> 
<DIV id="map_canvas" style="width:190%;overflow-x:auto;border:1px"></DIV>
<UL id="list" style="margin:0;padding:0;display:none; font-size:13px;"></UL>
<DIV id="bar"></DIV>
</div>
</form>
</BODY>

</HTML>
