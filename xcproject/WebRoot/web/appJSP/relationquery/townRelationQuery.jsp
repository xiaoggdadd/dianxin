<%@ page language="java" import="java.util.*,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	//----管理员登录默认选中第一个，区县人员默认选中区县
	Account account = (Account)session.getAttribute("account");//账户
	String loginId = account.getAccountId();
	String loginName = (String)session.getAttribute("loginName");
	String sheng = (String)session.getAttribute("accountSheng");//省
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
	
	int numcolor = request.getAttribute("numcolor")!=null?(Integer)request.getAttribute("numcolor"):0;
	String color = "";

%>
<html>
  <head>
   <title>外租站点与主站点关联市级查询</title>
   <style type="text/css">
   .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
    }
</style>
   <link type="text/css" rel="Stylesheet" href="<%=path%>/web/appCSS/pageCss/page.css" />
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js"></script>
   <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
   <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
   <script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
   <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
   <script type="text/javascript">
   var path = '<%=path%>';

function lookDetails2(xian,bzw){//详情
	window.open(path+"/servlet/TownRelationServlet?command=xiangqing&xian="+xian+"&bzw="+bzw);
}
   
 function selectTree(command){//查询
	 	
	 	document.form1.action = path + "/servlet/TownRelationServlet?command="+command;
		document.form1.submit();
		if(command == "chaxun"){
			showdiv("请稍等..............");
		}
}
 
$(function(){
	$("#chaxun").click(function(){
	   	if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
	   		alert("城市不能为空");
	   	}else{
			selectTree("chaxun");
	  	}
	});
	$("#daochuBtn").click(function(){
	   selectTree("daochu");
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
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
	 			<tr>
      				<td colspan="4" width="50">
						<div style="width:700px;height:50px">
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">外租站点与主站点关联市级查询</span>	
						</div>
					</td>
    			</tr>
				<tr>
					<td height="30" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;过滤条件&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="8%" width="1200">
						<table>
							<tr class="form_label">
								<td>
									城市：
								</td>
								<td>
									<select name="shi" class="selected_font" id="shi"
										onchange="changeCity()">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList shilist = new ArrayList();
											shilist = commBean.getAgcode(sheng, account.getAccountId());
											if (shilist != null) {
												String agcode = "", agname = "";
												int scount = ((Integer) shilist.get(0)).intValue();
												int size = shilist.size() - 1;
												for (int i = scount; i < size; i += scount) {
													agcode = (String) shilist
															.get(i + shilist.indexOf("AGCODE"));
													agname = (String) shilist
															.get(i + shilist.indexOf("AGNAME"));
										%>
										<option value="<%=agcode%>"><%=agname%></option>
										<%
											}
											}
										%>
									</select>
								</td>

								<td>

									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -240px; float: right; top: 0px">
										<img alt="" src="<%=path%>/images/chaxun.png" width="100%"
											height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td height="23" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;信息列表&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
			</table>
   <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
       <table width="100%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">       
         	<tr  >
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >序号</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >区县</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >外租回收站点数量</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >已关联主站点个数</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px" >未关联站点个数</div></td>
         	</tr>
         	<c:forEach items="${list}"  var="bean" varStatus="status">
			<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       			<td><div align="center" >${status.count}</div></td> 
       			<td><div align="center" >${bean.xian}</div></td> 
       			<td><div align="center" >${bean.wzhs}</div></td>
       			<td><div align="center" > <a href="javascript:lookDetails2('${bean.town}','1')">${bean.ygl}</a></div></td>
       			<td><div align="center" > <a href="javascript:lookDetails2('${bean.town}','2')">${bean.wgl}</a></div></td>        			       			
			</tr>
			</c:forEach>
					<% 
						if (numcolor==0){
							for(int i=0;i<17;i++){
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
							 for(int i=((numcolor-1)%16);i<16;i++){
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
  <script type="text/javascript">
  document.form1.shi.value='<%=shi%>';
  </script>
</html>
