
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="java.util.Calendar" %>
<%
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
        String roleId = account.getRoleId();
        String accountname=account.getAccountName();
        
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
	color: red;
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
.selected_font1{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
		border-left-width:0px; border-top-width:0px; border-right-width:0px;
}
.selected_font3{
		width:130px;
		text-align: right;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
bttcn{color:white;font-weight:bold;}
.form    {width:130px}
 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
 
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
 <script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script >

//var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
//oCalendarEn.Init();


//var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
//oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
//oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
//oCalendarChs.oBtnTodayTitle="今天";
//oCalendarChs.oBtnCancelTitle="取消";
//oCalendarChs.Init();
</script>
<!-- 年月日期控件 -->
<script >

//var oCalendarEnny=new PopupCalendarny("oCalendarEnny");	//初始化控件时,请给出实例名称如:oCalendarEn
//oCalendarEnny.Init();


//var oCalendarChsny=new PopupCalendarny("oCalendarChsny");	//初始化控件时,请给出实例名称:oCalendarChs
//oCalendarChsny.weekDaySting=new Array("日","一","二","三","四","五","六");
//oCalendarChsny.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
//oCalendarChsny.oBtnTodayTitle="确定";
//oCalendarChsny.oBtnCancelTitle="取消";
//oCalendarChsny.Init();
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
		var edhdl =  document.form1.edhdl.value;
		var zlfh = document.form1.zlfh.value;
		var jlfh = document.form1.jlfh.value;
		var scb = document.form1.scb.value;
		var property = document.form1.property.value;
		var qsdbdl = document.form1.qsdbdl.value;
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
	        if(0== Number(edhdl)){
				alert("系统中的额定耗电量是空值,请先填写额定耗电量！！！");	
			}else{
				if(0==Number(qsdbdl)){
					alert("系统中的全省定标电量是空值，请先填写全省定标电量！！！");	
				}else{
				if(0==Number(zlfh)){
					alert("系统中的直流负荷是空值，请先填写直流负荷！！！");	
				}else{
					if(0==Number(jlfh)){
						alert("系统中的交流负荷是空值，请先填写交流负荷！！！");	
					}else{
						if(0==Number(scb)){
							alert("系统中的生产标是空值，请先填写生产标！！！");	
						}else{
							if(property==""||property=="null"){
								alert("系统中的站点属性为空值，请先填写站点属性！！！");	
							}else{
	           if(checkNotnull(document.form1.startmonth,"起始年月")&&checkNotnull(document.form1.endmonth,"结束年月")&&checkNotnull(document.form1.thisdatetime,"本次抄表的时间")&&checkNotnull(document.form1.lastdatetime,"上次抄表的时间")&&checkNotnull(document.form1.thisdegree,"本次电表读数")){
				 var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
                 var tdt = document.form1.thisdatetime.value;
                 var ldt = document.form1.lastdatetime.value;
                 var ipt = document.form1.inputdatetime.value;
                 var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
                 var em = document.form1.endmonth.value;
                 var sm = document.form1.startmonth.value;
                 if((isDate(ipt)==true||ipt==""||ipt==null)){
                 	if(isDate(ldt)==true){
                 		if(isDate(tdt)==true){
                 			if(reg1.exec(sm)){
                 				if(reg1.exec(em)){
						                 if(ldt<=tdt){
						                	if(em>=sm){
						                		if(isNaN(document.form1.blhdl.value)==false){
							                         if(confirm("您将要添加电量信息！确认信息正确！")){
								                        document.form1.action=path+"/servlet/ammeterdegree?action=add&ad2_bz="+ad2_bz+"&ad2_bz1="+ad2_bz1;
								        			    document.form1.submit()
								        			    showdiv("请稍等..............");
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
			}
		}
	}
}
}

	}
		function isDate(str){
 var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
 if(reg.test(str)){
       return true;
  }else{
  
 return false;
 
 }
 }
	  function getHaodianliang(){
		     var thisdegree=document.form1.thisdegree.value;
             var lastdatetime=document.form1.lastdegree.value;
			 var floatdegree=document.form1.floatdegree.value;
			 var linelossvalue=document.form1.linelossvalue.value;
			 var actualdegree=document.form1.actualdegree.value;
		 
        	 var bl=document.form1.beilv.value;
        	  if(bl==null||bl=="")bl="1"
			 document.form1.actualdegree.value=(parseFloat(Number(document.form1.thisdegree.value)-Number(document.form1.lastdegree.value))+parseFloat(Number(document.form1.floatdegree.value)));
        	 if(document.form1.linelosstype.value=="01xstz"||document.form1.linelosstype.value=="0"){	
        		document.form1.blhdl.value=((Number(thisdegree)-Number(lastdatetime)+Number(linelossvalue)+Number(floatdegree))*bl).toFixed(2);
        	 }
        	 else{
        		
        		 if(linelossvalue==null||linelossvalue=="")linelossvalue="1";
        		document.form1.blhdl.value=((parseFloat(Number(thisdegree)-Number(lastdatetime))*parseFloat(Number(linelossvalue))+parseFloat(Number(floatdegree)))*bl).toFixed(2);
        	 }
          }
		function getHaodianliangc(){
		     var thisdegree=document.form1.thisdegree.value;
             var lastdatetime=document.form1.lastdegree.value;
			 var floatdegree=document.form1.floatdegree.value;
			 var linelossvalue=document.form1.linelossvalue.value;
			 var actualdegree=document.form1.actualdegree.value;
			 var bl=document.form1.beilv.value;
        	 if(null==bl||""==bl)bl="1"
        	 // alert(parseFloat(Number(document.form1.thisdegree.value)-Number(document.form1.lastdegree.value))*(-1));
			    document.form1.actualdegree.value=(parseFloat(Number(document.form1.thisdegree.value)-Number(document.form1.lastdegree.value)))+parseFloat(Number(document.form1.floatdegree.value));
        	 if(document.form1.linelosstype.value=="01xstz"){	
        		document.form1.blhdl.value=(((Number(thisdegree)-Number(lastdatetime))+Number(linelossvalue)+Number(floatdegree))*bl).toFixed(2);
        	 }
        	 else{
        		
        		 if(linelossvalue==null||linelossvalue=="")linelossvalue="1";
        		document.form1.blhdl.value=(((parseFloat(Number(thisdegree)-Number(lastdatetime))*parseFloat(Number(linelossvalue)))+parseFloat(Number(floatdegree)))*bl).toFixed(2);
        	 }
		  }
       
        function showIdList(){
        
               window.open('showAmmeterIdList.jsp','','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
       function endmonthzq(payzq){
    	 var startmonth = document.form1.startmonth.value;
    	 if(payzq==null||payzq==""||payzq==" "){
    		 payzq=1;
    	 }
         var month=startmonth.substring(5,7)-1+payzq;
         var year=startmonth.substring(0,4)-0;
         //alert(year+"-"+month);
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
        function fanhui(){        
               document.form1.action=path+"/web/dianliang/addDegree.jsp";
               document.form1.submit();
        }
         $(function(){
			
			$("#resetBtn").click(function(){
				$("#form")[0].reset();
			});
			$("#baocun").click(function(){
				saveDegree();
				
			});
			$("#showid").click(function(){
				showIdList();
			});
			$("#cancelBtn").click(function(){
				fanhui();
			});
		});
</script>
</head>
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean">
</jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	
<form action="" name="form1" method="POST" id="form">
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
  	<tr>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
						<tr>
							<td colspan=3 width="600" height=63>
								<div style="width:700px;height:50px">
							       
							       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:18px;color:black">添加电量</span>	
								</div></td>
					   </tr>
					</table>
				</td>
			</tr>
						<tr>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" >
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
		<%		
			String ammeterid = request.getParameter("ammeterid");
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
			AmmeterDegreeFormBean beanad = new AmmeterDegreeFormBean();
		    bean = bean.getAmmeterDegreeAllInfoa(ammeterid);//2014-11-13加a
		    beanad = beanad.getLastAmmeterDegreeGl(ammeterid);
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
		    String stationtype  = bean.getStationtypecode();
		    AmmeterDegreeBean beann=new AmmeterDegreeBean();
            ArrayList listt=new ArrayList();
           // String db=request.getParameter("ammeteridFk");
            String db=bean.getAmmeterid();
            listt=beann.selectXSX(db);
            String linelosstype="",linelossvalue="";
            String beilv="",zlfh = "",jlfh = "",property = "",edhdl = "",scb = "",shi = "",qsdbdl = "";
            int fycount = ((Integer)listt.get(0)).intValue();
            for( int k = fycount;k<listt.size()-1;k+=fycount){
    		   
    		    linelosstype = (String)listt.get(k+listt.indexOf("LINELOSSTYPE"));
    		    linelosstype = linelosstype != null ? linelosstype : "";
    		    
    		    beilv = (String)listt.get(k+listt.indexOf("BEILV"));
    		    beilv = beilv != null ? beilv : "";
    		    
    		    linelossvalue = (String)listt.get(k+listt.indexOf("LINELOSSVALUE"));
    		    linelossvalue = linelossvalue != null ? linelossvalue : "";
    		    
    		    zlfh = (String)listt.get(k+listt.indexOf("ZLFH"));
    		    zlfh = zlfh != null ? zlfh : "";
    		    jlfh = (String)listt.get(k+listt.indexOf("JLFH"));
    		    jlfh = jlfh != null ? jlfh : "";
    		    property = (String)listt.get(k+listt.indexOf("PROPERTY"));
    		    property = property != null ? property : "";
    		    edhdl = (String)listt.get(k+listt.indexOf("EDHDL"));
    		    edhdl = edhdl != null ? edhdl : "";
    		    scb = (String)listt.get(k+listt.indexOf("SCB"));
    		    scb = scb != null ? scb : "";
    		    shi = (String)listt.get(k+listt.indexOf("SHI"));
    		    shi = shi != null ? shi : "";	
    		    qsdbdl = (String)listt.get(k+listt.indexOf("QSDBDL"));
    		    qsdbdl = qsdbdl != null ? qsdbdl : "";	
            }
		%>
		<tr bgcolor="F9F9F9" class="selected_font">
           <td height="19" colspan="4">　<img src="../../images/v.gif" width="15" height="15" />基本信息</td>
       </tr>	
		<tr><td>
		  <input type="hidden" name="lastad" value="<%=lastad %>"  class="form" />
		  <input type="hidden" name="lastadtime1" value="<%=lastadtime1 %>"  class="form" />
		  <input type="hidden" name="linelosstype" value="<%=linelosstype %>">
		  <input type="hidden" name="linelossvalue" value="<%=linelossvalue %>"/>
		  <input type="hidden" name="beilv" value="<%=beilv %>"/>
		   <input type="hidden" name="accountname" value="<%=accountname %>"/>
		   <input type="hidden" name="zlfh" value="<%=zlfh %>"/>
		   <input type="hidden" name="jlfh" value="<%=jlfh %>"/>
		   <input type="hidden" name="property" value="<%=property %>"/>
		   <input type="hidden" name="edhdl" value="<%=edhdl %>"/>
		   <input type="hidden" name="scb" value="<%=scb %>"/>
			<input type="hidden" name="shi" value="<%=shi %>"/>
			<input type="hidden" name="qsdbdl" value="<%=qsdbdl %>"/>
			<input type="hidden" name="stationtype" value="<%=stationtype %>"/>
		</td></tr>
       <tr class="selected_font">
                 <td height="19" bgcolor="#DDDDDD" width="15%"><div>站点名称：</div>
         </td>
         <td width="35	%"><input type="text" name="stationname"  readonly="true"  value="<%=bean.getStationname() %>"  class="selected_font1"/>

         </td>

      </tr>

      <tr class="selected_font">
      <td height="19" bgcolor="#DDDDDD" width="15%"><div>地区：</div>
         </td>
         <td><input type="text" name="area" readonly="true"  value="<%=area%>"  class="selected_font1"/>
         </td>
         
      <td height="19" bgcolor="#DDDDDD"><div>集团报表类型：</div>
         </td>
         <td><input type="text" name="stationtypeid" readonly="true"  value="<%=bean.getStationtypeid() %>"  class="selected_font1" maxlength="11"/>
         </td>
                 
      </tr>
      
      <tr class="selected_font">
      <td height="19" bgcolor="#DDDDDD" width="15%"><div>是否标杆：</div>
         </td>
         <td><input type="text" name="isbenchmarkstation"  readonly="true" value="<%=bean.getIsbenchmarkstation() %>"  class="selected_font1" />
         </td>
         
      <td height="19" bgcolor="#DDDDDD"><div>站点别名：</div>
         </td>
         <td><input type="text" name="stationaliasname" readonly="true"  value="<%=bean.getStationaliasname() %>" class="selected_font1" maxlength="11"/>
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
       
     <tr class="selected_font">
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>电表ID：</div>
         </td>
         <td width="35%"><input type="text" name="ammeteridFk"  readonly="true" value="<%=bean.getAmmeterid() %>" readonly="true" class="selected_font1"/><span class="style1">&nbsp;*</span>
           <img id="showid" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png" style="cursor: pointer;width=15px;height=15px;"/>
         </td>
<%--          <td>           <div id="showid" style="width:21px;height:21px;cursor:pointer;float:right;position:relative;right:380px;">--%>
<%--	           <img src="<%=path %>/images/liulan1.png" width="100%" height="100%">--%>
<%--          </div></td>--%>
 
      </tr>

      <tr class="selected_font">
                 <td height="19" bgcolor="#DDDDDD" width="15%"><div>上次电表读数：</div>
         </td>
         <td width="35%"><input type="text" name="lastdegree" value="<%=lastad %>" onChange="getHaodianliang()" class="selected_font3"/>

         </td>
          <td height="19" bgcolor="#DDDDDD" width="15%"><div>本次电表读数：</div>
         </td>
         <%  
         if("插卡".equals(bean.getDfzflx())){
        %>
         <td><input type="text" name="thisdegree" value="" onChange="getHaodianliangc()" class="selected_font3"/>
        <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
        <% 	 
         }else{
         %>
       <td><input type="text" name="thisdegree" value="" onChange="getHaodianliang()" class="selected_font3"/>  
       <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>  
         <%
         }
         %>
        
         

      </tr>

      <tr class="selected_font">
      <td height="19" bgcolor="#DDDDDD"><div>上次抄表时间：</div>
         </td>
         <% 
         if("0".equals(lastadtime)){
         %>
         <td>
         <input type="text" name="lastdatetime" class="selected_font" 
         		value="<%if(null!=request.getParameter("lastdatetime")) out.print(request.getParameter("lastdatetime")); %>" 
				readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" maxlength="11"/>
          <span style="color: #FF0000;font-weight: bold">&nbsp;*</span></td>
		<% 
		}else{
		%>
 		<td>
 		<input type="text" name="lastdatetime" class="selected_font" 
         		value="<%=lastadtime1%>" 
				readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" maxlength="11"/>
 		<span style="color: #FF0000;font-weight: bold">&nbsp;*</span></td>
       <%
       }
        %> 
         <td height="19" bgcolor="#DDDDDD"><div>本次抄表时间：</div>
         </td>
         <td>
          <input type="text" name="thisdatetime" class="selected_font" 
         		value="<%if(null!=request.getParameter("thisdatetime")) out.print(request.getParameter("thisdatetime")); %>" 
				readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" align="right"/>
         <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
         
      </tr>


      <tr class="selected_font">
      
      <td height="19" bgcolor="#DDDDDD"><div>用电量调整：</div>
         </td>
          <%  
         if("插卡".equals(bean.getDfzflx())){
        %>
         <td><input type="text" name="floatdegree" value="0" onChange="getHaodianliangc()" class="selected_font3"/>
         </td>
         <% 	 
         }else{
         %>
          <td><input type="text" name="floatdegree" value="0" onChange="getHaodianliang()" class="selected_font3"/>
         </td>
         <%
         }
         %>
         <td height="19" bgcolor="#DDDDDD"><div>用电量：</div>
         </td>
         <td><input type="text" name="actualdegree" value="" readonly="true" class="selected_font3"/>
           </td>
      </tr>

          <tr class="selected_font">
      <td height="19" bgcolor="#DDDDDD"><div>抄表操作员：</div>
         </td>
         <td><input type="text" name="inputoperator" value="" class="selected_font"/>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>折算后用电量：</div>
         </td>
         <td><input type="text" name="blhdl" value="0" class="selected_font3"/>
           </td>
      </tr>
     <tr class="selected_font">
      <td height="19" bgcolor="#DDDDDD"><div>起始年月：</div>
         </td>
         <td>
         <input type="text" name="startmonth" class="selected_font" value="<%if(null!=request.getParameter("startmonth")) out.print(request.getParameter("startmonth")); %>" 
				readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" onpropertychange="endmonthzq(<%=payzq %>)" />
        <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>结束年月：</div>
         </td>
         <td><input type="text" name="endmonth" value="<%if(null!=request.getParameter("endmonth")) out.print(request.getParameter("endmonth")); %>"  
         			readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font"/>
           <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
           </td>
      </tr>
    
      <tr class="selected_font">
         <td height="19" bgcolor="#DDDDDD" ><div>备注：</div>
         </td>
         <td><input type="text" size="50" name="memo" value=""  class="selected_font"/>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>当前时间：</div>
         </td>
         <td>
         <input type="text" name="inputdatetime" class="selected_font" value="<%if(null!=request.getParameter("inputdatetime")) out.print(request.getParameter("inputdatetime")); %>" 
				readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
         </td>
      </tr>
      <tr class="selected_font">

         <td colspan="4" align="right">

         <div id="cancelBtn" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 260px;top:23px;">
				<img src="<%=path%>/images/quxiao.png" width="100%"
					height="100%">
				<span
					style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
			</div>

			<div id="resetBtn"
				style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 265px;top:23px;">
				<img src="<%=path%>/images/2chongzhi.png" width="100%"
					height="100%">
				<span
					style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
			</div>
			<div id="baocun"
				style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:270px;top:23px;">
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
		</table>
      <br />
</td></tr></table>  
</form>
</body>
</html>

