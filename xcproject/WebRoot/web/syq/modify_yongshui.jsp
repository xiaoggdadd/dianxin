﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.syq.SyqForm" %>
<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	String nhid = request.getParameter("id");
	String pagelx = request.getParameter("pagelx");
	
		String pageTitle = "";
	if(pagelx.equals("02")){
	pageTitle = "用水";
}else if(pagelx.equals("03")){
	pageTitle = "柴油";
}else if(pagelx.equals("04")){
	pageTitle = "汽油";
}else if(pagelx.equals("05")){
	pageTitle = "天然气";
	}
	SyqForm form = new SyqForm();
	form.getData(nhid);
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
  <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/syq.js"></script>

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
	function saveAccount(){


          	if(checkNotnull(document.form1.yuef,"月份")&&
          			checkNotnull(document.form1.bzh,"标志号")&&
          			checkNotnull(document.form1.nhsl,"能耗数量")&&
          			checkNotnull(document.form1.tzsl,"调整数量")&&
          			checkNotnull(document.form1.sjsl,"实际数量")&&
          			checkNotnull(document.form1.danjia,"单价")&&
          			checkNotnull(document.form1.nhje,"能耗金额")&&
          			checkNotnull(document.form1.tzje,"调整金额")&&
          			checkNotnull(document.form1.sjje,"实际金额")
          	){
          		if(document.form1.nhlx.value=="0"){
          			alert("请选择能耗类型");
          			return;
          		}
          		if(document.form1.nhyt.value=="0"){
          			alert("请选择能耗用途");
          			return;
          		}
          			addzhandian();

              }
        	}
        	
        	function addzhandian(){
        		if(confirm("您将要修改"+'<%=pageTitle%>'+"能耗数据！确认信息正确！")){
          		document.form1.action=path+"/servlet/syq?action=mys";
							document.form1.submit();
            }
        	}

       
       
