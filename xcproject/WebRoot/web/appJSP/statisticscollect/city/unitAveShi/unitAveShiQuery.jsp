<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();//项目路径
Account account = (Account)session.getAttribute("account");//账户
String sheng = (String)session.getAttribute("accountSheng");//省
//省市县三级联动参数
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
//回显字段
String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;//市
String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";//区县
String gdfs = request.getParameter("gdfs")!=null?request.getParameter("gdfs"):"0";//供电方式
String jzproperty = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";//站点属性
String autoauditstatus = request.getParameter("autoauditstatus")!=null?request.getParameter("autoauditstatus"):"5";//自动审核状态
String manauditstatus = request.getParameter("manauditstatus")!=null?request.getParameter("manauditstatus"):"5";//人工审核状态
String countryheadstatus = request.getParameter("countryheadstatus")!=null?request.getParameter("countryheadstatus"):"5";//区县审核状态
String citystatus = request.getParameter("citystatus")!=null?request.getParameter("citystatus"):"5";//市级审核状态
String cityzrauditstatus = request.getParameter("cityzrauditstatus")!=null?request.getParameter("cityzrauditstatus"):"1";//市主任审核状态
String bzyfstart = request.getParameter("bzyfstart")!=null?request.getParameter("bzyfstart"):"";//报账月份起
String bzyfend = request.getParameter("bzyfend")!=null?request.getParameter("bzyfend"):"";//报账月份止

