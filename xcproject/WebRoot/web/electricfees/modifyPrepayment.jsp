<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.electricfees.javabean.PrepaymentFormBean" %>
<%@ page import="java.text.*"%>
<%
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
        String roleId = account.getRoleId();
        String accountname=account.getAccountName();
        
        String prepayId = request.getParameter("prepayId");
		PrepaymentFormBean bean = new PrepaymentFormBean();
		
	    bean = bean.getPrepaymentInfo(prepayId);
	    String edhdl = bean.getEdhdl();
	    String qsdbdl = bean.getQsdbdl();
	    String shicode = bean.getShicode();
	    String property = bean.getProperty();
	    String scb = bean.getScb();
	    String zlfh = bean.getZlfh();
	    String jlfh = bean.getJlfh();
	    String stationtypecode = bean.getStationtypecode();
	    String gdfscode = bean.getGdfscode();
	    String dfzflxcode = bean.getDfzflxcode();
	    String dedhdlbz = bean.getDedhdlbz();
	    String qsdbdlbz = bean.getQsdbdlbz();
	    if(edhdl==null){
	    	edhdl="";
	    }
	    if(qsdbdl==null){
	    	qsdbdl="";
	    }
	    if(null==shicode){
	    	shicode = "";
	    }
	    if(null==property){
	    	property = "";
	    }
	    if(null==scb){
	    	scb = "";
	    }
	    if(null==zlfh){
	    	zlfh = "";
	    }
	    if(null==jlfh){
	    	jlfh = "";
	    }
	    if(null==gdfscode){
	    	gdfscode = "";
	    }
	    if(null==dfzflxcode){
	    	dfzflxcode = "";
	    }
	    if(null==stationtypecode){
	    	stationtypecode = "";
	    }
	
%>
<html>
<head>
<title>
</title>
<style>

.form{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
}
.form1{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		text-align: right;
}

