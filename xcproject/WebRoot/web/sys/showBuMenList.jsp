<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>选择部门</title>
    
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
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
<script src="../javascript/common.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>


  </head>
  <script type="text/javascript" language="JavaScript" charset="utf-8">
 var CurPage=0;                          //当前页
 var TotalPage=0;                        //总页数
 var PageRow=15;
 var path = '<%=path%>';
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
        getDictList(0,15,1);
      }else{
        setTimeout("waitThenDoIt()",10);
      }
  	 }else {//Firefox
       window.addEventListener("load",function(){getDictList(0,15,1);},false);
   } 

}
function getDictList(type,maxrow,minrow){
   
  	var Url = "";
	var params ="&key=value";
	if (type==1){
	 Url = getPublicPath()+"/servlet/DepartmentServlet?action=ajaxareaData";
	  params ="&fdeptcode="+ document.getElementById("gongsi").value;
	}else if (type==2){
	  Url = getPublicPath()+"/servlet/DepartmentServlet?action=ajaxareaData";
	  params ="&fdeptcode="+ document.getElementById("bumen").value;
	}else if (type==3){
		  Url = getPublicPath()+"/servlet/DepartmentServlet?action=ajaxareaData";
	      params ="&fdeptcode="+ document.getElementById("yibanzu").value;
	}else{

	  Url = getPublicPath()+"/servlet/DepartmentServlet?action=select";
	  params ="&fdeptcode=37";
	  	 
	}

     params = params+"&maxnum="+maxrow+"&minnum="+minrow;
	      new Ajax.Request(Url,{
      			method : 'post',
			asynchronous: false,
			parameters : params,
			onComplete : function (resp){
				var result=resp.responseText;//返回数据
				var msg = result.split("|");
				if ("msg"==msg[0]){
				  //alert(msg[1]);
				  return;
				}
				var dataType="";
				if (type==1){
				  dataType = document.getElementById("bumen");
				  RemoveItems(dataType);
				}else if (type==2){
				  dataType = document.getElementById("yibanzu");
				  RemoveItems(dataType);
				}
				else if (type==0){
				  dataType = document.getElementById("gongsi");
				  RemoveItems(dataType);
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
			 					for(var j=1;j<3;j++){
			 						var tdNode2=trNode.insertCell(j);//插入一列
			 						var dirNode2 = document.createElement("div");//创建一个层
			 			    		dirNode2.setAttribute("align","center");//层居中
			 						var divText=document.createTextNode(cellDate[j-1]);//创建文本节点
			 			    		dirNode2.appendChild(divText);//层中加入文本
			 			    		tdNode2.appendChild(dirNode2);//将层节点加入列
			 			    		trNode.appendChild(tdNode2);
			 			    	}
			 			    	maxRow = cellDate[4];
			 			    	curRow = cellDate[5]
			 		
				 			     
				 			     var tdNode4=trNode.insertCell();//插入一列
				 			     var dirNode4 = document.createElement("div");//创建一个层
				 			     dirNode4.setAttribute("align","center");//层居中
				 			     var id=oTable.rows(i+1).cells(0).firstChild.innerHTML;
				 			   //  var bumenid=oTable.rows(i+1).cells(1).firstChild.innerHTML;
				 			     //var bumen=oTable.rows(i+1).cells(2).firstChild.innerHTML;
				 			   //  var name=bumenid+":"+bumen;
				 			     var str ="<a href=\"javascript:selected('" + cellDate[0] + "','"+ cellDate[1] +"')\">选择</a>";
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
	 				 TurnTab(type);
	 				 getSelOpen(type);
			}
		}
      );
      
}
//下拉框检索
function getSelOpen(type){
  	var Url = getPublicPath()+"/servlet/DepartmentServlet?action=selOption";
	var params ="&key=value";
	if (type==1){
	  params ="&fdeptcode="+ document.getElementById("gongsi").value;
	}else if (type==2){
	  params ="&fdeptcode="+ document.getElementById("bumen").value;
	}else if (type==3){
	  params ="&fdeptcode="+ document.getElementById("yibanzu").value;
	}else{
	  params ="&fdeptcode=37";
	}
    new Ajax.Request(Url,{
    	method : 'post',
	asynchronous: false,
	parameters : params,
	onComplete : function (resp){
		var result=resp.responseText;//返回数据
	    var msg = result.split("|");
		if ("msg"==msg[0]){
		  //alert(msg[1]);
		  return;
		}
		var dataType="";
		if (type==1){
		  dataType = document.getElementById("bumen");
		  RemoveItems(dataType);
		}else if (type==0){
		  dataType = document.getElementById("gongsi");
		  RemoveItems(dataType);
		}else if (type==2){
		  dataType = document.getElementById("yibanzu");
		}
		var rowDate = result.split(",");//有多少行
		if (rowDate!=""){
			  for(var i=0;i<rowDate.length;i++){		     
					var cellDate = rowDate[i].split("|");//有多少列
	 	      		  //下拉框
		 			 var op =document.createElement("option");
	 			 	 op.innerHTML = cellDate[1];
	 			 	 op.value = cellDate[0];
	 			 	 dataType.appendChild(op);
	           }
		  }
	  }
    }
  );
 } 
//选择项信息
   function selected(id,name){
    var gongsi = document.getElementById("gongsi");
   var index = gongsi.selectedIndex;   
   var gongsiVal = gongsi.options[index].value;   
   var gongsiTxt = gongsi.options[index].text;
   var bumen = document.getElementById("bumen");
   var i = bumen.selectedIndex;   
   var bumenVal = bumen.options[i].value;   
   var bumenTxt = bumen.options[i].text;
   var yibanzu = document.getElementById("yibanzu");
   var i = yibanzu.selectedIndex;   
   var yibanzuVal =yibanzu.options[i].value;   
   var yibanzuTxt = yibanzu.options[i].text;
   var fullname="";
    if(id.length==4){
        fullname=name;
    }
    else if(id.length==6){
        fullname=gongsiTxt+"--"+name;
    }
    else if(id.length==8){
        fullname=gongsiTxt+"--"+bumenTxt+"--"+name;
    }
    else if(id.length==10){
        fullname=gongsiTxt+"--"+bumenTxt+"--"+yibanzuTxt+"--"+name;
    }
    var currentDiv = window.parent.$("#areaDiv");
	window.parent.form1.bumen.value = id;
	window.parent.form1.bumenid.value = fullname;
	closeDiv();
  }
function closeDiv(){
		var currentDiv = window.parent.$("#areaDiv");
		currentDiv.remove();
	}
function getDate(){
   var gongsi = document.getElementById("gongsi");
   var index = gongsi.selectedIndex;   
   var shengVal = gongsi.options[index].value;   
   var shengTxt = gongsi.options[index].text;
   var bumen = document.getElementById("bumen");
   var i = bumen.selectedIndex;   
   var shiVal = bumen.options[i].value;   
   var shiTxt = bumen.options[i].text;
   var yibanzu = document.getElementById("yibanzu");
   var i = yibanzu.selectedIndex;   
   var quxianVal = yibanzu.options[i].value;   
   var quxianTxt = yibanzu.options[i].text;
    
   var fatherAreaName = "";
   if (shengVal==0 && shiVal == 0&& quxianVal ==0){
     fatherAreaName = "";
   }else if (shengVal!=0 && shiVal == 0&& quxianVal ==0){
      fatherAreaName = "1";
   }else if (shengVal!=0 && shiVal != 0&& quxianVal ==0){
      fatherAreaName = "2";
   }else if (quxianVal!=0 && quxianVal != 0 && quxianVal !=0){
     fatherAreaName = "3";
   }
   CurPage = 0
   getDictList(fatherAreaName,15,1);
   
}
//分页
function TurnTab(type){
    var tabinfo = document.getElementById("div_trackpagetab"); 
    var strInner = "";
    strInner += "<font color='#000080'>页次:</font>&nbsp;&nbsp;&nbsp;&nbsp;<font color=red>" +CurPage+"</font>&nbsp;/&nbsp;"+TotalPage + "&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;";
    if (CurPage!=1){
      strInner += "<a href=\"javascript:TurnPage("+type+")\">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
      strInner += "<a href=\"javascript:PreviousPage(" + type + ")\">上页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
    }else{
       strInner += "<font color='#000080'>首页</font>&nbsp;&nbsp;&nbsp;&nbsp;";
       strInner += "<font color='#000080'>上页</font>&nbsp;&nbsp;&nbsp;&nbsp;";
    }
    
    if(CurPage < TotalPage ){
        strInner += "<a href=\"javascript:NextPage("+type+")\">下页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        strInner += "<a href=\"javascript:TurnPage1(" + type + ")\">尾页</a>";
    }else if (CurPage == TotalPage){
        strInner += "<font color='#000080'>下页 </font>&nbsp;&nbsp;&nbsp;&nbsp;";
       strInner += "<font color='#000080'>尾页 </font>";
    }
	strInner += "<select name='page' id ='page' onChange='javascript:gopagebyno("+type+")' class='form' >"
	
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
  getDictList(type,MaxRow,MinRow);
}
function TurnPage(type){
     //点击首页初始化
     getDictList(type,15,1);
}
function TurnPage1(type){
     //点击尾页初始化
     var MaxRow = (TotalPage)*PageRow
     var MinRow=(TotalPage-1)*PageRow+1
     getDictList(type,MaxRow,MinRow);
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
    getDictList(type,MaxRow,MinRow);
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
    getDictList(type,MaxRow,MinRow);
 }
}
function quxiao(){
		closeDiv();
		}
