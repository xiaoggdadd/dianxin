<%@ page language="java" import="java.util.*,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");//账户
	String loginId = account.getAccountId();
	String loginName = (String)session.getAttribute("loginName");
	String xian = (String)request.getAttribute("xian");
	String bzw = (String)request.getAttribute("bzw");
	int numcolor = request.getAttribute("numcolor")!=null?(Integer)request.getAttribute("numcolor"):0;
	String color = "";
%>
<html>
  <head>
   <title>外租站点与主站点关联市级详细查询</title>
   <style type="text/css">
   .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
    }
    .relativeTag   {     
	z-index:5;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop); 
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px    
}; 
</style>
   <link type="text/css" rel="Stylesheet" href="<%=path%>/web/appCSS/pageCss/page.css" />
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js"></script>
   <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
   <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
   <script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
   <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
   <script type="text/javascript">
   var path = '<%=path%>';
   
 function selectTree(command){//查询
	  	var xian = <%=xian%>;
        var bzw ="<%=bzw%>";
	 	document.form1.action = path + "/servlet/TownRelationServlet?command="+command+"&xian="+xian+"&bzw="+bzw;
		document.form1.submit();
}
 
$(function(){
	$("#daochuBtn").click(function(){
	   selectTree("daochuxx");
	});
});
   
   </script>
  </head>
  <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
  	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
  <body>
   <form action="" name="form1" method="POST">
			<table>
				<tr>
					<td height="23" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;详细信息&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
			</table>
   <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
       <table width="100%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">       
         	<tr  class="relativeTag">
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" ></div></td>
             <td colspan="3"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >外租回收站点</div></td>
             <td colspan="3"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >主站点</div></td>
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" ></div></td>
         	</tr>
         	<tr class="relativeTag">
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >城市</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >站点名称</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >站点ID</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >站点类型</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >主站点名称</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >主站点ID</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >站点类型</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >关联ID号</div></td>
         	</tr>
         	<c:forEach items="${list}"  var="bean" varStatus="status">
			<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       			<td><div align="center" >${bean.city}</div></td> 
       			<td><div align="center" >${bean.zdname}</div></td> 
       			<td><div align="center" >${bean.id}</div></td>
       			<td><div align="center" >${bean.zdlx}</div></td>
       			<td><div align="center" >${bean.jzname}</div></td> 
       			<td><div align="center" >${bean.zdid}</div></td>
       			<td><div align="center" >${bean.stationtype}</div></td>
       			<td><div align="center" >${bean.xmh}</div></td>        			       			
			</tr>
			</c:forEach>
					<% 
						if (numcolor==0){
							for(int i=0;i<13;i++){
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
            		</tr>
      				<% 
							}
						}else if(!(numcolor > 12)){
							 for(int i=((numcolor-1)%12);i<12;i++){
						            if(i%2==0)
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
        			</tr>
        			<% 
        					}
						}
					%>
		</table>
	</div>
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" align="right" height="5%">
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
