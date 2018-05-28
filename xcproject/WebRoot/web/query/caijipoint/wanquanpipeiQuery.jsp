<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet,java.util.List"%>
<%@ page import="java.text.*"%>
<%@page import="com.noki.query.basequery.javabean.StationPointQueryBean,com.noki.mobi.common.CommonBean"%>

<%

  java.text.SimpleDateFormat fordate=new java.text.SimpleDateFormat("yyyy-MM");
  java.util.Date currentTime=new java.util.Date();
  String crym=fordate.format(currentTime);
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	int intnum=0;
	String color="";
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        String loginName = account.getAccountName();
        String loginId1 = request.getParameter("loginId");
        String sheng = (String)session.getAttribute("accountSheng");
        String agcode1="";
    
		String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
		String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
		//String startmonth=request.getParameter("startmonth")!=null?request.getParameter("xiaoqu"):"";
		//String bzyf=request.getParameter("bzyf")!=null?request.getParameter("bzyf"):"";
		String bl=request.getParameter("bl")!=null?request.getParameter("bl"):"10";
		String bzmonth=request.getParameter("bzmonth")!=null?request.getParameter("bzmonth"):crym;
		String yf=request.getParameter("yf")!=null?request.getParameter("yf"):"2";
		String sjyf=request.getParameter("sjyf")!=null?request.getParameter("sjyf"):crym;
		String dbyt=request.getParameter("dbyt")!=null?request.getParameter("dbyt"):"3";
		
         String canshuStr="";
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
   //  String yf=request.getParameter("yf")!=null?request.getParameter("yf"):"3";
    
    
%>

<html>
<head>
<title>

</title>
<style>
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
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
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
#div1{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div1 p{ float:left;font-size:12px; width:110px; cursor:pointer;}
#box,#box2,#box3,#box4{padding:0px;border:1px;}
.bttcn{color:BLACK;font-weight:bold;}
 </style>
