<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>
增加投资信息
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
	color: #FF0000;
	font-weight: bold;
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 #id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
      <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>

     <script src="<%=path%>/javascript/PopupCalendar.js"></script>
     <script >
		var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
		oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
		oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
		oCalendarChs.oBtnTodayTitle="今天";
		oCalendarChs.oBtnCancelTitle="取消";
		oCalendarChs.Init();
	</script>
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
   function waitThenDoIt(){
	    if (window.document.readyState){//IE
		      if (window.document.readyState=='complete'){ 
		         
		        var params = "&xmmch="+document.getElementById("xmmch").value;
		        var Url=getPublicPath()+"/servlet/CashinfoServlet?action=optionSele&roleFlg=PERMISSION_SEARCH";
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
							if (""==result){
				              alert("没有投资信息可加");
				           	 	opener.getDate();
	 	                        window.close();
				           }else{
				           	  dataType = document.getElementById("xmmch");
							  var rowDate = result.split(",");	
							  for(var i=0;i<rowDate.length;i++){		     
								var cellDate = rowDate[i].split("|");//有多少列
					 			 var op =document.createElement("option");
				 			 	 op.innerHTML = cellDate[0];
				 			 	 op.value = cellDate[0];
				 			 	 dataType.appendChild(op);
				              }
							}

			 			}
			 	}) 
		      }else{
		        setTimeout("waitThenDoIt()",10);
		      }
	   	 } 
	}
    function addDate(){
       var Url=getPublicPath()+"/servlet/CashinfoServlet?action=insert&roleFlg=PERMISSION_ADD";
       var params ="&key=value";
		var	xmmch=document.getElementById("xmmch").value;    
		var	ysztz=document.getElementById("ysztz").value;    
		var	sjztz=document.getElementById("sjztz").value;    
		var	yjjsdf=document.getElementById("yjjsdf").value;  
		var	sjjydf=document.getElementById("sjjydf").value;  
		var	yssbtz=document.getElementById("yssbtz").value;  
		var	sjsbtz=document.getElementById("sjsbtz").value;  
		var	yssgtz=document.getElementById("yssgtz").value;  
		var	sjsgtz=document.getElementById("sjsgtz").value;  
		var	yswhtz=document.getElementById("yswhtz").value;  
		var	sjfhtz=document.getElementById("sjfhtz").value;  
		var	yjtzl=document.getElementById("yjtzl").value;    
		var	yjtzj=document.getElementById("yjtzj").value;    
 
       params = "&xmmch="+xmmch+"&ysztz="+ysztz+"&sjztz="+sjztz+"&yjjsdf="+yjjsdf+"&sjjydf="+sjjydf+
       "&yssbtz="+yssbtz+"&sjsbtz="+sjsbtz+"&yssgtz="+yssgtz+"&sjsgtz="+sjsgtz+"&yswhtz="+yswhtz+"&sjfhtz="+sjfhtz
       +"&yjtzl="+yjtzl+"&yjtzj="+yjtzj;
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
window.history.back(-1);
    }
   $(function(){
			$("#cancelBtn").click(function(){
				window.history.back();
			});
			$("#resetBtn").click(function(){
				$.each($("form input[type='text']"),function(){
					$(this).val("");
				})
			});
			$("#saveBtn").click(function(){
				addDate();
			});
		});
</script>
</head>

<body  class="body" style="overflow-x:hidden;"onload="waitThenDoIt()">
	
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						
						<tr>
							<td colspan=1 width="600" background="<%=path%>/images/btt2.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 增加投资信息</span></td>
							
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

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
	    <br/>
	    <tr bgcolor="F9F9F9">
	       <td bgcolor="#DDDDDD">项目名称:</td>
	       <td>	       
	       <select name="xmmch" id="xmmch" style="width:155" style="width:150;">
	       </select>
	       </td>
	       <td></td>
	       <td></td>
	    </tr>
	    <tr bgcolor="F9F9F9">
	       <td bgcolor="#DDDDDD">预算总投资:</td>
	       <td><input type="text" id="ysztz" name="ysztz"  class="form"></td>
	       <td bgcolor="#DDDDDD">实际总投资:</td>
	       <td><input type="text" id="sjztz" name="sjztz"  class="form"/></td>
	    </tr>
	    <tr bgcolor="F9F9F9">
	       <td bgcolor="#DDDDDD">预计节省电费:</td>
	       <td><input type="text" id="yjjsdf" name="yjjsdf"  class="form"></td>
	       <td bgcolor="#DDDDDD">实际节省电费:</td>
	       <td><input type="text" id="sjjydf" name="sjjydf"  class="form"/></td>

	    </tr>
	    <tr bgcolor="F9F9F9">
	       <td bgcolor="#DDDDDD">预算设备投资:</td>
	       <td><input type="text" id="yssbtz" name="yssbtz"   class="form"/></td>
	       <td bgcolor="#DDDDDD">实际设备投资:</td>
	       <td><input type="text" id="sjsbtz" name="sjsbtz"  class="form"></td>
	    </tr>
	    <tr bgcolor="F9F9F9">
	       <td bgcolor="#DDDDDD">预算施工投资:</td>
	       <td><input type="text" id="yssgtz" name="yssgtz" class="form"/></td>
	       <td bgcolor="#DDDDDD">实际施工投资:</td>
	       <td><input type="text" id="sjsgtz" name="sjsgtz"  class="form"></td>
	    </tr>   
	    <tr bgcolor="F9F9F9">
	       <td bgcolor="#DDDDDD">预算维护投资:</td>
	       <td><input type="text" id="yswhtz" name="yswhtz" class="form"/></td>
	       <td bgcolor="#DDDDDD">实际维护投资:</td>
	       <td><input type="text" id="sjfhtz" name="sjfhtz"  class="form"></td>
	    </tr>  
	    <tr bgcolor="F9F9F9">
	       <td bgcolor="#DDDDDD">预计投资回报率:</td>
	       <td><input type="text" id="yjtzl" name="yjtzl" class="form"/></td>
	       <td bgcolor="#DDDDDD">实际投资回报率:</td>
	       <td><input type="text" id="yjtzj" name="yjtzj"   class="form"></td>
	    </tr> 
    <tr>
    <td>
    </td>
    <td></td>
       <td colspan="2">
          <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:164px">
	           <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
          </div>
            
          <div id="resetBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:175px">
	           <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">重置</span>
          </div>
          
          <div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:186px">
	           <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>
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
      <br />
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
 
</table>
</body>
</html>


