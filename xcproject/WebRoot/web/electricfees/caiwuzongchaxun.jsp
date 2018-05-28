<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@page import="com.noki.electricfees.javabean.*"%>
<%
	String sheng = (String)session.getAttribute("accountSheng");
	String roleId = (String)session.getAttribute("accountRole");
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
    String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
    String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";//站点名称
   	String bzyf = request.getParameter("bzyf")!=null?request.getParameter("bzyf"):"";//报账月份
   	String datetime=new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
   	if(null==bzyf||"null".equals(bzyf)||"".equals(bzyf)){
   		bzyf = datetime;
   	}
   
  	String kyiji = request.getParameter("yiji")!=null?request.getParameter("yiji"):"0";//会计科目一级
   	String kerji = request.getParameter("erji")!=null?request.getParameter("erji"):"0";//会计科目二级
   	String ksanji = request.getParameter("sanji")!=null?request.getParameter("sanji"):"0";//会计科目三级
   	String ksiji = request.getParameter("siji")!=null?request.getParameter("siji"):"0";//会计科目四级
   	String qyiji = request.getParameter("qcbyiji")!=null?request.getParameter("qcbyiji"):"0";//全成本一级科目
   	String qerji = request.getParameter("qcberji")!=null?request.getParameter("qcberji"):"0";//全成本二级科目
   	String qsanji = request.getParameter("qcbsanji")!=null?request.getParameter("qcbsanji"):"0";//全成本三级科目
   	String sszy = request.getParameter("sszy")!=null?request.getParameter("sszy"):"0";//所属专业
   	String zymx= request.getParameter("zymx")!=null?request.getParameter("zymx"):"0";//专业明细
 
    String whereStr="";
    String path = request.getContextPath();
    String loginName = (String)session.getAttribute("loginName");
   	Account account = (Account)session.getAttribute("account");         
    String permissionRights="";         
%>

<html>
<head>
<title>
监测点列表
</title>
<style>
         .style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}

 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}

.bttcn{color:BLACK;font-weight:bold;}

.form    {width:130px}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
 
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
  <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
 <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script type="text/javascript">
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
</script>
<script language="javascript">
var path = '<%=path%>';


