<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.noki.util.CTime" %>
<%@ page import="com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="com.ptac.app.priceover.citysend.dao.CitySendDaoImp" %>

<%	
	String path = request.getContextPath();
    String roleId = (String)session.getAttribute("accountRole");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
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
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;//市
	String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";//区县	
  	String property=request.getParameter("property")!=null?request.getParameter("property"):"";//站点属性
  	String state=request.getParameter("state")!=null?request.getParameter("state"):"0";//省级审核状态
  	String datetime = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
	String accountmonth = request.getParameter("accountmonth")!=null?request.getParameter("accountmonth"):datetime;//报账月份
	String beginbl = request.getParameter("beginbl")!=null?request.getParameter("beginbl"):"";//比例
	String endbl = request.getParameter("endbl")!=null?request.getParameter("endbl"):"";//比例
	String zdmc = request.getParameter("zdmc")!=null?request.getParameter("zdmc"):"";//站点名称
	String zdid = request.getParameter("zdid")!=null?request.getParameter("zdid"):"";//站点ID	
	String gdfs = request.getParameter("gdfs")!=null?request.getParameter("gdfs"):"0";//供电方式
	
    String permissionRights="";
    String color;//颜色
	int intnum = request.getAttribute("num") != null ? (Integer) request.getAttribute("num"): 0;//条数 ,查出数据的条数，用于颜色设置
	if (intnum % 2 == 0) {
		color = "#DDDDDD";
	} else {
		color = "#FFFFFF";
	}
%>

<html>
<head>
<title>

