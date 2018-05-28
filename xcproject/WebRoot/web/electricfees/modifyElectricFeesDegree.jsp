<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="java.text.*"%>
<%
	    String path = request.getContextPath();
	    Account account = (Account)session.getAttribute("account");
        String roleId = account.getRoleId();
%>
<html>
<head>
<title>
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
 #id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
bttcn{color:white;font-weight:bold;}
 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
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
	function modifyDegree() {
	var ad2_bz = "";//AuditDegree2状态标志
	if (document.form1.lastad.value != document.form1.lastdegree.value) {
		//alert(document.form1.lastad.value);
		ad2_bz = "1";
	}
	var id = document.form1.ammeterdegreeidFk.value;
	var ii=document.form1.floatdegree.value;
	if(checkNotnull(document.form1.ammeteridFk,"电表ID")&&checkNotnull(document.form1.ammeterdegreeidFk,"电表流水ID")&&checkNotnull(document.form1.lastdegree,"上次电表读数")&&checkNotnull(document.form1.thisdegree,"本次电表读数")&&checkNotnull(document.form1.lastdatetime,"上次抄表时间")&&checkNotnull(document.form1.thisdatetime,"本次抄表时间")
	&&checkNotnull(document.form1.startmonth,"起始时间")&&checkNotnull(document.form1.endmonth,"结束时间")&&checkNotnull(document.form1.paydatetime,"交费时间")&&checkNotnull(document.form1.accountmonth,"报账月份")){	
	var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
	var ldt = document.form1.lastdatetime.value;
	var tdt = document.form1.thisdatetime.value;
	var ipt = document.form1.inputdatetime.value;
	var kpt = document.form1.kptime.value;
	var ntt = document.form1.notetime.value;
	var pdt = document.form1.paydatetime.value;
	var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
	var sm = document.form1.startmonth.value;
	var em = document.form1.endmonth.value;
	var am = document.form1.accountmonth.value;
		if(reg.exec(ntt)||ntt==""||ntt==null){
			if(reg.exec(kpt)||kpt==""||kpt==null){
				if(reg.exec(ipt)||ipt==""||ipt==null){
					if(reg.exec(ldt)){
						if(reg.exec(tdt)){
							if(reg1.exec(sm)){
								if(reg1.exec(em)){
									if(reg.exec(pdt)){
										if(reg1.exec(am)){						   
											   if(sm<=em){
													 if(tdt>=ldt){
															if(isNaN(document.form1.actualpay.value)==false){
																if (confirm("您将要修改电量电费信息！确认信息正确！")) {
																	document.form1.action = path
																			+ "/servlet/electricfees?action=modifyadfee&status=qmodify&ad2_bz="
																			+ ad2_bz + "&id=" + id;
																	document.form1.submit()
																}
															}else{
													          alert("您输入的信息有误，实际电费金额必须为数字！");
													         }
									                 }else{
									                 		alert("您输入的信息有误，本次抄表时间必须大于等于上次抄表时间 ！");
									                 }
								               }else{
								               			alert("您输入的信息有误，起始时间必须小于等于结束时间！");
								               }
								        }else{
								        	alert("您输入的报账月份有误，请确认后重新输入！");
								        }
								    }else{
								    	alert("您输入的交费时间有误，请确认后重新输入！");
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
					alert("您输入的抄表时间有误，请确认后重新输入！");
				}
			}else{
				alert("您输入的开票时间有误，请确认后重新输入！");
			}
		}else{
				alert("您输入的票据时间有误，请确认后重新输入！");
		}
	}
}
	  function getHaodianliang(){
        	 var bl=document.form1.beilv.value;
        	  if(bl==null||bl=="")bl="1"
        	 var thisdegree=document.form1.thisdegree.value;
             var lastdatetime=document.form1.lastdegree.value;
			 var floatdegree=document.form1.floatdegree.value;
			 var linelossvalue=document.form1.linelossvalue.value;
			 var actualdegree=document.form1.actualdegree.value;
			 document.form1.actualdegree.value=(parseFloat(Number(document.form1.thisdegree.value)-Number(document.form1.lastdegree.value))+parseFloat(Number(document.form1.floatdegree.value)));
        	 if(document.form1.linelosstype.value=="01xstz"){
        		document.form1.blhdl.value=((Number(thisdegree)-Number(lastdatetime)+Number(linelossvalue)+Number(floatdegree))*bl).toFixed(2);
        	 }
        	 else{       		
        		 if(linelossvalue==null||linelossvalue=="")linelossvalue="1";
        		document.form1.blhdl.value=((parseFloat(Number(thisdegree)-Number(lastdatetime))*parseFloat(Number(linelossvalue))+parseFloat(Number(floatdegree)))*bl).toFixed(2);
        	 }
        	   document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.blhdl.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2); 
        	
        	 
        	 
        	 
        	 
        	        	 
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
 $(function(){
			
			$("#resetBtn").click(function(){
				$.each($("form input[type='text']"),function(){
					$(this).val("");
				})
			});
			$("#baocun").click(function(){
				modifyDegree();
			});
			$("#cancelBtn").click(function(){
				window.history.back();
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
    <td width="10"></td>
    <td valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="2"></td>
      </tr>
      
    </table></td>
    <td width="12"></td>
  </tr>

	<tr>
    <td>&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4"></td>
						</tr>
						<tr>
							<td colspan=3 width="600" background="<%=path%>/images/btt2.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电量电费信息</span>
							</td>
							
							<td width="380">&nbsp;</td>
						</tr>
					</table>
					<br/>
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

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
		<%
		   //电费信息查询
			String degreeid = request.getParameter("degreeid");//电费id
			ElectricFeesFormBean bean = new ElectricFeesFormBean();
		    bean = bean.getElectricDegreeFeesInfo(degreeid);
		   //电量数据处理
		    AmmeterDegreeFormBean beanad = new AmmeterDegreeFormBean();
		    beanad = beanad.getLastAmmeterModfiyDegree(bean.getAmmererid(),bean.getAmmeterdegreeid());
		    String lastad = beanad.getLastdegree();
		    if(lastad.equals("")){
		       System.out.println("上次电表读数为空！");
		       AmmeterDegreeFormBean beanada = new AmmeterDegreeFormBean();
		       beanada = beanada.getStartAmmeterModfiyDegree(bean.getAmmererid());
		       lastad = beanada.getInitialdegree();
		    }
		    
		    
		    DecimalFormat mat=new DecimalFormat("0.00");
            double df=Double.parseDouble(bean.getActualpay());
            String actualpay=mat.format(df);
		    
            AmmeterDegreeBean beann=new AmmeterDegreeBean();
            ArrayList listt=new ArrayList();
           // String db=request.getParameter("ammeteridFk");
            String db=bean.getAmmererid();
            System.out.println("电表id："+db);
            listt=beann.selectXSX(db);
            String linelosstype="",linelossvalue="";
            String beilv="";
            int fycount = ((Integer)listt.get(0)).intValue();
            for( int k = fycount;k<listt.size()-1;k+=fycount){
    		   
    		    linelosstype = (String)listt.get(k+listt.indexOf("LINELOSSTYPE"));
    		    linelosstype = linelosstype != null ? linelosstype : "";
    		    
    		    beilv = (String)listt.get(k+listt.indexOf("BEILV"));
    		    beilv = beilv != null ? beilv : "";
    		    
    		    linelossvalue = (String)listt.get(k+listt.indexOf("LINELOSSVALUE"));
    		    linelossvalue = linelossvalue != null ? linelossvalue : "";
    		    		    
            }
            System.out.println(linelosstype+"wwwwwwwwwww");
    	    System.out.println(linelossvalue+"eeeeeeeeeee");
            
            
		%>
			
        <tr>
		 <input type="hidden" name="lastad" value="<%=lastad %>"  class="form" />
		 <input type="hidden" name="linelosstype" value="<%=linelosstype %>">
		 <input type="hidden" name="linelossvalue" value="<%=linelossvalue %>"/>
		 <input type="hidden" name="beilv" value="<%=beilv %>"/>
		 
		</tr>
     
      <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>电表ID：</div>
         </td>
         <td width="35%"><input type="text" name="ammeteridFk" readonly="true" value="<%=bean.getAmmererid()%>"  class="form" /><span class="style1">&nbsp;*</span>
        </td>
 
      </tr>

      <tr>
                 <td height="19" bgcolor="#DDDDDD" width="15%"><div>上次电表读数：</div>
         </td>
         <td width="35	%"><input type="text" name="lastdegree" value="<%=bean.getLastdegree() %>" onChange="getHaodianliang()"  class="form" />

         </td>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>本次电表读数：</div>
         </td>
         <td><input type="text" name="thisdegree" value="<%=bean.getThisdegree() %>" onChange="getHaodianliang()"  class="form" /><span class="style1">&nbsp;*</span>
         </td>
         

 

      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>上次抄表时间：</div>
         </td>
         <td><input type="text" name="lastdatetime" value="<%=bean.getLastdatetime() %>" onFocus="getDateString(this,oCalendarChs)" class="form" maxlength="11"/>

         </td>
         <td height="19" bgcolor="#DDDDDD"><div>本次抄表时间：</div>
         </td>
         <td><input type="text" name="thisdatetime" value="<%=bean.getThisdatetime() %>" onFocus="getDateString(this,oCalendarChs)" class="form"  align="right"/>
<span class="style1">&nbsp;*</span>        
</td>
         
      </tr>


      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>用电量调整：</div>
         </td>
         <td><input type="text" name="floatdegree" value="<%=bean.getFloatdegree()%>" onChange="getHaodianliang()"  class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>用电量：</div>
         </td>
         <td><input type="text" name="actualdegree" value="<%=bean.getActualdegree() %>" readonly="true" class="form" />
           </td>
      </tr>

       <tr>
         <td height="19" bgcolor="#DDDDDD"><div>折算后电量：</div></td>
         <td><input type="text" name="blhdl"  readonly="true" value="<%=bean.getBlhdl() %>"  class="form" /></td>
         <td height="19" bgcolor="#DDDDDD"><div>抄表时间：</div></td>
         <td><input type="text" name="inputdatetime" value="<%=bean.getInputdatetime() %>" onFocus="getDateString(this,oCalendarChs)" class="form" /></td>
      </tr>
     <tr>
      <td height="19" bgcolor="#DDDDDD"><div>起始时间：</div>
         </td>
         <td><input type="text" name="startmonth" value="<%=bean.getStartmonth() %>" onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
        <span class="style1">&nbsp;*</span>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>结束时间：</div>
         </td>
         <td><input type="text" name="endmonth" value="<%=bean.getEndmonth() %>" onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
          <span class="style1">&nbsp;*</span>
           </td>
      </tr>
    
      <tr>
         <td height="19" bgcolor="#DDDDDD" ><div>备注：</div></td>
         <td><input type="text" size="20" name="memo" value="<%=bean.getMemo() %>"  class="form" /></td>
         <td height="19" bgcolor="#DDDDDD"><div>抄表操作员：</div></td>
         <td><input type="text" name="inputoperator" value="<%=bean.getInputoperator() %>"  class="form" /></td>
         <td><input type="hidden" size="50" name="autoauditstatus" value="<%=bean.getAutoauditstatus() %>"  class="form" /></td>
         <td><input type="hidden" size="50" name="autoauditdescription" value="<%=bean.getAutoauditdescription() %>"  class="form" /></td>
      </tr>           
       <tr>
          <td height="15" colspan="4"></td>
      </tr>
      <tr>
          <td height="3" colspan="2"></td>
      </tr>
      <tr>
          <td height="15" colspan="4"></td>
      </tr>             
      <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>电量流水ID：</div>
         </td>
         <td width="35%"><input type="text" name="ammeterdegreeidFk" value="<%=bean.getAmmeterdegreeidFk() %>"  class="form" /><span class="style1">&nbsp;*</span>
         </td>
          <td height="19" bgcolor="#DDDDDD" width="15%"><div>费用调整：</div>
         </td>
         <td><input type="text" name="floatpay" value="<%=bean.getFloatpay() %>" onChange="javascript:document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.actualdegree.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2)" class="form" />
         </td>
      </tr>

      <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>本次单价：</div>
         </td>
         <td width="35	%"><input type="text" readonly="true" name="unitprice" value="<%=bean.getUnitprice() %>" onchange="javascript:document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.actualdegree.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2)" class="form" />

         </td>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>开票时间：</div>
         </td>
         <td><input type="text" name="kptime" value="<%=bean.getKptime() %>" onFocus="getDatenyString(this,oCalendarChs)" class="form" />
         </td>
     
      <tr>

      <tr> 
      <td height="19" bgcolor="#DDDDDD"><div>实际电费金额：</div>
         </td>
         <td><input type="text" name="actualpay" value="<%=actualpay %>"  class="form" maxlength="11"/>

         </td>
        <td height="19" bgcolor="#DDDDDD"><div>票据时间：</div>
         </td>
         <td><input type="text" name="notetime" value="<%=bean.getNotetime() %>" onFocus="getDateString(this,oCalendarChs)" class="form" maxlength="11"/>

         </td> 
         
      </tr>


      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>票据类型：</div>
         </td>
         <td>
         <select name="notetypeid" style="width:150">
         		<option value="0">请选择</option>
	         <%
	         	ArrayList feetypelist = new ArrayList();
	         	feetypelist = commBean.getNoteType();
	         	if(feetypelist!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)feetypelist.get(0)).intValue();
	         		for(int i=scount;i<feetypelist.size()-1;i+=scount)
                    {
                    	sfid=(String)feetypelist.get(i+feetypelist.indexOf("CODE"));
                    	sfnm=(String)feetypelist.get(i+feetypelist.indexOf("NAME"));
                    	if((bean.getNotetypeid()).equals(sfid)){
                    %>
                    <option selected="selected" value="<%=sfid%>"><%=sfnm%></option>
                    <%
                       }else{
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%
                       }
                    }
	         	}
	         %>
	         </select>
         </td>
      <td height="19" bgcolor="#DDDDDD"><div >票据编号：</div>
         </td>
         <td><input type="text" name="noteno" value="<%=bean.getNoteno() %>"  class="form" />
         </td>
      </tr>

          <tr>
      <td height="19" bgcolor="#DDDDDD"><div>交费操作员：</div>
         </td>
         <td><input type="text" name="payoperator" value="<%=bean.getPayoperator() %>"  class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>交费时间：</div>
         </td>
         <td><input type="text" name="paydatetime" value="<%=bean.getPaydatetime() %>" onFocus="getDateString(this,oCalendarChs)"  class="form" />
           <span class="style1">&nbsp;*</span>
           </td>
      </tr>
     <tr>
      <td height="19" bgcolor="#DDDDDD"><div>报账月份：</div>
         </td>
         <td><input type="text" name="accountmonth" value="<%=bean.getAccountmonth() %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form" />
         <span class="style1">&nbsp;*</span>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>备注：</div>
         </td>
         <td><input type="text" name="memo" value="<%=bean.getMemo() %>"  class="form" />
           </td>
      </tr>
      </table>
      <br/>
      <table width="100%" border="0" cellspacing="1" cellpadding="1">
      <tr>

                <td colspan="4" align="right">

         <div id="cancelBtn" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 230px">
				<img src="<%=path%>/images/quxiao.png" width="100%"
					height="100%">
				<span
					style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
			</div>

			<div id="resetBtn"
				style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 240px">
				<img src="<%=path%>/images/2chongzhi.png" width="100%"
					height="100%">
				<span
					style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
			</div>
			<div id="baocun"
				style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:250px">
				<img alt=""
					src="<%=request.getContextPath()%>/images/baocun.png"
					width="100%" height="100%" />
				<span
					style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">保存</span>
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
      
<input type="hidden" name = "ammeterdegreeid" value="<%=degreeid%>"/>

</td>
</tr>
</table>
</form>

</body>
</html>

