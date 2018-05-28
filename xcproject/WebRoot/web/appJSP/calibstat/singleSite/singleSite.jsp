<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.text.*"%>

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
	String zdsx1 = request.getParameter("zdsx")!=null?request.getParameter("zdsx"):"0";
	String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";//站点类型
	String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";//站点名称
	Date date = new Date(); 
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
	String dateString = formatter.format(date);
	
	String year = request.getParameter("year")!=null?request.getParameter("year"):dateString;

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
			text-align: right;
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

.form1{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;
   height:19px;
   width:130px;
}

 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/FusionCharts.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script language="javascript">
var path = '<%=path%>';
function queryDegree(action){
    	
    	var command=action;
    	var zdname1 = document.form1.zdname.value;
    	var year = document.form1.year.value;
        if(""!=(zdname1)){
        	if(""!=(year)){
		   		   var comm = document.getElementById("command");
		   		   comm.value = command;
	               document.form1.action=path+"/servlet/SingleSiteServlet";
	               document.form1.submit();
       		}else{
       			 alert("年份不能为空！");
       		}
        }else{
        	 alert("站点名称不能为空！");	
        }
}

$(function(){
	$("#chaxun").click(function(){
		if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
	        alert("城市不能为空");
	   	}else{
			queryDegree("chaxun");
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
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">单站点超标分析</span>
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
                  <td>站点名称：</td>
				  <td><input type="text" name="zdname" class="selected_font" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" />
				  	  <span class="style1">&nbsp;*</span></td>
                  <td>
					<p><font size="2">
							<div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
								<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
								<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                     </div>
							
						</font>
					</p>
				</td>
	         	  <td>
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
							    <td>
								站点属性：
								</td>
								<td>
									<select name="zdsx" class="selected_font"
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
											stationtype = ztcommon.getZdlx(zdsx1);
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
					             <td>站点ID：</td>
                            	 <td><input type="text" name="zdid" class="selected_font" value="<%if(null!=request.getParameter("zdid")) out.print(request.getParameter("zdid")); %>"/>
                            	 </td>
					             <td>年份：</td>
								 <td>
									<input class="selected_font" type="text" name="year"
											value="<%=dateString%>"
											readonly="readonly" class="Wdate"
											onFocus="WdatePicker({skin:'default',dateFmt:'yyyy'})" />
									<span class="style1">&nbsp;*</span></td>
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
  <table border="0" align="center" width="97%">
  	<tr>
  		<td width="83%">  		
			<fieldset>
				<legend><b><font size="2">站点信息</font></b></legend>
	 			<table width="100%" align="right" CellSpacing="1">
				     <tr>    
				        <td class="form_label"><div>站点名称：</div></td>
				        <td width=""><input type="text"  readonly="readonly" value="${bean.zdname}" class="form1" /></td>
						<td class="form_label"><div>站点ID：</div></td>
				        <td width=""><input type="text" readonly="readonly" value="${bean.zdid}"  class="form1" /></td>
				        <td class="form_label"><div>城市：</div></td>
				        <td width=""><input type="text" readonly="readonly" value="${bean.city}"  class="form1" /></td>
				        <td class="form_label"><div>区县：</div></td>
				        <td width=""><input type="text" readonly="readonly" value="${bean.xian}"  class="form1" /></td>
				     </tr>
				     <tr>
				     	<td class="form_label"><div>乡镇：</div></td>
				        <td width=""><input type="text" readonly="readonly" value="${bean.xiaoqu}"  class="form1" /></td>
				     	<td class="form_label"><div>站点属性：</div></td>
				        <td width=""><input type="text" readonly="readonly" value="${bean.zdsx}"  class="form1" /></td>
						<td class="form_label"><div>直流：</div></td>
				        <td width=""><input type="text" name="zlfh"  readonly="readonly" value="${bean.zlfh}"  class="form1" /></td>
				        <td class="form_label"><div>交流：</div></td>
				        <td width=""><input type="text" name="jlfh"  readonly="readonly" value="${bean.jlfh}"  class="form1" /></td>
				     </tr>
				      <tr>
				        <td class="form_label"><div>额定耗电量：</div></td>
				        <td width=""><input type="text" name="edhdl"  readonly="readonly" value="${bean.edhdl}"  class="form1" /></td>
				        <td class="form_label"><div>生产标：</div></td>
				        <td width=""><input type="text" name="scb"  readonly="readonly" value="${bean.scb}"  class="form1" /></td>
				     	<td class="form_label"><div>站点类型：</div></td>
				        <td width=""><input type="text" readonly="readonly" value="${bean.zdlx}"  class="form1" /></td>
						<td class="form_label"><div>房屋类型：</div></td>
				        <td width=""><input type="text" name="fwlx"  readonly="readonly" value="${bean.fwlx}"  class="form1" /></td>
				     </tr>
				     <tr>
				     	<td class="form_label"><div>空调个数：</div></td>
				        <td width=""><input type="text" name="ktgs"  readonly="readonly" value="${bean.ktgs}"  class="form1" /></td>
				     </tr>
				</table>
			</fieldset>
			<br/>
			<fieldset>
				<legend><b><font size="2">电量信息</font></b></legend>
	 			<table width="100%" align="center" CellSpacing="0" style="BORDER-COLLAPSE: collapse" borderColor=#eaeaea bgColor=#ffffff border=1>
				     <tr>
				     	<td><DIV align=center></DIV></td> 
				        <td><DIV align=center></DIV></td>
				        <td class="form_label"><DIV align=center>1月</DIV></td>
				        <td class="form_label"><DIV align=center>2月</DIV></td>
				        <td class="form_label"><DIV align=center>3月</DIV></td>
				        <td class="form_label"><DIV align=center>4月</DIV></td>
				        <td class="form_label"><DIV align=center>5月</DIV></td>
				        <td class="form_label"><DIV align=center>6月</DIV></td>
				        <td class="form_label"><DIV align=center>7月</DIV></td>
				        <td class="form_label"><DIV align=center>8月</DIV></td>
				        <td class="form_label"><DIV align=center>9月</DIV></td>
				        <td class="form_label"><DIV align=center>10月</DIV></td>
				        <td class="form_label"><DIV align=center>11月</DIV></td>
				        <td class="form_label"><DIV align=center>12月</DIV></td>
				     </tr>
				     <tr>
				         <td rowspan="3" class="form_label"><DIV align=center><%=year%>年</DIV></td>
				         <td class="form_label" ><DIV align=center>全省定标电量</DIV></td>
				         <td class="form_label"><DIV align=center>${bean.qsdbdl01}</DIV></td>
				         <td class="form_label"><DIV align=center>${bean.qsdbdl02}</DIV></td>
				         <td class="form_label"><DIV align=center>${bean.qsdbdl03}</DIV></td>
				         <td class="form_label"><DIV align=center>${bean.qsdbdl04}</DIV></td>
				         <td class="form_label"><DIV align=center>${bean.qsdbdl05}</DIV></td>
				         <td class="form_label"><DIV align=center>${bean.qsdbdl06}</DIV></td>
				         <td class="form_label"><DIV align=center>${bean.qsdbdl07}</DIV></td>
				         <td class="form_label"><DIV align=center>${bean.qsdbdl08}</DIV></td>
				         <td class="form_label"><DIV align=center>${bean.qsdbdl09}</DIV></td>
				         <td class="form_label"><DIV align=center>${bean.qsdbdl10}</DIV></td>
				         <td class="form_label"><DIV align=center>${bean.qsdbdl11}</DIV></td>
				         <td class="form_label"><DIV align=center>${bean.qsdbdl12}</DIV></td>
				     </tr>
				     <tr>
				          <td class="form_label"><DIV align=center>日均耗电量</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.hdl1}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.hdl2}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.hdl3}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.hdl4}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.hdl5}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.hdl6}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.hdl7}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.hdl8}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.hdl9}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.hdl10}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.hdl11}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.hdl12}</DIV></td>
				     </tr>
				     <tr>
				     	  <td class="form_label"><DIV align=center>超标比例</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.cbbl01}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.cbbl02}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.cbbl03}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.cbbl04}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.cbbl05}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.cbbl06}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.cbbl07}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.cbbl08}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.cbbl09}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.cbbl10}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.cbbl11}</DIV></td>
				          <td class="form_label"><DIV align=center>${bean.cbbl12}</DIV></td>
				     </tr>
				</table>
			</fieldset>
			<br/>
			<fieldset>
				<legend><b><font size="2">图表</font></b></legend>
				     		<DIV id="chartdiv"></DIV>
				     		<script type="text/javascript">
					            var chart = new FusionCharts("<%=path%>/javascript/Line.swf", "ChartId", "1000", "400", "0", "0");
							    chart.setXMLData("${dataString}");		   
							    chart.render("chartdiv");
							</script>
				     	
			</fieldset>
			<p class="form_label1"><font color="red">说明：超标比例 = （日均耗电量-全省定标电量）/ 全省定标电量</font></p>
  		</td>
	</tr>
	<tr>
       <td><input type="hidden" name="command" id="command" value=""/></td>
    </tr>
</table>
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
<script type="text/javascript">

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
<script language="javascript">
   		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.year.value='<%=year%>';
		document.form1.zdsx.value='<%=zdsx1%>';
		document.form1.zdlx.value='<%=zdlx%>';
		document.form1.zdname.value='<%=zdname%>';
 </script>
 </html>