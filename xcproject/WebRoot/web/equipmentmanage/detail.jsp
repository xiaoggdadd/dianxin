<%@ page contentType="text/html; charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
String sheiebanid=new String(request.getParameter("sheiebanid").getBytes("ISO8859-1"),"UTF-8"); 
%>
<html>
<head>
<title>
addAccount
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
    <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>

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
		         
		        var params = "&sheiebanid="+document.getElementById("sheiebanid").value;
		        var Url=getPublicPath()+"/servlet/EquipmentmanageServlet?action=detail&roleFlg=PERMISSION_SEARCH";
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
			 			   var item = result.split("|");

		                   document.getElementById("dianbiaoid").value=item[0];    
		                   document.getElementById("sheiebanid").value=item[1];    
		                   document.getElementById("mingcheng").value=item[2];    
		                   document.getElementById("guige").value=item[3];  
		                   document.getElementById("shuoshuzhuanye").value=item[4];  
		                   document.getElementById("shuoshuwangyuan").value=item[5];
		                   document.getElementById("bili").value=item[6]; 
		                   document.getElementById("sccj").value=item[7]; 
		                   document.getElementById("zcbh").value=item[8]; 
		                   document.getElementById("bccd").value=item[9];       
                           document.getElementById("beizhu").value=item[10];  
			 			}
			 	}) 
		      }else{
		        setTimeout("waitThenDoIt()",10);
		      }
	   	 } 
	}
	function updata(){
	 	window.history.back(-1);
	}
	
   </script>
</head>

<body  class="body" style="overflow-x:hidden;" onload="waitThenDoIt();">
	<LINK href="../../images/css.css" type="text/css" rel=stylesheet>
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan=1 width="600" background="<%=path%>/images/btt.bmp" height=40><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 监测点详细信息</span></td>
							
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

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
						 <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4">　<img src="../../images/v.gif" width="8" height="9" /></td>
                    </tr>
			 <tr bgcolor="F9F9F9">
			       <td bgcolor="#DDDDDD">电表ID:</td>
			       <td><input type="text" id="dianbiaoid" name="dianbiaoid" readonly class="form"/></td>
			       <td bgcolor="#DDDDDD">监测点ID:</td>
			       <td><input type="text" id="sheiebanid" name="sheiebanid" value ="<%=sheiebanid%>" readonly class="form"/></td>
			    </tr>
			    <tr  bgcolor="F9F9F9">
			       <td bgcolor="#DDDDDD">名称:</td>
			       <td><input type="text" id="mingcheng" name="mingcheng" readonly class="form"></td>
			       <td bgcolor="#DDDDDD">规格:</td>
			       <td><input type="text" id="guige" name="guige" readonly class="form"/></td>
		
			    </tr>
			    <tr bgcolor="F9F9F9">
			       <td bgcolor="#DDDDDD">所属专业:</td>
			       <td><input type="text" id="shuoshuzhuanye" name="shuoshuzhuanye" readonly  class="form"/></td>
			       <td bgcolor="#DDDDDD">所属网元:</td>
			       <td><input type="text" id="shuoshuwangyuan" name="shuoshuwangyuan" readonly class="form"></td>
			    </tr>
			    <tr bgcolor="F9F9F9">
			       <td bgcolor="#DDDDDD">所属电表占比:</td>
			       <td><input type="text" id="bili" name="bili" readonly  style="width:30"/>%&nbsp;&nbsp;(数字)</td>
			       <td bgcolor="#DDDDDD">生产厂家:</td>
			       <td><input type="text" id="sccj" name="sccj" readonly class="form"></td>
			    </tr>
			     <tr bgcolor="F9F9F9">
			       <td bgcolor="#DDDDDD">资产编码:</td>
			       <td><input type="text" id="zcbh" name="zcbh" readonly  class="form"/></td>
			       <td bgcolor="#DDDDDD">标称耗电（度/天）:</td>
			       <td><input type="text" id="bccd" name="bccd" readonly class="form">&nbsp;&nbsp;(数字)</td>
			    </tr>
			     <tr bgcolor="F9F9F9">
			       <td bgcolor="#DDDDDD">备注:</td>
			       <td><input type="text" id="beizhu" name="beizhu" readonly  class="form"/></td>
			       <td></td>
			       <td></td>
			    </tr>
			    <tr>
			       <td colspan="4" align="right"><input type="button" value="返回" onclick="updata();">
			       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
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


