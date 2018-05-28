<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean,com.noki.mobi.common.CommonBean,com.noki.mobi.common.Account"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@page import="com.noki.electricfees.javabean.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%
    
	String sheng = (String)session.getAttribute("accountSheng");
	String roleId = (String)session.getAttribute("accountRole");
	Account account1 = (Account)session.getAttribute("account");
	String agcode1="";
	if(request.getParameter("shi")==null){
   		ArrayList shilist = new ArrayList();
   		CommonBean commBean = new CommonBean();
   		shilist = commBean.getAgcode(sheng,account1.getAccountId());
		if(shilist!=null){
       		int scount = ((Integer)shilist.get(0)).intValue();
            agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
        }
   	}  
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
	
	
   // String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
    String fysx = request.getParameter("fysx")!=null?request.getParameter("fysx"):"0";//
    String sbzyf = request.getParameter("sbzyf")!=null?request.getParameter("sbzyf"):"";//报账月份
    String ebzyf = request.getParameter("ebzyf")!=null?request.getParameter("ebzyf"):"";//报账月份
	String sptype = request.getParameter("sptype")!=null?request.getParameter("sptype"):"0";//站点属性
	String xxft = request.getParameter("xxft")!=null?request.getParameter("xxft"):"0";//站点属性
	String fzyx = request.getParameter("fzyx")!=null?request.getParameter("fzyx"):"0";//
	
	String datetime=new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
   	if(null==sbzyf||"null".equals(sbzyf)||"".equals(sbzyf)){
   		sbzyf = datetime;
   	}
   	if(null==ebzyf||"null".equals(ebzyf)||"".equals(ebzyf)){
   		ebzyf = datetime;
   	}
   	
	  
	     String whereStr="";
        String path = request.getContextPath();
        String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
       String loginId = account.getAccountId();
        
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
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
  <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
 <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
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
<script>

var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();

var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChsny.oBtnTodayTitle = "确定";
oCalendarChsny.oBtnCancelTitle = "取消";
oCalendarChsny.Init();
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
  var fysx=document.form1.fysx.value;
  var sbzyf=document.form1.sbzyf.value;//报账月份
  var ebzyf=document.form1.ebzyf.value;//报账月份
  var zdsx=document.form1.sptype.value;//站点属性sptype
  var fzyx=document.form1.fzyx.value;//分专业线
  var xxft=document.form1.xxft.value;//详细分摊
  
  var bz = '1';
  
  
        //if(document.getElementById("shi").value=="0"||document.getElementById("shi").value==""){
	
	   ///}else{
	    
	   // document.form1.action=path+"/web/electricfees/zhandianfentanmx.jsp";
	   // document.form1.submit();
	   
	    var href = "fybzcountIfram.jsp?shi="+shi+"&xian="+xian+"&sbzyf="+sbzyf+"&ebzyf="+ebzyf+"&zdsx="+zdsx+"&xxft="+xxft+"&fzyx="+fzyx+"&bzw=2"+"&fysx="+fysx;
	     var str = "<a id='aa' href='"+href+"' target='test'></a>";
	    $("#aa").remove();
	    $("body").append(str);
	    $("#aa")[0].click();
	    
	   
	    
	   
		
	  //}
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
function CloseDiv(){
	closediv();
}


function RemoveItems(obj){
	for(var i=obj.options.length;i>=1;i--){
	   obj.options.remove(i);
    }
}
  $(function(){
		
		$("#query").click(function(){
		
			chaxun();
			showdiv("请稍等..............");
		});
		
	});
</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>