</title>
<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
.selected_font{
		width:90px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px ##888888 solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.bttcn{color:black;font-weight:bold;}
.form    {width:130px}

#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
 
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script language="javascript">
var path = '<%=path%>';

    function queryDegree(command){
    		var path = '<%=path%>';
			document.form1.action=path+"/servlet/ProAuditingServlet?command=" + command;
            document.form1.submit();
            if(command=="chaxun"){
            showdiv("请稍等..............");
            }
    }
    
     function tuidan(){
    	var m = document.getElementsByName('test[]');
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr = ""; 
       	
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       	}
	       	if(count!=0){
		       	if(count%240==0){
		       		n=count/240-1;
		       	}else{
		       		n=(count-(count%240))/240;
		       	}
		        for(var i = 0; i < l; i++){
		          if(m[i].checked == true){
		             bz+=1;
		             count1+=1;
		             chooseIdStr = chooseIdStr +"'" +m[i].value +"',"; 
		          }
		          if(count1<=240*n||((bz+count2)>=239&&(bz+count2)<=241)){
		         	  if(((bz+count2)/240==1)||((bz+count2)>=239&&(bz+count2)<=241)){
				        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
				       // document.form1.action=path+"/servlet/CityCheckServlet?command=tuidan&chooseIdStr="+chooseIdStr;
			           passnoCheck1(chooseIdStr);
			            chooseIdStr = ""; 
		       			bz=0;
		       			count2=0;
			          }
			       }else if(count==count1&&bzw==1){
			          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
				      bzw=0;
			          document.form1.action=path+"/servlet/ProAuditingServlet?command=tuidan&chooseIdStr="+chooseIdStr;
			          document.form1.submit(); 
			          showdiv("请稍等..............");
			       }            
		        } 
	        }else{
	          alert("请选择信息！");
	        }
    }
     
     function tongguo(){
         
    	var m = document.getElementsByName('test[]');
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr = ""; 
       	
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       	}
	       	if(count!=0){
		       	if(count%240==0){
		       		n=count/240-1;
		       	}else{
		       		n=(count-(count%240))/240;
		       	}
		        for(var i = 0; i < l; i++){
		          if(m[i].checked == true){
		             bz+=1;
		             count1+=1;
		             chooseIdStr = chooseIdStr +"'" +m[i].value +"',"; 
		          }
		          if(count1<=240*n||((bz+count2)>=239&&(bz+count2)<=241)){
		         	  if(((bz+count2)/240==1)||((bz+count2)>=239&&(bz+count2)<=241)){
				        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
				       // document.form1.action=path+"/servlet/CityCheckServlet?command=tongguo&chooseIdStr="+chooseIdStr;
				       alert(222);
				        passCheck1(chooseIdStr);
			            chooseIdStr = ""; 
		       			bz=0;
		       			count2=0;
			          }
			       }else if(count==count1&&bzw==1){
			          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
				      bzw=0;
			          document.form1.action=path+"/servlet/ProAuditingServlet?command=tongguo&chooseIdStr="+chooseIdStr;
			          document.form1.submit(); 
			          showdiv("请稍等..............");
			       }            
		        } 
	        }else{
	          alert("请选择信息！");
	        }
    }
    
    $(function(){
		$("#chaxun").click(function(){
			queryDegree("chaxun");
			showdiv("请稍等..........");
		});
		$("#daochu").click(function(){
			queryDegree("daochu");
		});
		$("#tuidan").click(function(){
		 	tuidan();
		});
		$("#tongguo").click(function(){
		 	tongguo();
		});
	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0804");
%>

<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr><td colspan="4">
			<div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">省审核</span>	
			 </div>
	    	</td></tr>	    	
	    	<tr><td height="19" colspan="4">
   				<div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td></tr>
	    	<tr><td height="8%" width="1200">
	    		<table>
		    		<tr class="form_label">
		    		<td>城市：</td><td><select name="shi" class="selected_font" onchange="changeCity()">
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
		                    <option value="<%=agcode%>" selected="selected"><%=agname%></option>
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
	         		<td>站点属性：
								</td>
								<td>
									<select name="property" style="width: 130"
										class="selected_font">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList zdsx = new ArrayList();
											zdsx = ztcommon.getSelctOptions("zdsx");
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
									</select>
								</td>
  						<td>省审核状态:</td>
						<td><select name="state"  class="selected_font">
         							<option value="-1">请选择</option>
         							<option value="0">未审核</option>
         							<option value="1">结单</option>
         							<option value="2">退单</option>
         					</select>
         				</td>
         				<td>报账月份：</td>
                  		<td>
                     		<input type="text" name="accountmonth" class="selected_font" value="" 
                     			   readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
                  		</td>
					</tr>
					<tr class="form_label">
	 					<td>站点名称：</td>
	         			<td><input type="text" name="zdmc" value="" class="selected_font"/></td>
	         			<td>站点ID：</td>
	        		 	<td><input type="text" name="zdid" value="" class="selected_font"/></td>
	        		 	<td>供电方式：</td>
						<td >
					    	<select name="gdfs" style="width:130" class="selected_font" title="必填项，根据下拉值选择填写">
					       		<option value="0">请选择</option>
					         		<%
						         	ArrayList gdfs1 = new ArrayList();
						         	gdfs1 = ztcommon.getSelctOptions("GDFS");
						         	if(gdfs1!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)gdfs1.get(0)).intValue();
						         		for(int i=cscount;i<gdfs1.size()-1;i+=cscount)
					                    {
					                    	code=(String)gdfs1.get(i+gdfs1.indexOf("CODE"));
					                    	name=(String)gdfs1.get(i+gdfs1.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					   		</select>
						</td>
						<td>比例(%)：</td>
	        		 	<td>
							<input type="text" name="beginbl"
								   value="<%if (null != request.getParameter("beginbl")) out.print(request.getParameter("beginbl"));%>"
								   class="selected_font" />
						至
							<input type="text" name="endbl"
								   value="<%if (null != request.getParameter("endbl")) out.print(request.getParameter("endbl"));%>"
								   class="selected_font" />
				   		</td>
	        		 	
						<td> <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
					             <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;left:200%;float:right;top:0px;right:-340px">
							       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
							       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
							     </div>
					        <%}%>		
						</td>
					</tr>
	    		</table>
	    	</td></tr>		
		</table>
		
	<table>
			 <tr><td colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
              </div></td></tr>
		</table>
			
		<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px">
			<table width="1500px" height="70%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
				<tr height = "23" class="relativeTag">
				        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
				        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">城市</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
				        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">偏离度</div></td>
				        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">操作</div></td>
				        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">标准单价</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际单价</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账电费</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账电量</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td> 
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">供电方式</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">乡镇</div></td>
            			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">省审核状态</div></td>
				</tr>
				
		   <c:forEach items="${list}" var="list" varStatus="status">
		       <tr  bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}" title="">
		            <td>
		              <div align="center">${status.count}</div>
		            </td>
		       		<td>
		              <div align="center"><input type="checkbox" name="test[]" value="${list.id}" /></div>
		            </td>
		            <td>
		       			<div align="center" >${list.city}</div>
		       		</td>
		       		<td>
		       			<div align="center" >${list.zdname}</div>
		       		</td>
		            <td>
		       			<div align="center" >${list.pld}%</div>
		       		</td>
		       		<td>
		       			<div align="center" ><a href="javascript:lookDetails('${list.id}','<%=accountmonth %>')">查看详细</a></div>
		       		</td>
		       		<td>
		       			<div align="center" >${list.bzdj}</div>
		       		</td>
		       		<td>
		       			<div align="center">${list.sjdj}</div>
		       		</td>	
					 <td>
		       			<div align="center" >${list.bzdf}</div>
		       		</td>
		       		<td>
		       			<div align="center" >${list.bzdl}</div>
		       		</td>
		       		<td>
		       			<div align="center" >${list.accountmonth}</div>
		       		</td>
		       		<td>
		       			<div align="center" >${list.zdsx}</div>
		       		</td>
		       		<td>
		       			<div align="center" >${list.zdlx}</div>
		       		</td>
		       		<td>
		       			<div align="center" >${list.gdfs}</div>
		       		</td>	       		
		       		<td>
		       			<div align="center" >${list.zdid}</div>
		       		</td>
		       		<td>
		       			<div align="center" >${list.xian}</div>
		       		</td>
		       		<td>
		       			<div align="center" >${list.xiaoqu}</div>
		       		</td>
		       		<td>
		       			<div align="center" >${list.state}</div>
		       		</td>
		       </tr>
		</c:forEach>    
		    
		<% if (intnum==0){
         	for(int i=0;i<15;i++){
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
      	</tr>
      <% }}else if(!(intnum > 15)){
    	  for(int i=((intnum-1)%15);i<15;i++){
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
        <% }}%>
		</table>
		</div>
		<table width="100%" height="8%" border="0" cellspacing="0" cellpadding="0">
		   	 <tr>
                    <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                        <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                    </div></td>
		    </tr>
		    <tr>
		    	<td align="right">
		    		<div id="daochu"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 4px">
							<img src="<%=path%>/images/daochu.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导出</span>
					</div>
					
					<div id="tongguo" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:20px">
								 <img src="<%=path %>/images/daochu.png" width="100%" height="100%">
							 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">结单</span>      
					</div> 
					
					<div id="tuidan" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:36px">
								 <img src="<%=path %>/images/daochu.png" width="100%" height="100%">
							 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">退单</span>      
					</div> 
		      	</td>
		    </tr>		                         
		</table>
		<input type="hidden" name="sheng2" id="sheng2" value="" />
		<input type="hidden" name="shi2" id="shi2" value="" />
		<input type="hidden" name="xian2" id="xian2" value="" />
		<input type="hidden" name="bzw1" id="bzw1" value="1" />
	</form>
</body>

<script type="text/javascript">
<!--
var XMLHttpReq;
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
		}else if(para=="checkpass1"){
			XMLHttpReq.onreadystatechange = processResponse_checkcity1;
		}else if(para=="checknopass1"){
			XMLHttpReq.onreadystatechange = processResponse_checkcityno1;
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		XMLHttpReq.send(null);  
	}
	
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res = XMLHttpReq.responseText;
              	window.alert(res);
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    function processResponse_checkcity1() {
    	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
    		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
    			document.form1.bzw1.value = XMLHttpReq1.responseText;
    		} else { //页面不正常
    			window.alert("您所请求的页面有异常。");
    		}
    	}
    }
    function processResponse_checkcityno1() {
    	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
    		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
    			document.form1.bzw1.value = XMLHttpReq1.responseText;
    		} else { //页面不正常
    			window.alert("您所请求的页面有异常。");
    		}
    	}
    }
