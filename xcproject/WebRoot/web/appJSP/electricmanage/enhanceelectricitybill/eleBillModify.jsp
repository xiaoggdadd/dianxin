<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="java.text.*,java.util.Date"%>
<%@ page import="com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo"%>
<%@ page import="com.ptac.app.electricmanage.electricitybill.bean.Share"%>
<%@ page import="com.ptac.app.electricmanageUtil.Format"%>
<%@ page import="com.ptac.app.electricmanage.electricitybill.dao.ElecBillDao"%>
<%@ page import="com.ptac.app.electricmanage.electricitybill.dao.ElecBillDaoImp"%>
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
String path = request.getContextPath();
Account account = (Account)session.getAttribute("account");
String accountid=account.getAccountName();
String accountname=account.getName();
String degreeid = (String)request.getAttribute("degreeid");//电费id

ArrayList list2=new ArrayList();
list2=(ArrayList)request.getAttribute("list2");
      String changevalue = "",linelosstype="",linelossvalue="",shifou="";
      String beilv="",zlfh = "",jlfh = "";
      int count = ((Integer)list2.get(0)).intValue();
      for( int k = count;k<list2.size()-1;k+=count){
    	  
    	  	zlfh = (String)list2.get(k+list2.indexOf("ZLFH"));
    	  	zlfh = zlfh != null ? zlfh : "0.00";
    	  	zlfh = Format.str2(zlfh);
  	  	    
    	  	jlfh = (String)list2.get(k+list2.indexOf("JLFH"));
    	  	jlfh = jlfh != null ? jlfh : "0.00";
    	  	jlfh = Format.str2(jlfh);
    	  
    	  	changevalue = (String)list2.get(k+list2.indexOf("CHANGEVALUE"));
    	  	changevalue = changevalue != null ? changevalue : "";
		   
		    linelosstype = (String)list2.get(k+list2.indexOf("LINELOSSTYPE"));
		    linelosstype = linelosstype != null ? linelosstype : "";
		    
		    beilv = (String)list2.get(k+list2.indexOf("BEILV"));
		    beilv = beilv != null ? beilv : "";
		    
		    shifou = (String)list2.get(k+list2.indexOf("BGSIGN"));
		    shifou = shifou != null ? shifou : "";
		    
		    linelossvalue = (String)list2.get(k+list2.indexOf("LINELOSSVALUE"));
		    linelossvalue = linelossvalue != null ? linelossvalue : "";
    		    
   		}
	Share share = new Share();
	share = (Share)request.getAttribute("share");
 	//六大分摊的格式化
 	Double a1=share.getNetworkhdl(),a2=share.getAdministrativehdl(),a3=share.getMarkethdl(),a4=share.getInformatizationhdl(),a5=share.getBuildhdl(),
 	a6=share.getNetworkdf(),a7=share.getAdministrativedf(),a8=share.getMarketdf(),a9=share.getInformatizationdf(),a10=share.getBuilddf();
 	Double a11 = share.getDddl();//代垫电量
 	Double a12 = share.getDddf();//代垫电费 
    	a1 = Format.d2(a1);
        a2 = Format.d2(a2);
        a3 = Format.d2(a3);
        a4 = Format.d2(a4);
        a5 = Format.d2(a5);
        a11 = Format.d2(a11);
        a6 = Format.d2(a6);
        a7 = Format.d2(a7);
        a8 = Format.d2(a8);
        a9 = Format.d2(a9);
       a10 = Format.d2(a10);
       a12 = Format.d2(a12);
       
       ElecBillDao dao = new ElecBillDaoImp();
       ElectricityInfo elec = new ElectricityInfo();
       elec = (ElectricityInfo)request.getAttribute("elec");
       String edhdl = elec.getEdhdl();//额定耗电量
       String qsdbdl = elec.getQsdbdl();//省定标
       String floatdegree = elec.getFloatdegree();//电量调整
       String bl="";
       if(Format.str_d(beilv)==0){
    	   bl = "1";
       }else{
    	   bl = beilv;
       }
       String floatdegreeandbl = String.valueOf(Format.d2(Double.parseDouble(floatdegree)*Double.parseDouble(bl)));
       ArrayList listm=new ArrayList();
   		listm=dao.share1(elec.getDbid());
    String shuoshuzhuanye="";
    String dbili="",dbili1="",dbili2="",dbili3="",dbili4="",dbili5="",dbili6="";
    
  if(listm!=null){
	int fmcount = ((Integer)listm.get(0)).intValue();
	for( int k = fmcount;k<listm.size()-1;k+=fmcount){
	shuoshuzhuanye = (String)listm.get(k+listm.indexOf("SHUOSHUZHUANYE"));
    shuoshuzhuanye = shuoshuzhuanye != null ? shuoshuzhuanye : "";
    dbili = (String)listm.get(k+listm.indexOf("DBILI"));
    dbili = dbili != null ? dbili : "0";
    if(shuoshuzhuanye.equals("zylx01")){ //生产
        dbili1=dbili;
     }
      if(shuoshuzhuanye.equals("zylx02")){//营业
       dbili2=dbili;
     }
      if(shuoshuzhuanye.equals("zylx03")){//办公
       dbili3=dbili;
     }
      if(shuoshuzhuanye.equals("zylx04")){//信息化支撑
       dbili4=dbili;
     }
      if(shuoshuzhuanye.equals("zylx05")){//建设投资
       dbili5=dbili;
     }
      if(shuoshuzhuanye.equals("zylx06")){//建设投资
       dbili6=dbili;            
     }
    }
}
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
	text-align:right;
	font-family: 微软雅黑;
   font-size: 12px;
   width:13%;
}
.formdemo{
	text-align:left;
   font-size: 12px;
   width:130px;
}
.formright{
   text-align: right;
   height:19px;
   width:130px;
}
.formm{
   text-align: right;
   height:19px;
   width:80px;
}
.form_labell{
	text-align: right;
	font-family: 微软雅黑;
	font-size: 12px;
	width:55x;
}
.form1{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;
   text-align: right;
   height:19px;
   width:130px;
}.formleft{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;
   text-align: left;
   height:19px;
   width:130px;
}
.ttxx{
	text-align: left;
	font-family: 微软雅黑;
	font-size: 12px;
	width:13%;
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
  <script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
window.onload=function(){
	selectlinelossvalue();
}
//线损值，如果线损类型为线损调整则自动带出；如果线损类型为线损比例，则根据比例转化成度，转化公式为：（本次电表读数-上次电表读数）*线损比例
function  selectlinelossvalue(){
	var linelosstype = "${elec.linelosstype}";//线损类型
	var linelossvalue = "${elec.linelossvalue}";//线损值
	var lastdegree = Number(document.getElementById('lastdegree').value);//上次电表读数
	var thisdegree = Number(document.getElementById('thisdegree').value);//本次电表读数
	if(linelosstype == "01xstz"){//线损调整
		document.form1.linelossvalue.value = linelossvalue; 
	}else if(linelosstype == "02xsbl"){//线损比例
		document.form1.linelossvalue.value = Number((thisdegree - lastdegree) * Number(linelossvalue)).toFixed(2); 
	}else{
		document.form1.linelossvalue.value = 0;
	}
}
function checkAdjust(){//电费电量调整检查
	var dltzyy = document.form1.memoam;//电量调整原因
	var dftzyy = document.form1.memo;//电费调整原因
	var dltzyyy = document.form1.memoam.value.replace(/[ ]/g,"");
	var dftzyyy = document.form1.memo.value.replace(/[ ]/g,"");
	var dltz = Number(document.form1.floatdegree.value);//电量调整
	var dftz = Number(document.form1.floatpay.value);//电费调整
	
	if(dltz!=0){
		if(checkNotnull(dltzyy,"电量调整原因说明")){
			if(dltzyyy!=""){
				
			}else{
				alert("电量调整原因说明为空！");
				return false;
			}
		}else{
			return false;
		}
	}
	if(dltzyy.value.length<=150){
						
	}else{
		alert("电量调整原因说明过长，不能超过150字！");
		return false;
	}
	if(dftz!=0){
		if(checkNotnull(dftzyy,"电费调整原因说明")){
			if(dftzyyy!=""){
				
			}else{
				alert("电费调整原因说明为空！");
				return false;
			}
		}else{
			return false;
		}
	}
	if(dftzyy.value.length<=150){
					
	}else{
		alert("电费调整原因说明过长，不能超过150字！");
		return false;
	}
	return true;
	
}
//1、异常调整：电量调整*倍率：不能大于电表用电量*5%（比例可调）；
function exceptAdjust(){
	var floatdegreeandbl = Number(document.form1.floatdegreeandbl.value);//电量调整*倍率
	var dbydl = Number(document.form1.actualdegree.value);//电表用电量
	var blke = "${configurations.exceptadjust}";//比例
	
	if(floatdegreeandbl > dbydl * (blke/100)){
		alert("异常调整!");
	}else{
		return true;	
	}
}

//2、异常线变损：（线损值+变损值）*倍率：不能大于电表用电量*10%（比例可调）；或（（本次电表读数-上次电表读数）*线损比例+变损值）*倍率：不能大于电表用电量*10%（比例可调）；
function exceptLineChangeLoss(){
	var bl = Number(document.getElementById('beilv').value); //倍率
	if(bl==null||bl==""||bl=="0")bl=1;
	var linelossvalue = Number(document.form1.linelossvalue.value);//线损值
	var changevalue = Number(document.form1.changevalue.value);//变损值
	var blke = "${configurations.exceptlinechangeloss}";//比例
	var dbydl = Number(document.form1.actualdegree.value);//电表用电量
	if((linelossvalue + changevalue) * bl > dbydl * (blke/100)){
		alert("异常线变损!");
	}else{
		return true;	
	}
}

//3、逆向调整：电量调整*倍率<-1并且电费调整>1：不能在此录入；
function backAdjust(){
	var danjia = document.getElementById('unitprice').value;//单价
	var backadjust1 = "${configurations.backadjust1}";
	var backadjust2 = "${configurations.backadjust2}";
	var backadjust3 = "${configurations.backadjust3}";
	var floatdegreeandbl = Number(document.form1.floatdegreeandbl.value);//电量调整*倍率
	var floatpay = Number(document.form1.floatpay.value);//电费调整
	if(floatdegreeandbl < backadjust1 && floatpay > backadjust2){
		if(Math.abs((floatdegreeandbl*danjia/floatpay).Fixed(2))>backadjust3){
			return true;
		}else{
			alert("逆向调整!");
		}
	}else{
		return true;
	}
}

//4、标杆虚高：（报账电量/周期—省定标电量）/省定标电量 < -0.2并且(电量调整+线损值+变损值）*倍率 > 1；
//5、躲避审核：（报账电量/周期—省定标电量）/省定标电量 < 0.1并且(电量调整+线损值+变损值）*倍率 < -1；
//11、电量超标：（报账电量/周期—省定标电量）/省定标电量> 15%
function three(){
	var sightvirtualheight1 = "${configurations.sightvirtualheight1}";
	var sightvirtualheight2 = "${configurations.sightvirtualheight2}";
	var escapeaudit1 = "${configurations.escapeaudit1}";
	var escapeaudit2 = "${configurations.escapeaudit2}";
	var elecoverproof = "${configurations.elecoverproof}";
	var bl = Number(document.getElementById('beilv').value); //倍率
	if(bl==null||bl==""||bl=="0")bl=1;
	var lastdate = document.getElementById('lastdatetime').value;//上次抄表时间
	var thisdate = document.getElementById('thisdatetime').value;//本次抄表时间
	var blhdl = parseFloat(document.getElementById('blhdl').value);//报账电量
	var qsdbdl = "${elec.qsdbdl}";//全省定标电量
	var nd = 1000 * 24 * 60 * 60;//一天多少毫秒
	var date1= new Date(Date.parse(lastdate.replace(/-/g,"/"))); //转换成Data();
	var date2= new Date(Date.parse(thisdate.replace(/-/g,"/"))); //转换成Data();
	lastdate = date1.getTime();
	thisdate = date2.getTime();
	var ld=(thisdate-lastdate)/nd + 1;//结算表相差天数
	var jsrj = blhdl/ld;//报账电量/周期
	var floatdegree = Number(document.form1.floatdegree.value);//电量调整
	var linelossvalue = Number(document.form1.linelossvalue.value);//线损值
	var changevalue = Number(document.form1.changevalue.value);//变损值
	
	var bjqq =  (jsrj - qsdbdl)/qsdbdl;//（报账电量/周期—省定标电量）/省定标电量
	var dxbb = (floatdegree + linelossvalue + changevalue) * bl;//(电量调整+线损值+变损值）*倍率
			if(bjqq < sightvirtualheight1 && dxbb > sightvirtualheight2){
				alert("标杆虚高!");
			}else{
				if(bjqq < escapeaudit1 && dxbb < escapeaudit2){
					alert("躲避审核!");
				}else{
					if(bjqq > (elecoverproof/100)){
						alert("电量超标!");
					}else{
						return true;	
					}
				}
			}
}

//6、倍率异常：倍率小于1；或倍率 / 5 不为正整数；提示倍率异常
function beilvExcept(){
	var beilv = Number(document.getElementById('beilv').value); //原始倍率
	var beilvexcept = "${configurations.beilvexcept}";
		var bei = new Array();
	bei = beilvexcept.split(",");
	var b = beilvBei(beilv,bei);
	if(beilv < 1 || (beilv != 1 && b)){
		alert("倍率异常!");
	}else{
		return true;
	}
}
function beilvBei(beilv,bei){
	var flag = true;
	for( var i=0;i<bei.length;i++){
		flag = (Number(beilv) % bei[i] != 0 ? true:false) && flag;
	}
	return flag;
}
//7、电费调整过高：电费调整>60；
//function feesHeight(){
//	var feesheight = "${configurations.feesheight}";
//	var floatpay = Number(document.form1.floatpay.value);//电费调整
//	if(floatpay > feesheight){
//		alert("电费调整过高!");
//	}else{
//		return true;
//	}
//}
//8、管理电量抄表不实：管理电量的本次抄表时间与结算表的本次抄表时间不同月或起止码一样或为未找到改记录，提示管理电量抄表不实；
//function gldlNotright(){
//	//管理表
//	var glthisdate1 = document.getElementById('glthistime').value.replace(/[ ]/g,"");1
//	var gllastdegree1 = document.getElementById('gllastdegree').value.replace(/[ ]/g,"");1
//	var glthisdegree1 = document.getElementById('glthisdegree').value.replace(/[ ]/g,"");1
//	var thisdate = document.getElementById('thisdatetime').value.replace(/[ ]/g,"");1
//	var gldlbz = document.getElementById("gldlbz").value;
//	
//	if(gldlbz == false){//未找到记录
//		alert("管理电量抄表不实!");
//	}else{
//		var jsyue = thisdate.substring(5,6);//结算表本次抄表时间月份
//		var glyue = glthisdate1.substring(5,6);//管理表本次抄表时间月份
//		if(gllastdegree1 == glthisdegree1 || jsyue > glyue){//本次抄表时间不同月或起止码一样
//			alert("管理电量抄表不实!");
//		}else{
//			return true;
//		}
//	}
//
//}
//9、报账和抄表差距过大：报账电量/周期>8则（报账电量/周期—管理表电表用电量/周期）/管理表电表电量/周期）（空调季节按含空调系数计算）大于15%（比例可调）不得录入。
//
//（结算表日均量-管理表日均量）/管理表日均量>15%
//function jiesuanguanli(){
//	var gapoversize1 = "${configurations.gapoversize1}";
//	var gapoversize2 = "${configurations.gapoversize2}";
//
//	var bzbili = gapoversize2/100;//15%
//	var nd = 1000 * 24 * 60 * 60;//一天多少毫秒
//	//结算表
//	var lastdate = document.getElementById('lastdatetime').value;
//	var thisdate = document.getElementById('thisdatetime').value;
//	var blhdl = parseFloat(document.getElementById('blhdl').value);
//	
//	//管理表
//	var gllastdate = document.getElementById('gllasttime').value;
//	var glthisdate = document.getElementById('glthistime').value;
//	var gllastdegree = document.getElementById('gllastdegree').value;
//	var glthisdegree = document.getElementById('glthisdegree').value;
//	
//	
//	var date1= new Date(Date.parse(lastdate.replace(/-/g,"/"))); //转换成Data();
//	var date2= new Date(Date.parse(thisdate.replace(/-/g,"/"))); //转换成Data();
//	lastdate = date1.getTime();
//	thisdate = date2.getTime();
//
//	var ld=(thisdate-lastdate)/nd + 1;//结算表相差天数
//	var jsrj = blhdl/ld;
//	
//	var date1= new Date(Date.parse(gllastdate.replace(/-/g,"/"))); //转换成Data();
//	var date2= new Date(Date.parse(glthisdate.replace(/-/g,"/"))); //转换成Data();
//	gllastdate = date1.getTime();
//	glthisdate = date2.getTime();
//	
//	var glld=(glthisdate-gllastdate)/nd + 1;//管理表相差天数
//	var glhdl = Number(glthisdegree) - Number(gllastdegree);
//	var glrj = glhdl/glld;
//	if(jsrj>gapoversize1){//报账电量/周期>8
//		//比较
//		if(glhdl!=0){
//				var bili = (jsrj-glrj)/glrj;
//				if(bili>bzbili){
//					alert("报账和抄表差距过大!");
//				}else{
//					return false;
//			}
//		}else{
//			alert("管理电量抄表不实!");
//		}
//	}else{
//		return false;
//	}
//}
//7、电费调整过高：电费调整>60；2015-0-7 暂时不上线
function feesHeight(){
	return true;
}
//8、管理电量抄表不实：管理电量的本次抄表时间与结算表的本次抄表时间不同月或起止码一样或为未找到改记录，提示管理电量抄表不实；2015-0-7 暂时不上线
function gldlNotright(){
	return true;
}
//9、报账和抄表差距过大：报账电量/周期>8则（报账电量/周期—管理表电表用电量/周期）/管理表电表电量/周期）（空调季节按含空调系数计算）大于15%（比例可调）不得录入。2015-0-7 暂时不上线

//（结算表日均量-管理表日均量）/管理表日均量>15%
function jiesuanguanli(){
	return false;
}
//10、电费调整异常：电费调整 / 用电电费>5%；
function feesAdjustexcept(){
	var feesadjustexcept = "${configurations.feesadjustexcept}";
	var floatpay = Number(document.form1.floatpay.value);//电费调整
	var yddf =  Number(document.form1.yddf.value);//用电电费
	if(floatpay/yddf > feesadjustexcept/100){
		alert("电费调整异常!");
	}else{
		return true;
	}
}

//12、单价异常：（单价—截止本年度当前的该地市的平均单价）/ 截止本年度当前的该地市的平均单价 > 20%；
//2015-2-11改为 加强流程：单价异常：报账电费 / 报账电量 > 标准单价*（1+xx%），提示单价异常
//function unitpriceExcept(){
//	var averageunitprice = "${averageunitprice}";//截止本年度当前的该地市的平均单价
//	var unitpriceexcept = "${configurations.unitpriceexcept}";
//	var unitprice = document.getElementById('unitprice').value;//本次单价
//	if((unitprice - averageunitprice) / averageunitprice > unitpriceexcept/100){
//		alert("单价异常!");
//	}else{
//		return true;
//	}	
//}
function unitpriceExcept(){
	var bzdf = document.form1.actualpay.value;
	var bzdl = document.form1.blhdl.value;
	var bzdj = "${averageunitprice}";//标准单价
	var unitpriceexcept = "${configurations.unitpriceexcept}";//比例值
	var a = parseFloat(bzdf/bzdl).toFixed(4);
	var b = parseFloat(bzdj*(1+unitpriceexcept/100)).toFixed(4);
	if( a> b ){
		alert("单价异常!");
	}else{
		return true;
	}
}

function sumEleadjust(){//电量调整改变,电表用电量改变
	var floatdegree = document.form1.floatdegree.value;//电量调整
	var linelossvalue = document.form1.linelossvalue.value;//线损值
	var changevalue = document.form1.changevalue.value;//变损值
	var bl = Number(document.getElementById('beilv').value); //倍率
	if(bl==null||bl==""||bl=="0")bl=1
	var floatdegreeandbl = Number(Number(floatdegree)*bl).toFixed(2);//电量调整*倍率
	document.form1.floatdegreeandbl.value = floatdegreeandbl;
	var ydl = Number(document.getElementById('actualdegree').value);//电表用电量
	document.form1.blhdl.value = Number(parseFloat(ydl)+(parseFloat(floatdegree)+parseFloat(linelossvalue)+parseFloat(changevalue))*parseFloat(bl)).toFixed(2);//报账电量
}
function sumPayadjust(){//电费调整改变
	var yddf = Number(document.getElementById('yddf').value);//用电电费
	var floatpay = Number(document.form1.floatpay.value);
	document.form1.actualpay.value = Number(parseFloat(yddf)+parseFloat(floatpay)).toFixed(2);
}


	//用电电费
	function yongdainfei(){
		var yongdianliang = document.getElementById('blhdl').value;//报账电量
		var zgdunitprice = document.getElementById('unitprice').value;//单价
		document.form1.yddf.value = (Number(zgdunitprice)*Number(yongdianliang)).toFixed(2);
	}
var path = '<%=path%>';
//计算额定耗电量，省定标超标比例
function fun(){	
		var lastdate = document.getElementById('lastdatetime').value;
		var thisdate = document.getElementById('thisdatetime').value;
		var qsdbdl = '<%=qsdbdl%>';
	    var edhdl='<%=edhdl%>';
		var nd = 1000 * 24 * 60 * 60;//一天多少毫秒
		if(thisdate!=null&&thisdate!=""){
		var blhdl = document.getElementById('blhdl').value;
		// alert("blhdl"+blhdl);
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
			document.form1.kongbai2.value="";	
			
		}else{
			if("0"==qq||"0.0"==qq||"0.00"==qq){
				document.form1.kongbai1.value="";	
				document.form1.kongbai2.value="";	
			}else{
				var qqbili=(((bb-qq)/qq)*100).toFixed(2);
				document.form1.kongbai1.value="超全省定标的"+qqbili+"%!";
				document.form1.kongbai2.value=qqbili/100;
			}
		}
		if(edhdl!=""&&edhdl!=null){
			if("0"==ee||"0.0"==ee||"0.00"==ee){
				document.form1.kongbai.value="";
				document.form1.kongbai3.value="";
			}else{
				var bili=(((bb-ee)/ee)*100).toFixed(2);
				document.form1.kongbai.value="该记录超本地标"+bili+"%!";
				document.form1.kongbai3.value=bili/100;
			}	
		}else{
			    document.form1.kongbai.value="系统中的本地标是空值！！！";
			    document.form1.kongbai3.value="";
		}
	}
}
		//电费超标比例
	function dfcbbl(){
		var elefeesbl = "${configurations.elefeesbl}";
		 var dfcbbl = Number(document.getElementById("dfcbdiv").value.replace("电费超标比例:","").replace("%",""));
		if("N"==elefeesbl){
			return true;
		}else{
			if(dfcbbl>elefeesbl){
				alert("电费超标比例大于"+elefeesbl+"%,不能录入!");
			}else{
				return true;
			}
		}
	}
