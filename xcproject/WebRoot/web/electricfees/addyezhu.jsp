<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.Double" %>

<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	Account account=(Account)session.getAttribute("account");
	String jzproperty=request.getParameter("jzproperty");//获取新站点的属性  
	
	String accountname=account.getAccountName();
	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd");
	String entrytime=mat.format(new Date());
	System.out.println("系统时间"+entrytime+"  shengId:"+shengId+"  loginName:"+loginName+"  accountname:"+accountname+" jzproperty:"+jzproperty);
	
%>
<html>
<head>
<title>
addAccount
</title>
<style>
       
            .style1 {
	color: #FF0000;
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
#div1{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div1 p{ float:left;font-size:12px; width:110px; cursor:pointer;}
 </style>
 <style type="text/css">
		.seperator{
			width:100%;
			margin-top:10px;
			margin-bottom: -10px;
		}
		.seperator .first{
			width:5%;
			position: relative;
			float: left;
		}
		.seperator span{
			position: relative;
			float:left;
			/*font-family: 微软雅黑;*/
			font-size: 16px;
		}
		.seperator .second{
			width:30%;
			position: relative;
		}
		.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			width:200px;
		}
		.fsx{
			
			font-size: 12px;
			
		}
		
</style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
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
<script language="javascript">
var path = '<%=path%>';
  function JZtypes(sxbzw){   //基站类型/接入网类型
	var jzpro=document.form1.jzproperty.value;
	//var yf=document.form1.yflx.value;
	var zdlxx=document.form1.stationtype.value;
	//var zl=Number(document.form1.zlfh.value);

	// kzinfo();
	if(sxbzw=="1"){
		zdsx();
		}
	}
function ktslhj(){


var ktw=document.form1.jfsckt.value;
var jmkt=document.form1.ktjmps.value;
//ktyps,kteps,ktps,ktships,ktwps,ktsps

var kty=document.form1.ktyps.value;
var kte=document.form1.kteps.value;
var ktp=document.form1.ktps.value;
var ktsh=document.form1.ktships.value;
var ktw=document.form1.ktwps.value;
var kts=document.form1.ktsps.value;


document.form1.jfsckt.value=parseInt(jmkt)+parseInt(kty)+parseInt(kte)+parseInt(ktp)+parseInt(ktsh)+parseInt(ktw)+parseInt(kts);


}
function ktglhj(){
//计算空调总功率
var k1=document.form1.ktypzgl.value;
var k2=document.form1.ktywzgl.value;
var k3=document.form1.ktepzgl.value;
var k4=document.form1.ktspzgl.value;
var k5=document.form1.ktwpzgl.value;
var k6=document.form1.ktshipzgl.value;
var k7=document.form1.ktjmzgl.value;

document.form1.ktzgl.value=parseInt(k1)+parseInt(k2)+parseInt(k3)+parseInt(k4)+parseInt(k5)+parseInt(k6)+parseInt(k7);
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
	function saveAccount(){
          	if(   
          	        checkNotnull(document.form1.name,"名称")
          			) 			
	     	   {
				addzhandian();
			  }                                    
   }
        	function addzhandian(){
        		if(confirm("您将要添加业主信息！确认信息正确！")){
        		
          		document.form1.action=path+"/servlet/DFDLservlet?action=addyezhu";
				document.form1.submit();
				showdiv("请稍等..............");
            }
        	}
        function retVname(){
        	addzhandian();
        }
       
        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！")
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        
        
        //=点击展开关闭效果=
		function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
		var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
		var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
		var openTip = oOpenTip || "";
		var shutTip = oShutTip || "";
		if(targetObj.style.display!="none"){
  		 if(shutAble) return;
   			targetObj.style.display="none";
   			if(openTip  &&  shutTip){
    		sourceObj.innerHTML = shutTip; 
  			 }
		} else {
  		 targetObj.style.display="block";
   		if(openTip  &&  shutTip){
   		 sourceObj.innerHTML = openTip; 
   		}
		}
	}    
	   function fanhui(){       
               document.form1.action=path+"/web/electricfees/yezhu_search.jsp";
              document.form1.submit();
        }
        function queding(){
	var oDiv=document.getElementById('div1');
	var oUl=oDiv.getElementsByTagName('ul')[0];
	var obj=document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
	var str1="";
	var str2="";
	for(var i=0;i< obj.length;i++){
		if(obj[i].checked){
			var m = obj[i].value.toString().indexOf(",");
			var bm = obj[i].value.toString().substring(0,m);
		    var mc = obj[i].value.toString().substring(m+1,obj[i].value.toString().length);
			str1 = str1+"'"+bm+"',";
			str2 = str2+mc+",";
		}
	}
	str1=str1.substring(0,str1.length-1);
	str2=str2.substring(0,str2.length-1);
	document.form1.zdlx1.value=str1;
	document.form1.zdlx.value=str2;
	oUl.style.display='none';
}
//取消选中   
function quxiao(){ 
	var oDiv=document.getElementById('div1');
	var oUl=oDiv.getElementsByTagName('ul')[0];  
	var obj = document.getElementsByName("CheckboxGroup1");
	if(obj.length){
		for(var i=0;i<obj.length;i++){ 
			obj[i].checked = false;   
		}
		oUl.style.display='none';   
	}else{   
		obj.checked = false; 
		oUl.style.display='none';   
	}   
}
function reset(){
	document.form1.jzname.value="";
	document.form1.address.value="";
	document.form1.fuzeren.value="";
	document.form1.area.value="";


}
	   

        $(function(){
			$("#cancelBtn").click(function(){
				fanhui();
			});
			$("#resetBtn").click(function(){
				//$.each($("form input[type='text']"),function(){
				//	$(this).val("");
				//})
				reset();
			});
			$("#saveBtn").click(function(){
				saveAccount();
			});
			
			$("#liulan").click(function(){
				liulan();
			});
			$("#ming").click(function(){
				 document.getElementById("jznamezs").style.display="block";
			});
			$("#queding").click(function(){
		queding();		
	});
	$("#quxiao").click(function(){
		quxiao();		
	});
		});
		
