<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
    String roleId = account.getRoleId();
    String accountname=account.getAccountName();
    String aid=request.getParameter("ammeterid");
    

    AmmeterDegreeFormBean beanm=new AmmeterDegreeFormBean();
    ArrayList listt=new ArrayList();
    listt=beanm.getStationMhchexkt(aid);
    String shuoshuzhuanye="";
    String dbili="",dbili1="",dbili2="",dbili3="",dbili4="",dbili5="",dbili6="",zh="";
    if(listt!=null)
{
	int fycount = ((Integer)listt.get(0)).intValue();
	for( int k = fycount;k<listt.size()-1;k+=fycount){
	     shuoshuzhuanye = (String)listt.get(k+listt.indexOf("SHUOSHUZHUANYE"));
    shuoshuzhuanye = shuoshuzhuanye != null ? shuoshuzhuanye : "";
    dbili = (String)listt.get(k+listt.indexOf("DBILI"));
    dbili = dbili != null ? dbili : "0";
    if(shuoshuzhuanye.equals("zylx01")){
       dbili1=dbili;
    }
     if(shuoshuzhuanye.equals("zylx02")){
      dbili2=dbili;
    
    }
     if(shuoshuzhuanye.equals("zylx03")){
    
      dbili3=dbili;
    }
     if(shuoshuzhuanye.equals("zylx04")){
      dbili4=dbili;
    
    }
     if(shuoshuzhuanye.equals("zylx05")){
      dbili5=dbili;
    
    }
     if(shuoshuzhuanye.equals("zylx06")){
      dbili6=dbili;
    
    }
    }
    }

    if(dbili1==null||dbili1.equals("null")||dbili1.equals("")||dbili1==""){
    	dbili1="0";
    	}
    	if(dbili2==null||dbili2.equals("null")||dbili2.equals("")||dbili2==""){
    	dbili2="0";
    	}
    	if(dbili3==null||dbili3.equals("null")||dbili3.equals("")||dbili3==""){
    	dbili3="0";
    	}
    	if(dbili4==null||dbili4.equals("null")||dbili4.equals("")||dbili4==""){
    	dbili4="0";
    	}  
    	if(dbili5==null||dbili5.equals("null")||dbili5.equals("")||dbili5==""){
    	dbili5="0";
    	}
    	if(dbili6==null||dbili6.equals("null")||dbili6.equals("")||dbili6==""){
    	dbili6="0";
    	}
    	 double hj=0.0;
    	DecimalFormat mat7 =new DecimalFormat("0.0000000");
    	System.out.println(dbili1+" "+dbili2+" "+dbili3+" "+dbili4+" "+dbili5+" "+dbili6);
    	hj=Double.parseDouble(dbili1)+Double.parseDouble(dbili2)+Double.parseDouble(dbili3)+Double.parseDouble(dbili4)+Double.parseDouble(dbili5)+Double.parseDouble(dbili6);
    	System.out.println(Double.parseDouble(dbili1)+"  "+Double.parseDouble(dbili2)+"  "+Double.parseDouble(dbili3)+" "+Double.parseDouble(dbili4)+" "+Double.parseDouble(dbili5)+" "+Double.parseDouble(dbili6));

    	zh= mat7.format(hj);

    	System.out.println("zh-:"+zh);
    	String bzw="1";
    	if(!zh.equals("100.0000000")){
    	bzw="2";
    	}
    	//获取分摊详细信息
    	String dbili9="",sszy="",qcb="",kjkm="",zymx="",xjbl="";
    	String bzw9="";
    	ArrayList listtxjbl=new ArrayList();
    	listtxjbl=beanm.getStationMhchexktxjbl(aid);
    	System.out.println("listtxjbl:"+listtxjbl);
    	if(listtxjbl!=null)
    	{
    	int fycountxjbl = ((Integer)listtxjbl.get(0)).intValue();
    	for( int kk = fycountxjbl;kk<listtxjbl.size()-1;kk+=fycountxjbl){

    	dbili9 = (String)listtxjbl.get(kk+listtxjbl.indexOf("DBILI"));
    	dbili9 = dbili9 != null ? dbili9 : "";
    	System.out.println("dbili9："+dbili9);

    	sszy = (String)listtxjbl.get(kk+listtxjbl.indexOf("SHUOSHUZHUANYE"));
    	sszy = sszy != null ? sszy : "";
    	System.out.println("sszy："+sszy);
    	qcb = (String)listtxjbl.get(kk+listtxjbl.indexOf("QCBCODE"));
    	qcb = qcb != null ? qcb : "";
    	System.out.println("qcb："+qcb);
    	kjkm = (String)listtxjbl.get(kk+listtxjbl.indexOf("KJKMCODE"));
    	kjkm = kjkm != null ? kjkm : "";
    	System.out.println("kjkm："+kjkm);

    	zymx = (String)listtxjbl.get(kk+listtxjbl.indexOf("ZYMXCODE"));
    	zymx = zymx != null ? zymx : "";
    	System.out.println("zymx："+zymx);
    	xjbl = (String)listtxjbl.get(kk+listtxjbl.indexOf("XJBILI"));
    	xjbl = xjbl != null ? xjbl : "";
    	System.out.println("xjbl："+xjbl);
    	//  dbili9="",sszy="",qcb="",kjkm="",zymx="",xjbl="";
    	if("".equals(dbili9)||"".equals(sszy)||"".equals(kjkm)||"".equals(qcb)||"".equals(zymx)||"".equals(xjbl)){
    	bzw9="0";
    	}
    	}
    	}

    	System.out.println("标志位："+bzw9);

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
		line-height:100%;
}
.selected_font1{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
		border-left-width:0px; border-top-width:0px; border-right-width:0px;
}
.selected_font2{
		width:130px;
		text-align: right;
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
 </style>
 <script>
window.onload=function(){

var bz='<%=bzw9%>';
var fthj='<%=zh%>';
if(fthj!="100.0000000"||bz=="0"){
alert("该电表没有做分摊或者分摊有异常，请到监测点管理进行修改");
return;

}
			
	}
</script>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
  <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script>
window.onload=function(){

var bz='<%=bzw9%>';
var fthj='<%=zh%>';
if(fthj!="100.0000000"||bz=="0"){
alert("该电表没有做分摊或者分摊有异常，请到监测点管理进行修改");
return;
	
}
			
	}
</script>
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
function change(){
var a=document.form1.startmonth.value;// stmon
var b=document.form1.stmon.value;
var reg2 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
 if(reg2.exec(a)){
 if(b>a){
 alert("您输入的起始年月小于系统的起始年月！");
document.form1.startmonth.value=b;
 return;
 }
 }else{
 alert("您输入的起始年月有误，请确认后重新输入！");
 document.form1.startmonth.value=b;
 return;
 }
}



	function saveDegree(){	
	
	  var std=document.form1.pjje.value;
	  if(""==std||null==std){std="0";}
		var ad2_bz="";

                  if(checkNotnull(document.form1.startmonth,"起始年月")&&
                     checkNotnull(document.form1.endmonth,"结束年月")&&
                     checkNotnull(document.form1.accountmonth,"报账月份")&&
                     checkNotnull(document.form1.money,"金额")&&
                     checkNotnull(document.form1.htbianhao,"合同编号")&&
                     checkNotSelected(document.form1.notetypeid,"票据类型")&&
			   		 checkNotnull(document.form1.pjje,"票据金额")&&
                     checkNotnull(document.form1.dfzflx,"电费支付类型")){
                       var moneyqk = document.form1.money.value.replace(/[ ]/g,"");
                       var moneyqk1 = document.form1.pjje.value.replace(/[ ]/g,"");
                       if((isNaN(document.form1.money.value)==false)&& moneyqk!=""){
                         if((isNaN(document.form1.pjje.value)==false)&& moneyqk1!=""){
                       		var moneyn=parseFloat(document.form1.money.value).toFixed(2);
                       		var dbili1n=parseFloat(document.form1.networkdf.value);
				        	var dbili2n=parseFloat(document.form1.marketdf.value);
				        	var dbili3n=parseFloat(document.form1.administrativedf.value);
				        	var dbili4n=parseFloat(document.form1.informatizationdf.value);
				        	var dbili5n=parseFloat(document.form1.builddf.value);
				        	var dbili6n=parseFloat(document.form1.dddf.value);
                       		if(Math.abs(moneyn-parseFloat(dbili1n+dbili2n+dbili3n+dbili4n+dbili5n+dbili6n).toFixed(2))<=0.02){
                       		   var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
                       		   var ntt = document.form1.notetime.value;
                       		   var kpt = document.form1.kptime.value;
                       		   
                       		   var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
		                       var qsny = document.form1.startmonth.value;
		                       var jsny = document.form1.endmonth.value;
		                       var am = document.form1.accountmonth.value;
		                       		if(reg1.exec(qsny)){	
								        if(reg1.exec(jsny)){
								        	if(isDate(kpt)==true||kpt==""||kpt==null){
								        		if(isDate(ntt)==true||ntt==""||ntt==null){
								        			if(reg1.exec(am)){               
								                       if(qsny<=jsny){
								                       if(isNaN(std)==false){                     
									                           if(confirm("您将要添加信息！确认信息正确！")){
									                             document.form1.action=path+"/servlet/bargain?action=addfd&ad2_bz="+ad2_bz;
									        			         document.form1.submit();
									        			         	showdiv("请稍等..............");
									                           }
									                           }else{
									                           alert("您输入的信息有误，票据金额必须为数字！");
									                           }
										               }else{
										               alert("您输入的信息有误，结束年月必须大于等于起始年月！");
										               }
										             }else{
										             	alert("您输入的报账月份有误，请确认后重新输入！");
										             }
										         }else{
										         	alert("您输入的票据时间有误，请确认后重新输入！");
										         }
										     }else{
										     	alert("您输入的开票时间有误，请确认后重新输入！");
										     }
										}else{
											alert("您输入的结束年月有误，请确认后重新输入！");
										} 
						            }else{
						            	alert("您输入的起始年月有误，请确认后重新输入！");
						            }
						       }else{
                       			alert("总金额与分摊金额与不符，请确认！");
                       		  } 
                       	  }else{
                       		alert("您输入的信息有误，票据金额必须为数字且不能输入空格！");
                         } 	   
                       }else{
                       		alert("您输入的信息有误，金额必须为数字且不能输入空格！");
                       }   
                  }else{
                  		return false;
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
        function showIdList(){
        
               window.open('showBargainList.jsp','','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
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
        function fanhui(){        
               document.form1.action=path+"/web/electricfees/bargainDanInput.jsp";
               document.form1.submit();
        }
        
        
        function getHaodianliang(){
        
          // alert("-===");
        	// var bl=document.form1.mpower.value;
        	// if(bl==null||bl=="")bl="1"
        	var dbili1=document.form1.dbili1.value;
        	var dbili2=document.form1.dbili2.value;
        	var dbili3=document.form1.dbili3.value;
        	var dbili4=document.form1.dbili4.value;
        	var dbili5=document.form1.dbili5.value;
        	var dbili6=document.form1.dbili6.value;
        	var money= document.form1.money.value;
        	if(isNaN(document.form1.money.value)==false){}
        	 //生产
        	 if((dbili1 !="")&&isNaN(document.form1.dbili1.value)==false){
        		document.form1.networkdf.value=((parseFloat(dbili1)/100)*parseFloat(money)).toFixed(2);
 			}else{
 				document.form1.networkdf.value="0.00";
 			}
        	  //销售
        	if((dbili2 !="")&&isNaN(document.form1.dbili2.value)==false){
        	 	document.form1.marketdf.value=((parseFloat(dbili2)/100)*parseFloat(money)).toFixed(2);	
        	}else{
        	 	document.form1.marketdf.value="0.00";
 			}
        	 // ADMINISTRATIVEDF
        	 if((dbili3 !="")&&isNaN(document.form1.dbili3.value)==false){
        	 	document.form1.administrativedf.value=((parseFloat(dbili3)/100)*parseFloat(money)).toFixed(2);
        	 }else{
        	 	document.form1.administrativedf.value="0.00";
 			}
        	  
        	 // INFORMATIZATIONDF
        	 if((dbili4 !="")&&isNaN(document.form1.dbili4.value)==false){	
        	 	document.form1.informatizationdf.value=((parseFloat(dbili4)/100)*parseFloat(money)).toFixed(2);
        	 }else{
        	 	document.form1.informatizationdf.value="0.00";
 			 }
        	 // BUILDDF
        	 if((dbili5 !="")&&isNaN(document.form1.dbili5.value)==false){	
        	 	document.form1.builddf.value=((parseFloat(dbili5)/100)*parseFloat(money)).toFixed(2); 
        	 }else{
        	 	document.form1.builddf.value="0.00";
 			 }     
 			 
 			 // 第六条专业线 代垫电费
        	 if((dbili6 !="")&&isNaN(document.form1.dbili6.value)==false){	
        	 	document.form1.dddf.value=((parseFloat(dbili6)/100)*parseFloat(money)).toFixed(2); 
        	 }else{
        	 	document.form1.dddf.value="0.00";
 			 }   	        	 
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
         
		$("#cancelBtn1").click(function(){
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
<% 
//如果一级分摊不为100不显示
if(bzw.equals("1")&&!bzw9.equals("0")){
%>
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
	<tr>
    <td width="10" height="500">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4"></td>
						</tr>
						<tr>
							<td colspan="4" width="50" >
								<div style="width:700px;height:50px">
									
									<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:18px;color:black">添加合同单</span>
									<input type="hidden" name="accountname"  value="<%=accountname %>">	
								</div>
						   </td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
						<tr>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1" class="form_label">

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
		<%		
			String ammeterid = request.getParameter("ammeterid");
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
			AmmeterDegreeFormBean beanad = new AmmeterDegreeFormBean();
			AmmeterDegreeFormBean beanendmonth = new AmmeterDegreeFormBean();
			ElectricFeesFormBean  beanam = new ElectricFeesFormBean();
		    bean = bean.getAmmeterDegreeAllInfoa(ammeterid);//2014-11-13加a
		    beanendmonth=beanendmonth.getEndmonthOne(ammeterid);
		    String endmonth=beanendmonth.getEndmonth();
		    if(null==endmonth||"null".equals(endmonth)){
		     endmonth="";
		    }
		         System.out.println("最大月份a："+endmonth);
		       if(!endmonth.equals("")&&null!=endmonth){
             SimpleDateFormat sdfa=new SimpleDateFormat("yyyy-MM");
             String stra=endmonth;
             Date dta=sdfa.parse(stra);
             Calendar rightNowa = Calendar.getInstance();
             rightNowa.setTime(dta);
             //rightNow.add(Calendar.YEAR,-1);//日期减1年
            rightNowa.add(Calendar.MONTH,1);//日期加3个月
            // rightNowa.add(Calendar.DAY_OF_YEAR,1);//日期加1天
             Date dt1a=rightNowa.getTime();
             endmonth = sdfa.format(dt1a);
            }
            System.out.println("最大月份b："+endmonth);
		    
		    beanad = beanad.getLastAmmeterDegree(ammeterid);		    
		    String jzpriceam = beanam.getJizhanPriceAm(ammeterid);
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
            //格式化单价
            String dianfei = bean.getDianfei();
            if(dianfei==null||dianfei.equals("")||dianfei.equals("null")||dianfei.equals("o")){
				dianfei="0.0000";
            }	
            DecimalFormat mat =new DecimalFormat("0.0000");           
	        dianfei=mat.format(Double.parseDouble(dianfei));
	        
	       
		    
		    
		%>
		<tr bgcolor="F9F9F9">
           <td height="19" colspan="4">　<img src="../../images/v.gif" width="15" height="15" />基本信息</td>
        </tr>
       	<tr><td>&nbsp;</td></tr>
		<tr><td>
			 <input type="hidden" name="lastad" value="<%=lastad %>"  class="form" />
			 <input type="hidden" name="lastadtime1" value="<%=lastadtime1 %>"  class="form" />
			 <input type="hidden" name="stationid" value="<%=bean.getStationid() %>"  class="form" />
		</td></tr>
       <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>站点名称：</div></td>
         <td width="35%"><input type="text" name="stationname" readonly="true" value="<%=bean.getStationname() %>"  class="selected_font1" /></td>                
       <td height="19" bgcolor="#DDDDDD"><div>集团报表类型：</div></td>
         <td><input type="text" name="stationtypeid" readonly="true" value="<%=bean.getStationtypeid() %>" class="selected_font1" maxlength="11"/></td>               
      </tr>

      <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>地区：</div></td>
         <td><input type="text" name="area" readonly="true" value="<%=area%>"  class="selected_font1" /></td> 
        <td height="19" bgcolor="#DDDDDD"><div>站点别名：</div></td>
         <td><input type="text" name="stationaliasname" readonly="true" value="<%=bean.getStationaliasname() %>"  class="selected_font1" maxlength="11"/></td>     
      </tr>
      
      <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>是否标杆：</div></td>
         <td><input type="text" name="isbenchmarkstation" readonly="true" value="<%=bean.getIsbenchmarkstation() %>" class="selected_font1"/></td>   
           <td height="19" bgcolor="#DDDDDD" width="15%"><div>合同编号：</div></td>
         <td width="35%"><input type="text" name="htbianhao" id="htbianhao" class="selected_font" />
          <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>  
          </td> 
         
      </tr>
       
      <tr>
          <td height="15" colspan="4"></td>
      </tr>
       
     <tr>
     <%
     String sss=bean.getInitialdate();
     if(sss==null){
    	 sss="";
     }
     %>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>电表ID：</div></td>
         <td width="35%">
               <input type="text" name="ammeteridFk" readonly="true" value="<%=bean.getAmmeterid() %>" class="selected_font1" />&nbsp;<span class="style1">&nbsp;*</span>
               <img id="liulan" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png" style="cursor: pointer;width:15px;height:15px;"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div>初始使用时间：</div></td>
         <td><input type="text" name="initialdate" readonly="true" value="<%=sss %>"  class="selected_font1" maxlength="11"/></td>
         </tr>  
      <tr>
	     <td height="8%" bgcolor="#DDDDDD" width="15%"><div>电表用途：</div></td>
         <td><input type="text" name="dbyt" readonly="true" value="<%=bean.getAmmeteruse() %>" class="selected_font1" maxlength="11"/></td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div>电流类型：</div></td>
         <td><input type="text" name="currenttype" readonly="true" value="<%=bean.getElectriccurrenttype_ammeter() %>" class="selected_font1" maxlength="11"/></td>
      </tr>
      
      <tr>         
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div>单价：</div></td>
         <td><input type="text" name="danjia" readonly="true" value="<%=dianfei%>"  class="selected_font2" maxlength="11"/></td>
		 <td height="19" bgcolor="#DDDDDD" width="15%"><div>金额：</div></td>
         <td width="35%"><input type="text" name="money" value="" onChange="getHaodianliang()" class="selected_font3" />
         <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
      </tr>
      <tr>         
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div>用电设备：</div></td>
         <td><input type="text" name="ydsb" readonly="true" value="<%=bean.getUsageofelectypeid_ammeter() %>"  class="selected_font1" maxlength="11"/></td>
        <td height="19" bgcolor="#DDDDDD"><div>票据金额：</div>
         </td>
         <td><input type="text" name="pjje" value="<%=request.getParameter("money")!=null?request.getParameter("pjje"):""%>"  class="selected_font3" />
         <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td> 
      </tr>
     <tr>
          <td height="15" colspan="4"></td>
      </tr>

      <tr>
         <td height="19" bgcolor="#DDDDDD"><div>起始年月：</div></td>
         <td><input type="text" name="startmonth" value="<%=endmonth%>"   onpropertychange="change()";    onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font" />
         	  <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>结束年月：</div></td>
         <td><input type="text" name="endmonth" value="" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font" />
             <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
      </tr>
      <tr> 
         <td height="19" bgcolor="#DDDDDD"><div>票据时间：</div></td>
         <td><input type="text" name="notetime" value="" onFocus="getDateString(this,oCalendarChs)" class="selected_font" maxlength="11"/></td>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>开票时间：</div></td>
         <td><input type="text" name="kptime" value="" onFocus="getDateString(this,oCalendarChs)" class="selected_font" /></td>
      </tr>


      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>票据类型：</div>
         </td>
          <td>   
         <select name="notetypeid"  class="selected_font">
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
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
	         </select><span style="color: #FF0000;font-weight: bold">&nbsp;&nbsp;*</span>
          </td>  
        
      <td height="19" bgcolor="#DDDDDD"><div>电费支付类型：</div></td>

       <td><input type="text" name="dfzflx" value="<%=bean.getDfzflx() %>" class="selected_font" readonly="true"/></td>   

      </tr>    
     <tr>
         <td height="19" bgcolor="#DDDDDD"><div>报账月份：</div></td>
         <td><input type="text" name="accountmonth" value="" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font"/>
         <span style="color: #FF0000;font-weight: bold">&nbsp;*</span></td>
         <td height="19" bgcolor="#DDDDDD"><div>备注：</div></td>
         <td><input type="text" name="memo" value="" class="selected_font"/></td>        
      </tr>
       <tr bgcolor="F9F9F9">
           <td height="19" colspan="4">　<img src="../../images/v.gif" width="15" height="15" />分摊信息</td>
       </tr> 
       <tr>
         <td height="19" bgcolor="#DDDDDD"><div>生产：</div></td>
         <td><input type="text" name="networkdf" value="" class="selected_font3"/></td>
         <td height="19" bgcolor="#DDDDDD"><div>办公：</div></td>
         <td><input type="text" name="administrativedf" value="" class="selected_font3"/></td>        
      </tr>
       <tr>
         <td height="19" bgcolor="#DDDDDD"><div>营业：</div></td>
         <td><input type="text" name="marketdf" value="" class="selected_font3"/></td>
         <td height="19" bgcolor="#DDDDDD"><div>信息化支撑：</div></td>
         <td><input type="text" name="informatizationdf" value="" class="selected_font3"/></td>  
         
             
      </tr>
      <tr>
         <td height="19" bgcolor="#DDDDDD"><div>建设投资：</div></td>
         <td><input type="text" name="builddf" value="" class="selected_font3"/></td>     
          <td height="19" bgcolor="#DDDDDD"><div>代垫电费：</div></td>
         <td><input type="text" name="dddf" value="" class="selected_font3"/></td>         
         <td ><input type="hidden" name="dbili1" value="<%=dbili1%>"></td>
         <td ><input type="hidden" name="dbili2" value="<%=dbili2%>"></td>
         <td ><input type="hidden" name="dbili3" value="<%=dbili3%>"></td>
         <td ><input type="hidden" name="dbili4" value="<%=dbili4%>"></td>
         <td ><input type="hidden" name="dbili5" value="<%=dbili5%>"></td>
         <td ><input type="hidden" name="dbili6" value="<%=dbili6%>"></td>      
          <td ><input type="hidden" name="stmon" value="<%=endmonth%>"></td>              
      </tr>
      <tr>
        <td colspan=4">
        <div id="cancelBtn1" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:250px;top:10px;">
			<img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
			<span style="font-size: 12px; position: absolute; left: 27px; top: 3px; color: white">返回</span>
		</div>
         <div id="resetBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:260px;top:10px;">
			 <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:3px;color:white">重置</span>      
		</div>
         <div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:270px;top:10px;">
			 <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:3px;color:white">保存</span>      
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
 </td></tr></table>  
 <%} %>   
</form>
</body>

</html>


