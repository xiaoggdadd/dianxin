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
String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";//乡镇
String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"请选择";//站点类型
String zdshuxing = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"请选择";//站点属性
String zdlx1 = request.getParameter("zdlx1")!=null?request.getParameter("zdlx1"):"";//站点类型,隐藏域
String zdsx1 = request.getParameter("zdsx1")!=null?request.getParameter("zdsx1"):"";//站点属性,隐藏域
String autoauditstatus = request.getParameter("autoauditstatus")!=null?request.getParameter("autoauditstatus"):"";//自动审核状态
String manauditstatus = request.getParameter("manauditstatus")!=null?request.getParameter("manauditstatus"):"";//人工审核状态
String citystatus = request.getParameter("citystatus")!=null?request.getParameter("citystatus"):"";//市级审核状态
String gdfstj = request.getParameter("gdfs")!=null?request.getParameter("gdfs"):"0";//供电方式
String dbqyzt = request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"-1";//电表启用状态
String qyzt = request.getParameter("qyzt")!=null?request.getParameter("qyzt"):"-1";//站点启用状态
String countryheadstatus = request.getParameter("countryheadstatus")!=null?request.getParameter("countryheadstatus"):"5";//区县审核状态
String sjzrsh = request.getParameter("sjzrsh")!=null?request.getParameter("sjzrsh"):"-1";//市级主任审核
String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";//报账月份起始月
String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";//报账月份结束月

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
		
		#div2{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
		ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
		#div2 p{float:left;font-size:12px; width:110px; cursor:pointer;}
		
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
//            	if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
//	                 alert("城市不能为空");
//	   			}else{
	   				var zdid=document.form1.zdid.value;
	   				var blhdl1=document.form1.blhdl1.value;
	   				var dianfeidd=document.form1.dianfeidd.value;
                	var re=/(^\d+([.]\d+$))|(^\d+$)/;
					var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
	   				var et = document.form1.entryTime1.value;
					if(!isNaN(zdid)){
					if(reg1.exec(et)||et==""||et==null){//录入月份判断
						if(re.exec(blhdl1)||blhdl1==null||blhdl1==""){//实际用电量
							if(re.exec(dianfeidd)||dianfeidd==null||dianfeidd==""){//电费
                   				document.form1.action=path+"/servlet/ElectricDetail?command="+command;                       
                   				document.form1.submit();
                   				if("chaxun"==command){//查询则不显示等待页面
                   				showdiv("请稍等..............");
                   				}
                   			}else{
                	   			alert("您输入的电费有误，请确认后重新输入！");
                   			}
                   		}else{
                   			alert("您输入的实际用电量有误，请确认后重新输入！");
                   		}
                	}else{
						alert("您输入的录入月份有误，请确认后重新输入！");
					}
					}else{
						alert("您输入的站点ID有误，请确认后重新输入！");
					}
