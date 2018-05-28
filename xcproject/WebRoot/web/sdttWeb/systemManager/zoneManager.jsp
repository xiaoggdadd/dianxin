 <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.noki.mobi.common.Account" %>

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
    String agcode=request.getParameter("agcode")!=null?request.getParameter("agcode"):"";
    String agname=request.getParameter("agname")!=null?request.getParameter("agname"):"";
   
   	//CommonBean commBean =new CommonBean();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
    int count=0,pagesize=10,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
   	String permissionRights="";   
   	 String rgsh=request.getParameter("rgsh");//站点审核通过   首页传的值
    String rgsh2=request.getParameter("caiji");// 采集站点      
%>
</script>
</head>
<body>
<form action="" name="form1" method="post">
<ul class="tab">
  <li class="first"></li>
  <li class="cur"><a href="zoneManager.jsp">区域管理</a></li>
  <%-- <li><a href="department.jsp">部门管理</a></li>--%>
  <li><a href="department_pro.jsp">部门管理</a></li>
  <li class="end"></li>
</ul>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">区域管理</div>
				<div class="content01_1">
					<table width="100%" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr >
							<td>
							关键字：&nbsp;<input name="agname" type="text"  id="agname" value=""  style="box-sizing: border-box; width: 130px;"  />
							&nbsp;&nbsp;※ 关键字只包括区域名称
							</td>
						</tr>
						<tr >
							<td align="center">
								<input type="submit" class="btn_c1" onclick="query()" value="查询" />&nbsp; 
							</td>
						</tr>
					</table>
					
					<div class="tbtitle01"><b>区域查询一览</b></div>
						<table width="800px"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
									<tr align="right">
									     <td colspan="4" align="right" valign="middle">
											<input name="button2" type="submit" class="btn_c1" id="button2" onclick="addjz()"  value="新增" />&nbsp; 
										      <input name="button2" type="button" class="btn_c1" onclick="daochu()" id="button2" value="导出Excel" />&nbsp; 
										</td>
									</tr>
									<tr align="center">
									      <th width="10">序号</th>
									      <th>区域编号</th>
										  <th>区域名称</th>
										  <th>操作</th>
									</tr>
									
											<%
								 SiteManage bean = new SiteManage();
 						       	 ArrayList fylist = new ArrayList();
						         fylist = bean.getquyu(curpage,agcode,agname);
 						       	 DecimalFormat df1 = new DecimalFormat("0.00");
 						       	 allpage=bean.getAllPage();
						       	
								String AGCODE="",AGNAME="",id = "" ;
								
								int intnum = pagesize*(curpage-1)+1;
								System.out.println(intnum);
								 if(fylist!=null)
 									{
 									int fycount = ((Integer)fylist.get(0)).intValue();
 									for( int k = fycount;k<fylist.size()-1;k+=fycount){
						
						       			id = (String) fylist.get(k + fylist.indexOf("AGID"));
 						       			AGCODE = (String) fylist.get(k + fylist.indexOf("AGCODE"));
						       			       			
						       			AGNAME = (String) fylist.get(k + fylist.indexOf("AGNAME"));
						       			
 						       			if (AGCODE == null)
 						       				AGCODE = "";
 						       				
 						       				
 						       			if (AGNAME == null)
 						       				AGNAME = "";
 						       			
						       			if (intnum % 2 == 0) {
						       				color = "#DDDDDD";
						
						       			} else {
						       				color = "#FFFFFF";
						       			}
						
						       %>
      
												<!-- 数据加载  Start-->
												<tr align="center">
													<td width="10"><%=intnum++%></td>
													<td align="left"><%=AGCODE%></td>
													<td align="left"><%=AGNAME%></td>							
													
			 										
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
						
				</div>
			</div>
		</td></tr>
		<%  } %>						
								</table>
						<div class="space_h_10"></div>
		</form>
									
									

							
			<script>
	var path = '<%=path%>';
	 var rgsh='<%=rgsh%>';
	 var caiji='<%=rgsh2%>';
	function gopage()
     {
      document.form1.submit();
     }
     function previouspage()
     {
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage - 1)%>';
      		document.form1.action=path+"/web/sdttWeb/systemManager/zoneManager.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      		document.form1.action=path+"/web/sdttWeb/systemManager/zoneManager.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;   
      document.form1.action=path+"/web/sdttWeb/systemManager/zoneManager.jsp?curpage="+pageno;
      document.form1.submit();
     }
     function delzd(id){
     if(confirm("您确定删除此区域信息？")){
     		document.form1.action=path+"/servlet/zhandian?action=deledatequyu&id="+id;
      		document.form1.submit();
   
    }
    }
    function xiangxi(id){
    	window.open("../jizan/xxquery.jsp?id="+id, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
    }
    function dfinfo(id){
    
    	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/dfinfo.jsp?id="+id;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
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
    //alert(intnum);
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
	
	///////////////////////////////////////////////////////////
	function sendRequest(url,para) {

		createXMLHttpRequest();
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="xxlx"){
			XMLHttpReq.onreadystatechange = processResponse_xxlx;}//指定响应函数
// 		}else if(para=="shi"){
// 			XMLHttpReq.onreadystatechange = processResponse_shi;
// 		}else if(para=="xian"){
// 			XMLHttpReq.onreadystatechange = processResponse_xian;
// 		}
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
    function processResponse_xxlx() {
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
    
// function processResponse_sheng() {
//     	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
//         	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
//             	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            	
            	//var res = XMLHttpReq.responseText;
            
//               updateShi(res);
                       
//             } else { //页面不正常
//                 window.alert("您所请求的页面有异常。");
//             }
//         }
//     }

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

	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
	 //alert("11111");
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

	window.open("../systemManager/addzoneManager.jsp", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
}
function update(id){
	window.open("../systemManager/addzoneManager.jsp?id="+id+"&bz="+"1", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
}
function query(){
	
	document.form1.action=path+"/web/sdttWeb/systemManager/zoneManager.jsp";
}
function daochu(){
	
document.form1.action =  path + "/servlet/SysManger?bz=" +'qygl';
document.form1.submit();
}
//document.form1.AGCODE.value='<%=AGCODE%>';
document.form1.agname.value='<%=agname%>';

</script>					

</body>

</html>
