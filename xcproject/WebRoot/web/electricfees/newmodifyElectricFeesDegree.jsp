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
        String accountid=account.getAccountName();
        String biaozhi = request.getParameter("biaozhi");
%>
<html>
<head>
<title>
</title>
<style>
            
            .style1 {
	color: red;
	font-weight: bold;
}
.form{
   height:19px;
   width:130px;
}
.formright{
   text-align: right;
   height:19px;
   width:130px;
}
.form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;		
}
.formm{
   text-align: right;
   height:19px;
   width:80px;
}
.form_labell{
	font-family: 宋体;
	font-size: 12px;
	width:70px;
}
.form1{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;
   text-align: right;
   height:19px;
   width:130px;
}
.form2{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;
   height:19px;
   width:130px;
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
  <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
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
	//计算结束年月
function endmonthzq(){
          qsyear();
         var thisdatetime = document.form1.thisdatetime.value;
         var lastdatetime = document.form1.lastdatetime.value;
         var startmonth   = document.form1.startmonth.value;
         if(lastdatetime==null||lastdatetime==""){
           alert("您输入的信息不完善，上次抄表时间不能为空！")
         }else{
            var a="2012-5-3";
            var b=a.split("-");
            
           // if(payzq==""||payzq==null||payzq==0)payzq=1;
            var month = startmonth.substring(5,7)-0;
            var year  = startmonth.substring(0,4)-0;
            var bmonth=thisdatetime.substring(5,7)-0;
            var byear=thisdatetime.substring(0,4)-0;
            var bdate=thisdatetime.substring(8,10)-0;
            
            var smonth=lastdatetime.substring(5,7)-0;
            var syear=lastdatetime.substring(0,4)-0;
            var sdate=lastdatetime.substring(8,10)-0;
            
            var d1=new Date(syear,smonth,sdate);  
            var d2=new Date(byear,bmonth,bdate);
            var s=(d2.getTime()-d1.getTime ())/(1000*60*60*24);
            var days=Math.round(s);
            		if(days<1){
			  alert("您输入的信息有误，上次抄表时间不能大于或等于本次抄表时间！");
			}else{
  if(syear==byear){
    if(smonth==bmonth){
      if(month<10){
         month="0"+month;
	 year=byear;
       }
         var s;
         s=year+"-"+month;
         document.form1.endmonth.value=s; 
    }
    else{
       if(sdate<16){
         if(bdate<16){
          month=(bmonth-smonth)-1+smonth;
          if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
         }
         if(bdate>15){
           month=(bmonth-smonth)+smonth;
           if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
         }
       }
       if(sdate>15){
         if(bdate<16){
           month=(bmonth-smonth)+smonth;
           if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
         }
         if(bdate>15){
           month=(bmonth-smonth)+smonth;
           if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
         }
       }
    }
    
  }
  if(syear<byear){
     if(smonth==bmonth){ 
       if(month<10){
         month="0"+month;
	 year=byear;
       }
         var s;
         s=year+"-"+month;
         document.form1.endmonth.value=s; 
     }
     else{
        if(sdate<16){
          if(bdate<16){
            month=(12-smonth)+bmonth-1+smonth;
	    year=syear;
		if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
          }
          if(bdate>15){
            month=(12-smonth)+bmonth+smonth;
            year=syear;
		if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
          }
        }
        if(sdate>15){
          if(bdate<16){
           month=(12-smonth)+bmonth+smonth;
           year=syear;
		if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
          }
          if(bdate>15){
            month=(12-smonth)+bmonth+smonth;
            year=syear;
		if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
          }
        }
     }
  }
}     
            
            
         }
        }
        //计算起始年月
        function qsyear(){
          var lastdatetime = document.form1.lastdatetime.value;
         // if(payzq==""||payzq==null||payzq==0)payzq=1;
          var month=lastdatetime.substring(5,7)-0;
          var year=lastdatetime.substring(0,4)-0;
          var date=lastdatetime.substring(8,10)-0;
          var s;
        
          if(date<16){
           if(month<10){
        	 month="0"+month;
           }
           s=year+"-"+month;
             document.form1.startmonth.value=s;
            
          }
          else{
            month=(month+1);
             if(month>12){
        	  year=(year+1);
        	  month=month-12;
              }
             if(month<10){
        	  month="0"+month;
            }
              s=year+"-"+month;
             document.form1.startmonth.value=s;
          }
          //设置一下当前时间给报账月份
           var today=new Date(); 
           var tyear=today.getYear();
           var tmonth=today.getMonth()+1;
           var s1;
          
          if(tmonth<10){
        	 tmonth="0"+tmonth;
           }
           s1=tyear+"-"+tmonth;
             document.form1.accountmonth.value=s1;
          
        }
	
function modifyDegree() {
	var pj=document.form1.pjje.value;
	var moneyqk1 = document.form1.pjje.value.replace(/[ ]/g,"");
	var dltz=document.form1.floatdegree.value;
	var dltz1 = document.form1.floatdegree.value.replace(/[ ]/g,"");
	var dftz=document.form1.floatpay.value;
	var dftz1 = document.form1.floatpay.value.replace(/[ ]/g,"");
	var zh=(parseFloat(document.form1.scdl.value)+ parseFloat(document.form1.bgdl.value)+parseFloat(document.form1.yydl.value)+ parseFloat(document.form1.xxhdl.value)+ parseFloat(document.form1.jstzdl.value)+ parseFloat(document.form1.dddfdl.value)).toFixed(0);
	// alert(zh+"a");
	// alert((parseFloat(document.form1.blhdl.value)).toFixed(0)+"b");
	if(zh==(parseFloat(document.form1.blhdl.value)).toFixed(0)){
		
	var zf=(parseFloat(parseFloat(document.form1.scdff.value)+ parseFloat(document.form1.bgdf.value)+parseFloat(document.form1.yydf.value)+ parseFloat(document.form1.xxhdf.value)+ parseFloat(document.form1.jstzdf.value)+ parseFloat(document.form1.dddfdf.value))).toFixed(0);
	// alert(zf);
	// alert((parseFloat(document.form1.actualpay.value)).toFixed(0));
	if(zf==(parseFloat(document.form1.actualpay.value)).toFixed(0)){
	var ad2_bz = "";//AuditDegree2状态标志
	if (document.form1.lastad.value != document.form1.lastdegree.value) {
		//alert(document.form1.lastad.value);
		ad2_bz = "1";
	}
	var id = document.form1.ammeterdegreeidFk.value;
	var ii=document.form1.floatdegree.value;
	if(checkNotnull(document.form1.ammeteridFk,"电表ID")&&checkNotnull(document.form1.ammeterdegreeidFk,"电表流水ID")&&checkNotnull(document.form1.lastdegree,"上次电表读数")&&checkNotnull(document.form1.thisdegree,"本次电表读数")&&checkNotnull(document.form1.lastdatetime,"上次抄表时间")&&checkNotnull(document.form1.thisdatetime,"本次抄表时间")
	&&checkNotnull(document.form1.startmonth,"起始时间")&&checkNotnull(document.form1.endmonth,"结束时间")&&checkNotSelected(document.form1.notetypeid,"票据类型")&&checkNotnull(document.form1.pjje,"票据金额")&&checkNotnull(document.form1.accountmonth,"报账月份")){	
			var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
			var ldt = document.form1.lastdatetime.value;
			var tdt = document.form1.thisdatetime.value;
			//var ipt = document.form1.inputdatetime.value;
			//var kpt = document.form1.kptime.value;
			var ntt = document.form1.notetime.value;
			var pdt = document.form1.paydatetime.value;
			var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
			var sm = document.form1.startmonth.value;
			var em = document.form1.endmonth.value;
			var am = document.form1.accountmonth.value;
			
			//=======
			var thisdatetime = document.form1.thisdatetime.value;
			var lastdatetime = document.form1.lastdatetime.value;
			var bmonth = thisdatetime.substring(5, 7) - 0;
		var byear = thisdatetime.substring(0, 4) - 0;
		var bdate = thisdatetime.substring(8, 10) - 0;

		var smonth = lastdatetime.substring(5, 7) - 0;
		var syear = lastdatetime.substring(0, 4) - 0;
		var sdate = lastdatetime.substring(8, 10) - 0;

		var d1 = new Date(syear, smonth, sdate);
		var d2 = new Date(byear, bmonth, bdate);
		var s = (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
		var days = Math.round(s);
		
			
				if(isDate(ntt)==true||ntt==""||ntt==null){
					
							if(reg.exec(ldt)&&isDate(ldt)==true){
								if(reg.exec(tdt)&&isDate(tdt)==true){
									if(reg1.exec(sm)){
										if(reg1.exec(em)){
											if(isDate(pdt)==true||pdt==""||pdt==null){
												if(reg1.exec(am)){
													  if(sm<=em){
															if(tdt>=ldt){
																if(isNaN(document.form1.actualpay.value)==false){
																if(isNaN(pj)==false&& moneyqk1!=""){
																if(isNaN(dltz)==false&& dltz1!=""){
																if(isNaN(dftz)==false&& dftz1!=""){
																	if (confirm("您将要修改电量电费信息！确认信息正确！")) {
																		var biaozhi = '<%=biaozhi%>';
																		document.form1.action = path + "/servlet/electricfees?action=modifyadfee1&status=qmodify&ad2_bz="
																					+ ad2_bz + "&id=" + id + "&biaozhi=" + biaozhi+"&days="+days;
																		document.form1.submit()
																		showdiv("请稍等..............");
																	 }
																	}else{
																	 alert("您输入的信息有误，费用调整必须为数字！");
																	}
																  }else{
																	 alert("您输入的信息有误，用电量调整必须为数字！");
																	}
																}else{
																	 alert("您输入的信息有误，票据金额必须为数字！");
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
						alert("您输入的票据时间有误，请确认后重新输入！");
				}
	}
	 }else{
           alert("分摊电费之和不等于实际电费金额,请确认！！！");
		}
       }else{
           alert("分摊电量之和不等于实际用电量,请确认！！！");
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
        	 var bl=document.form1.beilv.value;
        	  if(bl==null||bl=="")bl="1"
        	 var thisdegree=document.form1.thisdegree.value;
             var lastdatetime=document.form1.lastdegree.value;
			 var floatdegree=document.form1.floatdegree.value;
			 var linelossvalue=document.form1.linelossvalue.value;
			 var actualdegree=document.form1.actualdegree.value;
			 document.form1.actualdegree.value=((parseFloat(Number(document.form1.thisdegree.value)-Number(document.form1.lastdegree.value))+parseFloat(Number(document.form1.floatdegree.value)))).toFixed(2);
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
        
         function getMoney(){
        	var dbili1=document.form1.dbili1.value;
        	var dbili2=document.form1.dbili2.value;
        	var dbili3=document.form1.dbili3.value;
        	var dbili4=document.form1.dbili4.value;
        	var dbili5=document.form1.dbili5.value;
        	var dbili6=document.form1.dbili6.value;
            var actualpay= document.form1.actualpay.value;
             if(dbili1==null||dbili1==""){
        	 dbili1="0.00";
        	 }
        	   if(dbili2==null||dbili2==""){
        	 dbili2="0.00";
        	 }
        	    if(dbili3==null||dbili3==""){
        	 dbili3="0.00";
        	 }
        	    if(dbili4==null||dbili4==""){
        	 dbili4="0.00";
        	 }
        	  if(dbili5==null||dbili5==""){
        	 dbili5="0.00";
        	 }
        	  if(dbili6==null||dbili6==""){
        	 	dbili6="0.00";
        	 }
        	 	if(dbili6!="0.00"){
        		var v6=parseFloat(actualpay);
        	    var v7=(parseFloat(dbili1)/100)*parseFloat(actualpay)+(parseFloat(dbili2)/100)*parseFloat(actualpay)+(parseFloat(dbili3)/100)*parseFloat(actualpay)+(parseFloat(dbili4)/100)*parseFloat(actualpay)+(parseFloat(dbili5)/100)*parseFloat(actualpay);
        		document.form1.dddfdf.value=(v6-v7).toFixed(2);
        		}else{
        		document.form1.scdff.value=((parseFloat(dbili1)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.bgdf.value=((parseFloat(dbili3)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.xxhdf.value=((parseFloat(dbili4)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.jstzdf.value= ((parseFloat(dbili5)/100)*parseFloat(actualpay)).toFixed(2);
        		
        		}
        		if(dbili5!="0.00"&&dbili6=="0.00"){
        		var v6=parseFloat(actualpay);
        	    var v7=(parseFloat(dbili1)/100)*parseFloat(actualpay)+(parseFloat(dbili2)/100)*parseFloat(actualpay)+(parseFloat(dbili3)/100)*parseFloat(actualpay)+(parseFloat(dbili4)/100)*parseFloat(actualpay)+(parseFloat(dbili6)/100)*parseFloat(actualpay);
        		document.form1.jstzdf.value=(v6-v7).toFixed(2);
        		}else{
        		document.form1.scdff.value=((parseFloat(dbili1)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.bgdf.value=((parseFloat(dbili3)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.xxhdf.value=((parseFloat(dbili4)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.dddfdf.value= ((parseFloat(dbili6)/100)*parseFloat(actualpay)).toFixed(2);
        		
        		}
        		if(dbili4!="0.00"&&dbili5=="0.00"&&dbili6=="0.00"){
        		var v6=parseFloat(actualpay);
        	    var v7=(parseFloat(dbili1)/100)*parseFloat(actualpay)+(parseFloat(dbili2)/100)*parseFloat(actualpay)+(parseFloat(dbili3)/100)*parseFloat(actualpay)+(parseFloat(dbili5)/100)*parseFloat(actualpay)+(parseFloat(dbili6)/100)*parseFloat(actualpay);
        		document.form1.xxhdf.value=(v6-v7).toFixed(2);
        		}else{
        		document.form1.scdff.value=((parseFloat(dbili1)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.bgdf.value=((parseFloat(dbili3)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.jstzdf.value= ((parseFloat(dbili5)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.dddfdf.value= ((parseFloat(dbili6)/100)*parseFloat(actualpay)).toFixed(2);
        		}
        		if(dbili3!="0.00"&&dbili4=="0.00"&&dbili5=="0.00"&&dbili6=="0.00"){
        		var v6=parseFloat(actualpay);
        	    var v7=(parseFloat(dbili1)/100)*parseFloat(actualpay)+(parseFloat(dbili2)/100)*parseFloat(actualpay)+(parseFloat(dbili4)/100)*parseFloat(actualpay)+(parseFloat(dbili5)/100)*parseFloat(actualpay)+(parseFloat(dbili6)/100)*parseFloat(actualpay);
        		document.form1.yydf.value=(v6-v7).toFixed(2);
        		}else{
        		document.form1.scdff.value=((parseFloat(dbili1)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.xxhdf.value=((parseFloat(dbili4)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.dddfdf.value= ((parseFloat(dbili6)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.jstzdf.value= ((parseFloat(dbili5)/100)*parseFloat(actualpay)).toFixed(2);
        		}
        		if(dbili2!="0.00"&&dbili3=="0.00"&&dbili4=="0.00"&&dbili5=="0.00"&&dbili6=="0.00"){
        		var v6=parseFloat(actualpay);
        	    var v7=(parseFloat(dbili1)/100)*parseFloat(actualpay)+(parseFloat(dbili3)/100)*parseFloat(actualpay)+(parseFloat(dbili4)/100)*parseFloat(actualpay)+(parseFloat(dbili5)/100)*parseFloat(actualpay)+(parseFloat(dbili6)/100)*parseFloat(actualpay);
        		document.form1.bgdf.value=(v6-v7).toFixed(2);
        		}else{
        		document.form1.scdff.value=((parseFloat(dbili1)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.xxhdf.value=((parseFloat(dbili4)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.dddfdf.value= ((parseFloat(dbili6)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.jstzdf.value= ((parseFloat(dbili5)/100)*parseFloat(actualpay)).toFixed(2);
        		}
        		if(dbili1!="0.00"&&dbili2=="0.00"&&dbili3=="0.00"&&dbili4=="0.00"&&dbili5=="0.00"&&dbili6=="0.00"){
        		var v6=parseFloat(actualpay);
        	    var v7=(parseFloat(dbili2)/100)*parseFloat(actualpay)+(parseFloat(dbili3)/100)*parseFloat(actualpay)+(parseFloat(dbili4)/100)*parseFloat(actualpay)+(parseFloat(dbili5)/100)*parseFloat(actualpay)+(parseFloat(dbili6)/100)*parseFloat(actualpay);
        		document.form1.scdff.value=(v6-v7).toFixed(2);
        		}else{
        		document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.xxhdf.value=((parseFloat(dbili4)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.dddfdf.value= ((parseFloat(dbili6)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.jstzdf.value= ((parseFloat(dbili5)/100)*parseFloat(actualpay)).toFixed(2);
        		document.form1.bgdf.value=((parseFloat(dbili3)/100)*parseFloat(actualpay)).toFixed(2);	
        		}
        	 /* //alert(dbili1+"|"+dbili2+"|"+dbili3+"|"+dbili4+"|"+dbili5+"|"+actualpay); 
        	 //生产
        	 document.form1.scdff.value=((parseFloat(dbili1)/100)*parseFloat(actualpay)).toFixed(2);

        	  //办公
        	 document.form1.bgdf.value=((parseFloat(dbili3)/100)*parseFloat(actualpay)).toFixed(2);	
        	
        	 // 营业
        	 document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(actualpay)).toFixed(2);
        	  
        	 // 信息化	
        	 document.form1.xxhdf.value=((parseFloat(dbili4)/100)*parseFloat(actualpay)).toFixed(2);
        	
        	 // 建设投资
        	 document.form1.jstzdf.value= ((parseFloat(dbili5)/100)*parseFloat(actualpay)).toFixed(2);
        	 
        	 //代垫电费
        	 document.form1.dddfdf.value= ((parseFloat(dbili6)/100)*parseFloat(actualpay)).toFixed(2);
        	 */
        }
        
          function getHaodianliangg(){
        	var dbili1=document.form1.dbili1.value;
        	var dbili2=document.form1.dbili2.value;
        	var dbili3=document.form1.dbili3.value;
        	var dbili4=document.form1.dbili4.value;
        	var dbili5=document.form1.dbili5.value;
        	var dbili6=document.form1.dbili6.value;
        	var blhdl= document.form1.blhdl.value;
        	 if(dbili1==null||dbili1==""){
        	 dbili1="0.00";
        	 }
        	   if(dbili2==null||dbili2==""){
        	 dbili2="0.00";
        	 }
        	    if(dbili3==null||dbili3==""){
        	 dbili3="0.00";
        	 }
        	    if(dbili4==null||dbili4==""){
        	 dbili4="0.00";
        	 }
        	    if(dbili5==null||dbili5==""){
        	 dbili5="0.00";
        	 }
        	 if(dbili6==null||dbili6==""){
        	 	dbili6="0.00";
        	 }
        	// alert(dbili1+"|"+dbili2+"|"+dbili3+"|"+dbili4+"|"+dbili5+"|"+blhdl); 
        	 //生产
        	 document.form1.scdl.value=((parseFloat(dbili1)/100)*parseFloat(blhdl)).toFixed(2);
 
        	  //办公
        	 document.form1.bgdl.value=((parseFloat(dbili3)/100)*parseFloat(blhdl)).toFixed(2);	
        	
        	 // 营业
        	 document.form1.yydl.value=	((parseFloat(dbili2)/100)*parseFloat(blhdl)).toFixed(2);
        	  
        	 // 信息化	
        	 document.form1.xxhdl.value=((parseFloat(dbili4)/100)*parseFloat(blhdl)).toFixed(2);
        	
        	 // 建设投资
        	 document.form1.jstzdl.value=((parseFloat(dbili5)/100)*parseFloat(blhdl)).toFixed(2);
        	 
        	 // 代垫电量
        	 document.form1.dddfdl.value=((parseFloat(dbili6)/100)*parseFloat(blhdl)).toFixed(2);
        	 
        	        	 
         }
        
 $(function(){
			
			$("#resetBtn").click(function(){
				$("#form")[0].reset();
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
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean"></jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
<form action="" name="form1" method="POST" id="form">
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="4">
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan=3 width="600" height="50"><img src="<%=path%>/images/btt.bmp" width="600" height="60" />
					<span style="font-size:16px;position: absolute;left:25px;top:20px;color:black;font-weight:bold">电量电费信息</span></td>
					<td></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3" width="83%" > 
			<table width="100%" height="80%" border="0" cellspacing="1" cellpadding="1" class="form_label">
				
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
		    
		    double pje=bean.getPjje();
		    if(pje==0||pje==0.0){
		     pje=Double.parseDouble(actualpay);
		    }
		    
            AmmeterDegreeBean beann=new AmmeterDegreeBean();
            ArrayList listt=new ArrayList();
           // String db=request.getParameter("ammeteridFk");
            String db=bean.getAmmererid();
            System.out.println("电表id："+db);
            listt=beann.selectXSX(db);
            String linelosstype="",linelossvalue="",shifou="";
            String beilv="";
            int fycount = ((Integer)listt.get(0)).intValue();
            for( int k = fycount;k<listt.size()-1;k+=fycount){
    		   
    		    linelosstype = (String)listt.get(k+listt.indexOf("LINELOSSTYPE"));
    		    linelosstype = linelosstype != null ? linelosstype : "";
    		    
    		    beilv = (String)listt.get(k+listt.indexOf("BEILV"));
    		    beilv = beilv != null ? beilv : "";
    		    
    		    shifou = (String)listt.get(k+listt.indexOf("BGSIGN"));
    		    shifou = shifou != null ? shifou : "";
    		    
    		    linelossvalue = (String)listt.get(k+listt.indexOf("LINELOSSVALUE"));
    		    linelossvalue = linelossvalue != null ? linelossvalue : "";
    		    		    
            }
            String ydl=bean.getActualdegree();
            if(ydl==null||ydl.equals("")||ydl.equals("null"))ydl="0";
            DecimalFormat pay=new DecimalFormat("0.00");
            ydl = pay.format(Double.parseDouble(ydl));
            String mm="";
            if(bean.getBlhdl()!=null&&bean.getBlhdl()!=""&&bean.getBlhdl()!=" "){
            mm=pay.format(Double.parseDouble(bean.getBlhdl()));
            }
		 	//五大分摊的格式化
		 	DecimalFormat paym=new DecimalFormat("0.00");
		 	String a1=bean.getScdl(),a2=bean.getBgdl(),a3=bean.getYydl(),a4=bean.getXxhdl(),a5=bean.getJstzdl(),
		 	a6=bean.getScdff(),a7=bean.getBgdf(),a8=bean.getYydf(),a9=bean.getXxhdf(),a10=bean.getJstzdf();
		 	String a11 = bean.getDddfdl();//代垫电量
		 	String a12 = bean.getDddfdf();//代垫电费 
		 	
		 	if(a11==null||"".equals(a11)||"null".equals(a11)||" ".equals(a11))a11="0.00";
		 	if(a12==null||"".equals(a12)||"null".equals(a12)||" ".equals(a12))a12="0.00";
		 //	System.out.println("df:"+ bean.getDddfdl()+"  dl:"+bean.getDddfdf());
		 	if(bean.getScdl()!=null&&bean.getScdl()!=""&&bean.getScdl()!=" "){
            	 a1 = paym.format(Double.parseDouble(a1));
		 	}
		 	if(bean.getBgdl()!=null&&bean.getBgdl()!=""&&bean.getBgdl()!=" "){
                a2 = paym.format(Double.parseDouble(a2));
		 	}
		 	if(bean.getYydl()!=null&&bean.getYydl()!=""&&bean.getYydl()!=" "){
                a3 = paym.format(Double.parseDouble(a3));
		 	}
		 	if(bean.getXxhdl()!=null&&bean.getXxhdl()!=""&&bean.getXxhdl()!=" "){
                a4 = paym.format(Double.parseDouble(a4));
		 	}
		 	if(bean.getJstzdl()!=null&&bean.getJstzdl()!=""&&bean.getJstzdl()!=" "){
                a5 = paym.format(Double.parseDouble(a5));
		 	}
		 	if(bean.getDddfdl()!=null&&bean.getDddfdl()!=""&&bean.getDddfdl()!=" "){//代垫电量
                a11 = paym.format(Double.parseDouble(a11));
		 	}
		 	if(bean.getScdff()!=null&&bean.getScdff()!=""&&bean.getScdff()!=" "){
                a6 = paym.format(Double.parseDouble(a6));
		 	}
		 	if(bean.getBgdf()!=null&&bean.getBgdf()!=""&&bean.getBgdf()!=" "){
                a7 = paym.format(Double.parseDouble(a7));
		 	}
		 	if(bean.getYydf()!=null&&bean.getYydf()!=""&&bean.getYydf()!=" "){
                a8 = paym.format(Double.parseDouble(a8));
		 	}
		 	if(bean.getXxhdf()!=null&&bean.getXxhdf()!=""&&bean.getXxhdf()!=" "){
                a9 = paym.format(Double.parseDouble(a9));
		 	}
			if(bean.getJstzdf()!=null&&bean.getJstzdf()!=""&&bean.getJstzdf()!=" "){
               a10 = paym.format(Double.parseDouble(a10));
			}
			if(bean.getDddfdf()!=null&&bean.getDddfdf()!=""&&bean.getDddfdf()!=" "){//代垫电费
               a12 = paym.format(Double.parseDouble(a12));
			}
		 	
            
            AmmeterDegreeFormBean beanm=new AmmeterDegreeFormBean();
            ArrayList listm=new ArrayList();
            
            listm=beanm.getStationMhchexkt(bean.getAmmererid());
            String shuoshuzhuanye="";
            String dbili="",dbili1="",dbili2="",dbili3="",dbili4="",dbili5="",dbili6="";
            
          if(listm!=null)
        {
        	int fmcount = ((Integer)listm.get(0)).intValue();
        	for( int k = fmcount;k<listm.size()-1;k+=fmcount){
        	shuoshuzhuanye = (String)listm.get(k+listm.indexOf("SHUOSHUZHUANYE"));
            shuoshuzhuanye = shuoshuzhuanye != null ? shuoshuzhuanye : "";
            dbili = (String)listm.get(k+listm.indexOf("DBILI"));
            dbili = dbili != null ? dbili : "0";
            //生产
            if(shuoshuzhuanye.equals("zylx01")){
               dbili1=dbili;
            }//营业
             if(shuoshuzhuanye.equals("zylx02")){
              dbili2=dbili;
            
            }//办公
             if(shuoshuzhuanye.equals("zylx03")){
            
              dbili3=dbili;
            }//信息化支撑
             if(shuoshuzhuanye.equals("zylx04")){
              dbili4=dbili;
            
            }//建设投资
             if(shuoshuzhuanye.equals("zylx05")){
              dbili5=dbili;
            
            }
           //建设投资
             if(shuoshuzhuanye.equals("zylx06")){
              dbili6=dbili;            
            }
            }
        }
		%>
			
        
      <tr><td colspan="6" height="19" width="15%" class="form"><img src="../../images/v.gif" width="15" height="15" />电量信息</td></tr>
      <tr class="form_label">
         <td height="19" bgcolor="#DDDDDD" width="15%" class="form"><div>电表ID：</div></td>
         <td><input type="text" name="ammeteridFk" value="<%=bean.getAmmererid()%>" readonly="true" class="form" /><span class="style1">&nbsp;*</span></td>
         <td height="19" bgcolor="#DDDDDD" width="15%" class="form"><div>上次电表读数：</div></td>
         
      
      
        <%
         if(shifou.equals("0")){
         %>
         <td><input type="text" name="lastdegree" value="0" readonly="true"  class="form1" /></td>
         <td height="19" bgcolor="#DDDDDD" width="15%" class="form"><div>本次电表读数：</div></td>
         <td><input type="text" name="thisdegree" value="0" class="formright" /></td>
      <tr>

      <tr class="form_label">
         <%
         }else{
         %>
        <td><input type="text" name="lastdegree" value="<%=bean.getLastdegree() %>" readonly="true" onChange="getHaodianliang()"  class="form1" /></td>
         <td height="19" bgcolor="#DDDDDD" width="15%" class="form"><div>本次电表读数：</div></td>
         <td><input type="text" name="thisdegree" value="<%=bean.getThisdegree() %>" onChange="getHaodianliang()"  class="formright" /><span class="style1">&nbsp;*</span></td>
      <tr>

      <tr class="form_label">
        <%} %> 
        
	      <td height="19" bgcolor="#DDDDDD" class="form"><div>上次抄表时间：</div></td>
	      <td><input type="text" name="lastdatetime" value="<%=bean.getLastdatetime() %>" readonly="true" class="form2"/></td>
         <td height="19" bgcolor="#DDDDDD" class="form"><div>本次抄表时间：</div></td>
         <td><input type="text" name="thisdatetime" value="<%=bean.getThisdatetime() %>" onFocus="getDateString(this,oCalendarChs)" onpropertychange="endmonthzq()" class="form"  align="right"/><span class="style1">&nbsp;*</span>        </td>        
     
      <%
         if(shifou.equals("0")){
         %>
         <td height="19" class="form"></td>
         <td><input type="hidden" name="floatdegree" value="0" class="formright" /></td>
          </tr>
      <tr>
         <td height="19" class="form"></td>
         <td><input type="hidden" name="actualdegree" value="0" readonly="true" class="formright" /></td>
         <td height="19" bgcolor="#DDDDDD" class="form"><div>实际用电量：</div></td>
         <td><input type="text" name="blhdl" value="<%=mm %>"  class="formright" onpropertychange="getHaodianliangg()"/><span class="style1">&nbsp;*</span></td>
         <%
         }else{
         %>
          <td height="19" bgcolor="#DDDDDD" class="form"><div>用电量调整：</div></td>
         <td><input type="text" name="floatdegree" value="<%=bean.getFloatdegree()%>" onChange="getHaodianliang()"  class="formright" /></td>
      </tr>
      <tr>
         <td height="19" bgcolor="#DDDDDD" class="form"><div>用电量：</div></td>
         <td><input type="text" name="actualdegree" value="<%=ydl %>" readonly="true" class="formright" /></td>
          <td height="19" bgcolor="#DDDDDD" class="form"><div>实际用电量：</div></td>
         <td><input type="text" name="blhdl"  readonly="true" value="<%=mm %>"  class="form1" onpropertychange="getHaodianliangg()"/><span class="style1">&nbsp;*</span></td>
       <%} %> 
        
         <td height="19" bgcolor="#DDDDDD" class="form"><div>备注：</div></td>
         <td><input type="text" size="20" name="memoam" value="<%=bean.getMemoam() %>"  class="form" /></td>
     </tr>
     <tr>
         <td height="19" bgcolor="#DDDDDD" class="form"><div>起始月份：</div></td>
         <td><input type="text" name="startmonth" value="<%=bean.getStartmonth() %>" onFocus="getDatenyString(this,oCalendarChsny)" class="form" /><span class="style1">&nbsp;*</span></td>
         <td height="19" bgcolor="#DDDDDD" class="form"><div>结束月份：</div></td>
         <td><input type="text" name="endmonth" value="<%=bean.getEndmonth() %>" onFocus="getDatenyString(this,oCalendarChsny)" class="form" /><span class="style1">&nbsp;*</span></td>
    
                 
      </tr>    
      <tr>
         <td height="19" bgcolor="#DDDDDD" class="form"><div>抄表操作员：</div></td>
         <td><input type="text" name="inputoperator" value="<%=bean.getInputoperator() %>"  class="form" />
         <input type="hidden" size="50" name="autoauditstatus" value="<%=bean.getAutoauditstatus() %>"  class="form" />
         <input type="hidden" size="50" name="autoauditdescription" value="<%=bean.getAutoauditdescription() %>"  class="form" /></td>
<%--         <td height="19" bgcolor="#DDDDDD" width="15%" class="form"><div>电量流水ID：</div></td>--%>
         <td><input type="hidden" name="ammeterdegreeidFk" value="<%=bean.getAmmeterdegreeidFk() %>"  class="form" /></td>
        
      </tr>
      <tr><td colspan="6" height="23" width="15%" class="form"><img src="../../images/v.gif" width="15" height="15" />电费信息</td></tr>
      <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%" class="form"><div>本次单价：</div></td>
         <td><input type="text" readonly="true" name="unitprice" value="<%=bean.getUnitprice() %>" onchange="javascript:document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.actualdegree.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2)" class="form1" /></td>         
       
         <%
         if(shifou.equals("0")){
         %>
         <td height="19" bgcolor="#DDDDDD"  class="form"><div>实际电费金额：</div></td>
         <td><input type="text"  name="actualpay" value="<%=actualpay %>"  class="formright" onpropertychange="getMoney()"/><span class="style1">&nbsp;*</span></td>
         
         <td height="19" width="15%" class="form"></td>
         <td><input type="hidden" name="floatpay" value="0"  class="formright" /></td>
   
         <%
         }else{
         %>
         <td height="19" bgcolor="#DDDDDD"  class="form"><div>实际电费金额：</div></td>
         <td><input type="text"  readonly="true" name="actualpay" value="<%=actualpay %>"  class="form1" onpropertychange="getMoney()"/><span class="style1">&nbsp;*</span></td>
           
         <td height="19" bgcolor="#DDDDDD" width="15%" class="form"><div>费用调整：</div></td>
         <td><input type="text" name="floatpay" value="<%=bean.getFloatpay() %>" onChange="javascript:document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.blhdl.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2)" class="formright" /></td>
   
        <%} %>        
        </tr>
    
      <tr>
         <td height="19" bgcolor="#DDDDDD" class="form"><div>票据时间：</div></td>
         <td><input type="text" name="notetime" value="<%=bean.getNotetime() %>" onFocus="getDateString(this,oCalendarChs)" class="form" /></td> 
         <td height="19" bgcolor="#DDDDDD" class="form"><div>票据类型：</div></td>
         <td>
         <select name="notetypeid" style="width:130">
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
	         </select><span class="style1">&nbsp;*</span>
         </td>
         <td height="19" bgcolor="#DDDDDD" class="form"><div >票据编号：</div></td>
         <td><input type="text" name="noteno" value="<%=bean.getNoteno() %>"  class="form" /></td>
      </tr>

      <tr>
      <td height="19" bgcolor="#DDDDDD" class="form"><div>报账月份：</div></td>
         <td><input type="text" name="accountmonth" value="<%=bean.getAccountmonth() %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form" /><span class="style1">&nbsp;*</span></td> 
         <td height="19" bgcolor="#DDDDDD" class="form"><div>交费操作员：</div></td>
         <td><input type="text" name="payoperator" value="<%=bean.getPayoperator() %>"  class="form" /></td>
         <td height="19" bgcolor="#DDDDDD" class="form"><div>交费时间：</div></td>
         <td><input type="text" name="paydatetime" value="<%=bean.getPaydatetime() %>" onFocus="getDateString(this,oCalendarChs)"  class="form" /></td>
     </tr>
     <tr>
                
         <td height="19" bgcolor="#DDDDDD" class="form"><div>备注：</div></td>
         <td><input type="text" name="memo" value="<%=bean.getMemo() %>"  class="form" /></td>
          <td bgcolor="#DDDDDD" class="form_label">票据金额：</td>
     <td><input type="text" id="pjje" name="pjje" value="<%=pje%>" class="formright"><span class="style1">&nbsp;*</span></td>
      </table>
      <br/>
      <table width="100%" scope="2" border="0" cellspacing="1" cellpadding="1">
      <tr>
         <td colspan="4" align="right">
            <div id="cancelBtn" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; ">
			<img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
				<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
			</div>

			<div id="resetBtn" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 11px">
				<img src="<%=path%>/images/2chongzhi.png" width="100%" height="100%">
				<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
			</div>
			<div id="baocun" style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:22px">
				<img alt="" src="<%=request.getContextPath()%>/images/baocun.png" width="100%" height="100%" />
				<span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">保存</span>
			</div>
         </td>
      </tr>
      </table>
	</td>
    <td width="17%">
			 <table border="0" width="100%">
			 	<tr>
			 	
			 		<td>
			 		<fieldset id="regist" >
				 		<table border="0" width="100%">
				 			<tr>
						 		<td colspan="2"><div align="center"><b><font size="2">电量分摊</font></b><hr/></div></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">生产</td><td><input type="text" name="scdl" value="<%=a1 %>" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">办公</td><td><input type="text" name="bgdl" value="<%=a2 %>" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">营业</td><td><input type="text" name="yydl" value="<%=a3 %>" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">信息化支持</td><td><input type="text" name="xxhdl" value="<%=a4 %>" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">建设投资</td><td><input type="text" name="jstzdl" value="<%=a5 %>" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">代垫电量</td><td><input type="text" name="dddfdl" value="<%=a11 %>" class="formm"/></td>
						 	</tr>
						 </table>
					 </fieldset>
					 </td>
			 	</tr>
			 	<tr>
			 		<td>
			 		<fieldset id="regist">
			 			<table border="0" width="100%">
				 			<tr align="center">
						 		<td colspan="2"><div align="center"><b><font size="2">电费分摊</font></b><hr/></div></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">生产</td><td><input type="text" name="scdff" value="<%=a6 %>" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">办公</td><td><input type="text" name="bgdf" value="<%=a7 %>" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">营业</td><td><input type="text" name="yydf" value="<%=a8 %>" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">信息化支持</td><td><input type="text" name="xxhdf" value="<%=a9 %>" class="formm" /></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">建设投资</td><td><input type="text" name="jstzdf" value="<%=a10 %>" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">代垫电费</td><td><input type="text" name="dddfdf" value="<%=a12 %>" class="formm"/></td>
						 	</tr>
						 </table>
					   </fieldset>
					     <td ><input type="hidden" name="dbili1" value="<%=dbili1%>"></td>
				         <td ><input type="hidden" name="dbili2" value="<%=dbili2%>"></td>
				         <td ><input type="hidden" name="dbili3" value="<%=dbili3%>"></td>
				         <td ><input type="hidden" name="dbili4" value="<%=dbili4%>"></td>
				         <td ><input type="hidden" name="dbili5" value="<%=dbili5%>"></td>
				         <td ><input type="hidden" name="dbili6" value="<%=dbili6%>"></td>
			 		</td>
			 	</tr>
			 </table>
		
				</td>
			</tr>
		</table>
		
 <input type="hidden" name="lastad" value="<%=lastad %>"  class="form" />
 <input type="hidden" name="linelosstype" value="<%=linelosstype %>">
 <input type="hidden" name="linelossvalue" value="<%=linelossvalue %>"/>
 <input type="hidden" name="accountid" value="<%=accountid %>"/>
 <input type="hidden" name="beilv" value="<%=beilv %>"/>
<input type="hidden" name = "ammeterdegreeid" value="<%=degreeid%>"/>

</td>
</tr>
</table>
</form>

</body>
</html>

