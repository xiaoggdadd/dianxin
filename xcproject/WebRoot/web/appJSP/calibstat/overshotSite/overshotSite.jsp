<%@ page language="java" import="java.util.*,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
//----管理员登录默认选中第一个，区县人员默认选中区县
Account account = (Account)session.getAttribute("account");//账户
String sheng = (String)session.getAttribute("accountSheng");//省
String agcode1="";
if(request.getParameter("shi")==null){
	List shilist = new ArrayList();
	CommonBean commBean = new CommonBean();
	shilist = commBean.getAgcode(sheng,account.getAccountId());
	if(shilist!=null){
		int scount = ((Integer)shilist.get(0)).intValue();
    	agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
 	}
} 
String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
String property = request.getParameter("property")!=null?request.getParameter("property"):"0";//站点属性
String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";//站点类型
String cbmc = request.getParameter("cbmc")!=null?request.getParameter("cbmc"):"";//超标前_名
String cbbl = request.getParameter("cbbl")!=null?request.getParameter("cbbl"):"";//超标比例
String cbjdz = request.getParameter("cbjdz")!=null?request.getParameter("cbjdz"):"";//超标绝对值

String loginName = (String)session.getAttribute("loginName");
//颜色
int numcolor = request.getAttribute("numcolor")!=null?(Integer)request.getAttribute("numcolor"):0;//条数 ,查出数据的条数，用于颜色设置
String color = "";//颜色
String month = (String)request.getAttribute("month");

%>
<html>
  <head>
   <title>超标站点查询</title>
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
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript">
var path = '<%=path%>';

function lookDetails2(zdid){//详情
	window.open(path+"/servlet/OverSiteServlet?command=xiangqing&zdid="+zdid);
}
   
function selectTree(command){//查询
  var shi = document.form1.shi.value;//市
  var cbmc = document.form1.cbmc.value;//超标前n名
  var cbbl = document.form1.cbbl.value;//超标比例
  var cbjdz = document.form1.cbjdz.value;//超标绝对值
  
if (shi == "" || shi == "0" || shi == null){
		alert("城市不能为空");
	}else{
		if(!isNaN(cbmc)){
			if(!isNaN(cbbl)){
				if(!isNaN(cbjdz)){
					document.form1.action = path + "/servlet/OverSiteServlet?command="+command;
					document.form1.submit();
					if(command == "chaxun"){
						showdiv("请稍等..............");
					}
				}else{
					alert("超标绝对值必须为数字！");
				}
			}else{
				alert("超标比例必须为数字！");
			}
		}else{
			alert("超标前_名必须为数字！");
		}
	}
}
 
$(function(){
	$("#chaxun").click(function(){
	   selectTree("chaxun");
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
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">超标站点查询</span>	
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
									<span class="style1">&nbsp;*</span>
								</td>
							<td>
								月份：
							</td>
							<td>
								<input class="selected_font" type="text" name="month"
									value="<%if (null != request.getParameter("month"))
				out.print(request.getParameter("month"));%>"
									readonly="readonly" class="Wdate"
									onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />

							</td>
							
							<td>超标前：</td>
							<td>
								<input type="text" name="cbmc"
									value="<%if (null != request.getParameter("cbmc"))
														 out.print(request.getParameter("cbmc"));%>"
									class="selected_font" />名
							</td>
								<td>
									<p>
										<font size="2">
											<div title="您可以进行详细的条件筛选" id="query1"
												onclick="openShutManager(this,'box3',false)"
												style="position: relative; width: 17px; height: 17px; cursor: pointer; top: 10PX">
												<img alt=""
													src="<%=request.getContextPath()%>/images/gaojichaxun.gif"
													width="100%" height="100%" />
												<span
													style="font-size: 12px; position: absolute; left: 2px; top: 0px; color: white">&nbsp;&nbsp;&nbsp;</span>
											</div> </font>
									</p>
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

			<div style="width: 88%;">
				<p id="box3" style="display: none">
					<table>
						<tr class="form_label">
							<td>
								站点属性：
								</td>
								<td>
									<select name="property" class="selected_font"
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
											stationtype = ztcommon.getZdlx(property);
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
								   <td>超标比例小于(%)：</td>
								   <td><input class="selected_font" type="text" name="cbbl" value="" />
								   </td>
								   <td>超标绝对值：</td>
								   <td><input class="selected_font" type="text" name="cbjdz" value="" />
								   </td>
								</tr>
						</table>
					</p>
			</div>

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
       <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">       
           <tr class="relativeTag" >
            <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">序号</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">城市</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">区县</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点名称</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点属性</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点类型</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">月份</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">全省定标电量</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">日均耗电量</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">超标比例</div></td>
         	 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">超标绝对值</div></td>
         	</tr>
         
         	<c:forEach items="${beanlist}"  var="bean" varStatus="status">
			<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       			<td><div align="left" >${status.count}</div></td>
       			<td><div align="left" >${bean.city}</div></td> 
       			<td><div align="right" >${bean.xian}</div></td> 
       			<td><div align="right" > <a href="javascript:lookDetails2(${bean.zdid})">${bean.zdname}</a></div></td>
       			<td><div align="right" >${bean.property}</div></td>
       			<td><div align="right" >${bean.zdlx}</div></td>
       			<td><div align="right" >${bean.month}</div></td>  
       			<td><div align="right" >${bean.qsdbdl}</div></td> 		
       			<td><div align="right" >${bean.rjhdl}</div></td> 	
       			<td><div align="right" >${bean.cbbl}%</div></td> 	
       			<td><div align="right" >${bean.cbjdz}</div></td> 	       			
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
  				<tr>
			   		<td align="left">
			   			<font size="2" color="red">说明：月份：自然月<br>
												  生产标：该站点的生产标（不含空调）<br>
									                        日均耗电量：覆盖自然月的电费单电表用电量/自然月所占天数*（1+对应月系数）<br>
					   					         超标比例改为：（日均耗电量-全省定标电量）/全省定标电量(当月的)<br>
					   					              超标绝对值：日均耗电量-全省定标电量
						</font>                                                                  
                   	</td>
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
  document.form1.shi.value='<%=shi%>';
  document.form1.property.value='<%=property%>';
  document.form1.cbmc.value='<%=cbmc%>';
  document.form1.zdlx.value='<%=zdlx%>';
  document.form1.cbbl.value='<%=cbbl%>';
  document.form1.cbjdz.value='<%=cbjdz%>';
  </script>
</html>
