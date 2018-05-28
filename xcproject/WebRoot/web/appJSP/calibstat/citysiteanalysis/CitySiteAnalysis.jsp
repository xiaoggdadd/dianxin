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
	String zdshuxing = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";//站点属性
	String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";//站点类型
	
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

function queryDegree(command){
    	
    	var year = document.form1.year.value;
        if(""!=(year)){
	         document.form1.action=path+"/servlet/CityServlet?command="+command;
	         document.form1.submit();
       	}else{
       		alert("年份不能为空！");
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
			showdiv("请稍等..............");
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
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">地市站点超标分析</span>
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
                <td><select name="shi" class="selected_font" id="shi" >
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
         	    </select></td>

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
                  <td>年份：</td>
				  <td>
					<input class="selected_font" type="text" name="year"
							value="<%=dateString%>"
							readonly="readonly" class="Wdate"
							onFocus="WdatePicker({skin:'default',dateFmt:'yyyy'})" />
					<span class="style1">&nbsp;*</span></td>
                  
	         	  <td>
                     <div id="chaxun" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
		                  <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                  <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
		             </div>
			      </td>
			    </table>
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
				<legend><b><font size="2">数据信息</font></b></legend>
	 			<table width="100%" align="center" CellSpacing="0" style="BORDER-COLLAPSE: collapse" borderColor=#eaeaea bgColor=#ffffff border=1>
		           <tr> 
		             <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">区县</div></td>
		             <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">分类</div></td>
		             <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">1月</div></td>
		             <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">2月</div></td>
		             <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">3月</div></td>
		             <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">4月</div></td>
		         	 <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">5月</div></td>
		         	 <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">6月</div></td>
		         	 <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">7月</div></td>
		             <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">8月</div></td>
		             <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">9月</div></td>
		             <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">10月</div></td>
		         	 <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">11月</div></td>
		         	 <td class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">12月</div></td>
		         	</tr>
         
		         	<c:forEach items="${list}"  var="list" varStatus="status">
					<tr>
		       			<td rowspan="3" class="form_label">
		       				<div align="center" >
		       					<a href="javascript:xiangxi('${list.xian}','<%=zdshuxing%>','<%=zdlx%>','<%=year%>')">${list.county}</a>
		       				</div>
		       			</td> 
		       			<td class="form_label" ><DIV align=center>全省定标电量</DIV></td>
						<td class="form_label"><DIV align=center>${list.qsdbdl01}</DIV></td>
						<td class="form_label"><DIV align=center>${list.qsdbdl02}</DIV></td>
						<td class="form_label"><DIV align=center>${list.qsdbdl03}</DIV></td>
						<td class="form_label"><DIV align=center>${list.qsdbdl04}</DIV></td>
						<td class="form_label"><DIV align=center>${list.qsdbdl05}</DIV></td>
						<td class="form_label"><DIV align=center>${list.qsdbdl06}</DIV></td>
						<td class="form_label"><DIV align=center>${list.qsdbdl07}</DIV></td>
						<td class="form_label"><DIV align=center>${list.qsdbdl08}</DIV></td>
						<td class="form_label"><DIV align=center>${list.qsdbdl09}</DIV></td>
						<td class="form_label"><DIV align=center>${list.qsdbdl10}</DIV></td>
						<td class="form_label"><DIV align=center>${list.qsdbdl11}</DIV></td>
						<td class="form_label"><DIV align=center>${list.qsdbdl12}</DIV></td>
					</tr>
					<tr>
						<td class="form_label"><DIV align=center>日均耗电量</DIV></td>
						<td class="form_label"><DIV align=center>${list.hdl1}</DIV></td>
						<td class="form_label"><DIV align=center>${list.hdl2}</DIV></td>
						<td class="form_label"><DIV align=center>${list.hdl3}</DIV></td>
						<td class="form_label"><DIV align=center>${list.hdl4}</DIV></td>
						<td class="form_label"><DIV align=center>${list.hdl5}</DIV></td>
						<td class="form_label"><DIV align=center>${list.hdl6}</DIV></td>
						<td class="form_label"><DIV align=center>${list.hdl7}</DIV></td>
						<td class="form_label"><DIV align=center>${list.hdl8}</DIV></td>
						<td class="form_label"><DIV align=center>${list.hdl9}</DIV></td>
						<td class="form_label"><DIV align=center>${list.hdl10}</DIV></td>
						<td class="form_label"><DIV align=center>${list.hdl11}</DIV></td>
						<td class="form_label"><DIV align=center>${list.hdl12}</DIV></td>
					</tr>
					<tr>
						<td class="form_label"><DIV align=center>超标比例</DIV></td>
						<td class="form_label"><DIV align=center>${list.cbbl01}</DIV></td>
						<td class="form_label"><DIV align=center>${list.cbbl02}</DIV></td>
						<td class="form_label"><DIV align=center>${list.cbbl03}</DIV></td>
						<td class="form_label"><DIV align=center>${list.cbbl04}</DIV></td>
						<td class="form_label"><DIV align=center>${list.cbbl05}</DIV></td>
						<td class="form_label"><DIV align=center>${list.cbbl06}</DIV></td>
						<td class="form_label"><DIV align=center>${list.cbbl07}</DIV></td>
						<td class="form_label"><DIV align=center>${list.cbbl08}</DIV></td>
						<td class="form_label"><DIV align=center>${list.cbbl09}</DIV></td>
						<td class="form_label"><DIV align=center>${list.cbbl10}</DIV></td>
						<td class="form_label"><DIV align=center>${list.cbbl11}</DIV></td>
						<td class="form_label"><DIV align=center>${list.cbbl12}</DIV></td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
		<br/>
		<fieldset>
			<legend><b><font size="2">图表信息</font></b></legend>
				<DIV id="chartdiv"></DIV>
				<script type="text/javascript">
						var chart = new FusionCharts("<%=path%>/javascript/Line.swf", "ChartId", "1000", "400", "0", "0");
						chart.setXMLData("${dataString}");		   
						chart.render("chartdiv");
				</script>
		</fieldset>
		<p class="form_label1">
			<font color="red">说明：全省定标电量为：符合条件的各站点的全省定标电量和<br/>
											日均耗电量：符合条件的各站点的日均耗电量<br/>
											超标比例：（日均耗电量—全省定标电量）/ 全省定标电量*100%
			</font>
		</p>
  	  </td>
   </tr>
</table>
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
   </div>
</form>
</body>

<script type="text/javascript">
var path = '<%=path%>';
		
function xiangxi(xian,zdsx,zdlx,year) {//查看详情
	document.form1.attributes["action"].value = path
			+ "/servlet/CityServlet?command=xiangqing&xian="+xian+"&zdsx="
			+ zdsx+"&zdlx="+zdlx+"&year="+year;
	document.form1.submit();
}
		
var XMLHttpReq;
//选择浏览器
function createXMLHttpRequest(){
	if(window.XMLHttpRequest){ 
		XMLHttpReq = new XMLHttpRequest();
	}else if (window.ActiveXObject){ 
		try{
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e){
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){
			}
		}
	}
}
</script>

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
		document.form1.year.value='<%=year%>';
 </script>
</html>