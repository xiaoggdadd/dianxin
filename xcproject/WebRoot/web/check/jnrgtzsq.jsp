<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ptac.app.checksign.manElectricCheck.bean.CountryHeadBean" %>
<%@ page import="com.noki.mobi.common.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ptac.app.electricmanage.electricitybill.dao.ElecBillDao"%>
<%@ page import="com.ptac.app.electricmanage.electricitybill.dao.ElecBillDaoImp"%>
<%@ page import="com.ptac.app.electricmanage.electricitybill.bean.Share"%>
<%@ page import="com.ptac.app.electricmanageUtil.Format"%>
<%
	String path = request.getContextPath();
	Account account=(Account)session.getAttribute("account");
	String accountname=account.getAccountName();
	String accountid=account.getAccountName();
	String login=account.getAccountId();
	String roleid = account.getRoleId();//权限
	String loginName = (String)session.getAttribute("loginName");
	
	String dbid=request.getParameter("dbid");
	String dlid=request.getParameter("dlid");
	String electricfee_id = request.getParameter("electricfee_id");
	String edhdl = request.getParameter("edhdl");
	String qsdbdl = request.getParameter("qsdbdl");
	String jszq = request.getParameter("jszq");
	
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
	
    ArrayList listm=new ArrayList();
    ElecBillDao dao = new ElecBillDaoImp();
	listm=dao.share1(dbid);
	String shuoshuzhuanye="";
	String dbili="",dbili1="",dbili2="",dbili3="",dbili4="",dbili5="",dbili6="";

	if(listm!=null){
		int fmcount = ((Integer)listm.get(0)).intValue();
		for( int k = fmcount;k<listm.size()-1;k+=fmcount){
			shuoshuzhuanye = (String)listm.get(k+listm.indexOf("SHUOSHUZHUANYE"));
			shuoshuzhuanye = shuoshuzhuanye != null ? shuoshuzhuanye : "";
			dbili = (String)listm.get(k+listm.indexOf("DBILI"));
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

%>
<html>
<head>
<title>
电费审核调整申请信息
</title>
<style type="text/css">
           
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
}
.style1 {
	color:red;
	font-weight: bold;
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
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
			width:200px;
		}

 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>

<script language="javascript">
var path = '<%=path%>';

//6、倍率异常：倍率小于1；或倍率 / 5 不为正整数；提示倍率异常
function beilvExcept(){
	var beilv = Number(document.getElementById('beilv').value); //原始倍率
	if(beilv==0){
		beilv = 1;
	}
	var beilvexcept = "${configurations.beilvexcept}";
	if(beilv < 1 || (beilv != 1 && beilv % beilvexcept != 0)){
		alert("倍率异常!");
	}else{
		return true;
	}
}
//只能调小不能跳大判断
function adjustLittle(){
	var sjtzbz ="${sjtzbz}";//市级调整标志位（市级调整审核不进行调大调小判断）
	if(sjtzbz=="1"){
			return true;	
	}else{
		var hiddenfloatdegree = Number(document.form1.hiddenfloatdegree.value);//原电量调整
		var hiddenbeilv = Number(document.form1.hiddenbeilv.value);//原倍率
		var hiddenlinelossvalue = Number(document.form1.hiddenlinelossvalue.value);//原线损值
		var hiddenchangevalue = Number(document.form1.hiddenchangevalue.value);//原变损值
		var hiddenfloatpay= Number(document.form1.hiddenfloatpay.value);//原电费调整
		
		var floatdegree = Number(document.form1.floatdegree.value);//电量调整
		var beilv = Number(document.form1.beilv.value);//倍率
		var linelossvalue = Number(document.form1.linelossvalue.value);//线损值
		var changevalue = Number(document.form1.changevalue.value);//变损值
		var floatpay= Number(document.form1.floatpay.value);//电费调整
		
		if(floatdegree <= hiddenfloatdegree){
			if(beilv <= hiddenbeilv){
				if(linelossvalue <= hiddenlinelossvalue){
					if(changevalue <= hiddenchangevalue){
						if(floatpay <= hiddenfloatpay){
							return true;
						}else{
							alert("电费调整只能调小不能调大!");
						}
					}else{
						alert("变损值只能调小不能调大!");
					}
				}else{
					alert("线损值只能调小不能调大!");
				}
			}else{
				alert("倍率只能调小不能调大!");
			}
		}else{
			alert("电量调整只能调小不能调大!");
		}
	}
}

function checkAdjust(){//电费电量调整原因检查
	var dltzyy = document.form1.ammemo;//电量调整原因
	var dftzyy = document.form1.efmemo;//电费调整原因
	var dltzyyy = document.form1.ammemo.value.replace(/[ ]/g,"");
	var dftzyyy = document.form1.efmemo.value.replace(/[ ]/g,"");
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

function saveAccount(){
	var dltz = document.form1.floatdegree.value;//电量调整
	var dltz1= document.form1.floatdegree.value.replace(/[ ]/g,"");
	var dftz = document.form1.floatpay.value;//电费调整
	var dftz1= document.form1.floatpay.value.replace(/[ ]/g,"");
	var dltzyy = document.form1.ammemo;//电量调整原因
	var dltzyyy = document.form1.ammemo.value.replace(/[ ]/g,"");
	var dftzyy = document.form1.efmemo;//电费调整原因
	var dftzyyy = document.form1.efmemo.value.replace(/[ ]/g,"");
	var beilv = document.getElementById('beilv').value.replace(/[ ]/g,"");//倍率
	var bl = document.getElementById('beilv').value; //倍率
	var floatdegree = Number(document.form1.floatdegree.value);
	var floatpay = Number(document.form1.floatpay.value);
	var linelossvalue = document.form1.linelossvalue.value;//线损值
	var linelossvalue1 = document.form1.linelossvalue.value.replace(/[ ]/g,"");//线损值
	var changevalue = document.form1.changevalue.value;//变损值
	var changevalue1 = document.form1.changevalue.value.replace(/[ ]/g,"");//变损值
	if(isNaN(dltz)==false&&dltz1!=""){
		 if(isNaN(dftz)==false&&dftz1!=""){
					if(isNaN(bl)==false&&beilv!=""){
						if(isNaN(linelossvalue)==false&&linelossvalue1!=""){
							if(isNaN(changevalue)==false&&changevalue1!=""){
									if(adjustLittle()){
										 if(beilvExcept()==true){
											if(checkAdjust()==true){
												if (confirm("请确认信息正确！")) {
													document.form1.action = path + "/servlet/EnhanceCountryHeadServlet?action=save";
													document.form1.submit();
													showdiv("请稍等..............");
												}
											}					
										}
									 }
								}else{
								alert("您输入的信息有误，变损值必须为数字！");
								}
						}else{
						alert("您输入的信息有误，线损值必须为数字！");
					}
					}else{
						alert("您输入的信息有误，倍率必须为数字！");
					}
		}else{
			 alert("您输入的信息有误，电费调整必须为数字！");
			}
	 }else{
		alert("您输入的信息有误，电量调整必须为数字！");
	 }
}

function getDlhj(){
//电量调整改变
	var lastdegree ="${bean.lastdegree}";//上次电表读数
	var thisdegree ="${bean.thisdegree}";//本次电表读数
	var floatdegree = Number(document.getElementById('floatdegree').value);//电量调整
	var bl = Number(document.getElementById('beilv').value); //倍率
	var ydl = Number((thisdegree - lastdegree) * bl).toFixed(2);//电表用电量
	var changevalue = Number(document.getElementById('changevalue').value);//变损值
	var linelossvalue = Number(document.getElementById('linelossvalue').value);//线损值
	var linelosstype = document.getElementById("linelosstype").value;//线损类型
	document.form1.floatdegreeandbl.value = (floatdegree * bl).toFixed(2);//电量调整*倍率
	document.form1.actualdegree.value = ydl;//电表用电量
	if(linelosstype == "线损调整"){//线损调整
		document.form1.bzydl.value = (ydl + (floatdegree + changevalue + linelossvalue) * bl).toFixed(2);//报账电量
	}else if(linelosstype == "线损比例"){//线损比例
		var actuallinelossvalue = (thisdegree - lastdegree) * linelossvalue;//实际线损值
		document.form1.actuallinelossvalue.value = actuallinelossvalue;//实际线损值
		document.form1.bzydl.value = (ydl + (floatdegree + changevalue + actuallinelossvalue) * bl).toFixed(2);//报账电量
	}
	var bzdl = document.getElementById('bzydl').value;//报账电量
	var unitprice = document.getElementById('unitprice').value;//单价
	document.form1.yddf.value = (Number(unitprice)*Number(bzdl)).toFixed(2);
	var floatpay = Number(document.form1.floatpay.value);//电费调整
	document.form1.bzdf.value = Number(parseFloat(document.form1.yddf.value)+parseFloat(floatpay)).toFixed(2);

}
//用电电费
	function yongdainfei(){
		var yongdianliang = document.getElementById('bzydl').value;//报账电量
		var zgdunitprice = document.getElementById('unitprice').value;//单价
		document.form1.yddf.value = (Number(zgdunitprice)*Number(yongdianliang)).toFixed(2);
	}

function getDfhj(){
     //电费调整改变
	var yddf = Number(document.getElementById('yddf').value);//用电电费
	var floatpay = Number(document.form1.floatpay.value);//电费调整
	document.form1.bzdf.value = Number(parseFloat(yddf)+parseFloat(floatpay)).toFixed(2);//报账电费
}


         function getMoney(){
        	var dbili1=document.form1.dbili1.value;
        	var dbili2=document.form1.dbili2.value;
        	var dbili3=document.form1.dbili3.value;
        	var dbili4=document.form1.dbili4.value;
        	var dbili5=document.form1.dbili5.value;
        	var dbili6=document.form1.dbili6.value;
            var bzdf= document.form1.bzdf.value;
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
        		var v6=parseFloat(bzdf);
        	    var v7=(parseFloat(dbili1)/100)*parseFloat(bzdf)+(parseFloat(dbili2)/100)*parseFloat(bzdf)+(parseFloat(dbili3)/100)*parseFloat(bzdf)+(parseFloat(dbili4)/100)*parseFloat(bzdf)+(parseFloat(dbili5)/100)*parseFloat(bzdf);
        		document.form1.dddfdf.value=(v6-v7).toFixed(2);
        		}else{
        		document.form1.scdff.value=((parseFloat(dbili1)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.bgdf.value=((parseFloat(dbili3)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.xxhdf.value=((parseFloat(dbili4)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.jstzdf.value= ((parseFloat(dbili5)/100)*parseFloat(bzdf)).toFixed(2);
        		
        		}
        		if(dbili5!="0.00"&&dbili6=="0.00"){
        		var v6=parseFloat(bzdf);
        	    var v7=(parseFloat(dbili1)/100)*parseFloat(bzdf)+(parseFloat(dbili2)/100)*parseFloat(bzdf)+(parseFloat(dbili3)/100)*parseFloat(bzdf)+(parseFloat(dbili4)/100)*parseFloat(bzdf)+(parseFloat(dbili6)/100)*parseFloat(bzdf);
        		document.form1.jstzdf.value=(v6-v7).toFixed(2);
        		}else{
        		document.form1.scdff.value=((parseFloat(dbili1)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.bgdf.value=((parseFloat(dbili3)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.xxhdf.value=((parseFloat(dbili4)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.dddfdf.value= ((parseFloat(dbili6)/100)*parseFloat(bzdf)).toFixed(2);
        		
        		}
        		if(dbili4!="0.00"&&dbili5=="0.00"&&dbili6=="0.00"){
        		var v6=parseFloat(bzdf);
        	    var v7=(parseFloat(dbili1)/100)*parseFloat(bzdf)+(parseFloat(dbili2)/100)*parseFloat(bzdf)+(parseFloat(dbili3)/100)*parseFloat(bzdf)+(parseFloat(dbili5)/100)*parseFloat(bzdf)+(parseFloat(dbili6)/100)*parseFloat(bzdf);
        		document.form1.xxhdf.value=(v6-v7).toFixed(2);
        		}else{
        		document.form1.scdff.value=((parseFloat(dbili1)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.bgdf.value=((parseFloat(dbili3)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.jstzdf.value= ((parseFloat(dbili5)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.dddfdf.value= ((parseFloat(dbili6)/100)*parseFloat(bzdf)).toFixed(2);
        		}
        		if(dbili3!="0.00"&&dbili4=="0.00"&&dbili5=="0.00"&&dbili6=="0.00"){
        		var v6=parseFloat(bzdf);
        	    var v7=(parseFloat(dbili1)/100)*parseFloat(bzdf)+(parseFloat(dbili2)/100)*parseFloat(bzdf)+(parseFloat(dbili4)/100)*parseFloat(bzdf)+(parseFloat(dbili5)/100)*parseFloat(bzdf)+(parseFloat(dbili6)/100)*parseFloat(bzdf);
        		document.form1.yydf.value=(v6-v7).toFixed(2);
        		}else{
        		document.form1.scdff.value=((parseFloat(dbili1)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.xxhdf.value=((parseFloat(dbili4)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.dddfdf.value= ((parseFloat(dbili6)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.jstzdf.value= ((parseFloat(dbili5)/100)*parseFloat(bzdf)).toFixed(2);
        		}
        		if(dbili2!="0.00"&&dbili3=="0.00"&&dbili4=="0.00"&&dbili5=="0.00"&&dbili6=="0.00"){
        		var v6=parseFloat(bzdf);
        	    var v7=(parseFloat(dbili1)/100)*parseFloat(bzdf)+(parseFloat(dbili3)/100)*parseFloat(bzdf)+(parseFloat(dbili4)/100)*parseFloat(bzdf)+(parseFloat(dbili5)/100)*parseFloat(bzdf)+(parseFloat(dbili6)/100)*parseFloat(bzdf);
        		document.form1.bgdf.value=(v6-v7).toFixed(2);
        		}else{
        		document.form1.scdff.value=((parseFloat(dbili1)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.xxhdf.value=((parseFloat(dbili4)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.dddfdf.value= ((parseFloat(dbili6)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.jstzdf.value= ((parseFloat(dbili5)/100)*parseFloat(bzdf)).toFixed(2);
        		}
        		if(dbili1!="0.00"&&dbili2=="0.00"&&dbili3=="0.00"&&dbili4=="0.00"&&dbili5=="0.00"&&dbili6=="0.00"){
        		var v6=parseFloat(bzdf);
        	    var v7=(parseFloat(dbili2)/100)*parseFloat(bzdf)+(parseFloat(dbili3)/100)*parseFloat(bzdf)+(parseFloat(dbili4)/100)*parseFloat(bzdf)+(parseFloat(dbili5)/100)*parseFloat(bzdf)+(parseFloat(dbili6)/100)*parseFloat(bzdf);
        		document.form1.scdff.value=(v6-v7).toFixed(2);
        		}else{
        		document.form1.yydf.value=	((parseFloat(dbili2)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.xxhdf.value=((parseFloat(dbili4)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.dddfdf.value= ((parseFloat(dbili6)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.jstzdf.value= ((parseFloat(dbili5)/100)*parseFloat(bzdf)).toFixed(2);
        		document.form1.bgdf.value=((parseFloat(dbili3)/100)*parseFloat(bzdf)).toFixed(2);	
        		}
        }
         
            function getHaodianliangg(){
        	var dbili1=document.form1.dbili1.value;
        	var dbili2=document.form1.dbili2.value;
        	var dbili3=document.form1.dbili3.value;
        	var dbili4=document.form1.dbili4.value;
        	var dbili5=document.form1.dbili5.value;
        	var dbili6=document.form1.dbili6.value;
        	var bzydl= document.form1.bzydl.value;
        	if(dbili1==null||dbili1==""){dbili1="0.00";}
        	if(dbili2==null||dbili2==""){ dbili2="0.00"; }
        	if(dbili3==null||dbili3==""){ dbili3="0.00"; }
        	if(dbili4==null||dbili4==""){ dbili4="0.00";}
        	if(dbili5==null||dbili5==""){ dbili5="0.00"; }
        	if(dbili6==null||dbili6==""){dbili6="0.00";}
        	 
        	 document.form1.scdl.value=((parseFloat(dbili1)/100)*parseFloat(bzydl)).toFixed(2);//生产
        	 document.form1.bgdl.value=((parseFloat(dbili3)/100)*parseFloat(bzydl)).toFixed(2);	 //办公
        	 document.form1.yydl.value=	((parseFloat(dbili2)/100)*parseFloat(bzydl)).toFixed(2);// 营业
        	 document.form1.xxhdl.value=((parseFloat(dbili4)/100)*parseFloat(bzydl)).toFixed(2); // 信息化	
        	 document.form1.jstzdl.value=((parseFloat(dbili5)/100)*parseFloat(bzydl)).toFixed(2); // 建设投资
        	 document.form1.dddfdl.value=((parseFloat(dbili6)/100)*parseFloat(bzydl)).toFixed(2);// 代垫电量
          }
        

$(function(){
	$("#saveBtn").click(function(){
		saveAccount();
		
	});
});
		
</script>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
</head>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST" id="form">
		<table width="100%"  border="0" cellspacing="1" cellpadding="1"  >
			<tr>
				<td colspan="4" width="50" >
					<div style="width:700px;height:50px">
						
						<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电费审核调整申请信息</span>	
					</div>
				</td>
				<td width="500">&nbsp;</td>
			</tr>
      <tr class="form_label"><td>&nbsp;</td></tr> 
	  <tr bgcolor="F9F9F9" class="form_label">
           <td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />电量调整申请信息:有调整须填写信息，没有发生的项目不填。</td>
      </tr>
      <tr class="form_label">
      <td  height="8%" bgcolor="#DDDDDD" width="15%">电量调整：</td>
	        <td width="25%">
		        <input type="text" id = "floatdegree" name="floatdegree" value="${bean.floatdegree}" onChange="getDlhj()" style="width:130px" />度
		         <input type="hidden" name="hiddenfloatdegree" value="${bean.floatdegree}"  readonly="readonly" style="width:130px" />
			</td>
			<td  height="8%" bgcolor="#DDDDDD" width="15%">倍率：</td>
	        <td width="25%">
		        <input type="text" id = "beilv" name="beilv" value="${bean.beilv}" onChange="getDlhj()" style="width:130px" />
		         <input type="hidden" id = "hi" name="hiddenbeilv" value="${bean.beilv}" style="width:130px" />
			</td>
			<td  height="8%" bgcolor="#DDDDDD" width="15%">电量调整*倍率：</td>
	        <td width="25%">
		        <input type="text" id = "floatdegreeandbl" name="floatdegreeandbl" value="${bean.floatdegreeandbl}" readonly="readonly" style="width:130px" />度
			</td>
		</tr>
		
		<tr class="form_label">
      <td  height="8%" bgcolor="#DDDDDD" width="15%">线损类型：</td>
	        <td width="25%">
		        <input type="text" id = "linelosstype" name="linelosstype" value="${bean.linelosstype}" readonly="readonly" style="width:130px" />
			</td>
			<td  height="8%" bgcolor="#DDDDDD" width="15%">线损值：</td>
	        <td width="25%">
		        <input type="text" id = "linelossvalue" name="linelossvalue" value="${bean.linelossvalue}" onChange="getDlhj()" style="width:130px" />
		         <input type="hidden" id = "hiddenlinelossvalue" name="hiddenlinelossvalue" value="${bean.linelossvalue}"  style="width:130px" />
			</td>
			<td  height="8%" bgcolor="#DDDDDD" width="15%">变损值：</td>
	        <td width="25%">
		        <input type="text" id = "changevalue" name="changevalue" value="${bean.changevalue}" onChange="getDlhj()" style="width:130px" />
		         <input type="hidden" id = "hiddenchangevalue" name="hiddenchangevalue" value="${bean.changevalue}" style="width:130px" />
			</td>
		</tr>
		
		 <tr class="form_label">
		 <td  height="8%" bgcolor="#DDDDDD" width="15%">电量调整原因说明：</td>
	        <td width="25%">
		       	<input type="text" name="ammemo" value="${bean.ammemo}"  style="width:130px" />		        		 					
			</td>
			<td  height="8%" bgcolor="#DDDDDD" width="15%">报账电量：</td>
	        <td width="25%">
		        <input type="text" id = "bzydl" name="bzydl" value="${bean.blhdl}" onpropertychange="getHaodianliangg();yongdainfei()" readonly="readonly" style="width:130px" />	
		        <input type="hidden" name="hiddenbzydl" value="${bean.blhdl}"  readonly="readonly" style="width:130px" />	        		 					
			</td>
		</tr>
       
       <tr class="form_label"><td>&nbsp;</td></tr>  
       <tr bgcolor="F9F9F9" class="form_label">
           <td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />电费调整申请信息：有调整须填写信息</td>
       </tr>
		<tr class="form_label">
			<td  height="8%" bgcolor="#DDDDDD" width="15%">电费调整：</td>
	        <td width="25%">
		        <input type="text" name="floatpay" value="${bean.floatpay}" onChange="getDfhj()" style="width:130px" />元
		         <input type="hidden" name="hiddenfloatpay" value="${bean.floatpay}"  style="width:130px" />		        		 					
			</td>
		</tr>
		
		<tr class="form_label">
			<td  height="8%" bgcolor="#DDDDDD" width="15%">电费调整原因说明：</td>
	        <td width="25%">
		       	<input type="text" name="efmemo" value="${bean.efmemo}"  style="width:130px" />		        		 					
			</td>
			<td  height="8%" bgcolor="#DDDDDD" width="15%">报账电费：</td>
	        <td width="25%">
		        <input type="text" name="bzdf" value="${bean.actualpay}" onpropertychange="getMoney()" readonly="readonly" style="width:130px" />元
		        <input type="hidden" name="hiddenbzdf" value="${bean.actualpay}"  readonly="readonly" style="width:130px" />		        		 					
			</td>
		</tr>
    <tr class="form_label" >
        <td colspan="8">
		<div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:26px">
			 <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>      
		</div>
        </td>
      </tr>
			  
</table>
	
	<input type="hidden" name="scdl" value="<%=a1 %>"/>
	<input type="hidden" name="bgdl" value="<%=a2 %>"/>
	<input type="hidden" name="yydl" value="<%=a3 %>"/>
	<input type="hidden" name="xxhdl" value="<%=a4 %>"/>
	<input type="hidden" name="jstzdl" value="<%=a5 %>"/>
	<input type="hidden" name="dddfdl" value="<%=a11 %>"/>
	
	<input type="hidden" name="scdff" value="<%=a6 %>"/>
	<input type="hidden" name="bgdf" value="<%=a7 %>"/>
	<input type="hidden" name="yydf" value="<%=a8 %>"/>
	<input type="hidden" name="xxhdf" value="<%=a9 %>"/>
	<input type="hidden" name="jstzdf" value="<%=a10 %>"/>
	<input type="hidden" name="dddfdf" value="<%=a12 %>"/>
		
	<input type="hidden" name="dbili1" value="<%=dbili1%>">
	<input type="hidden" name="dbili2" value="<%=dbili2%>">
	<input type="hidden" name="dbili3" value="<%=dbili3%>">
	<input type="hidden" name="dbili4" value="<%=dbili4%>">
	<input type="hidden" name="dbili5" value="<%=dbili5%>">
	<input type="hidden" name="dbili6" value="<%=dbili6%>">
	
	<input type="hidden" name="accountname" value="<%=accountname %>"/>
	<input type="hidden" name="accountid" value="<%=accountid %>"/>
	<input type="hidden" name="dbid" value="<%=dbid%>"/>
	<input type="hidden" name="dlid" value="<%=dlid%>"/>
	<input type="hidden" name="electricfee_id" value="<%=electricfee_id%>"/>
	<input type="hidden" name="edhdl" value="<%=edhdl%>"/>
	<input type="hidden" name="qsdbdl" value="<%=qsdbdl%>"/>
	<input type="hidden" name="jszq" value="<%=jszq%>"/>
	<input type="hidden" id = "actualdegree" name="actualdegree" value="${bean.dbydl}"/>
	<input type="hidden" id = "yddf" name="yddf" value="${bean.yddf}"/>
	<input type="hidden" id = "unitprice" name="unitprice" value="${bean.unitprice}"/>
	<input type="hidden" id = "actuallinelossvalue" name="actuallinelossvalue" value="${bean.actuallinelossvalue}"/>
	<input type="hidden" id = "bzz" name="bzz" value="${bean.bzz}"/>
	<input type="hidden" id = "stationtype" name="stationtype" value="${bean.stationtype}"/>
	<input type="hidden" id = "property" name="property" value="${bean.property}"/>
</form>
</body>
</html>

