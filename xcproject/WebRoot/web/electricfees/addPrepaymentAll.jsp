<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@page import="com.noki.electricfees.javabean.PrepaymentFormBean"%>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Date" %>
<%
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
        String roleId = account.getRoleId();
        String accountname=account.getAccountName();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
    	 String entrytime123=df.format(new Date());
    	 String mo=request.getParameter("money")!=null?request.getParameter("money"):"0.00";
    	 String ss=request.getParameter("pjje")!=null?request.getParameter("pjje"):"0.00";
    	  ss=mo;
    	  System.out.println("mo:"+mo);
    	  
    	   /*
          获取六条分摊
**/
AmmeterDegreeFormBean beanm = new AmmeterDegreeFormBean();
ArrayList listt=new ArrayList();
String aid = request.getParameter("ammeterid");
listt=beanm.getStationMhchexkt(aid);

String shuoshuzhuanye="";
String dbili="",dbili1="",dbili2="",dbili3="",dbili4="",dbili5="",dbili6="",zh="0";
double hj=0.0;
//   String xjbl="",xjbl1="",xjbl2="",xjbl3="",xjbl4="",xjbl5="",xjbl6="";

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
	color: #FF9900;
	font-weight: bold;
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 #id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
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
		line-height:120%;
		text-align: right;
		
}
.bddf1{
border-left-width:0px; border-top-width:0px; border-right-width:0px;
width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		text-align: left;
}.bddf{
border-left-width:0px; border-top-width:0px; border-right-width:0px;
width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		text-align: right;
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
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
 <script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
 <script>window.onload=function(){ 
	 var bz='<%=bzw9%>';
	var fthj='<%=zh%>';
	if(fthj!="100.0000000"||bz=="0"){
	alert("该电表没有做分摊或者分摊有异常，请到监测点管理进行修改");
	return;

}
		endmonthzq();		
	}
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
function change(){
document.form1.endmonth.value=document.form1.startmonth.value;
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
		var qsdbdl = document.form1.qsdbdl.value;
		var edhdl =  document.form1.edhdl.value;
		var zlfh = document.form1.zlfh.value;
		var jlfh = document.form1.jlfh.value;
		var scb = document.form1.scb.value;
		var property = document.form1.property.value;
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
        	
	  var std=document.form1.pjje.value;
	  if(""==std||null==std){std="0.00";}
	  
	  
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
	        if(checkNotnull(document.form1.stationid,"站点ID")&&   //不能为空   如果为空显示 “”里的内容
                  checkNotSelected(document.form1.dbid,"电表ID")&&checkNotnull(document.form1.startdegree,"起始电表读数")&& //判断选择框是否为空
                 checkNotnull(document.form1.sumdegree,"累计电量")&& checkNotnull(document.form1.money,"金额")&&//判断输入框是否为空
        		  checkNotnull(document.form1.stopdegree,"终止电表读数")&&checkNotSelected(document.form1.feetype,"费用类型")&&
        		    checkNotnull(document.form1.startmonth,"起始月份")&&checkNotnull(document.form1.endmonth,"结束月份")&&
        		     checkNotnull(document.form1.startdate,"起始时间")&&checkNotnull(document.form1.preenddate,"预计结束时间")&&
        		     checkNotSelected(document.form1.notetypeid,"票据类型")&&checkNotnull(document.form1.pjje,"票据金额")&&  
        		     checkNotnull(document.form1.dfzflx,"电费支付类型")&&checkNotnull(document.form1.accountmonth,"报账月份")){
        		   	var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
					var sd = document.form1.startdate.value;
					var pd = document.form1.preenddate.value;
					var moneyqk = document.form1.money.value.replace(/[ ]/g,"");
					var ntt = document.form1.notetime.value;									
					var pdt = document.form1.paydatetime.value;
					var moneyqk1 = document.form1.pjje.value.replace(/[ ]/g,"");
                   var money=(parseFloat(document.form1.money.value)).toFixed(0);
					var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
					var acm = document.form1.accountmonth.value;
					var sm = document.form1.startmonth.value;
					var em = document.form1.endmonth.value;
					//var yue = parseFloat(document.form1.lastyue.value);
					
					var am = document.form1.accountmonth.value;
					if(isDate(ntt)==true||ntt==""||ntt==null){
							if(isDate(pdt)==true||pdt==""||pdt==null){
								if(reg1.exec(am)||am==""||am==null){
									if(isDate(sd)==true){
										if(isDate(pd)==true){
											if(reg1.exec(sm)){
												if(reg1.exec(em)){
									        		     if(pd>=sd){
											        		     if(isNaN(document.form1.stopdegree.value)==false){        		     
												        		     if(isNaN(document.form1.startdegree.value)==false){
														        		     if(em>=sm){
														        		     	if(isNaN(document.form1.pjje.value)==false&&moneyqk1!=""){
																	        		     if(isNaN(document.form1.money.value)==false&&moneyqk!=""){
																	        		     								   if(reg1.exec(acm)){
																	        		     								        	if(dd==money){
																	        		     								        	if(isNaN(document.form1.sumdegree.value)==false){	     
																		                       if(isNaN(document.form1.pjje.value)==false){
																		                    	   //if(yue<200){
																				                        if(confirm("您将要添加费用信息！确认信息正确！")){
																						                    document.form1.action=path+"/servlet/prepayment?action=add"
																						        			document.form1.submit();
																						        			 showdiv("请稍等..............");
																				                         }
																			                       // }else{
																			                       // 	alert("上期余额大于200，不能申请预支!");
																			                       // }
																		                         }else{
																		                          alert("您输入的信息有误！票据金额必须为数字！");
																		                         }
																		                         }else{
																		                         alert("您输入的信息有误！累计电量必须为数字！");
																		                         }
																		                        }else{
																		                        alert("总金额与分摊金额不符！请确认！");
																		                        }
																		                         }else{
																		                         alert(" 您输入的报账月份有误，请确认后重新输入！");
																		                         }
																	                      }else{
																	                      		alert("您输入的信息有误，金额必须为数字！");
																	                      }
																                 }else{
																	                 alert("您输入的信息有误，票据金额必须为数字！");
																	             }
														                      }else{
														                         alert("您输入的信息有误，结束月份必须大于等于起始月份！");
														                      }
												                      }else{
												                      	alert("您输入的信息有误，起始电表读数必须为数字！");
												                      }
											                     }else{
											                          alert("您输入的信息有误，终止电表读数必须为数字！");
											                    }
									            		}else{
									                         alert("您输入的信息有误，预计结束时间必须大于等于起始时间！");
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
	 function endmonthzq(){
          qsyear();
         var thisdatetime = document.form1.preenddate.value;//预计结束时间
         var lastdatetime = document.form1.startdate.value;//起始时间
         var startmonth   = document.form1.startmonth.value;//起始月份
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
	
	
	
	
	
	
	
	
	
        function showIdList(){
        
               window.open('PrepaymentBrowseAmmeter.jsp','','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
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
        function getHaodianliang(){
        
          // alert("-===");
        	// var bl=document.form1.mpower.value;
        	// if(bl==null||bl=="")bl="1"
        	var money=document.form1.m
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
		var edhdl = document.getElementById('edhdl').value;
		var qsdbdl = document.getElementById('qsdbdl').value;
	
		
			var nd = 1000 * 24 * 60 * 60;//一天多少毫秒
			if(thisdate!=null&&thisdate!=""){
			var actualdegree = document.getElementById('actualdegree').value;
			
			var date1= new Date(Date.parse(lastdate.replace(/-/g,"/"))); //转换成Data();
			var date2= new Date(Date.parse(thisdate.replace(/-/g,"/"))); //转换成Data();
			lastdate = date1.getTime();
			thisdate = date2.getTime();
			//两个时间之间的天数
			var ld=(thisdate-lastdate)/nd + 1;//相差天数
			var bb=parseFloat(actualdegree);//倍率耗电量
			var ee=parseFloat(edhdl)*parseFloat(ld);
			var qq=parseFloat(qsdbdl)*parseFloat(ld);//全省定标
	
			if("0"==qsdbdl||"0.0"==qsdbdl||"0.00"==qsdbdl||"null"==qsdbdl||""==qsdbdl ||  null == qsdbdl){
				document.form1.kongbai1.value="系统中的全省定标电量是空值！！！";	
				document.form1.qsdbdlbz.value="";	
			}else{
				if("0"==qq||"0.0"==qq||"0.00"==qq){
					document.form1.kongbai1.value="";
					document.form1.qsdbdlbz.value="";	
				}else{
					var qqbili=(((bb-qq)/qq)*100).toFixed(2);
					document.form1.kongbai1.value="超全省定标的"+qqbili+"%!";
					document.form1.qsdbdlbz.value=qqbili/100;	
				}
			}
			if(edhdl!=""&&edhdl!=null){
				if("0"==ee||"0.0"==ee||"0.00"==ee){
					document.form1.kongbai.value="";
					document.form1.dedhdlbz.value="";
				}else{
					var bili=(((bb-ee)/ee)*100).toFixed(2);
					document.form1.kongbai.value="该记录超本地标"+bili+"%!";
					document.form1.dedhdlbz.value=bili/100;
				}	
			}else{
				    document.form1.kongbai.value="系统中的额定耗电量是空值！！！";
				    document.form1.dedhdlbz.value="";
			}
		}
	}
         
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
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
 
				
						
							<td colspan=3 width="600" background="<%=path%>/images/btt.bmp" height=50><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加预付费</span></td>
							<input type="hidden" name="accountname"  value="<%=accountname %>">
							<td width="380">&nbsp;</td>
					</tr>
					</table>
			
		
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="4"></td>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1" class="form_label">

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>

        <%		
			String dbid = request.getParameter("ammeterid") != null ? request.getParameter("ammeterid") : "" ;
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    bean = bean.getAmmeterDegreeAllInfoa(dbid);//2014-11-13加a
		   String beilv = bean.getMultiplyingpower();
		   if("".equals(beilv)||"null".equals(beilv)||beilv==null||"0".equals(beilv)){
			   beilv="1";
			}
		 AmmeterDegreeFormBean bean1 = new AmmeterDegreeFormBean();
		 bean1=bean1.getLastAmmeterDegreeYf(dbid);
		    //bean.getStartdate();
		  //  ArrayList listt=new ArrayList();
		    listt=bean.getStationMhchexkt(dbid);
		  //  String shuoshuzhuanye="";
		   // String dbili="",dbili1="",dbili2="",dbili3="",dbili4="",dbili5="",dbili6="";
		 // if(listt!=null)
		//{
		//	int fycount = ((Integer)listt.get(0)).intValue();
		//	for( int k = fycount;k<listt.size()-1;k+=fycount){
		//	     shuoshuzhuanye = (String)listt.get(k+listt.indexOf("SHUOSHUZHUANYE"));
		//    shuoshuzhuanye = shuoshuzhuanye != null ? shuoshuzhuanye : "";
		//    dbili = (String)listt.get(k+listt.indexOf("DBILI"));
		//    dbili = dbili != null ? dbili : "0";
		//    if(shuoshuzhuanye.equals("zylx01")){
		 //      dbili1=dbili;
		 //   }
		 //    if(shuoshuzhuanye.equals("zylx02")){
		 //     dbili2=dbili;
		 //   }
		 //    if(shuoshuzhuanye.equals("zylx03")){
		 //     dbili3=dbili;
		 //   }
		 //    if(shuoshuzhuanye.equals("zylx04")){
		 //     dbili4=dbili;
		 //   }
		 //    if(shuoshuzhuanye.equals("zylx05")){
		   //   dbili5=dbili;
		  //  }
		  //    if(shuoshuzhuanye.equals("zylx06")){
		  //    dbili6=dbili;
		  //  }
		  //  }
		  //  }
		    
		   // String prepayId = request.getParameter("prepayId");
			PrepaymentFormBean beans = new PrepaymentFormBean();
		    beans = beans.getStopDegreeAllInfo(dbid);
		 String startdegree= bean1.getLastdegree();
		 String startdate=bean1.getLastdatetime();
		 
		 AmmeterDegreeFormBean bean2 = new AmmeterDegreeFormBean();
		 bean2=bean2.getLast(dbid);
		 String lastlch="",lastdl="",lastyue="",lastdf="";
		 lastlch = bean2.getLastlch();
		 
		 lastdl = bean2.getLastdl();
		 lastyue = bean2.getLastyue();
		 lastdf = bean2.getLastdf();
		 
		 String dianfei= bean.getDianfei();//单价
		// String sumdegree=bean.getSumdegree();//电量总计
		 if("".equals(startdegree)||startdegree.equals("null")||startdegree==null){
		 	 startdegree="0.0";
		 }else{
		  	DecimalFormat std=new DecimalFormat("0.0");
		 	startdegree=std.format(Double.parseDouble(startdegree));
		 }
		 if(startdate.equals("")||startdate.equals("null")||startdate==null){
		  startdate="";
		 }
		 
		 if("".equals(lastlch)||"null".equals(lastlch)||lastlch==null){
			 lastlch="";
		}
		 
		if("".equals(lastdl)||"null".equals(lastdl)||lastdl==null){
			lastdl="0.00";
		}else{
			  DecimalFormat std=new DecimalFormat("0.00");
			  lastdl=std.format(Double.parseDouble(lastdl));
		}
		if("".equals(lastyue)||"null".equals(lastyue)||lastyue==null){
			lastyue="0.00";
		}else{
			  DecimalFormat std=new DecimalFormat("0.00");
			  lastyue=std.format(Double.parseDouble(lastyue));
		}
		
		if("".equals(lastdf)||"null".equals(lastdf)||lastdf==null){
			lastdf="0.00";
		}else{
			  DecimalFormat std=new DecimalFormat("0.00");
			  lastdf=std.format(Double.parseDouble(lastdf));
		}
		 
//		 if(sumdegree.equals("")||sumdegree.equals("null")||sumdegree==null){
//		  sumdegree="0";
//		 }
		  DecimalFormat sum=new DecimalFormat("0.0");
//		sumdegree=sum.format(Double.parseDouble(sumdegree));
		 if(dianfei.equals("")||dianfei.equals("null")||dianfei==null){
		  dianfei="0";
		 }
		 DecimalFormat s=new DecimalFormat("0.0000");
		dianfei=s.format(Double.parseDouble(dianfei));
		    String stop = beans.getStopdegree();
		    
		    String area = bean.getProvinceid()+" "+bean.getCityid()+" "+bean.getCountryid();
		   
		    if(stop==null||stop.equals("")) stop = "";
		    
		    String shicode = bean.getShicode();
		    String property = bean.getProperty();
		    String scb = bean.getScb();
		    String zlfh = bean.getZlfh();
		    String jlfh = bean.getJlfh();
		    String gdfs = bean.getGdfscode();
		    String dfzflxcode = bean.getDfzflxcode();
		    String stationtype = bean.getStationtypecode();
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
		    if(null==gdfs){
		    	gdfs = "";
		    }
		    if(null==dfzflxcode){
		    	dfzflxcode = "";
		    }
		    if(null==stationtype){
		    	stationtype = "";
		    }

		%>
		<tr height="23px"><td>&nbsp;</td></tr> 
		<tr bgcolor="F9F9F9">
           <td height="19" colspan="4">　<img src="../../images/v.gif" width="15" height="15" />基本信息</td>
       </tr>
       	<tr><td>&nbsp;</td></tr>
      <tr class="selected_font">
         <td  bgcolor="#DDDDDD" width="15%"><div>站点ID：</div>
         </td>
         <td width="20%"><input type="text" name="stationid" readonly="true" value="<%=bean.getStationid() %>"  class="bddf1" />
           
         </td>
 
         <td  bgcolor="#DDDDDD" width="15%"><div>站点名称：</div>
         </td>
         <td width="20%"><input type="text" name="stationname" readonly="true" value="<%=bean.getStationname() %>"  class="bddf1" />

         </td>
 <td  bgcolor="#DDDDDD" width="15%"><div>地区：</div>
         </td>
         <td width="20%"><input type="text" name="area" readonly="true" value="<%=area%>"  class="bddf1" />
         </td>

      <tr>
     
         
      <td width="15%" bgcolor="#DDDDDD"><div>集团报表类型：</div>
         </td>
         <td><input type="text" name="stationtypeid" readonly="true" value="<%=bean.getStationtypeid() %>"  class="bddf1" maxlength="11" readonly="true"/>
         </td>
                  <td bgcolor="#DDDDDD" width="15%"><div>是否标杆：</div>
         </td>
         <td><input type="text" name="isbenchmarkstation" readonly="true" value="<%=bean.getIsbenchmarkstation() %>"  class="bddf1" />
         </td>
         
         <td width="15%" bgcolor="#DDDDDD"><div>站点别名：</div>
         </td>
         <td><input type="text" name="stationaliasname" readonly="true" value="<%=bean.getStationaliasname() %>"  class="bddf1" maxlength="11"/>
         </td>
      </tr>
     <tr><td>&nbsp;</td></tr>
                    
                     <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />电表信息</td>
                    </tr>
                    <tr><td>&nbsp;</td></tr> 
      <tr> 
          <td  bgcolor="#DDDDDD" width="15%"><div>电表ID：</div>
         </td> 
         <td><input type="text" name="dbid" readonly="true" value="<%=bean.getAmmeterid() %>"  class="bddf1" /><span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
	         <img id="liulan" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png" style="cursor: pointer"/>
          </td> 
          <td  bgcolor="#DDDDDD" width="15%"><div>单价(元)：</div>
         </td> 
         <td><input type="text" name="dianfei" readonly="true" value="<%=dianfei%>"  class="bddf" />
         
          </td> 
      <td width="15%" bgcolor="#DDDDDD"><div>累计电量(度)：</div>
         </td>
         <td><input type="text" name="sumdegree" value="0.0"  class="selected_font1" /><span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td> 
      </tr>
      <tr>
      	 <td width="15%" bgcolor="#DDDDDD"><div>起始电表数：</div>
         </td>
         <td><input type="text" name="startdegree" value="<%=startdegree %>" onChange="getActualdegree1();fun();getCsdb()" class="selected_font1" /><span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td> 
          <td width="15%" bgcolor="#DDDDDD"><div>终止电表数：</div>
         </td>
         <td><input type="text" name="stopdegree" value="" onChange="getActualdegree1();fun();getCsdb()" class="selected_font1" /><span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td> 
          <td width="15%" bgcolor="#DDDDDD"><div>实际用电量：</div>
         </td>
         <td><input type="text" name="actualdegree" id="actualdegree" value="" onChange="fun()" class="selected_font1" /><span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>   
      </tr>

     <tr> 
          
           <td width="15%" bgcolor="#DDDDDD"><div>金额：</div>
         </td>
         <td><input type="text" name="money" value="" onChange="getHaodianliang()" class="selected_font1" /><span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
          <td width="15%" bgcolor="#DDDDDD"><div>起始时间：</div>
         </td>
         <td><input type="text" name="startdate" value="<%=startdate%>" class="selected_font"  
         			readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" 
         			onpropertychange="changePreenddata();fun();getCsdb()" class="form" />
        <span style="color: #FF0000;font-weight: bold">*</span>
           </td>
            <td width="15%" bgcolor="#DDDDDD"><div>预计结束时间：</div>
         </td>
         <td><input type="text" name="preenddate" class="selected_font"
         			value="<%if(null!=request.getParameter("preenddate")) out.print(request.getParameter("preenddate")); %>"
         			readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
         			onpropertychange="endmonthzq();fun();getCsdb()"  />
         <span style="color: #FF0000;font-weight: bold">*</span>
           </td>
      </tr>
        <tr>
         <td width="15%" bgcolor="#DDDDDD"><div>起始月份：</div>
         </td>
         <td><input type="text" name="startmonth" class="selected_font" 
         			value="<%if(null!=request.getParameter("startmonth")) out.print(request.getParameter("startmonth")); %>" 
					readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
         <span style="color: #FF0000;font-weight: bold">*</span>
           </td>
            <td width="15%" bgcolor="#DDDDDD"><div>结束月份：</div>
         </td>
         <td><input type="text" name="endmonth" class="selected_font"
         			value="<%if(null!=request.getParameter("endmonth")) out.print(request.getParameter("endmonth")); %>" 
					readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
         <span style="color: #FF0000;font-weight: bold">*</span>
           </td>
           <td width="15%" bgcolor="#DDDDDD"><div>报账月份：</div>
         </td>
         <td><input type="text" name="accountmonth" value="<%=entrytime123%>" class="selected_font" 
         			readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
         <span style="color: #FF0000;font-weight: bold">*</span>
         </td> 
      </tr>
      
      <tr>
        
        <td width="15%" bgcolor="#DDDDDD"><div>电费支付类型：</div>
         </td>
         <td><input type="text" name="dfzflx" value="<%=bean.getDfzflx() %>" class="selected_font"   readonly="true"/>

         </td>
         <td width="15%" bgcolor="#DDDDDD"><div>收费人：</div>
         </td>
         <td><input type="text" name="payoperator" value=""  class="selected_font"  />
         </td>
         <td width="15%" bgcolor="#DDDDDD"><div>收费日期：</div>
         </td>
         <td><input type="text" name="paydatetime" class="selected_font" 
					value="<%if(null!=request.getParameter("paydatetime")) out.print(request.getParameter("paydatetime")); %>" 
					readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
           </td>
      </tr>          
     
     
      <tr>
      <td width="15%" bgcolor="#DDDDDD"><div>票据类型：</div>
         </td>
          <td>   
         <select name="notetypeid" class="selected_font">
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
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
	         </select><span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
	         
          </td>  
        
      <td width="15%" bgcolor="#DDDDDD"><div>票据编号：</div>
         </td>
         <td><input type="text" name="noteno" value=""  class="selected_font"  />
         </td>
          <td width="15%" bgcolor="#DDDDDD"><div>票据时间：</div>
         </td>
         <td><input type="text" name="notetime" class="selected_font"  maxlength="11"
					value="<%if(null!=request.getParameter("notetime")) out.print(request.getParameter("notetime")); %>" 
					readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
         </td>
      </tr>
       <tr>    
         
         <td width="15%" bgcolor="#DDDDDD"><div>备注：</div>
         </td>
         <td><input type="text" name="memo" value=""  class="selected_font" />
         </td>  
         <td  bgcolor="#DDDDDD" width="15%"><div>费用类型：</div>
         </td>  
         <td>   
         <select name="feetype" class="selected_font">
         		<option value="0">请选择</option><span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
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
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
	         </select>
	          <span style="color: #FF0000;font-weight: bold">*</span>
          </td>  
             <td height="19" bgcolor="#DDDDDD"><div>票据金额：</div>
         </td>
         <td><input type="text" name="pjje" value=""  class="selected_font1" /><span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td> 
      </tr>
      
      <tr>
         <td width="15%" bgcolor="#DDDDDD"><div>上期电费：</div>
         </td>
         <td><input type="text" name="lastdf" value="<%=lastdf %>" readonly="readonly" class="selected_font1" />
         </td> 
         <td width="15%" bgcolor="#DDDDDD"><div>上期电量：</div>
         </td>
         <td><input type="text" name="lastdl" value="<%=lastdl %>" readonly="readonly" class="selected_font1" />
         </td> 
         <td width="15%" bgcolor="#DDDDDD"><div>上期余额：</div>
         </td>
         <td><input type="text" name="lastyue" value="<%=lastyue %>" readonly="readonly" class="selected_font1" />
         </td> 
       </tr>
       <tr>
        <td width="15%" bgcolor="#DDDDDD"><div>上期流程号：</div>
        </td>
        <td><input type="text" name="lastlch" value="<%=lastlch %>" readonly="readonly" class="selected_font1" />
        </td> 
        <td width="15%" bgcolor="#DDDDDD"><div>倍率：</div>
        </td>
        <td><input type="text" name="beilv" value="<%=beilv %>" readonly="readonly" class="selected_font1" />
        </td>
        <td width="15%" bgcolor="#DDDDDD"><div>生产标：</div>
        </td> 
        <td><input type="text" name="scb1" value="<%=scb%>" readonly="readonly" class="selected_font1" />
        </td> 
       </tr>
      
      <tr>
      	<td colspan="2"><input id='kongbai' readonly="readonly"  class="form1"  type='text'  style="width: 100%"></td>
	    <td colspan="2"><input id='csdbdiv' readonly="readonly"  class="form1"  type='text' value="" style="width: 100%">
	    <input id='kongbai1' readonly="readonly"  class="form1"  type='hidden'  style="width: 100%"></td>
      </tr>
      <td ><input type="hidden" name="edhdl" id="edhdl" value="<%=bean.getEdhdl()%>"></td>
      <td ><input type="hidden" name="qsdbdl" id="qsdbdl" value="<%=bean.getQsdbdl()%>">
       <input type="hidden" name="property"  value="<%=property%>">
      <input type="hidden" name="shicode"  value="<%=shicode%>">
      <input type="hidden" name="zlfh"  value="<%=zlfh%>">
      <input type="hidden" name="jlfh"  value="<%=jlfh%>">
      <input type="hidden" name="scb"  value="<%=scb%>">
      <input type="hidden" name="gdfs"  value="<%=gdfs%>">
      <input type="hidden" name="stationtype"  value="<%=stationtype%>"/>
      <input type="hidden" name="dfzflxcode"  value="<%=dfzflxcode%>"></td>
      
         <td ><input type="hidden" name="dbili1" value="<%=dbili1%>"></td>
         <td ><input type="hidden" name="dbili2" value="<%=dbili2%>"></td>
         <td ><input type="hidden" name="dbili3" value="<%=dbili3%>"></td>
         <td ><input type="hidden" name="dbili4" value="<%=dbili4%>"></td>
         <td ><input type="hidden" name="dbili5" value="<%=dbili5%>"></td>
          <td ><input type="hidden" name="dbili6" value="<%=dbili6%>"></td>
            <td ><input type="hidden" name="dedhdlbz" id="dedhdlbz" value=""></td>
          <td ><input type="hidden" name="qsdbdlbz" id="qsdbdlbz" value=""></td>
      </tr>
<tr bgcolor="F9F9F9">
           <td height="19" colspan="4">　<img src="../../images/v.gif" width="15" height="15" />分摊信息</td>
       </tr>
       <tr>
          
         <td width="15%" bgcolor="#DDDDDD"><div>生产：</div>
         </td>
         <td><input type="text" name="NETWORKDF" value="0.00"  class="selected_font1" />
         </td> 
         <td width="15%" bgcolor="#DDDDDD"><div>办公：</div>
         </td>
         <td><input type="text" name="ADMINISTRATIVEDF" value="0.00"  class="selected_font1" />
         </td>    
         <td width="15%" bgcolor="#DDDDDD"><div>营业：</div>
         </td>
         <td><input type="text" name="MARKETDF" value="0.00"  class="selected_font1" />
         </td>    
       </tr>
       <tr>    
         
         
         <td width="15%" bgcolor="#DDDDDD"><div>信息化支撑：</div>
         </td>
         <td><input type="text" name="INFORMATIZATIONDF" value="0.00"  class="selected_font1" />
         </td> 
         <td width="15%" bgcolor="#DDDDDD"><div>建设投资：</div>
         </td>
         <td><input type="text" name="BUILDDF" value="0.00"  class="selected_font1" />
         </td>    
         <td width="15%" bgcolor="#DDDDDD"><div>代垫电费：</div>
         </td>
         <td><input type="text" name="DDDF" value="0.00"  class="selected_font1" />
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
					<br/>
					<br/>
		<table align="right"  border="0" cellspacing="0" cellpadding="0" width="90%">
<tr >
        <td colspan="4">
        	<div id="cancelBtn" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right:3px">
				<img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
				<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
			</div>
           <div id="resetBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:12px">
			 <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">重置</span>      
		</div>
         <div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:23px">
			 <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>      
		</div>
        </td>
      </tr>  
      </table> 
       <%} %>  
</form>
</body>
</html>
<script type="text/javascript">
<!--
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
	//document.form1.sheng2.value=document.form1.sheng.value;
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

// 处理返回信息函数
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
	var stationtype = document.form1.stationtype.value;
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
			&& preenddate>=startdate
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
	document.getElementById("csdbdiv").value="";
}
}

//-->
</script>
