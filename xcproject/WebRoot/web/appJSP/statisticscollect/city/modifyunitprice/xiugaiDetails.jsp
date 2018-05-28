<%@ page language="java" import="java.util.*,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();

//颜色
int numcolor = request.getAttribute("numcolor")!=null?(Integer)request.getAttribute("numcolor"):0;//条数 ,查出数据的条数，用于颜色设置
String color = "";//颜色
%>
<html>
  <head>
   <title>新增站点电量电费</title>
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
   
   function lookDetails2(){//修改单价详情
	var path = "<%=path%>"; 
	var command = "${command}";
	var shi = "${shi}";
	var xian = "${xian}";
	var beginyue = "${beginyue}";
	var endyue = "${endyue}";
	var dfshzt = "${dfshzt}";
	var zdqyzt = "${zdqyzt}";
	var dbqyzt = "${dbqyzt}";
	document.form1.action = path+"/servlet/ModifyUnitPriceServlet?command="+command+"daochu&shi="+shi+"&xian="+xian+"&beginyue="+beginyue+"&endyue="+endyue+"&dfshzt="+dfshzt+"&zdqyzt="+zdqyzt+"&dbqyzt="+dbqyzt;
	document.form1.submit();
   }
   
      $(function(){
	$("#daochuBtn").click(function(){
	   lookDetails2();
		});
});
   
   </script>
  </head>
  <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
  <body >
   <form action="" name="form1" method="POST">
     <table width="100%"  border="0" cellspacing="0" cellpadding="0">					
	<tr>
		<td colspan=1 width="900" background="<%=path%>/images/btt.bmp" height=50>
			<span style="color: black; font-weight: bold;">&nbsp;&nbsp;&nbsp;修改单价详情&nbsp;&nbsp;</span>
		</td>
		<td width='380'>
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
           <tr class="relativeTag">
           	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">序号</div></td>
           	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">城市</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">区县</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">乡镇</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点名称</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点属性</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点类型</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点ID</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电表ID</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">修改前单价</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">修改后单价</div></td>
         	</tr>
         
         	<c:forEach items="${beanlist}"  var="bean" varStatus="status">
			<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
			<td><div align="center" >${status.count}</div></td>
       			<td><div align="left" >${bean.shi}</div></td>
       			<td><div align="right" >${bean.xian}</div></td> 
       			<td><div align="right" >${bean.xiaoqu}</div></td> 
       			<td><div align="right" >${bean.jzname}</div></td>
       			<td><div align="right" >${bean.property}</div></td>
       			<td><div align="right" >${bean.stationtype}</div></td>
       			<td><div align="right" >${bean.zdid}</div></td>
       			<td><div align="right" >${bean.dbid}</div></td>
       			<td><div align="right" >${bean.previousunitprice}</div></td> 
       			<td><div align="right" >${bean.laterunitprice}</div></td> 
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
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
        			</tr>
        			<% 
        					}
						}
					%>
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
</html>
