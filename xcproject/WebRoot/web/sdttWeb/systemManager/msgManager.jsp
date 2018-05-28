<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean" %>
<%@ page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />

<script>
	<%
    String path = request.getContextPath();  //获取路经  
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String color = null;
    String xxlx=request.getParameter("xxlx")!=null?request.getParameter("xxlx"):"0";
   	System.out.println(xxlx+"1111111111");
   	CommonBean commBean =new CommonBean();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
    int count=0,pagesize=10,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
   	String permissionRights="";         
%>
</script>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
</head>
<body>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">系统消息管理</div>
				<div class="content01_1">
					<table width="100%" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr >
							<td>
							消息类型&nbsp;
							<select name="xxlx" id="xxlx" style="box-sizing: border-box; width: 130px;">
								<option  value="0">请选择</option>
								<option value="xxtype01">通知公告</option>
								<option value="xxtype02">工作提醒</option>
								<option value="xxtype03">提示警告</option>
							</select>&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						
						<tr >
							<td align="center">
								<input name="button2" type="submit" class="btn_c1" id="button2" onclick="query()" value="查询" />&nbsp; 
							</td>
						</tr>
						
					</table>
					
					<div class="tbtitle01"><b>系统消息管理</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
									<tr align="right">
									     <td colspan="6" align="right" valign="middle">
											<input name="button2" type="button" class="btn_c1" onclick="addjz()"  id="button2" value="新增" />&nbsp; 
										      <input name="button2" type="button" class="btn_c1" onclick="daochu()" id="button2" value="导出Excel" />&nbsp; 
										</td>
									</tr>
									
									<tr align="center">
									      <th width="10px">序号</th>
									      <th width="80px">编号</th>
									      <th width="80px">消息类型</th>
									      <th width="160px">标题</th>
									      <th width="220px">内容</th>
										  <th>操作</th>
									</tr>
											<%
								 SiteManage bean = new SiteManage();
						       	 ArrayList fylist = new ArrayList();
						         fylist = bean.getxx(curpage,loginName,loginId,xxlx);
						       	 DecimalFormat df1 = new DecimalFormat("0.00");
						       	 allpage=bean.getAllPage();
		 	
								 String xxtype="",name="", nr = "",bt = "",id = "" ;
								
								 int intnum = pagesize*(curpage-1)+1;
								 
								 if(fylist!=null)
										{
											int fycount = ((Integer)fylist.get(0)).intValue();
											for( int k = fycount;k<fylist.size()-1;k+=fycount){
						
						       			
						       				id = (String) fylist.get(k + fylist.indexOf("ID"));
						       		
						       				xxtype = (String) fylist.get(k + fylist.indexOf("XXTYPE"));
						       			       			
						       				name = (String) fylist.get(k + fylist.indexOf("NAME"));
						       				nr = (String) fylist.get(k + fylist.indexOf("NR"));
						       				bt = (String) fylist.get(k + fylist.indexOf("BT"));
						       				
						       					if (xxtype == null)
						       						xxtype = "";
						       					if (name == null)
						       						name = "";
						       					if (nr == null)
						       						nr = "";
						       					if (bt == null)
						       						bt = "";
						       					if (intnum % 2 == 0) {
						       						color = "#DDDDDD";
						
						       					} else {
						       						color = "#FFFFFF";
						       						}
						
						       			
						      					 %>
      
												<!-- 数据加载  Start-->
												<tr align="center">
													<td width="10"><%=intnum++%></td>
													<td align="center"><%=xxtype%></td>
													<td align="center"><%=name%></td>							
													<td align="left"><%=bt%></td>
													<td align="left"><%=nr%></td>
													<td width="80">
														<a href="#" onclick="update('<%=id%>')"><img src="../images/accept.png" width="16" height="16"  title="修改" /></a>
														<a href="#" onclick="delzd('<%=id%>')"><img src="../images/delete.png" width="16" height="16"  title="删除" /></a>
													</td>
												</tr><% } %>
			 
												<!-- 数据加载 End -->
												
												<tr bgcolor="#ffffff">
													<td colspan="10" align="left" >
													<div align="left">
										 				&nbsp;&nbsp;
										 				<font color='#1E90FF'>
									    				 <%
									     						if (curpage != 1) {
									     							out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
									     						} else {
									     							out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
									     							}
									     				  %>
								     					</font>
								     					&nbsp;&nbsp;
										 				<font color='#1E90FF'>
									     				  <%
									     					if (curpage != 1) {
									     					out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
									     					} else {
									     					out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
									     						}
									    				   %>
									   					</font>
									   					&nbsp;&nbsp;
									 						  转到 <select name="page" onchange="javascript:gopagebyno(document.form1.page.value)" style="width:40px;font-family: 宋体;font-size:12px;line-height:120%;" >
								     					  <%
								     							for (int i = 1; i <= allpage; i++) {
								     								if (curpage == i) {
								     									out.print("<option value='" + i
								     									+ "' selected='selected'>" + i + "</option>");
								     								} else {
								     									out.print("<option value='" + i + "'>" + i
								     										+ "</option>");
								     									}
								     								}
								     						%>
				     											</select>&nbsp;&nbsp;共 <font color='#1E90FF'><b><%=allpage%></b>&nbsp;</font>页 &nbsp;&nbsp;
																	<font color='#1E90FF'>
						    								 			<%
						     											if (allpage != 0 && (curpage < allpage)) {
						     												out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");
						     											} else {
						     												out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");
						     											 }
						    											%>
             														</font>
																&nbsp;&nbsp;
																							
																	<font color='#1E90FF'>
					    												 <%
					     													if (allpage != 0 && (curpage < allpage)) {
					     														out.print("<a href='javascript:gopagebyno(" + allpage+ ")'><img src='../images/page-last.gif'></a>");
					     													} else {
					     														out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
					     													 }
					    												 %>
           															 </font>
           														 &nbsp;&nbsp;


									</div>
								</td>
							</tr>							
					</table>
							<div class="space_h_10"></div>
							</div>
						</div>
					</td>
				</tr>
					<% } %>						
	</table>
						<div class="space_h_10"></div>
