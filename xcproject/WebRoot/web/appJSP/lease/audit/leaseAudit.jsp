<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.ptac.app.lease.query.bean.LeaseBean" %>
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
	String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
	String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";

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
function queryDegree(action){
    	
    	var command=action;
        if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
	             alert("城市不能为空");
	   	}else{
	   		   var comm = document.getElementById("command");
	   		   comm.value = command;
               document.form1.action=path+"/servlet/LeaseAuditServlet";
               document.form1.submit();
       	}
}

  $(function(){
	  
	  	$("#tongguo").click(function() {
			passCheck();//通过
		});

		$("#butongguo").click(function() {
			passCheckNoPass();//不通过
		});
	
		$("#chaxun").click(function(){
			   	if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
	               	alert("城市不能为空");
	   			}else{
					queryDegree("chaxun");
					showdiv("请稍等..............");
				}
		});
	});
  </script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" >	
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" >
	      <tr>
           <td width="50" >
             <div style="width:700px;height:50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">租赁合同审核</span>
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
                <td><select name="shi" class="selected_font" id="shi" onchange="changeCity()">
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


                 <td>区县：</td>
                 <td><select name="xian" class="selected_font" onchange="changeCountry()">
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
         	        </select>        
                 </td>
                 <td>乡镇：</td>
                 <td><select name="xiaoqu" class="selected_font" onchange="">
         		        <option value="0">请选择</option>
         		    <%
		         	ArrayList xiaoqulist = new ArrayList();
		         	xiaoqulist = commBean.getAgcode(xian,account.getAccountId());
		         	if(xiaoqulist!=null){
		         		String agcode="",agname="";
		         		int scount = ((Integer)xiaoqulist.get(0)).intValue();
		         		for(int i=scount;i<xiaoqulist.size()-1;i+=scount)
	                    {
	                    	agcode=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGCODE"));
	                    	agname=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGNAME"));
	                    %>
	                    <option value="<%=agcode%>"><%=agname%></option>
	                    <%}
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
							  <td>合同名称：</td>
					 		  <td><input type="text" name="leasename" class="selected_font" value="<%if(null!=request.getParameter("leasename")) out.print(request.getParameter("leasename")); %>" />
					 		  </td>
					 		   <td>站点名称：</td>
					 		  <td><input type="text" name="zdname" class="selected_font" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" />
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
			    <table width="100%" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                  <tr height = "10" class="relativeTag">
                      <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
                      <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
                      <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
                      <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
                      <td width="6%" height="15" bgcolor="#DDDDDD"><div align="center" class="bttcn">合同名称</div></td>
            		  <td width="6%" height="15" bgcolor="#DDDDDD"><div align="center" class="bttcn">合同编号</div></td>
            		  <td width="3%" height="15" bgcolor="#DDDDDD"><div align="center" class="bttcn">审核状态</div></td>
            		  <td width="6%" height="15" bgcolor="#DDDDDD"><div align="center" class="bttcn">审核时间</div></td>
            		  <td width="4%" height="15" bgcolor="#DDDDDD"><div align="center" class="bttcn">审核人</div></td>
                  </tr>
					<c:forEach items="${list}"  var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       						<td><div align="center" >${status.count}</div></td> 
       						<td>
             				 <div align="center"><input type="checkbox" name="test[]" value="${list.leaseid}" /></div>
           					</td>
				           	<td>
				       			<div align="center" >${list.area}</div>
				       		</td>
				       		<td>
				       			<div align="center" >${list.jzname}</div>
				       		</td>
				       		<td>
				       			<div align="center" >${list.stationtype}</div>
				       		</td>
				       		<td>
				       			<div align="center" >
				       				<a href="javascript:lookDetailss('${status.count}')">${list.leasename}</a>
				       			</div>
				       			<input id="leaseid" name="leaseid${status.count}" type="hidden" value="${list.leaseid}"/>
				       		</td>
				       		<td>
				       			<div align="center" >${list.leasenum}</div>
				       		</td> 
				       		<td>
				       			<div align="center" >${list.auditstatus}</div>
				       		</td> 
				       		<td>
				       			<div align="center" >${list.audittime}</div>
				       		</td> 
				       		<td>
				       			<div align="center" >${list.auditor}</div>
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
                   <div id="butongguo"
						style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right: 10px">
						<img alt="" src="<%=request.getContextPath()%>/images/baocun.png"
						width="100%" height="100%" />
						<span
						style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">不通过</span>
					</div>
					<div id="tongguo"
						style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
						<img src="<%=path%>/images/2chongzhi.png" width="100%"
						height="100%">
						<span
						style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">通过</span>
					</div>
             </td>
         </tr>
          <tr>
           <td><input type="hidden" name="command" id="command" value=""/>
           	   <input type="hidden" name="bzw1" id="bzw1" value="1" /></td>
         </tr>
	  </table>
   </div>

</form>
</body>
</html>

<script type="text/javascript">
var path = '<%=path%>';
function lookDetailss(statusi){
	document.form1.attributes["target"].value = "_blank";
	document.form1.attributes["action"].value = path
			+ "/servlet/LeaseAudit?command=xiangqing&statusi="
			+ statusi;
	document.form1.submit();
}

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
}
//改变乡镇
function changeCountry() {
	var sid = document.form1.xian.value;
	if (sid == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=xian&pid=" + sid, "xian");
	}
}

