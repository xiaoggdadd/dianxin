<%@ page language="java" import="java.util.*,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean,com.ptac.app.statisticcollect.city.addsitequantity.bean.*,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
//----管理员登录默认选中第一个，区县人员默认选中区县
Account account = (Account)session.getAttribute("account");//账户
String sheng = (String)session.getAttribute("accountSheng");//省
String agcode1="";
if(request.getParameter("shi")==null){
	List shilist = new ArrayList();
	CommonBean commBean = new CommonBean();
	shilist = commBean.getAgcode(sheng,account.getAccountId());
	if(shilist!=null){
		int scount = ((Integer)shilist.get(0)).intValue();
    	agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
 	}
} 
String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
//站点属性
String zdsx = request.getParameter("zdsx")!=null?request.getParameter("zdsx"):"0";
//站点启用状态
String qyzt = request.getParameter("qyzt")!=null?request.getParameter("qyzt"):"1";
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
%>
<html>
  <head>
   <title>新增站点数量(地市)</title>
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
   function lookDetailss() {//新增站点数量超链接，查看详情
	var shi = "${shi}";
	var beginyue = "${beginyue}";
	var endyue = "${endyue}";
	var zdsx = "${zdsx}";
	var qyzt = "${qyzt}";
	window.open(path+"/web/query/basequery/stationPointQuery.jsp?command=chaxun&shi="+shi+"&beginyue="+beginyue+"&endyue="+endyue+"&jzproperty="+zdsx+"&caiji=0&xuni=0&zdqyzt="+qyzt+"&manualauditstatus=1&sbzw=2");
}
   
   
   function lookDetails2(){//新增站点对应新增电量，电费
	var shi = "${shi}";
	var beginyue = "${beginyue}";
	var endyue = "${endyue}";
	var zdsx = "${zdsx}";
	var qyzt = "${qyzt}";
	window.open(path+"/servlet/CityNewAddSiteQuantityServlet?command=eleandfeeschaxun&shi="+shi+"&beginyue="+beginyue+"&endyue="+endyue+"&jzproperty="+zdsx+"&qyzt="+qyzt);
   }
   
 function selectTree(command){//查询
  var shi = document.form1.shi.value;//市
  var beginyue = document.form1.beginyue.value;//起始月
  var endyue  = document.form1.endyue.value;//结束月
  
if (shi == "" || shi == "0" || shi == null){
		alert("城市不能为空");
	}else if(beginyue == "" || beginyue == null){
		alert("起始月份不能为空");
	}else if(endyue == "" || endyue == null){
		alert("结束月份不能为空");
	}else{
		document.form1.action = path + "/servlet/CityNewAddSiteQuantityServlet?command="+command;
		document.form1.submit();
		if(command == "chaxun"){
			showdiv("请稍等..............");
		}
	}
}
 
      $(function(){
	$("#quary").click(function(){
	   selectTree("chaxun");
		});
		$("#daochuBtn").click(function(){
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
		<td colspan=1 width="900" background="<%=path%>/images/btt.bmp" height=50>
			<span style="color: black; font-weight: bold;">&nbsp;&nbsp;&nbsp;全省每月新增站点数量&nbsp;&nbsp;</span>
		</td>
		<td width='380'>
		</td>
	</tr>
	</table>
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="4"> <div style="width:50px;display:inline;"><hr></div><font size='2'>过滤条件</font><div style="width:300px;display:inline;"><hr></div></td>
	</tr>
	</table>
	<table width = "1200">
 	<tr class='form_label'>
 		<td>
 			城市：
 			<select name="shi" id="shi" style="width:130;height:10">
         		<option value="0">请选择</option>
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
         	<font color="red">&nbsp;*</font>
   		          月份：
   		    <input style="width:130;height:18" type="text" name="beginyue" value="${beginyue}" onpropertychange="changemonth()" readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/>
			至
			<input style="width:130;height:18" type="text" name="endyue" value="${endyue}" readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/>
   		    <font color="red">&nbsp;*</font>
   		          站点属性：
   		    <select name="zdsx" style="width:110">
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
	       	 站点启用状态：
	         <select name="qyzt" style="width:70">
	         <option value="-1">请选择</option>
	         <option value="1">是</option>
	         <option value="0">否</option>
	          </select>
   	   </td>
   	   <td>
   	   		 <div id="quary" style="position:relative;width:60px;height:23px;cursor: pointer;right:200px;float:right;">
		       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
		</div>
   	   </td>
   </tr>
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
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">区县</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点属性</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点总数</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">新增站点数</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">新增电费(/万元)</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">新增电量(/万度)</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电费为负值总和(/万元)</div></td>
         	</tr>
         
         	<c:forEach items="${beanlist}"  var="bean" varStatus="status">
			<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       			<td><div align="left" >${bean.xian}</div></td>
       			<td><div align="left" >${bean.zdsx}</div></td> 
       			<td><div align="right" >${bean.num}</div></td> 
       			<td><div align="right" >${bean.addnum}</div></td>
       			<td><div align="right" >${bean.addfee}</div></td>
       			<td><div align="right" >${bean.addelectric}</div></td>  
       			<td><div align="right" >${bean.feeweifu}</div></td> 		
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
        		<td bgcolor="#DDDDDD"></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sitesum}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px"><a href="javascript:lookDetailss()">${newaddnum}</a></div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px"><a href="javascript:lookDetails2()">${addfeesum}</a></div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${addelectricsum}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${addfeefusum}</div></td>
        	</tr>
		</table>
	</div>
	<table width="60%"  border="0" cellspacing="0" cellpadding="0" align="right" height="5%">
  				<tr align="right">
                	<td align="right" height="19" colspan="4">
                		<div id="parent" style="display:inline">
                    		<div style="width:50px;display:inline;"><hr></div>
                    		<div style="width:300px;display:inline;"><hr></div>
                      	</div>
                    </td>
                </tr>
			  	<tr>
			   		<td align="right">                                                                  
                    	<div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
	 						<img src="<%=path%>/images/daochu.png" width="100%" height="100%">
							<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
						</div>
                   	</td>
  				</tr>
			</table>
	</form>
  </body>
  <script type="text/javascript">
  document.form1.shi.value='<%=shi%>';
  document.form1.zdsx.value='<%=zdsx%>';
    document.form1.qyzt.value='<%=qyzt%>';
  </script>
</html>