//颜色
int numcolor = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;//条数 ,查出数据的条数，用于颜色设置
String color = "";//颜色
%>
<html>
	<head>
		<title>
		地市单价平均与平均单价
		</title>
		<style>
		.style1{
		color: red;
		font-weight: bold;
		}
		.form_label{
			text-align:right;
			font-family: 宋体;
			font-size: 12px;
			height:23px

		}
		.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		}
		body{
		overflow-x:hidden;
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		}
		#div1{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
		ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
		#div1 p{float:left;font-size:12px; width:110px; cursor:pointer;}
		.bttcn{color:black;font-weight:bold;}
		.relativeTag{     
		z-index:10;   
		position:relative;     
		top:expression(this.offsetParent.scrollTop);     
		}
		.table1{
		width:100%;height:20%;border-collapse:collapse;border-spacing:0; 
		}
		</style>
		<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
		<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
 
		<script type="text/javascript">
			var path = '<%=path%>';
			//查询,导出
   			function queryDegree(action){
				var command=action;
            	if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
	                 alert("城市不能为空");
	   			}else{
					var reg = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
	   				var bzyfstart = document.form1.bzyfstart.value;
	   				var bzyfend = document.form1.bzyfend.value;
					if(bzyfstart!=""&&bzyfstart!=null&&reg.exec(bzyfstart)){//判断报账月份起
						if(bzyfend!=""&&bzyfend!=null&&reg.exec(bzyfend)&&bzyfend>=bzyfstart){//判断报账月份止
               				document.form1.action=path+"/servlet/UnitAveShiServlet?command="+command;                       
               				document.form1.submit();
               				if("chaxun"==command){//查询则显示等待页面
               				showdiv("请稍等..............");
               				}
                   		}else{
                   			alert("报账月份(止)输入有误！");
                   		}
                	}else{
						alert("报账月份(起)输入有误！");
					}
      			}
    		}
   
		//查询详细信息
    		function information(dbid,dlid){  	
    			window.open(path+"/web/query/basequery/showdfxx.jsp?dbid="+dbid+"&dlid="+dlid,'','width=500,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
    		}



		//=点击展开关闭效果=
			function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
				var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
				var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
				var openTip = oOpenTip || "";
				var shutTip = oShutTip || "";
				if(targetObj.style.display!="none"){
   					if(shutAble) return;
   					targetObj.style.display="none";
   					if(openTip  &&  shutTip){
    					sourceObj.innerHTML = shutTip; 
   					}
				}else {
   					targetObj.style.display="block";
   					if(openTip  &&  shutTip){
    					sourceObj.innerHTML = openTip; 
   					}
				}
			}	
			$(function(){    
				$("#query").click(function(){
				queryDegree("chaxun");
				});
				$("#daochuBtn").click(function(){
				queryDegree("daochu");
				});
 				$("#queding").click(function(){
				queding();		
				});
				$("#quxiao").click(function(){
				quxiao();		
				});
			});
		</script>
	
	</head>
	<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
	<body class="body">
		<form action="" name="form1" method="POST">
	    	<table class ="table1">
	 			<tr>
      				<td colspan="4" width="50">
						<div style="width:700px;height:50px">
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">地市单价平均与平均单价</span>	
						</div>
					</td>
    			</tr>
				<tr>
    				<td height="20" colspan="4">
    					<div id="parent" style="display:inline">
       					 	<div style="width:50px;display:inline;"><hr></div>
       					 	<font size="2">过滤条件</font>
       					 	<div style="width:300px;display:inline;"><hr></div>
        				</div>
        			</td>
    			</tr>
  				<tr>
  					<td width="1200px">
						<table>
							<tr class="form_label">
								<td title="必填项，根据下拉值选择填写">城市：</td>
		    					<td title="必填项，根据下拉值选择填写">
		    						<select name="shi" class="selected_font" id="shi" onchange="changeCity()">
	         							<option value="0">请选择</option>
	         							<%
			         						ArrayList shilist = new ArrayList();
			         						shilist = commBean.getAgcode(sheng,account.getAccountId());
			         						if(shilist != null){
			         							String agcode = "",agname = "";
			         							int scount = ((Integer)shilist.get(0)).intValue();
			         							int size = shilist.size()-1;
			         							int i;
			         							for(i = scount;i < size;i += scount){
		                    							agcode = (String)shilist.get(i + shilist.indexOf("AGCODE"));
		                    							agname = (String)shilist.get(i + shilist.indexOf("AGNAME"));
		                    			%>
		                    			<option value="<%=agcode%>"><%=agname%></option>
		                    			<%		
		                    					}
			         						}
			   							%>
	         						</select>
	         						<span style="color: #FF0000;font-weight: bold">*</span>
	         					</td>
	         					
	         					<td>区县：  </td>          
                     			<td> 
                     				<select name="xian" class="selected_font" onchange="changeCountry()">
         		                         <option value="0">请选择</option>
         		 						<%
		         							ArrayList xianlist = new ArrayList();
		         							xianlist = commBean.getAgcode(shi,account.getAccountId());
		         							if(xianlist != null){
		         								String agcode = "",agname = "";
		         								int scount = ((Integer)xianlist.get(0)).intValue();
		         								int size = xianlist.size()-1;
		         								int i;
		         								for(i = scount;i < size;i += scount){
	                    							agcode = (String)xianlist.get(i + xianlist.indexOf("AGCODE"));
	                    							agname = (String)xianlist.get(i + xianlist.indexOf("AGNAME"));
	                    				%>
	                                     <option value="<%=agcode%>"><%=agname%></option>
	                    				<%		}
		         							}
		         						%>
         	                           </select>   
								</td>        
			                     
								
								<td>供电方式：</td>
                       			<td>
                       				<select name="gdfs"  class="selected_font">
		         						<option value="0">请选择</option>
		         							<%
			         							ArrayList gdfsb = new ArrayList();
			         							gdfsb = ztcommon.getSelctOptions("GDFS");
			         							if(gdfsb != null){
			         								String code = "",name = "";
			         								int cscount = ((Integer)gdfsb.get(0)).intValue();
			         								int size = gdfsb.size() - 1;
			         								int i;
			         								for(i = cscount;i < size;i += cscount){
		                    							code = (String)gdfsb.get(i + gdfsb.indexOf("CODE"));
		                    							name = (String)gdfsb.get(i + gdfsb.indexOf("NAME"));
		                    				%>
		                    			<option value="<%=code%>"><%=name%></option>
		                    				<%
		                    						}
			         							}
			        	 					%>
    	
    		         				</select>
    		         			</td>	
    		         			
								 <td>
									站点属性：
								</td>
								<td>
									<select name="jzproperty" class="selected_font"
										onchange="zdsxCheck(this.value)">
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


									</select>
								</td>
	         					<td>
							    	<div id="query" style="position:relative;width:59px;height:23px;right:-65px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          	</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
   				<tr>
					<td colspan="8">
						<table>
							<tr class="form_label">
                            			
                            			<td>自动审核状态：</td>
                                      <td>
                                      <select name="autoauditstatus" id="zd" class="selected_font">
       		                        	<option value="5">请选择</option>
       		                        	<option value="1">通过</option>
       		                        	<option value="0">不通过</option>
       	                          	</select></td>
		         
                            			<td> 人工审核状态：</td>            
       	                          	<td>
       	                          		<select name="manauditstatus" class="selected_font">
       		                        		<option value="5">请选择</option>
       		                         		<option value="0">人工未审核</option>
       		                         		<option value="1">人工通过</option>
       		                         		<option value="-2">人工不通过</option>
       		                         		<option value="2">财务通过</option>
       		                        		<option value="-1">财务未通过</option>
       	                          		</select>
       	                          	</td>  
  								
	    						<td>
									区县审核状态：
								</td>
								<td>
									<select name="countryheadstatus" class="selected_font">
										<option value="5">
											请选择
										</option>
										<option value="0">
											未审核
										</option>
										<option value="1">
											通过
										</option>
										<option value="2">
											不通过
										</option>
									</select>
								</td>	
							</tr>	
  								<tr class="form_label">
								<td> 市级审核状态：</td>
                            			<td>
									<select name="citystatus" class="selected_font">
       		                           		<option value="5">请选择</option>
       		                           		<option value="0">未审核</option>
       		                           		<option value="1">通过</option>
       		                            	<option value="-2">不通过</option>
                                   		</select>
                           	 		</td>
                           	 		
		                        <td>
									市主任审核状态：
								</td>
								<td>
									<select name="cityzrauditstatus" class="selected_font">
										<option value="5">
											请选择
										</option>
										<option value="0">
											未审核
										</option>
										<option value="1">
											通过
										</option>
										<option value="2">
											不通过
										</option>
									</select>
								</td>
								
								<td title="必填项，根据下拉值选择填写">报账月份：</td>
								<td title="必填项，根据下拉值选择填写">
                            			<input type="text" class="selected_font" name="bzyfstart"
                                    readonly="readonly" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/>
                                </td>
                                <td title="必填项，根据下拉值选择填写" width="10px">至</td>
                                <td title="必填项，根据下拉值选择填写">
                                    <input type="text" class="selected_font" name="bzyfend"
                                    readonly="readonly" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/>
                                    <span style="color: #FF0000;font-weight: bold">*</span>
                            	</td>
                             </tr>
                      	</table>
					</td>
    			</tr>
			</table>
	        <table  height=23>
				<tr>
					<td height="20"  colspan="4">
						<div id="parent" style="display:inline">
                     		<div style="width:50px;display:inline;"><hr></div>
                     		<font size="2">&nbsp;信息列表&nbsp;</font>
                     		<div style="width:300px;display:inline;"><hr></div>
                		</div>
                	</td>
               	</tr>
			</table>
			<div style="width:100%;height:250px;overflow-x:auto;overflow-y:auto;border:1px" > 	
				<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
         			<tr height = "23" bgcolor="#DDDDDD" class="relativeTag ">
         				<td width="" height="26" ><div align="center" class="bttcn">序号</div></td>
            			<td width="" height="26" ><div align="center" class="bttcn">城市</div></td>
            			<td width="" height="26" ><div align="center" class="bttcn">区县</div></td>
            			<td width="" height="26" ><div align="center" class="bttcn">报账月份</div></td>
            			<td width="" height="26" ><div align="center" class="bttcn">至报账月份</div></td>
            			<td width="" height="26" ><div align="center" class="bttcn">单价平均</div></td>
            			<td width="" height="26" ><div align="center" class="bttcn">平均单价</div></td>
            			<td width="" height="26" ><div align="center" class="bttcn">单价平均趋势</div></td>
            			<td width="" height="26" ><div align="center" class="bttcn">平均单价趋势</div></td>
       	 			</tr>
	
					<c:forEach items="${list}"  var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       						<td><div align="center" >${status.count}</div></td>  
       						<td><div align="center" >${list.shi}</div></td>  
       						<td><div align="center" >${list.xian}</div></td>  
       						<td><div align="center" >${list.bzyfstart}</div></td>  
       						<td><div align="center" >${list.bzyfend}</div></td>  
       						<td><div align="center" >${list.danjiapj}</div></td>  
       						<td><div align="center" >${list.pingjundj}</div></td>  
       						<td><div align="center" >${list.danjiapjqs}</div></td>  
       						<td><div align="center" >${list.pingjundjqs}</div></td>  
    					</tr>
					</c:forEach>
					<% 
						int i = 0;
						int j = 0;//f用于循环
						if (numcolor==0){
							for(i=0;i<14;i++){
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
        			</tr>
        			<% 
        					}
						}
					%>
  	 			</table> 
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
	<script type="text/javascript">
		var path = '<%=path%>';
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
		//指定响应函数
		function sendRequest(url,para){
			createXMLHttpRequest();
			XMLHttpReq.open("GET", url, false);
			if(para=="sheng"){
				XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
			}else if(para=="shi"){
				XMLHttpReq.onreadystatechange = processResponse_shi;
			}else if(para=="xian"){
				XMLHttpReq.onreadystatechange = processResponse_xian;
			}else{
				XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
			}
		  
			XMLHttpReq.send(null);  
		}
	
		// 处理返回信息函数
    	function processResponse(){
    		if (XMLHttpReq.readyState == 4){ // 判断对象状态
        		if (XMLHttpReq.status == 200){ // 信息已经成功返回，开始处理信息
            		var res = XMLHttpReq.responseText;
              		window.alert(res);
            	}else{//页面不正常
                	window.alert("您所请求的页面有异常。");
            	}
        	}
    	}
    	//改变省的响应，更新市
		function processResponse_sheng() {
    		if (XMLHttpReq.readyState == 4){ // 判断对象状态
        		if (XMLHttpReq.status == 200){ // 信息已经成功返回，开始处理信息
            		var res=XMLHttpReq.responseXML.getElementsByTagName("res");
             		 updateShi(res);        
            	}else{ //页面不正常
                	window.alert("您所请求的页面有异常。");
           	 	}
        	}
   	 	}
		//改变市的响应，更新区县
		function processResponse_shi(){
			if(XMLHttpReq.readyState == 4){ // 判断对象状态
    			if(XMLHttpReq.status == 200){ // 信息已经成功返回，开始处理信息
    				
        			var res=XMLHttpReq.responseXML.getElementsByTagName("res");
    			
          			updateQx(res);
        		}else{//页面不正常
            		window.alert("您所请求的页面有异常。");
        		}
    		}
		}

		//改变省
		function changeSheng(){
			var sid = document.form1.sheng.value;
			if(sid=="0"){
				var shilist = document.all.shi;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");
			}
		}
		//更新市
		function updateShi(res){
			var shilist = document.all.shi;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		//改变城市
		function changeCity(){
			var sid = document.form1.shi.value;
			if(sid=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
			}
		}
		//更新区县
		function updateQx(res){
			var shilist = document.all.xian;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		//改变区县
		function changeCountry(){
			var sid = document.form1.xian.value;
			if(sid=="0"){
				var shilist = document.all.xiaoqu;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
			}
		}
		
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.gdfs.value='<%=gdfs%>';
		document.form1.jzproperty.value='<%=jzproperty%>';
		document.form1.autoauditstatus.value='<%=autoauditstatus%>';
		document.form1.manauditstatus.value='<%=manauditstatus%>';
		document.form1.countryheadstatus.value='<%=countryheadstatus%>';
		document.form1.citystatus.value='<%=citystatus%>';
		document.form1.cityzrauditstatus.value='<%=cityzrauditstatus%>';
		document.form1.bzyfstart.value='<%=bzyfstart%>';
		document.form1.bzyfend.value='<%=bzyfend%>';
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
</html>