function RemoveItems(obj){
	for(var i=obj.options.length;i>=1;i--){
	   obj.options.remove(i);
    }
}
$(function(){
			$("#cancelBtn").click(function(){
				closeDiv();
			});
		});
</script>
  
  <body  class="body" style="overflow-x:hidden;" onload="waitThenDoIt();">
	<LINK href="../../images/css.css" type=text/css rel=stylesheet>
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
		    <td width="10" height="500" background="../../images/img_10.gif">&nbsp;</td>
		    <td valign="top">
				<table width="100%"  border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="3">
							<table width="100%"  border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td colspan="4">&nbsp;</td>
								</tr>
							
								<tr>
									<td colspan=3 width="600" background="<%=path%>/images/btt.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部门列表</span></td>
									
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
																<br>
														
															  		<tr>
															         <td colspan=4>
															        公司:
															         <select name="gongsi" id="gongsi" style="width:130" style="width:130;" onchange="getDictList(1,15,1)">
															         <option value="0">请选择</option>
															         	</select>
															         部门：
															         <select name="bumen" id="bumen" style="width:130" style="width:130; "onchange="getDictList(2,15,1)">
																           <option value="0">请选择</option>
															         </select>
															    一级班组：
															         <select name="yibanzu" id="yibanzu" style="width:130" style="width:130; "onchange="getDictList(3,15,1)">
																           <option value="0">请选择</option>
															         </select><!--
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          <input type="button" name="xuanze"id="id1" style="color:#014F8A;width:60" value="选择" onclick="select()"/>
															        --></td>
															         
															      </tr>
															     <tr>
															        <td> </br>
															        </td>
															     </tr>
															    
															     
													      </table>
													      
													     <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" id ="dict">
													         <tr>
													             <td width="5%" bgcolor="#888888"><div align="center" class="bttcn">ID</div></td>
													             <td height="23" bgcolor="#888888"><div align="center" class="bttcn">部门编码</div></td>
													             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">部门名称</div></td>
													    
													             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">选择</div></td>
													         </tr>
													  	 </table> 
										            </td>
								                 </tr>
                                                 <tr bgcolor="#ffffff">
                                                 <td>
                                                    <div id="div_trackpagetab" align="center"><font color='#000080'></font></div>
                                                 </td>
                                               
                                                 </tr>
                                                    <tr>
                                                 <td>
                                                    <div><font color='#000080'></font></div>
                                                 </td>
                                                 </tr>
							                 </table>
							                 
						                  </td>
					                  </tr>
				                   </table>
				                     <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:3px">
	                                               <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	                                               <span style="font-size:12px;position: absolute;left:27px;top:3px;color:white">返回</span>
                                            </div>
                                   
		                </td>
		                
		                
		             </tr>
		             <tr>
		             <td>&nbsp;</td>
		             </tr>
		        </table>    
	       </td>
	       </tr>        
	</table>	
</body>
</html>
