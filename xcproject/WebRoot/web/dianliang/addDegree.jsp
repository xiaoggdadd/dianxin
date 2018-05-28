<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
        String roleId = account.getRoleId();
%>
<html>
<head>
<title>
addDegree
</title>
<style>
            
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px

		}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.form{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
}


bttcn{color:white;font-weight:bold;}
 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
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
<!-- 年月日期控件 -->
<script >

var oCalendarEnny=new PopupCalendarny("oCalendarEnny");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();


var oCalendarChsny=new PopupCalendarny("oCalendarChsny");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChsny.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChsny.oBtnTodayTitle="确定";
oCalendarChsny.oBtnCancelTitle="取消";
oCalendarChsny.Init();

function CalendarTodayClick(oCell,oInstance)				/// "Today" button Change
{	
   	var sDay,sMonth,sYear,newDate
	sYear=oInstance.currDate.getFullYear();
	sMonth=oInstance.currDate.getMonth();
	sDay=oInstance.currDate.getDate();	
	sDateString=sYear+oInstance.separator+CalendarDblNum(sMonth+1);		///return sDateString
	if(oInstance.oTaget.tagName.toLowerCase()=="input")oInstance.oTaget.value = sDateString;
	CalendarCancel(oInstance);
	
	sMonth += 2;
	if(sMonth>12){
		sYear++;
		sMonth = 1;
	}
	sDateString=sYear+oInstance.separator+CalendarDblNum(sMonth);
	$("#endmonthId").val(sDateString);
}
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
	function saveDegree(){
			if(checkNotnull(document.form1.ammeteridFk,"电表ID")&&checkNotnull(document.form1.startmonth,"起始年月")&&checkNotnull(document.form1.endmonth,"结束年月")
					&&checkNotnull(document.form1.thisdatetime,"本次抄表的时间")
					&&checkNotnull(document.form1.thisdegree,"本次电表读数")){
				 var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
                 var tdt = document.form1.thisdatetime.value;
                 var ldt = document.form1.lastdatetime.value;
                 var ipt = document.form1.inputdatetime.value;
                 var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
                 var em = document.form1.endmonth.value;
                 var sm = document.form1.startmonth.value;
                 if((reg.exec(ipt)||ipt==""||ipt==null)){
                 	if(reg.exec(ldt)||ldt==""||ldt==null){
                 		if(reg.exec(tdt)){
                 			if(reg1.exec(sm)){
                 				if(reg1.exec(em)){
						                 if(ldt<=tdt){
						                	if(em>=sm){
						                		if(isNaN(document.form1.blhdl.value)==false){
									                  if(confirm("您将要添加电量信息！确认信息正确！")){
									                    document.form1.action=path+"/servlet/ammeterdegree?action=add"
									        			document.form1.submit()
									                  }
									             }else{
								                  alert("您输入的信息有误，折算后用电量必须为数字！");
								                 }
								             }else {
								                   alert("您输入的信息有误，结束年月必须大于等于起始年月！");
								             }
						                 }else{
						                     alert("您输入的信息有误，本次抄表的时间必须大于等于上次抄表的时间！");
						                 }
								 }else{
								 	alert("您输入的结束时间有误，请确认后重新输入！");
								 }
							}else{
								alert("您输入的起始时间有误，请确认后重新输入！");
							}
						}else{
							alert("您输入的本次抄表时间有误，请确认后重新输入！");
						}
					}else{
						alert("您输入的上次抄表时间有误，请确认后重新输入！");
					}
			     }else{
			     	alert("您输入的当前时间有误，请确认后重新输入！");
			     }								                  
		     }

	}
        function vName(){
         	var accName = document.form1.accountName.value;
                 if(accName==""){
           	alert("不能为空！")
                   return
          }
               window.open('accountName.jsp?accountId=0&accountName='+accName,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        function vCode(){
          var accCode = document.form1.workSn.value;
          if(accCode==""){
           	alert("不能为空！")
                   return
          }
               window.open('accountCode.jsp?accountId=0&accountCode='+accCode,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }

        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！")
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        
        function showIdList(){
        
               window.open('showAmmeterIdList.jsp','','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
       function fanhui(){        
              document.form1.action=path+"/web/dianliang/dianliangList.jsp";
              document.form1.submit();
        }
          $(function(){
			
			$("#resetBtn").click(function(){
				$.each($("form input[type='text']"),function(){
					$(this).val("");
				})
			});
			$("#baocun").click(function(){
				saveDegree();
			});
			$("#liulan11").click(function(){
				showBMList();
			});
			$("#jiancha").click(function(){
				vName();
			});
			$("#cancelBtn").click(function(){
				fanhui();
			});
			$("#liulan").click(function(){
				showIdList();
			});
		});
</script>
</head>
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean">
</jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	
<form action="" name="form1" method="POST">
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan=3 width="600" height=63>
			<div style="width:700px;height:50px">
		       
		       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:18px;color:black">添加电量</span>	
			</div></td>
   </tr>
   <tr bgcolor="F9F9F9">
     <td height="19" colspan="4">　<img src="../../images/v.gif" width="15" height="15" /><font size="2">   &nbsp;  基本信息</font></td>
   </tr></table>
                    
   <table width="100%" border="0" cellspacing="1" cellpadding="1" class="form_label">          
      <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>电表ID：</div></td>
         <td width="35%"><input type="text" name="ammeteridFk" value="" readonly="true" class="form" /><span class="style1">&nbsp;*</span>
           <img id="liulan" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png" style="cursor: pointer；width:15px;height: 15px;"/>
         </td>
      </tr>

      <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>上次电表读数：</div></td>
         <td width="35	%"><input type="text" name="lastdegree" value=""  class="form" />

         </td>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>本次电表读数：</div>
         </td>
         <td><input type="text" name="thisdegree" value=""  class="form" />
         <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
         </tr>

      

      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>上次抄表时间：</div>
         </td>
         <td><input type="text" name="lastdatetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" maxlength="11"/>

         </td>
         <td height="19" bgcolor="#DDDDDD"><div>本次抄表时间：</div>
         </td>
         <td><input type="text" name="thisdatetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form"  align="right"/>
            <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
         
      </tr>


      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>用电量调整：</div>
         </td>
         <td><input type="text" name="floatdegree" value=""  class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>实际用电量：</div>
         </td>
         <td><input type="text" name="actualdegree" value=""  class="form" />
           </td>
      </tr>

          <tr>
      <td height="19" bgcolor="#DDDDDD"><div>抄表操作员：</div>
         </td>
         <td><input type="text" name="inputoperator" value=""  class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>当前时间：</div>
         </td>
         <td><input type="text" name="inputdatetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" />
           </td>
      </tr>
     <tr>
      <td height="19" bgcolor="#DDDDDD"><div>起始年月：</div>
         </td>
         <td><input type="text" id="startmonthId" name="startmonth" value="" onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
         <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>结束年月：</div>
         </td>
         <td><input type="text" id="endmonthId" name="endmonth" value="" onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
          <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
           </td>
      </tr>
    
      <tr>
         <td height="19" bgcolor="#DDDDDD" ><div>备注：</div>
         </td>
         <td colspan="2"><input type="text" size="50" name="memo" value=""  class="form" />
         </td>
      </tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="1">
      <tr>
        <td colspan="4" align="right">

         <div id="cancelBtn" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 260px;top:23;">
				<img src="<%=path%>/images/quxiao.png" width="100%"
					height="100%">
				<span
					style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
			</div>

			
			
        </td>
      </tr>


      </table>
</form>
</body>
</html>

