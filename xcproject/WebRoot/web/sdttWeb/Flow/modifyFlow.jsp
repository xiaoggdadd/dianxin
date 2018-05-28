<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.noki.mobi.common.Account,com.noki.mobi.flow.javabean.FlowBean" %>
<%@ page import="java.util.ArrayList" %>
<%
	String path = request.getContextPath();
	String flowId = request.getParameter("flowId");//获取流程id 
    String flowName = FlowBean.getFlowName(flowId);
    String flowtypes="";
	    String FLOW_TYPES=flowName.substring(flowName.indexOf("BB")+2);
	    System.out.println(flowName);
	    if(FLOW_TYPES.equals("1")){
	    	flowtypes="报账流程";
		}else if(FLOW_TYPES.equals("2")){
			flowtypes="电表审核流程";
		}
        String flowDesc =flowName.substring(flowName.indexOf("AA")+2,flowName.indexOf("BB"));
        flowName=flowName.substring(0,flowName.indexOf("AA"));
        Account account = (Account)session.getAttribute("account");
String role_id = account.getRoleId();
%>
<html>
<head>
<title>
修改流程
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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	
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
         var flowName = document.form1.flowName.value;
          var flowtypes = document.form1.flowtypes.value;//流程类型
        if(flowName==""){
         	alert("流程新名称不能为空！");
                 document.form1.flowName.focus();
                 return
        }
        if(flowName.length > 10){
        	alert("流程名称长度不大于10个汉字的长度！");
                 document.form1.flowName.focus();
                 return
        }
         if(flowtypes==""){
         	alert("请选择流程类型！");
                 document.form1.flowtypes.focus();
                 return
        }
         if(confirm("你将要修改流程信息？确定？")){
           document.form1.action=path+"/servlet/flow?action=modify";
           document.form1.submit();
         }
	}
	//去空格
	 function Trim(str)
     { 
         return str.replace(/(^\s*)|(\s*$)/g, ""); 
     }
	$(function(){
			$("#typeVal").click(function(){
				baocun();
			});
		
			$("#cancelBtn").click(function(){
				window.close();
			});
			$("#resetBtn").click(function(){
				$.each($("form input[type='text']"),function(){
					$(this).val("");
				})
			});
		});
</script>
</head>
<jsp:useBean id="bean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
<form action="" name="form1" method="POST">
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">


	<tr>
    
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan=1  background="<%=path%>/images/btt.bmp" height=40><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编辑流程</span></td>

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
												<table width="80%" border="0" cellspacing="1" cellpadding="1" align="center">

       <tr>
      <td width="35%" bgcolor="#DDDDDD"><div align="center">原流程名称：
      </div></td>
      <td width="65%" >
        <%=flowName%>

      </td>
    </tr>
      <tr>
      <td height="22" bgcolor="#DDDDDD"><div align="center">新名称：
      </div></td>
      <td>
        <input type="text" name="flowName" value=""  class="form" width="65%"  style="width:130"/><span class="style1">&nbsp;*</span>

      </td>
    </tr>
    <tr><%--
    <td width="35%" bgcolor="#DDDDDD"><div align="center">原流程类型：
      </div></td>
      <td width="65%" >
        <%=flowtypes%>

      </td>
      </tr>
     --%><td width="35%" bgcolor="#DDDDDD" align="center">
											新流程类型
										</td>
										<td width="100px">
											<select name="flowtypes" 
												style="box-sizing: border-box; width:130px"
												class="selected_font">
												<%if(flowtypes!=null||flowtypes!=""){%>
													<option value="<%=FLOW_TYPES%>">
													<%=flowtypes%>
													</option>
											<%	}else{ %>
												<option value="">
													请选择
												</option>
												<%} %>
												<option value="1">
													报账流程
												</option>
												<option value="2">
													电表审批流程		
											</option>
												<option value="2">
													办公、营业厅审批流程	
													</option>
													
											</select>
											 <span class="style1">&nbsp;*</span> </td>
     <tr>
      <td height="22" bgcolor="#DDDDDD"><div align="center">流程说明：
      </div></td>
      <td>
        <input type="text" name="flowDesc" value="<%=flowDesc%>"  class="form" width="65%"style="width:130"/>

      </td>
    </tr>
      <tr>
      <td></td>
      <td>
      
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
     
  <input type="hidden" name = "flowId" value="<%=flowId%>"/>
<div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:1px">
	           <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
          </div>
	  
        <div id="resetBtn"style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:3px">
	   <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
       <span style="font-size:12px;position:absolute;left:27px;top:5px;color:white">重置</span>
       </div>
         <div id="typeVal" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:6px">
	      <img src="<%=path %>/images/tijiao.png" width="100%" height="100%">
	       <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">提交</span>
        </div>
</form>
</body>
</html>


