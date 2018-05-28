
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.noki.mobi.common.Account"%>

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
   String name=request.getParameter("name")!=null?request.getParameter("name"):"";
   
   String sheng = "137";
    String color = null;
    String shi=request.getParameter("shi")!=null?request.getParameter("shi"):"";
    String bianma=request.getParameter("bianma")!=null?request.getParameter("bianma"):"";
    String gongsi=request.getParameter("gongsi")!=null?request.getParameter("gongsi"):"0";
    String bumen=request.getParameter("bumen")!=null?request.getParameter("bumen"):"0";
    String banzu=request.getParameter("banzu")!=null?request.getParameter("banzu"):"0";
   System.out.println(gongsi);
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
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
	<form action="" name="form1" method="post">
		<ul class="tab">
			<li class="first"></li>
			<li ><a href="list.jsp">电费管理</a>
			</li>
			<li class="end"></li>
		</ul>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr valign="top">
				<td width="12"><img src="../images/space.gif" width="12"
					height="17" />
				</td>
				<td>
					<div class="content01">
						<div class="tit01">电费信息</div>
						<div class="content01_1">
							

							<div class="tbtitle01">
								<b>电费查询一览</b>
							</div>
							
							<table width="800px" border="0" cellpadding="0" cellspacing="0"
								class="tb_list1">
								<tr align="right">
									
								</tr>

								<tr align="center">
									<th width="10px">序号</th>
									<th>区县</th>
									<th>站点名称</th>
									<th>供电方式</th>
									<th>电量</th>
									<th>电费单价</th>
									<th>缴费金额</th>
									<th>缴费日期</th>
									<th>票据类型</th>
									<th>业主</th>
									<th>分摊比例%</th>
									<th>分摊金额</th>
									<th>报账单号</th>
								</tr>
								
								

								<%
								String whereStr = "";
											if (gongsi != null && !gongsi.equals("") && !gongsi.equals("0")) {
												whereStr = whereStr + " and d2.fdeptcode='" + gongsi + "'";
											}
											if(bumen != null && !bumen.equals("") && !bumen.equals("0"))
											{
												whereStr = whereStr + " and d2.deptcode='" + bumen + "'";
											}
											if(name != null && !name.equals(""))
											{
												whereStr = whereStr + " and d2.deptname like'%" + name + "%'";
											}
											
								
								 SiteManage bean = new SiteManage();
 						       	 ArrayList fylist = new ArrayList();
						         fylist = bean.getElectric_tieta(curpage,whereStr);
 						       	
 						       	 allpage=bean.getAllPage();
						       	
							     String area="",district="",id = "" ,jzname="", power_name="",ELECTRICITY="",
							    		 PRICE="",PAYMENT_AMOUNT="",PAYMENT_DATE="",PAPER_TYPE="",OWNER="",
							    		 SHARE_PROPORTION="",SHARE_AMOUNT="",BILLNO="";
								
								 int intnum = pagesize*(curpage-1)+1;
								 System.out.println(intnum);
								 if(fylist!=null)
 									{
 									int fycount = ((Integer)fylist.get(0)).intValue();
 									for( int k = fycount;k<fylist.size()-1;k+=fycount){
						
						       			//id = (String) fylist.get(k + fylist.indexOf("ID"));
 						       			area = (String) fylist.get(k + fylist.indexOf("AREA"));
 						       			district = (String) fylist.get(k + fylist.indexOf("DISTRICT"));
		 						       	jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
		 						        power_name = (String) fylist.get(k + fylist.indexOf("POWER_NAME"));
		 						        ELECTRICITY = (String) fylist.get(k + fylist.indexOf("ELECTRICITY"));
		 						        PRICE = (String) fylist.get(k + fylist.indexOf("PRICE"));
		 						        PAYMENT_AMOUNT = (String) fylist.get(k + fylist.indexOf("PAYMENT_AMOUNT"));
		 						        PAPER_TYPE = (String) fylist.get(k + fylist.indexOf("PAPER_TYPE"));
		 						        OWNER = (String) fylist.get(k + fylist.indexOf("OWNER"));
		 						        SHARE_PROPORTION = (String) fylist.get(k + fylist.indexOf("SHARE_PROPORTION"));
		 						        SHARE_AMOUNT = (String) fylist.get(k + fylist.indexOf("SHARE_AMOUNT"));
		 						        BILLNO = (String) fylist.get(k + fylist.indexOf("BILLNO"));
		 						       PAYMENT_DATE = (String) fylist.get(k + fylist.indexOf("PAYMENT_DATE"));
 						       			
						       			if (intnum % 2 == 0) {
						       				color = "#DDDDDD";
						
						       			} else {
						       				color = "#FFFFFF";
						       			}
					
						       			
						       %>

								<!-- 数据加载  Start-->
								<tr align="center">
									<td width="10"><%=intnum++%></td>
									<td align="left"><%=area%> <%=district%></td>
									<td align="left"><%=jzname%></td>
									<td align="left"><%=power_name%></td>
									<td align="left"><%=ELECTRICITY%></td>
									<td align="right"><%=PRICE%></td>
									<td align="left"><%=PAYMENT_AMOUNT%></td>
									<td align="center"><%=PAYMENT_DATE%></td>
									<td align="left"><%=PAPER_TYPE%></td>
									<td align="left"><%=OWNER%></td>
									<td align="left"><%=SHARE_PROPORTION%></td>
									<td align="right"><%=SHARE_AMOUNT%></td>
									<td align="left"><%=BILLNO%></td>


									
								</tr>
								
								<% } %>

								<!-- 数据加载 End -->

								<tr bgcolor="#ffffff">
									<td colspan="10" align="left">
										<div align="left">

											&nbsp;&nbsp; <font color='#1E90FF'> <%
									     	if (curpage != 1) {
									     			out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
									     		} else {
									     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
									     		}
									     %> </font> &nbsp;&nbsp; <font color='#1E90FF'> <%
									     	if (curpage != 1) {
									     			out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
									     		} else {
									     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
									     		}
									     %> </font> &nbsp;&nbsp; 转到 <select name="page"
												onchange="javascript:gopagebyno(document.form1.page.value)"
												style="width:40px;font-family: 宋体;font-size:12px;line-height:120%;">
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
											</select>&nbsp;&nbsp;共 <font color='#1E90FF'><b><%=allpage%></b>&nbsp;</font>页
											&nbsp;&nbsp; <font color='#1E90FF'> <%
						     	if (allpage != 0 && (curpage < allpage)) {
						     			out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");
						     		}
						     %> </font> &nbsp;&nbsp; <font color='#1E90FF'> <%
					     	if (allpage != 0 && (curpage < allpage)) {
					     			out.print("<a href='javascript:gopagebyno(" + allpage+ ")'><img src='../images/page-last.gif'></a>");
					     		} else {
					     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
					     		}
					     %> </font> &nbsp;&nbsp;


										</div></td>
								</tr>

							</table>

						</div>
					</div></td>
			</tr>
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
      		document.form1.action=path+"/web/sdttWeb/tieta/list.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      		document.form1.action=path+"/web/sdttWeb/tieta/list.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;   
      document.form1.action=path+"/web/sdttWeb/tieta/list.jsp?curpage="+pageno;
      document.form1.submit();
     }
     function delzd(id){
     if(confirm("您确定删除此部门信息？")){
     		document.form1.action=path+"/servlet/zhandian?action=deleteDept&id="+id;
      		document.form1.submit();
   
    }
    }
    function xiangxi(id){
    	window.open("../jizan/xxquery.jsp?id="+id, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
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
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian,ammeters,ammeterdegrees,electricfees&tab=zd,am,ad,ef&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
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
			XMLHttpReq.onreadystatechange = processResponse_xxlx;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
		}else if(para=="bumen"){
			XMLHttpReq.onreadystatechange = processResponse_bumen;
		}else if(para=="banzu"){
			XMLHttpReq.onreadystatechange = processResponse_banzu;
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

function processResponse_bumen() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("ress");
             updateBumen(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_banzu() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("ress");
           updatebanzu(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}

function updateBumen(res){
	var shilist = document.all.bumen;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function updatebanzu(res){
	var shilist = document.all.banzu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function addjz(){
    window.open("../systemManager/addDepartment_pro.jsp?sheng=<%=sheng%>","newwindow", "height=500, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}
function update(id){
	window.open("../systemManager/addDepartment_pro.jsp?id="+id+"&sheng=<%=sheng%>", "newwindow", "height=500, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}
function query(){
	document.form1.action=path+"/web/sdttWeb/systemManager/department_pro.jsp";
	document.form1.submit();
}
function changegongsi(){
	var sid = document.form1.gongsi.value;
	
	if(sid=="0"){
		var shilist = document.all.bumen;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=bumen&pid="+sid,"bumen");
	}
}
function changebumen(){
	var sid = document.form1.bumen.value;
	
	if(sid=="0"){
		var shilist = document.all.banzu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=bumen&pid="+sid,"banzu");
	}
}
function daochu(){
	
document.form1.action =  path + "/servlet/SysManger?bz=" +'bmgl';
document.form1.submit();
}

document.form1.gongsi.value="<%=gongsi%>";
document.form1.bumen.value="<%=bumen%>";
</script>		
</body>
</html>