</form>

<script>
var path = '<%=path%>';
function gopage()
     {
      document.form1.submit();
     }
     
function previouspage()
     {
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else{
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage - 1)%>';
      	document.form1.action=path+"/web/sdttWeb/systemManager/msgManager.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     
function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      	 document.form1.action=path+"/web/sdttWeb/systemManager/msgManager.jsp?curpage="+curpage;
         document.form1.submit();
     }
     
function gopagebyno(pageno)
    {
      document.form1.page.value = pageno;   
      document.form1.action=path+"/web/sdttWeb/systemManager/msgManager.jsp?curpage="+pageno;
      document.form1.submit();
    }
    
function delzd(id){
    if(confirm("您确定删除此公告信息？")){
      document.form1.action=path+"/servlet/zhandian?action=delsiteXiaoxi&id="+id;
      document.form1.submit();
      }
    }
    
function zlinfo(id){
    <%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
      document.form1.action=path+"/web/jizhan/zlinfo.jsp?id="+id;
      document.form1.submit();
    <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>		
    }

function modifyjz(id,intnum){
    alert(intnum);
  	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/modifysite.jsp?id="+id+"&nums="+intnum;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
       
    }    
    
function getValue(va,sql){
    var general =document.getElementById("general");
    var htmlsql =document.getElementById("htmlsql");
       general.value=va;
       htmlsql.value = sql;    
    }
    //页面载入方法
function op(){
    window.open(path+'/InquiryServlet?quiryTaeble=zhandian,ammeters,ammeterdegrees,electricfees&tab=zd,am,ad,ef&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no,scrollbars=yes');
    }

var XMLHttpReq;	
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
	if(para=="xxlx"){
		XMLHttpReq.onreadystatechange = processResponse_xxlx;}//指定响应函数
	else{
		XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		XMLHttpReq.send(null);  
	}

function processResponse() {	
    if (XMLHttpReq.readyState == 4) { // 判断对象状态
        if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            var res = XMLHttpReq.responseText;
            window.alert(res);            
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    
function processResponse_xxlx() {
    if (XMLHttpReq.readyState == 4) { // 判断对象状态   		
        if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            var res=XMLHttpReq.responseXML.getElementsByTagName("res");
     		updateShi(res);                 
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            updateQx(res);           
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}

function processResponse_xian() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            updateZd(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}

function changeSheng(){
	var sid = document.form1.sheng.value;
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
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	} else {
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
function addjz(){
	window.open("../systemManager/addmsgManager.jsp", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
}
function update(id){
	window.open("../systemManager/addmsgManager.jsp?id="+id+"&bz="+"1", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
}
function query(){
	document.form1.action=path+"/web/sdttWeb/systemManager/msgManager.jsp";
}

document.form1.xxlx.value="<%=xxlx%>";

function daochu(){
	
document.form1.action =  path + "/servlet/SysManger?bz=" +'xtxx';
document.form1.submit();
}

</script>
</body>
</html>



