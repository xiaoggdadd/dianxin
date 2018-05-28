<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,com.ptac.app.systemmanage.bean.LuRuLiangBean,java.util.List, com.ptac.app.systemmanage.action.LuRuLiangServlet" %>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.*"%>

<%
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String sheng = (String)session.getAttribute("accountSheng");
   	String agcode1="";
   	if(request.getParameter("shi")==null){
   		ArrayList shilist = new ArrayList();
   		CommonBean commBean = new CommonBean();
   		shilist = commBean.getAgcode(sheng,account.getAccountId());
		if(shilist!=null){
      		int scount = ((Integer)shilist.get(0)).intValue();
           agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
         }
   	}    
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
	String lx = request.getParameter("lx")!=null?request.getParameter("lx"):"dbyt02";//类型
	String datetime=new SimpleDateFormat("yyyy-mm-dd").format(Calendar.getInstance().getTime());
	Date date = new Date(); 
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String dateString = formatter.format(date);
	String startyear = request.getParameter("startyear")!=null?request.getParameter("startyear"):dateString;
	String endyear = request.getParameter("endyear")!=null?request.getParameter("endyear"):dateString;
   	if(null==startyear||"null".equals(startyear)||"".equals(startyear)){
   		startyear = datetime;
   	}
   	if(null==endyear||"null".equals(endyear)||"".equals(endyear)){
   		endyear = datetime;
   	}
    String roleId = (String)session.getAttribute("accountRole");
    //int intnum = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;
    String color = "";
%>
<html>
<head>
<style>   
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: right;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
.form_label1{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
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

.form1{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;
   height:19px;
   width:130px;
}

 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/FusionCharts.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
  <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script language="javascript">
var path = '<%=path%>';

function queryDegree(command){
    	
    	var startyear = document.form1.startyear.value;
    	var endyear = document.form1.endyear.value;
        var lx = document.form1.lx.value;
        if(""!=startyear && ""!=endyear && startyear<endyear){
        	document.form1.action=path+"/servlet/LuRuLiangServlet?command="+command;
	        document.form1.submit();
	        showdiv("请稍等..............");
       	}else if(startyear>endyear){
       		alert("起始年月日不得大于结束年月日！");
       	}
       	else {
       		alert("年月日不能为空！");
		}
}

function xiangxi(shi,startyear,endyear,lx) {//查看详情
	document.form1.attributes["action"].value = path
			+ "/servlet/LuRuLiangServlet?command=xiangqing&shi="+shi+"&startyear="
			+ startyear+"&endyear="+endyear+"&lx="+lx+"";
	document.form1.submit();
}

$(function(){
	$("#daochu").click(function(){
	   queryDegree("daochu");
	});
	
	$("#chaxun").click(function(){
		if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
	        alert("城市不能为空");
	   	}else{
			queryDegree("chaxun");
			
		}
	});
});
  </script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
<body  class="body" >	
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" >
	      <tr>
           <td width="50" >
             <div style="width:700px;height:50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">录入量</span>
	         </div>
	        </td>
		  </tr>
		  <tr>
		   <td height="20" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
            </td>
          </tr>

  		 <tr>
  		   <td width="1200px">
  		   <table>	
  		     <tr class="form_label">
               <td >城市：</td>      
                <td><select name="shi" class="selected_font" id="shi" >
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
			<td class="form_label">年月日：</td>
	       <td>
					<input class="selected_font" type="text" name="startyear"
							value="<%=startyear%>"
							readonly="readonly" class="Wdate"
							onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
					<span class="style1">&nbsp;*</span></td>   
	     		<td class="form_label">至</td>
	           <td>
					<input class="selected_font" type="text" name="endyear"
							value="<%=endyear%>"
							readonly="readonly" class="Wdate"
							onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
					<span class="style1">&nbsp;*</span></td>
           
				<td class="form_label"> 类型：</td>
	             <td class="form_label">
	             <select  name="lx" style="width:130">
	             	<option value="dbyt02">请选择</option>
	             	<option value="dbyt01">结算电量</option>
	             	<option value="dbyt03">管理电量</option>
	             </select>
	             </td>
	         	  <td>
                     <div id="chaxun" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
		                  <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                  <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
		             </div>
			      </td>
			    </table>
			 </td>
		</tr>
 </table>

  <table height="23">     
     <tr><td height="5"  colspan="4">
      <div id="parent" style="display:inline">
        <div style="width:50px;display:inline;"><hr></div>
        <font size="2">&nbsp;信息列表&nbsp;</font>
        <div style="width:300px;display:inline;"><hr></div>
      </div></td>
     </tr>
  </table>