//       			}
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
				$("#queding2").click(function(){
				queding2();		
				});
				$("#quxiao2").click(function(){
				quxiao2();		
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
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电费查询</span>	
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
								<td> 乡镇：</td>         
								<td>
									<select name="xiaoqu" class="selected_font">
         		                    	<option value="0">请选择</option>
         								<%
		         							ArrayList xiaoqulist = new ArrayList();
		         							xiaoqulist = commBean.getAgcode(xian,account.getAccountId());
		         							if(xiaoqulist!=null){
		         								String agcode = "",agname = "";
		         								int scount = ((Integer)xiaoqulist.get(0)).intValue();
		         								int size = xiaoqulist.size()-1;
		         								int i;
		         								for(i = scount;i < size;i += scount){
	                    							agcode = (String)xiaoqulist.get(i + xiaoqulist.indexOf("AGCODE"));
	                    							agname = (String)xiaoqulist.get(i + xiaoqulist.indexOf("AGNAME"));
	                    				%>
	                                     <option value="<%=agcode%>"><%=agname%></option>
	                    				<%
	                   							}
		         							}
		         						%>
									</select>
								</td>
	         					<td class="form_label">	
								
									
							 			<div title="您可以进行详细的条件筛选" id="query1"  
							 			onclick="openShutManager(this,'box3',false)"     
							 			style="position:relative;width:17px;height:17px;cursor: pointer;">
											<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
											<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          		</div>
							
									
								
								</td>
	         					<td>
							    	<div id="query" style="position:relative;width:59px;height:23px;right:-250px;cursor: pointer;TOP:0PX">
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
						<div style="width:99%;" > 
					  		<p id="box3" style="display:none">
								<table>
									<tr class="form_label">
										<td> 市级审核状态：</td>
                              			<td>
											<select name="citystatus" class="selected_font">
         		                           		<option value="2">请选择</option>
         		                           		<option value="0">未通过</option>
         		                           		<option value="1">通过</option>
         		                            	<option value="-2">不通过</option>
                                     		</select>
                             	 		</td> 
                              			<td>开始录入时间：</td>
										<td><input type="text" class="selected_font" name="entrytimeQS" value="<%if(null!=request.getParameter("entrytimeQS")) out.print(request.getParameter("entrytimeQS")); %>" 
													readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /></td>                 
                            			<td>结束录入时间：</td>
                        				<td><input type="text" class="selected_font" name="entrytimeJS" value="<%if(null!=request.getParameter("entrytimeJS")) out.print(request.getParameter("entrytimeJS")); %>" 
                        							readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /> </td>       
                        				<td> 人工审核状态：</td>            
         	                          	<td>
         	                          		<select name="manauditstatus" class="selected_font">
         		                        		<option value="3">请选择</option>
         		                         		<option value="1">人工通过</option>
         		                         		<option value="2">财务通过</option>
         		                         		<option value="0">人工未通过</option>
         		                         		<option value="-2">人工不通过</option>
         		                        		<option value="-1">财务未通过</option>
         	                          		</select>
         	                          		</td>  
									</tr>	
    								<tr class="form_label">
                                    	<td>自动审核状态：</td>
                                        <td>
                                        <select name="autoauditstatus" id="zd" class="selected_font">
         		                        	<option value="2">请选择</option>
         		                        	<option value="1">通过</option>
         		                        	<option value="0">未通过</option>
         	                          	</select></td>
         	                          	<!-- 
         	                          	<td> 报账月份：</td>
                              			<td>
                              				<input type="text" class="selected_font" name="bztime" value="<%if (null != request.getParameter("bztime"))out.print(request.getParameter("bztime"));%>"
		                                    onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
                              			</td>
                              			 -->
                              			 <td>电表启用状态：</td>
                       					<td>
                       						<select name="dbqyzt" id="db" class="selected_font" >
         		             					<option value="-1">请选择</option>
         		             					<option value="1">是</option>
         		              					<option value="0">否</option>
         	                				</select>
         	                			</td>
                              			 <td>
											站点属性：
										</td>
										<td>
										<div id="div2">
			      							<p><input type="text" class="selected_font" readonly= "readonly" name="jzproperty" value="请选择"/></p>
			      							<ul>
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
												 <li><input type="checkbox" name="CheckboxGroup2" value="<%=code%>,<%=name%>" id="CheckboxGroup2" /><%=name%></li>
												<%}
													}
												%>
												<li><input type="button" name="queding2" id="queding2"value="确定" /><input type="button" name="quxiao2" id="quxiao2" value="取消" /></li>
											 </ul>
                         				</div></td>
										<%--<td>
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
                              			 
										--%><td>
											站点类型：
										</td>
										<td>
											<div id="div1">
			      							<p><input type="text" class="selected_font" readonly= "readonly" name="zdlx" value="请选择"/></p>
			      							<ul>
												<%
													ArrayList stationtype = new ArrayList();
												stationtype = "".equals(zdsx1)?null:ztcommon.getZdlx2(zdsx1);
													//stationtype = ztcommon.getZdlx(zdshuxing);
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
															
												%>
												 <li><input type="checkbox" name="CheckboxGroup1" value="<%=code%>,<%=name%>" id="CheckboxGroup1" /><%=name%></li>
												<%}
													}
												%>
												<li><input type="button" name="queding" id="queding"value="确定" /><input type="button" name="quxiao" id="quxiao" value="取消" /></li>
											 </ul>
                         				</div>	
									</td>
								</tr>
                               	<tr class="form_label">
									<td>站点名称：</td>
                            		<td><input type="text" name="zdname" class="selected_font" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>"/></td>
                           			<td>站点ID：</td>
                            		<td><input type="text" name="zdid" class="selected_font" value="<%if(null!=request.getParameter("zdid")) out.print(request.getParameter("zdid")); %>"/></td>
                            		<td>录入月份：</td>
                        			<td><input type="text" name="entryTime1" class="selected_font"  value="<%if (null != request.getParameter("entryTime1")) out.print(request.getParameter("entryTime1"));%>" 
                        						readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /></td>
                            		<td>录入人员：</td><td><input type="text" class="selected_font" name="entrypensonnel" value="<%if(null!=request.getParameter("entrypensonnel")) out.print(request.getParameter("entrypensonnel")); %>" /></td>
                             	</tr>
                           		<tr class="form_label">
                             		<td>站点启用状态：</td>
                       				<td>
                       				<select name="qyzt" id="zd" class="selected_font" >
         		              			<option value="-1">请选择</option>
         		             			<option value="1">是</option>
         		              			<option value="0">否</option>
         	                		</select>
         	                		</td>
                           			<td>电费支付类型：</td>
                       				<td>
                       					<select name="dfzflx"  class="selected_font" >
		         							<option value="0">请选择</option>
		         								<%
			         								ArrayList dfzflxa = new ArrayList();
			         								dfzflxa = ztcommon.getSelctOptions("DFZFLX");
			         								if(dfzflxa!=null){
			         									String code="",name="";
			         									int cscount = ((Integer)dfzflxa.get(0)).intValue();
			         									int size = dfzflxa.size()-1;
			         									int i;
			         									for(i = cscount;i < size;i += cscount){
		                    								code = (String)dfzflxa.get(i + dfzflxa.indexOf("CODE"));
		                    								name = (String)dfzflxa.get(i + dfzflxa.indexOf("NAME"));
		                    					%>
		                    				<option value="<%=code%>"><%=name%></option>
		                    					<%
		                    							}
			         								}
			         							%>
    	
    		         					</select>
    		         				</td>	
                       				<td> 流程号：</td>
                       				<td><input type="text" name="liuch" value="<%if(null!=request.getParameter("liuch")) out.print(request.getParameter("liuch")); %>" class="selected_font"/></td>
                         			<td>耗电量大于</td>
                        			<td><input type="text" id="blhdl1" name="blhdl1" class="selected_font" value="<%if(null!=request.getParameter("blhdl1")) out.print(request.getParameter("blhdl1")); %>">度</td>
                          		</tr>
 								<tr class="form_label">
                         			<td>电费大于</td>
                        			<td><input type="text" id="dianfeidd" name="dianfeidd" class="selected_font" value="<%if(null!=request.getParameter("dianfeidd")) out.print(request.getParameter("dianfeidd")); %>">元</td>
                           		<td>供电方式：</td>
                       			<td>
                       				<select name="gdfs"  class="selected_font" >
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
    		          			<td> 财务月份：</td>
                      			<td>
                				<input type="text" class="selected_font" name="KJYF" value="<%if (null != request.getParameter("KJYF"))out.print(request.getParameter("KJYF"));%>"
		                        		readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="form" />
                              	</td> 
                       			<td>电表名称：</td>
                       			<td><input type="text" name="dbname" class="selected_font" value="<%if(null != request.getParameter("dbname")) out.print(request.getParameter("dbname")); %>"/>
         	          
    		         </tr>
    		         
								
                               	<tr class="form_label">
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
									
         	                			<td>市主任审核状态：</td>
                       					<td>
                       						<select name="sjzrsh" id="sjzrsh" class="selected_font" >
         		             					<option value="-1">请选择</option>
         		             					<option value="0">未审核</option>
         		             					<option value="1">审核通过</option>
         		              					<option value="2">审核不通过</option>
         	                				</select>
         	                			</td>
         	                			<td >报账月份：</td>
		    				<td colspan="3">
								<input type="text" name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" 
										readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font"/>
								至
	         					<input type="text" name="endTime" value="<%if(null!=request.getParameter("endTime")) out.print(request.getParameter("endTime")); %>" 
	         							readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font"/>
	         					<span class="style1">&nbsp;</span>
	         				</td>
                               	</tr>
                        		</table>
                        	</p>
						</div>
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
				<table width="5800px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
         			<tr height = "23" bgcolor="#DDDDDD" class="relativeTag ">
            			<td width="1%" height="23" ><div align="center" class="bttcn">序号</div></td>
            			<td width="1%" height="23" ><div align="center" class="bttcn">城市</div></td>
             			<td width="1.8%" height="23"><div align="center" class="bttcn">区县</div></td>
             			<td width="1.8%" height="23"><div align="center" class="bttcn">乡镇</div></td>
            			<td width="2.5%" height="23"><div align="center" class="bttcn">站点名称</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">电表名称</div></td>
             			<td width="1.8%" height="23"><div align="center" class="bttcn">电费支付类型</div></td>
           				<td width="1.8%" height="23"><div align="center" class="bttcn">报账月份</div></td> 
             			<td width="1.8%" height="23"><div align="center" class="bttcn">财务月份</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">供电方式</div></td>  
            			<td width="1.9%" height="23"><div align="center" class="bttcn">起始电表数</div></td>
  						<td width="1.9%" height="23"><div align="center" class="bttcn">结束电表数</div></td>  			
  						<td width="1.9%" height="23"><div align="center" class="bttcn">上次抄表时间</div></td> 
            			<td width="1.9%" height="23"><div align="center" class="bttcn">本次抄表时间</div></td>
            			<td width="1.9%" height="23"><div align="center" class="bttcn">报账用电量</div></td>
            			<td width="1.9%" height="23"><div align="center" class="bttcn">电表用电量</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">用电量调整</div></td>
             			<td width="1.8%" height="23"><div align="center" class="bttcn">(电量调整)备注</div></td>     
             			<td width="1.8%" height="23"><div align="center" class="bttcn">额定耗电量</div></td>     
              			<td width="1.8%" height="23"><div align="center" class="bttcn">全省定标电量</div></td>   
            			<td width="1.8%" height="23"><div align="center" class="bttcn">本次单价</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">电费调整</div></td>
             			<td width="1.8%" height="23"><div align="center" class="bttcn">(电费调整)备注</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">报账电费</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">结算周期</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">本地标电费</div></td>
              			<td width="1.8%" height="23"><div align="center" class="bttcn">省定标电费</div></td>  
              			<td width="1.8%" height="23"><div align="center" class="bttcn">超省定标电费额</div></td>   
            			<td width="1.8%" height="23"><div align="center" class="bttcn">直流负荷</div></td> 
             			<td width="1.8%" height="23"><div align="center" class="bttcn">交流负荷</div></td>
            			<td width="2%" height="23"><div align="center" class="bttcn">录入时间</div></td>             
  						<td width="1.8%" height="23"><div align="center" class="bttcn">录入人员</div></td> 
            			<td width="1.8%" height="23"><div align="center" class="bttcn">自动审核状态</div></td>
  						<td width="1.8%" height="23"><div align="center" class="bttcn">人工审核状态</div></td>
  						<td width="2%" height="23"><div align="center" class="bttcn">人工审核时间</div></td>
  						<td width="1.8%" height="23"><div align="center" class="bttcn">人工审核员</div></td>
  						<td width="2%" height="23"><div align="center" class="bttcn">区县主任审核状态</div></td>
  						<td width="2%" height="23"><div align="center" class="bttcn">区县主任审核时间</div></td>
  						<td width="2%" height="23"><div align="center" class="bttcn">区县主任审核员</div></td>    			
  						<td width="2%" height="23"><div align="center" class="bttcn">市级审核状态</div></td>
  						<td width="2%" height="23"><div align="center" class="bttcn">市级审核时间</div></td>
  						<td width="2%" height="23"><div align="center" class="bttcn">市级审核员</div></td>
  						<td width="2%" height="23"><div align="center" class="bttcn">市主任审核状态</div></td>
  						<td width="2%" height="23"><div align="center" class="bttcn">市主任审核时间</div></td>
  						<td width="1.8%" height="23"><div align="center" class="bttcn">市主任审核员</div></td>
  						<td width="1.8%" height="23"><div align="center" class="bttcn">财务审核状态</div></td>
  						<td width="2%" height="23"><div align="center" class="bttcn">财务审核时间</div></td>  			
  						<td width="1.8%" height="23"><div align="center" class="bttcn">财务审核员</div></td> 
            			<td width="1.8%" height="23"><div align="center" class="bttcn">倍率</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">站点属性</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">站点类型</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">站点别名</div></td> 
            			<td width="1.8%" height="23"><div align="center" class="bttcn">流程号</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">票据编号</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">票据类型</div></td>            
  						<td width="1.8%" height="23"><div align="center" class="bttcn">站点ID</div></td>
  						<td width="1.8%" height="23"><div align="center" class="bttcn">电表ID</div></td>
  						<td width="1.8%" height="23"><div align="center" class="bttcn"> 自报电用户号</div></td>
       	 				<td width="1.8%" height="23"><div align="center" class="bttcn">电量调整*倍率</div></td>
                        <td width="1.8%" height="23"><div align="center" class="bttcn">上期电表用电量</div></td>
                        <td width="1.8%" height="23"><div align="center" class="bttcn">上期电量调整*倍率</div></td>
                        <td width="1.8%" height="23" ><div align="center" class="bttcn">线变损电量</div></td>
                     	<td width="1.8%" height="23" ><div align="center" class="bttcn">报账日均电量</div></td>
                        <td width="1.8%" height="23"><div align="center" class="bttcn">倍率</div></td>
                        <td width="1.8%" height="23" ><div align="center" class="bttcn">电费超市标比例</div></td>
                        <td width="1.8%" height="23"><div align="center" class="bttcn">电费超省标比例</div></td>
                        <td width="1.8%" height="23" ><div align="center" class="bttcn">生产标</div></td>
                        <td width="1.8%" height="23"><div align="center" class="bttcn">合并周期</div></td>
       	 			</tr>
	
					<c:forEach items="${list}"  var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       						<td><div align="center" >${status.count}</div></td>  
       						<td><div align="center" >${list.shi}</div></td>  
       						<td><div align="center" >${list.xian}</div></td>
       						<td><div align="center" >${list.xiaoqu}</div></td>     		
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.dbid}','${list.electricfeeid}')">${list.jzname}</a></div></td> 
							<td><div align="center">${list.dbname}</div></td> 
       		  				<td><div align="center" >${list.dfzflx}</div></td>   
       		 				<td><div align="center" title="流程查询"><a href="javascript:liuchenginfo('${status.count}')">${list.accountmonth}</a></div>
       		 					<input type="hidden" name = "dbid" value = "${list.dbid}"/>
       		 					<input type="hidden" name = "dfid${status.count}" value = "${list.electricfeeid}"/>
       		 				</td>
       		 				<td><div align="center" >${list.kjyf}</div></td>
       				 		<td><div align="center" >${list.gdfs}</div></td>
        					<td><div align="right" >${list.lastdegree}</div></td>
       		 				<td><div align="right" >${list.thisdegree}</div></td>
							<td><div align="center" >${list.lastdatetime}</div></td>
       		  				<td><div align="center" >${list.thisdatetime}</div></td>
       						<td><div align="right" >${list.blhdl}</div></td>   
       						<td><div align="right" >${list.dbydl}</div></td>   	
       						<td><div align="right" >${list.floatdegree}</div></td> 
       						<td><div align="center" >${list.memo}</div></td> 
       						<td><div align="right" >${list.edhdl}</div></td> 
       						<td><div align="right" >${list.qsdbdl}</div></td> 
       						<td><div align="right" >${list.unitprice}</div></td> 
       						<td><div align="right" >${list.floatpay}</div></td> 
       						<td><div align="center" >${list.memo1}</div></td> 
       						<td><div align="right" >${list.actualpay}</div></td>
       						<td><div align="center" >${list.cycle}</div></td>  
       						<td><div align="right" >${list.rated}</div></td> 
        					<td><div align="right" >${list.sdbdf}</div></td> 
       						<td><div align="right" >${list.csdbdfe}</div></td> 
       						<td><div align="center" >${list.zlfh}</div></td>  
       						<td><div align="right" >${list.jlfh}</div></td>
       						<td><div align="center" >${list.entrytime}</div></td>    
       						<td><div align="center" >${list.entrypensonnel}</div></td> 
       						<td><div align="center" >${list.autoauditstatus}</div></td>        		           		          
       		 				<td><div align="center" >${list.manualauditstatus}</div></td>   
       						<td><div align="center" >${list.manualauditdatetime}</div></td> 
       						<td><div align="center" >${list.manualauditname}</div></td> 
       						<td><div align="center" >${list.countyauditstatus}</div></td>   
       						<td><div align="center" >${list.countyaudittime}</div></td> 
       						<td><div align="center" >${list.countyauditname}</div></td> 
       						<td><div align="center" >${list.cityaudit}</div></td>  
       						<td><div align="center" >${list.cityaudittime}</div></td>  
       						<td><div align="center" >${list.cityauditpensonnel}</div></td> 
       						<td><div align="center" >${list.cityzrauditstatus}</div></td>  
       						<td><div align="center" >${list.cityzraudittime}</div></td>  
       						<td><div align="center" >${list.cityzrauditname}</div></td> 
       						<td><div align="center" >${list.financialstatus}</div></td>          
            				<td><div align="center" >${list.financialdatetime}</div></td>
            				<td><div align="center" >${list.financialoperator}</div></td>
       						<td><div align="right" >${list.beilv}</div></td> 
       						<td><div align="center" >${list.zdsx}</div></td>
       						<td><div align="center" >${list.stationtype}</div></td> 
       		 				<td><div align="center" >${list.bieming}</div></td> 
	       		 			<td><div align="center" >${list.liuchenghao}</div></td>  
       						<td><div align="center">${list.noteno}</div></td> 
       						<td><div align="center" >${list.notetypeid}</div></td>  
       						<td><div align="center" >${list.id}</div></td> 
       		 				<td><div align="center" >${list.dbid}</div></td>  
       		 				<td><div align="center" >${list.dbzbdyhh}</div></td>
       		 				<td><div align="center" >${list.floatdegreeandbl}</div></td>
							<td><div align="center" >${list.lastactualdegree}</div></td>
							<td><div align="center" >${list.lastfloatdegreeandbl}</div></td>
							<td><div align="center" >${list.lineandchangeandbl}</div></td>
							<td><div align="center" >${list.bzrj}</div></td>
							<td><div align="center" >${list.beilv}</div></td>
							<td><div align="center" >${list.dedhdl}%</div></td>
							<td><div align="center" >${list.csdb}%</div></td>
							<td><div align="center" >${list.scb}</div></td>
							<td><div align="center" >${list.hbzq}</div></td>
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
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
        			</tr>
        			<% 
        					}
						}
					%>
	 				<tr>
        				<td align="center">合计</td>
        				<td colspan="2" align="center">电量合计：</td>
      					<td><font size="2">${totalelectric}度 </font></td>
      					<td colspan="2" align="center">电费合计：</td>
      					<td><font size="2">${totalmoney}元 </font></td>
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
       			<tr>
  					<td><input type="hidden" name="zdlx1" id="zdlx1" value="<%=zdlx1%>"/>
  					<input type="hidden" name="zdsx1" id="zdsx1" value="<%=zdsx1%>"/>
  					</td>
  					<input type="hidden" name="ccz" id="ccz" value="<%=zdlx1%>"/>
  				</tr>
			</table>
		</form>
	</body>
	<script type="text/javascript">
		var path = '<%=path%>';
			//流程查询
			function liuchenginfo(statusi){
				document.form1.attributes["target"].value = "_blank";
				document.form1.attributes["action"].value = path+"/servlet/LiuChengInfo?command=liuchenginfo&statusi="+ statusi;  
                document.form1.submit();
			}
	</script>
	
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
		//更新乡镇
		function updateZd(res){
			var shilist = document.all.xiaoqu;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		//载入执行，站点属性，站点类型的加载
		var bSwitch2=false;
		var bSwitch=false;
		window.onload=function(){
			var oDiv2=document.getElementById('div2');
			var oUl2=oDiv2.getElementsByTagName('ul')[0];
			var oP2=oDiv2.getElementsByTagName('p')[0];
			oP2.onclick=function(){
				if(bSwitch2){
					oUl2.style.display='none';
					bSwitch2=false;
				}else{
					var obj=document.getElementsByName("CheckboxGroup2");//根据自己的多选框名称修改下
					var zdlx2 =document.form1.zdsx1.value;//获取隐藏域等站点属性值
					if(zdlx2!=""&&zdlx2!=null){
						var zdlx3 = zdlx2.split(",");
						for(var i=0;i<obj.length;i++){
							var m = obj[i].value.toString().indexOf(",");
							var bm = obj[i].value.toString().substring(0,m);
							for(var j=0;j<zdlx3.length;j++){
								var zdlx4 = zdlx3[j].toString().substring(1,zdlx3[j].length-1);
								if(bm==zdlx4){
									obj[i].checked = true; 					
								}
							} 
						} 
					} 
					oUl2.style.display='block';
					bSwitch2=true;
				}
			};
			
			var oDiv=document.getElementById('div1');
			var oUl=oDiv.getElementsByTagName('ul')[0];
			var oP=oDiv.getElementsByTagName('p')[0];
			oP.onclick=function(){
				if(bSwitch){
					oUl.style.display='none';
					bSwitch=false;
				}else{
					var obj=document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
					var zdlx2 = document.form1.zdlx1.value;//获取隐藏域等站点类型值
					if(zdlx2!=""&&zdlx2!=null){
						var zdlx3 = zdlx2.split(",");
						for(var i=0;i<obj.length;i++){
							var m = obj[i].value.toString().indexOf(",");
							var bm = obj[i].value.toString().substring(0,m);
							for(var j=0;j<zdlx3.length;j++){
								var zdlx4 = zdlx3[j].toString().substring(1,zdlx3[j].length-1);
								if(bm==zdlx4){
									obj[i].checked = true; 					
								}
							} 
						} 
					} 
					oUl.style.display='block';
					bSwitch=true;
				}
			};

		};
		//确定选中(站点类型)
		function queding(){
			var oDiv=document.getElementById('div1');
			var oUl=oDiv.getElementsByTagName('ul')[0];
			var obj=document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
			var str1="";
			var str2="";
			for(var i=0;i< obj.length;i++){
				if(obj[i].checked){
					var m = obj[i].value.toString().indexOf(",");
					var bm = obj[i].value.toString().substring(0,m);
					var mc = obj[i].value.toString().substring(m+1,obj[i].value.toString().length);
					str1 = str1+"'"+bm+"',";
					str2 = str2+mc+",";
				}
			}
			str1=str1.substring(0,str1.length-1);
			str2=str2.substring(0,str2.length-1);
			document.form1.zdlx1.value=str1;
			
			if(str2==""){
				str2="请选择";
			}
			document.form1.zdlx.value=str2;
			oUl.style.display='none';
			bSwitch=false;
		}
		//取消选中 (站点类型) 
		function quxiao(){ 
			var oDiv=document.getElementById('div1');
			var oUl=oDiv.getElementsByTagName('ul')[0];  
			var obj = document.getElementsByName("CheckboxGroup1");
			if(obj.length){
				for(var i=0;i<obj.length;i++){ 
					obj[i].checked = false;   
				}
				oUl.style.display='none';   
			}else{   
				obj.checked = false; 
				oUl.style.display='none';   
			}  
			document.form1.zdlx.value="请选择";
			document.form1.zdlx1.value="";
			bSwitch=false;
		} 
				//确定选中(站点属性)
		function queding2(){
			var oDiv=document.getElementById('div2');
			var oUl=oDiv.getElementsByTagName('ul')[0];
			var obj=document.getElementsByName("CheckboxGroup2");//根据自己的多选框名称修改下
			var str1="";
			var str2="";
			for(var i=0;i< obj.length;i++){
				if(obj[i].checked){
					var m = obj[i].value.toString().indexOf(",");
					var bm = obj[i].value.toString().substring(0,m);
					var mc = obj[i].value.toString().substring(m+1,obj[i].value.toString().length);
					str1 = str1+"'"+bm+"',";
					str2 = str2+mc+",";
				}
			}
			str1=str1.substring(0,str1.length-1);
			str2=str2.substring(0,str2.length-1);
			if(str2==""){
				str2="请选择";
			}
			document.form1.zdsx1.value=str1;
			document.form1.jzproperty.value=str2;
			zdsxCheck(str1);
			oUl.style.display='none';
			bSwitch2=false;
		}
		//取消选中 (站点属性) 
		function quxiao2(){ 
			zdsxCheck("");
			var oDiv=document.getElementById('div2');
			var oUl=oDiv.getElementsByTagName('ul')[0];  
			var obj = document.getElementsByName("CheckboxGroup2");
			if(obj.length){
				for(var i=0;i<obj.length;i++){ 
					obj[i].checked = false;   
				}
				oUl.style.display='none';   
			}else{   
				obj.checked = false; 
				oUl.style.display='none';   
			}  
			document.form1.jzproperty.value="请选择";
			document.form1.zdsx1.value="";
			bSwitch2=false;
		} 
		
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.zdlx.value='<%=zdlx%>';
		document.form1.zdlx1.value=document.form1.ccz.value;
		document.form1.autoauditstatus.value='<%=autoauditstatus%>';
		document.form1.manauditstatus.value='<%=manauditstatus%>';
		document.form1.citystatus.value='<%=citystatus%>';
		document.form1.gdfs.value='<%=gdfstj%>';
		document.form1.dbqyzt.value='<%=dbqyzt%>';
		document.form1.qyzt.value='<%=qyzt%>';
		document.form1.jzproperty.value='<%=zdshuxing%>';
		document.form1.countryheadstatus.value='<%=countryheadstatus%>';
		document.form1.sjzrsh.value='<%=sjzrsh%>';
		document.form1.beginTime.value='<%=beginTime%>';
		document.form1.endTime.value='<%=endTime%>';
</script>
<script type="text/javascript">
//站点属性
function zdsxCheck(obj) {
	var sid = obj;
	document.form1.zdlx1.value="";
	//if (sid == "") {
	//	sendRequest2(path+"/servlet/garea?action=zdsxnull&pid=StationType","jzproperty");
	//} else {
		sendRequest2(path + "/servlet/garea?action=zdsxa&pid=" + sid,
				"jzproperty");
	//}

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

//function updateZdlx(res) {
//	alert(123456)
//	var shilist = document.all.zdlx;
//	shilist.options.length = "0";
//	shilist.add(new Option("请选项", "0"));
//
//	for ( var i = 0; i < res.length; i += 2) {
//		shilist.add(new Option(res[i + 1].firstChild.data,
//				res[i].firstChild.data));
//	}
//}
function updateZdlx(res) {
	var str="<p><input type='text' class='selected_font' readonly= 'readonly' name='zdlx' value='请选择'/></p><ul>";
		for ( var i = 0; i < res.length; i += 2) {
		str+="<li><input type='checkbox' name='CheckboxGroup1' value='"+res[i].firstChild.data+","+res[i + 1].firstChild.data+"' id='CheckboxGroup1' />"+res[i + 1].firstChild.data+"</li>";
	}
		str+="<li><input type='button' onclick='queding();' value='确定' /><input type='button'  onclick='quxiao();'  value='取消' /></li></ul>";
	//	alert(str)
document.getElementById("div1").innerHTML=str;
//	
var oDiv=document.getElementById('div1');
			var oUl=oDiv.getElementsByTagName('ul')[0];
			var oP=oDiv.getElementsByTagName('p')[0];
			bSwitch=false;
			oP.onclick=function(){
				if(bSwitch){
					oUl.style.display='none';
					bSwitch=false;
				}else{
					oUl.style.display='block';
					bSwitch=true;
				}
			};
}
</script>
</html>
