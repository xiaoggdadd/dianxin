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
String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";//站点类型
String zdshuxing = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";//站点类型
String manauditstatus = request.getParameter("manauditstatus")!=null?request.getParameter("manauditstatus"):"3";//人工审核状态
String countryheadstatus = request.getParameter("countryheadstatus")!=null?request.getParameter("countryheadstatus"):"5";//区县审核状态
String citystatus = request.getParameter("citystatus")!=null?request.getParameter("citystatus"):"2";//市级审核状态
String sjzrsh = request.getParameter("sjzrsh")!=null?request.getParameter("sjzrsh"):"-1";//市级主任审核
String financialstatus = request.getParameter("financialstatus")!=null?request.getParameter("financialstatus"):"3";//财务审核状态 
String zdlx1 = request.getParameter("zdlx1")!=null?request.getParameter("zdlx1"):"";//站点类型,隐藏域
String dbqyzt = request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"-1";//电表启用状态
String qyzt = request.getParameter("qyzt")!=null?request.getParameter("qyzt"):"-1";//站点启用状态
String ftyf = request.getParameter("ftyf")!=null?request.getParameter("ftyf"):"";//分摊月份
//String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";//报账月份结束月

//颜色
int numcolor = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;//条数 ,查出数据的条数，用于颜色设置
String color = "";//颜色
%>
<html>
	<head>
		<title>
		合同单信息
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
		<script>
		var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
		oCalendarEnny.Init();
		var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
		oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
		oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
		oCalendarChsny.oBtnTodayTitle = "确定";
		oCalendarChsny.oBtnCancelTitle = "取消";
		oCalendarChsny.Init();
		</script>
		<script >
		var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
		oCalendarEn.Init();
		var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
		oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
		oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
		oCalendarChs.oBtnTodayTitle="今天";
		oCalendarChs.oBtnCancelTitle="取消";
		oCalendarChs.Init();
		</script>
		<script type="text/javascript">
			var path = '<%=path%>';
			//查询,导出
   			function queryDegree(command){
				
            	if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
	                 alert("城市不能为空");
	   			}else{
	   				
                   	document.form1.action=path+"/servlet/ContractBillServlet?command="+command;                       
                   	document.form1.submit();
                   	if("chaxun"==command){//查询则不显示等待页面
                   	showdiv("请稍等..............");
                   	}
    			}
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
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">合同单查询</span>	
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
										<td >分摊月份：</td>
		    							<td>
											<input type="text" name="ftyf" value="<%if(null!=request.getParameter("ftyf")) out.print(request.getParameter("ftyf")); %>" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font"/>
	         							</td>
	         							<td>站点名称：</td>
                            			<td><input type="text" name="zdname" class="selected_font" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>"/></td>
										<td>站点ID：</td>
                            			<td><input type="text" name="zdid" class="selected_font" value="<%if(null!=request.getParameter("zdid")) out.print(request.getParameter("zdid")); %>"/></td>
									</tr>	
    								<tr class="form_label">
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
											站点类型：
										</td>
										<td>
											<select name="zdlx" class="selected_font">
												<option value="0">
													请选择
												</option>
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
                           			<td>电表名称：</td>
                       				<td><input type="text" name="dbname" class="selected_font" value="<%if(null != request.getParameter("dbname")) out.print(request.getParameter("dbname")); %>"/></td>
								</tr>
                               	<tr class="form_label">
                            		<td>电表启用状态：</td>
                       				<td>
                       					<select name="dbqyzt" id="db" class="selected_font" >
         		             				<option value="-1">请选择</option>
         		             				<option value="1">是</option>
         		              				<option value="0">否</option>
         	                			</select>
         	                		</td>
                             		<td>站点启用状态：</td>
                       				<td>
                       				<select name="qyzt" id="zd" class="selected_font" >
         		              			<option value="-1">请选择</option>
         		             			<option value="1">是</option>
         		              			<option value="0">否</option>
         	                		</select>
         	                		</td>
         	                		<td> 人工审核状态：</td>            
         	                          	<td>
         	                          		<select name="manauditstatus" class="selected_font">
         		                        		<option value="3">请选择</option>
         		                         		<option value="0">未审核</option>
         		                         		<option value="1">人工通过</option>
         		                         		<option value="-2">人工不通过</option>
         	                          		</select>
         	                          	</td>  
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
									
									<td> 市级审核状态：</td>
                              		<td>
										<select name="citystatus" class="selected_font">
         		                           	<option value="2">请选择</option>
         		                           	<option value="0">未审核</option>
         		                           	<option value="1">通过</option>
         		                            <option value="-2">不通过</option>
                                     	</select>
                             	 	</td> 
									
         	                		<td>市主任审核状态：</td>
                       				<td>
                       					<select name="sjzrsh" id="sjzrsh" class="selected_font" >
         		             				<option value="-1">请选择</option>
         		             				<option value="0">未审核</option>
         		             				<option value="1">通过</option>
         		              				<option value="2">不通过</option>
         	                			</select>
         	                		</td>
                               	</tr>
                               	<tr>
         	                          	<td>财务审核状态：</td>            
         	                          	<td>
         	                          		<select name="financialstatus" class="selected_font">
         		                        		<option value="3">请选择</option>
         		                         		<option value="1">未审核</option>
         		                         		<option value="2">财务通过</option>
         		                        		<option value="-1">财务不通过</option>
         	                          		</select>
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
              			<td width="1.8%" height="23"><div align="center" class="bttcn">全省定标电量</div></td>   
             			<td width="1.8%" height="23"><div align="center" class="bttcn">额定耗电量</div></td>
             			<td width="1.8%" height="23"><div align="center" class="bttcn">合同编号</div></td>      
            			<td width="1.8%" height="23"><div align="center" class="bttcn">电表名称</div></td>
             			<td width="1.8%" height="23"><div align="center" class="bttcn">电费支付类型</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">单价</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">金额</div></td>
            			<td width="1.9%" height="23"><div align="center" class="bttcn">用电量</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">起始年月</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">结束年月</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">票据编号</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">票据类型</div></td>
           				<td width="1.8%" height="23"><div align="center" class="bttcn">报账月份</div></td> 
             			<td width="1.8%" height="23"><div align="center" class="bttcn">财务月份</div></td>
             			<td width="1.8%" height="23"><div align="center" class="bttcn">备注</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">倍率</div></td>
            			<td width="1.9%" height="23"><div align="center" class="bttcn">起始电表数</div></td>
  						<td width="1.9%" height="23"><div align="center" class="bttcn">结束电表数</div></td>  			
  						<td width="1.9%" height="23"><div align="center" class="bttcn">上次抄表时间</div></td> 
            			<td width="1.9%" height="23"><div align="center" class="bttcn">本次抄表时间</div></td>
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
            			<td width="1.8%" height="23"><div align="center" class="bttcn">站点属性</div></td>
            			<td width="1.8%" height="23"><div align="center" class="bttcn">站点类型</div></td>
  						<td width="1.8%" height="23"><div align="center" class="bttcn">站点ID</div></td>
  						<td width="1.8%" height="23"><div align="center" class="bttcn">电表ID</div></td>
  						<td width="1.8%" height="23"><div align="center" class="bttcn">流程号</div></td>
       	 			</tr>
	
					<c:forEach items="${list}"  var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       						<td><div align="center" >${status.count}</div></td>  
       						<td><div align="center" >${list.shi}</div></td>  
       						<td><div align="center" >${list.xian}</div></td>
       						<td><div align="center" >${list.xiaoqu}</div></td>     		
       						<td><div align="center" >${list.jzname}</div></td> 
       						<td><div align="right" >${list.qsdbdl}</div></td> 
       						<td><div align="right" >${list.edhdl}</div></td> 
       						<td><div align="center" >${list.htbianhao}</div></td> 
							<td><div align="center" >${list.dbname}</div></td>
       		  				<td><div align="center" >${list.dfzflx}</div></td>   
       						<td><div align="right" >${list.danjia}</div></td> 
       						<td><div align="center" >${list.money}</div></td>
       						<td><div align="center" >${list.dianliang}</div></td>
       						<td><div align="center" >${list.startmonth}</div></td> 
       						<td><div align="center" >${list.endmonth}</div></td> 
       						<td><div align="center">${list.noteno}</div></td> 
       						<td><div align="center" >${list.notetypeid}</div></td> 
       		 				<td><div align="center" >${list.accountmonth}</div></td>
       		 				<td><div align="center" >${list.kjyf}</div></td>
       						<td><div align="center" >${list.memo}</div></td> 
       						<td><div align="right" >${list.beilv}</div></td> 
        					<td><div align="right" >${list.lastdegree}</div></td>
       		 				<td><div align="right" >${list.thisdegree}</div></td>
							<td><div align="center" >${list.lastdatetime}</div></td>
       		  				<td><div align="center" >${list.thisdatetime}</div></td>
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
       						<td><div align="center" >${list.zdsx}</div></td>
       						<td><div align="center" >${list.stationtype}</div></td> 
       						<td><div align="center" >${list.id}</div></td> 
       		 				<td><div align="center" >${list.dbid}</div></td>
       		 				<td><div align="center" >${list.liuchenghao}</div></td>  
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
  					<td><input type="hidden" name="zdlx1" id="zdlx1" value=""/></td>
  					<input type="hidden" name="ccz" id="ccz" value="<%=zdlx1%>"/>
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
		//载入执行，站点类型的加载
		window.onload=function(){
			var oDiv=document.getElementById('div1');
			var oUl=oDiv.getElementsByTagName('ul')[0];
			var oP=oDiv.getElementsByTagName('p')[0];
			var bSwitch=false;
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
 	
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.zdlx.value='<%=zdlx%>';
		document.form1.zdlx1.value=document.form1.ccz.value;
		document.form1.citystatus.value='<%=citystatus%>';
		document.form1.manauditstatus.value='<%=manauditstatus%>';
		document.form1.financialstatus.value='<%=financialstatus%>';
		document.form1.dbqyzt.value='<%=dbqyzt%>';
		document.form1.qyzt.value='<%=qyzt%>';
		document.form1.jzproperty.value='<%=zdshuxing%>';
		document.form1.countryheadstatus.value='<%=countryheadstatus%>';
		document.form1.sjzrsh.value='<%=sjzrsh%>';
		document.form1.ftyf.value='<%=ftyf%>';

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
