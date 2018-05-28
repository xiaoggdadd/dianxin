<%@ page language="java" import="com.noki.mobi.common.Account" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
Account account=(Account)session.getAttribute("account");
String accountId = account.getAccountId();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>gonggaoAlert</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
     <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<!--[if gt IE 8]><!-->
<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"></script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
<!--<![endif]-->
<!--[if lte IE 8]>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/console.js"></script>
<![endif]-->
<style type="text/css">
.alertWindowContent h1,p{text-align: center;font-size: 18px;font-weight: bolder;}
.alertWindowContent input{width: 100px; height: 50px;cursor: pointer;-webkit-border-radius: 5px;-moz-border-radius: 5px;border-radius: 5px;}
</style>
<script>
	var path = '<%=basePath%>';
	var accountId = "<%=accountId%>";
	var length = "${length}";
	var ggid = "${ggid}";
    $(function(){
	    if(true){
	    	window.location.href = path+"web/sdttWeb/index.jsp";//从这里进入管理页面
	    }else{
	    	jQuery.alertWindow("最新公告",alertnr);
	    	 return;
	    }
    });
    function dfinfo5(id,b) {
    	document.getElementById('new'+b).value=1;
	var url = path + "web/sys/ggaoxx.jsp?id=" + id;
	var obj = new Object();
	obj.mid = 'mid';
	var obj = showModalDialog(url, obj,
			'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');
	}
    function insertggid(){
    	var num=0;
    	for(var i=1;i<=length;i++){
    		num = num + Number(document.getElementById('new'+i).value);
    	}
    	if(num == length){
    		window.location.href=path+"servlet/NewsServlet?action=insertnews&accountId="+accountId+"&ggid="+ggid;
    		showdiv("请稍等..............");
    	}else{
    		alert("请查看所有公告！");
    	}
    }  
    /*
    alertWindow by taozhi
    消息框
 */
jQuery.extend({
    alertWindow:function(title,content,bgcolor){
		
        var title = title; //标题
        var content = content; //内容
        var color1; //背景颜色
        
        
        if(bgcolor===undefined){
            color1 = "#FF7C00";
        }else{
            color1 = bgcolor;
        }
        //查找body中是否存在该消息框
        if($("body").find(".alertWindow1").length===0){
        //不存在
            var alertHtml = '<form action="" name="form1"><div  class="alertWindow1" style="width: 100%;height: 100%; background:rgba(0,0,0,0.5);position: fixed; left:0px; top: 0px; z-index: 9999;">'+
                                '<div  style="width: 765px; height: 250px;background: #FFF;margin: 100px auto;border: 2px solid #CFCFCF; border-bottom: 10px solid '+color1+';">'+
                                    '<div  style="width: inherit;height: 20px;">'+
                                        '<div class="alertWindowCloseButton1" style="float: right; width: 10px; height: 20px;margin-right:5px;font-family:\'microsoft yahei\';color:'+color1+';cursor: pointer;">X</div>'+
                                    '</div>'+
                                    '<h1 class="alertWindowTitle" style="margin-top:20px;text-align:center;font-family:\'宋体\';font-size: 18px;font-weight: normal;color: '+color1+';">'+title+'</h1>'+
                                    '<div class="alertWindowContent" style="width:721px;px;height: 105px;padding-left:20px;padding-right:20px;font-size: 13px;color: #7F7F7F;">'+content+'</div>'+
                                    '<p><input class="alertWindowCloseSure1" type="button" value="确定" style="width: 70px;height: 30px;background:'+color1+';border:none;position: relative;bottom: 18px;font-size:18px;color:#FFFFFF;-webkit-border-radius: 10px;-moz-border-radius: 10px;border-radius: 10px;cursor: pointer;"></p>'+
                                '</div>'+
                           '</div></form>';
            $("body").append(alertHtml);
            /*
             绑定事件
             */
            var $alertWindow = $(".alertWindow1"); //窗口对象
            //右上角关闭按钮
            $(".alertWindowCloseButton1").click(function(){
            	insertggid();
                
            });
            //确定按钮
            $(".alertWindowCloseSure1").click(function(){
            	insertggid();
            });
        }else{
        //存在
            //设置标题
            $(".alertWindowTitle").text(title);
            //设置内容
            $(".alertWindowContent").text(content);
            //显示
            $(".alertWindow1").show();
        }
    }
});
</script>
  </head><body>
  </body>
</html>
