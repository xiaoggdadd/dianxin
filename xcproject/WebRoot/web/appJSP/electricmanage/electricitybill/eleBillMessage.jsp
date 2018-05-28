<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.ptac.app.electricmanage.electricitybill.bean.Share" %>
<%@ page import="com.ptac.app.electricmanage.electricitybill.bean.BasicInfo" %>
<%@ page import="com.ptac.app.electricmanage.electricitybill.bean.AssistInfo" %>
<%@ page import="com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo" %>


<%
String now = null;
Date today = new Date(); 
int tyear = 1900 + today.getYear();//年
int tmonth = today.getMonth() + 1;//月
int tday = today.getDate();//日
String month = String.valueOf(tmonth);
String day = String.valueOf(tday);
if(tmonth < 10){
	 month="0" + tmonth;
}
if(tday < 10){
	day = "0" + tday;
}
now = tyear + "-" + month+ "-" + day;
//如果电量管理的合理，在添加电费单的的时候  电量，本次抄表时间会自动带出
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
     String roleId = account.getRoleId();
     String accountid=account.getAccountName();
     
     String stationname=request.getParameter("stationname");
     String dbid="",zdcode="";
     if(stationname!=null){
     	if(stationname.contains(",")){
     		int aa=stationname.indexOf(",");
     		 dbid=stationname.substring(0,aa);
     		 zdcode=stationname.substring((aa+1));
     	}
     }
     String gdfs="";
     if(dbid!=null){
     	// 站点信息
     	// 获取站点和电表的信息
     	String ammeterid = (String)request.getAttribute("dbid");
     	
     	ElectricityInfo elec = new ElectricityInfo();
     	elec = (ElectricityInfo)request.getAttribute("elec");
     	String bcdbds = elec.getLastdegree();

     	BasicInfo bas = new BasicInfo();
     	bas = (BasicInfo)request.getAttribute("bas");
     	String shifou = bas.getShifou(); //胡波标志位 （已经不用）
     	String xgbzw = bas.getXgbzw();//修改电表读数标志位   1修改   2未修改
     	String dbds = bas.getDbds();//修改电表读数
     	String zdlxbm = bas.getZdlxbm();//站点类型 编码
     	gdfs = bas.getGdfs();//供电方式
		if("1".equals(xgbzw)){//判断如果 电表修改读数标志位为1 上次电表读数就等于 电表修改读数（信息修改）
			bcdbds=dbds;
		}
     	
 
     	 
         if(ammeterid!=null){
         
         
         //  获取六条分摊
         ArrayList listt=new ArrayList();
         listt=(ArrayList)request.getAttribute("share1");
       
         String shuoshuzhuanye="";
         String dbili="",dbili1="",dbili2="",dbili3="",dbili4="",dbili5="",dbili6="",zh="0";
         double hj=0.0;
         
       if(listt!=null){
     	int fycount = ((Integer)listt.get(0)).intValue();
     	int size = listt.size()-1;
     	for( int k = fycount;k<size;k+=fycount){
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
     if(dbili1==null||dbili1.equals("null")||dbili1.equals("")||dbili1==""){ dbili1="0"; }
     if(dbili2==null||dbili2.equals("null")||dbili2.equals("")||dbili2==""){ dbili2="0"; }
     if(dbili3==null||dbili3.equals("null")||dbili3.equals("")||dbili3==""){ dbili3="0"; }
     if(dbili4==null||dbili4.equals("null")||dbili4.equals("")||dbili4==""){ dbili4="0"; }  
     if(dbili5==null||dbili5.equals("null")||dbili5.equals("")||dbili5==""){ dbili5="0"; }
     if(dbili6==null||dbili6.equals("null")||dbili6.equals("")||dbili6==""){ dbili6="0"; }
     DecimalFormat mat7 =new DecimalFormat("0.0000000");
     hj=Double.parseDouble(dbili1)+Double.parseDouble(dbili2)+Double.parseDouble(dbili3)+Double.parseDouble(dbili4)+Double.parseDouble(dbili5)+Double.parseDouble(dbili6);
  	zh= mat7.format(hj);
     
     //获取分摊详细信息
     String dbili9="",sszy="",qcb="",kjkm="",zymx="",xjbl="";
     String bzw9="";
     ArrayList listx=new ArrayList();
     listx=(ArrayList)request.getAttribute("share2");
        
     if(listx!=null){
     	int fycountxjbl = ((Integer)listx.get(0)).intValue();
     	int size1 = listx.size()-1;
     	for( int kk = fycountxjbl;kk<size1;kk+=fycountxjbl){
     	
          dbili9 = (String)listx.get(kk+listx.indexOf("DBILI"));
          dbili9 = dbili9 != null ? dbili9 : "";
    
          sszy = (String)listx.get(kk+listx.indexOf("SHUOSHUZHUANYE"));
          sszy = sszy != null ? sszy : "";
         
          qcb = (String)listx.get(kk+listx.indexOf("QCBCODE"));
          qcb = qcb != null ? qcb : "";
          
          kjkm = (String)listx.get(kk+listx.indexOf("KJKMCODE"));
          kjkm = kjkm != null ? kjkm : "";
          
          zymx = (String)listx.get(kk+listx.indexOf("ZYMXCODE"));
          zymx = zymx != null ? zymx : "";
          
          xjbl = (String)listx.get(kk+listx.indexOf("XJBILI"));
          xjbl = xjbl != null ? xjbl : "";
           
          if("".equals(dbili9)||"".equals(sszy)||"".equals(kjkm)||"".equals(qcb)||"".equals(zymx)||"".equals(xjbl)){
          	bzw9="0";
          }
         }
     }
%>
<html>
<head>
<style>
.form_label{
	text-align: right;
	font-family: 微软雅黑;
	font-size: 12px;
	width:13%;
}
.form_labell{
	text-align: right;
	font-family: 微软雅黑;
	font-size: 12px;
	width:120px;
}
.form{
   height:19px;
   width:130px;
}

.formm{
   text-align: right;
   height:19px;
   width:80px;
}
.formr{
   text-align: right;
   height:19px;
   width:130px;
}
.form1{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;
   height:19px;
   width:130px;
}
.form2{
   border-left-width:0px; border-top-width:0px; border-right-width:0px;
   text-align: right;
   height:19px;
   width:130px;
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
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
 
<script>window.onload=function(){

	var bzw9='<%=bzw9%>';
	var zh='<%=zh%>';
	
	if(zh!="100.0000000"||bzw9=="0"){
		alert("该电表没有做分摊或者分摊有异常，请到监测点管理进行修改");
		history.go(-1);
		return;
	}
	var shifou='<%=shifou%>';
	if(shifou=="0"){
	
	}else{
			getHaodianliang();
			}
	}
</script> 

<script language="javascript">

var path = '<%=path%>';
function fun(){	
	var lastdate = document.getElementById('lastdatetime').value;
	var thisdate = document.getElementById('thisdatetime').value;

		var qsdbdl = '${bas.qsdbdl}';
	    var edhdl='${bas.edhdl}';
		var nd = 1000 * 24 * 60 * 60;//一天多少毫秒
		if(thisdate!=null&&thisdate!=""){
		var blhdl = document.getElementById('blhdl').value;
		var date1= new Date(Date.parse(lastdate.replace(/-/g,"/"))); //转换成Data();
		var date2= new Date(Date.parse(thisdate.replace(/-/g,"/"))); //转换成Data();
		lastdate = date1.getTime();
		thisdate = date2.getTime();
		//两个时间之间的天数
		var ld=(thisdate-lastdate)/nd + 1;//相差天数
		var bb=parseFloat(blhdl);//倍率耗电量
		var ee=parseFloat(edhdl)*parseFloat(ld);
		var qq=parseFloat(qsdbdl)*parseFloat(ld);//全省定标

		
		if("0"==qsdbdl||"0.0"==qsdbdl||"0.00"==qsdbdl || "" == qsdbdl){
			document.form1.kongbai1.value="系统中的全省定标电量是空值！！！";	
		}else{
			if("0"==qq||"0.0"==qq||"0.00"==qq){
				document.form1.kongbai1.value="";	
			}else{
				var qqbili=(((bb-qq)/qq)*100).toFixed(2);
				document.form1.kongbai1.value="超全省定标的"+qqbili+"%!";
				document.form1.qsbl.value=qqbili/100;
			}
		}
		if(edhdl!=""&&edhdl!=null){
			if("0"==ee||"0.0"==ee||"0.00"==ee){
				document.form1.kongbai.value="";
			}else{
				var bili=(((bb-ee)/ee)*100).toFixed(2);
				document.form1.kongbai.value="该记录超本地标"+bili+"%!";
				document.form1.bl.value=bili/100;
			}	
		}else{
			    document.form1.kongbai.value="系统中的本地标是空值！！！";
		}
	}
}
    function DBgh(){
      var qsagree = document.form1.lastdegree.value;
      if(document.form1.dbql.checked){
        
        document.form1.lastgree01.value=qsagree;
        document.form1.lastdegree.value="0.0";
      }else{
        
        document.form1.lastdegree.value=document.form1.lastgree01.value;
      }
      
    }
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
	//日均电费大于6去济宁电费单录入  是否 启用
	function goJining(){
		var go = "${configurations.averagefeestrueorfalse}";
		var averagefees = "${configurations.averagefees}";
		var actualpay =  Number(document.getElementById('actualpay').value);//报账电费
		var lastdate = document.getElementById('lastdatetime').value;//上次抄表时间
		var thisdate = document.getElementById('thisdatetime').value;//本次抄表时间
		var nd = 1000 * 24 * 60 * 60;//一天多少毫秒
		var date1= new Date(Date.parse(lastdate.replace(/-/g,"/"))); //转换成Data();
		var date2= new Date(Date.parse(thisdate.replace(/-/g,"/"))); //转换成Data();
		lastdate = date1.getTime();
		thisdate = date2.getTime();
		var ld=(thisdate-lastdate)/nd + 1;//结算表相差天数
		
		//1 启用 0 不启用
		if(go==1){
			if(shiLimit()==true && actualpay/ld > averagefees){
				alert("请到 电费管理下的    电费单加强功能添加电费单！");
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
	function shiLimit(){
		var shi = '${bas.shi}';
		var shiarray = "${configurations.startshi}";
		var str=new Array();
		str = shiarray.split(",");
		var lengtha = str.length;
		for(var i=0;i<lengtha;i++){
			if(shi == str[i]){
				return true;
			}
		}
		return false;
	}
	function httime(tdt){
		var dfzflxcode = '${bas.dfzflxcode}';
		var htendtime = '${htendtime}';
		if(dfzflxcode=='dfzflx02'){
			if(htendtime==''){
				alert("先录入合同单再录入电费单!");
			}else{
				if(tdt>htendtime){
					alert("合同已到期不允许录入!");
				}else{
					return true;
				}
			}
		}else{
			return true;
		}
	}
	function saveDegree(){
		var qsdbdl = '${bas.qsdbdl}';
		var edhdl =  '${bas.edhdl}';
		var zlfh = '${bas.zlfh}';
		var jlfh = '${bas.jlfh}';
		var scb = '${bas.scb}';
		var property = '${bas.propertycode}';
		var outnoconnct = '${outnoconnct}';
  		var now = '<%=now%>';//系统时间，录入时间

		 var dddfdf = parseFloat(document.form1.dddfdf.value);//代垫电费
		 var thisdatetime = document.form1.thisdatetime.value;
         var lastdatetime = document.form1.lastdatetime.value;   
         if(lastdatetime==null||lastdatetime==""){
           alert("您输入的信息不完善，上次抄表时间不能为空！")
         }else{
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
            if(days<0){
			  alert("您输入的信息有误，上次抄表时间不能大于本次抄表时间！");
			}
         }
         
		var pje=document.form1.pjje.value;
		var moneyqk1 = document.form1.pjje.value.replace(/[ ]/g,"");
		var zh=(parseFloat(document.form1.scdl.value)+ parseFloat(document.form1.bgdl.value)+parseFloat(document.form1.yydl.value)+ parseFloat(document.form1.xxhdl.value)+ parseFloat(document.form1.jstzdl.value)+ parseFloat(document.form1.dddfdl.value)).toFixed(2);
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
													if(checkNotnull(document.form1.lastdegree,"上次电表读数")&&checkNotnull(document.form1.actualpay,"报账电费")&&
												       checkNotnull(document.form1.thisdatetime,"本次抄表的时间")&&checkNotnull(document.form1.thisdegree,"本次电表读数")&&
												       checkNotnull(document.form1.accountmonth,"报账月份")&&checkNotSelected(document.form1.notetypeid,"票据类型")&&checkNotnull(document.form1.pjje,"票据金额")&&
												       checkNotnull(document.form1.unitprice,"单价")&&checkNotnull(document.form1.lastdatetime,"上次抄表时间")){
														var lastre = document.form1.lastdegree.value.replace(/[ ]/g,"");//上次电表读数去空格
														var thisre = document.form1.thisdegree.value.replace(/[ ]/g,"");//本次电表读数去空格
												if(lastre != ""){
													if(isNaN(document.form1.lastdegree.value)==false){
														if(document.form1.lastdegree.value>=0){
															if(thisre != ""){
															if(isNaN(document.form1.thisdegree.value)==false){
																if(document.form1.thisdegree.value>=0){
															
																	if(zh==(parseFloat(document.form1.blhdl.value)).toFixed(2)){
																	
																		var zf=(parseFloat((parseFloat(parseFloat(document.form1.scdff.value)+ parseFloat(document.form1.bgdf.value)+parseFloat(document.form1.yydf.value)+ parseFloat(document.form1.xxhdf.value)+ parseFloat(document.form1.jstzdf.value)+ parseFloat(document.form1.dddfdf.value))).toFixed(2))).toFixed(2);
														
																	if(zf==(parseFloat((parseFloat(document.form1.actualpay.value)).toFixed(2))).toFixed(2)){
															       		var ad2_bz="";//AuditDegree2状态标志
															       		var ad2_bz1="";//AuditDegree5状态标志
															       		if(document.form1.lastdegree.value != document.form1.thisdegree.value){
															        	 	ad2_bz="1";
															       		}
															       		if(document.form1.lastdatetime.value != document.form1.thisdatetime.value){
															         		ad2_bz1="1";
															       		}
															       
																              
																					var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
																					var ldt = document.form1.lastdatetime.value;
																					var tdt = document.form1.thisdatetime.value;
																					var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
																					var am = document.form1.accountmonth.value;//
																					var dftz = document.form1.floatpay.value;//电费调整
																					var dftz1= document.form1.floatpay.value.replace(/[ ]/g,"");
																					var dltz = document.form1.floatdegree.value;//电量调整
																					var dltz1= document.form1.floatdegree.value.replace(/[ ]/g,"");
																					var sszy=document.form1.shuoshuzhuanye.value;
																					var vb="";
															
																					var obj = document.form1.thisdegree;
								   													var reg2 = /\s/;
																					
																					if(reg.exec(ldt)){
																						if(isDate(tdt)==true){
																							if(reg1.exec(am)){
																                 				if(isNaN(document.form1.actualpay.value)==false&&(Number(document.form1.actualpay.value)>=5 || Number(document.form1.actualpay.value)<=0)){
																								    if(thisdatetime<=now){
																										if(tdt>=ldt){
																											if(httime(tdt)==true){
																												    if(isNaN(pje)==false&& moneyqk1!=""){
																												        if(isNaN(dltz)==false&&dltz1!=""){
																												            if(isNaN(dftz)==false&&dftz1!=""){
																												                if(dddfdf==0){
																												                	if(!reg2.test(obj.value)){
																												                		if(outnoconnct=='false'){
																													                  		if(goJining()==true){
																															                	 if(confirm("您将要添加信息！确认信息正确！")){
																															                     	document.form1.action=path+"/servlet/ElecBillServlet?action=addDf&ad2_bz="+ad2_bz;
																															        			 	document.form1.submit()
																															        			 	showdiv("请稍等..............");
																														                   		 }
																														              		}
																													                  	}else{
																													                  		alert("请先关联主站点ID号，再录入电费!"); 
																													                  	}
																												                  	}else{
																												                	 	alert("本次电表读数不能有空格,请重新输入!"); 
																												                  	}
																									                  			}else{
																									                  				alert("代垫电费不允许做分摊，请到监测点重新做分摊！");
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
																										    } 
																							           }else{
																							                 alert("您输入的信息有误，本次抄表时间必须大于等于上次抄表时间 ！");
																							           }
																				                 }else{
																				                	 alert("您输入的信息有误，本次抄表时间必须小于等于录入时间 ！");
																				                 } 
																		                   }else{
																		        	 			alert("您输入的信息有误，报账电费金额小于5元不能录入，且必须为数字！");
																		        	 	   }
																			        }else{
																			        	alert("您输入的报账月份有误，请确认后重新输入！");
																			        }
																				}else{
																					alert("您输入的本次抄表时间有误，请确认后重新输入！");
																				}
																			}else{
																				alert("您输入的上次抄表时间有误，请确认后重新输入！");
																			}    	
																		
														        	}else{
														           		alert("分摊电费之和不等于报账电费,请确认！！！");
																	}
														       }else{
														           alert("分摊电量之和不等于报账电量,请确认！！！");
																}
															}else{
																alert("本次电表读数必须大于等于0！");
															}
															}else{
																alert("本次电表读数必须为数字！");
															}	
														}else{
															alert("本次电表读数为空！");
														}
													}else{
														alert("上次电表读数必须大于等于0！");
													}	
												}else{
													alert("上次电表读数必须为数字！");
												}
											}else{
												alert("上次电表读数为空！");
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
        	 var bl=document.form1.mpower.value;       	 
        	 if(bl==null||bl=="")bl="1"
        	 var thisdegree=document.form1.thisdegree.value;
             var lastdegree=document.form1.lastdegree.value;
			 var floatdegree=document.form1.floatdegree.value;
			 var linelossvalue=document.form1.linelossvalue.value;
			 var actualdegree=document.form1.actualdegree.value;
			 
			 document.form1.actualdegree.value=((parseFloat(Number(document.form1.thisdegree.value)-Number(document.form1.lastdegree.value))+parseFloat(Number(document.form1.floatdegree.value)))).toFixed(2);
        	if(linelossvalue==null||linelossvalue=="")linelossvalue="0";
			  if(document.form1.linelosstype.value=="线损比例"){	
				  //（(本次电表读数-上次电表读数）*（1+线损比例）+电量调整）*倍率
        		 document.form1.blhdl.value=( ( parseFloat(Number(thisdegree)-Number(lastdegree))  *  ( 1 + parseFloat(Number(linelossvalue)))  + parseFloat(Number(floatdegree)) )*bl).toFixed(2);
        	 }else{
        		 document.form1.blhdl.value=((Number(thisdegree)-Number(lastdegree)+Number(linelossvalue)+Number(floatdegree))*bl).toFixed(2);
        	 }
			  
			  
			//  if(document.form1.linelosstype.value=="线损调整"){	
        	//	document.form1.blhdl.value=((Number(thisdegree)-Number(lastdegree)+Number(linelossvalue)+Number(floatdegree))*bl).toFixed(2);
        	// }
        	// else{
        		// if(linelossvalue==null||linelossvalue=="")linelossvalue="0";
        		// document.form1.blhdl.value=( ( parseFloat(Number(thisdegree)-Number(lastdegree))  *  ( 1 + parseFloat(Number(linelossvalue)))  + parseFloat(Number(floatdegree)) )*bl).toFixed(2);
        		// if(linelossvalue==null||linelossvalue==""||linelossvalue=="0")linelossvalue="1";
        		//document.form1.blhdl.value=((parseFloat(Number(thisdegree)-Number(lastdegree))*parseFloat(Number(linelossvalue))+parseFloat(Number(floatdegree)))*bl).toFixed(2);
        	// }

        	  var aaa=document.form1.unitprice.value*document.form1.blhdl.value;
        	  var bbb=document.form1.floatpay.value;
        	 // alert(Number(Number(Number(aaa).toFixed(2))+Number(Number(bbb).toFixed(2))).toFixed(2));
        	 // var ccc=Number(Number(aaa)+Number(bbb)).toFixed(2);
        	   var ccc=Number(Number(Number(aaa).toFixed(2))+Number(Number(bbb).toFixed(2))).toFixed(2);
        	   
        	  document.form1.actualpay.value=ccc; 
        	     fun();

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
            if(actualpay!="NaN"){
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
    
        		var bz_1 = 0;
        		var bz_2 = 0;
        		var bz_3 = 0;
        		var bz_4 = 0;
        		var bz_5 = 0;
        		var bz_6 = 0;
        		if(dbili6!=0){
        			bz_6=1;
        		}
        		if(dbili5!=0){
        			bz_5=1;
        		}
        		if(dbili4!=0){
        			bz_4=1;
        		}
        		if(dbili3!=0){
        			bz_3=1;
        		}
        		if(dbili2!=0){
        			bz_2=1;
        		}
        		if(dbili1!=0){
        			bz_1=1;
        		}
        		var df = parseFloat(actualpay);
        		var df1 = ((parseFloat(dbili1)/100)*parseFloat(actualpay)).toFixed(2);
        		var df2=	((parseFloat(dbili2)/100)*parseFloat(actualpay)).toFixed(2);
        		var df3=((parseFloat(dbili3)/100)*parseFloat(actualpay)).toFixed(2);
        		var df4=((parseFloat(dbili4)/100)*parseFloat(actualpay)).toFixed(2);
        		var df5= ((parseFloat(dbili5)/100)*parseFloat(actualpay)).toFixed(2);
        		var df6= ((parseFloat(dbili6)/100)*parseFloat(actualpay)).toFixed(2);
        		
        		document.form1.scdff.value=df1;
        		document.form1.yydf.value=	df2;
        		document.form1.bgdf.value=df3;
        		document.form1.xxhdf.value=df4;
        		document.form1.jstzdf.value= df5;
        		document.form1.dddfdf.value= df6;
        		if(bz_6==1){
        			document.form1.dddfdf.value=(df-df1-df2-df3-df4-df5).toFixed(2);
        			return;
        		}
        		if(bz_5==1){
        			document.form1.jstzdf.value=(df-df1-df2-df3-df4-df6).toFixed(2);
        			return;
        		}
        		if(bz_4==1){
        			document.form1.xxhdf.value=(df-df1-df2-df3-df6-df5).toFixed(2);
        			return;
        		}
        		if(bz_3==1){
        			document.form1.bgdf.value=(df-df1-df2-df6-df4-df5).toFixed(2);
        			return;
        		}
        		if(bz_2==1){
        			document.form1.yydf.value=(df-df1-df6-df3-df4-df5).toFixed(2);
        			return;
        		}
        		if(bz_1==1){
        			document.form1.scdff.value=(df-df6-df2-df3-df4-df5).toFixed(2);
        			return;
        		}
        
        	 }
        	 
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
        		var v = parseFloat(blhdl);
        		var v1 = ((parseFloat(dbili1)/100)*parseFloat(blhdl)).toFixed(2);
        		var v2 = ((parseFloat(dbili3)/100)*parseFloat(blhdl)).toFixed(2);
        		var v3 = ((parseFloat(dbili2)/100)*parseFloat(blhdl)).toFixed(2);
        		var v4 = ((parseFloat(dbili4)/100)*parseFloat(blhdl)).toFixed(2);
        		var v5 = ((parseFloat(dbili5)/100)*parseFloat(blhdl)).toFixed(2);
        		var v6 = ((parseFloat(dbili6)/100)*parseFloat(blhdl)).toFixed(2);
        		
        			
        	 //生产
        		 document.form1.scdl.value=v1;
        	  //办公
        		 document.form1.bgdl.value=v2;	
        	 // 营业
        		 document.form1.yydl.value=	v3;
        	 // 信息化	
        		 document.form1.xxhdl.value=v4;
        	 // 建设投资
        		 document.form1.jstzdl.value= v5;
        	// 代垫电量
        		document.form1.dddfdl.value= v6;
        	if(v6!=0){
        		document.form1.dddfdl.value=(v-v1-v2-v3-v4-v5).toFixed(2);
        		return;
        	}
        	if(v5!=0){
        		document.form1.jstzdl.value=(v-v1-v2-v3-v4-v6).toFixed(2);
        		return;
        	}
        	if(v4!=0){
        		document.form1.xxhdl.value=(v-v1-v2-v3-v6-v5).toFixed(2);
        		return;
        	}
        	if(v3!=0){
        		document.form1.yydl.value=(v-v1-v2-v6-v4-v5).toFixed(2);
        		return;
        	}
        	if(v2!=0){
        		document.form1.bgdl.value=(v-v1-v6-v3-v4-v5).toFixed(2);
        		return;
        	}
        	if(v1!=0){
        		document.form1.scdl.value=(v-v6-v2-v3-v4-v5).toFixed(2);
        		return;
        	}
        	
         }

	$(function(){
        $("#saveBtn").click(function(){
		   saveDegree();
		});
        $("#resetBtn").click(function() {	
        	$("#form")[0].reset();
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
<% 
//如果一级分摊不为100不显示
if(zh.equals("100.0000000")&&!bzw9.equals("0")){
%>
	<div style="width:700px;height:50px">
		<img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
		<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">添加电费单</span>
	</div>
  <table border="0" align="center" width="97%">
  	<tr>
  		<td width="83%">  		
			<fieldset>
				<legend><img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">基本信息</font></b></legend>
	 			<table width="97%" align="right" CellSpacing="1">
				     <tr>    
				        <td bgcolor="#DDDDDD"   class="form_label">
				        	<input type="hidden" name="accountid" value="<%=accountid %>" class="form1" />
				        	<input type="hidden" name="dbid" id="dbid" value="${dbid}"/>
				        	<input type="hidden" name="ammeteridFk" value="${ammeterid}"/>
				        	<input type="hidden" name="bl" id="bl" value=""/>
				        	<input type="hidden" name="qsbl" id="qsbl" value=""/>
				        	<input type="hidden" name="property" value="${bas.propertycode}"/>
				        	<input type="hidden" name="scb" value="${bas.scb}"/>
				        	<input type="hidden" name="stationtypecode" value="${bas.zdlxbm}"/>
				        	<input type="hidden" name="gdfscode" value="${bas.gdfs}"/>
				        	<input type="hidden" name="dfzflxcode" value="${bas.dfzflxcode}"/>
				        	<div>地区：</div>
				        </td>
				        <td width="21%"><input type="text" name="area" readonly="readonly" value="${bas.area}"  class="form1" /></td>
						<td bgcolor="#DDDDDD"   class="form_label"><div>站点类型：</div></td>
				        <td width="21%"><input type="text" name="stationtype" id = "stationtype" readonly="readonly" value="${bas.stationtype}"  class="form1" /></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>站点名称：</div></td>
				        <td><input type="text" name="gsf"  readonly="readonly" value="${bas.jzname}"  class="form1" /></td>
				     </tr>
				     <tr>
				     	<td bgcolor="#DDDDDD"   class="form_label"><div>集团报表类型：</div></td>
				        <td><input type="text" name="jztype"  readonly="readonly" value="${bas.jztype}"  class="form1" /></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>结算周期：</div></td>
				        <td><input type="text" name="gsf"  readonly="readonly" value="${bas.fkzq}"  class="form1" /></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>电费支付类型：</div></td>
				        <td><input type="text" name="jztype"  readonly="readonly" value="${bas.dfzflx}"  class="form1" /></td>
				     </tr>
				     <tr>
				     	<td bgcolor="#DDDDDD" class="form_label">供电方式</td>
				     	<td><input type="text" name="gdfs" readonly="readonly" value="${bas.gdfsname}" class="form1"/></td>
				     
				     </tr>
				</table>
			</fieldset>
			<fieldset>
				<legend><img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">电费信息</font></b></legend>
	 			<table width="97%" align="right" CellSpacing="1">
				  	 <tr>
				  	 <%--
				         <%if(shifou.equals("0")){ %>
					         <td bgcolor="#DDDDDD"   class="form_label"><div>上次电表读数：</div></td>        
					         <td width="21%"><input type="text" name="lastdegree" value="0" readonly="readonly" class="form2"/></td>
					         <td bgcolor="#DDDDDD"   class="form_label"><div>本次电表读数：</div></td>
					         <td width="21%"><input type="text" name="thisdegree" value="0" readonly="readonly"  class="form2" />
				         <% }else{  %>
					         --%>
					         <td bgcolor="#DDDDDD"   class="form_label"><div>上次电表读数：</div></td>        
					         <td width="21%"><input type="text" name="lastdegree" value="<%=bcdbds %>" readonly="readonly" class="form2"/></td>
					         <td bgcolor="#DDDDDD"   class="form_label"><div>本次电表读数：</div></td>
					         <td width="21%"><input type="text" name="thisdegree" value="${elec.thisdegree}" onChange="getHaodianliang();getCsdb()"  class="formr" />
					         <span style="color: #FF0000;font-weight: bold"> *</span>
						<%--<%}%>        
						     --%>
						     <br><input type="checkbox" name="dbql" onclick="DBgh()"><font size="1">是/否更换电表</font>
				        	 </td> 
					          <td bgcolor="#DDDDDD" class="form_label"><div>用电量：</div> </td>
					         <td><input type="text" name="actualdegree" value="0" readonly="readonly" class="form2" /></td> 
				       
				     </tr>
				     <tr>
				     
				     
				          <td bgcolor="#DDDDDD" class="form_label"><div>上次抄表时间：</div></td> 
				          <%if(zdlxbm.equals("StationType41")){//判断如果站点类型是增值税站点  本次抄表时间显示的值 是不加1天的 时间%>
				          <td><input type="text" id="lastdatetime" name="lastdatetime" value="${elec.lasttime}"  class="form1"  onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /> </td>
				          <%}else{ %> 
				 		  <td><input type="text" id="lastdatetime" name="lastdatetime" value="${elec.lastdatetime}" readonly="readonly" class="form1" /> </td>
				         <%} %>
				         <td bgcolor="#DDDDDD" class="form_label"><div>本次抄表时间：</div></td>
				         <td><input type="text" id="thisdatetime" name="thisdatetime" value="${elec.thisdatetime}" readonly="readonly" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" onpropertychange="fun();getCsdb()" class="form" align="right"/>
				         <span style="color: #FF0000;font-weight: bold">*</span></td>  
				         
				         <td bgcolor="#DDDDDD"   class="form_label"><div>本次单价：</div></td>
				         <td><input type="text" name="unitprice"  readonly="readonly" value="${bas.dianfei}" class="form2" /></td>
				            
				     </tr>
				     <tr>
				        
				         <!-- <td bgcolor="#DDDDDD" class="form_label"><div>起始年月：</div></td>
				         <td><input type="text" name="startmonth" value="" readonly="readonly" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"  class="form" />
				          <span style="color: #FF0000;font-weight: bold">*</span></td>  
				          -->
				        
				          <td bgcolor="#DDDDDD" class="form_label"><div>报账电量：</div></td>
				         <td><input type="text" id="blhdl" name="blhdl" value="0" readonly="readonly" class="form2"  onpropertychange="getHaodianliangg();getCsdb()"/></td>
				         <td bgcolor="#DDDDDD" class="form_label"><div>用电量调整：</div></td>
				         <td><input type="text" name="floatdegree" value="0" onChange="getHaodianliang()" class="formr" /></td>
				       
				        <td height="19" bgcolor="#DDDDDD" class="form_label"><div>电量备注：</div></td>
				         <td><input type="text" size="20" name="memoam" class="form" /></td>
				     </tr>
				     <tr>
				         <!--  <td bgcolor="#DDDDDD" class="form_label"><div>结束年月：</div></td>
				         <td><input type="text" name="endmonth" value="" readonly="readonly" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"  class="form" />
				          <span style="color: #FF0000;font-weight: bold">*</span></td>
				           -->
				          <td bgcolor="#DDDDDD" class="form_label"><div>报账电费：</div></td>
				         <td><input type="text" name="actualpay" value="" readonly="readonly" class="form2" onpropertychange="getMoney()"/></td>
				    
				         <td bgcolor="#DDDDDD"   class="form_label"><div>费用调整：</div></td>
				         <td><input type="text" name="floatpay" value="0" onChange="getHaodianliang()" class="formr" /></td>
				         <td bgcolor="#DDDDDD" class="form_label"><div>电费备注：</div></td>
				         <td><input type="text" name="memo" value=""  class="form" /></td>
				     </tr>
				     <tr>
				        <%--
				        <td bgcolor="#DDDDDD" class="form_label"><div>报账月份：</div></td> 
					      <td>--%>
					      <%--<span style="color: #FF0000;font-weight: bold">*</span></td>
					         --%>
					      <input type="hidden" name="accountmonth" value="${elec.accountmonth}" readonly="readonly" class="form" />
					      <td bgcolor="#DDDDDD" class="form_label"><div>票据类型：</div> </td>
				          <td><div><select name="notetypeid" style="width:130">
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
					         	} %>
					         </select><span style="color: #FF0000;font-weight: bold"> *</span>	
					         </div>        
				          </td> 
				          <td bgcolor="#DDDDDD" class="form_label">票据金额：</td>
				     <td><input type="text" id="pjje" name="pjje" value="" class="formr"><span style="color: #FF0000;font-weight: bold"> *</span></td>
				     <td bgcolor="#DDDDDD" class="form_label"><div>抄表操作员：</div></td>
				         <td><input type="text" name="inputoperator" value=""  class="form" /></td>
				     </tr>
				   
				     <tr> 
				              
				          <td bgcolor="#DDDDDD" class="form_label"><div>交费时间：</div></td>
					      <td><input type="text" name="paydatetime" value="" readonly="readonly" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="form" /></td>
					      <td bgcolor="#DDDDDD" class="form_label"><div>交费操作员：</div></td>
					      <td><input type="text" name="payoperator" value=""  class="form" /></td>	  
				     </tr>
				     <tr>
				     
				          <td colspan="2"><input id='kongbai' readonly="readonly"  class="form1"  type='text'  style="width: 100%"></td>
				          <%--<td><input name='getcsdbButton' value="获取超省标" onclick="getCsdb()" type="button" style="background:#FF9966;color:white"></td>--%>
				          <td colspan="2">
				          <input id='csdbdiv' type="text" readonly="readonly"  class="form1"  value="该记录超省标:" type='text' style="width: 100%" >
						<input id='kongbai1' type="hidden" readonly="readonly"  class="form1"  type='text' style="width: 100%" ></td>
				     </tr>
				</table>
			</fieldset>
			<fieldset>
				<legend><img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">辅助信息</font></b></legend>
	 			<table width="97%" align="right" CellSpacing="1">
				     <tr>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>上次用电量：</div></td>
					      <td width="21%"><input type="text" name="scydl"  readonly="readonly" value="${ass.blhdl}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>上次电费：</div></td>
					      <td width="21%"><input type="text" name="scdf"  readonly="readonly" value="${ass.actualpay}"   class="form2" /></td>
					      <td bgcolor="#DDDDDD"   class="form_label"><div>上次单价：</div></td>
					      <td width="21%"><input type="text" name="lastunitprice"  readonly="readonly" value="${ass.unitprice}"   class="form2" /></td>
				     </tr>
				     <tr>
					       <td bgcolor="#DDDDDD"   class="form_label"><div>直流负荷：</div></td>
					       <td align="center"><fmt:formatNumber type="text" value="${bas.zlfh}" pattern="##.##"  minFractionDigits="2"/> 
					       <input type = "hidden" name = "zlfh" value = "${bas.zlfh}"/></td>
					       
					      <td bgcolor="#DDDDDD"   class="form_label"><div>交流负荷：</div></td>
					       <td align="center"><fmt:formatNumber type="text" value="${bas.jlfh}" pattern="##.##"  minFractionDigits="2"/> 
					       <input type = "hidden" name = "jlfh" value = "${bas.jlfh}"/>
				        	<input type="hidden" name="dbili1" value="<%=dbili1%>">
				         	<input type="hidden" name="dbili2" value="<%=dbili2%>">
				         	<input type="hidden" name="dbili3" value="<%=dbili3%>">
				         	<input type="hidden" name="dbili4" value="<%=dbili4%>">
				         	<input type="hidden" name="dbili5" value="<%=dbili5%>">
				         	<input type="hidden" name="dbili6" value="<%=dbili6%>">
				         	<input type="hidden" name="shuoshuzhuanye" value="<%=shuoshuzhuanye%>">
				         	<input type="hidden" name="dd" value="<%=request.getParameter("blhdl")%>">
				         	</td>
				         	<td bgcolor="#DDDDDD"   class="form_label"><div>流程号：</div></td>
					       <td><input type="text" name="liuchenghao"  readonly="readonly" value="${liuchenghao}"   class="form2" />
					       </td>
				     </tr>
				     <tr>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>倍率：</div></td>
				        <td><input type="text" name="mpower"  readonly="readonly" value="${bas.beilv}"   class="form2" /></td>
				        <td bgcolor="#DDDDDD"  class="form_label"><div>线损类型：</div></td>
				        <td><input type="text" name="linelosstype"  readonly="readonly" value="${bas.linelosstype}"   class="form1" /></td>
				        <td bgcolor="#DDDDDD"   class="form_label"><div>线损值：</div></td>
				        <td><input type="text" name="linelossvalue"  readonly="readonly" value="${bas.linelossvalue}"   class="form2"/></td>
				     </tr>
				     <tr>
					     <td bgcolor="#DDDDDD"   class="form_label"><div>省定标电量(度)：</div></td>
					       <td align="center"><fmt:formatNumber type="text" value="${bas.qsdbdl}" pattern="##.##"  minFractionDigits="2"/>
					       <input type="hidden" name="qsdbdl" value="${bas.qsdbdl}"/>
					       </td> 
					       <td bgcolor="#DDDDDD"   class="form_label"><div>本地标(度)：</div></td>
					       <td align="center"><fmt:formatNumber type="text" value="${bas.edhdl}" pattern="##.##"  minFractionDigits="2"/>
					       <input type="hidden" name="edhdl" value="${bas.edhdl}"/>
					       </td>
					       <td bgcolor="#DDDDDD"   class="form_label"><div>生产标：</div></td>
					       <td align="center"><fmt:formatNumber type="text" value="${bas.scb}" pattern="##.##"  minFractionDigits="2"/>
					       <input type="hidden" name="scb1" value="${bas.scb}"/>
					       </td>     
				     </tr>
				     
				</table>
			</fieldset>
  		</td>
 		<td width="17%" valign="middle">
			 <table border="0" width="100%">
			 	<tr>
			 		<td>
			 		<fieldset>
			 			<table border="0" width="100%">
				 			<tr align="center"><td colspan="2"><div align="center"><b><font size="2">电量分摊</font></b><hr/></div></td></tr>
						 	<tr><td class="form_labell">生产</td><td><input type="text" name="scdl" value="0.00" class="formm"/></td></tr>
						 	<tr><td class="form_labell">办公</td><td><input type="text" name="bgdl" value="0.00" class="formm"/></td></tr>
						 	<tr><td class="form_labell">营业</td><td><input type="text" name="yydl" value="0.00" class="formm"/></td></tr>
						 	<tr><td class="form_labell">信息化支撑</td><td><input type="text" name="xxhdl" value="0.00" class="formm"/></td></tr>
						 	<tr><td class="form_labell">建设投资</td><td><input type="text" name="jstzdl" value="0.00" class="formm"/></td></tr>
						 	<tr><td class="form_labell">代垫电量</td><td><input type="text" name="dddfdl" value="0.00" class="formm"/></td></tr>
						 </table>
					 </fieldset>
					 </td>
			 	</tr>
			 	<tr>
			 		<td>
			 		<fieldset>
			 			<table border="0" width="100%">
				 			<tr align="center"><td colspan="2"><div align="center"><b><font size="2">电费分摊</font></b><hr/></div></td></tr>
						 	<tr><td class="form_labell">生产</td><td><input type="text" name="scdff" value="0.00" class="formm"/></td></tr>
						 	<tr><td class="form_labell">办公</td><td><input type="text" name="bgdf" value="0.00" class="formm"/></td></tr>
						 	<tr><td class="form_labell">营业</td><td><input type="text" name="yydf" value="0.00" class="formm"/></td></tr>
						 	<tr><td class="form_labell">信息化支撑</td><td><input type="text" name="xxhdf" value="0.00" class="formm" /></td></tr>
						 	<tr><td class="form_labell">建设投资</td><td><input type="text" name="jstzdf" value="0.00" class="formm"/></td></tr>
						 	<tr><td class="form_labell">代垫电费</td><td><input type="text" name="dddfdf" value="0.00" class="formm"/></td></tr>
						 </table>
					   </fieldset>
			 		</td>
			 	</tr>
			 </table>
		</td>
	</tr>
	<tr>
        <td>
            <input type="hidden" name="lastgree01" id="lastgree01" value=""/>
	        <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:0px">
				<img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
				<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
			</div>
	         <div id="resetBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:11px">
				 <img src="<%=path%>/images/2chongzhi.png" width="100%" height="100%">
				 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">重置</span>      
			</div>
			<%if(zh.equals("100.0000000")){%>
	         <div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:22px">
				 <img src="<%=path%>/images/baocun.png" width="100%" height="100%">
				 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>      
			</div>
			<%}%>
	     </td>
     </tr>
     
</table>
 <%} %>
 <input type="hidden" name="xgbzw" value="<%=xgbzw%>">
  </form> 
  <%}}%>
  
  
  
<script type="text/javascript" language="javascript">

var XMLHttpReq1;
function createXMLHttpRequest1() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq1 = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq1 = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq1 = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}
function sendRequest1(url) {
	createXMLHttpRequest1();
	XMLHttpReq1.open("POST", url, true);
	XMLHttpReq1.onreadystatechange = processResponse;//指定响应函数
	XMLHttpReq1.send(null);
}
// 处理返回信息函数
function processResponse() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq1.responseText;
			document.getElementById("csdbdiv").value="该记录超省标:"+res;
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function getCsdb() {
	var blhdl = document.form1.blhdl.value;
	var qsdbdl = document.form1.qsdbdl.value;
	var stationtype = document.form1.stationtypecode.value;
	var zlfh = document.form1.zlfh.value;
	var jlfh = document.form1.jlfh.value;
	var property = document.form1.property.value;
	var edhdl = document.form1.edhdl.value;
	var scb = document.form1.scb.value;
	var dbid = document.form1.dbid.value;
	var shi = '${bas.shi}';
	var thisdatetime = document.form1.thisdatetime.value;
	var lastdatetime = document.form1.lastdatetime.value;
	var thisdegree = document.form1.thisdegree.value;
	var lastdegree = document.form1.lastdegree.value;
	var beilv = document.form1.mpower.value;
	var kong1 = document.form1.thisdegree.value.replace(/[ ]/g,"");
	var kong2 = document.form1.thisdatetime.value.replace(/[ ]/g,"");
	if(parseFloat(blhdl)==blhdl
			&&isNaN(thisdegree)==false
			&& kong1!="" 
			&& kong2!="" 
			&& thisdatetime>=lastdatetime
			&& Number(zlfh)!=0
			&& Number(jlfh)!=0
			&& Number(edhdl)!=0
			&& Number(qsdbdl)!=0
			&& Number(scb)!=0
			&& property!="" && property!="null"
			&& thisdegree!=""
			&& lastdegree!=""){
	sendRequest1(path
+"/servlet/ElecBillServlet?action=getCsdb&blhdl="+blhdl+"&qsdbdl="+qsdbdl+"&stationtype="+stationtype+"&zlfh="
+zlfh+"&jlfh="+jlfh+"&property="+property+"&edhdl="+edhdl
+"&scb="+scb+"&dbid="+dbid+"&shi="+shi+"&thisdatetime="+thisdatetime
+"&lastdatetime="+lastdatetime+"&thisdegree="+thisdegree+"&lastdegree="+lastdegree+"&beilv="+beilv);
}else{
	document.getElementById("csdbdiv").value="该记录超省标:";
}
}

var gdfsbm='<%=gdfs%>'
if(gdfsbm=="gdfs06"){//如果供电方式是直供电1（单位用电） 那么 票据类型就默认为 增值税发票
	document.form1.notetypeid.value="pjlx06";
}
</script>
  </body>
</html>
