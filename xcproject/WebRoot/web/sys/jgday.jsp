<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.mobi.sys.javabean.TabParaForm" %>
<%@ page import="java.sql.ResultSet"%>

<%
	String sheng = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
        
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");

          String roleId = (String)session.getAttribute("accountRole");
          String permissionRights="";
          TabParaForm form = new TabParaForm();
          form = form.getAccountInfo();
%>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"1010");

System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<html>
<head>
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
 .btt{ bgcolor:#888888;}
  .bttcn{ color:white;font-weight:bold;}
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

  function chaxun(){
					document.form1.action=path+"/web/jizhan/danjiatiaozheng.jsp";
					document.form1.submit();
	}
	function delLogs(){
		

	}
	function addJz(){
		document.form1.action=path+"/web/jizhan/addjz.jsp";
		document.form1.submit();
	}
	function daorujz(){
		alert("请期待！");
	}

</script>

</head>

<body  class="body" style="overflow-x:hidden;">
	<LINK href="../../images/css.css" type=text/css rel=stylesheet>
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
							<td  width="600" background="<%=path%>/images/btt.bmp" height=40><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;解析数据参数配置</span></td>
							
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
									<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" bgcolor="7C92B7">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">


  		 <tr>
         <td colspan=3>
         
        
         </td>
      </tr>
 

  </table>

  	<div id="parent" style="display:inline">
          <div style="width:50px;display:inline;"><hr></div>&nbsp;参数 一 列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
      </div>
  <table width="60%" border="0" cellspacing="1" cellpadding="1" >
       </tr>
            <tr>
            <td align="center">解析读电表数据间隔时间</td>
            <td align="center">
            	<select name="jgday">
            		<option value="1">1 天</option>
            		<option value="2">2 天</option>
            		<option value="3">3 天</option>
            		<option value="4">4 天</option>
            		<option value="5">5 天</option>
            		<option value="6">6 天</option>
            		<option value="7">7 天</option>
            		<option value="8">8 天</option>
            		<option value="9">9 天</option>
            	</select>
            </td>
            <td align="center">
            	<input type="button" name="savejgday1" value="保存" onclick="savejgday()"/>
            </td>
            </tr>
       
      

  	 </table> 
  	 <br/>
  	 <div id="parent" style="display:inline">
          <div style="width:50px;display:inline;"><hr></div>&nbsp;参数 二 列表(填写对应的表字段)&nbsp;<div style="width:170px;display:inline;"><hr></div>
      </div>
      <table width="60%" border="0" cellspacing="1" cellpadding="1" >
       </tr>
            <tr>
            <td align="center">表名：</td>
            <td align="center">
            	<input type="text" name="tabname" value="<%=form.getTabname()%>"/>
            </td>
           
            </tr>
       			<tr>
	            <td align="center">电表ID：</td>
	            <td align="center">
	            	<input type="text" name="dbid" value="<%=form.getDbid()%>"/>
	            </td>
	             
           
            </tr>
            <tr>
            	<td align="center">上次读数值：</td>
	            <td align="center">
	            	<input type="text" name="ldata" value="<%=form.getLdata()%>"/>
	            </td>
	            <td align="center">本次读数值：</td>
	            <td align="center">
	            	<input type="text" name="bdata" value="<%=form.getBdata()%>"/>
	            </td>
	             
           
            </tr>
            <tr>
            	<td align="center">上次读数时间：</td>
	            <td align="center">
	            	<input type="text" name="ltime" value="<%=form.getLtime()%>"/>
	            </td>
	            <td align="center">本次读数时间：</td>
	            <td align="center">
	            	<input type="text" name="btime" value="<%=form.getBtime()%>"/>
	            </td>
	            
           
            </tr>
            <tr>
            	 <td align="center">实际值：</td>
	            <td align="center">
	            	<input type="text" name="sjdata" value="<%=form.getSjdata()%>"/>
	            </td>
            <td align="center">抄表标志位：</td>
            <td align="center">
            	<input type="text" name="zdcb" value="<%=form.getZdcb()%>"/>
            </td>
           
            </tr>
      			<tr>
      				<td align="center" colspan=4>
            	<input type="button" name="savejgday2" value="保存" onclick="savetab()"/>
            </td>
      			</tr>

  	 </table> 
  	 <div id="parent" style="display:inline">
          <div style="width:50px;display:inline;"><hr></div>&nbsp;参数 三 列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
      </div>
      <table width="60%" border="0" cellspacing="1" cellpadding="1" >
       </tr>
            <tr>
            <td align="center">数据库IP：</td>
            <td align="center">
            	<input type="text" name="dbip" value="<%=form.getDbip()%>"/>
            </td>
           
            </tr>
            <tr>
            <td align="center">数据库名称：</td>
            <td align="center">
            	<input type="text" name="dbname" value="<%=form.getDbname()%>"/>
            </td>
           
            </tr>
       			<tr>
	            <td align="center">连接用户名：</td>
	            <td align="center">
	            	<input type="text" name="dbuser" value="<%=form.getDbuser()%>"/>
	            </td>
	             
           
            </tr>
            <tr>
            	<td align="center">连接密码：</td>
	            <td align="center">
	            	<input type="text" name="dbpass" value="<%=form.getDbpass()%>"/>
	            </td>
	          
           
            </tr>
           
      			<tr>
      				<td align="center" colspan=2>
            	<input type="button" name="savejgday3" value="保存" onclick="savedb()"/>
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
    
    </td>
    <td background="../../images/img_13.gif">&nbsp;</td>
  </tr>

</table>

</form>
</body>
</html>
<script language="javascript">
	var path = '<%=path%>';

     document.form1.jgday.value='<%=form.getJgday()%>';
     var url = path+"/servlet/tabpara";
function savejgday(){
	url = path+"/servlet/tabpara?action=jgday";
	tijiao();
}
function savetab(){
	url = path+"/servlet/tabpara?action=tab";
	tijiao();
}

function savedb(){
	url = path+"/servlet/tabpara?action=db";
	tijiao();
}
function tijiao(){
	document.form1.action=url;
	document.form1.submit();
}
     </script>

