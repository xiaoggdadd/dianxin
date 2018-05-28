<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String typeVal=request.getParameter("typeVal");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据字典</title>
     <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
     <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
      <script type="text/javascript" src="<%=path%>/web/javascript/popup_layer.js"></script>
       <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
     <script type="text/javascript" language="JavaScript" charset="utf-8">
    
     //javascript获取指定参数及其对应的值
	  function getParameter(paraStr){
		var url=location.href;
		var result = "";
	    //获取URL中全部参数列表数据
	    var str = "&" + url.split("?")[1];
	    var paraName = paraStr + "=";
	    //判断要获取的参数是否存在
	    if(str.indexOf("&"+paraName)!=-1)
	    {
	        //如果要获取的参数到结尾是否还包含“&”
	        if(str.substring(str.indexOf(paraName),str.length).indexOf("&")!=-1)
	        {
	            //得到要获取的参数到结尾的字符串
	            var TmpStr=str.substring(str.indexOf(paraName),str.length);
	            //截取从参数开始到最近的“&”出现位置间的字符
	            result=TmpStr.substr(TmpStr.indexOf(paraName),TmpStr.indexOf("&")-TmpStr.indexOf(paraName));
	        }
	        else
	        {
	            result=str.substring(str.indexOf(paraName),str.length);
	        } 
	    }     
	    else   
	    {
	        result="无此参数";
	    }
	    return (result.replace("&",""));
		}
   
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
	
   
   //页面载入完成初始化下拉列表
   function waitThenDoIt(){
	    if (window.document.readyState){//IE
		      if (window.document.readyState=='complete'){ 
		      
		     var ids = getParameter("id");
		     var id = ids.split("=");
		      //根据id得到数据
		      // var ret = getDateById(id[1]);
		      //初始化下拉列表
		       getDictList();
		      }else{
		        setTimeout("waitThenDoIt()",10);
		      }
	   	 }else {//Firefox
	        window.addEventListener("load",function(){getCity();},false);
	    }  
	}
	
	//得到大分类数据
    function getDictList(){
    
    var params ="&key=value";
    params="&typeVal="+document.getElementById("typeVal").value;
        var Url=getPublicPath()+"/servlet/StaticDataServlet";

	    new Ajax.Request(Url,{
	      		method : 'post',
				asynchronous: false,
				parameters : params,
				onComplete : function (resp){//回调
	 			 var result=resp.responseText;//返回数据
	 			 var opt = result.split(",");
	 			 for (i=0;i<opt.length;i++ ){
	 			     var code = opt[i].split("|");
	 			     document.getElementById("dataType").value=code[1];
	 			     document.getElementById("index1").value=code[0];
	 			 }
	 	        }
		 })
    }
    
    
    function addDate(){
       var dataType = document.getElementById("dataType").value;
       var index1 = document.getElementById("index1").value;
       var code = document.getElementById("code").value;
       var index2 = document.getElementById("index2").value;
       var name = document.getElementById("name").value;
       var params = "?dataType="+dataType+"&index1="+index1+"&code="+code+"&name="+name+"&index2="+index2;
       var Url="servlet/AddDateServlet";
	    new Ajax.Request(Url,{
	      		method : 'post',
				asynchronous: false,
			    parameters : params,
				onComplete : function (resp){//回调
	 			 // var result=resp.responseText;//返回数据
	 			}
	 	})
	 	opener.getDate(15,1);
	 	window.close();
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<LINK href="web/images/css.css" type=text/css rel=stylesheet>
	<link href="web/images/core.css" type="text/css" rel="stylesheet"/>
  </head>
  <body  class="body" style="overflow-x:hidden;" onload="waitThenDoIt();">
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
							<td width="6" background="images/a_22.gif"><img src="images/a_21.gif" width="44" height="32" /></td>
							<td width="169" background="images/a_22.gif"><span class="style1">数据字典</span></td>
							<td width="185"><img src="images/a_23.gif" width="185" height="32" /></td>
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
								                    <tr bgcolor="F9F9F9">
								                      <td height="19" colspan="4">　<img src="images/v.gif" width="12" height="12" />添加小分类</td>
								                    </tr>

											  	</table>
											<tr>
											   <td height="49" bgcolor="#FFFFFF">
												  <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
												    
												  	<tr>
												        <td>数据类型：</td>
												        <td>
												            <input type="hidden"id="dataType">
												            <input type="text" readonly id ="index1">
												        </td>
												    </tr>
												    <tr>
												       <td>小分类CODE:</td>
												       <td><input type="text" id="code"/></td>
												    </tr>
												    <tr>
												       <td>小分类名称:</td>
												       <td><input type="text" id="name"/></td>
												    </tr>
												    <tr>
												       <td>小分类描述:</td>
												       <td><input type="text" id="index2"></td>
												    </tr>
												    <tr>
												    <td></td>
												       <td>
												            <input type="hidden"  name="typeVal" value ="<%=typeVal%>">
												             <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:10px">
	                                                        	 <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	                                                       	     <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
                                                             </div>
												             <div id="typeVal" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:14px">
	                                                             <img src="<%=path %>/images/tijiao.png" width="100%" height="100%">
	                                                             <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">提交</span>
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
		</td>
	</tr>
</table>
  </body>
</html>
