<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
<html>
<head>
<title>
投资信息维护
</title>
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
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
<script >

var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();


var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init();
</script>
</head>
<script type="text/javascript" language="JavaScript" charset="utf-8">
 var CurPage=0;                          //当前页
 var TotalPage=0;                        //总页数
 var PageRow=15;
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
function waitThenDoIt(){
   if (window.document.readyState){//IE
      if (window.document.readyState=='complete'){ 
      //初始化页面
        getDictList(15,1);
      }else{
        setTimeout("waitThenDoIt()",10);
      }
  	 }else {//Firefox
       window.addEventListener("load",function(){getDictList(15,1);},false);
   } 

}
function getDictList(maxrow,minrow){
  	var Url = "";
	var params ="&key=value";
	 Url = getPublicPath()+"/servlet/CashinfoServlet?action=getSelData&roleFlg=PERMISSION_SEARCH";
	 params ="&xmmch="+ document.getElementById("xmmch").value+"&xmzrr="+ document.getElementById("xmzrr").value;
     params = params+"&maxnum="+maxrow+"&minnum="+minrow;
	      new Ajax.Request(Url,{
      			method : 'post',
			asynchronous: false,
			parameters : params,
			onComplete : function (resp){
				var result=resp.responseText;//返回数据
				var msg = result.split("|");
				if ("msg"==msg[0]){
				  alert(msg[1]);
				  return;
				}
	 				 var oTable = document.getElementById("dict");//得到表格
	 				 var rowDate = result.split(",");//有多少行
	 				 var len = oTable.rows.length;
	 				 for(var k=1;k<len;k++){
	 				   oTable.deleteRow(1);
	 				 }
	 				 var maxRow = "";
	 				 var curRow = "";
	 				 if (rowDate!=""){
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
			 					
			 						var tdNode2=trNode.insertCell(1);//插入一列
			 						var dirNode2 = document.createElement("div");//创建一个层
			 			    		dirNode2.setAttribute("align","center");//层居中
			 						var divText=document.createTextNode(cellDate[0]);//创建文本节点
			 			    		dirNode2.appendChild(divText);//层中加入文本
			 			    		tdNode2.appendChild(dirNode2);//将层节点加入列
			 			    		trNode.appendChild(tdNode2);
			 			    		
			 			    		var tdNode2=trNode.insertCell(2);//插入一列
			 						var dirNode2 = document.createElement("div");//创建一个层
			 			    		dirNode2.setAttribute("align","center");//层居中
			 						var divText=document.createTextNode(cellDate[1]);//创建文本节点
			 			    		dirNode2.appendChild(divText);//层中加入文本
			 			    		tdNode2.appendChild(dirNode2);//将层节点加入列
			 			    		trNode.appendChild(tdNode2);
			 			    		
			 			    		var tdNode2=trNode.insertCell(3);//插入一列
			 						var dirNode2 = document.createElement("div");//创建一个层
			 			    		dirNode2.setAttribute("align","center");//层居中
			 						var divText=document.createTextNode(cellDate[2]);//创建文本节点
			 			    		dirNode2.appendChild(divText);//层中加入文本
			 			    		tdNode2.appendChild(dirNode2);//将层节点加入列
			 			    		trNode.appendChild(tdNode2);

			 			    	maxRow = cellDate[13];
			 			    	curRow = cellDate[14]
			 			    	 var tdNode3=trNode.insertCell();//插入一列
				 			     var dirNode3 = document.createElement("div");//创建一个层
				 			     dirNode3.setAttribute("align","center");//层居中 
				 			     // 
				 			     var url="'"+"detail.jsp?xmmch="+cellDate[0]+"'";
                                 var str ="<a href=\"javascript:openData(" + url + ")\">详细</a>";  
				 			     dirNode3.innerHTML=str;
				 			     tdNode3.appendChild(dirNode3);//将层节点加入列
				 			     trNode.appendChild(tdNode3);
				 			     var tdNode3=trNode.insertCell();//插入一列
				 			     var dirNode3 = document.createElement("div");//创建一个层
				 			     dirNode3.setAttribute("align","center");//层居中 
				 			     var url="'"+"modfy.jsp?xmmch="+cellDate[0]+"'";
                                 var str ="<a href=\"javascript:open1(" + url + ")\">修改</a>";  
				 			     dirNode3.innerHTML=str;
				 			     tdNode3.appendChild(dirNode3);//将层节点加入列
				 			     trNode.appendChild(tdNode3);
				 			     
				 			     var tdNode4=trNode.insertCell();//插入一列
				 			     var dirNode4 = document.createElement("div");//创建一个层
				 			     dirNode4.setAttribute("align","center");//层居中
				 			     var str ="<a href=\"javascript:del('" + cellDate[0] + "')\">删除</a>";
				 			     dirNode4.innerHTML=str;
				 			     tdNode4.appendChild(dirNode4);//将层节点加入列
				 			     trNode.appendChild(tdNode4);
	 			           }
	 				 }
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
		}
      );
      
}
//增加数据
 function add(){
    window.location.href=getPublicPath()+"/"+"web/cashInfo/add.jsp";
    //window.open("add.jsp", 'newwindow', 'height=410, width=600,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
 } 
   function open1(url){
   window.location.href=getPublicPath()+"/"+"web/cashInfo/"+url;
      	//window.open(url, 'newwindow', 'height=410, width=600,top=0,left=300,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
   }
   
   function openData(url){
        window.location.href=getPublicPath()+"/"+"web/cashInfo/"+url;
      	//window.open(url, 'newwindow', 'height=410, width=600,top=0,left=300,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
   }
