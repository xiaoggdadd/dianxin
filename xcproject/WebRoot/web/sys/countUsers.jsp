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

//颜色
int numcolor = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;//条数 ,查出数据的条数，用于颜色设置
String color = "";//颜色
%>
<html>
	<head>
		<title>
		用户数查询
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
		a {
		text-decoration:none;
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
   			function query(){
	   			var shi=document.form1.shi.value;
                document.form1.action=path+"/servlet/CountUsersServlet?shi="+shi;    
                document.form1.submit();
                showdiv("请稍等..............");  
    		}
   
		//查询详细信息
    		function information(shi,zwshi,zhiwu,flag){  
			var shi2 = encodeURIComponent(shi);
			var zwshi2 = encodeURIComponent(zwshi);
			var zhiwu2 = encodeURIComponent(zhiwu);
			if( flag==null || flag=="" || flag==" " ||flag=="0"){
				alert("无详细信息");
			}else{
    			window.open(path+"/servlet/CountUsersInfoServlet?shi="+shi2+"&zwshi="+zwshi2+"&zhiwu="+zhiwu2,''
    			     ,'width=500,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
    			}
    		}
			

			$(function(){    
				$("#query").click(function(){
				query();
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
							
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">用户数查询</span>	
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
	         					<td>
							    	<div id="query" style="position:relative;width:59px;height:23px;right:-10px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          	</div>
								</td>
                             </tr>
                        </table>
					</td>
    			</tr>
			</table>
				
			<div style="width:100%;overflow-x:auto;overflow-y:auto;border:1px" > 	
				<table width="100%" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr bgcolor="#7293b0">
						<td width="" height="23" rowspan="2"><div align="center" class="bttcn"><font color="#FFFFFF">序号</font></div></td>
            			<td width="" height="23" rowspan="2"><div align="center" class="bttcn"><font color="#FFFFFF">城市</font></div></td>
            			<td width="" height="23" rowspan="2"><div align="center" class="bttcn"><font color="#FFFFFF">账户数</font></div></td>
             			<td 		height="23" colspan="16"><div align="center" class="bttcn"><font color="#FFFFFF">角色</font></div></td>
					</tr>
         			<tr>
             			<td width="" height="23"><div align="center" class="bttcn">管理员</div></td>
            			<td width="" height="23"><div align="center" class="bttcn">业务岗</div></td>
            			<td width="" height="23"><div align="center" class="bttcn">市审核</div></td>
             			<td width="" height="23"><div align="center" class="bttcn">市分析</div></td>
           				<td width="" height="23"><div align="center" class="bttcn">市录入</div></td> 
             			<td width="" height="23"><div align="center" class="bttcn">市决策</div></td>
            			<td width="" height="23"><div align="center" class="bttcn">市系统</div></td>  
            			<td width="" height="23"><div align="center" class="bttcn">市财务</div></td>  
            			<td width="" height="23"><div align="center" class="bttcn">省决策</div></td>
  						<td width="" height="23"><div align="center" class="bttcn">省分析</div></td>  			
  						<td width="" height="23"><div align="center" class="bttcn">省系统</div></td> 
            			<td width="" height="23"><div align="center" class="bttcn">省财务</div></td>
            			<td width="" height="23"><div align="center" class="bttcn">区县录入</div></td>
            			<td width="" height="23"><div align="center" class="bttcn">区县管理</div></td>
            			<td width="" height="23"><div align="center" class="bttcn">区县财务</div></td>
             			<td width="" height="23"><div align="center" class="bttcn">区县分析</div></td> 
       	 			</tr>
					<c:forEach items="${list}"  var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       						<td><div align="center" >${status.count}</div></td>  
       						<td><div align="center" >${list.zwshi}</div></td>  
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','全部','${list.count}')">${list.count}</a></div></td>
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','管理员','${list.guanly}')">${list.guanly}</a></div></td>
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','业务岗','${list.yewg}')">${list.yewg}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','市审核','${list.shishh}')">${list.shishh}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','市分析','${list.shifx}')">${list.shifx}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','市录入','${list.shilr}')">${list.shilr}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','市决策','${list.shijc}')">${list.shijc}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','市系统','${list.shixt}')">${list.shixt}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','市财务','${list.shicw}')">${list.shicw}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','省决策','${list.shengjc}')">${list.shengjc}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','省分析','${list.shengfx}')">${list.shengfx}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','省系统','${list.shengxt}')">${list.shengxt}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','省财务','${list.shengcw}')">${list.shengcw}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','区县录入','${list.qxlr}')">${list.qxlr}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','区县管理','${list.qxgl}')">${list.qxgl}</a></div></td> 
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','区县财务','${list.qxcw}')">${list.qxcw}</a></div></td>  
       						<td><div align="center" title="详细信息"><a href="#" onclick="information('${list.shi}','${list.zwshi}','区县分析','${list.qxfx}')">${list.qxfx}</a></div></td>  
    					</tr>
					</c:forEach>
					<% 
						int i = 0;
						int j = 0;//f用于循环
						if (numcolor==0){
							for(i=0;i<18;i++){
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
            	
            		</tr>
      				<% 
							}
						}else if(!(numcolor > 17)){
    	  					int n = (numcolor-1)%17;
							for(j=n;j<17;j++){
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
            			
        			</tr>
        			<% 
        					}
						}
					%>
  	 			</table> 
			</div>
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

		document.form1.shi.value='<%=shi%>';
</script>
</html>