</script>

<script type="text/javascript">
var path = '<%=path%>';
function passCheck() {//通过
	var m = document.getElementsByName('test[]');
	var arr = new Array;
	var l = m.length; //l表示选择的条数
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var bzw = 1;
	var bzw1 = "";
	var count2 = 0;
	var chooseIdStr1 = "";

	//判断选择了多少条数
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
	}
	if (count != 0) {

		//最大只能传240条数据   所以取240的倍数
		if (count % 240 == 0) {
			n = count / 240 - 1;
		} else {
			n = (count - (count % 240)) / 240;
		}
		var r = 240 * n;
		for ( var i = 0; i < l; i++) {
			if (m[i].checked == true) {
				bz += 1;
				count1 += 1;//选择的条数
				var j = m[i].value.toString();
				var chooseIdStr3 = m[i].value.toString().substring(0, j);
				chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
			}
			var t = bz;
			bzw1 = document.form1.bzw1.value;
			if (bzw1 == "1") {
				if (count1 <= r || (t >= 239 && t <= 241) {
					if ((t / 240 == 1) || (t >= 239 && t <= 241)) {
						chooseIdStr1 = chooseIdStr1.substring(0,chooseIdStr1.length - 1);//去除最后一个逗号
						passCheck1(chooseIdStr1);
						chooseIdStr1 = "";
						bz = 0;
						count2 = 0;
					}
				} else if (count == count1 && bzw == 1) {
					chooseIdStr1 = chooseIdStr1.substring(0,chooseIdStr1.length - 1);
					bzw = 0;
					document.form1.action = path
							+ "/servlet/LeaseAudit?command=pass&chooseIdStr1="
							+ chooseIdStr1;
					document.form1.submit();
				}
			} else if (bzw1 == "0") {
				document.form1.action = path + "/web/msg.jsp";
				document.form1.submit();
				return;
			}
		}
	} else {
		alert("请选择信息！");
	}
}

//审核不通过
function passCheckNoPass() {
	var m = document.getElementsByName('test[]');
	var l = m.length;
	var chooseIdStr1 = "";
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var count2 = 0;
	var bzw = 1;
	var bzw1 = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
	}
	if (count != 0) {
		if (count % 240 == 0) {
			n = count / 240 - 1;
		} else {
			n = (count - (count % 240)) / 240;
		}
		var r = 240 * n;
		for ( var i = 0; i < l; i++) {
			if (m[i].checked == true) {
				bz += 1;
				count1 += 1;
				var j = m[i].value.toString();
				var chooseIdStr3 = m[i].value.toString().substring(0, j);
				chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
			}
			bzw1 = document.form1.bzw1.value;
			var t = bz;
			if (bzw1 == "1") {
				if (count1 <= r || (t >= 239 && t <= 241) {
					if ((t / 240 == 1) || (t >= 239 && t <= 241)) {
						chooseIdStr1 = chooseIdStr1.substring(0,chooseIdStr1.length - 1);
						passCheckNo2(chooseIdStr1);
						chooseIdStr1 = "";
						bz = 0;
						count2 = 0;
					}
				} else if (count == count1 && bzw == 1) {
					chooseIdStr1 = chooseIdStr1.substring(0,chooseIdStr1.length - 1);
					bzw = 0;
					document.form1.action = path
							+ "/servlet/LeaseAudit?command=nopass1&chooseIdStr1="
							+ chooseIdStr1;
					document.form1.submit();
					showdiv("请稍等..............");
				}
			} else if (bzw1 == "0") {
				document.form1.action = path + "/web/msg.jsp";
				document.form1.submit();
				return;
			}
		}
	} else {
		alert("请选择信息！");
	}
}

function passCheck1(chooseIdStr1) {//通过240
	sendRequest1(path
				+ "/servlet/LeaseAudit?command=pass1&chooseIdStr1="
				+ chooseIdStr1, "pass1");
}

function passCheckNo2(chooseIdStr1) {//审核不通过1
	sendRequest1(path
				+ "/servlet/LeaseAudit?command=nopass&chooseIdStr1="
				+ chooseIdStr1, "nopass");
}

function sendRequest1(url, para) {

	createXMLHttpRequest1();
	XMLHttpReq1.open("POST", url, true);
	if (para == "pass1") {
		XMLHttpReq1.onreadystatechange = processResponse_checkcity1;
	} else if (para == "nopass") {
		XMLHttpReq1.onreadystatechange = processResponse_checkcityno11;
	} else {
		XMLHttpReq1.onreadystatechange = processResponse;//指定响应函数
	}
	XMLHttpReq1.send(null);
}

var XMLHttpReq1;
function createXMLHttpRequest1() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq1 = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq1 = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq1 = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
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

//响应函数
function processResponse_checkcityno11() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			document.form1.bzw1.value = XMLHttpReq1.responseText;
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}

// 处理返回信息函数
function processResponse() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq1.responseText;
			window.alert(res);
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}

//选择所有按钮
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

</script>

<script language="javascript">
   
   		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';

 </script>