<style type="text/css" media=print>.noprint{display : none }
a {text-decoration:none}
</style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>

 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
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

  function chaxun(){
			var beginTime = document.form1.beginTime.value
           	if(checkNotnull(document.form1.beginTime,"开始时间")&&checkNotnull(document.form1.endTime,"结束时间")){
		 			document.form1.action=path+"/web/sys/logManage.jsp?beginTime="+beginTime
		            document.form1.submit()
        	}
	}
	function delLogs(){
		var beginTime = document.form1.delBeginTime.value
                var endTime = document.form1.delEndTime.value

                if(checkNotnull(document.form1.delBeginTime,"开始时间")&&
                   checkNotnull(document.form1.delEndTime,"结束时间")){
                      if(beginTime>endTime){
                 	alert("开始时间不能大于结束时间！")
                         return
               		 }
                      if(confirm("确定要删除，不可恢复！")){
                 	  document.form1.action=path+"/servlet/log?delBeginTime="+beginTime+"&delEndTime="+endTime
                           document.form1.submit()
               		 }
                }

	}
	
		function payfee(degreeid){
          document.form1.action=path+"/web/dianliang/payElectricFees.jsp?degreeid="+degreeid;
                                document.form1.submit();
        }
	function adddegree(){
          document.form1.action=path+"/web/dianliang/addDegree.jsp";
                                document.form1.submit();
        }
    function delad(ammeterdegreeid){
       if(confirm("您确定删除此电量信息？")){
                    document.form1.action=path+"/servlet/ammeterdegree?action=del&degreeid="+ammeterdegreeid
        			document.form1.submit()
       }
    }
    function modifyad(ammeterdegreeid){
                    document.form1.action=path+"/web/dianliang/modifyDegree.jsp?degreeid="+ammeterdegreeid;
                    document.form1.submit();
       
    }
    function queryDegree(){
                     
                    if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null)
		          {
	                   alert("城市不能为空");
	   
	           }else{
		           	var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
		   			var et = document.form1.entryTime1.value;
					if(reg1.exec(et)||et==""||et==null){
	                   document.form1.action=path+"/web/query/basequery/stationPointQuery.jsp?command=chaxun";
	                   document.form1.submit();
	                }else{
		        		alert("您输入的录入月份有误，请确认后重新输入！");
		        	}
               }
       
    }
    function getValue(va,sql){
       var general =document.getElementById("general");
       var htmlsql =document.getElementById("htmlsql");
       general.value=va;
       htmlsql.value = sql;    
    }  
    function CloseDiv(){
	closediv();
}
    	function selectTree(){
    	
    	var bl=document.form1.bl.value;
    	var y=document.form1.yf.value;
    	var b=document.form1.dbyt.value;
    		if(isNaN(bl)==false&&bl!=null&&bl!=""){
    		 
    	if(("3"==y&&"3"==b)||(("2"==y&&"3"==b)||("2"==y&&"2"==b))){
			var a = "<a href='<%=path %>/web/query/caijipoint/collectQueryleft1.jsp?";
			$("select").each(function(){
				a += $(this).attr("name")+"="+$(this).val()+"&";
			});
			$("input").each(function(){
				a += $(this).attr("name")+"="+$(this).val()+"&";
			});
			a = a.substring(0,a.length-1);
			a += "' target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();
		     showdiv("请稍等..............");
			}else{
			alert("月份为报账月份时 电表用途必须为结算电表！");
			}
			}else{
			alert("超标比例必须是数字！");
			}
		}		
   $(function(){

	$("#query").click(function(){
		queryDegree();
	});
	$("#query2").click(function(){
	   selectTree();
		});
	$("#daochuBtn").click(function(){
		exportad();
	});
	$("#dayinBtn").click(function(){
		printad();
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
<%
permissionRights=commBean.getPermissionRight(roleId,"0501");

%>
<body  class="body">
		<form action="" name="form1" method="POST">
			<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			        <tr>
					    	<td colspan="4" width="50" >
							<div style="width:700px;height:50px"><span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">超标基站管理</span>												</div>					  </td>
	    	        </tr>	    	
	    	  <tr>
	    	         <td height="20" colspan="4" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	    </td>
	    	</tr>
			<tr>
				<td width="1200px">
				<table>
					<tr class="form_label">
					<td>标准月份：</td>
					<td><input type="text" name="bzmonth" class="selected_font"  value=<%=crym%> 
								readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/></td>	
						<td>超标比例：</td>
						<td><input class="selected_font" type="text"name="bl" value="10"/>%
						<font color="red">*</font>
						</td>
		    		
	         		<td>月份：</td>
					<td><select name="yf" class="selected_font">
					<option value="2">开始月份</option>
	         		<option value="3">报账月份</option>
	         		</select></td>
	         		<td><input type="text" name="sjyf" class="selected_font"  value="<%=crym%>" 
	         					readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/></td>
					 <td>电表用途：</td> 
	         	<td><select name="dbyt" class="selected_font">
	         	<option value="3">结算电表</option>
	         	<option value="2">管理电表</option>
	         		</select></td>
	         		<td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
							</p>
					</td>
					<td>
					<div id="query2" style="position:relative;width:59px;height:23px;cursor:pointer; float: left; left: 20px;">
						 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
						 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
				    </div> 
				</td>     		
					</table></td>
					</tr>
					<tr>
					     <td colspan="5">
							<div style="width:90%;" > 
							  <p id="box3" style="display:none">
								<table>
								<tr class="form_label">
									<td >城市：</td>		    		
						    		<td ><select name="shi" class="selected_font" onchange="changeCity()">
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
					         		</select></td>
					         		<td>区县：</td><td> <select name="xian" class="selected_font" onchange="changeCountry()">
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
								</tr>
								</table>
							 </p>
							</div>
						</td>
				   </tr>
					</table>			       
  <table  height="23">
               <tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>
  <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
  <input type=hidden value="true" name="processBarDisplayFlag" />
</form>
		<iframe name="treeMap" width="600px" height="200px" frameborder="0" src="<%=path%>/web/query/caijipoint/collectQueryleft1.jsp"></iframe>
  	<span style="width:20px"></span>
	<iframe name="treeNodeInfo" width="500px" height="200px" frameborder="0" src="<%=path%>/web/query/caijipoint/collectQueryright1.jsp"></iframe>
    <br/>
   <iframe name="treeMap1" width="800px" height="400px" frameborder="0" src="<%=path%>/web/query/caijipoint/collectQueryMap.jsp"></iframe>                          
                <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                      
                    <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:20px;top:-300px; " >
				 		<img src="<%=path %>/images/daoru.png" width="100%" height="100%">
			 			<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
					</div>
                  <%} %>
	</body>
	<script language="javascript">
	var path = '<%=path%>';
	function gopage()
     {
      document.form1.submit();
     }
     function previouspage()
     {
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage-1)%>';
      		document.form1.action=path+"/web/query/basequery/stationPointQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/query/basequery/stationPointQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/query/basequery/stationPointQuery.jsp?curpage="+pageno+"<%=canshuStr%>";
      document.form1.submit();
     }
     </script>
     

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
	function sendRequest(url,para) {

		createXMLHttpRequest();
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="sheng"){
			XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
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

function changeSheng(){
	var sid = document.form1.sheng.value;
	document.form1.sheng2.value=document.form1.sheng.value;
	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
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
	document.form1.shi2.value=document.form1.shi.value;
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
	document.form1.xian2.value=document.form1.xian.value;
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

//-->
</script>
<script type="text/javascript">
	function exportad(){
			var shi=document.form1.shi.value; 
			var xian=document.form1.xian.value;  
			var bl=document.form1.bl.value; 
			var bzmonth=document.form1.bzmonth.value; 
			var yf=document.form1.yf.value; 
			var sjyf=document.form1.sjyf.value;   
			var dbyt=document.form1.dbyt.value; 
        	document.form1.action=path+"/web/query/caijipoint/超标基站管理信息.jsp?shi="+shi+"&xian="+xian+"&bl="+bl+"&bzmonth="+bzmonth+"&yf="+yf+"&sjyf="+sjyf+"&dbyt="+dbyt;
            document.form1.submit();
   }
</script>
<script type="text/javascript">
window.onload=function()
{
	var oDiv=document.getElementById('div1');
	var oUl=oDiv.getElementsByTagName('ul')[0];
	var oP=oDiv.getElementsByTagName('p')[0];
	var bSwitch=false;
	
	oP.onclick=function()
	{
		if(bSwitch){
			oUl.style.display='none';
			bSwitch=false;
		}else{
			var obj=document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
			var zdlx2 = document.form1.zdlx1.value;
			if(zdlx2!=""&&zdlx2!=null){
				var zdlx3 = zdlx2.split(",");
				for(var i=0;i<obj.length;i++){
					var m = obj[i].value.toString().indexOf(",");
					var bm = obj[i].value.toString().substring(0,m);
					for(var j=0;j<zdlx3.length;j++){
						var zdlx4 = zdlx3[j].toString().substring(1,zdlx3[j].length-1);
						if(bm==zdlx4){
							obj[i].checked = true; 					
						}
					} 
		 
				} 
			} 
			oUl.style.display='block';
			bSwitch=true;
		}
	};

};
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

</script>
<script type="text/javascript">
        document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		//document.form1.startmonth.value=';
		document.form1.bl.value='<%=bl%>';
		document.form1.bzmonth.value='<%=bzmonth%>';
		document.form1.yf.value='<%=yf%>';
		document.form1.dbyt.value='<%=dbyt%>';
		document.form1.sjyf.value='<%=sjyf%>';
		//document.form1.bzyf.value=';
 </script>
</html>
