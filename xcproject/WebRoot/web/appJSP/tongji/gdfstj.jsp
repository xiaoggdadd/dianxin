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
String citystatus = request.getParameter("citystatus")!=null?request.getParameter("citystatus"):"1";//市级审核状态
String dbqyzt = request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"-1";//电表启用状态
String qyzt = request.getParameter("qyzt")!=null?request.getParameter("qyzt"):"-1";//站点启用状态
String fysx = request.getParameter("fysx")!=null?request.getParameter("fysx"):"2";//费用属性
String jzproperty = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";//站点属性

//颜色
int numcolor = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;//条数 ,查出数据的条数，用于颜色设置
String color = "";//颜色
%>
<html>
	<head>
		<title>
		电费信息
		</title>
		<style>
		.style1{
		color: red;
		font-weight: bold;
		}
		.form_label{
			text-align: left;
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
		<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
		<script src="<%=path%>/javascript/PopupCalendar.js"></script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
		</script>
		<script type="text/javascript">
			var path = '<%=path%>';
			//查询,导出
   			function queryDegree(action){
				var command=action;
				var reg = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
				var bzyfstart = document.form1.bztime.value;
   				var bzyfend = document.form1.jstime.value;
				if(bzyfstart!=""&&bzyfstart!=null&&reg.exec(bzyfstart)){//判断报账月份起
					if(bzyfend!=""&&bzyfend!=null&&reg.exec(bzyfend)&&bzyfend>=bzyfstart){//判断报账月份止
				document.form1.action=path+"/servlet/GdfsServlet?command="+command;                       
                   				document.form1.submit();
                   				if("chaxun"==command){//查询则不显示等待页面
                   				showdiv("请稍等..............");
                   				}
                   	}else{
                  			alert("报账月份（止）不能为空，格式要正确！");
                  		}
               	}else{
					alert("报账月份(起)不能为空，格式要正确！");
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
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">供电方式省级查询</span>	
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
								<td>城市：</td>
		    					<td>
		    						<select name="shi" class="selected_font" >
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
	         					</td>
	         						<td>报账月份：</td>
                              			<td>从
                              				<input type="text" class="selected_font" name="bztime" value="<%if (null != request.getParameter("bztime"))out.print(request.getParameter("bztime"));%>"
		                                    readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="form" />
                              			</td> 	
                              				<td>至
                              				<input type="text" class="selected_font" name="jstime" value="<%if (null != request.getParameter("jstime"))out.print(request.getParameter("jstime"));%>"
		                                    readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="form" />
                              			<span><font color="red">*</font></span>
                              			</td>
                              			
                              		<td> 审核状态：</td>
                              			<td>
											<select name="citystatus" class="selected_font">
         		                           		<option value="1">业务审核通过</option>
         		                            	<option value="2">财务审核通过</option>
                                     		</select>
                             	 		</td>	 
         	                		
	         					<td>
							    	<div id="query" style="position:relative;width:59px;height:23px;right:-70px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          	</div>
								</td>
							</tr>
							<tr class="form_label">
								<td>站点启用状态：</td>
                       				<td>
                       				<select name="qyzt" id="zd" class="selected_font" >
         		              			<option value="-1">全部</option>
         		             			<option value="1">启用</option>
         		              			<option value="0">关闭</option>
         	                		</select>
         	                		</td>
         	                		<td>电表启用状态：</td>
                       					<td>
                       						<select name="dbqyzt" id="db" class="selected_font" >
         		             					<option value="-1">全部</option>
         		             					<option value="1">启用</option>
         		              					<option value="0">关闭</option>
         	                				</select>
         	                			</td>
         	                			
										<td>站点属性：
											<select name="jzproperty" class="selected_font" >
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
										<td>费用属性</td>
										<td>
											<select name ="fysx" class="selected_font">
											<option value="2">请选择</option>
											<option value='0'>收入</option>
											<option value='1'>支出</option>
											</select>
										
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
			<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 	
				<table width="1095px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
         			<tr height = "23" bgcolor="#DDDDDD" class="relativeTag ">
            			<td width="5%" height="23" ><div align="center" class="bttcn">序号</div></td>
             			<td width="5%" height="23"><div align="center" class="bttcn">城市</div></td>
             			<td width="10%" height="23"><div align="center" class="bttcn">总站点数量</div></td>
            			<td width="11%" height="23"><div align="center" class="bttcn">直供电站点数量</div></td>
            			<td width="11%" height="23"><div align="center" class="bttcn">转供电站点数量</div></td>
             			<td width="10%" height="23"><div align="center" class="bttcn">总金额(万元)</div></td>
           				<td width="10%" height="23"><div align="center" class="bttcn">直供电金额(万元)</div></td> 
             			<td width="10%" height="23"><div align="center" class="bttcn">转供电金额(万元)</div></td>
            			<td width="11%" height="23"><div align="center" class="bttcn">直供电电量(万度)</div></td>  
            			<td width="11%" height="23"><div align="center" class="bttcn">转供电电量(万度)</div></td>
       	 			</tr>
	
					<c:forEach items="${list}"  var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       						<td><div align="center" >${status.count}</div></td>  
       						<td><div align="center" >${list.shi}</div></td>
       						<td><div align="right" >${list.zdslsum}</div></td>     		
							<td><div align="right">${list.zgdsum}</div></td> 
       		  				<td><div align="right" >${list.zhgdsum}</div></td>   
       		 				<td><div align="right" >${list.moneysum}</div></td>
       		 				<td><div align="right" >${list.zgdmoneysum}</div></td>
       				 		<td><div align="right" >${list.zhgdmoneysum}</div></td>
        					<td><div align="right" >${list.zgddlsum}</div></td>
       		 				<td><div align="right" >${list.zhgddlsum}</div></td>
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
        			</tr>
        			<% 
        					}
						}
					%>
	 				<tr bgcolor="<%=color%>">
        				<td align="center" colspan="2">合计</td>
        				<td align="center">${zdzlhj}</td>
        				<td align="center">${zgdzdhj}</td>
        				<td align="center">${zhgdzdhj}</td>
        				<td align="center">${moneyhj}</td>
        				<td align="center">${zgdmoneyhj}</td>
        				<td align="center">${zhgdmoneyhj}</td>
        				<td align="center">${zgddlhj}</td>
        				<td align="center">${zhgddlhj}</td>
        			</tr>
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
			XMLHttpReq.open("GET", url, true);
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
		//改变区县的响应，更新乡镇
		function processResponse_xian(){
			if(XMLHttpReq.readyState == 4){ // 判断对象状态
    			if(XMLHttpReq.status == 200){// 信息已经成功返回，开始处理信息
        			var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          			updateZd(res);
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
	
	
	
		document.form1.shi.value='<%=shi%>';
		document.form1.citystatus.value='<%=citystatus%>';
		document.form1.dbqyzt.value='<%=dbqyzt%>';
		document.form1.qyzt.value='<%=qyzt%>';
		document.form1.jzproperty.value='<%=jzproperty%>';
		document.form1.fysx.value='<%=fysx%>';
		
	
</script>
</html>
