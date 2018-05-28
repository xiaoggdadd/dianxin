<%@ page language="java" import="java.util.*,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
//----管理员登录默认选中第一个，区县人员默认选中区县
Account account = (Account)session.getAttribute("account");//账户

String loginName = (String)session.getAttribute("loginName");
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
   
   function lookDetails2(){//新增站点对应新增电量，电费导出
	var path = "<%=path%>"; 
	var shi = "${shi}";
	var beginyue = "${beginyue}";
	var endyue = "${endyue}";
	var zdsx = "${zdsx}";
	var qyzt = "${qyzt}";
	document.form1.action = path+"/servlet/CityNewAddSiteQuantityServlet?command=eleandfeesdaochu&shi="+shi+"&beginyue="+beginyue+"&endyue="+endyue+"&jzproperty="+zdsx+"&qyzt="+qyzt;
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
			<span style="color: black; font-weight: bold;">&nbsp;&nbsp;&nbsp;全省每月新增站点电量、电费&nbsp;&nbsp;</span>
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
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">报账月份</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">上次电表读数</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">本次电表读数</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">上次抄表时间</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">本次抄表时间</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">报账用电量</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">用电量调整</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电量调整备注</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">本次单价</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">报账电费</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电费调整</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电费调整备注</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">结算周期</div></td> 
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">倍率</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点属性</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点类型</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">流程号</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点ID</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电表ID</div></td>
         	</tr>
         
         	<c:forEach items="${beanlist}"  var="bean" varStatus="status">
			<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
			<td><div align="center" >${status.count}</div></td>
       			<td><div align="left" >${bean.shi}</div></td>
       			<td><div align="right" >${bean.xian}</div></td> 
       			<td><div align="right" >${bean.xiaoqu}</div></td> 
       			<td><div align="right" >${bean.jzname}</div></td>
       			<td><div align="right" >${bean.accountmonth}</div></td>
       			<td><div align="right" >${bean.lastdegree}</div></td>  
       			<td><div align="right" >${bean.thisdegree}</div></td> 
       			<td><div align="right" >${bean.lastdatetime}</div></td> 
       			<td><div align="right" >${bean.thisdatetime}</div></td> 
       			<td><div align="right" >${bean.blhdl}</div></td> 
       			<td><div align="right" >${bean.floatdegree}</div></td> 
       			<td><div align="right" >${bean.amemo}</div></td> 
       			<td><div align="right" >${bean.unitprice}</div></td> 
       			<td><div align="right" >${bean.actualpay}</div></td> 
       			<td><div align="right" >${bean.floatpay}</div></td> 
       			<td><div align="right" >${bean.ememo}</div></td> 
       			<td><div align="right" >${bean.jszq}</div></td> 
       			<td><div align="right" >${bean.beilv}</div></td> 
       			<td><div align="right" >${bean.property}</div></td> 
       			<td><div align="right" >${bean.stationtype}</div></td> 
       			<td><div align="right" >${bean.liuchenghao}</div></td> 
       			<td><div align="right" >${bean.zdid}</div></td> 
       			<td><div align="right" >${bean.dbid}</div></td> 
       					
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
						<td>&nbsp;</td>
						<td>&nbsp;</td>
        			</tr>
        			<% 
        					}
						}
					%>
			
			<%--<tr height="23">
				<td bgcolor="#DDDDDD">
					<div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">
					合计
					</div>
				</td>
        		<td bgcolor="#DDDDDD"></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sitesum}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px"><a href="javascript:lookDetailss()">${newaddnum}</a></div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${addfeesum}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${addelectricsum}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${addfeefusum}</div></td>
        	</tr>
		--%></table>
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
