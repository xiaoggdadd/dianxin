<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>静态数据管理</title>
     <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
     <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
      <script type="text/javascript" src="<%=path%>/web/javascript/popup_layer.js"></script>
      <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
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
     <script type="text/javascript" language="JavaScript" charset="utf-8">
    var CurPage=1;                          //当前页
    var TotalPage=1;                        //总页数
    var PageRow=15;
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
     function getDate(max,min){
      var type = document.getElementById("dataType");
      var index = type.selectedIndex;   
      var typeVal = type.options[index].value; 
      var params ="&key=value";
      params="&maxnum="+max+"&minnum="+min;
          var Url=getPublicPath()+"/servlet/GetListServlet?type="+typeVal;
	      new Ajax.Request(Url,{
      			method : 'post',
			asynchronous: false,
			parameters : params,
			onComplete : function (resp){
 				var result=resp.responseText;
 				var oTable = document.getElementById("dict");//得到表格
 				var rowDate = result.split(",");//有多少行
 				var len = oTable.rows.length
 				for(var k=1;k<len;k++){
 				   oTable.deleteRow(1);
 				}
 				var maxRow = 1;
	 		    var curRow = 1;
 				for(var i=0;i<rowDate.length;i++){
 					var trNode = oTable.insertRow();//插入一行
 					if(i%2==0){
 						trNode.style.backgroundColor="#DDDDDD";
 					}else{
 					    trNode.style.backgroundColor="#FFFFFF";
 					}
				   var tdNode1=trNode.insertCell();//插入一列
 			       var dirNode1 = document.createElement("div");//创建一个层
 			       dirNode1.setAttribute("align","center");//层居中 
 			       var str =parseInt(i)+1;
 			       dirNode1.innerHTML=str;
 			       tdNode1.appendChild(dirNode1);//将层节点加入列
 			       trNode.appendChild(tdNode1);
 			       
 					var cellDate = rowDate[i].split("|");//有多少列
 					
 					maxRow = cellDate[3];
 					curRow = cellDate[4];
 					for(var j=0;j<3;j++){
 						var tdNode=trNode.insertCell(j);//插入一列
 						var dirNode = document.createElement("div");//创建一个层
 			    		dirNode.setAttribute("align","center");//层居中
 			    		var divText="";
 			    		if ("null"==cellDate[j]){
                           divText=document.createTextNode("");//创建文本节点
 			    		}else{
 			    		  divText=document.createTextNode(cellDate[j]);//创建文本节点
 			    		}
 						
 			    		dirNode.appendChild(divText);//层中加入文本
 			    		tdNode.appendChild(dirNode);//将层节点加入列
 			    		trNode.appendChild(tdNode);
 			    	}
 			    	 var tdNode=trNode.insertCell();//插入一列
	 			     var dirNode = document.createElement("div");//创建一个层
	 			     dirNode.setAttribute("align","center");//层居中 
	 			     var url="'"+"motify.jsp?id="+cellDate[0]+"&dateType="+typeVal+"'";
                     var str ="<a href=\"javascript:open1(" + url + ")\">修改</a>"; 
	 			     dirNode.innerHTML=str;
	 			     tdNode.appendChild(dirNode);//将层节点加入列
	 			     trNode.appendChild(tdNode);
	 			     var tdNode=trNode.insertCell();//插入一列
	 			     var dirNode = document.createElement("div");//创建一个层
	 			     dirNode.setAttribute("align","center");//层居中
	 			     var str ="<a href=\"javascript:del('" + cellDate[0] + "')\">删除</a>";
	 			     dirNode.innerHTML=str;
	 			     tdNode.appendChild(dirNode);//将层节点加入列
	 			     trNode.appendChild(tdNode);
 			    }
 			    //
 			    	if ((maxRow % PageRow)>0){
	 				 TotalPage=Math.floor(maxRow / PageRow) + 1
	 				 }else{
	 				   TotalPage=Math.floor(maxRow / PageRow)
	 				 }
                    if ((curRow % PageRow)>0){
                       CurPage = Math.floor(curRow / PageRow)+1
                    }else{
                      CurPage = Math.floor(curRow / PageRow)
                    }
	 				 
	 				 if (CurPage==0){
	 				   CurPage=1
	 				 }
	 				 TurnTab();
	 	  }
    })
   }
   
   //分页
