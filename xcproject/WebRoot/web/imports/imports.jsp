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
中间表导入管理
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
#id2 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>

<script type="text/javascript" language="JavaScript" charset="utf-8">
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
        getDictList();
      }else{
        setTimeout("waitThenDoIt()",10);
      }
  	 }else {//Firefox
       window.addEventListener("load",function(){getDictList();},false);
   } 
	function getDictList(){
	 var Url = "";
	 Url = getPublicPath()+"/servlet/ImportServlet?action=getSelData";
	      new Ajax.Request(Url,{
      			method : 'post',
			asynchronous: false,
			onComplete : function (resp){
				var result=resp.responseText;//返回数据
	 			var retObject =result.split(",");
	 			if (retObject!=null){
	 			   obj=document.getElementById('zjb');
	 			   for(var i=0;i<retObject.length;i++){
	 			   var items =retObject[i].split("|");
	 			   obj.options.add(new Option(items[1],items[0])); //这个兼容IE与firefox  
	 			   }
	 			}	
	 			var oTable = document.getElementById("dict"); 
	 			var trNode = oTable.insertRow();//插入一行
	 			
				var tdNode=trNode.insertCell(1);//插入一列
				var dirNode = document.createElement("div");//创建一个层
	    		dirNode.setAttribute("align","center");//层居中
				var divText=document.createTextNode("1");//创建文本节点
	    		dirNode.appendChild(divText);//层中加入文本
	    		tdNode.appendChild(dirNode);//将层节点加入列
	    		trNode.appendChild(tdNode);
 			     
				var tdNode=trNode.insertCell(1);//插入一列
				var dirNode = document.createElement("div");//创建一个层
	    		dirNode.setAttribute("align","center");//层居中
				var divText=document.createTextNode(" ");//创建文本节点
	    		dirNode.appendChild(divText);//层中加入文本
	    		tdNode.appendChild(dirNode);//将层节点加入列
	    		trNode.appendChild(tdNode);
 			     
				var tdNode=trNode.insertCell(1);//插入一列
				var dirNode = document.createElement("div");//创建一个层
	    		dirNode.setAttribute("align","center");//层居中
				var divText=document.createTextNode(" ");//创建文本节点
	    		dirNode.appendChild(divText);//层中加入文本
	    		tdNode.appendChild(dirNode);//将层节点加入列
	    		trNode.appendChild(tdNode);
 			     
				var tdNode=trNode.insertCell(1);//插入一列
				var dirNode = document.createElement("div");//创建一个层
	    		dirNode.setAttribute("align","center");//层居中
				var divText=document.createTextNode(" ");//创建文本节点
	    		dirNode.appendChild(divText);//层中加入文本
	    		tdNode.appendChild(dirNode);//将层节点加入列
	    		trNode.appendChild(tdNode);
			}
		}
      );
	}
}
function imports(){
  var zjb = document.getElementById("zjb").value;
         var params = "?&zjb="+zjb;
        Url = getPublicPath()+"/servlet/ImportServlet?action=impDate";
	    new Ajax.Request(Url,{
	      		method : 'post',
				asynchronous: false,
			    parameters : params,
				onComplete : function (resp){//回调
	 			  var result=resp.responseText;//返回数据
	 			  
                  if (null!=result){
                    var items =result.split("|");
	                var oTable = document.getElementById("dict");//得到表格
	                var len = oTable.rows.length;
		 			for(var k=1;k<len;k++){
		 				   oTable.deleteRow(1);
		 			 }

			 		var trNode = oTable.insertRow();//插入一行
					var tdNode=trNode.insertCell();//插入一列
					var dirNode = document.createElement("div");//创建一个层
		    		dirNode.setAttribute("align","center");//层居中
					var divText=document.createTextNode("1");//创建文本节点
		    		dirNode.appendChild(divText);//层中加入文本
		    		tdNode.appendChild(dirNode);//将层节点加入列
		    		trNode.appendChild(tdNode);
	 			     
					var tdNode=trNode.insertCell();//插入一列
					var dirNode = document.createElement("div");//创建一个层
		    		dirNode.setAttribute("align","center");//层居中
					var divText=document.createTextNode(items[0]);//创建文本节点
		    		dirNode.appendChild(divText);//层中加入文本
		    		tdNode.appendChild(dirNode);//将层节点加入列
		    		trNode.appendChild(tdNode);
	 			    
					var tdNode=trNode.insertCell();//插入一列
					var dirNode = document.createElement("div");//创建一个层
		    		dirNode.setAttribute("align","center");//层居中
					var divText=document.createTextNode(items[1]);//创建文本节点
		    		dirNode.appendChild(divText);//层中加入文本
		    		tdNode.appendChild(dirNode);//将层节点加入列
		    		trNode.appendChild(tdNode);
	 			   
					var tdNode=trNode.insertCell();//插入一列
					var dirNode = document.createElement("div");//创建一个层
		    		dirNode.setAttribute("align","center");//层居中
					var divText=document.createTextNode(items[2]);//创建文本节点
		    		dirNode.appendChild(divText);//层中加入文本
		    		tdNode.appendChild(dirNode);//将层节点加入列
		    		trNode.appendChild(tdNode);

                  }

	 			}
	 	})
}
</script>
</head>
<body  class="body" style="overflow-x:hidden;"onload="waitThenDoIt();">
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
									<td colspan=3 width="600" background="<%=path%>/images/btt.bmp" height=40><span style="color:black;font-weight:bold;font-size=16;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中间表导入管理</span></td>
									
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
																										<div id="parent" style="display:inline">
                          </div><div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
														
															  		<tr>
																	  <td>
																	       中间表： <select id="zjb" name="zjb" style="width:180">
																	        </select>
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															          <input type="button" name="shanchulogs1"id="id1" style="color:#014F8A" class="memo" value="导入" onclick="imports()"/>
															        </td>
															      </tr>
													      </table>
													        	<div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>
  	
													     <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" id ="dict">
													         <tr>
													             <td width="2%" bgcolor="#888888"><div align="center" class="bttcn">ID</div></td>
													             <td height="23" bgcolor="#888888"><div align="center" class="bttcn">基础表名称</div></td>
													             <td height="23" bgcolor="#888888"><div align="center" class="bttcn">中间表名称</div></td>
													             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">导入信息个数</div></td>
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
					            <tr bgcolor="#FFFFFF">
					            <td ></td>
                                                 <td align="right">
                                                 												<div id="parent" style="display:inline" align="right">
                          <div style="width:300px;display:inline;" align="right"><hr></div>
                      </div>
                                                 </td></tr>
					              <tr bgcolor="#FFFFFF">
					              <td ></td>
															        <td align="right">
															        

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

