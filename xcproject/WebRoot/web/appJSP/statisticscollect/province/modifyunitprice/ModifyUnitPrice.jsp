<%@ page language="java" import="java.util.*,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath(); 
//----管理员登录默认选中第一个，地市人员默认选中地市
Account account = (Account)session.getAttribute("account");//账户
String sheng = (String)session.getAttribute("accountSheng");//省 
String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"";
String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"";
String loginName = (String)session.getAttribute("loginName");
//颜色
int numcolor = request.getAttribute("numcolor")!=null?(Integer)request.getAttribute("numcolor"):0;//条数 ,查出数据的条数，用于颜色设置
String color = "";//颜色
String datetime = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
String beginyue = (String)request.getAttribute("beginyue");
String endyue = (String)request.getAttribute("endyue");
if (null == beginyue || "null".equals(beginyue) || "".equals(beginyue)) {
	beginyue = datetime;
}
if (null == endyue || "null".equals(endyue) || "".equals(endyue)) {
	endyue = datetime;
}
String dfshzt = request.getParameter("dfshzt")!=null?request.getParameter("dfshzt"):"3";
String zdqyzt = request.getParameter("zdqyzt")!=null?request.getParameter("zdqyzt"):"1";
String dbqyzt = request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"1";
%>
<html>
  <head>
    <title>省级修改单价查询</title>
<style type="text/css">
   .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
    }
</style>
       <link type="text/css" rel="Stylesheet" href="<%=path%>/web/appCSS/pageCss/page.css" />
   <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
   <script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js"></script>
   <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
       <script type="text/javascript">
   var path = '<%=path%>';
   function onloadtime(){
		var date = new Date();
    	var vYear = date.getFullYear();
   	 	var vMon = date.getMonth() + 1;
   	 	if(vMon<10){
   	 		vMon="0"+vMon;
   	 	}
    	var today=vYear+"-"+vMon;
		var beginyue = "${beginyue}";
		var endyue = "${endyue}";
	   if(beginyue == null || beginyue ==""){
		   document.getElementById("beginyue").innerText = today;
	   }
   }
   
   function changemonth() {
	var bzmonth = document.form1.beginyue.value;
	document.form1.endyue.value = bzmonth;
}
   
    function selectTree(command){//查询
  var shi = document.form1.shi.value;//市
  var beginyue = document.form1.beginyue.value;//起始月
  var endyue  = document.form1.endyue.value;//结束月
  
	if(beginyue == "" || beginyue == null){
		alert("起始月份不能为空");
	}else if(endyue == "" || endyue == null){
		alert("结束月份不能为空");
	}else{
		if(command=="chaoxun"){
		document.form1.action = path + "/servlet/ProvinceModifyUnitPriceServlet?command="+command;
		document.form1.submit();
		showdiv("请稍等..............");
		}else{
			document.form1.action = path + "/servlet/ProvinceModifyUnitPriceServlet?command="+command;
			document.form1.submit();
		}
	}
}
 
   $(function(){
		$("#quary").click(function(){
	   		selectTree("chaxun");
		});
		$("#daochu").click(function(){
			selectTree("daochu");
		});
	});
 </script>
  </head>
  <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
  <body onload = "onloadtime()">
   <form action="" name="form1" method="POST">
     <table width="100%"  border="0" cellspacing="0" cellpadding="0">					
	<tr>
		<td colspan=1 width="700" background="<%=path%>/images/btt.bmp" height=50>
			<span style="color: black; font-weight: bold;">&nbsp;&nbsp;&nbsp;省级修改单价查询&nbsp;&nbsp;</span>
		</td>
		<td width='380'>
		</td>
	</tr>
	<tr>
		<td colspan="4"> <div style="width:50px;display:inline;"><hr></div><font size='2'>过滤条件</font><div style="width:300px;display:inline;"><hr></div></td>
	</tr>
</table>
<table border = "0" width="100%">
 	<tr class='form_label'>
 		<td >
 			&nbsp;&nbsp;&nbsp;城&nbsp;&nbsp;&nbsp;&nbsp;市：&nbsp;
 			<select name="shi" id="shi" class="selected_font" >
         		<option value="">全省</option>
         		<%
         			ArrayList shilist = new ArrayList();
         			shilist = commBean.getAgcode(sheng, shi, loginName);
         			if (shilist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) shilist.get(0)).intValue();
         				int size = shilist.size() - 1;
         				for (int i = scount; i < size; i += scount) {
         					agcode = (String) shilist.get(i + shilist.indexOf("AGCODE"));
         					agname = (String) shilist.get(i + shilist.indexOf("AGNAME"));
         		%>
               	<option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    }
                    %>
         	</select>
         	
   		         报&nbsp;账&nbsp;&nbsp;月&nbsp;份：
   		    <input style="width:130;height:18" type="text" name="beginyue" value="${beginyue}" onpropertychange="changemonth()" readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/>
			至
			<input style="width:130;height:18" type="text" name="endyue" value="${endyue}" readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/>
   	  		 电费审核状态：
   	   		<select name="dfshzt" class="selected_font">
         		<option value="0">市级审核通过</option>
         		<option value="1">人工审核通过</option>
         		<option value="2">财务审核通过</option>
         		<option value="3">市级主任审核通过</option>
        	</select>
		</td>
   </tr>
   <tr  class='form_label'>
    <td>