function waitThenDoIt(){
   if (window.document.readyState){//IE
      if (window.document.readyState=='complete'){ 
      //初始化页面
        getSelOpen(0);
        getDictList(0);
      }else{
        setTimeout("waitThenDoIt()",10);
      }
  	 }else {//Firefox
       window.addEventListener("load",function(){ getSelOpen(0);getDictList(0);},false);
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
 function getPublicPath(){
		   var path = document.location.pathname;
			var arraypath=path.split("/");
			var mapath="/";
		 	if(arraypath[1]==undefined){
			 arraypath[1]="";
			 mapath="";
		 	}
		  path=mapath+arraypath[1];
		  return path;
	} 
  function chaxun(){
  var shi=document.form1.shi.value;
  var xian=document.form1.xian.value;
  var xiaoqu=document.form1.xiaoqu.value;
  var zdname=document.form1.zdname.value;
  var bzyf=document.form1.bzyf.value;//报账月份
  var yiji=document.form1.yiji.value;//会计科目一级
  var erji=document.form1.erji.value;//会计科目二级
  var sanji=document.form1.sanji.value;//会计科目三级
  var siji=document.form1.siji.value;//会计科目四级
  var qcbyiji=document.form1.qcbyiji.value;//全成本一级科目
  var qcberji=document.form1.qcberji.value;//全成本二级科目
  var qcbsanji=document.form1.qcbsanji.value;//全成本三级科目
  var zymx=document.form1.zymx.value;//  专业明细
  var shzt=document.form1.manauditstatus.value;// 审核状态
  var sszy=document.form1.sszy.value;// 所属专业
  var zdsx1=document.form1.zdsx1.value;// 
  var zdlx=document.form1.zdlx.value;// 
  
	    var href = "caiwuzongchaxun_iframe1.jsp?shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&zdname="+zdname+"&zdlx="+zdlx+"&zdsx1="+zdsx1+"&bzw=2&bzyf="+bzyf+"&yiji="+yiji+"&erji="+erji+"&sanji="+sanji+"&siji="+siji+"&qcbyiji="+qcbyiji+"&qcberji="+qcberji+"&qcbsanji="+qcbsanji+"&shzt="+shzt+"&zymx="+zymx+"&sszy="+sszy;
	    var str = "<a id='aa' href='"+href+"' target='test'></a>";
	    $("#aa").remove();
	    $("body").append(str);
	    $("#aa")[0].click();
	    
	    var href1 = "caiwuzongchaxun_iframeMap.jsp?shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&zdname="+zdname+"&zdlx="+zdlx+"&zdsx1="+zdsx1+"&bzw=2&bzyf="+bzyf+"&yiji="+yiji+"&erji="+erji+"&sanji="+sanji+"&siji="+siji+"&qcbyiji="+qcbyiji+"&qcberji="+qcberji+"&qcbsanji="+qcbsanji+"&shzt="+shzt+"&zymx="+zymx+"&sszy="+sszy;
	    var str = "<a id='aa' href='"+href1+"' target='treeNodeInfo1'></a>";
	    $("#aa").remove();
	    $("body").append(str);
	    $("#aa")[0].click();

	}
	
	
	//全成本下拉框检索
function getDictList(type){
  	var Url = getPublicPath()+"/servlet/Quanchengben?action=selOption1";
	var params ="&key=value";
	if (type==1){
	  params ="&fqcbcode="+ document.getElementById("qcbyiji").value;
	}else if (type==2){
	  params ="&fqcbcode="+ document.getElementById("qcberji").value;
	}else if (type==3){
	  params ="&fqcbcode="+ document.getElementById("qcbsanji").value;
	}else{
	  params ="&fqcbcode=0";
	}
    new Ajax.Request(Url,{
    	method : 'post',
	asynchronous: false,
	parameters : params,
	onComplete : function (resp){
		var result=resp.responseText;//返回数据
	    var msg = result.split("|");
		if ("msg"==msg[0]){
		  alert(msg[1]);
		  return;
		}
		var dataType="";
		if (type==1){
		  dataType = document.getElementById("qcberji");
		  RemoveItems(dataType);
		}else if (type==0){
		  dataType = document.getElementById("qcbyiji");
		  RemoveItems(dataType);
		}else if (type==2){
		  dataType = document.getElementById("qcbsanji");
		  RemoveItems(dataType);
		}
		var rowDate = result.split(",");//有多少行
		if (rowDate!=""){
			  for(var i=0;i<rowDate.length;i++){		     
					var cellDate = rowDate[i].split("|");//有多少列
	 	      		  //下拉框
		 			 var op =document.createElement("option");
	 			 	 op.innerHTML = cellDate[1];
	 			 	 op.value = cellDate[0];
	 			 	 dataType.appendChild(op);
	           }
		  }
	  }
    }
  );
} 
	//会计科目下拉框检索
function getSelOpen(type){

  	var Url = getPublicPath()+"/servlet/FinanceSubject?action=selOption1";
	var params ="&key=value";
	if (type==1){
	  params ="&fkmcode="+ document.getElementById("yiji").value;
	}else if (type==2){
	  params ="&fkmcode="+ document.getElementById("erji").value;
	}else if (type==3){
	  params ="&fkmcode="+ document.getElementById("sanji").value;
	}else if (type==4){
	  params ="&fkmcode="+ document.getElementById("siji").value;
	}else{
	  params ="&fkmcode=0";
	}
	
    new Ajax.Request(Url,{
    	method : 'post',
	asynchronous: false,
	parameters : params,
	onComplete : function (resp){
		var result=resp.responseText;//返回数据
	    var msg = result.split("|");
	   
		if ("msg"==msg[0]){
		  alert(msg[1]);
		  return;
		}
		
		var dataType="";
		
		if (type==1){
		  dataType = document.getElementById("erji");
		  RemoveItems(dataType);
		}else if (type==0){
		  dataType = document.getElementById("yiji");
		 RemoveItems(dataType);
		   
		}else if (type==2){
		  dataType = document.getElementById("sanji");
		  RemoveItems(dataType);
		}else if (type==3){
		  dataType = document.getElementById("siji");
		  RemoveItems(dataType);
		}
		
		var rowDate = result.split(",");//有多少行
		if (rowDate!=""){
			  for(var i=0;i<rowDate.length;i++){		     
					var cellDate = rowDate[i].split("|");//有多少列
	 	      		  //下拉框
		 			 var op =document.createElement("option");
	 			 	 op.innerHTML = cellDate[1];
	 			 	 op.value = cellDate[0];
	 			 	 dataType.appendChild(op);
	           }
		  }
	  }
    }
  );
} 
//移除选项
function RemoveItems(obj){
	for(var i=obj.options.length;i>=1;i--){
	   obj.options.remove(i);
    }
}
  $(function(){
		
		$("#query").click(function(){
			chaxun();
		});
		
	});
</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
        permissionRights=commBean.getPermissionRight(roleId,"0101");
        System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<body  class="body" style="overflow-x:hidden;" onload="waitThenDoIt()">
	<LINK href="../../images/css.css" type=text/css rel=stylesheet>
	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			
			<tr>
				<td colspan="11" width="50" >
                <div style="width:700px;height:50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">财务报账查询</span>
	              </div></td>
			</tr>
			
			<tr>
				<td colspan="11">
				
                       <div id="parent" style="display:inline">
                          </div><div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                    
                      </td>
            </tr>
			
        <tr class="form_label">
         	<td class="form_label">市：</td>
         	<td class="form_label"><select name="shi" id="shi" style="width:130" onchange="changeShi()" class="selected_font">
         		<option value="0">请选择</option>
         		<%
		         	ArrayList shilist = new ArrayList();
		         	shilist = commBean.getAgcode(sheng,account.getAccountId());
		         	if(shilist!=null){
		         		String agcode="",agname="";
		         		int scount = ((Integer)shilist.get(0)).intValue();
		         		for(int i=scount;i<shilist.size()-1;i+=scount)
	                    {
	                    	agcode=(String)shilist.get(i+shilist.indexOf("AGCODE"));
	                    	agname=(String)shilist.get(i+shilist.indexOf("AGNAME"));
	                    %>
	                    <option value="<%=agcode%>"><%=agname%></option>
	                    <%}
		         	}
		         %>
         	</td>
				     <td class="form_label">站点类型：</td>
         
         <td class="form_label">
         	<select name="zdlx"  onchange="kzinfo()" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList stationtype = new ArrayList();
         		stationtype = ztcommon.getSelctOptions("StationType");
	         	if(stationtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)stationtype.get(0)).intValue();
	         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
                    {
                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         </td>
         <td class="form_label">站点属性：</td>
         	<td class="form_label">
         	<select name="zdsx1" style="width:130" onchange="kzinfo()" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = ztcommon.getSelctOptions("zdsx");
	         	if(zdsx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdsx.get(0)).intValue();
	         		for(int i=cscount;i<zdsx.size()-1;i+=cscount)
                    {
                    	code=(String)zdsx.get(i+zdsx.indexOf("CODE"));
                    	name=(String)zdsx.get(i+zdsx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
         	%>
         	</select></td>
           <td class="form_label">报账月份</td>
	       <td class="form_label"> 
	       		<input type="text" name="bzyf" value="<%=bzyf%>"  class="selected_font"
	       				readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />   </td>
	       <td>	
			 <p><font size="2">
				<div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;left:-50px;cursor: pointer;top:10PX">
					<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
						<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		        </div>
							
				</font>
			 </p>
		 </td>
         <td>
            <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:-35px;;TOP:0PX">
		       <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
		         <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		    </div>
		</td>   
      </tr>
    <tr>
     <td colspan="11">
       <div style="width:99%;" > 
		<p id="box3" style="display:none">
		 <table>
     		<tr>
      		
     	     <td class="form_label"> 会计科目一级:</td>  
			<td class="form_label"><select name="yiji" id="yiji" class="selected_font" onchange="getSelOpen(1)">
				<option value="0">请选择</option>
			</select></td>  
			<td class="form_label"> 会计科目二级：</td>  
			<td class="form_label"><select name="erji" id="erji" class="selected_font" "onchange="getSelOpen(2)">
					<option value="0">请选择</option>
			</select></td>  
			<td class="form_label"> 会计科目三级：</td>  
			<td class="form_label"><select name="sanji" id="sanji" class="selected_font" onchange="getSelOpen(3)">
					<option value="0">请选择</option>
			</select></td>  
			<td class="form_label"> 会计科目四级：</td>  
			<td class="form_label"><select name="siji" id="siji" class="selected_font" onchange="getSelOpen(4)">
					<option value="0">请选择</option>
			</select></td> 
     </tr> 
     <tr>      
       <td class="form_label">全成本一级科目:</td>
	   <td class="form_label"> 
	    <select name="qcbyiji" id="qcbyiji" class="selected_font" onchange="getDictList(1)">
			<option value="0">请选择</option>
	    </select>
	   </td>
	   <td class="form_label">全成本二级科目：</td>
	   <td class="form_label"> 
	    <select name="qcberji" id="qcberji" class="selected_font" onchange="getDictList(2)">
			<option value="0">请选择</option>
		</select></td>
	   <td class="form_label"> 全成本三级科目：</td>
	   <td class="form_label">
	     <select name="qcbsanji" id="qcbsanji" class="selected_font" onchange="getDictList(3)">
	 		<option value="0">请选择</option>
		 </select>
	   </td>
	      
	       		<td class="form_label"> 站点名称：</td>
           		<td class="form_label"><input type="text" name="zdname" value="<%if (null != request.getParameter("zdname")) out.print(request.getParameter("zdname"));%>" class="selected_font"/></td>
	     </tr> 
	     <tr>
	     	<td class="form_label"> 所属专业:</td>  
			<td class="form_label"><select name="sszy" id="sszy" class="selected_font" onchange="getSelOpen(1)">
				<option value="0">请选择</option>
				<%
	         	ArrayList sszylist = new ArrayList();
	         	sszylist = ztcommon.getSelctOptions("SSZY");
	         	if(sszylist!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)sszylist.get(0)).intValue();
	         		for(int i=cscount;i<sszylist.size()-1;i+=cscount)
                    {
                    	code=(String)sszylist.get(i+sszylist.indexOf("CODE"));
                    	name=(String)sszylist.get(i+sszylist.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
			</select></td>
	     	<td class="form_label"> 专业明细：</td>
	   <td class="form_label">
	     <select name="zymx" id="zymx" class="selected_font">
	 		<option value="0">请选择</option>
	 			<%
	         	ArrayList zymxlist = new ArrayList();
	         	zymxlist = ztcommon.getSelctOptions("ZYMX");
	         	if(zymxlist!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zymxlist.get(0)).intValue();
	         		for(int i=cscount;i<zymxlist.size()-1;i+=cscount)
                    {
                    	code=(String)zymxlist.get(i+zymxlist.indexOf("CODE"));
                    	name=(String)zymxlist.get(i+zymxlist.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
	 		
		 	</select>
	   			</td>
	   			<td class="form_label">  审核状态：</td>            
         	    <td><select name="manauditstatus" class="selected_font" onchange="javascript:document.form1.manauditstatus.value=document.form1.manauditstatus.value">
         		    <option value="3">请选择</option>
         		    <option value="1">人工通过</option>
         		    <option value="2">财务通过</option>
         		    <option value="0">人工未通过</option>
         		    <option value="-1">财务未通过</option>
         	      </select>
         	   </td> 
	     </tr> 
	     <tr>
         <td class="form_label">区、县：</td>
         	<td class="form_label"><select name="xian" id="xian" style="width:130" onchange="changeXian()" class="selected_font" >
         		<option value="0">请选择</option>
         		<%
		         	ArrayList xianlist = new ArrayList();
		         	xianlist = commBean.getAgcode(shi,account.getAccountId());
		         	if(xianlist!=null){
		         		String agcode="",agname="";
		         		int scount = ((Integer)xianlist.get(0)).intValue();
		         		for(int i=scount;i<xianlist.size()-1;i+=scount)
	                    {
	                    	agcode=(String)xianlist.get(i+xianlist.indexOf("AGCODE"));
	                    	agname=(String)xianlist.get(i+xianlist.indexOf("AGNAME"));
	                    %>
	                    <option value="<%=agcode%>"><%=agname%></option>
	                    <%}
		         	}
		         %>
         	</select></td>
         	<td class="form_label">乡镇：</td>
         	<td class="form_label"><select name="xiaoqu" id="xiaoqu" style="width:130" class="selected_font">
         		<option value="0">请选择</option>
         		<%
		         	ArrayList xiaoqulist = new ArrayList();
		         	xiaoqulist = commBean.getAgcode(xian,account.getAccountId());
		         	if(xiaoqulist!=null){
		         		String agcode="",agname="";
		         		int scount = ((Integer)xiaoqulist.get(0)).intValue();
		         		for(int i=scount;i<xiaoqulist.size()-1;i+=scount)
	                    {
	                    	agcode=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGCODE"));
	                    	agname=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGNAME"));
	                    %>
	                    <option value="<%=agcode%>"><%=agname%></option>
	                    <%}
		         	}
		         %>
         	</select></td>
	     </tr> 
   				</table>
   			</p>
   		</div>
   	</td>
   </tr>
  
  </table>
 
  <table>
                 <tr><td height="5"  colspan="4"  class="form_label">
	                  <div id="parent" style="display:inline">
                          </div><div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      
             </td></tr>
 </table>
 </form>           
  	<iframe name="test" background-position-x ="1%" name="treeMap" width="100%" height="180px" frameborder="0" src="<%=path %>/web/electricfees/caiwuzongchaxun_iframe1.jsp"></iframe>
    <br/>
    <iframe name="treeNodeInfo1"  background-position-x ="52%" width="40%" height="370px" frameborder="0" src="<%=path %>/web/electricfees/caiwuzongchaxun_iframeMap.jsp"></iframe>
  	<iframe name="treeNodeInfo"  background-position-x ="52%" width="60%" height="360px" frameborder="0" src="<%=path %>/web/electricfees/caiwuzongchaxun_iframe2.jsp"></iframe>
</body>
</html>

<script language="javascript">
	
     var path = '<%=path%>';
    function changeShi(){
			var shi = document.form1.shi.value;
			if(shi=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选项","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=shi&pid="+shi,"shi");
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
		function changeXian(){
			var shi = document.form1.xian.value;
			if(shi=="0"){
				var shilist = document.all.xiaoqu;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=xian&pid="+shi,"xian");
			}
		}
		
		function updateXQ(res){
			var shilist = document.all.xiaoqu;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		//document.form1.bzyf.value='<%=bzyf%>';
		document.form1.yiji.value='<%=kyiji%>';
		document.form1.erji.value='<%=kerji%>';
		document.form1.sanji.value='<%=ksanji%>';
		document.form1.siji.value='<%=ksiji%>';
		document.form1.qcbyiji.value='<%=qyiji%>';
		document.form1.qcberji.value='<%=qerji%>';
		document.form1.qcbsanji.value='<%=qsanji%>';
		document.form1.sszy.value='<%=sszy%>';
		document.form1.zymx.value='<%=zymx%>';
	
</script>
