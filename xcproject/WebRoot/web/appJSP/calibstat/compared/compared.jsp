<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.ptac.app.lease.statistical.bean.LeaseStatBean" %>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	String zdshuxing = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";//站点属性
	String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";//站点类型
	String beginScb = request.getParameter("beginScb")!=null?request.getParameter("beginScb"):"";//起始生产标
	String endScb = request.getParameter("endScb")!=null?request.getParameter("endScb"):"";//结束生产标
	String beginJyscb = request.getParameter("beginJyscb")!=null?request.getParameter("beginJyscb"):"";//起始建议生产标
	String endJyscb = request.getParameter("endJyscb")!=null?request.getParameter("endJyscb"):"";//结束建议生产标
	String xcbl = request.getParameter("xcbl")!=null?request.getParameter("xcbl"):"";//相差比例

    String roleId = (String)session.getAttribute("accountRole");
    int intnum = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;
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
			text-align: left;
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

 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script language="javascript">
var path = '<%=path%>';
function queryDegree(command){
	   		
	var beginScb = document.form1.beginScb.value;//起始生产标
	var endScb = document.form1.endScb.value;//结束生产标
	var beginJyscb = document.form1.beginJyscb.value;//起始建议生产标
	var endJyscb = document.form1.endJyscb.value;//结束建议生产标
	var xcbl = document.form1.xcbl.value;//相差比例
	if(!isNaN(beginScb)){
		if(!isNaN(endScb)){
			if(!isNaN(beginJyscb)){
				if(!isNaN(endJyscb)){
					if(!isNaN(xcbl)){
						 document.form1.action=path+"/servlet/ComparedServlet?command="+command;
        				 document.form1.submit();
					}else{
						alert("相差比例必须为数字！");
					}
				}else{
					alert("结束建议生产标必须为数字！");
				}
			}else{
				alert("起始建议生产标必须为数字！");
			}
		}else{
			alert("结束生产标必须为数字！");
		}
	}else{
		alert("起始生产标必须为数字！");
	}
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
					//showdiv("请稍等..............");
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
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">建议生产标与生产标对比查询</span>
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
                <td><select name="shi" class="selected_font" id="shi">
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
         	    </select><span class="style1">&nbsp;*</span></td>
 				<td>
				站点属性：
				</td>
				<td>
					<select name="jzproperty" class="selected_font"
							onchange="zdsxCheck(this.value)">
						<option value="0">请选择</option>
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
					</select>
				</td>
                <td>
				站点类型：
				</td>
				<td>
					<select name="zdlx" class="selected_font">
						<option value="0">请选择</option>
						<%
							ArrayList stationtype = new ArrayList();
							stationtype = ztcommon.getZdlx(zdshuxing);
							//stationtype = ztcommon.getSelctOptions("StationType");
							if (stationtype != null) {
								String code = "", name = "";
								int cscount = ((Integer) stationtype.get(0)).intValue();
								int size = stationtype.size() - 1;
								int i;
								for (i = cscount; i < size; i += cscount) {
										code = (String) stationtype.get(i
												+ stationtype.indexOf("CODE"));
										name = (String) stationtype.get(i
												+ stationtype.indexOf("NAME"));
										if(zdlx!=null&&!"".equals(zdlx)){
						%>
						<option value="<%=code%>"><%=name%></option>
						<%
							}
						   }
						  }
						%>
					  </select>
				   </td>
				   
                  <td>
					<p><font size="2">
						<div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
							<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
							<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                </div>
						</font>
					</p>
				</td>
	         	  <td >
		              <div id="chaxun" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
		                  <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                  <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
		               </div>
			      </td>
			      </table>
			      </td>
			      </tr>
                  
                 <tr>
                  <td colspan="5">
					<div style="width:90%;" > 
					  <p id="box3" style="display:none">
						<table>
							<tr class="form_label">
							   <td>生产标区间：</td>
							   <td>
									<input type="text" name="beginScb"
											 value="<%if (null != request.getParameter("beginScb")) out.print(request.getParameter("beginScb"));%>"
											 class="selected_font" />
									至
									<input type="text" name="endScb"
											 value="<%if (null != request.getParameter("endScb")) out.print(request.getParameter("endScb"));%>"
											 class="selected_font" />
							   </td>
							   <td>建议生产标区间：</td>
				   				<td>
									<input type="text" name="beginJyscb"
								 			value="<%if (null != request.getParameter("beginJyscb")) out.print(request.getParameter("beginJyscb"));%>"
								 			class="selected_font" />
									至
									<input type="text" name="endJyscb"
											value="<%if (null != request.getParameter("endJyscb")) out.print(request.getParameter("endJyscb"));%>"
								 			class="selected_font" />
				   				</td>
				   				<td>相差比例(%)：</td>
				   				<td>
									<input type="text" name="xcbl"
								 			value="<%if (null != request.getParameter("xcbl")) out.print(request.getParameter("xcbl"));%>"
								 			class="selected_font" />
				   				</td>
                            </tr>
                 		</table> 
 					</p>
 				 </div>
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
	
  <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >  	
			    <table width="100%" height="80%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                  <tr height = "23" class="relativeTag">
                      <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
                      <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">城市</div></td>
                      <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                      <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">生产标</div></td>
            		  <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">建议生产标</div></td>
                      <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">相差比例</div></td>
                      <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
                      <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
                      <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>
                      <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>
                  </tr>
					<c:forEach items="${list}"  var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       						<td>
       							<div align="center" >${status.count}</div>
       						</td>
       						<td>
				       			<div align="center" >${list.city}</div>
				       		</td> 
				           	<td>
				       			<div align="center" >${list.zdname}</div>
				       		</td>
				       		<td>
				       			<div align="center" >${list.scb}</div>
				       		</td>
				       		<td>
       							<div align="center" >${list.jyscb}</div>
       						</td> 
				           	<td>
				       			<div align="center" >${list.xcbl}%</div>
				       		</td>
				       		<td>
				       			<div align="center" >${list.zdsx}</div>
				       		</td>
				       		<td>
				       			<div align="center" >${list.zdlx}</div>
				       		</td>
				       		<td>
				       			<div align="center" >${list.xian}</div>
				       		</td>     
				       		<td>
				       			<div align="center" >${list.id}</div>
				       		</td>
    					</tr>
					</c:forEach>
       
        <% if (intnum==0){
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
            <td>&nbsp;</td>
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            </tr>
      <% }}else if(!(intnum > 16)){
    	  for(int i=((intnum-1)%16);i<16;i++){
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
        </tr>
        <% }}%>
      </table>
      </div>
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
	  <p class="form_label1">
			<font color="red">说明：相差比例：（现有生产标—建议生产标）/建议生产标*100%</font>
	  </p>
   </div>
</form>
</body>

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
		document.form1.jzproperty.value='<%=zdshuxing%>';
		document.form1.zdlx.value='<%=zdlx%>';
		document.form1.beginScb.value='<%=beginScb%>';
		document.form1.endScb.value='<%=endScb%>';
		document.form1.beginJyscb.value='<%=beginJyscb%>'; 
		document.form1.endJyscb.value='<%=endJyscb%>';
		document.form1.xcbl.value='<%=xcbl%>'; 
 </script>
</html>