<table border="0" align="center" width="97%">

  	<tr>
  		<td width="83%">  		
			<fieldset>
				<legend><b><font size="2">数据信息</font></b></legend>
	 			<table width="100%" align="center" CellSpacing="0" style="BORDER-COLLAPSE: collapse" borderColor=#eaeaea bgColor=#ffffff border=1>
		           <tr> 
		             <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">序号</div></td>
		             <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">年月日</div></td>
		             <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">录入量</div></td>
		         	</tr>
		         	<% int i=1; %>
		         	<c:forEach items="${list}"  var="list" varStatus="status">
					<tr>
		       			<td class="form_label"><DIV align=center><%=i++ %></DIV></td>
						<td class="form_label"><DIV align=center>${list.year}</DIV></td>
						<td class="form_label"><DIV align=center>${list.lcount}</DIV></td>
					</tr>
         			</c:forEach>
         			<tr>
         				<td  align="center" class="form_label"></td>
        				<td  align="center" ><font color="red">合计：</font></td>
        				<td  align="center" ><font color="red">${sum}</font></td>
       				 </tr>
			</table>
		</fieldset>
		<br/>
		<fieldset>
			<legend><b><font size="2"><a href="javascript:xiangxi('<%=shi%>','<%=startyear%>','<%=endyear%>','<%=lx%>')">点击查看图表信息</a></font></b></legend>
				<DIV id="chartdiv"></DIV>
				<script type="text/javascript">
						var chart = new FusionCharts("<%=path%>/javascript/Line.swf", "ChartId", "1000", "400", "0", "0");
						chart.setXMLData("${dataString}");		   
						chart.render("chartdiv");
				</script>
		</fieldset>
  	  </td>
   </tr>
</table>
	<div style="width:100%;height:8%;border:1px" >	
	   <table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
         <tr >
            <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
               <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
            </div></td>
          </tr>
          <tr>
             <td align="right"> 
                     <div id="daochu" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:9px; ">
		                 <img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
		                 <span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">导出</span>
					 </div>
             </td>
         </tr>
	  </table>
   </div>
</form>
</body>

<script type="text/javascript">
var path = '<%=path%>';
		

		
var XMLHttpReq;
//选择浏览器
function createXMLHttpRequest(){
	if(window.XMLHttpRequest){ 
		XMLHttpReq = new XMLHttpRequest();
	}else if (window.ActiveXObject){ 
		try{
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e){
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){
			}
		}
	}
}
</script>

<script type="text/javascript">
//站点属性
function zdsxCheck(obj) {
	var sid = obj;
	if (sid == "0") {
		var shilist = document.all.zdlx;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest2(path + "/servlet/garea?action=zdsx&pid=" + sid,
				"jzproperty");
	}
}

function sendRequest2(url, para) {

	createXMLHttpRequest();

	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数
	XMLHttpReq.send(null);
}

function processResponse_zdlx() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			updateZdlx(res);
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}

function updateZdlx(res) {

	var shilist = document.all.zdlx;
	shilist.options.length = "0";
	shilist.add(new Option("请选项", "0"));

	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
</script>
<script language="javascript">
   		document.form1.shi.value='<%=shi%>';
		document.form1.lx.value='<%=lx%>';
 </script>
</html>