function TurnTab(){
    var tabinfo = document.getElementById("div_trackpagetab"); 
    var strInner = "";
    strInner += "<font color='#000080'>页次:</font>&nbsp;&nbsp;&nbsp;&nbsp;<font color=red>" +CurPage+"</font>&nbsp;/&nbsp;"+TotalPage + "&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;";
    if (CurPage!=1){
      strInner += "<a href=\"javascript:TurnPage("+""+")\">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
      strInner += "<a href=\"javascript:PreviousPage(" + "" + ")\">上页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
    }else{
       strInner += "<font color='#000080'>首页</font>&nbsp;&nbsp;&nbsp;&nbsp;";
       strInner += "<font color='#000080'>上页</font>&nbsp;&nbsp;&nbsp;&nbsp;";
    }
    
    if(CurPage < TotalPage ){
        strInner += "<a href=\"javascript:NextPage("+""+")\">下页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        strInner += "<a href=\"javascript:TurnPage1(" + "" + ")\">尾页</a>";
    }else if (CurPage == TotalPage){
        strInner += "<font color='#000080'>下页 </font>&nbsp;&nbsp;&nbsp;&nbsp;";
       strInner += "<font color='#000080'>尾页 </font>";
    }
	strInner += "<select name='page' id ='page' onChange='javascript:gopagebyno("+""+")' class='form' >"
	
	for(var i=1;i<TotalPage+1;i++){
	  if (i==CurPage){
	    strInner += "<option value='"+i+"' selected='selected'>"+i+"</option>";
	  }else{
	  	    strInner += "<option value="+i+">"+i+"</option>";
	  }
	}
	strInner += "</select>";
    tabinfo.innerHTML = strInner;
    
}
function gopagebyno(){
  var page = document.getElementById("page");
	var index = page.selectedIndex;   
	var pageVal = page.options[index].value; 
	
  //下拉框变化时，页面初始化
  var MaxRow = (pageVal)*PageRow
  var MinRow=(pageVal-1)*PageRow+1
  getDate(MaxRow,MinRow);
}
function TurnPage(){
     //点击首页初始化
     getDate(15,1);
}
function TurnPage1(){
     //点击尾页初始化
     var MaxRow = (TotalPage)*PageRow
    var MinRow=(TotalPage-1)*PageRow+1
    getDate(MaxRow,MinRow);
}
function PreviousPage(){
  //点击上页初始化
  if (CurPage-1==0){
    alert("当前是第一页");
  }else{
    //最大rownum
    var MaxRow = (CurPage-1)*PageRow
    //最小rownum
    var MinRow = (CurPage-2)*PageRow+1
    getDate(MaxRow,MinRow);
  }
}