//日均电费>6的进行判断：日均电费<=6 去原电费单中录入
function Collections(){
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
	if(actualpay/ld > averagefees){
		 if(exceptAdjust()==true){
			  if(exceptLineChangeLoss()==true){
				  if(beilvExcept()==true){
					  if(three()==true){
						   if(backAdjust()==true){
							   if(feesHeight()==true){
								    if(gldlNotright()==true){
								    	if(jiesuanguanli()==false){
								    		 if(feesAdjustexcept()==true){
								    			 if(unitpriceExcept()==true){
	            									if(dfcbbl()==true){
	            										return true;
	            										}
	         	  					 			}
	       		 	 						}
	     								}  
	   					 			}
	 					  		}
	 				   	   }  
					  }
				  }
			 }
	  	}
	}else{
		alert("去原电费单中录入");
	}
}

function modifyDegree() {
		var qsdbdl = '${elec.qsdbdl}';
		var edhdl =  '${elec.edhdl}';
		var zlfh = '<%=zlfh%>';
		var jlfh = '<%=jlfh%>';
		var scb = '${elec.scb}';
		var property = '${elec.propertycode}';
	var now='<%=now%>';
   	var pjdl = document.form1.pjdl.value;
    var elec = document.form1.pjdl.value.replace(/[ ]/g,"");
	var pj=document.form1.pjje.value;
	var moneyqk1 = document.form1.pjje.value.replace(/[ ]/g,"");
	var dltzandbl = document.form1.floatdegree.value;//电量调整*倍率
	var dltzandbl1= document.form1.floatdegree.value.replace(/[ ]/g,"");
	var dftz=document.form1.floatpay.value;
	var dftz1 = document.form1.floatpay.value.replace(/[ ]/g,"");
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
	if(checkNotnull(document.form1.ammeteridFk,"电表ID")&&checkNotnull(document.form1.ammeterdegreeidFk,"电表流水ID")&&checkNotnull(document.form1.lastdegree,"上次电表读数")&&checkNotnull(document.form1.thisdegree,"本次电表读数")&&checkNotnull(document.form1.lastdatetime,"上次抄表时间")&&checkNotnull(document.form1.thisdatetime,"本次抄表时间")
	&&checkNotSelected(document.form1.notetypeid,"票据类型")&&checkNotnull(document.form1.pjje,"票据金额")&&checkNotnull(document.form1.accountmonth,"报账月份")&&checkNotnull(document.form1.pjdl,"票据电量")&&checkNotnull(document.form1.inputoperator,"抄表操作员")&&checkNotnull(document.form1.floatdegree,"电量调整")&&checkNotnull(document.form1.floatpay,"电费调整")){
		var lastre = document.form1.lastdegree.value.replace(/[ ]/g,"");//上次电表读数去空格
			var thisre = document.form1.thisdegree.value.replace(/[ ]/g,"");//本次电表读数去空格
			if(lastre != ""){
			if(isNaN(document.form1.lastdegree.value)==false){
			if(document.form1.lastdegree.value>=0){
			if(thisre != ""){
			if(isNaN(document.form1.thisdegree.value)==false){
			if(document.form1.thisdegree.value>=0){
	var zh=(parseFloat(document.form1.scdl.value)+ parseFloat(document.form1.bgdl.value)+parseFloat(document.form1.yydl.value)+ parseFloat(document.form1.xxhdl.value)+ parseFloat(document.form1.jstzdl.value)+ parseFloat(document.form1.dddfdl.value)).toFixed(0);
	if(zh==(parseFloat(document.form1.blhdl.value)).toFixed(0)){
		
	var zf=(parseFloat(parseFloat(document.form1.scdff.value)+ parseFloat(document.form1.bgdf.value)+parseFloat(document.form1.yydf.value)+ parseFloat(document.form1.xxhdf.value)+ parseFloat(document.form1.jstzdf.value)+ parseFloat(document.form1.dddfdf.value))).toFixed(0);
	if(zf==(parseFloat(document.form1.actualpay.value)).toFixed(0)){
	var ad2_bz = "";//AuditDegree2状态标志

	var id = document.form1.ammeterdegreeidFk.value;
	var ii=document.form1.floatdegree.value;
		
			var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
			var ldt = document.form1.lastdatetime.value;
			var tdt = document.form1.thisdatetime.value;
			var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
			var am = document.form1.accountmonth.value;
							if(reg.exec(ldt)&&isDate(ldt)==true){
								if(reg.exec(tdt)&&isDate(tdt)==true){
												if(reg1.exec(am)){
														  if(tdt<=now){
															if(tdt>=ldt){
																if(isNaN(document.form1.actualpay.value)==false){
																if(isNaN(pj)==false&& moneyqk1!=""){
																	if(Number(pj)>=Number(document.form1.actualpay.value)){
																		if(isNaN(pjdl)==false&& elec!=""){
																				if(isNaN(dltzandbl)==false&&dltzandbl1!=""){
																					if(isNaN(dftz)==false&& dftz1!=""){
																	 					if(checkAdjust()==true){
														                					 if(Collections()==true){
																				                 if (confirm("您将要修改电量电费信息！确认信息正确！")) {
																									document.form1.action = path + "/servlet/EnhanceElecModifyServlet?action=modify&status=qmodify&ad2_bz=" + ad2_bz + "&id=" + id;
																									document.form1.submit()
																									showdiv("请稍等..............");
							 																	 }
																							}			                	  	 
													                					  }
																					}else{
																	 					alert("您输入的信息有误，费用调整必须为数字！");
																					}
																  				}else{
																	 				alert("您输入的信息有误，电量调整必须为数字！");
																				}
																			}else{
																	 			alert("您输入的信息有误，票据电量必须为数字！");
																			}
																		}else{
																			alert("票据金额不得小于报账电费！");
																		}
																	}else{
																	 	alert("您输入的信息有误，票据金额必须为数字！");
																	}
																}else{
														          alert("您输入的信息有误，报账电费必须为数字！");
														         }
												           }else{
												                alert("您输入的信息有误，本次抄表时间必须大于等于上次抄表时间 ！");
												           }
														}else{
															alert("您输入的信息有误，本次抄表时间必须小于等于录入时间 ！");
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
 
 }}
         function getHaodianliang(){
        	 var bl=document.form1.beilv.value;       	 
        	 if(bl==null||bl==""||bl=="0")bl=1;
        	 var thisdegree=document.form1.thisdegree.value;
             var lastdegree=document.form1.lastdegree.value;
        	 document.form1.actualdegree.value=Number((Number(thisdegree)-Number(lastdegree))*bl).toFixed(2); 
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
        }
        
          function getHaodianliangg(){
        	var dbili1=document.form1.dbili1.value;
        	var dbili2=document.form1.dbili2.value;
        	var dbili3=document.form1.dbili3.value;
        	var dbili4=document.form1.dbili4.value;
        	var dbili5=document.form1.dbili5.value;
        	var dbili6=document.form1.dbili6.value;
        	var blhdl= document.form1.blhdl.value;
        	if(dbili1==null||dbili1==""){dbili1="0.00";}
        	if(dbili2==null||dbili2==""){ dbili2="0.00"; }
        	if(dbili3==null||dbili3==""){ dbili3="0.00"; }
        	if(dbili4==null||dbili4==""){ dbili4="0.00";}
        	if(dbili5==null||dbili5==""){ dbili5="0.00"; }
        	if(dbili6==null||dbili6==""){dbili6="0.00";}
        	 
        	 document.form1.scdl.value=((parseFloat(dbili1)/100)*parseFloat(blhdl)).toFixed(2);//生产
        	 document.form1.bgdl.value=((parseFloat(dbili3)/100)*parseFloat(blhdl)).toFixed(2);	 //办公
        	 document.form1.yydl.value=	((parseFloat(dbili2)/100)*parseFloat(blhdl)).toFixed(2);// 营业
        	 document.form1.xxhdl.value=((parseFloat(dbili4)/100)*parseFloat(blhdl)).toFixed(2); // 信息化	
        	 document.form1.jstzdl.value=((parseFloat(dbili5)/100)*parseFloat(blhdl)).toFixed(2); // 建设投资
        	 document.form1.dddfdl.value=((parseFloat(dbili6)/100)*parseFloat(blhdl)).toFixed(2);// 代垫电量
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
	<div style="width:700px;height:50px">
		<img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
		<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电量/电费信息修改</span>
	</div>
  <table border="0" align="center" width="97%">
  	<tr>
  		<td width="80%">  		
			<fieldset>
				<legend><img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">电费信息</font></b></legend>
	 			<table width="96%" align="right" CellSpacing="1">
					<tr>
				         <td height="19" bgcolor="#DDDDDD" width="15%" class="form"><div>上次电表读数：</div></td>
				        <td><input type="text" name="lastdegree" value="${elec.lastdegree}" readonly="readonly"  class="form1" /></td>
				         <td height="19" bgcolor="#DDDDDD" width="15%" class="form"><div>本次电表读数：</div></td>
				         <td><input type="text" name="thisdegree" value="${elec.thisdegree}" onChange="selectlinelossvalue();getHaodianliang()"  class="formright" /><span class="style1">&nbsp;*</span></td>
				         <td height="19" bgcolor="#DDDDDD" class="form"><div>电表用电量：</div></td>
				         <td><input type="text" name="actualdegree" value="${elec.dbydl}" onpropertychange="sumEleadjust();getCsdb()" readonly="readonly" class="form1" /></td>
				      </tr>
				
				      <tr>
				       
					      <td height="19" bgcolor="#DDDDDD" class="form"><div>上次抄表时间：</div></td>
					      <td><input type="text" id = "lastdatetime" name="lastdatetime" value="${elec.lastdatetime}" readonly="readonly" class="form1"/></td>
				         <td height="19" bgcolor="#DDDDDD" class="form"><div>本次抄表时间：</div></td>
				         <td><input type="text" id = "thisdatetime" name="thisdatetime" value="${elec.thisdatetime}" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" onpropertychange="fun();getCsdb()" class="formdemo"  align="right"/><span class="style1">&nbsp;*</span></td>        
				     	 <td bgcolor="#DDDDDD"   class="form"><div>波谷占比：</div></td>
				         <td>
				         <input type="text" name="bgzb" readonly="readonly"  value="${elec.bgzb}" class="form1" />%
				         </td>
				      </tr>
				      <tr>
				      <td height="19" bgcolor="#DDDDDD" width="15%" class="form"><div>本次单价：</div></td>
				         <td><input type="text" readonly="readonly" class="form1" name="unitprice" value="${elec.unitprice}"/></td>
				         	<td height="19" bgcolor="#DDDDDD" width="15%" class="form"><div>电表ID：</div></td>
				         <td><input type="text" name="ammeteridFk" value="${elec.dbid}" readonly="readonly" class="formleft" align="left"/></td>
				         <td bgcolor="#DDDDDD"   class="form"><div>波峰占比：</div></td>
				         <td>
				         <input type="text" name="bfzb"  readonly="readonly" value="${elec.bfzb}" class="form1" />%
				         </td>
				      </tr>
				      <tr>
				      <td height="19" bgcolor="#DDDDDD" class="form"><div>用电电费：</div></td>
				      <td><input type="text" name="yddf" value="${elec.yddf}" onpropertychange="sumPayadjust()" readonly="readonly" class="form1" /></td>
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
				                    	if((elec.getNotetypeid()).equals(sfid)){
				                    %><option selected="selected" value="<%=sfid%>"><%=sfnm%></option><%
				                       }else{
				                    %> <option value="<%=sfid%>"><%=sfnm%></option> <%
				                    }}}
					         %>
					         </select><span class="style1">&nbsp;*</span>
				         </td>
				        <td bgcolor="#DDDDDD"   class="form"><div>波平占比：</div></td>
				         <td>
				         <input type="text" name="bpzb" readonly="readonly"  value="${elec.bpzb}" class="form1" />%
				         </td>
				      </tr>
				      <tr>
				            <td height="19" bgcolor="#DDDDDD" class="form">票据金额：</td>
				     <td id= "tdpjje"><input type="text" id="pjje" name="pjje" value="${elec.pjje}" class="formright"><span class="style1">&nbsp;*</span></td>
				     <td height="19" bgcolor="#DDDDDD" class="form">票据电量：</td>
				     <td id= "tdpjdl"><input type="text" id="pjdl" name="pjdl" value="${elec.pjdl}" class="formright"><span class="style1">&nbsp;*</span></td>
				      <td bgcolor="#DDDDDD"   class="form"><div>尖峰占比：</div></td>
				         <td>
				         <input type="text" name="jfzb"  readonly="readonly" value="${elec.jfzb}" class="form1" />%
				         </td>
				      </tr>    
				      <tr>
				      <td bgcolor="#DDDDDD" class="form"><div>录入员：</div></td>
					      <td><input type="text" name="enterpersonnel" readonly="readonly" value="<%=accountname %>"  class="formdemo" /></td>
				      <td height="19" bgcolor="#DDDDDD" class="form"><div>抄表操作员：</div></td>
				         <td><input type="text" name="inputoperator" value="${elec.inputoperator}"  class="formdemo" /><span class="style1">&nbsp;*</span>
				         <input type="hidden" size="50" name="autoauditstatus" value="bean.getAutoauditstatus()"  class="form" />
				         <input type="hidden" size="50" name="autoauditdescription" value="bean.getAutoauditdescription()"  class="form" />
				         <input type="hidden" name="ammeterdegreeidFk" value="bean.getAmmeterdegreeidFk()"  class="form" /></td>
				         <td height="19" bgcolor="#DDDDDD" class="form"><div>交费操作员：</div></td>
				         <td><input type="text" name="payoperator" value="${elec.payoperator}"  class="formdemo" /></td>
				      </tr>
				      <tr> <td bgcolor="#DDDDDD" class="form"><div>标准单价：</div></td>
					      <td><input type="text" name="bzdj" id="bzdj" readonly="readonly" value="${averageunitprice}"  class="formdemo" /></td></tr>
				      <tr>
				      <td colspan="2"><input id='kongbai' readonly="readonly"  class="form1"  type='text'  style="width: 100%"></td>
				      <td colspan="2"><input id='csdbdiv' type="text" readonly="readonly"  class="form1" value=""  style="width: 100%">
				      <input id='kongbai1' type="hidden" readonly="readonly"  class="form1"  type='text'  style="width: 100%"></td>
				      <td colspan="2"><input id='dfcbdiv' type='text' readonly="readonly"  class="form1" value="电费超标比例:" type='text'  style="width: 100%"></td>
				     </tr>
				</table>
			</fieldset>
			<br>
						<fieldset>
				<div id="zfbdbdiv">
				<table width="97%" align="right" CellSpacing="1">
				<tr><td></td></tr>
			     <tr>
			     <td colspan='6' class ="ttxx">电量调整申请信息:有调整须填写信息，没有发生的 项目不填。</td>
			     </tr>
			     <tr>
			     		<td bgcolor="#DDDDDD" class="form"><div>电量调整：</div></td>
				 		<td>
				 		<input type="text" name="floatdegree" value="${elec.floatdegree}" onChange = "sumEleadjust()"  class="formright">
				 		<span style="color: #FF0000;font-weight: bold"> *</span>
				 		</td>
				         <td bgcolor="#DDDDDD" class="form"><div>电量调整*倍率：</div></td>
				         <td><input type="text" name="floatdegreeandbl" readonly = "readonly" value="<%= floatdegreeandbl%>" class="formright" />
				         </td>
			     </tr>
			     <tr>
			     <td height="19" bgcolor="#DDDDDD" class="form"><div>电量调整原因说明：</div></td>
				         <td><input type="text" size="20" name="memoam" value="${elec.memo}" class="formdemo" /></td>
				
				          <td bgcolor="#DDDDDD" class="form"><div>报账电量(度)：</div></td>
				         <td><input type="text" id="blhdl" name="blhdl" value="${elec.blhdl}"  readonly="readonly" class="formright"  onpropertychange="fun();getHaodianliangg();yongdainfei();getCsdb()"/></td>
				       
			     <td></td>
			     </tr>
			     <tr><td></td></tr>
			     <tr>
			     <td colspan='6' class ="ttxx">电费调整申请信息：有调整须填写信息</td>
			     </tr>
			      <tr>
				         <td bgcolor="#DDDDDD"   class="form"><div>电费调整(元)：</div></td>
				         <td><input type="text" name="floatpay" value="${elec.floatpay}" onpropertychange = "sumPayadjust()" class="formright" /><span class="style1">&nbsp;*</span></td>
				       
			     </tr>
			     <tr>
			     <td bgcolor="#DDDDDD" class="form"><div>电费调整原因说明:</div></td>
				         <td><input type="text" name="memo" value="${elec.memo1}"  class="formdemo" /></td>
				          <td bgcolor="#DDDDDD" class="form"><div>报账电费(元)：</div></td>
				         <td><input type="text" name="actualpay" value="${elec.actualpay}" readonly="readonly" class="formright" onpropertychange="getMoney();getCsdb();"/></td>
			     <td></td>
			     </tr>
			     </table>
			    </div>
			    </fieldset>
			    <br>
			<fieldset>
				<legend><img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">辅助信息</font></b></legend>
	 			<table width="96%" align="right" CellSpacing="1">
	 			<tr>
				        <td bgcolor="#DDDDDD"   class="form"><div>倍率：</div></td>
				        <td><input type="text" name="beilv"  readonly="readonly" value="${elec.beilv}"   class="form1" /></td>
				        <td bgcolor="#DDDDDD"  class="form"><div>变损值：</div></td>
				        <td><input type="text" name="changevalue"  readonly="readonly" value="${elec.changevalue}"   class="form1" /></td>
				        <td bgcolor="#DDDDDD"   class="form"><div>线损值：</div></td>
				        <td><input type="text" name="linelossvalue"  readonly="readonly" value=""   class="form1"/></td>
				     </tr>
				      <tr>
					      <td bgcolor="#DDDDDD"   class="form"><div>上期电量(度)：</div></td>
					      <td width="21%"><input type="text" name="scydl"  readonly="readonly" value="${ass.blhdl}"   class="form1" /></td>
					      <td bgcolor="#DDDDDD"   class="form"><div>上期电费(元)：</div></td>
					      <td width="21%"><input type="text" name="scdf"  readonly="readonly" value="${ass.actualpay}"   class="form1" /></td>
					      <td bgcolor="#DDDDDD"   class="form"><div>上期单价(元/度)：</div></td>
					      <td width="21%"><input type="text" name="lastunitprice"  readonly="readonly" value="${ass.unitprice}"   class="form1" /></td>
				     </tr>
				     <tr>
					    <td bgcolor="#DDDDDD"   class="form"><div>直流负荷：</div></td>
					    <td ><input type="text" name="zlfh"  readonly="readonly" value="<%=zlfh%>" class="form1"/></td> 
					    <td bgcolor="#DDDDDD"   class="form"><div>交流负荷：</div></td>
					    <td ><input type="text" name="jlfh"  readonly="readonly" value="<%=jlfh%>" class="form1"/></td>
				        <td height="19" bgcolor="#DDDDDD" class="form"><div>报账月份：</div></td>
				      <td><input type="text" name="accountmonth" readonly="readonly" value="${elec.accountmonth}"  class="formdemo" /><span class="style1">&nbsp;*</span></td> 
				     </tr>
				  	 <tr>
				     <td height="19" bgcolor="#DDDDDD" class="form">省定标电量(度)：</td>
				     <td><input type="text" readonly="readonly" class="form1" id="qsdbdl" name="qsdbdl" value="${elec.qsdbdl}"></td>       
				     <td height="19" bgcolor="#DDDDDD" class="form">本地标定标电量(度)：</td>
				     <td><input type="text" readonly="readonly" class="form1" id="edhdl" name="edhdl" value="${elec.edhdl}"></td>
				     <td height="19" bgcolor="#DDDDDD" class="form">流程号：</td>
				     <td><input type="text" readonly="readonly" class="form1" id="liuchenghao" name="liuchenghao" value="${elec.liuchenghao}"></td>
				     </tr>
				      <tr>
				     <td bgcolor="#DDDDDD"   class="form"><div>管理电表上次电表读数：</div></td>        
					  <td width="21%"><input type="text" name="gllastdegree" value="${elec1.lastdegree}" readonly="readonly" class="form1"/>
					  <input type="hidden" name="gldlbz" value="${elec1.gldlbz}"/></td>
					  <td bgcolor="#DDDDDD"   class="form"><div>管理电表本次电表读数：</div></td>        
					  <td width="21%"><input type="text" name="glthisdegree" value="${elec1.thisdegree}" readonly="readonly" class="form1"/></td>
					  <td bgcolor="#DDDDDD"   class="form"><div>审核员：</div></td>        
					  <td width="21%"><input type="text" name="glshy" value="${elec1.manualauditname}" readonly="readonly" class="form1"/></td>
				     </tr>
				      <tr>
				     <td bgcolor="#DDDDDD"   class="form"><div>上次抄表时间：</div></td>        
					  <td width="21%"><input type="text" name="gllasttime" value="${elec1.lastdatetime}" readonly="readonly" class="form1"/></td>
					  <td bgcolor="#DDDDDD"   class="form"><div>本次抄表时间：</div></td>        
					  <td width="21%"><input type="text" name="glthistime" value="${elec1.thisdatetime}" readonly="readonly" class="form1"/></td>
					  <td bgcolor="#DDDDDD"   class="form"><div>抄表员：</div></td>        
					  <td width="21%"><input type="text" name="glcby" value="${elec1.dlinputoperator}" readonly="readonly" class="form1"/></td>
				     </tr>
				      </table>
			</fieldset>
			<br>
	 			<table width="97%" align="right" CellSpacing="1">
				      <tr>
				         <td align="right">
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
								<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">保存</span>
							</div>
				         </td>
				         <td width="7%"></td>
				     </tr>
				</table>
  		</td>
 		<td width="17%" valign="middle">
			 <table border="0" width="100%">
			 	<tr>
			 		<td>
			 		<fieldset>
			 			<table border="0" width="100%">
				 			<tr align="center"><td colspan="2"><div align="center"><b><font size="2">电量分摊</font></b><hr/></div></td></tr>
						 	<tr><td class="form_labell">生产</td><td><input type="text" name="scdl" value="<%=a1 %>" class="formm"/></td></tr>
						 	<tr><td class="form_labell">办公</td><td><input type="text" name="bgdl" value="<%=a2 %>" class="formm"/></td></tr>
						 	<tr><td class="form_labell">营业</td><td><input type="text" name="yydl" value="<%=a3 %>" class="formm"/></td></tr>
						 	<tr><td class="form_labell">信息化支持</td><td><input type="text" name="xxhdl" value="<%=a4 %>" class="formm"/></td></tr>
						 	<tr><td class="form_labell">建设投资</td><td><input type="text" name="jstzdl" value="<%=a5 %>" class="formm"/></td></tr>
						 	<tr><td class="form_labell">代垫电量</td><td><input type="text" name="dddfdl" value="<%=a11 %>" class="formm"/></td></tr>
						</table>
					 </fieldset>
					 </td>
			 	</tr>
			 	<tr>
			 		<td>
			 		<fieldset>
			 			<table border="0" width="100%">
				 			<tr align="center"><td colspan="2"><div align="center"><b><font size="2">电费分摊</font></b><hr/></div></td></tr>
						 	<tr><td class="form_labell">生产</td><td><input type="text" name="scdff" value="<%=a6 %>" class="formm"/></td></tr>
						 	<tr><td class="form_labell">办公</td><td><input type="text" name="bgdf" value="<%=a7 %>" class="formm"/></td></tr>
						 	<tr><td class="form_labell">营业</td><td><input type="text" name="yydf" value="<%=a8 %>" class="formm"/></td></tr>
						 	<tr><td class="form_labell">信息化支持</td><td><input type="text" name="xxhdf" value="<%=a9 %>" class="formm" /></td></tr>
						 	<tr><td class="form_labell">建设投资</td><td><input type="text" name="jstzdf" value="<%=a10 %>" class="formm"/></td></tr>
						 	<tr><td class="form_labell">代垫电费</td><td><input type="text" name="dddfdf" value="<%=a12 %>" class="formm"/></td></tr>
						 	<tr><td >	
						 		<input type="hidden" name="dbili1" value="<%=dbili1%>">
				         		<input type="hidden" name="dbili2" value="<%=dbili2%>">
				         		<input type="hidden" name="dbili3" value="<%=dbili3%>">
				         		<input type="hidden" name="dbili4" value="<%=dbili4%>">
				        		<input type="hidden" name="dbili5" value="<%=dbili5%>">
				        		<input type="hidden" name="dbili6" value="<%=dbili6%>">
							</td></tr>
						 </table>
					   </fieldset>
			 		</td>
			 	</tr>
			 </table>
			 <input type="hidden" name="linelosstype" value="<%=linelosstype %>">
			 <input type="hidden" name="accountid" value="<%=accountid %>"/>
			 <input type="hidden" name = "ammeterdegreeid" value="<%=degreeid%>"/>
			 <input type="hidden" name="ammeterdegree_id" value="${elec.ammeterdegree_id}"/>
			 <input type="hidden" name="electricfee_id" value="${elec.electricfee_id}"/>
			  <input type="hidden" name="kongbai3" value="${elec.dedhdl/100}" />
			 <input type="hidden" name="kongbai2" value="${elec.csdb/100}" />
			<input type="hidden" name="scb" value="${elec.scb}" />
			<input type="hidden" name="propertycode" value="${elec.propertycode}" />
			<input type="hidden" name="stationtypecode" value="${elec.stationtypecode}" />
			<input type="hidden" name="dfzflxcode" value="${elec.dfzflxcode}" />
			<input type="hidden" name="gdfscode" value="${elec.gdfscode}" />
		</td>
	</tr>
</table>
</form>
</body>
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
			var json = eval(res);
			var cs = json[0];
			var cf = json[1];
			document.getElementById("csdbdiv").value="该记录超省标:"+cs;
			document.getElementById("dfcbdiv").value="电费超标比例:"+cf;
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function getCsdb() {
	var bzdf = document.form1.actualpay.value;
	var bzdj = document.form1.bzdj.value;
	var blhdl = document.form1.blhdl.value;
	var qsdbdl = document.form1.qsdbdl.value;
	var stationtype = document.form1.stationtypecode.value;
	var zlfh = document.form1.zlfh.value;
	var jlfh = document.form1.jlfh.value;
	var property = document.form1.propertycode.value;
	var edhdl = document.form1.edhdl.value;
	var scb = document.form1.scb.value;
	var dbid = document.form1.ammeteridFk.value;
	var shi = '${elec.shi}';
	var thisdatetime = document.form1.thisdatetime.value;
	var lastdatetime = document.form1.lastdatetime.value;
	var actualdegree = document.form1.actualdegree.value;
	var kong1 = document.form1.actualdegree.value.replace(/[ ]/g,"");
	var kong2 = document.form1.thisdatetime.value.replace(/[ ]/g,"");
	if(parseFloat(blhdl)==blhdl
			&&isNaN(actualdegree)==false
			&& kong1!="" 
			&& kong2!="" 
			&& thisdatetime>=lastdatetime
			&& Number(zlfh)!=0
			&& Number(jlfh)!=0
			&& Number(edhdl)!=0
			&& Number(qsdbdl)!=0
			&& Number(scb)!=0
			&& property!="" && property!="null"){
	sendRequest1(path
+"/servlet/EnhanceElecBillServlet?action=getCsdb&bzdf="+bzdf+"&bzdj="+bzdj+"&qsdbdl="+qsdbdl+"&blhdl="+blhdl+"&stationtype="+stationtype+"&zlfh="
+zlfh+"&jlfh="+jlfh+"&propertycode="+property+"&edhdl="+edhdl
+"&scb="+scb+"&dbid="+dbid+"&shi="+shi+"&thisdatetime="+thisdatetime
+"&lastdatetime="+lastdatetime+"&dbydl="+actualdegree);
}else{
	document.getElementById("csdbdiv").value="该记录超省标:";
}
}
window.onload=function(){
	fun();
	getCsdb();
}
</script>
</html>