.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
}
.bddf{
border-left-width:0px; border-top-width:0px; border-right-width:0px;
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
.bddf{
border-left-width:0px; border-top-width:0px; border-right-width:0px;
width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
.style1 {
	color: red;
	font-weight: bold;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.bttcn{color:BLACK;font-weight:bold;}
 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
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
	function modifyPrepay(){
		var qsdbdl = document.form1.qsdbdl.value;
		var edhdl =  document.form1.edhdl.value;
		var zlfh = document.form1.zlfh.value;
		var jlfh = document.form1.jlfh.value;
		var scb = document.form1.scb.value;
		var property = document.form1.property.value;
	var pjjee=document.form1.pjje.value;
	 //生产
        		var net=parseFloat(document.form1.NETWORKDF.value);
               // Math.round((document.dorm1.NETWORKDF.value)*100/100)
        	  //销售
      var mar=  parseFloat(document.form1.MARKETDF.value);
        
        	 // ADMINISTRATIVEDF
        var adm=parseFloat(document.form1.ADMINISTRATIVEDF.value);
        	
        	 // INFORMATIZATIONDF	
        	var inf=parseFloat( document.form1.INFORMATIZATIONDF.value);
        	 
        	 // BUILDDF	
        	var bui=parseFloat(document.form1.BUILDDF.value);
        	var dddf=parseFloat(document.form1.DDDF.value);
	
var dd=	(net+mar+adm+inf+bui+dddf).toFixed(0);
	
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
			if(checkNotnull(document.form1.dbid,"电表ID")&&checkNotnull(document.form1.startdegree,"起始电表数")&&checkNotnull(document.form1.stopdegree,"终止电表数")&&
				checkNotnull(document.form1.feetype,"费用类型")&&checkNotnull(document.form1.notetypeid,"票据类型")&&checkNotSelected(document.form1.feetype,"费用类型")&&
				checkNotSelected(document.form1.notetypeid,"票据类型")&&	checkNotnull(document.form1.sumdegree,"累计电量")&&checkNotnull(document.form1.pjje,"票据金额")&&
				checkNotnull(document.form1.money,"金额")&&checkNotnull(document.form1.startdate,"起始时间")&&checkNotnull(document.form1.preenddate,"预计结束时间")&&
				checkNotnull(document.form1.startmonth,"起始月份")&&checkNotnull(document.form1.endmonth,"结束月份")&&checkNotnull(document.form1.accountmonth,"报账月份 ")){
	               	var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
					var sd = document.form1.startdate.value;
					var pd = document.form1.preenddate.value;
					var moneyqk = document.form1.money.value.replace(/[ ]/g,"");
					var moneyqk1 = document.form1.pjje.value.replace(/[ ]/g,"");
					 var money=(parseFloat(document.form1.money.value)).toFixed(0);
					var ntt = document.form1.notetime.value;
					var kpt = document.form1.kptime.value;										
					var pdt = document.form1.paydatetime.value;
                   
					var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
					var sm = document.form1.startmonth.value;
					var em = document.form1.endmonth.value;
					 var bzyf=document.form1.accountmonth.value;
					var am = document.form1.accountmonth.value;	
					if(isDate(ntt)==true||ntt==""||ntt==null){				
						if(isDate(kpt)==true||kpt==""||kpt==null){
							if(isDate(pdt)==true||pdt==""||pdt==null){
								if(reg1.exec(am)||am==""||am==null){
									if(isDate(sd)==true){
										if(isDate(pd)==true){
											if(reg1.exec(sm)){
												if(reg1.exec(em)){
										             if(sm<=em){
												       if(pd>=sd){
															if(isNaN(document.form1.startdegree.value)==false){
												               if(isNaN(document.form1.stopdegree.value)==false){
												               		if(isNaN(document.form1.pjje.value)==false&&moneyqk1!=""){
														               		if(isNaN(document.form1.money.value)==false){
														               		          if(isNaN(document.form1.sumdegree.value)==false){
														               		         if(dd==money){
														               		         if(reg1.exec(bzyf)){
														               		         if(isNaN(pjjee)==false){
														                        if(confirm("您将要修改预付费信息！确认信息正确！")){
																                    document.form1.action=path+"/servlet/prepayment?action=modify"
																        			document.form1.submit();
																        			showdiv("请稍等..............");
														                         }}else{alert("您输入的票据金额信息有误！请确认！");}
														                         }else{
														                         alert("您输入的报账月份信息有误！请确认！");
														                         }
														                    }else{
														                     alert("总金额与分摊金额不符！请确认！");
														                    }
														                    }else{
														                    alert("您输入的信息有误，累计电量必须为数字！ ");
														                    }
														                    }
														                    else{
														                    	alert("您输入的信息有误，金额必须为数字！");
														                    }
														             }else{
														                alert("您输入的信息有误，票据金额必须为数字！");
														             }
												               }else{
												               		alert("您输入的信息有误，终止电表数必须为数字！");
												               }
										                     }else{
										                     	alert("您输入的信息有误，起始电表数必须为数字！");
										                     }
									                   }else{
									                   		alert("您输入的信息有误，起始时间必须小于等于预计结束时间 ！");
									                   }
								                   }else{
								                   			alert("您输入的信息有误，起始年月必须小于等于结束年月！");
								                   }
								             }else{
								             	alert("您输入的结束月份有误，请确认后重新输入！");
								             }
								          }else{
								          		alert("您输入的起始月份有误，请确认后重新输入！");
								          }
								       }else{
								       		alert("您输入的预计结束时间有误，请确认后重新输入！");
								       }
								   }else{
								   		alert("您输入的起始时间有误，请确认后重新输入！");
								   }
								}else{
									alert("您输入的报账月份有误，请确认后重新输入！");
								}
							}else{
								alert("您输入的收费日期有误，请确认后重新输入！");
							}
						}else{
							alert("您输入的开票时间有误，请确认后重新输入！");
						}
	                }else{
	                	alert("您输入的票据时间有误，请确认后重新输入！");
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
        function showIdList(){
        
               window.open('showDegreeIdList.jsp','','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
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
        	$("#baocun").click(function(){
				modifyPrepay();
				
			});
			$("#cancelBtn").click(function(){
				window.history.back();
			});
		});
		
		    function getHaodianliang(){
        
          // alert("-===");
        	// var bl=document.form1.mpower.value;
        	// if(bl==null||bl=="")bl="1"
        	var money=document.form1.money.value;
        	var dbili1=document.form1.dbili1.value;
        	var dbili2=document.form1.dbili2.value;
        	var dbili3=document.form1.dbili3.value;
        	var dbili4=document.form1.dbili4.value;
        	var dbili5=document.form1.dbili5.value;
        	var dbili6=document.form1.dbili6.value;
        	var money= document.form1.money.value;
        	 if(dbili1==null||dbili1==""){
        	 dbili1="0.00"
        	 }
        	   if(dbili2==null||dbili2==""){
        	 dbili2="0.00"
        	 }
        	    if(dbili3==null||dbili3==""){
        	 dbili3="0.00"
        	 }
        	    if(dbili4==null||dbili4==""){
        	 dbili4="0.00"
        	 }
        	    if(dbili5==null||dbili5==""){
        	 dbili5="0.00"
        	 }
        	   if(dbili6==null||dbili6==""){
        	 dbili6="0.00"
        	 }
        	 
        	 //生产
        		document.form1.NETWORKDF.value=((parseFloat(dbili1)/100)*parseFloat(money)).toFixed(2);
               // Math.round((document.dorm1.NETWORKDF.value)*100/100)
        	  //销售
        
        	 document.form1.MARKETDF.value=((parseFloat(dbili2)/100)*parseFloat(money)).toFixed(2);	
        
        	 // ADMINISTRATIVEDF
        	 document.form1.ADMINISTRATIVEDF.value=	((parseFloat(dbili3)/100)*parseFloat(money)).toFixed(2);
        	
        	 // INFORMATIZATIONDF	
        	 document.form1.INFORMATIZATIONDF.value=((parseFloat(dbili4)/100)*parseFloat(money)).toFixed(2);
        	 
        	 // BUILDDF	
        	 document.form1.BUILDDF.value= ((parseFloat(dbili5)/100)*parseFloat(money)).toFixed(2); 
        	 
        	  // DDDF	
        	 document.form1.DDDF.value= ((parseFloat(dbili6)/100)*parseFloat(money)).toFixed(2);       	        	 
         }
         function getActualdegree1(){
         	var actualdegree1 =document.form1.startdegree.value;
        	var actualdegree2 =document.form1.stopdegree.value;
        	var beilv1 = document.form1.beilv.value;
        	if(actualdegree2==null||actualdegree2==""){
        	 document.form1.actualdegree.value="0.0"
        	}else{
        		document.form1.actualdegree.value=((parseFloat(actualdegree2)-parseFloat(actualdegree1))*parseFloat(beilv1)).toFixed(1);
        	}
         }
         
     function fun(){	
    	 
		var lastdate = document.getElementById('startdate').value;
		var thisdate = document.getElementById('preenddate').value;
		
			var qsdbdl = '<%=qsdbdl%>';
		    var edhdl='<%=edhdl%>';
			
			var nd = 1000 * 24 * 60 * 60;//一天多少毫秒
			if(thisdate!=null&&thisdate!=""){
			var actualdegree = document.getElementById('actualdegree').value;
			
			var date1= new Date(Date.parse(lastdate.replace(/-/g,"/"))); //转换成Data();
			var date2= new Date(Date.parse(thisdate.replace(/-/g,"/"))); //转换成Data();
			lastdate = date1.getTime();
			thisdate = date2.getTime();
			//两个时间之间的天数
			var ld=(thisdate-lastdate)/nd+1;//相差天数
			
			var bb=parseFloat(actualdegree);//倍率耗电量
			var ee=parseFloat(edhdl)*parseFloat(ld);
			var qq=parseFloat(qsdbdl)*parseFloat(ld);//全省定标
			if(qsdbdl!=""&&qsdbdl!=null&&qsdbdl!="0"&&qsdbdl!="0.00"&&qsdbdl!="0.0"&&qsdbdl!="null"){
				
				var qqbili=(((bb-qq)/qq)*100).toFixed(2);
				document.form1.kongbai1.value="超全省定标的"+qqbili+"%!";
				document.form1.qsdbdlbz.value=qqbili/100;	
				
			}else{
				document.form1.kongbai1.value="系统中的全省 定标电量是空值！！！";
				document.form1.qsdbdlbz.value="";
			}
			if(edhdl!=""&&edhdl!=null){
				var bili=(((bb-ee)/ee)*100).toFixed(2);
				
				document.form1.kongbai.value="该记录超本地标"+bili+"%!";
				document.form1.dedhdlbz.value=bili/100;
				
			}else{
				    document.form1.kongbai.value="系统中的额定耗电量是空值！！！";
				     document.form1.dedhdlbz.value="";
				
			}
							
		}
	}
     
           //计算结束年月
        function endmonthzq(){
          qsyear();
       
         var thisdatetime = document.form1.preenddate.value;
         var lastdatetime = document.form1.startdate.value;
         var startmonth   = document.form1.startmonth.value;
         if(lastdatetime==null||lastdatetime==""){
          
         }else{
          
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
			     month=bmonth;
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
       fun();
 }
        //计算起始年月
        function qsyear(){
          
          var lastdatetime = document.form1.startdate.value;
         
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
         
</script>
</head>
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean">
</jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body onload ="fun()" class="body" style="overflow-x:hidden;">
<form action="" name="form1" method="POST">
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="4">
			<div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">预付费基本信息</span>	
			  </div>
			  <input type="hidden" name="accountname"  value="<%=accountname %>">
		</td>
	</tr>
  </table>
  <table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" >
<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
		<%
			
		    String dbid=bean.getPrepaymentammeterid();
		    System.out.println("电表id=="+dbid);
		    
		    String beilv = bean.getBeilv();
			if("".equals(beilv)||"null".equals(beilv)||beilv==null||"0".equals(beilv)){
				 beilv="1";
			}
		    	
		    String s="",sss="",mon="",netw="",adminn="",mar="",info="",build="",danjia="",actualdegree="",dddf="",pjje="";
		    String lastyue="",lastdf="",lastdl="",lastlch="";
		  
		    netw=bean.getNetworkdf();
		    adminn=bean.getAdministrativedf();
		    mar=bean.getMarketdf();
		    info=bean.getInformatizationdf();
		    build=bean.getBuilddf();
		    dddf=bean.getDddf();
		    danjia=bean.getDianfei();
		    
		    lastyue = bean.getLastyue();
		    lastdf = bean.getLastdf();
		    lastdl = bean.getLastdl();
		    lastlch = bean.getLastlch();
		    
		    if("".equals(lastlch)||lastlch.equals("null")||lastlch==null){
				 lastlch="";
			}
			 
			if("".equals(lastdl)||lastdl.equals("null")||lastdl==null){
				lastdl="0.00";
			}else{
				  DecimalFormat std=new DecimalFormat("0.00");
				  lastdl=std.format(Double.parseDouble(lastdl));
			}
			if("".equals(lastyue)||lastyue.equals("null")||lastyue==null){
				lastyue="0.00";
			}else{
				  DecimalFormat std=new DecimalFormat("0.00");
				  lastyue=std.format(Double.parseDouble(lastyue));
			}
			
			if("".equals(lastdf)||lastdf.equals("null")||lastdf==null){
				lastdf="0.00";
			}else{
				  DecimalFormat std=new DecimalFormat("0.00");
				  lastdf=std.format(Double.parseDouble(lastdf));
			}
		    
		    if(netw.equals("")||netw.equals(null)||netw.equals("null")){
		    netw="0";
		    }
		      
		    if(adminn.equals("")||adminn.equals(null)||adminn.equals("null")){
		    adminn="0";
		    }
		    if(mar.equals("")||mar.equals(null)||mar.equals("null")){
		    mar="0";
		    }
		    if(info.equals("")||info.equals(null)||info.equals("null")){
		    info="0";
		    }
		    if(build.equals("")||build.equals(null)||build.equals("null")){
		    build="0";
		    }
		     if(dddf.equals("")||dddf.equals(null)||dddf.equals("null")||dddf.equals(" ")){
		    dddf="0";
		    }
		   	if(danjia.equals("")||danjia.equals(null)||danjia.equals("null")){
		    	danjia="0.0000";
		    }
		     DecimalFormat dbil1=new DecimalFormat("0.00");
		     netw=dbil1.format(Double.parseDouble(netw));
		     adminn=dbil1.format(Double.parseDouble(adminn));
		     info=dbil1.format(Double.parseDouble(info));
		     mar=dbil1.format(Double.parseDouble(mar));
		     build=dbil1.format(Double.parseDouble(build));
		       dddf=dbil1.format(Double.parseDouble(dddf));
		     DecimalFormat danjia1=new DecimalFormat("0.0000");
		     danjia=danjia1.format(Double.parseDouble(danjia));		       
		    
		     ArrayList listt=new ArrayList();
		    listt=bean.getStationMhchexkt(dbid);
		    String shuoshuzhuanye="";
		    String dbili="",dbili1="",dbili2="",dbili3="",dbili4="",dbili5="",dbili6="";
		    System.out.println("listt=="+listt);
		    String sumdegree=bean.getSumdegree();//电量总计
		    
			if(sumdegree.equals("")||sumdegree.equals("null")||sumdegree==null){
			  sumdegree="0";
			}
			actualdegree=bean.getActualdegree();//电量总计
			if(actualdegree.equals("")||actualdegree.equals("null")||actualdegree==null){
			  actualdegree="0";
			}
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
			//System.out.println("所属专业"+shuoshuzhuanye+"电表比例"+dbili);
		    }
		    }
		  mon=bean.getMoney();
		   s= bean.getStartdegree();
		   sss=bean.getStopdegree();
		    if(mon.equals("")||mon.equals("null")||mon==null){
		   mon="0";
		   }
		  double  d=bean.getPjje();
		   pjje=d+"";
		   //System.out.println("jine=====:"+d);
	
		DecimalFormat mo=new DecimalFormat("0.00");
		   pjje=mo.format(d);
		   mon=mo.format(Double.parseDouble(mon));
		   if(s.equals("")||s.equals("null")||s==null){
		   s="0";
		   }
		   if(sss.equals("")||sss.equals("null")||sss==null){
		   sss="0";
		   }
		    DecimalFormat ss=new DecimalFormat("0.0");
			 s=ss.format(Double.parseDouble(s));
			 sss=ss.format(Double.parseDouble(sss));
		    String kpsj=bean.getKptime();
		    
		    if(kpsj==null||kpsj.equals("null")){
		      kpsj="";
		    }
		    
		  
		%>	 
			 
			 <tr bgcolor="F9F9F9" class="form">
               <td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" /><font size="2">基本信息</font></td>
             </tr>            
      <tr class="form"> 
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>电表ID：</div></td>  
         <td><input type="text" name="dbid" readonly="true" value="<%=bean.getPrepaymentammeterid()%>"  class="bddf" /></td> 
	  <td width="15%" bgcolor="#DDDDDD"><div>累计电量(度)：</div>
         </td>
         <td><input type="text" name="sumdegree" value="<%=sumdegree%>"  class="form1" /><span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td> 
	 
	  </tr>
      <tr class="form">
      	 <td height="19" bgcolor="#DDDDDD"><div>起始电表数：</div></td>
         <td><input type="text" name="startdegree" value="<%=s%>" onChange="getActualdegree1();fun();getCsdb()"  class="form1" /><span class="style1">&nbsp;*</span></td>
      	 <td height="19" bgcolor="#DDDDDD"><div>终止电表数：</div></td>
         <td><input type="text" name="stopdegree" value="<%=sss%>" onChange="getActualdegree1();fun();getCsdb()" class="form1" /><span class="style1">&nbsp;*</span></td>
      </tr>

     <tr class="form"> 
          <td width="15%" bgcolor="#DDDDDD"><div>实际用电量：</div>
         </td>
         <td><input type="text" name="actualdegree" id="actualdegree" value="<%=actualdegree%>" onChange="fun()" class="form1" /><span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td> 
         <td height="19" bgcolor="#DDDDDD"><div>金额：</div></td>
         <td><input type="text" name="money" value="<%=mon%>" onChange="getHaodianliang()" class="form1" /><span class="style1">&nbsp;*</span></td>
      </tr>
      <tr class="form">
     
         <td height="19" bgcolor="#DDDDDD"><div>起始时间：</div>
         </td>
         <td><input type="text" name="startdate" value="<%=bean.getStartdate() %>" 
         			class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" 
         			onpropertychange="changePreenddata();fun();getCsdb()" class="form" />
         			<span class="style1">&nbsp;*</span>
           </td>
            <td height="19" bgcolor="#DDDDDD"><div>预计结束时间：</div>
         </td>
         <td><input type="text" name="preenddate" value="<%=bean.getEnddate() %>" 
         			class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" 
         			onpropertychange="endmonthzq();fun();getCsdb()" class="form" />
         			<span class="style1">&nbsp;*</span>
           </td>
      </tr>
      <tr class="form">
       <td height="19" bgcolor="#DDDDDD"><div>票据类型</div>
         </td>
          <td>   
         <select name="notetypeid" class="form">
         		<option value="0">请选择</option>
	         <%
	         	ArrayList feetypelist1 = new ArrayList();
	         	feetypelist1 = commBean.getNoteType();
	         	if(feetypelist1!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)feetypelist1.get(0)).intValue();
	         		for(int i=scount;i<feetypelist1.size()-1;i+=scount)
                    {
                    	sfid=(String)feetypelist1.get(i+feetypelist1.indexOf("CODE"));
                    	sfnm=(String)feetypelist1.get(i+feetypelist1.indexOf("NAME"));
                    if(sfid.equals(bean.getNotetypeid())){
                    //System.out.println(bean.getFeetypeid()+sfid);
                    %>
                    <option value="<%=sfid%>" selected><%=sfnm%></option>
                    <%}else{ %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}  
                    }                 
	         	}
	         %>
	         </select><span class="style1">&nbsp;*</span>
          </td>  
        
      <td height="19" bgcolor="#DDDDDD"><div>票据编号</div>
         </td>
         <td><input type="text" name="noteno" value="<%=bean.getNoteno() %>"  class="form" />
         </td>
      </tr>
      <tr class="form">
         <td height="19" bgcolor="#DDDDDD"><div>票据时间</div>
         </td>
         <td><input type="text" name="notetime" value="<%=bean.getNotetime() %>" 
         			class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
         			class="form" maxlength="11"/>
         </td>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>开票时间</div>
         </td>
         <td><input type="text" name="kptime" value="<%=kpsj%>" 
         			class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="form" />
         </td>
         
      </tr>          
     
      <tr class="form">
        <td height="19" bgcolor="#DDDDDD"><div>收费人</div>
         </td>
         <td><input type="text" name="payoperator" value="<%=bean.getPayoperator() %>"  class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>收费日期</div>
         </td>
         <td><input type="text" name="paydatetime" value="<%=bean.getPaydatetime() %>" 
         			class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="form" />
           </td>
      </tr>
      <tr class="form">
         <td height="19" bgcolor="#DDDDDD"><div>起始月份：</div>
         </td>
         <td><input type="text" name="startmonth" value="<%=bean.getStartmonth() %>" 
         			class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" 
         			 class="form" />
         			<span class="style1">&nbsp;*</span>
           </td>
            <td height="19" bgcolor="#DDDDDD"><div>结束月份：</div>
         </td>
         <td><input type="text" name="endmonth" value="<%=bean.getEndmonth() %>" 
         			class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="form" />
         			<span class="style1">&nbsp;*</span>
           </td>
      </tr>
       <tr class="form">    
         <td height="19" bgcolor="#DDDDDD"><div>报账月份</div>
         </td>
         <td><input type="text" name="accountmonth" value="<%=bean.getAccountmonth() %>" 
         			class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="form" />
         			<span class="style1">&nbsp;*</span>
         </td> 
         <td height="19" bgcolor="#DDDDDD"><div>备注</div>
         </td>
         <td><input type="text" name="memo" value="<%=bean.getMemo() %>"  class="form" />
         </td>       
      </tr>
      <tr class="form">
     <!--  netw="",adminn="",mar="",info="",build="" -->
       <td width="15%" bgcolor="#DDDDDD"><div>生产：</div>
         </td>
         <td><input type="text" name="NETWORKDF" value="<%=netw%>"  class="form1" />
         </td> 
         <td width="15%" bgcolor="#DDDDDD"><div>办公：</div>
         </td>
         <td><input type="text" name="ADMINISTRATIVEDF" value="<%=adminn%>"  class="form1" />
         </td>    
      </tr>
      <tr class="form">    
         
         <td width="15%" bgcolor="#DDDDDD"><div>营业：</div>
         </td>
         <td><input type="text" name="MARKETDF" value="<%=mar%>"  class="form1" />
         </td>    
         <td width="15%" bgcolor="#DDDDDD"><div>信息化支撑：</div>
         </td>
         <td><input type="text" name="INFORMATIZATIONDF" value="<%=info%>"  class="form1" />
         </td> 
        
      </tr>
      <tr class="form"> <td width="15%" bgcolor="#DDDDDD"><div>建设投资：</div></td>
         <td><input type="text" name="BUILDDF" value="<%=build%>"  class="form1" /></td>
         <td width="15%" bgcolor="#DDDDDD"><div>代垫电费：</div></td>
         <td><input type="text" name="DDDF" value="<%=dddf%>"  class="form1" /></td> 
	    
         <td ><input type="hidden" name="dbili1" value="<%=dbili1%>"></td>
         <td ><input type="hidden" name="dbili2" value="<%=dbili2%>"></td>
         <td ><input type="hidden" name="dbili3" value="<%=dbili3%>"></td>
         <td ><input type="hidden" name="dbili4" value="<%=dbili4%>"></td>
         <td ><input type="hidden" name="dbili5" value="<%=dbili5%>"></td>
         <td ><input type="hidden" name="dbili6" value="<%=dbili6%>"></td>
         <td ><input type="hidden" name="danjia" value="<%=danjia%>"></td>
          </tr>
              <tr class="form">
              <td  height="19" bgcolor="#DDDDDD" width="15%"><div>票据金额：</div></td>
              <td><input type="text" id="pjje" name="pjje" value="<%=pjje%>" class="form1"><span class="style1">&nbsp;*</span></td>
        
             <td height="19" bgcolor="#DDDDDD" width="15%"><div>费用类型：</div></td>  
         <td><select name="feetype" class="form">
         		<option value="0">请选择</option>
	         <%
	         	ArrayList feetypelist = new ArrayList();
	         	feetypelist = commBean.getFeeType();
	         	if(feetypelist!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)feetypelist.get(0)).intValue();
	         		for(int i=scount;i<feetypelist.size()-1;i+=scount)
                    {
                    	sfid=(String)feetypelist.get(i+feetypelist.indexOf("CODE"));
                    	sfnm=(String)feetypelist.get(i+feetypelist.indexOf("NAME"));
                    if(sfid.equals(bean.getFeetypeid())){
                    //System.out.println(bean.getFeetypeid()+sfid);
                    %>
                    <option value="<%=sfid%>" selected><%=sfnm%></option>
                    <%}else{ %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
                    }
	         	}
	         %>
	        </select><span class="style1">&nbsp;*</span></td>   
              </tr> 
           <tr class="form">
           	<td height="19" bgcolor="#DDDDDD"><div>上期电费:</div>
         	</td>
         	<td><input type="text" name="lastdf" value="<%=lastdf %>" readonly="readonly" class="form" />
         	</td>  
         	<td height="19" bgcolor="#DDDDDD"><div>上期电量:</div>
         	</td>
         	<td><input type="text" name="lastdl" value="<%=lastdl %>" readonly="readonly" class="form" />
           </td>  
           </tr>
           <tr class="form">
           	<td height="19" bgcolor="#DDDDDD"><div>上期余额:</div>
         	</td>
         	<td><input type="text" name="lastyue" value="<%=lastyue%>" readonly="readonly" class="form" />
         	</td>  
         	<td height="19" bgcolor="#DDDDDD"><div>上期流程号:</div>
         	</td>
        	<td><input type="text" name="lastlch" value="<%=lastlch %>" readonly="readonly" class="form" />
           </td>  
           </tr>
           <tr class="form">
           <td height="19" bgcolor="#DDDDDD"><div>倍率:</div>
         	</td>
        	<td><input type="text" name="beilv" value="<%=beilv %>" readonly="readonly" class="form" />
           </tr>
           <tr class="form">
              	<td colspan="2"><input id='kongbai' readonly="readonly" value = "" class="form1"  type='text'  style="width: 300px"></td>
          		<td colspan="2"><input id='csdbdiv' readonly="readonly"  class="form1"  type='text' value="" style="width: 300px"><input id='kongbai1' readonly="readonly"  class="form1"  type='hidden'  style="width: 300px"></td>
          		<td ><input type="hidden" name="dedhdlbz" id="dedhdlbz" value="<%=dedhdlbz%>"></td>
          		<td ><input type="hidden" name="qsdbdlbz" id="qsdbdlbz" value="<%=qsdbdlbz%>"></td>
          	  	<td ><input type="hidden" name="qsdbdl" value="<%=qsdbdl%>"></td> 
          	  	<td ><input type="hidden" name="edhdl" value="<%=edhdl%>"></td> 
          	  	<td ><input type="hidden" name="property" value="<%=property%>"></td>
          	  	<td ><input type="hidden" name="scb" value="<%=scb%>"></td>
          	  	<td ><input type="hidden" name="shicode" value="<%=shicode%>"></td>
          	  	<td ><input type="hidden" name="zlfh" value="<%=zlfh%>"></td>
          	  	<td ><input type="hidden" name="jlfh" value="<%=jlfh%>"></td>
          	  	<td ><input type="hidden" name="stationtypecode" value="<%=stationtypecode%>"></td>
          	  	<td ><input type="hidden" name="dfzflxcode" value="<%=dfzflxcode%>"></td>
          	  	<td ><input type="hidden" name="gdfscode" value="<%=gdfscode%>"></td>
           </tr>     
            
      <tr>
        <td align="right" colspan="4">
          
           <div id="baocun" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 330px">
				<img src="<%=path%>/images/baocun.png" width="100%"
					height="100%">
				<span
					style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">保存</span>
			</div>
			
			 <div id="cancelBtn" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 200px">
				<img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
					<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
			</div>
        </td>
      </tr>


      </table>
				
    
     
<input type="hidden" name = "prepayId" value="<%=prepayId%>"/>
<input type="hidden" name = "stationid" value="<%=request.getParameter("stationid")%>"/>
</form>

</body>
</html>
<script type="text/javascript">
var XMLHttpReq;
	//XMLHttpReq = createXMLHttpRequest();
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	
	///////////////////////////////////////////////////////////
	function sendRequest(url,para) {
		createXMLHttpRequest();	
		XMLHttpReq.open("POST", url, true);	
		if(para == 'getCsdb'){
			XMLHttpReq.onreadystatechange = processResponse_csdb;//指定响应函数
		}else if(para == 'amid'){			
           XMLHttpReq.onreadystatechange = processResponse;//指定响应函数		
        }else{
           XMLHttpReq.onreadystatechange = processResponse_preenddata;//指定响应函数
        }
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
	}
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseText;  	
            	 //alert(res);
            	 document.form1.startdegree.value=res;
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    function processResponse_preenddata() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseText;  	
            	 //alert(res);
            	 document.form1.preenddate.value=res;
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function changeAmid(){
	var amid = document.form1.amid.value;
	//alert(amid);
	if(amid=="0"){
	    alert("请选择电表ID！");
		return;
	}else{
		sendRequest(path+"/servlet/prepayment?action=startdegree&amid="+amid,"amid");

	}
}
function changePreenddata(){
	var startdate = document.form1.startdate.value;
	var amid = document.form1.dbid.value;
	var money = document.form1.money.value;
	//alert(startdate);
	if(startdate=="0"){
	    alert("请选择初始时间！");
		return;
	}else{
		sendRequest(path+"/servlet/prepayment?action=preenddata&startdate="+startdate+"&amid="+amid+"&money="+money,"preenddata");

	}
}

function processResponse_csdb() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseText;
			document.getElementById("csdbdiv").value="该记录超省标:"+res;
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function getCsdb() {
	var actualdegree = document.form1.actualdegree.value;
	var qsdbdl = document.form1.qsdbdl.value;
	var stationtype = document.form1.stationtypecode.value;
	var zlfh = document.form1.zlfh.value;
	var jlfh = document.form1.jlfh.value;
	var property = document.form1.property.value;
	var edhdl = document.form1.edhdl.value;
	var scb = document.form1.scb.value;
	var dbid = document.form1.dbid.value;
	var shicode = document.form1.shicode.value;
	var preenddate = document.form1.preenddate.value;
	var startdate = document.form1.startdate.value;
	var stopdegree = document.form1.stopdegree.value;
	var startdegree = document.form1.startdegree.value;
	var beilv = document.form1.beilv.value;
	var kong1 = document.form1.preenddate.value.replace(/[ ]/g,"");
	var kong2 = document.form1.startdate.value.replace(/[ ]/g,"");
	var kong3 = document.form1.stopdegree.value.replace(/[ ]/g,"");
	var kong4 = document.form1.startdegree.value.replace(/[ ]/g,"");
	if(parseFloat(actualdegree)==actualdegree
			&&isNaN(stopdegree)==false
			&&isNaN(startdegree)==false
			&& kong1!="" && kong2!=""&&kong3!="" &&kong4!="" 
			&& preenddate >= startdate
			&& Number(zlfh)!=0
			&& Number(jlfh)!=0
			&& Number(edhdl)!=0
			&& Number(qsdbdl)!=0
			&& Number(scb)!=0
			&& property!="" && property!="null"){
	sendRequest(path
+"/servlet/prepayment?action=getCsdb&actualdegree="+actualdegree+"&qsdbdl="+qsdbdl+"&stationtype="+stationtype+"&zlfh="
+zlfh+"&jlfh="+jlfh+"&property="+property+"&edhdl="+edhdl
+"&scb="+scb+"&dbid="+dbid+"&shicode="+shicode+"&preenddate="+preenddate
+"&startdate="+startdate+"&stopdegree="+stopdegree+"&startdegree="+startdegree+"&beilv="+beilv,"getCsdb");
}else{
	document.getElementById("csdbdiv").value="该记录超省标:";
}
}
</script>