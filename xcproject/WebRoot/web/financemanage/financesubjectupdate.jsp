<%@ page contentType="text/html; charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String kmCode=request.getParameter("kmCode");
String fkmCode = request.getParameter("fkmCode");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>修改科目</title>
     <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
     <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
     <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
      <script type="text/javascript" src="<%=path%>/web/javascript/popup_layer.js"></script>
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

    function updataDate(){
       var fkmCode = document.getElementById("fkmCode").value;
       var kmCode =document.getElementById("kmCode").value;
       var kmName =document.getElementById("kmName").value;
       var params = "?fkmCode="+fkmCode+"&kmCode="+kmCode+"&kmName="+kmName;
       var Url=getPublicPath()+"/servlet/FinanceSubject?action=insert";
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
	 	opener.getDate();
	 	window.close();
    }
   function waitThenDoIt(){
	    if (window.document.readyState){//IE
		      if (window.document.readyState=='complete'){ 
		        var kmCode = <%=kmCode%>;
		        var fkmCode = <%=fkmCode%>;
		        var params = "?&kmCode="+kmCode+"&fkmCode"+fkmCode;
		        var Url=getPublicPath()+"/servlet/FinanceSubject?action=updateSele";
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
		               	  document.getElementById("kmCode").value = item[0];
		               	  document.getElementById("kmName").value = item[1];
		               	  document.getElementById("fkmcode").value = item[2];
		               	  document.getElementById("kmgrade").value = item[3];
		               	  document.getElementById("id").value = item[4];
			 			}
			 	}) 
		      }else{
		        setTimeout("waitThenDoIt()",10);
		      }
	   	 } 
	}
	function updata(){
             var params = "?kmCode="+document.getElementById("kmCode").value
             +"&kmName="+document.getElementById("kmName").value
             +"&fkmcode="+document.getElementById("fkmcode").value
             +"&kmgrade="+document.getElementById("kmgrade").value
             +"&id="+document.getElementById("id").value;
            var Url=getPublicPath()+"/servlet/FinanceSubject?action=updata";
           
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
	 	opener.getDate();
	 	window.close();
	
	}

     $(function(){
			$("#tijiao").click(function(){
				updata();
			});
			$("#cancelBtn").click(function(){
				javascript:window.close();
			});
		
		});
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
	<LINK href="web/images/css.css" type=text/css rel=stylesheet>
	<link href="web/images/core.css" type="text/css" rel="stylesheet"/>
  </head>
  <body  class="body" onload="waitThenDoIt();">
  <LINK href="../../images/css.css" type=text/css rel=stylesheet>
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

							<td colspan=3 width="600" background="<%=path%>/images/btt2.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改科目</span></td>
							
							<td width="">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</table>
					<br>
					<br>
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
<tr bgcolor="F9F9F9">
                      <td height="5" colspan="4">　<img src="images/v.gif" width="15" height="15" />&nbsp;&nbsp;<font size="3">基本信息</font></td>
                    </tr>

  		</table>
  		<br>
  <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
     <tr>
       <td>科目ID:</td>
       <td><input type="hidden" id="id" name="id" readonly/></td>
    </tr>
     <tr>
       <td>上级编码:</td>
       <td><input type="text" id="fkmcode" name="fkmcode" readonly></td>
    </tr>
    <tr>
       <td>科目编码:</td>
       <td><input type="text" id="kmCode" name="kmCode"/></td>
    </tr>
    <tr>
       <td>科目名称:</td>
       <td><input type="text" id="kmName" name="kmName"></td>
    </tr>

    <tr>
       <td>科目等级:</td>
       <td><input type="text" id="kmgrade" name="kmgrade"></td>
    </tr>
    </table>
    <br/>
    <br/>
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
    <tr>
       
       <td  colspan="4"> 
    
			<div id="cancelBtn"
				style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:130px">
				<img alt=""
					src="<%=request.getContextPath()%>/images/quxiao.png"
					width="100%" height="100%" />
				<span
					style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">返回</span>
			</div>

			
			<div id="tijiao" style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:135px">
				<img alt=""
					src="<%=path%>/images/baocun.png"
					width="100%" height="100%" />
				<span
					style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">保存</span>
			</div>
			</td>
    </tr>
	</table>
	
	<div id ="char"></div>
  </body>
</html>
