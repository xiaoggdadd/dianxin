<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@page import="com.noki.mobi.common.CommonBean"%>
<%
	String path = request.getContextPath();
	String sheng = (String) session.getAttribute("accountSheng");
	
	Account account = (Account) session.getAttribute("account");
	String dfmc = (String) request.getParameter("dfmc");
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

	if (dfmc != null && !"".equals(dfmc)) {
	
	} else {
		dfmc = "";
	}
	
	String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
	String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
	String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
	String str="";
	if(shi != null && !shi.equals("")&& !shi.equals("0")){		
		str=str+" and Z.shi='"+shi+"'";
	}
	
	if(xian != null && !xian.equals("")&& !xian.equals("0")){		
		str=str+" and Z.xian='"+xian+"'";
	}
	
	if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){		
		str=str+" and Z.xiaoqu='"+xiaoqu+"'";
	}
	
	if(zdlx != null && !zdlx.equals("")&& !zdlx.equals("0")){
		
		str=str+" and Z.STATIONTYPE='"+zdlx+"'";
	}
	
	String loginId = account.getAccountId();
	String roleId = account.getRoleId();
	String accountid = account.getAccountName();
	String canshuStr="";
	 if((shi!=null)||(xian!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian;
     }
%>
<html>
	<head>
			<title>站点关键信息提交修改功能</title>
		<style>
			.style1 {
				color: #FF9900;
				font-weight: bold;
			}
			.form {
				height: 19px;
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
					line-height:100%;
			}
			.STYLE6 {
				color: #FFFFFF;
			}
			.memo {
				border: 1px #C8E1FB solid
			}
			.style7 {
				font-weight: bold
			}
			#id1 {
				BORDER-RIGHT: #2C59AA 1px solid;
				PADDING-RIGHT: 2px;
				BORDER-TOP: #2C59AA 1px solid;
				PADDING-LEFT: 2px;
				FONT-SIZE: 12px;
				FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
					StartColorStr = #ffffff, EndColorStr = #D7E7FA );
				BORDER-LEFT: #2C59AA 1px solid;
				CURSOR: hand;
				COLOR: black;
				PADDING-TOP: 2px;
				BORDER-BOTTOM: #2C59AA 1px solid
			}
			body {
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
			}
		</style>
				<script src="<%=path%>/javascript/PopupCalendar.js">
		</script>
				<script src="<%=path%>/javascript/PopupCalendar_ny.js">
		</script>
				<script src="<%=path%>/javascript/jquery-1.4.2.js">
		</script>
				<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
		</script>
		<script language="javascript">
				var path = '<%=path%>';
				function changeMC() {
					var stationname = document.form1.stationname.value;
					
					var c=stationname.split(",");
					var zdid=c[0];
					var dbid=c[1];
					var bzw=c[2];
				
					var Text01 = document.form1.stationname.options[document.form1.stationname.selectedIndex].text;
					var text = document.getElementById('dfmc');
					if (stationname == "") {
						alert("不能为空！");
						return
				    }else{ 
			        	text.value=Text01;
			        	var b=path+"/web/zdqxkz/zdgjxxxgxx.jsp?zdid="+zdid+"&dbid="+dbid+"&bzw="+bzw+"&bz1=1";
						var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";		
						$("#tmpTree").remove();
						$("body").append(a);
						$("#tmpTree")[0].click();
					}
		        }        
		       $(function(){
		        	$("#chaxun").click(function(){
			    		exportCheck();
		         	});
		       });
		       
		       function exportCheck(){          
		          document.form1.action=path+"/web/zdqxkz/zdgjxxxg.jsp?";      
		          document.form1.submit();
		       }		
		</script>
	</head>
