<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.Double" %>
<%@ page import="com.noki.database.DataBase" %>
<%@ page import="java.sql.ResultSet" %>
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
	String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增报账信息</title>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/PopupCalendar.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/PopupCalendar_ny.js"></script>

<script type="text/javascript">
var path = '<%=path%>';
	
         
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="diaobiaoBean" scope="page" class="com.noki.jizhan.DianBiaoBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
 <jsp:useBean id="flowBean" scope="page" class="com.noki.mobi.flow.javabean.FlowBean">
    </jsp:useBean>
<body>
<ul class="tab">
			<li class="first"></li>
			<li class="cur">
				报账信息
				</a>
			</li>
			<li>
				<a href="todo.jsp?taskId=<%=id%>">审核</a>
			</li>
			<li class="end"></li>
		</ul>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
	
		<td width="12" ><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">站点报账信息</div>
				<div class="content01_1">
					<table style="width: 100%;"  border="0" id="tableStr"  cellpadding="0" cellspacing="0" class="tb_select" >
					  <thead>
					 <tr>
					 	<td style="width: 13%;"><span style="color: #FF0000; font-weight: bold">*</span>主电表</td>
					 	<td style="width: 18%;">成本中心</td>
					 	<td style="width: 5%;"><span style="color: #FF0000; font-weight: bold">*</span>开始日期</td>
					 	<td style="width: 5%;"><span style="color: #FF0000; font-weight: bold">*</span>结束日期</td>
					 	<td style="width: 5%;"><span style="color: #FF0000; font-weight: bold">*</span>上期读数</td>
					 	<td style="width: 5%;"><span style="color: #FF0000; font-weight: bold">*</span>本期读数</td>
					 	<td style="width: 3%;">倍率</td>
					 	<td style="width: 5%;">电量</td>
					 	<td style="width: 5%;">总金额</td>
					 	<td style="width: 5%;">其中电损</td>
					 	<td style="width: 5%;">金额</td>
					 	<td style="width: 5%;">税额</td>
					 	<td style="width: 3%;">税率</td>
					 	<td style="width: 5%;">上期单价</td>
					 	<td style="width: 5%;">单价</td>
					 	<td style="width: 5%;">分摊系数</td>
					 </tr>
					  <tr>
					 	<td><select name="DIANBIAOID" style="box-sizing:border-box;width:130px;" onchange="changeDianbiao()">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList dianbiaolist = new ArrayList();
					         		dianbiaolist = diaobiaoBean.getDianbiao(); 
						         	if(dianbiaolist!=null){
						         		String dbid_se="",dbname_se="";
						         		int scount_se = ((Integer)dianbiaolist.get(0)).intValue();
						         		for(int j=scount_se;j<dianbiaolist.size()-1;j+=scount_se)
					                    {
						         			dbid_se=(String)dianbiaolist.get(j+dianbiaolist.indexOf("ID"));
						         			
						         			dbname_se=(String)dianbiaolist.get(j+dianbiaolist.indexOf("DBNAME"));
					                    %>
					                    <option value="<%=dbid_se%>"><%=dbname_se%></option>
					                    <%}
						         	}
						         %>
					         	</select></td>
					 	<td></td>
					 	<td>	<input type="text" id="STARTTIME"  style="box-sizing:border-box;width:90px" class="Wdate"  name="STARTTIME" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" value="" />
									</td>
					 	<td><input type="text"  style="box-sizing:border-box;width:90px" class="Wdate" name="ENDTIME" id="ENDTIME" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" value="" />
								</td>
					 	<td><input type="text" name="SQDS" id="SQDS" onchange="changeSJ()" value="" style="box-sizing:border-box;width:60px" title="必填项"/>	
							</td>
					 	<td><input type="text" name="BQDS" id="BQDS" value="" onchange="changeSJ()" style="box-sizing:border-box;width:60px" title="必填项"/>	
							</td>
					 	<td>	<input type="text" name="BEILV"  id="BEILV" value="" style="box-sizing:border-box;width:30px" />	
							</td>
					 	<td><input type="text" name="DIANLIANG" id="DIANLIANG" onchange="changeSJ()" disabled="disabled" value="" style="box-sizing:border-box;width:60px" />	
							</td>
					 	<td><input type="text" name="ALLMONEY" id="ALLMONEY" disabled="disabled" value="" style="box-sizing:border-box;width:60px" title="必填项"/>
							</td>
					 	<td><input type="text" name="DIANSUN" id="DIANSUN" value="" style="box-sizing:border-box;width:60px" />
							</td>
					 	<td><input type="text" name="MONEY" id="MONEY" value="" style="box-sizing:border-box;width:60px" />
							</td>
					 	<td><input type="text" name="TAX" id="TAX" value="" style="box-sizing:border-box;width:60px" />
							</td>
					 	<td><input type="text" name="SHUILV" id="SHUILV" disabled="disabled"  value="" style="box-sizing:border-box;width:30px" title="必填项" maxlength="30"/>
					 	</td>
					 	<td><input type="text" name="SQDJ" id="SQDJ" value="" style="box-sizing:border-box;width:30px" title="必填项"/>	
							</td>
					 	<td><input type="text" name="PRICE" id="PRICE" onchange="changeSJ()" value="" style="box-sizing:border-box;width:30px" title="必填项" maxlength="30"/>
							</td>
					 	<td><input type="text" name="FTXS" id="FTXS" value="" style="box-sizing:border-box;width:30px" />
							</td>
					 </tr>
					   </thead>
					 <tbody></tbody>
					 <tfoot>
					 
								<tr>
									<td align="right" colspan="16" height="60px">
										<input onclick="saveBz()" type="button" class="btn_c1" id="button2" value="提交" />
									</td>
								</tr>
								</tfoot>			
					</table>
				</div>
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
	shilist.add(new Option("请选择","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
	
	
function changeSheng(){
	var sid = document.form1.sheng.value;

	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
	 //alert("11111");
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeCity(){
	var sid = document.form1.shi.value;

	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}

function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function changeCountry(){
	var sid = document.form1.xian.value;

	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	
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
		shilist.add(new Option("请选择","0"));
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
		var url=path+"/web/jizhan/zhandianselect.jsp";
		//window.open(url);
		//return;
			var obj = new Object();
			obj.mid='mid';
		    var obj=showModalDialog(url,obj,'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no');	
			document.form1.czzdid.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.czzd.value=obj.substring(0,obj.indexOf(","));
	}
		
		function saveBz(){	
			if (
          			checkNotSelected(document.form1.DIANBIAOID,"主电表")&&
          			checkNotnull(document.form1.STARTTIME,"开始日期")&&
          			checkNotnull(document.form1.ENDTIME,"结束日期")&&
          			checkNotnull(document.form1.SQDS,"上期读数")&&
          			checkNotnull(document.form1.BQDS,"本期读数")&&
          			checkNotnull(document.form1.SQDJ,"上期单价")&&
          			checkNotnull(document.form1.PRICE,"单价")
           	 ) {
           	 	var SQDS_check = $("#SQDS").val();
			
           	 	var BQDS_check = $("#BQDS").val();
           	 	var BEILV_check = $("#BEILV").val();
           	 	var DIANLIANG_check = $("#DIANLIANG").val();
           	 	var ALLMONEY_check = $("#ALLMONEY").val();
           	 	var DIANSUN_check = $("#DIANSUN").val();
           	 	var MONEY_check = $("#MONEY").val();
           	 	var TAX_check = $("#TAX").val();
           	 	var SQDJ_check = $("#SQDJ").val();
           	 	var PRICE_check = $("#PRICE").val();
           	 	var FTXS_check = $("#FTXS").val();
           	 	
           	 	var beginTime = $("#STARTTIME").val();
    			var endTime   = $("#ENDTIME").val();
		     	var arys1= new Array();      
		   		var arys2= new Array();      
 				if(beginTime != null && endTime != null) {      
	     			arys1=beginTime.split('-');      
	     		 	var sdate=new Date(arys1[0],parseInt(arys1[1]-1),arys1[2]);      
	    			arys2=endTime.split('-');      
	   				var edate=new Date(arys2[0],parseInt(arys2[1]-1),arys2[2]);    
	      			if(sdate > edate){
	      				alert("结束时间不能小于开始时间！");
	           	 		return false;
	      			}
   				 }

           	 	if(SQDS_check!=null && SQDS_check!="" && !ismoney(SQDS_check)){
           	 		alert("上期读数必须为数字,最多两位小数！");
           	 		return false;
           	 	}
           	 	if(BQDS_check!=null && BQDS_check!="" && !ismoney(BQDS_check)){
           	 		alert("本期读数必须为数字,最多两位小数！");
           	 		return false;
           	 	}
           	 	if(BQDS_check*1<SQDS_check*1){
           	 		alert("本期读数不能小于上期读数！");
           	 		return false;
           	 	}
           	 	if(BEILV_check!=null && BEILV_check!="" && !ismoney(BEILV_check)){
           	 		alert("倍率必须为整数！");
           	 		return false;
           	 	}
           	 	if(DIANLIANG_check!=null && DIANLIANG_check!="" && !ismoney(DIANLIANG_check)){
           	 		alert("电量必须为数字,最多两位小数！");
           	 		return false;
           	 	}
           	 	if(ALLMONEY_check!=null && ALLMONEY_check!="" && !ismoney(ALLMONEY_check)){
           	 		alert("总金额必须为金额！");
           	 		return false;
           	 	}
           	 	if(DIANSUN_check!=null && DIANSUN_check!="" && !ismoney(DIANSUN_check)){
           	 		alert("其中电损必须为数字,最多两位小数！");
           	 		return false;
           	 	}
           	 	if(MONEY_check!=null && MONEY_check!="" && !ismoney(MONEY_check)){
           	 		alert("金额必须为数字！");
           	 		return false;
           	 	}
           	 	if(TAX_check!=null && TAX_check!="" && !ismoney(TAX_check)){
           	 		alert("税额必须为金额！");
           	 		return false;
           	 	}
           	 	if(SQDJ_check!=null && SQDJ_check!="" && !ismoney(SQDJ_check)){
           	 		alert("上期单价必须为金额！");
           	 		return false;
           	 	}
           	 	if(PRICE_check!=null && PRICE_check!="" && !ismoney(PRICE_check)){
           	 		alert("单价必须为金额！");
           	 		return false;
           	 	}
           	 	if(PRICE_check!=null && PRICE_check!="" && PRICE_check.length>5){
           	 		alert("单价异常！");
           	 		return false;
           	 	}
           	 	if(FTXS_check!=null && FTXS_check!="" && !isNaN2(FTXS_check)){
           	 		alert("分摊系数必须为整数！");
           	 		return false;
           	 	}
           	 				       
           	 	var flowId=$("#flowId").val();
				if(flowId==0){
					alert("请选择流程！");
					return false;
				}
				var auditorid=$('input:radio[name="auditorid"]:checked').val();
			    if(typeof(auditorid) == "undefined"){
					alert("请选择执行人！");
					return false;
			    }
			    
		          addbaozhang();
			  };
           	 
  	 }
        	function addbaozhang(){
        		if(confirm("您将要添加报账！确认信息正确！")){
				$('#STARTTIME').attr("disabled",false);
				$('#SQDS').attr("disabled",false);
				$('#SQDJ').attr("disabled",false);
				$('#BEILV').attr("disabled",false);
				$('#PRICE').attr("disabled",false);
				$('#DIANLIANG').attr("disabled",false);
				$('#ALLMONEY').attr("disabled",false);
          		document.form1.action=path+"/servlet/zhandian?action=addBaozhang";
							document.form1.submit();
							 showdiv("请稍等..............");
            }
        	}
        		function isNaN2(val) { 
				return val.match(new RegExp("^[0-9]+$")); 
			} 
        function ismoney (str) {
			if (/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(str))
				return true;
			return false;
		}
			function isNa(val) { 
				return val.match(new RegExp("^\d+\.\d+$")); 
			}
function changeDianbiao(){
	var dianbiaoId = document.form1.DIANBIAOID.value;

	if(dianbiaoId=="0"){
	
		$("#STARTTIME").val("");
		$('#STARTTIME').attr("disabled",false);
		$("#SQDS").val("");
		$('#SQDS').attr("disabled",false);
		$("#SQDJ").val("");
		$('#SQDJ').attr("disabled",false);
		$("#BEILV").val("");
		$('#BEILV').attr("disabled",false);
		$("#PRICE").val("");
		$('#PRICE').attr("disabled",false);
		$("#SHUILV").val(0);
		
		return;
	}else{
		
		 $.ajax({
                type: "POST",//请求方式
                url: path+"/servlet/zhandian?action=getDianbiaoById",
                data: "dianbiaoId="+dianbiaoId,
                dataType: "json",
                scriptCharset: 'utf-8',
				success: function(result){
			// alert(result);
			 if(result!=null && result.length>0){
				var dianbiaoRe = result.split("*_*")[0];
			 	var baozhangRe = result.split("*_*")[1];
			 	var chengbenRe = result.split("*_*")[2];
			 	alert("result:"+result);
			 //	alert(dianbiaoRe)
			 //	alert(baozhangRe)
			 	if(dianbiaoRe!=null && dianbiaoRe.length>0){
			 		var  beilv_dianbiao = dianbiaoRe.split("@_@")[0];
					var  dianjia_dianbiao = dianbiaoRe.split("@_@")[1];
					var  shuilv_dianbiao = dianbiaoRe.split("@_@")[2];
					$("#BEILV").val(beilv_dianbiao);
					if(beilv_dianbiao!=null && beilv_dianbiao!=""){
							$('#BEILV').attr("disabled",true);
					}else{
						$('#BEILV').attr("disabled",false);
					}
					$("#PRICE").val(dianjia_dianbiao);
					if(dianjia_dianbiao!=null && dianjia_dianbiao!=""){
							$('#PRICE').attr("disabled",true);
					}else{
						$('#PRICE').attr("disabled",false);
					}
					
					if(shuilv_dianbiao!=null && shuilv_dianbiao!=""){
							$("#SHUILV").val(shuilv_dianbiao);
					}else{
						$("#SHUILV").val(0);
					}
				
			 	}else{
			 		$("#BEILV").val("");
			 		$('#BEILV').attr("disabled",false);
					$("#PRICE").val("");
					$('#PRICE').attr("disabled",false);
					$("#SHUILV").val(0);
					
					
			 	}
			 	if(baozhangRe!=null && baozhangRe.length>0){
			 		if(baozhangRe.split("@_@")[0]!=null && baozhangRe.split("@_@")[0] !=""){
						
						$("#STARTTIME").val(baozhangRe.split("@_@")[0].substr(0, 10));
						$('#STARTTIME').attr("disabled",true);
			 		}else{
			 			$('#STARTTIME').attr("disabled",false);
			 		}
			 		
					$("#SQDS").val(baozhangRe.split("@_@")[1])
					if(baozhangRe.split("@_@")[1]!=null && baozhangRe.split("@_@")[1]!=""){
								$('#SQDS').attr("disabled",true);
					}else{
							$('#SQDS').attr("disabled",false);
					}
				
					$("#SQDJ").val(baozhangRe.split("@_@")[2])
					if(baozhangRe.split("@_@")[2]!=null && baozhangRe.split("@_@")[2]!=""){
								$('#SQDJ').attr("disabled",true);
					}else{
							$('#SQDJ').attr("disabled",false);
					}
					
			 	}else{
			 		$("#STARTTIME").val("");
			 		$("#STARTTIME2").val("");
			 		$('#STARTTIME').attr("disabled",false);
					$("#SQDS").val("");
					$('#SQDS').attr("disabled",false);
					$("#SQDJ").val("");
					$('#SQDJ').attr("disabled",false);
			 	}
					var htmlStr = "";
					$('#tableStr tbody').empty();
					alert("chengbenRe:"+chengbenRe);
				if(chengbenRe!=null && chengbenRe!=""  && chengbenRe.length>0){
					var arrcb = chengbenRe.split("@_@");
					alert("arrcb.length:"+arrcb.length);
					for(var v=0;v<arrcb.length;v++){
						var cb=arrcb[v];
				 		if(cb!=null && cb !=""){
							
							htmlStr += " <tr id='tr_"+cb.split('#_#')[1]+"'><td></td>" +
							"<td style='word-wrap:break-word;width: 100px;' id='mc_"+cb.split('#_#')[1]+"'>"+cb.split('#_#')[0]+"</td><td></td><td></td><td></td>" +
							"<td></td><td></td><td id='dl_"+cb.split('#_#')[1]+"'></td><td id='zje_"+cb.split('#_#')[1]+"'></td>" +
							"<td id='ds_"+cb.split('#_#')[1]+"'></td>" +
							"<td id='je_"+cb.split('#_#')[1]+"'></td><td id='se_"+cb.split('#_#')[1]+"'></td>" +
							"<td></td><td></td><td></td><td id='ftxs_"+cb.split('#_#')[1]+"'>"+cb.split('#_#')[1]+"</td></tr>"
				 		}
			 		}
					alert("html:"+htmlStr);
					$('#tableStr tbody').html(htmlStr);	
			 	}else{
			 		html="<tr><td cosplan='16'>数据错误或无核算单元！</td></tr>"
			 		$('#tableStr tbody').html(htmlStr);	
			 	}
				
			
			 }
		changeSJ();
                }
            });
	}
	
}
        	
function changeSJ(){
	var BQDS_ch = $("#BQDS").val();
	var SQDS_ch = $("#SQDS").val();
	var PRICE_ch = $("#PRICE").val();
//	alert("BQDS_ch:"+BQDS_ch+",SQDS_ch:"+SQDS_ch+",PRICE_ch:"+PRICE_ch);
	if(BQDS_ch!="" && SQDS_ch!=""){
		$("#DIANLIANG").val((BQDS_ch*1 - SQDS_ch*1).toFixed(2));
	}
	if(BQDS_ch!="" && SQDS_ch!="" && PRICE_ch!=""){
		$("#ALLMONEY").val(((BQDS_ch*1 - SQDS_ch*1)*(PRICE_ch*1)).toFixed(2));
	}
	
	
	
}

function select(){
        	$("#radioDiv").empty();
        	var flowId=$("#flowId").val();
        	var dbid=document.form1.DIANBIAOID.value ;
        	$.ajax({
	                type: "POST",//请求方式
	                url: path+"/servlet/workflow?action=getActionName",
	                data: {flowId:flowId},
	                dataType: "json",success: function(result){
				    	$("#nextActionName").html(result);
				    	$.ajax({
	                type: "POST",//请求方式
	                url: path+"/servlet/workflow?action=getAuditor",
	                data: {flowId:flowId,dbid:dbid},
	                dataType: "json",success: function(result){
	                	var nextActionName=$("#nextActionName").val();
				    	var  resultArr = eval(result);
				    	var html = "选择执行人：";
				    		if(resultArr!=null && resultArr.length>0){
				    			var scountArr = resultArr[0];
				    			var accountid="",name="";
				    			for (var i = scountArr; i < resultArr.length - 1; i += scountArr) {
															accountid = resultArr[i
																	+ resultArr.indexOf("ACCOUNTID")];
															name =  resultArr[i
																	+ resultArr.indexOf("NAME")];
															html+="<label><input type='radio' name='auditorid' value='"+accountid+"' />"+name+"</label>";
				    		}
				    		
				    	}
				    	$("#radioDiv").append(html);
				    	$("#tongyiTr").attr("style", "display:display;");
				    }
	            });
				    }
	            });
        	
        }

</script>
</body>
</html>