function passCheck1(chooseIdStr) {//省级审核通过240
	sendRequest(path+"/servlet/ProAuditingServlet?command=tongguo1&chooseIdStr="+chooseIdStr, "checkpass1");
    }
function passnoCheck1(chooseIdStr) {//省级退单240
	sendRequest(path+"/servlet/ProAuditingServlet?command=tuidan1&chooseIdStr="+chooseIdStr, "checknopass1");
}   
function processResponse_sheng() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
             	updateShi(res);
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          	updateQx(res);
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          	updateZd(res);
        } else {
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
 function chooseAll() { 
        var qm = document.getElementsByName('test');
        var m = document.getElementsByName('test[]');   
        var l = m.length; 
        if(qm[0].checked == true){
          for (var i = 0; i < l; i++) {   
            m[i].checked = true;   
          }  
        }else{
          for (var i = 0; i < l; i++) {   
            m[i].checked = false;  
          }   
        }
        
    }   
    
    	document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.property.value='<%= property%>';
		document.form1.state.value='<%=state%>';
		document.form1.accountmonth.value='<%=accountmonth%>';
		document.form1.beginbl.value='<%=beginbl%>';
		document.form1.endbl.value='<%=endbl%>';
		document.form1.zdmc.value='<%=zdmc%>';
		document.form1.zdid.value='<%=zdid%>';
		document.form1.gdfs.value='<%=gdfs%>';
 </script>
 <script language="javascript">
 var path = '<%=path%>';
 function lookDetails(id,bzyf){ 
	 window.open(path+"/servlet/CityCheckServlet?command=info&id="+id+"&bzyf="+bzyf);	
 }
</script>
</html>