<jsp:useBean id="Bean" scope="page"
	class="com.noki.zdqxkz.dao.Zdqxcxxg"></jsp:useBean>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body class="body" style="overflow-x: hidden;">
	<LINK href="../../images/css.css" type="text/css" rel=stylesheet>
	<form action="" name="form1" method="POST" id="form">
	<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
		<tr>   
	    <td valign="top">
			<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
							<tr><td colspan="8">
								<table width="100%"  border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td colspan="8"></td>
									</tr>					
									<tr>
									<td colspan="8">
										  <div style="width:700px;height:50px">
												<img src="<%=path%>/images/btt.bmp" width="100%" height="100%"/>
												<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点关键信息提交修改功能</span>
										  </div>
									</td>												
								   </tr>
							    </table>
							</td></tr>
							
							<tr><td bgcolor="F9F9F9" class="form_label">
								<img src="../../images/v.gif" width="15" height="15" />
								<font size="2">&nbsp;基本信息</font>							
							</td></tr>
							
							<tr>
					            <td colspan="8">
						         <table width="100%"  border="0" cellspacing="0" cellpadding="0" >
						         <tr>
									 <td height="49" bgcolor="#FFFFFF" colspan="8">
											<table width="100%" border="0" cellspacing="1" cellpadding="1" >									
												<tr class="form_label">
										    		<td class="form_label">城市：</td>			    		
										    		<td ><select name="shi" class="selected_font" onchange="changeCity()">
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
									         		
									         		<td class="form_label">区县：</td><td> <select name="xian" class="selected_font" onchange="changeCountry()">
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
																			
													<td class="form_label">乡镇：</td>
													<td><select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
									         		</select></td>
									         		<td class="form_label">站点类型：</td>
									         		<td><select name="zdlx" class="selected_font"/> 
												          <option class="form_label" value="0">请选择</option>
												          <%
													         	ArrayList stationtype = new ArrayList();
												         		stationtype = ztcommon.getSelctOptions("StationType");
													         	if(stationtype!=null){
													         		String code="",name="";
													         		int cscount = ((Integer)stationtype.get(0)).intValue();
													         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
												                    {
												                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
												                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
												                    %>
												                    <option value="<%=code%>"><%=name%></option>
												                    <%}
													         	}
													         %>
								                      </select></td>
									         		</tr>
									         	</table>
									 </td></tr>	
									 	
									<tr>
										<td colspan="8">
												<table width="100%" border="0">
													<tr class="selected_font">
														<td height="5%" bgcolor="#DDDDDD" width="15%" colspan="8">
															<div align="left"><font size="2">站点名称：</font></div>
														</td>
														
														<td>
															<div style="position: relative;">
																<span id="sp01"	style="margin-left: 230px; width: 18px; overflow: hidden;">
																		<select name="stationname"
																			style="width: 248px; margin-left: -230px;"
																			style="width:300" onchange="changeMC()">
																			<option value="0">
																				请选择
																			</option>
																			<%
																				ArrayList stationnamea = new ArrayList();
																				stationnamea = Bean.getStationMhchexk(loginId, dfmc,str);
																				String scode = "";
																				if (stationnamea != null) {
																					String name = "";
																					String zdid="";
																					String dbid="";
																					String bzw="";//标志位1为核标站点 0 为非核标站点
																					if (stationnamea.size() == 0) {																						
																			%>
	
																			<option value="0">
																				输入有误
																			</option>	
																			<%
																				} else {
																						int cscount = ((Integer) stationnamea.get(0)).intValue();
																						for (int i = cscount; i < stationnamea.size() - 1; i += cscount) {
																							name = (String) stationnamea.get(i+ stationnamea.indexOf("JZNAME"));
																						    zdid = (String) stationnamea.get(i+ stationnamea.indexOf("ID"));
																						    dbid=(String)stationnamea.get(i+ stationnamea.indexOf("DBID"));
																						    bzw=(String)stationnamea.get(i+ stationnamea.indexOf("BZW"));
																			%>
																			<option value="<%=zdid%>,<%=dbid%>,<%=bzw%>"><%=name%><%=dbid%></option>
																			<%
																				}
																					}
	
																				}
																			%>
																		</select> </span>
																	<%
																		if (dfmc != null && !"".equals(dfmc) && stationnamea.size() != 0
																				&& stationnamea.size() < 3) {																			
																	%>
																	<input type="text" name="dfmc"
																		style="width: 230px; position: absolute; left: 0px;"
																		value="<%=(String) stationnamea.get(2 + stationnamea.indexOf("JZNAME"))%>" />
																	<%
																		} else {
																	%>
																	<input type="text" name="dfmc" style="width: 230px; position: absolute; left: 0px;"	value="" />
																	<%
																		}
																	%>
	
																</div>	
															</td>
															
															<td>														 
																<div id="chaxun" style="position: relative;left: -300px;width: 60px; height: 23px">
																	<img alt="" src="<%=path%>/images/chaxun.png" width="100%" height="100%" />
																	<span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
																</div>
															</td>	
													 </tr>
												</table>
											</td>
										</tr>
	
										<tr>
											<td colspan="8">
												<iframe name="treeMap" width="100%" height="500px" scrolling="no" frameborder="0"  src="<%=path%>/web/electricfees/adddianfeidannei.jsp"></iframe>	
											</td>
										</tr>
									</table>
								</td>
							</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
		</form>
	</body>
	<script>
		var XMLHttpReq;
			//XMLHttpReq = createXMLHttpRequest();
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
				}
				else{
					XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
				}
				//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
				XMLHttpReq.send(null);  
			}
			/////////////////////////////////////////////////////////////
			// 处理返回信息函数
		    function processResponse() {
		    	
		    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		    		//alert(XMLHttpReq.status);
		        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
		            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
		            	var res = XMLHttpReq.responseText;
		              window.alert(res);
		             
		                       
		            } else { //页面不正常
		                window.alert("您所请求的页面有异常。");
		            }
		        }
		    }
		    
		function processResponse_sheng() {
		    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		    		
		        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
		            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
		            	
		            	//var res = XMLHttpReq.responseText;
		            
		              updateShi(res);
		                       
		            } else { //页面不正常
		                window.alert("您所请求的页面有异常。");
		            }
		        }
		    }
		
		function processResponse_shi() {
			
			if (XMLHttpReq.readyState == 4) { // 判断对象状态
				//alert(XMLHttpReq.status);
		    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
		        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
		        	//var res = XMLHttpReq.responseText;
		        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
		          updateQx(res);
		                   
		        } else { //页面不正常
		            window.alert("您所请求的页面有异常。");
		        }
		    }
		}
		function processResponse_xian() {
			
			if (XMLHttpReq.readyState == 4) { // 判断对象状态
				//alert(XMLHttpReq.status);
		    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
		        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
		        	//var res = XMLHttpReq.responseText;
		        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
		          updateZd(res);
		                   
		        } else { //页面不正常
		            window.alert("您所请求的页面有异常。");
		        }
		    }
		}
		
		function changeSheng(){
			var sid = document.form1.sheng.value;
			//document.form1.sheng2.value=document.form1.sheng.value;
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
			//document.form1.shi2.value=document.form1.shi.value;
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
			//document.form1.xian2.value=document.form1.xian.value;
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
	        document.form1.shi.value='<%=shi%>';
			document.form1.xian.value='<%=xian%>';
			document.form1.xiaoqu.value='<%=xiaoqu%>';
			document.form1.zdlx.value='<%=zdlx%>';
	</script>
</html>