</script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;" >
<form action="" name="form1" method="POST">
  
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" >
		
	                      <tr><td>
	                      <table width="100%"  border="0" cellspacing="0" cellpadding="0" align="left">
	                        <tr>
							<td colspan=1 width="700" height=37 >
							<span style="color:black;font-weight:bold;font-size=15;"> 添加业主名称</span></td>
							<input type="hidden" name="accountname" value="<%=accountname %>"/>
							<td width="380">&nbsp;</td>
							</tr>
							</table>
							</td>
						</tr>
			<tr class="form_label">
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
					<tr class="form_label">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">市：</div>
         </td>
         <td width="25%">
         <select name="shi" style="width:130;" onchange="changeCity()"()>
         		<option value="0">请选择</option>
         		<%
	         	ArrayList shenglist = new ArrayList();
	         	shenglist = commBean.getAgcode(shengId,"0",loginName);//获取该登录用户负责的城市 集合 
	         	if(shenglist!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)shenglist.get(0)).intValue();
	         		for(int i=scount;i<shenglist.size()-1;i+=scount)
                    {
                    	sfid=(String)shenglist.get(i+shenglist.indexOf("AGCODE"));
                    	sfnm=(String)shenglist.get(i+shenglist.indexOf("AGNAME"));
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         </td>
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">区、县：</div>
         </td>
         <td width="26%">
         <select name="xian" style="width:130" onchange="changeCountry()">
         		<option value="0">请选择</option>
         	</select><span class="style1">&nbsp;*</span>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">乡镇：</div>
         </td>
         <td width="26%">
         <select name="xiaoqu" id="xiaoqu" style="width:130">
         		<option value="0">请选择</option>
         	</select><span class="style1">&nbsp;*</span>
         </td>
      </tr>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">
	<tr class="form_label">
	  <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">业主名称：</div>
         </td>
         <td width="25%"><input type="text" name="name"  style="width:130px" title="选填项，站点所在的具体物理位置 "/></td>
	</tr>
<tr class="form_label">
        <td colspan="6">
          <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:14px">
	           <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
          </div>
            
          <div id="resetBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:16px">
	           <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">重置</span>
          </div>
          
          <div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:18px">
	           <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>
          </div>
        </td>
      </tr>
 
</table>
</form>

<script type="text/javascript">
var path = '<%=path%>';
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
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="sheng"){
			XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
		}else if(para=="jzproperty"){
			XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数
			
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
	}
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		//alert(XMLHttpReq.status);
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
            	var res = XMLHttpReq.responseText;
              window.alert(res);
             
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    
function processResponse_sheng() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            	
            	//var res = XMLHttpReq.responseText;
            
              updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateQx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZd(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
	function processResponse_zdlx() {
	//alert("333");
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZdlx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
    
	}
	//站点类型
function updateZdlx(res){
// alert("444");
	var shilist = document.all.stationtype;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
	
	
function changeSheng(){
	var sid = document.form1.sheng.value;

	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
	 //alert("11111");
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeCity(){
	var sid = document.form1.shi.value;

	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function changeCountry(){
	var sid = document.form1.xian.value;

	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

   	//站点属性
	function zdsx(){
	var sid = document.form1.jzproperty.value;
	if(sid=="0"){
		var shilist = document.all.stationtype;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=zdsx&pid="+sid,"jzproperty");
	}
	
}

   function xsl(){
  // 02xsbl线损比例  01xstz 线损调整 
   		var xsll=document.form1.linelosstype.value;
   		var bl=document.getElementById("xs");
   		var blnr="线损值（%）：";
   		var tz="线损值（度）：";
   		if(xsll=="02xsbl"){
   		 bl.innerHTML=blnr;
   		}else if(xsll=="01xstz"){
   		bl.innerHTML=tz;
   		}
   }


function kzinfo(){
	
}

		function showxuni(){

			if(document.form1.xuni.checked){
				document.getElementById("IDXUNI").style.display="block";
			}else{
				document.getElementById("IDXUNI").style.display="none";
			}
		}
		function liulan(){
		//window.open("zhandianselect.jsp");
		//return;
		var url=path+"/web/jizhan/zhandianselect.jsp"
		//window.open(url);
		//return;
			var obj = new Object();
			obj.mid='mid';
		    var obj=showModalDialog(url,obj,'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no');	
			document.form1.czzdid.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.czzd.value=obj.substring(0,obj.indexOf(","));
			/*
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_area.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_jzlx.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_bgsign.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_bieming.value=obj.substring(obj);
			*/
	}
//-->
</script>
</body>
</html>