站点启用状态：	  
 <select name="zdqyzt" class="selected_font">
         		<option value="">&nbsp;&nbsp;&nbsp;&nbsp;全&nbsp;&nbsp;部</option>
         		<option value="1">&nbsp;&nbsp;&nbsp;&nbsp;启&nbsp;&nbsp;用</option>
         		<option value="0">&nbsp;&nbsp;&nbsp;&nbsp;关&nbsp;&nbsp;闭</option>
        </select>
        电表启用状态：	   
        <select name="dbqyzt" class="selected_font">
         		<option value="">&nbsp;&nbsp;&nbsp;&nbsp;全&nbsp;&nbsp;部</option>
         		<option value="1">&nbsp;&nbsp;&nbsp;&nbsp;启&nbsp;&nbsp;用</option>
         		<option value="0">&nbsp;&nbsp;&nbsp;&nbsp;关&nbsp;&nbsp;闭</option>
        </select>
        <div id="quary" style="position:relative;width:59px;height:23px;cursor: pointer;left:70%;margin:-23px 0px 0px 0px">
			<img alt="" src="<%=path%>/images/chaxun.png" width="100%" height="100%" />
			<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		</div> 
	</td>
   	</tr>
   	</table>
   	<table>
   <tr class='form_label'>
		<td><div id="parent" style="display:inline">
		<div style="width:50px;display:inline;"><hr></div>
		&nbsp;信息列表&nbsp;
		<div style="width:300px;display:inline;"><hr></div>
		</div>
		</td>
   </tr>
   </table>
   <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
       <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">       
           <tr class="relativeTag" >
           <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">城市</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电费单录入条数</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">修改单价条数</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">单价调高条数</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">调高占比</div></td>
         	</tr>
         
         	<c:forEach items="${beanlist}" var="bean" varStatus="status">
			<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
			<td><div align="right" >${bean.shi}</div></td>
       			<td><div align="right" >${bean.lrts}</div></td> 
       			<td><div align="right" >${bean.xgdjts}</div></td> 
       			<td><div align="right" >${bean.tgts}</div></td>
       			<td><div align="right" >${bean.tgzb}%</div></td>
			</tr>
			</c:forEach>
								<% 
						int i = 0;
						int j = 0;//f用于循环
						if (numcolor==0){
							for(i=0;i<17;i++){
          						if(i%2==0){
			    					color="#FFFFFF" ;
          						}else{
			    					color="#DDDDDD";
								}
         			%>

        			<tr bgcolor="<%=color%>">  
						
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
            		</tr>
      				<% 
							}
						}else if(!(numcolor > 16)){
    	  					int n = (numcolor-1)%16;
							for(j=n;j<16;j++){
            					if(j%2==0)
			    				color="#DDDDDD";
            					else
			    				color="#FFFFFF" ;
        			%>
        			<tr bgcolor="<%=color%>">
            		
            			<td>&nbsp;</td>                   
            			<td>&nbsp;</td>
            			<td>&nbsp;</td> 
            			<td>&nbsp;</td> 
            			<td>&nbsp;</td> 
        			</tr>
        			<% 
        					}
						}
					%>
			
			<tr height="23">
				<td bgcolor="#DDDDDD">
					<div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">
					合计
					</div>
				</td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sumbean.lrtssum}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sumbean.xgdjtssum}</div></td>
				<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sumbean.djtgtssum}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sumbean.tgzbsum}<c:if test="${sumbean.tgzbsum != null}">%</c:if></div></td>
        		<td bgcolor="#DDDDDD"></td>
        	</tr>
		</table>
	</div>
	<table  align="right">
		<tr>
			<td align="right">
				<div id="daochu" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
					<img src="<%=path%>/images/daochu.png" width="100%" height="100%"/>
					<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>				
				</div>
			</td>
		</tr>
		
		</table>
	</form>
  </body>
  <script type="text/javascript">
  <%--
     //改变城市
function changeCity() {
	var sid = document.form1.shi.value;
	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=shi&pid=" + sid, "shi");
	}
}--%>
  document.form1.shi.value='<%=shi%>';
  document.form1.dfshzt.value='<%=dfshzt%>';
  document.form1.zdqyzt.value='<%=zdqyzt%>';
  document.form1.dbqyzt.value='<%=dbqyzt%>';
  </script>
</html>