<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String fatherAreaName=request.getParameter("fatherAreaName");
		String fatherCode = request.getParameter("fatherCode");
        String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        
        System.out.println("loginName :"+loginName+"loginId:"+loginId);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>增加地区</title>
    <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
     <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
     <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
      <script type="text/javascript" src="<%=path%>/web/javascript/popup_layer.js"></script>
        <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
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

    function addDate(){
        var father='<%=fatherAreaName%>';
    	if( checkNotnull(document.form1.fatherCode,"父区域编码")&&
    		checkNotnull(document.form1.areaCode,"区域编码")&&
    		checkNotnull(document.form1.areaName,"区域名称")){
          	if(isNaN(document.form1.areaCode.value)==false
          	){
       			
      		    var logname='<%=loginName%>';
      		    var logid='<%=loginId%>';
      		    var fatherCode = document.getElementById("fatherCode").value;
       			var areaCode =document.getElementById("areaCode").value;
      		    var areaName =document.getElementById("areaName").value;
      		    if(father=="1"){
      		    	if(areaCode.length!=3){
       			   		alert("省级的区域编码必须为三位数!");
       			   		return;
       			   	}
      	        }else{
      	        //alert((fatherCode.length+2));
       			    if ((areaCode.length<fatherCode.length)||areaCode.length<(fatherCode.length+2)||(areaCode.length>(fatherCode.length+2))||(areaCode.length==fatherCode.length)){
        			    alert("输入的区域编码不正确,区域编码比父区域编码多两位");
        		        return;
       			      }
       			
       					var s=fatherCode.length;
       					var z=areaCode.substr(0,s);
       			    if(z!=fatherCode){
       			   		alert("区域编码不规范，前几位要和父区域编码相同");
       					return;
       				 }
       			}
       			
     		    var params = "?fatherCode="+fatherCode+"&areaCode="+areaCode+"&areaName="+areaName+"&logname="+logname+"&logid="+logid;
      		    var Url=getPublicPath()+"/servlet/LocalityDataServlet?action=insert&roleFlg=PERMISSION_ADD";
       
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
	 	
	 	}else {
          	alert("区域编码必须为数字");
          	}
          	
       
        
       } 	
    }
       $(function(){
			$("#typeVal").click(function(){
				addDate();
			});
		
			$("#cancelBtn").click(function(){
				window.close();
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
  <body  class="body">
  <form action="" method="post" name="form1">
    
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
								<tr>
									<td colspan=3 width="600" background="<%=path%>/images/btt2.bmp" height=40><span style="color:black;font-weight:bold;font-size=16;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加地区</span></td>
									<td >&nbsp;</td>
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
                      <td height="19" colspan="4">　<img src="images/v.gif" width="12" height="12" />新增项</td>
                    </tr>

  		</table>
  <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
      <tr>
       <td>区域类型:</td>
       <%if("1".equals(fatherAreaName)){%>
       <td>添加省份
       
       </td>
       <%}%>
       <%if("2".equals(fatherAreaName)){%>
       <td>添加市地</td>
       <% }%>
       <%if("3".equals(fatherAreaName)){%>
       <td>添加区县</td>
       <% }%>
       <%if("4".equals(fatherAreaName)){%>
       <td>添加区县以下区域</td>
       <%}%>
    </tr>
     <tr>
       <td>父区域编码:</td>
       <td><input id="fatherCode" name="fatherCode" readonly value ="<%=fatherCode%>"></td>
    </tr>
    <tr>
       <td>区域编码:</td>
       <td><input type="text" id="areaCode" name="areaCode"/></td>
    </tr>
    <tr>
       <td>区域名称:</td>
       <td><input type="text" id="areaName" name="areaName"></td>
    </tr>
    <tr><td></td>
      <td>
        <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:14px">
	           <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
          </div>
	   <div id="typeVal" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:17px">
	      <img src="<%=path %>/images/tijiao.png" width="100%" height="100%">
	       <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">提交</span>
        </div>   
      </td>
    </tr>
	</table>
	
	<div id ="char"></div>
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
	<input type="hidden" name="logname" value="<%=loginName %>"/>
	<input type="hidden" name="logid" value="<%=loginId %>"/>
   </form>
  </body>
</html>
