<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.noki.mobi.common.Account" %>

<%
    String path = request.getContextPath();
    
    
		Account account = (Account)session.getAttribute("account");
        String ssheng=account.getSheng();
        String sshi=account.getShi();
        String xxian=account.getXian();
        String roleid = account.getAccountId();
        
        System.out.println("权限"+roleid+"市"+sshi+"县"+xxian);
%>

<html>
<head>
<script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<title>
logMange
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
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
<script src="../javascript/common.js"></script>
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
var path='<%=path%>';
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
        getDictList(0,15,1);
      }else{
        setTimeout("waitThenDoIt()",10);
      }
  	 }else {//Firefox
       window.addEventListener("load",function(){getDictList(0,15,1);},false);
   } 

}
function getDictList(type,maxrow,minrow){
    var sl=document.form1.shi.value;
    var ss=document.form1.quxian.value;
    var ll=document.form1.sheng.value;
    if(ss==0&&sl!=0){type=2;}
    if(sl==0&&ll!=0){type=1;}
    //alert(type);
	var role='<%=roleid%>';
  	var Url = "";
	var params ="&key=value";
	if (type==1){
	 Url = getPublicPath()+"/servlet/LocalityDataServlet?action=ajaxareaData&roleFlg=PERMISSION_SEARCH&role="+role;
	  params ="&zhanDian="+ document.getElementById("sheng").value;
	}else if (type==2){
	  Url = getPublicPath()+"/servlet/LocalityDataServlet?action=ajaxareaData&roleFlg=PERMISSION_SEARCH&role="+role;
	  params ="&zhanDian="+ document.getElementById("shi").value;
	}else if (type==3){
		  Url = getPublicPath()+"/servlet/LocalityDataServlet?action=ajaxareaData&roleFlg=PERMISSION_SEARCH&role="+role;
	      params ="&zhanDian="+ document.getElementById("quxian").value;
	}else{
	  Url = getPublicPath()+"/servlet/LocalityDataServlet?action=select&roleFlg=PERMISSION_SEARCH&role="+role;
	  params ="&zhanDian=000";
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
				  alert(msg[1]);
				  return;
				}
				var dataType="";
				if (type==1){
				  dataType = document.getElementById("shi");
				  RemoveItems(dataType);
				}else if (type==2){
				  dataType = document.getElementById("quxian");
				  RemoveItems(dataType);
				}
				else if (type==0){
				  dataType = document.getElementById("sheng");
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
			 			    	 var tdNode3=trNode.insertCell();//插入一列
				 			     var dirNode3 = document.createElement("div");//创建一个层
				 			     dirNode3.setAttribute("align","center");//层居中 
				 			     
				 			     var url="'"+"update.jsp?areaCode="+cellDate[0]+"&fatherCode="+cellDate[2]+"'";
                                 var str ="<a href=\"javascript:open1(" + url + ")\">修改</a>";  
   
				 			     dirNode3.innerHTML=str;
				 			     tdNode3.appendChild(dirNode3);//将层节点加入列
				 			     trNode.appendChild(tdNode3);
				 			     
				 			     var tdNode4=trNode.insertCell();//插入一列
				 			     var dirNode4 = document.createElement("div");//创建一个层
				 			     dirNode4.setAttribute("align","center");//层居中
				 			     var id=oTable.rows(i+1).cells(0).firstChild.innerHTML;
				 			     var str ="<a href=\"javascript:del(" + cellDate[0] + ")\">删除</a>";
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
    var role='<%=roleid%>';
  	var Url = getPublicPath()+"/servlet/LocalityDataServlet?action=selOption&roleFlg=PERMISSION_SEARCH&role="+role;
	var params ="&key=value";
	if (type==1){
	  params ="&zhanDian="+ document.getElementById("sheng").value;
	}else if (type==2){
	  params ="&zhanDian="+ document.getElementById("shi").value;
	}else if (type==3){
	  params ="&zhanDian="+ document.getElementById("quxian").value;
	}else{
	  params ="&zhanDian=000";
	}
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
		var dataType="";
		if (type==1){
		  dataType = document.getElementById("shi");
		  RemoveItems(dataType);
		}else if (type==0){
		  dataType = document.getElementById("sheng");
		  RemoveItems(dataType);
		}else if (type==2){
		  dataType = document.getElementById("quxian");
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
//增加数据
 function add(){
   var sheng = document.getElementById("sheng");
   var index = sheng.selectedIndex;   
   var shengVal = sheng.options[index].value;   
   var shengTxt = sheng.options[index].text;
   var shi = document.getElementById("shi");
   var i = shi.selectedIndex;   
   var shiVal = shi.options[i].value;   
   var shiTxt = shi.options[i].text;
   var quxian = document.getElementById("quxian");
   var i = quxian.selectedIndex;   
   var quxianVal = quxian.options[i].value;   
   var quxianTxt = quxian.options[i].text;
   var fatherAreaName = "";
   if (shengVal==0 && shiVal == 0 && quxianVal ==0){
     fatherAreaName = "1";
     fatherCode = "000";
   }else if (shengVal!=0 && shiVal == 0 && quxianVal ==0){
      fatherAreaName = "2";
      fatherCode = shengVal
   }else if (shengVal!=0 && shiVal != 0 && quxianVal ==0 ){
      fatherAreaName = "3";
      fatherCode = shiVal
   }else if (quxianVal!=0 && quxianVal != 0 && quxianVal !=0){
      fatherAreaName = "4";
      fatherCode = quxianVal
   
   }
   
  // document.form1.action=path+"/web/1111.jsp?fatherAreaName="+fatherAreaName+"&fatherCode="+fatherCode;
   //document.form1.submit();
    window.open("add.jsp?fatherAreaName="+fatherAreaName+"&fatherCode="+fatherCode, 'newwindow', 'height=200, width=500,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
 } 
   function open1(url){
      	window.open(url, 'newwindow', 'height=250, width=500,top=0,left=300,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
      	//document.form1.action=url;
       // document.form1.submit();
   }
//
   function del(id){
     if(confirm("确定删除")){
       var params = "?&areaCode="+id;
       document.form1.action=path+"/servlet/LocalityDataServlet?action=del&roleFlg=PERMISSION_DELETE&areaCode="+id
       document.form1.submit();
      
       var Url=getPublicPath()+"/servlet/LocalityDataServlet?action=del&roleFlg=PERMISSION_DELETE";
       
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
   var sheng = document.getElementById("sheng");
   var index = sheng.selectedIndex;   
   var shengVal = sheng.options[index].value;   
   var shengTxt = sheng.options[index].text;
   var shi = document.getElementById("shi");
   var i = shi.selectedIndex;   
   var shiVal = shi.options[i].value;   
   var shiTxt = shi.options[i].text;
   var quxian = document.getElementById("quxian");
   var i = quxian.selectedIndex;   
   var quxianVal = quxian.options[i].value;   
   var quxianTxt = quxian.options[i].text;
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

function RemoveItems(obj){
	for(var i=obj.options.length;i>=1;i--){
	   obj.options.remove(i);
    }
}
$(function(){
		$("#add11").click(function(){
			add();
		});
	});

</script>
<body  class="body" style="overflow-x:hidden;" onload="waitThenDoIt();">
<form action="" method="post" name="form1">
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
									<td colspan=3 width="600" background="<%=path%>/images/btt12.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地区管理</span></td>
									
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
											<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
												<tr>
													<td height="49" bgcolor="#FFFFFF">
															<table width="100%" border="0" cellspacing="1" cellpadding="1">
																<tr bgcolor="F9F9F9">
											                      <td height="19" colspan="4">　
											                    </tr>
														
															  		<tr>
															         <td colspan=4>
															         省:
															         <select name="sheng" id="sheng" style="width:130" style="width:130;" onchange="getDictList(1,15,1)">
															         <option value="0">请选择</option>
															         	</select>
															         城市：
															         <select name="shi" id="shi" style="width:130" style="width:130; "onchange="getDictList(2,15,1)">
																           <option value="0">请选择</option>
															         </select>
															         区县：
															         <select name="quxian" id="quxian" style="width:130" style="width:130; "onchange="getDictList(3,15,1)">
																           <option value="0">请选择</option>
															         </select>
															        </td>
															      </tr>
															     
													      </table><br>
													      
													     <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" id ="dict">
													      </div>
													         <tr>
													             <td width="5%" bgcolor="#888888"><div align="center" class="bttcn">ID</div></td>
													             <td height="23" bgcolor="#888888"><div align="center" class="bttcn">区域编码</div></td>
													             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">区域名称</div></td>
													             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">编辑</div></td>
													             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">删除</div></td>
													         </tr>
													  	 </table>
													  	 
										            </td>
								                 </tr>
                                                 <tr bgcolor="#ffffff">
                                                 <td>
                                                    <div id="div_trackpagetab" align="center"><font color='#000080'></font></div>
                                                    <div id="add11" style="position:relative;width:60px;height:23px;cursor: pointer;float:right;right:6px">
														<img alt="" src="<%=request.getContextPath() %>/images/xinzeng.png" width="100%" height="100%" />
														<span style="font-size:12px;position:absolute;left:25px;top:3px;color:white">新增</span>
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
</form>	
</body>
</html>