//
   function del(id){
     if(confirm("确定删除")){
       var params = "?&xmmch="+id;
       var Url=getPublicPath()+"/servlet/CashinfoServlet?action=del&roleFlg=PERMISSION_DELETE";
	    new Ajax.Request(Url,{
	      		method : 'post',
				asynchronous: false,
			    parameters : params,
				onComplete : function (resp){//回调
	 			  var result=resp.responseText;//返回数据
	 			  var msg = result.split("|");
					if ("msg"==msg[0]){
					  alert(msg[1]);
					  return;
					}
	 			}
	 	})
      window.getDate();
     }
   }
function getDate(){
   CurPage = 0
   getDictList(15,1);
   
}
//分页
function TurnTab(){
    var tabinfo = document.getElementById("div_trackpagetab"); 
    var strInner = "";
    strInner += "<font color='#000080'>页次:</font>&nbsp;&nbsp;&nbsp;&nbsp;<font color=red>" +CurPage+"</font>&nbsp;/&nbsp;"+TotalPage + "&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;";
    if (CurPage!=1){
      strInner += "<a href=\"javascript:TurnPage()\">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
      strInner += "<a href=\"javascript:PreviousPage()\">上页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
    }else{
       strInner += "<font color='#000080'>首页</font>&nbsp;&nbsp;&nbsp;&nbsp;";
       strInner += "<font color='#000080'>上页</font>&nbsp;&nbsp;&nbsp;&nbsp;";
    }
    
    if(CurPage < TotalPage ){
        strInner += "<a href=\"javascript:NextPage()\">下页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        strInner += "<a href=\"javascript:TurnPage1()\">尾页</a>";
    }else if (CurPage == TotalPage){
        strInner += "<font color='#000080'>下页 </font>&nbsp;&nbsp;&nbsp;&nbsp;";
       strInner += "<font color='#000080'>尾页 </font>";
    }
	strInner += "<select name='page' id ='page' onChange='javascript:gopagebyno()' class='form' >"
	
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
function gopagebyno(type){
	var page = document.getElementById("page");
	var index = page.selectedIndex;   
	var pageVal = page.options[index].value; 
  //下拉框变化时，页面初始化
  var MaxRow = (pageVal)*PageRow
  var MinRow=(pageVal-1)*PageRow+1
  getDictList(MaxRow,MinRow);
}
function TurnPage(type){
     //点击首页初始化
     getDictList(15,1);
}
function TurnPage1(type){
     //点击尾页初始化
     var MaxRow = (TotalPage)*PageRow
     var MinRow=(TotalPage-1)*PageRow+1
     getDictList(MaxRow,MinRow);
}
function PreviousPage(type){
  //点击上页初始化
  if (CurPage-1==0){
    alert("当前是第一页");
  }else{
    //最大rownum
    var MaxRow = (CurPage-1)*PageRow
    //最小rownum
    var MinRow = (CurPage-2)*PageRow+1
    getDictList(MaxRow,MinRow);
  }
}

function NextPage(type){
//点击下页初始化
 if (CurPage+1>TotalPage){
   alert("当前是最后一页");
 }else{
    //最大rownum
    var MaxRow = (CurPage+1)*PageRow
    //最小rownum
    var MinRow = (CurPage)*PageRow+1
    getDictList(MaxRow,MinRow);
 }
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
		$("#query").click(function(){
			getDictList(15,1);
		});
		})
</script>
<body  class="body" style="overflow-x:hidden;" onload="waitThenDoIt();">
	<LINK href="../../images/css.css" type=text/css rel=stylesheet>
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
		    <td width="10" height="500">&nbsp;</td>
		    <td valign="top">
				<table width="100%"  border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="3">
							<table width="100%"  border="0" cellspacing="0" cellpadding="0">
															
								<tr>
									<td colspan=3 width="600" background="<%=path%>/images/btt.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投资信息维护</span></td>
									
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
											<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" >
												<tr>
													<td height="49" bgcolor="#FFFFFF">
															<table width="100%" border="0" cellspacing="1" cellpadding="1">
														    <br/>
															  		<tr>
															         <td colspan=4>
															         项目名称:
															         <input type="text" id="xmmch" name="xmmch"  value="">
															          预算总投资:
															         <input type="text" id="xmzrr" name="xmzrr"  value="">
															         <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:55%;TOP:-23PX">
						                                                  <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
						                                                  <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
					                                                  </div>
															        </td>
															      </tr>
															     
													      </table>
													      
													     <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" id ="dict">
													         <tr>
													             <td width="5%" bgcolor="#888888"><div align="center" class="bttcn">ID</div></td>
													             <td height="23" bgcolor="#888888"><div align="center" class="bttcn">项目名称</div></td>
													             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">预算总投资</div></td>
													             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">实际总投资</div></td>
													             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">详细</div></td>
													             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">编辑</div></td>
													             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">删除</div></td>
													         </tr>
													  	 </table> 
													  	 
										            </td>
										           
								                 </tr>
								                 
                                                 <tr bgcolor="#ffffff">
                                                 <td>
                                                    <div id="div_trackpagetab" align="center"><font color='#000080'></font></div>
                                                 </td>
                                                 </tr>
							                 </table>
							                  
						                  </td>
					                  </tr>
				                   </table>
				                   <div id="add11" style="position: relative; width: 60px; height: 23px; cursor: pointer; float: right; right: 0px">
										 <img alt=""src="<%=request.getContextPath() %>/images/xinzeng.png" width="100%" height="100%" />
										 <span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">新增</span>
								   </div>
		                </td>
		             </tr>
		        </table>    
	       </td>
	       </tr>        
	</table>	
</body>
</html>

