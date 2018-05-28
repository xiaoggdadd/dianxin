<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="com.noki.mobi.common.Account"%>
<%@ page import="com.noki.mobi.sys.javabean.FenZuForm"%>
<%
	String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
String id = request.getParameter("id");
FenZuForm form = new FenZuForm();
form = form.getAccountInfo(id);
%>
<html>
<head>
<title>
修改分组
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
<script language="javascript">
var path = '<%=path%>';
		function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

		}
		else
		{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
}
	function baocun(){

         var roleName = document.form1.name.value;

        if(roleName==""){
         	alert("分组名称不能为空！");
                 document.form1.name.focus();
                 return
        }
        if(roleName.length > 10){
        	alert("name名称长度不大于10个汉字的长度！");
                 document.form1.name.focus();
                 return
        }
        var memo = document.form1.memo.value;
        if(memo==""){
         	alert("分组说明不能为空！");
                 document.form1.memo.focus();
                 return
        }
        var orderid = document.form1.orderid.value;
        if(orderid==""){
        		alert("排序不能为空！");
                 document.form1.orderid.focus();
                 return
        }
        document.form1.action=path+"/servlet/fenzu?action=modify";
        document.form1.submit();
         
	}
	$(function(){
			
			$("#resetBtn").click(function(){
				$.each($("form input[type='text']"),function(){
					$(this).val("");
				})
			});
			$("#baocun").click(function(){
				baocun();
			});
			$("#cancelBtn").click(function(){
				window.close();
			});
		});
</script>
</head>
<body  class="body" style="overflow-x:hidden;">
	
<form action="" name="form1" method="POST">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  
  <tr>
   
    <td valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan=1 width="500" background="<%=path%>/images/btt2.bmp" height=53><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改分组</span></td>
							
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
										  <td height="49" bgcolor="#FFFFFF">										  <table width="80%" border="0" cellspacing="1" cellpadding="1" align="center">
                                            <tr>
                                              <td width="30%" bgcolor="#DDDDDD" align="center">新分组名称：</td>
                                              <td width="70%">
                                                <input type="text" name="name" value="<%=form.getName()%>"  class="form" />
                                                <span class="style1">&nbsp;*</span> </td>
                                            </tr>
                                            <tr>
                                              <td width="30%" bgcolor="#DDDDDD" align="center">说明：</td>
                                              <td width="70%">
                                                <input type="text" name="memo" value="<%=form.getMemo()%>"  class="form" />
                                                <span class="style1">&nbsp;*</span> </td>
                                            </tr>
                                             <tr>
                                              <td width="30%" bgcolor="#DDDDDD" align="center">排序：</td>
                                              <td width="70%">
                                                <input type="text" name="orderid" value="<%=form.getOrderid()%>"  class="form" />
                                                <span class="style1">&nbsp;*</span> </td>
                                            </tr>
                                            <tr>
                                            
                                            <td></td>
                                            <td>
                                            <div id="baocun" style="position: relative; width: 63px; height: 23px; cursor: pointer; float: left; left: 49.5%">
											<img alt=""src="<%=request.getContextPath()%>/images/baocun.png" width="100%" height="100%" />
											<span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">保存</span>
											</div>
      									    <div id="resetBtn" style="width: 60px; height: 23px; cursor: pointer; float: left; position: relative; left: 50%">
			 								<img src="<%=path%>/images/2chongzhi.png" width="100%" height="100%">
											<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
											</div>
											<div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:-28px">
	                                             <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	                                             <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
                                            </div>
											</td>
                                             
                                            </tr>

                                          </table></td>
										</tr>
									</table>
								</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
     
<input type="hidden" name="id" value="<%=id%>" />
</form>
</body>
</html>

