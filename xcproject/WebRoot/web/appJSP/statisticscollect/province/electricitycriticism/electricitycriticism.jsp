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
	String color;//颜色
	int intnum = request.getAttribute("num") != null ? (Integer) request.getAttribute("num"): 0;//条数 ,查出数据的条数，用于颜色设置
	if (intnum % 2 == 0) {
		color = "#DDDDDD";
	} else {
		color = "#FFFFFF";
	}
String datetime = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
String bzyf = request.getParameter("bzyf")!=null?request.getParameter("bzyf"):datetime;
String dfshzt = request.getParameter("dfshzt")!=null?request.getParameter("dfshzt"):"2";
String cbbly = request.getParameter("cbbly")!=null?request.getParameter("cbbly"):"20";
String cbble = request.getParameter("cbble")!=null?request.getParameter("cbble"):"50";
String jzproperty = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
%>
<html>
  <head>
    <title>电价超标站点通报</title>
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

   
  /**
   function changemonth() {
	var bzmonth = document.form1.beginyue.value;
	document.form1.endyue.value = bzmonth;
}
 */  
    function selectTree(command){//查询
     var bzyf = document.form1.bzyf.value;//起始月
     
	if(bzyf == "" || bzyf == null){
		alert("报账月份不能为空");
	}else{
		document.form1.action = path + "/servlet/EleCriticism?action=elec&command="+command;
		document.form1.submit();
		
		if(command=="chaxun"){
		showdiv("请稍等..............");
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
  <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
  <body onload = "onloadtime()">
   <form action="" name="form1" method="POST">
     <table width="100%"  border="0" cellspacing="0" cellpadding="0">					
	<tr>
      				<td colspan="4" width="50">
						<div style="width:700px;height:50px">
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电价超标站点通报</span>	
						</div>
					</td>
   </tr>
	<tr>
		<td colspan="4"> <div style="width:50px;display:inline;"><hr></div><font size='2'>过滤条件</font><div style="width:300px;display:inline;"><hr></div></td>
	</tr>
	<tr>
  		<td width="1200px">
  		<table border = "0" width="100%" >
 	<tr class='form_label'>
 		<td > 报账月份：</td>
 	
         	
   		        
   		   <td > <input style="width:130;height:18" type="text" name="bzyf" value="<%=bzyf %>"  readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/></td>
   	  		<td > 电费审核状态：</td>
   	   		<td ><select name="dfshzt" class="selected_font">
         		<option value="2">财务审核通过</option>
         		<option value="1">业务审核通过</option>
        	</select></td>
        	  <td >      
        <div id="quary" style="position:relative;width:59px;height:23px;cursor: pointer; float: left; left: 20px;">
			<img alt="" src="<%=path%>/images/chaxun.png" width="100%" height="100%" />
			<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		</div> 
	</td>

								
		
   </tr>
   <tr  class='form_label'>
    <td>超标比例1：	</td>  
    <td ><input type="text" class="selected_font" name="cbbly" value="20"/>%</td>
    <td > 超标比例2：	  </td>
    <td ><input type="text" class="selected_font" name="cbble" value="50"/>%</td>

          	<td >站点属性</td>
 					
								<td >	<select name="jzproperty" class="selected_font">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList zdsx = new ArrayList();
											zdsx = ztcommon.getSelctOptions("ZDSX");
											if (zdsx != null) {
												String code = "", name = "";
												int cscount = ((Integer) zdsx.get(0)).intValue();
												for (int i = cscount; i < zdsx.size() - 1; i += cscount) {
													code = (String) zdsx.get(i + zdsx.indexOf("CODE"));
													name = (String) zdsx.get(i + zdsx.indexOf("NAME"));
										%>
										<option value="<%=code%>"><%=name%></option>
										<%
											}
											}
										%>
										</select></td>
   	</tr>
   	</table>
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
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">报账站点数（个）</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电量超标<%=cbbly%>%站点数（个）</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">超标站占总比</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电量超标<%=cbble%>%站点数（个）</div></td>
         	<td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">超标站占总比 </div></td>
         	
         	</tr>
         
         	<c:forEach items="${list}" var="bean" varStatus="status">
			<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
			    <td><div align="right" >${bean.shi}</div></td>
       			<td><div align="right" >${bean.bzzds}</div></td> 
       			<td><div align="right" >${bean.djcby}</div></td>
       			<td><div align="right" >${bean.cbzdzby}%</div></td>
       			<td><div align="right" >${bean.dbcbe}</div></td>
       			<td><div align="right" >${bean.cbzdzbe}%</div></td>
			</tr>
			</c:forEach>
								<% 
						int i = 0;
						int j = 0;//f用于循环
						if (intnum==0){
							
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
            		</tr>
      				<% 
							
						}else if(!(intnum > 16)){
    	  					int n = (intnum-1)%16;
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
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sumbean.bzzdshj}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sumbean.cbyhj}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sumbean.cbzbyhj}<c:if test="${sumbean.cbzbyhj != null}">%</c:if></div></td>
      			
				<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sumbean.cbehj}</div></td>
      			<td bgcolor="#DDDDDD"><div align="right" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">${sumbean.cbzbehj}<c:if test="${sumbean.cbzbehj != null}">%</c:if></div></td>
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
  document.form1.dfshzt.value='<%=dfshzt%>';
  document.form1.cbbly.value='<%=cbbly%>';
  document.form1.cbble.value='<%=cbble%>';
  document.form1.jzproperty.value='<%=jzproperty%>';
  document.form1.bzyf.value='<%=bzyf%>';
  </script>
</html>