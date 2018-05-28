<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="java.util.Calendar" %>
<%
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
        String roleId = account.getRoleId();
        String accountid=account.getAccountName();
        AmmeterDegreeBean beann=new AmmeterDegreeBean();
        ArrayList listt=new ArrayList();
        String db=request.getParameter("ammeterid");
        System.out.println("电表id："+db);
        listt=beann.selectXS(db);
        String linelosstype="",linelossvalue="";
        int fycount = ((Integer)listt.get(0)).intValue();
        for( int k = fycount;k<listt.size()-1;k+=fycount){
		   
		    linelosstype = (String)listt.get(k+listt.indexOf("LINELOSSTYPE"));
		    linelosstype = linelosstype != null ? linelosstype : "";
		    
		    linelossvalue = (String)listt.get(k+listt.indexOf("LINELOSSVALUE"));
		    linelossvalue = linelossvalue != null ? linelossvalue : "";
		    		    
        }
        System.out.println(linelosstype+"wwwwwwwwwww");
	    System.out.println(linelossvalue+"eeeeeeeeeee");
       
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
	function saveDegree(){
		
	       var ad2_bz="";//AuditDegree2状态标志
	        var ad2_bz1="";//AuditDegree5状态标志
	       if(document.form1.lastad.value != document.form1.lastdegree.value){
	         //alert(document.form1.lastad.value);
	         ad2_bz="1";
	       }
	       if(document.form1.lastadtime1.value != document.form1.lastdatetime.value){
	         //alert(document.form1.lastadtime1.value);
	         //alert(document.form1.lastdatetime.value);
	         ad2_bz1="1";
	       }
	       
                  if(checkNotnull(document.form1.lastdegree,"上次电表读数")&&checkNotnull(document.form1.actualpay,"实际电费金额")&&checkNotnull(document.form1.startmonth,"起始年月")&&checkNotnull(document.form1.endmonth,"结束年月")&&checkNotnull(document.form1.thisdatetime,"本次抄表的时间")&&checkNotnull(document.form1.thisdegree,"本次电表读数")&&checkNotnull(document.form1.accountmonth,"报账月份")&&checkNotnull(document.form1.paydatetime,"交费时间")&&checkNotnull(document.form1.unitprice,"单价")&&checkNotnull(document.form1.lastdatetime,"上次抄表时间")){   
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
												                  if(isNaN(document.form1.actualpay.value)==false){
													                  if(sm<=em){
															                  if(tdt>=ldt){
																	                  if(confirm("您将要添加信息！确认信息正确！")){
																	                    document.form1.action=path+"/servlet/electricfees?action=addfd&ad2_bz="+ad2_bz;
																	                    
																	        			document.form1.submit()
																                   	 }
															                   }else{
															                   		alert("您输入的时间信息有误，本次抄表时间必须大于等于上次抄表时间 ！");
															                   }
													                   }else{
													                   			alert("您输入的时间信息有误，起始年月必须小于等于结束年月！");
													                   }
											                   }else{
											        	 			alert("您输入的信息有误，实际电费金额必须为数字！");
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
										alert("您输入的当前时间有误，请确认后重新输入！");
									}
								}else{
									alert("您输入的开票时间有误，请确认后重新输入！");
								}
							}else{
									alert("您输入的票据时间有误，请确认后重新输入！");
							}       	
			}
	}
        function showIdList(){
        
               window.open('showAmmeterIdList.jsp','','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        
         function getHaodianliang(){
           
        	 var bl=document.form1.mpower.value;
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
        		
        		 if(linelossvalue==null||linelossvalue==""||linelossvalue=="0")linelossvalue="1";
        		 
        		document.form1.blhdl.value=((parseFloat(Number(thisdegree)-Number(lastdatetime))*parseFloat(Number(linelossvalue))+parseFloat(Number(floatdegree)))*bl).toFixed(2);
        	 }
        	  document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.blhdl.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2); 
        	        	 
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
        //计算结束年月
        function endmonthzq(payzq){
         var startmonth = document.form1.startmonth.value;
         if(payzq==""||payzq==null||payzq==0)payzq=1;
         var month=startmonth.substring(5,7)-1+payzq;
         var year=startmonth.substring(0,4)-0;
        // alert(year+"-"+month);
         if(month>12){
        	 year=(year+1);
        	 month=month-12;
         }
         if(month<10){
        	 month="0"+month;
         }
         var s;
         //alert(year+"-"+month);
          
         s=year+"-"+month;
         document.form1.endmonth.value=s;
        }
        
	$(function(){
        $("#saveBtn").click(function(){
		   saveDegree();
		});
        $("#resetBtn").click(function() {	
        	$("#form")[0].reset();
        });
         $("#liulan").click(function(){
		   showIdList();
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
	<LINK href="../../images/css.css" type="text/css" rel=stylesheet>
<form action="" name="form1" method="POST" id="form">
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
    <td width="10" height="500">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4"></td>
						</tr>
						<tr>
							<td colspan=3 width="600" background="<%=path%>/images/btt.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加电费单</span></td>
							
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
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
		<%		
		
			String ammeterid = request.getParameter("ammeterid");
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
			AmmeterDegreeFormBean beanad = new AmmeterDegreeFormBean();
			ElectricFeesFormBean  beanam = new ElectricFeesFormBean();
		    bean = bean.getAmmeterDegreeAllInfoa(ammeterid);//2014-11-13加a
		    beanad = beanad.getLastAmmeterDegree(ammeterid);
		    String jzpriceam = beanam.getJizhanPriceAm(ammeterid);
		    System.out.println(jzpriceam+"本次单价==========");
		    jzpriceam = jzpriceam != null ? jzpriceam : "";
		    String payzq = bean.getPrepayZq(ammeterid);
		    payzq = payzq != null ? payzq : "";
		    String area = bean.getProvinceid()+" "+bean.getCityid()+" "+bean.getCountryid();
		    area = area != null ? area : "";
		    String lastad = beanad.getLastdegree();
		    lastad = lastad != null ? lastad : "";
		    String lastadtime = beanad.getLastdatetime();
		    lastadtime = lastadtime != null ? lastadtime : "";
		    String lastadtime1 = "";
		    //日期转换
		    if(!lastadtime.equals("")){
             SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
             String str=lastadtime;
             Date dt=sdf.parse(str);
             Calendar rightNow = Calendar.getInstance();
             rightNow.setTime(dt);
             //rightNow.add(Calendar.YEAR,-1);//日期减1年
             //rightNow.add(Calendar.MONTH,3);//日期加3个月
             rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加1天
             Date dt1=rightNow.getTime();
             lastadtime1 = sdf.format(dt1);
            }
		    if(lastad.equals("")){
		       System.out.println("上次电表读数为空！");
		       AmmeterDegreeFormBean beanada = new AmmeterDegreeFormBean();
		       beanada = beanada.getStartAmmeterModfiyDegree(ammeterid);
		       lastad = beanada.getInitialdegree();
		       lastadtime = beanada.getInitialdate();
		       lastadtime1 = lastadtime;
		    }
        //System.out.println(lastadtime1);
       
		%>
		<tr><td>&nbsp;</td></tr>
		<tr bgcolor="F9F9F9">
           <td height="19" colspan="4">　<img src="../../images/v.gif" width="15" height="15" />基本信息</td>
       </tr>
       	<tr><td>&nbsp;</td></tr>
		<tr>
		 <input type="hidden" name="lastad" value="<%=lastad %>"  class="form" />
		 <input type="hidden" name="accountid" value="<%=accountid %>"  class="form" />
		 <input type="hidden" name="lastadtime1" value="<%=lastadtime1 %>"  class="form" />
		</tr>
       <tr>
                 <td height="19" bgcolor="#DDDDDD" width="15%"><div>站点名称：</div>
         </td>
         <td width="35	%"><input type="text" name="stationname"  readonly="true" value="<%=bean.getStationname() %>"  class="form" />

         </td>

      <tr>

      <tr>
      <td height="19" bgcolor="#DDDDDD" width="15%"><div>地区：</div>
         </td>
         <td><input type="text" name="area"  readonly="true" value="<%=area%>"  class="form" />
         </td>
         
      <td height="19" bgcolor="#DDDDDD"><div>集团报表类型：</div>
         </td>
         <td><input type="text" name="stationtypeid"   readonly="true" value="<%=bean.getStationtypeid() %>"  class="form" maxlength="11"/>
         </td>
                 
      </tr>
      
      <tr>
      <td height="19" bgcolor="#DDDDDD" width="15%"><div>是否标杆：</div>
         </td>
         <td><input type="text" name="isbenchmarkstation"   readonly="true" value="<%=bean.getIsbenchmarkstation() %>"  class="form" />
         </td>
         
      <td height="19" bgcolor="#DDDDDD"><div>站点别名：</div>
         </td>
         <td><input type="text" name="stationaliasname"   readonly="true" value="<%=bean.getStationaliasname() %>"  class="form" maxlength="11"/>
         </td>
         
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
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>电表ID：</div>
         </td>
         <td width="35%"><input type="text" name="ammeteridFk"  readonly="true" value="<%=bean.getAmmeterid() %>"  class="form" /><span class="style1">&nbsp;*</span>
           <img id="liulan" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png" style="cursor: pointer"/>
         </td>
 
      </tr>
      <tr>
				<td height="8%" bgcolor="#DDDDDD" width="15%"><div>电表用途：</div>
         </td>
         <td><input type="text" name="dbyt"  readonly="true" value="<%=bean.getAmmeteruse() %>"  class="form" maxlength="11"/>
         </td>
         	<td height="8%" bgcolor="#DDDDDD" width="15%"><div>电流类型：</div>
         </td>
         <td><input type="text" name="currenttype"  readonly="true" value="<%=bean.getElectriccurrenttype_ammeter() %>"  class="form" maxlength="11"/>
         </td>
      </tr>
      
      <tr>
         
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div>用电设备：</div>
         </td>
        <td><input type="text" name="ydsb"  readonly="true" value="<%=bean.getUsageofelectypeid_ammeter() %>"  class="form" maxlength="11"/>
         </td>
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div>初始读数：</div>
         </td>
         <td><input type="text" name="initialdegree"  readonly="true" value="<%=bean.getInitialdegree() %>"  class="form" maxlength="11"/>
         </td>
      </tr>
      
      <tr>
      <input type="hidden" name="linelossvalue" value="<%=linelossvalue %>"/>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div>初始使用时间：</div>
         </td>
        <td><input type="text" name="initialdate"  readonly="true" value="<%=bean.getInitialdate() %>"  class="form" maxlength="11"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div>倍率：</div>
         <td><input type="text" name="mpower"  readonly="true" value="<%=bean.getMultiplyingpower() %>"  class="form" maxlength="11"/>
         </td>	
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
                 <td height="19" bgcolor="#DDDDDD" width="15%"><div>上次电表读数：</div>
         </td>
         
         <td width="35%"><input type="text" name="lastdegree" value="<%=lastad %>" onChange="getHaodianliang()" class="form"/>
         </td>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>本次电表读数：</div>
         </td>
         <td><input type="text" name="thisdegree" value="" onChange="getHaodianliang()"  class="form" />
        <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
         

      <tr>

      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>上次抄表时间：</div>
         </td>
           <% 
         if(lastadtime.equals("0")){
         %>
         <td><input type="text" name="lastdatetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" maxlength="11"/> </td>
<% 
}else{
%>
 <td><input type="text" name="lastdatetime" value="<%=lastadtime1%>" onFocus="getDateString(this,oCalendarChs)" class="form" maxlength="11"/> </td>
       <%
       }
        %> 
        
         <td height="19" bgcolor="#DDDDDD"><div>本次抄表时间：</div>
         </td>
         <td><input type="text" name="thisdatetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form"  align="right"/>
         <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
         
      </tr>


      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>用电量调整：</div>
         </td>
         <td><input type="hidden" name="linelosstype" value="<%=linelosstype %>"><input type="text" name="floatdegree" value="0" onChange="getHaodianliang()" class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>用电量：</div>
         </td>
         <td><input type="text" name="actualdegree" value="0" readonly="readonly"  class="form" />
           </td>
      </tr>

          <tr>
      <td height="19" bgcolor="#DDDDDD"><div>抄表操作员：</div>
         </td>
         <td><input type="text" name="inputoperator" value=""  class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD" ><div>折算后用电量：</div>
         </td>
         <td><input type="text" name="blhdl" value="0" readonly="true" class="form" />
           </td>
      </tr>
     <tr>
      <td height="19" bgcolor="#DDDDDD"><div>起始年月：</div>
         </td>
         <td><input type="text" name="startmonth" value="" onFocus="getDatenyString(this,oCalendarChsny)" onpropertychange="endmonthzq(<%=payzq %>)" class="form" />
          <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>结束年月：</div>
         </td>
         <td><input type="text" name="endmonth" value="" onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
            <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
           </td>
      </tr>
    
      <tr>
         <td height="19" bgcolor="#DDDDDD" ><div>备注：</div>
         </td>
         <td><input type="text" size="20" name="memo" value=""  class="form" />
         </td>
          <td height="19" bgcolor="#DDDDDD"><div>当前时间：</div>
         </td>
         <td><input type="text" name="inputdatetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" />
           </td>
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
                 <td height="19" bgcolor="#DDDDDD" width="15%"><div>本次单价：</div>
         </td>
         <td width="35%"><input type="text" name="unitprice"  readonly="true" value="<%=jzpriceam %>" class="form" />
         </td>

      <td height="19" bgcolor="#DDDDDD" width="15%"><div>费用调整：</div>
         </td>
         <td><input type="text" name="floatpay" value="0" onChange="getHaodianliang()" class="form" />
         </td>
      </tr>

      <tr> 
      <td height="19" bgcolor="#DDDDDD"><div>实际电费金额：</div>
         </td>
         <td><input type="text" name="actualpay" value="" readonly="readonly" class="form" maxlength="11"/>

         </td>
         <td height="19" bgcolor="#DDDDDD"><div>票据时间：</div>
         </td>
         <td><input type="text" name="notetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" maxlength="11"/>

         </td>
         
      </tr>


      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>票据类型：</div>
         </td>
          <td>   
         <select name="notetypeid" style="width:155">
         		<option value="pjlx03">请选择</option>
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
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
	         </select>
	        
          </td>  
        
      <td height="19" bgcolor="#DDDDDD"><div>票据编号：</div>
         </td>
         <td><input type="text" name="noteno" value=""  class="form" />
         
         </td>
      </tr>

          <tr>
      <td height="19" bgcolor="#DDDDDD"><div>交费操作员：</div>
         </td>
         <td><input type="text" name="payoperator" value=""  class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>交费时间：</div>
         </td>
         <td><input type="text" name="paydatetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" />
         <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
           </td>
      </tr>
     <tr>
      <td height="19" bgcolor="#DDDDDD"><div>报账月份：</div>
         </td>
         <td><input type="text" name="accountmonth" value="" onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
         <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
          <td height="19" bgcolor="#DDDDDD" width="15%"><div>开票时间：</div>
         </td>
         <td><input type="text" name="kptime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" />
         </td>
        
      </tr>
      <tr>
    
         <td height="19" bgcolor="#DDDDDD"><div>备注：</div>
         </td>
         <td><input type="text" name="memo" value=""  class="form" />
           </td>
      </tr>
      <tr>
        <td colspan=4">
        <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:235px">
			<img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
			<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
		</div>
         <div id="resetBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:246px">
			 <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">重置</span>      
		</div>
         <div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:257px">
			 <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>      
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
      
</form>
</body>
</html>
<script type="text/javascript"> 

 document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.actualdegree.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2)</script>