function NextPage(){
//点击下页初始化
 if (CurPage+1>TotalPage){
   alert("当前是最后一页");
 }else{
    //最大rownum
    var MaxRow = (CurPage+1)*PageRow
    //最小rownum
    var MinRow = (CurPage)*PageRow+1
    getDate(MaxRow,MinRow);
 }
}
   
   
   function del(id){
      var type = document.getElementById("dataType");
      var index = type.selectedIndex;   
      var typeVal = type.options[index].value; 
     if(confirm("确定删除")){
       var Url="servlet/delDateServlet?id="+id+"&type="+typeVal;
	      new Ajax.Request(Url,{
      			method : 'post',
			asynchronous: false,
			onComplete : function (resp){
 				var result=resp.responseText;
 		    }
     })
      getDictList();
     }
   }
   
  function add(){
      var type = document.getElementById("dataType");
      var index = type.selectedIndex;   
      var typeVal = type.options[index].value; 
      
     	window.open("add.jsp?typeVal="+typeVal, 'newwindow', 'height=280, width=500,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
  }
  function addDataType(){
     	window.open("addType.jsp", 'newwindow', 'height=250, width=500,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
  } 
   
   //页面载入完成初始化下拉列表
   function waitThenDoIt(){
	    if (window.document.readyState){//IE
		      if (window.document.readyState=='complete'){ 
		      
		      //初始化下拉列表
		        getDictList();
		      }else{
		        setTimeout("waitThenDoIt()",10);
		      }
	   	 }else {//Firefox
	        window.addEventListener("load",function(){getDictList();},false);
	    }  
	}
	
   function open1(url){
      	 	window.open(url, 'newwindow', 'height=300, width=500,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
   }	
	
	//得到数据字典下拉列表数据
    function getDictList(){
        var Url=getPublicPath()+"/servlet/StaticDataServlet";
	    new Ajax.Request(Url,{
	      		method : 'post',
				asynchronous: false,
				onComplete : function (resp){//回调
	 			 var result=resp.responseText;//返回数据
	 			 var dataType = document.getElementById("dataType");
	 			 RemoveItems(dataType);
	 			 var opt = result.split(",");
	 			 totalPage=opt.length;
	 			 for (i=0;i<opt.length;i++ ){
	 			     var code = opt[i].split("|");
	 			 	 var op =document.createElement("option");
	 			 	 op.innerHTML = code[0];
	 			 	 op.value = code[1];  
	 			 	 dataType.appendChild(op); 
	 			 }
	 			   getDate(15,1);
	 	        }
		 })
    }
function RemoveItems(obj){
	for(var i=obj.options.length;i>=1;i--){
	   obj.options.remove(i);
    }
}
$(function(){
		$("#add11").click(function(){
			add();
		});
		$("#add22").click(function(){
			addDataType();
		});
	});

   </script>

  </head>
  <body  class="body" style="overflow-x:hidden;" onload="waitThenDoIt();">
  <LINK href="images/css.css" type=text/css rel=stylesheet>
    <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
    <td width="10" height="500">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan=3 width="600" background="<%=path%>/images/btt12.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;静态数据管理</span>
							</td>
							<td width="380">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
										<tr>
											<td height="49" bgcolor="#FFFFFF">										  		
												 <table width="100%" border="0" cellspacing="1" cellpadding="1">
										            <tr bgcolor="F9F9F9">
								                      <td height="19" colspan="4">　</td>
								                    </tr>
												  	<tr>
												        <td colspan="4">
												            &nbsp;&nbsp;数据类型： <select name="dataType" id="dataType" style="width:130" onChange="getDate(15,1);">
												            </select>
												        </td>
												    </tr>
												   
												 </table><br>
									         </td>
								  		</tr>
										<tr>
											<td height="49" bgcolor="#FFFFFF">	
												   	 <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" id ="dict">
											            <tr>
											             <td width="5%" height="23" bgcolor="#888888"><div align="center" class="bttcn">序号</div>
											             </td>
											             <td height="23" bgcolor="#888888"><div align="center" class="bttcn">CODE</div>
											             </td>
											             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">名称</div>
											             </td>
											             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">描述</div>
											             </td>
											             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">修改</div>
											             </td>
											             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">删除</div>
											             </td>
											            </tr>
											            
												    </table>
											 </td>
										 </tr>
										 
										 <tr  bgcolor="#ffffff">
                                             <td colspan="6">
                                               <div id="div_trackpagetab" align="center"><font color='#000080'></font></div>
                                             </td>
		                                </tr>
		                                 <tr>
												       <td colspan="4">
												       <div align="right">
												       		<div id="add22" style="position:relative;width:67px;height:23px;cursor: pointer;float:right;right:10px">
							                            	<img alt="" src="<%=request.getContextPath() %>/images/fenlei.png" width="100%" height="100%" />
							                          	    <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">大分类</span>
													  </div>
												       <div align="right">
												       		<div id="add11" style="position:relative;width:67px;height:23px;cursor: pointer;float:right;right:15px">
							                            	<img alt="" src="<%=request.getContextPath() %>/images/fenlei.png" width="100%" height="100%" />
							                          	    <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">小分类</span>
													  </div>
												     
												    </td>
												    </tr>
									</table>
							    </td>
							</tr>
						</table>
					</td>
				</tr>
		 </table>
		</td>
		</tr>
		</table>
  </body>
</html>