</script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="../../images/css.css" type="text/css" rel=stylesheet>
<form action="" name="form1" method="POST">
  
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan=1 width="400" background="<%=path%>/images/btt.bmp" height=40><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 修改用水能耗</span></td>
							
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
      <tr>
         
         <td height="8%" bgcolor="#DDDDDD" width="60"><div align="left">月份：</div>
         </td>
         <td width="150">
         <input type="text" name="yuef" value="<%=form.getYuef()%>" onfocus="setMonth(this);" class="form"/><span class="style1">&nbsp;*</span>
         </td>
			<td height="8%" bgcolor="#DDDDDD" width="60"><div align="left">标志号：</div>
         </td>
         <td width="150">
         <input type="text" name="bzh" value="<%=form.getBzh()%>"   class="form"/><span class="style1">&nbsp;*</span>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="60"><div align="left">型号：</div>
         </td>
         <td width="150">
         <input type="text" name="xinghao" value="<%=form.getXinghao()%>"   class="form"/>
         </td>
      </tr>
      
      <tr>
         <td height="8%" bgcolor="#DDDDDD" ><div align="left">能耗类型：</div>
         </td>
         <td >
         	<select name="nhlx" style="width:130" >
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdtype = new ArrayList();
	         	zdtype = ztcommon.getSelctOptions("NHLX");
	         	if(zdtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdtype.get(0)).intValue();
	         		for(int i=cscount;i<zdtype.size()-1;i+=cscount)
                    {
                    	code=(String)zdtype.get(i+zdtype.indexOf("CODE"));
                    	name=(String)zdtype.get(i+zdtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         </td>
         <td height="8%" bgcolor="#DDDDDD" ><div align="left">能耗用途：</div>
         </td>
         <td >
         	<select name="nhyt" style="width:130">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = ztcommon.getSelctOptions("NHYT");
	         	if(zdsx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdsx.get(0)).intValue();
	         		for(int i=cscount;i<zdsx.size()-1;i+=cscount)
                    {
                    	code=(String)zdsx.get(i+zdsx.indexOf("CODE"));
                    	name=(String)zdsx.get(i+zdsx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         		
        
         	</select><span class="style1">&nbsp;*</span>
         	</td>
			<td height="8%" bgcolor="#DDDDDD" ><div align="left">站点ID：</div>
         </td>
         <td >
         		<input type="text" name="zdname" value="<%=form.getZdname()%>"  class="form" />
         		<input type="button" name="baocun1" value="浏览" onclick="liulan()" id="id1"  style="color:#014F8A"/>
         		<input type="button" name="baocun1" value="清空站点" onclick="qingkong()" id="id1"  style="color:#014F8A"/>
         </td>
      </tr>
      
      <tr>
         <td height="8%" bgcolor="#DDDDDD" ><div align="left">能耗数量：</div>
         </td>
         <td ><input type="text" name="nhsl" value="<%=form.getNhsl()%>"  class="form" /><span class="style1">&nbsp;*</span>
         	
         </td>
         <td height="8%" bgcolor="#DDDDDD" ><div align="left">调整数量：</div>
         </td>
         <td ><input type="text" name="tzsl" value="<%=form.getTzsl()%>"  class="form" /><span class="style1">&nbsp;*</span></td>
				<td height="8%" bgcolor="#DDDDDD" ><div align="left">实际数量：</div>
         </td>
         <td ><input type="text" name="sjsl" value="<%=form.getSjsl()%>"  class="form" /><span class="style1">&nbsp;*</span></td>
      </tr>
     
      <tr>
      	<td height="8%" bgcolor="#DDDDDD" ><div align="left">单价：</div>
         </td>
         <td >
         <input type="text" name="danjia" value="<%=form.getDanjia()%>"  class="form" /><span class="style1">&nbsp;*</span>
         </td>
         <td height="8%" bgcolor="#DDDDDD" ><div align="left">能耗金额：</div>
         </td>
         <td >
         	<input type="text" name="nhje" value="<%=form.getNhje()%>"  class="form" /><span class="style1">&nbsp;*</span>
         </td>
        <td height="8%" bgcolor="#DDDDDD" ><div align="left">调整金额：</div>
         </td>
         <td ><input type="text" name="tzje" value="<%=form.getTzje()%>"  class="form" /><span class="style1">&nbsp;*</span></td>
      </tr>
    
      <tr>
         <td height="8%" bgcolor="#DDDDDD" ><div align="left">实际金额：</div>
         </td>
         <td >
         	<input type="text" name="sjje" value="<%=form.getSjje()%>"  class="form" /><span class="style1">&nbsp;*</span></td>
         </td>
         <td height="8%" bgcolor="#DDDDDD" ><div align="left">起始数：</div>
         </td>
         <td >
         	<input type="text" name="qss" value="<%=form.getQss()%>"  class="form"/>
         </td>
			<td height="8%" bgcolor="#DDDDDD" ><div align="left">结束数：</div>
         </td>
         <td ><input type="text" name="jss" value="<%=form.getJss()%>"  class="form" />
         </td>
      </tr>
    
   
    
      <tr>
         <td height="8%" bgcolor="#DDDDDD" ><div align="left">起始日期：</div>
         </td>
         <td >
         	<input type="text" name="bdate" value="<%=form.getBdate()%>"  onFocus="getDateString(this,oCalendarChs)" class="form"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" ><div align="left">结束日期：</div>
         </td>
         <td >
         	<input type="text" name="edate" value="<%=form.getEdate()%>" onFocus="getDateString(this,oCalendarChs)"  class="form"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" ><div align="left">收费人：</div>
         </td>
         <td ><input type="text" name="sfr" value="<%=form.getSfr()%>"   class="form"/>
         </td>
        
      </tr>
      <tr>
      	
         <td height="8%" bgcolor="#DDDDDD" ><div align="left">收费日期：</div>
         </td>
         <td >
         	<input type="text" name="sfdate" value="<%=form.getSfdate()%>" onFocus="getDateString(this,oCalendarChs)"  class="form"/>
         </td>
         
    
				         <td height="8%" bgcolor="#DDDDDD" ><div align="left">票据类型：</div>
				         </td>
				         <td >
				         	<select name="pjlx" style="width:130">
				         		<option value="0">请选择</option>
				         		<%
					         	ArrayList pjlx = new ArrayList();
					         	pjlx = ztcommon.getSelctOptions("PJLX");
					         	if(zdsx!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)pjlx.get(0)).intValue();
					         		for(int i=cscount;i<pjlx.size()-1;i+=cscount)
				                    {
				                    	code=(String)pjlx.get(i+pjlx.indexOf("CODE"));
				                    	name=(String)pjlx.get(i+pjlx.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         %>
				         		
				        
				         	</select>
				         </td>
				         <td height="8%" bgcolor="#DDDDDD" ><div align="left">票据号：</div>
				         </td>
				         <td ><input type="text" name="pjh" value="<%=form.getPjh()%>"  class="form" /></td>
				           </tr>
  
      				<tr>
								<td height="8%" bgcolor="#DDDDDD" ><div align="left">开票日期：</div>
				         </td>
				         <td ><input type="text" name="kpdate" value="<%=form.getKpdate()%>"  class="form" onFocus="getDateString(this,oCalendarChs)"/>
				         	</td>
				      </tr>
				      <tr>
				         <td height="8%" bgcolor="#DDDDDD" ><div align="left">备注：</div>
				         </td>
				         <td colspan=5>
				         		<input type="text" name="memo" value="<%=form.getMemo()%>"  class="form" size=100/>
				         </td>
				       
				      </tr>
				  
     
    <tr>
        <td colspan="6">
        	<div align="center">
          <input type="button" name="baocun1" value="保存" onclick="saveAccount()"  />
          &nbsp;&nbsp;
          <input type="button" name="baocun1" value="返回" onclick="javascript:history.go(-1)"  />
          &nbsp;&nbsp;
          <input type="reset" name="chongzhi0" value="重置"  />
        </div>
        </td>
      </tr>



      <%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>

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
<input type="hidden" name="czzdid" value="<%=form.getZdid()%>"/>
<input type="hidden" name="id" value="<%=nhid%>"/>
<input type="hidden" name="pagelx" value="<%=pagelx%>"/>
<input type="hidden" name="zdid" value="<%=form.getZdid()%>"/>
</form>
</body>
</html>
<script type="text/javascript">
<!--

	//XMLHttpReq = createXMLHttpRequest();

	///////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数


		function liulan(){
		//window.open("zhandianselect.jsp");
		//return;
		var url=path+"/web/jizhan/zhandianselect.jsp"
		//window.open(url);
		//return;
			var obj = new Object();
			obj.mid='mid';
		    var obj=showModalDialog(url,obj,'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no');	
			document.form1.zdid.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.zdname.value=obj.substring(0,obj.indexOf(","));
			
	}
	function qingkong(){
		document.form1.zdid.value="";
		document.form1.zdname.value="";
	}
	document.form1.nhlx.value='<%=form.getNhlx()%>';
	document.form1.nhyt.value='<%=form.getNhyt()%>';
	document.form1.pjlx.value='<%=form.getPjlx()%>';
//-->
</script>