<jsp:useBean id="commBean1" scope="page"
		class="com.noki.mobi.common.CommonAccountBean">
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
				<td colspan="8" width="50" >
                <div style="width:700px;height:50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">费用及报账对照统计数据查询</span>
	              </div></td>
			</tr>
			
			<tr>
				<td colspan="8">
				
                       <div id="parent" style="display:inline">
                          </div><div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                    
                      </td>
            </tr>
		<tr>
		  <td colspan="11">
		    <table >
		
			
        <tr class="form_label">
         	
         	<td class="form_label">报账月份：</td>
	       <td class="form_label"> <input type="text" name="sbzyf" value="<%=sbzyf%>" onFocus="getDatenyString(this,oCalendarChsny)"  style="width: 130px;"/>   </td>   
	     		<td class="form_label">至</td>
	             <td class="form_label"> <input type="text" name="ebzyf" value="<%=ebzyf%>" onFocus="getDatenyString(this,oCalendarChsny)"  style="width: 130px;"/>   </td>   
	     		
	             <td class="form_label"> 一级分摊：</td>
	             <td class="form_label">
	             <select  name="fzyx" style="width:130">
	             	<option value="0">请选择</option>
	             	<option value="1">完成</option>
	             	<option value="2">未分</option>
	             	<option value="3">异常</option>
	             </select>
	             </td>
         	   <td class="form_label"> 详细分摊：</td>
	             <td class="form_label">
	             <select  name="xxft" style="width:130">
	             	<option value="0">请选择</option>
	             	<option value="1">完成</option>
	             	<option value="2">未分</option>
	             	<option value="3">异常</option>
	             </select>
	             </td>
           
	    	<td  class="form_label">	
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p>
					</td>
	         		<td >
							       <div id="query" style="position:relative;width:59px;height:23px;right:-250px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
							</td>  
        </tr>
       </table>
	  </td>
	</tr>
    <tr>
     <td colspan="11">
       <div style="width:99%;" > 
		<p id="box3" style="display:none">
		 <table>
	     <tr class="form_label">   
	     <td class="form_label">市：</td>
         	<td class="form_label"><select name="shi" id="shi" style="width:130" onchange="changeShi()" class="selected_font">
         		<option value="0">请选择</option>
         		<%
		         	ArrayList shilist = new ArrayList();
		         	shilist = commBean1.getShi(loginId);
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
         	</select>
         	</td>
         	<td class="form_label">区、县：</td>
         	<td class="form_label"><select name="xian" id="xian" style="width:130" onchange="changeXian()" class="selected_font" >
         		<option value="0">请选择</option>
         		<%
		         	ArrayList xianlist = new ArrayList();
		         	xianlist = commBean1.getAgcode(shi,account.getAccountId());
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
	        <td class="form_label">站点属性</td>
	     		<td>
	     		<select name="sptype" style="width:130" >
         		<option value="0">请选择</option>
	         		<%
	         		ArrayList sptypelist = new ArrayList();
	         		sptypelist = commBean.getZdxl();
	         		if(sptypelist!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)sptypelist.get(0)).intValue();
	         		for(int i=scount;i<sptypelist.size()-1;i+=scount)
                    {
                    	sfid=(String)sptypelist.get(i+sptypelist.indexOf("CODE"));
                    	sfnm=(String)sptypelist.get(i+sptypelist.indexOf("NAME"));
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	    }
	        		 %>
	             </select>
	             </td>
	             
	             <td class="form_label"> 费用属性：</td>
	             <td class="form_label">
	             <select  name="fysx" id="fysx" style="width:130">
	             	<option value="0">请选择</option>
	             	<option value="2">收入</option>
	             	<option value="1">支出</option>
	             </select>
	             </td>
	       		
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

   
  	<iframe name="test" background-position-x ="1%" name="treeMap" width="100%" height="180px" frameborder="0" src="<%=path %>/web/tongjichaxun/fybzcountIfram.jsp"></iframe>
    <br/>
   
  	<iframe name="treeNodeInfo"  background-position-x ="52%" width="100%" height="180px" frameborder="0" src="<%=path %>/web/tongjichaxun/fybzcountIfram2.jsp"></iframe>
  

  
 


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
		
		document.form1.shi.value='<%=shi%>';//sptype
		document.form1.sptype.value='<%=sptype%>';

	
</script>
